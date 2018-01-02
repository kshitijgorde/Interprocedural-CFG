// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public interface XYDataset extends SeriesDataset
{
    int getItemCount(final int p0);
    
    Number getXValue(final int p0, final int p1);
    
    double getX(final int p0, final int p1);
    
    Number getYValue(final int p0, final int p1);
    
    double getY(final int p0, final int p1);
}
