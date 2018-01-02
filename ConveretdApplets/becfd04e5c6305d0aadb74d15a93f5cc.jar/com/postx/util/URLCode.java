// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util;

public class URLCode
{
    public static final String Ident = "$Id: URLCode.java,v 1.2 2008/06/20 23:14:32 bting Exp $";
    public static final int FLAG_X_WWW_FORM_URLENCODED = 1;
    public static final int FLAG_UTF_8 = 128;
    public static final int FLAG_UTF_16 = 256;
    public static final int FLAGS_DEFAULT = 0;
    public static final String LIT_URL = ";/?:@=&";
    private static final boolean[] encodeFlags;
    private static char[] h2c;
    private static String[][] plain;
    private static int[] flags;
    private static String[][] encoded;
    private static String[] errors;
    private static String[][] badTestsUTF8;
    private static String[][] badTestsUTF16;
    
    public static String encode(final String s) {
        return encode(s, 0, null, null);
    }
    
    public static String encode(final String s, final String s2) {
        return encode(s, 0, s2, null);
    }
    
    public static String encode(final String s, final String s2, final String s3) {
        return encode(s, 0, s2, s3);
    }
    
    public static String encode(final String s, final int n) {
        return encode(s, n, null, null);
    }
    
    public static String encode(final String s, final int n, final String s2) {
        return encode(s, n, s2, null);
    }
    
    public static String encode(final String s, int n, final String s2, final String s3) {
        final boolean b = (n & 0x1) != 0x0;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        if ((n & 0x180) == 0x180) {
            n &= 0xFFFFFEFF;
        }
        boolean[] array = null;
        if (s2 != null) {
            array = stringToEncodingFlags(s2, array, false);
        }
        if (b) {
            array = stringToEncodingFlags("+", array, true);
        }
        if (s3 != null) {
            array = stringToEncodingFlags(s3, array, true);
        }
        if (array == null) {
            array = URLCode.encodeFlags;
        }
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 < '\u0080' && !array[char1]) {
                sb.append(char1);
            }
            else if (char1 == ' ' && b) {
                sb.append('+');
            }
            else {
                sb.append('%');
                if (char1 < '\u0080' || (n & 0x180) == 0x0) {
                    toHex(sb, char1);
                }
                else if ((n & 0x100) != 0x0) {
                    sb.append('u');
                    toHex(sb, char1 >> 8);
                    toHex(sb, char1);
                }
                else if (char1 < '\u0800') {
                    toHex(sb, '\u00c0' | char1 >> 6);
                    sb.append('%');
                    toHex(sb, '\u0080' | (char1 & '?'));
                }
                else {
                    toHex(sb, '\u00e0' | char1 >> 12);
                    sb.append('%');
                    toHex(sb, '\u0080' | (char1 >> 6 & '?'));
                    sb.append('%');
                    toHex(sb, '\u0080' | (char1 & '?'));
                }
            }
        }
        return sb.toString();
    }
    
    private static void toHex(final StringBuffer sb, final int n) {
        sb.append(URLCode.h2c[n >> 4 & 0xF]).append(URLCode.h2c[n & 0xF]);
    }
    
    public static String prepareForURL(final String s) {
        return prepareForURL(s, 128);
    }
    
    public static String prepareForURL(final String s, final int n) {
        String encode = null;
        if (StringConversion.hasContents(s)) {
            encode = encode(StringConversion.HTMLUnescape(s), n);
        }
        if (encode == null) {
            return "";
        }
        return encode;
    }
    
    static {
        encodeFlags = new boolean[] { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true };
        URLCode.h2c = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        URLCode.plain = new String[][] { { "abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz" }, { "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ" }, { "0123456789", "0123456789" }, { " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~", " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~" }, { "\u0000\u0001\u001e\u001f", "\u0000\u0001\u001e\u001f" }, { "\u0080\u0081\u00fe\u00ff", "\u0080\u0081\u00fe\u00ff" }, { "\u07fe\u07ff\u0800\u0801", "\u00fe\u00ff\u0000\u0001" } };
        URLCode.flags = new int[] { 256, 128, 0, 257, 129, 1 };
        URLCode.encoded = new String[][] { { "abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz" }, { "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ" }, { "0123456789", "0123456789", "0123456789", "0123456789", "0123456789", "0123456789" }, { "%20!%22%23$%25%26'()*+,-.%2F%3A%3B%3C%3D%3E%3F%40%5B%5C%5D%5E_%60%7B%7C%7D%7E", "%20!%22%23$%25%26'()*+,-.%2F%3A%3B%3C%3D%3E%3F%40%5B%5C%5D%5E_%60%7B%7C%7D%7E", "%20!%22%23$%25%26'()*+,-.%2F%3A%3B%3C%3D%3E%3F%40%5B%5C%5D%5E_%60%7B%7C%7D%7E", "+!%22%23$%25%26'()*%2B,-.%2F%3A%3B%3C%3D%3E%3F%40%5B%5C%5D%5E_%60%7B%7C%7D%7E", "+!%22%23$%25%26'()*%2B,-.%2F%3A%3B%3C%3D%3E%3F%40%5B%5C%5D%5E_%60%7B%7C%7D%7E", "+!%22%23$%25%26'()*%2B,-.%2F%3A%3B%3C%3D%3E%3F%40%5B%5C%5D%5E_%60%7B%7C%7D%7E" }, { "%00%01%1E%1F", "%00%01%1E%1F", "%00%01%1E%1F", "%00%01%1E%1F", "%00%01%1E%1F", "%00%01%1E%1F" }, { "%u0080%u0081%u00FE%u00FF", "%C2%80%C2%81%C3%BE%C3%BF", "%80%81%FE%FF", "%u0080%u0081%u00FE%u00FF", "%C2%80%C2%81%C3%BE%C3%BF", "%80%81%FE%FF" }, { "%u07FE%u07FF%u0800%u0801", "%DF%BE%DF%BF%E0%A0%80%E0%A0%81", "%FE%FF%00%01", "%u07FE%u07FF%u0800%u0801", "%DF%BE%DF%BF%E0%A0%80%E0%A0%81", "%FE%FF%00%01" } };
        URLCode.errors = new String[] { "unexpected UTF-16 encoding at 0", "invalid hex digit at 1", "invalid UTF-8 sequence at 0", "partial encoding at 1", "partial encoding at 2" };
        URLCode.badTestsUTF8 = new String[][] { { "%u0000", URLCode.errors[0] }, { "%/", URLCode.errors[1] }, { "%:", URLCode.errors[1] }, { "%@", URLCode.errors[1] }, { "%G", URLCode.errors[1] }, { "%`", URLCode.errors[1] }, { "%g", URLCode.errors[1] }, { "%80", URLCode.errors[2] }, { "%bf", URLCode.errors[2] }, { "%c0", URLCode.errors[2] }, { "%df", URLCode.errors[2] }, { "%e0", URLCode.errors[2] }, { "%ef", URLCode.errors[2] }, { "%e080", URLCode.errors[2] }, { "%ef80", URLCode.errors[2] }, { "%c0%00", URLCode.errors[2] }, { "%c0%7f", URLCode.errors[2] }, { "%c0%c0", URLCode.errors[2] }, { "%c0%ff", URLCode.errors[2] }, { "%df%00", URLCode.errors[2] }, { "%df%7f", URLCode.errors[2] }, { "%df%c0", URLCode.errors[2] }, { "%df%ff", URLCode.errors[2] }, { "%e0%00", URLCode.errors[2] }, { "%e0%7f", URLCode.errors[2] }, { "%e0%c0", URLCode.errors[2] }, { "%e0%ff", URLCode.errors[2] }, { "%ef%00", URLCode.errors[2] }, { "%ef%7f", URLCode.errors[2] }, { "%ef%c0", URLCode.errors[2] }, { "%ef%ff", URLCode.errors[2] }, { "%f0%80%80", URLCode.errors[2] }, { "%fe%80%80", URLCode.errors[2] }, { "%ff%80%80", URLCode.errors[2] }, { "%c0%80", URLCode.errors[2] }, { "%c0%af", URLCode.errors[2] }, { "%c1%bf", URLCode.errors[2] }, { "%e0%80%80", URLCode.errors[2] }, { "%e0%80%af", URLCode.errors[2] }, { "%e0%9f%bf", URLCode.errors[2] } };
        URLCode.badTestsUTF16 = new String[][] { { "%1", URLCode.errors[3] }, { "%u", URLCode.errors[4] }, { "%u1", URLCode.errors[4] }, { "%u12", URLCode.errors[4] }, { "%u123", URLCode.errors[4] } };
    }
    
    public static String decode(final String s) {
        return decode(s, 0);
    }
    
    public static String decode(final String s, final int n) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            char char1;
            if ((char1 = s.charAt(i)) != '%') {
                if (char1 == '+' && (n & 0x1) != 0x0) {
                    char1 = ' ';
                }
                sb.append(char1);
            }
            else {
                if (i == length - 1) {
                    throw new IllegalArgumentException("trailing %");
                }
                if (s.charAt(i + 1) == 'u') {
                    if ((n & 0x100) == 0x0) {
                        throw new IllegalArgumentException("unexpected UTF-16 encoding at " + i);
                    }
                    sb.append(fromHex(s, i + 2, i + 6));
                    i += 5;
                }
                else {
                    final char fromHex = fromHex(s, i + 1, i + 3);
                    if ((n & 0x80) == 0x0) {
                        sb.append(fromHex);
                        i += 2;
                    }
                    else {
                        int n2;
                        try {
                            switch ((n2 = fromHex) & 0xE0) {
                                case 192: {
                                    final char fromHex2;
                                    if (i > length - 6 || s.charAt(i + 3) != '%' || ((fromHex2 = fromHex(s, i + 4, i + 6)) & '\u00c0') != '\u0080' || (n2 = ((n2 & 0x1F) << 6) + (fromHex2 & '?')) < 128 || n2 > 2047) {
                                        throw new Exception();
                                    }
                                    i += 5;
                                    break;
                                }
                                case 224: {
                                    final char fromHex3;
                                    final char fromHex4;
                                    if (i > length - 9 || s.charAt(i + 3) != '%' || s.charAt(i + 6) != '%' || ((fromHex3 = fromHex(s, i + 4, i + 6)) & '\u00c0') != '\u0080' || ((fromHex4 = fromHex(s, i + 7, i + 9)) & '\u00c0') != '\u0080' || (n2 = ((n2 & 0xF) << 12) + ((fromHex3 & '?') << 6) + (fromHex4 & '?')) < 2048 || n2 > 65535) {
                                        throw new Exception();
                                    }
                                    i += 8;
                                    break;
                                }
                                default: {
                                    if (n2 >= 128) {
                                        throw new Exception();
                                    }
                                    i += 2;
                                    break;
                                }
                            }
                        }
                        catch (Exception ex) {
                            throw new IllegalArgumentException("invalid UTF-8 sequence at " + i);
                        }
                        sb.append((char)n2);
                    }
                }
            }
        }
        return sb.toString();
    }
    
    private static boolean[] stringToEncodingFlags(final String s, boolean[] array, final boolean b) {
        final int length = s.length();
        if (array == null) {
            array = new boolean[URLCode.encodeFlags.length];
            System.arraycopy(URLCode.encodeFlags, 0, array, 0, array.length);
        }
        for (int i = 0; i < length; ++i) {
            array[s.charAt(i)] = b;
        }
        return array;
    }
    
    private static char fromHex(final String s, final int n, final int n2) {
        int n3 = 0;
        for (int i = n; i < n2; ++i) {
            char char1;
            try {
                char1 = s.charAt(i);
            }
            catch (IndexOutOfBoundsException ex) {
                throw new IllegalArgumentException("partial encoding at " + n);
            }
            final int n4 = n3 << 4;
            if ('0' <= char1 && char1 <= '9') {
                n3 = n4 + (char1 & '\u000f');
            }
            else {
                if (('A' > char1 || char1 > 'F') && ('a' > char1 || char1 > 'f')) {
                    throw new IllegalArgumentException("invalid hex digit at " + i);
                }
                n3 = n4 + ('\t' + (char1 & '\u000f'));
            }
        }
        return (char)n3;
    }
}
