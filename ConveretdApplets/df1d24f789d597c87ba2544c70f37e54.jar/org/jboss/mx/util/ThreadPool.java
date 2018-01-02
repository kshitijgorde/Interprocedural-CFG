// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.Stack;

public class ThreadPool
{
    private static int counter;
    private Stack pool;
    private int maxSize;
    private boolean active;
    private boolean daemon;
    
    public ThreadPool() {
        this.pool = new Stack();
        this.maxSize = 10;
        this.active = false;
        this.daemon = true;
    }
    
    public ThreadPool(final boolean active) {
        this.pool = new Stack();
        this.maxSize = 10;
        this.active = false;
        this.daemon = true;
        this.active = active;
    }
    
    public void setMaximumSize(final int size) {
        this.maxSize = size;
    }
    
    public int getMaximumSize() {
        return this.maxSize;
    }
    
    public void setActive(final boolean status) {
        if (!(this.active = status)) {
            while (this.pool.size() > 0) {
                this.pool.pop().die();
            }
        }
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void setDaemonThreads(final boolean value) {
        this.daemon = value;
    }
    
    public boolean getDaemonThreads() {
        return this.daemon;
    }
    
    public synchronized void run(final Runnable work) {
        if (this.pool.size() == 0) {
            new Worker(work);
        }
        else {
            final Worker worker = this.pool.pop();
            worker.run(work);
        }
    }
    
    private synchronized void returnWorker(final Worker worker) {
        if (this.pool.size() < this.maxSize) {
            this.pool.push(worker);
        }
        else {
            worker.die();
        }
    }
    
    static {
        ThreadPool.counter = 0;
    }
    
    class Worker extends Thread
    {
        boolean running;
        Runnable work;
        
        Worker(final Runnable work) {
            super("ThreadPoolWorker[" + ++ThreadPool.counter + "]");
            this.running = true;
            this.work = work;
            this.setDaemon(ThreadPool.this.daemon);
            this.start();
        }
        
        public synchronized void die() {
            this.running = false;
            this.notify();
        }
        
        public synchronized void run(final Runnable work) {
            if (this.work != null) {
                throw new IllegalStateException("Worker already has work to do.");
            }
            this.work = work;
            this.notify();
        }
        
        public void run() {
            while (ThreadPool.this.active && this.running) {
                if (this.work != null) {
                    try {
                        this.work.run();
                    }
                    catch (Exception ex) {}
                    this.work = null;
                }
                ThreadPool.this.returnWorker(this);
                synchronized (this) {
                    while (this.running && this.work == null) {
                        try {
                            this.wait();
                        }
                        catch (InterruptedException ignored) {}
                    }
                }
            }
        }
    }
}
