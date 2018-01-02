import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class151_Sub6 extends Class151
{
    private Class91 aClass91_4994;
    private Class42_Sub2[] aClass42_Sub2Array4995;
    private boolean aBoolean4996;
    static IncomingOpcode aClass58_4997;
    static IncomingOpcode aClass58_4998;
    static IncomingOpcode aClass58_4999;
    private boolean aBoolean5000;
    
    Class151_Sub6(final ha_Sub1 ha_Sub1) {
        super(ha_Sub1);
        this.aBoolean4996 = false;
        try {
            if (ha_Sub1.aBoolean4391) {
                this.aBoolean5000 = (~ha_Sub1.anInt4410 > -4);
                final int n = this.aBoolean5000 ? 48 : 127;
                final byte[][] array = new byte[6][4096];
                final byte[][] array2 = new byte[6][4096];
                final byte[][] array3 = new byte[6][4096];
                int n2 = 0;
                for (int i = 0; i < 64; ++i) {
                    for (int j = 0; j < 64; ++j) {
                        final float n3 = -1.0f + 2.0f * j / 64.0f;
                        final float n4 = -1.0f + i * 2.0f / 64.0f;
                        final float n5 = (float)(1.0 / Math.sqrt(n4 * n4 + (n3 * n3 + 1.0f)));
                        final float n6 = n3 * n5;
                        final float n7 = n4 * n5;
                        for (int k = 0; k < 6; ++k) {
                            float n8;
                            if (k != 0) {
                                if (~k != 0xFFFFFFFE) {
                                    if (~k == 0xFFFFFFFD) {
                                        n8 = n7;
                                    }
                                    else if (~k != 0xFFFFFFFC) {
                                        if (k == 4) {
                                            n8 = n5;
                                        }
                                        else {
                                            n8 = -n5;
                                        }
                                    }
                                    else {
                                        n8 = -n7;
                                    }
                                }
                                else {
                                    n8 = n6;
                                }
                            }
                            else {
                                n8 = -n6;
                            }
                            int n9;
                            int n10;
                            int n11;
                            if (n8 > 0.0f) {
                                n9 = (int)(Math.pow(n8, 96.0) * n);
                                n10 = (int)(n * Math.pow(n8, 36.0));
                                n11 = (int)(n * Math.pow(n8, 12.0));
                            }
                            else {
                                n10 = (n9 = (n11 = 0));
                            }
                            array2[k][n2] = (byte)n9;
                            array3[k][n2] = (byte)n10;
                            array[k][n2] = (byte)n11;
                        }
                        ++n2;
                    }
                }
                (this.aClass42_Sub2Array4995 = new Class42_Sub2[3])[0] = new Class42_Sub2(super.aHa_Sub1_1215, 6406, 64, false, array2, 6406);
                this.aClass42_Sub2Array4995[1] = new Class42_Sub2(super.aHa_Sub1_1215, 6406, 64, false, array3, 6406);
                this.aClass42_Sub2Array4995[2] = new Class42_Sub2(super.aHa_Sub1_1215, 6406, 64, false, array, 6406);
                this.method2464(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2441(final int n, final int n2, final int n3) {
        try {
            if (this.aBoolean4996) {
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1863(1, this.aClass42_Sub2Array4995[n - 1]);
                super.aHa_Sub1_1215.method1845(0, 847872872);
            }
            if (n3 >= -2) {
                this.aBoolean4996 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2440(final boolean b, final boolean b2) {
        try {
            if (!b) {
                if (this.aClass91_4994 != null && b2) {
                    if (!this.aBoolean5000) {
                        super.aHa_Sub1_1215.method1845(2, 847872872);
                        super.aHa_Sub1_1215.method1863(1, super.aHa_Sub1_1215.aClass42_Sub1_4358);
                        super.aHa_Sub1_1215.method1845(0, 847872872);
                    }
                    this.aClass91_4994.method888('\0', false);
                    this.aBoolean4996 = true;
                    if (!client.aBoolean3553) {
                        return;
                    }
                }
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 34168);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.D(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method2445(final byte b) {
        try {
            if (b <= 25) {
                this.aBoolean5000 = true;
            }
            Label_0112: {
                if (this.aBoolean4996) {
                    if (!this.aBoolean5000) {
                        super.aHa_Sub1_1215.method1845(2, 847872872);
                        super.aHa_Sub1_1215.method1863(1, null);
                    }
                    super.aHa_Sub1_1215.method1845(1, 847872872);
                    super.aHa_Sub1_1215.method1863(1, null);
                    super.aHa_Sub1_1215.method1845(0, 847872872);
                    this.aClass91_4994.method888('\u0001', false);
                    this.aBoolean4996 = false;
                    if (!client.aBoolean3553) {
                        break Label_0112;
                    }
                }
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 5890);
            }
            super.aHa_Sub1_1215.method1899(8448, 8960, 8448);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.E(" + b + ')');
        }
    }
    
    @Override
    final boolean method2439(final int n) {
        try {
            if (n != 31565) {
                this.aBoolean5000 = true;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.A(" + n + ')');
        }
    }
    
    @Override
    final void method2442(final Class42 class42, final boolean b, final int n) {
        try {
            if (b) {
                this.method2445((byte)(-95));
            }
            super.aHa_Sub1_1215.method1863(1, class42);
            super.aHa_Sub1_1215.method1896(260, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.F(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    static final void method2463(final Class293 class293, final Class293 class294, final int n) {
        try {
            final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class213.aClass171_1604, Class331.aClass117_2811);
            method3023.aClass98_Sub22_Sub1_3865.writeInt(1571862888, class294.anInt2248);
            if (n >= 18) {
                method3023.aClass98_Sub22_Sub1_3865.writeShortA(class293.anInt2302, (byte)126);
                method3023.aClass98_Sub22_Sub1_3865.writeShort(class294.anInt2302, 1571862888);
                method3023.aClass98_Sub22_Sub1_3865.writeLEInt(class293.anInt2248, 1046032984);
                method3023.aClass98_Sub22_Sub1_3865.writeLEShortA(class294.anInt2300, 128);
                method3023.aClass98_Sub22_Sub1_3865.writeLEShort(class293.anInt2300, 17624);
                Class98_Sub10_Sub29.sendPacket(false, method3023);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.H(" + ((class293 != null) ? "{...}" : "null") + ',' + ((class294 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method2464(final int n) {
        try {
            (this.aClass91_4994 = new Class91(super.aHa_Sub1_1215, 2)).method887(0, -30389);
            super.aHa_Sub1_1215.method1845(1, 847872872);
            OpenGL.glTexGeni(8192, 9472, 34065);
            OpenGL.glTexGeni(8193, 9472, 34065);
            OpenGL.glTexGeni(8194, 9472, 34065);
            OpenGL.glEnable(3168);
            OpenGL.glEnable(3169);
            OpenGL.glEnable(3170);
            OpenGL.glMatrixMode(5890);
            OpenGL.glLoadIdentity();
            OpenGL.glRotatef(22.5f, 1.0f, 0.0f, 0.0f);
            OpenGL.glMatrixMode(5888);
            if (this.aBoolean5000) {
                super.aHa_Sub1_1215.method1899(7681, n ^ 0x2300, 260);
                super.aHa_Sub1_1215.method1840(0, 770, -121, 5890);
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 34167);
            }
            else {
                super.aHa_Sub1_1215.method1899(8448, n ^ 0x2300, 7681);
                super.aHa_Sub1_1215.method1840(0, 768, n ^ 0x54, 34168);
                super.aHa_Sub1_1215.method1845(2, 847872872);
                super.aHa_Sub1_1215.method1899(7681, n ^ 0x2300, 260);
                super.aHa_Sub1_1215.method1840(0, 768, 90, 34168);
                super.aHa_Sub1_1215.method1840(1, 770, 80, 34168);
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 34167);
            }
            super.aHa_Sub1_1215.method1845(0, 847872872);
            this.aClass91_4994.method886((byte)58);
            this.aClass91_4994.method887(1, -30389);
            super.aHa_Sub1_1215.method1845(1, n ^ 0x32898368);
            OpenGL.glDisable(3168);
            OpenGL.glDisable(3169);
            OpenGL.glDisable(3170);
            OpenGL.glMatrixMode(5890);
            OpenGL.glLoadIdentity();
            OpenGL.glMatrixMode(5888);
            if (!this.aBoolean5000) {
                super.aHa_Sub1_1215.method1899(8448, 8960, 8448);
                super.aHa_Sub1_1215.method1840(0, 768, 81, 5890);
                super.aHa_Sub1_1215.method1845(2, 847872872);
                super.aHa_Sub1_1215.method1899(8448, 8960, 8448);
                super.aHa_Sub1_1215.method1840(0, 768, 110, 5890);
                super.aHa_Sub1_1215.method1840(1, 768, -124, 34168);
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 5890);
            }
            else {
                super.aHa_Sub1_1215.method1899(8448, 8960, 8448);
                super.aHa_Sub1_1215.method1840(0, 768, -82, 5890);
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 5890);
            }
            super.aHa_Sub1_1215.method1845(n, 847872872);
            this.aClass91_4994.method886((byte)(-95));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.B(" + n + ')');
        }
    }
    
    public static void method2465(final byte b) {
        try {
            Class151_Sub6.aClass58_4997 = null;
            if (b < 81) {
                method2463(null, null, 55);
            }
            Class151_Sub6.aClass58_4999 = null;
            Class151_Sub6.aClass58_4998 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.I(" + b + ')');
        }
    }
    
    @Override
    final void method2443(final boolean b, final int n) {
        try {
            if (n != 255) {
                this.method2443(false, 106);
            }
            super.aHa_Sub1_1215.method1899(7681, 8960, 8448);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nf.C(" + b + ',' + n + ')');
        }
    }
    
    static {
        Class151_Sub6.aClass58_4997 = new IncomingOpcode(76, -1);
        Class151_Sub6.aClass58_4998 = new IncomingOpcode(12, 0);
        Class151_Sub6.aClass58_4999 = new IncomingOpcode(80, -2);
    }
}
