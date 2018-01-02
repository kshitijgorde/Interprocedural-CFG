// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.c;

public final class g
{
    private static byte[] a;
    private static String b;
    
    public static String a(final String s) {
        final byte[] bytes = s.getBytes();
        final int length = bytes.length;
        final byte[] array = bytes;
        final byte[] array2 = new byte[((length + 2) / 3 << 2) + length / 72];
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int n5 = length, i = 0; i < n5; ++i) {
            final byte b = array[i];
            switch (++n2) {
                case 1: {
                    array2[n++] = g.a[b >> 2 & 0x3F];
                    break;
                }
                case 2: {
                    array2[n++] = g.a[(n3 << 4 & 0x30) | (b >> 4 & 0xF)];
                    break;
                }
                case 3: {
                    array2[n++] = g.a[(n3 << 2 & 0x3C) | (b >> 6 & 0x3)];
                    array2[n++] = g.a[b & 0x3F];
                    n2 = 0;
                    break;
                }
            }
            n3 = b;
            if (++n4 >= 72) {
                array2[n++] = 10;
                n4 = 0;
            }
        }
        switch (n2) {
            case 1: {
                array2[n++] = g.a[n3 << 4 & 0x30];
                array2[n] = (array2[n++] = 61);
                break;
            }
            case 2: {
                array2[n++] = g.a[n3 << 2 & 0x3C];
                array2[n] = 61;
                break;
            }
        }
        return new String(array2);
    }
    
    public static byte[] b(final String s) {
        int n = 0;
        if (s.endsWith("=")) {
            ++n;
        }
        if (s.endsWith("==")) {
            ++n;
        }
        final byte[] array = new byte[(s.length() + 3) / 4 * 3 - n];
        int n2 = 0;
        try {
            int index;
            for (int n3 = 0; n3 < s.length() && (index = g.b.indexOf(s.charAt(n3))) != -1; ++n3) {
                switch (n3 % 4) {
                    case 0: {
                        array[n2] = (byte)(index << 2);
                        break;
                    }
                    case 1: {
                        final byte[] array2 = array;
                        final int n4 = n2++;
                        array2[n4] |= (byte)(index >> 4 & 0x3);
                        array[n2] = (byte)(index << 4);
                        break;
                    }
                    case 2: {
                        final byte[] array3 = array;
                        final int n5 = n2++;
                        array3[n5] |= (byte)(index >> 2 & 0xF);
                        array[n2] = (byte)(index << 6);
                        break;
                    }
                    case 3: {
                        final byte[] array4 = array;
                        final int n6 = n2++;
                        array4[n6] |= (byte)(index & 0x3F);
                        break;
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        return array;
    }
    
    static {
        g.b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        g.a = new byte[64];
        for (int i = 0; i < 64; ++i) {
            g.a[i] = (byte)g.b.charAt(i);
        }
    }
}
