import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class bn
{
    static Color a;
    static Color b;
    static Color c;
    static Color d;
    static Color e;
    static Color f;
    static Color g;
    static Color h;
    static Color i;
    static Color j;
    static Color k;
    static Color l;
    static Color m;
    static Color n;
    static Color o;
    static Color p;
    static Color q;
    static Color r;
    static Color s;
    static Color t;
    static Color u;
    static Color v;
    static Color w;
    static Color x;
    static Color y;
    static Color z;
    static Color A;
    static Color B;
    static Color C;
    static Color D;
    static Color E;
    static Color F;
    static Color G;
    static Color H;
    static Color I;
    static Color J;
    static Color K;
    static Color L;
    static Color M;
    static Color N;
    static Color[] O;
    static String[] P;
    
    static {
        bn.a = new Color(0, 100, 0);
        bn.b = new Color(255, 15, 237);
        bn.c = Color.black;
        bn.d = Color.black;
        bn.e = Color.black;
        bn.f = bn.a;
        bn.g = Color.red;
        bn.h = bn.a;
        bn.i = Color.red;
        bn.j = bn.a;
        bn.k = bn.a;
        bn.l = bn.a;
        bn.m = Color.red;
        bn.n = bn.a;
        bn.o = Color.red;
        bn.p = bn.b;
        bn.q = Color.red;
        bn.r = Color.lightGray;
        bn.s = Color.lightGray;
        bn.t = Color.red;
        bn.u = Color.black;
        bn.v = Color.blue;
        bn.x = new Color(240, 240, 255);
        bn.y = new Color(240, 240, 255);
        bn.z = Color.black;
        bn.A = new Color(240, 240, 255);
        bn.B = Color.black;
        bn.C = new Color(240, 240, 255);
        bn.D = Color.black;
        bn.E = Color.black;
        bn.F = Color.white;
        bn.G = bn.y;
        bn.H = bn.z;
        bn.I = Color.black;
        bn.J = Color.red;
        bn.K = Color.white;
        bn.L = Color.blue;
        bn.M = Color.blue;
        bn.N = Color.blue;
        bn.O = new Color[] { Color.white, new Color(0, 0, 0), new Color(0, 0, 127), new Color(0, 147, 0), new Color(255, 0, 0), new Color(127, 0, 0), new Color(156, 0, 156), new Color(252, 127, 0), new Color(255, 255, 0), new Color(0, 252, 0), new Color(0, 147, 147), new Color(0, 255, 255), new Color(0, 0, 252), new Color(255, 0, 255), new Color(127, 127, 127), new Color(210, 210, 210) };
        bn.P = new String[bn.O.length + 1];
    }
    
    static Color a(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return null;
        }
        if (int1 >= 0 && int1 <= 15) {
            return bn.O[int1];
        }
        return null;
    }
    
    static Color b(final String s) {
        if (s == null) {
            return null;
        }
        Color color;
        try {
            color = new Color(Integer.parseInt(s, 16));
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return color;
    }
}
