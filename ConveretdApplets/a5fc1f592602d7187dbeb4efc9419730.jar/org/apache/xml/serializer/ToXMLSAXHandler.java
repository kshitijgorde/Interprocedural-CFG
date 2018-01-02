// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.Attributes;
import java.io.IOException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.io.Writer;
import java.io.OutputStream;
import java.util.Properties;

public class ToXMLSAXHandler extends ToSAXHandler
{
    protected boolean m_escapeSetting;
    
    public ToXMLSAXHandler() {
        this.m_escapeSetting = false;
        super.m_prefixMap = new NamespaceMappings();
        this.initCDATA();
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
    
    public void setOutputFormat(final Properties format) {
    }
    
    public void setOutputStream(final OutputStream output) {
    }
    
    public void setWriter(final Writer writer) {
    }
    
    public void attributeDecl(final String arg0, final String arg1, final String arg2, final String arg3, final String arg4) throws SAXException {
    }
    
    public void elementDecl(final String arg0, final String arg1) throws SAXException {
    }
    
    public void externalEntityDecl(final String arg0, final String arg1, final String arg2) throws SAXException {
    }
    
    public void internalEntityDecl(final String arg0, final String arg1) throws SAXException {
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
        final String localName = SerializerBase.getLocalName(super.m_elemContext.m_elementName);
        final String uri = this.getNamespaceURI(super.m_elemContext.m_elementName, true);
        if (super.m_needToCallStartDocument) {
            this.startDocumentInternal();
        }
        super.m_saxHandler.startElement(uri, localName, super.m_elemContext.m_elementName, super.m_attributes);
        super.m_attributes.clear();
        if (super.m_state != null) {
            super.m_state.setCurrentNode(null);
        }
    }
    
    public void closeCDATA() throws SAXException {
        if (super.m_lexHandler != null && super.m_cdataTagOpen) {
            super.m_lexHandler.endCDATA();
        }
        super.m_cdataTagOpen = false;
    }
    
    public void endElement(String namespaceURI, String localName, final String qName) throws SAXException {
        this.flushPending();
        if (namespaceURI == null) {
            if (super.m_elemContext.m_elementURI != null) {
                namespaceURI = super.m_elemContext.m_elementURI;
            }
            else {
                namespaceURI = this.getNamespaceURI(qName, true);
            }
        }
        if (localName == null) {
            if (super.m_elemContext.m_elementLocalName != null) {
                localName = super.m_elemContext.m_elementLocalName;
            }
            else {
                localName = SerializerBase.getLocalName(qName);
            }
        }
        super.m_saxHandler.endElement(namespaceURI, localName, qName);
        if (super.m_tracer != null) {
            super.fireEndElem(qName);
        }
        super.m_prefixMap.popNamespaces(super.m_elemContext.m_currentElemDepth, super.m_saxHandler);
        super.m_elemContext = super.m_elemContext.m_prev;
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    public void ignorableWhitespace(final char[] arg0, final int arg1, final int arg2) throws SAXException {
        super.m_saxHandler.ignorableWhitespace(arg0, arg1, arg2);
    }
    
    public void setDocumentLocator(final Locator arg0) {
        super.m_saxHandler.setDocumentLocator(arg0);
    }
    
    public void skippedEntity(final String arg0) throws SAXException {
        super.m_saxHandler.skippedEntity(arg0);
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.startPrefixMapping(prefix, uri, true);
    }
    
    public boolean startPrefixMapping(final String prefix, final String uri, final boolean shouldFlush) throws SAXException {
        int pushDepth;
        if (shouldFlush) {
            this.flushPending();
            pushDepth = super.m_elemContext.m_currentElemDepth + 1;
        }
        else {
            pushDepth = super.m_elemContext.m_currentElemDepth;
        }
        final boolean pushed = super.m_prefixMap.pushNamespace(prefix, uri, pushDepth);
        if (pushed) {
            super.m_saxHandler.startPrefixMapping(prefix, uri);
            if (this.getShouldOutputNSAttr()) {
                if ("".equals(prefix)) {
                    final String name = "xmlns";
                    this.addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, name, "CDATA", uri);
                }
                else if (!"".equals(uri)) {
                    final String name = "xmlns:" + prefix;
                    this.addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, name, "CDATA", uri);
                }
            }
        }
        return pushed;
    }
    
    public void comment(final char[] arg0, final int arg1, final int arg2) throws SAXException {
        this.flushPending();
        if (super.m_lexHandler != null) {
            super.m_lexHandler.comment(arg0, arg1, arg2);
        }
        if (super.m_tracer != null) {
            super.fireCommentEvent(arg0, arg1, arg2);
        }
    }
    
    public void endCDATA() throws SAXException {
    }
    
    public void endDTD() throws SAXException {
        if (super.m_lexHandler != null) {
            super.m_lexHandler.endDTD();
        }
    }
    
    public void startEntity(final String arg0) throws SAXException {
        if (super.m_lexHandler != null) {
            super.m_lexHandler.startEntity(arg0);
        }
    }
    
    public void characters(final String chars) throws SAXException {
        final int length = chars.length();
        if (length > super.m_charsBuff.length) {
            super.m_charsBuff = new char[length * 2 + 1];
        }
        chars.getChars(0, length, super.m_charsBuff, 0);
        this.characters(super.m_charsBuff, 0, length);
    }
    
    public ToXMLSAXHandler(final ContentHandler handler, final String encoding) {
        super(handler, encoding);
        this.m_escapeSetting = false;
        this.initCDATA();
        super.m_prefixMap = new NamespaceMappings();
    }
    
    public ToXMLSAXHandler(final ContentHandler handler, final LexicalHandler lex, final String encoding) {
        super(handler, lex, encoding);
        this.m_escapeSetting = false;
        this.initCDATA();
        super.m_prefixMap = new NamespaceMappings();
    }
    
    public void startElement(final String elementNamespaceURI, final String elementLocalName, final String elementName) throws SAXException {
        this.startElement(elementNamespaceURI, elementLocalName, elementName, null);
    }
    
    public void startElement(final String elementName) throws SAXException {
        this.startElement(null, null, elementName, null);
    }
    
    public void characters(final char[] ch, final int off, final int len) throws SAXException {
        if (super.m_needToCallStartDocument) {
            this.startDocumentInternal();
            super.m_needToCallStartDocument = false;
        }
        if (super.m_elemContext.m_startTagOpen) {
            this.closeStartTag();
            super.m_elemContext.m_startTagOpen = false;
        }
        if (super.m_elemContext.m_isCdataSection && !super.m_cdataTagOpen && super.m_lexHandler != null) {
            super.m_lexHandler.startCDATA();
            super.m_cdataTagOpen = true;
        }
        super.m_saxHandler.characters(ch, off, len);
        if (super.m_tracer != null) {
            this.fireCharEvent(ch, off, len);
        }
    }
    
    public void endElement(final String elemName) throws SAXException {
        this.endElement(null, null, elemName);
    }
    
    public void namespaceAfterStartElement(final String prefix, final String uri) throws SAXException {
        this.startPrefixMapping(prefix, uri, false);
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.flushPending();
        super.m_saxHandler.processingInstruction(target, data);
        if (super.m_tracer != null) {
            super.fireEscapingEvent(target, data);
        }
    }
    
    protected boolean popNamespace(final String prefix) {
        try {
            if (super.m_prefixMap.popNamespace(prefix)) {
                super.m_saxHandler.endPrefixMapping(prefix);
                return true;
            }
        }
        catch (SAXException ex) {}
        return false;
    }
    
    public void startCDATA() throws SAXException {
        if (!super.m_cdataTagOpen) {
            this.flushPending();
            if (super.m_lexHandler != null) {
                super.m_lexHandler.startCDATA();
                super.m_cdataTagOpen = true;
            }
        }
    }
    
    public void startElement(final String namespaceURI, final String localName, final String name, final Attributes atts) throws SAXException {
        this.flushPending();
        super.startElement(namespaceURI, localName, name, atts);
        if (super.m_needToOutputDocTypeDecl) {
            final String doctypeSystem = this.getDoctypeSystem();
            if (doctypeSystem != null && super.m_lexHandler != null) {
                final String doctypePublic = this.getDoctypePublic();
                if (doctypeSystem != null) {
                    super.m_lexHandler.startDTD(name, doctypePublic, doctypeSystem);
                }
            }
            super.m_needToOutputDocTypeDecl = false;
        }
        super.m_elemContext = super.m_elemContext.push(namespaceURI, localName, name);
        if (namespaceURI != null) {
            this.ensurePrefixIsDeclared(namespaceURI, name);
        }
        if (atts != null) {
            this.addAttributes(atts);
        }
        super.m_elemContext.m_isCdataSection = this.isCdataSection();
    }
    
    private void ensurePrefixIsDeclared(final String ns, final String rawName) throws SAXException {
        if (ns != null && ns.length() > 0) {
            final int index;
            final String prefix = ((index = rawName.indexOf(":")) < 0) ? "" : rawName.substring(0, index);
            if (null != prefix) {
                final String foundURI = super.m_prefixMap.lookupNamespace(prefix);
                if (null == foundURI || !foundURI.equals(ns)) {
                    this.startPrefixMapping(prefix, ns, false);
                    if (this.getShouldOutputNSAttr()) {
                        this.addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, "xmlns" + ((prefix.length() == 0) ? "" : ":") + prefix, "CDATA", ns);
                    }
                }
            }
        }
    }
    
    public void addAttribute(final String uri, final String localName, final String rawName, final String type, final String value) throws SAXException {
        if (super.m_elemContext.m_startTagOpen) {
            this.ensurePrefixIsDeclared(uri, rawName);
            this.addAttributeAlways(uri, localName, rawName, type, value);
        }
    }
    
    public boolean reset() {
        boolean wasReset = false;
        if (super.reset()) {
            this.resetToXMLSAXHandler();
            wasReset = true;
        }
        return wasReset;
    }
    
    private void resetToXMLSAXHandler() {
        this.m_escapeSetting = false;
    }
}
