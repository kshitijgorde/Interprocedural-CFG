// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax;

public class SAXException extends Exception
{
    private String message;
    private Exception exception;
    
    public SAXException(final String message) {
        this.message = message;
        this.exception = null;
    }
    
    public SAXException(final Exception exception) {
        this.message = null;
        this.exception = exception;
    }
    
    public SAXException(final String message, final Exception exception) {
        this.message = message;
        this.exception = exception;
    }
    
    public String getMessage() {
        if (this.message == null && this.exception != null) {
            return this.exception.getMessage();
        }
        return this.message;
    }
    
    public Exception getException() {
        return this.exception;
    }
    
    public String toString() {
        return this.getMessage();
    }
}
