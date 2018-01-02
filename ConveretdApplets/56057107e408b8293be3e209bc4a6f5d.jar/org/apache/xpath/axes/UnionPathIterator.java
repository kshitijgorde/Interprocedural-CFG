// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.DOMException;
import org.apache.xpath.DOMHelper;
import org.w3c.dom.traversal.NodeFilter;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XObject;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.OpMap;
import org.apache.xpath.compiler.Compiler;
import org.apache.xpath.XPathContext;
import org.w3c.dom.Node;
import org.apache.xpath.NodeSet;
import org.apache.xml.utils.ObjectPool;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.Expression;

public class UnionPathIterator extends Expression implements Cloneable, NodeIterator, ContextNodeList
{
    transient ObjectPool m_pool;
    transient NodeSet m_cachedNodes;
    protected transient int m_next;
    transient Node m_lastFetched;
    protected transient boolean m_foundLast;
    protected transient XPathContext m_execContext;
    protected transient Node m_context;
    protected transient Node m_currentContextNode;
    protected LocPathIterator[] m_iterators;
    private transient int m_last;
    
    public UnionPathIterator() {
        this.m_pool = new ObjectPool(this.getClass());
        this.m_cachedNodes = null;
        this.m_next = 0;
        this.m_foundLast = false;
        this.m_last = 0;
        this.m_iterators = null;
    }
    
    public UnionPathIterator(final Compiler compiler, int opPos) throws TransformerException {
        this.m_pool = new ObjectPool(this.getClass());
        this.m_cachedNodes = null;
        this.m_next = 0;
        this.m_foundLast = false;
        this.m_last = 0;
        opPos = OpMap.getFirstChildPos(opPos);
        this.loadLocationPaths(compiler, opPos, 0);
    }
    
    public void addIterator(final LocPathIterator iter) {
        if (this.m_iterators == null) {
            (this.m_iterators = new LocPathIterator[1])[0] = iter;
        }
        else {
            final LocPathIterator[] iters = this.m_iterators;
            final int len = this.m_iterators.length;
            System.arraycopy(iters, 0, this.m_iterators = new LocPathIterator[len + 1], 0, len);
            this.m_iterators[len] = iter;
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        final UnionPathIterator clone = (UnionPathIterator)super.clone();
        final int n = this.m_iterators.length;
        clone.m_iterators = new LocPathIterator[n];
        for (int i = 0; i < n; ++i) {
            clone.m_iterators[i] = (LocPathIterator)this.m_iterators[i].clone();
        }
        return clone;
    }
    
    public NodeIterator cloneWithReset() throws CloneNotSupportedException {
        final UnionPathIterator clone = (UnionPathIterator)this.clone();
        clone.reset();
        return clone;
    }
    
    protected LocPathIterator createLocPathIterator(final Compiler compiler, final int opPos) throws TransformerException {
        final LocPathIterator lpi = WalkerFactory.newLocPathIterator(compiler, opPos);
        if (compiler.getLocationPathDepth() <= 0) {
            lpi.setIsTopLevel(true);
        }
        return lpi;
    }
    
    public void detach() {
        this.m_context = null;
        this.m_execContext = null;
        this.m_context = null;
        for (int n = this.m_iterators.length, i = 0; i < n; ++i) {
            this.m_iterators[i].detach();
        }
        this.m_pool.freeInstance(this);
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        try {
            UnionPathIterator clone = (UnionPathIterator)this.m_pool.getInstanceIfFree();
            if (clone == null) {
                clone = (UnionPathIterator)this.clone();
            }
            clone.initContext(xctxt);
            return new XNodeSet(clone);
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public Node getCurrentContextNode() {
        return this.m_currentContextNode;
    }
    
    public Node getCurrentNode() {
        return this.m_lastFetched;
    }
    
    public int getCurrentPos() {
        return this.m_next;
    }
    
    public boolean getExpandEntityReferences() {
        return true;
    }
    
    public NodeFilter getFilter() {
        return null;
    }
    
    public int getLast() {
        return this.m_last;
    }
    
    public Node getRoot() {
        return this.m_context;
    }
    
    public int getWhatToShow() {
        return -17;
    }
    
    public void initContext(final XPathContext execContext) {
        this.m_execContext = execContext;
        this.m_currentContextNode = execContext.getCurrentExpressionNode();
        this.m_context = execContext.getCurrentNode();
        if (this.m_iterators != null) {
            for (int n = this.m_iterators.length, i = 0; i < n; ++i) {
                this.m_iterators[i].initContext(execContext);
                this.m_iterators[i].nextNode();
            }
        }
    }
    
    public boolean isFresh() {
        return this.m_next == 0;
    }
    
    protected void loadLocationPaths(final Compiler compiler, final int opPos, final int count) throws TransformerException {
        final int steptype = compiler.getOpMap()[opPos];
        if (steptype == 28) {
            this.loadLocationPaths(compiler, compiler.getNextOpPos(opPos), count + 1);
            this.m_iterators[count] = this.createLocPathIterator(compiler, opPos);
        }
        else {
            switch (steptype) {
                case 22:
                case 23:
                case 24:
                case 25: {
                    this.loadLocationPaths(compiler, compiler.getNextOpPos(opPos), count + 1);
                    final LocPathIterator iter = new LocPathIterator(compiler.getNamespaceContext());
                    if (compiler.getLocationPathDepth() <= 0) {
                        iter.setIsTopLevel(true);
                    }
                    (iter.m_firstWalker = new FilterExprWalker(iter)).init(compiler, opPos, steptype);
                    this.m_iterators[count] = iter;
                    break;
                }
                default: {
                    this.m_iterators = new LocPathIterator[count];
                    break;
                }
            }
        }
    }
    
    public Node nextNode() throws DOMException {
        if (this.m_cachedNodes != null && this.m_cachedNodes.getCurrentPos() < this.m_cachedNodes.size()) {
            return this.m_cachedNodes.nextNode();
        }
        if (this.m_foundLast) {
            return null;
        }
        Node earliestNode = null;
        if (this.m_iterators != null) {
            final int n = this.m_iterators.length;
            int iteratorUsed = -1;
            for (int i = 0; i < n; ++i) {
                final Node node = this.m_iterators[i].getCurrentNode();
                if (node != null) {
                    if (earliestNode == null) {
                        iteratorUsed = i;
                        earliestNode = node;
                    }
                    else if (node.equals(earliestNode)) {
                        this.m_iterators[i].nextNode();
                    }
                    else {
                        final DOMHelper dh = this.m_execContext.getDOMHelper();
                        if (dh.isNodeAfter(node, earliestNode)) {
                            iteratorUsed = i;
                            earliestNode = node;
                        }
                    }
                }
            }
            if (earliestNode != null) {
                this.m_iterators[iteratorUsed].nextNode();
                if (this.m_cachedNodes != null) {
                    this.m_cachedNodes.addElement(earliestNode);
                }
                ++this.m_next;
            }
            else {
                this.m_foundLast = true;
            }
        }
        return this.m_lastFetched = earliestNode;
    }
    
    public Node previousNode() throws DOMException {
        if (this.m_cachedNodes == null) {
            throw new RuntimeException("This NodeSet can not iterate to a previous node!");
        }
        return this.m_cachedNodes.previousNode();
    }
    
    public void reset() {
        this.m_foundLast = false;
        this.m_next = 0;
        this.m_last = 0;
        this.m_lastFetched = null;
        for (int n = this.m_iterators.length, i = 0; i < n; ++i) {
            this.m_iterators[i].reset();
            this.m_iterators[i].nextNode();
        }
    }
    
    public void runTo(final int index) {
        if (this.m_foundLast || (index >= 0 && index <= this.m_next)) {
            return;
        }
        Node n;
        while ((n = this.nextNode()) == null && this.m_next < index) {}
    }
    
    public void setCurrentPos(final int i) {
        if (this.m_cachedNodes == null) {
            throw new RuntimeException("This NodeSet can not do indexing or counting functions!");
        }
        this.m_next = i;
        this.m_cachedNodes.setCurrentPos(i);
    }
    
    public void setLast(final int last) {
        this.m_last = last;
    }
    
    public void setShouldCacheNodes(final boolean b) {
        if (b) {
            this.m_cachedNodes = new NodeSet();
        }
        else {
            this.m_cachedNodes = null;
        }
    }
    
    public int size() {
        if (this.m_cachedNodes == null) {
            return 0;
        }
        return this.m_cachedNodes.size();
    }
}
