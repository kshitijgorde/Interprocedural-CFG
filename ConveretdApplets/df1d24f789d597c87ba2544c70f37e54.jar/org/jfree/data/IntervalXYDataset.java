// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public interface IntervalXYDataset extends XYDataset
{
    Number getStartXValue(final int p0, final int p1);
    
    double getStartX(final int p0, final int p1);
    
    Number getEndXValue(final int p0, final int p1);
    
    double getEndX(final int p0, final int p1);
    
    Number getStartYValue(final int p0, final int p1);
    
    double getStartY(final int p0, final int p1);
    
    Number getEndYValue(final int p0, final int p1);
    
    double getEndY(final int p0, final int p1);
}
