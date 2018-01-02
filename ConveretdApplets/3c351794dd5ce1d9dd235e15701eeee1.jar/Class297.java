// 
// Decompiled by Procyon v0.5.30
// 

final class Class297
{
    int anInt2414;
    private int anInt2415;
    int anInt2416;
    private int anInt2417;
    int anInt2418;
    Class205 aClass205_2419;
    boolean aBoolean2420;
    int anInt2421;
    private int anInt2422;
    private int anInt2423;
    private int anInt2424;
    private int anInt2425;
    private int anInt2426;
    private int anInt2427;
    int[] anIntArray2428;
    private int anInt2429;
    private short[] aShortArray2430;
    private int anInt2431;
    private int anInt2432;
    int anInt2433;
    int anInt2434;
    int anInt2435;
    int[] anIntArray2436;
    int anInt2437;
    int anInt2438;
    int anInt2439;
    int anInt2440;
    int anInt2441;
    private short[] aShortArray2442;
    Class377 aClass377_2443;
    private int anInt2444;
    int anInt2445;
    String[] aStringArray2446;
    int anInt2447;
    private int anInt2448;
    private int anInt2449;
    String aString2450;
    private int anInt2451;
    private int anInt2452;
    private int anInt2453;
    int[] anIntArray2454;
    private int anInt2455;
    private short[] aShortArray2456;
    private byte[] aByteArray2457;
    int anInt2458;
    int anInt2459;
    private short[] aShortArray2460;
    boolean aBoolean2461;
    int anInt2462;
    int anInt2463;
    int anInt2464;
    int anInt2465;
    int anInt2466;
    private int anInt2467;
    int anInt2468;
    int anInt2469;
    static int anInt2470;
    int anInt2471;
    int anInt2472;
    String[] aStringArray2473;
    private int anInt2474;
    int anInt2475;
    int anInt2476;
    
    final void method3485(final int n) {
        try {
            if (n != 850) {
                this.anIntArray2436 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.M(" + n + ')');
        }
    }
    
    final Class178 method3486(final boolean b, final int n) {
        try {
            int n2 = this.anInt2417;
            int n3 = this.anInt2449;
            if (b) {
                n3 = this.anInt2455;
                n2 = this.anInt2453;
            }
            if (n2 == -1) {
                return null;
            }
            Class178 method981 = Class98_Sub6.method981(n, n ^ 0xFFFFDBDC, this.aClass205_2419.aClass207_1556, n2);
            if (method981.anInt1387 < 13) {
                method981.method2592(13746, 2);
            }
            if (n3 != -1) {
                final Class178 method982 = Class98_Sub6.method981(0, -9252, this.aClass205_2419.aClass207_1556, n3);
                if (method982.anInt1387 < 13) {
                    method982.method2592(13746, 2);
                }
                method981 = new Class178(new Class178[] { method981, method982 }, 2);
            }
            if (this.aShortArray2430 != null) {
                for (int n4 = 0; this.aShortArray2430.length > n4; ++n4) {
                    method981.method2593(0, this.aShortArray2430[n4], this.aShortArray2442[n4]);
                }
            }
            if (this.aShortArray2460 != null) {
                for (int i = 0; i < this.aShortArray2460.length; ++i) {
                    method981.method2590(this.aShortArray2456[i], (byte)115, this.aShortArray2460[i]);
                }
            }
            return method981;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.L(" + b + ',' + n + ')');
        }
    }
    
    final void method3487(final Class297 class297, final int n, final Class297 class298) {
        try {
            this.aShortArray2456 = class298.aShortArray2456;
            this.anInt2437 = class298.anInt2437;
            this.anInt2441 = class298.anInt2441;
            this.aShortArray2430 = class298.aShortArray2430;
            this.anInt2447 = class298.anInt2447;
            this.aString2450 = class297.aString2450;
            this.anInt2476 = class298.anInt2476;
            this.anInt2469 = 1;
            this.aShortArray2460 = class298.aShortArray2460;
            this.anInt2431 = class298.anInt2431;
            this.aByteArray2457 = class298.aByteArray2457;
            this.anInt2416 = class298.anInt2416;
            this.anInt2475 = class297.anInt2475;
            this.aShortArray2442 = class298.aShortArray2442;
            this.aBoolean2420 = class297.aBoolean2420;
            this.anInt2465 = class298.anInt2465;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.I(" + ((class297 != null) ? "{...}" : "null") + ',' + n + ',' + ((class298 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int[] method3488(final int n, final int n2, final boolean b, final int n3, final int n4, final ha ha, final ha ha2, final byte b2, final Class313 class313, final Class43 class314) {
        try {
            final Class178 method981 = Class98_Sub6.method981(0, -9252, this.aClass205_2419.aClass207_1556, this.anInt2431);
            if (method981 == null) {
                return null;
            }
            if (method981.anInt1387 < 13) {
                method981.method2592(13746, 2);
            }
            if (this.aShortArray2430 != null) {
                for (int n5 = 0; ~this.aShortArray2430.length < ~n5; ++n5) {
                    if (this.aByteArray2457 != null && ~n5 > ~this.aByteArray2457.length) {
                        method981.method2593(0, this.aShortArray2430[n5], Class338.aShortArray2833[0xFF & this.aByteArray2457[n5]]);
                    }
                    else {
                        method981.method2593(b2 + 125, this.aShortArray2430[n5], this.aShortArray2442[n5]);
                    }
                }
            }
            if (this.aShortArray2460 != null) {
                for (int n6 = 0; ~this.aShortArray2460.length < ~n6; ++n6) {
                    method981.method2590(this.aShortArray2456[n6], (byte)(-99), this.aShortArray2460[n6]);
                }
            }
            if (class313 != null) {
                for (int i = 0; i < 5; ++i) {
                    for (int n7 = 0; ~Class61.aShortArrayArrayArray478.length < ~n7; ++n7) {
                        if (Class61.aShortArrayArrayArray478[n7][i].length > class313.anIntArray2683[i]) {
                            method981.method2593(0, Class98_Sub10_Sub11.aShortArrayArray5597[n7][i], Class61.aShortArrayArrayArray478[n7][i][class313.anIntArray2683[i]]);
                        }
                    }
                }
            }
            int n8 = 2048;
            boolean b3 = false;
            if (this.anInt2451 != 128 || this.anInt2429 != 128 || this.anInt2415 != 128) {
                b3 = true;
                n8 |= 0x7;
            }
            final Class146 method982 = ha2.method1790(method981, n8, 64, 64 - -this.anInt2452, this.anInt2422 + 768);
            if (!method982.method2324()) {
                return null;
            }
            if (b3) {
                method982.O(this.anInt2451, this.anInt2429, this.anInt2415);
            }
            Class332 class315 = null;
            if (this.anInt2414 != -1) {
                class315 = this.aClass205_2419.method2722(class314, false, this.anInt2433, 1, 0, class313, ha2, 0, true, 10, ha, true);
                if (class315 == null) {
                    return null;
                }
            }
            else if (~this.anInt2459 != 0x0) {
                class315 = this.aClass205_2419.method2722(class314, false, this.anInt2472, n4, n, class313, ha2, 0, true, n3, ha, false);
                if (class315 == null) {
                    return null;
                }
            }
            int n9;
            if (b) {
                n9 = (int)(this.anInt2465 * 1.5) << -21057662;
            }
            else if (n4 == 2) {
                n9 = (int)(1.04 * this.anInt2465) << 839018274;
            }
            else {
                n9 = this.anInt2465 << -512379550;
            }
            ha2.DA(16, 16, 512, 512);
            final Class111 method983 = ha2.method1821();
            method983.method2091();
            ha2.a(method983);
            ha2.xa(1.0f);
            ha2.ZA(16777215, 1.0f, 1.0f, -50.0f, -10.0f, -50.0f);
            final Class111 method984 = ha2.method1793();
            method984.method2104(-this.anInt2441 << -384263997);
            method984.method2097(this.anInt2476 << 857131171);
            method984.method2106(this.anInt2437 << 1252715202, (n9 * Class284_Sub2_Sub2.anIntArray6200[this.anInt2416 << -664636093] >> -1954445778) + -(method982.fa() / 2) + (this.anInt2447 << -1632893918), (n9 * Class284_Sub2_Sub2.anIntArray6202[this.anInt2416 << 769335843] >> -1078595890) - -(this.anInt2447 << -772037790));
            method984.method2105(this.anInt2416 << 1743842275);
            final int j = ha2.i();
            final int xa = ha2.XA();
            ha2.f(50, Integer.MAX_VALUE);
            ha2.ya();
            if (b2 != -125) {
                return null;
            }
            ha2.la();
            ha2.aa(0, 0, 36, 32, 0, 0);
            method982.method2325(method984, null, 1);
            ha2.f(j, xa);
            int[] array = ha2.na(0, 0, 36, 32);
            if (~n4 <= -2) {
                array = this.method3491(array, b2 + 3, -16777214);
                if (n4 >= 2) {
                    array = this.method3491(array, -109, -1);
                }
            }
            if (~n != -1) {
                this.method3499(-76, n, array);
            }
            ha2.method1748(-7962, 0, 36, 32, array, 36).method3735(0, 0);
            if (this.anInt2414 == -1) {
                if (this.anInt2459 != -1) {
                    class315.method3735(0, 0);
                }
            }
            else {
                class315.method3735(0, 0);
            }
            if (~n2 == 0xFFFFFFFE || (n2 == 2 && (this.anInt2469 == 1 || n3 != 1) && ~n3 != 0x0)) {
                class314.method411((byte)76, 9, this.method3490((byte)(-93), n3), -256, -16777215, 0);
            }
            final int[] na = ha2.na(0, 0, 36, 32);
            for (int n10 = 0; ~na.length < ~n10; ++n10) {
                if ((0xFFFFFF & na[n10]) == 0x0) {
                    na[n10] = 0;
                }
                else {
                    na[n10] = Class41.method366(na[n10], -16777216);
                }
            }
            return na;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.H(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((ha2 != null) ? "{...}" : "null") + ',' + b2 + ',' + ((class313 != null) ? "{...}" : "null") + ',' + ((class314 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method3489(final boolean b, final int n) {
        try {
            int n2 = this.anInt2417;
            int n3 = this.anInt2449;
            if (b) {
                n2 = this.anInt2453;
                n3 = this.anInt2455;
            }
            if (~n2 == 0x0) {
                return true;
            }
            boolean b2 = true;
            if (!this.aClass205_2419.aClass207_1556.method2751(0, n2, -6329)) {
                b2 = false;
            }
            if (n3 != -1 && !this.aClass205_2419.aClass207_1556.method2751(0, n3, -6329)) {
                b2 = false;
            }
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.B(" + b + ',' + n + ')');
        }
    }
    
    private final String method3490(final byte b, final int n) {
        try {
            if (b != -93) {
                this.anInt2459 = 107;
            }
            if (n < 100000) {
                return "<col=ffff00>" + n + "</col>";
            }
            if (~n > -10000001) {
                return "<col=ffffff>" + n / 1000 + Class309.aClass309_2622.method3615(this.aClass205_2419.anInt1555, (byte)25) + "</col>";
            }
            return "<col=00ff80>" + n / 1000000 + Class309.aClass309_2620.method3615(this.aClass205_2419.anInt1555, (byte)25) + "</col>";
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.J(" + b + ',' + n + ')');
        }
    }
    
    private final int[] method3491(final int[] array, final int n, final int n2) {
        try {
            if (n > -36) {
                return null;
            }
            final int[] array2 = new int[1152];
            int n3 = 0;
            for (int n4 = 0; ~n4 > -33; ++n4) {
                for (int i = 0; i < 36; ++i) {
                    int n5 = array[n3];
                    if (~n5 == -1) {
                        if (i <= 0 || ~array[-1 + n3] == -1) {
                            if (~n4 >= -1 || ~array[-36 + n3] == -1) {
                                if (i < 35 && array[n3 + 1] != 0) {
                                    n5 = n2;
                                }
                                else if (n4 < 31 && ~array[36 + n3] != -1) {
                                    n5 = n2;
                                }
                            }
                            else {
                                n5 = n2;
                            }
                        }
                        else {
                            n5 = n2;
                        }
                    }
                    array2[n3++] = n5;
                }
            }
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.A(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final boolean method3492(final int n, final boolean b) {
        try {
            int n2 = this.anInt2458;
            int n3 = this.anInt2444;
            int n4 = this.anInt2424;
            if (b) {
                n3 = this.anInt2423;
                n2 = this.anInt2466;
                n4 = this.anInt2432;
            }
            if (~n2 == 0x0) {
                return true;
            }
            boolean b2 = true;
            if (!this.aClass205_2419.aClass207_1556.method2751(n, n2, -6329)) {
                b2 = false;
            }
            if (n3 != -1 && !this.aClass205_2419.aClass207_1556.method2751(0, n3, -6329)) {
                b2 = false;
            }
            if (~n4 != 0x0 && !this.aClass205_2419.aClass207_1556.method2751(0, n4, -6329)) {
                b2 = false;
            }
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.D(" + n + ',' + b + ')');
        }
    }
    
    final Class297 method3493(final byte b, final int n) {
        try {
            if (this.anIntArray2428 != null && ~n < -2) {
                int n2 = -1;
                for (int i = 0; i < 10; ++i) {
                    if (this.anIntArray2454[i] <= n && this.anIntArray2454[i] != 0) {
                        n2 = this.anIntArray2428[i];
                    }
                }
                if (~n2 != 0x0) {
                    return this.aClass205_2419.method2714(n2, (byte)(-124));
                }
            }
            return this;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.P(" + b + ',' + n + ')');
        }
    }
    
    final int method3494(final int n, final byte b, final int n2) {
        try {
            if (b > -75) {
                return -30;
            }
            if (this.aClass377_2443 == null) {
                return n2;
            }
            final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass377_2443.method3990(n, -1);
            if (class98_Sub34 == null) {
                return n2;
            }
            return class98_Sub34.anInt4126;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.G(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    final String method3495(final String s, final int n, final int n2) {
        try {
            if (this.aClass377_2443 == null) {
                return s;
            }
            final Class98_Sub15 class98_Sub15 = (Class98_Sub15)this.aClass377_2443.method3990(n2, n);
            if (class98_Sub15 == null) {
                return s;
            }
            return class98_Sub15.aString3917;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.F(" + ((s != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    private final void method3496(final int n, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            if (n != 1) {
                if (~n == 0xFFFFFFFD) {
                    this.aString2450 = class98_Sub22.readString((byte)84);
                }
                else if (n != 4) {
                    if (n == 5) {
                        this.anInt2416 = class98_Sub22.readShort((byte)127);
                    }
                    else if (n == 6) {
                        this.anInt2476 = class98_Sub22.readShort((byte)127);
                    }
                    else if (n != 7) {
                        if (n != 8) {
                            if (n != 11) {
                                if (~n == 0xFFFFFFF3) {
                                    this.anInt2475 = class98_Sub22.readInt(-2);
                                }
                                else if (n == 16) {
                                    this.aBoolean2420 = true;
                                }
                                else if (~n != 0xFFFFFFED) {
                                    if (~n != 0xFFFFFFE8) {
                                        if (n == 24) {
                                            this.anInt2444 = class98_Sub22.readShort((byte)127);
                                        }
                                        else if (~n == 0xFFFFFFE6) {
                                            this.anInt2466 = class98_Sub22.readShort((byte)127);
                                        }
                                        else if (n != 26) {
                                            if (n < 30 || n >= 35) {
                                                if (~n > -36 || n >= 40) {
                                                    if (n != 40) {
                                                        if (~n == 0xFFFFFFD6) {
                                                            final int i = class98_Sub22.readUnsignedByte((byte)123);
                                                            this.aShortArray2460 = new short[i];
                                                            this.aShortArray2456 = new short[i];
                                                            for (int n3 = 0; i > n3; ++n3) {
                                                                this.aShortArray2460[n3] = (short)class98_Sub22.readShort((byte)127);
                                                                this.aShortArray2456[n3] = (short)class98_Sub22.readShort((byte)127);
                                                            }
                                                        }
                                                        else if (~n != 0xFFFFFFD5) {
                                                            if (n == 65) {
                                                                this.aBoolean2461 = true;
                                                            }
                                                            else if (n == 78) {
                                                                this.anInt2424 = class98_Sub22.readShort((byte)127);
                                                            }
                                                            else if (~n != 0xFFFFFFB0) {
                                                                if (n != 90) {
                                                                    if (n != 91) {
                                                                        if (~n == 0xFFFFFFA3) {
                                                                            this.anInt2449 = class98_Sub22.readShort((byte)127);
                                                                        }
                                                                        else if (~n != 0xFFFFFFA2) {
                                                                            if (~n == 0xFFFFFFA0) {
                                                                                this.anInt2441 = class98_Sub22.readShort((byte)127);
                                                                            }
                                                                            else if (n != 96) {
                                                                                if (n != 97) {
                                                                                    if (~n == 0xFFFFFF9D) {
                                                                                        this.anInt2414 = class98_Sub22.readShort((byte)127);
                                                                                    }
                                                                                    else if (n < 100 || ~n <= -111) {
                                                                                        if (n != 110) {
                                                                                            if (~n == 0xFFFFFF90) {
                                                                                                this.anInt2429 = class98_Sub22.readShort((byte)127);
                                                                                            }
                                                                                            else if (n == 112) {
                                                                                                this.anInt2415 = class98_Sub22.readShort((byte)127);
                                                                                            }
                                                                                            else if (n == 113) {
                                                                                                this.anInt2452 = class98_Sub22.readSignedByte((byte)(-19));
                                                                                            }
                                                                                            else if (~n != 0xFFFFFF8D) {
                                                                                                if (~n == 0xFFFFFF8C) {
                                                                                                    this.anInt2435 = class98_Sub22.readUnsignedByte((byte)(-108));
                                                                                                }
                                                                                                else if (n != 121) {
                                                                                                    if (~n != 0xFFFFFF85) {
                                                                                                        if (~n == 0xFFFFFF82) {
                                                                                                            this.anInt2448 = class98_Sub22.readSignedByte((byte)(-19)) << 119149218;
                                                                                                            this.anInt2426 = class98_Sub22.readSignedByte((byte)(-19)) << -1266678398;
                                                                                                            this.anInt2425 = class98_Sub22.readSignedByte((byte)(-19)) << 1583045954;
                                                                                                        }
                                                                                                        else if (~n != 0xFFFFFF81) {
                                                                                                            if (n == 127) {
                                                                                                                this.anInt2438 = class98_Sub22.readUnsignedByte((byte)(-126));
                                                                                                                this.anInt2439 = class98_Sub22.readShort((byte)127);
                                                                                                            }
                                                                                                            else if (~n != 0xFFFFFF7F) {
                                                                                                                if (~n != 0xFFFFFF7E) {
                                                                                                                    if (~n != 0xFFFFFF7D) {
                                                                                                                        if (n != 132) {
                                                                                                                            if (n == 134) {
                                                                                                                                this.anInt2445 = class98_Sub22.readUnsignedByte((byte)118);
                                                                                                                            }
                                                                                                                            else if (~n == 0xFFFFFF06) {
                                                                                                                                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-115));
                                                                                                                                if (this.aClass377_2443 == null) {
                                                                                                                                    this.aClass377_2443 = new Class377(Class48.method453(423660257, unsignedByte));
                                                                                                                                }
                                                                                                                                for (int n4 = 0; ~unsignedByte < ~n4; ++n4) {
                                                                                                                                    final boolean b = ~class98_Sub22.readUnsignedByte((byte)18) == 0xFFFFFFFE;
                                                                                                                                    final int method1186 = class98_Sub22.method1186(n2 ^ 0xFFFFFF07);
                                                                                                                                    Class98 class98;
                                                                                                                                    if (!b) {
                                                                                                                                        class98 = new Class98_Sub34(class98_Sub22.readInt(-2));
                                                                                                                                    }
                                                                                                                                    else {
                                                                                                                                        class98 = new Class98_Sub15(class98_Sub22.readString((byte)84));
                                                                                                                                    }
                                                                                                                                    this.aClass377_2443.method3996(class98, method1186, -1);
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                        else {
                                                                                                                            final int j = class98_Sub22.readUnsignedByte((byte)(-114));
                                                                                                                            this.anIntArray2436 = new int[j];
                                                                                                                            for (int n5 = 0; j > n5; ++n5) {
                                                                                                                                this.anIntArray2436[n5] = class98_Sub22.readShort((byte)127);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                    else {
                                                                                                                        this.anInt2434 = class98_Sub22.readUnsignedByte((byte)(-110));
                                                                                                                        this.anInt2462 = class98_Sub22.readShort((byte)127);
                                                                                                                    }
                                                                                                                }
                                                                                                                else {
                                                                                                                    this.anInt2463 = class98_Sub22.readUnsignedByte((byte)79);
                                                                                                                    this.anInt2440 = class98_Sub22.readShort((byte)127);
                                                                                                                }
                                                                                                            }
                                                                                                            else {
                                                                                                                this.anInt2421 = class98_Sub22.readUnsignedByte((byte)(-101));
                                                                                                                this.anInt2471 = class98_Sub22.readShort((byte)127);
                                                                                                            }
                                                                                                        }
                                                                                                        else {
                                                                                                            this.anInt2474 = class98_Sub22.readSignedByte((byte)(-19)) << 1304107298;
                                                                                                            this.anInt2427 = class98_Sub22.readSignedByte((byte)(-19)) << 294608354;
                                                                                                            this.anInt2467 = class98_Sub22.readSignedByte((byte)(-19)) << -629362526;
                                                                                                        }
                                                                                                    }
                                                                                                    else {
                                                                                                        this.anInt2459 = class98_Sub22.readShort((byte)127);
                                                                                                    }
                                                                                                }
                                                                                                else {
                                                                                                    this.anInt2472 = class98_Sub22.readShort((byte)127);
                                                                                                }
                                                                                            }
                                                                                            else {
                                                                                                this.anInt2422 = 5 * class98_Sub22.readSignedByte((byte)(-19));
                                                                                            }
                                                                                        }
                                                                                        else {
                                                                                            this.anInt2451 = class98_Sub22.readShort((byte)127);
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                        if (this.anIntArray2428 == null) {
                                                                                            this.anIntArray2428 = new int[10];
                                                                                            this.anIntArray2454 = new int[10];
                                                                                        }
                                                                                        this.anIntArray2428[-100 + n] = class98_Sub22.readShort((byte)127);
                                                                                        this.anIntArray2454[-100 + n] = class98_Sub22.readShort((byte)127);
                                                                                    }
                                                                                }
                                                                                else {
                                                                                    this.anInt2433 = class98_Sub22.readShort((byte)127);
                                                                                }
                                                                            }
                                                                            else {
                                                                                this.anInt2464 = class98_Sub22.readUnsignedByte((byte)(-124));
                                                                            }
                                                                        }
                                                                        else {
                                                                            this.anInt2455 = class98_Sub22.readShort((byte)127);
                                                                        }
                                                                    }
                                                                    else {
                                                                        this.anInt2453 = class98_Sub22.readShort((byte)127);
                                                                    }
                                                                }
                                                                else {
                                                                    this.anInt2417 = class98_Sub22.readShort((byte)127);
                                                                }
                                                            }
                                                            else {
                                                                this.anInt2432 = class98_Sub22.readShort((byte)127);
                                                            }
                                                        }
                                                        else {
                                                            final int k = class98_Sub22.readUnsignedByte((byte)56);
                                                            this.aByteArray2457 = new byte[k];
                                                            for (int n6 = 0; k > n6; ++n6) {
                                                                this.aByteArray2457[n6] = class98_Sub22.readSignedByte((byte)(-19));
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-100));
                                                        this.aShortArray2442 = new short[unsignedByte2];
                                                        this.aShortArray2430 = new short[unsignedByte2];
                                                        for (int n7 = 0; ~n7 > ~unsignedByte2; ++n7) {
                                                            this.aShortArray2430[n7] = (short)class98_Sub22.readShort((byte)127);
                                                            this.aShortArray2442[n7] = (short)class98_Sub22.readShort((byte)127);
                                                        }
                                                    }
                                                }
                                                else {
                                                    this.aStringArray2473[-35 + n] = class98_Sub22.readString((byte)84);
                                                }
                                            }
                                            else {
                                                this.aStringArray2446[n - 30] = class98_Sub22.readString((byte)84);
                                            }
                                        }
                                        else {
                                            this.anInt2423 = class98_Sub22.readShort((byte)127);
                                        }
                                    }
                                    else {
                                        this.anInt2458 = class98_Sub22.readShort((byte)127);
                                    }
                                }
                                else {
                                    this.anInt2418 = class98_Sub22.readShort((byte)127);
                                }
                            }
                            else {
                                this.anInt2469 = 1;
                            }
                        }
                        else {
                            this.anInt2447 = class98_Sub22.readShort((byte)127);
                            if (this.anInt2447 > 32767) {
                                this.anInt2447 -= 65536;
                            }
                        }
                    }
                    else {
                        this.anInt2437 = class98_Sub22.readShort((byte)127);
                        if (this.anInt2437 > 32767) {
                            this.anInt2437 -= 65536;
                        }
                    }
                }
                else {
                    this.anInt2465 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt2431 = class98_Sub22.readShort((byte)127);
            }
            if (n2 != 132) {
                this.anInt2437 = 117;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.K(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    final void method3497(final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-123));
                if (~unsignedByte == -1) {
                    break;
                }
                this.method3496(unsignedByte, class98_Sub22, 132);
            }
            if (b >= -112) {
                this.aClass377_2443 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.O(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method3498(final Class297 class297, final Class297 class298, final int n) {
        try {
            this.anInt2475 = 0;
            this.aShortArray2430 = class297.aShortArray2430;
            this.anInt2432 = class297.anInt2432;
            this.anInt2424 = class297.anInt2424;
            this.anInt2474 = class297.anInt2474;
            this.aBoolean2420 = class297.aBoolean2420;
            this.anInt2425 = class297.anInt2425;
            this.anInt2455 = class297.anInt2455;
            this.anInt2423 = class297.anInt2423;
            this.anInt2448 = class297.anInt2448;
            this.anInt2437 = class298.anInt2437;
            this.anInt2417 = class297.anInt2417;
            this.anInt2467 = class297.anInt2467;
            this.aStringArray2473 = new String[5];
            this.anInt2465 = class298.anInt2465;
            this.aByteArray2457 = class297.aByteArray2457;
            this.anInt2444 = class297.anInt2444;
            this.anInt2476 = class298.anInt2476;
            this.anInt2449 = class297.anInt2449;
            this.aString2450 = class297.aString2450;
            this.aClass377_2443 = class297.aClass377_2443;
            this.anInt2431 = class298.anInt2431;
            this.anInt2426 = class297.anInt2426;
            this.aShortArray2456 = class297.aShortArray2456;
            this.anInt2458 = class297.anInt2458;
            this.anInt2453 = class297.anInt2453;
            this.aShortArray2442 = class297.aShortArray2442;
            this.anInt2441 = class298.anInt2441;
            this.anInt2416 = class298.anInt2416;
            this.anInt2466 = class297.anInt2466;
            this.aStringArray2446 = class297.aStringArray2446;
            this.aShortArray2460 = class297.aShortArray2460;
            this.anInt2427 = class297.anInt2427;
            this.anInt2447 = class298.anInt2447;
            this.anInt2435 = class297.anInt2435;
            if (class297.aStringArray2473 != null) {
                for (int i = 0; i < 4; ++i) {
                    this.aStringArray2473[i] = class297.aStringArray2473[i];
                }
            }
            this.aStringArray2473[4] = Class309.aClass309_2592.method3615(this.aClass205_2419.anInt1555, (byte)25);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.Q(" + ((class297 != null) ? "{...}" : "null") + ',' + ((class298 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method3499(final int n, final int n2, final int[] array) {
        try {
            int i = 31;
            if (n > -53) {
                this.anInt2452 = -82;
            }
            while (i > 0) {
                final int n3 = i * 36;
                for (int n4 = 35; ~n4 < -1; --n4) {
                    if (~array[n4 + n3] == -1 && ~array[n4 + (n3 - 37)] != -1) {
                        array[n4 + n3] = n2;
                    }
                }
                --i;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.C(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class178 method3500(final boolean b, final int n) {
        try {
            if (n <= 42) {
                this.method3487(null, 70, null);
            }
            int n2 = this.anInt2458;
            int n3 = this.anInt2444;
            int n4 = this.anInt2424;
            if (b) {
                n2 = this.anInt2466;
                n4 = this.anInt2432;
                n3 = this.anInt2423;
            }
            if (n2 == -1) {
                return null;
            }
            Class178 method981 = Class98_Sub6.method981(0, -9252, this.aClass205_2419.aClass207_1556, n2);
            if (~method981.anInt1387 > -14) {
                method981.method2592(13746, 2);
            }
            if (~n3 != 0x0) {
                final Class178 method982 = Class98_Sub6.method981(0, -9252, this.aClass205_2419.aClass207_1556, n3);
                if (method982.anInt1387 < 13) {
                    method982.method2592(13746, 2);
                }
                if (n4 != -1) {
                    final Class178 method983 = Class98_Sub6.method981(0, -9252, this.aClass205_2419.aClass207_1556, n4);
                    if (method983.anInt1387 < 13) {
                        method983.method2592(13746, 2);
                    }
                    method981 = new Class178(new Class178[] { method981, method982, method983 }, 3);
                }
                else {
                    method981 = new Class178(new Class178[] { method981, method982 }, 2);
                }
            }
            if (method981 == null) {
                return null;
            }
            if (!b && (this.anInt2448 != 0 || ~this.anInt2426 != -1 || this.anInt2425 != 0)) {
                method981.method2597(this.anInt2425, this.anInt2448, (byte)122, this.anInt2426);
            }
            if (b && (~this.anInt2474 != -1 || ~this.anInt2427 != -1 || ~this.anInt2467 != -1)) {
                method981.method2597(this.anInt2467, this.anInt2474, (byte)63, this.anInt2427);
            }
            if (this.aShortArray2430 != null) {
                for (int n5 = 0; ~n5 > ~this.aShortArray2430.length; ++n5) {
                    method981.method2593(0, this.aShortArray2430[n5], this.aShortArray2442[n5]);
                }
            }
            if (this.aShortArray2460 != null) {
                for (int i = 0; i < this.aShortArray2460.length; ++i) {
                    method981.method2590(this.aShortArray2456[i], (byte)107, this.aShortArray2460[i]);
                }
            }
            return method981;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.E(" + b + ',' + n + ')');
        }
    }
    
    final Class146 method3501(final int n, final int n2, final int n3, final Class97 class97, final int n4, final ha ha, final int n5, final int n6, final Class313 class98) {
        try {
            if (this.anIntArray2428 != null && n5 > 1) {
                int n7 = -1;
                for (int n8 = 0; ~n8 > -11; ++n8) {
                    if (~n5 <= ~this.anIntArray2454[n8] && ~this.anIntArray2454[n8] != -1) {
                        n7 = this.anIntArray2428[n8];
                    }
                }
                if (~n7 != 0x0) {
                    return this.aClass205_2419.method2714(n7, (byte)(-119)).method3501(n, n2, n3, class97, n4, ha, 1, n6, class98);
                }
            }
            int method1777 = n2;
            if (class97 != null) {
                method1777 |= class97.method932(true, n3, true, n4);
            }
            Class146 class99;
            synchronized (this.aClass205_2419.aClass79_1560) {
                class99 = (Class146)this.aClass205_2419.aClass79_1560.method802(-123, this.anInt2468 | ha.anInt937 << -1658708323);
                if (n6 != 128) {
                    this.anInt2434 = -112;
                }
            }
            if (class99 == null || ~ha.c(class99.ua(), method1777) != -1) {
                if (class99 != null) {
                    method1777 = ha.method1777(method1777, class99.ua());
                }
                int n9 = method1777;
                if (this.aShortArray2460 != null) {
                    n9 |= 0x8000;
                }
                if (this.aShortArray2430 != null || class98 != null) {
                    n9 |= 0x4000;
                }
                if (this.anInt2451 != 128) {
                    n9 |= 0x1;
                }
                if (~this.anInt2451 != 0xFFFFFF7F) {
                    n9 |= 0x2;
                }
                if (~this.anInt2451 != 0xFFFFFF7F) {
                    n9 |= 0x4;
                }
                final Class178 method1778 = Class98_Sub6.method981(0, -9252, this.aClass205_2419.aClass207_1556, this.anInt2431);
                if (method1778 == null) {
                    return null;
                }
                if (~method1778.anInt1387 > -14) {
                    method1778.method2592(13746, 2);
                }
                class99 = ha.method1790(method1778, n9, this.aClass205_2419.anInt1564, this.anInt2452 + 64, 850 - -this.anInt2422);
                if (this.anInt2451 != 128 || this.anInt2429 != 128 || ~this.anInt2415 != 0xFFFFFF7F) {
                    class99.O(this.anInt2451, this.anInt2429, this.anInt2415);
                }
                if (this.aShortArray2430 != null) {
                    for (int n10 = 0; this.aShortArray2430.length > n10; ++n10) {
                        if (this.aByteArray2457 == null || ~this.aByteArray2457.length >= ~n10) {
                            class99.ia(this.aShortArray2430[n10], this.aShortArray2442[n10]);
                        }
                        else {
                            class99.ia(this.aShortArray2430[n10], Class338.aShortArray2833[this.aByteArray2457[n10] & 0xFF]);
                        }
                    }
                }
                if (this.aShortArray2460 != null) {
                    for (int n11 = 0; ~this.aShortArray2460.length < ~n11; ++n11) {
                        class99.aa(this.aShortArray2460[n11], this.aShortArray2456[n11]);
                    }
                }
                if (class98 != null) {
                    for (int n12 = 0; ~n12 > -6; ++n12) {
                        for (int n13 = 0; ~Class61.aShortArrayArrayArray478.length < ~n13; ++n13) {
                            if (~class98.anIntArray2683[n12] > ~Class61.aShortArrayArrayArray478[n13][n12].length) {
                                class99.ia(Class98_Sub10_Sub11.aShortArrayArray5597[n13][n12], Class61.aShortArrayArrayArray478[n13][n12][class98.anIntArray2683[n12]]);
                            }
                        }
                    }
                }
                class99.s(method1777);
                synchronized (this.aClass205_2419.aClass79_1560) {
                    this.aClass205_2419.aClass79_1560.method805(this.anInt2468 | ha.anInt937 << -1883478627, class99, (byte)(-80));
                }
            }
            if (class97 != null) {
                class99 = class97.method937(n4, n, method1777, 42, class99, n3);
            }
            class99.s(n2);
            return class99;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sea.N(" + n + ',' + n2 + ',' + n3 + ',' + ((class97 != null) ? "{...}" : "null") + ',' + n4 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n5 + ',' + n6 + ',' + ((class98 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class297() {
        this.anInt2422 = 0;
        this.anInt2414 = -1;
        this.anInt2433 = -1;
        this.anInt2434 = -1;
        this.anInt2423 = -1;
        this.anInt2435 = 0;
        this.anInt2437 = 0;
        this.anInt2417 = -1;
        this.anInt2424 = -1;
        this.anInt2440 = -1;
        this.anInt2451 = 128;
        this.anInt2448 = 0;
        this.anInt2429 = 128;
        this.anInt2439 = -1;
        this.anInt2416 = 0;
        this.anInt2418 = -1;
        this.anInt2458 = -1;
        this.anInt2421 = -1;
        this.anInt2438 = -1;
        this.anInt2445 = 0;
        this.anInt2415 = 128;
        this.anInt2441 = 0;
        this.anInt2455 = -1;
        this.anInt2459 = -1;
        this.aBoolean2420 = false;
        this.anInt2452 = 0;
        this.anInt2425 = 0;
        this.aBoolean2461 = false;
        this.anInt2462 = -1;
        this.anInt2444 = -1;
        this.anInt2449 = -1;
        this.anInt2465 = 2000;
        this.aString2450 = "null";
        this.anInt2469 = 0;
        this.anInt2463 = -1;
        this.anInt2453 = -1;
        this.anInt2466 = -1;
        this.anInt2447 = 0;
        this.anInt2426 = 0;
        this.anInt2471 = -1;
        this.anInt2427 = 0;
        this.anInt2432 = -1;
        this.anInt2474 = 0;
        this.anInt2464 = 0;
        this.anInt2467 = 0;
        this.anInt2475 = 1;
        this.anInt2476 = 0;
        this.anInt2472 = -1;
    }
}
