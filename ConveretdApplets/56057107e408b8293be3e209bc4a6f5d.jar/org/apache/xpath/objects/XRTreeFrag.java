// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.w3c.dom.DOMException;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.Node;
import org.apache.xpath.DOMHelper;
import javax.xml.transform.TransformerException;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.DocumentFragment;

public class XRTreeFrag extends XObject
{
    public XRTreeFrag(final DocumentFragment frag) {
        super(frag);
    }
    
    public NodeIterator asNodeIterator() {
        if (super.m_obj instanceof NodeIterator) {
            return (NodeIterator)super.m_obj;
        }
        return new NodeIteratorWrapper(this.rtree());
    }
    
    public boolean bool() {
        return true;
    }
    
    public NodeList convertToNodeset() {
        if (super.m_obj instanceof NodeList) {
            return (NodeList)super.m_obj;
        }
        return null;
    }
    
    public boolean equals(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.equals(this);
        }
        if (obj2.getType() == 1) {
            return this.bool() == obj2.bool();
        }
        if (obj2.getType() == 2) {
            return this.num() == obj2.num();
        }
        if (obj2.getType() == 4) {
            return this.str().equals(obj2.str());
        }
        if (obj2.getType() == 3) {
            return this.str().equals(obj2.str());
        }
        if (obj2.getType() == 5) {
            return this.str().equals(obj2.str());
        }
        return super.equals(obj2);
    }
    
    public int getType() {
        return 5;
    }
    
    public String getTypeString() {
        return "#RTREEFRAG";
    }
    
    public double num() {
        final String s = DOMHelper.getNodeData((Node)super.m_obj);
        double result;
        if (s != null) {
            result = XString.castToNum(s.trim());
        }
        else {
            result = Double.NaN;
        }
        return result;
    }
    
    public DocumentFragment rtree() {
        return (DocumentFragment)super.m_obj;
    }
    
    public String str() {
        final String str = DOMHelper.getNodeData((Node)super.m_obj);
        return (str == null) ? "" : str;
    }
    
    class NodeIteratorWrapper implements NodeIterator
    {
        private int m_pos;
        private DocumentFragment m_docFrag;
        
        NodeIteratorWrapper(final DocumentFragment df) {
            this.m_pos = -1;
            this.m_docFrag = df;
        }
        
        public void detach() {
        }
        
        public boolean getExpandEntityReferences() {
            return true;
        }
        
        public NodeFilter getFilter() {
            return null;
        }
        
        public Node getRoot() {
            return null;
        }
        
        public int getWhatToShow() {
            return -1;
        }
        
        public Node nextNode() throws DOMException {
            if (this.m_pos == -1) {
                this.m_pos = 0;
                return this.m_docFrag;
            }
            return null;
        }
        
        public Node previousNode() throws DOMException {
            if (this.m_pos == 0) {
                this.m_pos = -1;
                return this.m_docFrag;
            }
            return null;
        }
    }
}
