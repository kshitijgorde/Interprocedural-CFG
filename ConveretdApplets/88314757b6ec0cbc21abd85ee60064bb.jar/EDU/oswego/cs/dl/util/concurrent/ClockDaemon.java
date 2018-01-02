// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Date;

public class ClockDaemon extends ThreadFactoryUser
{
    protected final Heap heap_;
    protected Thread thread_;
    protected final RunLoop runLoop_;
    
    public ClockDaemon() {
        this.heap_ = new Heap(DefaultChannelCapacity.get());
        this.runLoop_ = new RunLoop();
    }
    
    public static void cancel(final Object o) {
        ((TaskNode)o).setCancelled();
    }
    
    protected synchronized void clearThread() {
        this.thread_ = null;
    }
    
    public Object executeAfterDelay(final long n, final Runnable runnable) {
        final TaskNode taskNode = new TaskNode(System.currentTimeMillis() + n, runnable);
        this.heap_.insert(taskNode);
        this.restart();
        return taskNode;
    }
    
    public Object executeAt(final Date date, final Runnable runnable) {
        final TaskNode taskNode = new TaskNode(date.getTime(), runnable);
        this.heap_.insert(taskNode);
        this.restart();
        return taskNode;
    }
    
    public Object executePeriodically(final long n, final Runnable runnable, final boolean b) {
        if (n <= 0L) {
            throw new IllegalArgumentException();
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!b) {
            currentTimeMillis += n;
        }
        final TaskNode taskNode = new TaskNode(currentTimeMillis, runnable, n);
        this.heap_.insert(taskNode);
        this.restart();
        return taskNode;
    }
    
    public synchronized Thread getThread() {
        return this.thread_;
    }
    
    protected synchronized TaskNode nextTask() {
        try {
            while (!Thread.interrupted()) {
                final TaskNode taskNode = (TaskNode)this.heap_.peek();
                if (taskNode == null) {
                    this.wait();
                }
                else {
                    final long currentTimeMillis = System.currentTimeMillis();
                    final long timeToRun = taskNode.getTimeToRun();
                    if (timeToRun > currentTimeMillis) {
                        this.wait(timeToRun - currentTimeMillis);
                    }
                    else {
                        final TaskNode taskNode2 = (TaskNode)this.heap_.extract();
                        if (!taskNode2.getCancelled()) {
                            if (taskNode2.period > 0L) {
                                taskNode2.setTimeToRun(currentTimeMillis + taskNode2.period);
                                this.heap_.insert(taskNode2);
                            }
                            return taskNode2;
                        }
                        continue;
                    }
                }
            }
        }
        catch (InterruptedException ex) {}
        return null;
    }
    
    public synchronized void restart() {
        if (this.thread_ == null) {
            (this.thread_ = super.threadFactory_.newThread(this.runLoop_)).start();
        }
        else {
            this.notify();
        }
    }
    
    public synchronized void shutDown() {
        this.heap_.clear();
        if (this.thread_ != null) {
            this.thread_.interrupt();
        }
        this.thread_ = null;
    }
    
    protected static class TaskNode implements Comparable
    {
        final Runnable command;
        final long period;
        private long timeToRun_;
        private boolean cancelled_;
        
        TaskNode(final long n, final Runnable runnable) {
            this(n, runnable, -1L);
        }
        
        TaskNode(final long timeToRun_, final Runnable command, final long period) {
            this.cancelled_ = false;
            this.timeToRun_ = timeToRun_;
            this.command = command;
            this.period = period;
        }
        
        public int compareTo(final Object o) {
            final long timeToRun = this.getTimeToRun();
            final long timeToRun2 = ((TaskNode)o).getTimeToRun();
            return (timeToRun < timeToRun2) ? -1 : ((timeToRun == timeToRun2) ? false : true);
        }
        
        synchronized boolean getCancelled() {
            return this.cancelled_;
        }
        
        synchronized long getTimeToRun() {
            return this.timeToRun_;
        }
        
        synchronized void setCancelled() {
            this.cancelled_ = true;
        }
        
        synchronized void setTimeToRun(final long timeToRun_) {
            this.timeToRun_ = timeToRun_;
        }
    }
    
    protected class RunLoop implements Runnable
    {
        public void run() {
            try {
                while (true) {
                    final TaskNode nextTask = ClockDaemon.this.nextTask();
                    if (nextTask == null) {
                        break;
                    }
                    nextTask.command.run();
                }
            }
            finally {
                ClockDaemon.this.clearThread();
            }
        }
    }
}
