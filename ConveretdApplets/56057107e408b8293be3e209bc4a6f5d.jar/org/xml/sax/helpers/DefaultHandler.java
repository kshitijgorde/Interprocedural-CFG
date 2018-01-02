// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import org.xml.sax.SAXParseException;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;

public class DefaultHandler implements EntityResolver, DTDHandler, ContentHandler, ErrorHandler
{
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException {
        return null;
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void startDocument() throws SAXException {
    }
    
    public void endDocument() throws SAXException {
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
    }
    
    public void skippedEntity(final String name) throws SAXException {
    }
    
    public void warning(final SAXParseException e) throws SAXException {
    }
    
    public void error(final SAXParseException e) throws SAXException {
    }
    
    public void fatalError(final SAXParseException e) throws SAXException {
        throw e;
    }
}
