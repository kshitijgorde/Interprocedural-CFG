// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.thread;

import java.util.Map;
import org.apache.activemq.util.MDCHelper;

class DedicatedTaskRunner implements TaskRunner
{
    private final Task task;
    private final Thread thread;
    private final Object mutex;
    private boolean threadTerminated;
    private boolean pending;
    private boolean shutdown;
    
    public DedicatedTaskRunner(final Task task, final String name, final int priority, final boolean daemon) {
        this.mutex = new Object();
        this.task = task;
        final Map context = MDCHelper.getCopyOfContextMap();
        (this.thread = new Thread(name) {
            @Override
            public void run() {
                MDCHelper.setContextMap(context);
                DedicatedTaskRunner.this.runTask();
            }
        }).setDaemon(daemon);
        this.thread.setName(name);
        this.thread.setPriority(priority);
        this.thread.start();
    }
    
    @Override
    public void wakeup() throws InterruptedException {
        synchronized (this.mutex) {
            if (this.shutdown) {
                return;
            }
            this.pending = true;
            this.mutex.notifyAll();
        }
    }
    
    @Override
    public void shutdown(final long timeout) throws InterruptedException {
        synchronized (this.mutex) {
            this.shutdown = true;
            this.pending = true;
            this.mutex.notifyAll();
            if (Thread.currentThread() != this.thread && !this.threadTerminated) {
                this.mutex.wait(timeout);
            }
        }
    }
    
    @Override
    public void shutdown() throws InterruptedException {
        this.shutdown(0L);
    }
    
    final void runTask() {
        try {
            while (true) {
                synchronized (this.mutex) {
                    this.pending = false;
                    if (this.shutdown) {
                        return;
                    }
                }
                if (!this.task.iterate()) {
                    synchronized (this.mutex) {
                        if (this.shutdown) {
                            return;
                        }
                        while (!this.pending) {
                            this.mutex.wait();
                        }
                    }
                }
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            synchronized (this.mutex) {
                this.threadTerminated = true;
                this.mutex.notifyAll();
            }
        }
        finally {
            synchronized (this.mutex) {
                this.threadTerminated = true;
                this.mutex.notifyAll();
            }
        }
    }
}
