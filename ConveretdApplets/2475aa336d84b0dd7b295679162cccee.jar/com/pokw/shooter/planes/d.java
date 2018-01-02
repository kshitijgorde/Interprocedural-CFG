// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.planes;

import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.util.Vector;
import com.pokw.shooter.weapon.B;
import com.pokw.shooter.weapon.o;
import com.pokw.shooter.y;
import com.pokw.shooter.weapon.A;
import java.awt.Image;

public class d extends c
{
    private int c;
    private int o;
    private Image[] n;
    private int a;
    private A f;
    private int i;
    
    public static d q(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 30, y.x, 32, 32);
    }
    
    public static d i(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 30, y.i, 32, 32);
    }
    
    public static d a(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 30, y.W, 32, 32);
    }
    
    public static d o(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 30, y.ab, 32, 32);
    }
    
    public static d l(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 30, y.aa, 32, 32);
    }
    
    public static d u(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 30, y.H, 32, 32);
    }
    
    public static d r(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 30, y.R, 32, 32);
    }
    
    public static d f(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 30, y.e, 32, 32);
    }
    
    public static d e(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 60, y.u, 32, 32);
    }
    
    public static d k(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 75, y.Z, 50, 40);
    }
    
    public static d p(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 85, y.m, 60, 46);
    }
    
    public static d s(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 150, y.D, 93, 74);
    }
    
    public static d m(final double n, final double n2, final double n3, final double n4) {
        return new d(n, n2, n3, n4, 120, y.I, 85, 61);
    }
    
    public static d b(final double n, final double n2, final double n3, final double n4) {
        return new a(n, n2, n3, n4, y.G, o.i);
    }
    
    public static d n(final double n, final double n2, final double n3, final double n4) {
        return new a(n, n2, n3, n4, y.g, o.g);
    }
    
    public static d g(final double n, final double n2, final double n3, final double n4) {
        return new a(n, n2, n3, n4, y.L, o.d);
    }
    
    public static d t(final double n, final double n2, final double n3, final double n4) {
        return new a(n, n2, n3, n4, y.C, o.j);
    }
    
    public static d h(final double n, final double n2, final double n3, final double n4) {
        return new a(n, n2, n3, n4, y.a, o.f);
    }
    
    public static d j(final double n, final double n2, final double n3, final double n4) {
        return new a(n, n2, n3, n4, y.M, o.e);
    }
    
    public static d d(final double n, final double n2, final double n3, final double n4) {
        return new a(n, n2, n3, n4, y.k, o.c);
    }
    
    public static d c(final double n, final double n2, final double n3, final double n4) {
        return new com.pokw.shooter.planes.e(n, n2, n3, n4, 60, y.z, 31, 29);
    }
    
    protected d(final double n, final double n2, final double n3, final double n4, final int n5, final Image[] n6, final int c, final int o) {
        super(n, n2, n3, n4, n5);
        this.a = 0;
        this.f = B.b;
        this.i = 0;
        this.c = c;
        this.o = o;
        this.n = n6;
        this.a = n6.length;
    }
    
    public final void a(final Vector vector) {
        this.h += this.e;
        this.k += this.u;
        final double centerX = this.a().getCenterX();
        final double centerY = this.a().getCenterY();
        this.m.setRect(this.a().getMinX() + this.h, this.a().getMinY() + this.k, this.j(), this.e());
        this.t.setLine(centerX, centerY, this.a().getCenterX(), this.a().getCenterY());
        if (this.h()) {
            ++this.l;
        }
        this.f.a(vector, this);
        ++this.i;
    }
    
    public int j() {
        return this.c;
    }
    
    public int e() {
        return this.o;
    }
    
    public void b(final Graphics2D graphics2D, final ImageObserver imageObserver) {
        graphics2D.drawImage(this.n[this.i % this.a], (int)this.k(), (int)this.l(), imageObserver);
    }
    
    public void a(final A f) {
        this.f = f;
    }
    
    public void a(final b b) {
        final int min = Math.min(this.m(), b.m());
        this.a(min);
        b.a(min);
    }
}
