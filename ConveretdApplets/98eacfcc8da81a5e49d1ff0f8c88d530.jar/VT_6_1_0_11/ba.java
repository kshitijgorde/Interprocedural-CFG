// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class ba
{
    private int a;
    private float[] b;
    private float c;
    private float[] d;
    
    public ba() {
        this.b = new float[3];
        this.d = new float[1024];
    }
    
    public final void a() {
        this.a = 0;
        final float[] b = this.b;
        final int n = 0;
        final float[] b2 = this.b;
        final int n2 = 1;
        final float[] b3 = this.b;
        final int n3 = 2;
        final float n4 = 0.0f;
        b3[n3] = n4;
        b[n] = (b2[n2] = n4);
        this.c = 1.0f;
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
            array[n + i] = array2[0] * n5 + array4[n4];
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
    
    public static final void a(final float[] array, final int n, final float[] array2, final float[] array3, final int n2, final int n3, final int n4, final float[] array4) {
        for (int i = 0; i < n3; ++i) {
            array3[n2 + i] = array[n + i] + array4[0];
            for (int j = 0; j < n4 - 1; ++j) {
                array4[j] = array4[j + 1] - array2[j + 1] * array3[n2 + i];
            }
            array4[n4 - 1] = -array2[n4] * array3[n2 + i];
        }
    }
    
    public static final void b(final float[] array, final int n, final float[] array2, final float[] array3, final int n2, final int n3, final int n4, final float[] array4) {
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
        a(array5, 0, array4, array5, 0, n2, n3, array6);
    }
    
    public static final void b(final float[] array, final int n, final float[] array2, final float[] array3, final float[] array4, final float[] array5, final int n2, final int n3) {
        final float[] array6 = new float[n3];
        a(array, 0, array2, array3, array5, 0, n2, n3, array6, 0);
        for (int i = 0; i < n3; ++i) {
            array6[i] = 0.0f;
        }
        b(array5, 0, array4, array5, 0, n2, n3, array6);
    }
    
    public final void a(final float[] array, final float[] array2, final float[] array3, final int n, int i, final float[] array4) {
        for (i = 0; i < n / 2; ++i) {
            this.d[i * 2] = array[n / 2 - 1 - i];
        }
        for (i = 0; i < 63; i += 2) {
            this.d[n + i] = array4[i + 1];
        }
        float n5;
        float n4;
        float n3;
        float n2;
        float n6;
        int j;
        float n7;
        float n8;
        float n9;
        float n10;
        float n11;
        float n12;
        float n13;
        float n14;
        float n15;
        for (i = 0; i < n; i += 4) {
            n2 = (n3 = (n4 = (n5 = 0.0f)));
            n6 = this.d[n - 4 - i];
            for (j = 0; j < 64; j += 4) {
                n7 = array2[j];
                n8 = array2[j + 1];
                n9 = this.d[n - 2 + j - i];
                n10 = n3 + n7 * n9;
                n11 = n2 + n8 * n9;
                n12 = n4 + n7 * n6;
                n13 = n5 + n8 * n6;
                n14 = array2[j + 2];
                n15 = array2[j + 3];
                n6 = this.d[n + j - i];
                n3 = n10 + n14 * n6;
                n2 = n11 + n15 * n6;
                n4 = n12 + n14 * n9;
                n5 = n13 + n15 * n9;
            }
            array3[i] = n3;
            array3[i + 1] = n2;
            array3[i + 2] = n4;
            array3[i + 3] = n5;
        }
        for (i = 0; i < 63; i += 2) {
            array4[i + 1] = this.d[i];
        }
    }
    
    public final void a(final float[] array, int n, final float[] array2, final int n2, final int n3, final int a, final float[] array3, float n4) {
        float n5 = 0.0f;
        float n6 = 0.0f;
        for (int i = n; i < n + n3; ++i) {
            n5 += array[i] * array[i];
        }
        final float n7;
        if ((n7 = 0.5f * Math.abs(array3[0] + array3[1] + array3[2] + this.b[0] + this.b[1] + this.b[2])) > 1.3f) {
            n4 *= 1.3f / n7;
        }
        if (n7 < 0.5f) {
            n4 *= n7 * 2.0f;
        }
        final float n8 = 1.0f / n3;
        float n9 = 0.0f;
        int j;
        for (j = 0, n = n; j < n3; ++j, ++n) {
            n9 += n8;
            array2[n2 + j] = array[n] + n4 * n9 * (array3[0] * array[n - a + 1] + array3[1] * array[n - a] + array3[2] * array[n - a - 1]) + n4 * (1.0f - n9) * (this.b[0] * array[n - this.a + 1] + this.b[1] * array[n - this.a] + this.b[2] * array[n - this.a - 1]);
        }
        this.b[0] = array3[0];
        this.b[1] = array3[1];
        this.b[2] = array3[2];
        this.a = a;
        for (int k = n2; k < n2 + n3; ++k) {
            n6 += array2[k] * array2[k];
        }
        float n10;
        if ((n10 = (float)Math.sqrt(n5 / (n6 + 0.1f))) < 0.5f) {
            n10 = 0.5f;
        }
        if (n10 > 1.0f) {
            n10 = 1.0f;
        }
        for (int l = n2; l < n2 + n3; ++l) {
            this.c = 0.96f * this.c + 0.04f * n10;
            final int n11 = l;
            array2[n11] *= this.c;
        }
    }
}
