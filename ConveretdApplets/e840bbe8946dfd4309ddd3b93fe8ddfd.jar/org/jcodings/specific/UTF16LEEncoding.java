// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.ascii.AsciiTables;
import org.jcodings.Encoding;
import org.jcodings.IntHolder;
import org.jcodings.unicode.UnicodeEncoding;

public final class UTF16LEEncoding extends UnicodeEncoding
{
    public static final UTF16LEEncoding INSTANCE;
    
    protected UTF16LEEncoding() {
        super("UTF-16LE", 2, 4, UTF16BEEncoding.UTF16EncLen);
    }
    
    public int length(final byte c) {
        return this.EncLen[(c & 0xFF) + 1];
    }
    
    public int length(final byte[] bytes, final int p, final int end) {
        final int length = end - p;
        if (length < 2) {
            return this.missing(1);
        }
        final int b = bytes[p + 1] & 0xFF;
        if (!isSurrogate(b)) {
            return 2;
        }
        if (isSurrogateFirst(b)) {
            if (length < 4) {
                return this.missing(4 - length);
            }
            if (isSurrogateSecond(bytes[p + 3] & 0xFF)) {
                return 4;
            }
        }
        return -1;
    }
    
    public boolean isNewLine(final byte[] bytes, final int p, final int end) {
        return p + 1 < end && bytes[p] == 10 && bytes[p + 1] == 0;
    }
    
    public int mbcToCode(final byte[] bytes, final int p, final int end) {
        int code;
        if (isSurrogateFirst(bytes[p + 1] & 0xFF)) {
            code = ((((bytes[p + 1] & 0x27) << 2) + ((bytes[p + 0] & 0xFF & 0xC0) >> 6) + 1 << 16) + (((bytes[p + 0] & 0xFF & 0x3F) << 2) + (bytes[p + 2] & 0x23) << 8) + bytes[p + 3] & 0xFF);
        }
        else {
            code = (bytes[p + 1] & 0xFF) * 256 + (bytes[p + 0] & 0xFF);
        }
        return code;
    }
    
    public int codeToMbcLength(final int code) {
        return (code > 65535) ? 4 : 2;
    }
    
    public int codeToMbc(final int code, final byte[] bytes, final int p) {
        int p_ = p;
        if (code > 65535) {
            final int high = (code >>> 10) + 55232;
            final int low = (code & 0x3FF) + 56320;
            bytes[p_++] = (byte)(high & 0xFF);
            bytes[p_++] = (byte)(high >>> 8 & 0xFF);
            bytes[p_++] = (byte)(low & 0xFF);
            bytes[p_] = (byte)(low >>> 8 & 0xFF);
            return 4;
        }
        bytes[p_++] = (byte)(code & 0xFF);
        bytes[p_++] = (byte)((code & 0xFF00) >>> 8);
        return 2;
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] fold) {
        final int p = pp.value;
        int foldP = 0;
        if (Encoding.isAscii(bytes[p] & 0xFF) && bytes[p + 1] == 0) {
            fold[foldP++] = AsciiTables.ToLowerCaseTable[bytes[p] & 0xFF];
            fold[foldP] = 0;
            pp.value += 2;
            return 2;
        }
        return super.mbcCaseFold(flag, bytes, pp, end, fold);
    }
    
    public int[] ctypeCodeRange(final int ctype, final IntHolder sbOut) {
        sbOut.value = 0;
        return super.ctypeCodeRange(ctype);
    }
    
    public int leftAdjustCharHead(final byte[] bytes, final int p, int s, final int end) {
        if (s <= p) {
            return s;
        }
        if ((s - p) % 2 == 1) {
            --s;
        }
        if (isSurrogateSecond(bytes[s + 1] & 0xFF) && s > p + 1) {
            s -= 2;
        }
        return s;
    }
    
    public boolean isReverseMatchAllowed(final byte[] bytes, final int p, final int end) {
        return false;
    }
    
    private static boolean isSurrogateFirst(final int c) {
        return (c & 0xFC) == 0xD8;
    }
    
    private static boolean isSurrogateSecond(final int c) {
        return (c & 0xFC) == 0xDC;
    }
    
    private static boolean isSurrogate(final int c) {
        return (c & 0xF8) == 0xD8;
    }
    
    static {
        INSTANCE = new UTF16LEEncoding();
    }
}
