// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

public class InvocationException extends Exception
{
    private Throwable cause;
    
    public InvocationException(final Throwable cause) {
        this.cause = null;
        this.cause = cause;
    }
    
    public InvocationException(final String msg, final Throwable cause) {
        super(msg);
        this.cause = null;
        this.cause = cause;
    }
    
    public Throwable getTargetException() {
        return this.cause;
    }
}
