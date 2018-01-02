import java.awt.AWTEvent;
import java.awt.event.ActionEvent;

// 
// Decompiled by Procyon v0.5.30
// 

final class PlayerUpdate implements Interface14
{
    static Class79 aClass79_3411;
    private Class207 aClass207_3412;
    static boolean aBoolean3413;
    
    static final void method2855(final int n) {
        try {
            Class98_Sub37.aHa4185.method1791(Class46.aFloat388, Class260.aFloat3260, Class97.aFloat831);
            if (n != -19004) {
                PlayerUpdate.aBoolean3413 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oh.A(" + n + ')');
        }
    }
    
    public static void method2856(final int n) {
        try {
            PlayerUpdate.aClass79_3411 = null;
            if (n != 0) {
                PlayerUpdate.aClass79_3411 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oh.G(" + n + ')');
        }
    }
    
    static final void method2857(final Class88 class88, final int n, final Object o) {
        try {
            if (class88.anEventQueue698 != null) {
                for (int n2 = 0; ~n2 > -51 && class88.anEventQueue698.peekEvent() != null; ++n2) {
                    Class246_Sub7.method3131(0, 1L);
                }
                try {
                    if (n == 31668) {
                        if (o != null) {
                            class88.anEventQueue698.postEvent(new ActionEvent(o, 1001, "dummy"));
                        }
                    }
                }
                catch (Exception ex2) {}
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oh.D(" + ((class88 != null) ? "{...}" : "null") + ',' + n + ',' + ((o != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method2858(final Class98_Sub22_Sub1 class98_Sub22_Sub1, final int n) {
        try {
            class98_Sub22_Sub1.method1256(0);
            if (class98_Sub22_Sub1.readBits((byte)(-106), 1) == 1) {
                Class98_Sub10_Sub20.method1060(za_Sub2.anInt6080, 12, class98_Sub22_Sub1);
            }
            for (int i = 0; i < Class2.anInt71 - 1; ++i) {
                if (class98_Sub22_Sub1.readBits((byte)(-106), 1) == 1) {
                    Class98_Sub10_Sub20.method1060(class98_Sub22_Sub1.readBits((byte)(-106), 11), 12, class98_Sub22_Sub1);
                }
            }
            while (class98_Sub22_Sub1.readBits((byte)(-106), 1) != 0) {
                Class351.method3845(-2, class98_Sub22_Sub1.readBits((byte)(-106), 11), class98_Sub22_Sub1);
            }
            class98_Sub22_Sub1.method1254((byte)120);
            Class373_Sub2.anInt5473 = 0;
            Class2.anInt71 = 0;
            for (int n2 = 1; ~n2 > -2049; ++n2) {
                final byte[] aByteArray4075 = Class98_Sub27.aByteArray4075;
                final int n3 = n2;
                aByteArray4075[n3] >>= 1;
                if (Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n2] == null) {
                    Class76_Sub9.anIntArray3791[Class373_Sub2.anInt5473++] = n2;
                }
                else {
                    Class319.anIntArray2705[Class2.anInt71++] = n2;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oh.C(" + ((class98_Sub22_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final Class191 method50(final int n) {
        try {
            if (n != 15763) {
                method2855(61);
            }
            return Class191.aClass191_1473;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oh.B(" + n + ')');
        }
    }
    
    static final int method2859(final int n, final boolean b, final String s, final int n2) {
        try {
            if (n < 2 || ~n < -37) {
                throw new IllegalArgumentException("Invalid radix:" + n);
            }
            boolean b2 = false;
            if (n2 != -21972) {
                method2859(-69, true, null, 22);
            }
            boolean b3 = false;
            int n3 = 0;
            for (int length = s.length(), n4 = 0; ~length < ~n4; ++n4) {
                int char1 = s.charAt(n4);
                if (n4 == 0) {
                    if (char1 == 45) {
                        b2 = true;
                        continue;
                    }
                    if (char1 == 43 && b) {
                        continue;
                    }
                }
                if (char1 >= 48 && ~char1 >= -58) {
                    char1 -= 48;
                }
                else if (~char1 <= -66 && char1 <= 90) {
                    char1 -= 55;
                }
                else {
                    if (char1 < 97 || ~char1 < -123) {
                        throw new NumberFormatException();
                    }
                    char1 -= 87;
                }
                if (n <= char1) {
                    throw new NumberFormatException();
                }
                if (b2) {
                    char1 = -char1;
                }
                final int n5 = n3 * n - -char1;
                if (n5 / n != n3) {
                    throw new NumberFormatException();
                }
                n3 = n5;
                b3 = true;
            }
            if (!b3) {
                throw new NumberFormatException();
            }
            return n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oh.F(" + n + ',' + b + ',' + ((s != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    @Override
    public final int method51(final byte b) {
        try {
            if (b <= 126) {
                this.aClass207_3412 = null;
            }
            if (this.aClass207_3412.method2730((byte)78)) {
                return 100;
            }
            return this.aClass207_3412.method2762((byte)(-114));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oh.E(" + b + ')');
        }
    }
    
    PlayerUpdate(final Class207 aClass207_3412) {
        try {
            this.aClass207_3412 = aClass207_3412;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oh.<init>(" + ((aClass207_3412 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        PlayerUpdate.aClass79_3411 = new Class79(5);
        PlayerUpdate.aBoolean3413 = false;
    }
}
