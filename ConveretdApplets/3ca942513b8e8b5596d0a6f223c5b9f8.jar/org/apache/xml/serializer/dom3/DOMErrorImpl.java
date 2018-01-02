// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.dom3;

import org.w3c.dom.DOMLocator;
import org.w3c.dom.DOMError;

final class DOMErrorImpl implements DOMError
{
    private short fSeverity;
    private String fMessage;
    private String fType;
    private Exception fException;
    private Object fRelatedData;
    private DOMLocatorImpl fLocation;
    
    public DOMErrorImpl() {
        this.fSeverity = 1;
        this.fMessage = null;
        this.fException = null;
        this.fLocation = new DOMLocatorImpl();
    }
    
    public DOMErrorImpl(final short severity, final String message, final String type) {
        this.fSeverity = 1;
        this.fMessage = null;
        this.fException = null;
        this.fLocation = new DOMLocatorImpl();
        this.fSeverity = severity;
        this.fMessage = message;
        this.fType = type;
    }
    
    public DOMErrorImpl(final short severity, final String message, final String type, final Exception exception) {
        this.fSeverity = 1;
        this.fMessage = null;
        this.fException = null;
        this.fLocation = new DOMLocatorImpl();
        this.fSeverity = severity;
        this.fMessage = message;
        this.fType = type;
        this.fException = exception;
    }
    
    public DOMErrorImpl(final short severity, final String message, final String type, final Exception exception, final Object relatedData, final DOMLocatorImpl location) {
        this.fSeverity = 1;
        this.fMessage = null;
        this.fException = null;
        this.fLocation = new DOMLocatorImpl();
        this.fSeverity = severity;
        this.fMessage = message;
        this.fType = type;
        this.fException = exception;
        this.fRelatedData = relatedData;
        this.fLocation = location;
    }
    
    public short getSeverity() {
        return this.fSeverity;
    }
    
    public String getMessage() {
        return this.fMessage;
    }
    
    public DOMLocator getLocation() {
        return this.fLocation;
    }
    
    public Object getRelatedException() {
        return this.fException;
    }
    
    public String getType() {
        return this.fType;
    }
    
    public Object getRelatedData() {
        return this.fRelatedData;
    }
    
    public void reset() {
        this.fSeverity = 1;
        this.fException = null;
        this.fMessage = null;
        this.fType = null;
        this.fRelatedData = null;
        this.fLocation = null;
    }
}
