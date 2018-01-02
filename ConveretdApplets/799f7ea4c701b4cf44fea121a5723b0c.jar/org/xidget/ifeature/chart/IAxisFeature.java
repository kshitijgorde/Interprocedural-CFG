// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.chart;

import org.xidget.chart.Scale;
import org.xidget.IXidget;

public interface IAxisFeature
{
    void setGraph(final String p0, final IXidget p1);
    
    void setExtrema(final double p0, final double p1);
    
    void setLogBase(final int p0);
    
    void setFormat(final Scale.Format p0);
}
