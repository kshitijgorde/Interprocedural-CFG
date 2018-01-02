// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.graphics.renderer.a;
import neat.bb;
import neat.p;
import neat.system.graphics.renderer.m;
import neat.r;
import neat.lb;
import neat.fb;
import neat.i;
import a.z;
import neat.g;
import neat.system.f;
import neat.kb;
import neat.system.cb;

public class b implements cb
{
    public static final kb a;
    public static final kb b;
    private static final kb[] c;
    private static final int[] d;
    private static f e;
    private s f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private neat.system.graphics.renderer.b k;
    private int l;
    private int m;
    private g n;
    private int o;
    private z p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private kb y;
    private kb z;
    private kb A;
    private kb B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private kb I;
    private kb J;
    private int K;
    private int L;
    private kb M;
    private kb N;
    private int O;
    private int P;
    private kb Q;
    private kb R;
    private int S;
    private int T;
    private kb U;
    private kb V;
    private int W;
    private int X;
    private kb Y;
    private kb Z;
    private int ab;
    private int bb;
    private kb cb;
    private kb db;
    private kb eb;
    private kb fb;
    private kb gb;
    private kb hb;
    private kb ib;
    private kb jb;
    private kb kb;
    private kb lb;
    private i mb;
    private g nb;
    private g ob;
    private int pb;
    private int qb;
    private kb rb;
    private fb sb;
    private boolean tb;
    private int ub;
    private int vb;
    private i wb;
    private i xb;
    private int yb;
    private int zb;
    private int[] Ab;
    private i Bb;
    private int Cb;
    private g Db;
    private int[] Eb;
    private int[] Fb;
    private boolean[] Gb;
    private int Hb;
    private kb Ib;
    private int Jb;
    private int Kb;
    private kb Lb;
    private i Mb;
    private g Nb;
    private int Ob;
    private int Pb;
    private int Qb;
    private lb Rb;
    private kb Sb;
    private int Tb;
    private int Ub;
    private int Vb;
    private int Wb;
    private static /* synthetic */ Class Xb;
    private static String[] Yb;
    
    public void a(final int g, final int h) {
        this.g = g;
        this.h = h;
        this.c();
    }
    
    public void a(final int n) {
        this.g(n);
        this.h(n);
        this.j(n);
        this.f(n);
    }
    
    public boolean a() {
        return !this.Bb.e() || this.v() || this.D();
    }
    
    public void a(final jb jb, final int n) {
        this.b();
        this.b(jb, n);
    }
    
    private void b(final jb jb, final int n) {
        this.b();
        if (n == 0) {
            if (jb.e != null) {
                this.k = this.f.b(jb.e);
                if (this.k != null) {
                    if (jb.f != null) {
                        this.f.a(this.k, jb.f);
                    }
                    this.k.a(this.j);
                }
            }
        }
        else if (jb.g != null) {
            this.k = this.f.b(jb.g);
            if (this.k != null) {
                this.k.a(this.j);
                if (jb.h != null) {
                    this.f.a(this.k, jb.h);
                }
            }
        }
        this.l = jb.i;
        this.m = jb.j;
        this.a(jb);
        this.b(jb);
        this.d(jb);
        this.e(jb);
        this.c(jb);
        this.c();
    }
    
    private void b() {
        this.d();
        this.l();
        this.r();
        this.w();
        this.q();
        if (this.k != null) {
            this.k.a(false);
            this.k = null;
        }
    }
    
    public void a(final boolean j) {
        if (this.j == j) {
            return;
        }
        this.j = j;
        this.c();
    }
    
    private void c() {
        if (this.k != null) {
            if (this.g >= 0 && this.h >= 0 && this.l > 0 && this.m > 0) {
                this.k.b(this.g, this.h, this.l, this.m);
            }
            this.k.a(this.j);
        }
        this.k();
        this.p();
        this.s();
        this.C();
    }
    
    private void a(final jb jb) {
        this.d();
        this.o = this.f.N();
        if (jb.k != null) {
            this.p = this.f.b(jb.k);
        }
        this.q = jb.l;
        this.r = jb.m;
        this.s = jb.n;
        this.t = jb.o;
        this.u = jb.p;
        this.v = jb.q;
        this.w = jb.r;
        this.x = jb.s;
        if (jb.t != null) {
            this.y = jb.t.b();
        }
        if (jb.v != null) {
            this.A = jb.v.b();
        }
        if (jb.u != null) {
            this.z = jb.u.b();
        }
        if (jb.w != null) {
            this.B = jb.w.b();
        }
        this.C = jb.x;
        this.D = jb.y;
        this.E = jb.z;
        this.F = jb.A;
        this.G = jb.B;
        this.H = jb.C;
        if (jb.D != null) {
            this.I = jb.D.b();
        }
        if (jb.E != null) {
            this.J = jb.E.b();
        }
        this.K = jb.F;
        this.L = jb.G;
        if (jb.H != null) {
            this.M = jb.H.b();
        }
        if (jb.I != null) {
            this.N = jb.I.b();
        }
        this.O = jb.J;
        this.P = jb.K;
        if (jb.L != null) {
            this.Q = jb.L.b();
        }
        if (jb.M != null) {
            this.R = jb.M.b();
        }
        this.S = jb.N;
        this.T = jb.O;
        if (jb.P != null) {
            this.U = jb.P.b();
        }
        if (jb.Q != null) {
            this.V = jb.Q.b();
        }
        this.W = jb.R;
        this.X = jb.S;
        if (jb.T != null) {
            this.Y = jb.T.b();
        }
        if (jb.U != null) {
            this.Z = jb.U.b();
        }
        this.ab = jb.V;
        this.bb = jb.W;
        if (jb.X != null) {
            this.cb = jb.X.b();
        }
        if (jb.Y != null) {
            this.db = jb.Y.b();
        }
        if (jb.Z != null) {
            this.eb = jb.Z.b();
        }
        if (jb.ab != null) {
            this.fb = jb.ab.b();
        }
        if (jb.bb != null) {
            this.gb = jb.bb.b();
        }
        if (jb.cb != null) {
            this.hb = jb.cb.b();
        }
        if (jb.db != null) {
            this.ib = jb.db.b();
        }
        if (jb.eb != null) {
            this.jb = jb.eb.b();
        }
        if (jb.fb != null) {
            this.kb = jb.fb.b();
        }
        if (jb.gb != null) {
            this.lb = jb.gb.b();
        }
    }
    
    private void d() {
        this.e();
        if (this.p != null) {
            this.f.a(this.p);
            this.p = null;
        }
        if (this.y != null) {
            this.y.f();
            this.y = null;
        }
        if (this.A != null) {
            this.A.f();
            this.A = null;
        }
        if (this.z != null) {
            this.z.f();
            this.z = null;
        }
        if (this.B != null) {
            this.B.f();
            this.B = null;
        }
        if (this.I != null) {
            this.I.f();
            this.I = null;
        }
        if (this.J != null) {
            this.J.f();
            this.J = null;
        }
        if (this.M != null) {
            this.M.f();
            this.M = null;
        }
        if (this.N != null) {
            this.N.f();
            this.N = null;
        }
        if (this.Q != null) {
            this.Q.f();
            this.Q = null;
        }
        if (this.R != null) {
            this.R.f();
            this.R = null;
        }
        if (this.U != null) {
            this.U.f();
            this.U = null;
        }
        if (this.V != null) {
            this.V.f();
            this.V = null;
        }
        if (this.Y != null) {
            this.Y.f();
            this.Y = null;
        }
        if (this.Z != null) {
            this.Z.f();
            this.Z = null;
        }
        if (this.cb != null) {
            this.cb.f();
            this.cb = null;
        }
        if (this.db != null) {
            this.db.f();
            this.db = null;
        }
        if (this.eb != null) {
            this.eb.f();
            this.eb = null;
        }
        if (this.fb != null) {
            this.fb.f();
            this.fb = null;
        }
        if (this.gb != null) {
            this.gb.f();
            this.gb = null;
        }
        if (this.hb != null) {
            this.hb.f();
            this.hb = null;
        }
        if (this.ib != null) {
            this.ib.f();
            this.ib = null;
        }
        if (this.jb != null) {
            this.jb.f();
            this.jb = null;
        }
        if (this.kb != null) {
            this.kb.f();
            this.kb = null;
        }
        if (this.lb != null) {
            this.lb.f();
            this.lb = null;
        }
    }
    
    private void e() {
        final r d = this.n.d();
        while (d.a()) {
            final c c = (c)d.b();
            if (c != null) {
                if (c.j != null) {
                    this.f.a(c.j);
                    c.j = null;
                }
                if (c.k == null) {
                    continue;
                }
                final m k = c.k;
                c.k = null;
                this.c(k);
            }
        }
        d.f();
        this.n.i();
    }
    
    private void a(final m m) {
        final c b = this.b(m);
        if (b != null) {
            b.k = null;
            this.c(m);
        }
    }
    
    private c b(final m m) {
        if (m == null) {
            return null;
        }
        final r d = this.n.d();
        while (d.a()) {
            final c c = (c)d.b();
            if (c != null && c.k == m) {
                d.f();
                return c;
            }
        }
        d.f();
        return null;
    }
    
    private c a(final int n, final int n2, final boolean b) {
        final r d = this.n.d();
        while (d.a()) {
            final c c = (c)d.b();
            if (b == c.b && n >= c.c && n2 >= c.d && n <= c.c + this.s && n2 <= c.d + this.t) {
                d.f();
                return c;
            }
        }
        d.f();
        return null;
    }
    
    public void b(final int h) {
        if (this.p == null) {
            return;
        }
        final int n = this.o / 5;
        final int r = this.f.r();
        final char d = this.d(h);
        final int n2 = h * n + 1;
        final int n3 = n2 + n - 1;
        final p e = neat.p.e();
        for (int i = n2; i <= n3; ++i) {
            e.e(i);
        }
        for (int a = e.a(), j = 0; j < a * 3; ++j) {
            int k = this.f.J(a);
            final int l = this.f.J(a);
            if (k == l) {
                k = (k + a / 2 + 1) % a;
            }
            final int d2 = e.d(k);
            e.a(k, e.d(l));
            e.a(l, d2);
        }
        Label_0307: {
            if (r >= n2 && r <= n3) {
                for (int n4 = 0; n4 < e.a(); ++n4) {
                    if (e.d(n4) == r) {
                        e.d(n4, 1);
                        break;
                    }
                }
                int m = this.f.J(5);
                for (int n5 = 0; n5 < 5; ++n5) {
                    if (!this.a(this.c(m), d)) {
                        e.b(m, r);
                        break Label_0307;
                    }
                    if (++m >= 5) {
                        m = 0;
                    }
                }
                e.b(0, r);
            }
        }
        for (int g = 0; g < 5; ++g) {
            final char c = this.c(g);
            final c a2 = bingo.c.a();
            a2.c = h * this.u + this.q + this.g;
            a2.d = g * this.v + this.r + this.h;
            a2.e = a2.c + this.u / 2;
            a2.f = a2.d + this.v / 2;
            a2.g = g;
            a2.h = h;
            a2.i = e.d(g);
            a2.j = this.f.b(this.p);
            this.f.a(a2.j, a2.c + this.w, a2.d + this.x);
            final lb a3 = neat.lb.a();
            if (this.y != null) {
                a3.a(this.y);
            }
            a3.c(bingo.b.Yb[1]);
            a3.d(h + 1);
            a3.c("}");
            a3.d((a2.i - 1) % n + (a2.i - 1) / n * 15 + 1);
            final kb b = a3.b();
            this.f.a(a2.j, b);
            b.f();
            this.f.a(a2.j, this.j);
            this.n.a(g + h * 5, a2);
            if (this.a(c, d)) {
                this.a(a2);
            }
        }
        e.f();
    }
    
    public void i() {
        this.e();
        this.n.c(25);
        for (int i = 0; i < 5; ++i) {
            this.b(i);
        }
    }
    
    public void a(final p p3, final boolean b, final boolean b2) {
        final r d = this.n.d();
        while (d.a()) {
            final c c = (c)d.b();
            if (c.b == b2) {
                if (b) {
                    p3.e(c.i);
                }
                else {
                    if (p3.g(c.i)) {
                        continue;
                    }
                    p3.e(c.i);
                }
            }
        }
        d.f();
    }
    
    public void a(final c c) {
        c.b = true;
        if (c.j != null) {
            this.f.a(c.j);
            c.j = null;
        }
    }
    
    public boolean j() {
        final r d = this.n.d();
        while (d.a()) {
            if (!((c)d.b()).b) {
                d.f();
                return false;
            }
        }
        d.f();
        return true;
    }
    
    private void k() {
        final r d = this.n.d();
        while (d.a()) {
            final c c = (c)d.b();
            if (c.j != null) {
                this.f.a(c.j, this.j);
            }
        }
        d.f();
    }
    
    private boolean a(final char c, final char c2) {
        if (this.B == null) {
            return false;
        }
        final lb a = neat.lb.a();
        a.a(c2);
        a.a(c);
        final kb b = a.b();
        final boolean b2 = this.B.d(b) >= 0;
        b.f();
        return b2;
    }
    
    private final char c(final int n) {
        if (n < 0 || n >= 5) {
            return '-';
        }
        return (char)(48 + n);
    }
    
    private final char d(final int n) {
        char c = '-';
        switch (n) {
            case 0: {
                c = 'b';
                break;
            }
            case 1: {
                c = 'i';
                break;
            }
            case 2: {
                c = 'n';
                break;
            }
            case 3: {
                c = 'g';
                break;
            }
            case 4: {
                c = 'o';
                break;
            }
        }
        return c;
    }
    
    private final int a(final char c) {
        if (c < '0' || c >= '5') {
            return -1;
        }
        return c - '0';
    }
    
    private final int b(final char c) {
        int n = -1;
        switch (c) {
            case 'b': {
                n = 0;
                break;
            }
            case 'i': {
                n = 1;
                break;
            }
            case 'n': {
                n = 2;
                break;
            }
            case 'g': {
                n = 3;
                break;
            }
            case 'o': {
                n = 4;
                break;
            }
        }
        return n;
    }
    
    private void b(final jb jb) {
        this.l();
        this.nb.c(25);
        this.ob.c(25);
        for (int i = 0; i < 5; ++i) {
            neat.cb cb = null;
            switch (i) {
                case 0: {
                    cb = jb.hb;
                    break;
                }
                case 1: {
                    cb = jb.ib;
                    break;
                }
                case 2: {
                    cb = jb.jb;
                    break;
                }
                case 3: {
                    cb = jb.kb;
                    break;
                }
                case 4: {
                    cb = jb.lb;
                    break;
                }
            }
            if (cb != null) {
                for (int j = 0; j < 5; ++j) {
                    final m a = this.f.a(cb);
                    if (a == null) {
                        break;
                    }
                    a.a(false);
                    this.nb.a(j + i * 5, a);
                }
            }
        }
        this.pb = jb.mb;
        this.qb = jb.nb;
        if (jb.ob != null) {
            final neat.bb a2 = jb.ob.a();
            if (!(a2 instanceof fb)) {
                throw new RuntimeException(bingo.b.Yb[2] + jb.ob);
            }
            this.sb = (fb)a2;
        }
    }
    
    private void l() {
        final r d = this.nb.d();
        while (d.a()) {
            this.f.a((m)d.b());
        }
        d.f();
        this.nb.c();
        this.ob.c();
        if (this.sb != null) {
            this.sb.f();
            this.sb = null;
        }
    }
    
    public void e(final int n) {
        if (this.rb != null) {
            this.rb.f();
            this.rb = null;
        }
        if (this.sb != null) {
            this.rb = this.sb.d(n % this.sb.a()).b();
        }
        this.m();
    }
    
    private void m() {
        this.n();
        for (int i = 0; i < bingo.b.c.length; ++i) {
            this.mb.a(bingo.b.c[i].b());
        }
        if (this.rb != null) {
            this.mb.a(this.rb.b());
            this.ob.c();
            int n = 1;
            while (n + 1 < this.rb.d()) {
                final int b = this.b(this.rb.b(n));
                if (b < 0) {
                    break;
                }
                final int a = this.a(this.rb.b(n + 1));
                if (a < 0 || a >= 5) {
                    break;
                }
                n += 2;
                final int n2 = a + b * 5;
                if (n2 < 0 || n2 >= this.nb.e()) {
                    break;
                }
                final m m = (m)this.nb.a(n2);
                if (m == null) {
                    continue;
                }
                final int n3 = b * this.u;
                final int n4 = a * this.v;
                m.a(n3 + this.q + this.g + this.pb, n4 + this.r + this.h + this.qb);
                m.b(m.e(), this.v);
                m.a(0, n4, m.e(), this.v);
                this.ob.a(n2, m);
            }
        }
        this.p();
    }
    
    private void n() {
        this.mb.j();
        this.ob.c();
        this.p();
    }
    
    public void a(final kb kb) {
        final r f = this.mb.f();
        while (f.a()) {
            final kb kb2 = (kb)f.b();
            if (kb2.equals(kb)) {
                f.e();
                kb2.f();
            }
        }
        f.f();
    }
    
    public boolean b(final kb kb) {
        boolean b = true;
        int n = 0;
        Label_0039: {
            break Label_0039;
            c a = null;
            do {
                if (!a.b) {
                    b = false;
                    return b;
                }
                ++n;
                a = this.a(kb, n);
            } while (a != null);
        }
        return b;
    }
    
    private kb o() {
        kb kb = null;
        final r f = this.mb.f();
        while (f.a()) {
            final kb kb2 = (kb)f.b();
            final int c = this.c(kb2);
            if (c == -1) {
                continue;
            }
            if (!this.b(kb2)) {
                continue;
            }
            if (c == 3) {
                if (!this.f.a(this, kb2)) {
                    continue;
                }
                this.f.b(this, kb2);
            }
            f.e();
            kb = kb2;
            break;
        }
        f.f();
        return kb;
    }
    
    private int c(final kb kb) {
        if (kb == null) {
            return -1;
        }
        if (kb.d() <= 0) {
            return -1;
        }
        return kb.b(0) - '0';
    }
    
    private c a(final kb kb, int n) {
        c c = null;
        if (kb != null) {
            if (n >= 0) {
                n = n * 2 + 1;
                if (n + 1 < kb.d()) {
                    final int b = this.b(kb.b(n));
                    if (b >= 0) {
                        final int a = this.a(kb.b(n + 1));
                        if (a >= 0 && a < 5) {
                            final int n2 = a + b * 5;
                            if (n2 >= 0 && n2 < this.n.f()) {
                                c = (c)this.n.a(n2);
                            }
                        }
                    }
                }
            }
        }
        return c;
    }
    
    private void p() {
        final r d = this.nb.d();
        while (d.a()) {
            ((m)d.b()).a(false);
        }
        d.f();
        final r d2 = this.ob.d();
        while (d2.a()) {
            ((m)d2.b()).a(this.j);
        }
        d2.f();
    }
    
    public void b(final int ub, final int vb) {
        if (this.ub != ub || this.vb != vb) {
            this.ub = ub;
            this.vb = vb;
            this.tb = true;
        }
    }
    
    public boolean c(final int n, final int n2) {
        if (!this.f.x()) {
            return false;
        }
        boolean b = false;
        if (this.k != null) {
            final a o = this.k.o();
            if (n < o.b || n2 < o.c || n >= o.b + o.d || n2 >= o.c + o.e) {
                return false;
            }
            b = true;
        }
        final c a = this.a(n, n2, false);
        if (a != null) {
            boolean b2 = false;
            int n3 = -1;
            int n4 = -1;
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            boolean b3 = true;
            int n8 = 0;
            final int i = a.i;
            if (this.f.u(i)) {
                b2 = true;
                this.a(a);
                final bingo.i j = this.f.j(i);
                if (j != null) {
                    n3 = j.a();
                    n4 = j.c();
                    if (j.b() == 6) {
                        n5 = j.m();
                        b3 = false;
                    }
                    else {
                        n5 = this.C;
                    }
                    n6 = j.e();
                    n7 = j.f() - 20;
                    n8 = j.b();
                }
                this.f.a(i, j);
                this.f.a(i, 0, 0);
            }
            else {
                final bingo.i n9 = this.f.n(i);
                if (n9 != null) {
                    b2 = true;
                    this.a(a);
                    n5 = this.D;
                    n6 = n9.e();
                    n7 = n9.f() - 20;
                    n8 = n9.b();
                    n3 = n9.a();
                    n4 = n9.c();
                    this.f.a(i, n9);
                    this.f.v(i);
                    this.f.a(n9, 0, 0);
                }
            }
            if (b2) {
                this.f.c(this.A);
            }
            else {
                this.f.H(i);
                this.f.c(this.z);
            }
            if (n5 > 0) {
                int fb = this.f.fb();
                if (!b3) {
                    fb = 1;
                }
                final lb a2 = neat.lb.a();
                if (fb > 1 && this.eb != null) {
                    a2.a(this.eb);
                    a2.d(fb);
                    n5 *= fb;
                }
                if (n8 != 0) {
                    kb kb = null;
                    switch (n8) {
                        case 4: {
                            kb = this.fb;
                            break;
                        }
                        case 5: {
                            kb = this.gb;
                            break;
                        }
                        case 2: {
                            kb = this.hb;
                            break;
                        }
                        case 1: {
                            kb = this.ib;
                            break;
                        }
                        case 7: {
                            kb = this.jb;
                            break;
                        }
                        case 8: {
                            kb = this.kb;
                            break;
                        }
                        case 6: {
                            kb = this.lb;
                            break;
                        }
                    }
                    if (kb != null) {
                        if (a2.j() > 0) {
                            a2.c(bingo.b.Yb[3]);
                        }
                        a2.a(kb);
                    }
                }
                final kb b4 = a2.b();
                if (b4.d() == 0) {
                    this.f.b(n5, n6, n7);
                }
                else {
                    this.f.a(n5, b4, n6, n7);
                }
                b4.f();
            }
            if (b2) {
                this.d(n3, n4);
            }
            else {
                this.f.gb();
                this.f.Z();
            }
            b = true;
        }
        return b;
    }
    
    public c a(final bingo.i i) {
        if (i.b() == 9) {
            return null;
        }
        final boolean b = i.b() == 1 || i.b() == 2;
        c c = null;
        final r d = this.n.d();
        while (d.a()) {
            final c c2 = (c)d.b();
            if (c2.b) {
                continue;
            }
            final boolean u = this.f.u(c2.i);
            if (b) {
                if (u) {
                    continue;
                }
                if (i.b() == 1) {
                    if (i.d(c2.i)) {
                        c = c2;
                        break;
                    }
                    continue;
                }
                else {
                    if (i.b() == 2) {
                        c = c2;
                        break;
                    }
                    continue;
                }
            }
            else {
                if (i.d() == c2.i) {
                    c = c2;
                    break;
                }
                continue;
            }
        }
        d.f();
        return c;
    }
    
    private void c(final jb jb) {
        this.q();
        this.wb = neat.i.k();
        this.xb = neat.i.k();
        if (jb.Cb != null) {
            for (int i = 0; i < 5; ++i) {
                final m a = this.f.a(jb.Cb);
                if (a == null) {
                    throw new RuntimeException(bingo.b.Yb[0] + jb.Cb);
                }
                a.a(false);
                this.xb.a(a);
            }
        }
        this.yb = jb.Db;
        this.zb = jb.Eb;
        this.Ab[0] = 0;
        this.Ab[1] = this.Ab[0] + jb.Fb;
        this.Ab[2] = this.Ab[1] + jb.Gb;
        this.Ab[3] = this.Ab[2] + jb.Hb;
        this.Ab[4] = this.Ab[3] + jb.Ib;
        this.Ab[5] = this.Ab[4] + jb.Jb;
    }
    
    private void q() {
        if (this.wb != null) {
            final r f = this.wb.f();
            while (f.a()) {
                this.a((m)f.b());
            }
            f.f();
            this.wb.c();
            this.wb.f();
            this.wb = null;
        }
        if (this.xb != null) {
            final r f2 = this.xb.f();
            while (f2.a()) {
                this.f.a((m)f2.b());
            }
            f2.f();
            this.xb.c();
            this.xb.f();
            this.xb = null;
        }
    }
    
    private void c(final m m) {
        if (m == null) {
            return;
        }
        m.a(false);
        if (this.wb.d(m)) {
            this.xb.a(m);
        }
    }
    
    private void f(final int n) {
        if (this.wb == null || this.xb == null) {
            return;
        }
        final c a = this.a(this.ub, this.vb, false);
        if (a != null) {
            if (a.k == null) {
                if (!this.xb.e()) {
                    a.k = (m)this.xb.h();
                    this.wb.a(a.k);
                    final int n2 = this.Ab[a.h];
                    final int n3 = this.Ab[a.h + 1] - n2;
                    final int n4 = this.g + this.yb + n2;
                    final int f = a.k.f();
                    final int n5 = 0;
                    a.k.a(n4, this.h + this.zb + f * a.g);
                    a.k.a(n2, n5, n3, f);
                    a.k.b(n3, f);
                    a.k.a(0.0f, 1.0f);
                    a.k.a(true);
                    a.l = 0.0f;
                }
            }
        }
        final float n6 = n / 1000.0f;
        final r f2 = this.wb.f();
        while (f2.a()) {
            final m m = (m)f2.b();
            final c b = this.b(m);
            if (b == null) {
                this.c(m);
            }
            else {
                if (b == a) {
                    final c c = b;
                    c.l += n6 * 5.0f;
                    if (b.l > 1.0f) {
                        b.l = 1.0f;
                    }
                }
                else {
                    final c c2 = b;
                    c2.l -= n6 * 2.0f;
                    if (b.l < 0.0f) {
                        this.a(m);
                        continue;
                    }
                }
                m.a(b.l * 0.4f, 1.0f);
            }
        }
        f2.f();
    }
    
    private void d(final int n, final int n2) {
        final i k = neat.i.k();
        while (true) {
            Label_0049: {
                break Label_0049;
                kb o = null;
                do {
                    if (this.c(o) == -1) {
                        o.f();
                        final boolean b = false;
                        final boolean b2 = b | this.b(k, !b, n, n2);
                        if (!b2) {
                            final boolean b3 = b2 | this.a(k, !b2, n, n2);
                        }
                        k.j();
                        k.f();
                        return;
                    }
                    k.a(o);
                    o = this.o();
                } while (o != null);
            }
            continue;
        }
    }
    
    private boolean a(final i i, final boolean b, final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        if (b) {
            this.x();
        }
        final r f = i.f();
        while (f.a()) {
            final kb kb = (kb)f.b();
            final int c = this.c(kb);
            if (c != 1 && c != 0 && c != 2) {
                continue;
            }
            if (b) {
                this.e(kb);
            }
            ++n3;
            int n7 = 0;
            Label_0132: {
                break Label_0132;
                c a = null;
                do {
                    n4 += a.e;
                    n5 += a.f;
                    ++n6;
                    ++n7;
                    a = this.a(kb, n7);
                } while (a != null);
            }
            kb.f();
            f.e();
        }
        f.f();
        if (b) {
            this.y();
        }
        if (n3 == 0) {
            return false;
        }
        int n8;
        int n9;
        if (this.E >= 0) {
            n8 = this.g + this.E;
            n9 = this.h + this.F;
        }
        else {
            n8 = n4 / n6;
            n9 = n5 / n6;
        }
        int n10 = 0;
        kb kb2 = null;
        switch (n3) {
            default: {
                this.a(n8, n9, this.G, this.I);
                n10 = this.H;
                kb2 = this.J;
                break;
            }
            case 2: {
                this.a(n8, n9, this.K, this.M);
                n10 = this.L;
                kb2 = this.N;
                break;
            }
            case 3: {
                this.a(n8, n9, this.O, this.Q);
                n10 = this.P;
                kb2 = this.R;
                break;
            }
            case 4: {
                this.a(n8, n9, this.S, this.U);
                n10 = this.T;
                kb2 = this.V;
                break;
            }
        }
        if (n10 > 0) {
            this.f.a(n10, 100, 100, 0, n, n2);
        }
        this.f.c(kb2);
        this.t();
        this.f.i(n8, n9);
        return true;
    }
    
    private boolean b(final i i, final boolean b, final int n, final int n2) {
        boolean b2 = false;
        final r f = i.f();
        while (f.a()) {
            final kb kb = (kb)f.b();
            if (this.c(kb) != 3) {
                continue;
            }
            if (b) {
                this.f(kb);
                this.f.c(this, kb);
            }
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            Label_0114: {
                break Label_0114;
                c a = null;
                do {
                    n3 += a.e;
                    n4 += a.f;
                    ++n5;
                    a = this.a(kb, n5);
                } while (a != null);
            }
            int n6;
            int n7;
            if (this.E >= 0) {
                n6 = this.g + this.E;
                n7 = this.h + this.F;
            }
            else {
                n6 = n3 / n5;
                n7 = n4 / n5;
            }
            this.a(n6, n7, this.W, this.Y);
            if (this.X > 0) {
                this.f.a(this.X, 100, 100, 0, n, n2);
            }
            this.f.c(this.Z);
            b2 = true;
            this.t();
            this.f.j(n6, n7);
            kb.f();
            f.e();
        }
        f.f();
        return b2;
    }
    
    private void a(final int c, final int d, final int b, final kb kb) {
        if (b <= 0) {
            return;
        }
        final d a = d.a();
        a.c = c;
        a.d = d;
        a.b = b;
        if (kb != null) {
            a.e = kb.b();
        }
        this.Bb.a(a);
    }
    
    public void g(final int n) {
        if (this.Cb > 0) {
            this.Cb -= n;
            if (this.Cb > 0) {
                return;
            }
            this.Cb = 0;
        }
        if (this.Bb.e()) {
            return;
        }
        this.Cb = 1000;
        final d d = (d)this.Bb.b(0);
        if (d.e != null) {
            this.f.a(d.b, d.e, d.c, d.d);
        }
        else {
            this.f.b(d.b, d.c, d.d);
        }
        d.f();
    }
    
    private void d(final jb jb) {
        this.r();
        if (jb.pb == null) {
            return;
        }
        (this.Db = neat.g.j()).c(this.Gb.length);
        int n = 0;
        for (int i = 0; i < this.Db.f(); ++i) {
            this.Gb[i] = false;
            final m a = this.f.a(jb.pb);
            if (a != null) {
                int n2 = 0;
                switch (i) {
                    case 0: {
                        n2 = jb.sb;
                        break;
                    }
                    case 1: {
                        n2 = jb.tb;
                        break;
                    }
                    case 2: {
                        n2 = jb.ub;
                        break;
                    }
                    case 3: {
                        n2 = jb.vb;
                        break;
                    }
                    case 4: {
                        n2 = jb.wb;
                        break;
                    }
                }
                if (n2 > 0) {
                    final int f = a.f();
                    this.Eb[i] = jb.qb + n;
                    this.Fb[i] = jb.rb;
                    a.b(n2, f);
                    a.a(n, 0, n2, f);
                    a.a(false);
                    this.Db.a(i, a);
                    n += n2;
                }
            }
        }
        if (jb.xb != null) {
            this.Lb = jb.xb.b();
        }
    }
    
    private void r() {
        this.Hb = 0;
        if (this.Db != null) {
            final r d = this.Db.d();
            while (d.a()) {
                final m m = (m)d.b();
                if (m == null) {
                    continue;
                }
                this.f.a(m);
            }
            d.f();
            this.Db.c();
            this.Db.f();
            this.Db = null;
        }
        if (this.Ib != null) {
            this.Ib.f();
            this.Ib = null;
        }
        if (this.Lb != null) {
            this.Lb.f();
            this.Lb = null;
        }
    }
    
    private void s() {
        if (this.Db != null) {
            for (int i = 0; i < this.Db.f(); ++i) {
                final m m = (m)this.Db.a(i);
                if (m != null) {
                    if (this.j && this.Gb[i]) {
                        m.a(this.g + this.Eb[i], this.h + this.Fb[i]);
                        m.a(true);
                    }
                    else {
                        m.a(false);
                    }
                }
            }
        }
    }
    
    private void t() {
        if (this.Db == null) {
            return;
        }
        if (this.Db.e() == 0 || this.Lb == null) {
            return;
        }
        this.Hb = 1;
        this.d(this.Lb);
    }
    
    private void h(final int n) {
        switch (this.Hb) {
            case 0: {}
            case 1: {
                if (!this.i(n)) {
                    this.u();
                    this.Hb = 0;
                }
                break;
            }
        }
    }
    
    private void d(final kb kb) {
        this.u();
        if (kb == null) {
            return;
        }
        if (this.Ib != null) {
            this.Ib.f();
        }
        this.Ib = kb.b();
        this.Jb = 0;
        this.Kb = 0;
    }
    
    private void u() {
        if (this.Ib != null) {
            this.Ib.f();
            this.Ib = null;
        }
    }
    
    private boolean i(final int n) {
        if (this.Ib == null) {
            return false;
        }
        int n2 = 0;
        this.Kb += n;
        while (true) {
            while (this.Jb + 1 < this.Ib.d()) {
                final char b = this.Ib.b(this.Jb);
                int n3 = -1;
                if (b >= '0' && b <= '9') {
                    n3 = b - '0' + '\u0001';
                }
                else if (b == '.') {
                    n3 = 0;
                }
                if (n3 >= 0 && n3 < bingo.b.d.length) {
                    final int n4 = bingo.b.d[n3];
                    if (this.Kb < n4) {
                        if (n2 != 0) {
                            this.s();
                        }
                        return true;
                    }
                    this.Kb -= n4;
                    final char b2 = this.Ib.b(this.Jb + 1);
                    final boolean upperCase = Character.isUpperCase(b2);
                    final int b3 = this.b(Character.toLowerCase(b2));
                    if (b3 >= 0 && b3 < this.Db.f()) {
                        if (this.Gb[b3] != upperCase) {
                            this.Gb[b3] = upperCase;
                            n2 = 1;
                        }
                        this.Jb += 2;
                        continue;
                    }
                }
                this.u();
                this.s();
                return false;
            }
            continue;
        }
    }
    
    private boolean v() {
        return this.Hb != 0;
    }
    
    private void e(final jb jb) {
        this.w();
        if (jb.yb == null) {
            return;
        }
        this.Ob = jb.zb;
        this.Pb = jb.Ab;
        this.Qb = jb.Bb;
        this.Nb.c(25);
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                final m a = this.f.a(jb.yb);
                if (a == null) {
                    break;
                }
                final int n = a.e() / 5;
                final int f = a.f();
                a.b(this.Qb, f);
                a.a(i * this.Qb, 0, this.Qb, f);
                a.a(false);
                this.Nb.a(j + i * 5, a);
            }
        }
        this.Vb = 0;
    }
    
    private void w() {
        this.z();
        if (this.Rb != null) {
            this.Rb.f();
            this.Rb = null;
        }
        this.Mb.j();
        final r d = this.Nb.d();
        while (d.a()) {
            this.f.a((m)d.b());
        }
        d.f();
        this.Nb.c();
        this.Vb = 0;
    }
    
    private void x() {
        if (this.Rb != null) {
            this.Rb.f();
        }
        this.Rb = neat.lb.a();
    }
    
    private void e(final kb kb) {
        if (this.Rb == null) {
            return;
        }
        if (kb == null) {
            return;
        }
        if (kb.d() < 3) {
            return;
        }
        final kb d = kb.d(1);
        this.Rb.a(d);
        d.f();
    }
    
    private void y() {
        if (this.Rb == null) {
            return;
        }
        if (this.Rb.j() == 0) {
            return;
        }
        this.Mb.a(this.Rb.b());
        this.Rb = null;
    }
    
    public void f(final kb kb) {
        this.x();
        this.e(kb);
        this.y();
    }
    
    private void j(final int n) {
        final boolean b = !this.Mb.e();
        switch (this.Vb) {
            case 0: {
                if (this.Mb.e()) {
                    return;
                }
                if (!this.A()) {
                    return;
                }
                this.Vb = 1;
                this.Wb = 0;
                this.Tb = 0;
                this.Ub = 0;
                this.C();
                break;
            }
            case 1: {
                final int n2 = b ? 25 : 60;
                this.Wb += n;
                while (this.Wb >= n2) {
                    this.Wb -= n2;
                    ++this.Ub;
                    if (this.Ub >= this.B()) {
                        this.Vb = 2;
                        this.Wb = 0;
                        break;
                    }
                }
                this.C();
                break;
            }
            case 2: {
                final int n3 = b ? 250 : 500;
                this.Wb += n;
                if (this.f.W()) {
                    break;
                }
                if (this.Wb >= n3) {
                    this.Vb = 3;
                    this.Wb = 0;
                }
                break;
            }
            case 3: {
                final int n4 = b ? 25 : 60;
                this.Wb += n;
                while (this.Wb >= n4) {
                    this.Wb -= n4;
                    ++this.Tb;
                    if (this.Tb >= this.B()) {
                        this.z();
                        this.Vb = 0;
                        break;
                    }
                }
                this.C();
                break;
            }
        }
    }
    
    private void z() {
        if (this.Sb != null) {
            this.Sb.f();
            this.Sb = null;
        }
        this.C();
    }
    
    private boolean A() {
        this.z();
        if (this.Mb.e()) {
            return false;
        }
        this.Sb = (kb)this.Mb.b(0);
        this.Tb = 0;
        this.Ub = this.B();
        return true;
    }
    
    private int B() {
        if (this.Sb == null) {
            return 0;
        }
        return this.Sb.d() / 2;
    }
    
    private void C() {
        final r d = this.Nb.d();
        while (d.a()) {
            ((m)d.b()).a(false);
        }
        d.f();
        if (this.Sb == null || !this.j) {
            return;
        }
        int n = this.Tb * 2;
        int d2 = this.Ub * 2;
        if (d2 > this.Sb.d()) {
            d2 = this.Sb.d();
        }
        while (n + 1 < d2) {
            final int b = this.b(this.Sb.b(n));
            if (b >= 0) {
                final int a = this.a(this.Sb.b(n + 1));
                if (a >= 0 && a < 5) {
                    n += 2;
                    final int n2 = a + b * 5;
                    if (n2 >= 0 && n2 < this.Nb.e()) {
                        final m m = (m)this.Nb.a(n2);
                        if (m == null) {
                            continue;
                        }
                        m.a(b * this.u + this.Ob + this.g, a * this.v + this.Pb + this.h);
                        m.a(true);
                        continue;
                    }
                }
            }
        }
    }
    
    private boolean D() {
        return this.Vb != 0;
    }
    
    public static b a(final s f) {
        final b b = (b)bingo.b.e.a();
        b.f = f;
        return b;
    }
    
    public void f() {
        bingo.b.e.a(this);
    }
    
    public void g() {
        this.g = -1;
        this.h = -1;
        this.i = 0;
        this.l = -1;
        this.m = -1;
        this.n = neat.g.j();
        this.o = 75;
        this.q = 0;
        this.r = 0;
        this.u = 0;
        this.v = 0;
        this.mb = neat.i.k();
        this.nb = neat.g.j();
        this.ob = neat.g.j();
        this.C = 0;
        this.D = 0;
        this.G = 0;
        this.H = 0;
        this.W = 0;
        this.X = 0;
        this.ab = 0;
        this.bb = 0;
        this.Bb = neat.i.k();
        this.Cb = 0;
        this.Mb = neat.i.k();
        this.Nb = neat.g.j();
    }
    
    public void h() {
        this.e();
        this.b();
        this.n.f();
        this.n = null;
        this.mb.j();
        this.mb.f();
        this.mb = null;
        if (this.rb != null) {
            this.rb.f();
            this.rb = null;
        }
        this.nb.f();
        this.nb = null;
        this.ob.f();
        this.ob = null;
        this.Bb.j();
        this.Bb.f();
        this.Bb = null;
        this.Mb.f();
        this.Mb = null;
        this.Nb.f();
        this.Nb = null;
        this.f = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public b() {
        this.f = null;
        this.k = null;
        this.n = null;
        this.p = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.I = null;
        this.J = null;
        this.M = null;
        this.N = null;
        this.Q = null;
        this.R = null;
        this.U = null;
        this.V = null;
        this.Y = null;
        this.Z = null;
        this.cb = null;
        this.db = null;
        this.eb = null;
        this.fb = null;
        this.gb = null;
        this.hb = null;
        this.ib = null;
        this.jb = null;
        this.kb = null;
        this.lb = null;
        this.mb = null;
        this.nb = null;
        this.ob = null;
        this.rb = null;
        this.sb = null;
        this.wb = null;
        this.xb = null;
        this.Ab = new int[6];
        this.Bb = null;
        this.Db = null;
        this.Eb = new int[5];
        this.Fb = new int[5];
        this.Gb = new boolean[5];
        this.Ib = null;
        this.Lb = null;
        this.Mb = null;
        this.Nb = null;
        this.Rb = null;
        this.Sb = null;
    }
    
    static {
        bingo.b.Yb = new String[] { z(z(":F\feLYK\r#\\YK\u000b%P\r\u0007\u00112J\u0010S\u0007b^\u0016UB!Y\u000bCB,M\u0014E\u00070\u0002")), z(z("\u0002g")), z(z(":F\feLYK\r#\\YW\u00036L\u001cU\f1\u0002")), z(z("\u0002I\u001f")), z(z("\u001bN\f%WWE")) };
        a = kb.a(z(z("H\u0013Nq\u0014N\u000bSr\u0014M\u000bS{\u0014K\u0012Np\u000bU\u0016Tn\u000bI\u000bQv\u0014J\u0012Nv\rU\u0013Pn\u000b@\u000bVu\u0014L\u001fNv\u0001U\u0012Sn\rK\u000bUr\u0014N\u0015Nt\u000fU\u0010Wn\u000eO")));
        b = kb.a(z(z("IER \t\u001b\u0015\u0000qZM")));
        c = new kb[] { kb.a(z(z("IER \t\u001b\u0015\u0000qZM"))), kb.a(z(z("INR+\t\u0010\u0015\u000bqQM"))), kb.a(z(z("IIR,\t\u0017\u0015\fqVM"))), kb.a(z(z("I@R%\t\u001e\u0015\u0005q_M"))), kb.a(z(z("IHR-\t\u0016\u0015\rqWM"))), kb.a(z(z("HER+\b\u0017\u0017\u0005rWI"))), kb.a(z(z("HES+\t\u0017\u0016\u0005sWH"))), kb.a(z(z("HEP+\n\u0017\u0015\u0005pWK"))), kb.a(z(z("HEQ+\u000b\u0017\u0014\u0005qWJ"))), kb.a(z(z("HEV+\f\u0017\u0013\u0005vWM"))), kb.a(z(z("KER+\t\u0017\u0015\u0005qWM"))), kb.a(z(z("KEV+\u000b\u0017\u0015\u0005sWI"))), kb.a(z(z("MER \t\u001b\u0015\u0000qZMNR+\t\u0010\u0015\u000bqQMIR,\t\u0017\u0015\fqVM@R%\t\u001e\u0015\u0005q_MHR-\t\u0016\u0015\rqWM"))) };
        d = new int[] { 0, 20, 50, 100, 150, 200, 250, 500, 1000, 2000, 3000 };
        bingo.b.e = new f((bingo.b.Xb != null) ? bingo.b.Xb : (bingo.b.Xb = a(bingo.b.Yb[4])));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= '8';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'y';
                            break;
                        }
                        case 1: {
                            c2 = '\'';
                            break;
                        }
                        case 2: {
                            c2 = 'b';
                            break;
                        }
                        case 3: {
                            c2 = 'B';
                            break;
                        }
                        default: {
                            c2 = '8';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
