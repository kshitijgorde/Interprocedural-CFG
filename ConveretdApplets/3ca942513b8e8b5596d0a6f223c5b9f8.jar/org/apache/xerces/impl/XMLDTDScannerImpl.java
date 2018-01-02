// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

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
    
    public XMLDTDScannerImpl(final SymbolTable fSymbolTable, final XMLErrorReporter fErrorReporter, final XMLEntityManager fEntityManager) {
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
        super.fSymbolTable = fSymbolTable;
        super.fErrorReporter = fErrorReporter;
        (super.fEntityManager = fEntityManager).setProperty("http://apache.org/xml/properties/internal/symbol-table", super.fSymbolTable);
    }
    
    public void setInputSource(final XMLInputSource xmlInputSource) throws IOException {
        if (xmlInputSource == null) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startDTD(null, null);
                this.fDTDHandler.endDTD(null);
            }
            return;
        }
        super.fEntityManager.setEntityHandler(this);
        super.fEntityManager.startDTDEntity(xmlInputSource);
    }
    
    public boolean scanDTDExternalSubset(final boolean b) throws IOException, XNIException {
        super.fEntityManager.setEntityHandler(this);
        if (this.fScannerState == 1) {
            this.fSeenExternalDTD = true;
            final boolean scanTextDecl = this.scanTextDecl();
            if (this.fScannerState == 0) {
                return false;
            }
            this.setScannerState(2);
            if (scanTextDecl && !b) {
                return true;
            }
        }
        while (this.scanDecls(b)) {
            if (!b) {
                return true;
            }
        }
        return false;
    }
    
    public boolean scanDTDInternalSubset(final boolean b, final boolean fStandalone, final boolean b2) throws IOException, XNIException {
        super.fEntityScanner = super.fEntityManager.getEntityScanner();
        super.fEntityManager.setEntityHandler(this);
        this.fStandalone = fStandalone;
        if (this.fScannerState == 1) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startDTD(super.fEntityScanner, null);
                this.fStartDTDCalled = true;
            }
            this.setScannerState(2);
        }
        while (this.scanDecls(b)) {
            if (!b) {
                return true;
            }
        }
        if (this.fDTDHandler != null && !b2) {
            this.fDTDHandler.endDTD(null);
        }
        this.setScannerState(1);
        return false;
    }
    
    public void reset(final XMLComponentManager xmlComponentManager) throws XMLConfigurationException {
        super.reset(xmlComponentManager);
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
    
    public Boolean getFeatureDefault(final String s) {
        for (int i = 0; i < XMLDTDScannerImpl.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLDTDScannerImpl.RECOGNIZED_FEATURES[i].equals(s)) {
                return XMLDTDScannerImpl.FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public Object getPropertyDefault(final String s) {
        for (int i = 0; i < XMLDTDScannerImpl.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLDTDScannerImpl.RECOGNIZED_PROPERTIES[i].equals(s)) {
                return XMLDTDScannerImpl.PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public void setDTDHandler(final XMLDTDHandler fdtdHandler) {
        this.fDTDHandler = fdtdHandler;
    }
    
    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }
    
    public void setDTDContentModelHandler(final XMLDTDContentModelHandler fdtdContentModelHandler) {
        this.fDTDContentModelHandler = fdtdContentModelHandler;
    }
    
    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }
    
    public void startEntity(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final String s2, final Augmentations augmentations) throws XNIException {
        super.startEntity(s, xmlResourceIdentifier, s2, augmentations);
        final boolean equals = s.equals("[dtd]");
        if (equals) {
            if (this.fDTDHandler != null && !this.fStartDTDCalled) {
                this.fDTDHandler.startDTD(super.fEntityScanner, null);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startExternalSubset(xmlResourceIdentifier, null);
            }
            super.fEntityManager.startExternalSubset();
            ++this.fExtEntityDepth;
        }
        else if (s.charAt(0) == '%') {
            this.pushPEStack(this.fMarkUpDepth, super.fReportEntity);
            if (super.fEntityScanner.isExternal()) {
                ++this.fExtEntityDepth;
            }
        }
        if (this.fDTDHandler != null && !equals && super.fReportEntity) {
            this.fDTDHandler.startParameterEntity(s, xmlResourceIdentifier, s2, augmentations);
        }
    }
    
    public void endEntity(final String s, final Augmentations augmentations) throws XNIException {
        super.endEntity(s, augmentations);
        if (this.fScannerState == 0) {
            return;
        }
        final boolean fReportEntity = super.fReportEntity;
        if (s.startsWith("%")) {
            boolean peekReportEntity = this.peekReportEntity();
            final int popPEStack = this.popPEStack();
            if (popPEStack == 0 && popPEStack < this.fMarkUpDepth) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ILL_FORMED_PARAMETER_ENTITY_WHEN_USED_IN_DECL", new Object[] { super.fEntityManager.fCurrentEntity.name }, (short)2);
            }
            if (popPEStack != this.fMarkUpDepth) {
                peekReportEntity = false;
                if (super.fValidation) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ImproperDeclarationNesting", new Object[] { s }, (short)1);
                }
            }
            if (super.fEntityScanner.isExternal()) {
                --this.fExtEntityDepth;
            }
            if (this.fDTDHandler != null && peekReportEntity) {
                this.fDTDHandler.endParameterEntity(s, augmentations);
            }
        }
        else if (s.equals("[dtd]")) {
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
    
    protected final void setScannerState(final int fScannerState) {
        this.fScannerState = fScannerState;
    }
    
    private static String getScannerStateName(final int n) {
        return "??? (" + n + ')';
    }
    
    protected final boolean scanningInternalSubset() {
        return this.fExtEntityDepth == 0;
    }
    
    protected void startPE(final String s, final boolean b) throws IOException, XNIException {
        final int fpeDepth = this.fPEDepth;
        final String string = "%" + s;
        if (super.fValidation && !super.fEntityManager.isDeclaredEntity(string)) {
            super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[] { s }, (short)1);
        }
        super.fEntityManager.startEntity(super.fSymbolTable.addSymbol(string), b);
        if (fpeDepth != this.fPEDepth && super.fEntityScanner.isExternal()) {
            this.scanTextDecl();
        }
    }
    
    protected final boolean scanTextDecl() throws IOException, XNIException {
        boolean b = false;
        if (super.fEntityScanner.skipString("<?xml")) {
            ++this.fMarkUpDepth;
            if (this.isValidNameChar(super.fEntityScanner.peekChar())) {
                this.fStringBuffer.clear();
                this.fStringBuffer.append("xml");
                if (super.fNamespaces) {
                    while (this.isValidNCName(super.fEntityScanner.peekChar())) {
                        this.fStringBuffer.append((char)super.fEntityScanner.scanChar());
                    }
                }
                else {
                    while (this.isValidNameChar(super.fEntityScanner.peekChar())) {
                        this.fStringBuffer.append((char)super.fEntityScanner.scanChar());
                    }
                }
                this.scanPIData(super.fSymbolTable.addSymbol(this.fStringBuffer.ch, this.fStringBuffer.offset, this.fStringBuffer.length), this.fString);
            }
            else {
                this.scanXMLDeclOrTextDecl(true, this.fStrings);
                b = true;
                --this.fMarkUpDepth;
                final String xmlVersion = this.fStrings[0];
                final String encoding = this.fStrings[1];
                super.fEntityScanner.setXMLVersion(xmlVersion);
                if (!super.fEntityScanner.fCurrentEntity.isEncodingExternallySpecified()) {
                    super.fEntityScanner.setEncoding(encoding);
                }
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.textDecl(xmlVersion, encoding, null);
                }
            }
        }
        super.fEntityManager.fCurrentEntity.mayReadChunks = true;
        return b;
    }
    
    protected final void scanPIData(final String s, final XMLString xmlString) throws IOException, XNIException {
        super.scanPIData(s, xmlString);
        --this.fMarkUpDepth;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.processingInstruction(s, xmlString, null);
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
        final String scanName = super.fEntityScanner.scanName();
        if (scanName == null) {
            this.reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL", null);
        }
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL", new Object[] { scanName });
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.startContentModel(scanName, null);
        }
        super.fReportEntity = true;
        String string;
        if (super.fEntityScanner.skipString("EMPTY")) {
            string = "EMPTY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.empty(null);
            }
        }
        else if (super.fEntityScanner.skipString("ANY")) {
            string = "ANY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.any(null);
            }
        }
        else {
            if (!super.fEntityScanner.skipChar(40)) {
                this.reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[] { scanName });
            }
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.startGroup(null);
            }
            this.fStringBuffer.clear();
            this.fStringBuffer.append('(');
            ++this.fMarkUpDepth;
            this.skipSeparator(false, !this.scanningInternalSubset());
            if (super.fEntityScanner.skipString("#PCDATA")) {
                this.scanMixed(scanName);
            }
            else {
                this.scanChildren(scanName);
            }
            string = this.fStringBuffer.toString();
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.endContentModel(null);
        }
        this.skipSeparator(super.fReportEntity = false, !this.scanningInternalSubset());
        if (!super.fEntityScanner.skipChar(62)) {
            this.reportFatalError("ElementDeclUnterminated", new Object[] { scanName });
        }
        super.fReportEntity = true;
        --this.fMarkUpDepth;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.elementDecl(scanName, string, null);
        }
    }
    
    private final void scanMixed(final String s) throws IOException, XNIException {
        String scanName = null;
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
            scanName = super.fEntityScanner.scanName();
            if (scanName == null) {
                this.reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT", new Object[] { s });
            }
            this.fStringBuffer.append(scanName);
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.element(scanName, null);
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
        else if (scanName != null) {
            this.reportFatalError("MixedContentUnterminated", new Object[] { s });
        }
        else if (super.fEntityScanner.skipChar(41)) {
            this.fStringBuffer.append(')');
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.endGroup(null);
            }
        }
        else {
            this.reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[] { s });
        }
        --this.fMarkUpDepth;
    }
    
    private final void scanChildren(final String s) throws IOException, XNIException {
        this.pushContentStack(this.fContentDepth = 0);
        int popContentStack = 0;
    Label_0015:
        while (true) {
            if (super.fEntityScanner.skipChar(40)) {
                ++this.fMarkUpDepth;
                this.fStringBuffer.append('(');
                if (this.fDTDContentModelHandler != null) {
                    this.fDTDContentModelHandler.startGroup(null);
                }
                this.pushContentStack(popContentStack);
                popContentStack = 0;
                this.skipSeparator(false, !this.scanningInternalSubset());
            }
            else {
                this.skipSeparator(false, !this.scanningInternalSubset());
                final String scanName = super.fEntityScanner.scanName();
                if (scanName == null) {
                    this.reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[] { s });
                    return;
                }
                if (this.fDTDContentModelHandler != null) {
                    this.fDTDContentModelHandler.element(scanName, null);
                }
                this.fStringBuffer.append(scanName);
                final int peekChar = super.fEntityScanner.peekChar();
                if (peekChar == 63 || peekChar == 42 || peekChar == 43) {
                    if (this.fDTDContentModelHandler != null) {
                        short n;
                        if (peekChar == 63) {
                            n = 2;
                        }
                        else if (peekChar == 42) {
                            n = 3;
                        }
                        else {
                            n = 4;
                        }
                        this.fDTDContentModelHandler.occurrence(n, null);
                    }
                    super.fEntityScanner.scanChar();
                    this.fStringBuffer.append((char)peekChar);
                }
                do {
                    this.skipSeparator(false, !this.scanningInternalSubset());
                    final int peekChar2 = super.fEntityScanner.peekChar();
                    if (peekChar2 == 44 && popContentStack != 124) {
                        popContentStack = peekChar2;
                        if (this.fDTDContentModelHandler != null) {
                            this.fDTDContentModelHandler.separator((short)1, null);
                        }
                        super.fEntityScanner.scanChar();
                        this.fStringBuffer.append(',');
                    }
                    else {
                        if (peekChar2 != 124 || popContentStack == 44) {
                            if (peekChar2 != 41) {
                                this.reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[] { s });
                            }
                            if (this.fDTDContentModelHandler != null) {
                                this.fDTDContentModelHandler.endGroup(null);
                            }
                            popContentStack = this.popContentStack();
                            if (super.fEntityScanner.skipString(")?")) {
                                this.fStringBuffer.append(")?");
                                if (this.fDTDContentModelHandler != null) {
                                    this.fDTDContentModelHandler.occurrence((short)2, null);
                                }
                            }
                            else if (super.fEntityScanner.skipString(")+")) {
                                this.fStringBuffer.append(")+");
                                if (this.fDTDContentModelHandler != null) {
                                    this.fDTDContentModelHandler.occurrence((short)4, null);
                                }
                            }
                            else if (super.fEntityScanner.skipString(")*")) {
                                this.fStringBuffer.append(")*");
                                if (this.fDTDContentModelHandler != null) {
                                    this.fDTDContentModelHandler.occurrence((short)3, null);
                                }
                            }
                            else {
                                super.fEntityScanner.scanChar();
                                this.fStringBuffer.append(')');
                            }
                            --this.fMarkUpDepth;
                            continue;
                        }
                        popContentStack = peekChar2;
                        if (this.fDTDContentModelHandler != null) {
                            this.fDTDContentModelHandler.separator((short)0, null);
                        }
                        super.fEntityScanner.scanChar();
                        this.fStringBuffer.append('|');
                    }
                    this.skipSeparator(false, !this.scanningInternalSubset());
                    continue Label_0015;
                } while (this.fContentDepth != 0);
            }
        }
    }
    
    protected final void scanAttlistDecl() throws IOException, XNIException {
        super.fReportEntity = false;
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL", null);
        }
        final String scanName = super.fEntityScanner.scanName();
        if (scanName == null) {
            this.reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL", null);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startAttlist(scanName, null);
        }
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            if (super.fEntityScanner.skipChar(62)) {
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endAttlist(null);
                }
                --this.fMarkUpDepth;
                return;
            }
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF", new Object[] { scanName });
        }
        while (!super.fEntityScanner.skipChar(62)) {
            final String scanName2 = super.fEntityScanner.scanName();
            if (scanName2 == null) {
                this.reportFatalError("AttNameRequiredInAttDef", new Object[] { scanName });
            }
            if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF", new Object[] { scanName, scanName2 });
            }
            final String scanAttType = this.scanAttType(scanName, scanName2);
            if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF", new Object[] { scanName, scanName2 });
            }
            final String scanAttDefaultDecl = this.scanAttDefaultDecl(scanName, scanName2, scanAttType, this.fLiteral, this.fLiteral2);
            if (this.fDTDHandler != null) {
                String[] array = null;
                if (this.fEnumerationCount != 0) {
                    array = new String[this.fEnumerationCount];
                    System.arraycopy(this.fEnumeration, 0, array, 0, this.fEnumerationCount);
                }
                if (scanAttDefaultDecl != null && (scanAttDefaultDecl.equals("#REQUIRED") || scanAttDefaultDecl.equals("#IMPLIED"))) {
                    this.fDTDHandler.attributeDecl(scanName, scanName2, scanAttType, array, scanAttDefaultDecl, null, null, null);
                }
                else {
                    this.fDTDHandler.attributeDecl(scanName, scanName2, scanAttType, array, scanAttDefaultDecl, this.fLiteral, this.fLiteral2, null);
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
    
    private final String scanAttType(final String s, final String s2) throws IOException, XNIException {
        this.fEnumerationCount = 0;
        String s3;
        if (super.fEntityScanner.skipString("CDATA")) {
            s3 = "CDATA";
        }
        else if (super.fEntityScanner.skipString("IDREFS")) {
            s3 = "IDREFS";
        }
        else if (super.fEntityScanner.skipString("IDREF")) {
            s3 = "IDREF";
        }
        else if (super.fEntityScanner.skipString("ID")) {
            s3 = "ID";
        }
        else if (super.fEntityScanner.skipString("ENTITY")) {
            s3 = "ENTITY";
        }
        else if (super.fEntityScanner.skipString("ENTITIES")) {
            s3 = "ENTITIES";
        }
        else if (super.fEntityScanner.skipString("NMTOKENS")) {
            s3 = "NMTOKENS";
        }
        else if (super.fEntityScanner.skipString("NMTOKEN")) {
            s3 = "NMTOKEN";
        }
        else if (super.fEntityScanner.skipString("NOTATION")) {
            s3 = "NOTATION";
            if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                this.reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE", new Object[] { s, s2 });
            }
            if (super.fEntityScanner.scanChar() != 40) {
                this.reportFatalError("MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE", new Object[] { s, s2 });
            }
            ++this.fMarkUpDepth;
            int i;
            do {
                this.skipSeparator(false, !this.scanningInternalSubset());
                final String scanName = super.fEntityScanner.scanName();
                if (scanName == null) {
                    this.reportFatalError("MSG_NAME_REQUIRED_IN_NOTATIONTYPE", new Object[] { s, s2 });
                }
                this.ensureEnumerationSize(this.fEnumerationCount + 1);
                this.fEnumeration[this.fEnumerationCount++] = scanName;
                this.skipSeparator(false, !this.scanningInternalSubset());
                i = super.fEntityScanner.scanChar();
            } while (i == 124);
            if (i != 41) {
                this.reportFatalError("NotationTypeUnterminated", new Object[] { s, s2 });
            }
            --this.fMarkUpDepth;
        }
        else {
            s3 = "ENUMERATION";
            if (super.fEntityScanner.scanChar() != 40) {
                this.reportFatalError("AttTypeRequiredInAttDef", new Object[] { s, s2 });
            }
            ++this.fMarkUpDepth;
            int j;
            do {
                this.skipSeparator(false, !this.scanningInternalSubset());
                final String scanNmtoken = super.fEntityScanner.scanNmtoken();
                if (scanNmtoken == null) {
                    this.reportFatalError("MSG_NMTOKEN_REQUIRED_IN_ENUMERATION", new Object[] { s, s2 });
                }
                this.ensureEnumerationSize(this.fEnumerationCount + 1);
                this.fEnumeration[this.fEnumerationCount++] = scanNmtoken;
                this.skipSeparator(false, !this.scanningInternalSubset());
                j = super.fEntityScanner.scanChar();
            } while (j == 124);
            if (j != 41) {
                this.reportFatalError("EnumerationUnterminated", new Object[] { s, s2 });
            }
            --this.fMarkUpDepth;
        }
        return s3;
    }
    
    protected final String scanAttDefaultDecl(final String s, final String s2, final String s3, final XMLString xmlString, final XMLString xmlString2) throws IOException, XNIException {
        String s4 = null;
        this.fString.clear();
        xmlString.clear();
        if (super.fEntityScanner.skipString("#REQUIRED")) {
            s4 = "#REQUIRED";
        }
        else if (super.fEntityScanner.skipString("#IMPLIED")) {
            s4 = "#IMPLIED";
        }
        else {
            if (super.fEntityScanner.skipString("#FIXED")) {
                s4 = "#FIXED";
                if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                    this.reportFatalError("MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL", new Object[] { s, s2 });
                }
            }
            this.scanAttributeValue(xmlString, xmlString2, s2, !this.fStandalone && (this.fSeenExternalDTD || this.fSeenExternalPE), s);
        }
        return s4;
    }
    
    private final void scanEntityDecl() throws IOException, XNIException {
        int skipChar = 0;
        boolean b = false;
        super.fReportEntity = false;
        if (super.fEntityScanner.skipSpaces()) {
            if (!super.fEntityScanner.skipChar(37)) {
                skipChar = 0;
            }
            else if (this.skipSeparator(true, !this.scanningInternalSubset())) {
                skipChar = 1;
            }
            else if (this.scanningInternalSubset()) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
                skipChar = 1;
            }
            else if (super.fEntityScanner.peekChar() == 37) {
                this.skipSeparator(false, !this.scanningInternalSubset());
                skipChar = 1;
            }
            else {
                b = true;
            }
        }
        else if (this.scanningInternalSubset() || !super.fEntityScanner.skipChar(37)) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
            skipChar = 0;
        }
        else if (super.fEntityScanner.skipSpaces()) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL", null);
            skipChar = 0;
        }
        else {
            b = true;
        }
        if (b) {
            while (true) {
                final String scanName = super.fEntityScanner.scanName();
                if (scanName == null) {
                    this.reportFatalError("NameRequiredInPEReference", null);
                }
                else if (!super.fEntityScanner.skipChar(59)) {
                    this.reportFatalError("SemicolonRequiredInPEReference", new Object[] { scanName });
                }
                else {
                    this.startPE(scanName, false);
                }
                super.fEntityScanner.skipSpaces();
                if (!super.fEntityScanner.skipChar(37)) {
                    break;
                }
                if (skipChar != 0) {
                    continue;
                }
                if (this.skipSeparator(true, !this.scanningInternalSubset())) {
                    skipChar = 1;
                    break;
                }
                skipChar = (super.fEntityScanner.skipChar(37) ? 1 : 0);
            }
        }
        String s;
        if (super.fNamespaces) {
            s = super.fEntityScanner.scanNCName();
        }
        else {
            s = super.fEntityScanner.scanName();
        }
        if (s == null) {
            this.reportFatalError("MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL", null);
        }
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            if (super.fNamespaces && super.fEntityScanner.peekChar() == 58) {
                super.fEntityScanner.scanChar();
                final XMLStringBuffer xmlStringBuffer = new XMLStringBuffer(s);
                xmlStringBuffer.append(":");
                final String scanName2 = super.fEntityScanner.scanName();
                if (scanName2 != null) {
                    xmlStringBuffer.append(scanName2);
                }
                this.reportFatalError("ColonNotLegalWithNS", new Object[] { xmlStringBuffer.toString() });
                if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                    this.reportFatalError("MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", new Object[] { s });
                }
            }
            else {
                this.reportFatalError("MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", new Object[] { s });
            }
        }
        this.scanExternalID(this.fStrings, false);
        final String s2 = this.fStrings[0];
        final String s3 = this.fStrings[1];
        if (skipChar != 0 && s2 != null) {
            this.fSeenExternalPE = true;
        }
        String scanName3 = null;
        final boolean skipSeparator = this.skipSeparator(true, !this.scanningInternalSubset());
        if (skipChar == 0 && super.fEntityScanner.skipString("NDATA")) {
            if (!skipSeparator) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NDATA_IN_UNPARSED_ENTITYDECL", new Object[] { s });
            }
            if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
                this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL", new Object[] { s });
            }
            scanName3 = super.fEntityScanner.scanName();
            if (scanName3 == null) {
                this.reportFatalError("MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL", new Object[] { s });
            }
        }
        if (s2 == null) {
            this.scanEntityValue(this.fLiteral, this.fLiteral2);
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            this.fStringBuffer.append(this.fLiteral.ch, this.fLiteral.offset, this.fLiteral.length);
            this.fStringBuffer2.append(this.fLiteral2.ch, this.fLiteral2.offset, this.fLiteral2.length);
        }
        this.skipSeparator(false, !this.scanningInternalSubset());
        if (!super.fEntityScanner.skipChar(62)) {
            this.reportFatalError("EntityDeclUnterminated", new Object[] { s });
        }
        --this.fMarkUpDepth;
        if (skipChar != 0) {
            s = "%" + s;
        }
        if (s2 != null) {
            final String baseSystemId = super.fEntityScanner.getBaseSystemId();
            if (scanName3 != null) {
                super.fEntityManager.addUnparsedEntity(s, s3, s2, baseSystemId, scanName3);
            }
            else {
                super.fEntityManager.addExternalEntity(s, s3, s2, baseSystemId);
            }
            if (this.fDTDHandler != null) {
                super.fResourceIdentifier.setValues(s3, s2, baseSystemId, XMLEntityManager.expandSystemId(s2, baseSystemId, false));
                if (scanName3 != null) {
                    this.fDTDHandler.unparsedEntityDecl(s, super.fResourceIdentifier, scanName3, null);
                }
                else {
                    this.fDTDHandler.externalEntityDecl(s, super.fResourceIdentifier, null);
                }
            }
        }
        else {
            super.fEntityManager.addInternalEntity(s, this.fStringBuffer.toString());
            if (this.fDTDHandler != null) {
                this.fDTDHandler.internalEntityDecl(s, this.fStringBuffer, this.fStringBuffer2, null);
            }
        }
        super.fReportEntity = true;
    }
    
    protected final void scanEntityValue(final XMLString xmlString, final XMLString xmlString2) throws IOException, XNIException {
        final int scanChar = super.fEntityScanner.scanChar();
        if (scanChar != 39 && scanChar != 34) {
            this.reportFatalError("OpenQuoteMissingInDecl", null);
        }
        final int fEntityDepth = super.fEntityDepth;
        XMLString values = this.fString;
        XMLString values2 = this.fString;
        if (super.fEntityScanner.scanLiteral(scanChar, this.fString) != scanChar) {
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
                        final String scanName = super.fEntityScanner.scanName();
                        if (scanName == null) {
                            this.reportFatalError("NameRequiredInReference", null);
                        }
                        else {
                            this.fStringBuffer.append(scanName);
                            this.fStringBuffer2.append(scanName);
                        }
                        if (!super.fEntityScanner.skipChar(59)) {
                            this.reportFatalError("SemicolonRequiredInReference", new Object[] { scanName });
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
                        final String scanName2 = super.fEntityScanner.scanName();
                        if (scanName2 == null) {
                            this.reportFatalError("NameRequiredInPEReference", null);
                        }
                        else if (!super.fEntityScanner.skipChar(59)) {
                            this.reportFatalError("SemicolonRequiredInPEReference", new Object[] { scanName2 });
                        }
                        else {
                            if (this.scanningInternalSubset()) {
                                this.reportFatalError("PEReferenceWithinMarkup", new Object[] { scanName2 });
                            }
                            this.fStringBuffer2.append(scanName2);
                            this.fStringBuffer2.append(';');
                        }
                        this.startPE(scanName2, true);
                        super.fEntityScanner.skipSpaces();
                    } while (super.fEntityScanner.skipChar(37));
                }
                else {
                    final int peekChar = super.fEntityScanner.peekChar();
                    if (XMLChar.isHighSurrogate(peekChar)) {
                        this.scanSurrogates(this.fStringBuffer2);
                    }
                    else if (this.isInvalidLiteral(peekChar)) {
                        this.reportFatalError("InvalidCharInLiteral", new Object[] { Integer.toHexString(peekChar) });
                        super.fEntityScanner.scanChar();
                    }
                    else {
                        if (peekChar == scanChar && fEntityDepth == super.fEntityDepth) {
                            continue;
                        }
                        this.fStringBuffer.append((char)peekChar);
                        this.fStringBuffer2.append((char)peekChar);
                        super.fEntityScanner.scanChar();
                    }
                }
            } while (super.fEntityScanner.scanLiteral(scanChar, this.fString) != scanChar);
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer2.append(this.fString);
            values = this.fStringBuffer;
            values2 = this.fStringBuffer2;
        }
        xmlString.setValues(values);
        xmlString2.setValues(values2);
        if (!super.fEntityScanner.skipChar(scanChar)) {
            this.reportFatalError("CloseQuoteMissingInDecl", null);
        }
    }
    
    private final void scanNotationDecl() throws IOException, XNIException {
        super.fReportEntity = false;
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            this.reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL", null);
        }
        String s;
        if (super.fNamespaces) {
            s = super.fEntityScanner.scanNCName();
        }
        else {
            s = super.fEntityScanner.scanName();
        }
        if (s == null) {
            this.reportFatalError("MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL", null);
        }
        if (!this.skipSeparator(true, !this.scanningInternalSubset())) {
            if (super.fNamespaces && super.fEntityScanner.peekChar() == 58) {
                super.fEntityScanner.scanChar();
                final XMLStringBuffer xmlStringBuffer = new XMLStringBuffer(s);
                xmlStringBuffer.append(":");
                xmlStringBuffer.append(super.fEntityScanner.scanName());
                this.reportFatalError("ColonNotLegalWithNS", new Object[] { xmlStringBuffer.toString() });
                this.skipSeparator(true, !this.scanningInternalSubset());
            }
            else {
                this.reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL", new Object[] { s });
            }
        }
        this.scanExternalID(this.fStrings, true);
        final String s2 = this.fStrings[0];
        final String s3 = this.fStrings[1];
        final String baseSystemId = super.fEntityScanner.getBaseSystemId();
        if (s2 == null && s3 == null) {
            this.reportFatalError("ExternalIDorPublicIDRequired", new Object[] { s });
        }
        this.skipSeparator(false, !this.scanningInternalSubset());
        if (!super.fEntityScanner.skipChar(62)) {
            this.reportFatalError("NotationDeclUnterminated", new Object[] { s });
        }
        --this.fMarkUpDepth;
        if (this.fDTDHandler != null) {
            super.fResourceIdentifier.setValues(s3, s2, baseSystemId, XMLEntityManager.expandSystemId(s2, baseSystemId, false));
            this.fDTDHandler.notationDecl(s, super.fResourceIdentifier, null);
        }
        super.fReportEntity = true;
    }
    
    private final void scanConditionalSect(final int n) throws IOException, XNIException {
        this.skipSeparator(super.fReportEntity = false, !this.scanningInternalSubset());
        if (super.fEntityScanner.skipString("INCLUDE")) {
            this.skipSeparator(false, !this.scanningInternalSubset());
            if (n != this.fPEDepth && super.fValidation) {
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
            if (n != this.fPEDepth && super.fValidation) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "INVALID_PE_IN_CONDITIONAL", new Object[] { super.fEntityManager.fCurrentEntity.name }, (short)1);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startConditional((short)1, null);
            }
            if (!super.fEntityScanner.skipChar(91)) {
                this.reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            super.fReportEntity = true;
            final int n2 = ++this.fIncludeSectDepth;
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
                    if (this.fIncludeSectDepth-- == n2) {
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
                    final int scanChar = super.fEntityScanner.scanChar();
                    if (this.fScannerState == 0) {
                        this.reportFatalError("IgnoreSectUnterminated", null);
                        return;
                    }
                    if (this.fDTDHandler == null) {
                        continue;
                    }
                    this.fIgnoreConditionalBuffer.append((char)scanChar);
                }
            }
        }
        else {
            this.reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
        }
    }
    
    protected final boolean scanDecls(final boolean b) throws IOException, XNIException {
        this.skipSeparator(false, true);
        boolean b2 = true;
        while (b2 && this.fScannerState == 2) {
            b2 = b;
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
                    int peekChar;
                    do {
                        super.fEntityScanner.scanChar();
                        this.skipSeparator(false, true);
                        peekChar = super.fEntityScanner.peekChar();
                    } while (peekChar != 60 && peekChar != 93 && !XMLChar.isSpace(peekChar));
                }
            }
            this.skipSeparator(false, true);
        }
        return this.fScannerState != 0;
    }
    
    private boolean skipSeparator(final boolean b, final boolean b2) throws IOException, XNIException {
        final int fpeDepth = this.fPEDepth;
        final boolean skipSpaces = super.fEntityScanner.skipSpaces();
        if (!b2 || !super.fEntityScanner.skipChar(37)) {
            return !b || skipSpaces || fpeDepth != this.fPEDepth;
        }
        do {
            final String scanName = super.fEntityScanner.scanName();
            if (scanName == null) {
                this.reportFatalError("NameRequiredInPEReference", null);
            }
            else if (!super.fEntityScanner.skipChar(59)) {
                this.reportFatalError("SemicolonRequiredInPEReference", new Object[] { scanName });
            }
            this.startPE(scanName, false);
            super.fEntityScanner.skipSpaces();
        } while (super.fEntityScanner.skipChar(37));
        return true;
    }
    
    private final void pushContentStack(final int n) {
        if (this.fContentStack.length == this.fContentDepth) {
            final int[] fContentStack = new int[this.fContentDepth * 2];
            System.arraycopy(this.fContentStack, 0, fContentStack, 0, this.fContentDepth);
            this.fContentStack = fContentStack;
        }
        this.fContentStack[this.fContentDepth++] = n;
    }
    
    private final int popContentStack() {
        final int[] fContentStack = this.fContentStack;
        final int fContentDepth = this.fContentDepth - 1;
        this.fContentDepth = fContentDepth;
        return fContentStack[fContentDepth];
    }
    
    private final void pushPEStack(final int n, final boolean b) {
        if (this.fPEStack.length == this.fPEDepth) {
            final int[] fpeStack = new int[this.fPEDepth * 2];
            System.arraycopy(this.fPEStack, 0, fpeStack, 0, this.fPEDepth);
            this.fPEStack = fpeStack;
            final boolean[] fpeReport = new boolean[this.fPEDepth * 2];
            System.arraycopy(this.fPEReport, 0, fpeReport, 0, this.fPEDepth);
            this.fPEReport = fpeReport;
        }
        this.fPEReport[this.fPEDepth] = b;
        this.fPEStack[this.fPEDepth++] = n;
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
    
    private final void ensureEnumerationSize(final int n) {
        if (this.fEnumeration.length == n) {
            final String[] fEnumeration = new String[n * 2];
            System.arraycopy(this.fEnumeration, 0, fEnumeration, 0, n);
            this.fEnumeration = fEnumeration;
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
