// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class m
{
    public static boolean a;
    Method[] b;
    Object c;
    
    public m() {
        this.b = null;
        this.c = null;
    }
    
    public final void a(final boolean a) {
        m.a = a;
    }
    
    public m(final Object c) {
        this.b = null;
        this.c = null;
        this.c = c;
        this.b = c.getClass().getMethods();
    }
    
    public void a(final Class clazz) {
        this.b = clazz.getMethods();
    }
    
    public void a(final String s) throws Exception {
        this.a(Class.forName(s));
    }
    
    public final void a() {
        System.out.println(String.valueOf(String.valueOf(new StringBuffer("Methods for ").append(this.c).append("..."))));
        if (this.b != null) {
            for (int i = 0; i < this.b.length; ++i) {
                System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(i))).append(": ").append(this.b[i]))));
            }
        }
    }
    
    public static final Method a(final Class clazz, final String s, final Class[] array) {
        try {
            return clazz.getMethod(s, (Class[])array);
        }
        catch (Exception ex) {
            if (m.a) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public final Method b(final String s) {
        for (int i = 0; i < this.b.length; ++i) {
            if (this.b[i].getName().equals(s)) {
                return this.b[i];
            }
        }
        return null;
    }
    
    public final Method a(final String s, final String[] array) {
        try {
            Class[] array2 = null;
            if (array != null) {
                array2 = new Class[array.length];
                for (int i = 0; i < array.length; ++i) {
                    array2[i] = Class.forName(array[i]);
                }
            }
            return this.a(s, array2);
        }
        catch (Exception ex) {
            if (m.a) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public final Method a(final String s, final Class[] array) {
        boolean b = array == null;
        if (!b) {
            b = (array.length == 0);
        }
        for (int i = 0; i < this.b.length; ++i) {
            if (this.b[i].getName().equals(s)) {
                final Class<?>[] parameterTypes = this.b[i].getParameterTypes();
                if (b) {
                    if (parameterTypes == null) {
                        return this.b[i];
                    }
                    if (parameterTypes.length == 0) {
                        return this.b[i];
                    }
                }
                else if (array.length == parameterTypes.length) {
                    int n = 1;
                    for (int n2 = 0; n2 < array.length && n != 0; ++n2) {
                        if (!array[n2].getName().equals(parameterTypes[n2].getName())) {
                            n = 0;
                        }
                    }
                    if (n != 0) {
                        return this.b[i];
                    }
                }
            }
        }
        return null;
    }
    
    public final Method a(final String s, final boolean b) throws Exception {
        for (int i = 0; i < this.b.length; ++i) {
            if (this.b[i].getName().equals(s)) {
                return this.b[i];
            }
        }
        throw new Exception(String.valueOf(String.valueOf(new StringBuffer("Method ").append(s).append(" not found! in object: ").append(this.c))));
    }
    
    public final Object c(final String s) throws Exception {
        return this.a(this.a(s, true));
    }
    
    public final Object a(final Method method) {
        try {
            return method.invoke(this.c, new Object[0]);
        }
        catch (Exception ex) {
            if (m.a) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public final Object a(final String s, final Object o) throws Exception {
        return this.a(this.a(s, true), o);
    }
    
    public final Object a(final Method method, final Object o) {
        try {
            return method.invoke(this.c, o);
        }
        catch (Exception ex) {
            if (m.a) {
                ex.printStackTrace();
            }
            d.a(ex);
            return null;
        }
    }
    
    public final Object a(final String s, final Object o, final Object o2) throws Exception {
        return this.a(this.a(s, true), o, o2);
    }
    
    public final Object a(final Method method, final Object o, final Object o2) {
        try {
            return method.invoke(this.c, o, o2);
        }
        catch (Exception ex) {
            if (m.a) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public final Object a(final String s, final Object o, final Object o2, final Object o3) throws Exception {
        return this.a(this.a(s, true), o, o2, o3);
    }
    
    public final Object a(final Method method, final Object o, final Object o2, final Object o3) {
        try {
            return method.invoke(this.c, o, o2, o3);
        }
        catch (Exception ex) {
            if (m.a) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public final Object a(final String s, final Object o, final Object o2, final Object o3, final Object o4) throws Exception {
        return this.a(this.a(s, true), o, o2, o3, o4);
    }
    
    public final Object a(final Method method, final Object o, final Object o2, final Object o3, final Object o4) {
        try {
            return method.invoke(this.c, o, o2, o3, o4);
        }
        catch (Exception ex) {
            if (m.a) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public final Object a(final String s, final Object o, final Object o2, final Object o3, final Object o4, final Object o5) throws Exception {
        return this.a(this.a(s, true), o, o2, o3, o4, o5);
    }
    
    public final Object a(final String s, final Object[] array) throws Exception {
        return this.a(this.a(s, true), array);
    }
    
    public final Object a(final Method method, final Object[] array) {
        try {
            return method.invoke(this.c, array);
        }
        catch (Exception ex) {
            if (m.a) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public final Object a(final Method method, final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        try {
            return method.invoke(this.c, o, o2, o3, o4, o5);
        }
        catch (Exception ex) {
            d.a(ex);
            if (m.a) {
                ex.printStackTrace();
                if (ex instanceof InvocationTargetException) {
                    ((InvocationTargetException)ex).getTargetException().printStackTrace();
                }
            }
            return null;
        }
    }
    
    public final Object a(final Method method, final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6, final Object o7) {
        try {
            return method.invoke(this.c, o, o2, o3, o4, o5, o6, o7);
        }
        catch (Exception ex) {
            if (m.a) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public final void b() {
        this.c = null;
        this.b = null;
    }
    
    static {
        m.a = d.cy();
    }
}
