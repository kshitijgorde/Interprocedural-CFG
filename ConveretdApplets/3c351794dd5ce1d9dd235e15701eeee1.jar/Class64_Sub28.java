// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub28 extends Class64
{
    static int anInt3717;
    static int[][] anIntArrayArray3718;
    static int[][] anIntArrayArray3719;
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                this.method551((byte)106);
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vp.A(" + n + ')');
        }
    }
    
    Class64_Sub28(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vp.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    final int method668(final byte b) {
        try {
            if (b < 119) {
                return 12;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vp.E(" + b + ')');
        }
    }
    
    static final Class98_Sub3 method669(final int n, final boolean b, final int n2) {
        try {
            if (n2 != 6) {
                Class64_Sub28.anIntArrayArray3719 = null;
            }
            return (Class98_Sub3)Class142.aClass377_1157.method3990((b ? Integer.MIN_VALUE : 0) | n, -1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vp.H(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    static final boolean method670(final int n, final int n2, final int n3) {
        try {
            if (n2 != -12294) {
                Class64_Sub28.anIntArrayArray3719 = null;
            }
            return (Class151_Sub2.method2451(n, 544, n3) | (n & 0x2000) != 0x0 | Class246_Sub1.method2967(n3, n, (byte)91)) & Class246_Sub3_Sub5_Sub2.method3096(-27984, n3, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vp.I(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final boolean method671(final int n) {
        try {
            if (n != -1) {
                this.method551((byte)19);
            }
            return !super.aClass98_Sub27_495.method1291((byte)109);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vp.G(" + n + ')');
        }
    }
    
    Class64_Sub28(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (super.aClass98_Sub27_495.method1291((byte)118)) {
                super.anInt494 = 0;
            }
            if (b <= 118) {
                Class64_Sub28.anIntArrayArray3719 = null;
            }
            if (super.anInt494 < 0 && ~super.anInt494 < -3) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vp.C(" + b + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (super.aClass98_Sub27_495.method1291((byte)113)) {
                return 3;
            }
            if (~n == -1 || ~super.aClass98_Sub27_495.aClass64_Sub25_4039.method655((byte)121) == 0xFFFFFFFE) {
                return 1;
            }
            if (n2 != 29053) {
                this.method551((byte)(-65));
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vp.F(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method672(final int n) {
        try {
            Class64_Sub28.anIntArrayArray3718 = null;
            Class64_Sub28.anIntArrayArray3719 = null;
            if (n != 8192) {
                Class64_Sub28.anIntArrayArray3719 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vp.D(" + n + ')');
        }
    }
    
    static {
        Class64_Sub28.anIntArrayArray3718 = new int[][] { { 0, 2 }, { 0, 2 }, { 0, 0, 2 }, { 2, 0, 0 }, { 0, 2, 0 }, { 0, 0, 2 }, { 0, 5, 1, 4 }, { 0, 4, 4, 4 }, { 4, 4, 4, 0 }, { 6, 6, 6, 2, 2, 2 }, { 2, 2, 2, 6, 6, 6 }, { 0, 11, 6, 6, 6, 4 }, { 0, 2 }, { 0, 4, 4, 4 }, { 0, 4, 4, 4 } };
    }
}
