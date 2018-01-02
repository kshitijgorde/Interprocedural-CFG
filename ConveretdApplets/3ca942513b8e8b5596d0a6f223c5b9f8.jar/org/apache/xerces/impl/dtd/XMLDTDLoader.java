// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import java.io.Reader;
import java.io.StringReader;
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import java.io.EOFException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
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
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    protected static final String BALANCE_SYNTAX_TREES = "http://apache.org/xml/features/validation/balance-syntax-trees";
    private static final String[] LOADER_RECOGNIZED_FEATURES;
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    private static final String[] LOADER_RECOGNIZED_PROPERTIES;
    private boolean fStrictURI;
    private boolean fBalanceSyntaxTrees;
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
    
    public XMLDTDLoader(final SymbolTable symbolTable, final XMLGrammarPool xmlGrammarPool) {
        this(symbolTable, xmlGrammarPool, null, new XMLEntityManager());
    }
    
    XMLDTDLoader(final SymbolTable fSymbolTable, final XMLGrammarPool fGrammarPool, XMLErrorReporter fErrorReporter, final XMLEntityResolver fEntityResolver) {
        this.fStrictURI = false;
        this.fBalanceSyntaxTrees = false;
        super.fSymbolTable = fSymbolTable;
        super.fGrammarPool = fGrammarPool;
        if (fErrorReporter == null) {
            fErrorReporter = new XMLErrorReporter();
            fErrorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", new DefaultErrorHandler());
        }
        super.fErrorReporter = fErrorReporter;
        if (super.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            final XMLMessageFormatter xmlMessageFormatter = new XMLMessageFormatter();
            super.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmlMessageFormatter);
            super.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xmlMessageFormatter);
        }
        this.fEntityResolver = fEntityResolver;
        if (this.fEntityResolver instanceof XMLEntityManager) {
            this.fEntityManager = (XMLEntityManager)this.fEntityResolver;
        }
        else {
            this.fEntityManager = new XMLEntityManager();
        }
        this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/error-reporter", fErrorReporter);
        (this.fDTDScanner = this.createDTDScanner(super.fSymbolTable, super.fErrorReporter, this.fEntityManager)).setDTDHandler(this);
        this.fDTDScanner.setDTDContentModelHandler(this);
        this.reset();
    }
    
    public String[] getRecognizedFeatures() {
        return XMLDTDLoader.LOADER_RECOGNIZED_FEATURES.clone();
    }
    
    public void setFeature(final String s, final boolean b) throws XMLConfigurationException {
        if (s.equals("http://xml.org/sax/features/validation")) {
            super.fValidation = b;
        }
        else if (s.equals("http://apache.org/xml/features/validation/warn-on-duplicate-attdef")) {
            super.fWarnDuplicateAttdef = b;
        }
        else if (s.equals("http://apache.org/xml/features/scanner/notify-char-refs")) {
            this.fDTDScanner.setFeature(s, b);
        }
        else if (s.equals("http://apache.org/xml/features/standard-uri-conformant")) {
            this.fStrictURI = b;
        }
        else {
            if (!s.equals("http://apache.org/xml/features/validation/balance-syntax-trees")) {
                throw new XMLConfigurationException((short)0, s);
            }
            this.fBalanceSyntaxTrees = b;
        }
    }
    
    public String[] getRecognizedProperties() {
        return XMLDTDLoader.LOADER_RECOGNIZED_PROPERTIES.clone();
    }
    
    public Object getProperty(final String s) throws XMLConfigurationException {
        if (s.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            return super.fSymbolTable;
        }
        if (s.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            return super.fErrorReporter;
        }
        if (s.equals("http://apache.org/xml/properties/internal/error-handler")) {
            return super.fErrorReporter.getErrorHandler();
        }
        if (s.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            return this.fEntityResolver;
        }
        if (s.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            return super.fGrammarPool;
        }
        if (s.equals("http://apache.org/xml/properties/internal/validator/dtd")) {
            return super.fValidator;
        }
        throw new XMLConfigurationException((short)0, s);
    }
    
    public void setProperty(final String s, final Object o) throws XMLConfigurationException {
        if (s.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            super.fSymbolTable = (SymbolTable)o;
            this.fDTDScanner.setProperty(s, o);
            this.fEntityManager.setProperty(s, o);
        }
        else if (s.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            super.fErrorReporter = (XMLErrorReporter)o;
            if (super.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
                final XMLMessageFormatter xmlMessageFormatter = new XMLMessageFormatter();
                super.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmlMessageFormatter);
                super.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xmlMessageFormatter);
            }
            this.fDTDScanner.setProperty(s, o);
            this.fEntityManager.setProperty(s, o);
        }
        else if (s.equals("http://apache.org/xml/properties/internal/error-handler")) {
            super.fErrorReporter.setProperty(s, o);
        }
        else if (s.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityResolver = (XMLEntityResolver)o;
            this.fEntityManager.setProperty(s, o);
        }
        else {
            if (!s.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
                throw new XMLConfigurationException((short)0, s);
            }
            super.fGrammarPool = (XMLGrammarPool)o;
        }
    }
    
    public boolean getFeature(final String s) throws XMLConfigurationException {
        if (s.equals("http://xml.org/sax/features/validation")) {
            return super.fValidation;
        }
        if (s.equals("http://apache.org/xml/features/validation/warn-on-duplicate-attdef")) {
            return super.fWarnDuplicateAttdef;
        }
        if (s.equals("http://apache.org/xml/features/scanner/notify-char-refs")) {
            return this.fDTDScanner.getFeature(s);
        }
        if (s.equals("http://apache.org/xml/features/standard-uri-conformant")) {
            return this.fStrictURI;
        }
        if (s.equals("http://apache.org/xml/features/validation/balance-syntax-trees")) {
            return this.fBalanceSyntaxTrees;
        }
        throw new XMLConfigurationException((short)0, s);
    }
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    public void setErrorHandler(final XMLErrorHandler xmlErrorHandler) {
        super.fErrorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", xmlErrorHandler);
    }
    
    public XMLErrorHandler getErrorHandler() {
        return super.fErrorReporter.getErrorHandler();
    }
    
    public void setEntityResolver(final XMLEntityResolver fEntityResolver) {
        this.fEntityResolver = fEntityResolver;
        this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", fEntityResolver);
    }
    
    public XMLEntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }
    
    public Grammar loadGrammar(final XMLInputSource inputSource) throws IOException, XNIException {
        this.reset();
        final XMLDTDDescription xmldtdDescription = new XMLDTDDescription(inputSource.getPublicId(), inputSource.getSystemId(), inputSource.getBaseSystemId(), XMLEntityManager.expandSystemId(inputSource.getSystemId(), inputSource.getBaseSystemId(), this.fStrictURI), null);
        if (!this.fBalanceSyntaxTrees) {
            super.fDTDGrammar = new DTDGrammar(super.fSymbolTable, xmldtdDescription);
        }
        else {
            super.fDTDGrammar = new BalancedDTDGrammar(super.fSymbolTable, xmldtdDescription);
        }
        (super.fGrammarBucket = new DTDGrammarBucket()).setStandalone(false);
        super.fGrammarBucket.setActiveGrammar(super.fDTDGrammar);
        try {
            this.fDTDScanner.setInputSource(inputSource);
            this.fDTDScanner.scanDTDExternalSubset(true);
        }
        catch (EOFException ex) {}
        finally {
            this.fEntityManager.closeReaders();
        }
        if (super.fDTDGrammar != null && super.fGrammarPool != null) {
            super.fGrammarPool.cacheGrammars("http://www.w3.org/TR/REC-xml", new Grammar[] { super.fDTDGrammar });
        }
        return super.fDTDGrammar;
    }
    
    public void loadGrammarWithContext(final XMLDTDValidator xmldtdValidator, final String s, final String s2, final String s3, final String s4, final String s5) throws IOException, XNIException {
        final DTDGrammarBucket grammarBucket = xmldtdValidator.getGrammarBucket();
        final DTDGrammar activeGrammar = grammarBucket.getActiveGrammar();
        if (activeGrammar != null && !activeGrammar.isImmutable()) {
            super.fGrammarBucket = grammarBucket;
            this.fEntityManager.setScannerVersion(this.getScannerVersion());
            this.reset();
            try {
                if (s5 != null) {
                    final StringBuffer sb = new StringBuffer(s5.length() + 2);
                    sb.append(s5).append("]>");
                    this.fEntityManager.startDocumentEntity(new XMLInputSource(null, s4, null, new StringReader(sb.toString()), null));
                    this.fDTDScanner.scanDTDInternalSubset(true, false, s3 != null);
                }
                if (s3 != null) {
                    this.fDTDScanner.setInputSource(this.fEntityManager.resolveEntity(new XMLDTDDescription(s2, s3, s4, null, s)));
                    this.fDTDScanner.scanDTDExternalSubset(true);
                }
            }
            catch (EOFException ex) {}
            finally {
                this.fEntityManager.closeReaders();
            }
        }
    }
    
    protected void reset() {
        super.reset();
        this.fDTDScanner.reset();
        this.fEntityManager.reset();
        super.fErrorReporter.setDocumentLocator(this.fEntityManager.getEntityScanner());
    }
    
    protected XMLDTDScannerImpl createDTDScanner(final SymbolTable symbolTable, final XMLErrorReporter xmlErrorReporter, final XMLEntityManager xmlEntityManager) {
        return new XMLDTDScannerImpl(symbolTable, xmlErrorReporter, xmlEntityManager);
    }
    
    protected short getScannerVersion() {
        return 1;
    }
    
    static {
        LOADER_RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://apache.org/xml/features/validation/warn-on-duplicate-attdef", "http://apache.org/xml/features/scanner/notify-char-refs", "http://apache.org/xml/features/standard-uri-conformant", "http://apache.org/xml/features/validation/balance-syntax-trees" };
        LOADER_RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/internal/validator/dtd" };
    }
}
