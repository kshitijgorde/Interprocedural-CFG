// 
// Decompiled by Procyon v0.5.30
// 

package uk.org.xml.sax;

import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;
import java.io.Writer;

public interface DocumentHandler extends org.xml.sax.DocumentHandler
{
    Writer startDocument(final Writer p0) throws SAXException;
    
    Writer startElement(final String p0, final AttributeList p1, final Writer p2) throws SAXException;
}
