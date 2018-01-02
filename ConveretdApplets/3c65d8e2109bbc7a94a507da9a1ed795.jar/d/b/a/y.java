// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class y
{
    private float[] a;
    
    public y() {
        this.a = new float[42];
    }
    
    public static final float a(final float[] array, float n, final int n2) {
        final int n3 = n2 >> 1;
        final float[] array2 = new float[n3 + 1];
        array2[0] = 1.0f;
        array2[1] = n;
        float n4 = array[n3] + array[n3 - 1] * n;
        n *= 2.0f;
        for (int i = 2; i <= n3; ++i) {
            array2[i] = n * array2[i - 1] - array2[i - 2];
            n4 += array[n3 - i] * array2[i];
        }
        return n4;
    }
    
    public static int a(final float[] array, final int n, final float[] array2, final int n2, final float n3) {
        float n4 = 0.0f;
        int n5 = 0;
        final int n6 = n / 2;
        final float[] array3 = new float[n6 + 1];
        final float[] array4 = new float[n6 + 1];
        int n7 = 0;
        int n8 = 0;
        int n9 = n7;
        int n10 = n8;
        array4[n7++] = 1.0f;
        array3[n8++] = 1.0f;
        for (int i = 1; i <= n6; ++i) {
            array4[n7++] = array[i] + array[n + 1 - i] - array4[n9++];
            array3[n8++] = array[i] - array[n + 1 - i] + array3[n10++];
        }
        int n11 = 0;
        int n12 = 0;
        for (int j = 0; j < n6; ++j) {
            array4[n11] *= 2.0f;
            array3[n12] *= 2.0f;
            ++n11;
            ++n12;
        }
        float n13 = 0.0f;
        float n14 = 1.0f;
        for (int k = 0; k < n; ++k) {
            float[] array5;
            if (k % 2 != 0) {
                array5 = array3;
            }
            else {
                array5 = array4;
            }
            float a = a(array5, n14, n);
            int n15 = 1;
            while (n15 == 1 && n13 >= -1.0) {
                float n16 = (float)(n3 * (1.0 - 0.9 * n14 * n14));
                if (Math.abs(a) < 0.2) {
                    n16 *= 0.5;
                }
                n13 = n14 - n16;
                final float a2 = a(array5, n13, n);
                final float n17 = n13;
                if (a2 * a < 0.0) {
                    ++n5;
                    for (int l = 0; l <= n2; ++l) {
                        n4 = (n14 + n13) / 2.0f;
                        final float a3 = a(array5, n4, n);
                        if (a3 * a > 0.0) {
                            a = a3;
                            n14 = n4;
                        }
                        else {
                            n13 = n4;
                        }
                    }
                    array2[k] = n4;
                    n14 = n4;
                    n15 = 0;
                }
                else {
                    a = a2;
                    n14 = n17;
                }
            }
        }
        return n5;
    }
    
    public void a(final float[] array, final float[] array2, final int n) {
        int n2 = 0;
        final int n3 = n / 2;
        for (int i = 0; i < 4 * n3 + 2; ++i) {
            this.a[i] = 0.0f;
        }
        float n4 = 1.0f;
        float n5 = 1.0f;
        for (int j = 0; j <= n; ++j) {
            for (int n6 = 0, k = 0; k < n3; ++k, n6 += 2) {
                final int n7 = k * 4;
                final int n8 = n7 + 1;
                final int n9 = n8 + 1;
                n2 = n9 + 1;
                final float n10 = n4 - 2.0f * array[n6] * this.a[n7] + this.a[n8];
                final float n11 = n5 - 2.0f * array[n6 + 1] * this.a[n9] + this.a[n2];
                this.a[n8] = this.a[n7];
                this.a[n2] = this.a[n9];
                this.a[n7] = n4;
                this.a[n9] = n5;
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
