// 
// Decompiled by Procyon v0.5.30
// 

final class r_Sub2 extends r
{
    int anInt6327;
    int anInt6328;
    int anInt6329;
    static OutgoingOpcode aClass171_6330;
    int anInt6331;
    byte[] aByteArray6332;
    static int anInt6333;
    static Class38 aClass38_6334;
    
    public static void method1649(final boolean b) {
        try {
            r_Sub2.aClass38_6334 = null;
            r_Sub2.aClass171_6330 = null;
            if (!b) {
                method1655(89, (byte)(-40), 85);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gl.M(" + b + ')');
        }
    }
    
    static final int method1650(final String s, final byte b) {
        try {
            if (b <= 58) {
                r_Sub2.aClass38_6334 = null;
            }
            return s.length() + 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gl.G(" + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method1651(final int n, final int anInt6327, final int n2, final int anInt6328, final int n3) {
        try {
            this.anInt6328 = n3 + -anInt6328;
            this.anInt6331 = -anInt6327 + n;
            this.anInt6327 = anInt6327;
            if (n2 == 0) {
                this.anInt6329 = anInt6328;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gl.D(" + n + ',' + anInt6327 + ',' + n2 + ',' + anInt6328 + ',' + n3 + ')');
        }
    }
    
    final boolean method1652(final int n, final int n2, final int n3) {
        try {
            if (n3 != 22657) {
                this.method1654(-99);
            }
            return ~this.aByteArray6332.length <= ~(n2 * n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gl.N(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method1653(int n, int n2, int n3, int n4, int n5, final byte b, int n6) {
        try {
            if (b == -69) {
                int n7 = 0;
                if (n6 != n) {
                    n7 = (-n2 + n3 << -443628592) / (-n + n6);
                }
                int n8 = 0;
                if (n6 != n5) {
                    n8 = (-n3 + n4 << 785005552) / (-n6 + n5);
                }
                int n9 = 0;
                if (~n != ~n5) {
                    n9 = (n2 + -n4 << -747179600) / (-n5 + n);
                }
                if (~n6 > ~n || n > n5) {
                    if (~n5 <= ~n6) {
                        if (~n >= ~n5) {
                            n3 = (n4 = n3 << -1005720400);
                            n2 <<= 1054242160;
                            if (n6 < 0) {
                                n3 -= n8 * n6;
                                n4 -= n6 * n7;
                                n6 = 0;
                            }
                            if (~n > -1) {
                                n2 -= n9 * n;
                                n = 0;
                            }
                            if (n7 < n8) {
                                n5 -= n;
                                n -= n6;
                                n6 *= this.anInt6328;
                                while (~(--n) <= -1) {
                                    Class246_Sub4_Sub2.method3108((byte)1, n6, n4 >> 1059858032, n3 >> 2069463248, 0, this.aByteArray6332);
                                    n6 += this.anInt6328;
                                    n3 += n8;
                                    n4 += n7;
                                }
                                while (~(--n5) <= -1) {
                                    Class246_Sub4_Sub2.method3108((byte)1, n6, n2 >> 120550704, n3 >> 1465970480, 0, this.aByteArray6332);
                                    n6 += this.anInt6328;
                                    n2 += n9;
                                    n3 += n8;
                                }
                            }
                            else {
                                n5 -= n;
                                n -= n6;
                                n6 *= this.anInt6328;
                                while (~(--n) <= -1) {
                                    Class246_Sub4_Sub2.method3108((byte)1, n6, n3 >> -670753872, n4 >> -481747760, 0, this.aByteArray6332);
                                    n3 += n8;
                                    n6 += this.anInt6328;
                                    n4 += n7;
                                }
                                while (~(--n5) <= -1) {
                                    Class246_Sub4_Sub2.method3108((byte)1, n6, n3 >> -2068840848, n2 >> 2101115120, 0, this.aByteArray6332);
                                    n6 += this.anInt6328;
                                    n2 += n9;
                                    n3 += n8;
                                }
                            }
                        }
                        else {
                            n3 = (n2 = n3 << 82412240);
                            n4 <<= -2011332048;
                            if (n6 < 0) {
                                n3 -= n6 * n8;
                                n2 -= n6 * n7;
                                n6 = 0;
                            }
                            if (~n5 > -1) {
                                n4 -= n9 * n5;
                                n5 = 0;
                            }
                            if ((n5 != n6 && ~n7 > ~n8) || (n5 == n6 && ~n9 > ~n7)) {
                                n -= n5;
                                n5 -= n6;
                                n6 *= this.anInt6328;
                                while (--n5 >= 0) {
                                    Class246_Sub4_Sub2.method3108((byte)1, n6, n2 >> 1993822576, n3 >> -51441968, 0, this.aByteArray6332);
                                    n3 += n8;
                                    n6 += this.anInt6328;
                                    n2 += n7;
                                }
                                while (~(--n) <= -1) {
                                    Class246_Sub4_Sub2.method3108((byte)1, n6, n2 >> -1614605648, n4 >> -1609116880, 0, this.aByteArray6332);
                                    n2 += n7;
                                    n4 += n9;
                                    n6 += this.anInt6328;
                                }
                            }
                            else {
                                n -= n5;
                                n5 -= n6;
                                n6 *= this.anInt6328;
                                while (--n5 >= 0) {
                                    Class246_Sub4_Sub2.method3108((byte)1, n6, n3 >> -24007280, n2 >> 797325680, 0, this.aByteArray6332);
                                    n2 += n7;
                                    n6 += this.anInt6328;
                                    n3 += n8;
                                }
                                while (--n >= 0) {
                                    Class246_Sub4_Sub2.method3108((byte)1, n6, n4 >> 1015000624, n2 >> 1871830288, 0, this.aByteArray6332);
                                    n2 += n7;
                                    n6 += this.anInt6328;
                                    n4 += n9;
                                }
                            }
                        }
                    }
                    else if (~n <= ~n6) {
                        n4 = (n2 = n4 << 1675497904);
                        n3 <<= -180783632;
                        if (n5 < 0) {
                            n2 -= n8 * n5;
                            n4 -= n5 * n9;
                            n5 = 0;
                        }
                        if (~n6 > -1) {
                            n3 -= n6 * n7;
                            n6 = 0;
                        }
                        if (n8 >= n9) {
                            n -= n6;
                            n6 -= n5;
                            n5 *= this.anInt6328;
                            while (--n6 >= 0) {
                                Class246_Sub4_Sub2.method3108((byte)1, n5, n4 >> 904296848, n2 >> 871105648, 0, this.aByteArray6332);
                                n5 += this.anInt6328;
                                n2 += n8;
                                n4 += n9;
                            }
                            while (--n >= 0) {
                                Class246_Sub4_Sub2.method3108((byte)1, n5, n4 >> 1374751856, n3 >> -493069808, 0, this.aByteArray6332);
                                n5 += this.anInt6328;
                                n3 += n7;
                                n4 += n9;
                            }
                        }
                        else {
                            n -= n6;
                            n6 -= n5;
                            n5 *= this.anInt6328;
                            while (~(--n6) <= -1) {
                                Class246_Sub4_Sub2.method3108((byte)1, n5, n2 >> 588920496, n4 >> 1994599568, 0, this.aByteArray6332);
                                n4 += n9;
                                n2 += n8;
                                n5 += this.anInt6328;
                            }
                            while (~(--n) <= -1) {
                                Class246_Sub4_Sub2.method3108((byte)1, n5, n3 >> 454894448, n4 >> 317092656, 0, this.aByteArray6332);
                                n4 += n9;
                                n3 += n7;
                                n5 += this.anInt6328;
                            }
                        }
                    }
                    else {
                        n4 = (n3 = n4 << 1122623632);
                        n2 <<= -88820752;
                        if (n5 < 0) {
                            n3 -= n8 * n5;
                            n4 -= n9 * n5;
                            n5 = 0;
                        }
                        if (~n > -1) {
                            n2 -= n * n7;
                            n = 0;
                        }
                        if (~n9 >= ~n8) {
                            n6 -= n;
                            n -= n5;
                            n5 *= this.anInt6328;
                            while (~(--n) <= -1) {
                                Class246_Sub4_Sub2.method3108((byte)1, n5, n4 >> -1048394864, n3 >> -429300688, 0, this.aByteArray6332);
                                n5 += this.anInt6328;
                                n4 += n9;
                                n3 += n8;
                            }
                            while (--n6 >= 0) {
                                Class246_Sub4_Sub2.method3108((byte)1, n5, n2 >> -2103570032, n3 >> 1396136368, 0, this.aByteArray6332);
                                n5 += this.anInt6328;
                                n3 += n8;
                                n2 += n7;
                            }
                        }
                        else {
                            n6 -= n;
                            n -= n5;
                            n5 *= this.anInt6328;
                            while (~(--n) <= -1) {
                                Class246_Sub4_Sub2.method3108((byte)1, n5, n3 >> -322633904, n4 >> 955734320, 0, this.aByteArray6332);
                                n3 += n8;
                                n4 += n9;
                                n5 += this.anInt6328;
                            }
                            while (~(--n6) <= -1) {
                                Class246_Sub4_Sub2.method3108((byte)1, n5, n3 >> 504672272, n2 >> 46642864, 0, this.aByteArray6332);
                                n5 += this.anInt6328;
                                n3 += n8;
                                n2 += n7;
                            }
                        }
                    }
                }
                else if (n5 <= n6) {
                    n2 = (n3 = n2 << 825934864);
                    n4 <<= 502840432;
                    if (~n > -1) {
                        n2 -= n7 * n;
                        n3 -= n * n9;
                        n = 0;
                    }
                    if (n5 < 0) {
                        n4 -= n5 * n8;
                        n5 = 0;
                    }
                    if ((~n != ~n5 && n9 < n7) || (~n == ~n5 && ~n8 < ~n7)) {
                        n6 -= n5;
                        n5 -= n;
                        n *= this.anInt6328;
                        while (~(--n5) <= -1) {
                            Class246_Sub4_Sub2.method3108((byte)1, n, n3 >> -2091813776, n2 >> 146157264, 0, this.aByteArray6332);
                            n3 += n9;
                            n2 += n7;
                            n += this.anInt6328;
                        }
                        while (--n6 >= 0) {
                            Class246_Sub4_Sub2.method3108((byte)1, n, n4 >> 1605633872, n2 >> 118924656, 0, this.aByteArray6332);
                            n += this.anInt6328;
                            n2 += n7;
                            n4 += n8;
                        }
                    }
                    else {
                        n6 -= n5;
                        n5 -= n;
                        n *= this.anInt6328;
                        while (~(--n5) <= -1) {
                            Class246_Sub4_Sub2.method3108((byte)1, n, n2 >> 580937424, n3 >> -1796960496, 0, this.aByteArray6332);
                            n3 += n9;
                            n += this.anInt6328;
                            n2 += n7;
                        }
                        while (--n6 >= 0) {
                            Class246_Sub4_Sub2.method3108((byte)1, n, n2 >> 2001540368, n4 >> -1393449104, 0, this.aByteArray6332);
                            n += this.anInt6328;
                            n2 += n7;
                            n4 += n8;
                        }
                    }
                }
                else {
                    n2 = (n4 = n2 << 1281760752);
                    if (n < 0) {
                        n2 -= n7 * n;
                        n4 -= n * n9;
                        n = 0;
                    }
                    n3 <<= -1708312816;
                    if (n6 < 0) {
                        n3 -= n6 * n8;
                        n6 = 0;
                    }
                    if ((~n == ~n6 || n9 >= n7) && (~n != ~n6 || n9 <= n8)) {
                        n5 -= n6;
                        n6 -= n;
                        n *= this.anInt6328;
                        while (--n6 >= 0) {
                            Class246_Sub4_Sub2.method3108((byte)1, n, n2 >> 1230470320, n4 >> -1985593040, 0, this.aByteArray6332);
                            n += this.anInt6328;
                            n4 += n9;
                            n2 += n7;
                        }
                        while (~(--n5) <= -1) {
                            Class246_Sub4_Sub2.method3108((byte)1, n, n3 >> 52576112, n4 >> 411287696, 0, this.aByteArray6332);
                            n += this.anInt6328;
                            n3 += n8;
                            n4 += n9;
                        }
                    }
                    else {
                        n5 -= n6;
                        n6 -= n;
                        n *= this.anInt6328;
                        while (--n6 >= 0) {
                            Class246_Sub4_Sub2.method3108((byte)1, n, n4 >> 1095249744, n2 >> 265258096, 0, this.aByteArray6332);
                            n2 += n7;
                            n += this.anInt6328;
                            n4 += n9;
                        }
                        while (--n5 >= 0) {
                            Class246_Sub4_Sub2.method3108((byte)1, n, n4 >> 1255153136, n3 >> -1907768848, 0, this.aByteArray6332);
                            n3 += n8;
                            n += this.anInt6328;
                            n4 += n9;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gl.O(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ',' + n6 + ')');
        }
    }
    
    final void method1654(final int n) {
        try {
            if (n <= 68) {
                this.anInt6328 = -106;
            }
            int n2;
            for (n2 = -1; ~n2 > ~(-8 + this.aByteArray6332.length); this.aByteArray6332[++n2] = 0, this.aByteArray6332[++n2] = 0, this.aByteArray6332[++n2] = 0, this.aByteArray6332[++n2] = 0, this.aByteArray6332[++n2] = 0, this.aByteArray6332[++n2] = 0, this.aByteArray6332[++n2] = 0, this.aByteArray6332[++n2] = 0) {}
            while (this.aByteArray6332.length - 1 > n2) {
                this.aByteArray6332[++n2] = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gl.B(" + n + ')');
        }
    }
    
    static final boolean method1655(final int n, final byte b, final int n2) {
        try {
            return b > -120 || (s_Sub1.method3432(n, (byte)114, n2) & Class5.method176(24578, n, n2));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gl.E(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    static final void method1656(final Class246_Sub3[] array, final int n, final int n2) {
        if (n < n2) {
            final int n3 = (n + n2) / 2;
            int n4 = n;
            final Class246_Sub3 class246_Sub3 = array[n3];
            array[n3] = array[n2];
            array[n2] = class246_Sub3;
            final int anInt5083 = class246_Sub3.anInt5083;
            for (int i = n; i < n2; ++i) {
                if (array[i].anInt5083 > anInt5083 + (i & 0x1)) {
                    final Class246_Sub3 class246_Sub4 = array[i];
                    array[i] = array[n4];
                    array[n4++] = class246_Sub4;
                }
            }
            array[n2] = array[n4];
            array[n4] = class246_Sub3;
            method1656(array, n, n4 - 1);
            method1656(array, n4 + 1, n2);
        }
    }
    
    static final void method1657(int n, final byte[] array, final int n2, final int n3, final int n4, int n5, final int n6, final byte[] array2, int n7) {
        try {
            if (n3 != 1230470320) {
                method1657(-3, null, -82, 89, -120, 72, 31, null, -70);
            }
            final int n8 = -(n5 >> -110374878);
            n5 = -(0x3 & n5);
            for (int i = -n2; i < 0; ++i) {
                for (int j = n8; j < 0; ++j) {
                    final int n9 = n7++;
                    array[n9] += array2[n++];
                    final int n10 = n7++;
                    array[n10] += array2[n++];
                    final int n11 = n7++;
                    array[n11] += array2[n++];
                    final int n12 = n7++;
                    array[n12] += array2[n++];
                }
                for (int n13 = n5; ~n13 > -1; ++n13) {
                    final int n14 = n7++;
                    array[n14] += array2[n++];
                }
                n += n6;
                n7 += n4;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gl.F(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n7 + ')');
        }
    }
    
    r_Sub2(final ha_Sub3 ha_Sub3, final int n, final int n2) {
        try {
            this.aByteArray6332 = new byte[n2 * n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gl.<init>(" + ((ha_Sub3 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static {
        r_Sub2.aClass171_6330 = new OutgoingOpcode(77, -1);
        r_Sub2.aClass38_6334 = new Class38();
    }
}
