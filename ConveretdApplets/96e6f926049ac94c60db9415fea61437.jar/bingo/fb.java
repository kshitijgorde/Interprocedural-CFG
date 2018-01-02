// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class fb extends bb
{
    private static f d;
    public neat.cb e;
    public neat.cb f;
    public neat.cb g;
    public neat.cb h;
    public neat.cb i;
    public neat.cb j;
    public int k;
    public int l;
    public neat.cb m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public neat.cb u;
    public neat.cb v;
    public neat.cb w;
    public neat.cb x;
    public neat.cb y;
    public neat.cb z;
    public neat.cb A;
    public neat.cb B;
    public neat.cb C;
    public neat.cb D;
    public neat.cb E;
    public kb F;
    public lb G;
    public pb H;
    public ub I;
    public sb J;
    public ib K;
    public hb L;
    public gb M;
    private static /* synthetic */ Class N;
    private static String O;
    
    public void b() {
        fb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)fb.d.a();
    }
    
    public static fb a() {
        return (fb)fb.d.a();
    }
    
    public void g() {
        super.g();
        this.k = 0;
        this.l = 0;
        this.n = 0;
        this.o = 0;
        this.p = -1;
        this.q = -1;
        this.r = 1;
        this.s = 0;
        this.t = 0;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        if (this.h != null) {
            this.h.f();
            this.h = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.j != null) {
            this.j.f();
            this.j = null;
        }
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.u != null) {
            this.u.f();
            this.u = null;
        }
        if (this.v != null) {
            this.v.f();
            this.v = null;
        }
        if (this.w != null) {
            this.w.f();
            this.w = null;
        }
        if (this.x != null) {
            this.x.f();
            this.x = null;
        }
        if (this.y != null) {
            this.y.f();
            this.y = null;
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
        if (this.D != null) {
            this.D.f();
            this.D = null;
        }
        if (this.E != null) {
            this.E.f();
            this.E = null;
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
        if (this.L != null) {
            this.L.f();
            this.L = null;
        }
        if (this.M != null) {
            this.M.f();
            this.M = null;
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
    
    public fb() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.m = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
    }
    
    static {
        final char[] charArray = "\u0013y}G~_vq".toCharArray();
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
                            c2 = 'q';
                            break;
                        }
                        case 1: {
                            c2 = '\u0010';
                            break;
                        }
                        case 2: {
                            c2 = '\u0013';
                            break;
                        }
                        case 3: {
                            c2 = ' ';
                            break;
                        }
                        default: {
                            c2 = '\u0011';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                fb.O = new String(charArray).intern();
                fb.d = new f((fb.N != null) ? fb.N : (fb.N = a(fb.O)));
                return;
            }
            continue;
        }
    }
}
