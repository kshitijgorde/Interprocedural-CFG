// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class h
{
    float[][] if;
    float[] a;
    
    public h() {
        this.if = new float[4][10];
        this.a = new float[] { 0.285599f, 0.571199f, 0.856798f, 1.142397f, 1.427997f, 1.713596f, 1.999195f, 2.284795f, 2.570394f, 2.855993f };
    }
    
    public void a(final float[] array, final float[] array2, final int[] array3) {
        final float[] array4 = new float[10];
        final float[] array5 = new float[10];
        for (int i = 0; i < 10; ++i) {
            array4[i] = (float)Math.acos(array[i]);
        }
        this.if(array4, array5, array3);
        for (int j = 0; j < 10; ++j) {
            array2[j] = (float)Math.cos(array5[j]);
        }
    }
    
    void a() {
        for (int i = 0; i < 4; ++i) {
            j.a(this.a, this.if[i], 10);
        }
    }
    
    public void if(final float[] array, final float[] array2, final int[] array3) {
        final float[] array4 = new float[10];
        a(array, array4);
        a(array, array4, array2, c.l, c.h, c.else, this.if, c.new, c.long, array3);
    }
    
    static void a(final float[] array, final float[] array2, final float[] array3, final float[][] array4, final float[][] array5, final float[][][] array6, final float[][] array7, final float[][] array8, final float[][] array9, final int[] array10) {
        final af af = new af();
        final af af2 = new af();
        final af af3 = new af();
        final int[] array11 = new int[2];
        final int[] array12 = new int[2];
        final int[] array13 = new int[2];
        final float[] array14 = new float[2];
        final float[] array15 = new float[10];
        final float[] array16 = new float[10];
        for (int i = 0; i < 2; ++i) {
            n.a(array, array15, array6[i], array7, array9[i]);
            a(array15, array4, af3);
            array11[i] = af3.a;
            a(array15, array4[af3.a], array2, array5, af);
            array12[i] = af.a;
            for (int j = 0; j < 5; ++j) {
                array16[j] = array4[af3.a][j] + array5[af.a][j];
            }
            n.do(array16, 0.0012f);
            if(array15, array4[af3.a], array2, array5, af);
            array13[i] = af.a;
            for (int k = 5; k < 10; ++k) {
                array16[k] = array4[af3.a][k] + array5[af.a][k];
            }
            n.if(array16, 0.0012f);
            n.a(array16, 6.0E-4f);
            final g g = new g(new Float(array14[i]));
            a(array2, array16, g, array15, array8[i]);
            array14[i] = g.a;
        }
        a(array14, af2);
        array10[0] = (af2.a << 7 | array11[af2.a]);
        array10[1] = (array12[af2.a] << 5 | array13[af2.a]);
        n.a(array4, array5, array11[af2.a], array12[af2.a], array13[af2.a], array6[af2.a], array7, array3, array8[af2.a]);
    }
    
    static void a(final float[] array, final float[][] array2, final af af) {
        af.a = new Integer(0);
        float n = 1.0E38f;
        for (int i = 0; i < 128; ++i) {
            float n2 = 0.0f;
            for (int j = 0; j < 10; ++j) {
                final float n3 = array[j] - array2[i][j];
                n2 += n3 * n3;
            }
            if (n2 < n) {
                n = n2;
                af.a = new Integer(i);
            }
        }
    }
    
    static void a(final float[] array, final float[] array2, final float[] array3, final float[][] array4, final af af) {
        final float[] array5 = new float[10];
        for (int i = 0; i < 5; ++i) {
            array5[i] = array[i] - array2[i];
        }
        af.a = new Integer(0);
        float n = 1.0E38f;
        for (int j = 0; j < 32; ++j) {
            float n2 = 0.0f;
            for (int k = 0; k < 5; ++k) {
                final float n3 = array5[k] - array4[j][k];
                n2 += array3[k] * n3 * n3;
            }
            if (n2 < n) {
                n = n2;
                af.a = new Integer(j);
            }
        }
    }
    
    static void if(final float[] array, final float[] array2, final float[] array3, final float[][] array4, final af af) {
        final float[] array5 = new float[10];
        for (int i = 5; i < 10; ++i) {
            array5[i] = array[i] - array2[i];
        }
        af.a = new Integer(0);
        float n = 1.0E38f;
        for (int j = 0; j < 32; ++j) {
            float n2 = 0.0f;
            for (int k = 5; k < 10; ++k) {
                final float n3 = array5[k] - array4[j][k];
                n2 += array3[k] * n3 * n3;
            }
            if (n2 < n) {
                n = n2;
                af.a = new Integer(j);
            }
        }
    }
    
    static void a(final float[] array, final float[] array2, final g g, final float[] array3, final float[] array4) {
        g.a = new Float(0.0f);
        for (int i = 0; i < 10; ++i) {
            final float n = (array2[i] - array3[i]) * array4[i];
            g.a = new Float(g.a + array[i] * n * n);
        }
    }
    
    static void a(final float[] array, final af af) {
        af.a = new Integer(0);
        if (array[1] < array[0]) {
            af.a = new Integer(1);
        }
    }
    
    static void a(final float[] array, final float[] array2) {
        final float n = array[1] - 0.12566371f - 1.0f;
        if (n > 0.0f) {
            array2[0] = 1.0f;
        }
        else {
            array2[0] = n * n * 10.0f + 1.0f;
        }
        for (int i = 1; i < 9; ++i) {
            final float n2 = array[i + 1] - array[i - 1] - 1.0f;
            if (n2 > 0.0f) {
                array2[i] = 1.0f;
            }
            else {
                array2[i] = n2 * n2 * 10.0f + 1.0f;
            }
        }
        final float n3 = 2.8902655f - array[8] - 1.0f;
        if (n3 > 0.0f) {
            array2[9] = 1.0f;
        }
        else {
            array2[9] = n3 * n3 * 10.0f + 1.0f;
        }
        final int n4 = 4;
        array2[n4] *= 1.2f;
        final int n5 = 5;
        array2[n5] *= 1.2f;
    }
}
