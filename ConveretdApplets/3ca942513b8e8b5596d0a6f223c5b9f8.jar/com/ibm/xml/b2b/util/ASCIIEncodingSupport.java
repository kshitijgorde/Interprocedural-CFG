// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public final class ASCIIEncodingSupport extends SingleByteEncodingSupport
{
    static final char[] fgCharMap;
    static final byte[] fgContentMap;
    static final byte[] fgNameCharMap;
    private static EncodingSupport fgSingleton;
    
    public static EncodingSupport getInstance() {
        return ASCIIEncodingSupport.fgSingleton;
    }
    
    public boolean isASCIITransparent() {
        return true;
    }
    
    public void convertCharsToBytes(final char[] array, final int n, final int n2, final byte[][] array2, final int[] array3) {
        int n3 = n;
        byte[] array4 = array2[0];
        int i = array3[0];
        final int j = i + (n2 - n3);
        if (j > array4.length) {
            int n4;
            for (n4 = array4.length << 1; j > n4; n4 <<= 1) {}
            array4 = new byte[n4];
            System.arraycopy(array2[0], 0, array4, 0, i);
            array2[0] = array4;
        }
        while (i < j) {
            final char c = array[n3++];
            if (c < '\u0080') {
                array4[i++] = (byte)c;
            }
            else {
                CharConversionError.unableToConvertOutOfRangeUnicodeCharacter();
            }
        }
        array3[0] = i;
    }
    
    public int encodeCharacter(final int n, final byte[][] array, int n2) {
        byte[] array2 = array[0];
        if (n2 == array2.length) {
            array2 = new byte[array2.length << 1];
            System.arraycopy(array[0], 0, array2, 0, n2);
            array[0] = array2;
        }
        if (n < 128) {
            array2[n2++] = (byte)n;
        }
        else {
            CharConversionError.unableToConvertOutOfRangeUnicodeCharacter();
        }
        return n2;
    }
    
    public int normalizeLineBreaks(final byte[] array, int n, final int n2, final boolean[] array2) {
        boolean b = array2[0];
        for (int i = n; i < n2; ++i) {
            byte b2 = array[i];
            if (b) {
                b = false;
                if (b2 == 10) {
                    continue;
                }
            }
            if (b2 == 13) {
                b2 = 10;
                b = true;
            }
            array[n++] = b2;
        }
        array2[0] = b;
        return n;
    }
    
    public void convertBytesToChars(final byte[] array, final int n, final int n2, final char[][] array2, final int[] array3) {
        int n3 = n;
        char[] array4 = array2[0];
        int i = array3[0];
        final int j = i + (n2 - n3);
        if (j > array4.length) {
            int n4;
            for (n4 = array4.length << 1; j > n4; n4 <<= 1) {}
            array4 = new char[n4];
            System.arraycopy(array2[0], 0, array4, 0, i);
            array2[0] = array4;
        }
        while (i < j) {
            final int n5 = array[n3++] & 0xFF;
            if (n5 < 128) {
                array4[i++] = (char)n5;
            }
            else {
                CharConversionError.unableToConvertOutOfRangeUnicodeCharacter();
            }
        }
        array3[0] = i;
    }
    
    public int decodeCharacter(final byte[] array, final int n, final int n2, final int[] array2) {
        if (n >= n2) {
            CharConversionError.insufficientInputToDecodeCharacter();
            return -1;
        }
        final int n3 = array[n] & 0xFF;
        if (n3 < 128) {
            if (array2 != null) {
                array2[0] = 1;
            }
            return n3;
        }
        CharConversionError.unableToConvertOutOfRangeUnicodeCharacter();
        return -1;
    }
    
    protected int normalizeBytesToChars(final byte[] array, int n, final int n2, final char[][] array2, final int[] array3, final boolean[] array4) {
        int n4;
        final int n3 = n4 = n2 - n;
        char[] array5 = array2[0];
        int n5 = array3[0];
        boolean b = array4[0];
        if (n5 + n3 > array5.length) {
            int n6;
            for (n6 = array5.length << 1; n5 + n3 > n6; n6 <<= 1) {}
            array5 = new char[n6];
            System.arraycopy(array2[0], 0, array5, 0, n5);
            array2[0] = array5;
        }
        while (n4-- > 0) {
            final int n7 = array[n++] & 0xFF;
            char c;
            if (n7 < 128) {
                c = (char)n7;
            }
            else {
                CharConversionError.unableToConvertOutOfRangeUnicodeCharacter();
                c = '\0';
            }
            if (b) {
                b = false;
                if (c == '\n') {
                    continue;
                }
            }
            if (c == '\r') {
                c = '\n';
                b = true;
            }
            array5[n5++] = c;
        }
        array3[0] = n5;
        array4[0] = b;
        return n3;
    }
    
    private ASCIIEncodingSupport() {
        super(ASCIIEncodingSupport.fgCharMap, ASCIIEncodingSupport.fgContentMap, ASCIIEncodingSupport.fgNameCharMap);
    }
    
    static {
        fgCharMap = new char[] { '\0', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', ' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', '\u007f', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd', '\ufffd' };
        fgContentMap = new byte[] { 5, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        fgNameCharMap = new byte[] { 8, 8, 8, 8, 8, 8, 8, 8, 8, 20, 20, 8, 8, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 2, 2, 12, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 28, 8, 24, 16, 8, 8, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 8, 8, 8, 3, 8, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 };
        ASCIIEncodingSupport.fgSingleton = new ASCIIEncodingSupport();
    }
}
