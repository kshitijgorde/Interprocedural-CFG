// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.io.Writer;
import org.apache.xml.serializer.utils.Utils;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public final class ToTextStream extends ToStream
{
    protected void startDocumentInternal() throws SAXException {
        super.startDocumentInternal();
        super.m_needToCallStartDocument = false;
    }
    
    public void endDocument() throws SAXException {
        this.flushPending();
        this.flushWriter();
        if (super.m_tracer != null) {
            super.fireEndDoc();
        }
    }
    
    public void startElement(final String namespaceURI, final String localName, final String name, final Attributes atts) throws SAXException {
        if (super.m_tracer != null) {
            super.fireStartElem(name);
            this.firePseudoAttributes();
        }
    }
    
    public void endElement(final String namespaceURI, final String localName, final String name) throws SAXException {
        if (super.m_tracer != null) {
            super.fireEndElem(name);
        }
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        this.flushPending();
        try {
            if (this.inTemporaryOutputState()) {
                super.m_writer.write(ch, start, length);
            }
            else {
                this.writeNormalizedChars(ch, start, length, super.m_lineSepUse);
            }
            if (super.m_tracer != null) {
                super.fireCharEvent(ch, start, length);
            }
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }
    
    public void charactersRaw(final char[] ch, final int start, final int length) throws SAXException {
        try {
            this.writeNormalizedChars(ch, start, length, super.m_lineSepUse);
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }
    
    void writeNormalizedChars(final char[] ch, final int start, final int length, final boolean useLineSep) throws IOException, SAXException {
        final String encoding = this.getEncoding();
        final Writer writer = super.m_writer;
        final int end = start + length;
        final char S_LINEFEED = '\n';
        for (int i = start; i < end; ++i) {
            final char c = ch[i];
            if ('\n' == c && useLineSep) {
                writer.write(super.m_lineSep, 0, super.m_lineSepLen);
            }
            else if (super.m_encodingInfo.isInEncoding(c)) {
                writer.write(c);
            }
            else if (Encodings.isHighUTF16Surrogate(c)) {
                final int codePoint = this.writeUTF16Surrogate(c, ch, i, end);
                if (codePoint != 0) {
                    final String integralValue = Integer.toString(codePoint);
                    final String msg = Utils.messages.createMessage("ER_ILLEGAL_CHARACTER", new Object[] { integralValue, encoding });
                    System.err.println(msg);
                }
                ++i;
            }
            else if (encoding != null) {
                writer.write(38);
                writer.write(35);
                writer.write(Integer.toString(c));
                writer.write(59);
                final String integralValue2 = Integer.toString(c);
                final String msg2 = Utils.messages.createMessage("ER_ILLEGAL_CHARACTER", new Object[] { integralValue2, encoding });
                System.err.println(msg2);
            }
            else {
                writer.write(c);
            }
        }
    }
    
    public void cdata(final char[] ch, final int start, final int length) throws SAXException {
        try {
            this.writeNormalizedChars(ch, start, length, super.m_lineSepUse);
            if (super.m_tracer != null) {
                super.fireCDATAEvent(ch, start, length);
            }
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        try {
            this.writeNormalizedChars(ch, start, length, super.m_lineSepUse);
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.flushPending();
        if (super.m_tracer != null) {
            super.fireEscapingEvent(target, data);
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
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        this.flushPending();
        if (super.m_tracer != null) {
            super.fireCommentEvent(ch, start, length);
        }
    }
    
    public void entityReference(final String name) throws SAXException {
        if (super.m_tracer != null) {
            super.fireEntityReference(name);
        }
    }
    
    public void addAttribute(final String uri, final String localName, final String rawName, final String type, final String value, final boolean XSLAttribute) {
    }
    
    public void endCDATA() throws SAXException {
    }
    
    public void endElement(final String elemName) throws SAXException {
        if (super.m_tracer != null) {
            super.fireEndElem(elemName);
        }
    }
    
    public void startElement(final String elementNamespaceURI, final String elementLocalName, final String elementName) throws SAXException {
        if (super.m_needToCallStartDocument) {
            this.startDocumentInternal();
        }
        if (super.m_tracer != null) {
            super.fireStartElem(elementName);
            this.firePseudoAttributes();
        }
    }
    
    public void characters(final String characters) throws SAXException {
        final int length = characters.length();
        if (length > super.m_charsBuff.length) {
            super.m_charsBuff = new char[length * 2 + 1];
        }
        characters.getChars(0, length, super.m_charsBuff, 0);
        this.characters(super.m_charsBuff, 0, length);
    }
    
    public void addAttribute(final String name, final String value) {
    }
    
    public void addUniqueAttribute(final String qName, final String value, final int flags) throws SAXException {
    }
    
    public boolean startPrefixMapping(final String prefix, final String uri, final boolean shouldFlush) throws SAXException {
        return false;
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
    }
    
    public void namespaceAfterStartElement(final String prefix, final String uri) throws SAXException {
    }
    
    public void flushPending() throws SAXException {
        if (super.m_needToCallStartDocument) {
            this.startDocumentInternal();
            super.m_needToCallStartDocument = false;
        }
    }
}
