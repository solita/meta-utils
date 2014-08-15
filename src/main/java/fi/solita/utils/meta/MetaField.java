package fi.solita.utils.meta;

import java.lang.reflect.Field;

public interface MetaField<T,R> extends MetaNamedMember<T,R> {
    @Override
    public Field getMember();
}
