// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.apache.xalan.res.XSLMessages;
import java.util.Enumeration;
import org.apache.xpath.XPathContext;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.utils.PrefixResolver;
import org.xml.sax.SAXException;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.NamedNodeMap;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.StringVector;
import java.util.Vector;

public class ElemLiteralResult extends ElemUse
{
    static final long serialVersionUID = -8703409074421657260L;
    private static final String EMPTYSTRING = "";
    private boolean isLiteralResultAsStylesheet;
    private Vector m_avts;
    private Vector m_xslAttr;
    private String m_namespace;
    private String m_localName;
    private String m_rawName;
    private StringVector m_ExtensionElementURIs;
    private String m_version;
    private StringVector m_excludeResultPrefixes;
    
    public ElemLiteralResult() {
        this.isLiteralResultAsStylesheet = false;
        this.m_avts = null;
        this.m_xslAttr = null;
    }
    
    public void setIsLiteralResultAsStylesheet(final boolean b) {
        this.isLiteralResultAsStylesheet = b;
    }
    
    public boolean getIsLiteralResultAsStylesheet() {
        return this.isLiteralResultAsStylesheet;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        final Vector vnames = cstate.getVariableNames();
        if (null != this.m_avts) {
            final int nAttrs = this.m_avts.size();
            for (int i = nAttrs - 1; i >= 0; --i) {
                final AVT avt = this.m_avts.elementAt(i);
                avt.fixupVariables(vnames, cstate.getGlobalsSize());
            }
        }
    }
    
    public void addLiteralResultAttribute(final AVT avt) {
        if (null == this.m_avts) {
            this.m_avts = new Vector();
        }
        this.m_avts.addElement(avt);
    }
    
    public void addLiteralResultAttribute(final String att) {
        if (null == this.m_xslAttr) {
            this.m_xslAttr = new Vector();
        }
        this.m_xslAttr.addElement(att);
    }
    
    public void setXmlSpace(final AVT avt) {
        this.addLiteralResultAttribute(avt);
        final String val = avt.getSimpleString();
        if (val.equals("default")) {
            super.setXmlSpace(2);
        }
        else if (val.equals("preserve")) {
            super.setXmlSpace(1);
        }
    }
    
    public AVT getLiteralResultAttributeNS(final String namespaceURI, final String localName) {
        if (null != this.m_avts) {
            final int nAttrs = this.m_avts.size();
            for (int i = nAttrs - 1; i >= 0; --i) {
                final AVT avt = this.m_avts.elementAt(i);
                if (avt.getName().equals(localName) && avt.getURI().equals(namespaceURI)) {
                    return avt;
                }
            }
        }
        return null;
    }
    
    public String getAttributeNS(final String namespaceURI, final String localName) {
        final AVT avt = this.getLiteralResultAttributeNS(namespaceURI, localName);
        if (null != avt) {
            return avt.getSimpleString();
        }
        return "";
    }
    
    public AVT getLiteralResultAttribute(final String name) {
        if (null != this.m_avts) {
            final int nAttrs = this.m_avts.size();
            String namespace = null;
            for (int i = nAttrs - 1; i >= 0; --i) {
                final AVT avt = this.m_avts.elementAt(i);
                namespace = avt.getURI();
                if ((namespace != null && !namespace.equals("") && (namespace + ":" + avt.getName()).equals(name)) || ((namespace == null || namespace.equals("")) && avt.getRawName().equals(name))) {
                    return avt;
                }
            }
        }
        return null;
    }
    
    public String getAttribute(final String rawName) {
        final AVT avt = this.getLiteralResultAttribute(rawName);
        if (null != avt) {
            return avt.getSimpleString();
        }
        return "";
    }
    
    public boolean containsExcludeResultPrefix(String prefix, final String uri) {
        if (uri == null || (null == this.m_excludeResultPrefixes && null == this.m_ExtensionElementURIs)) {
            return super.containsExcludeResultPrefix(prefix, uri);
        }
        if (prefix.length() == 0) {
            prefix = "#default";
        }
        if (this.m_excludeResultPrefixes != null) {
            for (int i = 0; i < this.m_excludeResultPrefixes.size(); ++i) {
                if (uri.equals(this.getNamespaceForPrefix(this.m_excludeResultPrefixes.elementAt(i)))) {
                    return true;
                }
            }
        }
        return (this.m_ExtensionElementURIs != null && this.m_ExtensionElementURIs.contains(uri)) || super.containsExcludeResultPrefix(prefix, uri);
    }
    
    public void resolvePrefixTables() throws TransformerException {
        super.resolvePrefixTables();
        final StylesheetRoot stylesheet = this.getStylesheetRoot();
        if (null != this.m_namespace && this.m_namespace.length() > 0) {
            final NamespaceAlias nsa = stylesheet.getNamespaceAliasComposed(this.m_namespace);
            if (null != nsa) {
                this.m_namespace = nsa.getResultNamespace();
                final String resultPrefix = nsa.getStylesheetPrefix();
                if (null != resultPrefix && resultPrefix.length() > 0) {
                    this.m_rawName = resultPrefix + ":" + this.m_localName;
                }
                else {
                    this.m_rawName = this.m_localName;
                }
            }
        }
        if (null != this.m_avts) {
            for (int n = this.m_avts.size(), i = 0; i < n; ++i) {
                final AVT avt = this.m_avts.elementAt(i);
                final String ns = avt.getURI();
                if (null != ns && ns.length() > 0) {
                    final NamespaceAlias nsa2 = stylesheet.getNamespaceAliasComposed(this.m_namespace);
                    if (null != nsa2) {
                        final String namespace = nsa2.getResultNamespace();
                        final String resultPrefix2 = nsa2.getStylesheetPrefix();
                        String rawName = avt.getName();
                        if (null != resultPrefix2 && resultPrefix2.length() > 0) {
                            rawName = resultPrefix2 + ":" + rawName;
                        }
                        avt.setURI(namespace);
                        avt.setRawName(rawName);
                    }
                }
            }
        }
    }
    
    boolean needToCheckExclude() {
        if (null == this.m_excludeResultPrefixes && null == super.m_prefixTable && this.m_ExtensionElementURIs == null) {
            return false;
        }
        if (null == super.m_prefixTable) {
            super.m_prefixTable = new Vector();
        }
        return true;
    }
    
    public void setNamespace(String ns) {
        if (null == ns) {
            ns = "";
        }
        this.m_namespace = ns;
    }
    
    public String getNamespace() {
        return this.m_namespace;
    }
    
    public void setLocalName(final String localName) {
        this.m_localName = localName;
    }
    
    public String getLocalName() {
        return this.m_localName;
    }
    
    public void setRawName(final String rawName) {
        this.m_rawName = rawName;
    }
    
    public String getRawName() {
        return this.m_rawName;
    }
    
    public String getPrefix() {
        final int len = this.m_rawName.length() - this.m_localName.length() - 1;
        return (len > 0) ? this.m_rawName.substring(0, len) : "";
    }
    
    public void setExtensionElementPrefixes(final StringVector v) {
        this.m_ExtensionElementURIs = v;
    }
    
    public NamedNodeMap getAttributes() {
        return new LiteralElementAttributes();
    }
    
    public String getExtensionElementPrefix(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_ExtensionElementURIs) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_ExtensionElementURIs.elementAt(i);
    }
    
    public int getExtensionElementPrefixCount() {
        return (null != this.m_ExtensionElementURIs) ? this.m_ExtensionElementURIs.size() : 0;
    }
    
    public boolean containsExtensionElementURI(final String uri) {
        return null != this.m_ExtensionElementURIs && this.m_ExtensionElementURIs.contains(uri);
    }
    
    public int getXSLToken() {
        return 77;
    }
    
    public String getNodeName() {
        return this.m_rawName;
    }
    
    public void setVersion(final String v) {
        this.m_version = v;
    }
    
    public String getVersion() {
        return this.m_version;
    }
    
    public void setExcludeResultPrefixes(final StringVector v) {
        this.m_excludeResultPrefixes = v;
    }
    
    private boolean excludeResultNSDecl(final String prefix, final String uri) throws TransformerException {
        return null != this.m_excludeResultPrefixes && this.containsExcludeResultPrefix(prefix, uri);
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        final SerializationHandler rhandler = transformer.getSerializationHandler();
        try {
            if (transformer.getDebug()) {
                rhandler.flushPending();
                transformer.getTraceManager().fireTraceEvent(this);
            }
            rhandler.startPrefixMapping(this.getPrefix(), this.getNamespace());
            this.executeNSDecls(transformer);
            rhandler.startElement(this.getNamespace(), this.getLocalName(), this.getRawName());
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        TransformerException tException = null;
        try {
            super.execute(transformer);
            if (null != this.m_avts) {
                final int nAttrs = this.m_avts.size();
                for (int i = nAttrs - 1; i >= 0; --i) {
                    final AVT avt = this.m_avts.elementAt(i);
                    final XPathContext xctxt = transformer.getXPathContext();
                    final int sourceNode = xctxt.getCurrentNode();
                    final String stringedValue = avt.evaluate(xctxt, sourceNode, this);
                    if (null != stringedValue) {
                        rhandler.addAttribute(avt.getURI(), avt.getName(), avt.getRawName(), "CDATA", stringedValue, false);
                    }
                }
            }
            transformer.executeChildTemplates(this, true);
        }
        catch (TransformerException te) {
            tException = te;
        }
        catch (SAXException se2) {
            tException = new TransformerException(se2);
        }
        try {
            if (transformer.getDebug()) {
                transformer.getTraceManager().fireTraceEndEvent(this);
            }
            rhandler.endElement(this.getNamespace(), this.getLocalName(), this.getRawName());
        }
        catch (SAXException se3) {
            if (tException != null) {
                throw tException;
            }
            throw new TransformerException(se3);
        }
        if (tException != null) {
            throw tException;
        }
        this.unexecuteNSDecls(transformer);
        try {
            rhandler.endPrefixMapping(this.getPrefix());
        }
        catch (SAXException se3) {
            throw new TransformerException(se3);
        }
    }
    
    public Enumeration enumerateLiteralResultAttributes() {
        return (null == this.m_avts) ? null : this.m_avts.elements();
    }
    
    protected boolean accept(final XSLTVisitor visitor) {
        return visitor.visitLiteralResultElement(this);
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (callAttrs && null != this.m_avts) {
            final int nAttrs = this.m_avts.size();
            for (int i = nAttrs - 1; i >= 0; --i) {
                final AVT avt = this.m_avts.elementAt(i);
                avt.callVisitors(visitor);
            }
        }
        super.callChildVisitors(visitor, callAttrs);
    }
    
    public void throwDOMException(final short code, final String msg) {
        final String themsg = XSLMessages.createMessage(msg, null);
        throw new DOMException(code, themsg);
    }
    
    public class LiteralElementAttributes implements NamedNodeMap
    {
        private int m_count;
        
        public LiteralElementAttributes() {
            this.m_count = -1;
        }
        
        public int getLength() {
            if (this.m_count == -1) {
                if (null != ElemLiteralResult.this.m_avts) {
                    this.m_count = ElemLiteralResult.this.m_avts.size();
                }
                else {
                    this.m_count = 0;
                }
            }
            return this.m_count;
        }
        
        public Node getNamedItem(final String name) {
            if (this.getLength() == 0) {
                return null;
            }
            String uri = null;
            String localName = name;
            final int index = name.indexOf(":");
            if (-1 != index) {
                uri = name.substring(0, index);
                localName = name.substring(index + 1);
            }
            Node retNode = null;
            final Enumeration eum = ElemLiteralResult.this.m_avts.elements();
            while (eum.hasMoreElements()) {
                final AVT avt = eum.nextElement();
                if (localName.equals(avt.getName())) {
                    final String nsURI = avt.getURI();
                    if ((uri == null && nsURI == null) || (uri != null && uri.equals(nsURI))) {
                        retNode = new Attribute(avt, ElemLiteralResult.this);
                        break;
                    }
                    continue;
                }
            }
            return retNode;
        }
        
        public Node getNamedItemNS(final String namespaceURI, final String localName) {
            if (this.getLength() == 0) {
                return null;
            }
            Node retNode = null;
            final Enumeration eum = ElemLiteralResult.this.m_avts.elements();
            while (eum.hasMoreElements()) {
                final AVT avt = eum.nextElement();
                if (localName.equals(avt.getName())) {
                    final String nsURI = avt.getURI();
                    if ((namespaceURI == null && nsURI == null) || (namespaceURI != null && namespaceURI.equals(nsURI))) {
                        retNode = new Attribute(avt, ElemLiteralResult.this);
                        break;
                    }
                    continue;
                }
            }
            return retNode;
        }
        
        public Node item(final int i) {
            if (this.getLength() == 0 || i >= ElemLiteralResult.this.m_avts.size()) {
                return null;
            }
            return new Attribute(ElemLiteralResult.this.m_avts.elementAt(i), ElemLiteralResult.this);
        }
        
        public Node removeNamedItem(final String name) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
            return null;
        }
        
        public Node removeNamedItemNS(final String namespaceURI, final String localName) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
            return null;
        }
        
        public Node setNamedItem(final Node arg) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
            return null;
        }
        
        public Node setNamedItemNS(final Node arg) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
            return null;
        }
    }
    
    public class Attribute implements Attr
    {
        private AVT m_attribute;
        private Element m_owner;
        
        public Attribute(final AVT avt, final Element elem) {
            this.m_owner = null;
            this.m_attribute = avt;
            this.m_owner = elem;
        }
        
        public Node appendChild(final Node newChild) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
            return null;
        }
        
        public Node cloneNode(final boolean deep) {
            return new Attribute(this.m_attribute, this.m_owner);
        }
        
        public NamedNodeMap getAttributes() {
            return null;
        }
        
        public NodeList getChildNodes() {
            return new NodeList() {
                public int getLength() {
                    return 0;
                }
                
                public Node item(final int index) {
                    return null;
                }
            };
        }
        
        public Node getFirstChild() {
            return null;
        }
        
        public Node getLastChild() {
            return null;
        }
        
        public String getLocalName() {
            return this.m_attribute.getName();
        }
        
        public String getNamespaceURI() {
            final String uri = this.m_attribute.getURI();
            return uri.equals("") ? null : uri;
        }
        
        public Node getNextSibling() {
            return null;
        }
        
        public String getNodeName() {
            final String uri = this.m_attribute.getURI();
            final String localName = this.getLocalName();
            return uri.equals("") ? localName : (uri + ":" + localName);
        }
        
        public short getNodeType() {
            return 2;
        }
        
        public String getNodeValue() throws DOMException {
            return this.m_attribute.getSimpleString();
        }
        
        public Document getOwnerDocument() {
            return this.m_owner.getOwnerDocument();
        }
        
        public Node getParentNode() {
            return this.m_owner;
        }
        
        public String getPrefix() {
            final String uri = this.m_attribute.getURI();
            final String rawName = this.m_attribute.getRawName();
            return uri.equals("") ? null : rawName.substring(0, rawName.indexOf(":"));
        }
        
        public Node getPreviousSibling() {
            return null;
        }
        
        public boolean hasAttributes() {
            return false;
        }
        
        public boolean hasChildNodes() {
            return false;
        }
        
        public Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
            return null;
        }
        
        public boolean isSupported(final String feature, final String version) {
            return false;
        }
        
        public void normalize() {
        }
        
        public Node removeChild(final Node oldChild) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
            return null;
        }
        
        public Node replaceChild(final Node newChild, final Node oldChild) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
            return null;
        }
        
        public void setNodeValue(final String nodeValue) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        
        public void setPrefix(final String prefix) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        
        public String getBaseURI() {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return null;
        }
        
        public short compareDocumentPosition(final Node other) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)9, "NOT_SUPPORTED_ERR");
            return 0;
        }
        
        public String getTextContent() throws DOMException {
            return "";
        }
        
        public void setTextContent(final String textContent) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        
        public boolean isSameNode(final Node other) {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return false;
        }
        
        public String lookupPrefix(final String namespaceURI) {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return null;
        }
        
        public boolean isDefaultNamespace(final String namespaceURI) {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return false;
        }
        
        public String lookupNamespaceURI(final String prefix) {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return null;
        }
        
        public boolean isEqualNode(final Node arg) {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return false;
        }
        
        public Object getFeature(final String feature, final String version) {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return null;
        }
        
        public Object setUserData(final String key, final Object data, final UserDataHandler handler) {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return null;
        }
        
        public Object getUserData(final String key) {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return null;
        }
        
        public String getName() {
            return this.m_attribute.getName();
        }
        
        public String getValue() {
            return this.m_attribute.getSimpleString();
        }
        
        public Element getOwnerElement() {
            return this.m_owner;
        }
        
        public boolean getSpecified() {
            return true;
        }
        
        public void setValue(final String value) throws DOMException {
            ElemLiteralResult.this.throwDOMException((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        
        public TypeInfo getSchemaTypeInfo() {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return null;
        }
        
        public boolean isId() {
            ElemLiteralResult.this.error("ER_FUNCTION_NOT_SUPPORTED");
            return false;
        }
    }
}
