// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public class LineFunction2D implements Function2D
{
    private double a;
    private double b;
    
    public LineFunction2D(final double a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public double getValue(final double x) {
        return this.a + this.b * x;
    }
}