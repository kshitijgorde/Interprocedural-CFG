// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax;

public interface Configurable
{
    void setFeature(final String p0, final boolean p1) throws SAXException;
    
    boolean getFeature(final String p0) throws SAXException;
    
    void setProperty(final String p0, final Object p1) throws SAXException;
    
    Object getProperty(final String p0) throws SAXException;
}
