import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class42 implements Interface3
{
    private int anInt3224;
    private boolean aBoolean3225;
    int anInt3226;
    ha_Sub1 aHa_Sub1_3227;
    private boolean aBoolean3228;
    int anInt3229;
    int anInt3230;
    static short aShort3231;
    
    private final void method368(final int n) {
        try {
            this.aHa_Sub1_3227.method1863(1, this);
            if (this.aBoolean3228) {
                OpenGL.glTexParameteri(this.anInt3226, 10241, this.aBoolean3225 ? 9987 : 9729);
                OpenGL.glTexParameteri(this.anInt3226, 10240, 9729);
                if (!client.aBoolean3553) {
                    return;
                }
            }
            OpenGL.glTexParameteri(this.anInt3226, 10241, this.aBoolean3225 ? 9984 : 9728);
            OpenGL.glTexParameteri(this.anInt3226, 10240, 9728);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.Q(" + n + ')');
        }
    }
    
    final int method369(final boolean b) {
        try {
            if (!b) {
                this.method377(57, 44);
            }
            return this.anInt3229;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.T(" + b + ')');
        }
    }
    
    private final int method370(final int n) {
        try {
            final int n2 = this.aHa_Sub1_3227.method1866(n + 6368, this.anInt3230) * this.anInt3224;
            if (n != -6462) {
                method376((byte)56, null);
            }
            if (!this.aBoolean3225) {
                return n2;
            }
            return n2 * 4 / 3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.P(" + n + ')');
        }
    }
    
    final boolean method371(final int n) {
        try {
            if (n <= 31) {
                this.method370(108);
            }
            if (this.aHa_Sub1_3227.aBoolean4460) {
                final int method370 = this.method370(-6462);
                this.aHa_Sub1_3227.method1863(1, this);
                OpenGL.glGenerateMipmapEXT(this.anInt3226);
                this.aBoolean3225 = true;
                this.method368(-114);
                this.method377(113, method370);
                return true;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.S(" + n + ')');
        }
    }
    
    final void method372(final int n, final boolean aBoolean3228) {
        try {
            if (!aBoolean3228 == this.aBoolean3228) {
                this.aBoolean3228 = aBoolean3228;
                this.method368(102);
            }
            if (n != -28003) {
                Class42.aShort3231 = 21;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.U(" + n + ',' + aBoolean3228 + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.method375(true);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.finalize()");
        }
    }
    
    final void method373(final boolean b, final boolean b2) {
        try {
            if (!this.aBoolean3225 == b2) {
                final int method370 = this.method370(-6462);
                this.aBoolean3225 = true;
                this.method368(-38);
                this.method377(100, method370);
            }
            if (!b) {
                this.anInt3226 = 51;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.N(" + b + ',' + b2 + ')');
        }
    }
    
    static final void method374(final Class305_Sub1 class305_Sub1, final int n, final byte[][] array) {
        try {
            final int[] array2 = { -1, 0, 0, 0, 0 };
            final int i = array.length;
            for (int n2 = n; i > n2; ++n2) {
                final byte[] array3 = array[n2];
                if (array3 != null) {
                    final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array3);
                    final int n3 = Class121.anIntArray1006[n2] >> -908871672;
                    final int n4 = 0xFF & Class121.anIntArray1006[n2];
                    final int n5 = 64 * n3 - Class272.anInt2038;
                    final int n6 = 64 * n4 + -aa_Sub2.anInt3562;
                    Class128.method2224(n ^ 0x58A8);
                    class305_Sub1.method3574((byte)117, n6, aa_Sub2.anInt3562, Class272.anInt2038, class98_Sub22, n5, Class167.aClass243Array1281);
                    class305_Sub1.method3582(n + 17685, n6, n5, Class265.aHa1974, class98_Sub22, array2);
                    if (!class305_Sub1.aBoolean2544 && ~n3 == ~(Class160.anInt1258 / 8) && n4 == Class275.anInt2047 / 8 && array2[0] != -1) {
                        s_Sub1.aClass346_5202 = Class373_Sub2.aClass59_5470.method528(0, array2[0], array2[2], array2[1], Class101.aClass115_857, array2[3]);
                        Class156_Sub1.anInt3278 = array2[4];
                    }
                }
            }
            for (int n7 = 0; ~n7 > ~i; ++n7) {
                final int n8 = (Class121.anIntArray1006[n7] >> -1224911608) * 64 - Class272.anInt2038;
                final int n9 = -aa_Sub2.anInt3562 + (Class121.anIntArray1006[n7] & 0xFF) * 64;
                if (array[n7] == null && Class275.anInt2047 < 800) {
                    Class128.method2224(22696);
                    class305_Sub1.method3569(64, true, n9, 64, n8);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.O(" + ((class305_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method375(final boolean b) {
        try {
            if (b && this.anInt3229 > 0) {
                this.aHa_Sub1_3227.method1873(this.method370(-6462), 4, this.anInt3229);
                this.anInt3229 = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.M(" + b + ')');
        }
    }
    
    Class42(final ha_Sub1 aHa_Sub1_3227, final int anInt3226, final int anInt3227, final int anInt3228, final boolean aBoolean3225) {
        this.aBoolean3228 = false;
        try {
            this.aHa_Sub1_3227 = aHa_Sub1_3227;
            this.anInt3226 = anInt3226;
            this.aBoolean3225 = aBoolean3225;
            this.anInt3230 = anInt3227;
            this.anInt3224 = anInt3228;
            OpenGL.glGenTextures(1, Class165.anIntArray1277, 0);
            this.anInt3229 = Class165.anIntArray1277[0];
            this.method377(122, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.<init>(" + ((aHa_Sub1_3227 != null) ? "{...}" : "null") + ',' + anInt3226 + ',' + anInt3227 + ',' + anInt3228 + ',' + aBoolean3225 + ')');
        }
    }
    
    static final Class300 method376(final byte b, final Class98_Sub22 class98_Sub22) {
        try {
            if (b != -12) {
                Class42.aShort3231 = -15;
            }
            final Class300 class300 = new Class300();
            class300.anInt2496 = class98_Sub22.readShort((byte)127);
            class300.aClass98_Sub46_Sub11_2498 = Class52.aClass280_3500.method3325(class300.anInt2496, 96);
            return class300;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.L(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method377(final int n, final int n2) {
        try {
            final ha_Sub1 aHa_Sub1_3227 = this.aHa_Sub1_3227;
            aHa_Sub1_3227.anInt4337 -= n2;
            if (n <= 88) {
                this.anInt3226 = 38;
            }
            final ha_Sub1 aHa_Sub1_3228 = this.aHa_Sub1_3227;
            aHa_Sub1_3228.anInt4337 += this.method370(-6462);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cu.R(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public abstract void method3(final byte p0);
    
    static {
        Class42.aShort3231 = 1;
    }
}
