// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.xy;

public interface VectorXYDataset extends XYDataset
{
    double getVectorXValue(final int p0, final int p1);
    
    double getVectorYValue(final int p0, final int p1);
    
    Vector getVector(final int p0, final int p1);
}
