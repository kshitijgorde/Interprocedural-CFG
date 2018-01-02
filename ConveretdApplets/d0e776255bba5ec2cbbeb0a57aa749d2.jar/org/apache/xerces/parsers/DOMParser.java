// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.w3c.dom.Attr;
import org.apache.xerces.dom.NotationImpl;
import org.apache.xerces.dom.ElementDefinitionImpl;
import java.util.StringTokenizer;
import org.apache.xerces.framework.XMLContentSpec;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xerces.dom.EntityReferenceImpl;
import org.w3c.dom.EntityReference;
import org.apache.xerces.dom.TextImpl;
import org.w3c.dom.Text;
import org.apache.xerces.dom.AttrImpl;
import org.apache.xerces.validators.schema.XUtil;
import org.apache.xerces.utils.QName;
import org.w3c.dom.NamedNodeMap;
import org.apache.xerces.dom.EntityImpl;
import org.w3c.dom.Element;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xerces.utils.StringPool;
import org.xml.sax.SAXException;
import org.apache.xerces.framework.XMLAttrList;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentType;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DeferredDocumentImpl;
import org.w3c.dom.Document;
import org.apache.xerces.framework.XMLDocumentHandler;
import org.apache.xerces.framework.XMLParser;

public class DOMParser extends XMLParser implements XMLDocumentHandler
{
    public static final String DEFAULT_DOCUMENT_CLASS_NAME = "org.apache.xerces.dom.DocumentImpl";
    public static final String DEFAULT_DEFERRED_DOCUMENT_CLASS_NAME = "org.apache.xerces.dom.DeferredDocumentImpl";
    private static final boolean DEBUG_ATTLIST_DECL = false;
    private static final String[] RECOGNIZED_FEATURES;
    private static final String[] RECOGNIZED_PROPERTIES;
    protected Document fDocument;
    protected DeferredDocumentImpl fDeferredDocumentImpl;
    protected int fDocumentIndex;
    protected int fDocumentTypeIndex;
    protected int fCurrentNodeIndex;
    protected int fCurrentEntityName;
    protected int fCurrentEntityNode;
    protected DocumentImpl fDocumentImpl;
    protected DocumentType fDocumentType;
    protected Node fCurrentElementNode;
    protected boolean fInDTD;
    protected boolean fWithinElement;
    protected boolean fInCDATA;
    private boolean fGrammarAccess;
    private String fDocumentClassName;
    private boolean fDeferNodeExpansion;
    private boolean fCreateEntityReferenceNodes;
    private boolean fIncludeIgnorableWhitespace;
    protected int fAmpIndex;
    protected int fLtIndex;
    protected int fGtIndex;
    protected int fAposIndex;
    protected int fQuotIndex;
    private boolean fSeenRootElement;
    private boolean fStringPoolInUse;
    private XMLAttrList fAttrList;
    static /* synthetic */ Class class$org$w3c$dom$Document;
    
    public DOMParser() {
        this.initHandlers(false, this, this);
        this.init();
        try {
            this.setDocumentClassName("org.apache.xerces.dom.DocumentImpl");
            this.setCreateEntityReferenceNodes(true);
            this.setDeferNodeExpansion(true);
            this.setIncludeIgnorableWhitespace(true);
        }
        catch (SAXException ex) {
            throw new RuntimeException("PAR001 Fatal error constructing DOMParser.");
        }
    }
    
    public Document getDocument() {
        return this.fDocument;
    }
    
    public String[] getFeaturesRecognized() {
        final String[] featuresRecognized = super.getFeaturesRecognized();
        final String[] recognized_FEATURES = DOMParser.RECOGNIZED_FEATURES;
        final int length = recognized_FEATURES.length;
        if (length == 0) {
            return featuresRecognized;
        }
        final int length2 = featuresRecognized.length;
        if (length2 == 0) {
            return recognized_FEATURES;
        }
        final String[] array = new String[length2 + length];
        System.arraycopy(featuresRecognized, 0, array, 0, length2);
        System.arraycopy(recognized_FEATURES, 0, array, length2, length);
        return array;
    }
    
    public String[] getPropertiesRecognized() {
        final String[] propertiesRecognized = super.getPropertiesRecognized();
        final String[] recognized_PROPERTIES = DOMParser.RECOGNIZED_PROPERTIES;
        final int length = recognized_PROPERTIES.length;
        if (length == 0) {
            return propertiesRecognized;
        }
        final int length2 = propertiesRecognized.length;
        if (length2 == 0) {
            return recognized_PROPERTIES;
        }
        final String[] array = new String[length2 + length];
        System.arraycopy(propertiesRecognized, 0, array, 0, length2);
        System.arraycopy(recognized_PROPERTIES, 0, array, length2, length);
        return array;
    }
    
    public void reset() throws Exception {
        if (this.fStringPoolInUse) {
            super.fStringPool = new StringPool();
            this.fStringPoolInUse = false;
        }
        super.reset();
        this.init();
    }
    
    public void resetOrCopy() throws Exception {
        super.resetOrCopy();
        this.init();
    }
    
    protected void init() {
        this.fDocument = null;
        this.fDeferredDocumentImpl = null;
        this.fDocumentIndex = -1;
        this.fDocumentTypeIndex = -1;
        this.fCurrentNodeIndex = -1;
        this.fCurrentEntityNode = -1;
        this.fCurrentEntityName = -1;
        this.fDocumentImpl = null;
        this.fDocumentType = null;
        this.fCurrentElementNode = null;
        this.fInDTD = false;
        this.fWithinElement = false;
        this.fInCDATA = false;
        this.fAmpIndex = super.fStringPool.addSymbol("amp");
        this.fLtIndex = super.fStringPool.addSymbol("lt");
        this.fGtIndex = super.fStringPool.addSymbol("gt");
        this.fAposIndex = super.fStringPool.addSymbol("apos");
        this.fQuotIndex = super.fStringPool.addSymbol("quot");
        this.fSeenRootElement = false;
        this.fStringPoolInUse = false;
        this.fAttrList = new XMLAttrList(super.fStringPool);
    }
    
    protected void setDeferNodeExpansion(final boolean fDeferNodeExpansion) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.fDeferNodeExpansion = fDeferNodeExpansion;
    }
    
    protected boolean getDeferNodeExpansion() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fDeferNodeExpansion;
    }
    
    protected void setCreateEntityReferenceNodes(final boolean fCreateEntityReferenceNodes) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.fCreateEntityReferenceNodes = fCreateEntityReferenceNodes;
    }
    
    public boolean getCreateEntityReferenceNodes() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fCreateEntityReferenceNodes;
    }
    
    public void setIncludeIgnorableWhitespace(final boolean fIncludeIgnorableWhitespace) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.fIncludeIgnorableWhitespace = fIncludeIgnorableWhitespace;
    }
    
    public boolean getIncludeIgnorableWhitespace() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fIncludeIgnorableWhitespace;
    }
    
    protected void setDocumentClassName(String fDocumentClassName) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (fDocumentClassName == null) {
            fDocumentClassName = "org.apache.xerces.dom.DocumentImpl";
        }
        try {
            if (!((DOMParser.class$org$w3c$dom$Document == null) ? (DOMParser.class$org$w3c$dom$Document = class$("org.w3c.dom.Document")) : DOMParser.class$org$w3c$dom$Document).isAssignableFrom(Class.forName(fDocumentClassName))) {
                throw new IllegalArgumentException("PAR002 Class, \"" + fDocumentClassName + "\", is not of type org.w3c.dom.Document." + "\n" + fDocumentClassName);
            }
        }
        catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException("PAR003 Class, \"" + fDocumentClassName + "\", not found." + "\n" + fDocumentClassName);
        }
        this.fDocumentClassName = fDocumentClassName;
        if (!fDocumentClassName.equals("org.apache.xerces.dom.DocumentImpl")) {
            this.setDeferNodeExpansion(false);
        }
    }
    
    protected String getDocumentClassName() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fDocumentClassName;
    }
    
    protected Element getCurrentElementNode() throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fCurrentElementNode != null && this.fCurrentElementNode.getNodeType() == 1) {
            return (Element)this.fCurrentElementNode;
        }
        return null;
    }
    
    public void setFeature(final String s, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (!s.startsWith("http://xml.org/sax/features/")) {
            if (s.startsWith("http://apache.org/xml/features/")) {
                final String substring = s.substring("http://apache.org/xml/features/".length());
                if (substring.equals("dom/defer-node-expansion")) {
                    if (super.fParseInProgress) {
                        throw new SAXNotSupportedException("PAR004 Cannot setFeature(" + s + "): parse is in progress." + "\n" + s);
                    }
                    this.setDeferNodeExpansion(b);
                    return;
                }
                else {
                    if (substring.equals("dom/create-entity-ref-nodes")) {
                        this.setCreateEntityReferenceNodes(b);
                        return;
                    }
                    if (substring.equals("dom/include-ignorable-whitespace")) {
                        this.setIncludeIgnorableWhitespace(b);
                        return;
                    }
                    if (substring.equals("domx/grammar-access")) {
                        this.fGrammarAccess = b;
                        return;
                    }
                }
            }
        }
        super.setFeature(s, b);
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (!s.startsWith("http://xml.org/sax/features/")) {
            if (s.startsWith("http://apache.org/xml/features/")) {
                final String substring = s.substring("http://apache.org/xml/features/".length());
                if (substring.equals("dom/defer-node-expansion")) {
                    return this.getDeferNodeExpansion();
                }
                if (substring.equals("dom/create-entity-ref-nodes")) {
                    return this.getCreateEntityReferenceNodes();
                }
                if (substring.equals("dom/include-ignorable-whitespace")) {
                    return this.getIncludeIgnorableWhitespace();
                }
                if (substring.equals("domx/grammar-access")) {
                    return this.fGrammarAccess;
                }
            }
        }
        return super.getFeature(s);
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://apache.org/xml/properties/")) {
            final String substring = s.substring("http://apache.org/xml/properties/".length());
            if (substring.equals("dom/current-element-node")) {
                throw new SAXNotSupportedException("PAR005 Property, \"" + s + "\" is read-only.\n" + s);
            }
            if (substring.equals("dom/document-class-name")) {
                if (o != null && !(o instanceof String)) {
                    throw new SAXNotSupportedException("PAR006 Property value must be of type java.lang.String.");
                }
                this.setDocumentClassName((String)o);
                return;
            }
        }
        super.setProperty(s, o);
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://apache.org/xml/properties/")) {
            final String substring = s.substring("http://apache.org/xml/properties/".length());
            if (substring.equals("dom/current-element-node")) {
                boolean feature = false;
                try {
                    feature = this.getFeature("http://apache.org/xml/features/dom/defer-node-expansion");
                }
                catch (SAXNotSupportedException ex) {}
                catch (SAXNotRecognizedException ex2) {}
                if (feature) {
                    throw new SAXNotSupportedException("PAR007 Current element node cannot be queried when node expansion is deferred.");
                }
                return this.getCurrentElementNode();
            }
            else if (substring.equals("dom/document-class-name")) {
                return this.getDocumentClassName();
            }
        }
        return super.getProperty(s);
    }
    
    public void startDocument() {
        String documentClassName;
        try {
            documentClassName = this.getDocumentClassName();
        }
        catch (SAXException ex) {
            throw new RuntimeException("PAR008 Fatal error getting document factory.");
        }
        boolean deferNodeExpansion;
        try {
            deferNodeExpansion = this.getDeferNodeExpansion();
        }
        catch (SAXException ex2) {
            throw new RuntimeException("PAR009 Fatal error reading expansion mode.");
        }
        try {
            final boolean equals = this.fDocumentClassName.equals("org.apache.xerces.dom.DocumentImpl");
            final boolean equals2 = this.fDocumentClassName.equals("org.apache.xerces.dom.DeferredDocumentImpl");
            if (deferNodeExpansion && (equals || equals2)) {
                boolean namespaces = false;
                try {
                    namespaces = this.getNamespaces();
                }
                catch (SAXException ex3) {}
                this.fDeferredDocumentImpl = new DeferredDocumentImpl(super.fStringPool, namespaces, this.fGrammarAccess);
                this.fStringPoolInUse = true;
                this.fDocument = this.fDeferredDocumentImpl;
                this.fDocumentIndex = this.fDeferredDocumentImpl.createDocument();
                this.fCurrentNodeIndex = this.fDocumentIndex;
            }
            else {
                Class.forName(documentClassName);
                Class.forName("org.apache.xerces.dom.DocumentImpl");
                if (equals) {
                    this.fDocumentImpl = new DocumentImpl(this.fGrammarAccess);
                    this.fDocument = this.fDocumentImpl;
                    this.fDocumentImpl.setErrorChecking(false);
                }
                else {
                    try {
                        this.fDocument = (Document)Class.forName(documentClassName).newInstance();
                    }
                    catch (Exception ex4) {
                        throw new RuntimeException("Failed to create document object of class: " + documentClassName);
                    }
                }
                this.fCurrentElementNode = this.fDocument;
            }
        }
        catch (ClassNotFoundException ex5) {
            throw new RuntimeException(documentClassName);
        }
    }
    
    public void endDocument() throws Exception {
        if (this.fDocumentImpl != null) {
            this.fDocumentImpl.setErrorChecking(true);
        }
    }
    
    public void xmlDecl(final int n, final int n2, final int n3) throws Exception {
        final boolean b = n3 != -1 && super.fStringPool.toString(n3).equals("yes");
        if (this.fDocumentImpl != null) {
            this.fDocumentImpl.setVersion(super.fStringPool.toString(n));
            this.fDocumentImpl.setEncoding(super.fStringPool.toString(n2));
            this.fDocumentImpl.setStandalone(b);
        }
        else if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.setVersion(super.fStringPool.toString(n));
            this.fDeferredDocumentImpl.setEncoding(super.fStringPool.toString(n2));
            this.fDeferredDocumentImpl.setStandalone(b);
        }
    }
    
    public void textDecl(final int n, final int n2) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            final String string = super.fStringPool.toString(this.fCurrentEntityName);
            if (this.fDocumentTypeIndex != -1 && string != null) {
                int fCurrentEntityNode;
                for (fCurrentEntityNode = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, false); fCurrentEntityNode != -1 && (this.fDeferredDocumentImpl.getNodeType(fCurrentEntityNode, false) != 6 || !this.fDeferredDocumentImpl.getNodeNameString(fCurrentEntityNode, false).equals(string)); fCurrentEntityNode = this.fDeferredDocumentImpl.getPrevSibling(fCurrentEntityNode, false)) {}
                this.fCurrentEntityNode = fCurrentEntityNode;
                this.fDeferredDocumentImpl.setEntityInfo(fCurrentEntityNode, n, n2);
            }
        }
        else if (this.fDocumentImpl != null) {
            final NamedNodeMap entities = this.fDocumentType.getEntities();
            if (entities != null) {
                final EntityImpl entityImpl = (EntityImpl)entities.getNamedItem(this.fCurrentElementNode.getNodeName());
                if (entityImpl != null) {
                    entityImpl.setVersion(super.fStringPool.toString(n));
                    entityImpl.setEncoding(super.fStringPool.toString(n2));
                }
            }
        }
    }
    
    public void startNamespaceDeclScope(final int n, final int n2) throws Exception {
    }
    
    public void endNamespaceDeclScope(final int n) throws Exception {
    }
    
    public void startElement(final QName qName, final XMLAttrList list, final int n) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            if (!this.fSeenRootElement) {
                this.fSeenRootElement = true;
                if (this.fGrammarAccess && super.fGrammarResolver.size() > 0) {
                    if (this.fDocumentTypeIndex == -1) {
                        this.fDocumentTypeIndex = this.fDeferredDocumentImpl.createDocumentType(qName.rawname, -1, -1);
                        this.fDeferredDocumentImpl.appendChild(0, this.fDocumentTypeIndex);
                    }
                    final Document grammarDocument = super.fGrammarResolver.getGrammar(super.fGrammarResolver.nameSpaceKeys().nextElement()).getGrammarDocument();
                    if (grammarDocument != null) {
                        this.copyInto(grammarDocument.getDocumentElement(), this.fDocumentTypeIndex);
                    }
                }
            }
            final int element = this.fDeferredDocumentImpl.createElement(qName.rawname, qName.uri, list, n);
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, element);
            this.fCurrentNodeIndex = element;
            this.fWithinElement = true;
            for (int i = list.getFirstAttr(n); i != -1; i = list.getNextAttr(i)) {
                if (list.getAttType(i) == super.fStringPool.addSymbol("ID")) {
                    this.fDeferredDocumentImpl.putIdentifier(list.getAttValue(i), element);
                }
            }
        }
        else {
            boolean namespaces = false;
            try {
                namespaces = this.getNamespaces();
            }
            catch (SAXException ex) {}
            final String string = super.fStringPool.toString(qName.rawname);
            if (!this.fSeenRootElement) {
                this.fSeenRootElement = true;
                if (this.fDocumentImpl != null && this.fGrammarAccess && super.fGrammarResolver.size() > 0) {
                    if (this.fDocumentType == null) {
                        this.fDocumentType = this.fDocumentImpl.createDocumentType(string, "", "");
                        this.fDocument.appendChild(this.fDocumentType);
                    }
                    final Document grammarDocument2 = super.fGrammarResolver.getGrammar(super.fGrammarResolver.nameSpaceKeys().nextElement()).getGrammarDocument();
                    if (grammarDocument2 != null) {
                        XUtil.copyInto(grammarDocument2.getDocumentElement(), this.fDocumentType);
                    }
                }
            }
            Element fCurrentElementNode;
            if (namespaces) {
                fCurrentElementNode = this.fDocument.createElementNS(super.fStringPool.toString(qName.uri), string);
            }
            else {
                fCurrentElementNode = this.fDocument.createElement(string);
            }
            for (int j = list.getFirstAttr(n); j != -1; j = list.getNextAttr(j)) {
                final String string2 = super.fStringPool.toString(list.getAttrName(j));
                final String string3 = super.fStringPool.toString(list.getAttValue(j));
                if (namespaces) {
                    String string4 = super.fStringPool.toString(list.getAttrURI(j));
                    final String string5 = super.fStringPool.toString(list.getAttrPrefix(j));
                    if (string4 == null) {
                        if (string5 != null) {
                            if (string5.equals("xmlns")) {
                                string4 = "http://www.w3.org/2000/xmlns/";
                            }
                        }
                        else if (string2.equals("xmlns")) {
                            string4 = "http://www.w3.org/2000/xmlns/";
                        }
                    }
                    fCurrentElementNode.setAttributeNS(string4, string2, string3);
                }
                else {
                    fCurrentElementNode.setAttribute(string2, string3);
                }
                if (!list.isSpecified(j)) {
                    ((AttrImpl)fCurrentElementNode.getAttributeNode(string2)).setSpecified(false);
                }
            }
            this.fCurrentElementNode.appendChild(fCurrentElementNode);
            this.fCurrentElementNode = fCurrentElementNode;
            this.fWithinElement = true;
            if (this.fDocumentImpl != null) {
                for (int k = list.getFirstAttr(n); k != -1; k = list.getNextAttr(k)) {
                    if (list.getAttType(k) == super.fStringPool.addSymbol("ID")) {
                        this.fDocumentImpl.putIdentifier(super.fStringPool.toString(list.getAttValue(k)), fCurrentElementNode);
                    }
                }
            }
            list.releaseAttrList(n);
        }
    }
    
    public void endElement(final QName qName) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex, false);
            this.fWithinElement = false;
        }
        else {
            this.fCurrentElementNode = this.fCurrentElementNode.getParentNode();
            this.fWithinElement = false;
        }
    }
    
    public void characters(final int n) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            int n2;
            if (this.fInCDATA) {
                n2 = this.fDeferredDocumentImpl.createCDATASection(n, false);
            }
            else {
                n2 = this.fDeferredDocumentImpl.createTextNode(n, false);
            }
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, n2);
        }
        else {
            Text text;
            if (this.fInCDATA) {
                text = this.fDocument.createCDATASection(super.fStringPool.orphanString(n));
            }
            else {
                if (this.fWithinElement && this.fCurrentElementNode.getNodeType() == 1) {
                    final Node lastChild = this.fCurrentElementNode.getLastChild();
                    if (lastChild != null && lastChild.getNodeType() == 3) {
                        ((Text)lastChild).appendData(super.fStringPool.orphanString(n));
                        return;
                    }
                }
                text = this.fDocument.createTextNode(super.fStringPool.orphanString(n));
            }
            this.fCurrentElementNode.appendChild(text);
        }
    }
    
    public void ignorableWhitespace(final int n) throws Exception {
        if (!this.fIncludeIgnorableWhitespace) {
            super.fStringPool.orphanString(n);
            return;
        }
        if (this.fDeferredDocumentImpl != null) {
            int n2;
            if (this.fInCDATA) {
                n2 = this.fDeferredDocumentImpl.createCDATASection(n, true);
            }
            else {
                n2 = this.fDeferredDocumentImpl.createTextNode(n, true);
            }
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, n2);
        }
        else {
            Text text;
            if (this.fInCDATA) {
                text = this.fDocument.createCDATASection(super.fStringPool.orphanString(n));
            }
            else {
                if (this.fWithinElement && this.fCurrentElementNode.getNodeType() == 1) {
                    final Node lastChild = this.fCurrentElementNode.getLastChild();
                    if (lastChild != null && lastChild.getNodeType() == 3) {
                        ((Text)lastChild).appendData(super.fStringPool.orphanString(n));
                        return;
                    }
                }
                text = this.fDocument.createTextNode(super.fStringPool.orphanString(n));
            }
            if (this.fDocumentImpl != null) {
                ((TextImpl)text).setIgnorableWhitespace(true);
            }
            this.fCurrentElementNode.appendChild(text);
        }
    }
    
    public void processingInstruction(final int n, final int n2) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDeferredDocumentImpl.createProcessingInstruction(n, n2));
        }
        else {
            this.fCurrentElementNode.appendChild(this.fDocument.createProcessingInstruction(super.fStringPool.orphanString(n), super.fStringPool.orphanString(n2)));
        }
    }
    
    public void comment(final int n) throws Exception {
        if (this.fInDTD && !this.fGrammarAccess) {
            super.fStringPool.orphanString(n);
        }
        else if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDeferredDocumentImpl.createComment(n));
        }
        else {
            this.fCurrentElementNode.appendChild(this.fDocument.createComment(super.fStringPool.orphanString(n)));
        }
    }
    
    public void characters(final char[] array, final int n, final int n2) throws Exception {
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws Exception {
    }
    
    public void startCDATA() throws Exception {
        this.fInCDATA = true;
    }
    
    public void endCDATA() throws Exception {
        this.fInCDATA = false;
    }
    
    public void startEntityReference(final int fCurrentEntityName, final int n, final int n2) throws Exception {
        this.fCurrentEntityName = fCurrentEntityName;
        if (!this.fCreateEntityReferenceNodes) {
            return;
        }
        if (fCurrentEntityName == this.fAmpIndex || fCurrentEntityName == this.fGtIndex || fCurrentEntityName == this.fLtIndex || fCurrentEntityName == this.fAposIndex || fCurrentEntityName == this.fQuotIndex) {
            return;
        }
        if (n2 != 2) {
            return;
        }
        if (this.fDeferredDocumentImpl != null) {
            final int entityReference = this.fDeferredDocumentImpl.createEntityReference(fCurrentEntityName);
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, entityReference);
            this.fCurrentNodeIndex = entityReference;
        }
        else {
            final EntityReference entityReference2 = this.fDocument.createEntityReference(super.fStringPool.toString(fCurrentEntityName));
            this.fCurrentElementNode.appendChild(entityReference2);
            this.fCurrentElementNode = entityReference2;
        }
    }
    
    public void endEntityReference(final int n, final int n2, final int n3) throws Exception {
        if (!this.fCreateEntityReferenceNodes) {
            return;
        }
        if (n == this.fAmpIndex || n == this.fGtIndex || n == this.fLtIndex || n == this.fAposIndex || n == this.fQuotIndex) {
            return;
        }
        if (n3 != 2) {
            return;
        }
        if (this.fDeferredDocumentImpl != null) {
            final String string = super.fStringPool.toString(n);
            final int fCurrentNodeIndex = this.fCurrentNodeIndex;
            this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(fCurrentNodeIndex, false);
            if (this.fDeferredDocumentImpl.getNodeType(fCurrentNodeIndex, false) != 5) {
                return;
            }
            final int lastChild = this.fDeferredDocumentImpl.getLastChild(fCurrentNodeIndex, false);
            if (this.fDocumentTypeIndex != -1) {
                if (this.fCurrentEntityNode == -1) {
                    int fCurrentEntityNode;
                    for (fCurrentEntityNode = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, false); fCurrentEntityNode != -1 && (this.fDeferredDocumentImpl.getNodeType(fCurrentEntityNode, false) != 6 || !this.fDeferredDocumentImpl.getNodeNameString(fCurrentEntityNode, false).equals(string)); fCurrentEntityNode = this.fDeferredDocumentImpl.getPrevSibling(fCurrentEntityNode, false)) {}
                    this.fCurrentEntityNode = fCurrentEntityNode;
                }
                if (this.fCurrentEntityNode != -1 && this.fDeferredDocumentImpl.getLastChild(this.fCurrentEntityNode, false) == -1) {
                    this.fDeferredDocumentImpl.setAsLastChild(this.fCurrentEntityNode, lastChild);
                }
                this.fCurrentEntityNode = -1;
                this.fCurrentEntityName = -1;
            }
        }
        else {
            final Node fCurrentElementNode = this.fCurrentElementNode;
            this.fCurrentElementNode = fCurrentElementNode.getParentNode();
            if (this.fDocumentImpl != null) {
                final EntityReferenceImpl entityReferenceImpl = (EntityReferenceImpl)fCurrentElementNode;
                final Node namedItem = this.fDocumentType.getEntities().getNamedItem(super.fStringPool.toString(n));
                if (namedItem == null || namedItem.hasChildNodes()) {
                    return;
                }
                final EntityImpl entityImpl = (EntityImpl)namedItem;
                for (Node node = fCurrentElementNode.getFirstChild(); node != null; node = node.getNextSibling()) {
                    entityImpl.appendChild(node.cloneNode(true));
                }
            }
        }
    }
    
    public void startDTD(final QName qName, final int n, final int n2) throws Exception {
        this.fInDTD = true;
        if (this.fDocumentImpl != null) {
            this.fDocumentType = this.fDocumentImpl.createDocumentType(super.fStringPool.toString(qName.rawname), super.fStringPool.toString(n), super.fStringPool.toString(n2));
            this.fDocumentImpl.appendChild(this.fDocumentType);
            if (this.fGrammarAccess) {
                final Element element = this.fDocument.createElement("schema");
                element.setAttribute("xmlns", "http://www.w3.org/2000/10/XMLSchema");
                ((AttrImpl)element.getAttributeNode("xmlns")).setSpecified(false);
                element.setAttribute("finalDefault", "");
                ((AttrImpl)element.getAttributeNode("finalDefault")).setSpecified(false);
                element.setAttribute("exactDefault", "");
                ((AttrImpl)element.getAttributeNode("exactDefault")).setSpecified(false);
                this.fDocumentType.appendChild(element);
                this.fCurrentElementNode = element;
            }
        }
        else if (this.fDeferredDocumentImpl != null) {
            this.fDocumentTypeIndex = this.fDeferredDocumentImpl.createDocumentType(qName.rawname, n, n2);
            this.fDeferredDocumentImpl.appendChild(this.fDocumentIndex, this.fDocumentTypeIndex);
            if (this.fGrammarAccess) {
                final int startAttrList = this.fAttrList.startAttrList();
                this.fAttrList.addAttr(super.fStringPool.addSymbol("xmlns"), super.fStringPool.addString("http://www.w3.org/2000/10/XMLSchema"), super.fStringPool.addSymbol("CDATA"), false, false);
                this.fAttrList.addAttr(super.fStringPool.addSymbol("finalDefault"), super.fStringPool.addString(""), super.fStringPool.addSymbol("CDATA"), false, false);
                this.fAttrList.addAttr(super.fStringPool.addSymbol("exactDefault"), super.fStringPool.addString(""), super.fStringPool.addSymbol("CDATA"), false, false);
                this.fAttrList.endAttrList();
                final int element2 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("schema"), this.fAttrList, startAttrList);
                this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, element2);
                this.fCurrentNodeIndex = element2;
            }
        }
    }
    
    public void internalSubset(final int n) {
        if (this.fDocumentImpl != null && this.fDocumentType != null) {
            ((DocumentTypeImpl)this.fDocumentType).setInternalSubset(super.fStringPool.toString(n));
        }
        else if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.setInternalSubset(this.fDocumentTypeIndex, n);
        }
    }
    
    public void endDTD() throws Exception {
        this.fInDTD = false;
        if (this.fGrammarAccess) {
            if (this.fDocumentImpl != null) {
                this.fCurrentElementNode = this.fDocumentImpl;
            }
            else if (this.fDeferredDocumentImpl != null) {
                this.fCurrentNodeIndex = 0;
            }
        }
    }
    
    public void elementDecl(final QName qName, final int n, int i, final XMLContentSpec.Provider provider) throws Exception {
        if (this.fGrammarAccess) {
            if (this.fDeferredDocumentImpl != null) {
                final int lastChildElement = this.getLastChildElement(this.fDocumentTypeIndex, "schema");
                final String string = super.fStringPool.toString(qName.rawname);
                int n2 = this.getLastChildElement(lastChildElement, "element", "name", string);
                if (n2 == -1) {
                    final int startAttrList = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("minOccurs"), super.fStringPool.addString("1"), super.fStringPool.addSymbol("NMTOKEN"), false, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("nullable"), super.fStringPool.addString("false"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("abstract"), super.fStringPool.addString("false"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("final"), super.fStringPool.addString("false"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                    this.fAttrList.endAttrList();
                    n2 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("element"), this.fAttrList, startAttrList);
                    this.fDeferredDocumentImpl.appendChild(lastChildElement, n2);
                }
                int n3 = this.getLastChildElement(n2, "complexType");
                if (n3 == -1 && n != 2) {
                    n3 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("complexType"), null, -1);
                    this.fDeferredDocumentImpl.insertBefore(n2, n3, this.getFirstChildElement(n2));
                }
                switch (n) {
                    case 0: {
                        this.fDeferredDocumentImpl.setAttributeNode(n3, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("content"), super.fStringPool.addString("empty"), true));
                        break;
                    }
                    case 1: {
                        this.fDeferredDocumentImpl.insertBefore(n3, this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("any"), null, -1), this.getFirstChildElement(n3));
                        break;
                    }
                    case 2: {
                        final XMLContentSpec xmlContentSpec = new XMLContentSpec();
                        provider.getContentSpec(i, xmlContentSpec);
                        i = xmlContentSpec.value;
                        if (i == -1) {
                            this.fDeferredDocumentImpl.setAttributeNode(n2, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("type"), super.fStringPool.addString("string"), true));
                            break;
                        }
                        if (n3 == -1) {
                            n3 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("complexType"), null, -1);
                            this.fDeferredDocumentImpl.insertBefore(n2, n3, this.getFirstChildElement(n2));
                        }
                        this.fDeferredDocumentImpl.setAttributeNode(n3, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("content"), super.fStringPool.addString("mixed"), true));
                        final int startAttrList2 = this.fAttrList.startAttrList();
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("minOccurs"), super.fStringPool.addString("0"), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("maxOccurs"), super.fStringPool.addString("unbounded"), super.fStringPool.addSymbol("CDATA"), true, false);
                        this.fAttrList.endAttrList();
                        final int element = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("choice"), this.fAttrList, startAttrList2);
                        this.fDeferredDocumentImpl.appendChild(n3, element);
                        while (i != -1) {
                            provider.getContentSpec(i, xmlContentSpec);
                            final int type = xmlContentSpec.type;
                            final int value = xmlContentSpec.value;
                            final int otherValue = xmlContentSpec.otherValue;
                            if (type == 0) {
                                break;
                            }
                            provider.getContentSpec(otherValue, xmlContentSpec);
                            final int startAttrList3 = this.fAttrList.startAttrList();
                            this.fAttrList.addAttr(super.fStringPool.addSymbol("ref"), super.fStringPool.addString(super.fStringPool.toString(xmlContentSpec.value)), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                            this.fAttrList.endAttrList();
                            this.fDeferredDocumentImpl.insertBefore(element, this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("element"), this.fAttrList, startAttrList3), this.getFirstChildElement(element));
                            i = value;
                        }
                        break;
                    }
                    case 3: {
                        this.fDeferredDocumentImpl.setAttributeNode(n3, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("content"), super.fStringPool.addString("elementOnly"), true));
                        this.fDeferredDocumentImpl.insertBefore(n3, this.createChildren(provider, i, new XMLContentSpec(), this.fDeferredDocumentImpl, -1), this.getFirstChildElement(n3));
                        break;
                    }
                }
            }
            else if (this.fDocumentImpl != null) {
                final Element lastChildElement2 = XUtil.getLastChildElement(this.fDocumentType, "schema");
                final String string2 = super.fStringPool.toString(qName.rawname);
                Element element2 = XUtil.getLastChildElement(lastChildElement2, "element", "name", string2);
                if (element2 == null) {
                    element2 = this.fDocumentImpl.createElement("element");
                    element2.setAttribute("name", string2);
                    element2.setAttribute("minOccurs", "1");
                    ((AttrImpl)element2.getAttributeNode("minOccurs")).setSpecified(false);
                    element2.setAttribute("nullable", "false");
                    ((AttrImpl)element2.getAttributeNode("nullable")).setSpecified(false);
                    element2.setAttribute("abstract", "false");
                    ((AttrImpl)element2.getAttributeNode("abstract")).setSpecified(false);
                    element2.setAttribute("final", "false");
                    ((AttrImpl)element2.getAttributeNode("final")).setSpecified(false);
                    lastChildElement2.appendChild(element2);
                }
                Element element3 = XUtil.getLastChildElement(element2, "complexType");
                if (element3 == null && n != 2) {
                    element3 = this.fDocumentImpl.createElement("complexType");
                    element2.insertBefore(element3, XUtil.getFirstChildElement(element2));
                }
                switch (n) {
                    case 0: {
                        element3.setAttribute("content", "empty");
                        break;
                    }
                    case 1: {
                        element3.insertBefore(this.fDocumentImpl.createElement("any"), XUtil.getFirstChildElement(element3));
                        break;
                    }
                    case 2: {
                        final XMLContentSpec xmlContentSpec2 = new XMLContentSpec();
                        provider.getContentSpec(i, xmlContentSpec2);
                        i = xmlContentSpec2.value;
                        if (i == -1) {
                            element2.setAttribute("type", "string");
                            break;
                        }
                        if (element3 == null) {
                            element3 = this.fDocumentImpl.createElement("complexType");
                            element2.insertBefore(element3, XUtil.getFirstChildElement(element2));
                        }
                        element3.setAttribute("content", "mixed");
                        final Element element4 = this.fDocumentImpl.createElement("choice");
                        element4.setAttribute("minOccurs", "0");
                        element4.setAttribute("maxOccurs", "unbounded");
                        element3.appendChild(element4);
                        while (i != -1) {
                            provider.getContentSpec(i, xmlContentSpec2);
                            final int type2 = xmlContentSpec2.type;
                            final int value2 = xmlContentSpec2.value;
                            final int otherValue2 = xmlContentSpec2.otherValue;
                            if (type2 == 0) {
                                break;
                            }
                            provider.getContentSpec(otherValue2, xmlContentSpec2);
                            final Element element5 = this.fDocumentImpl.createElement("element");
                            element5.setAttribute("ref", super.fStringPool.toString(xmlContentSpec2.value));
                            element4.insertBefore(element5, XUtil.getFirstChildElement(element4));
                            i = value2;
                        }
                        break;
                    }
                    case 3: {
                        element3.setAttribute("content", "elementOnly");
                        element3.insertBefore(this.createChildren(provider, i, new XMLContentSpec(), this.fDocumentImpl, null), XUtil.getFirstChildElement(element3));
                        break;
                    }
                }
            }
        }
    }
    
    public void attlistDecl(final QName qName, final QName qName2, final int n, final boolean b, final String s, final int n2, final int n3) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            if (n3 != -1) {
                int n4 = this.fDeferredDocumentImpl.lookupElementDefinition(qName.rawname);
                if (n4 == -1) {
                    n4 = this.fDeferredDocumentImpl.createElementDefinition(qName.rawname);
                    this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, n4);
                }
                this.fDeferredDocumentImpl.appendChild(n4, this.fDeferredDocumentImpl.createAttribute(qName2.rawname, qName2.uri, n3, false));
            }
            if (this.fGrammarAccess) {
                final int lastChildElement = this.getLastChildElement(this.fDocumentTypeIndex, "schema");
                final String string = super.fStringPool.toString(qName.rawname);
                int n5 = this.getLastChildElement(lastChildElement, "element", "name", string);
                if (n5 == -1) {
                    final int startAttrList = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.endAttrList();
                    n5 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("element"), this.fAttrList, startAttrList);
                    this.fDeferredDocumentImpl.appendChild(lastChildElement, n5);
                }
                int n6 = this.getLastChildElement(n5, "complexType");
                if (n6 == -1) {
                    n6 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("complexType"), null, -1);
                    this.fDeferredDocumentImpl.insertBefore(n5, n6, this.getLastChildElement(n5));
                }
                final String string2 = super.fStringPool.toString(qName2.rawname);
                if (this.getLastChildElement(n5, "attribute", "name", string2) == -1) {
                    final int startAttrList2 = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string2), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("maxOccurs"), super.fStringPool.addString("1"), super.fStringPool.addSymbol("CDATA"), false, false);
                    this.fAttrList.endAttrList();
                    final int element = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("attribute"), this.fAttrList, startAttrList2);
                    this.fDeferredDocumentImpl.appendChild(n6, element);
                    if (n == 2) {
                        final int startAttrList3 = this.fAttrList.startAttrList();
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("base"), super.fStringPool.addString("NMTOKEN"), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                        this.fAttrList.endAttrList();
                        final int element2 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("simpleType"), this.fAttrList, startAttrList3);
                        this.fDeferredDocumentImpl.appendChild(element, element2);
                        final StringTokenizer stringTokenizer = new StringTokenizer(s.substring(1, s.length() - 1), "|");
                        while (stringTokenizer.hasMoreTokens()) {
                            final int startAttrList4 = this.fAttrList.startAttrList();
                            this.fAttrList.addAttr(super.fStringPool.addSymbol("value"), super.fStringPool.addString(stringTokenizer.nextToken()), super.fStringPool.addSymbol("CDATA"), true, false);
                            this.fAttrList.endAttrList();
                            this.fDeferredDocumentImpl.appendChild(element2, this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("enumeration"), this.fAttrList, startAttrList4));
                        }
                    }
                    else {
                        int n7 = 0;
                        switch (n) {
                            case 1: {
                                n7 = super.fStringPool.addString(b ? "ENTITIES" : "ENTITY");
                                break;
                            }
                            case 3: {
                                n7 = super.fStringPool.addString("ID");
                                break;
                            }
                            case 4: {
                                n7 = super.fStringPool.addString(b ? "IDREFS" : "IDREF");
                                break;
                            }
                            case 5: {
                                n7 = super.fStringPool.addString(b ? "NMTOKENS" : "NMTOKEN");
                                break;
                            }
                            case 6: {
                                n7 = super.fStringPool.addString("NOTATION");
                                break;
                            }
                            default: {
                                n7 = super.fStringPool.addString("string");
                                break;
                            }
                        }
                        this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("type"), n7, true));
                    }
                    boolean b2 = false;
                    switch (n2) {
                        case 2: {
                            this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("use"), super.fStringPool.addString("required"), true));
                            break;
                        }
                        case 1: {
                            b2 = true;
                            this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("use"), super.fStringPool.addString("fixed"), true));
                            break;
                        }
                    }
                    if (n3 != -1) {
                        if (!b2) {
                            this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("use"), super.fStringPool.addString("default"), true));
                        }
                        this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("value"), n3, true));
                    }
                }
            }
        }
        else if (this.fDocumentImpl != null) {
            if (n3 != -1) {
                final String string3 = super.fStringPool.toString(qName.rawname);
                ElementDefinitionImpl elementDefinition = (ElementDefinitionImpl)((DocumentTypeImpl)this.fDocumentType).getElements().getNamedItem(string3);
                if (elementDefinition == null) {
                    elementDefinition = this.fDocumentImpl.createElementDefinition(string3);
                    ((DocumentTypeImpl)this.fDocumentType).getElements().setNamedItem(elementDefinition);
                }
                final String string4 = super.fStringPool.toString(qName2.rawname);
                final String string5 = super.fStringPool.toString(n3);
                boolean namespaces = false;
                try {
                    namespaces = this.getNamespaces();
                }
                catch (SAXException ex2) {}
                AttrImpl attrImpl;
                if (namespaces) {
                    String string6 = super.fStringPool.toString(qName2.uri);
                    final String string7 = super.fStringPool.toString(qName2.prefix);
                    if (string6 == null) {
                        if (string7 != null) {
                            if (string7.equals("xmlns")) {
                                string6 = "http://www.w3.org/2000/xmlns/";
                            }
                        }
                        else if (string4.equals("xmlns")) {
                            string6 = "http://www.w3.org/2000/xmlns/";
                        }
                    }
                    attrImpl = (AttrImpl)this.fDocumentImpl.createAttributeNS(string6, string4);
                }
                else {
                    attrImpl = (AttrImpl)this.fDocumentImpl.createAttribute(string4);
                }
                attrImpl.setValue(string5);
                attrImpl.setSpecified(false);
                if (namespaces) {
                    elementDefinition.getAttributes().setNamedItemNS(attrImpl);
                }
                else {
                    elementDefinition.getAttributes().setNamedItem(attrImpl);
                }
            }
            try {
                if (this.fGrammarAccess) {
                    final Element lastChildElement2 = XUtil.getLastChildElement(this.fDocumentType, "schema");
                    final String string8 = super.fStringPool.toString(qName.rawname);
                    Element element3 = XUtil.getLastChildElement(lastChildElement2, "element", "name", string8);
                    if (element3 == null) {
                        element3 = this.fDocumentImpl.createElement("element");
                        element3.setAttribute("name", string8);
                        lastChildElement2.appendChild(element3);
                    }
                    Element element4 = XUtil.getLastChildElement(element3, "complexType");
                    if (element4 == null) {
                        element4 = this.fDocumentImpl.createElement("complexType");
                        element3.insertBefore(element4, XUtil.getLastChildElement(element3));
                    }
                    final String string9 = super.fStringPool.toString(qName2.rawname);
                    if (XUtil.getLastChildElement(element3, "attribute", "name", string9) == null) {
                        final Element element5 = this.fDocumentImpl.createElement("attribute");
                        element5.setAttribute("name", string9);
                        element5.setAttribute("maxOccurs", "1");
                        ((AttrImpl)element5.getAttributeNode("maxOccurs")).setSpecified(false);
                        element4.appendChild(element5);
                        if (n == 2) {
                            final Element element6 = this.fDocumentImpl.createElement("simpleType");
                            element6.setAttribute("base", "NMTOKEN");
                            element5.appendChild(element6);
                            final StringTokenizer stringTokenizer2 = new StringTokenizer(s.substring(1, s.length() - 1), "|");
                            while (stringTokenizer2.hasMoreTokens()) {
                                final Element element7 = this.fDocumentImpl.createElement("enumeration");
                                element7.setAttribute("value", stringTokenizer2.nextToken());
                                element6.appendChild(element7);
                            }
                        }
                        else {
                            String s2 = null;
                            switch (n) {
                                case 1: {
                                    s2 = (b ? "ENTITIES" : "ENTITY");
                                    break;
                                }
                                case 3: {
                                    s2 = "ID";
                                    break;
                                }
                                case 4: {
                                    s2 = (b ? "IDREFS" : "IDREF");
                                    break;
                                }
                                case 5: {
                                    s2 = (b ? "NMTOKENS" : "NMTOKEN");
                                    break;
                                }
                                case 6: {
                                    s2 = "NOTATION";
                                    break;
                                }
                                default: {
                                    s2 = "string";
                                    break;
                                }
                            }
                            element5.setAttribute("type", s2);
                        }
                        boolean b3 = false;
                        switch (n2) {
                            case 2: {
                                element5.setAttribute("use", "required");
                                break;
                            }
                            case 1: {
                                element5.setAttribute("use", "fixed");
                                b3 = true;
                                break;
                            }
                        }
                        if (n3 != -1) {
                            if (!b3) {
                                element5.setAttribute("use", "default");
                            }
                            element5.setAttribute("value", super.fStringPool.toString(n3));
                        }
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
    }
    
    public void internalPEDecl(final int n, final int n2) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            if (this.fGrammarAccess) {
                final StringBuffer sb = new StringBuffer();
                sb.append("<!ENTITY % ");
                sb.append(super.fStringPool.toString(n));
                sb.append(" \"");
                sb.append(super.fStringPool.toString(n2));
                sb.append("\">");
                this.fDeferredDocumentImpl.appendChild(this.getFirstChildElement(this.fDocumentTypeIndex, "schema"), this.fDeferredDocumentImpl.createComment(super.fStringPool.addString(sb.toString())));
            }
        }
        else if (this.fDocumentImpl != null) {
            if (this.fGrammarAccess) {
                final StringBuffer sb2 = new StringBuffer();
                sb2.append("<!ENTITY % ");
                sb2.append(super.fStringPool.toString(n));
                sb2.append(" \"");
                sb2.append(super.fStringPool.orphanString(n2));
                sb2.append("\">");
                XUtil.getFirstChildElement(this.fDocumentType, "schema").appendChild(this.fDocumentImpl.createComment(sb2.toString()));
            }
        }
        else {
            super.fStringPool.orphanString(n2);
        }
    }
    
    public void externalPEDecl(final int n, final int n2, final int n3) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            if (this.fGrammarAccess) {
                final StringBuffer sb = new StringBuffer();
                sb.append("<!ENTITY ");
                sb.append(super.fStringPool.toString(n));
                sb.append(' ');
                if (n2 != -1) {
                    sb.append("PUBLIC \"");
                    sb.append(super.fStringPool.toString(n2));
                    sb.append('\"');
                    if (n3 != -1) {
                        sb.append(" \"");
                        sb.append(super.fStringPool.toString(n3));
                        sb.append('\"');
                    }
                }
                else if (n3 != -1) {
                    sb.append("SYSTEM \"");
                    sb.append(super.fStringPool.toString(n3));
                    sb.append('\"');
                }
                sb.append('>');
                this.fDeferredDocumentImpl.appendChild(this.getFirstChildElement(this.fDocumentTypeIndex, "schema"), this.fDeferredDocumentImpl.createComment(super.fStringPool.addString(sb.toString())));
            }
        }
        else if (this.fDocumentImpl != null && this.fGrammarAccess) {
            final StringBuffer sb2 = new StringBuffer();
            sb2.append("<!ENTITY ");
            sb2.append(super.fStringPool.toString(n));
            sb2.append(' ');
            if (n2 != -1) {
                sb2.append("PUBLIC \"");
                sb2.append(super.fStringPool.toString(n2));
                sb2.append('\"');
                if (n3 != -1) {
                    sb2.append(" \"");
                    sb2.append(super.fStringPool.toString(n3));
                    sb2.append('\"');
                }
            }
            else if (n3 != -1) {
                sb2.append("SYSTEM \"");
                sb2.append(super.fStringPool.toString(n3));
                sb2.append('\"');
            }
            sb2.append('>');
            XUtil.getFirstChildElement(this.fDocumentType, "schema").appendChild(this.fDocumentImpl.createComment(sb2.toString()));
        }
    }
    
    public void internalEntityDecl(final int n, final int n2) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            if (this.fDocumentTypeIndex == -1) {
                return;
            }
            this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createEntity(n, -1, -1, -1));
            if (this.fGrammarAccess) {
                final StringBuffer sb = new StringBuffer();
                sb.append("<!ENTITY ");
                sb.append(super.fStringPool.toString(n));
                sb.append(" \"");
                sb.append(super.fStringPool.toString(n2));
                sb.append("\">");
                this.fDeferredDocumentImpl.appendChild(this.getFirstChildElement(this.fDocumentTypeIndex, "schema"), this.fDeferredDocumentImpl.createComment(super.fStringPool.addString(sb.toString())));
            }
        }
        else if (this.fDocumentImpl != null) {
            if (this.fDocumentType == null) {
                return;
            }
            this.fDocumentType.getEntities().setNamedItem(this.fDocumentImpl.createEntity(super.fStringPool.toString(n)));
            if (this.fGrammarAccess) {
                final StringBuffer sb2 = new StringBuffer();
                sb2.append("<!ENTITY ");
                sb2.append(super.fStringPool.toString(n));
                sb2.append(" \"");
                sb2.append(super.fStringPool.toString(n2));
                sb2.append("\">");
                XUtil.getFirstChildElement(this.fDocumentType, "schema").appendChild(this.fDocumentImpl.createComment(sb2.toString()));
            }
        }
    }
    
    public void externalEntityDecl(final int n, final int n2, final int n3) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createEntity(n, n2, n3, -1));
            if (this.fGrammarAccess) {
                final StringBuffer sb = new StringBuffer();
                sb.append("<!ENTITY ");
                sb.append(super.fStringPool.toString(n));
                sb.append(' ');
                if (n2 != -1) {
                    sb.append("PUBLIC \"");
                    sb.append(super.fStringPool.toString(n2));
                    sb.append('\"');
                    if (n3 != -1) {
                        sb.append(" \"");
                        sb.append(super.fStringPool.toString(n3));
                        sb.append('\"');
                    }
                }
                else if (n3 != -1) {
                    sb.append("SYSTEM \"");
                    sb.append(super.fStringPool.toString(n3));
                    sb.append('\"');
                }
                sb.append('>');
                this.fDeferredDocumentImpl.appendChild(this.getFirstChildElement(this.fDocumentTypeIndex, "schema"), this.fDeferredDocumentImpl.createComment(super.fStringPool.addString(sb.toString())));
            }
        }
        else if (this.fDocumentImpl != null) {
            final String string = super.fStringPool.toString(n);
            final String string2 = super.fStringPool.toString(n2);
            final String string3 = super.fStringPool.toString(n3);
            final EntityImpl namedItem = (EntityImpl)this.fDocumentImpl.createEntity(string);
            if (n2 != -1) {
                namedItem.setPublicId(string2);
            }
            namedItem.setSystemId(string3);
            this.fDocumentType.getEntities().setNamedItem(namedItem);
            if (this.fGrammarAccess) {
                final StringBuffer sb2 = new StringBuffer();
                sb2.append("<!ENTITY ");
                sb2.append(super.fStringPool.toString(n));
                sb2.append(' ');
                if (n2 != -1) {
                    sb2.append("PUBLIC \"");
                    sb2.append(super.fStringPool.toString(n2));
                    sb2.append('\"');
                    if (n3 != -1) {
                        sb2.append(" \"");
                        sb2.append(super.fStringPool.toString(n3));
                        sb2.append('\"');
                    }
                }
                else if (n3 != -1) {
                    sb2.append("SYSTEM \"");
                    sb2.append(super.fStringPool.toString(n3));
                    sb2.append('\"');
                }
                sb2.append('>');
                XUtil.getFirstChildElement(this.fDocumentType, "schema").appendChild(this.fDocumentImpl.createComment(sb2.toString()));
            }
        }
    }
    
    public void unparsedEntityDecl(final int n, final int n2, final int n3, final int n4) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createEntity(n, n2, n3, n4));
            if (this.fGrammarAccess) {
                final StringBuffer sb = new StringBuffer();
                sb.append("<!ENTITY ");
                sb.append(super.fStringPool.toString(n));
                sb.append(' ');
                if (n2 != -1) {
                    sb.append("PUBLIC \"");
                    sb.append(super.fStringPool.toString(n2));
                    sb.append('\"');
                    if (n3 != -1) {
                        sb.append(" \"");
                        sb.append(super.fStringPool.toString(n3));
                        sb.append('\"');
                    }
                }
                else if (n3 != -1) {
                    sb.append("SYSTEM \"");
                    sb.append(super.fStringPool.toString(n3));
                    sb.append('\"');
                }
                sb.append(" NDATA ");
                sb.append(super.fStringPool.toString(n4));
                sb.append('>');
                this.fDeferredDocumentImpl.appendChild(this.getFirstChildElement(this.fDocumentTypeIndex, "schema"), this.fDeferredDocumentImpl.createComment(super.fStringPool.addString(sb.toString())));
            }
        }
        else if (this.fDocumentImpl != null) {
            final String string = super.fStringPool.toString(n);
            final String string2 = super.fStringPool.toString(n2);
            final String string3 = super.fStringPool.toString(n3);
            final String string4 = super.fStringPool.toString(n4);
            final EntityImpl namedItem = (EntityImpl)this.fDocumentImpl.createEntity(string);
            if (n2 != -1) {
                namedItem.setPublicId(string2);
            }
            namedItem.setSystemId(string3);
            namedItem.setNotationName(string4);
            this.fDocumentType.getEntities().setNamedItem(namedItem);
            if (this.fGrammarAccess) {
                final StringBuffer sb2 = new StringBuffer();
                sb2.append("<!ENTITY ");
                sb2.append(super.fStringPool.toString(n));
                sb2.append(' ');
                if (n2 != -1) {
                    sb2.append("PUBLIC \"");
                    sb2.append(super.fStringPool.toString(n2));
                    sb2.append('\"');
                    if (n3 != -1) {
                        sb2.append(" \"");
                        sb2.append(super.fStringPool.toString(n3));
                        sb2.append('\"');
                    }
                }
                else if (n3 != -1) {
                    sb2.append("SYSTEM \"");
                    sb2.append(super.fStringPool.toString(n3));
                    sb2.append('\"');
                }
                sb2.append(" NDATA ");
                sb2.append(super.fStringPool.toString(n4));
                sb2.append('>');
                XUtil.getFirstChildElement(this.fDocumentType, "schema").appendChild(this.fDocumentImpl.createComment(sb2.toString()));
            }
        }
    }
    
    public void notationDecl(final int n, final int n2, final int n3) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createNotation(n, n2, n3));
            if (this.fGrammarAccess) {
                final int lastChildElement = this.getLastChildElement(this.fDocumentTypeIndex, "schema");
                final String string = super.fStringPool.toString(n);
                if (this.getLastChildElement(lastChildElement, "notation", "name", string) == -1) {
                    final int startAttrList = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    if (n2 != -1) {
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("public"), n2, super.fStringPool.addSymbol("CDATA"), true, false);
                    }
                    if (n3 != -1) {
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("system"), n3, super.fStringPool.addSymbol("CDATA"), true, false);
                    }
                    this.fAttrList.endAttrList();
                    this.fDeferredDocumentImpl.appendChild(lastChildElement, this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("notation"), this.fAttrList, startAttrList));
                }
            }
        }
        else if (this.fDocumentImpl != null) {
            final String string2 = super.fStringPool.toString(n);
            final String string3 = super.fStringPool.toString(n2);
            final String string4 = super.fStringPool.toString(n3);
            final NotationImpl namedItem = (NotationImpl)this.fDocumentImpl.createNotation(string2);
            namedItem.setPublicId(string3);
            if (n3 != -1) {
                namedItem.setSystemId(string4);
            }
            this.fDocumentType.getNotations().setNamedItem(namedItem);
            if (this.fGrammarAccess) {
                final Element firstChildElement = XUtil.getFirstChildElement(this.fDocumentType, "schema");
                if (XUtil.getFirstChildElement(firstChildElement, "notation", "name", string2) == null) {
                    final Element element = this.fDocument.createElement("notation");
                    element.setAttribute("name", string2);
                    if (string3 != null) {
                        element.setAttribute("public", string3);
                    }
                    if (n3 != -1) {
                        element.setAttribute("system", string4);
                    }
                    firstChildElement.appendChild(element);
                }
            }
        }
    }
    
    private int getFirstChildElement(final int n) {
        int i;
        int prevSiblingElement;
        for (i = this.getLastChildElement(n); i != -1; i = prevSiblingElement) {
            prevSiblingElement = this.getPrevSiblingElement(i);
            if (prevSiblingElement == -1) {
                break;
            }
        }
        return i;
    }
    
    private int getFirstChildElement(final int n, final String s) {
        int i = this.getLastChildElement(n);
        if (i != -1) {
            final int addSymbol = super.fStringPool.addSymbol(s);
            while (i != -1) {
                if (this.fDeferredDocumentImpl.getNodeName(i, false) == addSymbol) {
                    break;
                }
                i = this.getPrevSiblingElement(i);
            }
        }
        return i;
    }
    
    private int getLastChildElement(final int n) {
        for (int i = this.fDeferredDocumentImpl.getLastChild(n, false); i != -1; i = this.fDeferredDocumentImpl.getPrevSibling(i, false)) {
            if (this.fDeferredDocumentImpl.getNodeType(i, false) == 1) {
                return i;
            }
        }
        return -1;
    }
    
    private int getPrevSiblingElement(final int n) {
        for (int i = this.fDeferredDocumentImpl.getPrevSibling(n, false); i != -1; i = this.fDeferredDocumentImpl.getPrevSibling(i, false)) {
            if (this.fDeferredDocumentImpl.getNodeType(i, false) == 1) {
                return i;
            }
        }
        return -1;
    }
    
    private int getLastChildElement(final int n, final String s) {
        int i = this.getLastChildElement(n);
        if (i != -1) {
            while (i != -1) {
                if (this.fDeferredDocumentImpl.getNodeNameString(i, false).equals(s)) {
                    return i;
                }
                i = this.getPrevSiblingElement(i);
            }
        }
        return -1;
    }
    
    private int getPrevSiblingElement(final int n, final String s) {
        int i = this.getPrevSiblingElement(n);
        if (i != -1) {
            while (i != -1) {
                if (this.fDeferredDocumentImpl.getNodeNameString(i, false).equals(s)) {
                    return i;
                }
                i = this.getPrevSiblingElement(i);
            }
        }
        return -1;
    }
    
    private int getLastChildElement(final int n, final String s, final String s2, final String s3) {
        int i = this.getLastChildElement(n, s);
        if (i != -1) {
            while (i != -1) {
                for (int j = this.fDeferredDocumentImpl.getNodeValue(i, false); j != -1; j = this.fDeferredDocumentImpl.getPrevSibling(j, false)) {
                    if (this.fDeferredDocumentImpl.getNodeNameString(j, false).equals(s2) && this.fDeferredDocumentImpl.getNodeValueString(this.fDeferredDocumentImpl.getLastChild(j, false), false).equals(s3)) {
                        return i;
                    }
                }
                i = this.getPrevSiblingElement(i, s);
            }
        }
        return -1;
    }
    
    private int getPrevSiblingElement(final int n, final String s, final String s2, final String s3) {
        int i = this.getPrevSiblingElement(n, s);
        if (i != -1) {
            super.fStringPool.addSymbol(s2);
            while (i != -1) {
                for (int j = this.fDeferredDocumentImpl.getNodeValue(i, false); j != -1; j = this.fDeferredDocumentImpl.getPrevSibling(j, false)) {
                    if (s3.equals(super.fStringPool.toString(this.fDeferredDocumentImpl.getNodeValue(j, false)))) {
                        return i;
                    }
                }
                i = this.getPrevSiblingElement(i, s);
            }
        }
        return -1;
    }
    
    private void copyInto(final Node node, int parentNode) throws Exception {
        final boolean b = node != null && node instanceof DocumentImpl;
        Node parentNode2 = node;
        int n = 0;
        for (Node node2 = node; node2 != null; node2 = node2.getFirstChild(), parentNode = n) {
            final short nodeType = node2.getNodeType();
            switch (nodeType) {
                case 4: {
                    n = this.fDeferredDocumentImpl.createCDATASection(super.fStringPool.addString(node2.getNodeValue()), b && ((TextImpl)node2).isIgnorableWhitespace());
                    break;
                }
                case 8: {
                    n = this.fDeferredDocumentImpl.createComment(super.fStringPool.addString(node2.getNodeValue()));
                    break;
                }
                case 1: {
                    XMLAttrList fAttrList = null;
                    int startAttrList = -1;
                    final NamedNodeMap attributes = node2.getAttributes();
                    if (attributes != null) {
                        final int length = attributes.getLength();
                        if (length > 0) {
                            startAttrList = this.fAttrList.startAttrList();
                            for (int i = 0; i < length; ++i) {
                                final Attr attr = (Attr)attributes.item(i);
                                this.fAttrList.addAttr(super.fStringPool.addSymbol(attr.getNodeName()), super.fStringPool.addString(attr.getNodeValue()), super.fStringPool.addSymbol("CDATA"), attr.getSpecified(), false);
                            }
                            this.fAttrList.endAttrList();
                            fAttrList = this.fAttrList;
                        }
                    }
                    n = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol(node2.getNodeName()), fAttrList, startAttrList);
                    break;
                }
                case 5: {
                    n = this.fDeferredDocumentImpl.createEntityReference(super.fStringPool.addSymbol(node2.getNodeName()));
                    break;
                }
                case 7: {
                    n = this.fDeferredDocumentImpl.createProcessingInstruction(super.fStringPool.addSymbol(node2.getNodeName()), super.fStringPool.addString(node2.getNodeValue()));
                    break;
                }
                case 3: {
                    n = this.fDeferredDocumentImpl.createTextNode(super.fStringPool.addString(node2.getNodeValue()), b && ((TextImpl)node2).isIgnorableWhitespace());
                    break;
                }
                default: {
                    throw new IllegalArgumentException("PAR010 Can't copy node type, " + nodeType + " (" + node2.getNodeName() + ')' + "\n" + nodeType + "\t" + node2.getNodeName());
                }
            }
            this.fDeferredDocumentImpl.appendChild(parentNode, n);
            if (node2.hasChildNodes()) {
                parentNode2 = node2;
            }
            else {
                for (node2 = node2.getNextSibling(); node2 == null && parentNode2 != node; node2 = parentNode2.getNextSibling(), parentNode2 = parentNode2.getParentNode(), parentNode = this.fDeferredDocumentImpl.getParentNode(parentNode, false)) {}
            }
        }
    }
    
    private void setOccurrenceCount(final Element element, final int n, final int n2) {
        element.setAttribute("minOccurs", Integer.toString(n));
        if (n == 1) {
            ((AttrImpl)element.getAttributeNode("minOccurs")).setSpecified(false);
        }
        if (n2 == -1) {
            element.setAttribute("maxOccurs", "*");
        }
        else if (n2 != 1) {
            element.setAttribute("maxOccurs", Integer.toString(n2));
        }
    }
    
    private Element createChildren(final XMLContentSpec.Provider provider, final int n, final XMLContentSpec xmlContentSpec, final DocumentImpl documentImpl, final Element element) throws Exception {
        provider.getContentSpec(n, xmlContentSpec);
        int n2 = -1;
        switch (xmlContentSpec.type) {
            case 3: {
                n2 = 43;
                provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                break;
            }
            case 2: {
                n2 = 42;
                provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                break;
            }
            case 1: {
                n2 = 63;
                provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                break;
            }
        }
        final int type = xmlContentSpec.type;
        switch (type) {
            case 4:
            case 5: {
                final int value = xmlContentSpec.value;
                final int otherValue = xmlContentSpec.otherValue;
                final Element children = this.createChildren(provider, value, xmlContentSpec, documentImpl, element);
                final Element children2 = this.createChildren(provider, otherValue, xmlContentSpec, documentImpl, null);
                final String s = (type == 4) ? "choice" : "sequence";
                Element element2 = children;
                if (!children.getNodeName().equals(s)) {
                    final String attribute = children.getAttribute("minOccurs");
                    final String attribute2 = children.getAttribute("maxOccurs");
                    final boolean b = attribute.length() == 0 || attribute.equals("1");
                    final boolean b2 = attribute2.length() == 0 || attribute2.equals("1");
                    if (element == null || (b && b2)) {
                        element2 = documentImpl.createElement(s);
                        element2.appendChild(children);
                    }
                    else {
                        element2 = element;
                    }
                }
                switch (n2) {
                    case 43: {
                        element2.setAttribute("maxOccurs", "unbounded");
                        break;
                    }
                    case 42: {
                        element2.setAttribute("minOccurs", "0");
                        element2.setAttribute("maxOccurs", "unbounded");
                        break;
                    }
                    case 63: {
                        element2.setAttribute("minOccurs", "0");
                        break;
                    }
                }
                element2.appendChild(children2);
                return element2;
            }
            case 0: {
                final Element element3 = documentImpl.createElement("element");
                element3.setAttribute("ref", super.fStringPool.toString(xmlContentSpec.value));
                switch (n2) {
                    case 43: {
                        element3.setAttribute("maxOccurs", "unbounded");
                        break;
                    }
                    case 42: {
                        element3.setAttribute("minOccurs", "0");
                        element3.setAttribute("maxOccurs", "unbounded");
                        break;
                    }
                    case 63: {
                        element3.setAttribute("minOccurs", "0");
                        break;
                    }
                }
                return element3;
            }
            default: {
                return null;
            }
        }
    }
    
    private int createChildren(final XMLContentSpec.Provider provider, final int n, final XMLContentSpec xmlContentSpec, final DeferredDocumentImpl deferredDocumentImpl, final int n2) throws Exception {
        provider.getContentSpec(n, xmlContentSpec);
        int n3 = -1;
        switch (xmlContentSpec.type) {
            case 3: {
                n3 = 43;
                provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                break;
            }
            case 2: {
                n3 = 42;
                provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                break;
            }
            case 1: {
                n3 = 63;
                provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                break;
            }
        }
        final int type = xmlContentSpec.type;
        switch (type) {
            case 4:
            case 5: {
                final int value = xmlContentSpec.value;
                final int otherValue = xmlContentSpec.otherValue;
                final int children = this.createChildren(provider, value, xmlContentSpec, deferredDocumentImpl, n2);
                final int children2 = this.createChildren(provider, otherValue, xmlContentSpec, deferredDocumentImpl, -1);
                final int addSymbol = super.fStringPool.addSymbol((type == 4) ? "choice" : "sequence");
                int element = children;
                if (deferredDocumentImpl.getNodeName(children, false) != addSymbol) {
                    final int attribute = deferredDocumentImpl.getAttribute(children, super.fStringPool.addSymbol("minOccurs"));
                    final int attribute2 = deferredDocumentImpl.getAttribute(children, super.fStringPool.addSymbol("maxOccurs"));
                    final boolean b = attribute == -1 || super.fStringPool.toString(attribute).equals("1");
                    final boolean b2 = attribute2 == -1 || super.fStringPool.toString(attribute2).equals("1");
                    if (n2 == -1 || (b && b2)) {
                        element = deferredDocumentImpl.createElement(addSymbol, null, -1);
                        deferredDocumentImpl.appendChild(element, children);
                    }
                    else {
                        element = n2;
                    }
                }
                switch (n3) {
                    case 43: {
                        deferredDocumentImpl.setAttributeNode(element, deferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("maxOccurs"), super.fStringPool.addString("unbounded"), true));
                        break;
                    }
                    case 42: {
                        deferredDocumentImpl.setAttributeNode(element, deferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("minOccurs"), super.fStringPool.addString("0"), true));
                        deferredDocumentImpl.setAttributeNode(element, deferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("maxOccurs"), super.fStringPool.addString("unbounded"), true));
                        break;
                    }
                    case 63: {
                        deferredDocumentImpl.setAttributeNode(element, deferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("minOccurs"), super.fStringPool.addString("0"), true));
                        break;
                    }
                }
                deferredDocumentImpl.appendChild(element, children2);
                return element;
            }
            case 0: {
                final int startAttrList = this.fAttrList.startAttrList();
                this.fAttrList.addAttr(super.fStringPool.addSymbol("ref"), super.fStringPool.addString(super.fStringPool.toString(xmlContentSpec.value)), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                switch (n3) {
                    case 43: {
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("maxOccurs"), super.fStringPool.addString("unbounded"), super.fStringPool.addSymbol("CDATA"), true, false);
                        break;
                    }
                    case 42: {
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("minOccurs"), super.fStringPool.addString("0"), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("maxOccurs"), super.fStringPool.addString("unbounded"), super.fStringPool.addSymbol("CDATA"), true, false);
                        break;
                    }
                    case 63: {
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("minOccurs"), super.fStringPool.addString("0"), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                        break;
                    }
                }
                this.fAttrList.endAttrList();
                return deferredDocumentImpl.createElement(super.fStringPool.addSymbol("element"), this.fAttrList, startAttrList);
            }
            default: {
                return -1;
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://apache.org/xml/features/dom/defer-node-expansion", "http://apache.org/xml/features/dom/create-entity-ref-nodes", "http://apache.org/xml/features/dom/include-ignorable-whitespace", "http://apache.org/xml/features/domx/grammar-access" };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/dom/document-class-name", "http://apache.org/xml/properties/dom/current-element-node" };
    }
}
