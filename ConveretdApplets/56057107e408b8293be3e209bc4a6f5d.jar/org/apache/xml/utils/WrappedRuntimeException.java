// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class WrappedRuntimeException extends RuntimeException
{
    private Exception m_exception;
    
    public WrappedRuntimeException(final Exception e) {
        super(e.getMessage());
        this.m_exception = e;
    }
    
    public Exception getException() {
        return this.m_exception;
    }
}
