// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import logging.LogHolder;
import logging.LogType;

public class Base64
{
    public static final String BEGIN_TAG = "-----BEGIN ";
    public static final String END_TAG = "-----END ";
    public static final String TAG_END_SEQUENCE = "-----";
    public static final boolean ENCODE = true;
    public static final boolean DECODE = false;
    public static final boolean COMPRESS = true;
    public static final boolean DONT_COMPRESS = false;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte EQUALS_SIGN = 61;
    private static final byte NEW_LINE = 10;
    private static final byte[] ALPHABET;
    private static final byte[] DECODABET;
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte EQUALS_SIGN_ENC = -1;
    
    private static byte[] encode3to4(final byte[] array, final int n, final int n2, final byte[] array2, final int n3) {
        final int n4 = ((n2 > 0) ? (array[n] << 24 >>> 8) : 0) | ((n2 > 1) ? (array[n + 1] << 24 >>> 16) : 0) | ((n2 > 2) ? (array[n + 2] << 24 >>> 24) : 0);
        switch (n2) {
            case 3: {
                array2[n3] = Base64.ALPHABET[n4 >>> 18];
                array2[n3 + 1] = Base64.ALPHABET[n4 >>> 12 & 0x3F];
                array2[n3 + 2] = Base64.ALPHABET[n4 >>> 6 & 0x3F];
                array2[n3 + 3] = Base64.ALPHABET[n4 & 0x3F];
                return array2;
            }
            case 2: {
                array2[n3] = Base64.ALPHABET[n4 >>> 18];
                array2[n3 + 1] = Base64.ALPHABET[n4 >>> 12 & 0x3F];
                array2[n3 + 2] = Base64.ALPHABET[n4 >>> 6 & 0x3F];
                array2[n3 + 3] = 61;
                return array2;
            }
            case 1: {
                array2[n3] = Base64.ALPHABET[n4 >>> 18];
                array2[n3 + 1] = Base64.ALPHABET[n4 >>> 12 & 0x3F];
                array2[n3 + 3] = (array2[n3 + 2] = 61);
                return array2;
            }
            default: {
                return array2;
            }
        }
    }
    
    public static String createBeginTag(final String s) {
        return "-----BEGIN " + s + "-----" + "\n";
    }
    
    public static String createEndTag(final String s) {
        return "\n-----END " + s + "-----" + "\n";
    }
    
    public static String encodeBytes(final byte[] array) {
        return encode(array, true);
    }
    
    public static String encode(final byte[] array, final boolean b) {
        if (array == null) {
            return null;
        }
        return encode(array, 0, array.length, b);
    }
    
    public static String encode(final byte[] array, final int n, final int n2) {
        return encode(array, n, n2, true);
    }
    
    public static String encode(final byte[] array, final int n, final int n2, final boolean b) {
        final int n3 = n2 * 4 / 3;
        final byte[] array2 = new byte[n3 + ((n2 % 3 > 0) ? 4 : 0) + (b ? (n3 / 76) : 0)];
        int i = 0;
        int n4 = 0;
        final int n5 = n2 - 2;
        int n6 = 0;
        while (i < n5) {
            encode3to4(array, i + n, 3, array2, n4);
            n6 += 4;
            if (b && n6 == 76) {
                array2[n4 + 4] = 10;
                ++n4;
                n6 = 0;
            }
            i += 3;
            n4 += 4;
        }
        if (i < n2) {
            encode3to4(array, i + n, n2 - i, array2, n4);
            n4 += 4;
        }
        return new String(array2, 0, n4);
    }
    
    public static String encodeString(final String s) {
        return encodeString(s, true);
    }
    
    public static String encodeString(final String s, final boolean b) {
        if (s == null) {
            return null;
        }
        return encode(s.getBytes(), b);
    }
    
    private static int decode4to3(final byte[] array, final int n, final byte[] array2, final int n2) {
        if (array[n + 2] == 61) {
            array2[n2] = (byte)(((Base64.DECODABET[array[n]] & 0xFF) << 18 | (Base64.DECODABET[array[n + 1]] & 0xFF) << 12) >>> 16);
            return 1;
        }
        if (array[n + 3] == 61) {
            final int n3 = (Base64.DECODABET[array[n]] & 0xFF) << 18 | (Base64.DECODABET[array[n + 1]] & 0xFF) << 12 | (Base64.DECODABET[array[n + 2]] & 0xFF) << 6;
            array2[n2] = (byte)(n3 >>> 16);
            array2[n2 + 1] = (byte)(n3 >>> 8);
            return 2;
        }
        try {
            final int n4 = (Base64.DECODABET[array[n]] & 0xFF) << 18 | (Base64.DECODABET[array[n + 1]] & 0xFF) << 12 | (Base64.DECODABET[array[n + 2]] & 0xFF) << 6 | (Base64.DECODABET[array[n + 3]] & 0xFF);
            array2[n2] = (byte)(n4 >> 16);
            array2[n2 + 1] = (byte)(n4 >> 8);
            array2[n2 + 2] = (byte)n4;
            return 3;
        }
        catch (Exception ex) {
            System.out.println("" + array[n] + ": " + Base64.DECODABET[array[n]]);
            System.out.println("" + array[n + 1] + ": " + Base64.DECODABET[array[n + 1]]);
            System.out.println("" + array[n + 2] + ": " + Base64.DECODABET[array[n + 2]]);
            System.out.println("" + array[n + 3] + ": " + Base64.DECODABET[array[n + 3]]);
            return -1;
        }
    }
    
    public static byte[] decode(final String s) {
        final byte[] bytes = s.getBytes();
        return decode(bytes, 0, bytes.length);
    }
    
    public static String decodeToString(final String s) {
        return new String(decode(s));
    }
    
    public static byte[] decode(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[n2 * 3 / 4];
        int n3 = 0;
        final byte[] array3 = new byte[4];
        int n4 = 0;
        for (int i = n; i < n + n2; ++i) {
            final byte b = (byte)(array[i] & 0x7F);
            final byte b2 = Base64.DECODABET[b];
            if (b2 < -5) {
                LogHolder.log(7, LogType.MISC, "Bad Base64 input character at " + i + ": " + array[i] + "(decimal)");
                return null;
            }
            if (b2 >= -1) {
                array3[n4++] = b;
                if (n4 > 3) {
                    n3 += decode4to3(array3, 0, array2, n3);
                    n4 = 0;
                    if (b == 61) {
                        break;
                    }
                }
            }
        }
        final byte[] array4 = new byte[n3];
        System.arraycopy(array2, 0, array4, 0, n3);
        return array4;
    }
    
    static {
        ALPHABET = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9 };
    }
}
