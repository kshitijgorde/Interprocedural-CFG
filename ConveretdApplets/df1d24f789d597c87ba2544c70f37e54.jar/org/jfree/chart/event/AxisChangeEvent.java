// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.event;

import org.jfree.chart.axis.Axis;

public class AxisChangeEvent extends ChartChangeEvent
{
    private Axis axis;
    
    public AxisChangeEvent(final Axis axis) {
        super(axis);
        this.axis = axis;
    }
    
    public Axis getAxis() {
        return this.axis;
    }
}
