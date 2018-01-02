// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Slice
{
    double a;
    private Color b;
    private Rectangle c;
    Rectangle d;
    private int e;
    private int f;
    private i g;
    private i h;
    private i i;
    i j;
    int k;
    double l;
    double m;
    boolean n;
    PieSerie o;
    SliceLegend p;
    i q;
    
    public void setSliceLegendEnabled(final boolean n) {
        this.n = n;
    }
    
    public void setPosition(int k) {
        if (k < 0) {
            k = 0;
        }
        this.k = k;
    }
    
    public SliceLegend getSliceLegend() {
        return this.p;
    }
    
    public void setColor(final Color b) {
        this.b = b;
        this.o.D[this.getIndex()] = this.b;
    }
    
    public Color getColor() {
        return this.b;
    }
    
    public final int getIndex() {
        final boolean g = GraphSerie.G;
        int n = 0;
        int n2 = 0;
        while (true) {
        Label_0046:
            while (true) {
                Label_0034: {
                    if (!g) {
                        break Label_0034;
                    }
                    Label_0031: {
                        if (this.o.v[n2] != this) {
                            break Label_0031;
                        }
                        final int n3;
                        n = n3;
                        if (!g) {
                            break Label_0046;
                        }
                    }
                    ++n2;
                }
                if (n2 < this.o.v.length) {
                    continue;
                }
                break;
            }
            final int n3 = n;
            if (!g) {
                return n3;
            }
            continue;
        }
    }
    
    public final void setValue(final double a) {
        this.a = a;
        this.o.a[this.getIndex()] = this.a;
    }
    
    public double getValue() {
        return this.a;
    }
    
    int a() {
        return this.k;
    }
    
    boolean b() {
        return this.k > 0;
    }
    
    void a(final double l) {
        this.l = l;
    }
    
    void b(final double m) {
        this.m = m;
    }
    
    double c() {
        return Math.cos(((this.f - this.e) / 2.0 + this.e) * 3.141592653589793 / 180.0) * this.l;
    }
    
    double d() {
        return Math.sin(((this.f - this.e) / 2.0 + this.e) * 3.141592653589793 / 180.0);
    }
    
    void a(final int x, final int y, final int width, final int height) {
        this.c.x = x;
        this.c.y = y;
        this.c.width = width;
        this.c.height = height;
    }
    
    Rectangle e() {
        return this.c;
    }
    
    void f() {
        this.d.x = (int)(this.c.x + this.c() * this.k);
        this.d.y = (int)(this.c.y - this.d() * this.k);
        this.d.width = this.c.width;
        this.d.height = this.c.height;
    }
    
    void a(final int e, final int f) {
        this.e = e;
        this.f = f;
    }
    
    int g() {
        return this.e;
    }
    
    int h() {
        return this.f;
    }
    
    void a(final Graphics graphics) {
        final boolean g = GraphSerie.G;
        final double n = this.c.x + this.c.width / 2.0;
        final double n2 = this.c.y + this.c.height / 2.0;
        this.q.a = n + this.c() * this.k;
        this.q.b = n2 - this.d() * this.k;
        Label_0162: {
            if (this.o.y != 0) {
                Label_0149: {
                    if (this.o.u && this.o.y == this.o.x) {
                        graphics.setColor(Color.black);
                        if (!g) {
                            break Label_0149;
                        }
                    }
                    graphics.setColor(this.b.darker());
                }
                if (!g) {
                    break Label_0162;
                }
            }
            graphics.setColor(this.b);
        }
        if (this.c.width > 0 && this.c.height > 0) {
            graphics.fillArc((int)(this.c.x + this.c() * this.k), (int)(this.c.y - this.d() * this.k), this.c.width, this.c.height, this.e, this.f - this.e);
        }
        if (this.o.u) {
            this.c(graphics);
        }
    }
    
    void b(final Graphics graphics) {
        graphics.setColor(Color.black);
        if (this.o.y == 0 && this.c.width > 0 && this.c.height > 0) {
            graphics.drawArc((int)(this.c.x + this.c() * this.k), (int)(this.c.y - this.d() * this.k), this.c.width, this.c.height, this.e, this.f - this.e);
        }
        this.a(this.e, this.g);
        this.a(this.f, this.h);
        if (this.o.y == 0 && this.c.width > 0 && this.c.height > 0) {
            this.a(graphics, this.g);
            this.a(graphics, this.h);
        }
    }
    
    private void a(final Graphics graphics, final i i) {
        graphics.drawLine((int)this.q.a, (int)this.q.b, (int)i.a, (int)i.b);
    }
    
    private void c(final Graphics graphics) {
        graphics.setColor(Color.black);
        this.a(this.e, this.g);
        this.a(this.f, this.h);
        if (!this.o.d.bJ.a(2, this.e)) {
            graphics.drawLine((int)this.g.a, (int)this.g.b, (int)this.g.a, (int)this.g.b);
        }
        if (!this.o.d.bJ.a(1, this.f)) {
            graphics.drawLine((int)this.h.a, (int)this.h.b, (int)this.h.a, (int)this.h.b);
        }
        if (!this.o.d.bJ.a(3, this.e) || !this.o.d.bJ.a(4, this.f)) {
            graphics.drawLine((int)this.q.a, (int)this.q.b, (int)this.q.a, (int)this.q.b);
        }
    }
    
    double a(final int n) {
        this.f();
        final double n2 = this.d.x + this.d.width / 2.0;
        final double n3 = this.d.y + this.d.height / 2.0;
        this.a(n, this.i);
        return Math.sqrt(Math.pow((this.i.a - n2) * this.m, 2.0) + Math.pow(n3 - this.i.b, 2.0));
    }
    
    void a(final int n, final i i) {
        final boolean g = GraphSerie.G;
        double n2 = 0.0;
        double n3 = 0.0;
        final double n4 = n * 3.141592653589793 / 180.0;
        Label_0305: {
            if (this.o.t) {
                final double n5 = this.c.width / 2.0;
                n2 = this.q.a + Math.cos(n4) * n5;
                n3 = this.q.b - Math.sin(n4) * n5;
                if (!g) {
                    break Label_0305;
                }
            }
            final double n6 = this.c.width / 2.0;
            final double n7 = this.c.height / 2.0;
            final double n8 = Math.tan(n4) * n7 / n6;
            final double n9 = n6 * n7 / Math.sqrt(Math.pow(n7, 2.0) + Math.pow(n6, 2.0) * Math.pow(n8, 2.0));
            final double n10 = n9 * n8;
            if (this.o.d.bJ.a(1, n) || this.o.d.bJ.a(4, n)) {
                n2 = this.q.a + n9;
                n3 = this.q.b - n10;
                if (!g) {
                    break Label_0305;
                }
            }
            if (this.o.d.bJ.a(2, n) || this.o.d.bJ.a(3, n)) {
                n2 = this.q.a - n9;
                n3 = this.q.b + n10;
            }
        }
        if (i == this.g) {
            this.g.a = n2;
            this.g.b = n3;
            if (!g) {
                return;
            }
        }
        if (i == this.h) {
            this.h.a = n2;
            this.h.b = n3;
            if (!g) {
                return;
            }
        }
        if (i == this.i) {
            this.i.a = n2;
            this.i.b = n3;
            if (!g) {
                return;
            }
        }
        if (i == this.j) {
            this.j.a = n2;
            this.j.b = n3;
        }
    }
    
    Slice(final PieSerie o, final double a) {
        this.c = new Rectangle();
        this.d = new Rectangle();
        this.g = new i();
        this.h = new i();
        this.i = new i();
        this.j = new i();
        this.q = new i(0.0, 0.0);
        this.o = o;
        this.a = a;
        this.p = new SliceLegend(this);
    }
}
