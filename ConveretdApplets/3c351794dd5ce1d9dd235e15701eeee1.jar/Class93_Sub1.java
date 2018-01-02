// 
// Decompiled by Procyon v0.5.30
// 

class Class93_Sub1 extends Class93
{
    static int anInt5477;
    int anInt5478;
    static boolean aBoolean5479;
    int anInt5480;
    int anInt5481;
    static IncomingOpcode aClass58_5482;
    int anInt5483;
    int anInt5484;
    int anInt5485;
    static int anInt5486;
    static int[][] anIntArrayArray5487;
    static int anInt5488;
    static int anInt5489;
    
    public static void method902(final int n) {
        try {
            Class93_Sub1.anIntArrayArray5487 = null;
            if (n != 2) {
                method902(45);
            }
            Class93_Sub1.aClass58_5482 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ij.I(" + n + ')');
        }
    }
    
    static final int method903(final byte b) {
        try {
            if (~Class16.anInt196 == -1) {
                Class102.aClass102_862.method1707((byte)1, new Class54("jaclib"));
                if (Class102.aClass102_862.method1709(107).method51((byte)127) != 100) {
                    return 1;
                }
                if (!((Class54)Class102.aClass102_862.method1709(94)).method504(true)) {
                    Class315.aClient3529.method82(-21568);
                }
                Class16.anInt196 = 1;
            }
            if (~Class16.anInt196 == 0xFFFFFFFE) {
                Class194.aClass102Array1496 = Class102.method1704(4);
                Class102.aClass102_860.method1707((byte)1, new PlayerUpdate(Class253.aClass207_1932));
                Class102.aClass102_863.method1707((byte)1, new Class54("jaggl"));
                Class102.aClass102_864.method1707((byte)1, new Class54("jagdx"));
                Class102.aClass102_865.method1707((byte)1, new Class54("jagmisc"));
                Class102.aClass102_866.method1707((byte)1, new Class54("sw3d"));
                Class102.aClass102_867.method1707((byte)1, new Class54("hw3d"));
                Class102.aClass102_868.method1707((byte)1, new Class54("jagtheora"));
                Class102.aClass102_869.method1707((byte)1, new PlayerUpdate(Class212.aClass207_1603));
                Class102.aClass102_870.method1707((byte)1, new PlayerUpdate(Class64_Sub20.aClass207_3695));
                Class102.aClass102_871.method1707((byte)1, new PlayerUpdate(client.aClass207_3549));
                Class102.aClass102_872.method1707((byte)1, new PlayerUpdate(Class375.aClass207_3167));
                Class102.aClass102_873.method1707((byte)1, new PlayerUpdate(Class98_Sub10_Sub24.aClass207_5668));
                Class102.aClass102_874.method1707((byte)1, new PlayerUpdate(Class234.aClass207_1751));
                Class102.aClass102_875.method1707((byte)1, new PlayerUpdate(Class208.aClass207_1581));
                Class102.aClass102_876.method1707((byte)1, new PlayerUpdate(Class246_Sub3.aClass207_5087));
                Class102.aClass102_877.method1707((byte)1, new PlayerUpdate(PlayerUpdateMask.aClass207_525));
                Class102.aClass102_878.method1707((byte)1, new PlayerUpdate(Class98_Sub46_Sub19.aClass207_6067));
                Class102.aClass102_879.method1707((byte)1, new PlayerUpdate(Class81.aClass207_622));
                Class102.aClass102_880.method1707((byte)1, new PlayerUpdate(Class322.aClass207_2714));
                Class102.aClass102_881.method1707((byte)1, new PlayerUpdate(Class245.aClass207_1864));
                Class102.aClass102_882.method1707((byte)1, new PlayerUpdate(Class111_Sub3.aClass207_4715));
                Class102.aClass102_883.method1707((byte)1, new Class180(Class98_Sub40.aClass207_4198, "huffman"));
                Class102.aClass102_884.method1707((byte)1, new PlayerUpdate(Class64_Sub20.aClass207_3697));
                Class102.aClass102_885.method1707((byte)1, new PlayerUpdate(Class52.aClass207_3494));
                Class102.aClass102_886.method1707((byte)1, new PlayerUpdate(Class36.aClass207_348));
                Class102.aClass102_887.method1707((byte)1, new Class217(Class257.aClass207_1947, "details"));
                for (int i = 0; i < Class194.aClass102Array1496.length; ++i) {
                    if (Class194.aClass102Array1496[i].method1709(114) == null) {
                        throw new RuntimeException();
                    }
                }
                int anInt3431 = 0;
                final Class102[] aClass102Array1496 = Class194.aClass102Array1496;
                for (int n = 0; aClass102Array1496.length > n; ++n) {
                    final Class102 class102 = aClass102Array1496[n];
                    anInt3431 += class102.method1709(94).method51((byte)127) * class102.method1705(76) / 100;
                }
                Class16.anInt196 = 2;
                Class186.anInt3431 = anInt3431;
            }
            if (Class194.aClass102Array1496 == null) {
                return 100;
            }
            int n2 = 0;
            if (b > -116) {
                method902(-68);
            }
            int n3 = 0;
            boolean b2 = true;
            final Class102[] aClass102Array1497 = Class194.aClass102Array1496;
            for (int n4 = 0; ~n4 > ~aClass102Array1497.length; ++n4) {
                final Class102 class103 = aClass102Array1497[n4];
                final int method1705 = class103.method1705(68);
                final int method1706 = class103.method1709(97).method51((byte)127);
                n3 += method1706 * method1705 / 100;
                if (method1706 < 100) {
                    b2 = false;
                }
                n2 += method1705;
            }
            if (b2) {
                if (!((Class54)Class102.aClass102_865.method1709(118)).method504(true)) {
                    Class315.aClient3529.method80(0);
                }
                if (!((Class54)Class102.aClass102_868.method1709(-83)).method504(true)) {
                    Class372.aBoolean3152 = Class315.aClient3529.method83(true);
                }
                Class194.aClass102Array1496 = null;
            }
            final int n5 = n3 - Class186.anInt3431;
            final int n6 = n2 - Class186.anInt3431;
            int n7 = (n6 <= 0) ? 100 : (n5 * 100 / n6);
            if (!b2 && n7 > 99) {
                n7 = 99;
            }
            return n7;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ij.H(" + b + ')');
        }
    }
    
    Class93_Sub1(final Class63 class63, final Class110 class64, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int anInt5483, final int anInt5484, final int anInt5485, final int anInt5486, final int anInt5487, final int anInt5488) {
        super(class63, class64, n, n2, n3, n4, n5, n6, n7);
        try {
            this.anInt5478 = anInt5487;
            this.anInt5484 = anInt5488;
            this.anInt5480 = anInt5484;
            this.anInt5485 = anInt5485;
            this.anInt5483 = anInt5483;
            this.anInt5481 = anInt5486;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ij.<init>(" + ((class63 != null) ? "{...}" : "null") + ',' + ((class64 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + anInt5483 + ',' + anInt5484 + ',' + anInt5485 + ',' + anInt5486 + ',' + anInt5487 + ',' + anInt5488 + ')');
        }
    }
    
    static final void method904(final byte b) {
        try {
            if (Class208.anIntArray1579 == null) {
                Class208.anIntArray1579 = new int[65536];
                final double n = 0.7 + (-0.015 + Math.random() * 0.03);
                for (int n2 = 0; ~n2 > -65537; ++n2) {
                    final double n3 = (0x3F & n2 >> -239388310) / 64.0 + 0.0078125;
                    final double n4 = 0.0625 + ((0x3A8 & n2) >> 770038727) / 8.0;
                    double n8;
                    double n7;
                    double n6;
                    final double n5 = n6 = (n7 = (n8 = (0x7F & n2) / 128.0));
                    if (n4 != 0.0) {
                        double n9;
                        if (n5 >= 0.5) {
                            n9 = -(n5 * n4) + (n4 + n5);
                        }
                        else {
                            n9 = n5 * (n4 + 1.0);
                        }
                        final double n10 = 2.0 * n5 - n9;
                        double n11 = 0.3333333333333333 + n3;
                        if (n11 > 1.0) {
                            --n11;
                        }
                        final double n12 = n3;
                        double n13 = -0.3333333333333333 + n3;
                        if (n13 < 0.0) {
                            ++n13;
                        }
                        if (n12 * 6.0 >= 1.0) {
                            if (2.0 * n12 < 1.0) {
                                n7 = n9;
                            }
                            else if (3.0 * n12 >= 2.0) {
                                n7 = n10;
                            }
                            else {
                                n7 = n10 + 6.0 * ((n9 - n10) * (0.6666666666666666 - n12));
                            }
                        }
                        else {
                            n7 = n10 + 6.0 * (n9 - n10) * n12;
                        }
                        if (6.0 * n11 >= 1.0) {
                            if (2.0 * n11 >= 1.0) {
                                if (3.0 * n11 < 2.0) {
                                    n8 = 6.0 * ((-n10 + n9) * (-n11 + 0.6666666666666666)) + n10;
                                }
                                else {
                                    n8 = n10;
                                }
                            }
                            else {
                                n8 = n9;
                            }
                        }
                        else {
                            n8 = n11 * (6.0 * (-n10 + n9)) + n10;
                        }
                        if (n13 * 6.0 >= 1.0) {
                            if (n13 * 2.0 < 1.0) {
                                n6 = n9;
                            }
                            else if (3.0 * n13 < 2.0) {
                                n6 = n10 + (-n10 + n9) * (-n13 + 0.6666666666666666) * 6.0;
                            }
                            else {
                                n6 = n10;
                            }
                        }
                        else {
                            n6 = n10 + n13 * (6.0 * (-n10 + n9));
                        }
                    }
                    Class208.anIntArray1579[n2] = ((int)(256.0 * Math.pow(n8, n)) << -13557424) + (((int)(Math.pow(n7, n) * 256.0) << 394664072) + (int)(256.0 * Math.pow(n6, n)));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ij.F(" + b + ')');
        }
    }
    
    static final boolean method905(final int n, final int n2, final int n3) {
        try {
            if (n2 > -35) {
                Class93_Sub1.anInt5477 = -88;
            }
            return (~(0x70000 & n) != -1 | IncomingOpcode.method523(n3, -1, n)) || Class238.method2919(-60, n, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ij.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final Class348[] method906(final byte b) {
        try {
            if (b <= 11) {
                return null;
            }
            return new Class348[] { Class151_Sub9.aClass348_5023, Class77_Sub1.aClass348_3801, Class4.aClass348_82, Class306.aClass348_2565, Class42_Sub3.aClass348_5363, Class98_Sub36.aClass348_4156, Class186.aClass348_3433, aa_Sub3.aClass348_3573, Class359.aClass348_3046, Class151_Sub7.aClass348_5008, Class218.aClass348_1630, Class207.aClass348_1569, Class98_Sub5_Sub1.aClass348_5534, Class238.aClass348_1834 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ij.D(" + b + ')');
        }
    }
    
    @Override
    public Class113 method70(final int n) {
        try {
            if (n != 30778) {
                method904((byte)69);
            }
            return Class98_Sub44.aClass113_4245;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ij.A(" + n + ')');
        }
    }
    
    static final void method907(final int n, final int anInt1208, final int anInt1209) {
        try {
            Class151_Sub7.anInt5005 = anInt1209;
            Class149.anInt1208 = anInt1208;
            if (Class98_Sub46.anInt4261 == 0) {
                aa_Sub1.anInt3556 = 2 * Class377.anInt3183 + Class151_Sub7.anInt5005;
                Class48_Sub1_Sub2.anInt5519 = Class111_Sub3.anInt4701 * 2 + Class149.anInt1208;
            }
            else if (Class98_Sub46.anInt4261 != 1) {
                if (Class98_Sub46.anInt4261 == 2) {
                    Class48_Sub1_Sub2.anInt5519 = Class149.anInt1208;
                    aa_Sub1.anInt3556 = Class151_Sub7.anInt5005;
                }
            }
            else {
                Class191.anInt1477 = Class98_Sub40.anInt4194 + (Class151_Sub7.anInt5005 / Class197.anInt1513 + 2);
                Class63.anInt493 = Class149.anInt1208 / Class98_Sub10_Sub38.anInt5761 + (2 + Class151.anInt1214);
                aa_Sub1.anInt3556 = Class197.anInt1513 * Class191.anInt1477;
                Class48_Sub1_Sub2.anInt5519 = Class63.anInt493 * Class98_Sub10_Sub38.anInt5761;
                Class377.anInt3183 = -Class151_Sub7.anInt5005 + aa_Sub1.anInt3556 >> 881154753;
                Class111_Sub3.anInt4701 = -Class149.anInt1208 + Class48_Sub1_Sub2.anInt5519 >> 1217660993;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ij.E(" + n + ',' + anInt1208 + ',' + anInt1209 + ')');
        }
    }
    
    static {
        Class93_Sub1.anInt5477 = 0;
        Class93_Sub1.aBoolean5479 = true;
        Class93_Sub1.aClass58_5482 = new IncomingOpcode(62, -1);
        Class93_Sub1.anInt5486 = 0;
        Class93_Sub1.anIntArrayArray5487 = new int[][] { { 6, 6 }, { 6, 6 }, { 6, 5, 5 }, { 5, 6, 5 }, { 5, 5, 6 }, { 6, 5, 5 }, { 5, 0, 4, 1 }, { 7, 7, 1, 2 }, { 7, 1, 2, 7 }, { 8, 9, 4, 0, 8, 9 }, { 0, 8, 9, 8, 9, 4 }, { 11, 0, 10, 11, 4, 2 }, { 6, 6 }, { 7, 7, 1, 2 }, { 7, 7, 1, 2 } };
        Class93_Sub1.anInt5488 = 2;
    }
}
