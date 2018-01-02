// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.scheduler;

public class SchedulerFactory
{
    public static IScheduler getScheduler() throws SchedulerException {
        return com.stonewall.cornerstone.scheduler.quartz.SchedulerFactory.getScheduler();
    }
}
