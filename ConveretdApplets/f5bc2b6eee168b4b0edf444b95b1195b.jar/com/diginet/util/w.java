// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public class w
{
    private static final int[] a;
    private static final int[] b;
    private static final byte[] c;
    private static final byte[] d;
    private static final int[] e;
    private static final int[] f;
    private static final int[] g;
    private static final int[] h;
    private static final int[] i;
    private static final int[] j;
    private static final int[] k;
    private static final int[] l;
    private static final int[] m;
    private static final int[] n;
    private static final int[] o;
    private static final int[] p;
    private static final byte[] q;
    private static final int[][][] r;
    private static final byte[][] s;
    private static final int[][] t;
    private static final char[] u;
    private static final int[] v;
    private static final long[] w;
    private static final char[] x;
    private int y;
    private boolean z;
    private byte[] aa;
    private Object ab;
    private boolean ac;
    private Object ad;
    private boolean ae;
    private int[] af;
    private int ag;
    private int[] ah;
    private final int ai;
    private long aj;
    private long ak;
    private long al;
    
    public final void a(final byte[] aa) {
        if (aa.length != 16 && aa.length != 32) {
            throw new IllegalArgumentException("Key length must be 128-bits (16 bytes) or 256-bits (32 bytes).");
        }
        this.aa = aa;
    }
    
    public final void a(final int y) {
        if (y != 1 && y != 0 && y != 2) {
            throw new IllegalArgumentException("Algorithm not recognized. Algorithm must be SoftaCrypt.AES, SoftaCrypt.TWOFISH or SoftaCrypt.XTEA.");
        }
        this.y = y;
    }
    
    public final boolean a() {
        return this.z;
    }
    
    public final void a(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        if (this.aa == null) {
            throw new IllegalStateException("Encryption key is not specified. Use setKey(byte[]) to specify key.");
        }
        switch (this.y) {
            case 1: {
                if (!this.ac) {
                    this.b();
                    this.ab = this.b(this.aa);
                    this.ac = true;
                }
                this.c(inputStream, outputStream);
                break;
            }
            case 2: {
                if (this.aa.length == 32) {
                    throw new IllegalStateException("Encryption key is too large. Only 128-bit (16 byte) key is supported when using XTEA algorithm.");
                }
                if (this.af == null && this.aa.length == 16) {
                    this.af = new int[4];
                    for (int n = 0, i = 0; i < 16; i += 4, ++n) {
                        this.af[n] = (this.aa[i] << 24 | (this.aa[i + 1] & 0xFF) << 16 | (this.aa[i + 2] & 0xFF) << 8 | (this.aa[i + 3] & 0xFF));
                    }
                }
                this.g(inputStream, outputStream);
                break;
            }
            case 0: {
                if (!this.ae) {
                    this.d();
                    this.ad = this.e(this.aa);
                    this.ae = true;
                }
                this.e(inputStream, outputStream);
                break;
            }
            default: {
                throw new RuntimeException("Unexpected exception. Algorithm not recognized.");
            }
        }
        outputStream.flush();
        if (this.a()) {
            inputStream.close();
            outputStream.close();
        }
    }
    
    public final void b(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        if (this.aa == null) {
            throw new RuntimeException("Decryption key is not specified. Use setKey(byte[]) to specify key.");
        }
        switch (this.y) {
            case 1: {
                if (!this.ac) {
                    this.b();
                    this.ab = this.b(this.aa);
                    this.ac = true;
                }
                this.d(inputStream, outputStream);
                break;
            }
            case 2: {
                if (this.aa.length == 32) {
                    throw new IllegalStateException("Encryption key is too large. Only 128-bit (16 byte) key is supported when using XTEA algorithm.");
                }
                if (this.af == null && this.aa.length == 16) {
                    this.af = new int[4];
                    for (int n = 0, i = 0; i < 16; i += 4, ++n) {
                        this.af[n] = (this.aa[i] << 24 | (this.aa[i + 1] & 0xFF) << 16 | (this.aa[i + 2] & 0xFF) << 8 | (this.aa[i + 3] & 0xFF));
                    }
                }
                this.h(inputStream, outputStream);
                break;
            }
            case 0: {
                if (!this.ae) {
                    this.d();
                    this.ad = this.e(this.aa);
                    this.ae = true;
                }
                this.f(inputStream, outputStream);
                break;
            }
            default: {
                throw new RuntimeException("Unexpected exception. Algorithm not recognized.");
            }
        }
        outputStream.flush();
        if (this.a()) {
            inputStream.close();
            outputStream.close();
        }
    }
    
    private final void c(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.a(3, inputStream, outputStream);
    }
    
    private final void d(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.a(4, inputStream, outputStream);
    }
    
    private final void a(final int n, final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final int n2 = 16;
        byte[] array = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = 0;
        }
        for (int j = inputStream.read(array, 0, n2); j > -1; j = inputStream.read(array, 0, n2)) {
            if (j < n2) {
                for (int k = j; k < n2; ++k) {
                    array[k] = 0;
                }
            }
            switch (n) {
                case 3: {
                    array = this.c(array);
                    break;
                }
                case 4: {
                    array = this.d(array);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unkown operation. Should be CryptoUtil.ENCRYPT or CryptoUtil.DECRYPT");
                }
            }
            outputStream.write(array);
        }
    }
    
    private final void b() {
        final int n = 283;
        com.diginet.util.w.a[0] = 1;
        for (int i = 1; i < 256; ++i) {
            int n2 = com.diginet.util.w.a[i - 1] << 1 ^ com.diginet.util.w.a[i - 1];
            if ((n2 & 0x100) != 0x0) {
                n2 ^= n;
            }
            com.diginet.util.w.a[i] = n2;
        }
        for (int j = 1; j < 255; ++j) {
            com.diginet.util.w.b[com.diginet.util.w.a[j]] = j;
        }
        final byte[][] array = new byte[8][];
        array[0] = new byte[] { 1, 1, 1, 1, 1, 0, 0, 0 };
        array[1] = new byte[] { 0, 1, 1, 1, 1, 1, 0, 0 };
        array[2] = new byte[] { 0, 0, 1, 1, 1, 1, 1, 0 };
        array[3] = new byte[] { 0, 0, 0, 1, 1, 1, 1, 1 };
        array[4] = new byte[] { 1, 0, 0, 0, 1, 1, 1, 1 };
        array[5] = new byte[] { 1, 1, 0, 0, 0, 1, 1, 1 };
        array[6] = new byte[] { 1, 1, 1, 0, 0, 0, 1, 1 };
        array[7] = new byte[] { 1, 1, 1, 1, 0, 0, 0, 1 };
        final byte[][] array2 = array;
        final byte[] array3 = { 0, 1, 1, 0, 0, 0, 1, 1 };
        final byte[][] array4 = new byte[256][8];
        array4[1][7] = 1;
        for (int k = 2; k < 256; ++k) {
            final int n3 = com.diginet.util.w.a[255 - com.diginet.util.w.b[k]];
            for (int l = 0; l < 8; ++l) {
                array4[k][l] = (byte)(n3 >>> 7 - l & 0x1);
            }
        }
        final byte[][] array5 = new byte[256][8];
        for (int n4 = 0; n4 < 256; ++n4) {
            for (int n5 = 0; n5 < 8; ++n5) {
                array5[n4][n5] = array3[n5];
                for (int n6 = 0; n6 < 8; ++n6) {
                    final byte[] array6 = array5[n4];
                    final int n7 = n5;
                    array6[n7] ^= (byte)(array2[n5][n6] * array4[n4][n6]);
                }
            }
        }
        for (int n8 = 0; n8 < 256; ++n8) {
            com.diginet.util.w.c[n8] = (byte)(array5[n8][0] << 7);
            for (int n9 = 1; n9 < 8; ++n9) {
                final byte[] c = com.diginet.util.w.c;
                final int n10 = n8;
                c[n10] ^= (byte)(array5[n8][n9] << 7 - n9);
            }
            com.diginet.util.w.d[com.diginet.util.w.c[n8] & 0xFF] = (byte)n8;
        }
        final byte[][] array7 = new byte[4][];
        array7[0] = new byte[] { 2, 1, 1, 3 };
        array7[1] = new byte[] { 3, 2, 1, 1 };
        array7[2] = new byte[] { 1, 3, 2, 1 };
        array7[3] = new byte[] { 1, 1, 3, 2 };
        final byte[][] array8 = array7;
        final byte[][] array9 = new byte[4][8];
        for (int n11 = 0; n11 < 4; ++n11) {
            for (int n12 = 0; n12 < 4; ++n12) {
                array9[n11][n12] = array8[n11][n12];
            }
            array9[n11][n11 + 4] = 1;
        }
        final byte[][] array10 = new byte[4][4];
        for (int n13 = 0; n13 < 4; ++n13) {
            byte b = array9[n13][n13];
            if (b == 0) {
                int n14;
                for (n14 = n13 + 1; array9[n14][n13] == 0 && n14 < 4; ++n14) {}
                if (n14 == 4) {
                    throw new RuntimeException("Unexcpected exception");
                }
                for (int n15 = 0; n15 < 8; ++n15) {
                    final byte b2 = array9[n13][n15];
                    array9[n13][n15] = array9[n14][n15];
                    array9[n14][n15] = b2;
                }
                b = array9[n13][n13];
            }
            for (int n16 = 0; n16 < 8; ++n16) {
                if (array9[n13][n16] != 0) {
                    array9[n13][n16] = (byte)com.diginet.util.w.a[(255 + com.diginet.util.w.b[array9[n13][n16] & 0xFF] - com.diginet.util.w.b[b & 0xFF]) % 255];
                }
            }
            for (int n17 = 0; n17 < 4; ++n17) {
                if (n13 != n17) {
                    for (int n18 = n13 + 1; n18 < 8; ++n18) {
                        final byte[] array11 = array9[n17];
                        final int n19 = n18;
                        array11[n19] ^= (byte)this.a(array9[n13][n18], array9[n17][n13]);
                    }
                    array9[n17][n13] = 0;
                }
            }
        }
        for (int n20 = 0; n20 < 4; ++n20) {
            for (int n21 = 0; n21 < 4; ++n21) {
                array10[n20][n21] = array9[n20][n21 + 4];
            }
        }
        for (int n22 = 0; n22 < 256; ++n22) {
            final byte b3 = com.diginet.util.w.c[n22];
            com.diginet.util.w.e[n22] = this.a(b3, array8[0]);
            com.diginet.util.w.f[n22] = this.a(b3, array8[1]);
            com.diginet.util.w.g[n22] = this.a(b3, array8[2]);
            com.diginet.util.w.h[n22] = this.a(b3, array8[3]);
            final byte b4 = com.diginet.util.w.d[n22];
            com.diginet.util.w.i[n22] = this.a(b4, array10[0]);
            com.diginet.util.w.j[n22] = this.a(b4, array10[1]);
            com.diginet.util.w.k[n22] = this.a(b4, array10[2]);
            com.diginet.util.w.l[n22] = this.a(b4, array10[3]);
            com.diginet.util.w.m[n22] = this.a(n22, array10[0]);
            com.diginet.util.w.n[n22] = this.a(n22, array10[1]);
            com.diginet.util.w.o[n22] = this.a(n22, array10[2]);
            com.diginet.util.w.p[n22] = this.a(n22, array10[3]);
        }
        com.diginet.util.w.q[0] = 1;
        for (int a = 1, n23 = 1; n23 < 30; com.diginet.util.w.q[n23++] = (byte)(a = this.a(2, a))) {}
    }
    
    private final int a(final int n, final int n2) {
        return (n == 0 || n2 == 0) ? 0 : com.diginet.util.w.a[(com.diginet.util.w.b[n & 0xFF] + com.diginet.util.w.b[n2 & 0xFF]) % 255];
    }
    
    private final int c() {
        if (this.aa.length == 16) {
            return 10;
        }
        if (this.aa.length == 32) {
            return 14;
        }
        throw new RuntimeException("Invalid key length.");
    }
    
    private final int a(int n, final byte[] array) {
        if (n == 0) {
            return 0;
        }
        n = com.diginet.util.w.b[n & 0xFF];
        return ((array[0] == 0) ? 0 : (com.diginet.util.w.a[(n + com.diginet.util.w.b[array[0] & 0xFF]) % 255] & 0xFF)) << 24 | ((array[1] == 0) ? 0 : (com.diginet.util.w.a[(n + com.diginet.util.w.b[array[1] & 0xFF]) % 255] & 0xFF)) << 16 | ((array[2] == 0) ? 0 : (com.diginet.util.w.a[(n + com.diginet.util.w.b[array[2] & 0xFF]) % 255] & 0xFF)) << 8 | ((array[3] == 0) ? 0 : (com.diginet.util.w.a[(n + com.diginet.util.w.b[array[3] & 0xFF]) % 255] & 0xFF));
    }
    
    private final Object b(final byte[] array) {
        final int n = 16;
        final int c = this.c();
        final int n2 = n / 4;
        final int[][] array2 = new int[c + 1][n2];
        final int[][] array3 = new int[c + 1][n2];
        final int n3 = (c + 1) * n2;
        final int n4 = array.length / 4;
        final int[] array4 = new int[n4];
        for (int i = 0, n5 = 0; i < n4; array4[i++] = ((array[n5++] & 0xFF) << 24 | (array[n5++] & 0xFF) << 16 | (array[n5++] & 0xFF) << 8 | (array[n5++] & 0xFF))) {}
        int j = 0;
        for (int n6 = 0; n6 < n4 && j < n3; ++n6, ++j) {
            array2[j / n2][j % n2] = array4[n6];
            array3[c - j / n2][j % n2] = array4[n6];
        }
        int n7 = 0;
        while (j < n3) {
            final int n8 = array4[n4 - 1];
            final int[] array5 = array4;
            final int n9 = 0;
            array5[n9] ^= ((com.diginet.util.w.c[n8 >>> 16 & 0xFF] & 0xFF) << 24 ^ (com.diginet.util.w.c[n8 >>> 8 & 0xFF] & 0xFF) << 16 ^ (com.diginet.util.w.c[n8 & 0xFF] & 0xFF) << 8 ^ (com.diginet.util.w.c[n8 >>> 24 & 0xFF] & 0xFF) ^ (com.diginet.util.w.q[n7++] & 0xFF) << 24);
            if (n4 != 8) {
                int[] array6;
                int n11;
                for (int k = 1, n10 = 0; k < n4; n11 = k++, array6[n11] ^= array4[n10++]) {
                    array6 = array4;
                }
            }
            else {
                int[] array7;
                int n13;
                for (int l = 1, n12 = 0; l < n4 / 2; n13 = l++, array7[n13] ^= array4[n12++]) {
                    array7 = array4;
                }
                final int n14 = array4[n4 / 2 - 1];
                final int[] array8 = array4;
                final int n15 = n4 / 2;
                array8[n15] ^= ((com.diginet.util.w.c[n14 & 0xFF] & 0xFF) ^ (com.diginet.util.w.c[n14 >>> 8 & 0xFF] & 0xFF) << 8 ^ (com.diginet.util.w.c[n14 >>> 16 & 0xFF] & 0xFF) << 16 ^ (com.diginet.util.w.c[n14 >>> 24 & 0xFF] & 0xFF) << 24);
                int[] array9;
                int n18;
                for (int n16 = n4 / 2, n17 = n16 + 1; n17 < n4; n18 = n17++, array9[n18] ^= array4[n16++]) {
                    array9 = array4;
                }
            }
            for (int n19 = 0; n19 < n4 && j < n3; ++n19, ++j) {
                array2[j / n2][j % n2] = array4[n19];
                array3[c - j / n2][j % n2] = array4[n19];
            }
        }
        for (int n20 = 1; n20 < c; ++n20) {
            for (int n21 = 0; n21 < n2; ++n21) {
                final int n22 = array3[n20][n21];
                array3[n20][n21] = (com.diginet.util.w.m[n22 >>> 24 & 0xFF] ^ com.diginet.util.w.n[n22 >>> 16 & 0xFF] ^ com.diginet.util.w.o[n22 >>> 8 & 0xFF] ^ com.diginet.util.w.p[n22 & 0xFF]);
            }
        }
        return new Object[] { array2, array3 };
    }
    
    private final byte[] c(final byte[] array) {
        int n = 0;
        final int[][] array2 = (int[][])((Object[])this.ab)[0];
        final int n2 = array2.length - 1;
        final int[] array3 = array2[0];
        int n3 = ((array[n++] & 0xFF) << 24 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF)) ^ array3[0];
        int n4 = ((array[n++] & 0xFF) << 24 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF)) ^ array3[1];
        int n5 = ((array[n++] & 0xFF) << 24 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF)) ^ array3[2];
        int n6 = ((array[n++] & 0xFF) << 24 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF)) ^ array3[3];
        for (int i = 1; i < n2; ++i) {
            final int[] array4 = array2[i];
            final int n7 = com.diginet.util.w.e[n3 >>> 24 & 0xFF] ^ com.diginet.util.w.f[n4 >>> 16 & 0xFF] ^ com.diginet.util.w.g[n5 >>> 8 & 0xFF] ^ com.diginet.util.w.h[n6 & 0xFF] ^ array4[0];
            final int n8 = com.diginet.util.w.e[n4 >>> 24 & 0xFF] ^ com.diginet.util.w.f[n5 >>> 16 & 0xFF] ^ com.diginet.util.w.g[n6 >>> 8 & 0xFF] ^ com.diginet.util.w.h[n3 & 0xFF] ^ array4[1];
            final int n9 = com.diginet.util.w.e[n5 >>> 24 & 0xFF] ^ com.diginet.util.w.f[n6 >>> 16 & 0xFF] ^ com.diginet.util.w.g[n3 >>> 8 & 0xFF] ^ com.diginet.util.w.h[n4 & 0xFF] ^ array4[2];
            final int n10 = com.diginet.util.w.e[n6 >>> 24 & 0xFF] ^ com.diginet.util.w.f[n3 >>> 16 & 0xFF] ^ com.diginet.util.w.g[n4 >>> 8 & 0xFF] ^ com.diginet.util.w.h[n5 & 0xFF] ^ array4[3];
            n3 = n7;
            n4 = n8;
            n5 = n9;
            n6 = n10;
        }
        final byte[] array5 = new byte[16];
        final int[] array6 = array2[n2];
        final int n11 = array6[0];
        array5[0] = (byte)(com.diginet.util.w.c[n3 >>> 24 & 0xFF] ^ n11 >>> 24);
        array5[1] = (byte)(com.diginet.util.w.c[n4 >>> 16 & 0xFF] ^ n11 >>> 16);
        array5[2] = (byte)(com.diginet.util.w.c[n5 >>> 8 & 0xFF] ^ n11 >>> 8);
        array5[3] = (byte)(com.diginet.util.w.c[n6 & 0xFF] ^ n11);
        final int n12 = array6[1];
        array5[4] = (byte)(com.diginet.util.w.c[n4 >>> 24 & 0xFF] ^ n12 >>> 24);
        array5[5] = (byte)(com.diginet.util.w.c[n5 >>> 16 & 0xFF] ^ n12 >>> 16);
        array5[6] = (byte)(com.diginet.util.w.c[n6 >>> 8 & 0xFF] ^ n12 >>> 8);
        array5[7] = (byte)(com.diginet.util.w.c[n3 & 0xFF] ^ n12);
        final int n13 = array6[2];
        array5[8] = (byte)(com.diginet.util.w.c[n5 >>> 24 & 0xFF] ^ n13 >>> 24);
        array5[9] = (byte)(com.diginet.util.w.c[n6 >>> 16 & 0xFF] ^ n13 >>> 16);
        array5[10] = (byte)(com.diginet.util.w.c[n3 >>> 8 & 0xFF] ^ n13 >>> 8);
        array5[11] = (byte)(com.diginet.util.w.c[n4 & 0xFF] ^ n13);
        final int n14 = array6[3];
        array5[12] = (byte)(com.diginet.util.w.c[n6 >>> 24 & 0xFF] ^ n14 >>> 24);
        array5[13] = (byte)(com.diginet.util.w.c[n3 >>> 16 & 0xFF] ^ n14 >>> 16);
        array5[14] = (byte)(com.diginet.util.w.c[n4 >>> 8 & 0xFF] ^ n14 >>> 8);
        array5[15] = (byte)(com.diginet.util.w.c[n5 & 0xFF] ^ n14);
        return array5;
    }
    
    private final byte[] d(final byte[] array) {
        int n = 0;
        final int[][] array2 = (int[][])((Object[])this.ab)[1];
        final int n2 = array2.length - 1;
        final int[] array3 = array2[0];
        int n3 = ((array[n++] & 0xFF) << 24 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF)) ^ array3[0];
        int n4 = ((array[n++] & 0xFF) << 24 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF)) ^ array3[1];
        int n5 = ((array[n++] & 0xFF) << 24 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF)) ^ array3[2];
        int n6 = ((array[n++] & 0xFF) << 24 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF)) ^ array3[3];
        for (int i = 1; i < n2; ++i) {
            final int[] array4 = array2[i];
            final int n7 = com.diginet.util.w.i[n3 >>> 24 & 0xFF] ^ com.diginet.util.w.j[n6 >>> 16 & 0xFF] ^ com.diginet.util.w.k[n5 >>> 8 & 0xFF] ^ com.diginet.util.w.l[n4 & 0xFF] ^ array4[0];
            final int n8 = com.diginet.util.w.i[n4 >>> 24 & 0xFF] ^ com.diginet.util.w.j[n3 >>> 16 & 0xFF] ^ com.diginet.util.w.k[n6 >>> 8 & 0xFF] ^ com.diginet.util.w.l[n5 & 0xFF] ^ array4[1];
            final int n9 = com.diginet.util.w.i[n5 >>> 24 & 0xFF] ^ com.diginet.util.w.j[n4 >>> 16 & 0xFF] ^ com.diginet.util.w.k[n3 >>> 8 & 0xFF] ^ com.diginet.util.w.l[n6 & 0xFF] ^ array4[2];
            final int n10 = com.diginet.util.w.i[n6 >>> 24 & 0xFF] ^ com.diginet.util.w.j[n5 >>> 16 & 0xFF] ^ com.diginet.util.w.k[n4 >>> 8 & 0xFF] ^ com.diginet.util.w.l[n3 & 0xFF] ^ array4[3];
            n3 = n7;
            n4 = n8;
            n5 = n9;
            n6 = n10;
        }
        final byte[] array5 = new byte[16];
        final int[] array6 = array2[n2];
        final int n11 = array6[0];
        array5[0] = (byte)(com.diginet.util.w.d[n3 >>> 24 & 0xFF] ^ n11 >>> 24);
        array5[1] = (byte)(com.diginet.util.w.d[n6 >>> 16 & 0xFF] ^ n11 >>> 16);
        array5[2] = (byte)(com.diginet.util.w.d[n5 >>> 8 & 0xFF] ^ n11 >>> 8);
        array5[3] = (byte)(com.diginet.util.w.d[n4 & 0xFF] ^ n11);
        final int n12 = array6[1];
        array5[4] = (byte)(com.diginet.util.w.d[n4 >>> 24 & 0xFF] ^ n12 >>> 24);
        array5[5] = (byte)(com.diginet.util.w.d[n3 >>> 16 & 0xFF] ^ n12 >>> 16);
        array5[6] = (byte)(com.diginet.util.w.d[n6 >>> 8 & 0xFF] ^ n12 >>> 8);
        array5[7] = (byte)(com.diginet.util.w.d[n5 & 0xFF] ^ n12);
        final int n13 = array6[2];
        array5[8] = (byte)(com.diginet.util.w.d[n5 >>> 24 & 0xFF] ^ n13 >>> 24);
        array5[9] = (byte)(com.diginet.util.w.d[n4 >>> 16 & 0xFF] ^ n13 >>> 16);
        array5[10] = (byte)(com.diginet.util.w.d[n3 >>> 8 & 0xFF] ^ n13 >>> 8);
        array5[11] = (byte)(com.diginet.util.w.d[n6 & 0xFF] ^ n13);
        final int n14 = array6[3];
        array5[12] = (byte)(com.diginet.util.w.d[n6 >>> 24 & 0xFF] ^ n14 >>> 24);
        array5[13] = (byte)(com.diginet.util.w.d[n5 >>> 16 & 0xFF] ^ n14 >>> 16);
        array5[14] = (byte)(com.diginet.util.w.d[n4 >>> 8 & 0xFF] ^ n14 >>> 8);
        array5[15] = (byte)(com.diginet.util.w.d[n3 & 0xFF] ^ n14);
        return array5;
    }
    
    private final void e(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.b(3, inputStream, outputStream);
    }
    
    private final void f(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.b(4, inputStream, outputStream);
    }
    
    private final void b(final int n, final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final int n2 = 16;
        byte[] array = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = 0;
        }
        for (int j = inputStream.read(array, 0, n2); j > -1; j = inputStream.read(array, 0, n2)) {
            if (j < n2) {
                for (int k = j; k < n2; ++k) {
                    array[k] = 0;
                }
            }
            switch (n) {
                case 3: {
                    array = this.f(array);
                    break;
                }
                case 4: {
                    array = this.g(array);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unkown operation. Should be CryptoUtil.ENCRYPT or CryptoUtil.DECRYPT");
                }
            }
            outputStream.write(array);
        }
    }
    
    private final void d() {
        final int[] array = new int[2];
        final int[] array2 = new int[2];
        final int[] array3 = new int[2];
        for (int i = 0; i < 256; ++i) {
            final int n = com.diginet.util.w.s[0][i] & 0xFF;
            array[0] = n;
            array2[0] = (d(n) & 0xFF);
            array3[0] = (e(n) & 0xFF);
            final int n2 = com.diginet.util.w.s[1][i] & 0xFF;
            array[1] = n2;
            array2[1] = (d(n2) & 0xFF);
            array3[1] = (e(n2) & 0xFF);
            com.diginet.util.w.t[0][i] = (array[1] | array2[1] << 8 | array3[1] << 16 | array3[1] << 24);
            com.diginet.util.w.t[1][i] = (array3[0] | array3[0] << 8 | array2[0] << 16 | array[0] << 24);
            com.diginet.util.w.t[2][i] = (array2[1] | array3[1] << 8 | array[1] << 16 | array3[1] << 24);
            com.diginet.util.w.t[3][i] = (array2[0] | array[0] << 8 | array3[0] << 16 | array2[0] << 24);
        }
    }
    
    private static final int b(final int n) {
        return n >> 1 ^ (((n & 0x1) == 0x0) ? 0 : 180);
    }
    
    private static final int c(final int n) {
        return n >> 2 ^ (((n & 0x2) == 0x0) ? 0 : 180) ^ (((n & 0x1) == 0x0) ? 0 : 90);
    }
    
    private static final int d(final int n) {
        return n ^ c(n);
    }
    
    private static final int e(final int n) {
        return n ^ b(n) ^ c(n);
    }
    
    private final Object e(final byte[] array) {
        final int length = array.length;
        final int n = length / 8;
        final int n2 = 40;
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        final int[] array4 = new int[4];
        for (int n3 = 0, n4 = 0, n5 = n - 1; n4 < 4 && n3 < length; array2[n4] = ((array[n3++] & 0xFF) | (array[n3++] & 0xFF) << 8 | (array[n3++] & 0xFF) << 16 | (array[n3++] & 0xFF) << 24), array3[n4] = ((array[n3++] & 0xFF) | (array[n3++] & 0xFF) << 8 | (array[n3++] & 0xFF) << 16 | (array[n3++] & 0xFF) << 24), array4[n5] = b(array2[n4], array3[n4]), ++n4, --n5) {}
        final int[] array5 = new int[n2];
        int i;
        for (int n6 = i = 0; i < n2 / 2; ++i, n6 += 33686018) {
            final int a = a(n, n6, array2);
            final int a2 = a(n, n6 + 16843009, array3);
            final int n7 = a2 << 8 | a2 >>> 24;
            final int n8 = a + n7;
            array5[2 * i] = n8;
            final int n9 = n8 + n7;
            array5[2 * i + 1] = (n9 << 9 | n9 >>> 23);
        }
        final int n10 = array4[0];
        final int n11 = array4[1];
        final int n12 = array4[2];
        final int n13 = array4[3];
        final int[] array6 = new int[1024];
        for (int j = 0; j < 256; ++j) {
            int n17;
            int n16;
            int n15;
            int n14 = n15 = (n16 = (n17 = j));
            switch (n & 0x3) {
                case 1: {
                    array6[2 * j] = com.diginet.util.w.t[0][(com.diginet.util.w.s[0][n15] & 0xFF) ^ f(n10)];
                    array6[2 * j + 1] = com.diginet.util.w.t[1][(com.diginet.util.w.s[0][n14] & 0xFF) ^ g(n10)];
                    array6[512 + 2 * j] = com.diginet.util.w.t[2][(com.diginet.util.w.s[1][n16] & 0xFF) ^ h(n10)];
                    array6[512 + 2 * j + 1] = com.diginet.util.w.t[3][(com.diginet.util.w.s[1][n17] & 0xFF) ^ i(n10)];
                    break;
                }
                case 0: {
                    n15 = ((com.diginet.util.w.s[1][n15] & 0xFF) ^ f(n13));
                    n14 = ((com.diginet.util.w.s[0][n14] & 0xFF) ^ g(n13));
                    n16 = ((com.diginet.util.w.s[0][n16] & 0xFF) ^ h(n13));
                    n17 = ((com.diginet.util.w.s[1][n17] & 0xFF) ^ i(n13));
                }
                case 3: {
                    n15 = ((com.diginet.util.w.s[1][n15] & 0xFF) ^ f(n12));
                    n14 = ((com.diginet.util.w.s[1][n14] & 0xFF) ^ g(n12));
                    n16 = ((com.diginet.util.w.s[0][n16] & 0xFF) ^ h(n12));
                    n17 = ((com.diginet.util.w.s[0][n17] & 0xFF) ^ i(n12));
                }
                case 2: {
                    array6[2 * j] = com.diginet.util.w.t[0][(com.diginet.util.w.s[0][(com.diginet.util.w.s[0][n15] & 0xFF) ^ f(n11)] & 0xFF) ^ f(n10)];
                    array6[2 * j + 1] = com.diginet.util.w.t[1][(com.diginet.util.w.s[0][(com.diginet.util.w.s[1][n14] & 0xFF) ^ g(n11)] & 0xFF) ^ g(n10)];
                    array6[512 + 2 * j] = com.diginet.util.w.t[2][(com.diginet.util.w.s[1][(com.diginet.util.w.s[0][n16] & 0xFF) ^ h(n11)] & 0xFF) ^ h(n10)];
                    array6[512 + 2 * j + 1] = com.diginet.util.w.t[3][(com.diginet.util.w.s[1][(com.diginet.util.w.s[1][n17] & 0xFF) ^ i(n11)] & 0xFF) ^ i(n10)];
                    break;
                }
            }
        }
        return new Object[] { array6, array5 };
    }
    
    private final byte[] f(final byte[] array) {
        int n = 0;
        final Object[] array2 = (Object[])this.ad;
        final int[] array3 = (int[])array2[0];
        final int[] array4 = (int[])array2[1];
        final int n2 = (array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24;
        final int n3 = (array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24;
        final int n4 = (array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24;
        final int n5 = (array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24;
        int n6 = n2 ^ array4[0];
        int n7 = n3 ^ array4[1];
        int n8 = n4 ^ array4[2];
        int n9 = n5 ^ array4[3];
        int n10 = 8;
        for (int i = 0; i < 16; i += 2) {
            final int a = a(array3, n6, 0);
            final int a2 = a(array3, n7, 3);
            final int n11 = n8 ^ a + a2 + array4[n10++];
            n8 = (n11 >>> 1 | n11 << 31);
            n9 = ((n9 << 1 | n9 >>> 31) ^ a + 2 * a2 + array4[n10++]);
            final int a3 = a(array3, n8, 0);
            final int a4 = a(array3, n9, 3);
            final int n12 = n6 ^ a3 + a4 + array4[n10++];
            n6 = (n12 >>> 1 | n12 << 31);
            n7 = ((n7 << 1 | n7 >>> 31) ^ a3 + 2 * a4 + array4[n10++]);
        }
        final int n13 = n8 ^ array4[4];
        final int n14 = n9 ^ array4[5];
        final int n15 = n6 ^ array4[6];
        final int n16 = n7 ^ array4[7];
        return new byte[] { (byte)n13, (byte)(n13 >>> 8), (byte)(n13 >>> 16), (byte)(n13 >>> 24), (byte)n14, (byte)(n14 >>> 8), (byte)(n14 >>> 16), (byte)(n14 >>> 24), (byte)n15, (byte)(n15 >>> 8), (byte)(n15 >>> 16), (byte)(n15 >>> 24), (byte)n16, (byte)(n16 >>> 8), (byte)(n16 >>> 16), (byte)(n16 >>> 24) };
    }
    
    private final byte[] g(final byte[] array) {
        int n = 0;
        final Object[] array2 = (Object[])this.ad;
        final int[] array3 = (int[])array2[0];
        final int[] array4 = (int[])array2[1];
        final int n2 = (array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24;
        final int n3 = (array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24;
        final int n4 = (array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24;
        final int n5 = (array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24;
        int n6 = n2 ^ array4[4];
        int n7 = n3 ^ array4[5];
        int n8 = n4 ^ array4[6];
        int n9 = n5 ^ array4[7];
        int n10 = 39;
        for (int i = 0; i < 16; i += 2) {
            final int a = a(array3, n6, 0);
            final int a2 = a(array3, n7, 3);
            final int n11 = n9 ^ a + 2 * a2 + array4[n10--];
            n9 = (n11 >>> 1 | n11 << 31);
            n8 = ((n8 << 1 | n8 >>> 31) ^ a + a2 + array4[n10--]);
            final int a3 = a(array3, n8, 0);
            final int a4 = a(array3, n9, 3);
            final int n12 = n7 ^ a3 + 2 * a4 + array4[n10--];
            n7 = (n12 >>> 1 | n12 << 31);
            n6 = ((n6 << 1 | n6 >>> 31) ^ a3 + a4 + array4[n10--]);
        }
        final int n13 = n8 ^ array4[0];
        final int n14 = n9 ^ array4[1];
        final int n15 = n6 ^ array4[2];
        final int n16 = n7 ^ array4[3];
        return new byte[] { (byte)n13, (byte)(n13 >>> 8), (byte)(n13 >>> 16), (byte)(n13 >>> 24), (byte)n14, (byte)(n14 >>> 8), (byte)(n14 >>> 16), (byte)(n14 >>> 24), (byte)n15, (byte)(n15 >>> 8), (byte)(n15 >>> 16), (byte)(n15 >>> 24), (byte)n16, (byte)(n16 >>> 8), (byte)(n16 >>> 16), (byte)(n16 >>> 24) };
    }
    
    private static final int f(final int n) {
        return n & 0xFF;
    }
    
    private static final int g(final int n) {
        return n >>> 8 & 0xFF;
    }
    
    private static final int h(final int n) {
        return n >>> 16 & 0xFF;
    }
    
    private static final int i(final int n) {
        return n >>> 24 & 0xFF;
    }
    
    private static final int b(final int n, final int n2) {
        int j = n2;
        for (int i = 0; i < 4; ++i) {
            j = j(j);
        }
        int k = j ^ n;
        for (int l = 0; l < 4; ++l) {
            k = j(k);
        }
        return k;
    }
    
    private static final int j(final int n) {
        final int n2 = n >>> 24 & 0xFF;
        final int n3 = (n2 << 1 ^ (((n2 & 0x80) == 0x0) ? 0 : 333)) & 0xFF;
        final int n4 = n2 >>> 1 ^ (((n2 & 0x1) == 0x0) ? 0 : 166) ^ n3;
        return n << 8 ^ n4 << 24 ^ n3 << 16 ^ n4 << 8 ^ n2;
    }
    
    private static final int a(final int n, final int n2, final int[] array) {
        int f = f(n2);
        int g = g(n2);
        int h = h(n2);
        int i = i(n2);
        final int n3 = array[0];
        final int n4 = array[1];
        final int n5 = array[2];
        final int n6 = array[3];
        int n7 = 0;
        switch (n & 0x3) {
            case 1: {
                n7 = (com.diginet.util.w.t[0][(com.diginet.util.w.s[0][f] & 0xFF) ^ f(n3)] ^ com.diginet.util.w.t[1][(com.diginet.util.w.s[0][g] & 0xFF) ^ g(n3)] ^ com.diginet.util.w.t[2][(com.diginet.util.w.s[1][h] & 0xFF) ^ h(n3)] ^ com.diginet.util.w.t[3][(com.diginet.util.w.s[1][i] & 0xFF) ^ i(n3)]);
                break;
            }
            case 0: {
                f = ((com.diginet.util.w.s[1][f] & 0xFF) ^ f(n6));
                g = ((com.diginet.util.w.s[0][g] & 0xFF) ^ g(n6));
                h = ((com.diginet.util.w.s[0][h] & 0xFF) ^ h(n6));
                i = ((com.diginet.util.w.s[1][i] & 0xFF) ^ i(n6));
            }
            case 3: {
                f = ((com.diginet.util.w.s[1][f] & 0xFF) ^ f(n5));
                g = ((com.diginet.util.w.s[1][g] & 0xFF) ^ g(n5));
                h = ((com.diginet.util.w.s[0][h] & 0xFF) ^ h(n5));
                i = ((com.diginet.util.w.s[0][i] & 0xFF) ^ i(n5));
            }
            case 2: {
                n7 = (com.diginet.util.w.t[0][(com.diginet.util.w.s[0][(com.diginet.util.w.s[0][f] & 0xFF) ^ f(n4)] & 0xFF) ^ f(n3)] ^ com.diginet.util.w.t[1][(com.diginet.util.w.s[0][(com.diginet.util.w.s[1][g] & 0xFF) ^ g(n4)] & 0xFF) ^ g(n3)] ^ com.diginet.util.w.t[2][(com.diginet.util.w.s[1][(com.diginet.util.w.s[0][h] & 0xFF) ^ h(n4)] & 0xFF) ^ h(n3)] ^ com.diginet.util.w.t[3][(com.diginet.util.w.s[1][(com.diginet.util.w.s[1][i] & 0xFF) ^ i(n4)] & 0xFF) ^ i(n3)]);
                break;
            }
        }
        return n7;
    }
    
    private static final int a(final int[] array, final int n, final int n2) {
        return array[2 * c(n, n2)] ^ array[2 * c(n, n2 + 1) + 1] ^ array[512 + 2 * c(n, n2 + 2)] ^ array[512 + 2 * c(n, n2 + 3) + 1];
    }
    
    private static final int c(final int n, final int n2) {
        int n3 = 0;
        switch (n2 % 4) {
            case 0: {
                n3 = f(n);
                break;
            }
            case 1: {
                n3 = g(n);
                break;
            }
            case 2: {
                n3 = h(n);
                break;
            }
            case 3: {
                n3 = i(n);
                break;
            }
        }
        return n3;
    }
    
    private final void g(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.c(3, inputStream, outputStream);
    }
    
    private final void h(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.c(4, inputStream, outputStream);
    }
    
    private final void c(final int n, final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final int n2 = 8;
        final byte[] array = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = 0;
        }
        for (int j = inputStream.read(array, 0, n2); j > -1; j = inputStream.read(array, 0, n2)) {
            if (j < n2) {
                for (int k = j; k < n2; ++k) {
                    array[k] = 0;
                }
            }
            final int[] array2 = { array[0] << 24 | (array[1] & 0xFF) << 16 | (array[2] & 0xFF) << 8 | (array[3] & 0xFF), array[4] << 24 | (array[5] & 0xFF) << 16 | (array[6] & 0xFF) << 8 | (array[7] & 0xFF) };
            int[] array3 = null;
            switch (n) {
                case 3: {
                    array3 = this.a(array2);
                    break;
                }
                case 4: {
                    array3 = this.b(array2);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unkown operation. Should be CryptoUtil.ENCRYPT or CryptoUtil.DECRYPT");
                }
            }
            outputStream.write(array3[0] >>> 24);
            outputStream.write(array3[0] >>> 16);
            outputStream.write(array3[0] >>> 8);
            outputStream.write(array3[0]);
            outputStream.write(array3[1] >>> 24);
            outputStream.write(array3[1] >>> 16);
            outputStream.write(array3[1] >>> 8);
            outputStream.write(array3[1]);
        }
    }
    
    private final int[] a(final int[] array) {
        int n = array[0];
        int n2 = array[1];
        int n3 = 0;
        final int n4 = -1640531527;
        int ag = this.ag;
        while (ag-- > 0) {
            n += ((n2 << 4 ^ n2 >>> 5) + n2 ^ n3 + this.af[n3 & 0x3]);
            n3 += n4;
            n2 += ((n << 4 ^ n >>> 5) + n ^ n3 + this.af[n3 >>> 11 & 0x3]);
        }
        return new int[] { n, n2 };
    }
    
    private final int[] b(final int[] array) {
        int n = array[0];
        int n2 = array[1];
        int n3 = -957401312;
        final int n4 = -1640531527;
        int ag = this.ag;
        while (ag-- > 0) {
            n2 -= ((n << 4 ^ n >>> 5) + n ^ n3 + this.af[n3 >>> 11 & 0x3]);
            n3 -= n4;
            n -= ((n2 << 4 ^ n2 >>> 5) + n2 ^ n3 + this.af[n3 & 0x3]);
        }
        return new int[] { n, n2 };
    }
    
    public w() {
        this.ai = -306674912;
        this.y = 1;
        this.z = true;
        this.aa = null;
        this.ab = null;
        this.ac = false;
        this.ad = null;
        this.ae = false;
        this.af = null;
        this.ag = 32;
        this.ah = null;
        this.aj = 0L;
        this.ak = 0L;
        this.al = 0L;
    }
    
    static {
        a = new int[256];
        b = new int[256];
        c = new byte[256];
        d = new byte[256];
        e = new int[256];
        f = new int[256];
        g = new int[256];
        h = new int[256];
        i = new int[256];
        j = new int[256];
        k = new int[256];
        l = new int[256];
        m = new int[256];
        n = new int[256];
        o = new int[256];
        p = new int[256];
        q = new byte[30];
        final int[][][] r2 = new int[3][][];
        final int n2 = 0;
        final int[][] array = new int[4][];
        array[0] = new int[2];
        array[1] = new int[] { 1, 3 };
        array[2] = new int[] { 2, 2 };
        array[3] = new int[] { 3, 1 };
        r2[n2] = array;
        final int n3 = 1;
        final int[][] array2 = new int[4][];
        array2[0] = new int[2];
        array2[1] = new int[] { 1, 5 };
        array2[2] = new int[] { 2, 4 };
        array2[3] = new int[] { 3, 3 };
        r2[n3] = array2;
        final int n4 = 2;
        final int[][] array3 = new int[4][];
        array3[0] = new int[2];
        array3[1] = new int[] { 1, 7 };
        array3[2] = new int[] { 3, 5 };
        array3[3] = new int[] { 4, 4 };
        r2[n4] = array3;
        r = r2;
        final byte[][] s2 = new byte[2][];
        s2[0] = new byte[] { -87, 103, -77, -24, 4, -3, -93, 118, -102, -110, -128, 120, -28, -35, -47, 56, 13, -58, 53, -104, 24, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, 72, -14, -48, -117, 48, -124, 84, -33, 35, 25, 91, 61, 89, -13, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, 22, 12, -29, 97, -64, -116, 58, -11, 115, 44, 37, 11, -69, 78, -119, 107, 83, 106, -76, -15, -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 3, 86, -44, 28, 30, -41, -5, -61, -114, -75, -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, 98, 113, -127, 121, 9, -83, 36, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, 29, -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, 49, -62, 39, -112, 32, -10, 96, -1, -106, 92, -79, -85, -98, -100, 82, 27, 95, -109, 10, -17, -111, -123, 73, -18, 45, 79, -113, 59, 71, -121, 109, 70, -42, 62, 105, 100, 42, -50, -53, 47, -4, -105, 5, 122, -84, 127, -43, 26, 75, 14, -89, 90, 40, 20, 63, 41, -120, 60, 76, 2, -72, -38, -80, 23, 85, 31, -118, 125, 87, -57, -115, 116, -73, -60, -97, 114, 126, 21, 34, 18, 88, 7, -103, 52, 110, 80, -34, 104, 101, -68, -37, -8, -56, -88, 43, 64, -36, -2, 50, -92, -54, 16, 33, -16, -45, 93, 15, 0, 111, -99, 54, 66, 74, 94, -63, -32 };
        s2[1] = new byte[] { 117, -13, -58, -12, -37, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, 75, -42, 50, -40, -3, 55, 113, -15, -31, 48, 15, -8, 27, -121, -6, 6, 63, 94, -70, -82, 91, -118, 0, -68, -99, 109, -63, -79, 14, -128, 93, -46, -43, -96, -124, 7, 20, -75, -112, 44, -93, -78, 115, 76, 84, -110, 116, 54, 81, 56, -80, -67, 90, -4, 96, 98, -106, 108, 66, -9, 16, 124, 40, 39, -116, 19, -107, -100, -57, 36, 70, 59, 112, -54, -29, -123, -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, -65, 64, -25, 43, -30, 121, 12, -86, -126, 65, 58, -22, -71, -28, -102, -92, -105, 126, -38, 122, 23, 102, -108, -95, 29, 61, -16, -34, -77, 11, 114, -89, 28, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 73, -127, -120, -18, 33, -60, 26, -21, -39, -59, 57, -103, -51, -83, 49, -117, 1, 24, 35, -35, 31, 78, 45, -7, 72, 79, -14, 101, -114, 120, 92, 88, 25, -115, -27, -104, 87, 103, 127, 5, 100, -81, 99, -74, -2, -11, -73, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, 21, 89, -88, 10, -98, 110, 71, -33, 52, 53, 106, -49, -36, 34, -55, -64, -101, -119, -44, -19, -85, 18, -94, 13, 82, -69, 2, 47, -87, -41, 97, 30, -76, 80, 4, -10, -62, 22, 37, -122, 86, 85, 9, -66, -111 };
        s = s2;
        t = new int[4][256];
        u = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        v = new int[] { 1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998 };
        w = new long[] { 4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L };
        x = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
}
