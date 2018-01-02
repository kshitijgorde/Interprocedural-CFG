// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.l;
import a.gb;
import a.ib;
import a.d;
import neat.system.graphics.renderer.b;
import neat.bb;
import a.yb;
import neat.cb;
import a.x;
import neat.system.graphics.renderer.a;
import neat.kb;
import neat.system.graphics.renderer.m;
import a.db;
import a.z;
import neat.system.f;
import a.u;
import a.o;
import a.i;
import a.q;
import a.s;

public class t extends p implements s, q, i, o, a.p, u
{
    private static f l;
    private z m;
    private boolean n;
    private boolean o;
    private boolean p;
    private eb q;
    private db r;
    private boolean s;
    private m t;
    private m u;
    private neat.i v;
    private m w;
    private kb x;
    private boolean y;
    private int z;
    private int A;
    private float B;
    private a C;
    private boolean D;
    private boolean E;
    private kb F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private static /* synthetic */ Class L;
    private static String[] M;
    
    public void b() {
        this.c();
        final tc tc = (tc)this.a();
        if (tc.j != null) {
            this.m = this.i.b(tc.j);
            if (this.m == null) {
                throw new RuntimeException(bingo.t.M[3] + tc.j);
            }
        }
        this.a().B();
    }
    
    public void c() {
        this.s();
        this.A();
        if (this.m != null) {
            this.i.a(this.m);
            this.m = null;
        }
    }
    
    void a(final kb kb) {
        this.m.a(this.i, kb);
    }
    
    void k() {
        this.m.a(this.i, ((tc)this.a()).k);
    }
    
    public void l() {
        if (this.n) {
            return;
        }
        this.n = true;
        this.r();
    }
    
    public void p() {
        if (!this.n) {
            return;
        }
        this.n = false;
        this.r();
    }
    
    public boolean q() {
        if (this.q != null && this.q.z()) {
            return true;
        }
        if (!this.o) {
            final r u = this.a().U();
            if (u != null && u.u()) {
                return false;
            }
        }
        return this.n;
    }
    
    public void a(final boolean p) {
        if (this.p != p) {
            if (!(this.p = p)) {
                this.s();
            }
            else {
                this.r();
            }
        }
    }
    
    private void r() {
        this.v();
    }
    
    public eb a(final neat.cb cb, final boolean b) {
        if (cb == null) {
            return null;
        }
        final neat.bb a = cb.a();
        if (!(a instanceof yb)) {
            throw new RuntimeException(bingo.t.M[4] + a);
        }
        final eb a2 = this.a((yb)a, b);
        cb.c();
        return a2;
    }
    
    public eb a(final yb yb, final boolean s) {
        if (this.q != null) {
            yb.f();
            return null;
        }
        this.q = eb.b(this);
        this.s = s;
        this.q.a(yb);
        this.q.a(true);
        return this.q;
    }
    
    public void s() {
        if (this.q != null) {
            this.q.f();
            this.q = null;
            this.v();
        }
        this.z();
    }
    
    public boolean t() {
        return this.q != null;
    }
    
    public boolean u() {
        return this.q() && this.q != null && this.q.s();
    }
    
    private void b(final int n) {
        if (this.q != null) {
            this.q.c(n);
            if (!this.u()) {
                this.s();
                return;
            }
        }
        if (this.y) {
            this.y = false;
            if (this.u()) {
                this.s();
            }
        }
    }
    
    void v() {
        this.w();
        if (!this.u() || !this.p) {
            return;
        }
        int n = 0;
        final kb t = this.q.t();
        if (t != null && this.b(t)) {
            n = 1;
            final neat.system.graphics.renderer.b r = this.i.r();
            if (r != null && (this.C.b < r.k() || this.C.c < r.l() || this.C.b + this.C.f() > r.k() + r.m() || this.C.c + this.C.g() > r.l() + r.n())) {
                n = 0;
                this.w();
            }
        }
        if (n == 0) {
            final kb u = this.q.u();
            if (u != null && this.b(u)) {
                n = 1;
            }
        }
        if (n != 0) {
            this.x();
        }
    }
    
    private void w() {
        if (this.t != null) {
            this.i.a(this.t);
            this.t = null;
        }
        if (this.u != null) {
            this.i.a(this.u);
            this.u = null;
        }
        if (this.v != null) {
            final neat.r f = this.v.f();
            while (f.a()) {
                this.i.a((m)f.b());
            }
            f.f();
            this.v.f();
            this.v = null;
        }
        if (this.w != null) {
            this.i.a(this.w);
            this.w = null;
        }
        if (this.x != null) {
            final d d = (d)this.i.b(this.x);
            if (d != null) {
                this.i.d(d);
            }
            else {
                this.x.f();
                this.x = null;
            }
        }
        if (this.C != null) {
            this.C.f();
            this.C = null;
        }
        if (this.r != null) {
            this.m.a(this.i, this.r);
            this.r = null;
        }
        this.z = 0;
        this.A = 0;
        this.B = 1.0f;
    }
    
    private boolean b(final kb kb) {
        final kb q = this.q.q();
        final int v = this.q.v();
        final int w = this.q.w();
        final int x = this.q.x();
        final tc tc = (tc)this.a();
        final ic ic = (ic)tc.i.c(kb);
        if (ic == null) {
            return false;
        }
        if (ic.e != 0 && ic.f != 0) {
            throw new RuntimeException(bingo.t.M[2] + ic);
        }
        this.C = neat.system.graphics.renderer.a.h();
        if (ic.p != null) {
            this.t = this.i.b(ic.p);
            if (this.t != null) {
                this.t.a(w + ic.q, x + ic.r);
                this.t.a(true);
                final a h = neat.system.graphics.renderer.a.h();
                h.a(this.t.c(), this.t.d(), this.t.e(), this.t.f());
                this.C.c(h);
                h.f();
            }
        }
        if (ic.g != null) {
            this.u = this.i.b(ic.g);
            if (this.u != null) {
                this.u.a(w + ic.h, x + ic.i);
                this.u.a(true);
                final a h2 = neat.system.graphics.renderer.a.h();
                h2.a(this.u.c(), this.u.d(), this.u.e(), this.u.f());
                this.C.c(h2);
                h2.f();
            }
        }
        int n = 0;
        int n2 = 0;
        if (ic.j != null) {
            this.v = neat.i.k();
            for (int i = 0; i < v; ++i) {
                final m b = this.i.b(ic.j);
                if (b != null) {
                    b.a(w + ic.k + n, x + ic.l + n2);
                    b.a(true);
                    final a h3 = neat.system.graphics.renderer.a.h();
                    h3.a(b.c(), b.d(), b.e(), b.f());
                    this.C.c(h3);
                    h3.f();
                    n -= b.e() * ic.e;
                    n2 -= b.f() * ic.f;
                }
                this.v.a(b);
            }
        }
        if (ic.m != null) {
            this.w = this.i.b(ic.m);
            if (this.w != null) {
                this.w.a(w + ic.n + n, x + ic.o + n2);
                this.w.a(true);
                final a h4 = neat.system.graphics.renderer.a.h();
                h4.a(this.w.c(), this.w.d(), this.w.e(), this.w.f());
                this.C.c(h4);
                h4.f();
            }
        }
        if (ic.w != null) {
            final neat.bb a = ic.w.a();
            if (!(a instanceof ib)) {
                throw new RuntimeException(bingo.t.M[1] + a);
            }
            this.x = a.a().b();
            final d d = (d)this.i.a((gb)a);
            if (d != null) {
                d.a(this);
                d.a(true);
                if (ic.z) {
                    d.a(w + ic.x + n, x + ic.y + n2);
                }
                else {
                    d.a(w + ic.x, x + ic.y);
                }
            }
        }
        if (q != null) {
            final kb m = this.q.m();
            if (m == null) {
                this.k();
            }
            else {
                this.a(m);
            }
            int n3 = w + ic.s;
            int n4 = x + ic.t;
            final int u = ic.u;
            int n5;
            if (n >= 0) {
                n5 = u + n;
            }
            else {
                n5 = u + -n;
                n3 += n;
            }
            final int v2 = ic.v;
            int n6;
            if (n2 >= 0) {
                n6 = v2 + n2;
            }
            else {
                n6 = v2 + -n2;
                n4 += n2;
            }
            if (this.r != null) {
                this.m.a(this.i, this.r);
            }
            (this.r = this.m.b(this.i)).a(this.i, n3, n4);
            this.r.a(this.i, n3, n4, n5, n6);
            this.r.b(this.i, q);
            this.r.e(this.i);
            this.r.a(this.i, true, true);
            this.r.a(this.i, true);
            this.C.c(this.r.d());
        }
        if (tc.m != null) {
            this.a().b(tc.m);
        }
        return true;
    }
    
    void x() {
        if (!this.u()) {
            return;
        }
        final tc tc = (tc)this.a();
        final int w = this.q.w();
        final int x = this.q.x();
        final kb t = this.q.t();
        if (t == null) {
            return;
        }
        final ic ic = (ic)tc.i.c(t);
        if (ic == null) {
            return;
        }
        int z = 0;
        int a = 0;
        if (this.q.n()) {
            final neat.system.graphics.renderer.b r = this.i.r();
            if (r != null) {
                final a o = r.o();
                float p = this.q.p();
                if (this.q.o()) {
                    p = 1.0f - p;
                }
                if (p < 0.0f) {
                    p = 0.0f;
                }
                else if (p > 1.0f) {
                    p = 1.0f;
                }
                int n = w;
                if (ic.e > 0) {
                    n = o.d() - (this.C.d() + this.C.f() - w);
                }
                else if (ic.e < 0) {
                    n = o.d() + o.f() - (this.C.d() - w);
                }
                int n2 = x;
                if (ic.f > 0) {
                    n2 = o.e() - (this.C.e() + this.C.g() - x);
                }
                else if (ic.f < 0) {
                    n2 = o.e() + o.g() - (this.C.e() - x);
                }
                z = (int)((n - w) * p);
                a = (int)((n2 - x) * p);
            }
        }
        if (this.z != z || this.A != a) {
            final int n3 = z - this.z;
            final int n4 = a - this.A;
            if (this.t != null) {
                this.t.a(this.t.c() + n3, this.t.d() + n4);
            }
            if (this.u != null) {
                this.u.a(this.u.c() + n3, this.u.d() + n4);
            }
            if (this.v != null) {
                final neat.r f = this.v.f();
                while (f.a()) {
                    final m m = (m)f.b();
                    m.a(m.c() + n3, m.d() + n4);
                }
                f.f();
            }
            if (this.w != null) {
                this.w.a(this.w.c() + n3, this.w.d() + n4);
            }
            if (this.r != null) {
                this.r.b(this.i, n3, n4);
            }
            this.z = z;
            this.A = a;
        }
    }
    
    private void b(final boolean b) {
        if (this.q == null || this.q.d(b)) {}
    }
    
    private boolean a(final int n, final int n2) {
        if (!this.u()) {
            return false;
        }
        if (this.C == null) {
            return false;
        }
        if (!this.C.b()) {
            return false;
        }
        final int d = this.C.d();
        final int e = this.C.e();
        final int n3 = d + this.C.f();
        final int n4 = e + this.C.g();
        return n >= d && n2 >= e && n < n3 && n2 < n4;
    }
    
    private void y() {
        if (this.r != null) {
            this.r.s(this.i);
        }
    }
    
    private void z() {
        if (this.D) {
            this.E = true;
            this.a().b(((tc)this.a()).n);
        }
        this.D = false;
        this.K = 0;
    }
    
    private void A() {
        if (this.F != null) {
            this.F.f();
            this.F = null;
        }
        this.G = -1;
        this.H = -1;
    }
    
    private jc B() {
        final tc tc = (tc)this.a();
        if (tc.p == null) {
            return null;
        }
        if (this.F != null) {
            final jc jc = (jc)tc.p.c(this.F);
            if (jc != null) {
                return jc;
            }
        }
        this.z();
        return null;
    }
    
    private void b(final int i, final int j) {
        final boolean b = i != this.I || j != this.J;
        if (b) {
            this.I = i;
            this.J = j;
        }
        if (!this.p) {
            return;
        }
        if (this.D) {
            if (!this.t()) {
                this.z();
                return;
            }
            if (b) {
                final jc b2 = this.B();
                if (b2 != null && (i < this.G || j < this.H || i >= this.G + b2.h || j >= this.H + b2.i) && !this.a(i, j) && this.q.d(false)) {
                    this.z();
                }
            }
        }
        else if (b) {
            this.K = 0;
            this.E = false;
        }
    }
    
    private void c(final int n) {
        if (!this.D && !this.E) {
            this.K += n;
            final tc tc = (tc)this.a();
            if (!this.t() && this.K >= tc.o) {
                this.A();
                if (this.q() && tc.p != null) {
                    if (this.F == null) {
                        final neat.r c = tc.p.c();
                        while (c.a()) {
                            final jc jc = (jc)c.b();
                            if (jc.f >= 0 && jc.g >= 0 && this.I >= jc.f && this.J >= jc.g && this.I < jc.f + jc.h && this.J < jc.g + jc.i) {
                                boolean b = false;
                                switch (jc.j) {
                                    case -1: {
                                        b = true;
                                        break;
                                    }
                                    case 0: {
                                        b = this.a().Q();
                                        break;
                                    }
                                    case 3: {
                                        b = this.a().O();
                                        break;
                                    }
                                }
                                if (b) {
                                    this.F = tc.p.a(c.d()).b();
                                    this.G = jc.f;
                                    this.H = jc.g;
                                    break;
                                }
                                continue;
                            }
                        }
                        c.f();
                    }
                    if (this.F == null) {
                        final neat.r e = this.i.e();
                        while (e.a()) {
                            final b b2 = (b)e.b();
                            if (b2 instanceof n) {
                                final n n2 = (n)b2;
                                final kb a = n2.a();
                                if (a == null) {
                                    continue;
                                }
                                final jc jc2 = (jc)tc.p.c(a);
                                if (jc2 == null) {
                                    continue;
                                }
                                int f = jc2.f;
                                if (f < 0) {
                                    f = n2.b() - jc2.h / 2;
                                }
                                int g = jc2.g;
                                if (g < 0) {
                                    g = n2.c() - jc2.i / 2;
                                }
                                if (this.I >= f && this.J >= g && this.I < f + jc2.h && this.J < g + jc2.i) {
                                    this.F = a.b();
                                    this.G = f;
                                    this.H = g;
                                    break;
                                }
                                continue;
                            }
                        }
                        e.f();
                    }
                }
                final jc b3 = this.B();
                if (b3 != null) {
                    final eb a2 = this.a(b3.e, true);
                    if (a2 != null) {
                        this.D = true;
                        a2.a(this.I, this.J);
                    }
                }
            }
        }
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof tc)) {
            throw new RuntimeException(bingo.t.M[0] + gb);
        }
        final tc tc = (tc)gb;
    }
    
    public void d() {
        this.b();
    }
    
    public void a(final int n) {
        this.b(n);
        this.c(n);
    }
    
    public void a() {
        this.y();
    }
    
    public boolean a(final neat.system.n n) {
        this.b(n.d, n.e);
        if (this.u() && this.a(n.d, n.e)) {
            if (this.x != null) {
                final d d = (d)this.i.b(this.x);
                if (d != null) {
                    d.a(n);
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean a(final neat.system.o o) {
        this.b(o.d, o.e);
        if (this.u()) {
            final boolean a = this.a(o.d, o.e);
            this.b(a);
            if (a) {
                if (this.x != null) {
                    final d d = (d)this.i.b(this.x);
                    if (d != null) {
                        d.a(o);
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean a(final l l) {
        this.b(l.d, l.e);
        if (this.u() && this.a(l.d, l.e)) {
            if (this.x != null) {
                final d d = (d)this.i.b(this.x);
                if (d != null) {
                    d.a(l);
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean a(final neat.system.m m) {
        this.b(m.d, m.e);
        if (this.u() && this.a(m.d, m.e)) {
            if (this.x != null) {
                final d d = (d)this.i.b(this.x);
                if (d != null) {
                    d.a(m);
                }
            }
            return true;
        }
        return false;
    }
    
    public void a(final d d) {
        if (d.b().equals(this.x)) {
            this.y = true;
        }
    }
    
    public void b(final d d) {
    }
    
    public void c(final d d) {
        if (d.b().equals(this.x)) {
            this.x.f();
            this.x = null;
        }
    }
    
    public static t C() {
        return (t)t.l.a();
    }
    
    public void f() {
        bingo.t.l.a(this);
    }
    
    public void g() {
        super.g();
        this.n = false;
        this.o = false;
        this.p = false;
        this.s = false;
        this.D = false;
        this.I = -1;
        this.J = -1;
    }
    
    public void h() {
        this.o = true;
        this.c();
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
    
    public t() {
        this.m = null;
        this.q = null;
        this.r = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.C = null;
        this.F = null;
    }
    
    static {
        final String[] m = new String[6];
        final int n = 0;
        final char[] charArray = "sPj\u001f\nTPb\bEP\u0018l\n\nNLf\u0001\nJMp\u0018\nE]#\r\no]o\u001cyOYg\u0003]\u0007Qn\u001cFBUf\u0002^FLj\u0003D\u001d".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0101: {
                if (n2 > 1) {
                    break Label_0101;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\'';
                            break;
                        }
                        case 1: {
                            c2 = '8';
                            break;
                        }
                        case 2: {
                            c2 = '\u0003';
                            break;
                        }
                        case 3: {
                            c2 = 'l';
                            break;
                        }
                        default: {
                            c2 = '*';
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
        m[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "o]o\u001c\nH^eLHRLw\u0003D\u0007Uv\u001f^\u0007ZfLCJHo\tGBVw\r^NWmLEA\u0018A\u0019^SWm?BF\\l\u001b\u0010".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0217: {
                if (n6 > 1) {
                    break Label_0217;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\'';
                            break;
                        }
                        case 1: {
                            c4 = '8';
                            break;
                        }
                        case 2: {
                            c4 = '\u0003';
                            break;
                        }
                        case 3: {
                            c4 = 'l';
                            break;
                        }
                        default: {
                            c4 = '*';
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
        m[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "eW{LGRKwLBFNfLEITzLEI]#\bCU]`\u0018CHV/Lr\u0007WqLs\u001d".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0333: {
                if (n10 > 1) {
                    break Label_0333;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\'';
                            break;
                        }
                        case 1: {
                            c6 = '8';
                            break;
                        }
                        case 2: {
                            c6 = '\u0003';
                            break;
                        }
                        case 3: {
                            c6 = 'l';
                            break;
                        }
                        default: {
                            c6 = '*';
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
        m[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "aWm\u0018\nDYmK^\u0007Tl\rNB\\#\nXHU#\u0018BNK#\u001fBF\\l\u001b\u0010".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0449: {
                if (n14 > 1) {
                    break Label_0449;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\'';
                            break;
                        }
                        case 1: {
                            c8 = '8';
                            break;
                        }
                        case 2: {
                            c8 = '\u0003';
                            break;
                        }
                        case 3: {
                            c8 = 'l';
                            break;
                        }
                        default: {
                            c8 = '*';
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
        m[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "sPj\u001f\nTPb\bEP\u0018l\n\nS]{\u0018\nJMp\u0018\nE]#\r\nnVW\tRSkk\rNHO#\u0005GWTf\u0001OILb\u0018CHV9".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0565: {
                if (n18 > 1) {
                    break Label_0565;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\'';
                            break;
                        }
                        case 1: {
                            c10 = '8';
                            break;
                        }
                        case 2: {
                            c10 = '\u0003';
                            break;
                        }
                        case 3: {
                            c10 = 'l';
                            break;
                        }
                        default: {
                            c10 = '*';
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
        m[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "EQm\u000bE\tL".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0681: {
                if (n22 > 1) {
                    break Label_0681;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\'';
                            break;
                        }
                        case 1: {
                            c12 = '8';
                            break;
                        }
                        case 2: {
                            c12 = '\u0003';
                            break;
                        }
                        case 3: {
                            c12 = 'l';
                            break;
                        }
                        default: {
                            c12 = '*';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                m[n21] = new String(charArray6).intern();
                t.M = m;
                t.l = new f((t.L != null) ? t.L : (t.L = a(t.M[5])));
                return;
            }
            continue;
        }
    }
}
