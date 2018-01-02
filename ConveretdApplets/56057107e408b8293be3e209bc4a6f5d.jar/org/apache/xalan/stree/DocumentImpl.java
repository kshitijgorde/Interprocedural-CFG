// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.Text;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Element;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Comment;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentType;
import java.util.Hashtable;

public class DocumentImpl extends DocImpl
{
    DocumentTypeImpl m_docType;
    ElementImpl m_docElement;
    Hashtable m_idAttributes;
    
    DocumentImpl() {
        this.m_idAttributes = new Hashtable();
        this.setDoc(this);
    }
    
    public DocumentImpl(final int charBufSize) {
        super(charBufSize);
        this.m_idAttributes = new Hashtable();
        this.setDoc(this);
    }
    
    DocumentImpl(final SourceTreeHandler sth) {
        this.m_idAttributes = new Hashtable();
        this.setDoc(this);
        super.m_sourceTreeHandler = sth;
    }
    
    DocumentImpl(final DocumentType doctype) {
        this.m_idAttributes = new Hashtable();
        this.setDoc(this);
        if (doctype != null) {
            this.m_docType = (DocumentTypeImpl)doctype;
        }
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        if (this.getNodeType() != 11) {
            final short type = newChild.getNodeType();
            if (type == 1) {
                if (this.m_docElement != null) {
                    throw new DOMException((short)3, "DOM006 Hierarchy request error");
                }
                this.m_docElement = (ElementImpl)newChild;
            }
            else if (type == 10) {
                this.m_docType = (DocumentTypeImpl)newChild;
            }
        }
        return super.appendChild(newChild);
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        return super.createAttributeNS(namespaceURI, qualifiedName);
    }
    
    public CDATASection createCDATASection(final String data) throws DOMException {
        return new CDATASectionImpl(this, data);
    }
    
    public Comment createComment(final String data) {
        return new CommentImpl(this, data);
    }
    
    public DocumentFragment createDocumentFragment() {
        return new DocumentFragmentImpl();
    }
    
    public Element createElement(final String tagName) throws DOMException {
        return new ElementImpl(this, tagName);
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        return new ElementImplWithNS(this, namespaceURI, qualifiedName);
    }
    
    public ProcessingInstruction createProcessingInstruction(final String target, final String data) throws DOMException {
        return new ProcessingInstructionImpl(this, target, data);
    }
    
    public Text createTextNode(final String data) {
        return new TextImpl(this, data);
    }
    
    public DocumentType getDoctype() {
        return this.m_docType;
    }
    
    public Element getDocumentElement() {
        return this.m_docElement;
    }
    
    public Element getElementById(final String elementId) {
        Element elem = this.m_idAttributes.get(elementId);
        if (elem == null && !this.isComplete()) {
            synchronized (super.m_doc) {
                try {
                    while (!this.isComplete()) {
                        super.m_doc.wait(100L);
                        this.throwIfParseError();
                        elem = this.m_idAttributes.get(elementId);
                        if (elem != null) {
                            // monitorexit(super.m_doc)
                            return elem;
                        }
                    }
                }
                catch (InterruptedException ex) {
                    this.throwIfParseError();
                }
            }
            // monitorexit(super.m_doc)
            elem = this.m_idAttributes.get(elementId);
        }
        return elem;
    }
    
    public Hashtable getIDAttributes() {
        return this.m_idAttributes;
    }
    
    public String getLocalName() {
        return "#document";
    }
    
    public Node getNextSibling() {
        return null;
    }
    
    public String getNodeName() {
        return "#document";
    }
    
    public short getNodeType() {
        return 9;
    }
    
    public Node importNode(final Node importedNode, final boolean deep) throws DOMException {
        return super.importNode(importedNode, deep);
    }
    
    public void setDoctype(final DocumentType docType) {
        this.m_docType = (DocumentTypeImpl)docType;
    }
    
    public void setIDAttribute(final String id, final Element elem) {
        this.m_idAttributes.put(id, elem);
    }
}
