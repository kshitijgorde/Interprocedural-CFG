// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

public class TimeSensitiveWork
{
    private List<Work> work;
    private long timestamp;
    private String ip;
    private TimeSensitiveDomain timeSensitiveDomain;
    
    public TimeSensitiveWork() {
        this.work = new ArrayList<Work>();
        this.timestamp = Calendar.getInstance().getTimeInMillis();
        this.timeSensitiveDomain = new TimeSensitiveDomain();
    }
    
    public List<Work> getWork() {
        return this.work;
    }
    
    public void setWork(final List<Work> work) {
        this.work = work;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
    }
    
    public TimeSensitiveDomain getTimeSensitiveDomain() {
        return this.timeSensitiveDomain;
    }
    
    public void setTimeSensitiveDomain(final TimeSensitiveDomain timeSensitiveDomain) {
        this.timeSensitiveDomain = timeSensitiveDomain;
    }
}
