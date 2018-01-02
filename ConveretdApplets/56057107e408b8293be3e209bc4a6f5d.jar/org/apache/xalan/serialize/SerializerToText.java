// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import java.io.IOException;
import org.xml.sax.SAXException;

public class SerializerToText extends SerializerToXML
{
    public void cdata(final char[] ch, final int start, final int length) throws SAXException {
        try {
            this.writeNormalizedChars(ch, start, length, false);
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
        this.flush();
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        try {
            this.writeNormalizedChars(ch, start, length, false);
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
        this.flush();
    }
    
    public void charactersRaw(final char[] ch, final int start, final int length) throws SAXException {
        try {
            this.writeNormalizedChars(ch, start, length, false);
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
        this.flush();
    }
    
    public void comment(final String data) throws SAXException {
    }
    
    public void endDocument() throws SAXException {
        this.flush();
        this.flushWriter();
    }
    
    public void endElement(final String namespaceURI, final String localName, final String name) throws SAXException {
    }
    
    public void entityReference(final String name) throws SAXException {
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        try {
            this.writeNormalizedChars(ch, start, length, false);
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
        this.flush();
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void startDocument() throws SAXException {
    }
    
    public void startElement(final String namespaceURI, final String localName, final String name, final Attributes atts) throws SAXException {
    }
}
