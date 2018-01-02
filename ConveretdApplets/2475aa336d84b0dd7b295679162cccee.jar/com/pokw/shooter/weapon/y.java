// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

class y extends m
{
    private double d;
    private double a;
    private double f;
    private Rectangle2D c;
    private Line2D e;
    private int b;
    
    public y(final double n, final double n2, final double d, final double a, final int b) {
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = new Rectangle2D.Double(n, n2, 9.0, 30.0);
        this.e = new Line2D.Double(n, n2, n, n2);
        this.f = -0.85;
    }
    
    public void c() {
        final double centerX = this.c.getCenterX();
        final double centerY = this.c.getCenterY();
        final double minX = this.c.getMinX();
        final double minY = this.c.getMinY();
        this.a += this.f;
        this.c.setRect(minX + this.d, minY + this.a, 9.0, 30.0);
        this.e.setLine(centerX, centerY, this.c.getCenterX(), this.c.getCenterY());
    }
    
    public Rectangle2D a() {
        return this.c;
    }
    
    public Line2D b() {
        return this.e;
    }
    
    public void a(final Graphics2D graphics2D, final ImageObserver imageObserver) {
        graphics2D.drawImage(com.pokw.shooter.y.E, (int)this.c.getMinX(), (int)this.c.getMinY(), imageObserver);
    }
    
    public int d() {
        return this.b;
    }
}
