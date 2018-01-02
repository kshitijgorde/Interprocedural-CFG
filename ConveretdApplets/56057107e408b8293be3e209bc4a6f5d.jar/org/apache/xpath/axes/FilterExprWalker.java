// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.objects.XObject;
import org.apache.xpath.VariableStack;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xpath.compiler.Compiler;
import org.apache.xpath.patterns.NodeTest;
import org.apache.xpath.patterns.NodeTestFilter;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.Expression;

public class FilterExprWalker extends AxesWalker
{
    private Expression m_expr;
    private transient NodeIterator m_nodeSet;
    private transient Node m_peek;
    
    public FilterExprWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
        this.m_peek = null;
    }
    
    public short acceptNode(final Node n) {
        try {
            if (this.getPredicateCount() > 0) {
                this.countProximityPosition(0);
                if (!this.executePredicates(n, super.m_lpi.getXPathContext())) {
                    return 3;
                }
            }
            return 1;
        }
        catch (TransformerException se) {
            throw new RuntimeException(se.getMessage());
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        final FilterExprWalker clone = (FilterExprWalker)super.clone();
        if (this.m_nodeSet != null) {
            clone.m_nodeSet = (NodeIterator)((ContextNodeList)this.m_nodeSet).clone();
        }
        return clone;
    }
    
    protected int getLevelMax() {
        return 1;
    }
    
    public Node getNextNode() {
        Node next;
        if (this.m_peek != null) {
            next = this.m_peek;
            this.m_peek = null;
        }
        else if (this.m_nodeSet != null) {
            final Node current = this.getCurrentNode();
            if (current instanceof NodeTestFilter) {
                ((NodeTestFilter)current).setNodeTest(this);
            }
            next = this.m_nodeSet.nextNode();
        }
        else {
            next = null;
        }
        if (next == null) {
            super.m_nextLevelAmount = 0;
        }
        else {
            super.m_nextLevelAmount = (next.hasChildNodes() ? 1 : 0);
        }
        return this.setCurrentIfNotNull(next);
    }
    
    public void init(final Compiler compiler, final int opPos, final int stepType) throws TransformerException {
        super.init(compiler, opPos, stepType);
        switch (stepType) {
            case 22:
            case 23:
            case 24:
            case 25: {
                this.m_expr = compiler.compile(opPos);
                break;
            }
            default: {
                this.m_expr = compiler.compile(opPos + 2);
                break;
            }
        }
    }
    
    public void setRoot(final Node root) {
        final XPathContext xctxt = super.m_lpi.getXPathContext();
        final PrefixResolver savedResolver = xctxt.getNamespaceContext();
        try {
            xctxt.pushCurrentNode(root);
            xctxt.setNamespaceContext(super.m_lpi.getPrefixResolver());
            XObject obj;
            if (super.m_lpi.getIsTopLevel()) {
                final VariableStack vars = super.m_lpi.m_execContext.getVarStack();
                final int savedStart = vars.getSearchStart();
                vars.setSearchStart(super.m_lpi.m_varStackPos);
                vars.pushContextPosition(super.m_lpi.m_varStackContext);
                obj = this.m_expr.execute(super.m_lpi.getXPathContext());
                vars.setSearchStart(savedStart);
                vars.popContextPosition();
            }
            else {
                obj = this.m_expr.execute(super.m_lpi.getXPathContext());
            }
            this.m_nodeSet = ((obj != null) ? obj.nodeset() : null);
            this.m_peek = null;
        }
        catch (TransformerException e) {
            throw new WrappedRuntimeException(e);
        }
        finally {
            xctxt.popCurrentNode();
            xctxt.setNamespaceContext(savedResolver);
        }
        super.setRoot(root);
    }
}
