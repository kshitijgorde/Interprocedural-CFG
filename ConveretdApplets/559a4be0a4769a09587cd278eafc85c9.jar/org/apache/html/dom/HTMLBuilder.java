// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLDocument;
import org.apache.xerces.dom.ProcessingInstructionImpl;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.TextImpl;
import org.w3c.dom.Node;
import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;
import java.util.Vector;
import org.xml.sax.Locator;
import org.apache.xerces.dom.ElementImpl;
import org.xml.sax.DocumentHandler;

public class HTMLBuilder implements DocumentHandler
{
    protected HTMLDocumentImpl _document;
    protected ElementImpl _current;
    private Locator _locator;
    private boolean _ignoreWhitespace;
    private boolean _done;
    protected Vector _preRootNodes;
    
    public HTMLBuilder() {
        this._ignoreWhitespace = true;
        this._done = true;
    }
    
    public void startDocument() throws SAXException {
        if (!this._done) {
            throw new SAXException("HTM001 State error: startDocument fired twice on one builder.");
        }
        this._document = null;
        this._done = false;
    }
    
    public void endDocument() throws SAXException {
        if (this._document == null) {
            throw new SAXException("HTM002 State error: document never started or missing document element.");
        }
        if (this._current != null) {
            throw new SAXException("HTM003 State error: document ended before end of document element.");
        }
        this._current = null;
        this._done = true;
    }
    
    public synchronized void startElement(final String tagName, final AttributeList attrList) throws SAXException {
        if (tagName == null) {
            throw new SAXException("HTM004 Argument 'tagName' is null.");
        }
        ElementImpl elem;
        if (this._document == null) {
            this._document = new HTMLDocumentImpl();
            elem = (ElementImpl)this._document.getDocumentElement();
            this._current = elem;
            if (this._current == null) {
                throw new SAXException("HTM005 State error: Document.getDocumentElement returns null.");
            }
            if (this._preRootNodes != null) {
                int i = this._preRootNodes.size();
                while (i-- > 0) {
                    this._document.insertBefore(this._preRootNodes.elementAt(i), elem);
                }
                this._preRootNodes = null;
            }
        }
        else {
            if (this._current == null) {
                throw new SAXException("HTM006 State error: startElement called after end of document element.");
            }
            elem = (ElementImpl)this._document.createElement(tagName);
            this._current.appendChild(elem);
            this._current = elem;
        }
        if (attrList != null) {
            for (int i = 0; i < attrList.getLength(); ++i) {
                elem.setAttribute(attrList.getName(i), attrList.getValue(i));
            }
        }
    }
    
    public void endElement(final String tagName) throws SAXException {
        if (this._current == null) {
            throw new SAXException("HTM007 State error: endElement called with no current node.");
        }
        if (!this._current.getNodeName().equals(tagName)) {
            throw new SAXException("HTM008 State error: mismatch in closing tag name " + tagName + "\n" + tagName);
        }
        if (this._current.getParentNode() == this._current.getOwnerDocument()) {
            this._current = null;
        }
        else {
            this._current = (ElementImpl)this._current.getParentNode();
        }
    }
    
    public void characters(final String text) throws SAXException {
        if (this._current == null) {
            throw new SAXException("HTM009 State error: character data found outside of root element.");
        }
        this._current.appendChild(new TextImpl(this._document, text));
    }
    
    public void characters(final char[] text, final int start, final int length) throws SAXException {
        if (this._current == null) {
            throw new SAXException("HTM010 State error: character data found outside of root element.");
        }
        this._current.appendChild(new TextImpl(this._document, new String(text, start, length)));
    }
    
    public void ignorableWhitespace(final char[] text, final int start, final int length) throws SAXException {
        if (!this._ignoreWhitespace) {
            this._current.appendChild(new TextImpl(this._document, new String(text, start, length)));
        }
    }
    
    public void processingInstruction(final String target, final String instruction) throws SAXException {
        if (this._current == null && this._document == null) {
            if (this._preRootNodes == null) {
                this._preRootNodes = new Vector();
            }
            this._preRootNodes.addElement(new ProcessingInstructionImpl(null, target, instruction));
        }
        else if (this._current == null && this._document != null) {
            this._document.appendChild(new ProcessingInstructionImpl(this._document, target, instruction));
        }
        else {
            this._current.appendChild(new ProcessingInstructionImpl(this._document, target, instruction));
        }
    }
    
    public HTMLDocument getHTMLDocument() {
        return this._document;
    }
    
    public void setDocumentLocator(final Locator locator) {
        this._locator = locator;
    }
}
