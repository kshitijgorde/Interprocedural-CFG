// 
// Decompiled by Procyon v0.5.30
// 

class f
{
    private int[] a;
    private int[] b;
    private long[] c;
    private long[] d;
    private long[] e;
    private int[] f;
    private int[] g;
    private long[] h;
    
    f(final long n) {
        this.a = new int[56];
        this.b = new int[48];
        this.c = new long[2048];
        this.d = new long[2048];
        this.e = new long[1024];
        this.f = new int[512];
        this.g = new int[16];
        this.h = new long[16];
        this.a();
        long n2 = this.a(n);
        for (int i = 0; i < 16; ++i) {
            n2 = this.a(this.g[i], n2);
            this.h[i] = this.b(n2);
        }
    }
    
    private long a(final int n, long n2) {
        for (int i = 0; i < n; ++i) {
            n2 = ((n2 & 0x7FFFFFF7FFFFFFL) << 1 | (n2 & 0x80000008000000L) >> 27);
        }
        return n2;
    }
    
    private long a(final long n) {
        long n2 = 0L;
        for (int i = 0; i < 56; ++i) {
            n2 |= (n >> this.a[i] & 0x1L) << i;
        }
        return n2;
    }
    
    private long b(final long n) {
        long n2 = 0L;
        for (int i = 0; i < 48; ++i) {
            n2 |= (n >> this.b[i] & 0x1L) << i;
        }
        return n2;
    }
    
    void a(final byte[] array, final int n, final int n2) {
        for (int i = n; i < n + n2; i += 8) {
            long n3 = 0L;
            for (int j = 0; j < 8; ++j) {
                n3 |= this.d[(j << 8) + (array[i + j] & 0xFF)];
            }
            for (int k = 15; k >= 0; --k) {
                final long n4 = (this.e[0 + (int)(n3 >>> 32 & 0xFFL)] | this.e[256 + (int)(n3 >>> 40 & 0xFFL)] | this.e[512 + (int)(n3 >>> 48 & 0xFFL)] | this.e[768 + (int)(n3 >>> 56 & 0xFFL)]) ^ this.h[k];
                long n5 = 0L;
                for (int l = 0; l < 8; ++l) {
                    n5 |= this.f[(l << 6) + (int)(n4 >>> 6 * l & 0x3FL)];
                }
                final long n6 = n3 ^ (n5 & 0xFFFFFFFFL);
                n3 = (n6 << 32 | n6 >>> 32);
            }
            long n7 = 0L;
            for (int n8 = 0; n8 < 8; ++n8) {
                n7 |= this.c[(n8 << 8) + (int)(n3 >>> (n8 * 8 + 32 & 0x3F) & 0xFFL)];
            }
            for (int n9 = 0; n9 < 8; ++n9) {
                array[i + n9] = (byte)(n7 >>> 8 * n9 & 0xFFL);
            }
        }
    }
    
    private void a() {
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
        for (int k = 0; k < this.a.length; ++k) {
            this.a[k] = (array5[k / 4] >>> 8 * (3 - k % 4) & 0xFF);
        }
        final int[] array6 = { 219154967, 262683, 235213833, 370281219, 419892998, 437455873, 674438692, 775298343, 841752623, 724575799, 557067561, 824384543 };
        for (int l = 0; l < this.b.length; ++l) {
            this.b[l] = (array6[l / 4] >>> 8 * (3 - l % 4) & 0xFF);
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
            for (int n5 = 0; n5 < 4; ++n5, ++n3) {
                for (int n6 = array12[n3], n7 = 7; n7 >= 0; --n7, n6 >>>= 4) {
                    array11[n7][n4][n5] = (n6 & 0xF);
                }
            }
        }
        for (int n8 = 1521117865, n9 = this.g.length - 1; n9 >= 0; --n9, n8 >>>= 2) {
            this.g[n9] = (n8 & 0x3);
        }
        for (int n10 = 0; n10 < 8; ++n10) {
            for (int n11 = 0; n11 < 64; ++n11) {
                this.f[(n10 << 6) + n11] = array11[n10][n11 >> 1 & 0xF][(n11 >> 4 & 0x2) | (n11 & 0x1)] << (n10 << 2);
                int n12 = 0;
                for (int n13 = 0; n13 < 32; ++n13) {
                    n12 |= (this.f[(n10 << 6) + n11] >> array7[n13] & 0x1) << n13;
                }
                this.f[(n10 << 6) + n11] = n12;
            }
        }
        for (int n14 = 0; n14 < 4; ++n14) {
            for (int n15 = 0; n15 < 256; ++n15) {
                final int n16 = n15 << (n14 << 3);
                for (int n17 = 0; n17 < 48; ++n17) {
                    final long n18 = n16 >> array3[n17] & 0x1;
                    final long[] e = this.e;
                    final int n19 = (n14 << 8) + n15;
                    e[n19] |= n18 << n17;
                }
            }
        }
        for (int n20 = 0; n20 < 8; ++n20) {
            for (int n21 = 0; n21 < 256; ++n21) {
                final long n22 = n21 << (n20 << 3);
                for (int n23 = 0; n23 < 64; ++n23) {
                    final long n24 = n22 >> array9[n23] & 0x1L;
                    final long[] c = this.c;
                    final int n25 = (n20 << 8) + n21;
                    c[n25] |= n24 << n23;
                    final long n26 = n22 >> array[n23] & 0x1L;
                    final long[] d = this.d;
                    final int n27 = (n20 << 8) + n21;
                    d[n27] |= n26 << n23;
                }
            }
        }
    }
}
