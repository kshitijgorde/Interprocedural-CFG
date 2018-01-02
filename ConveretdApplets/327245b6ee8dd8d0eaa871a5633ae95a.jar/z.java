import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class z
{
    blaze3d a;
    int b;
    y c;
    z d;
    z e;
    int f;
    static final as g;
    static final as h;
    ac i;
    ac j;
    int k;
    f l;
    ag m;
    ag n;
    int p;
    ah q;
    public boolean r;
    boolean s;
    af t;
    an[] u;
    protected boolean v;
    int w;
    boolean x;
    ad y;
    bd z;
    Vector aa;
    Vector ab;
    boolean ac;
    
    z() {
        this.f = 0;
        this.i = new ac();
        this.k = -1;
        this.r = true;
        this.s = false;
        this.x = false;
        this.aa = new Vector(10, 10);
        this.ab = null;
        this.ac = false;
    }
    
    z(final blaze3d a, final y c, final ad y, final int b, final af t, final int n) {
        this.f = 0;
        this.i = new ac();
        this.k = -1;
        this.r = true;
        this.s = false;
        this.x = false;
        this.aa = new Vector(10, 10);
        this.ab = null;
        this.ac = false;
        this.a = a;
        this.c = c;
        this.y = y;
        this.b = b;
        this.u = new an[3];
        this.t = t;
        this.a(-1, n);
        Label_0153: {
            if (t != null) {
                this.p = t.a(n);
                if (this.p == -1) {
                    break Label_0153;
                }
                this.q = new ah();
                if (!c.l) {
                    break Label_0153;
                }
            }
            this.p = -1;
        }
        this.l = new f();
        this.k();
        this.j();
    }
    
    void b() {
        if (this == this.a.aq) {
            this.a.aq = null;
        }
        if (this == this.a.ar) {
            this.a.ar = null;
        }
        if (this == this.a.as.b) {
            this.a.as.b = null;
        }
        this.a = null;
        this.z = null;
        this.aa.removeAllElements();
        this.y = null;
        this.m = null;
        this.d = null;
        this.e = null;
    }
    
    void a(final int n, final boolean b) {
    }
    
    void a(final int n, final String s, final String s2) {
    }
    
    int c() {
        return 0;
    }
    
    c a() {
        return null;
    }
    
    int h() {
        return 0;
    }
    
    void a(final boolean s, final z z) {
        au au = null;
        if (z != null) {
            au = new au();
            au.a(z.a());
        }
        Label_0069: {
            if (s) {
                this.a().c(z.g, au, false);
                if (!c.l) {
                    break Label_0069;
                }
            }
            this.f &= 0x1;
            this.a().c(z.h, au, false);
        }
        this.s = s;
        this.k();
    }
    
    void a(final int n, final int n2) {
        if (this.t == null || this.x) {
            return;
        }
        final an a;
        if ((a = this.t.a(0, this.u[0], n, n2)) != null) {
            this.u[0] = a;
            this.i = new ac(a.c);
            this.k();
        }
        final an a2;
        if ((a2 = this.t.a(1, this.u[1], n, n2)) != null) {
            this.u[1] = a2;
            if (this.m == null) {
                this.m = new ag();
            }
            this.m.a(a2.d);
            this.k();
        }
    }
    
    void a(final e e) {
        e.a(this.l);
        this.d();
    }
    
    int a(int n, final ah t, final ah u) {
        int n2 = 0;
        while (true) {
            Label_0066: {
                if (!c.l) {
                    break Label_0066;
                }
                final ai d = this.aa.elementAt(n2);
                d.e = n++;
                d.t = t;
                if (u != null) {
                    d.u = u;
                    d.h = u.d;
                    u.d = d;
                }
                ++n2;
            }
            if (n2 >= this.aa.size()) {
                return n;
            }
            continue;
        }
    }
    
    void a(final boolean b, final ag n) {
        if (b || this.v) {
            if (!this.l.b()) {
                this.ac = true;
            }
            this.c.f.a(this.l);
            this.j();
            this.l = this.g();
            Label_0085: {
                if (this.m == null) {
                    this.n = n;
                    if (!c.l) {
                        break Label_0085;
                    }
                }
                this.a(n != null);
            }
            this.c.f.a(this.l);
            this.c.w = blaze3d.c;
            this.z = null;
            this.i();
            this.v = false;
        }
    }
    
    void i() {
        if (this.z != null) {
            return;
        }
        this.aa.removeAllElements();
        this.y.a(this);
    }
    
    ag a(final boolean b) {
        this.n = new ag(this.m);
        if (b) {
            y y = this.c;
            while (true) {
                Label_0053: {
                    if (!c.l) {
                        break Label_0053;
                    }
                    if (y.m != null) {
                        this.n.b(y.m);
                    }
                    y = y.c;
                }
                if (y != null) {
                    continue;
                }
                break;
            }
        }
        return this.n;
    }
    
    f f() {
        return this.y.c;
    }
    
    int j() {
        int j = -1;
        if (this.c != null) {
            j = this.c.j();
        }
        if (this.v || j > this.k || j == blaze3d.c) {
            Label_0090: {
                if (this.c == null) {
                    this.j = ac.a(this.i, this.a.am);
                    if (!c.l) {
                        break Label_0090;
                    }
                }
                this.j = ac.a(this.i, this.c.j);
            }
            this.k = blaze3d.c;
        }
        return this.k;
    }
    
    f g() {
        this.j();
        return this.j.a(this.y.c);
    }
    
    final void k() {
        this.v = true;
        this.w = blaze3d.c;
    }
    
    int e() {
        return this.w;
    }
    
    void a(final bd[] array, final f f) {
        this.c.a(array, this.z);
    }
    
    final float l() {
        return this.i.e / 20.0f;
    }
    
    final void a(final float n) {
        if (!Float.isNaN(n) && !Float.isInfinite(n)) {
            if (this.i.e == (int)(n * 20.0f)) {
                return;
            }
            this.i.e = (int)(n * 20.0f);
            this.k();
            this.x = true;
        }
    }
    
    final float m() {
        return this.i.f / 20.0f;
    }
    
    final void b(final float n) {
        if (!Float.isNaN(n) && !Float.isInfinite(n)) {
            if (this.i.f == (int)(n * 20.0f)) {
                return;
            }
            this.i.f = (int)(n * 20.0f);
            this.k();
            this.x = true;
        }
    }
    
    final float n() {
        return this.i.h * 100.0f;
    }
    
    final void c(final float n) {
        if (!Float.isNaN(n) && !Float.isInfinite(n)) {
            this.i.b(n / 100.0f);
            this.k();
            this.x = true;
        }
    }
    
    final float p() {
        return this.i.i * 100.0f;
    }
    
    final void d(final float n) {
        if (!Float.isNaN(n) && !Float.isInfinite(n)) {
            this.i.c(n / 100.0f);
            this.k();
            this.x = true;
        }
    }
    
    final float q() {
        return this.i.g;
    }
    
    final void e(final float n) {
        if (!Float.isNaN(n) && !Float.isInfinite(n)) {
            this.i.a(n);
            this.k();
            this.x = true;
        }
    }
    
    float r() {
        final f a = this.i.a(this.f());
        return (a.c - a.a) / 20.0f;
    }
    
    final void f(final float n) {
        if (!Float.isNaN(n) && !Float.isInfinite(n)) {
            final f f = this.f();
            Label_0074: {
                if (f.c != f.a) {
                    this.i.b(n * 20.0f / (f.c - f.a));
                    if (!c.l) {
                        break Label_0074;
                    }
                }
                this.i.b(0.0f);
                this.i.c(0.0f);
            }
            this.k();
            this.x = true;
        }
    }
    
    float s() {
        final f a = this.i.a(this.f());
        return (a.d - a.b) / 20.0f;
    }
    
    final void g(final float n) {
        if (!Float.isNaN(n) && !Float.isInfinite(n)) {
            final f f = this.f();
            Label_0074: {
                if (f.d != f.b) {
                    this.i.c(n * 20.0f / (f.d - f.b));
                    if (!c.l) {
                        break Label_0074;
                    }
                }
                this.i.b(0.0f);
                this.i.c(0.0f);
            }
            this.k();
            this.x = true;
        }
    }
    
    final float t() {
        if (this.m == null) {
            return 100.0f;
        }
        return this.m.b * 100 / 256.0f;
    }
    
    final void h(final float n) {
        if (this.m == null) {
            this.m = new ag();
        }
        this.m.b = (int)(n * 256.0f / 100.0f);
        this.k();
        this.x = true;
        this.m.j = 3;
    }
    
    final void b(final boolean r) {
        this.r = r;
        this.k();
        if (!r) {
            this.d();
        }
    }
    
    boolean a(final Point point, final boolean b) {
        final boolean l = c.l;
        boolean b2 = false;
        if (this.g().b(point)) {
            if (!b) {
                return true;
            }
            if (this.z == null) {
                this.i();
            }
            int n = 0;
            bd bd = this.z;
            int n2 = 0;
            while (true) {
                while (true) {
                    Label_0163: {
                        if (!l) {
                            break Label_0163;
                        }
                        final int b3 = bd.b;
                        if (n2 <= point.y && point.y < bd.f && bd.a(point, 0) > 0) {
                            n = 1;
                            Label_0134: {
                                if (bd.e.i) {
                                    final ai e = bd.e;
                                    e.d += bd.b;
                                    if (!l) {
                                        break Label_0134;
                                    }
                                }
                                final ai e2 = bd.e;
                                e2.d ^= 0x1;
                            }
                            if (bd.f != null) {
                                final ai f = bd.f;
                                f.d ^= 0x1;
                            }
                        }
                        bd = bd.a;
                    }
                    if (bd != null) {
                        continue;
                    }
                    break;
                }
                n2 = n;
                if (l) {
                    continue;
                }
                break;
            }
            if (n2 != 0) {
                bd bd2 = this.z;
                while (true) {
                    Label_0248: {
                        if (!l) {
                            break Label_0248;
                        }
                        if (bd2.e.d != 0) {
                            b2 = true;
                        }
                        bd2.e.d = 0;
                        if (bd2.f != null) {
                            if (bd2.f.d != 0) {
                                b2 = true;
                            }
                            bd2.f.d = 0;
                        }
                        bd2 = bd2.a;
                    }
                    if (bd2 != null) {
                        continue;
                    }
                    break;
                }
            }
        }
        return b2;
    }
    
    Point b(final int n, final int n2) {
        final Point point = new Point(n << 2, n2 << 2);
        this.j();
        this.j.b().a(point);
        final Point point2 = point;
        point2.x /= (int)20.0f;
        final Point point3 = point;
        point3.y /= (int)20.0f;
        return point;
    }
    
    private void d() {
        final boolean l = c.l;
        if (this.ab != null) {
            int n = 0;
            Vector vector;
            while (true) {
                while (true) {
                    Label_0034: {
                        if (!l) {
                            break Label_0034;
                        }
                        final Object o = this.ab.elementAt(n);
                        ((l)o).f();
                        ++n;
                    }
                    if (n < this.ab.size()) {
                        continue;
                    }
                    break;
                }
                Object o;
                vector = (Vector)(o = this.ab);
                if (l) {
                    continue;
                }
                break;
            }
            vector.removeAllElements();
        }
    }
    
    static {
        g = new as("onSetFocus");
        h = new as("onKillFocus");
    }
}
