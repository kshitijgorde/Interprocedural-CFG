// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub9 extends Class64
{
    static int anInt3662;
    static Class332 aClass332_3663;
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eu.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    Class64_Sub9(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (!super.aClass98_Sub27_495.method1291((byte)111)) {
                return 0;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eu.A(" + n + ')');
        }
    }
    
    final int method588(final byte b) {
        try {
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eu.E(" + b + ')');
        }
    }
    
    public static void method589(final int n) {
        try {
            Class64_Sub9.aClass332_3663 = null;
            if (n != 8) {
                method589(15);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eu.D(" + n + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            super.anInt494 = this.method552(0);
            if (b < 118) {
                this.method551((byte)28);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eu.C(" + b + ')');
        }
    }
    
    static final void method590(final boolean b, final ha_Sub1 ha_Sub1) {
        try {
            if (Class167.anObject1285 == null) {
                Class167.anObject1285 = Class64_Sub25.method654(2, new Class284_Sub2_Sub1().method3377(16, 20283, 128, 128), false);
            }
            if (Class130.anObject1030 == null) {
                Class130.anObject1030 = Class64_Sub25.method654(2, new Class284_Sub1_Sub1().method3366(true, 128, 16, 128), false);
            }
            if (ha_Sub1.aClass118_4322.method2171(b) && Class98_Sub41.anObject4203 == null) {
                Class98_Sub41.anObject4203 = Class64_Sub25.method654(2, Class134_Sub1.method2244(-31633, 16.0f, 0.5f, 0.6f, 16, 8, 128, new Class39_Sub1(419684), 4.0f, 4.0f, 128), false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eu.G(" + b + ',' + ((ha_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method591(final int n, final int anInt4112, final int anInt4113, final int anInt4114, final int anInt4115, final int anInt4116, final int anInt4117, final int anInt4118) {
        try {
            Class98_Sub33 class98_Sub33 = null;
            for (Class98_Sub33 class98_Sub34 = (Class98_Sub33)Class191.aClass148_1478.method2418(32); class98_Sub34 != null; class98_Sub34 = (Class98_Sub33)Class191.aClass148_1478.method2417(122)) {
                if (class98_Sub34.anInt4116 == anInt4118 && class98_Sub34.anInt4112 == anInt4112 && class98_Sub34.anInt4113 == anInt4114 && anInt4115 == class98_Sub34.anInt4118) {
                    class98_Sub33 = class98_Sub34;
                    break;
                }
            }
            if (class98_Sub33 == null) {
                class98_Sub33 = new Class98_Sub33();
                class98_Sub33.anInt4113 = anInt4114;
                class98_Sub33.anInt4118 = anInt4115;
                class98_Sub33.anInt4116 = anInt4118;
                class98_Sub33.anInt4112 = anInt4112;
                if (anInt4112 >= 0 && anInt4114 >= 0 && anInt4112 < Class165.anInt1276 && anInt4114 < Class98_Sub10_Sub7.anInt5572) {
                    Class98_Sub46_Sub9.method1558((byte)109, class98_Sub33);
                }
                Class191.aClass148_1478.method2419(class98_Sub33, -20911);
            }
            class98_Sub33.anInt4122 = anInt4113;
            class98_Sub33.aBoolean4124 = false;
            class98_Sub33.anInt4114 = anInt4116;
            class98_Sub33.aBoolean4123 = true;
            class98_Sub33.anInt4120 = anInt4117;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eu.H(" + n + ',' + anInt4112 + ',' + anInt4113 + ',' + anInt4114 + ',' + anInt4115 + ',' + anInt4116 + ',' + anInt4117 + ',' + anInt4118 + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            return 3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eu.F(" + n + ',' + n2 + ')');
        }
    }
    
    Class64_Sub9(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
}
