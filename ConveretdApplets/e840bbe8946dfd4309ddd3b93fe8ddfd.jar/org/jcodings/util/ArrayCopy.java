// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.util;

public class ArrayCopy
{
    public static byte[] int2byte(final int[] src) {
        final int srcLength = src.length;
        final byte[] dst = new byte[srcLength << 2];
        for (int i = 0; i < srcLength; ++i) {
            final int x = src[i];
            int j = i << 2;
            dst[j++] = (byte)(x >>> 0 & 0xFF);
            dst[j++] = (byte)(x >>> 8 & 0xFF);
            dst[j++] = (byte)(x >>> 16 & 0xFF);
            dst[j++] = (byte)(x >>> 24 & 0xFF);
        }
        return dst;
    }
    
    public static int[] byte2int(final byte[] src) {
        final int dstLength = src.length >>> 2;
        final int[] dst = new int[dstLength];
        for (int i = 0; i < dstLength; ++i) {
            int j = i << 2;
            int x = 0;
            x += (src[j++] & 0xFF) << 0;
            x += (src[j++] & 0xFF) << 8;
            x += (src[j++] & 0xFF) << 16;
            x += (src[j++] & 0xFF) << 24;
            dst[i] = x;
        }
        return dst;
    }
}
