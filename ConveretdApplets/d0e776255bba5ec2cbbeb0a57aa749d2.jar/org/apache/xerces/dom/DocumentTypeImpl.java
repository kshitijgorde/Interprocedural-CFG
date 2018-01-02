// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
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
    
    public DocumentTypeImpl(final DocumentImpl documentImpl, final String name) {
        super(documentImpl);
        this.name = name;
        this.entities = new NamedNodeMapImpl(this);
        this.notations = new NamedNodeMapImpl(this);
        this.elements = new NamedNodeMapImpl(this);
    }
    
    public DocumentTypeImpl(final DocumentImpl documentImpl, final String s, final String publicID, final String systemID) {
        this(documentImpl, s);
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
    
    public Node cloneNode(final boolean b) {
        final DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl)super.cloneNode(b);
        documentTypeImpl.entities = this.entities.cloneMap(documentTypeImpl);
        documentTypeImpl.notations = this.notations.cloneMap(documentTypeImpl);
        documentTypeImpl.elements = this.elements.cloneMap(documentTypeImpl);
        return documentTypeImpl;
    }
    
    void setOwnerDocument(final DocumentImpl documentImpl) {
        super.setOwnerDocument(documentImpl);
        this.entities.setOwnerDocument(documentImpl);
        this.notations.setOwnerDocument(documentImpl);
        this.elements.setOwnerDocument(documentImpl);
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
    
    public void setReadOnly(final boolean b, final boolean b2) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        super.setReadOnly(b, b2);
        this.elements.setReadOnly(b, true);
        this.entities.setReadOnly(b, true);
        this.notations.setReadOnly(b, true);
    }
    
    public NamedNodeMap getElements() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.elements;
    }
}
