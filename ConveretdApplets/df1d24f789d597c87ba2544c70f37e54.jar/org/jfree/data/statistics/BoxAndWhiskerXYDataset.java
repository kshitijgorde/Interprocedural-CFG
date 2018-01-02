// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.statistics;

import java.util.List;
import org.jfree.data.XYDataset;

public interface BoxAndWhiskerXYDataset extends XYDataset
{
    Number getMeanValue(final int p0, final int p1);
    
    Number getMedianValue(final int p0, final int p1);
    
    Number getQ1Value(final int p0, final int p1);
    
    Number getQ3Value(final int p0, final int p1);
    
    Number getMinRegularValue(final int p0, final int p1);
    
    Number getMaxRegularValue(final int p0, final int p1);
    
    Number getMinOutlier(final int p0, final int p1);
    
    Number getMaxOutlier(final int p0, final int p1);
    
    List getOutliers(final int p0, final int p1);
    
    double getOutlierCoefficient();
    
    double getFaroutCoefficient();
}
