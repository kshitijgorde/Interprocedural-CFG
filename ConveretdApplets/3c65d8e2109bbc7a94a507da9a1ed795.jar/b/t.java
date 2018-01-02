// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class t
{
    public static float[] a(final float[] array, final int n) {
        final float[] array2 = new float[array.length - n];
        for (int i = n; i < array.length; ++i) {
            array2[i - n] = array[i];
        }
        return array2;
    }
    
    public static void a(final float[] array, final int n, final float[] array2) {
        for (int i = n; i < array.length; ++i) {
            array[i] = array2[i - n];
        }
    }
}
