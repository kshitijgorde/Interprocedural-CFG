// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.DOMException;
import org.apache.xpath.VariableStack;
import org.w3c.dom.traversal.NodeIterator;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.OpMap;
import org.apache.xpath.compiler.Compiler;
import org.w3c.dom.Node;

public class DescendantIterator extends LocPathIterator
{
    private transient Node m_startContext;
    private boolean m_orSelf;
    private boolean m_fromRoot;
    
    public DescendantIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis, false);
        final int[] ops = compiler.getOpMap();
        int firstStepPos = OpMap.getFirstChildPos(opPos);
        final int stepType = ops[firstStepPos];
        if (stepType == 42) {
            this.m_orSelf = true;
        }
        if (stepType == 48) {
            this.m_orSelf = true;
            firstStepPos += 8;
        }
        else if (stepType == 50) {
            this.m_fromRoot = true;
            this.m_orSelf = true;
            firstStepPos += 8;
        }
        else {
            this.m_orSelf = false;
        }
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
        final DescendantIterator clone = (DescendantIterator)super.cloneWithReset();
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
        boolean getSelf;
        Node pos;
        if (super.m_lastFetched == null) {
            getSelf = this.m_orSelf;
            if (getSelf && this.m_fromRoot) {
                if (super.m_context.getNodeType() == 9) {
                    pos = super.m_context;
                }
                else {
                    pos = super.m_context.getOwnerDocument();
                }
            }
            else {
                pos = super.m_context;
            }
            this.m_startContext = pos;
            this.resetProximityPositions();
        }
        else {
            pos = super.m_lastFetched;
            getSelf = false;
        }
        VariableStack vars;
        int savedStart;
        if (super.m_varStackPos != -1) {
            vars = super.m_execContext.getVarStack();
            savedStart = vars.getSearchStart();
            vars.setSearchStart(super.m_varStackPos);
            vars.pushContextPosition(super.m_varStackContext);
        }
        else {
            vars = null;
            savedStart = 0;
        }
        try {
            final Node top = this.m_startContext;
            Node next2 = null;
            while (pos != null) {
                if (getSelf) {
                    super.m_lastFetched = pos;
                    if (this.acceptNode(pos) == 1) {
                        next2 = pos;
                        break;
                    }
                }
                else {
                    getSelf = true;
                }
                Node nextNode = pos.getFirstChild();
                while (nextNode == null && !top.equals(pos)) {
                    nextNode = pos.getNextSibling();
                    if (nextNode == null) {
                        pos = pos.getParentNode();
                        if (pos == null || top.equals(pos)) {
                            nextNode = null;
                            break;
                        }
                        continue;
                    }
                }
                pos = nextNode;
            }
            if ((super.m_lastFetched = next2) != null) {
                if (super.m_cachedNodes != null) {
                    super.m_cachedNodes.addElement(next2);
                }
                ++super.m_next;
                return next2;
            }
            super.m_foundLast = true;
            return this.m_startContext = null;
        }
        finally {
            if (super.m_varStackPos != -1) {
                vars.setSearchStart(savedStart);
                vars.popContextPosition();
            }
        }
    }
}
