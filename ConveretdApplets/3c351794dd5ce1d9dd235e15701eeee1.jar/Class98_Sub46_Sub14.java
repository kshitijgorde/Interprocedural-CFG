import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub14 extends Class98_Sub46 implements Interface3
{
    private ha_Sub1 aHa_Sub1_5373;
    private int anInt5374;
    private int anInt5375;
    int anInt5376;
    int anInt5377;
    static Class8 aClass8_5378;
    private int anInt5379;
    private int anInt5380;
    private int anInt5381;
    
    static final void method1602(final int n, final int n2, final int n3) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 != null) {
            Class129.method2227(class172.aClass246_Sub3_Sub1_1332);
            if (class172.aClass246_Sub3_Sub1_1332 != null) {
                class172.aClass246_Sub3_Sub1_1332 = null;
            }
        }
    }
    
    final void method1603(final int n) {
        try {
            if (n < this.anInt5381) {
                this.aHa_Sub1_5373.method1846(this.anInt5381, n ^ 0x4B, this.anInt5374);
                this.anInt5381 = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "is.C(" + n + ')');
        }
    }
    
    static final void method1604(final boolean b, final byte b2) {
        try {
            Class200.aClass111_1543.method2092(Class154.aHa1231.method1752());
            final int[] y = Class154.aHa1231.Y();
            if (b2 != 88) {
                method1604(false, (byte)(-30));
            }
            Class98_Sub10_Sub38.anInt5752 = y[3];
            Class98_Sub48.anInt4279 = y[0];
            Class96.anInt802 = y[2];
            Class54.anInt3391 = y[1];
            if (!b) {
                Class154.aHa1231.DA(Class246_Sub10.anInt5154, Class76_Sub11.anInt3798, Class138.anInt1085, Class59.anInt466);
                Class41.method367(Class291.aDouble2199, 14794);
            }
            else {
                Class154.aHa1231.DA(Class224_Sub2_Sub1.anInt6143, Class98_Sub10_Sub1.anInt5543, Class370.anInt3140, Class246_Sub3_Sub4_Sub2_Sub1.anInt6509);
                Class41.method367(Class263.aDouble1966, 14794);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "is.E(" + b + ',' + b2 + ')');
        }
    }
    
    final void method1605(final int n, final int anInt5375, final int anInt5376) {
        try {
            OpenGL.glFramebufferRenderbufferEXT(anInt5375, anInt5376, 36161, this.anInt5381);
            this.anInt5380 = anInt5376;
            this.anInt5375 = anInt5375;
            if (n != 0) {
                method1606((byte)(-93));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "is.G(" + n + ',' + anInt5375 + ',' + anInt5376 + ')');
        }
    }
    
    public static void method1606(final byte b) {
        try {
            Class98_Sub46_Sub14.aClass8_5378 = null;
            if (b <= 25) {
                method1607(null, (byte)87);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "is.F(" + b + ')');
        }
    }
    
    static final boolean method1607(final Class228 class228, final byte b) {
        try {
            if (b < 80) {
                method1607(null, (byte)113);
            }
            return class228 != null && s_Sub1.method3427(class228.anInt1713, class228.anInt1714 - class228.anInt1712, class228.anInt1708 - class228.anInt1713, class228.anInt1709 - class228.anInt1715, (byte)16, class228.anInt1715, class228.anInt1712);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "is.D(" + ((class228 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.method1603(0);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "is.finalize()");
        }
    }
    
    @Override
    public final void method3(final byte b) {
        try {
            OpenGL.glFramebufferRenderbufferEXT(this.anInt5375, this.anInt5380, 36161, 0);
            if (b > -117) {
                this.method1605(-42, -54, 110);
            }
            this.anInt5380 = -1;
            this.anInt5375 = -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "is.B(" + b + ')');
        }
    }
    
    Class98_Sub46_Sub14(final ha_Sub1 aHa_Sub1_5373, final int anInt5379, final int anInt5380, final int anInt5381) {
        this.anInt5375 = -1;
        this.anInt5380 = -1;
        try {
            this.anInt5379 = anInt5379;
            this.aHa_Sub1_5373 = aHa_Sub1_5373;
            this.anInt5377 = anInt5381;
            this.anInt5376 = anInt5380;
            OpenGL.glGenRenderbuffersEXT(1, Class76_Sub9.anIntArray3785, 0);
            OpenGL.glBindRenderbufferEXT(36161, this.anInt5381 = Class76_Sub9.anIntArray3785[0]);
            OpenGL.glRenderbufferStorageEXT(36161, this.anInt5379, this.anInt5376, this.anInt5377);
            this.anInt5374 = this.anInt5376 * (this.anInt5377 * this.aHa_Sub1_5373.method1866(-96, this.anInt5379));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "is.<init>(" + ((aHa_Sub1_5373 != null) ? "{...}" : "null") + ',' + anInt5379 + ',' + anInt5380 + ',' + anInt5381 + ')');
        }
    }
    
    Class98_Sub46_Sub14(final ha_Sub1 aHa_Sub1_5373, final int anInt5379, final int anInt5380, final int anInt5381, final int n) {
        this.anInt5375 = -1;
        this.anInt5380 = -1;
        try {
            this.aHa_Sub1_5373 = aHa_Sub1_5373;
            this.anInt5379 = anInt5379;
            this.anInt5376 = anInt5380;
            this.anInt5377 = anInt5381;
            OpenGL.glGenRenderbuffersEXT(1, Class76_Sub9.anIntArray3785, 0);
            OpenGL.glBindRenderbufferEXT(36161, this.anInt5381 = Class76_Sub9.anIntArray3785[0]);
            OpenGL.glRenderbufferStorageMultisampleEXT(36161, n, this.anInt5379, this.anInt5376, this.anInt5377);
            this.anInt5374 = this.anInt5377 * this.anInt5376 * this.aHa_Sub1_5373.method1866(-121, this.anInt5379);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "is.<init>(" + ((aHa_Sub1_5373 != null) ? "{...}" : "null") + ',' + anInt5379 + ',' + anInt5380 + ',' + anInt5381 + ',' + n + ')');
        }
    }
}
