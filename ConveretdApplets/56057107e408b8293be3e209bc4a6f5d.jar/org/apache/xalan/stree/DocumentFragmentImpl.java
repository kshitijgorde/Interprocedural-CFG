// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.DocumentFragment;

public class DocumentFragmentImpl extends DocumentImpl implements DocumentFragment
{
    public DocumentFragmentImpl() {
    }
    
    public DocumentFragmentImpl(final int charBufSize) {
        super(charBufSize);
    }
    
    public String getLocalName() {
        return "#document-fragment";
    }
    
    public String getNodeName() {
        return "#document-fragment";
    }
    
    public short getNodeType() {
        return 11;
    }
}
