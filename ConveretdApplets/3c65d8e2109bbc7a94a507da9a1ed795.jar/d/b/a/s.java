// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class s
{
    public static float[] a(final int n, final int n2) {
        final int n3 = n2 * 7 / 2;
        final int n4 = n2 * 5 / 2;
        final float[] array = new float[n];
        for (int i = 0; i < n3; ++i) {
            array[i] = (float)(0.54 - 0.46 * Math.cos(3.141592653589793 * i / n3));
        }
        for (int j = 0; j < n4; ++j) {
            array[n3 + j] = (float)(0.54 + 0.46 * Math.cos(3.141592653589793 * j / n4));
        }
        return array;
    }
    
    public static float[] a(final int n, final float n2) {
        final float[] array = new float[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            array[i] = (float)Math.exp(-0.5 * (6.283185307179586 * n2 * i) * (6.283185307179586 * n2 * i));
        }
        return array;
    }
}
