// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

public class ParseFailedException extends RuntimeException
{
    public ParseFailedException(final Throwable cause) {
        super(cause);
    }
    
    public ParseFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
