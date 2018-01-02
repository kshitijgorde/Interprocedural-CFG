// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.Arrays;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyString;
import org.jruby.RubyBignum;
import java.math.BigInteger;
import org.jruby.RubyInteger;
import org.jruby.Ruby;

public class ConvertBytes
{
    private final Ruby runtime;
    private final ByteList _str;
    private int str;
    private int end;
    private byte[] data;
    private int base;
    private final boolean badcheck;
    private final boolean is19;
    private static final byte[] LOWER_DIGITS;
    private static final byte[] UPPER_DIGITS;
    private static final byte[] conv_digit;
    private static final boolean[] digit;
    private static final boolean[] space;
    private static final boolean[] spaceOrUnderscore;
    
    public ConvertBytes(final Ruby runtime, final ByteList _str, final int base, final boolean badcheck) {
        this(runtime, _str, base, badcheck, false);
    }
    
    public ConvertBytes(final Ruby runtime, final ByteList _str, final int base, final boolean badcheck, final boolean is19) {
        this.runtime = runtime;
        this._str = _str;
        this.str = _str.getBegin();
        this.data = _str.getUnsafeBytes();
        this.end = this.str + _str.getRealSize();
        this.badcheck = badcheck;
        this.base = base;
        this.is19 = is19;
    }
    
    public static final byte[] intToBinaryBytes(final int i) {
        return ByteList.plain(Integer.toBinaryString(i));
    }
    
    public static final byte[] intToOctalBytes(final int i) {
        return ByteList.plain(Integer.toOctalString(i));
    }
    
    public static final byte[] intToHexBytes(final int i) {
        return ByteList.plain(Integer.toHexString(i).toLowerCase());
    }
    
    public static final byte[] intToHexBytes(final int i, final boolean upper) {
        String s = Integer.toHexString(i);
        s = (upper ? s.toUpperCase() : s.toLowerCase());
        return ByteList.plain(s);
    }
    
    public static final ByteList intToBinaryByteList(final int i) {
        return new ByteList(intToBinaryBytes(i));
    }
    
    public static final ByteList intToOctalByteList(final int i) {
        return new ByteList(intToOctalBytes(i));
    }
    
    public static final ByteList intToHexByteList(final int i) {
        return new ByteList(intToHexBytes(i));
    }
    
    public static final ByteList intToHexByteList(final int i, final boolean upper) {
        return new ByteList(intToHexBytes(i, upper));
    }
    
    public static final byte[] intToByteArray(final int i, final int radix, final boolean upper) {
        String s = Integer.toString(i, radix);
        s = (upper ? s.toUpperCase() : s.toLowerCase());
        return ByteList.plain(s);
    }
    
    public static final byte[] intToCharBytes(final int i) {
        return ByteList.plain(Integer.toString(i));
    }
    
    public static final byte[] longToBinaryBytes(final long i) {
        return ByteList.plain(Long.toBinaryString(i));
    }
    
    public static final byte[] longToOctalBytes(final long i) {
        return ByteList.plain(Long.toOctalString(i));
    }
    
    public static final byte[] longToHexBytes(final long i) {
        return ByteList.plain(Long.toHexString(i).toLowerCase());
    }
    
    public static final byte[] longToHexBytes(final long i, final boolean upper) {
        String s = Long.toHexString(i);
        s = (upper ? s.toUpperCase() : s.toLowerCase());
        return ByteList.plain(s);
    }
    
    public static final ByteList longToBinaryByteList(final long i) {
        return new ByteList(longToBinaryBytes(i));
    }
    
    public static final ByteList longToOctalByteList(final long i) {
        return new ByteList(longToOctalBytes(i));
    }
    
    public static final ByteList longToHexByteList(final long i) {
        return new ByteList(longToHexBytes(i));
    }
    
    public static final ByteList longToHexByteList(final long i, final boolean upper) {
        return new ByteList(longToHexBytes(i, upper));
    }
    
    public static final byte[] longToByteArray(final long i, final int radix, final boolean upper) {
        String s = Long.toString(i, radix);
        s = (upper ? s.toUpperCase() : s.toLowerCase());
        return ByteList.plain(s);
    }
    
    public static final byte[] longToCharBytes(final long i) {
        return ByteList.plain(Long.toString(i));
    }
    
    public static final ByteList longToByteList(final long i) {
        return new ByteList(ByteList.plain(Long.toString(i)), false);
    }
    
    public static final ByteList longToByteList(final long i, final int radix) {
        return new ByteList(ByteList.plain(Long.toString(i, radix)), false);
    }
    
    public static final byte[] twosComplementToBinaryBytes(final byte[] in) {
        return twosComplementToUnsignedBytes(in, 1, false);
    }
    
    public static final byte[] twosComplementToOctalBytes(final byte[] in) {
        return twosComplementToUnsignedBytes(in, 3, false);
    }
    
    public static final byte[] twosComplementToHexBytes(final byte[] in, final boolean upper) {
        return twosComplementToUnsignedBytes(in, 4, upper);
    }
    
    public static final byte[] twosComplementToUnsignedBytes(final byte[] in, final int shift, final boolean upper) {
        if (shift < 1 || shift > 4) {
            throw new IllegalArgumentException("shift value must be 1-4");
        }
        final int ilen = in.length;
        final int olen = (ilen * 8 + shift - 1) / shift;
        final byte[] out = new byte[olen];
        final int mask = (1 << shift) - 1;
        final byte[] digits = upper ? ConvertBytes.UPPER_DIGITS : ConvertBytes.LOWER_DIGITS;
        int bitbuf = 0;
        int bitcnt = 0;
        int i = ilen;
        int o = olen;
        while (--o >= 0) {
            if (bitcnt < shift) {
                bitbuf |= (in[--i] & 0xFF) << bitcnt;
                bitcnt += 8;
            }
            out[o] = digits[bitbuf & mask];
            bitbuf >>= shift;
            bitcnt -= shift;
        }
        return out;
    }
    
    public static RubyInteger byteListToInum(final Ruby runtime, final ByteList str, final int base, final boolean badcheck) {
        return new ConvertBytes(runtime, str, base, badcheck).byteListToInum();
    }
    
    public static RubyInteger byteListToInum19(final Ruby runtime, final ByteList str, final int base, final boolean badcheck) {
        return new ConvertBytes(runtime, str, base, badcheck, true).byteListToInum();
    }
    
    private byte convertDigit(final byte c) {
        if (c < 0) {
            return -1;
        }
        return ConvertBytes.conv_digit[c];
    }
    
    private boolean isSpace(final int str) {
        final byte c;
        return str != this.end && (c = this.data[str]) >= 0 && ConvertBytes.space[c];
    }
    
    private boolean isDigit(final byte[] buf, final int str) {
        final byte c;
        return str != buf.length && (c = buf[str]) >= 0 && ConvertBytes.digit[c];
    }
    
    private boolean isSpaceOrUnderscore(final int str) {
        final byte c;
        return str != this.end && (c = this.data[str]) >= 0 && ConvertBytes.spaceOrUnderscore[c];
    }
    
    private boolean getSign() {
        boolean sign = true;
        if (this.str < this.end) {
            if (this.data[this.str] == 43) {
                ++this.str;
            }
            else if (this.data[this.str] == 45) {
                ++this.str;
                sign = false;
            }
        }
        return sign;
    }
    
    private void ignoreLeadingWhitespace() {
        if (this.badcheck || this.is19) {
            while (this.isSpace(this.str)) {
                ++this.str;
            }
        }
        else {
            while (this.isSpaceOrUnderscore(this.str)) {
                ++this.str;
            }
        }
    }
    
    private void figureOutBase() {
        if (this.base <= 0) {
            if (this.str < this.end && this.data[this.str] == 48) {
                if (this.str + 1 < this.end) {
                    switch (this.data[this.str + 1]) {
                        case 88:
                        case 120: {
                            this.base = 16;
                            break;
                        }
                        case 66:
                        case 98: {
                            this.base = 2;
                            break;
                        }
                        case 79:
                        case 111: {
                            this.base = 8;
                            break;
                        }
                        case 68:
                        case 100: {
                            this.base = 10;
                            break;
                        }
                        default: {
                            this.base = 8;
                            break;
                        }
                    }
                }
                else {
                    this.base = 8;
                }
            }
            else if (this.base < -1) {
                this.base = -this.base;
            }
            else {
                this.base = 10;
            }
        }
    }
    
    private int calculateLength() {
        int len = 0;
        final byte second = (byte)((this.str + 1 < this.end && this.data[this.str] == 48) ? this.data[this.str + 1] : 0);
        switch (this.base) {
            case 2: {
                len = 1;
                if (second == 98 || second == 66) {
                    this.str += 2;
                    break;
                }
                break;
            }
            case 3: {
                len = 2;
                break;
            }
            case 8: {
                if (second == 111 || second == 79) {
                    this.str += 2;
                }
            }
            case 4:
            case 5:
            case 6:
            case 7: {
                len = 3;
                break;
            }
            case 10: {
                if (second == 100 || second == 68) {
                    this.str += 2;
                }
            }
            case 9:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15: {
                len = 4;
                break;
            }
            case 16: {
                len = 4;
                if (second == 120 || second == 88) {
                    this.str += 2;
                    break;
                }
                break;
            }
            default: {
                if (this.base < 2 || 36 < this.base) {
                    throw this.runtime.newArgumentError("illegal radix " + this.base);
                }
                if (this.base <= 32) {
                    len = 5;
                    break;
                }
                len = 6;
                break;
            }
        }
        return len;
    }
    
    private void squeezeZeroes() {
        if (this.str < this.end && this.data[this.str] == 48) {
            ++this.str;
            int us = 0;
            byte c;
            while (this.str < this.end && ((c = this.data[this.str]) == 48 || c == 95)) {
                if (c == 95) {
                    if (++us >= 2) {
                        break;
                    }
                }
                else {
                    us += 0;
                }
                ++this.str;
            }
            if (this.str == this.end || this.isSpace(this.str)) {
                --this.str;
            }
        }
    }
    
    private long stringToLong(final int nptr, final int[] endptr, final int base) {
        if (base < 0 || base == 1 || base > 36) {
            return 0L;
        }
        int save = nptr;
        int s = nptr;
        boolean overflow = false;
        while (this.isSpace(s)) {
            ++s;
        }
        if (s != this.end) {
            boolean negative = false;
            if (this.data[s] == 45) {
                negative = true;
                ++s;
            }
            else if (this.data[s] == 43) {
                negative = false;
                ++s;
            }
            save = s;
            long i = 0L;
            final long cutoff = Long.MAX_VALUE / base;
            final long cutlim = Long.MAX_VALUE % base;
            while (s < this.end) {
                final byte c = this.convertDigit(this.data[s]);
                if (c == -1) {
                    break;
                }
                if (c >= base) {
                    break;
                }
                ++s;
                if (i > cutoff || (i == cutoff && c > cutlim)) {
                    overflow = true;
                }
                else {
                    i *= base;
                    i += c;
                }
            }
            if (s != save) {
                if (endptr != null) {
                    endptr[0] = s;
                }
                if (overflow) {
                    throw new ERange(negative ? ERange.Kind.Underflow : ERange.Kind.Overflow);
                }
                if (negative) {
                    return -i;
                }
                return i;
            }
        }
        if (endptr != null) {
            if (save - nptr >= 2 && (this.data[save - 1] == 120 || this.data[save - 1] == 88) && this.data[save - 2] == 48) {
                endptr[0] = save - 1;
            }
            else {
                endptr[0] = nptr;
            }
        }
        return 0L;
    }
    
    public RubyInteger byteListToInum() {
        if (this._str == null) {
            if (this.badcheck) {
                this.invalidString("Integer");
            }
            return this.runtime.newFixnum(0);
        }
        this.ignoreLeadingWhitespace();
        final boolean sign = this.getSign();
        if (this.str < this.end && (this.data[this.str] == 43 || this.data[this.str] == 45)) {
            if (this.badcheck) {
                this.invalidString("Integer");
            }
            return this.runtime.newFixnum(0);
        }
        this.figureOutBase();
        int len = this.calculateLength();
        this.squeezeZeroes();
        byte c = 0;
        if (this.str < this.end) {
            c = this.data[this.str];
        }
        c = this.convertDigit(c);
        if (c < 0 || c >= this.base) {
            if (this.badcheck) {
                this.invalidString("Integer");
            }
            return this.runtime.newFixnum(0);
        }
        len *= this.end - this.str;
        if (len >= 63) {
            return this.bigParse(len, sign);
        }
        final int[] endPlace = { this.str };
        final long val = this.stringToLong(this.str, endPlace, this.base);
        if (endPlace[0] < this.end && this.data[endPlace[0]] == 95) {
            return this.bigParse(len, sign);
        }
        if (this.badcheck) {
            if (endPlace[0] == this.str) {
                this.invalidString("Integer");
            }
            while (this.isSpace(endPlace[0])) {
                final int[] array = endPlace;
                final int n = 0;
                ++array[n];
            }
            if (endPlace[0] < this.end) {
                this.invalidString("Integer");
            }
        }
        if (sign) {
            return this.runtime.newFixnum(val);
        }
        return this.runtime.newFixnum(-val);
    }
    
    private RubyInteger bigParse(final int len, final boolean sign) {
        if (this.badcheck && this.str < this.end && this.data[this.str] == 95) {
            this.invalidString("Integer");
        }
        final char[] result = new char[this.end - this.str];
        int resultIndex = 0;
        byte nondigit = -1;
        while (this.str < this.end) {
            byte c = this.data[this.str++];
            final byte cx;
            if ((cx = c) == 95) {
                if (nondigit != -1) {
                    if (this.badcheck) {
                        this.invalidString("Integer");
                        break;
                    }
                    break;
                }
                else {
                    nondigit = c;
                }
            }
            else {
                if ((c = this.convertDigit(c)) < 0) {
                    break;
                }
                if (c >= this.base) {
                    break;
                }
                nondigit = -1;
                result[resultIndex++] = (char)cx;
            }
        }
        BigInteger z;
        if (resultIndex == 0) {
            z = BigInteger.ZERO;
        }
        else {
            z = new BigInteger(new String(result, 0, resultIndex), this.base);
        }
        if (!sign) {
            z = z.negate();
        }
        if (this.badcheck) {
            if (this._str.getBegin() + 1 < this.str && this.data[this.str - 1] == 95) {
                this.invalidString("Integer");
            }
            while (this.str < this.end && this.isSpace(this.str)) {
                ++this.str;
            }
            if (this.str < this.end) {
                this.invalidString("Integer");
            }
        }
        return RubyBignum.bignorm(this.runtime, z);
    }
    
    private void invalidString(final String type) {
        final IRubyObject s = RubyString.newString(this.runtime, this._str).inspect();
        throw this.runtime.newArgumentError("invalid value for " + type + ": " + s);
    }
    
    static {
        LOWER_DIGITS = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
        UPPER_DIGITS = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 };
        conv_digit = new byte[128];
        digit = new boolean[128];
        space = new boolean[128];
        spaceOrUnderscore = new boolean[128];
        Arrays.fill(ConvertBytes.conv_digit, (byte)(-1));
        Arrays.fill(ConvertBytes.digit, false);
        for (char c = '0'; c <= '9'; ++c) {
            ConvertBytes.conv_digit[c] = (byte)(c - '0');
            ConvertBytes.digit[c] = true;
        }
        for (char c = 'a'; c <= 'z'; ++c) {
            ConvertBytes.conv_digit[c] = (byte)(c - 'a' + '\n');
        }
        for (char c = 'A'; c <= 'Z'; ++c) {
            ConvertBytes.conv_digit[c] = (byte)(c - 'A' + '\n');
        }
        Arrays.fill(ConvertBytes.space, false);
        ConvertBytes.space[9] = true;
        ConvertBytes.space[10] = true;
        ConvertBytes.space[11] = true;
        ConvertBytes.space[12] = true;
        ConvertBytes.space[13] = true;
        ConvertBytes.space[32] = true;
        Arrays.fill(ConvertBytes.spaceOrUnderscore, false);
        ConvertBytes.spaceOrUnderscore[9] = true;
        ConvertBytes.spaceOrUnderscore[10] = true;
        ConvertBytes.spaceOrUnderscore[11] = true;
        ConvertBytes.spaceOrUnderscore[12] = true;
        ConvertBytes.spaceOrUnderscore[13] = true;
        ConvertBytes.spaceOrUnderscore[32] = true;
        ConvertBytes.spaceOrUnderscore[95] = true;
    }
    
    public static class ERange extends RuntimeException
    {
        private Kind kind;
        
        public ERange() {
        }
        
        public ERange(final Kind kind) {
            this.kind = kind;
        }
        
        public Kind getKind() {
            return this.kind;
        }
        
        public enum Kind
        {
            Overflow, 
            Underflow;
        }
    }
}
