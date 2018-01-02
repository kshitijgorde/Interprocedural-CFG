// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.j;
import neat.system.l;
import a.gb;
import neat.lb;
import neat.nb;
import a.yb;
import a.x;
import neat.bb;
import neat.cb;
import neat.h;
import a.z;
import neat.system.graphics.renderer.m;
import neat.system.graphics.renderer.b;
import neat.kb;
import neat.system.f;
import a.i;
import a.n;
import a.o;
import a.q;
import a.s;

public class r extends p implements s, q, o, a.p, n, i
{
    private static f l;
    private kb m;
    private neat.system.graphics.renderer.b n;
    private m o;
    private int p;
    private int q;
    private int r;
    private int s;
    private m t;
    private m u;
    private m v;
    private m w;
    private int x;
    private int y;
    private int z;
    private int A;
    private m B;
    private m C;
    private m D;
    private m E;
    private int F;
    private int G;
    private int H;
    private int I;
    private z J;
    private z K;
    private z L;
    public int M;
    public int N;
    public int O;
    public int P;
    private int Q;
    private int R;
    private boolean S;
    private boolean T;
    private db U;
    private a.db V;
    private a.db W;
    private h X;
    private h Y;
    private m Z;
    private int ab;
    private int bb;
    private boolean cb;
    private h db;
    private boolean eb;
    private Object fb;
    private static /* synthetic */ Class gb;
    private static String[] hb;
    
    private void a(final kb kb) {
        if (this.m != null) {
            if (this.m.equals(kb)) {
                return;
            }
        }
        else if (kb == null) {
            return;
        }
        this.b();
        final sc sc = (sc)this.a();
        if (sc.i == null) {
            return;
        }
        if (sc.i.a() <= 0) {
            return;
        }
        neat.cb cb = null;
        if (kb != null) {
            cb = (neat.cb)sc.i.c(kb);
        }
        if (cb == null) {
            cb = (neat.cb)sc.i.c(0);
        }
        if (cb == null) {
            throw new RuntimeException(bingo.r.hb[3] + kb + bingo.r.hb[4] + sc + ")");
        }
        final neat.bb a = cb.a();
        if (!(a instanceof cc)) {
            throw new RuntimeException(bingo.r.hb[5] + kb + bingo.r.hb[1] + cb + ")");
        }
        final cc cc = (cc)a;
        if (cc.e != null) {
            this.n = this.i.d(cc.e);
            if (this.n != null) {
                if (cc.f != null) {
                    this.i.a(this.n, cc.f);
                }
                this.n.a(true);
            }
            this.J();
        }
        if (cc.g != null) {
            this.o = this.i.b(cc.g);
            if (this.o == null) {
                throw new RuntimeException(bingo.r.hb[0] + cc.g);
            }
            this.o.a(false);
            this.p = this.o.c();
            this.q = this.o.d();
            this.r = this.o.e();
            this.s = this.o.f();
        }
        if (cc.h != null) {
            this.t = this.i.b(cc.h);
            if (this.t == null) {
                throw new RuntimeException(bingo.r.hb[0] + cc.h);
            }
            this.t.a(false);
            this.x = this.t.f();
        }
        if (cc.i != null) {
            this.u = this.i.b(cc.i);
            if (this.u == null) {
                throw new RuntimeException(bingo.r.hb[0] + cc.i);
            }
            this.u.a(false);
            this.y = this.u.f();
        }
        if (cc.j != null) {
            this.v = this.i.b(cc.j);
            if (this.v == null) {
                throw new RuntimeException(bingo.r.hb[0] + cc.j);
            }
            this.v.a(false);
            this.z = this.v.e();
        }
        if (cc.k != null) {
            this.w = this.i.b(cc.k);
            if (this.w == null) {
                throw new RuntimeException(bingo.r.hb[0] + cc.k);
            }
            this.w.a(false);
            this.A = this.v.e();
        }
        if (cc.l != null) {
            this.B = this.i.b(cc.l);
            if (this.B == null) {
                throw new RuntimeException(bingo.r.hb[0] + cc.l);
            }
            this.B.a(-1.0f, 1.0f);
            this.B.a(false);
            this.F = this.B.f();
        }
        if (cc.m != null) {
            this.C = this.i.b(cc.m);
            if (this.C == null) {
                throw new RuntimeException(bingo.r.hb[0] + cc.m);
            }
            this.C.a(-1.0f, 1.0f);
            this.C.a(false);
            this.G = this.C.f();
        }
        if (cc.n != null) {
            this.D = this.i.b(cc.n);
            if (this.D == null) {
                throw new RuntimeException(bingo.r.hb[0] + cc.n);
            }
            this.D.a(-1.0f, 1.0f);
            this.D.a(false);
            this.H = this.D.e();
        }
        if (cc.o != null) {
            this.E = this.i.b(cc.o);
            if (this.E == null) {
                throw new RuntimeException(bingo.r.hb[0] + cc.o);
            }
            this.E.a(-1.0f, 1.0f);
            this.E.a(false);
            this.I = this.D.e();
        }
        if (cc.p < 0 || cc.q < 0 || cc.r <= 0 || cc.s <= 0) {
            throw new RuntimeException(bingo.r.hb[2] + cc);
        }
        this.M = cc.p;
        this.N = cc.q;
        this.O = cc.r;
        this.P = cc.s;
        cc.f();
        if (kb != null) {
            this.m = kb.b();
        }
        this.p();
    }
    
    private void b() {
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.o != null) {
            this.i.a(this.o);
            this.o = null;
        }
        if (this.t != null) {
            this.i.a(this.t);
            this.t = null;
        }
        if (this.u != null) {
            this.i.a(this.u);
            this.u = null;
        }
        if (this.v != null) {
            this.i.a(this.v);
            this.v = null;
        }
        if (this.w != null) {
            this.i.a(this.w);
            this.w = null;
        }
        if (this.B != null) {
            this.i.a(this.B);
            this.B = null;
        }
        if (this.C != null) {
            this.i.a(this.C);
            this.C = null;
        }
        if (this.D != null) {
            this.i.a(this.D);
            this.D = null;
        }
        if (this.E != null) {
            this.i.a(this.E);
            this.E = null;
        }
        if (this.n != null) {
            this.n.a(false);
            this.i.a(this.n);
            this.n = null;
        }
        this.i.b().n();
    }
    
    public void c() {
        this.k();
        final sc sc = (sc)this.a();
        if (sc.G != null) {
            this.J = this.i.b(sc.G);
            if (this.J == null) {
                throw new RuntimeException(bingo.r.hb[10] + sc.G);
            }
        }
        if (sc.H != null) {
            this.K = this.i.b(sc.H);
            if (this.K == null) {
                throw new RuntimeException(bingo.r.hb[11] + sc.H);
            }
        }
        if (sc.I != null) {
            this.L = this.i.b(sc.I);
            if (this.L == null) {
                throw new RuntimeException(bingo.r.hb[9] + sc.I);
            }
        }
        this.t();
    }
    
    public void k() {
        this.z();
        if (this.J != null) {
            this.i.a(this.J);
            this.J = null;
        }
        if (this.K != null) {
            this.i.a(this.K);
            this.K = null;
        }
        if (this.L != null) {
            this.i.a(this.L);
            this.L = null;
        }
        this.K();
    }
    
    public void a(final float n) {
        if (this.o != null) {
            this.o.a(n, 1.0f - n);
        }
        if (this.t != null) {
            this.t.a(n, 1.0f - n);
        }
        if (this.u != null) {
            this.u.a(n, 1.0f - n);
        }
        if (this.v != null) {
            this.v.a(n, 1.0f - n);
        }
        if (this.E != null) {
            this.E.a(n, 1.0f - n);
        }
        if (this.B != null) {
            this.B.a(-n, 1.0f);
        }
        if (this.C != null) {
            this.C.a(-n, 1.0f);
        }
        if (this.D != null) {
            this.D.a(-n, 1.0f);
        }
        if (this.E != null) {
            this.E.a(-n, 1.0f);
        }
    }
    
    public void l() {
        this.a(1.0f);
    }
    
    z a(final int n) {
        if (n == 2 && this.K != null) {
            return this.K;
        }
        if (n == 3 && this.L != null) {
            return this.L;
        }
        return this.J;
    }
    
    z m() {
        return this.J;
    }
    
    void a(final z z, final kb kb) {
        z.a(this.i, kb);
    }
    
    private void p() {
        if (this.o == null) {
            return;
        }
        final int n = 0;
        final int r = this.r();
        final int n2 = this.p + n;
        final int n3 = this.q + r;
        final int n4 = this.p + this.r - n;
        final int n5 = this.q + this.s - r;
        this.o.a(n2, n3);
        this.o.a(0, 0, n4 - n2, n5 - n3);
        this.o.b(n4 - n2, n5 - n3);
        int n6 = 0;
        if (this.t != null) {
            final int e = this.t.e();
            final int n7 = this.x - r * 2;
            this.t.a(n2 - e, n3);
            this.t.a(0, 0, e, n7);
            this.t.b(e, n7);
            n6 = e;
        }
        if (this.u != null) {
            final int e2 = this.u.e();
            final int n8 = this.y - r * 2;
            this.u.a(n4, n3);
            this.u.a(0, 0, e2, n8);
            this.u.b(e2, n8);
        }
        if (this.v != null) {
            this.v.a(n2 - n6, n3 - this.v.f());
        }
        if (this.w != null) {
            this.w.a(n2 - n6, n5);
        }
        int n9 = 0;
        if (this.B != null) {
            final int e3 = this.B.e();
            final int n10 = this.F - r * 2;
            this.B.a(n2 - e3, n3);
            this.B.a(0, 0, e3, n10);
            this.B.b(e3, n10);
            n9 = e3;
        }
        if (this.C != null) {
            final int e4 = this.C.e();
            final int n11 = this.G - r * 2;
            this.C.a(n4, n3);
            this.C.a(0, 0, e4, n11);
            this.C.b(e4, n11);
        }
        if (this.D != null) {
            this.D.a(n2 - n9, n3 - this.D.f());
        }
        if (this.E != null) {
            this.E.a(n2 - n9, n5);
        }
    }
    
    public int q() {
        return this.Q;
    }
    
    public int r() {
        return this.R;
    }
    
    void a(final int q, final int r) {
        if (this.Q != q || this.R != r) {
            final int n = q - this.Q;
            final int n2 = r - this.R;
            this.Q = q;
            this.R = r;
            this.b(n, n2);
        }
    }
    
    public void s() {
        if (this.S && !this.T) {
            return;
        }
        this.S = true;
        this.T = false;
        this.eb = true;
        final t jb = this.a().jb();
        if (jb != null) {
            jb.s();
        }
        this.y();
        this.a().p();
    }
    
    public void t() {
        if (!this.S) {
            return;
        }
        this.S = false;
        this.T = false;
        this.eb = false;
        this.y();
        this.a().p();
    }
    
    public boolean u() {
        return this.S && !this.T;
    }
    
    public void v() {
        if (this.S) {
            this.T = true;
            this.C();
        }
    }
    
    public boolean w() {
        return this.T || !this.S;
    }
    
    public void x() {
        if (this.a().w()) {
            this.z();
            this.t();
        }
        else {
            this.v();
        }
    }
    
    private void y() {
        this.C();
    }
    
    public db a(final neat.cb cb) {
        final neat.bb a = cb.a();
        if (!(a instanceof yb)) {
            throw new RuntimeException(bingo.r.hb[7] + a);
        }
        final db a2 = this.a((yb)a);
        cb.c();
        return a2;
    }
    
    public db a(final yb yb) {
        this.z();
        (this.U = bingo.db.b(this)).a(yb);
        this.U.a(true);
        this.l();
        return this.U;
    }
    
    public void z() {
        this.b(this.fb);
        this.D();
        this.G();
        this.K();
        if (this.U != null) {
            this.U.f();
            this.U = null;
            this.C();
        }
        this.b();
    }
    
    public void a(final kb kb, final kb kb2) {
        if (this.U == null) {
            return;
        }
        this.U.a(kb, kb2);
    }
    
    public kb b(final kb kb) {
        if (this.U == null) {
            return null;
        }
        return this.U.d(kb);
    }
    
    public void A() {
        if (this.U == null) {
            return;
        }
        this.U.d();
    }
    
    public void B() {
        this.b(0);
    }
    
    private void b(final int n) {
        if (this.U != null) {
            this.U.c(n);
            final kb b = this.b(ld.VARIABLE__BUTTON__CLOSE_DEFAULT);
            if (b != null && b.equals(ld.VARIABLE__BUTTON__TRUE)) {
                return;
            }
            this.c(n);
        }
    }
    
    private void b(final int n, final int n2) {
        this.p();
        if (this.V != null) {
            this.V.b(this.i, n, n2);
        }
        if (this.W != null) {
            this.W.b(this.i, n, n2);
        }
        if (this.Z != null) {
            this.Z.a(this.Z.c() + n, this.Z.d() + n2);
        }
        final neat.r a = this.X.a();
        while (a.a()) {
            final a a2 = (a)this.X.g(a.b());
            if (a2.i != null) {
                a2.i.b(this.i, n, n2);
            }
            if (a2.H != null) {
                a2.H.a(a2.H.c() + n, a2.H.d() + n2);
            }
            if (a2.V != null) {
                a2.V.a(a2.V.c() + n, a2.V.d() + n2);
            }
            if (a2.W != null) {
                a2.W.a(a2.W.c() + n, a2.W.d() + n2);
            }
            if (a2.X != null) {
                a2.X.a(a2.X.c() + n, a2.X.d() + n2);
            }
            if (a2.Y != null) {
                a2.Y.a(a2.Y.c() + n, a2.Y.d() + n2);
            }
        }
        a.f();
    }
    
    void C() {
        boolean l = false;
        if (this.S && this.U != null) {
            l = this.U.l();
        }
        if (l) {
            this.a(this.U.x());
        }
        if (this.o != null) {
            this.o.a(this.S);
        }
        if (this.t != null) {
            this.t.a(this.S);
        }
        if (this.u != null) {
            this.u.a(this.S);
        }
        if (this.v != null) {
            this.v.a(this.S);
        }
        if (this.w != null) {
            this.w.a(this.S);
        }
        if (this.B != null) {
            this.B.a(this.S);
        }
        if (this.C != null) {
            this.C.a(this.S);
        }
        if (this.D != null) {
            this.D.a(this.S);
        }
        if (this.E != null) {
            this.E.a(this.S);
        }
        z z = this.m();
        kb n = null;
        if (l) {
            z = this.a(this.U.o());
            n = this.U.n();
        }
        kb p = null;
        if (l) {
            p = this.U.p();
        }
        final sc sc = (sc)this.a();
        int n2 = this.M + this.q();
        int n3 = this.N + this.r();
        int o = this.O;
        int p2 = this.P;
        if (this.U != null) {
            final int q = this.U.q();
            if (q > 0) {
                n2 += (o - q) / 2;
                o = q;
            }
            final int r = this.U.r();
            if (r > 0) {
                n3 += (p2 - r) / 2;
                p2 = r;
            }
        }
        if (this.V != null) {
            this.V.a(this.i);
            this.V = null;
        }
        if (this.W != null) {
            this.W.a(this.i);
            this.W = null;
        }
        if (p != null) {
            if (this.V == null) {
                this.V = z.b(this.i);
            }
            if (n != null) {
                this.V.a(this.i, n);
            }
            this.V.a(this.i, n2, n3);
            this.V.a(this.i, n2, n3, o, p2);
            this.V.b(this.i, p);
            this.V.e(this.i);
            this.V.a(this.i, true);
            if (sc.K != null) {
                if (this.W == null) {
                    this.W = z.b(this.i);
                }
                this.W.a(this.i, sc.K, true);
                this.W.a(this.i, n2 + sc.L, n3 + sc.M);
                this.W.a(this.i, n2 + sc.L, n3 + sc.M, o, p2);
                this.W.b(this.i, p);
                if (sc.N != 1.0f) {
                    this.W.b(this.i, sc.N, 1.0f);
                }
                this.W.a(this.i, this.V.b() - 1);
                this.W.e(this.i);
                this.W.a(this.i, true);
            }
        }
        this.G();
        if (l) {
            final kb v = this.U.v();
            if (v != null && sc.F != null) {
                final neat.cb cb = (neat.cb)sc.F.c(v);
                if (cb != null) {
                    this.Z = this.i.b(cb);
                    if (this.Z != null) {
                        this.Z.a(n2 + this.Z.c(), n3 + this.Z.d());
                        this.Z.a(true);
                    }
                }
            }
        }
        this.D();
        if (l) {
            final neat.r t = this.U.t();
            while (t.a()) {
                final h h = (h)t.b();
                final kb kb = (kb)h.g(ld.COMMAND__LINE__NAME);
                if (kb == null) {
                    continue;
                }
                final a a = bingo.a.a(this.i);
                this.X.a(kb.b(), a);
                a.c = kb.b();
                a.a((kb)h.g(ld.COMMAND__LINE__TYPE), h);
                a.e = (kb)h.g(ld.COMMAND__LINE__TEXT);
                if (a.e == null) {
                    final kb kb2 = (kb)h.g(ld.COMMAND__LINE__TEXT_VARIABLE);
                    if (kb2 != null) {
                        a.e = this.b(kb2);
                    }
                }
                if (a.e != null) {
                    a.e = a.e.b();
                }
                a.f = (kb)h.g(ld.COMMAND__LINE__NORMAL_TEXT);
                if (a.f != null) {
                    a.f = a.f.b();
                }
                a.g = (kb)h.g(ld.COMMAND__LINE__ACTIVE_TEXT);
                if (a.g != null) {
                    a.g = a.g.b();
                }
                final kb kb3 = (kb)h.g(ld.COMMAND__LINE__IS_TEXT);
                if (kb3 != null) {
                    a.h = nb.a(kb3, true);
                }
                a.l = -1;
                final kb kb4 = (kb)h.g(ld.COMMAND__LINE__X);
                if (kb4 != null) {
                    a.l = nb.c(kb4);
                }
                if (a.l != -1) {
                    final a a2 = a;
                    a2.l += n2;
                }
                a.m = -1;
                final kb kb5 = (kb)h.g(ld.COMMAND__LINE__Y);
                if (kb5 != null) {
                    a.m = nb.c(kb5);
                }
                if (a.m != -1) {
                    final a a3 = a;
                    a3.m += n3;
                }
                a.n = n2;
                a.o = n3;
                a.p = o;
                a.q = p2;
                final kb kb6 = (kb)h.g(ld.COMMAND__LINE__IS_BUTTON_BODY);
                boolean a4 = true;
                if (kb6 != null) {
                    a4 = nb.a(kb6, true);
                }
                final kb kb7 = (kb)h.g(ld.COMMAND__LINE__BUTTON_SIZE);
                if (kb7 != null) {
                    final int b = nb.b(kb7, 0);
                    if (b > 0) {
                        if (sc.j != null) {
                            a.V = this.i.b(sc.j);
                            if (a.V == null) {
                                throw new RuntimeException(bingo.r.hb[6] + sc.j + ")");
                            }
                            if (sc.k != null) {
                                a.W = this.i.b(sc.k);
                                if (a.W == null) {
                                    throw new RuntimeException(bingo.r.hb[6] + sc.k + ")");
                                }
                            }
                            if (sc.l != null) {
                                a.X = this.i.b(sc.l);
                                if (a.X == null) {
                                    throw new RuntimeException(bingo.r.hb[6] + sc.l + ")");
                                }
                            }
                            if (sc.m != null) {
                                a.Y = this.i.b(sc.m);
                                if (a.Y == null) {
                                    throw new RuntimeException(bingo.r.hb[6] + sc.m + ")");
                                }
                            }
                            final int n4 = sc.n;
                            final int o2 = sc.o;
                            int e = a.V.e();
                            if (b < e) {
                                e = b;
                            }
                            else if (!a4) {
                                e = b;
                            }
                            final int f = a.V.f();
                            a.n = a.l;
                            if (a.n < 0) {
                                a.n = n2 + o / 2;
                            }
                            a.o = a.m;
                            if (a.o < 0) {
                                a.o = n3 + p2 / 2;
                            }
                            a.p = e;
                            a.q = f;
                            if (a.W != null) {
                                final a a5 = a;
                                a5.p += a.W.e();
                            }
                            a.n -= a.p / 2;
                            a.o -= a.q / 2;
                            a.l = a.n;
                            a.m = a.o;
                            final int n5 = a.n + n4;
                            final int n6 = a.o + o2;
                            a.V.a(0, 0, e, f);
                            a.V.b(e, f);
                            a.V.a(n5, n6);
                            a.V.a(a4);
                            final int n7 = n5 + e;
                            final int n8 = n6 + f;
                            final int n9 = n7;
                            final int n10 = n8;
                            if (a.W != null) {
                                a.W.a(n7, n6);
                                a.W.a(a4);
                            }
                            if (a.X != null) {
                                a.X.a(n9, n6);
                                a.X.a(-1.0f, 1.0f);
                                a.X.a(a4);
                            }
                            if (a.Y != null) {
                                a.Y.a(0, 0, n9 - n5, a.Y.f());
                                a.Y.b(n9 - n5, a.Y.f());
                                a.Y.a(n5, n10);
                                a.Y.a(-1.0f, 1.0f);
                                a.Y.a(a4);
                            }
                            int n11 = 0;
                            int n12 = 0;
                            if (a.d == 4) {
                                if (sc.s != null) {
                                    (a.Z = this.i.b(sc.s)).a(n5 + sc.t, n6 + sc.u);
                                    a.Z.a(true);
                                    n11 = a.Z.e() + sc.t;
                                    if (a.W != null) {
                                        n11 -= a.W.e();
                                    }
                                }
                            }
                            else if (a.d == 5) {
                                if (sc.p != null) {
                                    (a.Z = this.i.b(sc.p)).a(n5 + sc.q, n6 + sc.r);
                                    a.Z.a(true);
                                    n11 = a.Z.e() + sc.q;
                                    if (a.W != null) {
                                        n11 -= a.W.e();
                                    }
                                }
                            }
                            else if (a.d == 6) {
                                if (sc.v != null) {
                                    (a.Z = this.i.b(sc.v)).a(n5 + sc.x, n6 + sc.y);
                                    a.Z.a(true);
                                    n11 = a.Z.e() + sc.x;
                                    if (a.W != null) {
                                        n11 -= a.W.e();
                                    }
                                }
                                if (sc.w != null) {
                                    (a.ab = this.i.b(sc.w)).a(n5 + e - 1 + a.W.e() - sc.x - a.ab.e(), n6 + sc.y);
                                    a.ab.a(true);
                                    n12 = a.ab.e() + sc.x;
                                    if (a.W != null) {
                                        n12 -= a.W.e();
                                    }
                                }
                            }
                            final a a6 = a;
                            a6.l += n11;
                            final a a7 = a;
                            a7.n += n11;
                            final a a8 = a;
                            a8.p -= n11 + n12;
                        }
                    }
                }
                a.bb = -1;
                final kb kb8 = (kb)h.g(ld.COMMAND__LINE__PRIORITY);
                if (kb8 != null) {
                    a.bb = nb.b(kb8, -1);
                }
                kb kb9 = (kb)h.g(ld.COMMAND__LINE__SPRITE);
                if (kb9 != null && sc.F != null) {
                    if (sc.F.c(kb9) == null) {
                        final kb d = this.U.d(kb9);
                        if (d != null) {
                            a.J = kb9.b();
                            kb9 = d;
                            final neat.cb cb2 = (neat.cb)sc.F.c(kb9);
                        }
                    }
                    a.M = a.l;
                    a.N = a.m;
                    final kb kb10 = (kb)h.g(ld.COMMAND__LINE__SPRITE_HAS_POS);
                    if (kb10 != null) {
                        a.L = kb10.equals(ld.VARIABLE__BUTTON__TRUE);
                    }
                    a.K = kb9.b();
                    a.O = (kb)h.g(ld.COMMAND__LINE__SPRITE_ANIM);
                    if (a.O != null) {
                        final kb d2 = this.U.d(a.O);
                        if (d2 != null) {
                            a.O = d2;
                        }
                    }
                    if (a.O != null) {
                        a.O = a.O.b();
                    }
                    a.P = 0;
                    a.S = 0;
                    a.T = 0;
                    a.U = 0;
                    this.a(a);
                }
                if (a.l < 0) {
                    a.l = n2;
                }
                if (a.m < 0) {
                    a.m = n3;
                }
                final kb kb11 = (kb)h.g(ld.COMMAND__LINE__FONT);
                int n13;
                if (kb11 != null) {
                    n13 = nb.c(kb11);
                }
                else {
                    n13 = this.U.o();
                }
                final z a9 = this.a(n13);
                final int b2 = nb.b((kb)h.g(ld.COMMAND__LINE__CLIP_OFFSET), 0);
                (a.i = a9.b(this.i)).a(this.i, a.l + b2, a.m);
                a.i.a(this.i, a.n + b2, a.o, a.p - b2 * 2, a.q);
                if (a.bb >= 0) {
                    a.i.a(this.i, a.bb);
                }
                if (a.h) {
                    a.i.e(this.i);
                }
                final kb kb12 = (kb)h.g(ld.COMMAND__LINE__TYPE);
                a.i.a(this.i, true);
                kb kb13;
                int n14;
                int n15;
                float k;
                if (a.d == 1) {
                    kb13 = sc.K;
                    n14 = sc.L;
                    n15 = sc.M;
                    k = sc.N;
                }
                else {
                    kb13 = sc.O;
                    n14 = sc.P;
                    n15 = sc.Q;
                    k = sc.R;
                }
                if (kb13 != null) {
                    (a.j = a9.b(this.i)).a(this.i, kb13, true);
                    a.j.a(this.i, a.l + b2 + n14, a.m + n15);
                    a.j.a(this.i, a.n + b2 + n14, a.o + n15, a.p - b2 * 2, a.q);
                    a.k = k;
                    if (a.k != 1.0f) {
                        a.j.b(this.i, a.k, 1.0f);
                    }
                    a.j.a(this.i, a.i.b() + 1);
                    if (a.h) {
                        a.j.e(this.i);
                    }
                    a.j.a(this.i, true);
                }
                a.r = false;
                a.s = false;
            }
            t.f();
            this.E();
        }
    }
    
    private void D() {
        this.X.c();
    }
    
    private void E() {
        this.a(false);
    }
    
    public void a(final boolean b) {
        if (!this.S || this.U == null) {
            return;
        }
        final neat.r a = this.X.a();
        while (a.a()) {
            final a a2 = (a)this.X.g(a.b());
            boolean s = false;
            final boolean b2 = !this.eb && a2.V != null;
            if (a2.i != null && a2.i.c() != null) {
                final neat.system.graphics.renderer.a d = a2.i.d();
                if (d.b()) {
                    s = (this.ab >= d.d() && this.bb >= d.e() && this.ab < d.d() + d.f() && this.bb < d.e() + d.g());
                }
            }
            if (a2.H != null) {
                s |= (a2.H.k() && this.ab >= a2.H.c() && this.bb >= a2.H.d() && this.ab < a2.H.c() + a2.H.e() && this.bb < a2.H.d() + a2.H.f());
            }
            if (a2.V != null) {
                s |= (a2.V.k() && this.ab >= a2.V.c() && this.bb >= a2.V.d() && this.ab < a2.V.c() + a2.V.e() && this.bb < a2.V.d() + a2.V.f());
            }
            if (a2.W != null) {
                s |= (a2.W.k() && this.ab >= a2.W.c() && this.bb >= a2.W.d() && this.ab < a2.W.c() + a2.W.e() && this.bb < a2.W.d() + a2.W.f());
            }
            if (a2.Z != null) {
                s |= (a2.Z.k() && this.ab >= a2.Z.c() && this.bb >= a2.Z.d() && this.ab < a2.Z.c() + a2.Z.e() && this.bb < a2.Z.d() + a2.Z.f());
            }
            if (a2.ab != null) {
                s |= (a2.ab.k() && this.ab >= a2.ab.c() && this.bb >= a2.ab.d() && this.ab < a2.ab.c() + a2.ab.e() && this.bb < a2.ab.d() + a2.ab.f());
            }
            if (b2) {
                s = false;
            }
            int n = 0;
            if (a2.d == 5) {
                final kb d2 = this.U.d(a2.z);
                if (d2 != null && d2.equals(a2.c)) {
                    n = 1;
                }
            }
            if (n != 0) {
                s = true;
            }
            if (a2.s != s) {
                a2.s = s;
                a2.r = false;
            }
            if (b) {
                a2.r = false;
            }
            if (!a2.r) {
                a2.r = true;
                if (a2.d == 5) {
                    final kb d3 = this.U.d(a2.z);
                    if (d3 != null && d3.equals(a2.c)) {
                        n = 1;
                    }
                }
                final lb a3 = lb.a();
                boolean b3 = false;
                boolean b4 = false;
                if (a2.s) {
                    if (a2.g != null) {
                        a3.a(a2.g);
                    }
                }
                else if (a2.f != null) {
                    a3.a(a2.f);
                }
                if (a2.e != null) {
                    a3.a(a2.e);
                }
                if (a2.d != 0) {
                    if (a2.d == 2) {
                        if (a2.t != null) {
                            final kb d4 = this.U.d(a2.t);
                            if (d4 != null) {
                                a3.a(d4);
                            }
                        }
                    }
                    else if (a2.d == 4) {
                        b3 = true;
                        boolean b5 = false;
                        if (a2.w != null) {
                            final kb d5 = this.U.d(a2.w);
                            if (d5 != null && d5.equals(ld.VARIABLE__BUTTON__TRUE)) {
                                b5 = true;
                            }
                        }
                        if (b5) {
                            if (a2.x != null) {
                                a3.a(a2.x);
                            }
                            b4 = true;
                        }
                        else if (a2.y != null) {
                            a3.a(a2.y);
                        }
                    }
                    else if (a2.d == 5) {
                        b3 = true;
                        if (n != 0) {
                            b4 = true;
                        }
                    }
                    else if (a2.d == 6) {
                        b3 = true;
                        if (a2.E != null) {
                            a3.a(a2.E);
                        }
                        final kb d6 = this.U.d(a2.A);
                        if (d6 == null) {
                            a3.c("0");
                        }
                        else {
                            a3.a(d6);
                        }
                        if (a2.F != null) {
                            a3.a(a2.F);
                        }
                    }
                }
                final kb b6 = a3.b();
                if (a2.i != null) {
                    a2.i.b(this.i, b6);
                    if (b2) {
                        a2.i.b(this.i, 0.5f, 1.0f);
                    }
                    else {
                        a2.i.b(this.i, 1.0f, 0.0f);
                    }
                }
                if (a2.j != null) {
                    a2.j.b(this.i, b6);
                    if (b2) {
                        a2.j.b(this.i, a2.k * 0.5f, 1.0f);
                    }
                    else if (a2.k != 1.0f) {
                        a2.j.b(this.i, a2.k, 1.0f);
                    }
                    else {
                        a2.j.b(this.i, 1.0f, 0.0f);
                    }
                }
                b6.f();
                this.a(a2);
                if (a2.H != null && a2.O == null) {
                    int n2 = 0;
                    final int p = a2.H.p();
                    if (p > 0) {
                        if (p >= 2 && a2.s) {
                            n2 |= 0x1;
                        }
                        if (p >= 4 && b3) {
                            n2 |= 0x2;
                        }
                        a2.H.b(n2);
                    }
                }
                if (a2.V != null) {
                    int n3 = 0;
                    final int p2 = a2.V.p();
                    if (p2 > 0) {
                        if (p2 >= 2 && a2.s) {
                            n3 |= 0x1;
                        }
                        if (p2 >= 4 && b3) {
                            n3 |= 0x2;
                        }
                        a2.V.b(n3);
                    }
                }
                if (a2.W != null) {
                    int n4 = 0;
                    final int p3 = a2.W.p();
                    if (p3 > 0) {
                        if (p3 >= 2 && a2.s) {
                            n4 |= 0x1;
                        }
                        if (p3 >= 4 && b3) {
                            n4 |= 0x2;
                        }
                        a2.W.b(n4);
                    }
                }
                if (a2.Z != null) {
                    int n5 = 0;
                    final int p4 = a2.Z.p();
                    if (p4 > 0) {
                        if (p4 >= 2 && a2.s) {
                            n5 |= 0x1;
                        }
                        if (p4 >= 4 && b4) {
                            n5 |= 0x2;
                        }
                        a2.Z.b(n5);
                    }
                }
                if (a2.ab == null) {
                    continue;
                }
                int n6 = 0;
                final int p5 = a2.ab.p();
                if (p5 <= 0) {
                    continue;
                }
                if (p5 >= 2 && a2.s) {
                    n6 |= 0x1;
                }
                if (p5 >= 4 && b4) {
                    n6 |= 0x2;
                }
                a2.ab.b(n6);
            }
        }
        a.f();
    }
    
    private boolean F() {
        boolean b = false;
        if (this.S && this.ab < 798 && this.bb < 2) {
            b = true;
        }
        if (!this.S || this.U == null) {
            return b;
        }
        boolean b2 = false;
        final boolean b3 = false;
        boolean b4 = false;
        final sc sc = (sc)this.a();
        final int m = this.M;
        final int n = this.N;
        final int o = this.O;
        final int p = this.P;
        final neat.r a = this.X.a();
        while (a.a()) {
            final a a2 = (a)this.X.g(a.b());
            if (a2.c == null) {
                continue;
            }
            if (a2.d == 0) {
                continue;
            }
            final neat.system.graphics.renderer.a h = neat.system.graphics.renderer.a.h();
            if (a2.i != null && a2.i.c() != null) {
                final neat.system.graphics.renderer.a d = a2.i.d();
                if (d.b()) {
                    if (h.b()) {
                        h.c(d);
                    }
                    else {
                        h.b(d);
                    }
                }
            }
            if (a2.H != null && a2.H.k()) {
                final neat.system.graphics.renderer.a h2 = neat.system.graphics.renderer.a.h();
                h2.a(a2.H.c(), a2.H.d(), a2.H.e(), a2.H.f());
                if (h.b()) {
                    h.c(h2);
                }
                else {
                    h.b(h2);
                }
                h2.f();
            }
            if (a2.V != null && a2.V.k()) {
                final neat.system.graphics.renderer.a h3 = neat.system.graphics.renderer.a.h();
                h3.a(a2.V.c(), a2.V.d(), a2.V.e(), a2.V.f());
                if (h.b()) {
                    h.c(h3);
                }
                else {
                    h.b(h3);
                }
                h3.f();
            }
            if (a2.W != null && a2.W.k()) {
                final neat.system.graphics.renderer.a h4 = neat.system.graphics.renderer.a.h();
                h4.a(a2.W.c(), a2.W.d(), a2.W.e(), a2.W.f());
                if (h.b()) {
                    h.c(h4);
                }
                else {
                    h.b(h4);
                }
                h4.f();
            }
            if (a2.Z != null && a2.Z.k()) {
                final neat.system.graphics.renderer.a h5 = neat.system.graphics.renderer.a.h();
                h5.a(a2.Z.c(), a2.Z.d(), a2.Z.e(), a2.Z.f());
                if (h.b()) {
                    h.c(h5);
                }
                else {
                    h.b(h5);
                }
                h5.f();
            }
            if (a2.ab != null && a2.ab.k()) {
                final neat.system.graphics.renderer.a h6 = neat.system.graphics.renderer.a.h();
                h6.a(a2.ab.c(), a2.ab.d(), a2.ab.e(), a2.ab.f());
                if (h.b()) {
                    h.c(h6);
                }
                else {
                    h.b(h6);
                }
                h6.f();
            }
            int n2 = 0;
            if (h.b()) {
                n2 = ((this.ab >= h.d() && this.bb >= h.e() && this.ab < h.d() + h.f() && this.bb < h.e() + h.g()) ? 1 : 0);
            }
            h.f();
            boolean b5 = false;
            if (n2 != 0) {
                b = true;
                if (a2.d == 4) {
                    if (a2.w != null) {
                        final kb d2 = this.U.d(a2.w);
                        if (d2 != null) {
                            this.a().b(sc.S);
                            if (d2.equals(ld.VARIABLE__BUTTON__TRUE)) {
                                this.U.a(a2.w, ld.VARIABLE__BUTTON__FALSE);
                                b5 = true;
                            }
                            else if (d2.equals(ld.VARIABLE__BUTTON__FALSE)) {
                                this.U.a(a2.w, ld.VARIABLE__BUTTON__TRUE);
                                b5 = true;
                            }
                        }
                    }
                }
                else if (a2.d == 3) {
                    if (a2.u != null && a2.v != null) {
                        if (this.U.a(a2.u)) {
                            this.a().b(sc.S);
                            this.U.b(a2.u);
                            b4 = true;
                            break;
                        }
                        if (!a2.v.equals(this.U.d(a2.u))) {
                            this.a().b(sc.S);
                            this.U.a(a2.u, a2.v);
                            b5 = true;
                        }
                    }
                }
                else if (a2.d == 5) {
                    if (a2.z != null && a2.c != null && !a2.c.equals(this.U.d(a2.z))) {
                        this.a().b(sc.S);
                        this.U.a(a2.z, a2.c);
                        b5 = true;
                    }
                }
                else if (a2.d == 6 && a2.A != null) {
                    final int b6 = nb.b(this.U.d(a2.A), 0);
                    int n3;
                    if (this.ab < h.d() + h.f() / 2) {
                        n3 = b6 - a2.B;
                    }
                    else {
                        n3 = b6 + a2.B;
                    }
                    if (n3 < a2.C) {
                        if (a2.G) {
                            n3 += a2.D - a2.C + 1;
                        }
                        else {
                            n3 = a2.C;
                        }
                    }
                    else if (n3 > a2.D) {
                        if (a2.G) {
                            n3 -= a2.D - a2.C + 1;
                        }
                        else {
                            n3 = a2.D;
                        }
                    }
                    final kb a3 = nb.a(n3);
                    this.U.a(a2.A, a3);
                    a3.f();
                    this.a().b(sc.S);
                    b5 = true;
                }
            }
            if (!b5) {
                continue;
            }
            b2 = true;
            a2.r = false;
        }
        a.f();
        if (b4) {
            return b;
        }
        if (b3) {
            final neat.r a4 = this.X.a();
            while (a4.a()) {
                final a a5 = (a)this.X.g(a4.b());
                if (a5.c == null) {
                    continue;
                }
                if (a5.d == 0) {}
            }
            a4.f();
        }
        if (b2) {
            this.E();
        }
        return b;
    }
    
    private boolean c(final int n, final int n2) {
        if (!this.S || this.U == null) {
            return false;
        }
        final int m = this.M;
        final int n3 = this.N;
        final int o = this.O;
        final int p2 = this.P;
        return n >= m && n < m + o && n2 >= n3 && n2 < n3 + p2;
    }
    
    private void G() {
        if (this.Z != null) {
            this.i.a(this.Z);
            this.Z = null;
        }
    }
    
    private void d(final int ab, final int bb) {
        if (this.ab == ab && this.bb == bb) {
            return;
        }
        this.ab = ab;
        this.bb = bb;
        this.cb = true;
    }
    
    private void H() {
        if (this.cb) {
            this.cb = false;
            this.E();
        }
    }
    
    private void I() {
        if (this.V != null) {
            this.V.s(this.i);
        }
        if (this.W != null) {
            this.W.s(this.i);
        }
        if (this.X != null) {
            final neat.r a = this.X.a();
            while (a.a()) {
                final a a2 = (a)this.X.g(a.b());
                if (a2.i != null && a2.i.c() != null) {
                    a2.i.s(this.i);
                }
                if (a2.j != null && a2.j.c() != null) {
                    a2.j.s(this.i);
                }
            }
            a.f();
        }
    }
    
    private void a(final a a) {
        final sc sc = (sc)this.a();
        if (sc.F == null) {
            return;
        }
        boolean b = false;
        if (a.H == null) {
            b = true;
        }
        if (a.J != null) {
            final kb d = this.U.d(a.J);
            if (d == null) {
                if (a.K != null) {
                    a.K.f();
                    a.K = null;
                    b = true;
                }
            }
            else if (!d.equals(a.K)) {
                if (a.K != null) {
                    a.K.f();
                }
                a.K = d.b();
                b = true;
            }
        }
        if (!b) {
            return;
        }
        if (a.H != null) {
            if (a.I) {
                a.I = false;
                this.i.a(a.H);
            }
            else {
                a.H.a(false);
            }
            a.H = null;
        }
        if (a.K != null) {
            final neat.cb cb = (neat.cb)sc.F.c(a.K);
            if (cb != null) {
                a.H = this.i.b(cb);
                a.I = true;
            }
            else {
                a.H = (m)this.db.g(a.K);
                a.I = false;
            }
            if (a.H != null) {
                if (!a.L) {
                    final int m = this.M;
                    final int n = this.N;
                    final int o = this.O;
                    final int p = this.P;
                    int i;
                    if (a.M < 0) {
                        i = m + o / 2 - a.H.e() / 2;
                    }
                    else {
                        i = a.M;
                    }
                    int n2;
                    if (a.N < 0) {
                        n2 = n + p / 2 - a.H.f() / 2;
                    }
                    else {
                        n2 = a.N;
                    }
                    a.H.a(i, n2);
                }
                a.H.a(true);
                if (a.bb >= 0) {
                    a.H.a(a.bb);
                }
                this.a(a, 0);
            }
        }
    }
    
    private void a(final a a, int n) {
        if (a.H == null) {
            return;
        }
        if (a.O == null) {
            return;
        }
        if (a.O.d() == 0) {
            return;
        }
        final int p2 = a.H.p();
        if (p2 <= 0) {
            return;
        }
        int q = a.H.q();
        boolean k = a.H.k();
        while (true) {
            if (a.P >= a.O.d()) {
                if (a.U > 0) {
                    --a.U;
                }
                if (a.Q != null && a.U <= 0) {
                    a.O.f();
                    a.O = a.Q;
                    a.Q = null;
                    a.P = a.R;
                    a.T = 0;
                    a.S = 0;
                    continue;
                }
                a.P = 0;
            }
            if (a.P == 0) {
                k = true;
            }
            if (a.O.b(a.P) == '!') {
                if (++a.P >= a.O.d()) {
                    continue;
                }
                final char b = a.O.b(a.P++);
                if (b == 'r' || b == 'l') {
                    if (a.P >= a.O.d()) {
                        continue;
                    }
                    final int a2 = a.O.a(91, a.P);
                    if (a2 < 0) {
                        continue;
                    }
                    final int a3 = a.O.a(93, a2);
                    if (a3 < 0) {
                        continue;
                    }
                    final int a4 = a.O.a(44, a3);
                    final kb c = a.O.c(a.P, a2);
                    final int b2 = nb.b(c, -1);
                    c.f();
                    if (a4 < 0) {
                        a.P = 0;
                    }
                    else {
                        a.P = a4 + 1;
                    }
                    if (b2 < 0) {
                        continue;
                    }
                    if (b == 'r') {
                        if (this.i.d(b2) != 0) {
                            continue;
                        }
                    }
                    else if (b == 'l') {
                        a.U = b2;
                    }
                    if (a.Q != null) {
                        continue;
                    }
                    a.Q = a.O;
                    a.R = a.P;
                    a.O = a.Q.c(a2 + 1, a3);
                    a.P = 0;
                    a.T = 0;
                    a.S = 0;
                    k = true;
                }
                else {
                    if (b != 'v') {
                        continue;
                    }
                    final int a5 = a.O.a(44, a.P);
                    if (a5 < 0) {
                        a.P = 0;
                    }
                    else {
                        a.P = a5 + 1;
                    }
                    k ^= true;
                }
            }
            else {
                int a6 = a.O.a(58, a.P);
                if (a6 < 0 || a6 >= a.O.d() - 1) {
                    continue;
                }
                final kb c2 = a.O.c(a.P, a6);
                int b3 = nb.b(c2, -1);
                c2.f();
                boolean b4 = false;
                if (b3 < 0) {
                    b4 = true;
                    b3 = -b3;
                }
                ++a6;
                int n2 = a.O.a(44, a6);
                if (n2 < 0) {
                    n2 = a.O.d();
                }
                final kb c3 = a.O.c(a6, n2);
                final int b5 = nb.b(c3, -1000);
                c3.f();
                if (b5 == -1000) {
                    return;
                }
                if (b3 == 0) {
                    q = 0;
                    a.T = 0;
                    a.S = -this.i.d(b5);
                }
                else if (b5 == 0) {
                    if (b4) {
                        q -= b3;
                    }
                    else {
                        q += b3;
                    }
                    a.T += b3;
                }
                else if (b5 > 0 && n > 0) {
                    a.S += n;
                    n = 0;
                    if (a.S >= b5) {
                        final int n3 = a.S / b5;
                        a.S %= b5;
                        if (b4) {
                            q -= n3;
                        }
                        else {
                            q += n3;
                        }
                        a.T += n3;
                    }
                }
                if (a.T < b3) {
                    int n4 = q % p2;
                    if (n4 < 0) {
                        n4 += p2 - 1;
                    }
                    a.H.b(n4);
                    a.H.a(k);
                    return;
                }
                a.T -= b3;
                a.P = n2 + 1;
            }
        }
    }
    
    private void c(final int n) {
        if (!this.S || this.U == null) {
            return;
        }
        final neat.r a = this.X.a();
        while (a.a()) {
            final a a2 = (a)this.X.g(a.b());
            if (a2.c == null) {
                continue;
            }
            if (a2.d == 0) {
                continue;
            }
            if (a2.H == null) {
                continue;
            }
            this.a(a2, n);
        }
        a.f();
    }
    
    public void a(final kb kb, final m m) {
        if (kb == null || m == null) {
            return;
        }
        if (this.n != null) {
            m.b(this.n);
        }
        this.db.a(kb.b(), m);
    }
    
    public void c(final kb kb) {
        if (kb == null) {
            return;
        }
        final m m = (m)this.db.b(kb);
        if (m != null) {
            this.i.a(m);
        }
    }
    
    private void J() {
        if (this.n == null) {
            return;
        }
        final neat.r a = this.db.a();
        while (a.a()) {
            ((m)this.db.g(a.b())).b(this.n);
        }
        a.f();
    }
    
    public void K() {
        final neat.r a = this.db.a();
        while (a.a()) {
            this.i.a((m)this.db.g(a.b()));
        }
        a.f();
        this.db.d();
    }
    
    public void a(final Object fb) {
        this.fb = fb;
    }
    
    public void b(final Object o) {
        if (this.fb == o) {
            this.fb = null;
        }
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof sc)) {
            throw new RuntimeException(bingo.r.hb[8] + gb);
        }
        final sc sc = (sc)gb;
    }
    
    public void d() {
        this.c();
    }
    
    public void a(final int n) {
        this.b(n);
    }
    
    public void a() {
        this.I();
        this.H();
    }
    
    public boolean a(final neat.system.n n) {
        if (!this.eb) {
            return false;
        }
        if (this.w()) {
            return this.c(n.d, n.e);
        }
        if (this.fb instanceof o && ((o)this.fb).a(n)) {
            return true;
        }
        this.d(n.d, n.e);
        return this.c(n.d, n.e);
    }
    
    public boolean a(final neat.system.o o) {
        if (!this.eb) {
            return false;
        }
        if (this.w()) {
            return this.c(o.d, o.e);
        }
        if (this.fb instanceof o && ((o)this.fb).a(o)) {
            return true;
        }
        this.d(o.d, o.e);
        return this.F() || this.c(o.d, o.e);
    }
    
    public boolean a(final neat.system.m m) {
        if (!this.eb) {
            return false;
        }
        if (this.w()) {
            return this.c(m.d, m.e);
        }
        if (this.fb instanceof a.p && ((a.p)this.fb).a(m)) {
            return true;
        }
        this.d(m.d, m.e);
        return false;
    }
    
    public boolean a(final l l) {
        if (!this.eb) {
            return false;
        }
        if (this.w()) {
            return this.c(l.d, l.e);
        }
        if (this.fb instanceof a.p && ((a.p)this.fb).a(l)) {
            return true;
        }
        this.d(l.d, l.e);
        return false;
    }
    
    public boolean a(final neat.system.i i) {
        return false;
    }
    
    public boolean a(final neat.system.j j) {
        return false;
    }
    
    public static r L() {
        return (r)r.l.a();
    }
    
    public void f() {
        bingo.r.l.a(this);
    }
    
    public void g() {
        super.g();
        this.S = false;
        this.cb = false;
        this.ab = -1;
        this.bb = -1;
        this.X = neat.h.e();
        this.Y = neat.h.e();
        this.db = neat.h.e();
        this.Q = 0;
        this.R = 0;
        this.T = true;
    }
    
    public void h() {
        this.k();
        this.db.f();
        this.db = null;
        this.X.f();
        this.X = null;
        this.Y.f();
        this.Y = null;
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
    
    public r() {
        this.m = null;
        this.n = null;
        this.o = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.U = null;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.db = null;
        this.fb = null;
    }
    
    static {
        final String[] hb = new String[13];
        final int n = 0;
        final char[] charArray = "\u0016q\u0012R# !\u0003Z9bu@W8$e\u0005_w#s\u000fVw1i\tHw6i\u0001_82;".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'E';
                            break;
                        }
                        case 1: {
                            c2 = '\u0001';
                            break;
                        }
                        case 2: {
                            c2 = '`';
                            break;
                        }
                        case 3: {
                            c2 = ';';
                            break;
                        }
                        default: {
                            c2 = 'W';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        hb[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "i!\u0004R6)n\u0007\u001b5$b\u000b\\%*t\u000e_w6i\u0001_82!\fT6!d\u0004\u001b17n\r\u0001".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'E';
                            break;
                        }
                        case 1: {
                            c4 = '\u0001';
                            break;
                        }
                        case 2: {
                            c4 = '`';
                            break;
                        }
                        case 3: {
                            c4 = ';';
                            break;
                        }
                        default: {
                            c4 = 'W';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        hb[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0006m\tKw#n\u0012\u001b# y\u0014\u001b67d\u0001\u001b:0r\u0014\u001b5 !\u0004^1,o\u0005_w,o@H?$e\u000fLm".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'E';
                            break;
                        }
                        case 1: {
                            c6 = '\u0001';
                            break;
                        }
                        case 2: {
                            c6 = '`';
                            break;
                        }
                        case 3: {
                            c6 = ';';
                            break;
                        }
                        default: {
                            c6 = 'W';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        hb[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u0006`\u000eOw)n\u0001_w!h\u0001W8\"!\u0002Z4.f\u0012T\"+e@_61`A\u001b\u007f+`\r^m".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'E';
                            break;
                        }
                        case 1: {
                            c8 = '\u0001';
                            break;
                        }
                        case 2: {
                            c8 = '`';
                            break;
                        }
                        case 3: {
                            c8 = ';';
                            break;
                        }
                        default: {
                            c8 = 'W';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        hb[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "i!\u0004R6)n\u0007\u001b$-`\u0004T \u007f".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0561: {
                if (n18 > 1) {
                    break Label_0561;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'E';
                            break;
                        }
                        case 1: {
                            c10 = '\u0001';
                            break;
                        }
                        case 2: {
                            c10 = '`';
                            break;
                        }
                        case 3: {
                            c10 = ';';
                            break;
                        }
                        default: {
                            c10 = 'W';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        hb[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\fo\u0016Z;,e@_>$m\u000f\\w'`\u0003P07n\u0015U3ee\u0001O6d!HU6(dZ".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0677: {
                if (n22 > 1) {
                    break Label_0677;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'E';
                            break;
                        }
                        case 1: {
                            c12 = '\u0001';
                            break;
                        }
                        case 2: {
                            c12 = '`';
                            break;
                        }
                        case 3: {
                            c12 = ';';
                            break;
                        }
                        default: {
                            c12 = 'W';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        hb[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u0006`\u000e\u001c#em\u000fZ3er\u0010I>1d@]%*l@O?,r@H?$e\u000fLve)".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0797: {
                if (n26 > 1) {
                    break Label_0797;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'E';
                            break;
                        }
                        case 1: {
                            c14 = '\u0001';
                            break;
                        }
                        case 2: {
                            c14 = '`';
                            break;
                        }
                        case 3: {
                            c14 = ';';
                            break;
                        }
                        default: {
                            c14 = 'W';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        hb[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u0011i\tHw6i\u0001_82!\u000f]w1d\u0018Ow(t\u0013Ow'd@Zw\fo4^/1R\bZ3*v@R:5m\u0005V2+u\u0001O>*oZ".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0917: {
                if (n30 > 1) {
                    break Label_0917;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'E';
                            break;
                        }
                        case 1: {
                            c16 = '\u0001';
                            break;
                        }
                        case 2: {
                            c16 = '`';
                            break;
                        }
                        case 3: {
                            c16 = ';';
                            break;
                        }
                        default: {
                            c16 = 'W';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        hb[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u0011i\tHw6i\u0001_82!\u000f]w,u\u0005Vw(t\u0013Ow'd@Zw\u0001h\u0001W8\"R\bZ3*v@R:5m\u0005V2+u\u0001O>*oZ".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1037: {
                if (n34 > 1) {
                    break Label_1037;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 'E';
                            break;
                        }
                        case 1: {
                            c18 = '\u0001';
                            break;
                        }
                        case 2: {
                            c18 = '`';
                            break;
                        }
                        case 3: {
                            c18 = ';';
                            break;
                        }
                        default: {
                            c18 = 'W';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        hb[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u0003n\u000eOdeb\u0001Up1!\fT6!d\u0004\u001b17n\r\u001b#-h\u0013\u001b$-`\u0004T \u007f".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1157: {
                if (n38 > 1) {
                    break Label_1157;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 'E';
                            break;
                        }
                        case 1: {
                            c20 = '\u0001';
                            break;
                        }
                        case 2: {
                            c20 = '`';
                            break;
                        }
                        case 3: {
                            c20 = ';';
                            break;
                        }
                        default: {
                            c20 = 'W';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        hb[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "\u0003n\u000eOfeb\u0001Up1!\fT6!d\u0004\u001b17n\r\u001b#-h\u0013\u001b$-`\u0004T \u007f".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1277: {
                if (n42 > 1) {
                    break Label_1277;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = 'E';
                            break;
                        }
                        case 1: {
                            c22 = '\u0001';
                            break;
                        }
                        case 2: {
                            c22 = '`';
                            break;
                        }
                        case 3: {
                            c22 = ';';
                            break;
                        }
                        default: {
                            c22 = 'W';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        hb[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "\u0003n\u000eOeeb\u0001Up1!\fT6!d\u0004\u001b17n\r\u001b#-h\u0013\u001b$-`\u0004T \u007f".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1397: {
                if (n46 > 1) {
                    break Label_1397;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = 'E';
                            break;
                        }
                        case 1: {
                            c24 = '\u0001';
                            break;
                        }
                        case 2: {
                            c24 = '`';
                            break;
                        }
                        case 3: {
                            c24 = ';';
                            break;
                        }
                        default: {
                            c24 = 'W';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        hb[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "'h\u000e\\8ks".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1517: {
                if (n50 > 1) {
                    break Label_1517;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = 'E';
                            break;
                        }
                        case 1: {
                            c26 = '\u0001';
                            break;
                        }
                        case 2: {
                            c26 = '`';
                            break;
                        }
                        case 3: {
                            c26 = ';';
                            break;
                        }
                        default: {
                            c26 = 'W';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 <= n52) {
                hb[n49] = new String(charArray13).intern();
                r.hb = hb;
                r.l = new f((r.gb != null) ? r.gb : (r.gb = a(r.hb[12])));
                return;
            }
            continue;
        }
    }
}
