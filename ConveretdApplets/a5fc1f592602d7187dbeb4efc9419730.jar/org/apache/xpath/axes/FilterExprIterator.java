// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionNode;
import java.util.Vector;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.Expression;

public class FilterExprIterator extends BasicTestIterator
{
    private Expression m_expr;
    private transient XNodeSet m_exprObj;
    private boolean m_mustHardReset;
    private boolean m_canDetachNodeset;
    
    public FilterExprIterator() {
        super((PrefixResolver)null);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
    }
    
    public FilterExprIterator(final Expression expr) {
        super((PrefixResolver)null);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
        this.m_expr = expr;
    }
    
    public void setRoot(final int context, final Object environment) {
        super.setRoot(context, environment);
        this.m_exprObj = FilterExprIteratorSimple.executeFilterExpr(context, super.m_execContext, this.getPrefixResolver(), this.getIsTopLevel(), super.m_stackFrame, this.m_expr);
    }
    
    protected int getNextNode() {
        if (null != this.m_exprObj) {
            super.m_lastFetched = this.m_exprObj.nextNode();
        }
        else {
            super.m_lastFetched = -1;
        }
        return super.m_lastFetched;
    }
    
    public void detach() {
        super.detach();
        this.m_exprObj.detach();
        this.m_exprObj = null;
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
        final FilterExprIterator fet = (FilterExprIterator)expr;
        return this.m_expr.deepEquals(fet.m_expr);
    }
    
    class filterExprOwner implements ExpressionOwner
    {
        public Expression getExpression() {
            return FilterExprIterator.this.m_expr;
        }
        
        public void setExpression(final Expression exp) {
            exp.exprSetParent(FilterExprIterator.this);
            FilterExprIterator.this.m_expr = exp;
        }
    }
}
