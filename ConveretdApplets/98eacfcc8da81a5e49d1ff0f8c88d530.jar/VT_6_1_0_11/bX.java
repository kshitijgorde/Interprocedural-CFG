// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class bX
{
    private float[] a;
    
    public bX() {
        this.a = new float[42];
    }
    
    private static float a(final float[] array, float n, int i) {
        final int n2;
        final float[] array2;
        (array2 = new float[(n2 = i >> 1) + 1])[0] = 1.0f;
        array2[1] = n;
        float n3 = array[n2] + array[n2 - 1] * n;
        n *= 2.0f;
        for (i = 2; i <= n2; ++i) {
            array2[i] = n * array2[i - 1] - array2[i - 2];
            n3 += array[n2 - i] * array2[i];
        }
        return n3;
    }
    
    public static int a(final float[] array, final int n, final float[] array2, final int n2, final float n3) {
        float n4 = 0.0f;
        int n5 = 0;
        final int n6;
        final float[] array3 = new float[(n6 = n / 2) + 1];
        final float[] array4 = new float[n6 + 1];
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        final float[] array5 = array4;
        final int n11 = 0;
        ++n7;
        array5[n11] = 1.0f;
        final float[] array6 = array3;
        final int n12 = 0;
        ++n8;
        array6[n12] = 1.0f;
        for (int i = 1; i <= n6; ++i) {
            array4[n7++] = array[i] + array[n + 1 - i] - array4[n9++];
            array3[n8++] = array[i] - array[n + 1 - i] + array3[n10++];
        }
        int n13 = 0;
        int n14 = 0;
        for (int j = 0; j < n6; ++j) {
            array4[n13] *= 2.0f;
            array3[n14] *= 2.0f;
            ++n13;
            ++n14;
        }
        float n15 = 0.0f;
        float n16 = 1.0f;
        for (int k = 0; k < n; ++k) {
            float[] array7;
            if (k % 2 != 0) {
                array7 = array3;
            }
            else {
                array7 = array4;
            }
            float a = a(array7, n16, n);
            int n17 = 1;
            while (n17 == 1 && n15 >= -1.0) {
                float n18 = (float)(n3 * (1.0 - 0.9 * n16 * n16));
                if (Math.abs(a) < 0.2) {
                    n18 *= 0.5;
                }
                n15 = n16 - n18;
                final float a2 = a(array7, n15, n);
                final float n19 = n15;
                if (a2 * a < 0.0) {
                    ++n5;
                    for (int l = 0; l <= n2; ++l) {
                        n4 = (n16 + n15) / 2.0f;
                        final float a3;
                        if ((a3 = a(array7, n4, n)) * a > 0.0) {
                            a = a3;
                            n16 = n4;
                        }
                        else {
                            n15 = n4;
                        }
                    }
                    array2[k] = n4;
                    n16 = n4;
                    n17 = 0;
                }
                else {
                    a = a2;
                    n16 = n19;
                }
            }
        }
        return n5;
    }
    
    public final void a(final float[] array, final float[] array2, final int n) {
        int n2 = 0;
        final int n3 = n / 2;
        for (int i = 0; i < n3 * 4 + 2; ++i) {
            this.a[i] = 0.0f;
        }
        float n4 = 1.0f;
        float n5 = 1.0f;
        for (int j = 0; j <= n; ++j) {
            for (int n6 = 0, k = 0; k < n3; ++k, n6 += 2) {
                final int n9;
                final int n8;
                final int n7;
                n2 = (n7 = (n8 = (n9 = k << 2) + 1) + 1) + 1;
                final float n10 = n4 - 2.0f * array[n6] * this.a[n9] + this.a[n8];
                final float n11 = n5 - 2.0f * array[n6 + 1] * this.a[n7] + this.a[n2];
                this.a[n8] = this.a[n9];
                this.a[n2] = this.a[n7];
                this.a[n9] = n4;
                this.a[n7] = n5;
                n4 = n10;
                n5 = n11;
            }
            array2[j] = (n4 + this.a[n2 + 1] + (n5 - this.a[n2 + 2])) * 0.5f;
            this.a[n2 + 1] = n4;
            this.a[n2 + 2] = n5;
            n4 = 0.0f;
            n5 = 0.0f;
        }
    }
    
    public static void a(final float[] array, final int n, final float n2) {
        if (array[0] < n2) {
            array[0] = n2;
        }
        if (array[n - 1] > 3.1415927f - n2) {
            array[n - 1] = 3.1415927f - n2;
        }
        for (int i = 1; i < n - 1; ++i) {
            if (array[i] < array[i - 1] + n2) {
                array[i] = array[i - 1] + n2;
            }
            if (array[i] > array[i + 1] - n2) {
                array[i] = 0.5f * (array[i] + array[i + 1] - n2);
            }
        }
    }
}
