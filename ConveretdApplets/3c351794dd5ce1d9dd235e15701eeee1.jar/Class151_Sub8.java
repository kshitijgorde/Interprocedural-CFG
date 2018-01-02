import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class151_Sub8 extends Class151
{
    private boolean aBoolean5010;
    static IncomingOpcode aClass58_5011;
    private Class91 aClass91_5012;
    static Class98_Sub31_Sub2 aClass98_Sub31_Sub2_5013;
    static int[] anIntArray5014;
    static int anInt5015;
    static int anInt5016;
    
    Class151_Sub8(final ha_Sub1 ha_Sub1) {
        super(ha_Sub1);
        this.aBoolean5010 = false;
        try {
            if (ha_Sub1.aBoolean4391) {
                (this.aClass91_5012 = new Class91(ha_Sub1, 2)).method887(0, -30389);
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1899(7681, 8960, 34165);
                super.aHa_Sub1_1215.method1840(2, 770, 86, 34168);
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 34167);
                OpenGL.glTexGeni(8192, 9472, 34066);
                OpenGL.glTexGeni(8193, 9472, 34066);
                OpenGL.glTexGeni(8194, 9472, 34066);
                OpenGL.glEnable(3168);
                OpenGL.glEnable(3169);
                OpenGL.glEnable(3170);
                super.aHa_Sub1_1215.method1845(0, 847872872);
                this.aClass91_5012.method886((byte)100);
                this.aClass91_5012.method887(1, -30389);
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1899(8448, 8960, 8448);
                super.aHa_Sub1_1215.method1840(2, 770, -62, 34166);
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 5890);
                OpenGL.glDisable(3168);
                OpenGL.glDisable(3169);
                OpenGL.glDisable(3170);
                OpenGL.glMatrixMode(5890);
                OpenGL.glLoadIdentity();
                OpenGL.glMatrixMode(5888);
                super.aHa_Sub1_1215.method1845(0, 847872872);
                this.aClass91_5012.method886((byte)(-125));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vj.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2468(final byte b) {
        try {
            Class151_Sub8.aClass98_Sub31_Sub2_5013 = null;
            Class151_Sub8.anIntArray5014 = null;
            Class151_Sub8.aClass58_5011 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vj.B(" + b + ')');
        }
    }
    
    @Override
    final void method2441(final int n, final int n2, final int n3) {
        try {
            if (n3 > -2) {
                this.method2445((byte)108);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vj.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2440(final boolean b, final boolean b2) {
        try {
            final Class42_Sub2 method1827 = super.aHa_Sub1_1215.method1827(-126);
            if (b) {
                Class151_Sub8.aClass98_Sub31_Sub2_5013 = null;
            }
            if (this.aClass91_5012 != null && method1827 != null && b2) {
                this.aClass91_5012.method888('\0', b);
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1863(1, method1827);
                OpenGL.glMatrixMode(5890);
                OpenGL.glLoadMatrixf(super.aHa_Sub1_1215.aClass111_Sub1_4354.method2116(54), 0);
                OpenGL.glMatrixMode(5888);
                super.aHa_Sub1_1215.method1845(0, 847872872);
                this.aBoolean5010 = true;
            }
            else {
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 34168);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vj.D(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method2442(final Class42 class42, final boolean b, final int n) {
        try {
            if (!b) {
                super.aHa_Sub1_1215.method1863(1, class42);
                super.aHa_Sub1_1215.method1896(260, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vj.F(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2443(final boolean b, final int n) {
        try {
            super.aHa_Sub1_1215.method1899(7681, n + 8705, 8448);
            if (n != 255) {
                Class151_Sub8.aClass58_5011 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vj.C(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final boolean method2439(final int n) {
        try {
            if (n != 31565) {
                Class151_Sub8.aClass98_Sub31_Sub2_5013 = null;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vj.A(" + n + ')');
        }
    }
    
    @Override
    final void method2445(final byte b) {
        try {
            Label_0070: {
                if (!this.aBoolean5010) {
                    super.aHa_Sub1_1215.method1886(770, 0, 34200, 5890);
                    if (!client.aBoolean3553) {
                        break Label_0070;
                    }
                }
                this.aClass91_5012.method888('\u0001', false);
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1863(1, null);
                super.aHa_Sub1_1215.method1845(0, 847872872);
            }
            super.aHa_Sub1_1215.method1899(8448, 8960, 8448);
            if (b <= 25) {
                Class151_Sub8.aClass98_Sub31_Sub2_5013 = null;
            }
            this.aBoolean5010 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vj.E(" + b + ')');
        }
    }
    
    static {
        Class151_Sub8.aClass58_5011 = new IncomingOpcode(43, -1);
        Class151_Sub8.anIntArray5014 = new int[4096];
        for (int n = 0; ~n > -4097; ++n) {
            Class151_Sub8.anIntArray5014[n] = Class222.method2825(-83, n);
        }
        Class151_Sub8.anInt5016 = 0;
    }
}
