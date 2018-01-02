// 
// Decompiled by Procyon v0.5.30
// 

public class q
{
    private int[] a;
    private long b;
    private byte[] c;
    private int[] d;
    private byte[] e;
    
    private int a(final int n, final int n2, final int n3) {
        return n2 ^ (n | ~n3);
    }
    
    private int b(final int n, final int n2, final int n3) {
        return (n & n3) | (n2 & ~n3);
    }
    
    public q(final String s) {
        this.b();
        this.a(s);
        this.c();
    }
    
    private int a(int a, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        a += this.c(n, n2, n3) + n4 + n6;
        a = this.a(a, n5);
        a += n;
        return a;
    }
    
    private int c(final int n, final int n2, final int n3) {
        return (n & n2) | (~n & n3);
    }
    
    private void a(final byte b) {
        final int n = (int)(this.b >>> 3 & 0x3FL);
        this.b += 8L;
        this.c[n] = b;
        if (n >= 63) {
            this.a(this.c, 0);
        }
    }
    
    private void a(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.a(array[i]);
        }
    }
    
    private void a(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            this.a((byte)s.charAt(i));
        }
    }
    
    public String a() {
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        String string = "";
        for (int i = 0; i < this.e.length; ++i) {
            string = string + array[this.e[i] >> 4 & 0xF] + array[this.e[i] & 0xF];
        }
        return string;
    }
    
    private int b(int a, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        a += this.a(n, n2, n3) + n4 + n6;
        a = this.a(a, n5);
        a += n;
        return a;
    }
    
    private int c(int a, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        a += this.d(n, n2, n3) + n4 + n6;
        a = this.a(a, n5);
        a += n;
        return a;
    }
    
    public void a(final byte[] array, final int n) {
        final int[] d = this.d;
        final int n2 = this.a[0];
        final int n3 = this.a[1];
        final int n4 = this.a[2];
        final int n5 = this.a[3];
        int n6 = 0;
        do {
            d[n6] = (array[n6 * 4 + n] & 0xFF);
            int n7 = 1;
            do {
                final int[] array2 = d;
                final int n8 = n6;
                array2[n8] += (array[n6 * 4 + n7 + n] & 0xFF) << n7 * 8;
            } while (++n7 < 4);
        } while (++n6 < 16);
        final int a = this.a(n2, n3, n4, n5, d[0], 7, -680876936);
        final int a2 = this.a(n5, a, n3, n4, d[1], 12, -389564586);
        final int a3 = this.a(n4, a2, a, n3, d[2], 17, 606105819);
        final int a4 = this.a(n3, a3, a2, a, d[3], 22, -1044525330);
        final int a5 = this.a(a, a4, a3, a2, d[4], 7, -176418897);
        final int a6 = this.a(a2, a5, a4, a3, d[5], 12, 1200080426);
        final int a7 = this.a(a3, a6, a5, a4, d[6], 17, -1473231341);
        final int a8 = this.a(a4, a7, a6, a5, d[7], 22, -45705983);
        final int a9 = this.a(a5, a8, a7, a6, d[8], 7, 1770035416);
        final int a10 = this.a(a6, a9, a8, a7, d[9], 12, -1958414417);
        final int a11 = this.a(a7, a10, a9, a8, d[10], 17, -42063);
        final int a12 = this.a(a8, a11, a10, a9, d[11], 22, -1990404162);
        final int a13 = this.a(a9, a12, a11, a10, d[12], 7, 1804603682);
        final int a14 = this.a(a10, a13, a12, a11, d[13], 12, -40341101);
        final int a15 = this.a(a11, a14, a13, a12, d[14], 17, -1502002290);
        final int a16 = this.a(a12, a15, a14, a13, d[15], 22, 1236535329);
        final int d2 = this.d(a13, a16, a15, a14, d[1], 5, -165796510);
        final int d3 = this.d(a14, d2, a16, a15, d[6], 9, -1069501632);
        final int d4 = this.d(a15, d3, d2, a16, d[11], 14, 643717713);
        final int d5 = this.d(a16, d4, d3, d2, d[0], 20, -373897302);
        final int d6 = this.d(d2, d5, d4, d3, d[5], 5, -701558691);
        final int d7 = this.d(d3, d6, d5, d4, d[10], 9, 38016083);
        final int d8 = this.d(d4, d7, d6, d5, d[15], 14, -660478335);
        final int d9 = this.d(d5, d8, d7, d6, d[4], 20, -405537848);
        final int d10 = this.d(d6, d9, d8, d7, d[9], 5, 568446438);
        final int d11 = this.d(d7, d10, d9, d8, d[14], 9, -1019803690);
        final int d12 = this.d(d8, d11, d10, d9, d[3], 14, -187363961);
        final int d13 = this.d(d9, d12, d11, d10, d[8], 20, 1163531501);
        final int d14 = this.d(d10, d13, d12, d11, d[13], 5, -1444681467);
        final int d15 = this.d(d11, d14, d13, d12, d[2], 9, -51403784);
        final int d16 = this.d(d12, d15, d14, d13, d[7], 14, 1735328473);
        final int d17 = this.d(d13, d16, d15, d14, d[12], 20, -1926607734);
        final int c = this.c(d14, d17, d16, d15, d[5], 4, -378558);
        final int c2 = this.c(d15, c, d17, d16, d[8], 11, -2022574463);
        final int c3 = this.c(d16, c2, c, d17, d[11], 16, 1839030562);
        final int c4 = this.c(d17, c3, c2, c, d[14], 23, -35309556);
        final int c5 = this.c(c, c4, c3, c2, d[1], 4, -1530992060);
        final int c6 = this.c(c2, c5, c4, c3, d[4], 11, 1272893353);
        final int c7 = this.c(c3, c6, c5, c4, d[7], 16, -155497632);
        final int c8 = this.c(c4, c7, c6, c5, d[10], 23, -1094730640);
        final int c9 = this.c(c5, c8, c7, c6, d[13], 4, 681279174);
        final int c10 = this.c(c6, c9, c8, c7, d[0], 11, -358537222);
        final int c11 = this.c(c7, c10, c9, c8, d[3], 16, -722521979);
        final int c12 = this.c(c8, c11, c10, c9, d[6], 23, 76029189);
        final int c13 = this.c(c9, c12, c11, c10, d[9], 4, -640364487);
        final int c14 = this.c(c10, c13, c12, c11, d[12], 11, -421815835);
        final int c15 = this.c(c11, c14, c13, c12, d[15], 16, 530742520);
        final int c16 = this.c(c12, c15, c14, c13, d[2], 23, -995338651);
        final int b = this.b(c13, c16, c15, c14, d[0], 6, -198630844);
        final int b2 = this.b(c14, b, c16, c15, d[7], 10, 1126891415);
        final int b3 = this.b(c15, b2, b, c16, d[14], 15, -1416354905);
        final int b4 = this.b(c16, b3, b2, b, d[5], 21, -57434055);
        final int b5 = this.b(b, b4, b3, b2, d[12], 6, 1700485571);
        final int b6 = this.b(b2, b5, b4, b3, d[3], 10, -1894986606);
        final int b7 = this.b(b3, b6, b5, b4, d[10], 15, -1051523);
        final int b8 = this.b(b4, b7, b6, b5, d[1], 21, -2054922799);
        final int b9 = this.b(b5, b8, b7, b6, d[8], 6, 1873313359);
        final int b10 = this.b(b6, b9, b8, b7, d[15], 10, -30611744);
        final int b11 = this.b(b7, b10, b9, b8, d[6], 15, -1560198380);
        final int b12 = this.b(b8, b11, b10, b9, d[13], 21, 1309151649);
        final int b13 = this.b(b9, b12, b11, b10, d[4], 6, -145523070);
        final int b14 = this.b(b10, b13, b12, b11, d[11], 10, -1120210379);
        final int b15 = this.b(b11, b14, b13, b12, d[2], 15, 718787259);
        final int b16 = this.b(b12, b15, b14, b13, d[9], 21, -343485551);
        final int[] a17 = this.a;
        final int n9 = 0;
        a17[n9] += b13;
        final int[] a18 = this.a;
        final int n10 = 1;
        a18[n10] += b16;
        final int[] a19 = this.a;
        final int n11 = 2;
        a19[n11] += b15;
        final int[] a20 = this.a;
        final int n12 = 3;
        a20[n12] += b14;
    }
    
    private int d(final int n, final int n2, final int n3) {
        return n ^ n2 ^ n3;
    }
    
    private int a(final int n, final int n2) {
        return n << n2 | n >>> 32 - n2;
    }
    
    private int d(int a, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        a += this.b(n, n2, n3) + n4 + n6;
        a = this.a(a, n5);
        a += n;
        return a;
    }
    
    private void b() {
        this.a = new int[4];
        this.d = new int[16];
        this.c = new byte[64];
        this.e = new byte[16];
        this.b = 0L;
        this.a[0] = 1732584193;
        this.a[1] = -271733879;
        this.a[2] = -1732584194;
        this.a[3] = 271733878;
        for (int i = 0; i < this.e.length; ++i) {
            this.e[i] = 0;
        }
    }
    
    private void c() {
        final byte[] array = new byte[8];
        int n = 0;
        do {
            array[n] = (byte)(this.b >>> n * 8 & 0xFFL);
        } while (++n < 8);
        final int n2 = (int)(this.b >> 3) & 0x3F;
        final byte[] array2 = new byte[(n2 < 56) ? (56 - n2) : (120 - n2)];
        array2[0] = -128;
        this.a(array2);
        this.a(array);
        int n3 = 0;
        do {
            int n4 = 0;
            do {
                this.e[n3 * 4 + n4] = (byte)(this.a[n3] >>> n4 * 8 & 0xFF);
            } while (++n4 < 4);
        } while (++n3 < 4);
    }
}
