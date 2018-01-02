// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.entity;

import org.jfree.util.ObjectUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.io.Serializable;

public class StandardEntityCollection implements EntityCollection, Cloneable, Serializable
{
    private Collection entities;
    
    public StandardEntityCollection() {
        this.entities = new ArrayList();
    }
    
    public void clear() {
        this.entities.clear();
    }
    
    public void addEntity(final ChartEntity entity) {
        this.entities.add(entity);
    }
    
    public void addEntities(final EntityCollection collection) {
        this.entities.addAll(collection.getEntities());
    }
    
    public ChartEntity getEntity(final double x, final double y) {
        ChartEntity result = null;
        for (final ChartEntity entity : this.entities) {
            if (entity.getArea().contains(x, y)) {
                result = entity;
            }
        }
        return result;
    }
    
    public Collection getEntities() {
        return Collections.unmodifiableCollection((Collection<?>)this.entities);
    }
    
    public Iterator iterator() {
        return this.entities.iterator();
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StandardEntityCollection) {
            final StandardEntityCollection c = (StandardEntityCollection)obj;
            return ObjectUtils.equal(this.entities, c.entities);
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
