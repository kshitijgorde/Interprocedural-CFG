// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.planes;

import java.util.Vector;
import com.pokw.shooter.y;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import com.pokw.shooter.weapon.o;
import com.pokw.shooter.weapon.A;

public class b extends c
{
    private int f;
    private int a;
    private int i;
    private A c;
    private A[] n;
    
    public b(final double n, final double n2) {
        super(n, n2, 0.0, 0.0, 150);
        this.f = 0;
        this.a = 0;
        this.i = 0;
        this.c = o.k;
        this.n = new A[3];
        this.q();
    }
    
    public void a(final int n, final int n2) {
        this.h += this.e;
        this.k += this.u;
        final double centerX = this.a().getCenterX();
        final double centerY = this.a().getCenterY();
        double n3 = this.a().getMinX() + this.h;
        double n4 = this.a().getMinY() + this.k;
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        if (n3 + this.m.getWidth() > n) {
            n3 = n - this.m.getWidth();
        }
        if (n4 < 0.0) {
            n4 = 0.0;
        }
        if (n4 + this.m.getHeight() > n2) {
            n4 = n2 - this.m.getHeight();
        }
        this.m.setRect(n3, n4, this.j(), this.e());
        this.t.setLine(centerX, centerY, this.a().getCenterX(), this.a().getCenterY());
        if (this.h()) {
            ++this.l;
        }
        ++this.a;
        ++this.f;
    }
    
    public int j() {
        return 59;
    }
    
    public int e() {
        return 43;
    }
    
    public void b(final Graphics2D graphics2D, final ImageObserver imageObserver) {
        graphics2D.drawImage(y.p[this.f % 3], (int)this.k(), (int)this.l(), imageObserver);
    }
    
    public A n() {
        return this.n[this.i];
    }
    
    public void o() {
        ++this.i;
        if (this.i == this.n.length) {
            this.i = 0;
        }
    }
    
    public void q() {
        for (int i = 0; i < this.n.length; ++i) {
            this.n[i] = this.c;
        }
    }
    
    public void a(final A a) {
        this.n[this.i] = a;
    }
    
    public int p() {
        return this.i + 1;
    }
    
    public void a(final Vector vector) {
        if (this.h()) {
            return;
        }
        if (this.a > this.n().b()) {
            this.n().a(vector, this);
            this.a = 0;
            y.b();
        }
        else {
            ++this.a;
        }
    }
    
    public void d(final double h) {
        this.h = h;
    }
    
    public void c(final double k) {
        this.k = k;
    }
    
    public void b(final int n) {
        this.p += Math.min(this.r - this.p, n);
    }
}
