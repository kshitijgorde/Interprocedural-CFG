// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentFragment;

public class DocumentFragmentImpl extends NodeImpl implements DocumentFragment
{
    static final long serialVersionUID = -7596449967279236746L;
    
    public DocumentFragmentImpl(final DocumentImpl documentImpl) {
        super(documentImpl, null, null);
    }
    
    public DocumentFragmentImpl() {
    }
    
    public short getNodeType() {
        return 11;
    }
    
    public String getNodeName() {
        return "#document-fragment";
    }
    
    public void setNodeValue(final String s) throws DOMException {
        throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
    }
}
