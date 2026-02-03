package fi.solita.utils.meta;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

@SupportedAnnotationTypes("*")
@SupportedOptions({"CommonMetadataProcessor2." + CommonMetadataProcessor.Options.enabled,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.generatedClassNamePattern,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.generatedPackagePattern,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.includesRegex,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.excludesRegex,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.onlyPublicMembers,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.includePrivateMembers,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.makeFieldsPublic,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.includesAnnotation,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.excludesAnnotation,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.methodsAsFunctionsEnabled,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.constructorsAsFunctionsEnabled})
public class CommonMetadataProcessor2 extends CommonMetadataProcessor<CommonMetadataProcessor.CombinedGeneratorOptions> {
    @Override
    protected boolean enabledByDefault() {
        return false;
    }
}
