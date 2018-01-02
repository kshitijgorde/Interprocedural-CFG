// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.draw;

public class LargePoint
{
    public double x;
    public double y;
    
    public LargePoint() {
        this(0.0, 0.0);
    }
    
    public LargePoint(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public LargePoint(final LargePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void move(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public void translate(final double x, final double y) {
        this.x += x;
        this.y += y;
    }
    
    public boolean equals(final LargePoint pt) {
        return pt != null && this.x == pt.x && this.y == pt.y;
    }
    
    public String toString() {
        return this.getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
    }
}
