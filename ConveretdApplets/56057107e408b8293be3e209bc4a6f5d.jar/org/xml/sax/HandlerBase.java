// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax;

public class HandlerBase implements EntityResolver, DTDHandler, DocumentHandler, ErrorHandler
{
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException {
        return null;
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) {
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) {
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void startDocument() throws SAXException {
    }
    
    public void endDocument() throws SAXException {
    }
    
    public void startElement(final String name, final AttributeList attributes) throws SAXException {
    }
    
    public void endElement(final String name) throws SAXException {
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
    }
    
    public void warning(final SAXParseException e) throws SAXException {
    }
    
    public void error(final SAXParseException e) throws SAXException {
    }
    
    public void fatalError(final SAXParseException e) throws SAXException {
        throw e;
    }
}
