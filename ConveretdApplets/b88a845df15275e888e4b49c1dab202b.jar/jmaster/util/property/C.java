// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Iterator;
import jmaster.util.property.loader.StringPropertyLoader;
import jmaster.util.property.loader.ShortPropertyLoader;
import jmaster.util.property.loader.LongPropertyLoader;
import jmaster.util.property.loader.IntPropertyLoader;
import jmaster.util.property.loader.FloatPropertyLoader;
import jmaster.util.property.loader.DoublePropertyLoader;
import jmaster.util.property.loader.CharPropertyLoader;
import jmaster.util.property.loader.BooleanPropertyLoader;
import jmaster.util.property.loader.BytePropertyLoader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;

public class C
{
    public static final String E = "set";
    public static final String C = "super";
    private static final String D = "PropertyHelper.properties";
    protected static C A;
    protected Hashtable B;
    
    protected C() {
        this.B = new Hashtable();
        final Method[] methods = this.getClass().getMethods();
        final HashMap<String, Method> hashMap = new HashMap<String, Method>();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            hashMap.put(method.getName(), method);
        }
        this.A(new BytePropertyLoader());
        this.A(new BooleanPropertyLoader());
        this.A(new CharPropertyLoader());
        this.A(new DoublePropertyLoader());
        this.A(new FloatPropertyLoader());
        this.A(new IntPropertyLoader());
        this.A(new LongPropertyLoader());
        this.A(new ShortPropertyLoader());
        this.A(new StringPropertyLoader());
        final D g = jmaster.util.property.B.C().G("PropertyHelper.properties");
        for (final String s : ((Hashtable<String, V>)g).keySet()) {
            if (s.startsWith("propertyLoader.")) {
                final String q = g.Q(s);
                try {
                    this.A((A)Class.forName(q).newInstance());
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    
    public static synchronized C A() {
        if (jmaster.util.property.C.A == null) {
            jmaster.util.property.C.A = new C();
        }
        return jmaster.util.property.C.A;
    }
    
    protected static void A(final String s) {
        System.out.println(s);
    }
    
    private String A(final Method method) {
        final String name = method.getName();
        if (!name.startsWith("set") || name.length() < "set".length() + 1) {
            throw new IllegalArgumentException("The method " + name + " is not proper candidate for property injecting, " + "property name retrieval is not possible");
        }
        return name.substring("set".length(), "set".length() + 1).toLowerCase(Locale.ENGLISH) + name.substring("set".length() + 1);
    }
    
    private Object[] A(final Method method, final D d, final String s) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Object[] array = new Object[parameterTypes.length];
        final String a = this.A(method, s);
        String[] h;
        if (array.length > 1) {
            h = d.H(a);
            if (h.length != array.length) {
                throw new IllegalArgumentException("Method " + method.getName() + " expects " + array.length + " parameters, found: " + h.length + ", properties prefix: '" + s + "'");
            }
        }
        else {
            h = new String[] { d.Q(a) };
        }
        for (int i = 0; i < parameterTypes.length; ++i) {
            final Class<?> clazz = parameterTypes[i];
            final A a2 = this.A(clazz);
            if (a2 == null) {
                throw new NullPointerException("Property loader not found for class " + clazz);
            }
            try {
                array[i] = a2.loadProperty(h[i], d, a, i);
            }
            catch (Exception ex) {
                throw new RuntimeException("Property load failed, value=" + h[i] + ", key=" + a, ex);
            }
        }
        return array;
    }
    
    private String A(final Method method, final String s) {
        return ((s == null) ? "" : (s + ".")) + this.A(method);
    }
    
    private A A(final Class clazz) {
        A a = this.B.get(clazz);
        if (a == null) {
            for (final A a2 : this.B.values()) {
                if (clazz.isAssignableFrom(a2.getPropertyClass())) {
                    this.B.put(clazz, a = a2);
                    break;
                }
            }
        }
        return a;
    }
    
    private boolean B(final Method method, final D d, final String s) {
        final String name = method.getName();
        boolean containsKey = name.startsWith("set") && name.length() > "set".length();
        if (containsKey && d != null) {
            containsKey = d.containsKey(d.F(s, this.A(method)));
        }
        if (containsKey) {
            final Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; ++i) {
                if (this.A(parameterTypes[i]) == null) {
                    containsKey = false;
                    break;
                }
            }
        }
        return containsKey;
    }
    
    public void A(final A a) {
        this.B.put(a.getPropertyClass(), a);
    }
    
    public void A(final Object o, final D d, final String s) {
        if (o == null) {
            throw new NullPointerException("Cannot inject properties to null object");
        }
        final String f = d.F(s, "super");
        if (d.containsKey(f)) {
            this.A(o, d, d.Q(f));
        }
        final Method[] methods = o.getClass().getMethods();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            if (this.B(method, d, s)) {
                final Object[] a = this.A(method, d, s);
                try {
                    method.invoke(o, a);
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    
    public void A(final Object o, final PrintWriter printWriter) {
        final Method[] methods = o.getClass().getMethods();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            if (this.B(method, null, null)) {
                printWriter.print(method.getName() + "(");
                final Class<?>[] parameterTypes = method.getParameterTypes();
                for (int j = 0; j < parameterTypes.length; ++j) {
                    final Class<?> clazz = parameterTypes[j];
                    if (j > 0) {
                        printWriter.print(",");
                    }
                    printWriter.print(clazz.getName());
                }
                printWriter.print("), property=" + this.A(method));
                printWriter.println();
                printWriter.flush();
            }
        }
    }
    
    public void A(final PrintWriter printWriter) {
        for (final Class clazz : this.B.keySet()) {
            printWriter.println(clazz.getName() + "=" + ((A)this.B.get(clazz)).getClass().getName());
        }
        printWriter.flush();
    }
    
    public void A(final Object o, final String s, final Object o2) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
            Object invoke = o;
            Method a = null;
            while (invoke != null && stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (stringTokenizer.hasMoreTokens()) {
                    final String string = "get" + nextToken;
                    final Method a2 = jmaster.util.C.B.A(invoke.getClass(), string, 0);
                    if (a2 == null) {
                        throw new NullPointerException("Failed to resolve getterMethod named '" + string + "', " + "bean=" + o + ", property=" + s + ", value=" + o2);
                    }
                    invoke = a2.invoke(invoke, (Object[])null);
                }
                else {
                    a = jmaster.util.C.B.A(invoke.getClass(), "set" + nextToken, 1);
                }
            }
            if (invoke == null || a == null) {
                throw new NullPointerException("Failed to resolve target bean or setter, bean=" + o + ", property=" + s + ", value=" + o2);
            }
            Object loadProperty;
            if ((loadProperty = o2) != null) {
                final Class<?> clazz = a.getParameterTypes()[0];
                if (!clazz.isAssignableFrom(o2.getClass())) {
                    final A a3 = this.A(clazz);
                    if (a3 == null) {
                        throw new NullPointerException("Property loader not found for class " + clazz);
                    }
                    final D d = new D();
                    ((Hashtable<String, String>)d).put(s, "" + o2);
                    loadProperty = a3.loadProperty("" + o2, d, s, 0);
                }
            }
            a.invoke(invoke, loadProperty);
        }
        catch (Exception ex) {
            if (ex instanceof RuntimeException) {
                throw (RuntimeException)ex;
            }
            throw new RuntimeException(ex);
        }
    }
    
    public static D A(final String s, final String s2) {
        final D d = new D();
        if (s != null) {
            final String replaceAll = s.replaceAll(Pattern.quote(s2), "\r\n");
            try {
                d.load(new ByteArrayInputStream(replaceAll.getBytes("utf-8")));
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return d;
    }
    
    static {
        jmaster.util.property.C.A = null;
    }
}
