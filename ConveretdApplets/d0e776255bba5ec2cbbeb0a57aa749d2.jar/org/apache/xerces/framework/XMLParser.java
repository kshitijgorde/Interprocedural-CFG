// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

import org.apache.xerces.validators.datatype.DatatypeMessageProvider;
import org.apache.xerces.validators.schema.SchemaMessageProvider;
import org.apache.xerces.utils.ImplementationMessages;
import org.apache.xerces.utils.XMLMessages;
import org.apache.xerces.utils.QName;
import org.xml.sax.SAXParseException;
import java.io.InputStream;
import java.io.Reader;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.apache.xerces.readers.XMLDeclRecognizer;
import org.apache.xerces.readers.XMLEntityReaderFactory;
import org.xml.sax.Locator;
import org.xml.sax.InputSource;
import org.apache.xerces.validators.common.GrammarResolverImpl;
import org.apache.xerces.readers.XMLEntityHandler;
import org.apache.xerces.utils.ChunkyCharArray;
import org.apache.xerces.validators.common.XMLValidator;
import org.apache.xerces.readers.DefaultEntityHandler;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.utils.XMLMessageProvider;
import java.util.Locale;
import org.xml.sax.ErrorHandler;
import org.apache.xerces.validators.common.GrammarResolver;

public abstract class XMLParser implements XMLErrorReporter, XMLDocumentHandler.DTDHandler
{
    protected static final String SAX2_FEATURES_PREFIX = "http://xml.org/sax/features/";
    protected static final String SAX2_PROPERTIES_PREFIX = "http://xml.org/sax/properties/";
    protected static final String XERCES_FEATURES_PREFIX = "http://apache.org/xml/features/";
    protected static final String XERCES_PROPERTIES_PREFIX = "http://apache.org/xml/properties/";
    private static final String[] RECOGNIZED_FEATURES;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    protected GrammarResolver fGrammarResolver;
    protected boolean fParseInProgress;
    private boolean fNeedReset;
    private boolean fContinueAfterFatalError;
    private ErrorHandler fErrorHandler;
    private Locale fLocale;
    private static XMLMessageProvider fgXMLMessages;
    private static XMLMessageProvider fgImplementationMessages;
    private static XMLMessageProvider fgSchemaMessages;
    private static XMLMessageProvider fgDatatypeMessages;
    protected StringPool fStringPool;
    protected XMLErrorReporter fErrorReporter;
    protected DefaultEntityHandler fEntityHandler;
    protected XMLDocumentScanner fScanner;
    protected XMLValidator fValidator;
    
    protected XMLParser() {
        this(new StringPool());
    }
    
    protected XMLParser(final StringPool fStringPool) {
        this.fGrammarResolver = null;
        this.fParseInProgress = false;
        this.fNeedReset = false;
        this.fContinueAfterFatalError = false;
        this.fErrorHandler = null;
        this.fLocale = null;
        this.fStringPool = null;
        this.fErrorReporter = null;
        this.fEntityHandler = null;
        this.fScanner = null;
        this.fValidator = null;
        this.fStringPool = fStringPool;
        this.fErrorReporter = this;
        this.fEntityHandler = new DefaultEntityHandler(this.fStringPool, this.fErrorReporter);
        this.fScanner = new XMLDocumentScanner(this.fStringPool, this.fErrorReporter, this.fEntityHandler, new ChunkyCharArray(this.fStringPool));
        this.fValidator = new XMLValidator(this.fStringPool, this.fErrorReporter, this.fEntityHandler, this.fScanner);
        this.fGrammarResolver = new GrammarResolverImpl();
        this.fScanner.setGrammarResolver(this.fGrammarResolver);
        this.fValidator.setGrammarResolver(this.fGrammarResolver);
        try {
            this.setNamespaces(true);
        }
        catch (Exception ex) {}
    }
    
    protected void initHandlers(final boolean b, final XMLDocumentHandler xmlDocumentHandler, final XMLDocumentHandler.DTDHandler dtdHandler) {
        this.fValidator.initHandlers(b, xmlDocumentHandler, dtdHandler);
        this.fScanner.setDTDHandler(this);
    }
    
    public String[] getFeaturesRecognized() {
        return XMLParser.RECOGNIZED_FEATURES;
    }
    
    public boolean isFeatureRecognized(final String s) {
        final String[] featuresRecognized = this.getFeaturesRecognized();
        for (int i = 0; i < featuresRecognized.length; ++i) {
            if (s.equals(featuresRecognized[i])) {
                return true;
            }
        }
        return false;
    }
    
    public String[] getPropertiesRecognized() {
        return XMLParser.RECOGNIZED_PROPERTIES;
    }
    
    public boolean isPropertyRecognized(final String s) {
        final String[] propertiesRecognized = this.getPropertiesRecognized();
        for (int i = 0; i < propertiesRecognized.length; ++i) {
            if (s.equals(propertiesRecognized[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean parseSomeSetup(final InputSource inputSource) throws Exception {
        if (this.fNeedReset) {
            this.resetOrCopy();
        }
        this.fParseInProgress = true;
        this.fNeedReset = true;
        return this.fEntityHandler.startReadingFromDocument(inputSource);
    }
    
    public boolean parseSome() throws Exception {
        return this.fScanner.parseSome(false) || (this.fParseInProgress = false);
    }
    
    public void reset() throws Exception {
        this.fGrammarResolver.clearGrammarResolver();
        this.fStringPool.reset();
        this.fEntityHandler.reset(this.fStringPool);
        this.fScanner.reset(this.fStringPool, new ChunkyCharArray(this.fStringPool));
        this.fValidator.reset(this.fStringPool);
        this.fNeedReset = false;
    }
    
    public final Locator getLocator() {
        return this.fEntityHandler;
    }
    
    public void setReaderFactory(final XMLEntityReaderFactory readerFactory) {
        this.fEntityHandler.setReaderFactory(readerFactory);
    }
    
    public void addRecognizer(final XMLDeclRecognizer xmlDeclRecognizer) {
        this.fEntityHandler.addRecognizer(xmlDeclRecognizer);
    }
    
    protected void setValidation(final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("PAR004 Cannot setFeature(http://xml.org/sax/features/validation): parse is in progress.\nhttp://xml.org/sax/features/validation");
        }
        try {
            this.fScanner.setValidationEnabled(b);
            this.fValidator.setValidationEnabled(b);
        }
        catch (Exception ex) {
            throw new SAXNotSupportedException(ex.getMessage());
        }
    }
    
    protected boolean getValidation() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fValidator.getValidationEnabled();
    }
    
    protected void setExternalGeneralEntities(final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("PAR004 Cannot setFeature(http://xml.org/sax/features/external-general-entities): parse is in progress.\nhttp://xml.org/sax/features/external-general-entities");
        }
        if (!b) {
            throw new SAXNotSupportedException("http://xml.org/sax/features/external-general-entities");
        }
    }
    
    protected boolean getExternalGeneralEntities() throws SAXNotRecognizedException, SAXNotSupportedException {
        return true;
    }
    
    protected void setExternalParameterEntities(final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("PAR004 Cannot setFeature(http://xml.org/sax/features/external-general-entities): parse is in progress.\nhttp://xml.org/sax/features/external-general-entities");
        }
        if (!b) {
            throw new SAXNotSupportedException("http://xml.org/sax/features/external-parameter-entities");
        }
    }
    
    protected boolean getExternalParameterEntities() throws SAXNotRecognizedException, SAXNotSupportedException {
        return true;
    }
    
    protected void setNamespaces(final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("PAR004 Cannot setFeature(http://xml.org/sax/features/namespaces): parse is in progress.\nhttp://xml.org/sax/features/namespaces");
        }
        this.fScanner.setNamespacesEnabled(b);
        this.fValidator.setNamespacesEnabled(b);
    }
    
    protected boolean getNamespaces() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fValidator.getNamespacesEnabled();
    }
    
    protected void setValidationSchema(final boolean schemaValidationEnabled) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("http://apache.org/xml/features/validation/schema: parse is in progress");
        }
        this.fValidator.setSchemaValidationEnabled(schemaValidationEnabled);
    }
    
    protected boolean getValidationSchema() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fValidator.getSchemaValidationEnabled();
    }
    
    protected void setValidationDynamic(final boolean dynamicValidationEnabled) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("http://apache.org/xml/features/validation/dynamic: parse is in progress");
        }
        try {
            this.fValidator.setDynamicValidationEnabled(dynamicValidationEnabled);
        }
        catch (Exception ex) {
            throw new SAXNotSupportedException(ex.getMessage());
        }
    }
    
    protected boolean getValidationDynamic() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fValidator.getDynamicValidationEnabled();
    }
    
    protected void setNormalizeAttributeValues(final boolean normalizeAttributeValues) {
        this.fValidator.setNormalizeAttributeValues(normalizeAttributeValues);
    }
    
    protected void setLoadDTDGrammar(final boolean loadDTDGrammar) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("http://apache.org/xml/features/nonvalidating/load-dtd-grammar: parse is in progress");
        }
        try {
            this.fValidator.setLoadDTDGrammar(loadDTDGrammar);
        }
        catch (Exception ex) {
            throw new SAXNotSupportedException(ex.getMessage());
        }
    }
    
    protected boolean getLoadDTDGrammar() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fValidator.getLoadDTDGrammar();
    }
    
    protected void setLoadExternalDTD(final boolean loadExternalDTD) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("http://apache.org/xml/features/nonvalidating/load-external-dtd: parse is in progress");
        }
        try {
            this.fScanner.setLoadExternalDTD(loadExternalDTD);
        }
        catch (Exception ex) {
            throw new SAXNotSupportedException(ex.getMessage());
        }
    }
    
    protected boolean getLoadExternalDTD() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fScanner.getLoadExternalDTD();
    }
    
    protected void setValidationWarnOnDuplicateAttdef(final boolean warningOnDuplicateAttDef) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.fValidator.setWarningOnDuplicateAttDef(warningOnDuplicateAttDef);
    }
    
    protected boolean getValidationWarnOnDuplicateAttdef() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fValidator.getWarningOnDuplicateAttDef();
    }
    
    protected void setValidationWarnOnUndeclaredElemdef(final boolean warningOnUndeclaredElements) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.fValidator.setWarningOnUndeclaredElements(warningOnUndeclaredElements);
    }
    
    protected boolean getValidationWarnOnUndeclaredElemdef() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fValidator.getWarningOnUndeclaredElements();
    }
    
    protected void setAllowJavaEncodings(final boolean allowJavaEncodings) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.fEntityHandler.setAllowJavaEncodings(allowJavaEncodings);
    }
    
    protected boolean getAllowJavaEncodings() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fEntityHandler.getAllowJavaEncodings();
    }
    
    protected void setContinueAfterFatalError(final boolean fContinueAfterFatalError) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.fContinueAfterFatalError = fContinueAfterFatalError;
    }
    
    protected boolean getContinueAfterFatalError() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fContinueAfterFatalError;
    }
    
    protected String getXMLString() throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotSupportedException("http://xml.org/sax/properties/xml-string");
    }
    
    protected void resetOrCopy() throws Exception {
        this.fStringPool = new StringPool();
        this.fEntityHandler.reset(this.fStringPool);
        this.fScanner.reset(this.fStringPool, new ChunkyCharArray(this.fStringPool));
        this.fValidator.resetOrCopy(this.fStringPool);
        this.fNeedReset = false;
        (this.fGrammarResolver = new GrammarResolverImpl()).clearGrammarResolver();
        this.fScanner.setGrammarResolver(this.fGrammarResolver);
        this.fValidator.setGrammarResolver(this.fGrammarResolver);
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.fEntityHandler.setEntityResolver(entityResolver);
    }
    
    public EntityResolver getEntityResolver() {
        return this.fEntityHandler.getEntityResolver();
    }
    
    public void setErrorHandler(final ErrorHandler fErrorHandler) {
        this.fErrorHandler = fErrorHandler;
    }
    
    public ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }
    
    public void parse(final InputSource inputSource) throws SAXException, IOException {
        if (this.fParseInProgress) {
            throw new SAXException("FWK005 parse may not be called while parsing.");
        }
        try {
            if (this.parseSomeSetup(inputSource)) {
                this.fScanner.parseSome(true);
            }
        }
        catch (SAXException ex) {
            throw ex;
        }
        catch (IOException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new SAXException(ex3);
        }
        finally {
            this.fParseInProgress = false;
        }
    }
    
    public void parse(final String s) throws SAXException, IOException {
        final InputSource inputSource = new InputSource(s);
        try {
            this.parse(inputSource);
        }
        finally {
            try {
                final Reader characterStream = inputSource.getCharacterStream();
                if (characterStream != null) {
                    characterStream.close();
                }
                else {
                    final InputStream byteStream = inputSource.getByteStream();
                    if (byteStream != null) {
                        byteStream.close();
                    }
                }
            }
            catch (IOException ex) {}
        }
    }
    
    public void setLocale(final Locale locale) throws SAXException {
        if (this.fParseInProgress) {
            throw new SAXException("FWK006 setLocale may not be called while parsing");
        }
        this.fLocale = locale;
        XMLParser.fgXMLMessages.setLocale(locale);
        XMLParser.fgImplementationMessages.setLocale(locale);
    }
    
    public void reportError(final Locator locator, final String s, final int n, final int n2, final Object[] array, final int n3) throws Exception {
        SAXParseException ex;
        if (s.equals("http://www.w3.org/TR/1998/REC-xml-19980210")) {
            ex = new SAXParseException(XMLParser.fgXMLMessages.createMessage(this.fLocale, n, n2, array), locator);
        }
        else if (s.equals("http://www.w3.org/TR/1999/REC-xml-names-19990114")) {
            ex = new SAXParseException(XMLParser.fgXMLMessages.createMessage(this.fLocale, n, n2, array), locator);
        }
        else if (s.equals("http://www.apache.org/xml/xerces.html")) {
            ex = new SAXParseException(XMLParser.fgImplementationMessages.createMessage(this.fLocale, n, n2, array), locator);
        }
        else if (s.equals("http://www.w3.org/TR/xml-schema-1")) {
            ex = new SAXParseException(XMLParser.fgSchemaMessages.createMessage(this.fLocale, n, n2, array), locator);
        }
        else {
            if (!s.equals("http://www.w3.org/TR/xml-schema-2")) {
                throw new RuntimeException("FWK007 Unknown error domain \"" + s + "\"." + "\n" + s);
            }
            ex = new SAXParseException(XMLParser.fgDatatypeMessages.createMessage(this.fLocale, n, n2, array), locator);
        }
        if (this.fErrorHandler != null) {
            if (n3 == 0) {
                this.fErrorHandler.warning(ex);
            }
            else if (n3 == 2) {
                this.fErrorHandler.fatalError(ex);
                if (!this.fContinueAfterFatalError) {
                    throw new SAXException(XMLParser.fgImplementationMessages.createMessage(this.fLocale, 16, 0, new Object[] { ex.getMessage() }));
                }
            }
            else {
                this.fErrorHandler.error(ex);
            }
            return;
        }
        if (n3 == 2 && !this.fContinueAfterFatalError) {
            throw ex;
        }
    }
    
    public void setFeature(final String s, final boolean continueAfterFatalError) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://xml.org/sax/features/")) {
            final String substring = s.substring("http://xml.org/sax/features/".length());
            if (substring.equals("validation")) {
                this.setValidation(continueAfterFatalError);
                return;
            }
            if (substring.equals("external-general-entities")) {
                this.setExternalGeneralEntities(continueAfterFatalError);
                return;
            }
            if (substring.equals("external-parameter-entities")) {
                this.setExternalParameterEntities(continueAfterFatalError);
                return;
            }
            if (substring.equals("namespaces")) {
                this.setNamespaces(continueAfterFatalError);
                return;
            }
        }
        else if (s.startsWith("http://apache.org/xml/features/")) {
            final String substring2 = s.substring("http://apache.org/xml/features/".length());
            if (substring2.equals("validation/schema")) {
                this.setValidationSchema(continueAfterFatalError);
                return;
            }
            if (substring2.equals("validation/dynamic")) {
                this.setValidationDynamic(continueAfterFatalError);
                return;
            }
            if (substring2.equals("validation/default-attribute-values")) {
                throw new SAXNotSupportedException(s);
            }
            if (substring2.equals("validation/normalize-attribute-values")) {
                this.setNormalizeAttributeValues(continueAfterFatalError);
            }
            if (substring2.equals("validation/validate-content-models")) {
                throw new SAXNotSupportedException(s);
            }
            if (substring2.equals("nonvalidating/load-dtd-grammar")) {
                this.setLoadDTDGrammar(continueAfterFatalError);
                return;
            }
            if (substring2.equals("nonvalidating/load-external-dtd")) {
                this.setLoadExternalDTD(continueAfterFatalError);
                return;
            }
            if (substring2.equals("validation/validate-datatypes")) {
                throw new SAXNotSupportedException(s);
            }
            if (substring2.equals("validation/warn-on-duplicate-attdef")) {
                this.setValidationWarnOnDuplicateAttdef(continueAfterFatalError);
                return;
            }
            if (substring2.equals("validation/warn-on-undeclared-elemdef")) {
                this.setValidationWarnOnUndeclaredElemdef(continueAfterFatalError);
                return;
            }
            if (substring2.equals("allow-java-encodings")) {
                this.setAllowJavaEncodings(continueAfterFatalError);
                return;
            }
            if (substring2.equals("continue-after-fatal-error")) {
                this.setContinueAfterFatalError(continueAfterFatalError);
                return;
            }
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://xml.org/sax/features/")) {
            final String substring = s.substring("http://xml.org/sax/features/".length());
            if (substring.equals("validation")) {
                return this.getValidation();
            }
            if (substring.equals("external-general-entities")) {
                return this.getExternalGeneralEntities();
            }
            if (substring.equals("external-parameter-entities")) {
                return this.getExternalParameterEntities();
            }
            if (substring.equals("namespaces")) {
                return this.getNamespaces();
            }
        }
        else if (s.startsWith("http://apache.org/xml/features/")) {
            final String substring2 = s.substring("http://apache.org/xml/features/".length());
            if (substring2.equals("validation/schema")) {
                return this.getValidationSchema();
            }
            if (substring2.equals("validation/dynamic")) {
                return this.getValidationDynamic();
            }
            if (substring2.equals("validation/default-attribute-values")) {
                throw new SAXNotRecognizedException(s);
            }
            if (substring2.equals("validation/validate-content-models")) {
                throw new SAXNotRecognizedException(s);
            }
            if (substring2.equals("nonvalidating/load-dtd-grammar")) {
                return this.getLoadDTDGrammar();
            }
            if (substring2.equals("nonvalidating/load-external-dtd")) {
                return this.getLoadExternalDTD();
            }
            if (substring2.equals("validation/validate-datatypes")) {
                throw new SAXNotRecognizedException(s);
            }
            if (substring2.equals("validation/warn-on-duplicate-attdef")) {
                return this.getValidationWarnOnDuplicateAttdef();
            }
            if (substring2.equals("validation/warn-on-undeclared-elemdef")) {
                return this.getValidationWarnOnUndeclaredElemdef();
            }
            if (substring2.equals("allow-java-encodings")) {
                return this.getAllowJavaEncodings();
            }
            if (substring2.equals("continue-after-fatal-error")) {
                return this.getContinueAfterFatalError();
            }
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://xml.org/sax/properties/") && s.substring("http://xml.org/sax/properties/".length()).equals("xml-string")) {
            throw new SAXNotSupportedException(s);
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://xml.org/sax/properties/") && s.substring("http://xml.org/sax/properties/".length()).equals("xml-string")) {
            return this.getXMLString();
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public abstract void comment(final int p0) throws Exception;
    
    public abstract void processingInstruction(final int p0, final int p1) throws Exception;
    
    public abstract void notationDecl(final int p0, final int p1, final int p2) throws Exception;
    
    public abstract void unparsedEntityDecl(final int p0, final int p1, final int p2, final int p3) throws Exception;
    
    public abstract void externalEntityDecl(final int p0, final int p1, final int p2) throws Exception;
    
    public abstract void internalEntityDecl(final int p0, final int p1) throws Exception;
    
    public abstract void externalPEDecl(final int p0, final int p1, final int p2) throws Exception;
    
    public abstract void internalPEDecl(final int p0, final int p1) throws Exception;
    
    public abstract void attlistDecl(final QName p0, final QName p1, final int p2, final boolean p3, final String p4, final int p5, final int p6) throws Exception;
    
    public abstract void elementDecl(final QName p0, final int p1, final int p2, final XMLContentSpec.Provider p3) throws Exception;
    
    public abstract void endDTD() throws Exception;
    
    public abstract void textDecl(final int p0, final int p1) throws Exception;
    
    public abstract void internalSubset(final int p0) throws Exception;
    
    public abstract void startDTD(final QName p0, final int p1, final int p2) throws Exception;
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://xml.org/sax/features/external-general-entities", "http://xml.org/sax/features/external-parameter-entities", "http://xml.org/sax/features/namespaces", "http://apache.org/xml/features/validation/schema", "http://apache.org/xml/features/validation/dynamic", "http://apache.org/xml/features/validation/default-attribute-values", "http://apache.org/xml/features/validation/validate-content-models", "http://apache.org/xml/features/validation/validate-datatypes", "http://apache.org/xml/features/validation/warn-on-duplicate-attdef", "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/continue-after-fatal-error", "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", "http://apache.org/xml/features/nonvalidating/load-external-dtd" };
        RECOGNIZED_PROPERTIES = new String[] { "http://xml.org/sax/properties/xml-string" };
        XMLParser.fgXMLMessages = new XMLMessages();
        XMLParser.fgImplementationMessages = new ImplementationMessages();
        XMLParser.fgSchemaMessages = new SchemaMessageProvider();
        XMLParser.fgDatatypeMessages = new DatatypeMessageProvider();
    }
}
