// 
// Decompiled by Procyon v0.5.30
// 

public class ah
{
    public int a;
    public int b;
    public ac c;
    public float[] d;
    public ai e;
    public int[] f;
    
    public ah() {
        this.c = new ac();
        this.f = new int[15];
    }
    
    public synchronized int a(final float[] array, final int n, final w w, final int n2) {
        final int n3 = n2 / this.a;
        if (this.f.length < n3) {
            this.f = new int[n3];
        }
        for (int i = 0; i < n3; ++i) {
            final int a = this.a(w);
            if (a == -1) {
                return -1;
            }
            this.f[i] = a * this.a;
        }
        for (int j = 0, n4 = 0; j < this.a; ++j, n4 += n3) {
            for (int k = 0; k < n3; ++k) {
                final int n5 = n + n4 + k;
                array[n5] += this.d[this.f[k] + j];
            }
        }
        return 0;
    }
    
    public int b(final float[] array, final int n, final w w, final int n2) {
        if (this.a > 8) {
            int i = 0;
            while (i < n2) {
                final int a = this.a(w);
                if (a == -1) {
                    return -1;
                }
                int n4;
                for (int n3 = a * this.a, j = 0; j < this.a; array[n4] += this.d[n3 + j++]) {
                    n4 = n + i++;
                }
            }
        }
        else {
            int k = 0;
            while (k < n2) {
                final int a2 = this.a(w);
                if (a2 == -1) {
                    return -1;
                }
                final int n5 = a2 * this.a;
                int n6 = 0;
                switch (this.a) {
                    case 8: {
                        final int n7 = n + k++;
                        array[n7] += this.d[n5 + n6++];
                    }
                    case 7: {
                        final int n8 = n + k++;
                        array[n8] += this.d[n5 + n6++];
                    }
                    case 6: {
                        final int n9 = n + k++;
                        array[n9] += this.d[n5 + n6++];
                    }
                    case 5: {
                        final int n10 = n + k++;
                        array[n10] += this.d[n5 + n6++];
                    }
                    case 4: {
                        final int n11 = n + k++;
                        array[n11] += this.d[n5 + n6++];
                    }
                    case 3: {
                        final int n12 = n + k++;
                        array[n12] += this.d[n5 + n6++];
                    }
                    case 2: {
                        final int n13 = n + k++;
                        array[n13] += this.d[n5 + n6++];
                    }
                    case 1: {
                        final int n14 = n + k++;
                        array[n14] += this.d[n5 + n6++];
                        continue;
                    }
                }
            }
        }
        return 0;
    }
    
    public int c(final float[] array, final int n, final w w, final int n2) {
        int i = 0;
        while (i < n2) {
            final int a = this.a(w);
            if (a == -1) {
                return -1;
            }
            for (int n3 = a * this.a, j = 0; j < this.a; array[n + i++] = this.d[n3 + j++]) {}
        }
        return 0;
    }
    
    public int a(final float[][] array, final int n, final int n2, final w w, final int n3) {
        int n4 = 0;
        int i = n / n2;
        while (i < (n + n3) / n2) {
            final int a = this.a(w);
            if (a == -1) {
                return -1;
            }
            final int n5 = a * this.a;
            for (int j = 0; j < this.a; ++j) {
                final float[] array2 = array[n4++];
                final int n6 = i;
                array2[n6] += this.d[n5 + j];
                if (n4 == n2) {
                    n4 = 0;
                    ++i;
                }
            }
        }
        return 0;
    }
    
    public int a(final w w) {
        int i = 0;
        final ai e = this.e;
        final int a = w.a(e.c);
        if (a >= 0) {
            i = e.a[a];
            w.b(e.b[a]);
            if (i <= 0) {
                return -i;
            }
        }
        do {
            switch (w.c()) {
                case 0: {
                    i = e.d[i];
                    continue;
                }
                case 1: {
                    i = e.e[i];
                    continue;
                }
                default: {
                    return -1;
                }
            }
        } while (i > 0);
        return -i;
    }
    
    public void a() {
    }
    
    public int a(final ac c) {
        this.c = c;
        this.b = c.b;
        this.a = c.a;
        this.d = c.b();
        this.e = this.b();
        if (this.e == null) {
            this.a();
            return -1;
        }
        return 0;
    }
    
    public static int[] a(final int[] array, final int n) {
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
    
    public ai b() {
        int n = 0;
        final ai ai2;
        final ai ai = ai2 = new ai();
        final int[] d = new int[this.b * 2];
        ai2.d = d;
        final int[] array = d;
        final ai ai3 = ai;
        final int[] e = new int[this.b * 2];
        ai3.e = e;
        final int[] array2 = e;
        final int[] a = a(this.c.c, this.c.b);
        if (a == null) {
            return null;
        }
        for (int i = 0; i < this.b; ++i) {
            if (this.c.c[i] > 0) {
                int n2 = 0;
                int j;
                for (j = 0; j < this.c.c[i] - 1; ++j) {
                    if ((a[i] >>> j & 0x1) == 0x0) {
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
                if ((a[i] >>> j & 0x1) == 0x0) {
                    array[n2] = -i;
                }
                else {
                    array2[n2] = -i;
                }
            }
        }
        ai.c = ae.a(this.b, 0) - 4;
        if (ai.c < 5) {
            ai.c = 5;
        }
        final int n3 = 1 << ai.c;
        ai.a = new int[n3];
        ai.b = new int[n3];
        for (int k = 0; k < n3; ++k) {
            int n4;
            int n5;
            for (n4 = 0, n5 = 0; n5 < ai.c && (n4 > 0 || n5 == 0); ++n5) {
                if ((k & 1 << n5) != 0x0) {
                    n4 = array2[n4];
                }
                else {
                    n4 = array[n4];
                }
            }
            ai.a[k] = n4;
            ai.b[k] = n5;
        }
        return ai;
    }
}
