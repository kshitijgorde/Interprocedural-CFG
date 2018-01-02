// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.ascii.AsciiTables;
import org.jcodings.Encoding;
import org.jcodings.IntHolder;
import org.jcodings.exception.EncodingException;
import org.jcodings.unicode.UnicodeEncoding;

abstract class BaseUTF8Encoding extends UnicodeEncoding
{
    static final boolean USE_INVALID_CODE_SCHEME = true;
    private static final int INVALID_CODE_FE = -2;
    private static final int INVALID_CODE_FF = -1;
    
    protected BaseUTF8Encoding(final int[] EncLen, final int[][] Trans) {
        super("UTF-8", 1, 6, EncLen, Trans);
    }
    
    public String getCharsetName() {
        return "UTF-8";
    }
    
    public boolean isNewLine(final byte[] bytes, final int p, final int end) {
        return p < end && bytes[p] == 10;
    }
    
    public int codeToMbcLength(final int code) {
        if ((code & 0xFFFFFF80) == 0x0) {
            return 1;
        }
        if ((code & 0xFFFFF800) == 0x0) {
            return 2;
        }
        if ((code & 0xFFFF0000) == 0x0) {
            return 3;
        }
        if ((code & 0xFFE00000) == 0x0) {
            return 4;
        }
        if ((code & 0xFC000000) == 0x0) {
            return 5;
        }
        if ((code & Integer.MIN_VALUE) == 0x0) {
            return 6;
        }
        if (code == -2) {
            return 1;
        }
        if (code == -1) {
            return 1;
        }
        throw new EncodingException("invalid code point value");
    }
    
    public int mbcToCode(final byte[] bytes, int p, final int end) {
        int len = this.length(bytes, p, end);
        int c = bytes[p++] & 0xFF;
        if (len > 1) {
            --len;
            int n = c & (1 << 6 - len) - 1;
            while (len-- != 0) {
                c = (bytes[p++] & 0xFF);
                n = (n << 6 | (c & 0x3F));
            }
            return n;
        }
        if (c > 253) {
            return (c == 254) ? -2 : -1;
        }
        return c;
    }
    
    static byte trailS(final int code, final int shift) {
        return (byte)((code >>> shift & 0x3F) | 0x80);
    }
    
    static byte trail0(final int code) {
        return (byte)((code & 0x3F) | 0x80);
    }
    
    public int codeToMbc(final int code, final byte[] bytes, final int p) {
        int p_ = p;
        if ((code & 0xFFFFFF80) == 0x0) {
            bytes[p_] = (byte)code;
            return 1;
        }
        if ((code & 0xFFFFF800) == 0x0) {
            bytes[p_++] = (byte)((code >>> 6 & 0x1F) | 0xC0);
        }
        else if ((code & 0xFFFF0000) == 0x0) {
            bytes[p_++] = (byte)((code >>> 12 & 0xF) | 0xE0);
            bytes[p_++] = trailS(code, 6);
        }
        else if ((code & 0xFFE00000) == 0x0) {
            bytes[p_++] = (byte)((code >>> 18 & 0x7) | 0xF0);
            bytes[p_++] = trailS(code, 12);
            bytes[p_++] = trailS(code, 6);
        }
        else if ((code & 0xFC000000) == 0x0) {
            bytes[p_++] = (byte)((code >>> 24 & 0x3) | 0xF8);
            bytes[p_++] = trailS(code, 18);
            bytes[p_++] = trailS(code, 12);
            bytes[p_++] = trailS(code, 6);
        }
        else if ((code & Integer.MIN_VALUE) == 0x0) {
            bytes[p_++] = (byte)((code >>> 30 & 0x1) | 0xFC);
            bytes[p_++] = trailS(code, 24);
            bytes[p_++] = trailS(code, 18);
            bytes[p_++] = trailS(code, 12);
            bytes[p_++] = trailS(code, 6);
        }
        else {
            if (code == -2) {
                bytes[p_] = -2;
                return 1;
            }
            if (code == -1) {
                bytes[p_] = -1;
                return 1;
            }
            throw new EncodingException("too big wide-char value");
        }
        bytes[p_++] = trail0(code);
        return p_ - p;
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] fold) {
        final int p = pp.value;
        final int foldP = 0;
        if (Encoding.isMbcAscii(bytes[p])) {
            fold[foldP] = AsciiTables.ToLowerCaseTable[bytes[p] & 0xFF];
            ++pp.value;
            return 1;
        }
        return super.mbcCaseFold(flag, bytes, pp, end, fold);
    }
    
    public int[] ctypeCodeRange(final int ctype, final IntHolder sbOut) {
        sbOut.value = 128;
        return super.ctypeCodeRange(ctype);
    }
    
    private static boolean utf8IsLead(final int c) {
        return (c & 0xC0 & 0xFF) != 0x80;
    }
    
    public int leftAdjustCharHead(final byte[] bytes, final int p, final int s, final int end) {
        if (s <= p) {
            return s;
        }
        int p_;
        for (p_ = s; !utf8IsLead(bytes[p_] & 0xFF) && p_ > p; --p_) {}
        return p_;
    }
    
    public boolean isReverseMatchAllowed(final byte[] bytes, final int p, final int end) {
        return true;
    }
}
