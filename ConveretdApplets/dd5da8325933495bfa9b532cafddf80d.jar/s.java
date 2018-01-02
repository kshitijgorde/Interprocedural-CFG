// 
// Decompiled by Procyon v0.5.30
// 

public class s
{
    private d a;
    private boolean b;
    private boolean c;
    public int d;
    public int e;
    public v f;
    private r g;
    private r h;
    
    public s(final d a) {
        this.f = new v();
        this.a = a;
    }
    
    public void a(final r g, final r h, final int d) {
        this.g = g;
        this.h = h;
        this.b = false;
        this.d = d;
        this.f.a();
    }
    
    public boolean a() {
        return this.f.b();
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.h.b(n2, n3, n5);
        this.h.b(this.a, n2, n3);
        this.h.a(n2, n3, n, this.d, n4);
        this.h.c(this.a, n2, n3);
        if (this.h.k(n2, n3)) {
            p.a(4);
        }
    }
    
    private void a(final int n, final int n2, final int n3) {
        this.a(n, this.h.j, this.h.k, n2, n3);
    }
    
    public int b(final int n, final int n2, final int n3) {
        final boolean dj = p.dJ;
        int n4 = 0;
        while (true) {
            while (true) {
                Label_0084: {
                    if (!dj) {
                        break Label_0084;
                    }
                    this.g.g(0, n4);
                    final boolean b;
                    if (b) {
                        this.g.b(0, n4, n3);
                        this.g.a(0, n4, n, 0, n2);
                        this.g.c(this.a, 0, n4);
                        this.g.d(this.a, 0, n4);
                        return n4;
                    }
                    ++n4;
                }
                if (n4 < this.g.c) {
                    continue;
                }
                break;
            }
            final boolean b = false;
            if (!dj) {
                return b ? 1 : 0;
            }
            continue;
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4) {
        this.g.b(0, n2, n4);
        this.g.b(this.a, 0, n2);
        this.g.a(0, n2, n, 0, n3);
        this.g.c(this.a, 0, n2);
        this.g.d(this.a, 0, n2);
    }
    
    private void b() {
        this.a.o();
        this.f.a(this.a);
        this.a.p();
    }
    
    public boolean a(final boolean c) {
        final boolean dj = p.dJ;
        this.c = c;
        boolean b = false;
        if (this.a.bl && this.a()) {
            final boolean b2 = this.b;
        }
        if (this.a.bm || this.a.B) {
            if (!this.a() && !this.a.B && this.g.a(this.a, 0)) {
                final int h = this.g.h;
                final int i = this.g.i;
                this.g.e(this.a, h, i);
                this.f.a(this.g.m(h, i));
                if (c) {
                    this.f.d = this.g.j(h, i);
                    this.f.e = this.g.l(h, i);
                    this.f.a(this.a);
                    this.f.b(this.a);
                }
                this.g.c(h, i);
                this.b = true;
                this.f.b(this.a, 50000);
                p.a(1);
            }
            if (!this.a() && this.h.a(this.a, this.d)) {
                b = true;
                final int h2 = this.h.h;
                final int j = this.h.i;
                final int l = this.h.l;
                final int m = this.h.m;
                Label_0489: {
                    if (this.a.B) {
                        this.b(l, m, c ? this.h.j(h2, j) : 0);
                        this.h.b(this.a, h2, j);
                        this.h.c(h2, j);
                        this.a.o();
                        if (!dj) {
                            break Label_0489;
                        }
                    }
                    this.f.a(this.h.m(h2, j));
                    int k = 0;
                    if (c) {
                        this.f.e = this.h.l(h2, j);
                        k = this.h.j(h2, j);
                    }
                    this.h.c(h2, j);
                    this.f.a(this.a);
                    this.f.a(this.a, 0, 0, l, m, k);
                    this.f.b(this.a, 50000);
                }
                this.b = false;
                p.a(1);
                this.e = 1;
            }
        }
        Label_1317: {
            if (this.a() && !this.a.bm) {
                if (this.h.c(this.a)) {
                    boolean b3 = true;
                    if (this.h.p(this.h.j, this.h.k) > 6 || this.h.q(this.h.j, this.h.k) > 6) {
                        b3 = false;
                        this.d();
                    }
                    if (!b3) {
                        break Label_1317;
                    }
                    this.e = 0;
                    p.a(1);
                    b = true;
                    if (this.b) {
                        final int l2;
                        if ((l2 = this.h.l(this.h.j, this.h.k)) == -1) {
                            this.a(this.g.l, this.g.m, c ? this.f.d : this.h.j(this.h.j, this.h.k));
                            this.b();
                            if (!dj) {
                                break Label_1317;
                            }
                        }
                        if (this.h.h(this.h.j, this.h.k) == this.d) {
                            final int j2 = this.h.j(this.h.j, this.h.k);
                            this.a(this.g.l, this.g.m, c ? this.f.d : j2);
                            this.a(l2, this.g.i, this.g.m, c ? j2 : false);
                            this.b();
                            if (!dj) {
                                break Label_1317;
                            }
                        }
                        this.d();
                        if (!dj) {
                            break Label_1317;
                        }
                    }
                    final int l3;
                    if ((l3 = this.h.l(this.h.j, this.h.k)) == -1) {
                        this.a(this.h.l, this.h.m, c ? this.f.d : this.h.j(this.h.j, this.h.k));
                        this.b();
                        if (!dj) {
                            break Label_1317;
                        }
                    }
                    if (this.h.h(this.h.j, this.h.k) == this.d) {
                        final int n = c ? this.h.j(0, this.h.k) : this.h.j(this.h.h, this.h.i);
                        this.a(this.h.l, this.h.m, c ? this.f.d : this.h.j(this.h.j, this.h.k));
                        this.a(l3, this.h.h, this.h.i, this.h.m, n);
                        this.b();
                        if (!dj) {
                            break Label_1317;
                        }
                    }
                    this.d();
                    if (!dj) {
                        break Label_1317;
                    }
                }
                if (this.g.c(this.a)) {
                    p.a(1);
                    if (this.b) {
                        final int l4 = this.g.l(0, this.g.k);
                        final int n2 = c ? this.g.j(0, this.g.k) : 0;
                        this.a(this.g.l, this.g.k, this.g.m, c ? this.f.d : 0);
                        if (l4 != -1) {
                            this.g.b(0, this.g.i, n2);
                            this.a(l4, this.g.i, this.g.m, n2);
                        }
                        this.b();
                        if (!dj) {
                            break Label_1317;
                        }
                    }
                    this.b(this.h.l, this.h.m, this.f.d);
                    this.b();
                    if (!dj) {
                        break Label_1317;
                    }
                }
                this.d();
            }
        }
        this.c();
        return b;
    }
    
    public void c() {
        if (this.a()) {
            this.f.a(this.a, this.a.bo, this.a.bp);
        }
    }
    
    private void d() {
        p.a(3);
        Label_0112: {
            if (this.b) {
                this.b(this.g.l, 0, this.c ? this.f.d : 0);
                if (!p.dJ) {
                    break Label_0112;
                }
            }
            this.a(this.f.e, this.h.h, this.h.i, 0, this.c ? this.f.d : this.h.j(this.h.h, this.h.i));
        }
        this.b();
    }
    
    public void e() {
        if (this.a()) {
            Label_0072: {
                if (this.b) {
                    this.b(this.g.l, this.g.m, this.f.d);
                    if (!p.dJ) {
                        break Label_0072;
                    }
                }
                this.b(this.h.l, this.h.m, this.f.d);
            }
            this.b();
        }
    }
}
