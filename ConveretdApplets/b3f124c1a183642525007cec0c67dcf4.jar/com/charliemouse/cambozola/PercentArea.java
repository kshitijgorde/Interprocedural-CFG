// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola;

import java.awt.Rectangle;

public class PercentArea
{
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public static final int UP = -1;
    public static final int DOWN = 1;
    double p1x;
    double p1y;
    double p2x;
    double p2y;
    double centx;
    double centy;
    
    public PercentArea() {
        this.reset();
    }
    
    public boolean setBounds(final double n, final double n2, final double n3, final double n4) {
        final boolean b = true;
        this.p1x = Math.max(Math.min(n, n3), 0.0);
        this.p2x = Math.min(Math.max(n, n3), 100.0);
        this.p1y = Math.max(Math.min(n2, n4), 0.0);
        this.p2y = Math.min(Math.max(n2, n4), 100.0);
        this.centx = (this.p1x + this.p2x) / 2.0;
        this.centy = (this.p1y + this.p2y) / 2.0;
        return b;
    }
    
    public boolean setBoundsAspect(final double n, final double n2, final double n3, final double n4) {
        final boolean b = true;
        final double n5 = Math.min(n3, 100.0) / 2.0;
        final double n6 = Math.min(n4, 100.0) / 2.0;
        double centx = n;
        if (n <= n5) {
            centx = n5;
        }
        else if (n + n5 > 100.0) {
            centx = 100.0 - n5;
        }
        double centy = n2;
        if (n2 <= n6) {
            centy = n6;
        }
        else if (n2 + n6 > 100.0) {
            centy = 100.0 - n6;
        }
        this.p1x = centx - n5;
        this.p1y = centy - n6;
        this.p2x = centx + n5;
        this.p2y = centy + n6;
        this.centx = centx;
        this.centy = centy;
        return b;
    }
    
    public void reset() {
        this.setBounds(0.0, 0.0, 100.0, 100.0);
    }
    
    public double getWidth() {
        return this.p2x - this.p1x;
    }
    
    public double getHeight() {
        return this.p2y - this.p1y;
    }
    
    public Rectangle getArea(final int n, final int n2) {
        return new Rectangle((int)(this.p1x * n / 100.0), (int)(this.p1y * n2 / 100.0), (int)(this.getWidth() * n / 100.0), (int)(this.getHeight() * n2 / 100.0));
    }
    
    public boolean zoomIn() {
        final double n = this.getWidth() / 4.0;
        final double n2 = this.getHeight() / 4.0;
        return n >= 1.0 && n2 >= 1.0 && this.setBounds(this.centx - n, this.centy - n2, this.centx + n, this.centy + n2);
    }
    
    public boolean zoomOut() {
        return this.setBoundsAspect(this.centx, this.centy, this.getWidth() * 2.0, this.getHeight() * 2.0);
    }
    
    public boolean panHorizontal(final int n) {
        return this.setBoundsAspect(this.centx + Math.max(this.getWidth() / 5.0, 1.0) * n, this.centy, this.getWidth(), this.getHeight());
    }
    
    public boolean panVertical(final int n) {
        return this.setBoundsAspect(this.centx, this.centy + Math.max(this.getHeight() / 5.0, 1.0) * n, this.getWidth(), this.getHeight());
    }
    
    public String toString() {
        return "[" + this.p1x + "," + this.p1y + "] ==> [" + this.p2x + "," + this.p2y + "] (w=" + this.getWidth() + ", h=" + this.getHeight() + ")";
    }
}
