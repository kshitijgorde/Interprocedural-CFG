// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.utils;

public class MemUtils
{
    private static final char[] bytes;
    
    public static final int cmp(final byte[] array, final byte[] array2, final int n) {
        int i = 0;
        while (i < n) {
            if (array[i] != array2[i]) {
                if (array[i] < array2[i]) {
                    return -i;
                }
                return i;
            }
            else {
                ++i;
            }
        }
        return 0;
    }
    
    public static final void set(final byte[] array, final int n, final int n2, int n3) {
        n3 += n;
        for (int i = n; i < n3; ++i) {
            array[i] = (byte)n2;
        }
    }
    
    public static final void set(final short[] array, final int n, final int n2, int n3) {
        n3 += n;
        for (int i = n; i < n3; ++i) {
            array[i] = (short)n2;
        }
    }
    
    public static final void set(final int[] array, final int n, final int n2, int n3) {
        n3 += n;
        for (int i = n; i < n3; ++i) {
            array[i] = n2;
        }
    }
    
    public static final void set(final Object[] array, final int n, final Object o, int n2) {
        n2 += n;
        for (int i = n; i < n2; ++i) {
            array[i] = o;
        }
    }
    
    public static final boolean startsWith(final byte[] array, final int n, final int n2, final byte[] array2) {
        final int length = array2.length;
        if (n2 < length) {
            return false;
        }
        int n3;
        for (n3 = 0; n3 < length && array[n + n3] == array2[n3]; ++n3) {}
        return n3 == length;
    }
    
    public static final void dump(final byte[] array, final int n, final int n2) {
        final StringBuffer sb = new StringBuffer(50);
        final StringBuffer sb2 = new StringBuffer(18);
        final String s = new String(array, n, n2);
        int i;
        int n3 = i = 0;
        while (i < n2) {
            int n4 = array[i + n];
            if (n4 < 0) {
                n4 += 256;
            }
            if (n4 > 32 && n4 < 127) {
                sb2.append(s.charAt(i));
            }
            else {
                sb2.append(".");
            }
            sb.append(MemUtils.bytes[n4 / 16]);
            sb.append(MemUtils.bytes[n4 % 16]);
            sb.append(" ");
            ++n3;
            ++i;
            if (n3 == 16 || i == n2) {
                System.out.println("" + (i - n3) + "  " + sb.toString() + sb2.toString());
                sb.setLength(0);
                sb2.setLength(0);
                n3 = 0;
            }
        }
    }
    
    static {
        bytes = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
}
