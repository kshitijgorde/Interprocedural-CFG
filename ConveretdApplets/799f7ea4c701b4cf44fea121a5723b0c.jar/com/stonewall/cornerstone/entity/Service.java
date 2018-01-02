// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.IModelObject;

public interface Service
{
    boolean equals(final Object p0);
    
    boolean contains(final Service p0);
    
    void setId(final String p0);
    
    EntityReference getReference();
    
    boolean isAny();
    
    EntityFactory.EntityType getEntityType();
    
    IModelObject getRoot();
}
