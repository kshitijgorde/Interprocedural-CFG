// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

public class SystemClock extends Clock
{
    protected long getInternalTime() {
        return System.currentTimeMillis() * 1000L;
    }
    
    protected int waitFunc(final ClockID id) {
        final long real = this.getInternalTime();
        final long entryt = id.time;
        final long now = this.adjust(real);
        final long diff = entryt - now;
        int res;
        if (diff > 0L) {
            final long millis = diff / 1000L;
            final int nanos = (int)(diff % 1000L * 1000L);
            synchronized (this) {
                if (id.status == 2) {
                    return id.status;
                }
                id.status = 0;
                try {
                    this.wait(millis, nanos);
                }
                catch (InterruptedException ex) {}
            }
            res = id.status;
        }
        else {
            res = 1;
        }
        return res;
    }
    
    protected int waitAsyncFunc(final ClockID id) {
        return 0;
    }
    
    protected void unscheduleFunc(final ClockID id) {
        synchronized (this) {
            id.status = 2;
            this.notifyAll();
        }
    }
}
