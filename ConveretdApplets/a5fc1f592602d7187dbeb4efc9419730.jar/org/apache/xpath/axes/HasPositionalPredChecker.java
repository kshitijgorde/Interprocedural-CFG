// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.operations.Number;
import org.apache.xpath.operations.Mult;
import org.apache.xpath.operations.Quo;
import org.apache.xpath.operations.Mod;
import org.apache.xpath.operations.Minus;
import org.apache.xpath.operations.Plus;
import org.apache.xpath.operations.Div;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.operations.Variable;
import org.apache.xpath.Expression;
import org.apache.xpath.functions.FuncLast;
import org.apache.xpath.functions.FuncPosition;
import org.apache.xpath.functions.Function;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathVisitor;

public class HasPositionalPredChecker extends XPathVisitor
{
    private boolean m_hasPositionalPred;
    private int m_predDepth;
    
    public HasPositionalPredChecker() {
        this.m_hasPositionalPred = false;
        this.m_predDepth = 0;
    }
    
    public static boolean check(final LocPathIterator path) {
        final HasPositionalPredChecker hppc = new HasPositionalPredChecker();
        path.callVisitors(null, hppc);
        return hppc.m_hasPositionalPred;
    }
    
    public boolean visitFunction(final ExpressionOwner owner, final Function func) {
        if (func instanceof FuncPosition || func instanceof FuncLast) {
            this.m_hasPositionalPred = true;
        }
        return true;
    }
    
    public boolean visitPredicate(final ExpressionOwner owner, final Expression pred) {
        ++this.m_predDepth;
        if (this.m_predDepth == 1) {
            if (pred instanceof Variable || pred instanceof XNumber || pred instanceof Div || pred instanceof Plus || pred instanceof Minus || pred instanceof Mod || pred instanceof Quo || pred instanceof Mult || pred instanceof Number || pred instanceof Function) {
                this.m_hasPositionalPred = true;
            }
            else {
                pred.callVisitors(owner, this);
            }
        }
        --this.m_predDepth;
        return false;
    }
}
