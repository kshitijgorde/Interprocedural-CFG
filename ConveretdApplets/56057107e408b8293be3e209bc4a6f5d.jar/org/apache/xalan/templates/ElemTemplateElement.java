// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.w3c.dom.Element;
import org.apache.xpath.XPathContext;
import java.util.Enumeration;
import org.xml.sax.helpers.NamespaceSupport;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.apache.xalan.transformer.ResultTreeHandler;
import org.xml.sax.SAXException;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import java.util.Vector;
import org.apache.xpath.WhitespaceStrippingElementMatcher;
import javax.xml.transform.SourceLocator;
import java.io.Serializable;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.UnImplNode;

public class ElemTemplateElement extends UnImplNode implements PrefixResolver, Serializable, SourceLocator, WhitespaceStrippingElementMatcher
{
    private int m_lineNumber;
    private int m_columnNumber;
    private boolean m_defaultSpace;
    private Vector m_declaredPrefixes;
    Vector m_prefixTable;
    protected int m_docOrderNumber;
    protected ElemTemplateElement m_parentNode;
    ElemTemplateElement m_nextSibling;
    ElemTemplateElement m_firstChild;
    private transient Node m_DOMBackPointer;
    
    public ElemTemplateElement() {
        this.m_defaultSpace = true;
        this.m_docOrderNumber = -1;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        if (newChild == null) {
            this.error(20, null);
        }
        final ElemTemplateElement elem = (ElemTemplateElement)newChild;
        if (this.m_firstChild == null) {
            this.m_firstChild = elem;
        }
        else {
            final ElemTemplateElement last = (ElemTemplateElement)this.getLastChild();
            last.m_nextSibling = elem;
        }
        elem.m_parentNode = this;
        return newChild;
    }
    
    public boolean canStripWhiteSpace() {
        final StylesheetRoot sroot = this.getStylesheetRoot();
        return sroot != null && sroot.canStripWhiteSpace();
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
    
    public void compose() {
    }
    
    public boolean containsExcludeResultPrefix(final String prefix) {
        final ElemTemplateElement parent = this.getParentElem();
        return parent != null && parent.containsExcludeResultPrefix(prefix);
    }
    
    public void error(final int msg, final Object[] args) {
        final String themsg = XSLMessages.createMessage(msg, args);
        throw new RuntimeException(XSLMessages.createMessage(19, new Object[] { themsg }));
    }
    
    private boolean excludeResultNSDecl(final String prefix, final String uri) throws TransformerException {
        if (uri != null) {
            if (uri.equals("http://www.w3.org/1999/XSL/Transform") || this.getStylesheet().containsExtensionElementURI(uri) || uri.equals("http://xml.apache.org/xslt")) {
                return true;
            }
            if (this.containsExcludeResultPrefix(prefix)) {
                return true;
            }
        }
        return false;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
    }
    
    void executeNSDecls(final TransformerImpl transformer) throws TransformerException {
        try {
            if (this.m_prefixTable != null) {
                final ResultTreeHandler rhandler = transformer.getResultTreeHandler();
                final int n = this.m_prefixTable.size();
                for (int i = n - 1; i >= 0; --i) {
                    final XMLNSDecl decl = this.m_prefixTable.elementAt(i);
                    if (!decl.getIsExcluded()) {
                        rhandler.startPrefixMapping(decl.getPrefix(), decl.getURI(), true);
                    }
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
    }
    
    public String getBaseIdentifier() {
        return this.getSystemId();
    }
    
    public NodeList getChildNodes() {
        return this;
    }
    
    public int getColumnNumber() {
        return this.m_columnNumber;
    }
    
    public Node getDOMBackPointer() {
        return this.m_DOMBackPointer;
    }
    
    public Vector getDeclaredPrefixes() {
        return this.m_declaredPrefixes;
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
    
    public int getLength() {
        int count = 0;
        for (ElemTemplateElement node = this.m_firstChild; node != null; node = node.m_nextSibling) {
            ++count;
        }
        return count;
    }
    
    public int getLineNumber() {
        return this.m_lineNumber;
    }
    
    public String getNamespaceForPrefix(final String prefix) {
        final Vector nsDecls = this.m_declaredPrefixes;
        if (nsDecls != null) {
            for (int n = nsDecls.size(), i = 0; i < n; ++i) {
                final XMLNSDecl decl = nsDecls.elementAt(i);
                if (prefix.equals(decl.getPrefix())) {
                    return decl.getURI();
                }
            }
        }
        if (this.m_parentNode != null) {
            return this.m_parentNode.getNamespaceForPrefix(prefix);
        }
        return null;
    }
    
    public String getNamespaceForPrefix(final String prefix, final Node context) {
        this.error(16, null);
        return null;
    }
    
    public Node getNextSibling() {
        return this.m_nextSibling;
    }
    
    public ElemTemplateElement getNextSiblingElem() {
        return this.m_nextSibling;
    }
    
    public String getNodeName() {
        return "Unknown XSLT Element";
    }
    
    public short getNodeType() {
        return 1;
    }
    
    public Document getOwnerDocument() {
        return this.getStylesheet();
    }
    
    public ElemTemplateElement getParentElem() {
        return this.m_parentNode;
    }
    
    public Node getParentNode() {
        return this.m_parentNode;
    }
    
    public Vector getPrefixes() {
        return this.m_prefixTable;
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
    
    public String getPublicId() {
        return (this.m_parentNode != null) ? this.m_parentNode.getPublicId() : null;
    }
    
    public Stylesheet getStylesheet() {
        return this.m_parentNode.getStylesheet();
    }
    
    public StylesheetComposed getStylesheetComposed() {
        return this.m_parentNode.getStylesheetComposed();
    }
    
    public StylesheetRoot getStylesheetRoot() {
        return this.m_parentNode.getStylesheetRoot();
    }
    
    public String getSystemId() {
        return this.getStylesheet().getHref();
    }
    
    public String getTagName() {
        return this.getNodeName();
    }
    
    public int getUid() {
        return this.m_docOrderNumber;
    }
    
    public int getXSLToken() {
        return -1;
    }
    
    public boolean getXmlSpace() {
        return this.m_defaultSpace;
    }
    
    public boolean hasChildNodes() {
        return this.m_firstChild != null;
    }
    
    public boolean isCompiledTemplate() {
        return false;
    }
    
    protected boolean isValidNCName(final String s) {
        final int len = s.length();
        char c = s.charAt(0);
        if (!Character.isLetter(c) && c != '_') {
            return false;
        }
        if (len > 0) {
            for (int i = 1; i < len; ++i) {
                c = s.charAt(i);
                if (!Character.isLetterOrDigit(c) && c != '_' && c != '-' && c != '.') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Node item(final int index) {
        ElemTemplateElement node = this.m_firstChild;
        for (int i = 0; i < index && node != null; node = node.m_nextSibling, ++i) {}
        return node;
    }
    
    boolean needToCheckExclude() {
        return false;
    }
    
    public void recompose(final StylesheetRoot root) throws TransformerException {
    }
    
    public Node removeChild(final ElemTemplateElement childETE) throws DOMException {
        if (childETE == null || childETE.m_parentNode != this) {
            return null;
        }
        if (childETE == this.m_firstChild) {
            this.m_firstChild = childETE.m_nextSibling;
        }
        else {
            final ElemTemplateElement prev = (ElemTemplateElement)childETE.getPreviousSibling();
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
        if (prev != null) {
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
    
    public void resolvePrefixTables() throws TransformerException {
        this.m_prefixTable = null;
        if (this.m_declaredPrefixes != null) {
            final StylesheetRoot stylesheet = this.getStylesheetRoot();
            for (int n = this.m_declaredPrefixes.size(), i = 0; i < n; ++i) {
                XMLNSDecl decl = this.m_declaredPrefixes.elementAt(i);
                final String prefix = decl.getPrefix();
                String uri = decl.getURI();
                if (uri == null) {
                    uri = "";
                }
                final boolean shouldExclude = this.excludeResultNSDecl(prefix, uri);
                if (this.m_prefixTable == null) {
                    this.m_prefixTable = new Vector();
                }
                final NamespaceAlias nsAlias = stylesheet.getNamespaceAliasComposed(uri);
                if (nsAlias != null) {
                    decl = new XMLNSDecl(nsAlias.getStylesheetPrefix(), nsAlias.getResultNamespace(), shouldExclude);
                }
                else {
                    decl = new XMLNSDecl(prefix, uri, shouldExclude);
                }
                this.m_prefixTable.addElement(decl);
            }
        }
        final ElemTemplateElement parent = (ElemTemplateElement)this.getParentNode();
        if (parent != null) {
            final Vector prefixes = parent.m_prefixTable;
            if (this.m_prefixTable == null && !this.needToCheckExclude()) {
                this.m_prefixTable = parent.m_prefixTable;
            }
            else {
                for (int n2 = prefixes.size(), j = 0; j < n2; ++j) {
                    XMLNSDecl decl2 = prefixes.elementAt(j);
                    final boolean shouldExclude2 = this.excludeResultNSDecl(decl2.getPrefix(), decl2.getURI());
                    if (shouldExclude2 != decl2.getIsExcluded()) {
                        decl2 = new XMLNSDecl(decl2.getPrefix(), decl2.getURI(), shouldExclude2);
                    }
                    this.m_prefixTable.addElement(decl2);
                }
            }
        }
        else if (this.m_prefixTable == null) {
            this.m_prefixTable = new Vector();
        }
        for (ElemTemplateElement child = this.m_firstChild; child != null; child = child.m_nextSibling) {
            child.resolvePrefixTables();
        }
    }
    
    public void runtimeInit(final TransformerImpl transformer) throws TransformerException {
    }
    
    public void setDOMBackPointer(final Node n) {
        this.m_DOMBackPointer = n;
    }
    
    public void setLocaterInfo(final SourceLocator locator) {
        this.m_lineNumber = locator.getLineNumber();
        this.m_columnNumber = locator.getColumnNumber();
    }
    
    public void setPrefixes(final NamespaceSupport nsSupport) throws TransformerException {
        this.setPrefixes(nsSupport, false);
    }
    
    public void setPrefixes(final NamespaceSupport nsSupport, final boolean excludeXSLDecl) throws TransformerException {
        final Enumeration decls = nsSupport.getDeclaredPrefixes();
        while (decls.hasMoreElements()) {
            final String prefix = decls.nextElement();
            if (this.m_declaredPrefixes == null) {
                this.m_declaredPrefixes = new Vector();
            }
            final String uri = nsSupport.getURI(prefix);
            if (!excludeXSLDecl || !uri.equals("http://www.w3.org/1999/XSL/Transform")) {
                final XMLNSDecl decl = new XMLNSDecl(prefix, uri, false);
                this.m_declaredPrefixes.addElement(decl);
            }
        }
    }
    
    public void setUid(final int i) {
        this.m_docOrderNumber = i;
    }
    
    public void setXmlSpace(final int v) {
        this.m_defaultSpace = (v == 2);
    }
    
    public boolean shouldStripWhiteSpace(final XPathContext support, final Element targetElement) throws TransformerException {
        final StylesheetRoot sroot = this.getStylesheetRoot();
        return sroot != null && sroot.shouldStripWhiteSpace(support, targetElement);
    }
    
    void unexecuteNSDecls(final TransformerImpl transformer) throws TransformerException {
        try {
            if (this.m_prefixTable != null) {
                final ResultTreeHandler rhandler = transformer.getResultTreeHandler();
                for (int n = this.m_prefixTable.size(), i = 0; i < n; ++i) {
                    final XMLNSDecl decl = this.m_prefixTable.elementAt(i);
                    if (!decl.getIsExcluded()) {
                        rhandler.endPrefixMapping(decl.getPrefix());
                    }
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
    }
}
