// 
// Decompiled by Procyon v0.5.30
// 

final class Class23
{
    static boolean aBoolean220;
    static int anInt221;
    static int[] anIntArray222;
    
    public static void method281(final byte b) {
        try {
            Class23.anIntArray222 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bm.A(" + b + ')');
        }
    }
    
    static final int method282(final int n, final int n2) {
        try {
            final int n3 = n2 >>> -219070399;
            final int n4 = n3 | n3 >>> 1968182145;
            final int n5 = n4 | n4 >>> -2008770942;
            final int n6 = n5 | n5 >>> -17314684;
            final int n7 = n6 | n6 >>> -9788344;
            return ~(n7 | n7 >>> 1245334000) & n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bm.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method283(final byte b) {
        try {
            Class356 method865 = null;
            try {
                method865 = Class88.method865(-106, "2");
                final Class98_Sub22 class98_Sub22 = new Class98_Sub22(6 * Class64_Sub21.anInt3700 + 3);
                class98_Sub22.method1194(1, -89);
                class98_Sub22.writeShort(Class64_Sub21.anInt3700, 1571862888);
                for (int n = 0; Class76_Sub5.anIntArray3744.length > n; ++n) {
                    if (Class140.aBooleanArray3246[n]) {
                        class98_Sub22.writeShort(n, 1571862888);
                        class98_Sub22.writeInt(1571862888, Class76_Sub5.anIntArray3744[n]);
                    }
                }
                method865.method3882(class98_Sub22.aByteArray3992, 4657, 0, class98_Sub22.anInt3991);
            }
            catch (Exception ex2) {}
            try {
                if (method865 != null) {
                    method865.method3880(true);
                }
            }
            catch (Exception ex3) {}
            r_Sub1.aLong6322 = Class343.method3819(-47);
            Class66.aBoolean507 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bm.B(" + b + ')');
        }
    }
    
    static {
        Class23.aBoolean220 = false;
        Class23.anInt221 = 0;
        Class23.anIntArray222 = new int[1];
    }
}
