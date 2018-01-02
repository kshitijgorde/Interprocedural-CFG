// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.usage;

public class MemoryUsage extends Usage<MemoryUsage>
{
    private long usage;
    
    public MemoryUsage() {
        this(null, null);
    }
    
    public MemoryUsage(final MemoryUsage parent) {
        this(parent, "default");
    }
    
    public MemoryUsage(final String name) {
        this(null, name);
    }
    
    public MemoryUsage(final MemoryUsage parent, final String name) {
        this(parent, name, 1.0f);
    }
    
    public MemoryUsage(final MemoryUsage parent, final String name, final float portion) {
        super(parent, name, portion);
    }
    
    @Override
    public void waitForSpace() throws InterruptedException {
        if (this.parent != null) {
            ((MemoryUsage)this.parent).waitForSpace();
        }
        synchronized (this.usageMutex) {
            int i = 0;
            while (this.percentUsage >= 100) {
                this.usageMutex.wait();
                ++i;
            }
        }
    }
    
    @Override
    public boolean waitForSpace(final long timeout) throws InterruptedException {
        if (this.parent != null && !((MemoryUsage)this.parent).waitForSpace(timeout)) {
            return false;
        }
        synchronized (this.usageMutex) {
            if (this.percentUsage >= 100) {
                this.usageMutex.wait(timeout);
            }
            return this.percentUsage < 100;
        }
    }
    
    @Override
    public boolean isFull() {
        if (this.parent != null && ((MemoryUsage)this.parent).isFull()) {
            return true;
        }
        synchronized (this.usageMutex) {
            return this.percentUsage >= 100;
        }
    }
    
    public void enqueueUsage(final long value) throws InterruptedException {
        this.waitForSpace();
        this.increaseUsage(value);
    }
    
    public void increaseUsage(final long value) {
        if (value == 0L) {
            return;
        }
        final int percentUsage;
        synchronized (this.usageMutex) {
            this.usage += value;
            percentUsage = this.caclPercentUsage();
        }
        this.setPercentUsage(percentUsage);
        if (this.parent != null) {
            ((MemoryUsage)this.parent).increaseUsage(value);
        }
    }
    
    public void decreaseUsage(final long value) {
        if (value == 0L) {
            return;
        }
        final int percentUsage;
        synchronized (this.usageMutex) {
            this.usage -= value;
            percentUsage = this.caclPercentUsage();
        }
        this.setPercentUsage(percentUsage);
        if (this.parent != null) {
            ((MemoryUsage)this.parent).decreaseUsage(value);
        }
    }
    
    @Override
    protected long retrieveUsage() {
        return this.usage;
    }
    
    @Override
    public long getUsage() {
        return this.usage;
    }
    
    public void setUsage(final long usage) {
        this.usage = usage;
    }
}
