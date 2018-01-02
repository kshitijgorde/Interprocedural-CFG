// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

public final class NotActivatedException extends NeurotecExceptionBase implements NThrowable
{
    public NotActivatedException() {
        this(-200, null, null, null);
    }
    
    public NotActivatedException(final String message) {
        this(-200, message, null, null);
    }
    
    public NotActivatedException(final String message, final Throwable cause) {
        this(-200, message, null, cause);
    }
    
    protected NotActivatedException(final int code, final String message, final String callStack, final Throwable cause) {
        super(code, message, callStack, cause);
    }
}
