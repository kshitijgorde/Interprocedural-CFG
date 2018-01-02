// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Work
{
    private UUID id;
    private String content;
    private byte[] byteContent;
    private Date priorityDate;
    private ArrayList<Data> data;
    private WorkVersion version;
    private String reloadVersion;
    private String timeSensitiveDomainId;
    private int timeSensitiveDelay;
    
    public Work() {
        this.id = UUID.randomUUID();
        this.priorityDate = new Date();
        this.data = new ArrayList<Data>();
        this.version = new WorkVersion();
    }
    
    public String getTimeSensitiveDomainId() {
        return this.timeSensitiveDomainId;
    }
    
    public void setTimeSensitiveDomainId(final String timeSensitiveDomainId) {
        this.timeSensitiveDomainId = timeSensitiveDomainId;
    }
    
    public int getTimeSensitiveDelay() {
        return this.timeSensitiveDelay;
    }
    
    public void setTimeSensitiveDelay(final int timeSensitiveDelay) {
        this.timeSensitiveDelay = timeSensitiveDelay;
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
    
    public Date getPriorityDate() {
        return this.priorityDate;
    }
    
    public void setPriorityDate(final Date dateAdddedOrRequested) {
        this.priorityDate = dateAdddedOrRequested;
    }
    
    public ArrayList<Data> getData() {
        return this.data;
    }
    
    public void setData(final ArrayList<Data> data) {
        this.data = data;
    }
    
    public WorkVersion getVersion() {
        return this.version;
    }
    
    public void setVersion(final WorkVersion version) {
        this.version = version;
    }
    
    public String getReloadVersion() {
        return this.reloadVersion;
    }
    
    public void setReloadVersion(final String reloadVersion) {
        this.reloadVersion = reloadVersion;
    }
    
    public byte[] getByteContent() {
        return this.byteContent;
    }
    
    public void setByteContent(final byte[] byteContent) {
        this.byteContent = byteContent;
    }
}
