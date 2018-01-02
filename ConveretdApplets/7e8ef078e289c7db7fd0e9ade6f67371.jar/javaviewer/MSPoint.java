// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Point;

public class MSPoint extends Point
{
    protected Point point;
    
    public MSPoint(final int n, final int n2) {
        this.point = new Point(n, n2);
    }
    
    public MSPoint(final Point point) {
        this.point = new Point(point.x, point.y);
    }
    
    public double getX() {
        return this.point.x;
    }
    
    public double getY() {
        return this.point.y;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.getClass().getName()))).append("[x=").append(this.point.x).append(",y=").append(this.point.y).append("]")));
    }
    
    public MSPoint setPoint(final Point point) {
        this.point = point;
        return this;
    }
}
