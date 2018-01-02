// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathVisitor;
import java.util.Vector;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xpath.operations.Variable;
import org.apache.xpath.ExpressionNode;
import org.apache.xpath.compiler.Compiler;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.Expression;

public class FilterExprWalker extends AxesWalker
{
    private Expression m_expr;
    private transient XNodeSet m_exprObj;
    private boolean m_mustHardReset;
    private boolean m_canDetachNodeset;
    
    public FilterExprWalker(final WalkingIterator locPathIterator) {
        super(locPathIterator, 20);
        this.m_mustHardReset = false;
        this.m_canDetachNodeset = true;
    }
    
    public void init(final Compiler compiler, final int opPos, final int stepType) throws TransformerException {
        super.init(compiler, opPos, stepType);
        switch (stepType) {
            case 24:
            case 25: {
                this.m_mustHardReset = true;
            }
            case 22:
            case 23: {
                (this.m_expr = compiler.compile(opPos)).exprSetParent(this);
                if (this.m_expr instanceof Variable) {
                    this.m_canDetachNodeset = false;
                    break;
                }
                break;
            }
            default: {
                (this.m_expr = compiler.compile(opPos + 2)).exprSetParent(this);
                break;
            }
        }
    }
    
    public void detach() {
        super.detach();
        if (this.m_canDetachNodeset) {
            this.m_exprObj.detach();
        }
        this.m_exprObj = null;
    }
    
    public void setRoot(final int root) {
        super.setRoot(root);
        this.m_exprObj = FilterExprIteratorSimple.executeFilterExpr(root, super.m_lpi.getXPathContext(), super.m_lpi.getPrefixResolver(), super.m_lpi.getIsTopLevel(), super.m_lpi.m_stackFrame, this.m_expr);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final FilterExprWalker clone = (FilterExprWalker)super.clone();
        if (null != this.m_exprObj) {
            clone.m_exprObj = (XNodeSet)this.m_exprObj.clone();
        }
        return clone;
    }
    
    public short acceptNode(final int n) {
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
    
    public int getNextNode() {
        if (null != this.m_exprObj) {
            final int next = this.m_exprObj.nextNode();
            return next;
        }
        return -1;
    }
    
    public int getLastPos(final XPathContext xctxt) {
        return this.m_exprObj.getLength();
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
    
    public int getAxis() {
        return this.m_exprObj.getAxis();
    }
    
    public void callPredicateVisitors(final XPathVisitor visitor) {
        this.m_expr.callVisitors(new filterExprOwner(), visitor);
        super.callPredicateVisitors(visitor);
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!super.deepEquals(expr)) {
            return false;
        }
        final FilterExprWalker walker = (FilterExprWalker)expr;
        return this.m_expr.deepEquals(walker.m_expr);
    }
    
    class filterExprOwner implements ExpressionOwner
    {
        public Expression getExpression() {
            return FilterExprWalker.this.m_expr;
        }
        
        public void setExpression(final Expression exp) {
            exp.exprSetParent(FilterExprWalker.this);
            FilterExprWalker.this.m_expr = exp;
        }
    }
}
