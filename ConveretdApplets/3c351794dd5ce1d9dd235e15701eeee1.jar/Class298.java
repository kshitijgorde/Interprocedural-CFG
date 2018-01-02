// 
// Decompiled by Procyon v0.5.30
// 

final class Class298
{
    Class146 aClass146_2477;
    static Class222 aClass222_2478;
    r aR2479;
    static Class222 aClass222_2480;
    static Class222 aClass222_2481;
    static Class222 aClass222_2482;
    static Class222 aClass222_2483;
    static Class222 aClass222_2484;
    static Class222 aClass222_2485;
    static Class222 aClass222_2486;
    static Class222 aClass222_2487;
    static Class222 aClass222_2488;
    static Class222 aClass222_2489;
    static Class222 aClass222_2490;
    private static Class222[] aClass222Array2491;
    static byte[] aByteArray2492;
    
    public static void method3502(final int n) {
        try {
            Class298.aClass222_2488 = null;
            Class298.aClass222_2483 = null;
            Class298.aClass222_2490 = null;
            Class298.aClass222_2482 = null;
            Class298.aClass222_2489 = null;
            Class298.aClass222_2485 = null;
            Class298.aClass222_2484 = null;
            Class298.aClass222_2481 = null;
            Class298.aClass222_2487 = null;
            Class298.aClass222_2480 = null;
            Class298.aClass222_2478 = null;
            Class298.aClass222Array2491 = null;
            Class298.aByteArray2492 = null;
            Class298.aClass222_2486 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sfa.C(" + n + ')');
        }
    }
    
    static final Class163 method3503(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (n > -6) {
                method3504(-127, 96, 42);
            }
            return new Class163(class98_Sub22.readInt(-2));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sfa.A(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final boolean method3504(final int n, final int n2, final int n3) {
        try {
            if (n3 != 28) {
                method3503(null, -50);
            }
            return ((n2 & 0x70000) != 0x0 | Class105.method1715(true, n2, n)) || Class238.method2919(-116, n2, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sfa.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class298.aClass222_2478 = new Class222(14, 0);
        Class298.aClass222_2480 = new Class222(15, 4);
        Class298.aClass222_2481 = new Class222(16, -2);
        Class298.aClass222_2482 = new Class222(17, 0);
        Class298.aClass222_2483 = new Class222(18, -2);
        Class298.aClass222_2484 = new Class222(19, -2);
        Class298.aClass222_2485 = new Class222(22, -2);
        Class298.aClass222_2486 = new Class222(23, 4);
        Class298.aClass222_2487 = new Class222(24, -1);
        Class298.aClass222_2488 = new Class222(26, 0);
        Class298.aClass222_2489 = new Class222(27, 0);
        Class298.aClass222_2490 = new Class222(28, -2);
        Class298.aClass222Array2491 = new Class222[32];
        final Class222[] method1484 = Class98_Sub43.method1484(0);
        for (int n = 0; ~n > ~method1484.length; ++n) {
            Class298.aClass222Array2491[method1484[n].anInt1668] = method1484[n];
        }
    }
}
