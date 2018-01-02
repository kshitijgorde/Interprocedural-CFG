// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import a.x;
import zylom.ZylomDataGather;
import neat.nb;
import neat.h;
import neat.system.graphics.renderer.m;
import a.db;
import neat.kb;
import a.y;
import neat.system.f;
import neat.system.cb;

public class a implements cb
{
    private static f a;
    private a.y b;
    public kb c;
    public int d;
    public kb e;
    public kb f;
    public kb g;
    public boolean h;
    public db i;
    public db j;
    public float k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public boolean r;
    public boolean s;
    public kb t;
    public kb u;
    public kb v;
    public kb w;
    public kb x;
    public kb y;
    public kb z;
    public kb A;
    public int B;
    public int C;
    public int D;
    public kb E;
    public kb F;
    public boolean G;
    public m H;
    public boolean I;
    public kb J;
    public kb K;
    public boolean L;
    public int M;
    public int N;
    public kb O;
    public int P;
    public kb Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public m V;
    public m W;
    public m X;
    public m Y;
    public m Z;
    public m ab;
    public int bb;
    private static /* synthetic */ Class cb;
    private static String db;
    
    public void a(final kb kb, final h h) {
        if (kb.equals(ld.COMMAND__LINE__TYPE__TEXT)) {
            this.d = 1;
        }
        else if (kb.equals(ld.COMMAND__LINE__TYPE__VARIABLE)) {
            this.d = 2;
            this.t = (kb)h.g(ld.COMMAND__LINE__TYPE__VARIABLE__VARIABLE);
            if (this.t != null) {
                this.t = this.t.b();
            }
        }
        else if (kb.equals(ld.COMMAND__LINE__TYPE__SELECT)) {
            this.d = 3;
            this.u = (kb)h.g(ld.COMMAND__LINE__TYPE__SELECT__VARIABLE);
            if (this.u != null) {
                this.u = this.u.b();
            }
            this.v = (kb)h.g(ld.COMMAND__LINE__TYPE__SELECT__VALUE);
            if (this.v != null) {
                this.v = this.v.b();
            }
        }
        else if (kb.equals(ld.COMMAND__LINE__TYPE__CHECKBOX)) {
            this.d = 4;
            this.w = (kb)h.g(ld.COMMAND__LINE__TYPE__CHECKBOX__VARIABLE);
            if (this.w != null) {
                this.w = this.w.b();
            }
            this.x = (kb)h.g(ld.VARIABLE__BUTTON__TRUE);
            if (this.x != null) {
                this.x = this.x.b();
            }
            this.y = (kb)h.g(ld.VARIABLE__BUTTON__FALSE);
            if (this.y != null) {
                this.y = this.y.b();
            }
        }
        else if (kb.equals(ld.COMMAND__LINE__TYPE__RADIO)) {
            this.d = 5;
            this.z = (kb)h.g(ld.COMMAND__LINE__TYPE__RADIO__VARIABLE);
            if (this.z != null) {
                this.z = this.z.b();
            }
        }
        else if (kb.equals(ld.COMMAND__LINE__TYPE__VALUE)) {
            this.d = 6;
            this.A = (kb)h.g(ld.COMMAND__LINE__TYPE__VALUE__VARIABLE);
            if (this.A != null) {
                this.A = this.A.b();
            }
            this.B = nb.b((kb)h.g(ld.COMMAND__LINE__TYPE__VALUE__CHANGE), 1);
            this.C = nb.b((kb)h.g(ld.COMMAND__LINE__TYPE__VALUE__MIN), 0);
            this.D = nb.b((kb)h.g(ld.COMMAND__LINE__TYPE__VALUE__MAX), 100);
            this.E = (kb)h.g(ld.COMMAND__LINE__TYPE__VALUE__PREFIX);
            if (this.E != null) {
                this.E = this.E.b();
            }
            this.F = (kb)h.g(ld.COMMAND__LINE__TYPE__VALUE__EXT);
            if (this.F != null) {
                this.F = this.F.b();
            }
            final kb kb2 = (kb)h.g(ld.COMMAND__LINE__TYPE__VALUE__IS_LOOPED);
            if (kb2 != null) {
                this.G = kb2.equals(ld.VARIABLE__BUTTON__TRUE);
            }
        }
        else {
            this.d = 0;
        }
    }
    
    public static a a(final a.y b) {
        final a a = (a)bingo.a.a.a();
        a.b = b;
        ZylomDataGather.GetHelper().indicateFinishedLoading();
        return a;
    }
    
    public void f() {
        bingo.a.a.a(this);
    }
    
    public void g() {
        this.d = 0;
        this.l = 0;
        this.m = 0;
        this.h = true;
        this.k = -1.0f;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = false;
        this.s = false;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.G = false;
        this.I = false;
        this.M = -1;
        this.N = -1;
        this.L = false;
        this.P = 0;
        this.R = 0;
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.bb = -1;
    }
    
    public void h() {
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
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
        if (this.i != null) {
            this.i.a(this.b);
            this.i = null;
        }
        if (this.j != null) {
            this.j.a(this.b);
            this.j = null;
        }
        if (this.t != null) {
            this.t.f();
            this.t = null;
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
        if (this.E != null) {
            this.E.f();
            this.E = null;
        }
        if (this.F != null) {
            this.F.f();
            this.F = null;
        }
        if (this.H != null) {
            if (this.I) {
                this.b.a(this.H);
            }
            else {
                this.H.a(false);
            }
            this.H = null;
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
        if (this.Q != null) {
            this.Q.f();
            this.Q = null;
        }
        if (this.V != null) {
            this.b.a(this.V);
            this.V = null;
        }
        if (this.W != null) {
            this.b.a(this.W);
            this.W = null;
        }
        if (this.X != null) {
            this.b.a(this.X);
            this.X = null;
        }
        if (this.Y != null) {
            this.b.a(this.Y);
            this.Y = null;
        }
        if (this.Z != null) {
            this.b.a(this.Z);
            this.Z = null;
        }
        if (this.ab != null) {
            this.b.a(this.ab);
            this.ab = null;
        }
        this.b = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public a() {
        this.b = null;
        this.c = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.i = null;
        this.j = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.E = null;
        this.F = null;
        this.H = null;
        this.J = null;
        this.K = null;
        this.O = null;
        this.Q = null;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.ab = null;
    }
    
    static {
        final char[] charArray = ":\u0007!\u0000Yv\u000f".toCharArray();
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
                            c2 = 'X';
                            break;
                        }
                        case 1: {
                            c2 = 'n';
                            break;
                        }
                        case 2: {
                            c2 = 'O';
                            break;
                        }
                        case 3: {
                            c2 = 'g';
                            break;
                        }
                        default: {
                            c2 = '6';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                bingo.a.db = new String(charArray).intern();
                bingo.a.a = new f((bingo.a.cb != null) ? bingo.a.cb : (bingo.a.cb = a(bingo.a.db)));
                return;
            }
            continue;
        }
    }
}
