// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.planes;

import java.awt.Shape;
import java.awt.Color;
import com.pokw.shooter.y;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import com.pokw.shooter.e;

public abstract class c extends e
{
    protected double d;
    protected double q;
    protected double j;
    protected double g;
    protected double v;
    protected double s;
    protected double h;
    protected double k;
    protected double e;
    protected double u;
    protected int p;
    protected int r;
    protected int l;
    protected Line2D t;
    protected Rectangle2D m;
    protected boolean b;
    
    protected c(final double d, final double q, final double j, final double g, final int r) {
        this.b = false;
        this.d = d;
        this.q = q;
        this.j = j;
        this.g = g;
        this.r = r;
        this.f();
        this.t = new Line2D.Double(d, q, d, q);
        this.m = new Rectangle2D.Double(d, q, this.j(), this.e());
    }
    
    public abstract void b(final Graphics2D p0, final ImageObserver p1);
    
    public abstract int j();
    
    public abstract int e();
    
    public final Line2D b() {
        return this.t;
    }
    
    public final Rectangle2D a() {
        return this.m;
    }
    
    public final void a(final Graphics2D graphics2D, final ImageObserver imageObserver) {
        if (this.h()) {
            final int n = (int)this.a().getMinX() - 1;
            final int n2 = (int)this.a().getMinY() - 1;
            final int n3 = this.l / 2;
            graphics2D.drawImage(y.J, n, n2, n + this.j(), n2 + this.e(), n3 + n3 * 32, 0, n3 + n3 * 32 + 32, 32, imageObserver);
        }
        else {
            this.b(graphics2D, imageObserver);
            if (this.b) {
                graphics2D.setColor(Color.red);
                graphics2D.draw(this.a());
                this.b = false;
            }
        }
    }
    
    public final boolean i() {
        return this.l / 2 >= 6;
    }
    
    public final boolean h() {
        return this.p <= 0;
    }
    
    public final void a(final int n) {
        this.p -= n;
        this.b = true;
        if (this.p <= 0) {
            y.c();
        }
    }
    
    public void f() {
        this.v = this.d;
        this.s = this.q;
        this.h = this.j;
        this.k = this.g;
        this.p = this.r;
        this.e = 0.0;
        this.u = 0.0;
        this.l = 0;
    }
    
    public void a(final double e) {
        this.e = e;
    }
    
    public void b(final double u) {
        this.u = u;
    }
    
    public void a(final double n, final double n2) {
        this.a(n);
        this.b(n2);
    }
    
    public double k() {
        return this.m.getMinX();
    }
    
    public double l() {
        return this.m.getMinY();
    }
    
    public double d() {
        return this.m.getCenterX();
    }
    
    public double c() {
        return this.m.getMaxY();
    }
    
    public int m() {
        return this.p;
    }
    
    public int g() {
        return this.r;
    }
}
