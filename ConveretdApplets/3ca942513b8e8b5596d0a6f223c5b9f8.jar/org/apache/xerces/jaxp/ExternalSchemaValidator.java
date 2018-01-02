// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.dom.DOMInputImpl;
import java.io.IOException;
import org.apache.xerces.util.XMLResourceIdentifierImpl;
import org.w3c.dom.ls.LSInput;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.xml.sax.SAXParseException;
import org.apache.xerces.util.AugmentationsImpl;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.xml.sax.Attributes;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLString;
import org.xml.sax.SAXException;
import org.apache.xerces.xni.XNIException;
import org.xml.sax.Locator;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.w3c.dom.ls.LSResourceResolver;
import org.apache.xerces.xni.parser.XMLComponentManager;
import javax.xml.validation.Schema;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import javax.xml.validation.ValidatorHandler;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLDocumentFilter;

final class ExternalSchemaValidator implements XMLDocumentFilter, XMLComponent
{
    private ValidatorHandler fValidatorHandler;
    private XMLDocumentHandler fHandler;
    private XMLDocumentSource fDocumentSource;
    private XMLEntityResolver fEntityResolver;
    private XMLErrorHandler fErrorHandler;
    private SymbolTable fSymbolTable;
    private AugsHandler fAugsHandler;
    private Augmentations fSavedAugs;
    private NamespaceContext fNamespaceContext;
    private AttributesProxy fAttributesProxy;
    private XMLAttributes fSavedAttributes;
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private String[] RECOGNIZED_PROPERTIES;
    
    public ExternalSchemaValidator(final Schema schema, final XMLComponentManager xmlComponentManager) {
        this.fAugsHandler = new AugsHandler();
        this.fAttributesProxy = new AttributesProxy();
        this.RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/symbol-table" };
        (this.fValidatorHandler = schema.newValidatorHandler()).setResourceResolver(new LSResourceResolverProxy());
        this.fValidatorHandler.setErrorHandler(new ErrorHandlerProxy());
        this.fValidatorHandler.setContentHandler(this.fAugsHandler);
        this.reset(xmlComponentManager);
    }
    
    public void reset(final XMLComponentManager xmlComponentManager) throws XMLConfigurationException {
        try {
            this.fErrorHandler = (XMLErrorHandler)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/error-handler");
        }
        catch (XMLConfigurationException ex) {
            this.fErrorHandler = null;
        }
        try {
            this.fEntityResolver = (XMLEntityResolver)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
        }
        catch (XMLConfigurationException ex2) {
            this.fEntityResolver = null;
        }
        this.fSymbolTable = (SymbolTable)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
    }
    
    public String[] getRecognizedFeatures() {
        return null;
    }
    
    public void setFeature(final String s, final boolean b) throws XMLConfigurationException {
    }
    
    public String[] getRecognizedProperties() {
        return this.RECOGNIZED_PROPERTIES;
    }
    
    public void setProperty(final String s, final Object o) throws XMLConfigurationException {
        if (s.equals("http://apache.org/xml/properties/internal/error-handler")) {
            this.fErrorHandler = (XMLErrorHandler)o;
        }
        else if (s.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityResolver = (XMLEntityResolver)o;
        }
        else if (s.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            this.fSymbolTable = (SymbolTable)o;
        }
    }
    
    public Boolean getFeatureDefault(final String s) {
        return null;
    }
    
    public Object getPropertyDefault(final String s) {
        return null;
    }
    
    public void startDocument(final XMLLocator xmlLocator, final String s, final NamespaceContext fNamespaceContext, final Augmentations augmentations) throws XNIException {
        this.fNamespaceContext = fNamespaceContext;
        this.fValidatorHandler.setDocumentLocator(new LocatorProxy(xmlLocator));
        try {
            this.fValidatorHandler.startDocument();
        }
        catch (SAXException ex) {
            throw new XNIException(ex);
        }
        if (this.fHandler != null) {
            this.fHandler.startDocument(xmlLocator, s, fNamespaceContext, augmentations);
        }
    }
    
    public void xmlDecl(final String s, final String s2, final String s3, final Augmentations augmentations) throws XNIException {
        if (this.fHandler != null) {
            this.fHandler.xmlDecl(s, s2, s3, augmentations);
        }
    }
    
    public void doctypeDecl(final String s, final String s2, final String s3, final Augmentations augmentations) throws XNIException {
        if (this.fHandler != null) {
            this.fHandler.doctypeDecl(s, s2, s3, augmentations);
        }
    }
    
    public void comment(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fHandler != null) {
            this.fHandler.comment(xmlString, augmentations);
        }
    }
    
    public void processingInstruction(final String s, final XMLString xmlString, final Augmentations fSavedAugs) throws XNIException {
        this.fSavedAugs = fSavedAugs;
        try {
            this.fValidatorHandler.processingInstruction(s, (xmlString != null) ? xmlString.toString() : null);
        }
        catch (SAXException ex) {
            throw new XNIException(ex);
        }
    }
    
    public void startElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations fSavedAugs) throws XNIException {
        this.fSavedAugs = fSavedAugs;
        this.fSavedAttributes = xmlAttributes;
        this.fAttributesProxy.setAttributes(xmlAttributes);
        try {
            this.startNamespaceMapping();
            this.fValidatorHandler.startElement((qName.uri != null) ? qName.uri : "", (qName.localpart != null) ? qName.localpart : "", (qName.rawname != null) ? qName.rawname : "", this.fAttributesProxy);
        }
        catch (SAXException ex) {
            throw new XNIException(ex);
        }
    }
    
    public void emptyElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) throws XNIException {
        this.startElement(qName, xmlAttributes, augmentations);
        this.endElement(qName, augmentations);
    }
    
    public void startGeneralEntity(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final String s2, final Augmentations augmentations) throws XNIException {
        if (this.fHandler != null) {
            this.fHandler.startGeneralEntity(s, xmlResourceIdentifier, s2, augmentations);
        }
    }
    
    public void textDecl(final String s, final String s2, final Augmentations augmentations) throws XNIException {
        if (this.fHandler != null) {
            this.fHandler.textDecl(s, s2, augmentations);
        }
    }
    
    public void endGeneralEntity(final String s, final Augmentations augmentations) throws XNIException {
        if (this.fHandler != null) {
            this.fHandler.endGeneralEntity(s, augmentations);
        }
    }
    
    public void characters(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        try {
            this.fValidatorHandler.characters(xmlString.ch, xmlString.offset, xmlString.length);
        }
        catch (SAXException ex) {
            throw new XNIException(ex);
        }
    }
    
    public void ignorableWhitespace(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        try {
            this.fValidatorHandler.ignorableWhitespace(xmlString.ch, xmlString.offset, xmlString.length);
        }
        catch (SAXException ex) {
            throw new XNIException(ex);
        }
    }
    
    public void endElement(final QName qName, final Augmentations augmentations) throws XNIException {
        try {
            this.fValidatorHandler.endElement((qName.uri != null) ? qName.uri : XMLSymbols.EMPTY_STRING, (qName.localpart != null) ? qName.localpart : XMLSymbols.EMPTY_STRING, (qName.rawname != null) ? qName.rawname : XMLSymbols.EMPTY_STRING);
            this.endNamespaceMapping();
        }
        catch (SAXException ex) {
            throw new XNIException(ex);
        }
    }
    
    public void startCDATA(final Augmentations augmentations) throws XNIException {
        if (this.fHandler != null) {
            this.fHandler.startCDATA(augmentations);
        }
    }
    
    public void endCDATA(final Augmentations augmentations) throws XNIException {
        if (this.fHandler != null) {
            this.fHandler.endCDATA(augmentations);
        }
    }
    
    public void endDocument(final Augmentations augmentations) throws XNIException {
        try {
            this.fValidatorHandler.endDocument();
        }
        catch (SAXException ex) {
            throw new XNIException(ex);
        }
        if (this.fHandler != null) {
            this.fHandler.endDocument(augmentations);
        }
    }
    
    private final void startNamespaceMapping() throws SAXException {
        final int declaredPrefixCount = this.fNamespaceContext.getDeclaredPrefixCount();
        if (declaredPrefixCount > 0) {
            for (int i = 0; i < declaredPrefixCount; ++i) {
                final String declaredPrefix = this.fNamespaceContext.getDeclaredPrefixAt(i);
                final String uri = this.fNamespaceContext.getURI(declaredPrefix);
                this.fValidatorHandler.startPrefixMapping(declaredPrefix, (uri == null) ? XMLSymbols.EMPTY_STRING : uri);
            }
        }
    }
    
    private final void endNamespaceMapping() throws SAXException {
        final int declaredPrefixCount = this.fNamespaceContext.getDeclaredPrefixCount();
        if (declaredPrefixCount > 0) {
            for (int i = 0; i < declaredPrefixCount; ++i) {
                this.fValidatorHandler.endPrefixMapping(this.fNamespaceContext.getDeclaredPrefixAt(i));
            }
        }
    }
    
    public void setDocumentSource(final XMLDocumentSource fDocumentSource) {
        this.fDocumentSource = fDocumentSource;
    }
    
    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }
    
    public void setDocumentHandler(final XMLDocumentHandler fHandler) {
        this.fHandler = fHandler;
    }
    
    public XMLDocumentHandler getDocumentHandler() {
        return this.fHandler;
    }
    
    final class AugsHandler extends DefaultHandler
    {
        private Augmentations fAugs;
        private QName fQName;
        private XMLString fString;
        
        AugsHandler() {
            this.fAugs = new AugmentationsImpl();
            this.fQName = new QName();
            this.fString = new XMLString();
        }
        
        public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
            this.compareAttrList(attributes);
            if (ExternalSchemaValidator.this.fHandler != null) {
                ExternalSchemaValidator.this.fHandler.startElement(this.getQName(s, s2, s3), ExternalSchemaValidator.this.fSavedAttributes, this.getAugs());
            }
        }
        
        public void characters(final char[] array, final int n, final int n2) throws SAXException {
            if (ExternalSchemaValidator.this.fHandler != null) {
                ExternalSchemaValidator.this.fHandler.characters(this.getXMLString(array, n, n2), this.getAugs());
            }
        }
        
        private XMLString getXMLString(final char[] array, final int n, final int n2) {
            this.fString.setValues(array, n, n2);
            return this.fString;
        }
        
        public void endElement(final String s, final String s2, final String s3) throws SAXException {
            if (ExternalSchemaValidator.this.fHandler != null) {
                ExternalSchemaValidator.this.fHandler.endElement(this.getQName(s, s2, s3), this.getAugs());
            }
        }
        
        public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
            if (ExternalSchemaValidator.this.fHandler != null) {
                ExternalSchemaValidator.this.fHandler.ignorableWhitespace(this.getXMLString(array, n, n2), this.getAugs());
            }
        }
        
        public void processingInstruction(final String s, final String s2) throws SAXException {
            if (ExternalSchemaValidator.this.fHandler != null) {
                ExternalSchemaValidator.this.fHandler.processingInstruction(s, this.getXMLString(s2.toCharArray(), 0, s2.length()), this.getAugs());
            }
        }
        
        private void compareAttrList(final Attributes attributes) {
            for (int i = 0; i < attributes.getLength(); ++i) {
                final String qName = attributes.getQName(i);
                final int index = ExternalSchemaValidator.this.fSavedAttributes.getIndex(qName);
                if (index != -1) {
                    final String value = attributes.getValue(i);
                    if (!value.equals(ExternalSchemaValidator.this.fSavedAttributes.getValue(index))) {
                        ExternalSchemaValidator.this.fSavedAttributes.setValue(index, value);
                    }
                }
                else {
                    ExternalSchemaValidator.this.fSavedAttributes.addAttribute(this.getQName(attributes.getURI(i), attributes.getLocalName(i), qName), attributes.getType(i), attributes.getValue(i));
                }
            }
        }
        
        private Augmentations getAugs() {
            if (ExternalSchemaValidator.this.fSavedAugs != null) {
                final Augmentations access$400 = ExternalSchemaValidator.this.fSavedAugs;
                ExternalSchemaValidator.this.fSavedAugs = null;
                return access$400;
            }
            this.fAugs.removeAllItems();
            return this.fAugs;
        }
        
        private QName getQName(String s, String s2, String s3) {
            s = ((s != null && s.length() > 0) ? ExternalSchemaValidator.this.fSymbolTable.addSymbol(s) : null);
            s2 = ((s2 != null) ? ExternalSchemaValidator.this.fSymbolTable.addSymbol(s2) : XMLSymbols.EMPTY_STRING);
            s3 = ((s3 != null) ? ExternalSchemaValidator.this.fSymbolTable.addSymbol(s3) : XMLSymbols.EMPTY_STRING);
            String s4 = XMLSymbols.EMPTY_STRING;
            final int index = s3.indexOf(58);
            if (index != -1) {
                s4 = ExternalSchemaValidator.this.fSymbolTable.addSymbol(s3.substring(0, index));
            }
            this.fQName.setValues(s4, s2, s3, s);
            return this.fQName;
        }
    }
    
    final class ErrorHandlerProxy implements ErrorHandler
    {
        public void warning(final SAXParseException ex) throws SAXException {
            if (ExternalSchemaValidator.this.fErrorHandler != null) {
                ExternalSchemaValidator.this.fErrorHandler.warning(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING, ErrorHandlerWrapper.createXMLParseException(ex));
            }
        }
        
        public void error(final SAXParseException ex) throws SAXException {
            if (ExternalSchemaValidator.this.fErrorHandler != null) {
                ExternalSchemaValidator.this.fErrorHandler.error(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING, ErrorHandlerWrapper.createXMLParseException(ex));
                return;
            }
            throw ex;
        }
        
        public void fatalError(final SAXParseException ex) throws SAXException {
            if (ExternalSchemaValidator.this.fErrorHandler != null) {
                ExternalSchemaValidator.this.fErrorHandler.fatalError(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING, ErrorHandlerWrapper.createXMLParseException(ex));
                return;
            }
            throw ex;
        }
    }
    
    final class LSResourceResolverProxy implements LSResourceResolver
    {
        public LSInput resolveResource(final String s, final String s2, final String s3, final String s4, final String s5) {
            if (ExternalSchemaValidator.this.fEntityResolver == null) {
                return null;
            }
            XMLInputSource resolveEntity;
            try {
                resolveEntity = ExternalSchemaValidator.this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(s3, s4, s5, s4));
            }
            catch (XNIException ex) {
                throw ex;
            }
            catch (IOException ex2) {
                throw new XNIException(ex2);
            }
            final DOMInputImpl domInputImpl = new DOMInputImpl(resolveEntity.getPublicId(), resolveEntity.getSystemId(), resolveEntity.getBaseSystemId());
            domInputImpl.setByteStream(resolveEntity.getByteStream());
            domInputImpl.setCharacterStream(resolveEntity.getCharacterStream());
            domInputImpl.setEncoding(resolveEntity.getEncoding());
            return domInputImpl;
        }
    }
    
    final class LocatorProxy implements Locator
    {
        private XMLLocator fLocator;
        
        public LocatorProxy(final XMLLocator fLocator) {
            this.fLocator = fLocator;
        }
        
        public String getPublicId() {
            return this.fLocator.getPublicId();
        }
        
        public String getSystemId() {
            return this.fLocator.getExpandedSystemId();
        }
        
        public int getLineNumber() {
            return this.fLocator.getLineNumber();
        }
        
        public int getColumnNumber() {
            return this.fLocator.getColumnNumber();
        }
    }
    
    final class AttributesProxy implements Attributes
    {
        private XMLAttributes fAttributes;
        
        public void setAttributes(final XMLAttributes fAttributes) {
            this.fAttributes = fAttributes;
        }
        
        public int getLength() {
            return this.fAttributes.getLength();
        }
        
        public String getURI(final int n) {
            return this.fAttributes.getURI(n);
        }
        
        public String getLocalName(final int n) {
            return this.fAttributes.getLocalName(n);
        }
        
        public String getQName(final int n) {
            return this.fAttributes.getQName(n);
        }
        
        public String getType(final int n) {
            return this.fAttributes.getType(n);
        }
        
        public String getValue(final int n) {
            return this.fAttributes.getValue(n);
        }
        
        public int getIndex(final String s, final String s2) {
            return this.fAttributes.getIndex(s, s2);
        }
        
        public int getIndex(final String s) {
            return this.fAttributes.getIndex(s);
        }
        
        public String getType(final String s, final String s2) {
            return this.fAttributes.getType(s, s2);
        }
        
        public String getType(final String s) {
            return this.fAttributes.getType(s);
        }
        
        public String getValue(final String s, final String s2) {
            return this.fAttributes.getValue(s, s2);
        }
        
        public String getValue(final String s) {
            return this.fAttributes.getValue(s);
        }
        
        public XMLAttributes getXMLAttributes() {
            return this.fAttributes;
        }
    }
}
