// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.DOMException;
import org.apache.xpath.VariableStack;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.OpMap;
import org.apache.xpath.compiler.Compiler;

public class ChildTestIterator extends LocPathIterator
{
    public ChildTestIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis, false);
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        final int whatToShow = compiler.getWhatToShow(firstStepPos);
        if ((whatToShow & 0x43) == 0x0 || whatToShow == -1) {
            this.initNodeTest(whatToShow);
        }
        else {
            this.initNodeTest(whatToShow, compiler.getStepNS(firstStepPos), compiler.getStepLocalName(firstStepPos));
        }
        this.initPredicateInfo(compiler, firstStepPos);
    }
    
    public NodeIterator cloneWithReset() throws CloneNotSupportedException {
        final ChildTestIterator clone = (ChildTestIterator)super.cloneWithReset();
        clone.resetProximityPositions();
        return clone;
    }
    
    public Node nextNode() throws DOMException {
        if (super.m_cachedNodes != null && super.m_cachedNodes.getCurrentPos() < super.m_cachedNodes.size()) {
            final Node next = super.m_cachedNodes.nextNode();
            this.setCurrentPos(super.m_cachedNodes.getCurrentPos());
            return next;
        }
        if (super.m_foundLast) {
            return null;
        }
        if (super.m_lastFetched == null) {
            this.resetProximityPositions();
        }
        Label_0109: {
            if (super.m_varStackPos != -1) {
                final VariableStack vars = super.m_execContext.getVarStack();
                final int savedStart = vars.getSearchStart();
                vars.setSearchStart(super.m_varStackPos);
                vars.pushContextPosition(super.m_varStackContext);
                break Label_0109;
            }
            final VariableStack vars = null;
            final int savedStart = 0;
            try {
                Node next;
                do {
                    next = (super.m_lastFetched = ((super.m_lastFetched == null) ? super.m_context.getFirstChild() : super.m_lastFetched.getNextSibling()));
                    if (next == null) {
                        break;
                    }
                    if (this.acceptNode(next) == 1) {
                        break;
                    }
                } while (next != null);
                if (next != null) {
                    if (super.m_cachedNodes != null) {
                        super.m_cachedNodes.addElement(super.m_lastFetched);
                    }
                    ++super.m_next;
                    return next;
                }
                super.m_foundLast = true;
                return null;
            }
            finally {
                if (super.m_varStackPos != -1) {
                    vars.setSearchStart(savedStart);
                    vars.popContextPosition();
                }
            }
        }
    }
}
