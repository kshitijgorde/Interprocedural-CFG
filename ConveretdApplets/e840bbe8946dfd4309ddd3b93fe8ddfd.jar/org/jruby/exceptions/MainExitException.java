// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.exceptions;

public class MainExitException extends RuntimeException implements Unrescuable
{
    private static final long serialVersionUID = -8585821821150293755L;
    boolean usageError;
    int status;
    private boolean aborted;
    
    public MainExitException(final int status, final String message) {
        super(message);
        this.status = status;
    }
    
    public MainExitException(final int status, final boolean aborted) {
        super("aborted");
        this.status = status;
        this.aborted = aborted;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setAborted(final boolean aborted) {
        this.aborted = aborted;
    }
    
    public boolean isAborted() {
        return this.aborted;
    }
    
    public boolean isUsageError() {
        return this.usageError;
    }
    
    public void setUsageError(final boolean usageError) {
        this.usageError = usageError;
    }
    
    public Throwable fillInStackTrace() {
        return this;
    }
}
