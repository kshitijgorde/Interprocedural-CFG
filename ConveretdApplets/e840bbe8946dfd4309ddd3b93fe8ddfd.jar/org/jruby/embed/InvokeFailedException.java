// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

public class InvokeFailedException extends RuntimeException
{
    public InvokeFailedException(final Throwable cause) {
        super(cause);
    }
    
    public InvokeFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
