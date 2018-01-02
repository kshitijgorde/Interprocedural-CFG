// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.entity;

import java.util.Iterator;
import java.util.Collection;

public interface EntityCollection
{
    void clear();
    
    void addEntity(final ChartEntity p0);
    
    void addEntities(final EntityCollection p0);
    
    ChartEntity getEntity(final double p0, final double p1);
    
    Collection getEntities();
    
    Iterator iterator();
}
