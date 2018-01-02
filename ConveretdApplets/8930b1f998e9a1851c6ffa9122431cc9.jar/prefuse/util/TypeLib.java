// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

public class TypeLib
{
    public static boolean typeCheck(final Class clazz, final Object o) {
        return clazz.isAssignableFrom(o.getClass()) || isWrapperInstance(clazz, o);
    }
    
    public static Class getSharedType(final Object o, final Object o2) {
        return getSharedType(o.getClass(), o2.getClass());
    }
    
    public static Class getSharedType(final Class clazz, final Class clazz2) {
        if (clazz == clazz2) {
            return clazz;
        }
        if (clazz.isAssignableFrom(clazz2)) {
            return clazz;
        }
        if (clazz2.isAssignableFrom(clazz)) {
            return clazz2;
        }
        return null;
    }
    
    public static boolean isWrapperInstance(final Class clazz, final Object o) {
        if (!clazz.isPrimitive()) {
            throw new IllegalArgumentException("Input type must be a primitive");
        }
        return (Integer.TYPE == clazz && o instanceof Integer) || (Long.TYPE == clazz && o instanceof Long) || (Float.TYPE == clazz && o instanceof Float) || (Double.TYPE == clazz && o instanceof Double) || (Boolean.TYPE == clazz && o instanceof Boolean) || (Short.TYPE == clazz && o instanceof Short) || (Byte.TYPE == clazz && o instanceof Byte) || (Character.TYPE == clazz && o instanceof Character);
    }
    
    public static Class getPrimitiveType(final Class clazz) {
        if (Integer.class.equals(clazz) || clazz == Integer.TYPE) {
            return Integer.TYPE;
        }
        if (Long.class.equals(clazz) || clazz == Long.TYPE) {
            return Long.TYPE;
        }
        if (Float.class.equals(clazz) || clazz == Float.TYPE) {
            return Float.TYPE;
        }
        if (Double.class.equals(clazz) || clazz == Double.TYPE) {
            return Double.TYPE;
        }
        if (Byte.class.equals(clazz) || clazz == Byte.TYPE) {
            return Byte.TYPE;
        }
        if (Short.class.equals(clazz) || clazz == Short.TYPE) {
            return Short.TYPE;
        }
        throw new IllegalArgumentException("Input class must be a numeric type");
    }
    
    public static Class getWrapperType(final Class clazz) {
        if (!clazz.isPrimitive()) {
            return clazz;
        }
        if (Integer.TYPE == clazz) {
            return Integer.class;
        }
        if (Long.TYPE == clazz) {
            return Long.class;
        }
        if (Float.TYPE == clazz) {
            return Float.class;
        }
        if (Double.TYPE == clazz) {
            return Double.class;
        }
        if (Boolean.TYPE == clazz) {
            return Boolean.class;
        }
        if (Short.TYPE == clazz) {
            return Short.class;
        }
        if (Character.TYPE == clazz) {
            return Character.class;
        }
        if (Byte.TYPE == clazz) {
            return Byte.class;
        }
        if (Short.TYPE == clazz) {
            return Short.class;
        }
        throw new IllegalArgumentException();
    }
    
    public static boolean isIntegerType(final Class clazz) {
        return clazz == Byte.TYPE || clazz == Short.TYPE || clazz == Integer.TYPE || clazz == Long.TYPE;
    }
    
    public static boolean isNumericType(final Class clazz) {
        return clazz == Byte.TYPE || clazz == Short.TYPE || clazz == Integer.TYPE || clazz == Long.TYPE || clazz == Double.TYPE || clazz == Float.TYPE;
    }
    
    public static Class getNumericType(final Class clazz, final Class clazz2) {
        if (!isNumericType(clazz) || !isNumericType(clazz2)) {
            throw new IllegalArgumentException("Input types must be primitive number types");
        }
        if (clazz == Double.TYPE || clazz2 == Double.TYPE) {
            return Double.TYPE;
        }
        if (clazz == Float.TYPE || clazz == Float.TYPE) {
            return Float.TYPE;
        }
        if (clazz == Long.TYPE || clazz2 == Long.TYPE) {
            return Long.TYPE;
        }
        return Integer.TYPE;
    }
}
