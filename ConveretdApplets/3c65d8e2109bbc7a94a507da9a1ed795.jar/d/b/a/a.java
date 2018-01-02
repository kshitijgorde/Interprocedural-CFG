// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class a
{
    public static float a(final float[] array, final float[] array2, final float[] array3, final int n) {
        float n2 = array2[0];
        if (array2[0] == 0.0f) {
            for (int i = 0; i < n; ++i) {
                array3[i] = 0.0f;
            }
            return 0.0f;
        }
        for (int j = 0; j < n; ++j) {
            float n3 = -array2[j + 1];
            for (int k = 0; k < j; ++k) {
                n3 -= array[k] * array2[j - k];
            }
            final float n4 = array3[j] = n3 / n2;
            array[j] = n4;
            int l;
            for (l = 0; l < j / 2; ++l) {
                final float n5 = array[l];
                final int n6 = l;
                array[n6] += n4 * array[j - 1 - l];
                final int n7 = j - 1 - l;
                array[n7] += n4 * n5;
            }
            if (j % 2 != 0) {
                final int n8 = l;
                array[n8] += array[l] * n4;
            }
            n2 *= (float)(1.0 - n4 * n4);
        }
        return n2;
    }
    
    public static void a(final float[] array, final float[] array2, int n, final int n2) {
        while (n-- > 0) {
            int i = n;
            float n3 = 0.0f;
            while (i < n2) {
                n3 += array[i] * array[i - n];
                ++i;
            }
            array2[n] = n3;
        }
    }
}
