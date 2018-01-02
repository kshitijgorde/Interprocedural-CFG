// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.http;

import org.xmodel.log.Log;

public class MD5
{
    static final Log log;
    static String hex_chr;
    
    static {
        log = Log.getLog(MD5.class);
        MD5.hex_chr = "0123456789abcdef";
    }
    
    public static String rhex(final int num) {
        String str = "";
        for (int j = 0; j <= 3; ++j) {
            str = String.valueOf(str) + MD5.hex_chr.charAt(num >> j * 8 + 4 & 0xF) + MD5.hex_chr.charAt(num >> j * 8 & 0xF);
            MD5.log.debug(MD5.hex_chr.charAt(num >> j * 8 + 4 & 0xF));
            MD5.log.debug(MD5.hex_chr.charAt(num >> j * 8 & 0xF));
            MD5.log.debug("num = " + num + " str = " + str);
        }
        return str;
    }
    
    public static int[] str2blks_MD5(final String str) {
        int i = 0;
        final int nblk = (str.length() + 8 >> 6) + 1;
        final int[] blks = new int[nblk * 16];
        for (i = 0; i < nblk * 16; ++i) {
            blks[i] = 0;
        }
        for (i = 0; i < str.length(); ++i) {
            final int[] array = blks;
            final int n = i >> 2;
            array[n] |= str.charAt(i) << i % 4 * 8;
        }
        final int[] array2 = blks;
        final int n2 = i >> 2;
        array2[n2] |= 128 << i % 4 * 8;
        blks[nblk * 16 - 2] = str.length() * 8;
        return blks;
    }
    
    public static int[] bytes2blks_MD5(final int[] bytes) {
        int i = 0;
        final int nblk = (bytes.length + 8 >> 6) + 1;
        final int[] blks = new int[nblk * 16];
        for (i = 0; i < nblk * 16; ++i) {
            blks[i] = 0;
        }
        for (i = 0; i < bytes.length; ++i) {
            final int[] array = blks;
            final int n = i >> 2;
            array[n] |= bytes[i] << i % 4 * 8;
        }
        final int[] array2 = blks;
        final int n2 = i >> 2;
        array2[n2] |= 128 << i % 4 * 8;
        blks[nblk * 16 - 2] = bytes.length * 8;
        return blks;
    }
    
    public static int add(final int x, final int y) {
        final int lsw = (x & 0xFFFF) + (y & 0xFFFF);
        final int msw = (x >> 16) + (y >> 16) + (lsw >> 16);
        return msw << 16 | (lsw & 0xFFFF);
    }
    
    public static int rol(final int num, final int cnt) {
        return num << cnt | num >>> 32 - cnt;
    }
    
    public static int cmn(final int q, final int a, final int b, final int x, final int s, final int t) {
        return add(rol(add(add(a, q), add(x, t)), s), b);
    }
    
    public static int ff(final int a, final int b, final int c, final int d, final int x, final int s, final int t) {
        return cmn((b & c) | (~b & d), a, b, x, s, t);
    }
    
    public static int gg(final int a, final int b, final int c, final int d, final int x, final int s, final int t) {
        return cmn((b & d) | (c & ~d), a, b, x, s, t);
    }
    
    public static int hh(final int a, final int b, final int c, final int d, final int x, final int s, final int t) {
        return cmn(b ^ c ^ d, a, b, x, s, t);
    }
    
    public static int ii(final int a, final int b, final int c, final int d, final int x, final int s, final int t) {
        return cmn(c ^ (b | ~d), a, b, x, s, t);
    }
    
    public static String calcMD5(final String str) {
        return doCalcMD5(str2blks_MD5(str));
    }
    
    public static String calcMD5_2(final int[] str) {
        return doCalcMD5(bytes2blks_MD5(str));
    }
    
    public static String doCalcMD5(final int[] x) {
        MD5.log.debug("print x[]");
        for (final int b : x) {
            MD5.log.debug(b);
        }
        int a = 1732584193;
        int b2 = -271733879;
        int c = -1732584194;
        int d = 271733878;
        for (int i = 0; i < x.length; i += 16) {
            final int olda = a;
            final int oldb = b2;
            final int oldc = c;
            final int oldd = d;
            a = ff(a, b2, c, d, x[i + 0], 7, -680876936);
            d = ff(d, a, b2, c, x[i + 1], 12, -389564586);
            c = ff(c, d, a, b2, x[i + 2], 17, 606105819);
            b2 = ff(b2, c, d, a, x[i + 3], 22, -1044525330);
            a = ff(a, b2, c, d, x[i + 4], 7, -176418897);
            d = ff(d, a, b2, c, x[i + 5], 12, 1200080426);
            c = ff(c, d, a, b2, x[i + 6], 17, -1473231341);
            b2 = ff(b2, c, d, a, x[i + 7], 22, -45705983);
            a = ff(a, b2, c, d, x[i + 8], 7, 1770035416);
            d = ff(d, a, b2, c, x[i + 9], 12, -1958414417);
            c = ff(c, d, a, b2, x[i + 10], 17, -42063);
            b2 = ff(b2, c, d, a, x[i + 11], 22, -1990404162);
            a = ff(a, b2, c, d, x[i + 12], 7, 1804603682);
            d = ff(d, a, b2, c, x[i + 13], 12, -40341101);
            c = ff(c, d, a, b2, x[i + 14], 17, -1502002290);
            b2 = ff(b2, c, d, a, x[i + 15], 22, 1236535329);
            a = gg(a, b2, c, d, x[i + 1], 5, -165796510);
            d = gg(d, a, b2, c, x[i + 6], 9, -1069501632);
            c = gg(c, d, a, b2, x[i + 11], 14, 643717713);
            b2 = gg(b2, c, d, a, x[i + 0], 20, -373897302);
            a = gg(a, b2, c, d, x[i + 5], 5, -701558691);
            d = gg(d, a, b2, c, x[i + 10], 9, 38016083);
            c = gg(c, d, a, b2, x[i + 15], 14, -660478335);
            b2 = gg(b2, c, d, a, x[i + 4], 20, -405537848);
            a = gg(a, b2, c, d, x[i + 9], 5, 568446438);
            d = gg(d, a, b2, c, x[i + 14], 9, -1019803690);
            c = gg(c, d, a, b2, x[i + 3], 14, -187363961);
            b2 = gg(b2, c, d, a, x[i + 8], 20, 1163531501);
            a = gg(a, b2, c, d, x[i + 13], 5, -1444681467);
            d = gg(d, a, b2, c, x[i + 2], 9, -51403784);
            c = gg(c, d, a, b2, x[i + 7], 14, 1735328473);
            b2 = gg(b2, c, d, a, x[i + 12], 20, -1926607734);
            a = hh(a, b2, c, d, x[i + 5], 4, -378558);
            d = hh(d, a, b2, c, x[i + 8], 11, -2022574463);
            c = hh(c, d, a, b2, x[i + 11], 16, 1839030562);
            b2 = hh(b2, c, d, a, x[i + 14], 23, -35309556);
            a = hh(a, b2, c, d, x[i + 1], 4, -1530992060);
            d = hh(d, a, b2, c, x[i + 4], 11, 1272893353);
            c = hh(c, d, a, b2, x[i + 7], 16, -155497632);
            b2 = hh(b2, c, d, a, x[i + 10], 23, -1094730640);
            a = hh(a, b2, c, d, x[i + 13], 4, 681279174);
            d = hh(d, a, b2, c, x[i + 0], 11, -358537222);
            c = hh(c, d, a, b2, x[i + 3], 16, -722521979);
            b2 = hh(b2, c, d, a, x[i + 6], 23, 76029189);
            a = hh(a, b2, c, d, x[i + 9], 4, -640364487);
            d = hh(d, a, b2, c, x[i + 12], 11, -421815835);
            c = hh(c, d, a, b2, x[i + 15], 16, 530742520);
            b2 = hh(b2, c, d, a, x[i + 2], 23, -995338651);
            a = ii(a, b2, c, d, x[i + 0], 6, -198630844);
            d = ii(d, a, b2, c, x[i + 7], 10, 1126891415);
            c = ii(c, d, a, b2, x[i + 14], 15, -1416354905);
            b2 = ii(b2, c, d, a, x[i + 5], 21, -57434055);
            a = ii(a, b2, c, d, x[i + 12], 6, 1700485571);
            d = ii(d, a, b2, c, x[i + 3], 10, -1894986606);
            c = ii(c, d, a, b2, x[i + 10], 15, -1051523);
            b2 = ii(b2, c, d, a, x[i + 1], 21, -2054922799);
            a = ii(a, b2, c, d, x[i + 8], 6, 1873313359);
            d = ii(d, a, b2, c, x[i + 15], 10, -30611744);
            c = ii(c, d, a, b2, x[i + 6], 15, -1560198380);
            b2 = ii(b2, c, d, a, x[i + 13], 21, 1309151649);
            a = ii(a, b2, c, d, x[i + 4], 6, -145523070);
            d = ii(d, a, b2, c, x[i + 11], 10, -1120210379);
            c = ii(c, d, a, b2, x[i + 2], 15, 718787259);
            b2 = ii(b2, c, d, a, x[i + 9], 21, -343485551);
            a = add(a, olda);
            b2 = add(b2, oldb);
            c = add(c, oldc);
            d = add(d, oldd);
        }
        MD5.log.debug("a=" + a);
        MD5.log.debug("b=" + b2);
        MD5.log.debug("c=" + c);
        MD5.log.debug("d=" + d);
        return String.valueOf(rhex(a)) + rhex(b2) + rhex(c) + rhex(d);
    }
}
