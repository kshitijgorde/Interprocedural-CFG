// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.util.AugmentationsImpl;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.psvi.ElementPSVI;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.psvi.AttributePSVI;
import org.w3c.dom.Element;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.dom3.DOMError;
import org.w3c.dom.Text;
import org.apache.xerces.xni.XMLAttributes;
import org.w3c.dom.Attr;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.w3c.dom.Node;
import java.util.Vector;
import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.dom3.DOMErrorHandler;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.RevalidationHandler;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.grammars.XMLGrammarPool;

public class DOMNormalizer implements XMLGrammarPool, XMLDocumentHandler
{
    protected static final boolean DEBUG_ND = false;
    protected static final boolean DEBUG = false;
    protected static final String PREFIX = "NS";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected CoreDocumentImpl fDocument;
    protected final XMLAttributesProxy fAttrProxy;
    protected final QName fQName;
    protected RevalidationHandler fValidationHandler;
    protected SymbolTable fSymbolTable;
    protected DOMErrorHandler fErrorHandler;
    protected int fNamespaceCounter;
    protected boolean fNamespaceValidation;
    protected boolean fPSVI;
    protected final NamespaceSupport fNamespaceContext;
    protected final NamespaceSupport fLocalNSBinder;
    protected final Vector fAttributeList;
    protected final DOMErrorImpl fDOMError;
    protected final DOMLocatorImpl fLocator;
    protected Node fCurrentNode;
    private QName fAttrQName;
    
    public DOMNormalizer() {
        this.fDocument = null;
        this.fAttrProxy = new XMLAttributesProxy();
        this.fQName = new QName();
        this.fNamespaceCounter = 1;
        this.fNamespaceValidation = false;
        this.fPSVI = false;
        this.fNamespaceContext = new NamespaceSupport();
        this.fLocalNSBinder = new NamespaceSupport();
        this.fAttributeList = new Vector(5, 10);
        this.fDOMError = new DOMErrorImpl();
        this.fLocator = new DOMLocatorImpl();
        this.fCurrentNode = null;
        this.fAttrQName = new QName();
    }
    
    protected void reset(final XMLComponentManager componentManager) {
        if (componentManager == null) {
            this.fSymbolTable = null;
            this.fValidationHandler = null;
            return;
        }
        this.fPSVI = false;
        this.fSymbolTable = (SymbolTable)componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        if (this.fSymbolTable == null) {
            this.fSymbolTable = new SymbolTable();
        }
        this.fNamespaceValidation = componentManager.getFeature("http://apache.org/xml/features/validation/schema");
        this.fNamespaceContext.reset();
        this.fNamespaceContext.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
        this.fNamespaceCounter = 1;
        if (this.fValidationHandler != null) {
            ((XMLComponent)this.fValidationHandler).reset(componentManager);
        }
    }
    
    protected void setValidationHandler(final RevalidationHandler validator) {
        this.fValidationHandler = validator;
    }
    
    protected void normalizeDocument(final CoreDocumentImpl document) {
        if (this.fSymbolTable == null) {
            return;
        }
        this.fDocument = document;
        this.fErrorHandler = this.fDocument.getErrorHandler();
        if (this.fValidationHandler != null) {
            if (this.fDocument instanceof PSVIDocumentImpl) {
                this.fPSVI = true;
            }
            this.fValidationHandler.setBaseURI(this.fDocument.fDocumentURI);
            this.fValidationHandler.setDocumentHandler(this);
            this.fValidationHandler.startDocument(null, this.fDocument.encoding, this.fNamespaceContext, null);
        }
        Node next;
        for (Node kid = this.fDocument.getFirstChild(); kid != null; kid = next) {
            next = kid.getNextSibling();
            kid = this.normalizeNode(kid);
            if (kid != null) {
                next = kid;
            }
        }
        if (this.fValidationHandler != null) {
            this.fValidationHandler.endDocument(null);
        }
        this.fSymbolTable = null;
    }
    
    protected Node normalizeNode(Node node) {
        final int type = node.getNodeType();
        switch (type) {
            case 10: {
                if ((this.fDocument.features & 0x4) == 0x0) {
                    ((DocumentTypeImpl)node).entities.removeAll();
                    break;
                }
                break;
            }
            case 1: {
                this.fNamespaceContext.pushContext();
                final ElementImpl elem = (ElementImpl)node;
                if (elem.needsSyncChildren()) {
                    elem.synchronizeChildren();
                }
                final AttributeMap attributes = elem.hasAttributes() ? ((AttributeMap)elem.getAttributes()) : null;
                if ((this.fDocument.features & 0x1) != 0x0) {
                    this.namespaceFixUp(elem, attributes);
                }
                else if (attributes != null) {
                    for (int i = 0; i < attributes.getLength(); ++i) {
                        final Attr attr = (Attr)attributes.item(i);
                        this.removeDefault(attr, attributes);
                        attr.normalize();
                    }
                }
                if (this.fValidationHandler != null) {
                    this.fAttrProxy.setAttributes(attributes, this.fDocument, elem);
                    this.updateQName(elem, this.fQName);
                    this.fDocument.fErrorHandlerWrapper.fCurrentNode = node;
                    this.fCurrentNode = node;
                    this.fValidationHandler.startElement(this.fQName, this.fAttrProxy, null);
                }
                Node next;
                for (Node kid = elem.getFirstChild(); kid != null; kid = next) {
                    next = kid.getNextSibling();
                    kid = this.normalizeNode(kid);
                    if (kid != null) {
                        next = kid;
                    }
                }
                if (this.fValidationHandler != null) {
                    this.updateQName(elem, this.fQName);
                    this.fDocument.fErrorHandlerWrapper.fCurrentNode = node;
                    this.fCurrentNode = node;
                    this.fValidationHandler.endElement(this.fQName, null);
                }
                this.fNamespaceContext.popContext();
                break;
            }
            case 8: {
                if ((this.fDocument.features & 0x40) == 0x0) {
                    final Node prevSibling = node.getPreviousSibling();
                    final Node parent = node.getParentNode();
                    parent.removeChild(node);
                    if (prevSibling != null && prevSibling.getNodeType() == 3) {
                        final Node nextSibling = prevSibling.getNextSibling();
                        if (nextSibling != null && nextSibling.getNodeType() == 3) {
                            ((TextImpl)nextSibling).insertData(0, prevSibling.getNodeValue());
                            parent.removeChild(prevSibling);
                            return nextSibling;
                        }
                    }
                    break;
                }
                break;
            }
            case 5: {
                if ((this.fDocument.features & 0x4) != 0x0) {
                    break;
                }
                final Node prevSibling = node.getPreviousSibling();
                final Node parent = node.getParentNode();
                ((EntityReferenceImpl)node).setReadOnly(false, true);
                this.expandEntityRef(node, parent, node);
                parent.removeChild(node);
                final Node next2 = (prevSibling != null) ? prevSibling.getNextSibling() : parent.getFirstChild();
                if (prevSibling != null && prevSibling.getNodeType() == 3 && next2.getNodeType() == 3) {
                    return prevSibling;
                }
                return next2;
            }
            case 4: {
                if ((this.fDocument.features & 0x8) == 0x0) {
                    final Text text = this.fDocument.createTextNode(node.getNodeValue());
                    final Node parent = node.getParentNode();
                    final Node prevSibling2 = node.getPreviousSibling();
                    node = parent.replaceChild(text, node);
                    if (prevSibling2 != null && prevSibling2.getNodeType() == 3) {
                        text.insertData(0, prevSibling2.getNodeValue());
                        parent.removeChild(prevSibling2);
                    }
                    return text;
                }
                if (this.fValidationHandler != null) {
                    this.fDocument.fErrorHandlerWrapper.fCurrentNode = node;
                    this.fCurrentNode = node;
                    this.fValidationHandler.startCDATA(null);
                    this.fValidationHandler.characterData(node.getNodeValue(), null);
                    this.fValidationHandler.endCDATA(null);
                }
                if ((this.fDocument.features & 0x20) != 0x0) {
                    String value = node.getNodeValue();
                    int index = value.indexOf("]]>");
                    if (index >= 0) {}
                    final Node parent2 = node.getParentNode();
                    while (index >= 0) {
                        node.setNodeValue(value.substring(0, index + 2));
                        value = value.substring(index + 2);
                        node = this.fDocument.createCDATASection(value);
                        parent2.insertBefore(node, node.getNextSibling());
                        index = value.indexOf("]]>");
                    }
                    break;
                }
                break;
            }
            case 3: {
                final Node next3 = node.getNextSibling();
                if (next3 != null && next3.getNodeType() == 3) {
                    ((Text)node).appendData(next3.getNodeValue());
                    node.getParentNode().removeChild(next3);
                    return node;
                }
                if (node.getNodeValue().length() == 0) {
                    node.getParentNode().removeChild(node);
                    break;
                }
                if (this.fValidationHandler != null) {
                    final short nextType = (short)((next3 != null) ? next3.getNodeType() : -1);
                    if (((this.fDocument.features & 0x4) != 0x0 || nextType != 6) && ((this.fDocument.features & 0x40) != 0x0 || nextType != 8) && ((this.fDocument.features & 0x8) != 0x0 || nextType != 4)) {
                        this.fDocument.fErrorHandlerWrapper.fCurrentNode = node;
                        this.fCurrentNode = node;
                        this.fValidationHandler.characterData(node.getNodeValue(), null);
                    }
                    break;
                }
                break;
            }
        }
        return null;
    }
    
    protected final void expandEntityRef(final Node node, final Node parent, final Node reference) {
        Node next;
        for (Node kid = node.getFirstChild(); kid != null; kid = next) {
            next = kid.getNextSibling();
            if (node.getNodeType() == 3) {
                this.expandEntityRef(kid, parent, reference);
            }
            else {
                parent.insertBefore(kid, reference);
            }
        }
    }
    
    protected final void namespaceFixUp(final ElementImpl element, final AttributeMap attributes) {
        if (attributes != null) {
            for (int k = 0; k < attributes.getLength(); ++k) {
                final Attr attr = (Attr)attributes.getItem(k);
                final String uri = attr.getNamespaceURI();
                if (uri != null && uri.equals(NamespaceContext.XMLNS_URI)) {
                    String value = attr.getNodeValue();
                    if (value == null) {
                        value = XMLSymbols.EMPTY_STRING;
                    }
                    if (value.equals(NamespaceContext.XMLNS_URI)) {
                        if (this.fErrorHandler != null) {
                            this.modifyDOMError("No prefix other than 'xmlns' can be bound to 'http://www.w3.org/2000/xmlns/' namespace name", (short)1, attr);
                            final boolean continueProcess = this.fErrorHandler.handleError(this.fDOMError);
                            if (!continueProcess) {
                                throw new RuntimeException("Stopped at user request");
                            }
                        }
                    }
                    else {
                        String prefix = attr.getPrefix();
                        prefix = ((prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix));
                        final String localpart = this.fSymbolTable.addSymbol(attr.getLocalName());
                        if (prefix == XMLSymbols.PREFIX_XMLNS) {
                            value = this.fSymbolTable.addSymbol(value);
                            if (value.length() != 0) {
                                this.fNamespaceContext.declarePrefix(localpart, value);
                                this.fLocalNSBinder.declarePrefix(localpart, value);
                            }
                            this.removeDefault(attr, attributes);
                        }
                        else {
                            value = this.fSymbolTable.addSymbol(value);
                            this.fLocalNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, value);
                            this.fNamespaceContext.declarePrefix(XMLSymbols.EMPTY_STRING, value);
                            if (this.fValidationHandler != null) {
                                this.fValidationHandler.startPrefixMapping(XMLSymbols.EMPTY_STRING, value, null);
                            }
                            this.removeDefault(attr, attributes);
                        }
                    }
                }
            }
        }
        String uri = element.getNamespaceURI();
        String prefix = element.getPrefix();
        if (uri != null) {
            uri = this.fSymbolTable.addSymbol(uri);
            prefix = ((prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix));
            if (this.fNamespaceContext.getURI(prefix) != uri) {
                this.addNamespaceDecl(prefix, uri, element);
                this.fLocalNSBinder.declarePrefix(prefix, uri);
                this.fNamespaceContext.declarePrefix(prefix, uri);
            }
        }
        else {
            final String tagName = element.getNodeName();
            final int colon = tagName.indexOf(58);
            if (colon > -1) {
                boolean continueProcess = true;
                if (this.fErrorHandler != null) {
                    if (this.fNamespaceValidation) {
                        this.modifyDOMError("DOM Level 1 node: " + tagName, (short)2, element);
                        this.fErrorHandler.handleError(this.fDOMError);
                    }
                    else {
                        this.modifyDOMError("DOM Level 1 node: " + tagName, (short)1, element);
                        continueProcess = this.fErrorHandler.handleError(this.fDOMError);
                    }
                }
                if (this.fNamespaceValidation || !continueProcess) {
                    throw new RuntimeException("DOM Level 1 node: " + tagName);
                }
            }
            else {
                uri = this.fNamespaceContext.getURI(XMLSymbols.EMPTY_STRING);
                if (uri != null && uri.length() > 0) {
                    this.addNamespaceDecl(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING, element);
                    this.fLocalNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                    this.fNamespaceContext.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                }
            }
        }
        if (attributes != null) {
            attributes.cloneMap(this.fAttributeList);
            for (int i = 0; i < this.fAttributeList.size(); ++i) {
                final Attr attr = this.fAttributeList.elementAt(i);
                attr.normalize();
                String value = attr.getValue();
                String name = attr.getNodeName();
                uri = attr.getNamespaceURI();
                if (value == null) {
                    value = XMLSymbols.EMPTY_STRING;
                }
                if (uri != null) {
                    prefix = attr.getPrefix();
                    prefix = ((prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix));
                    final String localpart = this.fSymbolTable.addSymbol(attr.getLocalName());
                    if (uri == null || !uri.equals(NamespaceContext.XMLNS_URI)) {
                        if (!this.removeDefault(attr, attributes)) {
                            uri = this.fSymbolTable.addSymbol(uri);
                            final String declaredURI = this.fNamespaceContext.getURI(prefix);
                            if (prefix == XMLSymbols.EMPTY_STRING || declaredURI != uri) {
                                name = attr.getNodeName();
                                final String declaredPrefix = this.fNamespaceContext.getPrefix(uri);
                                if (declaredPrefix != null && declaredPrefix != XMLSymbols.EMPTY_STRING) {
                                    prefix = declaredPrefix;
                                }
                                else {
                                    if (prefix == XMLSymbols.EMPTY_STRING || this.fLocalNSBinder.getURI(prefix) != null) {
                                        for (prefix = this.fSymbolTable.addSymbol("NS" + this.fNamespaceCounter++); this.fLocalNSBinder.getURI(prefix) != null; prefix = this.fSymbolTable.addSymbol("NS" + this.fNamespaceCounter++)) {}
                                    }
                                    this.addNamespaceDecl(prefix, uri, element);
                                    value = this.fSymbolTable.addSymbol(value);
                                    this.fLocalNSBinder.declarePrefix(prefix, value);
                                    this.fNamespaceContext.declarePrefix(prefix, uri);
                                }
                                attr.setPrefix(prefix);
                            }
                        }
                    }
                }
                else {
                    final int colon2 = name.indexOf(58);
                    if (colon2 > -1) {
                        boolean continueProcess2 = true;
                        if (this.fErrorHandler != null) {
                            if (this.fNamespaceValidation) {
                                this.modifyDOMError("DOM Level 1 node: " + name, (short)2, attr);
                                this.fErrorHandler.handleError(this.fDOMError);
                            }
                            else {
                                this.modifyDOMError("DOM Level 1 node: " + name, (short)1, attr);
                                continueProcess2 = this.fErrorHandler.handleError(this.fDOMError);
                            }
                        }
                        if (this.fNamespaceValidation || !continueProcess2) {
                            throw new RuntimeException("DOM Level 1 node");
                        }
                    }
                    else {
                        this.removeDefault(attr, attributes);
                    }
                }
            }
        }
    }
    
    protected final void addNamespaceDecl(final String prefix, final String uri, final ElementImpl element) {
        if (prefix == XMLSymbols.EMPTY_STRING) {
            element.setAttributeNS(NamespaceContext.XMLNS_URI, XMLSymbols.PREFIX_XMLNS, uri);
        }
        else {
            element.setAttributeNS(NamespaceContext.XMLNS_URI, "xmlns:" + prefix, uri);
        }
    }
    
    protected final boolean removeDefault(final Attr attribute, final AttributeMap attrMap) {
        if ((this.fDocument.features & 0x10) != 0x0 && !attribute.getSpecified()) {
            attrMap.removeItem(attribute, false);
            return true;
        }
        return false;
    }
    
    protected final DOMError modifyDOMError(final String message, final short severity, final Node node) {
        this.fDOMError.reset();
        this.fDOMError.fMessage = message;
        this.fDOMError.fSeverity = severity;
        this.fDOMError.fLocator = this.fLocator;
        this.fLocator.fErrorNode = node;
        return this.fDOMError;
    }
    
    protected final void updateQName(final Node node, final QName qname) {
        final String prefix = node.getPrefix();
        final String namespace = node.getNamespaceURI();
        final String localName = node.getLocalName();
        qname.prefix = ((prefix != null && prefix.length() != 0) ? this.fSymbolTable.addSymbol(prefix) : null);
        qname.localpart = ((localName != null) ? this.fSymbolTable.addSymbol(localName) : null);
        qname.rawname = this.fSymbolTable.addSymbol(node.getNodeName());
        qname.uri = ((namespace != null) ? this.fSymbolTable.addSymbol(namespace) : null);
    }
    
    public Grammar[] retrieveInitialGrammarSet(final String grammarType) {
        return null;
    }
    
    public void cacheGrammars(final String grammarType, final Grammar[] grammars) {
    }
    
    public Grammar retrieveGrammar(final XMLGrammarDescription desc) {
        return null;
    }
    
    public void lockPool() {
    }
    
    public void unlockPool() {
    }
    
    public void clear() {
    }
    
    public void startDocument(final XMLLocator locator, final String encoding, final NamespaceContext namespaceContext, final Augmentations augs) throws XNIException {
    }
    
    public void xmlDecl(final String version, final String encoding, final String standalone, final Augmentations augs) throws XNIException {
    }
    
    public void doctypeDecl(final String rootElement, final String publicId, final String systemId, final Augmentations augs) throws XNIException {
    }
    
    public void comment(final XMLString text, final Augmentations augs) throws XNIException {
    }
    
    public void processingInstruction(final String target, final XMLString data, final Augmentations augs) throws XNIException {
    }
    
    public void startPrefixMapping(final String prefix, final String uri, final Augmentations augs) throws XNIException {
    }
    
    public void startElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        if (this.fPSVI) {
            final Element currentElement = (Element)this.fCurrentNode;
            for (int attrCount = attributes.getLength(), i = 0; i < attrCount; ++i) {
                attributes.getName(i, this.fAttrQName);
                Attr attr = null;
                attr = currentElement.getAttributeNodeNS(this.fAttrQName.uri, this.fAttrQName.localpart);
                final AttributePSVI attrPSVI = (AttributePSVI)attributes.getAugmentations(i).getItem("ATTRIBUTE_PSVI");
                if (attrPSVI != null) {
                    ((PSVIAttrNSImpl)attr).setPSVI(attrPSVI);
                }
            }
        }
    }
    
    public void emptyElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        this.startElement(element, attributes, augs);
        this.endElement(element, augs);
    }
    
    public void startGeneralEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
    }
    
    public void textDecl(final String version, final String encoding, final Augmentations augs) throws XNIException {
    }
    
    public void endGeneralEntity(final String name, final Augmentations augs) throws XNIException {
    }
    
    public void characters(final XMLString text, final Augmentations augs) throws XNIException {
    }
    
    public void ignorableWhitespace(final XMLString text, final Augmentations augs) throws XNIException {
    }
    
    public void endElement(final QName element, final Augmentations augs) throws XNIException {
        if (this.fPSVI) {
            final ElementPSVI elementPSVI = (ElementPSVI)augs.getItem("ELEMENT_PSVI");
            if (elementPSVI != null) {
                ((PSVIElementNSImpl)this.fCurrentNode).setPSVI(elementPSVI);
                if (elementPSVI.getIsSchemaSpecified()) {
                    this.fCurrentNode.setNodeValue(elementPSVI.getSchemaDefault());
                }
            }
        }
    }
    
    public void endPrefixMapping(final String prefix, final Augmentations augs) throws XNIException {
    }
    
    public void startCDATA(final Augmentations augs) throws XNIException {
    }
    
    public void endCDATA(final Augmentations augs) throws XNIException {
    }
    
    public void endDocument(final Augmentations augs) throws XNIException {
    }
    
    public void setDocumentSource(final XMLDocumentSource source) {
    }
    
    public XMLDocumentSource getDocumentSource() {
        return null;
    }
    
    protected final class XMLAttributesProxy implements XMLAttributes
    {
        protected AttributeMap fAttributes;
        protected CoreDocumentImpl fDocument;
        protected ElementImpl fElement;
        protected final Vector fAugmentations;
        
        protected XMLAttributesProxy() {
            this.fAugmentations = new Vector(5);
        }
        
        public void setAttributes(final AttributeMap attributes, final CoreDocumentImpl doc, final ElementImpl elem) {
            this.fDocument = doc;
            this.fAttributes = attributes;
            this.fElement = elem;
            if (attributes != null) {
                final int length = attributes.getLength();
                this.fAugmentations.setSize(length);
                for (int i = 0; i < length; ++i) {
                    this.fAugmentations.setElementAt(new AugmentationsImpl(), i);
                }
            }
            else {
                this.fAugmentations.setSize(0);
            }
        }
        
        public int addAttribute(final QName attrQName, final String attrType, final String attrValue) {
            final Attr attr = this.fDocument.createAttributeNS(attrQName.uri, attrQName.rawname, attrQName.localpart);
            attr.setValue(attrValue);
            if (this.fAttributes == null) {
                this.fAttributes = (AttributeMap)this.fElement.getAttributes();
            }
            final int index = this.fElement.setXercesAttributeNode(attr);
            this.fAugmentations.insertElementAt(new AugmentationsImpl(), index);
            return index;
        }
        
        public void removeAllAttributes() {
        }
        
        public void removeAttributeAt(final int attrIndex) {
        }
        
        public int getLength() {
            return (this.fAttributes != null) ? this.fAttributes.getLength() : 0;
        }
        
        public int getIndex(final String qName) {
            return -1;
        }
        
        public int getIndex(final String uri, final String localPart) {
            return -1;
        }
        
        public void setName(final int attrIndex, final QName attrName) {
        }
        
        public void getName(final int attrIndex, final QName attrName) {
            if (this.fAttributes != null) {
                DOMNormalizer.this.updateQName((Node)this.fAttributes.getItem(attrIndex), attrName);
            }
        }
        
        public String getPrefix(final int index) {
            return null;
        }
        
        public String getURI(final int index) {
            return null;
        }
        
        public String getLocalName(final int index) {
            return null;
        }
        
        public String getQName(final int index) {
            return null;
        }
        
        public void setType(final int attrIndex, final String attrType) {
        }
        
        public String getType(final int index) {
            return "CDATA";
        }
        
        public String getType(final String qName) {
            return "CDATA";
        }
        
        public String getType(final String uri, final String localName) {
            return "CDATA";
        }
        
        public void setValue(final int attrIndex, final String attrValue) {
        }
        
        public String getValue(final int index) {
            return this.fAttributes.item(index).getNodeValue();
        }
        
        public String getValue(final String qName) {
            return null;
        }
        
        public String getValue(final String uri, final String localName) {
            if (this.fAttributes != null) {
                final Node node = this.fAttributes.getNamedItemNS(uri, localName);
                return (node != null) ? node.getNodeValue() : null;
            }
            return null;
        }
        
        public void setNonNormalizedValue(final int attrIndex, final String attrValue) {
        }
        
        public String getNonNormalizedValue(final int attrIndex) {
            return null;
        }
        
        public void setSpecified(final int attrIndex, final boolean specified) {
            final AttrImpl attr = (AttrImpl)this.fAttributes.getItem(attrIndex);
            attr.setSpecified(specified);
        }
        
        public boolean isSpecified(final int attrIndex) {
            return ((Attr)this.fAttributes.getItem(attrIndex)).getSpecified();
        }
        
        public Augmentations getAugmentations(final int attributeIndex) {
            return this.fAugmentations.elementAt(attributeIndex);
        }
        
        public Augmentations getAugmentations(final String uri, final String localPart) {
            return null;
        }
        
        public Augmentations getAugmentations(final String qName) {
            return null;
        }
    }
}
