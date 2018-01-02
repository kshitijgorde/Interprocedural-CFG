// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.threading;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

final class ClassUtilities
{
    private static final Map objectToPrimitiveMap;
    private static final Map primitiveWideningsMap;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    
    static Class classForNameOrPrimitive(final String s, final ClassLoader classLoader) throws ClassNotFoundException {
        if (s == null || s.equals("") || s.equals("null") || s.equals("void")) {
            return Void.TYPE;
        }
        if (s.equals("boolean")) {
            return Boolean.TYPE;
        }
        if (s.equals("byte")) {
            return Byte.TYPE;
        }
        if (s.equals("char")) {
            return Character.TYPE;
        }
        if (s.equals("double")) {
            return Double.TYPE;
        }
        if (s.equals("float")) {
            return Float.TYPE;
        }
        if (s.equals("int")) {
            return Integer.TYPE;
        }
        if (s.equals("long")) {
            return Long.TYPE;
        }
        if (s.equals("short")) {
            return Short.TYPE;
        }
        return Class.forName(s, false, classLoader);
    }
    
    static boolean classIsAccessible(final Class clazz) {
        return Modifier.isPublic(clazz.getModifiers());
    }
    
    static boolean compatibleClasses(final Class[] array, final Class[] array2) {
        if (array.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array2[i] == null || array2[i].equals(Void.TYPE)) {
                if (array[i].isPrimitive()) {
                    return false;
                }
            }
            else if (!array[i].isAssignableFrom(array2[i]) && !primitiveIsAssignableFrom(primitiveEquivalentOf(array[i]), primitiveEquivalentOf(array2[i]))) {
                return false;
            }
        }
        return true;
    }
    
    static Method getAccessibleMethodFrom(final Class clazz, final String s, final Class[] array) {
        final Class superclass = clazz.getSuperclass();
        Method method = null;
        if (superclass != null && classIsAccessible(superclass)) {
            try {
                method = superclass.getMethod(s, (Class[])array);
            }
            catch (NoSuchMethodException ex) {}
            if (method != null) {
                return method;
            }
        }
        final Class[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            Method method2 = null;
            if (classIsAccessible(interfaces[i])) {
                try {
                    method2 = interfaces[i].getMethod(s, (Class[])array);
                }
                catch (NoSuchMethodException ex2) {}
                if (method2 != null) {
                    return method2;
                }
            }
        }
        if (superclass != null) {
            final Method accessibleMethod = getAccessibleMethodFrom(superclass, s, array);
            if (accessibleMethod != null) {
                return accessibleMethod;
            }
        }
        for (int j = 0; j < interfaces.length; ++j) {
            final Method accessibleMethod2 = getAccessibleMethodFrom(interfaces[j], s, array);
            if (accessibleMethod2 != null) {
                return accessibleMethod2;
            }
        }
        return null;
    }
    
    static Class primitiveEquivalentOf(final Class clazz) {
        return clazz.isPrimitive() ? clazz : ClassUtilities.objectToPrimitiveMap.get(clazz);
    }
    
    static boolean primitiveIsAssignableFrom(final Class clazz, final Class clazz2) {
        if (clazz == null || clazz2 == null) {
            return false;
        }
        if (!clazz.isPrimitive() || !clazz2.isPrimitive()) {
            return false;
        }
        if (clazz.equals(clazz2)) {
            return true;
        }
        final Set set = ClassUtilities.primitiveWideningsMap.get(clazz2);
        return set != null && set.contains(clazz);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        (objectToPrimitiveMap = new HashMap(13)).put((ClassUtilities.class$java$lang$Boolean == null) ? (ClassUtilities.class$java$lang$Boolean = class$("java.lang.Boolean")) : ClassUtilities.class$java$lang$Boolean, Boolean.TYPE);
        ClassUtilities.objectToPrimitiveMap.put((ClassUtilities.class$java$lang$Byte == null) ? (ClassUtilities.class$java$lang$Byte = class$("java.lang.Byte")) : ClassUtilities.class$java$lang$Byte, Byte.TYPE);
        ClassUtilities.objectToPrimitiveMap.put((ClassUtilities.class$java$lang$Character == null) ? (ClassUtilities.class$java$lang$Character = class$("java.lang.Character")) : ClassUtilities.class$java$lang$Character, Character.TYPE);
        ClassUtilities.objectToPrimitiveMap.put((ClassUtilities.class$java$lang$Double == null) ? (ClassUtilities.class$java$lang$Double = class$("java.lang.Double")) : ClassUtilities.class$java$lang$Double, Double.TYPE);
        ClassUtilities.objectToPrimitiveMap.put((ClassUtilities.class$java$lang$Float == null) ? (ClassUtilities.class$java$lang$Float = class$("java.lang.Float")) : ClassUtilities.class$java$lang$Float, Float.TYPE);
        ClassUtilities.objectToPrimitiveMap.put((ClassUtilities.class$java$lang$Integer == null) ? (ClassUtilities.class$java$lang$Integer = class$("java.lang.Integer")) : ClassUtilities.class$java$lang$Integer, Integer.TYPE);
        ClassUtilities.objectToPrimitiveMap.put((ClassUtilities.class$java$lang$Long == null) ? (ClassUtilities.class$java$lang$Long = class$("java.lang.Long")) : ClassUtilities.class$java$lang$Long, Long.TYPE);
        ClassUtilities.objectToPrimitiveMap.put((ClassUtilities.class$java$lang$Short == null) ? (ClassUtilities.class$java$lang$Short = class$("java.lang.Short")) : ClassUtilities.class$java$lang$Short, Short.TYPE);
        primitiveWideningsMap = new HashMap(11);
        final HashSet<Class<Short>> set = new HashSet<Class<Short>>();
        set.add(Short.TYPE);
        set.add((Class<Short>)Integer.TYPE);
        set.add((Class<Short>)Long.TYPE);
        set.add((Class<Short>)Float.TYPE);
        set.add((Class<Short>)Double.TYPE);
        ClassUtilities.primitiveWideningsMap.put(Byte.TYPE, set);
        final HashSet<Class<Integer>> set2 = new HashSet<Class<Integer>>();
        set2.add(Integer.TYPE);
        set2.add((Class<Integer>)Long.TYPE);
        set2.add((Class<Integer>)Float.TYPE);
        set2.add((Class<Integer>)Double.TYPE);
        ClassUtilities.primitiveWideningsMap.put(Short.TYPE, set2);
        ClassUtilities.primitiveWideningsMap.put(Character.TYPE, set2);
        final HashSet<Class<Long>> set3 = new HashSet<Class<Long>>();
        set3.add(Long.TYPE);
        set3.add((Class<Long>)Float.TYPE);
        set3.add((Class<Long>)Double.TYPE);
        ClassUtilities.primitiveWideningsMap.put(Integer.TYPE, set3);
        final HashSet<Class<Float>> set4 = new HashSet<Class<Float>>();
        set4.add(Float.TYPE);
        set4.add((Class<Float>)Double.TYPE);
        ClassUtilities.primitiveWideningsMap.put(Long.TYPE, set4);
        final HashSet<Class<Double>> set5 = new HashSet<Class<Double>>();
        set5.add(Double.TYPE);
        ClassUtilities.primitiveWideningsMap.put(Float.TYPE, set5);
    }
}
