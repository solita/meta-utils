package fi.solita.utils.meta.generators;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.FunctionalA.concat;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.append;
import static fi.solita.utils.meta.Helpers.element2Fields;
import static fi.solita.utils.meta.Helpers.padding;
import static fi.solita.utils.meta.Helpers.privateElement;
import static fi.solita.utils.meta.Helpers.publicElement;
import static fi.solita.utils.meta.Helpers.simpleName;
import static fi.solita.utils.meta.Helpers.staticElements;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import fi.solita.utils.meta.ForceMetadataGeneration;

public class InstanceFieldsAsEnum extends Generator<InstanceFieldsAsEnum.Options>{
    
    public static final InstanceFieldsAsEnum instance = new InstanceFieldsAsEnum();
    
    public static interface Options extends GeneratorOptions {
        boolean onlyPublicMembers();
        boolean includePrivateMembers();
        boolean instanceFieldsAsEnumEnabled();
    }
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
        if (!options.instanceFieldsAsEnumEnabled() && (source.getAnnotation(ForceMetadataGeneration.class) == null || !source.getAnnotation(ForceMetadataGeneration.class).instanceFieldsAsEnum())) {
            return emptyList();
        }
        
        Iterable<Element> elements = element2Fields.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(publicElement, elements);
        } else if (!options.includePrivateMembers()) {
            elements = filter(not(privateElement), elements);
        }
      
        List<Element> fieldsToInclude = newList(filter(not(staticElements), elements));
        if (fieldsToInclude.isEmpty()) {
            return emptyList();
        }
        return concat(
            Some("public enum $FieldNames {"),
            map(simpleName.andThen(padding).andThen(append(",")), fieldsToInclude),
            Some("}"),
            Some("")
        );
    }
}