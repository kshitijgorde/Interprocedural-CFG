// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

import org.apache.xerces.impl.validation.EntityState;
import org.apache.xerces.impl.dtd.models.ContentModelValidator;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.impl.dv.DatatypeValidator;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.impl.dv.DTDDVFactory;
import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.validation.ValidationState;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.xni.parser.XMLDocumentFilter;
import org.apache.xerces.xni.parser.XMLComponent;

public class XMLDTDValidator implements XMLComponent, XMLDocumentFilter, XMLDTDValidatorFilter
{
    private static final int TOP_LEVEL_SCOPE = -1;
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String[] RECOGNIZED_FEATURES;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final Object[] PROPERTY_DEFAULTS;
    private static final boolean DEBUG_ATTRIBUTES = false;
    private static final boolean DEBUG_ELEMENT_CHILDREN = false;
    protected ValidationManager fValidationManager;
    protected ValidationState fValidationState;
    protected boolean fNamespaces;
    protected boolean fValidation;
    protected boolean fDTDValidation;
    protected boolean fDynamicValidation;
    protected boolean fWarnDuplicateAttdef;
    protected SymbolTable fSymbolTable;
    protected XMLErrorReporter fErrorReporter;
    protected XMLGrammarPool fGrammarPool;
    protected DTDGrammarBucket fGrammarBucket;
    protected XMLResourceIdentifier fDocLocation;
    protected NamespaceSupport fNamespaceSupport;
    protected DTDDVFactory fDatatypeValidatorFactory;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fDocumentSource;
    protected DTDGrammar fDTDGrammar;
    protected boolean fSeenDoctypeDecl;
    private boolean fPerformValidation;
    private boolean fSkipValidation;
    private final QName fCurrentElement;
    private int fCurrentElementIndex;
    private int fCurrentContentSpecType;
    private final QName fRootElement;
    private boolean fInCDATASection;
    private int[] fElementIndexStack;
    private int[] fContentSpecTypeStack;
    private QName[] fElementQNamePartsStack;
    private QName[] fElementChildren;
    private int fElementChildrenLength;
    private int[] fElementChildrenOffsetStack;
    private int fElementDepth;
    private boolean fSeenRootElement;
    private boolean fInElementContent;
    private XMLElementDecl fTempElementDecl;
    private XMLAttributeDecl fTempAttDecl;
    private XMLEntityDecl fEntityDecl;
    private QName fTempQName;
    private StringBuffer fBuffer;
    private DatatypeValidator fValID;
    private DatatypeValidator fValIDRef;
    private DatatypeValidator fValIDRefs;
    private DatatypeValidator fValENTITY;
    private DatatypeValidator fValENTITIES;
    private DatatypeValidator fValNMTOKEN;
    private DatatypeValidator fValNMTOKENS;
    private DatatypeValidator fValNOTATION;
    
    public XMLDTDValidator() {
        this.fValidationManager = null;
        this.fValidationState = new ValidationState();
        this.fNamespaceSupport = null;
        this.fSeenDoctypeDecl = false;
        this.fCurrentElement = new QName();
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fRootElement = new QName();
        this.fInCDATASection = false;
        this.fElementIndexStack = new int[8];
        this.fContentSpecTypeStack = new int[8];
        this.fElementQNamePartsStack = new QName[8];
        this.fElementChildren = new QName[32];
        this.fElementChildrenLength = 0;
        this.fElementChildrenOffsetStack = new int[32];
        this.fElementDepth = -1;
        this.fSeenRootElement = false;
        this.fInElementContent = false;
        this.fTempElementDecl = new XMLElementDecl();
        this.fTempAttDecl = new XMLAttributeDecl();
        this.fEntityDecl = new XMLEntityDecl();
        this.fTempQName = new QName();
        this.fBuffer = new StringBuffer();
        for (int i = 0; i < this.fElementQNamePartsStack.length; ++i) {
            this.fElementQNamePartsStack[i] = new QName();
        }
        this.fGrammarBucket = new DTDGrammarBucket();
    }
    
    DTDGrammarBucket getGrammarBucket() {
        return this.fGrammarBucket;
    }
    
    public void reset(final XMLComponentManager componentManager) throws XMLConfigurationException {
        this.fDTDGrammar = null;
        this.fSeenDoctypeDecl = false;
        this.fInCDATASection = false;
        this.fSeenRootElement = false;
        this.fInElementContent = false;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fSkipValidation = false;
        this.fRootElement.clear();
        try {
            this.fNamespaces = componentManager.getFeature("http://xml.org/sax/features/namespaces");
        }
        catch (XMLConfigurationException e) {
            this.fNamespaces = true;
        }
        try {
            this.fValidation = componentManager.getFeature("http://xml.org/sax/features/validation");
        }
        catch (XMLConfigurationException e) {
            this.fValidation = false;
        }
        try {
            this.fDTDValidation = !componentManager.getFeature("http://apache.org/xml/features/validation/schema");
        }
        catch (XMLConfigurationException e) {
            this.fDTDValidation = true;
        }
        try {
            this.fDynamicValidation = componentManager.getFeature("http://apache.org/xml/features/validation/dynamic");
        }
        catch (XMLConfigurationException e) {
            this.fDynamicValidation = false;
        }
        try {
            this.fWarnDuplicateAttdef = componentManager.getFeature("http://apache.org/xml/features/validation/warn-on-duplicate-attdef");
        }
        catch (XMLConfigurationException e) {
            this.fWarnDuplicateAttdef = false;
        }
        (this.fValidationManager = (ValidationManager)componentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager")).addValidationState(this.fValidationState);
        this.fValidationState.resetIDTables();
        this.fErrorReporter = (XMLErrorReporter)componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fSymbolTable = (SymbolTable)componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        try {
            this.fGrammarPool = (XMLGrammarPool)componentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        }
        catch (XMLConfigurationException e) {
            this.fGrammarPool = null;
        }
        this.fGrammarBucket.clear();
        this.fDatatypeValidatorFactory = (DTDDVFactory)componentManager.getProperty("http://apache.org/xml/properties/internal/datatype-validator-factory");
        this.fElementDepth = -1;
        this.fElementChildrenLength = 0;
        this.init();
    }
    
    public String[] getRecognizedFeatures() {
        return XMLDTDValidator.RECOGNIZED_FEATURES.clone();
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
    }
    
    public String[] getRecognizedProperties() {
        return XMLDTDValidator.RECOGNIZED_PROPERTIES.clone();
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
    }
    
    public Boolean getFeatureDefault(final String featureId) {
        for (int i = 0; i < XMLDTDValidator.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLDTDValidator.RECOGNIZED_FEATURES[i].equals(featureId)) {
                return XMLDTDValidator.FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public Object getPropertyDefault(final String propertyId) {
        for (int i = 0; i < XMLDTDValidator.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLDTDValidator.RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return XMLDTDValidator.PROPERTY_DEFAULTS[i];
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
        if (this.fGrammarPool != null) {
            final Grammar[] grammars = this.fGrammarPool.retrieveInitialGrammarSet("http://www.w3.org/TR/REC-xml");
            for (int i = 0; i < grammars.length; ++i) {
                this.fGrammarBucket.putGrammar((DTDGrammar)grammars[i]);
            }
        }
        this.fDocLocation = locator;
        if (namespaceContext instanceof NamespaceSupport) {
            this.fNamespaceSupport = (NamespaceSupport)namespaceContext;
        }
        else {
            this.fNamespaceSupport = new NamespaceSupport();
        }
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startDocument(locator, encoding, this.fNamespaceSupport, augs);
        }
    }
    
    public void xmlDecl(final String version, final String encoding, final String standalone, final Augmentations augs) throws XNIException {
        this.fGrammarBucket.setStandalone(standalone != null && standalone.equals("yes"));
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.xmlDecl(version, encoding, standalone, augs);
        }
    }
    
    public void doctypeDecl(final String rootElement, final String publicId, final String systemId, final Augmentations augs) throws XNIException {
        this.fSeenDoctypeDecl = true;
        this.fRootElement.setValues(null, rootElement, rootElement, null);
        final XMLDTDDescription grammarDesc = new XMLDTDDescription(publicId, systemId, this.fDocLocation.getExpandedSystemId(), XMLEntityManager.expandSystemId(systemId), rootElement);
        this.fDTDGrammar = this.fGrammarBucket.getGrammar(grammarDesc);
        if (this.fDTDGrammar == null && this.fGrammarPool != null) {
            this.fDTDGrammar = (DTDGrammar)this.fGrammarPool.retrieveGrammar(grammarDesc);
        }
        if (this.fDTDGrammar == null) {
            this.fDTDGrammar = new DTDGrammar(this.fSymbolTable, grammarDesc);
        }
        else {
            this.fValidationManager.setCachedDTD(true);
        }
        this.fGrammarBucket.setActiveGrammar(this.fDTDGrammar);
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
        this.handleStartElement(element, attributes, augs);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startElement(element, attributes, augs);
        }
    }
    
    public void emptyElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        final boolean removed = this.handleStartElement(element, attributes, augs);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.emptyElement(element, attributes, augs);
        }
        if (!removed) {
            this.handleEndElement(element, augs, true);
        }
    }
    
    public void characters(final XMLString text, final Augmentations augs) throws XNIException {
        boolean callNextCharacters = true;
        boolean allWhiteSpace = true;
        for (int i = text.offset; i < text.offset + text.length; ++i) {
            if (!XMLChar.isSpace(text.ch[i])) {
                allWhiteSpace = false;
                break;
            }
        }
        if (this.fInElementContent && allWhiteSpace && !this.fInCDATASection && this.fDocumentHandler != null) {
            this.fDocumentHandler.ignorableWhitespace(text, augs);
            callNextCharacters = false;
        }
        if (this.fPerformValidation) {
            if (this.fInElementContent) {
                if (this.fGrammarBucket.getStandalone() && this.fDTDGrammar.getElementDeclIsExternal(this.fCurrentElementIndex) && allWhiteSpace) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_WHITE_SPACE_IN_ELEMENT_CONTENT_WHEN_STANDALONE", null, (short)1);
                }
                if (!allWhiteSpace) {
                    this.charDataInContent();
                }
            }
            if (this.fCurrentContentSpecType == 1) {
                this.charDataInContent();
            }
        }
        if (callNextCharacters && this.fDocumentHandler != null) {
            this.fDocumentHandler.characters(text, augs);
        }
    }
    
    public void ignorableWhitespace(final XMLString text, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.ignorableWhitespace(text, augs);
        }
    }
    
    public void endElement(final QName element, final Augmentations augs) throws XNIException {
        this.handleEndElement(element, augs, false);
    }
    
    public void endPrefixMapping(final String prefix, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endPrefixMapping(prefix, augs);
        }
    }
    
    public void startCDATA(final Augmentations augs) throws XNIException {
        if (this.fPerformValidation && this.fInElementContent) {
            this.charDataInContent();
        }
        this.fInCDATASection = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startCDATA(augs);
        }
    }
    
    public void endCDATA(final Augmentations augs) throws XNIException {
        this.fInCDATASection = false;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endCDATA(augs);
        }
    }
    
    public void endDocument(final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endDocument(augs);
        }
    }
    
    public void comment(final XMLString text, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.comment(text, augs);
        }
    }
    
    public void processingInstruction(final String target, final XMLString data, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.processingInstruction(target, data, augs);
        }
    }
    
    public void startGeneralEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
        if (this.fPerformValidation && this.fDTDGrammar != null && this.fGrammarBucket.getStandalone()) {
            XMLDTDProcessor.checkStandaloneEntityRef(name, this.fDTDGrammar, this.fEntityDecl, this.fErrorReporter);
        }
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startGeneralEntity(name, identifier, encoding, augs);
        }
    }
    
    public void endGeneralEntity(final String name, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endGeneralEntity(name, augs);
        }
    }
    
    public void textDecl(final String version, final String encoding, final Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.textDecl(version, encoding, augs);
        }
    }
    
    public final boolean hasGrammar() {
        return this.fDTDGrammar != null;
    }
    
    public final boolean validate() {
        return this.fValidation && (!this.fDynamicValidation || this.fSeenDoctypeDecl) && (this.fDTDValidation || this.fSeenDoctypeDecl);
    }
    
    protected void addDTDDefaultAttrsAndValidate(final QName elementName, final int elementIndex, final XMLAttributes attributes) throws XNIException {
        if (elementIndex == -1 || this.fDTDGrammar == null) {
            return;
        }
        for (int attlistIndex = this.fDTDGrammar.getFirstAttributeDeclIndex(elementIndex); attlistIndex != -1; attlistIndex = this.fDTDGrammar.getNextAttributeDeclIndex(attlistIndex)) {
            this.fDTDGrammar.getAttributeDecl(attlistIndex, this.fTempAttDecl);
            String attPrefix = this.fTempAttDecl.name.prefix;
            String attLocalpart = this.fTempAttDecl.name.localpart;
            final String attRawName = this.fTempAttDecl.name.rawname;
            final String attType = this.getAttributeTypeName(this.fTempAttDecl);
            final int attDefaultType = this.fTempAttDecl.simpleType.defaultType;
            String attValue = null;
            if (this.fTempAttDecl.simpleType.defaultValue != null) {
                attValue = this.fTempAttDecl.simpleType.defaultValue;
            }
            boolean specified = false;
            final boolean required = attDefaultType == 2;
            final boolean cdata = attType == XMLSymbols.fCDATASymbol;
            if (!cdata || required || attValue != null) {
                for (int attrCount = attributes.getLength(), i = 0; i < attrCount; ++i) {
                    if (attributes.getQName(i) == attRawName) {
                        specified = true;
                        break;
                    }
                }
            }
            if (!specified) {
                if (required) {
                    if (this.fValidation) {
                        final Object[] args = { elementName.localpart, attRawName };
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_REQUIRED_ATTRIBUTE_NOT_SPECIFIED", args, (short)1);
                    }
                }
                else if (attValue != null) {
                    if (this.fPerformValidation && this.fGrammarBucket.getStandalone() && this.fDTDGrammar.getAttributeDeclIsExternal(attlistIndex)) {
                        final Object[] args = { elementName.localpart, attRawName };
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DEFAULTED_ATTRIBUTE_NOT_SPECIFIED", args, (short)1);
                    }
                    if (this.fNamespaces) {
                        final int index = attRawName.indexOf(58);
                        if (index != -1) {
                            attPrefix = attRawName.substring(0, index);
                            attPrefix = this.fSymbolTable.addSymbol(attPrefix);
                            attLocalpart = attRawName.substring(index + 1);
                            attLocalpart = this.fSymbolTable.addSymbol(attLocalpart);
                        }
                    }
                    this.fTempQName.setValues(attPrefix, attLocalpart, attRawName, this.fTempAttDecl.name.uri);
                    final int newAttr = attributes.addAttribute(this.fTempQName, attType, attValue);
                }
            }
        }
        for (int attrCount2 = attributes.getLength(), j = 0; j < attrCount2; ++j) {
            final String attrRawName = attributes.getQName(j);
            boolean declared = false;
            if (this.fPerformValidation && this.fGrammarBucket.getStandalone()) {
                final String nonNormalizedValue = attributes.getNonNormalizedValue(j);
                if (nonNormalizedValue != null) {
                    final String entityName = this.getExternalEntityRefInAttrValue(nonNormalizedValue);
                    if (entityName != null) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[] { entityName }, (short)1);
                    }
                }
            }
            int attDefIndex = -1;
            int position;
            for (position = this.fDTDGrammar.getFirstAttributeDeclIndex(elementIndex); position != -1; position = this.fDTDGrammar.getNextAttributeDeclIndex(position)) {
                this.fDTDGrammar.getAttributeDecl(position, this.fTempAttDecl);
                if (this.fTempAttDecl.name.rawname == attrRawName) {
                    attDefIndex = position;
                    declared = true;
                    break;
                }
            }
            if (!declared) {
                if (this.fPerformValidation) {
                    final Object[] args2 = { elementName.rawname, attrRawName };
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ATTRIBUTE_NOT_DECLARED", args2, (short)1);
                }
            }
            else {
                final String type = this.getAttributeTypeName(this.fTempAttDecl);
                attributes.setType(j, type);
                boolean changedByNormalization = false;
                String attrValue;
                final String oldValue = attrValue = attributes.getValue(j);
                if (attributes.isSpecified(j) && type != XMLSymbols.fCDATASymbol) {
                    changedByNormalization = this.normalizeAttrValue(attributes, j);
                    attrValue = attributes.getValue(j);
                    if (this.fPerformValidation && this.fGrammarBucket.getStandalone() && changedByNormalization && this.fDTDGrammar.getAttributeDeclIsExternal(position)) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ATTVALUE_CHANGED_DURING_NORMALIZATION_WHEN_STANDALONE", new Object[] { attrRawName, oldValue, attrValue }, (short)1);
                    }
                }
                if (this.fPerformValidation) {
                    if (this.fTempAttDecl.simpleType.defaultType == 1) {
                        final String defaultValue = this.fTempAttDecl.simpleType.defaultValue;
                        if (!attrValue.equals(defaultValue)) {
                            final Object[] args3 = { elementName.localpart, attrRawName, attrValue, defaultValue };
                            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_FIXED_ATTVALUE_INVALID", args3, (short)1);
                        }
                    }
                    if (this.fTempAttDecl.simpleType.type == 1 || this.fTempAttDecl.simpleType.type == 2 || this.fTempAttDecl.simpleType.type == 3 || this.fTempAttDecl.simpleType.type == 4 || this.fTempAttDecl.simpleType.type == 5 || this.fTempAttDecl.simpleType.type == 6) {
                        this.validateDTDattribute(elementName, attrValue, this.fTempAttDecl);
                    }
                }
            }
        }
    }
    
    protected String getExternalEntityRefInAttrValue(final String nonNormalizedValue) {
        final int valLength = nonNormalizedValue.length();
        for (int ampIndex = nonNormalizedValue.indexOf(38); ampIndex != -1; ampIndex = nonNormalizedValue.indexOf(38, ampIndex + 1)) {
            if (ampIndex + 1 < valLength && nonNormalizedValue.charAt(ampIndex + 1) != '#') {
                final int semicolonIndex = nonNormalizedValue.indexOf(59, ampIndex + 1);
                String entityName = nonNormalizedValue.substring(ampIndex + 1, semicolonIndex);
                entityName = this.fSymbolTable.addSymbol(entityName);
                final int entIndex = this.fDTDGrammar.getEntityDeclIndex(entityName);
                if (entIndex > -1) {
                    this.fDTDGrammar.getEntityDecl(entIndex, this.fEntityDecl);
                    if (this.fEntityDecl.inExternal || (entityName = this.getExternalEntityRefInAttrValue(this.fEntityDecl.value)) != null) {
                        return entityName;
                    }
                }
            }
        }
        return null;
    }
    
    protected void validateDTDattribute(final QName element, final String attValue, final XMLAttributeDecl attributeDecl) throws XNIException {
        switch (attributeDecl.simpleType.type) {
            case 1: {
                final boolean isAlistAttribute = attributeDecl.simpleType.list;
                try {
                    if (isAlistAttribute) {
                        this.fValENTITIES.validate(attValue, this.fValidationState);
                    }
                    else {
                        this.fValENTITY.validate(attValue, this.fValidationState);
                    }
                }
                catch (InvalidDatatypeValueException ex) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", ex.getKey(), ex.getArgs(), (short)1);
                }
                break;
            }
            case 2:
            case 6: {
                boolean found = false;
                final String[] enumVals = attributeDecl.simpleType.enumeration;
                if (enumVals == null) {
                    found = false;
                }
                else {
                    for (int i = 0; i < enumVals.length; ++i) {
                        if (attValue == enumVals[i] || attValue.equals(enumVals[i])) {
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    final StringBuffer enumValueString = new StringBuffer();
                    if (enumVals != null) {
                        for (int j = 0; j < enumVals.length; ++j) {
                            enumValueString.append(enumVals[j] + " ");
                        }
                    }
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ATTRIBUTE_VALUE_NOT_IN_LIST", new Object[] { attributeDecl.name.rawname, attValue, enumValueString }, (short)1);
                    break;
                }
                break;
            }
            case 3: {
                try {
                    this.fValID.validate(attValue, this.fValidationState);
                }
                catch (InvalidDatatypeValueException ex2) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", ex2.getKey(), ex2.getArgs(), (short)1);
                }
                break;
            }
            case 4: {
                final boolean isAlistAttribute = attributeDecl.simpleType.list;
                try {
                    if (isAlistAttribute) {
                        this.fValIDRefs.validate(attValue, this.fValidationState);
                    }
                    else {
                        this.fValIDRef.validate(attValue, this.fValidationState);
                    }
                }
                catch (InvalidDatatypeValueException ex) {
                    String key = ex.getKey();
                    if (isAlistAttribute) {
                        key = "IDREFSInvalid";
                    }
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", key, ex.getArgs(), (short)1);
                }
                break;
            }
            case 5: {
                final boolean isAlistAttribute = attributeDecl.simpleType.list;
                try {
                    if (isAlistAttribute) {
                        this.fValNMTOKENS.validate(attValue, this.fValidationState);
                    }
                    else {
                        this.fValNMTOKEN.validate(attValue, this.fValidationState);
                    }
                }
                catch (InvalidDatatypeValueException ex) {
                    if (isAlistAttribute) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "NMTOKENSInvalid", new Object[] { attValue }, (short)1);
                    }
                    else {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "NMTOKENInvalid", new Object[] { attValue }, (short)1);
                    }
                }
                break;
            }
        }
    }
    
    protected boolean invalidStandaloneAttDef(final QName element, final QName attribute) {
        final boolean state = true;
        return state;
    }
    
    private boolean normalizeAttrValue(final XMLAttributes attributes, final int index) {
        boolean leadingSpace = true;
        boolean spaceStart = false;
        boolean readingNonSpace = false;
        int count = 0;
        int eaten = 0;
        final String attrValue = attributes.getValue(index);
        final char[] attValue = new char[attrValue.length()];
        this.fBuffer.setLength(0);
        attrValue.getChars(0, attrValue.length(), attValue, 0);
        for (int i = 0; i < attValue.length; ++i) {
            if (attValue[i] == ' ') {
                if (readingNonSpace) {
                    spaceStart = true;
                    readingNonSpace = false;
                }
                if (spaceStart && !leadingSpace) {
                    spaceStart = false;
                    this.fBuffer.append(attValue[i]);
                    ++count;
                }
                else if (leadingSpace || !spaceStart) {
                    ++eaten;
                }
            }
            else {
                readingNonSpace = true;
                spaceStart = false;
                leadingSpace = false;
                this.fBuffer.append(attValue[i]);
                ++count;
            }
        }
        if (count > 0 && this.fBuffer.charAt(count - 1) == ' ') {
            this.fBuffer.setLength(count - 1);
        }
        final String newValue = this.fBuffer.toString();
        attributes.setValue(index, newValue);
        return !attrValue.equals(newValue);
    }
    
    private final void rootElementSpecified(final QName rootElement) throws XNIException {
        if (this.fPerformValidation) {
            final String root1 = this.fRootElement.rawname;
            final String root2 = rootElement.rawname;
            if (root1 == null || !root1.equals(root2)) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RootElementTypeMustMatchDoctypedecl", new Object[] { root1, root2 }, (short)1);
            }
        }
    }
    
    private int checkContent(final int elementIndex, final QName[] children, final int childOffset, final int childCount) throws XNIException {
        this.fDTDGrammar.getElementDecl(elementIndex, this.fTempElementDecl);
        final String elementType = this.fCurrentElement.rawname;
        final int contentType = this.fCurrentContentSpecType;
        if (contentType == 1) {
            if (childCount != 0) {
                return 0;
            }
        }
        else if (contentType != 0) {
            if (contentType == 2 || contentType == 3) {
                ContentModelValidator cmElem = null;
                cmElem = this.fTempElementDecl.contentModelValidator;
                final int result = cmElem.validate(children, childOffset, childCount);
                return result;
            }
            if (contentType != -1) {
                if (contentType == 4) {}
            }
        }
        return -1;
    }
    
    private int getContentSpecType(final int elementIndex) {
        int contentSpecType = -1;
        if (elementIndex > -1 && this.fDTDGrammar.getElementDecl(elementIndex, this.fTempElementDecl)) {
            contentSpecType = this.fTempElementDecl.type;
        }
        return contentSpecType;
    }
    
    private void charDataInContent() {
        if (this.fElementChildren.length <= this.fElementChildrenLength) {
            final QName[] newarray = new QName[this.fElementChildren.length * 2];
            System.arraycopy(this.fElementChildren, 0, newarray, 0, this.fElementChildren.length);
            this.fElementChildren = newarray;
        }
        QName qname = this.fElementChildren[this.fElementChildrenLength];
        if (qname == null) {
            for (int i = this.fElementChildrenLength; i < this.fElementChildren.length; ++i) {
                this.fElementChildren[i] = new QName();
            }
            qname = this.fElementChildren[this.fElementChildrenLength];
        }
        qname.clear();
        ++this.fElementChildrenLength;
    }
    
    private String getAttributeTypeName(final XMLAttributeDecl attrDecl) {
        switch (attrDecl.simpleType.type) {
            case 1: {
                return attrDecl.simpleType.list ? XMLSymbols.fENTITIESSymbol : XMLSymbols.fENTITYSymbol;
            }
            case 2: {
                final StringBuffer buffer = new StringBuffer();
                buffer.append('(');
                for (int i = 0; i < attrDecl.simpleType.enumeration.length; ++i) {
                    if (i > 0) {
                        buffer.append("|");
                    }
                    buffer.append(attrDecl.simpleType.enumeration[i]);
                }
                buffer.append(')');
                return this.fSymbolTable.addSymbol(buffer.toString());
            }
            case 3: {
                return XMLSymbols.fIDSymbol;
            }
            case 4: {
                return attrDecl.simpleType.list ? XMLSymbols.fIDREFSSymbol : XMLSymbols.fIDREFSymbol;
            }
            case 5: {
                return attrDecl.simpleType.list ? XMLSymbols.fNMTOKENSSymbol : XMLSymbols.fNMTOKENSymbol;
            }
            case 6: {
                return XMLSymbols.fNOTATIONSymbol;
            }
            default: {
                return XMLSymbols.fCDATASymbol;
            }
        }
    }
    
    private void init() {
        if (this.fValidation) {
            try {
                this.fValID = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fIDSymbol);
                this.fValIDRef = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fIDREFSymbol);
                this.fValIDRefs = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fIDREFSSymbol);
                this.fValENTITY = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fENTITYSymbol);
                this.fValENTITIES = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fENTITIESSymbol);
                this.fValNMTOKEN = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fNMTOKENSymbol);
                this.fValNMTOKENS = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fNMTOKENSSymbol);
                this.fValNOTATION = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fNOTATIONSymbol);
            }
            catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }
    
    private void ensureStackCapacity(final int newElementDepth) {
        if (newElementDepth == this.fElementQNamePartsStack.length) {
            int[] newStack = new int[newElementDepth * 2];
            final QName[] newStackOfQueue = new QName[newElementDepth * 2];
            System.arraycopy(this.fElementQNamePartsStack, 0, newStackOfQueue, 0, newElementDepth);
            this.fElementQNamePartsStack = newStackOfQueue;
            final QName qname = this.fElementQNamePartsStack[newElementDepth];
            if (qname == null) {
                for (int i = newElementDepth; i < this.fElementQNamePartsStack.length; ++i) {
                    this.fElementQNamePartsStack[i] = new QName();
                }
            }
            newStack = new int[newElementDepth * 2];
            System.arraycopy(this.fElementIndexStack, 0, newStack, 0, newElementDepth);
            this.fElementIndexStack = newStack;
            newStack = new int[newElementDepth * 2];
            System.arraycopy(this.fContentSpecTypeStack, 0, newStack, 0, newElementDepth);
            this.fContentSpecTypeStack = newStack;
        }
    }
    
    protected boolean handleStartElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        this.fPerformValidation = (this.fValidation && (!this.fDynamicValidation || this.fSeenDoctypeDecl) && (this.fDTDValidation || this.fSeenDoctypeDecl));
        if (!this.fSeenRootElement) {
            this.fSeenRootElement = true;
            this.fValidationManager.setEntityState(this.fDTDGrammar);
            this.fValidationManager.setGrammarFound(this.fSeenDoctypeDecl);
            this.rootElementSpecified(element);
        }
        if (this.fDTDGrammar == null) {
            if (!this.fPerformValidation) {
                this.fCurrentElementIndex = -1;
                this.fCurrentContentSpecType = -1;
                this.fInElementContent = false;
            }
            if (this.fPerformValidation && !this.fSkipValidation) {
                this.fSkipValidation = true;
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_GRAMMAR_NOT_FOUND", new Object[] { element.rawname }, (short)1);
            }
            if (this.fDocumentSource != null) {
                this.fDocumentSource.setDocumentHandler(this.fDocumentHandler);
                if (this.fDocumentHandler != null) {
                    this.fDocumentHandler.setDocumentSource(this.fDocumentSource);
                }
                return true;
            }
        }
        else {
            this.fCurrentElementIndex = this.fDTDGrammar.getElementDeclIndex(element);
            this.fCurrentContentSpecType = this.fDTDGrammar.getContentSpecType(this.fCurrentElementIndex);
            if (this.fCurrentContentSpecType == -1 && this.fPerformValidation) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ELEMENT_NOT_DECLARED", new Object[] { element.rawname }, (short)1);
            }
            else {
                this.addDTDDefaultAttrsAndValidate(element, this.fCurrentElementIndex, attributes);
            }
        }
        this.fInElementContent = (this.fCurrentContentSpecType == 3);
        ++this.fElementDepth;
        if (this.fPerformValidation) {
            if (this.fElementChildrenOffsetStack.length <= this.fElementDepth) {
                final int[] newarray = new int[this.fElementChildrenOffsetStack.length * 2];
                System.arraycopy(this.fElementChildrenOffsetStack, 0, newarray, 0, this.fElementChildrenOffsetStack.length);
                this.fElementChildrenOffsetStack = newarray;
            }
            this.fElementChildrenOffsetStack[this.fElementDepth] = this.fElementChildrenLength;
            if (this.fElementChildren.length <= this.fElementChildrenLength) {
                final QName[] newarray2 = new QName[this.fElementChildrenLength * 2];
                System.arraycopy(this.fElementChildren, 0, newarray2, 0, this.fElementChildren.length);
                this.fElementChildren = newarray2;
            }
            QName qname = this.fElementChildren[this.fElementChildrenLength];
            if (qname == null) {
                for (int i = this.fElementChildrenLength; i < this.fElementChildren.length; ++i) {
                    this.fElementChildren[i] = new QName();
                }
                qname = this.fElementChildren[this.fElementChildrenLength];
            }
            qname.setValues(element);
            ++this.fElementChildrenLength;
        }
        this.fCurrentElement.setValues(element);
        this.ensureStackCapacity(this.fElementDepth);
        this.fElementQNamePartsStack[this.fElementDepth].setValues(this.fCurrentElement);
        this.fElementIndexStack[this.fElementDepth] = this.fCurrentElementIndex;
        this.fContentSpecTypeStack[this.fElementDepth] = this.fCurrentContentSpecType;
        this.startNamespaceScope(element, attributes, augs);
        return false;
    }
    
    protected void startNamespaceScope(final QName element, final XMLAttributes attributes, final Augmentations augs) {
    }
    
    protected void handleEndElement(final QName element, final Augmentations augs, final boolean isEmpty) throws XNIException {
        --this.fElementDepth;
        if (this.fPerformValidation) {
            final int elementIndex = this.fCurrentElementIndex;
            if (elementIndex != -1 && this.fCurrentContentSpecType != -1) {
                final QName[] children = this.fElementChildren;
                final int childrenOffset = this.fElementChildrenOffsetStack[this.fElementDepth + 1] + 1;
                final int childrenLength = this.fElementChildrenLength - childrenOffset;
                final int result = this.checkContent(elementIndex, children, childrenOffset, childrenLength);
                if (result != -1) {
                    this.fDTDGrammar.getElementDecl(elementIndex, this.fTempElementDecl);
                    if (this.fTempElementDecl.type == 1) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_CONTENT_INVALID", new Object[] { element.rawname, "EMPTY" }, (short)1);
                    }
                    else {
                        final String messageKey = (result != childrenLength) ? "MSG_CONTENT_INVALID" : "MSG_CONTENT_INCOMPLETE";
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", messageKey, new Object[] { element.rawname, this.fDTDGrammar.getContentSpecAsString(elementIndex) }, (short)1);
                    }
                }
            }
            this.fElementChildrenLength = this.fElementChildrenOffsetStack[this.fElementDepth + 1] + 1;
        }
        this.endNamespaceScope(this.fCurrentElement, augs, isEmpty);
        if (this.fElementDepth < -1) {
            throw new RuntimeException("FWK008 Element stack underflow");
        }
        if (this.fElementDepth < 0) {
            this.fCurrentElement.clear();
            this.fCurrentElementIndex = -1;
            this.fCurrentContentSpecType = -1;
            this.fInElementContent = false;
            if (this.fPerformValidation) {
                final String value = this.fValidationState.checkIDRefID();
                if (value != null) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ELEMENT_WITH_ID_REQUIRED", new Object[] { value }, (short)1);
                }
            }
            return;
        }
        this.fCurrentElement.setValues(this.fElementQNamePartsStack[this.fElementDepth]);
        this.fCurrentElementIndex = this.fElementIndexStack[this.fElementDepth];
        this.fCurrentContentSpecType = this.fContentSpecTypeStack[this.fElementDepth];
        this.fInElementContent = (this.fCurrentContentSpecType == 3);
    }
    
    protected void endNamespaceScope(final QName element, final Augmentations augs, final boolean isEmpty) {
        if (this.fDocumentHandler != null && !isEmpty) {
            this.fDocumentHandler.endElement(this.fCurrentElement, augs);
        }
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/namespaces", "http://xml.org/sax/features/validation", "http://apache.org/xml/features/validation/dynamic" };
        FEATURE_DEFAULTS = new Boolean[] { null, null, Boolean.FALSE };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/internal/datatype-validator-factory", "http://apache.org/xml/properties/internal/validation-manager" };
        PROPERTY_DEFAULTS = new Object[] { null, null, null, null, null };
    }
}
