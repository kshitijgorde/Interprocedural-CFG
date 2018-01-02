// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public abstract class bI
{
    public abstract int a(final float[] p0, final float[] p1, final int p2, final float[] p3, final float[] p4, final float[] p5, final float[] p6, final int p7, final int p8, final int p9, final float p10, final int p11, final int p12, final cH p13, final float[] p14, final int p15, final float[] p16, final int p17);
    
    public abstract int a(final float[] p0, final int p1, final int p2, final float p3, final int p4, final float[] p5, final cH p6, final int p7, final int p8, final float p9);
    
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
    
    protected static void a(final float[] array, int i, final int n, final int n2, final int n3, final int[] array2, final float[] array3, final int n4) {
        final float[] array4 = new float[n4];
        final float[] array5 = new float[n2 - n + 1];
        final float[] array6 = new float[n2 - n + 2];
        final float[] array7 = new float[n2 - n + 1];
        for (int j = 0; j < n4; ++j) {
            array4[j] = -1.0f;
            array3[j] = 0.0f;
            array2[j] = n;
        }
        array6[0] = a(array, i - n, array, i - n, n3);
        final float a = a(array, i, array, i, n3);
        for (int k = n; k <= n2; ++k) {
            array6[k - n + 1] = array6[k - n] + array[i - k - 1] * array[i - k - 1] - array[i - k + n3 - 1] * array[i - k + n3 - 1];
            if (array6[k - n + 1] < 1.0f) {
                array6[k - n + 1] = 1.0f;
            }
        }
        for (int l = n; l <= n2; ++l) {
            array7[l - n] = (array5[l - n] = 0.0f);
        }
        for (int n5 = n; n5 <= n2; ++n5) {
            array5[n5 - n] = a(array, i, array, i - n5, n3);
            array7[n5 - n] = array5[n5 - n] * array5[n5 - n] / (array6[n5 - n] + 1.0f);
        }
        for (int n6 = n; n6 <= n2; ++n6) {
            if (array7[n6 - n] > array4[n4 - 1]) {
                final float n8;
                float n7;
                if ((n7 = (float)Math.sqrt((n8 = array5[n6 - n] / (array6[n6 - n] + 10.0f)) * array5[n6 - n] / (a + 10.0f))) > n8) {
                    n7 = n8;
                }
                if (n7 < 0.0f) {
                    n7 = 0.0f;
                }
                for (int n9 = 0; n9 < n4; ++n9) {
                    if (array7[n6 - n] > array4[n9]) {
                        for (i = n4 - 1; i > n9; --i) {
                            array4[i] = array4[i - 1];
                            array2[i] = array2[i - 1];
                            array3[i] = array3[i - 1];
                        }
                        array4[n9] = array7[n6 - n];
                        array2[n9] = n6;
                        array3[n9] = n7;
                        break;
                    }
                }
            }
        }
    }
}
