// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub16 extends Class64
{
    static int anInt3680;
    static Class65 aClass65_3681;
    static short[] aShortArray3682;
    static Class207 aClass207_3683;
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return 97;
            }
            if (super.aClass98_Sub27_495.method1291((byte)118)) {
                return 2;
            }
            if (super.aClass98_Sub27_495.aClass64_Sub8_4042.method586(true) && Class151_Sub5.method2462(super.aClass98_Sub27_495.aClass64_Sub8_4042.method583((byte)124), (byte)20)) {
                return 1;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mf.A(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                Class64_Sub16.aClass65_3681 = null;
            }
            if (super.aClass98_Sub27_495.method1291((byte)117)) {
                return 3;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mf.F(" + n + ',' + n2 + ')');
        }
    }
    
    Class64_Sub16(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mf.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    final boolean method613(final int n) {
        try {
            if (n != -1) {
                this.method614((byte)(-99));
            }
            return !super.aClass98_Sub27_495.method1291((byte)122);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mf.G(" + n + ')');
        }
    }
    
    Class64_Sub16(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    final int method614(final byte b) {
        try {
            if (b <= 119) {
                Class64_Sub16.anInt3680 = -41;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mf.E(" + b + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b <= 118) {
                Class64_Sub16.aClass207_3683 = null;
            }
            if (super.aClass98_Sub27_495.method1291((byte)117)) {
                super.anInt494 = 2;
            }
            if (~super.anInt494 > -1 || super.anInt494 > 2) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mf.C(" + b + ')');
        }
    }
    
    public static void method615(final int n) {
        try {
            Class64_Sub16.aShortArray3682 = null;
            Class64_Sub16.aClass65_3681 = null;
            Class64_Sub16.aClass207_3683 = null;
            if (n != 3) {
                method615(19);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mf.D(" + n + ')');
        }
    }
    
    static {
        Class64_Sub16.anInt3680 = 0;
        Class64_Sub16.aClass65_3681 = new Class65();
    }
}
