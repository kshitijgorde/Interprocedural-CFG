// 
// Decompiled by Procyon v0.5.30
// 

final class Class93_Sub2 extends Class93
{
    int anInt5490;
    static int anInt5491;
    int anInt5492;
    
    @Override
    public final Class113 method70(final int n) {
        try {
            if (n != 30778) {
                this.anInt5490 = 109;
            }
            return Class137.aClass113_1078;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tu.A(" + n + ')');
        }
    }
    
    Class93_Sub2(final Class63 class63, final Class110 class64, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int anInt5492, final int anInt5493) {
        super(class63, class64, n, n2, n3, n4, n5, n6, n7);
        try {
            this.anInt5490 = anInt5493;
            this.anInt5492 = anInt5492;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tu.<init>(" + ((class63 != null) ? "{...}" : "null") + ',' + ((class64 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + anInt5492 + ',' + anInt5493 + ')');
        }
    }
    
    static final void method910(final ha ha, final byte b) {
        try {
            Class69_Sub2.aClass43_5336 = Class98_Sub1.method945(Class244.anInt1860, ha, (byte)117, true);
            if (b <= -47) {
                Class98_Sub46_Sub2_Sub2.aClass197_6296 = Class98_Sub46_Sub6.method1550(Class244.anInt1860, 18361, ha);
                Class195.aClass43_1499 = Class98_Sub1.method945(Class269.anInt2026, ha, (byte)119, true);
                Class98_Sub46_Sub10.aClass197_6019 = Class98_Sub46_Sub6.method1550(Class269.anInt2026, 18361, ha);
                Class98_Sub10_Sub34.aClass43_5730 = Class98_Sub1.method945(Class123_Sub1.anInt4742, ha, (byte)88, true);
                Class42_Sub1.aClass197_5354 = Class98_Sub46_Sub6.method1550(Class123_Sub1.anInt4742, 18361, ha);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tu.C(" + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final boolean method911(final char c, final int n) {
        try {
            if (n != 95) {
                method911('6', -97);
            }
            return c == ' ' || ~c == 0xFFFFFFDF || c == '_' || ~c == 0xFFFFFFD2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tu.B(" + c + ',' + n + ')');
        }
    }
}
