// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

public class EntityDefinition
{
    private Class entityClass;
    private Class dbAccess;
    private String displayName;
    
    public EntityDefinition(final Class entity, final Class dbAccess, final String displayName) {
        this.entityClass = entity;
        this.displayName = displayName;
        this.dbAccess = dbAccess;
    }
    
    Class getEntityClass() {
        return this.entityClass;
    }
    
    String getDisplayName() {
        return this.displayName;
    }
    
    Class getDbAccess() {
        return this.dbAccess;
    }
}
