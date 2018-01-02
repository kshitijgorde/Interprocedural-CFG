// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.thread;

import java.util.Map;
import org.apache.activemq.util.MDCHelper;
import java.util.concurrent.Executor;

class PooledTaskRunner implements TaskRunner
{
    private final int maxIterationsPerRun;
    private final Executor executor;
    private final Task task;
    private final Runnable runable;
    private boolean queued;
    private boolean shutdown;
    private boolean iterating;
    private volatile Thread runningThread;
    
    public PooledTaskRunner(final Executor executor, final Task task, final int maxIterationsPerRun) {
        this.executor = executor;
        this.maxIterationsPerRun = maxIterationsPerRun;
        this.task = task;
        final Map context = MDCHelper.getCopyOfContextMap();
        this.runable = new Runnable() {
            @Override
            public void run() {
                MDCHelper.setContextMap(context);
                PooledTaskRunner.this.runningThread = Thread.currentThread();
                try {
                    PooledTaskRunner.this.runTask();
                }
                finally {
                    PooledTaskRunner.this.runningThread = null;
                }
            }
        };
    }
    
    @Override
    public void wakeup() throws InterruptedException {
        synchronized (this.runable) {
            if (this.queued || this.shutdown) {
                return;
            }
            this.queued = true;
            if (!this.iterating) {
                this.executor.execute(this.runable);
            }
        }
    }
    
    @Override
    public void shutdown(final long timeout) throws InterruptedException {
        synchronized (this.runable) {
            this.shutdown = true;
            if (this.runningThread != Thread.currentThread() && this.iterating) {
                this.runable.wait(timeout);
            }
        }
    }
    
    @Override
    public void shutdown() throws InterruptedException {
        this.shutdown(0L);
    }
    
    final void runTask() {
        synchronized (this.runable) {
            this.queued = false;
            if (this.shutdown) {
                this.iterating = false;
                this.runable.notifyAll();
                return;
            }
            this.iterating = true;
        }
        boolean done = false;
        try {
            for (int i = 0; i < this.maxIterationsPerRun; ++i) {
                if (!this.task.iterate()) {
                    done = true;
                    break;
                }
            }
        }
        finally {
            synchronized (this.runable) {
                this.iterating = false;
                this.runable.notifyAll();
                if (this.shutdown) {
                    this.queued = false;
                    this.runable.notifyAll();
                    return;
                }
                if (!done) {
                    this.queued = true;
                }
                if (this.queued) {
                    this.executor.execute(this.runable);
                }
            }
        }
    }
}
