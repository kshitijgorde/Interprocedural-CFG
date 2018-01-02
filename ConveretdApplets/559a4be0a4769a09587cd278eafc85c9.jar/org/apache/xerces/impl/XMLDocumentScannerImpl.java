// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.util.XMLResourceIdentifierImpl;
import java.io.EOFException;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import java.io.IOException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.xni.parser.XMLDTDScanner;

public class XMLDocumentScannerImpl extends XMLDocumentFragmentScannerImpl
{
    protected static final int SCANNER_STATE_XML_DECL = 0;
    protected static final int SCANNER_STATE_PROLOG = 5;
    protected static final int SCANNER_STATE_TRAILING_MISC = 12;
    protected static final int SCANNER_STATE_DTD_INTERNAL_DECLS = 17;
    protected static final int SCANNER_STATE_DTD_EXTERNAL = 18;
    protected static final int SCANNER_STATE_DTD_EXTERNAL_DECLS = 19;
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String[] RECOGNIZED_FEATURES;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final Object[] PROPERTY_DEFAULTS;
    protected XMLDTDScanner fDTDScanner;
    protected ValidationManager fValidationManager;
    protected boolean fScanningDTD;
    protected String fDoctypeName;
    protected String fDoctypePublicId;
    protected String fDoctypeSystemId;
    protected NamespaceSupport fNamespaceContext;
    protected boolean fLoadExternalDTD;
    protected boolean fDisallowDoctype;
    protected boolean fSeenDoctypeDecl;
    protected Dispatcher fXMLDeclDispatcher;
    protected Dispatcher fPrologDispatcher;
    protected Dispatcher fDTDDispatcher;
    protected Dispatcher fTrailingMiscDispatcher;
    private String[] fStrings;
    private XMLString fString;
    private XMLStringBuffer fStringBuffer;
    
    public XMLDocumentScannerImpl() {
        this.fNamespaceContext = new NamespaceSupport();
        this.fLoadExternalDTD = true;
        this.fDisallowDoctype = false;
        this.fXMLDeclDispatcher = new XMLDeclDispatcher();
        this.fPrologDispatcher = new PrologDispatcher();
        this.fDTDDispatcher = new DTDDispatcher();
        this.fTrailingMiscDispatcher = new TrailingMiscDispatcher();
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
    }
    
    public void setInputSource(final XMLInputSource inputSource) throws IOException {
        super.fEntityManager.setEntityHandler(this);
        super.fEntityManager.startDocumentEntity(inputSource);
        super.fDocumentSystemId = XMLEntityManager.expandSystemId(inputSource.getSystemId());
    }
    
    public void reset(final XMLComponentManager componentManager) throws XMLConfigurationException {
        super.reset(componentManager);
        this.fDoctypeName = null;
        this.fDoctypePublicId = null;
        this.fDoctypeSystemId = null;
        this.fSeenDoctypeDecl = false;
        this.fNamespaceContext.reset();
        try {
            this.fLoadExternalDTD = componentManager.getFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd");
        }
        catch (XMLConfigurationException e) {
            this.fLoadExternalDTD = true;
        }
        try {
            this.fDisallowDoctype = componentManager.getFeature("http://apache.org/xml/features/disallow-doctype-decl");
        }
        catch (XMLConfigurationException e) {
            this.fDisallowDoctype = false;
        }
        this.fDTDScanner = (XMLDTDScanner)componentManager.getProperty("http://apache.org/xml/properties/internal/dtd-scanner");
        try {
            this.fValidationManager = (ValidationManager)componentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager");
        }
        catch (XMLConfigurationException e) {
            this.fValidationManager = null;
        }
        this.fScanningDTD = false;
        this.setScannerState(0);
        this.setDispatcher(this.fXMLDeclDispatcher);
    }
    
    public String[] getRecognizedFeatures() {
        final String[] featureIds = super.getRecognizedFeatures();
        final int length = (featureIds != null) ? featureIds.length : 0;
        final String[] combinedFeatureIds = new String[length + XMLDocumentScannerImpl.RECOGNIZED_FEATURES.length];
        if (featureIds != null) {
            System.arraycopy(featureIds, 0, combinedFeatureIds, 0, featureIds.length);
        }
        System.arraycopy(XMLDocumentScannerImpl.RECOGNIZED_FEATURES, 0, combinedFeatureIds, length, XMLDocumentScannerImpl.RECOGNIZED_FEATURES.length);
        return combinedFeatureIds;
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
        super.setFeature(featureId, state);
        if (featureId.startsWith("http://apache.org/xml/features/")) {
            final String feature = featureId.substring("http://apache.org/xml/features/".length());
            if (feature.equals("nonvalidating/load-external-dtd")) {
                this.fLoadExternalDTD = state;
                return;
            }
            if (feature.equals("disallow-doctype-decl")) {
                this.fDisallowDoctype = state;
            }
        }
    }
    
    public String[] getRecognizedProperties() {
        final String[] propertyIds = super.getRecognizedProperties();
        final int length = (propertyIds != null) ? propertyIds.length : 0;
        final String[] combinedPropertyIds = new String[length + XMLDocumentScannerImpl.RECOGNIZED_PROPERTIES.length];
        if (propertyIds != null) {
            System.arraycopy(propertyIds, 0, combinedPropertyIds, 0, propertyIds.length);
        }
        System.arraycopy(XMLDocumentScannerImpl.RECOGNIZED_PROPERTIES, 0, combinedPropertyIds, length, XMLDocumentScannerImpl.RECOGNIZED_PROPERTIES.length);
        return combinedPropertyIds;
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
        super.setProperty(propertyId, value);
        if (propertyId.startsWith("http://apache.org/xml/properties/")) {
            final String property = propertyId.substring("http://apache.org/xml/properties/".length());
            if (property.equals("internal/dtd-scanner")) {
                this.fDTDScanner = (XMLDTDScanner)value;
            }
        }
    }
    
    public Boolean getFeatureDefault(final String featureId) {
        for (int i = 0; i < XMLDocumentScannerImpl.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLDocumentScannerImpl.RECOGNIZED_FEATURES[i].equals(featureId)) {
                return XMLDocumentScannerImpl.FEATURE_DEFAULTS[i];
            }
        }
        return super.getFeatureDefault(featureId);
    }
    
    public Object getPropertyDefault(final String propertyId) {
        for (int i = 0; i < XMLDocumentScannerImpl.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLDocumentScannerImpl.RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return XMLDocumentScannerImpl.PROPERTY_DEFAULTS[i];
            }
        }
        return super.getPropertyDefault(propertyId);
    }
    
    public void startEntity(final String name, final XMLResourceIdentifier identifier, final String encoding) throws XNIException {
        super.startEntity(name, identifier, encoding);
        if (!name.equals("[xml]") && super.fEntityScanner.isExternal()) {
            this.setScannerState(16);
        }
        if (super.fDocumentHandler != null && name.equals("[xml]")) {
            super.fDocumentHandler.startDocument(super.fEntityScanner, encoding, this.fNamespaceContext, null);
        }
    }
    
    public void endEntity(final String name) throws XNIException {
        super.endEntity(name);
        if (super.fDocumentHandler != null && name.equals("[xml]")) {
            super.fDocumentHandler.endDocument(null);
        }
    }
    
    protected Dispatcher createContentDispatcher() {
        return new ContentDispatcher();
    }
    
    protected boolean scanDoctypeDecl() throws IOException, XNIException {
        if (!super.fEntityScanner.skipSpaces()) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ROOT_ELEMENT_TYPE_IN_DOCTYPEDECL", null);
        }
        this.fDoctypeName = super.fEntityScanner.scanName();
        if (this.fDoctypeName == null) {
            this.reportFatalError("MSG_ROOT_ELEMENT_TYPE_REQUIRED", null);
        }
        if (super.fEntityScanner.skipSpaces()) {
            this.scanExternalID(this.fStrings, false);
            this.fDoctypeSystemId = this.fStrings[0];
            this.fDoctypePublicId = this.fStrings[1];
            super.fEntityScanner.skipSpaces();
        }
        super.fHasExternalDTD = (this.fDoctypeSystemId != null);
        if (super.fDocumentHandler != null) {
            super.fDocumentHandler.doctypeDecl(this.fDoctypeName, this.fDoctypePublicId, this.fDoctypeSystemId, null);
        }
        boolean internalSubset = true;
        if (!super.fEntityScanner.skipChar(91)) {
            internalSubset = false;
            super.fEntityScanner.skipSpaces();
            if (!super.fEntityScanner.skipChar(62)) {
                this.reportFatalError("DoctypedeclUnterminated", new Object[] { this.fDoctypeName });
            }
            --super.fMarkupDepth;
        }
        return internalSubset;
    }
    
    protected String getScannerStateName(final int state) {
        switch (state) {
            case 0: {
                return "SCANNER_STATE_XML_DECL";
            }
            case 5: {
                return "SCANNER_STATE_PROLOG";
            }
            case 12: {
                return "SCANNER_STATE_TRAILING_MISC";
            }
            case 17: {
                return "SCANNER_STATE_DTD_INTERNAL_DECLS";
            }
            case 18: {
                return "SCANNER_STATE_DTD_EXTERNAL";
            }
            case 19: {
                return "SCANNER_STATE_DTD_EXTERNAL_DECLS";
            }
            default: {
                return super.getScannerStateName(state);
            }
        }
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://apache.org/xml/features/nonvalidating/load-external-dtd", "http://apache.org/xml/features/disallow-doctype-decl" };
        FEATURE_DEFAULTS = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/dtd-scanner", "http://apache.org/xml/properties/internal/validation-manager" };
        PROPERTY_DEFAULTS = new Object[] { null, null };
    }
    
    protected final class XMLDeclDispatcher implements Dispatcher
    {
        public boolean dispatch(final boolean complete) throws IOException, XNIException {
            XMLDocumentScannerImpl.this.setScannerState(5);
            XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fPrologDispatcher);
            try {
                if (XMLDocumentScannerImpl.this.fEntityScanner.skipString("<?xml")) {
                    final XMLDocumentScannerImpl this$0 = XMLDocumentScannerImpl.this;
                    ++this$0.fMarkupDepth;
                    if (XMLChar.isName(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                        XMLDocumentScannerImpl.this.fStringBuffer.clear();
                        XMLDocumentScannerImpl.this.fStringBuffer.append("xml");
                        while (XMLChar.isName(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                            XMLDocumentScannerImpl.this.fStringBuffer.append((char)XMLDocumentScannerImpl.this.fEntityScanner.scanChar());
                        }
                        final String target = XMLDocumentScannerImpl.this.fSymbolTable.addSymbol(XMLDocumentScannerImpl.this.fStringBuffer.ch, XMLDocumentScannerImpl.this.fStringBuffer.offset, XMLDocumentScannerImpl.this.fStringBuffer.length);
                        XMLDocumentScannerImpl.this.scanPIData(target, XMLDocumentScannerImpl.this.fString);
                    }
                    else {
                        XMLDocumentScannerImpl.this.scanXMLDeclOrTextDecl(false);
                    }
                }
                return XMLDocumentScannerImpl.this.fEntityManager.fCurrentEntity.mayReadChunks = true;
            }
            catch (EOFException e) {
                XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                return false;
            }
        }
    }
    
    protected final class PrologDispatcher implements Dispatcher
    {
        public boolean dispatch(final boolean complete) throws IOException, XNIException {
            try {
                boolean again;
                do {
                    again = false;
                    switch (XMLDocumentScannerImpl.this.fScannerState) {
                        case 5: {
                            XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(60)) {
                                XMLDocumentScannerImpl.this.setScannerState(1);
                                again = true;
                                continue;
                            }
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(38)) {
                                XMLDocumentScannerImpl.this.setScannerState(8);
                                again = true;
                                continue;
                            }
                            XMLDocumentScannerImpl.this.setScannerState(7);
                            again = true;
                            continue;
                        }
                        case 1: {
                            final XMLDocumentScannerImpl this$0 = XMLDocumentScannerImpl.this;
                            ++this$0.fMarkupDepth;
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(63)) {
                                XMLDocumentScannerImpl.this.setScannerState(3);
                                again = true;
                                continue;
                            }
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(33)) {
                                if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(45)) {
                                    if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(45)) {
                                        XMLDocumentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                                    }
                                    XMLDocumentScannerImpl.this.setScannerState(2);
                                    again = true;
                                    continue;
                                }
                                if (XMLDocumentScannerImpl.this.fEntityScanner.skipString("DOCTYPE")) {
                                    XMLDocumentScannerImpl.this.setScannerState(4);
                                    again = true;
                                    continue;
                                }
                                XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInProlog", null);
                                continue;
                            }
                            else {
                                if (XMLChar.isNameStart(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                    XMLDocumentScannerImpl.this.setScannerState(6);
                                    XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fContentDispatcher);
                                    return true;
                                }
                                XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInProlog", null);
                                continue;
                            }
                            break;
                        }
                        case 4: {
                            if (XMLDocumentScannerImpl.this.fDisallowDoctype) {
                                XMLDocumentScannerImpl.this.reportFatalError("DoctypeNotAllowed", null);
                            }
                            if (XMLDocumentScannerImpl.this.fSeenDoctypeDecl) {
                                XMLDocumentScannerImpl.this.reportFatalError("AlreadySeenDoctype", null);
                            }
                            XMLDocumentScannerImpl.this.fSeenDoctypeDecl = true;
                            if (XMLDocumentScannerImpl.this.scanDoctypeDecl()) {
                                XMLDocumentScannerImpl.this.setScannerState(17);
                                XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fDTDDispatcher);
                                return true;
                            }
                            if (XMLDocumentScannerImpl.this.fDoctypeSystemId != null && (XMLDocumentScannerImpl.this.fValidation || XMLDocumentScannerImpl.this.fLoadExternalDTD) && (XMLDocumentScannerImpl.this.fValidationManager == null || !XMLDocumentScannerImpl.this.fValidationManager.isCachedDTD())) {
                                XMLDocumentScannerImpl.this.setScannerState(18);
                                XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fDTDDispatcher);
                                return true;
                            }
                            XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(null);
                            XMLDocumentScannerImpl.this.setScannerState(5);
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 2: {
                            XMLDocumentScannerImpl.this.scanComment();
                            XMLDocumentScannerImpl.this.setScannerState(5);
                            continue;
                        }
                        case 3: {
                            XMLDocumentScannerImpl.this.scanPI();
                            XMLDocumentScannerImpl.this.setScannerState(5);
                            continue;
                        }
                        case 7: {
                            XMLDocumentScannerImpl.this.reportFatalError("ContentIllegalInProlog", null);
                            XMLDocumentScannerImpl.this.fEntityScanner.scanChar();
                        }
                        case 8: {
                            XMLDocumentScannerImpl.this.reportFatalError("ReferenceIllegalInProlog", null);
                            continue;
                        }
                    }
                } while (complete || again);
                if (complete) {
                    if (XMLDocumentScannerImpl.this.fEntityScanner.scanChar() != 60) {
                        XMLDocumentScannerImpl.this.reportFatalError("RootElementRequired", null);
                    }
                    XMLDocumentScannerImpl.this.setScannerState(6);
                    XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fContentDispatcher);
                }
            }
            catch (EOFException e) {
                XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                return false;
            }
            return true;
        }
    }
    
    protected final class DTDDispatcher implements Dispatcher
    {
        public boolean dispatch(final boolean complete) throws IOException, XNIException {
            XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(null);
            try {
                final XMLResourceIdentifierImpl resourceIdentifier = new XMLResourceIdentifierImpl();
                boolean again;
                do {
                    again = false;
                    switch (XMLDocumentScannerImpl.this.fScannerState) {
                        case 0: {
                            final boolean completeDTD = true;
                            final boolean moreToScan = XMLDocumentScannerImpl.this.fDTDScanner.scanDTDInternalSubset(completeDTD, XMLDocumentScannerImpl.this.fStandalone, XMLDocumentScannerImpl.this.fHasExternalDTD && XMLDocumentScannerImpl.this.fLoadExternalDTD);
                            if (moreToScan) {
                                continue;
                            }
                            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(93)) {
                                XMLDocumentScannerImpl.this.reportFatalError("EXPECTED_SQUARE_BRACKET_TO_CLOSE_INTERNAL_SUBSET", null);
                            }
                            XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(62)) {
                                XMLDocumentScannerImpl.this.reportFatalError("DoctypedeclUnterminated", new Object[] { XMLDocumentScannerImpl.this.fDoctypeName });
                            }
                            final XMLDocumentScannerImpl this$0 = XMLDocumentScannerImpl.this;
                            --this$0.fMarkupDepth;
                            if (XMLDocumentScannerImpl.this.fDoctypeSystemId != null && (XMLDocumentScannerImpl.this.fValidation || XMLDocumentScannerImpl.this.fLoadExternalDTD) && (XMLDocumentScannerImpl.this.fValidationManager == null || !XMLDocumentScannerImpl.this.fValidationManager.isCachedDTD())) {
                                XMLDocumentScannerImpl.this.setScannerState(18);
                                continue;
                            }
                            XMLDocumentScannerImpl.this.setScannerState(5);
                            XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fPrologDispatcher);
                            XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                            return true;
                        }
                        case 1: {
                            resourceIdentifier.setValues(XMLDocumentScannerImpl.this.fDoctypePublicId, XMLDocumentScannerImpl.this.fDoctypeSystemId, null, null);
                            final XMLInputSource xmlInputSource = XMLDocumentScannerImpl.this.fEntityManager.resolveEntity(resourceIdentifier);
                            XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(xmlInputSource);
                            XMLDocumentScannerImpl.this.setScannerState(19);
                            again = true;
                            continue;
                        }
                        case 2: {
                            final boolean completeDTD = true;
                            final boolean moreToScan = XMLDocumentScannerImpl.this.fDTDScanner.scanDTDExternalSubset(completeDTD);
                            if (!moreToScan) {
                                XMLDocumentScannerImpl.this.setScannerState(5);
                                XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fPrologDispatcher);
                                XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                                return true;
                            }
                            continue;
                        }
                        default: {
                            throw new XNIException("DTDDispatcher#dispatch: scanner state=" + XMLDocumentScannerImpl.this.fScannerState + " (" + XMLDocumentScannerImpl.this.getScannerStateName(XMLDocumentScannerImpl.this.fScannerState) + ')');
                        }
                    }
                } while (complete || again);
            }
            catch (EOFException e) {
                XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                return false;
            }
            finally {
                XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
            }
            return true;
        }
    }
    
    protected class ContentDispatcher extends FragmentContentDispatcher
    {
        protected boolean scanForDoctypeHook() throws IOException, XNIException {
            if (XMLDocumentScannerImpl.this.fEntityScanner.skipString("DOCTYPE")) {
                XMLDocumentScannerImpl.this.setScannerState(4);
                return true;
            }
            return false;
        }
        
        protected boolean elementDepthIsZeroHook() throws IOException, XNIException {
            XMLDocumentScannerImpl.this.setScannerState(12);
            XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fTrailingMiscDispatcher);
            return true;
        }
        
        protected boolean scanRootElementHook() throws IOException, XNIException {
            if (XMLDocumentScannerImpl.this.scanStartElement()) {
                XMLDocumentScannerImpl.this.setScannerState(12);
                XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fTrailingMiscDispatcher);
                return true;
            }
            return false;
        }
        
        protected void endOfFileHook(final EOFException e) throws IOException, XNIException {
            XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
        }
    }
    
    protected final class TrailingMiscDispatcher implements Dispatcher
    {
        public boolean dispatch(final boolean complete) throws IOException, XNIException {
            try {
                boolean again;
                do {
                    again = false;
                    switch (XMLDocumentScannerImpl.this.fScannerState) {
                        case 12: {
                            XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(60)) {
                                XMLDocumentScannerImpl.this.setScannerState(1);
                                again = true;
                                continue;
                            }
                            XMLDocumentScannerImpl.this.setScannerState(7);
                            again = true;
                            continue;
                        }
                        case 1: {
                            final XMLDocumentScannerImpl this$0 = XMLDocumentScannerImpl.this;
                            ++this$0.fMarkupDepth;
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(63)) {
                                XMLDocumentScannerImpl.this.setScannerState(3);
                                again = true;
                                continue;
                            }
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(33)) {
                                XMLDocumentScannerImpl.this.setScannerState(2);
                                again = true;
                                continue;
                            }
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(47)) {
                                XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                again = true;
                                continue;
                            }
                            if (XMLChar.isNameStart(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                XMLDocumentScannerImpl.this.scanStartElement();
                                XMLDocumentScannerImpl.this.setScannerState(7);
                                continue;
                            }
                            XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                            continue;
                        }
                        case 7: {
                            final int ch = XMLDocumentScannerImpl.this.fEntityScanner.peekChar();
                            if (ch == -1) {
                                XMLDocumentScannerImpl.this.setScannerState(14);
                                return false;
                            }
                            XMLDocumentScannerImpl.this.reportFatalError("ContentIllegalInTrailingMisc", null);
                            XMLDocumentScannerImpl.this.fEntityScanner.scanChar();
                            XMLDocumentScannerImpl.this.setScannerState(12);
                            continue;
                        }
                        case 14: {
                            return false;
                        }
                        default: {
                            continue;
                        }
                        case 3: {
                            XMLDocumentScannerImpl.this.scanPI();
                            XMLDocumentScannerImpl.this.setScannerState(12);
                            continue;
                        }
                        case 2: {
                            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipString("--")) {
                                XMLDocumentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                            }
                            XMLDocumentScannerImpl.this.scanComment();
                            XMLDocumentScannerImpl.this.setScannerState(12);
                            continue;
                        }
                        case 8: {
                            XMLDocumentScannerImpl.this.reportFatalError("ReferenceIllegalInTrailingMisc", null);
                            XMLDocumentScannerImpl.this.setScannerState(12);
                            continue;
                        }
                    }
                } while (complete || again);
            }
            catch (EOFException e) {
                if (XMLDocumentScannerImpl.this.fMarkupDepth != 0) {
                    XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                    return false;
                }
                XMLDocumentScannerImpl.this.setScannerState(14);
                return false;
            }
            return true;
        }
    }
}
