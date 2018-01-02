// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public interface HighLowDataset extends XYDataset
{
    Number getHighValue(final int p0, final int p1);
    
    double getHigh(final int p0, final int p1);
    
    Number getLowValue(final int p0, final int p1);
    
    double getLow(final int p0, final int p1);
    
    Number getOpenValue(final int p0, final int p1);
    
    double getOpen(final int p0, final int p1);
    
    Number getCloseValue(final int p0, final int p1);
    
    double getClose(final int p0, final int p1);
    
    Number getVolumeValue(final int p0, final int p1);
    
    double getVolume(final int p0, final int p1);
}
