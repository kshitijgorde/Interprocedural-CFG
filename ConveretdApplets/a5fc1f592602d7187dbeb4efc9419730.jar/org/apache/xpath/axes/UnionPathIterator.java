// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import java.util.Vector;
import org.apache.xml.dtm.DTM;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.OpMap;
import org.apache.xpath.compiler.Compiler;
import org.apache.xpath.ExpressionNode;
import org.apache.xpath.Expression;
import org.apache.xml.utils.WrappedRuntimeException;
import java.io.Serializable;
import org.apache.xml.dtm.DTMIterator;

public class UnionPathIterator extends LocPathIterator implements Cloneable, DTMIterator, Serializable, PathComponent
{
    protected LocPathIterator[] m_exprs;
    protected DTMIterator[] m_iterators;
    
    public UnionPathIterator() {
        this.m_iterators = null;
        this.m_exprs = null;
    }
    
    public void setRoot(final int context, final Object environment) {
        super.setRoot(context, environment);
        try {
            if (null != this.m_exprs) {
                final int n = this.m_exprs.length;
                final DTMIterator[] newIters = new DTMIterator[n];
                for (int i = 0; i < n; ++i) {
                    final DTMIterator iter = this.m_exprs[i].asIterator(super.m_execContext, context);
                    (newIters[i] = iter).nextNode();
                }
                this.m_iterators = newIters;
            }
        }
        catch (Exception e) {
            throw new WrappedRuntimeException(e);
        }
    }
    
    public void addIterator(final DTMIterator expr) {
        if (null == this.m_iterators) {
            (this.m_iterators = new DTMIterator[1])[0] = expr;
        }
        else {
            final DTMIterator[] exprs = this.m_iterators;
            final int len = this.m_iterators.length;
            System.arraycopy(exprs, 0, this.m_iterators = new DTMIterator[len + 1], 0, len);
            this.m_iterators[len] = expr;
        }
        expr.nextNode();
        if (expr instanceof Expression) {
            ((Expression)expr).exprSetParent(this);
        }
    }
    
    public void detach() {
        if (super.m_allowDetach && null != this.m_iterators) {
            for (int n = this.m_iterators.length, i = 0; i < n; ++i) {
                this.m_iterators[i].detach();
            }
            this.m_iterators = null;
        }
    }
    
    public UnionPathIterator(final Compiler compiler, int opPos) throws TransformerException {
        opPos = OpMap.getFirstChildPos(opPos);
        this.loadLocationPaths(compiler, opPos, 0);
    }
    
    public static LocPathIterator createUnionIterator(final Compiler compiler, final int opPos) throws TransformerException {
        final UnionPathIterator upi = new UnionPathIterator(compiler, opPos);
        final int nPaths = upi.m_exprs.length;
        boolean isAllChildIterators = true;
        for (int i = 0; i < nPaths; ++i) {
            final LocPathIterator lpi = upi.m_exprs[i];
            if (lpi.getAxis() != 3) {
                isAllChildIterators = false;
                break;
            }
            if (HasPositionalPredChecker.check(lpi)) {
                isAllChildIterators = false;
                break;
            }
        }
        if (isAllChildIterators) {
            final UnionChildIterator uci = new UnionChildIterator();
            for (int j = 0; j < nPaths; ++j) {
                final PredicatedNodeTest lpi2 = upi.m_exprs[j];
                uci.addNodeTest(lpi2);
            }
            return uci;
        }
        return upi;
    }
    
    public int getAnalysisBits() {
        int bits = 0;
        if (this.m_exprs != null) {
            for (int n = this.m_exprs.length, i = 0; i < n; ++i) {
                final int bit = this.m_exprs[i].getAnalysisBits();
                bits |= bit;
            }
        }
        return bits;
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, TransformerException {
        try {
            stream.defaultReadObject();
            super.m_clones = new IteratorPool(this);
        }
        catch (ClassNotFoundException cnfe) {
            throw new TransformerException(cnfe);
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        final UnionPathIterator clone = (UnionPathIterator)super.clone();
        return clone;
    }
    
    protected LocPathIterator createDTMIterator(final Compiler compiler, final int opPos) throws TransformerException {
        final LocPathIterator lpi = (LocPathIterator)WalkerFactory.newDTMIterator(compiler, opPos, compiler.getLocationPathDepth() <= 0);
        return lpi;
    }
    
    protected void loadLocationPaths(final Compiler compiler, final int opPos, final int count) throws TransformerException {
        final int steptype = compiler.getOp(opPos);
        if (steptype == 28) {
            this.loadLocationPaths(compiler, compiler.getNextOpPos(opPos), count + 1);
            (this.m_exprs[count] = this.createDTMIterator(compiler, opPos)).exprSetParent(this);
        }
        else {
            switch (steptype) {
                case 22:
                case 23:
                case 24:
                case 25: {
                    this.loadLocationPaths(compiler, compiler.getNextOpPos(opPos), count + 1);
                    final WalkingIterator iter = new WalkingIterator(compiler.getNamespaceContext());
                    iter.exprSetParent(this);
                    if (compiler.getLocationPathDepth() <= 0) {
                        iter.setIsTopLevel(true);
                    }
                    (iter.m_firstWalker = new FilterExprWalker(iter)).init(compiler, opPos, steptype);
                    this.m_exprs[count] = iter;
                    break;
                }
                default: {
                    this.m_exprs = new LocPathIterator[count];
                    break;
                }
            }
        }
    }
    
    public int nextNode() {
        if (super.m_foundLast) {
            return -1;
        }
        int earliestNode = -1;
        if (null != this.m_iterators) {
            final int n = this.m_iterators.length;
            int iteratorUsed = -1;
            for (int i = 0; i < n; ++i) {
                final int node = this.m_iterators[i].getCurrentNode();
                if (-1 != node) {
                    if (-1 == earliestNode) {
                        iteratorUsed = i;
                        earliestNode = node;
                    }
                    else if (node == earliestNode) {
                        this.m_iterators[i].nextNode();
                    }
                    else {
                        final DTM dtm = this.getDTM(node);
                        if (dtm.isNodeAfter(node, earliestNode)) {
                            iteratorUsed = i;
                            earliestNode = node;
                        }
                    }
                }
            }
            if (-1 != earliestNode) {
                this.m_iterators[iteratorUsed].nextNode();
                this.incrementCurrentPos();
            }
            else {
                super.m_foundLast = true;
            }
        }
        return super.m_lastFetched = earliestNode;
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        for (int i = 0; i < this.m_exprs.length; ++i) {
            this.m_exprs[i].fixupVariables(vars, globalsSize);
        }
    }
    
    public int getAxis() {
        return -1;
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        if (visitor.visitUnionPath(owner, this) && null != this.m_exprs) {
            for (int n = this.m_exprs.length, i = 0; i < n; ++i) {
                this.m_exprs[i].callVisitors(new iterOwner(i), visitor);
            }
        }
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!super.deepEquals(expr)) {
            return false;
        }
        final UnionPathIterator upi = (UnionPathIterator)expr;
        if (null != this.m_exprs) {
            final int n = this.m_exprs.length;
            if (null == upi.m_exprs || upi.m_exprs.length != n) {
                return false;
            }
            for (int i = 0; i < n; ++i) {
                if (!this.m_exprs[i].deepEquals(upi.m_exprs[i])) {
                    return false;
                }
            }
        }
        else if (null != upi.m_exprs) {
            return false;
        }
        return true;
    }
    
    class iterOwner implements ExpressionOwner
    {
        int m_index;
        
        iterOwner(final int index) {
            this.m_index = index;
        }
        
        public Expression getExpression() {
            return UnionPathIterator.this.m_exprs[this.m_index];
        }
        
        public void setExpression(Expression exp) {
            if (!(exp instanceof LocPathIterator)) {
                final WalkingIterator wi = new WalkingIterator(UnionPathIterator.this.getPrefixResolver());
                final FilterExprWalker few = new FilterExprWalker(wi);
                wi.setFirstWalker(few);
                few.setInnerExpression(exp);
                wi.exprSetParent(UnionPathIterator.this);
                few.exprSetParent(wi);
                exp.exprSetParent(few);
                exp = wi;
            }
            else {
                exp.exprSetParent(UnionPathIterator.this);
            }
            UnionPathIterator.this.m_exprs[this.m_index] = (LocPathIterator)exp;
        }
    }
}
