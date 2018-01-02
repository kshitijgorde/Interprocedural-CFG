// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class r
{
    public static final int a(final float n, final float[] array, final int n2) {
        float n3 = 0.0f;
        int n4 = 0;
        for (int i = 0; i < n2; ++i) {
            final float n5 = n - array[i];
            final float n6 = n5 * n5;
            if (i == 0 || n6 < n3) {
                n3 = n6;
                n4 = i;
            }
        }
        return n4;
    }
    
    public static final int a(final float[] array, final float[] array2, final int n, final int n2) {
        int n3 = 0;
        float n4 = 0.0f;
        int n5 = 0;
        for (int i = 0; i < n2; ++i) {
            float n6 = 0.0f;
            for (int j = 0; j < n; ++j) {
                final float n7 = array[j] - array2[n3++];
                n6 += n7 * n7;
            }
            if (i == 0 || n6 < n4) {
                n4 = n6;
                n5 = i;
            }
        }
        return n5;
    }
    
    public static final void a(final float[] array, final int n, final float[] array2, final int n2, final int n3, final float[] array3, final int n4, final int[] array4, final float[] array5) {
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < n3; ++i) {
            float n7 = 0.5f * array3[i];
            for (int j = 0; j < n2; ++j) {
                n7 -= array[n + j] * array2[n5++];
            }
            if (i < n4 || n7 < array5[n4 - 1]) {
                int n8;
                for (n8 = n4 - 1; n8 >= 1 && (n8 > n6 || n7 < array5[n8 - 1]); --n8) {
                    array5[n8] = array5[n8 - 1];
                    array4[n8] = array4[n8 - 1];
                }
                array5[n8] = n7;
                array4[n8] = i;
                ++n6;
            }
        }
    }
    
    public static final void if(final float[] array, final int n, final float[] array2, final int n2, final int n3, final float[] array3, final int n4, final int[] array4, final float[] array5) {
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < n3; ++i) {
            float n7 = 0.0f;
            for (int j = 0; j < n2; ++j) {
                n7 -= array[n + j] * array2[n5++];
            }
            boolean b;
            if (n7 > 0.0f) {
                b = true;
                n7 = -n7;
            }
            else {
                b = false;
            }
            final float n8 = (float)(n7 + 0.5 * array3[i]);
            if (i < n4 || n8 < array5[n4 - 1]) {
                int n9;
                for (n9 = n4 - 1; n9 >= 1 && (n9 > n6 || n8 < array5[n9 - 1]); --n9) {
                    array5[n9] = array5[n9 - 1];
                    array4[n9] = array4[n9 - 1];
                }
                array5[n9] = n8;
                array4[n9] = i;
                ++n6;
                if (b) {
                    final int n10 = n9;
                    array4[n10] += n3;
                }
            }
        }
    }
}
