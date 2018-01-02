// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub46_Sub13 extends Class98_Sub46
{
    boolean aBoolean6036;
    boolean aBoolean6037;
    volatile boolean aBoolean6038;
    
    abstract int method1590(final int p0);
    
    abstract byte[] method1591(final int p0);
    
    static final void method1592(final int n, final int anInt4202, final int anInt4203, final int anInt4204, final int anInt4205, final int anInt4206) {
        try {
            Class98_Sub4.anInt3828 = anInt4205;
            Class98_Sub15.anInt3915 = anInt4206;
            if (n == -25686) {
                Class98_Sub41.anInt4202 = anInt4202;
                Class303.anInt2530 = anInt4204;
                Exception_Sub1.anInt44 = anInt4203;
                if (Class98_Sub4.anInt3828 >= 100) {
                    final int n2 = 256 + Exception_Sub1.anInt44 * 512;
                    final int n3 = Class98_Sub15.anInt3915 * 512 + 256;
                    final int n4 = Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, n3, n2, 24111) + -Class303.anInt2530;
                    final int n5 = n2 + -Class98_Sub46_Sub10.anInt6020;
                    final int n6 = -Class79.anInt601 + n4;
                    final int n7 = -Class134.anInt3461 + n3;
                    Class246_Sub3_Sub4_Sub2.anInt6357 = (0x3FFF & (int)(2607.5945876176133 * Math.atan2(n6, (int)Math.sqrt(n5 * n5 + n7 * n7))));
                    Class186.anInt3424 = (0x3FFF & (int)(Math.atan2(n5, n7) * -2607.5945876176133));
                    Class308.anInt2584 = 0;
                    if (~Class246_Sub3_Sub4_Sub2.anInt6357 > -1025) {
                        Class246_Sub3_Sub4_Sub2.anInt6357 = 1024;
                    }
                    if (~Class246_Sub3_Sub4_Sub2.anInt6357 < -3073) {
                        Class246_Sub3_Sub4_Sub2.anInt6357 = 3072;
                    }
                }
                Class98_Sub46_Sub20_Sub2.anInt6319 = 2;
                Class116.anInt967 = (Class64_Sub26.anInt3712 = -1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hia.M(" + n + ',' + anInt4202 + ',' + anInt4203 + ',' + anInt4204 + ',' + anInt4205 + ',' + anInt4206 + ')');
        }
    }
    
    public Class98_Sub46_Sub13() {
        this.aBoolean6038 = true;
    }
}
