// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;

public abstract class ChildNode extends NodeImpl
{
    static final long serialVersionUID = -6112455738802414002L;
    protected ChildNode previousSibling;
    protected ChildNode nextSibling;
    
    protected ChildNode(final DocumentImpl documentImpl) {
        super(documentImpl);
    }
    
    public ChildNode() {
    }
    
    public Node cloneNode(final boolean b) {
        final ChildNode childNode = (ChildNode)super.cloneNode(b);
        childNode.previousSibling = null;
        childNode.nextSibling = null;
        childNode.isFirstChild(false);
        return childNode;
    }
    
    public Node getParentNode() {
        return this.isOwned() ? super.ownerNode : null;
    }
    
    final NodeImpl parentNode() {
        return this.isOwned() ? super.ownerNode : null;
    }
    
    public Node getNextSibling() {
        return this.nextSibling;
    }
    
    public Node getPreviousSibling() {
        return this.isFirstChild() ? null : this.previousSibling;
    }
    
    final ChildNode previousSibling() {
        return this.isFirstChild() ? null : this.previousSibling;
    }
}
