// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jcodings.ascii.AsciiTables;
import org.jruby.Ruby;
import org.jcodings.specific.ASCIIEncoding;
import org.jcodings.Encoding;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

public final class StringSupport
{
    public static final int CR_MASK = 96;
    public static final int CR_UNKNOWN = 0;
    public static final int CR_7BIT = 32;
    public static final int CR_VALID = 64;
    public static final int CR_BROKEN = 96;
    public static final Object UNSAFE;
    private static final int OFFSET;
    private static final long NONASCII_MASK = -9187201950435737472L;
    private static final int LONG_SIZE = 8;
    private static final int LOWBITS = 7;
    
    private static Object getUnsafe() {
        try {
            final Class sunUnsafe = Class.forName("sun.misc.Unsafe");
            final Field f = sunUnsafe.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return Unsafe.class.cast(f.get(sunUnsafe));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static int length(final Encoding enc, final byte[] bytes, final int p, final int end) {
        final int n = enc.length(bytes, p, end);
        if (n > 0 && end - p >= n) {
            return n;
        }
        return (end - p >= enc.minLength()) ? enc.minLength() : (end - p);
    }
    
    public static int preciseLength(final Encoding enc, final byte[] bytes, final int p, final int end) {
        if (p >= end) {
            return -2;
        }
        final int n = enc.length(bytes, p, end);
        if (n > end - p) {
            return -1 - (n - (end - p));
        }
        return n;
    }
    
    public static int searchNonAscii(final byte[] bytes, int p, final int end) {
        while (p < end) {
            if (!Encoding.isAscii(bytes[p])) {
                return p;
            }
            ++p;
        }
        return -1;
    }
    
    public static int searchNonAscii(final ByteList bytes) {
        return searchNonAscii(bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getBegin() + bytes.getRealSize());
    }
    
    public static int codeRangeScan(final Encoding enc, final byte[] bytes, final int p, final int len) {
        if (enc == ASCIIEncoding.INSTANCE) {
            return (searchNonAscii(bytes, p, p + len) != -1) ? 64 : 32;
        }
        if (enc.isAsciiCompatible()) {
            return codeRangeScanAsciiCompatible(enc, bytes, p, len);
        }
        return codeRangeScanNonAsciiCompatible(enc, bytes, p, len);
    }
    
    private static int codeRangeScanAsciiCompatible(final Encoding enc, final byte[] bytes, int p, final int len) {
        final int end = p + len;
        p = searchNonAscii(bytes, p, end);
        if (p == -1) {
            return 32;
        }
        while (p < end) {
            final int cl = preciseLength(enc, bytes, p, end);
            if (cl <= 0) {
                return 96;
            }
            p += cl;
            if (p >= end) {
                continue;
            }
            p = searchNonAscii(bytes, p, end);
            if (p == -1) {
                return 64;
            }
        }
        return (p > end) ? 96 : 64;
    }
    
    private static int codeRangeScanNonAsciiCompatible(final Encoding enc, final byte[] bytes, int p, final int len) {
        int end;
        int cl;
        for (end = p + len; p < end; p += cl) {
            cl = preciseLength(enc, bytes, p, end);
            if (cl <= 0) {
                return 96;
            }
        }
        return (p > end) ? 96 : 64;
    }
    
    public static int codeRangeScan(final Encoding enc, final ByteList bytes) {
        return codeRangeScan(enc, bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getRealSize());
    }
    
    public static long codeRangeScanRestartable(final Encoding enc, final byte[] bytes, final int s, final int end, final int cr) {
        if (cr == 96) {
            return pack(end - s, cr);
        }
        int p = s;
        if (enc == ASCIIEncoding.INSTANCE) {
            return pack(end - s, (searchNonAscii(bytes, p, end) == -1 && cr != 64) ? 32 : 64);
        }
        if (enc.isAsciiCompatible()) {
            p = searchNonAscii(bytes, p, end);
            if (p == -1) {
                return pack(end - s, (cr != 64) ? 32 : cr);
            }
            while (p < end) {
                final int cl = preciseLength(enc, bytes, p, end);
                if (cl <= 0) {
                    return pack(p - s, (cl == -1) ? 96 : 0);
                }
                p += cl;
                if (p >= end) {
                    continue;
                }
                p = searchNonAscii(bytes, p, end);
                if (p == -1) {
                    return pack(end - s, 64);
                }
            }
        }
        else {
            while (p < end) {
                final int cl = preciseLength(enc, bytes, p, end);
                if (cl <= 0) {
                    return pack(p - s, (cl == -1) ? 96 : 0);
                }
                p += cl;
            }
        }
        return pack(p - s, (p > end) ? 96 : 64);
    }
    
    private static int countUtf8LeadBytes(long d) {
        d |= ~(d >>> 1);
        d >>>= 6;
        d &= 0x101010101010101L;
        d += d >>> 8;
        d += d >>> 16;
        d += d >>> 32;
        return (int)(d & 0xFL);
    }
    
    public static int utf8Length(final byte[] bytes, int p, final int end) {
        int len = 0;
        if (StringSupport.UNSAFE != null && end - p > 16) {
            final int ep = 0xFFFFFFF8 & p + 7;
            while (p < ep) {
                if ((bytes[p++] & 0xC0) != 0x80) {
                    ++len;
                }
            }
            final Unsafe us = (Unsafe)StringSupport.UNSAFE;
            for (int eend = 0xFFFFFFF8 & end; p < eend; p += 8) {
                len += countUtf8LeadBytes(us.getLong(bytes, StringSupport.OFFSET + p));
            }
        }
        while (p < end) {
            if ((bytes[p++] & 0xC0) != 0x80) {
                ++len;
            }
        }
        return len;
    }
    
    public static int utf8Length(final ByteList bytes) {
        return utf8Length(bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getBegin() + bytes.getRealSize());
    }
    
    public static int strLength(final Encoding enc, final byte[] bytes, int p, final int end) {
        if (enc.isFixedWidth()) {
            return (end - p + enc.minLength() - 1) / enc.minLength();
        }
        if (enc.isAsciiCompatible()) {
            int c;
            for (c = 0; p < end; p += length(enc, bytes, p, end), ++c) {
                if (Encoding.isAscii(bytes[p])) {
                    final int q = searchNonAscii(bytes, p, end);
                    if (q == -1) {
                        return c + (end - p);
                    }
                    c += q - p;
                    p = q;
                }
            }
            return c;
        }
        int c;
        for (c = 0; end > p; p += length(enc, bytes, p, end), ++c) {}
        return c;
    }
    
    public static int strLength(final ByteList bytes) {
        return strLength(bytes.getEncoding(), bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getBegin() + bytes.getRealSize());
    }
    
    public static long strLengthWithCodeRange(final Encoding enc, final byte[] bytes, final int p, final int end) {
        if (enc.isFixedWidth()) {
            return (end - p + enc.minLength() - 1) / enc.minLength();
        }
        if (enc.isAsciiCompatible()) {
            return strLengthWithCodeRangeAsciiCompatible(enc, bytes, p, end);
        }
        return strLengthWithCodeRangeNonAsciiCompatible(enc, bytes, p, end);
    }
    
    private static long strLengthWithCodeRangeAsciiCompatible(final Encoding enc, final byte[] bytes, int p, final int end) {
        int cr = 0;
        int c = 0;
        while (p < end) {
            if (Encoding.isAscii(bytes[p])) {
                final int q = searchNonAscii(bytes, p, end);
                if (q == -1) {
                    return pack(c + (end - p), (cr == 0) ? 32 : cr);
                }
                c += q - p;
                p = q;
            }
            final int cl = preciseLength(enc, bytes, p, end);
            if (cl > 0) {
                cr |= 0x40;
                p += cl;
            }
            else {
                cr = 96;
                ++p;
            }
            ++c;
        }
        return pack(c, (cr == 0) ? 32 : cr);
    }
    
    private static long strLengthWithCodeRangeNonAsciiCompatible(final Encoding enc, final byte[] bytes, int p, final int end) {
        int cr = 0;
        int c = 0;
        c = 0;
        while (p < end) {
            final int cl = preciseLength(enc, bytes, p, end);
            if (cl > 0) {
                cr |= 0x40;
                p += cl;
            }
            else {
                cr = 96;
                ++p;
            }
            ++c;
        }
        return pack(c, (cr == 0) ? 32 : cr);
    }
    
    public static long strLengthWithCodeRange(final ByteList bytes) {
        return strLengthWithCodeRange(bytes.getEncoding(), bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getBegin() + bytes.getRealSize());
    }
    
    public static long strLengthWithCodeRange(final ByteList bytes, final Encoding enc) {
        return strLengthWithCodeRange(enc, bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getBegin() + bytes.getRealSize());
    }
    
    static long pack(final int result, final int arg) {
        return arg << 31 | result;
    }
    
    public static int unpackResult(final long len) {
        return (int)len & Integer.MAX_VALUE;
    }
    
    public static int unpackArg(final long cr) {
        return (int)(cr >>> 31);
    }
    
    public static int codePoint(final Ruby runtime, final Encoding enc, final byte[] bytes, final int p, final int end) {
        if (p >= end) {
            throw runtime.newArgumentError("empty string");
        }
        final int cl = preciseLength(enc, bytes, p, end);
        if (cl <= 0) {
            throw runtime.newArgumentError("invalid byte sequence in " + enc);
        }
        return enc.mbcToCode(bytes, p, end);
    }
    
    public static int codeLength(final Ruby runtime, final Encoding enc, final int c) {
        final int n = enc.codeToMbcLength(c);
        if (n == 0) {
            throw runtime.newArgumentError("invalid codepoint " + String.format("0x%x in ", c) + enc.getName());
        }
        return n;
    }
    
    public static long getAscii(final Encoding enc, final byte[] bytes, final int p, final int end) {
        return getAscii(enc, bytes, p, end, 0);
    }
    
    public static long getAscii(final Encoding enc, final byte[] bytes, final int p, final int end, final int len) {
        if (p >= end) {
            return pack(-1, len);
        }
        if (enc.isAsciiCompatible()) {
            final int c = bytes[p] & 0xFF;
            if (!Encoding.isAscii(c)) {
                pack(-1, len);
            }
            return pack(c, (len != 0) ? 1 : 0);
        }
        final int cl = preciseLength(enc, bytes, p, end);
        if (cl <= 0) {
            return pack(-1, len);
        }
        final int c2 = enc.mbcToCode(bytes, p, end);
        if (!Encoding.isAscii(c2)) {
            return pack(-1, len);
        }
        return pack(c2, (len == 0) ? 0 : cl);
    }
    
    public static int preciseCodePoint(final Encoding enc, final byte[] bytes, final int p, final int end) {
        final int l = preciseLength(enc, bytes, p, end);
        if (l > 0) {
            enc.mbcToCode(bytes, p, end);
        }
        return -1;
    }
    
    public static int utf8Nth(final byte[] bytes, int p, final int end, int n) {
        if (StringSupport.UNSAFE != null && n > 16) {
            final int ep = 0xFFFFFFF8 & p + 7;
            while (p < ep) {
                if ((bytes[p++] & 0xC0) != 0x80) {
                    --n;
                }
            }
            final Unsafe us = (Unsafe)StringSupport.UNSAFE;
            final int eend = 0xFFFFFFF8 & end;
            do {
                n -= countUtf8LeadBytes(us.getLong(bytes, StringSupport.OFFSET + p));
                p += 8;
            } while (p < eend && n >= 8);
        }
        while (p < end && ((bytes[p] & 0xC0) == 0x80 || n-- != 0)) {
            ++p;
        }
        return p;
    }
    
    public static int nth(final Encoding enc, final byte[] bytes, int p, final int end, final int n) {
        if (enc.isSingleByte()) {
            p += n;
        }
        else if (enc.isFixedWidth()) {
            p += n * enc.maxLength();
        }
        else if (enc.isAsciiCompatible()) {
            p = nthAsciiCompatible(enc, bytes, p, end, n);
        }
        else {
            p = nthNonAsciiCompatible(enc, bytes, p, end, n);
        }
        return (p > end) ? end : p;
    }
    
    private static int nthAsciiCompatible(final Encoding enc, final byte[] bytes, int p, final int end, int n) {
        while (p < end && n > 0) {
            final int end2 = p + n;
            if (end < end2) {
                return end;
            }
            if (Encoding.isAscii(bytes[p])) {
                final int p2 = searchNonAscii(bytes, p, end2);
                if (p2 == -1) {
                    return end2;
                }
                n -= p2 - p;
                p = p2;
            }
            final int cl = length(enc, bytes, p, end);
            p += cl;
            --n;
        }
        return (n != 0) ? end : p;
    }
    
    private static int nthNonAsciiCompatible(final Encoding enc, final byte[] bytes, int p, final int end, int n) {
        while (p < end && n-- != 0) {
            p += length(enc, bytes, p, end);
        }
        return p;
    }
    
    public static int utf8Offset(final byte[] bytes, final int p, final int end, final int n) {
        final int pp = utf8Nth(bytes, p, end, n);
        return (pp == -1) ? (end - p) : (pp - p);
    }
    
    public static int offset(final Encoding enc, final byte[] bytes, final int p, final int end, final int n) {
        final int pp = nth(enc, bytes, p, end, n);
        return (pp == -1) ? (end - p) : (pp - p);
    }
    
    public static int toLower(final Encoding enc, final int c) {
        return Encoding.isAscii(c) ? AsciiTables.ToLowerCaseTable[c] : c;
    }
    
    public static int toUpper(final Encoding enc, final int c) {
        return Encoding.isAscii(c) ? AsciiTables.ToUpperCaseTable[c] : c;
    }
    
    public static int caseCmp(final byte[] bytes1, final int p1, final byte[] bytes2, final int p2, final int len) {
        int i = -1;
        while (++i < len && bytes1[p1 + i] == bytes2[p2 + i]) {}
        if (i < len) {
            return ((bytes1[p1 + i] & 0xFF) > (bytes2[p2 + i] & 0xFF)) ? 1 : -1;
        }
        return 0;
    }
    
    public static int scanHex(final byte[] bytes, final int p, final int len) {
        return scanHex(bytes, p, len, ASCIIEncoding.INSTANCE);
    }
    
    public static int scanHex(final byte[] bytes, int p, int len, final Encoding enc) {
        int v = 0;
        int c;
        while (len-- > 0 && enc.isXDigit(c = (bytes[p++] & 0xFF))) {
            v = (v << 4) + enc.xdigitVal(c);
        }
        return v;
    }
    
    public static int hexLength(final byte[] bytes, final int p, final int len) {
        return hexLength(bytes, p, len, ASCIIEncoding.INSTANCE);
    }
    
    public static int hexLength(final byte[] bytes, int p, int len, final Encoding enc) {
        int hlen = 0;
        while (len-- > 0 && enc.isXDigit(bytes[p++] & 0xFF)) {
            ++hlen;
        }
        return hlen;
    }
    
    public static int scanOct(final byte[] bytes, final int p, final int len) {
        return scanOct(bytes, p, len, ASCIIEncoding.INSTANCE);
    }
    
    public static int scanOct(final byte[] bytes, int p, int len, final Encoding enc) {
        int v = 0;
        int c;
        while (len-- > 0 && enc.isDigit(c = (bytes[p++] & 0xFF)) && c < 56) {
            v = (v << 3) + Encoding.digitVal(c);
        }
        return v;
    }
    
    public static int octLength(final byte[] bytes, final int p, final int len) {
        return octLength(bytes, p, len, ASCIIEncoding.INSTANCE);
    }
    
    public static int octLength(final byte[] bytes, int p, int len, final Encoding enc) {
        int olen = 0;
        int c;
        while (len-- > 0 && enc.isDigit(c = (bytes[p++] & 0xFF)) && c < 56) {
            ++olen;
        }
        return olen;
    }
    
    static {
        UNSAFE = getUnsafe();
        OFFSET = ((StringSupport.UNSAFE != null) ? ((Unsafe)StringSupport.UNSAFE).arrayBaseOffset(byte[].class) : 0);
    }
}
