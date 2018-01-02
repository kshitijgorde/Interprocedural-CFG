// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.util.IntStack;
import org.apache.xerces.impl.xs.identity.KeyRef;
import org.apache.xerces.impl.xs.identity.UniqueOrKey;
import java.util.Enumeration;
import java.util.Stack;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import java.io.IOException;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.grammars.Grammar;
import java.util.Vector;
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
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.models.XSCMValidator;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.impl.xs.models.CMNodeFactory;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import java.util.Hashtable;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.impl.validation.ValidationState;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XMLString;
import java.util.HashMap;
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
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
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
    protected final HashMap fMayMatchFieldMap;
    protected XMLString fDefaultValue;
    protected boolean fDynamicValidation;
    protected boolean fSchemaDynamicValidation;
    protected boolean fDoValidation;
    protected boolean fFullChecking;
    protected boolean fNormalizeData;
    protected boolean fSchemaElementDefault;
    protected boolean fAugPSVI;
    protected boolean fIdConstraint;
    protected boolean fUseGrammarPoolOnly;
    private String fSchemaType;
    protected boolean fEntityRef;
    protected boolean fInCDATA;
    protected SymbolTable fSymbolTable;
    private XMLLocator fLocator;
    protected final XSIErrorReporter fXSIErrorReporter;
    protected XMLEntityResolver fEntityResolver;
    protected ValidationManager fValidationManager;
    protected ValidationState fValidationState;
    protected XMLGrammarPool fGrammarPool;
    protected String fExternalSchemas;
    protected String fExternalNoNamespaceSchema;
    protected Object fJaxpSchemaSource;
    protected final XSDDescription fXSDDescription;
    protected final Hashtable fLocationPairs;
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
    private final XSGrammarBucket fGrammarBucket;
    private final SubstitutionGroupHandler fSubGroupHandler;
    private final XSSimpleType fQNameDV;
    private final CMNodeFactory nodeFactory;
    private final CMBuilder fCMBuilder;
    private final XMLSchemaLoader fSchemaLoader;
    private String fValidationRoot;
    private int fSkipValidationDepth;
    private int fNFullValidationDepth;
    private int fNNoneValidationDepth;
    private int fElementDepth;
    private boolean fSubElement;
    private boolean[] fSubElementStack;
    private XSElementDecl fCurrentElemDecl;
    private XSElementDecl[] fElemDeclStack;
    private boolean fNil;
    private boolean[] fNilStack;
    private XSNotationDecl fNotation;
    private XSNotationDecl[] fNotationStack;
    private XSTypeDefinition fCurrentType;
    private XSTypeDefinition[] fTypeStack;
    private XSCMValidator fCurrentCM;
    private XSCMValidator[] fCMStack;
    private int[] fCurrCMState;
    private int[][] fCMStateStack;
    private boolean fStrictAssess;
    private boolean[] fStrictAssessStack;
    private final StringBuffer fBuffer;
    private boolean fAppendBuffer;
    private boolean fSawText;
    private boolean[] fSawTextStack;
    private boolean fSawCharacters;
    private boolean[] fStringContent;
    private final QName fTempQName;
    private ValidatedInfo fValidatedInfo;
    private ValidationState fState4XsiType;
    private ValidationState fState4ApplyDefault;
    protected XPathMatcherStack fMatcherStack;
    protected ValueStoreCache fValueStoreCache;
    
    public String[] getRecognizedFeatures() {
        return XMLSchemaValidator.RECOGNIZED_FEATURES.clone();
    }
    
    public void setFeature(final String s, final boolean b) throws XMLConfigurationException {
    }
    
    public String[] getRecognizedProperties() {
        return XMLSchemaValidator.RECOGNIZED_PROPERTIES.clone();
    }
    
    public void setProperty(final String s, final Object o) throws XMLConfigurationException {
    }
    
    public Boolean getFeatureDefault(final String s) {
        for (int i = 0; i < XMLSchemaValidator.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLSchemaValidator.RECOGNIZED_FEATURES[i].equals(s)) {
                return XMLSchemaValidator.FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public Object getPropertyDefault(final String s) {
        for (int i = 0; i < XMLSchemaValidator.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLSchemaValidator.RECOGNIZED_PROPERTIES[i].equals(s)) {
                return XMLSchemaValidator.PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public void setDocumentHandler(final XMLDocumentHandler fDocumentHandler) {
        this.fDocumentHandler = fDocumentHandler;
    }
    
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }
    
    public void setDocumentSource(final XMLDocumentSource fDocumentSource) {
        this.fDocumentSource = fDocumentSource;
    }
    
    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }
    
    public void startDocument(final XMLLocator fLocator, final String s, final NamespaceContext namespaceSupport, final Augmentations augmentations) throws XNIException {
        this.fValidationState.setNamespaceSupport(namespaceSupport);
        this.fState4XsiType.setNamespaceSupport(namespaceSupport);
        this.fState4ApplyDefault.setNamespaceSupport(namespaceSupport);
        this.handleStartDocument(this.fLocator = fLocator, s);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startDocument(fLocator, s, namespaceSupport, augmentations);
        }
    }
    
    public void xmlDecl(final String s, final String s2, final String s3, final Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.xmlDecl(s, s2, s3, augmentations);
        }
    }
    
    public void doctypeDecl(final String s, final String s2, final String s3, final Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.doctypeDecl(s, s2, s3, augmentations);
        }
    }
    
    public void startElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) throws XNIException {
        final Augmentations handleStartElement = this.handleStartElement(qName, xmlAttributes, augmentations);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startElement(qName, xmlAttributes, handleStartElement);
        }
    }
    
    public void emptyElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) throws XNIException {
        Augmentations augmentations2 = this.handleStartElement(qName, xmlAttributes, augmentations);
        this.fDefaultValue = null;
        if (this.fElementDepth != -2) {
            augmentations2 = this.handleEndElement(qName, augmentations2);
        }
        if (this.fDocumentHandler != null) {
            if (!this.fSchemaElementDefault || this.fDefaultValue == null) {
                this.fDocumentHandler.emptyElement(qName, xmlAttributes, augmentations2);
            }
            else {
                this.fDocumentHandler.startElement(qName, xmlAttributes, augmentations2);
                this.fDocumentHandler.characters(this.fDefaultValue, null);
                this.fDocumentHandler.endElement(qName, augmentations2);
            }
        }
    }
    
    public void characters(XMLString handleCharacters, final Augmentations augmentations) throws XNIException {
        handleCharacters = this.handleCharacters(handleCharacters);
        if (this.fDocumentHandler != null) {
            if (this.fNormalizeData && this.fUnionType) {
                if (augmentations != null) {
                    this.fDocumentHandler.characters(this.fEmptyXMLStr, augmentations);
                }
            }
            else {
                this.fDocumentHandler.characters(handleCharacters, augmentations);
            }
        }
    }
    
    public void ignorableWhitespace(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        this.handleIgnorableWhitespace(xmlString);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.ignorableWhitespace(xmlString, augmentations);
        }
    }
    
    public void endElement(final QName qName, final Augmentations augmentations) throws XNIException {
        this.fDefaultValue = null;
        final Augmentations handleEndElement = this.handleEndElement(qName, augmentations);
        if (this.fDocumentHandler != null) {
            if (!this.fSchemaElementDefault || this.fDefaultValue == null) {
                this.fDocumentHandler.endElement(qName, handleEndElement);
            }
            else {
                this.fDocumentHandler.characters(this.fDefaultValue, null);
                this.fDocumentHandler.endElement(qName, handleEndElement);
            }
        }
    }
    
    public void startCDATA(final Augmentations augmentations) throws XNIException {
        this.fInCDATA = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startCDATA(augmentations);
        }
    }
    
    public void endCDATA(final Augmentations augmentations) throws XNIException {
        this.fInCDATA = false;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endCDATA(augmentations);
        }
    }
    
    public void endDocument(final Augmentations augmentations) throws XNIException {
        this.handleEndDocument();
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endDocument(augmentations);
        }
        this.fLocator = null;
    }
    
    public boolean characterData(final String s, final Augmentations augmentations) {
        this.fSawText = (this.fSawText || s.length() > 0);
        if (this.fNormalizeData && this.fWhiteSpace != -1 && this.fWhiteSpace != 0) {
            this.normalizeWhitespace(s, this.fWhiteSpace == 2);
            this.fBuffer.append(this.fNormalizedStr.ch, this.fNormalizedStr.offset, this.fNormalizedStr.length);
        }
        else if (this.fAppendBuffer) {
            this.fBuffer.append(s);
        }
        boolean b = true;
        if (this.fCurrentType != null && this.fCurrentType.getTypeCategory() == 15 && ((XSComplexTypeDecl)this.fCurrentType).fContentType == 2) {
            for (int i = 0; i < s.length(); ++i) {
                if (!XMLChar.isSpace(s.charAt(i))) {
                    b = false;
                    this.fSawCharacters = true;
                    break;
                }
            }
        }
        return b;
    }
    
    public void elementDefault(final String s) {
    }
    
    public void startGeneralEntity(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final String s2, final Augmentations augmentations) throws XNIException {
        this.fEntityRef = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startGeneralEntity(s, xmlResourceIdentifier, s2, augmentations);
        }
    }
    
    public void textDecl(final String s, final String s2, final Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.textDecl(s, s2, augmentations);
        }
    }
    
    public void comment(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.comment(xmlString, augmentations);
        }
    }
    
    public void processingInstruction(final String s, final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.processingInstruction(s, xmlString, augmentations);
        }
    }
    
    public void endGeneralEntity(final String s, final Augmentations augmentations) throws XNIException {
        this.fEntityRef = false;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endGeneralEntity(s, augmentations);
        }
    }
    
    public XMLSchemaValidator() {
        this.fCurrentPSVI = new ElementPSVImpl();
        this.fAugmentations = new AugmentationsImpl();
        this.fMayMatchFieldMap = new HashMap();
        this.fDynamicValidation = false;
        this.fSchemaDynamicValidation = false;
        this.fDoValidation = false;
        this.fFullChecking = false;
        this.fNormalizeData = true;
        this.fSchemaElementDefault = true;
        this.fAugPSVI = true;
        this.fIdConstraint = false;
        this.fUseGrammarPoolOnly = false;
        this.fSchemaType = null;
        this.fEntityRef = false;
        this.fInCDATA = false;
        this.fXSIErrorReporter = new XSIErrorReporter();
        this.fValidationManager = null;
        this.fValidationState = new ValidationState();
        this.fExternalSchemas = null;
        this.fExternalNoNamespaceSchema = null;
        this.fJaxpSchemaSource = null;
        this.fXSDDescription = new XSDDescription();
        this.fLocationPairs = new Hashtable();
        this.fEmptyXMLStr = new XMLString(null, 0, -1);
        this.fNormalizedStr = new XMLString();
        this.fFirstChunk = true;
        this.fTrailing = false;
        this.fWhiteSpace = -1;
        this.fUnionType = false;
        this.fGrammarBucket = new XSGrammarBucket();
        this.fSubGroupHandler = new SubstitutionGroupHandler(this.fGrammarBucket);
        this.fQNameDV = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl("QName");
        this.nodeFactory = new CMNodeFactory();
        this.fCMBuilder = new CMBuilder(this.nodeFactory);
        this.fSchemaLoader = new XMLSchemaLoader(this.fXSIErrorReporter.fErrorReporter, this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder);
        this.fSubElementStack = new boolean[8];
        this.fElemDeclStack = new XSElementDecl[8];
        this.fNilStack = new boolean[8];
        this.fNotationStack = new XSNotationDecl[8];
        this.fTypeStack = new XSTypeDefinition[8];
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
        this.fTempQName = new QName();
        this.fValidatedInfo = new ValidatedInfo();
        this.fState4XsiType = new ValidationState();
        this.fState4ApplyDefault = new ValidationState();
        this.fMatcherStack = new XPathMatcherStack();
        this.fValueStoreCache = new ValueStoreCache();
        this.fState4XsiType.setExtraChecking(false);
        this.fState4ApplyDefault.setFacetChecking(false);
    }
    
    public void reset(final XMLComponentManager xmlComponentManager) throws XMLConfigurationException {
        this.fIdConstraint = false;
        this.fLocationPairs.clear();
        this.fValidationState.resetIDTables();
        this.nodeFactory.reset(xmlComponentManager);
        this.fSchemaLoader.reset(xmlComponentManager);
        this.fCurrentElemDecl = null;
        this.fCurrentCM = null;
        this.fCurrCMState = null;
        this.fSkipValidationDepth = -1;
        this.fNFullValidationDepth = -1;
        this.fNNoneValidationDepth = -1;
        this.fElementDepth = -1;
        this.fSubElement = false;
        this.fSchemaDynamicValidation = false;
        this.fEntityRef = false;
        this.fInCDATA = false;
        this.fMatcherStack.clear();
        if (!this.fMayMatchFieldMap.isEmpty()) {
            this.fMayMatchFieldMap.clear();
        }
        this.fXSIErrorReporter.reset((XMLErrorReporter)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter"));
        boolean feature;
        try {
            feature = xmlComponentManager.getFeature("http://apache.org/xml/features/internal/parser-settings");
        }
        catch (XMLConfigurationException ex) {
            feature = true;
        }
        if (!feature) {
            this.fValidationManager.addValidationState(this.fValidationState);
            XMLSchemaLoader.processExternalHints(this.fExternalSchemas, this.fExternalNoNamespaceSchema, this.fLocationPairs, this.fXSIErrorReporter.fErrorReporter);
            return;
        }
        final SymbolTable symbolTable = (SymbolTable)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        if (symbolTable != this.fSymbolTable) {
            this.fSymbolTable = symbolTable;
        }
        try {
            this.fDynamicValidation = xmlComponentManager.getFeature("http://apache.org/xml/features/validation/dynamic");
        }
        catch (XMLConfigurationException ex2) {
            this.fDynamicValidation = false;
        }
        if (this.fDynamicValidation) {
            this.fDoValidation = true;
        }
        else {
            try {
                this.fDoValidation = xmlComponentManager.getFeature("http://xml.org/sax/features/validation");
            }
            catch (XMLConfigurationException ex3) {
                this.fDoValidation = false;
            }
        }
        if (this.fDoValidation) {
            try {
                this.fDoValidation = xmlComponentManager.getFeature("http://apache.org/xml/features/validation/schema");
            }
            catch (XMLConfigurationException ex4) {}
        }
        try {
            this.fFullChecking = xmlComponentManager.getFeature("http://apache.org/xml/features/validation/schema-full-checking");
        }
        catch (XMLConfigurationException ex5) {
            this.fFullChecking = false;
        }
        try {
            this.fNormalizeData = xmlComponentManager.getFeature("http://apache.org/xml/features/validation/schema/normalized-value");
        }
        catch (XMLConfigurationException ex6) {
            this.fNormalizeData = false;
        }
        try {
            this.fSchemaElementDefault = xmlComponentManager.getFeature("http://apache.org/xml/features/validation/schema/element-default");
        }
        catch (XMLConfigurationException ex7) {
            this.fSchemaElementDefault = false;
        }
        try {
            this.fAugPSVI = xmlComponentManager.getFeature("http://apache.org/xml/features/validation/schema/augment-psvi");
        }
        catch (XMLConfigurationException ex8) {
            this.fAugPSVI = true;
        }
        try {
            this.fSchemaType = (String)xmlComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
        }
        catch (XMLConfigurationException ex9) {
            this.fSchemaType = null;
        }
        try {
            this.fUseGrammarPoolOnly = xmlComponentManager.getFeature("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only");
        }
        catch (XMLConfigurationException ex10) {
            this.fUseGrammarPoolOnly = false;
        }
        this.fEntityResolver = (XMLEntityResolver)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
        (this.fValidationManager = (ValidationManager)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager")).addValidationState(this.fValidationState);
        this.fValidationState.setSymbolTable(this.fSymbolTable);
        try {
            this.fExternalSchemas = (String)xmlComponentManager.getProperty("http://apache.org/xml/properties/schema/external-schemaLocation");
            this.fExternalNoNamespaceSchema = (String)xmlComponentManager.getProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation");
        }
        catch (XMLConfigurationException ex11) {
            this.fExternalSchemas = null;
            this.fExternalNoNamespaceSchema = null;
        }
        XMLSchemaLoader.processExternalHints(this.fExternalSchemas, this.fExternalNoNamespaceSchema, this.fLocationPairs, this.fXSIErrorReporter.fErrorReporter);
        try {
            this.fJaxpSchemaSource = xmlComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource");
        }
        catch (XMLConfigurationException ex12) {
            this.fJaxpSchemaSource = null;
        }
        try {
            this.fGrammarPool = (XMLGrammarPool)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        }
        catch (XMLConfigurationException ex13) {
            this.fGrammarPool = null;
        }
        this.fState4XsiType.setSymbolTable(symbolTable);
        this.fState4ApplyDefault.setSymbolTable(symbolTable);
    }
    
    public void startValueScopeFor(final IdentityConstraint identityConstraint, final int n) {
        this.fValueStoreCache.getValueStoreFor(identityConstraint, n).startValueScope();
    }
    
    public XPathMatcher activateField(final Field field, final int n) {
        final ValueStoreBase valueStore = this.fValueStoreCache.getValueStoreFor(field.getIdentityConstraint(), n);
        this.setMayMatch(field, Boolean.TRUE);
        final XPathMatcher matcher = field.createMatcher(this, valueStore);
        this.fMatcherStack.addMatcher(matcher);
        matcher.startDocumentFragment();
        return matcher;
    }
    
    public void endValueScopeFor(final IdentityConstraint identityConstraint, final int n) {
        this.fValueStoreCache.getValueStoreFor(identityConstraint, n).endValueScope();
    }
    
    public void setMayMatch(final Field field, final Boolean b) {
        this.fMayMatchFieldMap.put(field, b);
    }
    
    public Boolean mayMatch(final Field field) {
        return this.fMayMatchFieldMap.get(field);
    }
    
    private void activateSelectorFor(final IdentityConstraint identityConstraint) {
        final Selector selector = identityConstraint.getSelector();
        if (selector == null) {
            return;
        }
        final XPathMatcher matcher = selector.createMatcher(this, this.fElementDepth);
        this.fMatcherStack.addMatcher(matcher);
        matcher.startDocumentFragment();
    }
    
    void ensureStackCapacity() {
        if (this.fElementDepth == this.fElemDeclStack.length) {
            final int n = this.fElementDepth + 8;
            final boolean[] fSubElementStack = new boolean[n];
            System.arraycopy(this.fSubElementStack, 0, fSubElementStack, 0, this.fElementDepth);
            this.fSubElementStack = fSubElementStack;
            final XSElementDecl[] fElemDeclStack = new XSElementDecl[n];
            System.arraycopy(this.fElemDeclStack, 0, fElemDeclStack, 0, this.fElementDepth);
            this.fElemDeclStack = fElemDeclStack;
            final boolean[] fNilStack = new boolean[n];
            System.arraycopy(this.fNilStack, 0, fNilStack, 0, this.fElementDepth);
            this.fNilStack = fNilStack;
            final XSNotationDecl[] fNotationStack = new XSNotationDecl[n];
            System.arraycopy(this.fNotationStack, 0, fNotationStack, 0, this.fElementDepth);
            this.fNotationStack = fNotationStack;
            final XSTypeDefinition[] fTypeStack = new XSTypeDefinition[n];
            System.arraycopy(this.fTypeStack, 0, fTypeStack, 0, this.fElementDepth);
            this.fTypeStack = fTypeStack;
            final XSCMValidator[] fcmStack = new XSCMValidator[n];
            System.arraycopy(this.fCMStack, 0, fcmStack, 0, this.fElementDepth);
            this.fCMStack = fcmStack;
            final boolean[] fSawTextStack = new boolean[n];
            System.arraycopy(this.fSawTextStack, 0, fSawTextStack, 0, this.fElementDepth);
            this.fSawTextStack = fSawTextStack;
            final boolean[] fStringContent = new boolean[n];
            System.arraycopy(this.fStringContent, 0, fStringContent, 0, this.fElementDepth);
            this.fStringContent = fStringContent;
            final boolean[] fStrictAssessStack = new boolean[n];
            System.arraycopy(this.fStrictAssessStack, 0, fStrictAssessStack, 0, this.fElementDepth);
            this.fStrictAssessStack = fStrictAssessStack;
            final int[][] fcmStateStack = new int[n][];
            System.arraycopy(this.fCMStateStack, 0, fcmStateStack, 0, this.fElementDepth);
            this.fCMStateStack = fcmStateStack;
        }
    }
    
    void handleStartDocument(final XMLLocator xmlLocator, final String s) {
        this.fValueStoreCache.startDocument();
        if (this.fAugPSVI) {
            this.fCurrentPSVI.fGrammars = null;
            this.fCurrentPSVI.fSchemaInformation = null;
        }
    }
    
    void handleEndDocument() {
        this.fValueStoreCache.endDocument();
    }
    
    XMLString handleCharacters(XMLString fNormalizedStr) {
        if (this.fSkipValidationDepth >= 0) {
            return fNormalizedStr;
        }
        this.fSawText = (this.fSawText || fNormalizedStr.length > 0);
        if (this.fNormalizeData && this.fWhiteSpace != -1 && this.fWhiteSpace != 0) {
            this.normalizeWhitespace(fNormalizedStr, this.fWhiteSpace == 2);
            fNormalizedStr = this.fNormalizedStr;
        }
        if (this.fAppendBuffer) {
            this.fBuffer.append(fNormalizedStr.ch, fNormalizedStr.offset, fNormalizedStr.length);
        }
        if (this.fCurrentType != null && this.fCurrentType.getTypeCategory() == 15 && ((XSComplexTypeDecl)this.fCurrentType).fContentType == 2) {
            for (int i = fNormalizedStr.offset; i < fNormalizedStr.offset + fNormalizedStr.length; ++i) {
                if (!XMLChar.isSpace(fNormalizedStr.ch[i])) {
                    this.fSawCharacters = true;
                    break;
                }
            }
        }
        return fNormalizedStr;
    }
    
    private void normalizeWhitespace(final XMLString xmlString, final boolean b) {
        int n = b ? 1 : 0;
        int n2 = 0;
        boolean b2 = false;
        boolean fTrailing = false;
        final int n3 = xmlString.offset + xmlString.length;
        if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < xmlString.length + 1) {
            this.fNormalizedStr.ch = new char[xmlString.length + 1];
        }
        this.fNormalizedStr.offset = 1;
        this.fNormalizedStr.length = 1;
        for (int i = xmlString.offset; i < n3; ++i) {
            final char c = xmlString.ch[i];
            if (XMLChar.isSpace(c)) {
                if (n == 0) {
                    this.fNormalizedStr.ch[this.fNormalizedStr.length++] = ' ';
                    n = (b ? 1 : 0);
                }
                if (n2 == 0) {
                    b2 = true;
                }
            }
            else {
                this.fNormalizedStr.ch[this.fNormalizedStr.length++] = c;
                n = 0;
                n2 = 1;
            }
        }
        if (n != 0) {
            if (this.fNormalizedStr.length > 1) {
                final XMLString fNormalizedStr = this.fNormalizedStr;
                --fNormalizedStr.length;
                fTrailing = true;
            }
            else if (b2 && !this.fFirstChunk) {
                fTrailing = true;
            }
        }
        if (this.fNormalizedStr.length > 1 && !this.fFirstChunk && this.fWhiteSpace == 2) {
            if (this.fTrailing) {
                this.fNormalizedStr.offset = 0;
                this.fNormalizedStr.ch[0] = ' ';
            }
            else if (b2) {
                this.fNormalizedStr.offset = 0;
                this.fNormalizedStr.ch[0] = ' ';
            }
        }
        final XMLString fNormalizedStr2 = this.fNormalizedStr;
        fNormalizedStr2.length -= this.fNormalizedStr.offset;
        if ((this.fTrailing = fTrailing) || n2 != 0) {
            this.fFirstChunk = false;
        }
    }
    
    private void normalizeWhitespace(final String s, final boolean b) {
        int n = b ? 1 : 0;
        final int length = s.length();
        if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < length) {
            this.fNormalizedStr.ch = new char[length];
        }
        this.fNormalizedStr.offset = 0;
        this.fNormalizedStr.length = 0;
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (XMLChar.isSpace(char1)) {
                if (n == 0) {
                    this.fNormalizedStr.ch[this.fNormalizedStr.length++] = ' ';
                    n = (b ? 1 : 0);
                }
            }
            else {
                this.fNormalizedStr.ch[this.fNormalizedStr.length++] = char1;
                n = 0;
            }
        }
        if (n != 0 && this.fNormalizedStr.length != 0) {
            final XMLString fNormalizedStr = this.fNormalizedStr;
            --fNormalizedStr.length;
        }
    }
    
    void handleIgnorableWhitespace(final XMLString xmlString) {
        if (this.fSkipValidationDepth >= 0) {
            return;
        }
    }
    
    Augmentations handleStartElement(final QName qName, final XMLAttributes xmlAttributes, Augmentations augmentations) {
        if (this.fElementDepth == -1 && this.fValidationManager.isGrammarFound() && this.fSchemaType == null) {
            this.fSchemaDynamicValidation = true;
        }
        this.storeLocations(xmlAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_SCHEMALOCATION), xmlAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION));
        if (this.fSkipValidationDepth >= 0) {
            ++this.fElementDepth;
            if (this.fAugPSVI) {
                augmentations = this.getEmptyAugs(augmentations);
            }
            return augmentations;
        }
        final SchemaGrammar schemaGrammar = this.findSchemaGrammar((short)5, qName.uri, null, qName, xmlAttributes);
        Object oneTransition = null;
        if (this.fCurrentCM != null) {
            oneTransition = this.fCurrentCM.oneTransition(qName, this.fCurrCMState, this.fSubGroupHandler);
            if (this.fCurrCMState[0] == -1) {
                final Vector whatCanGoHere;
                if (((XSComplexTypeDecl)this.fCurrentType).fParticle != null && (whatCanGoHere = this.fCurrentCM.whatCanGoHere(this.fCurrCMState)).size() > 0) {
                    this.reportSchemaError("cvc-complex-type.2.4.a", new Object[] { qName.rawname, this.expectedStr(whatCanGoHere) });
                }
                else {
                    this.reportSchemaError("cvc-complex-type.2.4.d", new Object[] { qName.rawname });
                }
            }
        }
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
        }
        ++this.fElementDepth;
        this.fCurrentElemDecl = null;
        XSWildcardDecl xsWildcardDecl = null;
        this.fCurrentType = null;
        this.fStrictAssess = true;
        this.fNil = false;
        this.fNotation = null;
        this.fBuffer.setLength(0);
        this.fSawText = false;
        this.fSawCharacters = false;
        if (oneTransition != null) {
            if (oneTransition instanceof XSElementDecl) {
                this.fCurrentElemDecl = (XSElementDecl)oneTransition;
            }
            else {
                xsWildcardDecl = (XSWildcardDecl)oneTransition;
            }
        }
        if (xsWildcardDecl != null && xsWildcardDecl.fProcessContents == 2) {
            this.fSkipValidationDepth = this.fElementDepth;
            if (this.fAugPSVI) {
                augmentations = this.getEmptyAugs(augmentations);
            }
            return augmentations;
        }
        if (this.fCurrentElemDecl == null && schemaGrammar != null) {
            this.fCurrentElemDecl = schemaGrammar.getGlobalElementDecl(qName.localpart);
        }
        if (this.fCurrentElemDecl != null) {
            this.fCurrentType = this.fCurrentElemDecl.fType;
        }
        final String value = xmlAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_TYPE);
        if (this.fCurrentType == null && value == null) {
            if (this.fElementDepth == 0) {
                if (this.fDynamicValidation || this.fSchemaDynamicValidation) {
                    if (this.fDocumentSource != null) {
                        this.fDocumentSource.setDocumentHandler(this.fDocumentHandler);
                        if (this.fDocumentHandler != null) {
                            this.fDocumentHandler.setDocumentSource(this.fDocumentSource);
                        }
                        this.fElementDepth = -2;
                        return augmentations;
                    }
                    this.fSkipValidationDepth = this.fElementDepth;
                    if (this.fAugPSVI) {
                        augmentations = this.getEmptyAugs(augmentations);
                    }
                    return augmentations;
                }
                else {
                    this.fXSIErrorReporter.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "cvc-elt.1", new Object[] { qName.rawname }, (short)1);
                }
            }
            else if (xsWildcardDecl != null && xsWildcardDecl.fProcessContents == 1) {
                this.reportSchemaError("cvc-complex-type.2.4.c", new Object[] { qName.rawname });
            }
            this.fCurrentType = SchemaGrammar.fAnyType;
            this.fStrictAssess = false;
            this.fNFullValidationDepth = this.fElementDepth;
            this.fAppendBuffer = false;
            this.fXSIErrorReporter.pushContext();
        }
        else {
            this.fXSIErrorReporter.pushContext();
            if (value != null) {
                final XSTypeDefinition fCurrentType = this.fCurrentType;
                this.fCurrentType = this.getAndCheckXsiType(qName, value, xmlAttributes);
                if (this.fCurrentType == null) {
                    if (fCurrentType == null) {
                        this.fCurrentType = SchemaGrammar.fAnyType;
                    }
                    else {
                        this.fCurrentType = fCurrentType;
                    }
                }
            }
            this.fNNoneValidationDepth = this.fElementDepth;
            if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == 2) {
                this.fAppendBuffer = true;
            }
            else if (this.fCurrentType.getTypeCategory() == 16) {
                this.fAppendBuffer = true;
            }
            else {
                this.fAppendBuffer = (((XSComplexTypeDecl)this.fCurrentType).fContentType == 1);
            }
        }
        if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getAbstract()) {
            this.reportSchemaError("cvc-elt.2", new Object[] { qName.rawname });
        }
        if (this.fElementDepth == 0) {
            this.fValidationRoot = qName.rawname;
        }
        if (this.fNormalizeData) {
            this.fFirstChunk = true;
            this.fTrailing = false;
            this.fUnionType = false;
            this.fWhiteSpace = -1;
        }
        if (this.fCurrentType.getTypeCategory() == 15) {
            final XSComplexTypeDecl xsComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
            if (xsComplexTypeDecl.getAbstract()) {
                this.reportSchemaError("cvc-type.2", new Object[] { qName.rawname });
            }
            if (this.fNormalizeData && xsComplexTypeDecl.fContentType == 1) {
                if (xsComplexTypeDecl.fXSSimpleType.getVariety() == 3) {
                    this.fUnionType = true;
                }
                else {
                    try {
                        this.fWhiteSpace = xsComplexTypeDecl.fXSSimpleType.getWhitespace();
                    }
                    catch (DatatypeException ex) {}
                }
            }
        }
        else if (this.fNormalizeData) {
            final XSSimpleType xsSimpleType = (XSSimpleType)this.fCurrentType;
            if (xsSimpleType.getVariety() == 3) {
                this.fUnionType = true;
            }
            else {
                try {
                    this.fWhiteSpace = xsSimpleType.getWhitespace();
                }
                catch (DatatypeException ex2) {}
            }
        }
        this.fCurrentCM = null;
        if (this.fCurrentType.getTypeCategory() == 15) {
            this.fCurrentCM = ((XSComplexTypeDecl)this.fCurrentType).getContentModel(this.fCMBuilder);
        }
        this.fCurrCMState = null;
        if (this.fCurrentCM != null) {
            this.fCurrCMState = this.fCurrentCM.startContentModel();
        }
        final String value2 = xmlAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_NIL);
        if (value2 != null && this.fCurrentElemDecl != null) {
            this.fNil = this.getXsiNil(qName, value2);
        }
        XSAttributeGroupDecl attrGrp = null;
        if (this.fCurrentType.getTypeCategory() == 15) {
            attrGrp = ((XSComplexTypeDecl)this.fCurrentType).getAttrGrp();
        }
        this.fValueStoreCache.startElement();
        this.fMatcherStack.pushContext();
        if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.fIDCPos > 0) {
            this.fIdConstraint = true;
            this.fValueStoreCache.initValueStoresFor(this.fCurrentElemDecl, this);
        }
        this.processAttributes(qName, xmlAttributes, attrGrp);
        if (attrGrp != null) {
            this.addDefaultAttributes(qName, xmlAttributes, attrGrp);
        }
        for (int matcherCount = this.fMatcherStack.getMatcherCount(), i = 0; i < matcherCount; ++i) {
            this.fMatcherStack.getMatcherAt(i).startElement(qName, xmlAttributes);
        }
        if (this.fAugPSVI) {
            augmentations = this.getEmptyAugs(augmentations);
            this.fCurrentPSVI.fValidationContext = this.fValidationRoot;
            this.fCurrentPSVI.fDeclaration = this.fCurrentElemDecl;
            this.fCurrentPSVI.fTypeDecl = this.fCurrentType;
            this.fCurrentPSVI.fNotation = this.fNotation;
        }
        return augmentations;
    }
    
    Augmentations handleEndElement(final QName qName, Augmentations augmentations) {
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
            }
            else {
                --this.fElementDepth;
            }
            if (this.fElementDepth == -1 && this.fFullChecking) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter);
            }
            if (this.fAugPSVI) {
                augmentations = this.getEmptyAugs(augmentations);
            }
            return augmentations;
        }
        this.processElementContent(qName);
        final int matcherCount = this.fMatcherStack.getMatcherCount();
        for (int i = matcherCount - 1; i >= 0; --i) {
            final XPathMatcher matcher = this.fMatcherStack.getMatcherAt(i);
            if (this.fCurrentElemDecl == null) {
                matcher.endElement(qName, null, false, this.fValidatedInfo.actualValue, this.fValidatedInfo.actualValueType, this.fValidatedInfo.itemValueTypes);
            }
            else {
                matcher.endElement(qName, this.fCurrentType, this.fCurrentElemDecl.getNillable(), (this.fDefaultValue == null) ? this.fValidatedInfo.actualValue : this.fCurrentElemDecl.fDefault.actualValue, (this.fDefaultValue == null) ? this.fValidatedInfo.actualValueType : this.fCurrentElemDecl.fDefault.actualValueType, (this.fDefaultValue == null) ? this.fValidatedInfo.itemValueTypes : this.fCurrentElemDecl.fDefault.itemValueTypes);
            }
        }
        if (this.fMatcherStack.size() > 0) {
            this.fMatcherStack.popContext();
        }
        final int matcherCount2 = this.fMatcherStack.getMatcherCount();
        for (int j = matcherCount - 1; j >= matcherCount2; --j) {
            final XPathMatcher matcher2 = this.fMatcherStack.getMatcherAt(j);
            if (matcher2 instanceof Selector.Matcher) {
                final Selector.Matcher matcher3 = (Selector.Matcher)matcher2;
                final IdentityConstraint identityConstraint;
                if ((identityConstraint = matcher3.getIdentityConstraint()) != null && identityConstraint.getCategory() != 2) {
                    this.fValueStoreCache.transplant(identityConstraint, matcher3.getInitialDepth());
                }
            }
        }
        for (int k = matcherCount - 1; k >= matcherCount2; --k) {
            final XPathMatcher matcher4 = this.fMatcherStack.getMatcherAt(k);
            if (matcher4 instanceof Selector.Matcher) {
                final Selector.Matcher matcher5 = (Selector.Matcher)matcher4;
                final IdentityConstraint identityConstraint2;
                if ((identityConstraint2 = matcher5.getIdentityConstraint()) != null && identityConstraint2.getCategory() == 2) {
                    final ValueStoreBase valueStore = this.fValueStoreCache.getValueStoreFor(identityConstraint2, matcher5.getInitialDepth());
                    if (valueStore != null) {
                        valueStore.endDocumentFragment();
                    }
                }
            }
        }
        this.fValueStoreCache.endElement();
        final SchemaGrammar[] array = null;
        if (this.fElementDepth == 0) {
            final String checkIDRefID = this.fValidationState.checkIDRefID();
            this.fValidationState.resetIDTables();
            if (checkIDRefID != null) {
                this.reportSchemaError("cvc-id.1", new Object[] { checkIDRefID });
            }
            if (this.fFullChecking) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter);
            }
            final SchemaGrammar[] grammars = this.fGrammarBucket.getGrammars();
            if (this.fGrammarPool != null) {
                this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", grammars);
            }
            augmentations = this.endElementPSVI(true, grammars, augmentations);
        }
        else {
            augmentations = this.endElementPSVI(false, array, augmentations);
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
            this.fWhiteSpace = -1;
            this.fAppendBuffer = false;
            this.fUnionType = false;
        }
        return augmentations;
    }
    
    final Augmentations endElementPSVI(final boolean b, final SchemaGrammar[] fGrammars, Augmentations emptyAugs) {
        if (this.fAugPSVI) {
            emptyAugs = this.getEmptyAugs(emptyAugs);
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
            this.fCurrentPSVI.fActualValue = this.fValidatedInfo.actualValue;
            this.fCurrentPSVI.fActualValueType = this.fValidatedInfo.actualValueType;
            this.fCurrentPSVI.fItemValueTypes = this.fValidatedInfo.itemValueTypes;
            if (this.fStrictAssess) {
                final String[] mergeContext = this.fXSIErrorReporter.mergeContext();
                this.fCurrentPSVI.fErrorCodes = mergeContext;
                this.fCurrentPSVI.fValidity = (short)((mergeContext == null) ? 2 : 1);
            }
            else {
                this.fCurrentPSVI.fValidity = 0;
                this.fXSIErrorReporter.popContext();
            }
            if (b) {
                this.fCurrentPSVI.fGrammars = fGrammars;
                this.fCurrentPSVI.fSchemaInformation = null;
            }
        }
        return emptyAugs;
    }
    
    Augmentations getEmptyAugs(Augmentations fAugmentations) {
        if (fAugmentations == null) {
            fAugmentations = this.fAugmentations;
            fAugmentations.removeAllItems();
        }
        fAugmentations.putItem("ELEMENT_PSVI", this.fCurrentPSVI);
        this.fCurrentPSVI.reset();
        return fAugmentations;
    }
    
    void storeLocations(final String s, final String s2) {
        if (s != null && !XMLSchemaLoader.tokenizeSchemaLocationStr(s, this.fLocationPairs)) {
            this.fXSIErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "SchemaLocation", new Object[] { s }, (short)0);
        }
        if (s2 != null) {
            XMLSchemaLoader.LocationArray locationArray = this.fLocationPairs.get(XMLSymbols.EMPTY_STRING);
            if (locationArray == null) {
                locationArray = new XMLSchemaLoader.LocationArray();
                this.fLocationPairs.put(XMLSymbols.EMPTY_STRING, locationArray);
            }
            locationArray.addLocation(s2);
        }
    }
    
    SchemaGrammar findSchemaGrammar(final short fContextType, final String namespace, final QName fEnclosedElementName, final QName fTriggeringComponent, final XMLAttributes fAttributes) {
        SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(namespace);
        if (schemaGrammar == null) {
            this.fXSDDescription.reset();
            this.fXSDDescription.fContextType = fContextType;
            this.fXSDDescription.setNamespace(namespace);
            this.fXSDDescription.fEnclosedElementName = fEnclosedElementName;
            this.fXSDDescription.fTriggeringComponent = fTriggeringComponent;
            this.fXSDDescription.fAttributes = fAttributes;
            if (this.fLocator != null) {
                this.fXSDDescription.setBaseSystemId(this.fLocator.getExpandedSystemId());
            }
            Object locationArray = null;
            final XMLSchemaLoader.LocationArray value = this.fLocationPairs.get((namespace == null) ? XMLSymbols.EMPTY_STRING : namespace);
            if (value != null) {
                locationArray = value.getLocationArray();
            }
            if (locationArray != null && locationArray.length != 0) {
                System.arraycopy(locationArray, 0, this.fXSDDescription.fLocationHints = new String[locationArray.length], 0, locationArray.length);
            }
            if (this.fGrammarPool != null) {
                schemaGrammar = (SchemaGrammar)this.fGrammarPool.retrieveGrammar(this.fXSDDescription);
                if (schemaGrammar != null && !this.fGrammarBucket.putGrammar(schemaGrammar, true)) {
                    this.fXSIErrorReporter.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "GrammarConflict", null, (short)0);
                    schemaGrammar = null;
                }
            }
            if (schemaGrammar == null && !this.fUseGrammarPoolOnly) {
                try {
                    schemaGrammar = this.fSchemaLoader.loadSchema(this.fXSDDescription, XMLSchemaLoader.resolveDocument(this.fXSDDescription, this.fLocationPairs, this.fEntityResolver), this.fLocationPairs);
                }
                catch (IOException ex) {
                    this.fXSIErrorReporter.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "schema_reference.4", new Object[] { this.fXSDDescription.getLocationHints()[0] }, (short)0);
                }
            }
        }
        return schemaGrammar;
    }
    
    XSTypeDefinition getAndCheckXsiType(final QName qName, final String s, final XMLAttributes xmlAttributes) {
        QName qName2;
        try {
            qName2 = (QName)this.fQNameDV.validate(s, this.fValidationState, null);
        }
        catch (InvalidDatatypeValueException ex) {
            this.reportSchemaError(ex.getKey(), ex.getArgs());
            this.reportSchemaError("cvc-elt.4.1", new Object[] { qName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_TYPE, s });
            return null;
        }
        XSTypeDefinition xsTypeDefinition = null;
        if (qName2.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
            xsTypeDefinition = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(qName2.localpart);
        }
        if (xsTypeDefinition == null) {
            final SchemaGrammar schemaGrammar = this.findSchemaGrammar((short)7, qName2.uri, qName, qName2, xmlAttributes);
            if (schemaGrammar != null) {
                xsTypeDefinition = schemaGrammar.getGlobalTypeDecl(qName2.localpart);
            }
        }
        if (xsTypeDefinition == null) {
            this.reportSchemaError("cvc-elt.4.2", new Object[] { qName.rawname, s });
            return null;
        }
        if (this.fCurrentType != null) {
            short fBlock = this.fCurrentElemDecl.fBlock;
            if (this.fCurrentType.getTypeCategory() == 15) {
                fBlock |= ((XSComplexTypeDecl)this.fCurrentType).fBlock;
            }
            if (!XSConstraints.checkTypeDerivationOk(xsTypeDefinition, this.fCurrentType, fBlock)) {
                this.reportSchemaError("cvc-elt.4.3", new Object[] { qName.rawname, s, this.fCurrentType.getName() });
            }
        }
        return xsTypeDefinition;
    }
    
    boolean getXsiNil(final QName qName, final String s) {
        if (this.fCurrentElemDecl != null && !this.fCurrentElemDecl.getNillable()) {
            this.reportSchemaError("cvc-elt.3.1", new Object[] { qName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL });
        }
        else {
            final String trim = s.trim();
            if (trim.equals("true") || trim.equals("1")) {
                if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == 2) {
                    this.reportSchemaError("cvc-elt.3.2.2", new Object[] { qName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL });
                }
                return true;
            }
        }
        return false;
    }
    
    void processAttributes(final QName qName, final XMLAttributes xmlAttributes, final XSAttributeGroupDecl xsAttributeGroupDecl) {
        Object fName = null;
        final int length = xmlAttributes.getLength();
        AttributePSVImpl attributePSVImpl = null;
        final boolean b = this.fCurrentType == null || this.fCurrentType.getTypeCategory() == 16;
        XSObjectList attributeUses = null;
        int length2 = 0;
        XSWildcardDecl fAttributeWC = null;
        if (!b) {
            attributeUses = xsAttributeGroupDecl.getAttributeUses();
            length2 = attributeUses.getLength();
            fAttributeWC = xsAttributeGroupDecl.fAttributeWC;
        }
        for (int i = 0; i < length; ++i) {
            xmlAttributes.getName(i, this.fTempQName);
            if (this.fAugPSVI || this.fIdConstraint) {
                final Augmentations augmentations = xmlAttributes.getAugmentations(i);
                attributePSVImpl = (AttributePSVImpl)augmentations.getItem("ATTRIBUTE_PSVI");
                if (attributePSVImpl != null) {
                    attributePSVImpl.reset();
                }
                else {
                    attributePSVImpl = new AttributePSVImpl();
                    augmentations.putItem("ATTRIBUTE_PSVI", attributePSVImpl);
                }
                attributePSVImpl.fValidationContext = this.fValidationRoot;
            }
            if (this.fTempQName.uri == SchemaSymbols.URI_XSI) {
                XSAttributeDecl xsAttributeDecl = null;
                if (this.fTempQName.localpart == SchemaSymbols.XSI_SCHEMALOCATION) {
                    xsAttributeDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_SCHEMALOCATION);
                }
                else if (this.fTempQName.localpart == SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION) {
                    xsAttributeDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION);
                }
                else if (this.fTempQName.localpart == SchemaSymbols.XSI_NIL) {
                    xsAttributeDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NIL);
                }
                else if (this.fTempQName.localpart == SchemaSymbols.XSI_TYPE) {
                    xsAttributeDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_TYPE);
                }
                if (xsAttributeDecl != null) {
                    this.processOneAttribute(qName, xmlAttributes, i, xsAttributeDecl, null, attributePSVImpl);
                    continue;
                }
            }
            if (this.fTempQName.rawname != XMLSymbols.PREFIX_XMLNS) {
                if (!this.fTempQName.rawname.startsWith("xmlns:")) {
                    if (b) {
                        this.reportSchemaError("cvc-type.3.1.1", new Object[] { qName.rawname, this.fTempQName.rawname });
                    }
                    else {
                        XSAttributeUseImpl xsAttributeUseImpl = null;
                        for (int j = 0; j < length2; ++j) {
                            final XSAttributeUseImpl xsAttributeUseImpl2 = (XSAttributeUseImpl)attributeUses.item(j);
                            if (xsAttributeUseImpl2.fAttrDecl.fName == this.fTempQName.localpart && xsAttributeUseImpl2.fAttrDecl.fTargetNamespace == this.fTempQName.uri) {
                                xsAttributeUseImpl = xsAttributeUseImpl2;
                                break;
                            }
                        }
                        if (xsAttributeUseImpl == null && (fAttributeWC == null || !fAttributeWC.allowNamespace(this.fTempQName.uri))) {
                            this.reportSchemaError("cvc-complex-type.3.2.2", new Object[] { qName.rawname, this.fTempQName.rawname });
                        }
                        else {
                            XSAttributeDecl xsAttributeDecl2 = null;
                            if (xsAttributeUseImpl != null) {
                                xsAttributeDecl2 = xsAttributeUseImpl.fAttrDecl;
                            }
                            else {
                                if (fAttributeWC.fProcessContents == 2) {
                                    continue;
                                }
                                final SchemaGrammar schemaGrammar = this.findSchemaGrammar((short)6, this.fTempQName.uri, qName, this.fTempQName, xmlAttributes);
                                if (schemaGrammar != null) {
                                    xsAttributeDecl2 = schemaGrammar.getGlobalAttributeDecl(this.fTempQName.localpart);
                                }
                                if (xsAttributeDecl2 == null) {
                                    if (fAttributeWC.fProcessContents == 1) {
                                        this.reportSchemaError("cvc-complex-type.3.2.2", new Object[] { qName.rawname, this.fTempQName.rawname });
                                    }
                                    continue;
                                }
                                else if (xsAttributeDecl2.fType.getTypeCategory() == 16 && xsAttributeDecl2.fType.isIDType()) {
                                    if (fName != null) {
                                        this.reportSchemaError("cvc-complex-type.5.1", new Object[] { qName.rawname, xsAttributeDecl2.fName, fName });
                                    }
                                    else {
                                        fName = xsAttributeDecl2.fName;
                                    }
                                }
                            }
                            this.processOneAttribute(qName, xmlAttributes, i, xsAttributeDecl2, xsAttributeUseImpl, attributePSVImpl);
                        }
                    }
                }
            }
        }
        if (!b && xsAttributeGroupDecl.fIDAttrName != null && fName != null) {
            this.reportSchemaError("cvc-complex-type.5.2", new Object[] { qName.rawname, fName, xsAttributeGroupDecl.fIDAttrName });
        }
    }
    
    void processOneAttribute(final QName qName, final XMLAttributes xmlAttributes, final int n, final XSAttributeDecl fDeclaration, final XSAttributeUseImpl xsAttributeUseImpl, final AttributePSVImpl attributePSVImpl) {
        final String value = xmlAttributes.getValue(n);
        this.fXSIErrorReporter.pushContext();
        final XSSimpleType fType = fDeclaration.fType;
        Object validate = null;
        try {
            validate = fType.validate(value, this.fValidationState, this.fValidatedInfo);
            if (this.fNormalizeData) {
                xmlAttributes.setValue(n, this.fValidatedInfo.normalizedValue);
            }
            if (xmlAttributes instanceof XMLAttributesImpl) {
                ((XMLAttributesImpl)xmlAttributes).setSchemaId(n, (this.fValidatedInfo.memberType != null) ? this.fValidatedInfo.memberType.isIDType() : fType.isIDType());
            }
            if (fType.getVariety() == 1 && fType.getPrimitiveKind() == 20) {
                final QName qName2 = (QName)validate;
                final SchemaGrammar grammar = this.fGrammarBucket.getGrammar(qName2.uri);
                if (grammar != null) {
                    this.fNotation = grammar.getGlobalNotationDecl(qName2.localpart);
                }
            }
        }
        catch (InvalidDatatypeValueException ex) {
            this.reportSchemaError(ex.getKey(), ex.getArgs());
            this.reportSchemaError("cvc-attribute.3", new Object[] { qName.rawname, this.fTempQName.rawname, value, fType.getName() });
        }
        if (validate != null && fDeclaration.getConstraintType() == 2 && (!this.isComparable(this.fValidatedInfo, fDeclaration.fDefault) || !validate.equals(fDeclaration.fDefault.actualValue))) {
            this.reportSchemaError("cvc-attribute.4", new Object[] { qName.rawname, this.fTempQName.rawname, value, fDeclaration.fDefault.stringValue() });
        }
        if (validate != null && xsAttributeUseImpl != null && xsAttributeUseImpl.fConstraintType == 2 && (!this.isComparable(this.fValidatedInfo, xsAttributeUseImpl.fDefault) || !validate.equals(xsAttributeUseImpl.fDefault.actualValue))) {
            this.reportSchemaError("cvc-complex-type.3.1", new Object[] { qName.rawname, this.fTempQName.rawname, value, xsAttributeUseImpl.fDefault.stringValue() });
        }
        if (this.fIdConstraint) {
            attributePSVImpl.fActualValue = validate;
        }
        if (this.fAugPSVI) {
            attributePSVImpl.fDeclaration = fDeclaration;
            attributePSVImpl.fTypeDecl = fType;
            attributePSVImpl.fMemberType = this.fValidatedInfo.memberType;
            attributePSVImpl.fNormalizedValue = this.fValidatedInfo.normalizedValue;
            attributePSVImpl.fActualValue = this.fValidatedInfo.actualValue;
            attributePSVImpl.fActualValueType = this.fValidatedInfo.actualValueType;
            attributePSVImpl.fItemValueTypes = this.fValidatedInfo.itemValueTypes;
            attributePSVImpl.fValidationAttempted = 2;
            final String[] mergeContext = this.fXSIErrorReporter.mergeContext();
            attributePSVImpl.fErrorCodes = mergeContext;
            attributePSVImpl.fValidity = (short)((mergeContext == null) ? 2 : 1);
        }
    }
    
    void addDefaultAttributes(final QName qName, final XMLAttributes xmlAttributes, final XSAttributeGroupDecl xsAttributeGroupDecl) {
        final XSObjectList attributeUses = xsAttributeGroupDecl.getAttributeUses();
        for (int length = attributeUses.getLength(), i = 0; i < length; ++i) {
            final XSAttributeUseImpl xsAttributeUseImpl = (XSAttributeUseImpl)attributeUses.item(i);
            final XSAttributeDecl fAttrDecl = xsAttributeUseImpl.fAttrDecl;
            short n = xsAttributeUseImpl.fConstraintType;
            ValidatedInfo validatedInfo = xsAttributeUseImpl.fDefault;
            if (n == 0) {
                n = fAttrDecl.getConstraintType();
                validatedInfo = fAttrDecl.fDefault;
            }
            final boolean b = xmlAttributes.getValue(fAttrDecl.fTargetNamespace, fAttrDecl.fName) != null;
            if (xsAttributeUseImpl.fUse == 1 && !b) {
                this.reportSchemaError("cvc-complex-type.4", new Object[] { qName.rawname, fAttrDecl.fName });
            }
            if (!b && n != 0) {
                final QName qName2 = new QName(null, fAttrDecl.fName, fAttrDecl.fName, fAttrDecl.fTargetNamespace);
                final String fNormalizedValue = (validatedInfo != null) ? validatedInfo.stringValue() : "";
                final int addAttribute = xmlAttributes.addAttribute(qName2, "CDATA", fNormalizedValue);
                if (xmlAttributes instanceof XMLAttributesImpl) {
                    ((XMLAttributesImpl)xmlAttributes).setSchemaId(addAttribute, (validatedInfo != null && validatedInfo.memberType != null) ? validatedInfo.memberType.isIDType() : fAttrDecl.fType.isIDType());
                }
                if (this.fAugPSVI) {
                    final Augmentations augmentations = xmlAttributes.getAugmentations(addAttribute);
                    final AttributePSVImpl attributePSVImpl = new AttributePSVImpl();
                    augmentations.putItem("ATTRIBUTE_PSVI", attributePSVImpl);
                    attributePSVImpl.fDeclaration = fAttrDecl;
                    attributePSVImpl.fTypeDecl = fAttrDecl.fType;
                    attributePSVImpl.fMemberType = validatedInfo.memberType;
                    attributePSVImpl.fNormalizedValue = fNormalizedValue;
                    attributePSVImpl.fActualValue = validatedInfo.actualValue;
                    attributePSVImpl.fActualValueType = validatedInfo.actualValueType;
                    attributePSVImpl.fItemValueTypes = validatedInfo.itemValueTypes;
                    attributePSVImpl.fValidationContext = this.fValidationRoot;
                    attributePSVImpl.fValidity = 2;
                    attributePSVImpl.fValidationAttempted = 2;
                    attributePSVImpl.fSpecified = true;
                }
            }
        }
    }
    
    void processElementContent(final QName qName) {
        if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.fDefault != null && !this.fSawText && !this.fSubElement && !this.fNil) {
            final String stringValue = this.fCurrentElemDecl.fDefault.stringValue();
            final int length = stringValue.length();
            if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < length) {
                this.fNormalizedStr.ch = new char[length];
            }
            stringValue.getChars(0, length, this.fNormalizedStr.ch, 0);
            this.fNormalizedStr.offset = 0;
            this.fNormalizedStr.length = length;
            this.fDefaultValue = this.fNormalizedStr;
        }
        this.fValidatedInfo.normalizedValue = null;
        if (this.fNil && (this.fSubElement || this.fSawText)) {
            this.reportSchemaError("cvc-elt.3.2.1", new Object[] { qName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL });
        }
        this.fValidatedInfo.reset();
        if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() != 0 && !this.fSubElement && !this.fSawText && !this.fNil) {
            if (this.fCurrentType != this.fCurrentElemDecl.fType && XSConstraints.ElementDefaultValidImmediate(this.fCurrentType, this.fCurrentElemDecl.fDefault.stringValue(), this.fState4XsiType, null) == null) {
                this.reportSchemaError("cvc-elt.5.1.1", new Object[] { qName.rawname, this.fCurrentType.getName(), this.fCurrentElemDecl.fDefault.stringValue() });
            }
            this.elementLocallyValidType(qName, this.fCurrentElemDecl.fDefault.stringValue());
        }
        else {
            final Object elementLocallyValidType = this.elementLocallyValidType(qName, this.fBuffer);
            if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == 2 && !this.fNil) {
                final String string = this.fBuffer.toString();
                if (this.fSubElement) {
                    this.reportSchemaError("cvc-elt.5.2.2.1", new Object[] { qName.rawname });
                }
                if (this.fCurrentType.getTypeCategory() == 15) {
                    final XSComplexTypeDecl xsComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
                    if (xsComplexTypeDecl.fContentType == 3) {
                        if (!this.fCurrentElemDecl.fDefault.normalizedValue.equals(string)) {
                            this.reportSchemaError("cvc-elt.5.2.2.2.1", new Object[] { qName.rawname, string, this.fCurrentElemDecl.fDefault.normalizedValue });
                        }
                    }
                    else if (xsComplexTypeDecl.fContentType == 1 && elementLocallyValidType != null && (!this.isComparable(this.fValidatedInfo, this.fCurrentElemDecl.fDefault) || !elementLocallyValidType.equals(this.fCurrentElemDecl.fDefault.actualValue))) {
                        this.reportSchemaError("cvc-elt.5.2.2.2.2", new Object[] { qName.rawname, string, this.fCurrentElemDecl.fDefault.stringValue() });
                    }
                }
                else if (this.fCurrentType.getTypeCategory() == 16 && elementLocallyValidType != null && (!this.isComparable(this.fValidatedInfo, this.fCurrentElemDecl.fDefault) || !elementLocallyValidType.equals(this.fCurrentElemDecl.fDefault.actualValue))) {
                    this.reportSchemaError("cvc-elt.5.2.2.2.2", new Object[] { qName.rawname, string, this.fCurrentElemDecl.fDefault.stringValue() });
                }
            }
        }
        if (this.fDefaultValue == null && this.fNormalizeData && this.fDocumentHandler != null && this.fUnionType) {
            String s = this.fValidatedInfo.normalizedValue;
            if (s == null) {
                s = this.fBuffer.toString();
            }
            final int length2 = s.length();
            if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < length2) {
                this.fNormalizedStr.ch = new char[length2];
            }
            s.getChars(0, length2, this.fNormalizedStr.ch, 0);
            this.fNormalizedStr.offset = 0;
            this.fNormalizedStr.length = length2;
            this.fDocumentHandler.characters(this.fNormalizedStr, null);
        }
    }
    
    Object elementLocallyValidType(final QName qName, final Object o) {
        if (this.fCurrentType == null) {
            return null;
        }
        Object o2 = null;
        if (this.fCurrentType.getTypeCategory() == 16) {
            if (this.fSubElement) {
                this.reportSchemaError("cvc-type.3.1.2", new Object[] { qName.rawname });
            }
            if (!this.fNil) {
                final XSSimpleType xsSimpleType = (XSSimpleType)this.fCurrentType;
                try {
                    if (!this.fNormalizeData || this.fUnionType) {
                        this.fValidationState.setNormalizationRequired(true);
                    }
                    o2 = xsSimpleType.validate(o, this.fValidationState, this.fValidatedInfo);
                }
                catch (InvalidDatatypeValueException ex) {
                    this.reportSchemaError(ex.getKey(), ex.getArgs());
                    this.reportSchemaError("cvc-type.3.1.3", new Object[] { qName.rawname, o });
                }
            }
        }
        else {
            o2 = this.elementLocallyValidComplexType(qName, o);
        }
        return o2;
    }
    
    Object elementLocallyValidComplexType(final QName qName, final Object o) {
        Object validate = null;
        final XSComplexTypeDecl xsComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
        if (!this.fNil) {
            if (xsComplexTypeDecl.fContentType == 0 && (this.fSubElement || this.fSawText)) {
                this.reportSchemaError("cvc-complex-type.2.1", new Object[] { qName.rawname });
            }
            else if (xsComplexTypeDecl.fContentType == 1) {
                if (this.fSubElement) {
                    this.reportSchemaError("cvc-complex-type.2.2", new Object[] { qName.rawname });
                }
                final XSSimpleType fxsSimpleType = xsComplexTypeDecl.fXSSimpleType;
                try {
                    if (!this.fNormalizeData || this.fUnionType) {
                        this.fValidationState.setNormalizationRequired(true);
                    }
                    validate = fxsSimpleType.validate(o, this.fValidationState, this.fValidatedInfo);
                }
                catch (InvalidDatatypeValueException ex) {
                    this.reportSchemaError(ex.getKey(), ex.getArgs());
                    this.reportSchemaError("cvc-complex-type.2.2", new Object[] { qName.rawname });
                }
            }
            else if (xsComplexTypeDecl.fContentType == 2 && this.fSawCharacters) {
                this.reportSchemaError("cvc-complex-type.2.3", new Object[] { qName.rawname });
            }
            if ((xsComplexTypeDecl.fContentType == 2 || xsComplexTypeDecl.fContentType == 3) && this.fCurrCMState[0] >= 0 && !this.fCurrentCM.endContentModel(this.fCurrCMState)) {
                this.reportSchemaError("cvc-complex-type.2.4.b", new Object[] { qName.rawname, this.expectedStr(this.fCurrentCM.whatCanGoHere(this.fCurrCMState)) });
            }
        }
        return validate;
    }
    
    void reportSchemaError(final String s, final Object[] array) {
        if (this.fDoValidation) {
            this.fXSIErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", s, array, (short)1);
        }
    }
    
    private boolean isComparable(final ValidatedInfo validatedInfo, final ValidatedInfo validatedInfo2) {
        final short convertToPrimitiveKind = this.convertToPrimitiveKind(validatedInfo.actualValueType);
        final short convertToPrimitiveKind2 = this.convertToPrimitiveKind(validatedInfo2.actualValueType);
        if (convertToPrimitiveKind != convertToPrimitiveKind2) {
            return (convertToPrimitiveKind == 1 && convertToPrimitiveKind2 == 2) || (convertToPrimitiveKind == 2 && convertToPrimitiveKind2 == 1);
        }
        if (convertToPrimitiveKind == 44 || convertToPrimitiveKind == 43) {
            final ShortList itemValueTypes = validatedInfo.itemValueTypes;
            final ShortList itemValueTypes2 = validatedInfo2.itemValueTypes;
            final int n = (itemValueTypes != null) ? itemValueTypes.getLength() : 0;
            if (n != ((itemValueTypes2 != null) ? itemValueTypes2.getLength() : 0)) {
                return false;
            }
            for (int i = 0; i < n; ++i) {
                final short convertToPrimitiveKind3 = this.convertToPrimitiveKind(itemValueTypes.item(i));
                final short convertToPrimitiveKind4 = this.convertToPrimitiveKind(itemValueTypes2.item(i));
                if (convertToPrimitiveKind3 != convertToPrimitiveKind4 && (convertToPrimitiveKind3 != 1 || convertToPrimitiveKind4 != 2) && (convertToPrimitiveKind3 != 2 || convertToPrimitiveKind4 != 1)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private short convertToPrimitiveKind(final short n) {
        if (n <= 20) {
            return n;
        }
        if (n <= 29) {
            return 2;
        }
        if (n <= 42) {
            return 4;
        }
        return n;
    }
    
    private String expectedStr(final Vector vector) {
        final StringBuffer sb = new StringBuffer("{");
        for (int size = vector.size(), i = 0; i < size; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(vector.elementAt(i).toString());
        }
        sb.append('}');
        return sb.toString();
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://apache.org/xml/features/validation/schema", "http://apache.org/xml/features/validation/dynamic", "http://apache.org/xml/features/validation/schema-full-checking", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/continue-after-fatal-error", "http://apache.org/xml/features/standard-uri-conformant", "http://apache.org/xml/features/generate-synthetic-annotations", "http://apache.org/xml/features/validate-annotations", "http://apache.org/xml/features/honour-all-schemaLocations", "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only" };
        FEATURE_DEFAULTS = new Boolean[] { null, null, null, null, null, null, null, null, null, null, null };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/validation-manager", "http://apache.org/xml/properties/schema/external-schemaLocation", "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage" };
        PROPERTY_DEFAULTS = new Object[] { null, null, null, null, null, null, null, null };
    }
    
    protected static final class ShortVector
    {
        private int fLength;
        private short[] fData;
        
        public ShortVector() {
        }
        
        public ShortVector(final int n) {
            this.fData = new short[n];
        }
        
        public int length() {
            return this.fLength;
        }
        
        public void add(final short n) {
            this.ensureCapacity(this.fLength + 1);
            this.fData[this.fLength++] = n;
        }
        
        public short valueAt(final int n) {
            return this.fData[n];
        }
        
        public void clear() {
            this.fLength = 0;
        }
        
        public boolean contains(final short n) {
            for (int i = 0; i < this.fLength; ++i) {
                if (this.fData[i] == n) {
                    return true;
                }
            }
            return false;
        }
        
        private void ensureCapacity(final int n) {
            if (this.fData == null) {
                this.fData = new short[8];
            }
            else if (this.fData.length <= n) {
                final short[] fData = new short[this.fData.length * 2];
                System.arraycopy(this.fData, 0, fData, 0, this.fData.length);
                this.fData = fData;
            }
        }
    }
    
    protected class LocalIDKey
    {
        public IdentityConstraint fId;
        public int fDepth;
        
        public LocalIDKey() {
        }
        
        public LocalIDKey(final IdentityConstraint fId, final int fDepth) {
            this.fId = fId;
            this.fDepth = fDepth;
        }
        
        public int hashCode() {
            return this.fId.hashCode() + this.fDepth;
        }
        
        public boolean equals(final Object o) {
            if (o instanceof LocalIDKey) {
                final LocalIDKey localIDKey = (LocalIDKey)o;
                return localIDKey.fId == this.fId && localIDKey.fDepth == this.fDepth;
            }
            return false;
        }
    }
    
    protected class ValueStoreCache
    {
        final LocalIDKey fLocalId;
        protected final Vector fValueStores;
        protected final Hashtable fIdentityConstraint2ValueStoreMap;
        protected final Stack fGlobalMapStack;
        protected final Hashtable fGlobalIDConstraintMap;
        
        public ValueStoreCache() {
            this.fLocalId = new LocalIDKey();
            this.fValueStores = new Vector();
            this.fIdentityConstraint2ValueStoreMap = new Hashtable();
            this.fGlobalMapStack = new Stack();
            this.fGlobalIDConstraintMap = new Hashtable();
        }
        
        public void startDocument() {
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
            final Hashtable hashtable = this.fGlobalMapStack.pop();
            if (hashtable == null) {
                return;
            }
            final Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final IdentityConstraint identityConstraint = keys.nextElement();
                final ValueStoreBase valueStoreBase = hashtable.get(identityConstraint);
                if (valueStoreBase != null) {
                    final ValueStoreBase valueStoreBase2 = this.fGlobalIDConstraintMap.get(identityConstraint);
                    if (valueStoreBase2 == null) {
                        this.fGlobalIDConstraintMap.put(identityConstraint, valueStoreBase);
                    }
                    else {
                        if (valueStoreBase2 == valueStoreBase) {
                            continue;
                        }
                        valueStoreBase2.append(valueStoreBase);
                    }
                }
            }
        }
        
        public void initValueStoresFor(final XSElementDecl xsElementDecl, final FieldActivator fieldActivator) {
            final IdentityConstraint[] fidConstraints = xsElementDecl.fIDConstraints;
            for (int fidcPos = xsElementDecl.fIDCPos, i = 0; i < fidcPos; ++i) {
                switch (fidConstraints[i].getCategory()) {
                    case 3: {
                        final UniqueOrKey uniqueOrKey = (UniqueOrKey)fidConstraints[i];
                        final LocalIDKey localIDKey = new LocalIDKey(uniqueOrKey, XMLSchemaValidator.this.fElementDepth);
                        UniqueValueStore uniqueValueStore = this.fIdentityConstraint2ValueStoreMap.get(localIDKey);
                        if (uniqueValueStore == null) {
                            uniqueValueStore = new UniqueValueStore(uniqueOrKey);
                            this.fIdentityConstraint2ValueStoreMap.put(localIDKey, uniqueValueStore);
                        }
                        else {
                            uniqueValueStore.clear();
                        }
                        this.fValueStores.addElement(uniqueValueStore);
                        XMLSchemaValidator.this.activateSelectorFor(fidConstraints[i]);
                        break;
                    }
                    case 1: {
                        final UniqueOrKey uniqueOrKey2 = (UniqueOrKey)fidConstraints[i];
                        final LocalIDKey localIDKey2 = new LocalIDKey(uniqueOrKey2, XMLSchemaValidator.this.fElementDepth);
                        KeyValueStore keyValueStore = this.fIdentityConstraint2ValueStoreMap.get(localIDKey2);
                        if (keyValueStore == null) {
                            keyValueStore = new KeyValueStore(uniqueOrKey2);
                            this.fIdentityConstraint2ValueStoreMap.put(localIDKey2, keyValueStore);
                        }
                        else {
                            keyValueStore.clear();
                        }
                        this.fValueStores.addElement(keyValueStore);
                        XMLSchemaValidator.this.activateSelectorFor(fidConstraints[i]);
                        break;
                    }
                    case 2: {
                        final KeyRef keyRef = (KeyRef)fidConstraints[i];
                        final LocalIDKey localIDKey3 = new LocalIDKey(keyRef, XMLSchemaValidator.this.fElementDepth);
                        KeyRefValueStore keyRefValueStore = this.fIdentityConstraint2ValueStoreMap.get(localIDKey3);
                        if (keyRefValueStore == null) {
                            keyRefValueStore = new KeyRefValueStore(keyRef, null);
                            this.fIdentityConstraint2ValueStoreMap.put(localIDKey3, keyRefValueStore);
                        }
                        else {
                            keyRefValueStore.clear();
                        }
                        this.fValueStores.addElement(keyRefValueStore);
                        XMLSchemaValidator.this.activateSelectorFor(fidConstraints[i]);
                        break;
                    }
                }
            }
        }
        
        public ValueStoreBase getValueStoreFor(final IdentityConstraint fId, final int fDepth) {
            this.fLocalId.fDepth = fDepth;
            this.fLocalId.fId = fId;
            return this.fIdentityConstraint2ValueStoreMap.get(this.fLocalId);
        }
        
        public ValueStoreBase getGlobalValueStoreFor(final IdentityConstraint identityConstraint) {
            return this.fGlobalIDConstraintMap.get(identityConstraint);
        }
        
        public void transplant(final IdentityConstraint fId, final int fDepth) {
            this.fLocalId.fDepth = fDepth;
            this.fLocalId.fId = fId;
            final ValueStoreBase valueStoreBase = this.fIdentityConstraint2ValueStoreMap.get(this.fLocalId);
            if (fId.getCategory() == 2) {
                return;
            }
            final ValueStoreBase valueStoreBase2 = this.fGlobalIDConstraintMap.get(fId);
            if (valueStoreBase2 != null) {
                valueStoreBase2.append(valueStoreBase);
                this.fGlobalIDConstraintMap.put(fId, valueStoreBase2);
            }
            else {
                this.fGlobalIDConstraintMap.put(fId, valueStoreBase);
            }
        }
        
        public void endDocument() {
            for (int size = this.fValueStores.size(), i = 0; i < size; ++i) {
                ((ValueStoreBase)this.fValueStores.elementAt(i)).endDocument();
            }
        }
        
        public String toString() {
            final String string = super.toString();
            final int lastIndex = string.lastIndexOf(36);
            if (lastIndex != -1) {
                return string.substring(lastIndex + 1);
            }
            final int lastIndex2 = string.lastIndexOf(46);
            if (lastIndex2 != -1) {
                return string.substring(lastIndex2 + 1);
            }
            return string;
        }
    }
    
    protected abstract class ValueStoreBase implements ValueStore
    {
        protected IdentityConstraint fIdentityConstraint;
        protected int fFieldCount;
        protected Field[] fFields;
        protected Object[] fLocalValues;
        protected short[] fLocalValueTypes;
        protected ShortList[] fLocalItemValueTypes;
        protected int fValuesCount;
        public final Vector fValues;
        public ShortVector fValueTypes;
        public Vector fItemValueTypes;
        private boolean fUseValueTypeVector;
        private int fValueTypesLength;
        private short fValueType;
        private boolean fUseItemValueTypeVector;
        private int fItemValueTypesLength;
        private ShortList fItemValueType;
        final StringBuffer fTempBuffer;
        
        protected ValueStoreBase(final IdentityConstraint fIdentityConstraint) {
            this.fFieldCount = 0;
            this.fFields = null;
            this.fLocalValues = null;
            this.fLocalValueTypes = null;
            this.fLocalItemValueTypes = null;
            this.fValues = new Vector();
            this.fValueTypes = null;
            this.fItemValueTypes = null;
            this.fUseValueTypeVector = false;
            this.fValueTypesLength = 0;
            this.fValueType = 0;
            this.fUseItemValueTypeVector = false;
            this.fItemValueTypesLength = 0;
            this.fItemValueType = null;
            this.fTempBuffer = new StringBuffer();
            this.fIdentityConstraint = fIdentityConstraint;
            this.fFieldCount = this.fIdentityConstraint.getFieldCount();
            this.fFields = new Field[this.fFieldCount];
            this.fLocalValues = new Object[this.fFieldCount];
            this.fLocalValueTypes = new short[this.fFieldCount];
            this.fLocalItemValueTypes = new ShortList[this.fFieldCount];
            for (int i = 0; i < this.fFieldCount; ++i) {
                this.fFields[i] = this.fIdentityConstraint.getFieldAt(i);
            }
        }
        
        public void clear() {
            this.fValuesCount = 0;
            this.fUseValueTypeVector = false;
            this.fValueTypesLength = 0;
            this.fValueType = 0;
            this.fUseItemValueTypeVector = false;
            this.fItemValueTypesLength = 0;
            this.fItemValueType = null;
            this.fValues.setSize(0);
            if (this.fValueTypes != null) {
                this.fValueTypes.clear();
            }
            if (this.fItemValueTypes != null) {
                this.fItemValueTypes.setSize(0);
            }
        }
        
        public void append(final ValueStoreBase valueStoreBase) {
            for (int i = 0; i < valueStoreBase.fValues.size(); ++i) {
                this.fValues.addElement(valueStoreBase.fValues.elementAt(i));
            }
        }
        
        public void startValueScope() {
            this.fValuesCount = 0;
            for (int i = 0; i < this.fFieldCount; ++i) {
                this.fLocalValues[i] = null;
                this.fLocalValueTypes[i] = 0;
                this.fLocalItemValueTypes[i] = null;
            }
        }
        
        public void endValueScope() {
            if (this.fValuesCount == 0) {
                if (this.fIdentityConstraint.getCategory() == 1) {
                    XMLSchemaValidator.this.reportSchemaError("AbsentKeyValue", new Object[] { this.fIdentityConstraint.getElementName() });
                }
                return;
            }
            if (this.fValuesCount != this.fFieldCount) {
                if (this.fIdentityConstraint.getCategory() == 1) {
                    XMLSchemaValidator.this.reportSchemaError("KeyNotEnoughValues", new Object[] { this.fIdentityConstraint.getElementName(), ((UniqueOrKey)this.fIdentityConstraint).getIdentityConstraintName() });
                }
            }
        }
        
        public void endDocumentFragment() {
        }
        
        public void endDocument() {
        }
        
        public void reportError(final String s, final Object[] array) {
            XMLSchemaValidator.this.reportSchemaError(s, array);
        }
        
        public void addValue(final Field field, final Object o, final short n, final ShortList list) {
            int n2;
            for (n2 = this.fFieldCount - 1; n2 > -1 && this.fFields[n2] != field; --n2) {}
            if (n2 == -1) {
                XMLSchemaValidator.this.reportSchemaError("UnknownField", new Object[] { field.toString() });
                return;
            }
            if (Boolean.TRUE != XMLSchemaValidator.this.mayMatch(field)) {
                XMLSchemaValidator.this.reportSchemaError("FieldMultipleMatch", new Object[] { field.toString() });
            }
            else {
                ++this.fValuesCount;
            }
            this.fLocalValues[n2] = o;
            this.fLocalValueTypes[n2] = n;
            this.fLocalItemValueTypes[n2] = list;
            if (this.fValuesCount == this.fFieldCount) {
                this.checkDuplicateValues();
                for (int i = 0; i < this.fFieldCount; ++i) {
                    this.fValues.addElement(this.fLocalValues[i]);
                    this.addValueType(this.fLocalValueTypes[i]);
                    this.addItemValueType(this.fLocalItemValueTypes[i]);
                }
            }
        }
        
        public boolean contains() {
            final int size = this.fValues.size();
            int i = 0;
        Label_0165:
            while (i < size) {
                final int n = i + this.fFieldCount;
                int j = 0;
                while (j < this.fFieldCount) {
                    final Object o = this.fLocalValues[j];
                    final Object element = this.fValues.elementAt(i);
                    final short n2 = this.fLocalValueTypes[j];
                    final short valueType = this.getValueTypeAt(i);
                    Label_0163: {
                        if (o != null && element != null && n2 == valueType) {
                            if (o.equals(element)) {
                                if (n2 == 44 || n2 == 43) {
                                    final ShortList list = this.fLocalItemValueTypes[j];
                                    final ShortList itemValueType = this.getItemValueTypeAt(i);
                                    if (list == null || itemValueType == null) {
                                        break Label_0163;
                                    }
                                    if (!list.equals(itemValueType)) {
                                        break Label_0163;
                                    }
                                }
                                ++i;
                                ++j;
                                continue;
                            }
                        }
                    }
                    i = n;
                    continue Label_0165;
                }
                return true;
            }
            return false;
        }
        
        public int contains(final ValueStoreBase valueStoreBase) {
            final Vector fValues = valueStoreBase.fValues;
            final int size = fValues.size();
            if (this.fFieldCount <= 1) {
                for (int i = 0; i < size; ++i) {
                    final short valueType = valueStoreBase.getValueTypeAt(i);
                    if (!this.valueTypeContains(valueType) || !this.fValues.contains(fValues.elementAt(i))) {
                        return i;
                    }
                    if ((valueType == 44 || valueType == 43) && !this.itemValueTypeContains(valueStoreBase.getItemValueTypeAt(i))) {
                        return i;
                    }
                }
            }
            else {
                final int size2 = this.fValues.size();
                int j = 0;
            Label_0314:
                while (j < size) {
                    int k = 0;
                Label_0295:
                    while (k < size2) {
                        int l = 0;
                        while (l < this.fFieldCount) {
                            final Object element = fValues.elementAt(j + l);
                            final Object element2 = this.fValues.elementAt(k + l);
                            final short valueType2 = valueStoreBase.getValueTypeAt(j + l);
                            final short valueType3 = this.getValueTypeAt(k + l);
                            Label_0286: {
                                if (element != element2) {
                                    if (valueType2 != valueType3 || element == null) {
                                        break Label_0286;
                                    }
                                    if (!element.equals(element2)) {
                                        break Label_0286;
                                    }
                                }
                                if (valueType2 == 44 || valueType2 == 43) {
                                    final ShortList itemValueType = valueStoreBase.getItemValueTypeAt(j + l);
                                    final ShortList itemValueType2 = this.getItemValueTypeAt(k + l);
                                    if (itemValueType == null || itemValueType2 == null) {
                                        break Label_0286;
                                    }
                                    if (!itemValueType.equals(itemValueType2)) {
                                        break Label_0286;
                                    }
                                }
                                ++l;
                                continue;
                            }
                            k += this.fFieldCount;
                            continue Label_0295;
                        }
                        j += this.fFieldCount;
                        continue Label_0314;
                    }
                    return j;
                }
            }
            return -1;
        }
        
        protected void checkDuplicateValues() {
        }
        
        protected String toString(final Object[] array) {
            final int length = array.length;
            if (length == 0) {
                return "";
            }
            this.fTempBuffer.setLength(0);
            for (int i = 0; i < length; ++i) {
                if (i > 0) {
                    this.fTempBuffer.append(',');
                }
                this.fTempBuffer.append(array[i]);
            }
            return this.fTempBuffer.toString();
        }
        
        protected String toString(final Vector vector, final int n, final int n2) {
            if (n2 == 0) {
                return "";
            }
            if (n2 == 1) {
                return String.valueOf(vector.elementAt(n));
            }
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < n2; ++i) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(vector.elementAt(n + i));
            }
            return sb.toString();
        }
        
        public String toString() {
            String s = super.toString();
            final int lastIndex = s.lastIndexOf(36);
            if (lastIndex != -1) {
                s = s.substring(lastIndex + 1);
            }
            final int lastIndex2 = s.lastIndexOf(46);
            if (lastIndex2 != -1) {
                s = s.substring(lastIndex2 + 1);
            }
            return s + '[' + this.fIdentityConstraint + ']';
        }
        
        private void addValueType(final short fValueType) {
            if (this.fUseValueTypeVector) {
                this.fValueTypes.add(fValueType);
            }
            else if (this.fValueTypesLength++ == 0) {
                this.fValueType = fValueType;
            }
            else if (this.fValueType != fValueType) {
                this.fUseValueTypeVector = true;
                if (this.fValueTypes == null) {
                    this.fValueTypes = new ShortVector(this.fValueTypesLength * 2);
                }
                for (int i = 1; i < this.fValueTypesLength; ++i) {
                    this.fValueTypes.add(this.fValueType);
                }
                this.fValueTypes.add(fValueType);
            }
        }
        
        private short getValueTypeAt(final int n) {
            if (this.fUseValueTypeVector) {
                return this.fValueTypes.valueAt(n);
            }
            return this.fValueType;
        }
        
        private boolean valueTypeContains(final short n) {
            if (this.fUseValueTypeVector) {
                return this.fValueTypes.contains(n);
            }
            return this.fValueType == n;
        }
        
        private void addItemValueType(final ShortList fItemValueType) {
            if (this.fUseItemValueTypeVector) {
                this.fItemValueTypes.add(fItemValueType);
            }
            else if (this.fItemValueTypesLength++ == 0) {
                this.fItemValueType = fItemValueType;
            }
            else if (this.fItemValueType != fItemValueType && (this.fItemValueType == null || !this.fItemValueType.equals(fItemValueType))) {
                this.fUseItemValueTypeVector = true;
                if (this.fItemValueTypes == null) {
                    this.fItemValueTypes = new Vector(this.fItemValueTypesLength * 2);
                }
                for (int i = 1; i < this.fItemValueTypesLength; ++i) {
                    this.fItemValueTypes.add(this.fItemValueType);
                }
                this.fItemValueTypes.add(fItemValueType);
            }
        }
        
        private ShortList getItemValueTypeAt(final int n) {
            if (this.fUseItemValueTypeVector) {
                return this.fItemValueTypes.elementAt(n);
            }
            return this.fItemValueType;
        }
        
        private boolean itemValueTypeContains(final ShortList list) {
            if (this.fUseItemValueTypeVector) {
                return this.fItemValueTypes.contains(list);
            }
            return this.fItemValueType == list || (this.fItemValueType != null && this.fItemValueType.equals(list));
        }
    }
    
    protected class UniqueValueStore extends ValueStoreBase
    {
        public UniqueValueStore(final UniqueOrKey uniqueOrKey) {
            super(uniqueOrKey);
        }
        
        protected void checkDuplicateValues() {
            if (this.contains()) {
                XMLSchemaValidator.this.reportSchemaError("DuplicateUnique", new Object[] { this.toString(super.fLocalValues), super.fIdentityConstraint.getElementName() });
            }
        }
    }
    
    protected class KeyValueStore extends ValueStoreBase
    {
        public KeyValueStore(final UniqueOrKey uniqueOrKey) {
            super(uniqueOrKey);
        }
        
        protected void checkDuplicateValues() {
            if (this.contains()) {
                XMLSchemaValidator.this.reportSchemaError("DuplicateKey", new Object[] { this.toString(super.fLocalValues), super.fIdentityConstraint.getElementName() });
            }
        }
    }
    
    protected class KeyRefValueStore extends ValueStoreBase
    {
        protected ValueStoreBase fKeyValueStore;
        
        public KeyRefValueStore(final KeyRef keyRef, final KeyValueStore fKeyValueStore) {
            super(keyRef);
            this.fKeyValueStore = fKeyValueStore;
        }
        
        public void endDocumentFragment() {
            super.endDocumentFragment();
            this.fKeyValueStore = XMLSchemaValidator.this.fValueStoreCache.fGlobalIDConstraintMap.get(((KeyRef)super.fIdentityConstraint).getKey());
            if (this.fKeyValueStore == null) {
                XMLSchemaValidator.this.reportSchemaError("KeyRefOutOfScope", new Object[] { super.fIdentityConstraint.toString() });
                return;
            }
            final int contains = this.fKeyValueStore.contains(this);
            if (contains != -1) {
                XMLSchemaValidator.this.reportSchemaError("KeyNotFound", new Object[] { super.fIdentityConstraint.getName(), this.toString(super.fValues, contains, super.fFieldCount), super.fIdentityConstraint.getElementName() });
            }
        }
        
        public void endDocument() {
            super.endDocument();
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
        
        public void addMatcher(final XPathMatcher xPathMatcher) {
            this.ensureMatcherCapacity();
            this.fMatchers[this.fMatchersCount++] = xPathMatcher;
        }
        
        public XPathMatcher getMatcherAt(final int n) {
            return this.fMatchers[n];
        }
        
        public void pushContext() {
            this.fContextStack.push(this.fMatchersCount);
        }
        
        public void popContext() {
            this.fMatchersCount = this.fContextStack.pop();
        }
        
        private void ensureMatcherCapacity() {
            if (this.fMatchersCount == this.fMatchers.length) {
                final XPathMatcher[] fMatchers = new XPathMatcher[this.fMatchers.length * 2];
                System.arraycopy(this.fMatchers, 0, fMatchers, 0, this.fMatchers.length);
                this.fMatchers = fMatchers;
            }
        }
    }
    
    protected final class XSIErrorReporter
    {
        XMLErrorReporter fErrorReporter;
        Vector fErrors;
        int[] fContext;
        int fContextCount;
        
        protected XSIErrorReporter() {
            this.fErrors = new Vector();
            this.fContext = new int[8];
        }
        
        public void reset(final XMLErrorReporter fErrorReporter) {
            this.fErrorReporter = fErrorReporter;
            this.fErrors.removeAllElements();
            this.fContextCount = 0;
        }
        
        public void pushContext() {
            if (!XMLSchemaValidator.this.fAugPSVI) {
                return;
            }
            if (this.fContextCount == this.fContext.length) {
                final int[] fContext = new int[this.fContextCount + 8];
                System.arraycopy(this.fContext, 0, fContext, 0, this.fContextCount);
                this.fContext = fContext;
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
            final int size = fContext[fContextCount];
            final int n = this.fErrors.size() - size;
            if (n == 0) {
                return null;
            }
            final String[] array = new String[n];
            for (int i = 0; i < n; ++i) {
                array[i] = (String)this.fErrors.elementAt(size + i);
            }
            this.fErrors.setSize(size);
            return array;
        }
        
        public String[] mergeContext() {
            if (!XMLSchemaValidator.this.fAugPSVI) {
                return null;
            }
            final int[] fContext = this.fContext;
            final int fContextCount = this.fContextCount - 1;
            this.fContextCount = fContextCount;
            final int n = fContext[fContextCount];
            final int n2 = this.fErrors.size() - n;
            if (n2 == 0) {
                return null;
            }
            final String[] array = new String[n2];
            for (int i = 0; i < n2; ++i) {
                array[i] = (String)this.fErrors.elementAt(n + i);
            }
            return array;
        }
        
        public void reportError(final String s, final String s2, final Object[] array, final short n) throws XNIException {
            this.fErrorReporter.reportError(s, s2, array, n);
            if (XMLSchemaValidator.this.fAugPSVI) {
                this.fErrors.addElement(s2);
            }
        }
        
        public void reportError(final XMLLocator xmlLocator, final String s, final String s2, final Object[] array, final short n) throws XNIException {
            this.fErrorReporter.reportError(xmlLocator, s, s2, array, n);
            if (XMLSchemaValidator.this.fAugPSVI) {
                this.fErrors.addElement(s2);
            }
        }
    }
}
