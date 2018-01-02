// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class SHA1 extends MessageDigest
{
    private int[] hash;
    private int[] W;
    private long count;
    private int rest;
    private byte[] buffer;
    static byte[] padding;
    
    private static int rotateLeft(final int x, final int n) {
        return x << n | x >>> 32 - n;
    }
    
    private static int F00_19(final int x, final int y, final int z) {
        return (((y ^ z) & x) ^ z) + 1518500249;
    }
    
    private static int F20_39(final int x, final int y, final int z) {
        return (x ^ y ^ z) + 1859775393;
    }
    
    private static int F40_59(final int x, final int y, final int z) {
        return ((x & y) | ((x | y) & z)) - 1894007588;
    }
    
    private static int F60_79(final int x, final int y, final int z) {
        return (x ^ y ^ z) - 899497514;
    }
    
    private void transform(final byte[] data, int offset) {
        int a = this.hash[0];
        int b = this.hash[1];
        int c = this.hash[2];
        int d = this.hash[3];
        int e = this.hash[4];
        for (int i = 0; i < 16; ++i) {
            this.W[i] = ((data[offset++] & 0xFF) << 24 | (data[offset++] & 0xFF) << 16 | (data[offset++] & 0xFF) << 8 | (data[offset++] & 0xFF));
        }
        for (int i = 16; i < 80; ++i) {
            final int t = this.W[i - 3] ^ this.W[i - 8] ^ this.W[i - 14] ^ this.W[i - 16];
            this.W[i] = rotateLeft(t, 1);
        }
        for (int i = 0; i < 20; ++i) {
            final int t = rotateLeft(a, 5) + F00_19(b, c, d) + e + this.W[i];
            e = d;
            d = c;
            c = rotateLeft(b, 30);
            b = a;
            a = t;
        }
        for (int i = 20; i < 40; ++i) {
            final int t = rotateLeft(a, 5) + F20_39(b, c, d) + e + this.W[i];
            e = d;
            d = c;
            c = rotateLeft(b, 30);
            b = a;
            a = t;
        }
        for (int i = 40; i < 60; ++i) {
            final int t = rotateLeft(a, 5) + F40_59(b, c, d) + e + this.W[i];
            e = d;
            d = c;
            c = rotateLeft(b, 30);
            b = a;
            a = t;
        }
        for (int i = 60; i < 80; ++i) {
            final int t = rotateLeft(a, 5) + F60_79(b, c, d) + e + this.W[i];
            e = d;
            d = c;
            c = rotateLeft(b, 30);
            b = a;
            a = t;
        }
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
        final int[] hash5 = this.hash;
        final int n5 = 4;
        hash5[n5] += e;
    }
    
    public SHA1() {
        this.buffer = new byte[64];
        this.hash = new int[5];
        this.W = new int[80];
        this.reset();
    }
    
    private SHA1(final SHA1 c) {
        this.buffer = new byte[64];
        this.hash = new int[5];
        this.W = new int[80];
        System.arraycopy(c.hash, 0, this.hash, 0, 5);
        System.arraycopy(c.buffer, 0, this.buffer, 0, 64);
        this.count = c.count;
        this.rest = c.rest;
    }
    
    public Object clone() {
        return new SHA1(this);
    }
    
    public String getName() {
        return "SHA1";
    }
    
    public void reset() {
        this.hash[0] = 1732584193;
        this.hash[1] = -271733879;
        this.hash[2] = -1732584194;
        this.hash[3] = 271733878;
        this.hash[4] = -1009589776;
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
        final byte[] buf = new byte[20];
        this.digestInto(buf, 0);
        return buf;
    }
    
    public int digestInto(final byte[] dest, int destOff) {
        final int padlen = (this.rest < 56) ? (56 - this.rest) : (120 - this.rest);
        this.count *= 8L;
        final byte[] countBytes = { (byte)(this.count >> 56), (byte)(this.count >> 58), (byte)(this.count >> 40), (byte)(this.count >> 32), (byte)(this.count >> 24), (byte)(this.count >> 16), (byte)(this.count >> 8), (byte)this.count };
        this.update(SHA1.padding, 0, padlen);
        this.update(countBytes, 0, 8);
        for (int i = 0; i < 5; ++i) {
            dest[destOff++] = (byte)(this.hash[i] >>> 24 & 0xFF);
            dest[destOff++] = (byte)(this.hash[i] >>> 16 & 0xFF);
            dest[destOff++] = (byte)(this.hash[i] >>> 8 & 0xFF);
            dest[destOff++] = (byte)(this.hash[i] & 0xFF);
        }
        this.reset();
        return 20;
    }
    
    public int blockSize() {
        return 64;
    }
    
    public int hashSize() {
        return 20;
    }
    
    static {
        SHA1.padding = new byte[] { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
