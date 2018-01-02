// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

public interface XmlReadHandler
{
    void startElement(final String p0, final Attributes p1) throws SAXException, XmlReaderException;
    
    void characters(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void endElement(final String p0) throws SAXException, XmlReaderException;
    
    Object getObject() throws XmlReaderException;
    
    void init(final RootXmlReadHandler p0, final String p1);
}
