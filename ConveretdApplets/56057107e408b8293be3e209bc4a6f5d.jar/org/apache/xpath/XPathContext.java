// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.QName;
import org.apache.xpath.axes.SubContextList;
import org.apache.xml.utils.DefaultErrorHandler;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.axes.ContextNodeList;
import org.w3c.dom.Node;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.SAXSourceLocator;
import org.apache.xml.utils.NodeVector;
import org.apache.xml.utils.PrefixResolver;
import java.util.Stack;
import org.apache.xalan.res.XSLMessages;
import org.xml.sax.XMLReader;
import javax.xml.transform.URIResolver;
import javax.xml.transform.ErrorListener;
import org.apache.xalan.extensions.ExtensionsTable;
import java.lang.reflect.Method;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.extensions.ExpressionContext;

public class XPathContext implements ExpressionContext
{
    SourceLocator m_saxLocation;
    private Object m_owner;
    private Method m_ownerGetErrorListener;
    private ExtensionsTable m_extensionsTable;
    private VariableStack m_variableStacks;
    private DOMHelper m_domHelper;
    private SourceTreeManager m_sourceTreeManager;
    private ErrorListener m_errorListener;
    private ErrorListener m_defaultErrorListener;
    private URIResolver m_uriResolver;
    public XMLReader m_primaryReader;
    private static XSLMessages m_XSLMessages;
    private Stack m_contextNodeLists;
    PrefixResolver m_currentPrefixResolver;
    private NodeVector m_currentNodes;
    private NodeVector m_currentExpressionNodes;
    private Stack m_axesIteratorStack;
    
    static {
        XPathContext.m_XSLMessages = new XSLMessages();
    }
    
    public XPathContext() {
        this.m_extensionsTable = new ExtensionsTable();
        this.m_variableStacks = new VariableStack();
        this.m_sourceTreeManager = new SourceTreeManager();
        this.m_contextNodeLists = new Stack();
        this.m_currentPrefixResolver = null;
        this.m_currentNodes = new NodeVector();
        this.m_currentExpressionNodes = new NodeVector();
        this.m_axesIteratorStack = new Stack();
    }
    
    public XPathContext(final Object owner) {
        this.m_extensionsTable = new ExtensionsTable();
        this.m_variableStacks = new VariableStack();
        this.m_sourceTreeManager = new SourceTreeManager();
        this.m_contextNodeLists = new Stack();
        this.m_currentPrefixResolver = null;
        this.m_currentNodes = new NodeVector();
        this.m_currentExpressionNodes = new NodeVector();
        this.m_axesIteratorStack = new Stack();
        this.m_owner = owner;
        try {
            this.m_ownerGetErrorListener = this.m_owner.getClass().getMethod("getErrorListener", (Class<?>[])new Class[0]);
        }
        catch (NoSuchMethodException ex) {}
    }
    
    private void assert(final boolean b, final String msg) throws TransformerException {
        final ErrorListener errorHandler = this.getErrorListener();
        if (errorHandler != null) {
            errorHandler.fatalError(new TransformerException(XSLMessages.createMessage(30, new Object[] { msg }), this.getSAXLocator()));
        }
    }
    
    public final Node getContextNode() {
        return this.getCurrentNode();
    }
    
    public final ContextNodeList getContextNodeList() {
        if (this.m_contextNodeLists.size() > 0) {
            return this.m_contextNodeLists.peek();
        }
        return null;
    }
    
    public final NodeIterator getContextNodes() {
        try {
            final ContextNodeList cnl = this.getContextNodeList();
            if (cnl != null) {
                return cnl.cloneWithReset();
            }
            return null;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public final Node getCurrentExpressionNode() {
        return this.m_currentExpressionNodes.peepOrNull();
    }
    
    public final Node getCurrentNode() {
        return this.m_currentNodes.peepOrNull();
    }
    
    public final DOMHelper getDOMHelper() {
        if (this.m_domHelper == null) {
            this.m_domHelper = new DOM2Helper();
        }
        return this.m_domHelper;
    }
    
    public final ErrorListener getErrorListener() {
        if (this.m_errorListener != null) {
            return this.m_errorListener;
        }
        ErrorListener retval = null;
        try {
            if (this.m_ownerGetErrorListener != null) {
                retval = (ErrorListener)this.m_ownerGetErrorListener.invoke(this.m_owner, new Object[0]);
            }
        }
        catch (Exception ex) {}
        if (retval == null) {
            if (this.m_defaultErrorListener == null) {
                this.m_defaultErrorListener = new DefaultErrorHandler();
            }
            retval = this.m_defaultErrorListener;
        }
        return retval;
    }
    
    public ExtensionsTable getExtensionsTable() {
        return this.m_extensionsTable;
    }
    
    public final PrefixResolver getNamespaceContext() {
        return this.m_currentPrefixResolver;
    }
    
    public Object getOwnerObject() {
        return this.m_owner;
    }
    
    public final XMLReader getPrimaryReader() {
        return this.m_primaryReader;
    }
    
    public SourceLocator getSAXLocator() {
        return this.m_saxLocation;
    }
    
    public final SourceTreeManager getSourceTreeManager() {
        return this.m_sourceTreeManager;
    }
    
    public SubContextList getSubContextList() {
        return this.m_axesIteratorStack.isEmpty() ? null : this.m_axesIteratorStack.peek();
    }
    
    public final URIResolver getURIResolver() {
        return this.m_uriResolver;
    }
    
    public VariableStack getVarStack() {
        return this.m_variableStacks;
    }
    
    public XObject getVariable(final QName qname) throws TransformerException {
        return this.getVarStack().getVariable(this, qname);
    }
    
    public final void popContextNodeList() {
        this.m_contextNodeLists.pop();
    }
    
    public final void popCurrentExpressionNode() {
        this.m_currentExpressionNodes.popQuick();
    }
    
    public final void popCurrentNode() {
        this.m_currentNodes.popQuick();
    }
    
    public final void popCurrentNodeAndExpression() {
        this.m_currentNodes.popQuick();
        this.m_currentExpressionNodes.popQuick();
    }
    
    public final void popSubContextList() {
        this.m_axesIteratorStack.pop();
    }
    
    public final void pushContextNodeList(final ContextNodeList nl) {
        this.m_contextNodeLists.push(nl);
    }
    
    public final void pushCurrentExpressionNode(final Node n) {
        this.m_currentExpressionNodes.push(n);
    }
    
    public final void pushCurrentNode(final Node n) {
        this.m_currentNodes.push(n);
    }
    
    public final void pushCurrentNodeAndExpression(final Node cn, final Node en) {
        this.m_currentNodes.push(cn);
        this.m_currentExpressionNodes.push(en);
    }
    
    public final void pushSubContextList(final SubContextList iter) {
        this.m_axesIteratorStack.push(iter);
    }
    
    public void reset() {
    }
    
    public void setDOMHelper(final DOMHelper helper) {
        this.m_domHelper = helper;
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        if (listener == null) {
            throw new IllegalArgumentException("Null error handler");
        }
        this.m_errorListener = listener;
    }
    
    void setExtensionsTable(final ExtensionsTable table) {
        this.m_extensionsTable = table;
    }
    
    public final void setNamespaceContext(final PrefixResolver pr) {
        this.m_currentPrefixResolver = pr;
    }
    
    public void setPrimaryReader(final XMLReader reader) {
        this.m_primaryReader = reader;
    }
    
    public void setSAXLocator(final SourceLocator location) {
        this.m_saxLocation = location;
    }
    
    public void setSourceTreeManager(final SourceTreeManager mgr) {
        this.m_sourceTreeManager = mgr;
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this.m_uriResolver = resolver;
    }
    
    public void setVarStack(final VariableStack varStack) {
        this.m_variableStacks = varStack;
    }
    
    public final double toNumber(final Node n) {
        return XNodeSet.getNumberFromNode(n);
    }
    
    public final String toString(final Node n) {
        return XNodeSet.getStringFromNode(n);
    }
}
