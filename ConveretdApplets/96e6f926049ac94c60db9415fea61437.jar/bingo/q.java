// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import zylom.ZylomDataGather;
import neat.lb;
import neat.r;
import a.x;
import neat.nb;
import neat.bb;
import a.gb;
import a.d;
import a.ib;
import neat.cb;
import neat.system.graphics.renderer.b;
import neat.system.graphics.renderer.m;
import a.db;
import a.z;
import neat.h;
import neat.kb;
import neat.system.f;
import a.u;
import a.s;

public class q extends p implements s, a.q, u
{
    private static f l;
    private boolean m;
    private kb n;
    private boolean o;
    private int p;
    private h q;
    private neat.p r;
    private z s;
    private db t;
    private kb u;
    private int v;
    private kb w;
    private kb x;
    private int y;
    private int z;
    private int A;
    private m B;
    private int C;
    private int D;
    private m E;
    private int F;
    private m G;
    public int H;
    public int I;
    public int J;
    private m K;
    private int L;
    private boolean M;
    private neat.system.graphics.renderer.b N;
    private static /* synthetic */ Class O;
    private static String[] P;
    
    public void a(final boolean m) {
        if (this.m == m) {
            return;
        }
        this.m = m;
        this.M();
    }
    
    public boolean a() {
        return this.m;
    }
    
    public void b() {
        this.c();
        this.a(((rc)this.a()).l);
    }
    
    public void c() {
        this.q();
        this.C();
        this.G();
        this.s();
    }
    
    public boolean a(final int n, final int n2) {
        return this.a() && this.b(n, n2);
    }
    
    private void a(final neat.cb cb) {
        this.k();
        if (cb == null) {
            return;
        }
        final neat.bb a = cb.a();
        if (!(a instanceof ib)) {
            throw new RuntimeException(bingo.q.P[6] + a);
        }
        this.n = a.a().b();
        final d d = (d)this.i.a((gb)a);
        if (d != null) {
            d.a(this);
            d.a(this.a());
        }
    }
    
    private void k() {
        if (this.n != null) {
            final d d = (d)this.i.b(this.n);
            if (d != null) {
                d.b(this);
                this.i.d(d);
            }
            if (this.n != null) {
                this.n.f();
                this.n = null;
            }
        }
    }
    
    private void l() {
        if (this.n != null) {
            final d d = (d)this.i.b(this.n);
            if (d != null) {
                d.a(this.a());
            }
        }
    }
    
    private void p() {
        final w x = this.a().X();
        if (x != null) {
            x.b(0);
            x.k();
        }
    }
    
    public void q() {
        this.p = 0;
        this.u();
    }
    
    public void b(final int p) {
        this.p = p;
        this.u();
    }
    
    public void c(final int n) {
        this.p += n;
        this.u();
    }
    
    public int r() {
        return this.p;
    }
    
    private void a(final bc bc) {
        this.s();
        if (bc == null) {
            return;
        }
        if (bc.o != null) {
            this.q = neat.h.e();
            this.r = neat.p.e();
            int n = 1;
            while (true) {
                final kb a = nb.a(n);
                boolean b = false;
                final ac ac = (ac)bc.o.c(a);
                if (ac != null) {
                    if (ac.h != null) {
                        final m b2 = this.i.b(ac.h);
                        if (b2 != null) {
                            b = true;
                            this.q.a(a.b(), b2);
                            b2.a(bc.f + ac.e, bc.g + ac.f);
                            b2.a(false);
                            this.r.e(ac.g);
                        }
                    }
                }
                a.f();
                if (!b) {
                    break;
                }
                n *= 10;
            }
        }
        else {
            if (bc.j != null) {
                this.s = this.i.b(bc.j);
                if (this.s == null) {
                    throw new RuntimeException(bingo.q.P[3] + bc.j);
                }
                if (bc.n != null) {
                    this.s.b(this.i, bc.n);
                }
                if (bc.m != null) {
                    this.s.a(this.i, bc.m);
                }
                (this.t = this.s.b(this.i)).a(this.i, bc.f, bc.g, bc.h, bc.i);
                this.t.a(this.i, bc.f, bc.g);
                this.t.a(this.i, bc.l);
                this.t.f(this.i);
            }
            if (bc.k != null) {
                this.u = bc.k.b();
            }
        }
        this.o = true;
        this.t();
        this.u();
    }
    
    private void s() {
        if (!this.o) {
            return;
        }
        this.o = false;
        if (this.t != null) {
            this.t.a(this.i);
            this.t = null;
        }
        if (this.s != null) {
            this.i.a(this.s);
            this.s = null;
        }
        if (this.u != null) {
            this.u.f();
            this.u = null;
        }
        if (this.q != null) {
            final r a = this.q.a();
            while (a.a()) {
                final kb kb = (kb)a.b();
                final m m = (m)this.q.g(kb);
                if (m != null) {
                    this.i.a(m);
                    this.q.b(kb);
                }
            }
            a.f();
            this.q.f();
            this.q = null;
        }
    }
    
    private void t() {
        if (!this.o) {
            return;
        }
        if (this.t != null) {
            if (this.a()) {
                this.t.e(this.i);
            }
            else {
                this.t.f(this.i);
            }
        }
        this.v();
    }
    
    private void u() {
        if (!this.o) {
            return;
        }
        if (this.t != null) {
            final lb a = lb.a();
            int p = this.p;
            while (true) {
                int n = p % 1000;
                for (int i = 0; i < 3; ++i) {
                    a.d(n % 10);
                    n /= 10;
                    if (n == 0 && p < 1000) {
                        break;
                    }
                }
                p /= 1000;
                if (p == 0) {
                    break;
                }
                a.a(',');
            }
            a.k();
            if (this.u != null) {
                a.a(this.u);
            }
            final kb b = a.b();
            this.t.b(this.i, b);
            this.t.e(this.i);
            b.f();
        }
        this.v();
    }
    
    private void v() {
        if (!this.o) {
            return;
        }
        if (this.q != null) {
            int p = this.p;
            int n = 1;
            int n2 = 0;
            while (true) {
                final kb a = nb.a(n);
                final int n3 = p % 10;
                p /= 10;
                boolean b = false;
                final m m = (m)this.q.g(a);
                if (m != null) {
                    boolean a2 = this.a();
                    if (n2 >= 1 && p == 0 && n3 == 0) {
                        a2 = false;
                    }
                    m.a(a2);
                    m.b(this.r.d(n2) + n3);
                    b = true;
                }
                a.f();
                if (!b) {
                    break;
                }
                n *= 10;
                ++n2;
            }
        }
    }
    
    public void w() {
        this.v = 0;
    }
    
    public void d(final int v) {
        this.v = v;
    }
    
    private void a(final xb xb) {
        this.x();
        if (xb.e != null) {
            final neat.bb a = xb.e.a();
            if (!(a instanceof ib)) {
                throw new RuntimeException(bingo.q.P[5] + xb.e);
            }
            this.w = a.a().b();
            final d d = (d)this.i.a((gb)a);
            if (d != null) {
                d.a(this);
            }
        }
        if (xb.f != null) {
            final neat.bb a2 = xb.f.a();
            if (!(a2 instanceof ib)) {
                throw new RuntimeException(bingo.q.P[4] + xb.f);
            }
            this.x = a2.a().b();
            final d d2 = (d)this.i.a((gb)a2);
            if (d2 != null) {
                d2.a(this);
            }
            final dd c = this.a().C();
            if (c != null) {
                this.y = (c.h ? c.i : 0);
                this.z = (c.j ? c.k : 0);
            }
            this.z();
        }
        this.y();
    }
    
    private void x() {
        if (this.w != null) {
            final d d = (d)this.i.b(this.w);
            if (d != null) {
                d.b(this);
                this.i.d(d);
            }
            if (this.w != null) {
                this.w.f();
                this.w = null;
            }
        }
        if (this.x != null) {
            final d d2 = (d)this.i.b(this.x);
            if (d2 != null) {
                d2.b(this);
                this.i.d(d2);
            }
            if (this.x != null) {
                this.x.f();
                this.x = null;
            }
        }
    }
    
    private void y() {
        if (this.w != null) {
            final d d = (d)this.i.b(this.w);
            if (d != null) {
                d.a(this.a());
            }
        }
        if (this.x != null) {
            final d d2 = (d)this.i.b(this.x);
            if (d2 != null) {
                d2.a(this.a());
            }
        }
    }
    
    public void z() {
        final dd c = this.a().C();
        if (c != null) {
            boolean h;
            if (this.i.l()) {
                final int n = c.h ? c.i : 0;
                final int n2 = c.j ? c.k : 0;
                h = (n > 0 || n2 > 0);
            }
            else {
                h = c.h;
            }
            ZylomDataGather.GetHelper().setMuted(!h);
            if (this.x != null) {
                final d d = (d)this.i.b(this.x);
                if (d != null) {
                    if (h) {
                        d.q();
                    }
                    else {
                        final rc rc = (rc)this.a();
                        if (rc.m != null) {
                            if (rc.m.g != null) {
                                d.b(rc.m.g);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void A() {
        final w x = this.a().X();
        if (x != null && x.a(true)) {
            x.k();
        }
    }
    
    private void B() {
        final dd c = this.a().C();
        if (c != null) {
            boolean h;
            if (this.i.l()) {
                final int n = c.h ? c.i : 0;
                final int n2 = c.j ? c.k : 0;
                h = (n > 0 || n2 > 0);
            }
            else {
                h = c.h;
            }
            final boolean h2 = h ^ true;
            ZylomDataGather.GetHelper().setMuted(!h2);
            if (h2) {
                if (this.i.l()) {
                    c.i = this.y;
                    c.k = this.z;
                    if (c.i <= 0 && c.k <= 0) {
                        final rc rc = (rc)this.a();
                        if (rc.m == null) {
                            return;
                        }
                        c.i = rc.m.h;
                        c.k = rc.m.i;
                    }
                    c.h = (c.i > 0);
                    c.j = (c.k > 0);
                }
                else {
                    c.h = h2;
                }
            }
            else {
                if (this.i.l()) {
                    this.y = c.i;
                    this.z = c.k;
                    c.i = 0;
                    c.j = false;
                    c.k = 0;
                }
                c.h = false;
            }
            this.a().B();
        }
    }
    
    private void e(final int n) {
    }
    
    public boolean b(final int n, final int n2) {
        if (this.x != null) {
            final d d = (d)this.i.b(this.x);
            if (d != null && d.b(n, n2)) {
                return true;
            }
        }
        return false;
    }
    
    public void C() {
        this.A = 0;
        this.F();
    }
    
    private void a(final yb yb) {
        this.D();
        if (yb == null) {
            return;
        }
        if (yb.e != null) {
            this.B = this.i.b(yb.e);
            if (this.B == null) {
                throw new RuntimeException(bingo.q.P[1] + yb.e);
            }
            this.B.a(false);
            this.C = 0;
            this.D = this.B.e();
        }
        if (yb.f != null) {
            this.E = this.i.b(yb.f);
            if (this.E == null) {
                throw new RuntimeException(bingo.q.P[1] + yb.f);
            }
            this.E.a(false);
            this.F = -1;
        }
        this.E();
        this.F();
    }
    
    private void D() {
        if (this.B != null) {
            this.i.a(this.B);
            this.B = null;
        }
        if (this.E != null) {
            this.i.a(this.E);
            this.E = null;
        }
    }
    
    private void E() {
        if (this.B != null) {
            this.B.a(this.a() && this.C > 0);
        }
        if (this.E != null) {
            this.E.a(this.a() && this.F >= 0);
        }
    }
    
    private void F() {
        final bingo.s fb = this.a().fb();
        if (fb == null) {
            return;
        }
        final int cb = fb.cb();
        final int db = fb.db();
        if (cb > 0 && cb >= db) {
            this.A = (cb - db) * 1000 / cb;
        }
        else {
            this.A = 0;
        }
        if (this.B != null) {
            final int c = this.D * this.A / 1000;
            if (this.C == c) {
                return;
            }
            this.C = c;
            this.E();
            if (this.B != null) {
                if (this.C <= 0) {
                    return;
                }
                this.B.b(this.C, this.B.f());
                this.B.a(0, 0, this.C, this.B.f());
            }
        }
        if (this.E != null) {
            int f = -1;
            if (this.A > 0 && this.E.p() > 0) {
                f = this.A * (this.E.p() - 1) / 1000;
            }
            if (this.F == f) {
                return;
            }
            this.F = f;
            if (this.F >= 0) {
                this.E.b(this.F);
            }
            this.E();
        }
    }
    
    private void f(final int n) {
        this.F();
    }
    
    public void G() {
        this.J();
    }
    
    private void a(final zb zb) {
        this.H();
        if (zb == null) {
            return;
        }
        this.H = zb.e;
        this.I = zb.f;
        this.J = zb.g;
        if (zb.h != null) {
            this.K = this.i.b(zb.h);
            if (this.K == null) {
                throw new RuntimeException(bingo.q.P[2] + zb.h);
            }
            this.K.a(false);
        }
        this.J();
    }
    
    private void H() {
        if (this.G != null) {
            this.i.a(this.G);
            this.G = null;
        }
        if (this.K != null) {
            this.i.a(this.K);
            this.K = null;
        }
    }
    
    private void I() {
        if (this.G != null) {
            this.G.a(this.a());
        }
        if (this.K != null) {
            this.K.a(this.a() && this.L >= 0);
        }
    }
    
    public void J() {
        final bingo.s fb = this.a().fb();
        if (fb == null) {
            return;
        }
        if (this.G != null) {
            this.i.a(this.G);
            this.G = null;
        }
        int e = 0;
        this.G = fb.O();
        if (this.G != null) {
            this.G.a(this.H, this.I);
            this.G.a(this.J);
            e = this.G.e();
        }
        this.L = fb.P();
        if (this.K != null) {
            this.K.a(this.H + e, this.K.d());
            if (this.L < 0 || this.L >= this.K.p()) {
                this.L = -1;
            }
            else {
                this.K.b(this.L);
            }
        }
        this.I();
    }
    
    private void K() {
        this.L();
        final rc rc = (rc)this.a();
        if (rc.i != null) {
            this.N = this.i.d(rc.i);
            if (this.N != null) {
                if (rc.j != null) {
                    this.i.a(this.N, rc.j);
                }
                this.N.a(this.a());
            }
        }
        if (rc.k != null) {
            this.a(rc.k);
        }
        if (rc.m != null) {
            this.a(rc.m);
        }
        if (rc.n != null) {
            this.a(rc.n);
        }
        if (rc.o != null) {
            this.a(rc.o);
        }
        this.M = true;
    }
    
    private void L() {
        if (!this.M) {
            return;
        }
        this.M = false;
        this.k();
        this.s();
        this.x();
        this.D();
        this.H();
        if (this.N != null) {
            this.N.a(false);
            this.N = null;
        }
    }
    
    private void M() {
        this.l();
        this.t();
        this.y();
        this.E();
        this.I();
        if (this.N != null) {
            this.N.a(this.a());
        }
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof rc)) {
            throw new RuntimeException(bingo.q.P[0] + gb);
        }
        final rc rc = (rc)gb;
        this.K();
    }
    
    public void d() {
    }
    
    public void a(final int n) {
        this.e(n);
        this.f(n);
    }
    
    public void a() {
    }
    
    public void a(final d d) {
        if (!this.a()) {
            return;
        }
        if (d.b().equals(this.n)) {
            this.p();
        }
        if (d.b().equals(this.w)) {
            this.A();
        }
        if (d.b().equals(this.x)) {
            this.B();
        }
    }
    
    public void b(final d d) {
    }
    
    public void c(final d d) {
        if (d.b().equals(this.n)) {
            this.n.f();
            this.n = null;
        }
        if (d.b().equals(this.w)) {
            this.w.f();
            this.w = null;
        }
        if (d.b().equals(this.x)) {
            this.x.f();
            this.x = null;
        }
    }
    
    public static q N() {
        return (q)q.l.a();
    }
    
    public void f() {
        bingo.q.l.a(this);
    }
    
    public void g() {
        super.g();
        this.m = false;
        this.M = false;
        this.o = false;
        this.y = 0;
        this.z = 0;
        this.A = 0;
    }
    
    public void h() {
        this.L();
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
    
    public q() {
        this.n = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.w = null;
        this.x = null;
        this.B = null;
        this.E = null;
        this.G = null;
        this.K = null;
        this.N = null;
    }
    
    static {
        final String[] p = new String[8];
        final int n = 0;
        final char[] charArray = "\f\u0004B9|+\u0004J.3/LD,|1\u0018N'|5\u0019X>|:\t\u000b+|\u001b\u0003E>.7\u0000{+2=\u0000x\"=<\u0003\\j55\u001cG/1=\u0002_+(1\u0003Ep".toCharArray();
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
                            c2 = 'X';
                            break;
                        }
                        case 1: {
                            c2 = 'l';
                            break;
                        }
                        case 2: {
                            c2 = '+';
                            break;
                        }
                        case 3: {
                            c2 = 'J';
                            break;
                        }
                        default: {
                            c2 = '\\';
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
        p[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u001b\rE>|4\u0003J.|:\rYj:*\u0003Fp".toCharArray();
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
                            c4 = 'X';
                            break;
                        }
                        case 1: {
                            c4 = 'l';
                            break;
                        }
                        case 2: {
                            c4 = '+';
                            break;
                        }
                        case 3: {
                            c4 = 'J';
                            break;
                        }
                        default: {
                            c4 = '\\';
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
        p[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u001b\rE>|4\u0003J.|4\t]/0x\bB-5,\u001f\u000b,.7\u0001\u0011".toCharArray();
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
                            c6 = 'X';
                            break;
                        }
                        case 1: {
                            c6 = 'l';
                            break;
                        }
                        case 2: {
                            c6 = '+';
                            break;
                        }
                        case 3: {
                            c6 = 'J';
                            break;
                        }
                        default: {
                            c6 = '\\';
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
        p[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u001b\rE>|4\u0003J.|>\u0003E>|>\u001eD'f".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0458: {
                if (n14 > 1) {
                    break Label_0458;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'X';
                            break;
                        }
                        case 1: {
                            c8 = 'l';
                            break;
                        }
                        case 2: {
                            c8 = '+';
                            break;
                        }
                        case 3: {
                            c8 = 'J';
                            break;
                        }
                        default: {
                            c8 = '\\';
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
        p[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\f\u0004Nj/7\u0019E.\u001a\u0000LI?(,\u0003Ej1-\u001f_j>=LB',4\tF/2,\r_#36LD,|\u001a\u0019_>36?C+87\u001b\u0011".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0578: {
                if (n18 > 1) {
                    break Label_0578;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'X';
                            break;
                        }
                        case 1: {
                            c10 = 'l';
                            break;
                        }
                        case 2: {
                            c10 = '+';
                            break;
                        }
                        case 3: {
                            c10 = 'J';
                            break;
                        }
                        default: {
                            c10 = '\\';
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
        p[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\f\u0004Nj4=\u0000[j>-\u0018_%2x\u0001^9(x\u000eNj55\u001cG/1=\u0002_+(1\u0003Ej3>Li?(,\u0003E\u001949\bD=f".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0698: {
                if (n22 > 1) {
                    break Label_0698;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'X';
                            break;
                        }
                        case 1: {
                            c12 = 'l';
                            break;
                        }
                        case 2: {
                            c12 = '+';
                            break;
                        }
                        case 3: {
                            c12 = 'J';
                            break;
                        }
                        default: {
                            c12 = '\\';
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
        p[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u0015\tE?|:\u0019_>36LF?/,LI/|1\u0001[&95\tE>=,\u0005D$|7\n\u000b\b),\u0018D$\u000f0\rO%+b".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0818: {
                if (n26 > 1) {
                    break Label_0818;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'X';
                            break;
                        }
                        case 1: {
                            c14 = 'l';
                            break;
                        }
                        case 2: {
                            c14 = '+';
                            break;
                        }
                        case 3: {
                            c14 = 'J';
                            break;
                        }
                        default: {
                            c14 = '\\';
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
        p[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = ":\u0005E-3v\u001d".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0938: {
                if (n30 > 1) {
                    break Label_0938;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'X';
                            break;
                        }
                        case 1: {
                            c16 = 'l';
                            break;
                        }
                        case 2: {
                            c16 = '+';
                            break;
                        }
                        case 3: {
                            c16 = 'J';
                            break;
                        }
                        default: {
                            c16 = '\\';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 <= n32) {
                p[n29] = new String(charArray8).intern();
                q.P = p;
                q.l = new f((q.O != null) ? q.O : (q.O = a(q.P[7])));
                return;
            }
            continue;
        }
    }
}
