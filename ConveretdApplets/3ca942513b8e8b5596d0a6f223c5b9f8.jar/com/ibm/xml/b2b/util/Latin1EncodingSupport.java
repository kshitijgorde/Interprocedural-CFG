// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public final class Latin1EncodingSupport extends SingleByteEncodingSupport
{
    static final char[] fgCharMap;
    static final byte[] fgContentMap;
    static final byte[] fgNameCharMap;
    private static EncodingSupport fgSingleton;
    
    public static EncodingSupport getInstance() {
        return Latin1EncodingSupport.fgSingleton;
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
            if (c < '\u0100') {
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
        if (n < 256) {
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
            array4[i++] = (char)(array[n3++] & 0xFF);
        }
        array3[0] = i;
    }
    
    public int decodeCharacter(final byte[] array, final int n, final int n2, final int[] array2) {
        if (n < n2) {
            final int n3 = array[n] & 0xFF;
            if (array2 != null) {
                array2[0] = 1;
            }
            return n3;
        }
        CharConversionError.insufficientInputToDecodeCharacter();
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
            char c = (char)(array[n++] & 0xFF);
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
    
    private Latin1EncodingSupport() {
        super(Latin1EncodingSupport.fgCharMap, Latin1EncodingSupport.fgContentMap, Latin1EncodingSupport.fgNameCharMap);
    }
    
    static {
        fgCharMap = new char[] { '\0', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', ' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', '\u007f', '\u0080', '\u0081', '\u0082', '\u0083', '\u0084', '\u0085', '\u0086', '\u0087', '\u0088', '\u0089', '\u008a', '\u008b', '\u008c', '\u008d', '\u008e', '\u008f', '\u0090', '\u0091', '\u0092', '\u0093', '\u0094', '\u0095', '\u0096', '\u0097', '\u0098', '\u0099', '\u009a', '\u009b', '\u009c', '\u009d', '\u009e', '\u009f', ' ', '¡', '¢', '£', '¤', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '\u00ad', '®', '¯', '°', '±', '²', '³', '´', 'µ', '¶', '·', '¸', '¹', 'º', '»', '¼', '½', '¾', '¿', '\u00c0', '\u00c1', '\u00c2', '\u00c3', '\u00c4', '\u00c5', '\u00c6', '\u00c7', '\u00c8', '\u00c9', '\u00ca', '\u00cb', '\u00cc', '\u00cd', '\u00ce', '\u00cf', '\u00d0', '\u00d1', '\u00d2', '\u00d3', '\u00d4', '\u00d5', '\u00d6', '\u00d7', '\u00d8', '\u00d9', '\u00da', '\u00db', '\u00dc', '\u00dd', '\u00de', '\u00df', '\u00e0', '\u00e1', '\u00e2', '\u00e3', '\u00e4', '\u00e5', '\u00e6', '\u00e7', '\u00e8', '\u00e9', '\u00ea', '\u00eb', '\u00ec', '\u00ed', '\u00ee', '\u00ef', '\u00f0', '\u00f1', '\u00f2', '\u00f3', '\u00f4', '\u00f5', '\u00f6', '\u00f7', '\u00f8', '\u00f9', '\u00fa', '\u00fb', '\u00fc', '\u00fd', '\u00fe', '\u00ff' };
        fgContentMap = new byte[] { 5, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        fgNameCharMap = new byte[] { 8, 8, 8, 8, 8, 8, 8, 8, 8, 20, 20, 8, 8, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 2, 2, 12, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 28, 8, 24, 16, 8, 8, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 8, 8, 8, 3, 8, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 2, 8, 8, 8, 8, 8, 8, 8, 8, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 3, 3, 3, 3, 3, 3, 3, 3 };
        Latin1EncodingSupport.fgSingleton = new Latin1EncodingSupport();
    }
}
