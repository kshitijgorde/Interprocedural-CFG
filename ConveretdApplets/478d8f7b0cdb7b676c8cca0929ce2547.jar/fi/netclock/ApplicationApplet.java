// 
// Decompiled by Procyon v0.5.30
// 

package fi.netclock;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.awt.Component;
import javax.swing.JApplet;

public class ApplicationApplet extends JApplet
{
    private boolean cVa;
    private boolean ya;
    private implements EUa;
    private import FUa;
    private static String T = "\u6633\u662b\u6629\u662e\u666e\u6630\u6632\u662f\u6630\u6625\u6632\u6634\u6639\u666e\u6626\u6629\u662c\u6625\u666e\u6635\u6632\u662c";
    private static String U = "\u662e\u6625\u6634\u6623\u662c\u662f\u6623\u662b\u666e\u6624\u6625\u6622\u6635\u6627";
    private static String V = "\u6626\u6621\u662c\u6633\u6625";
    private static String W = "\u660c\u662f\u6621\u6624\u6629\u662e\u6627\u6660\u6623\u662c\u662f\u6623\u662b\u6607\u662c\u6621\u6633\u6633\u666e\u666e\u666e";
    private static String ba = "\u6629\u662d\u6621\u6627\u6625\u666e\u6623\u662c\u662f\u6623\u662b\u6627\u662c\u6621\u6633\u6633\u666e\u6635\u6632\u662c";
    private static String ca = "\u6629\u662d\u6621\u6627\u6625\u666e\u6623\u662c\u662f\u6623\u662b\u6627\u662c\u6621\u6633\u6633\u666e\u6638";
    private static String da = "\u6629\u662d\u6621\u6627\u6625\u666e\u6623\u662c\u662f\u6623\u662b\u6627\u662c\u6621\u6633\u6633\u666e\u6639";
    private static String ea = "\u6623\u662c\u662f\u6623\u662b\u6607\u662c\u6621\u6633\u6633\u6660\u6629\u662d\u6621\u6627\u6625\u6660\u662c\u662f\u6621\u6624\u6660\u6626\u6621\u6629\u662c\u6625\u6624\u6661\u6660\u6609\u662d\u6621\u6627\u6625\u6660\u6629\u6633\u6660\u6624\u6629\u6633\u6621\u6622\u662c\u6625\u6624\u666e";
    private static String ta = "\u660c\u662f\u6621\u6624\u6629\u662e\u6627\u6660\u6629\u662e\u6626\u662f\u6613\u6623\u6632\u6625\u6625\u662e\u6609\u662e\u6626\u662f\u666e\u666e\u666e";
    private static String ua = "\u6629\u662e\u6626\u662f\u6633\u6623\u6632\u6625\u6625\u662e\u666e\u6621\u6623\u6634\u6629\u6636\u6625\u6621\u6632\u6625\u6621\u666e\u6638";
    private static String Sa = "\u6629\u662e\u6626\u662f\u6633\u6623\u6632\u6625\u6625\u662e\u666e\u6621\u6623\u6634\u6629\u6636\u6625\u6621\u6632\u6625\u6621\u666e\u6639";
    private static String Ta = "\u6629\u662e\u6626\u662f\u6633\u6623\u6632\u6625\u6625\u662e\u666e\u6621\u6623\u6634\u6629\u6636\u6625\u6621\u6632\u6625\u6621\u666e\u6637\u6629\u6624\u6634\u6628";
    private static String Ua = "\u6629\u662e\u6626\u662f\u6633\u6623\u6632\u6625\u6625\u662e\u666e\u6621\u6623\u6634\u6629\u6636\u6625\u6621\u6632\u6625\u6621\u666e\u6628\u6625\u6629\u6627\u6628\u6634";
    private static String Va = "\u6633\u662b\u6629\u662e\u666e\u6623\u6632\u6625\u6621\u6634\u662f\u6632";
    private static String Wa = "\u660c\u662f\u6621\u6624\u6629\u662e\u6627\u6660\u6633\u6630\u6632\u6629\u6634\u6625\u666e\u666e\u666e";
    private static String Xa = "\u6629\u662d\u6621\u6627\u6625\u666e\u6624\u6629\u6627\u6629\u6634\u6621\u662c\u6626\u662f\u662e\u6634\u6633\u666e\u6626\u662f\u662e\u6634\u666e\u6637\u6629\u6624\u6634\u6628";
    private static String Ya = "\u6629\u662d\u6621\u6627\u6625\u666e\u6624\u6629\u6627\u6629\u6634\u6621\u662c\u6626\u662f\u662e\u6634\u6633\u666e\u6633\u6630\u6621\u6623\u6625\u666e\u6637\u6629\u6624\u6634\u6628";
    private static String Za = "\u6629\u662d\u6621\u6627\u6625\u666e\u6624\u6629\u6627\u6629\u6634\u6621\u662c\u6626\u662f\u662e\u6634\u6633\u666e\u6635\u6632\u662c";
    private static String h = "\u6624\u6629\u6627\u6629\u6634\u6621\u662c\u6624\u6629\u6633\u6630\u662c\u6621\u6639\u666e\u6638";
    private static String i = "\u6624\u6629\u6627\u6629\u6634\u6621\u662c\u6624\u6629\u6633\u6630\u662c\u6621\u6639\u666e\u6639";
    private static String j = "\u6624\u6629\u6627\u6629\u6634\u6621\u662c\u6624\u6629\u6633\u6630\u662c\u6621\u6639\u666e\u6637\u6629\u6624\u6634\u6628";
    private static String k = "\u6624\u6629\u6627\u6629\u6634\u6621\u662c\u6624\u6629\u6633\u6630\u662c\u6621\u6639\u666e\u6628\u6625\u6629\u6627\u6628\u6634";
    private static String l = "\u6624\u6629\u6627\u6629\u6634\u6621\u662c\u6624\u6629\u6633\u6630\u662c\u6621\u6639\u666e\u6623\u6628\u6621\u662e\u6627\u6625\u6632\u6621\u6634\u6625";
    private static String m = "\u660c\u662f\u6621\u6624\u6629\u662e\u6627\u6660\u6623\u662c\u662f\u6623\u662b\u6606\u6621\u6623\u6625\u666e\u666e\u666e";
    private static String n = "\u6629\u662d\u6621\u6627\u6625\u666e\u6623\u662c\u662f\u6623\u662b\u6626\u6621\u6623\u6625\u666e\u6635\u6632\u662c";
    private static String o = "\u6629\u662d\u6621\u6627\u6625\u666e\u6623\u662c\u662f\u6623\u662b\u6626\u6621\u6623\u6625\u666e\u6638";
    private static String p = "\u6629\u662d\u6621\u6627\u6625\u666e\u6623\u662c\u662f\u6623\u662b\u6626\u6621\u6623\u6625\u666e\u6639";
    private static String q = "\u660c\u662f\u6621\u6624\u6629\u662e\u6627\u6660\u6623\u662c\u662f\u6623\u662b\u6606\u6632\u6621\u662d\u6625\u666e\u666e\u666e";
    private static String r = "\u6629\u662d\u6621\u6627\u6625\u666e\u6623\u662c\u662f\u6623\u662b\u6626\u6632\u6621\u662d\u6625\u666e\u6635\u6632\u662c";
    private static String s = "\u6629\u662d\u6621\u6627\u6625\u666e\u6623\u662c\u662f\u6623\u662b\u6626\u6632\u6621\u662d\u6625\u666e\u6621\u662e\u6623\u6628\u662f\u6632\u666e\u6638";
    private static String t = "\u6629\u662d\u6621\u6627\u6625\u666e\u6623\u662c\u662f\u6623\u662b\u6626\u6632\u6621\u662d\u6625\u666e\u6621\u662e\u6623\u6628\u662f\u6632\u666e\u6639";
    private static String u = "\u660c\u662f\u6621\u6624\u6629\u662e\u6627\u6660\u6624\u6625\u6627\u6632\u6625\u6625\u6608\u6621\u662e\u6624\u666e\u666e\u666e";
    private static String v = "\u6629\u662d\u6621\u6627\u6625\u666e\u6624\u6625\u6627\u6632\u6625\u6625\u6628\u6621\u662e\u6624\u666e\u6635\u6632\u662c";
    private static String w = "\u6629\u662d\u6621\u6627\u6625\u666e\u6624\u6625\u6627\u6632\u6625\u6625\u6628\u6621\u662e\u6624\u666e\u6621\u662e\u6623\u6628\u662f\u6632\u666e\u6638";
    private static String x = "\u6629\u662d\u6621\u6627\u6625\u666e\u6624\u6625\u6627\u6632\u6625\u6625\u6628\u6621\u662e\u6624\u666e\u6621\u662e\u6623\u6628\u662f\u6632\u666e\u6639";
    private static String y = "\u660c\u662f\u6621\u6624\u6629\u662e\u6627\u6660\u662d\u6629\u662e\u6635\u6634\u6625\u6608\u6621\u662e\u6624\u666e\u666e\u666e";
    private static String z = "\u6629\u662d\u6621\u6627\u6625\u666e\u662d\u6629\u662e\u6635\u6634\u6625\u6628\u6621\u662e\u6624\u666e\u6635\u6632\u662c";
    private static String A = "\u6629\u662d\u6621\u6627\u6625\u666e\u662d\u6629\u662e\u6635\u6634\u6625\u6628\u6621\u662e\u6624\u666e\u6621\u662e\u6623\u6628\u662f\u6632\u666e\u6638";
    private static String B = "\u6629\u662d\u6621\u6627\u6625\u666e\u662d\u6629\u662e\u6635\u6634\u6625\u6628\u6621\u662e\u6624\u666e\u6621\u662e\u6623\u6628\u662f\u6632\u666e\u6639";
    private static String C = "\u6610\u6621\u6632\u6633\u6629\u662e\u6627\u6660\u6622\u6627\u6603\u662f\u662c\u662f\u6632\u666e\u666e\u666e";
    private static String D = "\u6626\u6632\u6621\u662d\u6625\u666e\u6622\u6621\u6623\u662b\u6627\u6632\u662f\u6635\u662e\u6624\u666e\u6623\u662f\u662c\u662f\u6632\u666e\u6632";
    private static String E = "\u6626\u6632\u6621\u662d\u6625\u666e\u6622\u6621\u6623\u662b\u6627\u6632\u662f\u6635\u662e\u6624\u666e\u6623\u662f\u662c\u662f\u6632\u666e\u6627";
    private static String IUa = "\u6626\u6632\u6621\u662d\u6625\u666e\u6622\u6621\u6623\u662b\u6627\u6632\u662f\u6635\u662e\u6624\u666e\u6623\u662f\u662c\u662f\u6632\u666e\u6622";
    private static String JUa = "\u6622\u6627\u6603\u662f\u662c\u662f\u6632\u667a\u6660\u6632\u667d";
    private static String KUa = "\u666c\u6660\u6627\u667d";
    private static String LUa = "\u666c\u6660\u6622\u667d";
    private static String MUa = "\u6605\u6632\u6632\u662f\u6632\u6660\u6630\u6632\u662f\u6623\u6625\u6633\u6633\u6629\u662e\u6627\u6660\u6630\u6621\u6632\u6621\u662d\u6625\u6634\u6625\u6632\u6633\u667a\u6660";
    private static String NUa = "\u6603\u6625\u662e\u6634\u6625\u6632";
    private static String OUa = "\u6660\u666d\u6660";
    
    public ApplicationApplet() {
        this.cVa = false;
        this.ya = true;
        this.FUa = null;
    }
    
    public void init() {
        try {
            this.e();
            this.EUa.a(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void e() {
        interface _ = null;
        interface _2 = null;
        interface _3 = null;
        interface _4 = null;
        interface _5 = null;
        native native1 = null;
        null null = null;
        final instanceof instanceof1 = new instanceof();
        Color color = null;
        try {
            this.FUa = new import(this.getParameter(ApplicationApplet.T), this);
            if (this.FUa.getProperty(ApplicationApplet.U).equalsIgnoreCase(ApplicationApplet.V)) {
                this.ya = false;
            }
            this.b(ApplicationApplet.W);
            try {
                _ = this.FUa._(ApplicationApplet.ba, ApplicationApplet.ca, ApplicationApplet.da, this);
            }
            catch (IOException ex2) {
                this.b(ApplicationApplet.ea);
                _ = null;
            }
            this.b(ApplicationApplet.ta);
            native1 = new native(this.ya, Integer.parseInt(this.FUa.getProperty(ApplicationApplet.ua)), Integer.parseInt(this.FUa.getProperty(ApplicationApplet.Sa)), Integer.parseInt(this.FUa.getProperty(ApplicationApplet.Ta)), Integer.parseInt(this.FUa.getProperty(ApplicationApplet.Ua)), this.FUa.getProperty(ApplicationApplet.Va), this);
            this.b(ApplicationApplet.Wa);
            null = new null(this.ya, new new(this.ya, Integer.parseInt(this.FUa.getProperty(ApplicationApplet.Xa)), Integer.parseInt(this.FUa.getProperty(ApplicationApplet.Ya)), this.FUa._(ApplicationApplet.Za, this), this), Integer.parseInt(this.FUa.getProperty(ApplicationApplet.h)), Integer.parseInt(this.FUa.getProperty(ApplicationApplet.i)), Integer.parseInt(this.FUa.getProperty(ApplicationApplet.j)), Integer.parseInt(this.FUa.getProperty(ApplicationApplet.k)), Integer.parseInt(this.FUa.getProperty(ApplicationApplet.l)), this);
            this.b(ApplicationApplet.m);
            _2 = this.FUa._(ApplicationApplet.n, ApplicationApplet.o, ApplicationApplet.p, this);
            this.b(ApplicationApplet.q);
            _3 = this.FUa._(ApplicationApplet.r, ApplicationApplet.s, ApplicationApplet.t, this);
            this.b(ApplicationApplet.u);
            _4 = this.FUa._(ApplicationApplet.v, ApplicationApplet.w, ApplicationApplet.x, this);
            this.b(ApplicationApplet.y);
            _5 = this.FUa._(ApplicationApplet.z, ApplicationApplet.A, ApplicationApplet.B, this);
            this.b(ApplicationApplet.C);
            final int int1 = Integer.parseInt(this.FUa.getProperty(ApplicationApplet.D));
            final int int2 = Integer.parseInt(this.FUa.getProperty(ApplicationApplet.E));
            final int int3 = Integer.parseInt(this.FUa.getProperty(ApplicationApplet.IUa));
            color = new Color(int1, int2, int3);
            this.b(String.valueOf(String.valueOf(new StringBuffer(ApplicationApplet.JUa).append(int1).append(ApplicationApplet.KUa).append(int2).append(ApplicationApplet.LUa).append(int3))));
        }
        catch (Exception ex) {
            this.b(ApplicationApplet.MUa.concat(String.valueOf(String.valueOf(ex.toString()))));
            System.exit(1);
        }
        this.EUa = new implements(this.ya, _, _2, _3, _4, _5, native1, null, color);
        this.getContentPane().add(this.EUa, ApplicationApplet.NUa);
        this.validate();
    }
    
    private void b(final String s) {
        if (this.ya) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i()))).append(ApplicationApplet.OUa).append(s))));
        }
    }
    
    private String i() {
        return this.getClass().getName();
    }
    
    private static String h(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u6640');
        }
        return new String(array);
    }
    
    static {
        ApplicationApplet.T = h(ApplicationApplet.T);
        ApplicationApplet.U = h(ApplicationApplet.U);
        ApplicationApplet.V = h(ApplicationApplet.V);
        ApplicationApplet.W = h(ApplicationApplet.W);
        ApplicationApplet.ba = h(ApplicationApplet.ba);
        ApplicationApplet.ca = h(ApplicationApplet.ca);
        ApplicationApplet.da = h(ApplicationApplet.da);
        ApplicationApplet.ea = h(ApplicationApplet.ea);
        ApplicationApplet.ta = h(ApplicationApplet.ta);
        ApplicationApplet.ua = h(ApplicationApplet.ua);
        ApplicationApplet.Sa = h(ApplicationApplet.Sa);
        ApplicationApplet.Ta = h(ApplicationApplet.Ta);
        ApplicationApplet.Ua = h(ApplicationApplet.Ua);
        ApplicationApplet.Va = h(ApplicationApplet.Va);
        ApplicationApplet.Wa = h(ApplicationApplet.Wa);
        ApplicationApplet.Xa = h(ApplicationApplet.Xa);
        ApplicationApplet.Ya = h(ApplicationApplet.Ya);
        ApplicationApplet.Za = h(ApplicationApplet.Za);
        ApplicationApplet.h = h(ApplicationApplet.h);
        ApplicationApplet.i = h(ApplicationApplet.i);
        ApplicationApplet.j = h(ApplicationApplet.j);
        ApplicationApplet.k = h(ApplicationApplet.k);
        ApplicationApplet.l = h(ApplicationApplet.l);
        ApplicationApplet.m = h(ApplicationApplet.m);
        ApplicationApplet.n = h(ApplicationApplet.n);
        ApplicationApplet.o = h(ApplicationApplet.o);
        ApplicationApplet.p = h(ApplicationApplet.p);
        ApplicationApplet.q = h(ApplicationApplet.q);
        ApplicationApplet.r = h(ApplicationApplet.r);
        ApplicationApplet.s = h(ApplicationApplet.s);
        ApplicationApplet.t = h(ApplicationApplet.t);
        ApplicationApplet.u = h(ApplicationApplet.u);
        ApplicationApplet.v = h(ApplicationApplet.v);
        ApplicationApplet.w = h(ApplicationApplet.w);
        ApplicationApplet.x = h(ApplicationApplet.x);
        ApplicationApplet.y = h(ApplicationApplet.y);
        ApplicationApplet.z = h(ApplicationApplet.z);
        ApplicationApplet.A = h(ApplicationApplet.A);
        ApplicationApplet.B = h(ApplicationApplet.B);
        ApplicationApplet.C = h(ApplicationApplet.C);
        ApplicationApplet.D = h(ApplicationApplet.D);
        ApplicationApplet.E = h(ApplicationApplet.E);
        ApplicationApplet.IUa = h(ApplicationApplet.IUa);
        ApplicationApplet.JUa = h(ApplicationApplet.JUa);
        ApplicationApplet.KUa = h(ApplicationApplet.KUa);
        ApplicationApplet.LUa = h(ApplicationApplet.LUa);
        ApplicationApplet.MUa = h(ApplicationApplet.MUa);
        ApplicationApplet.NUa = h(ApplicationApplet.NUa);
        ApplicationApplet.OUa = h(ApplicationApplet.OUa);
    }
}
