// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.util.Date;
import java.util.UUID;

public class CompletedWork implements Comparable<CompletedWork>
{
    private UUID id;
    private String clientIdentifier;
    private String affiliateId;
    private Work work;
    private Date dateRequested;
    private Date dateCompleted;
    private String result;
    private Double cpuUsage;
    private BandwidthResult bandwidthResult;
    
    public CompletedWork() {
        this.id = UUID.randomUUID();
        this.work = new Work();
        this.dateRequested = new Date();
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public void setId(final UUID id) {
        this.id = id;
    }
    
    public String getClientIdentifier() {
        return this.clientIdentifier;
    }
    
    public void setClientIdentifier(final String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }
    
    public String getAffiliateId() {
        return this.affiliateId;
    }
    
    public void setAffiliateId(final String affiliateId) {
        this.affiliateId = affiliateId;
    }
    
    public Work getWork() {
        return this.work;
    }
    
    public void setWork(final Work work) {
        this.work = work;
    }
    
    public Date getDateRequested() {
        return this.dateRequested;
    }
    
    public void setDateRequested(final Date dateRequested) {
        this.dateRequested = dateRequested;
    }
    
    public Date getDateCompleted() {
        return this.dateCompleted;
    }
    
    public void setDateCompleted(final Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
    
    public String getResult() {
        return this.result;
    }
    
    public void setResult(final String result) {
        this.result = result;
    }
    
    public Double getCpuUsage() {
        return this.cpuUsage;
    }
    
    public void setCpuUsage(final Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }
    
    public BandwidthResult getBandwidthResult() {
        return this.bandwidthResult;
    }
    
    public void setBandwidthResult(final BandwidthResult bandwidthResult) {
        this.bandwidthResult = bandwidthResult;
    }
    
    @Override
    public int compareTo(final CompletedWork cw) {
        final int versionComparision = this.getWork().getVersion().getId().toString().compareTo(cw.getWork().getVersion().getId().toString());
        if (versionComparision == 0) {
            return this.getDateRequested().compareTo(cw.getDateRequested());
        }
        return versionComparision;
    }
}
