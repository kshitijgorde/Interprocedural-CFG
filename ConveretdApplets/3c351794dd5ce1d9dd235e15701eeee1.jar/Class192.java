// 
// Decompiled by Procyon v0.5.30
// 

final class Class192
{
    private static int anInt1481;
    private static boolean aBoolean1482;
    private static Class148 aClass148_1483;
    
    static final synchronized void method2652(final int n, final boolean aBoolean1482) {
        try {
            if (n >= -41) {
                method2652(-3, false);
            }
            Class192.aBoolean1482 = aBoolean1482;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final synchronized void method2653(final int n) {
        try {
            if (n <= -24) {
                --Class192.anInt1481;
                if (Class192.anInt1481 == 0) {
                    method2656(0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final synchronized void method2654(final boolean b, final Interface9 anInterface9_4295) {
        try {
            if (!Class192.aBoolean1482) {
                if (Class192.anInt1481 <= 0) {
                    anInterface9_4295.w(false);
                }
                else {
                    final Class98_Sub51 class98_Sub51 = new Class98_Sub51();
                    class98_Sub51.anInterface9_4295 = anInterface9_4295;
                    Class192.aClass148_1483.method2419(class98_Sub51, -20911);
                }
                if (b) {
                    Class192.aClass148_1483 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final synchronized void method2655(final int n) {
        try {
            ++Class192.anInt1481;
            if (n != -374) {
                method2656(-76);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final synchronized void method2656(final int n) {
        try {
            while (true) {
                final Class98_Sub51 class98_Sub51 = (Class98_Sub51)Class192.aClass148_1483.method2421(6494);
                if (class98_Sub51 == null) {
                    break;
                }
                class98_Sub51.anInterface9_4295.w(true);
                class98_Sub51.method942(94);
            }
            if (n != 0) {
                method2653(11);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static {
        Class192.anInt1481 = 0;
        Class192.aBoolean1482 = false;
        Class192.aClass148_1483 = new Class148();
    }
}
