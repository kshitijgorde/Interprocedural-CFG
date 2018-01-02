// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub3 extends Class98
{
    int[] anIntArray3823;
    int[] anIntArray3824;
    static int anInt3825;
    
    final Class146 method951(final Class97 class97, final int n, final int n2, final boolean b, final Class313 class98, final int n3, final int n4, final int n5, final byte b2, final ha ha) {
        try {
            Class146 method1790 = null;
            int method1791 = n4;
            Class294 method1792 = null;
            if (~n3 != 0x0) {
                method1792 = Class370.aClass257_3136.method3199(false, n3);
            }
            int[] anIntArray3824 = this.anIntArray3824;
            if (method1792 != null && method1792.anIntArray2379 != null) {
                anIntArray3824 = new int[method1792.anIntArray2379.length];
                for (int n6 = 0; ~n6 > ~method1792.anIntArray2379.length; ++n6) {
                    final int n7 = method1792.anIntArray2379[n6];
                    if (n7 < 0 || ~n7 <= ~this.anIntArray3824.length) {
                        anIntArray3824[n6] = -1;
                    }
                    else {
                        anIntArray3824[n6] = this.anIntArray3824[n7];
                    }
                }
            }
            boolean b3 = false;
            boolean b4 = false;
            boolean b5 = false;
            boolean b6 = false;
            int n8 = -1;
            int n9 = -1;
            int n10 = 0;
            Class98_Sub46_Sub16 method1793 = null;
            Class98_Sub46_Sub16 method1794 = null;
            if (b2 > -43) {
                this.method951(null, 78, 100, false, null, 112, 24, -42, (byte)36, null);
            }
            if (class97 != null) {
                method1791 |= 0x20;
                final int n11 = class97.anIntArray818[n2];
                final int n12 = n11 >>> -2044512464;
                method1793 = Class151_Sub7.aClass183_5001.method2624(2, n12);
                n8 = (n11 & 0xFFFF);
                if (method1793 != null) {
                    b4 |= method1793.method1619(n8, 31239);
                    b3 |= method1793.method1617(false, n8);
                    b6 |= method1793.method1615(n8, false);
                    b5 |= class97.aBoolean817;
                }
                if ((class97.aBoolean825 || Class357.aBoolean3027) && ~n5 != 0x0 && class97.anIntArray818.length > n5) {
                    n10 = class97.anIntArray811[n2];
                    final int n13 = class97.anIntArray818[n5];
                    final int n14 = n13 >>> 951346256;
                    n9 = (n13 & 0xFFFF);
                    if (~n12 != ~n14) {
                        method1794 = Class151_Sub7.aClass183_5001.method2624(2, n9 >>> -1068924144);
                    }
                    else {
                        method1794 = method1793;
                    }
                    if (method1794 != null) {
                        b4 |= method1794.method1619(n9, 31239);
                        b3 |= method1794.method1617(false, n9);
                        b6 |= method1794.method1615(n9, false);
                    }
                }
                if (b4) {
                    method1791 |= 0x80;
                }
                if (b3) {
                    method1791 |= 0x100;
                }
                if (b5) {
                    method1791 |= 0x200;
                }
                if (b6) {
                    method1791 |= 0x400;
                }
            }
            final long method1795 = this.method952(n3, b, anIntArray3824, (int[])((class98 != null) ? class98.anIntArray2683 : null), -29509);
            if (r_Sub1.aClass79_6321 != null) {
                method1790 = (Class146)r_Sub1.aClass79_6321.method802(-120, method1795);
            }
            if (method1790 == null || ~ha.c(method1790.ua(), method1791) != -1) {
                if (method1790 != null) {
                    method1791 = ha.method1777(method1791, method1790.ua());
                }
                int n15 = method1791;
                boolean b7 = false;
                for (int n16 = 0; ~anIntArray3824.length < ~n16; ++n16) {
                    if (~anIntArray3824[n16] != 0x0 && !Class98_Sub46_Sub19.aClass205_6068.method2714(anIntArray3824[n16], (byte)(-123)).method3492(0, b)) {
                        b7 = true;
                    }
                }
                if (b7) {
                    return null;
                }
                final Class178[] array = new Class178[anIntArray3824.length];
                for (int i = 0; i < anIntArray3824.length; ++i) {
                    if (~anIntArray3824[i] != 0x0) {
                        array[i] = Class98_Sub46_Sub19.aClass205_6068.method2714(anIntArray3824[i], (byte)(-116)).method3500(b, 109);
                    }
                }
                if (method1792 != null && method1792.anIntArrayArray2366 != null) {
                    for (int n17 = 0; ~n17 > ~method1792.anIntArrayArray2366.length; ++n17) {
                        if (method1792.anIntArrayArray2366[n17] != null && array[n17] != null) {
                            final int n18 = method1792.anIntArrayArray2366[n17][0];
                            final int n19 = method1792.anIntArrayArray2366[n17][1];
                            final int n20 = method1792.anIntArrayArray2366[n17][2];
                            final int n21 = method1792.anIntArrayArray2366[n17][3];
                            final int n22 = method1792.anIntArrayArray2366[n17][4];
                            final int n23 = method1792.anIntArrayArray2366[n17][5];
                            if (~n21 != -1 || ~n22 != -1 || n23 != 0) {
                                array[n17].method2600(n23, n21, (byte)(-128), n22);
                            }
                            if (~n18 != -1 || n19 != 0 || ~n20 != -1) {
                                array[n17].method2597(n20, n18, (byte)108, n19);
                            }
                        }
                    }
                }
                if (class98 != null) {
                    n15 |= 0x4000;
                }
                method1790 = ha.method1790(new Class178(array, array.length), n15, Class105.anInt3415, 64, 850);
                if (class98 != null) {
                    for (int j = 0; j < 5; ++j) {
                        for (int n24 = 0; Class61.aShortArrayArrayArray478.length > n24; ++n24) {
                            if (Class61.aShortArrayArrayArray478[n24][j].length > class98.anIntArray2683[j]) {
                                method1790.ia(Class98_Sub10_Sub11.aShortArrayArray5597[n24][j], Class61.aShortArrayArrayArray478[n24][j][class98.anIntArray2683[j]]);
                            }
                        }
                    }
                }
                if (r_Sub1.aClass79_6321 != null) {
                    method1790.s(method1791);
                    r_Sub1.aClass79_6321.method805(method1795, method1790, (byte)(-80));
                }
            }
            if (class97 == null || method1793 == null) {
                return method1790;
            }
            final Class146 method1796 = method1790.method2341((byte)1, method1791, true);
            method1796.method2338(n - 1, method1793, n8, method1794, class97.aBoolean817, 0, -104, n10, n9);
            return method1796;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bc.A(" + ((class97 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + b + ',' + ((class98 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b2 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class98_Sub3() {
        this.anIntArray3823 = new int[1];
        this.anIntArray3824 = new int[] { -1 };
    }
    
    private final long method952(final int n, final boolean b, final int[] array, final int[] array2, final int n2) {
        try {
            final long[] aLongArray3164 = Class374.aLongArray3164;
            final long n3 = -1L;
            final long n4 = aLongArray3164[(int)((n >> 1392131400 ^ n3) & 0xFFL)] ^ n3 >>> -155609528;
            long n5 = aLongArray3164[(int)(0xFFL & (n ^ n4))] ^ n4 >>> -1669638136;
            for (int n6 = 0; array.length > n6; ++n6) {
                final long n7 = aLongArray3164[(int)((array[n6] >> -276629928 ^ n5) & 0xFFL)] ^ n5 >>> -947925880;
                final long n8 = n7 >>> -1018516728 ^ aLongArray3164[(int)((n7 ^ array[n6] >> 525663440) & 0xFFL)];
                final long n9 = aLongArray3164[(int)((n8 ^ array[n6] >> 285473992) & 0xFFL)] ^ n8 >>> -600711224;
                n5 = (aLongArray3164[(int)(0xFFL & (array[n6] ^ n9))] ^ n9 >>> -24100984);
            }
            if (array2 != null) {
                for (int n10 = 0; ~n10 > -6; ++n10) {
                    n5 = (n5 >>> 1900652552 ^ aLongArray3164[(int)((array2[n10] ^ n5) & 0xFFL)]);
                }
            }
            if (n2 != -29509) {
                return -33L;
            }
            return aLongArray3164[(int)(((b ? 1 : 0) ^ n5) & 0xFFL)] ^ n5 >>> -778341368;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bc.B(" + n + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static {
        Class98_Sub3.anInt3825 = 0;
    }
}
