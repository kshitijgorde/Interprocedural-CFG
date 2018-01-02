// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.scheduler;

public class SchedulerException extends Exception
{
    static final long serialVersionUID = 1L;
    
    public SchedulerException() {
    }
    
    public SchedulerException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public SchedulerException(final String message) {
        super(message);
    }
    
    public SchedulerException(final Throwable cause) {
        super(cause);
    }
}
