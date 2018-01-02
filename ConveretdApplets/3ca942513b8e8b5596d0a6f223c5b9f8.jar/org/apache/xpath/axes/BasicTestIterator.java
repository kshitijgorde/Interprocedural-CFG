// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.VariableStack;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.OpMap;
import org.apache.xpath.compiler.Compiler;
import org.apache.xml.utils.PrefixResolver;

public abstract class BasicTestIterator extends LocPathIterator
{
    static final long serialVersionUID = 3505378079378096623L;
    
    protected BasicTestIterator() {
    }
    
    protected BasicTestIterator(final PrefixResolver nscontext) {
        super(nscontext);
    }
    
    protected BasicTestIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis, false);
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        final int whatToShow = compiler.getWhatToShow(firstStepPos);
        if (0x0 == (whatToShow & 0x1043) || whatToShow == -1) {
            this.initNodeTest(whatToShow);
        }
        else {
            this.initNodeTest(whatToShow, compiler.getStepNS(firstStepPos), compiler.getStepLocalName(firstStepPos));
        }
        this.initPredicateInfo(compiler, firstStepPos);
    }
    
    protected BasicTestIterator(final Compiler compiler, final int opPos, final int analysis, final boolean shouldLoadWalkers) throws TransformerException {
        super(compiler, opPos, analysis, shouldLoadWalkers);
    }
    
    protected abstract int getNextNode();
    
    public int nextNode() {
        if (super.m_foundLast) {
            return super.m_lastFetched = -1;
        }
        if (-1 == super.m_lastFetched) {
            this.resetProximityPositions();
        }
        Label_0062: {
            if (-1 != super.m_stackFrame) {
                final VariableStack vars = super.m_execContext.getVarStack();
                final int savedStart = vars.getStackFrame();
                vars.setStackFrame(super.m_stackFrame);
                break Label_0062;
            }
            final VariableStack vars = null;
            final int savedStart = 0;
            try {
                int next;
                do {
                    next = this.getNextNode();
                    if (-1 == next) {
                        break;
                    }
                    if (1 == this.acceptNode(next)) {
                        break;
                    }
                } while (next != -1);
                if (-1 != next) {
                    ++super.m_pos;
                    return next;
                }
                super.m_foundLast = true;
                return -1;
            }
            finally {
                if (-1 != super.m_stackFrame) {
                    vars.setStackFrame(savedStart);
                }
            }
        }
    }
    
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        final ChildTestIterator clone = (ChildTestIterator)super.cloneWithReset();
        clone.resetProximityPositions();
        return clone;
    }
}
