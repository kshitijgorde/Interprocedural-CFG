// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xml.dtm.DTM;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xalan.templates.ElemNumber;
import org.apache.xpath.NodeSetDTM;

public class Counter
{
    static final int MAXCOUNTNODES = 500;
    int m_countNodesStartCount;
    NodeSetDTM m_countNodes;
    int m_fromNode;
    ElemNumber m_numberElem;
    int m_countResult;
    
    Counter(final ElemNumber numberElem, final NodeSetDTM countNodes) throws TransformerException {
        this.m_countNodesStartCount = 0;
        this.m_fromNode = -1;
        this.m_countNodes = countNodes;
        this.m_numberElem = numberElem;
    }
    
    int getPreviouslyCounted(final XPathContext support, final int node) {
        final int n = this.m_countNodes.size();
        this.m_countResult = 0;
        for (int i = n - 1; i >= 0; --i) {
            final int countedNode = this.m_countNodes.elementAt(i);
            if (node == countedNode) {
                this.m_countResult = i + 1 + this.m_countNodesStartCount;
                break;
            }
            final DTM dtm = support.getDTM(countedNode);
            if (dtm.isNodeAfter(countedNode, node)) {
                break;
            }
        }
        return this.m_countResult;
    }
    
    int getLast() {
        final int size = this.m_countNodes.size();
        return (size > 0) ? this.m_countNodes.elementAt(size - 1) : -1;
    }
}
