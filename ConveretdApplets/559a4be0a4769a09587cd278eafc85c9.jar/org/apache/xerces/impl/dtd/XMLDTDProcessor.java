// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

import java.util.Enumeration;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.util.XMLChar;
import java.util.StringTokenizer;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import java.util.Vector;
import java.util.Hashtable;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import java.util.Locale;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLDTDContentModelFilter;
import org.apache.xerces.xni.parser.XMLDTDFilter;
import org.apache.xerces.xni.parser.XMLComponent;

public class XMLDTDProcessor implements XMLComponent, XMLDTDFilter, XMLDTDContentModelFilter
{
    private static final int TOP_LEVEL_SCOPE = -1;
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    private static final String[] RECOGNIZED_FEATURES;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final Object[] PROPERTY_DEFAULTS;
    protected boolean fValidation;
    protected boolean fDTDValidation;
    protected boolean fWarnDuplicateAttdef;
    protected SymbolTable fSymbolTable;
    protected XMLErrorReporter fErrorReporter;
    protected DTDGrammarBucket fGrammarBucket;
    protected XMLDTDValidator fValidator;
    protected XMLGrammarPool fGrammarPool;
    protected Locale fLocale;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected DTDGrammar fDTDGrammar;
    private boolean fPerformValidation;
    protected boolean fInDTDIgnore;
    private boolean fMixed;
    private XMLEntityDecl fEntityDecl;
    private Hashtable fNDataDeclNotations;
    private String fDTDElementDeclName;
    private Vector fMixedElementTypes;
    private Vector fDTDElementDecls;
    private Hashtable fTableOfIDAttributeNames;
    private Hashtable fTableOfNOTATIONAttributeNames;
    private Hashtable fNotationEnumVals;
    
    public XMLDTDProcessor() {
        this.fEntityDecl = new XMLEntityDecl();
        this.fNDataDeclNotations = new Hashtable();
        this.fDTDElementDeclName = null;
        this.fMixedElementTypes = new Vector();
        this.fDTDElementDecls = new Vector();
    }
    
    public void reset(final XMLComponentManager componentManager) throws XMLConfigurationException {
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
            this.fWarnDuplicateAttdef = componentManager.getFeature("http://apache.org/xml/features/validation/warn-on-duplicate-attdef");
        }
        catch (XMLConfigurationException e) {
            this.fWarnDuplicateAttdef = false;
        }
        this.fErrorReporter = (XMLErrorReporter)componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fSymbolTable = (SymbolTable)componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        try {
            this.fGrammarPool = (XMLGrammarPool)componentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        }
        catch (XMLConfigurationException e) {
            this.fGrammarPool = null;
        }
        try {
            this.fValidator = (XMLDTDValidator)componentManager.getProperty("http://apache.org/xml/properties/internal/validator/dtd");
        }
        catch (XMLConfigurationException e) {
            this.fValidator = null;
        }
        catch (ClassCastException e2) {
            this.fValidator = null;
        }
        if (this.fValidator != null) {
            this.fGrammarBucket = this.fValidator.getGrammarBucket();
        }
        else {
            this.fGrammarBucket = null;
        }
        this.reset();
    }
    
    protected void reset() {
        this.fDTDGrammar = null;
        this.fInDTDIgnore = false;
        this.fNDataDeclNotations.clear();
        this.init();
    }
    
    public String[] getRecognizedFeatures() {
        return XMLDTDProcessor.RECOGNIZED_FEATURES.clone();
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
    }
    
    public String[] getRecognizedProperties() {
        return XMLDTDProcessor.RECOGNIZED_PROPERTIES.clone();
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
    }
    
    public Boolean getFeatureDefault(final String featureId) {
        for (int i = 0; i < XMLDTDProcessor.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLDTDProcessor.RECOGNIZED_FEATURES[i].equals(featureId)) {
                return XMLDTDProcessor.FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public Object getPropertyDefault(final String propertyId) {
        for (int i = 0; i < XMLDTDProcessor.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLDTDProcessor.RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return XMLDTDProcessor.PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public void setDTDHandler(final XMLDTDHandler dtdHandler) {
        this.fDTDHandler = dtdHandler;
    }
    
    public void setDTDContentModelHandler(final XMLDTDContentModelHandler dtdContentModelHandler) {
        this.fDTDContentModelHandler = dtdContentModelHandler;
    }
    
    public void startExternalSubset(final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startExternalSubset(identifier, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startExternalSubset(identifier, augs);
        }
    }
    
    public void endExternalSubset(final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endExternalSubset(augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endExternalSubset(augs);
        }
    }
    
    protected static void checkStandaloneEntityRef(final String name, final DTDGrammar grammar, final XMLEntityDecl tempEntityDecl, final XMLErrorReporter errorReporter) throws XNIException {
        final int entIndex = grammar.getEntityDeclIndex(name);
        if (entIndex > -1) {
            grammar.getEntityDecl(entIndex, tempEntityDecl);
            if (tempEntityDecl.inExternal) {
                errorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[] { name }, (short)1);
            }
        }
    }
    
    public void comment(final XMLString text, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.comment(text, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.comment(text, augs);
        }
    }
    
    public void processingInstruction(final String target, final XMLString data, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.processingInstruction(target, data, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.processingInstruction(target, data, augs);
        }
    }
    
    public void startDTD(final XMLLocator locator, final Augmentations augs) throws XNIException {
        this.fNDataDeclNotations.clear();
        this.fDTDElementDecls.removeAllElements();
        if (!this.fGrammarBucket.getActiveGrammar().isImmutable()) {
            this.fDTDGrammar = this.fGrammarBucket.getActiveGrammar();
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startDTD(locator, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startDTD(locator, augs);
        }
    }
    
    public void ignoredCharacters(final XMLString text, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.ignoredCharacters(text, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.ignoredCharacters(text, augs);
        }
    }
    
    public void textDecl(final String version, final String encoding, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.textDecl(version, encoding, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.textDecl(version, encoding, augs);
        }
    }
    
    public void startParameterEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
        if (this.fPerformValidation && this.fDTDGrammar != null && this.fGrammarBucket.getStandalone()) {
            checkStandaloneEntityRef(name, this.fDTDGrammar, this.fEntityDecl, this.fErrorReporter);
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startParameterEntity(name, identifier, encoding, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startParameterEntity(name, identifier, encoding, augs);
        }
    }
    
    public void endParameterEntity(final String name, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endParameterEntity(name, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endParameterEntity(name, augs);
        }
    }
    
    public void elementDecl(final String name, final String contentModel, final Augmentations augs) throws XNIException {
        if (this.fValidation) {
            if (this.fDTDElementDecls.contains(name)) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ELEMENT_ALREADY_DECLARED", new Object[] { name }, (short)1);
            }
            else {
                this.fDTDElementDecls.addElement(name);
            }
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.elementDecl(name, contentModel, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.elementDecl(name, contentModel, augs);
        }
    }
    
    public void startAttlist(final String elementName, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startAttlist(elementName, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startAttlist(elementName, augs);
        }
    }
    
    public void attributeDecl(final String elementName, final String attributeName, final String type, final String[] enumeration, final String defaultType, final XMLString defaultValue, final XMLString nonNormalizedDefaultValue, final Augmentations augs) throws XNIException {
        if (type != XMLSymbols.fCDATASymbol && defaultValue != null) {
            this.normalizeDefaultAttrValue(defaultValue);
        }
        if (this.fValidation) {
            boolean duplicateAttributeDef = false;
            final DTDGrammar grammar = (this.fDTDGrammar != null) ? this.fDTDGrammar : this.fGrammarBucket.getActiveGrammar();
            final int elementIndex = grammar.getElementDeclIndex(elementName);
            if (grammar.getAttributeDeclIndex(elementIndex, attributeName) != -1) {
                duplicateAttributeDef = true;
                if (this.fWarnDuplicateAttdef) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ATTRIBUTE_DEFINITION", new Object[] { elementName, attributeName }, (short)0);
                }
            }
            if (type == XMLSymbols.fIDSymbol) {
                if (defaultValue != null && defaultValue.length != 0 && (defaultType == null || (defaultType != XMLSymbols.fIMPLIEDSymbol && defaultType != XMLSymbols.fREQUIREDSymbol))) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "IDDefaultTypeInvalid", new Object[] { attributeName }, (short)1);
                }
                if (!this.fTableOfIDAttributeNames.containsKey(elementName)) {
                    this.fTableOfIDAttributeNames.put(elementName, attributeName);
                }
                else if (!duplicateAttributeDef) {
                    final String previousIDAttributeName = this.fTableOfIDAttributeNames.get(elementName);
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_MORE_THAN_ONE_ID_ATTRIBUTE", new Object[] { elementName, previousIDAttributeName, attributeName }, (short)1);
                }
            }
            if (type == XMLSymbols.fNOTATIONSymbol) {
                for (int i = 0; i < enumeration.length; ++i) {
                    this.fNotationEnumVals.put(enumeration[i], attributeName);
                }
                if (!this.fTableOfNOTATIONAttributeNames.containsKey(elementName)) {
                    this.fTableOfNOTATIONAttributeNames.put(elementName, attributeName);
                }
                else if (!duplicateAttributeDef) {
                    final String previousNOTATIONAttributeName = this.fTableOfNOTATIONAttributeNames.get(elementName);
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_MORE_THAN_ONE_NOTATION_ATTRIBUTE", new Object[] { elementName, previousNOTATIONAttributeName, attributeName }, (short)1);
                }
            }
            boolean ok = true;
            if (defaultValue != null && (defaultType == null || (defaultType != null && defaultType == XMLSymbols.fFIXEDSymbol))) {
                final String value = defaultValue.toString();
                if (type == XMLSymbols.fNMTOKENSSymbol || type == XMLSymbols.fENTITIESSymbol || type == XMLSymbols.fIDREFSSymbol) {
                    final StringTokenizer tokenizer = new StringTokenizer(value);
                    if (tokenizer.hasMoreTokens()) {
                        do {
                            final String nmtoken = tokenizer.nextToken();
                            if (type == XMLSymbols.fNMTOKENSSymbol) {
                                if (!XMLChar.isValidNmtoken(nmtoken)) {
                                    ok = false;
                                    break;
                                }
                            }
                            else if ((type == XMLSymbols.fENTITIESSymbol || type == XMLSymbols.fIDREFSSymbol) && !XMLChar.isValidName(nmtoken)) {
                                ok = false;
                                break;
                            }
                        } while (tokenizer.hasMoreTokens());
                    }
                }
                else {
                    if (type == XMLSymbols.fENTITYSymbol || type == XMLSymbols.fIDSymbol || type == XMLSymbols.fIDREFSymbol || type == XMLSymbols.fNOTATIONSymbol) {
                        if (!XMLChar.isValidName(value)) {
                            ok = false;
                        }
                    }
                    else if ((type == XMLSymbols.fNMTOKENSymbol || type == XMLSymbols.fENUMERATIONSymbol) && !XMLChar.isValidNmtoken(value)) {
                        ok = false;
                    }
                    if (type == XMLSymbols.fNOTATIONSymbol || type == XMLSymbols.fENUMERATIONSymbol) {
                        ok = false;
                        for (int j = 0; j < enumeration.length; ++j) {
                            if (defaultValue.equals(enumeration[j])) {
                                ok = true;
                            }
                        }
                    }
                }
                if (!ok) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ATT_DEFAULT_INVALID", new Object[] { attributeName, value }, (short)1);
                }
            }
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.attributeDecl(elementName, attributeName, type, enumeration, defaultType, defaultValue, nonNormalizedDefaultValue, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.attributeDecl(elementName, attributeName, type, enumeration, defaultType, defaultValue, nonNormalizedDefaultValue, augs);
        }
    }
    
    public void endAttlist(final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endAttlist(augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endAttlist(augs);
        }
    }
    
    public void internalEntityDecl(final String name, final XMLString text, final XMLString nonNormalizedText, final Augmentations augs) throws XNIException {
        final DTDGrammar grammar = (this.fDTDGrammar != null) ? this.fDTDGrammar : this.fGrammarBucket.getActiveGrammar();
        final int index = grammar.getEntityDeclIndex(name);
        if (index == -1) {
            if (this.fDTDGrammar != null) {
                this.fDTDGrammar.internalEntityDecl(name, text, nonNormalizedText, augs);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.internalEntityDecl(name, text, nonNormalizedText, augs);
            }
        }
    }
    
    public void externalEntityDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
        final DTDGrammar grammar = (this.fDTDGrammar != null) ? this.fDTDGrammar : this.fGrammarBucket.getActiveGrammar();
        final int index = grammar.getEntityDeclIndex(name);
        if (index == -1) {
            if (this.fDTDGrammar != null) {
                this.fDTDGrammar.externalEntityDecl(name, identifier, augs);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.externalEntityDecl(name, identifier, augs);
            }
        }
    }
    
    public void unparsedEntityDecl(final String name, final XMLResourceIdentifier identifier, final String notation, final Augmentations augs) throws XNIException {
        if (this.fValidation) {
            this.fNDataDeclNotations.put(name, notation);
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.unparsedEntityDecl(name, identifier, notation, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.unparsedEntityDecl(name, identifier, notation, augs);
        }
    }
    
    public void notationDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.notationDecl(name, identifier, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.notationDecl(name, identifier, augs);
        }
    }
    
    public void startConditional(final short type, final Augmentations augs) throws XNIException {
        this.fInDTDIgnore = (type == 1);
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startConditional(type, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startConditional(type, augs);
        }
    }
    
    public void endConditional(final Augmentations augs) throws XNIException {
        this.fInDTDIgnore = false;
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endConditional(augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endConditional(augs);
        }
    }
    
    public void endDTD(final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endDTD(augs);
            if (this.fGrammarPool != null) {
                this.fGrammarPool.cacheGrammars("http://www.w3.org/TR/REC-xml", new Grammar[] { this.fDTDGrammar });
            }
        }
        if (this.fValidation) {
            final DTDGrammar grammar = (this.fDTDGrammar != null) ? this.fDTDGrammar : this.fGrammarBucket.getActiveGrammar();
            final Enumeration entities = this.fNDataDeclNotations.keys();
            while (entities.hasMoreElements()) {
                final String entity = entities.nextElement();
                final String notation = this.fNDataDeclNotations.get(entity);
                if (grammar.getNotationDeclIndex(notation) == -1) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NOTATION_NOT_DECLARED_FOR_UNPARSED_ENTITYDECL", new Object[] { entity, notation }, (short)1);
                }
            }
            final Enumeration notationVals = this.fNotationEnumVals.keys();
            while (notationVals.hasMoreElements()) {
                final String notation = notationVals.nextElement();
                final String attributeName = this.fNotationEnumVals.get(notation);
                if (grammar.getNotationDeclIndex(notation) == -1) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NOTATION_NOT_DECLARED_FOR_NOTATIONTYPE_ATTRIBUTE", new Object[] { attributeName, notation }, (short)1);
                }
            }
            this.fTableOfIDAttributeNames = null;
            this.fTableOfNOTATIONAttributeNames = null;
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endDTD(augs);
        }
    }
    
    public void startContentModel(final String elementName, final Augmentations augs) throws XNIException {
        if (this.fValidation) {
            this.fDTDElementDeclName = elementName;
            this.fMixedElementTypes.removeAllElements();
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startContentModel(elementName, augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.startContentModel(elementName, augs);
        }
    }
    
    public void any(final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.any(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.any(augs);
        }
    }
    
    public void empty(final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.empty(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.empty(augs);
        }
    }
    
    public void startGroup(final Augmentations augs) throws XNIException {
        this.fMixed = false;
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startGroup(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.startGroup(augs);
        }
    }
    
    public void pcdata(final Augmentations augs) {
        this.fMixed = true;
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.pcdata(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.pcdata(augs);
        }
    }
    
    public void element(final String elementName, final Augmentations augs) throws XNIException {
        if (this.fMixed && this.fValidation) {
            if (this.fMixedElementTypes.contains(elementName)) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "DuplicateTypeInMixedContent", new Object[] { this.fDTDElementDeclName, elementName }, (short)1);
            }
            else {
                this.fMixedElementTypes.addElement(elementName);
            }
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.element(elementName, augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.element(elementName, augs);
        }
    }
    
    public void separator(final short separator, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.separator(separator, augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.separator(separator, augs);
        }
    }
    
    public void occurrence(final short occurrence, final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.occurrence(occurrence, augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.occurrence(occurrence, augs);
        }
    }
    
    public void endGroup(final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endGroup(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.endGroup(augs);
        }
    }
    
    public void endContentModel(final Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endContentModel(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.endContentModel(augs);
        }
    }
    
    private boolean normalizeDefaultAttrValue(final XMLString value) {
        final int oldLength = value.length;
        boolean skipSpace = true;
        int current = value.offset;
        final int end = value.offset + value.length;
        for (int i = value.offset; i < end; ++i) {
            if (value.ch[i] == ' ') {
                if (!skipSpace) {
                    value.ch[current++] = ' ';
                    skipSpace = true;
                }
            }
            else {
                if (current != i) {
                    value.ch[current] = value.ch[i];
                }
                ++current;
                skipSpace = false;
            }
        }
        if (current != end) {
            if (skipSpace) {
                --current;
            }
            value.length = current - value.offset;
            return true;
        }
        return false;
    }
    
    private void init() {
        if (this.fValidation) {
            if (this.fNotationEnumVals == null) {
                this.fNotationEnumVals = new Hashtable();
            }
            this.fNotationEnumVals.clear();
            this.fTableOfIDAttributeNames = new Hashtable();
            this.fTableOfNOTATIONAttributeNames = new Hashtable();
        }
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://apache.org/xml/features/validation/warn-on-duplicate-attdef", "http://apache.org/xml/features/scanner/notify-char-refs" };
        FEATURE_DEFAULTS = new Boolean[] { null, Boolean.FALSE, null };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/internal/validator/dtd" };
        PROPERTY_DEFAULTS = new Object[] { null, null, null, null };
    }
}
