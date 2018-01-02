// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public interface ContourDataset extends XYZDataset
{
    double getMinZValue();
    
    double getMaxZValue();
    
    Number[] getXValues();
    
    Number[] getYValues();
    
    Number[] getZValues();
    
    int[] indexX();
    
    int[] getXIndices();
    
    Range getZValueRange(final Range p0, final Range p1);
    
    boolean isDateAxis(final int p0);
}
