// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.awt.Shape;

public interface ShapeFactory
{
    Shape getShape(final int p0, final int p1, final double p2, final double p3, final double p4);
    
    Shape getShape(final int p0, final Object p1, final double p2, final double p3, final double p4);
}
