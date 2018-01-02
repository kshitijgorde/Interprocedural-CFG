// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub3 extends Class98_Sub46
{
    static Class131[] aClass131Array5953;
    Class246_Sub3_Sub4_Sub3 aClass246_Sub3_Sub4_Sub3_5954;
    
    public static void method1540(final boolean b) {
        try {
            Class98_Sub46_Sub3.aClass131Array5953 = null;
            if (b) {
                method1540(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bv.B(" + b + ')');
        }
    }
    
    Class98_Sub46_Sub3(final Class246_Sub3_Sub4_Sub3 aClass246_Sub3_Sub4_Sub3_5954) {
        try {
            this.aClass246_Sub3_Sub4_Sub3_5954 = aClass246_Sub3_Sub4_Sub3_5954;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bv.<init>(" + ((aClass246_Sub3_Sub4_Sub3_5954 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method1541(final int n) {
        try {
            final int n2 = 256 + 512 * Class363.anInt3098;
            final int n3 = Class98_Sub10_Sub21.anInt5643 * 512 + 256;
            final int n4 = Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, n3, n2, 24111) - Class308.anInt2580;
            Label_0392: {
                if (~Class263.anInt1967 <= -101) {
                    Class134.anInt3461 = 256 + 512 * Class98_Sub10_Sub21.anInt5643;
                    Class98_Sub46_Sub10.anInt6020 = Class363.anInt3098 * 512 + 256;
                    Class79.anInt601 = Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, Class134.anInt3461, Class98_Sub46_Sub10.anInt6020, 24111) - Class308.anInt2580;
                    if (!client.aBoolean3553) {
                        break Label_0392;
                    }
                }
                if (Class98_Sub46_Sub10.anInt6020 < n2) {
                    Class98_Sub46_Sub10.anInt6020 += Class98_Sub46_Sub2_Sub2.anInt6300 - -((-Class98_Sub46_Sub10.anInt6020 + n2) * Class263.anInt1967 / 1000);
                    if (~Class98_Sub46_Sub10.anInt6020 < ~n2) {
                        Class98_Sub46_Sub10.anInt6020 = n2;
                    }
                }
                if (n2 < Class98_Sub46_Sub10.anInt6020) {
                    Class98_Sub46_Sub10.anInt6020 -= Class98_Sub46_Sub2_Sub2.anInt6300 + (-n2 + Class98_Sub46_Sub10.anInt6020) * Class263.anInt1967 / 1000;
                    if (Class98_Sub46_Sub10.anInt6020 < n2) {
                        Class98_Sub46_Sub10.anInt6020 = n2;
                    }
                }
                if (~Class79.anInt601 > ~n4) {
                    Class79.anInt601 += (-Class79.anInt601 + n4) * Class263.anInt1967 / 1000 + Class98_Sub46_Sub2_Sub2.anInt6300;
                    if (Class79.anInt601 > n4) {
                        Class79.anInt601 = n4;
                    }
                }
                if (n3 > Class134.anInt3461) {
                    Class134.anInt3461 += Class263.anInt1967 * (n3 + -Class134.anInt3461) / 1000 + Class98_Sub46_Sub2_Sub2.anInt6300;
                    if (~Class134.anInt3461 < ~n3) {
                        Class134.anInt3461 = n3;
                    }
                }
                if (~Class79.anInt601 < ~n4) {
                    Class79.anInt601 -= Class98_Sub46_Sub2_Sub2.anInt6300 - -((Class79.anInt601 - n4) * Class263.anInt1967 / 1000);
                    if (n4 > Class79.anInt601) {
                        Class79.anInt601 = n4;
                    }
                }
                if (~n3 > ~Class134.anInt3461) {
                    Class134.anInt3461 -= Class98_Sub46_Sub2_Sub2.anInt6300 + Class263.anInt1967 * (-n3 + Class134.anInt3461) / 1000;
                    if (~n3 < ~Class134.anInt3461) {
                        Class134.anInt3461 = n3;
                    }
                }
            }
            final int n5 = 512 * Class98_Sub15.anInt3915 + 256;
            final int n6 = 256 + 512 * Exception_Sub1.anInt44;
            final int n7 = Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, n5, n6, 24111) - Class303.anInt2530;
            final int n8 = -Class98_Sub46_Sub10.anInt6020 + n6;
            final int n9 = -Class79.anInt601 + n7;
            final int n10 = -Class134.anInt3461 + n5;
            int n11 = 0x3FFF & (int)(Math.atan2(n9, (int)Math.sqrt(n8 * n8 - -(n10 * n10))) * 2607.5945876176133);
            final int anInt3424 = (int)(-2607.5945876176133 * Math.atan2(n8, n10)) & 0x3FFF;
            if (n11 < 1024) {
                n11 = 1024;
            }
            if (~n11 < -3073) {
                n11 = 3072;
            }
            if (Class246_Sub3_Sub4_Sub2.anInt6357 < n11) {
                Class246_Sub3_Sub4_Sub2.anInt6357 += (n11 - Class246_Sub3_Sub4_Sub2.anInt6357 >> -1117924989) * Class98_Sub4.anInt3828 / 1000 + Class98_Sub41.anInt4202 << -1739945821;
                if (Class246_Sub3_Sub4_Sub2.anInt6357 > n11) {
                    Class246_Sub3_Sub4_Sub2.anInt6357 = n11;
                }
            }
            if (~n11 > ~Class246_Sub3_Sub4_Sub2.anInt6357) {
                Class246_Sub3_Sub4_Sub2.anInt6357 -= Class98_Sub41.anInt4202 + Class98_Sub4.anInt3828 * (-n11 + Class246_Sub3_Sub4_Sub2.anInt6357 >> 1278679235) / 1000 << -1425824541;
                if (n11 > Class246_Sub3_Sub4_Sub2.anInt6357) {
                    Class246_Sub3_Sub4_Sub2.anInt6357 = n11;
                }
            }
            int n12 = anInt3424 + -Class186.anInt3424;
            if (~n12 < -8193) {
                n12 -= 16384;
            }
            if (~n12 > 8191) {
                n12 += 16384;
            }
            final int n13 = n12 >> 3;
            if (~n13 < -1) {
                Class186.anInt3424 += Class98_Sub4.anInt3828 * n13 / 1000 + Class98_Sub41.anInt4202 << 1507566883;
                Class186.anInt3424 &= 0x3FFF;
            }
            if (n13 < 0) {
                Class186.anInt3424 -= Class98_Sub4.anInt3828 * -n13 / 1000 + Class98_Sub41.anInt4202 << 1406205123;
                Class186.anInt3424 &= 0x3FFF;
            }
            int n14 = -Class186.anInt3424 + anInt3424;
            if (n > 69) {
                if (~n14 < -8193) {
                    n14 -= 16384;
                }
                if (n14 < -8192) {
                    n14 += 16384;
                }
                Class308.anInt2584 = 0;
                if ((~n14 > -1 && n13 > 0) || (n14 > 0 && n13 < 0)) {
                    Class186.anInt3424 = anInt3424;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bv.A(" + n + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub3.aClass131Array5953 = new Class131[100];
    }
}
