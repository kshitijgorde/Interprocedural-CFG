// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import java.io.IOException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.io.Writer;
import java.io.OutputStream;
import java.util.Properties;

public final class ToHTMLSAXHandler extends ToSAXHandler
{
    private boolean m_dtdHandled;
    protected boolean m_escapeSetting;
    
    public Properties getOutputFormat() {
        return null;
    }
    
    public OutputStream getOutputStream() {
        return null;
    }
    
    public Writer getWriter() {
        return null;
    }
    
    public void indent(final int n) throws SAXException {
    }
    
    public void serialize(final Node node) throws IOException {
    }
    
    public boolean setEscaping(final boolean escape) throws SAXException {
        final boolean oldEscapeSetting = this.m_escapeSetting;
        this.m_escapeSetting = escape;
        if (escape) {
            this.processingInstruction("javax.xml.transform.enable-output-escaping", "");
        }
        else {
            this.processingInstruction("javax.xml.transform.disable-output-escaping", "");
        }
        return oldEscapeSetting;
    }
    
    public void setIndent(final boolean indent) {
    }
    
    public void setOutputFormat(final Properties format) {
    }
    
    public void setOutputStream(final OutputStream output) {
    }
    
    public void setWriter(final Writer writer) {
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
    }
    
    public void externalEntityDecl(final String arg0, final String arg1, final String arg2) throws SAXException {
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.flushPending();
        super.m_saxHandler.endElement(uri, localName, qName);
        if (super.m_tracer != null) {
            super.fireEndElem(qName);
        }
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.flushPending();
        super.m_saxHandler.processingInstruction(target, data);
        if (super.m_tracer != null) {
            super.fireEscapingEvent(target, data);
        }
    }
    
    public void setDocumentLocator(final Locator arg0) {
    }
    
    public void skippedEntity(final String arg0) throws SAXException {
    }
    
    public void startElement(final String namespaceURI, final String localName, final String qName, final Attributes atts) throws SAXException {
        this.flushPending();
        super.startElement(namespaceURI, localName, qName, atts);
        super.m_saxHandler.startElement(namespaceURI, localName, qName, atts);
        super.m_elemContext.m_startTagOpen = false;
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        this.flushPending();
        if (super.m_lexHandler != null) {
            super.m_lexHandler.comment(ch, start, length);
        }
        if (super.m_tracer != null) {
            super.fireCommentEvent(ch, start, length);
        }
    }
    
    public void endCDATA() throws SAXException {
    }
    
    public void endDTD() throws SAXException {
    }
    
    public void startCDATA() throws SAXException {
    }
    
    public void startEntity(final String arg0) throws SAXException {
    }
    
    public void endDocument() throws SAXException {
        this.flushPending();
        super.m_saxHandler.endDocument();
        if (super.m_tracer != null) {
            super.fireEndDoc();
        }
    }
    
    protected void closeStartTag() throws SAXException {
        super.m_elemContext.m_startTagOpen = false;
        super.m_saxHandler.startElement("", super.m_elemContext.m_elementName, super.m_elemContext.m_elementName, super.m_attributes);
        super.m_attributes.clear();
    }
    
    public void close() {
    }
    
    public void characters(final String chars) throws SAXException {
        final int length = chars.length();
        if (length > super.m_charsBuff.length) {
            super.m_charsBuff = new char[length * 2 + 1];
        }
        chars.getChars(0, length, super.m_charsBuff, 0);
        this.characters(super.m_charsBuff, 0, length);
    }
    
    public ToHTMLSAXHandler(final ContentHandler handler, final String encoding) {
        super(handler, encoding);
        this.m_dtdHandled = false;
        this.m_escapeSetting = false;
    }
    
    public ToHTMLSAXHandler(final ContentHandler handler, final LexicalHandler lex, final String encoding) {
        super(handler, lex, encoding);
        this.m_dtdHandled = false;
        this.m_escapeSetting = false;
    }
    
    public void startElement(final String elementNamespaceURI, final String elementLocalName, final String elementName) throws SAXException {
        super.startElement(elementNamespaceURI, elementLocalName, elementName);
        this.flushPending();
        if (!this.m_dtdHandled) {
            final String doctypeSystem = this.getDoctypeSystem();
            final String doctypePublic = this.getDoctypePublic();
            if ((doctypeSystem != null || doctypePublic != null) && super.m_lexHandler != null) {
                super.m_lexHandler.startDTD(elementName, doctypePublic, doctypeSystem);
            }
            this.m_dtdHandled = true;
        }
        super.m_elemContext = super.m_elemContext.push(elementNamespaceURI, elementLocalName, elementName);
    }
    
    public void startElement(final String elementName) throws SAXException {
        this.startElement(null, null, elementName);
    }
    
    public void endElement(final String elementName) throws SAXException {
        this.flushPending();
        super.m_saxHandler.endElement("", elementName, elementName);
        if (super.m_tracer != null) {
            super.fireEndElem(elementName);
        }
    }
    
    public void characters(final char[] ch, final int off, final int len) throws SAXException {
        this.flushPending();
        super.m_saxHandler.characters(ch, off, len);
        if (super.m_tracer != null) {
            super.fireCharEvent(ch, off, len);
        }
    }
    
    public void flushPending() throws SAXException {
        if (super.m_needToCallStartDocument) {
            this.startDocumentInternal();
            super.m_needToCallStartDocument = false;
        }
        if (super.m_elemContext.m_startTagOpen) {
            this.closeStartTag();
            super.m_elemContext.m_startTagOpen = false;
        }
    }
    
    public boolean startPrefixMapping(final String prefix, final String uri, final boolean shouldFlush) throws SAXException {
        if (shouldFlush) {
            this.flushPending();
        }
        super.m_saxHandler.startPrefixMapping(prefix, uri);
        return false;
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.startPrefixMapping(prefix, uri, true);
    }
    
    public void namespaceAfterStartElement(final String prefix, final String uri) throws SAXException {
        if (super.m_elemContext.m_elementURI == null) {
            final String prefix2 = SerializerBase.getPrefixPart(super.m_elemContext.m_elementName);
            if (prefix2 == null && "".equals(prefix)) {
                super.m_elemContext.m_elementURI = uri;
            }
        }
        this.startPrefixMapping(prefix, uri, false);
    }
    
    public boolean reset() {
        boolean wasReset = false;
        if (super.reset()) {
            this.resetToHTMLSAXHandler();
            wasReset = true;
        }
        return wasReset;
    }
    
    private void resetToHTMLSAXHandler() {
        this.m_escapeSetting = false;
    }
}
