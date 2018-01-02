// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.w3c.dom.Element;
import org.apache.xpath.XPathContext;
import org.apache.xml.serializer.SerializationHandler;
import org.xml.sax.SAXException;
import java.util.Enumeration;
import org.xml.sax.helpers.NamespaceSupport;
import javax.xml.transform.SourceLocator;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.Node;
import java.util.Vector;
import org.apache.xpath.WhitespaceStrippingElementMatcher;
import org.apache.xpath.ExpressionNode;
import java.io.Serializable;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.UnImplNode;

public class ElemTemplateElement extends UnImplNode implements PrefixResolver, Serializable, ExpressionNode, WhitespaceStrippingElementMatcher, XSLTVisitable
{
    private int m_lineNumber;
    private int m_endLineNumber;
    private int m_columnNumber;
    private int m_endColumnNumber;
    private boolean m_defaultSpace;
    private boolean m_hasTextLitOnly;
    protected boolean m_hasVariableDecl;
    private Vector m_declaredPrefixes;
    Vector m_prefixTable;
    protected int m_docOrderNumber;
    protected ElemTemplateElement m_parentNode;
    ElemTemplateElement m_nextSibling;
    ElemTemplateElement m_firstChild;
    private transient Node m_DOMBackPointer;
    
    public ElemTemplateElement() {
        this.m_defaultSpace = true;
        this.m_hasTextLitOnly = false;
        this.m_hasVariableDecl = false;
        this.m_docOrderNumber = -1;
    }
    
    public boolean isCompiledTemplate() {
        return false;
    }
    
    public int getXSLToken() {
        return -1;
    }
    
    public String getNodeName() {
        return "Unknown XSLT Element";
    }
    
    public String getLocalName() {
        return this.getNodeName();
    }
    
    public void runtimeInit(final TransformerImpl transformer) throws TransformerException {
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
    }
    
    public StylesheetComposed getStylesheetComposed() {
        return this.m_parentNode.getStylesheetComposed();
    }
    
    public Stylesheet getStylesheet() {
        return (null == this.m_parentNode) ? null : this.m_parentNode.getStylesheet();
    }
    
    public StylesheetRoot getStylesheetRoot() {
        return this.m_parentNode.getStylesheetRoot();
    }
    
    public void recompose(final StylesheetRoot root) throws TransformerException {
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        this.resolvePrefixTables();
        final ElemTemplateElement t = this.getFirstChildElem();
        this.m_hasTextLitOnly = (t != null && t.getXSLToken() == 78 && t.getNextSiblingElem() == null);
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        cstate.pushStackMark();
    }
    
    public void endCompose(final StylesheetRoot sroot) throws TransformerException {
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        cstate.popStackMark();
    }
    
    public void error(final String msg, final Object[] args) {
        final String themsg = XSLMessages.createMessage(msg, args);
        throw new RuntimeException(XSLMessages.createMessage("ER_ELEMTEMPLATEELEM_ERR", new Object[] { themsg }));
    }
    
    public void error(final String msg) {
        this.error(msg, null);
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        if (null == newChild) {
            this.error("ER_NULL_CHILD", null);
        }
        final ElemTemplateElement elem = (ElemTemplateElement)newChild;
        if (null == this.m_firstChild) {
            this.m_firstChild = elem;
        }
        else {
            final ElemTemplateElement last = (ElemTemplateElement)this.getLastChild();
            last.m_nextSibling = elem;
        }
        elem.m_parentNode = this;
        return newChild;
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement elem) {
        if (null == elem) {
            this.error("ER_NULL_CHILD", null);
        }
        if (null == this.m_firstChild) {
            this.m_firstChild = elem;
        }
        else {
            final ElemTemplateElement last = this.getLastChildElem();
            last.m_nextSibling = elem;
        }
        elem.setParentElem(this);
        return elem;
    }
    
    public boolean hasChildNodes() {
        return null != this.m_firstChild;
    }
    
    public short getNodeType() {
        return 1;
    }
    
    public NodeList getChildNodes() {
        return this;
    }
    
    public ElemTemplateElement removeChild(final ElemTemplateElement childETE) {
        if (childETE == null || childETE.m_parentNode != this) {
            return null;
        }
        if (childETE == this.m_firstChild) {
            this.m_firstChild = childETE.m_nextSibling;
        }
        else {
            final ElemTemplateElement prev = childETE.getPreviousSiblingElem();
            prev.m_nextSibling = childETE.m_nextSibling;
        }
        childETE.m_parentNode = null;
        childETE.m_nextSibling = null;
        return childETE;
    }
    
    public Node replaceChild(final Node newChild, final Node oldChild) throws DOMException {
        if (oldChild == null || oldChild.getParentNode() != this) {
            return null;
        }
        final ElemTemplateElement newChildElem = (ElemTemplateElement)newChild;
        final ElemTemplateElement oldChildElem = (ElemTemplateElement)oldChild;
        final ElemTemplateElement prev = (ElemTemplateElement)oldChildElem.getPreviousSibling();
        if (null != prev) {
            prev.m_nextSibling = newChildElem;
        }
        if (this.m_firstChild == oldChildElem) {
            this.m_firstChild = newChildElem;
        }
        newChildElem.m_parentNode = this;
        oldChildElem.m_parentNode = null;
        newChildElem.m_nextSibling = oldChildElem.m_nextSibling;
        oldChildElem.m_nextSibling = null;
        return newChildElem;
    }
    
    public Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
        if (null == refChild) {
            this.appendChild(newChild);
            return newChild;
        }
        if (newChild == refChild) {
            return newChild;
        }
        Node node = this.m_firstChild;
        Node prev = null;
        boolean foundit = false;
        while (null != node) {
            if (newChild == node) {
                if (null != prev) {
                    ((ElemTemplateElement)prev).m_nextSibling = (ElemTemplateElement)node.getNextSibling();
                }
                else {
                    this.m_firstChild = (ElemTemplateElement)node.getNextSibling();
                }
                node = node.getNextSibling();
            }
            else if (refChild == node) {
                if (null != prev) {
                    ((ElemTemplateElement)prev).m_nextSibling = (ElemTemplateElement)newChild;
                }
                else {
                    this.m_firstChild = (ElemTemplateElement)newChild;
                }
                ((ElemTemplateElement)newChild).m_nextSibling = (ElemTemplateElement)refChild;
                ((ElemTemplateElement)newChild).setParentElem(this);
                prev = newChild;
                node = node.getNextSibling();
                foundit = true;
            }
            else {
                prev = node;
                node = node.getNextSibling();
            }
        }
        if (!foundit) {
            throw new DOMException((short)8, "refChild was not found in insertBefore method!");
        }
        return newChild;
    }
    
    public ElemTemplateElement replaceChild(final ElemTemplateElement newChildElem, final ElemTemplateElement oldChildElem) {
        if (oldChildElem == null || oldChildElem.getParentElem() != this) {
            return null;
        }
        final ElemTemplateElement prev = oldChildElem.getPreviousSiblingElem();
        if (null != prev) {
            prev.m_nextSibling = newChildElem;
        }
        if (this.m_firstChild == oldChildElem) {
            this.m_firstChild = newChildElem;
        }
        newChildElem.m_parentNode = this;
        oldChildElem.m_parentNode = null;
        newChildElem.m_nextSibling = oldChildElem.m_nextSibling;
        oldChildElem.m_nextSibling = null;
        return newChildElem;
    }
    
    public int getLength() {
        int count = 0;
        for (ElemTemplateElement node = this.m_firstChild; node != null; node = node.m_nextSibling) {
            ++count;
        }
        return count;
    }
    
    public Node item(final int index) {
        ElemTemplateElement node = this.m_firstChild;
        for (int i = 0; i < index && node != null; node = node.m_nextSibling, ++i) {}
        return node;
    }
    
    public Document getOwnerDocument() {
        return this.getStylesheet();
    }
    
    public ElemTemplate getOwnerXSLTemplate() {
        ElemTemplateElement el = this;
        for (int type = el.getXSLToken(); null != el && type != 19; type = el.getXSLToken()) {
            el = el.getParentElem();
            if (null != el) {}
        }
        return (ElemTemplate)el;
    }
    
    public String getTagName() {
        return this.getNodeName();
    }
    
    public boolean hasTextLitOnly() {
        return this.m_hasTextLitOnly;
    }
    
    public String getBaseIdentifier() {
        return this.getSystemId();
    }
    
    public int getEndLineNumber() {
        return this.m_endLineNumber;
    }
    
    public int getLineNumber() {
        return this.m_lineNumber;
    }
    
    public int getEndColumnNumber() {
        return this.m_endColumnNumber;
    }
    
    public int getColumnNumber() {
        return this.m_columnNumber;
    }
    
    public String getPublicId() {
        return (null != this.m_parentNode) ? this.m_parentNode.getPublicId() : null;
    }
    
    public String getSystemId() {
        final Stylesheet sheet = this.getStylesheet();
        return (sheet == null) ? null : sheet.getHref();
    }
    
    public void setLocaterInfo(final SourceLocator locator) {
        this.m_lineNumber = locator.getLineNumber();
        this.m_columnNumber = locator.getColumnNumber();
    }
    
    public void setEndLocaterInfo(final SourceLocator locator) {
        this.m_endLineNumber = locator.getLineNumber();
        this.m_endColumnNumber = locator.getColumnNumber();
    }
    
    public boolean hasVariableDecl() {
        return this.m_hasVariableDecl;
    }
    
    public void setXmlSpace(final int v) {
        this.m_defaultSpace = (2 == v);
    }
    
    public boolean getXmlSpace() {
        return this.m_defaultSpace;
    }
    
    public Vector getDeclaredPrefixes() {
        return this.m_declaredPrefixes;
    }
    
    public void setPrefixes(final NamespaceSupport nsSupport) throws TransformerException {
        this.setPrefixes(nsSupport, false);
    }
    
    public void setPrefixes(final NamespaceSupport nsSupport, final boolean excludeXSLDecl) throws TransformerException {
        final Enumeration decls = nsSupport.getDeclaredPrefixes();
        while (decls.hasMoreElements()) {
            final String prefix = decls.nextElement();
            if (null == this.m_declaredPrefixes) {
                this.m_declaredPrefixes = new Vector();
            }
            final String uri = nsSupport.getURI(prefix);
            if (excludeXSLDecl && uri.equals("http://www.w3.org/1999/XSL/Transform")) {
                continue;
            }
            final XMLNSDecl decl = new XMLNSDecl(prefix, uri, false);
            this.m_declaredPrefixes.addElement(decl);
        }
    }
    
    public String getNamespaceForPrefix(final String prefix, final Node context) {
        this.error("ER_CANT_RESOLVE_NSPREFIX", null);
        return null;
    }
    
    public String getNamespaceForPrefix(String prefix) {
        final Vector nsDecls = this.m_declaredPrefixes;
        if (null != nsDecls) {
            final int n = nsDecls.size();
            if (prefix.equals("#default")) {
                prefix = "";
            }
            for (int i = 0; i < n; ++i) {
                final XMLNSDecl decl = nsDecls.elementAt(i);
                if (prefix.equals(decl.getPrefix())) {
                    return decl.getURI();
                }
            }
        }
        if (null != this.m_parentNode) {
            return this.m_parentNode.getNamespaceForPrefix(prefix);
        }
        if ("xml".equals(prefix)) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        return null;
    }
    
    public Vector getPrefixes() {
        return this.m_prefixTable;
    }
    
    public boolean containsExcludeResultPrefix(final String prefix, final String uri) {
        final ElemTemplateElement parent = this.getParentElem();
        return null != parent && parent.containsExcludeResultPrefix(prefix, uri);
    }
    
    private boolean excludeResultNSDecl(final String prefix, final String uri) throws TransformerException {
        if (uri != null) {
            if (uri.equals("http://www.w3.org/1999/XSL/Transform") || this.getStylesheet().containsExtensionElementURI(uri)) {
                return true;
            }
            if (this.containsExcludeResultPrefix(prefix, uri)) {
                return true;
            }
        }
        return false;
    }
    
    public void resolvePrefixTables() throws TransformerException {
        this.m_prefixTable = null;
        if (null != this.m_declaredPrefixes) {
            final StylesheetRoot stylesheet = this.getStylesheetRoot();
            for (int n = this.m_declaredPrefixes.size(), i = 0; i < n; ++i) {
                XMLNSDecl decl = this.m_declaredPrefixes.elementAt(i);
                final String prefix = decl.getPrefix();
                String uri = decl.getURI();
                if (null == uri) {
                    uri = "";
                }
                final boolean shouldExclude = this.excludeResultNSDecl(prefix, uri);
                if (null == this.m_prefixTable) {
                    this.m_prefixTable = new Vector();
                }
                final NamespaceAlias nsAlias = stylesheet.getNamespaceAliasComposed(uri);
                if (null != nsAlias) {
                    decl = new XMLNSDecl(nsAlias.getStylesheetPrefix(), nsAlias.getResultNamespace(), shouldExclude);
                }
                else {
                    decl = new XMLNSDecl(prefix, uri, shouldExclude);
                }
                this.m_prefixTable.addElement(decl);
            }
        }
        final ElemTemplateElement parent = this.getParentNodeElem();
        if (null != parent) {
            final Vector prefixes = parent.m_prefixTable;
            if (null == this.m_prefixTable && !this.needToCheckExclude()) {
                this.m_prefixTable = parent.m_prefixTable;
            }
            else {
                for (int n2 = prefixes.size(), j = 0; j < n2; ++j) {
                    XMLNSDecl decl2 = prefixes.elementAt(j);
                    final boolean shouldExclude2 = this.excludeResultNSDecl(decl2.getPrefix(), decl2.getURI());
                    if (shouldExclude2 != decl2.getIsExcluded()) {
                        decl2 = new XMLNSDecl(decl2.getPrefix(), decl2.getURI(), shouldExclude2);
                    }
                    this.addOrReplaceDecls(decl2);
                }
            }
        }
        else if (null == this.m_prefixTable) {
            this.m_prefixTable = new Vector();
        }
    }
    
    void addOrReplaceDecls(final XMLNSDecl newDecl) {
        final int n = this.m_prefixTable.size();
        for (int i = n - 1; i >= 0; --i) {
            final XMLNSDecl decl = this.m_prefixTable.elementAt(i);
            if (decl.getPrefix().equals(newDecl.getPrefix())) {
                return;
            }
        }
        this.m_prefixTable.addElement(newDecl);
    }
    
    boolean needToCheckExclude() {
        return false;
    }
    
    void executeNSDecls(final TransformerImpl transformer) throws TransformerException {
        this.executeNSDecls(transformer, null);
    }
    
    void executeNSDecls(final TransformerImpl transformer, final String ignorePrefix) throws TransformerException {
        try {
            if (null != this.m_prefixTable) {
                final SerializationHandler rhandler = transformer.getResultTreeHandler();
                final int n = this.m_prefixTable.size();
                for (int i = n - 1; i >= 0; --i) {
                    final XMLNSDecl decl = this.m_prefixTable.elementAt(i);
                    if (!decl.getIsExcluded() && (null == ignorePrefix || !decl.getPrefix().equals(ignorePrefix))) {
                        rhandler.startPrefixMapping(decl.getPrefix(), decl.getURI(), true);
                    }
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
    }
    
    void unexecuteNSDecls(final TransformerImpl transformer) throws TransformerException {
        this.unexecuteNSDecls(transformer, null);
    }
    
    void unexecuteNSDecls(final TransformerImpl transformer, final String ignorePrefix) throws TransformerException {
        try {
            if (null != this.m_prefixTable) {
                final SerializationHandler rhandler = transformer.getResultTreeHandler();
                for (int n = this.m_prefixTable.size(), i = 0; i < n; ++i) {
                    final XMLNSDecl decl = this.m_prefixTable.elementAt(i);
                    if (!decl.getIsExcluded() && (null == ignorePrefix || !decl.getPrefix().equals(ignorePrefix))) {
                        rhandler.endPrefixMapping(decl.getPrefix());
                    }
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
    }
    
    public void setUid(final int i) {
        this.m_docOrderNumber = i;
    }
    
    public int getUid() {
        return this.m_docOrderNumber;
    }
    
    public Node getParentNode() {
        return this.m_parentNode;
    }
    
    public ElemTemplateElement getParentElem() {
        return this.m_parentNode;
    }
    
    public void setParentElem(final ElemTemplateElement p) {
        this.m_parentNode = p;
    }
    
    public Node getNextSibling() {
        return this.m_nextSibling;
    }
    
    public Node getPreviousSibling() {
        Node walker = this.getParentNode();
        Node prev = null;
        if (walker != null) {
            for (walker = walker.getFirstChild(); walker != null; walker = walker.getNextSibling()) {
                if (walker == this) {
                    return prev;
                }
                prev = walker;
            }
        }
        return null;
    }
    
    public ElemTemplateElement getPreviousSiblingElem() {
        ElemTemplateElement walker = this.getParentNodeElem();
        ElemTemplateElement prev = null;
        if (walker != null) {
            for (walker = walker.getFirstChildElem(); walker != null; walker = walker.getNextSiblingElem()) {
                if (walker == this) {
                    return prev;
                }
                prev = walker;
            }
        }
        return null;
    }
    
    public ElemTemplateElement getNextSiblingElem() {
        return this.m_nextSibling;
    }
    
    public ElemTemplateElement getParentNodeElem() {
        return this.m_parentNode;
    }
    
    public Node getFirstChild() {
        return this.m_firstChild;
    }
    
    public ElemTemplateElement getFirstChildElem() {
        return this.m_firstChild;
    }
    
    public Node getLastChild() {
        ElemTemplateElement lastChild = null;
        for (ElemTemplateElement node = this.m_firstChild; node != null; node = node.m_nextSibling) {
            lastChild = node;
        }
        return lastChild;
    }
    
    public ElemTemplateElement getLastChildElem() {
        ElemTemplateElement lastChild = null;
        for (ElemTemplateElement node = this.m_firstChild; node != null; node = node.m_nextSibling) {
            lastChild = node;
        }
        return lastChild;
    }
    
    public Node getDOMBackPointer() {
        return this.m_DOMBackPointer;
    }
    
    public void setDOMBackPointer(final Node n) {
        this.m_DOMBackPointer = n;
    }
    
    public int compareTo(final Object o) throws ClassCastException {
        final ElemTemplateElement ro = (ElemTemplateElement)o;
        final int roPrecedence = ro.getStylesheetComposed().getImportCountComposed();
        final int myPrecedence = this.getStylesheetComposed().getImportCountComposed();
        if (myPrecedence < roPrecedence) {
            return -1;
        }
        if (myPrecedence > roPrecedence) {
            return 1;
        }
        return this.getUid() - ro.getUid();
    }
    
    public boolean shouldStripWhiteSpace(final XPathContext support, final Element targetElement) throws TransformerException {
        final StylesheetRoot sroot = this.getStylesheetRoot();
        return null != sroot && sroot.shouldStripWhiteSpace(support, targetElement);
    }
    
    public boolean canStripWhiteSpace() {
        final StylesheetRoot sroot = this.getStylesheetRoot();
        return null != sroot && sroot.canStripWhiteSpace();
    }
    
    public boolean canAcceptVariables() {
        return true;
    }
    
    public void exprSetParent(final ExpressionNode n) {
        this.setParentElem((ElemTemplateElement)n);
    }
    
    public ExpressionNode exprGetParent() {
        return this.getParentElem();
    }
    
    public void exprAddChild(final ExpressionNode n, final int i) {
        this.appendChild((ElemTemplateElement)n);
    }
    
    public ExpressionNode exprGetChild(final int i) {
        return (ExpressionNode)this.item(i);
    }
    
    public int exprGetNumChildren() {
        return this.getLength();
    }
    
    protected boolean accept(final XSLTVisitor visitor) {
        return visitor.visitInstruction(this);
    }
    
    public void callVisitors(final XSLTVisitor visitor) {
        if (this.accept(visitor)) {
            this.callChildVisitors(visitor);
        }
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttributes) {
        for (ElemTemplateElement node = this.m_firstChild; node != null; node = node.m_nextSibling) {
            node.callVisitors(visitor);
        }
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor) {
        this.callChildVisitors(visitor, true);
    }
    
    public boolean handlesNullPrefixes() {
        return false;
    }
}
