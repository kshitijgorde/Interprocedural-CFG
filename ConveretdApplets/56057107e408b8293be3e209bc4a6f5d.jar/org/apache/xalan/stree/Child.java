// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.apache.xml.utils.WrappedRuntimeException;
import org.w3c.dom.DOMException;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.apache.xpath.DOMOrder;
import org.apache.xml.utils.UnImplNode;

public class Child extends UnImplNode implements DOMOrder, SaxEventDispatch
{
    protected DocumentImpl m_doc;
    protected Parent m_parent;
    Child m_next;
    Child m_prev;
    short m_level;
    int m_uid;
    
    public Child(final DocumentImpl doc) {
        this.m_doc = doc;
    }
    
    public void dispatchCharactersEvent(final ContentHandler ch) throws SAXException {
    }
    
    public NamedNodeMap getAttributes() {
        return null;
    }
    
    DocumentImpl getDocumentImpl() {
        return this.m_doc;
    }
    
    public Node getFirstChild() {
        return null;
    }
    
    public Node getLastChild() {
        return null;
    }
    
    public short getLevel() {
        return this.m_level;
    }
    
    public String getLocalName() {
        return null;
    }
    
    public String getNamespaceURI() {
        return null;
    }
    
    public Node getNextSibling() {
        if (this.m_next != null) {
            return this.m_next;
        }
        if (!this.m_parent.m_isComplete) {
            synchronized (this.m_doc) {
                try {
                    while (!this.m_parent.isComplete()) {
                        this.m_doc.wait(100L);
                        this.throwIfParseError();
                        if (this.m_next != null) {
                            // monitorexit(this.m_doc)
                            return this.m_next;
                        }
                    }
                }
                catch (InterruptedException ex) {
                    this.throwIfParseError();
                }
            }
            // monitorexit(this.m_doc)
        }
        return this.m_next;
    }
    
    public Document getOwnerDocument() {
        return this.m_doc;
    }
    
    public Node getParentNode() {
        return this.m_parent;
    }
    
    public String getPrefix() {
        return null;
    }
    
    public Node getPreviousSibling() {
        return this.m_prev;
    }
    
    public String getTagName() {
        return null;
    }
    
    protected TransformerImpl getTransformer() {
        return this.m_doc.m_sourceTreeHandler.m_transformer;
    }
    
    public int getUid() {
        return this.m_uid;
    }
    
    public boolean hasChildNodes() {
        return false;
    }
    
    public boolean isComplete() {
        return true;
    }
    
    public boolean isNamespaceNode() {
        return false;
    }
    
    public boolean isSupported(final String feature, final String version) {
        return feature == "http://xml.apache.org/xalan/features/feed-events" || feature == "http://xml.apache.org/xpath/features/whitespace-pre-stripping";
    }
    
    public void setAttribute(final String name, final String value) throws DOMException {
    }
    
    public void setComplete(final boolean isComplete) {
    }
    
    protected void setDoc(final DocumentImpl doc) {
        this.m_doc = doc;
    }
    
    public void setLevel(final short level) {
        this.m_level = level;
    }
    
    protected void setParent(final Parent parent) {
        this.m_parent = parent;
    }
    
    protected void setUid(final int kIndex) {
        this.m_uid = kIndex;
    }
    
    protected void throwIfParseError() {
        if (this.m_doc.m_exceptionThrown != null) {
            this.throwParseError(this.m_doc.m_exceptionThrown);
        }
    }
    
    protected void throwParseError(final Exception e) {
        throw new WrappedRuntimeException(e);
    }
}
