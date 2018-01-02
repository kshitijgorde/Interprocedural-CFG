// 
// Decompiled by Procyon v0.5.30
// 

package hydrogenic;

public class SpecialFunctions
{
    public static final double ln2 = 0.69314718056;
    public static final double EulerG = 0.577215664902;
    public static final double hlfLn2Pi = 0.918938533205;
    public static final double[] b;
    private boolean invokedStandalone;
    
    public SpecialFunctions() {
        this.invokedStandalone = false;
    }
    
    static double laguerre(final int n, final int n2, final double n3) {
        double n4 = 0.0;
        if (n2 < 1) {
            return 1.0;
        }
        if (n2 < 2) {
            return 1.0 + n - n3;
        }
        if (n2 < 3) {
            return 1.0 + 0.5 * n * (3.0 + n) - n3 * (2.0 + n - 0.5 * n3);
        }
        double n5 = 1.0 + n - n3;
        double n6 = 1.0 + 0.5 * n * (3.0 + n) - n3 * (2.0 + n - 0.5 * n3);
        for (int i = 3; i <= n2; ++i) {
            final double n7 = i;
            n4 = ((2.0 * n7 + n - 1.0 - n3) * n6 - (n7 + n - 1.0) * n5) / n7;
            n5 = n6;
            n6 = n4;
        }
        return n4;
    }
    
    static double factorial(int n) {
        double n2 = 1.0;
        int i = 1;
        final int n3 = n;
        if (n <= 20) {
            while (i <= n3) {
                n2 *= n;
                --n;
                ++i;
            }
        }
        else {
            n2 = Math.pow(n, n) * Math.exp(-n) * Math.sqrt(6.283185307179586 * n);
        }
        return n2;
    }
    
    static double legendre(final int n, int n2, double n3) {
        double n4 = 0.0;
        if (n2 < 0) {
            n2 = -n2;
        }
        if (n2 > n) {
            return 0.0;
        }
        if (n == 0) {
            return 1.0;
        }
        final int n5 = n - n2;
        if (n3 < 0) {
            n3 = -n3;
            if (n5 > 2 * (n5 / 2)) {
                return -legendre(n, n2, n3);
            }
            return legendre(n, n2, n3);
        }
        else {
            final double n6 = n3 * n3;
            final double n7 = 1.0 - n6;
            final double sqrt = Math.sqrt(n7);
            if (n == 1) {
                if (n2 == 0) {
                    return n3;
                }
                if (n2 == 1) {
                    return sqrt;
                }
            }
            if (n == 2) {
                if (n2 == 0) {
                    return 1.5 * n6 - 0.5;
                }
                if (n2 == 1) {
                    return 3.0 * n3 * sqrt;
                }
                if (n2 == 2) {
                    return 3.0 * n7;
                }
            }
            final int n8 = n2 * n2;
            final double n9 = n2;
            if (n == n2) {
                return Math.exp(logGamma(2.0 * n9 + 1) - n9 * 0.69314718056 - logGamma(n9 + 1)) * Math.pow(sqrt, n9);
            }
            double n10 = 0.0;
            double n11 = Math.exp(0.5 * logGamma(2.0 * n9 + 1) - n9 * 0.69314718056 - logGamma(n9 + 1)) * Math.pow(sqrt, n9);
            double n12 = 2 * n2 + 1;
            double n13 = 0.0;
            double n14 = Math.sqrt(n12);
            for (int i = n2 + 1; i <= n; ++i) {
                n4 = (n12 * n3 * n11 - n13 * n10) / n14;
                n12 += 2.0;
                n13 = n14;
                n14 = Math.sqrt((i + 1) * (i + 1) - n8);
                n10 = n11;
                n11 = n4;
            }
            return Math.exp(0.5 * (logGamma(n + n2 + 1) - logGamma(n - n2 + 1))) * n4;
        }
    }
    
    static double logGamma(double n) {
        if (n > 10.0) {
            final double n2 = 1 / (n * n);
            double n3 = n;
            double n4 = (n - 0.5) * Math.log(n) - n + 0.918938533205;
            for (int i = 1; i <= 5; ++i) {
                n3 *= n2;
                n4 += SpecialFunctions.b[i] * n3;
            }
            return n4;
        }
        if (n > 0) {
            --n;
            final int n5 = (int)(11 - n);
            final double n6 = n + n5;
            final int n7 = (int)n;
            double n8 = 0.0;
            for (int j = 1; j <= n5 - 1; ++j) {
                n8 -= Math.log(n6 - j);
            }
            return n8 + logGamma(n6);
        }
        if (n == 0) {
            return 0.0;
        }
        return 0.0;
    }
    
    public static void main(final String[] array) {
        new SpecialFunctions().invokedStandalone = true;
    }
    
    static {
        b = new double[] { 0.0, 0.0833333333333, -0.00277777777778, 7.93650793651E-4, -5.95238095238E-4, 8.41750841751E-4 };
    }
}
