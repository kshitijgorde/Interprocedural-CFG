// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.scheduler.quartz;

import org.quartz.JobDataMap;
import org.xmodel.IModelObject;
import java.util.Map;
import com.stonewall.cornerstone.scheduler.IScheduledJob;
import org.quartz.JobExecutionException;
import org.quartz.JobExecutionContext;
import org.quartz.JobDetail;

public class Job implements org.quartz.Job
{
    private JobDetail detail;
    
    public Job() {
    }
    
    Job(final JobDetail detail) {
        this.detail = detail;
    }
    
    JobDetail getJobDetail() {
        return this.detail;
    }
    
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        this.detail = context.getJobDetail();
        try {
            final IScheduledJob job = this.createJob();
            job.run();
        }
        catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
    
    private IScheduledJob createJob() throws Exception {
        final JobDataMap data = this.detail.getJobDataMap();
        final String className = data.getString("jobClass");
        final Class clazz = Class.forName(className);
        final IScheduledJob sJob = clazz.newInstance();
        sJob.setData((Map<String, IModelObject>)this.detail.getJobDataMap());
        return sJob;
    }
}
