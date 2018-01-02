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
    public static final String O = "SansSerif";
    public static final String \u00cb = "%SUBMIT%";
    public static final String \u00cd = "%MARKED%";
    public static final String º = "%PROGRESS%";
    public static final String E = "%TIME%";
    public static final String \u00ce = "%CHARSET%";
    public static final String \u00c4 = "%KEYWORD%";
    public String A;
    public String £;
    public String D;
    public boolean \u00c0;
    public int F;
    public boolean \u00c1;
    public boolean U;
    public boolean _;
    public boolean r;
    public int \u00d0;
    public boolean \u00c7;
    public C H;
    public boolean s;
    public String q;
    public static final int ª = 0;
    public static final int p = 1;
    public static final int a = 2;
    public int K;
    public boolean ¢;
    public boolean S;
    public Font L;
    public int \u00c5;
    public int J;
    public Color u;
    public Color z;
    public boolean Q;
    public boolean ¤;
    public boolean N;
    public Color \u00cf;
    public String \u00ca;
    public String g;
    public boolean w;
    public boolean V;
    public String f;
    public String \u00c6;
    public String c;
    public String o;
    public boolean n;
    public Color \u00c9;
    public Color \u00c2;
    public Color W;
    public Image e;
    public String µ;
    public String y;
    public String v;
    public String I;
    public boolean ¥;
    public com.cc.D.A P;
    public com.cc.D.A x;
    private static final String k = "%CREATED%";
    private static final String T = "%TITLE%";
    private static final String d = "%CREATOR%";
    private static final String X = "%EDITOR%";
    private static final String Z = "%RIGHTS%";
    private static final String h = "%COPYRIGHT%";
    private static final String C = "%PUBLISHER%";
    private static final String M = "%DESCRIPTION%";
    public String G;
    public String t;
    public String b;
    public String i;
    public String \u00d1;
    public String \u00cc;
    public String l;
    public String B;
    public String \u00c8;
    public String j;
    public int \u00c3;
    public int R;
    public int m;
    public int Y;
    
    public A() {
        this.A = "Cp1252";
        this.£ = "Cp1252";
        this.D = "Cp1252";
        this.\u00c0 = false;
        this.F = 0;
        this.\u00c1 = true;
        this.U = false;
        this._ = false;
        this.r = false;
        this.\u00d0 = 10000;
        this.\u00c7 = false;
        this.H = new C();
        this.s = false;
        this.q = "";
        this.K = 0;
        this.L = new Font("SansSerif", 0, 12);
        this.\u00c5 = 200;
        this.J = 5;
        this.u = Color.red;
        this.z = Color.gray;
        this.Q = false;
        this.¤ = false;
        this.N = true;
        this.\u00cf = Color.blue;
        this.g = "[?]";
        this.w = false;
        this.o = "GET";
        this.n = false;
        this.\u00c9 = Color.black;
        this.\u00c2 = Color.white;
        this.W = Color.gray;
        this.¥ = false;
        this.G = "";
        this.t = "";
        this.b = "";
        this.i = "";
        this.\u00d1 = "";
        this.\u00cc = "";
        this.l = "";
        this.B = "";
        this.\u00c8 = "";
        this.j = "";
        this.m = 0;
        this.Y = 0;
    }
    
    public String A() {
        if (this.\u00cc.indexOf("\u00c2©") != -1) {
            return this.\u00cc;
        }
        return "\u00c2© " + this.\u00cc.trim();
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
            return this.t;
        }
        if (s.equalsIgnoreCase("%CREATOR%")) {
            return this.b;
        }
        if (s.equalsIgnoreCase("%EDITOR%")) {
            return this.i;
        }
        if (s.equalsIgnoreCase("%RIGHTS%")) {
            return this.\u00d1;
        }
        if (s.equalsIgnoreCase("%COPYRIGHT%")) {
            return this.\u00cc;
        }
        if (s.equalsIgnoreCase("%PUBLISHER%")) {
            return this.l;
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
