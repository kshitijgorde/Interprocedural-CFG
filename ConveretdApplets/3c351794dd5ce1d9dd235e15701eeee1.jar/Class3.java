import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class3
{
    static Image anImage74;
    static IncomingOpcode aClass58_75;
    static Class282 aClass282_76;
    static int anInt77;
    static float aFloat78;
    
    public static void method171(final byte b) {
        try {
            Class3.aClass58_75 = null;
            Class3.anImage74 = null;
            if (b > 73) {
                Class3.aClass282_76 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ada.B(" + b + ')');
        }
    }
    
    static final void method172(final int n) {
        try {
            if (~Class15.anInt185 != 0x0) {
                int n2 = Class2.aClass299_73.method3514(61);
                int n3 = Class2.aClass299_73.method3507((byte)80);
                final Class98_Sub17 class98_Sub17 = (Class98_Sub17)Class167.aClass148_1284.method2418(32);
                if (class98_Sub17 != null) {
                    n2 = class98_Sub17.method1155(123);
                    n3 = class98_Sub17.method1151(48);
                }
                int method2642 = 0;
                if (n < 79) {
                    method171((byte)(-85));
                }
                int method2643 = 0;
                if (za_Sub2.aBoolean6079) {
                    method2642 = Class189.method2642((byte)42);
                    method2643 = Class335.method3765(false);
                }
                Class62.method544(method2642 + n2, method2643, Class15.anInt185, method2642 - -Class39_Sub1.anInt3593, 0, Class98_Sub25.anInt4024 + method2643, method2642, n2, n3, method2643 + n3, method2643, method2642);
                if (Class11.aClass293_120 != null) {
                    Class246_Sub4.method3099(n2 - -method2642, method2643 + n3, (byte)2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ada.A(" + n + ')');
        }
    }
    
    static {
        Class3.anInt77 = 100;
        Class3.aClass58_75 = new IncomingOpcode(81, 4);
    }
}
