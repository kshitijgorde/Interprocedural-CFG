// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLDTDScanner;

public class XMLDTDScannerImpl extends XMLScanner implements XMLDTDScanner, XMLComponent, XMLEntityHandler
{
    protected static final int SCANNER_STATE_END_OF_INPUT = 0;
    protected static final int SCANNER_STATE_TEXT_DECL = 1;
    protected static final int SCANNER_STATE_MARKUP_DECL = 2;
    private static final String[] RECOGNIZED_FEATURES;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final Object[] PROPERTY_DEFAULTS;
    private static final boolean DEBUG_SCANNER_STATE = false;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected int fScannerState;
    protected boolean fStandalone;
    protected boolean fSeenExternalDTD;
    protected boolean fSeenExternalPE;
    private boolean fStartDTDCalled;
    private XMLAttributesImpl fAttributes;
    private int[] fContentStack;
    private int fContentDepth;
    private int[] fPEStack;
    private boolean[] fPEReport;
    private int fPEDepth;
    private int fMarkUpDepth;
    private int fExtEntityDepth;
    private int fIncludeSectDepth;
    private String[] fStrings;
    private XMLString fString;
    private XMLStringBuffer fStringBuffer;
    private XMLStringBuffer fStringBuffer2;
    private XMLString fLiteral;
    private XMLString fLiteral2;
    private String[] fEnumeration;
    private int fEnumerationCount;
    private XMLStringBuffer fIgnoreConditionalBuffer;
    
    public XMLDTDScannerImpl() {
        this.fAttributes = new XMLAttributesImpl();
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer(128);
    }
    
    public XMLDTDScannerImpl(final SymbolTable symbolTable, final XMLErrorReporter errorReporter, final XMLEntityManager entityManager) {
        this.fAttributes = new XMLAttributesImpl();
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer(128);
        super.fSymbolTable = symbolTable;
        super.fErrorReporter = errorReporter;
        (super.fEntityManager = entityManager).setProperty("http://apache.org/xml/properties/internal/symbol-table", super.fSymbolTable);
    }
    
    public void setInputSource(final XMLInputSource inputSource) throws IOException {
        if (inputSource == null) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startDTD(null, null);
                this.fDTDHandler.endDTD(null);
            }
            return;
        }
        super.fEntityManager.setEntityHandler(this);
        super.fEntityManager.startDTDEntity(inputSource);
    }
    
    public boolean scanDTDExternalSubset(final boolean complete) throws IOException, XNIException {
        super.fEntityManager.setEntityHandler(this);
        if (this.fScannerState == 1) {
            this.fSeenExternalDTD = true;
            final boolean textDecl = this.scanTextDecl();
            if (this.fScannerState == 0) {
                return false;
            }
            this.setScannerState(2);
            if (textDecl && !complete) {
                return true;
            }
        }
        while (this.scanDecls(complete)) {
            if (!complete) {
                return true;
            }
        }
        return false;
    }
    
    public boolean scanDTDInternalSubset(final boolean complete, final boolean standalone, final boolean hasExternalSubset) throws IOException, XNIException {
        super.fEntityManager.setEntityHandler(this);
        this.fStandalone = standalone;
        if (this.fScannerState == 1) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startDTD(super.fEntityScanner, null);
                this.fStartDTDCalled = true;
            }
            this.setScannerState(2);
        }
        while (this.scanDecls(complete)) {
            if (!complete) {
                return true;
            }
        }
        if (this.fDTDHandler != null && !hasExternalSubset) {
            this.fDTDHandler.endDTD(null);
        }
        this.setScannerState(1);
        return false;
    }
    
    public void reset(final XMLComponentManager componentManager) throws XMLConfigurationException {
        super.reset(componentManager);
        this.init();
    }
    
    public void reset() {
        super.reset();
        this.init();
    }
    
    public String[] getRecognizedFeatures() {
        return XMLDTDScannerImpl.RECOGNIZED_FEATURES.clone();
    }
    
    public String[] getRecognizedProperties() {
        return XMLDTDScannerImpl.RECOGNIZED_PROPERTIES.clone();
    }
    
    public Boolean getFeatureDefault(final String featureId) {
        for (int i = 0; i < XMLDTDScannerImpl.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLDTDScannerImpl.RECOGNIZED_FEATURES[i].equals(featureId)) {
                return XMLDTDScannerImpl.FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public Object getPropertyDefault(final String propertyId) {
        for (int i = 0; i < XMLDTDScannerImpl.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLDTDScannerImpl.RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return XMLDTDScannerImpl.PROPERTY_DEFAULTS[i];
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
    
    public void startEntity(final String name, final XMLResourceIdentifier identifier, final String encoding) throws XNIException {
        super.startEntity(name, identifier, encoding);
        final boolean dtdEntity = name.equals("[dtd]");
        if (dtdEntity) {
            if (this.fDTDHandler != null && !this.fStartDTDCalled) {
                this.fDTDHandler.startDTD(super.fEntityScanner, null);
            }
            this.fDTDHandler.startExternalSubset(super.fEntityScanner, null);
            super.fEntityManager.startExternalSubset();
            ++this.fExtEntityDepth;
        }
        else if (name.charAt(0) == '%') {
            this.pushPEStack(this.fMarkUpDepth, super.fReportEntity);
            if (super.fEntityScanner.isExternal()) {
                ++this.fExtEntityDepth;
            }
        }
        if (this.fDTDHandler != null && !dtdEntity && super.fReportEntity) {
            this.fDTDHandler.startParameterEntity(name, identifier, encoding, null);
        }
    }
    
    public void endEntity(final String name) throws XNIException {
        super.endEntity(name);
        if (this.fScannerState == 0) {
            return;
        }
        boolean reportEntity = super.fReportEntity;
        if (name.startsWith("%")) {
            reportEntity = this.peekReportEntity();
            final int startMarkUpDepth = this.popPEStack();
            if (startMarkUpDepth == 0 && startMarkUpDepth < this.fMarkUpDepth) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ILL_FORMED_PARAMETER_ENTITY_WHEN_USED_IN_DECL", new Object[] { super.fEntityManager.fCurrentEntity.name }, (short)2);
            }
            if (startMarkUpDepth != this.fMarkUpDepth) {
                reportEntity = false;
                if (super.fValidation) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ImproperDeclarationNesting", new Object[] { name }, (short)1);
                }
            }
            if (super.fEntityScanner.isExternal()) {
                --this.fExtEntityDepth;
            }
        }
        final boolean dtdEntity = name.equals("[dtd]");
        if (this.fDTDHandler != null && !dtdEntity && reportEntity) {
            this.fDTDHandler.endParameterEntity(name, null);
        }
        if (dtdEntity) {
            if (this.fIncludeSectDepth != 0) {
                this.reportFatalError("IncludeSectUnterminated", null);
            }
            this.fScannerState = 0;
            super.fEntityManager.endExternalSubset();
            if (this.fDTDHandler != null) {
                this.fDTDHandler.endExternalSubset(null);
                this.fDTDHandler.endDTD(null);
            }
            --this.fExtEntityDepth;
        }
    }
    
    protected final void setScannerState(final int state) {
        this.fScannerState = state;
    }
    
    private static String getScannerStateName(final int state) {
        return "??? (" + state + ')';
    }
    
    protected final boolean scanningInternalSubset() {
        return this.fExtEntityDepth == 0;
    }
    
    protected void startPE(final String name, final boolean literal) throws IOException, XNIException {
        final int depth = this.fPEDepth;
        if (super.fValidation && !super.fEntityManager.isDeclaredEntity("%" + name)) {
            super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[] { name }, (short)1);
        }
        super.fEntityManager.startEntity(super.fSymbolTable.addSymbol("%" + name), literal);
        if (depth != this.fPEDepth && super.fEntityScanner.isExternal()) {
            this.scanTextDecl();
        }
    }
    
    protected final boolean scanTextDecl() throws IOException, XNIException {
        boolean textDecl = false;
        if (super.fEntityScanner.skipString("<?xml")) {
            ++this.fMarkUpDepth;
            if (XMLChar.isName(super.fEntityScanner.peekChar())) {
                this.fStringBuffer.clear();
                this.fStringBuffer.append("xml");
                while (XMLChar.isName(super.fEntityScanner.peekChar())) {
                    this.fStringBuffer.append((char)super.fEntityScanner.scanChar());
                }
                final String target = super.fSymbolTable.addSymbol(this.fStringBuffer.ch, this.fStringBuffer.offset, this.fStringBuffer.length);
                this.scanPIData(target, this.fString);
            }
            else {
                String version = null;
                String encoding = null;
                this.scanXMLDeclOrTextDecl(true, this.fStrings);
                textDecl = true;
                --this.fMarkUpDepth;
                version = this.fStrings[0];
                encoding = this.fStrings[1];
                super.fEntityScanner.setEncoding(encoding);
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.textDecl(version, encoding, null);
                }
            }
        }
        super.fEntityManager.fCurrentEntity.mayReadChunks = true;
        return textDecl;
    }
    
    protected final void scanPIData(final String target, final XMLString data) throws IOException, XNIException {
        super.scanPIData(target, data);
        --this.fMarkUpDepth;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.processingInstruction(target, data, null);
        }
    }
    
    protected final void scanComment() throws IOException, XNIException {
        super.fReportEntity = false;
        this.scanComment(this.fStringBuffer);
        --this.fMarkUpDepth;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.comment(this.fStringBuffer, null);
        }
        super.fReportEntity = true;
    }
    
    protected final void scanElementDecl() throws IOException, XNIException {
        super.fReportEntity = false;
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL", null);
        }
        final String name = super.fEntityScanner.scanName();
        if (name == null) {
            this.reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL", null);
        }
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL", new Object[] { name });
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.startContentModel(name, null);
        }
        String contentModel = null;
        super.fReportEntity = true;
        if (super.fEntityScanner.skipString("EMPTY")) {
            contentModel = "EMPTY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.empty(null);
            }
        }
        else if (super.fEntityScanner.skipString("ANY")) {
            contentModel = "ANY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.any(null);
            }
        }
        else {
            if (!super.fEntityScanner.skipChar(40)) {
                this.reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[] { name });
            }
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.startGroup(null);
            }
            this.fStringBuffer.clear();
            this.fStringBuffer.append('(');
            ++this.fMarkUpDepth;
            this.skipSeparator(false, !this.scanningInternalSubset());
            if (super.fEntityScanner.skipString("#PCDATA")) {
                this.scanMixed(name);
            }
            else {
                this.scanChildren(name);
            }
            contentModel = this.fStringBuffer.toString();
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.endContentModel(null);
        }
        this.skipSeparator(super.fReportEntity = false, !this.scanningInternalSubset());
        if (!super.fEntityScanner.skipChar(62)) {
            this.reportFatalError("ElementDeclUnterminated", new Object[] { name });
        }
        super.fReportEntity = true;
        --this.fMarkUpDepth;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.elementDecl(name, contentModel, null);
        }
    }
    
    private final void scanMixed(final String elName) throws IOException, XNIException {
        String childName = null;
        this.fStringBuffer.append("#PCDATA");
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.pcdata(null);
        }
        this.skipSeparator(false, !this.scanningInternalSubset());
        while (super.fEntityScanner.skipChar(124)) {
            this.fStringBuffer.append('|');
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.separator((short)0, null);
            }
            this.skipSeparator(false, !this.scanningInternalSubset());
            childName = super.fEntityScanner.scanName();
            if (childName == null) {
                this.reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT", new Object[] { elName });
            }
            this.fStringBuffer.append(childName);
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.element(childName, null);
            }
            this.skipSeparator(false, !this.scanningInternalSubset());
        }
        if (super.fEntityScanner.skipString(")*")) {
            this.fStringBuffer.append(")*");
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.endGroup(null);
                this.fDTDContentModelHandler.occurrence((short)3, null);
            }
        }
        else if (childName != null) {
            this.reportFatalError("MixedContentUnterminated", new Object[] { elName });
        }
        else if (super.fEntityScanner.skipChar(41)) {
            this.fStringBuffer.append(')');
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.endGroup(null);
            }
        }
        else {
            this.reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[] { elName });
        }
        --this.fMarkUpDepth;
    }
    
    private final void scanChildren(final String elName) throws IOException, XNIException {
        this.pushContentStack(this.fContentDepth = 0);
        int currentOp = 0;
        while (true) {
            if (super.fEntityScanner.skipChar(40)) {
                ++this.fMarkUpDepth;
                this.fStringBuffer.append('(');
                if (this.fDTDContentModelHandler != null) {
                    this.fDTDContentModelHandler.startGroup(null);
                }
                this.pushContentStack(currentOp);
                currentOp = 0;
                this.skipSeparator(false, !this.scanningInternalSubset());
            }
            else {
                this.skipSeparator(false, !this.scanningInternalSubset());
                final String childName = super.fEntityScanner.scanName();
                if (childName == null) {
                    this.reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[] { elName });
                    return;
                }
                if (this.fDTDContentModelHandler != null) {
                    this.fDTDContentModelHandler.element(childName, null);
                }
                this.fStringBuffer.append(childName);
                int c = super.fEntityScanner.peekChar();
                if (c == 63 || c == 42 || c == 43) {
                    if (this.fDTDContentModelHandler != null) {
                        short oc;
                        if (c == 63) {
                            oc = 2;
                        }
                        else if (c == 42) {
                            oc = 3;
                        }
                        else {
                            oc = 4;
                        }
                        this.fDTDContentModelHandler.occurrence(oc, null);
                    }
                    super.fEntityScanner.scanChar();
                    this.fStringBuffer.append((char)c);
                }
                while (true) {
                    this.skipSeparator(false, !this.scanningInternalSubset());
                    c = super.fEntityScanner.peekChar();
                    if (c == 44 && currentOp != 124) {
                        currentOp = c;
                        if (this.fDTDContentModelHandler != null) {
                            this.fDTDContentModelHandler.separator((short)1, null);
                        }
                        super.fEntityScanner.scanChar();
                        this.fStringBuffer.append(',');
                        break;
                    }
                    if (c == 124 && currentOp != 44) {
                        currentOp = c;
                        if (this.fDTDContentModelHandler != null) {
                            this.fDTDContentModelHandler.separator((short)0, null);
                        }
                        super.fEntityScanner.scanChar();
                        this.fStringBuffer.append('|');
                        break;
                    }
                    if (c != 41) {
                        this.reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[] { elName });
                    }
                    if (this.fDTDContentModelHandler != null) {
                        this.fDTDContentModelHandler.endGroup(null);
                    }
                    currentOp = this.popContentStack();
                    if (super.fEntityScanner.skipString(")?")) {
                        this.fStringBuffer.append(")?");
                        if (this.fDTDContentModelHandler != null) {
                            final short oc = 2;
                            this.fDTDContentModelHandler.occurrence(oc, null);
                        }
                    }
                    else if (super.fEntityScanner.skipString(")+")) {
                        this.fStringBuffer.append(")+");
                        if (this.fDTDContentModelHandler != null) {
                            final short oc = 4;
                            this.fDTDContentModelHandler.occurrence(oc, null);
                        }
                    }
                    else if (super.fEntityScanner.skipString(")*")) {
                        this.fStringBuffer.append(")*");
                        if (this.fDTDContentModelHandler != null) {
                            final short oc = 3;
                            this.fDTDContentModelHandler.occurrence(oc, null);
                        }
                    }
                    else {
                        super.fEntityScanner.scanChar();
                        this.fStringBuffer.append(')');
                    }
                    --this.fMarkUpDepth;
                    if (this.fContentDepth == 0) {
                        return;
                    }
                }
                this.skipSeparator(false, !this.scanningInternalSubset());
            }
        }
    }
    
    protected final void scanAttlistDecl() throws IOException, XNIException {
        super.fReportEntity = false;
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL", null);
        }
        final String elName = super.fEntityScanner.scanName();
        if (elName == null) {
            this.reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL", null);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startAttlist(elName, null);
        }
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            if (super.fEntityScanner.skipChar(62)) {
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endAttlist(null);
                }
                --this.fMarkUpDepth;
                return;
            }
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF", new Object[] { elName });
        }
        while (!super.fEntityScanner.skipChar(62)) {
            final String name = super.fEntityScanner.scanName();
            if (name == null) {
                this.reportFatalError("AttNameRequiredInAttDef", new Object[] { elName });
            }
            if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF", new Object[] { elName, name });
            }
            final String type = this.scanAttType(elName, name);
            if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF", new Object[] { elName, name });
            }
            final String defaultType = this.scanAttDefaultDecl(elName, name, type, this.fLiteral, this.fLiteral2);
            if (this.fDTDHandler != null) {
                String[] enum1 = null;
                if (this.fEnumerationCount != 0) {
                    enum1 = new String[this.fEnumerationCount];
                    System.arraycopy(this.fEnumeration, 0, enum1, 0, this.fEnumerationCount);
                }
                if (defaultType != null && (defaultType.equals("#REQUIRED") || defaultType.equals("#IMPLIED"))) {
                    this.fDTDHandler.attributeDecl(elName, name, type, enum1, defaultType, null, null, null);
                }
                else {
                    this.fDTDHandler.attributeDecl(elName, name, type, enum1, defaultType, this.fLiteral, this.fLiteral2, null);
                }
            }
            this.skipSeparator(false, !this.scanningInternalSubset());
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endAttlist(null);
        }
        --this.fMarkUpDepth;
        super.fReportEntity = true;
    }
    
    private final String scanAttType(final String elName, final String atName) throws IOException, XNIException {
        String type = null;
        this.fEnumerationCount = 0;
        if (super.fEntityScanner.skipString("CDATA")) {
            type = "CDATA";
        }
        else if (super.fEntityScanner.skipString("IDREFS")) {
            type = "IDREFS";
        }
        else if (super.fEntityScanner.skipString("IDREF")) {
            type = "IDREF";
        }
        else if (super.fEntityScanner.skipString("ID")) {
            type = "ID";
        }
        else if (super.fEntityScanner.skipString("ENTITY")) {
            type = "ENTITY";
        }
        else if (super.fEntityScanner.skipString("ENTITIES")) {
            type = "ENTITIES";
        }
        else if (super.fEntityScanner.skipString("NMTOKENS")) {
            type = "NMTOKENS";
        }
        else if (super.fEntityScanner.skipString("NMTOKEN")) {
            type = "NMTOKEN";
        }
        else if (super.fEntityScanner.skipString("NOTATION")) {
            type = "NOTATION";
            if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                this.reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE", new Object[] { elName, atName });
            }
            int c = super.fEntityScanner.scanChar();
            if (c != 40) {
                this.reportFatalError("MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE", new Object[] { elName, atName });
            }
            ++this.fMarkUpDepth;
            do {
                this.skipSeparator(false, !this.scanningInternalSubset());
                final String aName = super.fEntityScanner.scanName();
                if (aName == null) {
                    this.reportFatalError("MSG_NAME_REQUIRED_IN_NOTATIONTYPE", new Object[] { elName, atName });
                }
                this.ensureEnumerationSize(this.fEnumerationCount + 1);
                this.fEnumeration[this.fEnumerationCount++] = aName;
                this.skipSeparator(false, !this.scanningInternalSubset());
                c = super.fEntityScanner.scanChar();
            } while (c == 124);
            if (c != 41) {
                this.reportFatalError("NotationTypeUnterminated", new Object[] { elName, atName });
            }
            --this.fMarkUpDepth;
        }
        else {
            type = "ENUMERATION";
            int c = super.fEntityScanner.scanChar();
            if (c != 40) {
                this.reportFatalError("AttTypeRequiredInAttDef", new Object[] { elName, atName });
            }
            ++this.fMarkUpDepth;
            do {
                this.skipSeparator(false, !this.scanningInternalSubset());
                final String token = super.fEntityScanner.scanNmtoken();
                if (token == null) {
                    this.reportFatalError("MSG_NMTOKEN_REQUIRED_IN_ENUMERATION", new Object[] { elName, atName });
                }
                this.ensureEnumerationSize(this.fEnumerationCount + 1);
                this.fEnumeration[this.fEnumerationCount++] = token;
                this.skipSeparator(false, !this.scanningInternalSubset());
                c = super.fEntityScanner.scanChar();
            } while (c == 124);
            if (c != 41) {
                this.reportFatalError("EnumerationUnterminated", new Object[] { elName, atName });
            }
            --this.fMarkUpDepth;
        }
        return type;
    }
    
    protected final String scanAttDefaultDecl(final String elName, final String atName, final String type, final XMLString defaultVal, final XMLString nonNormalizedDefaultVal) throws IOException, XNIException {
        String defaultType = null;
        this.fString.clear();
        defaultVal.clear();
        if (super.fEntityScanner.skipString("#REQUIRED")) {
            defaultType = "#REQUIRED";
        }
        else if (super.fEntityScanner.skipString("#IMPLIED")) {
            defaultType = "#IMPLIED";
        }
        else {
            if (super.fEntityScanner.skipString("#FIXED")) {
                defaultType = "#FIXED";
                if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                    this.reportFatalError("MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL", new Object[] { elName, atName });
                }
            }
            final boolean isVC = !this.fStandalone && (this.fSeenExternalDTD || this.fSeenExternalPE);
            this.scanAttributeValue(defaultVal, nonNormalizedDefaultVal, atName, this.fAttributes, 0, isVC);
        }
        return defaultType;
    }
    
    private final void scanEntityDecl() throws IOException, XNIException {
        boolean isPEDecl = false;
        boolean sawPERef = false;
        super.fReportEntity = false;
        if (super.fEntityScanner.skipSpaces()) {
            if (!super.fEntityScanner.skipChar(37)) {
                isPEDecl = false;
            }
            else if (this.skipSeparator(true, !this.scanningInternalSubset())) {
                isPEDecl = true;
            }
            else if (this.scanningInternalSubset()) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
                isPEDecl = true;
            }
            else if (super.fEntityScanner.peekChar() == 37) {
                this.skipSeparator(false, !this.scanningInternalSubset());
                isPEDecl = true;
            }
            else {
                sawPERef = true;
            }
        }
        else if (this.scanningInternalSubset() || !super.fEntityScanner.skipChar(37)) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
            isPEDecl = false;
        }
        else if (super.fEntityScanner.skipSpaces()) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL", null);
            isPEDecl = false;
        }
        else {
            sawPERef = true;
        }
        if (sawPERef) {
            while (true) {
                final String peName = super.fEntityScanner.scanName();
                if (peName == null) {
                    this.reportFatalError("NameRequiredInPEReference", null);
                }
                else if (!super.fEntityScanner.skipChar(59)) {
                    this.reportFatalError("SemicolonRequiredInPEReference", new Object[] { peName });
                }
                else {
                    this.startPE(peName, false);
                }
                super.fEntityScanner.skipSpaces();
                if (!super.fEntityScanner.skipChar(37)) {
                    break;
                }
                if (isPEDecl) {
                    continue;
                }
                if (this.skipSeparator(true, !this.scanningInternalSubset())) {
                    isPEDecl = true;
                    break;
                }
                isPEDecl = super.fEntityScanner.skipChar(37);
            }
        }
        String name = super.fEntityScanner.scanName();
        if (name == null) {
            this.reportFatalError("MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL", null);
        }
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            this.reportFatalError("MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", new Object[] { name });
        }
        this.scanExternalID(this.fStrings, false);
        final String systemId = this.fStrings[0];
        final String publicId = this.fStrings[1];
        if (isPEDecl && systemId != null) {
            this.fSeenExternalPE = true;
        }
        String notation = null;
        final boolean sawSpace = this.skipSeparator(true, !this.scanningInternalSubset());
        if (!isPEDecl && super.fEntityScanner.skipString("NDATA")) {
            if (!sawSpace) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NDATA_IN_UNPARSED_ENTITYDECL", new Object[] { name });
            }
            if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL", new Object[] { name });
            }
            notation = super.fEntityScanner.scanName();
            if (notation == null) {
                this.reportFatalError("MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL", new Object[] { name });
            }
        }
        if (systemId == null) {
            this.scanEntityValue(this.fLiteral, this.fLiteral2);
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            this.fStringBuffer.append(this.fLiteral.ch, this.fLiteral.offset, this.fLiteral.length);
            this.fStringBuffer2.append(this.fLiteral2.ch, this.fLiteral2.offset, this.fLiteral2.length);
        }
        this.skipSeparator(false, !this.scanningInternalSubset());
        if (!super.fEntityScanner.skipChar(62)) {
            this.reportFatalError("EntityDeclUnterminated", new Object[] { name });
        }
        --this.fMarkUpDepth;
        if (isPEDecl) {
            name = "%" + name;
        }
        if (systemId != null) {
            final String baseSystemId = super.fEntityScanner.getBaseSystemId();
            final String expandedSystemId = XMLEntityManager.expandSystemId(systemId, baseSystemId);
            if (notation != null) {
                super.fEntityManager.addUnparsedEntity(name, publicId, systemId, baseSystemId, notation);
            }
            else {
                super.fEntityManager.addExternalEntity(name, publicId, systemId, baseSystemId);
            }
            if (this.fDTDHandler != null) {
                super.fResourceIdentifier.setValues(publicId, systemId, baseSystemId, XMLEntityManager.expandSystemId(systemId, baseSystemId));
                if (notation != null) {
                    this.fDTDHandler.unparsedEntityDecl(name, super.fResourceIdentifier, notation, null);
                }
                else {
                    this.fDTDHandler.externalEntityDecl(name, super.fResourceIdentifier, null);
                }
            }
        }
        else {
            super.fEntityManager.addInternalEntity(name, this.fStringBuffer.toString());
            if (this.fDTDHandler != null) {
                this.fDTDHandler.internalEntityDecl(name, this.fStringBuffer, this.fStringBuffer2, null);
            }
        }
        super.fReportEntity = true;
    }
    
    protected final void scanEntityValue(final XMLString value, final XMLString nonNormalizedValue) throws IOException, XNIException {
        final int quote = super.fEntityScanner.scanChar();
        if (quote != 39 && quote != 34) {
            this.reportFatalError("OpenQuoteMissingInDecl", null);
        }
        final int entityDepth = super.fEntityDepth;
        XMLString literal = this.fString;
        XMLString literal2 = this.fString;
        if (super.fEntityScanner.scanLiteral(quote, this.fString) != quote) {
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            do {
                this.fStringBuffer.append(this.fString);
                this.fStringBuffer2.append(this.fString);
                if (super.fEntityScanner.skipChar(38)) {
                    if (super.fEntityScanner.skipChar(35)) {
                        this.fStringBuffer2.append("&#");
                        this.scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2);
                    }
                    else {
                        this.fStringBuffer.append('&');
                        this.fStringBuffer2.append('&');
                        final String eName = super.fEntityScanner.scanName();
                        if (eName == null) {
                            this.reportFatalError("NameRequiredInReference", null);
                        }
                        else {
                            this.fStringBuffer.append(eName);
                            this.fStringBuffer2.append(eName);
                        }
                        if (!super.fEntityScanner.skipChar(59)) {
                            this.reportFatalError("SemicolonRequiredInReference", new Object[] { eName });
                        }
                        else {
                            this.fStringBuffer.append(';');
                            this.fStringBuffer2.append(';');
                        }
                    }
                }
                else if (super.fEntityScanner.skipChar(37)) {
                    do {
                        this.fStringBuffer2.append('%');
                        final String peName = super.fEntityScanner.scanName();
                        if (peName == null) {
                            this.reportFatalError("NameRequiredInPEReference", null);
                        }
                        else if (!super.fEntityScanner.skipChar(59)) {
                            this.reportFatalError("SemicolonRequiredInPEReference", new Object[] { peName });
                        }
                        else {
                            if (this.scanningInternalSubset()) {
                                this.reportFatalError("PEReferenceWithinMarkup", new Object[] { peName });
                            }
                            this.fStringBuffer2.append(peName);
                            this.fStringBuffer2.append(';');
                        }
                        this.startPE(peName, true);
                        super.fEntityScanner.skipSpaces();
                    } while (super.fEntityScanner.skipChar(37));
                }
                else {
                    final int c = super.fEntityScanner.peekChar();
                    if (XMLChar.isHighSurrogate(c)) {
                        this.scanSurrogates(this.fStringBuffer2);
                    }
                    else if (XMLChar.isInvalid(c)) {
                        this.reportFatalError("InvalidCharInLiteral", new Object[] { Integer.toHexString(c) });
                        super.fEntityScanner.scanChar();
                    }
                    else {
                        if (c == quote && entityDepth == super.fEntityDepth) {
                            continue;
                        }
                        this.fStringBuffer.append((char)c);
                        this.fStringBuffer2.append((char)c);
                        super.fEntityScanner.scanChar();
                    }
                }
            } while (super.fEntityScanner.scanLiteral(quote, this.fString) != quote);
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer2.append(this.fString);
            literal = this.fStringBuffer;
            literal2 = this.fStringBuffer2;
        }
        value.setValues(literal);
        nonNormalizedValue.setValues(literal2);
        if (!super.fEntityScanner.skipChar(quote)) {
            this.reportFatalError("CloseQuoteMissingInDecl", null);
        }
    }
    
    private final void scanNotationDecl() throws IOException, XNIException {
        super.fReportEntity = false;
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL", null);
        }
        final String name = super.fEntityScanner.scanName();
        if (name == null) {
            this.reportFatalError("MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL", null);
        }
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            this.reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL", new Object[] { name });
        }
        this.scanExternalID(this.fStrings, true);
        final String systemId = this.fStrings[0];
        final String publicId = this.fStrings[1];
        final String baseSystemId = super.fEntityScanner.getBaseSystemId();
        if (systemId == null && publicId == null) {
            this.reportFatalError("ExternalIDorPublicIDRequired", new Object[] { name });
        }
        this.skipSeparator(false, !this.scanningInternalSubset());
        if (!super.fEntityScanner.skipChar(62)) {
            this.reportFatalError("NotationDeclUnterminated", new Object[] { name });
        }
        --this.fMarkUpDepth;
        if (this.fDTDHandler != null) {
            super.fResourceIdentifier.setValues(publicId, systemId, baseSystemId, XMLEntityManager.expandSystemId(systemId, baseSystemId));
            this.fDTDHandler.notationDecl(name, super.fResourceIdentifier, null);
        }
        super.fReportEntity = true;
    }
    
    private final void scanConditionalSect(final int currPEDepth) throws IOException, XNIException {
        this.skipSeparator(super.fReportEntity = false, !this.scanningInternalSubset());
        if (super.fEntityScanner.skipString("INCLUDE")) {
            this.skipSeparator(false, !this.scanningInternalSubset());
            if (currPEDepth != this.fPEDepth && super.fValidation) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "INVALID_PE_IN_CONDITIONAL", new Object[] { super.fEntityManager.fCurrentEntity.name }, (short)1);
            }
            if (!super.fEntityScanner.skipChar(91)) {
                this.reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startConditional((short)0, null);
            }
            ++this.fIncludeSectDepth;
            super.fReportEntity = true;
        }
        else if (super.fEntityScanner.skipString("IGNORE")) {
            this.skipSeparator(false, !this.scanningInternalSubset());
            if (currPEDepth != this.fPEDepth && super.fValidation) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "INVALID_PE_IN_CONDITIONAL", new Object[] { super.fEntityManager.fCurrentEntity.name }, (short)1);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startConditional((short)1, null);
            }
            if (!super.fEntityScanner.skipChar(91)) {
                this.reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            super.fReportEntity = true;
            final int initialDepth = ++this.fIncludeSectDepth;
            if (this.fDTDHandler != null) {
                this.fIgnoreConditionalBuffer.clear();
            }
            while (true) {
                if (super.fEntityScanner.skipChar(60)) {
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append('<');
                    }
                    if (!super.fEntityScanner.skipChar(33)) {
                        continue;
                    }
                    if (super.fEntityScanner.skipChar(91)) {
                        if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append("![");
                        }
                        ++this.fIncludeSectDepth;
                    }
                    else {
                        if (this.fDTDHandler == null) {
                            continue;
                        }
                        this.fIgnoreConditionalBuffer.append("!");
                    }
                }
                else if (super.fEntityScanner.skipChar(93)) {
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append(']');
                    }
                    if (!super.fEntityScanner.skipChar(93)) {
                        continue;
                    }
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append(']');
                    }
                    while (super.fEntityScanner.skipChar(93)) {
                        if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append(']');
                        }
                    }
                    if (!super.fEntityScanner.skipChar(62)) {
                        continue;
                    }
                    if (this.fIncludeSectDepth-- == initialDepth) {
                        --this.fMarkUpDepth;
                        if (this.fDTDHandler != null) {
                            this.fLiteral.setValues(this.fIgnoreConditionalBuffer.ch, 0, this.fIgnoreConditionalBuffer.length - 2);
                            this.fDTDHandler.ignoredCharacters(this.fLiteral, null);
                            this.fDTDHandler.endConditional(null);
                        }
                        return;
                    }
                    if (this.fDTDHandler == null) {
                        continue;
                    }
                    this.fIgnoreConditionalBuffer.append('>');
                }
                else {
                    final int c = super.fEntityScanner.scanChar();
                    if (this.fScannerState == 0) {
                        this.reportFatalError("IgnoreSectUnterminated", null);
                        return;
                    }
                    if (this.fDTDHandler == null) {
                        continue;
                    }
                    this.fIgnoreConditionalBuffer.append((char)c);
                }
            }
        }
        else {
            this.reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
        }
    }
    
    protected final boolean scanDecls(final boolean complete) throws IOException, XNIException {
        this.skipSeparator(false, true);
        boolean again = true;
        while (again && this.fScannerState == 2) {
            again = complete;
            if (super.fEntityScanner.skipChar(60)) {
                ++this.fMarkUpDepth;
                if (super.fEntityScanner.skipChar(63)) {
                    this.scanPI();
                }
                else if (super.fEntityScanner.skipChar(33)) {
                    if (super.fEntityScanner.skipChar(45)) {
                        if (!super.fEntityScanner.skipChar(45)) {
                            this.reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                        }
                        else {
                            this.scanComment();
                        }
                    }
                    else if (super.fEntityScanner.skipString("ELEMENT")) {
                        this.scanElementDecl();
                    }
                    else if (super.fEntityScanner.skipString("ATTLIST")) {
                        this.scanAttlistDecl();
                    }
                    else if (super.fEntityScanner.skipString("ENTITY")) {
                        this.scanEntityDecl();
                    }
                    else if (super.fEntityScanner.skipString("NOTATION")) {
                        this.scanNotationDecl();
                    }
                    else if (super.fEntityScanner.skipChar(91) && !this.scanningInternalSubset()) {
                        this.scanConditionalSect(this.fPEDepth);
                    }
                    else {
                        --this.fMarkUpDepth;
                        this.reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                    }
                }
                else {
                    --this.fMarkUpDepth;
                    this.reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                }
            }
            else if (this.fIncludeSectDepth > 0 && super.fEntityScanner.skipChar(93)) {
                if (!super.fEntityScanner.skipChar(93) || !super.fEntityScanner.skipChar(62)) {
                    this.reportFatalError("IncludeSectUnterminated", null);
                }
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endConditional(null);
                }
                --this.fIncludeSectDepth;
                --this.fMarkUpDepth;
            }
            else {
                if (this.scanningInternalSubset() && super.fEntityScanner.peekChar() == 93) {
                    return false;
                }
                if (!super.fEntityScanner.skipSpaces()) {
                    this.reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                }
            }
            this.skipSeparator(false, true);
        }
        return this.fScannerState != 0;
    }
    
    private boolean skipSeparator(final boolean spaceRequired, final boolean lookForPERefs) throws IOException, XNIException {
        final int depth = this.fPEDepth;
        final boolean sawSpace = super.fEntityScanner.skipSpaces();
        if (!lookForPERefs || !super.fEntityScanner.skipChar(37)) {
            return !spaceRequired || sawSpace || depth != this.fPEDepth;
        }
        do {
            final String name = super.fEntityScanner.scanName();
            if (name == null) {
                this.reportFatalError("NameRequiredInPEReference", null);
            }
            else if (!super.fEntityScanner.skipChar(59)) {
                this.reportFatalError("SemicolonRequiredInPEReference", new Object[] { name });
            }
            this.startPE(name, false);
            super.fEntityScanner.skipSpaces();
        } while (super.fEntityScanner.skipChar(37));
        return true;
    }
    
    private final void pushContentStack(final int c) {
        if (this.fContentStack.length == this.fContentDepth) {
            final int[] newStack = new int[this.fContentDepth * 2];
            System.arraycopy(this.fContentStack, 0, newStack, 0, this.fContentDepth);
            this.fContentStack = newStack;
        }
        this.fContentStack[this.fContentDepth++] = c;
    }
    
    private final int popContentStack() {
        final int[] fContentStack = this.fContentStack;
        final int fContentDepth = this.fContentDepth - 1;
        this.fContentDepth = fContentDepth;
        return fContentStack[fContentDepth];
    }
    
    private final void pushPEStack(final int depth, final boolean report) {
        if (this.fPEStack.length == this.fPEDepth) {
            final int[] newIntStack = new int[this.fPEDepth * 2];
            System.arraycopy(this.fPEStack, 0, newIntStack, 0, this.fPEDepth);
            this.fPEStack = newIntStack;
            final boolean[] newBooleanStack = new boolean[this.fPEDepth * 2];
            System.arraycopy(this.fPEReport, 0, newBooleanStack, 0, this.fPEDepth);
            this.fPEReport = newBooleanStack;
        }
        this.fPEReport[this.fPEDepth] = report;
        this.fPEStack[this.fPEDepth++] = depth;
    }
    
    private final int popPEStack() {
        final int[] fpeStack = this.fPEStack;
        final int fpeDepth = this.fPEDepth - 1;
        this.fPEDepth = fpeDepth;
        return fpeStack[fpeDepth];
    }
    
    private final boolean peekReportEntity() {
        return this.fPEReport[this.fPEDepth - 1];
    }
    
    private final void ensureEnumerationSize(final int size) {
        if (this.fEnumeration.length == size) {
            final String[] newEnum = new String[size * 2];
            System.arraycopy(this.fEnumeration, 0, newEnum, 0, size);
            this.fEnumeration = newEnum;
        }
    }
    
    private void init() {
        this.fStartDTDCalled = false;
        this.fExtEntityDepth = 0;
        this.fIncludeSectDepth = 0;
        this.fMarkUpDepth = 0;
        this.fPEDepth = 0;
        this.fStandalone = false;
        this.fSeenExternalDTD = false;
        this.fSeenExternalPE = false;
        this.setScannerState(1);
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://apache.org/xml/features/scanner/notify-char-refs" };
        FEATURE_DEFAULTS = new Boolean[] { null, Boolean.FALSE };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager" };
        PROPERTY_DEFAULTS = new Object[] { null, null, null };
    }
}
