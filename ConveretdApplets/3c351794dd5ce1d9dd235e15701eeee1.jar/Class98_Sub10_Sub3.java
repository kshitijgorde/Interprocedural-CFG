// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub3 extends Class98_Sub10
{
    static Class113 aClass113_5546;
    
    public static void method1011(final boolean b) {
        try {
            if (!b) {
                method1011(false);
            }
            Class98_Sub10_Sub3.aClass113_5546 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aja.B(" + b + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                Class98_Sub10_Sub3.aClass113_5546 = null;
            }
            if (super.aClass16_3863.aBoolean198) {
                Class236.method2896(method237, 0, Class25.anInt268, Class352.anIntArray3001[n2]);
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aja.G(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub3() {
        super(0, true);
    }
    
    static final void method1012(final ha ha, final byte b) {
        try {
            int method2642 = 0;
            int method2643 = 0;
            if (za_Sub2.aBoolean6079) {
                method2642 = Class189.method2642((byte)42);
                method2643 = Class335.method3765(false);
            }
            ha.KA(method2642, method2643, Class39_Sub1.anInt3593 - -method2642, 350 - -method2643);
            ha.aa(method2642, method2643, Class39_Sub1.anInt3593, 350, 0x332277 | Class98_Sub10_Sub15.anInt5618 << -1258986120, 1);
            if (b <= 122) {
                method1012(null, (byte)(-3));
            }
            Class93_Sub1_Sub1.method908(method2643 + 350, method2643, false, method2642, method2642 + Class39_Sub1.anInt3593);
            final int n = 350 / Class253.anInt1934;
            if (Class98_Sub28.anInt4080 > 0) {
                final int n2 = 346 + (-Class253.anInt1934 - 4);
                final int n3 = n * n2 / (Class98_Sub28.anInt4080 + (n - 1));
                int n4 = 4;
                if (Class98_Sub28.anInt4080 > 1) {
                    n4 += (-n3 + n2) * (-Class54.anInt3395 + Class98_Sub28.anInt4080 - 1) / (-1 + Class98_Sub28.anInt4080);
                }
                ha.aa(-16 + (method2642 + Class39_Sub1.anInt3593), method2643 - -n4, 12, n3, Class98_Sub10_Sub15.anInt5618 << 2108767992 | 0x332277, 2);
                for (int anInt3395 = Class54.anInt3395; anInt3395 < n + Class54.anInt3395 && ~anInt3395 > ~Class98_Sub28.anInt4080; ++anInt3395) {
                    final String[] method2644 = Class112.method2142(Class98_Sub46_Sub20.aStringArray6073[anInt3395], '\b', false);
                    final int n5 = (-16 + Class39_Sub1.anInt3593 - 8) / method2644.length;
                    for (int n6 = 0; ~method2644.length < ~n6; ++n6) {
                        final int n7 = 8 - -(n5 * n6);
                        ha.KA(method2642 - -n7, method2643, n5 + (n7 + method2642) - 8, 350 + method2643);
                        Class195.aClass43_1499.method411((byte)85, -(Class253.anInt1934 * (-Class54.anInt3395 + anInt3395)) + (-Class98_Sub46_Sub10.aClass197_6019.anInt1514 + 348) + (method2643 + -Class98_Sub10_Sub12.anInt5598), Class224_Sub1.method2834(0, method2644[n6]), -1, -16777216, method2642 - -n7);
                    }
                }
            }
            Class69_Sub2.aClass43_5336.method397(-1, 0, method2642 - -Class39_Sub1.anInt3593 - 25, -16777216, "Build: 637", -20 + (method2643 + 350));
            ha.KA(method2642, method2643, method2642 - -Class39_Sub1.anInt3593, method2643 + 350);
            ha.method1753(22294, Class39_Sub1.anInt3593, -1, method2643 + 350 - Class98_Sub10_Sub12.anInt5598, method2642);
            Class98_Sub10_Sub34.aClass43_5730.method411((byte)(-97), -Class42_Sub1.aClass197_5354.anInt1514 + method2643 + 349, "--> " + Class224_Sub1.method2834(0, Class45.aString382), -1, -16777216, 10 + method2642);
            if (Class4.aBoolean84) {
                int n8 = -1;
                if (Class215.anInt1614 % 30 > 15) {
                    n8 = 16777215;
                }
                ha.method1755(8479, -11 + (-Class42_Sub1.aClass197_5354.anInt1514 + (350 + method2643)), Class42_Sub1.aClass197_5354.method2674("--> " + Class224_Sub1.method2834(0, Class45.aString382).substring(0, Class198.anInt1524), 127) + (10 + method2642), n8, 12);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aja.D(" + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub3.aClass113_5546 = new Class113(6, 1);
    }
}
