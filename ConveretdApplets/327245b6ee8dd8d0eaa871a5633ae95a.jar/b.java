import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends c
{
    blaze3d a;
    y b;
    t c;
    boolean d;
    int e;
    f f;
    g g;
    g h;
    l i;
    l j;
    l k;
    boolean l;
    String m;
    boolean n;
    u p;
    private af q;
    boolean r;
    int s;
    int t;
    byte u;
    boolean v;
    boolean w;
    int x;
    b y;
    b z;
    b aa;
    b ab;
    b ac;
    ax ad;
    b ae;
    b af;
    b ag;
    String ah;
    b ai;
    int aj;
    ar[] ak;
    static final as al;
    static final as am;
    static final as an;
    static final as ao;
    static final as ap;
    static final as aq;
    static final as ar;
    static final as as;
    static final as at;
    static final as au;
    static final as av;
    static final as aw;
    static final as ax;
    static final as ay;
    static final as az;
    static final as a0;
    static final as a1;
    static final as a2;
    
    c a(final aa aa) {
        return aa.h;
    }
    
    public b() {
        this.l = false;
        this.n = false;
        this.r = true;
        this.s = -1;
        this.t = -1;
        this.u = 0;
        this.x = 0;
        this.y = null;
        this.z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.aj = -1;
        super.a = 7;
    }
    
    void a() {
        final boolean l = c.l;
        b y = this.y;
        while (true) {
            while (true) {
                Label_0024: {
                    if (!l) {
                        break Label_0024;
                    }
                    final b z = y.z;
                    final b b = z;
                    y.a();
                    y = b;
                }
                if (y != null) {
                    continue;
                }
                break;
            }
            this.j();
            final b z = this;
            if (!l) {
                if (this.ak != null) {
                    int n = 0;
                    while (true) {
                        Label_0059: {
                            if (!l) {
                                break Label_0059;
                            }
                            this.ak[n] = null;
                            ++n;
                        }
                        if (n < 19) {
                            continue;
                        }
                        break;
                    }
                }
                if (this.c != null && this == this.ae) {
                    this.c.c();
                }
                this.h = null;
                this.p = null;
                this.q = null;
                this.af = null;
                this.ag = null;
                this.ai = null;
                this.y = null;
                this.c = null;
                this.z = null;
                this.aa = null;
                this.b = null;
                return;
            }
            continue;
        }
    }
    
    void a(final b ag, final String ah, final af af, final int n, final int n2) {
        this.ag = ag;
        this.ah = ah;
        this.ak = new ar[19];
        if (af != null && af.g != null) {
            int n3 = 0;
            while (true) {
                Label_0056: {
                    if (!c.l) {
                        break Label_0056;
                    }
                    this.ak[n3] = af.g[n3];
                    ++n3;
                }
                if (n3 < 19) {
                    continue;
                }
                break;
            }
        }
        this.ai = this;
        this.a = this.c.a;
        super.f = this.c.a.ak;
        y b = null;
        if (ag != null) {
            b = ag.b;
            this.ae = this.ag.ae;
        }
        this.b(false);
        this.c("this", this);
        this.b = new y(this.a, b, this, n, af, n2);
    }
    
    void a(final u p) {
        final boolean l = c.l;
        this.p = p;
        Label_0074: {
            if (p != null) {
                this.c = p.b;
                if (!l) {
                    break Label_0074;
                }
            }
            if (this.c != null && this.ae == this) {
                this.c.c();
            }
            if (this.ag != null) {
                this.c = this.ag.c;
                if (!l) {
                    break Label_0074;
                }
            }
            this.c = null;
        }
        Label_0115: {
            if (this.ag != null && this.ag.c == this.c) {
                this.ae = this.ag.ae;
                if (!l) {
                    break Label_0115;
                }
            }
            this.ae = this;
        }
        this.b(this.f("_lockroot").ag());
        if (this.c != null) {
            this.d = this.c.i;
        }
        this.r = true;
        this.s = -1;
        this.t = -1;
        if (this.b != null) {
            this.b.b(true);
        }
        this.q = null;
    }
    
    void b() {
        this.g = new g();
        this.g.f = this.toString();
        this.g.c = this;
    }
    
    boolean c() {
        final boolean l = c.l;
        this.ac = null;
        this.ab = this.y;
        Label_0066: {
            if (this.r && this.p != null) {
                if (this.s + 1 < this.p.a) {
                    this.a(this.s + 1);
                    if (!l) {
                        break Label_0066;
                    }
                }
                this.b(1);
            }
        }
        b b = this.ab;
        while (true) {
            while (true) {
                Label_0085: {
                    if (!l) {
                        break Label_0085;
                    }
                    b.c();
                    b = b.z;
                }
                if (b != null) {
                    continue;
                }
                break;
            }
            final boolean b2 = true;
            if (!l) {
                return b2;
            }
            continue;
        }
    }
    
    void a(int s) {
        final boolean l = c.l;
        if (this.w) {
            return;
        }
        Label_0045: {
            if (s < 0) {
                s = 0;
                if (!l) {
                    break Label_0045;
                }
            }
            if (s >= this.p.a) {
                s = this.p.a - 1;
            }
        }
        if (this.s == s) {
            return;
        }
        if ((this.v || (this.ag != null && this.c != this.ag.c)) && this.p.b <= s) {
            return;
        }
        this.p.c(s);
        this.p.a(this.ae, s);
        int n = 0;
        while (true) {
            Label_0196: {
                if (!l) {
                    break Label_0196;
                }
                z z = this.b.d[n];
                while (true) {
                    Label_0189: {
                        if (!l) {
                            break Label_0189;
                        }
                        final z d = z.d;
                        if (z.t != null && (s > z.t.b || s < z.t.a)) {
                            this.b.a(z, this.b.f);
                        }
                        z = d;
                    }
                    if (z != null) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n < 64) {
                continue;
            }
            break;
        }
        int n2 = 0;
        while (true) {
            Label_0254: {
                if (!l) {
                    break Label_0254;
                }
                z d2 = this.b.d[n2];
                while (true) {
                    Label_0246: {
                        if (!l) {
                            break Label_0246;
                        }
                        d2.a(this.s, s);
                        d2 = d2.d;
                    }
                    if (d2 != null) {
                        continue;
                    }
                    break;
                }
                ++n2;
            }
            if (n2 < 64) {
                continue;
            }
            break;
        }
        final boolean b = s > this.s;
    Label_0619_Outer:
        while (true) {
            af q = null;
            Label_0661: {
                if (!b || this.q == null) {
                    q = this.p.c;
                    this.q = null;
                    if (!l) {
                        break Label_0661;
                    }
                }
                q = this.q.i;
                if (!l) {
                    break Label_0661;
                }
                Label_0654: {
                    if (q.b >= s && ((b && q.a > this.s) || (!b && q.b < this.s))) {
                        this.q = q;
                        final m a = this.p.b.a(q.c);
                        if (a != null) {
                            if (a.a == 6 || a.a == 4) {
                                String s2 = q.e;
                                if (s2 == null) {
                                    s2 = "instance" + ++this.x;
                                }
                                final b a2 = this.a((u)a, s2, q.d, q, s, null);
                                if (a.a != 4) {
                                    break Label_0654;
                                }
                                a2.l();
                                final ay ay = (ay)a;
                                (a2.ai = new b()).a(ay.b);
                                a2.ai.a(a2, "", null, 65535, 0);
                                a2.b.a(a2.ai.b);
                                a2.ai.b.b(false);
                                a2.ai.b.b = true;
                                a2.ai.a(0);
                                int n3 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0622: {
                                            if (!l) {
                                                break Label_0622;
                                            }
                                            a2.ak[n3] = ay.c[n3];
                                            ++n3;
                                        }
                                        if (n3 < 19) {
                                            continue Label_0619_Outer;
                                        }
                                        break;
                                    }
                                    a2.n = true;
                                    if (l) {
                                        continue;
                                    }
                                    break;
                                }
                                if (!l) {
                                    break Label_0654;
                                }
                            }
                            a.a(this, q, s);
                        }
                    }
                }
                q = q.i;
            }
            if (q == null || q.a > s) {
                this.s = s;
                return;
            }
            continue;
        }
    }
    
    ax a(final az az, final String s, final int n, final af af, final int n2) {
        int n3 = 0;
        Label_0028: {
            if (az != null) {
                n3 = (az.d << 16 | n);
                if (!c.l) {
                    break Label_0028;
                }
            }
            n3 = (0xFFFF0000 | n);
        }
        final ax ax = new ax(this.a, az, this, s, n3, af, n2);
        if (ax.t != null) {
            this.c(ax.t, ax.a);
        }
        ax.c = this.c;
        this.b.a(ax);
        this.a(ax);
        return ax;
    }
    
    void a(final ax ax) {
        if (this.ad != null) {
            this.ad.y = ax;
            ax.x = this.ad;
        }
        this.ad = ax;
    }
    
    b a(final u u, final String s, final int n, final af af, final int n2, final c c) {
        int n3 = 0;
        Label_0028: {
            if (u != null) {
                n3 = (u.d << 16 | n);
                if (!c.l) {
                    break Label_0028;
                }
            }
            n3 = (0xFFFF0000 | n);
        }
        ar ar = null;
        if (u != null && u.e != null) {
            ar = this.a.ak.l.get(u.e);
        }
        b b;
        if (ar != null) {
            b = (b)ar.a();
        }
        else {
            b = new b();
        }
        if (u != null) {
            b.a(u);
        }
        b.c = this.c;
        b.d = this.d;
        b.a(this, s, af, n3, n2);
        this.b.a(b.b);
        this.a(b);
        if (u != null) {
            b.a(0);
        }
        if (c != null) {
            as ae;
            while ((ae = c.ae()) != null) {
                b.a((c)ae, c.d((c)ae, this.d));
            }
        }
        if (b.ak[9] != null) {
            b.ak[9].b(b, null);
        }
        if (b.ak[18] != null) {
            b.ak[18].b(b, null);
        }
        if (ar != null) {
            ar.b(b, null, b, this.d);
        }
        return b;
    }
    
    void d() {
        this.w = true;
    }
    
    void e() {
        boolean b = true;
        do {
            this.a.ap = false;
            this.a(b);
            b = false;
        } while (this.a.ap);
    }
    
    void a(final boolean b) {
        final boolean l = c.l;
        b ab = this.ab;
        while (true) {
            while (true) {
                Label_0029: {
                    if (!l) {
                        break Label_0029;
                    }
                    final b z = ab.z;
                    final b b2 = z;
                    ab.a(b);
                    ab = b2;
                }
                if (ab != null) {
                    continue;
                }
                break;
            }
            final b z = this;
            if (l) {
                continue;
            }
            break;
        }
        if (this.w) {
            this.g();
            this.h();
            this.a();
            return;
        }
        if (this.v) {
            if (this.s == -1) {
                return;
            }
            this.f();
            this.v = false;
        }
        if (b) {
            if (this.ak[1] != null) {
                this.ak[1].b(this, null);
            }
            this.c(b.an, null, this.d);
        }
        if (this.s != this.t) {
            this.t = this.s;
            ar c = this.p.g[this.s];
            while (true) {
                Label_0175: {
                    if (!l) {
                        break Label_0175;
                    }
                    c.b(c.f = this, null);
                    c = c.c;
                }
                if (c != null) {
                    continue;
                }
                break;
            }
        }
        b ac = this.ac;
        while (true) {
            while (true) {
                Label_0205: {
                    if (!l) {
                        break Label_0205;
                    }
                    final b aa = ac.aa;
                    final b b3 = aa;
                    ac.f();
                    ac.a(false);
                    ac = b3;
                }
                if (ac != null) {
                    continue;
                }
                break;
            }
            this.ac = null;
            final b aa = this;
            if (!l) {
                this.ab = this.y;
                return;
            }
            continue;
        }
    }
    
    void f() {
        if (this.ak[0] != null) {
            this.ak[0].b(this, null);
        }
        this.c(b.al, null, this.d);
    }
    
    void g() {
        if (this.ak[2] != null) {
            this.ak[2].b(this, null);
        }
        this.c(b.am, null, this.d);
    }
    
    void a(final b y) {
        if (this.ac == null) {
            this.ac = y;
        }
        if (this.y != null) {
            this.y.aa = y;
            y.z = this.y;
        }
        this.y = y;
        y.aa = null;
        this.c(y.ah, y);
    }
    
    void h() {
        if (this.ah != null && this.ag.f(this.ah) == this) {
            this.ag.e(this.ah);
        }
        if (this.z != null) {
            this.z.aa = this.aa;
        }
        Label_0098: {
            if (this.aa != null) {
                this.aa.z = this.z;
                if (!c.l) {
                    break Label_0098;
                }
            }
            if (this.ag.y == this) {
                this.ag.y = this.z;
            }
        }
        if (this.ag.ab == this) {
            this.ag.ab = this.z;
        }
    }
    
    void i() {
        final boolean l = c.l;
        int n = 0;
        while (true) {
            while (true) {
                Label_0056: {
                    if (!l) {
                        break Label_0056;
                    }
                    final z b = this.b.d[n];
                    z z = b;
                    while (true) {
                        Label_0049: {
                            if (!l) {
                                break Label_0049;
                            }
                            final z d = z.d;
                            this.b.a(z, this.b.f);
                            z = d;
                        }
                        if (z != null) {
                            continue;
                        }
                        break;
                    }
                    ++n;
                }
                if (n < 64) {
                    continue;
                }
                break;
            }
            z b;
            final y y = (y)(b = this.b);
            if (!l) {
                y.v = true;
                return;
            }
            continue;
        }
    }
    
    void j() {
        this.h = null;
        if (this.g != null) {
            if (this.g.e != null) {
                this.b.f.a(this.g.e);
            }
            this.g.b();
            this.g = null;
            this.b.z = null;
        }
    }
    
    public void k() {
        this.r = true;
    }
    
    public void l() {
        this.r = false;
    }
    
    public void b(int n) {
        --n;
        this.a(n);
        this.a.ap = true;
        this.r = true;
    }
    
    public void a(final String s) {
        final int n = this.p.b(s) + 1;
        if (n > 0) {
            this.b(n);
        }
    }
    
    public void c(int n) {
        --n;
        this.a(n);
        this.a.ap = true;
        this.r = false;
    }
    
    public void b(final String s) {
        if (this.p == null) {
            return;
        }
        final int n = this.p.b(s) + 1;
        if (n > 0) {
            this.c(n);
        }
    }
    
    void d(final int n) {
        this.e(n);
    }
    
    void e(final int n) {
        final boolean l = c.l;
        b y = this.y;
        while (true) {
            while (true) {
                Label_0025: {
                    if (!l) {
                        break Label_0025;
                    }
                    y.e(n);
                    final b z = y.z;
                    y = z;
                }
                if (y != null) {
                    continue;
                }
                break;
            }
            final b z = this;
            if (!l) {
                if (this.p == null || this.p.a != 4) {
                    if ((n & 0x2) != 0x0) {
                        if (this.ak[6] != null) {
                            this.ak[6].b(this, null);
                        }
                        this.c(b.as, null, this.d);
                    }
                    if ((n & 0x1) != 0x0) {
                        if (this.ak[7] != null) {
                            this.ak[7].b(this, null);
                        }
                        this.c(b.ar, null, this.d);
                    }
                }
                return;
            }
            continue;
        }
    }
    
    void f(final int n) {
        this.g(n);
        final Point b = this.a.ao[0].b.b(this.a.t, this.a.u);
        final z aq = this.a.aq;
        z z = this.a(b.x, b.y);
        this.a.ar = z;
        if (z == null && aq == null && (n & 0x2) != 0x0) {
            this.a.as.a((z)null);
        }
        if (aq != null) {
            aq.a(n, z == aq);
        }
        if (aq == null || (this.a.aq != null && (aq.f & 0x2) == 0x0)) {
            if (z != null && this.a.ar == null) {
                z = this.a(b.x, b.y);
            }
            if ((this.a.aq = z) != null && z != aq) {
                z.a(8, true);
            }
        }
        this.a.ar = null;
        int c = 0;
        if (this.a.aq != null) {
            c = this.a.aq.c();
        }
        this.a.a(c);
    }
    
    z a(final int n, final int n2) {
        final boolean l = c.l;
        int n3 = -1;
        z b = null;
        b b2 = this.y;
        ax ax;
        while (true) {
            Label_0157: {
                if (b2 == null) {
                    ax = this.ad;
                    if (!l) {
                        break;
                    }
                }
                else if (b2.b.r) {
                    if (!b2.w) {
                        if ((b2.b.b & 0xFFFF) > n3) {
                            if (b2.q() && b2.d((c)b.a1, this.d).ag()) {
                                if (!b2.ai.a(n, n2, true)) {
                                    break Label_0157;
                                }
                                b = b2.b;
                                n3 = (b.b & 0xFFFF);
                                if (!l) {
                                    break Label_0157;
                                }
                            }
                            final z a = b2.a(n, n2);
                            if (a != null) {
                                b = a;
                                n3 = (b2.b.b & 0xFFFF);
                            }
                        }
                    }
                }
            }
            b2 = b2.z;
        }
        while (true) {
            Label_0309: {
                if (!l) {
                    break Label_0309;
                }
                if (ax.r) {
                    if (ax.d()) {
                        if ((ax.b & 0xFFFF) > n3) {
                            final Point point = new Point(n * 20, n2 * 20);
                            this.a.ao[0].b.j();
                            this.a.ao[0].b.j.a(point);
                            if (ax.a(point, true)) {
                                b = ax;
                                n3 = (b.b & 0xFFFF);
                            }
                        }
                    }
                }
                ax = ax.x;
            }
            if (ax == null) {
                return b;
            }
            continue;
        }
    }
    
    void a(final int n, final boolean b) {
        final boolean l = c.l;
        final b p2 = this.p();
        if (!b) {
            if (this.b.f == 0) {
                return;
            }
            if ((n & 0x9) != 0x0) {
                Label_0183: {
                    if (this.b.f == 3) {
                        if (this.ak[16] != null) {
                            this.ak[16].b(p2, null);
                        }
                        this.b.f = 2;
                        this.a.d();
                        if (this.w) {
                            return;
                        }
                        this.c(b.ay, null, this.d);
                        if (!l) {
                            break Label_0183;
                        }
                    }
                    if (this.b.f == 1) {
                        if (this.ak[14] != null) {
                            this.ak[14].b(p2, null);
                        }
                        this.b.f = 0;
                        this.b("_up");
                        this.a.d();
                        if (this.w) {
                            return;
                        }
                        this.c(b.aw, null, this.d);
                    }
                }
                this.a.d();
            }
            if ((n & 0x4) == 0x0 || this.b.f != 2) {
                return;
            }
            this.a.ak.i = 2;
            if (this.ak[12] != null) {
                this.ak[12].b(p2, null);
            }
            this.a.d();
            if (this.w) {
                return;
            }
            this.b("_up");
            this.b.f = 0;
            if (this.w) {
                return;
            }
            this.c(b.az, null, this.d);
            this.a.d();
            this.a.ak.i = 0;
            if (!l) {
                return;
            }
        }
        if ((n & 0xD) != 0x0) {
            Label_0481: {
                if (this.b.f == 0) {
                    if (this.ak[13] != null) {
                        this.ak[13].b(p2, null);
                    }
                    this.a.d();
                    if (this.w) {
                        return;
                    }
                    this.b("_over");
                    this.b.f = 1;
                    if (this.w) {
                        return;
                    }
                    this.c(b.av, null, this.d);
                    if (!l) {
                        break Label_0481;
                    }
                }
                if (this.b.f == 2) {
                    if (this.ak[15] != null) {
                        this.ak[15].b(p2, null);
                    }
                    this.a.d();
                    if (this.w) {
                        return;
                    }
                    this.b.f = 3;
                    this.c(b.ax, null, this.d);
                }
            }
            this.a.d();
        }
        if ((n & 0x2) != 0x0 && this.b.f != 3) {
            this.a.ak.i = 1;
            if (this.ak[10] != null) {
                this.ak[10].b(p2, null);
            }
            this.a.d();
            if (this.w) {
                return;
            }
            this.b("_down");
            this.b.f = 3;
            this.c(b.at, null, this.d);
            this.a.d();
            this.a.ak.i = 0;
        }
        if ((n & 0x4) != 0x0 && this.b.f == 3) {
            this.a.ak.i = 2;
            if (this.ak[11] != null) {
                this.ak[11].b(p2, null);
            }
            this.a.d();
            if (this.w) {
                return;
            }
            this.b("_over");
            this.b.f = 1;
            this.c(b.au, null, this.d);
            this.a.d();
            this.a.ak.i = 0;
        }
    }
    
    void g(final int n) {
        final boolean l = c.l;
        b y = this.y;
        while (true) {
            while (true) {
                Label_0025: {
                    if (!l) {
                        break Label_0025;
                    }
                    y.g(n);
                    final b z = y.z;
                    y = z;
                }
                if (y != null) {
                    continue;
                }
                break;
            }
            final b z = this;
            if (!l) {
                if (this.p == null || this.p.a != 4) {
                    if ((n & 0x1) != 0x0) {
                        if (this.ak[3] != null) {
                            this.ak[3].b(this, null);
                        }
                        this.c(b.aq, null, this.d);
                    }
                    if ((n & 0x2) != 0x0) {
                        this.a.ak.i = 1;
                        if (this.ak[4] != null) {
                            this.ak[4].b(this, null);
                        }
                        this.c(b.ap, null, this.d);
                        this.a.ak.i = 0;
                    }
                    if ((n & 0x4) != 0x0) {
                        this.a.ak.i = 2;
                        if (this.ak[5] != null) {
                            this.ak[5].b(this, null);
                        }
                        this.c(b.ao, null, this.d);
                        this.a.ak.i = 0;
                    }
                }
                return;
            }
            continue;
        }
    }
    
    b p() {
        if (this.n) {
            return this.ag;
        }
        return this;
    }
    
    boolean q() {
        return this.n || this.ak[16] != null || this.ak[15] != null || this.ak[10] != null || this.ak[11] != null || this.ak[12] != null || this.ak[13] != null || this.ak[14] != null || this.a(b.at, false) || this.a(b.au, false) || this.a(b.av, false) || this.a(b.aw, false) || this.a(b.az, false) || this.a(b.ay, false) || this.a(b.ax, false);
    }
    
    String r() {
        return "movieclip";
    }
    
    c h(final int n) {
        return new as(this.toString());
    }
    
    public String toString() {
        if (this.ag != null) {
            return this.ag.toString() + "." + this.ah;
        }
        return this.ah;
    }
    
    static String i(final int n) {
        switch (n) {
            case 0: {
                return "_x";
            }
            case 1: {
                return "_y";
            }
            case 2: {
                return "_xscale";
            }
            case 3: {
                return "_yscale";
            }
            case 4: {
                return "_currentframe";
            }
            case 5: {
                return "_totalframes";
            }
            case 6: {
                return "_alpha";
            }
            case 7: {
                return "_visible";
            }
            case 8: {
                return "_width";
            }
            case 9: {
                return "_height";
            }
            case 10: {
                return "_rotation";
            }
            case 11: {
                return "_target";
            }
            case 12: {
                return "_framesloaded";
            }
            case 13: {
                return "_name";
            }
            case 14: {
                return "_droptarget";
            }
            case 15: {
                return "_url";
            }
            case 16: {
                return "_highquality";
            }
            case 17: {
                return "_focusrect";
            }
            case 18: {
                return "_soundbuftime";
            }
            case 19: {
                return "_quality";
            }
            case 20: {
                return "_xmouse";
            }
            case 21: {
                return "_ymouse";
            }
            default: {
                return "";
            }
        }
    }
    
    boolean a(final as as, final boolean b) {
        if (!super.b) {
            this.ad();
        }
        if (super.d.a(as, b) != null) {
            return true;
        }
        if (as.a.startsWith("_")) {
            final String b2 = as.b;
            if (b2.equals("_x")) {
                return true;
            }
            if (b2.equals("_y")) {
                return true;
            }
            if (b2.equals("_rotation")) {
                return true;
            }
            if (b2.equals("_xscale")) {
                return true;
            }
            if (b2.equals("_yscale")) {
                return true;
            }
            if (b2.equals("_xmouse")) {
                return true;
            }
            if (b2.equals("_ymouse")) {
                return true;
            }
            if (b2.equals("_parent")) {
                return true;
            }
            if (b2.equals("_currentframe")) {
                return true;
            }
            if (b2.equals("_width")) {
                return true;
            }
            if (b2.equals("_height")) {
                return true;
            }
            if (b2.equals("_name")) {
                return true;
            }
            if (b2.equals("_alpha")) {
                return true;
            }
            if (b2.equals("_framesloaded")) {
                return true;
            }
            if (b2.equals("_totalframes")) {
                return true;
            }
            if (b2.equals("_visible")) {
                return true;
            }
            if (b2.equals("_root")) {
                return true;
            }
            if (b2.equals("_url")) {
                return true;
            }
            if (b2.equals("_quality")) {
                return true;
            }
        }
        return super.e != null && super.e.a(as, b);
    }
    
    c c(final String s) {
        if (s.startsWith("_") && this.b != null) {
            final String lowerCase = s.toLowerCase();
            if (lowerCase.equals("_x")) {
                return new aq(this.b.l());
            }
            if (lowerCase.equals("_y")) {
                return new aq(this.b.m());
            }
            if (lowerCase.equals("_root")) {
                return this.af;
            }
            if (lowerCase.equals("_rotation")) {
                return new aq(this.b.q());
            }
            if (lowerCase.equals("_xscale")) {
                return new aq(this.b.n());
            }
            if (lowerCase.equals("_yscale")) {
                return new aq(this.b.p());
            }
            if (lowerCase.equals("_xmouse")) {
                return new aq(this.b.b(this.a.t, this.a.u).x);
            }
            if (lowerCase.equals("_ymouse")) {
                return new aq(this.b.b(this.a.t, this.a.u).y);
            }
            if (lowerCase.equals("_parent")) {
                return this.ag;
            }
            if (lowerCase.equals("_currentframe")) {
                return new aq(this.s + 1);
            }
            if (lowerCase.equals("_width")) {
                return new aq(this.b.r());
            }
            if (lowerCase.equals("_height")) {
                return new aq(this.b.s());
            }
            if (lowerCase.equals("_name")) {
                return new as((this.ah == null) ? "null" : this.ah);
            }
            if (lowerCase.equals("_alpha")) {
                return new aq(this.b.t());
            }
            if (lowerCase.equals("_framesloaded")) {
                return new aq(this.p.b);
            }
            if (lowerCase.equals("_totalframes")) {
                return new aq(this.p.a);
            }
            if (lowerCase.equals("_visible")) {
                return new av(this.b.r);
            }
            if (lowerCase.equals("_url")) {
                return new as(this.c.c.toString());
            }
            if (lowerCase.equals("_quality")) {
                return new as(this.a.ak.e());
            }
        }
        return null;
    }
    
    boolean a(final String s, final c c) {
        final boolean l = c.l;
        if (c.a == 0 || c == d.d) {
            return true;
        }
        if (s.startsWith("_") && this.b != null) {
            final String lowerCase = s.toLowerCase();
            if (lowerCase.equals("_x")) {
                this.b.a(c.af());
                if (!l) {
                    return false;
                }
            }
            if (lowerCase.equals("_y")) {
                this.b.b(c.af());
                if (!l) {
                    return false;
                }
            }
            if (lowerCase.equals("_width")) {
                this.b.f(c.af());
                if (!l) {
                    return false;
                }
            }
            if (lowerCase.equals("_height")) {
                this.b.g(c.af());
                if (!l) {
                    return false;
                }
            }
            if (lowerCase.equals("_xscale")) {
                this.b.c(c.af());
                if (!l) {
                    return false;
                }
            }
            if (lowerCase.equals("_yscale")) {
                this.b.d(c.af());
                if (!l) {
                    return false;
                }
            }
            if (lowerCase.equals("_rotation")) {
                this.b.e(c.af());
                if (!l) {
                    return false;
                }
            }
            if (lowerCase.equals("_visible")) {
                this.b.b(c.ag());
                if (!l) {
                    return false;
                }
            }
            if (lowerCase.equals("_alpha")) {
                this.b.h(c.af());
                if (!l) {
                    return false;
                }
            }
            if (lowerCase.equals("_lockroot")) {
                this.b(c.ag());
                return true;
            }
            if (lowerCase.equals("_name")) {
                this.ah = c.toString();
                if (!l) {
                    return false;
                }
            }
            if (!lowerCase.equals("_quality")) {
                return true;
            }
            this.a.ak.a(c.toString());
            return false;
        }
        return true;
    }
    
    void b(final boolean b) {
        final boolean l = c.l;
        if (b) {
            this.af = this.ae;
            if (!l) {
                return;
            }
        }
        if (this.ag != null) {
            this.af = this.ag.af;
            if (!l) {
                return;
            }
        }
        this.af = this;
    }
    
    public boolean a(final int n, final int n2, final boolean b) {
        final Point point = new Point(n * 20, n2 * 20);
        this.a.ao[0].b.j();
        this.a.ao[0].b.j.a(point);
        return this.b.a(point, b);
    }
    
    public void a(final String s, final String s2) {
        this.a.av.addElement(this);
        if (s == null) {
            return;
        }
        this.a.at.addElement(this);
        this.a.au.addElement(s);
    }
    
    public int t() {
        if (this.p == null) {
            return 0;
        }
        return this.p.g;
    }
    
    public int u() {
        if (this.p == null) {
            return 0;
        }
        return this.p.f;
    }
    
    public void a(final g h) {
        if (this.h == null) {
            if (this.g == null) {
                this.b();
            }
            this.g.a = new p(this.a);
            this.g.e = new f();
        }
        this.h = h;
        this.b.v = true;
    }
    
    public g aa() {
        return this.h;
    }
    
    public void a(final String s, final g g) {
        if (this.g == null) {
            this.b();
        }
        this.g.a(s, g);
        if (g != null) {
            this.c(s, g.a());
        }
    }
    
    public float d(final int n, final int n2) {
        if (this.g == null) {
            return 0.0f;
        }
        return this.g.a.b(n, n2);
    }
    
    int ab() {
        final boolean l = c.l;
        int e = 0;
        Label_0828: {
            if (this.l) {
                Label_0084: {
                    if (this.i == null) {
                        final m a = this.c.a(this.m);
                        if (a != null && a.a == 1) {
                            this.i = (l)a;
                            this.i.m.addElement(this);
                            if (!l) {
                                final t c = this.c;
                                this.e = blaze3d.c;
                                break Label_0084;
                            }
                        }
                        return this.e;
                    }
                }
                e = this.i.d();
                if (!l) {
                    break Label_0828;
                }
            }
            if (!this.w) {
                if (this.b.e() > this.i.k) {
                    this.i.c();
                    ac ac = this.b.i;
                    y y = this.b.c;
                    e f;
                    ac am;
                    while (true) {
                        while (true) {
                            Label_0169: {
                                if (!l) {
                                    break Label_0169;
                                }
                                ac = ac.a(ac, y.i);
                                y = y.c;
                            }
                            if (y != null) {
                                continue;
                            }
                            break;
                        }
                        final ac a2 = ac.a(ac.a(this.f), new f(0, 0, this.i.c * 4, this.i.d * 4), 0);
                        f = new e();
                        f.a(this.b.c.f);
                        f.a(this.b.g());
                        am = this.a.am;
                        this.a.am = a2;
                        this.a.an.k();
                        this.b.a(true, (ag)null);
                        if (l) {
                            continue;
                        }
                        break;
                    }
                    Label_0772: {
                        if (!this.f("mapWithAlphaChannel").ag()) {
                            this.b.b(this.c.a.an.i);
                            this.b.a(this.i, false);
                            if (!l) {
                                break Label_0772;
                            }
                        }
                        final int c2 = this.i.c;
                        final int d = this.i.d;
                        if (this.j == null || c2 != this.j.c || d != this.j.d) {
                            this.j = new l(new int[c2 * d], c2, d);
                            this.k = new l(new int[c2 * d], c2, d);
                        }
                        this.b.b(-16777216);
                        this.b.a(this.j, false);
                        this.b.b(-1);
                        this.b.a(this.k, false);
                        final int[] b = this.j.b;
                        final int[] b2 = this.k.b;
                        final int[] b3 = this.i.b;
                        int n = 0;
                        while (true) {
                            Label_0750: {
                                if (!l) {
                                    break Label_0750;
                                }
                                int n2 = b[n] & 0xFF;
                                int n3 = b[n] >> 8 & 0xFF;
                                int n4 = b[n] >> 16 & 0xFF;
                                final int n5 = 255 - ((b2[n] & 0xFF) + (b2[n] >> 8 & 0xFF) + (b2[n] >> 16 & 0xFF) - (n2 + n3 + n4)) / 3;
                                if (n5 > 0) {
                                    n2 = n2 * 255 / n5;
                                    n3 = n3 * 255 / n5;
                                    n4 = n4 * 255 / n5;
                                    if (n2 > 255) {
                                        n2 = 255;
                                    }
                                    if (n3 > 255) {
                                        n3 = 255;
                                    }
                                    if (n4 > 255) {
                                        n4 = 255;
                                    }
                                }
                                b3[n] = ((n2 & 0xFF) | (n3 << 8 & 0xFF00) | (n4 << 16 & 0xFF0000) | (n5 << 23 & 0xFF000000));
                                ++n;
                            }
                            if (n < c2 * d) {
                                continue;
                            }
                            break;
                        }
                        this.i.k = blaze3d.c;
                    }
                    this.b.c.f = f;
                    this.a.am = am;
                    this.b.a(true, (ag)null);
                    this.b.i = 0;
                }
                e = this.i.k;
                if (!l) {
                    break Label_0828;
                }
            }
            return this.e;
        }
        if (e > this.e) {
            this.e = e;
        }
        return this.e;
    }
    
    void ac() {
        if (this.i != null) {
            this.i.m.removeElement(this);
            this.i = null;
        }
    }
    
    static {
        al = new as("onLoad");
        am = new as("onUnload");
        an = new as("onEnterFrame");
        ao = new as("onMouseUp");
        ap = new as("onMouseDown");
        aq = new as("onMouseMove");
        ar = new as("onKeyUp");
        as = new as("onKeyDown");
        at = new as("onPress");
        au = new as("onRelease");
        av = new as("onRollOver");
        aw = new as("onRollOut");
        ax = new as("onDragOver");
        ay = new as("onDragOut");
        az = new as("onReleaseOutside");
        a0 = new as("useHandCursor");
        a1 = new as("enabled");
        a2 = new as("selectable");
    }
}
