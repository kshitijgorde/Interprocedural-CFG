// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.Arrays;
import java.math.BigInteger;

public class Random
{
    private static int N;
    private static int M;
    private static int MATRIX_A;
    private static int UMASK;
    private static int LMASK;
    private final int[] state;
    private int left;
    private static final BigInteger INTPAIR_CONST;
    private static final double LDEXP_CONST;
    
    private static int MIXBITS(final int u, final int v) {
        return (u & Random.UMASK) | (v & Random.LMASK);
    }
    
    private static int TWIST(final int u, final int v) {
        return MIXBITS(u, v) >>> 1 ^ (((v & 0x1) != 0x0) ? Random.MATRIX_A : 0);
    }
    
    public static void main(final String[] args) {
        final Random mt = new Random(1);
        for (int idx = 0; idx < 1; ++idx) {
            System.err.println(mt.genrandReal2());
        }
    }
    
    public Random(final int s) {
        this.state = new int[Random.N];
        this.left = 1;
        this.initGenrand(s);
    }
    
    public Random(final int[] initKey) {
        this.state = new int[Random.N];
        this.left = 1;
        this.initByArray(initKey);
    }
    
    public Random(final Random orig) {
        this.state = new int[Random.N];
        this.left = 1;
        System.arraycopy(orig.state, 0, this.state, 0, this.state.length);
        this.left = orig.left;
    }
    
    public Random(final int[] state, final int left) {
        this.state = new int[Random.N];
        this.left = 1;
        if (state.length != this.state.length) {
            throw new IllegalStateException("wrong state length: " + state.length);
        }
        System.arraycopy(state, 0, this.state, 0, this.state.length);
        this.left = left;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Random)) {
            return false;
        }
        final Random rhs = (Random)obj;
        return this.left == rhs.left && Arrays.equals(this.state, rhs.state);
    }
    
    public int hashCode() {
        return (629 + this.left) * 37 + this.state.hashCode();
    }
    
    private void initGenrand(final int s) {
        this.state[0] = s;
        for (int j = 1; j < Random.N; ++j) {
            this.state[j] = 1812433253 * (this.state[j - 1] ^ this.state[j - 1] >>> 30) + j;
        }
        this.left = 1;
    }
    
    private void initByArray(final int[] initKey) {
        this.initGenrand(19650218);
        final int len = initKey.length;
        int i = 1;
        int j = 0;
        for (int k = (Random.N > len) ? Random.N : len; k > 0; --k) {
            this.state[i] = (this.state[i] ^ (this.state[i - 1] ^ this.state[i - 1] >>> 30) * 1664525) + initKey[j] + j;
            ++i;
            ++j;
            if (i >= Random.N) {
                this.state[0] = this.state[Random.N - 1];
                i = 1;
            }
            if (j >= len) {
                j = 0;
            }
        }
        for (int k = Random.N - 1; k > 0; --k) {
            this.state[i] = (this.state[i] ^ (this.state[i - 1] ^ this.state[i - 1] >>> 30) * 1566083941) - i;
            if (++i >= Random.N) {
                this.state[0] = this.state[Random.N - 1];
                i = 1;
            }
        }
        this.state[0] = Integer.MIN_VALUE;
    }
    
    private void nextState() {
        int p = 0;
        this.left = Random.N;
        int j = Random.N - Random.M + 1;
        while (--j > 0) {
            this.state[p] = (this.state[p + Random.M] ^ TWIST(this.state[p + 0], this.state[p + 1]));
            ++p;
        }
        j = Random.M;
        while (--j > 0) {
            this.state[p] = (this.state[p + Random.M - Random.N] ^ TWIST(this.state[p + 0], this.state[p + 1]));
            ++p;
        }
        this.state[p] = (this.state[p + Random.M - Random.N] ^ TWIST(this.state[p + 0], this.state[0]));
    }
    
    public int genrandInt32() {
        final int left = this.left - 1;
        this.left = left;
        if (left <= 0) {
            this.nextState();
        }
        int y = this.state[Random.N - this.left];
        y ^= y >>> 11;
        y ^= (int)(y << 7 & 0x9D2C5680L);
        y ^= (int)(y << 15 & 0xEFC60000L);
        y ^= y >>> 18;
        return y;
    }
    
    public double genrandReal() {
        final int a = this.genrandInt32() >>> 5;
        final int b = this.genrandInt32() >>> 6;
        return (a * 6.7108864E7 + b) * 1.1102230246251565E-16;
    }
    
    public double genrandReal2() {
        final int a = this.genrandInt32();
        final int b = this.genrandInt32();
        return this.intPairToRealInclusive(a, b);
    }
    
    private double intPairToRealInclusive(final int a, final int b) {
        final BigInteger c = BigInteger.valueOf(a & 0xFFFFFFFFL);
        final BigInteger d = BigInteger.valueOf(b & 0xFFFFFFFFL);
        return c.shiftLeft(32).or(d).multiply(Random.INTPAIR_CONST).shiftRight(64).doubleValue() * Random.LDEXP_CONST;
    }
    
    public int[] getState() {
        return this.state;
    }
    
    public int getLeft() {
        return this.left;
    }
    
    static {
        Random.N = 624;
        Random.M = 397;
        Random.MATRIX_A = -1727483681;
        Random.UMASK = Integer.MIN_VALUE;
        Random.LMASK = Integer.MAX_VALUE;
        INTPAIR_CONST = BigInteger.valueOf(9007199254740993L);
        LDEXP_CONST = Math.pow(2.0, -53.0);
    }
}
