// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.C;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import javax.swing.SwingUtilities;
import java.lang.reflect.Method;

public class B
{
    public static void A(final Exception ex) {
        if (ex instanceof RuntimeException) {
            throw (RuntimeException)ex;
        }
        throw new RuntimeException(ex);
    }
    
    public static Thread A(final Object o, final String s) {
        return B(o, s, null, null);
    }
    
    public static Thread B(final Object o, final String s, final Class[] array, final Object[] array2) {
        Thread thread = null;
        try {
            final Method method = o.getClass().getMethod(s, (Class<?>[])array);
            thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        method.invoke(o, array2);
                    }
                    catch (Exception ex) {
                        B.A(ex);
                    }
                }
            });
            thread.setName(o.getClass().getName() + "." + method.getName() + "()");
            thread.start();
        }
        catch (Exception ex) {
            A(ex);
        }
        return thread;
    }
    
    public static void A(final Object o, final String s, final Class[] array, final Object[] array2) {
        try {
            o.getClass().getMethod(s, (Class<?>[])array).invoke(o, array2);
        }
        catch (Exception ex) {
            A(ex);
        }
    }
    
    public static boolean A() {
        return SwingUtilities.isEventDispatchThread();
    }
    
    public static void C(final Object o, final String s) {
        C(o, s, null, null);
    }
    
    public static void A(final Object o) {
        C(o, "updateView", null, null);
    }
    
    public static void C(final Object o, final String s, final Class[] array, final Object[] array2) {
        try {
            final Method method = o.getClass().getMethod(s, (Class<?>[])array);
            if (!SwingUtilities.isEventDispatchThread()) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            method.invoke(o, array2);
                        }
                        catch (Exception ex) {
                            B.A(ex);
                        }
                    }
                });
            }
            else {
                method.invoke(o, array2);
            }
        }
        catch (Exception ex) {
            A(ex);
        }
    }
    
    public static Method A(final Class clazz, final String s, final int n) {
        return A(clazz, s, n, null);
    }
    
    public static Method A(final Class clazz, final String s, final int n, final Class[] array) {
        Method method = null;
        final Method[] methods = clazz.getMethods();
        for (int n2 = 0; method == null && n2 < methods.length; ++n2) {
            final Method method2 = methods[n2];
            if (method2.getName().equalsIgnoreCase(s)) {
                final Class<?>[] parameterTypes = method2.getParameterTypes();
                if (parameterTypes.length == n) {
                    if (array != null) {
                        boolean assignable = true;
                        for (int n3 = 0; assignable && n3 < n; ++n3) {
                            if (array[n3] != null) {
                                assignable = parameterTypes[n3].isAssignableFrom(array[n3]);
                            }
                        }
                        if (assignable) {
                            method = method2;
                        }
                    }
                    else {
                        method = method2;
                    }
                }
            }
        }
        return method;
    }
    
    public static Method A(final Class clazz, final String s, final Class[] array, final Integer n, final Boolean b) {
        Method method = null;
        final Method[] methods = clazz.getMethods();
        for (int n2 = 0; method == null && n2 < methods.length; ++n2) {
            final Method method2 = methods[n2];
            final int modifiers = method2.getModifiers();
            final Class<?>[] parameterTypes = method2.getParameterTypes();
            boolean b2 = true;
            if (b2 && s != null) {
                b2 = method2.getName().equalsIgnoreCase(s);
            }
            if (b2 && n != null) {
                b2 = (parameterTypes.length == n);
            }
            if (b2 && b != null) {
                b2 = !(Modifier.isStatic(modifiers) ^ b);
            }
            if (b2 && array != null) {
                b2 = (array.length == parameterTypes.length);
                if (b2) {
                    for (int n3 = 0; b2 && n3 < parameterTypes.length; ++n3) {
                        if (array[n3] != null) {
                            b2 = parameterTypes[n3].isAssignableFrom(array[n3]);
                        }
                    }
                }
            }
            if (b2) {
                method = method2;
            }
        }
        return method;
    }
    
    public static Object B(Object value, final String s) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Object invoke = null;
        final String string = "get" + s.substring(0, 1).toUpperCase() + s.substring(1);
        final Class<?> class1 = value.getClass();
        final Method method = class1.getMethod(string, (Class[])null);
        if (method != null) {
            invoke = method.invoke(value, (Object[])null);
        }
        else {
            value = class1.getField(s).get(value);
        }
        return invoke;
    }
    
    public static void A(final boolean b) {
        if (!b) {
            throw new RuntimeException("Assertion failed");
        }
    }
}
