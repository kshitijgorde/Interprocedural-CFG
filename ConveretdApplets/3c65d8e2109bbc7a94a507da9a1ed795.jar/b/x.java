// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class x
{
    public static int a(final float[] array, final int n, final int n2, final int n3, final int n4) {
        final g g = new g();
        final g g2 = new g();
        final g g3 = new g();
        int a = a(array, n, n4, n3, 80, g);
        final int a2 = a(array, n, n4, 79, 40, g2);
        final int a3 = a(array, n, n4, 39, n2, g3);
        if (g.a * 0.85f < g2.a) {
            g.a = g2.a;
            a = a2;
        }
        if (g.a * 0.85f < g3.a) {
            a = a3;
        }
        return a;
    }
    
    public static int a(final float[] array, final int n, final int n2, final int n3, final int n4, final g g) {
        int n5 = 0;
        float n6 = -1.0E38f;
        for (int i = n3; i >= n4; --i) {
            int n7 = 0;
            int n8 = -i;
            float n9 = 0.0f;
            for (int j = 0; j < n2; ++j) {
                n9 += array[n + n7++] * array[n + n8++];
            }
            if (n9 >= n6) {
                n6 = n9;
                n5 = i;
            }
        }
        float n10 = 0.01f;
        for (int n11 = -n5, k = 0; k < n2; ++k, ++n11) {
            n10 += array[n + n11] * array[n + n11];
        }
        g.a = new Float(n6 * a(n10));
        return n5;
    }
    
    public static int a(final float[] array, final int n, final float[] array2, final int n2, final float[] array3, final int n3, final int n4, final int n5, final int n6, final int n7, final af af) {
        final float[] array4 = new float[18];
        final int n8 = n5 - 4;
        final int n9 = n6 + 4;
        final int n10 = -n8;
        a(array, n, array2, n2, array3, n3, n4, n8, n9, array4, n10);
        float n11 = array4[n10 + n5];
        int n12 = n5;
        for (int i = n5 + 1; i <= n6; ++i) {
            if (array4[n10 + i] >= n11) {
                n11 = array4[n10 + i];
                n12 = i;
            }
        }
        if (n7 == 0 && n12 > 84) {
            af.a = new Integer(0);
            return n12;
        }
        float a = a(array4, n10 + n12, -2);
        int n13 = -2;
        for (int j = -1; j <= 2; ++j) {
            final float a2 = a(array4, n10 + n12, j);
            if (a2 > a) {
                a = a2;
                n13 = j;
            }
        }
        if (n13 == -2) {
            n13 = 1;
            --n12;
        }
        if (n13 == 2) {
            n13 = -1;
            ++n12;
        }
        af.a = new Integer(n13);
        return n12;
    }
    
    public static void a(final float[] array, final int n, final float[] array2, final int n2, final float[] array3, final int n3, final int n4, final int n5, final int n6, final float[] array4, final int n7) {
        final float[] array5 = new float[40];
        int n8 = -n5;
        ah.if(array, n + n8, array3, 0, array5, 0, n4);
        for (int i = n5; i <= n6; ++i) {
            float n9 = 0.01f;
            for (int j = 0; j < n4; ++j) {
                n9 += array5[j] * array5[j];
            }
            final float a = a(n9);
            float n10 = 0.0f;
            for (int k = 0; k < n4; ++k) {
                n10 += array2[n2 + k] * array5[k];
            }
            array4[n7 + i] = n10 * a;
            if (i != n6) {
                --n8;
                for (int l = n4 - 1; l > 0; --l) {
                    array5[l] = array5[l - 1] + array[n + n8] * array3[l];
                }
                array5[0] = array[n + n8];
            }
        }
    }
    
    public static float a(final float[] array, final int n, final float[] array2, final int n2, final float[] array3, final int n3, final int n4) {
        float n5 = 0.0f;
        for (int i = 0; i < n4; ++i) {
            n5 += array[n + i] * array2[n2 + i];
        }
        float n6 = 0.01f;
        for (int j = 0; j < n4; ++j) {
            n6 += array2[n2 + j] * array2[n2 + j];
        }
        array3[n3 + 0] = n6;
        array3[n3 + 1] = -2.0f * n5 + 0.01f;
        float n7 = n5 / n6;
        if (n7 < 0.0f) {
            n7 = 0.0f;
        }
        if (n7 > 1.2f) {
            n7 = 1.2f;
        }
        return n7;
    }
    
    public static int a(final int n, final int n2, final af af, final af af2, final int n3, final int n4, final int n5) {
        int n6;
        if (n5 == 0) {
            if (n <= 85) {
                n6 = n * 3 - 58 + n2;
            }
            else {
                n6 = n + 112;
            }
            af.a = new Integer(n - 5);
            if (af.a < n3) {
                af.a = new Integer(n3);
            }
            af2.a = new Integer(af.a + 9);
            if (af2.a > n4) {
                af2.a = new Integer(n4);
                af.a = new Integer(af2.a - 9);
            }
        }
        else {
            n6 = (n - af.a) * 3 + 2 + n2;
        }
        return n6;
    }
    
    public static float a(final float[] array, int n, int n2) {
        if (n2 < 0) {
            n2 += 3;
            --n;
        }
        int n3 = 0;
        int n4 = 1;
        int n5 = n2;
        int n6 = 3 - n2;
        float n7 = 0.0f;
        for (int i = 0; i < 4; ++i, n5 += 3, n6 += 3) {
            n7 += array[n + n3--] * c.for[n5] + array[n + n4++] * c.for[n6];
        }
        return n7;
    }
    
    static float a(final float n) {
        return 1.0f / (float)Math.sqrt(n);
    }
}
