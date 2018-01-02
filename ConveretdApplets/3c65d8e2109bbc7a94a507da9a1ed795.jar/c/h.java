// 
// Decompiled by Procyon v0.5.30
// 

package c;

class h
{
    int if;
    int goto;
    int new;
    int d;
    int void;
    float do;
    float[] c;
    long try;
    float[] int;
    d char;
    float[] byte;
    float[] long;
    float[] for;
    float[] a;
    int else;
    float[] b;
    float[] case;
    
    void a(final float[] array, final int n, final float[] array2, final int n2, final int n3, final float[] array3) {
        int n4 = n;
        for (int i = 0; i < g.i; ++i) {
            int n5 = n + i - 1;
            int n6 = n2 + 1;
            int n7 = g.i - 1;
            for (int j = 1; j <= i; ++j) {
                final int n8 = n4;
                array[n8] -= array2[n6] * array[n5];
                ++n6;
                --n5;
            }
            for (int k = i + 1; k < g.i + 1; ++k) {
                final int n9 = n4;
                array[n9] -= array2[n6] * array3[n7];
                ++n6;
                --n7;
            }
            ++n4;
        }
        for (int l = g.i; l < n3; ++l) {
            int n10 = n + l - 1;
            int n11 = n2 + 1;
            for (int n12 = 1; n12 < g.i + 1; ++n12) {
                final int n13 = n4;
                array[n13] -= array2[n11] * array[n10];
                ++n11;
                --n10;
            }
            ++n4;
        }
        System.arraycopy(array, n + n3 - g.i, array3, 0, g.i);
    }
    
    public void a(final float[] array, final float[] array2, final float[] array3, final int n, final float n2, final int n3) {
        final float[] array4 = new float[g.i];
        c.b.a(array4, array2, array3, n, n2, n3);
        c.b.a(array, array4);
    }
    
    void a(final float[] array, final int[] array2, final int n) {
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < g.ao; ++i) {
            for (int j = 0; j < g.p[i]; ++j) {
                array[n2 + j] = g.af[n3 + (array2[i] * g.p[i] + j)];
            }
            n2 += g.p[i];
            n3 += g.N[i] * g.p[i];
        }
        if (n > 1) {
            int n4 = 0;
            int n5 = 0;
            for (int k = 0; k < g.ao; ++k) {
                for (int l = 0; l < g.p[k]; ++l) {
                    array[g.i + n4 + l] = g.af[n5 + array2[g.ao + k] * g.p[k] + l];
                }
                n4 += g.p[k];
                n5 += g.N[k] * g.p[k];
            }
        }
    }
    
    void a(final float[] array, final float[] array2, final float[] array3, final int n) {
        final float[] array4 = new float[g.i + 1];
        final int n2 = n + 1;
        if (this.char.else == 30) {
            this.a(array4, this.long, array3, 0, g.long[0], n);
            System.arraycopy(array4, 0, array, 0, n2);
            c.b.a(array2, 0, array4, g.byte, n2);
            int n3 = n2;
            for (int i = 1; i < 6; ++i) {
                this.a(array4, array3, array3, n, g.long[i], n);
                System.arraycopy(array4, 0, array, n3, n2);
                c.b.a(array2, n3, array4, g.byte, n2);
                n3 += n2;
            }
        }
        else {
            int n4 = 0;
            for (int j = 0; j < this.char.try; ++j) {
                this.a(array4, this.long, array3, 0, g.else[j], n);
                System.arraycopy(array4, 0, array, n4, n2);
                c.b.a(array2, n4, array4, g.byte, n2);
                n4 += n2;
            }
        }
        if (this.char.else == 30) {
            System.arraycopy(array3, n, this.long, 0, n);
        }
        else {
            System.arraycopy(array3, 0, this.long, 0, n);
        }
    }
    
    public void a(final int[] array) {
        for (int i = 1; i < g.as; ++i) {
            if (array[i] >= 44 && array[i] < 108) {
                final int n = i;
                array[n] += 64;
            }
            else if (array[i] >= 108 && array[i] < 128) {
                final int n2 = i;
                array[n2] += 128;
            }
        }
    }
    
    public void a(final float[] array, final int n, final float[] array2, final float[] array3) {
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            array2[n3] = g.am[0] * array[n2];
            final int n4 = n3;
            array2[n4] += g.am[1] * array3[0];
            final int n5 = n3;
            array2[n5] += g.am[2] * array3[1];
            array3[1] = array3[0];
            array3[0] = array[n2];
            ++n3;
            ++n2;
        }
        int n6 = 0;
        for (int j = 0; j < n; ++j) {
            final int n7 = n6;
            array2[n7] -= g.v[1] * array3[2];
            final int n8 = n6;
            array2[n8] -= g.v[2] * array3[3];
            array3[3] = array3[2];
            array3[2] = array2[n6];
            ++n6;
        }
    }
    
    void a(final float[] array, final int n, final float[] array2, final int n2, final float[] array3, final float[] array4) {
        int n3 = 0;
        for (int i = g.aK; i < n2; i += g.e) {
            int n4 = 0;
            int n5 = n + i;
            int n6 = g.aa - 2;
            float n7 = 0.0f;
            int aa;
            if (i < g.aa) {
                aa = i + 1;
            }
            else {
                aa = g.aa;
            }
            for (int j = 0; j < aa; ++j) {
                n7 += array2[n4] * array[n5];
                ++n4;
                --n5;
            }
            for (int k = i + 1; k < g.aa; ++k) {
                n7 += array2[n4] * array3[n6];
                ++n4;
                --n6;
            }
            array4[n3] = n7;
            ++n3;
        }
        for (int l = n2 + g.e; l < n2 + g.aK; l += g.e) {
            float n8 = 0.0f;
            if (l < n2) {
                int n9 = 0;
                for (int n10 = 0; n10 < g.aa; ++n10) {
                    n8 += array2[n9] * array4[n3];
                    ++n9;
                    --n3;
                }
            }
            else {
                int n11 = l - n2;
                int n12 = n + n2 - 1;
                for (int n13 = 0; n13 < g.aa - (l - n2); ++n13) {
                    n8 += array2[n11] * array[n12];
                    ++n11;
                    --n12;
                }
            }
            array4[n3] = n8;
            ++n3;
        }
    }
    
    public int a(final float[] array, final float n, final int n2) {
        final float n3 = array[0] - n;
        float n4 = n3 * n3;
        int n5 = 0;
        for (int i = 1; i < n2; ++i) {
            final float n6 = array[i] - n;
            final float n7 = n6 * n6;
            if (n7 < n4) {
                n4 = n7;
                n5 = i;
            }
        }
        return n5;
    }
    
    public void a(final float[] array, final int n, final float[] array2, final int n2, final int n3, final float[] array3, final int n4, final int n5) {
        for (int i = 0; i <= n3 - n5; ++i) {
            if (n + i < array.length) {
                array[n + i] = 0.0f;
            }
            for (int j = 0; j < n5; ++j) {
                final int n6 = n + i;
                array[n6] += array2[n2 + i + j] * array3[n4 + j];
            }
        }
    }
    
    public void a(final float[] array, final float[] array2, final int n, int n2) {
        final int[] array3 = new int[g.Y];
        int n3 = 2 * n2 + 1;
        if (n3 > n) {
            final int n4 = n / 2;
            for (int i = 0; i < g.Y; ++i) {
                array3[i] = i * n3 + n2 - n4;
            }
            n2 = n4;
            n3 = 2 * n2 + 1;
        }
        else {
            for (int j = 0; j < g.Y; ++j) {
                array3[j] = j * n3;
            }
        }
        int n5 = 0;
        for (int k = n2; k < n3; ++k) {
            for (int l = 0; l < g.Y; ++l) {
                array[n5] = 0.0f;
                int n6 = array3[l];
                int n7 = k;
                for (int n8 = 0; n8 <= k; ++n8) {
                    final int n9 = n5;
                    array[n9] += array2[n7] * g.for[n6];
                    --n7;
                    ++n6;
                }
                ++n5;
            }
        }
        for (int n10 = n3; n10 < n; ++n10) {
            for (int n11 = 0; n11 < g.Y; ++n11) {
                array[n5] = 0.0f;
                int n12 = array3[n11];
                int n13 = n10;
                for (int n14 = 0; n14 < n3; ++n14) {
                    final int n15 = n5;
                    array[n15] += array2[n13] * g.for[n12];
                    --n13;
                    ++n12;
                }
                ++n5;
            }
        }
        for (int n16 = 1; n16 <= n2; ++n16) {
            for (int n17 = 0; n17 < g.Y; ++n17) {
                array[n5] = 0.0f;
                int n18 = array3[n17] + n16;
                int n19 = n - 1;
                for (int n20 = 0; n20 < n3 - n16; ++n20) {
                    final int n21 = n5;
                    array[n21] += array2[n19] * g.for[n18];
                    --n19;
                    ++n18;
                }
                ++n5;
            }
        }
    }
    
    public float a(final float[] array, final int n, final float[] array2, final int n2, final int n3, final float n4, final float n5) {
        final float[] array3 = new float[g.j];
        final float[] array4 = new float[g.u];
        final float[] array5 = new float[g.u * g.Y];
        final int n6 = (int)(n4 - 0.5);
        int n7 = n6 - g.h;
        if (n7 < 0) {
            n7 = 0;
        }
        int n8 = n6 + g.h;
        if (n8 + g.c >= n2) {
            n8 = n2 - g.c - 1;
        }
        final int n9 = n8 - n7 + 1;
        this.a(array4, 0, array2, n7, n9 + g.c - 1, array2, n3, g.c);
        this.a(array5, array4, n9, g.do);
        int n10 = 0;
        float n11 = array5[0];
        for (int i = 1; i < g.Y * n9; ++i) {
            if (array5[i] > n11) {
                n10 = i;
                n11 = array5[i];
            }
        }
        final float n12 = n7 + n10 / g.Y + 1.0f;
        int n13 = n10 / g.Y;
        if (n10 > n13 * g.Y) {
            ++n13;
        }
        final int n14 = n7 + n13 - g.do;
        if (n14 < 0) {
            for (int j = 0; j < -n14; ++j) {
                array3[j] = 0.0f;
            }
            System.arraycopy(array2, 0, array3, -n14, g.j + n14);
        }
        else {
            final int n15 = n14 + g.j;
            if (n15 > n2) {
                System.arraycopy(array2, n14, array3, 0, g.j - (n15 - n2));
                for (int k = 0; k < n15 - n2; ++k) {
                    array3[g.j - (n15 - n2) + k] = 0.0f;
                }
            }
            else {
                System.arraycopy(array2, n14, array3, 0, g.j);
            }
        }
        this.a(array, n, array3, 0, g.j, g.for, (2 * g.do + 1) * (n13 * g.Y - n10), 2 * g.do + 1);
        return n12;
    }
    
    public void a(final float[] array, final int n, final float[] array2, final int n2, final float n3) {
        final float[] array3 = new float[g.aG];
        final float[] array4 = new float[2 * g.aL + 1];
        for (int i = 1; i <= 2 * n2 + 1; ++i) {
            array4[i - 1] = 0.5f * (1.0f - (float)Math.cos(2.0f * g.ah * i / (2 * n2 + 2)));
        }
        array4[n2] = 0.0f;
        for (int j = 0; j < g.c; ++j) {
            array3[j] = array2[j] * array4[0];
        }
        for (int k = 1; k < n2; ++k) {
            final int n4 = k * g.c;
            for (int l = 0; l < g.c; ++l) {
                final float[] array5 = array3;
                final int n5 = l;
                array5[n5] += array2[n4 + l] * array4[k];
            }
        }
        for (int n6 = n2 + 1; n6 <= 2 * n2; ++n6) {
            final int n7 = n6 * g.c;
            for (int n8 = 0; n8 < g.c; ++n8) {
                final float[] array6 = array3;
                final int n9 = n8;
                array6[n9] += array2[n7 + n8] * array4[n6];
            }
        }
        float n12;
        float n11;
        float n10 = n11 = (n12 = 0.0f);
        final int n13 = n2 * g.c;
        for (int n14 = 0; n14 < g.c; ++n14) {
            n11 += array2[n13 + n14] * array2[n13 + n14];
            n12 += array3[n14] * array3[n14];
            n10 += array3[n14] * array2[n13 + n14];
        }
        if (Math.abs(n12) < 1.0f) {
            n12 = 1.0f;
        }
        final float n15 = (float)Math.sqrt(n11 / n12);
        float n16 = 0.0f;
        final int n17 = n2 * g.c;
        for (int n18 = 0; n18 < g.c; ++n18) {
            array[n + n18] = n15 * array3[n18];
            final float n19 = array2[n17 + n18] - array[n + n18];
            n16 += n19 * n19;
        }
        if (n16 > n3 * n11) {
            if (n11 < 1.0f) {
                n11 = 1.0f;
            }
            final float n20 = (n12 * n11 - n10 * n10) / (n11 * n11);
            float n21;
            float n22;
            if (n20 > 1.0E-4f) {
                n21 = (float)Math.sqrt((n3 - n3 * n3 / 4.0f) / n20);
                n22 = -n3 / 2.0f - n21 * n10 / n11 + 1.0f;
            }
            else {
                n21 = 0.0f;
                n22 = 1.0f;
            }
            final int n23 = n2 * g.c;
            for (int n24 = 0; n24 < g.c; ++n24) {
                array[n + n24] = n21 * array3[n24] + n22 * array2[n23 + n24];
            }
        }
    }
    
    public void a(final float[] array, final float[] array2, final int n, final int n2, final float[] array3, final float[] array4, final int n3, final int n4) {
        final float[] array5 = new float[2 * g.aL + 1];
        final int[] array6 = new int[2 * g.aL + 1];
        final float[] array7 = new float[g.aR];
        array6[n4] = this.a(array4, 0.5f * (n2 + (n2 + g.c - 1)), n3);
        array5[n4] = n2;
        System.arraycopy(array2, n2, array, g.c * n4, g.c);
        for (int i = n4 - 1; i >= 0; --i) {
            array5[i] = array5[i + 1] - array3[array6[i + 1]];
            array6[i] = this.a(array4, array5[i] + g.s - array3[array6[i + 1]], n3);
            if (array5[i] - g.ac >= 0.0f) {
                array5[i] = this.a(array, i * g.c, array2, n, n2, array5[i], array3[array6[i + 1]]);
            }
            else {
                final int n5 = i * g.c;
                for (int j = 0; j < g.c; ++j) {
                    array[n5 + j] = 0.0f;
                }
            }
        }
        for (int k = 0; k < n3; ++k) {
            array7[k] = array4[k] - array3[k];
        }
        for (int l = n4 + 1; l <= 2 * n4; ++l) {
            array6[l] = this.a(array7, array5[l - 1] + g.s, n3);
            array5[l] = array5[l - 1] + array3[array6[l]];
            if (array5[l] + g.c + g.ac < n) {
                array5[l] = this.a(array, l * g.c, array2, n, n2, array5[l], array3[array6[l]]);
            }
            else {
                final int n6 = l * g.c;
                for (int n7 = 0; n7 < g.c; ++n7) {
                    array[n6 + n7] = 0.0f;
                }
            }
        }
    }
    
    public void a(final float[] array, final int n, final float[] array2, final int n2, final int n3, final float n4, final float[] array3, final float[] array4, final int n5) {
        final float[] array5 = new float[(2 * g.aL + 1) * g.c];
        this.a(array5, array2, n2, n3, array3, array4, n5, g.aL);
        this.a(array, n, array5, g.aL, n4);
    }
    
    public float a(final float[] array, final int n, final float[] array2, final int n2, final int n3) {
        float n4 = 0.0f;
        float n5 = 0.0f;
        for (int i = 0; i < n3; ++i) {
            n4 += array[n + i] * array2[n2 + i];
            n5 += array2[n2 + i] * array2[n2 + i];
        }
        if (n4 > 0.0f) {
            return n4 * n4 / n5;
        }
        return 0.0f;
    }
    
    int a(final float[] array, final float[] array2) {
        int n = 0;
        final float[] array3 = new float[g.c];
        final float[] array4 = new float[6];
        final float[] array5 = new float[(g.try * g.c + 120) / 2];
        final int n2 = g.try * g.c + 120;
        System.arraycopy(this.b, this.char.new, this.b, 0, g.aA - this.char.new);
        System.arraycopy(array2, 0, this.b, g.aA - this.char.new, this.char.new);
        int c;
        if (this.char.else == 30) {
            c = g.c;
        }
        else {
            c = 40;
        }
        int n3 = 0;
        if (this.char.else == 20) {
            n3 = 1;
        }
        final int n4 = 3 - n3;
        System.arraycopy(this.case, n4, this.case, 0, g.ax - n4);
        System.arraycopy(this.b, (g.S + n3) * g.c - 126, array4, 0, 6);
        this.a(this.b, (g.S + n3) * g.c - 120, g.ar, n2 - n3 * g.c, array4, array5);
        for (int i = 0; i < g.try - n3; ++i) {
            n = 10;
            float a = this.a(array5, 60 + i * g.s, array5, 60 + i * g.s - n, g.s);
            for (int j = 11; j < 60; ++j) {
                final float a2 = this.a(array5, 60 + i * g.s, array5, 60 + i * g.s - j, g.s);
                if (a2 > a) {
                    a = a2;
                    n = j;
                }
            }
            this.case[i + g.S + n3] = n * 2.0f;
        }
        if (this.void == 1) {
            final int n5 = (int)this.case[g.S + n3];
            n = n5 - 1;
            float a3 = this.a(array2, 0, array2, n, c);
            for (int k = n5; k <= n5 + 1; ++k) {
                final float a4 = this.a(array2, 0, array2, k, c);
                if (a4 > a3) {
                    a3 = a4;
                    n = k;
                }
            }
            this.case[g.S + n3 - 1] = n;
            int n6 = n - 1;
            int n7 = c - 1;
            int n8;
            if (n > c) {
                n8 = c;
            }
            else {
                n8 = n;
            }
            for (int l = n8; l > 0; --l) {
                array3[n7] = array2[n6];
                --n7;
                --n6;
            }
            int n9 = g.aA - 1 - this.char.new;
            for (int n10 = c - 1 - n; n10 >= 0; --n10) {
                array3[n7] = this.b[n9];
                --n7;
                --n9;
            }
            float n11 = 0.0f;
            float n12 = 0.0f;
            for (int n13 = 0; n13 < c; ++n13) {
                n11 += this.b[g.aA - 1 - this.char.new - n13] * this.b[g.aA - 1 - this.char.new - n13];
                n12 += array3[n13] * array3[n13];
            }
            final float n14 = (float)Math.sqrt(n12 / c);
            final float n15 = (float)Math.sqrt(n11 / c);
            if (n14 > 2.0f * n15 && n14 > 0.0) {
                for (int n16 = 0; n16 < c - 10; ++n16) {
                    final float[] array6 = array3;
                    final int n17 = n16;
                    array6[n17] *= 2.0f * n15 / n14;
                }
                for (int n18 = c - 10; n18 < c; ++n18) {
                    final float[] array7 = array3;
                    final int n19 = n18;
                    array7[n19] *= (n18 - c + 10) * (1.0f - 2.0f * n15 / n14) / 10.0f + 2.0f * n15 / n14;
                }
            }
            int n20 = g.aA - 1 - this.char.new;
            for (int n21 = 0; n21 < c; ++n21) {
                final float n22 = (n21 + 1) / (c + 1);
                final float[] b = this.b;
                final int n23 = n20;
                b[n23] *= n22;
                final float[] b2 = this.b;
                final int n24 = n20;
                b2[n24] += (1.0f - n22) * array3[c - 1 - n21];
                --n20;
            }
        }
        if (this.char.else == 20) {
            for (int n25 = 0; n25 < 2; ++n25) {
                this.a(array, n25 * g.c, this.b, g.aA, (5 + n25) * g.c + 40, g.X, this.case, g.al, g.ax);
            }
        }
        else if (this.char.else == 30) {
            for (int n26 = 0; n26 < 3; ++n26) {
                this.a(array, n26 * g.c, this.b, g.aA, (4 + n26) * g.c, g.X, this.case, g.al, g.ax);
            }
        }
        return n * 2;
    }
    
    public void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n, final int n2, int n3) {
        if (n2 - n3 - n < 0) {
            n3 = n2 - n;
        }
        float n4 = 0.0f;
        float n5 = 0.0f;
        float n6 = 0.0f;
        for (int i = 0; i < n3; ++i) {
            n4 += array4[n2 - n3 + i] * array4[n2 - n3 + i - n];
            n5 += array4[n2 - n3 + i - n] * array4[n2 - n3 + i - n];
            n6 += array4[n2 - n3 + i] * array4[n2 - n3 + i];
        }
        if (n5 > 0.0f) {
            array[0] = n4 * n4 / n5;
            array2[0] = Math.abs(n4 / n5);
            array3[0] = Math.abs(n4) / ((float)Math.sqrt(n5) * (float)Math.sqrt(n6));
        }
        else {
            array[0] = 0.0f;
            array3[0] = (array2[0] = 0.0f);
        }
    }
    
    public void a(final float[] array, final float[] array2, final int goto1, final float[] array3, final float[] array4, final int n, final int n2) {
        int new1 = 20;
        final float n3 = 0.0f;
        final float n4 = 0.0f;
        float n5 = 0.0f;
        float n6 = 0.0f;
        float n7 = 0.0f;
        float do1 = 0.0f;
        final float[] array5 = new float[g.aG];
        final float[] array6 = { 0.0f };
        final float[] array7 = { 0.0f };
        final float[] array8 = { 0.0f };
        if (goto1 == 1) {
            ++this.if;
            if (this.goto != 1) {
                new1 = n2 - 3;
                array7[0] = n4;
                array6[0] = n3;
                array8[0] = do1;
                this.a(array7, array6, array8, this.c, new1, this.char.new, 60);
                float n8 = array7[0];
                final float n9 = array6[0];
                do1 = array8[0];
                for (int i = n2 - 2; i <= n2 + 3; ++i) {
                    array7[0] = n6;
                    array6[0] = n5;
                    array8[0] = n7;
                    this.a(array7, array6, array8, this.c, i, this.char.new, 60);
                    n6 = array7[0];
                    n5 = array6[0];
                    n7 = array8[0];
                    if (n6 > n8) {
                        n8 = n6;
                        new1 = i;
                        do1 = n7;
                    }
                }
            }
            else {
                new1 = this.new;
                do1 = this.do;
            }
            float n10 = 1.0f;
            if (this.if * this.char.new > 320) {
                n10 = 0.9f;
            }
            else if (this.if * this.char.new > 640) {
                n10 = 0.7f;
            }
            else if (this.if * this.char.new > 960) {
                n10 = 0.5f;
            }
            else if (this.if * this.char.new > 1280) {
                n10 = 0.0f;
            }
            final float n11 = (float)Math.sqrt(do1);
            float n12;
            if (n11 > 0.7f) {
                n12 = 1.0f;
            }
            else if (n11 > 0.4f) {
                n12 = (n11 - 0.4f) / 0.29999998f;
            }
            else {
                n12 = 0.0f;
            }
            int n13 = new1;
            if (new1 < 80) {
                n13 = 2 * new1;
            }
            float n14 = 0.0f;
            for (int j = 0; j < this.char.new; ++j) {
                this.try = (this.try * 69069L + 1L & 0x7FFFFFFFL);
                final int n15 = j - (50 + (int)(this.try % 70L));
                if (n15 < 0) {
                    array5[j] = this.c[this.char.new + n15];
                }
                else {
                    array5[j] = array5[n15];
                }
                final int n16 = j - n13;
                if (n16 < 0) {
                    array[j] = this.c[this.char.new + n16];
                }
                else {
                    array[j] = array[n16];
                }
                if (j < 80) {
                    array[j] = n10 * (n12 * array[j] + (1.0f - n12) * array5[j]);
                }
                else if (j < 160) {
                    array[j] = 0.95f * n10 * (n12 * array[j] + (1.0f - n12) * array5[j]);
                }
                else {
                    array[j] = 0.9f * n10 * (n12 * array[j] + (1.0f - n12) * array5[j]);
                }
                n14 += array[j] * array[j];
            }
            if ((float)Math.sqrt(n14 / this.char.new) < 30.0f) {
                for (int k = 0; k < this.char.new; ++k) {
                    array[k] = array5[k];
                }
            }
            System.arraycopy(this.int, 0, array2, 0, g.i + 1);
        }
        else {
            System.arraycopy(array3, 0, array, 0, this.char.new);
            System.arraycopy(array4, n, array2, 0, g.i + 1);
            this.if = 0;
        }
        if (goto1 != 0) {
            this.new = new1;
            this.do = do1;
        }
        this.goto = goto1;
        System.arraycopy(array2, 0, this.int, 0, g.i + 1);
        System.arraycopy(array, 0, this.c, 0, this.char.new);
    }
    
    public short a(final short[] array, final short[] array2, final short n) {
        final float[] array3 = new float[g.aG];
        final a a = new a(this.char.char);
        if (n < 0 || n > 1) {
            System.out.println("\nERROR - Wrong mode - 0, 1 allowed\n");
        }
        for (int i = 0; i < array2.length; ++i) {
            a.a[2 * i + 1] = (char)(array2[i] & 0xFF);
            a.a[2 * i] = (char)(array2[i] >> 8 & 0xFF);
        }
        this.a(array3, a, n);
        for (int j = 0; j < this.char.new; ++j) {
            float n2 = array3[j];
            if (n2 < g.az) {
                n2 = g.az;
            }
            else if (n2 > g.aq) {
                n2 = g.aq;
            }
            array[j] = (short)n2;
        }
        return (short)this.char.new;
    }
    
    public void a(final float[] array, final int n, final int n2, final int[] array2, final float[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int n3) {
        final float[] array8 = new float[g.aG];
        final float[] array9 = new float[g.G];
        final int n4 = g.void - this.char.c;
        int n5;
        if (n3 == 1) {
            n5 = (n - 1) * g.A;
        }
        else {
            n5 = (n - 1) * g.A + n4;
        }
        c.b.a(n2, array2, array3, (n - 1) * (g.i + 1), array, n5, this.char.c);
        if (n3 != 0) {
            for (int i = 0; i < g.G - this.char.c; ++i) {
                array9[i] = 0.0f;
            }
            System.arraycopy(array, n5, array9, g.G - this.char.c, this.char.c);
            c.b.a(array, n5 + this.char.c, array6, 0, array7, 0, array9, g.G - g.B, g.B, n4, g.as);
        }
        else {
            for (int j = 0; j < n4; ++j) {
                array8[j] = array[(n + 1) * g.A - 1 - (j + this.char.c)];
            }
            int c;
            int k;
            for (c = this.char.c, k = 0; k < c; ++k) {
                array9[g.G - 1 - k] = array[n5 + k];
            }
            for (int l = 0; l < g.G - k; ++l) {
                array9[l] = 0.0f;
            }
            c.b.a(array8, 0, array6, 0, array7, 0, array9, g.G - g.B, g.B, n4, g.as);
            for (int n6 = 0; n6 < n4; ++n6) {
                array[n5 - 1 - n6] = array8[n6];
            }
        }
        int n7 = 0;
        final int n8 = this.char.try - n - 1;
        if (n8 > 0) {
            for (int n9 = 0; n9 < g.G - g.void; ++n9) {
                array9[n9] = 0.0f;
            }
            System.arraycopy(array, (n - 1) * g.A, array9, g.G - g.void, g.void);
            for (int n10 = 0; n10 < n8; ++n10) {
                c.b.a(array, (n + 1 + n10) * g.A, array4, n7 * g.as, array5, n7 * g.as, array9, g.G - g.d[n7], g.d[n7], g.A, g.as);
                System.arraycopy(array9, g.A, array9, 0, g.G - g.A);
                System.arraycopy(array, (n + 1 + n10) * g.A, array9, g.G - g.A, g.A);
                ++n7;
            }
        }
        final int n11 = n - 1;
        if (n11 > 0) {
            int g = c.g.A * (this.char.try + 1 - n);
            if (g > c.g.G) {
                g = c.g.G;
            }
            int n12;
            for (n12 = 0; n12 < g; ++n12) {
                array9[c.g.G - 1 - n12] = array[(n - 1) * c.g.A + n12];
            }
            for (int n13 = 0; n13 < c.g.G - n12; ++n13) {
                array9[n13] = 0.0f;
            }
            for (int n14 = 0; n14 < n11; ++n14) {
                c.b.a(array8, n14 * c.g.A, array4, n7 * c.g.as, array5, n7 * c.g.as, array9, c.g.G - c.g.d[n7], c.g.d[n7], c.g.A, c.g.as);
                System.arraycopy(array9, c.g.A, array9, 0, c.g.G - c.g.A);
                System.arraycopy(array8, n14 * c.g.A, array9, c.g.G - c.g.A, c.g.A);
                ++n7;
            }
            for (int n15 = 0; n15 < c.g.A * n11; ++n15) {
                array[c.g.A * n11 - n15 - 1] = array8[n15];
            }
        }
    }
    
    void a(final float[] array, final a a, int n) {
        final float[] array2 = new float[g.aG];
        final float[] array3 = new float[g.i * g.t];
        final float[] array4 = new float[g.aG];
        final float[] array5 = new float[g.i + 1];
        final float[] array6 = new float[g.aG];
        final float[] array7 = new float[g.i + 1];
        final int[] array8 = new int[g.void];
        final int[] array9 = new int[g.aP * g.as];
        final int[] array10 = new int[g.as];
        final int[] array11 = new int[g.as * g.aP];
        final int[] array12 = new int[g.as];
        final int[] array13 = new int[g.ao * g.t];
        final float[] array14 = new float[(g.i + 1) * g.U];
        final float[] array15 = new float[g.U * (g.i + 1)];
        final float[] array16 = new float[g.aG];
        if (n > 0) {
            for (int i = 0; i < g.ao * g.t; ++i) {
                array13[i] = 0;
            }
            int if1 = 0;
            int if2 = 0;
            int if3 = 0;
            for (int j = 0; j < this.char.c; ++j) {
                array8[j] = 0;
            }
            for (int k = 0; k < g.as; ++k) {
                array12[k] = 0;
            }
            for (int l = 0; l < g.as; ++l) {
                array10[l] = 0;
            }
            for (int n2 = 0; n2 < this.char.int; ++n2) {
                for (int n3 = 0; n3 < g.as; ++n3) {
                    array11[n2 * g.as + n3] = 0;
                }
            }
            for (int n4 = 0; n4 < this.char.int; ++n4) {
                for (int n5 = 0; n5 < g.as; ++n5) {
                    array9[n4 * g.as + n5] = 0;
                }
            }
            for (int n6 = 0; n6 < 3; ++n6) {
                for (int n7 = 0; n7 < g.ao * this.char.a; ++n7) {
                    array13[n7] = a.if(array13[n7], a.a(this.char.d[n7][n6]), this.char.d[n7][n6]);
                }
                if1 = a.if(if1, a.a(this.char.do[n6]), this.char.do[n6]);
                if2 = a.if(if2, a.a(this.char.goto[n6]), this.char.goto[n6]);
                if3 = a.if(if3, a.a(this.char.b[n6]), this.char.b[n6]);
                for (int n8 = 0; n8 < this.char.c; ++n8) {
                    array8[n8] = a.if(array8[n8], a.a(this.char.case[n6]), this.char.case[n6]);
                }
                for (int n9 = 0; n9 < g.as; ++n9) {
                    array12[n9] = a.if(array12[n9], a.a(this.char.void[n9][n6]), this.char.void[n9][n6]);
                }
                for (int n10 = 0; n10 < g.as; ++n10) {
                    array10[n10] = a.if(array10[n10], a.a(this.char.long[n10][n6]), this.char.long[n10][n6]);
                }
                for (int n11 = 0; n11 < this.char.int; ++n11) {
                    for (int n12 = 0; n12 < g.as; ++n12) {
                        array11[n11 * g.as + n12] = a.if(array11[n11 * g.as + n12], a.a(this.char.if[n11][n12][n6]), this.char.if[n11][n12][n6]);
                    }
                }
                for (int n13 = 0; n13 < this.char.int; ++n13) {
                    for (int n14 = 0; n14 < g.as; ++n14) {
                        array9[n13 * g.as + n14] = a.if(array9[n13 * g.as + n14], a.a(this.char.byte[n13][n14][n6]), this.char.byte[n13][n14][n6]);
                    }
                }
            }
            final int a2 = a.a(1);
            if (if1 < 1) {
                n = 0;
            }
            if (this.char.else == 20 && if1 > 3) {
                n = 0;
            }
            if (this.char.else == 30 && if1 > 5) {
                n = 0;
            }
            if (a2 == 1) {
                n = 0;
            }
            if (n == 1) {
                this.a(array11);
                this.a(array3, array13, this.char.a);
                c.b.a(array3, g.i, this.char.a);
                this.a(array15, array14, array3, g.i);
                this.a(array16, if1, if3, array8, array15, array11, array9, array12, array10, if2);
                this.a(array4, array5, 0, array16, array15, (g.i + 1) * (this.char.try - 1), this.d);
                System.arraycopy(array4, 0, array16, 0, this.char.new);
            }
        }
        if (n == 0) {
            for (int n15 = 0; n15 < g.aG; ++n15) {
                array6[n15] = 0.0f;
            }
            array7[0] = 1.0f;
            for (int n16 = 0; n16 < g.i; ++n16) {
                array7[n16 + 1] = 0.0f;
            }
            this.a(array4, array5, 1, array6, array7, 0, this.d);
            System.arraycopy(array4, 0, array16, 0, this.char.new);
            final int n17 = g.i + 1;
            for (int n18 = 0; n18 < this.char.try; ++n18) {
                System.arraycopy(array5, 0, array15, n18 * n17, n17);
            }
        }
        if (this.else == 1) {
            this.d = this.a(array2, array16);
            if (this.char.else == 20) {
                final int n19 = 0;
                this.a(array2, n19 * g.A, this.for, (n19 + this.char.try - 1) * (g.i + 1), g.A, this.byte);
                for (int n20 = 1; n20 < this.char.try; ++n20) {
                    this.a(array2, n20 * g.A, array15, (n20 - 1) * (g.i + 1), g.A, this.byte);
                }
            }
            else if (this.char.else == 30) {
                for (int n21 = 0; n21 < 2; ++n21) {
                    this.a(array2, n21 * g.A, this.for, (n21 + this.char.try - 2) * (g.i + 1), g.A, this.byte);
                }
                for (int n22 = 2; n22 < this.char.try; ++n22) {
                    this.a(array2, n22 * g.A, array15, (n22 - 2) * (g.i + 1), g.A, this.byte);
                }
            }
        }
        else {
            int d = 20;
            float a3 = this.a(array16, g.aG - g.c, array16, g.aG - g.c - d, g.c);
            for (int n23 = 21; n23 < 120; ++n23) {
                final float a4 = this.a(array16, g.aG - g.c, array16, g.aG - g.c - n23, g.c);
                if (a4 > a3) {
                    a3 = a4;
                    d = n23;
                }
            }
            this.d = d;
            System.arraycopy(array16, 0, array2, 0, this.char.new);
            for (int n24 = 0; n24 < this.char.try; ++n24) {
                this.a(array2, n24 * g.A, array15, n24 * (g.i + 1), g.A, this.byte);
            }
        }
        this.a(array2, this.char.new, array, this.a);
        System.arraycopy(array15, 0, this.for, 0, this.char.try * (g.i + 1));
        this.void = 0;
        if (n == 0) {
            this.void = 1;
        }
    }
    
    public h(final int n, final int else1) {
        this.char = null;
        this.char = new d(n);
        this.byte = new float[g.i];
        this.int = new float[g.i + 1];
        this.c = new float[g.U * g.A];
        this.for = new float[(g.i + 1) * g.U];
        this.a = new float[4];
        this.b = new float[g.aA];
        this.case = new float[g.ax];
        this.long = new float[g.i];
        for (int i = 0; i < this.byte.length; ++i) {
            this.byte[i] = 0.0f;
        }
        System.arraycopy(g.F, 0, this.long, 0, g.i);
        for (int j = 0; j < this.for.length; ++j) {
            this.for[j] = 0.0f;
        }
        for (int k = 0; k < g.U; ++k) {
            this.for[k * (g.i + 1)] = 1.0f;
        }
        this.d = 20;
        this.new = 120;
        this.do = 0.0f;
        this.if = 0;
        this.goto = 0;
        this.int[0] = 1.0f;
        for (int l = 1; l < this.int.length; ++l) {
            this.int[l] = 0.0f;
        }
        for (int n2 = 0; n2 < this.c.length; ++n2) {
            this.c[n2] = 0.0f;
        }
        this.try = 777L;
        for (int n3 = 0; n3 < this.a.length; ++n3) {
            this.a[n3] = 0.0f;
        }
        this.else = else1;
        for (int n4 = 0; n4 < this.b.length; ++n4) {
            this.b[n4] = 0.0f;
        }
        for (int n5 = 0; n5 < g.ax; ++n5) {
            this.case[n5] = 40.0f;
        }
        this.void = 0;
    }
}
