// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xpath.patterns.NodeTest;
import java.util.Vector;
import org.apache.xpath.ExpressionNode;
import org.apache.xml.dtm.DTMAxisTraverser;

public class UnionChildIterator extends ChildTestIterator
{
    static final long serialVersionUID = 3500298482193003495L;
    private PredicatedNodeTest[] m_nodeTests;
    
    public UnionChildIterator() {
        super((DTMAxisTraverser)null);
        this.m_nodeTests = null;
    }
    
    public void addNodeTest(final PredicatedNodeTest test) {
        if (null == this.m_nodeTests) {
            (this.m_nodeTests = new PredicatedNodeTest[1])[0] = test;
        }
        else {
            final PredicatedNodeTest[] tests = this.m_nodeTests;
            final int len = this.m_nodeTests.length;
            System.arraycopy(tests, 0, this.m_nodeTests = new PredicatedNodeTest[len + 1], 0, len);
            this.m_nodeTests[len] = test;
        }
        test.exprSetParent(this);
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        super.fixupVariables(vars, globalsSize);
        if (this.m_nodeTests != null) {
            for (int i = 0; i < this.m_nodeTests.length; ++i) {
                this.m_nodeTests[i].fixupVariables(vars, globalsSize);
            }
        }
    }
    
    public short acceptNode(final int n) {
        final XPathContext xctxt = this.getXPathContext();
        try {
            xctxt.pushCurrentNode(n);
            for (int i = 0; i < this.m_nodeTests.length; ++i) {
                final PredicatedNodeTest pnt = this.m_nodeTests[i];
                final XObject score = pnt.execute(xctxt, n);
                if (score != NodeTest.SCORE_NONE) {
                    if (pnt.getPredicateCount() <= 0) {
                        return 1;
                    }
                    if (pnt.executePredicates(n, xctxt)) {
                        return 1;
                    }
                }
            }
        }
        catch (TransformerException se) {
            throw new RuntimeException(se.getMessage());
        }
        finally {
            xctxt.popCurrentNode();
        }
        return 3;
    }
}
