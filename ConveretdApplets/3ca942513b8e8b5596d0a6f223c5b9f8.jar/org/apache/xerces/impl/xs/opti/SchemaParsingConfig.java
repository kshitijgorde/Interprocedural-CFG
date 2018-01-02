// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.opti;

import org.w3c.dom.Document;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import java.io.IOException;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.XNIException;
import java.util.Locale;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
import org.apache.xerces.impl.XMLDTDScannerImpl;
import org.apache.xerces.impl.XMLNSDocumentScannerImpl;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.xni.parser.XMLDTDScanner;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLDocumentScanner;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.dv.DTDDVFactory;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLPullParserConfiguration;
import org.apache.xerces.parsers.BasicParserConfiguration;

public class SchemaParsingConfig extends BasicParserConfiguration implements XMLPullParserConfiguration
{
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String WARN_ON_UNDECLARED_ELEMDEF = "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String GENERATE_SYNTHETIC_ANNOTATION = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String DOCUMENT_SCANNER = "http://apache.org/xml/properties/internal/document-scanner";
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String NAMESPACE_BINDER = "http://apache.org/xml/properties/internal/namespace-binder";
    protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    private static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    protected XMLGrammarPool fGrammarPool;
    protected DTDDVFactory fDatatypeValidatorFactory;
    protected XMLErrorReporter fErrorReporter;
    protected XMLEntityManager fEntityManager;
    protected XMLDocumentScanner fScanner;
    protected XMLInputSource fInputSource;
    protected XMLDTDScanner fDTDScanner;
    protected SchemaDOMParser fSchemaDOMParser;
    protected ValidationManager fValidationManager;
    protected XMLLocator fLocator;
    protected boolean fParseInProgress;
    
    public SchemaParsingConfig() {
        this(null, null, null);
    }
    
    public SchemaParsingConfig(final SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }
    
    public SchemaParsingConfig(final SymbolTable symbolTable, final XMLGrammarPool xmlGrammarPool) {
        this(symbolTable, xmlGrammarPool, null);
    }
    
    public SchemaParsingConfig(final SymbolTable symbolTable, final XMLGrammarPool fGrammarPool, final XMLComponentManager xmlComponentManager) {
        super(symbolTable, xmlComponentManager);
        this.fParseInProgress = false;
        this.addRecognizedFeatures(new String[] { "http://apache.org/xml/features/internal/parser-settings", "http://apache.org/xml/features/validation/warn-on-duplicate-attdef", "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/continue-after-fatal-error", "http://apache.org/xml/features/nonvalidating/load-external-dtd", "http://apache.org/xml/features/scanner/notify-builtin-refs", "http://apache.org/xml/features/scanner/notify-char-refs", "http://apache.org/xml/features/generate-synthetic-annotations" });
        super.fFeatures.put("http://apache.org/xml/features/internal/parser-settings", Boolean.TRUE);
        super.fFeatures.put("http://apache.org/xml/features/validation/warn-on-duplicate-attdef", Boolean.FALSE);
        super.fFeatures.put("http://apache.org/xml/features/validation/warn-on-undeclared-elemdef", Boolean.FALSE);
        super.fFeatures.put("http://apache.org/xml/features/allow-java-encodings", Boolean.FALSE);
        super.fFeatures.put("http://apache.org/xml/features/continue-after-fatal-error", Boolean.FALSE);
        super.fFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", Boolean.TRUE);
        super.fFeatures.put("http://apache.org/xml/features/scanner/notify-builtin-refs", Boolean.FALSE);
        super.fFeatures.put("http://apache.org/xml/features/scanner/notify-char-refs", Boolean.FALSE);
        super.fFeatures.put("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);
        this.addRecognizedProperties(new String[] { "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/document-scanner", "http://apache.org/xml/properties/internal/dtd-scanner", "http://apache.org/xml/properties/internal/validator/dtd", "http://apache.org/xml/properties/internal/namespace-binder", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/internal/datatype-validator-factory", "http://apache.org/xml/properties/internal/validation-manager", "http://apache.org/xml/features/generate-synthetic-annotations" });
        this.fGrammarPool = fGrammarPool;
        if (this.fGrammarPool != null) {
            this.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
        }
        this.fEntityManager = new XMLEntityManager();
        super.fProperties.put("http://apache.org/xml/properties/internal/entity-manager", this.fEntityManager);
        this.addComponent(this.fEntityManager);
        (this.fErrorReporter = new XMLErrorReporter()).setDocumentLocator(this.fEntityManager.getEntityScanner());
        super.fProperties.put("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        this.addComponent(this.fErrorReporter);
        this.fScanner = new XMLNSDocumentScannerImpl();
        super.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", this.fScanner);
        this.addComponent((XMLComponent)this.fScanner);
        this.fDTDScanner = new XMLDTDScannerImpl();
        super.fProperties.put("http://apache.org/xml/properties/internal/dtd-scanner", this.fDTDScanner);
        this.addComponent((XMLComponent)this.fDTDScanner);
        this.fDatatypeValidatorFactory = DTDDVFactory.getInstance();
        super.fProperties.put("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fDatatypeValidatorFactory);
        this.fValidationManager = new ValidationManager();
        super.fProperties.put("http://apache.org/xml/properties/internal/validation-manager", this.fValidationManager);
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            final XMLMessageFormatter xmlMessageFormatter = new XMLMessageFormatter();
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmlMessageFormatter);
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xmlMessageFormatter);
        }
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter());
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
    
    public void setInputSource(final XMLInputSource fInputSource) throws XMLConfigurationException, IOException {
        this.fInputSource = fInputSource;
    }
    
    public boolean parse(final boolean b) throws XNIException, IOException {
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
            return this.fScanner.scanDocument(b);
        }
        catch (XNIException ex5) {
            throw ex5;
        }
        catch (IOException ex6) {
            throw ex6;
        }
        catch (RuntimeException ex7) {
            throw ex7;
        }
        catch (Exception ex8) {
            throw new XNIException(ex8);
        }
    }
    
    public void cleanup() {
        this.fEntityManager.closeReaders();
    }
    
    public void parse(final XMLInputSource inputSource) throws XNIException, IOException {
        if (this.fParseInProgress) {
            throw new XNIException("FWK005 parse may not be called while parsing.");
        }
        this.fParseInProgress = true;
        try {
            this.setInputSource(inputSource);
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
    
    public void reset() throws XNIException {
        if (this.fSchemaDOMParser == null) {
            this.fSchemaDOMParser = new SchemaDOMParser(this);
        }
        super.fDocumentHandler = this.fSchemaDOMParser;
        super.fDTDHandler = this.fSchemaDOMParser;
        super.fDTDContentModelHandler = this.fSchemaDOMParser;
        this.configurePipeline();
        super.reset();
    }
    
    protected void configurePipeline() {
        this.fScanner.setDocumentHandler(super.fDocumentHandler);
        super.fDocumentHandler.setDocumentSource(this.fScanner);
        super.fLastComponent = this.fScanner;
        if (this.fDTDScanner != null) {
            this.fDTDScanner.setDTDHandler(super.fDTDHandler);
            this.fDTDScanner.setDTDContentModelHandler(super.fDTDContentModelHandler);
        }
    }
    
    protected void checkFeature(final String s) throws XMLConfigurationException {
        if (s.startsWith("http://apache.org/xml/features/")) {
            final int n = s.length() - "http://apache.org/xml/features/".length();
            if (n == "validation/dynamic".length() && s.endsWith("validation/dynamic")) {
                return;
            }
            if (n == "validation/default-attribute-values".length() && s.endsWith("validation/default-attribute-values")) {
                throw new XMLConfigurationException((short)1, s);
            }
            if (n == "validation/validate-content-models".length() && s.endsWith("validation/validate-content-models")) {
                throw new XMLConfigurationException((short)1, s);
            }
            if (n == "nonvalidating/load-dtd-grammar".length() && s.endsWith("nonvalidating/load-dtd-grammar")) {
                return;
            }
            if (n == "nonvalidating/load-external-dtd".length() && s.endsWith("nonvalidating/load-external-dtd")) {
                return;
            }
            if (n == "validation/validate-datatypes".length() && s.endsWith("validation/validate-datatypes")) {
                throw new XMLConfigurationException((short)1, s);
            }
        }
        super.checkFeature(s);
    }
    
    protected void checkProperty(final String s) throws XMLConfigurationException {
        if (s.startsWith("http://apache.org/xml/properties/") && s.length() - "http://apache.org/xml/properties/".length() == "internal/dtd-scanner".length() && s.endsWith("internal/dtd-scanner")) {
            return;
        }
        if (s.startsWith("http://java.sun.com/xml/jaxp/properties/") && s.length() - "http://java.sun.com/xml/jaxp/properties/".length() == "schemaSource".length() && s.endsWith("schemaSource")) {
            return;
        }
        super.checkProperty(s);
    }
    
    public Document getDocument() {
        return this.fSchemaDOMParser.getDocument();
    }
    
    public void resetNodePool() {
    }
}
