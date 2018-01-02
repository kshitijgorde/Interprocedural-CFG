// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class u
{
    float[] a;
    
    public u() {
        this.a = new float[] { -14.0f, -14.0f, -14.0f, -14.0f };
    }
    
    int a(final float[] array, final float[] array2, final int n, final g g, final g g2, final int n2) {
        int n3 = 0;
        int n4 = 0;
        final af af = new af(new Integer(0));
        final af af2 = new af(new Integer(0));
        final g g3 = new g(new Float(0.0f));
        final float[] array3 = new float[2];
        w.a(this.a, array, n, g3);
        final float n5 = -1.0f / (4.0f * array2[0] * array2[2] - array2[4] * array2[4]);
        array3[0] = (2.0f * array2[2] * array2[1] - array2[3] * array2[4]) * n5;
        array3[1] = (2.0f * array2[0] * array2[3] - array2[1] * array2[4]) * n5;
        if (n2 == 1 && array3[0] > 0.94f) {
            array3[0] = 0.94f;
        }
        a(array3, af, af2, g3.a);
        float n6 = 1.0E38f;
        if (n2 == 1) {
            for (int n7 = 0; n7 < 4.0f; ++n7) {
                for (int n8 = 0; n8 < 8.0f; ++n8) {
                    final float n9 = c.i[af.a + n7][0] + c.g[af2.a + n8][0];
                    if (n9 < 0.9999f) {
                        final float n10 = g3.a * (c.i[af.a + n7][1] + c.g[af2.a + n8][1]);
                        final float n11 = n9 * n9 * array2[0] + n9 * array2[1] + n10 * n10 * array2[2] + n10 * array2[3] + n9 * n10 * array2[4];
                        if (n11 < n6) {
                            n6 = n11;
                            n3 = af.a + n7;
                            n4 = af2.a + n8;
                        }
                    }
                }
            }
        }
        else {
            for (int n12 = 0; n12 < 4.0f; ++n12) {
                for (int n13 = 0; n13 < 8.0f; ++n13) {
                    final float n14 = c.i[af.a + n12][0] + c.g[af2.a + n13][0];
                    final float n15 = g3.a * (c.i[af.a + n12][1] + c.g[af2.a + n13][1]);
                    final float n16 = n14 * n14 * array2[0] + n14 * array2[1] + n15 * n15 * array2[2] + n15 * array2[3] + n14 * n15 * array2[4];
                    if (n16 < n6) {
                        n6 = n16;
                        n3 = af.a + n12;
                        n4 = af2.a + n13;
                    }
                }
            }
        }
        g.a = new Float(c.i[n3][0] + c.g[n4][0]);
        final float n17 = c.i[n3][1] + c.g[n4][1];
        g2.a = new Float(n17 * g3.a);
        w.a(this.a, n17);
        return c.m[n3] * 16 + c.j[n4];
    }
    
    public static void a(final float[] array, final af af, final af af2, final float n) {
        final float n2 = (array[1] - (c.byte[0][0] * array[0] + c.byte[1][1]) * n) * -0.032623f;
        final float n3 = (c.byte[1][0] * (-c.byte[0][1] + array[0] * c.byte[0][0]) * n - c.byte[0][0] * array[1]) * -0.032623f;
        if (n > 0.0f) {
            af.a = new Integer(0);
            while (n3 > c.void[af.a] * n) {
                af.a = new Integer(af.a + 1);
                if (af.a >= 4.0f) {
                    break;
                }
            }
            af2.a = new Integer(0);
            while (n2 > c.goto[af2.a] * n) {
                af2.a = new Integer(af2.a + 1);
                if (af2.a >= 8.0f) {
                    break;
                }
            }
        }
        else {
            af.a = new Integer(0);
            while (n3 < c.void[af.a] * n) {
                final Integer a = af.a;
                af.a = new Integer(af.a + 1);
                if (af.a >= 4.0f) {
                    break;
                }
            }
            af2.a = new Integer(0);
            while (n2 < c.goto[af2.a] * n) {
                final Integer a2 = af2.a;
                af2.a = new Integer(af2.a + 1);
                if (af2.a >= 8.0f) {
                    break;
                }
            }
        }
    }
}
