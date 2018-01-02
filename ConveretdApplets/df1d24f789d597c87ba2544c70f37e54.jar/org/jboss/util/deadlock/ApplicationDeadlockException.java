// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.deadlock;

public class ApplicationDeadlockException extends RuntimeException
{
    protected boolean retry;
    
    public ApplicationDeadlockException() {
        this.retry = false;
    }
    
    public ApplicationDeadlockException(final String msg, final boolean retry) {
        super(msg);
        this.retry = false;
        this.retry = retry;
    }
    
    public boolean retryable() {
        return this.retry;
    }
    
    public static ApplicationDeadlockException isADE(Throwable t) {
        while (t != null) {
            if (t instanceof ApplicationDeadlockException) {
                return (ApplicationDeadlockException)t;
            }
            t = t.getCause();
        }
        return null;
    }
}
