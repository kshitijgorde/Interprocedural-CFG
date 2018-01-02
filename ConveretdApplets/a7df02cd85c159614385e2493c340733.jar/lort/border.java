// 
// Decompiled by Procyon v0.5.30
// 

package lort;

import java.lang.reflect.Method;

public class border
{
    public static Object kubert(final Object o, final String s) {
        final Class<?> class1 = o.getClass();
        try {
            return class1.getMethod(s, (Class<?>[])null).invoke(o, (Object[])null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Object kubert(final Object o, final String s, final Object[] array) {
        final Class<?> class1 = o.getClass();
        if (array.length == 0) {
            kubert(o, s);
        }
        try {
            final Method[] methods = class1.getMethods();
            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().equals(s) && rupert(methods[i], array)) {
                    return methods[i].invoke(o, array);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private static boolean rupert(final Method method, final Object[] array) {
        return pivo(method.getParameterTypes(), array);
    }
    
    public static boolean pivo(final Class[] array, final Object[] array2) {
        if (array.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (!array2[i].getClass().getName().toUpperCase().contains(array[i].getName().toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}
