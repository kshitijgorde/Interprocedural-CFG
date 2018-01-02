// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

public class MD5
{
    private final int[] W;
    private long bytecount;
    private int A;
    private int B;
    private int C;
    private int D;
    
    public MD5() {
        this.W = new int[16];
        this.engineReset();
    }
    
    public void engineReset() {
        this.bytecount = 0L;
        this.A = 1732584193;
        this.B = -271733879;
        this.C = -1732584194;
        this.D = 271733878;
        for (int i = 0; i < 16; ++i) {
            this.W[i] = 0;
        }
    }
    
    public void update(final byte b) {
        final int n = (int)this.bytecount % 64;
        final int n2 = (3 - n % 4) * 8;
        final int n3 = n / 4;
        this.W[n3] = ((this.W[n3] & ~(255 << n2)) | (b & 0xFF) << n2);
        final long bytecount = this.bytecount + 1L;
        this.bytecount = bytecount;
        if (bytecount % 64 == 0) {
            this.munch();
        }
    }
    
    public void update(final byte[] array) {
        this.update(array, 0, array.length);
    }
    
    public void update(final byte[] array, int i, final int n) {
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        while (i < i + n) {
            this.update(array[i++]);
        }
    }
    
    public byte[] digest(final byte[] array) {
        this.update(array);
        return this.digest();
    }
    
    public byte[] digest() {
        final long n = this.bytecount * 8;
        this.update((byte)(-128));
        while ((int)this.bytecount % 64 != 56) {
            this.update((byte)0);
        }
        this.W[14] = this.SWAP((int)(-1 & n));
        this.W[15] = this.SWAP((int)(-1 & n >>> 32));
        this.bytecount += 8;
        this.munch();
        this.A = this.SWAP(this.A);
        this.B = this.SWAP(this.B);
        this.C = this.SWAP(this.C);
        this.D = this.SWAP(this.D);
        final byte[] array = { (byte)(this.A >>> 24), (byte)(this.A >>> 16), (byte)(this.A >>> 8), (byte)this.A, (byte)(this.B >>> 24), (byte)(this.B >>> 16), (byte)(this.B >>> 8), (byte)this.B, (byte)(this.C >>> 24), (byte)(this.C >>> 16), (byte)(this.C >>> 8), (byte)this.C, (byte)(this.D >>> 24), (byte)(this.D >>> 16), (byte)(this.D >>> 8), (byte)this.D };
        this.engineReset();
        return array;
    }
    
    private int F(final int n, final int n2, final int n3) {
        return (n & n2) | (~n & n3);
    }
    
    private int G(final int n, final int n2, final int n3) {
        return (n & n3) | (n2 & ~n3);
    }
    
    private int H(final int n, final int n2, final int n3) {
        return n ^ n2 ^ n3;
    }
    
    private int I(final int n, final int n2, final int n3) {
        return n2 ^ (n | ~n3);
    }
    
    private int rotateLeft(final int n, final int n2) {
        return n << n2 | n >>> 32 - n2;
    }
    
    private int FF(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        n += this.F(n2, n3, n4) + n5 + n7;
        return n2 + this.rotateLeft(n, n6);
    }
    
    private int GG(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        n += this.G(n2, n3, n4) + n5 + n7;
        return n2 + this.rotateLeft(n, n6);
    }
    
    private int HH(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        n += this.H(n2, n3, n4) + n5 + n7;
        return n2 + this.rotateLeft(n, n6);
    }
    
    private int II(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        n += this.I(n2, n3, n4) + n5 + n7;
        return n2 + this.rotateLeft(n, n6);
    }
    
    private int SWAP(final int n) {
        return (0xFF & n) << 24 | (n & 0xFF00) << 8 | (n >>> 8 & 0xFF00) | n >>> 24;
    }
    
    private void munch() {
        final int[] array = new int[16];
        for (int i = 0; i < 16; ++i) {
            array[i] = this.SWAP(this.W[i]);
        }
        final int a = this.A;
        final int b = this.B;
        final int c = this.C;
        final int d = this.D;
        this.A = this.FF(this.A, this.B, this.C, this.D, array[0], 7, -680876936);
        this.D = this.FF(this.D, this.A, this.B, this.C, array[1], 12, -389564586);
        this.C = this.FF(this.C, this.D, this.A, this.B, array[2], 17, 606105819);
        this.B = this.FF(this.B, this.C, this.D, this.A, array[3], 22, -1044525330);
        this.A = this.FF(this.A, this.B, this.C, this.D, array[4], 7, -176418897);
        this.D = this.FF(this.D, this.A, this.B, this.C, array[5], 12, 1200080426);
        this.C = this.FF(this.C, this.D, this.A, this.B, array[6], 17, -1473231341);
        this.B = this.FF(this.B, this.C, this.D, this.A, array[7], 22, -45705983);
        this.A = this.FF(this.A, this.B, this.C, this.D, array[8], 7, 1770035416);
        this.D = this.FF(this.D, this.A, this.B, this.C, array[9], 12, -1958414417);
        this.C = this.FF(this.C, this.D, this.A, this.B, array[10], 17, -42063);
        this.B = this.FF(this.B, this.C, this.D, this.A, array[11], 22, -1990404162);
        this.A = this.FF(this.A, this.B, this.C, this.D, array[12], 7, 1804603682);
        this.D = this.FF(this.D, this.A, this.B, this.C, array[13], 12, -40341101);
        this.C = this.FF(this.C, this.D, this.A, this.B, array[14], 17, -1502002290);
        this.B = this.FF(this.B, this.C, this.D, this.A, array[15], 22, 1236535329);
        this.A = this.GG(this.A, this.B, this.C, this.D, array[1], 5, -165796510);
        this.D = this.GG(this.D, this.A, this.B, this.C, array[6], 9, -1069501632);
        this.C = this.GG(this.C, this.D, this.A, this.B, array[11], 14, 643717713);
        this.B = this.GG(this.B, this.C, this.D, this.A, array[0], 20, -373897302);
        this.A = this.GG(this.A, this.B, this.C, this.D, array[5], 5, -701558691);
        this.D = this.GG(this.D, this.A, this.B, this.C, array[10], 9, 38016083);
        this.C = this.GG(this.C, this.D, this.A, this.B, array[15], 14, -660478335);
        this.B = this.GG(this.B, this.C, this.D, this.A, array[4], 20, -405537848);
        this.A = this.GG(this.A, this.B, this.C, this.D, array[9], 5, 568446438);
        this.D = this.GG(this.D, this.A, this.B, this.C, array[14], 9, -1019803690);
        this.C = this.GG(this.C, this.D, this.A, this.B, array[3], 14, -187363961);
        this.B = this.GG(this.B, this.C, this.D, this.A, array[8], 20, 1163531501);
        this.A = this.GG(this.A, this.B, this.C, this.D, array[13], 5, -1444681467);
        this.D = this.GG(this.D, this.A, this.B, this.C, array[2], 9, -51403784);
        this.C = this.GG(this.C, this.D, this.A, this.B, array[7], 14, 1735328473);
        this.B = this.GG(this.B, this.C, this.D, this.A, array[12], 20, -1926607734);
        this.A = this.HH(this.A, this.B, this.C, this.D, array[5], 4, -378558);
        this.D = this.HH(this.D, this.A, this.B, this.C, array[8], 11, -2022574463);
        this.C = this.HH(this.C, this.D, this.A, this.B, array[11], 16, 1839030562);
        this.B = this.HH(this.B, this.C, this.D, this.A, array[14], 23, -35309556);
        this.A = this.HH(this.A, this.B, this.C, this.D, array[1], 4, -1530992060);
        this.D = this.HH(this.D, this.A, this.B, this.C, array[4], 11, 1272893353);
        this.C = this.HH(this.C, this.D, this.A, this.B, array[7], 16, -155497632);
        this.B = this.HH(this.B, this.C, this.D, this.A, array[10], 23, -1094730640);
        this.A = this.HH(this.A, this.B, this.C, this.D, array[13], 4, 681279174);
        this.D = this.HH(this.D, this.A, this.B, this.C, array[0], 11, -358537222);
        this.C = this.HH(this.C, this.D, this.A, this.B, array[3], 16, -722521979);
        this.B = this.HH(this.B, this.C, this.D, this.A, array[6], 23, 76029189);
        this.A = this.HH(this.A, this.B, this.C, this.D, array[9], 4, -640364487);
        this.D = this.HH(this.D, this.A, this.B, this.C, array[12], 11, -421815835);
        this.C = this.HH(this.C, this.D, this.A, this.B, array[15], 16, 530742520);
        this.B = this.HH(this.B, this.C, this.D, this.A, array[2], 23, -995338651);
        this.A = this.II(this.A, this.B, this.C, this.D, array[0], 6, -198630844);
        this.D = this.II(this.D, this.A, this.B, this.C, array[7], 10, 1126891415);
        this.C = this.II(this.C, this.D, this.A, this.B, array[14], 15, -1416354905);
        this.B = this.II(this.B, this.C, this.D, this.A, array[5], 21, -57434055);
        this.A = this.II(this.A, this.B, this.C, this.D, array[12], 6, 1700485571);
        this.D = this.II(this.D, this.A, this.B, this.C, array[3], 10, -1894986606);
        this.C = this.II(this.C, this.D, this.A, this.B, array[10], 15, -1051523);
        this.B = this.II(this.B, this.C, this.D, this.A, array[1], 21, -2054922799);
        this.A = this.II(this.A, this.B, this.C, this.D, array[8], 6, 1873313359);
        this.D = this.II(this.D, this.A, this.B, this.C, array[15], 10, -30611744);
        this.C = this.II(this.C, this.D, this.A, this.B, array[6], 15, -1560198380);
        this.B = this.II(this.B, this.C, this.D, this.A, array[13], 21, 1309151649);
        this.A = this.II(this.A, this.B, this.C, this.D, array[4], 6, -145523070);
        this.D = this.II(this.D, this.A, this.B, this.C, array[11], 10, -1120210379);
        this.C = this.II(this.C, this.D, this.A, this.B, array[2], 15, 718787259);
        this.B = this.II(this.B, this.C, this.D, this.A, array[9], 21, -343485551);
        this.A += a;
        this.B += b;
        this.C += c;
        this.D += d;
    }
}
