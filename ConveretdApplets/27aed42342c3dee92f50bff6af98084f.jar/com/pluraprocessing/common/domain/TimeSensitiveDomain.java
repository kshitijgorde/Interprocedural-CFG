// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

public class TimeSensitiveDomain
{
    private String id;
    private int delaySeconds;
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public int getDelaySeconds() {
        return this.delaySeconds;
    }
    
    public void setDelaySeconds(final int delaySeconds) {
        this.delaySeconds = delaySeconds;
    }
}
