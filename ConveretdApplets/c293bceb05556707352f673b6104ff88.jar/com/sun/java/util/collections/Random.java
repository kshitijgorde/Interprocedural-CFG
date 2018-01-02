// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

import java.io.Serializable;

public class Random implements Serializable
{
    static final long serialVersionUID = 3905348978240129619L;
    private long seed;
    private static final long multiplier = 25214903917L;
    private static final long addend = 11L;
    private static final long mask;
    private static final int BITS_PER_BYTE = 8;
    private static final int BYTES_PER_INT = 4;
    private double nextNextGaussian;
    private boolean haveNextNextGaussian;
    
    public Random() {
        this(System.currentTimeMillis());
    }
    
    public Random(final long seed) {
        this.haveNextNextGaussian = false;
        this.setSeed(seed);
    }
    
    public synchronized void setSeed(final long n) {
        this.seed = ((n ^ 0x5DEECE66DL) & (1L << 48) - 1L);
        this.haveNextNextGaussian = false;
    }
    
    protected synchronized int next(final int n) {
        final long seed = this.seed * 25214903917L + 11L & (1L << 48) - 1L;
        this.seed = seed;
        return (int)(seed >>> 48 - n);
    }
    
    public void nextBytes(final byte[] array) {
        final int length = array.length;
        int n = 0;
        int n2 = 0;
    Block_1:
        while (true) {
            for (int i = 0; i < 4; ++i) {
                if (n == length) {
                    break Block_1;
                }
                n2 = ((i == 0) ? this.next(32) : (n2 >> 8));
                array[n++] = (byte)n2;
            }
        }
    }
    
    public int nextInt() {
        return this.next(32);
    }
    
    public int nextInt(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        int next;
        int n2;
        do {
            next = this.next(31);
            n2 = next % n;
        } while (next - n2 + (n - 1) < 0);
        return n2;
    }
    
    public long nextLong() {
        return (this.next(32) << 32) + this.next(32);
    }
    
    public boolean nextBoolean() {
        return this.next(1) != 0;
    }
    
    public float nextFloat() {
        return this.next(24) / 1.6777216E7f;
    }
    
    public double nextDouble() {
        return ((this.next(26) << 27) + this.next(27)) / (1L << 53);
    }
    
    public synchronized double nextGaussian() {
        if (this.haveNextNextGaussian) {
            this.haveNextNextGaussian = false;
            return this.nextNextGaussian;
        }
        double n;
        double n2;
        double n3;
        do {
            n2 = 2.0 * this.nextDouble() - 1.0;
            n3 = 2.0 * this.nextDouble() - 1.0;
            n = n2 * n2 + n3 * n3;
        } while (n >= 1.0);
        final double sqrt = Math.sqrt(-2.0 * Math.log(n) / n);
        this.nextNextGaussian = n3 * sqrt;
        this.haveNextNextGaussian = true;
        return n2 * sqrt;
    }
    
    static {
        mask = (1L << 48) - 1L;
    }
}
