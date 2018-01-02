// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.block;

import org.jfree.chart.entity.EntityCollection;

public class BlockResult implements EntityBlockResult
{
    private EntityCollection entities;
    
    public BlockResult() {
        this.entities = null;
    }
    
    public EntityCollection getEntityCollection() {
        return this.entities;
    }
    
    public void setEntityCollection(final EntityCollection entities) {
        this.entities = entities;
    }
}
