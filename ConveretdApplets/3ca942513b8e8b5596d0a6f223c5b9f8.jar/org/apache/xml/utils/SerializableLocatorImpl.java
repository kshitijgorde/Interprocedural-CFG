// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.io.Serializable;
import org.xml.sax.Locator;

public class SerializableLocatorImpl implements Locator, Serializable
{
    static final long serialVersionUID = -2660312888446371460L;
    private String publicId;
    private String systemId;
    private int lineNumber;
    private int columnNumber;
    
    public SerializableLocatorImpl() {
    }
    
    public SerializableLocatorImpl(final Locator locator) {
        this.setPublicId(locator.getPublicId());
        this.setSystemId(locator.getSystemId());
        this.setLineNumber(locator.getLineNumber());
        this.setColumnNumber(locator.getColumnNumber());
    }
    
    public String getPublicId() {
        return this.publicId;
    }
    
    public String getSystemId() {
        return this.systemId;
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
    
    public int getColumnNumber() {
        return this.columnNumber;
    }
    
    public void setPublicId(final String publicId) {
        this.publicId = publicId;
    }
    
    public void setSystemId(final String systemId) {
        this.systemId = systemId;
    }
    
    public void setLineNumber(final int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    public void setColumnNumber(final int columnNumber) {
        this.columnNumber = columnNumber;
    }
}
