// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import org.xml.sax.SAXException;
import org.w3c.dom.Comment;
import org.xml.sax.Locator;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Vector;
import java.util.Stack;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.apache.xalan.xsltc.runtime.Constants;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;

public class SAX2DOM implements ContentHandler, LexicalHandler, Constants
{
    private Node _root;
    private Document _document;
    private Stack _nodeStk;
    private Vector _namespaceDecls;
    
    public SAX2DOM() throws ParserConfigurationException {
        this._root = null;
        this._document = null;
        this._nodeStk = new Stack();
        this._namespaceDecls = null;
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        this._document = factory.newDocumentBuilder().newDocument();
        this._root = this._document;
    }
    
    public SAX2DOM(final Node root) throws ParserConfigurationException {
        this._root = null;
        this._document = null;
        this._nodeStk = new Stack();
        this._namespaceDecls = null;
        this._root = root;
        if (root instanceof Document) {
            this._document = (Document)root;
        }
        else if (root != null) {
            this._document = root.getOwnerDocument();
        }
        else {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            this._document = factory.newDocumentBuilder().newDocument();
            this._root = this._document;
        }
    }
    
    public Node getDOM() {
        return this._root;
    }
    
    public void characters(final char[] ch, final int start, final int length) {
        final Node last = this._nodeStk.peek();
        if (last != this._document) {
            final String text = new String(ch, start, length);
            last.appendChild(this._document.createTextNode(text));
        }
    }
    
    public void startDocument() {
        this._nodeStk.push(this._root);
    }
    
    public void endDocument() {
        this._nodeStk.pop();
    }
    
    public void startElement(final String namespace, final String localName, final String qName, final Attributes attrs) {
        final Element tmp = this._document.createElementNS(namespace, qName);
        if (this._namespaceDecls != null) {
            for (int nDecls = this._namespaceDecls.size(), i = 0; i < nDecls; ++i) {
                final String prefix = this._namespaceDecls.elementAt(i++);
                if (prefix == null || prefix.equals("")) {
                    tmp.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", this._namespaceDecls.elementAt(i));
                }
                else {
                    tmp.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, this._namespaceDecls.elementAt(i));
                }
            }
            this._namespaceDecls.clear();
        }
        for (int nattrs = attrs.getLength(), i = 0; i < nattrs; ++i) {
            if (attrs.getLocalName(i) == null) {
                tmp.setAttribute(attrs.getQName(i), attrs.getValue(i));
            }
            else {
                tmp.setAttributeNS(attrs.getURI(i), attrs.getQName(i), attrs.getValue(i));
            }
        }
        final Node last = this._nodeStk.peek();
        last.appendChild(tmp);
        this._nodeStk.push(tmp);
    }
    
    public void endElement(final String namespace, final String localName, final String qName) {
        this._nodeStk.pop();
    }
    
    public void startPrefixMapping(final String prefix, final String uri) {
        if (this._namespaceDecls == null) {
            this._namespaceDecls = new Vector(2);
        }
        this._namespaceDecls.addElement(prefix);
        this._namespaceDecls.addElement(uri);
    }
    
    public void endPrefixMapping(final String prefix) {
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) {
    }
    
    public void processingInstruction(final String target, final String data) {
        final Node last = this._nodeStk.peek();
        final ProcessingInstruction pi = this._document.createProcessingInstruction(target, data);
        if (pi != null) {
            last.appendChild(pi);
        }
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void skippedEntity(final String name) {
    }
    
    public void comment(final char[] ch, final int start, final int length) {
        final Node last = this._nodeStk.peek();
        final Comment comment = this._document.createComment(new String(ch, start, length));
        if (comment != null) {
            last.appendChild(comment);
        }
    }
    
    public void startCDATA() {
    }
    
    public void endCDATA() {
    }
    
    public void startEntity(final String name) {
    }
    
    public void endDTD() {
    }
    
    public void endEntity(final String name) {
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
    }
}
