// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public abstract class z implements ab
{
    public static final int l = 20;
    
    public abstract void a(final float[] p0, final float[] p1, final int p2, final ad p3);
    
    public abstract void a(final float[] p0, final int p1, final ad p2);
    
    protected void a(final float[] array, final int[] array2, final ad ad, final float n, final int n2, final int n3) {
        final int a = ad.a(6);
        for (int i = 0; i < n2; ++i) {
            final int n4 = i + n3;
            array[n4] += n * array2[a * n2 + i];
        }
    }
    
    protected static int a(final float[] array, final int n, final int[] array2, final int n2, final int n3) {
        float n4 = 0.0f;
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < n2; ++i) {
            float n7 = 0.0f;
            for (int j = 0; j < n3; ++j) {
                final float n8 = array[n + j] - array2[n6++];
                n7 += n8 * n8;
            }
            if (n7 < n4 || i == 0) {
                n4 = n7;
                n5 = i;
            }
        }
        for (int k = 0; k < n3; ++k) {
            final int n9 = n + k;
            array[n9] -= array2[n5 * n3 + k];
        }
        return n5;
    }
    
    protected static int a(final float[] array, final int n, final float[] array2, final int n2, final int[] array3, final int n3, final int n4) {
        float n5 = 0.0f;
        int n6 = 0;
        int n7 = 0;
        for (int i = 0; i < n3; ++i) {
            float n8 = 0.0f;
            for (int j = 0; j < n4; ++j) {
                final float n9 = array[n + j] - array3[n7++];
                n8 += array2[n2 + j] * n9 * n9;
            }
            if (n8 < n5 || i == 0) {
                n5 = n8;
                n6 = i;
            }
        }
        for (int k = 0; k < n4; ++k) {
            final int n10 = n + k;
            array[n10] -= array3[n6 * n4 + k];
        }
        return n6;
    }
}
