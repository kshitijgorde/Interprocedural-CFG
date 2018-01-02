// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

public abstract class Clock
{
    private long adjust;
    private long lastTime;
    public static final long USECOND = 1L;
    public static final long MSECOND = 1000L;
    public static final long SECOND = 1000000L;
    public static final int SINGLE = 0;
    public static final int PERIODIC = 0;
    
    public Clock() {
        this.adjust = 0L;
        this.lastTime = 0L;
    }
    
    protected synchronized long adjust(final long n) {
        long lastTime = n + this.adjust;
        if (lastTime < this.lastTime) {
            lastTime = this.lastTime;
        }
        else {
            this.lastTime = lastTime;
        }
        return lastTime;
    }
    
    protected abstract long getInternalTime();
    
    protected abstract WaitStatus waitFunc(final ClockID p0);
    
    protected abstract WaitStatus waitAsyncFunc(final ClockID p0);
    
    protected abstract void unscheduleFunc(final ClockID p0);
    
    public synchronized long getTime() {
        return this.adjust(this.getInternalTime());
    }
    
    public synchronized void setAdjust(final long adjust) {
        this.adjust = adjust;
    }
    
    public synchronized long getAdjust() {
        return this.adjust;
    }
    
    public ClockID newSingleShotID(final long n) {
        return new ClockID(n, 0L, 0);
    }
    
    public ClockID newPeriodicID(final long n, final long n2) {
        return new ClockID(n, n2, 0);
    }
    
    public class ClockID
    {
        long time;
        long interval;
        int type;
        int status;
        
        public ClockID(final long time, final long interval, final int type) {
            this.time = time;
            this.interval = interval;
            this.type = type;
        }
        
        public long getTime() {
            return this.time;
        }
        
        public WaitStatus waitID() {
            final WaitStatus waitFunc = Clock.this.waitFunc(this);
            if (this.type == 0) {
                this.time += this.interval;
            }
            return waitFunc;
        }
        
        public void unschedule() {
            Clock.this.unscheduleFunc(this);
        }
    }
}
