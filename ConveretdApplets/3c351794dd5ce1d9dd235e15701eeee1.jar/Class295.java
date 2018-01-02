// 
// Decompiled by Procyon v0.5.30
// 

final class Class295
{
    static int[] anIntArray2409;
    static int anInt2410;
    
    public static void method3483(final int n) {
        try {
            Class295.anIntArray2409 = null;
            if (n != 0) {
                method3484(false, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sca.A(" + n + ')');
        }
    }
    
    static final void method3484(final boolean b, final boolean b2) {
        try {
            if (Class45.aString382.length() != 0) {
                Class98_Sub46.method1525("--> " + Class45.aString382, -117);
                PlayerUpdateMask.method710(Class45.aString382, b, b2, (byte)117);
                Class45.aString382 = "";
                Class98_Sub31_Sub2.anInt5822 = 0;
                Class198.anInt1524 = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sca.B(" + b + ',' + b2 + ')');
        }
    }
    
    static {
        Class295.anIntArray2409 = new int[2];
    }
}
