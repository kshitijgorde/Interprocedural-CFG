// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

public final class NeurotecException extends NeurotecExceptionBase
{
    protected NeurotecException(final int code) {
        this(code, null, null, null);
    }
    
    protected NeurotecException(final int code, final String message) {
        this(code, message, null, null);
    }
    
    protected NeurotecException(final int code, final String message, final Throwable cause) {
        this(code, message, null, cause);
    }
    
    NeurotecException(final int code, final String message, final String callStack, final Throwable cause) {
        super(code, message, callStack, cause);
    }
}
