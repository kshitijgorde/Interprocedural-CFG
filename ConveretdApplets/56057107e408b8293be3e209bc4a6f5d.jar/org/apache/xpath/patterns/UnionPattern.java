// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.patterns;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.Expression;

public class UnionPattern extends Expression
{
    private StepPattern[] m_patterns;
    
    public boolean canTraverseOutsideSubtree() {
        if (this.m_patterns != null) {
            for (int n = this.m_patterns.length, i = 0; i < n; ++i) {
                if (this.m_patterns[i].canTraverseOutsideSubtree()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        XObject bestScore = null;
        for (int n = this.m_patterns.length, i = 0; i < n; ++i) {
            final XObject score = this.m_patterns[i].execute(xctxt);
            if (score != NodeTest.SCORE_NONE) {
                if (bestScore == null) {
                    bestScore = score;
                }
                else if (score.num() > bestScore.num()) {
                    bestScore = score;
                }
            }
        }
        if (bestScore == null) {
            bestScore = NodeTest.SCORE_NONE;
        }
        return bestScore;
    }
    
    public StepPattern[] getPatterns() {
        return this.m_patterns;
    }
    
    public void setPatterns(final StepPattern[] patterns) {
        this.m_patterns = patterns;
    }
}
