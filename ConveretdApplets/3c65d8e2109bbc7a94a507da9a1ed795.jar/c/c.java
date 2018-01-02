// 
// Decompiled by Procyon v0.5.30
// 

package c;

class c
{
    int for;
    float[] if;
    float[] do;
    float[] try;
    float[] a;
    float[] new;
    d int;
    
    void a(final float[] array, final int n, final float[] array2, int n2, final float[] array3, int n3, final int[] array4, final int n4, final int n5) {
        final float[] array5 = new float[g.i + g.a];
        final int[] array6 = { 0 };
        for (int i = 0; i < g.i; ++i) {
            array5[i] = 0.0f;
        }
        final int j = g.i;
        if (n5 != 0) {
            b.a(array, n, array3, n3, g.A, g.i);
        }
        else {
            b.a(array, n, array3, n3, this.int.c - g.A, g.i);
        }
        for (int k = 0; k < n4; ++k) {
            if (n5 != 0 && k == g.A) {
                n2 += g.i + 1;
                n3 += g.i + 1;
                b.a(array, n + k, array3, n3, n4 - k, g.i);
            }
            else if (n5 == 0 && k == this.int.c - g.A) {
                n2 += g.i + 1;
                n3 += g.i + 1;
                b.a(array, n + k, array3, n3, n4 - k, g.i);
            }
            array5[j + k] = 0.0f;
            b.a(array5, j + k, array3, n3, 1, g.i);
            this.a(array6, 0, array[n + k] - array5[j + k], g.aN, 8);
            array4[k] = array6[0];
            array5[j + k] = g.aN[array4[k]];
            b.a(array5, j + k, array3, n3, 1, g.i);
        }
    }
    
    void a(final float[] array, final int n, final float[] array2, final int n2, final float[] array3, final int n3, final int[] array4, final int[] array5, final int n4, final int n5) {
        final float[] array6 = new float[g.i + 2 * g.a];
        final float[] array7 = new float[1 + g.i];
        final float[] array8 = new float[g.i + 2 * g.a];
        for (int i = 0; i < g.i; ++i) {
            array8[i] = (array6[i] = 0.0f);
        }
        for (int j = 0; j < g.i; ++j) {
            array7[j] = array2[n2 + g.i - j];
        }
        array7[g.i] = array2[n2];
        final int k = g.i;
        final int l = g.i;
        System.arraycopy(array, n, array6, k, n4);
        for (int n6 = 0; n6 < n4; ++n6) {
            array6[k + n4 + n6] = 0.0f;
        }
        b.a(array6, k, array7, array2, n2, 2 * n4, g.i, array8, l);
        for (int n7 = 0; n7 < n4; ++n7) {
            final float[] array9 = array8;
            final int n8 = l + n7;
            array9[n8] += array8[l + n7 + n4];
        }
        float n9 = array8[l + 0];
        for (int n10 = 1; n10 < n4; ++n10) {
            if (array8[l + n10] * array8[l + n10] > n9 * n9) {
                n9 = array8[l + n10];
            }
        }
        float abs = Math.abs(n9);
        if (abs < 10.0f) {
            abs = 10.0f;
        }
        this.a(array4, 0, (float)(Math.log(abs) / Math.log(10.0)), g.ad, 64);
        final float n11 = 4.5f / (float)Math.pow(10.0, g.ad[array4[0]]);
        for (int n12 = 0; n12 < n4; ++n12) {
            final float[] array10 = array8;
            final int n13 = l + n12;
            array10[n13] *= n11;
        }
        this.a(array8, l, array2, n2, array3, n3, array5, n4, n5);
    }
    
    void a(final float[] array, final int n, final float[] array2) {
        final float[] array3 = { 0.00635f, 0.003175f, 0.0015875f, 7.9375E-4f };
        final float[] array4 = new float[g.n];
        final float[] array5 = new float[g.n];
        final float[] array6 = new float[g.n];
        final float[] array7 = new float[g.n];
        final int n2 = 0;
        final int n3 = 1;
        final float[] array8 = new float[2];
        for (int i = 0; i < g.n; ++i) {
            array4[i] = -1.0f * (array2[i + 1] + array2[g.i - i]);
            array5[i] = array2[g.i - i] - array2[i + 1];
        }
        array6[0] = -1.0f - array4[0];
        array6[1] = -array6[0] - array4[1];
        array6[2] = -array6[1] - array4[2];
        array6[3] = -array6[2] - array4[3];
        array6[4] = -array6[3] - array4[4];
        array6[4] /= 2.0f;
        array7[0] = 1.0f - array5[0];
        array7[1] = array7[0] - array5[1];
        array7[2] = array7[1] - array5[2];
        array7[3] = array7[2] - array5[3];
        array7[4] = array7[3] - array5[4];
        array7[4] /= 2.0f;
        float n4 = 0.0f;
        float n5 = 0.0f;
        array8[n2] = g.au;
        array8[n3] = g.au;
        for (int j = 0; j < g.i; ++j) {
            float[] array9;
            int n6;
            if ((j & 0x1) == 0x0) {
                array9 = array6;
                n6 = n2;
            }
            else {
                array9 = array7;
                n6 = n3;
            }
            int k = 0;
            float n7 = array3[k];
            while (k < g.new) {
                final float n8 = (float)Math.cos(n4 * g.D);
                final float n9 = 2.0f * n8 + array9[0];
                final float n10 = 2.0f * n8 * n9 - 1.0f + array9[1];
                final float n11 = 2.0f * n8 * n10 - n9 + array9[2];
                final float n12 = n8 * (2.0f * n8 * n11 - n10 + array9[3]) - n11 + array9[4];
                if (n12 * array8[n6] <= 0.0f || n4 >= 0.5) {
                    if (k == g.new - 1) {
                        if (Math.abs(n12) >= Math.abs(array8[n6])) {
                            array[n + j] = n4 - n7;
                        }
                        else {
                            array[n + j] = n4;
                        }
                        if (array8[n6] >= 0.0f) {
                            array8[n6] = -1.0f * g.au;
                        }
                        else {
                            array8[n6] = g.au;
                        }
                        n4 = n5;
                        k = g.new;
                    }
                    else {
                        if (k == 0) {
                            n5 = n4;
                        }
                        ++k;
                        n4 -= array3[k];
                        n7 = array3[k];
                    }
                }
                else {
                    array8[n6] = n12;
                    n4 += n7;
                }
            }
        }
        for (int l = 0; l < g.i; ++l) {
            array[n + l] *= g.D;
        }
    }
    
    void a(final float[] array, final float[] array2) {
        final float[] array3 = new float[g.aG];
        final float[] array4 = new float[g.i + 1];
        final float[] array5 = new float[g.i + 1];
        final float[] array6 = new float[g.i + 1];
        System.arraycopy(array2, 0, this.a, g.ap + g.aG - this.int.new, this.int.new);
        for (int i = 0; i < this.int.a; ++i) {
            final int ap = g.ap;
            if (i < this.int.a - 1) {
                this.a(array3, g.x, this.a, 0, g.aG);
            }
            else {
                this.a(array3, g.o, this.a, ap, g.aG);
            }
            this.if(array6, array3, g.aG, g.i);
            this.a(array6, array6, g.av, 0, g.i + 1);
            this.a(array4, array3, array6, g.i);
            b.a(array5, 0, array4, g.K, g.i + 1);
            this.a(array, i * g.i, array5);
        }
        final int n = g.ap + g.aG - this.int.new;
        System.arraycopy(this.a, g.ap + g.aG - n, this.a, 0, n);
    }
    
    void a(final float[] array, final float[] array2, final float[] array3, final int n, final float n2, final long n3) {
        final float[] array4 = new float[g.i];
        b.a(array4, array2, array3, n, n2, (int)n3);
        b.a(array, array4);
    }
    
    void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final float[] array5, final float[] array6, final int n) {
        final float[] array7 = new float[g.i + 1];
        final int n2 = n + 1;
        if (this.int.else == 30) {
            this.a(array7, array6, array4, 0, g.long[0], n);
            System.arraycopy(array7, 0, array, 0, n2);
            this.a(array7, array5, array3, 0, g.long[0], n);
            b.a(array2, 0, array7, g.byte, n2);
            int n3 = n2;
            for (int i = 1; i < this.int.try; ++i) {
                this.a(array7, array4, array4, n, g.long[i], n);
                System.arraycopy(array7, 0, array, n3, n2);
                this.a(array7, array3, array3, n, g.long[i], n);
                b.a(array2, n3, array7, g.byte, n2);
                n3 += n2;
            }
        }
        else {
            int n4 = 0;
            for (int j = 0; j < this.int.try; ++j) {
                this.a(array7, array6, array4, 0, g.else[j], n);
                System.arraycopy(array7, 0, array, n4, n2);
                for (int k = 0; k < n2; ++k) {
                    this.a(array7, array5, array3, 0, g.else[j], n);
                }
                b.a(array2, n4, array7, g.byte, n2);
                n4 += n2;
            }
        }
        if (this.int.else == 30) {
            System.arraycopy(array3, n, array5, 0, n);
            System.arraycopy(array4, n, array6, 0, n);
        }
        else {
            System.arraycopy(array3, 0, array5, 0, n);
            System.arraycopy(array4, 0, array6, 0, n);
        }
    }
    
    void a(final float[] array, final int[] array2, final float[] array3, final int n) {
        this.a(array, 0, array2, 0, array3, 0, g.af, g.ao, g.p, g.N);
        if (n == 2) {
            this.a(array, g.i, array2, g.ao, array3, g.i, g.af, g.ao, g.p, g.N);
        }
    }
    
    void a(final float[] array, final float[] array2, final int[] array3, final float[] array4) {
        final float[] array5 = new float[g.i * g.t];
        final float[] array6 = new float[g.i * g.t];
        this.a(array5, array4);
        this.a(array6, array3, array5, this.int.a);
        b.a(array6, g.i, this.int.a);
        this.a(array, array2, array5, array6, this.do, this.try, g.i);
    }
    
    public void a(final int[] array, final int n, final int[] array2, final int n2, final float[] array3, final int n3, final float[] array4, final int n4, final int n5, final int n6, final int n7, final float[] array5, final int n8, final float[] array6, final int n9) {
        final float[] array7 = new float[g.as];
        final float[] array8 = new float[g.A];
        final float[] array9 = new float[g.G + g.A + 2 * g.i];
        final float[] array10 = new float[g.R * 128];
        final float[] array11 = new float[g.R * 128];
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        final float[] array12 = new float[g.G];
        final float[] array13 = new float[g.A];
        final float[] array14 = new float[g.A];
        final float[] array15 = { 0.0f };
        final int[] array16 = { 0 };
        final float[] array17 = { 0.0f };
        for (int i = 0; i < g.A; ++i) {
            array13[i] = 0.0f;
        }
        int n13 = n5 - n6 + 1;
        if (n6 == g.A) {
            n13 = n5 - n6 + 1 + n6 / 2;
        }
        System.arraycopy(array6, 0, array9, 0, g.i);
        System.arraycopy(array4, n4, array9, g.i, n5);
        System.arraycopy(array3, n3, array9, g.i + n5, n6);
        b.a(array9, g.i, array5, n8, n5 + n6, g.i);
        System.arraycopy(array9, g.i + n5, array8, 0, n6);
        float n14 = 0.0f;
        for (int j = 0; j < n6; ++j) {
            n14 += array8[j] * array8[j];
        }
        this.a(array12, array9, g.i, n5);
        for (int k = 0; k < n7; ++k) {
            final int n15 = g.if[n9][k];
            float n16 = -1.0E7f;
            float char1 = 0.0f;
            int n17 = 0;
            float n18 = 0.0f;
            int n19 = g.i + n5 - n6;
            for (int l = 0; l < n6; ++l) {
                n18 += array8[l] * array9[n19];
                ++n19;
            }
            float n23;
            if (k == 0) {
                n12 = 0;
                n10 = g.i + n5 - n6 - 1;
                n11 = g.i + n5 - 1;
                array11[n12] = 0.0f;
                int n20 = g.i + n5 - n6;
                for (int n21 = 0; n21 < n6; ++n21) {
                    final float[] array18 = array11;
                    final int n22 = n12;
                    array18[n22] += array9[n20] * array9[n20];
                    ++n20;
                }
                if (array11[n12] > 0.0f) {
                    array10[0] = 1.0f / (array11[n12] + g.E);
                }
                else {
                    array10[0] = 0.0f;
                }
                ++n12;
                n23 = -1.0E7f;
                if (n18 > 0.0f) {
                    n23 = n18 * n18 * array10[0];
                }
            }
            else {
                n23 = n18 * n18 * array10[0];
            }
            final float n24 = n18 * array10[0];
            if (n23 > n16 && Math.abs(n24) < g.char) {
                n17 = 0;
                n16 = n23;
                char1 = n24;
            }
            for (int n25 = 1; n25 < n15; ++n25) {
                float n26 = 0.0f;
                int n27 = g.i + n5 - n6 - n25;
                for (int n28 = 0; n28 < n6; ++n28) {
                    n26 += array8[n28] * array9[n27];
                    ++n27;
                }
                float n29;
                if (k == 0) {
                    array11[n12] = array11[n25 - 1] + array9[n10] * array9[n10] - array9[n11] * array9[n11];
                    ++n12;
                    --n11;
                    --n10;
                    if (array11[n25] > 0.0f) {
                        array10[n25] = 1.0f / (array11[n25] + g.E);
                    }
                    else {
                        array10[n25] = 0.0f;
                    }
                    n29 = -1.0E7f;
                    if (n26 > 0.0f) {
                        n29 = n26 * n26 * array10[n25];
                    }
                }
                else {
                    n29 = n26 * n26 * array10[n25];
                }
                final float n30 = n26 * array10[n25];
                if (n29 > n16 && Math.abs(n30) < g.char) {
                    n17 = n25;
                    n16 = n29;
                    char1 = n30;
                }
            }
            if (n6 == g.A) {
                array15[0] = n16;
                array16[0] = n17;
                array17[0] = char1;
                this.a(20, 39, k, n13 - n6 / 2, array8, array9, g.i + n5, array15, array16, array17, array11, array10);
                n16 = array15[0];
                n17 = array16[0];
                char1 = array17[0];
            }
            final int n31 = n17;
            int n32;
            int n33;
            int n34;
            int n35;
            if (g.ak == -1) {
                n32 = 0;
                n33 = n15 - 1;
                n34 = 20;
                n35 = 39;
            }
            else {
                n34 = 0;
                n35 = 0;
                n32 = n31 - g.ak / 2;
                n33 = n32 + g.ak;
                if (n6 == g.A) {
                    if (n32 < 0) {
                        n34 = 40 + n32;
                        n35 = 39;
                        n32 = 0;
                    }
                    else if (n31 < n13 - 20) {
                        if (n33 > n15) {
                            n32 -= n33 - n15;
                            n33 = n15;
                        }
                    }
                    else if (n32 < n13 - 20) {
                        n34 = 20;
                        n32 = 0;
                        n33 = 0;
                        n35 = 19 + g.ak;
                        if (n35 > 39) {
                            n33 = n35 - 39;
                            n35 = 39;
                        }
                    }
                    else {
                        n34 = 20 + n32 - (n13 - 20);
                        n35 = 39;
                        n32 = 0;
                        n33 = g.ak - (n35 - n34 + 1);
                    }
                }
                else {
                    if (n32 < 0) {
                        n33 -= n32;
                        n32 = 0;
                    }
                    if (n33 > n15) {
                        n32 -= n33 - n15;
                        n33 = n15;
                    }
                }
            }
            int n36 = n32;
            final int n37 = n32 + n13;
            final int n38 = n33 + n13;
            if (k == 0) {
                n12 = n13;
                array11[n12] = 0.0f;
                int n39 = n5 - n6;
                for (int n40 = 0; n40 < n6; ++n40) {
                    final float[] array19 = array11;
                    final int n41 = n12;
                    array19[n41] += array12[n39] * array12[n39];
                    ++n39;
                }
                n10 = n5 - 1 - n6;
                n11 = n5 - 1;
                for (int n42 = 0; n42 < n15 - 1; ++n42) {
                    array11[n12 + 1] = array11[n12] + array12[n10] * array12[n10] - array12[n11] * array12[n11];
                    --n11;
                    --n10;
                    ++n12;
                }
            }
            for (int n43 = n37; n43 < n38; ++n43) {
                float n44 = 0.0f;
                int n45 = n5 - n36++ - n6;
                for (int n46 = 0; n46 < n6; ++n46) {
                    n44 += array8[n46] * array12[n45];
                    ++n45;
                }
                if (array11[n43] > 0.0f) {
                    array10[n43] = 1.0f / (array11[n43] + g.E);
                }
                else {
                    array10[n43] = 0.0f;
                }
                float n47;
                if (k == 0) {
                    n47 = -1.0E7f;
                    if (n44 > 0.0f) {
                        n47 = n44 * n44 * array10[n43];
                    }
                }
                else {
                    n47 = n44 * n44 * array10[n43];
                }
                final float n48 = n44 * array10[n43];
                if (n47 > n16 && Math.abs(n48) < g.char) {
                    n17 = n43;
                    n16 = n47;
                    char1 = n48;
                }
            }
            if (n6 == g.A && n34 != 0) {
                array15[0] = n16;
                array16[0] = n17;
                array17[0] = char1;
                this.a(n34, n35, k, 2 * n13 - 20, array8, array12, n5, array15, array16, array17, array11, array10);
                final float n49 = array15[0];
                n17 = array16[0];
                char1 = array17[0];
            }
            array[n + k] = n17;
            float n50;
            if (k == 0) {
                if (char1 < 0.0f) {
                    char1 = 0.0f;
                }
                if (char1 > g.char) {
                    char1 = g.char;
                }
                n50 = b.a(char1, 1.0f, 32, array2, n2 + k);
            }
            else if (k == 1) {
                n50 = b.a(char1, Math.abs(array7[k - 1]), 16, array2, n2 + k);
            }
            else {
                n50 = b.a(char1, Math.abs(array7[k - 1]), 8, array2, n2 + k);
            }
            int n51;
            float[] array20;
            if (n6 == g.void - this.int.c) {
                if (array[n + k] < n13) {
                    n51 = g.i + n5 - n6 - array[n + k];
                    array20 = array9;
                }
                else {
                    n51 = n5 - n6 - array[n + k] + n13;
                    array20 = array12;
                }
            }
            else if (array[n + k] < n13) {
                if (array[n + k] < n13 - 20) {
                    n51 = g.i + n5 - n6 - array[n + k];
                    array20 = array9;
                }
                else {
                    this.a(array[n + k] - n13 + 40, array9, g.i + n5, array14);
                    n51 = 0;
                    array20 = array14;
                }
            }
            else {
                final int n52 = array[n + k] / n13;
                if (array[n + k] - n52 * n13 < n13 - 20) {
                    n51 = n52 * n5 - n6 - array[n + k] + n52 * n13;
                    array20 = array12;
                }
                else {
                    this.a(array[n + k] - (n52 + 1) * n13 + 40, array12, n52 * n5, array14);
                    n51 = 0;
                    array20 = array14;
                }
            }
            for (int n53 = 0; n53 < n6; ++n53) {
                final float[] array21 = array13;
                final int n54 = n53;
                array21[n54] += n50 * array20[n51];
                final float[] array22 = array8;
                final int n55 = n53;
                array22[n55] -= n50 * array20[n51];
                ++n51;
            }
            array7[k] = n50;
        }
        float n56 = 0.0f;
        for (int n57 = 0; n57 < n6; ++n57) {
            n56 += array13[n57] * array13[n57];
        }
        int n58 = array2[n2 + 0];
        for (int n59 = array2[n2 + 0]; n59 < 32; ++n59) {
            if (n56 * g.T[n59] * g.T[n59] < n14 * array7[0] * array7[0] && g.T[n58] < 2.0f * array7[0]) {
                n58 = n59;
            }
        }
        array2[n2 + 0] = n58;
    }
    
    public void a(final int[] array) {
        for (int i = 1; i < g.as; ++i) {
            if (array[i] >= 108 && array[i] < 172) {
                final int n = i;
                array[n] -= 64;
            }
            else if (array[i] >= 236) {
                final int n2 = i;
                array[n2] -= 128;
            }
        }
    }
    
    public void a(final float[] array, final int n, final float[] array2, final float[] array3) {
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            array2[n3] = g.ag[0] * array[n2];
            final int n4 = n3;
            array2[n4] += g.ag[1] * array3[0];
            final int n5 = n3;
            array2[n5] += g.ag[2] * array3[1];
            array3[1] = array3[0];
            array3[0] = array[n2];
            ++n3;
            ++n2;
        }
        int n6 = 0;
        for (int j = 0; j < n; ++j) {
            final int n7 = n6;
            array2[n7] -= g.r[1] * array3[2];
            final int n8 = n6;
            array2[n8] -= g.r[2] * array3[3];
            array3[3] = array3[2];
            array3[2] = array2[n6];
            ++n6;
        }
    }
    
    public void if(final float[] array, final float[] array2, final int n, final int n2) {
        for (int i = 0; i <= n2; ++i) {
            float n3 = 0.0f;
            for (int j = 0; j < n - i; ++j) {
                n3 += array2[j] * array2[j + i];
            }
            array[i] = n3;
        }
    }
    
    public void a(final float[] array, final float[] array2, final float[] array3, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            array[i] = array2[i] * array3[i + n];
        }
    }
    
    public void a(final float[] array, final float[] array2, final float[] array3, final int n) {
        array[0] = 1.0f;
        if (array3[0] < g.E) {
            for (int i = 0; i < n; ++i) {
                array[i + 1] = (array2[i] = 0.0f);
            }
        }
        else {
            array[1] = (array2[0] = -array3[1] / array3[0]);
            float n2 = array3[0] + array3[1] * array2[0];
            for (int j = 1; j < n; ++j) {
                float n3 = array3[j + 1];
                for (int k = 0; k < j; ++k) {
                    n3 += array[k + 1] * array3[j - k];
                }
                array2[j] = -n3 / n2;
                n2 += array2[j] * n3;
                for (int n4 = j + 1 >> 1, l = 0; l < n4; ++l) {
                    final float n5 = array[l + 1] + array2[j] * array[j - l];
                    final int n6 = j - l;
                    array[n6] += array2[j] * array[l + 1];
                    array[l + 1] = n5;
                }
                array[j + 1] = array2[j];
            }
        }
    }
    
    public void a(final float[] array, final int n, final int[] array2, final int n2, final float[] array3, final int n3, final float[] array4, final int n4, final int n5, final int n6) {
        int n7 = 0;
        float au = g.au;
        int n8 = 0;
        for (int i = 0; i < n5; ++i) {
            final float n9 = array4[n4] - array3[n7 + n3];
            float n10 = n9 * n9;
            for (int j = 1; j < n6; ++j) {
                final float n11 = array4[j + n4] - array3[n7 + j + n3];
                n10 += n11 * n11;
            }
            if (n10 < au) {
                au = n10;
                n8 = i;
            }
            n7 += n6;
        }
        for (int k = 0; k < n6; ++k) {
            array[k + n] = array3[n8 * n6 + k + n3];
        }
        array2[n2] = n8;
    }
    
    public void a(final float[] array, final int n, final int[] array2, final int n2, final float[] array3, final int n3, final float[] array4, final int n4, final int[] array5, final int[] array6) {
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < n4; ++i) {
            this.a(array, n6 + n, array2, i + n2, array4, n5, array3, n6 + n3, array6[i], array5[i]);
            n6 += array5[i];
            n5 += array5[i] * array6[i];
        }
    }
    
    public float a(final int[] array, final int n, final float n2, final float[] array2, final int n3) {
        float n4;
        if (n2 <= array2[0]) {
            array[n] = 0;
            n4 = array2[0];
        }
        else {
            int n5;
            for (n5 = 0; n2 > array2[n5] && n5 < n3 - 1; ++n5) {}
            if (n2 > (array2[n5] + array2[n5 - 1]) / 2.0f) {
                array[n] = n5;
                n4 = array2[n5];
            }
            else {
                array[n] = n5 - 1;
                n4 = array2[n5 - 1];
            }
        }
        return n4;
    }
    
    int a(final float[] array) {
        final float[] array2 = new float[g.U];
        final float[] array3 = new float[g.U];
        final float[] array4 = { 0.8f, 0.9f, 1.0f, 0.9f, 0.8f };
        final float[] array5 = { 0.16666667f, 0.33333334f, 0.5f, 0.6666667f, 0.8333333f };
        for (int i = 0; i < g.U; ++i) {
            array2[i] = 0.0f;
        }
        for (int j = 0; j < g.U; ++j) {
            array3[j] = 0.0f;
        }
        final int n = 0;
        int n2 = 0;
        for (int k = 0; k < 5; ++k) {
            final float[] array6 = array2;
            final int n3 = n;
            array6[n3] += array5[k] * array[n2] * array[n2];
            ++n2;
        }
        for (int l = 5; l < g.A; ++l) {
            final float[] array7 = array2;
            final int n4 = n;
            array7[n4] += array[n2] * array[n2];
            ++n2;
        }
        for (int n5 = 1; n5 < this.int.try - 1; ++n5) {
            int n6 = n5 * g.A;
            for (int n7 = 0; n7 < 5; ++n7) {
                final float[] array8 = array2;
                final int n8 = n5;
                array8[n8] += array5[n7] * array[n6] * array[n6];
                final float[] array9 = array3;
                final int n9 = n5;
                array9[n9] += array[n6] * array[n6];
                ++n6;
            }
            for (int n10 = 5; n10 < g.A - 5; ++n10) {
                final float[] array10 = array2;
                final int n11 = n5;
                array10[n11] += array[n6] * array[n6];
                final float[] array11 = array3;
                final int n12 = n5;
                array11[n12] += array[n6] * array[n6];
                ++n6;
            }
            for (int n13 = g.A - 5; n13 < g.A; ++n13) {
                final float[] array12 = array2;
                final int n14 = n5;
                array12[n14] += array[n6] * array[n6];
                final float[] array13 = array3;
                final int n15 = n5;
                array13[n15] += array5[g.A - n13 - 1] * array[n6] * array[n6];
                ++n6;
            }
        }
        final int n16 = this.int.try - 1;
        int n17 = n16 * g.A;
        for (int n18 = 0; n18 < g.A - 5; ++n18) {
            final float[] array14 = array3;
            final int n19 = n16;
            array14[n19] += array[n17] * array[n17];
            ++n17;
        }
        for (int n20 = g.A - 5; n20 < g.A; ++n20) {
            final float[] array15 = array3;
            final int n21 = n16;
            array15[n21] += array5[g.A - n20 - 1] * array[n17] * array[n17];
            ++n17;
        }
        int n22;
        if (this.int.else == 20) {
            n22 = 1;
        }
        else {
            n22 = 0;
        }
        float n23 = (array2[0] + array3[1]) * array4[n22];
        int n24 = 1;
        for (int n25 = 2; n25 < this.int.try; ++n25) {
            ++n22;
            if ((array2[n25 - 1] + array3[n25]) * array4[n22] > n23) {
                n23 = (array2[n25 - 1] + array3[n25]) * array4[n22];
                n24 = n25;
            }
        }
        return n24;
    }
    
    private void a(final float[] array, final int n, final float[] array2, final int n2, final int n3, final float[] array3, final int n4, final float[] array4) {
        int n5 = n4;
        for (int i = 0; i < g.i; ++i) {
            int n6 = n + i;
            int n7 = g.i - 1;
            int n8 = n2;
            array3[n5] = 0.0f;
            for (int j = 0; j <= i; ++j) {
                final int n9 = n5;
                array3[n9] += array2[n8] * array[n6];
                ++n8;
                --n6;
            }
            for (int k = i + 1; k < g.i + 1; ++k) {
                final int n10 = n5;
                array3[n10] += array2[n8] * array4[n7];
                ++n8;
                --n7;
            }
            ++n5;
        }
        for (int l = g.i; l < n3; ++l) {
            int n11 = n + l;
            int n12 = n2;
            array3[n5] = 0.0f;
            for (int n13 = 0; n13 < g.i + 1; ++n13) {
                final int n14 = n5;
                array3[n14] += array2[n12] * array[n11];
                ++n12;
                --n11;
            }
            ++n5;
        }
        System.arraycopy(array, n + n3 - g.i, array4, 0, g.i);
    }
    
    private void a(final float[] array, final float[] array2, final int n, final int n2) {
        final float[] array3 = new float[g.G + g.aM];
        for (int i = 0; i < g.an; ++i) {
            array3[i] = 0.0f;
        }
        System.arraycopy(array2, n, array3, g.an - 1, n2);
        for (int j = n2 + g.an - 1; j < n2 + g.aM; ++j) {
            array3[j] = 0.0f;
        }
        int n3 = 0;
        for (int k = 0; k < n2; ++k) {
            array[k] = 0.0f;
        }
        for (int l = 0; l < n2; ++l) {
            int n4 = l;
            int n5 = g.aM - 1;
            for (int n6 = 0; n6 < g.aM; ++n6) {
                final int n7 = n3;
                array[n7] += array3[n4] * g.y[n5];
                ++n4;
                --n5;
            }
            ++n3;
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final float[] array, final float[] array2, final int n5, final float[] array3, final int[] array4, final float[] array5, final float[] array6, final float[] array7) {
        float n6 = 0.0f;
        int n7 = 1 - n + n5;
        for (int i = 0; i < n - 5; ++i) {
            n6 += array2[n7] * array2[n7];
            ++n7;
        }
        int n8 = n5 - n;
        for (int j = n; j <= n2; ++j) {
            final int n9 = n4 + j - 20;
            final int n10 = j - 4;
            n6 += array2[n8] * array2[n8];
            --n8;
            array6[n9] = n6;
            float n11 = 0.0f;
            int n12 = n5 - j;
            for (int k = 0; k < n10; ++k) {
                n11 += array[k] * array2[n12];
                ++n12;
            }
            float n13 = 0.2f;
            int n14 = n5 - 4;
            int n15 = n5 - j - 4;
            for (int l = n10; l < j; ++l) {
                final float n16 = (1.0f - n13) * array2[n14] + n13 * array2[n15];
                ++n14;
                ++n15;
                final int n17 = n9;
                array6[n17] += n16 * n16;
                n11 += array[l] * n16;
                n13 += 0.2f;
            }
            int n18 = n5 - j;
            for (int n19 = j; n19 < g.A; ++n19) {
                final int n20 = n9;
                array6[n20] += array2[n18] * array2[n18];
                n11 += array[n19] * array2[n18];
                ++n18;
            }
            if (array6[n9] > 0.0f) {
                array7[n9] = 1.0f / (array6[n9] + g.E);
            }
            else {
                array7[n9] = 0.0f;
            }
            float n21;
            if (n3 == 0) {
                n21 = -1.0E7f;
                if (n11 > 0.0f) {
                    n21 = n11 * n11 * array7[n9];
                }
            }
            else {
                n21 = n11 * n11 * array7[n9];
            }
            final float n22 = n11 * array7[n9];
            if (n21 > array3[0] && Math.abs(n22) < g.char) {
                array4[0] = n9;
                array3[0] = n21;
                array5[0] = n22;
            }
        }
    }
    
    private void a(final int n, final float[] array, final int n2, final float[] array2) {
        final int n3 = n - 5;
        System.arraycopy(array, n2 - n, array2, 0, n);
        final float n4 = 0.2f;
        float n5 = 0.0f;
        int n6 = n2 - 5;
        int n7 = n2 - n - 5;
        for (int i = n3; i < n; ++i) {
            final float n8 = (1.0f - n5) * array[n6] + n5 * array[n7];
            ++n6;
            ++n7;
            array2[i] = n8;
            n5 += n4;
        }
        System.arraycopy(array, n2 - n, array2, n, g.A - n);
    }
    
    public c(final int for1) throws Error {
        this.int = null;
        this.for = for1;
        if (this.for == 30 || this.for == 20) {
            this.int = new d(this.for);
            this.if = new float[g.i];
            this.do = new float[g.i];
            this.try = new float[g.i];
            this.a = new float[g.ap + g.aG];
            this.new = new float[4];
            for (int i = 0; i < this.if.length; ++i) {
                this.if[i] = 0.0f;
            }
            System.arraycopy(g.F, 0, this.try, 0, g.i);
            System.arraycopy(g.F, 0, this.do, 0, g.i);
            for (int j = 0; j < this.a.length; ++j) {
                this.a[j] = 0.0f;
            }
            for (int k = 0; k < this.new.length; ++k) {
                this.new[k] = 0.0f;
            }
            return;
        }
        throw new Error("invalid mode");
    }
    
    public short a(final short[] array, final short[] array2) {
        final float[] array3 = new float[this.int.new];
        final a a = new a(this.int.char * 2);
        for (int i = 0; i < this.int.new; ++i) {
            array3[i] = array2[i];
        }
        this.a(a, array3);
        for (int j = 0; j < array.length; ++j) {
            array[j] = (short)((a.a[2 * j] << 8 & '\uff00') | ((short)a.a[2 * j + 1] & 0xFF));
        }
        return (short)this.int.char;
    }
    
    public void a(final a a, final float[] array) {
        final int[] array2 = { 0 };
        final float[] array3 = new float[g.aG];
        final float[] array4 = new float[g.aG];
        final float[] array5 = new float[g.aG];
        final int[] array6 = new int[g.void];
        final float[] array7 = new float[g.aG];
        final float[] array8 = new float[g.G];
        final int[] array9 = new int[g.as * g.aP];
        final int[] array10 = new int[g.as];
        final int[] array11 = new int[g.as * g.aP];
        final int[] array12 = new int[g.as];
        final int[] array13 = new int[g.ao * g.t];
        final float[] array14 = new float[g.i];
        final float[] array15 = new float[g.U * (g.i + 1)];
        final float[] array16 = new float[g.U * (g.i + 1)];
        final float[] array17 = new float[g.aG];
        this.a(array, this.int.new, array3, this.new);
        this.a(array15, array16, array13, array3);
        for (int i = 0; i < this.int.try; ++i) {
            this.a(array3, i * g.A, array15, i * (g.i + 1), g.A, array4, i * g.A, this.if);
        }
        int n = this.a(array4);
        final int n2 = g.void - this.int.c;
        float n3 = 0.0f;
        final int n4 = (n - 1) * g.A;
        for (int j = 0; j < this.int.c; ++j) {
            n3 += array4[n4 + j] * array4[n4 + j];
        }
        float n5 = 0.0f;
        final int n6 = (n - 1) * g.A + n2;
        for (int k = 0; k < this.int.c; ++k) {
            n5 += array4[n6 + k] * array4[n6 + k];
        }
        int if1;
        int n7;
        if (n3 > n5) {
            if1 = 1;
            n7 = (n - 1) * g.A;
        }
        else {
            if1 = 0;
            n7 = (n - 1) * g.A + n2;
        }
        this.a(array4, n7, array15, (n - 1) * (g.i + 1), array16, (n - 1) * (g.i + 1), array2, array6, this.int.c, if1);
        b.a(array2[0], array6, array15, (n - 1) * (g.i + 1), array17, n7, this.int.c);
        if (if1 != 0) {
            for (int l = 0; l < g.G - this.int.c; ++l) {
                array8[l] = 0.0f;
            }
            System.arraycopy(array17, n7, array8, g.G - this.int.c, this.int.c);
            for (int n8 = 0; n8 < g.i; ++n8) {
                array14[n8] = 0.0f;
            }
            this.a(array12, 0, array10, 0, array4, n7 + this.int.c, array8, g.G - g.B, g.B, n2, g.as, array16, n * (g.i + 1), array14, 0);
            b.a(array17, n7 + this.int.c, array12, 0, array10, 0, array8, g.G - g.B, g.B, n2, g.as);
        }
        else {
            for (int n9 = 0; n9 < n2; ++n9) {
                array5[n9] = array4[(n + 1) * g.A - 1 - (n9 + this.int.c)];
            }
            int c;
            int n10;
            for (c = this.int.c, n10 = 0; n10 < c; ++n10) {
                array8[g.G - 1 - n10] = array17[n7 + n10];
            }
            for (int n11 = 0; n11 < g.G - n10; ++n11) {
                array8[n11] = 0.0f;
            }
            for (int n12 = 0; n12 < g.i; ++n12) {
                array14[n12] = 0.0f;
            }
            this.a(array12, 0, array10, 0, array5, 0, array8, g.G - g.B, g.B, n2, g.as, array16, (n - 1) * (g.i + 1), array14, 0);
            b.a(array7, 0, array12, 0, array10, 0, array8, g.G - g.B, g.B, n2, g.as);
            for (int n13 = 0; n13 < n2; ++n13) {
                array17[n7 - 1 - n13] = array7[n13];
            }
        }
        int n14 = 0;
        final int n15 = this.int.try - n - 1;
        if (n15 > 0) {
            for (int n16 = 0; n16 < g.G - g.void; ++n16) {
                array8[n16] = 0.0f;
            }
            System.arraycopy(array17, (n - 1) * g.A, array8, g.G - g.void, g.void);
            for (int n17 = 0; n17 < g.i; ++n17) {
                array14[n17] = 0.0f;
            }
            for (int n18 = 0; n18 < n15; ++n18) {
                this.a(array11, n14 * g.as, array9, n14 * g.as, array4, (n + 1 + n18) * g.A, array8, g.G - g.d[n14], g.d[n14], g.A, g.as, array16, (n + 1 + n18) * (g.i + 1), array14, n14 + 1);
                b.a(array17, (n + 1 + n18) * g.A, array11, n14 * g.as, array9, n14 * g.as, array8, g.G - g.d[n14], g.d[n14], g.A, g.as);
                System.arraycopy(array8, g.A, array8, 0, g.G - g.A);
                System.arraycopy(array17, (n + 1 + n18) * g.A, array8, g.G - g.A, g.A);
                for (int n19 = 0; n19 < g.i; ++n19) {
                    array14[n19] = 0.0f;
                }
                ++n14;
            }
        }
        final int n20 = n - 1;
        if (n20 > 0) {
            for (int n21 = 0; n21 < n20; ++n21) {
                for (int n22 = 0; n22 < g.A; ++n22) {
                    array5[n21 * g.A + n22] = array4[(n - 1) * g.A - 1 - n21 * g.A - n22];
                    array7[n21 * g.A + n22] = array17[(n - 1) * g.A - 1 - n21 * g.A - n22];
                }
            }
            int g = c.g.A * (this.int.try + 1 - n);
            if (g > c.g.G) {
                g = c.g.G;
            }
            int n23;
            for (n23 = 0; n23 < g; ++n23) {
                array8[c.g.G - 1 - n23] = array17[(n - 1) * c.g.A + n23];
            }
            for (int n24 = 0; n24 < c.g.G - n23; ++n24) {
                array8[n24] = 0.0f;
            }
            for (int n25 = 0; n25 < c.g.i; ++n25) {
                array14[n25] = 0.0f;
            }
            for (int n26 = 0; n26 < n20; ++n26) {
                this.a(array11, n14 * c.g.as, array9, n14 * c.g.as, array5, n26 * c.g.A, array8, c.g.G - c.g.d[n14], c.g.d[n14], c.g.A, c.g.as, array16, (n - 2 - n26) * (c.g.i + 1), array14, n14 + 1);
                b.a(array7, n26 * c.g.A, array11, n14 * c.g.as, array9, n14 * c.g.as, array8, c.g.G - c.g.d[n14], c.g.d[n14], c.g.A, c.g.as);
                System.arraycopy(array8, c.g.A, array8, 0, c.g.G - c.g.A);
                System.arraycopy(array7, n26 * c.g.A, array8, c.g.G - c.g.A, c.g.A);
                for (int n27 = 0; n27 < c.g.i; ++n27) {
                    array14[n27] = 0.0f;
                }
                ++n14;
            }
            for (int n28 = 0; n28 < c.g.A * n20; ++n28) {
                array17[c.g.A * n20 - n28 - 1] = array7[n28];
            }
        }
        this.a(array11);
        for (int n29 = 0; n29 < 3; ++n29) {
            final int[] array18 = { 0 };
            for (int n30 = 0; n30 < g.ao * this.int.a; ++n30) {
                final e a2 = a.a(array13[n30], this.int.d[n30][n29], this.int.d[n30][n29] + this.int.d[n30][n29 + 1] + this.int.d[n30][n29 + 2]);
                final int a3 = a2.a();
                array13[n30] = a2.if();
                a.a(a3, this.int.d[n30][n29]);
            }
            final e a4 = a.a(n, this.int.do[n29], this.int.do[n29] + this.int.do[n29 + 1] + this.int.do[n29 + 2]);
            final int a5 = a4.a();
            n = a4.if();
            a.a(a5, this.int.do[n29]);
            final e a6 = a.a(if1, this.int.goto[n29], this.int.goto[n29] + this.int.goto[n29 + 1] + this.int.goto[n29 + 2]);
            final int a7 = a6.a();
            if1 = a6.if();
            a.a(a7, this.int.goto[n29]);
            final e a8 = a.a(array2[0], this.int.b[n29], this.int.b[n29] + this.int.b[n29 + 1] + this.int.b[n29 + 2]);
            final int a9 = a8.a();
            array2[0] = a8.if();
            a.a(a9, this.int.b[n29]);
            for (int n31 = 0; n31 < this.int.c; ++n31) {
                final e a10 = a.a(array6[n31], this.int.case[n29], this.int.case[n29] + this.int.case[n29 + 1] + this.int.case[n29 + 2]);
                final int a11 = a10.a();
                array6[n31] = a10.if();
                a.a(a11, this.int.case[n29]);
            }
            for (int n32 = 0; n32 < g.as; ++n32) {
                final e a12 = a.a(array12[n32], this.int.void[n32][n29], this.int.void[n32][n29] + this.int.void[n32][n29 + 1] + this.int.void[n32][n29 + 2]);
                final int a13 = a12.a();
                array12[n32] = a12.if();
                a.a(a13, this.int.void[n32][n29]);
            }
            for (int n33 = 0; n33 < g.as; ++n33) {
                final e a14 = a.a(array10[n33], this.int.long[n33][n29], this.int.long[n33][n29] + this.int.long[n33][n29 + 1] + this.int.long[n33][n29 + 2]);
                final int a15 = a14.a();
                array10[n33] = a14.if();
                a.a(a15, this.int.long[n33][n29]);
            }
            for (int n34 = 0; n34 < this.int.int; ++n34) {
                for (int n35 = 0; n35 < g.as; ++n35) {
                    final e a16 = a.a(array11[n34 * g.as + n35], this.int.if[n34][n35][n29], this.int.if[n34][n35][n29] + this.int.if[n34][n35][n29 + 1] + this.int.if[n34][n35][n29 + 2]);
                    final int a17 = a16.a();
                    array11[n34 * g.as + n35] = a16.if();
                    a.a(a17, this.int.if[n34][n35][n29]);
                }
            }
            for (int n36 = 0; n36 < this.int.int; ++n36) {
                for (int n37 = 0; n37 < g.as; ++n37) {
                    final e a18 = a.a(array9[n36 * g.as + n37], this.int.byte[n36][n37][n29], this.int.byte[n36][n37][n29] + this.int.byte[n36][n37][n29 + 1] + this.int.byte[n36][n37][n29 + 2]);
                    final int a19 = a18.a();
                    array9[n36 * g.as + n37] = a18.if();
                    a.a(a19, this.int.byte[n36][n37][n29]);
                }
            }
        }
        a.a(0, 1);
    }
}
