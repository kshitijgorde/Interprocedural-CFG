// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub1 extends Class64
{
    static IncomingOpcode aClass58_3637;
    static int[][] anIntArrayArray3638;
    static int[] anIntArray3639;
    static int[] anIntArray3640;
    static Class207 aClass207_3641;
    
    @Override
    final void method551(final byte b) {
        try {
            if (b > 118) {
                if (super.anInt494 != 1 && ~super.anInt494 != -1) {
                    super.anInt494 = this.method552(0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aga.C(" + b + ')');
        }
    }
    
    public static void method557(final int n) {
        try {
            Class64_Sub1.anIntArrayArray3638 = null;
            if (n <= 79) {
                Class64_Sub1.anIntArray3639 = null;
            }
            Class64_Sub1.aClass207_3641 = null;
            Class64_Sub1.anIntArray3640 = null;
            Class64_Sub1.aClass58_3637 = null;
            Class64_Sub1.anIntArray3639 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aga.D(" + n + ')');
        }
    }
    
    final int method558(final byte b) {
        try {
            if (b < 119) {
                return -80;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aga.E(" + b + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                return 78;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aga.F(" + n + ',' + n2 + ')');
        }
    }
    
    Class64_Sub1(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                Class64_Sub1.anIntArray3639 = null;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aga.A(" + n + ')');
        }
    }
    
    Class64_Sub1(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aga.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    static {
        Class64_Sub1.anIntArrayArray3638 = new int[][] { { 0, 1, 2, 3 }, { 1, 2, 3, 0 }, { 1, 2, -1, 0 }, { 2, 0, -1, 1 }, { 0, 1, -1, 2 }, { 1, 2, -1, 0 }, { -1, 4, -1, 1 }, { -1, 1, 3, -1 }, { -1, 0, 2, -1 }, { 3, 5, 2, 0 }, { 0, 2, 5, 3 }, { 0, 2, 3, 5 }, { 0, 1, 2, 3 } };
        Class64_Sub1.aClass58_3637 = new IncomingOpcode(34, 6);
    }
}
