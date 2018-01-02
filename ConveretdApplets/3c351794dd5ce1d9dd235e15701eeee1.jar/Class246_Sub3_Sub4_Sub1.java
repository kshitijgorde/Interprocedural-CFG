// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub4_Sub1 extends Class246_Sub3_Sub4 implements Interface19
{
    private byte aByte6232;
    private byte aByte6233;
    private boolean aBoolean6234;
    private boolean aBoolean6235;
    private Class228 aClass228_6236;
    private boolean aBoolean6237;
    private short aShort6238;
    private boolean aBoolean6239;
    static Class279 aClass279_6240;
    static int anInt6241;
    private r aR6242;
    Class146 aClass146_6243;
    static boolean aBoolean6244;
    
    @Override
    final boolean method2987(final int n) {
        try {
            if (this.aClass146_6243 != null) {
                return this.aClass146_6243.F();
            }
            if (n != 6540) {
                this.method2985(true);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.I(" + n + ')');
        }
    }
    
    @Override
    public final void method67(final int n, final ha ha) {
        try {
            r ar6242;
            if (this.aR6242 == null && this.aBoolean6239) {
                final Class298 method3028 = this.method3028(262144, (byte)101, ha, true);
                ar6242 = ((method3028 == null) ? null : method3028.aR2479);
            }
            else {
                ar6242 = this.aR6242;
                this.aR6242 = null;
            }
            if (ar6242 != null) {
                Class268.method3254(ar6242, super.aByte5081, super.anInt5084, super.anInt5079, null);
            }
            if (n != -25163) {
                this.aBoolean6235 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.E(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            if (this.aClass146_6243 == null) {
                return null;
            }
            final Class111 method1793 = ha.method1793();
            method1793.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            if (n >= -12) {
                this.method2992((byte)(-76));
            }
            final Class246_Sub1 method1794 = Class94.method915(1, (byte)(-47), this.aBoolean6234);
            if (Class239.aBoolean1839) {
                this.aClass146_6243.method2329(method1793, method1794.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
                if (!client.aBoolean3553) {
                    return method1794;
                }
            }
            this.aClass146_6243.method2325(method1793, method1794.aClass246_Sub6Array5067[0], 0);
            return method1794;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final int method64(final int n) {
        try {
            if (n != 30472) {
                this.method2981(null, (byte)46, false, -115, null, -54, -101);
            }
            return 0xFFFF & this.aShort6238;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.C(" + n + ')');
        }
    }
    
    static final void method3025(final byte b) {
        try {
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4058.method612((byte)125) == 0xFFFFFFFD) {
                final byte b2 = (byte)(Class64_Sub15.anInt3676 - 4 & 0xFF);
                final int n = Class64_Sub15.anInt3676 % Class165.anInt1276;
                for (int i = 0; i < 4; ++i) {
                    for (int n2 = 0; Class98_Sub10_Sub7.anInt5572 > n2; ++n2) {
                        OutputStream_Sub2.aByteArrayArrayArray41[i][n][n2] = b2;
                    }
                }
                if (Class43.anInt377 != 3) {
                    int n3 = 0;
                    if (b != 72) {
                        method3029(-45);
                    }
                    while (~n3 > -3) {
                        Class204.anIntArray1551[n3] = -1000000;
                        Class336.anIntArray2826[n3] = 1000000;
                        Class287.anIntArray2195[n3] = 0;
                        Class48_Sub1_Sub2.anIntArray5518[n3] = 1000000;
                        Class295.anIntArray2409[n3] = 0;
                        ++n3;
                    }
                    int n4 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084;
                    int n5 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079;
                    if (~Class98_Sub46_Sub20_Sub2.anInt6319 == 0xFFFFFFFE || ~Class116.anInt967 != 0x0) {
                        if (Class98_Sub46_Sub20_Sub2.anInt6319 != 1) {
                            n5 = Class64_Sub26.anInt3712;
                            n4 = Class116.anInt967;
                        }
                        if ((Class281.aByteArrayArrayArray2117[Class43.anInt377][n4 >> 819933737][n5 >> -1734096311] & 0x4) != 0x0) {
                            Class253.method3175(n4 >> 302605065, 0, Class98_Sub46_Sub1.aClass172ArrayArrayArray5948, n5 >> 68371017, (byte)(-76), false);
                        }
                        if (~Class246_Sub3_Sub4_Sub2.anInt6357 > -2561) {
                            int n6 = Class98_Sub46_Sub10.anInt6020 >> 813546185;
                            int n7 = Class134.anInt3461 >> -794721367;
                            final int n8 = n4 >> 847752841;
                            final int j = n5 >> -43834903;
                            int n9;
                            if (~n8 < ~n6) {
                                n9 = n8 + -n6;
                            }
                            else {
                                n9 = -n8 + n6;
                            }
                            int n10;
                            if (~n7 <= ~j) {
                                n10 = n7 - j;
                            }
                            else {
                                n10 = -n7 + j;
                            }
                            if ((~n9 == -1 && n10 == 0) || ~n9 >= ~(-Class165.anInt1276) || ~Class165.anInt1276 >= ~n9 || -Class98_Sub10_Sub7.anInt5572 >= n10 || n10 >= Class98_Sub10_Sub7.anInt5572) {
                                Class305_Sub1.method3585(null, -127, "RC: " + n6 + "," + n7 + " " + n8 + "," + j + " " + Class272.anInt2038 + "," + aa_Sub2.anInt3562);
                            }
                            else if (n9 > n10) {
                                final int n11 = n10 * 65536 / n9;
                                int n12 = 32768;
                                while (~n6 != ~n8) {
                                    if (~n8 >= ~n6) {
                                        if (n8 < n6) {
                                            --n6;
                                        }
                                    }
                                    else {
                                        ++n6;
                                    }
                                    if ((0x4 & Class281.aByteArrayArrayArray2117[Class43.anInt377][n6][n7]) != 0x0) {
                                        Class253.method3175(n6, 1, Class98_Sub46_Sub1.aClass172ArrayArrayArray5948, n7, (byte)(-76), false);
                                        break;
                                    }
                                    n12 += n11;
                                    if (~n12 > -65537) {
                                        continue;
                                    }
                                    if (~j < ~n7) {
                                        ++n7;
                                    }
                                    else if (n7 > j) {
                                        --n7;
                                    }
                                    n12 -= 65536;
                                    if ((Class281.aByteArrayArrayArray2117[Class43.anInt377][n6][n7] & 0x4) != 0x0) {
                                        Class253.method3175(n6, 1, Class98_Sub46_Sub1.aClass172ArrayArrayArray5948, n7, (byte)(-76), false);
                                        break;
                                    }
                                }
                            }
                            else {
                                final int n13 = 65536 * n9 / n10;
                                int n14 = 32768;
                                while (j != n7) {
                                    if (~j >= ~n7) {
                                        if (~n7 < ~j) {
                                            --n7;
                                        }
                                    }
                                    else {
                                        ++n7;
                                    }
                                    if ((Class281.aByteArrayArrayArray2117[Class43.anInt377][n6][n7] & 0x4) != 0x0) {
                                        Class253.method3175(n6, 1, Class98_Sub46_Sub1.aClass172ArrayArrayArray5948, n7, (byte)(-76), false);
                                        break;
                                    }
                                    n14 += n13;
                                    if (n14 < 65536) {
                                        continue;
                                    }
                                    if (n8 <= n6) {
                                        if (~n6 < ~n8) {
                                            --n6;
                                        }
                                    }
                                    else {
                                        ++n6;
                                    }
                                    n14 -= 65536;
                                    if ((0x4 & Class281.aByteArrayArrayArray2117[Class43.anInt377][n6][n7]) != 0x0) {
                                        Class253.method3175(n6, 1, Class98_Sub46_Sub1.aClass172ArrayArrayArray5948, n7, (byte)(-76), false);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else if (Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, Class134.anInt3461, Class98_Sub46_Sub10.anInt6020, 24111) + -Class79.anInt601 < 3200 && (0x4 & Class281.aByteArrayArrayArray2117[Class43.anInt377][Class98_Sub46_Sub10.anInt6020 >> 2064460777][Class134.anInt3461 >> -1481382711]) != 0x0) {
                        Class253.method3175(Class98_Sub46_Sub10.anInt6020 >> 1855536521, 1, Class98_Sub46_Sub1.aClass172ArrayArrayArray5948, Class134.anInt3461 >> 510517769, (byte)(-76), false);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.M(" + b + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                this.aClass146_6243 = null;
            }
            if (this.aClass146_6243 == null) {
                return 0;
            }
            return this.aClass146_6243.fa();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.J(" + n + ')');
        }
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            if (b != -73) {
                this.method2992((byte)(-74));
            }
            this.aBoolean6235 = false;
            if (this.aClass146_6243 != null) {
                this.aClass146_6243.s(0xFFFEFFFF & this.aClass146_6243.ua());
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.DA(" + b + ')');
        }
    }
    
    private final Class146 method3026(final int n, final int n2, final ha ha) {
        try {
            if (this.aClass146_6243 != null && ~ha.c(this.aClass146_6243.ua(), n2) == -1) {
                return this.aClass146_6243;
            }
            if (n >= -20) {
                return null;
            }
            final Class298 method3028 = this.method3028(n2, (byte)123, ha, false);
            if (method3028 != null) {
                return method3028.aClass146_2477;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.P(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (b != -53) {
                return null;
            }
            if (this.aClass228_6236 == null) {
                this.aClass228_6236 = Class48_Sub2_Sub1.method472(super.anInt5089, super.anInt5084, this.method3026(-128, 0, ha), super.anInt5079, b ^ 0xFFFFFFCF);
            }
            return this.aClass228_6236;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class246_Sub3_Sub4_Sub1(final ha ha, final Class352 class352, final int n, final int n2, final int n3, final int n4, final int n5, final boolean aBoolean6237, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final boolean aBoolean6238) {
        super(n, n2, n3, n4, n5, n6, n7, n8, n9, class352.anInt2975 == 1, Class246_Sub3_Sub4_Sub5.method3088(n10, (byte)(-41), n11));
        try {
            this.aBoolean6235 = aBoolean6238;
            this.aBoolean6237 = aBoolean6237;
            this.aByte6233 = (byte)n11;
            this.aShort6238 = (short)class352.id;
            this.aBoolean6234 = (~class352.anInt2998 != -1 && !aBoolean6237);
            super.aByte5081 = (byte)n2;
            this.aByte6232 = (byte)n10;
            this.aBoolean6239 = (ha.method1771() && class352.aBoolean2935 && !this.aBoolean6237 && ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method579((byte)127) != -1);
            int n12 = 2048;
            if (this.aBoolean6235) {
                n12 |= 0x10000;
            }
            final Class298 method3028 = this.method3028(n12, (byte)95, ha, this.aBoolean6239);
            if (method3028 != null) {
                this.aR6242 = method3028.aR2479;
                this.aClass146_6243 = method3028.aClass146_2477;
                if (this.aBoolean6235) {
                    this.aClass146_6243 = this.aClass146_6243.method2341((byte)0, n12, false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((class352 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + aBoolean6237 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + n11 + ',' + aBoolean6238 + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            if (b <= 59) {
                this.aBoolean6234 = false;
            }
            final Class146 method3026 = this.method3026(-120, 131072, ha);
            if (method3026 == null) {
                return false;
            }
            final Class111 method3027 = ha.method1793();
            method3027.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            if (!Class239.aBoolean1839) {
                return method3026.method2339(n, n2, method3027, false, 0);
            }
            return method3026.method2333(n, n2, method3027, false, 0, Class16.anInt197);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return this.aClass146_6243 == null || !this.aClass146_6243.r();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.H(" + n + ')');
        }
    }
    
    @Override
    public final boolean method65(final boolean b) {
        try {
            if (!b) {
                this.aClass228_6236 = null;
            }
            return this.aBoolean6239;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.A(" + b + ')');
        }
    }
    
    final int method3027(final int n) {
        try {
            if (n <= 20) {
                Class246_Sub3_Sub4_Sub1.aClass279_6240 = null;
            }
            if (this.aClass146_6243 != null) {
                return this.aClass146_6243.na() / 4;
            }
            return 15;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.K(" + n + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                return -120;
            }
            if (this.aClass146_6243 != null) {
                return this.aClass146_6243.ma();
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.O(" + b + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b >= -70) {
                Class246_Sub3_Sub4_Sub1.anInt6241 = -94;
            }
            return this.aBoolean6235;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.HA(" + b + ')');
        }
    }
    
    private final Class298 method3028(final int n, final byte b, final ha ha, final boolean b2) {
        try {
            if (b < 69) {
                this.method61((byte)(-69));
            }
            final Class352 method3546 = Class130.aClass302_1028.method3546(this.aShort6238 & 0xFFFF, (byte)119);
            s s;
            s s2;
            if (!this.aBoolean6237) {
                if (~super.aByte5081 <= -4) {
                    s = null;
                }
                else {
                    s = Class98_Sub46_Sub2_Sub2.aSArray6298[1 + super.aByte5081];
                }
                s2 = Class98_Sub46_Sub2_Sub2.aSArray6298[super.aByte5081];
            }
            else {
                s = Class98_Sub46_Sub2_Sub2.aSArray6298[0];
                s2 = Class81.aSArray618[super.aByte5081];
            }
            return method3546.method3851(super.anInt5079, false, s, (~this.aByte6232 == 0xFFFFFFF4) ? (this.aByte6233 + 4) : this.aByte6233, super.anInt5089, b2, super.anInt5084, n, null, s2, ha, (this.aByte6232 != 11) ? this.aByte6232 : 10);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.D(" + n + ',' + b + ',' + ((ha != null) ? "{...}" : "null") + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            if (class246_Sub3 instanceof Class246_Sub3_Sub3_Sub2) {
                final Class246_Sub3_Sub3_Sub2 class246_Sub3_Sub3_Sub2 = (Class246_Sub3_Sub3_Sub2)class246_Sub3;
                if (this.aClass146_6243 == null) {
                    return;
                }
                if (class246_Sub3_Sub3_Sub2.aClass146_6285 == null) {
                    return;
                }
                this.aClass146_6243.method2332(class246_Sub3_Sub3_Sub2.aClass146_6285, n, n2, n3, b2);
                if (!client.aBoolean3553) {
                    return;
                }
            }
            if (class246_Sub3 instanceof Class246_Sub3_Sub4_Sub1) {
                final Class246_Sub3_Sub4_Sub1 class246_Sub3_Sub4_Sub1 = (Class246_Sub3_Sub4_Sub1)class246_Sub3;
                if (this.aClass146_6243 != null && class246_Sub3_Sub4_Sub1.aClass146_6243 != null) {
                    this.aClass146_6243.method2332(class246_Sub3_Sub4_Sub1.aClass146_6243, n, n2, n3, b2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    public final void method62(final ha ha, final int n) {
        try {
            r ar6242;
            if (this.aR6242 != null || !this.aBoolean6239) {
                ar6242 = this.aR6242;
                this.aR6242 = null;
            }
            else {
                final Class298 method3028 = this.method3028(262144, (byte)85, ha, true);
                ar6242 = ((method3028 != null) ? method3028.aR2479 : null);
            }
            if (n != 24447) {
                this.method2990(94);
            }
            if (ar6242 != null) {
                Class184.method2626(ar6242, super.aByte5081, super.anInt5084, super.anInt5079, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.G(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final int method66(final int n) {
        try {
            if (n != 4657) {
                return 91;
            }
            return this.aByte6233;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.N(" + n + ')');
        }
    }
    
    @Override
    public final int method63(final byte b) {
        try {
            if (b != 20) {
                this.method2992((byte)9);
            }
            return this.aByte6232;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.B(" + b + ')');
        }
    }
    
    public static void method3029(final int n) {
        try {
            Class246_Sub3_Sub4_Sub1.aClass279_6240 = null;
            if (n != -1) {
                method3025((byte)(-65));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.L(" + n + ')');
        }
    }
    
    @Override
    public final void method61(final byte b) {
        try {
            if (this.aClass146_6243 != null) {
                this.aClass146_6243.method2326();
            }
            if (b != -96) {
                this.aBoolean6234 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mb.Q(" + b + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub4_Sub1.aClass279_6240 = new Class279("game3", 2);
        Class246_Sub3_Sub4_Sub1.aBoolean6244 = false;
    }
}
