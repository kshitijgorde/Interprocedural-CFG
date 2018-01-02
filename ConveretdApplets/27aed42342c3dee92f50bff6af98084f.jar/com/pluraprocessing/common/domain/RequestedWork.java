// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.util.Calendar;
import java.util.UUID;

public class RequestedWork implements Comparable<RequestedWork>
{
    private UUID id;
    private Calendar dateRequested;
    private Work work;
    private String versionId;
    private int numberBytesInRequest;
    
    public RequestedWork() {
        this.dateRequested = Calendar.getInstance();
        this.work = new Work();
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public void setId(final UUID id) {
        this.id = id;
    }
    
    public Calendar getDateRequested() {
        return this.dateRequested;
    }
    
    public void setDateRequested(final Calendar dateRequested) {
        this.dateRequested = dateRequested;
    }
    
    public Work getWork() {
        return this.work;
    }
    
    public void setWork(final Work work) {
        this.work = work;
    }
    
    public String getVersionId() {
        return this.versionId;
    }
    
    public void setVersionId(final String versionId) {
        this.versionId = versionId;
    }
    
    @Override
    public int compareTo(final RequestedWork o) {
        return this.dateRequested.compareTo(o.dateRequested);
    }
    
    public int getNumberBytesInRequest() {
        return this.numberBytesInRequest;
    }
    
    public void setNumberBytesInRequest(final int numberBytesInRequest) {
        this.numberBytesInRequest = numberBytesInRequest;
    }
}
