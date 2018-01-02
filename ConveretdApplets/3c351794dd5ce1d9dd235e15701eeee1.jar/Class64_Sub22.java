// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub22 extends Class64
{
    static int anInt3705;
    static Class294 aClass294_3706;
    
    static final void method640(final double aDouble1197, final int n) {
        try {
            if (aDouble1197 != Class148.aDouble1197) {
                for (int i = 0; i < 256; ++i) {
                    final int n2 = (int)(Math.pow(i / 255.0, aDouble1197) * 255.0);
                    Class151_Sub4.anIntArray4985[i] = ((~n2 < -256) ? 255 : n2);
                }
                Class148.aDouble1197 = aDouble1197;
            }
            if (n != 0) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.H(" + aDouble1197 + ',' + n + ')');
        }
    }
    
    Class64_Sub22(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    final int method641(final byte b) {
        try {
            if (b <= 119) {
                method644(58);
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.E(" + b + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b > 118) {
                if (super.anInt494 < 0 && super.anInt494 > 127) {
                    super.anInt494 = this.method552(0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.C(" + b + ')');
        }
    }
    
    public static void method642(final int n) {
        try {
            Class64_Sub22.aClass294_3706 = null;
            if (n < 83) {
                method642(66);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.I(" + n + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    static final void method643(final int n, final Class213 aClass213_4949) {
        try {
            if (n != -256) {
                method644(116);
            }
            Class146_Sub3.aClass213_4949 = aClass213_4949;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.D(" + n + ',' + ((aClass213_4949 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method644(final int n) {
        try {
            for (int n2 = 0; ~Class336.anInt2820 < ~n2; ++n2) {
                Class98_Sub30.aClass155Array4099[n2] = null;
            }
            Class336.anInt2820 = 0;
            for (int n3 = 0; ~Class364.anInt3103 < ~n3; ++n3) {
                for (int n4 = 0; ~Class366.anInt3112 < ~n4; ++n4) {
                    for (int i = 0; i < Class64_Sub9.anInt3662; ++i) {
                        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n3][i][n4];
                        if (class172 != null) {
                            if (class172.aShort1328 > 0) {
                                final Class172 class173 = class172;
                                class173.aShort1328 *= -1;
                            }
                            if (class172.aShort1335 > 0) {
                                final Class172 class174 = class172;
                                class174.aShort1335 *= -1;
                            }
                        }
                    }
                }
            }
            int n5 = 0;
            if (n >= -36) {
                Class64_Sub22.anInt3705 = 62;
            }
            while (Class364.anInt3103 > n5) {
                for (int n6 = 0; ~Class366.anInt3112 < ~n6; ++n6) {
                    for (int n7 = 0; Class64_Sub9.anInt3662 > n7; ++n7) {
                        final Class172 class175 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n5][n7][n6];
                        if (class175 != null) {
                            final boolean b = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n7][n6] != null && Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n7][n6].aClass172_1330 != null;
                            if (class175.aShort1335 < 0) {
                                int n8 = n6;
                                int n9 = n6;
                                final int n10 = n5;
                                final int n11 = n5;
                                Class172 class176;
                                int method3420;
                                for (class176 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n5][n7][-1 + n8], method3420 = Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n6, -12639, n7); ~n8 < -1 && class176 != null && class176.aShort1335 < 0 && ~class176.aShort1335 == ~class175.aShort1335 && ~class176.aShort1329 == ~class175.aShort1329 && ~method3420 == ~Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n8 - 1, -12639, n7) && (~(n8 - 1) >= -1 || method3420 == Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n8 - 2, -12639, n7)); --n8, class176 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n5][n7][-1 + n8]) {}
                                for (Class172 class177 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n5][n7][n9 + 1]; ~n9 > ~Class64_Sub9.anInt3662 && class177 != null && ~class177.aShort1335 > -1 && class175.aShort1335 == class177.aShort1335 && ~class175.aShort1329 == ~class177.aShort1329 && Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n9 + 1, -12639, n7) == method3420 && (~(1 + n9) <= ~Class64_Sub9.anInt3662 || ~method3420 == ~Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n9 + 2, -12639, n7)); ++n9, class177 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n5][n7][1 + n9]) {}
                                final int n12 = -n10 + n11 + 1;
                                final int method3421 = Class98_Sub46_Sub2_Sub2.aSArray6298[b ? (1 + n10) : n10].method3420(n8, -12639, n7);
                                final short n13 = (short)(class175.aShort1335 * n12 + method3421);
                                final int method3422 = Class98_Sub46_Sub2_Sub2.aSArray6298[b ? (n10 + 1) : n10].method3420(1 + n9, -12639, n7);
                                final short n14 = (short)(method3422 + class175.aShort1335 * n12);
                                final int n15 = n7 << Class151_Sub8.anInt5015;
                                final int n16 = n8 << Class151_Sub8.anInt5015;
                                final int n17 = r_Sub2.anInt6333 + (n9 << Class151_Sub8.anInt5015);
                                Class98_Sub30.aClass155Array4099[Class336.anInt2820++] = new Class155(1, n11, n15 - -class175.aShort1329, class175.aShort1329 + n15, class175.aShort1329 + n15, class175.aShort1329 + n15, method3421, method3422, n14, n13, n16, n17, n17, n16);
                                for (int n18 = n10; ~n18 >= ~n11; ++n18) {
                                    for (int n19 = n8; ~n19 >= ~n9; ++n19) {
                                        final Class172 class178 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n18][n7][n19];
                                        class178.aShort1335 *= -1;
                                    }
                                }
                            }
                            if (~class175.aShort1328 > -1) {
                                int n20 = n7;
                                int n21 = n7;
                                final int n22 = n5;
                                final int n23 = n5;
                                Class172 class179;
                                int method3423;
                                for (class179 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n5][-1 + n20][n6], method3423 = Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n6, -12639, n7); ~n20 < -1 && class179 != null && ~class179.aShort1328 > -1 && class175.aShort1328 == class179.aShort1328 && ~class175.aShort1323 == ~class179.aShort1323 && method3423 == Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n6, -12639, -1 + n20) && (-1 + n20 <= 0 || ~Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n6, -12639, n20 - 2) == ~method3423); --n20, class179 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n5][n20 - 1][n6]) {}
                                for (Class172 class180 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n5][n21 + 1][n6]; ~n21 > ~Class366.anInt3112 && class180 != null && ~class180.aShort1328 > -1 && ~class175.aShort1328 == ~class180.aShort1328 && class180.aShort1323 == class175.aShort1323 && Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n6, -12639, n21 + 1) == method3423 && (~(n21 + 1) <= ~Class366.anInt3112 || ~method3423 == ~Class98_Sub46_Sub2_Sub2.aSArray6298[n5].method3420(n6, -12639, n21 + 2)); ++n21, class180 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n5][n21 + 1][n6]) {}
                                final int n24 = -n22 + (n23 + 1);
                                final int method3424 = Class98_Sub46_Sub2_Sub2.aSArray6298[b ? (1 + n22) : n22].method3420(n6, -12639, n20);
                                final short n25 = (short)(method3424 - -(n24 * class175.aShort1328));
                                final int method3425 = Class98_Sub46_Sub2_Sub2.aSArray6298[b ? (1 + n22) : n22].method3420(n6, -12639, n21 + 1);
                                final short n26 = (short)(class175.aShort1328 * n24 + method3425);
                                final int n27 = n20 << Class151_Sub8.anInt5015;
                                final int n28 = r_Sub2.anInt6333 + (n21 << Class151_Sub8.anInt5015);
                                final int n29 = n6 << Class151_Sub8.anInt5015;
                                Class98_Sub30.aClass155Array4099[Class336.anInt2820++] = new Class155(2, n23, n27, n28, n28, n27, method3424, method3425, n26, n25, n29 - -class175.aShort1323, class175.aShort1323 + n29, n29 + class175.aShort1323, n29 + class175.aShort1323);
                                for (int n30 = n22; ~n23 <= ~n30; ++n30) {
                                    for (int n31 = n20; ~n31 >= ~n21; ++n31) {
                                        final Class172 class181 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n30][n31][n6];
                                        class181.aShort1328 *= -1;
                                    }
                                }
                            }
                        }
                    }
                }
                ++n5;
            }
            OutputStream_Sub1.aBoolean35 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.G(" + n + ')');
        }
    }
    
    Class64_Sub22(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    static final void method645(final byte b) {
        try {
            OutputStream_Sub1.aBoolean35 = false;
            Class59.anInt464 = 0;
            Class98_Sub46_Sub13_Sub2.anIntArrayArrayArray6311 = new int[Class364.anInt3103][1 + Class366.anInt3112][Class64_Sub9.anInt3662 + 1];
            Class21_Sub3.anInt5389 = 0;
            Class98_Sub32_Sub1.aClass155Array5889 = new Class155[1000];
            Class98_Sub30.aClass155Array4099 = new Class155[2000];
            Class213.aClass155Array1611 = new Class155[500];
            Class119_Sub1.anInt4716 = r_Sub2.anInt6333;
            Class336.anInt2820 = 0;
            ha.anInt936 = 0;
            Class370.anInt3139 = r_Sub2.anInt6333;
            Class258.aClass155Array1951 = new Class155[500];
            Label_0114: {
                if (Class98_Sub10_Sub30.aHa5709 instanceof oa) {
                    Class98_Sub17.aBoolean3942 = false;
                    if (!client.aBoolean3553) {
                        break Label_0114;
                    }
                }
                Class98_Sub17.aBoolean3942 = true;
            }
            if (b != 102) {
                Class64_Sub22.anInt3705 = 61;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.M(" + b + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return -62;
            }
            return 127;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.A(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                return 3;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rv.F(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class64_Sub22.anInt3705 = 0;
        Class64_Sub22.aClass294_3706 = new Class294();
    }
}
