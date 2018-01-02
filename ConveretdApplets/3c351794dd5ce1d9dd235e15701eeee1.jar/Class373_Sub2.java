// 
// Decompiled by Procyon v0.5.30
// 

final class Class373_Sub2 extends Class373
{
    static Class246_Sub5[] aClass246_Sub5Array5469;
    static Class59 aClass59_5470;
    static int anInt5471;
    static float aFloat5472;
    static int anInt5473;
    
    @Override
    final void method3960(final int n, final int n2, final byte b, final boolean b2) {
        try {
            Class265.aHa1974.method1779(-2 + n2, n, super.aClass93_3478.anInt3514 + 4, super.aClass93_3478.anInt3504 + 2, ((Class93_Sub3)super.aClass93_3478).anInt5495, 0);
            if (b != -36) {
                method3974(-49, -39, 108);
            }
            Class265.aHa1974.method1779(n2 - 1, 1 + n, 2 + super.aClass93_3478.anInt3514, super.aClass93_3478.anInt3504, 0, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ct.D(" + n + ',' + n2 + ',' + b + ',' + b2 + ')');
        }
    }
    
    static final boolean method3974(final int n, final int n2, final int n3) {
        try {
            return ~(0x21 & n2) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ct.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method3975(final byte b) {
        try {
            Class373_Sub2.aClass59_5470 = null;
            Class373_Sub2.aClass246_Sub5Array5469 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ct.G(" + b + ')');
        }
    }
    
    @Override
    final void method3965(final int n, final int n2, final int n3, final boolean b) {
        try {
            final int n4 = this.method3963(100) * super.aClass93_3478.anInt3514 / 10000;
            Class265.aHa1974.aa(n3, 2 + n2, n4, -2 + super.aClass93_3478.anInt3504, ((Class93_Sub3)super.aClass93_3478).anInt5496, 0);
            Class265.aHa1974.aa(n4 + n3, n2 + n, -n4 + super.aClass93_3478.anInt3514, super.aClass93_3478.anInt3504 - 2, 0, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ct.F(" + n + ',' + n2 + ',' + n3 + ',' + b + ')');
        }
    }
    
    Class373_Sub2(final Class207 class207, final Class207 class208, final Class93_Sub3 class93_Sub3) {
        super(class207, class208, class93_Sub3);
    }
    
    static {
        Class373_Sub2.anInt5471 = 1406;
        Class373_Sub2.anInt5473 = 0;
    }
}
