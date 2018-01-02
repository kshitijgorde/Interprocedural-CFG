// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DocumentFragment;

public class DocumentFragmentImpl extends ParentNode implements DocumentFragment
{
    static final long serialVersionUID = -7596449967279236746L;
    
    public DocumentFragmentImpl(final DocumentImpl documentImpl) {
        super(documentImpl);
    }
    
    public DocumentFragmentImpl() {
    }
    
    public short getNodeType() {
        return 11;
    }
    
    public String getNodeName() {
        return "#document-fragment";
    }
}
