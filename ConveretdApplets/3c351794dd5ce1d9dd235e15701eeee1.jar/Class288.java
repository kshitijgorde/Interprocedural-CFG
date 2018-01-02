import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class288 implements Interface12
{
    private ha_Sub1 aHa_Sub1_3374;
    static Class105 aClass105_3375;
    static int[] anIntArray3376;
    private int anInt3377;
    private Interface3[] anInterface3Array3378;
    private int anInt3379;
    private int anInt3380;
    static Class128 aClass128_3381;
    private int anInt3382;
    private int anInt3383;
    private int anInt3384;
    
    final void method3395(final int n, final Class42_Sub1 class42_Sub1, final int n2) {
        try {
            this.method3405(0, class42_Sub1, -128, n);
            if (n2 <= 106) {
                this.method36((byte)25);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.J(" + n + ',' + ((class42_Sub1 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    private final void method3396(final int n, final int n2, final Class42_Sub2 class42_Sub2, final int n3, final int n4) {
        try {
            if (~this.anInt3382 == n4) {
                throw new RuntimeException();
            }
            final int n5 = 1 << n2;
            if (~(~n5 & this.anInt3377) != -1) {
                if (~class42_Sub2.anInt5357 != ~this.anInt3379 || ~this.anInt3380 != ~class42_Sub2.anInt5357) {
                    throw new RuntimeException();
                }
            }
            else {
                this.anInt3380 = class42_Sub2.anInt5357;
                this.anInt3379 = class42_Sub2.anInt5357;
            }
            class42_Sub2.method391(n, Class358.anIntArray3034[n2], n3, this.anInt3382, (byte)(-99));
            this.anInterface3Array3378[n2] = class42_Sub2;
            this.anInt3377 |= n5;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.C(" + n + ',' + n2 + ',' + ((class42_Sub2 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    public final void method37(final byte b) {
        try {
            OpenGL.glBindFramebufferEXT(36008, this.anInt3383);
            if (b == 77) {
                this.anInt3384 |= 0x1;
                this.anInt3382 = this.method3399(3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.P(" + b + ')');
        }
    }
    
    @Override
    public final void method39(final byte b) {
        try {
            if (b <= 10) {
                this.method3396(87, 67, null, -88, -66);
            }
            OpenGL.glBindFramebufferEXT(36160, this.anInt3383);
            this.anInt3384 |= 0x4;
            this.anInt3382 = this.method3399(3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.A(" + b + ')');
        }
    }
    
    public static void method3397(final boolean b) {
        try {
            Class288.aClass105_3375 = null;
            Class288.aClass128_3381 = null;
            Class288.anIntArray3376 = null;
            if (!b) {
                method3397(false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.B(" + b + ')');
        }
    }
    
    final void method3398(final int n, final int n2) {
        try {
            if (n <= -20) {
                if (this.anInt3382 == -1) {
                    throw new RuntimeException();
                }
                OpenGL.glReadBuffer(Class358.anIntArray3034[n2]);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.N(" + n + ',' + n2 + ')');
        }
    }
    
    private final int method3399(final int n) {
        try {
            if ((0x4 & this.anInt3384) != 0x0) {
                return 36160;
            }
            if (~(0x2 & this.anInt3384) != -1) {
                return 36009;
            }
            if (~(this.anInt3384 & 0x1) != -1) {
                return 36008;
            }
            if (n != 3) {
                Class288.aClass128_3381 = null;
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.O(" + n + ')');
        }
    }
    
    static final boolean method3400(int n, final int n2, int n3, int n4, int n5, final byte b, int anInt3646, final int[] array) {
        try {
            if (anInt3646 > Class64_Sub3.anInt3646) {
                anInt3646 = Class64_Sub3.anInt3646;
            }
            if (n < 0) {
                n = 0;
            }
            if (~anInt3646 >= ~n) {
                return true;
            }
            if (b >= -18) {
                Class288.aClass128_3381 = null;
            }
            n5 += n * n2;
            n4 = anInt3646 - n >> -595607166;
            n3 += n - 1;
            if (~Class287.anInt2190 == 0xFFFFFFFE) {
                Class4.anInt81 += n4;
                while (--n4 >= 0) {
                    if (n5 < array[++n3]) {
                        array[n3] = n5;
                    }
                    n5 += n2;
                    if (~array[++n3] < ~n5) {
                        array[n3] = n5;
                    }
                    n5 += n2;
                    if (~n5 > ~array[++n3]) {
                        array[n3] = n5;
                    }
                    n5 += n2;
                    if (~n5 > ~array[++n3]) {
                        array[n3] = n5;
                    }
                    n5 += n2;
                }
                n4 = (anInt3646 - n & 0x3);
                while (~(--n4) <= -1) {
                    if (~n5 > ~array[++n3]) {
                        array[n3] = n5;
                    }
                    n5 += n2;
                }
            }
            else {
                n5 -= 38400;
                while (~(--n4) <= -1) {
                    if (~array[++n3] < ~n5) {
                        return false;
                    }
                    n5 += n2;
                    if (array[++n3] > n5) {
                        return false;
                    }
                    n5 += n2;
                    if (array[++n3] > n5) {
                        return false;
                    }
                    n5 += n2;
                    if (array[++n3] > n5) {
                        return false;
                    }
                    n5 += n2;
                }
                n4 = (0x3 & -n + anInt3646);
                while (--n4 >= 0) {
                    if (array[++n3] > n5) {
                        return false;
                    }
                    n5 += n2;
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.F(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ',' + anInt3646 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void method36(final byte b) {
        try {
            OpenGL.glBindFramebufferEXT(36009, this.anInt3383);
            if (b > -113) {
                this.method3399(-82);
            }
            this.anInt3384 |= 0x2;
            this.anInt3382 = this.method3399(3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.H(" + b + ')');
        }
    }
    
    @Override
    public final void method40(final byte b) {
        try {
            OpenGL.glBindFramebufferEXT(36009, 0);
            this.anInt3384 &= 0xFFFFFFFD;
            if (b != -30) {
                method3400(-109, -55, 70, 87, -56, (byte)83, 20, null);
            }
            this.anInt3382 = this.method3399(3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.G(" + b + ')');
        }
    }
    
    final void method3401(final int n, final boolean b) {
        try {
            if (!b) {
                this.method40((byte)2);
            }
            if (this.anInterface3Array3378[n] != null) {
                this.anInterface3Array3378[n].method3((byte)(-120));
            }
            this.anInt3377 &= ~(1 << n);
            this.anInterface3Array3378[n] = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.E(" + n + ',' + b + ')');
        }
    }
    
    final void method3402(final int n, final int n2, final int n3, final Class42_Sub2 class42_Sub2) {
        try {
            this.method3396(n2, n3, class42_Sub2, n, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.Q(" + n + ',' + n2 + ',' + n3 + ',' + ((class42_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void method38(final int n) {
        try {
            if (n != -27095) {
                this.anInt3384 = -9;
            }
            OpenGL.glBindFramebufferEXT(36008, 0);
            this.anInt3384 &= 0xFFFFFFFE;
            this.anInt3382 = this.method3399(n ^ 0xFFFF962A);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.I(" + n + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.aHa_Sub1_3374.method1837((byte)(-103), this.anInt3383);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.finalize()");
        }
    }
    
    final boolean method3403(final byte b) {
        try {
            if (OpenGL.glCheckFramebufferStatusEXT(this.anInt3382) != 36053) {
                return false;
            }
            if (b != 74) {
                this.method3401(-5, true);
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.D(" + b + ')');
        }
    }
    
    final void method3404(final int n, final int n2) {
        try {
            if (~this.anInt3382 == n) {
                throw new RuntimeException();
            }
            OpenGL.glDrawBuffer(Class358.anIntArray3034[n2]);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.K(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method3405(final int n, final Class42_Sub1 class42_Sub1, final int n2, final int n3) {
        try {
            if (this.anInt3382 == -1) {
                throw new RuntimeException();
            }
            final int n4 = 1 << n3;
            if ((this.anInt3377 & ~n4) != 0x0) {
                if (~this.anInt3379 != ~class42_Sub1.anInt5355 || ~this.anInt3380 != ~class42_Sub1.anInt5352) {
                    throw new RuntimeException();
                }
            }
            else {
                this.anInt3379 = class42_Sub1.anInt5355;
                this.anInt3380 = class42_Sub1.anInt5352;
            }
            if (n2 >= -89) {
                this.anInterface3Array3378 = null;
            }
            class42_Sub1.method382(this.anInt3382, true, Class358.anIntArray3034[n3], n);
            this.anInterface3Array3378[n3] = class42_Sub1;
            this.anInt3377 |= n4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.L(" + n + ',' + ((class42_Sub1 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method3406(final byte b, final int n, final Class98_Sub46_Sub14 class98_Sub46_Sub14) {
        try {
            if (this.anInt3382 == -1) {
                throw new RuntimeException();
            }
            if (b < 56) {
                this.anInt3382 = -95;
            }
            final int n2 = 1 << n;
            Label_0108: {
                if ((~n2 & this.anInt3377) == 0x0) {
                    this.anInt3380 = class98_Sub46_Sub14.anInt5377;
                    this.anInt3379 = class98_Sub46_Sub14.anInt5376;
                    if (!client.aBoolean3553) {
                        break Label_0108;
                    }
                }
                if (~class98_Sub46_Sub14.anInt5376 != ~this.anInt3379 || ~this.anInt3380 != ~class98_Sub46_Sub14.anInt5377) {
                    throw new RuntimeException();
                }
            }
            class98_Sub46_Sub14.method1605(0, this.anInt3382, Class358.anIntArray3034[n]);
            this.anInterface3Array3378[n] = class98_Sub46_Sub14;
            this.anInt3377 |= n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.R(" + b + ',' + n + ',' + ((class98_Sub46_Sub14 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void method35(final byte b) {
        try {
            OpenGL.glBindFramebufferEXT(36160, 0);
            if (b != 69) {
                this.anInt3383 = 94;
            }
            this.anInt3384 &= 0xFFFFFFFB;
            this.anInt3382 = this.method3399(b - 66);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.M(" + b + ')');
        }
    }
    
    Class288(final ha_Sub1 aHa_Sub1_3374) {
        this.anInterface3Array3378 = new Interface3[9];
        this.anInt3382 = -1;
        this.anInt3384 = 0;
        try {
            if (!aHa_Sub1_3374.aBoolean4460) {
                throw new IllegalStateException("");
            }
            this.aHa_Sub1_3374 = aHa_Sub1_3374;
            OpenGL.glGenFramebuffersEXT(1, Class195.anIntArray1497, 0);
            this.anInt3383 = Class195.anIntArray1497[0];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rr.<init>(" + ((aHa_Sub1_3374 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class288.aClass105_3375 = new Class105("", 18);
        Class288.aClass128_3381 = new Class128();
    }
}
