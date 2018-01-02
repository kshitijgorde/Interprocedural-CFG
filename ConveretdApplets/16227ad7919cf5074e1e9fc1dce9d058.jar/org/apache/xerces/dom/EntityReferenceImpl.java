// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.EntityReference;

public class EntityReferenceImpl extends NodeImpl implements EntityReference
{
    static final long serialVersionUID = -7381452955687102062L;
    
    public EntityReferenceImpl(final DocumentImpl documentImpl, final String s) {
        super(documentImpl, s, null);
    }
    
    public short getNodeType() {
        return 5;
    }
    
    public void setNodeValue(final String s) throws DOMException {
        throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
    }
    
    public void setReadOnly(final boolean b, final boolean b2) {
        super.setReadOnly(b, b2);
    }
}
