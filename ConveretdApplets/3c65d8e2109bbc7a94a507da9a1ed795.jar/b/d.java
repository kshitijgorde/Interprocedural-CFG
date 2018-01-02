// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class d
{
    public static void a(final float[] array, final float[] array2, final float[] array3, final float[] array4) {
        float n = 0.01f;
        for (int i = 0; i < 40; ++i) {
            n += array3[i] * array3[i];
        }
        array4[2] = n;
        float n2 = 0.01f;
        for (int j = 0; j < 40; ++j) {
            n2 += array[j] * array3[j];
        }
        array4[3] = -2.0f * n2;
        float n3 = 0.01f;
        for (int k = 0; k < 40; ++k) {
            n3 += array2[k] * array3[k];
        }
        array4[4] = 2.0f * n3;
    }
    
    public static void a(final float[] array, final float[] array2, final float[] array3) {
        for (int i = 0; i < 40; ++i) {
            float n = 0.0f;
            for (int j = i; j < 40; ++j) {
                n += array2[j] * array[j - i];
            }
            array3[i] = n;
        }
    }
}
