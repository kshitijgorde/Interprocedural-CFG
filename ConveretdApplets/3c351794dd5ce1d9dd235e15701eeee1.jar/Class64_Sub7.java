// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub7 extends Class64
{
    static OutgoingOpcode aClass171_3657;
    
    static final char method576(final byte b, final byte b2) {
        try {
            int n = 0xFF & b;
            if (~n == -1) {
                throw new IllegalArgumentException("Non cp1252 character 0x" + Integer.toString(n, 16) + " provided");
            }
            if (n >= 128 && ~n > -161) {
                int n2 = Class65.aCharArray497[-128 + n];
                if (~n2 == -1) {
                    n2 = 63;
                }
                n = n2;
            }
            if (b2 < 118) {
                return '\u0013';
            }
            return (char)n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ds.D(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ds.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    public static void method577(final int n) {
        try {
            Class64_Sub7.aClass171_3657 = null;
            if (n != 16) {
                Class64_Sub7.aClass171_3657 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ds.I(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                Class64_Sub7.aClass171_3657 = null;
            }
            if (super.aClass98_Sub27_495.method1291((byte)127)) {
                return 3;
            }
            if (super.aClass98_Sub27_495.aClass64_Sub20_4056.method634((byte)123) == 0) {
                return 3;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ds.F(" + n + ',' + n2 + ')');
        }
    }
    
    Class64_Sub7(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    Class64_Sub7(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                method577(36);
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ds.A(" + n + ')');
        }
    }
    
    static final void method578(final int n) {
        try {
            if (n != 16543) {
                method578(-104);
            }
            for (Class98_Sub36 class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3998(123); class98_Sub36 != null; class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3995(n - 16544)) {
                if (!class98_Sub36.aClass237_Sub1_4157.method2902(true)) {
                    class98_Sub36.aClass237_Sub1_4157.method2916((byte)44);
                    try {
                        class98_Sub36.aClass237_Sub1_4157.method2904(false);
                    }
                    catch (Exception ex) {
                        Class305_Sub1.method3585(ex, -121, "TV: " + class98_Sub36.anInt4160);
                        Class98_Sub11.method1127((byte)67, class98_Sub36.anInt4160);
                    }
                    if (!class98_Sub36.aBoolean4155 && !class98_Sub36.aBoolean4153) {
                        final Class98_Sub43_Sub1 method2900 = class98_Sub36.aClass237_Sub1_4157.method2900((byte)(-79));
                        if (method2900 != null) {
                            final Class98_Sub31_Sub4 method2901 = method2900.method1488((byte)92);
                            if (method2901 != null) {
                                method2901.method1392(n + 255170282, class98_Sub36.anInt4152);
                                Class81.aClass98_Sub31_Sub3_619.method1371(method2901);
                                class98_Sub36.aBoolean4155 = true;
                            }
                        }
                    }
                }
                else {
                    Class98_Sub11.method1127((byte)67, class98_Sub36.anInt4160);
                }
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "ds.H(" + n + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b < 118) {
                this.method579((byte)45);
            }
            if (super.aClass98_Sub27_495.method1291((byte)119)) {
                super.anInt494 = 0;
            }
            if (~super.aClass98_Sub27_495.aClass64_Sub20_4056.method634((byte)120) == -1) {
                super.anInt494 = 0;
            }
            if (super.anInt494 < 0 || super.anInt494 > 2) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ds.C(" + b + ')');
        }
    }
    
    final int method579(final byte b) {
        try {
            if (b < 119) {
                Class64_Sub7.aClass171_3657 = null;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ds.E(" + b + ')');
        }
    }
    
    static final void method580(final ha ha, final int n) {
        try {
            for (Class246_Sub5 class246_Sub5 = (Class246_Sub5)Class267.aClass218_2002.method2803((byte)15); class246_Sub5 != null; class246_Sub5 = (Class246_Sub5)Class267.aClass218_2002.method2809(false)) {
                if (class246_Sub5.aBoolean5108) {
                    class246_Sub5.method3118(ha);
                }
            }
            if (n < 15) {
                method577(15);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ds.M(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final boolean method581(final int n) {
        try {
            return n == -1 && !super.aClass98_Sub27_495.method1291((byte)106) && super.aClass98_Sub27_495.aClass64_Sub20_4056.method634((byte)120) != 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ds.G(" + n + ')');
        }
    }
    
    static {
        Class64_Sub7.aClass171_3657 = new OutgoingOpcode(21, -1);
    }
}
