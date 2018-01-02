// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.platform.Platform;
import org.jruby.RubyFloat;
import java.nio.ByteOrder;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyFixnum;
import org.jcodings.Encoding;
import java.nio.ByteBuffer;
import org.jruby.RubyArray;
import org.jruby.RubyKernel;
import org.jruby.RubyString;
import org.jruby.Ruby;
import org.jruby.RubyNumeric;
import org.jruby.RubyBignum;
import org.jruby.runtime.builtin.IRubyObject;
import java.math.BigInteger;
import org.jcodings.specific.UTF8Encoding;
import org.jcodings.specific.USASCIIEncoding;
import org.jcodings.specific.ASCIIEncoding;

public class Pack
{
    private static final byte[] sSp10;
    private static final byte[] sNil10;
    private static final int IS_STAR = -1;
    private static final ASCIIEncoding ASCII;
    private static final USASCIIEncoding USASCII;
    private static final UTF8Encoding UTF8;
    private static final String NATIVE_CODES = "sSiIlL";
    private static final String MAPPED_CODES = "sSiIqQ";
    private static final String UNPACK_IGNORE_NULL_CODES = "cC";
    private static final String PACK_IGNORE_NULL_CODES = "cCiIlLnNqQsSvV";
    private static final String PACK_IGNORE_NULL_CODES_WITH_MODIFIERS = "lLsS";
    private static final String sTooFew = "too few arguments";
    private static final byte[] hex_table;
    private static final byte[] uu_table;
    private static final byte[] b64_table;
    private static final byte[] sHexDigits;
    private static final int[] b64_xtable;
    private static final Converter[] converters;
    private static final BigInteger QUAD_MIN;
    private static final BigInteger QUAD_MAX;
    private static final long[] utf8_limits;
    
    private static long num2quad(final IRubyObject arg) {
        if (arg == arg.getRuntime().getNil()) {
            return 0L;
        }
        if (!(arg instanceof RubyBignum)) {
            return RubyNumeric.num2long(arg);
        }
        final BigInteger big = ((RubyBignum)arg).getValue();
        if (big.compareTo(Pack.QUAD_MIN) < 0 || big.compareTo(Pack.QUAD_MAX) > 0) {
            throw arg.getRuntime().newRangeError("bignum too big to convert into `quad int'");
        }
        return big.longValue();
    }
    
    private static long num2quad19(final IRubyObject arg) {
        if (arg == arg.getRuntime().getNil()) {
            return 0L;
        }
        if (arg instanceof RubyBignum) {
            final BigInteger big = ((RubyBignum)arg).getValue();
            return big.longValue();
        }
        return RubyNumeric.num2long(arg);
    }
    
    private static float obj2flt(final Ruby runtime, final IRubyObject o) {
        if (!(o instanceof RubyString)) {
            return (float)RubyKernel.new_float(o, o).getDoubleValue();
        }
        final String str = o.asJavaString();
        final float f = (float)RubyNumeric.num2dbl(o.convertToFloat());
        if (str.matches("^\\s*[-+]?\\s*[0-9].*")) {
            return f;
        }
        throw runtime.newTypeError("array item not a float");
    }
    
    private static double obj2dbl(final Ruby runtime, final IRubyObject o) {
        if (!(o instanceof RubyString)) {
            return RubyKernel.new_float(o, o).getDoubleValue();
        }
        final String str = o.asJavaString();
        final double d = RubyNumeric.num2dbl(o.convertToFloat());
        if (str.matches("^\\s*[-+]?\\s*[0-9].*")) {
            return d;
        }
        throw runtime.newTypeError("array item not a float");
    }
    
    private static ByteList encodes(final Ruby runtime, final ByteList io2Append, final byte[] charsToEncode, final int startIndex, final int length, int charCount, final byte encodingType, final boolean tailLf) {
        charCount = ((charCount < length) ? charCount : length);
        io2Append.ensure(charCount * 4 / 3 + 6);
        int i = startIndex;
        final byte[] lTranslationTable = (encodingType == 117) ? Pack.uu_table : Pack.b64_table;
        byte lPadding;
        if (encodingType == 117) {
            if (charCount >= lTranslationTable.length) {
                throw runtime.newArgumentError("" + charCount + " is not a correct value for the number of bytes per line in a u directive.  Correct values range from 0 to " + lTranslationTable.length);
            }
            io2Append.append(lTranslationTable[charCount]);
            lPadding = 96;
        }
        else {
            lPadding = 61;
        }
        while (charCount >= 3) {
            final byte lCurChar = charsToEncode[i++];
            final byte lNextChar = charsToEncode[i++];
            final byte lNextNextChar = charsToEncode[i++];
            io2Append.append(lTranslationTable[0x3F & lCurChar >>> 2]);
            io2Append.append(lTranslationTable[0x3F & ((lCurChar << 4 & 0x30) | (lNextChar >>> 4 & 0xF))]);
            io2Append.append(lTranslationTable[0x3F & ((lNextChar << 2 & 0x3C) | (lNextNextChar >>> 6 & 0x3))]);
            io2Append.append(lTranslationTable[0x3F & lNextNextChar]);
            charCount -= 3;
        }
        if (charCount == 2) {
            final byte lCurChar = charsToEncode[i++];
            final byte lNextChar = charsToEncode[i++];
            io2Append.append(lTranslationTable[0x3F & lCurChar >>> 2]);
            io2Append.append(lTranslationTable[0x3F & ((lCurChar << 4 & 0x30) | (lNextChar >> 4 & 0xF))]);
            io2Append.append(lTranslationTable[0x3F & ((lNextChar << 2 & 0x3C) | 0x0)]);
            io2Append.append(lPadding);
        }
        else if (charCount == 1) {
            final byte lCurChar = charsToEncode[i++];
            io2Append.append(lTranslationTable[0x3F & lCurChar >>> 2]);
            io2Append.append(lTranslationTable[0x3F & ((lCurChar << 4 & 0x30) | 0x0)]);
            io2Append.append(lPadding);
            io2Append.append(lPadding);
        }
        if (tailLf) {
            io2Append.append(10);
        }
        return io2Append;
    }
    
    private static ByteList qpencode(final ByteList io2Append, final ByteList i2Encode, final int iLength) {
        io2Append.ensure(1024);
        int lCurLineLength = 0;
        int lPrevChar = -1;
        final byte[] l2Encode = i2Encode.getUnsafeBytes();
        try {
            for (int end = i2Encode.getBegin() + i2Encode.getRealSize(), i = i2Encode.getBegin(); i < end; ++i) {
                final int lCurChar = l2Encode[i] & 0xFF;
                if (lCurChar > 126 || (lCurChar < 32 && lCurChar != 10 && lCurChar != 9) || lCurChar == 61) {
                    io2Append.append(61);
                    io2Append.append(Pack.hex_table[lCurChar >>> 4]);
                    io2Append.append(Pack.hex_table[lCurChar & 0xF]);
                    lCurLineLength += 3;
                    lPrevChar = -1;
                }
                else if (lCurChar == 10) {
                    if (lPrevChar == 32 || lPrevChar == 9) {
                        io2Append.append(61);
                        io2Append.append(lCurChar);
                    }
                    io2Append.append(lCurChar);
                    lCurLineLength = 0;
                    lPrevChar = lCurChar;
                }
                else {
                    io2Append.append(lCurChar);
                    ++lCurLineLength;
                    lPrevChar = lCurChar;
                }
                if (lCurLineLength > iLength) {
                    io2Append.append(61);
                    io2Append.append(10);
                    lCurLineLength = 0;
                    lPrevChar = 10;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        if (lCurLineLength > 0) {
            io2Append.append(61);
            io2Append.append(10);
        }
        return io2Append;
    }
    
    public static RubyArray unpack(final Ruby runtime, final ByteList encodedString, final ByteList formatString) {
        final Encoding encoding = encodedString.getEncoding();
        final RubyArray result = runtime.newArray();
        final ByteBuffer format = ByteBuffer.wrap(formatString.getUnsafeBytes(), formatString.begin(), formatString.length());
        final ByteBuffer encode = ByteBuffer.wrap(encodedString.getUnsafeBytes(), encodedString.begin(), encodedString.length());
        int type = 0;
        int next = safeGet(format);
    Label_2491_Outer:
        while (next != 0) {
            type = next;
            next = safeGet(format);
            if ("cC".indexOf(type) != -1 && next == 0) {
                next = safeGetIgnoreNull(format);
            }
            if (next == 95 || next == 33) {
                final int index = "sSiIlL".indexOf(type);
                if (index == -1) {
                    throw runtime.newArgumentError("'" + next + "' allowed only after types " + "sSiIlL");
                }
                type = "sSiIqQ".charAt(index);
                next = safeGet(format);
            }
            int occurrences = 0;
            if (next == 0) {
                occurrences = 1;
            }
            else if (next == 42) {
                occurrences = -1;
                next = safeGet(format);
            }
            else if (Pack.ASCII.isDigit(next)) {
                occurrences = 0;
                do {
                    occurrences = occurrences * 10 + Character.digit((char)(next & 0xFF), 10);
                    next = safeGet(format);
                    if (next != 0) {
                        continue;
                    }
                    break;
                } while (Pack.ASCII.isDigit(next));
            }
            else {
                occurrences = ((type != 64) ? 1 : 0);
            }
            final Converter converter = Pack.converters[type];
            if (converter != null) {
                decode(runtime, encode, occurrences, result, converter);
                type = next;
            }
            else {
                Label_2591: {
                    switch (type) {
                        case 64: {
                            try {
                                if (occurrences == -1) {
                                    encode.position(encodedString.begin() + encode.remaining());
                                }
                                else {
                                    encode.position(encodedString.begin() + occurrences);
                                }
                                continue;
                            }
                            catch (IllegalArgumentException iae) {
                                throw runtime.newArgumentError("@ outside of string");
                            }
                        }
                        case 37: {
                            throw runtime.newArgumentError("% is not supported");
                        }
                        case 65: {
                            if (occurrences == -1 || occurrences > encode.remaining()) {
                                occurrences = encode.remaining();
                            }
                            final byte[] potential = new byte[occurrences];
                            encode.get(potential);
                            for (int t = occurrences - 1; occurrences > 0; --occurrences, --t) {
                                final byte c = potential[t];
                                if (c != 0 && c != 32) {
                                    break;
                                }
                            }
                            result.append(RubyString.newString(runtime, new ByteList(potential, 0, occurrences, encoding, false)));
                            continue;
                        }
                        case 90: {
                            final boolean isStar = occurrences == -1;
                            if (occurrences == -1 || occurrences > encode.remaining()) {
                                occurrences = encode.remaining();
                            }
                            final byte[] potential2 = new byte[occurrences];
                            int t2;
                            for (t2 = 0; t2 < occurrences; ++t2) {
                                final byte b = encode.get();
                                if (b == 0) {
                                    break;
                                }
                                potential2[t2] = b;
                            }
                            result.append(RubyString.newString(runtime, new ByteList(potential2, 0, t2, encoding, false)));
                            if (isStar) {
                                continue;
                            }
                            if (t2 < occurrences) {
                                ++t2;
                            }
                            while (t2 < occurrences) {
                                encode.get();
                                ++t2;
                            }
                            continue;
                        }
                        case 97: {
                            if (occurrences == -1 || occurrences > encode.remaining()) {
                                occurrences = encode.remaining();
                            }
                            final byte[] potential = new byte[occurrences];
                            encode.get(potential);
                            result.append(RubyString.newString(runtime, new ByteList(potential, encoding, false)));
                            continue;
                        }
                        case 98: {
                            if (occurrences == -1 || occurrences > encode.remaining() * 8) {
                                occurrences = encode.remaining() * 8;
                            }
                            int bits = 0;
                            final byte[] lElem = new byte[occurrences];
                            for (int lCurByte = 0; lCurByte < occurrences; ++lCurByte) {
                                if ((lCurByte & 0x7) != 0x0) {
                                    bits >>>= 1;
                                }
                                else {
                                    bits = encode.get();
                                }
                                lElem[lCurByte] = (byte)(((bits & 0x1) != 0x0) ? 49 : 48);
                            }
                            result.append(RubyString.newString(runtime, new ByteList(lElem, encoding, false)));
                            continue;
                        }
                        case 66: {
                            if (occurrences == -1 || occurrences > encode.remaining() * 8) {
                                occurrences = encode.remaining() * 8;
                            }
                            int bits = 0;
                            final byte[] lElem = new byte[occurrences];
                            for (int lCurByte = 0; lCurByte < occurrences; ++lCurByte) {
                                if ((lCurByte & 0x7) != 0x0) {
                                    bits <<= 1;
                                }
                                else {
                                    bits = encode.get();
                                }
                                lElem[lCurByte] = (byte)(((bits & 0x80) != 0x0) ? 49 : 48);
                            }
                            result.append(RubyString.newString(runtime, new ByteList(lElem, encoding, false)));
                            continue;
                        }
                        case 104: {
                            if (occurrences == -1 || occurrences > encode.remaining() * 2) {
                                occurrences = encode.remaining() * 2;
                            }
                            int bits = 0;
                            final byte[] lElem = new byte[occurrences];
                            for (int lCurByte = 0; lCurByte < occurrences; ++lCurByte) {
                                if ((lCurByte & 0x1) != 0x0) {
                                    bits >>>= 4;
                                }
                                else {
                                    bits = encode.get();
                                }
                                lElem[lCurByte] = Pack.sHexDigits[bits & 0xF];
                            }
                            result.append(RubyString.newString(runtime, new ByteList(lElem, encoding, false)));
                            continue;
                        }
                        case 72: {
                            if (occurrences == -1 || occurrences > encode.remaining() * 2) {
                                occurrences = encode.remaining() * 2;
                            }
                            int bits = 0;
                            final byte[] lElem = new byte[occurrences];
                            for (int lCurByte = 0; lCurByte < occurrences; ++lCurByte) {
                                if ((lCurByte & 0x1) != 0x0) {
                                    bits <<= 4;
                                }
                                else {
                                    bits = encode.get();
                                }
                                lElem[lCurByte] = Pack.sHexDigits[bits >>> 4 & 0xF];
                            }
                            result.append(RubyString.newString(runtime, new ByteList(lElem, encoding, false)));
                            continue;
                        }
                        case 117: {
                            final int length = encode.remaining() * 3 / 4;
                            final byte[] lElem = new byte[length];
                            int index2 = 0;
                            int total = 0;
                            int s = encode.get();
                            while (encode.hasRemaining() && s > 32 && s < 97) {
                                final byte[] hunk = new byte[3];
                                int len = s - 32 & 0x3F;
                                s = safeGet(encode);
                                total += len;
                                if (total > length) {
                                    len -= total - length;
                                    total = length;
                                }
                                while (len > 0) {
                                    final int mlen = (len > 3) ? 3 : len;
                                    int a;
                                    if (encode.hasRemaining() && s >= 32) {
                                        a = (s - 32 & 0x3F);
                                        s = safeGet(encode);
                                    }
                                    else {
                                        a = 0;
                                    }
                                    int b2;
                                    if (encode.hasRemaining() && s >= 32) {
                                        b2 = (s - 32 & 0x3F);
                                        s = safeGet(encode);
                                    }
                                    else {
                                        b2 = 0;
                                    }
                                    int c2;
                                    if (encode.hasRemaining() && s >= 32) {
                                        c2 = (s - 32 & 0x3F);
                                        s = safeGet(encode);
                                    }
                                    else {
                                        c2 = 0;
                                    }
                                    int d;
                                    if (encode.hasRemaining() && s >= 32) {
                                        d = (s - 32 & 0x3F);
                                        s = safeGet(encode);
                                    }
                                    else {
                                        d = 0;
                                    }
                                    hunk[0] = (byte)((a << 2 | b2 >> 4) & 0xFF);
                                    hunk[1] = (byte)((b2 << 4 | c2 >> 2) & 0xFF);
                                    hunk[2] = (byte)((c2 << 6 | d) & 0xFF);
                                    for (int i = 0; i < mlen; ++i) {
                                        lElem[index2++] = hunk[i];
                                    }
                                    len -= mlen;
                                }
                                if (s == 13) {
                                    s = safeGet(encode);
                                }
                                if (s == 10) {
                                    s = safeGet(encode);
                                }
                                else {
                                    if (!encode.hasRemaining()) {
                                        continue;
                                    }
                                    if (safeGet(encode) == 10) {
                                        safeGet(encode);
                                    }
                                    else {
                                        if (!encode.hasRemaining()) {
                                            continue;
                                        }
                                        encode.position(encode.position() - 1);
                                    }
                                }
                            }
                            result.append(RubyString.newString(runtime, new ByteList(lElem, 0, index2, encoding, false)));
                            continue;
                        }
                        case 109: {
                            final int length = encode.remaining() * 3 / 4;
                            final byte[] lElem = new byte[length];
                            int a2 = -1;
                            int b3 = -1;
                            int c3 = 0;
                            int index3 = 0;
                            int s2 = -1;
                            while (encode.hasRemaining()) {
                                int d2;
                                b3 = (a2 = (c3 = (d2 = -1)));
                                for (s2 = safeGet(encode); (a2 = Pack.b64_xtable[s2]) == -1 && encode.hasRemaining(); s2 = safeGet(encode)) {}
                                if (a2 == -1) {
                                    break;
                                }
                                for (s2 = safeGet(encode); (b3 = Pack.b64_xtable[s2]) == -1 && encode.hasRemaining(); s2 = safeGet(encode)) {}
                                if (b3 == -1) {
                                    break;
                                }
                                for (s2 = safeGet(encode); (c3 = Pack.b64_xtable[s2]) == -1 && encode.hasRemaining() && s2 != 61; s2 = safeGet(encode)) {}
                                if (s2 == 61 || c3 == -1) {
                                    if (s2 == 61) {
                                        encode.position(encode.position() - 1);
                                        break;
                                    }
                                    break;
                                }
                                else {
                                    for (s2 = safeGet(encode); (d2 = Pack.b64_xtable[s2]) == -1 && encode.hasRemaining() && s2 != 61; s2 = safeGet(encode)) {}
                                    if (s2 == 61 || d2 == -1) {
                                        if (s2 == 61) {
                                            encode.position(encode.position() - 1);
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        lElem[index3++] = (byte)((a2 << 2 | b3 >> 4) & 0xFF);
                                        lElem[index3++] = (byte)((b3 << 4 | c3 >> 2) & 0xFF);
                                        lElem[index3++] = (byte)((c3 << 6 | d2) & 0xFF);
                                    }
                                }
                            }
                            if (a2 != -1 && b3 != -1) {
                                if (c3 == -1 && s2 == 61) {
                                    lElem[index3++] = (byte)((a2 << 2 | b3 >> 4) & 0xFF);
                                }
                                else if (c3 != -1 && s2 == 61) {
                                    lElem[index3++] = (byte)((a2 << 2 | b3 >> 4) & 0xFF);
                                    lElem[index3++] = (byte)((b3 << 4 | c3 >> 2) & 0xFF);
                                }
                            }
                            result.append(RubyString.newString(runtime, new ByteList(lElem, 0, index3, ASCIIEncoding.INSTANCE, false)));
                            continue;
                        }
                        case 77: {
                            final byte[] lElem2 = new byte[Math.max(encode.remaining(), 0)];
                            int index4 = 0;
                            while (true) {
                                while (encode.hasRemaining()) {
                                    final int c4 = safeGet(encode);
                                    if (c4 == 61) {
                                        if (encode.hasRemaining()) {
                                            encode.mark();
                                            int c5 = safeGet(encode);
                                            if (c5 == 10) {
                                                continue Label_2491_Outer;
                                            }
                                            if (c5 == 13 && (c5 = safeGet(encode)) == 10) {
                                                continue Label_2491_Outer;
                                            }
                                            final int d3 = Character.digit(c5, 16);
                                            if (d3 == -1) {
                                                encode.reset();
                                            }
                                            else {
                                                encode.mark();
                                                if (encode.hasRemaining()) {
                                                    final int c6 = safeGet(encode);
                                                    final int d4 = Character.digit(c6, 16);
                                                    if (d4 != -1) {
                                                        final byte value = (byte)(d3 << 4 | d4);
                                                        lElem2[index4++] = value;
                                                        continue Label_2491_Outer;
                                                    }
                                                    encode.reset();
                                                }
                                            }
                                        }
                                        result.append(RubyString.newString(runtime, new ByteList(lElem2, 0, index4, ASCIIEncoding.INSTANCE, false)));
                                        break Label_2591;
                                    }
                                    lElem2[index4++] = (byte)c4;
                                }
                                continue;
                            }
                        }
                        case 85: {
                            if (occurrences == -1 || occurrences > encode.remaining()) {
                                occurrences = encode.remaining();
                            }
                            while (occurrences-- > 0 && encode.remaining() > 0) {
                                try {
                                    result.append(runtime.newFixnum(utf8Decode(encode)));
                                    continue;
                                }
                                catch (IllegalArgumentException e) {
                                    throw runtime.newArgumentError(e.getMessage());
                                }
                                break Label_2591;
                            }
                            continue;
                        }
                        case 88: {
                            if (occurrences == -1) {
                                occurrences = encode.remaining();
                            }
                            try {
                                encode.position(encode.position() - occurrences);
                                continue;
                            }
                            catch (IllegalArgumentException e) {
                                throw runtime.newArgumentError("in `unpack': X outside of string");
                            }
                        }
                        case 120: {
                            if (occurrences == -1) {
                                occurrences = encode.remaining();
                            }
                            try {
                                encode.position(encode.position() + occurrences);
                                continue;
                            }
                            catch (IllegalArgumentException e) {
                                throw runtime.newArgumentError("in `unpack': x outside of string");
                            }
                        }
                        case 119: {
                            if (occurrences == -1 || occurrences > encode.remaining()) {
                                occurrences = encode.remaining();
                            }
                            long ul = 0L;
                            final long ulmask = -33554432L;
                            final RubyBignum big128 = RubyBignum.newBignum(runtime, 128L);
                            int pos = encode.position();
                            while (occurrences > 0 && pos < encode.limit()) {
                                ul <<= 7;
                                ul |= (encode.get(pos) & 0x7F);
                                if ((encode.get(pos++) & 0x80) == 0x0) {
                                    result.append(RubyFixnum.newFixnum(runtime, ul));
                                    --occurrences;
                                    ul = 0L;
                                }
                                else {
                                    if ((ul & ulmask) != 0x0L) {
                                        continue;
                                    }
                                    RubyBignum big129 = RubyBignum.newBignum(runtime, ul);
                                    while (occurrences > 0 && pos < encode.limit()) {
                                        big129 = (RubyBignum)big129.op_mul(runtime.getCurrentContext(), big128);
                                        final IRubyObject v = big129.op_plus(runtime.getCurrentContext(), RubyBignum.newBignum(runtime, encode.get(pos) & 0x7F));
                                        if (v instanceof RubyFixnum) {
                                            big129 = RubyBignum.newBignum(runtime, RubyNumeric.fix2long(v));
                                        }
                                        else if (v instanceof RubyBignum) {
                                            big129 = (RubyBignum)v;
                                        }
                                        if ((encode.get(pos++) & 0x80) == 0x0) {
                                            result.add(big129);
                                            --occurrences;
                                            ul = 0L;
                                            break;
                                        }
                                    }
                                }
                            }
                            try {
                                encode.position(pos);
                            }
                            catch (IllegalArgumentException e2) {
                                throw runtime.newArgumentError("in `unpack': poorly encoded input");
                            }
                            continue;
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public static int utf8Decode(final Ruby runtime, final byte[] to, final int p, final int code) {
        if (code <= 127) {
            to[p] = (byte)code;
            return 1;
        }
        if (code <= 2047) {
            to[p + 0] = (byte)((code >>> 6 & 0xFF) | 0xC0);
            to[p + 1] = (byte)((code & 0x3F) | 0x80);
            return 2;
        }
        if (code <= 65535) {
            to[p + 0] = (byte)((code >>> 12 & 0xFF) | 0xE0);
            to[p + 1] = (byte)((code >>> 6 & 0x3F) | 0x80);
            to[p + 2] = (byte)((code & 0x3F) | 0x80);
            return 3;
        }
        if (code <= 2097151) {
            to[p + 0] = (byte)((code >>> 18 & 0xFF) | 0xF0);
            to[p + 1] = (byte)((code >>> 12 & 0x3F) | 0x80);
            to[p + 2] = (byte)((code >>> 6 & 0x3F) | 0x80);
            to[p + 3] = (byte)((code & 0x3F) | 0x80);
            return 4;
        }
        if (code <= 67108863) {
            to[p + 0] = (byte)((code >>> 24 & 0xFF) | 0xF8);
            to[p + 1] = (byte)((code >>> 18 & 0x3F) | 0x80);
            to[p + 2] = (byte)((code >>> 12 & 0x3F) | 0x80);
            to[p + 3] = (byte)((code >>> 6 & 0x3F) | 0x80);
            to[p + 4] = (byte)((code & 0x3F) | 0x80);
            return 5;
        }
        if (code <= Integer.MAX_VALUE) {
            to[p + 0] = (byte)((code >>> 30 & 0xFF) | 0xFC);
            to[p + 1] = (byte)((code >>> 24 & 0x3F) | 0x80);
            to[p + 2] = (byte)((code >>> 18 & 0x3F) | 0x80);
            to[p + 3] = (byte)((code >>> 12 & 0x3F) | 0x80);
            to[p + 4] = (byte)((code >>> 6 & 0x3F) | 0x80);
            to[p + 5] = (byte)((code & 0x3F) | 0x80);
            return 6;
        }
        throw runtime.newRangeError("pack(U): value out of range");
    }
    
    private static int utf8Decode(final ByteBuffer buffer) {
        int uv;
        int c = uv = (buffer.get() & 0xFF);
        if ((c & 0x80) == 0x0) {
            return c;
        }
        if ((c & 0x40) == 0x0) {
            throw new IllegalArgumentException("malformed UTF-8 character");
        }
        int n;
        if ((uv & 0x20) == 0x0) {
            n = 2;
            uv &= 0x1F;
        }
        else if ((uv & 0x10) == 0x0) {
            n = 3;
            uv &= 0xF;
        }
        else if ((uv & 0x8) == 0x0) {
            n = 4;
            uv &= 0x7;
        }
        else if ((uv & 0x4) == 0x0) {
            n = 5;
            uv &= 0x3;
        }
        else {
            if ((uv & 0x2) != 0x0) {
                throw new IllegalArgumentException("malformed UTF-8 character");
            }
            n = 6;
            uv &= 0x1;
        }
        if (n > buffer.remaining() + 1) {
            throw new IllegalArgumentException("malformed UTF-8 character (expected " + n + " bytes, " + "given " + (buffer.remaining() + 1) + " bytes)");
        }
        final int limit = n - 1;
        if (--n != 0) {
            while (n-- != 0) {
                c = (buffer.get() & 0xFF);
                if ((c & 0xC0) != 0x80) {
                    throw new IllegalArgumentException("malformed UTF-8 character");
                }
                c &= 0x3F;
                uv = (uv << 6 | c);
            }
        }
        if (uv < Pack.utf8_limits[limit]) {
            throw new IllegalArgumentException("redundant UTF-8 sequence");
        }
        return uv;
    }
    
    private static int safeGet(final ByteBuffer encode) {
        return encode.hasRemaining() ? (encode.get() & 0xFF) : 0;
    }
    
    private static int safeGetIgnoreNull(final ByteBuffer encode) {
        int next;
        for (next = 0; encode.hasRemaining() && next == 0; next = safeGet(encode)) {}
        return next;
    }
    
    public static void decode(final Ruby runtime, final ByteBuffer encode, int occurrences, final RubyArray result, final Converter converter) {
        int lPadLength = 0;
        if (occurrences == -1) {
            occurrences = encode.remaining() / converter.size;
        }
        else if (occurrences > encode.remaining() / converter.size) {
            lPadLength = occurrences - encode.remaining() / converter.size;
            occurrences = encode.remaining() / converter.size;
        }
        while (occurrences-- > 0) {
            result.append(converter.decode(runtime, encode));
        }
        if (converter != Pack.converters[81]) {
            while (lPadLength-- > 0) {
                result.append(runtime.getNil());
            }
        }
    }
    
    public static int encode(final Ruby runtime, int occurrences, final ByteList result, final RubyArray list, int index, final ConverterExecutor converter) {
        int listSize = list.size();
        while (occurrences-- > 0) {
            if (listSize-- <= 0 || index >= list.size()) {
                throw runtime.newArgumentError("too few arguments");
            }
            final IRubyObject from = list.eltInternal(index++);
            converter.encode(runtime, from, result);
        }
        return index;
    }
    
    private static ConverterExecutor executor18() {
        return new ConverterExecutor() {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer format) {
                return this.converter.decode(runtime, format);
            }
            
            public void encode(final Ruby runtime, final IRubyObject from, final ByteList result) {
                this.converter.encode(runtime, from, result);
            }
        };
    }
    
    private static ConverterExecutor executor19() {
        return new ConverterExecutor() {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer format) {
                return this.converter.decode19(runtime, format);
            }
            
            public void encode(final Ruby runtime, final IRubyObject from, final ByteList result) {
                this.converter.encode19(runtime, from, result);
            }
        };
    }
    
    private static final ByteList shrink(final ByteList i2Shrink, int iLength) {
        iLength = i2Shrink.length() - iLength;
        if (iLength < 0) {
            throw new IllegalArgumentException();
        }
        i2Shrink.length(iLength);
        return i2Shrink;
    }
    
    private static final ByteList grow(final ByteList i2Grow, final byte[] iPads, int iLength) {
        for (int lPadLength = iPads.length; iLength >= lPadLength; iLength -= lPadLength) {
            i2Grow.append(iPads);
        }
        i2Grow.append(iPads, 0, iLength);
        return i2Grow;
    }
    
    public static RubyString pack(final Ruby runtime, final RubyArray list, final ByteList formatString, final boolean taint) {
        return packCommon(runtime, list, formatString, taint, executor18());
    }
    
    public static RubyString pack(final Ruby runtime, final RubyArray list, final ByteList formatString) {
        return packCommon(runtime, list, formatString, false, executor18());
    }
    
    public static RubyString pack19(final ThreadContext context, final Ruby runtime, final RubyArray list, final RubyString formatString) {
        RubyString pack = packCommon(runtime, list, formatString.getByteList(), formatString.isTaint(), executor19());
        pack = (RubyString)pack.infectBy((IRubyObject)formatString);
        for (final IRubyObject element : list.toJavaArray()) {
            if (element.isUntrusted()) {
                pack = (RubyString)pack.untrust(context);
                break;
            }
        }
        return pack;
    }
    
    private static RubyString packCommon(final Ruby runtime, final RubyArray list, final ByteList formatString, final boolean tainted, final ConverterExecutor executor) {
        final ByteBuffer format = ByteBuffer.wrap(formatString.getUnsafeBytes(), formatString.begin(), formatString.length());
        final ByteList result = new ByteList();
        boolean taintOutput = tainted;
        int listSize = list.size();
        int type = 0;
        int next = safeGet(format);
        int idx = 0;
        int enc_info = 1;
    Label_2386:
        while (next != 0) {
            type = next;
            next = safeGet(format);
            if ("cCiIlLnNqQsSvV".indexOf(type) != -1 && next == 0) {
                next = safeGetIgnoreNull(format);
            }
            while (Pack.ASCII.isSpace(type)) {
                if (next == 0) {
                    break Label_2386;
                }
                type = next;
                next = safeGet(format);
            }
            if (type == 35) {
                while (type != 10) {
                    if (next == 0) {
                        break Label_2386;
                    }
                    type = next;
                    next = safeGet(format);
                }
            }
            if (next == 33 || next == 95) {
                final int index = "sSiIlL".indexOf(type);
                if (index == -1) {
                    throw runtime.newArgumentError("'" + next + "' allowed only after types " + "sSiIlL");
                }
                final int typeBeforeMap = type;
                type = "sSiIqQ".charAt(index);
                next = safeGet(format);
                if ("lLsS".indexOf(typeBeforeMap) != -1 && next == 0) {
                    next = safeGetIgnoreNull(format);
                }
            }
            int occurrences = 1;
            boolean isStar = false;
            boolean ignoreStar = false;
            if (next != 0) {
                if (next == 42) {
                    if ("@XxumM".indexOf(type) != -1) {
                        occurrences = 0;
                        ignoreStar = true;
                    }
                    else {
                        occurrences = list.size() - idx;
                        isStar = true;
                    }
                    next = safeGet(format);
                }
                else if (Pack.ASCII.isDigit(next)) {
                    occurrences = 0;
                    do {
                        occurrences = occurrences * 10 + Character.digit((char)(next & 0xFF), 10);
                        next = safeGet(format);
                    } while (next != 0 && Pack.ASCII.isDigit(next));
                }
            }
            if (runtime.is1_9()) {
                switch (type) {
                    case 85: {
                        if (enc_info == 1) {
                            enc_info = 2;
                            break;
                        }
                        break;
                    }
                    case 77:
                    case 109:
                    case 117: {
                        break;
                    }
                    default: {
                        enc_info = 0;
                        break;
                    }
                }
            }
            final Converter converter = Pack.converters[type];
            if (converter != null) {
                executor.setConverter(converter);
                idx = encode(runtime, occurrences, result, list, idx, executor);
            }
            else {
                switch (type) {
                    case 37: {
                        throw runtime.newArgumentError("% is not supported");
                    }
                    case 65:
                    case 66:
                    case 72:
                    case 90:
                    case 97:
                    case 98:
                    case 104: {
                        if (listSize-- <= 0) {
                            throw runtime.newArgumentError("too few arguments");
                        }
                        final IRubyObject from = list.eltInternal(idx++);
                        if (from.isTaint()) {
                            taintOutput = true;
                        }
                        final ByteList lCurElemString = (from == runtime.getNil()) ? ByteList.EMPTY_BYTELIST : from.convertToString().getByteList();
                        if (isStar) {
                            occurrences = lCurElemString.length();
                            if (type == 90) {
                                ++occurrences;
                            }
                        }
                        switch (type) {
                            case 65:
                            case 90:
                            case 97: {
                                if (lCurElemString.length() >= occurrences) {
                                    result.append(lCurElemString.getUnsafeBytes(), lCurElemString.getBegin(), occurrences);
                                    continue;
                                }
                                result.append(lCurElemString);
                                occurrences -= lCurElemString.length();
                                switch (type) {
                                    case 90:
                                    case 97: {
                                        grow(result, Pack.sNil10, occurrences);
                                        continue;
                                    }
                                    default: {
                                        grow(result, Pack.sSp10, occurrences);
                                        continue;
                                    }
                                }
                                break;
                            }
                            case 98: {
                                int currentByte = 0;
                                int padLength = 0;
                                if (occurrences > lCurElemString.length()) {
                                    padLength = (occurrences - lCurElemString.length()) / 2 + occurrences % 2;
                                    occurrences = lCurElemString.length();
                                }
                                int i = 0;
                                while (i < occurrences) {
                                    if ((lCurElemString.charAt(i++) & '\u0001') != '\0') {
                                        currentByte |= 0x80;
                                    }
                                    if ((i & 0x7) == 0x0) {
                                        result.append((byte)(currentByte & 0xFF));
                                        currentByte = 0;
                                    }
                                    else {
                                        currentByte >>= 1;
                                    }
                                }
                                if ((occurrences & 0x7) != 0x0) {
                                    currentByte >>= 7 - (occurrences & 0x7);
                                    result.append((byte)(currentByte & 0xFF));
                                }
                                result.length(result.length() + padLength);
                                continue;
                            }
                            case 66: {
                                int currentByte = 0;
                                int padLength = 0;
                                if (occurrences > lCurElemString.length()) {
                                    padLength = (occurrences - lCurElemString.length()) / 2 + occurrences % 2;
                                    occurrences = lCurElemString.length();
                                }
                                int i = 0;
                                while (i < occurrences) {
                                    currentByte |= (lCurElemString.charAt(i++) & '\u0001');
                                    if ((i & 0x7) == 0x0) {
                                        result.append((byte)(currentByte & 0xFF));
                                        currentByte = 0;
                                    }
                                    else {
                                        currentByte <<= 1;
                                    }
                                }
                                if ((occurrences & 0x7) != 0x0) {
                                    currentByte <<= 7 - (occurrences & 0x7);
                                    result.append((byte)(currentByte & 0xFF));
                                }
                                result.length(result.length() + padLength);
                                continue;
                            }
                            case 104: {
                                int currentByte = 0;
                                int padLength = 0;
                                if (occurrences > lCurElemString.length()) {
                                    padLength = occurrences - lCurElemString.length() + 1;
                                    occurrences = lCurElemString.length();
                                }
                                int i = 0;
                                while (i < occurrences) {
                                    final byte currentChar = (byte)lCurElemString.charAt(i++);
                                    if (Character.isJavaIdentifierStart(currentChar)) {
                                        currentByte |= ((currentChar & 0xF) + 9 & 0xF) << 4;
                                    }
                                    else {
                                        currentByte |= (currentChar & 0xF) << 4;
                                    }
                                    if ((i & 0x1) != 0x0) {
                                        currentByte >>= 4;
                                    }
                                    else {
                                        result.append((byte)(currentByte & 0xFF));
                                        currentByte = 0;
                                    }
                                }
                                if ((occurrences & 0x1) != 0x0) {
                                    result.append((byte)(currentByte & 0xFF));
                                    if (padLength > 0) {
                                        --padLength;
                                    }
                                }
                                result.length(result.length() + padLength / 2);
                                continue;
                            }
                            case 72: {
                                int currentByte = 0;
                                int padLength = 0;
                                if (occurrences > lCurElemString.length()) {
                                    padLength = occurrences - lCurElemString.length() + 1;
                                    occurrences = lCurElemString.length();
                                }
                                int i = 0;
                                while (i < occurrences) {
                                    final byte currentChar = (byte)lCurElemString.charAt(i++);
                                    if (Character.isJavaIdentifierStart(currentChar)) {
                                        currentByte |= ((currentChar & 0xF) + 9 & 0xF);
                                    }
                                    else {
                                        currentByte |= (currentChar & 0xF);
                                    }
                                    if ((i & 0x1) != 0x0) {
                                        currentByte <<= 4;
                                    }
                                    else {
                                        result.append((byte)(currentByte & 0xFF));
                                        currentByte = 0;
                                    }
                                }
                                if ((occurrences & 0x1) != 0x0) {
                                    result.append((byte)(currentByte & 0xFF));
                                    if (padLength > 0) {
                                        --padLength;
                                    }
                                }
                                result.length(result.length() + padLength / 2);
                                continue;
                            }
                        }
                        continue;
                    }
                    case 120: {
                        grow(result, Pack.sNil10, occurrences);
                        continue;
                    }
                    case 88: {
                        try {
                            shrink(result, occurrences);
                            continue;
                        }
                        catch (IllegalArgumentException e) {
                            throw runtime.newArgumentError("in `pack': X outside of string");
                        }
                    }
                    case 64: {
                        occurrences -= result.length();
                        if (occurrences > 0) {
                            grow(result, Pack.sNil10, occurrences);
                        }
                        occurrences = -occurrences;
                        if (occurrences > 0) {
                            shrink(result, occurrences);
                            continue;
                        }
                        continue;
                    }
                    case 109:
                    case 117: {
                        if (listSize-- <= 0) {
                            throw runtime.newArgumentError("too few arguments");
                        }
                        final IRubyObject from = list.eltInternal(idx++);
                        final ByteList lCurElemString = (from == runtime.getNil()) ? ByteList.EMPTY_BYTELIST : from.convertToString().getByteList();
                        if (runtime.is1_9() && occurrences == 0 && type == 109 && !ignoreStar) {
                            encodes(runtime, result, lCurElemString.getUnsafeBytes(), lCurElemString.getBegin(), lCurElemString.length(), lCurElemString.length(), (byte)type, false);
                            continue;
                        }
                        occurrences = ((occurrences <= 2) ? 45 : (occurrences / 3 * 3));
                        if (lCurElemString.length() == 0) {
                            continue;
                        }
                        final byte[] charsToEncode = lCurElemString.getUnsafeBytes();
                        for (int j = 0; j < lCurElemString.length(); j += occurrences) {
                            encodes(runtime, result, charsToEncode, j + lCurElemString.getBegin(), lCurElemString.length() - j, occurrences, (byte)type, true);
                        }
                        continue;
                    }
                    case 77: {
                        if (listSize-- <= 0) {
                            throw runtime.newArgumentError("too few arguments");
                        }
                        final IRubyObject from = list.eltInternal(idx++);
                        final ByteList lCurElemString = (from == runtime.getNil()) ? ByteList.EMPTY_BYTELIST : from.asString().getByteList();
                        if (occurrences <= 1) {
                            occurrences = 72;
                        }
                        qpencode(result, lCurElemString, occurrences);
                        continue;
                    }
                    case 85: {
                        while (occurrences-- > 0) {
                            if (listSize-- <= 0) {
                                throw runtime.newArgumentError("too few arguments");
                            }
                            final IRubyObject from = list.eltInternal(idx++);
                            final int code = (from == runtime.getNil()) ? 0 : RubyNumeric.num2int(from);
                            if (code < 0) {
                                throw runtime.newRangeError("pack(U): value out of range");
                            }
                            result.ensure(result.getRealSize() + 6);
                            result.setRealSize(result.getRealSize() + utf8Decode(runtime, result.getUnsafeBytes(), result.getBegin() + result.getRealSize(), code));
                        }
                        continue;
                    }
                    case 119: {
                        while (occurrences-- > 0) {
                            if (listSize-- <= 0) {
                                throw runtime.newArgumentError("too few arguments");
                            }
                            final ByteList buf = new ByteList();
                            IRubyObject from2 = list.eltInternal(idx++);
                            if (from2.isNil()) {
                                throw runtime.newTypeError("pack('w') does not take nil");
                            }
                            if (from2 instanceof RubyBignum) {
                                final RubyBignum big128 = RubyBignum.newBignum(runtime, 128L);
                                while (from2 instanceof RubyBignum) {
                                    final RubyBignum bignum = (RubyBignum)from2;
                                    final RubyArray ary = (RubyArray)bignum.divmod(runtime.getCurrentContext(), big128);
                                    buf.append((byte)(RubyNumeric.fix2int(ary.at(RubyFixnum.one(runtime))) | 0x80) & 0xFF);
                                    from2 = ary.at(RubyFixnum.zero(runtime));
                                }
                            }
                            long l = RubyNumeric.num2long(from2);
                            if (l < 0L) {
                                throw runtime.newArgumentError("can't compress negative numbers");
                            }
                            while (l != 0L) {
                                buf.append((byte)(((l & 0x7FL) | 0x80L) & 0xFFL));
                                l >>= 7;
                            }
                            int left = 0;
                            int right = buf.getRealSize() - 1;
                            if (right >= 0) {
                                final byte[] unsafeBytes = buf.getUnsafeBytes();
                                final int n = 0;
                                unsafeBytes[n] &= 0x7F;
                            }
                            else {
                                buf.append(0);
                            }
                            while (left < right) {
                                final byte tmp = buf.getUnsafeBytes()[left];
                                buf.getUnsafeBytes()[left] = buf.getUnsafeBytes()[right];
                                buf.getUnsafeBytes()[right] = tmp;
                                ++left;
                                --right;
                            }
                            result.append(buf);
                        }
                        continue;
                    }
                }
            }
        }
        final RubyString output = runtime.newString(result);
        if (taintOutput) {
            output.taint(runtime.getCurrentContext());
        }
        if (runtime.is1_9()) {
            switch (enc_info) {
                case 1: {
                    output.setEncodingAndCodeRange(Pack.USASCII, 8192);
                    break;
                }
                case 2: {
                    output.force_encoding(runtime.getCurrentContext(), runtime.getEncodingService().convertEncodingToRubyEncoding(Pack.UTF8));
                    break;
                }
            }
        }
        return output;
    }
    
    private static int decodeIntLittleEndian(final ByteBuffer encode) {
        encode.order(ByteOrder.LITTLE_ENDIAN);
        final int value = encode.getInt();
        encode.order(ByteOrder.BIG_ENDIAN);
        return value;
    }
    
    private static int decodeIntBigEndian(final ByteBuffer encode) {
        return encode.getInt();
    }
    
    private static long decodeIntUnsignedBigEndian(final ByteBuffer encode) {
        return encode.getInt() & 0xFFFFFFFFL;
    }
    
    private static long decodeIntUnsignedLittleEndian(final ByteBuffer encode) {
        encode.order(ByteOrder.LITTLE_ENDIAN);
        final long value = encode.getInt() & 0xFFFFFFFFL;
        encode.order(ByteOrder.BIG_ENDIAN);
        return value;
    }
    
    private static void encodeIntLittleEndian(final ByteList result, final int s) {
        result.append((byte)(s & 0xFF)).append((byte)(s >> 8 & 0xFF));
        result.append((byte)(s >> 16 & 0xFF)).append((byte)(s >> 24 & 0xFF));
    }
    
    private static void encodeIntBigEndian(final ByteList result, final int s) {
        result.append((byte)(s >> 24 & 0xFF)).append((byte)(s >> 16 & 0xFF));
        result.append((byte)(s >> 8 & 0xFF)).append((byte)(s & 0xFF));
    }
    
    private static long decodeLongBigEndian(final ByteBuffer encode) {
        final int c1 = decodeIntBigEndian(encode);
        final int c2 = decodeIntBigEndian(encode);
        return (c1 << 32) + (c2 & 0xFFFFFFFFL);
    }
    
    private static long decodeLongLittleEndian(final ByteBuffer encode) {
        final int c1 = decodeIntLittleEndian(encode);
        final int c2 = decodeIntLittleEndian(encode);
        return (c2 << 32) + (c1 & 0xFFFFFFFFL);
    }
    
    private static void encodeLongLittleEndian(final ByteList result, final long l) {
        encodeIntLittleEndian(result, (int)(l & -1L));
        encodeIntLittleEndian(result, (int)(l >>> 32));
    }
    
    private static void encodeLongBigEndian(final ByteList result, final long l) {
        encodeIntBigEndian(result, (int)(l >>> 32));
        encodeIntBigEndian(result, (int)(l & -1L));
    }
    
    private static double decodeDoubleLittleEndian(final ByteBuffer encode) {
        return Double.longBitsToDouble(decodeLongLittleEndian(encode));
    }
    
    private static double decodeDoubleBigEndian(final ByteBuffer encode) {
        return Double.longBitsToDouble(decodeLongBigEndian(encode));
    }
    
    private static void encodeDoubleLittleEndian(final ByteList result, final double d) {
        encodeLongLittleEndian(result, Double.doubleToRawLongBits(d));
    }
    
    private static void encodeDoubleBigEndian(final ByteList result, final double d) {
        encodeLongBigEndian(result, Double.doubleToRawLongBits(d));
    }
    
    private static float decodeFloatBigEndian(final ByteBuffer encode) {
        return Float.intBitsToFloat(decodeIntBigEndian(encode));
    }
    
    private static float decodeFloatLittleEndian(final ByteBuffer encode) {
        return Float.intBitsToFloat(decodeIntLittleEndian(encode));
    }
    
    private static void encodeFloatLittleEndian(final ByteList result, final float f) {
        encodeIntLittleEndian(result, Float.floatToRawIntBits(f));
    }
    
    private static void encodeFloatBigEndian(final ByteList result, final float f) {
        encodeIntBigEndian(result, Float.floatToRawIntBits(f));
    }
    
    private static int decodeShortUnsignedLittleEndian(final ByteBuffer encode) {
        encode.order(ByteOrder.LITTLE_ENDIAN);
        final int value = encode.getShort() & 0xFFFF;
        encode.order(ByteOrder.BIG_ENDIAN);
        return value;
    }
    
    private static int decodeShortUnsignedBigEndian(final ByteBuffer encode) {
        final int value = encode.getShort() & 0xFFFF;
        return value;
    }
    
    private static int decodeShortLittleEndian(final ByteBuffer encode) {
        encode.order(ByteOrder.LITTLE_ENDIAN);
        final int value = encode.getShort();
        encode.order(ByteOrder.BIG_ENDIAN);
        return value;
    }
    
    private static short decodeShortBigEndian(final ByteBuffer encode) {
        return encode.getShort();
    }
    
    private static void encodeShortLittleEndian(final ByteList result, final int s) {
        result.append((byte)(s & 0xFF)).append((byte)((s & 0xFF00) >> 8));
    }
    
    private static void encodeShortBigEndian(final ByteList result, final int s) {
        result.append((byte)((s & 0xFF00) >> 8)).append((byte)(s & 0xFF));
    }
    
    static {
        sSp10 = "          ".getBytes();
        sNil10 = "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000".getBytes();
        ASCII = ASCIIEncoding.INSTANCE;
        USASCII = USASCIIEncoding.INSTANCE;
        UTF8 = UTF8Encoding.INSTANCE;
        b64_xtable = new int[256];
        converters = new Converter[256];
        QUAD_MIN = new BigInteger("-ffffffffffffffff", 16);
        QUAD_MAX = new BigInteger("ffffffffffffffff", 16);
        hex_table = ByteList.plain("0123456789ABCDEF");
        uu_table = ByteList.plain("`!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_");
        b64_table = ByteList.plain("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        sHexDigits = ByteList.plain("0123456789abcdef0123456789ABCDEFx");
        for (int i = 0; i < 256; ++i) {
            Pack.b64_xtable[i] = -1;
        }
        for (int i = 0; i < 64; ++i) {
            Pack.b64_xtable[Pack.b64_table[i]] = i;
        }
        Pack.converters[101] = new Converter(4) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                return RubyFloat.newFloat(runtime, decodeFloatLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                encodeFloatLittleEndian(result, obj2flt(runtime, o));
            }
        };
        Pack.converters[103] = new Converter(4) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                return RubyFloat.newFloat(runtime, decodeFloatBigEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                encodeFloatBigEndian(result, obj2flt(runtime, o));
            }
        };
        Converter tmp = new Converter(4) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                if (Platform.BYTE_ORDER == 4321) {
                    return RubyFloat.newFloat(runtime, decodeFloatBigEndian(enc));
                }
                return RubyFloat.newFloat(runtime, decodeFloatLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                if (Platform.BYTE_ORDER == 4321) {
                    encodeFloatBigEndian(result, obj2flt(runtime, o));
                }
                else {
                    encodeFloatLittleEndian(result, obj2flt(runtime, o));
                }
            }
        };
        Pack.converters[70] = tmp;
        Pack.converters[102] = tmp;
        Pack.converters[69] = new Converter(8) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                return RubyFloat.newFloat(runtime, decodeDoubleLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                encodeDoubleLittleEndian(result, obj2dbl(runtime, o));
            }
        };
        Pack.converters[71] = new Converter(8) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                return RubyFloat.newFloat(runtime, decodeDoubleBigEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                encodeDoubleBigEndian(result, obj2dbl(runtime, o));
            }
        };
        tmp = new Converter(8) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                if (Platform.BYTE_ORDER == 4321) {
                    return RubyFloat.newFloat(runtime, decodeDoubleBigEndian(enc));
                }
                return RubyFloat.newFloat(runtime, decodeDoubleLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                if (Platform.BYTE_ORDER == 4321) {
                    encodeDoubleBigEndian(result, obj2dbl(runtime, o));
                }
                else {
                    encodeDoubleLittleEndian(result, obj2dbl(runtime, o));
                }
            }
        };
        Pack.converters[68] = tmp;
        Pack.converters[100] = tmp;
        Pack.converters[118] = new QuadConverter(2) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                return runtime.newFixnum(decodeShortUnsignedLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : this.overflowQuad(num2quad(o));
                encodeShortLittleEndian(result, s);
            }
            
            public void encode19(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : this.overflowQuad(num2quad19(o));
                encodeShortLittleEndian(result, s);
            }
        };
        Pack.converters[110] = new QuadConverter(2) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                return runtime.newFixnum(decodeShortUnsignedBigEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : this.overflowQuad(num2quad(o));
                encodeShortBigEndian(result, s);
            }
            
            public void encode19(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : this.overflowQuad(num2quad19(o));
                encodeShortBigEndian(result, s);
            }
        };
        Pack.converters[115] = new QuadConverter(2) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                if (Platform.BYTE_ORDER == 4321) {
                    return runtime.newFixnum(decodeShortBigEndian(enc));
                }
                return runtime.newFixnum(decodeShortLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : this.overflowQuad(num2quad(o));
                this.encodeShortByByteOrder(result, s);
            }
            
            public void encode19(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : this.overflowQuad(num2quad19(o));
                this.encodeShortByByteOrder(result, s);
            }
        };
        Pack.converters[83] = new QuadConverter(2) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                if (Platform.BYTE_ORDER == 4321) {
                    return runtime.newFixnum(decodeShortUnsignedBigEndian(enc));
                }
                return runtime.newFixnum(decodeShortUnsignedLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : this.overflowQuad(num2quad(o));
                this.encodeShortByByteOrder(result, s);
            }
            
            public void encode19(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : this.overflowQuad(num2quad19(o));
                this.encodeShortByByteOrder(result, s);
            }
        };
        Pack.converters[99] = new Converter(1) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                final int c = enc.get();
                return runtime.newFixnum((c > 127) ? (c - 256) : c);
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final byte c = (byte)((o == runtime.getNil()) ? 0 : ((byte)(RubyNumeric.num2long(o) & 0xFFL)));
                result.append(c);
            }
        };
        Pack.converters[67] = new Converter(1) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                return runtime.newFixnum(enc.get() & 0xFF);
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final byte c = (byte)((o == runtime.getNil()) ? 0 : ((byte)(RubyNumeric.num2long(o) & 0xFFL)));
                result.append(c);
            }
        };
        Pack.converters[86] = new Converter(4) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                return runtime.newFixnum(decodeIntUnsignedLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : ((int)RubyNumeric.num2long(o));
                encodeIntLittleEndian(result, s);
            }
        };
        Pack.converters[78] = new Converter(4) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                return runtime.newFixnum(decodeIntUnsignedBigEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : ((int)RubyNumeric.num2long(o));
                encodeIntBigEndian(result, s);
            }
        };
        tmp = new Converter(4) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                if (Platform.BYTE_ORDER == 4321) {
                    return runtime.newFixnum(decodeIntUnsignedBigEndian(enc));
                }
                return runtime.newFixnum(decodeIntUnsignedLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : ((int)RubyNumeric.num2long(o));
                if (Platform.BYTE_ORDER == 4321) {
                    encodeIntBigEndian(result, s);
                }
                else {
                    encodeIntLittleEndian(result, s);
                }
            }
        };
        Pack.converters[73] = tmp;
        Pack.converters[76] = tmp;
        tmp = new Converter(4) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                if (Platform.BYTE_ORDER == 4321) {
                    return runtime.newFixnum(decodeIntBigEndian(enc));
                }
                return runtime.newFixnum(decodeIntLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final int s = (o == runtime.getNil()) ? 0 : ((int)RubyNumeric.num2long(o));
                if (Platform.BYTE_ORDER == 4321) {
                    encodeIntBigEndian(result, s);
                }
                else {
                    encodeIntLittleEndian(result, s);
                }
            }
        };
        Pack.converters[105] = tmp;
        Pack.converters[108] = tmp;
        Pack.converters[81] = new QuadConverter(8) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                long l;
                if (Platform.BYTE_ORDER == 4321) {
                    l = decodeLongBigEndian(enc);
                }
                else {
                    l = decodeLongLittleEndian(enc);
                }
                return RubyBignum.bignorm(runtime, BigInteger.valueOf(l).and(new BigInteger("FFFFFFFFFFFFFFFF", 16)));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final long l = num2quad(o);
                this.encodeLongByByteOrder(result, l);
            }
            
            public void encode19(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final long l = num2quad19(o);
                this.encodeLongByByteOrder(result, l);
            }
        };
        Pack.converters[113] = new QuadConverter(8) {
            public IRubyObject decode(final Ruby runtime, final ByteBuffer enc) {
                if (Platform.BYTE_ORDER == 4321) {
                    return runtime.newFixnum(decodeLongBigEndian(enc));
                }
                return runtime.newFixnum(decodeLongLittleEndian(enc));
            }
            
            public void encode(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final long l = num2quad(o);
                this.encodeLongByByteOrder(result, l);
            }
            
            public void encode19(final Ruby runtime, final IRubyObject o, final ByteList result) {
                final long l = num2quad19(o);
                this.encodeLongByByteOrder(result, l);
            }
        };
        utf8_limits = new long[] { 0L, 128L, 2048L, 65536L, 2097152L, 67108864L, -2147483648L };
    }
    
    private abstract static class ConverterExecutor
    {
        protected Converter converter;
        
        public void setConverter(final Converter converter) {
            this.converter = converter;
        }
        
        public abstract IRubyObject decode(final Ruby p0, final ByteBuffer p1);
        
        public abstract void encode(final Ruby p0, final IRubyObject p1, final ByteList p2);
    }
    
    public abstract static class Converter
    {
        public int size;
        
        public Converter(final int size) {
            this.size = size;
        }
        
        public abstract IRubyObject decode(final Ruby p0, final ByteBuffer p1);
        
        public abstract void encode(final Ruby p0, final IRubyObject p1, final ByteList p2);
        
        public IRubyObject decode19(final Ruby runtime, final ByteBuffer format) {
            return this.decode(runtime, format);
        }
        
        public void encode19(final Ruby runtime, final IRubyObject from, final ByteList result) {
            this.encode(runtime, from, result);
        }
    }
    
    private abstract static class QuadConverter extends Converter
    {
        public QuadConverter(final int size) {
            super(size);
        }
        
        protected int overflowQuad(final long quad) {
            return (int)(quad & 0xFFFFL);
        }
        
        protected void encodeShortByByteOrder(final ByteList result, final int s) {
            if (Platform.BYTE_ORDER == 4321) {
                encodeShortBigEndian(result, s);
            }
            else {
                encodeShortLittleEndian(result, s);
            }
        }
        
        protected void encodeLongByByteOrder(final ByteList result, final long l) {
            if (Platform.BYTE_ORDER == 4321) {
                encodeLongBigEndian(result, l);
            }
            else {
                encodeLongLittleEndian(result, l);
            }
        }
    }
}
