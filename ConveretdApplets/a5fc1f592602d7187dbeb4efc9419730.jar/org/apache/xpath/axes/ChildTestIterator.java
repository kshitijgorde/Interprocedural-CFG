// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xml.dtm.DTMIterator;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;
import org.apache.xml.dtm.DTMAxisTraverser;

public class ChildTestIterator extends BasicTestIterator
{
    protected transient DTMAxisTraverser m_traverser;
    
    ChildTestIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis);
    }
    
    public ChildTestIterator(final DTMAxisTraverser traverser) {
        super((PrefixResolver)null);
        this.m_traverser = traverser;
    }
    
    protected int getNextNode() {
        return super.m_lastFetched = ((-1 == super.m_lastFetched) ? this.m_traverser.first(super.m_context) : this.m_traverser.next(super.m_context, super.m_lastFetched));
    }
    
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        final ChildTestIterator clone = (ChildTestIterator)super.cloneWithReset();
        clone.m_traverser = this.m_traverser;
        return clone;
    }
    
    public void setRoot(final int context, final Object environment) {
        super.setRoot(context, environment);
        this.m_traverser = super.m_cdtm.getAxisTraverser(3);
    }
    
    public int getAxis() {
        return 3;
    }
    
    public void detach() {
        if (super.m_allowDetach) {
            this.m_traverser = null;
            super.detach();
        }
    }
}
