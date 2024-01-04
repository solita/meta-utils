package fi.solita.utils.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Force specified metadata generation even if it is configured disabled globally
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ForceMetadataGeneration {
    boolean constructorsAsFunctions() default false;
    boolean methodsAsFunctions() default false;
}
