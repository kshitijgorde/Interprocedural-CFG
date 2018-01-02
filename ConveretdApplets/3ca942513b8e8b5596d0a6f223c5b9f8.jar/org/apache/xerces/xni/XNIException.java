// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni;

public class XNIException extends RuntimeException
{
    static final long serialVersionUID = 9019819772686063775L;
    private Exception fException;
    
    public XNIException(final String s) {
        super(s);
    }
    
    public XNIException(final Exception fException) {
        super(fException.getMessage());
        this.fException = fException;
    }
    
    public XNIException(final String s, final Exception fException) {
        super(s);
        this.fException = fException;
    }
    
    public Exception getException() {
        return this.fException;
    }
}
