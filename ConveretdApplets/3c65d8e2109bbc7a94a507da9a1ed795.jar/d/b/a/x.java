// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class x extends e
{
    public final int a(final float[] array, final float[] array2, final int n, final float[] array3, final float[] array4, final float[] array5, final float[] array6, final int n2, final int n3, final int n4, float n5, final int n6, final int n7, final ad ad, final float[] array7, final int n8, final float[] array8, final int n9) {
        if (n5 > 0.99f) {
            n5 = 0.99f;
        }
        for (int i = 0; i < n7; ++i) {
            array6[n2 + i] = array6[n2 + i - n3] * n5;
        }
        return n3;
    }
    
    public final int a(final float[] array, final int n, final int n2, float n3, final int n4, final float[] array2, final ad ad, final int n5, final int n6, final float n7) {
        if (n3 > 0.99f) {
            n3 = 0.99f;
        }
        for (int i = 0; i < n4; ++i) {
            array[n + i] = array[n + i - n2] * n3;
        }
        array2[0] = (array2[2] = 0.0f);
        array2[1] = n3;
        return n2;
    }
}
