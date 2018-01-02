// 
// Decompiled by Procyon v0.5.30
// 

final class Class1
{
    private int anInt52;
    private int anInt53;
    boolean aBoolean54;
    Class98_Sub5 aClass98_Sub5_55;
    private int anInt56;
    int anInt57;
    boolean aBoolean58;
    short[] aShortArray59;
    private int anInt60;
    private int anInt61;
    int anInt62;
    static Class6 aClass6_63;
    static Class128 aClass128_64;
    static int[] anIntArray65;
    static boolean aBoolean66;
    static Class207 aClass207_67;
    
    static final int method160(final int n, final byte b, final int n2) {
        try {
            if (b != -101) {
                Class1.aBoolean66 = true;
            }
            if (~n2 == 0xFFFFFFFE || ~n2 == 0xFFFFFFFC) {
                return Class98_Sub10_Sub6.anIntArray5559[n & 0x3];
            }
            return Class299_Sub2.anIntArray5301[0x3 & n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.D(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    final void method161(final boolean b, final int n, final int n2) {
        try {
            if (n2 < 61) {
                this.method164(96);
            }
            int n3;
            if (b) {
                n3 = 2048;
            }
            else {
                final int n4 = 0x7FF & this.anInt61 + n * this.anInt60 / 50;
                final int anInt53 = this.anInt53;
                if (anInt53 != 1) {
                    if (~anInt53 != 0xFFFFFFFC) {
                        if (~anInt53 != 0xFFFFFFFB) {
                            if (~anInt53 != 0xFFFFFFFD) {
                                if (anInt53 == 5) {
                                    n3 = ((n4 < 1024) ? n4 : (-n4 + 2048)) << 1254570465;
                                }
                                else {
                                    n3 = 2048;
                                }
                            }
                            else {
                                n3 = n4;
                            }
                        }
                        else {
                            n3 = n4 >> -322029302 << 2017264779;
                        }
                    }
                    else {
                        n3 = Class287_Sub1.anIntArray3421[n4] >> 1875641633;
                    }
                }
                else {
                    n3 = (Class284_Sub2_Sub2.anIntArray6200[n4 << -516328477] >> -967825788) + 1024;
                }
            }
            this.aClass98_Sub5_55.method959((this.anInt56 + (n3 * this.anInt52 >> 1801072939)) / 2048.0f, 57);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.F(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method162(final int n, final byte b, final int n2) {
        try {
            if (b >= -79) {
                Class1.aBoolean66 = true;
            }
            return ~n2 <= -1 && n >= 0 && Class281.aByteArrayArrayArray2117[1].length > n2 && ~n > ~Class281.aByteArrayArrayArray2117[1][n2].length && (0x2 & Class281.aByteArrayArrayArray2117[1][n2][n]) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.A(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    public static void method163(final int n) {
        try {
            if (n <= 25) {
                Class1.aClass128_64 = null;
            }
            Class1.aClass207_67 = null;
            Class1.aClass6_63 = null;
            Class1.aClass128_64 = null;
            Class1.anIntArray65 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.B(" + n + ')');
        }
    }
    
    private final void method164(final int n) {
        try {
            final int anInt62 = this.anInt62;
            if (anInt62 != 2) {
                if (anInt62 != 3) {
                    if (anInt62 != 4) {
                        if (~anInt62 != 0xFFFFFFFA) {
                            if (anInt62 != 12) {
                                if (anInt62 != 13) {
                                    if (anInt62 != 10) {
                                        if (anInt62 != 11) {
                                            if (~anInt62 != 0xFFFFFFF9) {
                                                if (anInt62 != 7) {
                                                    if (~anInt62 != 0xFFFFFFF7) {
                                                        if (anInt62 != 9) {
                                                            if (~anInt62 != 0xFFFFFFF1) {
                                                                if (~anInt62 != 0xFFFFFFF0) {
                                                                    if (~anInt62 == 0xFFFFFFEF) {
                                                                        this.anInt53 = 1;
                                                                        this.anInt52 = 256;
                                                                        this.anInt60 = 8192;
                                                                        this.anInt56 = 1792;
                                                                    }
                                                                    else {
                                                                        this.anInt52 = 2048;
                                                                        this.anInt53 = 0;
                                                                        this.anInt56 = 0;
                                                                        this.anInt60 = 2048;
                                                                    }
                                                                }
                                                                else {
                                                                    this.anInt53 = 1;
                                                                    this.anInt60 = 4096;
                                                                    this.anInt56 = 1536;
                                                                    this.anInt52 = 512;
                                                                }
                                                            }
                                                            else {
                                                                this.anInt56 = 1280;
                                                                this.anInt53 = 1;
                                                                this.anInt60 = 2048;
                                                                this.anInt52 = 768;
                                                            }
                                                        }
                                                        else {
                                                            this.anInt60 = 4096;
                                                            this.anInt53 = 3;
                                                            this.anInt56 = 1024;
                                                            this.anInt52 = 1024;
                                                        }
                                                    }
                                                    else {
                                                        this.anInt52 = 1024;
                                                        this.anInt53 = 3;
                                                        this.anInt56 = 1024;
                                                        this.anInt60 = 2048;
                                                    }
                                                }
                                                else {
                                                    this.anInt56 = 1280;
                                                    this.anInt53 = 3;
                                                    this.anInt60 = 4096;
                                                    this.anInt52 = 768;
                                                }
                                            }
                                            else {
                                                this.anInt52 = 768;
                                                this.anInt56 = 1280;
                                                this.anInt53 = 3;
                                                this.anInt60 = 2048;
                                            }
                                        }
                                        else {
                                            this.anInt52 = 512;
                                            this.anInt53 = 3;
                                            this.anInt56 = 1536;
                                            this.anInt60 = 4096;
                                        }
                                    }
                                    else {
                                        this.anInt52 = 512;
                                        this.anInt56 = 1536;
                                        this.anInt53 = 3;
                                        this.anInt60 = 2048;
                                    }
                                }
                                else {
                                    this.anInt53 = 2;
                                    this.anInt56 = 0;
                                    this.anInt52 = 2048;
                                    this.anInt60 = 8192;
                                }
                            }
                            else {
                                this.anInt53 = 2;
                                this.anInt56 = 0;
                                this.anInt60 = 2048;
                                this.anInt52 = 2048;
                            }
                        }
                        else {
                            this.anInt56 = 0;
                            this.anInt53 = 4;
                            this.anInt52 = 2048;
                            this.anInt60 = 8192;
                        }
                    }
                    else {
                        this.anInt52 = 2048;
                        this.anInt53 = 4;
                        this.anInt60 = 2048;
                        this.anInt56 = 0;
                    }
                }
                else {
                    this.anInt56 = 0;
                    this.anInt52 = 2048;
                    this.anInt53 = 1;
                    this.anInt60 = 4096;
                }
            }
            else {
                this.anInt52 = 2048;
                this.anInt60 = 2048;
                this.anInt53 = 1;
                this.anInt56 = 0;
            }
            if (n > 0) {
                method163(-26);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.E(" + n + ')');
        }
    }
    
    static final void method165(final byte b, final Class207 class207) {
        try {
            aa.anInt51 = class207.method2750((byte)(-107), "hitmarks");
            Class140.anInt3243 = class207.method2750((byte)(-106), "hitbar_default");
            Class65.anInt503 = class207.method2750((byte)(-71), "timerbar_default");
            if (b >= -95) {
                Class1.aClass207_67 = null;
            }
            Class260.anInt3259 = class207.method2750((byte)(-61), "headicons_pk");
            Class251.anInt1916 = class207.method2750((byte)(-92), "headicons_prayer");
            Class319.anInt2706 = class207.method2750((byte)(-67), "hint_headicons");
            Class76_Sub2.anInt3728 = class207.method2750((byte)(-68), "hint_mapmarkers");
            Class226.anInt1706 = class207.method2750((byte)(-93), "mapflag");
            Class39.anInt363 = class207.method2750((byte)(-55), "cross");
            OutputStream_Sub1.anInt37 = class207.method2750((byte)(-87), "mapdots");
            Class243.anInt1852 = class207.method2750((byte)(-67), "scrollbar");
            Class98_Sub31_Sub4.anInt5860 = class207.method2750((byte)(-68), "name_icons");
            Class75.anInt583 = class207.method2750((byte)(-94), "floorshadows");
            Class111_Sub2.anInt4695 = class207.method2750((byte)(-123), "compass");
            Class264.anInt1972 = class207.method2750((byte)(-50), "otherlevel");
            Class76.anInt588 = class207.method2750((byte)(-101), "hint_mapedge");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.C(" + b + ',' + ((class207 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method166(final int anInt60, final int anInt61, final int anInt62, final byte b, final int anInt63) {
        try {
            this.anInt53 = anInt63;
            if (b <= -81) {
                this.anInt56 = anInt62;
                this.anInt52 = anInt61;
                this.anInt60 = anInt60;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.H(" + anInt60 + ',' + anInt61 + ',' + anInt62 + ',' + b + ',' + anInt63 + ')');
        }
    }
    
    private final void method167(final byte b, final ha ha, final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.aClass98_Sub5_55 = ha.method1765(n3, n5, n, n4, n2, 1.0f);
            if (b >= -21) {
                this.method164(-124);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.G(" + b + ',' + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    protected Class1() {
        try {
            if (Class287_Sub1.anIntArray3421 == null) {
                Class358.method3887(110);
            }
            this.method164(-48);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.<init>()");
        }
    }
    
    Class1(final ha ha, final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (Class287_Sub1.anIntArray3421 == null) {
                Class358.method3887(110);
            }
            this.anInt57 = class98_Sub22.readUnsignedByte((byte)(-105));
            this.aBoolean54 = (~(0x10 & this.anInt57) != -1);
            this.aBoolean58 = (~(this.anInt57 & 0x8) != -1);
            this.anInt57 &= 0x7;
            final int n2 = class98_Sub22.readShort((byte)127) << n;
            final int n3 = class98_Sub22.readShort((byte)127) << n;
            final int n4 = class98_Sub22.readShort((byte)127) << n;
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)26);
            final int n5 = 1 + 2 * unsignedByte;
            this.aShortArray59 = new short[n5];
            for (int n6 = 0; ~n6 > ~this.aShortArray59.length; ++n6) {
                final short n7 = (short)class98_Sub22.readShort((byte)127);
                int n8 = n7 >>> -502532568;
                int n9 = n7 & 0xFF;
                if (~n8 <= ~n5) {
                    n8 = n5 - 1;
                }
                if (n9 > -n8 + n5) {
                    n9 = -n8 + n5;
                }
                this.aShortArray59[n6] = (short)Class41.method366(n8 << 1030619432, n9);
            }
            final int n10 = (unsignedByte << Class151_Sub8.anInt5015) + Class207.anInt1577;
            final int n11 = (Class208.anIntArray1579 != null) ? Class208.anIntArray1579[class98_Sub22.readShort((byte)127)] : Class221.anIntArray1665[Class111_Sub2.method2117(class98_Sub22.readShort((byte)127), 61) & 0xFFFF];
            final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-110));
            this.anInt62 = (0x1F & unsignedByte2);
            this.anInt61 = (0x700 & unsignedByte2 << -997312285);
            if (this.anInt62 != 31) {
                this.method164(-31);
            }
            this.method167((byte)(-56), ha, n3, n11, n2, n10, n4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ab.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class1.aClass6_63 = new Class6("RC", 1);
        Class1.anIntArray65 = new int[5];
        Class1.aClass128_64 = new Class128();
    }
}
