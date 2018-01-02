// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.patterns;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.Node;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.Expression;

public class FunctionPattern extends StepPattern
{
    Expression m_functionExpr;
    
    public FunctionPattern(final Expression expr) {
        super(0, null, null);
        this.m_functionExpr = expr;
    }
    
    protected final void calcScore() {
        super.m_score = NodeTest.SCORE_OTHER;
        if (super.m_targetString == null) {
            this.calcTargetString();
        }
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        Node context = xctxt.getCurrentNode();
        final XObject obj = this.m_functionExpr.execute(xctxt);
        final NodeIterator nl = obj.nodeset();
        XNumber score = NodeTest.SCORE_NONE;
        if (nl != null) {
            Node n;
            while ((n = nl.nextNode()) != null) {
                score = (n.equals(context) ? NodeTest.SCORE_OTHER : NodeTest.SCORE_NONE);
                if (score == NodeTest.SCORE_OTHER) {
                    context = n;
                    break;
                }
            }
        }
        return score;
    }
}
