// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.w3c.dom.Node;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;

public abstract class ToSAXHandler extends SerializerBase
{
    protected ContentHandler m_saxHandler;
    protected LexicalHandler m_lexHandler;
    private boolean m_shouldGenerateNSAttribute;
    protected TransformStateSetter m_state;
    
    public ToSAXHandler() {
        this.m_shouldGenerateNSAttribute = true;
        this.m_state = null;
    }
    
    public ToSAXHandler(final ContentHandler hdlr, final LexicalHandler lex, final String encoding) {
        this.m_shouldGenerateNSAttribute = true;
        this.m_state = null;
        this.setContentHandler(hdlr);
        this.setLexHandler(lex);
        this.setEncoding(encoding);
    }
    
    public ToSAXHandler(final ContentHandler handler, final String encoding) {
        this.m_shouldGenerateNSAttribute = true;
        this.m_state = null;
        this.setContentHandler(handler);
        this.setEncoding(encoding);
    }
    
    protected void startDocumentInternal() throws SAXException {
        if (super.m_needToCallStartDocument) {
            super.startDocumentInternal();
            this.m_saxHandler.startDocument();
            super.m_needToCallStartDocument = false;
        }
    }
    
    public void startDTD(final String arg0, final String arg1, final String arg2) throws SAXException {
    }
    
    public void characters(final String characters) throws SAXException {
        final int len = characters.length();
        if (len > super.m_charsBuff.length) {
            super.m_charsBuff = new char[len * 2 + 1];
        }
        characters.getChars(0, len, super.m_charsBuff, 0);
        this.characters(super.m_charsBuff, 0, len);
    }
    
    public void comment(final String comment) throws SAXException {
        this.flushPending();
        if (this.m_lexHandler != null) {
            final int len = comment.length();
            if (len > super.m_charsBuff.length) {
                super.m_charsBuff = new char[len * 2 + 1];
            }
            comment.getChars(0, len, super.m_charsBuff, 0);
            this.m_lexHandler.comment(super.m_charsBuff, 0, len);
            if (super.m_tracer != null) {
                super.fireCommentEvent(super.m_charsBuff, 0, len);
            }
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
    }
    
    protected void closeStartTag() throws SAXException {
    }
    
    protected void closeCDATA() throws SAXException {
    }
    
    public void startElement(final String arg0, final String arg1, final String arg2, final Attributes arg3) throws SAXException {
        if (this.m_state != null) {
            this.m_state.resetState(this.getTransformer());
        }
        if (super.m_tracer != null) {
            super.fireStartElem(arg2);
        }
    }
    
    public void setLexHandler(final LexicalHandler _lexHandler) {
        this.m_lexHandler = _lexHandler;
    }
    
    public void setContentHandler(final ContentHandler _saxHandler) {
        this.m_saxHandler = _saxHandler;
        if (this.m_lexHandler == null && _saxHandler instanceof LexicalHandler) {
            this.m_lexHandler = (LexicalHandler)_saxHandler;
        }
    }
    
    public void setCdataSectionElements(final Vector URI_and_localNames) {
    }
    
    public void setShouldOutputNSAttr(final boolean doOutputNSAttr) {
        this.m_shouldGenerateNSAttribute = doOutputNSAttr;
    }
    
    boolean getShouldOutputNSAttr() {
        return this.m_shouldGenerateNSAttribute;
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
        if (super.m_cdataTagOpen) {
            this.closeCDATA();
            super.m_cdataTagOpen = false;
        }
    }
    
    public void setTransformState(final TransformStateSetter ts) {
        this.m_state = ts;
    }
    
    public void startElement(final String uri, final String localName, final String qName) throws SAXException {
        if (this.m_state != null) {
            this.m_state.resetState(this.getTransformer());
        }
        if (super.m_tracer != null) {
            super.fireStartElem(qName);
        }
    }
    
    public void startElement(final String qName) throws SAXException {
        if (this.m_state != null) {
            this.m_state.resetState(this.getTransformer());
        }
        if (super.m_tracer != null) {
            super.fireStartElem(qName);
        }
    }
    
    public void characters(final Node node) throws SAXException {
        if (this.m_state != null) {
            this.m_state.setCurrentNode(node);
        }
        final String data = node.getNodeValue();
        if (data != null) {
            this.characters(data);
        }
    }
    
    public void fatalError(final SAXParseException exc) throws SAXException {
        super.fatalError(exc);
        super.m_needToCallStartDocument = false;
        if (this.m_saxHandler instanceof ErrorHandler) {
            ((ErrorHandler)this.m_saxHandler).fatalError(exc);
        }
    }
    
    public void error(final SAXParseException exc) throws SAXException {
        super.error(exc);
        if (this.m_saxHandler instanceof ErrorHandler) {
            ((ErrorHandler)this.m_saxHandler).error(exc);
        }
    }
    
    public void warning(final SAXParseException exc) throws SAXException {
        super.warning(exc);
        if (this.m_saxHandler instanceof ErrorHandler) {
            ((ErrorHandler)this.m_saxHandler).warning(exc);
        }
    }
    
    public boolean reset() {
        boolean wasReset = false;
        if (super.reset()) {
            this.resetToSAXHandler();
            wasReset = true;
        }
        return wasReset;
    }
    
    private void resetToSAXHandler() {
        this.m_lexHandler = null;
        this.m_saxHandler = null;
        this.m_state = null;
        this.m_shouldGenerateNSAttribute = false;
    }
    
    public void addUniqueAttribute(final String qName, final String value, final int flags) throws SAXException {
        this.addAttribute(qName, value);
    }
}
