// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.IOException;
import org.apache.xerces.utils.SymbolCache;
import org.apache.xerces.utils.QName;
import org.apache.xerces.utils.XMLCharacterProperties;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.UTF8DataChunk;
import java.io.InputStream;
import org.apache.xerces.utils.StringPool;

final class UTF8Reader extends XMLEntityReader
{
    private static final boolean USE_OUT_OF_LINE_LOAD_NEXT_BYTE = false;
    private static final boolean USE_TRY_CATCH_FOR_LOAD_NEXT_BYTE = true;
    public static final byte[] fgAsciiAttValueChar;
    public static final byte[] fgAsciiEntityValueChar;
    private char[] fCharacters;
    private int fCharDataLength;
    private static final char[] cdata_string;
    private StringPool.CharArrayRange fCharArrayRange;
    private InputStream fInputStream;
    private StringPool fStringPool;
    private UTF8DataChunk fCurrentChunk;
    private int fCurrentIndex;
    private byte[] fMostRecentData;
    private int fMostRecentByte;
    private int fLength;
    private boolean fCalledCharPropInit;
    private boolean fCallClearPreviousChunk;
    
    public UTF8Reader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final InputStream fInputStream, final StringPool fStringPool) throws Exception {
        super(xmlEntityHandler, xmlErrorReporter, b);
        this.fCharacters = new char[16384];
        this.fCharDataLength = 0;
        this.fCharArrayRange = null;
        this.fInputStream = null;
        this.fStringPool = null;
        this.fCurrentChunk = null;
        this.fCurrentIndex = 0;
        this.fMostRecentData = null;
        this.fMostRecentByte = 0;
        this.fLength = 0;
        this.fCalledCharPropInit = false;
        this.fCallClearPreviousChunk = true;
        this.fInputStream = fInputStream;
        this.fStringPool = fStringPool;
        this.fCharArrayRange = this.fStringPool.createCharArrayRange();
        this.fCurrentChunk = UTF8DataChunk.createChunk(this.fStringPool, null);
        this.fillCurrentChunk();
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
    
    private int addSymbol(final int n, final int n2, final int n3) {
        if (n2 == 0) {
            return 0;
        }
        return this.fCurrentChunk.addSymbol(n, n2, n3);
    }
    
    public void append(final XMLEntityHandler.CharBuffer charBuffer, final int n, final int n2) {
        this.fCurrentChunk.append(charBuffer, n, n2);
    }
    
    private int slowLoadNextByte() throws Exception {
        this.fCallClearPreviousChunk = true;
        if (this.fCurrentChunk.nextChunk() != null) {
            this.fCurrentChunk = this.fCurrentChunk.nextChunk();
            this.fCurrentIndex = 0;
            this.fMostRecentData = this.fCurrentChunk.toByteArray();
            return this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
        }
        this.fCurrentChunk = UTF8DataChunk.createChunk(this.fStringPool, this.fCurrentChunk);
        return this.fillCurrentChunk();
    }
    
    private int loadNextByte() throws Exception {
        ++super.fCurrentOffset;
        ++this.fCurrentIndex;
        try {
            return this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return this.slowLoadNextByte();
        }
    }
    
    private boolean atEOF(final int n) {
        return n > this.fLength;
    }
    
    public XMLEntityHandler.EntityReader changeReaders() throws Exception {
        final XMLEntityHandler.EntityReader changeReaders = super.changeReaders();
        this.fCurrentChunk.releaseChunk();
        this.fCurrentChunk = null;
        this.fMostRecentData = null;
        this.fMostRecentByte = 0;
        return changeReaders;
    }
    
    public boolean lookingAtChar(final char c, final boolean b) throws Exception {
        final int fMostRecentByte = this.fMostRecentByte;
        if (fMostRecentByte != c) {
            if (fMostRecentByte == '\0' && this.atEOF(super.fCurrentOffset + 1)) {
                return this.changeReaders().lookingAtChar(c, b);
            }
            if (c == '\n' && fMostRecentByte == '\r') {
                if (b) {
                    ++super.fCarriageReturnCounter;
                    super.fCharacterCounter = 1;
                    ++super.fCurrentOffset;
                    ++this.fCurrentIndex;
                    int n;
                    try {
                        this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                        n = this.fMostRecentByte;
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        n = this.slowLoadNextByte();
                    }
                    if (n == 10) {
                        ++super.fLinefeedCounter;
                        ++super.fCurrentOffset;
                        ++this.fCurrentIndex;
                        try {
                            this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                        }
                        catch (ArrayIndexOutOfBoundsException ex2) {
                            this.slowLoadNextByte();
                        }
                    }
                }
                return true;
            }
            return false;
        }
        else {
            if (c == '\r') {
                return false;
            }
            if (b) {
                ++super.fCharacterCounter;
                ++super.fCurrentOffset;
                ++this.fCurrentIndex;
                try {
                    this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                }
                catch (ArrayIndexOutOfBoundsException ex3) {
                    this.slowLoadNextByte();
                }
            }
            return true;
        }
    }
    
    public boolean lookingAtValidChar(final boolean b) throws Exception {
        final int fMostRecentByte = this.fMostRecentByte;
        if (fMostRecentByte < 128) {
            if (fMostRecentByte >= 32 || fMostRecentByte == 9) {
                if (b) {
                    ++super.fCharacterCounter;
                    ++super.fCurrentOffset;
                    ++this.fCurrentIndex;
                    try {
                        this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        this.slowLoadNextByte();
                    }
                }
                return true;
            }
            if (fMostRecentByte == 10) {
                if (b) {
                    ++super.fLinefeedCounter;
                    super.fCharacterCounter = 1;
                    ++super.fCurrentOffset;
                    ++this.fCurrentIndex;
                    try {
                        this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        this.slowLoadNextByte();
                    }
                }
                return true;
            }
            if (fMostRecentByte == 13) {
                if (b) {
                    ++super.fCarriageReturnCounter;
                    super.fCharacterCounter = 1;
                    ++super.fCurrentOffset;
                    ++this.fCurrentIndex;
                    int n;
                    try {
                        this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                        n = this.fMostRecentByte;
                    }
                    catch (ArrayIndexOutOfBoundsException ex3) {
                        n = this.slowLoadNextByte();
                    }
                    if (n == 10) {
                        ++super.fLinefeedCounter;
                        ++super.fCurrentOffset;
                        ++this.fCurrentIndex;
                        try {
                            this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                        }
                        catch (ArrayIndexOutOfBoundsException ex4) {
                            this.slowLoadNextByte();
                        }
                    }
                }
                return true;
            }
            return fMostRecentByte == 0 && this.atEOF(super.fCurrentOffset + 1) && this.changeReaders().lookingAtValidChar(b);
        }
        else {
            final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
            final int fCurrentIndex = this.fCurrentIndex;
            final int fCurrentOffset = super.fCurrentOffset;
            final int loadNextByte = this.loadNextByte();
            if ((0xE0 & fMostRecentByte) == 0xC0) {
                if (b) {
                    ++super.fCharacterCounter;
                    this.loadNextByte();
                }
                else {
                    this.fCurrentChunk = fCurrentChunk;
                    this.fCurrentIndex = fCurrentIndex;
                    super.fCurrentOffset = fCurrentOffset;
                    this.fMostRecentData = fCurrentChunk.toByteArray();
                    this.fMostRecentByte = fMostRecentByte;
                }
                return true;
            }
            final int loadNextByte2 = this.loadNextByte();
            if ((0xF0 & fMostRecentByte) == 0xE0) {
                boolean b2 = false;
                if ((fMostRecentByte != 237 || loadNextByte < 160) && (fMostRecentByte != 239 || loadNextByte != 191 || loadNextByte2 < 190)) {
                    if (b) {
                        ++super.fCharacterCounter;
                        this.loadNextByte();
                        return true;
                    }
                    b2 = true;
                }
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = fMostRecentByte;
                return b2;
            }
            final int loadNextByte3 = this.loadNextByte();
            boolean b3 = false;
            if ((fMostRecentByte & 0xF8) == 0xF0 && (loadNextByte & 0xC0) == 0x80 && (loadNextByte2 & 0xC0) == 0x80 && (loadNextByte3 & 0xC0) == 0x80) {
                if (fMostRecentByte <= 244 && (fMostRecentByte != 244 || loadNextByte < 144)) {
                    if (b) {
                        ++super.fCharacterCounter;
                        this.loadNextByte();
                        return true;
                    }
                    b3 = true;
                }
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = fMostRecentByte;
                return b3;
            }
            this.fCurrentChunk = fCurrentChunk;
            this.fCurrentIndex = fCurrentIndex;
            super.fCurrentOffset = fCurrentOffset;
            this.fMostRecentData = fCurrentChunk.toByteArray();
            this.fMostRecentByte = fMostRecentByte;
            return b3;
        }
    }
    
    public boolean lookingAtSpace(final boolean b) throws Exception {
        final int fMostRecentByte = this.fMostRecentByte;
        if (fMostRecentByte > 32) {
            return false;
        }
        if (fMostRecentByte == 32 || fMostRecentByte == 9) {
            if (!b) {
                return true;
            }
            ++super.fCharacterCounter;
        }
        else if (fMostRecentByte == 10) {
            if (!b) {
                return true;
            }
            ++super.fLinefeedCounter;
            super.fCharacterCounter = 1;
        }
        else {
            if (fMostRecentByte != 13) {
                return fMostRecentByte == 0 && this.atEOF(super.fCurrentOffset + 1) && this.changeReaders().lookingAtSpace(b);
            }
            if (!b) {
                return true;
            }
            ++super.fCarriageReturnCounter;
            super.fCharacterCounter = 1;
            ++super.fCurrentOffset;
            ++this.fCurrentIndex;
            int n;
            try {
                this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                n = this.fMostRecentByte;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                n = this.slowLoadNextByte();
            }
            if (n != 10) {
                return true;
            }
            ++super.fLinefeedCounter;
        }
        ++super.fCurrentOffset;
        ++this.fCurrentIndex;
        try {
            this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            this.slowLoadNextByte();
        }
        return true;
    }
    
    public void skipToChar(final char c) throws Exception {
        int i = this.fMostRecentByte;
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
            else if (i == '\r') {
                ++super.fCarriageReturnCounter;
                super.fCharacterCounter = 1;
                i = this.loadNextByte();
                if (i != '\n') {
                    continue;
                }
                ++super.fLinefeedCounter;
            }
            else if (i < '\u0080') {
                ++super.fCharacterCounter;
            }
            else {
                ++super.fCharacterCounter;
                if (('\u00e0' & i) == '\u00c0') {
                    this.loadNextByte();
                }
                else if (('\u00f0' & i) == '\u00e0') {
                    this.loadNextByte();
                    this.loadNextByte();
                }
                else {
                    this.loadNextByte();
                    this.loadNextByte();
                    this.loadNextByte();
                }
            }
            i = this.loadNextByte();
        }
    }
    
    public void skipPastSpaces() throws Exception {
        int n = this.fMostRecentByte;
        while (true) {
            if (n == 32 || n == 9) {
                ++super.fCharacterCounter;
            }
            else if (n == 10) {
                ++super.fLinefeedCounter;
                super.fCharacterCounter = 1;
            }
            else {
                if (n != 13) {
                    break;
                }
                ++super.fCarriageReturnCounter;
                super.fCharacterCounter = 1;
                ++super.fCurrentOffset;
                ++this.fCurrentIndex;
                try {
                    this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                    n = this.fMostRecentByte;
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    n = this.slowLoadNextByte();
                }
                if (n != 10) {
                    continue;
                }
                ++super.fLinefeedCounter;
            }
            ++super.fCurrentOffset;
            ++this.fCurrentIndex;
            try {
                this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                n = this.fMostRecentByte;
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                n = this.slowLoadNextByte();
            }
        }
        if (n == 0 && this.atEOF(super.fCurrentOffset + 1)) {
            this.changeReaders().skipPastSpaces();
        }
    }
    
    protected boolean skippedMultiByteCharWithFlag(final int n, final int n2) throws Exception {
        final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
        final int fCurrentOffset = super.fCurrentOffset;
        final int fCurrentIndex = this.fCurrentIndex;
        if (!this.fCalledCharPropInit) {
            XMLCharacterProperties.initCharFlags();
            this.fCalledCharPropInit = true;
        }
        final int loadNextByte = this.loadNextByte();
        if ((0xE0 & n) == 0xC0) {
            if ((XMLCharacterProperties.fgCharFlags[((0x1F & n) << 6) + (0x3F & loadNextByte)] & n2) == 0x0) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return false;
            }
            return true;
        }
        else {
            final int loadNextByte2 = this.loadNextByte();
            if ((0xF0 & n) != 0xE0) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return false;
            }
            if ((n == 237 && loadNextByte >= 160) || (n == 239 && loadNextByte == 191 && loadNextByte2 >= 190)) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return false;
            }
            if ((XMLCharacterProperties.fgCharFlags[((0xF & n) << 12) + ((0x3F & loadNextByte) << 6) + (0x3F & loadNextByte2)] & n2) == 0x0) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return false;
            }
            return true;
        }
    }
    
    public void skipPastName(final char c) throws Exception {
        final int fMostRecentByte = this.fMostRecentByte;
        if (fMostRecentByte < 128) {
            if (XMLCharacterProperties.fgAsciiInitialNameChar[fMostRecentByte] == 0) {
                return;
            }
        }
        else {
            if (!this.fCalledCharPropInit) {
                XMLCharacterProperties.initCharFlags();
                this.fCalledCharPropInit = true;
            }
            if (!this.skippedMultiByteCharWithFlag(fMostRecentByte, 2)) {
                return;
            }
        }
        while (true) {
            ++super.fCharacterCounter;
            final int loadNextByte = this.loadNextByte();
            if (c == loadNextByte) {
                return;
            }
            if (loadNextByte < '\u0080') {
                if (XMLCharacterProperties.fgAsciiNameChar[loadNextByte] == 0) {
                    return;
                }
                continue;
            }
            else {
                if (!this.fCalledCharPropInit) {
                    XMLCharacterProperties.initCharFlags();
                    this.fCalledCharPropInit = true;
                }
                if (!this.skippedMultiByteCharWithFlag(loadNextByte, 4)) {
                    return;
                }
                continue;
            }
        }
    }
    
    public void skipPastNmtoken(final char c) throws Exception {
        for (int n = this.fMostRecentByte; c != n; n = this.loadNextByte()) {
            if (n < '\u0080') {
                if (XMLCharacterProperties.fgAsciiNameChar[n] == 0) {
                    return;
                }
            }
            else if (!this.skippedMultiByteCharWithFlag(n, 4)) {
                return;
            }
            ++super.fCharacterCounter;
        }
    }
    
    public boolean skippedString(final char[] array) throws Exception {
        final int length = array.length;
        byte[] array2 = this.fMostRecentData;
        int n = this.fCurrentIndex + length;
        int n2 = length;
        try {
            while (n2-- > 0) {
                if (array2[--n] != array[n2]) {
                    return false;
                }
            }
            this.fCurrentIndex += length;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            int i = 0;
            int j = this.fCurrentIndex;
            while (j < 16384) {
                if (array2[j++] != array[i++]) {
                    return false;
                }
            }
            final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
            final int fCurrentOffset = super.fCurrentOffset;
            final int fCurrentIndex = this.fCurrentIndex;
            this.slowLoadNextByte();
            array2 = this.fMostRecentData;
            int fCurrentIndex2 = 0;
            while (i < length) {
                if (array2[fCurrentIndex2++] != array[i++]) {
                    this.fCurrentChunk = fCurrentChunk;
                    this.fCurrentIndex = fCurrentIndex;
                    super.fCurrentOffset = fCurrentOffset;
                    this.fMostRecentData = this.fCurrentChunk.toByteArray();
                    this.fMostRecentByte = (this.fMostRecentData[fCurrentIndex] & 0xFF);
                    return false;
                }
            }
            this.fCurrentIndex = fCurrentIndex2;
        }
        super.fCharacterCounter += length;
        super.fCurrentOffset += length;
        try {
            this.fMostRecentByte = (array2[this.fCurrentIndex] & 0xFF);
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            this.slowLoadNextByte();
        }
        return true;
    }
    
    public int scanInvalidChar() throws Exception {
        int n2;
        final int n = n2 = this.fMostRecentByte;
        if (n2 == 10) {
            ++super.fLinefeedCounter;
            super.fCharacterCounter = 1;
        }
        else if (n2 == 13) {
            ++super.fCarriageReturnCounter;
            super.fCharacterCounter = 1;
            n2 = this.loadNextByte();
            if (n2 != 10) {
                return 10;
            }
            ++super.fLinefeedCounter;
        }
        else if (n2 == 0) {
            if (this.atEOF(super.fCurrentOffset + 1)) {
                return this.changeReaders().scanInvalidChar();
            }
            ++super.fCharacterCounter;
        }
        else if (n >= 128) {
            ++super.fCharacterCounter;
            final int loadNextByte = this.loadNextByte();
            if ((0xE0 & n) == 0xC0) {
                n2 = ((0x1F & n) << 6) + (0x3F & loadNextByte);
            }
            else if ((0xF0 & n) == 0xE0) {
                n2 = ((0xF & n) << 12) + ((0x3F & loadNextByte) << 6) + (0x3F & this.loadNextByte());
            }
            else if ((0xF8 & n) == 0xF0) {
                n2 = ((0xF & n) << 18) + ((0x3F & loadNextByte) << 12) + ((0x3F & this.loadNextByte()) << 6) + (0x3F & this.loadNextByte());
            }
        }
        this.loadNextByte();
        return n2;
    }
    
    public int scanCharRef(final boolean b) throws Exception {
        final int fMostRecentByte = this.fMostRecentByte;
        if (fMostRecentByte == 0) {
            if (this.atEOF(super.fCurrentOffset + 1)) {
                return this.changeReaders().scanCharRef(b);
            }
            return -2;
        }
        else {
            int n;
            if (b) {
                if (fMostRecentByte > 102 || XMLCharacterProperties.fgAsciiXDigitChar[fMostRecentByte] == 0) {
                    return -2;
                }
                final int n2;
                n = n2 - (((n2 = fMostRecentByte) < 65) ? 48 : (((fMostRecentByte < 97) ? 65 : 97) - 10));
            }
            else {
                if (fMostRecentByte < 48 || fMostRecentByte > 57) {
                    return -2;
                }
                n = fMostRecentByte - 48;
            }
            ++super.fCharacterCounter;
            this.loadNextByte();
            boolean b2 = false;
            int fMostRecentByte2;
            while (true) {
                fMostRecentByte2 = this.fMostRecentByte;
                if (fMostRecentByte2 == 0) {
                    break;
                }
                if (b) {
                    if (fMostRecentByte2 > 102) {
                        break;
                    }
                    if (XMLCharacterProperties.fgAsciiXDigitChar[fMostRecentByte2] == 0) {
                        break;
                    }
                }
                else {
                    if (fMostRecentByte2 < 48) {
                        break;
                    }
                    if (fMostRecentByte2 > 57) {
                        break;
                    }
                }
                ++super.fCharacterCounter;
                this.loadNextByte();
                if (b) {
                    final int n3;
                    n = (n << 4) + (n3 - (((n3 = fMostRecentByte2) < 65) ? 48 : (((fMostRecentByte2 < 97) ? 65 : 97) - 10)));
                }
                else {
                    n = n * 10 + (fMostRecentByte2 - 48);
                }
                if (n <= 1114111) {
                    continue;
                }
                b2 = true;
                n = 0;
            }
            if (fMostRecentByte2 != 59) {
                return -1;
            }
            ++super.fCharacterCounter;
            this.loadNextByte();
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
        final int addString = this.fCurrentChunk.addString(fCurrentOffset, super.fCurrentOffset - fCurrentOffset);
        this.lookingAtChar((char)n, true);
        return addString;
    }
    
    public int scanAttValue(final char c, final boolean b) throws Exception {
        final int fCurrentOffset = super.fCurrentOffset;
        int n = this.fMostRecentByte;
        while (true) {
            if (n < 128) {
                switch (UTF8Reader.fgAsciiAttValueChar[n]) {
                    case 1: {
                        if (n == c) {
                            final int n2 = super.fCurrentOffset - fCurrentOffset;
                            final int n3 = (n2 == 0) ? 0 : (b ? this.fCurrentChunk.addSymbol(fCurrentOffset, n2, 0) : this.fCurrentChunk.addString(fCurrentOffset, n2));
                            ++super.fCharacterCounter;
                            ++super.fCurrentOffset;
                            ++this.fCurrentIndex;
                            try {
                                this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                            }
                            catch (ArrayIndexOutOfBoundsException ex) {
                                this.slowLoadNextByte();
                            }
                            return n3;
                        }
                    }
                    case 0: {
                        ++super.fCharacterCounter;
                        ++super.fCurrentOffset;
                        ++this.fCurrentIndex;
                        try {
                            final int fMostRecentByte = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                            this.fMostRecentByte = fMostRecentByte;
                            n = fMostRecentByte;
                        }
                        catch (ArrayIndexOutOfBoundsException ex2) {
                            n = this.slowLoadNextByte();
                        }
                        continue;
                    }
                    case 2: {
                        return -1;
                    }
                    case 3: {
                        return -2;
                    }
                    case 4: {
                        return -3;
                    }
                    default: {
                        continue;
                    }
                }
            }
            else {
                if (!this.skipMultiByteCharData(n)) {
                    return -3;
                }
                n = this.fMostRecentByte;
            }
        }
    }
    
    public int scanEntityValue(final int n, final boolean b) throws Exception {
        final int fCurrentOffset = super.fCurrentOffset;
        int n2 = this.fMostRecentByte;
    Label_0477:
        while (true) {
            if (n2 < 128) {
                Label_0177: {
                    switch (UTF8Reader.fgAsciiEntityValueChar[n2]) {
                        case 1: {
                            if (n2 != n) {
                                break Label_0177;
                            }
                            if (!b) {
                                return -1;
                            }
                            final int n3 = super.fCurrentOffset - fCurrentOffset;
                            final int n4 = (n3 == 0) ? 0 : this.fCurrentChunk.addString(fCurrentOffset, n3);
                            ++super.fCharacterCounter;
                            ++super.fCurrentOffset;
                            ++this.fCurrentIndex;
                            try {
                                this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                            }
                            catch (ArrayIndexOutOfBoundsException ex) {
                                this.slowLoadNextByte();
                            }
                            return n4;
                        }
                        case 0: {
                            ++super.fCharacterCounter;
                            ++super.fCurrentOffset;
                            ++this.fCurrentIndex;
                            try {
                                final int fMostRecentByte = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                                this.fMostRecentByte = fMostRecentByte;
                                n2 = fMostRecentByte;
                            }
                            catch (ArrayIndexOutOfBoundsException ex2) {
                                n2 = this.slowLoadNextByte();
                            }
                            continue;
                        }
                        case 5: {
                            ++super.fLinefeedCounter;
                            super.fCharacterCounter = 1;
                            ++super.fCurrentOffset;
                            ++this.fCurrentIndex;
                            try {
                                final int fMostRecentByte2 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                                this.fMostRecentByte = fMostRecentByte2;
                                n2 = fMostRecentByte2;
                            }
                            catch (ArrayIndexOutOfBoundsException ex3) {
                                n2 = this.slowLoadNextByte();
                            }
                            continue;
                        }
                        case 6: {
                            ++super.fCarriageReturnCounter;
                            super.fCharacterCounter = 1;
                            ++super.fCurrentOffset;
                            ++this.fCurrentIndex;
                            try {
                                final int fMostRecentByte3 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                                this.fMostRecentByte = fMostRecentByte3;
                                n2 = fMostRecentByte3;
                            }
                            catch (ArrayIndexOutOfBoundsException ex4) {
                                n2 = this.slowLoadNextByte();
                            }
                            if (n2 != 10) {
                                continue;
                            }
                            ++super.fLinefeedCounter;
                            ++super.fCurrentOffset;
                            ++this.fCurrentIndex;
                            try {
                                final int fMostRecentByte4 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                                this.fMostRecentByte = fMostRecentByte4;
                                n2 = fMostRecentByte4;
                            }
                            catch (ArrayIndexOutOfBoundsException ex5) {
                                n2 = this.slowLoadNextByte();
                            }
                            continue;
                        }
                        case 2: {
                            return -2;
                        }
                        case 3: {
                            return -3;
                        }
                        case 7: {
                            if (this.atEOF(super.fCurrentOffset + 1)) {
                                this.changeReaders();
                                return -5;
                            }
                            break Label_0477;
                        }
                        case 4: {
                            break Label_0477;
                        }
                        default: {
                            continue;
                        }
                    }
                }
            }
            else {
                if (!this.skipMultiByteCharData(n2)) {
                    return -4;
                }
                n2 = this.fMostRecentByte;
            }
        }
        return -4;
    }
    
    public boolean scanExpectedName(final char c, final StringPool.CharArrayRange charArrayRange) throws Exception {
        final char[] chars = charArrayRange.chars;
        int offset = charArrayRange.offset;
        final int length = charArrayRange.length;
        int n = this.fMostRecentByte;
        int n2 = 0;
        int n3;
        while (true) {
            if (n < 128) {
                n3 = n;
                if (n2 == length) {
                    break;
                }
                if (n3 != chars[offset]) {
                    this.skipPastNmtoken(c);
                    return false;
                }
            }
            else {
                final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
                final int fCurrentIndex = this.fCurrentIndex;
                final int fCurrentOffset = super.fCurrentOffset;
                ++super.fCurrentOffset;
                ++this.fCurrentIndex;
                int slowLoadNextByte;
                try {
                    slowLoadNextByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    slowLoadNextByte = this.slowLoadNextByte();
                }
                if ((0xE0 & n) == 0xC0) {
                    n3 = ((0x1F & n) << 6) + (0x3F & slowLoadNextByte);
                    if (n2 == length) {
                        break;
                    }
                    if (n3 != chars[offset]) {
                        this.fCurrentChunk = fCurrentChunk;
                        this.fCurrentIndex = fCurrentIndex;
                        super.fCurrentOffset = fCurrentOffset;
                        this.fMostRecentData = fCurrentChunk.toByteArray();
                        this.fMostRecentByte = n;
                        this.skipPastNmtoken(c);
                        return false;
                    }
                }
                else {
                    ++super.fCurrentOffset;
                    ++this.fCurrentIndex;
                    int slowLoadNextByte2;
                    try {
                        slowLoadNextByte2 = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        slowLoadNextByte2 = this.slowLoadNextByte();
                    }
                    if ((0xF0 & n) != 0xE0) {
                        this.fCurrentChunk = fCurrentChunk;
                        this.fCurrentIndex = fCurrentIndex;
                        super.fCurrentOffset = fCurrentOffset;
                        this.fMostRecentData = fCurrentChunk.toByteArray();
                        this.fMostRecentByte = n;
                        return false;
                    }
                    if ((n == 237 && slowLoadNextByte >= 160) || (n == 239 && slowLoadNextByte == 191 && slowLoadNextByte2 >= 190)) {
                        this.fCurrentChunk = fCurrentChunk;
                        this.fCurrentIndex = fCurrentIndex;
                        super.fCurrentOffset = fCurrentOffset;
                        this.fMostRecentData = fCurrentChunk.toByteArray();
                        this.fMostRecentByte = n;
                        return false;
                    }
                    n3 = ((0xF & n) << 12) + ((0x3F & slowLoadNextByte) << 6) + (0x3F & slowLoadNextByte2);
                    if (n2 == length) {
                        break;
                    }
                    if (n3 != chars[offset]) {
                        this.fCurrentChunk = fCurrentChunk;
                        this.fCurrentIndex = fCurrentIndex;
                        super.fCurrentOffset = fCurrentOffset;
                        this.fMostRecentData = fCurrentChunk.toByteArray();
                        this.fMostRecentByte = n;
                        this.skipPastNmtoken(c);
                        return false;
                    }
                }
            }
            ++n2;
            ++offset;
            ++super.fCharacterCounter;
            ++super.fCurrentOffset;
            ++this.fCurrentIndex;
            try {
                final int fMostRecentByte = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                this.fMostRecentByte = fMostRecentByte;
                n = fMostRecentByte;
            }
            catch (ArrayIndexOutOfBoundsException ex3) {
                n = this.slowLoadNextByte();
            }
        }
        if (n3 == c) {
            return true;
        }
        if (n3 < '\u0080') {
            if (XMLCharacterProperties.fgAsciiNameChar[n3] == 0) {
                return true;
            }
        }
        else {
            if (!this.fCalledCharPropInit) {
                XMLCharacterProperties.initCharFlags();
                this.fCalledCharPropInit = true;
            }
            if ((XMLCharacterProperties.fgCharFlags[n3] & 0x4) == 0x0) {
                return true;
            }
        }
        this.skipPastNmtoken(c);
        return false;
    }
    
    public void scanQName(final char c, final QName qName) throws Exception {
        final int fCurrentOffset = super.fCurrentOffset;
        final int fMostRecentByte = this.fMostRecentByte;
        if (fMostRecentByte < 128) {
            if (XMLCharacterProperties.fgAsciiInitialNameChar[fMostRecentByte] == 0) {
                qName.clear();
                return;
            }
            if (fMostRecentByte == 58) {
                qName.clear();
                return;
            }
        }
        else {
            if (!this.fCalledCharPropInit) {
                XMLCharacterProperties.initCharFlags();
                this.fCalledCharPropInit = true;
            }
            final int multiByteSymbolChar = this.getMultiByteSymbolChar(fMostRecentByte);
            --this.fCurrentIndex;
            --super.fCurrentOffset;
            if ((XMLCharacterProperties.fgCharFlags[multiByteSymbolChar] & 0x2) == 0x0) {
                qName.clear();
                return;
            }
        }
        int n = this.fCurrentIndex;
        byte[] array = this.fMostRecentData;
        int fCurrentOffset2 = -1;
        int n2;
        while (true) {
            ++super.fCharacterCounter;
            ++super.fCurrentOffset;
            ++n;
            try {
                n2 = (array[n] & 0xFF);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                n2 = this.slowLoadNextByte();
                n = 0;
                array = this.fMostRecentData;
            }
            if (c == n2) {
                break;
            }
            if (n2 < '\u0080') {
                if (XMLCharacterProperties.fgAsciiNameChar[n2] == 0) {
                    break;
                }
                if (n2 != ':') {
                    continue;
                }
                if (fCurrentOffset2 != -1) {
                    break;
                }
                fCurrentOffset2 = super.fCurrentOffset;
                int slowLoadNextByte;
                try {
                    slowLoadNextByte = (array[n + 1] & 0xFF);
                }
                catch (ArrayIndexOutOfBoundsException ex2) {
                    final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
                    final int fCurrentOffset3 = super.fCurrentOffset;
                    slowLoadNextByte = this.slowLoadNextByte();
                    this.fCurrentChunk = fCurrentChunk;
                    super.fCurrentOffset = fCurrentOffset3;
                    this.fMostRecentData = this.fCurrentChunk.toByteArray();
                }
                boolean b = true;
                if (slowLoadNextByte < 128) {
                    if (XMLCharacterProperties.fgAsciiInitialNameChar[slowLoadNextByte] == 0 || slowLoadNextByte == 58) {
                        b = false;
                    }
                }
                else {
                    if (!this.fCalledCharPropInit) {
                        XMLCharacterProperties.initCharFlags();
                        this.fCalledCharPropInit = true;
                    }
                    if ((XMLCharacterProperties.fgCharFlags[slowLoadNextByte] & 0x2) == 0x0) {
                        b = false;
                    }
                }
                n2 = ':';
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
                this.fCurrentIndex = n;
                this.fMostRecentByte = n2;
                n2 = this.getMultiByteSymbolChar(n2);
                --this.fCurrentIndex;
                --super.fCurrentOffset;
                n = this.fCurrentIndex;
                if ((XMLCharacterProperties.fgCharFlags[n2] & 0x4) == 0x0) {
                    break;
                }
                continue;
            }
        }
        this.fCurrentIndex = n;
        this.fMostRecentByte = n2;
        qName.rawname = this.addSymbol(fCurrentOffset, super.fCurrentOffset - fCurrentOffset);
        qName.prefix = ((fCurrentOffset2 == -1) ? -1 : this.addSymbol(fCurrentOffset, fCurrentOffset2 - fCurrentOffset));
        qName.localpart = ((fCurrentOffset2 == -1) ? qName.rawname : this.addSymbol(fCurrentOffset2 + 1, super.fCurrentOffset - (fCurrentOffset2 + 1)));
        qName.uri = 0;
    }
    
    private int getMultiByteSymbolChar(final int n) throws Exception {
        final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
        final int fCurrentIndex = this.fCurrentIndex;
        final int fCurrentOffset = super.fCurrentOffset;
        if (!this.fCalledCharPropInit) {
            XMLCharacterProperties.initCharFlags();
            this.fCalledCharPropInit = true;
        }
        ++super.fCurrentOffset;
        ++this.fCurrentIndex;
        int slowLoadNextByte;
        try {
            slowLoadNextByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            slowLoadNextByte = this.slowLoadNextByte();
        }
        if ((0xE0 & n) == 0xC0) {
            final int n2 = ((0x1F & n) << 6) + (0x3F & slowLoadNextByte);
            if ((XMLCharacterProperties.fgCharFlags[n2] & 0x4) == 0x0) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return -1;
            }
            this.loadNextByte();
            return n2;
        }
        else {
            ++super.fCurrentOffset;
            ++this.fCurrentIndex;
            int slowLoadNextByte2;
            try {
                slowLoadNextByte2 = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                slowLoadNextByte2 = this.slowLoadNextByte();
            }
            if ((0xF0 & n) != 0xE0) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return -1;
            }
            if ((n == 237 && slowLoadNextByte >= 160) || (n == 239 && slowLoadNextByte == 191 && slowLoadNextByte2 >= 190)) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return -1;
            }
            final int n3 = ((0xF & n) << 12) + ((0x3F & slowLoadNextByte) << 6) + (0x3F & slowLoadNextByte2);
            if ((XMLCharacterProperties.fgCharFlags[n3] & 0x4) == 0x0) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return -1;
            }
            this.loadNextByte();
            return n3;
        }
    }
    
    public int scanName(final char c) throws Exception {
        final int fMostRecentByte = this.fMostRecentByte;
        int n;
        if (fMostRecentByte < 128) {
            if (XMLCharacterProperties.fgAsciiInitialNameChar[fMostRecentByte] == 0) {
                if (fMostRecentByte == 0 && this.atEOF(super.fCurrentOffset + 1)) {
                    return this.changeReaders().scanName(c);
                }
                return -1;
            }
            else {
                n = fMostRecentByte;
            }
        }
        else {
            final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
            final int fCurrentIndex = this.fCurrentIndex;
            final int fCurrentOffset = super.fCurrentOffset;
            if (!this.fCalledCharPropInit) {
                XMLCharacterProperties.initCharFlags();
                this.fCalledCharPropInit = true;
            }
            ++super.fCurrentOffset;
            ++this.fCurrentIndex;
            int slowLoadNextByte;
            try {
                slowLoadNextByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                slowLoadNextByte = this.slowLoadNextByte();
            }
            if ((0xE0 & fMostRecentByte) == 0xC0) {
                n = ((0x1F & fMostRecentByte) << 6) + (0x3F & slowLoadNextByte);
                if ((XMLCharacterProperties.fgCharFlags[n] & 0x2) == 0x0) {
                    this.fCurrentChunk = fCurrentChunk;
                    this.fCurrentIndex = fCurrentIndex;
                    super.fCurrentOffset = fCurrentOffset;
                    this.fMostRecentData = fCurrentChunk.toByteArray();
                    this.fMostRecentByte = fMostRecentByte;
                    return -1;
                }
            }
            else {
                ++super.fCurrentOffset;
                ++this.fCurrentIndex;
                int slowLoadNextByte2;
                try {
                    slowLoadNextByte2 = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                }
                catch (ArrayIndexOutOfBoundsException ex2) {
                    slowLoadNextByte2 = this.slowLoadNextByte();
                }
                if ((0xF0 & fMostRecentByte) != 0xE0) {
                    this.fCurrentChunk = fCurrentChunk;
                    this.fCurrentIndex = fCurrentIndex;
                    super.fCurrentOffset = fCurrentOffset;
                    this.fMostRecentData = fCurrentChunk.toByteArray();
                    this.fMostRecentByte = fMostRecentByte;
                    return -1;
                }
                if ((fMostRecentByte == 237 && slowLoadNextByte >= 160) || (fMostRecentByte == 239 && slowLoadNextByte == 191 && slowLoadNextByte2 >= 190)) {
                    this.fCurrentChunk = fCurrentChunk;
                    this.fCurrentIndex = fCurrentIndex;
                    super.fCurrentOffset = fCurrentOffset;
                    this.fMostRecentData = fCurrentChunk.toByteArray();
                    this.fMostRecentByte = fMostRecentByte;
                    return -1;
                }
                n = ((0xF & fMostRecentByte) << 12) + ((0x3F & slowLoadNextByte) << 6) + (0x3F & slowLoadNextByte2);
                if ((XMLCharacterProperties.fgCharFlags[n] & 0x2) == 0x0) {
                    this.fCurrentChunk = fCurrentChunk;
                    this.fCurrentIndex = fCurrentIndex;
                    super.fCurrentOffset = fCurrentOffset;
                    this.fMostRecentData = fCurrentChunk.toByteArray();
                    this.fMostRecentByte = fMostRecentByte;
                    return -1;
                }
            }
        }
        ++super.fCharacterCounter;
        ++super.fCurrentOffset;
        ++this.fCurrentIndex;
        int slowLoadNextByte3;
        try {
            final int fMostRecentByte2 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
            this.fMostRecentByte = fMostRecentByte2;
            slowLoadNextByte3 = fMostRecentByte2;
        }
        catch (ArrayIndexOutOfBoundsException ex3) {
            slowLoadNextByte3 = this.slowLoadNextByte();
        }
        return this.scanMatchingName(n, slowLoadNextByte3, c);
    }
    
    private int scanMatchingName(int n, int n2, final int n3) throws Exception {
        final SymbolCache symbolCache = this.fStringPool.getSymbolCache();
        int[][] fCacheLines = symbolCache.fCacheLines;
        char[] fSymbolChars = symbolCache.fSymbolChars;
        final boolean b = n3 == this.fMostRecentByte;
        final int fSymbolCharsOffset = symbolCache.fSymbolCharsOffset;
        int n4 = 0;
        int[] array = fCacheLines[n4];
        int i = 1 + (array[0] - 1) * 3;
        int n5 = 0;
        if (b) {
            while (i > 0) {
                if (array[i + 0] == n) {
                    if (array[i + 1] != -1) {
                        final int n6 = array[i + 1];
                        if (n5 > 3) {
                            this.fStringPool.updateCacheLine(n6, n5, 1);
                        }
                        return n6;
                    }
                    break;
                }
                else {
                    i -= 3;
                    ++n5;
                }
            }
            try {
                fSymbolChars[symbolCache.fSymbolCharsOffset] = (char)n;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                final char[] fSymbolChars2 = new char[symbolCache.fSymbolCharsOffset * 2];
                System.arraycopy(symbolCache.fSymbolChars, 0, fSymbolChars2, 0, symbolCache.fSymbolCharsOffset);
                (symbolCache.fSymbolChars = fSymbolChars2)[symbolCache.fSymbolCharsOffset] = (char)n;
            }
            final SymbolCache symbolCache2 = symbolCache;
            ++symbolCache2.fSymbolCharsOffset;
            if (i < 0) {
                i = 1 + array[0] * 3;
                final int[] array2 = array;
                final int n7 = 0;
                ++array2[n7];
                try {
                    array[i + 0] = n;
                }
                catch (ArrayIndexOutOfBoundsException ex2) {
                    array = new int[1 + (i - 1) * 2];
                    System.arraycopy(fCacheLines[n4], 0, array, 0, i);
                    (fCacheLines[n4] = array)[i + 0] = n;
                }
                array[i + 2] = -1;
            }
            return this.fStringPool.createNonMatchingSymbol(fSymbolCharsOffset, n4, array, i);
        }
        try {
            fSymbolChars[symbolCache.fSymbolCharsOffset] = (char)n;
        }
        catch (ArrayIndexOutOfBoundsException ex3) {
            fSymbolChars = new char[symbolCache.fSymbolCharsOffset * 2];
            System.arraycopy(symbolCache.fSymbolChars, 0, fSymbolChars, 0, symbolCache.fSymbolCharsOffset);
            (symbolCache.fSymbolChars = fSymbolChars)[symbolCache.fSymbolCharsOffset] = (char)n;
        }
        final SymbolCache symbolCache3 = symbolCache;
        ++symbolCache3.fSymbolCharsOffset;
        int n8 = 1;
        while (i >= 0) {
            if (array[i + 0] != n) {
                i -= 3;
                ++n5;
            }
            else {
                if (n2 >= 128) {
                    n = this.getMultiByteSymbolChar(n2);
                    n2 = this.fMostRecentByte;
                }
                else if (n2 == n3 || XMLCharacterProperties.fgAsciiNameChar[n2] == 0) {
                    n = -1;
                }
                else {
                    n = n2;
                    ++super.fCharacterCounter;
                    ++super.fCurrentOffset;
                    ++this.fCurrentIndex;
                    try {
                        final int fMostRecentByte = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                        this.fMostRecentByte = fMostRecentByte;
                        n2 = fMostRecentByte;
                    }
                    catch (ArrayIndexOutOfBoundsException ex4) {
                        n2 = this.slowLoadNextByte();
                    }
                }
                if (n == -1) {
                    if (array[i + 1] == -1) {
                        return this.fStringPool.createNonMatchingSymbol(fSymbolCharsOffset, n4, array, i);
                    }
                    symbolCache.fSymbolCharsOffset = fSymbolCharsOffset;
                    final int n9 = array[i + 1];
                    if (n5 > n8 * 3) {
                        this.fStringPool.updateCacheLine(n9, n5, n8);
                    }
                    return n9;
                }
                else {
                    try {
                        fSymbolChars[symbolCache.fSymbolCharsOffset] = (char)n;
                    }
                    catch (ArrayIndexOutOfBoundsException ex5) {
                        fSymbolChars = new char[symbolCache.fSymbolCharsOffset * 2];
                        System.arraycopy(symbolCache.fSymbolChars, 0, fSymbolChars, 0, symbolCache.fSymbolCharsOffset);
                        (symbolCache.fSymbolChars = fSymbolChars)[symbolCache.fSymbolCharsOffset] = (char)n;
                    }
                    final SymbolCache symbolCache4 = symbolCache;
                    ++symbolCache4.fSymbolCharsOffset;
                    n4 = array[i + 2];
                    try {
                        array = fCacheLines[n4];
                    }
                    catch (ArrayIndexOutOfBoundsException ex6) {
                        if (n4 != -1) {
                            final int[] array3 = fCacheLines[n4];
                            throw new RuntimeException("RDR001 untested");
                        }
                        n4 = symbolCache.fCacheLineCount++;
                        array[i + 2] = n4;
                        array = new int[13];
                        try {
                            fCacheLines[n4] = array;
                        }
                        catch (ArrayIndexOutOfBoundsException ex7) {
                            fCacheLines = new int[n4 * 2][];
                            System.arraycopy(symbolCache.fCacheLines, 0, fCacheLines, 0, n4);
                            (symbolCache.fCacheLines = fCacheLines)[n4] = array;
                        }
                    }
                    i = 1 + (array[0] - 1) * 3;
                    ++n8;
                }
            }
        }
        if (i < 0) {
            i = 1 + array[0] * 3;
        }
        while (true) {
            final int[] array4 = array;
            final int n10 = 0;
            ++array4[n10];
            try {
                array[i + 0] = n;
            }
            catch (ArrayIndexOutOfBoundsException ex8) {
                array = new int[1 + (i - 1) * 2];
                System.arraycopy(fCacheLines[n4], 0, array, 0, i);
                (fCacheLines[n4] = array)[i + 0] = n;
            }
            if (n2 >= 128) {
                n = this.getMultiByteSymbolChar(n2);
                n2 = this.fMostRecentByte;
            }
            else if (n2 == n3 || XMLCharacterProperties.fgAsciiNameChar[n2] == 0) {
                n = -1;
            }
            else {
                n = n2;
                ++super.fCharacterCounter;
                ++super.fCurrentOffset;
                ++this.fCurrentIndex;
                try {
                    final int fMostRecentByte2 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                    this.fMostRecentByte = fMostRecentByte2;
                    n2 = fMostRecentByte2;
                }
                catch (ArrayIndexOutOfBoundsException ex9) {
                    n2 = this.slowLoadNextByte();
                }
            }
            if (n == -1) {
                break;
            }
            n4 = symbolCache.fCacheLineCount++;
            array[i + 1] = -1;
            array[i + 2] = n4;
            array = new int[13];
            try {
                fCacheLines[n4] = array;
            }
            catch (ArrayIndexOutOfBoundsException ex10) {
                fCacheLines = new int[n4 * 2][];
                System.arraycopy(symbolCache.fCacheLines, 0, fCacheLines, 0, n4);
                (symbolCache.fCacheLines = fCacheLines)[n4] = array;
            }
            i = 1;
            try {
                fSymbolChars[symbolCache.fSymbolCharsOffset] = (char)n;
            }
            catch (ArrayIndexOutOfBoundsException ex11) {
                fSymbolChars = new char[symbolCache.fSymbolCharsOffset * 2];
                System.arraycopy(symbolCache.fSymbolChars, 0, fSymbolChars, 0, symbolCache.fSymbolCharsOffset);
                (symbolCache.fSymbolChars = fSymbolChars)[symbolCache.fSymbolCharsOffset] = (char)n;
            }
            final SymbolCache symbolCache5 = symbolCache;
            ++symbolCache5.fSymbolCharsOffset;
        }
        array[i + 2] = -1;
        return this.fStringPool.createNonMatchingSymbol(fSymbolCharsOffset, n4, array, i);
    }
    
    private int recognizeMarkup(int n, final QName qName) throws Exception {
        switch (n) {
            case 0: {
                return 11;
            }
            case 63: {
                ++super.fCharacterCounter;
                this.loadNextByte();
                return 0;
            }
            case 33: {
                ++super.fCharacterCounter;
                n = this.loadNextByte();
                if (n == 0) {
                    --super.fCharacterCounter;
                    --super.fCurrentOffset;
                    return 11;
                }
                if (n == 45) {
                    ++super.fCharacterCounter;
                    n = this.loadNextByte();
                    if (n == 0) {
                        super.fCharacterCounter -= 2;
                        super.fCurrentOffset -= 2;
                        return 11;
                    }
                    if (n == 45) {
                        ++super.fCharacterCounter;
                        n = this.loadNextByte();
                        return 1;
                    }
                }
                else if (n == 91) {
                    for (int i = 0; i < 6; ++i) {
                        ++super.fCharacterCounter;
                        n = this.loadNextByte();
                        if (n == 0) {
                            super.fCharacterCounter -= 2 + i;
                            super.fCurrentOffset -= 2 + i;
                            return 11;
                        }
                        if (n != UTF8Reader.cdata_string[i]) {
                            return 10;
                        }
                    }
                    ++super.fCharacterCounter;
                    this.loadNextByte();
                    return 2;
                }
                return 10;
            }
            case 47: {
                ++super.fCharacterCounter;
                ++super.fCurrentOffset;
                ++this.fCurrentIndex;
                try {
                    final int fMostRecentByte = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                    this.fMostRecentByte = fMostRecentByte;
                    n = fMostRecentByte;
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    n = this.slowLoadNextByte();
                }
                this.fStringPool.getCharArrayRange(qName.rawname, this.fCharArrayRange);
                final char[] chars = this.fCharArrayRange.chars;
                int offset = this.fCharArrayRange.offset;
                final int length = this.fCharArrayRange.length;
                if (n == chars[offset++]) {
                    final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
                    final int fCurrentIndex = this.fCurrentIndex;
                    final int fCurrentOffset = super.fCurrentOffset;
                    for (int j = 1; j < length; ++j) {
                        ++super.fCurrentOffset;
                        ++this.fCurrentIndex;
                        try {
                            final int fMostRecentByte2 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                            this.fMostRecentByte = fMostRecentByte2;
                            n = fMostRecentByte2;
                        }
                        catch (ArrayIndexOutOfBoundsException ex2) {
                            n = this.slowLoadNextByte();
                        }
                        if (n != chars[offset++]) {
                            this.fCurrentChunk = fCurrentChunk;
                            this.fCurrentIndex = fCurrentIndex;
                            super.fCurrentOffset = fCurrentOffset;
                            this.fMostRecentData = this.fCurrentChunk.toByteArray();
                            this.fMostRecentByte = (this.fMostRecentData[fCurrentIndex] & 0xFF);
                            return 4;
                        }
                    }
                    super.fCharacterCounter += length;
                    ++super.fCharacterCounter;
                    ++super.fCurrentOffset;
                    ++this.fCurrentIndex;
                    try {
                        final int fMostRecentByte3 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                        this.fMostRecentByte = fMostRecentByte3;
                        n = fMostRecentByte3;
                    }
                    catch (ArrayIndexOutOfBoundsException ex3) {
                        n = this.slowLoadNextByte();
                    }
                    if (n == 62) {
                        ++super.fCharacterCounter;
                        ++super.fCurrentOffset;
                        ++this.fCurrentIndex;
                        try {
                            this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                        }
                        catch (ArrayIndexOutOfBoundsException ex4) {
                            this.slowLoadNextByte();
                        }
                        return 5;
                    }
                    while (n == 32 || n == 9 || n == 10 || n == 13) {
                        if (n == 10) {
                            ++super.fLinefeedCounter;
                            super.fCharacterCounter = 1;
                            n = this.loadNextByte();
                        }
                        else if (n == 13) {
                            ++super.fCarriageReturnCounter;
                            super.fCharacterCounter = 1;
                            n = this.loadNextByte();
                            if (n == 10) {
                                ++super.fLinefeedCounter;
                                n = this.loadNextByte();
                            }
                        }
                        else {
                            ++super.fCharacterCounter;
                            n = this.loadNextByte();
                        }
                        if (n == 62) {
                            ++super.fCharacterCounter;
                            ++super.fCurrentOffset;
                            ++this.fCurrentIndex;
                            try {
                                this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                            }
                            catch (ArrayIndexOutOfBoundsException ex5) {
                                this.slowLoadNextByte();
                            }
                            return 5;
                        }
                    }
                    this.fCurrentChunk = fCurrentChunk;
                    this.fCurrentIndex = fCurrentIndex;
                    super.fCurrentOffset = fCurrentOffset;
                    this.fMostRecentData = this.fCurrentChunk.toByteArray();
                    this.fMostRecentByte = (this.fMostRecentData[fCurrentIndex] & 0xFF);
                }
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
            this.loadNextByte();
            return 7;
        }
        return 8;
    }
    
    public int scanContent(final QName qName) throws Exception {
        if (this.fCallClearPreviousChunk && this.fCurrentChunk.clearPreviousChunk()) {
            this.fCallClearPreviousChunk = false;
        }
        this.fCharDataLength = 0;
        final int fCurrentOffset = super.fCurrentOffset;
        int n = this.fMostRecentByte;
        if (n < 128) {
            switch (XMLCharacterProperties.fgAsciiWSCharData[n]) {
                case 0: {
                    if (super.fSendCharDataAsCharArray) {
                        try {
                            this.fCharacters[this.fCharDataLength] = (char)n;
                            ++this.fCharDataLength;
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {
                            this.slowAppendCharData(n);
                        }
                    }
                    ++super.fCharacterCounter;
                    ++super.fCurrentOffset;
                    ++this.fCurrentIndex;
                    try {
                        this.fMostRecentByte = (this.fMostRecentData[this.fCurrentIndex] & 0xFF);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        this.slowLoadNextByte();
                    }
                    break;
                }
                case 1: {
                    ++super.fCharacterCounter;
                    ++super.fCurrentOffset;
                    ++this.fCurrentIndex;
                    int slowLoadNextByte;
                    try {
                        final int fMostRecentByte = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                        this.fMostRecentByte = fMostRecentByte;
                        slowLoadNextByte = fMostRecentByte;
                    }
                    catch (ArrayIndexOutOfBoundsException ex3) {
                        slowLoadNextByte = this.slowLoadNextByte();
                    }
                    if (!super.fInCDSect) {
                        return this.recognizeMarkup(slowLoadNextByte, qName);
                    }
                    if (super.fSendCharDataAsCharArray) {
                        this.appendCharData(60);
                        break;
                    }
                    break;
                }
                case 2: {
                    ++super.fCharacterCounter;
                    final int loadNextByte = this.loadNextByte();
                    if (!super.fInCDSect) {
                        return this.recognizeReference(loadNextByte);
                    }
                    if (super.fSendCharDataAsCharArray) {
                        this.appendCharData(38);
                        break;
                    }
                    break;
                }
                case 3: {
                    ++super.fCharacterCounter;
                    if (this.loadNextByte() == 93) {
                        if (this.fCurrentIndex + 1 == 16384) {
                            final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
                            final int fCurrentIndex = this.fCurrentIndex;
                            final int fCurrentOffset2 = super.fCurrentOffset;
                            if (this.loadNextByte() != 62) {
                                this.fCurrentChunk = fCurrentChunk;
                                this.fCurrentIndex = fCurrentIndex;
                                super.fCurrentOffset = fCurrentOffset2;
                                this.fMostRecentData = this.fCurrentChunk.toByteArray();
                                this.fMostRecentByte = 93;
                                if (super.fSendCharDataAsCharArray) {
                                    this.appendCharData(93);
                                    break;
                                }
                                break;
                            }
                        }
                        else if (this.fMostRecentData[this.fCurrentIndex + 1] != 62) {
                            if (super.fSendCharDataAsCharArray) {
                                this.appendCharData(93);
                                break;
                            }
                            break;
                        }
                        else {
                            ++this.fCurrentIndex;
                            ++super.fCurrentOffset;
                        }
                        this.loadNextByte();
                        super.fCharacterCounter += 2;
                        return 3;
                    }
                    if (super.fSendCharDataAsCharArray) {
                        this.appendCharData(93);
                        break;
                    }
                    break;
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
                        else if (n == 13) {
                            ++super.fCarriageReturnCounter;
                            super.fCharacterCounter = 1;
                            ++super.fCurrentOffset;
                            ++this.fCurrentIndex;
                            try {
                                final int fMostRecentByte2 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                                this.fMostRecentByte = fMostRecentByte2;
                                n = fMostRecentByte2;
                            }
                            catch (ArrayIndexOutOfBoundsException ex4) {
                                n = this.slowLoadNextByte();
                            }
                            if (n != 10) {
                                if (super.fSendCharDataAsCharArray) {
                                    this.appendCharData(10);
                                }
                                if (n == 32 || n == 9) {
                                    continue;
                                }
                                if (n == 13) {
                                    continue;
                                }
                                break;
                            }
                            else {
                                ++super.fLinefeedCounter;
                            }
                        }
                        else {
                            ++super.fCharacterCounter;
                        }
                        if (super.fSendCharDataAsCharArray) {
                            try {
                                this.fCharacters[this.fCharDataLength] = (char)n;
                                ++this.fCharDataLength;
                            }
                            catch (ArrayIndexOutOfBoundsException ex5) {
                                this.slowAppendCharData(n);
                            }
                        }
                        ++super.fCurrentOffset;
                        ++this.fCurrentIndex;
                        try {
                            final int fMostRecentByte3 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                            this.fMostRecentByte = fMostRecentByte3;
                            n = fMostRecentByte3;
                        }
                        catch (ArrayIndexOutOfBoundsException ex6) {
                            n = this.slowLoadNextByte();
                        }
                    } while (n == 32 || n == 9 || n == 10 || n == 13);
                    if (n < 128) {
                        switch (XMLCharacterProperties.fgAsciiCharData[n]) {
                            case 0: {
                                if (super.fSendCharDataAsCharArray) {
                                    this.appendCharData(n);
                                }
                                ++super.fCharacterCounter;
                                this.loadNextByte();
                                break;
                            }
                            case 1: {
                                if (!super.fInCDSect) {
                                    if (super.fSendCharDataAsCharArray) {
                                        super.fCharDataHandler.processWhitespace(this.fCharacters, 0, this.fCharDataLength);
                                    }
                                    else {
                                        super.fCharDataHandler.processWhitespace(this.addString(fCurrentOffset, super.fCurrentOffset - fCurrentOffset));
                                    }
                                    ++super.fCharacterCounter;
                                    ++super.fCurrentOffset;
                                    ++this.fCurrentIndex;
                                    int slowLoadNextByte2;
                                    try {
                                        final int fMostRecentByte4 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                                        this.fMostRecentByte = fMostRecentByte4;
                                        slowLoadNextByte2 = fMostRecentByte4;
                                    }
                                    catch (ArrayIndexOutOfBoundsException ex7) {
                                        slowLoadNextByte2 = this.slowLoadNextByte();
                                    }
                                    return this.recognizeMarkup(slowLoadNextByte2, qName);
                                }
                                if (super.fSendCharDataAsCharArray) {
                                    this.appendCharData(60);
                                }
                                ++super.fCharacterCounter;
                                this.loadNextByte();
                                break;
                            }
                            case 2: {
                                if (!super.fInCDSect) {
                                    this.whitespace(fCurrentOffset, super.fCurrentOffset);
                                    ++super.fCharacterCounter;
                                    return this.recognizeReference(this.loadNextByte());
                                }
                                if (super.fSendCharDataAsCharArray) {
                                    this.appendCharData(38);
                                }
                                ++super.fCharacterCounter;
                                this.loadNextByte();
                                break;
                            }
                            case 3: {
                                final int fCurrentOffset3 = super.fCurrentOffset;
                                if (this.loadNextByte() == 93) {
                                    if (this.fCurrentIndex + 1 == 16384) {
                                        final UTF8DataChunk fCurrentChunk2 = this.fCurrentChunk;
                                        final int fCurrentIndex2 = this.fCurrentIndex;
                                        final int fCurrentOffset4 = super.fCurrentOffset;
                                        if (this.loadNextByte() != 62) {
                                            this.fCurrentChunk = fCurrentChunk2;
                                            this.fCurrentIndex = fCurrentIndex2;
                                            super.fCurrentOffset = fCurrentOffset4;
                                            this.fMostRecentData = this.fCurrentChunk.toByteArray();
                                            this.fMostRecentByte = 93;
                                            ++super.fCharacterCounter;
                                            if (super.fSendCharDataAsCharArray) {
                                                this.appendCharData(93);
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                    else if (this.fMostRecentData[this.fCurrentIndex + 1] != 62) {
                                        ++super.fCharacterCounter;
                                        if (super.fSendCharDataAsCharArray) {
                                            this.appendCharData(93);
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        ++this.fCurrentIndex;
                                        ++super.fCurrentOffset;
                                    }
                                    this.loadNextByte();
                                    this.whitespace(fCurrentOffset, fCurrentOffset3);
                                    super.fCharacterCounter += 3;
                                    return 3;
                                }
                                ++super.fCharacterCounter;
                                if (super.fSendCharDataAsCharArray) {
                                    this.appendCharData(93);
                                    break;
                                }
                                break;
                            }
                            case 4: {
                                this.whitespace(fCurrentOffset, super.fCurrentOffset);
                                if (n == 0 && this.atEOF(super.fCurrentOffset + 1)) {
                                    this.changeReaders();
                                    return 9;
                                }
                                return 9;
                            }
                        }
                        break;
                    }
                    if (super.fSendCharDataAsCharArray) {
                        if (!this.copyMultiByteCharData(n)) {
                            this.whitespace(fCurrentOffset, super.fCurrentOffset);
                            return 9;
                        }
                        break;
                    }
                    else {
                        if (!this.skipMultiByteCharData(n)) {
                            this.whitespace(fCurrentOffset, super.fCurrentOffset);
                            return 9;
                        }
                        break;
                    }
                    break;
                }
            }
        }
        else if (super.fSendCharDataAsCharArray) {
            if (!this.copyMultiByteCharData(n)) {
                return 9;
            }
        }
        else if (!this.skipMultiByteCharData(n)) {
            return 9;
        }
        int n2;
        if (super.fSendCharDataAsCharArray) {
            n2 = this.copyAsciiCharData();
        }
        else {
            n2 = this.skipAsciiCharData();
        }
        Block_81: {
            Block_80: {
                Label_2119: {
                    int fCurrentOffset5 = 0;
                Label_1991:
                    while (true) {
                        if (n2 < 128) {
                            switch (XMLCharacterProperties.fgAsciiCharData[n2]) {
                                case 0: {
                                    if (super.fSendCharDataAsCharArray) {
                                        this.appendCharData(n2);
                                    }
                                    ++super.fCharacterCounter;
                                    n2 = this.loadNextByte();
                                    continue;
                                }
                                case 1: {
                                    if (!super.fInCDSect) {
                                        if (super.fSendCharDataAsCharArray) {
                                            super.fCharDataHandler.processCharacters(this.fCharacters, 0, this.fCharDataLength);
                                        }
                                        else {
                                            super.fCharDataHandler.processCharacters(this.addString(fCurrentOffset, super.fCurrentOffset - fCurrentOffset));
                                        }
                                        ++super.fCharacterCounter;
                                        ++super.fCurrentOffset;
                                        ++this.fCurrentIndex;
                                        int slowLoadNextByte3;
                                        try {
                                            final int fMostRecentByte5 = this.fMostRecentData[this.fCurrentIndex] & 0xFF;
                                            this.fMostRecentByte = fMostRecentByte5;
                                            slowLoadNextByte3 = fMostRecentByte5;
                                        }
                                        catch (ArrayIndexOutOfBoundsException ex8) {
                                            slowLoadNextByte3 = this.slowLoadNextByte();
                                        }
                                        return this.recognizeMarkup(slowLoadNextByte3, qName);
                                    }
                                    if (super.fSendCharDataAsCharArray) {
                                        this.appendCharData(60);
                                    }
                                    ++super.fCharacterCounter;
                                    n2 = this.loadNextByte();
                                    continue;
                                }
                                case 2: {
                                    if (!super.fInCDSect) {
                                        this.characters(fCurrentOffset, super.fCurrentOffset);
                                        ++super.fCharacterCounter;
                                        return this.recognizeReference(this.loadNextByte());
                                    }
                                    if (super.fSendCharDataAsCharArray) {
                                        this.appendCharData(38);
                                    }
                                    ++super.fCharacterCounter;
                                    n2 = this.loadNextByte();
                                    continue;
                                }
                                case 3: {
                                    fCurrentOffset5 = super.fCurrentOffset;
                                    n2 = this.loadNextByte();
                                    if (n2 != 93) {
                                        ++super.fCharacterCounter;
                                        if (super.fSendCharDataAsCharArray) {
                                            this.appendCharData(93);
                                            continue;
                                        }
                                        continue;
                                    }
                                    else if (this.fCurrentIndex + 1 == 16384) {
                                        final UTF8DataChunk fCurrentChunk3 = this.fCurrentChunk;
                                        final int fCurrentIndex3 = this.fCurrentIndex;
                                        final int fCurrentOffset6 = super.fCurrentOffset;
                                        if (this.loadNextByte() == 62) {
                                            break Label_1991;
                                        }
                                        this.fCurrentChunk = fCurrentChunk3;
                                        this.fCurrentIndex = fCurrentIndex3;
                                        super.fCurrentOffset = fCurrentOffset6;
                                        this.fMostRecentData = this.fCurrentChunk.toByteArray();
                                        this.fMostRecentByte = 93;
                                        ++super.fCharacterCounter;
                                        if (super.fSendCharDataAsCharArray) {
                                            this.appendCharData(93);
                                            continue;
                                        }
                                        continue;
                                    }
                                    else {
                                        if (this.fMostRecentData[this.fCurrentIndex + 1] == 62) {
                                            ++this.fCurrentIndex;
                                            ++super.fCurrentOffset;
                                            break Label_1991;
                                        }
                                        ++super.fCharacterCounter;
                                        if (super.fSendCharDataAsCharArray) {
                                            this.appendCharData(93);
                                            continue;
                                        }
                                        continue;
                                    }
                                    break;
                                }
                                case 4: {
                                    if (n2 == 10) {
                                        if (super.fSendCharDataAsCharArray) {
                                            this.appendCharData(n2);
                                        }
                                        ++super.fLinefeedCounter;
                                        super.fCharacterCounter = 1;
                                        n2 = this.loadNextByte();
                                        continue;
                                    }
                                    if (n2 != 13) {
                                        break Label_2119;
                                    }
                                    if (super.fSendCharDataAsCharArray) {
                                        this.appendCharData(10);
                                    }
                                    ++super.fCarriageReturnCounter;
                                    super.fCharacterCounter = 1;
                                    n2 = this.loadNextByte();
                                    if (n2 == 10) {
                                        ++super.fLinefeedCounter;
                                        n2 = this.loadNextByte();
                                        continue;
                                    }
                                    continue;
                                }
                            }
                        }
                        else {
                            if (super.fSendCharDataAsCharArray) {
                                if (!this.copyMultiByteCharData(n2)) {
                                    break Block_80;
                                }
                            }
                            else if (!this.skipMultiByteCharData(n2)) {
                                break Block_81;
                            }
                            n2 = this.fMostRecentByte;
                        }
                    }
                    this.loadNextByte();
                    this.characters(fCurrentOffset, fCurrentOffset5);
                    super.fCharacterCounter += 3;
                    return 3;
                }
                this.characters(fCurrentOffset, super.fCurrentOffset);
                if (n2 == 0 && this.atEOF(super.fCurrentOffset + 1)) {
                    this.changeReaders();
                    return 9;
                }
                return 9;
            }
            this.characters(fCurrentOffset, super.fCurrentOffset);
            return 9;
        }
        this.characters(fCurrentOffset, super.fCurrentOffset);
        return 9;
    }
    
    private boolean copyMultiByteCharData(final int fMostRecentByte) throws Exception {
        final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
        final int fCurrentOffset = super.fCurrentOffset;
        final int fCurrentIndex = this.fCurrentIndex;
        final int loadNextByte = this.loadNextByte();
        if ((0xE0 & fMostRecentByte) == 0xC0) {
            this.appendCharData(((0x1F & fMostRecentByte) << 6) + (0x3F & loadNextByte));
            this.loadNextByte();
            return true;
        }
        final int loadNextByte2 = this.loadNextByte();
        if ((0xF0 & fMostRecentByte) == 0xE0) {
            if ((fMostRecentByte == 237 && loadNextByte >= 160) || (fMostRecentByte == 239 && loadNextByte == 191 && loadNextByte2 >= 190)) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = fMostRecentByte;
                return false;
            }
            this.appendCharData(((0xF & fMostRecentByte) << 12) + ((0x3F & loadNextByte) << 6) + (0x3F & loadNextByte2));
            this.loadNextByte();
            return true;
        }
        else {
            final int loadNextByte3 = this.loadNextByte();
            if ((0xF8 & fMostRecentByte) != 0xF0) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = fMostRecentByte;
                return false;
            }
            if (fMostRecentByte > 244 || (fMostRecentByte == 244 && loadNextByte >= 144)) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = fMostRecentByte;
                return false;
            }
            final int n = ((0xF & fMostRecentByte) << 18) + ((0x3F & loadNextByte) << 12) + ((0x3F & loadNextByte2) << 6) + (0x3F & loadNextByte3);
            if (n < 65536) {
                this.appendCharData(n);
            }
            else {
                this.appendCharData((n - 65536 >> 10) + 55296);
                this.appendCharData((n - 65536 & 0x3FF) + 56320);
            }
            this.loadNextByte();
            return true;
        }
    }
    
    private boolean skipMultiByteCharData(final int n) throws Exception {
        final UTF8DataChunk fCurrentChunk = this.fCurrentChunk;
        final int fCurrentOffset = super.fCurrentOffset;
        final int fCurrentIndex = this.fCurrentIndex;
        final int loadNextByte = this.loadNextByte();
        if ((0xE0 & n) == 0xC0) {
            this.loadNextByte();
            return true;
        }
        final int loadNextByte2 = this.loadNextByte();
        if ((0xF0 & n) == 0xE0) {
            if ((n == 237 && loadNextByte >= 160) || (n == 239 && loadNextByte == 191 && loadNextByte2 >= 190)) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return false;
            }
            this.loadNextByte();
            return true;
        }
        else {
            this.loadNextByte();
            if (n > 244 || (n == 244 && loadNextByte >= 144)) {
                this.fCurrentChunk = fCurrentChunk;
                this.fCurrentIndex = fCurrentIndex;
                super.fCurrentOffset = fCurrentOffset;
                this.fMostRecentData = fCurrentChunk.toByteArray();
                this.fMostRecentByte = n;
                return false;
            }
            this.loadNextByte();
            return true;
        }
    }
    
    private int copyAsciiCharData() throws Exception {
        int fCurrentIndex = this.fCurrentIndex;
        int n = super.fCurrentOffset - fCurrentIndex;
        byte[] array = this.fMostRecentData;
        final int fCharDataLength = this.fCharDataLength;
        int n2 = 0;
        while (true) {
            int n3;
            try {
                n3 = (array[fCurrentIndex] & 0xFF);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                n += fCurrentIndex;
                this.slowLoadNextByte();
                fCurrentIndex = 0;
                array = this.fMostRecentData;
                n3 = (array[fCurrentIndex] & 0xFF);
            }
            if (n3 >= 128) {
                super.fCurrentOffset = n + fCurrentIndex;
                this.fCurrentIndex = fCurrentIndex;
                return this.fMostRecentByte = n3;
            }
            if (XMLCharacterProperties.fgAsciiCharData[n3] == 0) {
                ++super.fCharacterCounter;
                n2 = 0;
            }
            else if (n3 == 10) {
                ++super.fLinefeedCounter;
                if (n2 != 0) {
                    n2 = 0;
                    ++fCurrentIndex;
                    continue;
                }
                super.fCharacterCounter = 1;
            }
            else {
                if (n3 != 13) {
                    super.fCurrentOffset = n + fCurrentIndex;
                    this.fCurrentIndex = fCurrentIndex;
                    return this.fMostRecentByte = n3;
                }
                ++super.fCarriageReturnCounter;
                super.fCharacterCounter = 1;
                n2 = 1;
                n3 = 10;
            }
            ++fCurrentIndex;
            try {
                this.fCharacters[this.fCharDataLength] = (char)n3;
                ++this.fCharDataLength;
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                this.slowAppendCharData(n3);
            }
        }
    }
    
    private int skipAsciiCharData() throws Exception {
        int fCurrentIndex = this.fCurrentIndex;
        int n = super.fCurrentOffset - fCurrentIndex;
        byte[] array = this.fMostRecentData;
        while (true) {
            int n2;
            try {
                n2 = (array[fCurrentIndex] & 0xFF);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                n += fCurrentIndex;
                this.slowLoadNextByte();
                fCurrentIndex = 0;
                array = this.fMostRecentData;
                n2 = (array[fCurrentIndex] & 0xFF);
            }
            if (n2 >= 128) {
                super.fCurrentOffset = n + fCurrentIndex;
                this.fCurrentIndex = fCurrentIndex;
                return this.fMostRecentByte = n2;
            }
            if (XMLCharacterProperties.fgAsciiCharData[n2] == 0) {
                ++super.fCharacterCounter;
            }
            else if (n2 == 10) {
                ++super.fLinefeedCounter;
                super.fCharacterCounter = 1;
            }
            else {
                if (n2 != 13) {
                    super.fCurrentOffset = n + fCurrentIndex;
                    this.fCurrentIndex = fCurrentIndex;
                    return this.fMostRecentByte = n2;
                }
                ++super.fCarriageReturnCounter;
                super.fCharacterCounter = 1;
            }
            ++fCurrentIndex;
        }
    }
    
    private void appendCharData(final int n) throws Exception {
        try {
            this.fCharacters[this.fCharDataLength] = (char)n;
            ++this.fCharDataLength;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.slowAppendCharData(n);
        }
    }
    
    private void slowAppendCharData(final int n) throws Exception {
        this.characters(0, this.fCharDataLength);
        this.fCharDataLength = 0;
        this.fCharacters[this.fCharDataLength++] = (char)n;
    }
    
    private void characters(final int n, final int n2) throws Exception {
        if (!super.fSendCharDataAsCharArray) {
            super.fCharDataHandler.processCharacters(this.addString(n, n2 - n));
            return;
        }
        super.fCharDataHandler.processCharacters(this.fCharacters, 0, this.fCharDataLength);
    }
    
    private void whitespace(final int n, final int n2) throws Exception {
        if (!super.fSendCharDataAsCharArray) {
            super.fCharDataHandler.processWhitespace(this.addString(n, n2 - n));
            return;
        }
        super.fCharDataHandler.processWhitespace(this.fCharacters, 0, this.fCharDataLength);
    }
    
    private int fillCurrentChunk() throws Exception {
        byte[] byteArray = this.fCurrentChunk.toByteArray();
        if (this.fInputStream == null) {
            if (byteArray == null) {
                byteArray = new byte[] { 0 };
            }
            byteArray[0] = 0;
            this.fMostRecentData = byteArray;
            this.fCurrentIndex = 0;
            this.fCurrentChunk.setByteArray(this.fMostRecentData);
            return this.fMostRecentByte = (this.fMostRecentData[0] & 0xFF);
        }
        if (byteArray == null) {
            byteArray = new byte[16384];
        }
        int n = 0;
        int n2 = 16384;
        do {
            int read;
            try {
                read = this.fInputStream.read(byteArray, n, n2);
            }
            catch (IOException ex) {
                read = -1;
            }
            if (read == -1) {
                this.fInputStream.close();
                this.fInputStream = null;
                try {
                    byteArray[n] = 0;
                }
                catch (ArrayIndexOutOfBoundsException ex2) {}
                break;
            }
            if (read > 0) {
                n += read;
                n2 -= read;
            }
        } while (n2 > 0);
        this.fMostRecentData = byteArray;
        this.fLength += n;
        this.fCurrentIndex = 0;
        this.fCurrentChunk.setByteArray(this.fMostRecentData);
        return this.fMostRecentByte = (this.fMostRecentData[0] & 0xFF);
    }
    
    static {
        fgAsciiAttValueChar = new byte[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 1, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        fgAsciiEntityValueChar = new byte[] { 7, 4, 4, 4, 4, 4, 4, 4, 4, 0, 5, 4, 4, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 1, 0, 0, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        cdata_string = new char[] { 'C', 'D', 'A', 'T', 'A', '[' };
    }
}
