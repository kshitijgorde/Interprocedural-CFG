// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import java.util.Iterator;
import java.util.Map;
import java.util.Locale;
import org.apache.xerces.util.SAXMessageFormatter;
import java.util.HashMap;
import org.apache.xerces.xs.AttributePSVI;
import org.apache.xerces.xs.ElementPSVI;
import org.xml.sax.HandlerBase;
import java.io.IOException;
import org.xml.sax.DocumentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.Parser;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import java.util.Enumeration;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.jaxp.validation.XSGrammarPoolContainer;
import org.apache.xerces.util.SecurityManager;
import org.xml.sax.SAXException;
import java.util.Hashtable;
import org.xml.sax.ErrorHandler;
import javax.xml.validation.Schema;
import org.apache.xerces.xs.PSVIProvider;
import javax.xml.parsers.SAXParser;

public class SAXParserImpl extends SAXParser implements JAXPConstants, PSVIProvider
{
    protected static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    protected static final String XMLSCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
    private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private JAXPSAXParser xmlReader;
    String schemaLanguage;
    Schema fSchema;
    ExternalSchemaValidator fExternalSchemaValidator;
    private final ErrorHandler fErrorHandler;
    
    SAXParserImpl(final SAXParserFactoryImpl saxParserFactoryImpl, final Hashtable hashtable) throws SAXException {
        this(saxParserFactoryImpl, hashtable, false);
    }
    
    SAXParserImpl(final SAXParserFactoryImpl saxParserFactoryImpl, final Hashtable features, final boolean b) throws SAXException {
        this.schemaLanguage = null;
        this.fSchema = null;
        (this.xmlReader = new JAXPSAXParser(this)).setFeature0("http://xml.org/sax/features/namespaces", saxParserFactoryImpl.isNamespaceAware());
        this.xmlReader.setFeature0("http://xml.org/sax/features/namespace-prefixes", !saxParserFactoryImpl.isNamespaceAware());
        if (saxParserFactoryImpl.isXIncludeAware()) {
            this.xmlReader.setFeature0("http://apache.org/xml/features/xinclude", true);
        }
        if (b) {
            this.xmlReader.setProperty0("http://apache.org/xml/properties/security-manager", new SecurityManager());
        }
        this.setFeatures(features);
        if (saxParserFactoryImpl.isValidating()) {
            this.xmlReader.setErrorHandler(new DefaultValidationErrorHandler());
        }
        this.xmlReader.setFeature0("http://xml.org/sax/features/validation", saxParserFactoryImpl.isValidating());
        this.fSchema = saxParserFactoryImpl.getSchema();
        if (this.fSchema != null) {
            if (this.fSchema instanceof XSGrammarPoolContainer) {
                this.xmlReader.setFeature0("http://apache.org/xml/features/validation/schema", true);
                if (!saxParserFactoryImpl.isValidating()) {
                    this.xmlReader.setFeature0("http://xml.org/sax/features/validation", true);
                }
                this.xmlReader.setProperty0("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
                this.xmlReader.setProperty0("http://apache.org/xml/properties/internal/grammar-pool", ((XSGrammarPoolContainer)this.fSchema).getGrammarPool());
                this.xmlReader.setFeature0("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only", ((XSGrammarPoolContainer)this.fSchema).isFullyComposed());
            }
            else {
                final XMLParserConfiguration xmlParserConfiguration = this.xmlReader.getXMLParserConfiguration();
                xmlParserConfiguration.setDocumentHandler(this.fExternalSchemaValidator = new ExternalSchemaValidator(this.fSchema, xmlParserConfiguration));
                this.fExternalSchemaValidator.setDocumentHandler(this.xmlReader);
                this.xmlReader.setDocumentSource(this.fExternalSchemaValidator);
                this.xmlReader.setProperty0("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            }
        }
        this.fErrorHandler = this.xmlReader.getErrorHandler();
    }
    
    private void setFeatures(final Hashtable hashtable) throws SAXNotSupportedException, SAXNotRecognizedException {
        if (hashtable != null) {
            final Enumeration<String> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                this.xmlReader.setFeature0(s, hashtable.get(s));
            }
        }
    }
    
    public Parser getParser() throws SAXException {
        return this.xmlReader;
    }
    
    public XMLReader getXMLReader() {
        return this.xmlReader;
    }
    
    public boolean isNamespaceAware() {
        try {
            return this.xmlReader.getFeature("http://xml.org/sax/features/namespaces");
        }
        catch (SAXException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    public boolean isValidating() {
        try {
            return this.xmlReader.getFeature("http://xml.org/sax/features/validation");
        }
        catch (SAXException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    public boolean isXIncludeAware() {
        try {
            return this.xmlReader.getFeature("http://apache.org/xml/features/xinclude");
        }
        catch (SAXException ex) {
            return false;
        }
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.xmlReader.setProperty(s, o);
        if (this.fExternalSchemaValidator != null) {
            this.fExternalSchemaValidator.reset(this.xmlReader.getXMLParserConfiguration());
        }
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.xmlReader.getProperty(s);
    }
    
    public void parse(final InputSource inputSource, final DefaultHandler defaultHandler) throws SAXException, IOException {
        if (inputSource == null) {
            throw new IllegalArgumentException();
        }
        if (defaultHandler != null) {
            this.xmlReader.setContentHandler(defaultHandler);
            this.xmlReader.setEntityResolver(defaultHandler);
            this.xmlReader.setErrorHandler(defaultHandler);
            this.xmlReader.setDTDHandler(defaultHandler);
            this.xmlReader.setDocumentHandler(null);
        }
        this.xmlReader.parse(inputSource);
    }
    
    public void parse(final InputSource inputSource, final HandlerBase handlerBase) throws SAXException, IOException {
        if (inputSource == null) {
            throw new IllegalArgumentException();
        }
        if (handlerBase != null) {
            this.xmlReader.setDocumentHandler(handlerBase);
            this.xmlReader.setEntityResolver(handlerBase);
            this.xmlReader.setErrorHandler(handlerBase);
            this.xmlReader.setDTDHandler(handlerBase);
            this.xmlReader.setContentHandler(null);
        }
        this.xmlReader.parse(inputSource);
    }
    
    public void reset() {
        try {
            this.xmlReader.restoreInitState();
        }
        catch (SAXException ex) {}
        this.xmlReader.setContentHandler(null);
        this.xmlReader.setDTDHandler(null);
        this.xmlReader.setEntityResolver(null);
        this.xmlReader.setErrorHandler(this.fErrorHandler);
        if (this.fExternalSchemaValidator != null) {
            this.fExternalSchemaValidator.reset(this.xmlReader.getXMLParserConfiguration());
        }
    }
    
    public Schema getSchema() {
        return this.fSchema;
    }
    
    public ElementPSVI getElementPSVI() {
        return this.xmlReader.getElementPSVI();
    }
    
    public AttributePSVI getAttributePSVI(final int n) {
        return this.xmlReader.getAttributePSVI(n);
    }
    
    public AttributePSVI getAttributePSVIByName(final String s, final String s2) {
        return this.xmlReader.getAttributePSVIByName(s, s2);
    }
    
    public static class JAXPSAXParser extends org.apache.xerces.parsers.SAXParser
    {
        private HashMap fInitFeatures;
        private HashMap fInitProperties;
        private SAXParserImpl fSAXParser;
        
        public JAXPSAXParser() {
            this.fInitFeatures = new HashMap();
            this.fInitProperties = new HashMap();
        }
        
        private JAXPSAXParser(final SAXParserImpl fsaxParser) {
            this.fInitFeatures = new HashMap();
            this.fInitProperties = new HashMap();
            this.fSAXParser = fsaxParser;
        }
        
        public synchronized void setFeature(final String s, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
            if (s == null) {
                throw new NullPointerException();
            }
            if (this.fSAXParser != null && this.fSAXParser.fSchema instanceof XSGrammarPoolContainer) {
                if (s.equals("http://xml.org/sax/features/validation")) {
                    if (!this.fInitProperties.containsKey("http://java.sun.com/xml/jaxp/properties/schemaLanguage")) {
                        this.fInitProperties.put("http://java.sun.com/xml/jaxp/properties/schemaLanguage", super.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage"));
                    }
                    this.setProperty0("http://java.sun.com/xml/jaxp/properties/schemaLanguage", b ? null : "http://www.w3.org/2001/XMLSchema");
                    return;
                }
                if (!b && s.equals("http://apache.org/xml/features/validation/schema")) {
                    throw new SAXNotSupportedException();
                }
            }
            if (s.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
                try {
                    this.setProperty("http://apache.org/xml/properties/security-manager", new SecurityManager());
                }
                catch (SAXException ex) {
                    if (b) {
                        throw new SAXNotSupportedException();
                    }
                }
                return;
            }
            if (!this.fInitFeatures.containsKey(s)) {
                this.fInitFeatures.put(s, super.getFeature(s) ? Boolean.TRUE : Boolean.FALSE);
            }
            super.setFeature(s, b);
        }
        
        public synchronized boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
            if (s == null) {
                throw new NullPointerException();
            }
            if (s.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
                try {
                    return super.getProperty("http://apache.org/xml/properties/security-manager") != null;
                }
                catch (SAXException ex) {
                    return false;
                }
            }
            return super.getFeature(s);
        }
        
        public synchronized void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
            if (s == null) {
                throw new NullPointerException();
            }
            if (this.fSAXParser != null) {
                if ("http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(s)) {
                    if (this.fSAXParser.fSchema != null && o != null) {
                        throw new SAXNotSupportedException();
                    }
                    if ("http://www.w3.org/2001/XMLSchema".equals(o)) {
                        if (this.fSAXParser.isValidating()) {
                            this.fSAXParser.schemaLanguage = "http://www.w3.org/2001/XMLSchema";
                            this.setFeature("http://apache.org/xml/features/validation/schema", true);
                            if (!this.fInitProperties.containsKey("http://java.sun.com/xml/jaxp/properties/schemaLanguage")) {
                                this.fInitProperties.put("http://java.sun.com/xml/jaxp/properties/schemaLanguage", super.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage"));
                            }
                            super.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
                        }
                    }
                    else {
                        if (o != null) {
                            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(null, "schema-not-supported", null));
                        }
                        this.fSAXParser.schemaLanguage = null;
                        this.setFeature("http://apache.org/xml/features/validation/schema", false);
                    }
                    return;
                }
                else if ("http://java.sun.com/xml/jaxp/properties/schemaSource".equals(s)) {
                    if (this.fSAXParser.fSchema != null && o != null) {
                        throw new SAXNotSupportedException();
                    }
                    final String s2 = (String)this.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
                    if (s2 != null && "http://www.w3.org/2001/XMLSchema".equals(s2)) {
                        if (!this.fInitProperties.containsKey("http://java.sun.com/xml/jaxp/properties/schemaSource")) {
                            this.fInitProperties.put("http://java.sun.com/xml/jaxp/properties/schemaSource", super.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource"));
                        }
                        super.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", o);
                        return;
                    }
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(null, "jaxp-order-not-supported", new Object[] { "http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://java.sun.com/xml/jaxp/properties/schemaSource" }));
                }
            }
            if (!this.fInitProperties.containsKey(s)) {
                this.fInitProperties.put(s, super.getProperty(s));
            }
            super.setProperty(s, o);
        }
        
        public synchronized Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
            if (s == null) {
                throw new NullPointerException();
            }
            if (this.fSAXParser != null) {
                if (this.fSAXParser.fSchema != null) {
                    if ("http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(s) || "http://java.sun.com/xml/jaxp/properties/schemaSource".equals(s)) {
                        return null;
                    }
                }
                else if ("http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(s)) {
                    return this.fSAXParser.schemaLanguage;
                }
            }
            return super.getProperty(s);
        }
        
        synchronized void restoreInitState() throws SAXNotRecognizedException, SAXNotSupportedException {
            if (!this.fInitFeatures.isEmpty()) {
                for (final Map.Entry<String, V> entry : this.fInitFeatures.entrySet()) {
                    super.setFeature(entry.getKey(), (boolean)entry.getValue());
                }
                this.fInitFeatures.clear();
            }
            if (!this.fInitProperties.isEmpty()) {
                for (final Map.Entry<String, V> entry2 : this.fInitProperties.entrySet()) {
                    super.setProperty(entry2.getKey(), entry2.getValue());
                }
                this.fInitProperties.clear();
            }
        }
        
        XMLParserConfiguration getXMLParserConfiguration() {
            return super.fConfiguration;
        }
        
        void setFeature0(final String s, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
            super.setFeature(s, b);
        }
        
        boolean getFeature0(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
            return super.getFeature(s);
        }
        
        void setProperty0(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
            super.setProperty(s, o);
        }
        
        Object getProperty0(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
            return super.getProperty(s);
        }
    }
}
