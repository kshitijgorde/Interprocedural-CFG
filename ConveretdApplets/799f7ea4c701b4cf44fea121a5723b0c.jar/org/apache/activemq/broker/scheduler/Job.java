// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.scheduler;

public interface Job
{
    String getJobId();
    
    int getRepeat();
    
    long getStart();
    
    long getDelay();
    
    long getPeriod();
    
    String getCronEntry();
    
    byte[] getPayload();
    
    String getStartTime();
    
    String getNextExecutionTime();
}
