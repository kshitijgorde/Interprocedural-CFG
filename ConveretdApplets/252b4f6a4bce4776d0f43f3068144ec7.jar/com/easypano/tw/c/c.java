// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.image.ImageObserver;
import com.easypano.tw.ds;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Container;
import com.easypano.tw.du;
import java.awt.Rectangle;
import com.easypano.tw.ci;

public final class c extends a
{
    private ci h;
    private Rectangle[] i;
    private du[] j;
    private Container k;
    private Image l;
    private int m;
    private int n;
    private Image o;
    private Graphics p;
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
    private double H;
    private volatile double I;
    private volatile double J;
    private volatile double K;
    private volatile double L;
    private volatile double M;
    private volatile double N;
    int O;
    int P;
    int Q;
    int R;
    int S;
    int T;
    int U;
    int V;
    boolean W;
    
    public c() {
        this.i = new Rectangle[0];
        this.j = new du[0];
        this.k = null;
        this.o = null;
        this.p = null;
        this.H = 2.0;
        this.L = -1.0;
        this.M = -1.0;
        this.N = -1.0;
        this.W = false;
    }
    
    public void a(final ci ci, final int[] array, final Container container) {
        final Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(ci.m, ci.n, array, 0, ci.m));
        ds.a(image);
        this.a(ci, image, container);
    }
    
    public void a(final ci h, final Image l, final Container k) {
        if (this.h == null || !this.h.k.equals(h.k)) {
            this.W = true;
        }
        this.h = h;
        this.k = k;
        this.l = l;
        this.m = l.getWidth(k);
        this.n = l.getHeight(k);
        this.u = this.m;
        this.w = 0;
        this.z = this.n;
        this.B = 0;
        this.i = h.a();
        this.j = h.b();
    }
    
    public void a(final int q, final int r) {
        this.q = q;
        this.r = r;
        this.m();
        if (this.q >= this.m && this.r >= this.n) {
            this.E = this.q;
        }
        else {
            final float n = this.m / this.n;
            final float n2 = this.q / this.r;
            if (n > n2) {
                this.E = this.m;
            }
            else {
                this.E = (int)(this.n * n2);
            }
        }
        this.G = this.q / 9;
        this.b((int)this.h.o, (int)this.h.p);
        this.c(this.h.q);
        this.d((int)this.h.r, (int)this.h.s);
        this.i(this.h.t);
        this.c((int)this.h.u, (int)this.h.v);
        this.f(this.h.w);
    }
    
    public void m() {
        this.disposeOffImage();
        this.o = this.k.createImage(this.q, this.r);
        this.p = this.o.getGraphics();
    }
    
    public void disposeOffImage() {
        if (this.p != null) {
            this.o = null;
            this.p.dispose();
            this.p = null;
        }
    }
    
    public Image a() {
        if (this.o == null) {
            this.m();
        }
        return this.o;
    }
    
    public void b() {
        this.disposeOffImage();
        this.k = null;
        this.c();
    }
    
    public void c() {
        this.h = null;
        this.l = null;
        this.i = new Rectangle[0];
        this.j = new du[0];
        this.k = null;
    }
    
    public void a(final double n) {
        this.b((int)n, this.v);
    }
    
    public void b(final double n) {
        this.b(this.t, (int)n);
    }
    
    private void b(final int n, final int n2) {
        this.t = ((n > this.u) ? this.u : n);
        if (this.t < this.w) {
            this.t = this.w;
        }
        this.v = ((n2 < this.w) ? this.w : n2);
        if (this.v > this.u) {
            this.v = this.u;
        }
        if (this.v > this.t) {
            final int v = this.v;
            this.v = this.t;
            this.t = v;
        }
    }
    
    public void c(double i) {
        if (i > this.t) {
            i = this.t;
        }
        if (i < this.v) {
            i = this.v;
        }
        this.I = i;
        this.s = (int)i;
        this.n();
    }
    
    public double d() {
        return this.I;
    }
    
    public void d(final double n) {
        this.c((int)n, this.F);
    }
    
    public void e(final double n) {
        this.c(this.D, (int)n);
    }
    
    private void c(final int n, final int n2) {
        this.D = ((n > this.E) ? this.E : n);
        if (this.D < this.G) {
            this.D = this.G;
        }
        this.F = ((n2 < this.G) ? this.G : n2);
        if (this.F > this.E) {
            this.F = this.E;
        }
        if (this.D < this.F) {
            final int d = this.D;
            this.D = this.F;
            this.F = d;
        }
    }
    
    public void f(double k) {
        if (k > this.D) {
            k = this.D;
        }
        if (k < this.F) {
            k = this.F;
        }
        this.K = k;
        this.C = (int)this.K;
        this.n();
    }
    
    public double e() {
        return this.C;
    }
    
    public void g(final double n) {
        this.d((int)n, this.A);
    }
    
    public void h(final double n) {
        this.d(this.y, (int)n);
    }
    
    private void d(final int n, final int n2) {
        this.y = ((n > this.z) ? this.z : n);
        if (this.y < this.B) {
            this.y = this.B;
        }
        this.A = ((n2 < this.B) ? this.B : n2);
        if (this.A > this.z) {
            this.A = this.z;
        }
        if (this.A > this.y) {
            final int a = this.A;
            this.A = this.y;
            this.y = a;
        }
    }
    
    public void i(double j) {
        if (j > this.y) {
            j = this.y;
        }
        if (j < this.A) {
            j = this.A;
        }
        this.J = j;
        this.x = (int)this.J;
        this.n();
    }
    
    public double f() {
        return this.J;
    }
    
    private void n() {
        this.Q = this.C;
        this.R = this.r * this.C / this.q;
        if (this.Q < this.m) {
            final int s = (this.Q + 1) / 2;
            if (this.s < s) {
                this.s = s;
                this.I = this.s;
            }
            else if (this.s + s > this.m) {
                this.s = this.m - s;
                this.I = this.s;
            }
            this.O = this.s - s;
            this.S = 0;
            this.U = this.q;
        }
        else {
            this.s = (this.m + 1) / 2;
            this.I = this.s;
            final int q = this.Q;
            this.Q = this.m;
            this.O = 0;
            this.U = this.m * this.q / this.C;
            this.S = (this.q - this.U) / 2;
        }
        if (this.R < this.n) {
            final int x = (this.R + 1) / 2;
            if (this.x < x) {
                this.x = x;
                this.J = this.x;
            }
            else if (this.x + x > this.n) {
                this.x = this.n - x;
                this.J = this.x;
            }
            this.P = this.x - x;
            this.T = 0;
            this.V = this.r;
        }
        else {
            this.x = (this.n + 1) / 2;
            this.J = this.x;
            final int r = this.R;
            this.R = this.n;
            this.P = 0;
            this.V = this.n * this.q / this.C;
            this.T = (this.r - this.V) / 2;
        }
    }
    
    public double g() {
        return this.H;
    }
    
    public void h() {
        for (int length = this.j.length, i = 0; i < length; ++i) {
            this.j[i].a(2);
        }
    }
    
    public void i() {
        for (int length = this.j.length, i = 0; i < length; ++i) {
            this.j[i].a(1);
        }
    }
    
    public void j() {
        for (int length = this.j.length, i = 0; i < length; ++i) {
            if (this.j[i].d() == 2) {
                this.j[i].a(1);
            }
            else {
                this.j[i].a(2);
            }
        }
    }
    
    private void o() {
        for (int length = this.j.length, i = 0; i < length; ++i) {
            this.j[i].a(this.S - (this.O - this.h.a()[i].x) * this.U / this.Q, this.T - (this.P - this.h.a()[i].y) * this.V / this.R);
        }
    }
    
    public void b(final int n) {
    }
    
    public du[] l() {
        return this.j;
    }
    
    public void k() {
        if (this.l != null) {
            if (this.p == null) {
                this.o = this.k.createImage(this.q, this.r);
                this.p = this.o.getGraphics();
            }
            if (this.p != null) {
                this.n();
                this.p.setColor(this.k.getBackground());
                this.p.fillRect(0, 0, this.q, this.r);
                this.p.drawImage(this.l, this.S, this.T, this.S + this.U, this.T + this.V, this.O, this.P, this.O + this.Q, this.P + this.R, this.k);
            }
        }
    }
    
    public int a(final int n) {
        int n2 = 0;
        if (this.l != null) {
            if (this.p == null) {
                this.o = this.k.createImage(this.q, this.r);
                this.p = this.o.getGraphics();
            }
            if ((this.p != null && (Math.abs(this.L - this.I) >= 1.0 || Math.abs(this.M - this.J) >= 1.0)) || Math.abs(this.N - this.K) >= 1.0 || this.W) {
                this.L = this.I;
                this.M = this.J;
                this.N = this.K;
                this.W = false;
                this.p.setColor(this.k.getBackground());
                this.p.fillRect(0, 0, this.q, this.r);
                this.p.drawImage(this.l, this.S, this.T, this.S + this.U, this.T + this.V, this.O, this.P, this.O + this.Q, this.P + this.R, this.k);
                this.o();
                n2 = 1;
                this.k.repaint();
            }
        }
        return n2;
    }
}
