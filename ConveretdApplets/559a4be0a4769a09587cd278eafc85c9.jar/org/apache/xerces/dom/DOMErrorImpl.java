// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.dom3.DOMLocator;
import org.apache.xerces.xni.parser.XMLParseException;
import org.apache.xerces.dom3.DOMError;

public class DOMErrorImpl implements DOMError
{
    public short fSeverity;
    public String fMessage;
    public DOMLocatorImpl fLocator;
    public Exception fException;
    
    public DOMErrorImpl() {
        this.fSeverity = 0;
        this.fMessage = null;
        this.fLocator = new DOMLocatorImpl();
        this.fException = null;
    }
    
    public DOMErrorImpl(final short severity, final XMLParseException exception) {
        this.fSeverity = 0;
        this.fMessage = null;
        this.fLocator = new DOMLocatorImpl();
        this.fException = null;
        this.fSeverity = severity;
        this.fException = exception;
        this.fLocator = this.createDOMLocator(exception);
    }
    
    public short getSeverity() {
        return this.fSeverity;
    }
    
    public String getMessage() {
        return this.fMessage;
    }
    
    public DOMLocator getLocation() {
        return this.fLocator;
    }
    
    private DOMLocatorImpl createDOMLocator(final XMLParseException exception) {
        return new DOMLocatorImpl(exception.getLineNumber(), exception.getColumnNumber(), exception.getExpandedSystemId());
    }
    
    public Object getRelatedException() {
        return this.fException;
    }
    
    public void reset() {
        this.fSeverity = 0;
        this.fException = null;
    }
}
