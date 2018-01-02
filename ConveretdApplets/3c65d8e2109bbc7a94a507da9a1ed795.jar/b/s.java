// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class s
{
    int a;
    float[] if;
    
    public s() {
        this.a = 1;
        this.if = new float[] { 0.0f, 0.0f };
    }
    
    void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final float[] array5) {
        final float[] array6 = new float[4];
        final int n = 2;
        for (int i = 0; i < 2; ++i) {
            array6[n + i] = (float)Math.log10((1.0f + array5[i]) / (1.0f - array5[i]));
        }
        for (int j = 0; j < 2; ++j) {
            array6[j] = 0.5f * (array6[n + j] + this.if[j]);
            this.if[j] = array6[n + j];
        }
        for (int k = 0; k < 2; ++k) {
            final float n2 = array6[2 * k];
            final float n3 = array6[2 * k + 1];
            if (this.a != 0) {
                if (n2 < -1.74f && n3 > 0.65f) {
                    this.a = 0;
                }
            }
            else if (n2 > -1.52f || n3 < 0.43f) {
                this.a = 1;
            }
            if (this.a == 0) {
                array[k] = 0.98f;
                float[] array7;
                if (k == 0) {
                    array7 = array3;
                }
                else {
                    array7 = array4;
                }
                float n4 = array7[1] - array7[0];
                for (int l = 1; l < 9; ++l) {
                    final float n5 = array7[l + 1] - array7[l];
                    if (n5 < n4) {
                        n4 = n5;
                    }
                }
                array2[k] = -6.0f * n4 + 1.0f;
                if (array2[k] > 0.7f) {
                    array2[k] = 0.7f;
                }
                if (array2[k] < 0.4f) {
                    array2[k] = 0.4f;
                }
            }
            else {
                array[k] = 0.94f;
                array2[k] = 0.6f;
            }
        }
    }
}
