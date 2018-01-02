// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.awt.Stroke;
import java.awt.Paint;

public class ValueMarker extends Marker
{
    private double value;
    
    public ValueMarker(final double value) {
        this.value = value;
    }
    
    public ValueMarker(final double value, final Paint paint, final Stroke stroke, final Paint outlinePaint, final Stroke outlineStroke, final float alpha) {
        super(paint, stroke, paint, stroke, alpha);
        this.value = value;
    }
    
    public double getValue() {
        return this.value;
    }
}
