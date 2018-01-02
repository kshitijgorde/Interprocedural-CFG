// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub17 extends Class64
{
    static int anInt3684;
    static int[] anIntArray3685;
    static float aFloat3686;
    static Class207 aClass207_3687;
    
    public static void method616(final int n) {
        try {
            Class64_Sub17.anIntArray3685 = null;
            Class64_Sub17.aClass207_3687 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nm.D(" + n + ')');
        }
    }
    
    final int method617(final byte b) {
        try {
            if (b <= 119) {
                return -123;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nm.E(" + b + ')');
        }
    }
    
    Class64_Sub17(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return -93;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nm.A(" + n + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            final int method2318 = super.aClass98_Sub27_495.method1289(-101).method2318(-1);
            if (method2318 < 96) {
                super.anInt494 = 0;
            }
            if (b < 118) {
                Class64_Sub17.aFloat3686 = 0.5277947f;
            }
            if (super.anInt494 > 1 && ~method2318 > -129) {
                super.anInt494 = 1;
            }
            if (super.anInt494 > 2 && method2318 < 192) {
                super.anInt494 = 2;
            }
            if (super.anInt494 < 0 || ~super.anInt494 < -4) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nm.C(" + b + ')');
        }
    }
    
    static final void method618(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final int method3219 = Class263.method3219(false, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, n6);
            final int i = Class263.method3219(false, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, n);
            final int method3220 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n2);
            final int method3221 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n4);
            if (n3 != -21327) {
                Class64_Sub17.aClass207_3687 = null;
            }
            for (int n7 = method3219; i >= n7; ++n7) {
                Class333.method3761(n5, Class97.anIntArrayArray814[n7], method3220, method3221, (byte)13);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nm.M(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    Class64_Sub17(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nm.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    static final void method619(final Class1 class1) {
        if (Class226.anInt1705 < 65535) {
            final Class98_Sub5 aClass98_Sub5_55 = class1.aClass98_Sub5_55;
            Class98_Sub10_Sub31.aClass1Array5717[Class226.anInt1705] = class1;
            Class21_Sub4.aBooleanArray5399[Class226.anInt1705] = false;
            ++Class226.anInt1705;
            int anInt57 = class1.anInt57;
            if (class1.aBoolean54) {
                anInt57 = 0;
            }
            int anInt58 = class1.anInt57;
            if (class1.aBoolean58) {
                anInt58 = Class364.anInt3103 - 1;
            }
            for (int i = anInt57; i <= anInt58; ++i) {
                int n = 0;
                int n2 = aClass98_Sub5_55.method962(28699) - aClass98_Sub5_55.method958(-96) + Class207.anInt1577 >> Class151_Sub8.anInt5015;
                if (n2 < 0) {
                    n -= n2;
                    n2 = 0;
                }
                int n3 = aClass98_Sub5_55.method962(28699) + aClass98_Sub5_55.method958(127) - Class207.anInt1577 >> Class151_Sub8.anInt5015;
                if (n3 >= Class64_Sub9.anInt3662) {
                    n3 = Class64_Sub9.anInt3662 - 1;
                }
                for (int j = n2; j <= n3; ++j) {
                    final short n4 = class1.aShortArray59[n++];
                    int n5 = (aClass98_Sub5_55.method954(7019) - aClass98_Sub5_55.method958(126) + Class207.anInt1577 >> Class151_Sub8.anInt5015) + (n4 >>> 8);
                    int n6 = n5 + (n4 & 0xFF) - 1;
                    if (n5 < 0) {
                        n5 = 0;
                    }
                    if (n6 >= Class366.anInt3112) {
                        n6 = Class366.anInt3112 - 1;
                    }
                    for (int k = n5; k <= n6; ++k) {
                        final long n7 = Class373_Sub3.aLongArrayArrayArray5476[i][k][j];
                        if ((n7 & 0xFFFFL) == 0x0L) {
                            Class373_Sub3.aLongArrayArrayArray5476[i][k][j] = (n7 | Class226.anInt1705);
                        }
                        else if ((n7 & 0xFFFF0000L) == 0x0L) {
                            Class373_Sub3.aLongArrayArrayArray5476[i][k][j] = (n7 | Class226.anInt1705 << 16);
                        }
                        else if ((n7 & 0xFFFF00000000L) == 0x0L) {
                            Class373_Sub3.aLongArrayArrayArray5476[i][k][j] = (n7 | Class226.anInt1705 << 32);
                        }
                        else if ((n7 & 0xFFFF000000000000L) == 0x0L) {
                            Class373_Sub3.aLongArrayArrayArray5476[i][k][j] = (n7 | Class226.anInt1705 << 48);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            final int method2318 = super.aClass98_Sub27_495.method1289(-108).method2318(-1);
            if (method2318 < 96) {
                return 3;
            }
            if (n > 1 && method2318 < 128) {
                return 3;
            }
            if (n2 != 29053) {
                this.method621(125);
            }
            if (n > 3 && method2318 < 192) {
                return 3;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nm.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method620(final int n, final int n2, final boolean b, final int n3, final int n4) {
        try {
            Label_0067: {
                if (-n2 + n3 < Class76_Sub8.anInt3778 || n2 + n3 > Class3.anInt77 || Class98_Sub10_Sub38.anInt5753 > n - n2 || n - -n2 > Class218.anInt1635) {
                    Class96.method924((byte)(-109), n, n2, n3, n4);
                    if (!client.aBoolean3553) {
                        break Label_0067;
                    }
                }
                Class151_Sub1.method2446((byte)(-127), n3, n4, n2, n);
            }
            if (!b) {
                method616(-17);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nm.H(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final boolean method621(final int n) {
        try {
            if (n != -1) {
                method619(null);
            }
            return ~super.aClass98_Sub27_495.method1289(-103).method2318(n) <= -97;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nm.G(" + n + ')');
        }
    }
    
    static {
        Class64_Sub17.anInt3684 = 0;
        Class64_Sub17.anIntArray3685 = new int[] { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3 };
    }
}
