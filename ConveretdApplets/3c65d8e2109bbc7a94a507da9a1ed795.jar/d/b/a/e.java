// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public abstract class e
{
    public abstract int a(final float[] p0, final float[] p1, final int p2, final float[] p3, final float[] p4, final float[] p5, final float[] p6, final int p7, final int p8, final int p9, final float p10, final int p11, final int p12, final ad p13, final float[] p14, final int p15, final float[] p16, final int p17);
    
    public abstract int a(final float[] p0, final int p1, final int p2, final float p3, final int p4, final float[] p5, final ad p6, final int p7, final int p8, final float p9);
    
    protected static float a(final float[] array, final int n, final float[] array2, final int n2, final int n3) {
        float n4 = 0.0f;
        float n5 = 0.0f;
        float n6 = 0.0f;
        float n7 = 0.0f;
        for (int i = 0; i < n3; i += 4) {
            n4 += array[n + i] * array2[n2 + i];
            n5 += array[n + i + 1] * array2[n2 + i + 1];
            n6 += array[n + i + 2] * array2[n2 + i + 2];
            n7 += array[n + i + 3] * array2[n2 + i + 3];
        }
        return n4 + n5 + n6 + n7;
    }
    
    protected static void a(final float[] array, final int n, final int n2, final int n3, final int n4, final int[] array2, final float[] array3, final int n5) {
        final float[] array4 = new float[n5];
        final float[] array5 = new float[n3 - n2 + 1];
        final float[] array6 = new float[n3 - n2 + 2];
        final float[] array7 = new float[n3 - n2 + 1];
        for (int i = 0; i < n5; ++i) {
            array4[i] = -1.0f;
            array3[i] = 0.0f;
            array2[i] = n2;
        }
        array6[0] = a(array, n - n2, array, n - n2, n4);
        final float a = a(array, n, array, n, n4);
        for (int j = n2; j <= n3; ++j) {
            array6[j - n2 + 1] = array6[j - n2] + array[n - j - 1] * array[n - j - 1] - array[n - j + n4 - 1] * array[n - j + n4 - 1];
            if (array6[j - n2 + 1] < 1.0f) {
                array6[j - n2 + 1] = 1.0f;
            }
        }
        for (int k = n2; k <= n3; ++k) {
            array7[k - n2] = (array5[k - n2] = 0.0f);
        }
        for (int l = n2; l <= n3; ++l) {
            array5[l - n2] = a(array, n, array, n - l, n4);
            array7[l - n2] = array5[l - n2] * array5[l - n2] / (array6[l - n2] + 1.0f);
        }
        for (int n6 = n2; n6 <= n3; ++n6) {
            if (array7[n6 - n2] > array4[n5 - 1]) {
                final float n7 = array5[n6 - n2] / (array6[n6 - n2] + 10.0f);
                float n8 = (float)Math.sqrt(n7 * array5[n6 - n2] / (a + 10.0f));
                if (n8 > n7) {
                    n8 = n7;
                }
                if (n8 < 0.0f) {
                    n8 = 0.0f;
                }
                for (int n9 = 0; n9 < n5; ++n9) {
                    if (array7[n6 - n2] > array4[n9]) {
                        for (int n10 = n5 - 1; n10 > n9; --n10) {
                            array4[n10] = array4[n10 - 1];
                            array2[n10] = array2[n10 - 1];
                            array3[n10] = array3[n10 - 1];
                        }
                        array4[n9] = array7[n6 - n2];
                        array2[n9] = n6;
                        array3[n9] = n8;
                        break;
                    }
                }
            }
        }
    }
}
