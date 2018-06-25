package fi.solita.utils.meta.generators;

import static fi.solita.utils.meta.Helpers.element2Fields;
import static fi.solita.utils.meta.Helpers.elementGenericQualifiedName;
import static fi.solita.utils.meta.Helpers.hasNonQmarkGenerics;
import static fi.solita.utils.meta.Helpers.importType;
import static fi.solita.utils.meta.Helpers.importTypes;
import static fi.solita.utils.meta.Helpers.isFinal;
import static fi.solita.utils.meta.Helpers.isPrivate;
import static fi.solita.utils.meta.Helpers.padding;
import static fi.solita.utils.meta.Helpers.privateElement;
import static fi.solita.utils.meta.Helpers.publicElement;
import static fi.solita.utils.meta.Helpers.qualifiedName;
import static fi.solita.utils.meta.Helpers.resolveBoxedGenericType;
import static fi.solita.utils.meta.Helpers.resolveVisibility;
import static fi.solita.utils.meta.Helpers.simpleName;
import static fi.solita.utils.meta.Helpers.staticElements;
import static fi.solita.utils.meta.Helpers.typeParameter2String;
import static fi.solita.utils.meta.generators.Content.EmptyLine;
import static fi.solita.utils.meta.generators.Content.None;
import static fi.solita.utils.meta.generators.Content.catchBlock;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.contains;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.subtract;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.FunctionalA.concat;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import fi.solita.utils.meta.Helpers;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function3;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple2;

public class InstanceFieldsAsFunctions extends Generator<InstanceFieldsAsFunctions.Options> {
    
    public static final InstanceFieldsAsFunctions instance = new InstanceFieldsAsFunctions();
    
    @SuppressWarnings("rawtypes")
    public static interface Options extends GeneratorOptions {
        Class<? extends Apply> getClassForInstanceFields(boolean isFinal);
        Class<? extends Apply> getPredicateClassForInstanceFields(boolean isFinal);
        List<String> getAdditionalBodyLinesForInstanceFields();
        boolean generateMemberAccessorForFields();
        boolean generateMemberInitializerForFields();
        boolean generateMemberNameAccessorForFields();
        boolean makeFieldsPublic();
        boolean onlyPublicMembers();
        boolean includePrivateMembers();
    }
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
        Iterable<VariableElement> elements = element2Fields.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(publicElement, elements);
        } else if (!options.includePrivateMembers()) {
            elements = filter(not(privateElement), elements);
        }
      
        return flatMap(variableElementGen.ap(options, new Helpers.EnvDependent(processingEnv)), filter(not(staticElements), elements));
    }
    
    static final Transformer<String,Pattern> toReplacePattern = new Transformer<String,Pattern>() {
        @Override
        public Pattern transform(String obj) {
            return Pattern.compile("([^a-zA-Z0-9_])([?]\\s*(?:extends|super)\\s+)?" + Pattern.quote(obj.toString()) + "([^a-zA-Z0-9_])");
        }
    };
    
    // this hack is a way to share most of the code with other generators
    static abstract class F extends Function3<Options, Helpers.EnvDependent, VariableElement, Iterable<String>> {
        String enclosingElementQualifiedNameImported;
        String relevantTypeParamsString;
        List<String> relevantTypeParamsWithoutConstraints;
        String fundef;
        boolean needsToBeFunction;
    }
    
    public static F variableElementGen = new F() {
        @Override
        public Iterable<String> apply(Options options, Helpers.EnvDependent helper, VariableElement field) {
            TypeElement enclosingElement = (TypeElement) field.getEnclosingElement();
            String enclosingElementQualifiedName = qualifiedName.apply(enclosingElement);
            enclosingElementQualifiedNameImported = importTypes(enclosingElementQualifiedName);
            
            String returnType = resolveBoxedGenericType(field.asType(), helper.elementUtils);
            
            Iterable<String> allTypeParams = map(typeParameter2String, enclosingElement.getTypeParameters());
            List<String> allTypeParamsWithoutConstraints = newList(map(simpleName, enclosingElement.getTypeParameters()));
            final String rett = ("." + returnType + ".");
            List<Pair<String, String>> relevantTypeParamPairs = newList(filter(new Predicate<Tuple2<String,String>>() {
                @Override
                public boolean accept(Tuple2<String,String> candidate) {
                    return rett.matches(".*[^a-zA-Z0-9_]" + Pattern.quote(candidate._2.toString()) + "[^a-zA-Z0-9_].*");
                }
            }, zip(allTypeParams, allTypeParamsWithoutConstraints)));
            
            List<String> relevantTypeParams = newList(map(Helpers.<String>left(), relevantTypeParamPairs));
            relevantTypeParamsWithoutConstraints = newList(map(Helpers.<String>right(), relevantTypeParamPairs));

            final List<String> toReplace = newList(subtract(allTypeParamsWithoutConstraints, relevantTypeParamsWithoutConstraints));
            final List<Pattern> toReplacePatterns = newList(map(toReplacePattern, toReplace));
            Transformer<String,String> doReplace = new Transformer<String,String>() {
                @Override
                public String transform(String candidate) {
                    for (Pattern p: toReplacePatterns) {
                        candidate = p.matcher(candidate).replaceAll("$1?$3");
                    }
                    return candidate;
                }
            };
            
            boolean isPrivate = isPrivate(field);
            boolean isFinal = isFinal(field);
            needsToBeFunction = !relevantTypeParams.isEmpty();
            
            relevantTypeParamsString = isEmpty(relevantTypeParams) ? "" : "<" + mkString(", ", map(doReplace, relevantTypeParams)) + ">";
            returnType = doReplace.apply(returnType);
            String returnTypeImported = importTypes(returnType);
            
            String enclosingElementGenericQualifiedName = doReplace.apply(elementGenericQualifiedName(enclosingElement));
            String enclosingElementGenericQualifiedNameImported = importTypes(enclosingElementGenericQualifiedName);
            
            String visibility = options.makeFieldsPublic() ? "public " : resolveVisibility(field);
            String modifiers = visibility + "static final";
            String fieldName = field.getSimpleName().toString();
            
            final String returnClause = "return $self == null ? null : " + (isPrivate ? "(" + (needsToBeFunction ? "Object" : returnTypeImported) + ")" : "");
            
            boolean usePredicate = returnType.equals(Boolean.class.getName()) || returnType.equals(boolean.class.getName());
            Class<?> fieldClass = usePredicate ? options.getPredicateClassForInstanceFields(isFinal) : options.getClassForInstanceFields(isFinal);
            String fieldClassImported = importType(fieldClass);
            fundef = fieldClassImported + "<" + enclosingElementGenericQualifiedNameImported + (usePredicate ? "" : ", " + returnTypeImported) + ">";
            String privateFundef = fieldClassImported + "<" + enclosingElementQualifiedNameImported + (usePredicate ? "" : ",Object") + ">";
            String declaration = modifiers + " " + (needsToBeFunction ? relevantTypeParamsString + " ": "") + fundef + " " + fieldName;
            String privateDeclaration = "private static final " + fieldClassImported + " $" + fieldName;
            String setterFundef = importType(Function1.class) + "<" + (needsToBeFunction ? "Object" : returnTypeImported) + ",Void>";
            String implementedMethod = Predicate.class.isAssignableFrom(fieldClass) ? "Boolean apply" : (needsToBeFunction ? "Object" : returnTypeImported) + " apply";

            Iterable<String> tryBlock = isPrivate
                ? Some(returnClause + "getMember().get($self);")
                : Some(returnClause + "$self." + fieldName + ";");
            
            Iterable<String> tryCatchBlock = isPrivate
                ? concat(
                    Some("try {"),
                    map(padding, tryBlock),
                    catchBlock,
                    Some("}"))
                : tryBlock;
                        
            Iterable<String> applyBlock = concat(
                Some("public final " + implementedMethod + "(final " + (needsToBeFunction ? enclosingElementQualifiedNameImported : enclosingElementGenericQualifiedNameImported) + " $self) {"),
                map(padding, tryCatchBlock),
                Some("}")
            );
            
            Iterable<String> setTryBlock = isPrivate
                    ? Some("getMember().set($self, $newValue);")
                    : Some("$self." + fieldName + " = $newValue;");
            
            Iterable<String> setTryCatchBlock = isPrivate
                    ? concat(
                        Some("try {"),
                        map(padding, setTryBlock),
                        catchBlock,
                        Some("}"))
                    : setTryBlock;
            
            Iterable<String> setBlock = concat(
                    needsToBeFunction
                        ? Some("public final " + setterFundef + " setter(final " + (needsToBeFunction ? enclosingElementQualifiedNameImported : enclosingElementGenericQualifiedNameImported) + " $self) { return new " + setterFundef + "() { public Void apply(final Object $newValue) {")
                        : Some("public final " + relevantTypeParamsString + " " + setterFundef + " setter(final " + (needsToBeFunction ? enclosingElementQualifiedNameImported : enclosingElementGenericQualifiedNameImported) + " $self) { return new " + setterFundef + "() { public Void apply(final " + returnTypeImported + " $newValue) {"),
                    map(padding, setTryCatchBlock),
                    Some("    return null;"),
                    Some("}};}")
                );
            
            String initParams = "(" + enclosingElementQualifiedNameImported + ".class, \"" + fieldName + "\")";
                        
            @SuppressWarnings("unchecked")
            Iterable<String> res = concat(
                needsToBeFunction
                    ? newList(enclosingElementQualifiedNameImported.equals(enclosingElementGenericQualifiedNameImported) ? "" : "@SuppressWarnings(\"rawtypes\")",
                              privateDeclaration + " = new " + privateFundef + initParams + " {")
                    : Some(declaration + " = new " + fundef + initParams + " {"),
                map(padding, options.getAdditionalBodyLinesForInstanceFields()),
                EmptyLine,
                isPrivate && !needsToBeFunction && (field.getEnclosingElement().getAnnotation(SuppressWarnings.class) == null || !contains("unchecked", field.getEnclosingElement().getAnnotation(SuppressWarnings.class).value())) && (hasNonQmarkGenerics(returnType) || field.asType().getKind() == TypeKind.TYPEVAR)
                    ? Some("    @SuppressWarnings(\"unchecked\")")
                    : None,
                map(padding, applyBlock),
                isFinal ? None : map(padding, setBlock),
                Some("};"),
                needsToBeFunction
                    ? newList("@SuppressWarnings(\"unchecked\")",
                              declaration + "() { return (" + fundef + ")$" + fieldName + "; }")
                    : None,
                EmptyLine
            );
            return res;
        }
    };
}