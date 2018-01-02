// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import com.fluendo.utils.Debug;

public class SystemClock extends Clock
{
    protected long getInternalTime() {
        return System.currentTimeMillis() * 1000L;
    }
    
    protected WaitStatus waitFunc(final ClockID clockID) {
        final WaitStatus waitStatus = new WaitStatus();
        final long internalTime = this.getInternalTime();
        final long time = clockID.time;
        final long adjust = this.adjust(internalTime);
        waitStatus.jitter = adjust - time;
        if (waitStatus.jitter < 0L) {
            Debug.log(4, "Waiting from " + adjust + " until " + time + " (" + -waitStatus.jitter + "us)");
            final long n = -waitStatus.jitter / 1000L;
            final int n2 = (int)(-waitStatus.jitter % 1000L * 1000L);
            synchronized (this) {
                if (clockID.status == 2) {
                    waitStatus.status = 2;
                    return waitStatus;
                }
                clockID.status = 0;
                try {
                    this.wait(n, n2);
                }
                catch (InterruptedException ex) {}
            }
            waitStatus.status = clockID.status;
        }
        else if (waitStatus.jitter == 0L) {
            waitStatus.status = 0;
        }
        else {
            Debug.log(4, "Wait for timestamp " + adjust + " is late by " + waitStatus.jitter + "us");
            waitStatus.status = 1;
        }
        return waitStatus;
    }
    
    protected WaitStatus waitAsyncFunc(final ClockID clockID) {
        return WaitStatus.newOK();
    }
    
    protected void unscheduleFunc(final ClockID clockID) {
        synchronized (this) {
            clockID.status = 2;
            this.notifyAll();
        }
    }
}
