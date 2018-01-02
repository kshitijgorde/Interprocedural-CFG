// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub24 extends Class64
{
    static Object anObject3709;
    static boolean aBoolean3710;
    
    public static void method650(final int n) {
        try {
            Class64_Sub24.anObject3709 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uga.G(" + n + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return -68;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uga.A(" + n + ')');
        }
    }
    
    Class64_Sub24(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    Class64_Sub24(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uga.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    final int method651(final byte b) {
        try {
            if (b <= 119) {
                Class64_Sub24.anObject3709 = null;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uga.E(" + b + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                return -97;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uga.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method652(final int n, final int n2) {
        try {
            if (n2 <= 66) {
                return 62;
            }
            if (n == 16711935) {
                return -1;
            }
            return Class38.method348(n, -24);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uga.D(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (super.aClass98_Sub27_495.method1286((byte)104) == Class64_Sub2.aClass279_3643) {
                super.anInt494 = 2;
            }
            if (b >= 118 && (~super.anInt494 > -1 || super.anInt494 > 2)) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uga.C(" + b + ')');
        }
    }
    
    static {
        Class64_Sub24.aBoolean3710 = false;
    }
}
