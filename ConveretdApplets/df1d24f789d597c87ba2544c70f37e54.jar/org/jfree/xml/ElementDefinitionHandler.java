// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

public interface ElementDefinitionHandler
{
    void startElement(final String p0, final Attributes p1) throws SAXException;
    
    void characters(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void endElement(final String p0) throws SAXException;
    
    Parser getParser();
}
