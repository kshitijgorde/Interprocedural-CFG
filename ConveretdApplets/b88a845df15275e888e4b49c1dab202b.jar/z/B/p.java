// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.Serializable;

public class p implements Comparable, Serializable
{
    public static final int D = 1;
    public static final int B = 2;
    public static final int I = 3;
    public static final int J = 4;
    public static final int M = 5;
    public static final int C = 6;
    public static final int E = 7;
    public static final int O = 8;
    public static final int G = 9;
    public static final int N = 10;
    public static final int H = 11;
    public static final int A = 12;
    int P;
    int L;
    int K;
    Object F;
    
    p() {
    }
    
    public p(final int p4, final int l, final int k, final Object f) {
        this.P = p4;
        this.L = l;
        this.K = k;
        this.F = f;
    }
    
    public int K() {
        return this.P;
    }
    
    public int H() {
        return this.L;
    }
    
    public int J() {
        return this.K;
    }
    
    public byte[] A() {
        return (byte[])this.F;
    }
    
    public char[] L() {
        return (char[])this.F;
    }
    
    public short[] B() {
        return (short[])this.F;
    }
    
    public int[] E() {
        return (int[])this.F;
    }
    
    public long[] F() {
        return (long[])this.F;
    }
    
    public float[] C() {
        return (float[])this.F;
    }
    
    public double[] G() {
        return (double[])this.F;
    }
    
    public int[][] I() {
        return (int[][])this.F;
    }
    
    public long[][] D() {
        return (long[][])this.F;
    }
    
    public int E(final int n) {
        switch (this.L) {
            case 1:
            case 7: {
                return ((byte[])this.F)[n] & 0xFF;
            }
            case 6: {
                return ((byte[])this.F)[n];
            }
            case 3: {
                return ((char[])this.F)[n] & '\uffff';
            }
            case 8: {
                return ((short[])this.F)[n];
            }
            case 9: {
                return ((int[])this.F)[n];
            }
            default: {
                throw new ClassCastException();
            }
        }
    }
    
    public long D(final int n) {
        switch (this.L) {
            case 1:
            case 7: {
                return ((byte[])this.F)[n] & 0xFF;
            }
            case 6: {
                return ((byte[])this.F)[n];
            }
            case 3: {
                return ((char[])this.F)[n] & '\uffff';
            }
            case 8: {
                return ((short[])this.F)[n];
            }
            case 9: {
                return ((int[])this.F)[n];
            }
            case 4: {
                return ((long[])this.F)[n];
            }
            default: {
                throw new ClassCastException();
            }
        }
    }
    
    public float B(final int n) {
        switch (this.L) {
            case 1: {
                return ((byte[])this.F)[n] & 0xFF;
            }
            case 6: {
                return ((byte[])this.F)[n];
            }
            case 3: {
                return ((char[])this.F)[n] & '\uffff';
            }
            case 8: {
                return ((short[])this.F)[n];
            }
            case 9: {
                return ((int[])this.F)[n];
            }
            case 4: {
                return ((long[])this.F)[n];
            }
            case 11: {
                return ((float[])this.F)[n];
            }
            case 12: {
                return (float)((double[])this.F)[n];
            }
            case 10: {
                final int[] f = this.F(n);
                return f[0] / f[1];
            }
            case 5: {
                final long[] a = this.A(n);
                return a[0] / a[1];
            }
            default: {
                throw new ClassCastException();
            }
        }
    }
    
    public double G(final int n) {
        switch (this.L) {
            case 1: {
                return ((byte[])this.F)[n] & 0xFF;
            }
            case 6: {
                return ((byte[])this.F)[n];
            }
            case 3: {
                return ((char[])this.F)[n] & '\uffff';
            }
            case 8: {
                return ((short[])this.F)[n];
            }
            case 9: {
                return ((int[])this.F)[n];
            }
            case 4: {
                return ((long[])this.F)[n];
            }
            case 11: {
                return ((float[])this.F)[n];
            }
            case 12: {
                return ((double[])this.F)[n];
            }
            case 10: {
                final int[] f = this.F(n);
                return f[0] / f[1];
            }
            case 5: {
                final long[] a = this.A(n);
                return a[0] / a[1];
            }
            default: {
                throw new ClassCastException();
            }
        }
    }
    
    public String C(final int n) {
        return ((String[])this.F)[n];
    }
    
    public int[] F(final int n) {
        return ((int[][])this.F)[n];
    }
    
    public long[] A(final int n) {
        return ((long[][])this.F)[n];
    }
    
    public int compareTo(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        final int k = ((p)o).K();
        if (this.P < k) {
            return -1;
        }
        if (this.P > k) {
            return 1;
        }
        return 0;
    }
}
