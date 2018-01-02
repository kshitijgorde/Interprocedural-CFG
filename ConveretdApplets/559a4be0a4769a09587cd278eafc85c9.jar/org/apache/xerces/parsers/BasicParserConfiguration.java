// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import java.io.IOException;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLComponent;
import java.util.Hashtable;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import java.util.Vector;
import java.util.Locale;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.util.ParserConfigurationSettings;

public abstract class BasicParserConfiguration extends ParserConfigurationSettings implements XMLParserConfiguration
{
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected SymbolTable fSymbolTable;
    protected Locale fLocale;
    protected Vector fComponents;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDocumentSource fLastComponent;
    
    protected BasicParserConfiguration() {
        this(null, null);
    }
    
    protected BasicParserConfiguration(final SymbolTable symbolTable) {
        this(symbolTable, null);
    }
    
    protected BasicParserConfiguration(SymbolTable symbolTable, final XMLComponentManager parentSettings) {
        super(parentSettings);
        this.fComponents = new Vector();
        super.fRecognizedFeatures = new Vector();
        super.fRecognizedProperties = new Vector();
        super.fFeatures = new Hashtable();
        super.fProperties = new Hashtable();
        final String[] recognizedFeatures = { "http://xml.org/sax/features/validation", "http://xml.org/sax/features/namespaces", "http://xml.org/sax/features/external-general-entities", "http://xml.org/sax/features/external-parameter-entities" };
        this.addRecognizedFeatures(recognizedFeatures);
        this.setFeature("http://xml.org/sax/features/validation", false);
        this.setFeature("http://xml.org/sax/features/namespaces", true);
        this.setFeature("http://xml.org/sax/features/external-general-entities", true);
        this.setFeature("http://xml.org/sax/features/external-parameter-entities", true);
        final String[] recognizedProperties = { "http://xml.org/sax/properties/xml-string", "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver" };
        this.addRecognizedProperties(recognizedProperties);
        if (symbolTable == null) {
            symbolTable = new SymbolTable();
        }
        this.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable = symbolTable);
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
        if (recognizedFeatures != null) {
            for (int i = 0; i < recognizedFeatures.length; ++i) {
                final String featureId = recognizedFeatures[i];
                final Boolean state = component.getFeatureDefault(featureId);
                if (state != null) {
                    this.setFeature(featureId, state);
                }
            }
        }
        if (recognizedProperties != null) {
            for (int i = 0; i < recognizedProperties.length; ++i) {
                final String propertyId = recognizedProperties[i];
                final Object value = component.getPropertyDefault(propertyId);
                if (value != null) {
                    this.setProperty(propertyId, value);
                }
            }
        }
    }
    
    public abstract void parse(final XMLInputSource p0) throws XNIException, IOException;
    
    public void setDocumentHandler(final XMLDocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
        if (this.fLastComponent != null) {
            this.fLastComponent.setDocumentHandler(this.fDocumentHandler);
        }
    }
    
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }
    
    public void setDTDHandler(final XMLDTDHandler dtdHandler) {
        this.fDTDHandler = dtdHandler;
    }
    
    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }
    
    public void setDTDContentModelHandler(final XMLDTDContentModelHandler handler) {
        this.fDTDContentModelHandler = handler;
    }
    
    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }
    
    public void setEntityResolver(final XMLEntityResolver resolver) {
        super.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", resolver);
    }
    
    public XMLEntityResolver getEntityResolver() {
        return super.fProperties.get("http://apache.org/xml/properties/internal/entity-resolver");
    }
    
    public void setErrorHandler(final XMLErrorHandler errorHandler) {
        super.fProperties.put("http://apache.org/xml/properties/internal/error-handler", errorHandler);
    }
    
    public XMLErrorHandler getErrorHandler() {
        return super.fProperties.get("http://apache.org/xml/properties/internal/error-handler");
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
        for (int count = this.fComponents.size(), i = 0; i < count; ++i) {
            final XMLComponent c = this.fComponents.elementAt(i);
            c.setFeature(featureId, state);
        }
        super.setFeature(featureId, state);
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
        for (int count = this.fComponents.size(), i = 0; i < count; ++i) {
            final XMLComponent c = this.fComponents.elementAt(i);
            c.setProperty(propertyId, value);
        }
        super.setProperty(propertyId, value);
    }
    
    public void setLocale(final Locale locale) throws XNIException {
        this.fLocale = locale;
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    protected void reset() throws XNIException {
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
}
