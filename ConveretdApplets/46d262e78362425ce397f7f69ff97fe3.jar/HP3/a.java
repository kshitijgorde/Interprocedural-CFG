// 
// Decompiled by Procyon v0.5.30
// 

package HP3;

import java.applet.Applet;

public class a extends Applet
{
    private static int do;
    private static byte[] if;
    private static int[] a;
    
    public String a(final String s, final String s2) {
        final int[] array = new int[8];
        byte b = 0;
        int n = 0;
        int n2 = 255;
        final byte[] bytes = s.getBytes();
        final byte[] array2 = new byte[65536];
        for (int i = 0; i < bytes.length; ++i) {
            b ^= bytes[i];
        }
        final int length = s2.length();
        final int n3 = 5 - length % 5;
        final byte[] array3 = new byte[n3 + length];
        for (int j = 0; j < length; ++j) {
            array3[j] = (byte)s2.charAt(j);
        }
        if (n3 < 5) {
            for (int k = length; k < n3 + length; ++k) {
                array3[k] = (byte)n2;
                n2 -= 5;
            }
        }
        if (HP3.a.do == 1) {
            for (int l = 0; l < length; ++l) {
                array3[l] ^= b;
            }
        }
        else {
            int n4 = 0;
            for (int n5 = 0; n5 < length; ++n5) {
                array3[n5] ^= bytes[n4++];
                if (n4 >= bytes.length) {
                    n4 = 0;
                }
            }
        }
        for (int n6 = 0; n6 < length; n6 += 5, n += 8) {
            int n7 = ((char)array3[n6] & '\u00ff') + (char)(array3[n6 + 1] & 0xFF) * '\u0100' + (char)(array3[n6 + 2] & 0xFF) * 65536 + (char)(array3[n6 + 3] & 0xFF) * 16777216;
            for (int n8 = 0; n8 <= 5; ++n8) {
                array[n8] = (n7 & 0x1F);
                n7 >>= 5;
            }
            final char c = (char)((char)(array3[n6 + 3] & 0xFF) + (char)(array3[n6 + 4] & 0xFF) * '\u0100' >> 6);
            array[6] = (c & '\u001f');
            array[7] = (c >> 5 & '\u001f');
            for (int n9 = 0; n9 <= 7; ++n9) {
                array2[n + n9] = HP3.a.if[array[n9]];
            }
        }
        return new String(array2, 0, n);
    }
    
    public String if(final String s, final String s2) {
        final int[] array = new int[8];
        byte b = 0;
        final byte[] array2 = new byte[s2.length()];
        final byte[] bytes = s.getBytes();
        final byte[] array3 = new byte[65536];
        final byte[] bytes2 = s2.getBytes();
        final int length = s2.length();
        int i = 0;
        for (int j = 0; j < 8; ++j) {
            array[j] = 0;
        }
        int n = 0;
        while (i < length) {
            for (int k = 0; k <= 7; ++k) {
                array[k] = HP3.a.a[bytes2[i]];
                if (array[k] == 255) {
                    return "";
                }
                ++i;
            }
            final int n2 = array[1] << 5 | array[2] << 10 | array[3] << 15 | array[4] << 20 | array[5] << 25 | array[0];
            array3[n] = (byte)(n2 & 0xFF);
            ++n;
            final int n3 = n2 / 256;
            array3[n] = (byte)(n3 & 0xFF);
            ++n;
            final int n4 = n3 / 256;
            array3[n] = (byte)(n4 & 0xFF);
            final int n5 = n4 / 256 | (array[6] << 6 | array[7] << 11);
            ++n;
            array3[n] = (byte)(n5 & 0xFF);
            ++n;
            array3[n] = (byte)(n5 / 256 & 0xFF);
            ++n;
        }
        final int n6 = n;
        for (int l = 0; l < bytes.length; ++l) {
            b ^= bytes[l];
        }
        if (HP3.a.do == 1) {
            for (int n7 = 0; n7 < n6; ++n7) {
                array3[n7] ^= b;
            }
        }
        else {
            int n8 = 0;
            for (int n9 = 0; n9 < n6; ++n9) {
                array3[n9] ^= bytes[n8++];
                if (n8 >= bytes.length) {
                    n8 = 0;
                }
            }
        }
        int n10;
        for (n10 = n6 - 1; n10 >= 0 && (array3[n10] >= 128 || array3[n10] <= 0); --n10) {
            array3[n10] = 0;
        }
        return new String(array3, 0, n10 + 1);
    }
    
    static {
        HP3.a.do = 0;
        HP3.a.if = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 119, 106, 107, 120, 109, 110, 121, 112, 113, 114, 122, 116, 117, 118 };
        HP3.a.a = new int[] { 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 255, 255, 255, 255, 255, 255, 255, 10, 11, 12, 13, 14, 15, 16, 17, 255, 19, 20, 255, 22, 23, 255, 25, 26, 27, 255, 29, 30, 31, 18, 21, 24, 28, 255, 255, 255, 255, 255, 255, 10, 11, 12, 13, 14, 15, 16, 17, 255, 19, 20, 255, 22, 23, 255, 25, 26, 27, 255, 29, 30, 31, 18, 21, 24, 28, 255, 255, 255, 255, 255 };
    }
}
