// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub2 extends Class64
{
    static IncomingOpcode aClass58_3642;
    static Class279 aClass279_3643;
    static Class207 aClass207_3644;
    static IncomingOpcode aClass58_3645;
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                this.method551((byte)21);
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "av.A(" + n + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (~super.anInt494 != 0xFFFFFFFE && super.anInt494 != 0) {
                super.anInt494 = this.method552(0);
            }
            if (b < 118) {
                method562(null, -99, -101, -104, -34, -85);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "av.C(" + b + ')');
        }
    }
    
    static final void method559(final boolean b, final int anInt268, final int anInt269) {
        try {
            if (!b) {
                Class64_Sub2.aClass58_3642 = null;
            }
            if (anInt268 != Class25.anInt268) {
                Class64_Sub1.anIntArray3640 = new int[anInt268];
                for (int n = 0; ~anInt268 < ~n; ++n) {
                    Class64_Sub1.anIntArray3640[n] = (n << 715435980) / anInt268;
                }
                Class329.anInt2761 = -1 + anInt268;
                Class246_Sub3_Sub4_Sub1.anInt6241 = anInt268 * 32;
                Class25.anInt268 = anInt268;
            }
            if (anInt269 != Class63.anInt492) {
                if (~Class25.anInt268 != ~anInt269) {
                    Class352.anIntArray3001 = new int[anInt269];
                    for (int i = 0; i < anInt269; ++i) {
                        Class352.anIntArray3001[i] = (i << 1275810284) / anInt269;
                    }
                }
                else {
                    Class352.anIntArray3001 = Class64_Sub1.anIntArray3640;
                }
                Class63.anInt492 = anInt269;
                za_Sub1.anInt6075 = -1 + anInt269;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "av.H(" + b + ',' + anInt268 + ',' + anInt269 + ')');
        }
    }
    
    Class64_Sub2(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    final int method560(final byte b) {
        try {
            if (b <= 119) {
                this.method556(-57, 123);
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "av.E(" + b + ')');
        }
    }
    
    Class64_Sub2(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    static final int method561(final byte b, final int n) {
        try {
            if (b != -95) {
                method562(null, -47, -58, -94, -118, -95);
            }
            final int n2 = n & 0x3F;
            final int n3 = 0x3 & n >> -1593233914;
            if (~n2 == 0xFFFFFFED) {
                if (n3 == 0) {
                    return 1;
                }
                if (n3 == 1) {
                    return 2;
                }
                if (n3 == 2) {
                    return 4;
                }
                if (n3 == 3) {
                    return 8;
                }
            }
            else if (~n2 == 0xFFFFFFEC || n2 == 21) {
                if (n3 == 0) {
                    return 16;
                }
                if (~n3 == 0xFFFFFFFE) {
                    return 32;
                }
                if (~n3 == 0xFFFFFFFD) {
                    return 64;
                }
                if (~n3 == 0xFFFFFFFC) {
                    return 128;
                }
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "av.I(" + b + ',' + n + ')');
        }
    }
    
    static final void method562(final Class246_Sub3 class246_Sub3, final int n, final int n2, final int n3, final int n4, final int n5) {
        boolean b = true;
        int n6 = n2;
        final int n7 = n2 + n4;
        final int n8 = n3 - 1;
        final int n9 = n3 + n5;
        for (int i = n; i <= n + 1; ++i) {
            if (i != Class364.anInt3103) {
                for (int j = n6; j <= n7; ++j) {
                    if (j >= 0 && j < Class366.anInt3112) {
                        for (int k = n8; k <= n9; ++k) {
                            if (k >= 0 && k < Class64_Sub9.anInt3662 && (!b || j >= n7 || k >= n9 || (k < n3 && j != n2))) {
                                final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[i][j][k];
                                if (class172 != null) {
                                    final int n10 = (Class78.aSArray594[i].method3420(k, -12639, j) + Class78.aSArray594[i].method3420(k, -12639, j + 1) + Class78.aSArray594[i].method3420(k + 1, -12639, j) + Class78.aSArray594[i].method3420(k + 1, -12639, j + 1)) / 4 - (Class78.aSArray594[n].method3420(n3, -12639, n2) + Class78.aSArray594[n].method3420(n3, -12639, n2 + 1) + Class78.aSArray594[n].method3420(n3 + 1, -12639, n2) + Class78.aSArray594[n].method3420(n3 + 1, -12639, n2 + 1)) / 4;
                                    final Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1324 = class172.aClass246_Sub3_Sub3_1324;
                                    final Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1325 = class172.aClass246_Sub3_Sub3_1333;
                                    if (aClass246_Sub3_Sub3_1324 != null && aClass246_Sub3_Sub3_1324.method2982((byte)(-84))) {
                                        class246_Sub3.method2981(aClass246_Sub3_Sub3_1324, (byte)(-124), b, (j - n2) * r_Sub2.anInt6333 + (1 - n4) * Class207.anInt1577, Class98_Sub10_Sub30.aHa5709, n10, (k - n3) * r_Sub2.anInt6333 + (1 - n5) * Class207.anInt1577);
                                    }
                                    if (aClass246_Sub3_Sub3_1325 != null && aClass246_Sub3_Sub3_1325.method2982((byte)(-71))) {
                                        class246_Sub3.method2981(aClass246_Sub3_Sub3_1325, (byte)88, b, (j - n2) * r_Sub2.anInt6333 + (1 - n4) * Class207.anInt1577, Class98_Sub10_Sub30.aHa5709, n10, (k - n3) * r_Sub2.anInt6333 + (1 - n5) * Class207.anInt1577);
                                    }
                                    for (Class154 class173 = class172.aClass154_1325; class173 != null; class173 = class173.aClass154_1233) {
                                        final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232 = class173.aClass246_Sub3_Sub4_1232;
                                        if (aClass246_Sub3_Sub4_1232 != null && aClass246_Sub3_Sub4_1232.method2982((byte)(-125)) && (j == aClass246_Sub3_Sub4_1232.aShort6158 || j == n6) && (k == aClass246_Sub3_Sub4_1232.aShort6157 || k == n8)) {
                                            class246_Sub3.method2981(aClass246_Sub3_Sub4_1232, (byte)(-126), b, (aClass246_Sub3_Sub4_1232.aShort6158 - n2) * r_Sub2.anInt6333 + (aClass246_Sub3_Sub4_1232.aShort6160 - aClass246_Sub3_Sub4_1232.aShort6158 + 1 - n4) * Class207.anInt1577, Class98_Sub10_Sub30.aHa5709, n10, (aClass246_Sub3_Sub4_1232.aShort6157 - n3) * r_Sub2.anInt6333 + (aClass246_Sub3_Sub4_1232.aShort6159 - aClass246_Sub3_Sub4_1232.aShort6157 + 1 - n5) * Class207.anInt1577);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                --n6;
                b = false;
            }
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "av.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    public static void method563(final byte b) {
        try {
            Class64_Sub2.aClass58_3642 = null;
            if (b != 122) {
                Class64_Sub2.aClass58_3642 = null;
            }
            Class64_Sub2.aClass58_3645 = null;
            Class64_Sub2.aClass207_3644 = null;
            Class64_Sub2.aClass279_3643 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "av.G(" + b + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                return -104;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "av.F(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class64_Sub2.aClass58_3642 = new IncomingOpcode(48, 3);
        Class64_Sub2.aClass279_3643 = new Class279("stellardawn", 1);
        Class64_Sub2.aClass58_3645 = new IncomingOpcode(73, 4);
    }
}
