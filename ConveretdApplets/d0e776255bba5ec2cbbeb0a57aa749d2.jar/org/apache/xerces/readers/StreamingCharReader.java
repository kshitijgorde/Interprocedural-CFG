// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.IOException;
import java.util.Locale;
import org.apache.xerces.utils.ImplementationMessages;
import org.apache.xerces.utils.QName;
import org.apache.xerces.utils.StringHasher;
import org.apache.xerces.utils.XMLCharacterProperties;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.CharDataChunk;
import java.io.Reader;
import java.util.Vector;
import org.apache.xerces.utils.StringPool;

public class StreamingCharReader extends XMLEntityReader
{
    private static final char[] cdata_string;
    private StringPool fStringPool;
    private boolean fCallClearPreviousChunk;
    private Vector fDeferredErrors;
    private boolean seenCR;
    private int oweChar;
    private char[] inBuffer;
    protected Reader fCharacterStream;
    protected CharDataChunk fCurrentChunk;
    protected int fCurrentIndex;
    protected int fFillIndex;
    protected char[] fMostRecentData;
    protected int fMostRecentChar;
    protected int fLength;
    protected boolean fCalledCharPropInit;
    
    public StreamingCharReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final Reader fCharacterStream, final StringPool fStringPool) throws Exception {
        super(xmlEntityHandler, xmlErrorReporter, b);
        this.fStringPool = null;
        this.fCallClearPreviousChunk = true;
        this.fDeferredErrors = null;
        this.seenCR = false;
        this.oweChar = -1;
        this.inBuffer = new char[2];
        this.fCharacterStream = null;
        this.fCurrentChunk = null;
        this.fCurrentIndex = 0;
        this.fFillIndex = 0;
        this.fMostRecentData = null;
        this.fMostRecentChar = 0;
        this.fLength = 0;
        this.fCalledCharPropInit = false;
        this.fStringPool = fStringPool;
        this.fCharacterStream = fCharacterStream;
        this.fCurrentChunk = CharDataChunk.createChunk(this.fStringPool, null);
        this.loadFirstChar();
    }
    
    protected void deferException(final int n, final Object[] array, final int n2) {
        if (this.fDeferredErrors == null) {
            this.fDeferredErrors = new Vector();
        }
        this.fDeferredErrors.addElement(new DeferredError(n, array, n2));
    }
    
    protected XMLEntityHandler.EntityReader changeReaders() throws Exception {
        final XMLEntityHandler.EntityReader changeReaders = super.changeReaders();
        this.fCurrentChunk.releaseChunk();
        this.fCurrentChunk = null;
        return changeReaders;
    }
    
    public void append(final XMLEntityHandler.CharBuffer charBuffer, final int n, final int n2) {
        this.fCurrentChunk.append(charBuffer, n, n2);
    }
    
    public int addString(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
        return this.fCurrentChunk.addString(n, n2);
    }
    
    public int addSymbol(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
        return this.fCurrentChunk.addSymbol(n, n2, 0);
    }
    
    public boolean lookingAtChar(final char c, final boolean b) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar != c) {
            return fMostRecentChar == '\0' && this.atEOF(super.fCurrentOffset + 1) && this.changeReaders().lookingAtChar(c, b);
        }
        if (b) {
            ++super.fCharacterCounter;
            this.loadNextChar();
        }
        return true;
    }
    
    public boolean lookingAtValidChar(final boolean b) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar < 55296) {
            if (fMostRecentChar >= 32 || fMostRecentChar == 9) {
                if (b) {
                    ++super.fCharacterCounter;
                    this.loadNextChar();
                }
                return true;
            }
            if (fMostRecentChar == 10) {
                if (b) {
                    ++super.fLinefeedCounter;
                    super.fCharacterCounter = 1;
                    this.loadNextChar();
                }
                return true;
            }
            return fMostRecentChar == 0 && this.atEOF(super.fCurrentOffset + 1) && this.changeReaders().lookingAtValidChar(b);
        }
        else {
            if (fMostRecentChar > 65533) {
                return false;
            }
            if (fMostRecentChar < 56320) {
                final CharDataChunk fCurrentChunk = this.fCurrentChunk;
                final int fCurrentIndex = this.fCurrentIndex;
                final int fCurrentOffset = super.fCurrentOffset;
                final int loadNextChar = this.loadNextChar();
                final boolean b2 = loadNextChar >= 56320 && loadNextChar < 57344;
                if (!b2 || !b) {
                    this.fCurrentChunk = fCurrentChunk;
                    this.fCurrentIndex = fCurrentIndex;
                    super.fCurrentOffset = fCurrentOffset;
                    this.fMostRecentData = fCurrentChunk.toCharArray();
                    this.fMostRecentChar = (this.fMostRecentData[fCurrentIndex] & '\uffff');
                    return b2;
                }
            }
            else if (fMostRecentChar < 57344) {
                return false;
            }
            if (b) {
                ++super.fCharacterCounter;
                this.loadNextChar();
            }
            return true;
        }
    }
    
    public boolean lookingAtSpace(final boolean b) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar > 32) {
            return false;
        }
        if (fMostRecentChar == 32 || fMostRecentChar == 9) {
            if (!b) {
                return true;
            }
            ++super.fCharacterCounter;
        }
        else {
            if (fMostRecentChar != 10) {
                return fMostRecentChar == 0 && this.atEOF(super.fCurrentOffset + 1) && this.changeReaders().lookingAtSpace(b);
            }
            if (!b) {
                return true;
            }
            ++super.fLinefeedCounter;
            super.fCharacterCounter = 1;
        }
        this.loadNextChar();
        return true;
    }
    
    public void skipToChar(final char c) throws Exception {
        int i = this.fMostRecentChar;
        while (i != c) {
            if (i == '\0') {
                if (this.atEOF(super.fCurrentOffset + 1)) {
                    this.changeReaders().skipToChar(c);
                    return;
                }
                ++super.fCharacterCounter;
            }
            else if (i == '\n') {
                ++super.fLinefeedCounter;
                super.fCharacterCounter = 1;
            }
            else if (i >= '\ud800' && i < '\udc00') {
                ++super.fCharacterCounter;
                i = this.loadNextChar();
                if (i < '\udc00') {
                    continue;
                }
                if (i >= '\ue000') {
                    continue;
                }
            }
            else {
                ++super.fCharacterCounter;
            }
            i = this.loadNextChar();
        }
    }
    
    public void skipPastSpaces() throws Exception {
        int n = this.fMostRecentChar;
        while (true) {
            if (n == 32 || n == 9) {
                ++super.fCharacterCounter;
            }
            else {
                if (n != 10) {
                    break;
                }
                ++super.fLinefeedCounter;
                super.fCharacterCounter = 1;
            }
            n = this.loadNextChar();
        }
        if (n == 0 && this.atEOF(super.fCurrentOffset + 1)) {
            this.changeReaders().skipPastSpaces();
        }
    }
    
    public void skipPastName(final char c) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar < 128) {
            if (XMLCharacterProperties.fgAsciiInitialNameChar[fMostRecentChar] == 0) {
                return;
            }
        }
        else {
            if (!this.fCalledCharPropInit) {
                XMLCharacterProperties.initCharFlags();
                this.fCalledCharPropInit = true;
            }
            if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x2) == 0x0) {
                return;
            }
        }
        while (true) {
            ++super.fCharacterCounter;
            final int loadNextChar = this.loadNextChar();
            if (c == loadNextChar) {
                return;
            }
            if (loadNextChar < '\u0080') {
                if (XMLCharacterProperties.fgAsciiNameChar[loadNextChar] == 0) {
                    return;
                }
                continue;
            }
            else {
                if (!this.fCalledCharPropInit) {
                    XMLCharacterProperties.initCharFlags();
                    this.fCalledCharPropInit = true;
                }
                if ((XMLCharacterProperties.fgCharFlags[loadNextChar] & 0x4) == 0x0) {
                    return;
                }
                continue;
            }
        }
    }
    
    public void skipPastNmtoken(final char c) throws Exception {
        for (int n = this.fMostRecentChar; c != n; n = this.loadNextChar()) {
            if (n < '\u0080') {
                if (XMLCharacterProperties.fgAsciiNameChar[n] == 0) {
                    return;
                }
            }
            else {
                if (!this.fCalledCharPropInit) {
                    XMLCharacterProperties.initCharFlags();
                    this.fCalledCharPropInit = true;
                }
                if ((XMLCharacterProperties.fgCharFlags[n] & 0x4) == 0x0) {
                    return;
                }
            }
            ++super.fCharacterCounter;
        }
    }
    
    public boolean skippedString(final char[] array) throws Exception {
        if (this.fMostRecentChar != array[0]) {
            return false;
        }
        final int length = array.length;
        final CharDataChunk fCurrentChunk = this.fCurrentChunk;
        final int fCurrentOffset = super.fCurrentOffset;
        final int fCurrentIndex = this.fCurrentIndex;
        int n = this.loadNextChar();
        for (int i = 1; i < length; ++i) {
            if (n != array[i]) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toCharArray();
                this.fMostRecentChar = (this.fMostRecentData[fCurrentIndex] & '\uffff');
                return false;
            }
            n = this.loadNextChar();
        }
        super.fCharacterCounter += length;
        return true;
    }
    
    public int scanInvalidChar() throws Exception {
        int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar == 10) {
            ++super.fLinefeedCounter;
            super.fCharacterCounter = 1;
            this.loadNextChar();
        }
        else if (fMostRecentChar == 0) {
            if (this.atEOF(super.fCurrentOffset + 1)) {
                return this.changeReaders().scanInvalidChar();
            }
            if (this.fDeferredErrors != null) {
                for (int i = 0; i < this.fDeferredErrors.size(); ++i) {
                    final DeferredError deferredError = this.fDeferredErrors.elementAt(i);
                    if (deferredError.offset == this.fCurrentIndex) {
                        super.fErrorReporter.reportError(super.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", deferredError.errorCode, 0, deferredError.args, 2);
                        this.fDeferredErrors.removeElementAt(i);
                        ++super.fCharacterCounter;
                        this.loadNextChar();
                        return -1;
                    }
                }
            }
            ++super.fCharacterCounter;
            this.loadNextChar();
        }
        else {
            ++super.fCharacterCounter;
            if (fMostRecentChar >= 55296 && fMostRecentChar < 56320) {
                final int loadNextChar = this.loadNextChar();
                if (loadNextChar >= 56320 && loadNextChar < 57344) {
                    fMostRecentChar = (fMostRecentChar - 55296 << 10) + (loadNextChar - 56320) + 65536;
                    this.loadNextChar();
                }
            }
            else {
                this.loadNextChar();
            }
        }
        return fMostRecentChar;
    }
    
    public int scanCharRef(final boolean b) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar == 0) {
            if (this.atEOF(super.fCurrentOffset + 1)) {
                return this.changeReaders().scanCharRef(b);
            }
            return -2;
        }
        else {
            int n;
            if (b) {
                if (fMostRecentChar > 102 || XMLCharacterProperties.fgAsciiXDigitChar[fMostRecentChar] == 0) {
                    return -2;
                }
                final int n2;
                n = n2 - (((n2 = fMostRecentChar) < 65) ? 48 : (((fMostRecentChar < 97) ? 65 : 97) - 10));
            }
            else {
                if (fMostRecentChar < 48 || fMostRecentChar > 57) {
                    return -2;
                }
                n = fMostRecentChar - 48;
            }
            ++super.fCharacterCounter;
            this.loadNextChar();
            boolean b2 = false;
            int fMostRecentChar2;
            while (true) {
                fMostRecentChar2 = this.fMostRecentChar;
                if (fMostRecentChar2 == 0) {
                    break;
                }
                if (b) {
                    if (fMostRecentChar2 > 102) {
                        break;
                    }
                    if (XMLCharacterProperties.fgAsciiXDigitChar[fMostRecentChar2] == 0) {
                        break;
                    }
                }
                else {
                    if (fMostRecentChar2 < 48) {
                        break;
                    }
                    if (fMostRecentChar2 > 57) {
                        break;
                    }
                }
                ++super.fCharacterCounter;
                this.loadNextChar();
                if (b) {
                    final int n3;
                    n = (n << 4) + (n3 - (((n3 = fMostRecentChar2) < 65) ? 48 : (((fMostRecentChar2 < 97) ? 65 : 97) - 10)));
                }
                else {
                    n = n * 10 + (fMostRecentChar2 - 48);
                }
                if (n <= 1114111) {
                    continue;
                }
                b2 = true;
                n = 0;
            }
            if (fMostRecentChar2 != 59) {
                return -1;
            }
            ++super.fCharacterCounter;
            this.loadNextChar();
            if (b2) {
                return -3;
            }
            return n;
        }
    }
    
    public int scanStringLiteral() throws Exception {
        final boolean lookingAtChar;
        if (!(lookingAtChar = this.lookingAtChar('\'', true)) && !this.lookingAtChar('\"', true)) {
            return -1;
        }
        final int fCurrentOffset = super.fCurrentOffset;
        final int n = lookingAtChar ? 39 : 34;
        while (!this.lookingAtChar((char)n, false)) {
            if (!this.lookingAtValidChar(true)) {
                return -2;
            }
        }
        final int addString = this.addString(fCurrentOffset, super.fCurrentOffset - fCurrentOffset);
        this.lookingAtChar((char)n, true);
        return addString;
    }
    
    public int scanAttValue(final char c, final boolean b) throws Exception {
        final int fCurrentOffset = super.fCurrentOffset;
        while (!this.lookingAtChar(c, false)) {
            if (this.lookingAtChar(' ', true)) {
                continue;
            }
            if (this.lookingAtSpace(false)) {
                return -1;
            }
            if (this.lookingAtChar('&', false)) {
                return -1;
            }
            if (this.lookingAtChar('<', false)) {
                return -2;
            }
            if (!this.lookingAtValidChar(true)) {
                return -3;
            }
        }
        final int n = b ? this.addSymbol(fCurrentOffset, super.fCurrentOffset - fCurrentOffset) : this.addString(fCurrentOffset, super.fCurrentOffset - fCurrentOffset);
        this.lookingAtChar(c, true);
        return n;
    }
    
    public int scanEntityValue(final int n, final boolean b) throws Exception {
        final int fCurrentOffset = super.fCurrentOffset;
        while (!this.atEOF(super.fCurrentOffset + 1)) {
            if (n != -1 && this.lookingAtChar((char)n, false)) {
                if (!b) {
                    return -1;
                }
                final int addString = this.addString(fCurrentOffset, super.fCurrentOffset - fCurrentOffset);
                this.lookingAtChar((char)n, true);
                return addString;
            }
            else {
                if (this.lookingAtChar('&', false)) {
                    return -2;
                }
                if (this.lookingAtChar('%', false)) {
                    return -3;
                }
                if (!this.lookingAtValidChar(true)) {
                    return -4;
                }
                continue;
            }
        }
        this.changeReaders();
        return -5;
    }
    
    public int scanName(final char c) throws Exception {
        int n = this.fMostRecentChar;
        if (n < 128) {
            if (XMLCharacterProperties.fgAsciiInitialNameChar[n] == 0) {
                return -1;
            }
        }
        else {
            if (!this.fCalledCharPropInit) {
                XMLCharacterProperties.initCharFlags();
                this.fCalledCharPropInit = true;
            }
            if ((XMLCharacterProperties.fgCharFlags[n] & 0x2) == 0x0) {
                return -1;
            }
        }
        final int fCurrentOffset = super.fCurrentOffset;
        ++super.fCharacterCounter;
        int hashChar = 0;
        while (true) {
            hashChar = StringHasher.hashChar(hashChar, n);
            n = this.loadNextChar();
            if (c == n) {
                break;
            }
            if (n < '\u0080') {
                if (XMLCharacterProperties.fgAsciiNameChar[n] == 0) {
                    break;
                }
            }
            else {
                if (!this.fCalledCharPropInit) {
                    XMLCharacterProperties.initCharFlags();
                    this.fCalledCharPropInit = true;
                }
                if ((XMLCharacterProperties.fgCharFlags[n] & 0x4) == 0x0) {
                    break;
                }
            }
            ++super.fCharacterCounter;
        }
        return this.fCurrentChunk.addSymbol(fCurrentOffset, super.fCurrentOffset - fCurrentOffset, StringHasher.finishHash(hashChar));
    }
    
    public boolean scanExpectedName(final char c, final StringPool.CharArrayRange charArrayRange) throws Exception {
        final char[] chars = charArrayRange.chars;
        int offset = charArrayRange.offset;
        final int length = charArrayRange.length;
        int n = this.fMostRecentChar;
        for (int i = 0; i < length; ++i) {
            if (n != chars[offset++]) {
                this.skipPastNmtoken(c);
                return false;
            }
            ++super.fCharacterCounter;
            n = this.loadNextChar();
        }
        if (n == c) {
            return true;
        }
        if (n < '\u0080') {
            if (XMLCharacterProperties.fgAsciiNameChar[n] == 0) {
                return true;
            }
        }
        else {
            if (!this.fCalledCharPropInit) {
                XMLCharacterProperties.initCharFlags();
                this.fCalledCharPropInit = true;
            }
            if ((XMLCharacterProperties.fgCharFlags[n] & 0x4) == 0x0) {
                return true;
            }
        }
        this.skipPastNmtoken(c);
        return false;
    }
    
    public void scanQName(final char c, final QName qName) throws Exception {
        int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar < 128) {
            if (XMLCharacterProperties.fgAsciiInitialNameChar[fMostRecentChar] == 0) {
                qName.clear();
                return;
            }
            if (fMostRecentChar == 58) {
                qName.clear();
                return;
            }
        }
        else {
            if (!this.fCalledCharPropInit) {
                XMLCharacterProperties.initCharFlags();
                this.fCalledCharPropInit = true;
            }
            if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x2) == 0x0) {
                qName.clear();
                return;
            }
        }
        final int fCurrentOffset = super.fCurrentOffset;
        ++super.fCharacterCounter;
        int hashChar = 0;
        int fCurrentOffset2 = -1;
        while (true) {
            hashChar = StringHasher.hashChar(hashChar, fMostRecentChar);
            fMostRecentChar = this.loadNextChar();
            if (c == fMostRecentChar) {
                break;
            }
            if (fMostRecentChar < '\u0080') {
                if (XMLCharacterProperties.fgAsciiNameChar[fMostRecentChar] == 0) {
                    break;
                }
                if (fMostRecentChar == ':') {
                    if (fCurrentOffset2 != -1) {
                        break;
                    }
                    fCurrentOffset2 = super.fCurrentOffset;
                    final CharDataChunk fCurrentChunk = this.fCurrentChunk;
                    final int fCurrentOffset3 = super.fCurrentOffset;
                    final int fCurrentIndex = this.fCurrentIndex;
                    final int loadNextChar = this.loadNextChar();
                    this.fCurrentChunk = fCurrentChunk;
                    super.fCurrentOffset = fCurrentOffset3;
                    this.fCurrentIndex = fCurrentIndex;
                    this.fMostRecentData = fCurrentChunk.toCharArray();
                    boolean b = true;
                    if (loadNextChar < 128) {
                        if (XMLCharacterProperties.fgAsciiInitialNameChar[loadNextChar] == 0 || loadNextChar == 58) {
                            b = false;
                        }
                    }
                    else {
                        if (!this.fCalledCharPropInit) {
                            XMLCharacterProperties.initCharFlags();
                            this.fCalledCharPropInit = true;
                        }
                        if ((XMLCharacterProperties.fgCharFlags[loadNextChar] & 0x2) == 0x0) {
                            b = false;
                        }
                    }
                    fMostRecentChar = ':';
                    if (!b) {
                        fCurrentOffset2 = -1;
                        this.fMostRecentChar = fMostRecentChar;
                        break;
                    }
                }
            }
            else {
                if (!this.fCalledCharPropInit) {
                    XMLCharacterProperties.initCharFlags();
                    this.fCalledCharPropInit = true;
                }
                if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x4) == 0x0) {
                    break;
                }
            }
            ++super.fCharacterCounter;
        }
        qName.rawname = this.fCurrentChunk.addSymbol(fCurrentOffset, super.fCurrentOffset - fCurrentOffset, StringHasher.finishHash(hashChar));
        qName.prefix = ((fCurrentOffset2 == -1) ? -1 : this.addSymbol(fCurrentOffset, fCurrentOffset2 - fCurrentOffset));
        qName.localpart = ((fCurrentOffset2 == -1) ? qName.rawname : this.addSymbol(fCurrentOffset2 + 1, super.fCurrentOffset - (fCurrentOffset2 + 1)));
        qName.uri = 0;
    }
    
    public int scanContent(final QName qName) throws Exception {
        if (this.fCallClearPreviousChunk && this.fCurrentChunk.clearPreviousChunk()) {
            this.fCallClearPreviousChunk = false;
        }
        final int fCurrentOffset = super.fCurrentOffset;
        int n = this.fMostRecentChar;
        if (n < 128) {
            switch (XMLCharacterProperties.fgAsciiWSCharData[n]) {
                case 0: {
                    ++super.fCharacterCounter;
                    this.loadNextChar();
                    break;
                }
                case 1: {
                    ++super.fCharacterCounter;
                    final int loadNextChar = this.loadNextChar();
                    if (!super.fInCDSect) {
                        return this.recognizeMarkup(loadNextChar);
                    }
                    break;
                }
                case 2: {
                    ++super.fCharacterCounter;
                    final int loadNextChar2 = this.loadNextChar();
                    if (!super.fInCDSect) {
                        return this.recognizeReference(loadNextChar2);
                    }
                    break;
                }
                case 3: {
                    ++super.fCharacterCounter;
                    if (this.loadNextChar() != 93) {
                        break;
                    }
                    final CharDataChunk fCurrentChunk = this.fCurrentChunk;
                    final int fCurrentIndex = this.fCurrentIndex;
                    final int fCurrentOffset2 = super.fCurrentOffset;
                    if (this.loadNextChar() != 62) {
                        this.fCurrentChunk = fCurrentChunk;
                        this.fCurrentIndex = fCurrentIndex;
                        super.fCurrentOffset = fCurrentOffset2;
                        this.fMostRecentData = fCurrentChunk.toCharArray();
                        this.fMostRecentChar = 93;
                        break;
                    }
                    this.loadNextChar();
                    super.fCharacterCounter += 2;
                    return 3;
                }
                case 4: {
                    if (n == 0 && this.atEOF(super.fCurrentOffset + 1)) {
                        this.changeReaders();
                        return 9;
                    }
                    return 9;
                }
                case 5: {
                    do {
                        if (n == 10) {
                            ++super.fLinefeedCounter;
                            super.fCharacterCounter = 1;
                        }
                        else {
                            ++super.fCharacterCounter;
                        }
                        n = this.loadNextChar();
                    } while (n == 32 || n == 9 || n == 10);
                    if (n < 128) {
                        switch (XMLCharacterProperties.fgAsciiCharData[n]) {
                            case 0: {
                                ++super.fCharacterCounter;
                                this.loadNextChar();
                                break;
                            }
                            case 1: {
                                if (!super.fInCDSect) {
                                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
                                    ++super.fCharacterCounter;
                                    return this.recognizeMarkup(this.loadNextChar());
                                }
                                ++super.fCharacterCounter;
                                this.loadNextChar();
                                break;
                            }
                            case 2: {
                                if (!super.fInCDSect) {
                                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
                                    ++super.fCharacterCounter;
                                    return this.recognizeReference(this.loadNextChar());
                                }
                                ++super.fCharacterCounter;
                                this.loadNextChar();
                                break;
                            }
                            case 3: {
                                final int fCurrentOffset3 = super.fCurrentOffset;
                                if (this.loadNextChar() != 93) {
                                    ++super.fCharacterCounter;
                                    break;
                                }
                                final CharDataChunk fCurrentChunk2 = this.fCurrentChunk;
                                final int fCurrentIndex2 = this.fCurrentIndex;
                                final int fCurrentOffset4 = super.fCurrentOffset;
                                if (this.loadNextChar() != 62) {
                                    this.fCurrentChunk = fCurrentChunk2;
                                    this.fCurrentIndex = fCurrentIndex2;
                                    super.fCurrentOffset = fCurrentOffset4;
                                    this.fMostRecentData = fCurrentChunk2.toCharArray();
                                    this.fMostRecentChar = 93;
                                    ++super.fCharacterCounter;
                                    break;
                                }
                                this.loadNextChar();
                                this.callCharDataHandler(fCurrentOffset, fCurrentOffset3, true);
                                super.fCharacterCounter += 3;
                                return 3;
                            }
                            case 4: {
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
                                if (n == 0 && this.atEOF(super.fCurrentOffset + 1)) {
                                    this.changeReaders();
                                    return 9;
                                }
                                return 9;
                            }
                        }
                        break;
                    }
                    if (!this.skipMultiByteCharData(n)) {
                        this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
                        return 9;
                    }
                    break;
                }
            }
        }
        else if (!this.skipMultiByteCharData(n)) {
            return 9;
        }
        int n2 = this.skipAsciiCharData();
        while (true) {
            if (n2 < 128) {
                switch (XMLCharacterProperties.fgAsciiCharData[n2]) {
                    case 0: {
                        ++super.fCharacterCounter;
                        n2 = this.loadNextChar();
                        continue;
                    }
                    case 1: {
                        if (!super.fInCDSect) {
                            this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
                            ++super.fCharacterCounter;
                            return this.recognizeMarkup(this.loadNextChar());
                        }
                        ++super.fCharacterCounter;
                        n2 = this.loadNextChar();
                        continue;
                    }
                    case 2: {
                        if (!super.fInCDSect) {
                            this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
                            ++super.fCharacterCounter;
                            return this.recognizeReference(this.loadNextChar());
                        }
                        ++super.fCharacterCounter;
                        n2 = this.loadNextChar();
                        continue;
                    }
                    case 3: {
                        final int fCurrentOffset5 = super.fCurrentOffset;
                        n2 = this.loadNextChar();
                        if (n2 != 93) {
                            ++super.fCharacterCounter;
                            continue;
                        }
                        final CharDataChunk fCurrentChunk3 = this.fCurrentChunk;
                        final int fCurrentIndex3 = this.fCurrentIndex;
                        final int fCurrentOffset6 = super.fCurrentOffset;
                        if (this.loadNextChar() != 62) {
                            this.fCurrentChunk = fCurrentChunk3;
                            this.fCurrentIndex = fCurrentIndex3;
                            super.fCurrentOffset = fCurrentOffset6;
                            this.fMostRecentData = fCurrentChunk3.toCharArray();
                            this.fMostRecentChar = 93;
                            ++super.fCharacterCounter;
                            continue;
                        }
                        this.loadNextChar();
                        this.callCharDataHandler(fCurrentOffset, fCurrentOffset5, false);
                        super.fCharacterCounter += 3;
                        return 3;
                    }
                    case 4: {
                        if (n2 == 10) {
                            ++super.fLinefeedCounter;
                            super.fCharacterCounter = 1;
                            n2 = this.loadNextChar();
                            continue;
                        }
                        this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
                        if (n2 == 0 && this.atEOF(super.fCurrentOffset + 1)) {
                            this.changeReaders();
                            return 9;
                        }
                        return 9;
                    }
                }
            }
            else {
                if (!this.skipMultiByteCharData(n2)) {
                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
                    return 9;
                }
                n2 = this.fMostRecentChar;
            }
        }
    }
    
    private int recognizeMarkup(int n) throws Exception {
        switch (n) {
            case 0: {
                return 11;
            }
            case 63: {
                ++super.fCharacterCounter;
                this.loadNextChar();
                return 0;
            }
            case 33: {
                ++super.fCharacterCounter;
                n = this.loadNextChar();
                if (n == 0) {
                    --super.fCharacterCounter;
                    --super.fCurrentOffset;
                    return 11;
                }
                if (n == 45) {
                    ++super.fCharacterCounter;
                    n = this.loadNextChar();
                    if (n == 0) {
                        super.fCharacterCounter -= 2;
                        super.fCurrentOffset -= 2;
                        return 11;
                    }
                    if (n == 45) {
                        ++super.fCharacterCounter;
                        this.loadNextChar();
                        return 1;
                    }
                }
                else if (n == 91) {
                    for (int i = 0; i < 6; ++i) {
                        ++super.fCharacterCounter;
                        n = this.loadNextChar();
                        if (n == 0) {
                            super.fCharacterCounter -= 2 + i;
                            super.fCurrentOffset -= 2 + i;
                            return 11;
                        }
                        if (n != StreamingCharReader.cdata_string[i]) {
                            return 10;
                        }
                    }
                    ++super.fCharacterCounter;
                    this.loadNextChar();
                    return 2;
                }
                return 10;
            }
            case 47: {
                ++super.fCharacterCounter;
                this.loadNextChar();
                return 4;
            }
            default: {
                return 6;
            }
        }
    }
    
    private int recognizeReference(final int n) throws Exception {
        if (n == 0) {
            return 12;
        }
        if (n == 35) {
            ++super.fCharacterCounter;
            this.loadNextChar();
            return 7;
        }
        return 8;
    }
    
    private boolean skipMultiByteCharData(int loadNextChar) throws Exception {
        if (loadNextChar < 55296) {
            this.loadNextChar();
            return true;
        }
        if (loadNextChar > 65533) {
            return false;
        }
        if (loadNextChar >= 56320 && loadNextChar < 57344) {
            return false;
        }
        if (loadNextChar >= 55296 && loadNextChar < 56320) {
            final CharDataChunk fCurrentChunk = this.fCurrentChunk;
            final int fCurrentIndex = this.fCurrentIndex;
            final int fCurrentOffset = super.fCurrentOffset;
            loadNextChar = this.loadNextChar();
            if (loadNextChar < 56320 || loadNextChar >= 57344) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toCharArray();
                this.fMostRecentChar = (this.fMostRecentData[fCurrentIndex] & '\uffff');
                return false;
            }
        }
        this.loadNextChar();
        return true;
    }
    
    private int skipAsciiCharData() throws Exception {
        int i;
        for (i = this.fMostRecentChar; i < 128; i = this.loadNextChar()) {
            if (XMLCharacterProperties.fgAsciiCharData[i] == 0) {
                ++super.fCharacterCounter;
            }
            else {
                if (i != 10) {
                    return i;
                }
                ++super.fLinefeedCounter;
                super.fCharacterCounter = 1;
            }
        }
        return i;
    }
    
    private void callCharDataHandler(final int n, final int n2, final boolean b) throws Exception {
        final int n3 = n2 - n;
        if (!super.fSendCharDataAsCharArray) {
            final int addString = this.addString(n, n3);
            if (b) {
                super.fCharDataHandler.processWhitespace(addString);
            }
            else {
                super.fCharDataHandler.processCharacters(addString);
            }
            return;
        }
        CharDataChunk charDataChunk = this.fCurrentChunk.chunkFor(n);
        final int n4 = n & 0x3FFF;
        if (n4 + n3 <= 16384) {
            if (n3 != 0) {
                if (b) {
                    super.fCharDataHandler.processWhitespace(charDataChunk.toCharArray(), n4, n3);
                }
                else {
                    super.fCharDataHandler.processCharacters(charDataChunk.toCharArray(), n4, n3);
                }
            }
            return;
        }
        final int n5 = n3;
        final int n6 = 16384 - n4;
        if (b) {
            super.fCharDataHandler.processWhitespace(charDataChunk.toCharArray(), n4, n6);
        }
        else {
            super.fCharDataHandler.processCharacters(charDataChunk.toCharArray(), n4, n6);
        }
        int i = n5 - n6;
        do {
            charDataChunk = charDataChunk.nextChunk();
            if (charDataChunk == null) {
                throw new RuntimeException(new ImplementationMessages().createMessage(null, 14, 0, null));
            }
            final int n7 = (i <= 16384) ? i : 16384;
            if (b) {
                super.fCharDataHandler.processWhitespace(charDataChunk.toCharArray(), 0, n7);
            }
            else {
                super.fCharDataHandler.processCharacters(charDataChunk.toCharArray(), 0, n7);
            }
            i -= n7;
        } while (i > 0);
    }
    
    private int slowLoadNextChar() throws Exception {
        this.fCallClearPreviousChunk = true;
        if (this.fCurrentChunk.nextChunk() != null) {
            this.fCurrentChunk = this.fCurrentChunk.nextChunk();
            this.fCurrentIndex = 0;
            this.fMostRecentData = this.fCurrentChunk.toCharArray();
            return this.fMostRecentChar = (this.fMostRecentData[this.fCurrentIndex] & '\uffff');
        }
        this.fCurrentChunk = CharDataChunk.createChunk(this.fStringPool, this.fCurrentChunk);
        this.fCurrentIndex = 0;
        this.fFillIndex = 0;
        this.loadFirstChar();
        return this.fMostRecentChar;
    }
    
    private int loadNextChar() throws Exception {
        ++super.fCurrentOffset;
        if (++this.fCurrentIndex == 16384) {
            return this.slowLoadNextChar();
        }
        if (this.fCurrentIndex < this.fFillIndex) {
            return this.fMostRecentChar = (this.fMostRecentData[this.fCurrentIndex] & '\uffff');
        }
        return this.loadMoreChars();
    }
    
    private void loadFirstChar() throws Exception {
        this.fMostRecentData = this.fCurrentChunk.toCharArray();
        if (this.fMostRecentData == null) {
            this.fMostRecentData = new char[16384];
            this.fCurrentChunk.setCharArray(this.fMostRecentData);
        }
        this.loadMoreChars();
    }
    
    private int loadMoreChars() throws Exception {
        if (this.oweChar != -1) {
            this.fMostRecentData[this.fFillIndex] = (char)this.oweChar;
            ++this.fFillIndex;
            ++this.fLength;
            this.fMostRecentChar = this.oweChar;
            this.oweChar = -1;
            return this.fMostRecentChar;
        }
        try {
            Label_0345: {
                int n = 0;
            Label_0146:
                while (true) {
                    switch (this.fCharacterStream.read(this.inBuffer, 0, 2)) {
                        case 0: {
                            continue;
                        }
                        case 1: {
                            n = this.inBuffer[0];
                            if (!this.seenCR) {
                                break Label_0146;
                            }
                            this.seenCR = false;
                            if (n == 10) {
                                continue;
                            }
                            break Label_0146;
                        }
                        case 2: {
                            int n2 = this.inBuffer[0];
                            boolean b = false;
                            if (this.seenCR) {
                                this.seenCR = false;
                                if (n2 == 10) {
                                    n2 = this.inBuffer[1];
                                    b = true;
                                }
                            }
                            if (n2 == 13) {
                                this.seenCR = true;
                                n2 = 10;
                            }
                            final char[] fMostRecentData = this.fMostRecentData;
                            final int fFillIndex = this.fFillIndex;
                            final char fMostRecentChar = (char)n2;
                            fMostRecentData[fFillIndex] = fMostRecentChar;
                            this.fMostRecentChar = fMostRecentChar;
                            ++this.fFillIndex;
                            ++this.fLength;
                            if (!b) {
                                int oweChar = this.inBuffer[1];
                                if (this.seenCR) {
                                    this.seenCR = false;
                                    if (oweChar == 10) {
                                        return this.fMostRecentChar;
                                    }
                                }
                                if (oweChar == 13) {
                                    this.seenCR = true;
                                    oweChar = 10;
                                }
                                this.oweChar = oweChar;
                            }
                            return this.fMostRecentChar;
                        }
                        default: {}
                        case -1: {
                            break Label_0345;
                        }
                    }
                }
                if (n == 13) {
                    this.seenCR = true;
                    n = 10;
                }
                final char[] fMostRecentData2 = this.fMostRecentData;
                final int fFillIndex2 = this.fFillIndex;
                final char fMostRecentChar2 = (char)n;
                fMostRecentData2[fFillIndex2] = fMostRecentChar2;
                this.fMostRecentChar = fMostRecentChar2;
                ++this.fFillIndex;
                ++this.fLength;
                return this.fMostRecentChar;
            }
        }
        catch (IOException ex) {}
        try {
            this.fCharacterStream.close();
        }
        catch (IOException ex2) {}
        this.fCharacterStream = null;
        final char[] fMostRecentData3 = this.fMostRecentData;
        final int fFillIndex3 = this.fFillIndex;
        final char fMostRecentChar3 = '\0';
        fMostRecentData3[fFillIndex3] = fMostRecentChar3;
        this.fMostRecentChar = fMostRecentChar3;
        return 0;
    }
    
    private boolean atEOF(final int n) {
        return n > this.fLength;
    }
    
    static {
        cdata_string = new char[] { 'C', 'D', 'A', 'T', 'A', '[' };
    }
    
    private class DeferredError
    {
        int errorCode;
        Object[] args;
        int offset;
        
        DeferredError(final int errorCode, final Object[] args, final int offset) {
            this.errorCode = errorCode;
            this.args = args;
            this.offset = offset;
        }
    }
}
