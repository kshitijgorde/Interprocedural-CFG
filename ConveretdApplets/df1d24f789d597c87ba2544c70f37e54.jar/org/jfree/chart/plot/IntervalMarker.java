// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.awt.Stroke;
import java.awt.Paint;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.Serializable;

public class IntervalMarker extends Marker implements Cloneable, Serializable
{
    private double startValue;
    private double endValue;
    
    public IntervalMarker(final double start, final double end) {
        this(start, end, Color.gray, new BasicStroke(0.5f), Color.blue, new BasicStroke(0.5f), 0.8f);
    }
    
    public IntervalMarker(final double start, final double end, final Paint paint, final Stroke stroke, final Paint outlinePaint, final Stroke outlineStroke, final float alpha) {
        super(paint, stroke, outlinePaint, outlineStroke, alpha);
        this.startValue = start;
        this.endValue = end;
    }
    
    public double getStartValue() {
        return this.startValue;
    }
    
    public double getEndValue() {
        return this.endValue;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
