// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Notation;

public class NotationImpl extends NodeImpl implements Notation
{
    static final long serialVersionUID = -764632195890658402L;
    protected String name;
    protected String publicId;
    protected String systemId;
    
    public NotationImpl(final DocumentImpl documentImpl, final String name) {
        super(documentImpl);
        this.name = name;
    }
    
    public short getNodeType() {
        return 12;
    }
    
    public String getNodeName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public String getPublicId() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.publicId;
    }
    
    public String getSystemId() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.systemId;
    }
    
    public void setPublicId(final String publicId) {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.publicId = publicId;
    }
    
    public void setSystemId(final String systemId) {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.systemId = systemId;
    }
}
