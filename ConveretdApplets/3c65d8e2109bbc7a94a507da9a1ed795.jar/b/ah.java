// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class ah
{
    public static void if(final float[] array, final int n, final float[] array2, final int n2, final float[] array3, final int n3, final int n4) {
        for (int i = 0; i < n4; ++i) {
            float n5 = 0.0f;
            for (int j = 0; j <= i; ++j) {
                n5 += array[n + j] * array2[n2 + i - j];
            }
            array3[n3 + i] = n5;
        }
    }
    
    public static void a(final float[] array, final int n, final float[] array2, int n2, final float[] array3, int n3, final int n4, final float[] array4, int n5, final int n6) {
        final float[] array5 = new float[50];
        int n7 = 0;
        for (int i = 0; i < 10; ++i) {
            array5[n7++] = array4[n5++];
        }
        for (int j = 0; j < n4; ++j) {
            int n8 = n7;
            int n9 = 0;
            double n10 = array2[n2++];
            for (int k = 0; k < 10; ++k) {
                n10 -= array[n + ++n9] * array5[--n8];
            }
            array5[n7++] = (float)n10;
            array3[n3++] = (float)n10;
        }
        if (n6 != 0) {
            for (int l = 0; l < 10; ++l) {
                array4[--n5] = array5[--n7];
            }
        }
    }
    
    public static void a(final float[] array, final int n, final float[] array2, final int n2, final float[] array3, final int n3, final int n4) {
        int n5 = 0;
        for (int i = 0; i < n4; ++i) {
            double n6 = array2[n2 + i];
            for (int j = 1; j <= 10; ++j) {
                n6 += array[n + j] * array2[n2 + i - j];
            }
            array3[n3 + n5++] = (float)n6;
        }
    }
}
