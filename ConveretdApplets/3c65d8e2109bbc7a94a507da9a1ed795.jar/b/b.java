// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class b
{
    void a(final float[] array, final int n, final float[] array2) {
        final float[] array3 = new float[240];
        for (int i = 0; i < 240; ++i) {
            array3[i] = array[i] * c.try[i];
        }
        for (int j = 0; j <= n; ++j) {
            float n2 = 0.0f;
            for (int k = 0; k < 240 - j; ++k) {
                n2 += array3[k] * array3[k + j];
            }
            array2[j] = n2;
        }
        if (array2[0] < 1.0f) {
            array2[0] = 1.0f;
        }
    }
    
    void a(final int n, final float[] array) {
        for (int i = 1; i <= n; ++i) {
            final int n2 = i;
            array[n2] *= c.f[i - 1];
        }
    }
    
    float if(final float[] array, final float[] array2, final float[] array3) {
        array3[0] = -array[1] / array[0];
        array2[0] = 1.0f;
        array2[1] = array3[0];
        float n = array[0] + array[1] * array3[0];
        for (int i = 2; i <= 10; ++i) {
            float n2 = 0.0f;
            for (int j = 0; j < i; ++j) {
                n2 += array[i - j] * array2[j];
            }
            array3[i - 1] = -n2 / n;
            for (int k = 1; k <= i / 2; ++k) {
                final int n3 = i - k;
                final float n4 = array2[k] + array3[i - 1] * array2[n3];
                final int n5 = n3;
                array2[n5] += array3[i - 1] * array2[k];
                array2[k] = n4;
            }
            array2[i] = array3[i - 1];
            n += array3[i - 1] * n2;
            if (n <= 0.0f) {
                n = 0.001f;
            }
        }
        return n;
    }
    
    void a(final float[] array, final float[] array2, final float[] array3) {
        final float[] array4 = new float[6];
        final float[] array5 = new float[6];
        array5[0] = (array4[0] = 1.0f);
        for (int i = 1, n = 10; i <= 5; ++i, --n) {
            array4[i] = array[i] + array[n] - array4[i - 1];
            array5[i] = array[i] - array[n] + array5[i - 1];
        }
        int n2 = 0;
        int n3 = 0;
        float[] array6 = array4;
        float n4 = c.if[0];
        float n5 = a(n4, array4, 5);
        float n10 = 0.0f;
        for (int n6 = 0; n2 < 10 && n6 < 60; ++n2, n3 = 1 - n3, array6 = ((n3 != 0) ? array5 : array4), n4 = n10, n5 = a(n4, array6, 5)) {
            ++n6;
            float n7 = n4;
            float n8 = n5;
            n4 = c.if[n6];
            n5 = a(n4, array6, 5);
            if (n5 * n8 <= 0.0f) {
                --n6;
                for (int j = 0; j < 4; ++j) {
                    final float n9 = 0.5f * (n4 + n7);
                    final float a = a(n9, array6, 5);
                    if (n5 * a <= 0.0f) {
                        n8 = a;
                        n7 = n9;
                    }
                    else {
                        n5 = a;
                        n4 = n9;
                    }
                }
                n10 = n4 - n5 * (n7 - n4) / (n8 - n5);
                array2[n2] = n10;
            }
        }
        if (n2 < 10) {
            for (int k = 0; k < 10; ++k) {
                array2[k] = array3[k];
            }
        }
    }
    
    static float a(final float n, final float[] array, final int n2) {
        final float n3 = 2.0f * n;
        float n4 = 1.0f;
        float n5 = n3 + array[1];
        for (int i = 2; i < n2; ++i) {
            final float n6 = n3 * n5 - n4 + array[i];
            n4 = n5;
            n5 = n6;
        }
        return n * n5 - n4 + 0.5f * array[n2];
    }
}
