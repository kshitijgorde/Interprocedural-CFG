// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Enumeration;
import java.awt.Image;

public class g implements q
{
    x a;
    double b;
    double c;
    f d;
    
    public g() {
        this.b = -1.0;
        this.c = -1.0;
        this.d = new f();
    }
    
    public void a(final h h, final d d) {
        final boolean i = r.i;
        if (!(h instanceof x) || this.d == null) {
            return;
        }
        if (h.f()) {
            return;
        }
        h a;
        final x x = (x)(a = this.a);
        if (!i) {
            Label_0115: {
                if (x != h) {
                    this.a = (x)h;
                    d.b(true);
                    d.a(this.a.n());
                    final Enumeration j = this.a.i();
                    while (j.hasMoreElements()) {
                        d.a(j.nextElement());
                        if (i) {
                            break Label_0115;
                        }
                        if (i) {
                            break;
                        }
                    }
                    this.a.a(true);
                }
            }
            a = h;
        }
        double n3;
        int l;
        int n2;
        final int n = n2 = (l = (int)(n3 = (a.f() ? 1 : 0)));
        if (!i) {
            if (n != 0) {
                return;
            }
            final int n4;
            n2 = (n4 = (l = (int)(n3 = (this.a.l() ? 1 : 0))));
        }
        if (!i) {
            if (n != 0) {
                final e k = this.a.j();
                Label_0249: {
                    if (!i) {
                        if (k != null) {
                            final Shape h2 = k.h();
                            final Rectangle r = ((com.easypano.tourweaver.b.d)k).r();
                            this.d.g();
                            this.d.a(this.a.n(), k.getX(), k.getY(), r, h2, k.g());
                            this.b = 0.0;
                            this.c = 0.0;
                            if (!i) {
                                break Label_0249;
                            }
                        }
                        d.a((Image)null);
                    }
                    this.d.g();
                }
                this.a.a(false);
            }
            l = (n2 = (int)(n3 = (d.l() ? 1 : 0)));
        }
        Label_0319: {
            Label_0315: {
                if (!i) {
                    if (n2 == 0) {
                        final double n5 = n3 = dcmpl(this.b, d.f());
                        if (i) {
                            break Label_0319;
                        }
                        if (n5 != 0) {
                            break Label_0315;
                        }
                        final double n6 = n3 = dcmpl(this.c, d.B());
                        if (i) {
                            break Label_0319;
                        }
                        if (n6 != 0) {
                            break Label_0315;
                        }
                    }
                    n3 = (l = (d.l() ? 1 : 0));
                }
                if (i) {
                    break Label_0319;
                }
                if (l == 0) {
                    return;
                }
            }
            n3 = (d.l() ? 1 : 0);
        }
        h a2 = null;
        Label_0433: {
            Label_0432: {
                if (n3 == 0) {
                    this.b = d.f();
                    this.c = d.B();
                    if (!i) {
                        break Label_0432;
                    }
                }
                final x x2 = (x)(a2 = this.a);
                if (i) {
                    break Label_0433;
                }
                final e m = x2.j();
                if (m != null) {
                    final com.easypano.tourweaver.b.d d2 = (com.easypano.tourweaver.b.d)m;
                    final double b = d2.n() + 3.141592653589793 - d2.u();
                    g g = this;
                    Label_0424: {
                        if (!i) {
                            if (this.b == b) {
                                g = this;
                                if (i) {
                                    break Label_0424;
                                }
                                if (this.c == d2.m()) {
                                    return;
                                }
                            }
                            this.b = b;
                            g = this;
                        }
                    }
                    g.c = d2.m();
                }
            }
            a2 = h;
        }
        if (a2.f()) {
            return;
        }
        this.d.a(this.b, this.c);
        final e j2 = this.a.j();
        if (!i) {
            if (j2 == null) {
                return;
            }
            this.a.j();
        }
        final com.easypano.tourweaver.b.d d3 = (com.easypano.tourweaver.b.d)j2;
        d3.c(this.d.a().x, this.d.a().y);
        d3.d(this.d.b().x, this.d.b().y);
        d.a(this.d.h());
    }
    
    public Image g() {
        return null;
    }
    
    public boolean a(final h h) {
        return h instanceof x;
    }
    
    public boolean b(final h h) {
        return h == this.a;
    }
    
    public void a(final q q) {
    }
    
    public q h() {
        return null;
    }
    
    public void c() {
        g g = this;
        if (!r.i) {
            if (this.d != null) {
                this.d.g();
            }
            g = this;
        }
        g.d = null;
    }
}
