// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.scheduler.quartz;

import org.quartz.Trigger;
import org.quartz.CronTrigger;
import java.util.Map;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import com.stonewall.cornerstone.scheduler.IScheduledJob;
import com.stonewall.cornerstone.scheduler.SchedulerException;
import com.stonewall.cornerstone.scheduler.IScheduler;

public class Scheduler implements IScheduler
{
    private org.quartz.Scheduler scheduler;
    
    Scheduler(final org.quartz.Scheduler scheduler) {
        this.scheduler = scheduler;
    }
    
    @Override
    public void init() throws SchedulerException {
    }
    
    @Override
    public void shutdown() throws SchedulerException {
        try {
            this.scheduler.shutdown();
        }
        catch (org.quartz.SchedulerException e) {
            throw new SchedulerException((Throwable)e);
        }
    }
    
    @Override
    public void start() throws SchedulerException {
        try {
            this.scheduler.start();
        }
        catch (org.quartz.SchedulerException e) {
            throw new SchedulerException((Throwable)e);
        }
    }
    
    @Override
    public void scheduleJob(final IScheduledJob sjob) throws SchedulerException {
        try {
            final JobDetail detail = new JobDetail(sjob.getName(), "DEFAULT", (Class)Job.class);
            final JobDataMap map = new JobDataMap();
            map.putAll((Map)sjob.getData());
            map.put("jobClass", sjob.getClass().getName());
            detail.setJobDataMap(map);
            final CronTrigger trigger = new CronTrigger(sjob.getName(), "DEFAULT", "0/15 * * ? * *");
            this.scheduler.scheduleJob(detail, (Trigger)trigger);
        }
        catch (Exception e) {
            throw new SchedulerException(e);
        }
    }
    
    @Override
    public boolean hasJob(final IScheduledJob sjob) throws SchedulerException {
        try {
            final JobDetail detail = this.scheduler.getJobDetail(sjob.getName(), "DEFAULT");
            return detail != null;
        }
        catch (Exception e) {
            throw new SchedulerException(e);
        }
    }
    
    @Override
    public void removeJob(final IScheduledJob sjob) throws SchedulerException {
        try {
            this.scheduler.unscheduleJob(sjob.getName(), "DEFAULT");
            this.scheduler.deleteJob(sjob.getName(), "DEFAULT");
        }
        catch (Exception e) {
            throw new SchedulerException(e);
        }
    }
}
