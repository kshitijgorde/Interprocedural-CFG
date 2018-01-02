// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

import java.util.StringTokenizer;
import org.apache.xerces.utils.XMLCharacterProperties;
import org.apache.xerces.validators.common.Grammar;
import org.apache.xerces.readers.DefaultEntityHandler;
import org.apache.xerces.readers.XMLEntityHandler;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.utils.QName;
import org.apache.xerces.validators.common.XMLAttributeDecl;
import org.apache.xerces.validators.common.XMLElementDecl;
import org.apache.xerces.validators.common.GrammarResolver;
import org.apache.xerces.validators.dtd.DTDGrammar;

public final class XMLDTDScanner
{
    private static final char[] version_string;
    private static final char[] element_string;
    private static final char[] empty_string;
    private static final char[] any_string;
    private static final char[] pcdata_string;
    private static final char[] attlist_string;
    private static final char[] cdata_string;
    private static final char[] id_string;
    private static final char[] ref_string;
    private static final char[] entit_string;
    private static final char[] ies_string;
    private static final char[] nmtoken_string;
    private static final char[] notation_string;
    private static final char[] required_string;
    private static final char[] implied_string;
    private static final char[] fixed_string;
    private static final char[] include_string;
    private static final char[] ignore_string;
    private static final char[] entity_string;
    private static final char[] system_string;
    private static final char[] public_string;
    private static final char[] ndata_string;
    private static final char[] encoding_string;
    private DTDGrammar fDTDGrammar;
    private GrammarResolver fGrammarResolver;
    private boolean fNamespacesEnabled;
    private boolean fValidationEnabled;
    private boolean fLoadExternalDTD;
    private XMLElementDecl fTempElementDecl;
    private XMLAttributeDecl fTempAttributeDecl;
    private QName fElementQName;
    private QName fAttributeQName;
    private QName fElementRefQName;
    private EventHandler fEventHandler;
    private XMLDocumentHandler.DTDHandler fDTDHandler;
    private StringPool fStringPool;
    private XMLErrorReporter fErrorReporter;
    private XMLEntityHandler fEntityHandler;
    private XMLEntityHandler.EntityReader fEntityReader;
    private XMLEntityHandler.CharBuffer fLiteralData;
    private int fReaderId;
    private int fSystemLiteral;
    private int fPubidLiteral;
    private int[] fOpStack;
    private int[] fNodeIndexStack;
    private int[] fPrevNodeIndexStack;
    private int fScannerState;
    private int fIncludeSectDepth;
    private int fDoctypeReader;
    private int fExternalSubsetReader;
    private int fDefaultAttValueReader;
    private int fDefaultAttValueElementType;
    private int fDefaultAttValueAttrName;
    private int fDefaultAttValueOffset;
    private int fDefaultAttValueMark;
    private int fEntityValueReader;
    private int fEntityValueMark;
    private int fXMLSymbol;
    private int fXMLNamespace;
    private int fXMLSpace;
    private int fDefault;
    private int fPreserve;
    private int fScannerMarkupDepth;
    private int fScannerParenDepth;
    private static final int SCANNER_STATE_INVALID = -1;
    private static final int SCANNER_STATE_END_OF_INPUT = 0;
    private static final int SCANNER_STATE_DOCTYPEDECL = 50;
    private static final int SCANNER_STATE_MARKUP_DECL = 51;
    private static final int SCANNER_STATE_TEXTDECL = 53;
    private static final int SCANNER_STATE_COMMENT = 54;
    private static final int SCANNER_STATE_PI = 55;
    private static final int SCANNER_STATE_DEFAULT_ATTRIBUTE_VALUE = 56;
    private static final int SCANNER_STATE_CONTENTSPEC = 57;
    private static final int SCANNER_STATE_ENTITY_VALUE = 58;
    private static final int SCANNER_STATE_SYSTEMLITERAL = 59;
    private static final int SCANNER_STATE_PUBIDLITERAL = 60;
    private QName fElementDeclQName;
    
    public XMLDTDScanner(final StringPool fStringPool, final XMLErrorReporter fErrorReporter, final XMLEntityHandler fEntityHandler, final XMLEntityHandler.CharBuffer fLiteralData) {
        this.fDTDGrammar = null;
        this.fGrammarResolver = null;
        this.fNamespacesEnabled = false;
        this.fValidationEnabled = false;
        this.fLoadExternalDTD = true;
        this.fTempElementDecl = new XMLElementDecl();
        this.fTempAttributeDecl = new XMLAttributeDecl();
        this.fElementQName = new QName();
        this.fAttributeQName = new QName();
        this.fElementRefQName = new QName();
        this.fEventHandler = null;
        this.fDTDHandler = null;
        this.fStringPool = null;
        this.fErrorReporter = null;
        this.fEntityHandler = null;
        this.fEntityReader = null;
        this.fLiteralData = null;
        this.fReaderId = -1;
        this.fSystemLiteral = -1;
        this.fPubidLiteral = -1;
        this.fOpStack = null;
        this.fNodeIndexStack = null;
        this.fPrevNodeIndexStack = null;
        this.fScannerState = -1;
        this.fIncludeSectDepth = 0;
        this.fDoctypeReader = -1;
        this.fExternalSubsetReader = -1;
        this.fDefaultAttValueReader = -1;
        this.fDefaultAttValueElementType = -1;
        this.fDefaultAttValueAttrName = -1;
        this.fDefaultAttValueOffset = -1;
        this.fDefaultAttValueMark = -1;
        this.fEntityValueReader = -1;
        this.fEntityValueMark = -1;
        this.fXMLSymbol = -1;
        this.fXMLNamespace = -1;
        this.fXMLSpace = -1;
        this.fDefault = -1;
        this.fPreserve = -1;
        this.fScannerMarkupDepth = 0;
        this.fScannerParenDepth = 0;
        this.fElementDeclQName = new QName();
        this.fStringPool = fStringPool;
        this.fErrorReporter = fErrorReporter;
        this.fEntityHandler = fEntityHandler;
        this.fLiteralData = fLiteralData;
        this.init();
    }
    
    public void setEventHandler(final EventHandler fEventHandler) {
        this.fEventHandler = fEventHandler;
    }
    
    public void setDTDHandler(final XMLDocumentHandler.DTDHandler fdtdHandler) {
        this.fDTDHandler = fdtdHandler;
    }
    
    public void setGrammarResolver(final GrammarResolver fGrammarResolver) {
        this.fGrammarResolver = fGrammarResolver;
    }
    
    public void setNamespacesEnabled(final boolean fNamespacesEnabled) {
        this.fNamespacesEnabled = fNamespacesEnabled;
    }
    
    public void setValidationEnabled(final boolean fValidationEnabled) {
        this.fValidationEnabled = fValidationEnabled;
    }
    
    public void setLoadExternalDTD(final boolean fLoadExternalDTD) {
        this.fLoadExternalDTD = fLoadExternalDTD;
    }
    
    public boolean getReadingExternalEntity() {
        return this.fReaderId != this.fDoctypeReader;
    }
    
    public boolean getReadingContentSpec() {
        return this.getScannerState() == 57;
    }
    
    public int markupDepth() {
        return this.fScannerMarkupDepth;
    }
    
    private int increaseMarkupDepth() {
        return this.fScannerMarkupDepth++;
    }
    
    private int decreaseMarkupDepth() {
        return this.fScannerMarkupDepth--;
    }
    
    public int parenDepth() {
        return this.fScannerParenDepth;
    }
    
    private void setParenDepth(final int fScannerParenDepth) {
        this.fScannerParenDepth = fScannerParenDepth;
    }
    
    private void increaseParenDepth() {
        ++this.fScannerParenDepth;
    }
    
    private void decreaseParenDepth() {
        --this.fScannerParenDepth;
    }
    
    public void reset(final StringPool fStringPool, final XMLEntityHandler.CharBuffer fLiteralData) throws Exception {
        this.fStringPool = fStringPool;
        this.fLiteralData = fLiteralData;
        this.fEntityReader = null;
        this.fReaderId = -1;
        this.fSystemLiteral = -1;
        this.fPubidLiteral = -1;
        this.fOpStack = null;
        this.fNodeIndexStack = null;
        this.fPrevNodeIndexStack = null;
        this.fScannerState = -1;
        this.fIncludeSectDepth = 0;
        this.fDoctypeReader = -1;
        this.fExternalSubsetReader = -1;
        this.fDefaultAttValueReader = -1;
        this.fDefaultAttValueElementType = -1;
        this.fDefaultAttValueAttrName = -1;
        this.fDefaultAttValueOffset = -1;
        this.fDefaultAttValueMark = -1;
        this.fEntityValueReader = -1;
        this.fEntityValueMark = -1;
        this.fScannerMarkupDepth = 0;
        this.fScannerParenDepth = 0;
        this.init();
    }
    
    private void init() {
        this.fXMLSymbol = this.fStringPool.addSymbol("xml");
        this.fXMLNamespace = this.fStringPool.addSymbol("http://www.w3.org/XML/1998/namespace");
        this.fXMLSpace = this.fStringPool.addSymbol("xml:space");
        this.fDefault = this.fStringPool.addSymbol("default");
        this.fPreserve = this.fStringPool.addSymbol("preserve");
    }
    
    protected void reportRecoverableXMLError(final int n, final int n2, final int n3) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { this.fStringPool.toString(n3) }, 1);
    }
    
    protected void reportRecoverableXMLError(final int n, final int n2, final String s) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s }, 1);
    }
    
    protected void reportRecoverableXMLError(final int n, final int n2, final String s, final String s2) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s, s2 }, 1);
    }
    
    private void reportFatalXMLError(final int n, final int n2) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, null, 2);
    }
    
    private void reportFatalXMLError(final int n, final int n2, final int n3) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { this.fStringPool.toString(n3) }, 2);
    }
    
    private void reportFatalXMLError(final int n, final int n2, final String s) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s }, 2);
    }
    
    private void reportFatalXMLError(final int n, final int n2, final int n3, final int n4) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { this.fStringPool.toString(n3), this.fStringPool.toString(n4) }, 2);
    }
    
    private void reportFatalXMLError(final int n, final int n2, final String s, final String s2) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s, s2 }, 2);
    }
    
    private void reportFatalXMLError(final int n, final int n2, final String s, final String s2, final String s3) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s, s2, s3 }, 2);
    }
    
    private void abortMarkup(final int n, final int n2) throws Exception {
        this.reportFatalXMLError(n, n2);
        this.skipPastEndOfCurrentMarkup();
    }
    
    private void abortMarkup(final int n, final int n2, final int n3) throws Exception {
        this.reportFatalXMLError(n, n2, n3);
        this.skipPastEndOfCurrentMarkup();
    }
    
    private void abortMarkup(final int n, final int n2, final String s) throws Exception {
        this.reportFatalXMLError(n, n2, s);
        this.skipPastEndOfCurrentMarkup();
    }
    
    private void abortMarkup(final int n, final int n2, final int n3, final int n4) throws Exception {
        this.reportFatalXMLError(n, n2, n3, n4);
        this.skipPastEndOfCurrentMarkup();
    }
    
    private void skipPastEndOfCurrentMarkup() throws Exception {
        this.fEntityReader.skipToChar('>');
        if (this.fEntityReader.lookingAtChar('>', true)) {
            this.decreaseMarkupDepth();
        }
    }
    
    private int setScannerState(final int fScannerState) {
        final int fScannerState2 = this.fScannerState;
        this.fScannerState = fScannerState;
        return fScannerState2;
    }
    
    private int getScannerState() {
        return this.fScannerState;
    }
    
    private void restoreScannerState(final int fScannerState) {
        if (this.fScannerState != 0) {
            this.fScannerState = fScannerState;
        }
    }
    
    public void readerChange(final XMLEntityHandler.EntityReader fEntityReader, final int fReaderId) throws Exception {
        this.fEntityReader = fEntityReader;
        this.fReaderId = fReaderId;
        if (this.fScannerState == 56) {
            this.fDefaultAttValueOffset = this.fEntityReader.currentOffset();
            this.fDefaultAttValueMark = this.fDefaultAttValueOffset;
        }
        else if (this.fScannerState == 58) {
            this.fEntityValueMark = this.fEntityReader.currentOffset();
        }
    }
    
    public void endOfInput(final int n, final boolean b) throws Exception {
        if (this.fValidationEnabled) {
            final int readerDepth = this.fEntityHandler.getReaderDepth();
            if (this.getReadingContentSpec()) {
                if (readerDepth != this.parenDepth()) {
                    this.reportRecoverableXMLError(74, 75, n);
                }
            }
            else if (readerDepth != this.markupDepth()) {
                this.reportRecoverableXMLError(73, 74, n);
            }
        }
        final boolean b2 = this.fReaderId != this.fExternalSubsetReader;
        switch (this.fScannerState) {
            case -1: {
                throw new RuntimeException("FWK004 XMLDTDScanner.endOfInput: cannot happen: 2\n2");
            }
            case 0: {
                break;
            }
            case 51: {
                if (!b2 && this.fIncludeSectDepth > 0) {
                    this.reportFatalXMLError(23, 22);
                    break;
                }
                break;
            }
            case 50: {
                throw new RuntimeException("FWK004 XMLDTDScanner.endOfInput: cannot happen: 2.5\n2.5");
            }
            case 53: {
                break;
            }
            case 59: {
                if (!b2) {
                    this.reportFatalXMLError(100, 140);
                    break;
                }
                break;
            }
            case 60: {
                if (!b2) {
                    this.reportFatalXMLError(101, 141);
                    break;
                }
                break;
            }
            case 54: {
                if (!b2 && !this.getReadingExternalEntity()) {
                    this.reportFatalXMLError(163, 133);
                    break;
                }
                break;
            }
            case 55: {
                if (!b2) {
                    this.reportFatalXMLError(164, 134);
                    break;
                }
                this.reportFatalXMLError(165, 68);
                break;
            }
            case 56: {
                if (!b2) {
                    this.reportFatalXMLError(151, 136, this.fDefaultAttValueElementType, this.fDefaultAttValueAttrName);
                    break;
                }
                if (this.fReaderId == this.fDefaultAttValueReader) {
                    break;
                }
                this.fEntityReader.append(this.fLiteralData, this.fDefaultAttValueMark, this.fDefaultAttValueOffset - this.fDefaultAttValueMark);
                break;
            }
            case 57: {
                break;
            }
            case 58: {
                if (this.fReaderId == this.fEntityValueReader) {
                    break;
                }
                this.fEntityReader.append(this.fLiteralData, this.fEntityValueMark, this.fEntityReader.currentOffset() - this.fEntityValueMark);
                break;
            }
            default: {
                throw new RuntimeException("FWK004 XMLDTDScanner.endOfInput: cannot happen: 3\n3");
            }
        }
        if (!b2) {
            this.setScannerState(0);
        }
    }
    
    private int scanCharRef() throws Exception {
        final int currentOffset = this.fEntityReader.currentOffset();
        final boolean lookingAtChar = this.fEntityReader.lookingAtChar('x', true);
        int scanCharRef = this.fEntityReader.scanCharRef(lookingAtChar);
        if (scanCharRef < 0) {
            switch (scanCharRef) {
                case -1: {
                    this.reportFatalXMLError(18, 17);
                    return -1;
                }
                case -2: {
                    this.reportFatalXMLError(lookingAtChar ? 17 : 16, lookingAtChar ? 16 : 15);
                    return -1;
                }
                case -3: {
                    scanCharRef = 1114112;
                    break;
                }
            }
        }
        if (scanCharRef < 32) {
            if (scanCharRef == 9 || scanCharRef == 10 || scanCharRef == 13) {
                return scanCharRef;
            }
        }
        else if (scanCharRef <= 55295 || (scanCharRef >= 57344 && (scanCharRef <= 65533 || (scanCharRef >= 65536 && scanCharRef <= 1114111)))) {
            return scanCharRef;
        }
        this.reportFatalXMLError(11, 9, this.fEntityReader.addString(currentOffset, this.fEntityReader.currentOffset() - currentOffset));
        return -1;
    }
    
    private void scanComment() throws Exception {
        final int currentOffset = this.fEntityReader.currentOffset();
        int n = 0;
        final int setScannerState = this.setScannerState(54);
        while (this.fScannerState == 54) {
            if (this.fEntityReader.lookingAtChar('-', false)) {
                int currentOffset2 = this.fEntityReader.currentOffset();
                int n2 = 0;
                this.fEntityReader.lookingAtChar('-', true);
                int n3 = this.fEntityReader.currentOffset();
                int n4 = 1;
                while (this.fEntityReader.lookingAtChar('-', true)) {
                    ++n4;
                    n2 = currentOffset2;
                    currentOffset2 = n3;
                    n3 = this.fEntityReader.currentOffset();
                }
                if (n4 <= 1) {
                    continue;
                }
                if (this.fEntityReader.lookingAtChar('>', true)) {
                    if (n == 0 && n4 > 2) {
                        this.reportFatalXMLError(9, 7);
                    }
                    this.decreaseMarkupDepth();
                    final int addString = this.fEntityReader.addString(currentOffset, n2 - currentOffset);
                    this.fDTDGrammar.callComment(addString);
                    if (this.fDTDHandler != null) {
                        this.fDTDHandler.comment(addString);
                    }
                    this.restoreScannerState(setScannerState);
                    return;
                }
                if (n != 0) {
                    continue;
                }
                this.reportFatalXMLError(9, 7);
                n = 1;
            }
            else {
                if (this.fEntityReader.lookingAtValidChar(true)) {
                    continue;
                }
                final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                if (this.fScannerState == 0 || scanInvalidChar < 0) {
                    continue;
                }
                this.reportFatalXMLError(10, 8, Integer.toHexString(scanInvalidChar));
            }
        }
        this.restoreScannerState(setScannerState);
    }
    
    private void scanPI(final int n) throws Exception {
        final String string = this.fStringPool.toString(n);
        if (string.length() == 3 && (string.charAt(0) == 'X' || string.charAt(0) == 'x') && (string.charAt(1) == 'M' || string.charAt(1) == 'm') && (string.charAt(2) == 'L' || string.charAt(2) == 'l')) {
            this.abortMarkup(6, 4);
            return;
        }
        final int setScannerState = this.setScannerState(55);
        int currentOffset = -1;
        int n2 = 0;
        if (!this.fEntityReader.lookingAtSpace(true)) {
            if (!this.fEntityReader.lookingAtChar('?', true) || !this.fEntityReader.lookingAtChar('>', true)) {
                if (this.fScannerState != 0) {
                    this.abortMarkup(7, 5);
                    this.restoreScannerState(setScannerState);
                }
                return;
            }
            this.decreaseMarkupDepth();
            this.restoreScannerState(setScannerState);
        }
        else {
            this.fEntityReader.skipPastSpaces();
            currentOffset = this.fEntityReader.currentOffset();
            while (this.fScannerState == 55) {
                while (this.fEntityReader.lookingAtChar('?', false)) {
                    final int currentOffset2 = this.fEntityReader.currentOffset();
                    this.fEntityReader.lookingAtChar('?', true);
                    if (this.fEntityReader.lookingAtChar('>', true)) {
                        n2 = currentOffset2 - currentOffset;
                        this.decreaseMarkupDepth();
                        this.restoreScannerState(setScannerState);
                        break;
                    }
                }
                if (this.fScannerState != 55) {
                    break;
                }
                if (!this.fEntityReader.lookingAtValidChar(true)) {
                    final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                    if (this.fScannerState != 0) {
                        if (scanInvalidChar >= 0) {
                            this.reportFatalXMLError(8, 6, Integer.toHexString(scanInvalidChar));
                        }
                        this.skipPastEndOfCurrentMarkup();
                        this.restoreScannerState(setScannerState);
                    }
                    return;
                }
            }
        }
        final int n3 = (n2 == 0) ? 0 : this.fEntityReader.addString(currentOffset, n2);
        this.fDTDGrammar.callProcessingInstruction(n, n3);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.processingInstruction(n, n3);
        }
    }
    
    public boolean scanDoctypeDecl() throws Exception {
        (this.fDTDGrammar = new DTDGrammar(this.fStringPool)).callStartDTD();
        this.increaseMarkupDepth();
        this.fEntityReader = this.fEntityHandler.getEntityReader();
        this.fReaderId = this.fEntityHandler.getReaderId();
        this.fDoctypeReader = this.fReaderId;
        this.setScannerState(50);
        if (!this.fEntityReader.lookingAtSpace(true)) {
            this.abortMarkup(159, 120);
            return false;
        }
        this.fEntityReader.skipPastSpaces();
        this.scanElementType(this.fEntityReader, ' ', this.fElementQName);
        if (this.fElementQName.rawname == -1) {
            this.abortMarkup(153, 119);
            return false;
        }
        boolean b = false;
        int fPubidLiteral = -1;
        int fSystemLiteral = -1;
        boolean b2;
        if (this.fEntityReader.lookingAtSpace(true)) {
            this.fEntityReader.skipPastSpaces();
            if (!(b2 = this.fEntityReader.lookingAtChar('[', true)) && !this.fEntityReader.lookingAtChar('>', false)) {
                if (!this.scanExternalID(false)) {
                    this.skipPastEndOfCurrentMarkup();
                    return false;
                }
                if (this.fValidationEnabled || this.fLoadExternalDTD) {
                    b = true;
                }
                fPubidLiteral = this.fPubidLiteral;
                fSystemLiteral = this.fSystemLiteral;
                this.fEntityReader.skipPastSpaces();
                b2 = this.fEntityReader.lookingAtChar('[', true);
            }
        }
        else {
            b2 = this.fEntityReader.lookingAtChar('[', true);
        }
        this.fDTDGrammar.doctypeDecl(this.fElementQName, fPubidLiteral, fSystemLiteral);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startDTD(this.fElementQName, fPubidLiteral, fSystemLiteral);
        }
        if (b2) {
            this.scanDecls(false);
            this.fEntityReader.skipPastSpaces();
        }
        if (!this.fEntityReader.lookingAtChar('>', true)) {
            if (this.fScannerState != 0) {
                this.abortMarkup(147, 121, this.fElementQName.rawname);
            }
            return false;
        }
        this.decreaseMarkupDepth();
        if (b) {
            ((DefaultEntityHandler)this.fEntityHandler).startReadingFromExternalSubset(this.fStringPool.toString(fPubidLiteral), this.fStringPool.toString(fSystemLiteral), this.markupDepth());
            this.fDTDGrammar.startReadingFromExternalSubset(fPubidLiteral, fSystemLiteral);
        }
        else {
            this.fDTDGrammar.callEndDTD();
            if (this.fDTDHandler != null) {
                this.fDTDHandler.endDTD();
            }
        }
        this.fGrammarResolver.putGrammar("", this.fDTDGrammar);
        return true;
    }
    
    private boolean scanExternalID(final boolean b) throws Exception {
        this.fSystemLiteral = -1;
        this.fPubidLiteral = -1;
        this.fEntityReader.currentOffset();
        if (this.fEntityReader.skippedString(XMLDTDScanner.system_string)) {
            if (!this.fEntityReader.lookingAtSpace(true)) {
                this.reportFatalXMLError(160, 131);
                return false;
            }
            this.fEntityReader.skipPastSpaces();
            if (this.getReadingExternalEntity()) {
                this.checkForPEReference(false);
            }
            return this.scanSystemLiteral();
        }
        else {
            if (!this.fEntityReader.skippedString(XMLDTDScanner.public_string)) {
                this.reportFatalXMLError(50, 52);
                return false;
            }
            if (!this.fEntityReader.lookingAtSpace(true)) {
                this.reportFatalXMLError(158, 131);
                return false;
            }
            this.fEntityReader.skipPastSpaces();
            if (!this.scanPubidLiteral()) {
                return false;
            }
            if (b) {
                if (!this.fEntityReader.lookingAtSpace(true)) {
                    return true;
                }
                this.fEntityReader.skipPastSpaces();
                if (this.fEntityReader.lookingAtChar('>', false)) {
                    return true;
                }
            }
            else {
                if (!this.fEntityReader.lookingAtSpace(true)) {
                    this.reportFatalXMLError(154, 131);
                    return false;
                }
                this.fEntityReader.skipPastSpaces();
            }
            return this.scanSystemLiteral();
        }
    }
    
    private boolean scanSystemLiteral() throws Exception {
        final boolean lookingAtChar;
        if (!(lookingAtChar = this.fEntityReader.lookingAtChar('\'', true)) && !this.fEntityReader.lookingAtChar('\"', true)) {
            this.reportFatalXMLError(19, 18);
            return false;
        }
        final int setScannerState = this.setScannerState(59);
        final int currentOffset = this.fEntityReader.currentOffset();
        final int n = lookingAtChar ? 39 : 34;
        boolean b = true;
        boolean b2 = false;
        while (!this.fEntityReader.lookingAtChar((char)n, false)) {
            if (this.fEntityReader.lookingAtChar('#', true)) {
                b2 = true;
            }
            else {
                if (this.fEntityReader.lookingAtValidChar(true)) {
                    continue;
                }
                b = false;
                final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                if (this.fScannerState == 0) {
                    return false;
                }
                if (scanInvalidChar < 0) {
                    continue;
                }
                this.reportFatalXMLError(20, 19, Integer.toHexString(scanInvalidChar));
            }
        }
        if (b) {
            this.fSystemLiteral = this.fEntityReader.addString(currentOffset, this.fEntityReader.currentOffset() - currentOffset);
            if (b2) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 133, 142, new Object[] { this.fStringPool.toString(this.fSystemLiteral) }, 1);
            }
        }
        this.fEntityReader.lookingAtChar((char)n, true);
        this.restoreScannerState(setScannerState);
        return b;
    }
    
    private boolean scanPubidLiteral() throws Exception {
        final boolean lookingAtChar;
        if (!(lookingAtChar = this.fEntityReader.lookingAtChar('\'', true)) && !this.fEntityReader.lookingAtChar('\"', true)) {
            this.reportFatalXMLError(21, 20);
            return false;
        }
        final int n = lookingAtChar ? 39 : 34;
        final int setScannerState = this.setScannerState(60);
        boolean b = true;
        do {
            if (this.fEntityReader.lookingAtChar('\t', true)) {
                b = false;
                this.reportFatalXMLError(60, 21, "9");
            }
        } while (this.fEntityReader.lookingAtSpace(true));
        int n2 = this.fEntityReader.currentOffset();
        final int length = this.fLiteralData.length();
        int currentOffset = n2;
    Label_0441:
        while (true) {
            while (!this.fEntityReader.lookingAtChar((char)n, true)) {
                if (this.fEntityReader.lookingAtChar('\t', true)) {
                    b = false;
                    this.reportFatalXMLError(60, 21, "9");
                }
                else {
                    if (this.fEntityReader.lookingAtSpace(true)) {
                        if (b && n2 - currentOffset > 0) {
                            this.fEntityReader.append(this.fLiteralData, currentOffset, n2 - currentOffset);
                        }
                        while (true) {
                            while (!this.fEntityReader.lookingAtChar('\t', true)) {
                                if (!this.fEntityReader.lookingAtSpace(true)) {
                                    if (this.fEntityReader.lookingAtChar((char)n, true)) {
                                        break Label_0441;
                                    }
                                    if (b) {
                                        this.fLiteralData.append(' ');
                                        n2 = (currentOffset = this.fEntityReader.currentOffset());
                                        continue Label_0441;
                                    }
                                    continue Label_0441;
                                }
                            }
                            b = false;
                            this.reportFatalXMLError(60, 21, "9");
                            continue;
                        }
                        if (b) {
                            this.fPubidLiteral = this.fLiteralData.addString(length, this.fLiteralData.length() - length);
                            final String string = this.fStringPool.toString(this.fPubidLiteral);
                            final int validPublicId = this.validPublicId(string);
                            if (validPublicId >= 0) {
                                this.reportFatalXMLError(60, 21, Integer.toHexString(string.charAt(validPublicId)));
                                return false;
                            }
                        }
                        this.restoreScannerState(setScannerState);
                        return b;
                    }
                    if (!this.fEntityReader.lookingAtValidChar(true)) {
                        final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                        if (this.fScannerState == 0) {
                            return false;
                        }
                        b = false;
                        if (scanInvalidChar >= 0) {
                            this.reportFatalXMLError(22, 21, Integer.toHexString(scanInvalidChar));
                        }
                    }
                    if (!b) {
                        continue;
                    }
                    n2 = this.fEntityReader.currentOffset();
                }
            }
            if (b && n2 - currentOffset > 0) {
                this.fEntityReader.append(this.fLiteralData, currentOffset, n2 - currentOffset);
            }
            continue Label_0441;
        }
    }
    
    public void scanDecls(final boolean b) throws Exception {
        final int currentOffset = this.fEntityReader.currentOffset();
        if (b) {
            this.fExternalSubsetReader = this.fReaderId;
        }
        this.fIncludeSectDepth = 0;
        int n = b ? 1 : 0;
        final int setScannerState = this.setScannerState(51);
        while (this.fScannerState == 51) {
            int startReadingFromEntity = 0;
            if (!b && this.fEntityReader.lookingAtChar(']', false)) {
                final int addString = this.fEntityReader.addString(currentOffset, this.fEntityReader.currentOffset() - currentOffset);
                this.fDTDGrammar.internalSubset(addString);
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.internalSubset(addString);
                }
                this.fEntityReader.lookingAtChar(']', true);
                this.restoreScannerState(setScannerState);
                return;
            }
            if (this.fEntityReader.lookingAtChar('<', true)) {
                this.markupDepth();
                this.increaseMarkupDepth();
                if (this.fEntityReader.lookingAtChar('!', true)) {
                    if (this.fEntityReader.lookingAtChar('-', true)) {
                        if (this.fEntityReader.lookingAtChar('-', true)) {
                            this.scanComment();
                        }
                        else {
                            this.abortMarkup(150, 122);
                        }
                    }
                    else if (this.fEntityReader.lookingAtChar('[', true) && this.getReadingExternalEntity()) {
                        this.checkForPEReference(false);
                        if (this.fEntityReader.skippedString(XMLDTDScanner.include_string)) {
                            this.checkForPEReference(false);
                            if (!this.fEntityReader.lookingAtChar('[', true)) {
                                this.abortMarkup(150, 122);
                            }
                            else {
                                ++this.fIncludeSectDepth;
                            }
                        }
                        else if (this.fEntityReader.skippedString(XMLDTDScanner.ignore_string)) {
                            this.checkForPEReference(false);
                            if (!this.fEntityReader.lookingAtChar('[', true)) {
                                this.abortMarkup(150, 122);
                            }
                            else {
                                this.scanIgnoreSectContents();
                            }
                        }
                        else {
                            this.abortMarkup(150, 122);
                        }
                    }
                    else if (this.fEntityReader.skippedString(XMLDTDScanner.element_string)) {
                        this.scanElementDecl();
                    }
                    else if (this.fEntityReader.skippedString(XMLDTDScanner.attlist_string)) {
                        this.scanAttlistDecl();
                    }
                    else if (this.fEntityReader.skippedString(XMLDTDScanner.entity_string)) {
                        this.scanEntityDecl();
                    }
                    else if (this.fEntityReader.skippedString(XMLDTDScanner.notation_string)) {
                        this.scanNotationDecl();
                    }
                    else {
                        this.abortMarkup(150, 122);
                    }
                }
                else if (this.fEntityReader.lookingAtChar('?', true)) {
                    final int scanName = this.fEntityReader.scanName(' ');
                    if (scanName == -1) {
                        this.abortMarkup(131, 106);
                    }
                    else if ("xml".equals(this.fStringPool.toString(scanName))) {
                        if (this.fEntityReader.lookingAtSpace(true)) {
                            if (n != 0) {
                                this.scanTextDecl();
                            }
                            else {
                                this.abortMarkup(105, 138);
                            }
                        }
                        else {
                            this.abortMarkup(6, 4);
                        }
                    }
                    else {
                        this.scanPI(scanName);
                    }
                }
                else {
                    this.abortMarkup(150, 122);
                }
            }
            else if (this.fEntityReader.lookingAtSpace(true)) {
                this.fEntityReader.skipPastSpaces();
            }
            else if (this.fEntityReader.lookingAtChar('%', true)) {
                final int currentOffset2 = this.fEntityReader.currentOffset();
                this.fEntityReader.skipPastName(';');
                final int n2 = this.fEntityReader.currentOffset() - currentOffset2;
                if (n2 == 0) {
                    this.reportFatalXMLError(48, 50);
                }
                else if (!this.fEntityReader.lookingAtChar(';', true)) {
                    this.reportFatalXMLError(49, 51, this.fEntityReader.addString(currentOffset2, n2));
                }
                else {
                    startReadingFromEntity = (this.fEntityHandler.startReadingFromEntity(this.fEntityReader.addSymbol(currentOffset2, n2), this.markupDepth(), 3) ? 1 : 0);
                }
            }
            else if (this.fIncludeSectDepth > 0 && this.fEntityReader.lookingAtChar(']', true)) {
                if (!this.fEntityReader.lookingAtChar(']', true) || !this.fEntityReader.lookingAtChar('>', true)) {
                    this.abortMarkup(23, 22);
                }
                else {
                    this.decreaseMarkupDepth();
                }
                --this.fIncludeSectDepth;
            }
            else if (!this.fEntityReader.lookingAtValidChar(false)) {
                final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                if (this.fScannerState == 0) {
                    break;
                }
                if (scanInvalidChar >= 0) {
                    if (!b) {
                        this.reportFatalXMLError(44, 46, Integer.toHexString(scanInvalidChar));
                    }
                    else {
                        this.reportFatalXMLError(45, 47, Integer.toHexString(scanInvalidChar));
                    }
                }
            }
            else {
                this.reportFatalXMLError(150, 122);
                this.fEntityReader.lookingAtValidChar(true);
            }
            n = startReadingFromEntity;
        }
        if (b) {
            ((DefaultEntityHandler)this.fEntityHandler).stopReadingFromExternalSubset();
            this.fDTDGrammar.stopReadingFromExternalSubset();
            this.fDTDGrammar.callEndDTD();
            if (this.fDTDHandler != null) {
                this.fDTDHandler.endDTD();
            }
            this.fGrammarResolver.putGrammar("", this.fDTDGrammar);
        }
    }
    
    private void scanIgnoreSectContents() throws Exception {
        final int n = ++this.fIncludeSectDepth;
        while (true) {
            if (this.fEntityReader.lookingAtChar('<', true)) {
                if (!this.fEntityReader.lookingAtChar('!', true) || !this.fEntityReader.lookingAtChar('[', true)) {
                    continue;
                }
                ++this.fIncludeSectDepth;
            }
            else if (this.fEntityReader.lookingAtChar(']', true)) {
                if (!this.fEntityReader.lookingAtChar(']', true)) {
                    continue;
                }
                while (this.fEntityReader.lookingAtChar(']', true)) {}
                if (this.fEntityReader.lookingAtChar('>', true) && this.fIncludeSectDepth-- == n) {
                    this.decreaseMarkupDepth();
                    return;
                }
                continue;
            }
            else {
                if (this.fEntityReader.lookingAtValidChar(true)) {
                    continue;
                }
                final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                if (this.fScannerState == 0) {
                    return;
                }
                if (scanInvalidChar < 0) {
                    continue;
                }
                this.reportFatalXMLError(25, 24, Integer.toHexString(scanInvalidChar));
            }
        }
    }
    
    private void scanTextDecl() throws Exception {
        int n = -1;
        int n2 = -1;
        final int setScannerState = this.setScannerState(53);
        int i = 0;
        do {
            this.fEntityReader.skipPastSpaces();
            final int currentOffset = this.fEntityReader.currentOffset();
            if (i == 0 && this.fEntityReader.skippedString(XMLDTDScanner.version_string)) {
                i = 1;
            }
            else {
                if (!this.fEntityReader.skippedString(XMLDTDScanner.encoding_string)) {
                    this.abortMarkup(29, 28);
                    this.restoreScannerState(setScannerState);
                    return;
                }
                i = 2;
            }
            final int n3 = this.fEntityReader.currentOffset() - currentOffset;
            this.fEntityReader.skipPastSpaces();
            if (!this.fEntityReader.lookingAtChar('=', true)) {
                this.abortMarkup(32, (i == 1) ? 30 : 32, this.fEntityReader.addString(currentOffset, n3));
                this.restoreScannerState(setScannerState);
                return;
            }
            this.fEntityReader.skipPastSpaces();
            final int scanStringLiteral = this.fEntityReader.scanStringLiteral();
            switch (scanStringLiteral) {
                case -1: {
                    this.abortMarkup(34, (i == 1) ? 33 : 35, this.fEntityReader.addString(currentOffset, n3));
                    this.restoreScannerState(setScannerState);
                    return;
                }
                case -2: {
                    final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                    if (this.fScannerState != 0) {
                        if (scanInvalidChar >= 0) {
                            this.reportFatalXMLError(36, (i == 1) ? 36 : 38, Integer.toHexString(scanInvalidChar));
                        }
                        this.skipPastEndOfCurrentMarkup();
                        this.restoreScannerState(setScannerState);
                    }
                    return;
                }
                default: {
                    switch (i) {
                        case 1: {
                            n = scanStringLiteral;
                            final String string = this.fStringPool.toString(n);
                            if (!"1.0".equals(string)) {
                                if (!this.validVersionNum(string)) {
                                    this.abortMarkup(37, 39, string);
                                    this.restoreScannerState(setScannerState);
                                    return;
                                }
                                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 38, 40, new Object[] { string }, 1);
                            }
                            if (!this.fEntityReader.lookingAtSpace(true)) {
                                this.abortMarkup(39, 41);
                                this.restoreScannerState(setScannerState);
                                return;
                            }
                            continue;
                        }
                        case 2: {
                            n2 = scanStringLiteral;
                            final String string2 = this.fStringPool.toString(n2);
                            if (!this.validEncName(string2)) {
                                this.abortMarkup(40, 42, string2);
                                this.restoreScannerState(setScannerState);
                                return;
                            }
                            this.fEntityReader.skipPastSpaces();
                            i = 3;
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                    break;
                }
            }
        } while (i != 3);
        if (!this.fEntityReader.lookingAtChar('?', true) || !this.fEntityReader.lookingAtChar('>', true)) {
            this.abortMarkup(43, 45);
            this.restoreScannerState(setScannerState);
            return;
        }
        this.decreaseMarkupDepth();
        this.fDTDGrammar.callTextDecl(n, n2);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.textDecl(n, n2);
        }
        this.restoreScannerState(setScannerState);
    }
    
    private void scanElementDecl() throws Exception {
        if (!this.checkForPEReference(true)) {
            this.abortMarkup(156, 125);
            return;
        }
        this.checkForElementTypeWithPEReference(this.fEntityReader, ' ', this.fElementQName);
        if (this.fElementQName.rawname == -1) {
            this.abortMarkup(148, 124);
            return;
        }
        if (this.fDTDHandler != null) {
            this.fElementDeclQName.setValues(this.fElementQName);
        }
        if (!this.checkForPEReference(true)) {
            this.abortMarkup(155, 125, this.fElementQName.rawname);
            return;
        }
        int contentSpecIndex = -1;
        int type;
        if (this.fEntityReader.skippedString(XMLDTDScanner.empty_string)) {
            type = 0;
        }
        else if (this.fEntityReader.skippedString(XMLDTDScanner.any_string)) {
            type = 1;
        }
        else {
            if (!this.fEntityReader.lookingAtChar('(', true)) {
                this.abortMarkup(146, 123, this.fElementQName.rawname);
                return;
            }
            final int fReaderId = this.fReaderId;
            final int readerDepth = this.fEntityHandler.getReaderDepth();
            final int setScannerState = this.setScannerState(57);
            final int parenDepth = this.parenDepth();
            this.fEntityHandler.setReaderDepth(parenDepth);
            this.increaseParenDepth();
            this.checkForPEReference(false);
            if (this.fEntityReader.skippedString(XMLDTDScanner.pcdata_string)) {
                type = 2;
                contentSpecIndex = this.scanMixed(this.fElementQName);
            }
            else {
                type = 3;
                contentSpecIndex = this.scanChildren(this.fElementQName);
            }
            final boolean b = contentSpecIndex != -1;
            this.restoreScannerState(setScannerState);
            this.fEntityHandler.setReaderDepth(readerDepth);
            if (!b) {
                this.setParenDepth(parenDepth);
                this.skipPastEndOfCurrentMarkup();
                return;
            }
            if (this.parenDepth() != parenDepth) {}
        }
        this.checkForPEReference(false);
        if (!this.fEntityReader.lookingAtChar('>', true)) {
            this.abortMarkup(106, 126, this.fElementQName.rawname);
            return;
        }
        this.decreaseMarkupDepth();
        int n = this.fDTDGrammar.getElementDeclIndex(this.fElementQName, -1);
        final boolean readingExternalEntity = this.getReadingExternalEntity();
        if (n == -1) {
            n = this.fDTDGrammar.addElementDecl(this.fElementQName, type, contentSpecIndex, readingExternalEntity);
        }
        else {
            this.fDTDGrammar.getElementDecl(n, this.fTempElementDecl);
            if (this.fTempElementDecl.type == -1) {
                this.fTempElementDecl.type = type;
                this.fTempElementDecl.contentSpecIndex = contentSpecIndex;
                this.fDTDGrammar.setElementDeclDTD(n, this.fTempElementDecl);
                this.fDTDGrammar.setElementDeclIsExternal(n, readingExternalEntity);
            }
            else if (this.fValidationEnabled) {
                this.reportRecoverableXMLError(89, 82, this.fStringPool.toString(this.fElementQName.rawname));
            }
        }
        if (this.fDTDHandler != null) {
            this.fDTDGrammar.getElementDecl(n, this.fTempElementDecl);
            this.fDTDHandler.elementDecl(this.fElementDeclQName, type, contentSpecIndex, this.fDTDGrammar);
        }
    }
    
    private int scanMixed(final QName qName) throws Exception {
        int i = -1;
        int n = -1;
        int n2 = 0;
        int[] array = new int[32];
        int n3 = 0;
        int n4 = 0;
        int n5 = -1;
        do {
            if (this.fValidationEnabled) {
                for (int j = 0; j < n3; ++j) {
                    if (array[j] == i) {
                        n4 = 1;
                        break;
                    }
                }
            }
            if (n4 != 0 && this.fValidationEnabled) {
                this.reportRecoverableXMLError(67, 67, i);
                n4 = 0;
            }
            else {
                try {
                    array[n3] = i;
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    final int[] array2 = new int[array.length * 2];
                    System.arraycopy(array, 0, array2, 0, array.length);
                    array = array2;
                    array[n3] = i;
                }
                ++n3;
                n5 = this.fDTDGrammar.addUniqueLeafNode(i);
            }
            this.checkForPEReference(false);
            if (!this.fEntityReader.lookingAtChar('|', true)) {
                if (!this.fEntityReader.lookingAtChar(')', true)) {
                    this.reportFatalXMLError(145, 129, qName.rawname);
                    return -1;
                }
                this.decreaseParenDepth();
                if (n5 == -1) {
                    n5 = n;
                }
                else if (n != -1) {
                    n5 = this.fDTDGrammar.addContentSpecNode(4, n, n5);
                }
                if (this.fEntityReader.lookingAtChar('*', true)) {
                    n5 = this.fDTDGrammar.addContentSpecNode(2, n5);
                }
                else if (n2 != 0) {
                    this.reportFatalXMLError(47, 49, this.fStringPool.toString(qName.rawname), this.fDTDGrammar.getContentSpecNodeAsString(n5));
                    return -1;
                }
                return n5;
            }
            else {
                if (n5 != -1) {
                    if (n != -1) {
                        n5 = this.fDTDGrammar.addContentSpecNode(4, n, n5);
                    }
                    n = n5;
                }
                n2 = 1;
                this.checkForPEReference(false);
                this.checkForElementTypeWithPEReference(this.fEntityReader, ')', this.fElementRefQName);
                i = this.fElementRefQName.rawname;
            }
        } while (i != -1);
        this.reportFatalXMLError(149, 130, qName.rawname);
        return -1;
    }
    
    private int scanChildren(final QName qName) throws Exception {
        int i = 1;
        this.initializeContentModelStack(i);
    Label_0010:
        while (true) {
            if (this.fEntityReader.lookingAtChar('(', true)) {
                this.increaseParenDepth();
                this.checkForPEReference(false);
                ++i;
                this.initializeContentModelStack(i);
            }
            else {
                this.checkForElementTypeWithPEReference(this.fEntityReader, ')', this.fElementRefQName);
                final int rawname = this.fElementRefQName.rawname;
                if (rawname == -1) {
                    this.reportFatalXMLError(152, 128, qName.rawname);
                    return -1;
                }
                this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(0, rawname);
                if (this.fEntityReader.lookingAtChar('?', true)) {
                    this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(1, this.fNodeIndexStack[i]);
                }
                else if (this.fEntityReader.lookingAtChar('*', true)) {
                    this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(2, this.fNodeIndexStack[i]);
                }
                else if (this.fEntityReader.lookingAtChar('+', true)) {
                    this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(3, this.fNodeIndexStack[i]);
                }
                do {
                    this.checkForPEReference(false);
                    if (this.fOpStack[i] != 5 && this.fEntityReader.lookingAtChar('|', true)) {
                        if (this.fPrevNodeIndexStack[i] != -1) {
                            this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(this.fOpStack[i], this.fPrevNodeIndexStack[i], this.fNodeIndexStack[i]);
                        }
                        this.fPrevNodeIndexStack[i] = this.fNodeIndexStack[i];
                        this.fOpStack[i] = 4;
                    }
                    else if (this.fOpStack[i] != 4 && this.fEntityReader.lookingAtChar(',', true)) {
                        if (this.fPrevNodeIndexStack[i] != -1) {
                            this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(this.fOpStack[i], this.fPrevNodeIndexStack[i], this.fNodeIndexStack[i]);
                        }
                        this.fPrevNodeIndexStack[i] = this.fNodeIndexStack[i];
                        this.fOpStack[i] = 5;
                    }
                    else {
                        if (!this.fEntityReader.lookingAtChar(')', true)) {
                            this.reportFatalXMLError(98, 127, qName.rawname);
                        }
                        this.decreaseParenDepth();
                        if (this.fPrevNodeIndexStack[i] != -1) {
                            this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(this.fOpStack[i], this.fPrevNodeIndexStack[i], this.fNodeIndexStack[i]);
                        }
                        this.fNodeIndexStack[i] = this.fNodeIndexStack[i--];
                        if (this.fEntityReader.lookingAtChar('?', true)) {
                            this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(1, this.fNodeIndexStack[i]);
                            continue;
                        }
                        if (this.fEntityReader.lookingAtChar('*', true)) {
                            this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(2, this.fNodeIndexStack[i]);
                            continue;
                        }
                        if (this.fEntityReader.lookingAtChar('+', true)) {
                            this.fNodeIndexStack[i] = this.fDTDGrammar.addContentSpecNode(3, this.fNodeIndexStack[i]);
                            continue;
                        }
                        continue;
                    }
                    this.checkForPEReference(false);
                    continue Label_0010;
                } while (i != 0);
                return this.fNodeIndexStack[0];
            }
        }
    }
    
    private void scanAttlistDecl() throws Exception {
        if (!this.checkForPEReference(true)) {
            this.abortMarkup(141, 112);
            return;
        }
        this.checkForElementTypeWithPEReference(this.fEntityReader, ' ', this.fElementQName);
        final int rawname = this.fElementQName.rawname;
        if (rawname == -1) {
            this.abortMarkup(125, 111);
            return;
        }
        if (this.fDTDGrammar.getElementDeclIndex(this.fElementQName, -1) == -1) {
            this.fDTDGrammar.addElementDecl(this.fElementQName);
        }
        final boolean checkForPEReference = this.checkForPEReference(true);
        if (this.fEntityReader.lookingAtChar('>', true)) {
            this.decreaseMarkupDepth();
            return;
        }
        if (!checkForPEReference) {
            if (this.fEntityReader.lookingAtSpace(true)) {
                this.fEntityReader.skipPastSpaces();
            }
            else {
                this.reportFatalXMLError(138, 113);
            }
        }
        else if (this.fEntityReader.lookingAtSpace(true)) {
            this.fEntityReader.skipPastSpaces();
        }
        if (this.fEntityReader.lookingAtChar('>', true)) {
            this.decreaseMarkupDepth();
            return;
        }
        while (true) {
            this.checkForAttributeNameWithPEReference(this.fEntityReader, ' ', this.fAttributeQName);
            final int rawname2 = this.fAttributeQName.rawname;
            if (rawname2 == -1) {
                this.abortMarkup(58, 70, this.fElementQName.rawname);
                return;
            }
            if (!this.checkForPEReference(true)) {
                this.abortMarkup(139, 113);
                return;
            }
            boolean b = false;
            int n = -1;
            int n2;
            if (this.fEntityReader.skippedString(XMLDTDScanner.cdata_string)) {
                n2 = 0;
            }
            else if (this.fEntityReader.skippedString(XMLDTDScanner.id_string)) {
                if (!this.fEntityReader.skippedString(XMLDTDScanner.ref_string)) {
                    n2 = 3;
                }
                else if (!this.fEntityReader.lookingAtChar('S', true)) {
                    n2 = 4;
                }
                else {
                    n2 = 4;
                    b = true;
                }
            }
            else if (this.fEntityReader.skippedString(XMLDTDScanner.entit_string)) {
                if (this.fEntityReader.lookingAtChar('Y', true)) {
                    n2 = 1;
                }
                else {
                    if (!this.fEntityReader.skippedString(XMLDTDScanner.ies_string)) {
                        this.abortMarkup(59, 71, rawname, rawname2);
                        return;
                    }
                    n2 = 1;
                    b = true;
                }
            }
            else if (this.fEntityReader.skippedString(XMLDTDScanner.nmtoken_string)) {
                if (this.fEntityReader.lookingAtChar('S', true)) {
                    n2 = 5;
                    b = true;
                }
                else {
                    n2 = 5;
                }
            }
            else if (this.fEntityReader.skippedString(XMLDTDScanner.notation_string)) {
                if (!this.checkForPEReference(true)) {
                    this.abortMarkup(136, 115, rawname, rawname2);
                    return;
                }
                if (!this.fEntityReader.lookingAtChar('(', true)) {
                    this.abortMarkup(130, 114, rawname, rawname2);
                    return;
                }
                this.increaseParenDepth();
                n2 = 6;
                n = this.scanEnumeration(rawname, rawname2, true);
                if (n == -1) {
                    this.skipPastEndOfCurrentMarkup();
                    return;
                }
            }
            else {
                if (!this.fEntityReader.lookingAtChar('(', true)) {
                    this.abortMarkup(59, 71, rawname, rawname2);
                    return;
                }
                this.increaseParenDepth();
                n2 = 2;
                n = this.scanEnumeration(rawname, rawname2, false);
                if (n == -1) {
                    this.skipPastEndOfCurrentMarkup();
                    return;
                }
            }
            if (!this.checkForPEReference(true)) {
                this.abortMarkup(140, 113, rawname, rawname2);
                return;
            }
            int n3 = -1;
            int n4;
            if (this.fEntityReader.skippedString(XMLDTDScanner.required_string)) {
                n4 = 2;
            }
            else if (this.fEntityReader.skippedString(XMLDTDScanner.implied_string)) {
                n4 = 0;
            }
            else {
                if (this.fEntityReader.skippedString(XMLDTDScanner.fixed_string)) {
                    if (!this.checkForPEReference(true)) {
                        this.abortMarkup(135, 116, rawname, rawname2);
                        return;
                    }
                    n4 = 1;
                }
                else {
                    n4 = 3;
                }
                n3 = this.scanDefaultAttValue(this.fElementQName, this.fAttributeQName, n2, n);
                if (n3 != -1 && n2 != 0) {
                    n3 = this.normalizeDefaultAttValue(this.fAttributeQName, n3, n2, n, b);
                }
                if (n3 == -1) {
                    this.skipPastEndOfCurrentMarkup();
                    return;
                }
            }
            if (rawname2 == this.fXMLSpace) {
                int n5 = 0;
                if (n2 == 2) {
                    final int n6 = n;
                    if (n6 != -1) {
                        n5 = (((this.fStringPool.stringListLength(n6) == 1 && (this.fStringPool.stringInList(n6, this.fDefault) || this.fStringPool.stringInList(n6, this.fPreserve))) || (this.fStringPool.stringListLength(n6) == 2 && this.fStringPool.stringInList(n6, this.fDefault) && this.fStringPool.stringInList(n6, this.fPreserve))) ? 1 : 0);
                    }
                }
                if (n5 == 0) {
                    this.reportFatalXMLError(144, 117, rawname);
                }
            }
            final boolean checkForPEReference2 = this.checkForPEReference(true);
            if (this.fAttributeQName.prefix == this.fXMLSymbol) {
                this.fAttributeQName.uri = this.fXMLNamespace;
            }
            if (this.fEntityReader.lookingAtChar('>', true)) {
                this.addAttDef(this.fElementQName, this.fAttributeQName, n2, b, n, n4, n3, this.getReadingExternalEntity());
                this.decreaseMarkupDepth();
                return;
            }
            if (!checkForPEReference2) {
                if (this.fEntityReader.lookingAtSpace(true)) {
                    this.fEntityReader.skipPastSpaces();
                }
                else {
                    this.reportFatalXMLError(138, 113);
                }
            }
            else if (this.fEntityReader.lookingAtSpace(true)) {
                this.fEntityReader.skipPastSpaces();
            }
            if (this.fEntityReader.lookingAtChar('>', true)) {
                this.addAttDef(this.fElementQName, this.fAttributeQName, n2, b, n, n4, n3, this.getReadingExternalEntity());
                this.decreaseMarkupDepth();
                return;
            }
            this.addAttDef(this.fElementQName, this.fAttributeQName, n2, b, n, n4, n3, this.getReadingExternalEntity());
        }
    }
    
    private int addAttDef(final QName qName, final QName qName2, final int n, final boolean b, final int n2, final int n3, final int n4, final boolean b2) throws Exception {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.attlistDecl(qName, qName2, n, b, (n2 != -1) ? this.fStringPool.stringListAsString(n2) : null, n3, n4);
        }
        final int elementDeclIndex = this.fDTDGrammar.getElementDeclIndex(qName, -1);
        if (elementDeclIndex != -1) {
            int i = this.fDTDGrammar.getFirstAttributeDeclIndex(elementDeclIndex);
            int rawname = -1;
            int rawname2 = -1;
            while (i != -1) {
                this.fDTDGrammar.getAttributeDecl(i, this.fTempAttributeDecl);
                if (this.fStringPool.equalNames(this.fTempAttributeDecl.name.rawname, qName2.rawname)) {
                    return -1;
                }
                if (this.fValidationEnabled) {
                    if (n == 3 && this.fTempAttributeDecl.type == 3) {
                        rawname = this.fTempAttributeDecl.name.rawname;
                    }
                    if (n == 6 && this.fTempAttributeDecl.type == 6) {
                        rawname2 = this.fTempAttributeDecl.name.rawname;
                    }
                }
                i = this.fDTDGrammar.getNextAttributeDeclIndex(i);
            }
            if (this.fValidationEnabled) {
                if (rawname != -1) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 86, 81, new Object[] { this.fStringPool.toString(qName.rawname), this.fStringPool.toString(rawname), this.fStringPool.toString(qName2.rawname) }, 1);
                    return -1;
                }
                if (rawname2 != -1) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 121, 143, new Object[] { this.fStringPool.toString(qName.rawname), this.fStringPool.toString(rawname2), this.fStringPool.toString(qName2.rawname) }, 1);
                    return -1;
                }
            }
        }
        return this.fDTDGrammar.addAttDef(qName, qName2, n, b, n2, n3, n4, b2);
    }
    
    private int scanEnumeration(final int n, final int n2, final boolean b) throws Exception {
        final int startEnumeration = this.fDTDGrammar.startEnumeration();
        do {
            this.checkForPEReference(false);
            final int n3 = b ? this.checkForNameWithPEReference(this.fEntityReader, ')') : this.checkForNmtokenWithPEReference(this.fEntityReader, ')');
            if (n3 == -1) {
                if (b) {
                    this.reportFatalXMLError(115, 90, n, n2);
                }
                else {
                    this.reportFatalXMLError(116, 92, n, n2);
                }
                this.fDTDGrammar.endEnumeration(startEnumeration);
                return -1;
            }
            this.fDTDGrammar.addNameToEnumeration(startEnumeration, n, n2, n3, b);
            if (b && !((DefaultEntityHandler)this.fEntityHandler).isNotationDeclared(n3)) {
                ((DefaultEntityHandler)this.fEntityHandler).addRequiredNotation(n3, this.fErrorReporter.getLocator(), 117, 89, new Object[] { this.fStringPool.toString(n), this.fStringPool.toString(n2), this.fStringPool.toString(n3) });
            }
            this.checkForPEReference(false);
        } while (this.fEntityReader.lookingAtChar('|', true));
        this.fDTDGrammar.endEnumeration(startEnumeration);
        if (!this.fEntityReader.lookingAtChar(')', true)) {
            if (b) {
                this.reportFatalXMLError(118, 91, n, n2);
            }
            else {
                this.reportFatalXMLError(119, 93, n, n2);
            }
            return -1;
        }
        this.decreaseParenDepth();
        return startEnumeration;
    }
    
    public int scanDefaultAttValue(final QName qName, final QName qName2) throws Exception {
        final boolean lookingAtChar;
        if (!(lookingAtChar = this.fEntityReader.lookingAtChar('\'', true)) && !this.fEntityReader.lookingAtChar('\"', true)) {
            this.reportFatalXMLError(13, 12, qName.rawname, qName2.rawname);
            return -1;
        }
        final int setScannerState = this.setScannerState(56);
        final int n = lookingAtChar ? 39 : 34;
        this.fDefaultAttValueReader = this.fReaderId;
        this.fDefaultAttValueElementType = qName.rawname;
        this.fDefaultAttValueAttrName = qName2.rawname;
        int n2 = 1;
        final int length = this.fLiteralData.length();
        while (true) {
            this.fDefaultAttValueOffset = this.fEntityReader.currentOffset();
            if (n2 != 0) {
                this.fDefaultAttValueMark = this.fDefaultAttValueOffset;
                n2 = 0;
            }
            if (this.fEntityReader.lookingAtChar((char)n, true)) {
                if (this.fReaderId != this.fDefaultAttValueReader) {
                    continue;
                }
                this.restoreScannerState(setScannerState);
                int n3 = this.fLiteralData.length() - length;
                if (n3 == 0) {
                    return this.fEntityReader.addString(this.fDefaultAttValueMark, this.fDefaultAttValueOffset - this.fDefaultAttValueMark);
                }
                if (this.fDefaultAttValueOffset - this.fDefaultAttValueMark > 0) {
                    this.fEntityReader.append(this.fLiteralData, this.fDefaultAttValueMark, this.fDefaultAttValueOffset - this.fDefaultAttValueMark);
                    n3 = this.fLiteralData.length() - length;
                }
                return this.fLiteralData.addString(length, n3);
            }
            else {
                if (this.fEntityReader.lookingAtChar(' ', true)) {
                    continue;
                }
                final boolean lookingAtChar2;
                if ((lookingAtChar2 = this.fEntityReader.lookingAtChar('\r', true)) || this.fEntityReader.lookingAtSpace(true)) {
                    if (this.fDefaultAttValueOffset - this.fDefaultAttValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fDefaultAttValueMark, this.fDefaultAttValueOffset - this.fDefaultAttValueMark);
                    }
                    n2 = 1;
                    this.fLiteralData.append(' ');
                    if (!lookingAtChar2) {
                        continue;
                    }
                    this.fEntityReader.lookingAtChar('\n', true);
                }
                else if (this.fEntityReader.lookingAtChar('&', true)) {
                    if (this.fDefaultAttValueOffset - this.fDefaultAttValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fDefaultAttValueMark, this.fDefaultAttValueOffset - this.fDefaultAttValueMark);
                    }
                    n2 = 1;
                    if (this.fEntityReader.lookingAtChar('#', true)) {
                        final int scanCharRef = this.scanCharRef();
                        if (scanCharRef == -1) {
                            continue;
                        }
                        if (scanCharRef < 65536) {
                            this.fLiteralData.append((char)scanCharRef);
                        }
                        else {
                            this.fLiteralData.append((char)((scanCharRef - 65536 >> 10) + 55296));
                            this.fLiteralData.append((char)((scanCharRef - 65536 & 0x3FF) + 56320));
                        }
                    }
                    else {
                        final int currentOffset = this.fEntityReader.currentOffset();
                        this.fEntityReader.skipPastName(';');
                        final int n4 = this.fEntityReader.currentOffset() - currentOffset;
                        if (n4 == 0) {
                            this.reportFatalXMLError(14, 13);
                        }
                        else if (!this.fEntityReader.lookingAtChar(';', true)) {
                            this.reportFatalXMLError(15, 14, this.fEntityReader.addString(currentOffset, n4));
                        }
                        else {
                            this.fEntityHandler.startReadingFromEntity(this.fEntityReader.addSymbol(currentOffset, n4), this.markupDepth(), 1);
                        }
                    }
                }
                else if (this.fEntityReader.lookingAtChar('<', true)) {
                    if (this.fDefaultAttValueOffset - this.fDefaultAttValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fDefaultAttValueMark, this.fDefaultAttValueOffset - this.fDefaultAttValueMark);
                    }
                    n2 = 1;
                    this.reportFatalXMLError(2, 11, qName.rawname, qName2.rawname);
                }
                else {
                    if (this.fEntityReader.lookingAtValidChar(true)) {
                        continue;
                    }
                    if (this.fDefaultAttValueOffset - this.fDefaultAttValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fDefaultAttValueMark, this.fDefaultAttValueOffset - this.fDefaultAttValueMark);
                    }
                    n2 = 1;
                    final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                    if (this.fScannerState == 0) {
                        return -1;
                    }
                    if (scanInvalidChar < 0) {
                        continue;
                    }
                    this.reportFatalXMLError(12, 10, this.fStringPool.toString(qName.rawname), this.fStringPool.toString(qName2.rawname), Integer.toHexString(scanInvalidChar));
                }
            }
        }
    }
    
    private void scanNotationDecl() throws Exception {
        if (!this.checkForPEReference(true)) {
            this.abortMarkup(142, 102);
            return;
        }
        final int checkForNameWithPEReference = this.checkForNameWithPEReference(this.fEntityReader, ' ');
        if (checkForNameWithPEReference == -1) {
            this.abortMarkup(120, 101);
            return;
        }
        if (!this.checkForPEReference(true)) {
            this.abortMarkup(137, 102, checkForNameWithPEReference);
            return;
        }
        if (!this.scanExternalID(true)) {
            this.skipPastEndOfCurrentMarkup();
            return;
        }
        this.checkForPEReference(false);
        if (!this.fEntityReader.lookingAtChar('>', true)) {
            this.abortMarkup(122, 103, checkForNameWithPEReference);
            return;
        }
        this.decreaseMarkupDepth();
        ((DefaultEntityHandler)this.fEntityHandler).addNotationDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral, this.getReadingExternalEntity());
        this.fDTDGrammar.addNotationDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.notationDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral);
        }
    }
    
    private void scanEntityDecl() throws Exception {
        boolean lookingAtChar = false;
        boolean b = false;
        if (this.fEntityReader.lookingAtSpace(true)) {
            this.fEntityReader.skipPastSpaces();
            if (!this.fEntityReader.lookingAtChar('%', true)) {
                lookingAtChar = false;
            }
            else if (this.fEntityReader.lookingAtSpace(true)) {
                this.checkForPEReference(false);
                lookingAtChar = true;
            }
            else if (!this.getReadingExternalEntity()) {
                this.reportFatalXMLError(107, 98);
                lookingAtChar = true;
            }
            else if (this.fEntityReader.lookingAtChar('%', false)) {
                this.checkForPEReference(false);
                lookingAtChar = true;
            }
            else {
                b = true;
            }
        }
        else if (!this.getReadingExternalEntity() || !this.fEntityReader.lookingAtChar('%', true)) {
            this.reportFatalXMLError(108, 94);
            lookingAtChar = false;
        }
        else if (this.fEntityReader.lookingAtSpace(false)) {
            this.reportFatalXMLError(109, 98);
            lookingAtChar = false;
        }
        else {
            b = true;
        }
        if (b) {
            while (true) {
                final int currentOffset = this.fEntityReader.currentOffset();
                this.fEntityReader.skipPastName(';');
                final int n = this.fEntityReader.currentOffset() - currentOffset;
                if (n == 0) {
                    this.reportFatalXMLError(48, 50);
                }
                else if (!this.fEntityReader.lookingAtChar(';', true)) {
                    this.reportFatalXMLError(49, 51, this.fEntityReader.addString(currentOffset, n));
                }
                else {
                    this.fEntityHandler.startReadingFromEntity(this.fEntityReader.addSymbol(currentOffset, n), (this.fScannerState == 57) ? this.parenDepth() : this.markupDepth(), 5);
                }
                this.fEntityReader.skipPastSpaces();
                if (!this.fEntityReader.lookingAtChar('%', true)) {
                    break;
                }
                if (lookingAtChar) {
                    continue;
                }
                if (this.fEntityReader.lookingAtSpace(true)) {
                    this.checkForPEReference(false);
                    lookingAtChar = true;
                    break;
                }
                lookingAtChar = this.fEntityReader.lookingAtChar('%', true);
            }
        }
        final int checkForNameWithPEReference = this.checkForNameWithPEReference(this.fEntityReader, ' ');
        if (checkForNameWithPEReference == -1) {
            this.abortMarkup(110, 95);
            return;
        }
        if (!this.fDTDGrammar.startEntityDecl(lookingAtChar, checkForNameWithPEReference)) {
            this.skipPastEndOfCurrentMarkup();
            return;
        }
        if (!this.checkForPEReference(true)) {
            this.abortMarkup(111, 96, checkForNameWithPEReference);
            this.fDTDGrammar.endEntityDecl();
            return;
        }
        if (lookingAtChar) {
            final boolean lookingAtChar2;
            if ((lookingAtChar2 = this.fEntityReader.lookingAtChar('\'', true)) || this.fEntityReader.lookingAtChar('\"', true)) {
                final int scanEntityValue = this.scanEntityValue(lookingAtChar2);
                if (scanEntityValue == -1) {
                    this.skipPastEndOfCurrentMarkup();
                    this.fDTDGrammar.endEntityDecl();
                    return;
                }
                this.checkForPEReference(false);
                if (!this.fEntityReader.lookingAtChar('>', true)) {
                    this.abortMarkup(112, 99, checkForNameWithPEReference);
                    this.fDTDGrammar.endEntityDecl();
                    return;
                }
                this.decreaseMarkupDepth();
                this.fDTDGrammar.endEntityDecl();
                this.fDTDGrammar.addInternalPEDecl(checkForNameWithPEReference, scanEntityValue);
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.internalPEDecl(checkForNameWithPEReference, scanEntityValue);
                }
                ((DefaultEntityHandler)this.fEntityHandler).addInternalPEDecl(checkForNameWithPEReference, scanEntityValue, this.getReadingExternalEntity());
            }
            else {
                if (!this.scanExternalID(false)) {
                    this.skipPastEndOfCurrentMarkup();
                    this.fDTDGrammar.endEntityDecl();
                    return;
                }
                this.checkForPEReference(false);
                if (!this.fEntityReader.lookingAtChar('>', true)) {
                    this.abortMarkup(112, 99, checkForNameWithPEReference);
                    this.fDTDGrammar.endEntityDecl();
                    return;
                }
                this.decreaseMarkupDepth();
                this.fDTDGrammar.endEntityDecl();
                this.fDTDGrammar.addExternalPEDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral);
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.externalPEDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral);
                }
                ((DefaultEntityHandler)this.fEntityHandler).addExternalPEDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral, this.getReadingExternalEntity());
            }
        }
        else {
            final boolean lookingAtChar3;
            if ((lookingAtChar3 = this.fEntityReader.lookingAtChar('\'', true)) || this.fEntityReader.lookingAtChar('\"', true)) {
                final int scanEntityValue2 = this.scanEntityValue(lookingAtChar3);
                if (scanEntityValue2 == -1) {
                    this.skipPastEndOfCurrentMarkup();
                    this.fDTDGrammar.endEntityDecl();
                    return;
                }
                this.checkForPEReference(false);
                if (!this.fEntityReader.lookingAtChar('>', true)) {
                    this.abortMarkup(112, 97, checkForNameWithPEReference);
                    this.fDTDGrammar.endEntityDecl();
                    return;
                }
                this.decreaseMarkupDepth();
                this.fDTDGrammar.endEntityDecl();
                this.fDTDGrammar.addInternalEntityDecl(checkForNameWithPEReference, scanEntityValue2);
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.internalEntityDecl(checkForNameWithPEReference, scanEntityValue2);
                }
                ((DefaultEntityHandler)this.fEntityHandler).addInternalEntityDecl(checkForNameWithPEReference, scanEntityValue2, this.getReadingExternalEntity());
            }
            else {
                if (!this.scanExternalID(false)) {
                    this.skipPastEndOfCurrentMarkup();
                    this.fDTDGrammar.endEntityDecl();
                    return;
                }
                boolean skippedString = false;
                if (this.fEntityReader.lookingAtSpace(true)) {
                    this.fEntityReader.skipPastSpaces();
                    skippedString = this.fEntityReader.skippedString(XMLDTDScanner.ndata_string);
                }
                if (!skippedString) {
                    this.checkForPEReference(false);
                    if (!this.fEntityReader.lookingAtChar('>', true)) {
                        this.abortMarkup(112, 99, checkForNameWithPEReference);
                        this.fDTDGrammar.endEntityDecl();
                        return;
                    }
                    this.decreaseMarkupDepth();
                    this.fDTDGrammar.endEntityDecl();
                    this.fDTDGrammar.addExternalEntityDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral);
                    if (this.fDTDHandler != null) {
                        this.fDTDHandler.externalEntityDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral);
                    }
                    ((DefaultEntityHandler)this.fEntityHandler).addExternalEntityDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral, this.getReadingExternalEntity());
                }
                else {
                    if (!this.fEntityReader.lookingAtSpace(true)) {
                        this.abortMarkup(157, 132, checkForNameWithPEReference);
                        this.fDTDGrammar.endEntityDecl();
                        return;
                    }
                    this.fEntityReader.skipPastSpaces();
                    final int currentOffset2 = this.fEntityReader.currentOffset();
                    this.fEntityReader.skipPastName('>');
                    final int n2 = this.fEntityReader.currentOffset() - currentOffset2;
                    if (n2 == 0) {
                        this.abortMarkup(113, 100, checkForNameWithPEReference);
                        this.fDTDGrammar.endEntityDecl();
                        return;
                    }
                    final int addSymbol = this.fEntityReader.addSymbol(currentOffset2, n2);
                    this.checkForPEReference(false);
                    if (!this.fEntityReader.lookingAtChar('>', true)) {
                        this.abortMarkup(112, 99, checkForNameWithPEReference);
                        this.fDTDGrammar.endEntityDecl();
                        return;
                    }
                    this.decreaseMarkupDepth();
                    this.fDTDGrammar.endEntityDecl();
                    this.fDTDGrammar.addUnparsedEntityDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral, addSymbol);
                    if (this.fDTDHandler != null) {
                        this.fDTDHandler.unparsedEntityDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral, addSymbol);
                    }
                    ((DefaultEntityHandler)this.fEntityHandler).addUnparsedEntityDecl(checkForNameWithPEReference, this.fPubidLiteral, this.fSystemLiteral, addSymbol, this.getReadingExternalEntity());
                }
            }
        }
    }
    
    private int scanEntityValue(final boolean b) throws Exception {
        final char c = b ? '\'' : '\"';
        this.fEntityValueMark = this.fEntityReader.currentOffset();
        int n = this.fEntityReader.scanEntityValue(c, true);
        if (n < 0) {
            n = this.scanComplexEntityValue(c, n);
        }
        return n;
    }
    
    private int scanComplexEntityValue(final char c, int scanEntityValue) throws Exception {
        final int setScannerState = this.setScannerState(58);
        this.fEntityValueReader = this.fReaderId;
        final int length = this.fLiteralData.length();
        while (true) {
            switch (scanEntityValue) {
                case -1: {
                    final int currentOffset = this.fEntityReader.currentOffset();
                    this.fEntityReader.lookingAtChar(c, true);
                    this.restoreScannerState(setScannerState);
                    int n = this.fLiteralData.length() - length;
                    if (n == 0) {
                        return this.fEntityReader.addString(this.fEntityValueMark, currentOffset - this.fEntityValueMark);
                    }
                    if (currentOffset - this.fEntityValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fEntityValueMark, currentOffset - this.fEntityValueMark);
                        n = this.fLiteralData.length() - length;
                    }
                    return this.fLiteralData.addString(length, n);
                }
                case -2: {
                    final int currentOffset2 = this.fEntityReader.currentOffset();
                    if (currentOffset2 - this.fEntityValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fEntityValueMark, currentOffset2 - this.fEntityValueMark);
                    }
                    this.fEntityReader.lookingAtChar('&', true);
                    if (this.fEntityReader.lookingAtChar('#', true)) {
                        final int scanCharRef = this.scanCharRef();
                        if (scanCharRef != -1) {
                            if (scanCharRef < 65536) {
                                this.fLiteralData.append((char)scanCharRef);
                            }
                            else {
                                this.fLiteralData.append((char)((scanCharRef - 65536 >> 10) + 55296));
                                this.fLiteralData.append((char)((scanCharRef - 65536 & 0x3FF) + 56320));
                            }
                        }
                        this.fEntityValueMark = this.fEntityReader.currentOffset();
                        break;
                    }
                    final int currentOffset3 = this.fEntityReader.currentOffset();
                    this.fEntityReader.skipPastName(';');
                    final int n2 = this.fEntityReader.currentOffset() - currentOffset3;
                    if (n2 == 0) {
                        this.reportFatalXMLError(14, 13);
                        this.fEntityValueMark = this.fEntityReader.currentOffset();
                        break;
                    }
                    if (!this.fEntityReader.lookingAtChar(';', true)) {
                        this.reportFatalXMLError(15, 14, this.fEntityReader.addString(currentOffset3, n2));
                        this.fEntityValueMark = this.fEntityReader.currentOffset();
                        break;
                    }
                    this.fEntityValueMark = currentOffset2;
                    break;
                }
                case -3: {
                    final int currentOffset4 = this.fEntityReader.currentOffset();
                    if (currentOffset4 - this.fEntityValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fEntityValueMark, currentOffset4 - this.fEntityValueMark);
                    }
                    this.fEntityReader.lookingAtChar('%', true);
                    final int currentOffset5 = this.fEntityReader.currentOffset();
                    this.fEntityReader.skipPastName(';');
                    final int n3 = this.fEntityReader.currentOffset() - currentOffset5;
                    if (n3 == 0) {
                        this.reportFatalXMLError(48, 50);
                    }
                    else if (!this.fEntityReader.lookingAtChar(';', true)) {
                        this.reportFatalXMLError(49, 51, this.fEntityReader.addString(currentOffset5, n3));
                    }
                    else if (!this.getReadingExternalEntity()) {
                        this.reportFatalXMLError(51, 53, this.fEntityReader.addString(currentOffset5, n3));
                    }
                    else {
                        this.fEntityHandler.startReadingFromEntity(this.fEntityReader.addSymbol(currentOffset5, n3), this.markupDepth(), 4);
                    }
                    this.fEntityValueMark = this.fEntityReader.currentOffset();
                    break;
                }
                case -4: {
                    final int currentOffset6 = this.fEntityReader.currentOffset();
                    if (currentOffset6 - this.fEntityValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fEntityValueMark, currentOffset6 - this.fEntityValueMark);
                    }
                    final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                    if (this.fScannerState == 0) {
                        return -1;
                    }
                    if (scanInvalidChar >= 0) {
                        this.reportFatalXMLError(46, 48, Integer.toHexString(scanInvalidChar));
                    }
                    this.fEntityValueMark = this.fEntityReader.currentOffset();
                    break;
                }
            }
            scanEntityValue = this.fEntityReader.scanEntityValue((this.fReaderId == this.fEntityValueReader) ? c : -1, false);
        }
    }
    
    private boolean checkForPEReference(final boolean b) throws Exception {
        boolean lookingAtSpace = true;
        if (b) {
            lookingAtSpace = this.fEntityReader.lookingAtSpace(true);
        }
        this.fEntityReader.skipPastSpaces();
        if (!this.getReadingExternalEntity()) {
            return lookingAtSpace;
        }
        if (!this.fEntityReader.lookingAtChar('%', true)) {
            return lookingAtSpace;
        }
        do {
            final int currentOffset = this.fEntityReader.currentOffset();
            this.fEntityReader.skipPastName(';');
            final int n = this.fEntityReader.currentOffset() - currentOffset;
            if (n == 0) {
                this.reportFatalXMLError(48, 50);
            }
            else if (!this.fEntityReader.lookingAtChar(';', true)) {
                this.reportFatalXMLError(49, 51, this.fEntityReader.addString(currentOffset, n));
            }
            else {
                this.fEntityHandler.startReadingFromEntity(this.fEntityReader.addSymbol(currentOffset, n), (this.fScannerState == 57) ? this.parenDepth() : this.markupDepth(), 5);
            }
            this.fEntityReader.skipPastSpaces();
        } while (this.fEntityReader.lookingAtChar('%', true));
        return true;
    }
    
    private void initializeContentModelStack(final int n) {
        if (this.fOpStack == null) {
            this.fOpStack = new int[8];
            this.fNodeIndexStack = new int[8];
            this.fPrevNodeIndexStack = new int[8];
        }
        else if (n == this.fOpStack.length) {
            final int[] fOpStack = new int[n * 2];
            System.arraycopy(this.fOpStack, 0, fOpStack, 0, n);
            this.fOpStack = fOpStack;
            final int[] fNodeIndexStack = new int[n * 2];
            System.arraycopy(this.fNodeIndexStack, 0, fNodeIndexStack, 0, n);
            this.fNodeIndexStack = fNodeIndexStack;
            final int[] fPrevNodeIndexStack = new int[n * 2];
            System.arraycopy(this.fPrevNodeIndexStack, 0, fPrevNodeIndexStack, 0, n);
            this.fPrevNodeIndexStack = fPrevNodeIndexStack;
        }
        this.fOpStack[n] = -1;
        this.fNodeIndexStack[n] = -1;
        this.fPrevNodeIndexStack[n] = -1;
    }
    
    private boolean validVersionNum(final String s) {
        return XMLCharacterProperties.validVersionNum(s);
    }
    
    private boolean validEncName(final String s) {
        return XMLCharacterProperties.validEncName(s);
    }
    
    private int validPublicId(final String s) {
        return XMLCharacterProperties.validPublicId(s);
    }
    
    private void scanElementType(final XMLEntityHandler.EntityReader entityReader, final char c, final QName qName) throws Exception {
        if (!this.fNamespacesEnabled) {
            qName.clear();
            qName.localpart = entityReader.scanName(c);
            qName.rawname = qName.localpart;
            return;
        }
        entityReader.scanQName(c, qName);
        if (entityReader.lookingAtChar(':', false)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
            entityReader.skipPastNmtoken(' ');
        }
    }
    
    public void checkForElementTypeWithPEReference(final XMLEntityHandler.EntityReader entityReader, final char c, final QName qName) throws Exception {
        if (!this.fNamespacesEnabled) {
            qName.clear();
            qName.localpart = entityReader.scanName(c);
            qName.rawname = qName.localpart;
            return;
        }
        entityReader.scanQName(c, qName);
        if (entityReader.lookingAtChar(':', false)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
            entityReader.skipPastNmtoken(' ');
        }
    }
    
    public void checkForAttributeNameWithPEReference(final XMLEntityHandler.EntityReader entityReader, final char c, final QName qName) throws Exception {
        if (!this.fNamespacesEnabled) {
            qName.clear();
            qName.localpart = entityReader.scanName(c);
            qName.rawname = qName.localpart;
            return;
        }
        entityReader.scanQName(c, qName);
        if (entityReader.lookingAtChar(':', false)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
            entityReader.skipPastNmtoken(' ');
        }
    }
    
    public int checkForNameWithPEReference(final XMLEntityHandler.EntityReader entityReader, final char c) throws Exception {
        return entityReader.scanName(c);
    }
    
    public int checkForNmtokenWithPEReference(final XMLEntityHandler.EntityReader entityReader, final char c) throws Exception {
        final int currentOffset = entityReader.currentOffset();
        entityReader.skipPastNmtoken(c);
        final int n = entityReader.currentOffset() - currentOffset;
        if (n == 0) {
            return -1;
        }
        return entityReader.addSymbol(currentOffset, n);
    }
    
    public int scanDefaultAttValue(final QName qName, final QName qName2, final int n, final int n2) throws Exception {
        if (this.fValidationEnabled && n == 3) {
            this.reportRecoverableXMLError(69, 69, this.fStringPool.toString(qName2.rawname));
        }
        final int scanDefaultAttValue = this.scanDefaultAttValue(qName, qName2);
        if (scanDefaultAttValue == -1) {
            return -1;
        }
        return scanDefaultAttValue;
    }
    
    public int normalizeDefaultAttValue(final QName qName, int n, final int n2, final int n3, final boolean b) throws Exception {
        final String string = this.fStringPool.toString(n);
        if (b) {
            final StringTokenizer stringTokenizer = new StringTokenizer(string);
            final StringBuffer sb = new StringBuffer(string.length());
            boolean b2 = true;
            if (stringTokenizer.hasMoreTokens()) {
                while (true) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (n2 == 5) {
                        if (this.fValidationEnabled && !XMLCharacterProperties.validNmtoken(nextToken)) {
                            b2 = false;
                        }
                    }
                    else if (n2 == 4 || n2 == 1) {
                        if (this.fValidationEnabled && !XMLCharacterProperties.validName(nextToken)) {
                            b2 = false;
                        }
                        if (this.fValidationEnabled && n2 == 1 && !((DefaultEntityHandler)this.fEntityHandler).isUnparsedEntity(n)) {
                            this.reportRecoverableXMLError(79, 77, this.fStringPool.toString(qName.rawname), nextToken);
                        }
                    }
                    sb.append(nextToken);
                    if (!stringTokenizer.hasMoreTokens()) {
                        break;
                    }
                    sb.append(' ');
                }
            }
            final String string2 = sb.toString();
            if (this.fValidationEnabled && (!b2 || string2.length() == 0)) {
                this.reportRecoverableXMLError(168, 145, this.fStringPool.toString(qName.rawname), string2);
            }
            if (!string2.equals(string)) {
                n = this.fStringPool.addString(string2);
            }
            return n;
        }
        final String trim = string.trim();
        if (this.fValidationEnabled) {
            if (trim != string) {
                n = this.fStringPool.addSymbol(trim);
            }
            else {
                n = this.fStringPool.addSymbol(n);
            }
            if (n2 == 1 || n2 == 3 || n2 == 4 || n2 == 6) {
                if (n2 == 1 && !((DefaultEntityHandler)this.fEntityHandler).isUnparsedEntity(n)) {
                    this.reportRecoverableXMLError(79, 77, this.fStringPool.toString(qName.rawname), trim);
                }
                if (!XMLCharacterProperties.validName(trim)) {
                    this.reportRecoverableXMLError(168, 145, this.fStringPool.toString(qName.rawname), trim);
                }
            }
            else if ((n2 == 5 || n2 == 2) && !XMLCharacterProperties.validNmtoken(trim)) {
                this.reportRecoverableXMLError(168, 145, this.fStringPool.toString(qName.rawname), trim);
            }
            if ((n2 == 6 || n2 == 2) && !this.fStringPool.stringInList(n3, n)) {
                this.reportRecoverableXMLError(168, 145, this.fStringPool.toString(qName.rawname), trim);
            }
        }
        else if (trim != string) {
            n = this.fStringPool.addSymbol(trim);
        }
        return n;
    }
    
    static {
        version_string = new char[] { 'v', 'e', 'r', 's', 'i', 'o', 'n' };
        element_string = new char[] { 'E', 'L', 'E', 'M', 'E', 'N', 'T' };
        empty_string = new char[] { 'E', 'M', 'P', 'T', 'Y' };
        any_string = new char[] { 'A', 'N', 'Y' };
        pcdata_string = new char[] { '#', 'P', 'C', 'D', 'A', 'T', 'A' };
        attlist_string = new char[] { 'A', 'T', 'T', 'L', 'I', 'S', 'T' };
        cdata_string = new char[] { 'C', 'D', 'A', 'T', 'A' };
        id_string = new char[] { 'I', 'D' };
        ref_string = new char[] { 'R', 'E', 'F' };
        entit_string = new char[] { 'E', 'N', 'T', 'I', 'T' };
        ies_string = new char[] { 'I', 'E', 'S' };
        nmtoken_string = new char[] { 'N', 'M', 'T', 'O', 'K', 'E', 'N' };
        notation_string = new char[] { 'N', 'O', 'T', 'A', 'T', 'I', 'O', 'N' };
        required_string = new char[] { '#', 'R', 'E', 'Q', 'U', 'I', 'R', 'E', 'D' };
        implied_string = new char[] { '#', 'I', 'M', 'P', 'L', 'I', 'E', 'D' };
        fixed_string = new char[] { '#', 'F', 'I', 'X', 'E', 'D' };
        include_string = new char[] { 'I', 'N', 'C', 'L', 'U', 'D', 'E' };
        ignore_string = new char[] { 'I', 'G', 'N', 'O', 'R', 'E' };
        entity_string = new char[] { 'E', 'N', 'T', 'I', 'T', 'Y' };
        system_string = new char[] { 'S', 'Y', 'S', 'T', 'E', 'M' };
        public_string = new char[] { 'P', 'U', 'B', 'L', 'I', 'C' };
        ndata_string = new char[] { 'N', 'D', 'A', 'T', 'A' };
        encoding_string = new char[] { 'e', 'n', 'c', 'o', 'd', 'i', 'n', 'g' };
    }
    
    public interface EventHandler
    {
        void callStartDTD() throws Exception;
        
        void callEndDTD() throws Exception;
        
        void callTextDecl(final int p0, final int p1) throws Exception;
        
        void doctypeDecl(final QName p0, final int p1, final int p2) throws Exception;
        
        void startReadingFromExternalSubset(final int p0, final int p1) throws Exception;
        
        void stopReadingFromExternalSubset() throws Exception;
        
        int addElementDecl(final QName p0) throws Exception;
        
        int addElementDecl(final QName p0, final int p1, final int p2, final boolean p3) throws Exception;
        
        int addAttDef(final QName p0, final QName p1, final int p2, final boolean p3, final int p4, final int p5, final int p6, final boolean p7) throws Exception;
        
        int addUniqueLeafNode(final int p0) throws Exception;
        
        int addContentSpecNode(final int p0, final int p1) throws Exception;
        
        int addContentSpecNode(final int p0, final int p1, final int p2) throws Exception;
        
        String getContentSpecNodeAsString(final int p0) throws Exception;
        
        boolean startEntityDecl(final boolean p0, final int p1) throws Exception;
        
        void endEntityDecl() throws Exception;
        
        int addInternalPEDecl(final int p0, final int p1) throws Exception;
        
        int addExternalPEDecl(final int p0, final int p1, final int p2) throws Exception;
        
        int addInternalEntityDecl(final int p0, final int p1) throws Exception;
        
        int addExternalEntityDecl(final int p0, final int p1, final int p2) throws Exception;
        
        int addUnparsedEntityDecl(final int p0, final int p1, final int p2, final int p3) throws Exception;
        
        int startEnumeration() throws Exception;
        
        void addNameToEnumeration(final int p0, final int p1, final int p2, final int p3, final boolean p4) throws Exception;
        
        void endEnumeration(final int p0) throws Exception;
        
        int addNotationDecl(final int p0, final int p1, final int p2) throws Exception;
        
        void callComment(final int p0) throws Exception;
        
        void callProcessingInstruction(final int p0, final int p1) throws Exception;
        
        void internalSubset(final int p0) throws Exception;
    }
}
