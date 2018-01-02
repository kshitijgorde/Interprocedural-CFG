import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class151_Sub7 extends Class151
{
    static Class183 aClass183_5001;
    private Class51 aClass51_5002;
    private Class91 aClass91_5003;
    static Class79 aClass79_5004;
    static int anInt5005;
    static int anInt5006;
    static boolean aBoolean5007;
    static Class348 aClass348_5008;
    static Class326 aClass326_5009;
    
    @Override
    final void method2445(final byte b) {
        try {
            this.aClass91_5003.method888('\u0001', false);
            if (b <= 25) {
                Class151_Sub7.aClass326_5009 = null;
            }
            super.aHa_Sub1_1215.method1845(1, 847872872);
            super.aHa_Sub1_1215.method1863(1, null);
            super.aHa_Sub1_1215.method1845(0, 847872872);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sl.E(" + b + ')');
        }
    }
    
    static final void method2466(final int n) {
        try {
            Class175.method2578();
            if (n != -32346) {
                Class151_Sub7.aClass348_5008 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sl.B(" + n + ')');
        }
    }
    
    @Override
    final void method2443(final boolean b, final int n) {
        try {
            if (n != 255) {
                this.aClass51_5002 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sl.C(" + b + ',' + n + ')');
        }
    }
    
    Class151_Sub7(final ha_Sub1 ha_Sub1, final Class51 aClass51_5002) {
        super(ha_Sub1);
        try {
            this.aClass51_5002 = aClass51_5002;
            (this.aClass91_5003 = new Class91(ha_Sub1, 2)).method887(0, -30389);
            super.aHa_Sub1_1215.method1845(1, 847872872);
            if (this.aClass51_5002.aBoolean424) {
                OpenGL.glTexGeni(8194, 9472, 9217);
                OpenGL.glEnable(3170);
            }
            OpenGL.glTexGeni(8192, 9472, 9216);
            OpenGL.glTexGeni(8193, 9472, 9216);
            OpenGL.glEnable(3168);
            OpenGL.glEnable(3169);
            super.aHa_Sub1_1215.method1845(0, 847872872);
            this.aClass91_5003.method886((byte)(-50));
            this.aClass91_5003.method887(1, -30389);
            super.aHa_Sub1_1215.method1845(1, 847872872);
            if (this.aClass51_5002.aBoolean424) {
                OpenGL.glDisable(3170);
            }
            OpenGL.glDisable(3168);
            OpenGL.glDisable(3169);
            super.aHa_Sub1_1215.method1845(0, 847872872);
            this.aClass91_5003.method886((byte)53);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sl.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + ((aClass51_5002 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2439(final int n) {
        try {
            if (n != 31565) {
                Class151_Sub7.anInt5006 = -90;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sl.A(" + n + ')');
        }
    }
    
    public static void method2467(final int n) {
        try {
            Class151_Sub7.aClass348_5008 = null;
            Class151_Sub7.aClass79_5004 = null;
            Class151_Sub7.aClass183_5001 = null;
            if (n != 0) {
                method2466(-69);
            }
            Class151_Sub7.aClass326_5009 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sl.H(" + n + ')');
        }
    }
    
    @Override
    final void method2442(final Class42 class42, final boolean b, final int n) {
        try {
            super.aHa_Sub1_1215.method1863(1, class42);
            super.aHa_Sub1_1215.method1896(260, n);
            if (b) {
                method2466(-73);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sl.F(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2441(final int n, final int n2, final int n3) {
        try {
            final float n4 = ((0x3 & n) + 1) * -5.0E-4f;
            final float n5 = (1 + ((0x1D & n) >> -1356659485)) * 5.0E-4f;
            final float n6 = (~(0x40 & n) != -1) ? 9.765625E-4f : 4.8828125E-4f;
            if (n3 > -2) {
                this.aClass91_5003 = null;
            }
            super.aHa_Sub1_1215.method1845(1, 847872872);
            if (~(n & 0x80) != -1) {
                Class98_Sub43_Sub4.aFloatArray5940[0] = n6;
                Class98_Sub43_Sub4.aFloatArray5940[2] = 0.0f;
                Class98_Sub43_Sub4.aFloatArray5940[3] = 0.0f;
                Class98_Sub43_Sub4.aFloatArray5940[1] = 0.0f;
            }
            else {
                Class98_Sub43_Sub4.aFloatArray5940[3] = 0.0f;
                Class98_Sub43_Sub4.aFloatArray5940[0] = 0.0f;
                Class98_Sub43_Sub4.aFloatArray5940[2] = n6;
                Class98_Sub43_Sub4.aFloatArray5940[1] = 0.0f;
            }
            OpenGL.glTexGenfv(8192, 9474, Class98_Sub43_Sub4.aFloatArray5940, 0);
            Class98_Sub43_Sub4.aFloatArray5940[0] = 0.0f;
            Class98_Sub43_Sub4.aFloatArray5940[1] = n6;
            Class98_Sub43_Sub4.aFloatArray5940[3] = super.aHa_Sub1_1215.anInt4321 * n4 % 1.0f;
            Class98_Sub43_Sub4.aFloatArray5940[2] = 0.0f;
            OpenGL.glTexGenfv(8193, 9474, Class98_Sub43_Sub4.aFloatArray5940, 0);
            if (this.aClass51_5002.aBoolean424) {
                Class98_Sub43_Sub4.aFloatArray5940[0] = 0.0f;
                Class98_Sub43_Sub4.aFloatArray5940[3] = n5 * super.aHa_Sub1_1215.anInt4321 % 1.0f;
                Class98_Sub43_Sub4.aFloatArray5940[2] = 0.0f;
                Class98_Sub43_Sub4.aFloatArray5940[1] = 0.0f;
                OpenGL.glTexGenfv(8194, 9473, Class98_Sub43_Sub4.aFloatArray5940, 0);
            }
            else {
                super.aHa_Sub1_1215.method1863(1, this.aClass51_5002.aClass42_Sub1Array420[(int)(16.0f * (n5 * super.aHa_Sub1_1215.anInt4321)) % 16]);
            }
            super.aHa_Sub1_1215.method1845(0, 847872872);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sl.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2440(final boolean b, final boolean b2) {
        try {
            this.aClass91_5003.method888('\0', b);
            if (this.aClass51_5002.aBoolean424) {
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1863(1, this.aClass51_5002.aClass42_Sub4_425);
                super.aHa_Sub1_1215.method1845(0, 847872872);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sl.D(" + b + ',' + b2 + ')');
        }
    }
    
    static {
        Class151_Sub7.aClass79_5004 = new Class79(4);
        Class151_Sub7.aClass348_5008 = new Class348(11, 0, 1, 2);
    }
}
