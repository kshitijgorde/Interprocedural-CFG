// 
// Decompiled by Procyon v0.5.30
// 

final class Class27
{
    static IncomingOpcode aClass58_274;
    static Class148 aClass148_275;
    static Class268 aClass268_276;
    static IncomingOpcode aClass58_277;
    static int[] anIntArray278;
    static long[][] aLongArrayArray279;
    static long[] aLongArray280;
    
    static final void method294(final boolean b) {
        try {
            Class199.aClass32_1531.method315((byte)(-46));
            Class82.aClass153_630.method2482((byte)62);
            Class149.aClass83_1205.method828(-113);
            Class130.aClass302_1028.method3547((byte)127);
            Class4.aClass301_85.method3535((byte)(-19));
            Class98_Sub46_Sub19.aClass205_6068.method2719((byte)71);
            Class151_Sub7.aClass183_5001.method2619(-2118);
            Class196.aClass304_1509.method3559(4);
            Class17.aClass198_205.method2683(0);
            Class134.aClass139_3465.method2283((byte)97);
            Class370.aClass257_3136.method3202((byte)96);
            Class98_Sub10_Sub23.aClass335_5662.method3768(10673);
            Class216.aClass341_1622.method3813(36);
            Class303.aClass13_2529.method218(b);
            Class98_Sub43_Sub1.aClass365_5897.method3944(-1);
            Class373_Sub2.aClass59_5470.method525(-112);
            Class101.aClass115_857.method2158(b);
            Class21_Sub1.aClass269_5383.method3271(b);
            Class18.aClass11_213.method200(1);
            Class62.aClass264_487.method3226(32);
            Class246_Sub3_Sub1.aClass121_6150.method2193(0);
            Class243.method2942(1);
            Class52.method489(false);
            Class34.method328(0);
            Class315.method3646(-106);
            Class246_Sub3_Sub2_Sub1.method3008((byte)60);
            Class275.aClass79_2046.method806((byte)(-106));
            Class224_Sub3.aClass79_5039.method806((byte)(-82));
            Class378.aClass79_3189.method806((byte)(-80));
            Class98_Sub6.aClass79_3847.method806((byte)118);
            Class247.aClass79_1890.method806((byte)28);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "c.C(" + b + ')');
        }
    }
    
    public static void method295(final byte b) {
        try {
            Class27.anIntArray278 = null;
            Class27.aClass268_276 = null;
            Class27.aLongArrayArray279 = null;
            if (b <= -121) {
                Class27.aClass148_275 = null;
                Class27.aClass58_277 = null;
                Class27.aLongArray280 = null;
                Class27.aClass58_274 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "c.B(" + b + ')');
        }
    }
    
    static final void method296(final byte b) {
        try {
            Class98_Sub46_Sub20_Sub2.anInt6317 = 1;
            Class146_Sub2.anInt4855 = -1;
            if (Class98_Sub10_Sub10.aString5593 == null) {
                Class369.method3952(35, (byte)(-55));
            }
            else {
                final Class98_Sub22 class98_Sub22 = new Class98_Sub22(Class167.method2531(Class378.method4006(Class98_Sub10_Sub10.aString5593, -1), 12705));
                final long method1246 = class98_Sub22.method1246(b - 171);
                Class98_Sub10_Sub19.aLong5631 = class98_Sub22.method1246(-105);
                Class342.method3814(true, Class98_Sub28.method1305(-111, method1246), 80, "");
                if (b != 55) {
                    Class27.anIntArray278 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "c.A(" + b + ')');
        }
    }
    
    static {
        Class27.aClass58_274 = new IncomingOpcode(70, -2);
        Class27.aClass148_275 = new Class148();
        Class27.aClass58_277 = new IncomingOpcode(61, 4);
        Class27.anIntArray278 = new int[8];
        Class27.aLongArrayArray279 = new long[8][256];
        Class27.aLongArray280 = new long[11];
        for (int i = 0; i < 256; ++i) {
            final char char1 = "\u1823\uc6e8\u87b8\u014f\u36a6\ud2f5\u796f\u9152\u60bc\u9b8e\ua30c\u7b35\u1de0\ud7c2\u2e4b\ufe57\u1577\u37e5\u9ff0\u4ada\u58c9\u290a\ub1a0\u6b85\ubd5d\u10f4\ucb3e\u0567\ue427\u418b\ua77d\u95d8\ufbee\u7c66\udd17\u479e\uca2d\ubf07\uad5a\u8333\u6302\uaa71\uc819\u49d9\uf2e3\u5b88\u9a26\u32b0\ue90f\ud580\ubecd\u3448\uff7a\u905f\u2068\u1aae\ub454\u9322\u64f1\u7312\u4008\uc3ec\udba1\u8d3d\u9700\ucf2b\u7682\ud61b\ub5af\u6a50\u45f3\u30ef\u3f55\ua2ea\u65ba\u2fc0\ude1c\ufd4d\u9275\u068a\ub2e6\u0e1f\u62d4\ua896\uf9c5\u2559\u8472\u394c\u5e78\u388c\ud1a5\ue261\ub321\u9c1e\u43c7\ufc04\u5199\u6d0d\ufadf\u7e24\u3bab\uce11\u8f4e\ub7eb\u3c81\u94f7\ub913\u2cd3\ue76e\uc403\u5644\u7fa9\u2abb\uc153\udc0b\u9d6c\u3174\uf646\uac89\u14e1\u163a\u6909\u70b6\ud0ed\ucc42\u98a4\u285c\uf886".charAt(i / 2);
            final long n = (~(0x1 & i) == -1) ? (char1 >>> 1856053640) : (char1 & '\u00ff');
            long n2 = n << 909211713;
            if (n2 >= 256L) {
                n2 ^= 0x11DL;
            }
            long n3 = n2 << 1782822081;
            if (~n3 <= -257L) {
                n3 ^= 0x11DL;
            }
            final long n4 = n3 ^ n;
            long n5 = n3 << 483539521;
            if (~n5 <= -257L) {
                n5 ^= 0x11DL;
            }
            Class27.aLongArrayArray279[0][i] = Class151_Sub1.method2448(n ^ n5, Class151_Sub1.method2448(Class151_Sub1.method2448(n4 << 390312336, Class151_Sub1.method2448(n5 << 1263478616, Class151_Sub1.method2448(Class151_Sub1.method2448(n3 << 1309969768, Class151_Sub1.method2448(n << -966627024, n << -650887624)), n << 996425056))), n2 << -175798776));
            for (int n6 = 1; ~n6 > -9; ++n6) {
                Class27.aLongArrayArray279[n6][i] = Class151_Sub1.method2448(Class27.aLongArrayArray279[-1 + n6][i] << 2027155000, Class27.aLongArrayArray279[-1 + n6][i] >>> 1503710728);
            }
        }
        Class27.aLongArray280[0] = 0L;
        for (int j = 1; j <= 10; ++j) {
            final int n7 = -8 + j * 8;
            Class27.aLongArray280[j] = Class284_Sub1_Sub1.method3367(Class35.method335(Class27.aLongArrayArray279[7][7 + n7], 255L), Class284_Sub1_Sub1.method3367(Class284_Sub1_Sub1.method3367(Class284_Sub1_Sub1.method3367(Class35.method335(Class27.aLongArrayArray279[4][n7 + 4], 4278190080L), Class284_Sub1_Sub1.method3367(Class35.method335(Class27.aLongArrayArray279[3][n7 + 3], 1095216660480L), Class284_Sub1_Sub1.method3367(Class284_Sub1_Sub1.method3367(Class35.method335(71776119061217280L, Class27.aLongArrayArray279[1][n7 + 1]), Class35.method335(Class27.aLongArrayArray279[0][n7], -72057594037927936L)), Class35.method335(Class27.aLongArrayArray279[2][n7 + 2], 280375465082880L)))), Class35.method335(Class27.aLongArrayArray279[5][n7 + 5], 16711680L)), Class35.method335(Class27.aLongArrayArray279[6][n7 + 6], 65280L)));
        }
    }
}
