// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.psvi.AttributePSVI;
import org.apache.xerces.xni.psvi.ElementPSVI;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import java.util.Locale;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.xml.sax.ErrorHandler;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.util.EntityResolverWrapper;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.apache.xerces.xni.parser.XMLParseException;
import java.io.IOException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.XMLString;
import org.xml.sax.Attributes;
import org.xml.sax.AttributeList;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.xml.sax.SAXException;
import org.apache.xerces.xni.XNIException;
import org.xml.sax.Locator;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.Parser;
import org.apache.xerces.impl.xs.psvi.PSVIProvider;

public abstract class AbstractSAXParser extends AbstractXMLDocumentParser implements PSVIProvider, Parser, XMLReader
{
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    protected static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    private static final String[] RECOGNIZED_FEATURES;
    protected static final String LEXICAL_HANDLER = "http://xml.org/sax/properties/lexical-handler";
    protected static final String DECLARATION_HANDLER = "http://xml.org/sax/properties/declaration-handler";
    protected static final String DOM_NODE = "http://xml.org/sax/properties/dom-node";
    private static final String[] RECOGNIZED_PROPERTIES;
    protected boolean fNamespaces;
    protected boolean fNamespacePrefixes;
    protected ContentHandler fContentHandler;
    protected DocumentHandler fDocumentHandler;
    protected DTDHandler fDTDHandler;
    protected DeclHandler fDeclHandler;
    protected LexicalHandler fLexicalHandler;
    protected QName fQName;
    protected boolean fParseInProgress;
    private final AttributesProxy fAttributesProxy;
    private Augmentations fAugmentations;
    private static final int BUFFER_SIZE = 20;
    private char[] fCharBuffer;
    
    protected AbstractSAXParser(final XMLParserConfiguration config) {
        super(config);
        this.fNamespacePrefixes = false;
        this.fQName = new QName();
        this.fParseInProgress = false;
        this.fAttributesProxy = new AttributesProxy();
        this.fAugmentations = null;
        this.fCharBuffer = new char[20];
        config.addRecognizedFeatures(AbstractSAXParser.RECOGNIZED_FEATURES);
        config.addRecognizedProperties(AbstractSAXParser.RECOGNIZED_PROPERTIES);
    }
    
    public void startDocument(final XMLLocator locator, final String encoding, final NamespaceContext namespaceContext, final Augmentations augs) throws XNIException {
        try {
            if (this.fDocumentHandler != null) {
                if (locator != null) {
                    this.fDocumentHandler.setDocumentLocator(new LocatorProxy(locator));
                }
                this.fDocumentHandler.startDocument();
            }
            if (this.fContentHandler != null) {
                if (locator != null) {
                    this.fContentHandler.setDocumentLocator(new LocatorProxy(locator));
                }
                this.fContentHandler.startDocument();
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void doctypeDecl(final String rootElement, final String publicId, final String systemId, final Augmentations augs) throws XNIException {
        super.fInDTD = true;
        try {
            if (this.fLexicalHandler != null) {
                this.fLexicalHandler.startDTD(rootElement, publicId, systemId);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void startGeneralEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
        this.startParameterEntity(name, identifier, encoding, augs);
    }
    
    public void endGeneralEntity(final String name, final Augmentations augs) throws XNIException {
        this.endParameterEntity(name, augs);
    }
    
    public void startPrefixMapping(final String prefix, final String uri, final Augmentations augs) throws XNIException {
        try {
            if (this.fContentHandler != null) {
                this.fContentHandler.startPrefixMapping(prefix, uri);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void startElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        try {
            if (this.fDocumentHandler != null) {
                this.fAttributesProxy.setAttributes(attributes);
                this.fDocumentHandler.startElement(element.rawname, this.fAttributesProxy);
            }
            if (this.fContentHandler != null) {
                this.fAugmentations = augs;
                final int len = attributes.getLength();
                for (int i = len - 1; i >= 0; --i) {
                    attributes.getName(i, this.fQName);
                    if ((this.fQName.prefix != null && this.fQName.prefix.equals("xmlns")) || this.fQName.rawname.equals("xmlns")) {
                        if (!this.fNamespacePrefixes) {
                            attributes.removeAttributeAt(i);
                        }
                        if (this.fNamespaces && this.fNamespacePrefixes) {
                            this.fQName.prefix = "";
                            this.fQName.uri = "";
                            this.fQName.localpart = "";
                            attributes.setName(i, this.fQName);
                        }
                    }
                }
                final String uri = (element.uri != null) ? element.uri : "";
                final String localpart = this.fNamespaces ? element.localpart : "";
                this.fAttributesProxy.setAttributes(attributes);
                this.fContentHandler.startElement(uri, localpart, element.rawname, this.fAttributesProxy);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void characters(final XMLString text, final Augmentations augs) throws XNIException {
        if (text.length == 0) {
            return;
        }
        try {
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.characters(text.ch, text.offset, text.length);
            }
            if (this.fContentHandler != null) {
                this.fContentHandler.characters(text.ch, text.offset, text.length);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void ignorableWhitespace(final XMLString text, final Augmentations augs) throws XNIException {
        try {
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.ignorableWhitespace(text.ch, text.offset, text.length);
            }
            if (this.fContentHandler != null) {
                this.fContentHandler.ignorableWhitespace(text.ch, text.offset, text.length);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void endElement(final QName element, final Augmentations augs) throws XNIException {
        try {
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.endElement(element.rawname);
            }
            if (this.fContentHandler != null) {
                this.fAugmentations = augs;
                final String uri = (element.uri != null) ? element.uri : "";
                final String localpart = this.fNamespaces ? element.localpart : "";
                this.fContentHandler.endElement(uri, localpart, element.rawname);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void endPrefixMapping(final String prefix, final Augmentations augs) throws XNIException {
        try {
            if (this.fContentHandler != null) {
                this.fContentHandler.endPrefixMapping(prefix);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void startCDATA(final Augmentations augs) throws XNIException {
        try {
            if (this.fLexicalHandler != null) {
                this.fLexicalHandler.startCDATA();
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void endCDATA(final Augmentations augs) throws XNIException {
        try {
            if (this.fLexicalHandler != null) {
                this.fLexicalHandler.endCDATA();
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void comment(final XMLString text, final Augmentations augs) throws XNIException {
        try {
            if (this.fLexicalHandler != null) {
                this.fLexicalHandler.comment(text.ch, 0, text.length);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void processingInstruction(final String target, final XMLString data, final Augmentations augs) throws XNIException {
        try {
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.processingInstruction(target, data.toString());
            }
            if (this.fContentHandler != null) {
                this.fContentHandler.processingInstruction(target, data.toString());
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void endDocument(final Augmentations augs) throws XNIException {
        try {
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.endDocument();
            }
            if (this.fContentHandler != null) {
                this.fContentHandler.endDocument();
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void startExternalSubset(final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
        this.startParameterEntity("[dtd]", null, null, augs);
    }
    
    public void endExternalSubset(final Augmentations augs) throws XNIException {
        this.endParameterEntity("[dtd]", augs);
    }
    
    public void startParameterEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
        try {
            if (this.fLexicalHandler != null) {
                this.fLexicalHandler.startEntity(name);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void endParameterEntity(final String name, final Augmentations augs) throws XNIException {
        try {
            if (this.fLexicalHandler != null) {
                this.fLexicalHandler.endEntity(name);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void elementDecl(final String name, final String contentModel, final Augmentations augs) throws XNIException {
        try {
            if (this.fDeclHandler != null) {
                this.fDeclHandler.elementDecl(name, contentModel);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void attributeDecl(final String elementName, final String attributeName, String type, final String[] enumeration, final String defaultType, final XMLString defaultValue, final XMLString nonNormalizedDefaultValue, final Augmentations augs) throws XNIException {
        try {
            if (this.fDeclHandler != null) {
                if (type.equals("NOTATION") || type.equals("ENUMERATION")) {
                    final StringBuffer str = new StringBuffer();
                    if (type.equals("NOTATION")) {
                        str.append(type);
                        str.append(" (");
                    }
                    else {
                        str.append("(");
                    }
                    for (int i = 0; i < enumeration.length; ++i) {
                        str.append(enumeration[i]);
                        if (i < enumeration.length - 1) {
                            str.append('|');
                        }
                    }
                    str.append(')');
                    type = str.toString();
                }
                final String value = (defaultValue == null) ? null : defaultValue.toString();
                this.fDeclHandler.attributeDecl(elementName, attributeName, type, defaultType, value);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void internalEntityDecl(final String name, final XMLString text, final XMLString nonNormalizedText, final Augmentations augs) throws XNIException {
        try {
            if (this.fDeclHandler != null) {
                this.fDeclHandler.internalEntityDecl(name, text.toString());
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void externalEntityDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
        final String publicId = identifier.getPublicId();
        final String literalSystemId = identifier.getLiteralSystemId();
        try {
            if (this.fDeclHandler != null) {
                this.fDeclHandler.externalEntityDecl(name, publicId, literalSystemId);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void unparsedEntityDecl(final String name, final XMLResourceIdentifier identifier, final String notation, final Augmentations augs) throws XNIException {
        final String publicId = identifier.getPublicId();
        final String expandedSystemId = identifier.getExpandedSystemId();
        try {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.unparsedEntityDecl(name, publicId, expandedSystemId, notation);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void notationDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
        final String publicId = identifier.getPublicId();
        final String expandedSystemId = identifier.getExpandedSystemId();
        try {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.notationDecl(name, publicId, expandedSystemId);
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void endDTD(final Augmentations augs) throws XNIException {
        super.fInDTD = false;
        try {
            if (this.fLexicalHandler != null) {
                this.fLexicalHandler.endDTD();
            }
        }
        catch (SAXException e) {
            throw new XNIException(e);
        }
    }
    
    public void parse(final String systemId) throws SAXException, IOException {
        final XMLInputSource source = new XMLInputSource(null, systemId, null);
        try {
            this.parse(source);
        }
        catch (XMLParseException e) {
            final Exception exception = e.getException();
            if (exception == null) {
                final LocatorImpl locatorImpl = new LocatorImpl();
                locatorImpl.setPublicId(e.getPublicId());
                locatorImpl.setSystemId(e.getExpandedSystemId());
                locatorImpl.setLineNumber(e.getLineNumber());
                locatorImpl.setColumnNumber(e.getColumnNumber());
                throw new SAXParseException(e.getMessage(), locatorImpl);
            }
            if (exception instanceof SAXException) {
                throw (SAXException)exception;
            }
            if (exception instanceof IOException) {
                throw (IOException)exception;
            }
            throw new SAXException(exception);
        }
        catch (XNIException e2) {
            final Exception ex = e2.getException();
            if (ex == null) {
                throw new SAXException(e2.getMessage());
            }
            if (ex instanceof SAXException) {
                throw (SAXException)ex;
            }
            if (ex instanceof IOException) {
                throw (IOException)ex;
            }
            throw new SAXException(ex);
        }
    }
    
    public void parse(final InputSource inputSource) throws SAXException, IOException {
        try {
            final XMLInputSource xmlInputSource = new XMLInputSource(inputSource.getPublicId(), inputSource.getSystemId(), null);
            xmlInputSource.setByteStream(inputSource.getByteStream());
            xmlInputSource.setCharacterStream(inputSource.getCharacterStream());
            xmlInputSource.setEncoding(inputSource.getEncoding());
            this.parse(xmlInputSource);
        }
        catch (XMLParseException e) {
            final Exception exception = e.getException();
            if (exception == null) {
                final LocatorImpl locatorImpl = new LocatorImpl();
                locatorImpl.setPublicId(e.getPublicId());
                locatorImpl.setSystemId(e.getExpandedSystemId());
                locatorImpl.setLineNumber(e.getLineNumber());
                locatorImpl.setColumnNumber(e.getColumnNumber());
                throw new SAXParseException(e.getMessage(), locatorImpl);
            }
            if (exception instanceof SAXException) {
                throw (SAXException)exception;
            }
            if (exception instanceof IOException) {
                throw (IOException)exception;
            }
            throw new SAXException(exception);
        }
        catch (XNIException e2) {
            final Exception ex = e2.getException();
            if (ex == null) {
                throw new SAXException(e2.getMessage());
            }
            if (ex instanceof SAXException) {
                throw (SAXException)ex;
            }
            if (ex instanceof IOException) {
                throw (IOException)ex;
            }
            throw new SAXException(ex);
        }
    }
    
    public void setEntityResolver(final EntityResolver resolver) {
        try {
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new EntityResolverWrapper(resolver));
        }
        catch (XMLConfigurationException ex) {}
    }
    
    public EntityResolver getEntityResolver() {
        EntityResolver entityResolver = null;
        try {
            final XMLEntityResolver xmlEntityResolver = (XMLEntityResolver)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            if (xmlEntityResolver != null && xmlEntityResolver instanceof EntityResolverWrapper) {
                entityResolver = ((EntityResolverWrapper)xmlEntityResolver).getEntityResolver();
            }
        }
        catch (XMLConfigurationException ex) {}
        return entityResolver;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        try {
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/error-handler", new ErrorHandlerWrapper(errorHandler));
        }
        catch (XMLConfigurationException ex) {}
    }
    
    public ErrorHandler getErrorHandler() {
        ErrorHandler errorHandler = null;
        try {
            final XMLErrorHandler xmlErrorHandler = (XMLErrorHandler)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/error-handler");
            if (xmlErrorHandler != null && xmlErrorHandler instanceof ErrorHandlerWrapper) {
                errorHandler = ((ErrorHandlerWrapper)xmlErrorHandler).getErrorHandler();
            }
        }
        catch (XMLConfigurationException ex) {}
        return errorHandler;
    }
    
    public void setLocale(final Locale locale) throws SAXException {
        super.fConfiguration.setLocale(locale);
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
        if (dtdHandler == null) {
            throw new NullPointerException();
        }
        this.fDTDHandler = dtdHandler;
    }
    
    public void setDocumentHandler(final DocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
    }
    
    public void setContentHandler(final ContentHandler contentHandler) {
        this.fContentHandler = contentHandler;
    }
    
    public ContentHandler getContentHandler() {
        return this.fContentHandler;
    }
    
    public DTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }
    
    public void setFeature(final String featureId, final boolean state) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (featureId.startsWith("http://xml.org/sax/features/")) {
                final String feature = featureId.substring("http://xml.org/sax/features/".length());
                if (feature.equals("namespaces")) {
                    super.fConfiguration.setFeature(featureId, state);
                    this.fNamespaces = state;
                    return;
                }
                if (feature.equals("namespace-prefixes")) {
                    super.fConfiguration.setFeature(featureId, state);
                    this.fNamespacePrefixes = state;
                    return;
                }
                if (feature.equals("string-interning")) {
                    if (!state) {
                        throw new SAXNotSupportedException("PAR018 " + state + " state for feature \"" + featureId + "\" is not supported.\n" + state + '\t' + featureId);
                    }
                    return;
                }
            }
            super.fConfiguration.setFeature(featureId, state);
        }
        catch (XMLConfigurationException e) {
            final String message = e.getMessage();
            if (e.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    public boolean getFeature(final String featureId) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (featureId.startsWith("http://xml.org/sax/features/")) {
                final String feature = featureId.substring("http://xml.org/sax/features/".length());
                if (feature.equals("namespace-prefixes")) {
                    final boolean state = super.fConfiguration.getFeature(featureId);
                    return state;
                }
                if (feature.equals("string-interning")) {
                    return true;
                }
            }
            return super.fConfiguration.getFeature(featureId);
        }
        catch (XMLConfigurationException e) {
            final String message = e.getMessage();
            if (e.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    public void setProperty(final String propertyId, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (propertyId.startsWith("http://xml.org/sax/properties/")) {
                final String property = propertyId.substring("http://xml.org/sax/properties/".length());
                if (property.equals("lexical-handler")) {
                    try {
                        this.setLexicalHandler((LexicalHandler)value);
                    }
                    catch (ClassCastException e2) {
                        throw new SAXNotSupportedException("PAR012 For propertyID \"" + propertyId + "\", the value \"" + value + "\" cannot be cast to LexicalHandler." + '\n' + propertyId + '\t' + value + "\tLexicalHandler");
                    }
                    return;
                }
                if (property.equals("declaration-handler")) {
                    try {
                        this.setDeclHandler((DeclHandler)value);
                    }
                    catch (ClassCastException e2) {
                        throw new SAXNotSupportedException("PAR012 For propertyID \"" + propertyId + "\", the value \"" + value + "\" cannot be cast to DeclHandler." + '\n' + propertyId + '\t' + value + "\tDeclHandler");
                    }
                    return;
                }
                if (property.equals("dom-node")) {
                    throw new SAXNotSupportedException("PAR013 Property \"" + propertyId + "\" is read only." + '\n' + propertyId);
                }
            }
            super.fConfiguration.setProperty(propertyId, value);
        }
        catch (XMLConfigurationException e) {
            final String message = e.getMessage();
            if (e.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    public Object getProperty(final String propertyId) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (propertyId.startsWith("http://xml.org/sax/properties/")) {
                final String property = propertyId.substring("http://xml.org/sax/properties/".length());
                if (property.equals("lexical-handler")) {
                    return this.getLexicalHandler();
                }
                if (property.equals("declaration-handler")) {
                    return this.getDeclHandler();
                }
                if (property.equals("dom-node")) {
                    throw new SAXNotSupportedException("PAR014 Cannot getProperty(\"" + propertyId + "\". No DOM Tree exists.\n" + propertyId);
                }
            }
            return super.fConfiguration.getProperty(propertyId);
        }
        catch (XMLConfigurationException e) {
            final String message = e.getMessage();
            if (e.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    protected void setDeclHandler(final DeclHandler handler) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("PAR011 Feature: http://xml.org/sax/properties/declaration-handler is not supported during parse.\nhttp://xml.org/sax/properties/declaration-handler");
        }
        this.fDeclHandler = handler;
    }
    
    protected DeclHandler getDeclHandler() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fDeclHandler;
    }
    
    protected void setLexicalHandler(final LexicalHandler handler) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("PAR011 Feature: http://xml.org/sax/properties/lexical-handler is not supported during parse.\nhttp://xml.org/sax/properties/lexical-handler");
        }
        this.fLexicalHandler = handler;
    }
    
    protected LexicalHandler getLexicalHandler() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fLexicalHandler;
    }
    
    public void reset() throws XNIException {
        super.reset();
        super.fInDTD = false;
        this.fNamespaces = super.fConfiguration.getFeature("http://xml.org/sax/features/namespaces");
        this.fNamespacePrefixes = super.fConfiguration.getFeature("http://xml.org/sax/features/namespace-prefixes");
        this.fAugmentations = null;
    }
    
    public ElementPSVI getElementPSVI() {
        return (this.fAugmentations != null) ? ((ElementPSVI)this.fAugmentations.getItem("ELEMENT_PSVI")) : null;
    }
    
    public AttributePSVI getAttributePSVI(final int index) {
        return (AttributePSVI)this.fAttributesProxy.fAttributes.getAugmentations(index).getItem("ATTRIBUTE_PSVI");
    }
    
    public AttributePSVI getAttributePSVIByName(final String uri, final String localname) {
        return (AttributePSVI)this.fAttributesProxy.fAttributes.getAugmentations(uri, localname).getItem("ATTRIBUTE_PSVI");
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/namespaces", "http://xml.org/sax/features/namespace-prefixes", "http://xml.org/sax/features/string-interning" };
        RECOGNIZED_PROPERTIES = new String[] { "http://xml.org/sax/properties/lexical-handler", "http://xml.org/sax/properties/declaration-handler", "http://xml.org/sax/properties/dom-node" };
    }
    
    protected static class LocatorProxy implements Locator
    {
        protected XMLLocator fLocator;
        
        public LocatorProxy(final XMLLocator locator) {
            this.fLocator = locator;
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
    
    protected static final class AttributesProxy implements AttributeList, Attributes
    {
        protected XMLAttributes fAttributes;
        
        public void setAttributes(final XMLAttributes attributes) {
            this.fAttributes = attributes;
        }
        
        public int getLength() {
            return this.fAttributes.getLength();
        }
        
        public String getName(final int i) {
            return this.fAttributes.getQName(i);
        }
        
        public String getQName(final int index) {
            return this.fAttributes.getQName(index);
        }
        
        public String getURI(final int index) {
            final String uri = this.fAttributes.getURI(index);
            return (uri != null) ? uri : "";
        }
        
        public String getLocalName(final int index) {
            return this.fAttributes.getLocalName(index);
        }
        
        public String getType(final int i) {
            return this.fAttributes.getType(i);
        }
        
        public String getType(final String name) {
            return this.fAttributes.getType(name);
        }
        
        public String getType(final String uri, final String localName) {
            return uri.equals("") ? this.fAttributes.getType(null, localName) : this.fAttributes.getType(uri, localName);
        }
        
        public String getValue(final int i) {
            return this.fAttributes.getValue(i);
        }
        
        public String getValue(final String name) {
            return this.fAttributes.getValue(name);
        }
        
        public String getValue(final String uri, final String localName) {
            return uri.equals("") ? this.fAttributes.getValue(null, localName) : this.fAttributes.getValue(uri, localName);
        }
        
        public int getIndex(final String qName) {
            return this.fAttributes.getIndex(qName);
        }
        
        public int getIndex(final String uri, final String localPart) {
            return uri.equals("") ? this.fAttributes.getIndex(null, localPart) : this.fAttributes.getIndex(uri, localPart);
        }
    }
}
