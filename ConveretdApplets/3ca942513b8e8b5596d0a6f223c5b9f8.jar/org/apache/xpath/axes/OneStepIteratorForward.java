// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.Expression;
import org.apache.xml.dtm.DTMAxisTraverser;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.OpMap;
import org.apache.xpath.compiler.Compiler;

public class OneStepIteratorForward extends ChildTestIterator
{
    static final long serialVersionUID = -1576936606178190566L;
    protected int m_axis;
    
    OneStepIteratorForward(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis);
        this.m_axis = -1;
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        this.m_axis = WalkerFactory.getAxisFromStep(compiler, firstStepPos);
    }
    
    public OneStepIteratorForward(final int axis) {
        super((DTMAxisTraverser)null);
        this.m_axis = -1;
        this.m_axis = axis;
        final int whatToShow = -1;
        this.initNodeTest(whatToShow);
    }
    
    public void setRoot(final int context, final Object environment) {
        super.setRoot(context, environment);
        super.m_traverser = super.m_cdtm.getAxisTraverser(this.m_axis);
    }
    
    protected int getNextNode() {
        return super.m_lastFetched = ((-1 == super.m_lastFetched) ? super.m_traverser.first(super.m_context) : super.m_traverser.next(super.m_context, super.m_lastFetched));
    }
    
    public int getAxis() {
        return this.m_axis;
    }
    
    public boolean deepEquals(final Expression expr) {
        return super.deepEquals(expr) && this.m_axis == ((OneStepIteratorForward)expr).m_axis;
    }
}
