// 
// Decompiled by Procyon v0.5.30
// 

package VisualNumerics.math;

public class Statistics
{
    static final double log_min_value;
    private static final double con1 = 0.63661977236758;
    private static final double hpi = 1.5707963267948966;
    private static final double eps_normalInv = 1.0E-6;
    private static final double sqrt2 = 1.4142135623730951;
    private static final double e0_normalInv = -0.05668422;
    private static final double e1_normalInv = 0.3937021;
    private static final double e2_normalInv = -0.3166501;
    private static final double e3_normalInv = 0.06208963;
    private static final double g0_normalInv = 1.851159E-4;
    private static final double g1_normalInv = -0.002028152;
    private static final double g2_normalInv = -0.1498384;
    private static final double g3_normalInv = 0.01078639;
    private static final double h0_normalInv = 0.09952975;
    private static final double h1_normalInv = 0.5211733;
    private static final double h2_normalInv = -0.06888301;
    private static final double a1_normalInv = -0.5751703;
    private static final double a2_normalInv = -1.896513;
    private static final double a3_normalInv = -0.05496261;
    private static final double b0_normalInv = -0.113773;
    private static final double b1_normalInv = -3.293474;
    private static final double b2_normalInv = -2.374996;
    private static final double b3_normalInv = -1.187515;
    private static final double c0_normalInv = -0.1146666;
    private static final double c1_normalInv = -0.1314774;
    private static final double c2_normalInv = -0.2368201;
    private static final double c3_normalInv = 0.05073975;
    private static final double d0_normalInv = -44.27977;
    private static final double d1_normalInv = 21.98546;
    private static final double d2_normalInv = -7.586103;
    private static final double f0_normalInv = -6.266786;
    private static final double f1_normalInv = 4.666263;
    private static final double f2_normalInv = -2.962883;
    static final double log_epsilon_small;
    private static final double[] alnrcs;
    
    static {
        log_min_value = Math.log(Double.MIN_VALUE);
        log_epsilon_small = Math.log(1.11022302462515E-16);
        alnrcs = new double[] { 1.037869356274377, -0.13364301504908918, 0.019408249135520562, -0.0030107551127535777, 4.869461479715485E-4, -8.105488189317536E-5, 1.3778847799559525E-5, -2.380221089435897E-6, 4.1640416213865184E-7, -7.359582837807599E-8, 1.3117611876241675E-8, -2.3546709317742423E-9, 4.2522773276035E-10, -7.71908941348408E-11, 1.407574648135907E-11, -2.5769072058024682E-12, 4.734240666629442E-13, -8.724901267474264E-14, 1.612461490274055E-14, -2.9875652015665774E-15, 5.548070120908289E-16, -1.0324619158271569E-16, 1.9250239203049852E-17, -3.595507346526515E-18 };
    }
    
    public static double FCdf(final double n, final double n2, final double n3) {
        if (n2 <= 0.0) {
            return Double.NaN;
        }
        if (n3 <= 0.0) {
            return Double.NaN;
        }
        if (n <= 0.0) {
            return 0.0;
        }
        return 1.0 - beta_incomplete(n3 / (n3 + n2 * n), 0.5 * n3, 0.5 * n2);
    }
    
    public static double average(final double[] array) {
        double n = 0.0;
        if (array.length == 0) {
            return Double.NaN;
        }
        for (int i = 0; i < array.length; ++i) {
            n += array[i];
        }
        return n / array.length;
    }
    
    static double beta_incomplete(final double n, final double n2, final double n3) {
        final double n4 = 0.95 * Statistics.log_min_value;
        final double n5 = Double.NaN;
        if (n < 0.0 || n > 1.0 || n2 <= 0.0 || n3 <= 0.0) {
            return n5;
        }
        double n6 = n;
        double n7 = n2;
        double n8 = n3;
        if ((n8 > n7 || n > 0.8) && n > 0.2) {
            n6 = 1.0 - n6;
            n7 = n3;
            n8 = n2;
        }
        if ((n7 + n8) * n6 / (n7 + 1.0) < 1.11022302462515E-16) {
            double exp = 0.0;
            final double n9 = n7 * Math.log(Math.max(n6, Double.MIN_VALUE)) - Math.log(n7) - log_beta(n7, n8);
            if (n9 > n4 && n6 != 0.0) {
                exp = Math.exp(n9);
            }
            if (n6 != n || n7 != n2) {
                exp = 1.0 - exp;
            }
            return exp;
        }
        double n10 = n8 - (int)n8;
        if (n10 == 0.0) {
            n10 = 1.0;
        }
        final double n11 = n7 * Math.log(n6) - log_beta(n10, n7) - Math.log(n7);
        double exp2 = 0.0;
        if (n11 >= n4) {
            exp2 = Math.exp(n11);
            double n12 = exp2 * n7;
            if (n10 != 1.0) {
                for (int n13 = (int)Math.max(Statistics.log_epsilon_small / Math.log(n6), 4.0), i = 1; i <= n13; ++i) {
                    n12 = n12 * (i - n10) * n6 / i;
                    exp2 += n12 / (n7 + i);
                }
            }
        }
        if (n8 <= 1.0) {
            if (n6 != n || n7 != n2) {
                exp2 = 1.0 - exp2;
            }
            return Math.max(Math.min(exp2, 1.0), 0.0);
        }
        final double n14 = n7 * Math.log(n6) + n8 * Math.log(1.0 - n6) - log_beta(n7, n8) - Math.log(n8);
        int n15 = (int)Math.max(n14 / n4, 0.0);
        double exp3 = Math.exp(n14 - n15 * n4);
        final double n16 = 1.0 / (1.0 - n6);
        final double n17 = n8 * n16 / (n7 + n8 - 1.0);
        double n18 = 0.0;
        int n19 = (int)n8;
        if (n8 == n19) {
            --n19;
        }
        for (int n20 = 1; n20 <= n19 && (n17 > 1.0 || exp3 / 1.11022302462515E-16 > n18); ++n20) {
            exp3 = (n8 - (n20 - 1)) * n16 * exp3 / (n7 + n8 - n20);
            if (exp3 > 1.0) {
                --n15;
                exp3 *= Double.MIN_VALUE;
            }
            if (n15 == 0) {
                n18 += exp3;
            }
        }
        double n21 = exp2 + n18;
        if (n6 != n || n7 != n2) {
            n21 = 1.0 - n21;
        }
        return Math.max(Math.min(n21, 1.0), 0.0);
    }
    
    private static double inverseBeta(final double n, final double n2, final double n3) {
        final double n4 = Double.NaN;
        if (n2 <= 0.0 || n3 <= 0.0 || n <= 0.0 || n >= 1.0) {
            return n4;
        }
        final double min = Math.min(n2, n3);
        if (min > 1.0 && 10.0 * min > Math.max(n2, n3)) {
            double n5 = 0.0;
            double n6 = 1.0;
            double n7 = -n;
            for (int i = 0; i < 30; ++i) {
                final double n8 = (n5 + n6) * 0.5;
                final double n9 = beta_incomplete(n8, n2, n3) - n;
                if (n9 * n7 <= 0.0) {
                    n6 = n8;
                }
                else {
                    n5 = n8;
                    n7 = n9;
                }
                if (n6 - n5 <= 2.2204460492503E-16 && Math.abs(n9) <= 2.2204460492503E-16) {
                    return n8;
                }
            }
        }
        double n10;
        double n11;
        double n12;
        if (n <= 0.5) {
            n10 = n2;
            n11 = n3;
            n12 = Math.log(n);
        }
        else {
            n12 = Math.log(1.0 - n);
            n10 = n3;
            n11 = n2;
        }
        double n13 = n10 / (n10 + n11);
        final double n14 = Sfun.logGamma(n10 + n11) - Sfun.logGamma(n10) - Sfun.logGamma(n11) + (-(n10 + n11) * Math.log(n10 + n11) + (n10 - 0.5) * Math.log(n10) + (n11 - 0.5) * Math.log(n11)) + (0.5 * Math.log(n11 / n10) + n10 * Math.log(1.0 + n11 / n10) + n11 * Math.log(1.0 + n10 / n11));
        for (int j = 1; j <= 100; ++j) {
            final double log = Math.log(15.0 + n10 + n11);
            final double n15 = 0.7 * log * log + Math.max(n13 * (n10 + n11) - n10, 0.0);
            final double n16 = n10 + n15 + n15;
            final double n17 = (int)n15 + 1;
            final double n18 = 1.0 - (n10 + n11) * n13 / n16;
            double n19 = 2.0 / (n18 + Math.sqrt(n18 * n18 - 4.0 * n15 * (n15 - n11) * n13 / (n16 * n16)));
            for (double n20 = n17 - 1.0; n20 >= 0.5; --n20) {
                final double n21 = n10 + n20 + n20;
                final double n22 = (n21 - 2.0) * (n21 - 1.0 - n20 * (n20 - n11) * n13 * n19 / n21);
                final double n23 = n10 + n20 - 1.0;
                n19 = 1.0 / (1.0 - n23 * (n23 + n11) * n13 / n22);
            }
            final double n24 = n19;
            final double log2 = Math.log(n13);
            if (log2 <= Statistics.log_min_value + 10.0) {
                double n25;
                if (n <= 0.5) {
                    n25 = 0.0;
                }
                else {
                    n25 = 1.0;
                }
                return n25;
            }
            final double min2 = Math.min(Math.max((n12 - (n14 + n10 * log2 + n11 * Math.log(1.0 - n13) + Math.log(n24))) * (1.0 - n13) * n24 / n10, -0.99), 0.5 / n13 - 0.5);
            n13 *= 1.0 + min2;
            if (Math.abs(min2) < 2.2204460492503E-16) {
                double n26;
                if (n <= 0.5) {
                    n26 = n13;
                }
                else {
                    n26 = 1.0 - n13;
                }
                return n26;
            }
        }
        double n27;
        if (n <= 0.5) {
            n27 = n13;
        }
        else {
            n27 = 1.0 - n13;
        }
        return n27;
    }
    
    public static double inverseFCdf(final double n, final double n2, final double n3) {
        final double n4 = Double.NaN;
        if (n <= 0.0 || n >= 1.0) {
            return n4;
        }
        if (n2 <= 0.0 || n3 <= 0.0) {
            return n4;
        }
        final double n5 = 0.9999999999999998;
        final double n6 = 0.5 * n2;
        final double n7 = 0.5 * n3;
        double n8;
        if (n <= 0.5) {
            final double inverseBeta = inverseBeta(n, n6, n7);
            if (inverseBeta >= n5) {
                return Double.POSITIVE_INFINITY;
            }
            n8 = n3 * inverseBeta / (n2 * (1.0 - inverseBeta));
        }
        else {
            final double inverseBeta2 = inverseBeta(1.0 - n, n7, n6);
            if (inverseBeta2 == 0.0) {
                return Double.POSITIVE_INFINITY;
            }
            n8 = (1.0 / inverseBeta2 - 1.0) * n3 / n2;
        }
        return n8;
    }
    
    public static double inverseNormalCdf(final double n) {
        if (n <= 0.0 || n >= 1.0) {
            return Double.NaN;
        }
        double n6;
        if (n > 1.0E-6 && n < 0.999997) {
            final double abs = Math.abs(1.0 - (n + n));
            double n4;
            if (abs > 0.85) {
                final double n2 = 1.0 - abs;
                final double sqrt = Math.sqrt(-Math.log(n2 + n2 * abs));
                if (sqrt >= 2.5) {
                    if (sqrt >= 4.0) {
                        final double n3 = 1.0 / sqrt;
                        n4 = sqrt + sqrt * (1.851159E-4 + ((0.01078639 * n3 - 0.1498384) * n3 - 0.002028152) * n3 / (((n3 - 0.06888301) * n3 + 0.5211733) * n3 + 0.09952975));
                    }
                    else {
                        n4 = sqrt + sqrt * (-0.05668422 + ((0.06208963 * sqrt - 0.3166501) * sqrt + 0.3937021) * sqrt / (((sqrt - 2.962883) * sqrt + 4.666263) * sqrt - 6.266786));
                    }
                }
                else {
                    n4 = sqrt + sqrt * (-0.1146666 + ((0.05073975 * sqrt - 0.2368201) * sqrt - 0.1314774) * sqrt / (((sqrt - 7.586103) * sqrt + 21.98546) * sqrt - 44.27977));
                }
            }
            else {
                final double n5 = abs * abs;
                n4 = abs + abs * (-0.113773 + -0.5751703 * n5 / (-3.293474 + n5 + -1.896513 / (-2.374996 + n5 + -0.05496261 / (-1.187515 + n5))));
            }
            if (n >= 0.5) {
                n6 = 1.4142135623730951 * n4;
            }
            else {
                n6 = -1.4142135623730951 * n4;
            }
        }
        else {
            double n7;
            if (n >= 0.5) {
                n7 = 2.0 * (1.0 - n);
            }
            else {
                n7 = n + n;
            }
            final double sqrt2 = Math.sqrt(-Math.log(n7 + (n7 - n7 * n7)));
            final double n8 = 1.0 / sqrt2;
            final double n9 = sqrt2 + sqrt2 * (1.851159E-4 + ((0.01078639 * n8 - 0.1498384) * n8 - 0.002028152) * n8 / (((n8 - 0.06888301) * n8 + 0.5211733) * n8 + 0.09952975));
            if (n >= 0.5) {
                n6 = 1.4142135623730951 * n9;
            }
            else {
                n6 = -1.4142135623730951 * n9;
            }
        }
        return n6;
    }
    
    public static double inverseTCdf(final double n, final double n2) {
        final double n3 = Double.NaN;
        if (n2 < 1.0) {
            return n3;
        }
        if (n <= 0.0 || n >= 1.0) {
            return n3;
        }
        final double n4 = 2.2204460492503E-16;
        if (n > 0.5 - n4 && n < 0.5 + n4) {
            return 0.0;
        }
        double n5;
        if (n < 0.5) {
            n5 = 2.0 * n;
        }
        else {
            n5 = 2.0 * (1.0 - n);
        }
        Math.log(Double.MAX_VALUE);
        double n6;
        if (Math.abs(n2 - 2.0) <= 2.2204460492503E-16) {
            n6 = Math.sqrt(2.0 / (n5 * (2.0 - n5)) - 2.0);
        }
        else if (Math.abs(n2 - 1.0) <= 2.2204460492503E-16) {
            final double n7 = n5 * 1.5707963267948966;
            n6 = Math.cos(n7) / Math.sin(n7);
        }
        else if (n2 <= 2.0) {
            final double n8 = 1.0;
            final double n9 = 0.5 * n8;
            final double n10 = 0.5 * n2;
            double n11;
            if (n < 0.5) {
                n11 = 1.0 - 2.0 * n;
            }
            else {
                n11 = -(1.0 - 2.0 * n);
            }
            final double inverseBeta = inverseBeta(1.0 - n11, n10, n9);
            if (inverseBeta == 0.0) {
                return Double.POSITIVE_INFINITY;
            }
            n6 = Math.sqrt((1.0 / inverseBeta - 1.0) * n2 / n8);
        }
        else {
            final double n12 = 1.0 / (n2 - 0.5);
            final double n13 = 48.0 / (n12 * n12);
            double n14 = ((20700.0 * n12 / n13 - 98.0) * n12 - 16.0) * n12 + 96.36;
            final double n15 = ((94.5 / (n13 + n14) - 3.0) / n13 + 1.0) * Math.sqrt(n12 * 1.5707963267948966) * n2;
            final double pow = Math.pow(n15 * n5, 2.0 / n2);
            if (pow <= n12 + 0.05) {
                n6 = Math.sqrt(n2 * (((1.0 / (((n2 + 6.0) / (n2 * pow) - 0.089 * n15 - 0.822) * (n2 + 2.0) * 3.0) + 0.5 / (n2 + 4.0)) * pow - 1.0) * (n2 + 1.0) / (n2 + 2.0) + 1.0 / pow));
            }
            else {
                final double inverseNormalCdf = inverseNormalCdf(0.5 * n5);
                final double n16 = inverseNormalCdf * inverseNormalCdf;
                if (n2 < 5.0) {
                    n14 += 0.3 * (n2 - 4.5) * (inverseNormalCdf + 0.6);
                }
                final double n17 = (((((0.4 * n16 + 6.3) * n16 + 36.0) * n16 + 94.5) / (n14 + ((((0.05 * n15 * inverseNormalCdf - 5.0) * inverseNormalCdf - 7.0) * inverseNormalCdf - 2.0) * inverseNormalCdf + n13)) - n16 - 3.0) / n13 + 1.0) * inverseNormalCdf;
                final double n19;
                double n18 = n19 = n17 * (n12 * n17);
                if (n19 <= 0.002) {
                    n18 += 0.5 * n18 * n18;
                }
                if (n19 > 0.002) {
                    n18 = Math.exp(n18) - 1.0;
                }
                n6 = Math.sqrt(n2 * n18);
            }
        }
        if (n < 0.5) {
            n6 = -n6;
        }
        return n6;
    }
    
    public static double kurtosis(final double[] array) {
        final int length = array.length;
        double n = 0.0;
        for (int i = 0; i < length; ++i) {
            n += array[i];
        }
        final double n2 = n / length;
        double n3 = 0.0;
        for (int j = 0; j < length; ++j) {
            n3 += (array[j] - n2) * (array[j] - n2);
        }
        final double n4 = n3 / length;
        double n5 = 0.0;
        for (int k = 0; k < length; ++k) {
            final double n6 = array[k] - n2;
            n5 += n6 * n6 * n6 * n6;
        }
        return n5 / (length * n4 * n4) - 3.0;
    }
    
    public static double[] linearFit(final double[] array, final double[] array2) {
        if (array.length != array2.length) {
            throw new IllegalArgumentException("number of x and y values are not equal");
        }
        double n = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 0.0;
        for (int i = 0; i < array.length; ++i) {
            n += array[i];
            n2 += array2[i];
            n3 += array[i] * array2[i];
            n4 += array[i] * array[i];
        }
        final double[] array3 = new double[2];
        final double n5 = array.length * n4 - n * n;
        if (n5 == 0.0) {
            array3[0] = n2 / array.length;
            array3[1] = 0.0;
        }
        else {
            array3[0] = (n2 * n4 - n * n3) / n5;
            array3[1] = (array.length * n3 - n * n2) / n5;
        }
        return array3;
    }
    
    private static double logRelative(final double n) {
        if (n <= -1.0) {
            return Double.NaN;
        }
        return (Math.abs(n) <= 0.375) ? (n * (1.0 - n * Sfun.csevl(n / 0.375, Statistics.alnrcs))) : Math.log(1.0 + n);
    }
    
    private static double log_beta(final double n, final double n2) {
        final double n3 = 0.9189385332046728;
        final double n4 = Double.NaN;
        final double min = Math.min(n, n2);
        final double max = Math.max(n, n2);
        if (min <= 0.0) {
            return n4;
        }
        double log;
        if (min >= 10.0) {
            log = -0.5 * Math.log(max) + n3 + (Sfun.logGammaCorrection(min) + Sfun.logGammaCorrection(max) - Sfun.logGammaCorrection(min + max)) + (min - 0.5) * Math.log(min / (min + max)) + max * logRelative(-min / (min + max));
        }
        else if (max >= 10.0) {
            log = Sfun.logGamma(min) + (Sfun.logGammaCorrection(max) - Sfun.logGammaCorrection(min + max)) + min - min * Math.log(min + max) + (max - 0.5) * logRelative(-min / (min + max));
        }
        else {
            log = Math.log(Sfun.gamma(min) * (Sfun.gamma(max) / Sfun.gamma(min + max)));
        }
        return log;
    }
    
    public static double maximum(final double[] array) {
        if (array.length == 0) {
            return Double.NaN;
        }
        double n = array[0];
        for (int i = 1; i < array.length; ++i) {
            n = ((n > array[i]) ? n : array[i]);
        }
        return n;
    }
    
    public static double median(final double[] array) {
        final double[] array2 = new double[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        sort(array2);
        final double n = (array2.length - 1) * 0.5;
        final int n2 = (int)n;
        if (n2 >= array2.length - 1) {
            return array2[array2.length - 1];
        }
        final double n3 = n - n2;
        return (1.0 - n3) * array2[n2] + n3 * array2[n2 + 1];
    }
    
    public static double minimum(final double[] array) {
        if (array.length == 0) {
            return Double.NaN;
        }
        double n = array[0];
        for (int i = 1; i < array.length; ++i) {
            n = ((n < array[i]) ? n : array[i]);
        }
        return n;
    }
    
    public static double normalCdf(final double n) {
        return 0.5 * Sfun.erfc(-n * 0.7071067811865476);
    }
    
    public static double range(final double[] array) {
        if (array.length == 0) {
            return Double.NaN;
        }
        double n = array[0];
        double n2 = array[0];
        for (int i = 1; i < array.length; ++i) {
            n = ((n < array[i]) ? n : array[i]);
            n2 = ((n2 > array[i]) ? n2 : array[i]);
        }
        return n2 - n;
    }
    
    public static double skew(final double[] array) {
        double n = 0.0;
        for (int i = 0; i < array.length; ++i) {
            n += array[i];
        }
        final double n2 = n / array.length;
        double n3 = 0.0;
        for (int j = 0; j < array.length; ++j) {
            n3 += (array[j] - n2) * (array[j] - n2);
        }
        final double n4 = n3 / array.length;
        double n5 = 0.0;
        for (int k = 0; k < array.length; ++k) {
            final double n6 = array[k] - n2;
            n5 += n6 * n6 * n6;
        }
        return n5 * (Math.pow(n4, -1.5) / array.length);
    }
    
    public static double slope(final double[] array, final double[] array2) {
        if (array.length != array2.length) {
            throw new IllegalArgumentException("number of x and y values are not equal");
        }
        double n = 0.0;
        double n2 = 0.0;
        for (int i = 0; i < array.length; ++i) {
            n += array[i] * array2[i];
            n2 += array[i] * array[i];
        }
        if (n2 == 0.0) {
            double n3 = 0.0;
            for (int j = 0; j < array.length; ++j) {
                n3 += array2[j];
            }
            return n3 / array.length;
        }
        return n / n2;
    }
    
    private static void sort(final double[] array) {
        int n;
        for (int i = array.length; i != 0; i = n + 1) {
            n = -1;
            for (int j = 0; j < i - 1; ++j) {
                if (array[j] > array[j + 1]) {
                    final double n2 = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = n2;
                    n = j;
                }
            }
        }
    }
    
    public static double standardDeviation(final double[] array) {
        return Math.sqrt(variance(array));
    }
    
    public static double tCdf(final double n, final double n2) {
        final double n3 = Double.NaN;
        if (Double.isNaN(n)) {
            return n3;
        }
        if (n == Double.POSITIVE_INFINITY) {
            return 1.0;
        }
        if (n == Double.NEGATIVE_INFINITY) {
            return 0.0;
        }
        if (n2 < 1.0) {
            return n3;
        }
        final double n4 = n * n;
        double n15;
        if (n2 > n4) {
            double n5 = n2;
            final int n6 = (int)n5;
            final double n7 = n * n;
            double n8 = n7 / n5;
            final double n9 = 1.0 + n8;
            if (n5 != n6 || n5 >= 20.0 || n5 > 200.0) {
                final double n10 = n9 - 1.0;
                if (n10 != 0.0) {
                    n8 *= Math.log(n9) / n10;
                }
                final double n11 = n5 - 0.5;
                final double n12 = 48.0 * n11 * n11;
                final double n13 = n8 * n11;
                final double n14 = (((((-0.4 * n13 - 3.3) * n13 - 24.0) * n13 - 85.5) / (0.8 * (n13 * n13) + 100.0 + n12) + n13 + 3.0) / n12 + 1.0) * Math.sqrt(n13);
                if (n14 < 18.8125) {
                    n15 = Sfun.erfc(n14 * Math.sqrt(0.5));
                }
                else {
                    n15 = 0.0;
                }
            }
            else if (n5 < 20.0 && n7 < 4.0) {
                double sqrt;
                final double n16 = sqrt = Math.sqrt(n8);
                if (n5 == 1.0) {
                    sqrt = 0.0;
                }
                double n17;
                for (n17 = n5 - 2.0; n17 > 1.0; n17 -= 2.0) {
                    sqrt = (n17 - 1.0) / (n9 * n17) * sqrt + n16;
                }
                if (n17 == 0.0) {
                    sqrt /= Math.sqrt(n9);
                }
                if (n17 != 0.0) {
                    sqrt = (Math.atan(n16) + sqrt / n9) * 0.63661977236758;
                }
                n15 = 1.0 - sqrt;
            }
            else {
                double n18 = 1.0;
                for (double n19 = n5, n20 = 0.0, n21 = 0.0; n18 != n21; n21 = n18, n19 = n19 * (n20 - 1.0) / (n9 * n20), n18 += n19 / (n5 + n20)) {
                    n20 += 2.0;
                }
                while (n5 > 1.0 && n18 >= 1.0E-30) {
                    n18 *= (n5 - 1.0) / (n9 * n5);
                    n5 -= 2.0;
                }
                if (n5 != 0.0) {
                    n18 = Math.sqrt(n9) * 0.63661977236758 * n18 / n9;
                }
                n15 = n18;
            }
        }
        else {
            n15 = beta_incomplete(n2 / (n2 + n4), 0.5 * n2, 0.5);
        }
        double n22;
        if (n > 0.0) {
            n22 = 1.0 - 0.5 * n15;
        }
        else {
            n22 = 0.5 * n15;
        }
        return n22;
    }
    
    public static double variance(final double[] array) {
        double n = 0.0;
        if (array.length < 2) {
            return Double.NaN;
        }
        for (int i = 0; i < array.length; ++i) {
            n += array[i];
        }
        final double n2 = n / array.length;
        double n3 = 0.0;
        for (int j = 0; j < array.length; ++j) {
            n3 += (array[j] - n2) * (array[j] - n2);
        }
        return n3 / (array.length - 1);
    }
}
