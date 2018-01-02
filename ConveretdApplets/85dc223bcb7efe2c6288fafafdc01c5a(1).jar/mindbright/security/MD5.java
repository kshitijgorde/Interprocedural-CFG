// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class MD5 extends MessageDigest implements Cloneable
{
    private int[] x;
    private long count;
    private int rest;
    int[] hash;
    byte[] buffer;
    static byte[] padding;
    
    private static int rotateLeft(final int x, final int n) {
        return x << n | x >>> 32 - n;
    }
    
    private static int FF(int a, final int b, final int c, final int d, final int x, final int s, final int ac) {
        a = a + (((c ^ d) & b) ^ d) + x + ac;
        return rotateLeft(a, s) + b;
    }
    
    private static int GG(int a, final int b, final int c, final int d, final int x, final int s, final int ac) {
        a = a + (((b ^ c) & d) ^ c) + x + ac;
        return rotateLeft(a, s) + b;
    }
    
    private static int HH(int a, final int b, final int c, final int d, final int x, final int s, final int ac) {
        a = a + (b ^ c ^ d) + x + ac;
        return rotateLeft(a, s) + b;
    }
    
    private static int II(int a, final int b, final int c, final int d, final int x, final int s, final int ac) {
        a = a + (c ^ (b | ~d)) + x + ac;
        return rotateLeft(a, s) + b;
    }
    
    void transform(final byte[] data, int offset) {
        int a = this.hash[0];
        int b = this.hash[1];
        int c = this.hash[2];
        int d = this.hash[3];
        for (int i = 0; i < 16; ++i) {
            this.x[i] = ((data[offset++] & 0xFF) | (data[offset++] & 0xFF) << 8 | (data[offset++] & 0xFF) << 16 | (data[offset++] & 0xFF) << 24);
        }
        a = FF(a, b, c, d, this.x[0], 7, -680876936);
        d = FF(d, a, b, c, this.x[1], 12, -389564586);
        c = FF(c, d, a, b, this.x[2], 17, 606105819);
        b = FF(b, c, d, a, this.x[3], 22, -1044525330);
        a = FF(a, b, c, d, this.x[4], 7, -176418897);
        d = FF(d, a, b, c, this.x[5], 12, 1200080426);
        c = FF(c, d, a, b, this.x[6], 17, -1473231341);
        b = FF(b, c, d, a, this.x[7], 22, -45705983);
        a = FF(a, b, c, d, this.x[8], 7, 1770035416);
        d = FF(d, a, b, c, this.x[9], 12, -1958414417);
        c = FF(c, d, a, b, this.x[10], 17, -42063);
        b = FF(b, c, d, a, this.x[11], 22, -1990404162);
        a = FF(a, b, c, d, this.x[12], 7, 1804603682);
        d = FF(d, a, b, c, this.x[13], 12, -40341101);
        c = FF(c, d, a, b, this.x[14], 17, -1502002290);
        b = FF(b, c, d, a, this.x[15], 22, 1236535329);
        a = GG(a, b, c, d, this.x[1], 5, -165796510);
        d = GG(d, a, b, c, this.x[6], 9, -1069501632);
        c = GG(c, d, a, b, this.x[11], 14, 643717713);
        b = GG(b, c, d, a, this.x[0], 20, -373897302);
        a = GG(a, b, c, d, this.x[5], 5, -701558691);
        d = GG(d, a, b, c, this.x[10], 9, 38016083);
        c = GG(c, d, a, b, this.x[15], 14, -660478335);
        b = GG(b, c, d, a, this.x[4], 20, -405537848);
        a = GG(a, b, c, d, this.x[9], 5, 568446438);
        d = GG(d, a, b, c, this.x[14], 9, -1019803690);
        c = GG(c, d, a, b, this.x[3], 14, -187363961);
        b = GG(b, c, d, a, this.x[8], 20, 1163531501);
        a = GG(a, b, c, d, this.x[13], 5, -1444681467);
        d = GG(d, a, b, c, this.x[2], 9, -51403784);
        c = GG(c, d, a, b, this.x[7], 14, 1735328473);
        b = GG(b, c, d, a, this.x[12], 20, -1926607734);
        a = HH(a, b, c, d, this.x[5], 4, -378558);
        d = HH(d, a, b, c, this.x[8], 11, -2022574463);
        c = HH(c, d, a, b, this.x[11], 16, 1839030562);
        b = HH(b, c, d, a, this.x[14], 23, -35309556);
        a = HH(a, b, c, d, this.x[1], 4, -1530992060);
        d = HH(d, a, b, c, this.x[4], 11, 1272893353);
        c = HH(c, d, a, b, this.x[7], 16, -155497632);
        b = HH(b, c, d, a, this.x[10], 23, -1094730640);
        a = HH(a, b, c, d, this.x[13], 4, 681279174);
        d = HH(d, a, b, c, this.x[0], 11, -358537222);
        c = HH(c, d, a, b, this.x[3], 16, -722521979);
        b = HH(b, c, d, a, this.x[6], 23, 76029189);
        a = HH(a, b, c, d, this.x[9], 4, -640364487);
        d = HH(d, a, b, c, this.x[12], 11, -421815835);
        c = HH(c, d, a, b, this.x[15], 16, 530742520);
        b = HH(b, c, d, a, this.x[2], 23, -995338651);
        a = II(a, b, c, d, this.x[0], 6, -198630844);
        d = II(d, a, b, c, this.x[7], 10, 1126891415);
        c = II(c, d, a, b, this.x[14], 15, -1416354905);
        b = II(b, c, d, a, this.x[5], 21, -57434055);
        a = II(a, b, c, d, this.x[12], 6, 1700485571);
        d = II(d, a, b, c, this.x[3], 10, -1894986606);
        c = II(c, d, a, b, this.x[10], 15, -1051523);
        b = II(b, c, d, a, this.x[1], 21, -2054922799);
        a = II(a, b, c, d, this.x[8], 6, 1873313359);
        d = II(d, a, b, c, this.x[15], 10, -30611744);
        c = II(c, d, a, b, this.x[6], 15, -1560198380);
        b = II(b, c, d, a, this.x[13], 21, 1309151649);
        a = II(a, b, c, d, this.x[4], 6, -145523070);
        d = II(d, a, b, c, this.x[11], 10, -1120210379);
        c = II(c, d, a, b, this.x[2], 15, 718787259);
        b = II(b, c, d, a, this.x[9], 21, -343485551);
        final int[] hash = this.hash;
        final int n = 0;
        hash[n] += a;
        final int[] hash2 = this.hash;
        final int n2 = 1;
        hash2[n2] += b;
        final int[] hash3 = this.hash;
        final int n3 = 2;
        hash3[n3] += c;
        final int[] hash4 = this.hash;
        final int n4 = 3;
        hash4[n4] += d;
    }
    
    public MD5() {
        this.buffer = new byte[64];
        this.hash = new int[4];
        this.x = new int[16];
        this.reset();
    }
    
    private MD5(final MD5 c) {
        this.buffer = new byte[64];
        this.hash = new int[4];
        this.x = new int[16];
        System.arraycopy(c.hash, 0, this.hash, 0, 4);
        System.arraycopy(c.buffer, 0, this.buffer, 0, 64);
        this.count = c.count;
        this.rest = c.rest;
    }
    
    public Object clone() {
        return new MD5(this);
    }
    
    public String getName() {
        return "MD5";
    }
    
    public void reset() {
        this.hash[0] = 1732584193;
        this.hash[1] = -271733879;
        this.hash[2] = -1732584194;
        this.hash[3] = 271733878;
        this.count = 0L;
        this.rest = 0;
    }
    
    public void update(final byte[] data, int offset, int length) {
        final int left = 64 - this.rest;
        this.count += length;
        if (this.rest > 0 && length >= left) {
            System.arraycopy(data, offset, this.buffer, this.rest, left);
            this.transform(this.buffer, 0);
            offset += left;
            length -= left;
            this.rest = 0;
        }
        while (length > 63) {
            this.transform(data, offset);
            offset += 64;
            length -= 64;
        }
        if (length > 0) {
            System.arraycopy(data, offset, this.buffer, this.rest, length);
            this.rest += length;
        }
    }
    
    public byte[] digest() {
        final byte[] buf = new byte[16];
        this.digestInto(buf, 0);
        return buf;
    }
    
    public int digestInto(final byte[] dest, int destOff) {
        final int padlen = (this.rest < 56) ? (56 - this.rest) : (120 - this.rest);
        this.count *= 8L;
        final byte[] countBytes = { (byte)this.count, (byte)(this.count >>> 8), (byte)(this.count >>> 16), (byte)(this.count >>> 24), (byte)(this.count >>> 32), (byte)(this.count >>> 40), (byte)(this.count >>> 58), (byte)(this.count >>> 56) };
        this.update(MD5.padding, 0, padlen);
        this.update(countBytes, 0, 8);
        for (int i = 0; i < 4; ++i) {
            dest[destOff++] = (byte)(this.hash[i] & 0xFF);
            dest[destOff++] = (byte)(this.hash[i] >>> 8 & 0xFF);
            dest[destOff++] = (byte)(this.hash[i] >>> 16 & 0xFF);
            dest[destOff++] = (byte)(this.hash[i] >>> 24 & 0xFF);
        }
        this.reset();
        return 16;
    }
    
    public int blockSize() {
        return 64;
    }
    
    public int hashSize() {
        return 16;
    }
    
    static {
        MD5.padding = new byte[] { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
