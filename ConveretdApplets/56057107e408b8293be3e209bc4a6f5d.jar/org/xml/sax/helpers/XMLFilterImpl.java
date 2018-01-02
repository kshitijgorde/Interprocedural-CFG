// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import org.xml.sax.SAXParseException;
import org.xml.sax.Attributes;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.Locator;
import org.xml.sax.XMLReader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.XMLFilter;

public class XMLFilterImpl implements XMLFilter, EntityResolver, DTDHandler, ContentHandler, ErrorHandler
{
    private XMLReader parent;
    private Locator locator;
    private EntityResolver entityResolver;
    private DTDHandler dtdHandler;
    private ContentHandler contentHandler;
    private ErrorHandler errorHandler;
    
    public XMLFilterImpl() {
        this.parent = null;
        this.locator = null;
        this.entityResolver = null;
        this.dtdHandler = null;
        this.contentHandler = null;
        this.errorHandler = null;
    }
    
    public XMLFilterImpl(final XMLReader parent) {
        this.parent = null;
        this.locator = null;
        this.entityResolver = null;
        this.dtdHandler = null;
        this.contentHandler = null;
        this.errorHandler = null;
        this.setParent(parent);
    }
    
    public void setParent(final XMLReader parent) {
        if (parent == null) {
            throw new NullPointerException("Null parent");
        }
        this.parent = parent;
    }
    
    public XMLReader getParent() {
        return this.parent;
    }
    
    public void setFeature(final String name, final boolean state) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.parent != null) {
            this.parent.setFeature(name, state);
            return;
        }
        throw new SAXNotRecognizedException("Feature: " + name);
    }
    
    public boolean getFeature(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.parent != null) {
            return this.parent.getFeature(name);
        }
        throw new SAXNotRecognizedException("Feature: " + name);
    }
    
    public void setProperty(final String name, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.parent != null) {
            this.parent.setProperty(name, value);
            return;
        }
        throw new SAXNotRecognizedException("Property: " + name);
    }
    
    public Object getProperty(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.parent != null) {
            return this.parent.getProperty(name);
        }
        throw new SAXNotRecognizedException("Property: " + name);
    }
    
    public void setEntityResolver(final EntityResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException("Null entity resolver");
        }
        this.entityResolver = resolver;
    }
    
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }
    
    public void setDTDHandler(final DTDHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null DTD handler");
        }
        this.dtdHandler = handler;
    }
    
    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }
    
    public void setContentHandler(final ContentHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null content handler");
        }
        this.contentHandler = handler;
    }
    
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }
    
    public void setErrorHandler(final ErrorHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null error handler");
        }
        this.errorHandler = handler;
    }
    
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }
    
    public void parse(final InputSource input) throws SAXException, IOException {
        this.setupParse();
        this.parent.parse(input);
    }
    
    public void parse(final String systemId) throws SAXException, IOException {
        this.parse(new InputSource(systemId));
    }
    
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {
        if (this.entityResolver != null) {
            return this.entityResolver.resolveEntity(publicId, systemId);
        }
        return null;
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
        if (this.dtdHandler != null) {
            this.dtdHandler.notationDecl(name, publicId, systemId);
        }
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
        if (this.dtdHandler != null) {
            this.dtdHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
        }
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
        if (this.contentHandler != null) {
            this.contentHandler.setDocumentLocator(locator);
        }
    }
    
    public void startDocument() throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.startDocument();
        }
    }
    
    public void endDocument() throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.endDocument();
        }
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.startPrefixMapping(prefix, uri);
        }
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.endPrefixMapping(prefix);
        }
    }
    
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.startElement(uri, localName, qName, atts);
        }
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.endElement(uri, localName, qName);
        }
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.characters(ch, start, length);
        }
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.ignorableWhitespace(ch, start, length);
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.processingInstruction(target, data);
        }
    }
    
    public void skippedEntity(final String name) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.skippedEntity(name);
        }
    }
    
    public void warning(final SAXParseException e) throws SAXException {
        if (this.errorHandler != null) {
            this.errorHandler.warning(e);
        }
    }
    
    public void error(final SAXParseException e) throws SAXException {
        if (this.errorHandler != null) {
            this.errorHandler.error(e);
        }
    }
    
    public void fatalError(final SAXParseException e) throws SAXException {
        if (this.errorHandler != null) {
            this.errorHandler.fatalError(e);
        }
    }
    
    private void setupParse() {
        if (this.parent == null) {
            throw new NullPointerException("No parent for filter");
        }
        this.parent.setEntityResolver(this);
        this.parent.setDTDHandler(this);
        this.parent.setContentHandler(this);
        this.parent.setErrorHandler(this);
    }
}
