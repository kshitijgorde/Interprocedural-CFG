// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax;

public class SAXException extends Exception
{
    private Exception exception;
    
    public SAXException(final String message) {
        super(message);
        this.exception = null;
    }
    
    public SAXException(final Exception e) {
        this.exception = e;
    }
    
    public SAXException(final String message, final Exception e) {
        super(message);
        this.exception = e;
    }
    
    public String getMessage() {
        final String message = super.getMessage();
        if (message == null && this.exception != null) {
            return this.exception.getMessage();
        }
        return message;
    }
    
    public Exception getException() {
        return this.exception;
    }
    
    public String toString() {
        if (this.exception != null) {
            return this.exception.toString();
        }
        return super.toString();
    }
}
