package fi.solita.utils.meta;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMutableList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.sequence;
import static fi.solita.utils.functional.Functional.transpose;
import static fi.solita.utils.functional.FunctionalM.find;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.matches;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.meta.Helpers.element2NestedClasses;
import static fi.solita.utils.meta.Helpers.getPackageName;
import static fi.solita.utils.meta.Helpers.nanosToMillis;
import static fi.solita.utils.meta.Helpers.generatedElements;
import static fi.solita.utils.meta.Helpers.forcedElements;
import static fi.solita.utils.meta.Helpers.padding;
import static fi.solita.utils.meta.Helpers.privateElement;
import static fi.solita.utils.meta.Helpers.publicElement;
import static fi.solita.utils.meta.Helpers.qualifiedName;
import static fi.solita.utils.meta.Helpers.removeGenericPart;
import static fi.solita.utils.meta.Helpers.simpleName;
import static fi.solita.utils.meta.Helpers.withAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Option;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.meta.generators.ConstructorsAsFunctions;
import fi.solita.utils.meta.generators.Content;
import fi.solita.utils.meta.generators.Generator;
import fi.solita.utils.meta.generators.InstanceFieldsAsEnum;
import fi.solita.utils.meta.generators.InstanceFieldsAsFunctions;
import fi.solita.utils.meta.generators.InstanceFieldsAsTuple;
import fi.solita.utils.meta.generators.MethodsAsFunctions;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("*")
@SupportedOptions({"CommonMetadataProcessor." + CommonMetadataProcessor.Options.enabled,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.generatedClassNamePattern,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.generatedPackagePattern,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.includesRegex,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.excludesRegex,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.onlyPublicMembers,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.includePrivateMembers,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.makeFieldsPublic,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.includesAnnotation,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.excludesAnnotation,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.methodsAsFunctionsEnabled,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.constructorsAsFunctionsEnabled,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.instanceFieldsAsEnumEnabled,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.instanceFieldsAsFunctionsEnabled,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.instanceFieldsAsTupleEnabled})
public class CommonMetadataProcessor<OPTIONS extends CommonMetadataProcessor.CombinedGeneratorOptions> extends AbstractProcessor {

    private static final int version = 1;
    
    public static class Options {
        public static final String enabled = "enabled";
        public static final String generatedClassNamePattern = "generatedClassNamePattern";
        public static final String generatedPackagePattern = "generatedPackagePattern";
        public static final String includesRegex = "includesRegex";
        public static final String excludesRegex = "excludesRegex";
        public static final String onlyPublicMembers = "onlyPublicMembers";
        public static final String includePrivateMembers = "includePrivateMembers";
        public static final String makeFieldsPublic = "makeFieldsPublic";
        public static final String includesAnnotation = "includesAnnotation";
        public static final String excludesAnnotation = "excludesAnnotation";
        public static final String methodsAsFunctionsEnabled = "methodsAsFunctionsEnabled";
        public static final String constructorsAsFunctionsEnabled = "constructorsAsFunctionsEnabled";
        public static final String instanceFieldsAsEnumEnabled = "instanceFieldsAsEnumEnabled";
        public static final String instanceFieldsAsFunctionsEnabled = "instanceFieldsAsFunctionsEnabled";
        public static final String instanceFieldsAsTupleEnabled = "instanceFieldsAsTupleEnabled";
    }
    
    public Map<String, String> options()      { return processingEnv.getOptions(); }
    
    protected boolean enabledByDefault() {
        return true;
    }
    
    public boolean enabled()                          { return Boolean.parseBoolean(findOption(Options.enabled, Boolean.toString(enabledByDefault()))); }
    public Pattern includesRegex()                    { return Pattern.compile(findOption(Options.includesRegex, ".*")); }
    public Pattern excludesRegex()                    { return Pattern.compile(findOption(Options.excludesRegex, ".*_")); }
    public boolean onlyPublicMembers()                { return Boolean.parseBoolean(findOption(Options.onlyPublicMembers, "false")); }
    public boolean includePrivateMembers()            { return Boolean.parseBoolean(findOption(Options.includePrivateMembers, "false")); }
    public boolean makeFieldsPublic()                 { return Boolean.parseBoolean(findOption(Options.makeFieldsPublic, "false")); }
    public String generatedClassNamePattern()         { return findOption(Options.generatedClassNamePattern, "{}_"); }
    public String generatedPackagePattern()           { return findOption(Options.generatedPackagePattern, "{}"); }
    public String includesAnnotation()                { return findOption(Options.includesAnnotation, ""); }
    public String excludesAnnotation()                { return findOption(Options.excludesAnnotation, mkString(",", newList("javax.persistence.Entity", "javax.persistence.MappedSuperclass", "javax.persistence.Embeddable", NoMetadataGeneration.class.getName()))); }
    public Pattern extendClassNamePattern()           { return Pattern.compile("<not enabled>"); }
    public boolean methodsAsFunctionsEnabled()        { return Boolean.parseBoolean(findOption(Options.methodsAsFunctionsEnabled, "true")); }
    public boolean constructorsAsFunctionsEnabled()   { return Boolean.parseBoolean(findOption(Options.constructorsAsFunctionsEnabled, "true")); }
    public boolean instanceFieldsAsEnumEnabled()      { return Boolean.parseBoolean(findOption(Options.instanceFieldsAsEnumEnabled, "true")); }
    public boolean instanceFieldsAsFunctionsEnabled() { return Boolean.parseBoolean(findOption(Options.instanceFieldsAsFunctionsEnabled, "true")); }
    public boolean instanceFieldsAsTupleEnabled()     { return Boolean.parseBoolean(findOption(Options.instanceFieldsAsTupleEnabled, "true")); }

    public String findOption(String option, String defaultIfNotFound) {
        return find(getClass().getSimpleName() + "." + option, options()).getOrElse(defaultIfNotFound);
    }
    
    public Predicate<Element> elementsToProcess(final Messager msg) {
        return new Predicate<Element>() {
            @Override
            public boolean accept(Element candidate) {
                if (!Helpers.<Element>instanceOf(TypeElement.class).accept(candidate)) {
                    //msg.printMessage(Kind.OTHER, "Skipping due to not TypeElement: " + candidate.getSimpleName());
                    return false;
                }
                if (generatedElements.accept(candidate) && !forcedElements.accept(candidate)) {
                    //msg.printMessage(Kind.OTHER, "Skipping due to being generated non-forced element: " + candidate.getSimpleName());
                    return false;
                }
                if (!includeAllByAnnotation.accept(candidate) && !withAnnotations(includesAnnotation(), true).accept(candidate)) {
                    //msg.printMessage(Kind.OTHER, "Skipping due to not included by annotation: " + candidate.getSimpleName());
                    return false;
                }
                if (withAnnotations(excludesAnnotation(), true).accept(candidate)) {
                    //msg.printMessage(Kind.OTHER, "Skipping due to being excluded by annotation: " + candidate.getSimpleName());
                    return false;
                }
                String qname = qualifiedName.apply(candidate);
                if (!matches(includesRegex()).accept(qname)) {
                    //msg.printMessage(Kind.OTHER, "Skipping due to not maching includesRegex: " + candidate.getSimpleName());
                    return false;
                }
                if (matches(excludesRegex()).accept(qname)) {
                    //msg.printMessage(Kind.OTHER, "Skipping due to matching excludesRegex: " + candidate.getSimpleName());
                    return false;
                }
                if (simpleName.apply(candidate).equals("package-info")) {
                    //msg.printMessage(Kind.OTHER, "Skipping due to being package-info: " + candidate.getSimpleName());
                    return false;
                }
                
                return true;
            }
        };
    }

    private final Predicate<Element> includeAllByAnnotation = new Predicate<Element>() {
        public boolean accept(Element candidate) {
            return includesAnnotation().isEmpty();
        };
    };
    
    @SuppressWarnings("unchecked")
    public OPTIONS generatorOptions() {
        final boolean onlyPublicMembers = CommonMetadataProcessor.this.onlyPublicMembers();
        final boolean includePrivateMembers = CommonMetadataProcessor.this.includePrivateMembers();
        final boolean makeFieldsPublic = CommonMetadataProcessor.this.makeFieldsPublic();
        final String generatedPackagePattern = CommonMetadataProcessor.this.generatedPackagePattern();
        final String generatedClassNamePattern = CommonMetadataProcessor.this.generatedClassNamePattern();
        final boolean methodsAsFunctionsEnabled = CommonMetadataProcessor.this.methodsAsFunctionsEnabled();
        final boolean constructorsAsFunctionsEnabled = CommonMetadataProcessor.this.constructorsAsFunctionsEnabled();
        final boolean instanceFieldsAsEnumEnabled = CommonMetadataProcessor.this.instanceFieldsAsEnumEnabled();
        final boolean instanceFieldsAsFunctionsEnabled = CommonMetadataProcessor.this.instanceFieldsAsFunctionsEnabled();
        final boolean instanceFieldsAsTupleEnabled = CommonMetadataProcessor.this.instanceFieldsAsTupleEnabled();
        return (OPTIONS) new CombinedGeneratorOptions() {
            public boolean onlyPublicMembers() {
                return onlyPublicMembers;
            }
            @Override
            public boolean includePrivateMembers() {
                return includePrivateMembers;
            }
            @Override
            public boolean makeFieldsPublic() {
                return makeFieldsPublic;
            }
            public String generatedPackagePattern() {
                return generatedPackagePattern;
            }
            @Override
            public String generatedClassNamePattern() {
                return generatedClassNamePattern;
            }
            @Override
            public boolean methodsAsFunctionsEnabled() {
                return methodsAsFunctionsEnabled;
            }
            @Override
            public boolean constructorsAsFunctionsEnabled() {
                return constructorsAsFunctionsEnabled;
            }
            @Override
            public boolean instanceFieldsAsEnumEnabled() {
                return instanceFieldsAsEnumEnabled;
            }
            @Override
            public boolean instanceFieldsAsFunctionsEnabled() {
                return instanceFieldsAsFunctionsEnabled;
            }
            @Override
            public boolean instanceFieldsAsTupleEnabled() {
                return instanceFieldsAsTupleEnabled;
            }
        };
    }
    
    public List<Generator<? super OPTIONS>> generators() {
        return Arrays.<Generator<? super OPTIONS>>asList(
                       InstanceFieldsAsEnum.instance,
                       InstanceFieldsAsTuple.instance,
                       InstanceFieldsAsFunctions.instance,
                       ConstructorsAsFunctions.instance,
                       MethodsAsFunctions.instance);
    }
    
    static final Transformer<Apply<TypeElement,Iterable<String>>, Apply<TypeElement,Pair<Long,List<String>>>> timed = new Transformer<Apply<TypeElement,Iterable<String>>, Apply<TypeElement,Pair<Long,List<String>>>>() {
        @Override
        public Apply<TypeElement, Pair<Long, List<String>>> transform(final Apply<TypeElement, Iterable<String>> source) {
            return new Function1<TypeElement, Pair<Long,List<String>>>() {
                @Override
                public Pair<Long, List<String>> apply(TypeElement t) {
                    long start = System.nanoTime();
                    List<String> result = newList(map(padding, source.apply(t)));
                    return Pair.of(System.nanoTime() - start, result);
                }
            };
        }
    };
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!enabled()) {
            return false;
        }
        
        try {
            return doProcess(roundEnv);
        } catch (RuntimeException e) {
            processingEnv.getMessager().printMessage(Kind.ERROR, e.toString());
            throw e;
        }
    }
    
    public boolean doProcess(RoundEnvironment roundEnv) {
        OPTIONS options = generatorOptions();
        List<Apply<TypeElement,Iterable<String>>> generators = newMutableList();
        for (Generator<? super OPTIONS> g: generators()) {
            generators.add(g.ap(processingEnv, options));
        }
        List<Apply<TypeElement, Pair<Long, List<String>>>> generatorsWithTiming = newList(map(timed, generators));
        
        String genClassNamePat = generatedClassNamePattern();
        String genPackagePat = generatedPackagePattern();
        Pattern extendClassNamePattern = extendClassNamePattern();
        Filer filer = processingEnv.getFiler();
        Class<?> clazz = getClass();
        
        long started = System.nanoTime();
        long generation = 0;
        long nestedGeneration = 0;
        long rest = 0;
        long fileWriting = 0;
        long[] cumulativeGeneratorTimes = new long[generators.size()];
        Predicate<Element> acceptedElement = elementsToProcess(processingEnv.getMessager());
        @SuppressWarnings("unchecked")
        List<TypeElement> elementsToProcess = newList((Iterable<TypeElement>)filter(acceptedElement, roundEnv.getRootElements()));
        if (options.onlyPublicMembers()) {
            elementsToProcess = newList(filter(publicElement, elementsToProcess));
        }
        Apply<TypeElement, Pair<List<Long>, List<String>>> nestedDataProducer = Content.withNestedClasses.ap(options, genClassNamePat, Predicate.of(Function.<Element,Boolean>constant(true)), generatorsWithTiming);
        for (TypeElement element: elementsToProcess) {
            try {
                long time = System.nanoTime();
                List<Pair<Long, List<String>>> elemData = newList(sequence(element, generatorsWithTiming));
                long time2 = System.nanoTime();
                Iterable<TypeElement> nestedToProcess = element2NestedClasses.apply(element);
                if (options.onlyPublicMembers()) {
                    nestedToProcess = newList(filter(publicElement, nestedToProcess));
                } else if (!options.includePrivateMembers()) {
                    nestedToProcess = filter(not(privateElement), nestedToProcess);
                }
                List<Pair<List<Long>, List<String>>> nestedData = newList(map(nestedDataProducer, filter(Predicate.of(Function.<Element,Boolean>constant(true)), nestedToProcess)));
                long time3 = System.nanoTime();
                List<String> content = newList(map(padding, concat(flatMap(Helpers.<List<String>>right(), elemData), flatMap(Helpers.<List<String>>right(), nestedData))));
                
                String genPackage = genPackagePat.replace("{}", getPackageName(element));
                String genClassName = generatedClassName(genClassNamePat, element.getSimpleName().toString());
                String superclassName = removeGenericPart.apply(element.getSuperclass().toString());
                Option<String> extendedClassName = extendClassNamePattern.matcher(superclassName).matches() ? Some(generatedClassName(genClassNamePat, superclassName)) : Option.<String>None();
                long time4 = System.nanoTime();
                if (!content.isEmpty() || Helpers.isAbstract(element)) {
                    // always produce metaclass for abstract classes in case they are inherited
                    ClassFileWriter.writeClassFile(genPackage, genClassName, extendedClassName, content, clazz, filer, element, Option.of(element.getAnnotation(SuppressWarnings.class)), element.getAnnotation(Deprecated.class) != null);
                }
                
                generation += time2 - time;
                nestedGeneration += time3 - time2;
                rest += time4 - time3;
                fileWriting += System.nanoTime() - time4;
    
                Iterable<Long> generatorTimesForContent = map(Helpers.<Long>left(), elemData);
                Iterable<List<Long>> generatorTimesForNestedClasses = map(Helpers.<List<Long>>left(), nestedData);
                Long[] totalTimesPerGenerator = newArray(Long.class, map(Helpers.iterableSum, transpose(cons(generatorTimesForContent, generatorTimesForNestedClasses))));
                
                for (int i = 0; i < cumulativeGeneratorTimes.length; ++i) {
                    cumulativeGeneratorTimes[i] += totalTimesPerGenerator[i];
                }
                
                //processingEnv.getMessager().printMessage(Kind.OTHER, "Processed: " + element.getQualifiedName());
            } catch (RuntimeException e) {
                processingEnv.getMessager().printMessage(Kind.ERROR, "Exception while handling: " + element.getQualifiedName());
                throw e;
            }
        }
        if (!elementsToProcess.isEmpty()) {
            processingEnv.getMessager().printMessage(Kind.NOTE, getClass().getName() + " (" + version + ") processed " + elementsToProcess.size() + " elements in " + (System.nanoTime()-started)/1000/1000 + " ms (" + generation/1000/1000 + "/" + nestedGeneration/1000/1000 + "/" + rest/1000/1000 + "/" + fileWriting/1000/1000 + " ms) (" + newList(map(nanosToMillis, newArray(cumulativeGeneratorTimes))) + " ms)");
        }
        return false;
    }

    public static String generatedClassName(String genClassNamePat, String className) {
        return genClassNamePat.replace("{}", className);
    }
    
    public static abstract class CombinedGeneratorOptions implements InstanceFieldsAsFunctions.Options, MethodsAsFunctions.Options, ConstructorsAsFunctions.Options, InstanceFieldsAsEnum.Options, InstanceFieldsAsTuple.Options {
        @Override
        public boolean generateMemberNameAccessorForMethods() {
            return true;
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getClassForInstanceFields(boolean isFinal) {
            return isFinal ? MetaFieldPlain.class : MetaFieldProperty.class;
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getPredicateClassForInstanceFields(boolean isFinal) {
            return isFinal ? MetaFieldPredicate.class : MetaFieldPredicateProperty.class; 
        }
        @Override
        public List<String> getAdditionalBodyLinesForInstanceFields() {
            return emptyList();
        }
        @Override
        public boolean generateMemberAccessorForFields() {
            return true;
        }
        @Override
        public boolean generateMemberInitializerForFields() {
            return true;
        }
        @Override
        public boolean generateMemberNameAccessorForFields() {
            return true;
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getClassForMethods(int argCount) {
            switch (argCount) {
                case 0: return MetaMethods.M0.class;
                case 1: return MetaMethods.M1.class;
                case 2: return MetaMethods.M2.class;
                case 3: return MetaMethods.M3.class;
                case 4: return MetaMethods.M4.class;
                case 5: return MetaMethods.M5.class;
                case 6: return MetaMethods.M6.class;
                case 7: return MetaMethods.M7.class;
                case 8: return MetaMethods.M8.class;
                case 9: return MetaMethods.M9.class;
                case 10: return MetaMethods.M10.class;
                case 11: return MetaMethods.M11.class;
                case 12: return MetaMethods.M12.class;
                case 13: return MetaMethods.M13.class;
                case 14: return MetaMethods.M14.class;
                case 15: return MetaMethods.M15.class;
                case 16: return MetaMethods.M16.class;
                case 17: return MetaMethods.M17.class;
                case 18: return MetaMethods.M18.class;
                case 19: return MetaMethods.M19.class;
                case 20: return MetaMethods.M20.class;
                case 21: return MetaMethods.M21.class;
                case 22: return MetaMethods.M22.class;
                case 23: return MetaMethods.M23.class;
                case 24: return MetaMethods.M24.class;
                case 25: return MetaMethods.M25.class;
                case 26: return MetaMethods.M26.class;
                case 27: return MetaMethods.M27.class;
                case 28: return MetaMethods.M28.class;
                case 29: return MetaMethods.M29.class;
                case 30: return MetaMethods.M30.class;
                case 31: return MetaMethods.M31.class;
                case 32: return MetaMethods.M32.class;
                case 33: return MetaMethods.M33.class;
                case 34: return MetaMethods.M34.class;
                case 35: return MetaMethods.M35.class;
                case 36: return MetaMethods.M36.class;
                case 37: return MetaMethods.M37.class;
                case 38: return MetaMethods.M38.class;
                case 39: return MetaMethods.M39.class;
                case 40: return MetaMethods.M40.class;
                case 41: return MetaMethods.M41.class;
                case 42: return MetaMethods.M42.class;
            }
            throw new RuntimeException("Not implemented: F" + argCount);
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getPredicateClassForMethods() {
            return (Class<? extends Apply>) MetaMethodPredicate.class; 
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getClassForConstructors(int argCount) {
            switch (argCount) {
                case 0: return MetaConstructors.C0.class;
                case 1: return MetaConstructors.C1.class;
                case 2: return MetaConstructors.C2.class;
                case 3: return MetaConstructors.C3.class;
                case 4: return MetaConstructors.C4.class;
                case 5: return MetaConstructors.C5.class;
                case 6: return MetaConstructors.C6.class;
                case 7: return MetaConstructors.C7.class;
                case 8: return MetaConstructors.C8.class;
                case 9: return MetaConstructors.C9.class;
                case 10: return MetaConstructors.C10.class;
                case 11: return MetaConstructors.C11.class;
                case 12: return MetaConstructors.C12.class;
                case 13: return MetaConstructors.C13.class;
                case 14: return MetaConstructors.C14.class;
                case 15: return MetaConstructors.C15.class;
                case 16: return MetaConstructors.C16.class;
                case 17: return MetaConstructors.C17.class;
                case 18: return MetaConstructors.C18.class;
                case 19: return MetaConstructors.C19.class;
                case 20: return MetaConstructors.C20.class;
                case 21: return MetaConstructors.C21.class;
                case 22: return MetaConstructors.C22.class;
                case 23: return MetaConstructors.C23.class;
                case 24: return MetaConstructors.C24.class;
                case 25: return MetaConstructors.C25.class;
                case 26: return MetaConstructors.C26.class;
                case 27: return MetaConstructors.C27.class;
                case 28: return MetaConstructors.C28.class;
                case 29: return MetaConstructors.C29.class;
                case 30: return MetaConstructors.C30.class;
                case 31: return MetaConstructors.C31.class;
                case 32: return MetaConstructors.C32.class;
                case 33: return MetaConstructors.C33.class;
                case 34: return MetaConstructors.C34.class;
                case 35: return MetaConstructors.C35.class;
                case 36: return MetaConstructors.C36.class;
                case 37: return MetaConstructors.C37.class;
                case 38: return MetaConstructors.C38.class;
                case 39: return MetaConstructors.C39.class;
                case 40: return MetaConstructors.C40.class;
                case 41: return MetaConstructors.C41.class;
                case 42: return MetaConstructors.C42.class;
            }
            throw new RuntimeException("Not implemented: F" + argCount);
        }
        @Override
        public List<String> getAdditionalBodyLinesForConstructors(ExecutableElement element) {
            return emptyList();
        }
        @Override
        public List<String> getAdditionalBodyLinesForMethods(ExecutableElement element) {
            return emptyList();
        }
        @Override
        public boolean generateMemberInitializerForMethods() {
            return true;
        }
        @Override
        public boolean generateMemberAccessorForMethods() {
            return true;
        }
        @Override
        public boolean generateMemberInitializerForConstructors() {
            return true;
        }
        @Override
        public boolean generateMemberAccessorForConstructors() {
            return true;
        }
    }
}
