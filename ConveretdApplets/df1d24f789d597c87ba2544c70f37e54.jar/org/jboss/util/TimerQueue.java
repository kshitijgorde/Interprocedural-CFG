// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public class TimerQueue extends WorkerQueue
{
    private Heap m_heap;
    
    public TimerQueue() {
        this("TimerTask Thread");
    }
    
    public TimerQueue(final String threadName) {
        super(threadName);
        this.m_heap = new Heap();
    }
    
    public void schedule(final TimerTask t) {
        this.schedule(t, 0L);
    }
    
    public void schedule(final TimerTask t, long delay) {
        if (t == null) {
            throw new IllegalArgumentException("Can't schedule a null TimerTask");
        }
        if (delay < 0L) {
            delay = 0L;
        }
        t.setNextExecutionTime(System.currentTimeMillis() + delay);
        this.putJob(t);
    }
    
    protected void putJobImpl(final Executable task) {
        this.m_heap.insert(task);
        ((TimerTask)task).setState(2);
        this.notifyAll();
    }
    
    protected Executable getJobImpl() throws InterruptedException {
        while (this.m_heap.peek() == null) {
            this.wait();
        }
        TimerTask task = (TimerTask)this.m_heap.extract();
        switch (task.getState()) {
            case 3:
            case 4: {
                task = null;
                return this.getJobImpl();
            }
            case 1:
            case 2: {
                return task;
            }
            default: {
                throw new IllegalStateException("TimerTask has an illegal state");
            }
        }
    }
    
    protected Runnable createQueueLoop() {
        return new TimerTaskLoop();
    }
    
    protected void clear() {
        super.clear();
        synchronized (this) {
            this.m_heap.clear();
        }
    }
    
    protected class TimerTaskLoop implements Runnable
    {
        public void run() {
            try {
                while (true) {
                    final TimerTask task = (TimerTask)TimerQueue.this.getJob();
                    final long now = System.currentTimeMillis();
                    final long executionTime = task.getNextExecutionTime();
                    final long timeToWait = executionTime - now;
                    final boolean runTask = timeToWait <= 0L;
                    if (!runTask) {
                        TimerQueue.this.putJob(task);
                        final Object mutex = TimerQueue.this;
                        synchronized (mutex) {
                            mutex.wait(timeToWait);
                        }
                    }
                    else {
                        if (task.isPeriodic()) {
                            task.setNextExecutionTime(executionTime + task.getPeriod());
                            TimerQueue.this.putJob(task);
                        }
                        else {
                            task.setState(3);
                        }
                        task.execute();
                    }
                }
            }
            catch (InterruptedException x) {
                TimerQueue.this.clear();
            }
            catch (Exception ex) {}
        }
    }
}
