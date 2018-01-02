// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Notation;

public class NotationImpl extends NodeImpl implements Notation
{
    static final long serialVersionUID = -764632195890658402L;
    protected String publicId;
    protected String systemId;
    
    public NotationImpl(final DocumentImpl documentImpl, final String s) {
        super(documentImpl, s, null);
    }
    
    public short getNodeType() {
        return 12;
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
    
    public void setPublicId(final String publicId) {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, null);
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        this.publicId = publicId;
    }
    
    public void setSystemId(final String systemId) {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, null);
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        this.systemId = systemId;
    }
}
