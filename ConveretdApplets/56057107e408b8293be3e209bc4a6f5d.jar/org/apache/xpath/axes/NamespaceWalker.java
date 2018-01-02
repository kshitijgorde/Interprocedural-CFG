// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import java.util.Stack;

public class NamespaceWalker extends AxesWalker
{
    transient Stack m_namespaces;
    
    public NamespaceWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final NamespaceWalker clone = (NamespaceWalker)super.clone();
        if (this.m_namespaces != null) {
            clone.m_namespaces = (Stack)this.m_namespaces.clone();
        }
        return clone;
    }
    
    public Node firstChild() {
        super.m_nextLevelAmount = 0;
        return this.nextSibling();
    }
    
    protected int getLevelMax() {
        return super.m_lpi.getDOMHelper().getLevel(super.m_root);
    }
    
    public Node nextSibling() {
        final Node next = this.m_namespaces.isEmpty() ? null : this.m_namespaces.pop();
        return this.setCurrentIfNotNull(next);
    }
    
    public void setRoot(final Node root) {
        super.m_nextLevelAmount = 1;
        this.m_namespaces = new Stack();
        for (Node nsContext = root; nsContext != null; nsContext = nsContext.getParentNode()) {
            final NamedNodeMap attributeList = nsContext.getAttributes();
            if (attributeList != null) {
                final int nAttrs = attributeList.getLength();
                for (int i = nAttrs - 1; i >= 0; --i) {
                    final Node attr = attributeList.item(i);
                    if (super.m_lpi.getDOMHelper().isNamespaceNode(attr)) {
                        this.m_namespaces.push(attr);
                    }
                }
            }
        }
        super.setRoot(root);
    }
}
