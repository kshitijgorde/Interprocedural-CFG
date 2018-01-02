// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xpath.axes.ContextNodeList;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.Node;
import org.apache.xml.utils.NodeVector;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import java.text.CollationKey;
import java.text.NumberFormat;
import java.util.Vector;
import org.apache.xpath.XPathContext;

public class NodeSorter
{
    XPathContext m_execContext;
    Vector m_keys;
    NumberFormat m_formatter;
    
    public NodeSorter(final XPathContext p) {
        this.m_formatter = NumberFormat.getNumberInstance();
        this.m_execContext = p;
    }
    
    int compare(final NodeCompareElem n1, final NodeCompareElem n2, final int kIndex, final XPathContext support) throws TransformerException {
        int result = 0;
        final NodeSortKey k = this.m_keys.elementAt(kIndex);
        if (k.m_treatAsNumbers) {
            double n1Num;
            double n2Num;
            if (kIndex == 0) {
                n1Num = (double)n1.m_key1Value;
                n2Num = (double)n2.m_key1Value;
            }
            else if (kIndex == 1) {
                n1Num = (double)n1.m_key2Value;
                n2Num = (double)n2.m_key2Value;
            }
            else {
                final XObject r1 = k.m_selectPat.execute(this.m_execContext, n1.m_node, k.m_namespaceContext);
                final XObject r2 = k.m_selectPat.execute(this.m_execContext, n2.m_node, k.m_namespaceContext);
                double d = r1.num();
                n1Num = (Double.isNaN(d) ? 0.0 : d);
                d = r2.num();
                n2Num = (Double.isNaN(d) ? 0.0 : d);
            }
            if (n1Num == n2Num && kIndex + 1 < this.m_keys.size()) {
                result = this.compare(n1, n2, kIndex + 1, support);
            }
            else {
                final double diff = n1Num - n2Num;
                result = ((diff < 0.0) ? (k.m_descending ? 1 : -1) : ((diff > 0.0) ? (k.m_descending ? -1 : 1) : 0));
            }
        }
        else {
            CollationKey n1String;
            CollationKey n2String;
            if (kIndex == 0) {
                n1String = (CollationKey)n1.m_key1Value;
                n2String = (CollationKey)n2.m_key1Value;
            }
            else if (kIndex == 1) {
                n1String = (CollationKey)n1.m_key2Value;
                n2String = (CollationKey)n2.m_key2Value;
            }
            else {
                final XObject r3 = k.m_selectPat.execute(this.m_execContext, n1.m_node, k.m_namespaceContext);
                final XObject r4 = k.m_selectPat.execute(this.m_execContext, n2.m_node, k.m_namespaceContext);
                n1String = k.m_col.getCollationKey(r3.str());
                n2String = k.m_col.getCollationKey(r4.str());
            }
            result = n1String.compareTo(n2String);
            if (k.m_caseOrderUpper) {
                final String tempN1 = n1String.getSourceString().toLowerCase();
                final String tempN2 = n2String.getSourceString().toLowerCase();
                if (tempN1.equals(tempN2)) {
                    result = ((result == 0) ? 0 : (-result));
                }
            }
            if (k.m_descending) {
                result = -result;
            }
        }
        if (result == 0 && kIndex + 1 < this.m_keys.size()) {
            result = this.compare(n1, n2, kIndex + 1, support);
        }
        if (result == 0) {
            result = (support.getDOMHelper().isNodeAfter(n1.m_node, n2.m_node) ? -1 : 1);
        }
        return result;
    }
    
    void mergesort(final Vector a, final Vector b, final int l, final int r, final XPathContext support) throws TransformerException {
        if (r - l > 0) {
            final int m = (r + l) / 2;
            this.mergesort(a, b, l, m, support);
            this.mergesort(a, b, m + 1, r, support);
            for (int i = m; i >= l; --i) {
                if (i >= b.size()) {
                    b.insertElementAt(a.elementAt(i), i);
                }
                else {
                    b.setElementAt(a.elementAt(i), i);
                }
            }
            int i = l;
            for (int j = m + 1; j <= r; ++j) {
                if (r + m + 1 - j >= b.size()) {
                    b.insertElementAt(a.elementAt(j), r + m + 1 - j);
                }
                else {
                    b.setElementAt(a.elementAt(j), r + m + 1 - j);
                }
            }
            int j = r;
            for (int k = l; k <= r; ++k) {
                int compVal;
                if (i == j) {
                    compVal = -1;
                }
                else {
                    compVal = this.compare(b.elementAt(i), b.elementAt(j), 0, support);
                }
                if (compVal < 0) {
                    a.setElementAt(b.elementAt(i), k);
                    ++i;
                }
                else if (compVal > 0) {
                    a.setElementAt(b.elementAt(j), k);
                    --j;
                }
            }
        }
    }
    
    public void sort(final NodeVector v, final Vector keys, final XPathContext support) throws TransformerException {
        this.m_keys = keys;
        final int n = v.size();
        final Vector nodes = new Vector();
        for (int i = 0; i < n; ++i) {
            final NodeCompareElem elem = new NodeCompareElem(v.elementAt(i));
            nodes.addElement(elem);
        }
        final Vector scratchVector = new Vector();
        this.mergesort(nodes, scratchVector, 0, n - 1, support);
        for (int j = 0; j < n; ++j) {
            v.setElementAt(nodes.elementAt(j).m_node, j);
        }
    }
    
    private void swap(final Vector v, final int i, final int j) {
        final Node node = v.elementAt(i);
        v.setElementAt(v.elementAt(j), i);
        v.setElementAt(node, j);
    }
    
    class NodeCompareElem
    {
        Node m_node;
        int maxkey;
        Object m_key1Value;
        Object m_key2Value;
        
        NodeCompareElem(final Node node) throws TransformerException {
            this.maxkey = 2;
            boolean tryNextKey = true;
            this.m_node = node;
            if (!NodeSorter.this.m_keys.isEmpty()) {
                final NodeSortKey k1 = NodeSorter.this.m_keys.elementAt(0);
                final XObject r = k1.m_selectPat.execute(NodeSorter.this.m_execContext, node, k1.m_namespaceContext);
                if (r == null) {
                    tryNextKey = false;
                }
                if (k1.m_treatAsNumbers) {
                    final double d = r.num();
                    this.m_key1Value = new Double(Double.isNaN(d) ? 0.0 : d);
                }
                else {
                    this.m_key1Value = k1.m_col.getCollationKey(r.str());
                }
                if (r.getType() == 4) {
                    final NodeIterator ni = (NodeIterator)r.object();
                    if (ni instanceof ContextNodeList) {
                        tryNextKey = (((ContextNodeList)ni).getCurrentNode() != null);
                    }
                }
                if (NodeSorter.this.m_keys.size() > 1) {
                    final NodeSortKey k2 = NodeSorter.this.m_keys.elementAt(1);
                    if (!tryNextKey) {
                        if (k2.m_treatAsNumbers) {
                            this.m_key2Value = new Double(0.0);
                        }
                        else {
                            this.m_key2Value = k2.m_col.getCollationKey("");
                        }
                    }
                    else {
                        final XObject r2 = k2.m_selectPat.execute(NodeSorter.this.m_execContext, node, k2.m_namespaceContext);
                        if (k2.m_treatAsNumbers) {
                            final double d = r2.num();
                            this.m_key2Value = new Double(Double.isNaN(d) ? 0.0 : d);
                        }
                        else {
                            this.m_key2Value = k2.m_col.getCollationKey(r2.str());
                        }
                    }
                }
            }
        }
    }
}
