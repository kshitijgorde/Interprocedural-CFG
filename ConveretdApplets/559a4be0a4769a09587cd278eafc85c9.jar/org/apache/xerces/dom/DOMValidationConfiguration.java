// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import java.io.IOException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.util.ObjectFactory;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.xni.parser.XMLComponent;
import java.util.Hashtable;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.impl.XMLErrorReporter;
import java.util.Locale;
import org.apache.xerces.impl.validation.ValidationManager;
import java.util.Vector;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.util.ParserConfigurationSettings;

public class DOMValidationConfiguration extends ParserConfigurationSettings implements XMLParserConfiguration
{
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String SCHEMA = "http://apache.org/xml/features/validation/schema";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    XMLDocumentHandler fDocumentHandler;
    protected SymbolTable fSymbolTable;
    protected Vector fComponents;
    protected ValidationManager fValidationManager;
    protected Locale fLocale;
    protected XMLErrorReporter fErrorReporter;
    
    protected DOMValidationConfiguration() {
        this(null, null);
    }
    
    protected DOMValidationConfiguration(final SymbolTable symbolTable) {
        this(symbolTable, null);
    }
    
    protected DOMValidationConfiguration(SymbolTable symbolTable, final XMLComponentManager parentSettings) {
        super(parentSettings);
        super.fRecognizedFeatures = new Vector();
        super.fRecognizedProperties = new Vector();
        super.fFeatures = new Hashtable();
        super.fProperties = new Hashtable();
        final String[] recognizedFeatures = { "http://xml.org/sax/features/validation", "http://xml.org/sax/features/namespaces", "http://apache.org/xml/features/validation/schema", "http://apache.org/xml/features/validation/dynamic", "http://apache.org/xml/features/validation/schema/normalized-value" };
        this.addRecognizedFeatures(recognizedFeatures);
        this.setFeature("http://xml.org/sax/features/validation", false);
        this.setFeature("http://apache.org/xml/features/validation/schema", false);
        this.setFeature("http://apache.org/xml/features/validation/dynamic", false);
        this.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", true);
        this.setFeature("http://xml.org/sax/features/namespaces", true);
        final String[] recognizedProperties = { "http://xml.org/sax/properties/xml-string", "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/validation-manager", "http://apache.org/xml/properties/internal/grammar-pool" };
        this.addRecognizedProperties(recognizedProperties);
        if (symbolTable == null) {
            symbolTable = new SymbolTable();
        }
        this.fSymbolTable = symbolTable;
        this.fComponents = new Vector();
        this.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
        this.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter = new XMLErrorReporter());
        this.addComponent(this.fErrorReporter);
        final XMLEntityManager manager = new XMLEntityManager();
        this.setProperty("http://apache.org/xml/properties/internal/entity-manager", manager);
        this.addComponent(manager);
        this.setProperty("http://apache.org/xml/properties/internal/validation-manager", this.fValidationManager = this.createValidationManager());
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            final XMLMessageFormatter xmft = new XMLMessageFormatter();
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmft);
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xmft);
        }
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
            MessageFormatter xmft2 = null;
            try {
                xmft2 = (MessageFormatter)ObjectFactory.newInstance("org.apache.xerces.impl.xs.XSMessageFormatter", ObjectFactory.findClassLoader(), true);
            }
            catch (Exception ex) {}
            if (xmft2 != null) {
                this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", xmft2);
            }
        }
        try {
            this.setLocale(Locale.getDefault());
        }
        catch (XNIException ex2) {}
    }
    
    public void parse(final XMLInputSource inputSource) throws XNIException, IOException {
    }
    
    public void setDocumentHandler(final XMLDocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
    }
    
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }
    
    public void setDTDHandler(final XMLDTDHandler dtdHandler) {
    }
    
    public XMLDTDHandler getDTDHandler() {
        return null;
    }
    
    public void setDTDContentModelHandler(final XMLDTDContentModelHandler handler) {
    }
    
    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return null;
    }
    
    public void setEntityResolver(final XMLEntityResolver resolver) {
        if (resolver != null) {
            super.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", resolver);
        }
    }
    
    public XMLEntityResolver getEntityResolver() {
        return super.fProperties.get("http://apache.org/xml/properties/internal/entity-resolver");
    }
    
    public void setErrorHandler(final XMLErrorHandler errorHandler) {
        if (errorHandler != null) {
            super.fProperties.put("http://apache.org/xml/properties/internal/error-handler", errorHandler);
        }
    }
    
    public XMLErrorHandler getErrorHandler() {
        return super.fProperties.get("http://apache.org/xml/properties/internal/error-handler");
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
        super.setFeature(featureId, state);
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
        super.setProperty(propertyId, value);
    }
    
    public void setLocale(final Locale locale) throws XNIException {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    protected void reset() throws XNIException {
        if (this.fValidationManager != null) {
            this.fValidationManager.reset();
        }
        for (int count = this.fComponents.size(), i = 0; i < count; ++i) {
            final XMLComponent c = this.fComponents.elementAt(i);
            c.reset(this);
        }
    }
    
    protected void checkProperty(final String propertyId) throws XMLConfigurationException {
        if (propertyId.startsWith("http://xml.org/sax/properties/")) {
            final String property = propertyId.substring("http://xml.org/sax/properties/".length());
            if (property.equals("xml-string")) {
                final short type = 1;
                throw new XMLConfigurationException(type, propertyId);
            }
        }
        super.checkProperty(propertyId);
    }
    
    protected void addComponent(final XMLComponent component) {
        if (this.fComponents.contains(component)) {
            return;
        }
        this.fComponents.addElement(component);
        final String[] recognizedFeatures = component.getRecognizedFeatures();
        this.addRecognizedFeatures(recognizedFeatures);
        final String[] recognizedProperties = component.getRecognizedProperties();
        this.addRecognizedProperties(recognizedProperties);
    }
    
    protected ValidationManager createValidationManager() {
        return new ValidationManager();
    }
}
