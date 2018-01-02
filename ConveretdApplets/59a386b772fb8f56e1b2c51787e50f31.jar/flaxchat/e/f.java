// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

public class f
{
    private static final char[] a;
    private static final char[] b;
    private static final byte[] c;
    private static final byte[] d;
    private static String[] z;
    
    public static String a(final byte[] array) {
        return a(array, false);
    }
    
    private static String a(final byte[] array, final boolean b) {
        final boolean a = g.a;
        final int length = array.length;
        final int n = length / 3;
        final int n2 = length - 3 * n;
        final StringBuffer sb = new StringBuffer(4 * ((length + 2) / 3));
        final char[] array2 = b ? f.b : f.a;
        int n3 = 0;
        int n4 = 0;
        while (true) {
            Label_0177: {
                if (!a) {
                    break Label_0177;
                }
                final int n5 = array[n3++] & 0xFF;
                final int n6 = array[n3++] & 0xFF;
                final int n7 = array[n3++] & 0xFF;
                sb.append(array2[n5 >> 2]);
                sb.append(array2[(n5 << 4 & 0x3F) | n6 >> 4]);
                sb.append(array2[(n6 << 2 & 0x3F) | n7 >> 6]);
                sb.append(array2[n7 & 0x3F]);
                ++n4;
            }
            if (n4 >= n) {
                if (n2 != 0) {
                    final int n8 = array[n3++] & 0xFF;
                    sb.append(array2[n8 >> 2]);
                    if (n2 == 1) {
                        sb.append(array2[n8 << 4 & 0x3F]);
                        sb.append(f.z[0]);
                        if (!a) {
                            return sb.toString();
                        }
                    }
                    final int n9 = array[n3++] & 0xFF;
                    sb.append(array2[(n8 << 4 & 0x3F) | n9 >> 4]);
                    sb.append(array2[n9 << 2 & 0x3F]);
                    sb.append('=');
                }
                return sb.toString();
            }
            continue;
        }
    }
    
    public static String a(final String s) {
        return new String(a(s, false));
    }
    
    private static byte[] a(final String s, final boolean b) {
        final boolean a = g.a;
        final byte[] array = b ? f.d : f.c;
        final int length = s.length();
        final int n = length / 4;
        if (4 * n != length) {
            throw new IllegalArgumentException(f.z[1]);
        }
        int n2 = 0;
        int n3 = n;
        if (length != 0) {
            if (s.charAt(length - 1) == '=') {
                ++n2;
                --n3;
            }
            if (s.charAt(length - 2) == '=') {
                ++n2;
            }
        }
        final byte[] array2 = new byte[3 * n - n2];
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        while (true) {
            Label_0235: {
                if (!a) {
                    break Label_0235;
                }
                final int a2 = a(s.charAt(n4++), array);
                final int a3 = a(s.charAt(n4++), array);
                final int a4 = a(s.charAt(n4++), array);
                final int a5 = a(s.charAt(n4++), array);
                array2[n5++] = (byte)(a2 << 2 | a3 >> 4);
                array2[n5++] = (byte)(a3 << 4 | a4 >> 2);
                array2[n5++] = (byte)(a4 << 6 | a5);
                ++n6;
            }
            if (n6 >= n3) {
                if (n2 != 0) {
                    final int a6 = a(s.charAt(n4++), array);
                    final int a7 = a(s.charAt(n4++), array);
                    array2[n5++] = (byte)(a6 << 2 | a7 >> 4);
                    if (n2 == 1) {
                        array2[n5++] = (byte)(a7 << 4 | a(s.charAt(n4++), array) >> 2);
                    }
                }
                return array2;
            }
            continue;
        }
    }
    
    private static int a(final char c, final byte[] array) {
        final byte b = array[c];
        if (b < 0) {
            throw new IllegalArgumentException(f.z[2] + c);
        }
        return b;
    }
    
    static {
        f.z = new String[] { z(z("x(")), z(z("\u0016a>XR\"5 TR\"a$\u0011Q0f8\u0011^ 5-\u0011Q0y8XL)pl^Zes#DNk")), z(z("\fy T[$ylRT$g-RH gl")) };
        a = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        b = new char[] { '!', '\"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':', ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '?' };
        c = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
        d = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, -1, 62, 9, 10, 11, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 12, 13, 14, -1, 15, 63, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, 18, 19, 21, 20, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 22, 23, 24, 25 };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '<';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'E';
                    break;
                }
                case 1: {
                    c2 = '\u0015';
                    break;
                }
                case 2: {
                    c2 = 'L';
                    break;
                }
                case 3: {
                    c2 = '1';
                    break;
                }
                default: {
                    c2 = '<';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
