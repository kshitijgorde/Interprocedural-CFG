// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.xml.sax.SAXParseException;
import org.xml.sax.Locator;
import java.io.Serializable;
import javax.xml.transform.SourceLocator;
import org.xml.sax.helpers.LocatorImpl;

public class SAXSourceLocator extends LocatorImpl implements SourceLocator, Serializable
{
    Locator m_locator;
    
    public SAXSourceLocator() {
    }
    
    public SAXSourceLocator(final Locator locator) {
        this.m_locator = locator;
        this.setColumnNumber(locator.getColumnNumber());
        this.setLineNumber(locator.getLineNumber());
        this.setPublicId(locator.getPublicId());
        this.setSystemId(locator.getSystemId());
    }
    
    public SAXSourceLocator(final SAXParseException spe) {
        this.setLineNumber(spe.getLineNumber());
        this.setColumnNumber(spe.getColumnNumber());
        this.setPublicId(spe.getPublicId());
        this.setSystemId(spe.getSystemId());
    }
    
    public int getColumnNumber() {
        return (this.m_locator == null) ? super.getColumnNumber() : this.m_locator.getColumnNumber();
    }
    
    public int getLineNumber() {
        return (this.m_locator == null) ? super.getLineNumber() : this.m_locator.getLineNumber();
    }
    
    public String getPublicId() {
        return (this.m_locator == null) ? super.getPublicId() : this.m_locator.getPublicId();
    }
    
    public String getSystemId() {
        return (this.m_locator == null) ? super.getSystemId() : this.m_locator.getSystemId();
    }
}
