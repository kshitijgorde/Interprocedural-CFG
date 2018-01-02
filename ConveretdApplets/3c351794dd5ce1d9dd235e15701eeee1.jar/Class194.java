// 
// Decompiled by Procyon v0.5.30
// 

final class Class194
{
    static byte[] aByteArray1495;
    static Class102[] aClass102Array1496;
    
    static final void method2657(final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2, final byte b, final int n, final int n2) {
        try {
            if (b == 114) {
                final int[] array = new int[4];
                Class236.method2896(array, 0, array.length, n2);
                Class181.method2608(n, class246_Sub3_Sub4_Sub2_Sub2, array, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mq.A(" + ((class246_Sub3_Sub4_Sub2_Sub2 != null) ? "{...}" : "null") + ',' + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    public static void method2658(final int n) {
        try {
            Class194.aClass102Array1496 = null;
            if (n != 255) {
                method2657(null, (byte)(-10), -124, -20);
            }
            Class194.aByteArray1495 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mq.B(" + n + ')');
        }
    }
    
    static {
        Class194.aByteArray1495 = new byte[32896];
        int n = 0;
        for (int n2 = 0; ~n2 > -257; ++n2) {
            for (int i = 0; i <= n2; ++i) {
                Class194.aByteArray1495[n++] = (byte)(255.0 / Math.sqrt((65535 + (n2 * n2 + i * i)) / 65535.0f));
            }
        }
    }
}
