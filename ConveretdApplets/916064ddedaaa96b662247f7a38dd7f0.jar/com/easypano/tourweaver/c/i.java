// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.c;

import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;

public class i extends f
{
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int[] H;
    private int[] I;
    private double J;
    private double K;
    private double L;
    private double M;
    private double N;
    private Image O;
    private Image P;
    private Graphics Q;
    private MemoryImageSource R;
    double S;
    double T;
    
    public i() {
        this.G = -1;
    }
    
    public void a(final int n) {
    }
    
    public void a(final int n, final int n2) {
        super.f = this.m - (this.i - n) * this.o / this.k;
        super.g = this.n - (this.j - n2) * this.p / this.l;
    }
    
    public void a(final c[] array, final int n) {
    }
    
    public synchronized void a(final int[] i, final int n, final int n2, final int n3, final int n4) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        int n5 = n3;
        Label_0092: {
            Label_0044: {
                if (!h) {
                    if (n3 == this.q) {
                        n5 = n4;
                        if (h) {
                            break Label_0044;
                        }
                        if (n4 == this.t) {
                            this.I = i;
                            if (!h) {
                                break Label_0092;
                            }
                        }
                    }
                    n5 = 0;
                }
            }
            int j = n5;
            while (j < n4) {
                System.arraycopy(i, j * n3, this.I, n + (j + n2) * this.q, n3);
                ++j;
                if (h) {
                    return;
                }
                if (h) {
                    break;
                }
            }
        }
        (this.R = new MemoryImageSource(this.q, this.t, this.I, 0, this.q)).setAnimated(true);
        this.P = Toolkit.getDefaultToolkit().createImage(this.R);
        this.R.newPixels();
    }
    
    public double b() {
        return this.A;
    }
    
    public double d() {
        return this.y;
    }
    
    public double c() {
        return this.u;
    }
    
    public double a(double l) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n = dcmpl(l, (double)(this.v - this.y / 2));
        final double n2;
        if (!h) {
            if (n > 0) {
                l = this.v - this.y / 2;
            }
            n2 = l;
            if (h) {
                return n2;
            }
            final double n3 = dcmpg(n2, (double)(this.x + this.y / 2));
        }
        if (n < 0) {
            l = this.x + this.y / 2;
        }
        this.L = l;
        this.u = (int)l;
        return n2;
    }
    
    public double b(double j) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n = dcmpl(j, (double)(this.B - this.y * this.s / this.r / 2));
        final double n2;
        if (!h) {
            if (n > 0) {
                j = this.B - this.y * this.s / this.r / 2;
            }
            n2 = j;
            if (h) {
                return n2;
            }
            final double n3 = dcmpg(n2, (double)(this.E + this.y * this.s / this.r / 2));
        }
        if (n < 0) {
            j = this.E + this.y * this.s / this.r / 2;
        }
        this.J = j;
        this.A = (int)this.J;
        final double i = this.J;
        return n2;
    }
    
    public double c(double k) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        double y;
        final double n = y = dcmpl(k, this.M);
        if (!h) {
            if (n > 0) {
                k = this.M;
            }
            final int n2;
            y = (n2 = dcmpg(k, this.N));
        }
        if (!h) {
            if (n < 0) {
                k = this.N;
            }
            this.K = k;
            this.y = (int)this.K;
            final i i = this;
            if (h) {
                return i.K;
            }
            y = this.y;
        }
        if (y == 0) {
            this.y = 1;
        }
        final i i = this;
        return i.K;
    }
    
    public Image a(final Component component, final boolean b) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        i i = this;
        if (!h) {
            if (this.O == null) {
                this.O = component.createImage(this.r, this.s);
                this.Q = this.O.getGraphics();
            }
            this.Q.setColor(component.getBackground());
            i = this;
        }
        i.Q.fillRect(0, 0, this.r, this.s);
        if (!h) {
            if (b) {
                this.k = this.q;
                this.i = 0;
                this.o = this.q * this.r / this.F;
                this.m = (this.r - this.o) / 2;
                this.l = this.t;
                this.j = 0;
                this.p = this.t * this.r / this.F;
                this.n = (this.s - this.p) / 2;
                this.Q.drawImage(this.P, this.m, this.n, this.m + this.o, this.n + this.p, this.i, this.j, this.i + this.k, this.j + this.l, component);
                if (!h) {
                    return this.O;
                }
            }
            this.g();
            this.Q.drawImage(this.P, this.m, this.n, this.m + this.o, this.n + this.p, this.i, this.j, this.i + this.k, this.j + this.l, component);
        }
        return this.O;
    }
    
    public void a(final int r, final int s, final int[] array, final Component component) {
        i i = this;
        if (!com.easypano.tourweaver.c.f.h) {
            if (this.G == -1) {
                this.G = r;
            }
            this.i(Math.max(Math.max(r, this.q), 1.0 * this.y / this.G * r));
            this.c(1.0 * this.y / this.G * r);
            this.G = r;
            this.r = r;
            this.s = s;
            this.O = component.createImage(this.r, this.s);
            i = this;
        }
        i.Q = this.O.getGraphics();
    }
    
    public boolean a(final int q, final int t, final boolean b) {
        final boolean b2 = this.b(q, t);
        if (!com.easypano.tourweaver.c.f.h && b2) {
            this.q = q;
            this.t = t;
            this.w = this.q;
            this.z = 0;
            this.C = this.t;
            this.D = 0;
            return true;
        }
        return b2;
    }
    
    public boolean d(final double s) {
        this.c(s * this.r);
        this.S = s;
        return true;
    }
    
    public void a(final int n, final int n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final double n12, final double n13, final boolean b, final int n14, final int n15, final Component component) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.a(n, n2, b);
        this.a(n14, n15, null, component);
        final int r = this.r;
        Label_0135: {
            Label_0074: {
                if (!h) {
                    if (r >= this.q) {
                        final int s = this.s;
                        if (h) {
                            break Label_0074;
                        }
                        if (s >= this.t) {
                            this.F = this.r;
                            if (!h) {
                                break Label_0135;
                            }
                        }
                    }
                    final int q = this.q;
                }
            }
            final float n16 = r / this.t;
            final float n17 = this.r / this.s;
            Label_0122: {
                if (!h) {
                    if (n16 <= n17) {
                        break Label_0122;
                    }
                    this.F = this.q;
                }
                if (!h) {
                    break Label_0135;
                }
            }
            this.F = (int)(this.t * n17);
        }
        this.g(n3);
        this.h(n4);
        this.e(n5);
        this.f(n6);
        this.i(n7);
        this.j(n8);
        this.b(n12);
        this.a(n13);
    }
    
    public void a(final boolean b) {
    }
    
    public void a() {
    }
    
    private boolean b(final int n, final int n2) {
        return true;
    }
    
    private void e(final double n) {
        this.v = this.w;
    }
    
    private void f(final double n) {
        this.x = this.z;
    }
    
    private void g(final double n) {
        this.B = this.C;
    }
    
    private void h(final double n) {
        this.E = this.D;
    }
    
    private void i(final double n) {
        this.M = (int)n;
    }
    
    private void j(final double n) {
        this.N = (int)n;
    }
    
    private void g() {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.k = this.y;
        this.l = this.s * this.y / this.r;
        int n2;
        final int n = n2 = this.l;
        if (!h) {
            if (n == 0) {
                this.l = 1;
            }
            final int k = this.k;
        }
        Label_0235: {
            if (!h) {
                if (n < this.q) {
                    final int u = (this.k + 1) / 2;
                    final int u2 = this.u;
                    final int n3 = u;
                    i i = null;
                    Label_0152: {
                        Label_0136: {
                            if (!h) {
                                if (u2 < n3) {
                                    this.u = u;
                                    this.L = this.u;
                                    if (!h) {
                                        break Label_0136;
                                    }
                                }
                                i = this;
                                if (h) {
                                    break Label_0152;
                                }
                                final int n4 = this.u + u;
                                final int q = this.q;
                            }
                            if (u2 > n3) {
                                this.u = this.q - u;
                                this.L = this.u;
                            }
                        }
                        this.i = this.u - u;
                        this.m = 0;
                        i = this;
                    }
                    i.o = this.r;
                    if (!h) {
                        break Label_0235;
                    }
                }
                this.u = (this.q + 1) / 2;
                this.L = this.u;
                n2 = this.k;
            }
            this.k = this.q;
            this.i = 0;
            this.o = this.q * this.r / this.y;
            this.m = (this.r - this.o) / 2;
        }
        final int l = this.l;
        if (!h) {
            if (l < this.t) {
                final int a = (this.l + 1) / 2;
                final int a2 = this.A;
                final int n5 = a;
                i j = null;
                Label_0341: {
                    Label_0325: {
                        if (!h) {
                            if (a2 < n5) {
                                this.A = a;
                                this.J = this.A;
                                if (!h) {
                                    break Label_0325;
                                }
                            }
                            j = this;
                            if (h) {
                                break Label_0341;
                            }
                            final int n6 = this.A + a;
                            final int t = this.t;
                        }
                        if (a2 > n5) {
                            this.A = this.t - a;
                            this.J = this.A;
                        }
                    }
                    this.j = this.A - a;
                    this.n = 0;
                    j = this;
                }
                j.p = this.s;
                if (!h) {
                    return;
                }
            }
            this.A = (this.t + 1) / 2;
            this.J = this.A;
            final int m = this.l;
        }
        this.l = this.t;
        this.j = 0;
        this.p = this.t * this.r / this.y;
        this.n = (this.s - this.p) / 2;
    }
}
