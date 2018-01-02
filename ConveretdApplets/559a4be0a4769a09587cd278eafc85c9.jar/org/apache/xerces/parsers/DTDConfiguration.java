// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.impl.XMLDTDScannerImpl;
import org.apache.xerces.impl.XMLDocumentScannerImpl;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import java.io.IOException;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.XNIException;
import java.util.Locale;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.XMLNamespaceBinder;
import org.apache.xerces.impl.dtd.XMLDTDValidator;
import org.apache.xerces.impl.dtd.XMLDTDProcessor;
import org.apache.xerces.xni.parser.XMLDTDScanner;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLDocumentScanner;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.dv.DTDDVFactory;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLPullParserConfiguration;

public class DTDConfiguration extends BasicParserConfiguration implements XMLPullParserConfiguration
{
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
    protected static final String WARN_ON_UNDECLARED_ELEMDEF = "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String DOCUMENT_SCANNER = "http://apache.org/xml/properties/internal/document-scanner";
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String DTD_PROCESSOR = "http://apache.org/xml/properties/internal/dtd-processor";
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String NAMESPACE_BINDER = "http://apache.org/xml/properties/internal/namespace-binder";
    protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    protected XMLGrammarPool fGrammarPool;
    protected DTDDVFactory fDatatypeValidatorFactory;
    protected XMLErrorReporter fErrorReporter;
    protected XMLEntityManager fEntityManager;
    protected XMLDocumentScanner fScanner;
    protected XMLInputSource fInputSource;
    protected XMLDTDScanner fDTDScanner;
    protected XMLDTDProcessor fDTDProcessor;
    protected XMLDTDValidator fDTDValidator;
    protected XMLNamespaceBinder fNamespaceBinder;
    protected ValidationManager fValidationManager;
    protected XMLLocator fLocator;
    protected boolean fParseInProgress;
    
    public DTDConfiguration() {
        this(null, null, null);
    }
    
    public DTDConfiguration(final SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }
    
    public DTDConfiguration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }
    
    public DTDConfiguration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool, final XMLComponentManager parentSettings) {
        super(symbolTable, parentSettings);
        this.fParseInProgress = false;
        final String[] recognizedFeatures = { "http://apache.org/xml/features/continue-after-fatal-error" };
        this.addRecognizedFeatures(recognizedFeatures);
        this.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false);
        final String[] recognizedProperties = { "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/document-scanner", "http://apache.org/xml/properties/internal/dtd-scanner", "http://apache.org/xml/properties/internal/dtd-processor", "http://apache.org/xml/properties/internal/validator/dtd", "http://apache.org/xml/properties/internal/namespace-binder", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/internal/datatype-validator-factory", "http://apache.org/xml/properties/internal/validation-manager" };
        this.addRecognizedProperties(recognizedProperties);
        this.fGrammarPool = grammarPool;
        if (this.fGrammarPool != null) {
            this.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
        }
        this.setProperty("http://apache.org/xml/properties/internal/entity-manager", this.fEntityManager = this.createEntityManager());
        this.addComponent(this.fEntityManager);
        (this.fErrorReporter = this.createErrorReporter()).setDocumentLocator(this.fEntityManager.getEntityScanner());
        this.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        this.addComponent(this.fErrorReporter);
        this.setProperty("http://apache.org/xml/properties/internal/document-scanner", this.fScanner = this.createDocumentScanner());
        if (this.fScanner instanceof XMLComponent) {
            this.addComponent((XMLComponent)this.fScanner);
        }
        this.fDTDScanner = this.createDTDScanner();
        if (this.fDTDScanner != null) {
            this.setProperty("http://apache.org/xml/properties/internal/dtd-scanner", this.fDTDScanner);
            if (this.fDTDScanner instanceof XMLComponent) {
                this.addComponent((XMLComponent)this.fDTDScanner);
            }
        }
        this.fDTDProcessor = this.createDTDProcessor();
        if (this.fDTDProcessor != null) {
            this.setProperty("http://apache.org/xml/properties/internal/dtd-processor", this.fDTDProcessor);
            if (this.fDTDProcessor instanceof XMLComponent) {
                this.addComponent(this.fDTDProcessor);
            }
        }
        this.fDTDValidator = this.createDTDValidator();
        if (this.fDTDValidator != null) {
            this.setProperty("http://apache.org/xml/properties/internal/validator/dtd", this.fDTDValidator);
            this.addComponent(this.fDTDValidator);
        }
        this.fNamespaceBinder = this.createNamespaceBinder();
        if (this.fNamespaceBinder != null) {
            this.setProperty("http://apache.org/xml/properties/internal/namespace-binder", this.fNamespaceBinder);
            this.addComponent(this.fNamespaceBinder);
        }
        this.fDatatypeValidatorFactory = this.createDatatypeValidatorFactory();
        if (this.fDatatypeValidatorFactory != null) {
            this.setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fDatatypeValidatorFactory);
        }
        this.fValidationManager = this.createValidationManager();
        if (this.fValidationManager != null) {
            this.setProperty("http://apache.org/xml/properties/internal/validation-manager", this.fValidationManager);
        }
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            final XMLMessageFormatter xmft = new XMLMessageFormatter();
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmft);
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xmft);
        }
        try {
            this.setLocale(Locale.getDefault());
        }
        catch (XNIException ex) {}
    }
    
    public void setLocale(final Locale locale) throws XNIException {
        super.setLocale(locale);
        this.fErrorReporter.setLocale(locale);
    }
    
    public void setInputSource(final XMLInputSource inputSource) throws XMLConfigurationException, IOException {
        this.fInputSource = inputSource;
    }
    
    public boolean parse(final boolean complete) throws XNIException, IOException {
        if (this.fInputSource != null) {
            try {
                this.reset();
                this.fScanner.setInputSource(this.fInputSource);
                this.fInputSource = null;
            }
            catch (XNIException ex) {
                throw ex;
            }
            catch (IOException ex2) {
                throw ex2;
            }
            catch (RuntimeException ex3) {
                throw ex3;
            }
            catch (Exception ex4) {
                throw new XNIException(ex4);
            }
        }
        try {
            return this.fScanner.scanDocument(complete);
        }
        catch (XNIException ex) {
            throw ex;
        }
        catch (IOException ex2) {
            throw ex2;
        }
        catch (RuntimeException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new XNIException(ex4);
        }
    }
    
    public void cleanup() {
        this.fEntityManager.closeReaders();
    }
    
    public void parse(final XMLInputSource source) throws XNIException, IOException {
        if (this.fParseInProgress) {
            throw new XNIException("FWK005 parse may not be called while parsing.");
        }
        this.fParseInProgress = true;
        try {
            this.setInputSource(source);
            this.parse(true);
        }
        catch (XNIException ex) {
            throw ex;
        }
        catch (IOException ex2) {
            throw ex2;
        }
        catch (RuntimeException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new XNIException(ex4);
        }
        finally {
            this.fParseInProgress = false;
            this.cleanup();
        }
    }
    
    protected void reset() throws XNIException {
        if (this.fValidationManager != null) {
            this.fValidationManager.reset();
        }
        this.configurePipeline();
        super.reset();
    }
    
    protected void configurePipeline() {
        if (this.fDTDValidator != null) {
            this.fScanner.setDocumentHandler(this.fDTDValidator);
            this.fDTDValidator.setDocumentHandler(this.fNamespaceBinder);
            this.fDTDValidator.setDocumentSource(this.fScanner);
            this.fNamespaceBinder.setDocumentHandler(super.fDocumentHandler);
            this.fNamespaceBinder.setDocumentSource(this.fDTDValidator);
        }
        else {
            this.fScanner.setDocumentHandler(this.fNamespaceBinder);
            this.fNamespaceBinder.setDocumentHandler(super.fDocumentHandler);
            this.fNamespaceBinder.setDocumentSource(this.fDTDValidator);
        }
        super.fLastComponent = this.fNamespaceBinder;
        if (this.fDTDScanner != null) {
            if (this.fDTDProcessor != null) {
                this.fDTDScanner.setDTDHandler(this.fDTDProcessor);
                this.fDTDProcessor.setDTDHandler(super.fDTDHandler);
                this.fDTDScanner.setDTDContentModelHandler(this.fDTDProcessor);
                this.fDTDProcessor.setDTDContentModelHandler(super.fDTDContentModelHandler);
            }
            else {
                this.fDTDScanner.setDTDHandler(super.fDTDHandler);
                this.fDTDScanner.setDTDContentModelHandler(super.fDTDContentModelHandler);
            }
        }
    }
    
    protected void checkFeature(final String featureId) throws XMLConfigurationException {
        if (featureId.startsWith("http://apache.org/xml/features/")) {
            final String feature = featureId.substring("http://apache.org/xml/features/".length());
            if (feature.equals("validation/dynamic")) {
                return;
            }
            if (feature.equals("validation/default-attribute-values")) {
                final short type = 1;
                throw new XMLConfigurationException(type, featureId);
            }
            if (feature.equals("validation/validate-content-models")) {
                final short type = 1;
                throw new XMLConfigurationException(type, featureId);
            }
            if (feature.equals("nonvalidating/load-dtd-grammar")) {
                return;
            }
            if (feature.equals("nonvalidating/load-external-dtd")) {
                return;
            }
            if (feature.equals("validation/validate-datatypes")) {
                final short type = 1;
                throw new XMLConfigurationException(type, featureId);
            }
        }
        super.checkFeature(featureId);
    }
    
    protected void checkProperty(final String propertyId) throws XMLConfigurationException {
        if (propertyId.startsWith("http://apache.org/xml/properties/")) {
            final String property = propertyId.substring("http://apache.org/xml/properties/".length());
            if (property.equals("internal/dtd-scanner")) {
                return;
            }
        }
        super.checkProperty(propertyId);
    }
    
    protected XMLEntityManager createEntityManager() {
        return new XMLEntityManager();
    }
    
    protected XMLErrorReporter createErrorReporter() {
        return new XMLErrorReporter();
    }
    
    protected XMLDocumentScanner createDocumentScanner() {
        return new XMLDocumentScannerImpl();
    }
    
    protected XMLDTDScanner createDTDScanner() {
        return new XMLDTDScannerImpl();
    }
    
    protected XMLDTDProcessor createDTDProcessor() {
        return new XMLDTDProcessor();
    }
    
    protected XMLDTDValidator createDTDValidator() {
        return new XMLDTDValidator();
    }
    
    protected XMLNamespaceBinder createNamespaceBinder() {
        return new XMLNamespaceBinder();
    }
    
    protected DTDDVFactory createDatatypeValidatorFactory() {
        return DTDDVFactory.getInstance();
    }
    
    protected ValidationManager createValidationManager() {
        return new ValidationManager();
    }
}
