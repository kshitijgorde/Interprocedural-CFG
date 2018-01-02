// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

public class I
{
    public static final float N = 3.1415927f;
    public static final float A = 1.5707964f;
    public static final float Q = 0.7853982f;
    public static final float O = 6.2831855f;
    private static final float L = -0.5f;
    private static final float J = 1.5f;
    private static final float H = -1.5f;
    private static final float F = 0.5f;
    private static final float T = 1.0f;
    private static final float S = -2.5f;
    private static final float R = 2.0f;
    private static final float P = -0.5f;
    private static final float E = -0.5f;
    private static final float D = 0.0f;
    private static final float C = 0.5f;
    private static final float B = 0.0f;
    private static final float M = 0.0f;
    private static final float K = 1.0f;
    private static final float I = 0.0f;
    private static final float G = 0.0f;
    
    public static float C(final float n, final float n2) {
        return n / ((1.0f / n2 - 2.0f) * (1.0f - n) + 1.0f);
    }
    
    public static float B(final float n, final float n2) {
        final float n3 = (1.0f / n2 - 2.0f) * (1.0f - 2.0f * n);
        if (n < 0.5) {
            return n / (n3 + 1.0f);
        }
        return (n3 - n) / (n3 - 1.0f);
    }
    
    public static float D(final float n, final float n2) {
        return (n2 < n) ? 0.0f : 1.0f;
    }
    
    public static float C(final float n, final float n2, final float n3) {
        return (n3 < n || n3 >= n2) ? 0.0f : 1.0f;
    }
    
    public static float A(final float n, final float n2, final float n3, final float n4, float n5) {
        if (n5 < n || n5 >= n4) {
            return 0.0f;
        }
        if (n5 < n2) {
            n5 = (n5 - n) / (n2 - n);
            return n5 * n5 * (3.0f - 2.0f * n5);
        }
        if (n5 < n3) {
            return 1.0f;
        }
        n5 = (n5 - n3) / (n4 - n3);
        return 1.0f - n5 * n5 * (3.0f - 2.0f * n5);
    }
    
    public static float A(final float n, final float n2, float n3) {
        if (n3 < n) {
            return 0.0f;
        }
        if (n3 >= n2) {
            return 1.0f;
        }
        n3 = (n3 - n) / (n2 - n);
        return n3 * n3 * (3.0f - 2.0f * n3);
    }
    
    public static float C(float n) {
        n = 1.0f - n;
        return (float)Math.sqrt(1.0f - n * n);
    }
    
    public static float A(final float n) {
        return 1.0f - (float)Math.sqrt(1.0f - n * n);
    }
    
    public static float B(final float n, final float n2, final float n3) {
        return (n < n2) ? n2 : ((n > n3) ? n3 : n);
    }
    
    public static int A(final int n, final int n2, final int n3) {
        return (n < n2) ? n2 : ((n > n3) ? n3 : n);
    }
    
    public static float A(float n, final float n2) {
        n -= (int)(n / n2) * n2;
        if (n < 0.0f) {
            return n + n2;
        }
        return n;
    }
    
    public static int A(int n, final int n2) {
        n -= n / n2 * n2;
        if (n < 0) {
            return n + n2;
        }
        return n;
    }
    
    public static float B(final float n) {
        final float a = A(n, 1.0f);
        return 2.0f * ((a < 0.5) ? a : (1.0f - a));
    }
    
    public static float D(final float n, final float n2, final float n3) {
        return n2 + n * (n3 - n2);
    }
    
    public static int B(final float n, final int n2, final int n3) {
        return (int)(n2 + n * (n3 - n2));
    }
    
    public static int A(final float n, final int n2, final int n3) {
        return B(n, n2 >> 24 & 0xFF, n3 >> 24 & 0xFF) << 24 | B(n, n2 >> 16 & 0xFF, n3 >> 16 & 0xFF) << 16 | B(n, n2 >> 8 & 0xFF, n3 >> 8 & 0xFF) << 8 | B(n, n2 & 0xFF, n3 & 0xFF);
    }
    
    public static int A(final float n, final float n2, final int[] array) {
        final int n3 = array[0] >> 24 & 0xFF;
        final int n4 = array[0] >> 16 & 0xFF;
        final int n5 = array[0] >> 8 & 0xFF;
        final int n6 = array[0] & 0xFF;
        final int n7 = array[1] >> 24 & 0xFF;
        final int n8 = array[1] >> 16 & 0xFF;
        final int n9 = array[1] >> 8 & 0xFF;
        final int n10 = array[1] & 0xFF;
        final int n11 = array[2] >> 24 & 0xFF;
        final int n12 = array[2] >> 16 & 0xFF;
        final int n13 = array[2] >> 8 & 0xFF;
        final int n14 = array[2] & 0xFF;
        final int n15 = array[3] >> 24 & 0xFF;
        final int n16 = array[3] >> 16 & 0xFF;
        final int n17 = array[3] >> 8 & 0xFF;
        final int n18 = array[3] & 0xFF;
        final float n19 = 1.0f - n;
        final float n20 = 1.0f - n2;
        return (int)(n20 * (n19 * n3 + n * n7) + n2 * (n19 * n11 + n * n15)) << 24 | (int)(n20 * (n19 * n4 + n * n8) + n2 * (n19 * n12 + n * n16)) << 16 | (int)(n20 * (n19 * n5 + n * n9) + n2 * (n19 * n13 + n * n17)) << 8 | (int)(n20 * (n19 * n6 + n * n10) + n2 * (n19 * n14 + n * n18));
    }
    
    public static int A(final int n) {
        return (int)((n >> 16 & 0xFF) * 0.299f + (n >> 8 & 0xFF) * 0.587f + (n & 0xFF) * 0.114f);
    }
    
    public static float A(float n, final int n2, final float[] array) {
        final int n3 = n2 - 3;
        if (n3 < 1) {
            throw new IllegalArgumentException("Too few knots in spline");
        }
        n = B(n, 0.0f, 1.0f) * n3;
        int n4 = (int)n;
        if (n4 > n2 - 4) {
            n4 = n2 - 4;
        }
        n -= n4;
        final float n5 = array[n4];
        final float n6 = array[n4 + 1];
        final float n7 = array[n4 + 2];
        final float n8 = array[n4 + 3];
        return (((-0.5f * n5 + 1.5f * n6 + -1.5f * n7 + 0.5f * n8) * n + (1.0f * n5 + -2.5f * n6 + 2.0f * n7 + -0.5f * n8)) * n + (-0.5f * n5 + 0.0f * n6 + 0.5f * n7 + 0.0f * n8)) * n + (0.0f * n5 + 1.0f * n6 + 0.0f * n7 + 0.0f * n8);
    }
    
    public static float A(final float n, final int n2, final int[] array, final int[] array2) {
        final int n3 = n2 - 3;
        if (n3 < 1) {
            throw new IllegalArgumentException("Too few knots in spline");
        }
        int n4;
        for (n4 = 0; n4 < n3 && array[n4 + 1] <= n; ++n4) {}
        if (n4 > n2 - 3) {
            n4 = n2 - 3;
        }
        float n5 = (n - array[n4]) / (array[n4 + 1] - array[n4]);
        if (--n4 < 0) {
            n4 = 0;
            n5 = 0.0f;
        }
        final float n6 = array2[n4];
        final float n7 = array2[n4 + 1];
        final float n8 = array2[n4 + 2];
        final float n9 = array2[n4 + 3];
        return (((-0.5f * n6 + 1.5f * n7 + -1.5f * n8 + 0.5f * n9) * n5 + (1.0f * n6 + -2.5f * n7 + 2.0f * n8 + -0.5f * n9)) * n5 + (-0.5f * n6 + 0.0f * n7 + 0.5f * n8 + 0.0f * n9)) * n5 + (0.0f * n6 + 1.0f * n7 + 0.0f * n8 + 0.0f * n9);
    }
    
    public static int A(float n, final int n2, final int[] array) {
        final int n3 = n2 - 3;
        if (n3 < 1) {
            throw new IllegalArgumentException("Too few knots in spline");
        }
        n = B(n, 0.0f, 1.0f) * n3;
        int n4 = (int)n;
        if (n4 > n2 - 4) {
            n4 = n2 - 4;
        }
        n -= n4;
        int n5 = 0;
        for (int i = 0; i < 4; ++i) {
            final int n6 = i * 8;
            final float n7 = array[n4] >> n6 & 0xFF;
            final float n8 = array[n4 + 1] >> n6 & 0xFF;
            final float n9 = array[n4 + 2] >> n6 & 0xFF;
            final float n10 = array[n4 + 3] >> n6 & 0xFF;
            int n11 = (int)((((-0.5f * n7 + 1.5f * n8 + -1.5f * n9 + 0.5f * n10) * n + (1.0f * n7 + -2.5f * n8 + 2.0f * n9 + -0.5f * n10)) * n + (-0.5f * n7 + 0.0f * n8 + 0.5f * n9 + 0.0f * n10)) * n + (0.0f * n7 + 1.0f * n8 + 0.0f * n9 + 0.0f * n10));
            if (n11 < 0) {
                n11 = 0;
            }
            else if (n11 > 255) {
                n11 = 255;
            }
            n5 |= n11 << n6;
        }
        return n5;
    }
    
    public static int A(final int n, final int n2, final int[] array, final int[] array2) {
        final int n3 = n2 - 3;
        if (n3 < 1) {
            throw new IllegalArgumentException("Too few knots in spline");
        }
        int n4;
        for (n4 = 0; n4 < n3 && array[n4 + 1] <= n; ++n4) {}
        if (n4 > n2 - 3) {
            n4 = n2 - 3;
        }
        float n5 = (n - array[n4]) / (array[n4 + 1] - array[n4]);
        if (--n4 < 0) {
            n4 = 0;
            n5 = 0.0f;
        }
        int n6 = 0;
        for (int i = 0; i < 4; ++i) {
            final int n7 = i * 8;
            final float n8 = array2[n4] >> n7 & 0xFF;
            final float n9 = array2[n4 + 1] >> n7 & 0xFF;
            final float n10 = array2[n4 + 2] >> n7 & 0xFF;
            final float n11 = array2[n4 + 3] >> n7 & 0xFF;
            int n12 = (int)((((-0.5f * n8 + 1.5f * n9 + -1.5f * n10 + 0.5f * n11) * n5 + (1.0f * n8 + -2.5f * n9 + 2.0f * n10 + -0.5f * n11)) * n5 + (-0.5f * n8 + 0.0f * n9 + 0.5f * n10 + 0.0f * n11)) * n5 + (0.0f * n8 + 1.0f * n9 + 0.0f * n10 + 0.0f * n11));
            if (n12 < 0) {
                n12 = 0;
            }
            else if (n12 > 255) {
                n12 = 255;
            }
            n6 |= n12 << n7;
        }
        return n6;
    }
    
    public static void A(final int[] array, final int[] array2, final int n, final int n2, final int n3, final float[] array3) {
        int n4 = n2;
        final int length = array.length;
        final float[] array4 = new float[n + 1];
        int n5 = 0;
        for (int i = 0; i < n; ++i) {
            while (array3[n5 + 1] < i) {
                ++n5;
            }
            array4[i] = n5 + (i - array3[n5]) / (array3[n5 + 1] - array3[n5]);
        }
        array4[n] = n;
        float n6 = 1.0f;
        float n8;
        float n7 = n8 = array4[1];
        float n12;
        float n11;
        float n10;
        float n9 = n10 = (n11 = (n12 = 0.0f));
        final int n13 = array[n2];
        int n14 = n13 >> 24 & 0xFF;
        int n15 = n13 >> 16 & 0xFF;
        int n16 = n13 >> 8 & 0xFF;
        int n17 = n13 & 0xFF;
        final int n18 = n2 + n3;
        int n19 = array[n18];
        int n20 = n19 >> 24 & 0xFF;
        int n21 = n19 >> 16 & 0xFF;
        int n22 = n19 >> 8 & 0xFF;
        int n23 = n19 & 0xFF;
        int n24 = n18 + n3;
        int j = 1;
        while (j < n) {
            final float n25 = n6 * n14 + (1.0f - n6) * n20;
            final float n26 = n6 * n15 + (1.0f - n6) * n21;
            final float n27 = n6 * n16 + (1.0f - n6) * n22;
            final float n28 = n6 * n17 + (1.0f - n6) * n23;
            if (n6 < n7) {
                n10 += n25 * n6;
                n9 += n26 * n6;
                n11 += n27 * n6;
                n12 += n28 * n6;
                n7 -= n6;
                n6 = 1.0f;
                n14 = n20;
                n15 = n21;
                n16 = n22;
                n17 = n23;
                if (n24 < length) {
                    n19 = array[n24];
                }
                n20 = (n19 >> 24 & 0xFF);
                n21 = (n19 >> 16 & 0xFF);
                n22 = (n19 >> 8 & 0xFF);
                n23 = (n19 & 0xFF);
                n24 += n3;
            }
            else {
                n10 += n25 * n7;
                array2[n4] = ((int)Math.min(n10 / n8, 255.0f) << 24 | (int)Math.min((n9 + n26 * n7) / n8, 255.0f) << 16 | (int)Math.min((n11 + n27 * n7) / n8, 255.0f) << 8 | (int)Math.min((n12 + n28 * n7) / n8, 255.0f));
                n4 += n3;
                n11 = (n9 = (n12 = 0.0f));
                n6 -= n7;
                n7 = (n8 = array4[j + 1] - array4[j]);
                ++j;
            }
        }
    }
}
