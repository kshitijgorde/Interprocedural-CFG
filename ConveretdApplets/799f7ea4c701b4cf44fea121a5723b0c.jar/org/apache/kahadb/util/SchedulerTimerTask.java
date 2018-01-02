// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.util.TimerTask;

public class SchedulerTimerTask extends TimerTask
{
    private final Runnable task;
    
    public SchedulerTimerTask(final Runnable task) {
        this.task = task;
    }
    
    public void run() {
        this.task.run();
    }
}
