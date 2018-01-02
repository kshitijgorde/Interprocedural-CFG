// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class ae
{
    private int if;
    private float[] do;
    private float a;
    private float[] for;
    
    public ae() {
        this.do = new float[3];
        this.for = new float[1024];
    }
    
    public void a() {
        this.if = 0;
        final float[] do1 = this.do;
        final int n = 0;
        final float[] do2 = this.do;
        final int n2 = 1;
        final float[] do3 = this.do;
        final int n3 = 2;
        final float n4 = 0.0f;
        do3[n3] = n4;
        do1[n] = (do2[n2] = n4);
        this.a = 1.0f;
    }
    
    public static final void a(final float n, final float[] array, final float[] array2, final int n2) {
        float n3 = 1.0f;
        for (int i = 0; i < n2 + 1; ++i) {
            array2[i] = n3 * array[i];
            n3 *= n;
        }
    }
    
    public static final void a(final float[] array, final int n, final float[] array2, final float[] array3, final int n2, final int n3, final float[] array4, final int n4) {
        for (int i = 0; i < n2; ++i) {
            final float n5 = array[n + i];
            array[n + i] = array2[0] * n5 + array4[n4 + 0];
            final float n6 = array[n + i];
            for (int j = 0; j < n3 - 1; ++j) {
                array4[n4 + j] = array4[n4 + j + 1] + array2[j + 1] * n5 - array3[j + 1] * n6;
            }
            array4[n4 + n3 - 1] = array2[n3] * n5 - array3[n3] * n6;
        }
    }
    
    public static final void a(final float[] array, final int n, final float[] array2, final float[] array3, final float[] array4, final int n2, final int n3, final int n4, final float[] array5, final int n5) {
        for (int i = 0; i < n3; ++i) {
            final float n6 = array[n + i];
            array4[n2 + i] = array2[0] * n6 + array5[0];
            final float n7 = array4[n2 + i];
            for (int j = 0; j < n4 - 1; ++j) {
                array5[n5 + j] = array5[n5 + j + 1] + array2[j + 1] * n6 - array3[j + 1] * n7;
            }
            array5[n5 + n4 - 1] = array2[n4] * n6 - array3[n4] * n7;
        }
    }
    
    public static final void if(final float[] array, final int n, final float[] array2, final float[] array3, final int n2, final int n3, final int n4, final float[] array4) {
        for (int i = 0; i < n3; ++i) {
            array3[n2 + i] = array[n + i] + array4[0];
            for (int j = 0; j < n4 - 1; ++j) {
                array4[j] = array4[j + 1] - array2[j + 1] * array3[n2 + i];
            }
            array4[n4 - 1] = -array2[n4] * array3[n2 + i];
        }
    }
    
    public static final void a(final float[] array, final int n, final float[] array2, final float[] array3, final int n2, final int n3, final int n4, final float[] array4) {
        for (int i = 0; i < n3; ++i) {
            final float n5 = array[n + i];
            array3[n2 + i] = array2[0] * n5 + array4[0];
            for (int j = 0; j < n4 - 1; ++j) {
                array4[j] = array4[j + 1] + array2[j + 1] * n5;
            }
            array4[n4 - 1] = array2[n4] * n5;
        }
    }
    
    public static final void a(final float[] array, final int n, final float[] array2, final float[] array3, final float[] array4, final float[] array5, final int n2, final int n3) {
        final float[] array6 = new float[n3];
        a(array, n, array3, array2, array5, 0, n2, n3, array6, 0);
        for (int i = 0; i < n3; ++i) {
            array6[i] = 0.0f;
        }
        if(array5, 0, array4, array5, 0, n2, n3, array6);
    }
    
    public static final void if(final float[] array, final int n, final float[] array2, final float[] array3, final float[] array4, final float[] array5, final int n2, final int n3) {
        final float[] array6 = new float[n3];
        a(array, n, array2, array3, array5, 0, n2, n3, array6, 0);
        for (int i = 0; i < n3; ++i) {
            array6[i] = 0.0f;
        }
        a(array5, 0, array4, array5, 0, n2, n3, array6);
    }
    
    public void a(final float[] array, final float[] array2, final float[] array3, final int n, final int n2, final float[] array4) {
        for (int i = 0; i < n / 2; ++i) {
            this.for[2 * i] = array[n / 2 - 1 - i];
        }
        for (int j = 0; j < n2 - 1; j += 2) {
            this.for[n + j] = array4[j + 1];
        }
        for (int k = 0; k < n; k += 4) {
            float n6;
            float n5;
            float n4;
            float n3 = n4 = (n5 = (n6 = 0.0f));
            float n7 = this.for[n - 4 - k];
            for (int l = 0; l < n2; l += 4) {
                final float n8 = array2[l];
                final float n9 = array2[l + 1];
                final float n10 = this.for[n - 2 + l - k];
                final float n11 = n4 + n8 * n10;
                final float n12 = n3 + n9 * n10;
                final float n13 = n5 + n8 * n7;
                final float n14 = n6 + n9 * n7;
                final float n15 = array2[l + 2];
                final float n16 = array2[l + 3];
                n7 = this.for[n + l - k];
                n4 = n11 + n15 * n7;
                n3 = n12 + n16 * n7;
                n5 = n13 + n15 * n10;
                n6 = n14 + n16 * n10;
            }
            array3[k] = n4;
            array3[k + 1] = n3;
            array3[k + 2] = n5;
            array3[k + 3] = n6;
        }
        for (int n17 = 0; n17 < n2 - 1; n17 += 2) {
            array4[n17 + 1] = this.for[n17];
        }
    }
    
    public void a(final float[] array, final int n, final float[] array2, final int n2, final int n3, final int if1, final float[] array3, float n4) {
        float n5 = 0.0f;
        float n6 = 0.0f;
        for (int i = n; i < n + n3; ++i) {
            n5 += array[i] * array[i];
        }
        final float n7 = 0.5f * Math.abs(array3[0] + array3[1] + array3[2] + this.do[0] + this.do[1] + this.do[2]);
        if (n7 > 1.3f) {
            n4 *= 1.3f / n7;
        }
        if (n7 < 0.5f) {
            n4 *= 2.0f * n7;
        }
        final float n8 = 1.0f / n3;
        float n9 = 0.0f;
        for (int j = 0, n10 = n; j < n3; ++j, ++n10) {
            n9 += n8;
            array2[n2 + j] = array[n10] + n4 * n9 * (array3[0] * array[n10 - if1 + 1] + array3[1] * array[n10 - if1] + array3[2] * array[n10 - if1 - 1]) + n4 * (1.0f - n9) * (this.do[0] * array[n10 - this.if + 1] + this.do[1] * array[n10 - this.if] + this.do[2] * array[n10 - this.if - 1]);
        }
        this.do[0] = array3[0];
        this.do[1] = array3[1];
        this.do[2] = array3[2];
        this.if = if1;
        for (int k = n2; k < n2 + n3; ++k) {
            n6 += array2[k] * array2[k];
        }
        float n11 = (float)Math.sqrt(n5 / (0.1f + n6));
        if (n11 < 0.5f) {
            n11 = 0.5f;
        }
        if (n11 > 1.0f) {
            n11 = 1.0f;
        }
        for (int l = n2; l < n2 + n3; ++l) {
            this.a = 0.96f * this.a + 0.04f * n11;
            final int n12 = l;
            array2[n12] *= this.a;
        }
    }
    
    public static final void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n, final int n2, final float[] array5) {
        final float[] array6 = new float[n2];
        final float[] array7 = new float[n + n2 - 1];
        final int n3 = n2 - 1;
        final int n4 = n2 >> 1;
        for (int i = 0; i < n2; ++i) {
            array6[n2 - i - 1] = array2[i];
        }
        for (int j = 0; j < n2 - 1; ++j) {
            array7[j] = array5[n2 - j - 2];
        }
        for (int k = 0; k < n; ++k) {
            array7[k + n2 - 1] = array[k];
        }
        for (int l = 0, n5 = 0; l < n; l += 2, ++n5) {
            array4[n5] = (array3[n5] = 0.0f);
            int n9;
            int n10;
            for (int n6 = 0; n6 < n4; ++n6, n9 = n5, array3[n9] += array6[n6] * (array7[l + n6] + array7[n3 + l - n6]), n10 = n5, array4[n10] += array6[n6] * (array7[l + n6] - array7[n3 + l - n6]), ++n6) {
                final int n7 = n5;
                array3[n7] += array6[n6] * (array7[l + n6] + array7[n3 + l - n6]);
                final int n8 = n5;
                array4[n8] -= array6[n6] * (array7[l + n6] - array7[n3 + l - n6]);
            }
        }
        for (int n11 = 0; n11 < n2 - 1; ++n11) {
            array5[n11] = array[n - n11 - 1];
        }
    }
}
