// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.codec.binary;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;

public class Hex implements BinaryEncoder, BinaryDecoder
{
    private static final char[] DIGITS;
    
    public static byte[] decodeHex(final char[] data) throws DecoderException {
        final int len = data.length;
        if ((len & 0x1) != 0x0) {
            throw new DecoderException("Odd number of characters.");
        }
        final byte[] out = new byte[len >> 1];
        int f;
        for (int i = 0, j = 0; j < len; ++j, f |= toDigit(data[j], j), ++j, out[i] = (byte)(f & 0xFF), ++i) {
            f = toDigit(data[j], j) << 4;
        }
        return out;
    }
    
    protected static int toDigit(final char ch, final int index) throws DecoderException {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new DecoderException("Illegal hexadecimal charcter " + ch + " at index " + index);
        }
        return digit;
    }
    
    public static char[] encodeHex(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        int i = 0;
        int j = 0;
        while (i < l) {
            out[j++] = Hex.DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = Hex.DIGITS[0xF & data[i]];
            ++i;
        }
        return out;
    }
    
    public byte[] decode(final byte[] array) throws DecoderException {
        return decodeHex(new String(array).toCharArray());
    }
    
    public Object decode(final Object object) throws DecoderException {
        try {
            final char[] charArray = (object instanceof String) ? ((String)object).toCharArray() : object;
            return decodeHex(charArray);
        }
        catch (ClassCastException e) {
            throw new DecoderException(e.getMessage());
        }
    }
    
    public byte[] encode(final byte[] array) {
        return new String(encodeHex(array)).getBytes();
    }
    
    public Object encode(final Object object) throws EncoderException {
        try {
            final byte[] byteArray = (object instanceof String) ? ((String)object).getBytes() : object;
            return encodeHex(byteArray);
        }
        catch (ClassCastException e) {
            throw new EncoderException(e.getMessage());
        }
    }
    
    static {
        DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
}
