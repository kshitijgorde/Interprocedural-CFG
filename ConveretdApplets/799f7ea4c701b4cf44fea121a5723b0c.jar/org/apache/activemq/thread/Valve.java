// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.thread;

public final class Valve
{
    private final Object mutex;
    private boolean on;
    private int turningOff;
    private int usage;
    
    public Valve(final boolean on) {
        this.mutex = new Object();
        this.on = on;
    }
    
    public void turnOn() throws InterruptedException {
        synchronized (this.mutex) {
            while (this.on) {
                this.mutex.wait();
            }
            this.on = true;
            this.mutex.notifyAll();
        }
    }
    
    public boolean isOn() {
        synchronized (this.mutex) {
            return this.on;
        }
    }
    
    public void turnOff() throws InterruptedException {
        synchronized (this.mutex) {
            if (this.turningOff < 0) {
                throw new IllegalStateException("Unbalanced turningOff: " + this.turningOff);
            }
            try {
                ++this.turningOff;
                while (this.usage > 0 || !this.on) {
                    this.mutex.wait();
                }
                this.on = false;
                this.mutex.notifyAll();
            }
            finally {
                --this.turningOff;
            }
        }
    }
    
    public void increment() throws InterruptedException {
        synchronized (this.mutex) {
            if (this.turningOff < 0) {
                throw new IllegalStateException("Unbalanced turningOff: " + this.turningOff);
            }
            if (this.usage < 0) {
                throw new IllegalStateException("Unbalanced usage: " + this.usage);
            }
            while (this.turningOff > 0 || !this.on) {
                this.mutex.wait();
            }
            ++this.usage;
        }
    }
    
    public void decrement() {
        synchronized (this.mutex) {
            --this.usage;
            if (this.turningOff < 0) {
                throw new IllegalStateException("Unbalanced turningOff: " + this.turningOff);
            }
            if (this.usage < 0) {
                throw new IllegalStateException("Unbalanced usage: " + this.usage);
            }
            if (this.turningOff > 0 && this.usage < 1) {
                this.mutex.notifyAll();
            }
        }
    }
}
