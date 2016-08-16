package fi.solita.utils.meta;

import java.lang.reflect.Field;

import fi.solita.utils.meta.generators.Content;
import fi.solita.utils.functional.Predicate;

public abstract class MetaFieldPredicate<T> extends Predicate<T> implements MetaField<T, Boolean> {
    private transient Field $r;
    private final Class<?> clazz;
    private final String name;
    
    public MetaFieldPredicate(Class<?> clazz, String name) {
        this.clazz = clazz;
        this.name = name;
    }
    
    @Override
    public boolean accept(T candidate) {
        return apply(candidate);
    }
    
    @Override
    public final Field getMember() {
        if ($r == null) {
            try {
                Field f = (Field)(Object)clazz.getDeclaredField(name);
                f.setAccessible(true);
                $r = f;
            } catch (Throwable $e) {
                throw Content.wrap($e);
            }
        }
        return $r;
    }
    
    public final String getName() {
        return name;
    }
    
    @Override
    public final String toString() {
        return clazz.getSimpleName() + "." + name;
    }
}