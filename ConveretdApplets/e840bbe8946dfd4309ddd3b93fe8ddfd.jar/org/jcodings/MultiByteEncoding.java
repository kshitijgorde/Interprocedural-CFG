// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

import org.jcodings.exception.EncodingException;
import org.jcodings.ascii.AsciiTables;

public abstract class MultiByteEncoding extends AbstractEncoding
{
    protected final int[] EncLen;
    protected static final int A = -1;
    protected static final int F = -2;
    protected final int[][] Trans;
    protected final int[] TransZero;
    
    protected MultiByteEncoding(final String name, final int minLength, final int maxLength, final int[] EncLen, final int[][] Trans, final short[] CTypeTable) {
        super(name, minLength, maxLength, CTypeTable);
        this.EncLen = EncLen;
        this.Trans = Trans;
        this.TransZero = (int[])((Trans != null) ? Trans[0] : null);
    }
    
    public int length(final byte c) {
        return this.EncLen[c & 0xFF];
    }
    
    protected final int missing(final int n) {
        return -1 - n;
    }
    
    protected final int missing(final int b, final int delta) {
        return this.missing(this.EncLen[b] - delta);
    }
    
    protected final int safeLengthForUptoFour(final byte[] bytes, final int p, final int end) {
        final int b = bytes[p] & 0xFF;
        final int s = this.TransZero[b];
        if (s < 0) {
            return (s == -1) ? 1 : -1;
        }
        return this.lengthForTwoUptoFour(bytes, p, end, b, s);
    }
    
    private int lengthForTwoUptoFour(final byte[] bytes, int p, final int end, final int b, int s) {
        if (++p == end) {
            return this.missing(b, 1);
        }
        s = this.Trans[s][bytes[p] & 0xFF];
        if (s < 0) {
            return (s == -1) ? 2 : -1;
        }
        return this.lengthForThreeUptoFour(bytes, p, end, b, s);
    }
    
    private int lengthForThreeUptoFour(final byte[] bytes, int p, final int end, final int b, int s) {
        if (++p == end) {
            return this.missing(b, 2);
        }
        s = this.Trans[s][bytes[p] & 0xFF];
        if (s < 0) {
            return (s == -1) ? 3 : -1;
        }
        if (++p == end) {
            return this.missing(b, 3);
        }
        s = this.Trans[s][bytes[p] & 0xFF];
        return (s == -1) ? 4 : -1;
    }
    
    protected final int safeLengthForUptoThree(final byte[] bytes, final int p, final int end) {
        final int b = bytes[p] & 0xFF;
        final int s = this.TransZero[b];
        if (s < 0) {
            return (s == -1) ? 1 : -1;
        }
        return this.lengthForTwoUptoThree(bytes, p, end, b, s);
    }
    
    private int lengthForTwoUptoThree(final byte[] bytes, int p, final int end, final int b, int s) {
        if (++p == end) {
            return this.missing(b, 1);
        }
        s = this.Trans[s][bytes[p] & 0xFF];
        if (s < 0) {
            return (s == -1) ? 2 : -1;
        }
        return this.lengthForThree(bytes, p, end, b, s);
    }
    
    private int lengthForThree(final byte[] bytes, int p, final int end, final int b, int s) {
        if (++p == end) {
            return this.missing(b, 2);
        }
        s = this.Trans[s][bytes[p] & 0xFF];
        return (s == -1) ? 3 : -1;
    }
    
    protected final int safeLengthForUptoTwo(final byte[] bytes, final int p, final int end) {
        final int b = bytes[p] & 0xFF;
        final int s = this.TransZero[b];
        if (s < 0) {
            return (s == -1) ? 1 : -1;
        }
        return this.lengthForTwo(bytes, p, end, b, s);
    }
    
    private int lengthForTwo(final byte[] bytes, int p, final int end, final int b, int s) {
        if (++p == end) {
            return this.missing(b, 1);
        }
        s = this.Trans[s][bytes[p] & 0xFF];
        return (s == -1) ? 2 : -1;
    }
    
    protected final int mbnMbcToCode(final byte[] bytes, int p, final int end) {
        final int len = this.length(bytes, p, end);
        int n = bytes[p++] & 0xFF;
        if (len == 1) {
            return n;
        }
        int c;
        for (int i = 1; i < len && p < end; c = (bytes[p++] & 0xFF), n <<= 8, n += c, ++i) {}
        return n;
    }
    
    protected final int mbnMbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] lower) {
        int p = pp.value;
        int lowerP = 0;
        if (Encoding.isAscii(bytes[p] & 0xFF)) {
            lower[lowerP] = AsciiTables.ToLowerCaseTable[bytes[p] & 0xFF];
            ++pp.value;
            return 1;
        }
        final int len = this.length(bytes, p, end);
        for (int i = 0; i < len; ++i) {
            lower[lowerP++] = bytes[p++];
        }
        pp.value += len;
        return len;
    }
    
    protected final int mb2CodeToMbcLength(final int code) {
        return ((code & 0xFF00) != 0x0) ? 2 : 1;
    }
    
    protected final int mb4CodeToMbcLength(final int code) {
        if ((code & 0xFF000000) != 0x0) {
            return 4;
        }
        if ((code & 0xFF0000) != 0x0) {
            return 3;
        }
        if ((code & 0xFF00) != 0x0) {
            return 2;
        }
        return 1;
    }
    
    protected final int mb2CodeToMbc(final int code, final byte[] bytes, final int p) {
        int p_ = p;
        if ((code & 0xFF00) != 0x0) {
            bytes[p_++] = (byte)(code >>> 8 & 0xFF);
        }
        bytes[p_++] = (byte)(code & 0xFF);
        if (this.length(bytes, p, p_) != p_ - p) {
            throw new EncodingException("invalid code point value");
        }
        return p_ - p;
    }
    
    protected final int mb4CodeToMbc(final int code, final byte[] bytes, final int p) {
        int p_ = p;
        if ((code & 0xFF000000) != 0x0) {
            bytes[p_++] = (byte)(code >>> 24 & 0xFF);
        }
        if ((code & 0xFF0000) != 0x0 || p_ != p) {
            bytes[p_++] = (byte)(code >>> 16 & 0xFF);
        }
        if ((code & 0xFF00) != 0x0 || p_ != p) {
            bytes[p_++] = (byte)(code >>> 8 & 0xFF);
        }
        bytes[p_++] = (byte)(code & 0xFF);
        if (this.length(bytes, p, p_) != p_ - p) {
            throw new EncodingException("invalid code point value");
        }
        return p_ - p;
    }
    
    protected final boolean mb2IsCodeCType(final int code, final int ctype) {
        if (code < 128) {
            return this.isCodeCTypeInternal(code, ctype);
        }
        return Encoding.isWordGraphPrint(ctype) && this.codeToMbcLength(code) > 1;
    }
    
    protected final boolean mb4IsCodeCType(final int code, final int ctype) {
        return this.mb2IsCodeCType(code, ctype);
    }
    
    public int strLength(final byte[] bytes, final int p, final int end) {
        int n = 0;
        for (int q = p; q < end; q += this.length(bytes, q, end), ++n) {}
        return n;
    }
    
    public int strCodeAt(final byte[] bytes, final int p, final int end, final int index) {
        for (int n = 0, q = p; q < end; q += this.length(bytes, q, end), ++n) {
            if (n == index) {
                return this.mbcToCode(bytes, q, end);
            }
        }
        return -1;
    }
}
