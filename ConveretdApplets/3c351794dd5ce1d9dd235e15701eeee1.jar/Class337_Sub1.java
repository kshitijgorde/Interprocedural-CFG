// 
// Decompiled by Procyon v0.5.30
// 

final class Class337_Sub1 extends Class337
{
    static int anInt5497;
    int anInt5498;
    static int anInt5499;
    static int[] anIntArray5500;
    
    public static void method3776(final byte b) {
        try {
            if (b >= 22) {
                Class337_Sub1.anIntArray5500 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dv.C(" + b + ')');
        }
    }
    
    Class337_Sub1(final int n, final Class63 class63, final Class110 class64, final int n2, final int n3, final int anInt5498) {
        super(n, class63, class64, n2, n3);
        try {
            this.anInt5498 = anInt5498;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dv.<init>(" + n + ',' + ((class63 != null) ? "{...}" : "null") + ',' + ((class64 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + anInt5498 + ')');
        }
    }
    
    static final void method3777(final int n) {
        try {
            Class366.aClass98_Sub31_Sub2_3122.method1364(96);
            Class257.anInt1948 = 1;
            if (n != 31585) {
                method3777(-86);
            }
            Class269.aClass207_2025 = null;
            Class116.aClass98_Sub31_Sub2_965 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dv.D(" + n + ')');
        }
    }
    
    @Override
    public final Class113 method70(final int n) {
        try {
            if (n != 30778) {
                return null;
            }
            return Class98_Sub10_Sub3.aClass113_5546;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dv.A(" + n + ')');
        }
    }
    
    static {
        Class337_Sub1.anInt5497 = 2;
        Class337_Sub1.anInt5499 = 1;
        Class337_Sub1.anIntArray5500 = new int[6];
    }
}
