// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub8 extends Class64
{
    boolean aBoolean3658;
    static Class148 aClass148_3659;
    private boolean aBoolean3660;
    static OutgoingOpcode aClass171_3661;
    
    final void method582(final boolean aBoolean3660, final boolean b) {
        try {
            this.aBoolean3660 = aBoolean3660;
            if (b) {
                method584(-106);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.I(" + aBoolean3660 + ',' + b + ')');
        }
    }
    
    Class64_Sub8(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
        this.aBoolean3658 = false;
        this.aBoolean3660 = true;
    }
    
    Class64_Sub8(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
        this.aBoolean3658 = false;
        this.aBoolean3660 = true;
    }
    
    final int method583(final byte b) {
        try {
            if (b <= 119) {
                this.aBoolean3660 = false;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.E(" + b + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
            this.aBoolean3658 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                Class64_Sub8.aClass171_3661 = null;
            }
            if (!super.aClass98_Sub27_495.method1289(n2 - 29179).method2317(false)) {
                return 3;
            }
            if (n == 3 && !Class48_Sub1_Sub1.method463(-1, "jagdx")) {
                return 3;
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.F(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            this.aBoolean3658 = true;
            if (n != 0) {
                return 108;
            }
            if (!super.aClass98_Sub27_495.method1289(-125).method2317(false)) {
                return 0;
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.A(" + n + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b < 118) {
                this.aBoolean3658 = false;
            }
            if (!super.aClass98_Sub27_495.method1289(-117).method2317(false)) {
                super.anInt494 = 0;
            }
            if (super.anInt494 < 0 || ~super.anInt494 < -6) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.C(" + b + ')');
        }
    }
    
    public static void method584(final int n) {
        try {
            Class64_Sub8.aClass148_3659 = null;
            Class64_Sub8.aClass171_3661 = null;
            if (n != 0) {
                method585(45, (byte)(-105), -121);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.H(" + n + ')');
        }
    }
    
    static final void method585(final int anInt6054, final byte b, final int n) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -49, 12);
            method2628.method1626((byte)(-103));
            method2628.anInt6054 = anInt6054;
            if (b != -85) {
                method584(-109);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.M(" + anInt6054 + ',' + b + ',' + n + ')');
        }
    }
    
    final boolean method586(final boolean b) {
        try {
            return this.aBoolean3660;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.D(" + b + ')');
        }
    }
    
    final boolean method587(final int n) {
        try {
            if (!super.aClass98_Sub27_495.method1289(-114).method2317(false)) {
                return false;
            }
            if (n != -1) {
                this.method551((byte)5);
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eg.G(" + n + ')');
        }
    }
    
    static {
        Class64_Sub8.aClass148_3659 = new Class148();
        Class64_Sub8.aClass171_3661 = new OutgoingOpcode(70, 3);
    }
}
