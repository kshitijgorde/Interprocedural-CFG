// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Entity;

public class EntityImpl extends NodeImpl implements Entity
{
    static final long serialVersionUID = -3575760943444303423L;
    protected String publicId;
    protected String systemId;
    protected String notationName;
    
    public EntityImpl(final DocumentImpl documentImpl, final String s) {
        super(documentImpl, s, null);
    }
    
    public short getNodeType() {
        return 6;
    }
    
    public String getNodeValue() {
        return null;
    }
    
    public void setNodeValue(final String s) throws DOMException {
        throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
    }
    
    public String getPublicId() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.publicId;
    }
    
    public String getSystemId() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.systemId;
    }
    
    public String getNotationName() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.notationName;
    }
    
    public void setPublicId(final String publicId) {
        if (super.syncData) {
            this.synchronizeData();
        }
        this.publicId = publicId;
    }
    
    public void setSystemId(final String systemId) {
        if (super.syncData) {
            this.synchronizeData();
        }
        this.systemId = systemId;
    }
    
    public void setNotationName(final String notationName) {
        if (super.syncData) {
            this.synchronizeData();
        }
        this.notationName = notationName;
    }
}
