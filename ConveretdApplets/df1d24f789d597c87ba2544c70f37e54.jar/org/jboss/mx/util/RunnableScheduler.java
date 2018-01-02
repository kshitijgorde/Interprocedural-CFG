// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.Iterator;
import java.util.TreeSet;

public class RunnableScheduler implements Runnable
{
    private TreeSet runnables;
    private ThreadPool threadPool;
    private Thread controller;
    
    public RunnableScheduler() {
        this.runnables = new TreeSet();
        this.controller = null;
    }
    
    public synchronized void start() {
        if (this.controller != null) {
            return;
        }
        (this.controller = new Thread(this)).setDaemon(true);
        this.controller.start();
    }
    
    public synchronized void stop() {
        if (this.controller == null) {
            return;
        }
        this.controller.interrupt();
        this.controller = null;
    }
    
    public void run() {
        (this.threadPool = new ThreadPool()).setActive(true);
        try {
            while (true) {
                this.runOutstanding();
                this.waitOutstanding();
            }
        }
        catch (InterruptedException weAreDone) {}
        finally {
            this.threadPool.setActive(false);
            this.threadPool = null;
        }
    }
    
    synchronized void add(final SchedulableRunnable runnable) {
        this.runnables.add(runnable);
        this.notifyAll();
    }
    
    synchronized void remove(final SchedulableRunnable runnable) {
        this.runnables.remove(runnable);
    }
    
    synchronized boolean contains(final SchedulableRunnable runnable) {
        return this.runnables.contains(runnable);
    }
    
    private synchronized void runOutstanding() {
        final long current = System.currentTimeMillis();
        final Iterator iterator = this.runnables.iterator();
        while (iterator.hasNext()) {
            final SchedulableRunnable next = iterator.next();
            if (next.getNextRun() > current) {
                break;
            }
            iterator.remove();
            this.threadPool.run(next);
        }
    }
    
    private synchronized void waitOutstanding() throws InterruptedException {
        if (this.runnables.size() == 0) {
            this.wait();
        }
        else {
            final SchedulableRunnable next = this.runnables.first();
            final long current = System.currentTimeMillis();
            final long wait = next.getNextRun() - current;
            if (wait > 0L) {
                this.wait(wait);
            }
        }
    }
}
