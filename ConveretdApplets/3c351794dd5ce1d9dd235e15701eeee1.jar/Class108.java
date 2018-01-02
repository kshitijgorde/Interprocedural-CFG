// 
// Decompiled by Procyon v0.5.30
// 

final class Class108
{
    static final void method1729(final int n, final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2) {
        try {
            if (n < -89 && (class246_Sub3_Sub4_Sub2.anIntArray6383 != null || class246_Sub3_Sub4_Sub2.anIntArray6370 != null)) {
                boolean b = true;
                for (int n2 = 0; class246_Sub3_Sub4_Sub2.anIntArray6383.length > n2; ++n2) {
                    int n3 = -1;
                    if (class246_Sub3_Sub4_Sub2.anIntArray6383 != null) {
                        n3 = class246_Sub3_Sub4_Sub2.anIntArray6383[n2];
                    }
                    if (n3 == -1) {
                        if (!class246_Sub3_Sub4_Sub2.method3043(12, n2, -1)) {
                            b = false;
                        }
                    }
                    else {
                        b = false;
                        int n5;
                        int n6;
                        if (~(n3 & 0xC0000000) == 0x3FFFFFFF) {
                            final int n4 = 0xFFFFFFF & n3;
                            n5 = class246_Sub3_Sub4_Sub2.anInt5084 - 256 + -(((n4 >> 9673934) + -Class272.anInt2038) * 512);
                            n6 = -256 - ((-aa_Sub2.anInt3562 + (0x3FFF & n4)) * 512 - class246_Sub3_Sub4_Sub2.anInt5079);
                        }
                        else if (~(0x8000 & n3) != -1) {
                            final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[0x7FFF & n3];
                            if (class246_Sub3_Sub4_Sub2_Sub2 == null) {
                                class246_Sub3_Sub4_Sub2.method3043(12, n2, -1);
                                continue;
                            }
                            n6 = class246_Sub3_Sub4_Sub2.anInt5079 - class246_Sub3_Sub4_Sub2_Sub2.anInt5079;
                            n5 = -class246_Sub3_Sub4_Sub2_Sub2.anInt5084 + class246_Sub3_Sub4_Sub2.anInt5084;
                        }
                        else {
                            final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(n3, -1);
                            if (class98_Sub39 == null) {
                                class246_Sub3_Sub4_Sub2.method3043(12, n2, -1);
                                continue;
                            }
                            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            n6 = class246_Sub3_Sub4_Sub2.anInt5079 + -aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079;
                            n5 = class246_Sub3_Sub4_Sub2.anInt5084 + -aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084;
                        }
                        if (~n5 != -1 || ~n6 != -1) {
                            class246_Sub3_Sub4_Sub2.method3043(12, n2, 0x3FFF & (int)(2607.5945876176133 * Math.atan2(n5, n6)));
                        }
                    }
                }
                if (b) {
                    class246_Sub3_Sub4_Sub2.anIntArray6370 = null;
                    class246_Sub3_Sub4_Sub2.anIntArray6383 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gs.B(" + n + ',' + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final String method1730(final int n, final int n2, final int n3) {
        try {
            if (n3 != 16383) {
                return null;
            }
            final int n4 = -n2 + n;
            if (n4 < -9) {
                return "<col=ff0000>";
            }
            if (~n4 > 5) {
                return "<col=ff3000>";
            }
            if (n4 < -3) {
                return "<col=ff7000>";
            }
            if (~n4 > -1) {
                return "<col=ffb000>";
            }
            if (n4 > 9) {
                return "<col=00ff00>";
            }
            if (~n4 < -7) {
                return "<col=40ff00>";
            }
            if (~n4 < -4) {
                return "<col=80ff00>";
            }
            if (~n4 < -1) {
                return "<col=c0ff00>";
            }
            return "<col=ffff00>";
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gs.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method1731(final int n, final Class98_Sub22_Sub1 class98_Sub22_Sub1, final int anInt354) {
        try {
            Class64_Sub24.aBoolean3710 = false;
            PlayerUpdate.method2858(class98_Sub22_Sub1, ~(Class38.anInt354 = anInt354));
            Class147.method2413(class98_Sub22_Sub1, 8429);
            if (Class64_Sub24.aBoolean3710) {
                System.out.println("---endgpp---");
            }
            if (n != class98_Sub22_Sub1.anInt3991) {
                throw new RuntimeException("gpi1 pos:" + class98_Sub22_Sub1.anInt3991 + " psize:" + n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gs.A(" + n + ',' + ((class98_Sub22_Sub1 != null) ? "{...}" : "null") + ',' + anInt354 + ')');
        }
    }
}
