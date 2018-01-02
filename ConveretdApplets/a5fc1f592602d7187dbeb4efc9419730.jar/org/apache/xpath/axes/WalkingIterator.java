// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.ExpressionNode;
import org.apache.xpath.Expression;
import org.apache.xpath.XPathVisitor;
import org.apache.xpath.VariableStack;
import java.util.Vector;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.OpMap;
import org.apache.xpath.compiler.Compiler;
import org.apache.xpath.ExpressionOwner;

public class WalkingIterator extends LocPathIterator implements ExpressionOwner
{
    protected AxesWalker m_lastUsedWalker;
    protected AxesWalker m_firstWalker;
    
    WalkingIterator(final Compiler compiler, final int opPos, final int analysis, final boolean shouldLoadWalkers) throws TransformerException {
        super(compiler, opPos, analysis, shouldLoadWalkers);
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        if (shouldLoadWalkers) {
            this.m_firstWalker = WalkerFactory.loadWalkers(this, compiler, firstStepPos, 0);
            this.m_lastUsedWalker = this.m_firstWalker;
        }
    }
    
    public WalkingIterator(final PrefixResolver nscontext) {
        super(nscontext);
    }
    
    public int getAnalysisBits() {
        int bits = 0;
        if (null != this.m_firstWalker) {
            for (AxesWalker walker = this.m_firstWalker; null != walker; walker = walker.getNextWalker()) {
                final int bit = walker.getAnalysisBits();
                bits |= bit;
            }
        }
        return bits;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final WalkingIterator clone = (WalkingIterator)super.clone();
        if (null != this.m_firstWalker) {
            clone.m_firstWalker = this.m_firstWalker.cloneDeep(clone, null);
        }
        return clone;
    }
    
    public void reset() {
        super.reset();
        if (null != this.m_firstWalker) {
            this.m_lastUsedWalker = this.m_firstWalker;
            this.m_firstWalker.setRoot(super.m_context);
        }
    }
    
    public void setRoot(final int context, final Object environment) {
        super.setRoot(context, environment);
        if (null != this.m_firstWalker) {
            this.m_firstWalker.setRoot(context);
            this.m_lastUsedWalker = this.m_firstWalker;
        }
    }
    
    public int nextNode() {
        if (super.m_foundLast) {
            return -1;
        }
        if (-1 == super.m_stackFrame) {
            return this.returnNextNode(this.m_firstWalker.nextNode());
        }
        final VariableStack vars = super.m_execContext.getVarStack();
        final int savedStart = vars.getStackFrame();
        vars.setStackFrame(super.m_stackFrame);
        final int n = this.returnNextNode(this.m_firstWalker.nextNode());
        vars.setStackFrame(savedStart);
        return n;
    }
    
    public final AxesWalker getFirstWalker() {
        return this.m_firstWalker;
    }
    
    public final void setFirstWalker(final AxesWalker walker) {
        this.m_firstWalker = walker;
    }
    
    public final void setLastUsedWalker(final AxesWalker walker) {
        this.m_lastUsedWalker = walker;
    }
    
    public final AxesWalker getLastUsedWalker() {
        return this.m_lastUsedWalker;
    }
    
    public void detach() {
        if (super.m_allowDetach) {
            for (AxesWalker walker = this.m_firstWalker; null != walker; walker = walker.getNextWalker()) {
                walker.detach();
            }
            this.m_lastUsedWalker = null;
            super.detach();
        }
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        super.m_predicateIndex = -1;
        for (AxesWalker walker = this.m_firstWalker; null != walker; walker = walker.getNextWalker()) {
            walker.fixupVariables(vars, globalsSize);
        }
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        if (visitor.visitLocationPath(owner, this) && null != this.m_firstWalker) {
            this.m_firstWalker.callVisitors(this, visitor);
        }
    }
    
    public Expression getExpression() {
        return this.m_firstWalker;
    }
    
    public void setExpression(final Expression exp) {
        exp.exprSetParent(this);
        this.m_firstWalker = (AxesWalker)exp;
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!super.deepEquals(expr)) {
            return false;
        }
        AxesWalker walker1;
        AxesWalker walker2;
        for (walker1 = this.m_firstWalker, walker2 = ((WalkingIterator)expr).m_firstWalker; null != walker1 && null != walker2; walker1 = walker1.getNextWalker(), walker2 = walker2.getNextWalker()) {
            if (!walker1.deepEquals(walker2)) {
                return false;
            }
        }
        return null == walker1 && null == walker2;
    }
}
