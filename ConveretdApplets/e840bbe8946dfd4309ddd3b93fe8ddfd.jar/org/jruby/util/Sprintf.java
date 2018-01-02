// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.text.DecimalFormatSymbols;
import org.jruby.RubyArray;
import org.jruby.RubyKernel;
import org.jruby.common.IRubyWarnings;
import org.jruby.RubyBignum;
import org.jruby.RubyInteger;
import org.jruby.RubyFixnum;
import org.jruby.RubyNumeric;
import org.jruby.RubyFloat;
import org.jruby.RubyString;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.Locale;
import java.math.BigInteger;

public class Sprintf
{
    private static final int FLAG_NONE = 0;
    private static final int FLAG_SPACE = 1;
    private static final int FLAG_ZERO = 2;
    private static final int FLAG_PLUS = 4;
    private static final int FLAG_MINUS = 8;
    private static final int FLAG_SHARP = 16;
    private static final int FLAG_WIDTH = 32;
    private static final int FLAG_PRECISION = 64;
    private static final byte[] PREFIX_OCTAL;
    private static final byte[] PREFIX_HEX_LC;
    private static final byte[] PREFIX_HEX_UC;
    private static final byte[] PREFIX_BINARY_LC;
    private static final byte[] PREFIX_BINARY_UC;
    private static final byte[] PREFIX_NEGATIVE;
    private static final byte[] NAN_VALUE;
    private static final byte[] INFINITY_VALUE;
    private static final BigInteger BIG_32;
    private static final BigInteger BIG_64;
    private static final BigInteger BIG_MINUS_32;
    private static final BigInteger BIG_MINUS_64;
    private static final String ERR_MALFORMED_FORMAT = "malformed format string";
    private static final String ERR_MALFORMED_NUM = "malformed format string - %[0-9]";
    private static final String ERR_MALFORMED_DOT_NUM = "malformed format string - %.[0-9]";
    private static final String ERR_MALFORMED_STAR_NUM = "malformed format string - %*[0-9]";
    private static final String ERR_ILLEGAL_FORMAT_CHAR = "illegal format character - %";
    
    public static boolean sprintf(final ByteList to, final Locale locale, final CharSequence format, final IRubyObject args) {
        return rubySprintfToBuffer(to, format, new Args(locale, args));
    }
    
    public static boolean sprintf1_9(final ByteList to, final Locale locale, final CharSequence format, final IRubyObject args) {
        return rubySprintfToBuffer(to, format, new Args(locale, args), false);
    }
    
    public static boolean sprintf(final ByteList to, final CharSequence format, final IRubyObject args) {
        return rubySprintf(to, format, new Args(args));
    }
    
    public static boolean sprintf(final Ruby runtime, final ByteList to, final CharSequence format, final int arg) {
        return rubySprintf(to, format, new Args(runtime, arg));
    }
    
    public static boolean sprintf(final ByteList to, final RubyString format, final IRubyObject args) {
        return rubySprintf(to, format.getByteList(), new Args(args));
    }
    
    private static boolean rubySprintf(final ByteList to, final CharSequence charFormat, final Args args) {
        return rubySprintfToBuffer(to, charFormat, args);
    }
    
    private static boolean rubySprintfToBuffer(final ByteList buf, final CharSequence charFormat, final Args args) {
        return rubySprintfToBuffer(buf, charFormat, args, true);
    }
    
    private static boolean rubySprintfToBuffer(final ByteList buf, final CharSequence charFormat, final Args args, final boolean usePrefixForZero) {
        boolean tainted = false;
        byte[] format;
        int offset;
        int length;
        if (charFormat instanceof ByteList) {
            final ByteList list = (ByteList)charFormat;
            format = list.getUnsafeBytes();
            final int begin = offset = list.begin();
            length = begin + list.length();
            final int start = begin;
            final int mark = begin;
        }
        else {
            format = stringToBytes(charFormat, false);
            offset = 0;
            length = charFormat.length();
            final int start = 0;
            final int mark = 0;
        }
        while (offset < length) {
            int start = offset;
            while (offset < length && format[offset] != 37) {
                ++offset;
            }
            if (offset > start) {
                buf.append(format, start, offset - start);
                start = offset;
            }
            if (offset++ >= length) {
                break;
            }
            IRubyObject arg = null;
            int flags = 0;
            int width = 0;
            int precision = 0;
            int number = 0;
            byte fchar = 0;
            boolean incomplete = true;
            while (incomplete && offset < length) {
                switch (fchar = format[offset]) {
                    default: {
                        if (fchar == 0 && flags == 0) {
                            buf.append(37);
                            buf.append(fchar);
                            incomplete = false;
                            ++offset;
                            continue;
                        }
                        if (isPrintable(fchar)) {
                            raiseArgumentError(args, "malformed format string - %" + (char)fchar);
                            continue;
                        }
                        raiseArgumentError(args, "malformed format string");
                        continue;
                    }
                    case 32: {
                        flags |= 0x1;
                        ++offset;
                        continue;
                    }
                    case 48: {
                        flags |= 0x2;
                        ++offset;
                        continue;
                    }
                    case 43: {
                        flags |= 0x4;
                        ++offset;
                        continue;
                    }
                    case 45: {
                        flags |= 0x8;
                        ++offset;
                        continue;
                    }
                    case 35: {
                        flags |= 0x10;
                        ++offset;
                        continue;
                    }
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57: {
                        number = 0;
                        while (offset < length && isDigit(fchar = format[offset])) {
                            number = extendWidth(args, number, fchar);
                            ++offset;
                        }
                        checkOffset(args, offset, length, "malformed format string - %[0-9]");
                        if (fchar == 36) {
                            if (arg != null) {
                                raiseArgumentError(args, "value given twice - " + number + "$");
                            }
                            arg = args.getNth(number);
                            ++offset;
                            continue;
                        }
                        width = number;
                        flags |= 0x20;
                        continue;
                    }
                    case 42: {
                        if ((flags & 0x20) != 0x0) {
                            raiseArgumentError(args, "width given twice");
                        }
                        flags |= 0x20;
                        checkOffset(args, ++offset, length, "malformed format string - %*[0-9]");
                        final int mark = offset;
                        number = 0;
                        while (offset < length && isDigit(fchar = format[offset])) {
                            number = extendWidth(args, number, fchar);
                            ++offset;
                        }
                        checkOffset(args, offset, length, "malformed format string - %*[0-9]");
                        if (fchar == 36) {
                            width = args.getNthInt(number);
                            if (width < 0) {
                                flags |= 0x8;
                                width = -width;
                            }
                            ++offset;
                            continue;
                        }
                        width = args.nextInt();
                        if (width < 0) {
                            flags |= 0x8;
                            width = -width;
                        }
                        offset = mark;
                        continue;
                    }
                    case 46: {
                        if ((flags & 0x40) != 0x0) {
                            raiseArgumentError(args, "precision given twice");
                        }
                        flags |= 0x40;
                        checkOffset(args, ++offset, length, "malformed format string - %.[0-9]");
                        fchar = format[offset];
                        if (fchar != 42) {
                            number = 0;
                            while (offset < length && isDigit(fchar = format[offset])) {
                                number = extendWidth(args, number, fchar);
                                ++offset;
                            }
                            checkOffset(args, offset, length, "malformed format string - %.[0-9]");
                            precision = number;
                            continue;
                        }
                        checkOffset(args, ++offset, length, "malformed format string - %*[0-9]");
                        final int mark = offset;
                        number = 0;
                        while (offset < length && isDigit(fchar = format[offset])) {
                            number = extendWidth(args, number, fchar);
                            ++offset;
                        }
                        checkOffset(args, offset, length, "malformed format string - %*[0-9]");
                        if (fchar == 36) {
                            precision = args.getNthInt(number);
                            if (precision < 0) {
                                flags &= 0xFFFFFFBF;
                            }
                            ++offset;
                            continue;
                        }
                        precision = args.nextInt();
                        if (precision < 0) {
                            flags &= 0xFFFFFFBF;
                        }
                        offset = mark;
                        continue;
                    }
                    case 10: {
                        --offset;
                    }
                    case 37: {
                        if (flags != 0) {
                            raiseArgumentError(args, "illegal format character - %");
                        }
                        buf.append(37);
                        ++offset;
                        incomplete = false;
                        continue;
                    }
                    case 99: {
                        if (arg == null) {
                            arg = args.next();
                        }
                        int c = 0;
                        if (arg instanceof RubyString) {
                            final ByteList bytes = ((RubyString)arg).getByteList();
                            if (bytes.length() == 1) {
                                c = bytes.getUnsafeBytes()[bytes.begin()];
                            }
                            else {
                                raiseArgumentError(args, "%c requires a character");
                            }
                        }
                        else {
                            c = args.intValue(arg);
                        }
                        if ((flags & 0x20) != 0x0 && width > 1) {
                            if ((flags & 0x8) != 0x0) {
                                buf.append(c);
                                buf.fill(32, width - 1);
                            }
                            else {
                                buf.fill(32, width - 1);
                                buf.append(c);
                            }
                        }
                        else {
                            buf.append(c);
                        }
                        ++offset;
                        incomplete = false;
                        continue;
                    }
                    case 112:
                    case 115: {
                        if (arg == null) {
                            arg = args.next();
                        }
                        if (fchar == 112) {
                            arg = arg.callMethod(arg.getRuntime().getCurrentContext(), "inspect");
                        }
                        final ByteList bytes2 = arg.asString().getByteList();
                        int len = bytes2.length();
                        if (arg.isTaint()) {
                            tainted = true;
                        }
                        if ((flags & 0x40) != 0x0 && precision < len) {
                            len = precision;
                        }
                        if ((flags & 0x20) != 0x0 && width > len) {
                            width -= len;
                            if ((flags & 0x8) != 0x0) {
                                buf.append(bytes2.getUnsafeBytes(), bytes2.begin(), len);
                                buf.fill(32, width);
                            }
                            else {
                                buf.fill(32, width);
                                buf.append(bytes2.getUnsafeBytes(), bytes2.begin(), len);
                            }
                        }
                        else {
                            buf.append(bytes2.getUnsafeBytes(), bytes2.begin(), len);
                        }
                        ++offset;
                        incomplete = false;
                        continue;
                    }
                    case 66:
                    case 88:
                    case 98:
                    case 100:
                    case 105:
                    case 111:
                    case 117:
                    case 120: {
                        if (arg == null) {
                            arg = args.next();
                        }
                        int type = arg.getMetaClass().index;
                        if (type != 1 && type != 2) {
                            switch (type) {
                                case 11: {
                                    arg = RubyNumeric.dbl2num(arg.getRuntime(), ((RubyFloat)arg).getValue());
                                    break;
                                }
                                case 4: {
                                    arg = ((RubyString)arg).stringToInum(0, true);
                                    break;
                                }
                                default: {
                                    if (arg.respondsTo("to_int")) {
                                        arg = TypeConverter.convertToType(arg, arg.getRuntime().getInteger(), "to_int", true);
                                        break;
                                    }
                                    arg = TypeConverter.convertToType(arg, arg.getRuntime().getInteger(), "to_i", true);
                                    break;
                                }
                            }
                            type = arg.getMetaClass().index;
                        }
                        byte[] bytes3 = null;
                        int first = 0;
                        byte[] prefix = null;
                        byte signChar = 0;
                        byte leadChar = 0;
                        if (fchar == 105) {
                            fchar = 100;
                        }
                        if (fchar == 117 && (flags & 0x5) != 0x0) {
                            fchar = 100;
                        }
                        final boolean sign = fchar == 100 || (flags & 0x5) != 0x0;
                        int base = 0;
                        switch (fchar) {
                            case 111: {
                                base = 8;
                                break;
                            }
                            case 88:
                            case 120: {
                                base = 16;
                                break;
                            }
                            case 66:
                            case 98: {
                                base = 2;
                                break;
                            }
                            default: {
                                base = 10;
                                break;
                            }
                        }
                        boolean negative;
                        boolean zero;
                        if (type == 1) {
                            negative = (((RubyFixnum)arg).getLongValue() < 0L);
                            zero = (((RubyFixnum)arg).getLongValue() == 0L);
                            if (negative && fchar == 117) {
                                bytes3 = getUnsignedNegativeBytes((RubyInteger)arg);
                            }
                            else {
                                bytes3 = getFixnumBytes((RubyFixnum)arg, base, sign, fchar == 88);
                            }
                        }
                        else {
                            negative = (((RubyBignum)arg).getValue().signum() < 0);
                            zero = ((RubyBignum)arg).getValue().equals(BigInteger.ZERO);
                            if (negative && fchar == 117 && usePrefixForZero) {
                                bytes3 = getUnsignedNegativeBytes((RubyInteger)arg);
                            }
                            else {
                                bytes3 = getBignumBytes((RubyBignum)arg, base, sign, fchar == 88);
                            }
                        }
                        if ((flags & 0x10) != 0x0) {
                            if (!zero || usePrefixForZero) {
                                switch (fchar) {
                                    case 111: {
                                        prefix = Sprintf.PREFIX_OCTAL;
                                        break;
                                    }
                                    case 120: {
                                        prefix = Sprintf.PREFIX_HEX_LC;
                                        break;
                                    }
                                    case 88: {
                                        prefix = Sprintf.PREFIX_HEX_UC;
                                        break;
                                    }
                                    case 98: {
                                        prefix = Sprintf.PREFIX_BINARY_LC;
                                        break;
                                    }
                                    case 66: {
                                        prefix = Sprintf.PREFIX_BINARY_UC;
                                        break;
                                    }
                                }
                            }
                            if (prefix != null) {
                                width -= prefix.length;
                            }
                        }
                        int len2 = 0;
                        if (sign) {
                            if (negative) {
                                signChar = 45;
                                --width;
                                first = 1;
                            }
                            else if ((flags & 0x4) != 0x0) {
                                signChar = 43;
                                --width;
                            }
                            else if ((flags & 0x1) != 0x0) {
                                signChar = 32;
                                --width;
                            }
                        }
                        else if (negative) {
                            if (base == 10) {
                                warning(IRubyWarnings.ID.NEGATIVE_NUMBER_FOR_U, args, "negative number for %u specifier");
                                leadChar = 46;
                                len2 += 2;
                            }
                            else {
                                if ((flags & 0x42) == 0x0) {
                                    len2 += 2;
                                }
                                first = skipSignBits(bytes3, base);
                                switch (fchar) {
                                    case 66:
                                    case 98: {
                                        leadChar = 49;
                                        break;
                                    }
                                    case 111: {
                                        leadChar = 55;
                                        break;
                                    }
                                    case 120: {
                                        leadChar = 102;
                                        break;
                                    }
                                    case 88: {
                                        leadChar = 70;
                                        break;
                                    }
                                }
                                if (leadChar != 0) {
                                    ++len2;
                                }
                            }
                        }
                        final int numlen = bytes3.length - first;
                        len2 += numlen;
                        if ((flags & 0x42) == 0x2) {
                            precision = width;
                            width = 0;
                        }
                        else {
                            if (precision < len2) {
                                precision = len2;
                            }
                            width -= precision;
                        }
                        if ((flags & 0x8) == 0x0) {
                            buf.fill(32, width);
                            width = 0;
                        }
                        if (signChar != 0) {
                            buf.append(signChar);
                        }
                        if (prefix != null) {
                            buf.append(prefix);
                        }
                        if (len2 < precision) {
                            if (leadChar == 0) {
                                if (fchar != 100 || usePrefixForZero || !negative || ((flags & 0x2) != 0x0 && (flags & 0x8) == 0x0)) {
                                    buf.fill(48, precision - len2);
                                }
                            }
                            else if (leadChar == 46) {
                                buf.fill(leadChar, precision - len2);
                                buf.append(Sprintf.PREFIX_NEGATIVE);
                            }
                            else if (!usePrefixForZero) {
                                buf.append(Sprintf.PREFIX_NEGATIVE);
                                buf.fill(leadChar, precision - len2 - 1);
                            }
                            else {
                                buf.fill(leadChar, precision - len2 + 1);
                            }
                        }
                        else if (leadChar != 0) {
                            if (((flags & 0x42) == 0x0 && usePrefixForZero) || (!usePrefixForZero && "xXbBo".indexOf(fchar) != -1)) {
                                buf.append(Sprintf.PREFIX_NEGATIVE);
                            }
                            if (leadChar != 46) {
                                buf.append(leadChar);
                            }
                        }
                        buf.append(bytes3, first, numlen);
                        if (width > 0) {
                            buf.fill(32, width);
                        }
                        if (len2 < precision && fchar == 100 && negative && !usePrefixForZero && (flags & 0x8) != 0x0) {
                            buf.fill(32, precision - len2);
                        }
                        ++offset;
                        incomplete = false;
                        continue;
                    }
                    case 69:
                    case 71:
                    case 101:
                    case 102:
                    case 103: {
                        if (arg == null) {
                            arg = args.next();
                        }
                        if (!(arg instanceof RubyFloat)) {
                            if (usePrefixForZero) {
                                arg = RubyKernel.new_float(arg, arg);
                            }
                            else {
                                arg = RubyKernel.new_float19(arg, arg);
                            }
                        }
                        final double dval = ((RubyFloat)arg).getDoubleValue();
                        final boolean nan = dval != dval;
                        final boolean inf = dval == Double.POSITIVE_INFINITY || dval == Double.NEGATIVE_INFINITY;
                        final boolean negative2 = dval < 0.0 || (dval == 0.0 && new Float(dval).equals(new Float(-0.0)));
                        int nDigits = 0;
                        int exponent = 0;
                        int len3 = 0;
                        if (nan || inf) {
                            byte[] digits;
                            if (nan) {
                                digits = Sprintf.NAN_VALUE;
                                len3 = Sprintf.NAN_VALUE.length;
                            }
                            else {
                                digits = Sprintf.INFINITY_VALUE;
                                len3 = Sprintf.INFINITY_VALUE.length;
                            }
                            byte signChar2;
                            if (negative2) {
                                signChar2 = 45;
                                --width;
                            }
                            else if ((flags & 0x4) != 0x0) {
                                signChar2 = 43;
                                --width;
                            }
                            else if ((flags & 0x1) != 0x0) {
                                signChar2 = 32;
                                --width;
                            }
                            else {
                                signChar2 = 0;
                            }
                            width -= len3;
                            if (width > 0 && (flags & 0xA) == 0x0) {
                                buf.fill(32, width);
                                width = 0;
                            }
                            if (signChar2 != 0) {
                                buf.append(signChar2);
                            }
                            if (width > 0 && (flags & 0x8) == 0x0) {
                                buf.fill(48, width);
                                width = 0;
                            }
                            buf.append(digits);
                            if (width > 0) {
                                buf.fill(32, width);
                            }
                            ++offset;
                            incomplete = false;
                            continue;
                        }
                        final String str = Double.toString(dval);
                        final int strlen = str.length();
                        byte[] digits = new byte[strlen];
                        int nTrailingZeroes = 0;
                        int i = negative2 ? 1 : 0;
                        int decPos = 0;
                    Label_3372:
                        while (i < strlen) {
                            final byte ival;
                            switch (ival = (byte)str.charAt(i++)) {
                                case 48: {
                                    if (nDigits > 0) {
                                        ++nTrailingZeroes;
                                        continue;
                                    }
                                    continue;
                                }
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57: {
                                    if (nTrailingZeroes > 0) {
                                        while (nTrailingZeroes > 0) {
                                            digits[nDigits++] = 48;
                                            --nTrailingZeroes;
                                        }
                                    }
                                    digits[nDigits++] = ival;
                                    continue;
                                }
                                case 46: {
                                    break Label_3372;
                                }
                            }
                        }
                        decPos = nDigits + nTrailingZeroes;
                    Label_3566:
                        while (i < strlen) {
                            final byte ival;
                            switch (ival = (byte)str.charAt(i++)) {
                                case 48: {
                                    if (nDigits > 0) {
                                        ++nTrailingZeroes;
                                        continue;
                                    }
                                    --exponent;
                                    continue;
                                }
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57: {
                                    if (nTrailingZeroes > 0) {
                                        while (nTrailingZeroes > 0) {
                                            digits[nDigits++] = 48;
                                            --nTrailingZeroes;
                                        }
                                    }
                                    digits[nDigits++] = ival;
                                    continue;
                                }
                                case 69: {
                                    break Label_3566;
                                }
                            }
                        }
                        if (i < strlen) {
                            int expVal = 0;
                            int expSign;
                            if (str.charAt(i) == '-') {
                                expSign = -1;
                                ++i;
                            }
                            else {
                                expSign = 1;
                            }
                            while (i < strlen) {
                                expVal = expVal * 10 + (str.charAt(i++) - '0');
                            }
                            exponent += expVal * expSign;
                        }
                        exponent += decPos - nDigits;
                        if (nDigits == 0) {
                            digits[0] = 48;
                            nDigits = 1;
                            exponent = 0;
                        }
                        byte signChar2;
                        if (negative2) {
                            signChar2 = 45;
                            --width;
                        }
                        else if ((flags & 0x4) != 0x0) {
                            signChar2 = 43;
                            --width;
                        }
                        else if ((flags & 0x1) != 0x0) {
                            signChar2 = 32;
                            --width;
                        }
                        else {
                            signChar2 = 0;
                        }
                        if ((flags & 0x40) == 0x0) {
                            precision = 6;
                        }
                        byte expChar = 0;
                        switch (fchar) {
                            case 69:
                            case 71: {
                                expChar = 69;
                                break;
                            }
                            case 101:
                            case 103: {
                                expChar = 101;
                                break;
                            }
                            default: {
                                expChar = 0;
                                break;
                            }
                        }
                        switch (fchar) {
                            case 71:
                            case 103: {
                                final boolean expForm = exponent + nDigits - 1 < -4 || exponent + nDigits > ((precision == 0) ? 1 : precision);
                                if (expForm) {
                                    int decDigits = nDigits - 1;
                                    precision = Math.max(0, precision - 1);
                                    if (precision < decDigits) {
                                        final int n = round(digits, nDigits, precision, precision != 0);
                                        if (n > nDigits) {
                                            nDigits = n;
                                        }
                                        decDigits = Math.min(nDigits - 1, precision);
                                    }
                                    exponent += nDigits - 1;
                                    final boolean isSharp = (flags & 0x10) != 0x0;
                                    ++len3;
                                    if (exponent > 99) {
                                        len3 += 5;
                                    }
                                    else {
                                        len3 += 4;
                                    }
                                    if (isSharp) {
                                        ++len3;
                                    }
                                    if (precision > 0) {
                                        if (!isSharp) {
                                            for (int j = decDigits; j >= 1 && digits[j] == 48; --j) {
                                                --decDigits;
                                            }
                                            if (decDigits > 0) {
                                                len3 = ++len3 + decDigits;
                                            }
                                        }
                                        else {
                                            len3 += precision;
                                        }
                                    }
                                    width -= len3;
                                    if (width > 0 && (flags & 0xA) == 0x0) {
                                        buf.fill(32, width);
                                        width = 0;
                                    }
                                    if (signChar2 != 0) {
                                        buf.append(signChar2);
                                    }
                                    if (width > 0 && (flags & 0x8) == 0x0) {
                                        buf.fill(48, width);
                                        width = 0;
                                    }
                                    buf.append(digits[0]);
                                    final boolean dotToPrint = isSharp || (precision > 0 && decDigits > 0);
                                    if (dotToPrint) {
                                        buf.append(args.getDecimalSeparator());
                                    }
                                    if (precision > 0 && decDigits > 0) {
                                        buf.append(digits, 1, decDigits);
                                        precision -= decDigits;
                                    }
                                    if (precision > 0 && isSharp) {
                                        buf.fill(48, precision);
                                    }
                                    writeExp(buf, exponent, expChar);
                                    if (width > 0) {
                                        buf.fill(32, width);
                                    }
                                    break;
                                }
                                int intDigits = Math.max(0, Math.min(nDigits + exponent, nDigits));
                                final int intZeroes = Math.max(0, exponent);
                                int intLength = intDigits + intZeroes;
                                int decDigits = nDigits - intDigits;
                                int decZeroes = Math.max(0, -(decDigits + exponent));
                                int decLength = decZeroes + decDigits;
                                precision = Math.max(0, precision - intLength);
                                if (precision < decDigits) {
                                    final int n = round(digits, nDigits, intDigits + precision - 1, precision != 0);
                                    if (n > nDigits) {
                                        nDigits = n;
                                        intDigits = Math.max(0, Math.min(nDigits + exponent, nDigits));
                                        intLength = intDigits + intZeroes;
                                        decDigits = nDigits - intDigits;
                                        decZeroes = Math.max(0, -(decDigits + exponent));
                                        precision = Math.max(0, precision - 1);
                                    }
                                    decDigits = precision;
                                    decLength = decZeroes + decDigits;
                                }
                                len3 += intLength;
                                if (decLength > 0) {
                                    len3 += decLength + 1;
                                }
                                else if ((flags & 0x10) != 0x0) {
                                    ++len3;
                                    if (precision > 0) {
                                        len3 += precision;
                                    }
                                }
                                width -= len3;
                                if (width > 0 && (flags & 0xA) == 0x0) {
                                    buf.fill(32, width);
                                    width = 0;
                                }
                                if (signChar2 != 0) {
                                    buf.append(signChar2);
                                }
                                if (width > 0 && (flags & 0x8) == 0x0) {
                                    buf.fill(48, width);
                                    width = 0;
                                }
                                if (intLength > 0) {
                                    if (intDigits > 0) {
                                        buf.append(digits, 0, intDigits);
                                    }
                                    if (intZeroes > 0) {
                                        buf.fill(48, intZeroes);
                                    }
                                }
                                else {
                                    buf.append(48);
                                }
                                if (decLength > 0 || (flags & 0x10) != 0x0) {
                                    buf.append(args.getDecimalSeparator());
                                }
                                if (decLength > 0) {
                                    if (decZeroes > 0) {
                                        buf.fill(48, decZeroes);
                                        precision -= decZeroes;
                                    }
                                    if (decDigits > 0) {
                                        buf.append(digits, intDigits, decDigits);
                                        precision -= decDigits;
                                    }
                                    if ((flags & 0x10) != 0x0 && precision > 0) {
                                        buf.fill(48, precision);
                                    }
                                }
                                if ((flags & 0x10) != 0x0 && precision > 0) {
                                    buf.fill(48, precision);
                                }
                                if (width > 0) {
                                    buf.fill(32, width);
                                    break;
                                }
                                break;
                            }
                            case 102: {
                                int intDigits = Math.max(0, Math.min(nDigits + exponent, nDigits));
                                final int intZeroes = Math.max(0, exponent);
                                int intLength = intDigits + intZeroes;
                                int decDigits = nDigits - intDigits;
                                int decZeroes = Math.max(0, -(decDigits + exponent));
                                int decLength = decZeroes + decDigits;
                                if (precision < decLength) {
                                    if (precision < decZeroes) {
                                        decDigits = 0;
                                        decZeroes = precision;
                                    }
                                    else {
                                        final int n = round(digits, nDigits, intDigits + precision - decZeroes - 1, false);
                                        if (n > nDigits) {
                                            nDigits = n;
                                            intDigits = Math.max(0, Math.min(nDigits + exponent, nDigits));
                                            intLength = intDigits + intZeroes;
                                            decDigits = nDigits - intDigits;
                                            decZeroes = Math.max(0, -(decDigits + exponent));
                                            decLength = decZeroes + decDigits;
                                        }
                                        decDigits = precision - decZeroes;
                                    }
                                    decLength = decZeroes + decDigits;
                                }
                                if (precision > 0) {
                                    len3 += Math.max(1, intLength) + 1 + precision;
                                }
                                else {
                                    len3 += Math.max(1, intLength);
                                    if ((flags & 0x10) != 0x0) {
                                        ++len3;
                                    }
                                }
                                width -= len3;
                                if (width > 0 && (flags & 0xA) == 0x0) {
                                    buf.fill(32, width);
                                    width = 0;
                                }
                                if (signChar2 != 0) {
                                    buf.append(signChar2);
                                }
                                if (width > 0 && (flags & 0x8) == 0x0) {
                                    buf.fill(48, width);
                                    width = 0;
                                }
                                if (intLength > 0) {
                                    if (intDigits > 0) {
                                        buf.append(digits, 0, intDigits);
                                    }
                                    if (intZeroes > 0) {
                                        buf.fill(48, intZeroes);
                                    }
                                }
                                else {
                                    buf.append(48);
                                }
                                if (precision > 0 || (flags & 0x10) != 0x0) {
                                    buf.append(args.getDecimalSeparator());
                                }
                                if (precision > 0) {
                                    if (decZeroes > 0) {
                                        buf.fill(48, decZeroes);
                                        precision -= decZeroes;
                                    }
                                    if (decDigits > 0) {
                                        buf.append(digits, intDigits, decDigits);
                                        precision -= decDigits;
                                    }
                                    if (precision > 0) {
                                        buf.fill(48, precision);
                                    }
                                }
                                if (width > 0) {
                                    buf.fill(32, width);
                                    break;
                                }
                                break;
                            }
                            case 69:
                            case 101: {
                                int decDigits = nDigits - 1;
                                if (precision < decDigits) {
                                    final int n = round(digits, nDigits, precision, precision != 0);
                                    if (n > nDigits) {
                                        nDigits = n;
                                    }
                                    decDigits = Math.min(nDigits - 1, precision);
                                }
                                exponent += nDigits - 1;
                                final boolean isSharp = (flags & 0x10) != 0x0;
                                ++len3;
                                if (exponent > 99) {
                                    len3 += 5;
                                }
                                else {
                                    len3 += 4;
                                }
                                if (precision > 0) {
                                    len3 += 1 + precision;
                                }
                                else if (isSharp) {
                                    ++len3;
                                }
                                width -= len3;
                                if (width > 0 && (flags & 0xA) == 0x0) {
                                    buf.fill(32, width);
                                    width = 0;
                                }
                                if (signChar2 != 0) {
                                    buf.append(signChar2);
                                }
                                if (width > 0 && (flags & 0x8) == 0x0) {
                                    buf.fill(48, width);
                                    width = 0;
                                }
                                buf.append(digits[0]);
                                if (precision > 0) {
                                    buf.append(args.getDecimalSeparator());
                                    if (decDigits > 0) {
                                        buf.append(digits, 1, decDigits);
                                        precision -= decDigits;
                                    }
                                    if (precision > 0) {
                                        buf.fill(48, precision);
                                    }
                                }
                                else if ((flags & 0x10) != 0x0) {
                                    buf.append(args.getDecimalSeparator());
                                }
                                writeExp(buf, exponent, expChar);
                                if (width > 0) {
                                    buf.fill(32, width);
                                    break;
                                }
                                break;
                            }
                        }
                        ++offset;
                        incomplete = false;
                        continue;
                    }
                }
            }
            if (!incomplete) {
                continue;
            }
            if (flags == 0) {
                buf.append(37);
            }
            else {
                raiseArgumentError(args, "illegal format character - %");
            }
        }
        if (args.numbered == 0 && args.unnumbered < args.length) {
            if (args.runtime.getDebug().isTrue()) {
                args.raiseArgumentError("too many arguments for format string");
            }
            else if (args.runtime.isVerbose()) {
                args.warn(IRubyWarnings.ID.TOO_MANY_ARGUMENTS, "too many arguments for format string");
            }
        }
        return tainted;
    }
    
    private static void writeExp(final ByteList buf, int exponent, final byte expChar) {
        buf.append(expChar);
        buf.append((exponent >= 0) ? 43 : 45);
        if (exponent < 0) {
            exponent = -exponent;
        }
        if (exponent > 99) {
            buf.append(exponent / 100 + 48);
            buf.append(exponent % 100 / 10 + 48);
        }
        else {
            buf.append(exponent / 10 + 48);
        }
        buf.append(exponent % 10 + 48);
    }
    
    private static void raiseArgumentError(final Args args, final String message) {
        args.raiseArgumentError(message);
    }
    
    private static void warning(final IRubyWarnings.ID id, final Args args, final String message) {
        args.warning(id, message);
    }
    
    private static void checkOffset(final Args args, final int offset, final int length, final String message) {
        if (offset >= length) {
            raiseArgumentError(args, message);
        }
    }
    
    private static int extendWidth(final Args args, final int oldWidth, final byte newChar) {
        final int newWidth = oldWidth * 10 + (newChar - 48);
        if (newWidth / 10 != oldWidth) {
            raiseArgumentError(args, "width too big");
        }
        return newWidth;
    }
    
    private static boolean isDigit(final byte aChar) {
        return aChar >= 48 && aChar <= 57;
    }
    
    private static boolean isPrintable(final byte aChar) {
        return aChar > 32 && aChar < 127;
    }
    
    private static int skipSignBits(final byte[] bytes, final int base) {
        int skip = 0;
        final int length = bytes.length;
        switch (base) {
            case 2: {
                while (skip < length && bytes[skip] == 49) {
                    ++skip;
                }
                break;
            }
            case 8: {
                if (length > 0 && bytes[0] == 51) {
                    ++skip;
                }
                while (skip < length && bytes[skip] == 55) {
                    ++skip;
                }
                break;
            }
            case 10: {
                if (length > 0 && bytes[0] == 45) {
                    ++skip;
                    break;
                }
                break;
            }
            case 16: {
                byte b;
                while (skip < length && ((b = bytes[skip]) == 102 || b == 70)) {
                    ++skip;
                }
                break;
            }
        }
        return skip;
    }
    
    private static int round(final byte[] bytes, final int nDigits, int roundPos, final boolean roundDown) {
        final int next = roundPos + 1;
        if (next >= nDigits || bytes[next] < 53 || (roundDown && bytes[next] == 53 && next == nDigits - 1)) {
            return nDigits;
        }
        if (roundPos < 0) {
            System.arraycopy(bytes, 0, bytes, 1, nDigits);
            bytes[0] = 49;
            return nDigits + 1;
        }
        ++bytes[roundPos];
        while (bytes[roundPos] > 57) {
            bytes[roundPos] = 48;
            if (--roundPos < 0) {
                System.arraycopy(bytes, 0, bytes, 1, nDigits);
                bytes[0] = 49;
                return nDigits + 1;
            }
            ++bytes[roundPos];
        }
        return nDigits;
    }
    
    private static byte[] getFixnumBytes(final RubyFixnum arg, final int base, final boolean sign, final boolean upper) {
        final long val = arg.getLongValue();
        if (val >= -2147483648L && val <= 2147483647L) {
            if (sign) {
                return ConvertBytes.intToByteArray((int)val, base, upper);
            }
            switch (base) {
                case 2: {
                    return ConvertBytes.intToBinaryBytes((int)val);
                }
                case 8: {
                    return ConvertBytes.intToOctalBytes((int)val);
                }
                default: {
                    return ConvertBytes.intToCharBytes((int)val);
                }
                case 16: {
                    return ConvertBytes.intToHexBytes((int)val, upper);
                }
            }
        }
        else {
            if (sign) {
                return ConvertBytes.longToByteArray(val, base, upper);
            }
            switch (base) {
                case 2: {
                    return ConvertBytes.longToBinaryBytes(val);
                }
                case 8: {
                    return ConvertBytes.longToOctalBytes(val);
                }
                default: {
                    return ConvertBytes.longToCharBytes(val);
                }
                case 16: {
                    return ConvertBytes.longToHexBytes(val, upper);
                }
            }
        }
    }
    
    private static byte[] getBignumBytes(final RubyBignum arg, final int base, final boolean sign, final boolean upper) {
        final BigInteger val = arg.getValue();
        if (sign || base == 10 || val.signum() >= 0) {
            return stringToBytes(val.toString(base), upper);
        }
        final byte[] bytes = val.toByteArray();
        switch (base) {
            case 2: {
                return ConvertBytes.twosComplementToBinaryBytes(bytes);
            }
            case 8: {
                return ConvertBytes.twosComplementToOctalBytes(bytes);
            }
            case 16: {
                return ConvertBytes.twosComplementToHexBytes(bytes, upper);
            }
            default: {
                return stringToBytes(val.toString(base), upper);
            }
        }
    }
    
    private static byte[] getUnsignedNegativeBytes(final RubyInteger arg) {
        BigInteger bigval;
        if (arg instanceof RubyFixnum) {
            final long longval = ((RubyFixnum)arg).getLongValue();
            if (longval >= 0L) {
                return ConvertBytes.longToCharBytes(0L + longval);
            }
            bigval = BigInteger.valueOf(longval);
        }
        else {
            bigval = ((RubyBignum)arg).getValue();
        }
        int shift = 0;
        for (BigInteger minus = Sprintf.BIG_MINUS_64; bigval.compareTo(minus) < 0; minus = minus.shiftLeft(32), ++shift) {}
        final BigInteger nPower32 = (shift > 0) ? Sprintf.BIG_64.shiftLeft(32 * shift) : Sprintf.BIG_64;
        return stringToBytes(nPower32.add(bigval).toString(), false);
    }
    
    private static byte[] stringToBytes(final CharSequence s, final boolean upper) {
        final int len = s.length();
        final byte[] bytes = new byte[len];
        if (upper) {
            int i = len;
            while (--i >= 0) {
                final int b = (byte)(s.charAt(i) & '\u00ff');
                if (b >= 97 && b <= 122) {
                    bytes[i] = (byte)(b & 0xFFFFFFDF);
                }
                else {
                    bytes[i] = (byte)b;
                }
            }
        }
        else {
            int i = len;
            while (--i >= 0) {
                bytes[i] = (byte)(s.charAt(i) & '\u00ff');
            }
        }
        return bytes;
    }
    
    static {
        PREFIX_OCTAL = new byte[] { 48 };
        PREFIX_HEX_LC = new byte[] { 48, 120 };
        PREFIX_HEX_UC = new byte[] { 48, 88 };
        PREFIX_BINARY_LC = new byte[] { 48, 98 };
        PREFIX_BINARY_UC = new byte[] { 48, 66 };
        PREFIX_NEGATIVE = new byte[] { 46, 46 };
        NAN_VALUE = new byte[] { 78, 97, 78 };
        INFINITY_VALUE = new byte[] { 73, 110, 102 };
        BIG_32 = BigInteger.valueOf(4294967296L);
        BIG_64 = Sprintf.BIG_32.shiftLeft(32);
        BIG_MINUS_32 = BigInteger.valueOf(-4294967296L);
        BIG_MINUS_64 = Sprintf.BIG_MINUS_32.shiftLeft(32);
    }
    
    private static final class Args
    {
        private final Ruby runtime;
        private final Locale locale;
        private final IRubyObject rubyObject;
        private final RubyArray rubyArray;
        private final int length;
        private int unnumbered;
        private int numbered;
        
        Args(final Locale locale, final IRubyObject rubyObject) {
            if (rubyObject == null) {
                throw new IllegalArgumentException("null IRubyObject passed to sprintf");
            }
            this.locale = ((locale == null) ? Locale.getDefault() : locale);
            this.rubyObject = rubyObject;
            if (rubyObject instanceof RubyArray) {
                this.rubyArray = (RubyArray)rubyObject;
                this.length = this.rubyArray.size();
            }
            else {
                this.length = 1;
                this.rubyArray = null;
            }
            this.runtime = rubyObject.getRuntime();
        }
        
        Args(final IRubyObject rubyObject) {
            this(Locale.getDefault(), rubyObject);
        }
        
        Args(final Ruby runtime, final long value) {
            this(RubyFixnum.newFixnum(runtime, value));
        }
        
        void raiseArgumentError(final String message) {
            throw this.runtime.newArgumentError(message);
        }
        
        void warn(final IRubyWarnings.ID id, final String message) {
            this.runtime.getWarnings().warn(id, message, new Object[0]);
        }
        
        void warning(final IRubyWarnings.ID id, final String message) {
            if (this.runtime.isVerbose()) {
                this.runtime.getWarnings().warning(id, message, new Object[0]);
            }
        }
        
        IRubyObject next() {
            if (this.numbered > 0) {
                this.raiseArgumentError("unnumbered" + (this.unnumbered + 1) + "mixed with numbered");
            }
            if (this.unnumbered >= this.length) {
                this.raiseArgumentError("too few arguments");
            }
            final IRubyObject object = (this.rubyArray == null) ? this.rubyObject : this.rubyArray.eltInternal(this.unnumbered);
            ++this.unnumbered;
            return object;
        }
        
        IRubyObject get(final int index) {
            if (this.unnumbered > 0) {
                this.raiseArgumentError("numbered(" + this.numbered + ") after unnumbered(" + this.unnumbered + ")");
            }
            if (index < 0) {
                this.raiseArgumentError("invalid index - " + (index + 1) + '$');
            }
            if (index >= this.length) {
                this.raiseArgumentError("too few arguments");
            }
            this.numbered = index + 1;
            return (this.rubyArray == null) ? this.rubyObject : this.rubyArray.eltInternal(index);
        }
        
        IRubyObject getNth(final int formatIndex) {
            return this.get(formatIndex - 1);
        }
        
        int nextInt() {
            return this.intValue(this.next());
        }
        
        int getInt(final int index) {
            return this.intValue(this.get(index));
        }
        
        int getNthInt(final int formatIndex) {
            return this.intValue(this.get(formatIndex - 1));
        }
        
        int intValue(IRubyObject obj) {
            if (obj instanceof RubyNumeric) {
                return (int)((RubyNumeric)obj).getLongValue();
            }
            obj = TypeConverter.convertToType(obj, obj.getRuntime().getFixnum(), "to_int", true);
            return (int)((RubyFixnum)obj).getLongValue();
        }
        
        byte getDecimalSeparator() {
            return (byte)new DecimalFormatSymbols(this.locale).getDecimalSeparator();
        }
    }
}
