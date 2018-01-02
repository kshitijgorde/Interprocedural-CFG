// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.dom.ElementDefinitionImpl;
import org.apache.xerces.dom.NotationImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xerces.dom.ProcessingInstructionImpl;
import org.apache.xerces.dom.NodeImpl;
import org.w3c.dom.NodeList;
import org.apache.xerces.dom.PSVIElementNSImpl;
import org.apache.xerces.xni.psvi.ElementPSVI;
import org.w3c.dom.Text;
import org.apache.xerces.dom.TextImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.dom.ElementImpl;
import org.apache.xerces.dom.AttrImpl;
import org.apache.xerces.dom.PSVIAttrNSImpl;
import org.apache.xerces.xni.psvi.AttributePSVI;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Comment;
import org.apache.xerces.xni.XMLString;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.EntityReference;
import org.apache.xerces.dom.EntityReferenceImpl;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLResourceIdentifier;
import java.util.Locale;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.util.ObjectFactory;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.w3c.dom.ls.DOMBuilderFilter;
import org.apache.xerces.xni.QName;
import java.util.Stack;
import org.apache.xerces.dom.DeferredDocumentImpl;
import org.apache.xerces.dom.EntityImpl;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentType;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.Document;

public class AbstractDOMParser extends AbstractXMLDocumentParser
{
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String CREATE_ENTITY_REF_NODES = "http://apache.org/xml/features/dom/create-entity-ref-nodes";
    protected static final String INCLUDE_COMMENTS_FEATURE = "http://apache.org/xml/features/include-comments";
    protected static final String CREATE_CDATA_NODES_FEATURE = "http://apache.org/xml/features/create-cdata-nodes";
    protected static final String INCLUDE_IGNORABLE_WHITESPACE = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
    protected static final String DEFER_NODE_EXPANSION = "http://apache.org/xml/features/dom/defer-node-expansion";
    private static final String[] RECOGNIZED_FEATURES;
    protected static final String DOCUMENT_CLASS_NAME = "http://apache.org/xml/properties/dom/document-class-name";
    protected static final String CURRENT_ELEMENT_NODE = "http://apache.org/xml/properties/dom/current-element-node";
    private static final String[] RECOGNIZED_PROPERTIES;
    protected static final String DEFAULT_DOCUMENT_CLASS_NAME = "org.apache.xerces.dom.DocumentImpl";
    protected static final String CORE_DOCUMENT_CLASS_NAME = "org.apache.xerces.dom.CoreDocumentImpl";
    protected static final String PSVI_DOCUMENT_CLASS_NAME = "org.apache.xerces.dom.PSVIDocumentImpl";
    private static final boolean DEBUG_EVENTS = false;
    private static final boolean DEBUG_BASEURI = false;
    protected boolean fInDTD;
    protected boolean fCreateEntityRefNodes;
    protected boolean fIncludeIgnorableWhitespace;
    protected boolean fIncludeComments;
    protected boolean fCreateCDATANodes;
    protected Document fDocument;
    protected CoreDocumentImpl fDocumentImpl;
    protected boolean fStorePSVI;
    protected String fDocumentClassName;
    protected DocumentType fDocumentType;
    protected Node fCurrentNode;
    protected CDATASection fCurrentCDATASection;
    protected EntityImpl fCurrentEntityDecl;
    protected int fDeferredEntityDecl;
    protected final StringBuffer fStringBuffer;
    protected StringBuffer fInternalSubset;
    protected boolean fDeferNodeExpansion;
    protected boolean fNamespaceAware;
    protected DeferredDocumentImpl fDeferredDocumentImpl;
    protected int fDocumentIndex;
    protected int fDocumentTypeIndex;
    protected int fCurrentNodeIndex;
    protected int fCurrentCDATASectionIndex;
    protected boolean fInDTDExternalSubset;
    protected boolean fInDocument;
    protected boolean fInCDATASection;
    protected boolean fFirstChunk;
    protected boolean fFilterReject;
    protected Stack fBaseURIStack;
    protected final QName fRejectedElement;
    protected Stack fSkippedElemStack;
    private QName fAttrQName;
    protected DOMBuilderFilter fDOMFilter;
    static /* synthetic */ Class class$org$w3c$dom$Document;
    
    protected AbstractDOMParser(final XMLParserConfiguration config) {
        super(config);
        this.fStringBuffer = new StringBuffer(50);
        this.fFirstChunk = false;
        this.fFilterReject = false;
        this.fBaseURIStack = new Stack();
        this.fRejectedElement = new QName();
        this.fSkippedElemStack = null;
        this.fAttrQName = new QName();
        this.fDOMFilter = null;
        super.fConfiguration.addRecognizedFeatures(AbstractDOMParser.RECOGNIZED_FEATURES);
        super.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", true);
        super.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", true);
        super.fConfiguration.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", true);
        super.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", true);
        super.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", true);
        super.fConfiguration.addRecognizedProperties(AbstractDOMParser.RECOGNIZED_PROPERTIES);
        super.fConfiguration.setProperty("http://apache.org/xml/properties/dom/document-class-name", "org.apache.xerces.dom.DocumentImpl");
    }
    
    protected String getDocumentClassName() {
        return this.fDocumentClassName;
    }
    
    protected void setDocumentClassName(String documentClassName) {
        if (documentClassName == null) {
            documentClassName = "org.apache.xerces.dom.DocumentImpl";
        }
        try {
            final Class _class = ObjectFactory.findProviderClass(documentClassName, ObjectFactory.findClassLoader(), true);
            if (!((AbstractDOMParser.class$org$w3c$dom$Document == null) ? (AbstractDOMParser.class$org$w3c$dom$Document = class$("org.w3c.dom.Document")) : AbstractDOMParser.class$org$w3c$dom$Document).isAssignableFrom(_class)) {
                throw new IllegalArgumentException("PAR002 Class, \"" + documentClassName + "\", is not of type org.w3c.dom.Document.\n" + documentClassName);
            }
        }
        catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("PAR003 Class, \"" + documentClassName + "\", not found.\n" + documentClassName);
        }
        this.fDocumentClassName = documentClassName;
        if (!documentClassName.equals("org.apache.xerces.dom.DocumentImpl")) {
            this.fDeferNodeExpansion = false;
        }
    }
    
    public Document getDocument() {
        return this.fDocument;
    }
    
    public void reset() throws XNIException {
        super.reset();
        this.fCreateEntityRefNodes = super.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes");
        this.fIncludeIgnorableWhitespace = super.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace");
        this.fDeferNodeExpansion = super.fConfiguration.getFeature("http://apache.org/xml/features/dom/defer-node-expansion");
        this.fNamespaceAware = super.fConfiguration.getFeature("http://xml.org/sax/features/namespaces");
        this.fIncludeComments = super.fConfiguration.getFeature("http://apache.org/xml/features/include-comments");
        this.fCreateCDATANodes = super.fConfiguration.getFeature("http://apache.org/xml/features/create-cdata-nodes");
        this.setDocumentClassName((String)super.fConfiguration.getProperty("http://apache.org/xml/properties/dom/document-class-name"));
        this.fDocument = null;
        this.fDocumentImpl = null;
        this.fStorePSVI = false;
        this.fDocumentType = null;
        this.fDocumentTypeIndex = -1;
        this.fDeferredDocumentImpl = null;
        this.fCurrentNode = null;
        this.fStringBuffer.setLength(0);
        this.fInDocument = false;
        this.fInDTD = false;
        this.fInDTDExternalSubset = false;
        this.fInCDATASection = false;
        this.fFirstChunk = false;
        this.fCurrentCDATASection = null;
        this.fCurrentCDATASectionIndex = -1;
        this.fBaseURIStack.removeAllElements();
    }
    
    public void setLocale(final Locale locale) {
        super.fConfiguration.setLocale(locale);
    }
    
    public void startGeneralEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
        if (!this.fDeferNodeExpansion) {
            if (this.fFilterReject) {
                return;
            }
            this.setCharacterData(true);
            final EntityReference er = this.fDocument.createEntityReference(name);
            if (this.fDocumentImpl != null) {
                final EntityReferenceImpl erImpl = (EntityReferenceImpl)er;
                erImpl.setBaseURI(identifier.getExpandedSystemId());
                if (this.fDocumentType != null) {
                    final NamedNodeMap entities = this.fDocumentType.getEntities();
                    this.fCurrentEntityDecl = (EntityImpl)entities.getNamedItem(name);
                    if (this.fCurrentEntityDecl != null) {
                        this.fCurrentEntityDecl.setActualEncoding(encoding);
                    }
                }
                erImpl.needsSyncChildren(false);
            }
            this.fCurrentNode.appendChild(er);
            this.fCurrentNode = er;
        }
        else {
            final int er2 = this.fDeferredDocumentImpl.createDeferredEntityReference(name, identifier.getExpandedSystemId());
            if (this.fDocumentTypeIndex != -1) {
                for (int node = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, false); node != -1; node = this.fDeferredDocumentImpl.getRealPrevSibling(node, false)) {
                    final short nodeType = this.fDeferredDocumentImpl.getNodeType(node, false);
                    if (nodeType == 6) {
                        final String nodeName = this.fDeferredDocumentImpl.getNodeName(node, false);
                        if (nodeName.equals(name)) {
                            this.fDeferredEntityDecl = node;
                            this.fDeferredDocumentImpl.setActualEncoding(node, encoding);
                            break;
                        }
                    }
                }
            }
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, er2);
            this.fCurrentNodeIndex = er2;
        }
    }
    
    public void textDecl(final String version, final String encoding, final Augmentations augs) throws XNIException {
        if (!this.fDeferNodeExpansion) {
            if (this.fCurrentEntityDecl != null && !this.fFilterReject) {
                this.fCurrentEntityDecl.setEncoding(encoding);
                this.fCurrentEntityDecl.setVersion(version);
            }
        }
        else if (this.fDeferredEntityDecl != -1) {
            this.fDeferredDocumentImpl.setEntityInfo(this.fDeferredEntityDecl, version, encoding);
        }
    }
    
    public void comment(final XMLString text, final Augmentations augs) throws XNIException {
        if (this.fInDTD) {
            if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
                this.fInternalSubset.append("<!-- ");
                this.fInternalSubset.append(text.toString());
                this.fInternalSubset.append(" -->");
            }
            return;
        }
        if (!this.fIncludeComments || this.fFilterReject) {
            return;
        }
        if (!this.fDeferNodeExpansion) {
            final Comment comment = this.fDocument.createComment(text.toString());
            this.setCharacterData(false);
            this.fCurrentNode.appendChild(comment);
            if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x80) != 0x0) {
                final short code = this.fDOMFilter.acceptNode((Node)comment);
                switch (code) {
                    case 4: {
                        throw new RuntimeException("The normal processing of the document was interrupted.");
                    }
                    case 2:
                    case 3: {
                        this.fCurrentNode.removeChild(comment);
                        this.fFirstChunk = true;
                    }
                }
            }
        }
        else {
            final int comment2 = this.fDeferredDocumentImpl.createDeferredComment(text.toString());
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, comment2);
        }
    }
    
    public void processingInstruction(final String target, final XMLString data, final Augmentations augs) throws XNIException {
        if (this.fInDTD) {
            if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
                this.fInternalSubset.append("<?");
                this.fInternalSubset.append(target.toString());
                this.fInternalSubset.append(' ');
                this.fInternalSubset.append(data.toString());
                this.fInternalSubset.append("?>");
            }
            return;
        }
        if (!this.fDeferNodeExpansion) {
            if (this.fFilterReject) {
                return;
            }
            final ProcessingInstruction pi = this.fDocument.createProcessingInstruction(target, data.toString());
            this.setCharacterData(false);
            this.fCurrentNode.appendChild(pi);
            if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x40) != 0x0) {
                final short code = this.fDOMFilter.acceptNode((Node)pi);
                switch (code) {
                    case 4: {
                        throw new RuntimeException("The normal processing of the document was interrupted.");
                    }
                    case 2:
                    case 3: {
                        this.fCurrentNode.removeChild(pi);
                        this.fFirstChunk = true;
                    }
                }
            }
        }
        else {
            final int pi2 = this.fDeferredDocumentImpl.createDeferredProcessingInstruction(target, data.toString());
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, pi2);
        }
    }
    
    public void startDocument(final XMLLocator locator, final String encoding, final NamespaceContext namespaceContext, final Augmentations augs) throws XNIException {
        this.fInDocument = true;
        if (!this.fDeferNodeExpansion) {
            if (this.fDocumentClassName.equals("org.apache.xerces.dom.DocumentImpl")) {
                this.fDocument = new DocumentImpl();
                (this.fDocumentImpl = (CoreDocumentImpl)this.fDocument).setStrictErrorChecking(false);
                this.fDocumentImpl.setActualEncoding(encoding);
                this.fDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
            }
            else {
                try {
                    final Class documentClass = ObjectFactory.findProviderClass(this.fDocumentClassName, ObjectFactory.findClassLoader(), true);
                    this.fDocument = documentClass.newInstance();
                    final Class defaultDocClass = ObjectFactory.findProviderClass("org.apache.xerces.dom.CoreDocumentImpl", ObjectFactory.findClassLoader(), true);
                    if (defaultDocClass.isAssignableFrom(documentClass)) {
                        this.fDocumentImpl = (CoreDocumentImpl)this.fDocument;
                        final Class psviDocClass = ObjectFactory.findProviderClass("org.apache.xerces.dom.PSVIDocumentImpl", ObjectFactory.findClassLoader(), true);
                        if (psviDocClass.isAssignableFrom(documentClass)) {
                            this.fStorePSVI = true;
                        }
                        this.fDocumentImpl.setStrictErrorChecking(false);
                        this.fDocumentImpl.setActualEncoding(encoding);
                        if (locator != null) {
                            this.fDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
                        }
                    }
                }
                catch (ClassNotFoundException e) {}
                catch (Exception e2) {
                    throw new RuntimeException("Failed to create document object of class: " + this.fDocumentClassName);
                }
            }
            this.fCurrentNode = this.fDocument;
        }
        else {
            this.fDeferredDocumentImpl = new DeferredDocumentImpl(this.fNamespaceAware);
            this.fDocument = this.fDeferredDocumentImpl;
            this.fDocumentIndex = this.fDeferredDocumentImpl.createDeferredDocument();
            this.fDeferredDocumentImpl.setActualEncoding(encoding);
            this.fDeferredDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
            this.fCurrentNodeIndex = this.fDocumentIndex;
        }
    }
    
    public void xmlDecl(final String version, final String encoding, final String standalone, final Augmentations augs) throws XNIException {
        if (!this.fDeferNodeExpansion) {
            if (this.fDocumentImpl != null) {
                this.fDocumentImpl.setVersion(version);
                this.fDocumentImpl.setEncoding(encoding);
                this.fDocumentImpl.setStandalone("true".equals(standalone));
            }
        }
        else {
            this.fDeferredDocumentImpl.setVersion(version);
            this.fDeferredDocumentImpl.setEncoding(encoding);
            this.fDeferredDocumentImpl.setStandalone("true".equals(standalone));
        }
    }
    
    public void doctypeDecl(final String rootElement, final String publicId, final String systemId, final Augmentations augs) throws XNIException {
        if (!this.fDeferNodeExpansion) {
            if (this.fDocumentImpl != null) {
                this.fDocumentType = this.fDocumentImpl.createDocumentType(rootElement, publicId, systemId);
                this.fCurrentNode.appendChild(this.fDocumentType);
            }
        }
        else {
            this.fDocumentTypeIndex = this.fDeferredDocumentImpl.createDeferredDocumentType(rootElement, publicId, systemId);
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDocumentTypeIndex);
        }
    }
    
    public void startPrefixMapping(final String prefix, final String uri, final Augmentations augs) throws XNIException {
    }
    
    public void endPrefixMapping(final String prefix, final Augmentations augs) throws XNIException {
    }
    
    public void startElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        if (!this.fDeferNodeExpansion) {
            if (this.fFilterReject) {
                return;
            }
            final Element el = this.createElementNode(element);
            for (int attrCount = attributes.getLength(), i = 0; i < attrCount; ++i) {
                attributes.getName(i, this.fAttrQName);
                final Attr attr = this.createAttrNode(this.fAttrQName);
                final String attrValue = attributes.getValue(i);
                if (this.fStorePSVI) {
                    final AttributePSVI attrPSVI = (AttributePSVI)attributes.getAugmentations(i).getItem("ATTRIBUTE_PSVI");
                    if (attrPSVI != null) {
                        ((PSVIAttrNSImpl)attr).setPSVI(attrPSVI);
                    }
                }
                attr.setValue(attrValue);
                el.setAttributeNode(attr);
                if (this.fDocumentImpl != null) {
                    final AttrImpl attrImpl = (AttrImpl)attr;
                    final boolean specified = attributes.isSpecified(i);
                    attrImpl.setSpecified(specified);
                    if (attributes.getType(i).equals("ID")) {
                        ((ElementImpl)el).setIdAttributeNode(attr);
                    }
                    else if (attributes instanceof XMLAttributesImpl) {
                        final XMLAttributesImpl attrs = (XMLAttributesImpl)attributes;
                        if (attrs.getSchemaId(i)) {
                            ((ElementImpl)el).setIdAttributeNode(attr);
                        }
                    }
                }
            }
            this.setCharacterData(false);
            if (this.fDOMFilter != null) {
                final short code = this.fDOMFilter.startElement(el);
                switch (code) {
                    case 4: {
                        throw new RuntimeException("The normal processing of the document was interrupted.");
                    }
                    case 2: {
                        this.fFilterReject = true;
                        this.fRejectedElement.setValues(element);
                        return;
                    }
                    case 3: {
                        this.fSkippedElemStack.push(element);
                        return;
                    }
                }
            }
            this.fCurrentNode.appendChild(el);
            this.fCurrentNode = el;
        }
        else {
            final int el2 = this.fDeferredDocumentImpl.createDeferredElement(this.fNamespaceAware ? element.uri : null, element.rawname);
            for (int attrCount = attributes.getLength(), i = 0; i < attrCount; ++i) {
                final String attrValue2 = attributes.getValue(i);
                final int attr2 = this.fDeferredDocumentImpl.setDeferredAttribute(el2, attributes.getQName(i), attributes.getURI(i), attrValue2, attributes.isSpecified(i));
                if (attributes.getType(i).equals("ID")) {
                    this.fDeferredDocumentImpl.setIdAttributeNode(el2, attr2);
                }
                else if (attributes instanceof XMLAttributesImpl) {
                    final XMLAttributesImpl attrs2 = (XMLAttributesImpl)attributes;
                    if (attrs2.getSchemaId(i)) {
                        this.fDeferredDocumentImpl.setIdAttributeNode(el2, attr2);
                    }
                }
            }
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, el2);
            this.fCurrentNodeIndex = el2;
        }
    }
    
    public void emptyElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        this.startElement(element, attributes, augs);
        this.endElement(element, augs);
    }
    
    public void characters(final XMLString text, final Augmentations augs) throws XNIException {
        if (!this.fDeferNodeExpansion) {
            if (this.fFilterReject) {
                return;
            }
            if (this.fInCDATASection && this.fCreateCDATANodes) {
                if (this.fCurrentCDATASection == null) {
                    this.fCurrentCDATASection = this.fDocument.createCDATASection(text.toString());
                    this.fCurrentNode.appendChild(this.fCurrentCDATASection);
                    this.fCurrentNode = this.fCurrentCDATASection;
                }
                else {
                    this.fCurrentCDATASection.appendData(text.toString());
                }
            }
            else if (!this.fInDTD) {
                if (text.length == 0) {
                    return;
                }
                final String value = text.toString();
                final Node child = this.fCurrentNode.getLastChild();
                if (child != null && child.getNodeType() == 3) {
                    if (this.fFirstChunk) {
                        if (this.fDocumentImpl != null) {
                            this.fStringBuffer.append(((TextImpl)child).removeData());
                        }
                        else {
                            this.fStringBuffer.append(((Text)child).getData());
                            ((Text)child).setNodeValue(null);
                        }
                        this.fFirstChunk = false;
                    }
                    this.fStringBuffer.append(value);
                }
                else {
                    this.fFirstChunk = true;
                    final Text textNode = this.fDocument.createTextNode(value);
                    this.fCurrentNode.appendChild(textNode);
                }
            }
        }
        else if (this.fInCDATASection && this.fCreateCDATANodes) {
            if (this.fCurrentCDATASectionIndex == -1) {
                final int cs = this.fDeferredDocumentImpl.createDeferredCDATASection(text.toString());
                this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, cs);
                this.fCurrentCDATASectionIndex = cs;
                this.fCurrentNodeIndex = cs;
            }
            else {
                final int txt = this.fDeferredDocumentImpl.createDeferredTextNode(text.toString(), false);
                this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, txt);
            }
        }
        else if (!this.fInDTD) {
            if (text.length == 0) {
                return;
            }
            final String value = text.toString();
            final int txt2 = this.fDeferredDocumentImpl.createDeferredTextNode(value, false);
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, txt2);
        }
    }
    
    public void ignorableWhitespace(final XMLString text, final Augmentations augs) throws XNIException {
        if (!this.fIncludeIgnorableWhitespace || this.fFilterReject) {
            return;
        }
        if (!this.fDeferNodeExpansion) {
            final Node child = this.fCurrentNode.getLastChild();
            if (child != null && child.getNodeType() == 3) {
                final Text textNode = (Text)child;
                textNode.appendData(text.toString());
            }
            else {
                final Text textNode = this.fDocument.createTextNode(text.toString());
                if (this.fDocumentImpl != null) {
                    final TextImpl textNodeImpl = (TextImpl)textNode;
                    textNodeImpl.setIgnorableWhitespace(true);
                }
                this.fCurrentNode.appendChild(textNode);
            }
        }
        else {
            final int txt = this.fDeferredDocumentImpl.createDeferredTextNode(text.toString(), true);
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, txt);
        }
    }
    
    public void endElement(final QName element, final Augmentations augs) throws XNIException {
        if (!this.fDeferNodeExpansion) {
            if (this.fStorePSVI && augs != null) {
                final ElementPSVI elementPSVI = (ElementPSVI)augs.getItem("ELEMENT_PSVI");
                if (elementPSVI != null) {
                    ((PSVIElementNSImpl)this.fCurrentNode).setPSVI(elementPSVI);
                }
            }
            if (this.fDOMFilter != null) {
                if (this.fFilterReject) {
                    if (element.equals(this.fRejectedElement)) {
                        this.fFilterReject = false;
                    }
                    return;
                }
                if (!this.fSkippedElemStack.isEmpty() && this.fSkippedElemStack.peek().equals(element)) {
                    this.fSkippedElemStack.pop();
                    return;
                }
                this.setCharacterData(false);
                if ((this.fDOMFilter.getWhatToShow() & 0x1) != 0x0) {
                    final short code = this.fDOMFilter.acceptNode(this.fCurrentNode);
                    switch (code) {
                        case 4: {
                            throw new RuntimeException("The normal processing of the document was interrupted.");
                        }
                        case 2: {
                            final Node parent = this.fCurrentNode.getParentNode();
                            parent.removeChild(this.fCurrentNode);
                            this.fCurrentNode = parent;
                            return;
                        }
                        case 3: {
                            this.fFirstChunk = true;
                            final Node parent = this.fCurrentNode.getParentNode();
                            final NodeList ls = this.fCurrentNode.getChildNodes();
                            for (int length = ls.getLength(), i = 0; i < length; ++i) {
                                parent.appendChild(ls.item(0));
                            }
                            parent.removeChild(this.fCurrentNode);
                            this.fCurrentNode = parent;
                            return;
                        }
                    }
                }
                this.fCurrentNode = this.fCurrentNode.getParentNode();
            }
            else {
                this.setCharacterData(false);
                this.fCurrentNode = this.fCurrentNode.getParentNode();
            }
        }
        else {
            this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex, false);
        }
    }
    
    public void startCDATA(final Augmentations augs) throws XNIException {
        this.fInCDATASection = true;
        if (!this.fDeferNodeExpansion) {
            if (this.fFilterReject) {
                return;
            }
            this.setCharacterData(false);
        }
    }
    
    public void endCDATA(final Augmentations augs) throws XNIException {
        this.fInCDATASection = false;
        if (!this.fDeferNodeExpansion) {
            if (this.fFilterReject) {
                return;
            }
            if (this.fCurrentCDATASection != null) {
                if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x8) != 0x0) {
                    final short code = this.fDOMFilter.acceptNode((Node)this.fCurrentCDATASection);
                    switch (code) {
                        case 4: {
                            throw new RuntimeException("The normal processing of the document was interrupted.");
                        }
                        case 2:
                        case 3: {
                            final Node parent = this.fCurrentNode.getParentNode();
                            parent.removeChild(this.fCurrentCDATASection);
                            this.fCurrentNode = parent;
                            return;
                        }
                    }
                }
                this.fCurrentNode = this.fCurrentNode.getParentNode();
                this.fCurrentCDATASection = null;
            }
        }
        else if (this.fCurrentCDATASectionIndex != -1) {
            this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex, false);
            this.fCurrentCDATASectionIndex = -1;
        }
    }
    
    public void endDocument(final Augmentations augs) throws XNIException {
        this.fInDocument = false;
        if (!this.fDeferNodeExpansion) {
            if (this.fDocumentImpl != null) {
                this.fDocumentImpl.setStrictErrorChecking(true);
            }
            this.fCurrentNode = null;
        }
        else {
            this.fCurrentNodeIndex = -1;
        }
    }
    
    public void endGeneralEntity(final String name, final Augmentations augs) throws XNIException {
        if (!this.fDeferNodeExpansion) {
            if (this.fFilterReject) {
                return;
            }
            this.setCharacterData(true);
            if (this.fDocumentType != null) {
                final NamedNodeMap entities = this.fDocumentType.getEntities();
                this.fCurrentEntityDecl = (EntityImpl)entities.getNamedItem(name);
                if (this.fCurrentEntityDecl != null) {
                    if (this.fCurrentEntityDecl != null && this.fCurrentEntityDecl.getFirstChild() == null) {
                        this.fCurrentEntityDecl.setReadOnly(false, true);
                        for (Node child = this.fCurrentNode.getFirstChild(); child != null; child = child.getNextSibling()) {
                            final Node copy = child.cloneNode(true);
                            this.fCurrentEntityDecl.appendChild(copy);
                        }
                        this.fCurrentEntityDecl.setReadOnly(true, true);
                    }
                    this.fCurrentEntityDecl = null;
                }
            }
            boolean removeEntityRef = false;
            if (this.fCreateEntityRefNodes) {
                if (this.fDocumentImpl != null) {
                    ((NodeImpl)this.fCurrentNode).setReadOnly(true, true);
                }
                if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x10) != 0x0) {
                    final short code = this.fDOMFilter.acceptNode(this.fCurrentNode);
                    switch (code) {
                        case 4: {
                            throw new RuntimeException("The normal processing of the document was interrupted.");
                        }
                        case 2: {
                            final Node parent = this.fCurrentNode.getParentNode();
                            parent.removeChild(this.fCurrentNode);
                            this.fCurrentNode = parent;
                            return;
                        }
                        case 3: {
                            this.fFirstChunk = true;
                            removeEntityRef = true;
                            break;
                        }
                        default: {
                            this.fCurrentNode = this.fCurrentNode.getParentNode();
                            break;
                        }
                    }
                }
                else {
                    this.fCurrentNode = this.fCurrentNode.getParentNode();
                }
            }
            if (!this.fCreateEntityRefNodes || removeEntityRef) {
                final NodeList children = this.fCurrentNode.getChildNodes();
                final Node parent = this.fCurrentNode.getParentNode();
                final int length = children.getLength();
                if (length > 0) {
                    Node node = this.fCurrentNode.getPreviousSibling();
                    final Node child2 = children.item(0);
                    if (node != null && node.getNodeType() == 3 && child2.getNodeType() == 3) {
                        ((Text)node).appendData(child2.getNodeValue());
                        this.fCurrentNode.removeChild(child2);
                    }
                    else {
                        node = parent.insertBefore(child2, this.fCurrentNode);
                        this.handleBaseURI(node);
                    }
                    for (int i = 1; i < length; ++i) {
                        node = parent.insertBefore(children.item(0), this.fCurrentNode);
                        this.handleBaseURI(node);
                    }
                }
                parent.removeChild(this.fCurrentNode);
                this.fCurrentNode = parent;
            }
        }
        else {
            if (this.fDocumentTypeIndex != -1) {
                for (int node2 = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, false); node2 != -1; node2 = this.fDeferredDocumentImpl.getRealPrevSibling(node2, false)) {
                    final short nodeType = this.fDeferredDocumentImpl.getNodeType(node2, false);
                    if (nodeType == 6) {
                        final String nodeName = this.fDeferredDocumentImpl.getNodeName(node2, false);
                        if (nodeName.equals(name)) {
                            this.fDeferredEntityDecl = node2;
                            break;
                        }
                    }
                }
            }
            if (this.fDeferredEntityDecl != -1) {
                int prevIndex = -1;
                for (int childIndex = this.fDeferredDocumentImpl.getLastChild(this.fCurrentNodeIndex, false); childIndex != -1; childIndex = this.fDeferredDocumentImpl.getRealPrevSibling(childIndex, false)) {
                    final int cloneIndex = this.fDeferredDocumentImpl.cloneNode(childIndex, true);
                    this.fDeferredDocumentImpl.insertBefore(this.fDeferredEntityDecl, cloneIndex, prevIndex);
                    prevIndex = cloneIndex;
                }
            }
            if (this.fCreateEntityRefNodes) {
                this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex, false);
            }
            else {
                int childIndex2 = this.fDeferredDocumentImpl.getLastChild(this.fCurrentNodeIndex, false);
                final int parentIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex, false);
                int prevIndex2 = this.fCurrentNodeIndex;
                final int lastChild = childIndex2;
                for (int sibling = -1; childIndex2 != -1; childIndex2 = sibling) {
                    this.handleBaseURI(childIndex2);
                    sibling = this.fDeferredDocumentImpl.getRealPrevSibling(childIndex2, false);
                    this.fDeferredDocumentImpl.insertBefore(parentIndex, childIndex2, prevIndex2);
                    prevIndex2 = childIndex2;
                }
                this.fDeferredDocumentImpl.setAsLastChild(parentIndex, lastChild);
                this.fCurrentNodeIndex = parentIndex;
            }
            this.fDeferredEntityDecl = -1;
        }
    }
    
    protected void handleBaseURI(final Node node) {
        if (this.fDocumentImpl != null) {
            String baseURI = null;
            final short nodeType = node.getNodeType();
            if (nodeType == 1) {
                if (this.fNamespaceAware && ((Element)node).getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "base") != null) {
                    return;
                }
                if (((Element)node).getAttributeNode("xml:base") != null) {
                    return;
                }
                baseURI = ((EntityReferenceImpl)this.fCurrentNode).getBaseURI();
                if (baseURI != null && !baseURI.equals(this.fDocumentImpl.getDocumentURI())) {
                    if (this.fNamespaceAware) {
                        ((Element)node).setAttributeNS("http://www.w3.org/XML/1998/namespace", "base", baseURI);
                    }
                    else {
                        ((Element)node).setAttribute("xml:base", baseURI);
                    }
                }
            }
            else if (nodeType == 7) {
                baseURI = ((EntityReferenceImpl)this.fCurrentNode).getBaseURI();
                ((ProcessingInstructionImpl)node).setBaseURI(baseURI);
            }
        }
    }
    
    protected void handleBaseURI(final int node) {
        final short nodeType = this.fDeferredDocumentImpl.getNodeType(node, false);
        if (nodeType == 1) {
            String baseURI = this.fDeferredDocumentImpl.getNodeValueString(this.fCurrentNodeIndex, false);
            if (baseURI == null) {
                baseURI = this.fDeferredDocumentImpl.getDeferredEntityBaseURI(this.fDeferredEntityDecl);
            }
            if (baseURI != null && !baseURI.equals(this.fDeferredDocumentImpl.getDocumentURI())) {
                this.fDeferredDocumentImpl.setDeferredAttribute(node, "xml:base", "http://www.w3.org/XML/1998/namespace", baseURI, true);
            }
        }
        else if (nodeType == 7) {
            String baseURI = this.fDeferredDocumentImpl.getNodeValueString(this.fCurrentNodeIndex, false);
            if (baseURI == null) {
                baseURI = this.fDeferredDocumentImpl.getDeferredEntityBaseURI(this.fDeferredEntityDecl);
            }
            this.fDeferredDocumentImpl.setDeferredPIBaseURI(node, baseURI);
        }
    }
    
    public void startDTD(final XMLLocator locator, final Augmentations augs) throws XNIException {
        this.fInDTD = true;
        if (locator != null) {
            this.fBaseURIStack.push(locator.getBaseSystemId());
        }
        if (this.fDeferNodeExpansion || this.fDocumentImpl != null) {
            this.fInternalSubset = new StringBuffer(1024);
        }
    }
    
    public void endDTD(final Augmentations augs) throws XNIException {
        this.fInDTD = false;
        if (!this.fBaseURIStack.isEmpty()) {
            this.fBaseURIStack.pop();
        }
        final String internalSubset = (this.fInternalSubset != null && this.fInternalSubset.length() > 0) ? this.fInternalSubset.toString() : null;
        if (this.fDeferNodeExpansion) {
            if (internalSubset != null) {
                this.fDeferredDocumentImpl.setInternalSubset(this.fDocumentTypeIndex, internalSubset);
            }
        }
        else if (this.fDocumentImpl != null && internalSubset != null) {
            ((DocumentTypeImpl)this.fDocumentType).setInternalSubset(internalSubset);
        }
    }
    
    public void startConditional(final short type, final Augmentations augs) throws XNIException {
    }
    
    public void endConditional(final Augmentations augs) throws XNIException {
    }
    
    public void startExternalSubset(final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
        this.fBaseURIStack.push(identifier.getBaseSystemId());
        this.fInDTDExternalSubset = true;
    }
    
    public void endExternalSubset(final Augmentations augs) throws XNIException {
        this.fInDTDExternalSubset = false;
        this.fBaseURIStack.pop();
    }
    
    public void internalEntityDecl(final String name, final XMLString text, final XMLString nonNormalizedText, final Augmentations augs) throws XNIException {
        if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
            this.fInternalSubset.append("<!ENTITY ");
            if (name.startsWith("%")) {
                this.fInternalSubset.append("% ");
                this.fInternalSubset.append(name.substring(1));
            }
            else {
                this.fInternalSubset.append(name);
            }
            this.fInternalSubset.append(' ');
            final String value = nonNormalizedText.toString();
            final boolean singleQuote = value.indexOf(39) == -1;
            this.fInternalSubset.append(singleQuote ? '\'' : '\"');
            this.fInternalSubset.append(value);
            this.fInternalSubset.append(singleQuote ? '\'' : '\"');
            this.fInternalSubset.append(">\n");
        }
        if (name.startsWith("%")) {
            return;
        }
        if (this.fDocumentType != null) {
            final NamedNodeMap entities = this.fDocumentType.getEntities();
            EntityImpl entity = (EntityImpl)entities.getNamedItem(name);
            if (entity == null) {
                entity = (EntityImpl)this.fDocumentImpl.createEntity(name);
                entity.setBaseURI(this.fBaseURIStack.peek());
                entities.setNamedItem(entity);
            }
        }
        if (this.fDocumentTypeIndex != -1) {
            boolean found = false;
            for (int node = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, false); node != -1; node = this.fDeferredDocumentImpl.getRealPrevSibling(node, false)) {
                final short nodeType = this.fDeferredDocumentImpl.getNodeType(node, false);
                if (nodeType == 6) {
                    final String nodeName = this.fDeferredDocumentImpl.getNodeName(node, false);
                    if (nodeName.equals(name)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                final int entityIndex = this.fDeferredDocumentImpl.createDeferredEntity(name, null, null, null, this.fBaseURIStack.peek());
                this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, entityIndex);
            }
        }
    }
    
    public void externalEntityDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
        final String publicId = identifier.getPublicId();
        final String literalSystemId = identifier.getLiteralSystemId();
        if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
            this.fInternalSubset.append("<!ENTITY ");
            if (name.startsWith("%")) {
                this.fInternalSubset.append("% ");
                this.fInternalSubset.append(name.substring(1));
            }
            else {
                this.fInternalSubset.append(name);
            }
            this.fInternalSubset.append(' ');
            if (publicId != null) {
                this.fInternalSubset.append("PUBLIC '");
                this.fInternalSubset.append(publicId);
                this.fInternalSubset.append("' '");
            }
            else {
                this.fInternalSubset.append("SYSTEM '");
            }
            this.fInternalSubset.append(literalSystemId);
            this.fInternalSubset.append("'>\n");
        }
        if (name.startsWith("%")) {
            return;
        }
        if (this.fDocumentType != null) {
            final NamedNodeMap entities = this.fDocumentType.getEntities();
            EntityImpl entity = (EntityImpl)entities.getNamedItem(name);
            if (entity == null) {
                entity = (EntityImpl)this.fDocumentImpl.createEntity(name);
                entity.setPublicId(publicId);
                entity.setSystemId(literalSystemId);
                entity.setBaseURI(identifier.getBaseSystemId());
                entities.setNamedItem(entity);
            }
        }
        if (this.fDocumentTypeIndex != -1) {
            boolean found = false;
            for (int nodeIndex = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, false); nodeIndex != -1; nodeIndex = this.fDeferredDocumentImpl.getRealPrevSibling(nodeIndex, false)) {
                final short nodeType = this.fDeferredDocumentImpl.getNodeType(nodeIndex, false);
                if (nodeType == 6) {
                    final String nodeName = this.fDeferredDocumentImpl.getNodeName(nodeIndex, false);
                    if (nodeName.equals(name)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                final int entityIndex = this.fDeferredDocumentImpl.createDeferredEntity(name, publicId, literalSystemId, null, identifier.getBaseSystemId());
                this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, entityIndex);
            }
        }
    }
    
    public void startParameterEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
        this.fBaseURIStack.push(identifier.getExpandedSystemId());
    }
    
    public void endParameterEntity(final String name, final Augmentations augs) throws XNIException {
        this.fBaseURIStack.pop();
    }
    
    public void unparsedEntityDecl(final String name, final XMLResourceIdentifier identifier, final String notation, final Augmentations augs) throws XNIException {
        final String publicId = identifier.getPublicId();
        final String literalSystemId = identifier.getLiteralSystemId();
        if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
            this.fInternalSubset.append("<!ENTITY ");
            this.fInternalSubset.append(name);
            this.fInternalSubset.append(' ');
            if (publicId != null) {
                this.fInternalSubset.append("PUBLIC '");
                this.fInternalSubset.append(publicId);
                if (literalSystemId != null) {
                    this.fInternalSubset.append("' '");
                    this.fInternalSubset.append(literalSystemId);
                }
            }
            else {
                this.fInternalSubset.append("SYSTEM '");
                this.fInternalSubset.append(literalSystemId);
            }
            this.fInternalSubset.append("' NDATA ");
            this.fInternalSubset.append(notation);
            this.fInternalSubset.append(">\n");
        }
        if (this.fDocumentType != null) {
            final NamedNodeMap entities = this.fDocumentType.getEntities();
            EntityImpl entity = (EntityImpl)entities.getNamedItem(name);
            if (entity == null) {
                entity = (EntityImpl)this.fDocumentImpl.createEntity(name);
                entity.setPublicId(publicId);
                entity.setSystemId(literalSystemId);
                entity.setNotationName(notation);
                entity.setBaseURI(identifier.getBaseSystemId());
                entities.setNamedItem(entity);
            }
        }
        if (this.fDocumentTypeIndex != -1) {
            boolean found = false;
            for (int nodeIndex = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, false); nodeIndex != -1; nodeIndex = this.fDeferredDocumentImpl.getRealPrevSibling(nodeIndex, false)) {
                final short nodeType = this.fDeferredDocumentImpl.getNodeType(nodeIndex, false);
                if (nodeType == 6) {
                    final String nodeName = this.fDeferredDocumentImpl.getNodeName(nodeIndex, false);
                    if (nodeName.equals(name)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                final int entityIndex = this.fDeferredDocumentImpl.createDeferredEntity(name, publicId, literalSystemId, notation, identifier.getBaseSystemId());
                this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, entityIndex);
            }
        }
    }
    
    public void notationDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
        final String publicId = identifier.getPublicId();
        final String literalSystemId = identifier.getLiteralSystemId();
        if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
            this.fInternalSubset.append("<!NOTATION ");
            this.fInternalSubset.append(name);
            if (publicId != null) {
                this.fInternalSubset.append(" PUBLIC '");
                this.fInternalSubset.append(publicId);
                if (literalSystemId != null) {
                    this.fInternalSubset.append("' '");
                    this.fInternalSubset.append(literalSystemId);
                }
            }
            else {
                this.fInternalSubset.append(" SYSTEM '");
                this.fInternalSubset.append(literalSystemId);
            }
            this.fInternalSubset.append("'>\n");
        }
        if (this.fDocumentImpl != null && this.fDocumentType != null) {
            final NamedNodeMap notations = this.fDocumentType.getNotations();
            if (notations.getNamedItem(name) == null) {
                final NotationImpl notation = (NotationImpl)this.fDocumentImpl.createNotation(name);
                notation.setPublicId(publicId);
                notation.setSystemId(literalSystemId);
                notation.setBaseURI(identifier.getBaseSystemId());
                notations.setNamedItem(notation);
            }
        }
        if (this.fDocumentTypeIndex != -1) {
            boolean found = false;
            for (int nodeIndex = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, false); nodeIndex != -1; nodeIndex = this.fDeferredDocumentImpl.getPrevSibling(nodeIndex, false)) {
                final short nodeType = this.fDeferredDocumentImpl.getNodeType(nodeIndex, false);
                if (nodeType == 12) {
                    final String nodeName = this.fDeferredDocumentImpl.getNodeName(nodeIndex, false);
                    if (nodeName.equals(name)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                final int notationIndex = this.fDeferredDocumentImpl.createDeferredNotation(name, publicId, literalSystemId, identifier.getBaseSystemId());
                this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, notationIndex);
            }
        }
    }
    
    public void ignoredCharacters(final XMLString text, final Augmentations augs) throws XNIException {
    }
    
    public void elementDecl(final String name, final String contentModel, final Augmentations augs) throws XNIException {
        if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
            this.fInternalSubset.append("<!ELEMENT ");
            this.fInternalSubset.append(name);
            this.fInternalSubset.append(' ');
            this.fInternalSubset.append(contentModel);
            this.fInternalSubset.append(">\n");
        }
    }
    
    public void attributeDecl(final String elementName, final String attributeName, final String type, final String[] enumeration, final String defaultType, final XMLString defaultValue, final XMLString nonNormalizedDefaultValue, final Augmentations augs) throws XNIException {
        if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
            this.fInternalSubset.append("<!ATTLIST ");
            this.fInternalSubset.append(elementName);
            this.fInternalSubset.append(' ');
            this.fInternalSubset.append(attributeName);
            this.fInternalSubset.append(' ');
            if (type.equals("ENUMERATION")) {
                this.fInternalSubset.append('(');
                for (int i = 0; i < enumeration.length; ++i) {
                    if (i > 0) {
                        this.fInternalSubset.append('|');
                    }
                    this.fInternalSubset.append(enumeration[i]);
                }
                this.fInternalSubset.append(')');
            }
            else {
                this.fInternalSubset.append(type);
            }
            if (defaultType != null) {
                this.fInternalSubset.append(' ');
                this.fInternalSubset.append(defaultType);
            }
            if (defaultValue != null) {
                this.fInternalSubset.append(" '");
                for (int i = 0; i < defaultValue.length; ++i) {
                    final char c = defaultValue.ch[defaultValue.offset + i];
                    if (c == '\'') {
                        this.fInternalSubset.append("&apos;");
                    }
                    else {
                        this.fInternalSubset.append(c);
                    }
                }
                this.fInternalSubset.append('\'');
            }
            this.fInternalSubset.append(">\n");
        }
        if (this.fDeferredDocumentImpl != null) {
            if (defaultValue != null) {
                int elementDefIndex = this.fDeferredDocumentImpl.lookupElementDefinition(elementName);
                if (elementDefIndex == -1) {
                    elementDefIndex = this.fDeferredDocumentImpl.createDeferredElementDefinition(elementName);
                    this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, elementDefIndex);
                }
                final int attrIndex = this.fDeferredDocumentImpl.createDeferredAttribute(attributeName, defaultValue.toString(), false);
                if (type.equals("ID")) {
                    this.fDeferredDocumentImpl.setIdAttribute(attrIndex);
                }
                this.fDeferredDocumentImpl.appendChild(elementDefIndex, attrIndex);
            }
        }
        else if (this.fDocumentImpl != null && defaultValue != null) {
            final NamedNodeMap elements = ((DocumentTypeImpl)this.fDocumentType).getElements();
            ElementDefinitionImpl elementDef = (ElementDefinitionImpl)elements.getNamedItem(elementName);
            if (elementDef == null) {
                elementDef = this.fDocumentImpl.createElementDefinition(elementName);
                ((DocumentTypeImpl)this.fDocumentType).getElements().setNamedItem(elementDef);
            }
            final boolean nsEnabled = this.fNamespaceAware;
            AttrImpl attr;
            if (nsEnabled) {
                String namespaceURI = null;
                if (attributeName.startsWith("xmlns:") || attributeName.equals("xmlns")) {
                    namespaceURI = NamespaceContext.XMLNS_URI;
                }
                attr = (AttrImpl)this.fDocumentImpl.createAttributeNS(namespaceURI, attributeName);
            }
            else {
                attr = (AttrImpl)this.fDocumentImpl.createAttribute(attributeName);
            }
            attr.setValue(defaultValue.toString());
            attr.setSpecified(false);
            attr.setIdAttribute(type.equals("ID"));
            if (nsEnabled) {
                elementDef.getAttributes().setNamedItemNS(attr);
            }
            else {
                elementDef.getAttributes().setNamedItem(attr);
            }
        }
    }
    
    public void startAttlist(final String elementName, final Augmentations augs) throws XNIException {
    }
    
    public void endAttlist(final Augmentations augs) throws XNIException {
    }
    
    protected Element createElementNode(final QName element) {
        Element el = null;
        if (this.fNamespaceAware) {
            if (this.fDocumentImpl != null) {
                el = this.fDocumentImpl.createElementNS(element.uri, element.rawname, element.localpart);
            }
            else {
                el = this.fDocument.createElementNS(element.uri, element.rawname);
            }
        }
        else {
            el = this.fDocument.createElement(element.rawname);
        }
        return el;
    }
    
    protected Attr createAttrNode(final QName attrQName) {
        Attr attr = null;
        if (this.fNamespaceAware) {
            if (this.fDocumentImpl != null) {
                attr = this.fDocumentImpl.createAttributeNS(attrQName.uri, attrQName.rawname, attrQName.localpart);
            }
            else {
                attr = this.fDocument.createAttributeNS(attrQName.uri, attrQName.rawname);
            }
        }
        else {
            attr = this.fDocument.createAttribute(attrQName.rawname);
        }
        return attr;
    }
    
    protected void setCharacterData(final boolean sawChars) {
        this.fFirstChunk = sawChars;
        final Node child = this.fCurrentNode.getLastChild();
        if (child != null) {
            if (this.fStringBuffer.length() > 0) {
                if (child.getNodeType() == 3) {
                    if (this.fDocumentImpl != null) {
                        ((TextImpl)child).replaceData(this.fStringBuffer.toString());
                    }
                    else {
                        ((Text)child).setData(this.fStringBuffer.toString());
                    }
                }
                this.fStringBuffer.setLength(0);
            }
            if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x4) != 0x0) {
                final short code = this.fDOMFilter.acceptNode(child);
                switch (code) {
                    case 4: {
                        throw new RuntimeException("The normal processing of the document was interrupted.");
                    }
                    case 2:
                    case 3: {
                        this.fCurrentNode.removeChild(child);
                    }
                }
            }
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/namespaces", "http://apache.org/xml/features/dom/create-entity-ref-nodes", "http://apache.org/xml/features/include-comments", "http://apache.org/xml/features/create-cdata-nodes", "http://apache.org/xml/features/dom/include-ignorable-whitespace", "http://apache.org/xml/features/dom/defer-node-expansion" };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/dom/document-class-name", "http://apache.org/xml/properties/dom/current-element-node" };
    }
}
