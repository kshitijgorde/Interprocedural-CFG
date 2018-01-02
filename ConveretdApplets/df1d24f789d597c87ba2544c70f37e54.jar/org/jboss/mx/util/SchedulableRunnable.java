// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import EDU.oswego.cs.dl.util.concurrent.SynchronizedLong;

public abstract class SchedulableRunnable implements Comparable, Runnable
{
    private long id;
    private SynchronizedLong nextRun;
    private RunnableScheduler scheduler;
    private boolean running;
    private boolean reschedule;
    private static long nextId;
    
    public SchedulableRunnable() {
        this.nextRun = new SynchronizedLong(0L);
        this.id = getNextId();
    }
    
    public long getNextRun() {
        return this.nextRun.get();
    }
    
    public synchronized void setNextRun(final long nextRun) {
        if (this.scheduler != null) {
            this.scheduler.remove(this);
        }
        this.nextRun.set(nextRun);
        if (!this.running && this.scheduler != null) {
            this.scheduler.add(this);
        }
        else {
            this.reschedule = true;
        }
    }
    
    public synchronized RunnableScheduler setScheduler(final RunnableScheduler scheduler) {
        if (this.scheduler == scheduler) {
            return this.scheduler;
        }
        final RunnableScheduler result = this.scheduler;
        if (this.scheduler != null) {
            this.scheduler.remove(this);
        }
        if ((this.scheduler = scheduler) == null) {
            this.reschedule = false;
        }
        else if (!this.running) {
            scheduler.add(this);
        }
        else {
            this.reschedule = true;
        }
        return result;
    }
    
    public abstract void doRun();
    
    public final void run() {
        this.startRun();
        try {
            this.doRun();
        }
        finally {
            this.endRun();
        }
    }
    
    public int compareTo(final Object o) {
        final SchedulableRunnable other = (SchedulableRunnable)o;
        long temp = this.nextRun.get() - other.nextRun.get();
        if (temp < 0L) {
            return -1;
        }
        if (temp > 0L) {
            return 1;
        }
        temp = this.id - other.id;
        if (temp < 0L) {
            return -1;
        }
        if (temp > 0L) {
            return 1;
        }
        return 0;
    }
    
    public boolean equals(final Object obj) {
        return this.compareTo(obj) == 0;
    }
    
    private synchronized void startRun() {
        this.running = true;
    }
    
    private synchronized void endRun() {
        this.running = false;
        if (this.reschedule) {
            this.scheduler.add(this);
        }
        this.reschedule = false;
    }
    
    private static synchronized long getNextId() {
        return SchedulableRunnable.nextId++;
    }
    
    static {
        SchedulableRunnable.nextId = 0L;
    }
}
