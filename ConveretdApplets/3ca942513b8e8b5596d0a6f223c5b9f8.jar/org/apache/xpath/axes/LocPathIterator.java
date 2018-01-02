// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.patterns.NodeTest;
import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xml.dtm.DTMFilter;
import org.apache.xpath.res.XPATHMessages;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XObject;
import org.apache.xml.dtm.DTMManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.dtm.DTM;
import java.io.Serializable;
import org.apache.xml.dtm.DTMIterator;

public abstract class LocPathIterator extends PredicatedNodeTest implements Cloneable, DTMIterator, Serializable, PathComponent
{
    static final long serialVersionUID = -4602476357268405754L;
    protected boolean m_allowDetach;
    protected transient IteratorPool m_clones;
    protected transient DTM m_cdtm;
    transient int m_stackFrame;
    private boolean m_isTopLevel;
    public transient int m_lastFetched;
    protected transient int m_context;
    protected transient int m_currentContextNode;
    protected transient int m_pos;
    protected transient int m_length;
    private PrefixResolver m_prefixResolver;
    protected transient XPathContext m_execContext;
    
    protected LocPathIterator() {
        this.m_allowDetach = true;
        this.m_clones = new IteratorPool(this);
        this.m_stackFrame = -1;
        this.m_isTopLevel = false;
        this.m_lastFetched = -1;
        this.m_context = -1;
        this.m_currentContextNode = -1;
        this.m_pos = 0;
        this.m_length = -1;
    }
    
    protected LocPathIterator(final PrefixResolver nscontext) {
        this.m_allowDetach = true;
        this.m_clones = new IteratorPool(this);
        this.m_stackFrame = -1;
        this.m_isTopLevel = false;
        this.m_lastFetched = -1;
        this.m_context = -1;
        this.m_currentContextNode = -1;
        this.m_pos = 0;
        this.m_length = -1;
        this.setLocPathIterator(this);
        this.m_prefixResolver = nscontext;
    }
    
    protected LocPathIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        this(compiler, opPos, analysis, true);
    }
    
    protected LocPathIterator(final Compiler compiler, final int opPos, final int analysis, final boolean shouldLoadWalkers) throws TransformerException {
        this.m_allowDetach = true;
        this.m_clones = new IteratorPool(this);
        this.m_stackFrame = -1;
        this.m_isTopLevel = false;
        this.m_lastFetched = -1;
        this.m_context = -1;
        this.m_currentContextNode = -1;
        this.m_pos = 0;
        this.m_length = -1;
        this.setLocPathIterator(this);
    }
    
    public int getAnalysisBits() {
        final int axis = this.getAxis();
        final int bit = WalkerFactory.getAnalysisBitFromAxes(axis);
        return bit;
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, TransformerException {
        try {
            stream.defaultReadObject();
            this.m_clones = new IteratorPool(this);
        }
        catch (ClassNotFoundException cnfe) {
            throw new TransformerException(cnfe);
        }
    }
    
    public void setEnvironment(final Object environment) {
    }
    
    public DTM getDTM(final int nodeHandle) {
        return this.m_execContext.getDTM(nodeHandle);
    }
    
    public DTMManager getDTMManager() {
        return this.m_execContext.getDTMManager();
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final XNodeSet iter = new XNodeSet(this.m_clones.getInstance());
        iter.setRoot(xctxt.getCurrentNode(), xctxt);
        return iter;
    }
    
    public void executeCharsToContentHandler(final XPathContext xctxt, final ContentHandler handler) throws TransformerException, SAXException {
        final LocPathIterator clone = (LocPathIterator)this.m_clones.getInstance();
        final int current = xctxt.getCurrentNode();
        clone.setRoot(current, xctxt);
        final int node = clone.nextNode();
        final DTM dtm = clone.getDTM(node);
        clone.detach();
        if (node != -1) {
            dtm.dispatchCharactersEvents(node, handler, false);
        }
    }
    
    public DTMIterator asIterator(final XPathContext xctxt, final int contextNode) throws TransformerException {
        final XNodeSet iter = new XNodeSet(this.m_clones.getInstance());
        iter.setRoot(contextNode, xctxt);
        return iter;
    }
    
    public boolean isNodesetExpr() {
        return true;
    }
    
    public int asNode(final XPathContext xctxt) throws TransformerException {
        final DTMIterator iter = this.m_clones.getInstance();
        final int current = xctxt.getCurrentNode();
        iter.setRoot(current, xctxt);
        final int next = iter.nextNode();
        iter.detach();
        return next;
    }
    
    public boolean bool(final XPathContext xctxt) throws TransformerException {
        return this.asNode(xctxt) != -1;
    }
    
    public void setIsTopLevel(final boolean b) {
        this.m_isTopLevel = b;
    }
    
    public boolean getIsTopLevel() {
        return this.m_isTopLevel;
    }
    
    public void setRoot(final int context, final Object environment) {
        this.m_context = context;
        final XPathContext xctxt = (XPathContext)environment;
        this.m_execContext = xctxt;
        this.m_cdtm = xctxt.getDTM(context);
        this.m_currentContextNode = context;
        if (null == this.m_prefixResolver) {
            this.m_prefixResolver = xctxt.getNamespaceContext();
        }
        this.m_lastFetched = -1;
        super.m_foundLast = false;
        this.m_pos = 0;
        this.m_length = -1;
        if (this.m_isTopLevel) {
            this.m_stackFrame = xctxt.getVarStack().getStackFrame();
        }
    }
    
    protected void setNextPosition(final int next) {
        this.assertion(false, "setNextPosition not supported in this iterator!");
    }
    
    public final int getCurrentPos() {
        return this.m_pos;
    }
    
    public void setShouldCacheNodes(final boolean b) {
        this.assertion(false, "setShouldCacheNodes not supported by this iterater!");
    }
    
    public boolean isMutable() {
        return false;
    }
    
    public void setCurrentPos(final int i) {
        this.assertion(false, "setCurrentPos not supported by this iterator!");
    }
    
    public void incrementCurrentPos() {
        ++this.m_pos;
    }
    
    public int size() {
        this.assertion(false, "size() not supported by this iterator!");
        return 0;
    }
    
    public int item(final int index) {
        this.assertion(false, "item(int index) not supported by this iterator!");
        return 0;
    }
    
    public void setItem(final int node, final int index) {
        this.assertion(false, "setItem not supported by this iterator!");
    }
    
    public int getLength() {
        final boolean isPredicateTest = this == this.m_execContext.getSubContextList();
        final int predCount = this.getPredicateCount();
        if (-1 != this.m_length && isPredicateTest && super.m_predicateIndex < 1) {
            return this.m_length;
        }
        if (super.m_foundLast) {
            return this.m_pos;
        }
        int pos = (super.m_predicateIndex >= 0) ? this.getProximityPosition() : this.m_pos;
        LocPathIterator clone;
        try {
            clone = (LocPathIterator)this.clone();
        }
        catch (CloneNotSupportedException cnse) {
            return -1;
        }
        if (predCount > 0 && isPredicateTest) {
            clone.m_predCount = super.m_predicateIndex;
        }
        int next;
        while (-1 != (next = clone.nextNode())) {
            ++pos;
        }
        if (isPredicateTest && super.m_predicateIndex < 1) {
            this.m_length = pos;
        }
        return pos;
    }
    
    public boolean isFresh() {
        return this.m_pos == 0;
    }
    
    public int previousNode() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_CANNOT_ITERATE", null));
    }
    
    public int getWhatToShow() {
        return -17;
    }
    
    public DTMFilter getFilter() {
        return null;
    }
    
    public int getRoot() {
        return this.m_context;
    }
    
    public boolean getExpandEntityReferences() {
        return true;
    }
    
    public void allowDetachToRelease(final boolean allowRelease) {
        this.m_allowDetach = allowRelease;
    }
    
    public void detach() {
        if (this.m_allowDetach) {
            this.m_execContext = null;
            this.m_cdtm = null;
            this.m_length = -1;
            this.m_pos = 0;
            this.m_lastFetched = -1;
            this.m_context = -1;
            this.m_currentContextNode = -1;
            this.m_clones.freeInstance(this);
        }
    }
    
    public void reset() {
        this.assertion(false, "This iterator can not reset!");
    }
    
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        final LocPathIterator clone = (LocPathIterator)this.m_clones.getInstanceOrThrow();
        clone.m_execContext = this.m_execContext;
        clone.m_cdtm = this.m_cdtm;
        clone.m_context = this.m_context;
        clone.m_currentContextNode = this.m_currentContextNode;
        clone.m_stackFrame = this.m_stackFrame;
        return clone;
    }
    
    public abstract int nextNode();
    
    protected int returnNextNode(final int nextNode) {
        if (-1 != nextNode) {
            ++this.m_pos;
        }
        if (-1 == (this.m_lastFetched = nextNode)) {
            super.m_foundLast = true;
        }
        return nextNode;
    }
    
    public int getCurrentNode() {
        return this.m_lastFetched;
    }
    
    public void runTo(final int index) {
        if (super.m_foundLast || (index >= 0 && index <= this.getCurrentPos())) {
            return;
        }
        if (-1 == index) {
            int n;
            while (-1 != (n = this.nextNode())) {}
        }
        else {
            int n;
            while (-1 != (n = this.nextNode())) {
                if (this.getCurrentPos() >= index) {
                    break;
                }
            }
        }
    }
    
    public final boolean getFoundLast() {
        return super.m_foundLast;
    }
    
    public final XPathContext getXPathContext() {
        return this.m_execContext;
    }
    
    public final int getContext() {
        return this.m_context;
    }
    
    public final int getCurrentContextNode() {
        return this.m_currentContextNode;
    }
    
    public final void setCurrentContextNode(final int n) {
        this.m_currentContextNode = n;
    }
    
    public final PrefixResolver getPrefixResolver() {
        if (null == this.m_prefixResolver) {
            this.m_prefixResolver = (PrefixResolver)this.getExpressionOwner();
        }
        return this.m_prefixResolver;
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        if (visitor.visitLocationPath(owner, this)) {
            visitor.visitStep(owner, this);
            this.callPredicateVisitors(visitor);
        }
    }
    
    public boolean isDocOrdered() {
        return true;
    }
    
    public int getAxis() {
        return -1;
    }
    
    public int getLastPos(final XPathContext xctxt) {
        return this.getLength();
    }
}
