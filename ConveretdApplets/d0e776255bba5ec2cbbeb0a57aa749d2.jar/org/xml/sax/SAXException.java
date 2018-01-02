// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax;

public class SAXException extends Exception
{
    private Exception exception;
    
    public SAXException(final String s) {
        super(s);
        this.exception = null;
    }
    
    public SAXException(final Exception exception) {
        this.exception = exception;
    }
    
    public SAXException(final String s, final Exception exception) {
        super(s);
        this.exception = exception;
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
