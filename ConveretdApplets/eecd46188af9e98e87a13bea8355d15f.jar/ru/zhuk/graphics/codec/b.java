// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

import java.io.IOException;
import java.io.BufferedOutputStream;
import java.util.Vector;

class b
{
    int m;
    int u;
    public int s;
    public int i;
    public int[][] l;
    public int[][] w;
    public int[][] r;
    public int[][] d;
    public Object[] h;
    public Object[] c;
    public int n;
    public int p;
    public int e;
    public int[] q;
    public int[] x;
    public int[] o;
    public int[] f;
    public int[] j;
    public int[] v;
    public int[] k;
    public int[] g;
    public Vector b;
    public Vector a;
    public static int[] t;
    
    public b(final int i, final int s) {
        this.q = new int[] { 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };
        this.x = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        this.o = new int[] { 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
        this.f = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        this.j = new int[] { 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125 };
        this.v = new int[] { 1, 2, 3, 0, 4, 17, 5, 18, 33, 49, 65, 6, 19, 81, 97, 7, 34, 113, 20, 50, 129, 145, 161, 8, 35, 66, 177, 193, 21, 82, 209, 240, 36, 51, 98, 114, 130, 9, 10, 22, 23, 24, 25, 26, 37, 38, 39, 40, 41, 42, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, 131, 132, 133, 134, 135, 136, 137, 138, 146, 147, 148, 149, 150, 151, 152, 153, 154, 162, 163, 164, 165, 166, 167, 168, 169, 170, 178, 179, 180, 181, 182, 183, 184, 185, 186, 194, 195, 196, 197, 198, 199, 200, 201, 202, 210, 211, 212, 213, 214, 215, 216, 217, 218, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250 };
        this.k = new int[] { 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119 };
        this.g = new int[] { 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, 18, 65, 81, 7, 97, 113, 19, 34, 50, 129, 8, 20, 66, 145, 161, 177, 193, 9, 35, 51, 82, 240, 21, 98, 114, 209, 10, 22, 36, 52, 225, 37, 241, 23, 24, 25, 26, 38, 39, 40, 41, 42, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, 130, 131, 132, 133, 134, 135, 136, 137, 138, 146, 147, 148, 149, 150, 151, 152, 153, 154, 162, 163, 164, 165, 166, 167, 168, 169, 170, 178, 179, 180, 181, 182, 183, 184, 185, 186, 194, 195, 196, 197, 198, 199, 200, 201, 202, 210, 211, 212, 213, 214, 215, 216, 217, 218, 226, 227, 228, 229, 230, 231, 232, 233, 234, 242, 243, 244, 245, 246, 247, 248, 249, 250 };
        (this.b = new Vector()).addElement(this.q);
        this.b.addElement(this.j);
        this.b.addElement(this.o);
        this.b.addElement(this.k);
        (this.a = new Vector()).addElement(this.x);
        this.a.addElement(this.v);
        this.a.addElement(this.f);
        this.a.addElement(this.g);
        this.a();
        this.n = this.n;
        this.i = i;
        this.s = s;
    }
    
    public void a(final BufferedOutputStream bufferedOutputStream, final int[] array, final int n, final int n2, final int n3) {
        this.p = 2;
        this.e = 2;
        int i;
        int n4 = i = array[0] - n;
        if (i < 0) {
            i = -i;
            --n4;
        }
        int n5 = 0;
        while (i != 0) {
            ++n5;
            i >>= 1;
        }
        this.a(bufferedOutputStream, ((int[][])this.h[n2])[n5][0], ((int[][])this.h[n2])[n5][1]);
        if (n5 != 0) {
            this.a(bufferedOutputStream, n4, n5);
        }
        int j = 0;
        for (int k = 1; k < 64; ++k) {
            int n6;
            if ((n6 = array[ru.zhuk.graphics.codec.b.t[k]]) == 0) {
                ++j;
            }
            else {
                while (j > 15) {
                    this.a(bufferedOutputStream, ((int[][])this.c[n3])[240][0], ((int[][])this.c[n3])[240][1]);
                    j -= 16;
                }
                int n7;
                if ((n7 = n6) < 0) {
                    n6 = -n6;
                    --n7;
                }
                int n8 = 1;
                while ((n6 >>= 1) != 0) {
                    ++n8;
                }
                final int n9 = (j << 4) + n8;
                this.a(bufferedOutputStream, ((int[][])this.c[n3])[n9][0], ((int[][])this.c[n3])[n9][1]);
                this.a(bufferedOutputStream, n7, n8);
                j = 0;
            }
        }
        if (j > 0) {
            this.a(bufferedOutputStream, ((int[][])this.c[n3])[0][0], ((int[][])this.c[n3])[0][1]);
        }
    }
    
    void a(final BufferedOutputStream bufferedOutputStream, final int n, final int n2) {
        final int m = this.m;
        final int n3 = n & (1 << n2) - 1;
        int i = m + n2;
        int u = n3 << 24 - i | this.u;
        while (i >= 8) {
            final int n4 = u >> 16 & 0xFF;
            try {
                bufferedOutputStream.write(n4);
            }
            catch (IOException ex) {}
            if (n4 == 255) {
                try {
                    bufferedOutputStream.write(0);
                }
                catch (IOException ex2) {}
            }
            u <<= 8;
            i -= 8;
        }
        this.u = u;
        this.m = i;
    }
    
    void a(final BufferedOutputStream bufferedOutputStream) {
        int u = this.u;
        int i;
        for (i = this.m; i >= 8; i -= 8) {
            final int n = u >> 16 & 0xFF;
            try {
                bufferedOutputStream.write(n);
            }
            catch (IOException ex) {}
            if (n == 255) {
                try {
                    bufferedOutputStream.write(0);
                }
                catch (IOException ex2) {}
            }
            u <<= 8;
        }
        if (i > 0) {
            final int n2 = u >> 16 & 0xFF;
            try {
                bufferedOutputStream.write(n2);
            }
            catch (IOException ex3) {}
        }
    }
    
    public void a() {
        this.l = new int[12][2];
        this.r = new int[12][2];
        this.w = new int[255][2];
        this.d = new int[255][2];
        this.h = new Object[2];
        this.c = new Object[2];
        final int[] array = new int[257];
        final int[] array2 = new int[257];
        int n = 0;
        for (int i = 1; i <= 16; ++i) {
            for (int j = 1; j <= this.o[i]; ++j) {
                array[n++] = i;
            }
        }
        array[n] = 0;
        final int n2 = n;
        int n3 = 0;
        int n4 = array[0];
        int n5 = 0;
        while (array[n5] != 0) {
            while (array[n5] == n4) {
                array2[n5++] = n3;
                ++n3;
            }
            n3 <<= 1;
            ++n4;
        }
        for (int k = 0; k < n2; ++k) {
            this.r[this.f[k]][0] = array2[k];
            this.r[this.f[k]][1] = array[k];
        }
        int n6 = 0;
        for (int l = 1; l <= 16; ++l) {
            for (int n7 = 1; n7 <= this.k[l]; ++n7) {
                array[n6++] = l;
            }
        }
        array[n6] = 0;
        final int n8 = n6;
        int n9 = 0;
        int n10 = array[0];
        int n11 = 0;
        while (array[n11] != 0) {
            while (array[n11] == n10) {
                array2[n11++] = n9;
                ++n9;
            }
            n9 <<= 1;
            ++n10;
        }
        for (int n12 = 0; n12 < n8; ++n12) {
            this.d[this.g[n12]][0] = array2[n12];
            this.d[this.g[n12]][1] = array[n12];
        }
        int n13 = 0;
        for (int n14 = 1; n14 <= 16; ++n14) {
            for (int n15 = 1; n15 <= this.q[n14]; ++n15) {
                array[n13++] = n14;
            }
        }
        array[n13] = 0;
        final int n16 = n13;
        int n17 = 0;
        int n18 = array[0];
        int n19 = 0;
        while (array[n19] != 0) {
            while (array[n19] == n18) {
                array2[n19++] = n17;
                ++n17;
            }
            n17 <<= 1;
            ++n18;
        }
        for (int n20 = 0; n20 < n16; ++n20) {
            this.l[this.x[n20]][0] = array2[n20];
            this.l[this.x[n20]][1] = array[n20];
        }
        int n21 = 0;
        for (int n22 = 1; n22 <= 16; ++n22) {
            for (int n23 = 1; n23 <= this.j[n22]; ++n23) {
                array[n21++] = n22;
            }
        }
        array[n21] = 0;
        final int n24 = n21;
        int n25 = 0;
        int n26 = array[0];
        int n27 = 0;
        while (array[n27] != 0) {
            while (array[n27] == n26) {
                array2[n27++] = n25;
                ++n25;
            }
            n25 <<= 1;
            ++n26;
        }
        for (int n28 = 0; n28 < n24; ++n28) {
            this.w[this.v[n28]][0] = array2[n28];
            this.w[this.v[n28]][1] = array[n28];
        }
        this.h[0] = this.l;
        this.h[1] = this.r;
        this.c[0] = this.w;
        this.c[1] = this.d;
    }
    
    static {
        b.t = new int[] { 0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63 };
    }
}
