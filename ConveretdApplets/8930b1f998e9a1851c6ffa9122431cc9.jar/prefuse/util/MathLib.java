// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.util.Arrays;

public class MathLib
{
    public static final double TWO_PI = 6.283185307179586;
    public static final double LOG10;
    public static final double LOG2;
    
    public static double log2(final double n) {
        return Math.log(n) / MathLib.LOG2;
    }
    
    public static double log10(final double n) {
        return Math.log(n) / MathLib.LOG10;
    }
    
    public static double safeLog10(double n) {
        final boolean b = n < 0.0;
        if (b) {
            n = -n;
        }
        if (n < 10.0) {
            n += (10.0 - n) / 10.0;
        }
        n = Math.log(n) / MathLib.LOG10;
        return b ? (-n) : n;
    }
    
    public static double safeSqrt(final double n) {
        return (n < 0.0) ? (-Math.sqrt(-n)) : Math.sqrt(n);
    }
    
    public static double interp(final int n, final double n2, final double[] array) {
        switch (n) {
            case 0: {
                return linearInterp(n2, array[0], array[array.length - 1]);
            }
            case 1: {
                return logInterp(n2, array[0], array[array.length - 1]);
            }
            case 2: {
                return sqrtInterp(n2, array[0], array[array.length - 1]);
            }
            case 3: {
                return quantile(n2, array);
            }
            default: {
                throw new IllegalArgumentException("Unrecognized scale value: " + n);
            }
        }
    }
    
    public static double linearInterp(final double n, final double n2, final double n3) {
        final double n4 = n3 - n2;
        if (n4 == 0.0) {
            return 0.0;
        }
        return (n - n2) / n4;
    }
    
    public static double logInterp(final double n, final double n2, final double n3) {
        final double safeLog10 = safeLog10(n2);
        final double n4 = safeLog10(n3) - safeLog10;
        if (n4 == 0.0) {
            return 0.0;
        }
        return (safeLog10(n) - safeLog10) / n4;
    }
    
    public static double sqrtInterp(final double n, final double n2, final double n3) {
        final double safeSqrt = safeSqrt(n2);
        final double n4 = safeSqrt(n3) - safeSqrt;
        if (n4 == 0.0) {
            return 0.0;
        }
        return (safeSqrt(n) - safeSqrt) / n4;
    }
    
    public static double[] quantiles(final int n, double[] array) {
        array = array.clone();
        Arrays.sort(array);
        final double[] array2 = new double[n + 1];
        for (int i = 0; i <= n; ++i) {
            array2[i] = array[(array.length - 1) * i / n];
        }
        return array2;
    }
    
    public static double quantile(final double n, final double[] array) {
        int n2;
        int length;
        int n3;
        for (n2 = 1, length = array.length, n3 = length / 2; n2 < length && array[n3] != n; n3 = n2 + (length - n2) / 2) {
            if (array[n3] < n) {
                n2 = n3 + 1;
            }
            else {
                length = n3;
            }
        }
        return n3 / (array.length - 1);
    }
    
    static {
        LOG10 = Math.log(10.0);
        LOG2 = Math.log(2.0);
    }
}
