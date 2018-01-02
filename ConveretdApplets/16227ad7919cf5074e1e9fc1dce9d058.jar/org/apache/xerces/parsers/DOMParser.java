// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import org.apache.xerces.dom.NotationImpl;
import org.apache.xerces.dom.EntityImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xerces.dom.ElementDefinitionImpl;
import java.util.StringTokenizer;
import org.apache.xerces.framework.XMLContentSpecNode;
import org.apache.xerces.framework.XMLValidator;
import org.w3c.dom.NodeList;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.apache.xerces.dom.TextImpl;
import org.w3c.dom.Text;
import org.xml.sax.AttributeList;
import org.apache.xerces.validators.schema.XUtil;
import org.apache.xerces.dom.AttrImpl;
import org.apache.xerces.dom.ElementImpl;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.apache.xerces.framework.XMLAttrList;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentType;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DeferredDocumentImpl;
import org.w3c.dom.Document;
import java.util.Hashtable;
import org.apache.xerces.framework.XMLParser;

public class DOMParser extends XMLParser
{
    public static final String DEFAULT_DOCUMENT_CLASS_NAME = "org.apache.xerces.dom.DocumentImpl";
    private static final boolean DEBUG_ATTLIST_DECL = false;
    private static final String[] RECOGNIZED_FEATURES;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final Hashtable TYPES;
    protected Document fDocument;
    protected DeferredDocumentImpl fDeferredDocumentImpl;
    protected int fDocumentIndex;
    protected int fDocumentTypeIndex;
    protected int fCurrentNodeIndex;
    protected DocumentImpl fDocumentImpl;
    protected DocumentType fDocumentType;
    protected Node fCurrentElementNode;
    protected boolean fWithinElement;
    protected boolean fInCDATA;
    private boolean fGrammarAccess;
    private String fDocumentClassName;
    private boolean fDeferNodeExpansion;
    private boolean fCreateEntityReferenceNodes;
    protected int fAmpIndex;
    protected int fLtIndex;
    protected int fGtIndex;
    protected int fAposIndex;
    protected int fQuotIndex;
    private boolean fSeenRootElement;
    private XMLAttrList fAttrList;
    static /* synthetic */ Class class$org$w3c$dom$Document;
    
    public DOMParser() {
        this.init();
        try {
            this.setDocumentClassName("org.apache.xerces.dom.DocumentImpl");
            this.setCreateEntityReferenceNodes(true);
            this.setDeferNodeExpansion(true);
        }
        catch (SAXException ex) {
            throw new RuntimeException("fatal error constructing DOMParser");
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
        this.fDocumentImpl = null;
        this.fDocumentType = null;
        this.fCurrentElementNode = null;
        this.fWithinElement = false;
        this.fInCDATA = false;
        this.fAmpIndex = super.fStringPool.addSymbol("amp");
        this.fLtIndex = super.fStringPool.addSymbol("lt");
        this.fGtIndex = super.fStringPool.addSymbol("gt");
        this.fAposIndex = super.fStringPool.addSymbol("apos");
        this.fQuotIndex = super.fStringPool.addSymbol("quot");
        this.setSendCharDataAsCharArray(false);
        this.fSeenRootElement = false;
        this.fAttrList = new XMLAttrList(super.fStringPool);
    }
    
    protected void setDeferNodeExpansion(final boolean fDeferNodeExpansion) throws SAXException {
        this.fDeferNodeExpansion = fDeferNodeExpansion;
    }
    
    protected boolean getDeferNodeExpansion() throws SAXException {
        return this.fDeferNodeExpansion;
    }
    
    protected void setCreateEntityReferenceNodes(final boolean fCreateEntityReferenceNodes) throws SAXException {
        this.fCreateEntityReferenceNodes = fCreateEntityReferenceNodes;
    }
    
    public boolean getCreateEntityReferenceNodes() throws SAXException {
        return this.fCreateEntityReferenceNodes;
    }
    
    protected void setDocumentClassName(String fDocumentClassName) throws SAXException {
        if (fDocumentClassName == null) {
            fDocumentClassName = "org.apache.xerces.dom.DocumentImpl";
        }
        try {
            if (!((DOMParser.class$org$w3c$dom$Document != null) ? DOMParser.class$org$w3c$dom$Document : (DOMParser.class$org$w3c$dom$Document = class$("org.w3c.dom.Document"))).isAssignableFrom(Class.forName(fDocumentClassName))) {
                throw new IllegalArgumentException("Class, \"" + fDocumentClassName + "\", is not of type org.w3c.dom.Document.");
            }
        }
        catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException("Class, \"" + fDocumentClassName + "\", not found.");
        }
        this.fDocumentClassName = fDocumentClassName;
        if (!fDocumentClassName.equals("org.apache.xerces.dom.DocumentImpl")) {
            this.setDeferNodeExpansion(false);
        }
    }
    
    protected String getDocumentClassName() throws SAXException {
        return this.fDocumentClassName;
    }
    
    protected Element getCurrentElementNode() throws SAXException {
        if (this.fCurrentElementNode != null && this.fCurrentElementNode.getNodeType() == 1) {
            return (Element)this.fCurrentElementNode;
        }
        return null;
    }
    
    public void setFeature(final String s, final boolean fGrammarAccess) throws SAXException {
        if (!s.startsWith("http://xml.org/sax/features/") && s.startsWith("http://apache.org/xml/features/")) {
            final String substring = s.substring("http://apache.org/xml/features/".length());
            if (substring.equals("dom/defer-node-expansion")) {
                if (super.fParseInProgress) {
                    throw new SAXNotSupportedException(String.valueOf(s) + ": parse is in progress");
                }
                this.setDeferNodeExpansion(fGrammarAccess);
                return;
            }
            else {
                if (substring.equals("dom/create-entity-ref-nodes")) {
                    this.setCreateEntityReferenceNodes(fGrammarAccess);
                    return;
                }
                if (substring.equals("domx/grammar-access")) {
                    this.fGrammarAccess = fGrammarAccess;
                    return;
                }
            }
        }
        super.setFeature(s, fGrammarAccess);
    }
    
    public boolean getFeature(final String s) throws SAXException {
        if (!s.startsWith("http://xml.org/sax/features/") && s.startsWith("http://apache.org/xml/features/")) {
            final String substring = s.substring("http://apache.org/xml/features/".length());
            if (substring.equals("dom/defer-node-expansion")) {
                return this.getDeferNodeExpansion();
            }
            if (substring.equals("dom/create-entity-ref-nodes")) {
                return this.getCreateEntityReferenceNodes();
            }
            if (substring.equals("domx/grammar-access")) {
                return this.fGrammarAccess;
            }
        }
        return super.getFeature(s);
    }
    
    public void setProperty(final String s, final Object o) throws SAXException {
        if (s.startsWith("http://apache.org/xml/properties/")) {
            final String substring = s.substring("http://apache.org/xml/properties/".length());
            if (substring.equals("dom/current-element-node")) {
                throw new SAXNotSupportedException("Property, \"" + s + "\" is read-only.");
            }
            if (substring.equals("dom/document-class-name")) {
                if (o != null && !(o instanceof String)) {
                    throw new SAXNotSupportedException("Property value must be of type java.lang.String.");
                }
                this.setDocumentClassName((String)o);
                return;
            }
        }
        super.setProperty(s, o);
    }
    
    public Object getProperty(final String s) throws SAXException {
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
                    throw new SAXNotSupportedException("Current element node cannot be queried when node expansion is deferred.");
                }
                return this.getCurrentElementNode();
            }
            else if (substring.equals("dom/document-class-name")) {
                return this.getDocumentClassName();
            }
        }
        return super.getProperty(s);
    }
    
    public void startDocument(final int n, final int n2, final int n3) {
        if (n != -1) {
            super.fStringPool.orphanString(n);
        }
        if (n2 != -1) {
            super.fStringPool.orphanString(n2);
        }
        if (n3 != -1) {
            super.fStringPool.orphanString(n3);
        }
        String documentClassName;
        try {
            documentClassName = this.getDocumentClassName();
        }
        catch (SAXException ex) {
            throw new RuntimeException("fatal error getting document factory");
        }
        boolean deferNodeExpansion;
        try {
            deferNodeExpansion = this.getDeferNodeExpansion();
        }
        catch (SAXException ex2) {
            throw new RuntimeException("fatal error reading expansion mode");
        }
        if (documentClassName.equals("org.apache.xerces.dom.DocumentImpl") && deferNodeExpansion) {
            boolean namespaces = false;
            try {
                namespaces = this.getNamespaces();
            }
            catch (SAXException ex3) {}
            final DeferredDocumentImpl deferredDocumentImpl = new DeferredDocumentImpl(super.fStringPool, namespaces, this.fGrammarAccess);
            this.fDeferredDocumentImpl = deferredDocumentImpl;
            this.fDocument = deferredDocumentImpl;
            this.fDocumentIndex = this.fDeferredDocumentImpl.createDocument();
            this.fCurrentNodeIndex = this.fDocumentIndex;
            return;
        }
        if (documentClassName.equals("org.apache.xerces.dom.DocumentImpl")) {
            final DocumentImpl documentImpl = new DocumentImpl(this.fGrammarAccess);
            this.fDocumentImpl = documentImpl;
            this.fDocument = documentImpl;
        }
        else {
            try {
                this.fDocument = (Document)Class.forName(documentClassName).newInstance();
            }
            catch (Exception ex4) {}
        }
        this.fCurrentElementNode = this.fDocument;
    }
    
    public void endDocument() throws Exception {
    }
    
    public void startNamespaceDeclScope(final int n, final int n2) throws Exception {
    }
    
    public void endNamespaceDeclScope(final int n) throws Exception {
    }
    
    public void startElement(final int n, final XMLAttrList list, final int n2) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            final int element = this.fDeferredDocumentImpl.createElement(n, list, n2);
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, element);
            this.fCurrentNodeIndex = element;
            this.fWithinElement = true;
            for (int i = list.getFirstAttr(n2); i != -1; i = list.getNextAttr(i)) {
                if (list.getAttType(i) == super.fStringPool.addSymbol("ID")) {
                    this.fDeferredDocumentImpl.putIdentifier(list.getAttValue(i), element);
                }
            }
            if (!this.fSeenRootElement) {
                this.fSeenRootElement = true;
                if (this.fGrammarAccess && super.fValidator == super.fSchemaValidator) {
                    final Document schemaDocument = super.fSchemaValidator.getSchemaDocument();
                    if (schemaDocument != null) {
                        if (this.fDocumentTypeIndex == -1) {
                            this.fDocumentTypeIndex = this.fDeferredDocumentImpl.createDocumentType(n, -1, -1);
                            this.fDeferredDocumentImpl.appendChild(0, this.fDocumentTypeIndex);
                        }
                        this.copyInto(schemaDocument.getDocumentElement(), this.fDocumentTypeIndex);
                    }
                }
            }
        }
        else {
            boolean namespaces = false;
            try {
                namespaces = this.getNamespaces();
            }
            catch (SAXException ex) {}
            final String string = super.fStringPool.toString(n);
            final AttributeList attributeList = list.getAttributeList(n2);
            Element element2;
            if (namespaces) {
                element2 = ((DocumentImpl)this.fDocument).createElementNS(super.fStringPool.toString(super.fStringPool.getURIForQName(n)), super.fStringPool.toString(n));
            }
            else {
                element2 = this.fDocument.createElement(string);
            }
            for (int length = attributeList.getLength(), j = 0; j < length; ++j) {
                if (namespaces) {
                    final int attrName = list.getAttrName(j);
                    ((ElementImpl)element2).setAttributeNS(super.fStringPool.toString(super.fStringPool.getURIForQName(attrName)), super.fStringPool.toString(attrName), attributeList.getValue(j));
                }
                else {
                    final String name = attributeList.getName(j);
                    element2.setAttribute(name, attributeList.getValue(j));
                    if (this.fDocumentImpl != null && !list.isSpecified(j)) {
                        ((AttrImpl)element2.getAttributeNode(name)).setSpecified(false);
                    }
                }
            }
            this.fCurrentElementNode.appendChild(element2);
            this.fCurrentElementNode = element2;
            this.fWithinElement = true;
            if (this.fDocumentImpl != null) {
                for (int k = list.getFirstAttr(n2); k != -1; k = list.getNextAttr(k)) {
                    if (list.getAttType(k) == super.fStringPool.addSymbol("ID")) {
                        this.fDocumentImpl.putIdentifier(super.fStringPool.toString(list.getAttValue(k)), element2);
                    }
                }
            }
            list.releaseAttrList(n2);
            if (!this.fSeenRootElement) {
                this.fSeenRootElement = true;
                if (this.fDocumentImpl != null && this.fGrammarAccess && super.fValidator == super.fSchemaValidator) {
                    final Document schemaDocument2 = super.fSchemaValidator.getSchemaDocument();
                    if (schemaDocument2 != null) {
                        if (this.fDocumentType == null) {
                            this.fDocumentType = this.fDocumentImpl.createDocumentType(string, "", "");
                            this.fDocument.appendChild(this.fDocumentType);
                        }
                        XUtil.copyInto(schemaDocument2.getDocumentElement(), this.fDocumentType);
                    }
                }
            }
        }
    }
    
    public void endElement(final int n) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex);
            this.fWithinElement = false;
            return;
        }
        this.fCurrentElementNode = this.fCurrentElementNode.getParentNode();
        this.fWithinElement = false;
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
            return;
        }
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
    
    public void ignorableWhitespace(final int n) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            int n2;
            if (this.fInCDATA) {
                n2 = this.fDeferredDocumentImpl.createCDATASection(n, true);
            }
            else {
                n2 = this.fDeferredDocumentImpl.createTextNode(n, true);
            }
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, n2);
            return;
        }
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
    
    public void processingInstruction(final int n, final int n2) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDeferredDocumentImpl.createProcessingInstruction(n, n2));
            return;
        }
        this.fCurrentElementNode.appendChild(this.fDocument.createProcessingInstruction(super.fStringPool.orphanString(n), super.fStringPool.orphanString(n2)));
    }
    
    public void comment(final int n) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDeferredDocumentImpl.createComment(n));
            return;
        }
        this.fCurrentElementNode.appendChild(this.fDocument.createComment(super.fStringPool.orphanString(n)));
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
    
    public void startEntityReference(final int n, final int n2, final int n3) throws Exception {
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
            final int entityReference = this.fDeferredDocumentImpl.createEntityReference(n);
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, entityReference);
            this.fCurrentNodeIndex = entityReference;
            return;
        }
        final EntityReference entityReference2 = this.fDocument.createEntityReference(super.fStringPool.toString(n));
        this.fCurrentElementNode.appendChild(entityReference2);
        this.fCurrentElementNode = entityReference2;
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
            this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(fCurrentNodeIndex);
            if (this.fDeferredDocumentImpl.getNodeType(fCurrentNodeIndex) != 5) {
                return;
            }
            final int firstChild = this.fDeferredDocumentImpl.getFirstChild(fCurrentNodeIndex);
            if (this.fDocumentTypeIndex != -1) {
                int n4;
                for (n4 = this.fDeferredDocumentImpl.getFirstChild(this.fDocumentTypeIndex); n4 != -1 && (this.fDeferredDocumentImpl.getNodeType(n4) != 6 || !this.fDeferredDocumentImpl.getNodeNameString(n4).equals(string)); n4 = this.fDeferredDocumentImpl.getNextSibling(n4)) {}
                if (n4 != -1 && this.fDeferredDocumentImpl.getFirstChild(n4) == -1) {
                    this.fDeferredDocumentImpl.setAsFirstChild(n4, firstChild);
                }
            }
        }
        else {
            final Node fCurrentElementNode = this.fCurrentElementNode;
            this.fCurrentElementNode = fCurrentElementNode.getParentNode();
            if (this.fDocumentImpl != null) {
                final Node namedItem = this.fDocumentType.getEntities().getNamedItem(super.fStringPool.toString(n));
                if (namedItem == null || namedItem.hasChildNodes()) {
                    return;
                }
                final Entity entity = (Entity)namedItem;
                if (fCurrentElementNode.hasChildNodes()) {
                    final NodeList childNodes = fCurrentElementNode.getChildNodes();
                    for (int length = childNodes.getLength(), i = 0; i < length; ++i) {
                        entity.appendChild(childNodes.item(i).cloneNode(true));
                    }
                }
            }
        }
    }
    
    public void startDTD(final int n, final int n2, final int n3) throws Exception {
        if (this.fDocumentImpl != null) {
            this.fDocumentType = this.fDocumentImpl.createDocumentType(super.fStringPool.toString(n), super.fStringPool.toString(n2), super.fStringPool.toString(n3));
            this.fDocumentImpl.appendChild(this.fDocumentType);
            if (this.fGrammarAccess) {
                final Element element = this.fDocument.createElement("schema");
                element.setAttribute("xmlns", "http://www.w3.org/XML/Group/1999/09/23-xmlschema/");
                ((AttrImpl)element.getAttributeNode("xmlns")).setSpecified(false);
                element.setAttribute("model", "closed");
                ((AttrImpl)element.getAttributeNode("model")).setSpecified(false);
                this.fDocumentType.appendChild(element);
            }
        }
        else if (this.fDeferredDocumentImpl != null) {
            this.fDocumentTypeIndex = this.fDeferredDocumentImpl.createDocumentType(n, n2, n3);
            this.fDeferredDocumentImpl.appendChild(this.fDocumentIndex, this.fDocumentTypeIndex);
            if (this.fGrammarAccess) {
                final int startAttrList = this.fAttrList.startAttrList();
                this.fAttrList.addAttr(super.fStringPool.addSymbol("xmlns"), super.fStringPool.addString("http://www.w3.org/XML/Group/1999/09/23-xmlschema/"), super.fStringPool.addSymbol("CDATA"), false, false);
                this.fAttrList.addAttr(super.fStringPool.addSymbol("model"), super.fStringPool.addString("closed"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                this.fAttrList.endAttrList();
                this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("schema"), this.fAttrList, startAttrList));
            }
        }
    }
    
    public void endDTD() throws Exception {
    }
    
    public void elementDecl(final int n, final ContentSpec contentSpec) throws Exception {
        if (this.fGrammarAccess) {
            if (this.fDeferredDocumentImpl != null) {
                final int firstChildElement = this.getFirstChildElement(this.fDocumentTypeIndex, "schema");
                final String string = super.fStringPool.toString(n);
                int n2 = this.getFirstChildElement(firstChildElement, "element", "name", string);
                if (n2 == -1) {
                    final int startAttrList = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("export"), super.fStringPool.addString("true"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                    this.fAttrList.endAttrList();
                    n2 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("element"), this.fAttrList, startAttrList);
                    this.fDeferredDocumentImpl.appendChild(firstChildElement, n2);
                }
                int n3 = this.getFirstChildElement(n2, "archetype");
                if (n3 == -1) {
                    n3 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("archetype"), null, -1);
                    this.fDeferredDocumentImpl.insertBefore(n2, n3, this.getFirstChildElement(n2));
                }
                final String string2 = super.fStringPool.toString(contentSpec.getType());
                if (string2.equals("EMPTY")) {
                    this.fDeferredDocumentImpl.setAttributeNode(n3, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("content"), super.fStringPool.addString("empty"), true));
                    return;
                }
                if (string2.equals("ANY")) {
                    this.fDeferredDocumentImpl.setAttributeNode(n3, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("content"), super.fStringPool.addString("any"), true));
                    return;
                }
                if (string2.equals("CHILDREN")) {
                    this.fDeferredDocumentImpl.setAttributeNode(n3, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("content"), super.fStringPool.addString("elemOnly"), false));
                    this.fDeferredDocumentImpl.setAttributeNode(n3, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("order"), super.fStringPool.addString("seq"), false));
                    final XMLContentSpecNode xmlContentSpecNode = new XMLContentSpecNode();
                    contentSpec.getNode(contentSpec.getHandle(), xmlContentSpecNode);
                    this.fDeferredDocumentImpl.insertBefore(n3, this.createDeferredContentModel(this.createContentModel(contentSpec, xmlContentSpecNode)), this.getFirstChildElement(n3));
                    return;
                }
                this.fDeferredDocumentImpl.setAttributeNode(n3, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("content"), super.fStringPool.addString("mixed"), true));
                final XMLContentSpecNode xmlContentSpecNode2 = new XMLContentSpecNode();
                contentSpec.getNode(contentSpec.getHandle(), xmlContentSpecNode2);
                if (xmlContentSpecNode2.type != 0) {
                    contentSpec.getNode(xmlContentSpecNode2.value, xmlContentSpecNode2);
                    do {
                        final int value = xmlContentSpecNode2.value;
                        final int startAttrList2 = this.fAttrList.startAttrList();
                        contentSpec.getNode(xmlContentSpecNode2.otherValue, xmlContentSpecNode2);
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("ref"), super.fStringPool.addString(super.fStringPool.toString(xmlContentSpecNode2.value)), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                        this.fAttrList.endAttrList();
                        this.fDeferredDocumentImpl.insertBefore(n3, this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("element"), this.fAttrList, startAttrList2), this.getFirstChildElement(n3, "element"));
                        contentSpec.getNode(value, xmlContentSpecNode2);
                    } while (xmlContentSpecNode2.type != 0);
                }
            }
            else if (this.fDocumentImpl != null) {
                final Element firstChildElement2 = XUtil.getFirstChildElement(this.fDocumentType, "schema");
                final String string3 = super.fStringPool.toString(n);
                Element element = XUtil.getFirstChildElement(firstChildElement2, "element", "name", string3);
                if (element == null) {
                    element = this.fDocument.createElement("element");
                    element.setAttribute("name", string3);
                    element.setAttribute("export", "true");
                    ((AttrImpl)element.getAttributeNode("export")).setSpecified(false);
                    firstChildElement2.appendChild(element);
                }
                Element element2 = XUtil.getFirstChildElement(element, "archetype");
                if (element2 == null) {
                    element2 = this.fDocument.createElement("archetype");
                    element.insertBefore(element2, XUtil.getFirstChildElement(element));
                }
                final String string4 = super.fStringPool.toString(contentSpec.getType());
                if (string4.equals("EMPTY")) {
                    element2.setAttribute("content", "empty");
                    return;
                }
                if (string4.equals("ANY")) {
                    element2.setAttribute("content", "any");
                    return;
                }
                if (string4.equals("CHILDREN")) {
                    element2.setAttribute("content", "elemOnly");
                    ((AttrImpl)element2.getAttributeNode("content")).setSpecified(false);
                    element2.setAttribute("order", "seq");
                    ((AttrImpl)element2.getAttributeNode("order")).setSpecified(false);
                    final XMLContentSpecNode xmlContentSpecNode3 = new XMLContentSpecNode();
                    contentSpec.getNode(contentSpec.getHandle(), xmlContentSpecNode3);
                    element2.insertBefore(this.createContentModel(contentSpec, xmlContentSpecNode3), XUtil.getFirstChildElement(element2));
                    return;
                }
                element2.setAttribute("content", "mixed");
                final XMLContentSpecNode xmlContentSpecNode4 = new XMLContentSpecNode();
                contentSpec.getNode(contentSpec.getHandle(), xmlContentSpecNode4);
                if (xmlContentSpecNode4.type != 0) {
                    contentSpec.getNode(xmlContentSpecNode4.value, xmlContentSpecNode4);
                    do {
                        final int value2 = xmlContentSpecNode4.value;
                        final Element element3 = this.fDocument.createElement("element");
                        contentSpec.getNode(xmlContentSpecNode4.otherValue, xmlContentSpecNode4);
                        element3.setAttribute("ref", super.fStringPool.toString(xmlContentSpecNode4.value));
                        element2.insertBefore(element3, XUtil.getFirstChildElement(element2, "element"));
                        contentSpec.getNode(value2, xmlContentSpecNode4);
                    } while (xmlContentSpecNode4.type != 0);
                }
            }
        }
    }
    
    public void attlistDecl(final int n, final int n2, final int n3, final String s, final int n4, final int n5) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            if (n5 != -1) {
                int n6 = this.fDeferredDocumentImpl.lookupElementDefinition(n);
                if (n6 == -1) {
                    n6 = this.fDeferredDocumentImpl.createElementDefinition(n);
                    this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, n6);
                }
                this.fDeferredDocumentImpl.appendChild(n6, this.fDeferredDocumentImpl.createAttribute(n2, n5, false));
            }
            if (this.fGrammarAccess) {
                final int firstChildElement = this.getFirstChildElement(this.fDocumentTypeIndex, "schema");
                final String string = super.fStringPool.toString(n);
                int n7 = this.getFirstChildElement(firstChildElement, "element", "name", string);
                if (n7 == -1) {
                    final int startAttrList = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("export"), super.fStringPool.addString("true"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                    this.fAttrList.endAttrList();
                    n7 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("element"), this.fAttrList, startAttrList);
                    this.fDeferredDocumentImpl.appendChild(firstChildElement, n7);
                }
                int n8 = this.getFirstChildElement(n7, "archetype");
                if (n8 == -1) {
                    n8 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("archetype"), null, -1);
                    this.fDeferredDocumentImpl.insertBefore(n7, n8, this.getFirstChildElement(n7));
                }
                final String string2 = super.fStringPool.toString(n2);
                if (this.getFirstChildElement(n7, "attribute", "name", string2) == -1) {
                    final int startAttrList2 = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string2), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("minOccurs"), super.fStringPool.addString("0"), super.fStringPool.addSymbol("CDATA"), false, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("maxOccurs"), super.fStringPool.addString("1"), super.fStringPool.addSymbol("CDATA"), false, false);
                    this.fAttrList.endAttrList();
                    final int element = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("attribute"), this.fAttrList, startAttrList2);
                    this.fDeferredDocumentImpl.appendChild(n8, element);
                    final String string3 = super.fStringPool.toString(n3);
                    if (string3.equals("CDATA")) {
                        this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("type"), super.fStringPool.addString("string"), false));
                    }
                    else if (string3.equals("ENUMERATION")) {
                        this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("type"), super.fStringPool.addString("NMTOKEN"), true));
                        final int element2 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("enumeration"), null, -1);
                        this.fDeferredDocumentImpl.appendChild(element, element2);
                        final StringTokenizer stringTokenizer = new StringTokenizer(s.substring(1, s.length() - 1), "|");
                        while (stringTokenizer.hasMoreTokens()) {
                            final int element3 = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("literal"), null, -1);
                            this.fDeferredDocumentImpl.appendChild(element3, this.fDeferredDocumentImpl.createTextNode(super.fStringPool.addString(stringTokenizer.nextToken()), false));
                            this.fDeferredDocumentImpl.appendChild(element2, element3);
                        }
                    }
                    else {
                        this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("type"), super.fStringPool.addString(string3), true));
                    }
                    boolean b = false;
                    if (n4 != -1) {
                        final String string4 = super.fStringPool.toString(n4);
                        if (string4.equals("#REQUIRED")) {
                            super.fStringPool.releaseString(this.fDeferredDocumentImpl.getNodeValue(this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("minOccurs"), super.fStringPool.addString("1"), true))));
                        }
                        else if (string4.equals("#FIXED")) {
                            b = true;
                            this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("fixed"), n5, true));
                        }
                    }
                    if (!b && n5 != -1) {
                        this.fDeferredDocumentImpl.setAttributeNode(element, this.fDeferredDocumentImpl.createAttribute(super.fStringPool.addSymbol("default"), n5, true));
                    }
                }
            }
        }
        else if (this.fDocumentImpl != null) {
            if (n5 != -1) {
                final String string5 = super.fStringPool.toString(n);
                ElementDefinitionImpl elementDefinition = (ElementDefinitionImpl)((DocumentTypeImpl)this.fDocumentType).getElements().getNamedItem(string5);
                if (elementDefinition == null) {
                    elementDefinition = this.fDocumentImpl.createElementDefinition(string5);
                    ((DocumentTypeImpl)this.fDocumentType).getElements().setNamedItem(elementDefinition);
                }
                final String string6 = super.fStringPool.toString(n2);
                final String string7 = super.fStringPool.toString(n5);
                final AttrImpl namedItem = (AttrImpl)this.fDocumentImpl.createAttribute(string6);
                namedItem.setValue(string7);
                namedItem.setSpecified(false);
                elementDefinition.getAttributes().setNamedItem(namedItem);
            }
            if (this.fGrammarAccess) {
                final Element firstChildElement2 = XUtil.getFirstChildElement(this.fDocumentType, "schema");
                final String string8 = super.fStringPool.toString(n);
                Element element4 = XUtil.getFirstChildElement(firstChildElement2, "element", "name", string8);
                if (element4 == null) {
                    element4 = this.fDocument.createElement("element");
                    element4.setAttribute("name", string8);
                    element4.setAttribute("export", "true");
                    ((AttrImpl)element4.getAttributeNode("export")).setSpecified(false);
                    firstChildElement2.appendChild(element4);
                }
                Element element5 = XUtil.getFirstChildElement(element4, "archetype");
                if (element5 == null) {
                    element5 = this.fDocument.createElement("archetype");
                    element4.insertBefore(element5, XUtil.getFirstChildElement(element4));
                }
                final String string9 = super.fStringPool.toString(n2);
                if (XUtil.getFirstChildElement(element4, "attribute", "name", string9) == null) {
                    final Element element6 = this.fDocument.createElement("attribute");
                    element6.setAttribute("name", string9);
                    element6.setAttribute("minOccurs", "0");
                    ((AttrImpl)element6.getAttributeNode("minOccurs")).setSpecified(false);
                    element6.setAttribute("maxOccurs", "1");
                    ((AttrImpl)element6.getAttributeNode("maxOccurs")).setSpecified(false);
                    element5.appendChild(element6);
                    final String string10 = super.fStringPool.toString(n3);
                    if (string10.equals("CDATA")) {
                        element6.setAttribute("type", "string");
                        ((AttrImpl)element6.getAttributeNode("type")).setSpecified(false);
                    }
                    else if (string10.equals("ENUMERATION")) {
                        element6.setAttribute("type", "NMTOKEN");
                        final Element element7 = this.fDocument.createElement("enumeration");
                        element6.appendChild(element7);
                        final StringTokenizer stringTokenizer2 = new StringTokenizer(s.substring(1, s.length() - 1), "|");
                        while (stringTokenizer2.hasMoreTokens()) {
                            final Element element8 = this.fDocument.createElement("literal");
                            element8.appendChild(this.fDocument.createTextNode(stringTokenizer2.nextToken()));
                            element7.appendChild(element8);
                        }
                    }
                    else {
                        element6.setAttribute("type", string10);
                    }
                    boolean b2 = false;
                    if (n4 != -1) {
                        final String string11 = super.fStringPool.toString(n4);
                        if (string11.equals("#REQUIRED")) {
                            element6.setAttribute("minOccurs", "1");
                            ((AttrImpl)element6.getAttributeNode("minOccurs")).setSpecified(true);
                        }
                        else if (string11.equals("#FIXED")) {
                            b2 = true;
                            element6.setAttribute("fixed", super.fStringPool.toString(n5));
                        }
                    }
                    if (!b2 && n5 != -1) {
                        element6.setAttribute("default", super.fStringPool.toString(n5));
                    }
                }
            }
        }
    }
    
    public void internalPEDecl(final int n, final int n2) throws Exception {
    }
    
    public void externalPEDecl(final int n, final int n2, final int n3) throws Exception {
    }
    
    public void internalEntityDecl(final int n, final int n2) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            if (this.fDocumentTypeIndex == -1) {
                return;
            }
            this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createEntity(n, -1, -1, -1));
            if (this.fGrammarAccess) {
                final int firstChildElement = this.getFirstChildElement(this.fDocumentTypeIndex, "schema");
                final String string = super.fStringPool.toString(n);
                if (this.getFirstChildElement(firstChildElement, "textEntity", "name", string) == -1) {
                    final int startAttrList = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("export"), super.fStringPool.addString("true"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                    this.fAttrList.endAttrList();
                    final int element = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("textEntity"), this.fAttrList, startAttrList);
                    this.fDeferredDocumentImpl.appendChild(firstChildElement, element);
                    this.fDeferredDocumentImpl.appendChild(element, this.fDeferredDocumentImpl.createTextNode(n2, false));
                }
            }
        }
        else if (this.fDocumentImpl != null) {
            if (this.fDocumentType == null) {
                return;
            }
            final String string2 = super.fStringPool.toString(n);
            this.fDocumentType.getEntities().setNamedItem(this.fDocumentImpl.createEntity(string2));
            if (this.fGrammarAccess) {
                final Element firstChildElement2 = XUtil.getFirstChildElement(this.fDocumentType, "schema");
                if (XUtil.getFirstChildElement(firstChildElement2, "textEntity", "name", string2) == null) {
                    final Element element2 = this.fDocument.createElement("textEntity");
                    element2.setAttribute("name", string2);
                    element2.setAttribute("export", "true");
                    ((AttrImpl)element2.getAttributeNode("export")).setSpecified(false);
                    element2.appendChild(this.fDocument.createTextNode(super.fStringPool.toString(n2)));
                    firstChildElement2.appendChild(element2);
                }
            }
        }
    }
    
    public void externalEntityDecl(final int n, final int n2, final int n3) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createEntity(n, n2, n3, -1));
            if (this.fGrammarAccess) {
                final int firstChildElement = this.getFirstChildElement(this.fDocumentTypeIndex, "schema");
                final String string = super.fStringPool.toString(n);
                if (this.getFirstChildElement(firstChildElement, "externalEntity", "name", string) == -1) {
                    final int startAttrList = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("export"), super.fStringPool.addString("true"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                    if (n2 != -1) {
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("public"), n2, super.fStringPool.addSymbol("CDATA"), true, false);
                    }
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("system"), n3, super.fStringPool.addSymbol("CDATA"), true, false);
                    this.fAttrList.endAttrList();
                    this.fDeferredDocumentImpl.appendChild(firstChildElement, this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("externalEntity"), this.fAttrList, startAttrList));
                }
            }
        }
        else if (this.fDocumentImpl != null) {
            final String string2 = super.fStringPool.toString(n);
            final String string3 = super.fStringPool.toString(n2);
            final String string4 = super.fStringPool.toString(n3);
            final EntityImpl namedItem = (EntityImpl)this.fDocumentImpl.createEntity(string2);
            if (n2 != -1) {
                namedItem.setPublicId(string3);
            }
            namedItem.setSystemId(string4);
            this.fDocumentType.getEntities().setNamedItem(namedItem);
            if (this.fGrammarAccess) {
                final Element firstChildElement2 = XUtil.getFirstChildElement(this.fDocumentType, "schema");
                if (XUtil.getFirstChildElement(firstChildElement2, "externalEntity", "name", string2) == null) {
                    final Element element = this.fDocument.createElement("externalEntity");
                    element.setAttribute("name", string2);
                    element.setAttribute("export", "true");
                    ((AttrImpl)element.getAttributeNode("export")).setSpecified(false);
                    if (n2 != -1) {
                        element.setAttribute("public", string3);
                    }
                    element.setAttribute("system", string4);
                    firstChildElement2.appendChild(element);
                }
            }
        }
    }
    
    public void unparsedEntityDecl(final int n, final int n2, final int n3, final int n4) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createEntity(n, n2, n3, n4));
            if (this.fGrammarAccess) {
                final int firstChildElement = this.getFirstChildElement(this.fDocumentTypeIndex, "schema");
                final String string = super.fStringPool.toString(n);
                if (this.getFirstChildElement(firstChildElement, "unparsedEntity", "name", string) == -1) {
                    final int startAttrList = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("export"), super.fStringPool.addString("true"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                    if (n2 != -1) {
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("public"), n2, super.fStringPool.addSymbol("CDATA"), true, false);
                    }
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("system"), n3, super.fStringPool.addSymbol("CDATA"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("notation"), super.fStringPool.addString(super.fStringPool.toString(n4)), super.fStringPool.addSymbol("CDATA"), true, false);
                    this.fAttrList.endAttrList();
                    this.fDeferredDocumentImpl.appendChild(firstChildElement, this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("unparsedEntity"), this.fAttrList, startAttrList));
                }
            }
        }
        else if (this.fDocumentImpl != null) {
            final String string2 = super.fStringPool.toString(n);
            final String string3 = super.fStringPool.toString(n2);
            final String string4 = super.fStringPool.toString(n3);
            final String string5 = super.fStringPool.toString(n4);
            final EntityImpl namedItem = (EntityImpl)this.fDocumentImpl.createEntity(string2);
            if (n2 != -1) {
                namedItem.setPublicId(string3);
            }
            namedItem.setSystemId(string4);
            namedItem.setNotationName(string5);
            this.fDocumentType.getEntities().setNamedItem(namedItem);
            if (this.fGrammarAccess) {
                final Element firstChildElement2 = XUtil.getFirstChildElement(this.fDocumentType, "schema");
                if (XUtil.getFirstChildElement(firstChildElement2, "unparsedEntity", "name", string2) == null) {
                    final Element element = this.fDocument.createElement("unparsedEntity");
                    element.setAttribute("name", string2);
                    element.setAttribute("export", "true");
                    ((AttrImpl)element.getAttributeNode("export")).setSpecified(false);
                    if (n2 != -1) {
                        element.setAttribute("public", string3);
                    }
                    element.setAttribute("system", string4);
                    element.setAttribute("notation", string5);
                    firstChildElement2.appendChild(element);
                }
            }
        }
    }
    
    public void notationDecl(final int n, int n2, final int n3) throws Exception {
        if (this.fDeferredDocumentImpl != null) {
            this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createNotation(n, n2, n3));
            if (this.fGrammarAccess) {
                final int firstChildElement = this.getFirstChildElement(this.fDocumentTypeIndex, "schema");
                final String string = super.fStringPool.toString(n);
                if (this.getFirstChildElement(firstChildElement, "notation", "name", string) == -1) {
                    final int startAttrList = this.fAttrList.startAttrList();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("name"), super.fStringPool.addString(string), super.fStringPool.addSymbol("NMTOKEN"), true, false);
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("export"), super.fStringPool.addString("true"), super.fStringPool.addSymbol("ENUMERATION"), false, false);
                    if (n2 == -1) {
                        n2 = 0;
                    }
                    this.fAttrList.addAttr(super.fStringPool.addSymbol("public"), n2, super.fStringPool.addSymbol("CDATA"), true, false);
                    if (n3 != -1) {
                        this.fAttrList.addAttr(super.fStringPool.addSymbol("system"), n3, super.fStringPool.addSymbol("CDATA"), true, false);
                    }
                    this.fAttrList.endAttrList();
                    this.fDeferredDocumentImpl.appendChild(firstChildElement, this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol("notation"), this.fAttrList, startAttrList));
                }
            }
        }
        else if (this.fDocumentImpl != null) {
            final String string2 = super.fStringPool.toString(n);
            String string3 = super.fStringPool.toString(n2);
            final String string4 = super.fStringPool.toString(n3);
            final NotationImpl namedItem = (NotationImpl)this.fDocumentImpl.createNotation(string2);
            namedItem.setPublicId(string3);
            if (n3 != -1) {
                namedItem.setSystemId(string4);
            }
            this.fDocumentType.getNotations().setNamedItem(namedItem);
            if (this.fGrammarAccess) {
                final Element firstChildElement2 = XUtil.getFirstChildElement(this.fDocumentType, "schema");
                if (XUtil.getFirstChildElement(firstChildElement2, "notation", "name", string2) == null) {
                    final Element element = this.fDocument.createElement("notation");
                    element.setAttribute("name", string2);
                    element.setAttribute("export", "true");
                    ((AttrImpl)element.getAttributeNode("export")).setSpecified(false);
                    if (string3 == null) {
                        string3 = "";
                    }
                    element.setAttribute("public", string3);
                    if (n3 != -1) {
                        element.setAttribute("system", string4);
                    }
                    firstChildElement2.appendChild(element);
                }
            }
        }
    }
    
    private Element createContentModel(final ContentSpec contentSpec, final XMLContentSpecNode xmlContentSpecNode) {
        return this.createContentModel(contentSpec, xmlContentSpecNode, null);
    }
    
    private Element createContentModel(final ContentSpec contentSpec, final XMLContentSpecNode xmlContentSpecNode, final Element element) {
        int n = 1;
        int n2 = 1;
        switch (xmlContentSpecNode.type) {
            case 3: {
                n = 1;
                n2 = -1;
                contentSpec.getNode(xmlContentSpecNode.value, xmlContentSpecNode);
                break;
            }
            case 2: {
                n = 0;
                n2 = -1;
                contentSpec.getNode(xmlContentSpecNode.value, xmlContentSpecNode);
                break;
            }
            case 1: {
                n = 0;
                n2 = 1;
                contentSpec.getNode(xmlContentSpecNode.value, xmlContentSpecNode);
                break;
            }
        }
        final int type = xmlContentSpecNode.type;
        switch (type) {
            case 4:
            case 5: {
                final int value = xmlContentSpecNode.value;
                final int otherValue = xmlContentSpecNode.otherValue;
                contentSpec.getNode(value, xmlContentSpecNode);
                final Element contentModel = this.createContentModel(contentSpec, xmlContentSpecNode, element);
                contentSpec.getNode(otherValue, xmlContentSpecNode);
                final Element contentModel2 = this.createContentModel(contentSpec, xmlContentSpecNode, null);
                final String s = (type == 4) ? "choice" : "seq";
                Element element2 = contentModel;
                if (!contentModel.getAttribute("order").equals(s)) {
                    final String attribute = contentModel.getAttribute("minOccurs");
                    final String attribute2 = contentModel.getAttribute("maxOccurs");
                    if (element == null || ((attribute.equals("1") || attribute.length() == 0) && (attribute2.equals("1") || attribute2.length() == 0))) {
                        element2 = this.fDocument.createElement("group");
                        element2.setAttribute("collection", "no");
                        ((AttrImpl)element2.getAttributeNode("collection")).setSpecified(false);
                        element2.setAttribute("order", s);
                        if (s.equals("seq")) {
                            ((AttrImpl)element2.getAttributeNode("order")).setSpecified(false);
                        }
                        element2.appendChild(contentModel);
                    }
                    else {
                        element2 = element;
                    }
                }
                this.setOccurrenceCount(element2, n, n2);
                element2.appendChild(contentModel2);
                return element2;
            }
            case 0: {
                final String string = super.fStringPool.toString(xmlContentSpecNode.value);
                final Element element3 = this.fDocument.createElement("element");
                element3.setAttribute("ref", string);
                this.setOccurrenceCount(element3, n, n2);
                return element3;
            }
            default: {
                return null;
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
            return;
        }
        if (n2 != 1) {
            element.setAttribute("maxOccurs", Integer.toString(n2));
        }
    }
    
    private int getFirstChildElement(final int n) {
        for (int i = this.fDeferredDocumentImpl.getFirstChild(n); i != -1; i = this.fDeferredDocumentImpl.getNextSibling(i)) {
            if (this.fDeferredDocumentImpl.getNodeType(i) == 1) {
                return i;
            }
        }
        return -1;
    }
    
    private int getNextSiblingElement(final int n) {
        for (int i = this.fDeferredDocumentImpl.getNextSibling(n); i != -1; i = this.fDeferredDocumentImpl.getNextSibling(i)) {
            if (this.fDeferredDocumentImpl.getNodeType(i) == 1) {
                return i;
            }
        }
        return -1;
    }
    
    private int getFirstChildElement(final int n, final String s) {
        int i = this.getFirstChildElement(n);
        if (i != -1) {
            while (i != -1) {
                this.fDeferredDocumentImpl.getNodeNameString(i);
                if (this.fDeferredDocumentImpl.getNodeNameString(i).equals(s)) {
                    return i;
                }
                i = this.getNextSiblingElement(i);
            }
        }
        return -1;
    }
    
    private int getNextSiblingElement(final int n, final String s) {
        int i = this.getNextSiblingElement(n);
        if (i != -1) {
            while (i != -1) {
                if (this.fDeferredDocumentImpl.getNodeNameString(i).equals(s)) {
                    return i;
                }
                i = this.getNextSiblingElement(i);
            }
        }
        return -1;
    }
    
    private int getFirstChildElement(final int n, final String s, final String s2, final String s3) {
        int i = this.getFirstChildElement(n, s);
        if (i != -1) {
            while (i != -1) {
                for (int j = this.fDeferredDocumentImpl.getNodeValue(i); j != -1; j = this.fDeferredDocumentImpl.getNextSibling(j)) {
                    if (this.fDeferredDocumentImpl.getNodeNameString(j).equals(s2) && this.fDeferredDocumentImpl.getNodeValueString(this.fDeferredDocumentImpl.getFirstChild(j)).equals(s3)) {
                        return i;
                    }
                }
                i = this.getNextSiblingElement(i, s);
            }
        }
        return -1;
    }
    
    private int getNextSiblingElement(final int n, final String s, final String s2, final String s3) {
        int i = this.getNextSiblingElement(n, s);
        if (i != -1) {
            super.fStringPool.addSymbol(s2);
            while (i != -1) {
                for (int j = this.fDeferredDocumentImpl.getNodeValue(i); j != -1; j = this.fDeferredDocumentImpl.getNextSibling(j)) {
                    if (s3.equals(super.fStringPool.toString(this.fDeferredDocumentImpl.getNodeValue(j)))) {
                        return i;
                    }
                }
                i = this.getNextSiblingElement(i, s);
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
                    throw new IllegalArgumentException("can't copy node type, " + nodeType + " (" + node2.getNodeName() + ')');
                }
            }
            this.fDeferredDocumentImpl.appendChild(parentNode, n);
            if (node2.hasChildNodes()) {
                parentNode2 = node2;
            }
            else {
                for (node2 = node2.getNextSibling(); node2 == null && parentNode2 != node; node2 = parentNode2.getNextSibling(), parentNode2 = parentNode2.getParentNode(), parentNode = this.fDeferredDocumentImpl.getParentNode(parentNode)) {}
            }
        }
    }
    
    private int createDeferredContentModel(final Node node) throws Exception {
        switch (node.getNodeType()) {
            case 1: {
                final NamedNodeMap attributes = node.getAttributes();
                final int startAttrList = this.fAttrList.startAttrList();
                for (int length = attributes.getLength(), i = 0; i < length; ++i) {
                    final Attr attr = (Attr)attributes.item(i);
                    final String nodeName = attr.getNodeName();
                    this.fAttrList.addAttr(super.fStringPool.addSymbol(nodeName), super.fStringPool.addString(attr.getNodeValue()), super.fStringPool.addSymbol((String)DOMParser.TYPES.get(nodeName)), attr.getSpecified(), false);
                }
                this.fAttrList.endAttrList();
                final int element = this.fDeferredDocumentImpl.createElement(super.fStringPool.addSymbol(node.getNodeName()), this.fAttrList, startAttrList);
                for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                    this.fDeferredDocumentImpl.appendChild(element, this.createDeferredContentModel(node2));
                }
                return element;
            }
            case 3: {
                return this.fDeferredDocumentImpl.createTextNode(super.fStringPool.addString(node.getNodeValue()), false);
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
        RECOGNIZED_FEATURES = new String[] { "http://apache.org/xml/features/dom/defer-node-expansion", "http://apache.org/xml/features/dom/create-entity-ref-nodes", "http://apache.org/xml/features/domx/grammar-access" };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/dom/document-class-name", "http://apache.org/xml/properties/dom/current-element-node" };
        TYPES = new Hashtable();
        final String[][] array = { { "CDATA", "minOccurs", "maxOccurs" }, { "ENUMERATION", "collection", "order", "export" }, { "NMTOKEN", "name", "ref" } };
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i][0];
            for (int j = 1; j < array[i].length; ++j) {
                DOMParser.TYPES.put(array[i][j], s);
            }
        }
    }
}
