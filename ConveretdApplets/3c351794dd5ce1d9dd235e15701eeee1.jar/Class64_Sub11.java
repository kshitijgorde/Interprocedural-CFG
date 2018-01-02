// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub11 extends Class64
{
    static int anInt3668;
    static double aDouble3669;
    static byte[][] aByteArrayArray3670;
    
    static final byte[] method595(final int n, final byte b, final byte[] array, final int n2) {
        try {
            if (b >= -116) {
                method598(-74);
            }
            byte[] array2;
            if (~n >= -1) {
                array2 = array;
            }
            else {
                array2 = new byte[n2];
                for (int n3 = 0; ~n3 > ~n2; ++n3) {
                    array2[n3] = array[n3 + n];
                }
            }
            final Class374 class374 = new Class374();
            class374.method3983((byte)(-77));
            class374.method3981(array2, 64, n2 * 8);
            final byte[] array3 = new byte[64];
            class374.method3982((byte)(-111), 0, array3);
            return array3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hn.D(" + n + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                return -66;
            }
            if (super.aClass98_Sub27_495.method1291((byte)121)) {
                return 3;
            }
            if (super.aClass98_Sub27_495.method1286((byte)104) == s_Sub1.aClass279_5197) {
                return 1;
            }
            return 3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hn.F(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b >= 118) {
                Label_0052: {
                    if (super.aClass98_Sub27_495.method1286((byte)104) != s_Sub1.aClass279_5197) {
                        super.anInt494 = 1;
                        if (!client.aBoolean3553) {
                            break Label_0052;
                        }
                    }
                    if (super.aClass98_Sub27_495.method1291((byte)112)) {
                        super.anInt494 = 0;
                    }
                }
                if (super.anInt494 != 0 && ~super.anInt494 != 0xFFFFFFFE) {
                    super.anInt494 = this.method552(0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hn.C(" + b + ')');
        }
    }
    
    final int method596(final byte b) {
        try {
            if (b < 119) {
                Class64_Sub11.aDouble3669 = 0.8269233149088107;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hn.E(" + b + ')');
        }
    }
    
    final boolean method597(final int n) {
        try {
            if (super.aClass98_Sub27_495.method1291((byte)117)) {
                return false;
            }
            if (super.aClass98_Sub27_495.method1286((byte)104) == s_Sub1.aClass279_5197) {
                return true;
            }
            if (n != -1) {
                method595(-108, (byte)43, null, -37);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hn.G(" + n + ')');
        }
    }
    
    public static void method598(final int n) {
        try {
            Class64_Sub11.aByteArrayArray3670 = null;
            if (n != 0) {
                Class64_Sub11.aDouble3669 = -0.004191568579013829;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hn.H(" + n + ')');
        }
    }
    
    Class64_Sub11(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return 13;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hn.A(" + n + ')');
        }
    }
    
    Class64_Sub11(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hn.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    static {
        Class64_Sub11.anInt3668 = 0;
        Class64_Sub11.aByteArrayArray3670 = new byte[50][];
    }
}
