// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import a.b;
import neat.kb;
import neat.cb;
import neat.eb;
import neat.system.f;
import a.hb;

public class sc extends hb
{
    private static f h;
    public eb i;
    public neat.cb j;
    public neat.cb k;
    public neat.cb l;
    public neat.cb m;
    public int n;
    public int o;
    public neat.cb p;
    public int q;
    public int r;
    public neat.cb s;
    public int t;
    public int u;
    public neat.cb v;
    public neat.cb w;
    public int x;
    public int y;
    public neat.cb z;
    public neat.cb A;
    public neat.cb B;
    public neat.cb C;
    public int D;
    public int E;
    public eb F;
    public neat.cb G;
    public neat.cb H;
    public neat.cb I;
    public kb J;
    public kb K;
    public int L;
    public int M;
    public float N;
    public kb O;
    public int P;
    public int Q;
    public float R;
    public kb S;
    private static /* synthetic */ Class T;
    private static String U;
    
    public b a() {
        return bingo.r.L();
    }
    
    public void b() {
        sc.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)sc.h.a();
    }
    
    public static sc a() {
        return (sc)sc.h.a();
    }
    
    public void g() {
        super.g();
        this.n = 0;
        this.o = 0;
        this.q = 0;
        this.r = 0;
        this.t = 0;
        this.u = 0;
        this.x = 0;
        this.y = 0;
        this.D = 0;
        this.E = 0;
        this.L = 0;
        this.M = 0;
        this.N = 1.0f;
        this.P = 0;
        this.Q = 0;
        this.R = 1.0f;
    }
    
    public void h() {
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.j != null) {
            this.j.f();
            this.j = null;
        }
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
        if (this.s != null) {
            this.s.f();
            this.s = null;
        }
        if (this.v != null) {
            this.v.f();
            this.v = null;
        }
        if (this.w != null) {
            this.w.f();
            this.w = null;
        }
        if (this.z != null) {
            this.z.f();
            this.z = null;
        }
        if (this.A != null) {
            this.A.f();
            this.A = null;
        }
        if (this.B != null) {
            this.B.f();
            this.B = null;
        }
        if (this.C != null) {
            this.C.f();
            this.C = null;
        }
        if (this.F != null) {
            this.F.f();
            this.F = null;
        }
        if (this.G != null) {
            this.G.f();
            this.G = null;
        }
        if (this.H != null) {
            this.H.f();
            this.H = null;
        }
        if (this.I != null) {
            this.I.f();
            this.I = null;
        }
        if (this.J != null) {
            this.J.f();
            this.J = null;
        }
        if (this.K != null) {
            this.K.f();
            this.K = null;
        }
        if (this.O != null) {
            this.O.f();
            this.O = null;
        }
        if (this.S != null) {
            this.S.f();
            this.S = null;
        }
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public sc() {
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.p = null;
        this.s = null;
        this.v = null;
        this.w = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.O = null;
        this.S = null;
    }
    
    static {
        final char[] charArray = ")\u0019Ht&e\u0003E".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'K';
                            break;
                        }
                        case 1: {
                            c2 = 'p';
                            break;
                        }
                        case 2: {
                            c2 = '&';
                            break;
                        }
                        case 3: {
                            c2 = '\u0013';
                            break;
                        }
                        default: {
                            c2 = 'I';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                sc.U = new String(charArray).intern();
                sc.h = new f((sc.T != null) ? sc.T : (sc.T = a(sc.U)));
                return;
            }
            continue;
        }
    }
}
