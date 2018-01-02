// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.patterns;

import org.w3c.dom.Node;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.Expression;
import org.apache.xpath.axes.SubContextList;

public class StepPattern extends NodeTest implements SubContextList
{
    String m_targetString;
    StepPattern m_relativePathPattern;
    Expression[] m_predicates;
    private static final boolean DEBUG_MATCHES = false;
    
    public StepPattern(final int whatToShow) {
        super(whatToShow);
    }
    
    public StepPattern(final int whatToShow, final String namespace, final String name) {
        super(whatToShow, namespace, name);
    }
    
    protected void calcScore() {
        if (this.getPredicateCount() > 0 || this.m_relativePathPattern != null) {
            super.m_score = NodeTest.SCORE_OTHER;
        }
        else {
            super.calcScore();
        }
        if (this.m_targetString == null) {
            this.calcTargetString();
        }
    }
    
    public void calcTargetString() {
        final int whatToShow = this.getWhatToShow();
        switch (whatToShow) {
            case 128: {
                this.m_targetString = "#comment";
                break;
            }
            case 4:
            case 8:
            case 12: {
                this.m_targetString = "#text";
                break;
            }
            case -1: {
                this.m_targetString = "*";
                break;
            }
            case 256:
            case 1280: {
                this.m_targetString = "/";
                break;
            }
            case 1: {
                if (super.m_name == "*") {
                    this.m_targetString = "*";
                    break;
                }
                this.m_targetString = super.m_name;
                break;
            }
            default: {
                this.m_targetString = "*";
                break;
            }
        }
    }
    
    public boolean canTraverseOutsideSubtree() {
        for (int n = this.getPredicateCount(), i = 0; i < n; ++i) {
            if (this.getPredicate(i).canTraverseOutsideSubtree()) {
                return true;
            }
        }
        return false;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        XObject score = this.executeStep(xctxt);
        if (score != NodeTest.SCORE_NONE && this.m_relativePathPattern != null) {
            score = this.m_relativePathPattern.executeRelativePathPattern(xctxt);
        }
        return score;
    }
    
    public XObject executeRelativePathPattern(final XPathContext xctxt) throws TransformerException {
        final Node parent = xctxt.getDOMHelper().getParentOfNode(xctxt.getCurrentNode());
        if (parent != null) {
            try {
                xctxt.pushCurrentNode(parent);
                XObject score = this.execute(xctxt);
                if (score != NodeTest.SCORE_NONE) {
                    score = NodeTest.SCORE_OTHER;
                }
                return score;
            }
            finally {
                xctxt.popCurrentNode();
            }
        }
        XObject score = NodeTest.SCORE_NONE;
        return score;
    }
    
    public XObject executeStep(final XPathContext xctxt) throws TransformerException {
        XObject score = super.execute(xctxt);
        if (score == NodeTest.SCORE_NONE) {
            return score;
        }
        final int n = this.getPredicateCount();
        if (n == 0) {
            return score;
        }
        try {
            xctxt.pushSubContextList(this);
            for (int i = 0; i < n; ++i) {
                final XObject pred = this.m_predicates[i].execute(xctxt);
                if (pred.getType() == 2) {
                    if (this.getProximityPosition(xctxt) != (int)pred.num()) {
                        score = NodeTest.SCORE_NONE;
                        break;
                    }
                }
                else if (!pred.bool()) {
                    score = NodeTest.SCORE_NONE;
                    break;
                }
            }
        }
        finally {
            xctxt.popSubContextList();
        }
        return score;
    }
    
    public int getLastPos(final XPathContext xctxt) {
        final Node context = xctxt.getCurrentNode();
        final Node parentContext = xctxt.getDOMHelper().getParentOfNode(context);
        try {
            xctxt.pushCurrentNode(parentContext);
            int count = 0;
            for (Node child = parentContext.getFirstChild(); child != null; child = child.getNextSibling()) {
                try {
                    xctxt.pushCurrentNode(child);
                    if (NodeTest.SCORE_NONE != super.execute(xctxt)) {
                        ++count;
                    }
                }
                finally {
                    xctxt.popCurrentNode();
                }
            }
            return count;
        }
        catch (TransformerException se) {
            throw new RuntimeException(se.getMessage());
        }
        finally {
            xctxt.popCurrentNode();
        }
    }
    
    public double getMatchScore(final XPathContext xctxt, final Node context) throws TransformerException {
        xctxt.pushCurrentNode(context);
        xctxt.pushCurrentExpressionNode(context);
        try {
            final XObject score = this.execute(xctxt);
            return score.num();
        }
        finally {
            xctxt.popCurrentNode();
            xctxt.popCurrentExpressionNode();
        }
    }
    
    public Expression getPredicate(final int i) {
        return this.m_predicates[i];
    }
    
    public final int getPredicateCount() {
        return (this.m_predicates == null) ? 0 : this.m_predicates.length;
    }
    
    public int getProximityPosition(final XPathContext xctxt) {
        final Node context = xctxt.getCurrentNode();
        final Node parentContext = xctxt.getDOMHelper().getParentOfNode(context);
        try {
            xctxt.pushCurrentNode(parentContext);
            int pos = 0;
            for (Node child = parentContext.getFirstChild(); child != null; child = child.getNextSibling()) {
                try {
                    xctxt.pushCurrentNode(child);
                    if (NodeTest.SCORE_NONE != super.execute(xctxt)) {
                        ++pos;
                        if (child.equals(context)) {
                            return pos;
                        }
                    }
                }
                finally {
                    xctxt.popCurrentNode();
                }
            }
        }
        catch (TransformerException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        finally {
            xctxt.popCurrentNode();
        }
        return 0;
    }
    
    public String getTargetString() {
        return this.m_targetString;
    }
    
    public void setPredicates(final Expression[] predicates) {
        this.m_predicates = predicates;
        this.calcScore();
    }
    
    public void setRelativePathPattern(final StepPattern expr) {
        this.m_relativePathPattern = expr;
        this.calcScore();
    }
}
