// 
// Decompiled by Procyon v0.5.30
// 

final class r_Sub1 extends r
{
    int anInt6320;
    static Class79 aClass79_6321;
    static long aLong6322;
    int anInt6323;
    int anInt6324;
    byte[] aByteArray6325;
    int anInt6326;
    
    static final boolean method1642(final byte b) {
        try {
            if (Class98_Sub46_Sub19.aClass98_Sub46_Sub8_6066 == null) {
                return false;
            }
            if (Class98_Sub46_Sub19.aClass98_Sub46_Sub8_6066.anInt5990 >= 2000) {
                final Class98_Sub46_Sub8 aClass98_Sub46_Sub8_6066 = Class98_Sub46_Sub19.aClass98_Sub46_Sub8_6066;
                aClass98_Sub46_Sub8_6066.anInt5990 -= 2000;
            }
            if (~Class98_Sub46_Sub19.aClass98_Sub46_Sub8_6066.anInt5990 == 0xFFFFFC11) {
                return true;
            }
            if (b <= 121) {
                r_Sub1.aClass79_6321 = null;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "en.E(" + b + ')');
        }
    }
    
    final void method1643(final int anInt6324, final int n, final int n2, final int anInt6325, final int n3) {
        try {
            this.anInt6326 = -anInt6325 + n;
            this.anInt6320 = anInt6325;
            if (n3 == -1) {
                this.anInt6323 = -anInt6324 + n2;
                this.anInt6324 = anInt6324;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "en.G(" + anInt6324 + ',' + n + ',' + n2 + ',' + anInt6325 + ',' + n3 + ')');
        }
    }
    
    public static void method1644(final int n) {
        try {
            r_Sub1.aClass79_6321 = null;
            if (n != 9949) {
                r_Sub1.aLong6322 = -46L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "en.C(" + n + ')');
        }
    }
    
    static final void method1645(final int n) {
        try {
            OutputStream_Sub2.anIntArray42 = null;
            Class145.anIntArray1175 = null;
            Class98_Sub9.aBoolean3851 = false;
            Class284_Sub1.anIntArray5178 = null;
            Class138.anIntArray1083 = null;
            Class294.anIntArray2406 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "en.B(" + n + ')');
        }
    }
    
    final void method1646(final int n) {
        try {
            int i;
            for (i = -1; i < this.aByteArray6325.length - 8; this.aByteArray6325[++i] = 0, this.aByteArray6325[++i] = 0, this.aByteArray6325[++i] = 0, this.aByteArray6325[++i] = 0, this.aByteArray6325[++i] = 0, this.aByteArray6325[++i] = 0, this.aByteArray6325[++i] = 0, this.aByteArray6325[++i] = 0) {}
            while (~(this.aByteArray6325.length - 1) < ~i) {
                this.aByteArray6325[++i] = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "en.D(" + n + ')');
        }
    }
    
    final boolean method1647(final int n, final byte b, final int n2) {
        try {
            return b > -114 || ~this.aByteArray6325.length <= ~(n * n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "en.F(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    r_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2) {
        try {
            this.aByteArray6325 = new byte[n2 * n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "en.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method1648(int n, int n2, int n3, int n4, int n5, final int n6, int n7) {
        try {
            int n8 = 0;
            if (~n5 != ~n) {
                n8 = (n2 + -n3 << 308809648) / (-n + n5);
            }
            int n9 = 0;
            if (n4 != n5) {
                n9 = (-n2 + n7 << -1744910128) / (-n5 + n4);
            }
            int n10 = 0;
            if (~n4 != ~n) {
                n10 = (n3 + -n7 << 478406896) / (-n4 + n);
            }
            if (~n >= ~n5 && n <= n4) {
                if (n4 > n5) {
                    n3 = (n7 = n3 << 1202729712);
                    if (n < 0) {
                        n3 -= n8 * n;
                        n7 -= n * n10;
                        n = 0;
                    }
                    n2 <<= 132460240;
                    if (~n5 > -1) {
                        n2 -= n9 * n5;
                        n5 = 0;
                    }
                    if ((~n != ~n5 && n8 > n10) || (~n == ~n5 && ~n10 < ~n9)) {
                        n4 -= n5;
                        n5 -= n;
                        n *= this.anInt6323;
                        while (--n5 >= 0) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n3 >> 2088693072, n, n7 >> -1493619120);
                            n += this.anInt6323;
                            n7 += n10;
                            n3 += n8;
                        }
                        while (~(--n4) <= -1) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n2 >> 1016237104, n, n7 >> 43486800);
                            n7 += n10;
                            n2 += n9;
                            n += this.anInt6323;
                        }
                    }
                    else {
                        n4 -= n5;
                        n5 -= n;
                        n *= this.anInt6323;
                        while (--n5 >= 0) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n7 >> -70451696, n, n3 >> -569292944);
                            n3 += n8;
                            n += this.anInt6323;
                            n7 += n10;
                        }
                        while (~(--n4) <= -1) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n7 >> 1090756528, n, n2 >> -376719216);
                            n2 += n9;
                            n7 += n10;
                            n += this.anInt6323;
                        }
                    }
                }
                else {
                    n3 = (n2 = n3 << -2138871568);
                    n7 <<= -1328825456;
                    if (~n > -1) {
                        n2 -= n10 * n;
                        n3 -= n8 * n;
                        n = 0;
                    }
                    if (n4 < 0) {
                        n7 -= n4 * n9;
                        n4 = 0;
                    }
                    if ((~n == ~n4 || n10 >= n8) && (~n4 != ~n || n8 >= n9)) {
                        n5 -= n4;
                        n4 -= n;
                        n *= this.anInt6323;
                        while (--n4 >= 0) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n2 >> 1658514640, n, n3 >> -493710704);
                            n2 += n10;
                            n += this.anInt6323;
                            n3 += n8;
                        }
                        while (--n5 >= 0) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n7 >> 875406544, n, n3 >> 968472208);
                            n7 += n9;
                            n3 += n8;
                            n += this.anInt6323;
                        }
                    }
                    else {
                        n5 -= n4;
                        n4 -= n;
                        n *= this.anInt6323;
                        while (~(--n4) <= -1) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n3 >> 1630435120, n, n2 >> 860712432);
                            n2 += n10;
                            n += this.anInt6323;
                            n3 += n8;
                        }
                        while (--n5 >= 0) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n3 >> 215894032, n, n7 >> 2015104304);
                            n3 += n8;
                            n7 += n9;
                            n += this.anInt6323;
                        }
                    }
                }
            }
            else if (n4 < n5) {
                if (n >= n5) {
                    n7 = (n3 = n7 << 374585904);
                    n2 <<= 1905005264;
                    if (~n4 > -1) {
                        n3 -= n9 * n4;
                        n7 -= n10 * n4;
                        n4 = 0;
                    }
                    if (~n5 > -1) {
                        n2 -= n5 * n8;
                        n5 = 0;
                    }
                    if (n9 >= n10) {
                        n -= n5;
                        n5 -= n4;
                        n4 *= this.anInt6323;
                        while (~(--n5) <= -1) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n3 >> -2063477360, n4, n7 >> 1547652080);
                            n7 += n10;
                            n3 += n9;
                            n4 += this.anInt6323;
                        }
                        while (~(--n) <= -1) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n2 >> -880271024, n4, n7 >> 1114847408);
                            n7 += n10;
                            n2 += n8;
                            n4 += this.anInt6323;
                        }
                    }
                    else {
                        n -= n5;
                        n5 -= n4;
                        n4 *= this.anInt6323;
                        while (--n5 >= 0) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n7 >> -1610162128, n4, n3 >> 844481776);
                            n7 += n10;
                            n4 += this.anInt6323;
                            n3 += n9;
                        }
                        while (~(--n) <= -1) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n7 >> -1338436880, n4, n2 >> -1716563952);
                            n7 += n10;
                            n4 += this.anInt6323;
                            n2 += n8;
                        }
                    }
                }
                else {
                    n7 = (n2 = n7 << 800608624);
                    if (~n4 > -1) {
                        n7 -= n10 * n4;
                        n2 -= n9 * n4;
                        n4 = 0;
                    }
                    n3 <<= 1504634480;
                    if (n < 0) {
                        n3 -= n8 * n;
                        n = 0;
                    }
                    if (~n10 < ~n9) {
                        n5 -= n;
                        n -= n4;
                        n4 *= this.anInt6323;
                        while (~(--n) <= -1) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n7 >> -1957735056, n4, n2 >> 1141100560);
                            n4 += this.anInt6323;
                            n2 += n9;
                            n7 += n10;
                        }
                        while (--n5 >= 0) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n3 >> 1458762608, n4, n2 >> -491951696);
                            n2 += n9;
                            n4 += this.anInt6323;
                            n3 += n8;
                        }
                    }
                    else {
                        n5 -= n;
                        n -= n4;
                        n4 *= this.anInt6323;
                        while (--n >= 0) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n2 >> -441923792, n4, n7 >> -1844532944);
                            n4 += this.anInt6323;
                            n7 += n10;
                            n2 += n9;
                        }
                        while (--n5 >= 0) {
                            Class261.method3210((byte)(-104), this.aByteArray6325, 0, n2 >> -1096286768, n4, n3 >> -2134156272);
                            n4 += this.anInt6323;
                            n2 += n9;
                            n3 += n8;
                        }
                    }
                }
            }
            else if (n > n4) {
                n2 = (n3 = n2 << 1231068944);
                n7 <<= 1066666256;
                if (n5 < 0) {
                    n2 -= n9 * n5;
                    n3 -= n5 * n8;
                    n5 = 0;
                }
                if (n4 < 0) {
                    n7 -= n4 * n10;
                    n4 = 0;
                }
                if ((n4 != n5 && n9 > n8) || (n4 == n5 && n10 < n8)) {
                    n -= n4;
                    n4 -= n5;
                    n5 *= this.anInt6323;
                    while (~(--n4) <= -1) {
                        Class261.method3210((byte)(-104), this.aByteArray6325, 0, n2 >> 704910512, n5, n3 >> 114452112);
                        n3 += n8;
                        n5 += this.anInt6323;
                        n2 += n9;
                    }
                    while (~(--n) <= -1) {
                        Class261.method3210((byte)(-104), this.aByteArray6325, 0, n7 >> 876486832, n5, n3 >> 928878736);
                        n3 += n8;
                        n7 += n10;
                        n5 += this.anInt6323;
                    }
                }
                else {
                    n -= n4;
                    n4 -= n5;
                    n5 *= this.anInt6323;
                    while (--n4 >= 0) {
                        Class261.method3210((byte)(-104), this.aByteArray6325, 0, n3 >> -1290116336, n5, n2 >> -1781790608);
                        n5 += this.anInt6323;
                        n2 += n9;
                        n3 += n8;
                    }
                    while (--n >= 0) {
                        Class261.method3210((byte)(-104), this.aByteArray6325, 0, n3 >> 192479824, n5, n7 >> -1290160400);
                        n5 += this.anInt6323;
                        n3 += n8;
                        n7 += n10;
                    }
                }
            }
            else {
                n2 = (n7 = n2 << -1766173488);
                if (n5 < 0) {
                    n2 -= n5 * n9;
                    n7 -= n5 * n8;
                    n5 = 0;
                }
                n3 <<= -1121160880;
                if (~n > -1) {
                    n3 -= n * n10;
                    n = 0;
                }
                if (n8 >= n9) {
                    n4 -= n;
                    n -= n5;
                    n5 *= this.anInt6323;
                    while (--n >= 0) {
                        Class261.method3210((byte)(-104), this.aByteArray6325, 0, n7 >> 1933867888, n5, n2 >> -281985552);
                        n2 += n9;
                        n5 += this.anInt6323;
                        n7 += n8;
                    }
                    while (--n4 >= 0) {
                        Class261.method3210((byte)(-104), this.aByteArray6325, 0, n3 >> -1913628528, n5, n2 >> -825231248);
                        n5 += this.anInt6323;
                        n3 += n10;
                        n2 += n9;
                    }
                }
                else {
                    n4 -= n;
                    n -= n5;
                    n5 *= this.anInt6323;
                    while (--n >= 0) {
                        Class261.method3210((byte)(-104), this.aByteArray6325, 0, n2 >> 1066305936, n5, n7 >> -378739344);
                        n2 += n9;
                        n5 += this.anInt6323;
                        n7 += n8;
                    }
                    while (~(--n4) <= -1) {
                        Class261.method3210((byte)(-104), this.aByteArray6325, 0, n2 >> -448370448, n5, n3 >> 842419952);
                        n2 += n9;
                        n5 += this.anInt6323;
                        n3 += n10;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "en.M(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    static {
        r_Sub1.aLong6322 = -1L;
        r_Sub1.aClass79_6321 = new Class79(10);
    }
}
