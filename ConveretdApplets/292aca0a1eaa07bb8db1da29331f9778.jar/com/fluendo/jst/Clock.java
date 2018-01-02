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
    public static final int OK = 0;
    public static final int EARLY = 1;
    public static final int UNSCHEDULED = 2;
    public static final int BUSY = 3;
    public static final int BADTIME = 4;
    public static final int ERROR = 5;
    public static final int UNSUPPORTED = 6;
    
    public Clock() {
        this.adjust = 0L;
        this.lastTime = 0L;
    }
    
    protected synchronized long adjust(final long internal) {
        long ret = internal + this.adjust;
        if (ret < this.lastTime) {
            ret = this.lastTime;
        }
        else {
            this.lastTime = ret;
        }
        return ret;
    }
    
    protected abstract long getInternalTime();
    
    protected abstract int waitFunc(final ClockID p0);
    
    protected abstract int waitAsyncFunc(final ClockID p0);
    
    protected abstract void unscheduleFunc(final ClockID p0);
    
    public synchronized long getTime() {
        final long internal = this.getInternalTime();
        final long ret = this.adjust(internal);
        return ret;
    }
    
    public synchronized void setAdjust(final long newAdjust) {
        this.adjust = newAdjust;
    }
    
    public synchronized long getAdjust() {
        return this.adjust;
    }
    
    public ClockID newSingleShotID(final long time) {
        return new ClockID(time, 0L, 0);
    }
    
    public ClockID newPeriodicID(final long time, final long interval) {
        return new ClockID(time, interval, 0);
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
        
        public int waitID() {
            final int res = Clock.this.waitFunc(this);
            if (this.type == 0) {
                this.time += this.interval;
            }
            return res;
        }
        
        public void unschedule() {
            Clock.this.unscheduleFunc(this);
        }
    }
}
