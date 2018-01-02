// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub20 extends Class64
{
    static Class218 aClass218_3694;
    static Class207 aClass207_3695;
    static int anInt3696;
    static Class207 aClass207_3697;
    static int anInt3698;
    static Class178[] aClass178Array3699;
    
    @Override
    final void method551(final byte b) {
        try {
            if (b >= 118) {
                Label_0055: {
                    if (super.aClass98_Sub27_495.method1286((byte)104) == s_Sub1.aClass279_5197) {
                        if (!super.aClass98_Sub27_495.method1291((byte)117)) {
                            break Label_0055;
                        }
                        super.anInt494 = 0;
                        if (!client.aBoolean3553) {
                            break Label_0055;
                        }
                    }
                    super.anInt494 = 1;
                }
                if (super.anInt494 != 0 && super.anInt494 != 1) {
                    super.anInt494 = this.method552(0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "raa.C(" + b + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                this.method556(68, 122);
            }
            if (super.aClass98_Sub27_495.method1286((byte)104) != s_Sub1.aClass279_5197) {
                return 3;
            }
            if (super.aClass98_Sub27_495.method1291((byte)114)) {
                return 3;
            }
            if (n == 0 || super.aClass98_Sub27_495.aClass64_Sub25_4039.method655((byte)126) == 1) {
                return 1;
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "raa.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method633(final int n) {
        try {
            if (Class258.aClass155Array1951 != null) {
                for (int n2 = 0; ~Class21_Sub3.anInt5389 < ~n2; ++n2) {
                    Class258.aClass155Array1951[n2] = null;
                }
                Class258.aClass155Array1951 = null;
            }
            if (n != 0) {
                Class64_Sub20.aClass178Array3699 = null;
            }
            if (Class98_Sub30.aClass155Array4099 != null) {
                for (int n3 = 0; ~n3 > ~Class336.anInt2820; ++n3) {
                    Class98_Sub30.aClass155Array4099[n3] = null;
                }
                Class98_Sub30.aClass155Array4099 = null;
            }
            if (Class98_Sub32_Sub1.aClass155Array5889 != null) {
                for (int i = 0; i < ha.anInt936; ++i) {
                    Class98_Sub32_Sub1.aClass155Array5889[i] = null;
                }
                Class98_Sub32_Sub1.aClass155Array5889 = null;
            }
            Class213.aClass155Array1611 = null;
            Class111_Sub3.anIntArray4707 = null;
            Class64_Sub3.anInt3646 = (IncomingOpcode.anInt461 = -1);
            Class98_Sub46_Sub13_Sub2.anIntArrayArrayArray6311 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "raa.I(" + n + ')');
        }
    }
    
    final int method634(final byte b) {
        try {
            if (b <= 119) {
                method637(-83);
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "raa.E(" + b + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "raa.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    final boolean method635(final int n) {
        try {
            if (n != -1) {
                method633(-77);
            }
            return super.aClass98_Sub27_495.method1286((byte)104) == s_Sub1.aClass279_5197 && !super.aClass98_Sub27_495.method1291((byte)120);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "raa.G(" + n + ')');
        }
    }
    
    Class64_Sub20(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    static final Class206 method636(final byte b) {
        try {
            try {
                if (b != 5) {
                    method637(20);
                }
                return (Class206)Class.forName("Class206_Sub1").newInstance();
            }
            catch (Throwable t) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "raa.D(" + b + ')');
        }
    }
    
    public static void method637(final int n) {
        try {
            Class64_Sub20.aClass207_3695 = null;
            Class64_Sub20.aClass218_3694 = null;
            if (n != 0) {
                method636((byte)45);
            }
            Class64_Sub20.aClass207_3697 = null;
            Class64_Sub20.aClass178Array3699 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "raa.H(" + n + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                Class64_Sub20.aClass207_3695 = null;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "raa.A(" + n + ')');
        }
    }
    
    Class64_Sub20(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    static {
        Class64_Sub20.aClass218_3694 = new Class218();
        Class64_Sub20.anInt3698 = 1403;
        Class64_Sub20.aClass178Array3699 = new Class178[4];
    }
}
