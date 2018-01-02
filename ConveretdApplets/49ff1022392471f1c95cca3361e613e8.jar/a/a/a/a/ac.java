// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.util.Properties;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.Component;
import java.awt.Toolkit;

public class ac
{
    static final char[] V;
    static final char[] U;
    static final char[] p;
    static final char[] aj;
    static final char[] t;
    static final char[] void;
    static final char[] goto;
    static final char[] D;
    static final char[] z;
    static final char[] ae;
    static final char[] case;
    static final char[] ac;
    static final char[] L;
    static final char[] A;
    static final char[] T;
    static final char[] R;
    static final char[] f;
    static final char[] W;
    static final char[] i;
    static final char[] new;
    static final char[] j;
    static final char[] u;
    static final char[] x;
    static final char[] s;
    static final char[] G;
    static final char[] try;
    static final char[] ao;
    static final char[] H;
    static final char[] K;
    static final char[] g;
    static final char[] ap;
    static final char[] B;
    static final char[] N;
    static final char[] l;
    static final char[] int;
    static final char[] m;
    static final char[] O;
    static final char[] k;
    static final char[] long;
    static final char[] y;
    static final char[] ak;
    static final char[] do;
    static final char[] o;
    static final char[] aa;
    static final char[] char;
    static final char[] r;
    static final char[] X;
    static final char[] am;
    static final char[] Q;
    static final String v = "Can't open current package because of time restriction.";
    public l byte;
    public int al;
    public static float e;
    public Toolkit C;
    public ap P;
    public static Component F;
    public be w;
    public av S;
    public au n;
    public Applet J;
    public static final double q = 6.283185307179586;
    public static final double d = 1.5707963267948966;
    public MediaTracker if;
    public int b;
    public ad else;
    public boolean M;
    public boolean E;
    public boolean h;
    public static int ab;
    public boolean for;
    public static String I;
    public int Z;
    public float ai;
    public boolean ah;
    public Properties c;
    public static boolean Y;
    float[][] ag;
    float[][] af;
    float[][] ad;
    float[][] a;
    float[] an;
    
    static {
        V = new char[] { 'p', 'o', 's', 'x', '\0' };
        U = new char[] { 'p', 'o', 's', 'y', '\0' };
        p = new char[] { 'w', 'i', 'd', 't', 'h', '\0' };
        aj = new char[] { 'h', 'e', 'i', 'g', 'h', 't', '\0' };
        t = new char[] { 'm', 'o', 'u', 's', 'e', 'p', 'r', 'e', 's', 's', '\0' };
        void = new char[] { 'm', 'o', 'u', 's', 'e', 'c', 'l', 'i', 'c', 'k', '\0' };
        goto = new char[] { 'm', 'o', 'u', 's', 'e', 'e', 'n', 't', 'e', 'r', '\0' };
        D = new char[] { 'm', 'o', 'u', 's', 'e', 'e', 'x', 'i', 't', '\0' };
        z = new char[] { 'm', 'o', 'u', 's', 'e', 'r', 'e', 'l', 'e', 'a', 's', 'e', '\0' };
        ae = new char[] { 'c', 'h', 'e', 'c', 'k', '\0' };
        case = new char[] { 'c', 'h', 'e', 'c', 'k', 'e', 'd', '\0' };
        ac = new char[] { 'u', 'n', 'c', 'h', 'e', 'c', 'k', '\0' };
        L = new char[] { 'm', 'o', 'u', 's', 'e', 'e', 'n', 't', 'e', 'r', 'c', 'h', 'e', 'c', 'k', 'e', 'd', '\0' };
        A = new char[] { 'm', 'o', 'u', 's', 'e', 'e', 'n', 't', 'e', 'r', 'u', 'n', 'c', 'h', 'e', 'c', 'k', 'e', 'd', '\0' };
        T = new char[] { 'm', 'o', 'u', 's', 'e', 'e', 'x', 'i', 't', 'c', 'h', 'e', 'c', 'k', 'e', 'd', '\0' };
        R = new char[] { 'm', 'o', 'u', 's', 'e', 'e', 'x', 'i', 't', 'u', 'n', 'c', 'h', 'e', 'c', 'k', 'e', 'd', '\0' };
        f = new char[] { 'l', 'o', 'o', 'p', '\0' };
        W = new char[] { 'p', 'l', 'a', 'y', '\0' };
        i = new char[] { 's', 't', 'o', 'p', '\0' };
        new = new char[] { 'p', 'a', 'u', 's', 'e', '\0' };
        j = new char[] { 'p', 'l', 'a', 'y', 'n', 'o', 'd', 'e', '\0' };
        u = new char[] { 'a', 'd', 'd', 'n', 'o', 'd', 'e', '\0' };
        x = new char[] { 'r', 'm', 'n', 'o', 'd', 'e', '\0' };
        s = new char[1];
        G = new char[] { 'p', 'a', 'n', '\0' };
        try = new char[] { 't', 'i', 'l', 't', '\0' };
        ao = new char[] { 'r', 'o', 'l', 'l', '\0' };
        H = new char[] { 'f', 'o', 'v', '\0' };
        K = new char[] { 'v', 'i', 's', 'i', 'b', 'l', 'e', '\0' };
        g = new char[] { 'd', 'i', 's', 'p', 'l', 'a', 'y', '\0' };
        ap = new char[] { 'l', 'a', 'y', 'e', 'r', '\0' };
        B = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 's', '\0' };
        N = new char[] { 'l', 'i', 'g', 'h', 't', 's', '\0' };
        l = new char[] { 's', 'o', 'u', 'n', 'd', 's', '\0' };
        int = new char[] { 'p', 'a', 'n', 'o', 'r', 'a', 'm', 'a', 's', '\0' };
        m = new char[] { 'p', 'a', 'n', 'o', 'r', 'a', 'm', 'a', '\0' };
        O = new char[] { 'g', 'u', 'i', '\0' };
        k = new char[] { 't', 'e', 'x', 't', '\0' };
        long = new char[] { 'n', 'o', 't', ' ', 's', 'e', 't', '\0' };
        y = new char[] { 'a', 'd', 'd', 'o', 'b', 'j', 'e', 'c', 't', '\0' };
        ak = new char[] { 'r', 'm', 'o', 'b', 'j', 'e', 'c', 't', '\0' };
        do = new char[] { 't', 'y', 'p', 'e', '\0' };
        o = new char[] { 'c', 'h', 'i', 'l', 'd', 'o', 'b', 'j', 'e', 'c', 't', 's', '\0' };
        aa = new char[] { 'c', 'o', 'l', 'o', 'r', '\0' };
        char = new char[] { 'a', 'n', 'g', 'l', 'e', 't', 'y', 'p', 'e', '\0' };
        r = new char[] { 'd', 'e', 'g', 'r', 'e', 'e', '\0' };
        X = new char[] { 'd', 'e', 'g', 'r', 'e', 'e', '\0' };
        am = new char[] { 'm', 'a', 'n', 'a', 'g', 'e', 't', 'r', 'a', 'n', 's', 'p', 'a', 'r', 'e', 'n', 'c', 'y', '\0' };
        Q = new char[] { 'f', 'i', 'l', 'e', 'n', 'a', 'm', 'e', '\0' };
        a.a.a.a.ac.e = 0.9f;
        a.a.a.a.ac.ab = 0;
        a.a.a.a.ac.I = "ImmerId";
        a.a.a.a.ac.Y = true;
    }
    
    public static int a(final String s) {
        final String lowerCase = s.toLowerCase();
        if (s.startsWith("0x") | s.startsWith("#")) {
            int n = 0;
            String s2;
            if (s.startsWith("0x")) {
                s2 = lowerCase.substring(2);
            }
            else {
                s2 = lowerCase.substring(1);
            }
            for (int i = 0; i < s2.length(); ++i) {
                if (s2.charAt(i) >= 'a' && s2.charAt(i) <= 'f') {
                    n = (n << 4) + s2.charAt(i) - 97 + 10;
                }
                else if (s2.charAt(i) >= '0' && s2.charAt(i) <= '9') {
                    n = (n << 4) + s2.charAt(i) - 48;
                }
            }
            return n;
        }
        try {
            return new Integer(s);
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    ac() {
        this.al = -2;
        this.b = 0;
        this.M = false;
        this.E = false;
        this.h = true;
        this.for = false;
        this.Z = 0;
        this.ai = 100.0f;
        this.ah = false;
        this.c = null;
        this.ag = this.a();
        this.af = this.a();
        this.ad = this.a();
        this.a = this.a();
    }
    
    float[][] a() {
        return new float[3][3];
    }
    
    void a(final float n, final float n2, final float n3, final float[][] array) {
        this.a(this.ag);
        this.a(this.af);
        this.a(this.ad);
        this.ag[0][0] = 1.0f;
        this.ag[2][2] = (float)Math.cos(-n3);
        this.ag[1][1] = this.ag[2][2];
        this.ag[1][2] = (float)Math.sin(-n3);
        this.ag[2][1] = -this.ag[1][2];
        this.af[1][1] = 1.0f;
        this.af[2][2] = (float)Math.cos(n2);
        this.af[0][0] = this.af[2][2];
        this.af[0][2] = (float)Math.sin(n2);
        this.af[2][0] = -this.af[0][2];
        this.ad[2][2] = 1.0f;
        this.ad[1][1] = (float)Math.cos(n);
        this.ad[0][0] = this.ad[1][1];
        this.ad[0][1] = (float)Math.sin(n);
        this.ad[1][0] = -this.ad[0][1];
        this.a(this.ad, this.af, this.a);
        this.a(this.a, this.ag, array);
    }
    
    void a(final float[][] array) {
        array[1][0] = 0.0f;
        array[2][0] = 0.0f;
        array[0][1] = 0.0f;
        array[0][2] = 0.0f;
    }
    
    void a(final float[][] array, final float[][] array2, final float[][] array3) {
        array3[0][0] = array[0][0] * array2[0][0] + array[1][0] * array2[0][1] + array[2][0] * array2[0][2];
        array3[1][0] = array[0][0] * array2[1][0] + array[1][0] * array2[1][1] + array[2][0] * array2[1][2];
        array3[2][0] = array[0][0] * array2[2][0] + array[1][0] * array2[2][1] + array[2][0] * array2[2][2];
        array3[0][1] = array[0][1] * array2[0][0] + array[1][1] * array2[0][1] + array[2][1] * array2[0][2];
        array3[1][1] = array[0][1] * array2[1][0] + array[1][1] * array2[1][1] + array[2][1] * array2[1][2];
        array3[2][1] = array[0][1] * array2[2][0] + array[1][1] * array2[2][1] + array[2][1] * array2[2][2];
        array3[0][2] = array[0][2] * array2[0][0] + array[1][2] * array2[0][1] + array[2][2] * array2[0][2];
        array3[1][2] = array[0][2] * array2[1][0] + array[1][2] * array2[1][1] + array[2][2] * array2[1][2];
        array3[2][2] = array[0][2] * array2[2][0] + array[1][2] * array2[2][1] + array[2][2] * array2[2][2];
    }
    
    void a(final float[][] array, final aq aq, final aq aq2) {
        aq2.try = array[0][0] * aq.try + array[1][0] * aq.if + array[2][0] * aq.byte;
        aq2.if = array[0][1] * aq.try + array[1][1] * aq.if + array[2][1] * aq.byte;
        aq2.byte = array[0][2] * aq.try + array[1][2] * aq.if + array[2][2] * aq.byte;
    }
    
    public float a(float n, final float n2) {
        while (n - n2 >= 3.141592653589793) {
            n -= 6.283185307179586;
        }
        while (n - n2 <= -3.141592653589793) {
            n += 6.283185307179586;
        }
        return n;
    }
    
    public void if(final a8[] array, final int n, final int n2) {
        int i = n;
        int n3 = n2;
        if (n2 > n) {
            final int c = array[(n + n2) / 2].c;
            while (i <= n3) {
                while (i < n2) {
                    if (array[i].c >= c) {
                        break;
                    }
                    ++i;
                }
                while (n3 > n && array[n3].c > c) {
                    --n3;
                }
                if (i <= n3) {
                    this.a(array, i, n3);
                    ++i;
                    --n3;
                }
            }
            if (n < n3) {
                this.if(array, n, n3);
            }
            if (i < n2) {
                this.if(array, i, n2);
            }
        }
    }
    
    public int a(final a8[] array, final char[] array2, final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            if (array[i].e != null && a.a.a.a.g.a(array[i].e, array2) == 0) {
                array[i].if();
                array[i].e = null;
                array[i] = null;
            }
            else {
                array[n2] = array[i];
                ++n2;
            }
        }
        return n2;
    }
    
    private void a(final a8[] array, final int n, final int n2) {
        final a8 a8 = array[n];
        array[n] = array[n2];
        array[n2] = a8;
    }
    
    static final a3 a(final a8[] array, final int goto1) {
        final a3 a3 = new a3();
        a3.char = 5;
        if (goto1 == 0) {
            a3.goto = 0;
        }
        else {
            a3.goto = goto1;
            a3.a = new a3[goto1];
            for (int i = 0; i < goto1; ++i) {
                a3.a[i] = new a3();
                a3.a[i].char = 4;
                a3.a[i].int = array[i].e;
            }
        }
        return a3;
    }
    
    public static a3 a(final a3 a3) {
        a3.char = 4;
        a3.int = a.a.a.a.ac.long;
        return a3;
    }
}
