// 
// Decompiled by Procyon v0.5.30
// 

final class Class92
{
    private int anInt726;
    int anInt727;
    int[] anIntArray728;
    int anInt729;
    int anInt730;
    int anInt731;
    boolean aBoolean732;
    int anInt733;
    int anInt734;
    int[] anIntArray735;
    boolean aBoolean736;
    int anInt737;
    static IncomingOpcode currentIncommingOpcode;
    int anInt739;
    private int anInt740;
    int anInt741;
    int anInt742;
    int anInt743;
    private int anInt744;
    int anInt745;
    int anInt746;
    short aShort747;
    private int anInt748;
    private int anInt749;
    int anInt750;
    private int anInt751;
    int anInt752;
    boolean aBoolean753;
    short aShort754;
    private int anInt755;
    int anInt756;
    int anInt757;
    int anInt758;
    boolean aBoolean759;
    int anInt760;
    int anInt761;
    int anInt762;
    short aShort763;
    int anInt764;
    int anInt765;
    int anInt766;
    private int anInt767;
    int[] anIntArray768;
    private int anInt769;
    int anInt770;
    int anInt771;
    int[] anIntArray772;
    private int anInt773;
    int anInt774;
    int anInt775;
    boolean aBoolean776;
    int anInt777;
    boolean aBoolean778;
    int anInt779;
    int anInt780;
    int anInt781;
    int anInt782;
    boolean aBoolean783;
    int anInt784;
    int anInt785;
    short aShort786;
    int anInt787;
    int anInt788;
    boolean aBoolean789;
    int anInt790;
    boolean aBoolean791;
    int anInt792;
    
    static final void method891(final int anInt5371, final int n) {
        try {
            Class101.anInt849 = -1;
            Class42_Sub4.anInt5371 = anInt5371;
            Class169.anInt1307 = -1;
            if (n < -63) {
                aa_Sub1.method155(-1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fp.F(" + anInt5371 + ',' + n + ')');
        }
    }
    
    static final void method892(final int n, int anInt5016, int anInt5017, int anInt5018, final boolean b, int anInt5019) {
        try {
            if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 == null) {
                Class265.aHa1974.method1760(anInt5019, anInt5018, anInt5017, -16777216, (byte)(-66), anInt5016);
            }
            else if (~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 > -1 || ~(512 * Class165.anInt1276) >= ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 || ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 > -1 || ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 <= ~(512 * Class98_Sub10_Sub7.anInt5572)) {
                Class265.aHa1974.method1760(anInt5019, anInt5018, anInt5017, -16777216, (byte)(-66), anInt5016);
            }
            else {
                ++Class64_Sub15.anInt3676;
                if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 != null && Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 - 256 * (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(0) - 1) >> 856517161 == Class269.anInt2024 && ~(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 - (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(0) * 256 - 256) >> -425076055) == ~Class246_Sub3_Sub1_Sub2.anInt6251) {
                    Class246_Sub3_Sub1_Sub2.anInt6251 = -1;
                    Class269.anInt2024 = -1;
                    Class98_Sub22.method1216(-17470);
                }
                Class98_Sub44.method1512(true);
                if (!b) {
                    Class205.method2716(-9627);
                }
                Class246_Sub1.method2966(66);
                Class151_Sub3.method2453(anInt5017, true, anInt5019, 100, anInt5018, anInt5016);
                anInt5018 = Class332_Sub2.anInt5421;
                anInt5016 = Class151_Sub8.anInt5016;
                Class16.anInt197 = Class16.anInt199;
                anInt5019 = Class215.anInt1612;
                anInt5017 = Class98_Sub31_Sub2.anInt5824;
                if (Class98_Sub46_Sub20_Sub2.anInt6319 == 1) {
                    int n2 = (int)Class119_Sub4.aFloat4740;
                    if (Class43.anInt372 >> 861735464 > n2) {
                        n2 = Class43.anInt372 >> -1370935128;
                    }
                    if (Class217.aBooleanArray3410[4] && ~n2 > ~(Class98_Sub10_Sub13.anIntArray5603[4] + 128)) {
                        n2 = 128 + Class98_Sub10_Sub13.anIntArray5603[4];
                    }
                    Class104.method1712(false, 0x3FFF & Class98_Sub10_Sub9.anInt5581 + (int)Class98_Sub22_Sub2.aFloat5794, 600 + 3 * (n2 >> 2062510403) << 989514018, Class224_Sub3_Sub1.anInt6147, anInt5018, Class201.anInt1545, n2, -200 + Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084, n ^ 0xD76));
                }
                else if (Class98_Sub46_Sub20_Sub2.anInt6319 == 4) {
                    int n3 = (int)Class119_Sub4.aFloat4740;
                    if (n3 < Class43.anInt372 >> 1907221800) {
                        n3 = Class43.anInt372 >> 107774920;
                    }
                    if (Class217.aBooleanArray3410[4] && ~(128 + Class98_Sub10_Sub13.anIntArray5603[4]) < ~n3) {
                        n3 = Class98_Sub10_Sub13.anIntArray5603[4] + 128;
                    }
                    Class104.method1712(false, (int)Class98_Sub22_Sub2.aFloat5794 & 0x3FFF, 600 - -((n3 >> 325944995) * 3) << 128688834, Class224_Sub3_Sub1.anInt6147, anInt5018, Class201.anInt1545, n3, Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, Class135.anInt1051, Class98_Sub46_Sub2_Sub2.anInt6295, 24111) - 200);
                }
                else if (~Class98_Sub46_Sub20_Sub2.anInt6319 == 0xFFFFFFFA) {
                    Class50.method484(anInt5018, n ^ 0x5301);
                }
                final int anInt5020 = Class98_Sub46_Sub10.anInt6020;
                final int anInt5021 = Class79.anInt601;
                final int anInt5022 = Class134.anInt3461;
                final int anInt5023 = Class246_Sub3_Sub4_Sub2.anInt6357;
                final int anInt5024 = Class186.anInt3424;
                for (int n4 = 0; ~n4 > -6; ++n4) {
                    if (Class217.aBooleanArray3410[n4]) {
                        final int n5 = (int)(Math.random() * (2 * aa_Sub3.anIntArray3571[n4] + 1) - aa_Sub3.anIntArray3571[n4] + Math.sin(Class212.anIntArray1597[n4] * (Class98_Sub32.anIntArray4109[n4] / 100.0)) * Class98_Sub10_Sub13.anIntArray5603[n4]);
                        if (~n4 == 0xFFFFFFFC) {
                            Class186.anInt3424 = (0x3FFF & Class186.anInt3424 + n5);
                        }
                        if (~n4 == -1) {
                            Class98_Sub46_Sub10.anInt6020 += n5 << -286964990;
                        }
                        if (n4 == 2) {
                            Class134.anInt3461 += n5 << 1958877954;
                        }
                        if (~n4 == 0xFFFFFFFB) {
                            Class246_Sub3_Sub4_Sub2.anInt6357 += n5;
                            if (Class246_Sub3_Sub4_Sub2.anInt6357 >= 1024) {
                                if (Class246_Sub3_Sub4_Sub2.anInt6357 > 3072) {
                                    Class246_Sub3_Sub4_Sub2.anInt6357 = 3072;
                                }
                            }
                            else {
                                Class246_Sub3_Sub4_Sub2.anInt6357 = 1024;
                            }
                        }
                        if (n4 == 1) {
                            Class79.anInt601 += n5 << -757066846;
                        }
                    }
                }
                if (Class98_Sub46_Sub10.anInt6020 < 0) {
                    Class98_Sub46_Sub10.anInt6020 = 0;
                }
                if ((Class366.anInt3112 << 1495907977) - 1 < Class98_Sub46_Sub10.anInt6020) {
                    Class98_Sub46_Sub10.anInt6020 = (Class366.anInt3112 << 1118361961) - 1;
                }
                if (Class134.anInt3461 < 0) {
                    Class134.anInt3461 = 0;
                }
                if (~Class134.anInt3461 < ~(-1 + (Class64_Sub9.anInt3662 << -179806359))) {
                    Class134.anInt3461 = (Class64_Sub9.anInt3662 << 2090279273) - 1;
                }
                if (n == 21337) {
                    Class246_Sub3_Sub4_Sub1.method3025((byte)72);
                    Class329.method3708(n - 21338);
                    Class265.aHa1974.KA(anInt5016, anInt5017, anInt5019 + anInt5016, anInt5018 + anInt5017);
                    Class201.method2697(n ^ 0xFFFFAE87, true);
                    if (!za_Sub2.aBoolean6079) {
                        Class265.aHa1974.ya();
                        final int anInt5025 = Class284_Sub1_Sub2.anInt6192;
                        if (s_Sub1.aClass346_5202 == null) {
                            Class265.aHa1974.GA(anInt5025);
                        }
                        else {
                            s_Sub1.aClass346_5202.method3831(Class156_Sub1.anInt3278 << -14047229, Class246_Sub3_Sub4_Sub2.anInt6357, Class186.anInt3424, anInt5017, n - 45783, anInt5016, Class265.aHa1974, anInt5018, anInt5019, anInt5025);
                        }
                    }
                    else {
                        Class156_Sub2.method2500(Class284_Sub1_Sub2.anInt6192, n - 21337);
                        if (~Class16.anInt197 != ~Class48_Sub2_Sub1.anInt5525) {
                            Class358.aBoolean3033 = true;
                        }
                        Class48_Sub2_Sub1.anInt5525 = Class16.anInt197;
                    }
                    Class128.method2224(n ^ 0xBF1);
                    Class266.aClass111_1986.method2093(Class98_Sub46_Sub10.anInt6020, Class79.anInt601, Class134.anInt3461, 0x3FFF & -Class246_Sub3_Sub4_Sub2.anInt6357, -Class186.anInt3424 & 0x3FFF, 0x3FFF & -Class308.anInt2584);
                    Class265.aHa1974.a(Class266.aClass111_1986);
                    Class265.aHa1974.DA(anInt5016 - -(anInt5019 / 2), anInt5018 / 2 + anInt5017, Class98_Sub10_Sub14.anInt5610 << -346205375, Class98_Sub10_Sub14.anInt5610 << 74627969);
                    Class260.method3208(Class98_Sub10_Sub14.anInt5610 << -1360844447, -128, Class98_Sub10_Sub14.anInt5610 << 1280299137, anInt5017 - -(anInt5018 / 2), anInt5016 + anInt5019 / 2);
                    Class98_Sub46_Sub6.method1548(0x3FFF & -Class186.anInt3424, Class79.anInt601, -Class246_Sub3_Sub4_Sub2.anInt6357 & 0x3FFF, 0x3FFF & -Class308.anInt2584, (byte)(-78), Class134.anInt3461, Class98_Sub46_Sub10.anInt6020);
                    final byte b2 = (byte)((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4058.method612((byte)126) != 2) ? 1 : ((byte)Class64_Sub15.anInt3676));
                    if (!za_Sub2.aBoolean6079) {
                        Class60.method535(Class215.anInt1614, Class98_Sub46_Sub10.anInt6020, Class79.anInt601, Class134.anInt3461, OutputStream_Sub2.aByteArrayArrayArray41, Class204.anIntArray1551, Class336.anIntArray2826, Class287.anIntArray2195, Class295.anIntArray2409, Class48_Sub1_Sub2.anIntArray5518, 1 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, b2, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 >> 1664602249, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 >> -1941412663, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub13_4063.method602((byte)121) == 0, true, Class239.aBoolean1839 ? Class16.anInt197 : -1, 0, false);
                    }
                    else {
                        Class216.method2797(-Class246_Sub3_Sub4_Sub2.anInt6357 & 0x3FFF, 0x3FFF & -Class308.anInt2584, -Class186.anInt3424 & 0x3FFF, n + 4643);
                        Class154.method2493(Class79.anInt601, 1 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, OutputStream_Sub2.aByteArrayArrayArray41, Class16.anInt197, Class98_Sub46_Sub10.anInt6020, Class48_Sub1_Sub2.anIntArray5518, ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub13_4063.method602((byte)126) == -1, Class204.anIntArray1551, b2, true, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 >> -921672887, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 >> -1397703127, Class287.anIntArray2195, Class215.anInt1614, Class295.anIntArray2409, Class134.anInt3461, Class336.anIntArray2826, true);
                    }
                    Class128.method2224(22696);
                    if (~Class177.anInt1376 == 0xFFFFFFF5) {
                        Class290.method3411(anInt5016, (byte)7, anInt5017, anInt5018, 256, 256, anInt5019);
                        Class98_Sub34.method1450(256, anInt5019, anInt5018, 256, (byte)(-124), anInt5016, anInt5017);
                        Class294.method3479(256, anInt5019, -7957, anInt5018, anInt5016, anInt5017, 256);
                        Class98_Sub46_Sub13_Sub1.method1594(anInt5017, anInt5016, anInt5018, anInt5019, (byte)116);
                    }
                    Class114.method2148();
                    Class186.anInt3424 = anInt5024;
                    Class98_Sub46_Sub10.anInt6020 = anInt5020;
                    Class134.anInt3461 = anInt5022;
                    Class246_Sub3_Sub4_Sub2.anInt6357 = anInt5023;
                    Class79.anInt601 = anInt5021;
                    if (Class98_Sub10_Sub12.aBoolean5599 && ~Class98_Sub10_Sub38.aClass135_5765.method2261(-1) == -1) {
                        Class98_Sub10_Sub12.aBoolean5599 = false;
                    }
                    if (Class98_Sub10_Sub12.aBoolean5599) {
                        Class265.aHa1974.method1760(anInt5019, anInt5018, anInt5017, -16777216, (byte)(-66), anInt5016);
                        Class246_Sub2.method2972(n - 21457, Class98_Sub46_Sub10.aClass197_6019, Class195.aClass43_1499, false, Class265.aHa1974, Class309.aClass309_2598.method3615(Class374.anInt3159, (byte)25));
                    }
                    Class201.method2697(-546, false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fp.D(" + n + ',' + anInt5016 + ',' + anInt5017 + ',' + anInt5018 + ',' + b + ',' + anInt5019 + ')');
        }
    }
    
    static final boolean method893(final int n) {
        try {
            final Class98_Sub19 class98_Sub19 = (Class98_Sub19)Class186.aClass148_3428.method2418(32);
            if (class98_Sub19 == null) {
                return false;
            }
            for (int n2 = 0; ~class98_Sub19.anInt3960 < ~n2; ++n2) {
                if (class98_Sub19.aClass143Array3962[n2] != null && class98_Sub19.aClass143Array3962[n2].anInt1163 == 0) {
                    return false;
                }
                if (class98_Sub19.aClass143Array3961[n2] != null && ~class98_Sub19.aClass143Array3961[n2].anInt1163 == -1) {
                    return false;
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fp.B(" + n + ')');
        }
    }
    
    final void method894(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-128));
                if (~unsignedByte == -1) {
                    break;
                }
                this.method895(class98_Sub22, unsignedByte, (byte)(-112));
            }
            if (n >= -20) {
                method892(113, 42, 109, 50, true, -116);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fp.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method895(final Class98_Sub22 class98_Sub22, final int n, final byte b) {
        try {
            if (n == 1) {
                this.aShort747 = (short)class98_Sub22.readShort((byte)127);
                this.aShort786 = (short)class98_Sub22.readShort((byte)127);
                this.aShort763 = (short)class98_Sub22.readShort((byte)127);
                this.aShort754 = (short)class98_Sub22.readShort((byte)127);
                final int n2 = 3;
                this.aShort763 <<= (short)n2;
                this.aShort786 <<= (short)n2;
                this.aShort747 <<= (short)n2;
                this.aShort754 <<= (short)n2;
            }
            else if (~n == 0xFFFFFFFD) {
                class98_Sub22.readUnsignedByte((byte)47);
            }
            else if (n == 3) {
                this.anInt770 = class98_Sub22.readInt(-2);
                this.anInt731 = class98_Sub22.readInt(-2);
            }
            else if (n != 4) {
                if (~n == 0xFFFFFFFA) {
                    final int n3 = class98_Sub22.readShort((byte)127) << -577553204 << 1586483106;
                    this.anInt788 = n3;
                    this.anInt780 = n3;
                }
                else if (n != 6) {
                    if (n == 7) {
                        this.anInt766 = class98_Sub22.readShort((byte)127);
                        this.anInt787 = class98_Sub22.readShort((byte)127);
                    }
                    else if (n != 8) {
                        if (n == 9) {
                            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)41);
                            this.anIntArray728 = new int[unsignedByte];
                            for (int i = 0; i < unsignedByte; ++i) {
                                this.anIntArray728[i] = class98_Sub22.readShort((byte)127);
                            }
                        }
                        else if (~n == 0xFFFFFFF5) {
                            final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)121);
                            this.anIntArray772 = new int[unsignedByte2];
                            for (int n4 = 0; ~unsignedByte2 < ~n4; ++n4) {
                                this.anIntArray772[n4] = class98_Sub22.readShort((byte)127);
                            }
                        }
                        else if (~n != 0xFFFFFFF3) {
                            if (n == 13) {
                                this.anInt782 = class98_Sub22.readSignedByte((byte)(-19));
                            }
                            else if (n == 14) {
                                this.anInt784 = class98_Sub22.readShort((byte)127);
                            }
                            else if (~n == 0xFFFFFFF0) {
                                this.anInt729 = class98_Sub22.readShort((byte)127);
                            }
                            else if (~n != 0xFFFFFFEF) {
                                if (~n == 0xFFFFFFEE) {
                                    this.anInt764 = class98_Sub22.readShort((byte)127);
                                }
                                else if (n != 18) {
                                    if (~n == 0xFFFFFFEC) {
                                        this.anInt752 = class98_Sub22.readUnsignedByte((byte)(-111));
                                    }
                                    else if (n == 20) {
                                        this.anInt773 = class98_Sub22.readUnsignedByte((byte)107);
                                    }
                                    else if (n == 21) {
                                        this.anInt767 = class98_Sub22.readUnsignedByte((byte)68);
                                    }
                                    else if (~n != 0xFFFFFFE9) {
                                        if (~n == 0xFFFFFFE8) {
                                            this.anInt748 = class98_Sub22.readUnsignedByte((byte)(-119));
                                        }
                                        else if (~n == 0xFFFFFFE7) {
                                            this.aBoolean759 = false;
                                        }
                                        else if (n != 25) {
                                            if (n != 26) {
                                                if (n != 27) {
                                                    if (n == 28) {
                                                        this.anInt749 = class98_Sub22.readUnsignedByte((byte)(-117));
                                                    }
                                                    else if (n != 29) {
                                                        if (n != 30) {
                                                            if (n == 31) {
                                                                this.anInt780 = class98_Sub22.readShort((byte)127) << -1910098324 << 660699586;
                                                                this.anInt788 = class98_Sub22.readShort((byte)127) << 1930543596 << 296157090;
                                                            }
                                                            else if (~n != 0xFFFFFFDF) {
                                                                if (n == 33) {
                                                                    this.aBoolean789 = true;
                                                                }
                                                                else if (n == 34) {
                                                                    this.aBoolean776 = false;
                                                                }
                                                            }
                                                            else {
                                                                this.aBoolean778 = false;
                                                            }
                                                        }
                                                        else {
                                                            this.aBoolean791 = true;
                                                        }
                                                    }
                                                    else {
                                                        class98_Sub22.readUShort(false);
                                                    }
                                                }
                                                else {
                                                    this.anInt775 = class98_Sub22.readShort((byte)127) << -1135326388 << 1935821730;
                                                }
                                            }
                                            else {
                                                this.aBoolean753 = false;
                                            }
                                        }
                                        else {
                                            final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)(-124));
                                            this.anIntArray735 = new int[unsignedByte3];
                                            for (int n5 = 0; ~unsignedByte3 < ~n5; ++n5) {
                                                this.anIntArray735[n5] = class98_Sub22.readShort((byte)127);
                                            }
                                        }
                                    }
                                    else {
                                        this.anInt745 = class98_Sub22.readInt(-2);
                                    }
                                }
                                else {
                                    this.anInt760 = class98_Sub22.readInt(-2);
                                }
                            }
                            else {
                                this.aBoolean732 = (~class98_Sub22.readUnsignedByte((byte)13) == 0xFFFFFFFE);
                                this.anInt746 = class98_Sub22.readShort((byte)127);
                                this.anInt762 = class98_Sub22.readShort((byte)127);
                                this.aBoolean783 = (class98_Sub22.readUnsignedByte((byte)(-120)) == 1);
                            }
                        }
                        else {
                            this.anInt774 = class98_Sub22.readSignedByte((byte)(-19));
                        }
                    }
                    else {
                        this.anInt750 = class98_Sub22.readShort((byte)127);
                        this.anInt790 = class98_Sub22.readShort((byte)127);
                    }
                }
                else {
                    this.anInt726 = class98_Sub22.readInt(-2);
                    this.anInt744 = class98_Sub22.readInt(-2);
                }
            }
            else {
                this.anInt739 = class98_Sub22.readUnsignedByte((byte)81);
                this.anInt792 = class98_Sub22.readSignedByte((byte)(-19));
            }
            if (b >= -101) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fp.C(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    public static void method896(final int n) {
        try {
            if (n != -1025810040) {
                method891(-100, -83);
            }
            Class92.currentIncommingOpcode = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fp.G(" + n + ')');
        }
    }
    
    final void method897(final byte b) {
        try {
            if (~this.anInt774 < 1 || this.anInt782 > -2) {
                this.aBoolean736 = true;
            }
            this.anInt741 = (this.anInt726 & 0xFFC52C) >> -731747568;
            this.anInt751 = (0xFF & this.anInt744 >> -1356228592);
            this.anInt730 = -this.anInt741 + this.anInt751;
            this.anInt740 = (0xFF9A & this.anInt744) >> 1249635720;
            this.anInt757 = (this.anInt726 & 0xFF64) >> 547175144;
            this.anInt734 = -this.anInt757 + this.anInt740;
            this.anInt771 = (this.anInt726 & 0xFF);
            this.anInt755 = (0xFF & this.anInt744);
            this.anInt769 = (0xFF & this.anInt744 >> 1155880536);
            if (b > 56) {
                this.anInt737 = this.anInt755 - this.anInt771;
                this.anInt756 = (this.anInt726 >> 328179352 & 0xFF);
                this.anInt765 = -this.anInt756 + this.anInt769;
                if (this.anInt760 != 0) {
                    this.anInt761 = this.anInt773 * this.anInt787 / 100;
                    this.anInt758 = this.anInt787 * this.anInt767 / 100;
                    if (this.anInt761 == 0) {
                        this.anInt761 = 1;
                    }
                    this.anInt743 = (-this.anInt741 + (-(this.anInt730 / 2) + (this.anInt760 >> 1956144528 & 0xFF)) << -1025810040) / this.anInt761;
                    this.anInt733 = (-(this.anInt734 / 2) + -this.anInt757 + (this.anInt760 >> -21137720 & 0xFF) << -1200703608) / this.anInt761;
                    this.anInt727 = (-this.anInt771 + (-(this.anInt737 / 2) + (0xFF & this.anInt760)) << -178823608) / this.anInt761;
                    if (~this.anInt758 == -1) {
                        this.anInt758 = 1;
                    }
                    this.anInt743 += ((~this.anInt743 >= -1) ? 4 : -4);
                    this.anInt733 += ((this.anInt733 <= 0) ? 4 : -4);
                    this.anInt727 += ((this.anInt727 <= 0) ? 4 : -4);
                    this.anInt779 = ((this.anInt760 >> -686226984 & 0xFF) - (this.anInt765 / 2 + this.anInt756) << -121018712) / this.anInt758;
                    this.anInt779 += ((this.anInt779 <= 0) ? 4 : -4);
                }
                if (this.anInt775 != -1) {
                    this.anInt777 = this.anInt749 * this.anInt787 / 100;
                    if (~this.anInt777 == -1) {
                        this.anInt777 = 1;
                    }
                    this.anInt742 = (-this.anInt780 + (-((this.anInt788 - this.anInt780) / 2) + this.anInt775)) / this.anInt777;
                }
                if (~this.anInt745 != 0x0) {
                    this.anInt785 = this.anInt787 * this.anInt748 / 100;
                    if (~this.anInt785 == -1) {
                        this.anInt785 = 1;
                    }
                    this.anInt781 = (this.anInt745 - (-this.anInt770 + this.anInt731) / 2 - this.anInt770) / this.anInt785;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fp.H(" + b + ')');
        }
    }
    
    static final void method898(final boolean b, final byte[][] array, final Class305_Sub1 class305_Sub1) {
        try {
            if (b) {
                method892(70, -39, 115, -109, false, -73);
            }
            for (int length = client.aByteArrayArray3551.length, n = 0; ~n > ~length; ++n) {
                final byte[] array2 = array[n];
                if (array2 != null) {
                    final int n2 = (Class121.anIntArray1006[n] >> 1369954824) * 64 - Class272.anInt2038;
                    final int n3 = -aa_Sub2.anInt3562 + 64 * (Class121.anIntArray1006[n] & 0xFF);
                    Class128.method2224(22696);
                    class305_Sub1.method3591(Class167.aClass243Array1281, n3, (byte)(-19), array2, Class265.aHa1974, n2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fp.E(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + ((class305_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class92() {
        this.anInt739 = 0;
        this.anInt729 = -1;
        this.anInt749 = 100;
        this.anInt748 = 100;
        this.aBoolean732 = true;
        this.aBoolean753 = true;
        this.anInt764 = -1;
        this.aBoolean736 = false;
        this.anInt762 = -1;
        this.aBoolean759 = true;
        this.anInt752 = 0;
        this.anInt773 = 100;
        this.aBoolean778 = true;
        this.anInt774 = -2;
        this.anInt745 = -1;
        this.anInt784 = 0;
        this.anInt746 = -1;
        this.anInt775 = -1;
        this.anInt782 = -2;
        this.anInt767 = 100;
        this.aBoolean776 = true;
        this.aBoolean783 = true;
        this.aBoolean791 = false;
        this.aBoolean789 = false;
    }
    
    static {
        Class92.currentIncommingOpcode = null;
    }
}
