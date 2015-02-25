package fi.solita.utils.meta.generators;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.repeat;
import static fi.solita.utils.functional.Functional.subtract;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.FunctionalM.groupBy;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.prepend;
import static fi.solita.utils.meta.Helpers.allTypeParams;
import static fi.solita.utils.meta.Helpers.allUsedTypeParameters;
import static fi.solita.utils.meta.Helpers.boxedQualifiedName;
import static fi.solita.utils.meta.Helpers.element2Fields;
import static fi.solita.utils.meta.Helpers.element2Methods;
import static fi.solita.utils.meta.Helpers.elementGenericQualifiedName;
import static fi.solita.utils.meta.Helpers.hasNonQmarkGenerics;
import static fi.solita.utils.meta.Helpers.hasRawTypes;
import static fi.solita.utils.meta.Helpers.importType;
import static fi.solita.utils.meta.Helpers.importTypes;
import static fi.solita.utils.meta.Helpers.isInstanceMethod;
import static fi.solita.utils.meta.Helpers.isPrivate;
import static fi.solita.utils.meta.Helpers.joinWithSpace;
import static fi.solita.utils.meta.Helpers.parameterTypesAsClasses;
import static fi.solita.utils.meta.Helpers.paramsWithCast;
import static fi.solita.utils.meta.Helpers.publicElement;
import static fi.solita.utils.meta.Helpers.qualifiedName;
import static fi.solita.utils.meta.Helpers.relevantTypeParams;
import static fi.solita.utils.meta.Helpers.removeGenericPart;
import static fi.solita.utils.meta.Helpers.replaceTypeVarWithObject;
import static fi.solita.utils.meta.Helpers.resolveBoxedGenericType;
import static fi.solita.utils.meta.Helpers.resolveVisibility;
import static fi.solita.utils.meta.Helpers.returnsVoid;
import static fi.solita.utils.meta.Helpers.simpleName;
import static fi.solita.utils.meta.Helpers.staticElements;
import static fi.solita.utils.meta.Helpers.typeParameter2String;
import static fi.solita.utils.meta.Helpers.typeVariableReplacer;
import static fi.solita.utils.meta.generators.Content.EmptyLine;
import static fi.solita.utils.meta.generators.Content.None;
import static fi.solita.utils.meta.generators.Content.catchBlock;
import static fi.solita.utils.meta.generators.Content.reflectionInvokationArgs;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function4;
import fi.solita.utils.functional.Functional;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple2;
import fi.solita.utils.meta.Helpers;

public class MethodsAsFunctions extends Generator<MethodsAsFunctions.Options> {

    @SuppressWarnings("rawtypes")
    public static interface Options extends GeneratorOptions {
        Class<? extends Apply> getClassForMethods(int argCount);
        Class<? extends Apply> getPredicateClassForMethods();
        List<String> getAdditionalBodyLinesForMethods(ExecutableElement element);
        boolean generateMemberInitializerForMethods();
        boolean generateMemberAccessorForMethods();
        boolean generateMemberNameAccessorForMethods();
        boolean onlyPublicMembers();
    }
    
    public static final MethodsAsFunctions instance = new MethodsAsFunctions();
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
        Iterable<ExecutableElement> elements = element2Methods.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(publicElement, elements);
        }
        
        Iterable<VariableElement> processedFields = element2Fields.apply(source);
        if (options.onlyPublicMembers()) {
            processedFields = filter(publicElement, processedFields);
        }
        processedFields = filter(not(staticElements), processedFields);
      
        Iterable<List<ExecutableElement>> elementsByName = groupBy(simpleName, elements).values();
        Function1<Entry<Integer, ExecutableElement>, Iterable<String>> singleElementTransformer = executableElementGen.ap(new Helpers.EnvDependent(processingEnv), options, newSet(map(simpleName, processedFields)));
        
        return flatMap(singleElementTransformer, flatMap(zipWithIndex, elementsByName));
    }
    
    public static Function4<Helpers.EnvDependent, Options, Set<String>, Map.Entry<Integer, ExecutableElement>, Iterable<String>> executableElementGen = new Function4<Helpers.EnvDependent, Options, Set<String>, Map.Entry<Integer, ExecutableElement>, Iterable<String>>() {
        @Override
        public Iterable<String> apply(Helpers.EnvDependent helper, Options options, Set<String> reservedNames, Map.Entry<Integer, ExecutableElement> entry) {
            try {
                ExecutableElement method = entry.getValue();
                TypeElement enclosingElement = (TypeElement) method.getEnclosingElement();
                String enclosingElementQualifiedName = qualifiedName.apply(enclosingElement);
                
                int index = entry.getKey() + (reservedNames.contains(simpleName.apply(method)) ? 1 : 0);
                
                List<? extends TypeParameterElement> allTypeParamsForMethod = newList(allTypeParams(method));
                List<? extends TypeParameterElement> relevantTypeParamsForMethod = newList(relevantTypeParams(method));
                final List<String> relevantTypeParams = newList(map(typeParameter2String, relevantTypeParamsForMethod));
                final Set<String> relevantTypeParamsWithoutConstraints = newSet(map(simpleName, relevantTypeParamsForMethod));
                final List<String> allTypeParamsWithoutConstraints = newList(map(simpleName, allTypeParamsForMethod));
                List<? extends VariableElement> parameters = method.getParameters();
                List<? extends TypeParameterElement> typeParameters = method.getTypeParameters();
                
                Transformer<String, String> doReplace = typeVariableReplacer(newList(subtract(allTypeParamsWithoutConstraints, relevantTypeParamsWithoutConstraints)));
                
                String modifiers = resolveVisibility(method) + "static final";
                String methodName = method.getSimpleName().toString();
                String returnType = doReplace.transform(resolveBoxedGenericType(method.getReturnType(), helper.elementUtils));
                String returnTypeImported = importTypes(returnType);
                List<String> argumentTypes = newList(map(boxedQualifiedName, parameters));
                List<? extends TypeParameterElement> enclosingElementTypeParameters = enclosingElement.getTypeParameters();
                
                boolean needsToBeFunction = !relevantTypeParams.isEmpty();
                final boolean isPrivate = isPrivate(method);
                boolean isInstanceMethod = isInstanceMethod(method);
                boolean zeroArgInstanceMethod = isInstanceMethod && parameters.isEmpty(); // handle no-arg methods like static functions
                boolean handleAsInstanceMethod = isInstanceMethod && !zeroArgInstanceMethod;
                boolean returnsVoid = returnsVoid(method);
                boolean needsTypeArguments = !allUsedTypeParameters(method).isEmpty();
                
                boolean throwsChecked = helper.throwsCheckedExceptions(method);
                boolean hasRawTypes = hasRawTypes(method);
                
                int argCount = zeroArgInstanceMethod ? 1 : parameters.size(); // no-arg instance methods need $self parameter
                
                String enclosingElementGenericQualifiedName = doReplace.transform(needsTypeArguments
                        ? elementGenericQualifiedName(enclosingElement)
                        : enclosingElementQualifiedName + (enclosingElementTypeParameters.isEmpty() ? "" : "<" + mkString(", ", repeat("?", enclosingElementTypeParameters.size())) + ">"));
                
                String instanceName = isInstanceMethod ? "$self" : importTypes(enclosingElementQualifiedName);
                argumentTypes = newList(map(doReplace, argumentTypes));
                String relevantTypeParamsString = relevantTypeParams.isEmpty() ? " " : "<" + importTypes(mkString(", ", map(doReplace, relevantTypeParams))) + "> ";
                
                List<String> argTypes = zeroArgInstanceMethod ? newList(enclosingElementGenericQualifiedName) : argumentTypes;
                Iterable<String> argNames = zeroArgInstanceMethod ? newList("$self") : map(simpleName, parameters);
                List<String> argNamesWithCast = newList(paramsWithCast(parameters, isPrivate));
                
                //boolean hasPlainGenericArguments = exists(map(argTypes, removeGenericPart), not(contains('.')));
                boolean optimize = false; // skipped, since javac compiler starts to complain about ambiguous methods in some cases... // needsToBeFunction && !hasPlainGenericArguments;
                
                String returnClause = returnsVoid ? "" : "return " + (isPrivate && !optimize ? "(" + returnTypeImported + ")" : "");
    
                boolean usePredicate = !handleAsInstanceMethod && argCount == 1 && (returnType.equals("java.lang.Boolean") || returnType.equals("boolean"));
                Class<?> methodClass = usePredicate ? options.getPredicateClassForMethods() : options.getClassForMethods(argCount + (handleAsInstanceMethod ? 1 : 0));
                
                Iterable<String> implementedMethodArgTypes = handleAsInstanceMethod ? cons(enclosingElementGenericQualifiedName, argTypes) : argTypes;
                Iterable<String> privateImplementedMethodArgTypes  = map(removeGenericPart.andThen(replaceTypeVarWithObject), handleAsInstanceMethod ? cons(enclosingElementQualifiedName, argTypes) : argTypes);
    
                String implementedMethod = Predicate.class.isAssignableFrom(methodClass) ? "boolean accept" : (optimize ? "Object" : returnTypeImported) + " apply";
                String fundef = importType(methodClass) + "<" + importTypes(mkString(", ", usePredicate ? implementedMethodArgTypes : concat(implementedMethodArgTypes, newSet(returnType)))) + "> ";
                String privateFundef = importType(methodClass) + "<" + importTypes(mkString(", ", usePredicate ? privateImplementedMethodArgTypes : concat(privateImplementedMethodArgTypes, newSet("Object")))) + "> ";
                
                String declaration = modifiers + " " + relevantTypeParamsString + fundef + " " + methodName + (index == 0 ? "" : index);
                String privateDeclaration = "private static final " + importType(methodClass) + " $" + methodName + (index == 0 ? "" : index);
                
                Iterable<String> tryBlock = concat(
                    isPrivate
                        ? Some(returnClause + "getMember().invoke(" + mkString(", ", cons(isInstanceMethod ? "$self" : "null", argNamesWithCast)) + ");")
                        : Some(returnClause + instanceName + "." + (typeParameters.isEmpty() || optimize ? "" : "<" + mkString(", ", map(simpleName, typeParameters)) + ">") + methodName + "(" + mkString(", ", argNamesWithCast) + ");"),
                    returnsVoid
                        ? Some("return null;")
                        : None
                );
                Iterable<String> tryCatchBlock = isPrivate || throwsChecked
                    ? concat(
                        Some("try {"),
                        map(Helpers.padding, tryBlock),
                        catchBlock,
                        Some("}"))
                    : tryBlock;
                Iterable<String> applyBlock = concat(
                    Some("public final " + implementedMethod + "(" + importTypes(mkString(", ", map(joinWithSpace, zip(map(prepend("final "), optimize ? privateImplementedMethodArgTypes : implementedMethodArgTypes), handleAsInstanceMethod ? cons("$self", argNames) : argNames)))) + ") { "),
                    map(Helpers.padding, tryCatchBlock),
                    Some("}")
                );
                Iterable<String> contentBlock = concat(
                    map(Helpers.padding, applyBlock),
                    map(Helpers.padding, options.getAdditionalBodyLinesForMethods(method))
                );
                
                String initParams = "(" + importTypes(enclosingElementQualifiedName) + ".class, " + mkString(", ", cons("\"" + methodName + (index == 0 ? "" : index) + "\"", reflectionInvokationArgs(parameterTypesAsClasses(method, relevantTypeParamsForMethod)))) + ")";
                
                Iterable<String> warnings = concat(
                    isPrivate && (method.getReturnType().getKind() == TypeKind.TYPEVAR || hasNonQmarkGenerics(returnType)) ? Some("\"unchecked\"") : None,
                    hasRawTypes ? Some("\"rawtypes\"") : None);
                        
                @SuppressWarnings("unchecked")
                Iterable<String> res = concat(
                    isEmpty(warnings)
                        ? None
                        : Some("@SuppressWarnings({" + mkString(",", warnings) + "})"),
                    method.getAnnotation(Deprecated.class) == null
                        ? None
                        : Some("@Deprecated"),
                    needsToBeFunction
                        ? optimize
                            ? Some(privateDeclaration + " = new " + privateFundef + initParams + " {")
                            : Some(declaration + "() { return new " + fundef + initParams + " {")
                        : Some(declaration + " = new " + fundef + initParams + " {"),
                    contentBlock,
                    Some("};"),
                    needsToBeFunction
                        ? optimize
                            ? newList("@SuppressWarnings(\"unchecked\")",
                                      declaration + "() { return (" + fundef + ")$" + methodName + (index == 0 ? "" : index) + "; }")
                            : Some("}")
                        : None,
                    EmptyLine
                );
                return res;
            } catch (RuntimeException e) {
                throw new RuntimeException("Exception while processing: " + entry.getValue(), e);
            }
        }
    };
    
    public static final Function1<Iterable<ExecutableElement>, Iterable<Tuple2<Integer,ExecutableElement>>> zipWithIndex = new Function1<Iterable<ExecutableElement>, Iterable<Tuple2<Integer,ExecutableElement>>>() {
        @Override
        public Iterable<Tuple2<Integer, ExecutableElement>> apply(Iterable<ExecutableElement> t) {
            return Functional.zipWithIndex(t);
        }
    };
}