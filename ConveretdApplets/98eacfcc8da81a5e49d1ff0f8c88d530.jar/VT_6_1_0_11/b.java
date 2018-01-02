// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public abstract class b implements aO
{
    public abstract void a(final float[] p0, final float[] p1, final int p2, final cH p3);
    
    public abstract void a(final float[] p0, final int p1, final cH p2);
    
    protected static void a(final float[] array, final int[] array2, final cH ch, final float n, final int n2, final int n3) {
        final int b = ch.b(6);
        for (int i = 0; i < n2; ++i) {
            final int n4 = i + n3;
            array[n4] += n * array2[b * n2 + i];
        }
    }
    
    protected static int a(final float[] array, int i, final int[] array2, int j, final int n) {
        float n2 = 0.0f;
        int n3 = 0;
        int n4 = 0;
        float n5;
        float n6;
        for (i = 0; i < 64; ++i) {
            n5 = 0.0f;
            for (j = 0; j < n; ++j) {
                n6 = array[j + 0] - array2[n4++];
                n5 += n6 * n6;
            }
            if (n5 < n2 || i == 0) {
                n2 = n5;
                n3 = i;
            }
        }
        int n7;
        for (j = 0; j < n; ++j) {
            n7 = j + 0;
            array[n7] -= array2[n3 * n + j];
        }
        return n3;
    }
    
    protected static int a(final float[] array, final int n, final float[] array2, final int n2, final int[] array3, int i, final int n3) {
        float n4 = 0.0f;
        int n5 = 0;
        int n6 = 0;
        float n7;
        int j;
        float n8;
        for (i = 0; i < 64; ++i) {
            n7 = 0.0f;
            for (j = 0; j < n3; ++j) {
                n8 = array[n + j] - array3[n6++];
                n7 += array2[n2 + j] * n8 * n8;
            }
            if (n7 < n4 || i == 0) {
                n4 = n7;
                n5 = i;
            }
        }
        for (int k = 0; k < n3; ++k) {
            final int n9 = n + k;
            array[n9] -= array3[n5 * n3 + k];
        }
        return n5;
    }
}
