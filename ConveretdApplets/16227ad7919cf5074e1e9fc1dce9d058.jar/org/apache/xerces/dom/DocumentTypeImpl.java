// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

public class DocumentTypeImpl extends NodeImpl implements DocumentType
{
    static final long serialVersionUID = 7751299192316526485L;
    protected NamedNodeMapImpl entities;
    protected NamedNodeMapImpl notations;
    protected NamedNodeMapImpl elements;
    protected String publicID;
    protected String systemID;
    
    public DocumentTypeImpl(final DocumentImpl documentImpl, final String s) {
        super(documentImpl, s, null);
        this.entities = new NamedNodeMapImpl(documentImpl, null);
        this.notations = new NamedNodeMapImpl(documentImpl, null);
        this.elements = new NamedNodeMapImpl(documentImpl, null);
    }
    
    public DocumentTypeImpl(final DocumentImpl documentImpl, final String s, final String publicID, final String systemID) {
        this(documentImpl, s);
        this.publicID = publicID;
        this.systemID = systemID;
    }
    
    public String getPublicID() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.publicID;
    }
    
    public String getSystemID() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.systemID;
    }
    
    public short getNodeType() {
        return 10;
    }
    
    public void setNodeValue(final String s) throws DOMException {
        throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
    }
    
    public Node cloneNode(final boolean b) {
        final DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl)super.cloneNode(b);
        documentTypeImpl.entities = this.entities.cloneMap();
        documentTypeImpl.notations = this.notations.cloneMap();
        documentTypeImpl.elements = this.elements.cloneMap();
        return documentTypeImpl;
    }
    
    public String getName() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return super.name;
    }
    
    public NamedNodeMap getEntities() {
        if (super.syncChildren) {
            this.synchronizeChildren();
        }
        return this.entities;
    }
    
    public NamedNodeMap getNotations() {
        if (super.syncChildren) {
            this.synchronizeChildren();
        }
        return this.notations;
    }
    
    public void setReadOnly(final boolean b, final boolean b2) {
        if (super.syncChildren) {
            this.synchronizeChildren();
        }
        this.setReadOnly(b, b2);
        this.elements.setReadOnly(b, true);
        this.entities.setReadOnly(b, true);
        this.notations.setReadOnly(b, true);
    }
    
    public NamedNodeMap getElements() {
        if (super.syncChildren) {
            this.synchronizeChildren();
        }
        return this.elements;
    }
}
