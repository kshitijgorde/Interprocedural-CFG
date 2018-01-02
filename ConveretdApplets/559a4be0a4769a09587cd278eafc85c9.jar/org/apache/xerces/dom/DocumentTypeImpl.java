// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentType;

public class DocumentTypeImpl extends ParentNode implements DocumentType
{
    static final long serialVersionUID = 7751299192316526485L;
    protected String name;
    protected NamedNodeMapImpl entities;
    protected NamedNodeMapImpl notations;
    protected NamedNodeMapImpl elements;
    protected String publicID;
    protected String systemID;
    protected String internalSubset;
    
    public DocumentTypeImpl(final CoreDocumentImpl ownerDocument, final String name) {
        super(ownerDocument);
        this.name = name;
        this.entities = new NamedNodeMapImpl(this);
        this.notations = new NamedNodeMapImpl(this);
        this.elements = new NamedNodeMapImpl(this);
    }
    
    public DocumentTypeImpl(final CoreDocumentImpl ownerDocument, final String qualifiedName, final String publicID, final String systemID) {
        this(ownerDocument, qualifiedName);
        this.publicID = publicID;
        this.systemID = systemID;
    }
    
    public String getPublicId() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.publicID;
    }
    
    public String getSystemId() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.systemID;
    }
    
    public void setInternalSubset(final String internalSubset) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.internalSubset = internalSubset;
    }
    
    public String getInternalSubset() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.internalSubset;
    }
    
    public short getNodeType() {
        return 10;
    }
    
    public String getNodeName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public Node cloneNode(final boolean deep) {
        final DocumentTypeImpl newnode = (DocumentTypeImpl)super.cloneNode(deep);
        newnode.entities = this.entities.cloneMap(newnode);
        newnode.notations = this.notations.cloneMap(newnode);
        newnode.elements = this.elements.cloneMap(newnode);
        return newnode;
    }
    
    public String getTextContent() throws DOMException {
        return null;
    }
    
    public void setTextContent(final String textContent) throws DOMException {
    }
    
    void setOwnerDocument(final CoreDocumentImpl doc) {
        super.setOwnerDocument(doc);
        this.entities.setOwnerDocument(doc);
        this.notations.setOwnerDocument(doc);
        this.elements.setOwnerDocument(doc);
    }
    
    public String getName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public NamedNodeMap getEntities() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.entities;
    }
    
    public NamedNodeMap getNotations() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.notations;
    }
    
    public void setReadOnly(final boolean readOnly, final boolean deep) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        super.setReadOnly(readOnly, deep);
        this.elements.setReadOnly(readOnly, true);
        this.entities.setReadOnly(readOnly, true);
        this.notations.setReadOnly(readOnly, true);
    }
    
    public NamedNodeMap getElements() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.elements;
    }
}
