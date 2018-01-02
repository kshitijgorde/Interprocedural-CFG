// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub14 extends Class64
{
    static Class332[] aClass332Array3675;
    
    Class64_Sub14(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    final boolean method607(final int n) {
        try {
            if (n != -1) {
                Class64_Sub14.aClass332Array3675 = null;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ju.G(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (~n == -1 || super.aClass98_Sub27_495.aClass64_Sub25_4039.method655((byte)126) == 1) {
                return 1;
            }
            if (n2 != 29053) {
                Class64_Sub14.aClass332Array3675 = null;
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ju.F(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b <= 118) {
                Class64_Sub14.aClass332Array3675 = null;
            }
            if (~super.anInt494 != -1 && super.aClass98_Sub27_495.aClass64_Sub25_4039.method655((byte)122) != 1) {
                super.anInt494 = 0;
            }
            if (super.anInt494 < 0 || ~super.anInt494 < -2) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ju.C(" + b + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return 81;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ju.A(" + n + ')');
        }
    }
    
    public static void method608(final int n) {
        try {
            Class64_Sub14.aClass332Array3675 = null;
            if (n >= -90) {
                method608(69);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ju.D(" + n + ')');
        }
    }
    
    final int method609(final byte b) {
        try {
            if (b < 119) {
                Class64_Sub14.aClass332Array3675 = null;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ju.E(" + b + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ju.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    Class64_Sub14(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
}
