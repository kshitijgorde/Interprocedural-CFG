import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class233
{
    static int anInt1746;
    
    static final void method2883(final byte b) {
        try {
            if (b != 111) {
                method2884(96);
            }
            Class299_Sub2.method3523(255, -1, b - 111);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "os.A(" + b + ')');
        }
    }
    
    static final void method2884(final int n) {
        try {
            Class246_Sub3_Sub4_Sub5.method3084(true);
            Class217.method2799(2, 22050, ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub1_4043.method558((byte)127) == 0xFFFFFFFE, (byte)124);
            Class145.aClass268_1173 = Class153.method2484(22050, 0, (byte)(-126), Class98_Sub43_Sub2.aClass88_5907, Class42_Sub3.aCanvas5361);
            Class246_Sub3_Sub5_Sub2.method3098(Class111_Sub1.method2115(126, null), true, 28643);
            if (n >= 96) {
                (Class27.aClass268_276 = Class153.method2484(2048, 1, (byte)(-126), Class98_Sub43_Sub2.aClass88_5907, Class42_Sub3.aCanvas5361)).method3252(0, Class81.aClass98_Sub31_Sub3_619);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "os.B(" + n + ')');
        }
    }
}
