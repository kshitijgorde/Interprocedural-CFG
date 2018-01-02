import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub5_Sub1 extends Class98_Sub5
{
    static Color[] aColorArray5533;
    static Class348 aClass348_5534;
    
    static final int method964(final Class162 class162, final byte b) {
        try {
            if (b != 105) {
                return -27;
            }
            if (class162 == Class162.aClass162_1262) {
                return 5120;
            }
            if (Class162.aClass162_1264 == class162) {
                return 5122;
            }
            if (Class162.aClass162_1265 == class162) {
                return 5124;
            }
            if (Class162.aClass162_1266 == class162) {
                return 5121;
            }
            if (class162 == Class162.aClass162_1267) {
                return 5123;
            }
            if (Class162.aClass162_1268 == class162) {
                return 5125;
            }
            if (class162 == Class162.aClass162_1269) {
                return 5131;
            }
            if (Class162.aClass162_1270 == class162) {
                return 5126;
            }
            throw new IllegalArgumentException("");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fi.K(" + ((class162 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final boolean method965(final byte b, final int n) {
        try {
            return n == 3 || n == 7 || n == 10;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fi.N(" + b + ',' + n + ')');
        }
    }
    
    static final boolean method966(final int n, boolean b) {
        try {
            final boolean method1768 = Class265.aHa1974.method1768();
            if (n != 29089) {
                Class98_Sub5_Sub1.aColorArray5533 = null;
            }
            if (b != !method1768) {
                return true;
            }
            Label_0066: {
                if (b) {
                    if (Class265.aHa1974.method1802()) {
                        break Label_0066;
                    }
                    b = false;
                    if (!client.aBoolean3553) {
                        break Label_0066;
                    }
                }
                Class265.aHa1974.method1756();
            }
            if (b == !method1768) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), b ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065);
                Class310.method3618(-5964);
                return true;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fi.L(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method955(final int anInt3834, final byte b, final int anInt3835, final int anInt3836) {
        try {
            super.anInt3833 = anInt3835;
            super.anInt3834 = anInt3834;
            if (b > -120) {
                Class98_Sub5_Sub1.aClass348_5534 = null;
            }
            super.anInt3830 = anInt3836;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fi.A(" + anInt3834 + ',' + b + ',' + anInt3835 + ',' + anInt3836 + ')');
        }
    }
    
    Class98_Sub5_Sub1(final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
        super(n, n2, n3, n4, n5, n6);
    }
    
    public static void method967(final byte b) {
        try {
            if (b >= 16) {
                Class98_Sub5_Sub1.aClass348_5534 = null;
                Class98_Sub5_Sub1.aColorArray5533 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fi.M(" + b + ')');
        }
    }
    
    @Override
    final void method959(final float aFloat3832, final int n) {
        try {
            super.aFloat3832 = aFloat3832;
            if (n <= 12) {
                this.method955(55, (byte)80, -52, 106);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fi.D(" + aFloat3832 + ',' + n + ')');
        }
    }
    
    static {
        Class98_Sub5_Sub1.aColorArray5533 = new Color[] { new Color(16777215), new Color(16777215), new Color(16741381), new Color(16741381) };
        Class98_Sub5_Sub1.aClass348_5534 = new Class348(14, 0, 4, 1);
    }
}
