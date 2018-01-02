import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub23 extends Class64
{
    static Class370 aClass370_3707;
    static int anInt3708;
    
    static final Class98_Sub32 method646(final int n, final int n2, final Canvas canvas, final byte b) {
        try {
            try {
                final Class98_Sub32 class98_Sub32 = (Class98_Sub32)Class.forName("Class98_Sub32_Sub2").newInstance();
                class98_Sub32.method1441(n, n2, 4095, canvas);
                if (b >= -28) {
                    Class64_Sub23.anInt3708 = 90;
                }
                return class98_Sub32;
            }
            catch (Throwable t) {
                final Class98_Sub32_Sub1 class98_Sub32_Sub1 = new Class98_Sub32_Sub1();
                class98_Sub32_Sub1.method1441(n, n2, 4095, canvas);
                return class98_Sub32_Sub1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "saa.H(" + n + ',' + n2 + ',' + ((canvas != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final boolean method647(final int n) {
        try {
            if (!Class144.method2311(false, super.aClass98_Sub27_495.aClass64_Sub8_4042.method583((byte)127))) {
                return false;
            }
            if (n != -1) {
                method649(78);
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "saa.G(" + n + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "saa.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    Class64_Sub23(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b < 118) {
                Class64_Sub23.aClass370_3707 = null;
            }
            if (super.aClass98_Sub27_495.aClass64_Sub8_4042.method586(true) && !Class144.method2311(false, super.aClass98_Sub27_495.aClass64_Sub8_4042.method583((byte)120))) {
                super.anInt494 = 0;
            }
            if (~super.anInt494 > -1 || super.anInt494 > 2) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "saa.C(" + b + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return 77;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "saa.A(" + n + ')');
        }
    }
    
    final int method648(final byte b) {
        try {
            if (b < 119) {
                return -112;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "saa.E(" + b + ')');
        }
    }
    
    Class64_Sub23(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    public static void method649(final int n) {
        try {
            Class64_Sub23.aClass370_3707 = null;
            if (n >= -50) {
                method646(76, 110, null, (byte)(-57));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "saa.D(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                this.method551((byte)(-94));
            }
            if (!Class144.method2311(false, super.aClass98_Sub27_495.aClass64_Sub8_4042.method583((byte)125))) {
                return 3;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "saa.F(" + n + ',' + n2 + ')');
        }
    }
}
