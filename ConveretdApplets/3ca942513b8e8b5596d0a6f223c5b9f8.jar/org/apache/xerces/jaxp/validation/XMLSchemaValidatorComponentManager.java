// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.NamespaceSupport;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.XMLEntityManager;
import java.util.HashMap;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.util.ParserConfigurationSettings;

final class XMLSchemaValidatorComponentManager extends ParserConfigurationSettings implements XMLComponentManager
{
    private static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    private static final String VALIDATION = "http://xml.org/sax/features/validation";
    private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    private static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    private static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private boolean fConfigUpdated;
    private boolean fUseGrammarPoolOnly;
    private final HashMap fComponents;
    private XMLEntityManager fEntityManager;
    private XMLErrorReporter fErrorReporter;
    private NamespaceContext fNamespaceContext;
    private XMLSchemaValidator fSchemaValidator;
    private ValidationManager fValidationManager;
    private ErrorHandler fErrorHandler;
    private LSResourceResolver fResourceResolver;
    
    public XMLSchemaValidatorComponentManager(final XSGrammarPoolContainer xsGrammarPoolContainer) {
        this.fConfigUpdated = true;
        this.fComponents = new HashMap();
        this.fErrorHandler = null;
        this.fResourceResolver = null;
        this.fEntityManager = new XMLEntityManager();
        this.fComponents.put("http://apache.org/xml/properties/internal/entity-manager", this.fEntityManager);
        this.fErrorReporter = new XMLErrorReporter();
        this.fComponents.put("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        this.fNamespaceContext = new NamespaceSupport();
        this.fComponents.put("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
        this.fSchemaValidator = new XMLSchemaValidator();
        this.fComponents.put("http://apache.org/xml/properties/internal/validator/schema", this.fSchemaValidator);
        this.fValidationManager = new ValidationManager();
        this.fComponents.put("http://apache.org/xml/properties/internal/validation-manager", this.fValidationManager);
        this.fComponents.put("http://apache.org/xml/properties/internal/entity-resolver", null);
        this.fComponents.put("http://apache.org/xml/properties/internal/error-handler", null);
        this.fComponents.put("http://apache.org/xml/properties/security-manager", null);
        this.fComponents.put("http://apache.org/xml/properties/internal/symbol-table", new SymbolTable());
        this.fComponents.put("http://apache.org/xml/properties/internal/grammar-pool", xsGrammarPoolContainer.getGrammarPool());
        this.fUseGrammarPoolOnly = xsGrammarPoolContainer.isFullyComposed();
        this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter());
        this.addRecognizedParamsAndSetDefaults(this.fEntityManager);
        this.addRecognizedParamsAndSetDefaults(this.fErrorReporter);
        this.addRecognizedParamsAndSetDefaults(this.fSchemaValidator);
    }
    
    public boolean getFeature(final String s) throws XMLConfigurationException {
        if ("http://apache.org/xml/features/internal/parser-settings".equals(s)) {
            return this.fConfigUpdated;
        }
        if ("http://xml.org/sax/features/validation".equals(s) || "http://apache.org/xml/features/validation/schema".equals(s)) {
            return true;
        }
        if ("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only".equals(s)) {
            return this.fUseGrammarPoolOnly;
        }
        if ("http://javax.xml.XMLConstants/feature/secure-processing".equals(s)) {
            return this.getProperty("http://apache.org/xml/properties/security-manager") != null;
        }
        return super.getFeature(s);
    }
    
    public void setFeature(final String s, final boolean b) throws XMLConfigurationException {
        if ("http://apache.org/xml/features/internal/parser-settings".equals(s)) {
            throw new XMLConfigurationException((short)1, s);
        }
        if (!b && ("http://xml.org/sax/features/validation".equals(s) || "http://apache.org/xml/features/validation/schema".equals(s))) {
            throw new XMLConfigurationException((short)1, s);
        }
        if ("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only".equals(s) && b != this.fUseGrammarPoolOnly) {
            throw new XMLConfigurationException((short)1, s);
        }
        this.fConfigUpdated = true;
        if ("http://javax.xml.XMLConstants/feature/secure-processing".equals(s)) {
            this.setProperty("http://apache.org/xml/properties/security-manager", b ? new SecurityManager() : null);
            return;
        }
        this.fEntityManager.setFeature(s, b);
        this.fErrorReporter.setFeature(s, b);
        this.fSchemaValidator.setFeature(s, b);
        super.setFeature(s, b);
    }
    
    public Object getProperty(final String s) throws XMLConfigurationException {
        final Object value = this.fComponents.get(s);
        if (value != null) {
            return value;
        }
        if (this.fComponents.containsKey(s)) {
            return null;
        }
        return super.getProperty(s);
    }
    
    public void setProperty(final String s, final Object o) throws XMLConfigurationException {
        if ("http://apache.org/xml/properties/internal/entity-manager".equals(s) || "http://apache.org/xml/properties/internal/error-reporter".equals(s) || "http://apache.org/xml/properties/internal/namespace-context".equals(s) || "http://apache.org/xml/properties/internal/validator/schema".equals(s) || "http://apache.org/xml/properties/internal/symbol-table".equals(s) || "http://apache.org/xml/properties/internal/validation-manager".equals(s) || "http://apache.org/xml/properties/internal/grammar-pool".equals(s)) {
            throw new XMLConfigurationException((short)1, s);
        }
        this.fConfigUpdated = true;
        this.fEntityManager.setProperty(s, o);
        this.fErrorReporter.setProperty(s, o);
        this.fSchemaValidator.setProperty(s, o);
        if ("http://apache.org/xml/properties/internal/entity-resolver".equals(s) || "http://apache.org/xml/properties/internal/error-handler".equals(s) || "http://apache.org/xml/properties/security-manager".equals(s)) {
            this.fComponents.put(s, o);
            return;
        }
        super.setProperty(s, o);
    }
    
    public void addRecognizedParamsAndSetDefaults(final XMLComponent xmlComponent) {
        final String[] recognizedFeatures = xmlComponent.getRecognizedFeatures();
        this.addRecognizedFeatures(recognizedFeatures);
        final String[] recognizedProperties = xmlComponent.getRecognizedProperties();
        this.addRecognizedProperties(recognizedProperties);
        this.setFeatureDefaults(xmlComponent, recognizedFeatures);
        this.setPropertyDefaults(xmlComponent, recognizedProperties);
    }
    
    public void reset() throws XNIException {
        this.fNamespaceContext.reset();
        this.fValidationManager.reset();
        this.fEntityManager.reset(this);
        this.fErrorReporter.reset(this);
        this.fSchemaValidator.reset(this);
        this.fConfigUpdated = false;
    }
    
    void setErrorHandler(final ErrorHandler fErrorHandler) {
        this.fErrorHandler = fErrorHandler;
        this.setProperty("http://apache.org/xml/properties/internal/error-handler", (fErrorHandler != null) ? new ErrorHandlerWrapper(fErrorHandler) : new ErrorHandlerWrapper(DefaultErrorHandler.getInstance()));
    }
    
    ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }
    
    void setResourceResolver(final LSResourceResolver fResourceResolver) {
        this.fResourceResolver = fResourceResolver;
        this.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new DOMEntityResolverWrapper(fResourceResolver));
    }
    
    public LSResourceResolver getResourceResolver() {
        return this.fResourceResolver;
    }
    
    void restoreInitialState() {
        this.fConfigUpdated = true;
        super.fFeatures.clear();
        super.fProperties.clear();
        this.fComponents.put("http://apache.org/xml/properties/internal/entity-resolver", null);
        this.fComponents.put("http://apache.org/xml/properties/internal/error-handler", null);
        this.setFeatureDefaults(this.fEntityManager, this.fEntityManager.getRecognizedFeatures());
        this.setPropertyDefaults(this.fEntityManager, this.fEntityManager.getRecognizedProperties());
        this.setFeatureDefaults(this.fErrorReporter, this.fErrorReporter.getRecognizedFeatures());
        this.setPropertyDefaults(this.fErrorReporter, this.fErrorReporter.getRecognizedProperties());
        this.setFeatureDefaults(this.fSchemaValidator, this.fSchemaValidator.getRecognizedFeatures());
        this.setPropertyDefaults(this.fSchemaValidator, this.fSchemaValidator.getRecognizedProperties());
    }
    
    private void setFeatureDefaults(final XMLComponent xmlComponent, final String[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final String s = array[i];
                final Boolean featureDefault = xmlComponent.getFeatureDefault(s);
                if (featureDefault != null && !super.fFeatures.containsKey(s)) {
                    super.fFeatures.put(s, featureDefault);
                    this.fConfigUpdated = true;
                }
            }
        }
    }
    
    private void setPropertyDefaults(final XMLComponent xmlComponent, final String[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final String s = array[i];
                final Object propertyDefault = xmlComponent.getPropertyDefault(s);
                if (propertyDefault != null && !super.fProperties.containsKey(s)) {
                    super.fProperties.put(s, propertyDefault);
                    this.fConfigUpdated = true;
                }
            }
        }
    }
}
