// 
// Decompiled by Procyon v0.5.30
// 

package c;

class b
{
    public static int a(final float[] array, final int n, final int n2) {
        final int n3 = 2;
        int n4 = 0;
        final float n5 = 0.039f;
        final float n6 = 0.0195f;
        final float n7 = 3.14f;
        final float n8 = 0.01f;
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n2; ++j) {
                for (int k = 0; k < n - 1; ++k) {
                    final int n9 = j * n + k;
                    if (array[n9 + 1] - array[n9] < n5) {
                        if (array[n9 + 1] < array[n9]) {
                            final float n10 = array[n9 + 1];
                            array[n9 + 1] = array[n9] + n6;
                            array[n9] = array[n9 + 1] - n6;
                        }
                        else {
                            final int n11 = n9;
                            array[n11] -= n6;
                            final int n12 = n9 + 1;
                            array[n12] += n6;
                        }
                        n4 = 1;
                    }
                    if (array[n9] < n8) {
                        array[n9] = n8;
                        n4 = 1;
                    }
                    if (array[n9] > n7) {
                        array[n9] = n7;
                        n4 = 1;
                    }
                }
            }
        }
        return n4;
    }
    
    public static void a(final int n, final int[] array, final float[] array2, final int n2, final float[] array3, final int n3, final int n4) {
        final float[] array4 = new float[g.i + 2 * g.void];
        final float[] array5 = new float[g.i + 1];
        final float[] array6 = new float[g.i + 2 * g.void];
        final float n5 = (float)Math.pow(10.0, g.ad[n]) / 4.5f;
        for (int i = 0; i < g.i; ++i) {
            array6[i] = (array4[i] = 0.0f);
        }
        for (int j = 0; j < g.i; ++j) {
            array5[j] = array2[n2 + g.i - j];
        }
        array5[g.i] = array2[n2];
        final int k = g.i;
        final int l = g.i;
        for (int n6 = 0; n6 < n4; ++n6) {
            array4[k + n6] = n5 * g.aN[array[n4 - 1 - n6]];
        }
        for (int n7 = 0; n7 < n4; ++n7) {
            array4[k + n4 + n7] = 0.0f;
        }
        a(array4, k, array5, array2, n2, 2 * n4, g.i, array6, l);
        for (int n8 = 0; n8 < n4; ++n8) {
            array3[n3 + n8] = array6[l + n4 - 1 - n8] + array6[l + 2 * n4 - 1 - n8];
        }
    }
    
    public static void a(final float[] array, final int n, final float[] array2, final int n2, final int n3, final int n4) {
        for (int i = 0; i < n3; ++i) {
            for (int j = 1; j <= n4; ++j) {
                final int n5 = i + n;
                array[n5] -= array2[n2 + j] * array[i - j + n];
            }
        }
    }
    
    public static void a(final float[] array, int n, final float[] array2, final int n2, final int n3, final float[] array3, int n4) {
        for (int i = 0; i < n2; ++i) {
            array3[n4] = array2[0] * array[n];
            for (int j = 1; j <= n3; ++j) {
                final int n5 = n4;
                array3[n5] += array2[j] * array[n - j];
            }
            ++n4;
            ++n;
        }
    }
    
    public static void a(final float[] array, final int n, final float[] array2, final float[] array3, final int n2, final int n3, final int n4, final float[] array4, final int n5) {
        a(array, n, array2, n3, n4, array4, n5);
        a(array4, n5, array3, n2, n3, n4);
    }
    
    public static void a(final float[] array, final float[] array2) {
        final float[] array3 = new float[g.n];
        final float[] array4 = new float[g.n];
        final float[] array5 = new float[g.n + 1];
        final float[] array6 = new float[g.n];
        final float[] array7 = new float[g.n];
        final float[] array8 = new float[g.n + 1];
        final float[] array9 = new float[g.n];
        final float[] array10 = new float[g.n];
        for (int i = 0; i < g.i; ++i) {
            array2[i] *= g.z;
        }
        if (array2[0] <= 0.0f || array2[g.i - 1] >= 0.5) {
            if (array2[0] <= 0.0f) {
                array2[0] = 0.022f;
            }
            if (array2[g.i - 1] >= 0.5) {
                array2[g.i - 1] = 0.499f;
            }
            final float n = (array2[g.i - 1] - array2[0]) / (g.i - 1);
            for (int j = 1; j < g.i; ++j) {
                array2[j] = array2[j - 1] + n;
            }
        }
        for (int k = 0; k < g.n; ++k) {
            array7[k] = (array6[k] = 0.0f);
            array10[k] = (array9[k] = 0.0f);
        }
        for (int l = 0; l < g.n + 1; ++l) {
            array8[l] = (array5[l] = 0.0f);
        }
        for (int n2 = 0; n2 < g.n; ++n2) {
            array3[n2] = (float)Math.cos(g.D * array2[2 * n2]);
            array4[n2] = (float)Math.cos(g.D * array2[2 * n2 + 1]);
        }
        array8[0] = (array5[0] = 0.25f);
        for (int n3 = 0; n3 < g.n; ++n3) {
            array5[n3 + 1] = array5[n3] - 2.0f * array3[n3] * array6[n3] + array7[n3];
            array8[n3 + 1] = array8[n3] - 2.0f * array4[n3] * array9[n3] + array10[n3];
            array7[n3] = array6[n3];
            array6[n3] = array5[n3];
            array10[n3] = array9[n3];
            array9[n3] = array8[n3];
        }
        for (int n4 = 0; n4 < g.i; ++n4) {
            if (n4 == 0) {
                array5[0] = 0.25f;
                array8[0] = -0.25f;
            }
            else {
                array5[0] = (array8[0] = 0.0f);
            }
            for (int n5 = 0; n5 < g.n; ++n5) {
                array5[n5 + 1] = array5[n5] - 2.0f * array3[n5] * array6[n5] + array7[n5];
                array8[n5 + 1] = array8[n5] - 2.0f * array4[n5] * array9[n5] + array10[n5];
                array7[n5] = array6[n5];
                array6[n5] = array5[n5];
                array10[n5] = array9[n5];
                array9[n5] = array8[n5];
            }
            array[n4 + 1] = 2.0f * (array5[g.n] + array8[g.n]);
        }
        array[0] = 1.0f;
    }
    
    public static void a(final float[] array, final float[] array2, final float[] array3, final int n, final float n2, final int n3) {
        final float n4 = 1.0f - n2;
        for (int i = 0; i < n3; ++i) {
            array[i] = n2 * array2[i] + n4 * array3[i + n];
        }
    }
    
    public static void a(final float[] array, final int n, final float[] array2, final float n2, final int n3) {
        float n4 = n2;
        array[n] = array2[0];
        for (int i = 1; i < n3; ++i) {
            array[i + n] = n4 * array2[i];
            n4 *= n2;
        }
    }
    
    public static void a(final float[] array, final float[] array2, final int n, final int n2, final int n3, final int n4) {
        final float[] array3 = new float[g.G];
        int n5 = n3 - n4 + 1;
        if (n4 == g.A) {
            n5 += n4 / 2;
        }
        if (n2 < n3 - n4 + 1) {
            System.arraycopy(array2, n + n3 - (n2 + n4), array, 0, n4);
        }
        else if (n2 < n5) {
            final int n6 = 2 * (n2 - (n3 - n4 + 1)) + n4;
            final int n7 = n6 / 2;
            final int n8 = n7 - 5;
            System.arraycopy(array2, n + n3 - n6 / 2, array, 0, n8);
            final float n9 = 0.2f;
            float n10 = 0.0f;
            for (int i = n8; i < n7; ++i) {
                array[i] = (1.0f - n10) * array2[n + n3 - n6 / 2 + i] + n10 * array2[n + n3 - n6 + i];
                n10 += n9;
            }
            System.arraycopy(array2, n + n3 - n6 + n7, array, n7, n4 - n7);
        }
        else if (n2 - n5 < n3 - n4 + 1) {
            final float[] array4 = new float[g.G + g.aM + 1];
            for (int j = 0; j < g.an; ++j) {
                array4[j] = 0.0f;
            }
            System.arraycopy(array2, n, array4, g.an, n3);
            for (int k = 0; k < g.an + 1; ++k) {
                array4[n3 + g.an + k] = 0.0f;
            }
            final int n11 = n3 - (n2 - n5 + n4) + 1 - g.an;
            int n12 = 0;
            for (int l = 0; l < n4; ++l) {
                array[l] = 0.0f;
            }
            for (int n13 = 0; n13 < n4; ++n13) {
                int n14 = n11 + n13 + g.an;
                int n15 = g.aM - 1;
                for (int n16 = 0; n16 < g.aM; ++n16) {
                    final int n17 = n12;
                    array[n17] += array4[n14] * g.y[n15];
                    ++n14;
                    --n15;
                }
                ++n12;
            }
        }
        else {
            final float[] array5 = new float[g.G + g.aM + 1];
            for (int n18 = 0; n18 < g.an; ++n18) {
                array5[n18] = 0.0f;
            }
            System.arraycopy(array2, n, array5, g.an, n3);
            for (int n19 = 0; n19 < g.an; ++n19) {
                array5[n3 + g.an + n19] = 0.0f;
            }
            final int n20 = 2 * (n2 - n5 - (n3 - n4 + 1)) + n4;
            final int n21 = n3 - n20;
            final int n22 = n21 + 1 - g.an;
            int n23 = n21;
            for (int n24 = 0; n24 < n20; ++n24) {
                array3[n23 + n24] = 0.0f;
            }
            for (int n25 = 0; n25 < n20; ++n25) {
                int n26 = n22 + n25 + g.an;
                int n27 = g.aM - 1;
                for (int n28 = 0; n28 < g.aM; ++n28) {
                    final float[] array6 = array3;
                    final int n29 = n23;
                    array6[n29] += array5[n26] * g.y[n27];
                    ++n26;
                    --n27;
                }
                ++n23;
            }
            final int n30 = n20 / 2;
            final int n31 = n30 - 5;
            System.arraycopy(array3, n3 - n20 / 2, array, 0, n31);
            final float n32 = 0.2f;
            float n33 = 0.0f;
            for (int n34 = n31; n34 < n30; ++n34) {
                array[n34] = (1.0f - n33) * array3[n3 - n20 / 2 + n34] + n33 * array3[n3 - n20 + n34];
                n33 += n32;
            }
            System.arraycopy(array3, n3 - n20 + n30, array, n30, n4 - n30);
        }
    }
    
    public static float a(final float n, final float n2, final int n3, final int[] array, final int n4) {
        float n5 = n2;
        if (n5 < 0.1) {
            n5 = 0.1f;
        }
        float[] array2;
        if (n3 == 8) {
            array2 = g.L;
        }
        else if (n3 == 16) {
            array2 = g.P;
        }
        else {
            array2 = g.T;
        }
        float n6 = 1.0E7f;
        int n7 = 0;
        for (int i = 0; i < n3; ++i) {
            final float n8 = (n - n5 * array2[i]) * (n - n5 * array2[i]);
            if (n8 < n6) {
                n7 = i;
                n6 = n8;
            }
        }
        array[n4] = n7;
        return n5 * array2[n7];
    }
    
    public static float a(final int n, final float n2, final int n3) {
        float abs = Math.abs(n2);
        if (abs < 0.1) {
            abs = 0.1f;
        }
        if (n3 == 8) {
            return abs * g.L[n];
        }
        if (n3 == 16) {
            return abs * g.P[n];
        }
        if (n3 == 32) {
            return abs * g.T[n];
        }
        return 0.0f;
    }
    
    public static void a(final float[] array, final int n, final int[] array2, final int n2, final int[] array3, final int n3, final float[] array4, final int n4, final int n5, final int n6, final int n7) {
        final float[] array5 = new float[g.as];
        final float[] array6 = new float[g.A];
        array5[0] = a(array3[n3 + 0], 1.0f, 32);
        if (n7 > 1) {
            array5[1] = a(array3[n3 + 1], Math.abs(array5[0]), 16);
        }
        if (n7 > 2) {
            array5[2] = a(array3[n3 + 2], Math.abs(array5[1]), 8);
        }
        a(array6, array4, n4, array2[n2 + 0], n5, n6);
        for (int i = 0; i < n6; ++i) {
            array[n + i] = array5[0] * array6[i];
        }
        if (n7 > 1) {
            for (int j = 1; j < n7; ++j) {
                a(array6, array4, n4, array2[n2 + j], n5, n6);
                for (int k = 0; k < n6; ++k) {
                    final int n8 = n + k;
                    array[n8] += array5[j] * array6[k];
                }
            }
        }
    }
}
