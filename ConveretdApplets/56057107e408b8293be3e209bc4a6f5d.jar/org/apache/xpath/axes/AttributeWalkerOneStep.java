// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.patterns.NodeTest;
import org.apache.xpath.patterns.NodeTestFilter;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

public class AttributeWalkerOneStep extends AxesWalker
{
    transient NamedNodeMap m_attributeList;
    transient int m_attrListPos;
    transient int m_nAttrs;
    
    public AttributeWalkerOneStep(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Node nextNode() {
        if (super.m_isFresh) {
            super.m_isFresh = false;
        }
        final Node current = this.getCurrentNode();
        if (current.isSupported("NodeTestFilter", "1.0")) {
            ((NodeTestFilter)current).setNodeTest(this);
        }
        Node next = null;
        while (this.m_attributeList != null) {
            ++this.m_attrListPos;
            if (this.m_attrListPos < this.m_nAttrs) {
                next = this.m_attributeList.item(this.m_attrListPos);
                if (next != null) {
                    super.m_currentNode = next;
                }
                if (this.acceptNode(next) == 1) {
                    break;
                }
                continue;
            }
            else {
                next = null;
                this.m_attributeList = null;
            }
        }
        if (next == null) {
            super.m_isDone = true;
        }
        return next;
    }
    
    public void setRoot(final Node root) {
        super.setRoot(root);
        if (root.getNodeType() == 1) {
            this.m_attrListPos = -1;
            this.m_attributeList = super.m_currentNode.getAttributes();
            if (this.m_attributeList != null) {
                this.m_nAttrs = this.m_attributeList.getLength();
            }
            else {
                this.m_nAttrs = -2;
            }
        }
    }
}
