// 
// Decompiled by Procyon v0.5.30
// 

class h
{
    private int[] a;
    private int[] b;
    private long[] c;
    private long[] d;
    private long[] e;
    private int[] f;
    private int[] g;
    private long[] h;
    
    h(final long n) {
        this.a = new int[56];
        this.b = new int[48];
        this.c = new long[2048];
        this.d = new long[2048];
        this.e = new long[1024];
        this.f = new int[512];
        this.g = new int[16];
        this.h = new long[16];
        this.a();
        long n2 = this.b(n);
        int n3 = 0;
        do {
            n2 = this.a(this.g[n3], n2);
            this.h[n3] = this.a(n2);
        } while (++n3 < 16);
    }
    
    private long a(final long n) {
        long n2 = 0L;
        int n3 = 0;
        do {
            n2 |= (n >> this.b[n3] & 0x1L) << n3;
        } while (++n3 < 48);
        return n2;
    }
    
    private long b(final long n) {
        long n2 = 0L;
        int n3 = 0;
        do {
            n2 |= (n >> this.a[n3] & 0x1L) << n3;
        } while (++n3 < 56);
        return n2;
    }
    
    private long a(final int n, long n2) {
        for (int i = 0; i < n; ++i) {
            n2 = ((n2 & 0x7FFFFFF7FFFFFFL) << 1 | (n2 & 0x80000008000000L) >> 27);
        }
        return n2;
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
            int n5 = 0;
            do {
                for (int n6 = array12[n3], n7 = 7; n7 >= 0; --n7, n6 >>>= 4) {
                    array11[n7][n4][n5] = (n6 & 0xF);
                }
                ++n5;
                ++n3;
            } while (n5 < 4);
        }
        for (int n8 = 1521117865, n9 = this.g.length - 1; n9 >= 0; --n9, n8 >>>= 2) {
            this.g[n9] = (n8 & 0x3);
        }
        int n10 = 0;
        do {
            int n11 = 0;
            do {
                this.f[(n10 << 6) + n11] = array11[n10][n11 >> 1 & 0xF][(n11 >> 4 & 0x2) | (n11 & 0x1)] << (n10 << 2);
                int n12 = 0;
                int n13 = 0;
                do {
                    n12 |= (this.f[(n10 << 6) + n11] >> array7[n13] & 0x1) << n13;
                } while (++n13 < 32);
                this.f[(n10 << 6) + n11] = n12;
            } while (++n11 < 64);
        } while (++n10 < 8);
        int n14 = 0;
        do {
            int n15 = 0;
            do {
                this.e[(n14 << 8) + n15] = 0L;
                final int n16 = n15 << (n14 << 3);
                int n17 = 0;
                do {
                    final long n18 = n16 >> array3[n17] & 0x1;
                    final long[] e = this.e;
                    final int n19 = (n14 << 8) + n15;
                    e[n19] |= n18 << n17;
                } while (++n17 < 48);
            } while (++n15 < 256);
        } while (++n14 < 4);
        int n20 = 0;
        do {
            int n21 = 0;
            do {
                this.d[(n20 << 8) + n21] = 0L;
                this.c[(n20 << 8) + n21] = 0L;
                final long n22 = n21 << (n20 << 3);
                int n23 = 0;
                do {
                    final long n24 = n22 >> array9[n23] & 0x1L;
                    final long[] c = this.c;
                    final int n25 = (n20 << 8) + n21;
                    c[n25] |= n24 << n23;
                    final long n26 = n22 >> array[n23] & 0x1L;
                    final long[] d = this.d;
                    final int n27 = (n20 << 8) + n21;
                    d[n27] |= n26 << n23;
                } while (++n23 < 64);
            } while (++n21 < 256);
        } while (++n20 < 8);
    }
    
    void a(final byte[] array, final int n, final int n2) {
        for (int i = n; i < n + n2; i += 8) {
            long n3 = this.d[array[i] & 0xFF] | this.d[256 + (array[i + 1] & 0xFF)] | this.d[512 + (array[i + 2] & 0xFF)] | this.d[768 + (array[i + 3] & 0xFF)] | this.d[1024 + (array[i + 4] & 0xFF)] | this.d[1280 + (array[i + 5] & 0xFF)] | this.d[1536 + (array[i + 6] & 0xFF)] | this.d[1792 + (array[i + 7] & 0xFF)];
            int n4 = 15;
            do {
                final long n5 = (this.e[(int)(n3 >>> 32 & 0xFFL)] | this.e[256 + (int)(n3 >>> 40 & 0xFFL)] | this.e[512 + (int)(n3 >>> 48 & 0xFFL)] | this.e[768 + (int)(n3 >>> 56 & 0xFFL)]) ^ this.h[n4];
                final long n6 = n3 ^ ((this.f[448 + (int)(n5 >>> 42 & 0x3FL)] | this.f[384 + (int)(n5 >>> 36 & 0x3FL)] | this.f[320 + (int)(n5 >>> 30 & 0x3FL)] | this.f[256 + (int)(n5 >>> 24 & 0x3FL)] | this.f[192 + (int)(n5 >>> 18 & 0x3FL)] | this.f[128 + (int)(n5 >>> 12 & 0x3FL)] | this.f[64 + (int)(n5 >>> 6 & 0x3FL)] | this.f[(int)(n5 & 0x3FL)]) & 0xFFFFFFFFL);
                n3 = (n6 << 32 | n6 >>> 32);
            } while (--n4 >= 0);
            final long n7 = this.c[(int)(n3 >>> 32 & 0xFFL)] | this.c[256 + (int)(n3 >>> 40 & 0xFFL)] | this.c[512 + (int)(n3 >>> 48 & 0xFFL)] | this.c[768 + (int)(n3 >>> 56 & 0xFFL)] | this.c[1024 + (int)(n3 & 0xFFL)] | this.c[1280 + (int)(n3 >>> 8 & 0xFFL)] | this.c[1536 + (int)(n3 >>> 16 & 0xFFL)] | this.c[1792 + (int)(n3 >>> 24 & 0xFFL)];
            array[i] = (byte)(n7 & 0xFFL);
            array[i + 1] = (byte)(n7 >>> 8 & 0xFFL);
            array[i + 2] = (byte)(n7 >>> 16 & 0xFFL);
            array[i + 3] = (byte)(n7 >>> 24 & 0xFFL);
            array[i + 4] = (byte)(n7 >>> 32 & 0xFFL);
            array[i + 5] = (byte)(n7 >>> 40 & 0xFFL);
            array[i + 6] = (byte)(n7 >>> 48 & 0xFFL);
            array[i + 7] = (byte)(n7 >>> 56 & 0xFFL);
        }
    }
}
