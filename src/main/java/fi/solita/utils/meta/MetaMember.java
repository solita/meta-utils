package fi.solita.utils.meta;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;

import fi.solita.utils.functional.Apply;

public interface MetaMember<T, R> extends Apply<T, R>, Serializable {
    AccessibleObject getMember();
}
