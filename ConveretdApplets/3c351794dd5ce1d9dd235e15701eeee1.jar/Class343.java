// 
// Decompiled by Procyon v0.5.30
// 

final class Class343
{
    static String aString2863;
    
    static final boolean method3817(final byte b, final int n) {
        try {
            return ~n == 0xFFFFFFD1 || ~n == 0xFFFFFFCD || n == 48 || ~n == 0xFFFFFFC5 || ~n == 0xFFFFFFFA || n == 57 || n == 59;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ur.B(" + b + ',' + n + ')');
        }
    }
    
    public static void method3818(final int n) {
        try {
            Class343.aString2863 = null;
            if (n != -59) {
                method3819(-110);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ur.C(" + n + ')');
        }
    }
    
    static final synchronized long method3819(final int n) {
        try {
            if (n != -47) {
                method3818(-90);
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (Class342.aLong2862 > currentTimeMillis) {
                Class360.aLong3070 += Class342.aLong2862 - currentTimeMillis;
            }
            Class342.aLong2862 = currentTimeMillis;
            return Class360.aLong3070 + currentTimeMillis;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ur.A(" + n + ')');
        }
    }
}
