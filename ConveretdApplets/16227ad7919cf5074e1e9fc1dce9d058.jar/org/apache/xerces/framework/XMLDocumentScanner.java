// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

import org.apache.xerces.readers.XMLEntityHandler;
import org.apache.xerces.utils.StringPool;

public final class XMLDocumentScanner
{
    static final char[] cdata_string;
    static final char[] xml_string;
    private static final char[] version_string;
    static final char[] doctype_string;
    private static final char[] standalone_string;
    private static final char[] encoding_string;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILURE = -1;
    public static final int RESULT_DUPLICATE_ATTR = -2;
    static final int SCANNER_STATE_XML_DECL = 0;
    static final int SCANNER_STATE_START_OF_MARKUP = 1;
    static final int SCANNER_STATE_COMMENT = 2;
    static final int SCANNER_STATE_PI = 3;
    static final int SCANNER_STATE_DOCTYPE = 4;
    static final int SCANNER_STATE_PROLOG = 5;
    static final int SCANNER_STATE_ROOT_ELEMENT = 6;
    static final int SCANNER_STATE_CONTENT = 7;
    static final int SCANNER_STATE_REFERENCE = 8;
    static final int SCANNER_STATE_ATTRIBUTE_LIST = 9;
    static final int SCANNER_STATE_ATTRIBUTE_NAME = 10;
    static final int SCANNER_STATE_ATTRIBUTE_VALUE = 11;
    static final int SCANNER_STATE_TRAILING_MISC = 12;
    static final int SCANNER_STATE_END_OF_INPUT = 13;
    static final int SCANNER_STATE_TERMINATED = 14;
    ScannerDispatcher fDispatcher;
    EventHandler fEventHandler;
    StringPool fStringPool;
    XMLErrorReporter fErrorReporter;
    XMLEntityHandler fEntityHandler;
    XMLEntityHandler.EntityReader fEntityReader;
    XMLEntityHandler.CharBuffer fLiteralData;
    boolean fSeenRootElement;
    boolean fSeenDoctypeDecl;
    boolean fStandalone;
    boolean fParseTextDecl;
    int fScannerState;
    int fReaderId;
    int fAttValueReader;
    int fAttValueElementType;
    int fAttValueAttrName;
    int fAttValueOffset;
    int fAttValueMark;
    int fScannerMarkupDepth;
    
    public XMLDocumentScanner(final EventHandler fEventHandler, final StringPool fStringPool, final XMLErrorReporter fErrorReporter, final XMLEntityHandler fEntityHandler, final XMLEntityHandler.CharBuffer fLiteralData) {
        this.fSeenRootElement = false;
        this.fSeenDoctypeDecl = false;
        this.fStandalone = false;
        this.fParseTextDecl = false;
        this.fScannerState = 0;
        this.fReaderId = -1;
        this.fAttValueReader = -1;
        this.fAttValueElementType = -1;
        this.fAttValueAttrName = -1;
        this.fAttValueOffset = -1;
        this.fAttValueMark = -1;
        this.fEventHandler = fEventHandler;
        this.fStringPool = fStringPool;
        this.fErrorReporter = fErrorReporter;
        this.fEntityHandler = fEntityHandler;
        this.fLiteralData = fLiteralData;
        this.fDispatcher = (ScannerDispatcher)new XMLDeclDispatcher();
    }
    
    public void reset(final StringPool fStringPool, final XMLEntityHandler.CharBuffer fLiteralData) throws Exception {
        this.fStringPool = fStringPool;
        this.fLiteralData = fLiteralData;
        this.fParseTextDecl = false;
        this.fSeenRootElement = false;
        this.fSeenDoctypeDecl = false;
        this.fStandalone = false;
        this.fDispatcher = (ScannerDispatcher)new XMLDeclDispatcher();
        this.fScannerState = 0;
        this.fScannerMarkupDepth = 0;
    }
    
    public boolean parseSome(final boolean b) throws Exception {
        while (this.fDispatcher.dispatch(b)) {
            if (!b) {
                return true;
            }
        }
        return false;
    }
    
    public void readerChange(final XMLEntityHandler.EntityReader fEntityReader, final int fReaderId) throws Exception {
        this.fEntityReader = fEntityReader;
        this.fReaderId = fReaderId;
        if (this.fScannerState == 11) {
            this.fAttValueOffset = this.fEntityReader.currentOffset();
            this.fAttValueMark = this.fAttValueOffset;
        }
    }
    
    public void endOfInput(final int n, final boolean b) throws Exception {
        this.fDispatcher.endOfInput(n, b);
    }
    
    public boolean atEndOfInput() {
        return this.fScannerState == 13;
    }
    
    public int scanAttValue(final int fAttValueElementType, final int fAttValueAttrName, final boolean b) throws Exception {
        final boolean lookingAtChar;
        if (!(lookingAtChar = this.fEntityReader.lookingAtChar('\'', true)) && !this.fEntityReader.lookingAtChar('\"', true)) {
            this.reportFatalXMLError(13, 12, fAttValueElementType, fAttValueAttrName);
            return -1;
        }
        final int n = lookingAtChar ? 39 : 34;
        this.fAttValueMark = this.fEntityReader.currentOffset();
        final int scanAttValue = this.fEntityReader.scanAttValue((char)n, b);
        if (scanAttValue >= 0) {
            return scanAttValue;
        }
        final int setScannerState = this.setScannerState(11);
        this.fAttValueReader = this.fReaderId;
        this.fAttValueElementType = fAttValueElementType;
        this.fAttValueAttrName = fAttValueAttrName;
        this.fAttValueOffset = this.fEntityReader.currentOffset();
        final int length = this.fLiteralData.length();
        if (this.fAttValueOffset - this.fAttValueMark > 0) {
            this.fEntityReader.append(this.fLiteralData, this.fAttValueMark, this.fAttValueOffset - this.fAttValueMark);
        }
        this.fAttValueMark = this.fAttValueOffset;
        int n2 = 0;
        while (true) {
            if (this.fEntityReader.lookingAtChar((char)n, true)) {
                if (this.fReaderId == this.fAttValueReader) {
                    this.restoreScannerState(setScannerState);
                    int n3 = this.fLiteralData.length() - length;
                    if (n3 == 0) {
                        return this.fEntityReader.addString(this.fAttValueMark, this.fAttValueOffset - this.fAttValueMark);
                    }
                    if (this.fAttValueOffset - this.fAttValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fAttValueMark, this.fAttValueOffset - this.fAttValueMark);
                        n3 = this.fLiteralData.length() - length;
                    }
                    return this.fLiteralData.addString(length, n3);
                }
            }
            else if (!this.fEntityReader.lookingAtChar(' ', true)) {
                if (this.fEntityReader.lookingAtChar('\r', true) || this.fEntityReader.lookingAtSpace(true)) {
                    if (this.fAttValueOffset - this.fAttValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fAttValueMark, this.fAttValueOffset - this.fAttValueMark);
                    }
                    n2 = 1;
                    this.fLiteralData.append(' ');
                }
                else if (this.fEntityReader.lookingAtChar('&', true)) {
                    if (this.fAttValueOffset - this.fAttValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fAttValueMark, this.fAttValueOffset - this.fAttValueMark);
                    }
                    n2 = 1;
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
                            this.fEntityHandler.startReadingFromEntity(this.fEntityReader.addSymbol(currentOffset, n4), this.fScannerMarkupDepth, 0);
                        }
                    }
                }
                else if (this.fEntityReader.lookingAtChar('<', true)) {
                    if (this.fAttValueOffset - this.fAttValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fAttValueMark, this.fAttValueOffset - this.fAttValueMark);
                    }
                    n2 = 1;
                    this.reportFatalXMLError(2, 11, fAttValueElementType, fAttValueAttrName);
                }
                else if (!this.fEntityReader.lookingAtValidChar(true)) {
                    if (this.fAttValueOffset - this.fAttValueMark > 0) {
                        this.fEntityReader.append(this.fLiteralData, this.fAttValueMark, this.fAttValueOffset - this.fAttValueMark);
                    }
                    n2 = 1;
                    final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                    if (this.fScannerState == 13) {
                        return -1;
                    }
                    if (scanInvalidChar >= 0) {
                        this.reportFatalXMLError(12, 10, this.fStringPool.toString(fAttValueElementType), this.fStringPool.toString(fAttValueAttrName), Integer.toHexString(scanInvalidChar));
                    }
                }
            }
            this.fAttValueOffset = this.fEntityReader.currentOffset();
            if (n2 != 0) {
                this.fAttValueMark = this.fAttValueOffset;
                n2 = 0;
            }
        }
    }
    
    public void checkXMLLangAttributeValue(final int n) throws Exception {
        final String string = this.fStringPool.toString(n);
        int n2 = -1;
        if (string.length() >= 2) {
            final char char1 = string.charAt(0);
            if (string.charAt(1) == '-') {
                if (char1 == 'i' || char1 == 'I' || char1 == 'x' || char1 == 'X') {
                    n2 = 1;
                }
            }
            else {
                final char char2 = string.charAt(1);
                if (((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) && ((char2 >= 'a' && char2 <= 'z') || (char2 >= 'A' && char2 <= 'Z'))) {
                    n2 = 2;
                }
            }
        }
        if (n2 > 0 && string.length() > n2) {
            char c = string.charAt(n2++);
            if (c != '-') {
                n2 = -1;
            }
            else {
                while (true) {
                    if (c == '-') {
                        if (string.length() == n2) {
                            n2 = -1;
                            break;
                        }
                        final char char3 = string.charAt(n2++);
                        if ((char3 < 'a' || char3 > 'z') && (char3 < 'A' || char3 > 'Z')) {
                            n2 = -1;
                            break;
                        }
                        if (string.length() == n2) {
                            break;
                        }
                    }
                    else {
                        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                            n2 = -1;
                            break;
                        }
                        if (string.length() == n2) {
                            break;
                        }
                    }
                    c = string.charAt(n2++);
                }
            }
        }
        if (n2 == -1) {
            this.reportFatalXMLError(65, 65, string);
        }
    }
    
    void reportFatalXMLError(final int n, final int n2) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, null, 2);
    }
    
    void reportFatalXMLError(final int n, final int n2, final int n3) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { this.fStringPool.toString(n3) }, 2);
    }
    
    void reportFatalXMLError(final int n, final int n2, final String s) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s }, 2);
    }
    
    void reportFatalXMLError(final int n, final int n2, final int n3, final int n4) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { this.fStringPool.toString(n3), this.fStringPool.toString(n4) }, 2);
    }
    
    void reportFatalXMLError(final int n, final int n2, final String s, final String s2) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s, s2 }, 2);
    }
    
    void reportFatalXMLError(final int n, final int n2, final String s, final String s2, final String s3) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s, s2, s3 }, 2);
    }
    
    void abortMarkup(final int n, final int n2) throws Exception {
        this.reportFatalXMLError(n, n2);
        this.skipPastEndOfCurrentMarkup();
    }
    
    void abortMarkup(final int n, final int n2, final int n3) throws Exception {
        this.reportFatalXMLError(n, n2, n3);
        this.skipPastEndOfCurrentMarkup();
    }
    
    void abortMarkup(final int n, final int n2, final String s) throws Exception {
        this.reportFatalXMLError(n, n2, s);
        this.skipPastEndOfCurrentMarkup();
    }
    
    void abortMarkup(final int n, final int n2, final int n3, final int n4) throws Exception {
        this.reportFatalXMLError(n, n2, n3, n4);
        this.skipPastEndOfCurrentMarkup();
    }
    
    void skipPastEndOfCurrentMarkup() throws Exception {
        this.fEntityReader.skipToChar('>');
        if (this.fEntityReader.lookingAtChar('>', true)) {
            --this.fScannerMarkupDepth;
        }
    }
    
    int setScannerState(final int fScannerState) {
        final int fScannerState2 = this.fScannerState;
        this.fScannerState = fScannerState;
        return fScannerState2;
    }
    
    void restoreScannerState(final int fScannerState) {
        if (this.fScannerState != 13) {
            this.fScannerState = fScannerState;
        }
    }
    
    void scanXMLDeclOrTextDecl(final boolean b) throws Exception {
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        final boolean b2 = false;
        final boolean b3 = true;
        final int n4 = 2;
        final int n5 = 3;
        final int n6 = 4;
        int i = b2 ? 1 : 0;
        do {
            this.fEntityReader.skipPastSpaces();
            final int currentOffset = this.fEntityReader.currentOffset();
            if (b) {
                if (i == (b2 ? 1 : 0) && this.fEntityReader.skippedString(XMLDocumentScanner.version_string)) {
                    i = (b3 ? 1 : 0);
                }
                else {
                    if (!this.fEntityReader.skippedString(XMLDocumentScanner.encoding_string)) {
                        this.abortMarkup(29, 28);
                        return;
                    }
                    i = n4;
                }
            }
            else if (i == (b2 ? 1 : 0)) {
                if (!this.fEntityReader.skippedString(XMLDocumentScanner.version_string)) {
                    this.abortMarkup(30, 29);
                    return;
                }
                i = (b3 ? 1 : 0);
            }
            else {
                if (i == (b3 ? 1 : 0)) {
                    if (this.fEntityReader.skippedString(XMLDocumentScanner.encoding_string)) {
                        i = n4;
                    }
                    else {
                        i = n5;
                    }
                }
                else {
                    i = n5;
                }
                if (i == n5 && !this.fEntityReader.skippedString(XMLDocumentScanner.standalone_string)) {
                    break;
                }
            }
            final int n7 = this.fEntityReader.currentOffset() - currentOffset;
            this.fEntityReader.skipPastSpaces();
            if (!this.fEntityReader.lookingAtChar('=', true)) {
                this.abortMarkup(b ? 32 : 31, (i == (b3 ? 1 : 0)) ? 30 : ((i == n4) ? 32 : 31), this.fEntityReader.addString(currentOffset, n7));
                return;
            }
            this.fEntityReader.skipPastSpaces();
            final int scanStringLiteral = this.fEntityReader.scanStringLiteral();
            switch (scanStringLiteral) {
                case -1: {
                    this.abortMarkup(b ? 34 : 33, (i == (b3 ? 1 : 0)) ? 33 : ((i == n4) ? 35 : 34), this.fEntityReader.addString(currentOffset, n7));
                    return;
                }
                case -2: {
                    final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                    if (this.fScannerState != 13) {
                        if (scanInvalidChar >= 0) {
                            this.reportFatalXMLError(b ? 36 : 35, (i == (b3 ? 1 : 0)) ? 36 : ((i == n4) ? 38 : 37), Integer.toHexString(scanInvalidChar));
                        }
                        this.skipPastEndOfCurrentMarkup();
                    }
                    return;
                }
                default: {
                    switch (i) {
                        case 1: {
                            n = scanStringLiteral;
                            final String string = this.fStringPool.toString(n);
                            if (!"1.0".equals(string)) {
                                if (!this.fEventHandler.validVersionNum(string)) {
                                    this.abortMarkup(37, 39, string);
                                    return;
                                }
                                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 38, 40, new Object[] { string }, 1);
                            }
                            if (this.fEntityReader.lookingAtSpace(true)) {
                                continue;
                            }
                            if (b) {
                                this.abortMarkup(39, 41);
                                return;
                            }
                            i = n6;
                            continue;
                        }
                        case 2: {
                            n2 = scanStringLiteral;
                            final String string2 = this.fStringPool.toString(n2);
                            if (!this.fEventHandler.validEncName(string2)) {
                                this.abortMarkup(40, 42, string2);
                                return;
                            }
                            if (!this.fEntityReader.lookingAtSpace(true)) {
                                i = n6;
                                continue;
                            }
                            if (b) {
                                this.fEntityReader.skipPastSpaces();
                                i = n6;
                                continue;
                            }
                            continue;
                        }
                        case 3: {
                            n3 = scanStringLiteral;
                            final String string3 = this.fStringPool.toString(n3);
                            final boolean equals = "yes".equals(string3);
                            if (!equals && !"no".equals(string3)) {
                                this.abortMarkup(41, 43, string3);
                                return;
                            }
                            this.fStandalone = equals;
                            this.fEntityReader.skipPastSpaces();
                            i = n6;
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                    break;
                }
            }
        } while (i != n6);
        if (!this.fEntityReader.lookingAtChar('?', true) || !this.fEntityReader.lookingAtChar('>', true)) {
            int n8;
            int n9;
            if (b) {
                n8 = 43;
                n9 = 45;
            }
            else {
                n8 = 42;
                n9 = 44;
            }
            this.abortMarkup(n8, n9);
            return;
        }
        --this.fScannerMarkupDepth;
        if (!b) {
            this.fEventHandler.callStartDocument(n, n2, n3);
        }
    }
    
    boolean scanElement(final int n) throws Exception {
        boolean b = false;
        boolean b2 = false;
        Label_0311: {
            if (!(b2 = this.fEntityReader.lookingAtChar('>', true))) {
                if (this.fEntityReader.lookingAtSpace(true)) {
                    final int setScannerState = this.setScannerState(9);
                    while (true) {
                        do {
                            this.fEntityReader.skipPastSpaces();
                            if (!(b2 = this.fEntityReader.lookingAtChar('>', true))) {
                                if (!(b = this.fEntityReader.lookingAtChar('/', true))) {
                                    this.setScannerState(10);
                                    final int scanAttributeName = this.fEventHandler.scanAttributeName(this.fEntityReader, n);
                                    if (scanAttributeName != -1) {
                                        this.fEntityReader.skipPastSpaces();
                                        if (!this.fEntityReader.lookingAtChar('=', true)) {
                                            if (this.fScannerState != 13) {
                                                this.abortMarkup(27, 26, n, scanAttributeName);
                                                this.restoreScannerState(setScannerState);
                                            }
                                            return false;
                                        }
                                        this.fEntityReader.skipPastSpaces();
                                        final int scanAttValue = this.fEventHandler.scanAttValue(n, scanAttributeName);
                                        if (scanAttValue == -1) {
                                            if (this.fScannerState != 13) {
                                                this.skipPastEndOfCurrentMarkup();
                                                this.restoreScannerState(setScannerState);
                                            }
                                            return false;
                                        }
                                        if (scanAttValue == -2) {
                                            this.reportFatalXMLError(28, 27, n, scanAttributeName);
                                        }
                                        this.restoreScannerState(9);
                                        continue;
                                    }
                                }
                            }
                            this.restoreScannerState(setScannerState);
                            break Label_0311;
                        } while (this.fEntityReader.lookingAtSpace(true));
                        if (!(b2 = this.fEntityReader.lookingAtChar('>', true))) {
                            b = this.fEntityReader.lookingAtChar('/', true);
                        }
                        continue;
                    }
                }
                else {
                    b = this.fEntityReader.lookingAtChar('/', true);
                }
            }
        }
        if (!b2 && (!b || !this.fEntityReader.lookingAtChar('>', true))) {
            if (this.fScannerState != 13) {
                this.abortMarkup(26, 25, n);
            }
            return false;
        }
        this.fEventHandler.callStartElement(n);
        --this.fScannerMarkupDepth;
        if (b) {
            this.fEventHandler.callEndElement(this.fReaderId);
            return false;
        }
        return true;
    }
    
    int scanCharRef() throws Exception {
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
    
    void scanComment() throws Exception {
        final int currentOffset = this.fEntityReader.currentOffset();
        int n = 0;
        final int setScannerState = this.setScannerState(2);
        while (this.fScannerState == 2) {
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
                    --this.fScannerMarkupDepth;
                    this.fEventHandler.callComment(this.fEntityReader.addString(currentOffset, n2 - currentOffset));
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
                if (this.fScannerState == 13 || scanInvalidChar < 0) {
                    continue;
                }
                this.reportFatalXMLError(10, 8, Integer.toHexString(scanInvalidChar));
            }
        }
        this.restoreScannerState(setScannerState);
    }
    
    void scanPI(final int n) throws Exception {
        final String string = this.fStringPool.toString(n);
        if (string.length() == 3 && (string.charAt(0) == 'X' || string.charAt(0) == 'x') && (string.charAt(1) == 'M' || string.charAt(1) == 'm') && (string.charAt(2) == 'L' || string.charAt(2) == 'l')) {
            this.abortMarkup(6, 4);
            return;
        }
        final int setScannerState = this.setScannerState(3);
        int currentOffset = -1;
        int n2 = -1;
        if (!this.fEntityReader.lookingAtSpace(true)) {
            if (!this.fEntityReader.lookingAtChar('?', true) || !this.fEntityReader.lookingAtChar('>', true)) {
                if (this.fScannerState != 13) {
                    this.abortMarkup(7, 5);
                    this.restoreScannerState(setScannerState);
                }
                return;
            }
            n2 = 0;
        }
        else {
            this.fEntityReader.skipPastSpaces();
            currentOffset = this.fEntityReader.currentOffset();
            while (this.fScannerState == 3) {
                while (this.fEntityReader.lookingAtChar('?', false)) {
                    final int currentOffset2 = this.fEntityReader.currentOffset();
                    this.fEntityReader.lookingAtChar('?', true);
                    if (this.fEntityReader.lookingAtChar('>', true)) {
                        n2 = currentOffset2 - currentOffset;
                        break;
                    }
                }
                if (n2 >= 0) {
                    break;
                }
                if (!this.fEntityReader.lookingAtValidChar(true)) {
                    final int scanInvalidChar = this.fEntityReader.scanInvalidChar();
                    if (this.fScannerState != 13) {
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
        --this.fScannerMarkupDepth;
        this.restoreScannerState(setScannerState);
        this.fEventHandler.callProcessingInstruction(n, (n2 == 0) ? 0 : this.fEntityReader.addString(currentOffset, n2));
    }
    
    static {
        cdata_string = new char[] { '[', 'C', 'D', 'A', 'T', 'A', '[' };
        xml_string = new char[] { 'x', 'm', 'l' };
        version_string = new char[] { 'v', 'e', 'r', 's', 'i', 'o', 'n' };
        doctype_string = new char[] { 'D', 'O', 'C', 'T', 'Y', 'P', 'E' };
        standalone_string = new char[] { 's', 't', 'a', 'n', 'd', 'a', 'l', 'o', 'n', 'e' };
        encoding_string = new char[] { 'e', 'n', 'c', 'o', 'd', 'i', 'n', 'g' };
    }
    
    final class XMLDeclDispatcher implements ScannerDispatcher
    {
        public boolean dispatch(final boolean b) throws Exception {
            if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('<', true)) {
                final XMLDocumentScanner this$0 = XMLDocumentScanner.this;
                ++this$0.fScannerMarkupDepth;
                XMLDocumentScanner.this.setScannerState(1);
                if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('?', true)) {
                    final int scanName = XMLDocumentScanner.this.fEntityReader.scanName(' ');
                    if (scanName == -1) {
                        XMLDocumentScanner.this.abortMarkup(131, 105);
                    }
                    else if ("xml".equals(XMLDocumentScanner.this.fStringPool.toString(scanName))) {
                        if (XMLDocumentScanner.this.fEntityReader.lookingAtSpace(true)) {
                            XMLDocumentScanner.this.scanXMLDeclOrTextDecl(false);
                        }
                        else {
                            XMLDocumentScanner.this.abortMarkup(6, 4);
                        }
                    }
                    else {
                        XMLDocumentScanner.this.fEventHandler.callStartDocument(-1, -1, -1);
                        XMLDocumentScanner.this.scanPI(scanName);
                    }
                    XMLDocumentScanner.this.fEventHandler.callStartDocument(-1, -1, -1);
                    XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new PrologDispatcher();
                    XMLDocumentScanner.this.restoreScannerState(5);
                    return true;
                }
                XMLDocumentScanner.this.fEventHandler.callStartDocument(-1, -1, -1);
                if (!XMLDocumentScanner.this.fEntityReader.lookingAtChar('!', true)) {
                    XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new ContentDispatcher();
                    XMLDocumentScanner.this.restoreScannerState(6);
                    return true;
                }
                if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('-', true)) {
                    if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('-', true)) {
                        XMLDocumentScanner.this.scanComment();
                    }
                    else {
                        XMLDocumentScanner.this.abortMarkup(129, 108);
                    }
                }
                else {
                    if (XMLDocumentScanner.this.fEntityReader.skippedString(XMLDocumentScanner.doctype_string)) {
                        XMLDocumentScanner.this.setScannerState(4);
                        XMLDocumentScanner.this.fSeenDoctypeDecl = true;
                        XMLDocumentScanner.this.fEventHandler.scanDoctypeDecl(XMLDocumentScanner.this.fStandalone);
                        final XMLDocumentScanner this$2 = XMLDocumentScanner.this;
                        --this$2.fScannerMarkupDepth;
                        XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new PrologDispatcher();
                        XMLDocumentScanner.this.restoreScannerState(5);
                        return true;
                    }
                    XMLDocumentScanner.this.abortMarkup(129, 108);
                }
            }
            else {
                XMLDocumentScanner.this.fEventHandler.callStartDocument(-1, -1, -1);
                if (XMLDocumentScanner.this.fEntityReader.lookingAtSpace(true)) {
                    XMLDocumentScanner.this.fEntityReader.skipPastSpaces();
                }
                else if (!XMLDocumentScanner.this.fEntityReader.lookingAtValidChar(false)) {
                    final int scanInvalidChar = XMLDocumentScanner.this.fEntityReader.scanInvalidChar();
                    if (XMLDocumentScanner.this.fScannerState != 13 && scanInvalidChar >= 0) {
                        XMLDocumentScanner.this.reportFatalXMLError(52, 54, Integer.toHexString(scanInvalidChar));
                    }
                }
                else {
                    XMLDocumentScanner.this.reportFatalXMLError(129, 108);
                    XMLDocumentScanner.this.fEntityReader.lookingAtValidChar(true);
                }
            }
            XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new PrologDispatcher();
            XMLDocumentScanner.this.restoreScannerState(5);
            return true;
        }
        
        public void endOfInput(final int n, final boolean b) throws Exception {
            Label_0140: {
                switch (XMLDocumentScanner.this.fScannerState) {
                    case 2: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(163, 133);
                            break Label_0140;
                        }
                        XMLDocumentScanner.this.reportFatalXMLError(162, 68);
                        break Label_0140;
                    }
                    case 3: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(164, 134);
                            break Label_0140;
                        }
                        XMLDocumentScanner.this.reportFatalXMLError(165, 68);
                        break Label_0140;
                    }
                    default: {
                        throw new RuntimeException("1]ScannerState " + XMLDocumentScanner.this.fScannerState);
                    }
                    case 0:
                    case 1:
                    case 4: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(134, 107);
                            XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new EndOfInputDispatcher();
                            XMLDocumentScanner.this.setScannerState(13);
                        }
                    }
                }
            }
        }
    }
    
    final class PrologDispatcher implements ScannerDispatcher
    {
        public boolean dispatch(final boolean b) throws Exception {
            do {
                if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('<', true)) {
                    final XMLDocumentScanner this$0 = XMLDocumentScanner.this;
                    ++this$0.fScannerMarkupDepth;
                    XMLDocumentScanner.this.setScannerState(1);
                    if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('?', true)) {
                        final int scanName = XMLDocumentScanner.this.fEntityReader.scanName(' ');
                        if (scanName == -1) {
                            XMLDocumentScanner.this.abortMarkup(131, 105);
                        }
                        else if ("xml".equals(XMLDocumentScanner.this.fStringPool.toString(scanName))) {
                            if (XMLDocumentScanner.this.fEntityReader.lookingAtSpace(true)) {
                                XMLDocumentScanner.this.abortMarkup(104, 137);
                            }
                            else {
                                XMLDocumentScanner.this.abortMarkup(6, 4);
                            }
                        }
                        else {
                            XMLDocumentScanner.this.scanPI(scanName);
                        }
                    }
                    else {
                        if (!XMLDocumentScanner.this.fEntityReader.lookingAtChar('!', true)) {
                            XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new ContentDispatcher();
                            XMLDocumentScanner.this.restoreScannerState(6);
                            return true;
                        }
                        if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('-', true)) {
                            if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('-', true)) {
                                XMLDocumentScanner.this.scanComment();
                            }
                            else {
                                XMLDocumentScanner.this.abortMarkup(129, 108);
                            }
                        }
                        else if (!XMLDocumentScanner.this.fSeenDoctypeDecl && XMLDocumentScanner.this.fEntityReader.skippedString(XMLDocumentScanner.doctype_string)) {
                            XMLDocumentScanner.this.setScannerState(4);
                            XMLDocumentScanner.this.fSeenDoctypeDecl = true;
                            XMLDocumentScanner.this.fEventHandler.scanDoctypeDecl(XMLDocumentScanner.this.fStandalone);
                            final XMLDocumentScanner this$2 = XMLDocumentScanner.this;
                            --this$2.fScannerMarkupDepth;
                        }
                        else {
                            XMLDocumentScanner.this.abortMarkup(129, 108);
                        }
                    }
                    XMLDocumentScanner.this.restoreScannerState(5);
                }
                else if (XMLDocumentScanner.this.fEntityReader.lookingAtSpace(true)) {
                    XMLDocumentScanner.this.fEntityReader.skipPastSpaces();
                }
                else if (!XMLDocumentScanner.this.fEntityReader.lookingAtValidChar(false)) {
                    final int scanInvalidChar = XMLDocumentScanner.this.fEntityReader.scanInvalidChar();
                    if (XMLDocumentScanner.this.fScannerState == 13 || scanInvalidChar < 0) {
                        continue;
                    }
                    XMLDocumentScanner.this.reportFatalXMLError(52, 54, Integer.toHexString(scanInvalidChar));
                }
                else {
                    XMLDocumentScanner.this.reportFatalXMLError(129, 108);
                    XMLDocumentScanner.this.fEntityReader.lookingAtValidChar(true);
                }
            } while (XMLDocumentScanner.this.fScannerState != 13 && b);
            return true;
        }
        
        public void endOfInput(final int n, final boolean b) throws Exception {
            Label_0140: {
                switch (XMLDocumentScanner.this.fScannerState) {
                    case 2: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(163, 133);
                            break Label_0140;
                        }
                        XMLDocumentScanner.this.reportFatalXMLError(162, 68);
                        break Label_0140;
                    }
                    case 3: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(164, 134);
                            break Label_0140;
                        }
                        XMLDocumentScanner.this.reportFatalXMLError(165, 68);
                        break Label_0140;
                    }
                    default: {
                        throw new RuntimeException("2]ScannerState " + XMLDocumentScanner.this.fScannerState);
                    }
                    case 1:
                    case 4:
                    case 5: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(134, 107);
                            XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new EndOfInputDispatcher();
                            XMLDocumentScanner.this.setScannerState(13);
                        }
                    }
                }
            }
        }
    }
    
    final class ContentDispatcher implements ScannerDispatcher
    {
        private int fContentReader;
        private int fElementDepth;
        private int fCurrentElementType;
        private int[] fElementTypeStack;
        
        void popElementType() {
            if (this.fElementDepth-- == 0) {
                throw new RuntimeException("popElementType");
            }
            if (this.fElementDepth == 0) {
                this.fCurrentElementType = -1;
                return;
            }
            this.fCurrentElementType = this.fElementTypeStack[this.fElementDepth - 1];
        }
        
        public boolean dispatch(final boolean b) throws Exception {
            do {
                switch (XMLDocumentScanner.this.fScannerState) {
                    case 6: {
                        final int scanElementType = XMLDocumentScanner.this.fEventHandler.scanElementType(XMLDocumentScanner.this.fEntityReader, '>');
                        if (scanElementType == -1) {
                            XMLDocumentScanner.this.reportFatalXMLError(129, 108);
                            XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new PrologDispatcher();
                            XMLDocumentScanner.this.restoreScannerState(5);
                            return true;
                        }
                        this.fContentReader = XMLDocumentScanner.this.fReaderId;
                        XMLDocumentScanner.this.fSeenRootElement = true;
                        if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('>', true)) {
                            XMLDocumentScanner.this.fEventHandler.callStartElement(scanElementType);
                            final XMLDocumentScanner this$0 = XMLDocumentScanner.this;
                            --this$0.fScannerMarkupDepth;
                            if (this.fElementDepth == this.fElementTypeStack.length) {
                                final int[] fElementTypeStack = new int[this.fElementDepth * 2];
                                System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack, 0, this.fElementDepth);
                                this.fElementTypeStack = fElementTypeStack;
                            }
                            this.fCurrentElementType = scanElementType;
                            this.fElementTypeStack[this.fElementDepth] = scanElementType;
                            ++this.fElementDepth;
                            XMLDocumentScanner.this.restoreScannerState(7);
                            continue;
                        }
                        if (XMLDocumentScanner.this.scanElement(scanElementType)) {
                            if (this.fElementDepth == this.fElementTypeStack.length) {
                                final int[] fElementTypeStack2 = new int[this.fElementDepth * 2];
                                System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack2, 0, this.fElementDepth);
                                this.fElementTypeStack = fElementTypeStack2;
                            }
                            this.fCurrentElementType = scanElementType;
                            this.fElementTypeStack[this.fElementDepth] = scanElementType;
                            ++this.fElementDepth;
                            XMLDocumentScanner.this.restoreScannerState(7);
                            continue;
                        }
                        XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new TrailingMiscDispatcher();
                        XMLDocumentScanner.this.restoreScannerState(12);
                        return true;
                    }
                    case 1: {
                        if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('?', true)) {
                            final int scanName = XMLDocumentScanner.this.fEntityReader.scanName(' ');
                            if (scanName == -1) {
                                XMLDocumentScanner.this.abortMarkup(131, 105);
                            }
                            else if ("xml".equals(XMLDocumentScanner.this.fStringPool.toString(scanName))) {
                                if (XMLDocumentScanner.this.fEntityReader.lookingAtSpace(true)) {
                                    if (XMLDocumentScanner.this.fParseTextDecl) {
                                        XMLDocumentScanner.this.scanXMLDeclOrTextDecl(true);
                                        XMLDocumentScanner.this.fParseTextDecl = false;
                                    }
                                    else {
                                        XMLDocumentScanner.this.abortMarkup(105, 138);
                                    }
                                }
                                else {
                                    XMLDocumentScanner.this.abortMarkup(6, 4);
                                }
                            }
                            else {
                                XMLDocumentScanner.this.scanPI(scanName);
                            }
                            XMLDocumentScanner.this.restoreScannerState(7);
                        }
                        else if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('!', true)) {
                            if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('-', true)) {
                                if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('-', true)) {
                                    XMLDocumentScanner.this.scanComment();
                                }
                                else {
                                    XMLDocumentScanner.this.abortMarkup(127, 110);
                                }
                            }
                            else if (XMLDocumentScanner.this.fEntityReader.skippedString(XMLDocumentScanner.cdata_string)) {
                                XMLDocumentScanner.this.fEntityReader.setInCDSect(true);
                                XMLDocumentScanner.this.fEventHandler.startCDATA();
                            }
                            else {
                                XMLDocumentScanner.this.abortMarkup(127, 110);
                            }
                        }
                        else if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('/', true)) {
                            if (!XMLDocumentScanner.this.fEventHandler.scanExpectedElementType(XMLDocumentScanner.this.fEntityReader, '>')) {
                                XMLDocumentScanner.this.abortMarkup(56, 58, this.fCurrentElementType);
                            }
                            else {
                                if (!XMLDocumentScanner.this.fEntityReader.lookingAtChar('>', true)) {
                                    XMLDocumentScanner.this.fEntityReader.skipPastSpaces();
                                    if (!XMLDocumentScanner.this.fEntityReader.lookingAtChar('>', true)) {
                                        XMLDocumentScanner.this.reportFatalXMLError(57, 59, this.fCurrentElementType);
                                    }
                                }
                                final XMLDocumentScanner this$2 = XMLDocumentScanner.this;
                                --this$2.fScannerMarkupDepth;
                                if (XMLDocumentScanner.this.fEventHandler.callEndElement(XMLDocumentScanner.this.fReaderId)) {
                                    XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new TrailingMiscDispatcher();
                                    XMLDocumentScanner.this.restoreScannerState(12);
                                    return true;
                                }
                                if (this.fElementDepth-- == 0) {
                                    throw new RuntimeException("popElementType");
                                }
                                if (this.fElementDepth == 0) {
                                    this.fCurrentElementType = -1;
                                }
                                else {
                                    this.fCurrentElementType = this.fElementTypeStack[this.fElementDepth - 1];
                                }
                            }
                        }
                        else {
                            final int scanElementType2 = XMLDocumentScanner.this.fEventHandler.scanElementType(XMLDocumentScanner.this.fEntityReader, '>');
                            if (scanElementType2 != -1) {
                                if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('>', true)) {
                                    XMLDocumentScanner.this.fEventHandler.callStartElement(scanElementType2);
                                    final XMLDocumentScanner this$3 = XMLDocumentScanner.this;
                                    --this$3.fScannerMarkupDepth;
                                    if (this.fElementDepth == this.fElementTypeStack.length) {
                                        final int[] fElementTypeStack3 = new int[this.fElementDepth * 2];
                                        System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack3, 0, this.fElementDepth);
                                        this.fElementTypeStack = fElementTypeStack3;
                                    }
                                    this.fCurrentElementType = scanElementType2;
                                    this.fElementTypeStack[this.fElementDepth] = scanElementType2;
                                    ++this.fElementDepth;
                                }
                                else if (XMLDocumentScanner.this.scanElement(scanElementType2)) {
                                    if (this.fElementDepth == this.fElementTypeStack.length) {
                                        final int[] fElementTypeStack4 = new int[this.fElementDepth * 2];
                                        System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack4, 0, this.fElementDepth);
                                        this.fElementTypeStack = fElementTypeStack4;
                                    }
                                    this.fCurrentElementType = scanElementType2;
                                    this.fElementTypeStack[this.fElementDepth] = scanElementType2;
                                    ++this.fElementDepth;
                                }
                            }
                            else {
                                XMLDocumentScanner.this.abortMarkup(127, 110);
                            }
                        }
                        XMLDocumentScanner.this.restoreScannerState(7);
                        continue;
                    }
                    case 7: {
                        if (XMLDocumentScanner.this.fParseTextDecl && XMLDocumentScanner.this.fEntityReader.lookingAtChar('<', true)) {
                            final XMLDocumentScanner this$4 = XMLDocumentScanner.this;
                            ++this$4.fScannerMarkupDepth;
                            XMLDocumentScanner.this.setScannerState(1);
                            continue;
                        }
                        switch (XMLDocumentScanner.this.fEntityReader.scanContent(this.fCurrentElementType)) {
                            case 0: {
                                final XMLDocumentScanner this$5 = XMLDocumentScanner.this;
                                ++this$5.fScannerMarkupDepth;
                                final int scanName2 = XMLDocumentScanner.this.fEntityReader.scanName(' ');
                                if (scanName2 == -1) {
                                    XMLDocumentScanner.this.abortMarkup(131, 105);
                                    continue;
                                }
                                if (!"xml".equals(XMLDocumentScanner.this.fStringPool.toString(scanName2))) {
                                    XMLDocumentScanner.this.scanPI(scanName2);
                                    continue;
                                }
                                if (!XMLDocumentScanner.this.fEntityReader.lookingAtSpace(true)) {
                                    XMLDocumentScanner.this.abortMarkup(6, 4);
                                    continue;
                                }
                                if (XMLDocumentScanner.this.fReaderId == this.fContentReader) {
                                    XMLDocumentScanner.this.abortMarkup(104, 137);
                                    continue;
                                }
                                XMLDocumentScanner.this.abortMarkup(105, 138);
                                continue;
                            }
                            case 1: {
                                final XMLDocumentScanner this$6 = XMLDocumentScanner.this;
                                ++this$6.fScannerMarkupDepth;
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                XMLDocumentScanner.this.scanComment();
                                continue;
                            }
                            case 2: {
                                final XMLDocumentScanner this$7 = XMLDocumentScanner.this;
                                ++this$7.fScannerMarkupDepth;
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                XMLDocumentScanner.this.fEntityReader.setInCDSect(true);
                                XMLDocumentScanner.this.fEventHandler.startCDATA();
                                continue;
                            }
                            case 4: {
                                final XMLDocumentScanner this$8 = XMLDocumentScanner.this;
                                ++this$8.fScannerMarkupDepth;
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                if (!XMLDocumentScanner.this.fEventHandler.scanExpectedElementType(XMLDocumentScanner.this.fEntityReader, '>')) {
                                    XMLDocumentScanner.this.abortMarkup(56, 58, this.fCurrentElementType);
                                }
                                else {
                                    if (!XMLDocumentScanner.this.fEntityReader.lookingAtChar('>', true)) {
                                        XMLDocumentScanner.this.fEntityReader.skipPastSpaces();
                                        if (!XMLDocumentScanner.this.fEntityReader.lookingAtChar('>', true)) {
                                            XMLDocumentScanner.this.reportFatalXMLError(57, 59, this.fCurrentElementType);
                                        }
                                    }
                                    final XMLDocumentScanner this$9 = XMLDocumentScanner.this;
                                    --this$9.fScannerMarkupDepth;
                                    if (XMLDocumentScanner.this.fEventHandler.callEndElement(XMLDocumentScanner.this.fReaderId)) {
                                        XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new TrailingMiscDispatcher();
                                        XMLDocumentScanner.this.restoreScannerState(12);
                                        return true;
                                    }
                                    if (this.fElementDepth-- == 0) {
                                        throw new RuntimeException("popElementType");
                                    }
                                    if (this.fElementDepth == 0) {
                                        this.fCurrentElementType = -1;
                                    }
                                    else {
                                        this.fCurrentElementType = this.fElementTypeStack[this.fElementDepth - 1];
                                    }
                                }
                                XMLDocumentScanner.this.restoreScannerState(7);
                                continue;
                            }
                            case 6: {
                                final XMLDocumentScanner this$10 = XMLDocumentScanner.this;
                                ++this$10.fScannerMarkupDepth;
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                final int scanElementType3 = XMLDocumentScanner.this.fEventHandler.scanElementType(XMLDocumentScanner.this.fEntityReader, '>');
                                if (scanElementType3 != -1) {
                                    if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('>', true)) {
                                        XMLDocumentScanner.this.fEventHandler.callStartElement(scanElementType3);
                                        final XMLDocumentScanner this$11 = XMLDocumentScanner.this;
                                        --this$11.fScannerMarkupDepth;
                                        if (this.fElementDepth == this.fElementTypeStack.length) {
                                            final int[] fElementTypeStack5 = new int[this.fElementDepth * 2];
                                            System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack5, 0, this.fElementDepth);
                                            this.fElementTypeStack = fElementTypeStack5;
                                        }
                                        this.fCurrentElementType = scanElementType3;
                                        this.fElementTypeStack[this.fElementDepth] = scanElementType3;
                                        ++this.fElementDepth;
                                    }
                                    else if (XMLDocumentScanner.this.scanElement(scanElementType3)) {
                                        if (this.fElementDepth == this.fElementTypeStack.length) {
                                            final int[] fElementTypeStack6 = new int[this.fElementDepth * 2];
                                            System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack6, 0, this.fElementDepth);
                                            this.fElementTypeStack = fElementTypeStack6;
                                        }
                                        this.fCurrentElementType = scanElementType3;
                                        this.fElementTypeStack[this.fElementDepth] = scanElementType3;
                                        ++this.fElementDepth;
                                    }
                                }
                                else {
                                    XMLDocumentScanner.this.abortMarkup(127, 110);
                                }
                                if (XMLDocumentScanner.this.fScannerState != 13) {
                                    XMLDocumentScanner.this.fScannerState = 7;
                                    continue;
                                }
                                continue;
                            }
                            case 5: {
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                if (XMLDocumentScanner.this.fEventHandler.callEndElement(XMLDocumentScanner.this.fReaderId)) {
                                    if (XMLDocumentScanner.this.fScannerState != 13) {
                                        XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new TrailingMiscDispatcher();
                                        XMLDocumentScanner.this.fScannerState = 12;
                                    }
                                    return true;
                                }
                                if (this.fElementDepth-- == 0) {
                                    throw new RuntimeException("popElementType");
                                }
                                if (this.fElementDepth == 0) {
                                    this.fCurrentElementType = -1;
                                }
                                else {
                                    this.fCurrentElementType = this.fElementTypeStack[this.fElementDepth - 1];
                                }
                                if (XMLDocumentScanner.this.fScannerState != 13) {
                                    XMLDocumentScanner.this.fScannerState = 7;
                                    continue;
                                }
                                continue;
                            }
                            case 7: {
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                XMLDocumentScanner.this.setScannerState(8);
                                final int scanCharRef = XMLDocumentScanner.this.scanCharRef();
                                if (scanCharRef != -1) {
                                    XMLDocumentScanner.this.fEventHandler.callCharacters(scanCharRef);
                                }
                                XMLDocumentScanner.this.restoreScannerState(7);
                                continue;
                            }
                            case 8:
                            case 12: {
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                XMLDocumentScanner.this.setScannerState(8);
                                final int currentOffset = XMLDocumentScanner.this.fEntityReader.currentOffset();
                                XMLDocumentScanner.this.fEntityReader.skipPastName(';');
                                final int n = XMLDocumentScanner.this.fEntityReader.currentOffset() - currentOffset;
                                if (n == 0) {
                                    XMLDocumentScanner.this.reportFatalXMLError(14, 13);
                                    XMLDocumentScanner.this.restoreScannerState(7);
                                    continue;
                                }
                                if (!XMLDocumentScanner.this.fEntityReader.lookingAtChar(';', true)) {
                                    XMLDocumentScanner.this.reportFatalXMLError(15, 14, XMLDocumentScanner.this.fEntityReader.addString(currentOffset, n));
                                    XMLDocumentScanner.this.restoreScannerState(7);
                                    continue;
                                }
                                XMLDocumentScanner.this.restoreScannerState(7);
                                XMLDocumentScanner.this.fParseTextDecl = XMLDocumentScanner.this.fEntityHandler.startReadingFromEntity(XMLDocumentScanner.this.fEntityReader.addSymbol(currentOffset, n), this.fElementDepth, 2);
                                continue;
                            }
                            case 3: {
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                if (XMLDocumentScanner.this.fEntityReader.getInCDSect()) {
                                    XMLDocumentScanner.this.fEntityReader.setInCDSect(false);
                                    XMLDocumentScanner.this.fEventHandler.endCDATA();
                                    final XMLDocumentScanner this$12 = XMLDocumentScanner.this;
                                    --this$12.fScannerMarkupDepth;
                                }
                                else {
                                    XMLDocumentScanner.this.reportFatalXMLError(124, 104);
                                }
                                XMLDocumentScanner.this.restoreScannerState(7);
                                continue;
                            }
                            case 9: {
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                if (XMLDocumentScanner.this.fScannerState != 13) {
                                    if (!XMLDocumentScanner.this.fEntityReader.lookingAtValidChar(false)) {
                                        final int scanInvalidChar = XMLDocumentScanner.this.fEntityReader.scanInvalidChar();
                                        if (XMLDocumentScanner.this.fScannerState != 13 && scanInvalidChar >= 0) {
                                            if (XMLDocumentScanner.this.fEntityReader.getInCDSect()) {
                                                XMLDocumentScanner.this.reportFatalXMLError(54, 56, Integer.toHexString(scanInvalidChar));
                                            }
                                            else {
                                                XMLDocumentScanner.this.reportFatalXMLError(55, 57, Integer.toHexString(scanInvalidChar));
                                            }
                                        }
                                    }
                                    XMLDocumentScanner.this.restoreScannerState(7);
                                    continue;
                                }
                                continue;
                            }
                            case 10: {
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                XMLDocumentScanner.this.abortMarkup(127, 110);
                                continue;
                            }
                            case 11: {
                                final XMLDocumentScanner this$13 = XMLDocumentScanner.this;
                                ++this$13.fScannerMarkupDepth;
                                XMLDocumentScanner.this.fParseTextDecl = false;
                                XMLDocumentScanner.this.fScannerState = 1;
                                continue;
                            }
                            default: {
                                throw new RuntimeException("3]ScannerState " + XMLDocumentScanner.this.fScannerState);
                            }
                        }
                        break;
                    }
                    default: {
                        throw new RuntimeException("4]ScannerState " + XMLDocumentScanner.this.fScannerState);
                    }
                }
            } while (XMLDocumentScanner.this.fScannerState != 13 && b);
            return true;
        }
        
        public void endOfInput(final int n, final boolean b) throws Exception {
            Label_0323: {
                switch (XMLDocumentScanner.this.fScannerState) {
                    case 7: {
                        if (XMLDocumentScanner.this.fEntityReader.getInCDSect()) {
                            XMLDocumentScanner.this.reportFatalXMLError(66, 66);
                        }
                        break Label_0323;
                    }
                    case 11: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(151, 136, XMLDocumentScanner.this.fAttValueElementType, XMLDocumentScanner.this.fAttValueAttrName);
                            break Label_0323;
                        }
                        if (XMLDocumentScanner.this.fReaderId != XMLDocumentScanner.this.fAttValueReader) {
                            XMLDocumentScanner.this.fEntityReader.append(XMLDocumentScanner.this.fLiteralData, XMLDocumentScanner.this.fAttValueMark, XMLDocumentScanner.this.fAttValueOffset - XMLDocumentScanner.this.fAttValueMark);
                        }
                        break Label_0323;
                    }
                    case 2: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(163, 133);
                            break Label_0323;
                        }
                        XMLDocumentScanner.this.reportFatalXMLError(162, 68);
                        break Label_0323;
                    }
                    case 3: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(164, 134);
                            break Label_0323;
                        }
                        XMLDocumentScanner.this.reportFatalXMLError(165, 68);
                        break Label_0323;
                    }
                    case 8: {
                        if (!b) {
                            XMLDocumentScanner.this.reportFatalXMLError(166, 135);
                            break Label_0323;
                        }
                        XMLDocumentScanner.this.reportFatalXMLError(161, 68);
                        break Label_0323;
                    }
                    default: {
                        throw new RuntimeException("5]ScannerState " + XMLDocumentScanner.this.fScannerState);
                    }
                    case 1:
                    case 6:
                    case 9:
                    case 10: {
                        if (!b) {
                            if (this.fElementDepth > 0) {
                                XMLDocumentScanner.this.reportFatalXMLError(56, 58, this.fCurrentElementType);
                            }
                            XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new EndOfInputDispatcher();
                            XMLDocumentScanner.this.setScannerState(13);
                        }
                    }
                }
            }
        }
        
        ContentDispatcher() {
            this.fContentReader = -1;
            this.fCurrentElementType = -1;
            this.fElementTypeStack = new int[8];
        }
    }
    
    final class TrailingMiscDispatcher implements ScannerDispatcher
    {
        public boolean dispatch(final boolean b) throws Exception {
            do {
                if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('<', true)) {
                    final XMLDocumentScanner this$0 = XMLDocumentScanner.this;
                    ++this$0.fScannerMarkupDepth;
                    XMLDocumentScanner.this.setScannerState(1);
                    if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('?', true)) {
                        final int scanName = XMLDocumentScanner.this.fEntityReader.scanName(' ');
                        if (scanName == -1) {
                            XMLDocumentScanner.this.abortMarkup(131, 105);
                        }
                        else if ("xml".equals(XMLDocumentScanner.this.fStringPool.toString(scanName))) {
                            if (XMLDocumentScanner.this.fEntityReader.lookingAtSpace(true)) {
                                XMLDocumentScanner.this.abortMarkup(104, 137);
                            }
                            else {
                                XMLDocumentScanner.this.abortMarkup(6, 4);
                            }
                        }
                        else {
                            XMLDocumentScanner.this.scanPI(scanName);
                        }
                    }
                    else if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('!', true)) {
                        if (XMLDocumentScanner.this.fEntityReader.lookingAtChar('-', true) && XMLDocumentScanner.this.fEntityReader.lookingAtChar('-', true)) {
                            XMLDocumentScanner.this.scanComment();
                        }
                        else {
                            XMLDocumentScanner.this.abortMarkup(128, 109);
                        }
                    }
                    else {
                        XMLDocumentScanner.this.abortMarkup(128, 109);
                    }
                    XMLDocumentScanner.this.restoreScannerState(12);
                }
                else if (XMLDocumentScanner.this.fEntityReader.lookingAtSpace(true)) {
                    XMLDocumentScanner.this.fEntityReader.skipPastSpaces();
                }
                else if (!XMLDocumentScanner.this.fEntityReader.lookingAtValidChar(false)) {
                    final int scanInvalidChar = XMLDocumentScanner.this.fEntityReader.scanInvalidChar();
                    if (XMLDocumentScanner.this.fScannerState == 13 || scanInvalidChar < 0) {
                        continue;
                    }
                    XMLDocumentScanner.this.reportFatalXMLError(53, 55, Integer.toHexString(scanInvalidChar));
                }
                else {
                    XMLDocumentScanner.this.reportFatalXMLError(128, 109);
                    XMLDocumentScanner.this.fEntityReader.lookingAtValidChar(true);
                }
            } while (XMLDocumentScanner.this.fScannerState != 13 && b);
            return true;
        }
        
        public void endOfInput(final int n, final boolean b) throws Exception {
            if (b) {
                throw new RuntimeException("TrailingMiscDispatcher.endOfInput moreToFollow");
            }
            while (true) {
                switch (XMLDocumentScanner.this.fScannerState) {
                    default: {
                        throw new RuntimeException("6]ScannerState " + XMLDocumentScanner.this.fScannerState);
                    }
                    case 1:
                    case 12: {
                        XMLDocumentScanner.this.fDispatcher = (ScannerDispatcher)new EndOfInputDispatcher();
                        XMLDocumentScanner.this.setScannerState(13);
                    }
                    case 2: {
                        XMLDocumentScanner.this.reportFatalXMLError(163, 133);
                        continue;
                    }
                    case 3: {
                        XMLDocumentScanner.this.reportFatalXMLError(164, 134);
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    final class EndOfInputDispatcher implements ScannerDispatcher
    {
        public boolean dispatch(final boolean b) throws Exception {
            if (XMLDocumentScanner.this.fScannerState != 14) {
                XMLDocumentScanner.this.fEventHandler.callEndDocument();
            }
            XMLDocumentScanner.this.setScannerState(14);
            return false;
        }
        
        public void endOfInput(final int n, final boolean b) throws Exception {
            throw new RuntimeException("7]ScannerState " + XMLDocumentScanner.this.fScannerState);
        }
    }
    
    public interface EventHandler
    {
        int scanElementType(final XMLEntityHandler.EntityReader p0, final char p1) throws Exception;
        
        boolean scanExpectedElementType(final XMLEntityHandler.EntityReader p0, final char p1) throws Exception;
        
        int scanAttributeName(final XMLEntityHandler.EntityReader p0, final int p1) throws Exception;
        
        void callStartDocument(final int p0, final int p1, final int p2) throws Exception;
        
        void callEndDocument() throws Exception;
        
        void callStartElement(final int p0) throws Exception;
        
        boolean callEndElement(final int p0) throws Exception;
        
        boolean validVersionNum(final String p0) throws Exception;
        
        boolean validEncName(final String p0) throws Exception;
        
        void startCDATA() throws Exception;
        
        void endCDATA() throws Exception;
        
        void callCharacters(final int p0) throws Exception;
        
        void callProcessingInstruction(final int p0, final int p1) throws Exception;
        
        void callComment(final int p0) throws Exception;
        
        void scanDoctypeDecl(final boolean p0) throws Exception;
        
        int scanAttValue(final int p0, final int p1) throws Exception;
    }
    
    interface ScannerDispatcher
    {
        boolean dispatch(final boolean p0) throws Exception;
        
        void endOfInput(final int p0, final boolean p1) throws Exception;
    }
}
