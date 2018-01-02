// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common11;

public final class StoppableThread extends Thread
{
    private volatile boolean pleaseStop;
    
    public StoppableThread(final Runnable r) {
        super(r);
        this.pleaseStop = false;
    }
    
    public void gentleStop(final boolean interrupt, final long timeout) {
        if (!this.isAlive()) {
            return;
        }
        synchronized (this) {
            this.pleaseStop = true;
        }
        if (interrupt) {
            this.interrupt();
        }
        if (timeout >= 0L) {
            try {
                this.join(timeout);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        super.start();
    }
    
    public final synchronized boolean stopping() {
        return this.pleaseStop;
    }
}
