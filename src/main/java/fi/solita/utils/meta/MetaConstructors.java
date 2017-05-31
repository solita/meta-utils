package fi.solita.utils.meta;

import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.FunctionalA.map;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import fi.solita.utils.functional.Function0;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function10;
import fi.solita.utils.functional.Function11;
import fi.solita.utils.functional.Function12;
import fi.solita.utils.functional.Function13;
import fi.solita.utils.functional.Function14;
import fi.solita.utils.functional.Function15;
import fi.solita.utils.functional.Function16;
import fi.solita.utils.functional.Function17;
import fi.solita.utils.functional.Function18;
import fi.solita.utils.functional.Function19;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Function20;
import fi.solita.utils.functional.Function21;
import fi.solita.utils.functional.Function22;
import fi.solita.utils.functional.Function23;
import fi.solita.utils.functional.Function24;
import fi.solita.utils.functional.Function25;
import fi.solita.utils.functional.Function26;
import fi.solita.utils.functional.Function27;
import fi.solita.utils.functional.Function3;
import fi.solita.utils.functional.Function4;
import fi.solita.utils.functional.Function5;
import fi.solita.utils.functional.Function6;
import fi.solita.utils.functional.Function7;
import fi.solita.utils.functional.Function8;
import fi.solita.utils.functional.Function9;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple0;
import fi.solita.utils.functional.Tuple10;
import fi.solita.utils.functional.Tuple11;
import fi.solita.utils.functional.Tuple12;
import fi.solita.utils.functional.Tuple13;
import fi.solita.utils.functional.Tuple14;
import fi.solita.utils.functional.Tuple15;
import fi.solita.utils.functional.Tuple16;
import fi.solita.utils.functional.Tuple17;
import fi.solita.utils.functional.Tuple18;
import fi.solita.utils.functional.Tuple19;
import fi.solita.utils.functional.Tuple20;
import fi.solita.utils.functional.Tuple21;
import fi.solita.utils.functional.Tuple22;
import fi.solita.utils.functional.Tuple23;
import fi.solita.utils.functional.Tuple24;
import fi.solita.utils.functional.Tuple25;
import fi.solita.utils.functional.Tuple26;
import fi.solita.utils.functional.Tuple27;
import fi.solita.utils.functional.Tuple3;
import fi.solita.utils.functional.Tuple4;
import fi.solita.utils.functional.Tuple5;
import fi.solita.utils.functional.Tuple6;
import fi.solita.utils.functional.Tuple7;
import fi.solita.utils.functional.Tuple8;
import fi.solita.utils.functional.Tuple9;
import fi.solita.utils.meta.generators.Content;

public abstract class MetaConstructors {
    static final <R> Constructor<R> doGetMember(Class<?> clazz, Class<?>... argClasses) {
        try {
            @SuppressWarnings("unchecked")
            Constructor<R> c = (Constructor<R>)(Object)clazz.getDeclaredConstructor(argClasses);
            c.setAccessible(true);
            return c;
        } catch (Throwable $e) {
            throw Content.wrap($e);
        }
    }
    static final Transformer<Class<?>,String> className = new Transformer<Class<?>,String>() {
        public String transform(Class<?> source) {
            return source.getName();
        };
    };
    static final String doToString(Class<?> clazz, Class<?>[] argClasses) {
        return clazz.getSimpleName() + "(" + mkString(", ", map(className, argClasses)) + ")";
    }
    
    public static abstract class C0<R> extends Function0<R> implements MetaConstructor<Tuple0,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C0(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C1<T1,R> extends Function1<T1,R> implements MetaConstructor<T1,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C1(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C2<T1,T2,R> extends Function2<T1,T2,R> implements MetaConstructor<Map.Entry<? extends T1,? extends T2>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C2(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C3<T1,T2,T3,R> extends Function3<T1,T2,T3,R> implements MetaConstructor<Tuple3<? extends T1,? extends T2,? extends T3>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C3(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C4<T1,T2,T3,T4,R> extends Function4<T1,T2,T3,T4,R> implements MetaConstructor<Tuple4<? extends T1,? extends T2,? extends T3,? extends T4>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C4(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C5<T1,T2,T3,T4,T5,R> extends Function5<T1,T2,T3,T4,T5,R> implements MetaConstructor<Tuple5<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C5(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C6<T1,T2,T3,T4,T5,T6,R> extends Function6<T1,T2,T3,T4,T5,T6,R> implements MetaConstructor<Tuple6<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C6(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C7<T1,T2,T3,T4,T5,T6,T7,R> extends Function7<T1,T2,T3,T4,T5,T6,T7,R> implements MetaConstructor<Tuple7<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C7(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C8<T1,T2,T3,T4,T5,T6,T7,T8,R> extends Function8<T1,T2,T3,T4,T5,T6,T7,T8,R> implements MetaConstructor<Tuple8<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C8(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> extends Function9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> implements MetaConstructor<Tuple9<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C9(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> extends Function10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> implements MetaConstructor<Tuple10<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C10(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> extends Function11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> implements MetaConstructor<Tuple11<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C11(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> extends Function12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> implements MetaConstructor<Tuple12<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C12(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> extends Function13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> implements MetaConstructor<Tuple13<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C13(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> extends Function14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> implements MetaConstructor<Tuple14<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C14(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> extends Function15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> implements MetaConstructor<Tuple15<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C15(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> extends Function16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> implements MetaConstructor<Tuple16<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C16(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> extends Function17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> implements MetaConstructor<Tuple17<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C17(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> extends Function18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> implements MetaConstructor<Tuple18<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C18(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> extends Function19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> implements MetaConstructor<Tuple19<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C19(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> extends Function20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> implements MetaConstructor<Tuple20<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C20(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> extends Function21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> implements MetaConstructor<Tuple21<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C21(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> extends Function22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> implements MetaConstructor<Tuple22<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C22(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C23<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,R> extends Function23<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,R> implements MetaConstructor<Tuple23<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C23(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C24<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,R> extends Function24<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,R> implements MetaConstructor<Tuple24<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C24(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    public static abstract class C25<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,R> extends Function25<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,R> implements MetaConstructor<Tuple25<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C25(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    
    public static abstract class C26<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,R> extends Function26<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,R> implements MetaConstructor<Tuple26<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C26(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
    
    public static abstract class C27<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,R> extends Function27<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,R> implements MetaConstructor<Tuple27<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27>,R> {
        private transient Constructor<R> $r;
        private final Class<?> clazz;
        private final Class<?>[] argClasses;
        public C27(Class<?> clazz, Class<?>... argClasses) {
            this.clazz = clazz;
            this.argClasses = argClasses;
        }
        @Override
        public List<Class<?>> getConstructorParameterTypes() {
            return Collections.unmodifiableList(Arrays.asList(argClasses));
        }
        @Override
        public Constructor<R> getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, argClasses);
            }
            return $r;
        }
        @Override
        public String toString() {
            return doToString(clazz, argClasses);
        }
    }
}