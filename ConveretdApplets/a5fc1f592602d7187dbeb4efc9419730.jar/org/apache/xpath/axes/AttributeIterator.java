// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;

public class AttributeIterator extends ChildTestIterator
{
    AttributeIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis);
    }
    
    protected int getNextNode() {
        return super.m_lastFetched = ((-1 == super.m_lastFetched) ? super.m_cdtm.getFirstAttribute(super.m_context) : super.m_cdtm.getNextAttribute(super.m_lastFetched));
    }
    
    public int getAxis() {
        return 2;
    }
}
