// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub5 extends Class64
{
    static Class79 aClass79_3650;
    static float[] aFloatArray3651;
    static int[] anIntArray3652;
    static float[] aFloatArray3653;
    static int anInt3654;
    
    @Override
    final void method551(final byte b) {
        try {
            if (super.aClass98_Sub27_495.aClass64_Sub8_4042.method586(true) && !Class144.method2311(false, super.aClass98_Sub27_495.aClass64_Sub8_4042.method583((byte)124))) {
                super.anInt494 = 0;
            }
            if (~super.anInt494 > -1 || super.anInt494 > 1) {
                super.anInt494 = this.method552(0);
            }
            if (b <= 118) {
                Class64_Sub5.aClass79_3650 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dda.C(" + b + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dda.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                this.method556(-22, 33);
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dda.A(" + n + ')');
        }
    }
    
    Class64_Sub5(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (!Class144.method2311(false, super.aClass98_Sub27_495.aClass64_Sub8_4042.method583((byte)125))) {
                return 3;
            }
            if (n2 != 29053) {
                Class64_Sub5.anIntArray3652 = null;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dda.F(" + n + ',' + n2 + ')');
        }
    }
    
    Class64_Sub5(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    public static void method569(final boolean b) {
        try {
            Class64_Sub5.anIntArray3652 = null;
            if (!b) {
                method569(true);
            }
            Class64_Sub5.aFloatArray3651 = null;
            Class64_Sub5.aFloatArray3653 = null;
            Class64_Sub5.aClass79_3650 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dda.D(" + b + ')');
        }
    }
    
    final int method570(final byte b) {
        try {
            if (b <= 119) {
                return -44;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dda.E(" + b + ')');
        }
    }
    
    final boolean method571(final int n) {
        try {
            return n == -1 && Class144.method2311(false, super.aClass98_Sub27_495.aClass64_Sub8_4042.method583((byte)124));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dda.G(" + n + ')');
        }
    }
    
    static {
        Class64_Sub5.aClass79_3650 = new Class79(50);
        Class64_Sub5.aFloatArray3651 = new float[16384];
        Class64_Sub5.aFloatArray3653 = new float[16384];
        final double n = 3.834951969714103E-4;
        for (int i = 0; i < 16384; ++i) {
            Class64_Sub5.aFloatArray3651[i] = (float)Math.sin(i * n);
            Class64_Sub5.aFloatArray3653[i] = (float)Math.cos(i * n);
        }
    }
}
