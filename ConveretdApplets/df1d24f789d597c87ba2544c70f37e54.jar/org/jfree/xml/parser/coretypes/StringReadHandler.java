// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.jfree.xml.parser.XmlReaderException;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class StringReadHandler extends AbstractXmlReadHandler
{
    private StringBuffer buffer;
    private String result;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        this.buffer = new StringBuffer();
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        this.buffer.append(array, n, n2);
    }
    
    protected void doneParsing() throws SAXException, XmlReaderException {
        this.result = this.buffer.toString();
        this.buffer = null;
    }
    
    public Object getObject() {
        return this.result;
    }
}
