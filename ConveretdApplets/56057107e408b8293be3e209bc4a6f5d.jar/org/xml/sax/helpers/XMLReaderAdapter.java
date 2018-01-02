// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXNotSupportedException;
import java.util.Locale;
import org.xml.sax.SAXException;
import org.xml.sax.DocumentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.ContentHandler;
import org.xml.sax.Parser;

public class XMLReaderAdapter implements Parser, ContentHandler
{
    XMLReader xmlReader;
    DocumentHandler documentHandler;
    AttributesAdapter qAtts;
    
    public XMLReaderAdapter() throws SAXException {
        this.setup(XMLReaderFactory.createXMLReader());
    }
    
    public XMLReaderAdapter(final XMLReader xmlReader) {
        this.setup(xmlReader);
    }
    
    private void setup(final XMLReader xmlReader) {
        if (xmlReader == null) {
            throw new NullPointerException("XMLReader must not be null");
        }
        this.xmlReader = xmlReader;
        this.qAtts = new AttributesAdapter();
    }
    
    public void setLocale(final Locale locale) throws SAXException {
        throw new SAXNotSupportedException("setLocale not supported");
    }
    
    public void setEntityResolver(final EntityResolver resolver) {
        this.xmlReader.setEntityResolver(resolver);
    }
    
    public void setDTDHandler(final DTDHandler handler) {
        this.xmlReader.setDTDHandler(handler);
    }
    
    public void setDocumentHandler(final DocumentHandler handler) {
        this.documentHandler = handler;
    }
    
    public void setErrorHandler(final ErrorHandler handler) {
        this.xmlReader.setErrorHandler(handler);
    }
    
    public void parse(final String systemId) throws IOException, SAXException {
        this.parse(new InputSource(systemId));
    }
    
    public void parse(final InputSource input) throws IOException, SAXException {
        this.setupXMLReader();
        this.xmlReader.parse(input);
    }
    
    private void setupXMLReader() throws SAXException {
        this.xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
        try {
            this.xmlReader.setFeature("http://xml.org/sax/features/namespaces", false);
        }
        catch (SAXException ex) {}
        this.xmlReader.setContentHandler(this);
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.documentHandler.setDocumentLocator(locator);
    }
    
    public void startDocument() throws SAXException {
        this.documentHandler.startDocument();
    }
    
    public void endDocument() throws SAXException {
        this.documentHandler.endDocument();
    }
    
    public void startPrefixMapping(final String prefix, final String uri) {
    }
    
    public void endPrefixMapping(final String prefix) {
    }
    
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        this.qAtts.setAttributes(atts);
        this.documentHandler.startElement(qName, this.qAtts);
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.documentHandler.endElement(qName);
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        this.documentHandler.characters(ch, start, length);
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        this.documentHandler.ignorableWhitespace(ch, start, length);
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.documentHandler.processingInstruction(target, data);
    }
    
    public void skippedEntity(final String name) throws SAXException {
    }
    
    final class AttributesAdapter implements AttributeList
    {
        private Attributes attributes;
        
        void setAttributes(final Attributes attributes) {
            this.attributes = attributes;
        }
        
        public int getLength() {
            return this.attributes.getLength();
        }
        
        public String getName(final int i) {
            return this.attributes.getQName(i);
        }
        
        public String getType(final int i) {
            return this.attributes.getType(i);
        }
        
        public String getValue(final int i) {
            return this.attributes.getValue(i);
        }
        
        public String getType(final String qName) {
            return this.attributes.getType(qName);
        }
        
        public String getValue(final String qName) {
            return this.attributes.getValue(qName);
        }
    }
}
