// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class ag
{
    public static void if(final float[] array, final int n, final float[] array2, final int n2) {
        final float[] array3 = new float[6];
        final float[] array4 = new float[6];
        a(array, n, array3, 0);
        a(array, n + 1, array4, 0);
        for (int i = 5; i > 0; --i) {
            final float[] array5 = array3;
            final int n3 = i;
            array5[n3] += array3[i - 1];
            final float[] array6 = array4;
            final int n4 = i;
            array6[n4] -= array4[i - 1];
        }
        array2[n2 + 0] = 1.0f;
        for (int j = 1, n5 = 10; j <= 5; ++j, --n5) {
            array2[n2 + j] = 0.5f * (array3[j] + array4[j]);
            array2[n2 + n5] = 0.5f * (array3[j] - array4[j]);
        }
    }
    
    public static void a(final float[] array, final int n, final float[] array2, final int n2) {
        array2[n2 + 0] = 1.0f;
        array2[n2 + 1] = -2.0f * array[n + 0];
        for (int i = 2; i <= 5; ++i) {
            final float n3 = -2.0f * array[n + 2 * i - 2];
            array2[i] = n3 * array2[n2 + i - 1] + 2.0f * array2[n2 + i - 2];
            for (int j = i - 1; j > 1; --j) {
                final int n4 = n2 + j;
                array2[n4] += n3 * array2[n2 + j - 1] + array2[n2 + j - 2];
            }
            final int n5 = n2 + 1;
            array2[n5] += n3;
        }
    }
    
    public static void if(final float[] array, final float[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            array2[i] = (float)Math.cos(array[i]);
        }
    }
    
    public static void a(final float[] array, final float[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            array2[i] = (float)Math.acos(array[i]);
        }
    }
    
    public static void a(final float[] array, final int n, final float n2, final int n3, final float[] array2, final int n4) {
        array2[n4] = array[n];
        float n5 = n2;
        for (int i = 1; i < n3; ++i) {
            array2[n4 + i] = n5 * array[n + i];
            n5 *= n2;
        }
        array2[n4 + n3] = n5 * array[n + n3];
    }
    
    public static void a(final float[] array, final float[] array2, final float[] array3) {
        final float[] array4 = new float[10];
        for (int i = 0; i < 10; ++i) {
            array4[i] = array[i] * 0.5f + array2[i] * 0.5f;
        }
        if(array4, 0, array3, 0);
        if(array2, 0, array3, 11);
    }
    
    public static void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final float[] array5) {
        final float[] array6 = new float[10];
        for (int i = 0; i < 10; ++i) {
            array6[i] = array[i] * 0.5f + array2[i] * 0.5f;
        }
        if(array6, 0, array5, 0);
        a(array6, array3, 10);
        a(array2, array4, 10);
    }
}
