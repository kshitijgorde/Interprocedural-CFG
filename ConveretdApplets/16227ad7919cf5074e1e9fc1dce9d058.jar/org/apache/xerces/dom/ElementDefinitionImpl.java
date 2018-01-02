// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

public class ElementDefinitionImpl extends NodeImpl
{
    static final long serialVersionUID = -8373890672670022714L;
    protected NamedNodeMapImpl attributes;
    
    public ElementDefinitionImpl(final DocumentImpl documentImpl, final String s) {
        super(documentImpl, s, null);
        this.attributes = new NamedNodeMapImpl(documentImpl, null);
    }
    
    public short getNodeType() {
        return -1;
    }
    
    public Node cloneNode(final boolean b) {
        final ElementDefinitionImpl elementDefinitionImpl = (ElementDefinitionImpl)super.cloneNode(b);
        elementDefinitionImpl.attributes = this.attributes.cloneMap();
        return elementDefinitionImpl;
    }
    
    public NamedNodeMap getAttributes() {
        if (super.syncChildren) {
            this.synchronizeChildren();
        }
        return this.attributes;
    }
}
