// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

public class BandwidthResult
{
    private String ipAddress;
    private Integer bandwidthUsedKB;
    
    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    public Integer getBandwidthUsedKB() {
        return this.bandwidthUsedKB;
    }
    
    public void setBandwidthUsedKB(final Integer bandwidthUsed) {
        this.bandwidthUsedKB = bandwidthUsed;
    }
}
