// 
// Decompiled by Procyon v0.5.30
// 

final class Class82
{
    static OutgoingOpcode aClass171_625;
    static boolean aBoolean626;
    private ha_Sub3_Sub2 aHa_Sub3_Sub2_627;
    long aLong628;
    static int anInt629;
    static Class153 aClass153_630;
    
    public static void method821(final int n) {
        try {
            if (n == 14) {
                Class82.aClass171_625 = null;
                Class82.aClass153_630 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fg.B(" + n + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.aHa_Sub3_Sub2_627.method2082(0, this.aLong628);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fg.finalize()");
        }
    }
    
    static final void method822(final int n) {
        try {
            r_Sub1.aClass79_6321.method794(n ^ 0x27B6);
            if (n != 10157) {
                Class82.aClass171_625 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fg.C(" + n + ')');
        }
    }
    
    static final Class42_Sub1_Sub1 method823(final int n, final ha_Sub1 ha_Sub1, final int n2, final int n3, final int n4) {
        try {
            if (ha_Sub1.aBoolean4426 || (Class81.method815(n2, 0) && Class81.method815(n, 0))) {
                return new Class42_Sub1_Sub1(ha_Sub1, 3553, n4, n2, n);
            }
            if (ha_Sub1.aBoolean4378) {
                return new Class42_Sub1_Sub1(ha_Sub1, 34037, n4, n2, n);
            }
            return new Class42_Sub1_Sub1(ha_Sub1, n4, n2, n, Class48.method453(423660257, n2), Class48.method453(423660257, n));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fg.A(" + n + ',' + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    Class82(final ha_Sub3_Sub2 aHa_Sub3_Sub2_627, final long aLong628, final Class230[] array) {
        try {
            this.aLong628 = aLong628;
            this.aHa_Sub3_Sub2_627 = aHa_Sub3_Sub2_627;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fg.<init>(" + ((aHa_Sub3_Sub2_627 != null) ? "{...}" : "null") + ',' + aLong628 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class82.aBoolean626 = false;
        Class82.aClass171_625 = new OutgoingOpcode(14, 11);
    }
}
