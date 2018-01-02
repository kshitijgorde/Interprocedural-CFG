// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.xml.sax.Attributes;
import org.w3c.dom.Element;
import org.xml.sax.Locator;
import java.io.Writer;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.apache.xalan.res.XSLMessages;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;

public class DOMBuilder implements ContentHandler, LexicalHandler
{
    public Document m_doc;
    protected Node m_currentNode;
    public DocumentFragment m_docFrag;
    protected NodeVector m_elemStack;
    protected boolean m_inCData;
    
    public DOMBuilder(final Document doc) {
        this.m_currentNode = null;
        this.m_docFrag = null;
        this.m_elemStack = new NodeVector();
        this.m_inCData = false;
        this.m_doc = doc;
    }
    
    public DOMBuilder(final Document doc, final DocumentFragment docFrag) {
        this.m_currentNode = null;
        this.m_docFrag = null;
        this.m_elemStack = new NodeVector();
        this.m_inCData = false;
        this.m_doc = doc;
        this.m_docFrag = docFrag;
    }
    
    public DOMBuilder(final Document doc, final Node node) {
        this.m_currentNode = null;
        this.m_docFrag = null;
        this.m_elemStack = new NodeVector();
        this.m_inCData = false;
        this.m_doc = doc;
        this.m_currentNode = node;
    }
    
    protected void append(final Node newNode) throws SAXException {
        final Node currentNode = this.m_currentNode;
        if (currentNode != null) {
            currentNode.appendChild(newNode);
        }
        else if (this.m_docFrag != null) {
            this.m_docFrag.appendChild(newNode);
        }
        else {
            boolean ok = true;
            final short type = newNode.getNodeType();
            if (type == 3) {
                final String data = newNode.getNodeValue();
                if (data != null && data.trim().length() > 0) {
                    throw new SAXException(XSLMessages.createXPATHMessage(63, null));
                }
                ok = false;
            }
            else if (type == 1 && this.m_doc.getDocumentElement() != null) {
                throw new SAXException(XSLMessages.createXPATHMessage(64, null));
            }
            if (ok) {
                this.m_doc.appendChild(newNode);
            }
        }
    }
    
    public void cdata(final char[] ch, final int start, final int length) throws SAXException {
        if (this.isOutsideDocElem() && XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
            return;
        }
        final String s = new String(ch, start, length);
        this.append(this.m_doc.createCDATASection(s));
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (this.isOutsideDocElem() && XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
            return;
        }
        if (this.m_inCData) {
            this.cdata(ch, start, length);
            return;
        }
        final String s = new String(ch, start, length);
        final Text text = this.m_doc.createTextNode(s);
        this.append(text);
    }
    
    public void charactersRaw(final char[] ch, final int start, final int length) throws SAXException {
        if (this.isOutsideDocElem() && XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
            return;
        }
        final String s = new String(ch, start, length);
        this.append(this.m_doc.createProcessingInstruction("xslt-next-is-raw", "formatter-to-dom"));
        this.append(this.m_doc.createTextNode(s));
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        this.append(this.m_doc.createComment(new String(ch, start, length)));
    }
    
    public void endCDATA() throws SAXException {
        this.m_inCData = false;
    }
    
    public void endDTD() throws SAXException {
    }
    
    public void endDocument() throws SAXException {
    }
    
    public void endElement(final String ns, final String localName, final String name) throws SAXException {
        this.m_currentNode = this.m_elemStack.popAndTop();
    }
    
    public void endEntity(final String name) throws SAXException {
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    public void entityReference(final String name) throws SAXException {
        this.append(this.m_doc.createEntityReference(name));
    }
    
    public Node getCurrentNode() {
        return this.m_currentNode;
    }
    
    public Node getRootNode() {
        return (Node)((this.m_docFrag != null) ? this.m_docFrag : this.m_doc);
    }
    
    public Writer getWriter() {
        return null;
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (this.isOutsideDocElem()) {
            return;
        }
        final String s = new String(ch, start, length);
        this.append(this.m_doc.createTextNode(s));
    }
    
    private boolean isOutsideDocElem() {
        return this.m_docFrag == null && this.m_elemStack.size() == 0 && (this.m_currentNode == null || this.m_currentNode.getNodeType() == 9);
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.append(this.m_doc.createProcessingInstruction(target, data));
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void setIDAttribute(final String id, final Element elem) {
    }
    
    public void skippedEntity(final String name) throws SAXException {
    }
    
    public void startCDATA() throws SAXException {
        this.m_inCData = true;
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
    }
    
    public void startDocument() throws SAXException {
    }
    
    public void startElement(final String ns, final String localName, final String name, final Attributes atts) throws SAXException {
        Element elem;
        if (ns == null || ns.length() == 0) {
            elem = this.m_doc.createElement(name);
        }
        else {
            elem = this.m_doc.createElementNS(ns, name);
        }
        this.append(elem);
        try {
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
                    final String attrQName = atts.getQName(i);
                    if (attrNS.length() == 0) {
                        elem.setAttribute(attrQName, atts.getValue(i));
                    }
                    else {
                        elem.setAttributeNS(attrNS, attrQName, atts.getValue(i));
                    }
                }
            }
            this.m_elemStack.push(elem);
            this.m_currentNode = elem;
        }
        catch (Exception e) {
            throw new SAXException(e);
        }
    }
    
    public void startEntity(final String name) throws SAXException {
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
    }
}
