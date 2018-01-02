import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub5_Sub3 extends Class98_Sub5
{
    static int anInt5538;
    static boolean aBoolean5539;
    static Class111 aClass111_5540;
    
    @Override
    final void method959(final float aFloat3832, final int n) {
        try {
            super.aFloat3832 = aFloat3832;
            if (n <= 12) {
                Class98_Sub5_Sub3.aClass111_5540 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lh.D(" + aFloat3832 + ',' + n + ')');
        }
    }
    
    static final int method971(final int n, final int n2, final int n3, final int n4) {
        try {
            if (n4 > -39) {
                return -27;
            }
            final int n5 = n / n3;
            final int n6 = n & n3 - 1;
            final int n7 = n2 / n3;
            return Class98_Sub21.method1180(n3, (byte)104, Class98_Sub21.method1180(n3, (byte)(-38), Class156_Sub1.method2499(19, n7, n5), n6, Class156_Sub1.method2499(-117, n7, n5 + 1)), n2 & n3 - 1, Class98_Sub21.method1180(n3, (byte)107, Class156_Sub1.method2499(84, n7 + 1, n5), n6, Class156_Sub1.method2499(19, 1 + n7, n5 + 1)));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lh.L(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final Class98_Sub27 method972(final int n) {
        try {
            Class356 class356 = null;
            Class98_Sub27 class98_Sub27 = new Class98_Sub27(Class4.aClass279_86, 0);
            try {
                final Class143 method875 = Class98_Sub43_Sub2.aClass88_5907.method875("", true, 21516);
                while (~method875.anInt1163 == -1) {
                    Class246_Sub7.method3131(0, 1L);
                }
                if (method875.anInt1163 == 1) {
                    class356 = (Class356)method875.anObject1162;
                    final byte[] array = new byte[(int)class356.method3878((byte)(-112))];
                    int method876;
                    for (int n2 = 0; ~array.length < ~n2; n2 += method876) {
                        method876 = class356.method3879(array.length - n2, (byte)(-26), n2, array);
                        if (method876 == -1) {
                            throw new IOException("EOF");
                        }
                    }
                    class98_Sub27 = new Class98_Sub27(new Class98_Sub22(array), Class4.aClass279_86, 0);
                }
            }
            catch (Exception ex2) {}
            try {
                if (class356 != null) {
                    class356.method3880(true);
                }
            }
            catch (Exception ex3) {}
            return class98_Sub27;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lh.K(" + n + ')');
        }
    }
    
    Class98_Sub5_Sub3(final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
        super(n, n2, n3, n4, n5, n6);
    }
    
    public static void method973(final int n) {
        try {
            Class98_Sub5_Sub3.aClass111_5540 = null;
            if (n != 1) {
                Class98_Sub5_Sub3.aBoolean5539 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lh.N(" + n + ')');
        }
    }
    
    static final boolean method974(final int n, final byte b) {
        try {
            if (b > -36) {
                method972(-63);
            }
            return n == 7 || ~n == 0xFFFFFFF6;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lh.M(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method955(final int anInt3834, final byte b, final int anInt3835, final int anInt3836) {
        try {
            super.anInt3834 = anInt3834;
            super.anInt3833 = anInt3835;
            if (b > -120) {
                method972(-17);
            }
            super.anInt3830 = anInt3836;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lh.A(" + anInt3834 + ',' + b + ',' + anInt3835 + ',' + anInt3836 + ')');
        }
    }
    
    static {
        Class98_Sub5_Sub3.aBoolean5539 = false;
        Class98_Sub5_Sub3.anInt5538 = 1;
    }
}
