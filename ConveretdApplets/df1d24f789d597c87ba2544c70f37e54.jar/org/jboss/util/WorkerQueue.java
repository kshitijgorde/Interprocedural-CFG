// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public class WorkerQueue
{
    protected Thread m_queueThread;
    private JobItem m_currentJob;
    
    public WorkerQueue() {
        this("Worker Thread");
    }
    
    public WorkerQueue(final String threadName) {
        this.m_queueThread = new Thread(this.createQueueLoop(), threadName);
    }
    
    public WorkerQueue(final String threadName, final boolean isDaemon) {
        (this.m_queueThread = new Thread(this.createQueueLoop(), threadName)).setDaemon(isDaemon);
    }
    
    public void start() {
        if (this.m_queueThread != null) {
            this.m_queueThread.start();
        }
    }
    
    public synchronized void stop() {
        if (this.m_queueThread != null) {
            this.m_queueThread.interrupt();
        }
    }
    
    public synchronized void putJob(final Executable job) {
        if (this.m_queueThread == null || !this.m_queueThread.isAlive()) {
            throw new IllegalStateException("Can't put job, thread is not alive or not present");
        }
        if (this.isInterrupted()) {
            throw new IllegalStateException("Can't put job, thread was interrupted");
        }
        this.putJobImpl(job);
    }
    
    protected boolean isInterrupted() {
        return this.m_queueThread.isInterrupted();
    }
    
    protected synchronized Executable getJob() throws InterruptedException {
        if (this.m_queueThread == null || !this.m_queueThread.isAlive()) {
            throw new IllegalStateException();
        }
        return this.getJobImpl();
    }
    
    protected Executable getJobImpl() throws InterruptedException {
        while (this.m_currentJob == null) {
            this.wait();
        }
        final JobItem item = this.m_currentJob;
        this.m_currentJob = this.m_currentJob.m_next;
        return item.m_job;
    }
    
    protected void putJobImpl(final Executable job) {
        final JobItem posted = new JobItem(job);
        if (this.m_currentJob == null) {
            this.m_currentJob = posted;
            this.notifyAll();
        }
        else {
            JobItem item;
            for (item = this.m_currentJob; item.m_next != null; item = item.m_next) {}
            item.m_next = posted;
        }
    }
    
    protected void clear() {
        this.m_queueThread = null;
        this.m_currentJob = null;
    }
    
    protected Runnable createQueueLoop() {
        return new QueueLoop();
    }
    
    protected class QueueLoop implements Runnable
    {
        public void run() {
            while (true) {
                try {
                    while (!WorkerQueue.this.isInterrupted()) {
                        WorkerQueue.this.getJob().execute();
                    }
                    this.flush();
                }
                catch (InterruptedException e2) {
                    try {
                        this.flush();
                    }
                    catch (Exception ex) {}
                }
                catch (Exception e) {
                    ThrowableHandler.add(1, e);
                    continue;
                }
                finally {
                    WorkerQueue.this.clear();
                }
                break;
            }
        }
        
        protected void flush() throws Exception {
            while (WorkerQueue.this.m_currentJob != null) {
                WorkerQueue.this.m_currentJob.m_job.execute();
                WorkerQueue.this.m_currentJob = WorkerQueue.this.m_currentJob.m_next;
            }
        }
    }
    
    private class JobItem
    {
        private Executable m_job;
        private JobItem m_next;
        
        private JobItem(final Executable job) {
            this.m_job = job;
        }
    }
}
