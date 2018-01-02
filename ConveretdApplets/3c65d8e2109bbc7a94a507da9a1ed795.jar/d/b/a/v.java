// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class v extends z
{
    public final void a(final float[] array, final float[] array2, final int n, final ad ad) {
        final float[] array3 = new float[20];
        for (int i = 0; i < n; ++i) {
            array2[i] = array[i];
        }
        array3[0] = 1.0f / (array2[1] - array2[0]);
        array3[n - 1] = 1.0f / (array2[n - 1] - array2[n - 2]);
        for (int j = 1; j < n - 1; ++j) {
            final float n2 = 1.0f / (array2[j] - array2[j - 1]);
            final float n3 = 1.0f / (array2[j + 1] - array2[j]);
            array3[j] = ((n2 > n3) ? n2 : n3);
        }
        for (int k = 0; k < n; ++k) {
            final int n4 = k;
            array2[n4] -= (float)(0.3125 * k + 0.75);
        }
        for (int l = 0; l < n; ++l) {
            final int n5 = l;
            array2[n5] *= 256.0f;
        }
        ad.a(z.a(array2, 0, ab.if, 64, n), 6);
        for (int n6 = 0; n6 < n; ++n6) {
            final int n7 = n6;
            array2[n7] *= 2.0f;
        }
        ad.a(z.a(array2, 0, array3, 0, ab.d, 64, n), 6);
        for (int n8 = 0; n8 < n; ++n8) {
            final int n9 = n8;
            array2[n9] *= 0.0019531;
        }
        for (int n10 = 0; n10 < n; ++n10) {
            array2[n10] = array[n10] - array2[n10];
        }
    }
    
    public final void a(final float[] array, final int n, final ad ad) {
        for (int i = 0; i < n; ++i) {
            array[i] = 0.3125f * i + 0.75f;
        }
        this.a(array, ab.if, ad, 0.0039062f, n, 0);
        this.a(array, ab.d, ad, 0.0019531f, n, 0);
    }
}
