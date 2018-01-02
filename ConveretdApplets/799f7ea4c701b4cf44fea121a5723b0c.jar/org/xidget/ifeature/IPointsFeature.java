// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import org.xidget.chart.Point;

public interface IPointsFeature
{
    void add(final Point p0);
    
    void add(final int p0, final Point p1);
    
    void update(final Point p0, final int p1, final double p2);
    
    void update(final Point p0, final String p1);
    
    void remove(final int p0);
}
