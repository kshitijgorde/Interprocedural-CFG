// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import java.io.EOFException;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLDocumentScanner;

public class XMLDocumentFragmentScannerImpl extends XMLScanner implements XMLDocumentScanner, XMLComponent, XMLEntityHandler
{
    protected static final int SCANNER_STATE_START_OF_MARKUP = 1;
    protected static final int SCANNER_STATE_COMMENT = 2;
    protected static final int SCANNER_STATE_PI = 3;
    protected static final int SCANNER_STATE_DOCTYPE = 4;
    protected static final int SCANNER_STATE_ROOT_ELEMENT = 6;
    protected static final int SCANNER_STATE_CONTENT = 7;
    protected static final int SCANNER_STATE_REFERENCE = 8;
    protected static final int SCANNER_STATE_END_OF_INPUT = 13;
    protected static final int SCANNER_STATE_TERMINATED = 14;
    protected static final int SCANNER_STATE_CDATA = 15;
    protected static final int SCANNER_STATE_TEXT_DECL = 16;
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    private static final String[] RECOGNIZED_FEATURES;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final Object[] PROPERTY_DEFAULTS;
    private static final boolean DEBUG_SCANNER_STATE = false;
    private static final boolean DEBUG_DISPATCHER = false;
    protected static final boolean DEBUG_CONTENT_SCANNING = false;
    protected XMLDocumentHandler fDocumentHandler;
    protected int[] fEntityStack;
    protected int fMarkupDepth;
    protected int fScannerState;
    protected boolean fInScanContent;
    protected boolean fHasExternalDTD;
    protected boolean fStandalone;
    protected QName fCurrentElement;
    protected ElementStack fElementStack;
    protected String fDocumentSystemId;
    protected boolean fNamespaces;
    protected boolean fNotifyBuiltInRefs;
    protected Dispatcher fDispatcher;
    protected Dispatcher fContentDispatcher;
    protected QName fElementQName;
    protected QName fAttributeQName;
    protected XMLAttributesImpl fAttributes;
    protected XMLString fTempString;
    protected XMLString fTempString2;
    private String[] fStrings;
    private XMLStringBuffer fStringBuffer;
    private XMLStringBuffer fStringBuffer2;
    private QName fQName;
    private final char[] fSingleChar;
    private XMLEntityManager.ExternalEntity fExternalEntity;
    
    public XMLDocumentFragmentScannerImpl() {
        this.fEntityStack = new int[4];
        this.fInScanContent = false;
        this.fElementStack = new ElementStack();
        this.fNotifyBuiltInRefs = false;
        this.fContentDispatcher = this.createContentDispatcher();
        this.fElementQName = new QName();
        this.fAttributeQName = new QName();
        this.fAttributes = new XMLAttributesImpl();
        this.fTempString = new XMLString();
        this.fTempString2 = new XMLString();
        this.fStrings = new String[3];
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fQName = new QName();
        this.fSingleChar = new char[1];
        this.fExternalEntity = new XMLEntityManager.ExternalEntity();
    }
    
    public void setInputSource(final XMLInputSource inputSource) throws IOException {
        super.fEntityManager.setEntityHandler(this);
        super.fEntityManager.startEntity("$fragment$", inputSource, false, true);
        this.fDocumentSystemId = XMLEntityManager.expandSystemId(inputSource.getSystemId());
    }
    
    public boolean scanDocument(final boolean complete) throws IOException, XNIException {
        super.fEntityManager.setEntityHandler(this);
        while (this.fDispatcher.dispatch(complete)) {
            if (!complete) {
                return true;
            }
        }
        return false;
    }
    
    public void reset(final XMLComponentManager componentManager) throws XMLConfigurationException {
        super.reset(componentManager);
        this.fDocumentSystemId = null;
        try {
            this.fNamespaces = componentManager.getFeature("http://xml.org/sax/features/namespaces");
        }
        catch (XMLConfigurationException e) {
            this.fNamespaces = true;
        }
        this.fAttributes.setNamespaces(this.fNamespaces);
        try {
            this.fNotifyBuiltInRefs = componentManager.getFeature("http://apache.org/xml/features/scanner/notify-builtin-refs");
        }
        catch (XMLConfigurationException e) {
            this.fNotifyBuiltInRefs = true;
        }
        this.fMarkupDepth = 0;
        this.fCurrentElement = null;
        this.fElementStack.clear();
        this.fHasExternalDTD = false;
        this.fStandalone = false;
        this.setScannerState(7);
        this.setDispatcher(this.fContentDispatcher);
    }
    
    public String[] getRecognizedFeatures() {
        return XMLDocumentFragmentScannerImpl.RECOGNIZED_FEATURES.clone();
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
        super.setFeature(featureId, state);
        if (featureId.startsWith("http://apache.org/xml/features/")) {
            final String feature = featureId.substring("http://apache.org/xml/features/".length());
            if (feature.equals("scanner/notify-builtin-refs")) {
                this.fNotifyBuiltInRefs = state;
            }
        }
    }
    
    public String[] getRecognizedProperties() {
        return XMLDocumentFragmentScannerImpl.RECOGNIZED_PROPERTIES.clone();
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
        super.setProperty(propertyId, value);
        if (propertyId.startsWith("http://apache.org/xml/properties/")) {
            final String property = propertyId.substring("http://apache.org/xml/properties/".length());
            if (property.equals("internal/entity-manager")) {
                super.fEntityManager = (XMLEntityManager)value;
            }
        }
    }
    
    public Boolean getFeatureDefault(final String featureId) {
        for (int i = 0; i < XMLDocumentFragmentScannerImpl.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLDocumentFragmentScannerImpl.RECOGNIZED_FEATURES[i].equals(featureId)) {
                return XMLDocumentFragmentScannerImpl.FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public Object getPropertyDefault(final String propertyId) {
        for (int i = 0; i < XMLDocumentFragmentScannerImpl.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLDocumentFragmentScannerImpl.RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return XMLDocumentFragmentScannerImpl.PROPERTY_DEFAULTS[i];
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
    
    public void startEntity(final String name, final XMLResourceIdentifier identifier, final String encoding) throws XNIException {
        if (super.fEntityDepth == this.fEntityStack.length) {
            final int[] entityarray = new int[this.fEntityStack.length * 2];
            System.arraycopy(this.fEntityStack, 0, entityarray, 0, this.fEntityStack.length);
            this.fEntityStack = entityarray;
        }
        this.fEntityStack[super.fEntityDepth] = this.fMarkupDepth;
        super.startEntity(name, identifier, encoding);
        if (this.fStandalone && super.fEntityManager.isEntityDeclInExternalSubset(name)) {
            this.reportFatalError("MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[] { name });
        }
        if (this.fDocumentHandler != null && !super.fScanningAttribute && !name.equals("[xml]")) {
            this.fDocumentHandler.startGeneralEntity(name, identifier, encoding, null);
        }
    }
    
    public void endEntity(final String name) throws XNIException {
        if (this.fInScanContent && this.fStringBuffer.length != 0 && this.fDocumentHandler != null) {
            this.fDocumentHandler.characters(this.fStringBuffer, null);
            this.fStringBuffer.length = 0;
        }
        super.endEntity(name);
        if (this.fMarkupDepth != this.fEntityStack[super.fEntityDepth]) {
            this.reportFatalError("MarkupEntityMismatch", null);
        }
        if (this.fDocumentHandler != null && !super.fScanningAttribute && !name.equals("[xml]")) {
            this.fDocumentHandler.endGeneralEntity(name, null);
        }
    }
    
    protected Dispatcher createContentDispatcher() {
        return new FragmentContentDispatcher();
    }
    
    protected void scanXMLDeclOrTextDecl(final boolean scanningTextDecl) throws IOException, XNIException {
        super.scanXMLDeclOrTextDecl(scanningTextDecl, this.fStrings);
        --this.fMarkupDepth;
        final String version = this.fStrings[0];
        final String encoding = this.fStrings[1];
        final String standalone = this.fStrings[2];
        this.fStandalone = (standalone != null && standalone.equals("yes"));
        super.fEntityManager.setStandalone(this.fStandalone);
        if (this.fDocumentHandler != null) {
            if (scanningTextDecl) {
                this.fDocumentHandler.textDecl(version, encoding, null);
            }
            else {
                this.fDocumentHandler.xmlDecl(version, encoding, standalone, null);
            }
        }
        if (encoding != null) {
            super.fEntityScanner.setEncoding(encoding);
        }
    }
    
    protected void scanPIData(final String target, final XMLString data) throws IOException, XNIException {
        super.scanPIData(target, data);
        --this.fMarkupDepth;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.processingInstruction(target, data, null);
        }
    }
    
    protected void scanComment() throws IOException, XNIException {
        this.scanComment(this.fStringBuffer);
        --this.fMarkupDepth;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.comment(this.fStringBuffer, null);
        }
    }
    
    protected boolean scanStartElement() throws IOException, XNIException {
        if (this.fNamespaces) {
            super.fEntityScanner.scanQName(this.fElementQName);
        }
        else {
            final String name = super.fEntityScanner.scanName();
            this.fElementQName.setValues(null, name, name, null);
        }
        final String rawname = this.fElementQName.rawname;
        this.fCurrentElement = this.fElementStack.pushElement(this.fElementQName);
        boolean empty = false;
        this.fAttributes.removeAllAttributes();
        while (true) {
            final boolean sawSpace = super.fEntityScanner.skipSpaces();
            final int c = super.fEntityScanner.peekChar();
            if (c == 62) {
                super.fEntityScanner.scanChar();
                break;
            }
            if (c == 47) {
                super.fEntityScanner.scanChar();
                if (!super.fEntityScanner.skipChar(62)) {
                    this.reportFatalError("ElementUnterminated", new Object[] { rawname });
                }
                empty = true;
                break;
            }
            if (!XMLChar.isNameStart(c) || !sawSpace) {
                this.reportFatalError("ElementUnterminated", new Object[] { rawname });
            }
            this.scanAttribute(this.fAttributes);
        }
        if (this.fDocumentHandler != null) {
            if (empty) {
                --this.fMarkupDepth;
                if (this.fMarkupDepth < this.fEntityStack[super.fEntityDepth - 1]) {
                    this.reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname });
                }
                this.fDocumentHandler.emptyElement(this.fElementQName, this.fAttributes, null);
                this.fElementStack.popElement(this.fElementQName);
            }
            else {
                this.fDocumentHandler.startElement(this.fElementQName, this.fAttributes, null);
            }
        }
        return empty;
    }
    
    protected void scanAttribute(final XMLAttributes attributes) throws IOException, XNIException {
        if (this.fNamespaces) {
            super.fEntityScanner.scanQName(this.fAttributeQName);
        }
        else {
            final String name = super.fEntityScanner.scanName();
            this.fAttributeQName.setValues(null, name, name, null);
        }
        super.fEntityScanner.skipSpaces();
        if (!super.fEntityScanner.skipChar(61)) {
            this.reportFatalError("EqRequiredInAttribute", new Object[] { this.fAttributeQName.rawname });
        }
        super.fEntityScanner.skipSpaces();
        final int oldLen = attributes.getLength();
        attributes.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
        if (oldLen == attributes.getLength()) {
            this.reportFatalError("AttributeNotUnique", new Object[] { this.fCurrentElement.rawname, this.fAttributeQName.rawname });
        }
        final boolean isVC = this.fHasExternalDTD && !this.fStandalone;
        this.scanAttributeValue(this.fTempString, this.fTempString2, this.fAttributeQName.rawname, attributes, oldLen, isVC);
        attributes.setValue(oldLen, this.fTempString.toString());
        attributes.setNonNormalizedValue(oldLen, this.fTempString2.toString());
        attributes.setSpecified(oldLen, true);
    }
    
    protected int scanContent() throws IOException, XNIException {
        XMLString content = this.fTempString;
        int c = super.fEntityScanner.scanContent(content);
        if (c == 13) {
            super.fEntityScanner.scanChar();
            this.fStringBuffer.clear();
            this.fStringBuffer.append(this.fTempString);
            this.fStringBuffer.append((char)c);
            content = this.fStringBuffer;
            c = -1;
        }
        if (this.fDocumentHandler != null && content.length > 0) {
            this.fDocumentHandler.characters(content, null);
        }
        if (c == 93 && this.fTempString.length == 0) {
            this.fStringBuffer.clear();
            this.fStringBuffer.append((char)super.fEntityScanner.scanChar());
            this.fInScanContent = true;
            if (super.fEntityScanner.skipChar(93)) {
                this.fStringBuffer.append(']');
                while (super.fEntityScanner.skipChar(93)) {
                    this.fStringBuffer.append(']');
                }
                if (super.fEntityScanner.skipChar(62)) {
                    this.reportFatalError("CDEndInContent", null);
                }
            }
            if (this.fDocumentHandler != null && this.fStringBuffer.length != 0) {
                this.fDocumentHandler.characters(this.fStringBuffer, null);
            }
            this.fInScanContent = false;
            c = -1;
        }
        return c;
    }
    
    protected boolean scanCDATASection(final boolean complete) throws IOException, XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startCDATA(null);
        }
        while (true) {
            this.fStringBuffer.clear();
            if (!super.fEntityScanner.scanData("]]", this.fStringBuffer)) {
                if (this.fDocumentHandler != null && this.fStringBuffer.length > 0) {
                    this.fDocumentHandler.characters(this.fStringBuffer, null);
                }
                int brackets = 2;
                while (super.fEntityScanner.skipChar(93)) {
                    ++brackets;
                }
                if (this.fDocumentHandler != null && brackets > 2) {
                    this.fStringBuffer.clear();
                    for (int i = 2; i < brackets; ++i) {
                        this.fStringBuffer.append(']');
                    }
                    this.fDocumentHandler.characters(this.fStringBuffer, null);
                }
                if (super.fEntityScanner.skipChar(62)) {
                    break;
                }
                if (this.fDocumentHandler == null) {
                    continue;
                }
                this.fStringBuffer.clear();
                this.fStringBuffer.append("]]");
                this.fDocumentHandler.characters(this.fStringBuffer, null);
            }
            else {
                if (this.fDocumentHandler != null) {
                    this.fDocumentHandler.characters(this.fStringBuffer, null);
                }
                final int c = super.fEntityScanner.peekChar();
                if (c == -1 || !XMLChar.isInvalid(c)) {
                    continue;
                }
                if (XMLChar.isHighSurrogate(c)) {
                    this.fStringBuffer.clear();
                    this.scanSurrogates(this.fStringBuffer);
                    if (this.fDocumentHandler == null) {
                        continue;
                    }
                    this.fDocumentHandler.characters(this.fStringBuffer, null);
                }
                else {
                    this.reportFatalError("InvalidCharInCDSect", new Object[] { Integer.toString(c, 16) });
                    super.fEntityScanner.scanChar();
                }
            }
        }
        --this.fMarkupDepth;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endCDATA(null);
        }
        return true;
    }
    
    protected int scanEndElement() throws IOException, XNIException {
        this.fElementStack.popElement(this.fElementQName);
        if (!super.fEntityScanner.skipString(this.fElementQName.rawname)) {
            this.reportFatalError("ETagRequired", new Object[] { this.fElementQName.rawname });
        }
        super.fEntityScanner.skipSpaces();
        if (!super.fEntityScanner.skipChar(62)) {
            this.reportFatalError("ETagUnterminated", new Object[] { this.fElementQName.rawname });
        }
        --this.fMarkupDepth;
        --this.fMarkupDepth;
        if (this.fMarkupDepth < this.fEntityStack[super.fEntityDepth - 1]) {
            this.reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname });
        }
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endElement(this.fElementQName, null);
        }
        return this.fMarkupDepth;
    }
    
    protected void scanCharReference() throws IOException, XNIException {
        this.fStringBuffer2.clear();
        final int ch = this.scanCharReferenceValue(this.fStringBuffer2, null);
        --this.fMarkupDepth;
        if (ch != -1 && this.fDocumentHandler != null) {
            if (super.fNotifyCharRefs) {
                this.fDocumentHandler.startGeneralEntity(super.fCharRefLiteral, null, null, null);
            }
            this.fDocumentHandler.characters(this.fStringBuffer2, null);
            if (super.fNotifyCharRefs) {
                this.fDocumentHandler.endGeneralEntity(super.fCharRefLiteral, null);
            }
        }
    }
    
    protected void scanEntityReference() throws IOException, XNIException {
        final String name = super.fEntityScanner.scanName();
        if (name == null) {
            this.reportFatalError("NameRequiredInReference", null);
            return;
        }
        if (!super.fEntityScanner.skipChar(59)) {
            this.reportFatalError("SemicolonRequiredInReference", new Object[] { name });
        }
        --this.fMarkupDepth;
        if (name == XMLScanner.fAmpSymbol) {
            this.handleCharacter('&', XMLScanner.fAmpSymbol);
        }
        else if (name == XMLScanner.fLtSymbol) {
            this.handleCharacter('<', XMLScanner.fLtSymbol);
        }
        else if (name == XMLScanner.fGtSymbol) {
            this.handleCharacter('>', XMLScanner.fGtSymbol);
        }
        else if (name == XMLScanner.fQuotSymbol) {
            this.handleCharacter('\"', XMLScanner.fQuotSymbol);
        }
        else if (name == XMLScanner.fAposSymbol) {
            this.handleCharacter('\'', XMLScanner.fAposSymbol);
        }
        else if (super.fEntityManager.isUnparsedEntity(name)) {
            this.reportFatalError("ReferenceToUnparsedEntity", new Object[] { name });
        }
        else {
            if (!super.fEntityManager.isDeclaredEntity(name)) {
                if (this.fHasExternalDTD && !this.fStandalone) {
                    if (super.fValidation) {
                        super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[] { name }, (short)1);
                    }
                }
                else {
                    this.reportFatalError("EntityNotDeclared", new Object[] { name });
                }
            }
            super.fEntityManager.startEntity(name, false);
        }
    }
    
    private void handleCharacter(final char c, final String entity) throws XNIException {
        if (this.fDocumentHandler != null) {
            if (this.fNotifyBuiltInRefs) {
                this.fDocumentHandler.startGeneralEntity(entity, null, null, null);
            }
            this.fSingleChar[0] = c;
            this.fTempString.setValues(this.fSingleChar, 0, 1);
            this.fDocumentHandler.characters(this.fTempString, null);
            if (this.fNotifyBuiltInRefs) {
                this.fDocumentHandler.endGeneralEntity(entity, null);
            }
        }
    }
    
    protected int handleEndElement(final QName element, final boolean isEmpty) throws XNIException {
        --this.fMarkupDepth;
        if (this.fMarkupDepth < this.fEntityStack[super.fEntityDepth - 1]) {
            this.reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname });
        }
        final QName startElement = this.fQName;
        this.fElementStack.popElement(startElement);
        if (element.rawname != startElement.rawname) {
            this.reportFatalError("ETagRequired", new Object[] { startElement.rawname });
        }
        if (this.fNamespaces) {
            element.uri = startElement.uri;
        }
        if (this.fDocumentHandler != null && !isEmpty) {
            this.fDocumentHandler.endElement(element, null);
        }
        return this.fMarkupDepth;
    }
    
    protected final void setScannerState(final int state) {
        this.fScannerState = state;
    }
    
    protected final void setDispatcher(final Dispatcher dispatcher) {
        this.fDispatcher = dispatcher;
    }
    
    protected String getScannerStateName(final int state) {
        switch (state) {
            case 4: {
                return "SCANNER_STATE_DOCTYPE";
            }
            case 6: {
                return "SCANNER_STATE_ROOT_ELEMENT";
            }
            case 1: {
                return "SCANNER_STATE_START_OF_MARKUP";
            }
            case 2: {
                return "SCANNER_STATE_COMMENT";
            }
            case 3: {
                return "SCANNER_STATE_PI";
            }
            case 7: {
                return "SCANNER_STATE_CONTENT";
            }
            case 8: {
                return "SCANNER_STATE_REFERENCE";
            }
            case 13: {
                return "SCANNER_STATE_END_OF_INPUT";
            }
            case 14: {
                return "SCANNER_STATE_TERMINATED";
            }
            case 15: {
                return "SCANNER_STATE_CDATA";
            }
            case 16: {
                return "SCANNER_STATE_TEXT_DECL";
            }
            default: {
                return "??? (" + state + ')';
            }
        }
    }
    
    public String getDispatcherName(final Dispatcher dispatcher) {
        return "null";
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/namespaces", "http://xml.org/sax/features/validation", "http://apache.org/xml/features/scanner/notify-builtin-refs", "http://apache.org/xml/features/scanner/notify-char-refs" };
        FEATURE_DEFAULTS = new Boolean[] { null, null, Boolean.FALSE, Boolean.FALSE };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager" };
        PROPERTY_DEFAULTS = new Object[] { null, null, null };
    }
    
    protected static class ElementStack
    {
        protected QName[] fElements;
        protected int fSize;
        
        public ElementStack() {
            this.fElements = new QName[10];
            for (int i = 0; i < this.fElements.length; ++i) {
                this.fElements[i] = new QName();
            }
        }
        
        public QName pushElement(final QName element) {
            if (this.fSize == this.fElements.length) {
                final QName[] array = new QName[this.fElements.length * 2];
                System.arraycopy(this.fElements, 0, array, 0, this.fSize);
                this.fElements = array;
                for (int i = this.fSize; i < this.fElements.length; ++i) {
                    this.fElements[i] = new QName();
                }
            }
            this.fElements[this.fSize].setValues(element);
            return this.fElements[this.fSize++];
        }
        
        public void popElement(final QName element) {
            final QName[] fElements = this.fElements;
            final int fSize = this.fSize - 1;
            this.fSize = fSize;
            element.setValues(fElements[fSize]);
        }
        
        public void clear() {
            this.fSize = 0;
        }
    }
    
    protected class FragmentContentDispatcher implements Dispatcher
    {
        public boolean dispatch(final boolean complete) throws IOException, XNIException {
            try {
                boolean again;
                do {
                    again = false;
                    switch (XMLDocumentFragmentScannerImpl.this.fScannerState) {
                        case 7: {
                            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(60)) {
                                XMLDocumentFragmentScannerImpl.this.setScannerState(1);
                                again = true;
                                continue;
                            }
                            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(38)) {
                                XMLDocumentFragmentScannerImpl.this.setScannerState(8);
                                again = true;
                                continue;
                            }
                            do {
                                final int c = XMLDocumentFragmentScannerImpl.this.scanContent();
                                if (c == 60) {
                                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                    XMLDocumentFragmentScannerImpl.this.setScannerState(1);
                                    break;
                                }
                                if (c == 38) {
                                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                    XMLDocumentFragmentScannerImpl.this.setScannerState(8);
                                    break;
                                }
                                if (c == -1 || !XMLChar.isInvalid(c)) {
                                    continue;
                                }
                                if (XMLChar.isHighSurrogate(c)) {
                                    XMLDocumentFragmentScannerImpl.this.fStringBuffer.clear();
                                    if (!XMLDocumentFragmentScannerImpl.this.scanSurrogates(XMLDocumentFragmentScannerImpl.this.fStringBuffer) || XMLDocumentFragmentScannerImpl.this.fDocumentHandler == null) {
                                        continue;
                                    }
                                    XMLDocumentFragmentScannerImpl.this.fDocumentHandler.characters(XMLDocumentFragmentScannerImpl.this.fStringBuffer, null);
                                }
                                else {
                                    XMLDocumentFragmentScannerImpl.this.reportFatalError("InvalidCharInContent", new Object[] { Integer.toString(c, 16) });
                                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                }
                            } while (complete);
                            continue;
                        }
                        case 1: {
                            final XMLDocumentFragmentScannerImpl this$0 = XMLDocumentFragmentScannerImpl.this;
                            ++this$0.fMarkupDepth;
                            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(63)) {
                                XMLDocumentFragmentScannerImpl.this.setScannerState(3);
                                again = true;
                                continue;
                            }
                            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(33)) {
                                if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(45)) {
                                    if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(45)) {
                                        XMLDocumentFragmentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                                    }
                                    XMLDocumentFragmentScannerImpl.this.setScannerState(2);
                                    again = true;
                                    continue;
                                }
                                if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipString("[CDATA[")) {
                                    XMLDocumentFragmentScannerImpl.this.setScannerState(15);
                                    again = true;
                                    continue;
                                }
                                if (!this.scanForDoctypeHook()) {
                                    XMLDocumentFragmentScannerImpl.this.reportFatalError("MarkupNotRecognizedInContent", null);
                                    continue;
                                }
                                continue;
                            }
                            else if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(47)) {
                                if (XMLDocumentFragmentScannerImpl.this.scanEndElement() == 0 && this.elementDepthIsZeroHook()) {
                                    return true;
                                }
                                XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                                continue;
                            }
                            else {
                                if (XMLChar.isNameStart(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                    XMLDocumentFragmentScannerImpl.this.scanStartElement();
                                    XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                                    continue;
                                }
                                XMLDocumentFragmentScannerImpl.this.reportFatalError("MarkupNotRecognizedInContent", null);
                                continue;
                            }
                            break;
                        }
                        case 8: {
                            final XMLDocumentFragmentScannerImpl this$2 = XMLDocumentFragmentScannerImpl.this;
                            ++this$2.fMarkupDepth;
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(35)) {
                                XMLDocumentFragmentScannerImpl.this.scanCharReference();
                                continue;
                            }
                            XMLDocumentFragmentScannerImpl.this.scanEntityReference();
                            continue;
                        }
                        case 6: {
                            if (this.scanRootElementHook()) {
                                return true;
                            }
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 2: {
                            XMLDocumentFragmentScannerImpl.this.scanComment();
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            continue;
                        }
                        case 3: {
                            XMLDocumentFragmentScannerImpl.this.scanPI();
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            continue;
                        }
                        case 15: {
                            XMLDocumentFragmentScannerImpl.this.scanCDATASection(complete);
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            continue;
                        }
                        case 16: {
                            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipString("<?xml")) {
                                final XMLDocumentFragmentScannerImpl this$3 = XMLDocumentFragmentScannerImpl.this;
                                ++this$3.fMarkupDepth;
                                if (XMLChar.isName(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                    XMLDocumentFragmentScannerImpl.this.fStringBuffer.clear();
                                    XMLDocumentFragmentScannerImpl.this.fStringBuffer.append("xml");
                                    while (XMLChar.isName(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                        XMLDocumentFragmentScannerImpl.this.fStringBuffer.append((char)XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar());
                                    }
                                    final String target = XMLDocumentFragmentScannerImpl.this.fSymbolTable.addSymbol(XMLDocumentFragmentScannerImpl.this.fStringBuffer.ch, XMLDocumentFragmentScannerImpl.this.fStringBuffer.offset, XMLDocumentFragmentScannerImpl.this.fStringBuffer.length);
                                    XMLDocumentFragmentScannerImpl.this.scanPIData(target, XMLDocumentFragmentScannerImpl.this.fTempString);
                                }
                                else {
                                    XMLDocumentFragmentScannerImpl.this.scanXMLDeclOrTextDecl(true);
                                }
                            }
                            XMLDocumentFragmentScannerImpl.this.fEntityManager.fCurrentEntity.mayReadChunks = true;
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            continue;
                        }
                        case 4: {
                            XMLDocumentFragmentScannerImpl.this.reportFatalError("DoctypeIllegalInContent", null);
                            continue;
                        }
                    }
                } while (complete || again);
            }
            catch (EOFException e) {
                this.endOfFileHook(e);
                return false;
            }
            return true;
        }
        
        protected boolean scanForDoctypeHook() throws IOException, XNIException {
            return false;
        }
        
        protected boolean elementDepthIsZeroHook() throws IOException, XNIException {
            return false;
        }
        
        protected boolean scanRootElementHook() throws IOException, XNIException {
            return false;
        }
        
        protected void endOfFileHook(final EOFException e) throws IOException, XNIException {
            if (XMLDocumentFragmentScannerImpl.this.fMarkupDepth != 0) {
                XMLDocumentFragmentScannerImpl.this.reportFatalError("PrematureEOF", null);
            }
        }
    }
    
    protected interface Dispatcher
    {
        boolean dispatch(final boolean p0) throws IOException, XNIException;
    }
}
