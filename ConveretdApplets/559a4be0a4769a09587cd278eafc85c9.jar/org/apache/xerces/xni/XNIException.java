// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni;

public class XNIException extends RuntimeException
{
    private Exception fException;
    
    public XNIException(final String message) {
        super(message);
    }
    
    public XNIException(final Exception exception) {
        super(exception.getMessage());
        this.fException = exception;
    }
    
    public XNIException(final String message, final Exception exception) {
        super(message);
        this.fException = exception;
    }
    
    public Exception getException() {
        return this.fException;
    }
}
