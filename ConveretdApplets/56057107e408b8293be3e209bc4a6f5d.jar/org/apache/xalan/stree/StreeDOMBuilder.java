// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.xml.sax.Attributes;
import org.apache.xml.utils.XMLCharacterRecognizer;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Document;
import org.apache.xml.utils.DOMBuilder;

public class StreeDOMBuilder extends DOMBuilder
{
    TextImpl m_text_buffer;
    protected DocumentImpl m_docImpl;
    private boolean m_previousIsText;
    private static final boolean DEBUG = false;
    
    public StreeDOMBuilder(final Document doc) {
        super(doc);
        this.m_text_buffer = null;
        this.m_previousIsText = false;
        this.m_docImpl = (DocumentImpl)doc;
    }
    
    public StreeDOMBuilder(final Document doc, final DocumentFragment docFrag) {
        super(doc, docFrag);
        this.m_text_buffer = null;
        this.m_previousIsText = false;
        this.m_docImpl = (DocumentImpl)doc;
    }
    
    public StreeDOMBuilder(final Document doc, final Node node) {
        super(doc, node);
        this.m_text_buffer = null;
        this.m_previousIsText = false;
        this.m_docImpl = (DocumentImpl)doc;
    }
    
    void appendAccumulatedText(final Node currentNode, final char[] ch, final int start, final int length) throws SAXException {
        if (this.m_text_buffer == null) {
            this.m_text_buffer = new TextImpl(this.m_docImpl, ch, start, length);
            this.setPreviousIsText(true);
        }
        else {
            this.m_text_buffer.appendText(ch, start, length);
        }
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (this.getPreviousIsText()) {
            this.appendAccumulatedText(this.m_text_buffer, ch, start, length);
        }
        else {
            this.m_text_buffer = new TextImpl(this.m_docImpl, ch, start, length);
            this.setPreviousIsText(true);
        }
    }
    
    public void charactersRaw(final char[] ch, final int start, final int length) throws SAXException {
        this.setPreviousIsText(false);
        this.append(super.m_doc.createProcessingInstruction("xslt-next-is-raw", "formatter-to-dom"));
        this.append(new TextImpl(this.m_docImpl, ch, start, length));
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        this.setPreviousIsText(false);
        this.append(new CommentImpl(this.m_docImpl, ch, start, length));
    }
    
    public void endDocument() throws SAXException {
        super.endDocument();
        this.setPreviousIsText(false);
    }
    
    public void endElement(final String ns, final String localName, final String name) throws SAXException {
        this.setPreviousIsText(false);
        super.endElement(ns, localName, name);
    }
    
    boolean getPreviousIsText() {
        return this.m_previousIsText;
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (this.getPreviousIsText()) {
            this.appendAccumulatedText(this.m_text_buffer, ch, start, length);
        }
        else {
            this.m_text_buffer = new TextImpl(this.m_docImpl, ch, start, length);
        }
        this.setPreviousIsText(true);
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.setPreviousIsText(false);
        super.processingInstruction(target, data);
    }
    
    public void setIDAttribute(final String id, final Element elem) {
        this.m_docImpl.setIDAttribute(id, elem);
    }
    
    public void setPreviousIsText(final boolean isText) throws SAXException {
        if (this.m_previousIsText && !isText) {
            if (!this.m_docImpl.m_sourceTreeHandler.getShouldStripWhitespace() || !XMLCharacterRecognizer.isWhiteSpace(this.m_text_buffer.m_doc.m_chars.m_map, this.m_text_buffer.m_start, this.m_text_buffer.m_length)) {
                this.append(this.m_text_buffer);
            }
            this.m_text_buffer = null;
        }
        this.m_previousIsText = isText;
    }
    
    public void startElement(final String ns, final String localName, final String name, final Attributes atts) throws SAXException {
        this.setPreviousIsText(false);
        ElementImpl elem;
        if (ns == null || ns.length() == 0) {
            elem = (ElementImpl)super.m_doc.createElement(name);
        }
        else {
            elem = (ElementImpl)super.m_doc.createElementNS(ns, name);
        }
        elem.m_uid = ++((DocImpl)super.m_doc).m_docOrderCount;
        elem.m_level = (short)(((DocImpl)super.m_doc).m_level + 1);
        final int nAtts = atts.getLength();
        if (nAtts != 0) {
            for (int i = 0; i < nAtts; ++i) {
                if (atts.getType(i).equalsIgnoreCase("ID")) {
                    this.setIDAttribute(atts.getValue(i), elem);
                }
                String attrNS = atts.getURI(i);
                if (attrNS == null) {
                    attrNS = "";
                }
                if (attrNS.length() == 0 || atts.getQName(i).startsWith("xmlns:")) {
                    elem.setAttribute(atts.getQName(i), atts.getValue(i));
                }
                else {
                    elem.setAttributeNS(attrNS, atts.getQName(i), atts.getValue(i));
                }
            }
        }
        this.append(elem);
        super.m_elemStack.push(elem);
        super.m_currentNode = elem;
    }
}
