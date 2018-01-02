// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import java.io.IOException;
import org.w3c.dom.Node;
import java.io.Writer;
import java.io.OutputStream;
import java.util.Properties;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public final class ToTextSAXHandler extends ToSAXHandler
{
    public void endElement(final String elemName) throws SAXException {
        if (super.m_tracer != null) {
            super.fireEndElem(elemName);
        }
    }
    
    public void endElement(final String arg0, final String arg1, final String arg2) throws SAXException {
        if (super.m_tracer != null) {
            super.fireEndElem(arg2);
        }
    }
    
    public ToTextSAXHandler(final ContentHandler hdlr, final LexicalHandler lex, final String encoding) {
        super(hdlr, lex, encoding);
    }
    
    public ToTextSAXHandler(final ContentHandler handler, final String encoding) {
        super(handler, encoding);
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        if (super.m_tracer != null) {
            super.fireCommentEvent(ch, start, length);
        }
    }
    
    public void comment(final String data) throws SAXException {
        final int length = data.length();
        if (length > super.m_charsBuff.length) {
            super.m_charsBuff = new char[length * 2 + 1];
        }
        data.getChars(0, length, super.m_charsBuff, 0);
        this.comment(super.m_charsBuff, 0, length);
    }
    
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
    
    public boolean reset() {
        return false;
    }
    
    public void serialize(final Node node) throws IOException {
    }
    
    public boolean setEscaping(final boolean escape) {
        return false;
    }
    
    public void setIndent(final boolean indent) {
    }
    
    public void setOutputFormat(final Properties format) {
    }
    
    public void setOutputStream(final OutputStream output) {
    }
    
    public void setWriter(final Writer writer) {
    }
    
    public void addAttribute(final String uri, final String localName, final String rawName, final String type, final String value, final boolean XSLAttribute) {
    }
    
    public void attributeDecl(final String arg0, final String arg1, final String arg2, final String arg3, final String arg4) throws SAXException {
    }
    
    public void elementDecl(final String arg0, final String arg1) throws SAXException {
    }
    
    public void externalEntityDecl(final String arg0, final String arg1, final String arg2) throws SAXException {
    }
    
    public void internalEntityDecl(final String arg0, final String arg1) throws SAXException {
    }
    
    public void endPrefixMapping(final String arg0) throws SAXException {
    }
    
    public void ignorableWhitespace(final char[] arg0, final int arg1, final int arg2) throws SAXException {
    }
    
    public void processingInstruction(final String arg0, final String arg1) throws SAXException {
        if (super.m_tracer != null) {
            super.fireEscapingEvent(arg0, arg1);
        }
    }
    
    public void setDocumentLocator(final Locator arg0) {
    }
    
    public void skippedEntity(final String arg0) throws SAXException {
    }
    
    public void startElement(final String arg0, final String arg1, final String arg2, final Attributes arg3) throws SAXException {
        this.flushPending();
        super.startElement(arg0, arg1, arg2, arg3);
    }
    
    public void endCDATA() throws SAXException {
    }
    
    public void endDTD() throws SAXException {
    }
    
    public void startCDATA() throws SAXException {
    }
    
    public void startEntity(final String arg0) throws SAXException {
    }
    
    public void startElement(final String elementNamespaceURI, final String elementLocalName, final String elementName) throws SAXException {
        super.startElement(elementNamespaceURI, elementLocalName, elementName);
    }
    
    public void startElement(final String elementName) throws SAXException {
        super.startElement(elementName);
    }
    
    public void endDocument() throws SAXException {
        this.flushPending();
        super.m_saxHandler.endDocument();
        if (super.m_tracer != null) {
            super.fireEndDoc();
        }
    }
    
    public void characters(final String characters) throws SAXException {
        final int length = characters.length();
        if (length > super.m_charsBuff.length) {
            super.m_charsBuff = new char[length * 2 + 1];
        }
        characters.getChars(0, length, super.m_charsBuff, 0);
        super.m_saxHandler.characters(super.m_charsBuff, 0, length);
    }
    
    public void characters(final char[] characters, final int offset, final int length) throws SAXException {
        super.m_saxHandler.characters(characters, offset, length);
        if (super.m_tracer != null) {
            super.fireCharEvent(characters, offset, length);
        }
    }
    
    public void addAttribute(final String name, final String value) {
    }
    
    public boolean startPrefixMapping(final String prefix, final String uri, final boolean shouldFlush) throws SAXException {
        return false;
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
    }
    
    public void namespaceAfterStartElement(final String prefix, final String uri) throws SAXException {
    }
}
