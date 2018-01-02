// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;

public class A extends E
{
    public static final int u = 15;
    private static final double t = 1.0471975511965976;
    private static final int q = 20;
    private double v;
    private double s;
    private B p;
    Integer r;
    
    public A(final B p2, final Component component) {
        super(component);
        this.v = 100.0;
        this.s = 0.0;
        this.r = null;
        this.p = p2;
    }
    
    public Dimension L() {
        return new Dimension(15, 50);
    }
    
    public void C(final Graphics graphics) {
        final int width = this.B().width;
        final int height = this.B().height;
        graphics.setColor(this.D());
        graphics.fill3DRect(0, 0, width, this.M(), true);
        graphics.fill3DRect(0, height - this.M(), width, height, true);
        graphics.setColor(this.D().darker().darker());
        final int n = this.M() / 2;
        final int n2 = (int)(n * Math.sin(1.0471975511965976));
        final int n3 = (width - n) / 2;
        final int n4 = (this.M() - n2) / 2;
        final Polygon polygon = new Polygon(new int[] { n3, n3 + n / 2, n3 + n }, new int[] { n4 + n2, n4 + 0, n4 + n2 }, 3);
        final Polygon polygon2 = new Polygon(new int[] { n3, n3 + n / 2, n3 + n }, new int[] { height - n2 - n4, height - n4, height - n2 - n4 }, 3);
        graphics.fillPolygon(polygon);
        graphics.fillPolygon(polygon2);
        graphics.setColor(this.D().brighter());
        graphics.fillRect(0, this.M(), width, height - 2 * this.M());
        graphics.setColor(this.D());
        final Rectangle n5 = this.N();
        graphics.fill3DRect(n5.x, n5.y, n5.width, n5.height, true);
    }
    
    private Rectangle N() {
        return new Rectangle(0, this.O(), this.B().width, 20);
    }
    
    private int O() {
        final int m = this.M();
        return (int)(m + (this.B().height - 2 * m - 20) * this.s / this.v);
    }
    
    private int M() {
        return this.L().width;
    }
    
    private double G(final int n) {
        final int m = this.M();
        return (n - m) * this.v / (this.B().height - 2 * m - 20);
    }
    
    public void F(final int n) {
        this.v = n;
    }
    
    public int Q() {
        return (int)this.s;
    }
    
    private void A(final double n, final boolean b) {
        final double min = Math.min(this.v, Math.max(n, 0.0));
        if (Math.abs(this.s - min) < 1.0E-6) {
            return;
        }
        final Rectangle n2 = this.N();
        this.s = min;
        final Rectangle n3 = this.N();
        if (!n3.equals(n2)) {
            this.A(n2.x, n2.y, n2.width, n2.height);
            this.A(n3.x, n3.y, n3.width, n3.height);
        }
        if (b) {
            this.p.C(this.Q());
        }
    }
    
    public void H(final int n) {
        this.A(n, true);
    }
    
    public void D(final Point point) {
        if (this.N().contains(point)) {
            return;
        }
        int n = -1;
        if (this.N().y + this.N().height < point.y) {
            n = 1;
        }
        this.p.B(n);
    }
    
    public void E(final Point point) {
        if (this.N().contains(point)) {
            this.r = new Integer(point.y - this.N().y);
        }
        else {
            this.r = null;
        }
    }
    
    public void K() {
        this.r = null;
    }
    
    public void F(final Point point) {
        if (this.r == null) {
            return;
        }
        final int m = this.M();
        int n = point.y - this.r;
        if (n < m) {
            n = m;
        }
        if (n > this.B().height - m) {
            n = this.B().height - m;
        }
        this.A(this.G(n), true);
    }
    
    public int P() {
        return (int)this.v;
    }
}
