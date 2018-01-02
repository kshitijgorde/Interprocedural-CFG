// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.util.UUID;

public class Data
{
    private UUID id;
    private String content;
    private UUID ownerId;
    private byte[] byteContent;
    private String externalId;
    
    public Data() {
        this.id = UUID.randomUUID();
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public void setId(final UUID id) {
        this.id = id;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public UUID getOwnerId() {
        return this.ownerId;
    }
    
    public void setOwnerId(final UUID ownerId) {
        this.ownerId = ownerId;
    }
    
    public byte[] getByteContent() {
        return this.byteContent;
    }
    
    public void setByteContent(final byte[] byteContent) {
        this.byteContent = byteContent;
    }
    
    public String getExternalId() {
        return this.externalId;
    }
    
    public void setExternalId(final String externalId) {
        this.externalId = externalId;
    }
}
