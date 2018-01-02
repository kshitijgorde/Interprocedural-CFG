// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

public class ParameterException extends NeurotecExceptionBase
{
    public ParameterException() {
        this(-100, null, null, null);
    }
    
    public ParameterException(final String message) {
        this(-100, message, null, null);
    }
    
    public ParameterException(final String message, final Throwable cause) {
        this(-100, message, null, cause);
    }
    
    protected ParameterException(final int code, final String message, final String callStack, final Throwable cause) {
        super(code, message, callStack, cause);
    }
}
