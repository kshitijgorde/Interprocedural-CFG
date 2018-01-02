// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.QName;
import org.apache.xml.utils.XMLString;
import org.apache.xpath.objects.XString;
import org.apache.xml.dtm.ref.DTMNodeIterator;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xalan.extensions.ExpressionContext;
import org.apache.xpath.axes.SubContextList;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.SAXSourceLocator;
import org.apache.xalan.res.XSLMessages;
import org.apache.xpath.res.XPATHMessages;
import org.apache.xml.utils.DefaultErrorHandler;
import javax.xml.transform.SourceLocator;
import java.util.Enumeration;
import org.apache.xpath.objects.XMLStringFactoryImpl;
import org.apache.xpath.axes.OneStepIteratorForward;
import org.apache.xml.dtm.DTMFilter;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.dtm.DTMIterator;
import org.w3c.dom.Node;
import org.apache.xml.dtm.DTM;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Source;
import org.apache.xml.utils.NodeVector;
import java.util.Stack;
import org.xml.sax.XMLReader;
import javax.xml.transform.URIResolver;
import javax.xml.transform.ErrorListener;
import java.lang.reflect.Method;
import org.apache.xml.utils.ObjectStack;
import org.apache.xml.dtm.ref.sax2dtm.SAX2RTFDTM;
import java.util.Vector;
import org.apache.xml.utils.IntStack;
import org.apache.xml.dtm.DTMManager;

public class XPathContext extends DTMManager
{
    IntStack m_last_pushed_rtfdtm;
    private Vector m_rtfdtm_stack;
    private int m_which_rtfdtm;
    private SAX2RTFDTM m_global_rtfdtm;
    protected DTMManager m_dtmManager;
    ObjectStack m_saxLocations;
    private Object m_owner;
    private Method m_ownerGetErrorListener;
    private VariableStack m_variableStacks;
    private SourceTreeManager m_sourceTreeManager;
    private ErrorListener m_errorListener;
    private ErrorListener m_defaultErrorListener;
    private URIResolver m_uriResolver;
    public XMLReader m_primaryReader;
    private Stack m_contextNodeLists;
    public static final int RECURSIONLIMIT = 4096;
    private IntStack m_currentNodes;
    private NodeVector m_iteratorRoots;
    private NodeVector m_predicateRoots;
    private IntStack m_currentExpressionNodes;
    private IntStack m_predicatePos;
    private ObjectStack m_prefixResolvers;
    private Stack m_axesIteratorStack;
    XPathExpressionContext expressionContext;
    
    public DTMManager getDTMManager() {
        return this.m_dtmManager;
    }
    
    public DTM getDTM(final Source source, final boolean unique, final DTMWSFilter wsfilter, final boolean incremental, final boolean doIndexing) {
        return this.m_dtmManager.getDTM(source, unique, wsfilter, incremental, doIndexing);
    }
    
    public DTM getDTM(final int nodeHandle) {
        return this.m_dtmManager.getDTM(nodeHandle);
    }
    
    public int getDTMHandleFromNode(final Node node) {
        return this.m_dtmManager.getDTMHandleFromNode(node);
    }
    
    public int getDTMIdentity(final DTM dtm) {
        return this.m_dtmManager.getDTMIdentity(dtm);
    }
    
    public DTM createDocumentFragment() {
        return this.m_dtmManager.createDocumentFragment();
    }
    
    public boolean release(final DTM dtm, final boolean shouldHardDelete) {
        return (this.m_rtfdtm_stack == null || !this.m_rtfdtm_stack.contains(dtm)) && this.m_dtmManager.release(dtm, shouldHardDelete);
    }
    
    public DTMIterator createDTMIterator(final Object xpathCompiler, final int pos) {
        return this.m_dtmManager.createDTMIterator(xpathCompiler, pos);
    }
    
    public DTMIterator createDTMIterator(final String xpathString, final PrefixResolver presolver) {
        return this.m_dtmManager.createDTMIterator(xpathString, presolver);
    }
    
    public DTMIterator createDTMIterator(final int whatToShow, final DTMFilter filter, final boolean entityReferenceExpansion) {
        return this.m_dtmManager.createDTMIterator(whatToShow, filter, entityReferenceExpansion);
    }
    
    public DTMIterator createDTMIterator(final int node) {
        final DTMIterator iter = new OneStepIteratorForward(13);
        iter.setRoot(node, this);
        return iter;
    }
    
    public XPathContext() {
        this.m_last_pushed_rtfdtm = new IntStack();
        this.m_rtfdtm_stack = null;
        this.m_which_rtfdtm = -1;
        this.m_global_rtfdtm = null;
        this.m_dtmManager = DTMManager.newInstance(XMLStringFactoryImpl.getFactory());
        this.m_saxLocations = new ObjectStack(4096);
        this.m_variableStacks = new VariableStack();
        this.m_sourceTreeManager = new SourceTreeManager();
        this.m_contextNodeLists = new Stack();
        this.m_currentNodes = new IntStack(4096);
        this.m_iteratorRoots = new NodeVector();
        this.m_predicateRoots = new NodeVector();
        this.m_currentExpressionNodes = new IntStack(4096);
        this.m_predicatePos = new IntStack();
        this.m_prefixResolvers = new ObjectStack(4096);
        this.m_axesIteratorStack = new Stack();
        this.expressionContext = new XPathExpressionContext();
        this.m_prefixResolvers.push(null);
        this.m_currentNodes.push(-1);
        this.m_currentExpressionNodes.push(-1);
        this.m_saxLocations.push(null);
    }
    
    public XPathContext(final Object owner) {
        this.m_last_pushed_rtfdtm = new IntStack();
        this.m_rtfdtm_stack = null;
        this.m_which_rtfdtm = -1;
        this.m_global_rtfdtm = null;
        this.m_dtmManager = DTMManager.newInstance(XMLStringFactoryImpl.getFactory());
        this.m_saxLocations = new ObjectStack(4096);
        this.m_variableStacks = new VariableStack();
        this.m_sourceTreeManager = new SourceTreeManager();
        this.m_contextNodeLists = new Stack();
        this.m_currentNodes = new IntStack(4096);
        this.m_iteratorRoots = new NodeVector();
        this.m_predicateRoots = new NodeVector();
        this.m_currentExpressionNodes = new IntStack(4096);
        this.m_predicatePos = new IntStack();
        this.m_prefixResolvers = new ObjectStack(4096);
        this.m_axesIteratorStack = new Stack();
        this.expressionContext = new XPathExpressionContext();
        this.m_owner = owner;
        try {
            this.m_ownerGetErrorListener = this.m_owner.getClass().getMethod("getErrorListener", (Class<?>[])new Class[0]);
        }
        catch (NoSuchMethodException ex) {}
        this.m_prefixResolvers.push(null);
        this.m_currentNodes.push(-1);
        this.m_currentExpressionNodes.push(-1);
        this.m_saxLocations.push(null);
    }
    
    public void reset() {
        if (this.m_rtfdtm_stack != null) {
            final Enumeration e = this.m_rtfdtm_stack.elements();
            while (e.hasMoreElements()) {
                this.m_dtmManager.release(e.nextElement(), true);
            }
        }
        this.m_rtfdtm_stack = null;
        this.m_which_rtfdtm = -1;
        if (this.m_global_rtfdtm != null) {
            this.m_dtmManager.release(this.m_global_rtfdtm, true);
        }
        this.m_global_rtfdtm = null;
        this.m_dtmManager = DTMManager.newInstance(XMLStringFactoryImpl.getFactory());
        this.m_saxLocations.removeAllElements();
        this.m_axesIteratorStack.removeAllElements();
        this.m_contextNodeLists.removeAllElements();
        this.m_currentExpressionNodes.removeAllElements();
        this.m_currentNodes.removeAllElements();
        this.m_iteratorRoots.RemoveAllNoClear();
        this.m_predicatePos.removeAllElements();
        this.m_predicateRoots.RemoveAllNoClear();
        this.m_prefixResolvers.removeAllElements();
        this.m_prefixResolvers.push(null);
        this.m_currentNodes.push(-1);
        this.m_currentExpressionNodes.push(-1);
        this.m_saxLocations.push(null);
    }
    
    public void setSAXLocator(final SourceLocator location) {
        this.m_saxLocations.setTop(location);
    }
    
    public void pushSAXLocator(final SourceLocator location) {
        this.m_saxLocations.push(location);
    }
    
    public void pushSAXLocatorNull() {
        this.m_saxLocations.push(null);
    }
    
    public void popSAXLocator() {
        this.m_saxLocations.pop();
    }
    
    public SourceLocator getSAXLocator() {
        return (SourceLocator)this.m_saxLocations.peek();
    }
    
    public Object getOwnerObject() {
        return this.m_owner;
    }
    
    public final VariableStack getVarStack() {
        return this.m_variableStacks;
    }
    
    public final void setVarStack(final VariableStack varStack) {
        this.m_variableStacks = varStack;
    }
    
    public final SourceTreeManager getSourceTreeManager() {
        return this.m_sourceTreeManager;
    }
    
    public void setSourceTreeManager(final SourceTreeManager mgr) {
        this.m_sourceTreeManager = mgr;
    }
    
    public final ErrorListener getErrorListener() {
        if (null != this.m_errorListener) {
            return this.m_errorListener;
        }
        ErrorListener retval = null;
        try {
            if (null != this.m_ownerGetErrorListener) {
                retval = (ErrorListener)this.m_ownerGetErrorListener.invoke(this.m_owner, new Object[0]);
            }
        }
        catch (Exception ex) {}
        if (null == retval) {
            if (null == this.m_defaultErrorListener) {
                this.m_defaultErrorListener = new DefaultErrorHandler();
            }
            retval = this.m_defaultErrorListener;
        }
        return retval;
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        if (listener == null) {
            throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", null));
        }
        this.m_errorListener = listener;
    }
    
    public final URIResolver getURIResolver() {
        return this.m_uriResolver;
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this.m_uriResolver = resolver;
    }
    
    public final XMLReader getPrimaryReader() {
        return this.m_primaryReader;
    }
    
    public void setPrimaryReader(final XMLReader reader) {
        this.m_primaryReader = reader;
    }
    
    private void assertion(final boolean b, final String msg) throws TransformerException {
        if (!b) {
            final ErrorListener errorHandler = this.getErrorListener();
            if (errorHandler != null) {
                errorHandler.fatalError(new TransformerException(XSLMessages.createMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { msg }), this.getSAXLocator()));
            }
        }
    }
    
    public Stack getContextNodeListsStack() {
        return this.m_contextNodeLists;
    }
    
    public void setContextNodeListsStack(final Stack s) {
        this.m_contextNodeLists = s;
    }
    
    public final DTMIterator getContextNodeList() {
        if (this.m_contextNodeLists.size() > 0) {
            return this.m_contextNodeLists.peek();
        }
        return null;
    }
    
    public final void pushContextNodeList(final DTMIterator nl) {
        this.m_contextNodeLists.push(nl);
    }
    
    public final void popContextNodeList() {
        if (this.m_contextNodeLists.isEmpty()) {
            System.err.println("Warning: popContextNodeList when stack is empty!");
        }
        else {
            this.m_contextNodeLists.pop();
        }
    }
    
    public IntStack getCurrentNodeStack() {
        return this.m_currentNodes;
    }
    
    public void setCurrentNodeStack(final IntStack nv) {
        this.m_currentNodes = nv;
    }
    
    public final int getCurrentNode() {
        return this.m_currentNodes.peek();
    }
    
    public final void pushCurrentNodeAndExpression(final int cn, final int en) {
        this.m_currentNodes.push(cn);
        this.m_currentExpressionNodes.push(cn);
    }
    
    public final void popCurrentNodeAndExpression() {
        this.m_currentNodes.quickPop(1);
        this.m_currentExpressionNodes.quickPop(1);
    }
    
    public final void pushExpressionState(final int cn, final int en, final PrefixResolver nc) {
        this.m_currentNodes.push(cn);
        this.m_currentExpressionNodes.push(cn);
        this.m_prefixResolvers.push(nc);
    }
    
    public final void popExpressionState() {
        this.m_currentNodes.quickPop(1);
        this.m_currentExpressionNodes.quickPop(1);
        this.m_prefixResolvers.pop();
    }
    
    public final void pushCurrentNode(final int n) {
        this.m_currentNodes.push(n);
    }
    
    public final void popCurrentNode() {
        this.m_currentNodes.quickPop(1);
    }
    
    public final void pushPredicateRoot(final int n) {
        this.m_predicateRoots.push(n);
    }
    
    public final void popPredicateRoot() {
        this.m_predicateRoots.popQuick();
    }
    
    public final int getPredicateRoot() {
        return this.m_predicateRoots.peepOrNull();
    }
    
    public final void pushIteratorRoot(final int n) {
        this.m_iteratorRoots.push(n);
    }
    
    public final void popIteratorRoot() {
        this.m_iteratorRoots.popQuick();
    }
    
    public final int getIteratorRoot() {
        return this.m_iteratorRoots.peepOrNull();
    }
    
    public IntStack getCurrentExpressionNodeStack() {
        return this.m_currentExpressionNodes;
    }
    
    public void setCurrentExpressionNodeStack(final IntStack nv) {
        this.m_currentExpressionNodes = nv;
    }
    
    public final int getPredicatePos() {
        return this.m_predicatePos.peek();
    }
    
    public final void pushPredicatePos(final int n) {
        this.m_predicatePos.push(n);
    }
    
    public final void popPredicatePos() {
        this.m_predicatePos.pop();
    }
    
    public final int getCurrentExpressionNode() {
        return this.m_currentExpressionNodes.peek();
    }
    
    public final void pushCurrentExpressionNode(final int n) {
        this.m_currentExpressionNodes.push(n);
    }
    
    public final void popCurrentExpressionNode() {
        this.m_currentExpressionNodes.quickPop(1);
    }
    
    public final PrefixResolver getNamespaceContext() {
        return (PrefixResolver)this.m_prefixResolvers.peek();
    }
    
    public final void setNamespaceContext(final PrefixResolver pr) {
        this.m_prefixResolvers.setTop(pr);
    }
    
    public final void pushNamespaceContext(final PrefixResolver pr) {
        this.m_prefixResolvers.push(pr);
    }
    
    public final void pushNamespaceContextNull() {
        this.m_prefixResolvers.push(null);
    }
    
    public final void popNamespaceContext() {
        this.m_prefixResolvers.pop();
    }
    
    public Stack getAxesIteratorStackStacks() {
        return this.m_axesIteratorStack;
    }
    
    public void setAxesIteratorStackStacks(final Stack s) {
        this.m_axesIteratorStack = s;
    }
    
    public final void pushSubContextList(final SubContextList iter) {
        this.m_axesIteratorStack.push(iter);
    }
    
    public final void popSubContextList() {
        this.m_axesIteratorStack.pop();
    }
    
    public SubContextList getSubContextList() {
        return this.m_axesIteratorStack.isEmpty() ? null : this.m_axesIteratorStack.peek();
    }
    
    public SubContextList getCurrentNodeList() {
        return this.m_axesIteratorStack.isEmpty() ? null : ((SubContextList)this.m_axesIteratorStack.elementAt(0));
    }
    
    public final int getContextNode() {
        return this.getCurrentNode();
    }
    
    public final DTMIterator getContextNodes() {
        try {
            final DTMIterator cnl = this.getContextNodeList();
            if (null != cnl) {
                return cnl.cloneWithReset();
            }
            return null;
        }
        catch (CloneNotSupportedException cnse) {
            return null;
        }
    }
    
    public ExpressionContext getExpressionContext() {
        return this.expressionContext;
    }
    
    public DTM getGlobalRTFDTM() {
        if (this.m_global_rtfdtm == null || this.m_global_rtfdtm.isTreeIncomplete()) {
            this.m_global_rtfdtm = (SAX2RTFDTM)this.m_dtmManager.getDTM(null, true, null, false, false);
        }
        return this.m_global_rtfdtm;
    }
    
    public DTM getRTFDTM() {
        SAX2RTFDTM rtfdtm;
        if (this.m_rtfdtm_stack == null) {
            this.m_rtfdtm_stack = new Vector();
            rtfdtm = (SAX2RTFDTM)this.m_dtmManager.getDTM(null, true, null, false, false);
            this.m_rtfdtm_stack.addElement(rtfdtm);
            ++this.m_which_rtfdtm;
        }
        else if (this.m_which_rtfdtm < 0) {
            rtfdtm = this.m_rtfdtm_stack.elementAt(++this.m_which_rtfdtm);
        }
        else {
            rtfdtm = this.m_rtfdtm_stack.elementAt(this.m_which_rtfdtm);
            if (rtfdtm.isTreeIncomplete()) {
                if (++this.m_which_rtfdtm < this.m_rtfdtm_stack.size()) {
                    rtfdtm = this.m_rtfdtm_stack.elementAt(this.m_which_rtfdtm);
                }
                else {
                    rtfdtm = (SAX2RTFDTM)this.m_dtmManager.getDTM(null, true, null, false, false);
                    this.m_rtfdtm_stack.addElement(rtfdtm);
                }
            }
        }
        return rtfdtm;
    }
    
    public void pushRTFContext() {
        this.m_last_pushed_rtfdtm.push(this.m_which_rtfdtm);
        if (null != this.m_rtfdtm_stack) {
            ((SAX2RTFDTM)this.getRTFDTM()).pushRewindMark();
        }
    }
    
    public void popRTFContext() {
        final int previous = this.m_last_pushed_rtfdtm.pop();
        if (null == this.m_rtfdtm_stack) {
            return;
        }
        if (this.m_which_rtfdtm == previous) {
            if (previous >= 0) {
                final boolean isEmpty = this.m_rtfdtm_stack.elementAt(previous).popRewindMark();
            }
        }
        else {
            while (this.m_which_rtfdtm != previous) {
                final boolean isEmpty = this.m_rtfdtm_stack.elementAt(this.m_which_rtfdtm).popRewindMark();
                --this.m_which_rtfdtm;
            }
        }
    }
    
    public class XPathExpressionContext implements ExpressionContext
    {
        public XPathContext getXPathContext() {
            return XPathContext.this;
        }
        
        public DTMManager getDTMManager() {
            return XPathContext.this.m_dtmManager;
        }
        
        public Node getContextNode() {
            final int context = XPathContext.this.getCurrentNode();
            return XPathContext.this.getDTM(context).getNode(context);
        }
        
        public NodeIterator getContextNodes() {
            return new DTMNodeIterator(XPathContext.this.getContextNodeList());
        }
        
        public ErrorListener getErrorListener() {
            return XPathContext.this.getErrorListener();
        }
        
        public double toNumber(final Node n) {
            final int nodeHandle = XPathContext.this.getDTMHandleFromNode(n);
            final DTM dtm = XPathContext.this.getDTM(nodeHandle);
            final XString xobj = (XString)dtm.getStringValue(nodeHandle);
            return xobj.num();
        }
        
        public String toString(final Node n) {
            final int nodeHandle = XPathContext.this.getDTMHandleFromNode(n);
            final DTM dtm = XPathContext.this.getDTM(nodeHandle);
            final XMLString strVal = dtm.getStringValue(nodeHandle);
            return strVal.toString();
        }
        
        public final XObject getVariableOrParam(final QName qname) throws TransformerException {
            return XPathContext.this.m_variableStacks.getVariableOrParam(XPathContext.this, qname);
        }
    }
}
