// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

import com.jcraft.jogg.Buffer;

public class Bitwise
{
    private static final int fp_bits = 32;
    private static final int fp_cuts_bits_bits = 4;
    
    static void readbuf(final Buffer buffer, final byte[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            array[i] = (byte)buffer.read(8);
        }
    }
    
    static int read32(final Buffer buffer) {
        return buffer.read(8) | buffer.read(8) << 8 | buffer.read(8) << 16 | buffer.read(8) << 24;
    }
    
    static int read32v(final Buffer buffer) {
        int n = buffer.read(4);
        if (n == 15) {
            final int read = buffer.read(1);
            n = buffer.read(buffer.read(5) + 1);
            if (read != 0) {
                n = -n;
            }
        }
        return n;
    }
    
    static long read64(final Buffer buffer) {
        return read32(buffer) | read32(buffer) << 32;
    }
    
    static int skipWarp(final Buffer buffer) {
        while (true) {
            final int read32v = read32v(buffer);
            if (read32v == 0) {
                return 0;
            }
            if (read32v < 0) {
                return -6;
            }
            buffer.adv(read32v);
        }
    }
    
    private static int[] readFixed(final Buffer buffer, int n) {
        final int read = buffer.read(4);
        final int read2 = buffer.read(4);
        final int n2 = 32 - read - read2;
        final int[] array = new int[n];
        int n3 = 0;
        while (n-- > 0) {
            int read3 = 0;
            if (read > 0) {
                read3 = buffer.read1();
            }
            int n4 = buffer.read(n2) << read2;
            if (read3 != 0) {
                n4 = -n4;
            }
            array[n3++] = n4;
        }
        return array;
    }
    
    private static double fixedToFloat(final int n) {
        return n / 65536.0;
    }
    
    static double[][] readFloats(final Buffer buffer, int n, int n2) {
        if (n * n2 == 0) {
            return null;
        }
        if (n2 > 1 && buffer.read1() != 0) {
            n *= n2;
            n2 = 1;
        }
        final double[][] array = new double[n2][];
        for (int i = 0; i < n2; ++i) {
            final int[] fixed = readFixed(buffer, n);
            array[i] = new double[n];
            for (int j = 0; j < n; ++j) {
                array[i][j] = fixedToFloat(fixed[j]);
            }
        }
        return array;
    }
}
