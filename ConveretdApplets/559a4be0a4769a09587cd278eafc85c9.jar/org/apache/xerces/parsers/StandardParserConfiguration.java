// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.xs.XMLSchemaValidator;

public class StandardParserConfiguration extends DTDConfiguration
{
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String SCHEMA_AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String XMLSCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    protected static final String XMLSCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    private static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    protected XMLSchemaValidator fSchemaValidator;
    
    public StandardParserConfiguration() {
        this(null, null, null);
    }
    
    public StandardParserConfiguration(final SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }
    
    public StandardParserConfiguration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }
    
    public StandardParserConfiguration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool, final XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);
        final String[] recognizedFeatures = { "http://apache.org/xml/features/validation/schema/normalized-value", "http://apache.org/xml/features/validation/schema/element-default", "http://apache.org/xml/features/validation/schema/augment-psvi", "http://apache.org/xml/features/validation/schema", "http://apache.org/xml/features/validation/schema-full-checking" };
        this.addRecognizedFeatures(recognizedFeatures);
        this.setFeature("http://apache.org/xml/features/validation/schema/element-default", true);
        this.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", true);
        this.setFeature("http://apache.org/xml/features/validation/schema/augment-psvi", true);
        final String[] recognizedProperties = { "http://apache.org/xml/properties/schema/external-schemaLocation", "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage" };
        this.addRecognizedProperties(recognizedProperties);
    }
    
    protected void configurePipeline() {
        super.configurePipeline();
        if (this.getFeature("http://apache.org/xml/features/validation/schema")) {
            if (this.fSchemaValidator == null) {
                this.fSchemaValidator = new XMLSchemaValidator();
                super.fProperties.put("http://apache.org/xml/properties/internal/validator/schema", this.fSchemaValidator);
                this.addComponent(this.fSchemaValidator);
                if (super.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
                    final XSMessageFormatter xmft = new XSMessageFormatter();
                    super.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", xmft);
                }
            }
            super.fLastComponent = this.fSchemaValidator;
            super.fNamespaceBinder.setDocumentHandler(this.fSchemaValidator);
            this.fSchemaValidator.setDocumentHandler(super.fDocumentHandler);
            this.fSchemaValidator.setDocumentSource(super.fNamespaceBinder);
        }
    }
    
    protected void checkFeature(final String featureId) throws XMLConfigurationException {
        if (featureId.startsWith("http://apache.org/xml/features/")) {
            final String feature = featureId.substring("http://apache.org/xml/features/".length());
            if (feature.equals("validation/schema")) {
                return;
            }
            if (feature.equals("validation/schema-full-checking")) {
                return;
            }
            if (feature.equals("validation/schema/normalized-value")) {
                return;
            }
            if (feature.equals("validation/schema/element-default")) {
                return;
            }
        }
        super.checkFeature(featureId);
    }
    
    protected void checkProperty(final String propertyId) throws XMLConfigurationException {
        if (propertyId.startsWith("http://apache.org/xml/properties/")) {
            final String property = propertyId.substring("http://apache.org/xml/properties/".length());
            if (property.equals("schema/external-schemaLocation")) {
                return;
            }
            if (property.equals("schema/external-noNamespaceSchemaLocation")) {
                return;
            }
        }
        if (propertyId.startsWith("http://java.sun.com/xml/jaxp/properties/")) {
            final String property = propertyId.substring("http://java.sun.com/xml/jaxp/properties/".length());
            if (property.equals("schemaSource")) {
                return;
            }
        }
        super.checkProperty(propertyId);
    }
}
