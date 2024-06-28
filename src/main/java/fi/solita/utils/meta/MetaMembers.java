package fi.solita.utils.meta;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.find;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.FunctionalA.filter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Option;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.lens.Builder;

public abstract class MetaMembers {
    
    public static final <T> Collection<MetaNamedMember<T,?>> forClass(Class<T> clazz) {
        return forClass(clazz, "{}_");
    }
    
    public static final <T> Collection<MetaNamedMember<T,?>> forClass(Class<T> clazz, String metaClassNamePattern) {
        Class<?> metaClass;
        try {
            metaClass = Class.forName(CommonMetadataProcessor.generatedClassName(metaClassNamePattern, clazz.getName().toString()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        return newList(map(new Apply<Field,MetaNamedMember<T,?>>() {
            @SuppressWarnings("unchecked")
            @Override
            public MetaNamedMember<T,?> apply(Field t) {
                try {
                    t.setAccessible(true);
                    return (MetaNamedMember<T,?>)t.get(null);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }, filter(new Predicate<Field>() {
            @Override
            public boolean accept(Field candidate) {
                return Modifier.isStatic(candidate.getModifiers()) && MetaMember.class.isAssignableFrom(candidate.getType());
            }
        }, metaClass.getDeclaredFields())));
    }
    
    public static final <T> Collection<MetaNamedMember<T,?>> forBean(Class<T> beanClazz) {
        return resolveBeanProperties(forClass(beanClazz), beanClazz);
    }
    
    public static final <T> Collection<MetaNamedMember<T,?>> forBean(Class<T> beanClazz, String metaClassNamePattern) {
        return resolveBeanProperties(forClass(beanClazz, metaClassNamePattern), beanClazz);
    }
    
    public static final <T,M extends MetaMember<T,?>> Option<M> resolveBeanProperty(Collection<M> candidates, final PropertyDescriptor t) {
        return find(new Predicate<MetaMember<T,?>>() {
            @Override
            public boolean accept(MetaMember<T, ?> candidate) {
                return candidate.getMember().equals(t.getReadMethod());
            }
        }, candidates);
    }

    public static final <T,M extends MetaMember<T,?>> List<M> resolveBeanProperties(final Collection<M> candidates, Class<T> beanClass) {
        return newList(flatMap(new Apply<PropertyDescriptor,Option<M>>() {
            @Override
            public Option<M> apply(PropertyDescriptor t) {
                return MetaMembers.resolveBeanProperty(candidates, t);
            }
        }, Builder.readableWritableBeanDescriptors(beanClass)));
    }
}
