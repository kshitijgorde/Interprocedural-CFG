// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.awt.Color;
import java.awt.Paint;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class GrayPaintScale implements PaintScale, PublicCloneable, Serializable
{
    private double lowerBound;
    private double upperBound;
    
    public GrayPaintScale() {
        this(0.0, 1.0);
    }
    
    public GrayPaintScale(final double lowerBound, final double upperBound) {
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("Requires lowerBound < upperBound.");
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    
    public double getLowerBound() {
        return this.lowerBound;
    }
    
    public double getUpperBound() {
        return this.upperBound;
    }
    
    public Paint getPaint(final double value) {
        double v = Math.max(value, this.lowerBound);
        v = Math.min(v, this.upperBound);
        final int g = (int)((value - this.lowerBound) / (this.upperBound - this.lowerBound) * 255.0);
        return new Color(g, g, g);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GrayPaintScale)) {
            return false;
        }
        final GrayPaintScale that = (GrayPaintScale)obj;
        return this.lowerBound == that.lowerBound && this.upperBound == that.upperBound;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
