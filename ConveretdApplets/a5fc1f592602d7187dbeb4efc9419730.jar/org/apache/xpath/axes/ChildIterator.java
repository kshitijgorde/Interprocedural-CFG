// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xml.dtm.DTM;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;

public class ChildIterator extends LocPathIterator
{
    ChildIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis, false);
    }
    
    public int asNode(final XPathContext xctxt) throws TransformerException {
        final int current = xctxt.getCurrentNode();
        final DTM dtm = xctxt.getDTM(current);
        return dtm.getFirstChild(current);
    }
    
    public int nextNode() {
        if (super.m_foundLast) {
            return -1;
        }
        final int next = super.m_lastFetched = ((-1 == super.m_lastFetched) ? super.m_cdtm.getFirstChild(super.m_context) : super.m_cdtm.getNextSibling(super.m_lastFetched));
        if (-1 != next) {
            ++super.m_pos;
            return next;
        }
        super.m_foundLast = true;
        return -1;
    }
    
    public int getAxis() {
        return 3;
    }
}
