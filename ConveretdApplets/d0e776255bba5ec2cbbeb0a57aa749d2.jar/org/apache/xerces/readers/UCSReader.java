// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import org.apache.xerces.utils.QName;
import org.apache.xerces.utils.XMLCharacterProperties;
import org.apache.xerces.utils.StringHasher;
import java.io.IOException;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.ChunkyCharArray;
import org.apache.xerces.utils.ChunkyByteArray;
import org.apache.xerces.utils.StringPool;

final class UCSReader extends XMLEntityReader implements StringPool.StringProducer
{
    private static final boolean DEBUG_UTF16_BIG = false;
    static final int E_UCS4B = 0;
    static final int E_UCS4L = 1;
    static final int E_UCS2B = 2;
    static final int E_UCS2L = 3;
    static final int E_UCS2B_NOBOM = 4;
    static final int E_UCS2L_NOBOM = 5;
    private ChunkyByteArray fData;
    private int fEncoding;
    private StringPool fStringPool;
    private int fBytesPerChar;
    private boolean fBigEndian;
    private ChunkyCharArray fStringCharArray;
    private boolean fCalledCharPropInit;
    private static char[] fCharacters;
    private int fCharDataLength;
    private static final char[] cdata_string;
    
    UCSReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final ChunkyByteArray fData, final int fEncoding, final StringPool fStringPool) throws Exception {
        super(xmlEntityHandler, xmlErrorReporter, b);
        this.fData = null;
        this.fEncoding = -1;
        this.fStringPool = null;
        this.fBytesPerChar = -1;
        this.fBigEndian = true;
        this.fStringCharArray = null;
        this.fCalledCharPropInit = false;
        this.fCharDataLength = 0;
        super.fCurrentOffset = ((fEncoding == 2 || fEncoding == 3) ? 2 : 0);
        this.fData = fData;
        this.fEncoding = fEncoding;
        this.fStringPool = fStringPool;
        this.fBytesPerChar = ((this.fEncoding == 0 || this.fEncoding == 1) ? 4 : 2);
        this.fBigEndian = (this.fEncoding == 0 || this.fEncoding == 2 || this.fEncoding == 4);
    }
    
    private int getChar(int n) throws IOException {
        final int n2 = this.fData.byteAt(n++) & 0xFF;
        if (n2 == 255 && this.fData.atEOF(n)) {
            return -1;
        }
        final int n3 = this.fData.byteAt(n++) & 0xFF;
        if (this.fBytesPerChar == 4) {
            final int n4 = this.fData.byteAt(n++) & 0xFF;
            final int n5 = this.fData.byteAt(n++) & 0xFF;
            if (this.fBigEndian) {
                return (n2 << 24) + (n3 << 16) + (n4 << 8) + n5;
            }
            return (n5 << 24) + (n4 << 16) + (n3 << 8) + n2;
        }
        else {
            if (this.fBigEndian) {
                return (n2 << 8) + n3;
            }
            return (n3 << 8) + n2;
        }
    }
    
    public int addString(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
        return this.fStringPool.addString(this, n, n2);
    }
    
    public int addSymbol(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
        return this.fStringPool.addSymbol(this, n, n2, this.getHashcode(n, n2));
    }
    
    public void append(final XMLEntityHandler.CharBuffer charBuffer, int i, final int n) {
        while (i < i + n) {
            int char1;
            try {
                char1 = this.getChar(i);
            }
            catch (IOException ex) {
                char1 = 0;
            }
            charBuffer.append((char)char1);
            i += this.fBytesPerChar;
        }
    }
    
    public void releaseString(final int n, final int n2) {
    }
    
    public String toString(final int n, final int n2) {
        if (this.fStringCharArray == null) {
            this.fStringCharArray = new ChunkyCharArray(this.fStringPool);
        }
        final int length = this.fStringCharArray.length();
        this.append(this.fStringCharArray, n, n2);
        return this.fStringPool.toString(this.fStringCharArray.addString(length, this.fStringCharArray.length() - length));
    }
    
    private int getHashcode(int i, final int n) {
        final int n2 = i + n;
        int hashChar = 0;
        while (i < n2) {
            int char1;
            try {
                char1 = this.getChar(i);
            }
            catch (IOException ex) {
                char1 = 0;
            }
            hashChar = StringHasher.hashChar(hashChar, char1);
            i += this.fBytesPerChar;
        }
        return StringHasher.finishHash(hashChar);
    }
    
    public boolean equalsString(int i, final int n, final char[] array, int n2, final int n3) {
        final int n4 = i + n;
        int n5 = n3;
        while (i < n4) {
            if (n5-- == 0) {
                return false;
            }
            int char1;
            try {
                char1 = this.getChar(i);
            }
            catch (IOException ex) {
                char1 = 0;
            }
            if (char1 != array[n2++]) {
                return false;
            }
            i += this.fBytesPerChar;
        }
        return n5 == 0;
    }
    
    private void appendCharData(final int n) {
        if (UCSReader.fCharacters.length == this.fCharDataLength) {
            final char[] fCharacters = new char[UCSReader.fCharacters.length * 2];
            System.arraycopy(UCSReader.fCharacters, 0, fCharacters, 0, UCSReader.fCharacters.length);
            UCSReader.fCharacters = fCharacters;
        }
        UCSReader.fCharacters[this.fCharDataLength++] = (char)n;
    }
    
    public void callCharDataHandler(int i, final int n, final boolean b) throws Exception {
        final int n2 = i + n;
        int n3 = 0;
        while (i < n2) {
            int char1 = this.getChar(i);
            if (n3 != 0) {
                n3 = 0;
                if (char1 == 10) {
                    i += this.fBytesPerChar;
                    continue;
                }
            }
            if (char1 == 13) {
                n3 = 1;
                char1 = 10;
            }
            this.appendCharData(char1);
            i += this.fBytesPerChar;
        }
        if (super.fSendCharDataAsCharArray) {
            if (b) {
                super.fCharDataHandler.processWhitespace(UCSReader.fCharacters, 0, this.fCharDataLength);
            }
            else {
                super.fCharDataHandler.processCharacters(UCSReader.fCharacters, 0, this.fCharDataLength);
            }
        }
        else {
            final int addString = this.fStringPool.addString(new String(UCSReader.fCharacters, 0, this.fCharDataLength));
            if (b) {
                super.fCharDataHandler.processWhitespace(addString);
            }
            else {
                super.fCharDataHandler.processCharacters(addString);
            }
        }
        this.fCharDataLength = 0;
    }
    
    public boolean lookingAtChar(final char c, final boolean b) throws Exception {
        if (this.getChar(super.fCurrentOffset) == c) {
            if (b) {
                ++super.fCharacterCounter;
                super.fCurrentOffset += this.fBytesPerChar;
            }
            return true;
        }
        return false;
    }
    
    public boolean lookingAtValidChar(final boolean b) throws Exception {
        final int char1 = this.getChar(super.fCurrentOffset);
        if (char1 < 32) {
            if (char1 == 9) {
                if (!b) {
                    return true;
                }
                ++super.fCharacterCounter;
            }
            else if (char1 == 10) {
                if (!b) {
                    return true;
                }
                ++super.fLinefeedCounter;
                super.fCharacterCounter = 1;
            }
            else {
                if (char1 != 13) {
                    return char1 == -1 && this.changeReaders().lookingAtValidChar(b);
                }
                if (!b) {
                    return true;
                }
                ++super.fCarriageReturnCounter;
                super.fCharacterCounter = 1;
            }
            super.fCurrentOffset += this.fBytesPerChar;
            return true;
        }
        if (char1 <= 55295) {
            if (b) {
                ++super.fCharacterCounter;
                super.fCurrentOffset += this.fBytesPerChar;
            }
            return true;
        }
        if (char1 <= 57343) {
            if (b) {
                ++super.fCharacterCounter;
                super.fCurrentOffset += this.fBytesPerChar;
            }
            return true;
        }
        if (char1 <= 65533) {
            if (b) {
                ++super.fCharacterCounter;
                super.fCurrentOffset += this.fBytesPerChar;
            }
            return true;
        }
        return false;
    }
    
    public boolean lookingAtSpace(final boolean b) throws Exception {
        final int char1 = this.getChar(super.fCurrentOffset);
        if (char1 > 32) {
            return false;
        }
        if (char1 == 32 || char1 == 9) {
            if (!b) {
                return true;
            }
            ++super.fCharacterCounter;
        }
        else if (char1 == 10) {
            if (!b) {
                return true;
            }
            ++super.fLinefeedCounter;
            super.fCharacterCounter = 1;
        }
        else {
            if (char1 != 13) {
                return char1 == -1 && this.changeReaders().lookingAtSpace(b);
            }
            if (!b) {
                return true;
            }
            ++super.fCarriageReturnCounter;
            super.fCharacterCounter = 1;
        }
        super.fCurrentOffset += this.fBytesPerChar;
        return true;
    }
    
    public void skipToChar(final char c) throws Exception {
        while (true) {
            final int char1 = this.getChar(super.fCurrentOffset);
            if (char1 == c) {
                return;
            }
            if (char1 == -1) {
                this.changeReaders().skipToChar(c);
                return;
            }
            if (char1 == '\n') {
                ++super.fLinefeedCounter;
                super.fCharacterCounter = 1;
            }
            else if (char1 == '\r') {
                ++super.fCarriageReturnCounter;
                super.fCharacterCounter = 1;
            }
            else if (char1 >= '\ud800' && char1 < '\udc00') {
                ++super.fCharacterCounter;
                super.fCurrentOffset += this.fBytesPerChar;
                final int char2 = this.getChar(super.fCurrentOffset);
                if (char2 < 56320) {
                    continue;
                }
                if (char2 >= 57344) {
                    continue;
                }
            }
            else {
                ++super.fCharacterCounter;
            }
            super.fCurrentOffset += this.fBytesPerChar;
        }
    }
    
    public void skipPastSpaces() throws Exception {
        while (true) {
            final int char1 = this.getChar(super.fCurrentOffset);
            if (char1 > 32) {
                return;
            }
            if (char1 == 32 || char1 == 9) {
                ++super.fCharacterCounter;
            }
            else if (char1 == 10) {
                ++super.fLinefeedCounter;
                super.fCharacterCounter = 1;
            }
            else {
                if (char1 != 13) {
                    if (char1 == -1) {
                        this.changeReaders().skipPastSpaces();
                    }
                    return;
                }
                ++super.fCarriageReturnCounter;
                super.fCharacterCounter = 1;
            }
            super.fCurrentOffset += this.fBytesPerChar;
        }
    }
    
    public void skipPastName(final char c) throws Exception {
        final int char1 = this.getChar(super.fCurrentOffset);
        if (!this.fCalledCharPropInit) {
            XMLCharacterProperties.initCharFlags();
            this.fCalledCharPropInit = true;
        }
        if ((XMLCharacterProperties.fgCharFlags[char1] & 0x2) == 0x0) {
            return;
        }
        int char2;
        do {
            super.fCurrentOffset += this.fBytesPerChar;
            ++super.fCharacterCounter;
            char2 = this.getChar(super.fCurrentOffset);
            if (c == char2) {
                return;
            }
        } while ((XMLCharacterProperties.fgCharFlags[char2] & 0x4) != 0x0);
    }
    
    public void skipPastNmtoken(final char c) throws Exception {
        int n = this.getChar(super.fCurrentOffset);
        if (!this.fCalledCharPropInit) {
            XMLCharacterProperties.initCharFlags();
            this.fCalledCharPropInit = true;
        }
        while (c != n) {
            if ((XMLCharacterProperties.fgCharFlags[n] & 0x4) == 0x0) {
                return;
            }
            super.fCurrentOffset += this.fBytesPerChar;
            ++super.fCharacterCounter;
            n = this.getChar(super.fCurrentOffset);
        }
    }
    
    public boolean skippedString(final char[] array) throws Exception {
        int fCurrentOffset = super.fCurrentOffset;
        for (int i = 0; i < array.length; ++i) {
            if (this.getChar(fCurrentOffset) != array[i]) {
                return false;
            }
            fCurrentOffset += this.fBytesPerChar;
        }
        super.fCurrentOffset = fCurrentOffset;
        super.fCharacterCounter += array.length;
        return true;
    }
    
    public int scanInvalidChar() throws Exception {
        int char1 = this.getChar(super.fCurrentOffset);
        if (char1 == -1) {
            return this.changeReaders().scanInvalidChar();
        }
        super.fCurrentOffset += this.fBytesPerChar;
        if (char1 == 10) {
            ++super.fLinefeedCounter;
            super.fCharacterCounter = 1;
        }
        else if (char1 == 13) {
            ++super.fCarriageReturnCounter;
            super.fCharacterCounter = 1;
        }
        else {
            ++super.fCharacterCounter;
            if (char1 >= 55296 && char1 < 56320) {
                final int char2 = this.getChar(super.fCurrentOffset);
                if (char2 >= 56320 && char2 < 57344) {
                    char1 = (char1 - 55296 << 10) + (char2 - 56320) + 65536;
                    super.fCurrentOffset += this.fBytesPerChar;
                }
            }
        }
        return char1;
    }
    
    public int scanCharRef(final boolean b) throws Exception {
        final int char1 = this.getChar(super.fCurrentOffset);
        if (char1 == -1) {
            return this.changeReaders().scanCharRef(b);
        }
        int n;
        if (b) {
            if (char1 > 102 || XMLCharacterProperties.fgAsciiXDigitChar[char1] == 0) {
                return -2;
            }
            final int n2;
            n = n2 - (((n2 = char1) < 65) ? 48 : (((char1 < 97) ? 65 : 97) - 10));
        }
        else {
            if (char1 < 48 || char1 > 57) {
                return -2;
            }
            n = char1 - 48;
        }
        ++super.fCharacterCounter;
        super.fCurrentOffset += this.fBytesPerChar;
        boolean b2 = false;
        int char2;
        while (true) {
            char2 = this.getChar(super.fCurrentOffset);
            if (char2 == -1) {
                break;
            }
            if (b) {
                if (char2 > 102) {
                    break;
                }
                if (XMLCharacterProperties.fgAsciiXDigitChar[char2] == 0) {
                    break;
                }
            }
            else {
                if (char2 < 48) {
                    break;
                }
                if (char2 > 57) {
                    break;
                }
            }
            ++super.fCharacterCounter;
            super.fCurrentOffset += this.fBytesPerChar;
            if (b) {
                final int n3;
                n = (n << 4) + (n3 - (((n3 = char2) < 65) ? 48 : (((char2 < 97) ? 65 : 97) - 10)));
            }
            else {
                n = n * 10 + (char2 - 48);
            }
            if (n <= 1114111) {
                continue;
            }
            b2 = true;
            n = 0;
        }
        if (char2 != 59) {
            return -1;
        }
        ++super.fCharacterCounter;
        super.fCurrentOffset += this.fBytesPerChar;
        if (b2) {
            return -3;
        }
        return n;
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
        while (n == -1 || !this.lookingAtChar((char)n, false)) {
            if (this.lookingAtChar('&', false)) {
                return -2;
            }
            if (this.lookingAtChar('%', false)) {
                return -3;
            }
            if (!this.lookingAtValidChar(true)) {
                return -4;
            }
        }
        if (!b) {
            return -1;
        }
        final int addString = this.addString(fCurrentOffset, super.fCurrentOffset - fCurrentOffset);
        this.lookingAtChar((char)n, true);
        return addString;
    }
    
    public boolean scanExpectedName(final char c, final StringPool.CharArrayRange charArrayRange) throws Exception {
        final int fCurrentOffset = super.fCurrentOffset;
        this.skipPastName(c);
        final int n = super.fCurrentOffset - fCurrentOffset;
        if (n == 0) {
            return false;
        }
        this.addSymbol(fCurrentOffset, n);
        return true;
    }
    
    public void scanQName(final char c, final QName qName) throws Exception {
        final int fCurrentOffset = super.fCurrentOffset;
        int fCurrentOffset2 = -1;
        final int fCurrentOffset3 = super.fCurrentOffset;
        final int char1 = this.getChar(super.fCurrentOffset);
        if (char1 < 128) {
            if (XMLCharacterProperties.fgAsciiInitialNameChar[char1] == 0) {
                qName.clear();
                return;
            }
            if (char1 == 58) {
                qName.clear();
                return;
            }
        }
        else {
            if (!this.fCalledCharPropInit) {
                XMLCharacterProperties.initCharFlags();
                this.fCalledCharPropInit = true;
            }
            if ((XMLCharacterProperties.fgCharFlags[char1] & 0x2) == 0x0) {
                return;
            }
        }
        while (true) {
            super.fCurrentOffset += this.fBytesPerChar;
            ++super.fCharacterCounter;
            final int char2 = this.getChar(super.fCurrentOffset);
            if (c == char2) {
                break;
            }
            if (char2 < '\u0080') {
                if (XMLCharacterProperties.fgAsciiNameChar[char2] == 0) {
                    break;
                }
                if (char2 != ':') {
                    continue;
                }
                if (fCurrentOffset2 != -1) {
                    break;
                }
                fCurrentOffset2 = super.fCurrentOffset;
                final int char3 = this.getChar(super.fCurrentOffset + this.fBytesPerChar);
                boolean b = true;
                if (char3 < 128) {
                    if (XMLCharacterProperties.fgAsciiInitialNameChar[char3] == 0 || char3 == 58) {
                        b = false;
                    }
                }
                else {
                    if (!this.fCalledCharPropInit) {
                        XMLCharacterProperties.initCharFlags();
                        this.fCalledCharPropInit = true;
                    }
                    if ((XMLCharacterProperties.fgCharFlags[char3] & 0x2) == 0x0) {
                        b = false;
                    }
                }
                if (!b) {
                    fCurrentOffset2 = -1;
                    break;
                }
                continue;
            }
            else {
                if (!this.fCalledCharPropInit) {
                    XMLCharacterProperties.initCharFlags();
                    this.fCalledCharPropInit = true;
                }
                if ((XMLCharacterProperties.fgCharFlags[char2] & 0x2) == 0x0) {
                    break;
                }
                continue;
            }
        }
        final int n = super.fCurrentOffset - fCurrentOffset3;
        qName.prefix = ((fCurrentOffset2 == -1) ? -1 : this.addSymbol(fCurrentOffset3, fCurrentOffset2 - fCurrentOffset3));
        qName.rawname = this.addSymbol(fCurrentOffset3, n);
        qName.localpart = ((fCurrentOffset2 == -1) ? qName.rawname : this.addSymbol(fCurrentOffset2 + this.fBytesPerChar, super.fCurrentOffset - (fCurrentOffset2 + this.fBytesPerChar)));
        qName.uri = 0;
    }
    
    public int scanName(final char c) throws Exception {
        final int fCurrentOffset = super.fCurrentOffset;
        this.skipPastName(c);
        final int n = super.fCurrentOffset - fCurrentOffset;
        if (n == 0) {
            return -1;
        }
        return this.addSymbol(fCurrentOffset, n);
    }
    
    private int recognizeMarkup() throws Exception {
        switch (this.getChar(super.fCurrentOffset)) {
            case -1: {
                return 11;
            }
            case 63: {
                ++super.fCharacterCounter;
                super.fCurrentOffset += this.fBytesPerChar;
                return 0;
            }
            case 33: {
                ++super.fCharacterCounter;
                super.fCurrentOffset += this.fBytesPerChar;
                final int char1 = this.getChar(super.fCurrentOffset);
                if (char1 == -1) {
                    --super.fCharacterCounter;
                    super.fCurrentOffset -= this.fBytesPerChar;
                    return 11;
                }
                if (char1 == 45) {
                    ++super.fCharacterCounter;
                    super.fCurrentOffset += this.fBytesPerChar;
                    final int char2 = this.getChar(super.fCurrentOffset);
                    if (char2 == -1) {
                        super.fCharacterCounter -= 2;
                        super.fCurrentOffset -= 2;
                        return 11;
                    }
                    if (char2 == 45) {
                        ++super.fCharacterCounter;
                        super.fCurrentOffset += this.fBytesPerChar;
                        return 1;
                    }
                }
                else if (char1 == 91) {
                    ++super.fCharacterCounter;
                    super.fCurrentOffset += this.fBytesPerChar;
                    for (int i = 0; i < 6; ++i) {
                        final int char3 = this.getChar(super.fCurrentOffset);
                        if (char3 == -1) {
                            super.fCharacterCounter -= 2 + i;
                            super.fCurrentOffset -= (2 + i) * this.fBytesPerChar;
                            return 11;
                        }
                        if (char3 != UCSReader.cdata_string[i]) {
                            return 10;
                        }
                        ++super.fCharacterCounter;
                        super.fCurrentOffset += this.fBytesPerChar;
                    }
                    return 2;
                }
                return 10;
            }
            case 47: {
                ++super.fCharacterCounter;
                super.fCurrentOffset += this.fBytesPerChar;
                return 4;
            }
            default: {
                return 6;
            }
        }
    }
    
    private int recognizeReference() throws Exception {
        final int char1 = this.getChar(super.fCurrentOffset);
        if (char1 == -1) {
            return 12;
        }
        if (char1 == 35) {
            ++super.fCharacterCounter;
            super.fCurrentOffset += this.fBytesPerChar;
            return 7;
        }
        return 8;
    }
    
    public int scanContent(final QName qName) throws Exception {
        final int fCurrentOffset = super.fCurrentOffset;
        int n = this.getChar(super.fCurrentOffset);
        super.fCurrentOffset += this.fBytesPerChar;
        if (!this.fCalledCharPropInit) {
            XMLCharacterProperties.initCharFlags();
            this.fCalledCharPropInit = true;
        }
        if (n < 128) {
            if (n == -1) {
                super.fCurrentOffset -= this.fBytesPerChar;
                return this.changeReaders().scanContent(qName);
            }
            if ((XMLCharacterProperties.fgCharFlags[n] & 0x1) == 0x0 && n != 10 && n != 13) {
                if (n == 60) {
                    ++super.fCharacterCounter;
                    if (!super.fInCDSect) {
                        return this.recognizeMarkup();
                    }
                }
                else if (n == 38) {
                    ++super.fCharacterCounter;
                    if (!super.fInCDSect) {
                        return this.recognizeReference();
                    }
                }
                else {
                    if (n != 93) {
                        super.fCurrentOffset -= this.fBytesPerChar;
                        return 9;
                    }
                    if (this.getChar(super.fCurrentOffset) == 93 && this.getChar(super.fCurrentOffset + this.fBytesPerChar) == 62) {
                        super.fCharacterCounter += 3;
                        super.fCurrentOffset += 2 * this.fBytesPerChar;
                        return 3;
                    }
                }
            }
            else if (n == 32 || n == 9 || n == 10 || n == 13) {
                do {
                    if (n == 10) {
                        ++super.fLinefeedCounter;
                        super.fCharacterCounter = 1;
                    }
                    else if (n == 13) {
                        ++super.fCarriageReturnCounter;
                        super.fCharacterCounter = 1;
                    }
                    else {
                        ++super.fCharacterCounter;
                    }
                    n = this.getChar(super.fCurrentOffset);
                    super.fCurrentOffset += this.fBytesPerChar;
                } while (n == 32 || n == 9 || n == 10 || n == 13);
                if (n < 128) {
                    if (n == -1) {
                        super.fCurrentOffset -= this.fBytesPerChar;
                        this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - fCurrentOffset, true);
                        return this.changeReaders().scanContent(qName);
                    }
                    if ((XMLCharacterProperties.fgCharFlags[n] & 0x1) == 0x0) {
                        if (n == 60) {
                            if (!super.fInCDSect) {
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - this.fBytesPerChar - fCurrentOffset, true);
                                ++super.fCharacterCounter;
                                return this.recognizeMarkup();
                            }
                            ++super.fCharacterCounter;
                        }
                        else if (n == 38) {
                            if (!super.fInCDSect) {
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - this.fBytesPerChar - fCurrentOffset, true);
                                ++super.fCharacterCounter;
                                return this.recognizeReference();
                            }
                            ++super.fCharacterCounter;
                        }
                        else {
                            if (n != 93) {
                                super.fCurrentOffset -= this.fBytesPerChar;
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - fCurrentOffset, true);
                                return 9;
                            }
                            if (this.getChar(super.fCurrentOffset) == 93 && this.getChar(super.fCurrentOffset + this.fBytesPerChar) == 62) {
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - this.fBytesPerChar - fCurrentOffset, true);
                                super.fCharacterCounter += 3;
                                super.fCurrentOffset += 2 * this.fBytesPerChar;
                                return 3;
                            }
                        }
                    }
                }
                else if (n >= 55296 && n <= 57343) {
                    super.fCurrentOffset += this.fBytesPerChar;
                }
                else if (n == 65534 || n == 65535) {
                    super.fCurrentOffset -= this.fBytesPerChar;
                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - fCurrentOffset, true);
                    return 9;
                }
            }
        }
        else if (n >= 55296 && n <= 57343) {
            super.fCurrentOffset += this.fBytesPerChar;
        }
        else if (n == 65534 || n == 65535) {
            super.fCurrentOffset -= this.fBytesPerChar;
            return 9;
        }
        ++super.fCharacterCounter;
        int n2;
        while (true) {
            n2 = this.getChar(super.fCurrentOffset);
            super.fCurrentOffset += this.fBytesPerChar;
            if (n2 >= 128) {
                break;
            }
            if (n2 < 0) {
                break;
            }
            if ((XMLCharacterProperties.fgCharFlags[n2] & 0x1) == 0x0) {
                if (n2 == 10) {
                    ++super.fLinefeedCounter;
                    super.fCharacterCounter = 1;
                }
                else {
                    if (n2 != 13) {
                        break;
                    }
                    ++super.fCarriageReturnCounter;
                    super.fCharacterCounter = 1;
                }
            }
            else {
                ++super.fCharacterCounter;
            }
        }
        while (true) {
            if (n2 < 128) {
                if (n2 == -1) {
                    super.fCurrentOffset -= this.fBytesPerChar;
                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - fCurrentOffset, false);
                    return this.changeReaders().scanContent(qName);
                }
                if ((XMLCharacterProperties.fgCharFlags[n2] & 0x1) == 0x0) {
                    if (n2 == 60) {
                        if (!super.fInCDSect) {
                            this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - this.fBytesPerChar - fCurrentOffset, false);
                            ++super.fCharacterCounter;
                            return this.recognizeMarkup();
                        }
                        ++super.fCharacterCounter;
                    }
                    else if (n2 == 38) {
                        if (!super.fInCDSect) {
                            this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - this.fBytesPerChar - fCurrentOffset, false);
                            ++super.fCharacterCounter;
                            return this.recognizeReference();
                        }
                        ++super.fCharacterCounter;
                    }
                    else if (n2 == 10) {
                        ++super.fLinefeedCounter;
                        super.fCharacterCounter = 1;
                    }
                    else if (n2 == 13) {
                        ++super.fCarriageReturnCounter;
                        super.fCharacterCounter = 1;
                    }
                    else {
                        if (n2 != 93) {
                            super.fCurrentOffset -= this.fBytesPerChar;
                            this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - fCurrentOffset, false);
                            return 9;
                        }
                        if (this.getChar(super.fCurrentOffset) == 93 && this.getChar(super.fCurrentOffset + this.fBytesPerChar) == 62) {
                            this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - this.fBytesPerChar - fCurrentOffset, false);
                            super.fCharacterCounter += 3;
                            super.fCurrentOffset += 2 * this.fBytesPerChar;
                            return 3;
                        }
                        ++super.fCharacterCounter;
                    }
                }
                else {
                    ++super.fCharacterCounter;
                }
            }
            else {
                if (n2 >= 55296 && n2 <= 57343) {
                    ++super.fCharacterCounter;
                    super.fCurrentOffset += this.fBytesPerChar;
                }
                else if (n2 == 65534 || n2 == 65535) {
                    super.fCurrentOffset -= this.fBytesPerChar;
                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - fCurrentOffset, false);
                    return 9;
                }
                ++super.fCharacterCounter;
            }
            n2 = this.getChar(super.fCurrentOffset);
            super.fCurrentOffset += this.fBytesPerChar;
        }
    }
    
    static {
        UCSReader.fCharacters = new char[256];
        cdata_string = new char[] { 'C', 'D', 'A', 'T', 'A', '[' };
    }
}
