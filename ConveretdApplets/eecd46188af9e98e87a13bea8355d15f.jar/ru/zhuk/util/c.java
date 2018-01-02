// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.util;

import java.io.ByteArrayOutputStream;

public class c
{
    public static byte[] a(final String s) {
        final byte[] array = new byte[3];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(s.length());
        int n = 1;
        int i;
        for (i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (!Character.isWhitespace(char1)) {
                byte b;
                if (char1 >= 'A' && char1 <= 'Z') {
                    b = (byte)(char1 - 'A');
                }
                else if (char1 >= 'a' && char1 <= 'z') {
                    b = (byte)('\u001a' + (char1 - 'a'));
                }
                else if (char1 >= '0' && char1 <= '9') {
                    b = (byte)('4' + (char1 - '0'));
                }
                else if (char1 == '+') {
                    b = 62;
                }
                else if (char1 == '/') {
                    b = 63;
                }
                else {
                    if (char1 == '=') {
                        break;
                    }
                    return null;
                }
                switch (n) {
                    case 1: {
                        array[0] = (byte)(b << 2);
                        break;
                    }
                    case 2: {
                        final byte[] array2 = array;
                        final int n2 = 0;
                        array2[n2] |= (byte)(b >>> 4);
                        array[1] = (byte)((b & 0xF) << 4);
                        break;
                    }
                    case 3: {
                        final byte[] array3 = array;
                        final int n3 = 1;
                        array3[n3] |= (byte)(b >>> 2);
                        array[2] = (byte)((b & 0x3) << 6);
                        break;
                    }
                    case 4: {
                        final byte[] array4 = array;
                        final int n4 = 2;
                        array4[n4] |= b;
                        byteArrayOutputStream.write(array, 0, array.length);
                        break;
                    }
                }
                n = ((n < 4) ? (n + 1) : 1);
            }
        }
        if (i >= s.length()) {
            return (byte[])((n == 1) ? byteArrayOutputStream.toByteArray() : null);
        }
        switch (n) {
            case 3: {
                byteArrayOutputStream.write(array, 0, 1);
                return (byte[])((s.charAt(i) == '=' && s.charAt(i + 1) == '=') ? byteArrayOutputStream.toByteArray() : null);
            }
            case 4: {
                byteArrayOutputStream.write(array, 0, 2);
                return (byte[])((s.charAt(i) == '=') ? byteArrayOutputStream.toByteArray() : null);
            }
            default: {
                return null;
            }
        }
    }
    
    public static String a(final byte[] array) {
        final char[] array2 = new char[4];
        int n = 1;
        int n2 = 0;
        int n3 = 0;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            final int n4 = (array[i] >= 0) ? array[i] : ((array[i] & 0x7F) + 128);
            switch (n) {
                case 1: {
                    array2[0] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(n4 >>> 2);
                    n2 = (n4 & 0x3);
                    break;
                }
                case 2: {
                    array2[1] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(n2 << 4 | n4 >>> 4);
                    n2 = (n4 & 0xF);
                    break;
                }
                case 3: {
                    array2[2] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(n2 << 2 | n4 >>> 6);
                    array2[3] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(n4 & 0x3F);
                    sb.append(array2);
                    if (++n3 % 19 == 0) {
                        sb.append("\r\n");
                        break;
                    }
                    break;
                }
            }
            n = ((n < 3) ? (n + 1) : 1);
        }
        switch (n) {
            case 2: {
                array2[1] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(n2 << 4);
                array2[2] = (array2[3] = '=');
                sb.append(array2);
                break;
            }
            case 3: {
                array2[2] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(n2 << 2);
                array2[3] = '=';
                sb.append(array2);
                break;
            }
        }
        return sb.toString();
    }
}
