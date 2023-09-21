package fi.solita.utils.meta;

import fi.solita.utils.functional.*;
import fi.solita.utils.meta.generators.Content;

import java.lang.reflect.Method;
import java.util.Map;

public abstract class MetaMethods {
    static final Method doGetMember(Class<?> clazz, String name, Class<?>... argClasses) {
        try {
            Method method = (Method)(Object)clazz.getDeclaredMethod(name, argClasses);
            method.setAccessible(true);
            return method;
        } catch (Throwable $e) {
            throw Content.wrap($e);
        }
    }
    static final String doToString(Class<?> clazz, String name) {
        return clazz.getSimpleName() + "." + name;
    }
    
    public static abstract class M0<R> extends Function0<R> implements MetaMethod<Tuple0,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M0(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M1<T1,R> extends Function1<T1,R> implements MetaMethod<T1,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M1(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M2<T1,T2,R> extends Function2<T1,T2,R> implements MetaMethod<Map.Entry<? extends T1,? extends T2>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M2(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M3<T1,T2,T3,R> extends Function3<T1,T2,T3,R> implements MetaMethod<Tuple3<? extends T1,? extends T2,? extends T3>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M3(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M4<T1,T2,T3,T4,R> extends Function4<T1,T2,T3,T4,R> implements MetaMethod<Tuple4<? extends T1,? extends T2,? extends T3,? extends T4>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M4(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M5<T1,T2,T3,T4,T5,R> extends Function5<T1,T2,T3,T4,T5,R> implements MetaMethod<Tuple5<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M5(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M6<T1,T2,T3,T4,T5,T6,R> extends Function6<T1,T2,T3,T4,T5,T6,R> implements MetaMethod<Tuple6<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M6(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M7<T1,T2,T3,T4,T5,T6,T7,R> extends Function7<T1,T2,T3,T4,T5,T6,T7,R> implements MetaMethod<Tuple7<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M7(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M8<T1,T2,T3,T4,T5,T6,T7,T8,R> extends Function8<T1,T2,T3,T4,T5,T6,T7,T8,R> implements MetaMethod<Tuple8<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M8(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> extends Function9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> implements MetaMethod<Tuple9<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M9(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> extends Function10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> implements MetaMethod<Tuple10<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M10(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> extends Function11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> implements MetaMethod<Tuple11<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M11(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> extends Function12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> implements MetaMethod<Tuple12<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M12(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> extends Function13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> implements MetaMethod<Tuple13<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M13(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> extends Function14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> implements MetaMethod<Tuple14<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M14(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> extends Function15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> implements MetaMethod<Tuple15<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M15(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> extends Function16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> implements MetaMethod<Tuple16<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M16(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> extends Function17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> implements MetaMethod<Tuple17<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M17(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> extends Function18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> implements MetaMethod<Tuple18<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M18(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> extends Function19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> implements MetaMethod<Tuple19<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M19(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> extends Function20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> implements MetaMethod<Tuple20<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M20(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> extends Function21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> implements MetaMethod<Tuple21<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M21(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> extends Function22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> implements MetaMethod<Tuple22<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M22(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M23<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,R> extends Function23<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,R> implements MetaMethod<Tuple23<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M23(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M24<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,R> extends Function24<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,R> implements MetaMethod<Tuple24<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M24(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M25<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,R> extends Function25<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,R> implements MetaMethod<Tuple25<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M25(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class M26<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,R> extends Function26<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,R> implements MetaMethod<Tuple26<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M26(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M27<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,R> extends Function27<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,R> implements MetaMethod<Tuple27<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M27(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M28<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,R> extends Function28<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,R> implements MetaMethod<Tuple28<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M28(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M29<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,R> extends Function29<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,R> implements MetaMethod<Tuple29<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28,? extends T29>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M29(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M30<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,R> extends Function30<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,R> implements MetaMethod<Tuple30<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28,? extends T29,? extends T30>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M30(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M31<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,R> extends Function31<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,R> implements MetaMethod<Tuple31<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28,? extends T29,? extends T30,? extends T31>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M31(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M32<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,R> extends Function32<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,R> implements MetaMethod<Tuple32<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28,? extends T29,? extends T30,? extends T31,? extends T32>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M32(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M33<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,R> extends Function33<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,R> implements MetaMethod<Tuple33<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28,? extends T29,? extends T30,? extends T31,? extends T32,? extends T33>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M33(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M34<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,R> extends Function34<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,R> implements MetaMethod<Tuple34<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28,? extends T29,? extends T30,? extends T31,? extends T32,? extends T33,? extends T34>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M34(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M35<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,R> extends Function35<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,R> implements MetaMethod<Tuple35<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28,? extends T29,? extends T30,? extends T31,? extends T32,? extends T33,? extends T34,? extends T35>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M35(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    
    public static abstract class M36<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,R> extends Function36<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,R> implements MetaMethod<Tuple36<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28,? extends T29,? extends T30,? extends T31,? extends T32,? extends T33,? extends T34,? extends T35,? extends T36>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M36(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }

    public static abstract class M37<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,R> extends Function37<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,R> implements MetaMethod<Tuple37<? extends T1,? extends T2,? extends T3,? extends T4,? extends T5,? extends T6,? extends T7,? extends T8,? extends T9,? extends T10,? extends T11,? extends T12,? extends T13,? extends T14,? extends T15,? extends T16,? extends T17,? extends T18,? extends T19,? extends T20,? extends T21,? extends T22,? extends T23,? extends T24,? extends T25,? extends T26,? extends T27,? extends T28,? extends T29,? extends T30,? extends T31,? extends T32,? extends T33,? extends T34,? extends T35,? extends T36,? extends T37>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M37(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
}
