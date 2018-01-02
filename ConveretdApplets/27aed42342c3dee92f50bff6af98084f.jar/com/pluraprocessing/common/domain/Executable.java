// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.util.UUID;

public class Executable
{
    private UUID id;
    private UUID ownerId;
    private boolean lazyLoad;
    private boolean isRestricted;
    private String sha1Value;
    
    public Executable() {
        this.id = UUID.randomUUID();
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public void setId(final UUID id) {
        this.id = id;
    }
    
    public UUID getOwnerId() {
        return this.ownerId;
    }
    
    public void setOwnerId(final UUID ownerId) {
        this.ownerId = ownerId;
    }
    
    public boolean isLazyLoad() {
        return this.lazyLoad;
    }
    
    public void setLazyLoad(final boolean lazyLoad) {
        this.lazyLoad = lazyLoad;
    }
    
    public boolean isRestricted() {
        return this.isRestricted;
    }
    
    public void setRestricted(final boolean isRestricted) {
        this.isRestricted = isRestricted;
    }
    
    public String getSha1Value() {
        return this.sha1Value;
    }
    
    public void setSha1Value(final String sha1Value) {
        this.sha1Value = sha1Value;
    }
}
