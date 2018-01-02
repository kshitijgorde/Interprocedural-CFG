// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xalan.stree.Child;
import org.apache.xpath.compiler.Compiler;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.apache.xpath.Expression;
import org.apache.xpath.patterns.NodeTest;

public abstract class PredicatedNodeTest extends NodeTest implements SubContextList
{
    protected LocPathIterator m_lpi;
    transient int m_predicateIndex;
    private Expression[] m_predicates;
    protected transient int[] m_proximityPositions;
    static final boolean DEBUG_PREDICATECOUNTING = false;
    
    public PredicatedNodeTest() {
        this.m_predicateIndex = -1;
    }
    
    public PredicatedNodeTest(final LocPathIterator locPathIterator) {
        this.m_predicateIndex = -1;
        this.m_lpi = locPathIterator;
    }
    
    public short acceptNode(final Node n) {
        final XPathContext xctxt = this.m_lpi.getXPathContext();
        try {
            xctxt.pushCurrentNode(n);
            final XObject score = this.execute(xctxt, n);
            if (score != NodeTest.SCORE_NONE) {
                if (this.getPredicateCount() > 0) {
                    this.countProximityPosition(0);
                    if (!this.executePredicates(n, xctxt)) {
                        return 3;
                    }
                }
                return 1;
            }
        }
        catch (TransformerException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        finally {
            xctxt.popCurrentNode();
        }
        return 3;
    }
    
    public boolean canTraverseOutsideSubtree() {
        for (int n = this.getPredicateCount(), i = 0; i < n; ++i) {
            if (this.getPredicate(i).canTraverseOutsideSubtree()) {
                return true;
            }
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final PredicatedNodeTest clone = (PredicatedNodeTest)super.clone();
        if (this.m_proximityPositions != null && this.m_proximityPositions == clone.m_proximityPositions) {
            clone.m_proximityPositions = new int[this.m_proximityPositions.length];
            System.arraycopy(this.m_proximityPositions, 0, clone.m_proximityPositions, 0, this.m_proximityPositions.length);
        }
        if (clone.m_lpi == this) {
            clone.m_lpi = (LocPathIterator)clone;
        }
        return clone;
    }
    
    protected void countProximityPosition(final int i) {
        if (i < this.m_proximityPositions.length) {
            final int[] proximityPositions = this.m_proximityPositions;
            ++proximityPositions[i];
        }
    }
    
    boolean executePredicates(final Node context, final XPathContext xctxt) throws TransformerException {
        this.m_predicateIndex = 0;
        final int nPredicates = this.getPredicateCount();
        if (nPredicates == 0) {
            return true;
        }
        final PrefixResolver savedResolver = xctxt.getNamespaceContext();
        try {
            xctxt.pushSubContextList(this);
            xctxt.setNamespaceContext(this.m_lpi.getPrefixResolver());
            xctxt.pushCurrentNode(context);
            for (int i = 0; i < nPredicates; ++i) {
                final int savedWaitingBottom = this.m_lpi.m_waitingBottom;
                this.m_lpi.m_waitingBottom = this.m_lpi.getWaitingCount();
                XObject pred;
                try {
                    pred = this.m_predicates[i].execute(xctxt);
                }
                finally {
                    this.m_lpi.m_waitingBottom = savedWaitingBottom;
                }
                if (pred.getType() == 2) {
                    final int proxPos = this.getProximityPosition(this.m_predicateIndex);
                    if (proxPos != (int)pred.num()) {
                        return false;
                    }
                }
                else if (!pred.bool()) {
                    return false;
                }
                this.countProximityPosition(++this.m_predicateIndex);
            }
        }
        finally {
            xctxt.popCurrentNode();
            xctxt.setNamespaceContext(savedResolver);
            xctxt.popSubContextList();
        }
        this.m_predicateIndex = -1;
        return true;
    }
    
    public abstract int getLastPos(final XPathContext p0);
    
    public LocPathIterator getLocPathIterator() {
        return this.m_lpi;
    }
    
    Expression getPredicate(final int index) {
        return this.m_predicates[index];
    }
    
    public int getPredicateCount() {
        return (this.m_predicates == null) ? 0 : this.m_predicates.length;
    }
    
    public int getPredicateIndex() {
        return this.m_predicateIndex;
    }
    
    public int getProximityPosition() {
        return this.getProximityPosition(this.m_predicateIndex);
    }
    
    protected int getProximityPosition(final int predicateIndex) {
        return (predicateIndex >= 0) ? this.m_proximityPositions[predicateIndex] : 0;
    }
    
    public int getProximityPosition(final XPathContext xctxt) {
        return this.getProximityPosition();
    }
    
    protected void initPredicateInfo(final Compiler compiler, final int opPos) throws TransformerException {
        final int pos = compiler.getFirstPredicateOpPos(opPos);
        this.m_predicates = compiler.getCompiledPredicates(pos);
    }
    
    public void initProximityPosition(final int i) throws TransformerException {
        this.m_proximityPositions[i] = 0;
    }
    
    public boolean isReverseAxes() {
        return false;
    }
    
    protected String nodeToString(final Node n) {
        try {
            return (n != null) ? (String.valueOf(n.getNodeName()) + "{" + ((Child)n).getUid() + "}") : "null";
        }
        catch (ClassCastException ex) {
            return (n != null) ? n.getNodeName() : "null";
        }
    }
    
    public void resetProximityPositions() {
        final int nPredicates = this.getPredicateCount();
        if (nPredicates > 0) {
            if (this.m_proximityPositions == null) {
                this.m_proximityPositions = new int[nPredicates];
            }
            for (int i = 0; i < nPredicates; ++i) {
                try {
                    this.initProximityPosition(i);
                }
                catch (Exception e) {
                    throw new WrappedRuntimeException(e);
                }
            }
        }
    }
    
    public void setLocPathIterator(final LocPathIterator li) {
        this.m_lpi = li;
    }
    
    public void setPredicateCount(final int count) {
        if (count > 0) {
            final Expression[] newPredicates = new Expression[count];
            for (int i = 0; i < count; ++i) {
                newPredicates[i] = this.m_predicates[i];
            }
            this.m_predicates = newPredicates;
        }
        else {
            this.m_predicates = null;
        }
    }
}
