// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.w3c.dom.Node;
import org.apache.xalan.templates.ElemNumber;
import org.apache.xpath.XPathContext;
import org.apache.xpath.NodeSet;
import java.util.Hashtable;

public class CountersTable extends Hashtable
{
    private transient NodeSet m_newFound;
    transient int m_countersMade;
    
    public CountersTable() {
        this.m_newFound = new NodeSet();
        this.m_countersMade = 0;
    }
    
    void appendBtoFList(final NodeSet flist, final NodeSet blist) {
        final int n = blist.size();
        for (int i = n - 1; i >= 0; --i) {
            flist.addElement(blist.item(i));
        }
    }
    
    public int countNode(final XPathContext support, final ElemNumber numberElem, final Node node) throws TransformerException {
        int count = 0;
        final Vector counters = this.getCounters(numberElem);
        final int nCounters = counters.size();
        Node target = numberElem.getTargetNode(support, node);
        if (target != null) {
            for (int i = 0; i < nCounters; ++i) {
                final Counter counter = counters.elementAt(i);
                count = counter.getPreviouslyCounted(support, target);
                if (count > 0) {
                    return count;
                }
            }
            count = 0;
            while (target != null) {
                if (count != 0) {
                    for (int j = 0; j < nCounters; ++j) {
                        final Counter counter2 = counters.elementAt(j);
                        final int cacheLen = counter2.m_countNodes.size();
                        if (cacheLen > 0 && counter2.m_countNodes.elementAt(cacheLen - 1).equals(target)) {
                            count += cacheLen + counter2.m_countNodesStartCount;
                            if (cacheLen > 0) {
                                this.appendBtoFList(counter2.m_countNodes, this.m_newFound);
                            }
                            this.m_newFound.removeAllElements();
                            return count;
                        }
                    }
                }
                this.m_newFound.addElement(target);
                ++count;
                target = numberElem.getPreviousNode(support, target);
            }
            final Counter counter = new Counter(numberElem);
            ++this.m_countersMade;
            this.appendBtoFList(counter.m_countNodes, this.m_newFound);
            this.m_newFound.removeAllElements();
            counters.addElement(counter);
        }
        return count;
    }
    
    Vector getCounters(final ElemNumber numberElem) {
        final Vector counters = this.get(numberElem);
        return (counters == null) ? this.putElemNumber(numberElem) : counters;
    }
    
    Vector putElemNumber(final ElemNumber numberElem) {
        final Vector counters = new Vector();
        this.put(numberElem, counters);
        return counters;
    }
}
