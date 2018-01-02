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
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected XMLSchemaValidator fSchemaValidator;
    
    public StandardParserConfiguration() {
        this(null, null, null);
    }
    
    public StandardParserConfiguration(final SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }
    
    public StandardParserConfiguration(final SymbolTable symbolTable, final XMLGrammarPool xmlGrammarPool) {
        this(symbolTable, xmlGrammarPool, null);
    }
    
    public StandardParserConfiguration(final SymbolTable symbolTable, final XMLGrammarPool xmlGrammarPool, final XMLComponentManager xmlComponentManager) {
        super(symbolTable, xmlGrammarPool, xmlComponentManager);
        this.addRecognizedFeatures(new String[] { "http://apache.org/xml/features/validation/schema/normalized-value", "http://apache.org/xml/features/validation/schema/element-default", "http://apache.org/xml/features/validation/schema/augment-psvi", "http://apache.org/xml/features/generate-synthetic-annotations", "http://apache.org/xml/features/validate-annotations", "http://apache.org/xml/features/honour-all-schemaLocations", "http://apache.org/xml/features/validation/schema", "http://apache.org/xml/features/validation/schema-full-checking" });
        this.setFeature("http://apache.org/xml/features/validation/schema/element-default", true);
        this.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", true);
        this.setFeature("http://apache.org/xml/features/validation/schema/augment-psvi", true);
        this.setFeature("http://apache.org/xml/features/generate-synthetic-annotations", false);
        this.setFeature("http://apache.org/xml/features/validate-annotations", false);
        this.setFeature("http://apache.org/xml/features/honour-all-schemaLocations", false);
        this.addRecognizedProperties(new String[] { "http://apache.org/xml/properties/schema/external-schemaLocation", "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation" });
    }
    
    protected void configurePipeline() {
        super.configurePipeline();
        if (this.getFeature("http://apache.org/xml/features/validation/schema")) {
            if (this.fSchemaValidator == null) {
                this.fSchemaValidator = new XMLSchemaValidator();
                super.fProperties.put("http://apache.org/xml/properties/internal/validator/schema", this.fSchemaValidator);
                this.addComponent(this.fSchemaValidator);
                if (super.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
                    super.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter());
                }
            }
            super.fLastComponent = this.fSchemaValidator;
            super.fNamespaceBinder.setDocumentHandler(this.fSchemaValidator);
            this.fSchemaValidator.setDocumentHandler(super.fDocumentHandler);
            this.fSchemaValidator.setDocumentSource(super.fNamespaceBinder);
        }
    }
    
    protected void checkFeature(final String s) throws XMLConfigurationException {
        if (s.startsWith("http://apache.org/xml/features/")) {
            final int n = s.length() - "http://apache.org/xml/features/".length();
            if (n == "validation/schema".length() && s.endsWith("validation/schema")) {
                return;
            }
            if (n == "validation/schema-full-checking".length() && s.endsWith("validation/schema-full-checking")) {
                return;
            }
            if (n == "validation/schema/normalized-value".length() && s.endsWith("validation/schema/normalized-value")) {
                return;
            }
            if (n == "validation/schema/element-default".length() && s.endsWith("validation/schema/element-default")) {
                return;
            }
        }
        super.checkFeature(s);
    }
    
    protected void checkProperty(final String s) throws XMLConfigurationException {
        if (s.startsWith("http://apache.org/xml/properties/")) {
            final int n = s.length() - "http://apache.org/xml/properties/".length();
            if (n == "schema/external-schemaLocation".length() && s.endsWith("schema/external-schemaLocation")) {
                return;
            }
            if (n == "schema/external-noNamespaceSchemaLocation".length() && s.endsWith("schema/external-noNamespaceSchemaLocation")) {
                return;
            }
        }
        if (s.startsWith("http://java.sun.com/xml/jaxp/properties/") && s.length() - "http://java.sun.com/xml/jaxp/properties/".length() == "schemaSource".length() && s.endsWith("schemaSource")) {
            return;
        }
        super.checkProperty(s);
    }
}
