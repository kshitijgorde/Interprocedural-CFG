// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

public class NFMd5
{
    private long a;
    private int b;
    private int c;
    private int d;
    private int e;
    byte[] f;
    private static final byte g = 7;
    private static final byte h = 12;
    private static final byte i = 17;
    private static final byte j = 22;
    private static final byte k = 5;
    private static final byte l = 9;
    private static final byte m = 14;
    private static final byte n = 20;
    private static final byte o = 4;
    private static final byte p = 11;
    private static final byte q = 16;
    private static final byte r = 23;
    private static final byte s = 6;
    private static final byte t = 10;
    private static final byte u = 15;
    private static final byte v = 21;
    private static final byte[] w;
    
    public NFMd5() {
        this.a = 0L;
        this.b = 1732584193;
        this.c = -271733879;
        this.d = -1732584194;
        this.e = 271733878;
        this.f = new byte[64];
    }
    
    public final int OutputLen() {
        return 16;
    }
    
    public final void Reset() {
        this.a = 0L;
        this.b = 1732584193;
        this.c = -271733879;
        this.d = -1732584194;
        this.e = 271733878;
        this.f = new byte[64];
    }
    
    public final void Update(final NFBuffer nfBuffer) {
        int n = (int)(this.a & 0x3FL);
        final int length = nfBuffer.length();
        this.a += length;
        final int n2 = 64 - n;
        int n3;
        if (length >= n2) {
            nfBuffer.copyout(new NFBuffer(this.f, n, n2));
            this.a(new NFBuffer(this.f));
            for (n3 = n2; n3 + 63 < length; n3 += 64) {
                this.a(new NFBuffer(nfBuffer, n3, 64));
            }
            n = 0;
        }
        else {
            n3 = 0;
        }
        new NFBuffer(this.f, n).copyin(new NFBuffer(nfBuffer, n3));
    }
    
    public final void Update(final byte b) {
        final int n = (int)(this.a++ & 0x3FL);
        this.f[n] = b;
        if (n >= 63) {
            this.a(new NFBuffer(this.f));
        }
    }
    
    private static final int a(final int n, final int n2) {
        if (n2 <= 0) {
            return n;
        }
        return n << n2 | (n >> 1 & Integer.MAX_VALUE) >> 31 - n2;
    }
    
    private static final int a(int a, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        a += ((n & n2) | (~n & n3)) + n4 + n6;
        a = a(a, n5);
        return a + n;
    }
    
    private static final int b(int a, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        a += ((n & n3) | (n2 & ~n3)) + n4 + n6;
        a = a(a, n5);
        return a + n;
    }
    
    private static final int c(int a, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        a += (n ^ n2 ^ n3) + n4 + n6;
        a = a(a, n5);
        return a + n;
    }
    
    private static final int d(int a, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        a += (n2 ^ (n | ~n3)) + n4 + n6;
        a = a(a, n5);
        return a + n;
    }
    
    private static final int a(final NFBuffer nfBuffer, final int n) {
        return (nfBuffer.get(n) & 0xFF) | (nfBuffer.get(n + 1) & 0xFF) << 8 | (nfBuffer.get(n + 2) & 0xFF) << 16 | (nfBuffer.get(n + 3) & 0xFF) << 24;
    }
    
    private static final int a(final byte[] array, final int n) {
        return (array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF) << 16 | (array[n + 3] & 0xFF) << 24;
    }
    
    private final void a(final NFBuffer nfBuffer) {
        final int b = this.b;
        final int c = this.c;
        final int d = this.d;
        final int e = this.e;
        final int a = a(nfBuffer, 0);
        final int a2 = a(nfBuffer, 4);
        final int a3 = a(nfBuffer, 8);
        final int a4 = a(nfBuffer, 12);
        final int a5 = a(nfBuffer, 16);
        final int a6 = a(nfBuffer, 20);
        final int a7 = a(nfBuffer, 24);
        final int a8 = a(nfBuffer, 28);
        final int a9 = a(nfBuffer, 32);
        final int a10 = a(nfBuffer, 36);
        final int a11 = a(nfBuffer, 40);
        final int a12 = a(nfBuffer, 44);
        final int a13 = a(nfBuffer, 48);
        final int a14 = a(nfBuffer, 52);
        final int a15 = a(nfBuffer, 56);
        final int a16 = a(nfBuffer, 60);
        final int a17 = a(b, c, d, e, a, 7, -680876936);
        final int a18 = a(e, a17, c, d, a2, 12, -389564586);
        final int a19 = a(d, a18, a17, c, a3, 17, 606105819);
        final int a20 = a(c, a19, a18, a17, a4, 22, -1044525330);
        final int a21 = a(a17, a20, a19, a18, a5, 7, -176418897);
        final int a22 = a(a18, a21, a20, a19, a6, 12, 1200080426);
        final int a23 = a(a19, a22, a21, a20, a7, 17, -1473231341);
        final int a24 = a(a20, a23, a22, a21, a8, 22, -45705983);
        final int a25 = a(a21, a24, a23, a22, a9, 7, 1770035416);
        final int a26 = a(a22, a25, a24, a23, a10, 12, -1958414417);
        final int a27 = a(a23, a26, a25, a24, a11, 17, -42063);
        final int a28 = a(a24, a27, a26, a25, a12, 22, -1990404162);
        final int a29 = a(a25, a28, a27, a26, a13, 7, 1804603682);
        final int a30 = a(a26, a29, a28, a27, a14, 12, -40341101);
        final int a31 = a(a27, a30, a29, a28, a15, 17, -1502002290);
        final int a32 = a(a28, a31, a30, a29, a16, 22, 1236535329);
        final int b2 = b(a29, a32, a31, a30, a2, 5, -165796510);
        final int b3 = b(a30, b2, a32, a31, a7, 9, -1069501632);
        final int b4 = b(a31, b3, b2, a32, a12, 14, 643717713);
        final int b5 = b(a32, b4, b3, b2, a, 20, -373897302);
        final int b6 = b(b2, b5, b4, b3, a6, 5, -701558691);
        final int b7 = b(b3, b6, b5, b4, a11, 9, 38016083);
        final int b8 = b(b4, b7, b6, b5, a16, 14, -660478335);
        final int b9 = b(b5, b8, b7, b6, a5, 20, -405537848);
        final int b10 = b(b6, b9, b8, b7, a10, 5, 568446438);
        final int b11 = b(b7, b10, b9, b8, a15, 9, -1019803690);
        final int b12 = b(b8, b11, b10, b9, a4, 14, -187363961);
        final int b13 = b(b9, b12, b11, b10, a9, 20, 1163531501);
        final int b14 = b(b10, b13, b12, b11, a14, 5, -1444681467);
        final int b15 = b(b11, b14, b13, b12, a3, 9, -51403784);
        final int b16 = b(b12, b15, b14, b13, a8, 14, 1735328473);
        final int b17 = b(b13, b16, b15, b14, a13, 20, -1926607734);
        final int c2 = c(b14, b17, b16, b15, a6, 4, -378558);
        final int c3 = c(b15, c2, b17, b16, a9, 11, -2022574463);
        final int c4 = c(b16, c3, c2, b17, a12, 16, 1839030562);
        final int c5 = c(b17, c4, c3, c2, a15, 23, -35309556);
        final int c6 = c(c2, c5, c4, c3, a2, 4, -1530992060);
        final int c7 = c(c3, c6, c5, c4, a5, 11, 1272893353);
        final int c8 = c(c4, c7, c6, c5, a8, 16, -155497632);
        final int c9 = c(c5, c8, c7, c6, a11, 23, -1094730640);
        final int c10 = c(c6, c9, c8, c7, a14, 4, 681279174);
        final int c11 = c(c7, c10, c9, c8, a, 11, -358537222);
        final int c12 = c(c8, c11, c10, c9, a4, 16, -722521979);
        final int c13 = c(c9, c12, c11, c10, a7, 23, 76029189);
        final int c14 = c(c10, c13, c12, c11, a10, 4, -640364487);
        final int c15 = c(c11, c14, c13, c12, a13, 11, -421815835);
        final int c16 = c(c12, c15, c14, c13, a16, 16, 530742520);
        final int c17 = c(c13, c16, c15, c14, a3, 23, -995338651);
        final int d2 = d(c14, c17, c16, c15, a, 6, -198630844);
        final int d3 = d(c15, d2, c17, c16, a8, 10, 1126891415);
        final int d4 = d(c16, d3, d2, c17, a15, 15, -1416354905);
        final int d5 = d(c17, d4, d3, d2, a6, 21, -57434055);
        final int d6 = d(d2, d5, d4, d3, a13, 6, 1700485571);
        final int d7 = d(d3, d6, d5, d4, a4, 10, -1894986606);
        final int d8 = d(d4, d7, d6, d5, a11, 15, -1051523);
        final int d9 = d(d5, d8, d7, d6, a2, 21, -2054922799);
        final int d10 = d(d6, d9, d8, d7, a9, 6, 1873313359);
        final int d11 = d(d7, d10, d9, d8, a16, 10, -30611744);
        final int d12 = d(d8, d11, d10, d9, a7, 15, -1560198380);
        final int d13 = d(d9, d12, d11, d10, a14, 21, 1309151649);
        final int d14 = d(d10, d13, d12, d11, a5, 6, -145523070);
        final int d15 = d(d11, d14, d13, d12, a12, 10, -1120210379);
        final int d16 = d(d12, d15, d14, d13, a3, 15, 718787259);
        final int d17 = d(d13, d16, d15, d14, a10, 21, -343485551);
        this.b += d14;
        this.c += d17;
        this.d += d16;
        this.e += d15;
    }
    
    private static final void a(final NFBuffer nfBuffer, final int n, final int n2) {
        nfBuffer.put(n, (byte)(n2 & 0xFF));
        nfBuffer.put(n + 1, (byte)(n2 >> 8 & 0xFF));
        nfBuffer.put(n + 2, (byte)(n2 >> 16 & 0xFF));
        nfBuffer.put(n + 3, (byte)(n2 >> 24 & 0xFF));
    }
    
    public final NFBuffer Finalize(NFBuffer nfBuffer) {
        if (nfBuffer == null) {
            nfBuffer = new NFBuffer(new byte[16]);
        }
        a(nfBuffer, 0, (int)(this.a << 3));
        a(nfBuffer, 4, (int)(this.a >> 29));
        final int n = (int)(this.a & 0x3FL);
        this.Update(new NFBuffer(NFMd5.w, 0, (n < 56) ? (56 - n) : (120 - n)));
        this.Update(new NFBuffer(nfBuffer, 0, 8));
        a(nfBuffer, 0, this.b);
        a(nfBuffer, 4, this.c);
        a(nfBuffer, 8, this.d);
        a(nfBuffer, 12, this.e);
        this.f = null;
        return nfBuffer;
    }
    
    static {
        w = new byte[] { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
