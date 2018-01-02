// 
// Decompiled by Procyon v0.5.30
// 

package Z;

class C
{
    int I;
    private int Z;
    private AI c;
    private float[] C;
    private D B;
    private int[] D;
    
    C() {
        this.c = new AI();
        this.D = new int[15];
    }
    
    final synchronized int I(final float[] array, final int n, final Z z, final int n2) {
        final int n3 = n2 / this.I;
        if (this.D.length < n3) {
            this.D = new int[n3];
        }
        for (int i = 0; i < n3; ++i) {
            final int j = this.I(z);
            if (j == -1) {
                return -1;
            }
            this.D[i] = j * this.I;
        }
        int k;
        for (int n4 = k = 0; k < this.I; ++k, n4 += n3) {
            for (int l = 0; l < n3; ++l) {
                final int n5 = n + n4 + l;
                array[n5] += this.C[this.D[l] + k];
            }
        }
        return 0;
    }
    
    final int Z(final float[] array, final int n, final Z z, final int n2) {
        if (this.I > 8) {
            int i = 0;
            while (i < n2) {
                final int j = this.I(z);
                if (j == -1) {
                    return -1;
                }
                int n4;
                for (int n3 = j * this.I, k = 0; k < this.I; array[n4] += this.C[n3 + k++]) {
                    n4 = n + i++;
                }
            }
        }
        else {
            int l = 0;
            while (l < n2) {
                final int m = this.I(z);
                if (m == -1) {
                    return -1;
                }
                final int n5 = m * this.I;
                int n6 = 0;
                switch (this.I) {
                    case 8: {
                        final int n7 = n + l++;
                        array[n7] += this.C[n5 + n6++];
                    }
                    case 7: {
                        final int n8 = n + l++;
                        array[n8] += this.C[n5 + n6++];
                    }
                    case 6: {
                        final int n9 = n + l++;
                        array[n9] += this.C[n5 + n6++];
                    }
                    case 5: {
                        final int n10 = n + l++;
                        array[n10] += this.C[n5 + n6++];
                    }
                    case 4: {
                        final int n11 = n + l++;
                        array[n11] += this.C[n5 + n6++];
                    }
                    case 3: {
                        final int n12 = n + l++;
                        array[n12] += this.C[n5 + n6++];
                    }
                    case 2: {
                        final int n13 = n + l++;
                        array[n13] += this.C[n5 + n6++];
                    }
                    case 1: {
                        final int n14 = n + l++;
                        array[n14] += this.C[n5 + n6++];
                        continue;
                    }
                }
            }
        }
        return 0;
    }
    
    final int C(final float[] array, final int n, final Z z, final int n2) {
        int i = 0;
        while (i < n2) {
            final int j = this.I(z);
            if (j == -1) {
                return -1;
            }
            for (int n3 = j * this.I, k = 0; k < this.I; array[n + i++] = this.C[n3 + k++]) {}
        }
        return 0;
    }
    
    final int I(final float[][] array, final int n, final int n2, final Z z, final int n3) {
        int n4 = 0;
        int i = n / n2;
        while (i < (n + n3) / n2) {
            final int j = this.I(z);
            if (j == -1) {
                return -1;
            }
            final int n5 = j * this.I;
            for (int k = 0; k < this.I; ++k) {
                final float[] array2 = array[n4++];
                final int n6 = i;
                array2[n6] += this.C[n5 + k];
                if (n4 == n2) {
                    n4 = 0;
                    ++i;
                }
            }
        }
        return 0;
    }
    
    final int I(final Z z) {
        int n = 0;
        final D b = this.B;
        final int i = z.I(b.C);
        if (i >= 0) {
            n = b.I[i];
            z.Z(b.Z[i]);
            if (n <= 0) {
                return -n;
            }
        }
        do {
            switch (z.Z()) {
                case 0: {
                    n = b.B[n];
                    continue;
                }
                case 1: {
                    n = b.D[n];
                    continue;
                }
                default: {
                    return -1;
                }
            }
        } while (0 < n);
        return -n;
    }
    
    private static void I() {
    }
    
    final int I(final AI c) {
        this.c = c;
        this.Z = c.Z;
        this.I = c.I;
        this.C = c.Z();
        this.B = this.Z();
        if (this.B == null) {
            I();
            return -1;
        }
        return 0;
    }
    
    private static int[] I(final int[] array, final int n) {
        final int[] array2 = new int[33];
        final int[] array3 = new int[n];
        for (int i = 0; i < n; ++i) {
            final int n2 = array[i];
            if (n2 > 0) {
                int n3 = array2[n2];
                if (n2 < 32 && n3 >>> n2 != 0) {
                    return null;
                }
                array3[i] = n3;
                int j = n2;
                while (j > 0) {
                    if ((array2[j] & 0x1) != 0x0) {
                        if (j == 1) {
                            final int[] array4 = array2;
                            final int n4 = 1;
                            ++array4[n4];
                            break;
                        }
                        array2[j] = array2[j - 1] << 1;
                        break;
                    }
                    else {
                        final int[] array5 = array2;
                        final int n5 = j;
                        ++array5[n5];
                        --j;
                    }
                }
                for (int n6 = n2 + 1; n6 < 33 && array2[n6] >>> 1 == n3; n3 = array2[n6], array2[n6] = array2[n6 - 1] << 1, ++n6) {}
            }
        }
        for (int k = 0; k < n; ++k) {
            int n7 = 0;
            for (int l = 0; l < array[k]; ++l) {
                n7 = (n7 << 1 | (array3[k] >>> l & 0x1));
            }
            array3[k] = n7;
        }
        return array3;
    }
    
    private D Z() {
        int n = 0;
        final D d2;
        final D d = d2 = new D();
        final int[] b = new int[this.Z * 2];
        d2.B = b;
        final int[] array = b;
        final D d3 = d;
        final int[] d4 = new int[this.Z * 2];
        d3.D = d4;
        final int[] array2 = d4;
        final int[] i = I(this.c.C, this.c.Z);
        if (i == null) {
            return null;
        }
        d.F = this.Z * 2;
        for (int j = 0; j < this.Z; ++j) {
            if (this.c.C[j] > 0) {
                int n2 = 0;
                int k;
                for (k = 0; k < this.c.C[j] - 1; ++k) {
                    if ((i[j] >>> k & 0x1) == 0x0) {
                        if (array[n2] == 0) {
                            array[n2] = ++n;
                        }
                        n2 = array[n2];
                    }
                    else {
                        if (array2[n2] == 0) {
                            array2[n2] = ++n;
                        }
                        n2 = array2[n2];
                    }
                }
                if ((i[j] >>> k & 0x1) == 0x0) {
                    array[n2] = -j;
                }
                else {
                    array2[n2] = -j;
                }
            }
        }
        d.C = I(this.Z) - 4;
        if (d.C < 5) {
            d.C = 5;
        }
        final int n3 = 1 << d.C;
        d.I = new int[n3];
        d.Z = new int[n3];
        for (int l = 0; l < n3; ++l) {
            int n4;
            int n5;
            for (n4 = 0, n5 = 0; n5 < d.C && (n4 > 0 || n5 == 0); ++n5) {
                if ((l & 1 << n5) != 0x0) {
                    n4 = array2[n4];
                }
                else {
                    n4 = array[n4];
                }
            }
            d.I[l] = n4;
            d.Z[l] = n5;
        }
        return d;
    }
    
    private static int I(int i) {
        int n = 0;
        while (i != 0) {
            ++n;
            i >>>= 1;
        }
        return n;
    }
}
