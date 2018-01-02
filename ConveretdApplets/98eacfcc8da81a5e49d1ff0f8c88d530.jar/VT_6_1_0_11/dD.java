// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class dD extends bg
{
    public final void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n, final int n2, final float[] array5, final int n3, float[] array6, final cH ch, final int n4) {
        array6 = new float[n2];
        ba.b(array, 0, array2, array3, array4, array6, n2, n);
        for (int i = 0; i < n2; ++i) {
            final int n5 = n3 + i;
            array5[n5] += array6[i];
        }
        for (int j = 0; j < n2; ++j) {
            array[j] = 0.0f;
        }
    }
    
    public final void a(final float[] array, final int n, final int n2, final cH ch) {
        for (int i = 0; i < n2; ++i) {
            final int n3 = n + i;
            array[n3] += (float)(3.0 * (Math.random() - 0.5));
        }
    }
}
