// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub3 extends Class64
{
    static int anInt3646;
    static int anInt3647;
    static Class207 aClass207_3648;
    
    final int method564(final byte b) {
        try {
            if (b < 119) {
                Class64_Sub3.anInt3646 = 36;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "be.E(" + b + ')');
        }
    }
    
    Class64_Sub3(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    static final void method565(int n, int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            Class333.method3761(n5, Class97.anIntArrayArray814[n++], n4, n3, (byte)(-123));
            Class333.method3761(n5, Class97.anIntArrayArray814[n2--], n4, n3, (byte)125);
            if (n6 != -10194) {
                Class64_Sub3.anInt3647 = 18;
            }
            for (int n7 = n; ~n7 >= ~n2; ++n7) {
                final int[] array;
                array[n4] = ((array = Class97.anIntArrayArray814[n7])[n3] = n5);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "be.G(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    Class64_Sub3(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                Class64_Sub3.aClass207_3648 = null;
            }
            if (super.aClass98_Sub27_495.method1286((byte)104) != s_Sub1.aClass279_5197 || !super.aClass98_Sub27_495.method1291((byte)104)) {
                return 1;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "be.A(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                Class64_Sub3.anInt3647 = -23;
            }
            return 3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "be.F(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "be.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            super.anInt494 = this.method552(0);
            if (b < 118) {
                method565(-59, 53, -54, 62, 98, 89);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "be.C(" + b + ')');
        }
    }
    
    public static void method566(final int n) {
        try {
            if (n == -3623) {
                Class64_Sub3.aClass207_3648 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "be.D(" + n + ')');
        }
    }
    
    static {
        Class64_Sub3.anInt3647 = -1;
        Class64_Sub3.anInt3646 = -1;
    }
}
