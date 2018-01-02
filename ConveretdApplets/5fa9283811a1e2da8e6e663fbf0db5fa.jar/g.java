// 
// Decompiled by Procyon v0.5.30
// 

public class g extends r
{
    public c p;
    public boolean p;
    public int p;
    public boolean d;
    public l p;
    public l d;
    public int d;
    public int a;
    public dd[] p;
    public l a;
    public l[] p;
    public u p;
    public u d;
    public u a;
    public l n;
    public l v;
    public int n;
    public int[] p;
    public int[] d;
    public v p;
    public u n;
    public l i;
    
    private final void p(final c p) {
        this.p = p;
        this.i();
        for (int i = 0; i < this.d; ++i) {
            (this.p[i] = new dd(this.p)).p();
            this.p[i] = new l();
        }
    }
    
    public final void v() {
        this.v.p(0.0, 0.0);
        for (int i = 0; i < this.a; ++i) {
            this.v.p += this.p[i].p.p;
            this.v.d += this.p[i].p.d;
        }
        this.v.p /= this.a;
        this.v.d /= this.a;
        this.n = this.a;
    }
    
    public final int d() {
        int n = (int)Math.abs(this.p[this.a - 1].p.p - this.p[0].p.p);
        for (int i = 0; i < this.a - 1; ++i) {
            final int n2 = (int)Math.abs(this.p[i].p.p - this.p[i + 1].p.p);
            if (n2 > n) {
                n = n2;
            }
        }
        return n;
    }
    
    public final int p() {
        int n = (int)Math.abs(this.p[this.a - 1].p.d - this.p[0].p.d);
        for (int i = 0; i < this.a - 1; ++i) {
            final int n2 = (int)Math.abs(this.p[i].p.d - this.p[i + 1].p.d);
            if (n2 > n) {
                n = n2;
            }
        }
        return n;
    }
    
    public final int a() {
        final int d = this.d();
        final int p = this.p();
        if (d > p) {
            return d;
        }
        return p;
    }
    
    public final void e() {
        this.v();
    }
    
    public final u p(final int n) {
        this.p.d(this.p[n].p, this.p[n].n);
        this.n.a(this.p[n].p);
        this.n.p(this.p);
        this.d.p(this.v, this.n);
        this.n.a(this.p[n].p);
        this.n.d(this.p);
        this.a.p(this.v, this.n);
        if (this.d.p() < this.a.p()) {
            this.p.p();
        }
        this.p.d();
        this.p.p(0.5);
        return this.p;
    }
    
    public final boolean p() {
        if (this.a <= 2) {
            return false;
        }
        this.e();
        this.b();
        int a = 0;
        for (int i = 0; i < this.a; ++i) {
            this.p = this.p(i);
            this.p[a].d(this.p[i].p);
            this.p[a].a(this.p);
            ++a;
            this.p[a].d(this.p[i].n);
            this.p[a].a(this.p);
            ++a;
        }
        this.a = a;
        for (int j = 0; j < this.a; ++j) {
            this.p[j].p.d(this.p[j]);
        }
        this.d();
        return true;
    }
    
    public final void n() {
        this.p();
        for (int i = 0; i < this.a; ++i) {
            this.p[i].d();
        }
    }
    
    public final void p(final int p2, final l l) {
        this.p[p2].p(l.p, l.d, 0.0, 0.0);
        this.p[p2].p = p2;
    }
    
    public final void f() {
        for (int i = 0; i < this.a; ++i) {
            this.p.p.drawLine(this.p[i], this.d[i], this.p[i + 1], this.d[i + 1]);
        }
    }
    
    public final void a() {
        if (this.p.d) {
            this.f();
        }
        else {
            this.p.p.fillPolygon(this.p, this.d, this.a + 1);
        }
    }
    
    public final void a(final int a) {
        this.a = a;
    }
    
    public final void b() {
        for (int i = 0; i < this.a - 1; ++i) {
            this.p[i].n.d(this.p[i + 1].p);
        }
        this.p[this.a - 1].n.d(this.p[0].p);
    }
    
    public final void d(final int a) {
        this.a = a;
        this.b();
    }
    
    public final void c() {
        this.p.n();
    }
    
    public final void p() {
        this.p = null;
        this.d = null;
    }
    
    public final void i() {
        this.a = 0;
        this.n = 0;
        this.p();
        this.v.p();
    }
    
    public final void l() {
        this.i();
        for (int i = 0; i < this.d; ++i) {
            this.p[i].p();
        }
    }
    
    public final boolean v(final g g, final g g2) {
        for (int i = 0; i < g.a; ++i) {
            if (!g2.p(g.p[i].p)) {
                return false;
            }
        }
        return true;
    }
    
    public final void p(final l l, final l i) {
        this.p[this.a].p(l, i);
        ++this.a;
    }
    
    public final void p(final m m) {
        this.a = 4;
        final l p = m.p;
        final l d = m.d;
        this.p = null;
        this.d = null;
        final l l = new l(d.p, p.d);
        final l i = new l(p.p, d.d);
        this.p[0].p(p, i);
        this.p[1].p(i, d);
        this.p[2].p(d, l);
        this.p[3].p(l, p);
        this.p = true;
    }
    
    public final void p(final int a) {
        final double n = 6.283185307179586 / a;
        double n2 = -n / 4.0;
        final double n3 = 170.0;
        final double n4 = 50.0;
        final l l = new l();
        final l i = new l();
        this.a = a;
        this.p = null;
        this.d = null;
        i.d(n4 + n3 + n3 * Math.cos(n2), n4 + n3 + n3 * Math.sin(n2));
        for (int j = 0; j < this.a; ++j) {
            n2 += n;
            l.d(n4 + n3 + n3 * Math.cos(n2), n4 + n3 + n3 * Math.sin(n2));
            this.p[j].p(i, l);
            i.n(l);
        }
        this.p = true;
    }
    
    public final void p(final g g) {
        int i;
        for (i = 0; i < g.a; ++i) {
            this.p[i] = (int)Math.round(g.p[i].p.p);
            this.d[i] = (int)Math.round(g.p[i].p.d);
        }
        this.p[i] = (int)Math.round(g.p[0].p.p);
        this.d[i] = (int)Math.round(g.p[0].p.d);
        this.a = g.a;
    }
    
    public final void d() {
        int i;
        for (i = 0; i < this.a; ++i) {
            this.p[i] = (int)Math.round(this.p[i].p.p);
            this.d[i] = (int)Math.round(this.p[i].p.d);
        }
        this.p[i] = (int)Math.round(this.p[0].p.p);
        this.d[i] = (int)Math.round(this.p[0].p.d);
    }
    
    private final void p(final l l, final g g, final int n) {
        if (n == g.a - 1) {
            l.p(g.p[0].n);
        }
        else {
            l.p(g.p[n + 1].n);
        }
    }
    
    public final boolean n(final g g, final g g2) {
        final x x = new x(this.p);
        final i i = new i(false);
        boolean b = true;
        final l l = new l();
        for (int j = 0; j < g.a; ++j) {
            x.p(g.p[j].p, g.p[j].n);
            this.p(l, g, j);
            int n = 0;
            b = true;
            for (int k = 0; k < g2.a; ++k) {
                if (x.p(i, l, g2.p[k].p)) {
                    b = false;
                    break;
                }
                if (i.p) {
                    g.p = false;
                    return true;
                }
                if (i.d) {
                    ++n;
                }
            }
            if (n == g2.a) {
                g2.p = false;
                return true;
            }
            if (b) {
                break;
            }
        }
        return b;
    }
    
    public final boolean p(final l l) {
        final x x = new x(this.p);
        final i i = new i(false);
        final l j = new l();
        boolean b = this.a > 0;
        for (int k = 0; k < this.a; ++k) {
            x.p(this.p[k].p, this.p[k].n);
            this.p(j, this, k);
            if (!x.p(i, j, l)) {
                if (!i.p) {
                    if (!i.d) {
                        b = false;
                    }
                }
            }
        }
        return b;
    }
    
    public final void d(final g g, final g g2, final boolean b) {
        final int n = g.a - 1;
        for (int i = 0; i < g.a; ++i) {
            if (g2.p(g.p[i].p)) {
                if (b) {
                    this.v.v(g.p[i].p);
                    ++this.n;
                }
                else {
                    g.p = g.p[i].p;
                    if (i == 0) {
                        g.p[i].p.p(g.p[i], g.p[n].n);
                        g.p[n].n.p(g.p[n], g.p[i].p);
                    }
                    else {
                        g.p[i].p.p(g.p[i], g.p[i - 1].n);
                        g.p[i - 1].n.p(g.p[i - 1], g.p[i].p);
                    }
                }
            }
        }
    }
    
    public final void p(final g g, final dd dd, final dd dd2, final l l) {
        if (dd.p) {
            g.d = dd.d;
            if (dd2.p) {
                dd.d.p(l, dd, dd2.d);
                dd2.d.p(l, dd2, dd.d);
            }
            else if (dd2.d) {
                dd.d.p(l, dd, dd2.a);
                dd2.a.p(l, dd2, dd.d);
            }
            dd.p = false;
        }
        else if (dd.d) {
            g.d = dd.a;
            if (dd2.p) {
                dd.a.p(l, dd, dd2.d);
                dd2.d.p(l, dd2, dd.a);
            }
            else if (dd2.d) {
                dd.a.p(l, dd, dd2.a);
                dd2.a.p(l, dd2, dd.a);
            }
            dd.d = false;
        }
        if (dd2.p) {
            dd2.p = false;
        }
        else if (dd2.d) {
            dd2.d = false;
        }
    }
    
    public final void p(final g g, final g g2, final boolean b) {
        final l l = new l(0.0, 0.0);
        for (int i = 0; i < g.a; ++i) {
            for (int j = 0; j < g2.a; ++j) {
                if (g.p[i].p(l, g2.p[j])) {
                    if (b) {
                        this.v.v(l);
                        ++this.n;
                    }
                    else {
                        this.p(g, g.p[i], g2.p[j], l);
                    }
                }
            }
        }
    }
    
    public final boolean d(final l l) {
        if (l == null) {
            System.out.println("Err:addPointToIntersectionPoly");
            return false;
        }
        this.p[this.a].p(l, this.a);
        ++this.a;
        return true;
    }
    
    public final void p(final l l) {
        l.d = false;
        if (l.p != null) {
            l.p.d = false;
        }
    }
    
    public final void p(final s s, final l l, final l i, final int n) {
        s.p = null;
        dd dd = null;
        switch (n) {
            case 0: {
                if (l.p != null) {
                    dd = l.p;
                }
                else {
                    System.out.print("Err1: loadNextPoint");
                }
                break;
            }
            case 1: {
                if (l.p != null) {
                    if (l.p.p != null) {
                        dd = l.p.p;
                    }
                    else {
                        System.out.print("Err3: loadNextPoint");
                    }
                }
                else {
                    System.out.print("Err2: loadNextPoint");
                }
                break;
            }
            default: {
                System.out.println("Err:loadNextPoint-switch");
                break;
            }
        }
        if (dd != null) {
            if (dd.p.p(i, l)) {
                s.p = dd.p;
            }
            else if (dd.n.p(i, l)) {
                s.p = dd.n;
            }
            else if (dd.d.p(i, l)) {
                s.p = dd.d;
            }
            else if (dd.a.p(i, l)) {
                s.p = dd.a;
            }
        }
    }
    
    public final boolean p(final g g, final g g2) {
        l l = null;
        final s s = new s();
        l i;
        if (g.p != null) {
            i = g.p;
        }
        else if (g2.p != null) {
            i = g2.p;
        }
        else {
            if (g.d == null) {
                return false;
            }
            i = g.d;
        }
        int n = 0;
        this.d(i);
        this.p(i);
        while (n <= 1 && this.a < this.d) {
            this.p(s, i, l, n);
            if (s.p == null) {
                ++n;
            }
            else {
                this.p(s.p);
                n = 0;
                if (s.p.d(i, l)) {
                    this.d(s.p);
                }
                l = i;
                i = s.p;
            }
        }
        return true;
    }
    
    public final boolean d(final g g, final g g2) {
        return !this.n(g, g2) && !this.n(g2, g);
    }
    
    public final boolean p(final g g) {
        return this.d(g, this);
    }
    
    public final boolean i(final g g, final g g2) {
        if (!g.p(g2)) {
            return false;
        }
        this.i();
        g.n();
        g2.n();
        this.p = g.p;
        this.d(g, g2, false);
        this.d(g2, g, false);
        this.p(g, g2, false);
        this.p(g, g2);
        return true;
    }
    
    public final boolean d(final g g) {
        if (this.v(g, this.p.a)) {
            this.p(g);
            g.d();
            g.d = true;
        }
        else {
            g.d = false;
            if (!this.i(g, this.p.a)) {
                return g.d = false;
            }
            this.d();
        }
        return true;
    }
    
    public final boolean a(final g g, final g g2) {
        if (!g.p(g2)) {
            return false;
        }
        this.i();
        g.n();
        g2.n();
        this.d(g, g2, true);
        this.d(g2, g, true);
        this.p(g, g2, true);
        this.v.p /= this.n;
        this.v.d /= this.n;
        return true;
    }
    
    public g(final c c) {
        this.p = true;
        this.p = 0;
        this.d = true;
        this.p = null;
        this.d = null;
        this.d = 20;
        this.p = new dd[this.d];
        this.a = new l();
        this.p = new l[this.d];
        this.p = new u();
        this.d = new u();
        this.a = new u();
        this.n = new l();
        this.v = new l();
        this.n = 0;
        this.p = new int[this.d + 1];
        this.d = new int[this.d + 1];
        this.n = new u();
        this.i = new l();
        this.p(c);
        this.p = null;
    }
    
    public g(final c c, final v p2) {
        this.p = true;
        this.p = 0;
        this.d = true;
        this.p = null;
        this.d = null;
        this.d = 20;
        this.p = new dd[this.d];
        this.a = new l();
        this.p = new l[this.d];
        this.p = new u();
        this.d = new u();
        this.a = new u();
        this.n = new l();
        this.v = new l();
        this.n = 0;
        this.p = new int[this.d + 1];
        this.d = new int[this.d + 1];
        this.n = new u();
        this.i = new l();
        this.p(c);
        this.p = p2;
    }
}
