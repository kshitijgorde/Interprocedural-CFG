// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.DOMException;
import org.apache.xpath.VariableStack;
import org.w3c.dom.traversal.NodeFilter;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.compiler.OpMap;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;
import java.util.Vector;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.DOMHelper;
import org.apache.xpath.NodeSet;
import org.w3c.dom.Node;
import java.io.Serializable;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

public class LocPathIterator extends PredicatedNodeTest implements Cloneable, NodeIterator, ContextNodeList, NodeList, Serializable
{
    transient int m_waitingBottom;
    transient int m_varStackPos;
    transient int m_varStackContext;
    private boolean m_isTopLevel;
    private transient int m_last;
    public transient Node m_lastFetched;
    transient NodeSet m_cachedNodes;
    protected AxesWalker m_lastUsedWalker;
    protected AxesWalker m_firstWalker;
    protected transient boolean m_foundLast;
    protected transient DOMHelper m_dhelper;
    protected transient Node m_context;
    protected transient Node m_currentContextNode;
    protected PrefixResolver m_prefixResolver;
    protected transient XPathContext m_execContext;
    protected transient int m_next;
    private transient Vector m_waiting;
    protected int m_analysis;
    
    public LocPathIterator(final PrefixResolver nscontext) {
        this.m_waitingBottom = 0;
        this.m_varStackPos = -1;
        this.m_isTopLevel = false;
        this.m_last = 0;
        this.m_foundLast = false;
        this.m_next = 0;
        this.m_waiting = null;
        this.m_analysis = 0;
        this.setLocPathIterator(this);
        this.m_prefixResolver = nscontext;
    }
    
    public LocPathIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        this(compiler, opPos, analysis, true);
    }
    
    public LocPathIterator(final Compiler compiler, final int opPos, final int analysis, final boolean shouldLoadWalkers) throws TransformerException {
        this.m_waitingBottom = 0;
        this.m_varStackPos = -1;
        this.m_isTopLevel = false;
        this.m_last = 0;
        this.m_foundLast = false;
        this.m_next = 0;
        this.m_waiting = null;
        this.m_analysis = 0;
        this.m_analysis = analysis;
        this.setLocPathIterator(this);
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        if (shouldLoadWalkers) {
            this.m_firstWalker = WalkerFactory.loadWalkers(this, compiler, firstStepPos, 0);
            this.m_lastUsedWalker = this.m_firstWalker;
        }
    }
    
    public final void addToWaitList(final AxesWalker walker) {
        if (this.m_waiting == null) {
            this.m_waiting = new Vector();
        }
        this.m_waiting.addElement(walker);
    }
    
    public boolean canTraverseOutsideSubtree() {
        return (this.m_analysis & 0xDF86000) != 0x0 || (this.m_firstWalker != null && this.m_firstWalker.canTraverseOutsideSubtree()) || super.canTraverseOutsideSubtree();
    }
    
    public Object clone() throws CloneNotSupportedException {
        final LocPathIterator clone = (LocPathIterator)super.clone();
        if (this.m_firstWalker != null) {
            final Vector clones = (this.m_waiting != null) ? new Vector() : null;
            clone.m_firstWalker = this.m_firstWalker.cloneDeep(clone, clones);
            if (this.m_waiting != null) {
                clone.m_waiting = (Vector)this.m_waiting.clone();
                for (int n = this.m_waiting.size(), i = 0; i < n; ++i) {
                    final AxesWalker waiting = this.m_waiting.elementAt(i);
                    clone.m_waiting.setElementAt(waiting.cloneDeep(clone, clones), i);
                }
            }
        }
        return clone;
    }
    
    public NodeIterator cloneWithReset() throws CloneNotSupportedException {
        final LocPathIterator clone = (LocPathIterator)this.clone();
        clone.reset();
        return clone;
    }
    
    public void detach() {
        this.m_context = null;
        this.m_execContext = null;
        this.m_prefixResolver = null;
        this.m_dhelper = null;
        this.m_varStackPos = -1;
        this.m_varStackContext = 0;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        try {
            final LocPathIterator clone = (LocPathIterator)this.clone();
            clone.initContext(xctxt);
            return new XNodeSet(clone);
        }
        catch (CloneNotSupportedException ncse) {
            throw new TransformerException(ncse);
        }
    }
    
    int getAnalysis() {
        return this.m_analysis;
    }
    
    public NodeSet getCachedNodes() {
        return this.m_cachedNodes;
    }
    
    public final Node getContext() {
        return this.m_context;
    }
    
    public final Node getCurrentContextNode() {
        return this.m_currentContextNode;
    }
    
    public Node getCurrentNode() {
        return this.m_lastFetched;
    }
    
    public final int getCurrentPos() {
        return this.m_next;
    }
    
    public final DOMHelper getDOMHelper() {
        return this.m_dhelper;
    }
    
    public boolean getExpandEntityReferences() {
        return true;
    }
    
    public NodeFilter getFilter() {
        return null;
    }
    
    public final AxesWalker getFirstWalker() {
        return this.m_firstWalker;
    }
    
    public final boolean getFoundLast() {
        return this.m_foundLast;
    }
    
    public boolean getIsTopLevel() {
        return this.m_isTopLevel;
    }
    
    public int getLast() {
        return this.m_last;
    }
    
    public int getLastPos(final XPathContext xctxt) {
        int pos = this.getProximityPosition();
        LocPathIterator clone;
        try {
            clone = (LocPathIterator)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            return -1;
        }
        clone.setPredicateCount(clone.getPredicateCount() - 1);
        Node next;
        while ((next = clone.nextNode()) != null) {
            ++pos;
        }
        return pos;
    }
    
    public final AxesWalker getLastUsedWalker() {
        return this.m_lastUsedWalker;
    }
    
    public int getLength() {
        this.resetToCachedList();
        return this.m_cachedNodes.getLength();
    }
    
    public final PrefixResolver getPrefixResolver() {
        return this.m_prefixResolver;
    }
    
    public Node getRoot() {
        return this.m_context;
    }
    
    AxesWalker getWaiting(final int i) {
        return this.m_waiting.elementAt(i);
    }
    
    int getWaitingCount() {
        if (this.m_waiting == null) {
            return 0;
        }
        return this.m_waiting.size() - this.m_waitingBottom;
    }
    
    public int getWhatToShow() {
        return -17;
    }
    
    public final XPathContext getXPathContext() {
        return this.m_execContext;
    }
    
    void incrementNextPosition() {
        ++this.m_next;
    }
    
    public void initContext(final XPathContext execContext) {
        this.m_context = execContext.getCurrentNode();
        this.m_currentContextNode = execContext.getCurrentExpressionNode();
        this.m_execContext = execContext;
        this.m_prefixResolver = execContext.getNamespaceContext();
        this.m_dhelper = execContext.getDOMHelper();
        if (this.m_isTopLevel) {
            final VariableStack vars = execContext.getVarStack();
            this.m_varStackPos = vars.getSearchStartOrTop();
            this.m_varStackContext = vars.getContextPos();
        }
    }
    
    public boolean isFresh() {
        return this.m_next == 0;
    }
    
    public Node item(final int index) {
        this.resetToCachedList();
        return this.m_cachedNodes.item(index);
    }
    
    public Node nextNode() throws DOMException {
        if (this.m_cachedNodes != null && this.m_cachedNodes.getCurrentPos() < this.m_cachedNodes.size()) {
            final Node next = this.m_cachedNodes.nextNode();
            this.setCurrentPos(this.m_cachedNodes.getCurrentPos());
            return next;
        }
        if (this.m_varStackPos == -1) {
            if (this.m_firstWalker.getRoot() == null) {
                this.setNextPosition(0);
                this.m_firstWalker.setRoot(this.m_context);
                this.m_lastUsedWalker = this.m_firstWalker;
            }
            return this.returnNextNode(this.m_firstWalker.nextNode());
        }
        final VariableStack vars = this.m_execContext.getVarStack();
        final int savedStart = vars.getSearchStart();
        vars.setSearchStart(this.m_varStackPos);
        vars.pushContextPosition(this.m_varStackContext);
        if (this.m_firstWalker.getRoot() == null) {
            this.setNextPosition(0);
            this.m_firstWalker.setRoot(this.m_context);
            this.m_lastUsedWalker = this.m_firstWalker;
        }
        final Node n = this.returnNextNode(this.m_firstWalker.nextNode());
        vars.setSearchStart(savedStart);
        vars.popContextPosition();
        return n;
    }
    
    public Node previousNode() throws DOMException {
        if (this.m_cachedNodes == null) {
            throw new RuntimeException("This NodeSet can not iterate to a previous node!");
        }
        return this.m_cachedNodes.previousNode();
    }
    
    public final void removeFromWaitList(final AxesWalker walker) {
        if (this.m_waiting != null) {
            this.m_waiting.removeElement(walker);
        }
    }
    
    public void reset() {
        this.m_foundLast = false;
        this.m_lastFetched = null;
        this.m_next = 0;
        this.m_last = 0;
        this.m_waitingBottom = 0;
        if (this.m_firstWalker != null) {
            this.m_lastUsedWalker = this.m_firstWalker;
            this.m_firstWalker.setRoot(this.m_context);
            if (this.m_waiting != null) {
                this.m_waiting.removeAllElements();
            }
        }
    }
    
    private void resetToCachedList() {
        final int pos = this.getCurrentPos();
        if (this.m_cachedNodes == null || pos != 0) {
            this.setShouldCacheNodes(true);
        }
        this.runTo(-1);
        this.setCurrentPos(pos);
    }
    
    protected Node returnNextNode(final Node nextNode) {
        if (nextNode != null) {
            if (this.m_cachedNodes != null) {
                this.m_cachedNodes.addElement(nextNode);
            }
            this.incrementNextPosition();
        }
        if ((this.m_lastFetched = nextNode) == null) {
            this.m_foundLast = true;
        }
        return nextNode;
    }
    
    public void runTo(final int index) {
        if (this.m_foundLast || (index >= 0 && index <= this.getCurrentPos())) {
            return;
        }
        if (index == -1) {
            Node n;
            while ((n = this.nextNode()) != null) {}
        }
        else {
            Node n;
            while ((n = this.nextNode()) != null) {
                if (this.getCurrentPos() >= index) {
                    break;
                }
            }
        }
    }
    
    void setAnalysis(final int a) {
        this.m_analysis = a;
    }
    
    public final void setCurrentContextNode(final Node n) {
        this.m_currentContextNode = n;
    }
    
    public void setCurrentPos(final int i) {
        if (this.m_cachedNodes == null) {
            throw new RuntimeException("This NodeSet can not do indexing or counting functions!");
        }
        this.setNextPosition(i);
        this.m_cachedNodes.setCurrentPos(i);
    }
    
    public void setIsTopLevel(final boolean b) {
        this.m_isTopLevel = b;
    }
    
    public void setLast(final int last) {
        this.m_last = last;
    }
    
    public final void setLastUsedWalker(final AxesWalker walker) {
        this.m_lastUsedWalker = walker;
    }
    
    protected void setNextPosition(final int next) {
        this.m_next = next;
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
