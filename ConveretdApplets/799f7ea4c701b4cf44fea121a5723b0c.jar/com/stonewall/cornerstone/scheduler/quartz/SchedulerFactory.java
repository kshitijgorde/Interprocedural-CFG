// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.scheduler.quartz;

import com.stonewall.cornerstone.jms.JndiProvider;
import org.quartz.impl.StdSchedulerFactory;
import javax.naming.NamingException;
import com.stonewall.cornerstone.scheduler.SchedulerException;
import com.stonewall.cornerstone.jms.ProviderFactory;
import java.io.File;
import com.stonewall.cornerstone.scheduler.IScheduler;

public class SchedulerFactory
{
    public static IScheduler getScheduler() throws SchedulerException {
        try {
            final String file = String.valueOf(System.getProperty("cornerstone.home")) + File.separator + "etc" + File.separator + "quartz.properties";
            System.setProperty("org.quartz.properties", file);
            final QuartzDataSource ds = new QuartzDataSource();
            final JndiProvider provider = ProviderFactory.getJndiProvider();
            try {
                provider.getInitialContext().bind(ds.getName(), ds);
            }
            catch (NamingException e) {
                throw new SchedulerException(e);
            }
            final StdSchedulerFactory factory = new StdSchedulerFactory();
            return new Scheduler(factory.getScheduler());
        }
        catch (org.quartz.SchedulerException se) {
            throw new SchedulerException((Throwable)se);
        }
    }
}
