// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class l
{
    float[] new;
    float[] for;
    float[] try;
    float[] do;
    int a;
    int int;
    g if;
    
    public l() {
        this.new = new float[20];
        this.for = new float[10];
        this.try = new float[10];
        this.do = new float[192];
        this.if = new g(new Float(0.0f));
    }
    
    public void a() {
        for (int i = 0; i < 152; ++i) {
            this.do[i] = 0.0f;
        }
        this.a = 152;
        for (int j = 0; j < 10; ++j) {
            this.for[j] = 0.0f;
        }
        this.int = 9;
        for (int k = 11; k < 20; ++k) {
            this.new[k] = 0.0f;
        }
        for (int l = 0; l < 10; ++l) {
            this.try[l] = 0.0f;
        }
        this.if.a = new Float(1.0f);
    }
    
    public void a(final int n, final float[] array, final int n2, final float[] array2, final int n3, final float[] array3, final int n4, final af af) {
        final float[] array4 = new float[11];
        final float[] array5 = new float[41];
        final g g = new g();
        ag.a(array2, n3, 0.7f, 10, array4, 0);
        ag.a(array2, n3, 0.55f, 10, this.new, 0);
        ah.a(this.new, 0, array, n2, this.do, this.a, 40);
        final int n5 = 1;
        this.a(n, this.do, this.a, array5, n5, af);
        array5[0] = this.for[this.int];
        this.a(this.new, 0, array4, 0, g, array5, n5);
        ah.a(array4, 0, array5, n5, array5, n5, 40, this.for, 0, 1);
        a(array5, 0, array3, n4, g.a);
        this.a(array, n2, array3, n4, this.if);
        j.a(this.do, 40, this.do, 0, 152);
    }
    
    public void a(final int n, final float[] array, final int n2, final float[] array2, final int n3, final af af) {
        final af af2 = new af(new Integer(0));
        final af af3 = new af(new Integer(0));
        g g = new g(new Float(0.0f));
        g g2 = new g(new Float(0.0f));
        final g g3 = new g(new Float(0.0f));
        final g g4 = new g(new Float(0.0f));
        final float[] array3 = new float[287];
        final af af4 = new af();
        a(n, array, n2, af2, af3, g, g2, array3, af4);
        af.a = af2.a;
        if (g.a == 0.0f) {
            j.a(array, n2, array2, n3, 40);
        }
        else {
            int n4;
            float[] array4;
            if (af3.a == 0) {
                n4 = n2 - af2.a;
                array4 = array;
            }
            else {
                a(array, n2, af2.a, af3.a, array2, n3, g3, g4);
                if (a(g.a, g2.a, g3.a, g4.a) == 1) {
                    n4 = 0 + ((af3.a - 1) * 41 + af4.a);
                    array4 = array3;
                }
                else {
                    g = g3;
                    g2 = g4;
                    n4 = n3;
                    array4 = array2;
                }
            }
            float n5;
            if (g.a > g2.a) {
                n5 = 0.6666667f;
            }
            else {
                n5 = g2.a / (g2.a + 0.5f * g.a);
            }
            this.a(array, n2, array4, n4, array2, n3, n5);
        }
    }
    
    static void a(final int n, final float[] array, final int n2, final af af, final af af2, final g g, final g g2, final float[] array2, final af af3) {
        final float[] array3 = new float[7];
        final float[] array4 = new float[7];
        float n3 = 0.0f;
        for (int i = 0; i < 40; ++i) {
            n3 += array[i + n2] * array[i + n2];
        }
        if (n3 < 0.1f) {
            g.a = new Float(0.0f);
            g2.a = new Float(1.0f);
            af.a = new Integer(0);
            af2.a = new Integer(0);
            return;
        }
        final int n4 = n - 1;
        int n5 = 0 - n4;
        float n6 = -1.0E30f;
        int n7 = 0;
        for (int j = 0; j < 3; ++j) {
            float n8 = 0.0f;
            for (int k = 0; k < 40; ++k) {
                n8 += array[n2 + k] * array[n2 + n5 + k];
            }
            if (n8 > n6) {
                n7 = j;
                n6 = n8;
            }
            --n5;
        }
        if (n6 <= 0.0f) {
            g.a = new Float(0.0f);
            g2.a = new Float(1.0f);
            af.a = new Integer(0);
            af2.a = new Integer(0);
            return;
        }
        final int n9 = n4 + n7;
        final int n10 = 0 - n9;
        float n11 = 0.0f;
        for (int l = 0; l < 40; ++l) {
            n11 += array[n2 + n10 + l] * array[n2 + n10 + l];
        }
        if (n11 < 0.1f) {
            g.a = new Float(0.0f);
            g2.a = new Float(1.0f);
            af.a = new Integer(0);
            af2.a = new Integer(0);
            return;
        }
        int n12 = 0;
        float n13 = 0.0f;
        int n14 = 0;
        int n15 = 0;
        int n16 = 0;
        final int n17 = 1 - n9;
        for (int n18 = 1; n18 < 8; ++n18) {
            int n19 = n17;
            for (int n20 = 0; n20 <= 40; ++n20) {
                final int n21 = n19++;
                float n22 = 0.0f;
                for (int n23 = 0; n23 < 4; ++n23) {
                    n22 += c.k[n16 + n23] * array[n2 + n21 - n23];
                }
                array2[n12 + n20] = n22;
            }
            float n24 = 0.0f;
            for (int n25 = 1; n25 < 40; ++n25) {
                n24 += array2[n12 + n25] * array2[n12 + n25];
            }
            final float n26 = n24 + array2[n12 + 0] * array2[n12 + 0];
            array3[n14++] = n26;
            final float n27 = n24 + array2[n12 + 40] * array2[n12 + 40];
            array4[n15++] = n27;
            if (Math.abs(array2[n12 + 0]) > Math.abs(array2[n12 + 40])) {
                if (n26 > n13) {
                    n13 = n26;
                }
            }
            else if (n27 > n13) {
                n13 = n27;
            }
            n12 += 41;
            n16 += 4;
        }
        if (n13 < 0.1f) {
            g.a = new Float(0.0f);
            g2.a = new Float(1.0f);
            af.a = new Integer(0);
            af2.a = new Integer(0);
            return;
        }
        float n28 = n6;
        float n29 = n11;
        float n30 = n28 * n28;
        int n31 = 0;
        int n32 = 1;
        int n33 = 0;
        int n34 = 0;
        int n35 = 0;
        for (int n36 = 1; n36 < 8; ++n36) {
            float n37 = 0.0f;
            for (int n38 = 0; n38 < 40; ++n38) {
                n37 += array[n2 + n38] * array2[n35 + n38];
            }
            if (n37 < 0.0f) {
                n37 = 0.0f;
            }
            final float n39 = n37 * n37;
            final float n40 = array3[n33++];
            if (n39 * n29 > n30 * n40) {
                n28 = n37;
                n30 = n39;
                n29 = n40;
                n32 = 0;
                n31 = n36;
            }
            ++n35;
            float n41 = 0.0f;
            for (int n42 = 0; n42 < 40; ++n42) {
                n41 += array[n2 + n42] * array2[n35 + n42];
            }
            if (n41 < 0.0f) {
                n41 = 0.0f;
            }
            final float n43 = n41 * n41;
            final float n44 = array4[n34++];
            if (n43 * n29 > n30 * n44) {
                n28 = n41;
                n30 = n43;
                n29 = n44;
                n32 = 1;
                n31 = n36;
            }
            n35 += 40;
        }
        if (n28 == 0.0f || n29 <= 0.1f) {
            g.a = new Float(0.0f);
            g2.a = new Float(1.0f);
            af.a = new Integer(0);
            af2.a = new Integer(0);
            return;
        }
        if (n30 >= n29 * n3 * 0.5f) {
            af.a = new Integer(n9 + 1 - n32);
            af3.a = new Integer(n32);
            af2.a = new Integer(n31);
            g.a = new Float(n28);
            g2.a = new Float(n29);
        }
        else {
            g.a = new Float(0.0f);
            g2.a = new Float(1.0f);
            af.a = new Integer(0);
            af2.a = new Integer(0);
        }
    }
    
    void a(final float[] array, final int n, final float[] array2, final int n2, final float[] array3, final int n3, final float n4) {
        final float n5 = 1.0f - n4;
        for (int i = 0; i < 40; ++i) {
            array3[i + n3] = n4 * array[i + n] + n5 * array2[i + n2];
        }
    }
    
    static void a(final float[] array, final int n, final int n2, final int n3, final float[] array2, final int n4, final g g, final g g2) {
        final int n5 = (n3 - 1) * 16;
        int n6 = 0 - n2 + 8;
        for (int i = 0; i < 40; ++i) {
            float n7 = 0.0f;
            for (int j = 0; j < 16; ++j) {
                n7 += c.do[n5 + j] * array[n + n6--];
            }
            array2[n4 + i] = n7;
            n6 += 17;
        }
        g.a = new Float(0.0f);
        for (int k = 0; k < 40; ++k) {
            g.a = new Float(g.a + array2[n4 + k] * array[n + k]);
        }
        if (g.a < 0.0f) {
            g.a = new Float(0.0f);
        }
        g2.a = new Float(0.0f);
        for (int l = 0; l < 40; ++l) {
            g2.a = new Float(g2.a + array2[n4 + l] * array2[n4 + l]);
        }
    }
    
    static int a(final float n, final float n2, final float n3, final float n4) {
        if (n4 == 0.0f) {
            return 1;
        }
        if (n3 * n3 * n2 > n * n * n4) {
            return 2;
        }
        return 1;
    }
    
    void a(final float[] array, final int n, final float[] array2, final int n2, final g g, final float[] array3, final int n3) {
        final float[] array4 = new float[20];
        ah.a(array2, n2, array, n, array4, 0, 20, this.try, 0, 0);
        a(array4, 0, g);
        float n4 = 0.0f;
        for (int i = 0; i < 20; ++i) {
            n4 += Math.abs(array4[i]);
        }
        if (n4 > 1.0f) {
            final float n5 = 1.0f / n4;
            for (int j = 0; j < 40; ++j) {
                array3[j + n3] *= n5;
            }
        }
    }
    
    static void a(final float[] array, final int n, final g g) {
        float n2 = 0.0f;
        for (int i = 0; i < 20; ++i) {
            n2 += array[n + i] * array[n + i];
        }
        final float n3 = n2;
        float n4 = 0.0f;
        int n5 = 0;
        for (int j = 0; j < 19; ++j) {
            n4 += array[n + n5++] * array[n + n5];
        }
        final float n6 = n4;
        if (n3 == 0.0f) {
            g.a = new Float(0.0f);
            return;
        }
        if (n3 < Math.abs(n6)) {
            g.a = new Float(0.0f);
            return;
        }
        g.a = new Float(-n6 / n3);
    }
    
    static void a(final float[] array, final int n, final float[] array2, final int n2, final float n3) {
        float n4;
        if (n3 > 0.0f) {
            n4 = n3 * 0.2f;
        }
        else {
            n4 = n3 * 0.9f;
        }
        final float n5 = 1.0f / (1.0f - Math.abs(n4));
        int n6 = n;
        for (int i = 0; i < 40; ++i) {
            array2[n2 + i] = n5 * (n4 * array[n6++] + array[n6]);
        }
    }
    
    void a(final float[] array, final int n, final float[] array2, final int n2, final g g) {
        float n3 = 0.0f;
        for (int i = 0; i < 40; ++i) {
            n3 += Math.abs(array[n + i]);
        }
        float n4;
        if (n3 == 0.0f) {
            n4 = 0.0f;
        }
        else {
            float n5 = 0.0f;
            for (int j = 0; j < 40; ++j) {
                n5 += Math.abs(array2[n2 + j]);
            }
            if (n5 == 0.0f) {
                g.a = new Float(0.0f);
                return;
            }
            n4 = n3 / n5 * 0.012499988f;
        }
        float floatValue = g.a;
        for (int k = 0; k < 40; ++k) {
            floatValue = floatValue * 0.9875f + n4;
            final int n6 = n2 + k;
            array2[n6] *= floatValue;
        }
        g.a = new Float(floatValue);
    }
}
