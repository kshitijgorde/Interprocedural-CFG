// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.objects.XObject;
import org.apache.xpath.patterns.NodeTest;
import org.apache.xpath.XPathContext;
import org.apache.xpath.VariableStack;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.OpMap;
import org.apache.xpath.compiler.Compiler;
import org.apache.xml.dtm.DTMAxisTraverser;
import org.apache.xpath.patterns.StepPattern;

public class MatchPatternIterator extends LocPathIterator
{
    protected StepPattern m_pattern;
    protected int m_superAxis;
    protected DTMAxisTraverser m_traverser;
    private static final boolean DEBUG = false;
    
    MatchPatternIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis, false);
        this.m_superAxis = -1;
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        this.m_pattern = WalkerFactory.loadSteps(this, compiler, firstStepPos, 0);
        boolean fromRoot = false;
        boolean walkBack = false;
        boolean walkDescendants = false;
        boolean walkAttributes = false;
        if (0x0 != (analysis & 0x28000000)) {
            fromRoot = true;
        }
        if (0x0 != (analysis & 0x5D86000)) {
            walkBack = true;
        }
        if (0x0 != (analysis & 0x70000)) {
            walkDescendants = true;
        }
        if (0x0 != (analysis & 0x208000)) {
            walkAttributes = true;
        }
        if (fromRoot || walkBack) {
            if (walkAttributes) {
                this.m_superAxis = 16;
            }
            else {
                this.m_superAxis = 17;
            }
        }
        else if (walkDescendants) {
            if (walkAttributes) {
                this.m_superAxis = 14;
            }
            else {
                this.m_superAxis = 5;
            }
        }
        else {
            this.m_superAxis = 16;
        }
    }
    
    public void setRoot(final int context, final Object environment) {
        super.setRoot(context, environment);
        this.m_traverser = super.m_cdtm.getAxisTraverser(this.m_superAxis);
    }
    
    public void detach() {
        if (super.m_allowDetach) {
            this.m_traverser = null;
            super.detach();
        }
    }
    
    protected int getNextNode() {
        return super.m_lastFetched = ((-1 == super.m_lastFetched) ? this.m_traverser.first(super.m_context) : this.m_traverser.next(super.m_context, super.m_lastFetched));
    }
    
    public int nextNode() {
        if (super.m_foundLast) {
            return -1;
        }
        Label_0045: {
            if (-1 != super.m_stackFrame) {
                final VariableStack vars = super.m_execContext.getVarStack();
                final int savedStart = vars.getStackFrame();
                vars.setStackFrame(super.m_stackFrame);
                break Label_0045;
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
                    if (1 == this.acceptNode(next, super.m_execContext)) {
                        break;
                    }
                } while (next != -1);
                if (-1 != next) {
                    this.incrementCurrentPos();
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
    
    public short acceptNode(final int n, final XPathContext xctxt) {
        try {
            xctxt.pushCurrentNode(n);
            xctxt.pushIteratorRoot(super.m_context);
            final XObject score = this.m_pattern.execute(xctxt);
            return (short)((score == NodeTest.SCORE_NONE) ? 3 : 1);
        }
        catch (TransformerException se) {
            throw new RuntimeException(se.getMessage());
        }
        finally {
            xctxt.popCurrentNode();
            xctxt.popIteratorRoot();
        }
    }
}
