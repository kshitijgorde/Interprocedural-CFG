// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;
import org.apache.xerces.xs.AttributePSVI;
import org.apache.xerces.xs.ElementPSVI;
import java.io.IOException;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.EntityResolver;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.xml.sax.ext.Attributes2;
import org.apache.xerces.xni.parser.XMLParseException;
import org.xml.sax.Locator;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.xml.sax.Attributes;
import org.apache.xerces.util.XMLSymbols;
import org.xml.sax.SAXException;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLLocator;
import org.xml.sax.SAXNotSupportedException;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xerces.util.SAXMessageFormatter;
import java.util.Locale;
import javax.xml.validation.TypeInfoProvider;
import org.apache.xerces.xni.XMLAttributes;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ContentHandler;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.xni.QName;
import java.util.HashMap;
import org.apache.xerces.util.SAXLocatorWrapper;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xs.PSVIProvider;
import org.apache.xerces.impl.validation.EntityState;
import org.xml.sax.DTDHandler;
import javax.xml.validation.ValidatorHandler;

final class XMLSchemaValidatorHandler extends ValidatorHandler implements DTDHandler, EntityState, PSVIProvider, ValidatorHelper, XMLDocumentHandler
{
    private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    protected static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String LEXICAL_HANDLER = "http://xml.org/sax/properties/lexical-handler";
    private static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private XMLErrorReporter fErrorReporter;
    private NamespaceContext fNamespaceContext;
    private XMLSchemaValidator fSchemaValidator;
    private SymbolTable fSymbolTable;
    private ValidationManager fValidationManager;
    private XMLSchemaValidatorComponentManager fComponentManager;
    private final SAXLocatorWrapper fSAXLocatorWrapper;
    private LSResourceResolverAdapter fResourceResolverAdapter;
    private final XMLSchemaTypeInfoProvider fTypeInfoProvider;
    private boolean fNeedPushNSContext;
    private HashMap fUnparsedEntities;
    private boolean fStringsInternalized;
    private final QName fElementQName;
    private final QName fAttributeQName;
    private final XMLAttributesImpl fAttributes;
    private final Attributes2Adapter fAttrAdapter;
    private final XMLString fTempString;
    private ContentHandler fContentHandler;
    
    public XMLSchemaValidatorHandler(final XSGrammarPoolContainer xsGrammarPoolContainer) {
        this(new XMLSchemaValidatorComponentManager(xsGrammarPoolContainer));
        this.fComponentManager.addRecognizedFeatures(new String[] { "http://xml.org/sax/features/namespace-prefixes" });
        this.fComponentManager.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
        this.setErrorHandler(null);
        this.setResourceResolver(null);
    }
    
    public XMLSchemaValidatorHandler(final XMLSchemaValidatorComponentManager fComponentManager) {
        this.fSAXLocatorWrapper = new SAXLocatorWrapper();
        this.fResourceResolverAdapter = new LSResourceResolverAdapter(null);
        this.fTypeInfoProvider = new XMLSchemaTypeInfoProvider();
        this.fNeedPushNSContext = true;
        this.fUnparsedEntities = null;
        this.fStringsInternalized = false;
        this.fElementQName = new QName();
        this.fAttributeQName = new QName();
        this.fAttributes = new XMLAttributesImpl();
        this.fAttrAdapter = new Attributes2Adapter(this.fAttributes);
        this.fTempString = new XMLString();
        this.fContentHandler = null;
        this.fComponentManager = fComponentManager;
        this.fErrorReporter = (XMLErrorReporter)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fNamespaceContext = (NamespaceContext)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/namespace-context");
        this.fSchemaValidator = (XMLSchemaValidator)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validator/schema");
        this.fSymbolTable = (SymbolTable)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fValidationManager = (ValidationManager)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager");
    }
    
    public void setContentHandler(final ContentHandler fContentHandler) {
        this.fContentHandler = fContentHandler;
    }
    
    public ContentHandler getContentHandler() {
        return this.fContentHandler;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.fComponentManager.setErrorHandler(errorHandler);
    }
    
    public ErrorHandler getErrorHandler() {
        return this.fComponentManager.getErrorHandler();
    }
    
    public void setResourceResolver(final LSResourceResolver resourceResolver) {
        this.fComponentManager.setResourceResolver(resourceResolver);
    }
    
    public LSResourceResolver getResourceResolver() {
        return this.fComponentManager.getResourceResolver();
    }
    
    public TypeInfoProvider getTypeInfoProvider() {
        return this.fTypeInfoProvider;
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        try {
            return this.fComponentManager.getFeature(s);
        }
        catch (XMLConfigurationException ex) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), (ex.getType() == 0) ? "feature-not-recognized" : "feature-not-supported", new Object[] { ex.getIdentifier() }));
        }
    }
    
    public void setFeature(final String s, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        try {
            this.fComponentManager.setFeature(s, b);
        }
        catch (XMLConfigurationException ex) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), (ex.getType() == 0) ? "feature-not-recognized" : "feature-not-supported", new Object[] { ex.getIdentifier() }));
        }
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        try {
            return this.fComponentManager.getProperty(s);
        }
        catch (XMLConfigurationException ex) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), (ex.getType() == 0) ? "property-not-recognized" : "property-not-supported", new Object[] { ex.getIdentifier() }));
        }
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        try {
            this.fComponentManager.setProperty(s, o);
        }
        catch (XMLConfigurationException ex) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), (ex.getType() == 0) ? "property-not-recognized" : "property-not-supported", new Object[] { ex.getIdentifier() }));
        }
    }
    
    public boolean isEntityDeclared(final String s) {
        return false;
    }
    
    public boolean isEntityUnparsed(final String s) {
        return this.fUnparsedEntities != null && this.fUnparsedEntities.containsKey(s);
    }
    
    public void startDocument(final XMLLocator xmlLocator, final String s, final NamespaceContext namespaceContext, final Augmentations augmentations) throws XNIException {
        if (this.fContentHandler != null) {
            try {
                this.fContentHandler.startDocument();
            }
            catch (SAXException ex) {
                throw new XNIException(ex);
            }
        }
    }
    
    public void xmlDecl(final String s, final String s2, final String s3, final Augmentations augmentations) throws XNIException {
    }
    
    public void doctypeDecl(final String s, final String s2, final String s3, final Augmentations augmentations) throws XNIException {
    }
    
    public void comment(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
    }
    
    public void processingInstruction(final String s, final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fContentHandler != null) {
            try {
                this.fContentHandler.processingInstruction(s, xmlString.toString());
            }
            catch (SAXException ex) {
                throw new XNIException(ex);
            }
        }
    }
    
    public void startElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) throws XNIException {
        if (this.fContentHandler != null) {
            try {
                this.fTypeInfoProvider.beginStartElement(augmentations, xmlAttributes);
                this.fContentHandler.startElement((qName.uri != null) ? qName.uri : XMLSymbols.EMPTY_STRING, qName.localpart, qName.rawname, this.fAttrAdapter);
            }
            catch (SAXException ex) {
                throw new XNIException(ex);
            }
            finally {
                this.fTypeInfoProvider.finishStartElement();
            }
        }
    }
    
    public void emptyElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) throws XNIException {
        this.startElement(qName, xmlAttributes, augmentations);
        this.endElement(qName, augmentations);
    }
    
    public void startGeneralEntity(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final String s2, final Augmentations augmentations) throws XNIException {
    }
    
    public void textDecl(final String s, final String s2, final Augmentations augmentations) throws XNIException {
    }
    
    public void endGeneralEntity(final String s, final Augmentations augmentations) throws XNIException {
    }
    
    public void characters(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fContentHandler != null) {
            if (xmlString.length == 0) {
                return;
            }
            try {
                this.fContentHandler.characters(xmlString.ch, xmlString.offset, xmlString.length);
            }
            catch (SAXException ex) {
                throw new XNIException(ex);
            }
        }
    }
    
    public void ignorableWhitespace(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fContentHandler != null) {
            try {
                this.fContentHandler.ignorableWhitespace(xmlString.ch, xmlString.offset, xmlString.length);
            }
            catch (SAXException ex) {
                throw new XNIException(ex);
            }
        }
    }
    
    public void endElement(final QName qName, final Augmentations augmentations) throws XNIException {
        if (this.fContentHandler != null) {
            try {
                this.fTypeInfoProvider.beginEndElement(augmentations);
                this.fContentHandler.endElement((qName.uri != null) ? qName.uri : XMLSymbols.EMPTY_STRING, qName.localpart, qName.rawname);
            }
            catch (SAXException ex) {
                throw new XNIException(ex);
            }
            finally {
                this.fTypeInfoProvider.finishEndElement();
            }
        }
    }
    
    public void startCDATA(final Augmentations augmentations) throws XNIException {
    }
    
    public void endCDATA(final Augmentations augmentations) throws XNIException {
    }
    
    public void endDocument(final Augmentations augmentations) throws XNIException {
        if (this.fContentHandler != null) {
            try {
                this.fContentHandler.endDocument();
            }
            catch (SAXException ex) {
                throw new XNIException(ex);
            }
        }
    }
    
    public void setDocumentSource(final XMLDocumentSource xmlDocumentSource) {
    }
    
    public XMLDocumentSource getDocumentSource() {
        return this.fSchemaValidator;
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.fSAXLocatorWrapper.setLocator(locator);
        if (this.fContentHandler != null) {
            this.fContentHandler.setDocumentLocator(locator);
        }
    }
    
    public void startDocument() throws SAXException {
        this.fComponentManager.reset();
        this.fSchemaValidator.setDocumentHandler(this);
        this.fValidationManager.setEntityState(this);
        this.fTypeInfoProvider.finishStartElement();
        this.fNeedPushNSContext = true;
        if (this.fUnparsedEntities != null && !this.fUnparsedEntities.isEmpty()) {
            this.fUnparsedEntities.clear();
        }
        this.fErrorReporter.setDocumentLocator(this.fSAXLocatorWrapper);
        try {
            this.fSchemaValidator.startDocument(this.fSAXLocatorWrapper, this.fSAXLocatorWrapper.getEncoding(), this.fNamespaceContext, null);
        }
        catch (XMLParseException ex) {
            convertToSAXParseException(ex);
        }
        catch (XNIException ex2) {
            convertToSAXException(ex2);
        }
    }
    
    public void endDocument() throws SAXException {
        this.fSAXLocatorWrapper.setLocator(null);
        try {
            this.fSchemaValidator.endDocument(null);
        }
        catch (XMLParseException ex) {
            convertToSAXParseException(ex);
        }
        catch (XNIException ex2) {
            convertToSAXException(ex2);
        }
    }
    
    public void startPrefixMapping(final String s, final String s2) throws SAXException {
        String s3;
        String s4;
        if (!this.fStringsInternalized) {
            s3 = ((s != null) ? this.fSymbolTable.addSymbol(s) : XMLSymbols.EMPTY_STRING);
            s4 = ((s2 != null && s2.length() > 0) ? this.fSymbolTable.addSymbol(s2) : null);
        }
        else {
            s3 = ((s != null) ? s : XMLSymbols.EMPTY_STRING);
            s4 = ((s2 != null && s2.length() > 0) ? s2 : null);
        }
        if (this.fNeedPushNSContext) {
            this.fNeedPushNSContext = false;
            this.fNamespaceContext.pushContext();
        }
        this.fNamespaceContext.declarePrefix(s3, s4);
        if (this.fContentHandler != null) {
            this.fContentHandler.startPrefixMapping(s, s2);
        }
    }
    
    public void endPrefixMapping(final String s) throws SAXException {
        if (this.fContentHandler != null) {
            this.fContentHandler.endPrefixMapping(s);
        }
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        if (this.fNeedPushNSContext) {
            this.fNamespaceContext.pushContext();
        }
        this.fNeedPushNSContext = true;
        this.fillQName(this.fElementQName, s, s2, s3);
        if (attributes instanceof Attributes2) {
            this.fillXMLAttributes2((Attributes2)attributes);
        }
        else {
            this.fillXMLAttributes(attributes);
        }
        try {
            this.fSchemaValidator.startElement(this.fElementQName, this.fAttributes, null);
        }
        catch (XMLParseException ex) {
            convertToSAXParseException(ex);
        }
        catch (XNIException ex2) {
            convertToSAXException(ex2);
        }
    }
    
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        this.fillQName(this.fElementQName, s, s2, s3);
        try {
            this.fSchemaValidator.endElement(this.fElementQName, null);
        }
        catch (XMLParseException ex) {
            convertToSAXParseException(ex);
        }
        catch (XNIException ex2) {
            convertToSAXException(ex2);
        }
        finally {
            this.fNamespaceContext.popContext();
        }
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        try {
            this.fTempString.setValues(array, n, n2);
            this.fSchemaValidator.characters(this.fTempString, null);
        }
        catch (XMLParseException ex) {
            convertToSAXParseException(ex);
        }
        catch (XNIException ex2) {
            convertToSAXException(ex2);
        }
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
        try {
            this.fTempString.setValues(array, n, n2);
            this.fSchemaValidator.ignorableWhitespace(this.fTempString, null);
        }
        catch (XMLParseException ex) {
            convertToSAXParseException(ex);
        }
        catch (XNIException ex2) {
            convertToSAXException(ex2);
        }
    }
    
    public void processingInstruction(final String s, final String s2) throws SAXException {
        if (this.fContentHandler != null) {
            this.fContentHandler.processingInstruction(s, s2);
        }
    }
    
    public void skippedEntity(final String s) throws SAXException {
        if (this.fContentHandler != null) {
            this.fContentHandler.skippedEntity(s);
        }
    }
    
    public void notationDecl(final String s, final String s2, final String s3) throws SAXException {
    }
    
    public void unparsedEntityDecl(final String s, final String s2, final String s3, final String s4) throws SAXException {
        if (this.fUnparsedEntities == null) {
            this.fUnparsedEntities = new HashMap();
        }
        this.fUnparsedEntities.put(s, s);
    }
    
    public void validate(final Source source, final Result result) throws SAXException, IOException {
        if (result instanceof SAXResult || result == null) {
            final SAXSource saxSource = (SAXSource)source;
            final SAXResult saxResult = (SAXResult)result;
            XMLReader xmlReader = saxSource.getXMLReader();
            if (xmlReader == null) {
                xmlReader = XMLReaderFactory.createXMLReader();
                if (xmlReader instanceof SAXParser) {
                    final SecurityManager securityManager = (SecurityManager)this.fComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
                    if (securityManager != null) {
                        try {
                            xmlReader.setProperty("http://apache.org/xml/properties/security-manager", securityManager);
                        }
                        catch (SAXException ex) {}
                    }
                }
            }
            try {
                this.fStringsInternalized = xmlReader.getFeature("http://xml.org/sax/features/string-interning");
            }
            catch (SAXException ex2) {
                this.fStringsInternalized = false;
            }
            xmlReader.setContentHandler(this);
            xmlReader.setDTDHandler(this);
            xmlReader.setEntityResolver(this.fResourceResolverAdapter);
            this.fResourceResolverAdapter.setEntityResolver(this.fComponentManager.getResourceResolver());
            final ErrorHandler errorHandler = this.fComponentManager.getErrorHandler();
            xmlReader.setErrorHandler((errorHandler != null) ? errorHandler : DefaultErrorHandler.getInstance());
            ContentHandler handler = null;
            Object lexicalHandler = null;
            if (result != null) {
                handler = saxResult.getHandler();
                lexicalHandler = saxResult.getLexicalHandler();
                if (lexicalHandler == null && handler instanceof LexicalHandler) {
                    lexicalHandler = handler;
                }
            }
            try {
                xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", lexicalHandler);
            }
            catch (SAXException ex3) {}
            try {
                this.setContentHandler(handler);
                InputSource inputSource = saxSource.getInputSource();
                if (inputSource == null) {
                    inputSource = new InputSource(saxSource.getSystemId());
                }
                xmlReader.parse(inputSource);
            }
            finally {
                this.setContentHandler(null);
            }
            return;
        }
        throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SourceResultMismatch", new Object[] { source.getClass().getName(), result.getClass().getName() }));
    }
    
    public ElementPSVI getElementPSVI() {
        return this.fTypeInfoProvider.getElementPSVI();
    }
    
    public AttributePSVI getAttributePSVI(final int n) {
        return this.fTypeInfoProvider.getAttributePSVI(n);
    }
    
    public AttributePSVI getAttributePSVIByName(final String s, final String s2) {
        return this.fTypeInfoProvider.getAttributePSVIByName(s, s2);
    }
    
    private void fillQName(final QName qName, String s, String empty_STRING, String empty_STRING2) {
        if (!this.fStringsInternalized) {
            s = ((s != null && s.length() > 0) ? this.fSymbolTable.addSymbol(s) : null);
            empty_STRING = ((empty_STRING != null) ? this.fSymbolTable.addSymbol(empty_STRING) : XMLSymbols.EMPTY_STRING);
            empty_STRING2 = ((empty_STRING2 != null) ? this.fSymbolTable.addSymbol(empty_STRING2) : XMLSymbols.EMPTY_STRING);
        }
        else {
            if (s != null && s.length() == 0) {
                s = null;
            }
            if (empty_STRING == null) {
                empty_STRING = XMLSymbols.EMPTY_STRING;
            }
            if (empty_STRING2 == null) {
                empty_STRING2 = XMLSymbols.EMPTY_STRING;
            }
        }
        String s2 = XMLSymbols.EMPTY_STRING;
        final int index = empty_STRING2.indexOf(58);
        if (index != -1) {
            s2 = this.fSymbolTable.addSymbol(empty_STRING2.substring(0, index));
        }
        qName.setValues(s2, empty_STRING, empty_STRING2, s);
    }
    
    private void fillXMLAttributes(final Attributes attributes) {
        this.fAttributes.removeAllAttributes();
        for (int length = attributes.getLength(), i = 0; i < length; ++i) {
            this.fillXMLAttribute(attributes, i);
            this.fAttributes.setSpecified(i, true);
        }
    }
    
    private void fillXMLAttributes2(final Attributes2 attributes2) {
        this.fAttributes.removeAllAttributes();
        for (int length = attributes2.getLength(), i = 0; i < length; ++i) {
            this.fillXMLAttribute(attributes2, i);
            this.fAttributes.setSpecified(i, attributes2.isSpecified(i));
            if (attributes2.isDeclared(i)) {
                this.fAttributes.getAugmentations(i).putItem("ATTRIBUTE_DECLARED", Boolean.TRUE);
            }
        }
    }
    
    private void fillXMLAttribute(final Attributes attributes, final int n) {
        this.fillQName(this.fAttributeQName, attributes.getURI(n), attributes.getLocalName(n), attributes.getQName(n));
        final String type = attributes.getType(n);
        this.fAttributes.addAttributeNS(this.fAttributeQName, (type != null) ? type : XMLSymbols.fCDATASymbol, attributes.getValue(n));
    }
    
    static void convertToSAXParseException(final XMLParseException ex) throws SAXException {
        final Exception exception = ex.getException();
        if (exception == null) {
            final LocatorImpl locatorImpl = new LocatorImpl();
            locatorImpl.setPublicId(ex.getPublicId());
            locatorImpl.setSystemId(ex.getExpandedSystemId());
            locatorImpl.setLineNumber(ex.getLineNumber());
            locatorImpl.setColumnNumber(ex.getColumnNumber());
            throw new SAXParseException(ex.getMessage(), locatorImpl);
        }
        if (exception instanceof SAXException) {
            throw (SAXException)exception;
        }
        throw new SAXException(exception);
    }
    
    static void convertToSAXException(final XNIException ex) throws SAXException {
        final Exception exception = ex.getException();
        if (exception == null) {
            throw new SAXException(ex.getMessage());
        }
        if (exception instanceof SAXException) {
            throw (SAXException)exception;
        }
        throw new SAXException(exception);
    }
}
