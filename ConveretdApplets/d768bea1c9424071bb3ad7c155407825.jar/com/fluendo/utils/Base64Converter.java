// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.utils;

public class Base64Converter
{
    public static final char[] alphabet;
    
    public static String encode(final byte[] array) {
        char[] array2;
        int n;
        int n2;
        int n3;
        for (array2 = new char[((array.length - 1) / 3 + 1) * 4], n = 0, n2 = 0; n2 + 3 <= array.length; n3 = ((array[n2++] & 0xFF) << 16 | (array[n2++] & 0xFF) << 8 | (array[n2++] & 0xFF) << 0), array2[n++] = Base64Converter.alphabet[(n3 & 0xFC0000) >> 18], array2[n++] = Base64Converter.alphabet[(n3 & 0x3F000) >> 12], array2[n++] = Base64Converter.alphabet[(n3 & 0xFC0) >> 6], array2[n++] = Base64Converter.alphabet[n3 & 0x3F]) {}
        if (array.length - n2 == 2) {
            final int n4 = (array[n2] & 0xFF) << 16 | (array[n2 + 1] & 0xFF) << 8;
            array2[n++] = Base64Converter.alphabet[(n4 & 0xFC0000) >> 18];
            array2[n++] = Base64Converter.alphabet[(n4 & 0x3F000) >> 12];
            array2[n++] = Base64Converter.alphabet[(n4 & 0xFC0) >> 6];
            array2[n++] = '=';
        }
        else if (array.length - n2 == 1) {
            final int n5 = (array[n2] & 0xFF) << 16;
            array2[n++] = Base64Converter.alphabet[(n5 & 0xFC0000) >> 18];
            array2[n++] = Base64Converter.alphabet[(n5 & 0x3F000) >> 12];
            array2[n++] = '=';
            array2[n++] = '=';
        }
        return new String(array2);
    }
    
    static {
        alphabet = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
    }
}
