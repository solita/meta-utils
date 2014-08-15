package fi.solita.utils.meta.generators;

import static fi.solita.utils.meta.Helpers.boxedQualifiedName;
import static fi.solita.utils.meta.Helpers.element2Constructors;
import static fi.solita.utils.meta.Helpers.elementGenericQualifiedName;
import static fi.solita.utils.meta.Helpers.hasRawTypes;
import static fi.solita.utils.meta.Helpers.importType;
import static fi.solita.utils.meta.Helpers.importTypes;
import static fi.solita.utils.meta.Helpers.isPrivate;
import static fi.solita.utils.meta.Helpers.joinWithSpace;
import static fi.solita.utils.meta.Helpers.padding;
import static fi.solita.utils.meta.Helpers.parameterTypesAsClasses;
import static fi.solita.utils.meta.Helpers.paramsWithCast;
import static fi.solita.utils.meta.Helpers.publicElement;
import static fi.solita.utils.meta.Helpers.qualifiedName;
import static fi.solita.utils.meta.Helpers.relevantTypeParams;
import static fi.solita.utils.meta.Helpers.resolveVisibility;
import static fi.solita.utils.meta.Helpers.simpleName;
import static fi.solita.utils.meta.Helpers.typeParameter2String;
import static fi.solita.utils.meta.generators.Content.EmptyLine;
import static fi.solita.utils.meta.generators.Content.None;
import static fi.solita.utils.meta.generators.Content.catchBlock;
import static fi.solita.utils.meta.generators.Content.reflectionInvokationArgs;
import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.Functional.zipWithIndex;
import static fi.solita.utils.functional.FunctionalA.concat;
import static fi.solita.utils.functional.FunctionalImpl.filter;
import static fi.solita.utils.functional.FunctionalImpl.map;
import static fi.solita.utils.functional.Option.Some;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;

import fi.solita.utils.meta.Helpers;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function3;

public class ConstructorsAsFunctions extends Generator<ConstructorsAsFunctions.Options> {
    
    public static interface Options extends GeneratorOptions {
        @SuppressWarnings("rawtypes")
        Class<? extends Apply> getClassForConstructors(int argCount);
        List<String> getAdditionalBodyLinesForConstructors(ExecutableElement element);
        boolean generateMemberInitializerForConstructors();
        boolean generateMemberAccessorForConstructors();
        boolean onlyPublicMembers();
    }
    
    public static ConstructorsAsFunctions instance = new ConstructorsAsFunctions();
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
        if (source.getModifiers().contains(Modifier.ABSTRACT)) {
            return emptyList();
        }

        Iterable<ExecutableElement> elements = element2Constructors.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, publicElement);
        }
        
        Function1<Entry<Integer, ExecutableElement>, Iterable<String>> singleElementTransformer = constructorGen.ap(new Helpers.EnvDependent(processingEnv), options);
        Iterable<Iterable<String>> rm = newList(map(newList(zipWithIndex(elements)), singleElementTransformer));
        return flatten(rm);
    }
    
    public static Function3<Helpers.EnvDependent, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>> constructorGen = new Function3<Helpers.EnvDependent, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>>() {
        @Override
        public Iterable<String> apply(Helpers.EnvDependent helper, Options options, Map.Entry<Integer, ExecutableElement> entry) {
            ExecutableElement constructor = entry.getValue();
            TypeElement enclosingElement = (TypeElement) constructor.getEnclosingElement();
            String enclosingElementQualifiedName = qualifiedName.apply(enclosingElement);
            int index = entry.getKey();

            List<? extends TypeParameterElement> relevantTypeParams = newList(relevantTypeParams(constructor));
            String relevantTypeParamsString = relevantTypeParams.isEmpty() ? "" : "<" + importTypes(mkString(", ", map(relevantTypeParams, typeParameter2String))) + ">";
            List<? extends VariableElement> parameters = constructor.getParameters();
            boolean needsToBeFunction = !relevantTypeParams.isEmpty();
            
            boolean isPrivate = isPrivate(constructor);
            boolean throwsChecked = helper.throwsCheckedExceptions(constructor);
            boolean hasRawTypes = hasRawTypes(constructor);
            
            String returnType = elementGenericQualifiedName(enclosingElement);
            String returnTypeImported = importTypes(elementGenericQualifiedName(enclosingElement));
            
            int argCount = constructor.getParameters().size();
            List<String> argTypes = newList(map(parameters, boxedQualifiedName));
            List<String> argNames = newList(map(parameters, simpleName));
            List<String> argNamesWithCast = newList(paramsWithCast(parameters, isPrivate));

            String fieldName = "$" + (index == 0 ? "" : index);
            Class<?> constructorClass = options.getClassForConstructors(argCount);
            String fundef = importType(constructorClass) + "<" + importTypes(mkString(", ", concat(argTypes, newList(returnType)))) + ">";
            String declaration = resolveVisibility(constructor) + "static final " + relevantTypeParamsString + " " + fundef + " " + fieldName;
            
            Iterable<String> tryBlock = isPrivate 
                    ? Some("return (" + returnTypeImported + ")getMember().newInstance(" + mkString(", ", argNamesWithCast) + ");")
                    : Some("return new " + returnTypeImported + "(" + mkString(", ", argNamesWithCast) + ");");
            
            Iterable<String> tryCatchBlock = isPrivate || throwsChecked
                ? concat(
                    Some("try {"),
                    map(tryBlock, padding),
                    catchBlock,
                    Some("}"))
                : tryBlock;
                    
            Iterable<String> applyBlock = concat(
                Some("public " + returnTypeImported + " apply(" + mkString(", ", map(zip(argTypes, argNames), joinWithSpace)) + ") {"),
                map(tryCatchBlock, padding),
                Some("}")
            );
            
            @SuppressWarnings("unchecked")
            Iterable<String> res = concat(
                hasRawTypes
                    ? Some("@SuppressWarnings(\"rawtypes\")")
                    : None,
                Some(declaration + (needsToBeFunction ? "() { return" : " =") + " new " + fundef + "(" + mkString(", ", cons(importTypes(enclosingElementQualifiedName) + ".class", reflectionInvokationArgs(parameterTypesAsClasses(constructor, relevantTypeParams)))) + ") {"),
                map(applyBlock, padding),
                EmptyLine,
                map(options.getAdditionalBodyLinesForConstructors(constructor), padding),
                Some("};"),
                needsToBeFunction
                    ? Some("}")
                    : None,
                EmptyLine
            );
            return res;
        }
    };

}