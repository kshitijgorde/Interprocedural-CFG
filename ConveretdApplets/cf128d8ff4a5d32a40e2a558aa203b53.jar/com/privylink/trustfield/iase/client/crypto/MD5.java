// 
// Decompiled by Procyon v0.5.30
// 

package com.privylink.trustfield.iase.client.crypto;

public class MD5
{
    private int _$37803;
    private int _$37804;
    private int _$37805;
    private int _$37806;
    private int _$37807;
    private int _$37808;
    private int _$37809;
    private int _$37810;
    private int _$37811;
    private int _$37812;
    private int _$37813;
    private int _$37814;
    private int _$37815;
    private int _$37816;
    private int _$37817;
    private int _$37818;
    private int _$37819;
    private int _$37820;
    private int _$37821;
    private int _$37822;
    private int _$37823;
    private int _$21002;
    private int[] _$355;
    private int[] _$33604;
    private int[] _$37824;
    
    public MD5() {
        this._$355 = new int[2];
        this._$33604 = new int[4];
        this._$37824 = new int[16];
        this._$37803 = 1732584193;
        this._$37804 = -271733879;
        this._$37805 = -1732584194;
        this._$37806 = 271733878;
        this._$37807 = 7;
        this._$37808 = 12;
        this._$37809 = 17;
        this._$37810 = 22;
        this._$37811 = 5;
        this._$37812 = 9;
        this._$37813 = 14;
        this._$37814 = 20;
        this._$37815 = 4;
        this._$37816 = 11;
        this._$37817 = 16;
        this._$37818 = 23;
        this._$37819 = 6;
        this._$37820 = 10;
        this._$37821 = 15;
        this._$37822 = 21;
        this._$37823 = 128;
        this._$21002 = 0;
    }
    
    private int _$37825(final int x, final int n) {
        return x << n | x >>> 32 - n;
    }
    
    private int _$22(final int x, final int y, final int z) {
        return (x & y) | (~x & z);
    }
    
    private int _$37827(final int x, final int y, final int z) {
        return (x & z) | (y & ~z);
    }
    
    private int _$37828(final int x, final int y, final int z) {
        return x ^ y ^ z;
    }
    
    private int _$20(final int x, final int y, final int z) {
        return y ^ (x | ~z);
    }
    
    private void _$37829(final int[] a, final int b, final int c, final int d, final int x, final int s, final int ac) {
        final int n = 0;
        a[n] += this._$22(b, c, d) + x + ac;
        a[0] = this._$37825(a[0], s);
        final int n2 = 0;
        a[n2] += b;
    }
    
    private void _$37831(final int[] a, final int b, final int c, final int d, final int x, final int s, final int ac) {
        final int n = 0;
        a[n] += this._$37827(b, c, d) + x + ac;
        a[0] = this._$37825(a[0], s);
        final int n2 = 0;
        a[n2] += b;
    }
    
    private void _$37832(final int[] a, final int b, final int c, final int d, final int x, final int s, final int ac) {
        final int n = 0;
        a[n] += this._$37828(b, c, d) + x + ac;
        a[0] = this._$37825(a[0], s);
        final int n2 = 0;
        a[n2] += b;
    }
    
    private void _$37833(final int[] a, final int b, final int c, final int d, final int x, final int s, final int ac) {
        final int n = 0;
        a[n] += this._$20(b, c, d) + x + ac;
        a[0] = this._$37825(a[0], s);
        final int n2 = 0;
        a[n2] += b;
    }
    
    private void _$37834() {
        for (int i = 0; i < 16; ++i) {
            this._$37824[i] = 0;
        }
        this._$355[0] = (this._$355[1] = 0);
        this._$33604[0] = this._$37803;
        this._$33604[1] = this._$37804;
        this._$33604[2] = this._$37805;
        this._$33604[3] = this._$37806;
    }
    
    private void _$37835() {
        final int[] ar = { 0 };
        int a = this._$33604[0];
        int b = this._$33604[1];
        int c = this._$33604[2];
        int d = this._$33604[3];
        ar[0] = a;
        this._$37829(ar, b, c, d, this._$37824[0], this._$37807, -680876936);
        a = ar[0];
        ar[0] = d;
        this._$37829(ar, a, b, c, this._$37824[1], this._$37808, -389564586);
        d = ar[0];
        ar[0] = c;
        this._$37829(ar, d, a, b, this._$37824[2], this._$37809, 606105819);
        c = ar[0];
        ar[0] = b;
        this._$37829(ar, c, d, a, this._$37824[3], this._$37810, -1044525330);
        b = ar[0];
        ar[0] = a;
        this._$37829(ar, b, c, d, this._$37824[4], this._$37807, -176418897);
        a = ar[0];
        ar[0] = d;
        this._$37829(ar, a, b, c, this._$37824[5], this._$37808, 1200080426);
        d = ar[0];
        ar[0] = c;
        this._$37829(ar, d, a, b, this._$37824[6], this._$37809, -1473231341);
        c = ar[0];
        ar[0] = b;
        this._$37829(ar, c, d, a, this._$37824[7], this._$37810, -45705983);
        b = ar[0];
        ar[0] = a;
        this._$37829(ar, b, c, d, this._$37824[8], this._$37807, 1770035416);
        a = ar[0];
        ar[0] = d;
        this._$37829(ar, a, b, c, this._$37824[9], this._$37808, -1958414417);
        d = ar[0];
        ar[0] = c;
        this._$37829(ar, d, a, b, this._$37824[10], this._$37809, -42063);
        c = ar[0];
        ar[0] = b;
        this._$37829(ar, c, d, a, this._$37824[11], this._$37810, -1990404162);
        b = ar[0];
        ar[0] = a;
        this._$37829(ar, b, c, d, this._$37824[12], this._$37807, 1804603682);
        a = ar[0];
        ar[0] = d;
        this._$37829(ar, a, b, c, this._$37824[13], this._$37808, -40341101);
        d = ar[0];
        ar[0] = c;
        this._$37829(ar, d, a, b, this._$37824[14], this._$37809, -1502002290);
        c = ar[0];
        ar[0] = b;
        this._$37829(ar, c, d, a, this._$37824[15], this._$37810, 1236535329);
        b = ar[0];
        ar[0] = a;
        this._$37831(ar, b, c, d, this._$37824[1], this._$37811, -165796510);
        a = ar[0];
        ar[0] = d;
        this._$37831(ar, a, b, c, this._$37824[6], this._$37812, -1069501632);
        d = ar[0];
        ar[0] = c;
        this._$37831(ar, d, a, b, this._$37824[11], this._$37813, 643717713);
        c = ar[0];
        ar[0] = b;
        this._$37831(ar, c, d, a, this._$37824[0], this._$37814, -373897302);
        b = ar[0];
        ar[0] = a;
        this._$37831(ar, b, c, d, this._$37824[5], this._$37811, -701558691);
        a = ar[0];
        ar[0] = d;
        this._$37831(ar, a, b, c, this._$37824[10], this._$37812, 38016083);
        d = ar[0];
        ar[0] = c;
        this._$37831(ar, d, a, b, this._$37824[15], this._$37813, -660478335);
        c = ar[0];
        ar[0] = b;
        this._$37831(ar, c, d, a, this._$37824[4], this._$37814, -405537848);
        b = ar[0];
        ar[0] = a;
        this._$37831(ar, b, c, d, this._$37824[9], this._$37811, 568446438);
        a = ar[0];
        ar[0] = d;
        this._$37831(ar, a, b, c, this._$37824[14], this._$37812, -1019803690);
        d = ar[0];
        ar[0] = c;
        this._$37831(ar, d, a, b, this._$37824[3], this._$37813, -187363961);
        c = ar[0];
        ar[0] = b;
        this._$37831(ar, c, d, a, this._$37824[8], this._$37814, 1163531501);
        b = ar[0];
        ar[0] = a;
        this._$37831(ar, b, c, d, this._$37824[13], this._$37811, -1444681467);
        a = ar[0];
        ar[0] = d;
        this._$37831(ar, a, b, c, this._$37824[2], this._$37812, -51403784);
        d = ar[0];
        ar[0] = c;
        this._$37831(ar, d, a, b, this._$37824[7], this._$37813, 1735328473);
        c = ar[0];
        ar[0] = b;
        this._$37831(ar, c, d, a, this._$37824[12], this._$37814, -1926607734);
        b = ar[0];
        ar[0] = a;
        this._$37832(ar, b, c, d, this._$37824[5], this._$37815, -378558);
        a = ar[0];
        ar[0] = d;
        this._$37832(ar, a, b, c, this._$37824[8], this._$37816, -2022574463);
        d = ar[0];
        ar[0] = c;
        this._$37832(ar, d, a, b, this._$37824[11], this._$37817, 1839030562);
        c = ar[0];
        ar[0] = b;
        this._$37832(ar, c, d, a, this._$37824[14], this._$37818, -35309556);
        b = ar[0];
        ar[0] = a;
        this._$37832(ar, b, c, d, this._$37824[1], this._$37815, -1530992060);
        a = ar[0];
        ar[0] = d;
        this._$37832(ar, a, b, c, this._$37824[4], this._$37816, 1272893353);
        d = ar[0];
        ar[0] = c;
        this._$37832(ar, d, a, b, this._$37824[7], this._$37817, -155497632);
        c = ar[0];
        ar[0] = b;
        this._$37832(ar, c, d, a, this._$37824[10], this._$37818, -1094730640);
        b = ar[0];
        ar[0] = a;
        this._$37832(ar, b, c, d, this._$37824[13], this._$37815, 681279174);
        a = ar[0];
        ar[0] = d;
        this._$37832(ar, a, b, c, this._$37824[0], this._$37816, -358537222);
        d = ar[0];
        ar[0] = c;
        this._$37832(ar, d, a, b, this._$37824[3], this._$37817, -722521979);
        c = ar[0];
        ar[0] = b;
        this._$37832(ar, c, d, a, this._$37824[6], this._$37818, 76029189);
        b = ar[0];
        ar[0] = a;
        this._$37832(ar, b, c, d, this._$37824[9], this._$37815, -640364487);
        a = ar[0];
        ar[0] = d;
        this._$37832(ar, a, b, c, this._$37824[12], this._$37816, -421815835);
        d = ar[0];
        ar[0] = c;
        this._$37832(ar, d, a, b, this._$37824[15], this._$37817, 530742520);
        c = ar[0];
        ar[0] = b;
        this._$37832(ar, c, d, a, this._$37824[2], this._$37818, -995338651);
        b = ar[0];
        ar[0] = a;
        this._$37833(ar, b, c, d, this._$37824[0], this._$37819, -198630844);
        a = ar[0];
        ar[0] = d;
        this._$37833(ar, a, b, c, this._$37824[7], this._$37820, 1126891415);
        d = ar[0];
        ar[0] = c;
        this._$37833(ar, d, a, b, this._$37824[14], this._$37821, -1416354905);
        c = ar[0];
        ar[0] = b;
        this._$37833(ar, c, d, a, this._$37824[5], this._$37822, -57434055);
        b = ar[0];
        ar[0] = a;
        this._$37833(ar, b, c, d, this._$37824[12], this._$37819, 1700485571);
        a = ar[0];
        ar[0] = d;
        this._$37833(ar, a, b, c, this._$37824[3], this._$37820, -1894986606);
        d = ar[0];
        ar[0] = c;
        this._$37833(ar, d, a, b, this._$37824[10], this._$37821, -1051523);
        c = ar[0];
        ar[0] = b;
        this._$37833(ar, c, d, a, this._$37824[1], this._$37822, -2054922799);
        b = ar[0];
        ar[0] = a;
        this._$37833(ar, b, c, d, this._$37824[8], this._$37819, 1873313359);
        a = ar[0];
        ar[0] = d;
        this._$37833(ar, a, b, c, this._$37824[15], this._$37820, -30611744);
        d = ar[0];
        ar[0] = c;
        this._$37833(ar, d, a, b, this._$37824[6], this._$37821, -1560198380);
        c = ar[0];
        ar[0] = b;
        this._$37833(ar, c, d, a, this._$37824[13], this._$37822, 1309151649);
        b = ar[0];
        ar[0] = a;
        this._$37833(ar, b, c, d, this._$37824[4], this._$37819, -145523070);
        a = ar[0];
        ar[0] = d;
        this._$37833(ar, a, b, c, this._$37824[11], this._$37820, -1120210379);
        d = ar[0];
        ar[0] = c;
        this._$37833(ar, d, a, b, this._$37824[2], this._$37821, 718787259);
        c = ar[0];
        ar[0] = b;
        this._$37833(ar, c, d, a, this._$37824[9], this._$37822, -343485551);
        b = ar[0];
        final int[] $33604 = this._$33604;
        final int n = 0;
        $33604[n] += a;
        final int[] $33605 = this._$33604;
        final int n2 = 1;
        $33605[n2] += b;
        final int[] $33606 = this._$33604;
        final int n3 = 2;
        $33606[n3] += c;
        final int[] $33607 = this._$33604;
        final int n4 = 3;
        $33607[n4] += d;
        for (int i = 0; i < 16; ++i) {
            this._$37824[i] = 0;
        }
    }
    
    private void _$37837(final int b) {
        final int cnt = this._$355[0] / 32 % 16;
        final int[] $37824 = this._$37824;
        final int n = cnt;
        $37824[n] |= b << this._$355[0] % 32;
        final int[] $37825 = this._$355;
        final int n2 = 0;
        $37825[n2] += 8;
        if (this._$355[0] == 0) {
            final int[] $37826 = this._$355;
            final int n3 = 1;
            ++$37826[n3];
            this._$355[0] = 0;
        }
        if (this._$355[0] % 512 == 0) {
            this._$37835();
        }
    }
    
    private void _$37839(final byte[] hash) {
        final int len0 = this._$355[0];
        final int len2 = this._$355[1];
        this._$37837(this._$37823);
        while (this._$355[0] % 512 != 448) {
            this._$37837(this._$21002);
        }
        this._$37824[15] = len2;
        this._$37824[14] = len0;
        this._$37835();
        for (int i = 0; i < 16; ++i) {
            hash[i] = (byte)(this._$33604[i / 4] >>> 8 * (i % 4) & 0xFF);
        }
        this._$37834();
    }
    
    public void md5_algo(final int len, final byte[] in, final byte[] out) {
        final byte[] localh = new byte[16];
        this._$37834();
        for (int j = 0; j < len; ++j) {
            this._$37837(in[j] & 0xFF);
        }
        this._$37839(localh);
        for (int j = 0; j < 16; ++j) {
            out[j] = localh[j];
        }
    }
}
