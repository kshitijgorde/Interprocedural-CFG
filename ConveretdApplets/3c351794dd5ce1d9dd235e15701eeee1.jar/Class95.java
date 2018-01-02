import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class95
{
    static boolean aBoolean798;
    static int anInt799;
    static int[] anIntArray800;
    
    static final void method920(final byte b) throws IOException {
        try {
            if (b < 77) {
                method921(true);
            }
            if (aa_Sub1.aClass123_3561 != null && ~Class62.anInt490 < -1) {
                Class160.aClass98_Sub22_1257.anInt3991 = 0;
                while (true) {
                    final Class98_Sub11 class98_Sub11 = (Class98_Sub11)Class336.aClass148_2827.method2418(32);
                    if (class98_Sub11 == null || Class160.aClass98_Sub22_1257.aByteArray3992.length + -Class160.aClass98_Sub22_1257.anInt3991 < class98_Sub11.anInt3869) {
                        break;
                    }
                    Class160.aClass98_Sub22_1257.method1217(class98_Sub11.aClass98_Sub22_Sub1_3865.aByteArray3992, class98_Sub11.anInt3869, -1, 0);
                    Class62.anInt490 -= class98_Sub11.anInt3869;
                    class98_Sub11.method942(90);
                    class98_Sub11.aClass98_Sub22_Sub1_3865.method1201(0);
                    class98_Sub11.method1125((byte)6);
                }
                aa_Sub1.aClass123_3561.method2202(-24305, Class160.aClass98_Sub22_1257.anInt3991, Class160.aClass98_Sub22_1257.aByteArray3992, 0);
                Class98_Sub50.anInt4289 += Class160.aClass98_Sub22_1257.anInt3991;
                Class196.anInt1511 = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ft.B(" + b + ')');
        }
    }
    
    public static void method921(final boolean b) {
        try {
            if (b) {
                Class95.anInt799 = 59;
            }
            Class95.anIntArray800 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ft.A(" + b + ')');
        }
    }
    
    static {
        Class95.aBoolean798 = false;
        Class95.anIntArray800 = new int[13];
    }
}
