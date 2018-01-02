// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization.elements;

public class Axes
{
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    
    public Axes(final double minX, final double minY, final double maxX, final double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    
    public double getMinX() {
        return this.minX;
    }
    
    public double getMaxX() {
        return this.maxX;
    }
    
    public double getMinY() {
        return this.minY;
    }
    
    public double getMaxY() {
        return this.maxY;
    }
    
    public Axes clone() {
        return new Axes(this.minX, this.minY, this.maxX, this.maxY);
    }
    
    public String toString() {
        return "Axes:  (" + this.minX + ", " + this.minY + ") -> (" + this.maxX + ", " + this.maxY + ")";
    }
}
