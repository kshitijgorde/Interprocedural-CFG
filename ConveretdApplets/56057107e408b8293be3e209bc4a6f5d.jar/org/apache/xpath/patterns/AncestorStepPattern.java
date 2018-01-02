// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.patterns;

import org.w3c.dom.Node;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class AncestorStepPattern extends StepPattern
{
    public AncestorStepPattern(final int whatToShow) {
        super(whatToShow);
    }
    
    public AncestorStepPattern(final int whatToShow, final String namespace, final String name) {
        super(whatToShow, namespace, name);
    }
    
    protected final void calcScore() {
        super.m_score = NodeTest.SCORE_OTHER;
        if (super.m_targetString == null) {
            this.calcTargetString();
        }
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final int whatToShow = this.getWhatToShow();
        if (whatToShow == 65536) {
            XObject score = NodeTest.SCORE_NONE;
            if (super.m_relativePathPattern != null) {
                score = super.m_relativePathPattern.execute(xctxt);
            }
            return score;
        }
        return super.execute(xctxt);
    }
    
    public XObject executeRelativePathPattern(final XPathContext xctxt) throws TransformerException {
        XObject score = NodeTest.SCORE_NONE;
        Node parent = xctxt.getCurrentNode();
        while ((parent = xctxt.getDOMHelper().getParentOfNode(parent)) != null) {
            try {
                xctxt.pushCurrentNode(parent);
                score = this.execute(xctxt);
                if (score != NodeTest.SCORE_NONE) {
                    score = NodeTest.SCORE_OTHER;
                    break;
                }
                continue;
            }
            finally {
                xctxt.popCurrentNode();
            }
        }
        return score;
    }
}
