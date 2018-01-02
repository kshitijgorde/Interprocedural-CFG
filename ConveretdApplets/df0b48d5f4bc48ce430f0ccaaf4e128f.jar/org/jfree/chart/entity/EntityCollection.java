// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.entity;

import java.util.Iterator;
import java.util.Collection;

public interface EntityCollection
{
    void clear();
    
    void add(final ChartEntity p0);
    
    void addAll(final EntityCollection p0);
    
    ChartEntity getEntity(final double p0, final double p1);
    
    ChartEntity getEntity(final int p0);
    
    int getEntityCount();
    
    Collection getEntities();
    
    Iterator iterator();
}
