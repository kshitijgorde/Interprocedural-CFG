// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub4 extends Class64
{
    static Class204 aClass204_3649;
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                Class64_Sub4.aClass204_3649 = null;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cq.F(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                Class64_Sub4.aClass204_3649 = null;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cq.A(" + n + ')');
        }
    }
    
    Class64_Sub4(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cq.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    public static void method567(final byte b) {
        try {
            Class64_Sub4.aClass204_3649 = null;
            if (b != 81) {
                method567((byte)73);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cq.D(" + b + ')');
        }
    }
    
    final int method568(final byte b) {
        try {
            if (b < 119) {
                Class64_Sub4.aClass204_3649 = null;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cq.E(" + b + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b < 118) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cq.C(" + b + ')');
        }
    }
    
    Class64_Sub4(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    static {
        Class64_Sub4.aClass204_3649 = new Class204();
    }
}
