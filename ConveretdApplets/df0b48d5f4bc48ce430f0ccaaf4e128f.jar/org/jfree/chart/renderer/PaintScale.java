// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.awt.Paint;

public interface PaintScale
{
    double getLowerBound();
    
    double getUpperBound();
    
    Paint getPaint(final double p0);
}
