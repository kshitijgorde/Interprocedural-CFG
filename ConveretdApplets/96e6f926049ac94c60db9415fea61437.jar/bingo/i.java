// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.r;
import neat.cb;
import neat.g;
import neat.system.graphics.renderer.m;
import neat.kb;
import neat.system.f;

public class i extends h
{
    private static final float[] h;
    private static f i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private int t;
    private kb u;
    private int v;
    private int w;
    private m x;
    private int y;
    private int z;
    private int A;
    private int B;
    private int C;
    private float D;
    private boolean E;
    private int F;
    private boolean G;
    private int H;
    private int I;
    private boolean J;
    private i K;
    private boolean L;
    private int M;
    private float N;
    private boolean O;
    private boolean P;
    private m Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private m W;
    public int X;
    public int Y;
    public int Z;
    private int ab;
    private int bb;
    private int cb;
    private float db;
    private g eb;
    private float fb;
    private int gb;
    private int hb;
    private int ib;
    private kb jb;
    private static /* synthetic */ Class kb;
    private static String[] lb;
    
    public int b() {
        return this.k;
    }
    
    public int d() {
        return this.l;
    }
    
    public int h() {
        return this.j;
    }
    
    public l i() {
        return this.a.d(this.a());
    }
    
    public void a(final boolean p) {
        if (this.p == p) {
            return;
        }
        this.p = p;
        this.w();
    }
    
    public void c(final int n) {
        if (this.k != 0) {
            this.f(n);
        }
        this.i(n);
    }
    
    public void j() {
        this.x();
        this.t();
    }
    
    private void a(final lb lb) {
        if (this.k != 1) {
            return;
        }
        final int n = this.a.N() / 5;
        this.q = this.n * n + 1;
        this.r = this.q + n - 1;
        if (lb.k != null) {
            this.u = lb.k.b();
        }
    }
    
    public boolean d(final int n) {
        return this.k == 1 && n >= this.q && n <= this.r;
    }
    
    public int k() {
        if (this.k != 1) {
            return -1;
        }
        return this.q;
    }
    
    public int l() {
        if (this.k != 1) {
            return -1;
        }
        return this.r;
    }
    
    private void a(final pb pb) {
        if (this.k != 2 || pb == null) {
            return;
        }
        if (pb.f != null) {
            this.u = pb.f.b();
        }
    }
    
    private void a(final ub ub) {
        if (this.k != 4 || ub == null) {
            return;
        }
        this.s = ub.j;
        this.t = ub.l;
        this.a(ub.m);
        if (ub.n != null) {
            this.u = ub.n.b();
        }
    }
    
    private void a(final sb sb) {
        if (this.k != 5 || sb == null) {
            return;
        }
        this.s = sb.j;
        this.a(sb.k);
        if (sb.l != null) {
            this.u = sb.l.b();
        }
    }
    
    private void a(final ib ib) {
        if (this.k != 6 || ib == null) {
            return;
        }
        this.v = ib.j;
        this.a(ib.k);
        if (ib.l != null) {
            this.u = ib.l.b();
        }
    }
    
    private void a(final hb hb) {
        if (this.k != 8 || hb == null) {
            return;
        }
        this.w = hb.j;
        this.a(hb.k);
        if (hb.l != null) {
            this.u = hb.l.b();
        }
    }
    
    private void a(final gb gb) {
        if (this.k != 9 || gb == null) {
            return;
        }
    }
    
    public int m() {
        if (this.k != 6) {
            return 0;
        }
        return this.v;
    }
    
    private void e(final int n) {
        boolean b = false;
        switch (this.k) {
            case 1:
            case 2: {
                if (this.H == 0) {
                    b = true;
                }
                break;
            }
            case 3: {
                if (this.H == 0) {
                    this.a.c(2, this.s, this.t);
                    b = true;
                }
                break;
            }
            case 4: {
                if (this.H == 0) {
                    this.a.c(5, this.s, this.t);
                    b = true;
                }
                break;
            }
            case 5: {
                if (this.H == 0) {
                    this.a.c(2, this.s, 0);
                    b = true;
                }
                break;
            }
            case 6: {
                if (this.H == 0) {
                    this.a.a(this, 2, n + 200, 200, this.H);
                    b = true;
                }
                break;
            }
            case 8: {
                if (this.H == 0 || this.H == 1) {
                    if (this.a.d(this.a()) != null) {
                        this.a.a(this, 6, n + 100, 100, 1);
                    }
                    else {
                        this.a.a(this, 6, n + 100, 100, 0);
                    }
                    b = true;
                }
                break;
            }
        }
        if (b && this.u != null) {
            this.a.c(this.u);
        }
    }
    
    private void f(final int n) {
        switch (this.k) {
            case 8: {
                this.g(n);
                break;
            }
            case 4:
            case 5: {
                this.g(n);
                break;
            }
            case 6: {
                this.g(n);
                break;
            }
        }
    }
    
    private void n() {
        this.o();
        if (this.u != null) {
            this.u.f();
            this.u = null;
        }
    }
    
    private void a(final neat.cb cb) {
        if (cb != null) {
            this.x = this.a.a(cb);
            if (this.x != null) {
                this.x.a(false);
                this.y = this.x.e();
                this.z = this.x.f();
                this.A = this.x.g();
            }
            this.B = 0;
            this.C = this.a.J(3000);
            this.D = 0.0f;
        }
    }
    
    private void o() {
        if (this.x != null) {
            this.a.a(this.x);
            this.x = null;
        }
    }
    
    private void g(final int n) {
        if (this.x == null) {
            return;
        }
        this.C += n;
        final float d = this.D;
        switch (this.B) {
            case 0: {
                if (this.C >= 0) {
                    ++this.B;
                    this.C = 0;
                    break;
                }
                this.D = 0.0f;
                break;
            }
            case 1: {
                if (this.C >= 2000) {
                    this.B = 0;
                    this.C = 0;
                    this.D = 0.0f;
                    break;
                }
                this.D = this.C / 2000.0f;
                this.D = 1.0f - ((float)Math.cos(this.D * 3.1415927f * 2.0f) + 1.0f) / 2.0f;
                if (this.D < 0.0f) {
                    this.D = 0.0f;
                }
                else if (this.D > 1.0f) {
                    this.D = 1.0f;
                }
                break;
            }
        }
        if (d != this.D) {
            this.w();
        }
    }
    
    private void a(final boolean b, final float n, final int n2) {
        if (this.x != null) {
            if (this.D == 0.0f || !b || n == 0.0f) {
                this.x.a(false);
            }
            else {
                final int e = this.e();
                final int f = this.f();
                if (e >= 0 && f >= 0) {
                    final int y = this.y;
                    final int z = this.z;
                    this.x.a(e - y / 2, f - z / 2);
                    this.x.b(y, z);
                }
                this.x.a(n * this.D, 1.0f);
                this.x.a(this.A + n2);
                this.x.a(true);
            }
        }
    }
    
    public void a(final fb fb, final int k, final int l) {
        this.v();
        this.n();
        this.k = k;
        final int n = this.a.N();
        if (l < 1 || l > n) {
            throw new RuntimeException(bingo.i.lb[7] + l);
        }
        this.l = l;
        if (k == 1) {
            if (fb.G == null) {
                throw new RuntimeException(bingo.i.lb[3]);
            }
            this.m = fb.G.e;
            if (this.m < 0) {
                this.m = 0;
            }
            this.n = (l - 1) / (n / 5);
        }
        else if (k == 2) {
            this.m = 0;
            this.n = 0;
        }
        else {
            this.m = (l - 1) % (n / 5);
            this.n = (l - 1) / (n / 5);
        }
        if (this.m < 0) {
            throw new RuntimeException(bingo.i.lb[5] + this.m);
        }
        if (this.n < 0 || this.n >= 5) {
            throw new RuntimeException(bingo.i.lb[4] + this.n);
        }
        this.j = fb.r;
        if (this.j <= 0) {
            throw new RuntimeException(bingo.i.lb[6] + this.j);
        }
        switch (k) {
            case 1: {
                this.a(fb.G);
                break;
            }
            case 2: {
                this.a(fb.H);
                break;
            }
            case 4: {
                this.a(fb.I);
                break;
            }
            case 5: {
                this.a(fb.J);
                break;
            }
            case 6: {
                this.a(fb.K);
                break;
            }
            case 8: {
                this.a(fb.L);
                break;
            }
            case 9: {
                this.a(fb.M);
                break;
            }
        }
        this.a(fb);
    }
    
    public void a(final int f, final int h) {
        this.E = true;
        this.G = false;
        this.H = h;
        if (this.H == -1) {
            this.F = 0;
            this.J = true;
        }
        else {
            this.F = f;
            this.J = false;
        }
    }
    
    public boolean p() {
        return this.E;
    }
    
    public boolean q() {
        return this.E && this.J;
    }
    
    public void h(final int n) {
        if (!this.E) {
            return;
        }
        if (this.a.W()) {
            return;
        }
        if (!this.G) {
            this.F -= n;
            if (this.F <= 0) {
                this.G = true;
                if (this.k != 0) {
                    this.e(0);
                }
                this.fb = 0.0f;
                this.I = 0;
                this.w();
                if (this.H == 0) {
                    this.a.c(this.jb);
                }
            }
        }
        else {
            if (this.H == -1) {
                this.J = true;
                return;
            }
            if (this.H == 0) {
                if (this.eb == null) {
                    this.J = true;
                    return;
                }
                this.I += n;
                this.fb = this.I / 600.0f;
                if (this.fb >= 1.0f) {
                    this.fb = 1.0f;
                    this.J = true;
                }
            }
            if (this.H == 1 || this.H == 2) {
                this.I += n;
                if (this.H == 2) {
                    this.db = this.I / 150.0f;
                }
                else {
                    this.db = this.I / 250.0f;
                }
                if (this.db >= 1.0f) {
                    this.db = 1.0f;
                    this.J = true;
                }
            }
            this.w();
        }
    }
    
    private void r() {
        if (this.K != null) {
            if (this.J) {
                this.a.a(this.K);
            }
            else {
                this.a.b(this.K);
            }
            this.K = null;
        }
    }
    
    private void s() {
        if (this.K != null) {
            this.K.b(this.c());
            this.K.b(this.O);
        }
    }
    
    private void t() {
        if (this.K != null) {
            this.K.j();
        }
    }
    
    public boolean u() {
        return this.L;
    }
    
    private void i(final int n) {
        if (!this.L) {
            return;
        }
        if (this.p()) {
            return;
        }
        final float n2 = 0.8f;
        final float n3 = this.c();
        this.N += n * n2;
        if (n3 > this.N) {
            this.N = n3;
        }
        final int n4 = (int)this.N;
        if (n4 >= this.M) {
            this.b(this.M);
            this.a.a(this, 0, -1);
            return;
        }
        this.b(n4);
    }
    
    private void a(final fb fb) {
        this.v();
        if (this.l == -1) {
            throw new RuntimeException(bingo.i.lb[0]);
        }
        neat.cb cb = null;
        neat.cb cb2 = null;
        neat.cb cb3 = null;
        if (this.k == 2) {
            if (fb.H == null) {
                throw new RuntimeException(bingo.i.lb[2]);
            }
            cb = fb.H.e;
        }
        else if (this.k == 9) {
            if (fb.M == null) {
                throw new RuntimeException(bingo.i.lb[1]);
            }
            cb = fb.M.e;
        }
        else {
            switch (this.n) {
                case 0: {
                    cb = fb.e;
                    cb2 = fb.u;
                    cb3 = fb.A;
                    break;
                }
                case 1: {
                    cb = fb.f;
                    cb2 = fb.v;
                    cb3 = fb.B;
                    break;
                }
                case 2: {
                    cb = fb.g;
                    cb2 = fb.w;
                    cb3 = fb.C;
                    break;
                }
                case 3: {
                    cb = fb.h;
                    cb2 = fb.x;
                    cb3 = fb.D;
                    break;
                }
                case 4: {
                    cb = fb.i;
                    cb2 = fb.y;
                    cb3 = fb.E;
                    break;
                }
            }
            switch (this.k) {
                case 1: {
                    if (fb.G != null) {
                        switch (this.n) {
                            case 0: {
                                cb = fb.G.f;
                                break;
                            }
                            case 1: {
                                cb = fb.G.g;
                                break;
                            }
                            case 2: {
                                cb = fb.G.h;
                                break;
                            }
                            case 3: {
                                cb = fb.G.i;
                                break;
                            }
                            case 4: {
                                cb = fb.G.j;
                                break;
                            }
                        }
                        cb2 = null;
                    }
                    break;
                }
                case 4: {
                    if (fb.I != null) {
                        switch (this.n) {
                            case 0: {
                                cb = fb.I.e;
                                break;
                            }
                            case 1: {
                                cb = fb.I.f;
                                break;
                            }
                            case 2: {
                                cb = fb.I.g;
                                break;
                            }
                            case 3: {
                                cb = fb.I.h;
                                break;
                            }
                            case 4: {
                                cb = fb.I.i;
                                break;
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    if (fb.J != null) {
                        switch (this.n) {
                            case 0: {
                                cb = fb.J.e;
                                break;
                            }
                            case 1: {
                                cb = fb.J.f;
                                break;
                            }
                            case 2: {
                                cb = fb.J.g;
                                break;
                            }
                            case 3: {
                                cb = fb.J.h;
                                break;
                            }
                            case 4: {
                                cb = fb.J.i;
                                break;
                            }
                        }
                    }
                    break;
                }
                case 6: {
                    if (fb.K != null) {
                        switch (this.n) {
                            case 0: {
                                cb = fb.K.e;
                                break;
                            }
                            case 1: {
                                cb = fb.K.f;
                                break;
                            }
                            case 2: {
                                cb = fb.K.g;
                                break;
                            }
                            case 3: {
                                cb = fb.K.h;
                                break;
                            }
                            case 4: {
                                cb = fb.K.i;
                                break;
                            }
                        }
                    }
                    break;
                }
                case 8: {
                    if (fb.L != null) {
                        switch (this.n) {
                            case 0: {
                                cb = fb.L.e;
                                break;
                            }
                            case 1: {
                                cb = fb.L.f;
                                break;
                            }
                            case 2: {
                                cb = fb.L.g;
                                break;
                            }
                            case 3: {
                                cb = fb.L.h;
                                break;
                            }
                            case 4: {
                                cb = fb.L.i;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        if (cb != null) {
            this.Q = this.a.a(cb);
            this.R = this.a.J(2000);
            this.S = 3;
            if (this.Q != null) {
                this.T = this.Q.g();
                this.U = this.Q.e();
                this.V = this.Q.f();
            }
        }
        if (cb2 != null) {
            this.W = this.a.a(cb2);
            if (this.W != null) {
                this.ab = this.W.g();
                this.bb = this.W.e();
                this.cb = this.W.f();
            }
        }
        this.X = fb.s;
        this.Y = fb.t;
        this.Z = this.m;
        if (cb3 == null) {
            cb3 = fb.z;
        }
        if (cb3 != null) {
            this.eb = neat.g.j();
            for (int i = 0; i < 2; ++i) {
                final m a = this.a.a(cb3);
                if (a == null) {
                    break;
                }
                if (i == 0) {
                    this.gb = a.g();
                    this.hb = a.e();
                    this.ib = a.f();
                }
                this.eb.a(i, a);
            }
            this.fb = -1.0f;
        }
        this.db = -1.0f;
        if (fb.F != null) {
            this.jb = fb.F.b();
        }
        this.w();
    }
    
    private void v() {
        if (this.Q != null) {
            this.a.a(this.Q);
            this.Q = null;
        }
        if (this.W != null) {
            this.a.a(this.W);
            this.W = null;
        }
        if (this.eb != null) {
            final r d = this.eb.d();
            while (d.a()) {
                final m m = (m)d.b();
                d.e();
                this.a.a(m);
            }
            d.f();
            this.eb.f();
            this.eb = null;
        }
        if (this.jb != null) {
            this.jb.f();
            this.jb = null;
        }
    }
    
    public void b(final boolean o) {
        if (this.O == o) {
            return;
        }
        this.O = o;
        this.w();
    }
    
    private void w() {
        this.P = true;
        this.s();
    }
    
    private void x() {
        if (!this.P) {
            return;
        }
        this.P = false;
        boolean o = this.O;
        final int e = this.e();
        final int f = this.f();
        final int g = this.g();
        float n = 1.0f;
        if (g < 0) {
            if (this.c() < this.b().e() / 2) {
                n = 1.0f - -g / (this.j * 2.0f);
            }
            else {
                float n2 = -g / (this.j * 1.0f);
                if (n2 < 0.0f) {
                    n2 = 0.0f;
                }
                else if (n2 > 1.0f) {
                    n2 = 1.0f;
                }
                n = (float)Math.cos(n2 * 3.1415927f / 2.0f);
            }
            if (n < 0.0f) {
                n = 0.0f;
            }
        }
        if (this.fb >= 0.0f) {
            if (this.fb >= 0.8f) {
                o = false;
            }
            else {
                n *= (0.8f - this.fb) / 0.8f;
            }
        }
        if (this.db >= 0.0f) {
            n *= 1.0f - this.db;
        }
        int n3 = 0;
        if (n != 1.0f || this.p) {
            n3 = 5;
        }
        if (this.Q != null) {
            if (e >= 0 && f >= 0) {
                this.Q.a(e - this.U / 2, f - this.V / 2);
            }
            this.Q.a(n, 1.0f - n);
            this.Q.a(this.T + n3);
            this.Q.a(o);
        }
        if (this.W != null) {
            if (e >= 0 && f >= 0) {
                this.W.a(e - this.U / 2 + this.X, f - this.V / 2 + this.Y);
            }
            if (this.W.p() > 0 && this.Z >= 0 && this.W.q() != this.Z) {
                this.W.b(this.Z);
            }
            this.W.a(n, 1.0f - n);
            this.W.a(this.ab + n3);
            this.W.a(o);
        }
        if (this.eb != null) {
            int n4 = 0;
            for (int i = 0; i < 2; ++i) {
                final m m = (m)this.eb.a(i);
                if (m != null) {
                    if (this.fb < 0.0f) {
                        m.a(false);
                    }
                    else {
                        final float n5 = bingo.i.h[n4++];
                        final float n6 = bingo.i.h[n4++];
                        final float n7 = bingo.i.h[n4++];
                        final float n8 = bingo.i.h[n4++];
                        final float n9 = bingo.i.h[n4++];
                        final float n10 = bingo.i.h[n4++];
                        float n11;
                        if (this.fb < n5) {
                            n11 = -1.0f;
                        }
                        else if (this.fb >= n5 + n6) {
                            n11 = 1.0f;
                        }
                        else {
                            n11 = (this.fb - n5) / (n6 - n5);
                        }
                        if (n11 < 0.0f) {
                            m.a(false);
                        }
                        else {
                            final float n12 = n7 + n8 * n11;
                            final int n13 = (int)(this.hb * n12) / 2 * 2;
                            final int n14 = (int)(this.ib * n12) / 2 * 2;
                            if (e >= 0 && f >= 0) {
                                m.a(e - n13 / 2, f - n14 / 2);
                                m.b(n13, n14);
                            }
                            final int p = m.p();
                            if (p > 1) {
                                m.b(i % p);
                            }
                            float n15;
                            if (this.fb < n10) {
                                n15 = n11 / n10;
                            }
                            else {
                                n15 = (1.0f - n11) / (1.0f - n10);
                            }
                            if (n15 <= 0.0f) {
                                m.a(false);
                            }
                            else {
                                if (n15 > 1.0f) {
                                    n15 = 1.0f;
                                }
                                m.a(n15 * n9, 1.0f);
                                m.a(this.O);
                            }
                        }
                    }
                }
            }
        }
        this.a(o, n, n3);
    }
    
    protected void d() {
        super.d();
        this.w();
    }
    
    public static i a(final s a) {
        final i i = (i)bingo.i.i.a();
        i.a = a;
        return i;
    }
    
    public void f() {
        bingo.i.i.a(this);
    }
    
    public void g() {
        super.g();
        this.j = 1;
        this.k = -1;
        this.l = -1;
        this.n = -1;
        this.o = 0;
        this.p = true;
        this.E = false;
        this.O = false;
        this.P = false;
        this.X = 0;
        this.Y = 0;
        this.Z = -1;
        this.L = false;
    }
    
    public void h() {
        this.v();
        this.n();
        this.r();
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
    
    public i() {
        this.u = null;
        this.x = null;
        this.K = null;
        this.Q = null;
        this.W = null;
        this.eb = null;
        this.jb = null;
    }
    
    static {
        final String[] lb = new String[9];
        final int n = 0;
        final char[] charArray = "[L)\u000bye\\._;wU6_7cT8\u001a+6[?\u00196d\\z\u00136w]z\u0019+yTz\f<b\u0018".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0102: {
                if (n2 > 1) {
                    break Label_0102;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0016';
                            break;
                        }
                        case 1: {
                            c2 = '9';
                            break;
                        }
                        case 2: {
                            c2 = 'Z';
                            break;
                        }
                        case 3: {
                            c2 = '\u007f';
                            break;
                        }
                        default: {
                            c2 = 'Y';
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
        lb[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "XVz\f<b\u0019<\u0010+6[6\u001e:}\u00198\u001e5z\u0018".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0222: {
                if (n6 > 1) {
                    break Label_0222;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0016';
                            break;
                        }
                        case 1: {
                            c4 = '9';
                            break;
                        }
                        case 2: {
                            c4 = 'Z';
                            break;
                        }
                        case 3: {
                            c4 = '\u007f';
                            break;
                        }
                        default: {
                            c4 = 'Y';
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
        lb[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "XVz\f<b\u0019<\u0010+6^5\u0013=6S5\u0014<d\u0018".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0342: {
                if (n10 > 1) {
                    break Label_0342;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0016';
                            break;
                        }
                        case 1: {
                            c6 = '9';
                            break;
                        }
                        case 2: {
                            c6 = 'Z';
                            break;
                        }
                        case 3: {
                            c6 = '\u007f';
                            break;
                        }
                        default: {
                            c6 = 'Y';
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
        lb[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "XVz\f<b\u0019<\u0010+6Z5\u00136d\u00190\u00102sK{".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0462: {
                if (n14 > 1) {
                    break Label_0462;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u0016';
                            break;
                        }
                        case 1: {
                            c8 = '9';
                            break;
                        }
                        case 2: {
                            c8 = 'Z';
                            break;
                        }
                        case 3: {
                            c8 = '\u007f';
                            break;
                        }
                        default: {
                            c8 = 'Y';
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
        lb[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "AK5\u0011>6[;\u001356Z5\u0013,{W`".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0582: {
                if (n18 > 1) {
                    break Label_0582;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u0016';
                            break;
                        }
                        case 1: {
                            c10 = '9';
                            break;
                        }
                        case 2: {
                            c10 = 'Z';
                            break;
                        }
                        case 3: {
                            c10 = '\u007f';
                            break;
                        }
                        default: {
                            c10 = 'Y';
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
        lb[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "AK5\u0011>6[;\u001356K5\bc".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0702: {
                if (n22 > 1) {
                    break Label_0702;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u0016';
                            break;
                        }
                        case 1: {
                            c12 = '9';
                            break;
                        }
                        case 2: {
                            c12 = 'Z';
                            break;
                        }
                        case 3: {
                            c12 = '\u007f';
                            break;
                        }
                        default: {
                            c12 = 'Y';
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
        lb[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "AK5\u0011>6[;\u001356K;\u001b0cJ`".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0822: {
                if (n26 > 1) {
                    break Label_0822;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\u0016';
                            break;
                        }
                        case 1: {
                            c14 = '9';
                            break;
                        }
                        case 2: {
                            c14 = 'Z';
                            break;
                        }
                        case 3: {
                            c14 = '\u007f';
                            break;
                        }
                        default: {
                            c14 = 'Y';
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
        lb[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "AK5\u0011>6[;\u001356W/\u0012;sK`".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0942: {
                if (n30 > 1) {
                    break Label_0942;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\u0016';
                            break;
                        }
                        case 1: {
                            c16 = '9';
                            break;
                        }
                        case 2: {
                            c16 = 'Z';
                            break;
                        }
                        case 3: {
                            c16 = '\u007f';
                            break;
                        }
                        default: {
                            c16 = 'Y';
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
        lb[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "tP4\u001868P".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1062: {
                if (n34 > 1) {
                    break Label_1062;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '\u0016';
                            break;
                        }
                        case 1: {
                            c18 = '9';
                            break;
                        }
                        case 2: {
                            c18 = 'Z';
                            break;
                        }
                        case 3: {
                            c18 = '\u007f';
                            break;
                        }
                        default: {
                            c18 = 'Y';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 <= n36) {
                lb[n33] = new String(charArray9).intern();
                bingo.i.lb = lb;
                h = new float[] { 0.0f, 0.8f, 0.3f, 0.35f, 1.0f, 0.3f, 0.1f, 0.9f, 0.1f, 0.35f, 0.6f, 0.3f };
                bingo.i.i = new f((bingo.i.kb != null) ? bingo.i.kb : (bingo.i.kb = a(bingo.i.lb[8])));
                return;
            }
            continue;
        }
    }
}
