// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class w
{
    public static void a(final float[] array, final float[] array2, final int n, final g g) {
        final float n2 = 36.0f;
        float n3 = 0.01f;
        for (int i = 0; i < n; ++i) {
            n3 += array2[i] * array2[i];
        }
        float n4 = n2 - 10.0f * (float)Math.log10(n3 / n);
        for (int j = 0; j < 4; ++j) {
            n4 += c.n[j] * array[j];
        }
        g.a = new Float(n4);
        g.a = new Float((float)Math.pow(10.0, g.a / 20.0));
    }
    
    public static void a(final float[] array, final float n) {
        for (int i = 3; i > 0; --i) {
            array[i] = array[i - 1];
        }
        array[0] = 20.0f * (float)Math.log10(n);
    }
    
    public static void a(final float[] array) {
        float n = 0.0f;
        for (int i = 0; i < 4; ++i) {
            n += array[i];
        }
        float n2 = n * 0.25f - 4.0f;
        if (n2 < -14.0f) {
            n2 = -14.0f;
        }
        for (int j = 3; j > 0; --j) {
            array[j] = array[j - 1];
        }
        array[0] = n2;
    }
}
