// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.w3c.dom.DOMImplementation;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import java.util.Enumeration;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.jaxp.validation.XSGrammarPoolContainer;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import java.util.Hashtable;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import javax.xml.validation.Schema;
import org.apache.xerces.parsers.DOMParser;
import javax.xml.parsers.DocumentBuilder;

public class DocumentBuilderImpl extends DocumentBuilder implements JAXPConstants
{
    private DOMParser domParser;
    private Schema fSchema;
    private ExternalSchemaValidator fExternalSchemaValidator;
    private boolean fIsXIncludeAware;
    private final ErrorHandler fErrorHandler;
    private final EntityResolver fEntityResolver;
    protected static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    protected static final String INCLUDE_IGNORABLE_WHITESPACE = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
    protected static final String CREATE_ENTITY_REF_NODES_FEATURE = "http://apache.org/xml/features/dom/create-entity-ref-nodes";
    protected static final String INCLUDE_COMMENTS_FEATURE = "http://apache.org/xml/features/include-comments";
    protected static final String CREATE_CDATA_NODES_FEATURE = "http://apache.org/xml/features/create-cdata-nodes";
    private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    protected static final String XMLSCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    
    DocumentBuilderImpl(final DocumentBuilderFactoryImpl documentBuilderFactoryImpl, final Hashtable hashtable, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        this(documentBuilderFactoryImpl, hashtable, false, b);
    }
    
    DocumentBuilderImpl(final DocumentBuilderFactoryImpl documentBuilderFactoryImpl, final Hashtable documentBuilderFactoryAttributes, final boolean b, final boolean b2) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.domParser = null;
        this.fSchema = null;
        this.fIsXIncludeAware = documentBuilderFactoryImpl.isXIncludeAware();
        this.fSchema = documentBuilderFactoryImpl.getSchema();
        (this.domParser = new DOMParser()).setFeature("http://xml.org/sax/features/validation", documentBuilderFactoryImpl.isValidating());
        if (this.fSchema != null) {
            if (this.fSchema instanceof XSGrammarPoolContainer) {
                this.domParser.setFeature("http://apache.org/xml/features/validation/schema", true);
                if (!documentBuilderFactoryImpl.isValidating()) {
                    this.domParser.setFeature("http://xml.org/sax/features/validation", true);
                }
                if (!b2) {
                    this.domParser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
                }
                this.domParser.setProperty("http://apache.org/xml/properties/internal/grammar-pool", ((XSGrammarPoolContainer)this.fSchema).getGrammarPool());
                this.domParser.setFeature("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only", ((XSGrammarPoolContainer)this.fSchema).isFullyComposed());
            }
            else {
                final XMLParserConfiguration xmlParserConfiguration = this.domParser.getXMLParserConfiguration();
                xmlParserConfiguration.setDocumentHandler(this.fExternalSchemaValidator = new ExternalSchemaValidator(this.fSchema, xmlParserConfiguration));
                this.fExternalSchemaValidator.setDocumentHandler(this.domParser);
                this.domParser.setDocumentSource(this.fExternalSchemaValidator);
                if (!b2) {
                    this.domParser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
                }
            }
        }
        this.domParser.getXMLParserConfiguration().addRecognizedFeatures(new String[] { "http://apache.org/xml/features/xinclude" });
        if (this.fIsXIncludeAware) {
            this.domParser.setFeature("http://apache.org/xml/features/xinclude", true);
        }
        if (b) {
            this.domParser.setProperty("http://apache.org/xml/properties/security-manager", new SecurityManager());
        }
        if (documentBuilderFactoryImpl.isValidating()) {
            this.setErrorHandler(this.fErrorHandler = new DefaultValidationErrorHandler());
        }
        else {
            this.fErrorHandler = this.domParser.getErrorHandler();
        }
        this.domParser.setFeature("http://xml.org/sax/features/namespaces", documentBuilderFactoryImpl.isNamespaceAware());
        this.domParser.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", !documentBuilderFactoryImpl.isIgnoringElementContentWhitespace());
        this.domParser.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", !documentBuilderFactoryImpl.isExpandEntityReferences());
        this.domParser.setFeature("http://apache.org/xml/features/include-comments", !documentBuilderFactoryImpl.isIgnoringComments());
        this.domParser.setFeature("http://apache.org/xml/features/create-cdata-nodes", !documentBuilderFactoryImpl.isCoalescing());
        this.setDocumentBuilderFactoryAttributes(documentBuilderFactoryAttributes);
        this.fEntityResolver = this.domParser.getEntityResolver();
    }
    
    private void setDocumentBuilderFactoryAttributes(final Hashtable hashtable) throws SAXNotSupportedException, SAXNotRecognizedException {
        if (hashtable == null) {
            return;
        }
        final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final Boolean value = (Boolean)hashtable.get(s);
            if (value instanceof Boolean) {
                this.domParser.setFeature(s, value);
            }
            else if ("http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(s)) {
                if (!"http://www.w3.org/2001/XMLSchema".equals(value) || !this.isValidating()) {
                    continue;
                }
                this.domParser.setFeature("http://apache.org/xml/features/validation/schema", true);
                this.domParser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            }
            else if ("http://java.sun.com/xml/jaxp/properties/schemaSource".equals(s)) {
                if (!this.isValidating()) {
                    continue;
                }
                final String s2 = hashtable.get("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
                if (s2 == null || !"http://www.w3.org/2001/XMLSchema".equals(s2)) {
                    throw new IllegalArgumentException(DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "jaxp-order-not-supported", new Object[] { "http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://java.sun.com/xml/jaxp/properties/schemaSource" }));
                }
                this.domParser.setProperty(s, value);
            }
            else {
                this.domParser.setProperty(s, value);
            }
        }
    }
    
    public Document newDocument() {
        return new DocumentImpl();
    }
    
    public DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }
    
    public Document parse(final InputSource inputSource) throws SAXException, IOException {
        if (inputSource == null) {
            throw new IllegalArgumentException(DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "jaxp-null-input-source", null));
        }
        this.domParser.parse(inputSource);
        return this.domParser.getDocument();
    }
    
    public boolean isNamespaceAware() {
        try {
            return this.domParser.getFeature("http://xml.org/sax/features/namespaces");
        }
        catch (SAXException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    public boolean isValidating() {
        try {
            return this.domParser.getFeature("http://xml.org/sax/features/validation");
        }
        catch (SAXException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.domParser.setEntityResolver(entityResolver);
        if (this.fExternalSchemaValidator != null) {
            this.fExternalSchemaValidator.reset(this.domParser.getXMLParserConfiguration());
        }
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.domParser.setErrorHandler(errorHandler);
        if (this.fExternalSchemaValidator != null) {
            this.fExternalSchemaValidator.reset(this.domParser.getXMLParserConfiguration());
        }
    }
    
    public void reset() {
        this.domParser.setEntityResolver(this.fEntityResolver);
        this.domParser.setErrorHandler(this.fErrorHandler);
        if (this.fExternalSchemaValidator != null) {
            this.fExternalSchemaValidator.reset(this.domParser.getXMLParserConfiguration());
        }
    }
    
    public Schema getSchema() {
        return this.fSchema;
    }
    
    public boolean isXIncludeAware() {
        return this.fIsXIncludeAware;
    }
    
    DOMParser getDOMParser() {
        return this.domParser;
    }
}
