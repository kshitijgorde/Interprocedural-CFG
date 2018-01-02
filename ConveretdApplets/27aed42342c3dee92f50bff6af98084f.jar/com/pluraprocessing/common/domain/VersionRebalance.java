// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.util.Date;

public class VersionRebalance
{
    private String id;
    private String ownerId;
    private float desiredPercentage;
    private int currentNumberOfRequests;
    private String parentId;
    private int numberAvailableWUs;
    private String reloadVersionString;
    private Date earliestPriorityDate;
    private int priority;
    private long sleepTimeMS;
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public float getDesiredPercentage() {
        return this.desiredPercentage;
    }
    
    public void setDesiredPercentage(final float desiredPercentage) {
        this.desiredPercentage = desiredPercentage;
    }
    
    public int getCurrentNumberOfRequests() {
        return this.currentNumberOfRequests;
    }
    
    public void setCurrentNumberOfRequests(final int currentNumberOfRequests) {
        this.currentNumberOfRequests = currentNumberOfRequests;
    }
    
    public String getOwnerId() {
        return this.ownerId;
    }
    
    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }
    
    public String getParentId() {
        return this.parentId;
    }
    
    public void setParentId(final String parentId) {
        this.parentId = parentId;
    }
    
    public int getNumberAvailableWUs() {
        return this.numberAvailableWUs;
    }
    
    public void setNumberAvailableWUs(final int numberAvailableWUs) {
        this.numberAvailableWUs = numberAvailableWUs;
    }
    
    public String getReloadVersionString() {
        return this.reloadVersionString;
    }
    
    public void setReloadVersionString(final String reloadVersionString) {
        this.reloadVersionString = reloadVersionString;
    }
    
    public Date getEarliestPriorityDate() {
        return this.earliestPriorityDate;
    }
    
    public void setEarliestPriorityDate(final Date earliestPriorityDate) {
        this.earliestPriorityDate = earliestPriorityDate;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(final int priority) {
        this.priority = priority;
    }
    
    public long getSleepTimeMS() {
        return this.sleepTimeMS;
    }
    
    public void setSleepTimeMS(final long sleepTimeMS) {
        this.sleepTimeMS = sleepTimeMS;
    }
}
