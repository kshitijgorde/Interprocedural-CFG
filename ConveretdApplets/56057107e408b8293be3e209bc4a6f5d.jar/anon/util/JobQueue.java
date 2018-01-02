// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import logging.LogHolder;
import logging.LogType;
import java.util.Vector;

public class JobQueue
{
    private Vector m_jobs;
    private Vector m_jobThreads;
    private Thread m_threadQueue;
    private boolean m_bInterrupted;
    private Job m_currentJob;
    private Thread m_currentJobThread;
    
    public JobQueue(final String s) {
        this.m_bInterrupted = false;
        this.m_jobs = new Vector();
        this.m_jobThreads = new Vector();
        (this.m_threadQueue = new Thread(new Runnable() {
            public void run() {
                synchronized (Thread.currentThread()) {
                    while (!Thread.currentThread().isInterrupted()) {
                        if (JobQueue.this.m_bInterrupted) {
                            break;
                        }
                        try {
                            Thread.currentThread().wait();
                        }
                        catch (InterruptedException ex2) {}
                        if (Thread.currentThread().isInterrupted()) {
                            Thread.currentThread().notifyAll();
                            break;
                        }
                        if (JobQueue.this.m_jobs.size() > 0 && JobQueue.this.m_currentJob == JobQueue.this.m_jobs.firstElement() && JobQueue.this.m_currentJobThread.isAlive()) {
                            if (!JobQueue.this.m_jobs.lastElement().isInterrupting()) {
                                continue;
                            }
                            while (JobQueue.this.m_currentJobThread.isAlive()) {
                                JobQueue.this.m_currentJobThread.interrupt();
                                try {
                                    Thread.currentThread().wait(100L);
                                }
                                catch (InterruptedException ex3) {
                                    break;
                                }
                            }
                        }
                        if (JobQueue.this.m_jobs.size() <= 0) {
                            continue;
                        }
                        while (JobQueue.this.m_jobs.size() > 1) {
                            JobQueue.this.m_jobs.removeElementAt(0);
                            JobQueue.this.m_jobThreads.removeElementAt(0);
                        }
                        JobQueue.this.m_currentJob = JobQueue.this.m_jobs.elementAt(0);
                        JobQueue.this.m_currentJobThread = JobQueue.this.m_jobThreads.elementAt(0);
                        try {
                            JobQueue.this.m_currentJobThread.start();
                        }
                        catch (IllegalThreadStateException ex) {
                            LogHolder.log(3, LogType.THREAD, ex);
                        }
                    }
                    while (JobQueue.this.m_jobs.size() > 0) {
                        if (JobQueue.this.m_currentJob == JobQueue.this.m_jobs.firstElement()) {
                            JobQueue.this.m_currentJobThread.interrupt();
                            try {
                                Thread.currentThread().wait(500L);
                            }
                            catch (InterruptedException ex4) {}
                        }
                        else {
                            JobQueue.this.m_jobs.removeAllElements();
                            JobQueue.this.m_jobThreads.removeAllElements();
                        }
                    }
                }
            }
        }, s)).setDaemon(true);
        this.m_threadQueue.start();
    }
    
    public void addJob(final Job job) {
        if (job == null) {
            return;
        }
        if (!job.isSkippedIfDuplicate() && this.m_bInterrupted) {
            return;
        }
        if (job.m_queue != null) {
            return;
        }
        synchronized (this.m_threadQueue) {
            if (job.isSkippedIfDuplicate() && this.m_jobs.contains(job)) {
                return;
            }
            if (this.m_jobs.size() > 0) {
                if (this.m_jobs.lastElement().isSkippedIfDuplicate() && job.isSkippedIfDuplicate()) {
                    return;
                }
                while (this.m_jobs.size() > 0) {
                    final Job job2 = this.m_jobs.lastElement();
                    if (job2 == this.m_currentJob && this.m_currentJobThread.isAlive()) {
                        break;
                    }
                    this.removeJob(job2, false);
                }
            }
            final Thread thread = new Thread(job);
            thread.setDaemon(true);
            job.m_queue = this;
            this.m_jobs.addElement(job);
            this.m_jobThreads.addElement(thread);
            this.m_threadQueue.notify();
            final String addedJobLogMessage = job.getAddedJobLogMessage();
            if (addedJobLogMessage != null) {
                LogHolder.log(7, LogType.MISC, addedJobLogMessage);
            }
        }
    }
    
    public void stop() {
        while (this.m_threadQueue.isAlive()) {
            this.m_threadQueue.interrupt();
            synchronized (this.m_threadQueue) {
                this.m_bInterrupted = true;
                this.m_threadQueue.notifyAll();
                this.m_threadQueue.interrupt();
            }
            try {
                this.m_threadQueue.join(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void removeJob(final Job job, final boolean b) {
        if (job == null) {
            return;
        }
        synchronized (this.m_threadQueue) {
            final int index = this.m_jobs.indexOf(job);
            if (index >= 0) {
                ((Thread)this.m_jobThreads.elementAt(index)).interrupt();
                this.m_jobs.removeElementAt(index);
                this.m_jobThreads.removeElementAt(index);
                if (b) {
                    this.m_threadQueue.notify();
                }
            }
        }
    }
    
    public abstract static class Job implements Runnable
    {
        private boolean m_bMayBeSkippedIfDuplicate;
        private JobQueue m_queue;
        
        public Job(final boolean bMayBeSkippedIfDuplicate) {
            this.m_bMayBeSkippedIfDuplicate = bMayBeSkippedIfDuplicate;
        }
        
        public Job() {
            this(false);
        }
        
        public abstract void runJob();
        
        public final void run() {
            this.runJob();
            if (this.m_queue != null) {
                this.m_queue.removeJob(this, true);
            }
        }
        
        public String getAddedJobLogMessage() {
            return null;
        }
        
        public boolean isInterrupting() {
            return false;
        }
        
        public final boolean isSkippedIfDuplicate() {
            return this.m_bMayBeSkippedIfDuplicate;
        }
    }
}
