// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

public class EvalFailedException extends RuntimeException
{
    public EvalFailedException(final Throwable cause) {
        super(cause);
    }
    
    public EvalFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
