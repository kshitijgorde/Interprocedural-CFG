// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.EntityReference;

public class EntityReferenceImpl extends ParentNode implements EntityReference
{
    static final long serialVersionUID = -7381452955687102062L;
    protected String name;
    protected String baseURI;
    
    public EntityReferenceImpl(final CoreDocumentImpl ownerDoc, final String name) {
        super(ownerDoc);
        this.name = name;
        this.isReadOnly(true);
        this.needsSyncChildren(true);
    }
    
    public short getNodeType() {
        return 5;
    }
    
    public String getNodeName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public Node cloneNode(final boolean deep) {
        final EntityReferenceImpl er = (EntityReferenceImpl)super.cloneNode(deep);
        er.setReadOnly(true, deep);
        return er;
    }
    
    public String getBaseURI() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final DocumentType doctype;
        final NamedNodeMap entities;
        if (this.baseURI == null && null != (doctype = this.getOwnerDocument().getDoctype()) && null != (entities = doctype.getEntities())) {
            final EntityImpl entDef = (EntityImpl)entities.getNamedItem(this.getNodeName());
            if (entDef != null) {
                return entDef.getBaseURI();
            }
        }
        return this.baseURI;
    }
    
    public void setBaseURI(final String uri) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.baseURI = uri;
    }
    
    protected void synchronizeChildren() {
        this.needsSyncChildren(false);
        final DocumentType doctype;
        final NamedNodeMap entities;
        if (null != (doctype = this.getOwnerDocument().getDoctype()) && null != (entities = doctype.getEntities())) {
            final EntityImpl entDef = (EntityImpl)entities.getNamedItem(this.getNodeName());
            if (entDef == null) {
                return;
            }
            this.isReadOnly(false);
            for (Node defkid = entDef.getFirstChild(); defkid != null; defkid = defkid.getNextSibling()) {
                final Node newkid = defkid.cloneNode(true);
                this.insertBefore(newkid, null);
            }
            this.setReadOnly(true, true);
        }
    }
    
    public void setReadOnly(final boolean readOnly, final boolean deep) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (deep) {
            if (this.needsSyncChildren()) {
                this.synchronizeChildren();
            }
            for (ChildNode mykid = super.firstChild; mykid != null; mykid = mykid.nextSibling) {
                mykid.setReadOnly(readOnly, true);
            }
        }
        this.isReadOnly(readOnly);
    }
}
