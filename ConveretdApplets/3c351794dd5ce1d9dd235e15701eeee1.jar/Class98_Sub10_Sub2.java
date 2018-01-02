// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub2 extends Class98_Sub10
{
    private int anInt5545;
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n > -76) {
                this.anInt5545 = -101;
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[] method2829 = this.method1000(n2, 1, 0);
                final int[] method2830 = this.method1000(n2, 2, 0);
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                for (int n3 = 0; ~Class25.anInt268 < ~n3; ++n3) {
                    final int n4 = 255 * method2829[n3] >> 328402860 & 0xFF;
                    final int n5 = method2830[n3] * this.anInt5545 >> 299114828;
                    final int n6 = Class278_Sub1.anIntArray5168[n4] * n5 >> -1083056916;
                    final int n7 = aa_Sub2.anIntArray3565[n4] * n5 >> -1641503572;
                    final int n8 = Class329.anInt2761 & (n6 >> 1756603788) + n3;
                    final int[][] method2831 = this.method994(za_Sub1.anInt6075 & (n7 >> -1134552788) + n2, 24431, 0);
                    array[n3] = method2831[0][n8];
                    array2[n3] = method2831[1][n8];
                    array3[n3] = method2831[2][n8];
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aha.C(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub2() {
        super(3, false);
        this.anInt5545 = 32768;
    }
    
    static final String method1007(final String s, final int n) {
        try {
            String method247 = Class18.method247(Class156_Sub2.method2501((byte)(-125), s), -120);
            if (method247 == null) {
                method247 = "";
            }
            return method247;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aha.B(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            if (b != 66) {
                this.method991(122, null, (byte)17);
            }
            Class98_Sub31_Sub4.method1386(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aha.I(" + b + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int[] method238 = this.method1000(n2, 1, 0);
                final int[] method239 = this.method1000(n2, 2, 0);
                for (int n3 = 0; ~Class25.anInt268 < ~n3; ++n3) {
                    final int n4 = method238[n3] >> -2049795036 & 0xFF;
                    final int n5 = this.anInt5545 * method239[n3] >> 329150252;
                    method237[n3] = this.method1000(za_Sub1.anInt6075 & (aa_Sub2.anIntArray3565[n4] * n5 >> -1243350708 >> 1296780) + n2, 0, 0)[Class329.anInt2761 & (n5 * Class278_Sub1.anIntArray5168[n4] >> -862649204 >> 820741420) + n3];
                }
            }
            if (n != 255) {
                method1008(16);
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aha.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1008(final int n) {
        try {
            if (n < -32) {
                RuntimeException_Sub1.aClass79_3204.method794(77);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aha.F(" + n + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (~n != -1) {
                if (~n == 0xFFFFFFFE) {
                    super.aBoolean3861 = (~class98_Sub22.readUnsignedByte((byte)(-126)) == 0xFFFFFFFE);
                }
            }
            else {
                this.anInt5545 = class98_Sub22.readShort((byte)127) << 568932100;
            }
            if (b >= -92) {
                this.anInt5545 = 33;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aha.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method1009(final int n) {
        try {
            try {
                if (~Class257.anInt1948 == 0xFFFFFFFE) {
                    final int method1360 = Class366.aClass98_Sub31_Sub2_3122.method1360(-16257);
                    if (~method1360 < -1 && Class366.aClass98_Sub31_Sub2_3122.method1354(-3619)) {
                        int n2 = method1360 - Class348.anInt2911;
                        if (n2 < 0) {
                            n2 = 0;
                        }
                        Class366.aClass98_Sub31_Sub2_3122.method1366(n2, (byte)48);
                        return;
                    }
                    Class366.aClass98_Sub31_Sub2_3122.method1364(85);
                    Class366.aClass98_Sub31_Sub2_3122.method1349(-1);
                    Class202.aClass308_1550 = null;
                    Label_0115: {
                        if (Class269.aClass207_2025 == null) {
                            Class257.anInt1948 = 0;
                            if (!client.aBoolean3553) {
                                break Label_0115;
                            }
                        }
                        Class257.anInt1948 = 2;
                    }
                    Class81.aClass98_Sub7_620 = null;
                }
                if (~Class257.anInt1948 == 0xFFFFFFFC) {
                    final int method1361 = Class366.aClass98_Sub31_Sub2_3122.method1360(-16257);
                    if (~Class224_Sub3.anInt5037 < ~method1361 && Class366.aClass98_Sub31_Sub2_3122.method1354(-3619)) {
                        int anInt5037 = method1361 + Class22.anInt219;
                        if (~Class224_Sub3.anInt5037 > ~anInt5037) {
                            anInt5037 = Class224_Sub3.anInt5037;
                        }
                        Class366.aClass98_Sub31_Sub2_3122.method1366(anInt5037, (byte)72);
                    }
                    else {
                        Class257.anInt1948 = 0;
                        Class22.anInt219 = 0;
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Class366.aClass98_Sub31_Sub2_3122.method1364(105);
                Class202.aClass308_1550 = null;
                Class269.aClass207_2025 = null;
                Class81.aClass98_Sub7_620 = null;
                Class116.aClass98_Sub31_Sub2_965 = null;
                Class257.anInt1948 = 0;
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "aha.E(" + n + ')');
        }
    }
    
    static final Class267 method1010(final int n) {
        try {
            return Class114.method2149(1, -100);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aha.D(" + n + ')');
        }
    }
}
