package fi.solita.utils.meta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import javax.lang.model.element.Element;

/** https://bugs.eclipse.org/bugs/show_bug.cgi?id=300408 */
public class Workaround {

    private static Class<?> sourceTypeBinding;
    static {
        try {
            sourceTypeBinding = Class.forName("org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding");
        } catch (ClassNotFoundException e) {
            // ignore
        }
    }
    
    public static List<? extends Element> getEnclosedElementsDeclarationOrder(Element type) {
        try {
            Object binding;
            try {
                binding = field(type, "_binding");
            } catch (NoSuchFieldException e) {
                // not eclipse, skip workaround
                return type.getEnclosedElements();
            }

            final List<Object> declarationOrder;
            if (sourceTypeBinding.isAssignableFrom(binding.getClass())) {
                declarationOrder = findSourceOrder(binding);
            } else {
                throw new RuntimeException(sourceTypeBinding.getClass() + " not assignable from " + binding.getClass());
            }

            List<Element> enclosedElements = new ArrayList<Element>(type.getEnclosedElements());
            Collections.sort(enclosedElements, new Comparator<Element>() {

                public int compare(Element o1, Element o2) {
                    try {
                        Object o1Binding = field(o1, "_binding");
                        Object o2Binding = field(o2, "_binding");

                        int i1 = declarationOrder.indexOf(o1Binding);
                        int i2 = declarationOrder.indexOf(o2Binding);

                        return i1 - i2;
                    } catch (Exception e) {
                        return 0;
                    }
                }

            });
            return enclosedElements;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Object> findSourceOrder(Object binding) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        Object referenceContext = field(field(binding, "scope"), "referenceContext");

        TreeMap<Integer, Object> orderedBindings = new TreeMap<Integer,Object>();

        collectSourceOrder(orderedBindings, referenceContext, "methods");
        collectSourceOrder(orderedBindings, referenceContext, "fields");
        collectSourceOrder(orderedBindings, referenceContext, "memberTypes");

        return new ArrayList<Object>(orderedBindings.values());
    }

    private static void collectSourceOrder(TreeMap<Integer, Object> orderedBindings, Object referenceContext, String fieldName) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        Object[] declarations = (Object[]) field(referenceContext, fieldName);
        if (declarations != null) {
            for (int i = 0; i < declarations.length; i++) {
                Integer declarationSourceStart = (Integer) field(declarations[i], "declarationSourceStart");
                orderedBindings.put(declarationSourceStart, field(declarations[i], "binding"));
            }
        }
    }

    private static Object field(Object o, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (o == null) {
            return null;
        }
        Field field = o.getClass().getField(fieldName);
        field.setAccessible(true);
        return field.get(o);
    }

}
