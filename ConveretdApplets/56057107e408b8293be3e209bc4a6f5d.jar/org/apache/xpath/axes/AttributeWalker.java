// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

public class AttributeWalker extends AxesWalker
{
    transient NamedNodeMap m_attributeList;
    transient int m_attrListPos;
    transient int m_nAttrs;
    
    public AttributeWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Node firstChild() {
        if (super.m_currentNode.getNodeType() == 1) {
            this.m_attrListPos = -1;
            this.m_attributeList = super.m_currentNode.getAttributes();
            if (this.m_attributeList != null) {
                this.m_nAttrs = this.m_attributeList.getLength();
            }
            else {
                this.m_nAttrs = -2;
            }
        }
        super.m_nextLevelAmount = 0;
        return this.nextSibling();
    }
    
    protected int getLevelMax() {
        return super.m_lpi.getDOMHelper().getLevel(super.m_root);
    }
    
    public Node nextSibling() {
        if (this.m_attributeList != null) {
            ++this.m_attrListPos;
            if (this.m_attrListPos < this.m_nAttrs) {
                return this.setCurrentIfNotNull(this.m_attributeList.item(this.m_attrListPos));
            }
            this.m_attributeList = null;
        }
        return null;
    }
    
    public void setRoot(final Node root) {
        this.m_attrListPos = -1;
        this.m_attributeList = null;
        this.m_nAttrs = -2;
        super.m_nextLevelAmount = 1;
        super.setRoot(root);
    }
}
