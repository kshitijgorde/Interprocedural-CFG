// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub20_Sub2 extends Class98_Sub46_Sub20
{
    private Object anObject6315;
    static Class218[] aClass218Array6316;
    static int anInt6317;
    static int[] anIntArray6318;
    static int anInt6319;
    
    @Override
    final Object method1635(final int n) {
        try {
            return this.anObject6315;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "to.A(" + n + ')');
        }
    }
    
    @Override
    final boolean method1638(final int n) {
        try {
            return n != 896 && false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "to.B(" + n + ')');
        }
    }
    
    public static void method1640(final byte b) {
        try {
            Class98_Sub46_Sub20_Sub2.aClass218Array6316 = null;
            if (b > 40) {
                Class98_Sub46_Sub20_Sub2.anIntArray6318 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "to.F(" + b + ')');
        }
    }
    
    Class98_Sub46_Sub20_Sub2(final Interface20 interface20, final Object anObject6315, final int n) {
        super(interface20, n);
        try {
            this.anObject6315 = anObject6315;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "to.<init>(" + ((interface20 != null) ? "{...}" : "null") + ',' + ((anObject6315 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub20_Sub2.aClass218Array6316 = new Class218[5];
        for (int i = 0; i < Class98_Sub46_Sub20_Sub2.aClass218Array6316.length; ++i) {
            Class98_Sub46_Sub20_Sub2.aClass218Array6316[i] = new Class218();
        }
        Class98_Sub46_Sub20_Sub2.anIntArray6318 = new int[] { 2, 1, 1, 1, 2, 2, 2, 1, 3, 3, 3, 2, 0, 4, 0 };
    }
}
