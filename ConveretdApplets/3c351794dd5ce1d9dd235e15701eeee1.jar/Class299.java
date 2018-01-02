// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class299
{
    static Class79 aClass79_2493;
    static int anInt2494;
    static IncomingOpcode aClass58_2495;
    
    final boolean method3505(final byte b) {
        try {
            if (b != 22) {
                this.method3510((byte)46);
            }
            return this.method3506((byte)121) || this.method3510((byte)(-19)) || this.method3512(1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sga.J(" + b + ')');
        }
    }
    
    abstract boolean method3506(final byte p0);
    
    abstract int method3507(final byte p0);
    
    abstract Class98_Sub17 method3508(final int p0);
    
    static final Class243 method3509(final int n, final int n2, final int n3) {
        try {
            if (n != 2742) {
                method3511(85);
            }
            final Class243 class243 = new Class243();
            class243.anInt1855 = -1;
            class243.anInt1854 = -1;
            class243.anInt1856 = 6 + n2;
            class243.anInt1857 = 1 + (n3 + 5);
            class243.anIntArrayArray1853 = new int[class243.anInt1857][class243.anInt1856];
            class243.method2950((byte)(-99));
            return class243;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sga.F(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    abstract boolean method3510(final byte p0);
    
    public static void method3511(final int n) {
        try {
            Class299.aClass58_2495 = null;
            Class299.aClass79_2493 = null;
            if (n != -1) {
                Class299.aClass58_2495 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sga.K(" + n + ')');
        }
    }
    
    abstract boolean method3512(final int p0);
    
    static final void method3513(final boolean b) {
        try {
            int method614 = 0;
            if (Class98_Sub9.aClass98_Sub27_3856 != null) {
                method614 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040.method614((byte)127);
            }
            if (b) {
                method3511(6);
            }
            if (~method614 != 0xFFFFFFFD) {
                if (~method614 == 0xFFFFFFFE) {
                    final int n = Class39_Sub1.anInt3593 = ((~Class188.anInt1453 < -1025) ? 1024 : Class188.anInt1453);
                    final int anInt4024 = (Class123_Sub1.anInt4744 <= 768) ? Class123_Sub1.anInt4744 : 768;
                    Class98_Sub46_Sub10.anInt6022 = (Class188.anInt1453 + -n) / 2;
                    Class191.anInt1479 = 0;
                    Class98_Sub25.anInt4024 = anInt4024;
                }
                else {
                    Class39_Sub1.anInt3593 = Class188.anInt1453;
                    Class98_Sub25.anInt4024 = Class123_Sub1.anInt4744;
                    Class191.anInt1479 = 0;
                    Class98_Sub46_Sub10.anInt6022 = 0;
                }
            }
            else {
                final int anInt4025 = (Class188.anInt1453 <= 800) ? Class188.anInt1453 : 800;
                Class98_Sub46_Sub10.anInt6022 = (-anInt4025 + Class188.anInt1453) / 2;
                final int anInt4026 = (Class123_Sub1.anInt4744 > 600) ? 600 : Class123_Sub1.anInt4744;
                Class39_Sub1.anInt3593 = anInt4025;
                Class191.anInt1479 = 0;
                Class98_Sub25.anInt4024 = anInt4026;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sga.D(" + b + ')');
        }
    }
    
    abstract int method3514(final int p0);
    
    abstract void method3515(final int p0);
    
    abstract void method3516(final byte p0);
    
    static {
        Class299.aClass79_2493 = new Class79(32);
        Class299.aClass58_2495 = new IncomingOpcode(47, -1);
    }
}
