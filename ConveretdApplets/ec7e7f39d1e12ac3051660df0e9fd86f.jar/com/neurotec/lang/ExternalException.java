// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

public class ExternalException extends NeurotecExceptionBase implements NThrowable
{
    private int externalError;
    
    public ExternalException() {
        this(-90, 0, null, null);
    }
    
    public ExternalException(final String message) {
        this(-90, 0, message, null);
    }
    
    public ExternalException(final int externalError, final String message) {
        this(-90, externalError, message, null);
    }
    
    public ExternalException(final String message, final Throwable cause) {
        this(-90, 0, message, cause);
    }
    
    public ExternalException(final int code, final int externalError, final String message, final Throwable cause) {
        this(code, externalError, message, null, cause);
    }
    
    protected ExternalException(final int code, final int externalError, final String message, final String callStack, final Throwable cause) {
        super(code, message, callStack, cause);
        this.externalError = externalError;
    }
    
    public final int getExternalError() {
        return this.externalError;
    }
    
    public String toString() {
        return this.getMessage();
    }
}
