// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.EntityReference;

public class EntityReferenceImpl extends ParentNode implements EntityReference
{
    static final long serialVersionUID = -7381452955687102062L;
    protected String name;
    
    public EntityReferenceImpl(final DocumentImpl documentImpl, final String name) {
        super(documentImpl);
        this.name = name;
        this.isReadOnly(true);
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
    
    public NodeList getChildNodes() {
        this.synchronize();
        return super.getChildNodes();
    }
    
    public Node getFirstChild() {
        this.synchronize();
        return super.getFirstChild();
    }
    
    public Node getLastChild() {
        this.synchronize();
        return super.getLastChild();
    }
    
    public int getLength() {
        this.synchronize();
        return super.getLength();
    }
    
    public boolean hasChildNodes() {
        this.synchronize();
        return super.hasChildNodes();
    }
    
    public Node item(final int n) {
        this.synchronize();
        return super.item(n);
    }
    
    protected void synchronize() {
        if (super.firstChild != null) {
            return;
        }
        final DocumentType doctype;
        final NamedNodeMap entities;
        if (null != (doctype = this.getOwnerDocument().getDoctype()) && null != (entities = doctype.getEntities())) {
            final EntityImpl entityImpl = (EntityImpl)entities.getNamedItem(this.getNodeName());
            if (entityImpl == null) {
                return;
            }
            this.isReadOnly(false);
            for (Node node = entityImpl.getFirstChild(); node != null; node = node.getNextSibling()) {
                this.insertBefore(node.cloneNode(true), null);
            }
            this.setReadOnly(true, true);
        }
    }
}
