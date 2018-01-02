// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl;

import java.awt.Graphics;

public class dataPoint
{
    boolean active;
    int x;
    int y;
    double radius;
    public static final boolean DEBUG = false;
    public static final short OFFSET = 0;
    
    public dataPoint() {
        this(0, 0);
    }
    
    public dataPoint(final int x, final int y) {
        this.active = false;
        this.x = x;
        this.y = y;
        this.radius = 3.0;
    }
    
    public boolean gotFocus(final int n, final int n2) {
        return this.active = this.inside(n, n2);
    }
    
    private boolean inside(int n, int n2) {
        n += 0;
        n2 += 0;
        return Math.sqrt(Math.pow(n - this.x, 2.0) + Math.pow(n2 - this.y, 2.0)) < this.radius;
    }
    
    public void update(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public void paint(final Graphics graphics, final int n, final int n2, final boolean b) {
        if (!b) {
            graphics.drawOval((int)Math.round(n - this.radius), (int)Math.round(n2 - this.radius), (int)Math.round(2.0 * this.radius), (int)Math.round(2.0 * this.radius));
        }
        else {
            graphics.fillOval((int)Math.round(n - this.radius), (int)Math.round(n2 - this.radius), (int)Math.round(2.0 * this.radius), (int)Math.round(2.0 * this.radius));
        }
    }
    
    public void paint(final Graphics graphics) {
        this.paint(graphics, this.x, this.y, false);
    }
    
    public void paint(final Graphics graphics, final boolean b) {
        this.paint(graphics, this.x, this.y, b);
    }
    
    public boolean getActive() {
        return this.active;
    }
    
    public void setActive(final boolean active) {
        this.active = active;
    }
}
