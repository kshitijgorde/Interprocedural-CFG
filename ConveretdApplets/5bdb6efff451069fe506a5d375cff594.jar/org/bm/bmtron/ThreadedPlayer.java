// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.Color;

public abstract class ThreadedPlayer extends Player implements Runnable
{
    private Thread thread;
    private Object monitor;
    
    ThreadedPlayer(final Field field, final String s, final Color color) {
        super(field, s, color);
        this.thread = null;
        this.monitor = new Object();
    }
    
    synchronized void start() {
        (this.thread = new Thread(this)).start();
        super.start();
    }
    
    synchronized void stop() {
        this.thread = null;
        this.update();
        super.stop();
    }
    
    void update() {
        synchronized (this.monitor) {
            this.monitor.notifyAll();
        }
    }
    
    public void run() {
        while (Thread.currentThread() == this.thread) {
            this.move();
            try {
                synchronized (this.monitor) {
                    this.monitor.wait();
                }
            }
            catch (InterruptedException ex) {}
        }
    }
    
    protected abstract void move();
}
