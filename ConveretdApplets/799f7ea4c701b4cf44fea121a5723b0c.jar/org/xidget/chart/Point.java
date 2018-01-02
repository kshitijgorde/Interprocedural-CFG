// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.chart;

public class Point
{
    public double[] coords;
    public int[][] shape;
    public String color;
    public String label;
    public Point prev;
    public Point next;
    
    public Point() {
        this.shape = PointStyles.getLineSegments(PointStyles.Style.ex);
    }
}
