// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.patterns;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.ExpressionNode;
import java.util.Vector;
import org.apache.xpath.Expression;

public class UnionPattern extends Expression
{
    private StepPattern[] m_patterns;
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        for (int i = 0; i < this.m_patterns.length; ++i) {
            this.m_patterns[i].fixupVariables(vars, globalsSize);
        }
    }
    
    public boolean canTraverseOutsideSubtree() {
        if (null != this.m_patterns) {
            for (int n = this.m_patterns.length, i = 0; i < n; ++i) {
                if (this.m_patterns[i].canTraverseOutsideSubtree()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void setPatterns(final StepPattern[] patterns) {
        this.m_patterns = patterns;
        if (null != patterns) {
            for (int i = 0; i < patterns.length; ++i) {
                patterns[i].exprSetParent(this);
            }
        }
    }
    
    public StepPattern[] getPatterns() {
        return this.m_patterns;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        XObject bestScore = null;
        for (int n = this.m_patterns.length, i = 0; i < n; ++i) {
            final XObject score = this.m_patterns[i].execute(xctxt);
            if (score != NodeTest.SCORE_NONE) {
                if (null == bestScore) {
                    bestScore = score;
                }
                else if (score.num() > bestScore.num()) {
                    bestScore = score;
                }
            }
        }
        if (null == bestScore) {
            bestScore = NodeTest.SCORE_NONE;
        }
        return bestScore;
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        visitor.visitUnionPattern(owner, this);
        if (null != this.m_patterns) {
            for (int n = this.m_patterns.length, i = 0; i < n; ++i) {
                this.m_patterns[i].callVisitors(new UnionPathPartOwner(i), visitor);
            }
        }
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!this.isSameClass(expr)) {
            return false;
        }
        final UnionPattern up = (UnionPattern)expr;
        if (null != this.m_patterns) {
            final int n = this.m_patterns.length;
            if (null == up.m_patterns || up.m_patterns.length != n) {
                return false;
            }
            for (int i = 0; i < n; ++i) {
                if (!this.m_patterns[i].deepEquals(up.m_patterns[i])) {
                    return false;
                }
            }
        }
        else if (up.m_patterns != null) {
            return false;
        }
        return true;
    }
    
    class UnionPathPartOwner implements ExpressionOwner
    {
        int m_index;
        
        UnionPathPartOwner(final int index) {
            this.m_index = index;
        }
        
        public Expression getExpression() {
            return UnionPattern.this.m_patterns[this.m_index];
        }
        
        public void setExpression(final Expression exp) {
            exp.exprSetParent(UnionPattern.this);
            UnionPattern.this.m_patterns[this.m_index] = (StepPattern)exp;
        }
    }
}
