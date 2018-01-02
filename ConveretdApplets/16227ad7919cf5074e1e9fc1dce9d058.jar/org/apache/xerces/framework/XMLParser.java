// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

import org.apache.xerces.validators.datatype.DatatypeMessageProvider;
import org.apache.xerces.validators.schema.SchemaMessageProvider;
import org.apache.xerces.utils.ImplementationMessages;
import org.apache.xerces.utils.XMLMessages;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.File;
import org.apache.xerces.utils.ChunkyByteArray;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXParseException;
import java.io.InputStream;
import java.io.Reader;
import java.io.IOException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXException;
import org.apache.xerces.readers.XMLDeclRecognizer;
import org.apache.xerces.utils.XMLCharacterProperties;
import org.apache.xerces.utils.ChunkyCharArray;
import org.xml.sax.InputSource;
import org.xml.sax.EntityResolver;
import java.util.Stack;
import org.apache.xerces.readers.XMLEntityReaderFactory;
import org.apache.xerces.utils.XMLMessageProvider;
import org.apache.xerces.utils.StringPool;
import org.xml.sax.helpers.LocatorImpl;
import java.util.Locale;
import org.apache.xerces.validators.schema.XSchemaValidator;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.Configurable;
import org.apache.xerces.validators.dtd.DTDValidator;
import org.apache.xerces.readers.XMLEntityHandler;

public abstract class XMLParser implements XMLErrorReporter, XMLEntityHandler, XMLDocumentScanner.EventHandler, DTDValidator.EventHandler, Configurable, Locator
{
    protected static final String SAX2_FEATURES_PREFIX = "http://xml.org/sax/features/";
    protected static final String SAX2_PROPERTIES_PREFIX = "http://xml.org/sax/properties/";
    protected static final String SAX2_HANDLERS_PREFIX = "http://xml.org/sax/handlers/";
    protected static final String XERCES_FEATURES_PREFIX = "http://apache.org/xml/features/";
    protected static final String XERCES_PROPERTIES_PREFIX = "http://apache.org/xml/properties/";
    private static final String[] RECOGNIZED_FEATURES;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    private XMLAttrList fAttrList;
    protected boolean fParseInProgress;
    private boolean fNeedReset;
    private boolean fContinueAfterFatalError;
    private ErrorHandler fErrorHandler;
    private char[] fCharRefData;
    private int[] fElementTypeStack;
    private int[] fElementEntityStack;
    private boolean fCalledStartDocument;
    private int fXMLLang;
    protected String fNamespaceSep;
    protected XMLValidator fValidator;
    protected DTDValidator fDTDValidator;
    protected XSchemaValidator fSchemaValidator;
    private boolean fCheckedForSchema;
    private Locator fLocator;
    private Locale fLocale;
    private LocatorImpl fAttrNameLocator;
    private boolean fSeenRootElement;
    private boolean fStandaloneDocument;
    private int fCDATASymbol;
    protected boolean fNamespacesEnabled;
    private boolean fSendCharDataAsCharArray;
    private boolean fValidating;
    private boolean fScanningDTD;
    private StringPool.CharArrayRange fCurrentElementCharArrayRange;
    private static XMLMessageProvider fgXMLMessages;
    private static XMLMessageProvider fgImplementationMessages;
    private static XMLMessageProvider fgSchemaMessages;
    private static XMLMessageProvider fgDatatypeMessages;
    protected XMLDocumentScanner fScanner;
    protected StringPool fStringPool;
    protected XMLErrorReporter fErrorReporter;
    protected XMLEntityHandler fEntityHandler;
    protected XMLEntityReaderFactory fReaderFactory;
    protected int fElementDepth;
    protected int fCurrentElementType;
    protected int fCurrentElementEntity;
    protected boolean fInElementContent;
    private ReaderState fReaderStateFreeList;
    private boolean fAllowJavaEncodingName;
    private Stack fRecognizers;
    private EntityResolver fResolver;
    private byte[] fEntityTypeStack;
    private int[] fEntityNameStack;
    private int fEntityStackDepth;
    private Stack fReaderStack;
    private EntityReader fReader;
    private InputSource fSource;
    private int fEntityName;
    private int fEntityType;
    private int fEntityContext;
    private String fPublicId;
    private String fSystemId;
    private int fReaderId;
    private int fReaderDepth;
    private int fNextReaderId;
    private NullReader fNullReader;
    
    protected XMLParser() {
        this.fParseInProgress = false;
        this.fNeedReset = false;
        this.fElementTypeStack = new int[8];
        this.fElementEntityStack = new int[8];
        this.fCalledStartDocument = false;
        this.fXMLLang = -1;
        this.fNamespaceSep = "";
        this.fCheckedForSchema = false;
        this.fSeenRootElement = false;
        this.fStandaloneDocument = false;
        this.fCDATASymbol = -1;
        this.fNamespacesEnabled = false;
        this.fSendCharDataAsCharArray = false;
        this.fValidating = false;
        this.fScanningDTD = false;
        this.fCurrentElementType = -1;
        this.fCurrentElementEntity = -1;
        this.fInElementContent = false;
        this.fAllowJavaEncodingName = false;
        this.fRecognizers = new Stack();
        this.fReaderStack = new Stack();
        this.fEntityName = -1;
        this.fEntityType = -1;
        this.fEntityContext = -1;
        this.fReaderId = -1;
        this.fReaderDepth = -1;
        this.fStringPool = new StringPool();
        this.fErrorReporter = this;
        this.fEntityHandler = this;
        this.fReaderFactory = new XMLEntityReaderFactory();
        this.fScanner = new XMLDocumentScanner((XMLDocumentScanner.EventHandler)this, this.fStringPool, this.fErrorReporter, this.fEntityHandler, new ChunkyCharArray(this.fStringPool));
        XMLCharacterProperties.initCharFlags();
        this.fAttrList = new XMLAttrList(this.fStringPool);
        this.fLocator = this;
        XMLDeclRecognizer.registerDefaultRecognizers(this.fRecognizers);
        this.fCDATASymbol = this.fStringPool.addSymbol("CDATA");
        this.fDTDValidator = new DTDValidator((DTDValidator.EventHandler)this, this.fStringPool, this.fErrorReporter, this.fEntityHandler);
        try {
            this.setContinueAfterFatalError(false);
        }
        catch (SAXException ex) {}
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
        this.fStringPool.reset();
        this.fAttrList.reset(this.fStringPool);
        this.resetCommon();
    }
    
    public void setLocator(final Locator fLocator) {
        this.fLocator = fLocator;
    }
    
    public final Locator getLocator() {
        return this.fLocator;
    }
    
    public void processingInstructionInDTD(final int n, final int n2) throws Exception {
        this.fStringPool.releaseString(n);
        this.fStringPool.releaseString(n2);
    }
    
    public void commentInDTD(final int n) throws Exception {
        this.fStringPool.releaseString(n);
    }
    
    public abstract void startDocument(final int p0, final int p1, final int p2) throws Exception;
    
    public abstract void endDocument() throws Exception;
    
    public abstract void startNamespaceDeclScope(final int p0, final int p1) throws Exception;
    
    public abstract void endNamespaceDeclScope(final int p0) throws Exception;
    
    public abstract void startElement(final int p0, final XMLAttrList p1, final int p2) throws Exception;
    
    public abstract void endElement(final int p0) throws Exception;
    
    public abstract void startEntityReference(final int p0, final int p1, final int p2) throws Exception;
    
    public abstract void endEntityReference(final int p0, final int p1, final int p2) throws Exception;
    
    public abstract void startCDATA() throws Exception;
    
    public abstract void endCDATA() throws Exception;
    
    public abstract void processingInstruction(final int p0, final int p1) throws Exception;
    
    public abstract void comment(final int p0) throws Exception;
    
    public abstract void characters(final int p0) throws Exception;
    
    public abstract void characters(final char[] p0, final int p1, final int p2) throws Exception;
    
    public abstract void ignorableWhitespace(final int p0) throws Exception;
    
    public abstract void ignorableWhitespace(final char[] p0, final int p1, final int p2) throws Exception;
    
    public abstract void startDTD(final int p0, final int p1, final int p2) throws Exception;
    
    public abstract void endDTD() throws Exception;
    
    public abstract void elementDecl(final int p0, final ContentSpec p1) throws Exception;
    
    public abstract void attlistDecl(final int p0, final int p1, final int p2, final String p3, final int p4, final int p5) throws Exception;
    
    public abstract void internalPEDecl(final int p0, final int p1) throws Exception;
    
    public abstract void externalPEDecl(final int p0, final int p1, final int p2) throws Exception;
    
    public abstract void internalEntityDecl(final int p0, final int p1) throws Exception;
    
    public abstract void externalEntityDecl(final int p0, final int p1, final int p2) throws Exception;
    
    public abstract void unparsedEntityDecl(final int p0, final int p1, final int p2, final int p3) throws Exception;
    
    public abstract void notationDecl(final int p0, final int p1, final int p2) throws Exception;
    
    protected void setValidation(final boolean b) throws SAXException {
        try {
            this.fDTDValidator.setValidationEnabled(b);
            this.getSchemaValidator().setValidationEnabled(b);
        }
        catch (Exception ex) {
            throw new SAXException(ex);
        }
    }
    
    protected boolean getValidation() throws SAXException {
        return this.fDTDValidator.getValidationEnabled();
    }
    
    protected void setExternalGeneralEntities(final boolean b) throws SAXException {
        if (!b) {
            throw new SAXNotSupportedException("http://xml.org/sax/features/external-general-entities");
        }
    }
    
    protected boolean getExternalGeneralEntities() throws SAXException {
        return true;
    }
    
    protected void setExternalParameterEntities(final boolean b) throws SAXException {
        if (!b) {
            throw new SAXNotSupportedException("http://xml.org/sax/features/external-parameter-entities");
        }
    }
    
    protected boolean getExternalParameterEntities() throws SAXException {
        return true;
    }
    
    protected void setNamespaces(final boolean namespacesEnabled) throws SAXException {
        this.fNamespacesEnabled = namespacesEnabled;
        this.fDTDValidator.setNamespacesEnabled(namespacesEnabled);
        this.getSchemaValidator().setNamespacesEnabled(namespacesEnabled);
    }
    
    protected boolean getNamespaces() throws SAXException {
        return this.fNamespacesEnabled;
    }
    
    protected void setValidationDynamic(final boolean b) throws SAXException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("http://apache.org/xml/features/validation/dynamic: parse is in progress");
        }
        try {
            this.fDTDValidator.setDynamicValidationEnabled(b);
            this.getSchemaValidator().setDynamicValidationEnabled(b);
        }
        catch (Exception ex) {
            throw new SAXException(ex);
        }
    }
    
    protected boolean getValidationDynamic() throws SAXException {
        return this.fDTDValidator.getDynamicValidationEnabled();
    }
    
    protected void setValidationWarnOnDuplicateAttdef(final boolean b) throws SAXException {
        this.fDTDValidator.setWarningOnDuplicateAttDef(b);
        this.getSchemaValidator().setWarningOnDuplicateAttDef(b);
    }
    
    protected boolean getValidationWarnOnDuplicateAttdef() throws SAXException {
        return this.fDTDValidator.getWarningOnDuplicateAttDef();
    }
    
    protected void setValidationWarnOnUndeclaredElemdef(final boolean b) throws SAXException {
        this.fDTDValidator.setWarningOnUndeclaredElements(b);
        this.getSchemaValidator().setWarningOnUndeclaredElements(b);
    }
    
    protected boolean getValidationWarnOnUndeclaredElemdef() throws SAXException {
        return this.fDTDValidator.getWarningOnUndeclaredElements();
    }
    
    protected void setAllowJavaEncodings(final boolean allowJavaEncodingName) throws SAXException {
        this.setAllowJavaEncodingName(allowJavaEncodingName);
    }
    
    protected boolean getAllowJavaEncodings() throws SAXException {
        return this.getAllowJavaEncodingName();
    }
    
    protected void setContinueAfterFatalError(final boolean fContinueAfterFatalError) throws SAXException {
        this.fContinueAfterFatalError = fContinueAfterFatalError;
    }
    
    protected boolean getContinueAfterFatalError() throws SAXException {
        return this.fContinueAfterFatalError;
    }
    
    protected void setNamespaceSep(final String fNamespaceSep) throws SAXException {
        if (this.fParseInProgress) {
            throw new SAXNotSupportedException("http://xml.org/sax/properties/namespace-sep: parse is in progress");
        }
        this.fNamespaceSep = fNamespaceSep;
    }
    
    protected String getNamespaceSep() throws SAXException {
        return this.fNamespaceSep;
    }
    
    protected String getXMLString() throws SAXException {
        throw new SAXNotSupportedException("http://xml.org/sax/properties/xml-string");
    }
    
    protected void resetOrCopy() throws Exception {
        this.fStringPool = new StringPool();
        this.fAttrList = new XMLAttrList(this.fStringPool);
        this.resetCommon();
    }
    
    private void resetCommon() throws Exception {
        this.fScanner.reset(this.fStringPool, new ChunkyCharArray(this.fStringPool));
        this.fValidating = false;
        this.fScanningDTD = false;
        this.resetEntityHandler();
        this.fValidator = null;
        this.fDTDValidator.reset(this.fStringPool);
        if (this.fSchemaValidator != null) {
            this.fSchemaValidator.reset(this.fStringPool, this.fErrorReporter, this.fEntityHandler);
        }
        this.fCheckedForSchema = false;
        this.fNeedReset = false;
        this.fCalledStartDocument = false;
        this.fSeenRootElement = false;
        this.fStandaloneDocument = false;
        this.fCDATASymbol = this.fStringPool.addSymbol("CDATA");
        this.fXMLLang = -1;
        this.fElementDepth = 0;
        this.fCurrentElementType = -1;
        this.fCurrentElementEntity = -1;
        this.fInElementContent = false;
    }
    
    protected XSchemaValidator getSchemaValidator() {
        if (this.fSchemaValidator == null) {
            this.fSchemaValidator = new XSchemaValidator(this.fStringPool, this.fErrorReporter, this.fEntityHandler);
        }
        return this.fSchemaValidator;
    }
    
    protected void setSendCharDataAsCharArray(final boolean fSendCharDataAsCharArray) {
        this.fSendCharDataAsCharArray = fSendCharDataAsCharArray;
    }
    
    public void setErrorHandler(final ErrorHandler fErrorHandler) {
        this.fErrorHandler = fErrorHandler;
    }
    
    public void parse(final InputSource inputSource) throws SAXException, IOException {
        if (this.fParseInProgress) {
            throw new SAXException("parse may not be called while parsing");
        }
        try {
            if (this.parseSomeSetup(inputSource)) {
                this.fScanner.parseSome(true);
            }
            this.fParseInProgress = false;
        }
        catch (SAXException ex) {
            this.fParseInProgress = false;
            throw ex;
        }
        catch (IOException ex2) {
            this.fParseInProgress = false;
            throw ex2;
        }
        catch (Exception ex3) {
            this.fParseInProgress = false;
            throw new SAXException(ex3);
        }
    }
    
    public void parse(final String s) throws SAXException, IOException {
        final InputSource inputSource = new InputSource(s);
        this.parse(inputSource);
        try {
            final Reader characterStream = inputSource.getCharacterStream();
            if (characterStream != null) {
                characterStream.close();
                return;
            }
            final InputStream byteStream = inputSource.getByteStream();
            if (byteStream != null) {
                byteStream.close();
            }
        }
        catch (IOException ex) {}
    }
    
    public void setLocale(final Locale locale) throws SAXException {
        if (this.fParseInProgress) {
            throw new SAXException("setLocale may not be called while parsing");
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
                throw new RuntimeException("Unknown error domain \"" + s + "\".");
            }
            ex = new SAXParseException(XMLParser.fgDatatypeMessages.createMessage(this.fLocale, n, n2, array), locator);
        }
        if (this.fErrorHandler == null) {
            if (n3 == 2 && !this.fContinueAfterFatalError) {
                throw ex;
            }
        }
        else {
            if (n3 == 0) {
                this.fErrorHandler.warning(ex);
                return;
            }
            if (n3 == 2) {
                this.fErrorHandler.fatalError(ex);
                if (!this.fContinueAfterFatalError) {
                    throw new SAXException(XMLParser.fgImplementationMessages.createMessage(this.fLocale, 16, 0, new Object[] { ex.getMessage() }));
                }
            }
            else {
                this.fErrorHandler.error(ex);
            }
        }
    }
    
    public void setFeature(final String s, final boolean continueAfterFatalError) throws SAXException {
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
            if (substring2.equals("validation/dynamic")) {
                this.setValidationDynamic(continueAfterFatalError);
                return;
            }
            if (substring2.equals("validation/default-attribute-values")) {
                throw new SAXNotSupportedException(s);
            }
            if (substring2.equals("validation/validate-content-models")) {
                throw new SAXNotSupportedException(s);
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
    
    public boolean getFeature(final String s) throws SAXException {
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
            if (substring2.equals("validation/dynamic")) {
                return this.getValidationDynamic();
            }
            if (substring2.equals("validation/default-attribute-values")) {
                throw new SAXNotRecognizedException(s);
            }
            if (substring2.equals("validation/validate-content-models")) {
                throw new SAXNotRecognizedException(s);
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
    
    public void setProperty(final String s, final Object o) throws SAXException {
        if (s.startsWith("http://xml.org/sax/properties/")) {
            final String substring = s.substring("http://xml.org/sax/properties/".length());
            if (substring.equals("namespace-sep")) {
                try {
                    this.setNamespaceSep((String)o);
                    return;
                }
                catch (ClassCastException ex) {
                    throw new SAXNotSupportedException(s);
                }
            }
            if (substring.equals("xml-string")) {
                throw new SAXNotSupportedException(s);
            }
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public Object getProperty(final String s) throws SAXException {
        if (s.startsWith("http://xml.org/sax/properties/")) {
            final String substring = s.substring("http://xml.org/sax/properties/".length());
            if (substring.equals("namespace-sep")) {
                return this.getNamespaceSep();
            }
            if (substring.equals("xml-string")) {
                return this.getXMLString();
            }
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public boolean validVersionNum(final String s) {
        return XMLCharacterProperties.validVersionNum(s);
    }
    
    public boolean validEncName(final String s) {
        return XMLCharacterProperties.validEncName(s);
    }
    
    public void callStartDocument(final int n, final int n2, final int n3) throws Exception {
        if (!this.fCalledStartDocument) {
            this.startDocument(n, n2, n3);
            this.fCalledStartDocument = true;
        }
    }
    
    public void callEndDocument() throws Exception {
        if (this.fCalledStartDocument) {
            this.endDocument();
        }
    }
    
    public void callStartElement(final int fCurrentElementType) throws Exception {
        if (!this.fSeenRootElement) {
            this.fSeenRootElement = true;
            if (this.fValidator == null) {
                this.fValidator = this.fDTDValidator;
            }
            this.fValidator.rootElementSpecified(fCurrentElementType);
            this.fStringPool.resetShuffleCount();
        }
        this.fInElementContent = this.fValidator.startElement(fCurrentElementType, this.fAttrList);
        final int attrListHandle = this.fAttrList.attrListHandle();
        if (attrListHandle != -1) {
            this.fAttrList.endAttrList();
            if (this.fXMLLang == -1) {
                this.fXMLLang = this.fStringPool.addSymbol("xml:lang");
            }
            for (int i = this.fAttrList.getFirstAttr(attrListHandle); i != -1; i = this.fAttrList.getNextAttr(i)) {
                if (this.fStringPool.equalNames(this.fAttrList.getAttrName(i), this.fXMLLang)) {
                    this.fScanner.checkXMLLangAttributeValue(this.fAttrList.getAttValue(i));
                    break;
                }
            }
        }
        this.startElement(fCurrentElementType, this.fAttrList, attrListHandle);
        final int readerId = this.fEntityHandler.getReaderId();
        if (this.fElementDepth == this.fElementTypeStack.length) {
            final int[] fElementTypeStack = new int[this.fElementDepth * 2];
            System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack, 0, this.fElementDepth);
            this.fElementTypeStack = fElementTypeStack;
            final int[] fElementEntityStack = new int[this.fElementDepth * 2];
            System.arraycopy(this.fElementEntityStack, 0, fElementEntityStack, 0, this.fElementDepth);
            this.fElementEntityStack = fElementEntityStack;
        }
        this.fCurrentElementType = fCurrentElementType;
        this.fCurrentElementEntity = readerId;
        this.fElementTypeStack[this.fElementDepth] = fCurrentElementType;
        this.fElementEntityStack[this.fElementDepth] = readerId;
        ++this.fElementDepth;
    }
    
    public boolean callEndElement(final int n) throws Exception {
        final int fCurrentElementType = this.fCurrentElementType;
        if (this.fCurrentElementEntity != n) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 68, 68, new Object[] { this.fStringPool.toString(fCurrentElementType) }, 2);
        }
        this.endElement(fCurrentElementType);
        this.fInElementContent = this.fValidator.endElement(fCurrentElementType);
        if (this.fElementDepth-- == 0) {
            throw new RuntimeException("Element stack underflow");
        }
        if (this.fElementDepth == 0) {
            this.fCurrentElementType = -1;
            this.fCurrentElementEntity = -1;
            return true;
        }
        this.fCurrentElementType = this.fElementTypeStack[this.fElementDepth - 1];
        this.fCurrentElementEntity = this.fElementEntityStack[this.fElementDepth - 1];
        return false;
    }
    
    public void callProcessingInstruction(final int n, final int n2) throws Exception {
        this.processingInstruction(n, n2);
    }
    
    public void callComment(final int n) throws Exception {
        this.comment(n);
    }
    
    public void callCharacters(final int n) throws Exception {
        if (this.fCharRefData == null) {
            this.fCharRefData = new char[2];
        }
        final int n2 = (n < 65536) ? 1 : 2;
        if (n2 == 1) {
            this.fCharRefData[0] = (char)n;
        }
        else {
            this.fCharRefData[0] = (char)((n - 65536 >> 10) + 55296);
            this.fCharRefData[1] = (char)((n - 65536 & 0x3FF) + 56320);
        }
        if (!this.fInElementContent) {
            this.fValidator.characters(this.fCharRefData, 0, n2);
        }
        this.characters(this.fCharRefData, 0, n2);
    }
    
    public int scanAttValue(final int n, final int n2) throws Exception {
        this.fAttrNameLocator = this.getLocatorImpl(this.fAttrNameLocator);
        final int scanAttValue = this.fScanner.scanAttValue(n, n2, this.fValidating);
        if (scanAttValue == -1) {
            return -1;
        }
        if (!this.fCheckedForSchema) {
            this.fCheckedForSchema = true;
            if (n2 == this.fStringPool.addSymbol("xmlns")) {
                this.fValidator = this.getSchemaValidator();
                this.fSchemaValidator.loadSchema(this.fStringPool.toString(scanAttValue));
            }
        }
        if (!this.fValidator.attributeSpecified(n, this.fAttrList, n2, this.fAttrNameLocator, scanAttValue)) {
            return -2;
        }
        return 0;
    }
    
    public int scanElementType(final EntityReader entityReader, final char c) throws Exception {
        if (!this.fNamespacesEnabled) {
            return entityReader.scanName(c);
        }
        final int scanQName = entityReader.scanQName(c);
        if (this.fNamespacesEnabled && entityReader.lookingAtChar(':', false)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
            entityReader.skipPastNmtoken(' ');
        }
        return scanQName;
    }
    
    public boolean scanExpectedElementType(final EntityReader entityReader, final char c) throws Exception {
        if (this.fCurrentElementCharArrayRange == null) {
            this.fCurrentElementCharArrayRange = this.fStringPool.createCharArrayRange();
        }
        this.fStringPool.getCharArrayRange(this.fCurrentElementType, this.fCurrentElementCharArrayRange);
        return entityReader.scanExpectedName(c, this.fCurrentElementCharArrayRange);
    }
    
    public int scanAttributeName(final EntityReader entityReader, final int n) throws Exception {
        if (!this.fSeenRootElement) {
            this.fSeenRootElement = true;
            if (this.fValidator == null) {
                this.fValidator = this.fDTDValidator;
            }
            this.fValidator.rootElementSpecified(n);
            this.fStringPool.resetShuffleCount();
        }
        if (!this.fNamespacesEnabled) {
            return entityReader.scanName('=');
        }
        final int scanQName = entityReader.scanQName('=');
        if (entityReader.lookingAtChar(':', false)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
            entityReader.skipPastNmtoken(' ');
        }
        return scanQName;
    }
    
    public void scanDoctypeDecl(final boolean fStandaloneDocument) throws Exception {
        this.fScanningDTD = true;
        this.fCheckedForSchema = true;
        this.fStandaloneDocument = fStandaloneDocument;
        this.fValidator = this.fDTDValidator;
        this.fDTDValidator.scanDoctypeDecl(fStandaloneDocument);
        this.fScanningDTD = false;
    }
    
    public void setValidating(final boolean fValidating) throws Exception {
        this.fValidating = fValidating;
    }
    
    private LocatorImpl getLocatorImpl(final LocatorImpl locatorImpl) {
        if (locatorImpl == null) {
            return new LocatorImpl(this);
        }
        locatorImpl.setPublicId(this.getPublicId());
        locatorImpl.setSystemId(this.getSystemId());
        locatorImpl.setLineNumber(this.getLineNumber());
        locatorImpl.setColumnNumber(this.getColumnNumber());
        return locatorImpl;
    }
    
    public void processCharacters(final char[] array, final int n, final int n2) throws Exception {
        if (this.fValidating && !this.fInElementContent) {
            this.fValidator.characters(array, n, n2);
        }
        this.characters(array, n, n2);
    }
    
    public void processCharacters(final int n) throws Exception {
        if (this.fValidating && !this.fInElementContent) {
            this.fValidator.characters(n);
        }
        this.characters(n);
    }
    
    public void processWhitespace(final char[] array, final int n, final int n2) throws Exception {
        if (this.fInElementContent) {
            if (this.fStandaloneDocument && this.fValidating) {
                this.fValidator.ignorableWhitespace(array, n, n2);
            }
            this.ignorableWhitespace(array, n, n2);
            return;
        }
        if (this.fValidating && !this.fInElementContent) {
            this.fValidator.characters(array, n, n2);
        }
        this.characters(array, n, n2);
    }
    
    public void processWhitespace(final int n) throws Exception {
        if (this.fInElementContent) {
            if (this.fStandaloneDocument && this.fValidating) {
                this.fValidator.ignorableWhitespace(n);
            }
            this.ignorableWhitespace(n);
            return;
        }
        if (this.fValidating && !this.fInElementContent) {
            this.fValidator.characters(n);
        }
        this.characters(n);
    }
    
    private void resetEntityHandler() {
        this.fReaderStack.removeAllElements();
        this.fEntityStackDepth = 0;
        this.fReader = null;
        this.fSource = null;
        this.fEntityName = -1;
        this.fEntityType = -1;
        this.fEntityContext = -1;
        this.fPublicId = null;
        this.fSystemId = null;
        this.fReaderId = -1;
        this.fReaderDepth = -1;
        this.fNextReaderId = 0;
    }
    
    private void setAllowJavaEncodingName(final boolean fAllowJavaEncodingName) {
        this.fAllowJavaEncodingName = fAllowJavaEncodingName;
    }
    
    private boolean getAllowJavaEncodingName() {
        return this.fAllowJavaEncodingName;
    }
    
    public EntityReader getEntityReader() {
        return this.fReader;
    }
    
    public void addRecognizer(final XMLDeclRecognizer xmlDeclRecognizer) {
        this.fRecognizers.push(xmlDeclRecognizer);
    }
    
    public void setEntityResolver(final EntityResolver fResolver) {
        this.fResolver = fResolver;
    }
    
    public String expandSystemId(final String s) {
        return this.expandSystemId(s, this.fSystemId);
    }
    
    private String expandSystemId(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            return s;
        }
        try {
            if (new URL(s) != null) {
                return s;
            }
        }
        catch (MalformedURLException ex) {}
        final String fixURI = fixURI(s);
        URL url = null;
        try {
            URL url2;
            if (s2 == null) {
                String s3;
                try {
                    s3 = fixURI(System.getProperty("user.dir"));
                }
                catch (SecurityException ex2) {
                    s3 = "";
                }
                if (!s3.endsWith("/")) {
                    s3 = String.valueOf(s3) + "/";
                }
                url2 = new URL("file", "", s3);
            }
            else {
                url2 = new URL(s2);
            }
            url = new URL(url2, fixURI);
        }
        catch (Exception ex3) {}
        if (url == null) {
            return s;
        }
        return url.toString();
    }
    
    private EntityReader callRecognizers(final InputSource inputSource, final ChunkyByteArray chunkyByteArray, final boolean b) throws Exception {
        for (int i = this.fRecognizers.size() - 1; i >= 0; --i) {
            final EntityReader recognize = ((XMLDeclRecognizer)this.fRecognizers.elementAt(i)).recognize(this.fReaderFactory, this, this.fErrorReporter, this.fSendCharDataAsCharArray, this.fStringPool, chunkyByteArray, b, this.fAllowJavaEncodingName);
            if (recognize != null) {
                return recognize;
            }
        }
        return null;
    }
    
    private static String fixURI(String s) {
        s = s.replace(File.separatorChar, '/');
        if (s.length() >= 2 && s.charAt(1) == ':') {
            final char upperCase = Character.toUpperCase(s.charAt(0));
            if (upperCase >= 'A' && upperCase <= 'Z') {
                s = "/" + s;
            }
        }
        return s;
    }
    
    private void sendEndOfInputNotifications() throws Exception {
        final boolean b = this.fReaderStack.size() > 1;
        this.fScanner.endOfInput(this.fEntityName, b);
        if (this.fScanningDTD) {
            this.fDTDValidator.endOfInput(this.fEntityName, b);
        }
    }
    
    private void sendReaderChangeNotifications() throws Exception {
        this.fScanner.readerChange(this.fReader, this.fReaderId);
        if (this.fScanningDTD) {
            this.fDTDValidator.readerChange(this.fReader, this.fReaderId);
        }
    }
    
    private void sendStartEntityNotifications() throws Exception {
        this.startEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
    }
    
    private void sendEndEntityNotifications() throws Exception {
        this.endEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
    }
    
    public boolean startReadingFromDocument(final InputSource fSource) throws Exception {
        this.pushEntity(false, -2);
        this.fSystemId = null;
        this.pushNullReader();
        this.fEntityName = -2;
        this.fEntityType = 5;
        this.fEntityContext = 6;
        this.fReaderDepth = 0;
        this.fReaderId = this.fNextReaderId++;
        this.fPublicId = fSource.getPublicId();
        this.fSystemId = fSource.getSystemId();
        this.sendStartEntityNotifications();
        this.fSystemId = this.expandSystemId(this.fSystemId, null);
        this.fSource = fSource;
        final boolean b = true;
        try {
            this.createReader(b);
        }
        catch (UnsupportedEncodingException ex) {
            final String message = ex.getMessage();
            if (message == null) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 70, 72, null, 2);
            }
            else if (!XMLCharacterProperties.validEncName(message)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 40, 42, new Object[] { message }, 2);
            }
            else {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 61, 60, new Object[] { message }, 2);
            }
            this.fReader = null;
            this.sendReaderChangeNotifications();
            return false;
        }
        this.sendReaderChangeNotifications();
        return true;
    }
    
    public void startReadingFromExternalSubset(final String fPublicId, final String fSystemId, final int fReaderDepth) throws Exception {
        this.pushEntity(true, -1);
        this.pushReader();
        this.pushNullReader();
        this.fEntityName = -1;
        this.fEntityType = 6;
        this.fEntityContext = 7;
        this.fReaderDepth = fReaderDepth;
        this.fReaderId = this.fNextReaderId++;
        this.fPublicId = fPublicId;
        this.fSystemId = fSystemId;
        this.startReadingFromExternalEntity(false);
    }
    
    public void stopReadingFromExternalSubset() throws Exception {
        if (!(this.fReader instanceof NullReader)) {
            throw new RuntimeException("cannot happen 18");
        }
        this.popReader();
        this.sendReaderChangeNotifications();
    }
    
    public boolean startReadingFromEntity(final int fEntityName, final int fReaderDepth, final int fEntityContext) throws Exception {
        if (fEntityContext > 2) {
            return this.startReadingFromParameterEntity(fEntityName, fReaderDepth, fEntityContext);
        }
        final int lookupEntity = this.fValidator.lookupEntity(fEntityName);
        if (lookupEntity < 0) {
            int n = 62;
            int n2 = 1;
            if (this.fEntityContext == 6 || this.fEntityContext == 0) {
                n = 61;
                n2 = 2;
            }
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 62, n, new Object[] { this.fStringPool.toString(fEntityName) }, n2);
            return false;
        }
        if (fEntityContext == 2) {
            if (this.fValidator.isUnparsedEntity(lookupEntity)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 63, 63, new Object[] { this.fStringPool.toString(fEntityName) }, 2);
                return false;
            }
        }
        else if (this.fValidator.isExternalEntity(lookupEntity)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 64, 64, new Object[] { this.fStringPool.toString(fEntityName) }, 2);
            return false;
        }
        if (!this.pushEntity(false, fEntityName)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 71, 73, new Object[] { this.fStringPool.toString(fEntityName), this.entityReferencePath(false, fEntityName) }, 2);
            return false;
        }
        this.pushReader();
        this.fEntityName = fEntityName;
        this.fEntityContext = fEntityContext;
        this.fReaderDepth = fReaderDepth;
        this.fReaderId = this.fNextReaderId++;
        if (fEntityContext != 2 || !this.fValidator.externalReferenceInContent(lookupEntity)) {
            this.fEntityType = 2;
            this.fPublicId = null;
            this.fSystemId = this.fSystemId;
            int n3;
            if (fEntityContext == 2 || fEntityContext == 1) {
                n3 = this.fValidator.getEntityValue(lookupEntity);
            }
            else {
                n3 = this.fValidator.valueOfReferenceInAttValue(lookupEntity);
            }
            this.startReadingFromInternalEntity(n3, false);
            return false;
        }
        this.fEntityType = 3;
        this.fPublicId = this.fValidator.getPublicIdOfEntity(lookupEntity);
        this.fSystemId = this.fValidator.getSystemIdOfEntity(lookupEntity);
        return this.startReadingFromExternalEntity(true);
    }
    
    private boolean startReadingFromParameterEntity(final int fEntityName, final int fReaderDepth, final int fEntityContext) throws Exception {
        final int lookupParameterEntity = this.fValidator.lookupParameterEntity(fEntityName);
        if (lookupParameterEntity == -1) {
            return false;
        }
        if (!this.pushEntity(true, fEntityName)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 72, 73, new Object[] { this.fStringPool.toString(fEntityName), this.entityReferencePath(true, fEntityName) }, 2);
            return false;
        }
        this.pushReader();
        this.fEntityName = fEntityName;
        this.fEntityContext = fEntityContext;
        this.fReaderDepth = fReaderDepth;
        this.fReaderId = this.fNextReaderId++;
        if (!this.fValidator.isExternalParameterEntity(lookupParameterEntity)) {
            this.fEntityType = 0;
            this.fPublicId = null;
            this.fSystemId = this.fSystemId;
            this.startReadingFromInternalEntity(this.fValidator.getParameterEntityValue(lookupParameterEntity), this.fEntityContext != 4);
            return false;
        }
        this.fEntityType = 1;
        this.fPublicId = this.fValidator.getPublicIdOfParameterEntity(lookupParameterEntity);
        this.fSystemId = this.fValidator.getSystemIdOfParameterEntity(lookupParameterEntity);
        return this.startReadingFromExternalEntity(true);
    }
    
    private void startReadingFromInternalEntity(final int n, final boolean b) throws Exception {
        this.fSource = null;
        this.sendStartEntityNotifications();
        this.fReader = this.fReaderFactory.createStringReader(this, this.fErrorReporter, this.fSendCharDataAsCharArray, this.getLineNumber(), this.getColumnNumber(), n, this.fStringPool, b);
        this.sendReaderChangeNotifications();
    }
    
    private boolean startReadingFromExternalEntity(final boolean b) throws Exception {
        this.sendStartEntityNotifications();
        this.fSystemId = this.expandSystemId(this.fSystemId, this.fReaderStack.peek().systemId);
        this.fSource = ((this.fResolver == null) ? null : this.fResolver.resolveEntity(this.fPublicId, this.fSystemId));
        if (this.fSource == null) {
            this.fSource = new InputSource(this.fSystemId);
            if (this.fPublicId != null) {
                this.fSource.setPublicId(this.fPublicId);
            }
        }
        final boolean b2 = false;
        try {
            this.createReader(b2);
        }
        catch (UnsupportedEncodingException ex) {
            final String message = ex.getMessage();
            if (message == null) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 70, 72, null, 2);
            }
            else if (!XMLCharacterProperties.validEncName(message)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 40, 42, new Object[] { message }, 2);
            }
            else {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 61, 60, new Object[] { message }, 2);
            }
        }
        if (this.fReader == null || !b) {
            this.sendReaderChangeNotifications();
            return false;
        }
        final int fReaderId = this.fReaderId;
        this.sendReaderChangeNotifications();
        boolean lookingAtChar = this.fReader.lookingAtChar('<', false);
        if (fReaderId != this.fReaderId) {
            lookingAtChar = false;
        }
        return lookingAtChar;
    }
    
    private void createReader(final boolean b) throws Exception {
        this.fReader = null;
        if (this.fSource.getCharacterStream() != null) {
            this.fReader = this.fReaderFactory.createCharReader(this, this.fErrorReporter, this.fSendCharDataAsCharArray, this.fSource.getCharacterStream(), this.fStringPool);
            return;
        }
        if (this.fSource.getEncoding() != null && this.fSource.getByteStream() != null) {
            this.fReader = this.fReaderFactory.createCharReader(this, this.fErrorReporter, this.fSendCharDataAsCharArray, new InputStreamReader(this.fSource.getByteStream(), this.fSource.getEncoding()), this.fStringPool);
            return;
        }
        InputStream inputStream = this.fSource.getByteStream();
        if (inputStream == null) {
            try {
                inputStream = new URL(this.fSystemId).openStream();
            }
            catch (MalformedURLException ex) {
                final String fSystemId = this.fSystemId;
                this.sendEndEntityNotifications();
                this.popReader();
                this.popEntity();
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 5, 0, new Object[] { fSystemId }, 2);
                return;
            }
            catch (FileNotFoundException ex2) {
                final String fSystemId2 = this.fSystemId;
                this.sendEndEntityNotifications();
                this.popReader();
                this.popEntity();
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 5, 0, new Object[] { fSystemId2 }, 2);
                return;
            }
        }
        final ChunkyByteArray chunkyByteArray = new ChunkyByteArray(inputStream);
        EntityReader fReader = this.callRecognizers(this.fSource, chunkyByteArray, b);
        if (fReader == null) {
            fReader = this.fReaderFactory.createUTF8Reader(this, this.fErrorReporter, this.fSendCharDataAsCharArray, chunkyByteArray, this.fStringPool);
        }
        this.fReader = fReader;
    }
    
    private void pushNullReader() {
        ReaderState fReaderStateFreeList = this.fReaderStateFreeList;
        if (fReaderStateFreeList == null) {
            fReaderStateFreeList = new ReaderState();
        }
        else {
            this.fReaderStateFreeList = fReaderStateFreeList.nextReaderState;
        }
        if (this.fNullReader == null) {
            this.fNullReader = new NullReader();
        }
        fReaderStateFreeList.reader = this.fNullReader;
        fReaderStateFreeList.source = null;
        fReaderStateFreeList.entityName = -1;
        fReaderStateFreeList.entityType = -1;
        fReaderStateFreeList.entityContext = -1;
        fReaderStateFreeList.publicId = "Null Entity";
        fReaderStateFreeList.systemId = this.fSystemId;
        fReaderStateFreeList.readerId = this.fNextReaderId++;
        fReaderStateFreeList.depth = -1;
        fReaderStateFreeList.nextReaderState = null;
        this.fReaderStack.push(fReaderStateFreeList);
    }
    
    private void pushReader() {
        ReaderState fReaderStateFreeList = this.fReaderStateFreeList;
        if (fReaderStateFreeList == null) {
            fReaderStateFreeList = new ReaderState();
        }
        else {
            this.fReaderStateFreeList = fReaderStateFreeList.nextReaderState;
        }
        fReaderStateFreeList.reader = this.fReader;
        fReaderStateFreeList.source = this.fSource;
        fReaderStateFreeList.entityName = this.fEntityName;
        fReaderStateFreeList.entityType = this.fEntityType;
        fReaderStateFreeList.entityContext = this.fEntityContext;
        fReaderStateFreeList.publicId = this.fPublicId;
        fReaderStateFreeList.systemId = this.fSystemId;
        fReaderStateFreeList.readerId = this.fReaderId;
        fReaderStateFreeList.depth = this.fReaderDepth;
        fReaderStateFreeList.nextReaderState = null;
        this.fReaderStack.push(fReaderStateFreeList);
    }
    
    private void popReader() {
        if (this.fReaderStack.empty()) {
            throw new RuntimeException("cannot happen 19");
        }
        final ReaderState fReaderStateFreeList = this.fReaderStack.pop();
        this.fReader = fReaderStateFreeList.reader;
        this.fSource = fReaderStateFreeList.source;
        this.fEntityName = fReaderStateFreeList.entityName;
        this.fEntityType = fReaderStateFreeList.entityType;
        this.fEntityContext = fReaderStateFreeList.entityContext;
        this.fPublicId = fReaderStateFreeList.publicId;
        this.fSystemId = fReaderStateFreeList.systemId;
        this.fReaderId = fReaderStateFreeList.readerId;
        this.fReaderDepth = fReaderStateFreeList.depth;
        fReaderStateFreeList.nextReaderState = this.fReaderStateFreeList;
        this.fReaderStateFreeList = fReaderStateFreeList;
    }
    
    public boolean startEntityDecl(final boolean b, final int n) throws Exception {
        if (!this.pushEntity(b, n)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", b ? 72 : 71, 73, new Object[] { this.fStringPool.toString(n), this.entityReferencePath(b, n) }, 2);
            return false;
        }
        return true;
    }
    
    public void endEntityDecl() throws Exception {
        this.popEntity();
    }
    
    private boolean pushEntity(final boolean b, final int n) throws Exception {
        if (n >= 0) {
            for (int i = 0; i < this.fEntityStackDepth; ++i) {
                if (this.fEntityNameStack[i] == n && this.fEntityTypeStack[i] == (b ? 1 : 0)) {
                    return false;
                }
            }
        }
        if (this.fEntityTypeStack == null) {
            this.fEntityTypeStack = new byte[8];
            this.fEntityNameStack = new int[8];
        }
        else if (this.fEntityStackDepth == this.fEntityTypeStack.length) {
            final byte[] fEntityTypeStack = new byte[this.fEntityStackDepth * 2];
            System.arraycopy(this.fEntityTypeStack, 0, fEntityTypeStack, 0, this.fEntityStackDepth);
            this.fEntityTypeStack = fEntityTypeStack;
            final int[] fEntityNameStack = new int[this.fEntityStackDepth * 2];
            System.arraycopy(this.fEntityNameStack, 0, fEntityNameStack, 0, this.fEntityStackDepth);
            this.fEntityNameStack = fEntityNameStack;
        }
        this.fEntityTypeStack[this.fEntityStackDepth] = (byte)(b ? 1 : 0);
        this.fEntityNameStack[this.fEntityStackDepth] = n;
        ++this.fEntityStackDepth;
        return true;
    }
    
    private String entityReferencePath(final boolean b, final int n) {
        final StringBuffer sb = new StringBuffer();
        sb.append("(top-level)");
        for (int i = 0; i < this.fEntityStackDepth; ++i) {
            if (this.fEntityNameStack[i] >= 0) {
                sb.append('-');
                sb.append((char)((this.fEntityTypeStack[i] == 1) ? 37 : 38));
                sb.append(this.fStringPool.toString(this.fEntityNameStack[i]));
                sb.append(';');
            }
        }
        sb.append('-');
        sb.append(b ? '%' : '&');
        sb.append(this.fStringPool.toString(n));
        sb.append(';');
        return sb.toString();
    }
    
    private void popEntity() throws Exception {
        --this.fEntityStackDepth;
    }
    
    public int getReaderId() {
        return this.fReaderId;
    }
    
    public void setReaderDepth(final int fReaderDepth) {
        this.fReaderDepth = fReaderDepth;
    }
    
    public int getReaderDepth() {
        return this.fReaderDepth;
    }
    
    public String getPublicId() {
        return this.fPublicId;
    }
    
    public String getSystemId() {
        return this.fSystemId;
    }
    
    public int getLineNumber() {
        return this.fReader.getLineNumber();
    }
    
    public int getColumnNumber() {
        return this.fReader.getColumnNumber();
    }
    
    public EntityReader changeReaders() throws Exception {
        this.sendEndOfInputNotifications();
        this.sendEndEntityNotifications();
        this.popReader();
        this.sendReaderChangeNotifications();
        this.popEntity();
        return this.fReader;
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://xml.org/sax/features/external-general-entities", "http://xml.org/sax/features/external-parameter-entities", "http://xml.org/sax/features/namespaces", "http://apache.org/xml/features/validation/dynamic", "http://apache.org/xml/features/validation/default-attribute-values", "http://apache.org/xml/features/validation/validate-content-models", "http://apache.org/xml/features/validation/validate-datatypes", "http://apache.org/xml/features/validation/warn-on-duplicate-attdef", "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/continue-after-fatal-error" };
        RECOGNIZED_PROPERTIES = new String[] { "http://xml.org/sax/properties/namespace-sep", "http://xml.org/sax/properties/xml-string" };
        XMLParser.fgXMLMessages = new XMLMessages();
        XMLParser.fgImplementationMessages = new ImplementationMessages();
        XMLParser.fgSchemaMessages = new SchemaMessageProvider();
        XMLParser.fgDatatypeMessages = new DatatypeMessageProvider();
    }
    
    private class ReaderState
    {
        EntityReader reader;
        InputSource source;
        int entityName;
        int entityType;
        int entityContext;
        String publicId;
        String systemId;
        int readerId;
        int depth;
        ReaderState nextReaderState;
    }
    
    private final class NullReader implements EntityReader
    {
        public int currentOffset() {
            return -1;
        }
        
        public int getLineNumber() {
            return -1;
        }
        
        public int getColumnNumber() {
            return -1;
        }
        
        public void setInCDSect(final boolean b) {
        }
        
        public boolean getInCDSect() {
            return false;
        }
        
        public void append(final CharBuffer charBuffer, final int n, final int n2) {
        }
        
        public int addString(final int n, final int n2) {
            return -1;
        }
        
        public int addSymbol(final int n, final int n2) {
            return -1;
        }
        
        public boolean lookingAtChar(final char c, final boolean b) {
            return false;
        }
        
        public boolean lookingAtValidChar(final boolean b) {
            return false;
        }
        
        public boolean lookingAtSpace(final boolean b) {
            return false;
        }
        
        public void skipToChar(final char c) {
        }
        
        public void skipPastSpaces() {
        }
        
        public void skipPastName(final char c) {
        }
        
        public void skipPastNmtoken(final char c) {
        }
        
        public boolean skippedString(final char[] array) {
            return false;
        }
        
        public int scanInvalidChar() {
            return -1;
        }
        
        public int scanCharRef(final boolean b) {
            return -2;
        }
        
        public int scanStringLiteral() {
            return -1;
        }
        
        public int scanAttValue(final char c, final boolean b) {
            return -3;
        }
        
        public int scanEntityValue(final int n, final boolean b) {
            return -4;
        }
        
        public boolean scanExpectedName(final char c, final StringPool.CharArrayRange charArrayRange) {
            return false;
        }
        
        public int scanQName(final char c) {
            return -1;
        }
        
        public int scanName(final char c) {
            return -1;
        }
        
        public int scanContent(final int n) throws Exception {
            return 9;
        }
    }
}
