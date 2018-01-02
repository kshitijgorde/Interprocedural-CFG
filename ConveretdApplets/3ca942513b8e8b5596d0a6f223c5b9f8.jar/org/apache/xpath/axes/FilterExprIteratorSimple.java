// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionNode;
import java.util.Vector;
import org.apache.xpath.VariableStack;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.Expression;

public class FilterExprIteratorSimple extends LocPathIterator
{
    static final long serialVersionUID = -6978977187025375579L;
    private Expression m_expr;
    private transient XNodeSet m_exprObj;
    private boolean m_mustHardReset;
    private boolean m_canDetachNodeset;
    
    public FilterExprIteratorSimple() {
        super((PrefixResolver)null);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
    }
    
    public FilterExprIteratorSimple(final Expression expr) {
        super((PrefixResolver)null);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
        this.m_expr = expr;
    }
    
    public void setRoot(final int context, final Object environment) {
        super.setRoot(context, environment);
        this.m_exprObj = executeFilterExpr(context, super.m_execContext, this.getPrefixResolver(), this.getIsTopLevel(), super.m_stackFrame, this.m_expr);
    }
    
    public static XNodeSet executeFilterExpr(final int context, final XPathContext xctxt, final PrefixResolver prefixResolver, final boolean isTopLevel, final int stackFrame, final Expression expr) throws WrappedRuntimeException {
        final PrefixResolver savedResolver = xctxt.getNamespaceContext();
        XNodeSet result = null;
        try {
            xctxt.pushCurrentNode(context);
            xctxt.setNamespaceContext(prefixResolver);
            if (isTopLevel) {
                final VariableStack vars = xctxt.getVarStack();
                final int savedStart = vars.getStackFrame();
                vars.setStackFrame(stackFrame);
                result = (XNodeSet)expr.execute(xctxt);
                result.setShouldCacheNodes(true);
                vars.setStackFrame(savedStart);
            }
            else {
                result = (XNodeSet)expr.execute(xctxt);
            }
        }
        catch (TransformerException se) {
            throw new WrappedRuntimeException(se);
        }
        finally {
            xctxt.popCurrentNode();
            xctxt.setNamespaceContext(savedResolver);
        }
        return result;
    }
    
    public int nextNode() {
        if (super.m_foundLast) {
            return -1;
        }
        int next;
        if (null != this.m_exprObj) {
            next = (super.m_lastFetched = this.m_exprObj.nextNode());
        }
        else {
            next = (super.m_lastFetched = -1);
        }
        if (-1 != next) {
            ++super.m_pos;
            return next;
        }
        super.m_foundLast = true;
        return -1;
    }
    
    public void detach() {
        if (super.m_allowDetach) {
            super.detach();
            this.m_exprObj.detach();
            this.m_exprObj = null;
        }
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        super.fixupVariables(vars, globalsSize);
        this.m_expr.fixupVariables(vars, globalsSize);
    }
    
    public Expression getInnerExpression() {
        return this.m_expr;
    }
    
    public void setInnerExpression(final Expression expr) {
        expr.exprSetParent(this);
        this.m_expr = expr;
    }
    
    public int getAnalysisBits() {
        if (null != this.m_expr && this.m_expr instanceof PathComponent) {
            return ((PathComponent)this.m_expr).getAnalysisBits();
        }
        return 67108864;
    }
    
    public boolean isDocOrdered() {
        return this.m_exprObj.isDocOrdered();
    }
    
    public void callPredicateVisitors(final XPathVisitor visitor) {
        this.m_expr.callVisitors(new filterExprOwner(), visitor);
        super.callPredicateVisitors(visitor);
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!super.deepEquals(expr)) {
            return false;
        }
        final FilterExprIteratorSimple fet = (FilterExprIteratorSimple)expr;
        return this.m_expr.deepEquals(fet.m_expr);
    }
    
    public int getAxis() {
        if (null != this.m_exprObj) {
            return this.m_exprObj.getAxis();
        }
        return 20;
    }
    
    class filterExprOwner implements ExpressionOwner
    {
        public Expression getExpression() {
            return FilterExprIteratorSimple.this.m_expr;
        }
        
        public void setExpression(final Expression exp) {
            exp.exprSetParent(FilterExprIteratorSimple.this);
            FilterExprIteratorSimple.this.m_expr = exp;
        }
    }
}
