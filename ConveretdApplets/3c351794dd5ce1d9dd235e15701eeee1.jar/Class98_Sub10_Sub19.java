import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub19 extends Class98_Sub10
{
    private int anInt5628;
    private int anInt5629;
    static int anInt5630;
    static long aLong5631;
    private int anInt5632;
    private int anInt5633;
    private int anInt5634;
    
    static final void method1057(final int anInt5037, final int n) {
        try {
            if (n != 1024) {
                method1057(93, 113);
            }
            if (Class257.anInt1948 == 0) {
                Class366.aClass98_Sub31_Sub2_3122.method1366(anInt5037, (byte)58);
            }
            else {
                Class224_Sub3.anInt5037 = anInt5037;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lp.B(" + anInt5037 + ',' + n + ')');
        }
    }
    
    static final void method1058(final byte b, final ha ha) {
        try {
            if (b < -35) {
                Class242.aClass244Array1851 = new Class244[Class2.anIntArray70.length];
                for (int n = 0; ~Class2.anIntArray70.length < ~n; ++n) {
                    final int n2 = Class2.anIntArray70[n];
                    final Class197 method2182 = Class119_Sub1.method2182(Class64_Sub17.aClass207_3687, true, n2);
                    Class242.aClass244Array1851[n] = new Class244(ha.method1804(method2182, Class324.method3684(Class64_Sub16.aClass207_3683, n2), true), method2182);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lp.E(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (n != 1) {
                    if (n != 2) {
                        if (~n != 0xFFFFFFFC) {
                            if (~n == 0xFFFFFFFB) {
                                this.anInt5634 = class98_Sub22.readShort((byte)127);
                            }
                        }
                        else {
                            this.anInt5633 = class98_Sub22.readShort((byte)127);
                        }
                    }
                    else {
                        this.anInt5629 = class98_Sub22.readUnsignedByte((byte)(-126));
                    }
                }
                else {
                    this.anInt5632 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt5628 = class98_Sub22.readUnsignedByte((byte)69);
            }
            if (b >= -92) {
                method1057(-103, -126);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lp.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = this.anInt5634 >> 471123713;
                final int[][] method238 = super.aClass16_3863.method238(-4);
                final Random random = new Random(this.anInt5628);
                for (int n4 = 0; ~this.anInt5632 < ~n4; ++n4) {
                    final int n5 = (0xFFB & ((this.anInt5634 <= 0) ? this.anInt5633 : (this.anInt5633 + -n3 + Class63.method546(-28737, this.anInt5634, random)))) >> -745670044;
                    int method239 = Class63.method546(-28737, Class25.anInt268, random);
                    int method240 = Class63.method546(n ^ 0xFFFF8F40, Class63.anInt492, random);
                    int n6 = (this.anInt5629 * Class278_Sub1.anIntArray5168[n5] >> 1421866124) + method239;
                    int n7 = method240 + (aa_Sub2.anIntArray3565[n5] * this.anInt5629 >> -8291220);
                    int n8 = -method240 + n7;
                    int n9 = -method239 + n6;
                    if (n9 != 0 || ~n8 != -1) {
                        if (n8 < 0) {
                            n8 = -n8;
                        }
                        if (~n9 > -1) {
                            n9 = -n9;
                        }
                        final boolean b = n9 < n8;
                        if (b) {
                            final int n10 = method239;
                            method239 = method240;
                            final int n11 = n6;
                            n6 = n7;
                            method240 = n10;
                            n7 = n11;
                        }
                        if (method239 > n6) {
                            final int n12 = method239;
                            final int n13 = method240;
                            method239 = n6;
                            method240 = n7;
                            n6 = n12;
                            n7 = n13;
                        }
                        int n14 = method240;
                        final int n15 = -method239 + n6;
                        int n16 = n7 - method240;
                        int n17 = -n15 / 2;
                        final int n18 = 2048 / n15;
                        final int n19 = -(Class63.method546(-28737, 4096, random) >> -951063678) + 1024;
                        if (~n16 > -1) {
                            n16 = -n16;
                        }
                        final int n20 = (n7 > method240) ? 1 : -1;
                        for (int i = method239; i < n6; ++i) {
                            final int n21 = (i - method239) * n18 + n19 + 1024;
                            final int n22 = Class329.anInt2761 & i;
                            final int n23 = n14 & za_Sub1.anInt6075;
                            n17 += n16;
                            if (b) {
                                method238[n23][n22] = n21;
                            }
                            else {
                                method238[n22][n23] = n21;
                            }
                            if (~n17 < -1) {
                                n14 -= -n20;
                                n17 += -n15;
                            }
                        }
                    }
                }
            }
            if (n != 255) {
                this.method1001((byte)(-38));
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lp.G(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub19() {
        super(0, true);
        this.anInt5633 = 0;
        this.anInt5628 = 0;
        this.anInt5629 = 16;
        this.anInt5634 = 4096;
        this.anInt5632 = 2000;
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            if (b == 66) {
                Class98_Sub31_Sub4.method1386(b - 66);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lp.I(" + b + ')');
        }
    }
    
    static final void method1059(final boolean b, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final int method3219 = Class263.method3219(false, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, n3);
            final int method3220 = Class263.method3219(b, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, n6);
            final int method3221 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n);
            final int method3222 = Class263.method3219(b, Class3.anInt77, Class76_Sub8.anInt3778, n5);
            final int i = Class263.method3219(b, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, n4 + n3);
            final int method3223 = Class263.method3219(b, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, -n4 + n6);
            for (int n7 = method3219; i > n7; ++n7) {
                Class333.method3761(n2, Class97.anIntArrayArray814[n7], method3221, method3222, (byte)(-123));
            }
            for (int j = method3220; j > method3223; --j) {
                Class333.method3761(n2, Class97.anIntArrayArray814[j], method3221, method3222, (byte)(-128));
            }
            final int method3224 = Class263.method3219(b, Class3.anInt77, Class76_Sub8.anInt3778, n4 + n);
            final int method3225 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 - n4);
            for (int n8 = i; ~method3223 <= ~n8; ++n8) {
                final int[] array = Class97.anIntArrayArray814[n8];
                Class333.method3761(n2, array, method3221, method3224, (byte)(-124));
                Class333.method3761(n2, array, method3225, method3222, (byte)1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lp.D(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub19.aLong5631 = 0L;
    }
}
