package fi.solita.utils.meta;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("*")
@SupportedOptions({"CommonMetadataProcessor3." + CommonMetadataProcessor.Options.enabled,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.generatedClassNamePattern,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.generatedPackagePattern,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.includesRegex,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.excludesRegex,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.onlyPublicMembers,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.includePrivateMembers,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.makeFieldsPublic,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.includesAnnotation,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.excludesAnnotation,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.methodsAsFunctionsEnabled,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.constructorsAsFunctionsEnabled})
public class CommonMetadataProcessor3 extends CommonMetadataProcessor<CommonMetadataProcessor.CombinedGeneratorOptions> {
    @Override
    protected boolean enabledByDefault() {
        return false;
    }
}
