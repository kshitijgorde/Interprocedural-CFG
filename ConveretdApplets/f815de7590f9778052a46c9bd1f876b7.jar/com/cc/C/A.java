// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.C;

import java.awt.Graphics;
import A.A.E;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

public class A
{
    public static final String P = "SansSerif";
    public static final String \u00cd = "%SUBMIT%";
    public static final String \u00cf = "%MARKED%";
    public static final String \u00c1 = "%PROGRESS%";
    public static final String E = "%TIME%";
    public static final String \u00d0 = "%CHARSET%";
    public static final String \u00c6 = "%KEYWORD%";
    public String A;
    public String ¥;
    public String D;
    public boolean \u00c2;
    public int F;
    public boolean \u00c3;
    public boolean V;
    public boolean b;
    public boolean t;
    public int \u00d2;
    public boolean \u00c9;
    public C H;
    public boolean u;
    public String s;
    public static final int º = 0;
    public static final int r = 1;
    public static final int c = 2;
    public int L;
    public boolean ¤;
    public boolean T;
    public Font M;
    public int \u00c7;
    public int K;
    public Color w;
    public Color £;
    public boolean R;
    public boolean ª;
    public boolean O;
    public Color \u00d1;
    public String \u00cc;
    public String i;
    public boolean y;
    public boolean W;
    public String h;
    public String \u00c8;
    public String e;
    public String q;
    public boolean p;
    public Color \u00cb;
    public Color \u00c4;
    public Color X;
    public Image g;
    public String \u00c0;
    public String ¢;
    public String x;
    public String J;
    public boolean µ;
    public boolean I;
    public int Z;
    public com.cc.D.A Q;
    public com.cc.D.A z;
    private static final String m = "%CREATED%";
    private static final String U = "%TITLE%";
    private static final String f = "%CREATOR%";
    private static final String Y = "%EDITOR%";
    private static final String a = "%RIGHTS%";
    private static final String j = "%COPYRIGHT%";
    private static final String C = "%PUBLISHER%";
    private static final String N = "%DESCRIPTION%";
    public String G;
    public String v;
    public String d;
    public String k;
    public String \u00d3;
    public String \u00ce;
    public String n;
    public String B;
    public String \u00ca;
    public String l;
    public int \u00c5;
    public int S;
    public int o;
    public int _;
    
    public A() {
        this.A = "Cp1252";
        this.¥ = "Cp1252";
        this.D = "Cp1252";
        this.\u00c2 = false;
        this.F = 0;
        this.\u00c3 = true;
        this.V = false;
        this.b = false;
        this.t = false;
        this.\u00d2 = 10000;
        this.\u00c9 = false;
        this.H = new C();
        this.u = false;
        this.s = "";
        this.L = 0;
        this.M = new Font("SansSerif", 0, 12);
        this.\u00c7 = 200;
        this.K = 5;
        this.w = Color.red;
        this.£ = Color.gray;
        this.R = false;
        this.ª = false;
        this.O = true;
        this.\u00d1 = Color.blue;
        this.i = "[?]";
        this.y = false;
        this.q = "GET";
        this.p = false;
        this.\u00cb = Color.black;
        this.\u00c4 = Color.white;
        this.X = Color.gray;
        this.µ = false;
        this.I = false;
        this.Z = 11;
        this.G = "";
        this.v = "";
        this.d = "";
        this.k = "";
        this.\u00d3 = "";
        this.\u00ce = "";
        this.n = "";
        this.B = "";
        this.\u00ca = "";
        this.l = "";
        this.o = 0;
        this._ = 0;
    }
    
    public String A() {
        if (this.\u00ce.indexOf("\u00c2©") != -1) {
            return this.\u00ce;
        }
        return "\u00c2© " + this.\u00ce.trim();
    }
    
    public com.cc.D.A A(final com.cc.D.A a) {
        for (int i = 0; i < a.C().length; ++i) {
            a.C()[i].A(this.A(a.C()[i].A()));
        }
        return a;
    }
    
    public com.cc.D.A C(final String s) {
        return this.A(com.cc.D.A.A(A.A.E.A("<tag>" + s + "</tag>").B()));
    }
    
    private String A(final String s) {
        String string = new String();
        int n = 0;
        int index;
        for (int i = s.indexOf(37); i > -1; i = s.indexOf(37, index + 1)) {
            index = s.indexOf(37, i + 1);
            string = string + s.substring(n, i) + this.B(s.substring(i, index + 1));
            n = index + 1;
        }
        return string + s.substring(n);
    }
    
    private String B(final String s) {
        if (s.equalsIgnoreCase("%CREATED%")) {
            return this.G;
        }
        if (s.equalsIgnoreCase("%TITLE%")) {
            return this.v;
        }
        if (s.equalsIgnoreCase("%CREATOR%")) {
            return this.d;
        }
        if (s.equalsIgnoreCase("%EDITOR%")) {
            return this.k;
        }
        if (s.equalsIgnoreCase("%RIGHTS%")) {
            return this.\u00d3;
        }
        if (s.equalsIgnoreCase("%COPYRIGHT%")) {
            return this.\u00ce;
        }
        if (s.equalsIgnoreCase("%PUBLISHER%")) {
            return this.n;
        }
        if (s.equalsIgnoreCase("%DESCRIPTION%")) {
            return this.B;
        }
        return s;
    }
    
    public static boolean D(final String s) {
        return s == null || (s.indexOf("%SUBMIT%") == -1 && s.indexOf("%PROGRESS%") == -1 && s.indexOf("%MARKED%") == -1 && s.indexOf("%TIME%") == -1 && s.indexOf("%KEYWORD%") == -1);
    }
    
    public int A(final Graphics graphics, final String s) {
        return graphics.getFontMetrics().stringWidth(s);
    }
}
