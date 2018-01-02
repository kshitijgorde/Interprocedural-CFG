// 
// Decompiled by Procyon v0.5.30
// 

public class ag
{
    public int a;
    public int b;
    public float[] c;
    public int[] d;
    public float e;
    public float[] f;
    public float[] g;
    public float[] h;
    
    public ag() {
        this.f = new float[1024];
        this.g = new float[1024];
        this.h = new float[(this.a > 2048) ? (this.a / 2) : 1024];
    }
    
    public void a(final int a) {
        this.d = new int[a / 4];
        this.c = new float[a + a / 4];
        this.b = (int)Math.rint(Math.log(a) / Math.log(2.0));
        this.a = a;
        final int n = 0;
        final int n2 = 1;
        final int n3 = n + a / 2;
        final int n4 = n3 + 1;
        final int n5 = n3 + a / 2;
        final int n6 = n5 + 1;
        for (int i = 0; i < a / 4; ++i) {
            this.c[n + i * 2] = (float)Math.cos(3.141592653589793 / a * (4 * i));
            this.c[n2 + i * 2] = (float)(-Math.sin(3.141592653589793 / a * (4 * i)));
            this.c[n3 + i * 2] = (float)Math.cos(3.141592653589793 / (2 * a) * (2 * i + 1));
            this.c[n4 + i * 2] = (float)Math.sin(3.141592653589793 / (2 * a) * (2 * i + 1));
        }
        for (int j = 0; j < a / 8; ++j) {
            this.c[n5 + j * 2] = (float)Math.cos(3.141592653589793 / a * (4 * j + 2));
            this.c[n6 + j * 2] = (float)(-Math.sin(3.141592653589793 / a * (4 * j + 2)));
        }
        final int n7 = (1 << this.b - 1) - 1;
        final int n8 = 1 << this.b - 2;
        for (int k = 0; k < a / 8; ++k) {
            int n9 = 0;
            for (int n10 = 0; n8 >>> n10 != 0; ++n10) {
                if ((n8 >>> n10 & k) != 0x0) {
                    n9 |= 1 << n10;
                }
            }
            this.d[k * 2] = (~n9 & n7);
            this.d[k * 2 + 1] = n9;
        }
        this.e = 4.0f / a;
    }
    
    public synchronized void a(final float[] array, final float[] array2) {
        if (this.f.length < this.a / 2) {
            this.f = new float[this.a / 2];
        }
        if (this.g.length < this.a / 2) {
            this.g = new float[this.a / 2];
        }
        final float[] f = this.f;
        final float[] g = this.g;
        final int n = this.a >>> 1;
        final int n2 = this.a >>> 2;
        final int n3 = this.a >>> 3;
        int n4 = 1;
        int n5 = 0;
        int n6 = n;
        for (int i = 0; i < n3; ++i) {
            n6 -= 2;
            f[n5++] = -array[n4 + 2] * this.c[n6 + 1] - array[n4] * this.c[n6];
            f[n5++] = array[n4] * this.c[n6 + 1] - array[n4 + 2] * this.c[n6];
            n4 += 4;
        }
        int n7 = n - 4;
        for (int j = 0; j < n3; ++j) {
            n6 -= 2;
            f[n5++] = array[n7] * this.c[n6 + 1] + array[n7 + 2] * this.c[n6];
            f[n5++] = array[n7] * this.c[n6] - array[n7 + 2] * this.c[n6 + 1];
            n7 -= 4;
        }
        final float[] a = this.a(this.a(f, g, n, n2), this.a, n, n3);
        int n8 = 0;
        int n9 = n;
        int n10 = n2;
        int n11 = n10 - 1;
        int n12 = n2 + n;
        int n13 = n12 - 1;
        for (int k = 0; k < n2; ++k) {
            final float n14 = a[n8] * this.c[n9 + 1] - a[n8 + 1] * this.c[n9];
            final float n15 = -(a[n8] * this.c[n9] + a[n8 + 1] * this.c[n9 + 1]);
            array2[n10] = -n14;
            array2[n11] = n14;
            array2[n13] = (array2[n12] = n15);
            ++n10;
            --n11;
            ++n12;
            --n13;
            n8 += 2;
            n9 += 2;
        }
    }
    
    private float[] a(float[] array, float[] h, final int n, final int n2) {
        float n6;
        float n7;
        for (int n3 = n2, n4 = 0, n5 = n, i = 0; i < n2; h[i++] = n6 * this.c[n5] + n7 * this.c[n5 + 1], h[i] = n7 * this.c[n5] - n6 * this.c[n5 + 1], h[n2 + i] = array[n3++] + array[n4++], ++i) {
            n6 = array[n3] - array[n4];
            h[n2 + i] = array[n3++] + array[n4++];
            n7 = array[n3] - array[n4];
            n5 -= 4;
        }
        for (int j = 0; j < this.b - 3; ++j) {
            int n8 = this.a >>> j + 2;
            final int n9 = 1 << j + 3;
            int n10 = n - 2;
            for (int n11 = 0, k = 0; k < n8 >>> 2; --n8, n11 += n9, ++k) {
                int n12 = n10;
                int n13 = n12 - (n8 >> 1);
                final float n14 = this.c[n11];
                final float n15 = this.c[n11 + 1];
                n10 -= 2;
                ++n8;
                for (int l = 0; l < 2 << j; ++l) {
                    final float n16 = h[n12] - h[n13];
                    array[n12] = h[n12] + h[n13];
                    final float n17 = h[++n12] - h[++n13];
                    array[n12] = h[n12] + h[n13];
                    array[n13] = n17 * n14 - n16 * n15;
                    array[n13 - 1] = n16 * n14 + n17 * n15;
                    n12 -= n8;
                    n13 -= n8;
                }
            }
            final float[] array2 = h;
            h = array;
            array = array2;
        }
        this.h = h;
        return array;
    }
    
    private float[] a(final float[] array, final int n, final int n2, final int n3) {
        int n4 = n;
        int n5 = 0;
        int n6 = 0;
        int n7 = n2 - 1;
        final float[] h = this.h;
        for (int i = 0; i < n3; ++i) {
            final int n8 = this.d[n5++];
            final int n9 = this.d[n5++];
            final float n10 = h[n8] - h[n9 + 1];
            final float n11 = h[n8 - 1] + h[n9];
            final float n12 = h[n8] + h[n9 + 1];
            final float n13 = h[n8 - 1] - h[n9];
            final float n14 = n10 * this.c[n4];
            final float n15 = n11 * this.c[n4++];
            final float n16 = n10 * this.c[n4];
            final float n17 = n11 * this.c[n4++];
            array[n6++] = (n12 + n16 + n15) * 0.5f;
            final int n18 = n7;
            final int n19 = n18 - 1;
            array[n18] = (-n13 + n17 - n14) * 0.5f;
            array[n6++] = (n13 + n17 - n14) * 0.5f;
            final int n20 = n19;
            n7 = n20 - 1;
            array[n20] = (n12 - n16 - n15) * 0.5f;
        }
        return array;
    }
}
