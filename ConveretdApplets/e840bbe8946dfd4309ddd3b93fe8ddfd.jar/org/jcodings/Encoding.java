// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

import org.jcodings.exception.InternalException;
import org.jcodings.ascii.AsciiTables;
import org.jcodings.exception.EncodingException;
import org.jcodings.util.BytesHash;
import java.nio.charset.Charset;

public abstract class Encoding implements Cloneable
{
    public static final int CHAR_INVALID = -1;
    private static int count;
    protected final int minLength;
    protected final int maxLength;
    protected final boolean isFixedWidth;
    protected final boolean isSingleByte;
    protected final boolean isDummy;
    protected final boolean isAsciiCompatible;
    protected byte[] name;
    protected int hashCode;
    private int index;
    protected Charset charset;
    public static final byte NEW_LINE = 10;
    
    protected Encoding(final String name, final int minLength, final int maxLength, final boolean isDummy) {
        this.charset = null;
        this.setName(name);
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.isFixedWidth = (minLength == maxLength);
        this.isSingleByte = (this.isFixedWidth && minLength == 1);
        this.index = Encoding.count++;
        this.isDummy = isDummy;
        this.isAsciiCompatible = (minLength == 1 && !isDummy);
    }
    
    protected Encoding(final String name, final int minLength, final int maxLength) {
        this(name, minLength, maxLength, false);
    }
    
    protected final void setName(final String name) {
        this.name = name.getBytes();
        this.hashCode = BytesHash.hashCode(this.name, 0, this.name.length);
    }
    
    protected final void setName(final byte[] name) {
        this.name = name;
        this.hashCode = BytesHash.hashCode(this.name, 0, this.name.length);
    }
    
    public final String toString() {
        return new String(this.name);
    }
    
    public final boolean equals(final Object other) {
        return this == other;
    }
    
    public final int hashCode() {
        return this.hashCode;
    }
    
    public final int getIndex() {
        return this.index;
    }
    
    public final byte[] getName() {
        return this.name;
    }
    
    public final boolean isDummy() {
        return this.isDummy;
    }
    
    public final boolean isAsciiCompatible() {
        return this.isAsciiCompatible;
    }
    
    public Charset getCharset() {
        if (!this.isDummy() && this.charset == null && this.getCharsetName() != null) {
            this.charset = Charset.forName(this.getCharsetName());
        }
        return this.charset;
    }
    
    public String getCharsetName() {
        return null;
    }
    
    public Encoding replicate(final byte[] name) {
        try {
            final Encoding clone = (Encoding)this.clone();
            clone.setName(name);
            clone.index = Encoding.count++;
            return clone;
        }
        catch (CloneNotSupportedException cnse) {
            throw new EncodingException("could not replicate <%n> encoding", new String(name));
        }
    }
    
    public abstract int length(final byte p0);
    
    public abstract int length(final byte[] p0, final int p1, final int p2);
    
    public final int maxLength() {
        return this.maxLength;
    }
    
    public final int maxLengthDistance() {
        return this.maxLength();
    }
    
    public final int minLength() {
        return this.minLength;
    }
    
    public abstract boolean isNewLine(final byte[] p0, final int p1, final int p2);
    
    public abstract int mbcToCode(final byte[] p0, final int p1, final int p2);
    
    public abstract int codeToMbcLength(final int p0);
    
    public abstract int codeToMbc(final int p0, final byte[] p1, final int p2);
    
    public abstract int mbcCaseFold(final int p0, final byte[] p1, final IntHolder p2, final int p3, final byte[] p4);
    
    public byte[] toLowerCaseTable() {
        return null;
    }
    
    public abstract void applyAllCaseFold(final int p0, final ApplyAllCaseFoldFunction p1, final Object p2);
    
    public abstract CaseFoldCodeItem[] caseFoldCodesByString(final int p0, final byte[] p1, final int p2, final int p3);
    
    public abstract int propertyNameToCType(final byte[] p0, final int p1, final int p2);
    
    public abstract boolean isCodeCType(final int p0, final int p1);
    
    public abstract int[] ctypeCodeRange(final int p0, final IntHolder p1);
    
    public abstract int leftAdjustCharHead(final byte[] p0, final int p1, final int p2, final int p3);
    
    public abstract boolean isReverseMatchAllowed(final byte[] p0, final int p1, final int p2);
    
    public final int rightAdjustCharHead(final byte[] bytes, final int p, final int s, final int end) {
        int p_ = this.leftAdjustCharHead(bytes, p, s, end);
        if (p_ < s) {
            p_ += this.length(bytes, p_, end);
        }
        return p_;
    }
    
    public final int rightAdjustCharHeadWithPrev(final byte[] bytes, final int p, final int s, final int end, final IntHolder prev) {
        int p_ = this.leftAdjustCharHead(bytes, p, s, end);
        if (p_ < s) {
            if (prev != null) {
                prev.value = p_;
            }
            p_ += this.length(bytes, p_, end);
        }
        else if (prev != null) {
            prev.value = -1;
        }
        return p_;
    }
    
    public final int prevCharHead(final byte[] bytes, final int p, final int s, final int end) {
        if (s <= p) {
            return -1;
        }
        return this.leftAdjustCharHead(bytes, p, s - 1, end);
    }
    
    public final int stepBack(final byte[] bytes, final int p, int s, final int end, int n) {
        while (s != -1 && n-- > 0) {
            if (s <= p) {
                return -1;
            }
            s = this.leftAdjustCharHead(bytes, p, s - 1, end);
        }
        return s;
    }
    
    public final int step(final byte[] bytes, final int p, final int end, int n) {
        int q = p;
        while (n-- > 0) {
            q += this.length(bytes, q, end);
        }
        return (q <= end) ? q : -1;
    }
    
    public abstract int strLength(final byte[] p0, final int p1, final int p2);
    
    public abstract int strCodeAt(final byte[] p0, final int p1, final int p2, final int p3);
    
    public final int strLengthNull(final byte[] bytes, int p, final int end) {
        int n = 0;
        while (true) {
            if (bytes[p] == 0) {
                int len = this.minLength();
                if (len == 1) {
                    return n;
                }
                for (int q = p + 1; len > 1 && bytes[q] == 0; ++q, --len) {}
                if (len == 1) {
                    return n;
                }
            }
            p += this.length(bytes, p, end);
            ++n;
        }
    }
    
    public final int strByteLengthNull(final byte[] bytes, final int p, final int end) {
        int p_;
        final int start = p_ = 0;
        while (true) {
            if (bytes[p_] == 0) {
                int len = this.minLength();
                if (len == 1) {
                    return p_ - start;
                }
                int q = p_ + 1;
                while (len > 1) {
                    if (q >= bytes.length) {
                        return p_ - start;
                    }
                    if (bytes[q] != 0) {
                        break;
                    }
                    ++q;
                    --len;
                }
                if (len == 1) {
                    return p_ - start;
                }
            }
            p_ += this.length(bytes, p_, end);
        }
    }
    
    public final int strNCmp(final byte[] bytes, int p, final int end, final byte[] ascii, int asciiP, int n) {
        while (n-- > 0) {
            if (p >= end) {
                return ascii[asciiP];
            }
            final int c = this.mbcToCode(bytes, p, end);
            final int x = ascii[asciiP] - c;
            if (x != 0) {
                return x;
            }
            ++asciiP;
            p += this.length(bytes, p, end);
        }
        return 0;
    }
    
    public final boolean isNewLine(final int code) {
        return this.isCodeCType(code, 0);
    }
    
    public final boolean isGraph(final int code) {
        return this.isCodeCType(code, 5);
    }
    
    public final boolean isPrint(final int code) {
        return this.isCodeCType(code, 7);
    }
    
    public final boolean isAlnum(final int code) {
        return this.isCodeCType(code, 13);
    }
    
    public final boolean isAlpha(final int code) {
        return this.isCodeCType(code, 1);
    }
    
    public final boolean isLower(final int code) {
        return this.isCodeCType(code, 6);
    }
    
    public final boolean isUpper(final int code) {
        return this.isCodeCType(code, 10);
    }
    
    public final boolean isCntrl(final int code) {
        return this.isCodeCType(code, 3);
    }
    
    public final boolean isPunct(final int code) {
        return this.isCodeCType(code, 8);
    }
    
    public final boolean isSpace(final int code) {
        return this.isCodeCType(code, 9);
    }
    
    public final boolean isBlank(final int code) {
        return this.isCodeCType(code, 2);
    }
    
    public final boolean isDigit(final int code) {
        return this.isCodeCType(code, 4);
    }
    
    public final boolean isXDigit(final int code) {
        return this.isCodeCType(code, 11);
    }
    
    public final boolean isWord(final int code) {
        return this.isCodeCType(code, 12);
    }
    
    public final boolean isMbcWord(final byte[] bytes, final int p, final int end) {
        return this.isWord(this.mbcToCode(bytes, p, end));
    }
    
    public final boolean isSbWord(final int code) {
        return isAscii(code) && this.isWord(code);
    }
    
    public final boolean isMbcHead(final byte[] bytes, final int p, final int end) {
        return this.length(bytes, p, end) != 1;
    }
    
    public boolean isMbcCrnl(final byte[] bytes, final int p, final int end) {
        return this.mbcToCode(bytes, p, end) == 13 && this.isNewLine(bytes, p + this.length(bytes, p, end), end);
    }
    
    public static int digitVal(final int code) {
        return code - 48;
    }
    
    public static int odigitVal(final int code) {
        return digitVal(code);
    }
    
    public final int xdigitVal(final int code) {
        if (this.isDigit(code)) {
            return digitVal(code);
        }
        return this.isUpper(code) ? (code - 65 + 10) : (code - 97 + 10);
    }
    
    public static boolean isMbcAscii(final byte b) {
        return (b & 0xFF) < 128;
    }
    
    public static boolean isAscii(final int code) {
        return code < 128;
    }
    
    public static boolean isAscii(final byte b) {
        return b >= 0;
    }
    
    public static byte asciiToLower(final int c) {
        return AsciiTables.ToLowerCaseTable[c];
    }
    
    public static byte asciiToUpper(final int c) {
        return AsciiTables.ToUpperCaseTable[c];
    }
    
    public static boolean isWordGraphPrint(final int ctype) {
        return ctype == 12 || ctype == 5 || ctype == 7;
    }
    
    public final int mbcodeStartPosition() {
        return (this.minLength() > 1) ? 0 : 128;
    }
    
    public final boolean isSingleByte() {
        return this.isSingleByte;
    }
    
    public final boolean isFixedWidth() {
        return this.isFixedWidth;
    }
    
    public static Encoding load(final String name) {
        final String encClassName = "org.jcodings.specific." + name + "Encoding";
        Class<?> encClass;
        try {
            encClass = Class.forName(encClassName);
        }
        catch (ClassNotFoundException cnfe) {
            throw new InternalException("encoding class <%n> not found", encClassName);
        }
        try {
            return (Encoding)encClass.getField("INSTANCE").get(encClass);
        }
        catch (Exception e) {
            throw new InternalException("problem loading encoding <%n>", encClassName);
        }
    }
}
