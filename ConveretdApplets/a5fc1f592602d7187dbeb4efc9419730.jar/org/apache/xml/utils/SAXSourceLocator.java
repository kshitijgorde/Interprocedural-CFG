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
    
    public SAXSourceLocator(final SourceLocator locator) {
        this.m_locator = null;
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
    
    public String getPublicId() {
        return (null == this.m_locator) ? super.getPublicId() : this.m_locator.getPublicId();
    }
    
    public String getSystemId() {
        return (null == this.m_locator) ? super.getSystemId() : this.m_locator.getSystemId();
    }
    
    public int getLineNumber() {
        return (null == this.m_locator) ? super.getLineNumber() : this.m_locator.getLineNumber();
    }
    
    public int getColumnNumber() {
        return (null == this.m_locator) ? super.getColumnNumber() : this.m_locator.getColumnNumber();
    }
}
