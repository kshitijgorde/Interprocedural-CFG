import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub1_Sub1 extends Class246_Sub3_Sub1 implements Interface19
{
    private Class228 aClass228_6222;
    static int[] anIntArray6223;
    private boolean aBoolean6224;
    private short aShort6225;
    private byte aByte6226;
    private r aR6227;
    private boolean aBoolean6228;
    private boolean aBoolean6229;
    private Class146 aClass146_6230;
    private boolean aBoolean6231;
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b >= -70) {
                this.aBoolean6231 = false;
            }
            return this.aBoolean6224;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.HA(" + b + ')');
        }
    }
    
    @Override
    public final void method67(final int n, final ha ha) {
        try {
            if (n == -25163) {
                r ar6227;
                if (this.aR6227 == null && this.aBoolean6231) {
                    final Class298 method2998 = this.method2998(true, ha, true, 262144);
                    ar6227 = ((method2998 != null) ? method2998.aR2479 : null);
                }
                else {
                    ar6227 = this.aR6227;
                    this.aR6227 = null;
                }
                if (ar6227 != null) {
                    Class268.method3254(ar6227, super.aByte5081, super.anInt5084, super.anInt5079, null);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.E(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final int method64(final int n) {
        try {
            if (n != 30472) {
                return 37;
            }
            return 0xFFFF & this.aShort6225;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.C(" + n + ')');
        }
    }
    
    static final Class345 method2996(final int n, final ha_Sub1 ha_Sub1, final boolean b, final String s) {
        try {
            if (b) {
                return null;
            }
            final long glCreateShaderObjectARB = OpenGL.glCreateShaderObjectARB(n);
            OpenGL.glShaderSourceARB(glCreateShaderObjectARB, s);
            OpenGL.glCompileShaderARB(glCreateShaderObjectARB);
            OpenGL.glGetObjectParameterivARB(glCreateShaderObjectARB, 35713, ha.anIntArray942, 0);
            if (ha.anIntArray942[0] == 0) {
                if (ha.anIntArray942[0] == 0) {
                    System.out.println("Shader compile failed:");
                }
                OpenGL.glGetObjectParameterivARB(glCreateShaderObjectARB, 35716, ha.anIntArray942, 1);
                if (~ha.anIntArray942[1] < -2) {
                    final byte[] array = new byte[ha.anIntArray942[1]];
                    OpenGL.glGetInfoLogARB(glCreateShaderObjectARB, ha.anIntArray942[1], ha.anIntArray942, 0, array, 0);
                    System.out.println(new String(array));
                }
                if (~ha.anIntArray942[0] == -1) {
                    OpenGL.glDeleteObjectARB(glCreateShaderObjectARB);
                    return null;
                }
            }
            return new Class345(ha_Sub1, glCreateShaderObjectARB, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.L(" + n + ',' + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final int method66(final int n) {
        try {
            if (n != 4657) {
                return -4;
            }
            return this.aByte6226;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.N(" + n + ')');
        }
    }
    
    static final Class196 method2997(final int n, final int n2) {
        try {
            final Class196[] method180 = Class6.method180(94);
            for (int n3 = 0; ~method180.length < ~n3; ++n3) {
                final Class196 class196 = method180[n3];
                if (~class196.anInt1510 == ~n2) {
                    return class196;
                }
            }
            if (n < 101) {
                Class246_Sub3_Sub1_Sub1.anIntArray6223 = null;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.M(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final boolean method2987(final int n) {
        try {
            if (n != 6540) {
                this.method3000(23, -77, null);
            }
            return this.aClass146_6230 != null && this.aClass146_6230.F();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.I(" + n + ')');
        }
    }
    
    @Override
    public final int method63(final byte b) {
        try {
            if (b != 20) {
                this.method63((byte)84);
            }
            return 22;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.B(" + b + ')');
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                return 87;
            }
            if (this.aClass146_6230 != null) {
                return this.aClass146_6230.fa();
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.J(" + n + ')');
        }
    }
    
    private final Class298 method2998(final boolean b, final ha ha, final boolean b2, final int n) {
        try {
            if (!b) {
                return null;
            }
            final Class352 method3546 = Class130.aClass302_1028.method3546(0xFFFF & this.aShort6225, (byte)119);
            s s;
            s s2;
            if (!this.aBoolean6228) {
                s = Class98_Sub46_Sub2_Sub2.aSArray6298[super.aByte5081];
                if (super.aByte5081 < 3) {
                    s2 = Class98_Sub46_Sub2_Sub2.aSArray6298[super.aByte5081 + 1];
                }
                else {
                    s2 = null;
                }
            }
            else {
                s = Class81.aSArray618[super.aByte5081];
                s2 = Class98_Sub46_Sub2_Sub2.aSArray6298[0];
            }
            return method3546.method3851(super.anInt5079, false, s2, this.aByte6226, super.anInt5089, b2, super.anInt5084, n, null, s, ha, 22);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.P(" + b + ',' + ((ha != null) ? "{...}" : "null") + ',' + b2 + ',' + n + ')');
        }
    }
    
    Class246_Sub3_Sub1_Sub1(final ha ha, final Class352 class352, final int n, final int n2, final int anInt5084, final int n3, final int anInt5085, final boolean aBoolean6228, final int n4, final boolean aBoolean6229) {
        super(anInt5084, n3, anInt5085, n, n2, class352.anInt2945);
        try {
            this.aBoolean6224 = aBoolean6229;
            this.aShort6225 = (short)class352.id;
            super.anInt5084 = anInt5084;
            this.aBoolean6228 = aBoolean6228;
            this.aBoolean6229 = (~class352.anInt2998 != -1 && !aBoolean6228);
            super.anInt5079 = anInt5085;
            this.aByte6226 = (byte)n4;
            this.aBoolean6231 = (ha.method1771() && class352.aBoolean2935 && !this.aBoolean6228 && Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method579((byte)123) != 0);
            int n5 = 2048;
            if (this.aBoolean6224) {
                n5 |= 0x10000;
            }
            final Class298 method2998 = this.method2998(true, ha, this.aBoolean6231, n5);
            if (method2998 != null) {
                this.aClass146_6230 = method2998.aClass146_2477;
                this.aR6227 = method2998.aR2479;
                if (this.aBoolean6224) {
                    this.aClass146_6230 = this.aClass146_6230.method2341((byte)0, n5, false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((class352 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt5084 + ',' + n3 + ',' + anInt5085 + ',' + aBoolean6228 + ',' + n4 + ',' + aBoolean6229 + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return this.aClass146_6230 == null || !this.aClass146_6230.r();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.H(" + n + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            if (b <= 59) {
                return false;
            }
            final Class146 method3000 = this.method3000(-69, 131072, ha);
            if (method3000 == null) {
                return false;
            }
            final Class111 method3001 = ha.method1793();
            method3001.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            if (!Class239.aBoolean1839) {
                return method3000.method2339(n, n2, method3001, false, 0);
            }
            return method3000.method2333(n, n2, method3001, false, 0, Class16.anInt197);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
    }
    
    public static void method2999(final int n) {
        try {
            if (n == 25581) {
                Class246_Sub3_Sub1_Sub1.anIntArray6223 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.R(" + n + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                return 68;
            }
            if (this.aClass146_6230 == null) {
                return 0;
            }
            return this.aClass146_6230.ma();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.O(" + b + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (this.aClass228_6222 == null) {
                this.aClass228_6222 = Class48_Sub2_Sub1.method472(super.anInt5089, super.anInt5084, this.method3000(121, 0, ha), super.anInt5079, 4);
            }
            if (b != -53) {
                Class246_Sub3_Sub1_Sub1.anIntArray6223 = null;
            }
            return this.aClass228_6222;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            if (b != -73) {
                this.aShort6225 = 12;
            }
            this.aBoolean6224 = false;
            if (this.aClass146_6230 != null) {
                this.aClass146_6230.s(0xFFFEFFFF & this.aClass146_6230.ua());
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.DA(" + b + ')');
        }
    }
    
    private final Class146 method3000(final int n, final int n2, final ha ha) {
        try {
            if (this.aClass146_6230 != null && ~ha.c(this.aClass146_6230.ua(), n2) == -1) {
                return this.aClass146_6230;
            }
            final Class298 method2998 = this.method2998(true, ha, false, n2);
            if (method2998 == null) {
                return null;
            }
            return method2998.aClass146_2477;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.S(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void method62(final ha ha, final int n) {
        try {
            if (n != 24447) {
                method2996(95, null, true, null);
            }
            r ar6227;
            if (this.aR6227 == null && this.aBoolean6231) {
                final Class298 method2998 = this.method2998(true, ha, true, 262144);
                ar6227 = ((method2998 != null) ? method2998.aR2479 : null);
            }
            else {
                ar6227 = this.aR6227;
                this.aR6227 = null;
            }
            if (ar6227 != null) {
                Class184.method2626(ar6227, super.aByte5081, super.anInt5084, super.anInt5079, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.G(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final boolean method65(final boolean b) {
        try {
            if (!b) {
                this.aClass228_6222 = null;
            }
            return this.aBoolean6231;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.A(" + b + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            if (class246_Sub3 instanceof Class246_Sub3_Sub1_Sub1) {
                final Class246_Sub3_Sub1_Sub1 class246_Sub3_Sub1_Sub1 = (Class246_Sub3_Sub1_Sub1)class246_Sub3;
                if (this.aClass146_6230 != null && class246_Sub3_Sub1_Sub1.aClass146_6230 != null) {
                    this.aClass146_6230.method2332(class246_Sub3_Sub1_Sub1.aClass146_6230, n, n2, n3, b2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            if (this.aClass146_6230 == null) {
                return null;
            }
            if (n >= -12) {
                this.aShort6225 = 45;
            }
            final Class111 method1793 = ha.method1793();
            method1793.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            final Class246_Sub1 method1794 = Class94.method915(1, (byte)(-47), this.aBoolean6229);
            if (Class239.aBoolean1839) {
                this.aClass146_6230.method2329(method1793, method1794.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
                if (!client.aBoolean3553) {
                    return method1794;
                }
            }
            this.aClass146_6230.method2325(method1793, method1794.aClass246_Sub6Array5067[0], 0);
            return method1794;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final void method61(final byte b) {
        try {
            if (b != -96) {
                this.method2998(true, null, false, 21);
            }
            if (this.aClass146_6230 != null) {
                this.aClass146_6230.method2326();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lo.Q(" + b + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub1_Sub1.anIntArray6223 = new int[6];
    }
}
