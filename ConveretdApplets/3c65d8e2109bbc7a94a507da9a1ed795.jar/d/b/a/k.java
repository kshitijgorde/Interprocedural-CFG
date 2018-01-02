// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class k extends z
{
    public final void a(final float[] array, final float[] array2, final int n, final ad ad) {
        final float[] array3 = new float[20];
        for (int i = 0; i < n; ++i) {
            array2[i] = array[i];
        }
        array3[0] = 1.0f / (array2[1] - array2[0]);
        array3[n - 1] = 1.0f / (array2[n - 1] - array2[n - 2]);
        for (int j = 1; j < n - 1; ++j) {
            final float n2 = 1.0f / ((0.15f + array2[j] - array2[j - 1]) * (0.15f + array2[j] - array2[j - 1]));
            final float n3 = 1.0f / ((0.15f + array2[j + 1] - array2[j]) * (0.15f + array2[j + 1] - array2[j]));
            array3[j] = ((n2 > n3) ? n2 : n3);
        }
        for (int k = 0; k < n; ++k) {
            final int n4 = k;
            array2[n4] -= (float)(0.25 * k + 0.25);
        }
        for (int l = 0; l < n; ++l) {
            final int n5 = l;
            array2[n5] *= 256.0f;
        }
        ad.a(z.a(array2, 0, ab.b, 64, n), 6);
        for (int n6 = 0; n6 < n; ++n6) {
            final int n7 = n6;
            array2[n7] *= 2.0f;
        }
        ad.a(z.a(array2, 0, array3, 0, ab.try, 64, 5), 6);
        for (int n8 = 0; n8 < 5; ++n8) {
            final int n9 = n8;
            array2[n9] *= 2.0f;
        }
        ad.a(z.a(array2, 0, array3, 0, ab.int, 64, 5), 6);
        ad.a(z.a(array2, 5, array3, 5, ab.goto, 64, 5), 6);
        for (int n10 = 5; n10 < 10; ++n10) {
            final int n11 = n10;
            array2[n11] *= 2.0f;
        }
        ad.a(z.a(array2, 5, array3, 5, ab.else, 64, 5), 6);
        for (int n12 = 0; n12 < n; ++n12) {
            final int n13 = n12;
            array2[n13] *= 9.7656E-4;
        }
        for (int n14 = 0; n14 < n; ++n14) {
            array2[n14] = array[n14] - array2[n14];
        }
    }
    
    public final void a(final float[] array, final int n, final ad ad) {
        for (int i = 0; i < n; ++i) {
            array[i] = 0.25f * i + 0.25f;
        }
        this.a(array, ab.b, ad, 0.0039062f, 10, 0);
        this.a(array, ab.try, ad, 0.0019531f, 5, 0);
        this.a(array, ab.int, ad, 9.7656E-4f, 5, 0);
        this.a(array, ab.goto, ad, 0.0019531f, 5, 5);
        this.a(array, ab.else, ad, 9.7656E-4f, 5, 5);
    }
}
