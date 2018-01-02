// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import org.apache.xerces.utils.QName;
import org.apache.xerces.utils.XMLCharacterProperties;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.StringPool;

final class StringReader extends XMLEntityReader
{
    public static final byte[] fgAsciiEntityValueChar;
    private static final char[] cdata_string;
    private StringPool fStringPool;
    private String fData;
    private int fEndOffset;
    private boolean hadTrailingSpace;
    private boolean oweTrailingSpace;
    private int fMostRecentChar;
    private StringReader fNextFreeReader;
    private static StringReader fgFreeReaders;
    private boolean fCalledCharPropInit;
    static /* synthetic */ Class class$org$apache$xerces$readers$StringReader;
    
    public static StringReader createStringReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final int n, final int n2, final int n3, final StringPool stringPool, final boolean b2) {
        StringReader fgFreeReaders = null;
        final Class clazz = (StringReader.class$org$apache$xerces$readers$StringReader == null) ? (StringReader.class$org$apache$xerces$readers$StringReader = class$("org.apache.xerces.readers.StringReader")) : StringReader.class$org$apache$xerces$readers$StringReader;
        synchronized (clazz) {
            fgFreeReaders = StringReader.fgFreeReaders;
            if (fgFreeReaders == null) {
                return new StringReader(xmlEntityHandler, xmlErrorReporter, b, n, n2, n3, stringPool, b2);
            }
            StringReader.fgFreeReaders = fgFreeReaders.fNextFreeReader;
        }
        fgFreeReaders.init(xmlEntityHandler, xmlErrorReporter, b, n, n2, n3, stringPool, b2);
        return fgFreeReaders;
    }
    
    private StringReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final int n, final int n2, final int n3, final StringPool fStringPool, final boolean b2) {
        super(xmlEntityHandler, xmlErrorReporter, b, n, n2);
        this.fStringPool = null;
        this.fData = null;
        this.hadTrailingSpace = false;
        this.oweTrailingSpace = false;
        this.fNextFreeReader = null;
        this.fCalledCharPropInit = false;
        this.fStringPool = fStringPool;
        this.fData = this.fStringPool.toString(n3);
        super.fCurrentOffset = 0;
        this.fEndOffset = this.fData.length();
        if (b2) {
            this.fMostRecentChar = 32;
            --super.fCurrentOffset;
            final boolean b3 = true;
            this.hadTrailingSpace = b3;
            this.oweTrailingSpace = b3;
        }
        else {
            this.fMostRecentChar = ((this.fEndOffset == 0) ? -1 : this.fData.charAt(0));
        }
    }
    
    private void init(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final int n, final int n2, final int n3, final StringPool fStringPool, final boolean b2) {
        super.init(xmlEntityHandler, xmlErrorReporter, b, n, n2);
        this.fStringPool = fStringPool;
        this.fData = this.fStringPool.toString(n3);
        super.fCurrentOffset = 0;
        this.fEndOffset = this.fData.length();
        this.fNextFreeReader = null;
        if (b2) {
            this.fMostRecentChar = 32;
            --super.fCurrentOffset;
            final boolean b3 = true;
            this.hadTrailingSpace = b3;
            this.oweTrailingSpace = b3;
        }
        else {
            this.fMostRecentChar = ((this.fEndOffset == 0) ? -1 : this.fData.charAt(0));
            final boolean b4 = false;
            this.hadTrailingSpace = b4;
            this.oweTrailingSpace = b4;
        }
    }
    
    public int addString(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
        return this.fStringPool.addString(this.fData.substring(n, n + n2));
    }
    
    public int addSymbol(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
        return this.fStringPool.addSymbol(this.fData.substring(n, n + n2));
    }
    
    public void append(final XMLEntityHandler.CharBuffer charBuffer, int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            try {
                charBuffer.append(this.fData.charAt(n++));
            }
            catch (StringIndexOutOfBoundsException ex) {
                if (n != this.fEndOffset + 1 || !this.hadTrailingSpace) {
                    System.err.println("StringReader.append()");
                    throw ex;
                }
                charBuffer.append(' ');
            }
        }
    }
    
    private int loadNextChar() {
        if (++super.fCurrentOffset >= this.fEndOffset) {
            if (this.oweTrailingSpace) {
                this.oweTrailingSpace = false;
                this.fMostRecentChar = 32;
            }
            else {
                this.fMostRecentChar = -1;
            }
        }
        else {
            this.fMostRecentChar = this.fData.charAt(super.fCurrentOffset);
        }
        return this.fMostRecentChar;
    }
    
    public XMLEntityHandler.EntityReader changeReaders() throws Exception {
        final XMLEntityHandler.EntityReader changeReaders = super.changeReaders();
        final Class clazz = (StringReader.class$org$apache$xerces$readers$StringReader == null) ? (StringReader.class$org$apache$xerces$readers$StringReader = class$("org.apache.xerces.readers.StringReader")) : StringReader.class$org$apache$xerces$readers$StringReader;
        synchronized (clazz) {
            this.fNextFreeReader = StringReader.fgFreeReaders;
            StringReader.fgFreeReaders = this;
        }
        return changeReaders;
    }
    
    public boolean lookingAtChar(final char c, final boolean b) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar != c) {
            return fMostRecentChar == -1 && this.changeReaders().lookingAtChar(c, b);
        }
        if (b) {
            if (++super.fCurrentOffset >= this.fEndOffset) {
                if (this.oweTrailingSpace) {
                    this.oweTrailingSpace = false;
                    this.fMostRecentChar = 32;
                }
                else {
                    this.fMostRecentChar = -1;
                }
            }
            else {
                this.fMostRecentChar = this.fData.charAt(super.fCurrentOffset);
            }
        }
        return true;
    }
    
    public boolean lookingAtValidChar(final boolean b) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar < 55296) {
            if (fMostRecentChar < 32 && fMostRecentChar != 9 && fMostRecentChar != 10 && fMostRecentChar != 13) {
                return fMostRecentChar == -1 && this.changeReaders().lookingAtValidChar(b);
            }
            if (b) {
                if (++super.fCurrentOffset >= this.fEndOffset) {
                    if (this.oweTrailingSpace) {
                        this.oweTrailingSpace = false;
                        this.fMostRecentChar = 32;
                    }
                    else {
                        this.fMostRecentChar = -1;
                    }
                }
                else {
                    this.fMostRecentChar = this.fData.charAt(super.fCurrentOffset);
                }
            }
            return true;
        }
        else {
            if (fMostRecentChar > 65533) {
                return false;
            }
            if (fMostRecentChar < 56320) {
                if (super.fCurrentOffset + 1 >= this.fEndOffset) {
                    return false;
                }
                final char char1 = this.fData.charAt(super.fCurrentOffset + 1);
                if (char1 < '\udc00' || char1 >= '\ue000') {
                    return false;
                }
                if (!b) {
                    return true;
                }
                ++super.fCurrentOffset;
            }
            else if (fMostRecentChar < 57344) {
                return false;
            }
            if (b) {
                if (++super.fCurrentOffset >= this.fEndOffset) {
                    if (this.oweTrailingSpace) {
                        this.oweTrailingSpace = false;
                        this.fMostRecentChar = 32;
                    }
                    else {
                        this.fMostRecentChar = -1;
                    }
                }
                else {
                    this.fMostRecentChar = this.fData.charAt(super.fCurrentOffset);
                }
            }
            return true;
        }
    }
    
    public boolean lookingAtSpace(final boolean b) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar > 32) {
            return false;
        }
        if (fMostRecentChar == 32 || fMostRecentChar == 10 || fMostRecentChar == 13 || fMostRecentChar == 9) {
            if (b) {
                this.loadNextChar();
            }
            return true;
        }
        return fMostRecentChar == -1 && this.changeReaders().lookingAtSpace(b);
    }
    
    public void skipToChar(final char c) throws Exception {
        for (int i = this.fMostRecentChar; i != c; i = this.loadNextChar()) {
            if (i == -1) {
                this.changeReaders().skipToChar(c);
                return;
            }
        }
    }
    
    public void skipPastSpaces() throws Exception {
        int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar == -1) {
            this.changeReaders().skipPastSpaces();
            return;
        }
        while (fMostRecentChar <= 32 && (fMostRecentChar == 32 || fMostRecentChar == 10 || fMostRecentChar == 9 || fMostRecentChar == 13)) {
            if (++super.fCurrentOffset >= this.fEndOffset) {
                this.changeReaders().skipPastSpaces();
                return;
            }
            fMostRecentChar = this.fData.charAt(super.fCurrentOffset);
        }
        this.fMostRecentChar = fMostRecentChar;
    }
    
    public void skipPastName(final char c) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar < 128) {
            if (fMostRecentChar == -1 || XMLCharacterProperties.fgAsciiInitialNameChar[fMostRecentChar] == 0) {
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
            final int loadNextChar = this.loadNextChar();
            if (c == loadNextChar) {
                return;
            }
            if (loadNextChar < '\u0080') {
                if (loadNextChar == -1 || XMLCharacterProperties.fgAsciiNameChar[loadNextChar] == 0) {
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
                if (n == -1 || XMLCharacterProperties.fgAsciiNameChar[n] == 0) {
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
        }
    }
    
    public boolean skippedString(final char[] array) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar != array[0]) {
            return fMostRecentChar == -1 && this.changeReaders().skippedString(array);
        }
        if (super.fCurrentOffset + array.length > this.fEndOffset) {
            return false;
        }
        for (int i = 1; i < array.length; ++i) {
            if (this.fData.charAt(super.fCurrentOffset + i) != array[i]) {
                return false;
            }
        }
        super.fCurrentOffset += array.length - 1;
        this.loadNextChar();
        return true;
    }
    
    public int scanInvalidChar() throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar == -1) {
            return this.changeReaders().scanInvalidChar();
        }
        this.loadNextChar();
        return fMostRecentChar;
    }
    
    public int scanCharRef(final boolean b) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar == -1) {
            return this.changeReaders().scanCharRef(b);
        }
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
        boolean b2 = false;
        int loadNextChar;
        while (true) {
            loadNextChar = this.loadNextChar();
            if (loadNextChar == -1) {
                return -1;
            }
            if (b) {
                if (loadNextChar > 102) {
                    break;
                }
                if (XMLCharacterProperties.fgAsciiXDigitChar[loadNextChar] == 0) {
                    break;
                }
            }
            else {
                if (loadNextChar < 48) {
                    break;
                }
                if (loadNextChar > 57) {
                    break;
                }
            }
            if (b) {
                final int n3;
                n = (n << 4) + (n3 - (((n3 = loadNextChar) < 65) ? 48 : (((loadNextChar < 97) ? 65 : 97) - 10)));
            }
            else {
                n = n * 10 + (loadNextChar - 48);
            }
            if (n <= 1114111) {
                continue;
            }
            b2 = true;
            n = 0;
        }
        if (loadNextChar != 59) {
            return -1;
        }
        this.loadNextChar();
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
        int i = this.fMostRecentChar;
        while (i != -1) {
            if (i < 128) {
                Label_0122: {
                    switch (StringReader.fgAsciiEntityValueChar[i]) {
                        case 1: {
                            if (i != n) {
                                break Label_0122;
                            }
                            if (!b) {
                                return -1;
                            }
                            final int n2 = super.fCurrentOffset - fCurrentOffset;
                            final int n3 = (n2 == 0) ? 0 : this.addString(fCurrentOffset, n2);
                            this.loadNextChar();
                            return n3;
                        }
                        case 0: {
                            if (++super.fCurrentOffset < this.fEndOffset) {
                                final char char1 = this.fData.charAt(super.fCurrentOffset);
                                this.fMostRecentChar = char1;
                                i = char1;
                                continue;
                            }
                            if (this.oweTrailingSpace) {
                                this.oweTrailingSpace = false;
                                final int fMostRecentChar = 32;
                                this.fMostRecentChar = fMostRecentChar;
                                i = fMostRecentChar;
                                continue;
                            }
                            final int fMostRecentChar2 = -1;
                            this.fMostRecentChar = fMostRecentChar2;
                            i = fMostRecentChar2;
                            continue;
                        }
                        case 2: {
                            return -2;
                        }
                        case 3: {
                            return -3;
                        }
                        case 4: {
                            return -4;
                        }
                        default: {
                            continue;
                        }
                    }
                }
            }
            else if (i < 55296) {
                i = this.loadNextChar();
            }
            else {
                if (i < 57344 || (i > 65533 && (i < 65536 || i > 1114111))) {
                    return -4;
                }
                i = this.loadNextChar();
            }
        }
        this.changeReaders();
        return -5;
    }
    
    public boolean scanExpectedName(final char c, final StringPool.CharArrayRange charArrayRange) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar == -1) {
            return this.changeReaders().scanExpectedName(c, charArrayRange);
        }
        if (!this.fCalledCharPropInit) {
            XMLCharacterProperties.initCharFlags();
            this.fCalledCharPropInit = true;
        }
        final int fCurrentOffset = super.fCurrentOffset;
        if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x2) == 0x0) {
            return false;
        }
        int loadNextChar;
        do {
            loadNextChar = this.loadNextChar();
            if (c == loadNextChar) {
                break;
            }
            if (loadNextChar == -1) {
                break;
            }
        } while ((XMLCharacterProperties.fgCharFlags[loadNextChar] & 0x4) != 0x0);
        this.fStringPool.addSymbol(this.fData.substring(fCurrentOffset, super.fCurrentOffset));
        return true;
    }
    
    public void scanQName(final char c, final QName qName) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar == -1) {
            this.changeReaders().scanQName(c, qName);
            return;
        }
        if (!this.fCalledCharPropInit) {
            XMLCharacterProperties.initCharFlags();
            this.fCalledCharPropInit = true;
        }
        final int fCurrentOffset = super.fCurrentOffset;
        if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x2) == 0x0) {
            qName.clear();
            return;
        }
        int loadNextChar;
        do {
            loadNextChar = this.loadNextChar();
            if (c == loadNextChar) {
                break;
            }
            if (loadNextChar == -1) {
                break;
            }
        } while ((XMLCharacterProperties.fgCharFlags[loadNextChar] & 0x4) != 0x0);
        qName.clear();
        qName.rawname = this.fStringPool.addSymbol(this.fData.substring(fCurrentOffset, super.fCurrentOffset));
        final int index = this.fData.indexOf(58, fCurrentOffset);
        if (index != -1 && index < super.fCurrentOffset) {
            qName.prefix = this.fStringPool.addSymbol(this.fData.substring(fCurrentOffset, index));
            final int index2 = this.fData.indexOf(32, index + 1);
            String s;
            if (index2 != -1) {
                s = this.fData.substring(index + 1, index2);
                qName.localpart = this.fStringPool.addSymbol(s);
            }
            else {
                s = this.fData.substring(index + 1, this.fData.length());
                qName.localpart = this.fStringPool.addSymbol(s);
            }
            qName.localpart = this.fStringPool.addSymbol(s);
        }
        else {
            qName.localpart = qName.rawname;
        }
    }
    
    public int scanName(final char c) throws Exception {
        final int fMostRecentChar = this.fMostRecentChar;
        if (fMostRecentChar == -1) {
            return this.changeReaders().scanName(c);
        }
        if (!this.fCalledCharPropInit) {
            XMLCharacterProperties.initCharFlags();
            this.fCalledCharPropInit = true;
        }
        final int fCurrentOffset = super.fCurrentOffset;
        if ((XMLCharacterProperties.fgCharFlags[fMostRecentChar] & 0x2) == 0x0) {
            return -1;
        }
        while (++super.fCurrentOffset < this.fEndOffset) {
            final char char1 = this.fData.charAt(super.fCurrentOffset);
            this.fMostRecentChar = char1;
            final char c2 = char1;
            if (c != c2) {
                if ((XMLCharacterProperties.fgCharFlags[c2] & 0x4) != 0x0) {
                    continue;
                }
            }
            return this.fStringPool.addSymbol(this.fData.substring(fCurrentOffset, super.fCurrentOffset));
        }
        if (this.oweTrailingSpace) {
            this.oweTrailingSpace = false;
            this.fMostRecentChar = 32;
            return this.fStringPool.addSymbol(this.fData.substring(fCurrentOffset, super.fCurrentOffset));
        }
        this.fMostRecentChar = -1;
        return this.fStringPool.addSymbol(this.fData.substring(fCurrentOffset, super.fCurrentOffset));
    }
    
    private int recognizeMarkup(int n) throws Exception {
        if (n == -1) {
            return 11;
        }
        switch (n) {
            case 63: {
                this.loadNextChar();
                return 0;
            }
            case 33: {
                n = this.loadNextChar();
                if (n == -1) {
                    super.fCurrentOffset -= 2;
                    this.loadNextChar();
                    return 11;
                }
                if (n == 45) {
                    n = this.loadNextChar();
                    if (n == -1) {
                        super.fCurrentOffset -= 3;
                        this.loadNextChar();
                        return 11;
                    }
                    if (n == 45) {
                        this.loadNextChar();
                        return 1;
                    }
                }
                else if (n == 91) {
                    for (int i = 0; i < 6; ++i) {
                        n = this.loadNextChar();
                        if (n == -1) {
                            super.fCurrentOffset -= 3 + i;
                            this.loadNextChar();
                            return 11;
                        }
                        if (n != StringReader.cdata_string[i]) {
                            return 10;
                        }
                    }
                    this.loadNextChar();
                    return 2;
                }
                return 10;
            }
            case 47: {
                this.loadNextChar();
                return 4;
            }
            default: {
                return 6;
            }
        }
    }
    
    private int recognizeReference(final int n) throws Exception {
        if (n == -1) {
            return 11;
        }
        if (n == 35) {
            this.loadNextChar();
            return 7;
        }
        return 8;
    }
    
    public int scanContent(final QName qName) throws Exception {
        int i = this.fMostRecentChar;
        if (i == -1) {
            return this.changeReaders().scanContent(qName);
        }
        final int fCurrentOffset = super.fCurrentOffset;
        if (i < 128) {
            switch (XMLCharacterProperties.fgAsciiWSCharData[i]) {
                case 0: {
                    i = this.loadNextChar();
                    break;
                }
                case 1: {
                    i = this.loadNextChar();
                    if (!super.fInCDSect) {
                        return this.recognizeMarkup(i);
                    }
                    break;
                }
                case 2: {
                    i = this.loadNextChar();
                    if (!super.fInCDSect) {
                        return this.recognizeReference(i);
                    }
                    break;
                }
                case 3: {
                    i = this.loadNextChar();
                    if (i == 93 && super.fCurrentOffset + 1 < this.fEndOffset && this.fData.charAt(super.fCurrentOffset + 1) == '>') {
                        this.loadNextChar();
                        this.loadNextChar();
                        return 3;
                    }
                    break;
                }
                case 4: {
                    return 9;
                }
                case 5: {
                    do {
                        i = this.loadNextChar();
                        if (i == -1) {
                            this.callCharDataHandler(fCurrentOffset, this.fEndOffset, true);
                            return this.changeReaders().scanContent(qName);
                        }
                    } while (i == 32 || i == 10 || i == 13 || i == 9);
                    if (i < 128) {
                        switch (XMLCharacterProperties.fgAsciiCharData[i]) {
                            case 0: {
                                i = this.loadNextChar();
                                break;
                            }
                            case 1: {
                                i = this.loadNextChar();
                                if (!super.fInCDSect) {
                                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - 1, true);
                                    return this.recognizeMarkup(i);
                                }
                                break;
                            }
                            case 2: {
                                i = this.loadNextChar();
                                if (!super.fInCDSect) {
                                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - 1, true);
                                    return this.recognizeReference(i);
                                }
                                break;
                            }
                            case 3: {
                                i = this.loadNextChar();
                                if (i == 93 && super.fCurrentOffset + 1 < this.fEndOffset && this.fData.charAt(super.fCurrentOffset + 1) == '>') {
                                    this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - 1, true);
                                    this.loadNextChar();
                                    this.loadNextChar();
                                    return 3;
                                }
                                break;
                            }
                            case 4: {
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
                                return 9;
                            }
                        }
                        break;
                    }
                    if (i == 65534 || i == 65535) {
                        this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, true);
                        return 9;
                    }
                    i = this.loadNextChar();
                    break;
                }
            }
        }
        else {
            if (i == 65534 || i == 65535) {
                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
                return 9;
            }
            i = this.loadNextChar();
        }
        while (i != -1) {
            if (i < 128) {
                if (XMLCharacterProperties.fgAsciiCharData[i] == 0) {
                    i = this.loadNextChar();
                    continue;
                }
            }
            do {
                if (i < 128) {
                    switch (XMLCharacterProperties.fgAsciiCharData[i]) {
                        case 0: {
                            i = this.loadNextChar();
                            continue;
                        }
                        case 1: {
                            i = this.loadNextChar();
                            if (!super.fInCDSect) {
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - 1, false);
                                return this.recognizeMarkup(i);
                            }
                            continue;
                        }
                        case 2: {
                            i = this.loadNextChar();
                            if (!super.fInCDSect) {
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - 1, false);
                                return this.recognizeReference(i);
                            }
                            continue;
                        }
                        case 3: {
                            i = this.loadNextChar();
                            if (i == 93 && super.fCurrentOffset + 1 < this.fEndOffset && this.fData.charAt(super.fCurrentOffset + 1) == '>') {
                                this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset - 1, false);
                                this.loadNextChar();
                                this.loadNextChar();
                                return 3;
                            }
                            continue;
                        }
                        case 4: {
                            this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
                            return 9;
                        }
                    }
                }
                else {
                    if (i == 65534 || i == 65535) {
                        this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
                        return 9;
                    }
                    i = this.loadNextChar();
                }
            } while (i != -1);
            this.callCharDataHandler(fCurrentOffset, super.fCurrentOffset, false);
            return this.changeReaders().scanContent(qName);
        }
        this.callCharDataHandler(fCurrentOffset, this.fEndOffset, false);
        return this.changeReaders().scanContent(qName);
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
        if (b) {
            super.fCharDataHandler.processWhitespace(this.fData.toCharArray(), n, n3);
        }
        else {
            super.fCharDataHandler.processCharacters(this.fData.toCharArray(), n, n3);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        fgAsciiEntityValueChar = new byte[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 1, 0, 0, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        cdata_string = new char[] { 'C', 'D', 'A', 'T', 'A', '[' };
        StringReader.fgFreeReaders = null;
    }
}
