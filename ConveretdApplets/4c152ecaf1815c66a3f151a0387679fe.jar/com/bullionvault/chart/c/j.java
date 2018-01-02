// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.c;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.bullionvault.chart.resources.Resources;
import java.util.Vector;

public final class j
{
    public String a;
    public String b;
    public int c;
    public String d;
    public String e;
    public String f;
    public String g;
    public boolean h;
    public static String i;
    private static String o;
    public static String j;
    private static String p;
    private static String q;
    private static String r;
    private static String s;
    private static String t;
    private static String u;
    public static String k;
    private static String v;
    private static String w;
    private static String x;
    private static String y;
    public static String l;
    private static String z;
    private static String A;
    private static String B;
    private static String C;
    private static String D;
    private static String E;
    private static String F;
    private static String G;
    private static String H;
    public static String[] m;
    private static String[] I;
    private static String[] J;
    private static String[] K;
    public static j[] n;
    
    public static void a() {
        com.bullionvault.chart.c.j.i = "menu.series.spotgold";
        com.bullionvault.chart.c.j.o = "menu.series.spotsilver";
        com.bullionvault.chart.c.j.j = "menu.currency.USD";
        com.bullionvault.chart.c.j.p = "menu.currency.GBP";
        com.bullionvault.chart.c.j.q = "menu.currency.EUR";
        com.bullionvault.chart.c.j.r = "menu.currency.JPY";
        com.bullionvault.chart.c.j.s = "menu.currency.AUD";
        com.bullionvault.chart.c.j.t = "menu.currency.CAD";
        com.bullionvault.chart.c.j.u = "menu.currency.CHF";
        com.bullionvault.chart.c.j.k = "menu.style.line";
        com.bullionvault.chart.c.j.v = "menu.style.hlc";
        com.bullionvault.chart.c.j.w = "menu.period.10minutes";
        com.bullionvault.chart.c.j.x = "menu.period.hour";
        com.bullionvault.chart.c.j.y = "menu.period.6hours";
        com.bullionvault.chart.c.j.l = "menu.period.day";
        com.bullionvault.chart.c.j.z = "menu.period.week";
        com.bullionvault.chart.c.j.A = "menu.period.month";
        com.bullionvault.chart.c.j.B = "menu.period.quarter";
        com.bullionvault.chart.c.j.C = "menu.period.year";
        com.bullionvault.chart.c.j.D = "menu.period.5years";
        com.bullionvault.chart.c.j.E = "menu.period.20years";
        final String[] m = { com.bullionvault.chart.c.j.j, com.bullionvault.chart.c.j.p, com.bullionvault.chart.c.j.q, com.bullionvault.chart.c.j.r, com.bullionvault.chart.c.j.s, com.bullionvault.chart.c.j.t, com.bullionvault.chart.c.j.u };
        final String[] i = { com.bullionvault.chart.c.j.k, com.bullionvault.chart.c.j.v };
        final String[] j = { com.bullionvault.chart.c.j.w, com.bullionvault.chart.c.j.x, com.bullionvault.chart.c.j.y, com.bullionvault.chart.c.j.l, com.bullionvault.chart.c.j.z, com.bullionvault.chart.c.j.A, com.bullionvault.chart.c.j.B, com.bullionvault.chart.c.j.C, com.bullionvault.chart.c.j.D, com.bullionvault.chart.c.j.E };
        final String[] k = { com.bullionvault.chart.c.j.i, com.bullionvault.chart.c.j.o };
        com.bullionvault.chart.c.j.m = m;
        com.bullionvault.chart.c.j.I = i;
        com.bullionvault.chart.c.j.J = j;
        com.bullionvault.chart.c.j.K = k;
        final Vector<j> vector = new Vector<j>();
        for (int l = 0; l < com.bullionvault.chart.c.j.m.length; ++l) {
            for (int n = 0; n < com.bullionvault.chart.c.j.I.length; ++n) {
                for (int n2 = 0; n2 < com.bullionvault.chart.c.j.J.length; ++n2) {
                    for (int n3 = 0; n3 < com.bullionvault.chart.c.j.K.length; ++n3) {
                        final j j2;
                        if ((j2 = new j(com.bullionvault.chart.c.j.K[n3], com.bullionvault.chart.c.j.m[l], com.bullionvault.chart.c.j.I[n], com.bullionvault.chart.c.j.J[n2])).b == com.bullionvault.chart.c.j.j && j2.d == com.bullionvault.chart.c.j.k && (j2.e == com.bullionvault.chart.c.j.l || j2.e == com.bullionvault.chart.c.j.z)) {
                            j2.h = false;
                        }
                        vector.addElement(j2);
                    }
                }
            }
        }
        com.bullionvault.chart.c.j.n = a(vector);
    }
    
    public j(final String a, final String b, final String d, final String e) {
        this.h = true;
        this.a = a;
        this.b = b;
        this.d = d;
        this.e = e;
        if (e.equals(com.bullionvault.chart.c.j.w)) {
            this.c = 5;
        }
        else if (e.equals(com.bullionvault.chart.c.j.x)) {
            this.c = 15;
        }
        else if (e.equals(com.bullionvault.chart.c.j.y)) {
            this.c = 120;
        }
        else if (e.equals(com.bullionvault.chart.c.j.l)) {
            this.c = 600;
        }
        else if (e.equals(com.bullionvault.chart.c.j.z)) {
            this.c = 3600;
        }
        else if (e.equals(com.bullionvault.chart.c.j.A)) {
            this.c = 14400;
        }
        else if (e.equals(com.bullionvault.chart.c.j.B)) {
            this.c = 43200;
        }
        else if (e.equals(com.bullionvault.chart.c.j.C)) {
            this.c = 172800;
        }
        else if (e.equals(com.bullionvault.chart.c.j.D)) {
            this.c = 864000;
        }
        else {
            if (!e.equals(com.bullionvault.chart.c.j.E)) {
                throw new RuntimeException("Unknown chart period requested - " + e);
            }
            this.c = 2592000;
        }
        if (d.equals(com.bullionvault.chart.c.j.k)) {
            this.f = com.bullionvault.chart.c.j.F + a(a) + "/" + a(b) + "/" + this.c;
        }
        else {
            if (!d.equals(com.bullionvault.chart.c.j.v)) {
                throw new RuntimeException("Unknown chart style requested - " + d);
            }
            this.f = com.bullionvault.chart.c.j.G + a(a) + "/" + a(b) + "/" + this.c;
        }
        this.g = com.bullionvault.chart.c.j.H + a(a) + "/" + a(b) + "/" + this.c;
    }
    
    public final boolean a(final j j) {
        return (this.a.equals(j.a) || this.a.equals(b(j.a))) && (this.b.equals(j.b) || this.b.equals(b(j.b))) && (this.d.equals(j.d) || this.d.equals(b(j.d))) && (this.e.equals(j.e) || this.e.equals(b(j.e)));
    }
    
    private static String b(final String s) {
        if (s.startsWith("menu.")) {
            return Resources.b(s);
        }
        return s;
    }
    
    public static j b(final j j) {
        for (int i = 0; i < j.n.length; ++i) {
            if (j.a(j.n[i])) {
                return j.n[i];
            }
        }
        return null;
    }
    
    private static j[] a(final Vector vector) {
        final j[] array = new j[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public final String toString() {
        return "chartParam: series=[" + this.a + "] currency=[" + this.b + "] style=[" + this.d + "] period=[" + this.e + "] --> seconds=[" + this.c + "] url=" + this.f + " secured=" + this.h;
    }
    
    public final j b() {
        final j j;
        (j = new j(this.a, this.b, this.d, this.e)).h = this.h;
        return j;
    }
    
    public static String a(final String s) {
        final ResourceBundle bundle = ResourceBundle.getBundle("com.bullionvault.chart.util.urlFragments");
        try {
            return bundle.getString(s);
        }
        catch (MissingResourceException ex) {
            return s;
        }
    }
    
    static {
        com.bullionvault.chart.c.j.F = "/CHART_LINE/";
        com.bullionvault.chart.c.j.G = "/CHART_BAR_HLC/";
        com.bullionvault.chart.c.j.H = "/CSV/";
        com.bullionvault.chart.c.j.n = new j[0];
    }
}
