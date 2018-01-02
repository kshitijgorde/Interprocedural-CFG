// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import java.io.EOFException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.util.DefaultErrorHandler;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import java.util.Locale;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLDTDScannerImpl;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.grammars.XMLGrammarLoader;

public class XMLDTDLoader extends XMLDTDProcessor implements XMLGrammarLoader
{
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    private static final String[] LOADER_RECOGNIZED_PROPERTIES;
    protected XMLEntityResolver fEntityResolver;
    protected XMLDTDScannerImpl fDTDScanner;
    protected XMLEntityManager fEntityManager;
    protected Locale fLocale;
    
    public XMLDTDLoader() {
        this(new SymbolTable());
    }
    
    public XMLDTDLoader(final SymbolTable symbolTable) {
        this(symbolTable, null);
    }
    
    public XMLDTDLoader(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null, new XMLEntityManager());
    }
    
    XMLDTDLoader(final SymbolTable symbolTable, final XMLGrammarPool grammarPool, XMLErrorReporter errorReporter, final XMLEntityResolver entityResolver) {
        super.fSymbolTable = symbolTable;
        super.fGrammarPool = grammarPool;
        if (errorReporter == null) {
            errorReporter = new XMLErrorReporter();
            errorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", new DefaultErrorHandler());
        }
        super.fErrorReporter = errorReporter;
        this.fEntityResolver = entityResolver;
        if (this.fEntityResolver instanceof XMLEntityManager) {
            this.fEntityManager = (XMLEntityManager)this.fEntityResolver;
        }
        else {
            this.fEntityManager = new XMLEntityManager();
        }
        (this.fDTDScanner = new XMLDTDScannerImpl(super.fSymbolTable, super.fErrorReporter, this.fEntityManager)).setDTDHandler(this);
        this.fDTDScanner.setDTDContentModelHandler(this);
        this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/error-reporter", super.fErrorReporter);
        this.reset();
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
        if (featureId.equals("http://xml.org/sax/features/validation")) {
            super.fValidation = state;
        }
        else if (featureId.equals("http://apache.org/xml/features/validation/warn-on-duplicate-attdef")) {
            super.fWarnDuplicateAttdef = state;
        }
        else {
            if (!featureId.equals("http://apache.org/xml/features/scanner/notify-char-refs")) {
                throw new XMLConfigurationException((short)0, featureId);
            }
            this.fDTDScanner.setFeature(featureId, state);
        }
    }
    
    public String[] getRecognizedProperties() {
        return XMLDTDLoader.LOADER_RECOGNIZED_PROPERTIES.clone();
    }
    
    public Object getProperty(final String propertyId) throws XMLConfigurationException {
        if (propertyId.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            return super.fSymbolTable;
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            return super.fErrorReporter;
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/error-handler")) {
            return super.fErrorReporter.getErrorHandler();
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            return this.fEntityResolver;
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            return super.fGrammarPool;
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/validator/dtd")) {
            return super.fValidator;
        }
        throw new XMLConfigurationException((short)0, propertyId);
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
        if (propertyId.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            super.fSymbolTable = (SymbolTable)value;
            this.fDTDScanner.setProperty(propertyId, value);
            this.fEntityManager.setProperty(propertyId, value);
        }
        else if (propertyId.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            super.fErrorReporter = (XMLErrorReporter)value;
            this.fDTDScanner.setProperty(propertyId, value);
        }
        else if (propertyId.equals("http://apache.org/xml/properties/internal/error-handler")) {
            super.fErrorReporter.setProperty(propertyId, value);
        }
        else if (propertyId.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityResolver = (XMLEntityResolver)value;
        }
        else {
            if (!propertyId.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
                throw new XMLConfigurationException((short)0, propertyId);
            }
            super.fGrammarPool = (XMLGrammarPool)value;
        }
    }
    
    public boolean getFeature(final String featureId) throws XMLConfigurationException {
        if (featureId.equals("http://xml.org/sax/features/validation")) {
            return super.fValidation;
        }
        if (featureId.equals("http://apache.org/xml/features/validation/warn-on-duplicate-attdef")) {
            return super.fWarnDuplicateAttdef;
        }
        if (featureId.equals("http://apache.org/xml/features/scanner/notify-char-refs")) {
            return this.fDTDScanner.getFeature(featureId);
        }
        throw new XMLConfigurationException((short)0, featureId);
    }
    
    public void setLocale(final Locale locale) {
        this.fLocale = locale;
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    public void setErrorHandler(final XMLErrorHandler errorHandler) {
        super.fErrorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", errorHandler);
    }
    
    public XMLErrorHandler getErrorHandler() {
        return super.fErrorReporter.getErrorHandler();
    }
    
    public void setEntityResolver(final XMLEntityResolver entityResolver) {
        this.fEntityResolver = entityResolver;
    }
    
    public XMLEntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }
    
    public Grammar loadGrammar(final XMLInputSource source) throws IOException, XNIException {
        this.reset();
        this.fDTDScanner.reset();
        super.fDTDGrammar = new DTDGrammar(super.fSymbolTable, new XMLDTDDescription(source.getPublicId(), source.getSystemId(), source.getBaseSystemId(), XMLEntityManager.expandSystemId(source.getSystemId()), null));
        (super.fGrammarBucket = new DTDGrammarBucket()).setStandalone(false);
        super.fGrammarBucket.setActiveGrammar(super.fDTDGrammar);
        this.fDTDScanner.setInputSource(source);
        try {
            this.fDTDScanner.scanDTDExternalSubset(true);
        }
        catch (EOFException ex) {}
        if (super.fDTDGrammar != null && super.fGrammarPool != null) {
            super.fGrammarPool.cacheGrammars("http://www.w3.org/TR/REC-xml", new Grammar[] { super.fDTDGrammar });
        }
        return super.fDTDGrammar;
    }
    
    static {
        LOADER_RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/internal/validator/dtd" };
    }
}
