// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

public class WaitStatus
{
    int status;
    long jitter;
    public static final int OK = 0;
    public static final int LATE = 1;
    public static final int UNSCHEDULED = 2;
    public static final int BUSY = 3;
    public static final int BADTIME = 4;
    public static final int ERROR = 5;
    public static final int UNSUPPORTED = 6;
    
    WaitStatus(final int status, final long jitter) {
        this.status = status;
        this.jitter = jitter;
    }
    
    WaitStatus() {
        this.status = 5;
        this.jitter = 0L;
    }
    
    public static WaitStatus newOK() {
        return new WaitStatus(0, 0L);
    }
}
