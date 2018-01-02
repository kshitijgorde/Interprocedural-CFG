// 
// Decompiled by Procyon v0.5.30
// 

final class Class241
{
    static int anInt1845;
    static int[] anIntArray1846;
    static Object anObject1847;
    
    static final boolean method2931(final int n, final int n2, final int n3) {
        try {
            if (n2 != 262144) {
                Class241.anInt1845 = 31;
            }
            return (Class98_Sub22.method1241(false, n, n3) | (0x40000 & n3) != 0x0) || Class98_Sub27.method1292(n, (byte)116, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pe.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method2932(final int n) {
        try {
            if (n != 14441) {
                Class241.anIntArray1846 = null;
            }
            Class241.anIntArray1846 = null;
            Class241.anObject1847 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pe.A(" + n + ')');
        }
    }
    
    static {
        Class241.anIntArray1846 = new int[] { 19, 55, 38, 155, 255, 110, 137, 205, 76 };
    }
}
