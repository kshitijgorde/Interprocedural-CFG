// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub13_Sub2 extends Class98_Sub46_Sub13
{
    static int anInt6309;
    int anInt6310;
    static int[][][] anIntArrayArrayArray6311;
    Class17 aClass17_6312;
    byte[] aByteArray6313;
    
    static final int method1598(final int n, final int n2) {
        try {
            if (n2 != -22645) {
                method1601(41, -108);
            }
            return n >>> 1951350663;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rba.E(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method1599(final int n) {
        try {
            if (n >= 100) {
                Class98_Sub46_Sub13_Sub2.anIntArrayArrayArray6311 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rba.F(" + n + ')');
        }
    }
    
    @Override
    final int method1590(final int n) {
        try {
            if (n != 100) {
                return -47;
            }
            if (super.aBoolean6038) {
                return 0;
            }
            return 100;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rba.B(" + n + ')');
        }
    }
    
    static final int method1600(final byte b) {
        try {
            if (b >= -6) {
                method1598(73, -45);
            }
            return Class337_Sub1.anInt5497;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rba.A(" + b + ')');
        }
    }
    
    static final Class98_Sub46_Sub4 method1601(final int n, final int n2) {
        try {
            final Class98_Sub46_Sub4 class98_Sub46_Sub4 = (Class98_Sub46_Sub4)Class38.aClass100_357.method1694((byte)117, n);
            if (class98_Sub46_Sub4 != null) {
                return class98_Sub46_Sub4;
            }
            final byte[] method2745 = Class52.aClass207_3494.method2745(0, n, false);
            if (n2 != 100) {
                method1599(-28);
            }
            if (method2745 == null || method2745.length <= 1) {
                return null;
            }
            Class98_Sub46_Sub4 method2746;
            try {
                method2746 = Class22.method280(method2745, 0);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex.getMessage() + " S: " + n);
            }
            Class38.aClass100_357.method1695(26404, method2746, n);
            return method2746;
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "rba.D(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final byte[] method1591(final int n) {
        try {
            if (n < 5) {
                this.aClass17_6312 = null;
            }
            if (super.aBoolean6038) {
                throw new RuntimeException();
            }
            return this.aByteArray6313;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rba.C(" + n + ')');
        }
    }
}
