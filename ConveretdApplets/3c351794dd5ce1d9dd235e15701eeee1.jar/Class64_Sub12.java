// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub12 extends Class64
{
    static boolean aBoolean3671;
    static int anInt3672;
    static boolean[][][] aBooleanArrayArrayArray3673;
    
    @Override
    final void method551(final byte b) {
        try {
            if (super.anInt494 < 0 || super.anInt494 > 4) {
                super.anInt494 = this.method552(0);
            }
            if (b <= 118) {
                this.method550(-40, 88);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hp.C(" + b + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                Class64_Sub12.aBooleanArrayArrayArray3673 = null;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hp.A(" + n + ')');
        }
    }
    
    Class64_Sub12(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    public static void method599(final byte b) {
        try {
            Class64_Sub12.aBooleanArrayArrayArray3673 = null;
            if (b != -13) {
                Class64_Sub12.aBooleanArrayArrayArray3673 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hp.D(" + b + ')');
        }
    }
    
    Class64_Sub12(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hp.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                return -96;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hp.F(" + n + ',' + n2 + ')');
        }
    }
    
    final int method600(final byte b) {
        try {
            if (b <= 119) {
                this.method551((byte)(-101));
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hp.E(" + b + ')');
        }
    }
    
    static {
        Class64_Sub12.anInt3672 = 0;
        Class64_Sub12.aBoolean3671 = false;
    }
}
