// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class ac extends o
{
    public final void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n, final int n2, final float[] array5, final int n3, final float[] array6, final ad ad, final int n4) {
        final float[] array7 = new float[n2];
        ae.if(array, 0, array2, array3, array4, array7, n2, n);
        for (int i = 0; i < n2; ++i) {
            final int n5 = n3 + i;
            array5[n5] += array7[i];
        }
        for (int j = 0; j < n2; ++j) {
            array[j] = 0.0f;
        }
    }
    
    public final void a(final float[] array, final int n, final int n2, final ad ad) {
        for (int i = 0; i < n2; ++i) {
            final int n3 = n + i;
            array[n3] += (float)(3.0 * (Math.random() - 0.5));
        }
    }
}
