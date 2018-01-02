// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xalan.templates.ElemNumber;
import org.w3c.dom.Node;
import org.apache.xpath.NodeSet;

public class Counter
{
    static final int MAXCOUNTNODES = 500;
    int m_countNodesStartCount;
    NodeSet m_countNodes;
    Node m_fromNode;
    ElemNumber m_numberElem;
    int m_countResult;
    
    Counter(final ElemNumber numberElem) throws TransformerException {
        this.m_countNodesStartCount = 0;
        this.m_countNodes = new NodeSet();
        this.m_fromNode = null;
        this.m_numberElem = numberElem;
    }
    
    Counter(final ElemNumber numberElem, final NodeSet countNodes) throws TransformerException {
        this.m_countNodesStartCount = 0;
        this.m_countNodes = new NodeSet();
        this.m_fromNode = null;
        this.m_countNodes = countNodes;
        this.m_numberElem = numberElem;
    }
    
    Node getLast() {
        final int size = this.m_countNodes.size();
        return (size > 0) ? this.m_countNodes.elementAt(size - 1) : null;
    }
    
    int getPreviouslyCounted(final XPathContext support, final Node node) {
        final int n = this.m_countNodes.size();
        this.m_countResult = 0;
        for (int i = n - 1; i >= 0; --i) {
            final Node countedNode = this.m_countNodes.elementAt(i);
            if (node.equals(countedNode)) {
                this.m_countResult = i + 1 + this.m_countNodesStartCount;
                break;
            }
            if (support.getDOMHelper().isNodeAfter(countedNode, node)) {
                break;
            }
        }
        return this.m_countResult;
    }
}
