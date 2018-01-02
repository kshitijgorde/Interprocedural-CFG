// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

public final class ParameterReadOnlyException extends ParameterException
{
    public ParameterReadOnlyException() {
        this(-101, null, null, null);
    }
    
    public ParameterReadOnlyException(final String message) {
        this(-101, message, null, null);
    }
    
    public ParameterReadOnlyException(final String message, final Throwable cause) {
        this(-101, message, null, cause);
    }
    
    protected ParameterReadOnlyException(final int code, final String message, final String callStack, final Throwable cause) {
        super(code, message, callStack, cause);
    }
}
