// 
// Decompiled by Procyon v0.5.30
// 

class h
{
    private static int[] a;
    private static int[] b;
    private static int[][][] c;
    private static int[][][] d;
    private static int[][][] e;
    private static int[][] f;
    private static int[] g;
    private int[][] h;
    
    h(final long n) {
        this.h = new int[16][2];
        long n2 = b(n);
        int n3 = 0;
        do {
            n2 = a(h.g[n3], n2);
            final long a = a(n2);
            this.h[n3][0] = (int)(a & -1L);
            this.h[n3][1] = (int)(a >>> 32);
        } while (++n3 < 16);
    }
    
    private static long a(final long n) {
        long n2 = 0L;
        int n3 = 0;
        do {
            n2 |= (n >> h.b[n3] & 0x1L) << n3;
        } while (++n3 < 48);
        return n2;
    }
    
    private static long b(final long n) {
        long n2 = 0L;
        int n3 = 0;
        do {
            n2 |= (n >> h.a[n3] & 0x1L) << n3;
        } while (++n3 < 56);
        return n2;
    }
    
    static {
        h.a = new int[56];
        h.b = new int[48];
        h.c = new int[8][256][2];
        h.d = new int[8][256][2];
        h.e = new int[4][256][2];
        h.f = new int[8][64];
        h.g = new int[16];
        final int[] array = new int[64];
        final int[] array2 = { 959523105, 420546817, 993209123, 454232835, 1026895141, 487918853, 1060581159, 521604871, 942680096, 403703808, 976366114, 437389826, 1010052132, 471075844, 1043738150, 504761862 };
        for (int i = 0; i < array.length; ++i) {
            array[i] = (array2[i / 4] >>> 8 * (3 - i % 4) & 0xFF);
        }
        final int[] array3 = new int[48];
        final int[] array4 = { 1040222308, 105027816, 243575148, 382122480, 520669812, 659217144, 797764476, 936311776 };
        for (int j = 0; j < array3.length; ++j) {
            array3[j] = (array4[j / 6] >>> 5 * (5 - j % 6) & 0x1F);
        }
        final int[] array5 = { 942680096, 403703808, 959523105, 420546817, 976366114, 437389826, 993209123, 1043738150, 504761862, 1026895141, 487918853, 1010052132, 471075844, 454232835 };
        for (int k = 0; k < h.a.length; ++k) {
            h.a[k] = (array5[k / 4] >>> 8 * (3 - k % 4) & 0xFF);
        }
        final int[] array6 = { 219154967, 262683, 235213833, 370281219, 419892998, 437455873, 674438692, 775298343, 841752623, 724575799, 557067561, 824384543 };
        for (int l = 0; l < h.b.length; ++l) {
            h.b[l] = (array6[l / 4] >>> 8 * (3 - l % 4) & 0xFF);
        }
        final int[] array7 = new int[32];
        final int[] array8 = { 252056340, 470489872, 923161, 68230665, 17241869, 521798152, 302783749, 352977688 };
        for (int n = 0; n < array7.length; ++n) {
            array7[n] = (array8[n / 4] >>> 8 * (3 - n % 4) & 0xFF);
        }
        final int[] array9 = new int[64];
        final int[] array10 = { 654782223, 924270367, 637939214, 907427358, 621096205, 890584349, 604253196, 873741340, 587410187, 856898331, 570567178, 840055322, 553724169, 823212313, 536881160, 806369304 };
        for (int n2 = 0; n2 < array9.length; ++n2) {
            array9[n2] = (array10[n2 / 4] >>> 8 * (3 - n2 % 4) & 0xFF);
        }
        final int[][][] array11 = new int[8][16][4];
        final int[] array12 = { -274256819, 64875217, 1088047383, -49040286, 1091420594, -42418417, 510013003, -928021583, -660714968, 1946887357, -414638156, -1966030114, 518201316, 1200996984, -1953450543, 554073223, 643856886, -281655478, -628317495, 1131026708, -80305649, 575634579, 1694226492, -1617828534, -1275480437, -664741609, 758611070, 344797096, -2074449711, 514004388, -1324514334, 1920522877, 957448250, -1406904596, -171968608, 1531538335, -1479385655, 1619460405, -938372874, -1225458084, 1657287571, -1051525802, -1675377558, 937754889, -847907714, -1158893877, 1993235085, -331638800, 1555816021, -1765724128, 961896719, -1598249245, -1874065504, 1505401854, -1549648557, 89604133, 86304108, 1006535561, 1390939029, 1847744566, 2056231703, -2061932446, 259319336, -640795189 };
        int n3 = 0;
        for (int n4 = 0; n4 < 16; ++n4) {
            int n5 = 0;
            do {
                for (int n6 = array12[n3], n7 = 7; n7 >= 0; --n7, n6 >>>= 4) {
                    array11[n7][n4][n5] = (n6 & 0xF);
                }
                ++n5;
                ++n3;
            } while (n5 < 4);
        }
        for (int n8 = 1521117865, n9 = h.g.length - 1; n9 >= 0; --n9, n8 >>>= 2) {
            h.g[n9] = (n8 & 0x3);
        }
        int n10 = 0;
        do {
            int n11 = 0;
            do {
                h.f[n10][n11] = array11[n10][n11 >> 1 & 0xF][(n11 >> 4 & 0x2) | (n11 & 0x1)] << (n10 << 2);
                int n12 = 0;
                int n13 = 0;
                do {
                    n12 |= (h.f[n10][n11] >> array7[n13] & 0x1) << n13;
                } while (++n13 < 32);
                h.f[n10][n11] = n12;
            } while (++n11 < 64);
        } while (++n10 < 8);
        int n14 = 0;
        do {
            int n15 = 0;
            do {
                h.e[n14][n15][0] = 0;
                h.e[n14][n15][1] = 0;
                final int n16 = n15 << (n14 << 3);
                int n17 = 0;
                do {
                    final int n18 = n16 >> array3[n17] & 0x1;
                    final int[] array13 = h.e[n14][n15];
                    final int n19 = n17 >> 5;
                    array13[n19] |= n18 << (n17 & 0x1F);
                } while (++n17 < 48);
            } while (++n15 < 256);
        } while (++n14 < 4);
        int n20 = 0;
        do {
            int n21 = 0;
            do {
                h.d[n20][n21][0] = 0;
                h.d[n20][n21][1] = 0;
                h.c[n20][n21][0] = 0;
                h.c[n20][n21][1] = 0;
                final int[] array14 = new int[2];
                array14[n20 >> 2] = n21 << ((n20 & 0x3) << 3);
                int n22 = 0;
                do {
                    final int n23 = array14[array[n22] >> 5] >> (array[n22] & 0x1F) & 0x1;
                    final int[] array15 = h.d[n20][n21];
                    final int n24 = n22 >> 5;
                    array15[n24] |= n23 << (n22 & 0x1F);
                    final int n25 = array14[array9[n22] >> 5] >> (array9[n22] & 0x1F) & 0x1;
                    final int[] array16 = h.c[n20][n21];
                    final int n26 = n22 >> 5;
                    array16[n26] |= n25 << (n22 & 0x1F);
                } while (++n22 < 64);
            } while (++n21 < 256);
        } while (++n20 < 8);
    }
    
    private static long a(final int n, long n2) {
        for (int i = 0; i < n; ++i) {
            n2 = ((n2 & 0x7FFFFFF7FFFFFFL) << 1 | (n2 & 0x80000008000000L) >> 27);
        }
        return n2;
    }
    
    void a(final byte[] array, final int n, final int n2) {
        for (int i = n; i < n + n2; i += 8) {
            int n3 = h.d[0][array[i] & 0xFF][0] | h.d[1][array[i + 1] & 0xFF][0] | h.d[2][array[i + 2] & 0xFF][0] | h.d[3][array[i + 3] & 0xFF][0] | h.d[4][array[i + 4] & 0xFF][0] | h.d[5][array[i + 5] & 0xFF][0] | h.d[6][array[i + 6] & 0xFF][0] | h.d[7][array[i + 7] & 0xFF][0];
            int n4 = h.d[0][array[i] & 0xFF][1] | h.d[1][array[i + 1] & 0xFF][1] | h.d[2][array[i + 2] & 0xFF][1] | h.d[3][array[i + 3] & 0xFF][1] | h.d[4][array[i + 4] & 0xFF][1] | h.d[5][array[i + 5] & 0xFF][1] | h.d[6][array[i + 6] & 0xFF][1] | h.d[7][array[i + 7] & 0xFF][1];
            int n5 = 15;
            do {
                final int n6 = (h.e[0][n4 & 0xFF][0] | h.e[1][n4 >>> 8 & 0xFF][0] | h.e[2][n4 >>> 16 & 0xFF][0] | h.e[3][n4 >>> 24 & 0xFF][0]) ^ this.h[n5][0];
                final int n7 = (h.e[0][n4 & 0xFF][1] | h.e[1][n4 >>> 8 & 0xFF][1] | h.e[2][n4 >>> 16 & 0xFF][1] | h.e[3][n4 >>> 24 & 0xFF][1]) ^ this.h[n5][1];
                final int n8 = n3 ^ (h.f[7][n7 >>> 10 & 0x3F] | h.f[6][n7 >>> 4 & 0x3F] | h.f[5][(n7 << 2 & 0x3C) | (n6 >>> 30 & 0x3)] | h.f[4][n6 >>> 24 & 0x3F] | h.f[3][n6 >>> 18 & 0x3F] | h.f[2][n6 >>> 12 & 0x3F] | h.f[1][n6 >>> 6 & 0x3F] | h.f[0][n6 & 0x3F]);
                n3 = n4;
                n4 = n8;
            } while (--n5 >= 0);
            final int n9 = h.c[0][n4 & 0xFF][0] | h.c[1][n4 >>> 8 & 0xFF][0] | h.c[2][n4 >>> 16 & 0xFF][0] | h.c[3][n4 >>> 24 & 0xFF][0] | h.c[4][n3 & 0xFF][0] | h.c[5][n3 >>> 8 & 0xFF][0] | h.c[6][n3 >>> 16 & 0xFF][0] | h.c[7][n3 >>> 24 & 0xFF][0];
            final int n10 = h.c[0][n4 & 0xFF][1] | h.c[1][n4 >>> 8 & 0xFF][1] | h.c[2][n4 >>> 16 & 0xFF][1] | h.c[3][n4 >>> 24 & 0xFF][1] | h.c[4][n3 & 0xFF][1] | h.c[5][n3 >>> 8 & 0xFF][1] | h.c[6][n3 >>> 16 & 0xFF][1] | h.c[7][n3 >>> 24 & 0xFF][1];
            array[i] = (byte)(n9 & 0xFF);
            array[i + 1] = (byte)(n9 >>> 8 & 0xFF);
            array[i + 2] = (byte)(n9 >>> 16 & 0xFF);
            array[i + 3] = (byte)(n9 >>> 24 & 0xFF);
            array[i + 4] = (byte)(n10 & 0xFF);
            array[i + 5] = (byte)(n10 >>> 8 & 0xFF);
            array[i + 6] = (byte)(n10 >>> 16 & 0xFF);
            array[i + 7] = (byte)(n10 >>> 24 & 0xFF);
        }
    }
}
