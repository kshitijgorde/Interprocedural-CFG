// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.transform.TransformerException;
import org.apache.xpath.XPathContext;
import java.util.Vector;
import org.apache.xalan.templates.ElemNumber;
import org.apache.xpath.NodeSetDTM;
import java.util.Hashtable;

public class CountersTable extends Hashtable
{
    private transient NodeSetDTM m_newFound;
    transient int m_countersMade;
    
    public CountersTable() {
        this.m_countersMade = 0;
    }
    
    Vector getCounters(final ElemNumber numberElem) {
        final Vector counters = this.get(numberElem);
        return (null == counters) ? this.putElemNumber(numberElem) : counters;
    }
    
    Vector putElemNumber(final ElemNumber numberElem) {
        final Vector counters = new Vector();
        this.put(numberElem, counters);
        return counters;
    }
    
    void appendBtoFList(final NodeSetDTM flist, final NodeSetDTM blist) {
        final int n = blist.size();
        for (int i = n - 1; i >= 0; --i) {
            flist.addElement(blist.item(i));
        }
    }
    
    public int countNode(final XPathContext support, final ElemNumber numberElem, final int node) throws TransformerException {
        int count = 0;
        final Vector counters = this.getCounters(numberElem);
        final int nCounters = counters.size();
        int target = numberElem.getTargetNode(support, node);
        if (-1 != target) {
            for (int i = 0; i < nCounters; ++i) {
                final Counter counter = counters.elementAt(i);
                count = counter.getPreviouslyCounted(support, target);
                if (count > 0) {
                    return count;
                }
            }
            count = 0;
            if (this.m_newFound == null) {
                this.m_newFound = new NodeSetDTM(support.getDTMManager());
            }
            while (-1 != target) {
                if (0 != count) {
                    for (int j = 0; j < nCounters; ++j) {
                        final Counter counter2 = counters.elementAt(j);
                        final int cacheLen = counter2.m_countNodes.size();
                        if (cacheLen > 0 && counter2.m_countNodes.elementAt(cacheLen - 1) == target) {
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
            final Counter counter = new Counter(numberElem, new NodeSetDTM(support.getDTMManager()));
            ++this.m_countersMade;
            this.appendBtoFList(counter.m_countNodes, this.m_newFound);
            this.m_newFound.removeAllElements();
            counters.addElement(counter);
        }
        return count;
    }
}
