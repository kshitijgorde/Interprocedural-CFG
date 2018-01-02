// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.Serializable;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.util.XMLGrammarPoolImpl;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.ShadowedSymbolTable;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.dom3.UserDataHandler;
import org.apache.xerces.util.XMLChar;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Notation;
import org.w3c.dom.Entity;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.impl.RevalidationHandler;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.dom3.DOMErrorHandler;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Element;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Comment;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Attr;
import java.util.Enumeration;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import java.util.Hashtable;
import org.apache.xerces.util.DOMErrorHandlerWrapper;
import org.w3c.dom.Document;

public class CoreDocumentImpl extends ParentNode implements Document
{
    static final long serialVersionUID = 0L;
    protected DocumentTypeImpl docType;
    protected ElementImpl docElement;
    transient NodeListCache fFreeNLCache;
    protected String encoding;
    protected String actualEncoding;
    protected String version;
    protected boolean standalone;
    protected String fDocumentURI;
    protected final transient DOMErrorHandlerWrapper fErrorHandlerWrapper;
    protected Hashtable userData;
    protected Hashtable identifiers;
    protected short features;
    protected static final short NAMESPACES = 1;
    protected static final short DTNORMALIZATION = 2;
    protected static final short ENTITIES = 4;
    protected static final short CDATA = 8;
    protected static final short DEFAULTS = 16;
    protected static final short SPLITCDATA = 32;
    protected static final short COMMENTS = 64;
    protected static final short VALIDATION = 128;
    protected DOMNormalizer domNormalizer;
    protected DOMValidationConfiguration fConfiguration;
    private static final int[] kidOK;
    protected int changes;
    protected boolean allowGrammarAccess;
    protected boolean errorChecking;
    
    public CoreDocumentImpl() {
        this(false);
    }
    
    public CoreDocumentImpl(final boolean grammarAccess) {
        super(null);
        this.fErrorHandlerWrapper = new DOMErrorHandlerWrapper();
        this.features = 0;
        this.domNormalizer = null;
        this.fConfiguration = null;
        this.changes = 0;
        this.errorChecking = true;
        super.ownerDocument = this;
        this.allowGrammarAccess = grammarAccess;
        this.features |= 0x1;
        this.features |= 0x4;
        this.features |= 0x40;
        this.features |= 0x2;
        this.features |= 0x8;
        this.features |= 0x10;
        this.features |= 0x20;
    }
    
    public CoreDocumentImpl(final DocumentType doctype) {
        this(doctype, false);
    }
    
    public CoreDocumentImpl(final DocumentType doctype, final boolean grammarAccess) {
        this(grammarAccess);
        if (doctype != null) {
            DocumentTypeImpl doctypeImpl;
            try {
                doctypeImpl = (DocumentTypeImpl)doctype;
            }
            catch (ClassCastException e) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
                throw new DOMException((short)4, msg);
            }
            (doctypeImpl.ownerDocument = this).appendChild(doctype);
        }
    }
    
    public final Document getOwnerDocument() {
        return null;
    }
    
    public short getNodeType() {
        return 9;
    }
    
    public String getNodeName() {
        return "#document";
    }
    
    public Node cloneNode(final boolean deep) {
        final CoreDocumentImpl newdoc = new CoreDocumentImpl();
        this.callUserDataHandlers(this, newdoc, (short)1);
        this.cloneNode(newdoc, deep);
        return newdoc;
    }
    
    protected void cloneNode(final CoreDocumentImpl newdoc, final boolean deep) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        if (deep) {
            Hashtable reversedIdentifiers = null;
            if (this.identifiers != null) {
                reversedIdentifiers = new Hashtable();
                final Enumeration elementIds = this.identifiers.keys();
                while (elementIds.hasMoreElements()) {
                    final Object elementId = elementIds.nextElement();
                    reversedIdentifiers.put(this.identifiers.get(elementId), elementId);
                }
            }
            for (ChildNode kid = super.firstChild; kid != null; kid = kid.nextSibling) {
                newdoc.appendChild(newdoc.importNode(kid, true, true, reversedIdentifiers));
            }
        }
        newdoc.allowGrammarAccess = this.allowGrammarAccess;
        newdoc.errorChecking = this.errorChecking;
    }
    
    public Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
        final int type = newChild.getNodeType();
        if (this.errorChecking && ((type == 1 && this.docElement != null) || (type == 10 && this.docType != null))) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null);
            throw new DOMException((short)3, msg);
        }
        if (newChild.getOwnerDocument() == null && newChild instanceof DocumentTypeImpl) {
            ((DocumentTypeImpl)newChild).ownerDocument = this;
        }
        super.insertBefore(newChild, refChild);
        if (type == 1) {
            this.docElement = (ElementImpl)newChild;
        }
        else if (type == 10) {
            this.docType = (DocumentTypeImpl)newChild;
        }
        return newChild;
    }
    
    public Node removeChild(final Node oldChild) throws DOMException {
        super.removeChild(oldChild);
        final int type = oldChild.getNodeType();
        if (type == 1) {
            this.docElement = null;
        }
        else if (type == 10) {
            this.docType = null;
        }
        return oldChild;
    }
    
    public Node replaceChild(final Node newChild, final Node oldChild) throws DOMException {
        if (newChild.getOwnerDocument() == null && newChild instanceof DocumentTypeImpl) {
            ((DocumentTypeImpl)newChild).ownerDocument = this;
        }
        super.replaceChild(newChild, oldChild);
        final int type = oldChild.getNodeType();
        if (type == 1) {
            this.docElement = (ElementImpl)newChild;
        }
        else if (type == 10) {
            this.docType = (DocumentTypeImpl)newChild;
        }
        return oldChild;
    }
    
    public String getTextContent() throws DOMException {
        return null;
    }
    
    public void setTextContent(final String textContent) throws DOMException {
    }
    
    public Attr createAttribute(final String name) throws DOMException {
        if (this.errorChecking && !isXMLName(name)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new AttrImpl(this, name);
    }
    
    public CDATASection createCDATASection(final String data) throws DOMException {
        return new CDATASectionImpl(this, data);
    }
    
    public Comment createComment(final String data) {
        return new CommentImpl(this, data);
    }
    
    public DocumentFragment createDocumentFragment() {
        return new DocumentFragmentImpl(this);
    }
    
    public Element createElement(final String tagName) throws DOMException {
        if (this.errorChecking && !isXMLName(tagName)) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg);
        }
        return new ElementImpl(this, tagName);
    }
    
    public EntityReference createEntityReference(final String name) throws DOMException {
        if (this.errorChecking && !isXMLName(name)) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg);
        }
        return new EntityReferenceImpl(this, name);
    }
    
    public ProcessingInstruction createProcessingInstruction(final String target, final String data) throws DOMException {
        if (this.errorChecking && !isXMLName(target)) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg);
        }
        return new ProcessingInstructionImpl(this, target, data);
    }
    
    public Text createTextNode(final String data) {
        return new TextImpl(this, data);
    }
    
    public DocumentType getDoctype() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.docType;
    }
    
    public Element getDocumentElement() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.docElement;
    }
    
    public NodeList getElementsByTagName(final String tagname) {
        return new DeepNodeListImpl(this, tagname);
    }
    
    public DOMImplementation getImplementation() {
        return CoreDOMImplementationImpl.getDOMImplementation();
    }
    
    public void setErrorChecking(final boolean check) {
        this.errorChecking = check;
    }
    
    public void setStrictErrorChecking(final boolean check) {
        this.errorChecking = check;
    }
    
    public boolean getErrorChecking() {
        return this.errorChecking;
    }
    
    public boolean getStrictErrorChecking() {
        return this.errorChecking;
    }
    
    public String getActualEncoding() {
        return this.actualEncoding;
    }
    
    public void setActualEncoding(final String value) {
        this.actualEncoding = value;
    }
    
    public void setEncoding(final String value) {
        this.encoding = value;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public void setVersion(final String value) {
        this.version = value;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public void setStandalone(final boolean value) {
        this.standalone = value;
    }
    
    public boolean getStandalone() {
        return this.standalone;
    }
    
    public String getDocumentURI() {
        return this.fDocumentURI;
    }
    
    public DOMErrorHandler getErrorHandler() {
        return this.fErrorHandlerWrapper.getErrorHandler();
    }
    
    public void setErrorHandler(final DOMErrorHandler errorHandler) {
        this.fErrorHandlerWrapper.setErrorHandler(errorHandler);
    }
    
    public Node renameNode(final Node n, final String namespaceURI, final String name) throws DOMException {
        if (n.getOwnerDocument() != this) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
            throw new DOMException((short)4, msg);
        }
        switch (n.getNodeType()) {
            case 1: {
                ElementImpl el = (ElementImpl)n;
                if (el instanceof ElementNSImpl) {
                    ((ElementNSImpl)el).rename(namespaceURI, name);
                }
                else if (namespaceURI == null) {
                    el.rename(name);
                }
                else {
                    final ElementNSImpl nel = new ElementNSImpl(this, namespaceURI, name);
                    this.copyEventListeners(el, nel);
                    final Hashtable data = this.removeUserDataTable(el);
                    final Node parent = el.getParentNode();
                    final Node nextSib = el.getNextSibling();
                    if (parent != null) {
                        parent.removeChild(el);
                    }
                    for (Node child = el.getFirstChild(); child != null; child = el.getFirstChild()) {
                        el.removeChild(child);
                        nel.appendChild(child);
                    }
                    nel.moveSpecifiedAttributes(el);
                    this.setUserDataTable(nel, data);
                    this.callUserDataHandlers(el, nel, (short)4);
                    if (parent != null) {
                        parent.insertBefore(nel, nextSib);
                    }
                    el = nel;
                }
                this.renamedElement((Element)n, el);
                return el;
            }
            case 2: {
                AttrImpl at = (AttrImpl)n;
                final Element el2 = at.getOwnerElement();
                if (el2 != null) {
                    el2.removeAttributeNode(at);
                }
                if (n instanceof AttrNSImpl) {
                    ((AttrNSImpl)at).rename(namespaceURI, name);
                    if (el2 != null) {
                        el2.setAttributeNodeNS(at);
                    }
                }
                else if (namespaceURI == null) {
                    at.rename(name);
                    if (el2 != null) {
                        el2.setAttributeNode(at);
                    }
                }
                else {
                    final AttrNSImpl nat = new AttrNSImpl(this, namespaceURI, name);
                    this.copyEventListeners(at, nat);
                    final Hashtable data2 = this.removeUserDataTable(at);
                    for (Node child2 = at.getFirstChild(); child2 != null; child2 = at.getFirstChild()) {
                        at.removeChild(child2);
                        nat.appendChild(child2);
                    }
                    this.setUserDataTable(nat, data2);
                    this.callUserDataHandlers(at, nat, (short)4);
                    if (el2 != null) {
                        el2.setAttributeNode(nat);
                    }
                    at = nat;
                }
                this.renamedAttrNode((Attr)n, at);
                return at;
            }
            default: {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
                throw new DOMException((short)9, msg);
            }
        }
    }
    
    public void normalizeDocument() {
        if (this.isNormalized() && !this.isNormalizeDocRequired()) {
            return;
        }
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        if (this.domNormalizer == null) {
            this.domNormalizer = new DOMNormalizer();
        }
        if (this.fConfiguration == null) {
            this.fConfiguration = new DOMValidationConfiguration();
        }
        if (this.fErrorHandlerWrapper.getErrorHandler() != null) {
            this.fConfiguration.setErrorHandler(this.fErrorHandlerWrapper);
        }
        this.fConfiguration.reset();
        if ((this.features & 0x80) != 0x0) {
            this.fConfiguration.setFeature("http://xml.org/sax/features/validation", true);
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", true);
            this.domNormalizer.setValidationHandler(CoreDOMImplementationImpl.singleton.getValidator("http://www.w3.org/2001/XMLSchema"));
        }
        else {
            this.domNormalizer.setValidationHandler(null);
        }
        this.domNormalizer.reset(this.fConfiguration);
        try {
            this.domNormalizer.normalizeDocument(this);
        }
        catch (RuntimeException ex) {}
        if ((this.features & 0x80) != 0x0) {
            CoreDOMImplementationImpl.singleton.releaseValidator("http://www.w3.org/2001/XMLSchema");
        }
        this.isNormalized(true);
    }
    
    protected boolean isNormalizeDocRequired() {
        return true;
    }
    
    public void setNormalizationFeature(final String name, final boolean state) throws DOMException {
        if (name.equals("comments")) {
            this.features = (state ? ((short)(this.features | 0x40)) : ((short)(this.features & 0xFFFFFFBF)));
        }
        else if (name.equals("datatype-normalization")) {
            this.features = (state ? ((short)(this.features | 0x2)) : ((short)(this.features & 0xFFFFFFFD)));
        }
        else if (name.equals("namespaces")) {
            this.features = (state ? ((short)(this.features | 0x1)) : ((short)(this.features & 0xFFFFFFFE)));
        }
        else if (name.equals("cdata-sections")) {
            this.features = (state ? ((short)(this.features | 0x8)) : ((short)(this.features & 0xFFFFFFF7)));
        }
        else if (name.equals("entities")) {
            this.features = (state ? ((short)(this.features | 0x4)) : ((short)(this.features & 0xFFFFFFFB)));
        }
        else if (name.equals("discard-default-content")) {
            this.features = (state ? ((short)(this.features | 0x10)) : ((short)(this.features & 0xFFFFFFEF)));
        }
        else if (name.equals("split-cdata-sections")) {
            this.features = (state ? ((short)(this.features | 0x20)) : ((short)(this.features & 0xFFFFFFDF)));
        }
        else if (name.equals("validate")) {
            this.features = (state ? ((short)(this.features | 0x80)) : ((short)(this.features & 0xFFFFFF7F)));
        }
        else if (name.equals("infoset") || name.equals("normalize-characters") || name.equals("canonical-form") || name.equals("validate-if-schema")) {
            if (state) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { name });
                throw new DOMException((short)9, msg);
            }
        }
        else {
            if (!name.equals("namespace-declarations") && !name.equals("whitespace-in-element-content")) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { name });
                throw new DOMException((short)8, msg);
            }
            if (!state) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { name });
                throw new DOMException((short)9, msg);
            }
        }
    }
    
    public boolean getNormalizationFeature(final String name) throws DOMException {
        if (name.equals("comments")) {
            return (this.features & 0x40) != 0x0;
        }
        if (name.equals("namespaces")) {
            return (this.features & 0x1) != 0x0;
        }
        if (name.equals("datatype-normalization")) {
            return (this.features & 0x2) != 0x0;
        }
        if (name.equals("cdata-sections")) {
            return (this.features & 0x8) != 0x0;
        }
        if (name.equals("entities")) {
            return (this.features & 0x4) != 0x0;
        }
        if (name.equals("discard-default-content")) {
            return (this.features & 0x10) != 0x0;
        }
        if (name.equals("split-cdata-sections")) {
            return (this.features & 0x20) != 0x0;
        }
        if (name.equals("infoset") || name.equals("normalize-characters") || name.equals("canonical-form") || name.equals("validate") || name.equals("validate-if-schema")) {
            return false;
        }
        if (name.equals("namespace-declarations") || name.equals("whitespace-in-element-content")) {
            return true;
        }
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { name });
        throw new DOMException((short)8, msg);
    }
    
    public boolean canSetNormalizationFeature(final String name, final boolean state) {
        if (name.equals("comments") || name.equals("datatype-normalization") || name.equals("cdata-sections") || name.equals("entities") || name.equals("discard-default-content") || name.equals("split-cdata-sections") || name.equals("namespaces")) {
            return true;
        }
        if (name.equals("infoset") || name.equals("normalize-characters") || name.equals("canonical-form") || name.equals("validate") || name.equals("validate-if-schema")) {
            return !state;
        }
        if (name.equals("namespace-declarations") || name.equals("whitespace-in-element-content")) {
            return state;
        }
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { name });
        throw new DOMException((short)8, msg);
    }
    
    public String getBaseURI() {
        return this.fDocumentURI;
    }
    
    public void setDocumentURI(final String documentURI) {
        this.fDocumentURI = documentURI;
    }
    
    public boolean getAsync() {
        return false;
    }
    
    public void setAsync(final boolean async) {
        if (async) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
            throw new DOMException((short)9, msg);
        }
    }
    
    public void abort() {
    }
    
    public boolean load(final String uri) {
        return false;
    }
    
    public boolean loadXML(final String source) {
        return false;
    }
    
    void setMutationEvents(final boolean set) {
    }
    
    boolean getMutationEvents() {
        return false;
    }
    
    public DocumentType createDocumentType(final String qualifiedName, final String publicID, final String systemID) throws DOMException {
        if (this.errorChecking && !isXMLName(qualifiedName)) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg);
        }
        return new DocumentTypeImpl(this, qualifiedName, publicID, systemID);
    }
    
    public Entity createEntity(final String name) throws DOMException {
        if (this.errorChecking && !isXMLName(name)) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg);
        }
        return new EntityImpl(this, name);
    }
    
    public Notation createNotation(final String name) throws DOMException {
        if (this.errorChecking && !isXMLName(name)) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg);
        }
        return new NotationImpl(this, name);
    }
    
    public ElementDefinitionImpl createElementDefinition(final String name) throws DOMException {
        if (this.errorChecking && !isXMLName(name)) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg);
        }
        return new ElementDefinitionImpl(this, name);
    }
    
    public Node importNode(final Node source, final boolean deep) throws DOMException {
        return this.importNode(source, deep, false, null);
    }
    
    private Node importNode(final Node source, boolean deep, final boolean cloningDoc, final Hashtable reversedIdentifiers) throws DOMException {
        Node newnode = null;
        final int type = source.getNodeType();
        switch (type) {
            case 1: {
                final boolean domLevel20 = source.getOwnerDocument().getImplementation().hasFeature("XML", "2.0");
                Element newElement;
                if (!domLevel20 || source.getLocalName() == null) {
                    newElement = this.createElement(source.getNodeName());
                }
                else {
                    newElement = this.createElementNS(source.getNamespaceURI(), source.getNodeName());
                }
                final NamedNodeMap sourceAttrs = source.getAttributes();
                if (sourceAttrs != null) {
                    for (int length = sourceAttrs.getLength(), index = 0; index < length; ++index) {
                        final Attr attr = (Attr)sourceAttrs.item(index);
                        if (attr.getSpecified() || cloningDoc) {
                            final Attr newAttr = (Attr)this.importNode(attr, true, cloningDoc, reversedIdentifiers);
                            if (!domLevel20 || attr.getLocalName() == null) {
                                newElement.setAttributeNode(newAttr);
                            }
                            else {
                                newElement.setAttributeNodeNS(newAttr);
                            }
                        }
                    }
                }
                if (reversedIdentifiers != null) {
                    final Object elementId = reversedIdentifiers.get(source);
                    if (elementId != null) {
                        if (this.identifiers == null) {
                            this.identifiers = new Hashtable();
                        }
                        this.identifiers.put(elementId, newElement);
                    }
                }
                newnode = newElement;
                break;
            }
            case 2: {
                if (source.getOwnerDocument().getImplementation().hasFeature("XML", "2.0")) {
                    if (source.getLocalName() == null) {
                        newnode = this.createAttribute(source.getNodeName());
                    }
                    else {
                        newnode = this.createAttributeNS(source.getNamespaceURI(), source.getNodeName());
                    }
                }
                else {
                    newnode = this.createAttribute(source.getNodeName());
                }
                if (source instanceof AttrImpl) {
                    final AttrImpl attr2 = (AttrImpl)source;
                    if (attr2.hasStringValue()) {
                        final AttrImpl newattr = (AttrImpl)newnode;
                        newattr.setValue(attr2.getValue());
                        deep = false;
                    }
                    else {
                        deep = true;
                    }
                    break;
                }
                if (source.getFirstChild() == null) {
                    newnode.setNodeValue(source.getNodeValue());
                    deep = false;
                    break;
                }
                deep = true;
                break;
            }
            case 3: {
                newnode = this.createTextNode(source.getNodeValue());
                break;
            }
            case 4: {
                newnode = this.createCDATASection(source.getNodeValue());
                break;
            }
            case 5: {
                newnode = this.createEntityReference(source.getNodeName());
                deep = false;
                break;
            }
            case 6: {
                final Entity srcentity = (Entity)source;
                final EntityImpl newentity = (EntityImpl)this.createEntity(source.getNodeName());
                newentity.setPublicId(srcentity.getPublicId());
                newentity.setSystemId(srcentity.getSystemId());
                newentity.setNotationName(srcentity.getNotationName());
                newentity.isReadOnly(false);
                newnode = newentity;
                break;
            }
            case 7: {
                newnode = this.createProcessingInstruction(source.getNodeName(), source.getNodeValue());
                break;
            }
            case 8: {
                newnode = this.createComment(source.getNodeValue());
                break;
            }
            case 10: {
                if (!cloningDoc) {
                    final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
                    throw new DOMException((short)9, msg);
                }
                final DocumentType srcdoctype = (DocumentType)source;
                final DocumentTypeImpl newdoctype = (DocumentTypeImpl)this.createDocumentType(srcdoctype.getNodeName(), srcdoctype.getPublicId(), srcdoctype.getSystemId());
                NamedNodeMap smap = srcdoctype.getEntities();
                NamedNodeMap tmap = newdoctype.getEntities();
                if (smap != null) {
                    for (int i = 0; i < smap.getLength(); ++i) {
                        tmap.setNamedItem(this.importNode(smap.item(i), true, true, reversedIdentifiers));
                    }
                }
                smap = srcdoctype.getNotations();
                tmap = newdoctype.getNotations();
                if (smap != null) {
                    for (int i = 0; i < smap.getLength(); ++i) {
                        tmap.setNamedItem(this.importNode(smap.item(i), true, true, reversedIdentifiers));
                    }
                }
                newnode = newdoctype;
                break;
            }
            case 11: {
                newnode = this.createDocumentFragment();
                break;
            }
            case 12: {
                final Notation srcnotation = (Notation)source;
                final NotationImpl newnotation = (NotationImpl)this.createNotation(source.getNodeName());
                newnotation.setPublicId(srcnotation.getPublicId());
                newnotation.setSystemId(srcnotation.getSystemId());
                newnode = newnotation;
                break;
            }
            default: {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
                throw new DOMException((short)9, msg);
            }
        }
        this.callUserDataHandlers(source, newnode, (short)2);
        if (deep) {
            for (Node srckid = source.getFirstChild(); srckid != null; srckid = srckid.getNextSibling()) {
                newnode.appendChild(this.importNode(srckid, true, cloningDoc, reversedIdentifiers));
            }
        }
        if (newnode.getNodeType() == 6) {
            ((NodeImpl)newnode).setReadOnly(true, true);
        }
        return newnode;
    }
    
    public Node adoptNode(final Node source) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* source */
        //     1: checkcast       Lorg/apache/xerces/dom/NodeImpl;
        //     4: astore_2        /* node */
        //     5: goto            11
        //     8: astore_3        /* e */
        //     9: aconst_null    
        //    10: areturn        
        //    11: aload_2         /* node */
        //    12: invokevirtual   org/apache/xerces/dom/NodeImpl.getNodeType:()S
        //    15: tableswitch {
        //                2: 252
        //                3: 68
        //                4: 284
        //                5: 284
        //                6: 117
        //                7: 284
        //                8: 284
        //                9: 284
        //               10: 97
        //               11: 97
        //          default: 284
        //        }
        //    68: aload_2         /* node */
        //    69: checkcast       Lorg/apache/xerces/dom/AttrImpl;
        //    72: astore_3        /* attr */
        //    73: aload_3         /* attr */
        //    74: invokevirtual   org/apache/xerces/dom/AttrImpl.getOwnerElement:()Lorg/w3c/dom/Element;
        //    77: aload_3         /* attr */
        //    78: invokeinterface org/w3c/dom/Element.removeAttributeNode:(Lorg/w3c/dom/Attr;)Lorg/w3c/dom/Attr;
        //    83: pop            
        //    84: aload_3         /* attr */
        //    85: iconst_1       
        //    86: invokevirtual   org/apache/xerces/dom/NodeImpl.isSpecified:(Z)V
        //    89: aload_3         /* attr */
        //    90: aload_0         /* this */
        //    91: invokevirtual   org/apache/xerces/dom/AttrImpl.setOwnerDocument:(Lorg/apache/xerces/dom/CoreDocumentImpl;)V
        //    94: goto            306
        //    97: ldc             "http://www.w3.org/dom/DOMTR"
        //    99: ldc             "NOT_SUPPORTED_ERR"
        //   101: aconst_null    
        //   102: invokestatic    org/apache/xerces/dom/DOMMessageFormatter.formatMessage:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   105: astore_3        /* msg */
        //   106: new             Lorg/w3c/dom/DOMException;
        //   109: dup            
        //   110: bipush          9
        //   112: aload_3         /* msg */
        //   113: invokespecial   org/w3c/dom/DOMException.<init>:(SLjava/lang/String;)V
        //   116: athrow         
        //   117: aload_2         /* node */
        //   118: invokevirtual   org/apache/xerces/dom/NodeImpl.getParentNode:()Lorg/w3c/dom/Node;
        //   121: astore_3        /* parent */
        //   122: aload_3         /* parent */
        //   123: ifnull          144
        //   126: aload_3         /* parent */
        //   127: aload_1         /* source */
        //   128: invokeinterface org/w3c/dom/Node.removeChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //   133: pop            
        //   134: goto            144
        //   137: aload_2         /* node */
        //   138: aload           4
        //   140: invokevirtual   org/apache/xerces/dom/NodeImpl.removeChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //   143: pop            
        //   144: aload_2         /* node */
        //   145: invokevirtual   org/apache/xerces/dom/NodeImpl.getFirstChild:()Lorg/w3c/dom/Node;
        //   148: dup            
        //   149: astore          child
        //   151: ifnonnull       137
        //   154: aload_2         /* node */
        //   155: aload_0         /* this */
        //   156: invokevirtual   org/apache/xerces/dom/NodeImpl.setOwnerDocument:(Lorg/apache/xerces/dom/CoreDocumentImpl;)V
        //   159: aload_0         /* this */
        //   160: getfield        org/apache/xerces/dom/CoreDocumentImpl.docType:Lorg/apache/xerces/dom/DocumentTypeImpl;
        //   163: ifnonnull       169
        //   166: goto            306
        //   169: aload_0         /* this */
        //   170: getfield        org/apache/xerces/dom/CoreDocumentImpl.docType:Lorg/apache/xerces/dom/DocumentTypeImpl;
        //   173: invokevirtual   org/apache/xerces/dom/DocumentTypeImpl.getEntities:()Lorg/w3c/dom/NamedNodeMap;
        //   176: astore          entities
        //   178: aload           entities
        //   180: aload_2         /* node */
        //   181: invokevirtual   org/apache/xerces/dom/NodeImpl.getNodeName:()Ljava/lang/String;
        //   184: invokeinterface org/w3c/dom/NamedNodeMap.getNamedItem:(Ljava/lang/String;)Lorg/w3c/dom/Node;
        //   189: astore          entityNode
        //   191: aload           entityNode
        //   193: ifnonnull       199
        //   196: goto            306
        //   199: aload           entityNode
        //   201: checkcast       Lorg/apache/xerces/dom/EntityImpl;
        //   204: astore          entity
        //   206: aload           entityNode
        //   208: invokeinterface org/w3c/dom/Node.getFirstChild:()Lorg/w3c/dom/Node;
        //   213: astore          child
        //   215: goto            244
        //   218: aload           child
        //   220: iconst_1       
        //   221: invokeinterface org/w3c/dom/Node.cloneNode:(Z)Lorg/w3c/dom/Node;
        //   226: astore          childClone
        //   228: aload_2         /* node */
        //   229: aload           childClone
        //   231: invokevirtual   org/apache/xerces/dom/NodeImpl.appendChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //   234: pop            
        //   235: aload           child
        //   237: invokeinterface org/w3c/dom/Node.getNextSibling:()Lorg/w3c/dom/Node;
        //   242: astore          child
        //   244: aload           child
        //   246: ifnonnull       218
        //   249: goto            306
        //   252: aload_2         /* node */
        //   253: invokevirtual   org/apache/xerces/dom/NodeImpl.getParentNode:()Lorg/w3c/dom/Node;
        //   256: astore_3        /* parent */
        //   257: aload_3         /* parent */
        //   258: ifnull          269
        //   261: aload_3         /* parent */
        //   262: aload_1         /* source */
        //   263: invokeinterface org/w3c/dom/Node.removeChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //   268: pop            
        //   269: aload_2         /* node */
        //   270: aload_0         /* this */
        //   271: invokevirtual   org/apache/xerces/dom/NodeImpl.setOwnerDocument:(Lorg/apache/xerces/dom/CoreDocumentImpl;)V
        //   274: aload_2         /* node */
        //   275: checkcast       Lorg/apache/xerces/dom/ElementImpl;
        //   278: invokevirtual   org/apache/xerces/dom/ElementImpl.reconcileDefaultAttributes:()V
        //   281: goto            306
        //   284: aload_2         /* node */
        //   285: invokevirtual   org/apache/xerces/dom/NodeImpl.getParentNode:()Lorg/w3c/dom/Node;
        //   288: astore_3        /* parent */
        //   289: aload_3         /* parent */
        //   290: ifnull          301
        //   293: aload_3         /* parent */
        //   294: aload_1         /* source */
        //   295: invokeinterface org/w3c/dom/Node.removeChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //   300: pop            
        //   301: aload_2         /* node */
        //   302: aload_0         /* this */
        //   303: invokevirtual   org/apache/xerces/dom/NodeImpl.setOwnerDocument:(Lorg/apache/xerces/dom/CoreDocumentImpl;)V
        //   306: aload_2         /* node */
        //   307: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name        Signature
        //  -----  ------  ----  ----------  ----------------------------------------
        //  0      308     0     this        Lorg/apache/xerces/dom/CoreDocumentImpl;
        //  0      308     1     source      Lorg/w3c/dom/Node;
        //  5      303     2     node        Lorg/apache/xerces/dom/NodeImpl;
        //  8      3       3     e           Ljava/lang/ClassCastException;
        //  73     24      3     attr        Lorg/apache/xerces/dom/AttrImpl;
        //  106    11      3     msg         Ljava/lang/String;
        //  122    130     3     parent      Lorg/w3c/dom/Node;
        //  151    101     4     child       Lorg/w3c/dom/Node;
        //  178    74      5     entities    Lorg/w3c/dom/NamedNodeMap;
        //  191    61      6     entityNode  Lorg/w3c/dom/Node;
        //  206    46      7     entity      Lorg/apache/xerces/dom/EntityImpl;
        //  228    7       8     childClone  Lorg/w3c/dom/Node;
        //  257    27      3     parent      Lorg/w3c/dom/Node;
        //  289    17      3     parent      Lorg/w3c/dom/Node;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                          
        //  -----  -----  -----  -----  ------------------------------
        //  0      5      8      11     Ljava/lang/ClassCastException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Element getElementById(final String elementId) {
        return this.getIdentifier(elementId);
    }
    
    public void putIdentifier(final String idName, final Element element) {
        if (element == null) {
            this.removeIdentifier(idName);
            return;
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            this.identifiers = new Hashtable();
        }
        this.identifiers.put(idName, element);
    }
    
    public Element getIdentifier(final String idName) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            return null;
        }
        return this.identifiers.get(idName);
    }
    
    public void removeIdentifier(final String idName) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            return;
        }
        this.identifiers.remove(idName);
    }
    
    public Enumeration getIdentifiers() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            this.identifiers = new Hashtable();
        }
        return this.identifiers.keys();
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        if (this.errorChecking && !isXMLName(qualifiedName)) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg);
        }
        return new ElementNSImpl(this, namespaceURI, qualifiedName);
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName, final String localpart) throws DOMException {
        return new ElementNSImpl(this, namespaceURI, qualifiedName, localpart);
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        return new AttrNSImpl(this, namespaceURI, qualifiedName);
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName, final String localpart) throws DOMException {
        return new AttrNSImpl(this, namespaceURI, qualifiedName, localpart);
    }
    
    public NodeList getElementsByTagNameNS(final String namespaceURI, final String localName) {
        return new DeepNodeListImpl(this, namespaceURI, localName);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final CoreDocumentImpl newdoc = (CoreDocumentImpl)super.clone();
        newdoc.docType = null;
        newdoc.docElement = null;
        return newdoc;
    }
    
    public static boolean isXMLName(final String s) {
        return s != null && XMLChar.isValidName(s);
    }
    
    protected boolean isKidOK(final Node parent, final Node child) {
        if (this.allowGrammarAccess && parent.getNodeType() == 10) {
            return child.getNodeType() == 1;
        }
        return 0x0 != (CoreDocumentImpl.kidOK[parent.getNodeType()] & 1 << child.getNodeType());
    }
    
    protected void changed() {
        ++this.changes;
    }
    
    protected int changes() {
        return this.changes;
    }
    
    NodeListCache getNodeListCache(final ParentNode owner) {
        if (this.fFreeNLCache == null) {
            return new NodeListCache(owner);
        }
        final NodeListCache c = this.fFreeNLCache;
        this.fFreeNLCache = this.fFreeNLCache.next;
        c.fChild = null;
        c.fChildIndex = -1;
        c.fLength = -1;
        if (c.fOwner != null) {
            c.fOwner.fNodeListCache = null;
        }
        c.fOwner = owner;
        return c;
    }
    
    void freeNodeListCache(final NodeListCache c) {
        c.next = this.fFreeNLCache;
        this.fFreeNLCache = c;
    }
    
    public Object setUserData(final Node n, final String key, final Object data, final UserDataHandler handler) {
        if (data == null) {
            if (this.userData != null) {
                final Hashtable t = this.userData.get(n);
                if (t != null) {
                    final Object o = t.remove(key);
                    if (o != null) {
                        final UserDataRecord r = (UserDataRecord)o;
                        return r.fData;
                    }
                }
            }
            return null;
        }
        Hashtable t;
        if (this.userData == null) {
            this.userData = new Hashtable();
            t = new Hashtable();
            this.userData.put(n, t);
        }
        else {
            t = this.userData.get(n);
            if (t == null) {
                t = new Hashtable();
                this.userData.put(n, t);
            }
        }
        final Object o = t.put(key, new UserDataRecord(data, handler));
        if (o != null) {
            final UserDataRecord r = (UserDataRecord)o;
            return r.fData;
        }
        return null;
    }
    
    public Object getUserData(final Node n, final String key) {
        if (this.userData == null) {
            return null;
        }
        final Hashtable t = this.userData.get(n);
        if (t == null) {
            return null;
        }
        final Object o = t.get(key);
        if (o != null) {
            final UserDataRecord r = (UserDataRecord)o;
            return r.fData;
        }
        return null;
    }
    
    Hashtable removeUserDataTable(final Node n) {
        if (this.userData == null) {
            return null;
        }
        return this.userData.get(n);
    }
    
    void setUserDataTable(final Node n, final Hashtable data) {
        if (data != null) {
            this.userData.put(n, data);
        }
    }
    
    void callUserDataHandlers(final Node n, final Node c, final short operation) {
        if (this.userData == null) {
            return;
        }
        final Hashtable t = this.userData.get(n);
        if (t == null || t.isEmpty()) {
            return;
        }
        final Enumeration keys = t.keys();
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            final UserDataRecord r = t.get(key);
            if (r.fHandler != null) {
                r.fHandler.handle(operation, key, r.fData, n, c);
            }
        }
    }
    
    public void copyConfigurationProperties(final XMLParserConfiguration config) {
        final SymbolTable symbolTable = new ShadowedSymbolTable((SymbolTable)config.getProperty("http://apache.org/xml/properties/internal/symbol-table"));
        this.fConfiguration = new DOMValidationConfiguration(symbolTable);
        final XMLEntityResolver entityResolver = config.getEntityResolver();
        if (entityResolver != null) {
            this.fConfiguration.setEntityResolver(entityResolver);
        }
        final XMLGrammarPool pool = (XMLGrammarPool)config.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        if (pool != null) {
            final XMLGrammarPool grammarPool = new XMLGrammarPoolImpl();
            grammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", pool.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema"));
            this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", grammarPool);
        }
    }
    
    protected final void checkNamespaceWF(final String qname, final int colon1, final int colon2) {
        if (!this.errorChecking) {
            return;
        }
        if (colon1 == 0 || colon1 == qname.length() - 1 || colon2 != colon1) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
            throw new DOMException((short)14, msg);
        }
    }
    
    protected final void checkDOMNSErr(final String prefix, final String namespace) {
        if (this.errorChecking) {
            if (namespace == null) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
                throw new DOMException((short)14, msg);
            }
            if (prefix.equals("xml") && namespace != NamespaceContext.XML_URI) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
                throw new DOMException((short)14, msg);
            }
            if ((prefix.equals("xmlns") && namespace != NamespaceContext.XMLNS_URI) || (!prefix.equals("xmlns") && namespace == NamespaceContext.XMLNS_URI)) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
                throw new DOMException((short)14, msg);
            }
        }
    }
    
    protected final void checkQName(final String prefix, final String local) {
        if (!this.errorChecking) {
            return;
        }
        if (prefix != null) {
            final int length = prefix.length();
            if (!XMLChar.isNCNameStart(prefix.charAt(0))) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
                throw new DOMException((short)5, msg);
            }
            for (int i = 1; i < length; ++i) {
                if (!XMLChar.isNCName(prefix.charAt(i))) {
                    final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
                    throw new DOMException((short)5, msg2);
                }
            }
        }
        final int length = local.length();
        if (!XMLChar.isNCNameStart(local.charAt(0))) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg);
        }
        for (int i = 1; i < length; ++i) {
            if (!XMLChar.isNCName(local.charAt(i))) {
                final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
                throw new DOMException((short)5, msg2);
            }
        }
    }
    
    protected void setUserData(final NodeImpl n, final Object data) {
        this.setUserData(n, "XERCES1DOMUSERDATA", data, null);
    }
    
    protected Object getUserData(final NodeImpl n) {
        return this.getUserData(n, "XERCES1DOMUSERDATA");
    }
    
    protected void addEventListener(final NodeImpl node, final String type, final EventListener listener, final boolean useCapture) {
    }
    
    protected void removeEventListener(final NodeImpl node, final String type, final EventListener listener, final boolean useCapture) {
    }
    
    protected void copyEventListeners(final NodeImpl src, final NodeImpl tgt) {
    }
    
    protected boolean dispatchEvent(final NodeImpl node, final Event event) {
        return false;
    }
    
    void replacedText(final NodeImpl node) {
    }
    
    void deletedText(final NodeImpl node, final int offset, final int count) {
    }
    
    void insertedText(final NodeImpl node, final int offset, final int count) {
    }
    
    void modifyingCharacterData(final NodeImpl node) {
    }
    
    void modifiedCharacterData(final NodeImpl node, final String oldvalue, final String value) {
    }
    
    void insertingNode(final NodeImpl node, final boolean replace) {
    }
    
    void insertedNode(final NodeImpl node, final NodeImpl newInternal, final boolean replace) {
    }
    
    void removingNode(final NodeImpl node, final NodeImpl oldChild, final boolean replace) {
    }
    
    void removedNode(final NodeImpl node, final boolean replace) {
    }
    
    void replacingNode(final NodeImpl node) {
    }
    
    void replacedNode(final NodeImpl node) {
    }
    
    void modifiedAttrValue(final AttrImpl attr, final String oldvalue) {
    }
    
    void setAttrNode(final AttrImpl attr, final AttrImpl previous) {
    }
    
    void removedAttrNode(final AttrImpl attr, final NodeImpl oldOwner, final String name) {
    }
    
    void renamedAttrNode(final Attr oldAt, final Attr newAt) {
    }
    
    void renamedElement(final Element oldEl, final Element newEl) {
    }
    
    static {
        (kidOK = new int[13])[9] = 1410;
        final int[] kidOK2 = CoreDocumentImpl.kidOK;
        final int n = 11;
        final int[] kidOK3 = CoreDocumentImpl.kidOK;
        final int n2 = 6;
        final int[] kidOK4 = CoreDocumentImpl.kidOK;
        final int n3 = 5;
        final int[] kidOK5 = CoreDocumentImpl.kidOK;
        final int n4 = 1;
        final int n5 = 442;
        kidOK4[n3] = (kidOK5[n4] = n5);
        kidOK2[n] = (kidOK3[n2] = n5);
        CoreDocumentImpl.kidOK[2] = 40;
        final int[] kidOK6 = CoreDocumentImpl.kidOK;
        final int n6 = 10;
        final int[] kidOK7 = CoreDocumentImpl.kidOK;
        final int n7 = 7;
        final int[] kidOK8 = CoreDocumentImpl.kidOK;
        final int n8 = 8;
        final int[] kidOK9 = CoreDocumentImpl.kidOK;
        final int n9 = 3;
        final int[] kidOK10 = CoreDocumentImpl.kidOK;
        final int n10 = 4;
        final int[] kidOK11 = CoreDocumentImpl.kidOK;
        final int n11 = 12;
        final boolean b = false;
        kidOK10[n10] = (kidOK11[n11] = (b ? 1 : 0));
        kidOK8[n8] = (kidOK9[n9] = (b ? 1 : 0));
        kidOK6[n6] = (kidOK7[n7] = (b ? 1 : 0));
    }
    
    class UserDataRecord implements Serializable
    {
        Object fData;
        UserDataHandler fHandler;
        
        UserDataRecord(final Object data, final UserDataHandler handler) {
            this.fData = data;
            this.fHandler = handler;
        }
    }
}
