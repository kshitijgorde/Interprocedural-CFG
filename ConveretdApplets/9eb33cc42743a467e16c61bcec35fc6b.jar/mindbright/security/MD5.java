// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class MD5 extends ch implements Cloneable
{
    public int[] m5;
    public long nc;
    public int nb;
    public int[] na;
    public byte[] m9;
    public static byte[] m8;
    
    private static int nl(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8;
        n = (n8 = n + (((n3 ^ n4) & n2) ^ n4) + n5 + n7);
        return (n8 << n6 | n8 >>> 32 - n6) + n2;
    }
    
    private static int nk(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8;
        n = (n8 = n + (((n2 ^ n3) & n4) ^ n3) + n5 + n7);
        return (n8 << n6 | n8 >>> 32 - n6) + n2;
    }
    
    private static int nj(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8;
        n = (n8 = n + (n2 ^ n3 ^ n4) + n5 + n7);
        return (n8 << n6 | n8 >>> 32 - n6) + n2;
    }
    
    private static int ni(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8;
        n = (n8 = n + (n3 ^ (n2 | ~n4)) + n5 + n7);
        return (n8 << n6 | n8 >>> 32 - n6) + n2;
    }
    
    public final void nh(final byte[] array, int n) {
        final int n2 = this.na[0];
        final int n3 = this.na[1];
        final int n4 = this.na[2];
        final int n5 = this.na[3];
        for (int i = 0; i < 16; ++i) {
            this.m5[i] = ((array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24);
        }
        final int nl = nl(n2, n3, n4, n5, this.m5[0], 7, -680876936);
        final int nl2 = nl(n5, nl, n3, n4, this.m5[1], 12, -389564586);
        final int nl3 = nl(n4, nl2, nl, n3, this.m5[2], 17, 606105819);
        final int nl4 = nl(n3, nl3, nl2, nl, this.m5[3], 22, -1044525330);
        final int nl5 = nl(nl, nl4, nl3, nl2, this.m5[4], 7, -176418897);
        final int nl6 = nl(nl2, nl5, nl4, nl3, this.m5[5], 12, 1200080426);
        final int nl7 = nl(nl3, nl6, nl5, nl4, this.m5[6], 17, -1473231341);
        final int nl8 = nl(nl4, nl7, nl6, nl5, this.m5[7], 22, -45705983);
        final int nl9 = nl(nl5, nl8, nl7, nl6, this.m5[8], 7, 1770035416);
        final int nl10 = nl(nl6, nl9, nl8, nl7, this.m5[9], 12, -1958414417);
        final int nl11 = nl(nl7, nl10, nl9, nl8, this.m5[10], 17, -42063);
        final int nl12 = nl(nl8, nl11, nl10, nl9, this.m5[11], 22, -1990404162);
        final int nl13 = nl(nl9, nl12, nl11, nl10, this.m5[12], 7, 1804603682);
        final int nl14 = nl(nl10, nl13, nl12, nl11, this.m5[13], 12, -40341101);
        final int nl15 = nl(nl11, nl14, nl13, nl12, this.m5[14], 17, -1502002290);
        final int nl16 = nl(nl12, nl15, nl14, nl13, this.m5[15], 22, 1236535329);
        final int nk = nk(nl13, nl16, nl15, nl14, this.m5[1], 5, -165796510);
        final int nk2 = nk(nl14, nk, nl16, nl15, this.m5[6], 9, -1069501632);
        final int nk3 = nk(nl15, nk2, nk, nl16, this.m5[11], 14, 643717713);
        final int nk4 = nk(nl16, nk3, nk2, nk, this.m5[0], 20, -373897302);
        final int nk5 = nk(nk, nk4, nk3, nk2, this.m5[5], 5, -701558691);
        final int nk6 = nk(nk2, nk5, nk4, nk3, this.m5[10], 9, 38016083);
        final int nk7 = nk(nk3, nk6, nk5, nk4, this.m5[15], 14, -660478335);
        final int nk8 = nk(nk4, nk7, nk6, nk5, this.m5[4], 20, -405537848);
        final int nk9 = nk(nk5, nk8, nk7, nk6, this.m5[9], 5, 568446438);
        final int nk10 = nk(nk6, nk9, nk8, nk7, this.m5[14], 9, -1019803690);
        final int nk11 = nk(nk7, nk10, nk9, nk8, this.m5[3], 14, -187363961);
        final int nk12 = nk(nk8, nk11, nk10, nk9, this.m5[8], 20, 1163531501);
        final int nk13 = nk(nk9, nk12, nk11, nk10, this.m5[13], 5, -1444681467);
        final int nk14 = nk(nk10, nk13, nk12, nk11, this.m5[2], 9, -51403784);
        final int nk15 = nk(nk11, nk14, nk13, nk12, this.m5[7], 14, 1735328473);
        final int nk16 = nk(nk12, nk15, nk14, nk13, this.m5[12], 20, -1926607734);
        final int nj = nj(nk13, nk16, nk15, nk14, this.m5[5], 4, -378558);
        final int nj2 = nj(nk14, nj, nk16, nk15, this.m5[8], 11, -2022574463);
        final int nj3 = nj(nk15, nj2, nj, nk16, this.m5[11], 16, 1839030562);
        final int nj4 = nj(nk16, nj3, nj2, nj, this.m5[14], 23, -35309556);
        final int nj5 = nj(nj, nj4, nj3, nj2, this.m5[1], 4, -1530992060);
        final int nj6 = nj(nj2, nj5, nj4, nj3, this.m5[4], 11, 1272893353);
        final int nj7 = nj(nj3, nj6, nj5, nj4, this.m5[7], 16, -155497632);
        final int nj8 = nj(nj4, nj7, nj6, nj5, this.m5[10], 23, -1094730640);
        final int nj9 = nj(nj5, nj8, nj7, nj6, this.m5[13], 4, 681279174);
        final int nj10 = nj(nj6, nj9, nj8, nj7, this.m5[0], 11, -358537222);
        final int nj11 = nj(nj7, nj10, nj9, nj8, this.m5[3], 16, -722521979);
        final int nj12 = nj(nj8, nj11, nj10, nj9, this.m5[6], 23, 76029189);
        final int nj13 = nj(nj9, nj12, nj11, nj10, this.m5[9], 4, -640364487);
        final int nj14 = nj(nj10, nj13, nj12, nj11, this.m5[12], 11, -421815835);
        final int nj15 = nj(nj11, nj14, nj13, nj12, this.m5[15], 16, 530742520);
        final int nj16 = nj(nj12, nj15, nj14, nj13, this.m5[2], 23, -995338651);
        final int ni = ni(nj13, nj16, nj15, nj14, this.m5[0], 6, -198630844);
        final int ni2 = ni(nj14, ni, nj16, nj15, this.m5[7], 10, 1126891415);
        final int ni3 = ni(nj15, ni2, ni, nj16, this.m5[14], 15, -1416354905);
        final int ni4 = ni(nj16, ni3, ni2, ni, this.m5[5], 21, -57434055);
        final int ni5 = ni(ni, ni4, ni3, ni2, this.m5[12], 6, 1700485571);
        final int ni6 = ni(ni2, ni5, ni4, ni3, this.m5[3], 10, -1894986606);
        final int ni7 = ni(ni3, ni6, ni5, ni4, this.m5[10], 15, -1051523);
        final int ni8 = ni(ni4, ni7, ni6, ni5, this.m5[1], 21, -2054922799);
        final int ni9 = ni(ni5, ni8, ni7, ni6, this.m5[8], 6, 1873313359);
        final int ni10 = ni(ni6, ni9, ni8, ni7, this.m5[15], 10, -30611744);
        final int ni11 = ni(ni7, ni10, ni9, ni8, this.m5[6], 15, -1560198380);
        final int ni12 = ni(ni8, ni11, ni10, ni9, this.m5[13], 21, 1309151649);
        final int ni13 = ni(ni9, ni12, ni11, ni10, this.m5[4], 6, -145523070);
        final int ni14 = ni(ni10, ni13, ni12, ni11, this.m5[11], 10, -1120210379);
        final int ni15 = ni(ni11, ni14, ni13, ni12, this.m5[2], 15, 718787259);
        final int ni16 = ni(ni12, ni15, ni14, ni13, this.m5[9], 21, -343485551);
        final int[] na = this.na;
        final int n6 = 0;
        na[n6] += ni13;
        final int[] na2 = this.na;
        final int n7 = 1;
        na2[n7] += ni16;
        final int[] na3 = this.na;
        final int n8 = 2;
        na3[n8] += ni15;
        final int[] na4 = this.na;
        final int n9 = 3;
        na4[n9] += ni14;
    }
    
    public MD5() {
        this.m9 = new byte[64];
        this.na = new int[4];
        this.m5 = new int[16];
        this.ng();
    }
    
    private MD5(final MD5 md5) {
        this.m9 = new byte[64];
        this.na = new int[4];
        this.m5 = new int[16];
        System.arraycopy(md5.na, 0, this.na, 0, 4);
        System.arraycopy(md5.m9, 0, this.m9, 0, 64);
        this.nc = md5.nc;
        this.nb = md5.nb;
    }
    
    public final Object clone() {
        return new MD5(this);
    }
    
    public final void ng() {
        this.na[0] = 1732584193;
        this.na[1] = -271733879;
        this.na[2] = -1732584194;
        this.na[3] = 271733878;
        this.nc = 0L;
        this.nb = 0;
    }
    
    public final void c3(final byte[] array, int n, int i) {
        final int n2 = 64 - this.nb;
        this.nc += i;
        if (this.nb > 0 && i >= n2) {
            System.arraycopy(array, n, this.m9, this.nb, n2);
            this.nh(this.m9, 0);
            n += n2;
            i -= n2;
            this.nb = 0;
        }
        while (i > 63) {
            this.nh(array, n);
            n += 64;
            i -= 64;
        }
        if (i > 0) {
            System.arraycopy(array, n, this.m9, this.nb, i);
            this.nb += i;
        }
    }
    
    public final byte[] nd() {
        final byte[] array = new byte[16];
        this.nf(array, 0);
        return array;
    }
    
    public final int nf(final byte[] array, int n) {
        final int n2 = (this.nb < 56) ? (56 - this.nb) : (120 - this.nb);
        this.nc *= 8L;
        final byte[] array2 = { (byte)this.nc, (byte)(this.nc >>> 8), (byte)(this.nc >>> 16), (byte)(this.nc >>> 24), (byte)(this.nc >>> 32), (byte)(this.nc >>> 40), (byte)(this.nc >>> 58), (byte)(this.nc >>> 56) };
        this.c3(MD5.m8, 0, n2);
        this.c3(array2, 0, 8);
        for (int i = 0; i < 4; ++i) {
            array[n++] = (byte)(this.na[i] & 0xFF);
            array[n++] = (byte)(this.na[i] >>> 8 & 0xFF);
            array[n++] = (byte)(this.na[i] >>> 16 & 0xFF);
            array[n++] = (byte)(this.na[i] >>> 24 & 0xFF);
        }
        this.ng();
        return 16;
    }
    
    static {
        final byte[] m8 = new byte[64];
        m8[0] = -128;
        MD5.m8 = m8;
    }
}
