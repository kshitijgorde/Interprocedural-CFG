// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import java.util.Enumeration;
import java.util.Stack;
import org.apache.xerces.impl.xs.identity.KeyRef;
import org.apache.xerces.impl.xs.identity.UniqueOrKey;
import org.apache.xerces.impl.xs.identity.IDValue;
import org.apache.xerces.util.IntStack;
import java.util.Vector;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xni.parser.XMLInputSource;
import java.io.IOException;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.impl.dv.DatatypeException;
import org.apache.xerces.impl.xs.identity.Selector;
import org.apache.xerces.impl.xs.identity.ValueStore;
import org.apache.xerces.impl.xs.identity.XPathMatcher;
import org.apache.xerces.impl.xs.identity.Field;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.models.XSCMValidator;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import java.util.Hashtable;
import org.apache.xerces.util.XMLResourceIdentifierImpl;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.impl.validation.ValidationState;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.util.AugmentationsImpl;
import org.apache.xerces.impl.RevalidationHandler;
import org.apache.xerces.impl.xs.identity.FieldActivator;
import org.apache.xerces.xni.parser.XMLDocumentFilter;
import org.apache.xerces.xni.parser.XMLComponent;

public class XMLSchemaValidator implements XMLComponent, XMLDocumentFilter, FieldActivator, RevalidationHandler
{
    private static final boolean DEBUG = false;
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String SCHEMA_AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String[] RECOGNIZED_FEATURES;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final Object[] PROPERTY_DEFAULTS;
    protected static final int ID_CONSTRAINT_NUM = 1;
    protected ElementPSVImpl fCurrentPSVI;
    protected final AugmentationsImpl fAugmentations;
    protected XMLString fDefaultValue;
    protected boolean fDynamicValidation;
    protected boolean fDoValidation;
    protected boolean fFullChecking;
    protected boolean fNormalizeData;
    protected boolean fSchemaElementDefault;
    protected boolean fAugPSVI;
    protected boolean fEntityRef;
    protected boolean fInCDATA;
    protected SymbolTable fSymbolTable;
    protected final XSIErrorReporter fXSIErrorReporter;
    protected XMLEntityResolver fEntityResolver;
    protected ValidationManager fValidationManager;
    protected ValidationState fValidationState;
    protected XMLGrammarPool fGrammarPool;
    protected String fExternalSchemas;
    protected String fExternalNoNamespaceSchema;
    protected Object fJaxpSchemaSource;
    final XMLResourceIdentifierImpl fResourceIdentifier;
    protected final XSDDescription fXSDDescription;
    protected final Hashtable fLocationPairs;
    protected final XMLSchemaLoader.LocationArray fNoNamespaceLocationArray;
    protected String fBaseURI;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fDocumentSource;
    static final int INITIAL_STACK_SIZE = 8;
    static final int INC_STACK_SIZE = 8;
    private static final boolean DEBUG_NORMALIZATION = false;
    private final XMLString fEmptyXMLStr;
    private static final int BUFFER_SIZE = 20;
    private final XMLString fNormalizedStr;
    private boolean fFirstChunk;
    private boolean fTrailing;
    private short fWhiteSpace;
    private boolean fUnionType;
    final XSGrammarBucket fGrammarBucket;
    final SubstitutionGroupHandler fSubGroupHandler;
    final XMLSchemaLoader fSchemaLoader;
    final XSSimpleType fQNameDV;
    final CMBuilder fCMBuilder;
    String fValidationRoot;
    int fSkipValidationDepth;
    int fNFullValidationDepth;
    int fNNoneValidationDepth;
    int fElementDepth;
    boolean fSubElement;
    boolean[] fSubElementStack;
    XSElementDecl fCurrentElemDecl;
    XSElementDecl[] fElemDeclStack;
    boolean fNil;
    boolean[] fNilStack;
    XSNotationDecl fNotation;
    XSNotationDecl[] fNotationStack;
    XSTypeDecl fCurrentType;
    XSTypeDecl[] fTypeStack;
    XSCMValidator fCurrentCM;
    XSCMValidator[] fCMStack;
    int[] fCurrCMState;
    int[][] fCMStateStack;
    boolean fStrictAssess;
    boolean[] fStrictAssessStack;
    final StringBuffer fBuffer;
    boolean fAppendBuffer;
    boolean fSawText;
    boolean[] fSawTextStack;
    boolean fSawCharacters;
    boolean[] fStringContent;
    boolean fSawChildren;
    boolean[] fSawChildrenStack;
    final QName fTempQName;
    ValidatedInfo fValidatedInfo;
    private ValidationState fState4XsiType;
    private ValidationState fState4ApplyDefault;
    protected XPathMatcherStack fMatcherStack;
    protected ValueStoreCache fValueStoreCache;
    
    public String[] getRecognizedFeatures() {
        return XMLSchemaValidator.RECOGNIZED_FEATURES.clone();
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
    }
    
    public String[] getRecognizedProperties() {
        return XMLSchemaValidator.RECOGNIZED_PROPERTIES.clone();
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
    }
    
    public Boolean getFeatureDefault(final String featureId) {
        for (int i = 0; i < XMLSchemaValidator.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLSchemaValidator.RECOGNIZED_FEATURES[i].equals(featureId)) {
                return XMLSchemaValidator.FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public Object getPropertyDefault(final String propertyId) {
        for (int i = 0; i < XMLSchemaValidator.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLSchemaValidator.RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return XMLSchemaValidator.PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public void setDocumentHandler(final XMLDocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
    }
    
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }
    
    public void setDocumentSource(final XMLDocumentSource source) {
        this.fDocumentSource = source;
    }
    
    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }
    
    public void startDocument(final XMLLocator locator, final String encoding, final NamespaceContext namespaceContext, final Augmentations augs) throws XNIException {
        this.fValidationState.setNamespaceSupport(namespaceContext);
        this.fState4XsiType.setNamespaceSupport(namespaceContext);
        this.fState4ApplyDefault.setNamespaceSupport(namespaceContext);
        this.handleStartDocument(locator, encoding);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startDocument(locator, encoding, namespaceContext, augs);
        }
    }
    
    public void xmlDecl(final String version, final String encoding, final String standalone, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.xmlDecl(version, encoding, standalone, augs);
        }
    }
    
    public void doctypeDecl(final String rootElement, final String publicId, final String systemId, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.doctypeDecl(rootElement, publicId, systemId, augs);
        }
    }
    
    public void startPrefixMapping(final String prefix, final String uri, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startPrefixMapping(prefix, uri, augs);
        }
    }
    
    public void startElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        final Augmentations modifiedAugs = this.handleStartElement(element, attributes, augs);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startElement(element, attributes, modifiedAugs);
        }
    }
    
    public void emptyElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        Augmentations modifiedAugs = this.handleStartElement(element, attributes, augs);
        this.fDefaultValue = null;
        if (this.fElementDepth != -2) {
            modifiedAugs = this.handleEndElement(element, modifiedAugs);
        }
        if (this.fDocumentHandler != null) {
            if (!this.fSchemaElementDefault || this.fDefaultValue == null) {
                this.fDocumentHandler.emptyElement(element, attributes, modifiedAugs);
            }
            else {
                this.fDocumentHandler.startElement(element, attributes, modifiedAugs);
                this.fDocumentHandler.characters(this.fDefaultValue, null);
                this.fDocumentHandler.endElement(element, modifiedAugs);
            }
        }
    }
    
    public void characters(XMLString text, final Augmentations augs) throws XNIException {
        text = this.handleCharacters(text);
        if (this.fDocumentHandler != null) {
            if (this.fNormalizeData && this.fUnionType) {
                if (augs != null) {
                    this.fDocumentHandler.characters(this.fEmptyXMLStr, augs);
                }
            }
            else {
                this.fDocumentHandler.characters(text, augs);
            }
        }
    }
    
    public void ignorableWhitespace(final XMLString text, final Augmentations augs) throws XNIException {
        this.handleIgnorableWhitespace(text);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.ignorableWhitespace(text, augs);
        }
    }
    
    public void endElement(final QName element, final Augmentations augs) throws XNIException {
        this.fDefaultValue = null;
        final Augmentations modifiedAugs = this.handleEndElement(element, augs);
        if (this.fDocumentHandler != null) {
            if (!this.fSchemaElementDefault || this.fDefaultValue == null) {
                this.fDocumentHandler.endElement(element, modifiedAugs);
            }
            else {
                this.fDocumentHandler.characters(this.fDefaultValue, null);
                this.fDocumentHandler.endElement(element, modifiedAugs);
            }
        }
    }
    
    public void endPrefixMapping(final String prefix, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endPrefixMapping(prefix, augs);
        }
    }
    
    public void startCDATA(final Augmentations augs) throws XNIException {
        this.fInCDATA = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startCDATA(augs);
        }
    }
    
    public void endCDATA(final Augmentations augs) throws XNIException {
        this.fInCDATA = false;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endCDATA(augs);
        }
    }
    
    public void endDocument(final Augmentations augs) throws XNIException {
        this.handleEndDocument();
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endDocument(augs);
        }
    }
    
    public void setBaseURI(final String base) {
        this.fBaseURI = base;
    }
    
    public boolean characterData(final String data, final Augmentations augs) {
        this.fSawText = (this.fSawText || data.length() > 0);
        if (this.fNormalizeData && this.fWhiteSpace != -1 && this.fWhiteSpace != 0) {
            this.normalizeWhitespace(data, this.fWhiteSpace == 2);
            this.fBuffer.append(this.fNormalizedStr.ch, this.fNormalizedStr.offset, this.fNormalizedStr.length);
        }
        else if (this.fAppendBuffer) {
            this.fBuffer.append(data);
        }
        boolean allWhiteSpace = true;
        if (this.fCurrentType != null && this.fCurrentType.getTypeCategory() == 13) {
            final XSComplexTypeDecl ctype = (XSComplexTypeDecl)this.fCurrentType;
            if (ctype.fContentType == 2) {
                for (int i = 0; i < data.length(); ++i) {
                    if (!XMLChar.isSpace(data.charAt(i))) {
                        allWhiteSpace = false;
                        this.fSawCharacters = true;
                        break;
                    }
                }
            }
        }
        this.fFirstChunk = false;
        return allWhiteSpace;
    }
    
    public void elementDefault(final String data) {
    }
    
    public void startGeneralEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
        this.fEntityRef = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startGeneralEntity(name, identifier, encoding, augs);
        }
    }
    
    public void textDecl(final String version, final String encoding, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.textDecl(version, encoding, augs);
        }
    }
    
    public void comment(final XMLString text, final Augmentations augs) throws XNIException {
        this.fSawChildren = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.comment(text, augs);
        }
    }
    
    public void processingInstruction(final String target, final XMLString data, final Augmentations augs) throws XNIException {
        this.fSawChildren = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.processingInstruction(target, data, augs);
        }
    }
    
    public void endGeneralEntity(final String name, final Augmentations augs) throws XNIException {
        this.fEntityRef = false;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endGeneralEntity(name, augs);
        }
    }
    
    public XMLSchemaValidator() {
        this.fCurrentPSVI = new ElementPSVImpl();
        this.fAugmentations = new AugmentationsImpl();
        this.fDynamicValidation = false;
        this.fDoValidation = false;
        this.fFullChecking = false;
        this.fNormalizeData = true;
        this.fSchemaElementDefault = true;
        this.fAugPSVI = true;
        this.fEntityRef = false;
        this.fInCDATA = false;
        this.fXSIErrorReporter = new XSIErrorReporter();
        this.fValidationManager = null;
        this.fValidationState = new ValidationState();
        this.fExternalSchemas = null;
        this.fExternalNoNamespaceSchema = null;
        this.fJaxpSchemaSource = null;
        this.fResourceIdentifier = new XMLResourceIdentifierImpl();
        this.fXSDDescription = new XSDDescription();
        this.fLocationPairs = new Hashtable();
        this.fNoNamespaceLocationArray = new XMLSchemaLoader.LocationArray();
        this.fBaseURI = null;
        this.fEmptyXMLStr = new XMLString(null, 0, -1);
        this.fNormalizedStr = new XMLString();
        this.fFirstChunk = true;
        this.fTrailing = false;
        this.fWhiteSpace = -1;
        this.fUnionType = false;
        this.fQNameDV = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl("QName");
        this.fCMBuilder = new CMBuilder();
        this.fSubElementStack = new boolean[8];
        this.fElemDeclStack = new XSElementDecl[8];
        this.fNilStack = new boolean[8];
        this.fNotationStack = new XSNotationDecl[8];
        this.fTypeStack = new XSTypeDecl[8];
        this.fCMStack = new XSCMValidator[8];
        this.fCMStateStack = new int[8][];
        this.fStrictAssess = true;
        this.fStrictAssessStack = new boolean[8];
        this.fBuffer = new StringBuffer();
        this.fAppendBuffer = true;
        this.fSawText = false;
        this.fSawTextStack = new boolean[8];
        this.fSawCharacters = false;
        this.fStringContent = new boolean[8];
        this.fSawChildren = false;
        this.fSawChildrenStack = new boolean[8];
        this.fTempQName = new QName();
        this.fValidatedInfo = new ValidatedInfo();
        this.fState4XsiType = new ValidationState();
        this.fState4ApplyDefault = new ValidationState();
        this.fMatcherStack = new XPathMatcherStack();
        this.fValueStoreCache = new ValueStoreCache();
        this.fGrammarBucket = new XSGrammarBucket();
        this.fSubGroupHandler = new SubstitutionGroupHandler(this.fGrammarBucket);
        this.fSchemaLoader = new XMLSchemaLoader(this.fXSIErrorReporter.fErrorReporter, this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder);
        this.fState4XsiType.setExtraChecking(false);
        this.fState4ApplyDefault.setFacetChecking(false);
    }
    
    public void reset(final XMLComponentManager componentManager) throws XMLConfigurationException {
        this.fXSIErrorReporter.reset((XMLErrorReporter)componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter"));
        this.fSchemaLoader.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fXSIErrorReporter.fErrorReporter);
        final SymbolTable symbolTable = (SymbolTable)componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        if (symbolTable != this.fSymbolTable) {
            this.fSchemaLoader.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
            this.fSymbolTable = symbolTable;
        }
        try {
            this.fDynamicValidation = componentManager.getFeature("http://apache.org/xml/features/validation/dynamic");
        }
        catch (XMLConfigurationException e) {
            this.fDynamicValidation = false;
        }
        if (this.fDynamicValidation) {
            this.fDoValidation = true;
        }
        else {
            try {
                this.fDoValidation = componentManager.getFeature("http://xml.org/sax/features/validation");
            }
            catch (XMLConfigurationException e) {
                this.fDoValidation = false;
            }
        }
        if (this.fDoValidation) {
            try {
                this.fDoValidation = componentManager.getFeature("http://apache.org/xml/features/validation/schema");
            }
            catch (XMLConfigurationException ex) {}
        }
        try {
            this.fFullChecking = componentManager.getFeature("http://apache.org/xml/features/validation/schema-full-checking");
        }
        catch (XMLConfigurationException e) {
            this.fFullChecking = false;
        }
        this.fSchemaLoader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", false);
        try {
            this.fNormalizeData = componentManager.getFeature("http://apache.org/xml/features/validation/schema/normalized-value");
        }
        catch (XMLConfigurationException e) {
            this.fNormalizeData = false;
        }
        try {
            this.fSchemaElementDefault = componentManager.getFeature("http://apache.org/xml/features/validation/schema/element-default");
        }
        catch (XMLConfigurationException e) {
            this.fSchemaElementDefault = false;
        }
        try {
            this.fAugPSVI = componentManager.getFeature("http://apache.org/xml/features/validation/schema/augment-psvi");
        }
        catch (XMLConfigurationException e) {
            this.fAugPSVI = true;
        }
        this.fEntityResolver = (XMLEntityResolver)componentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
        this.fSchemaLoader.setEntityResolver(this.fEntityResolver);
        (this.fValidationManager = (ValidationManager)componentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager")).addValidationState(this.fValidationState);
        this.fValidationState.setSymbolTable(this.fSymbolTable);
        this.fLocationPairs.clear();
        this.fNoNamespaceLocationArray.resize(0, 2);
        try {
            this.fExternalSchemas = (String)componentManager.getProperty("http://apache.org/xml/properties/schema/external-schemaLocation");
            this.fExternalNoNamespaceSchema = (String)componentManager.getProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation");
        }
        catch (XMLConfigurationException e) {
            this.fExternalSchemas = null;
            this.fExternalNoNamespaceSchema = null;
        }
        this.fSchemaLoader.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", this.fExternalSchemas);
        this.fSchemaLoader.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", this.fExternalNoNamespaceSchema);
        this.storeLocations(this.fExternalSchemas, this.fExternalNoNamespaceSchema);
        try {
            this.fJaxpSchemaSource = componentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource");
        }
        catch (XMLConfigurationException e) {
            this.fJaxpSchemaSource = null;
        }
        this.fSchemaLoader.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", this.fJaxpSchemaSource);
        this.fResourceIdentifier.clear();
        try {
            this.fGrammarPool = (XMLGrammarPool)componentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        }
        catch (XMLConfigurationException e) {
            this.fGrammarPool = null;
        }
        this.fSchemaLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
        try {
            final boolean allowJavaEncodings = componentManager.getFeature("http://apache.org/xml/features/allow-java-encodings");
            this.fSchemaLoader.setFeature("http://apache.org/xml/features/allow-java-encodings", allowJavaEncodings);
        }
        catch (XMLConfigurationException ex2) {}
        try {
            final boolean fatalError = componentManager.getFeature("http://apache.org/xml/features/continue-after-fatal-error");
            this.fSchemaLoader.setFeature("http://apache.org/xml/features/continue-after-fatal-error", fatalError);
        }
        catch (XMLConfigurationException ex3) {}
        this.fSchemaLoader.reset();
        this.fCurrentElemDecl = null;
        this.fCurrentCM = null;
        this.fCurrCMState = null;
        this.fSkipValidationDepth = -1;
        this.fNFullValidationDepth = -1;
        this.fNNoneValidationDepth = -1;
        this.fElementDepth = -1;
        this.fSubElement = false;
        this.fEntityRef = false;
        this.fInCDATA = false;
        this.fMatcherStack.clear();
        this.fBaseURI = null;
        this.fState4XsiType.setSymbolTable(symbolTable);
        this.fState4ApplyDefault.setSymbolTable(symbolTable);
    }
    
    public void startValueScopeFor(final IdentityConstraint identityConstraint, final int initialDepth) throws XNIException {
        final ValueStoreBase valueStore = this.fValueStoreCache.getValueStoreFor(identityConstraint, initialDepth);
        valueStore.startValueScope();
    }
    
    public XPathMatcher activateField(final Field field, final int initialDepth) {
        final ValueStore valueStore = this.fValueStoreCache.getValueStoreFor(field.getIdentityConstraint(), initialDepth);
        field.setMayMatch(true);
        final XPathMatcher matcher = field.createMatcher(valueStore);
        this.fMatcherStack.addMatcher(matcher);
        matcher.startDocumentFragment(this.fSymbolTable);
        return matcher;
    }
    
    public void endValueScopeFor(final IdentityConstraint identityConstraint, final int initialDepth) throws XNIException {
        final ValueStoreBase valueStore = this.fValueStoreCache.getValueStoreFor(identityConstraint, initialDepth);
        valueStore.endValueScope();
    }
    
    private void activateSelectorFor(final IdentityConstraint ic) throws XNIException {
        final Selector selector = ic.getSelector();
        if (selector == null) {
            return;
        }
        final XPathMatcher matcher = selector.createMatcher(this, this.fElementDepth);
        this.fMatcherStack.addMatcher(matcher);
        matcher.startDocumentFragment(this.fSymbolTable);
    }
    
    void ensureStackCapacity() {
        if (this.fElementDepth == this.fElemDeclStack.length) {
            final int newSize = this.fElementDepth + 8;
            boolean[] newArrayB = new boolean[newSize];
            System.arraycopy(this.fSubElementStack, 0, newArrayB, 0, this.fElementDepth);
            this.fSubElementStack = newArrayB;
            final XSElementDecl[] newArrayE = new XSElementDecl[newSize];
            System.arraycopy(this.fElemDeclStack, 0, newArrayE, 0, this.fElementDepth);
            this.fElemDeclStack = newArrayE;
            newArrayB = new boolean[newSize];
            System.arraycopy(this.fNilStack, 0, newArrayB, 0, this.fElementDepth);
            this.fNilStack = newArrayB;
            final XSNotationDecl[] newArrayN = new XSNotationDecl[newSize];
            System.arraycopy(this.fNotationStack, 0, newArrayN, 0, this.fElementDepth);
            this.fNotationStack = newArrayN;
            final XSTypeDecl[] newArrayT = new XSTypeDecl[newSize];
            System.arraycopy(this.fTypeStack, 0, newArrayT, 0, this.fElementDepth);
            this.fTypeStack = newArrayT;
            final XSCMValidator[] newArrayC = new XSCMValidator[newSize];
            System.arraycopy(this.fCMStack, 0, newArrayC, 0, this.fElementDepth);
            this.fCMStack = newArrayC;
            newArrayB = new boolean[newSize];
            System.arraycopy(this.fSawTextStack, 0, newArrayB, 0, this.fElementDepth);
            this.fSawTextStack = newArrayB;
            newArrayB = new boolean[newSize];
            System.arraycopy(this.fStringContent, 0, newArrayB, 0, this.fElementDepth);
            this.fStringContent = newArrayB;
            newArrayB = new boolean[newSize];
            System.arraycopy(this.fSawChildrenStack, 0, newArrayB, 0, this.fElementDepth);
            this.fSawChildrenStack = newArrayB;
            newArrayB = new boolean[newSize];
            System.arraycopy(this.fStrictAssessStack, 0, newArrayB, 0, this.fElementDepth);
            this.fStrictAssessStack = newArrayB;
            final int[][] newArrayIA = new int[newSize][];
            System.arraycopy(this.fCMStateStack, 0, newArrayIA, 0, this.fElementDepth);
            this.fCMStateStack = newArrayIA;
        }
    }
    
    void handleStartDocument(final XMLLocator locator, final String encoding) {
        this.fValueStoreCache.startDocument();
    }
    
    void handleEndDocument() {
        this.fValueStoreCache.endDocument();
    }
    
    XMLString handleCharacters(XMLString text) {
        if (this.fSkipValidationDepth >= 0) {
            return text;
        }
        this.fSawText = (this.fSawText || text.length > 0);
        if (this.fNormalizeData && this.fWhiteSpace != -1 && this.fWhiteSpace != 0) {
            this.normalizeWhitespace(text, this.fWhiteSpace == 2);
            text = this.fNormalizedStr;
        }
        if (this.fAppendBuffer) {
            this.fBuffer.append(text.ch, text.offset, text.length);
        }
        if (this.fCurrentType != null && this.fCurrentType.getTypeCategory() == 13) {
            final XSComplexTypeDecl ctype = (XSComplexTypeDecl)this.fCurrentType;
            if (ctype.fContentType == 2) {
                for (int i = text.offset; i < text.offset + text.length; ++i) {
                    if (!XMLChar.isSpace(text.ch[i])) {
                        this.fSawCharacters = true;
                        break;
                    }
                }
            }
        }
        this.fFirstChunk = false;
        return text;
    }
    
    private void normalizeWhitespace(final XMLString value, final boolean collapse) {
        boolean skipSpace = collapse;
        boolean sawNonWS = false;
        int leading = 0;
        int trailing = 0;
        final int size = value.offset + value.length;
        if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < value.length + 1) {
            this.fNormalizedStr.ch = new char[value.length + 1];
        }
        this.fNormalizedStr.offset = 1;
        this.fNormalizedStr.length = 1;
        for (int i = value.offset; i < size; ++i) {
            final char c = value.ch[i];
            if (XMLChar.isSpace(c)) {
                if (!skipSpace) {
                    this.fNormalizedStr.ch[this.fNormalizedStr.length++] = ' ';
                    skipSpace = collapse;
                }
                if (!sawNonWS) {
                    leading = 1;
                }
            }
            else {
                this.fNormalizedStr.ch[this.fNormalizedStr.length++] = c;
                skipSpace = false;
                sawNonWS = true;
            }
        }
        if (skipSpace) {
            if (this.fNormalizedStr.length > 1) {
                final XMLString fNormalizedStr = this.fNormalizedStr;
                --fNormalizedStr.length;
                trailing = 2;
            }
            else if (leading != 0 && !sawNonWS) {
                trailing = 2;
            }
        }
        final int spaces = collapse ? (leading + trailing) : 0;
        if (this.fNormalizedStr.length > 1 && !this.fFirstChunk && this.fWhiteSpace == 2) {
            if (this.fTrailing) {
                this.fNormalizedStr.offset = 0;
                this.fNormalizedStr.ch[0] = ' ';
            }
            else if (spaces == 1 || spaces == 3) {
                this.fNormalizedStr.offset = 0;
                this.fNormalizedStr.ch[0] = ' ';
            }
        }
        final XMLString fNormalizedStr2 = this.fNormalizedStr;
        fNormalizedStr2.length -= this.fNormalizedStr.offset;
        this.fTrailing = (spaces > 1);
    }
    
    private void normalizeWhitespace(final String value, final boolean collapse) {
        boolean skipSpace = collapse;
        final int size = value.length();
        if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < size) {
            this.fNormalizedStr.ch = new char[size];
        }
        this.fNormalizedStr.offset = 0;
        this.fNormalizedStr.length = 0;
        for (int i = 0; i < size; ++i) {
            final char c = value.charAt(i);
            if (XMLChar.isSpace(c)) {
                if (!skipSpace) {
                    this.fNormalizedStr.ch[this.fNormalizedStr.length++] = ' ';
                    skipSpace = collapse;
                }
            }
            else {
                this.fNormalizedStr.ch[this.fNormalizedStr.length++] = c;
                skipSpace = false;
            }
        }
        if (skipSpace && this.fNormalizedStr.length != 0) {
            final XMLString fNormalizedStr = this.fNormalizedStr;
            --fNormalizedStr.length;
        }
    }
    
    void handleIgnorableWhitespace(final XMLString text) {
        if (this.fSkipValidationDepth >= 0) {
            return;
        }
    }
    
    Augmentations handleStartElement(final QName element, final XMLAttributes attributes, Augmentations augs) {
        if (this.fElementDepth == -1 && this.fValidationManager.isGrammarFound()) {
            this.fDynamicValidation = true;
        }
        final String sLocation = attributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_SCHEMALOCATION);
        final String nsLocation = attributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION);
        this.storeLocations(sLocation, nsLocation);
        if (this.fSkipValidationDepth >= 0) {
            ++this.fElementDepth;
            if (this.fAugPSVI) {
                augs = this.getEmptyAugs(augs);
            }
            return augs;
        }
        final SchemaGrammar sGrammar = this.findSchemaGrammar((short)5, element.uri, null, element, attributes);
        Object decl = null;
        if (this.fCurrentCM != null) {
            decl = this.fCurrentCM.oneTransition(element, this.fCurrCMState, this.fSubGroupHandler);
            if (this.fCurrCMState[0] == -1) {
                final XSComplexTypeDecl ctype = (XSComplexTypeDecl)this.fCurrentType;
                if (ctype.fParticle != null) {
                    this.reportSchemaError("cvc-complex-type.2.4.a", new Object[] { element.rawname, ctype.fParticle.toString() });
                }
                else {
                    this.reportSchemaError("cvc-complex-type.2.4.a", new Object[] { element.rawname, "mixed with no element content" });
                }
            }
        }
        this.fXSIErrorReporter.pushContext();
        if (this.fElementDepth != -1) {
            this.ensureStackCapacity();
            this.fSubElementStack[this.fElementDepth] = true;
            this.fSubElement = false;
            this.fElemDeclStack[this.fElementDepth] = this.fCurrentElemDecl;
            this.fNilStack[this.fElementDepth] = this.fNil;
            this.fNotationStack[this.fElementDepth] = this.fNotation;
            this.fTypeStack[this.fElementDepth] = this.fCurrentType;
            this.fStrictAssessStack[this.fElementDepth] = this.fStrictAssess;
            this.fCMStack[this.fElementDepth] = this.fCurrentCM;
            this.fCMStateStack[this.fElementDepth] = this.fCurrCMState;
            this.fSawTextStack[this.fElementDepth] = this.fSawText;
            this.fStringContent[this.fElementDepth] = this.fSawCharacters;
            this.fSawChildrenStack[this.fElementDepth] = this.fSawChildren;
        }
        ++this.fElementDepth;
        this.fCurrentElemDecl = null;
        XSWildcardDecl wildcard = null;
        this.fCurrentType = null;
        this.fStrictAssess = true;
        this.fNil = false;
        this.fNotation = null;
        this.fBuffer.setLength(0);
        this.fSawText = false;
        this.fSawCharacters = false;
        this.fSawChildren = false;
        if (decl != null) {
            if (decl instanceof XSElementDecl) {
                this.fCurrentElemDecl = (XSElementDecl)decl;
            }
            else {
                wildcard = (XSWildcardDecl)decl;
            }
        }
        if (wildcard != null && wildcard.fProcessContents == 2) {
            this.fSkipValidationDepth = this.fElementDepth;
            if (this.fAugPSVI) {
                augs = this.getEmptyAugs(augs);
            }
            return augs;
        }
        if (this.fCurrentElemDecl == null && sGrammar != null) {
            this.fCurrentElemDecl = sGrammar.getGlobalElementDecl(element.localpart);
        }
        if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getIsAbstract()) {
            this.reportSchemaError("cvc-elt.2", new Object[] { element.rawname });
        }
        if (this.fCurrentElemDecl != null) {
            this.fCurrentType = this.fCurrentElemDecl.fType;
        }
        final String xsiType = attributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_TYPE);
        if (xsiType != null) {
            this.fCurrentType = this.getAndCheckXsiType(element, xsiType, attributes);
        }
        if (this.fCurrentType == null) {
            if (this.fElementDepth == 0) {
                if (this.fDynamicValidation) {
                    if (this.fDocumentSource != null) {
                        this.fDocumentSource.setDocumentHandler(this.fDocumentHandler);
                        if (this.fDocumentHandler != null) {
                            this.fDocumentHandler.setDocumentSource(this.fDocumentSource);
                        }
                        this.fElementDepth = -2;
                        return augs;
                    }
                    this.fSkipValidationDepth = this.fElementDepth;
                    if (this.fAugPSVI) {
                        augs = this.getEmptyAugs(augs);
                    }
                    return augs;
                }
                else {
                    this.fXSIErrorReporter.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "cvc-elt.1", new Object[] { element.rawname }, (short)1);
                }
            }
            else if (wildcard != null && wildcard.fProcessContents == 1) {
                this.reportSchemaError("cvc-complex-type.2.4.c", new Object[] { element.rawname });
            }
            this.fCurrentType = SchemaGrammar.fAnyType;
            this.fStrictAssess = false;
            this.fNFullValidationDepth = this.fElementDepth;
            this.fAppendBuffer = false;
        }
        else {
            this.fNNoneValidationDepth = this.fElementDepth;
            if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == 2) {
                this.fAppendBuffer = true;
            }
            else if (this.fCurrentType.getTypeCategory() == 14) {
                this.fAppendBuffer = true;
            }
            else {
                final XSComplexTypeDecl ctype2 = (XSComplexTypeDecl)this.fCurrentType;
                this.fAppendBuffer = (ctype2.fContentType == 1);
            }
        }
        if (this.fElementDepth == 0) {
            this.fValidationRoot = element.rawname;
        }
        if (this.fNormalizeData) {
            this.fFirstChunk = true;
            this.fTrailing = false;
            this.fUnionType = false;
            this.fWhiteSpace = -1;
        }
        if (this.fCurrentType.getTypeCategory() == 13) {
            final XSComplexTypeDecl ctype2 = (XSComplexTypeDecl)this.fCurrentType;
            if (ctype2.getIsAbstract()) {
                this.reportSchemaError("cvc-type.2", new Object[] { "Element " + element.rawname + " is declared with a type that is abstract.  Use xsi:type to specify a non-abstract type" });
            }
            if (this.fNormalizeData && ctype2.fContentType == 1) {
                if (ctype2.fXSSimpleType.getVariety() == 3) {
                    this.fUnionType = true;
                }
                else {
                    try {
                        this.fWhiteSpace = ctype2.fXSSimpleType.getWhitespace();
                    }
                    catch (DatatypeException ex) {}
                }
            }
        }
        else if (this.fNormalizeData) {
            final XSSimpleType dv = (XSSimpleType)this.fCurrentType;
            if (dv.getVariety() == 3) {
                this.fUnionType = true;
            }
            else {
                try {
                    this.fWhiteSpace = dv.getWhitespace();
                }
                catch (DatatypeException ex2) {}
            }
        }
        this.fCurrentCM = null;
        if (this.fCurrentType.getTypeCategory() == 13) {
            this.fCurrentCM = ((XSComplexTypeDecl)this.fCurrentType).getContentModel(this.fCMBuilder);
        }
        this.fCurrCMState = null;
        if (this.fCurrentCM != null) {
            this.fCurrCMState = this.fCurrentCM.startContentModel();
        }
        final String xsiNil = attributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_NIL);
        if (xsiNil != null && this.fCurrentElemDecl != null) {
            this.fNil = this.getXsiNil(element, xsiNil);
        }
        XSAttributeGroupDecl attrGrp = null;
        if (this.fCurrentType.getTypeCategory() == 13) {
            final XSComplexTypeDecl ctype3 = (XSComplexTypeDecl)this.fCurrentType;
            attrGrp = ctype3.getAttrGrp();
        }
        this.processAttributes(element, attributes, attrGrp);
        if (attrGrp != null) {
            this.addDefaultAttributes(element, attributes, attrGrp);
        }
        this.fValueStoreCache.startElement();
        this.fMatcherStack.pushContext();
        if (this.fCurrentElemDecl != null) {
            this.fValueStoreCache.initValueStoresFor(this.fCurrentElemDecl);
            int icCount;
            int uniqueOrKey;
            for (icCount = this.fCurrentElemDecl.fIDCPos, uniqueOrKey = 0; uniqueOrKey < icCount && this.fCurrentElemDecl.fIDConstraints[uniqueOrKey].getCategory() != 2; ++uniqueOrKey) {
                this.activateSelectorFor(this.fCurrentElemDecl.fIDConstraints[uniqueOrKey]);
            }
            for (int keyref = uniqueOrKey; keyref < icCount; ++keyref) {
                this.activateSelectorFor(this.fCurrentElemDecl.fIDConstraints[keyref]);
            }
        }
        for (int count = this.fMatcherStack.getMatcherCount(), i = 0; i < count; ++i) {
            final XPathMatcher matcher = this.fMatcherStack.getMatcherAt(i);
            matcher.startElement(element, attributes, this.fCurrentElemDecl);
        }
        if (this.fAugPSVI) {
            augs = this.getEmptyAugs(augs);
            this.fCurrentPSVI.fValidationContext = this.fValidationRoot;
            this.fCurrentPSVI.fDeclaration = this.fCurrentElemDecl;
            this.fCurrentPSVI.fTypeDecl = this.fCurrentType;
            this.fCurrentPSVI.fNotation = this.fNotation;
        }
        return augs;
    }
    
    Augmentations handleEndElement(final QName element, Augmentations augs) {
        if (this.fSkipValidationDepth >= 0) {
            if (this.fSkipValidationDepth == this.fElementDepth && this.fSkipValidationDepth > 0) {
                this.fNFullValidationDepth = this.fSkipValidationDepth - 1;
                this.fSkipValidationDepth = -1;
                --this.fElementDepth;
                this.fSubElement = this.fSubElementStack[this.fElementDepth];
                this.fCurrentElemDecl = this.fElemDeclStack[this.fElementDepth];
                this.fNil = this.fNilStack[this.fElementDepth];
                this.fNotation = this.fNotationStack[this.fElementDepth];
                this.fCurrentType = this.fTypeStack[this.fElementDepth];
                this.fCurrentCM = this.fCMStack[this.fElementDepth];
                this.fStrictAssess = this.fStrictAssessStack[this.fElementDepth];
                this.fCurrCMState = this.fCMStateStack[this.fElementDepth];
                this.fSawText = this.fSawTextStack[this.fElementDepth];
                this.fSawCharacters = this.fStringContent[this.fElementDepth];
                this.fSawChildren = this.fSawChildrenStack[this.fElementDepth];
            }
            else {
                --this.fElementDepth;
            }
            if (this.fElementDepth == -1 && this.fFullChecking) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter);
            }
            if (this.fAugPSVI) {
                augs = this.getEmptyAugs(augs);
            }
            return augs;
        }
        this.processElementContent(element);
        final int oldCount = this.fMatcherStack.getMatcherCount();
        for (int i = oldCount - 1; i >= 0; --i) {
            final XPathMatcher matcher = this.fMatcherStack.getMatcherAt(i);
            matcher.endElement(element, this.fCurrentElemDecl, (this.fDefaultValue == null) ? this.fValidatedInfo.normalizedValue : this.fCurrentElemDecl.fDefault.normalizedValue);
        }
        if (this.fMatcherStack.size() > 0) {
            this.fMatcherStack.popContext();
        }
        final int newCount = this.fMatcherStack.getMatcherCount();
        for (int j = oldCount - 1; j >= newCount; --j) {
            final XPathMatcher matcher2 = this.fMatcherStack.getMatcherAt(j);
            if (matcher2 instanceof Selector.Matcher) {
                final Selector.Matcher selMatcher = (Selector.Matcher)matcher2;
                final IdentityConstraint id;
                if ((id = selMatcher.getIdentityConstraint()) != null && id.getCategory() != 2) {
                    this.fValueStoreCache.transplant(id, selMatcher.getInitialDepth());
                }
            }
        }
        for (int k = oldCount - 1; k >= newCount; --k) {
            final XPathMatcher matcher3 = this.fMatcherStack.getMatcherAt(k);
            if (matcher3 instanceof Selector.Matcher) {
                final Selector.Matcher selMatcher2 = (Selector.Matcher)matcher3;
                final IdentityConstraint id2;
                if ((id2 = selMatcher2.getIdentityConstraint()) != null && id2.getCategory() == 2) {
                    final ValueStoreBase values = this.fValueStoreCache.getValueStoreFor(id2, selMatcher2.getInitialDepth());
                    if (values != null) {
                        values.endDocumentFragment();
                    }
                }
            }
        }
        this.fValueStoreCache.endElement();
        SchemaGrammar[] grammars = null;
        if (this.fElementDepth == 0) {
            final String invIdRef = this.fValidationState.checkIDRefID();
            if (invIdRef != null) {
                this.reportSchemaError("cvc-id.1", new Object[] { invIdRef });
            }
            if (this.fFullChecking) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter);
            }
            this.fValidationState.resetIDTables();
            grammars = this.fGrammarBucket.getGrammars();
            if (this.fGrammarPool != null) {
                this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", grammars);
            }
            augs = this.endElementPSVI(true, grammars, augs);
        }
        else {
            augs = this.endElementPSVI(false, grammars, augs);
            --this.fElementDepth;
            this.fSubElement = this.fSubElementStack[this.fElementDepth];
            this.fCurrentElemDecl = this.fElemDeclStack[this.fElementDepth];
            this.fNil = this.fNilStack[this.fElementDepth];
            this.fNotation = this.fNotationStack[this.fElementDepth];
            this.fCurrentType = this.fTypeStack[this.fElementDepth];
            this.fCurrentCM = this.fCMStack[this.fElementDepth];
            this.fStrictAssess = this.fStrictAssessStack[this.fElementDepth];
            this.fCurrCMState = this.fCMStateStack[this.fElementDepth];
            this.fSawText = this.fSawTextStack[this.fElementDepth];
            this.fSawCharacters = this.fStringContent[this.fElementDepth];
            this.fSawChildren = this.fSawChildrenStack[this.fElementDepth];
            this.fWhiteSpace = -1;
            this.fAppendBuffer = false;
            this.fUnionType = false;
        }
        return augs;
    }
    
    final Augmentations endElementPSVI(final boolean root, final SchemaGrammar[] grammars, Augmentations augs) {
        if (this.fAugPSVI) {
            augs = this.getEmptyAugs(augs);
            this.fCurrentPSVI.fDeclaration = this.fCurrentElemDecl;
            this.fCurrentPSVI.fTypeDecl = this.fCurrentType;
            this.fCurrentPSVI.fNotation = this.fNotation;
            this.fCurrentPSVI.fValidationContext = this.fValidationRoot;
            if (this.fElementDepth > this.fNFullValidationDepth) {
                this.fCurrentPSVI.fValidationAttempted = 2;
            }
            else if (this.fElementDepth > this.fNNoneValidationDepth) {
                this.fCurrentPSVI.fValidationAttempted = 0;
            }
            else {
                this.fCurrentPSVI.fValidationAttempted = 1;
                final int n = this.fElementDepth - 1;
                this.fNNoneValidationDepth = n;
                this.fNFullValidationDepth = n;
            }
            if (this.fDefaultValue != null) {
                this.fCurrentPSVI.fSpecified = true;
            }
            this.fCurrentPSVI.fNil = this.fNil;
            this.fCurrentPSVI.fMemberType = this.fValidatedInfo.memberType;
            this.fCurrentPSVI.fNormalizedValue = this.fValidatedInfo.normalizedValue;
            if (this.fStrictAssess) {
                final String[] errors = this.fXSIErrorReporter.mergeContext();
                this.fCurrentPSVI.fErrorCodes = errors;
                this.fCurrentPSVI.fValidity = (short)((errors == null) ? 2 : 1);
            }
            else {
                this.fCurrentPSVI.fValidity = 0;
                this.fXSIErrorReporter.popContext();
            }
            if (root) {
                this.fCurrentPSVI.fSchemaInformation = new XSModelImpl(grammars);
            }
        }
        return augs;
    }
    
    Augmentations getEmptyAugs(Augmentations augs) {
        if (augs == null) {
            augs = this.fAugmentations;
            augs.clear();
        }
        augs.putItem("ELEMENT_PSVI", this.fCurrentPSVI);
        this.fCurrentPSVI.reset();
        return augs;
    }
    
    void storeLocations(final String sLocation, final String nsLocation) {
        if (sLocation != null && !XMLSchemaLoader.tokenizeSchemaLocationStr(sLocation, this.fLocationPairs)) {
            this.fXSIErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "SchemaLocation", new Object[] { sLocation }, (short)0);
        }
        if (nsLocation != null) {
            this.fNoNamespaceLocationArray.addLocation(nsLocation);
            this.fLocationPairs.put(XMLSymbols.EMPTY_STRING, this.fNoNamespaceLocationArray);
        }
    }
    
    SchemaGrammar findSchemaGrammar(final short contextType, final String namespace, final QName enclosingElement, final QName triggeringComponet, final XMLAttributes attributes) {
        SchemaGrammar grammar = null;
        grammar = this.fGrammarBucket.getGrammar(namespace);
        if (grammar == null) {
            this.fXSDDescription.reset();
            this.fXSDDescription.fContextType = contextType;
            this.fXSDDescription.fTargetNamespace = namespace;
            this.fXSDDescription.fEnclosedElementName = enclosingElement;
            this.fXSDDescription.fTriggeringComponent = triggeringComponet;
            this.fXSDDescription.fAttributes = attributes;
            if (this.fBaseURI != null) {
                this.fXSDDescription.setBaseSystemId(this.fBaseURI);
            }
            String[] temp = null;
            if (namespace != null) {
                final Object locationArray = this.fLocationPairs.get(namespace);
                if (locationArray != null) {
                    temp = ((XMLSchemaLoader.LocationArray)locationArray).getLocationArray();
                }
            }
            else {
                temp = this.fNoNamespaceLocationArray.getLocationArray();
            }
            if (temp != null && temp.length != 0) {
                System.arraycopy(temp, 0, this.fXSDDescription.fLocationHints = new String[temp.length], 0, temp.length);
            }
            if (this.fGrammarPool != null) {
                grammar = (SchemaGrammar)this.fGrammarPool.retrieveGrammar(this.fXSDDescription);
                if (grammar != null && !this.fGrammarBucket.putGrammar(grammar, true)) {
                    this.fXSIErrorReporter.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "GrammarConflict", null, (short)0);
                    grammar = null;
                }
            }
            if (grammar == null) {
                try {
                    final XMLInputSource xis = XMLSchemaLoader.resolveDocument(this.fXSDDescription, this.fLocationPairs, this.fEntityResolver);
                    grammar = this.fSchemaLoader.loadSchema(this.fXSDDescription, xis, this.fLocationPairs);
                }
                catch (IOException ex) {
                    this.fXSIErrorReporter.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "schema_reference.4", new Object[] { this.fXSDDescription.getLocationHints()[0] }, (short)0);
                }
            }
        }
        return grammar;
    }
    
    XSTypeDecl getAndCheckXsiType(final QName element, final String xsiType, final XMLAttributes attributes) {
        QName typeName = null;
        try {
            typeName = (QName)this.fQNameDV.validate(xsiType, this.fValidationState, null);
        }
        catch (InvalidDatatypeValueException e) {
            this.reportSchemaError(e.getKey(), e.getArgs());
            this.reportSchemaError("cvc-elt.4.1", new Object[] { element.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_TYPE, xsiType });
            return null;
        }
        XSTypeDecl type = null;
        if (typeName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
            type = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(typeName.localpart);
        }
        if (type == null) {
            final SchemaGrammar grammar = this.findSchemaGrammar((short)7, typeName.uri, element, typeName, attributes);
            if (grammar != null) {
                type = grammar.getGlobalTypeDecl(typeName.localpart);
            }
        }
        if (type == null) {
            this.reportSchemaError("cvc-elt.4.2", new Object[] { element.rawname, xsiType });
            return null;
        }
        if (this.fCurrentType != null) {
            short block = this.fCurrentElemDecl.fBlock;
            if (this.fCurrentType.getTypeCategory() == 13) {
                block |= ((XSComplexTypeDecl)this.fCurrentType).fBlock;
            }
            if (!XSConstraints.checkTypeDerivationOk(type, this.fCurrentType, block)) {
                this.reportSchemaError("cvc-elt.4.3", new Object[] { element.rawname, xsiType });
            }
        }
        return type;
    }
    
    boolean getXsiNil(final QName element, final String xsiNil) {
        if (this.fCurrentElemDecl != null && !this.fCurrentElemDecl.getIsNillable()) {
            this.reportSchemaError("cvc-elt.3.1", new Object[] { element.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL });
        }
        else {
            final String value = xsiNil.trim();
            if (value.equals("true") || value.equals("1")) {
                if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == 2) {
                    this.reportSchemaError("cvc-elt.3.2.2", new Object[] { element.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL });
                }
                return true;
            }
        }
        return false;
    }
    
    void processAttributes(final QName element, final XMLAttributes attributes, final XSAttributeGroupDecl attrGrp) {
        String wildcardIDName = null;
        final int attCount = attributes.getLength();
        Augmentations augs = null;
        AttributePSVImpl attrPSVI = null;
        final boolean isSimple = this.fCurrentType == null || this.fCurrentType.getTypeCategory() == 14;
        XSObjectList attrUses = null;
        int useCount = 0;
        XSWildcardDecl attrWildcard = null;
        if (!isSimple) {
            attrUses = attrGrp.getAttributeUses();
            useCount = attrUses.getLength();
            attrWildcard = attrGrp.fAttributeWC;
        }
        for (int index = 0; index < attCount; ++index) {
            attributes.getName(index, this.fTempQName);
            if (this.fAugPSVI) {
                augs = attributes.getAugmentations(index);
                attrPSVI = (AttributePSVImpl)augs.getItem("ATTRIBUTE_PSVI");
                if (attrPSVI != null) {
                    attrPSVI.reset();
                }
                else {
                    attrPSVI = new AttributePSVImpl();
                    augs.putItem("ATTRIBUTE_PSVI", attrPSVI);
                }
                attrPSVI.fValidationContext = this.fValidationRoot;
            }
            if (this.fTempQName.uri == SchemaSymbols.URI_XSI) {
                XSAttributeDecl attrDecl = null;
                if (this.fTempQName.localpart == SchemaSymbols.XSI_SCHEMALOCATION) {
                    attrDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_SCHEMALOCATION);
                }
                else if (this.fTempQName.localpart == SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION) {
                    attrDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION);
                }
                else if (this.fTempQName.localpart == SchemaSymbols.XSI_NIL) {
                    attrDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NIL);
                }
                else if (this.fTempQName.localpart == SchemaSymbols.XSI_TYPE) {
                    attrDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_TYPE);
                }
                if (attrDecl != null) {
                    this.processOneAttribute(element, attributes, index, attrDecl, null, attrPSVI);
                    continue;
                }
            }
            if (this.fTempQName.rawname != XMLSymbols.PREFIX_XMLNS) {
                if (!this.fTempQName.rawname.startsWith("xmlns:")) {
                    if (isSimple) {
                        this.reportSchemaError("cvc-type.3.1.1", new Object[] { element.rawname });
                    }
                    else {
                        XSAttributeUseImpl currUse = null;
                        for (int i = 0; i < useCount; ++i) {
                            final XSAttributeUseImpl oneUse = (XSAttributeUseImpl)attrUses.getItem(i);
                            if (oneUse.fAttrDecl.fName == this.fTempQName.localpart && oneUse.fAttrDecl.fTargetNamespace == this.fTempQName.uri) {
                                currUse = oneUse;
                                break;
                            }
                        }
                        if (currUse == null && (attrWildcard == null || !attrWildcard.allowNamespace(this.fTempQName.uri))) {
                            this.reportSchemaError("cvc-complex-type.3.2.2", new Object[] { element.rawname, this.fTempQName.rawname });
                        }
                        else {
                            XSAttributeDecl currDecl = null;
                            if (currUse != null) {
                                currDecl = currUse.fAttrDecl;
                            }
                            else {
                                if (attrWildcard.fProcessContents == 2) {
                                    continue;
                                }
                                final SchemaGrammar grammar = this.findSchemaGrammar((short)6, this.fTempQName.uri, element, this.fTempQName, attributes);
                                if (grammar != null) {
                                    currDecl = grammar.getGlobalAttributeDecl(this.fTempQName.localpart);
                                }
                                if (currDecl == null) {
                                    if (attrWildcard.fProcessContents == 1) {
                                        this.reportSchemaError("cvc-complex-type.3.2.2", new Object[] { element.rawname, this.fTempQName.rawname });
                                    }
                                    continue;
                                }
                                else if (currDecl.fType.getTypeCategory() == 14 && currDecl.fType.isIDType()) {
                                    if (wildcardIDName != null) {
                                        this.reportSchemaError("cvc-complex-type.5.1", new Object[] { element.rawname, currDecl.fName, wildcardIDName });
                                    }
                                    else {
                                        wildcardIDName = currDecl.fName;
                                    }
                                }
                            }
                            this.processOneAttribute(element, attributes, index, currDecl, currUse, attrPSVI);
                        }
                    }
                }
            }
        }
        if (!isSimple && attrGrp.fIDAttrName != null && wildcardIDName != null) {
            this.reportSchemaError("cvc-complex-type.5.2", new Object[] { element.rawname, wildcardIDName, attrGrp.fIDAttrName });
        }
    }
    
    void processOneAttribute(final QName element, final XMLAttributes attributes, final int index, final XSAttributeDecl currDecl, final XSAttributeUseImpl currUse, final AttributePSVImpl attrPSVI) {
        final String attrValue = attributes.getValue(index);
        this.fXSIErrorReporter.pushContext();
        final XSSimpleType attDV = currDecl.fType;
        Object actualValue = null;
        try {
            actualValue = attDV.validate(attrValue, this.fValidationState, this.fValidatedInfo);
            if (this.fNormalizeData) {
                attributes.setValue(index, this.fValidatedInfo.normalizedValue);
            }
            if (attributes instanceof XMLAttributesImpl) {
                final XMLAttributesImpl attrs = (XMLAttributesImpl)attributes;
                final boolean schemaId = (this.fValidatedInfo.memberType != null) ? this.fValidatedInfo.memberType.isIDType() : attDV.isIDType();
                attrs.setSchemaId(index, schemaId);
            }
            if (attDV.getVariety() == 1 && attDV.getPrimitiveKind() == 19) {
                final QName qName = (QName)actualValue;
                final SchemaGrammar grammar = this.fGrammarBucket.getGrammar(qName.uri);
                if (grammar != null) {
                    this.fNotation = grammar.getGlobalNotationDecl(qName.localpart);
                }
            }
        }
        catch (InvalidDatatypeValueException idve) {
            this.reportSchemaError(idve.getKey(), idve.getArgs());
            this.reportSchemaError("cvc-attribute.3", new Object[] { element.rawname, this.fTempQName.rawname, attrValue });
        }
        if (actualValue != null && currDecl.getConstraintType() == 2 && !actualValue.equals(currDecl.fDefault.actualValue)) {
            this.reportSchemaError("cvc-attribute.4", new Object[] { element.rawname, this.fTempQName.rawname, attrValue });
        }
        if (actualValue != null && currUse != null && currUse.fConstraintType == 2 && !actualValue.equals(currUse.fDefault.actualValue)) {
            this.reportSchemaError("cvc-complex-type.3.1", new Object[] { element.rawname, this.fTempQName.rawname, attrValue });
        }
        if (this.fAugPSVI) {
            attrPSVI.fDeclaration = currDecl;
            if (currDecl != null && currDecl.fDefault != null) {
                attrPSVI.fSchemaDefault = currDecl.fDefault.toString();
            }
            attrPSVI.fTypeDecl = attDV;
            attrPSVI.fMemberType = this.fValidatedInfo.memberType;
            attrPSVI.fNormalizedValue = this.fValidatedInfo.normalizedValue;
            attrPSVI.fValidationAttempted = 2;
            final String[] errors = this.fXSIErrorReporter.mergeContext();
            attrPSVI.fValidity = (short)(((attrPSVI.fErrorCodes = errors) == null) ? 2 : 1);
        }
    }
    
    void addDefaultAttributes(final QName element, final XMLAttributes attributes, final XSAttributeGroupDecl attrGrp) {
        final XSObjectList attrUses = attrGrp.getAttributeUses();
        for (int useCount = attrUses.getLength(), i = 0; i < useCount; ++i) {
            final XSAttributeUseImpl currUse = (XSAttributeUseImpl)attrUses.getItem(i);
            final XSAttributeDecl currDecl = currUse.fAttrDecl;
            short constType = currUse.fConstraintType;
            ValidatedInfo defaultValue = currUse.fDefault;
            if (constType == 0) {
                constType = currDecl.getConstraintType();
                defaultValue = currDecl.fDefault;
            }
            final boolean isSpecified = attributes.getValue(currDecl.fTargetNamespace, currDecl.fName) != null;
            if (currUse.fUse == 1 && !isSpecified) {
                this.reportSchemaError("cvc-complex-type.4", new Object[] { element.rawname, currDecl.fName });
            }
            if (!isSpecified && constType != 0) {
                final QName attName = new QName(null, currDecl.fName, currDecl.fName, currDecl.fTargetNamespace);
                final String normalized = (defaultValue != null) ? defaultValue.stringValue() : "";
                final int attrIndex = attributes.addAttribute(attName, "CDATA", normalized);
                if (attributes instanceof XMLAttributesImpl) {
                    final XMLAttributesImpl attrs = (XMLAttributesImpl)attributes;
                    final boolean schemaId = (defaultValue != null && defaultValue.memberType != null) ? defaultValue.memberType.isIDType() : currDecl.fType.isIDType();
                    attrs.setSchemaId(attrIndex, schemaId);
                }
                if (this.fAugPSVI) {
                    final Augmentations augs = attributes.getAugmentations(attrIndex);
                    final AttributePSVImpl attrPSVI = new AttributePSVImpl();
                    augs.putItem("ATTRIBUTE_PSVI", attrPSVI);
                    attrPSVI.fDeclaration = currDecl;
                    attrPSVI.fTypeDecl = currDecl.fType;
                    attrPSVI.fMemberType = defaultValue.memberType;
                    attrPSVI.fNormalizedValue = normalized;
                    attrPSVI.fSchemaDefault = normalized;
                    attrPSVI.fValidationContext = this.fValidationRoot;
                    attrPSVI.fValidity = 2;
                    attrPSVI.fValidationAttempted = 2;
                    attrPSVI.fSpecified = true;
                }
            }
        }
    }
    
    void processElementContent(final QName element) {
        if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.fDefault != null && !this.fSawText && !this.fSubElement && !this.fNil) {
            final String strv = this.fCurrentElemDecl.fDefault.stringValue();
            final int bufLen = strv.length();
            if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < bufLen) {
                this.fNormalizedStr.ch = new char[bufLen];
            }
            strv.getChars(0, bufLen, this.fNormalizedStr.ch, 0);
            this.fNormalizedStr.offset = 0;
            this.fNormalizedStr.length = bufLen;
            this.fDefaultValue = this.fNormalizedStr;
        }
        this.fValidatedInfo.normalizedValue = null;
        if (this.fNil && (this.fSubElement || this.fSawText)) {
            this.reportSchemaError("cvc-elt.3.2.1", new Object[] { element.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL });
        }
        this.fValidatedInfo.reset();
        if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() != 0 && !this.fSubElement && !this.fSawText && !this.fNil) {
            if (this.fCurrentType != this.fCurrentElemDecl.fType && XSConstraints.ElementDefaultValidImmediate(this.fCurrentType, this.fCurrentElemDecl.fDefault.stringValue(), this.fState4XsiType, null) == null) {
                this.reportSchemaError("cvc-elt.5.1.1", new Object[] { element.rawname, this.fCurrentType.getName(), this.fCurrentElemDecl.fDefault.stringValue() });
            }
            this.elementLocallyValidType(element, this.fCurrentElemDecl.fDefault.stringValue());
        }
        else {
            final Object actualValue = this.elementLocallyValidType(element, this.fBuffer);
            if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == 2 && !this.fNil) {
                final String content = this.fBuffer.toString();
                if (this.fSubElement) {
                    this.reportSchemaError("cvc-elt.5.2.2.1", new Object[] { element.rawname });
                }
                if (this.fCurrentType.getTypeCategory() == 13) {
                    final XSComplexTypeDecl ctype = (XSComplexTypeDecl)this.fCurrentType;
                    if (ctype.fContentType == 3) {
                        if (!this.fCurrentElemDecl.fDefault.normalizedValue.equals(content)) {
                            this.reportSchemaError("cvc-elt.5.2.2.2.1", new Object[] { element.rawname, content, this.fCurrentElemDecl.fDefault.normalizedValue });
                        }
                    }
                    else if (ctype.fContentType == 1 && actualValue != null && !actualValue.equals(this.fCurrentElemDecl.fDefault.actualValue)) {
                        this.reportSchemaError("cvc-elt.5.2.2.2.2", new Object[] { element.rawname, content, this.fCurrentElemDecl.fDefault.stringValue() });
                    }
                }
                else if (this.fCurrentType.getTypeCategory() == 14) {
                    final XSSimpleType sType = (XSSimpleType)this.fCurrentType;
                    if (actualValue != null && !actualValue.equals(this.fCurrentElemDecl.fDefault.actualValue)) {
                        this.reportSchemaError("cvc-elt.5.2.2.2.2", new Object[] { element.rawname, content, this.fCurrentElemDecl.fDefault.stringValue() });
                    }
                }
            }
        }
        if (this.fDefaultValue == null && this.fNormalizeData && this.fDocumentHandler != null && this.fUnionType) {
            String content2 = this.fValidatedInfo.normalizedValue;
            if (content2 == null) {
                content2 = this.fBuffer.toString();
            }
            final int bufLen = content2.length();
            if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < bufLen) {
                this.fNormalizedStr.ch = new char[bufLen];
            }
            content2.getChars(0, bufLen, this.fNormalizedStr.ch, 0);
            this.fNormalizedStr.offset = 0;
            this.fNormalizedStr.length = bufLen;
            this.fDocumentHandler.characters(this.fNormalizedStr, null);
        }
    }
    
    Object elementLocallyValidType(final QName element, final Object textContent) {
        if (this.fCurrentType == null) {
            return null;
        }
        Object retValue = null;
        if (this.fCurrentType.getTypeCategory() == 14) {
            if (this.fSubElement) {
                this.reportSchemaError("cvc-type.3.1.2", new Object[] { element.rawname });
            }
            if (!this.fNil) {
                final XSSimpleType dv = (XSSimpleType)this.fCurrentType;
                try {
                    if (!this.fNormalizeData || this.fUnionType) {
                        this.fValidationState.setNormalizationRequired(true);
                    }
                    retValue = dv.validate(textContent, this.fValidationState, this.fValidatedInfo);
                }
                catch (InvalidDatatypeValueException e) {
                    this.reportSchemaError(e.getKey(), e.getArgs());
                    this.reportSchemaError("cvc-type.3.1.3", new Object[] { element.rawname, textContent });
                }
            }
        }
        else {
            retValue = this.elementLocallyValidComplexType(element, textContent);
        }
        return retValue;
    }
    
    Object elementLocallyValidComplexType(final QName element, final Object textContent) {
        Object actualValue = null;
        final XSComplexTypeDecl ctype = (XSComplexTypeDecl)this.fCurrentType;
        if (!this.fNil) {
            if (ctype.fContentType == 0 && (this.fSubElement || this.fSawText || this.fSawChildren)) {
                this.reportSchemaError("cvc-complex-type.2.1", new Object[] { element.rawname });
            }
            else if (ctype.fContentType == 1) {
                if (this.fSubElement) {
                    this.reportSchemaError("cvc-complex-type.2.2", new Object[] { element.rawname });
                }
                final XSSimpleType dv = ctype.fXSSimpleType;
                try {
                    if (!this.fNormalizeData || this.fUnionType) {
                        this.fValidationState.setNormalizationRequired(true);
                    }
                    actualValue = dv.validate(textContent, this.fValidationState, this.fValidatedInfo);
                }
                catch (InvalidDatatypeValueException e) {
                    this.reportSchemaError(e.getKey(), e.getArgs());
                    this.reportSchemaError("cvc-complex-type.2.2", new Object[] { element.rawname });
                }
            }
            else if (ctype.fContentType == 2 && this.fSawCharacters) {
                this.reportSchemaError("cvc-complex-type.2.3", new Object[] { element.rawname });
            }
            if ((ctype.fContentType == 2 || ctype.fContentType == 3) && this.fCurrCMState[0] >= 0 && !this.fCurrentCM.endContentModel(this.fCurrCMState)) {
                this.reportSchemaError("cvc-complex-type.2.4.b", new Object[] { element.rawname, ((XSParticleDecl)ctype.getParticle()).toString() });
            }
        }
        return actualValue;
    }
    
    void reportSchemaError(final String key, final Object[] arguments) {
        if (this.fDoValidation) {
            this.fXSIErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", key, arguments, (short)1);
        }
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://apache.org/xml/features/validation/schema", "http://apache.org/xml/features/validation/dynamic", "http://apache.org/xml/features/validation/schema-full-checking", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/continue-after-fatal-error" };
        FEATURE_DEFAULTS = new Boolean[] { null, null, null, null, null, null };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/validation-manager", "http://apache.org/xml/properties/schema/external-schemaLocation", "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage" };
        PROPERTY_DEFAULTS = new Object[] { null, null, null, null, null, null, null, null };
    }
    
    protected final class XSIErrorReporter
    {
        XMLErrorReporter fErrorReporter;
        Vector fErrors;
        int[] fContext;
        int fContextCount;
        
        protected XSIErrorReporter() {
            this.fErrors = new Vector(8, 8);
            this.fContext = new int[8];
        }
        
        public void reset(final XMLErrorReporter errorReporter) {
            this.fErrorReporter = errorReporter;
            this.fErrors.removeAllElements();
            this.fContextCount = 0;
        }
        
        public void pushContext() {
            if (!XMLSchemaValidator.this.fAugPSVI) {
                return;
            }
            if (this.fContextCount == this.fContext.length) {
                final int newSize = this.fContextCount + 8;
                final int[] newArray = new int[newSize];
                System.arraycopy(this.fContext, 0, newArray, 0, this.fContextCount);
                this.fContext = newArray;
            }
            this.fContext[this.fContextCount++] = this.fErrors.size();
        }
        
        public String[] popContext() {
            if (!XMLSchemaValidator.this.fAugPSVI) {
                return null;
            }
            final int[] fContext = this.fContext;
            final int fContextCount = this.fContextCount - 1;
            this.fContextCount = fContextCount;
            final int contextPos = fContext[fContextCount];
            final int size = this.fErrors.size() - contextPos;
            if (size == 0) {
                return null;
            }
            final String[] errors = new String[size];
            for (int i = 0; i < size; ++i) {
                errors[i] = this.fErrors.elementAt(contextPos + i);
            }
            this.fErrors.setSize(contextPos);
            return errors;
        }
        
        public String[] mergeContext() {
            if (!XMLSchemaValidator.this.fAugPSVI) {
                return null;
            }
            final int[] fContext = this.fContext;
            final int fContextCount = this.fContextCount - 1;
            this.fContextCount = fContextCount;
            final int contextPos = fContext[fContextCount];
            final int size = this.fErrors.size() - contextPos;
            if (size == 0) {
                return null;
            }
            final String[] errors = new String[size];
            for (int i = 0; i < size; ++i) {
                errors[i] = this.fErrors.elementAt(contextPos + i);
            }
            return errors;
        }
        
        public void reportError(final String domain, final String key, final Object[] arguments, final short severity) throws XNIException {
            this.fErrorReporter.reportError(domain, key, arguments, severity);
            this.fErrors.addElement(key);
        }
        
        public void reportError(final XMLLocator location, final String domain, final String key, final Object[] arguments, final short severity) throws XNIException {
            this.fErrorReporter.reportError(location, domain, key, arguments, severity);
            this.fErrors.addElement(key);
        }
    }
    
    protected static class XPathMatcherStack
    {
        protected XPathMatcher[] fMatchers;
        protected int fMatchersCount;
        protected IntStack fContextStack;
        
        public XPathMatcherStack() {
            this.fMatchers = new XPathMatcher[4];
            this.fContextStack = new IntStack();
        }
        
        public void clear() {
            for (int i = 0; i < this.fMatchersCount; ++i) {
                this.fMatchers[i] = null;
            }
            this.fMatchersCount = 0;
            this.fContextStack.clear();
        }
        
        public int size() {
            return this.fContextStack.size();
        }
        
        public int getMatcherCount() {
            return this.fMatchersCount;
        }
        
        public void addMatcher(final XPathMatcher matcher) {
            this.ensureMatcherCapacity();
            this.fMatchers[this.fMatchersCount++] = matcher;
        }
        
        public XPathMatcher getMatcherAt(final int index) {
            return this.fMatchers[index];
        }
        
        public void pushContext() {
            this.fContextStack.push(this.fMatchersCount);
        }
        
        public void popContext() {
            this.fMatchersCount = this.fContextStack.pop();
        }
        
        private void ensureMatcherCapacity() {
            if (this.fMatchersCount == this.fMatchers.length) {
                final XPathMatcher[] array = new XPathMatcher[this.fMatchers.length * 2];
                System.arraycopy(this.fMatchers, 0, array, 0, this.fMatchers.length);
                this.fMatchers = array;
            }
        }
    }
    
    protected abstract class ValueStoreBase implements ValueStore
    {
        protected IDValue NOT_AN_IDVALUE;
        protected IdentityConstraint fIdentityConstraint;
        protected final OrderedHashtable fValues;
        protected int fValuesCount;
        protected final Vector fValueTuples;
        
        protected ValueStoreBase(final IdentityConstraint identityConstraint) {
            this.NOT_AN_IDVALUE = new IDValue("\uffff", null);
            this.fValues = new OrderedHashtable();
            this.fValueTuples = new Vector();
            this.fIdentityConstraint = identityConstraint;
        }
        
        public void clear() {
            this.fValuesCount = 0;
            this.fValues.clear();
            this.fValueTuples.removeAllElements();
        }
        
        public void append(final ValueStoreBase newVal) {
            for (int i = 0; i < newVal.fValueTuples.size(); ++i) {
                final OrderedHashtable o = newVal.fValueTuples.elementAt(i);
                if (!this.contains(o)) {
                    this.fValueTuples.addElement(o);
                }
            }
        }
        
        public void startValueScope() throws XNIException {
            this.fValuesCount = 0;
            for (int count = this.fIdentityConstraint.getFieldCount(), i = 0; i < count; ++i) {
                this.fValues.put(this.fIdentityConstraint.getFieldAt(i), this.NOT_AN_IDVALUE);
            }
        }
        
        public void endValueScope() throws XNIException {
            if (this.fValuesCount == 0) {
                if (this.fIdentityConstraint.getCategory() == 1) {
                    final String code = "AbsentKeyValue";
                    final String eName = this.fIdentityConstraint.getElementName();
                    XMLSchemaValidator.this.reportSchemaError(code, new Object[] { eName });
                }
                return;
            }
            if (this.fValuesCount != this.fIdentityConstraint.getFieldCount()) {
                switch (this.fIdentityConstraint.getCategory()) {
                    case 3: {
                        final String code = "UniqueNotEnoughValues";
                        final String ename = this.fIdentityConstraint.getElementName();
                        XMLSchemaValidator.this.reportSchemaError(code, new Object[] { ename });
                        break;
                    }
                    case 1: {
                        final String code = "KeyNotEnoughValues";
                        final UniqueOrKey key = (UniqueOrKey)this.fIdentityConstraint;
                        final String ename2 = this.fIdentityConstraint.getElementName();
                        final String kname = key.getIdentityConstraintName();
                        XMLSchemaValidator.this.reportSchemaError(code, new Object[] { ename2, kname });
                        break;
                    }
                    case 2: {
                        final String code = "KeyRefNotEnoughValues";
                        final KeyRef keyref = (KeyRef)this.fIdentityConstraint;
                        final String ename2 = this.fIdentityConstraint.getElementName();
                        final String kname = keyref.getKey().getIdentityConstraintName();
                        XMLSchemaValidator.this.reportSchemaError(code, new Object[] { ename2, kname });
                        break;
                    }
                }
            }
        }
        
        public void endDocumentFragment() throws XNIException {
        }
        
        public void endDocument() throws XNIException {
        }
        
        public void reportError(final String key, final Object[] args) {
            XMLSchemaValidator.this.reportSchemaError(key, args);
        }
        
        public void addValue(final Field field, final IDValue value) {
            if (!field.mayMatch()) {
                final String code = "FieldMultipleMatch";
                XMLSchemaValidator.this.reportSchemaError(code, new Object[] { field.toString() });
            }
            final int index = this.fValues.indexOf(field);
            if (index == -1) {
                final String code2 = "UnknownField";
                XMLSchemaValidator.this.reportSchemaError(code2, new Object[] { field.toString() });
                return;
            }
            final IDValue storedValue = this.fValues.valueAt(index);
            if (storedValue.isDuplicateOf(this.NOT_AN_IDVALUE)) {
                ++this.fValuesCount;
            }
            this.fValues.put(field, value);
            if (this.fValuesCount == this.fValues.size()) {
                if (this.contains(this.fValues)) {
                    this.duplicateValue(this.fValues);
                }
                final OrderedHashtable values = (OrderedHashtable)this.fValues.clone();
                this.fValueTuples.addElement(values);
            }
        }
        
        public boolean contains(final OrderedHashtable tuple) {
            final int tcount = tuple.size();
            final int count = this.fValueTuples.size();
            int i = 0;
        Label_0083:
            while (i < count) {
                final OrderedHashtable vtuple = this.fValueTuples.elementAt(i);
                for (int j = 0; j < tcount; ++j) {
                    final IDValue value1 = vtuple.valueAt(j);
                    final IDValue value2 = tuple.valueAt(j);
                    if (!value1.isDuplicateOf(value2)) {
                        ++i;
                        continue Label_0083;
                    }
                }
                return true;
            }
            return false;
        }
        
        protected void duplicateValue(final OrderedHashtable tuple) throws XNIException {
        }
        
        protected String toString(final OrderedHashtable tuple) {
            final int size = tuple.size();
            if (size == 0) {
                return "";
            }
            final StringBuffer str = new StringBuffer();
            for (int i = 0; i < size; ++i) {
                if (i > 0) {
                    str.append(',');
                }
                str.append(tuple.valueAt(i));
            }
            return str.toString();
        }
        
        public String toString() {
            String s = super.toString();
            final int index1 = s.lastIndexOf(36);
            if (index1 != -1) {
                s = s.substring(index1 + 1);
            }
            final int index2 = s.lastIndexOf(46);
            if (index2 != -1) {
                s = s.substring(index2 + 1);
            }
            return s + '[' + this.fIdentityConstraint + ']';
        }
    }
    
    protected class UniqueValueStore extends ValueStoreBase
    {
        public UniqueValueStore(final UniqueOrKey unique) {
            super(unique);
        }
        
        protected void duplicateValue(final OrderedHashtable tuple) throws XNIException {
            final String code = "DuplicateUnique";
            final String value = this.toString(tuple);
            final String ename = super.fIdentityConstraint.getElementName();
            XMLSchemaValidator.this.reportSchemaError(code, new Object[] { value, ename });
        }
    }
    
    protected class KeyValueStore extends ValueStoreBase
    {
        public KeyValueStore(final UniqueOrKey key) {
            super(key);
        }
        
        protected void duplicateValue(final OrderedHashtable tuple) throws XNIException {
            final String code = "DuplicateKey";
            final String value = this.toString(tuple);
            final String ename = super.fIdentityConstraint.getElementName();
            XMLSchemaValidator.this.reportSchemaError(code, new Object[] { value, ename });
        }
    }
    
    protected class KeyRefValueStore extends ValueStoreBase
    {
        protected ValueStoreBase fKeyValueStore;
        
        public KeyRefValueStore(final KeyRef keyRef, final KeyValueStore keyValueStore) {
            super(keyRef);
            this.fKeyValueStore = keyValueStore;
        }
        
        public void endDocumentFragment() throws XNIException {
            super.endDocumentFragment();
            this.fKeyValueStore = XMLSchemaValidator.this.fValueStoreCache.fGlobalIDConstraintMap.get(((KeyRef)super.fIdentityConstraint).getKey());
            if (this.fKeyValueStore == null) {
                final String code = "KeyRefOutOfScope";
                final String value = super.fIdentityConstraint.toString();
                XMLSchemaValidator.this.reportSchemaError(code, new Object[] { value });
                return;
            }
            for (int count = super.fValueTuples.size(), i = 0; i < count; ++i) {
                final OrderedHashtable values = super.fValueTuples.elementAt(i);
                if (!this.fKeyValueStore.contains(values)) {
                    final String code2 = "KeyNotFound";
                    final String value2 = this.toString(values);
                    final String element = super.fIdentityConstraint.getElementName();
                    final String name = super.fIdentityConstraint.getName();
                    XMLSchemaValidator.this.reportSchemaError(code2, new Object[] { name, value2, element });
                }
            }
        }
        
        public void endDocument() throws XNIException {
            super.endDocument();
        }
    }
    
    protected class ValueStoreCache
    {
        protected final Vector fValueStores;
        protected final Hashtable fIdentityConstraint2ValueStoreMap;
        protected final Stack fGlobalMapStack;
        protected final Hashtable fGlobalIDConstraintMap;
        
        public ValueStoreCache() {
            this.fValueStores = new Vector();
            this.fIdentityConstraint2ValueStoreMap = new Hashtable();
            this.fGlobalMapStack = new Stack();
            this.fGlobalIDConstraintMap = new Hashtable();
        }
        
        public void startDocument() throws XNIException {
            this.fValueStores.removeAllElements();
            this.fIdentityConstraint2ValueStoreMap.clear();
            this.fGlobalIDConstraintMap.clear();
            this.fGlobalMapStack.removeAllElements();
        }
        
        public void startElement() {
            if (this.fGlobalIDConstraintMap.size() > 0) {
                this.fGlobalMapStack.push(this.fGlobalIDConstraintMap.clone());
            }
            else {
                this.fGlobalMapStack.push(null);
            }
            this.fGlobalIDConstraintMap.clear();
        }
        
        public void endElement() {
            if (this.fGlobalMapStack.isEmpty()) {
                return;
            }
            final Hashtable oldMap = this.fGlobalMapStack.pop();
            if (oldMap == null) {
                return;
            }
            final Enumeration keys = oldMap.keys();
            while (keys.hasMoreElements()) {
                final IdentityConstraint id = keys.nextElement();
                final ValueStoreBase oldVal = oldMap.get(id);
                if (oldVal != null) {
                    final ValueStoreBase currVal = this.fGlobalIDConstraintMap.get(id);
                    if (currVal == null) {
                        this.fGlobalIDConstraintMap.put(id, oldVal);
                    }
                    else {
                        currVal.append(oldVal);
                    }
                }
            }
        }
        
        public void initValueStoresFor(final XSElementDecl eDecl) {
            final IdentityConstraint[] icArray = eDecl.fIDConstraints;
            for (int icCount = eDecl.fIDCPos, i = 0; i < icCount; ++i) {
                switch (icArray[i].getCategory()) {
                    case 3: {
                        final UniqueOrKey unique = (UniqueOrKey)icArray[i];
                        final LocalIDKey toHash = new LocalIDKey(unique, XMLSchemaValidator.this.fElementDepth);
                        UniqueValueStore uniqueValueStore = this.fIdentityConstraint2ValueStoreMap.get(toHash);
                        if (uniqueValueStore == null) {
                            uniqueValueStore = new UniqueValueStore(unique);
                            this.fIdentityConstraint2ValueStoreMap.put(toHash, uniqueValueStore);
                        }
                        else {
                            uniqueValueStore.clear();
                        }
                        this.fValueStores.addElement(uniqueValueStore);
                        break;
                    }
                    case 1: {
                        final UniqueOrKey key = (UniqueOrKey)icArray[i];
                        final LocalIDKey toHash = new LocalIDKey(key, XMLSchemaValidator.this.fElementDepth);
                        KeyValueStore keyValueStore = this.fIdentityConstraint2ValueStoreMap.get(toHash);
                        if (keyValueStore == null) {
                            keyValueStore = new KeyValueStore(key);
                            this.fIdentityConstraint2ValueStoreMap.put(toHash, keyValueStore);
                        }
                        else {
                            keyValueStore.clear();
                        }
                        this.fValueStores.addElement(keyValueStore);
                        break;
                    }
                    case 2: {
                        final KeyRef keyRef = (KeyRef)icArray[i];
                        final LocalIDKey toHash = new LocalIDKey(keyRef, XMLSchemaValidator.this.fElementDepth);
                        KeyRefValueStore keyRefValueStore = this.fIdentityConstraint2ValueStoreMap.get(toHash);
                        if (keyRefValueStore == null) {
                            keyRefValueStore = new KeyRefValueStore(keyRef, null);
                            this.fIdentityConstraint2ValueStoreMap.put(toHash, keyRefValueStore);
                        }
                        else {
                            keyRefValueStore.clear();
                        }
                        this.fValueStores.addElement(keyRefValueStore);
                        break;
                    }
                }
            }
        }
        
        public ValueStoreBase getValueStoreFor(final IdentityConstraint id, final int initialDepth) {
            final ValueStoreBase vb = this.fIdentityConstraint2ValueStoreMap.get(new LocalIDKey(id, initialDepth));
            return vb;
        }
        
        public ValueStoreBase getGlobalValueStoreFor(final IdentityConstraint id) {
            return this.fGlobalIDConstraintMap.get(id);
        }
        
        public void transplant(final IdentityConstraint id, final int initialDepth) {
            final ValueStoreBase newVals = this.fIdentityConstraint2ValueStoreMap.get(new LocalIDKey(id, initialDepth));
            if (id.getCategory() == 2) {
                return;
            }
            final ValueStoreBase currVals = this.fGlobalIDConstraintMap.get(id);
            if (currVals != null) {
                currVals.append(newVals);
                this.fGlobalIDConstraintMap.put(id, currVals);
            }
            else {
                this.fGlobalIDConstraintMap.put(id, newVals);
            }
        }
        
        public void endDocument() {
            for (int count = this.fValueStores.size(), i = 0; i < count; ++i) {
                final ValueStoreBase valueStore = this.fValueStores.elementAt(i);
                valueStore.endDocument();
            }
        }
        
        public String toString() {
            final String s = super.toString();
            final int index1 = s.lastIndexOf(36);
            if (index1 != -1) {
                return s.substring(index1 + 1);
            }
            final int index2 = s.lastIndexOf(46);
            if (index2 != -1) {
                return s.substring(index2 + 1);
            }
            return s;
        }
    }
    
    static final class OrderedHashtable implements Cloneable
    {
        private int fSize;
        private Entry[] fEntries;
        
        OrderedHashtable() {
            this.fEntries = null;
        }
        
        public int size() {
            return this.fSize;
        }
        
        public void put(final Field key, final IDValue value) {
            int index = this.indexOf(key);
            if (index == -1) {
                this.ensureCapacity(this.fSize);
                index = this.fSize++;
                this.fEntries[index].key = key;
            }
            this.fEntries[index].value = value;
        }
        
        public IDValue get(final Field key) {
            return this.fEntries[this.indexOf(key)].value;
        }
        
        public int indexOf(final Field key) {
            for (int i = 0; i < this.fSize; ++i) {
                if (this.fEntries[i].key == key) {
                    return i;
                }
            }
            return -1;
        }
        
        public Field keyAt(final int index) {
            return this.fEntries[index].key;
        }
        
        public IDValue valueAt(final int index) {
            return this.fEntries[index].value;
        }
        
        public void clear() {
            this.fSize = 0;
        }
        
        private void ensureCapacity(final int size) {
            int osize = -1;
            int nsize = -1;
            if (this.fEntries == null) {
                osize = 0;
                nsize = 2;
                this.fEntries = new Entry[nsize];
            }
            else if (this.fEntries.length <= size) {
                osize = this.fEntries.length;
                nsize = 2 * osize;
                final Entry[] array = new Entry[nsize];
                System.arraycopy(this.fEntries, 0, array, 0, osize);
                this.fEntries = array;
            }
            for (int i = osize; i < nsize; ++i) {
                this.fEntries[i] = new Entry();
            }
        }
        
        public Object clone() {
            final OrderedHashtable hashtable = new OrderedHashtable();
            for (int i = 0; i < this.fSize; ++i) {
                hashtable.put(this.fEntries[i].key, this.fEntries[i].value);
            }
            return hashtable;
        }
        
        public String toString() {
            if (this.fSize == 0) {
                return "[]";
            }
            final StringBuffer str = new StringBuffer();
            str.append('[');
            for (int i = 0; i < this.fSize; ++i) {
                if (i > 0) {
                    str.append(',');
                }
                str.append('{');
                str.append(this.fEntries[i].key);
                str.append(',');
                str.append(this.fEntries[i].value);
                str.append('}');
            }
            str.append(']');
            return str.toString();
        }
        
        public static final class Entry
        {
            public Field key;
            public IDValue value;
        }
    }
    
    protected class LocalIDKey
    {
        private IdentityConstraint fId;
        private int fDepth;
        
        public LocalIDKey(final IdentityConstraint id, final int depth) {
            this.fId = id;
            this.fDepth = depth;
        }
        
        public int hashCode() {
            return this.fId.hashCode() + this.fDepth;
        }
        
        public boolean equals(final Object localIDKey) {
            if (localIDKey instanceof LocalIDKey) {
                final LocalIDKey lIDKey = (LocalIDKey)localIDKey;
                return lIDKey.fId == this.fId && lIDKey.fDepth == this.fDepth;
            }
            return false;
        }
    }
}
