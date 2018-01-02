// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.scheduler;

public interface IScheduler
{
    void init() throws SchedulerException;
    
    void start() throws SchedulerException;
    
    void shutdown() throws SchedulerException;
    
    void scheduleJob(final IScheduledJob p0) throws SchedulerException;
    
    void removeJob(final IScheduledJob p0) throws SchedulerException;
    
    boolean hasJob(final IScheduledJob p0) throws SchedulerException;
}
