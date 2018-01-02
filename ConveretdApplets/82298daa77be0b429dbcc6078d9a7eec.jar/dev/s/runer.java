// 
// Decompiled by Procyon v0.5.30
// 

package dev.s;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class runer
{
    public static void main(final String[] args) {
    }
    
    public static Object function(final Object obj, final String name) {
        final Class cls = obj.getClass();
        try {
            final Method method = cls.getMethod(name, (Class[])null);
            return method.invoke(obj, (Object[])null);
        }
        catch (NoSuchMethodException ex2) {}
        catch (IllegalAccessException ex3) {}
        catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
    
    public static boolean check(final Class[] cls, final Object[] val) {
        if (cls.length != val.length) {
            return false;
        }
        for (int i = 0; i < cls.length; ++i) {
            if (!val[i].getClass().getName().toUpperCase().contains(cls[i].getName().toUpperCase())) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean checkMethod(final Method m, final Object[] val) {
        final Class[] cls = m.getParameterTypes();
        return check(cls, val);
    }
    
    public static Object function(final Object obj, final String name, final Object[] val) {
        if (val.length == 0) {
            function(obj, name);
        }
        final Class cls = obj.getClass();
        try {
            final Method[] methods = cls.getMethods();
            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().equals(name) && checkMethod(methods[i], val)) {
                    return methods[i].invoke(obj, val);
                }
            }
        }
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
    
    public static Object constructor(final String ClassName, final Object[] obj, final Class[] list) {
        try {
            final Class cls = Class.forName(ClassName);
            if (check(list, obj)) {
                return cls.getConstructor((Class<?>[])list).newInstance(obj);
            }
        }
        catch (ClassNotFoundException ex2) {
            System.out.println("ClassNotFoundException");
            return null;
        }
        catch (InstantiationException ex3) {
            System.out.println("InstantiationException");
        }
        catch (NoSuchMethodException ex4) {
            System.out.println("NoSuchMethodException");
        }
        catch (IllegalAccessException ex5) {
            System.out.println("IllegalAccessException");
        }
        catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
