// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.XPathContext;
import org.apache.xml.dtm.DTM;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;

public class SelfIteratorNoPredicate extends LocPathIterator
{
    SelfIteratorNoPredicate(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis, false);
    }
    
    public SelfIteratorNoPredicate() throws TransformerException {
        super((PrefixResolver)null);
    }
    
    public int nextNode() {
        if (super.m_foundLast) {
            return -1;
        }
        final DTM dtm = super.m_cdtm;
        final int next = super.m_lastFetched = ((-1 == super.m_lastFetched) ? super.m_context : -1);
        if (-1 != next) {
            ++super.m_pos;
            return next;
        }
        super.m_foundLast = true;
        return -1;
    }
    
    public int asNode(final XPathContext xctxt) throws TransformerException {
        return xctxt.getCurrentNode();
    }
    
    public int getLastPos(final XPathContext xctxt) {
        return 1;
    }
}
