// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cM
{
    private static cM q;
    private static final byte[] q;
    private static final int[] q;
    private static final int[] w;
    private static final int[] e;
    private static final int[] r;
    private static final byte[] w;
    private static final int[] t;
    private static final int[] y;
    private static final int[] u;
    private static final int[] i;
    private static final int[] o;
    private int q;
    private int w;
    private int e;
    private int[] p;
    private int[] a;
    
    public cM() {
        this.q = 0;
        this.w = 0;
        this.e = 0;
        this.p = null;
        this.a = null;
    }
    
    public static byte[] q(byte[] array) {
        byte[] array2;
        int n;
        if ((array2 = array).length > 16) {
            n = 16 - array2.length / 16;
        }
        else {
            n = 16 - array2.length;
        }
        if (n > 0) {
            final byte[] array3 = new byte[array2.length + n];
            System.arraycopy(array2, 0, array3, 0, array2.length);
            for (int i = 0; i < n; ++i) {
                array3[array2.length + i] = -1;
            }
            array2 = array3;
        }
        array = array2;
        final byte[] array4 = new byte[16];
        final byte[] array5 = new byte[16];
        for (int j = 0; j < array.length / 16; ++j) {
            System.arraycopy(array, j * 16, array4, 0, 16);
            final cM q = cM.q;
            final byte[] array6 = array4;
            final byte[] array7 = array5;
            final byte[] array8 = array6;
            final cM cm = q;
            int n2 = 0;
            int n3 = (array8[0] << 24 | (array8[1] & 0xFF) << 16 | (array8[2] & 0xFF) << 8 | (array8[3] & 0xFF)) ^ cm.p[0];
            int n4 = (array8[4] << 24 | (array8[5] & 0xFF) << 16 | (array8[6] & 0xFF) << 8 | (array8[7] & 0xFF)) ^ cm.p[1];
            int n5 = (array8[8] << 24 | (array8[9] & 0xFF) << 16 | (array8[10] & 0xFF) << 8 | (array8[11] & 0xFF)) ^ cm.p[2];
            int n6 = (array8[12] << 24 | (array8[13] & 0xFF) << 16 | (array8[14] & 0xFF) << 8 | (array8[15] & 0xFF)) ^ cm.p[3];
            for (int k = 1; k < cm.q; ++k) {
                n2 += 4;
                final int n7 = cM.q[n3 >>> 24] ^ cM.w[n4 >>> 16 & 0xFF] ^ cM.e[n5 >>> 8 & 0xFF] ^ cM.r[n6 & 0xFF] ^ cm.p[n2];
                final int n8 = cM.q[n4 >>> 24] ^ cM.w[n5 >>> 16 & 0xFF] ^ cM.e[n6 >>> 8 & 0xFF] ^ cM.r[n3 & 0xFF] ^ cm.p[n2 + 1];
                final int n9 = cM.q[n5 >>> 24] ^ cM.w[n6 >>> 16 & 0xFF] ^ cM.e[n3 >>> 8 & 0xFF] ^ cM.r[n4 & 0xFF] ^ cm.p[n2 + 2];
                final int n10 = cM.q[n6 >>> 24] ^ cM.w[n3 >>> 16 & 0xFF] ^ cM.e[n4 >>> 8 & 0xFF] ^ cM.r[n5 & 0xFF] ^ cm.p[n2 + 3];
                n3 = n7;
                n4 = n8;
                n5 = n9;
                n6 = n10;
            }
            n2 += 4;
            final int n11 = cm.p[n2];
            array7[0] = (byte)(cM.q[n3 >>> 24] ^ n11 >>> 24);
            array7[1] = (byte)(cM.q[n4 >>> 16 & 0xFF] ^ n11 >>> 16);
            array7[2] = (byte)(cM.q[n5 >>> 8 & 0xFF] ^ n11 >>> 8);
            array7[3] = (byte)(cM.q[n6 & 0xFF] ^ n11);
            final int n12 = cm.p[n2 + 1];
            array7[4] = (byte)(cM.q[n4 >>> 24] ^ n12 >>> 24);
            array7[5] = (byte)(cM.q[n5 >>> 16 & 0xFF] ^ n12 >>> 16);
            array7[6] = (byte)(cM.q[n6 >>> 8 & 0xFF] ^ n12 >>> 8);
            array7[7] = (byte)(cM.q[n3 & 0xFF] ^ n12);
            final int n13 = cm.p[n2 + 2];
            array7[8] = (byte)(cM.q[n5 >>> 24] ^ n13 >>> 24);
            array7[9] = (byte)(cM.q[n6 >>> 16 & 0xFF] ^ n13 >>> 16);
            array7[10] = (byte)(cM.q[n3 >>> 8 & 0xFF] ^ n13 >>> 8);
            array7[11] = (byte)(cM.q[n4 & 0xFF] ^ n13);
            final int n14 = cm.p[n2 + 3];
            array7[12] = (byte)(cM.q[n6 >>> 24] ^ n14 >>> 24);
            array7[13] = (byte)(cM.q[n3 >>> 16 & 0xFF] ^ n14 >>> 16);
            array7[14] = (byte)(cM.q[n4 >>> 8 & 0xFF] ^ n14 >>> 8);
            array7[15] = (byte)(cM.q[n5 & 0xFF] ^ n14);
            System.arraycopy(array5, 0, array, j * 16, 16);
        }
        return array;
    }
    
    public static byte[] w(final byte[] array) {
        final byte[] array2 = new byte[16];
        final byte[] array3 = new byte[16];
        for (int i = 0; i < array.length / 16; ++i) {
            System.arraycopy(array, i * 16, array2, 0, 16);
            final cM q = cM.q;
            final byte[] array4 = array2;
            final byte[] array5 = array3;
            final byte[] array6 = array4;
            final cM cm = q;
            int n = 0;
            int n2 = (array6[0] << 24 | (array6[1] & 0xFF) << 16 | (array6[2] & 0xFF) << 8 | (array6[3] & 0xFF)) ^ cm.a[0];
            int n3 = (array6[4] << 24 | (array6[5] & 0xFF) << 16 | (array6[6] & 0xFF) << 8 | (array6[7] & 0xFF)) ^ cm.a[1];
            int n4 = (array6[8] << 24 | (array6[9] & 0xFF) << 16 | (array6[10] & 0xFF) << 8 | (array6[11] & 0xFF)) ^ cm.a[2];
            int n5 = (array6[12] << 24 | (array6[13] & 0xFF) << 16 | (array6[14] & 0xFF) << 8 | (array6[15] & 0xFF)) ^ cm.a[3];
            for (int j = 1; j < cm.q; ++j) {
                n += 4;
                final int n6 = cM.t[n2 >>> 24] ^ cM.y[n5 >>> 16 & 0xFF] ^ cM.u[n4 >>> 8 & 0xFF] ^ cM.i[n3 & 0xFF] ^ cm.a[n];
                final int n7 = cM.t[n3 >>> 24] ^ cM.y[n2 >>> 16 & 0xFF] ^ cM.u[n5 >>> 8 & 0xFF] ^ cM.i[n4 & 0xFF] ^ cm.a[n + 1];
                final int n8 = cM.t[n4 >>> 24] ^ cM.y[n3 >>> 16 & 0xFF] ^ cM.u[n2 >>> 8 & 0xFF] ^ cM.i[n5 & 0xFF] ^ cm.a[n + 2];
                final int n9 = cM.t[n5 >>> 24] ^ cM.y[n4 >>> 16 & 0xFF] ^ cM.u[n3 >>> 8 & 0xFF] ^ cM.i[n2 & 0xFF] ^ cm.a[n + 3];
                n2 = n6;
                n3 = n7;
                n4 = n8;
                n5 = n9;
            }
            n += 4;
            final int n10 = cm.a[n];
            array5[0] = (byte)(cM.w[n2 >>> 24] ^ n10 >>> 24);
            array5[1] = (byte)(cM.w[n5 >>> 16 & 0xFF] ^ n10 >>> 16);
            array5[2] = (byte)(cM.w[n4 >>> 8 & 0xFF] ^ n10 >>> 8);
            array5[3] = (byte)(cM.w[n3 & 0xFF] ^ n10);
            final int n11 = cm.a[n + 1];
            array5[4] = (byte)(cM.w[n3 >>> 24] ^ n11 >>> 24);
            array5[5] = (byte)(cM.w[n2 >>> 16 & 0xFF] ^ n11 >>> 16);
            array5[6] = (byte)(cM.w[n5 >>> 8 & 0xFF] ^ n11 >>> 8);
            array5[7] = (byte)(cM.w[n4 & 0xFF] ^ n11);
            final int n12 = cm.a[n + 2];
            array5[8] = (byte)(cM.w[n4 >>> 24] ^ n12 >>> 24);
            array5[9] = (byte)(cM.w[n3 >>> 16 & 0xFF] ^ n12 >>> 16);
            array5[10] = (byte)(cM.w[n2 >>> 8 & 0xFF] ^ n12 >>> 8);
            array5[11] = (byte)(cM.w[n5 & 0xFF] ^ n12);
            final int n13 = cm.a[n + 3];
            array5[12] = (byte)(cM.w[n5 >>> 24] ^ n13 >>> 24);
            array5[13] = (byte)(cM.w[n4 >>> 16 & 0xFF] ^ n13 >>> 16);
            array5[14] = (byte)(cM.w[n3 >>> 8 & 0xFF] ^ n13 >>> 8);
            array5[15] = (byte)(cM.w[n2 & 0xFF] ^ n13);
            System.arraycopy(array3, 0, array, i * 16, 16);
        }
        return array;
    }
    
    private void q(final byte[] array) {
        int n = 0;
        for (int i = 0, n2 = 0; i < this.w; ++i, n2 += 4) {
            this.p[i] = (array[n2] << 24 | (array[n2 + 1] & 0xFF) << 16 | (array[n2 + 2] & 0xFF) << 8 | (array[n2 + 3] & 0xFF));
        }
        for (int j = this.w, w = 0; j < this.e; ++j, --w) {
            int n3 = this.p[j - 1];
            if (w == 0) {
                w = this.w;
                n3 = ((cM.q[n3 >>> 16 & 0xFF] << 24 | (cM.q[n3 >>> 8 & 0xFF] & 0xFF) << 16 | (cM.q[n3 & 0xFF] & 0xFF) << 8 | (cM.q[n3 >>> 24] & 0xFF)) ^ cM.o[n++]);
            }
            else if (this.w == 8 && w == 4) {
                n3 = (cM.q[n3 >>> 24] << 24 | (cM.q[n3 >>> 16 & 0xFF] & 0xFF) << 16 | (cM.q[n3 >>> 8 & 0xFF] & 0xFF) << 8 | (cM.q[n3 & 0xFF] & 0xFF));
            }
            this.p[j] = (this.p[j - this.w] ^ n3);
        }
    }
    
    private void q() {
        int n = 0;
        int n2 = 4 * this.q;
        this.a[0] = this.p[n2];
        this.a[1] = this.p[n2 + 1];
        this.a[2] = this.p[n2 + 2];
        this.a[3] = this.p[n2 + 3];
        n += 4;
        n2 -= 4;
        for (int i = 1; i < this.q; ++i) {
            final int n3 = this.p[n2];
            this.a[n] = (cM.t[cM.q[n3 >>> 24] & 0xFF] ^ cM.y[cM.q[n3 >>> 16 & 0xFF] & 0xFF] ^ cM.u[cM.q[n3 >>> 8 & 0xFF] & 0xFF] ^ cM.i[cM.q[n3 & 0xFF] & 0xFF]);
            final int n4 = this.p[n2 + 1];
            this.a[n + 1] = (cM.t[cM.q[n4 >>> 24] & 0xFF] ^ cM.y[cM.q[n4 >>> 16 & 0xFF] & 0xFF] ^ cM.u[cM.q[n4 >>> 8 & 0xFF] & 0xFF] ^ cM.i[cM.q[n4 & 0xFF] & 0xFF]);
            final int n5 = this.p[n2 + 2];
            this.a[n + 2] = (cM.t[cM.q[n5 >>> 24] & 0xFF] ^ cM.y[cM.q[n5 >>> 16 & 0xFF] & 0xFF] ^ cM.u[cM.q[n5 >>> 8 & 0xFF] & 0xFF] ^ cM.i[cM.q[n5 & 0xFF] & 0xFF]);
            final int n6 = this.p[n2 + 3];
            this.a[n + 3] = (cM.t[cM.q[n6 >>> 24] & 0xFF] ^ cM.y[cM.q[n6 >>> 16 & 0xFF] & 0xFF] ^ cM.u[cM.q[n6 >>> 8 & 0xFF] & 0xFF] ^ cM.i[cM.q[n6 & 0xFF] & 0xFF]);
            n += 4;
            n2 -= 4;
        }
        this.a[n] = this.p[n2];
        this.a[n + 1] = this.p[n2 + 1];
        this.a[n + 2] = this.p[n2 + 2];
        this.a[n + 3] = this.p[n2 + 3];
    }
    
    protected final void finalize() {
        if (this.p != null) {
            for (int i = 0; i < this.p.length; ++i) {
                this.p[i] = 0;
            }
            this.p = null;
        }
        if (this.a != null) {
            for (int j = 0; j < this.a.length; ++j) {
                this.a[j] = 0;
            }
            this.a = null;
        }
    }
    
    static {
        q = new byte[256];
        q = new int[256];
        w = new int[256];
        e = new int[256];
        r = new int[256];
        w = new byte[256];
        t = new int[256];
        y = new int[256];
        u = new int[256];
        i = new int[256];
        o = new int[10];
        for (int j = 0; j < 256; ++j) {
            final char char1 = "\u637c\u777b\uf26b\u6fc5\u3001\u672b\ufed7\uab76\uca82\uc97d\ufa59\u47f0\uadd4\ua2af\u9ca4\u72c0\ub7fd\u9326\u363f\uf7cc\u34a5\ue5f1\u71d8\u3115\u04c7\u23c3\u1896\u059a\u0712\u80e2\ueb27\ub275\u0983\u2c1a\u1b6e\u5aa0\u523b\ud6b3\u29e3\u2f84\u53d1\u00ed\u20fc\ub15b\u6acb\ube39\u4a4c\u58cf\ud0ef\uaafb\u434d\u3385\u45f9\u027f\u503c\u9fa8\u51a3\u408f\u929d\u38f5\ubcb6\uda21\u10ff\uf3d2\ucd0c\u13ec\u5f97\u4417\uc4a7\u7e3d\u645d\u1973\u6081\u4fdc\u222a\u9088\u46ee\ub814\ude5e\u0bdb\ue032\u3a0a\u4906\u245c\uc2d3\uac62\u9195\ue479\ue7c8\u376d\u8dd5\u4ea9\u6c56\uf4ea\u657a\uae08\uba78\u252e\u1ca6\ub4c6\ue8dd\u741f\u4bbd\u8b8a\u703e\ub566\u4803\uf60e\u6135\u57b9\u86c1\u1d9e\ue1f8\u9811\u69d9\u8e94\u9b1e\u87e9\uce55\u28df\u8ca1\u890d\ubfe6\u4268\u4199\u2d0f\ub054\ubb16".charAt(j >>> 1);
            final int n2;
            int n;
            if ((n = (n2 = ((byte)(((j & 0x1) == 0x0) ? (char1 >>> 8) : char1) & 0xFF)) << 1) >= 256) {
                n ^= 0x11B;
            }
            final int n3 = n ^ n2;
            int n4;
            if ((n4 = j << 1) >= 256) {
                n4 ^= 0x11B;
            }
            int n5;
            if ((n5 = n4 << 1) >= 256) {
                n5 ^= 0x11B;
            }
            int n6;
            if ((n6 = n5 << 1) >= 256) {
                n6 ^= 0x11B;
            }
            final int n8;
            final int n7 = (n8 = (n6 ^ j)) ^ n4;
            final int n9 = n8 ^ n5;
            final int n10 = n6 ^ n5 ^ n4;
            cM.q[j] = (byte)n2;
            final int n11 = cM.q[j] = (n << 24 | n2 << 16 | n2 << 8 | n3);
            cM.w[j] = (n11 >>> 8 | n11 << 24);
            cM.e[j] = (n11 >>> 16 | n11 << 16);
            cM.r[j] = (n11 >>> 24 | n11 << 8);
            cM.w[n2] = (byte)j;
            final int n12 = cM.t[n2] = (n10 << 24 | n8 << 16 | n9 << 8 | n7);
            cM.y[n2] = (n12 >>> 8 | n12 << 24);
            cM.u[n2] = (n12 >>> 16 | n12 << 16);
            cM.i[n2] = (n12 >>> 24 | n12 << 8);
        }
        int n13 = 1;
        cM.o[0] = 16777216;
        for (int k = 1; k < 10; ++k) {
            if ((n13 <<= 1) >= 256) {
                n13 ^= 0x11B;
            }
            cM.o[k] = n13 << 24;
        }
        cM.q = new cM();
        final byte[] q2 = cN.q();
        final byte[] array = new byte[128];
        for (int l = 0; l < array.length / 16; ++l) {
            for (int n14 = 0; n14 < 16; ++n14) {
                array[(l << 4) + n14] = (byte)(q2[n14 % q2.length] + q2[l % q2.length]);
            }
        }
        final cM q3 = cM.q;
        final byte[] array2 = array;
        final int n15 = 128;
        final byte[] array3 = array2;
        final cM cm = q3;
        if (n15 != 128 && n15 != 192 && n15 != 256) {
            throw new RuntimeException("Invalid AES key size (" + n15 + " bits)");
        }
        cm.w = n15 >>> 5;
        cm.q = cm.w + 6;
        cm.e = 4 * (cm.q + 1);
        cm.p = new int[cm.e];
        cm.a = new int[cm.e];
        cm.q(array3);
        cm.q();
    }
}