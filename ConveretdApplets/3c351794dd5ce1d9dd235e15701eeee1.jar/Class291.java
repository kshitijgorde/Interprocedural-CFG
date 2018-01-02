// 
// Decompiled by Procyon v0.5.30
// 

final class Class291
{
    static double aDouble2199;
    
    static final void method3414(final int n, final boolean b, final int n2) {
        try {
            if (n == -1) {
                if (b) {
                    final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class98_Sub42.aClass171_4235, Class331.aClass117_2811);
                    method3023.aClass98_Sub22_Sub1_3865.writeShort(n2, 1571862888);
                    Class98_Sub10_Sub29.sendPacket(false, method3023);
                }
                else {
                    Class247.method3152(Class288.aClass105_3375, n2, -1);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rw.A(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    static final void method3415(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            if (n > 64) {
                for (int i = 0; i < Class42_Sub3.anInt5366; ++i) {
                    final int smart = class98_Sub22.readSmart(1689622712);
                    int short1 = class98_Sub22.readShort((byte)127);
                    if (short1 == 65535) {
                        short1 = -1;
                    }
                    if (Class98_Sub20.aClass53_Sub1Array3967[smart] != null) {
                        Class98_Sub20.aClass53_Sub1Array3967[smart].anInt429 = short1;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rw.B(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
}
