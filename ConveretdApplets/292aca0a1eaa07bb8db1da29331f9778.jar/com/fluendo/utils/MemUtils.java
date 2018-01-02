// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.utils;

public class MemUtils
{
    private static final char[] bytes;
    
    public static final int cmp(final byte[] mem1, final byte[] mem2, final int len) {
        int i = 0;
        while (i < len) {
            if (mem1[i] != mem2[i]) {
                if (mem1[i] < mem2[i]) {
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
    
    public static final void set(final byte[] mem, final int offset, final int val, int len) {
        len += offset;
        for (int i = offset; i < len; ++i) {
            mem[i] = (byte)val;
        }
    }
    
    public static final void set(final short[] mem, final int offset, final int val, int len) {
        len += offset;
        for (int i = offset; i < len; ++i) {
            mem[i] = (short)val;
        }
    }
    
    public static final void set(final int[] mem, final int offset, final int val, int len) {
        len += offset;
        for (int i = offset; i < len; ++i) {
            mem[i] = val;
        }
    }
    
    public static final void set(final Object[] mem, final int offset, final Object val, int len) {
        len += offset;
        for (int i = offset; i < len; ++i) {
            mem[i] = val;
        }
    }
    
    public static final boolean startsWith(final byte[] arr, final int offset, final int len, final byte[] pattern) {
        final int length = pattern.length;
        if (len < length) {
            return false;
        }
        int i;
        for (i = 0; i < length && arr[offset + i] == pattern[i]; ++i) {}
        return i == length;
    }
    
    public static final void dump(final byte[] mem, final int start, final int len) {
        final StringBuffer string = new StringBuffer(50);
        final StringBuffer chars = new StringBuffer(18);
        final String vis = new String(mem, start, len);
        int i;
        int j = i = 0;
        while (i < len) {
            int b = mem[i + start];
            if (b < 0) {
                b += 256;
            }
            if (b > 32 && b < 127) {
                chars.append(vis.charAt(i));
            }
            else {
                chars.append(".");
            }
            string.append(MemUtils.bytes[b / 16]);
            string.append(MemUtils.bytes[b % 16]);
            string.append(" ");
            ++j;
            ++i;
            if (j == 16 || i == len) {
                System.out.println("" + (i - j) + "  " + string.toString() + chars.toString());
                string.setLength(0);
                chars.setLength(0);
                j = 0;
            }
        }
    }
    
    static {
        bytes = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
}
