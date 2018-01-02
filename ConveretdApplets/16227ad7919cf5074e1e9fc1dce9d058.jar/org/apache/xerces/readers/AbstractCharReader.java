// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.util.Locale;
import org.apache.xerces.utils.ImplementationMessages;
import org.apache.xerces.utils.StringHasher;
import org.apache.xerces.utils.XMLCharacterProperties;
import org.apache.xerces.framework.XMLErrorReporter;
import java.util.Vector;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.utils.CharDataChunk;

abstract class AbstractCharReader extends XMLEntityReader
{
    protected CharDataChunk fCurrentChunk;
    protected int fCurrentIndex;
    protected char[] fMostRecentData;
    protected int fMostRecentChar;
    protected int fLength;
    private static final char[] cdata_string;
    private StringPool fStringPool;
    private boolean fCallClearPreviousChunk;
    private Vector fDeferredErrors;
    
    protected AbstractCharReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final StringPool fStringPool) {
        super(xmlEntityHandler, xmlErrorReporter, b);
        this.fCallClearPreviousChunk = true;
        this.fStringPool = fStringPool;
        this.fCurrentChunk = CharDataChunk.createChunk(this.fStringPool, null);
    }
    
    protected abstract int fillCurrentChunk() throws Exception;
    
    protected void deferException(final int n, final Object[] array, final int n2) {
        if (this.fDeferredErrors == null) {
            this.fDeferredErrors = new Vector();
        }
        this.fDeferredErrors.addElement(new DeferredError(n, array, n2));
    }
    
    protected EntityReader changeReaders() throws Exception {
        final EntityReader changeReaders = super.changeReaders();
        this.fCurrentChunk.releaseChunk();
        this.fCurrentChunk = null;
        return changeReaders;
    }
    
    public void append(final CharBuffer charBuffer, final int n, final int n2) {
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
            ++super.fCurrentOffset;
            if (++this.fCurrentIndex == 16384) {
                this.slowLoadNextChar();
            }
            else {
                this.fMostRecentChar = (this.fMostRecentData[this.fCurrentIndex] & '\uffff');
            }
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
        ++super.fCurrentOffset;
        if (++this.fCurrentIndex == 16384) {
            this.slowLoadNextChar();
        }
        else {
            this.fMostRecentChar = (this.fMostRecentData[this.fCurrentIndex] & '\uffff');
        }
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
        else if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x2) == 0x0) {
            return;
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
            else if ((XMLCharacterProperties.fgCharFlags[n] & 0x4) == 0x0) {
                return;
            }
            ++super.fCharacterCounter;
        }
    }
    
    public boolean skippedString(final char[] array) throws Exception {
        final int length = array.length;
        final char[] fMostRecentData = this.fMostRecentData;
        int i = this.fCurrentIndex;
        if (i + length <= 16384) {
            for (int j = 0; j < length; ++j) {
                if (fMostRecentData[i++] != array[j]) {
                    return false;
                }
            }
            super.fCharacterCounter += length;
            super.fCurrentOffset += length;
            if ((this.fCurrentIndex = i) == 16384) {
                this.slowLoadNextChar();
            }
            else {
                this.fMostRecentChar = (fMostRecentData[i] & '\uffff');
            }
            return true;
        }
        final CharDataChunk fCurrentChunk = this.fCurrentChunk;
        final int fCurrentOffset = super.fCurrentOffset;
        final int fCurrentIndex = i;
        int k = 0;
        while (i < 16384) {
            if (fMostRecentData[i++] != array[k++]) {
                return false;
            }
        }
        this.slowLoadNextChar();
        final char[] fMostRecentData2 = this.fMostRecentData;
        int fCurrentIndex2 = 0;
        while (k < length) {
            if (fMostRecentData2[fCurrentIndex2++] != array[k++]) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toCharArray();
                this.fMostRecentChar = (this.fMostRecentData[fCurrentIndex] & '\uffff');
                return false;
            }
        }
        super.fCharacterCounter += length;
        super.fCurrentOffset += length;
        if ((this.fCurrentIndex = fCurrentIndex2) == 16384) {
            this.slowLoadNextChar();
        }
        else {
            this.fMostRecentChar = (fMostRecentData2[fCurrentIndex2] & '\uffff');
        }
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
            if (!this.lookingAtChar(' ', true)) {
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
                continue;
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
        int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar < 128) {
            if (XMLCharacterProperties.fgAsciiInitialNameChar[fMostRecentChar] == 0) {
                return -1;
            }
        }
        else if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x2) == 0x0) {
            return -1;
        }
        final int fCurrentOffset = super.fCurrentOffset;
        int fCurrentIndex = this.fCurrentIndex;
        char[] array = this.fMostRecentData;
        if (++fCurrentIndex == 16384) {
            this.slowLoadNextChar();
            fCurrentIndex = 0;
            array = this.fMostRecentData;
        }
        ++super.fCharacterCounter;
        ++super.fCurrentOffset;
        int hashChar = 0;
        while (true) {
            hashChar = StringHasher.hashChar(hashChar, fMostRecentChar);
            fMostRecentChar = (array[fCurrentIndex] & '\uffff');
            if (c == fMostRecentChar) {
                break;
            }
            if (fMostRecentChar < '\u0080') {
                if (XMLCharacterProperties.fgAsciiNameChar[fMostRecentChar] == 0) {
                    break;
                }
            }
            else if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x4) == 0x0) {
                break;
            }
            if (++fCurrentIndex == 16384) {
                this.slowLoadNextChar();
                fCurrentIndex = 0;
                array = this.fMostRecentData;
            }
            ++super.fCharacterCounter;
            ++super.fCurrentOffset;
        }
        this.fCurrentIndex = fCurrentIndex;
        this.fMostRecentChar = fMostRecentChar;
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
            ++super.fCurrentOffset;
            if (++this.fCurrentIndex == 16384) {
                n = this.slowLoadNextChar();
            }
            else {
                final char fMostRecentChar = (char)(this.fMostRecentData[this.fCurrentIndex] & '\uffff');
                this.fMostRecentChar = fMostRecentChar;
                n = fMostRecentChar;
            }
        }
        if (n == c) {
            return true;
        }
        if (n < '\u0080') {
            if (XMLCharacterProperties.fgAsciiNameChar[n] == 0) {
                return true;
            }
        }
        else if ((XMLCharacterProperties.fgCharFlags[n] & 0x4) == 0x0) {
            return true;
        }
        this.skipPastNmtoken(c);
        return false;
    }
    
    public int scanQName(final char c) throws Exception {
        int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar < 128) {
            if (XMLCharacterProperties.fgAsciiInitialNameChar[fMostRecentChar] == 0) {
                return -1;
            }
            if (fMostRecentChar == 58) {
                return -1;
            }
        }
        else if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x2) == 0x0) {
            return -1;
        }
        final int fCurrentOffset = super.fCurrentOffset;
        int fCurrentIndex = this.fCurrentIndex;
        char[] array = this.fMostRecentData;
        if (++fCurrentIndex == 16384) {
            this.slowLoadNextChar();
            fCurrentIndex = 0;
            array = this.fMostRecentData;
        }
        ++super.fCharacterCounter;
        ++super.fCurrentOffset;
        int hashChar = 0;
        int fCurrentOffset2 = -1;
        while (true) {
            hashChar = StringHasher.hashChar(hashChar, fMostRecentChar);
            fMostRecentChar = (array[fCurrentIndex] & '\uffff');
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
                    int slowLoadNextChar;
                    if (fCurrentIndex + 1 == 16384) {
                        final CharDataChunk fCurrentChunk = this.fCurrentChunk;
                        final int fCurrentOffset3 = super.fCurrentOffset;
                        slowLoadNextChar = this.slowLoadNextChar();
                        this.fCurrentChunk = fCurrentChunk;
                        super.fCurrentOffset = fCurrentOffset3;
                        this.fMostRecentData = fCurrentChunk.toCharArray();
                    }
                    else {
                        slowLoadNextChar = (array[fCurrentIndex + 1] & '\uffff');
                    }
                    boolean b = true;
                    if (slowLoadNextChar < 128) {
                        if (XMLCharacterProperties.fgAsciiInitialNameChar[slowLoadNextChar] == 0 || slowLoadNextChar == 58) {
                            b = false;
                        }
                    }
                    else if ((XMLCharacterProperties.fgCharFlags[slowLoadNextChar] & 0x2) == 0x0) {
                        b = false;
                    }
                    fMostRecentChar = ':';
                    if (!b) {
                        fCurrentOffset2 = -1;
                        break;
                    }
                }
            }
            else if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x4) == 0x0) {
                break;
            }
            if (++fCurrentIndex == 16384) {
                this.slowLoadNextChar();
                fCurrentIndex = 0;
                array = this.fMostRecentData;
            }
            ++super.fCharacterCounter;
            ++super.fCurrentOffset;
        }
        this.fCurrentIndex = fCurrentIndex;
        this.fMostRecentChar = fMostRecentChar;
        final int addSymbol = this.fCurrentChunk.addSymbol(fCurrentOffset, super.fCurrentOffset - fCurrentOffset, StringHasher.finishHash(hashChar));
        return this.fStringPool.addQName(addSymbol, (fCurrentOffset2 == -1) ? -1 : this.addSymbol(fCurrentOffset, fCurrentOffset2 - fCurrentOffset), (fCurrentOffset2 == -1) ? addSymbol : this.addSymbol(fCurrentOffset2 + 1, super.fCurrentOffset - (fCurrentOffset2 + 1)));
    }
    
    public int scanContent(final int n) throws Exception {
        if (this.fCallClearPreviousChunk && this.fCurrentChunk.clearPreviousChunk()) {
            this.fCallClearPreviousChunk = false;
        }
        final int fCurrentOffset = super.fCurrentOffset;
        int n2 = this.fMostRecentChar;
        Label_0832: {
            if (n2 < 128) {
                switch (XMLCharacterProperties.fgAsciiWSCharData[n2]) {
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
                        if (this.loadNextChar() == 93) {
                            if (this.fCurrentIndex + 1 == 16384) {
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
                            }
                            else {
                                if (this.fMostRecentData[this.fCurrentIndex + 1] != '>') {
                                    break;
                                }
                                ++this.fCurrentIndex;
                                ++super.fCurrentOffset;
                            }
                            this.loadNextChar();
                            super.fCharacterCounter += 2;
                            return 3;
                        }
                        break;
                    }
                    case 4: {
                        if (n2 == 0 && this.atEOF(super.fCurrentOffset + 1)) {
                            this.changeReaders();
                            return 9;
                        }
                        return 9;
                    }
                    case 5: {
                        do {
                            if (n2 == 10) {
                                ++super.fLinefeedCounter;
                                super.fCharacterCounter = 1;
                            }
                            else {
                                ++super.fCharacterCounter;
                            }
                            n2 = this.loadNextChar();
                        } while (n2 == 32 || n2 == 9 || n2 == 10);
                        if (n2 < 128) {
                            switch (XMLCharacterProperties.fgAsciiCharData[n2]) {
                                case 0: {
                                    ++super.fCharacterCounter;
                                    this.loadNextChar();
                                    break Label_0832;
                                }
                                case 1: {
                                    if (!super.fInCDSect) {
                                        this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
                                        ++super.fCharacterCounter;
                                        return this.recognizeMarkup(this.loadNextChar());
                                    }
                                    ++super.fCharacterCounter;
                                    this.loadNextChar();
                                    break Label_0832;
                                }
                                case 2: {
                                    if (!super.fInCDSect) {
                                        this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
                                        ++super.fCharacterCounter;
                                        return this.recognizeReference(this.loadNextChar());
                                    }
                                    ++super.fCharacterCounter;
                                    this.loadNextChar();
                                    break Label_0832;
                                }
                                case 3: {
                                    final int fCurrentOffset3 = super.fCurrentOffset;
                                    if (this.loadNextChar() != 93) {
                                        ++super.fCharacterCounter;
                                        break Label_0832;
                                    }
                                    if (this.fCurrentIndex + 1 == 16384) {
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
                                            break Label_0832;
                                        }
                                    }
                                    else {
                                        if (this.fMostRecentData[this.fCurrentIndex + 1] != '>') {
                                            ++super.fCharacterCounter;
                                            break Label_0832;
                                        }
                                        ++this.fCurrentIndex;
                                        ++super.fCurrentOffset;
                                    }
                                    this.loadNextChar();
                                    this.callCharDataHandler(fCurrentOffset, fCurrentOffset3, true);
                                    super.fCharacterCounter += 3;
                                    return 3;
                                }
                                case 4: {
                                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
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
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
                                return 9;
                            }
                            break;
                        }
                        break;
                    }
                }
            }
            else if (!this.skipMultiByteCharData(n2)) {
                return 9;
            }
        }
        int n3 = this.skipAsciiCharData();
        Block_37: {
            Label_1232: {
                int fCurrentOffset5 = 0;
            Label_1178:
                while (true) {
                    if (n3 < 128) {
                        switch (XMLCharacterProperties.fgAsciiCharData[n3]) {
                            case 0: {
                                ++super.fCharacterCounter;
                                n3 = this.loadNextChar();
                                continue;
                            }
                            case 1: {
                                if (!super.fInCDSect) {
                                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
                                    ++super.fCharacterCounter;
                                    return this.recognizeMarkup(this.loadNextChar());
                                }
                                ++super.fCharacterCounter;
                                n3 = this.loadNextChar();
                                continue;
                            }
                            case 2: {
                                if (!super.fInCDSect) {
                                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
                                    ++super.fCharacterCounter;
                                    return this.recognizeReference(this.loadNextChar());
                                }
                                ++super.fCharacterCounter;
                                n3 = this.loadNextChar();
                                continue;
                            }
                            case 3: {
                                fCurrentOffset5 = super.fCurrentOffset;
                                n3 = this.loadNextChar();
                                if (n3 != 93) {
                                    ++super.fCharacterCounter;
                                    continue;
                                }
                                if (this.fCurrentIndex + 1 == 16384) {
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
                                    break Label_1178;
                                }
                                else {
                                    if (this.fMostRecentData[this.fCurrentIndex + 1] != '>') {
                                        ++super.fCharacterCounter;
                                        continue;
                                    }
                                    ++this.fCurrentIndex;
                                    ++super.fCurrentOffset;
                                    break Label_1178;
                                }
                                break;
                            }
                            case 4: {
                                if (n3 == 10) {
                                    ++super.fLinefeedCounter;
                                    super.fCharacterCounter = 1;
                                    n3 = this.loadNextChar();
                                    continue;
                                }
                                break Label_1232;
                            }
                        }
                    }
                    else {
                        if (!this.skipMultiByteCharData(n3)) {
                            break Block_37;
                        }
                        n3 = this.fMostRecentChar;
                    }
                }
                this.loadNextChar();
                this.callCharDataHandler(fCurrentOffset, fCurrentOffset5, false);
                super.fCharacterCounter += 3;
                return 3;
            }
            this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
            if (n3 == 0 && this.atEOF(super.fCurrentOffset + 1)) {
                this.changeReaders();
                return 9;
            }
            return 9;
        }
        this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
        return 9;
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
                        if (n != AbstractCharReader.cdata_string[i]) {
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
        int i = this.fCurrentIndex;
        int n = super.fCurrentOffset - i;
        while (true) {
            final char[] fMostRecentData = this.fMostRecentData;
            while (i < 16384) {
                final char c = (char)(fMostRecentData[i] & '\uffff');
                if (c >= '\u0080') {
                    super.fCurrentOffset = n + i;
                    this.fCurrentIndex = i;
                    return this.fMostRecentChar = c;
                }
                if (XMLCharacterProperties.fgAsciiCharData[c] == 0) {
                    ++super.fCharacterCounter;
                }
                else {
                    if (c != '\n') {
                        super.fCurrentOffset = n + i;
                        this.fCurrentIndex = i;
                        return this.fMostRecentChar = c;
                    }
                    ++super.fLinefeedCounter;
                    super.fCharacterCounter = 1;
                }
                ++i;
            }
            n += i;
            this.slowLoadNextChar();
            i = 0;
        }
    }
    
    private void callCharDataHandler(final int n, final int n2, final boolean b) throws Exception {
        final int n3 = n2 - n;
        if (!super.fSendCharDataAsCharArray) {
            final int addString = this.addString(n, n3);
            if (b) {
                super.fEntityHandler.processWhitespace(addString);
                return;
            }
            super.fEntityHandler.processCharacters(addString);
        }
        else {
            CharDataChunk charDataChunk = this.fCurrentChunk.chunkFor(n);
            final int n4 = n & 0x3FFF;
            if (n4 + n3 <= 16384) {
                if (n3 != 0) {
                    if (b) {
                        super.fEntityHandler.processWhitespace(charDataChunk.toCharArray(), n4, n3);
                        return;
                    }
                    super.fEntityHandler.processCharacters(charDataChunk.toCharArray(), n4, n3);
                }
                return;
            }
            final int n5 = n3;
            final int n6 = 16384 - n4;
            if (b) {
                super.fEntityHandler.processWhitespace(charDataChunk.toCharArray(), n4, n6);
            }
            else {
                super.fEntityHandler.processCharacters(charDataChunk.toCharArray(), n4, n6);
            }
            int i = n5 - n6;
            do {
                charDataChunk = charDataChunk.nextChunk();
                if (charDataChunk == null) {
                    throw new RuntimeException(new ImplementationMessages().createMessage(null, 14, 0, null));
                }
                final int n7 = (i <= 16384) ? i : 16384;
                if (b) {
                    super.fEntityHandler.processWhitespace(charDataChunk.toCharArray(), 0, n7);
                }
                else {
                    super.fEntityHandler.processCharacters(charDataChunk.toCharArray(), 0, n7);
                }
                i -= n7;
            } while (i > 0);
        }
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
        return this.fillCurrentChunk();
    }
    
    private int loadNextChar() throws Exception {
        ++super.fCurrentOffset;
        if (++this.fCurrentIndex == 16384) {
            return this.slowLoadNextChar();
        }
        return this.fMostRecentChar = (this.fMostRecentData[this.fCurrentIndex] & '\uffff');
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
