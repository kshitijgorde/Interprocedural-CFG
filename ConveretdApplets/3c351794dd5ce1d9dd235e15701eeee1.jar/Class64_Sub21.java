// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub21 extends Class64
{
    static int anInt3700;
    static int[] anIntArray3701;
    static int anInt3702;
    static Class148 aClass148_3703;
    static int anInt3704;
    
    public static void method638(final int n) {
        try {
            Class64_Sub21.anIntArray3701 = null;
            Class64_Sub21.aClass148_3703 = null;
            if (n != 4) {
                method638(3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rha.D(" + n + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rha.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (super.anInt494 < 0 && super.anInt494 > 4) {
                super.anInt494 = this.method552(0);
            }
            if (b < 118) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rha.C(" + b + ')');
        }
    }
    
    final int method639(final byte b) {
        try {
            if (b < 119) {
                this.method639((byte)43);
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rha.E(" + b + ')');
        }
    }
    
    Class64_Sub21(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    Class64_Sub21(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                Class64_Sub21.anInt3700 = 51;
            }
            if (super.aClass98_Sub27_495.method1289(-108).method2319(32755) > 1) {
                return 4;
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rha.A(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                Class64_Sub21.anInt3704 = -17;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rha.F(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class64_Sub21.anInt3700 = 0;
        Class64_Sub21.anIntArray3701 = new int[25];
        Class64_Sub21.anInt3704 = 1407;
        Class64_Sub21.anInt3702 = 0;
        Class64_Sub21.aClass148_3703 = new Class148();
    }
}
