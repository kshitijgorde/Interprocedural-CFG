// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.text.ParsePosition;
import java.text.FieldPosition;
import java.text.Format;

public class TextFormat extends Format
{
    public static char NOT_A_CHAR;
    static final String TRACE = "TextFormat";
    static final byte LITERAL_CHAR = 1;
    static final byte OPTIONAL_CHAR = 2;
    static final byte PASSWORD_CHAR = 4;
    private String pattern;
    boolean rightToLeft;
    boolean injectLiterals;
    char[] litMask;
    byte[] infoMask;
    int charCount;
    int litCount;
    char fillChar;
    char replaceChar;
    boolean explicitFillChar;
    
    public TextFormat() {
        this.applyPattern(null);
    }
    
    public TextFormat(final String pattern) {
        this();
        this.applyPattern(pattern);
    }
    
    public void setFillCharacter(final char c) {
        if (c == TextFormat.NOT_A_CHAR) {
            this.fillChar = ' ';
            this.explicitFillChar = false;
        }
        else {
            this.fillChar = c;
            this.explicitFillChar = true;
        }
    }
    
    public char getFillCharacter() {
        return this.explicitFillChar ? this.fillChar : TextFormat.NOT_A_CHAR;
    }
    
    public void setReplaceCharacter(final char c) {
        this.replaceChar = c;
    }
    
    public char getReplaceCharacter() {
        return this.replaceChar;
    }
    
    public void setKeepLiterals(final boolean tf) {
        this.injectLiterals = !tf;
    }
    
    public boolean getKeepLiterals() {
        return !this.injectLiterals;
    }
    
    public void applyPattern(final String pattern) {
        this.pattern = ((pattern != null) ? new String(pattern) : null);
        this.decomposePattern();
    }
    
    public String toPattern() {
        return (this.pattern == null) ? null : this.pattern.toString();
    }
    
    private void decomposePattern() {
        this.charCount = 0;
        this.litCount = 0;
        this.rightToLeft = false;
        this.injectLiterals = false;
        this.fillChar = ' ';
        this.explicitFillChar = false;
        this.replaceChar = '\0';
        this.litMask = null;
        if (this.pattern == null) {
            return;
        }
        int subRegion = 0;
        final int iLen = this.pattern.length();
        final FastStringBuffer fsb = new FastStringBuffer(this.pattern);
        int passwordCount = 0;
        for (char c = fsb.firstChar(); c != '\0'; c = fsb.nextChar()) {
            switch (c) {
                case ';': {
                    ++subRegion;
                    if (fsb.offset() > iLen - 1) {
                        c = '\0';
                    }
                    else {
                        c = fsb.peekNextChar();
                    }
                    if (c == ';') {
                        c = '\0';
                    }
                    else {
                        c = fsb.nextChar();
                    }
                    if (c == '\\') {
                        c = fsb.parseBackSlash();
                    }
                    switch (subRegion) {
                        case 1: {
                            if (c == '0') {
                                this.injectLiterals = true;
                            }
                            continue;
                        }
                        case 2: {
                            this.fillChar = c;
                            this.explicitFillChar = true;
                            continue;
                        }
                        case 3: {
                            this.replaceChar = c;
                            break;
                        }
                    }
                    fsb.lastChar();
                    break;
                }
                case '!': {
                    this.rightToLeft = true;
                }
                case '<':
                case '>':
                case '^':
                case '{':
                case '}': {
                    break;
                }
                case '\'':
                case '*': {
                    final FastStringBuffer fsb2 = fsb.parseLiteral(c, c == '\'');
                    if (c == '*') {
                        ++passwordCount;
                        this.charCount += fsb2.length();
                    }
                    else {
                        this.litCount += fsb2.length();
                    }
                    break;
                }
                case '\\': {
                    fsb.parseBackSlash();
                    ++this.litCount;
                    break;
                }
                case '#':
                case '&':
                case '0':
                case '9':
                case '?':
                case 'A':
                case 'C':
                case 'L':
                case 'a':
                case 'c':
                case 'l': {
                    ++this.charCount;
                    break;
                }
                default: {
                    ++this.litCount;
                    break;
                }
            }
        }
        if (this.injectLiterals) {}
        if (this.rightToLeft) {}
        if (this.litCount == 0 && passwordCount == 0) {
            return;
        }
        int fillPos = 0;
        this.litMask = new char[this.charCount + this.litCount];
        this.infoMask = new byte[this.charCount + this.litCount];
        for (int i = 0; i < this.litMask.length; ++i) {
            this.litMask[i] = TextFormat.NOT_A_CHAR;
            this.infoMask[i] = 0;
        }
        byte infoBits = 0;
        for (char c = fsb.firstChar(); c != '\0'; c = fsb.nextChar()) {
            switch (c) {
                case '!':
                case '<':
                case '>':
                case '^': {
                    break;
                }
                case '{': {
                    infoBits |= 0x2;
                    break;
                }
                case '}': {
                    infoBits &= 0xFFFFFFFD;
                    break;
                }
                case '*': {
                    infoBits ^= 0x4;
                    break;
                }
                case '\'': {
                    final FastStringBuffer fsb2 = fsb.parseLiteral(c, c == '\'');
                    System.arraycopy(fsb2.value(), 0, this.litMask, fillPos, fsb2.length());
                    for (int j = 0; j < fsb2.length(); ++j) {
                        this.infoMask[fillPos++] = 1;
                    }
                    break;
                }
                case '\\': {
                    this.infoMask[fillPos] = 1;
                    this.litMask[fillPos++] = fsb.parseBackSlash();
                    break;
                }
                case '0':
                case 'A':
                case 'L':
                case 'c': {
                    this.infoMask[fillPos++] = infoBits;
                    break;
                }
                case '#':
                case '&':
                case '9':
                case '?':
                case 'C':
                case 'a':
                case 'l': {
                    this.infoMask[fillPos++] = (byte)((infoBits & 0x4) | 0x2);
                    break;
                }
                case ';': {
                    fsb.lastChar();
                    break;
                }
                default: {
                    this.infoMask[fillPos] = 1;
                    this.litMask[fillPos++] = c;
                    break;
                }
            }
        }
    }
    
    public StringBuffer format(final String toBeFormatted, StringBuffer result, final FieldPosition pos) {
        final int maxChars = this.charCount + this.litCount;
        if (maxChars == 0) {
            result = new StringBuffer(toBeFormatted);
            return result;
        }
        int iLen = toBeFormatted.length();
        final char[] buffer = new char[iLen];
        toBeFormatted.getChars(0, iLen, buffer, 0);
        result.setLength(0);
        int incr = 1;
        int i = 0;
        int srcPos = 0;
        int nRemaining = iLen;
        if (this.rightToLeft) {
            incr = -1;
            srcPos = iLen - 1;
            i = maxChars - 1;
        }
        final char[] fillBuffer = new char[maxChars];
        if (this.litMask != null) {}
        int fillPos = i;
        while (i >= 0 && i < maxChars) {
            if (nRemaining <= 0) {
                if (this.isLiteral(i)) {
                    fillBuffer[fillPos] = this.litMask[i];
                    fillPos += incr;
                }
                else if (this.replaceChar != '\0') {
                    fillBuffer[fillPos] = this.replaceChar;
                    fillPos += incr;
                }
            }
            else if (this.isLiteral(i)) {
                if (this.injectLiterals) {
                    fillBuffer[fillPos] = this.litMask[i];
                    fillPos += incr;
                }
                else if (buffer[srcPos] != this.litMask[i] && (this.infoMask[i] & 0x4) == 0x0) {
                    if (this.replaceChar != '\0') {
                        fillBuffer[fillPos] = this.replaceChar;
                        fillPos += incr;
                    }
                }
                else {
                    if (this.isPassword(i)) {
                        fillBuffer[fillPos] = '*';
                    }
                    else {
                        fillBuffer[fillPos] = buffer[srcPos];
                    }
                    fillPos += incr;
                    srcPos += incr;
                    --nRemaining;
                }
            }
            else {
                final boolean spotForChar = this.spotForCharacter(i);
                if (spotForChar && buffer[srcPos] == this.fillChar) {
                    if (this.replaceChar != '\0') {
                        fillBuffer[fillPos] = this.replaceChar;
                        fillPos += incr;
                    }
                    srcPos += incr;
                    --nRemaining;
                }
                else {
                    char nextLit = '\0';
                    if (spotForChar && this.litMask != null) {
                        for (int jj = i + incr; jj >= 0 && jj < maxChars; jj += incr) {
                            if (this.litMask[jj] != TextFormat.NOT_A_CHAR && (this.infoMask[jj] & 0x4) == 0x0) {
                                nextLit = this.litMask[jj];
                                break;
                            }
                        }
                    }
                    if (nextLit != '\0' && buffer[srcPos] == nextLit) {
                        if (this.replaceChar != '\0') {
                            fillBuffer[fillPos] = this.replaceChar;
                            fillPos += incr;
                        }
                    }
                    else {
                        if (this.litMask != null && (this.infoMask[i] & 0x4) != 0x0) {
                            fillBuffer[fillPos] = '*';
                        }
                        else {
                            fillBuffer[fillPos] = buffer[srcPos];
                            srcPos += incr;
                        }
                        fillPos += incr;
                        --nRemaining;
                    }
                }
            }
            i += incr;
        }
        i = 0;
        if (this.rightToLeft) {
            i = fillPos;
        }
        if (i < 0) {
            i = 0;
        }
        if (i >= maxChars) {
            i = maxChars - 1;
        }
        if (this.rightToLeft) {
            i = fillPos + 1;
            iLen = maxChars - fillPos - 1;
        }
        else {
            i = 0;
            iLen = fillPos;
        }
        result.append(fillBuffer, i, iLen);
        return result;
    }
    
    public StringBuffer parse(final String text, final ParsePosition pos) {
        final int maxChars = this.charCount + this.litCount;
        if (maxChars == 0) {
            return new StringBuffer(text);
        }
        final int iLen = text.length();
        final char[] buffer = new char[iLen];
        text.getChars(0, iLen, buffer, 0);
        final char[] fillBuffer = new char[maxChars];
        int nFilled = 0;
        int fillPos = 0;
        if (!this.injectLiterals || this.litCount == 0) {
            for (int i = 0; i < maxChars; ++i) {
                if (this.litMask != null && this.litMask[i] != TextFormat.NOT_A_CHAR && (this.infoMask[i] & 0x4) == 0x0) {
                    fillBuffer[fillPos++] = this.litMask[i];
                }
                else {
                    char c = (i < iLen) ? buffer[i] : this.fillChar;
                    if (c == this.fillChar) {
                        c = this.replaceChar;
                        if (c == '\0') {
                            continue;
                        }
                    }
                    fillBuffer[fillPos++] = c;
                }
            }
            final StringBuffer result = new StringBuffer(fillPos);
            result.append(fillBuffer, 0, fillPos);
            return result;
        }
        int i = 0;
        int incr = 1;
        int litPos = 0;
        int nRemaining = iLen;
        if (this.rightToLeft) {
            i = iLen - 1;
            incr = -1;
            fillPos = maxChars - 1;
            litPos = maxChars - 1;
        }
        while (i >= 0 && i < iLen) {
            Label_0396: {
                if (buffer[i] != this.fillChar) {
                    if (this.litMask[litPos] == TextFormat.NOT_A_CHAR || (this.infoMask[litPos] & 0x4) != 0x0) {
                        char c = buffer[i];
                        --nRemaining;
                        if (c == this.fillChar) {
                            c = this.replaceChar;
                            if (c == '\0') {
                                break Label_0396;
                            }
                        }
                        fillBuffer[fillPos] = c;
                        fillPos += incr;
                        ++nFilled;
                    }
                    else if (this.litMask[litPos] != buffer[i]) {
                        break;
                    }
                }
            }
            i += incr;
            litPos += incr;
        }
        final StringBuffer result = new StringBuffer(nFilled);
        if (this.rightToLeft) {
            result.append(fillBuffer, maxChars - nFilled, nFilled);
        }
        else {
            result.append(fillBuffer, 0, nFilled);
        }
        return result;
    }
    
    public final Object parseObject(final String source, final ParsePosition pos) {
        return this.parse(source, pos);
    }
    
    public final StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
        return this.format(obj.toString(), toAppendTo, pos);
    }
    
    private final boolean isLiteral(final int position) {
        return this.infoMask != null && position >= 0 && position < this.infoMask.length && (this.infoMask[position] & 0x1) != 0x0;
    }
    
    private final boolean isPassword(final int position) {
        return this.infoMask != null && position >= 0 && position < this.infoMask.length && (this.infoMask[position] & 0x4) != 0x0;
    }
    
    private final boolean isOptional(final int position) {
        return this.infoMask == null || (position < 0 || position >= this.infoMask.length) || (this.infoMask[position] & 0x2) != 0x0;
    }
    
    private final boolean spotForCharacter(final int position) {
        return this.infoMask == null || (this.infoMask[position] & 0x1) != 0x0;
    }
    
    static {
        TextFormat.NOT_A_CHAR = '\uffff';
    }
}
