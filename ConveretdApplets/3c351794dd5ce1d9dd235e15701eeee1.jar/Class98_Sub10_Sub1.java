// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub1 extends Class98_Sub10
{
    private int anInt5541;
    static Class75[] aClass75Array5542;
    static int anInt5543;
    static Class207 aClass207_5544;
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                Class98_Sub10_Sub1.anInt5543 = 92;
            }
            if (super.aClass16_3863.aBoolean198) {
                final int[] method238 = this.method1000(n2 - 1 & za_Sub1.anInt6075, 0, n ^ 0xFF);
                final int[] method239 = this.method1000(n2, 0, 0);
                final int[] method240 = this.method1000(za_Sub1.anInt6075 & 1 + n2, 0, 0);
                for (int n3 = 0; ~n3 > ~Class25.anInt268; ++n3) {
                    final int n4 = this.anInt5541 * (method240[n3] - method238[n3]);
                    final int n5 = (method239[n3 + 1 & Class329.anInt2761] - method239[Class329.anInt2761 & n3 - 1]) * this.anInt5541 >> 1820664684;
                    final int n6 = n4 >> 1009040556;
                    final int n7 = (int)(4096.0 * Math.sqrt(((n5 * n5 >> 466133996) - -(n6 * n6 >> -1631782228) + 4096) / 4096.0f));
                    method237[n3] = 4096 + -((~n7 != -1) ? (16777216 / n7) : 0);
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aca.G(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub1() {
        super(1, true);
        this.anInt5541 = 4096;
    }
    
    static final void method1002(final boolean b) {
        try {
            Class199.aClass32_1531.method313((byte)30, 5);
            Class82.aClass153_630.method2485(5, 1);
            Class149.aClass83_1205.method827((byte)(-63), 5);
            Class130.aClass302_1028.method3548(5, (byte)85);
            Class4.aClass301_85.method3539(5, (byte)123);
            Class98_Sub46_Sub19.aClass205_6068.method2720(5, 1);
            Class151_Sub7.aClass183_5001.method2621(5, 3);
            Class196.aClass304_1509.method3566((byte)(-53), 5);
            Class17.aClass198_205.method2681((byte)126, 5);
            Class134.aClass139_3465.method2284((byte)(-120), 5);
            Class370.aClass257_3136.method3197((byte)30, 5);
            Class216.aClass341_1622.method3806(5, false);
            Class98_Sub10_Sub23.aClass335_5662.method3767(56, 5);
            Class98_Sub43_Sub1.aClass365_5897.method3943(5, false);
            Class303.aClass13_2529.method223(32, 5);
            Class373_Sub2.aClass59_5470.method530((byte)(-32), 5);
            Class101.aClass115_857.method2153(5, (byte)(-125));
            Class21_Sub1.aClass269_5383.method3272(119, 5);
            Class18.aClass11_213.method203(b, 5);
            Class62.aClass264_487.method3227(5, -112);
            Class246_Sub3_Sub1.aClass121_6150.method2191(5, (byte)126);
            Class43.method404(5, 2974);
            Class269.method3266(50, (byte)(-50));
            Class98_Sub11.method1124(50, (byte)78);
            Class77_Sub1.method787((byte)110, 5);
            Class67.method687((byte)118, 5);
            Class275.aClass79_2046.method800((byte)62, 5);
            Class224_Sub3.aClass79_5039.method800((byte)62, 5);
            Class378.aClass79_3189.method800((byte)62, 5);
            Class98_Sub6.aClass79_3847.method800((byte)62, 5);
            Class247.aClass79_1890.method800((byte)62, 5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aca.H(" + b + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b >= -92) {
                Class98_Sub10_Sub1.aClass207_5544 = null;
            }
            if (~n == -1) {
                this.anInt5541 = class98_Sub22.readShort((byte)127);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aca.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method1003(final boolean aBoolean4991, final boolean b) {
        try {
            if (aa_Sub1.aClass123_3561 != null) {
                aa_Sub1.aClass123_3561.method2207(-14);
                aa_Sub1.aClass123_3561 = null;
            }
            Class64_Sub16.anInt3680 = 0;
            Class98_Sub10_Sub15.method1050((byte)97);
            Class129.method2229();
            for (int i = 0; i < 4; ++i) {
                Class167.aClass243Array1281[i].method2950((byte)(-99));
            }
            Class118.method2173(false, -7);
            System.gc();
            Class96.method923(127, 2);
            Class151_Sub5.aBoolean4991 = aBoolean4991;
            Class144.anInt1169 = -1;
            Class233.method2883((byte)111);
            Class309.method3614(true, 121);
            Class272.anInt2038 = 0;
            Class160.anInt1258 = 0;
            Class275.anInt2047 = 0;
            aa_Sub2.anInt3562 = 0;
            Class118.anInt978 = (Class151_Sub9.anInt5028 = 0);
            for (int n = 0; Class104.aClass36Array903.length > n; ++n) {
                Class104.aClass36Array903[n] = null;
            }
            Class121.method2195(30574);
            for (int j = 0; j < 2048; ++j) {
                Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[j] = null;
            }
            Class150.anInt1211 = 0;
            Class260.aClass377_3254.method3994(-96);
            Class98_Sub10_Sub20.anInt5640 = 0;
            Class146.aClass377_1180.method3994(-126);
            Class284.method3359(9268);
            Class75.anInt581 = 0;
            Class75.aClass140_584.method2288((byte)(-103));
            Class373_Sub3.method3977(true);
            Class246_Sub4_Sub1.method3104(5134);
            Class11.aLong121 = 0L;
            Class284.aClass98_Sub4_2167 = null;
            if (b) {
                Class61.method538(12, false);
            }
            else {
                Class61.method538(3, false);
                try {
                    Class203.method2704("loggedout", Class76_Sub11.anApplet3799, -26978);
                }
                catch (Throwable t) {}
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aca.E(" + aBoolean4991 + ',' + b + ')');
        }
    }
    
    static final void method1004(final int n, int anInt5497) {
        try {
            if (~anInt5497 > -1 || ~anInt5497 < -3) {
                anInt5497 = 0;
            }
            if (n > -41) {
                Class98_Sub10_Sub1.aClass75Array5542 = null;
            }
            Class337_Sub1.anInt5497 = anInt5497;
            Class373_Sub2.aClass246_Sub5Array5469 = new Class246_Sub5[1 + Class224_Sub1.anIntArray5034[Class337_Sub1.anInt5497]];
            Class258.anInt1952 = 0;
            Class273.anInt2039 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aca.F(" + n + ',' + anInt5497 + ')');
        }
    }
    
    static final boolean method1005(final int n, final int n2, final byte b) {
        try {
            if (b != -23) {
                Class98_Sub10_Sub1.aClass207_5544 = null;
            }
            return ~(0x800 & n) != -1 && ~(n2 & 0x37) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aca.D(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    public static void method1006(final int n) {
        try {
            if (n == -1) {
                Class98_Sub10_Sub1.aClass75Array5542 = null;
                Class98_Sub10_Sub1.aClass207_5544 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aca.B(" + n + ')');
        }
    }
}
