// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub3_Sub2 extends Class246_Sub3_Sub3 implements Interface19
{
    private byte aByte6279;
    private boolean aBoolean6280;
    private boolean aBoolean6281;
    private Class228 aClass228_6282;
    private short aShort6283;
    private boolean aBoolean6284;
    Class146 aClass146_6285;
    private r aR6286;
    private boolean aBoolean6287;
    private byte aByte6288;
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            if (this.aClass146_6285 == null) {
                return null;
            }
            final Class111 method1793 = ha.method1793();
            method1793.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            final Class246_Sub1 method1794 = Class94.method915(1, (byte)(-47), this.aBoolean6280);
            Label_0092: {
                if (!Class239.aBoolean1839) {
                    this.aClass146_6285.method2325(method1793, method1794.aClass246_Sub6Array5067[0], 0);
                    if (!client.aBoolean3553) {
                        break Label_0092;
                    }
                }
                this.aClass146_6285.method2329(method1793, method1794.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
            }
            if (n >= -12) {
                return null;
            }
            return method1794;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final int method64(final int n) {
        try {
            if (n != 30472) {
                this.aBoolean6284 = false;
            }
            return this.aShort6283 & 0xFFFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.C(" + n + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (this.aClass228_6282 == null) {
                this.aClass228_6282 = Class48_Sub2_Sub1.method472(super.anInt5089, super.anInt5084, this.method3019(0, (byte)58, ha), super.anInt5079, 4);
            }
            if (b != -53) {
                this.method3019(89, (byte)(-78), null);
            }
            return this.aClass228_6282;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final Class298 method3018(final ha ha, final int n, final int n2, final boolean b) {
        try {
            if (n >= -20) {
                return null;
            }
            final Class352 method3546 = Class130.aClass302_1028.method3546(0xFFFF & this.aShort6283, (byte)119);
            s s;
            s s2;
            if (!this.aBoolean6287) {
                if (~super.aByte5081 > -4) {
                    s = Class98_Sub46_Sub2_Sub2.aSArray6298[super.aByte5081 + 1];
                }
                else {
                    s = null;
                }
                s2 = Class98_Sub46_Sub2_Sub2.aSArray6298[super.aByte5081];
            }
            else {
                s2 = Class81.aSArray618[super.aByte5081];
                s = Class98_Sub46_Sub2_Sub2.aSArray6298[0];
            }
            return method3546.method3851(super.anInt5079, false, s, this.aByte6288, super.anInt5089, b, super.anInt5084, n2, null, s2, ha, this.aByte6279);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.T(" + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            final Class146 method3019 = this.method3019(131072, (byte)61, ha);
            if (method3019 == null) {
                if (b <= 59) {
                    method3020(43, -102);
                }
                return false;
            }
            final Class111 method3020 = ha.method1793();
            method3020.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            if (Class239.aBoolean1839) {
                return method3019.method2333(n, n2, method3020, false, 0, Class16.anInt197);
            }
            return method3019.method2339(n, n2, method3020, false, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return this.aClass146_6285 == null || !this.aClass146_6285.r();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.H(" + n + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                return 118;
            }
            if (this.aClass146_6285 != null) {
                return this.aClass146_6285.ma();
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.O(" + b + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            if (!(class246_Sub3 instanceof Class246_Sub3_Sub3_Sub2)) {
                if (class246_Sub3 instanceof Class246_Sub3_Sub4_Sub1) {
                    final Class246_Sub3_Sub4_Sub1 class246_Sub3_Sub4_Sub1 = (Class246_Sub3_Sub4_Sub1)class246_Sub3;
                    if (this.aClass146_6285 != null && class246_Sub3_Sub4_Sub1.aClass146_6243 != null) {
                        this.aClass146_6285.method2332(class246_Sub3_Sub4_Sub1.aClass146_6243, n, n2, n3, b2);
                    }
                }
            }
            else {
                final Class246_Sub3_Sub3_Sub2 class246_Sub3_Sub3_Sub2 = (Class246_Sub3_Sub3_Sub2)class246_Sub3;
                if (this.aClass146_6285 != null && class246_Sub3_Sub3_Sub2.aClass146_6285 != null) {
                    this.aClass146_6285.method2332(class246_Sub3_Sub3_Sub2.aClass146_6285, n, n2, n3, b2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    public final void method61(final byte b) {
        try {
            if (this.aClass146_6285 != null) {
                this.aClass146_6285.method2326();
            }
            if (b != -96) {
                this.method2992((byte)(-58));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.Q(" + b + ')');
        }
    }
    
    @Override
    public final boolean method65(final boolean b) {
        try {
            return b && this.aBoolean6281;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.A(" + b + ')');
        }
    }
    
    @Override
    public final int method63(final byte b) {
        try {
            if (b != 20) {
                this.method64(-101);
            }
            return this.aByte6279;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.B(" + b + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b > -70) {
                method3021(-106, 41, null);
            }
            return this.aBoolean6284;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.HA(" + b + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
    }
    
    @Override
    public final int method66(final int n) {
        try {
            if (n != 4657) {
                this.aBoolean6284 = true;
            }
            return this.aByte6288;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.N(" + n + ')');
        }
    }
    
    private final Class146 method3019(final int n, final byte b, final ha ha) {
        try {
            if (this.aClass146_6285 != null && ha.c(this.aClass146_6285.ua(), n) == 0) {
                return this.aClass146_6285;
            }
            if (b < 31) {
                this.aClass228_6282 = null;
            }
            final Class298 method3018 = this.method3018(ha, -56, n, false);
            if (method3018 == null) {
                return null;
            }
            return method3018.aClass146_2477;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.R(" + n + ',' + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class246_Sub3_Sub3_Sub2(final ha ha, final Class352 class352, final int n, final int n2, final int anInt5084, final int n3, final int anInt5085, final boolean aBoolean6287, final int n4, final int n5, final boolean aBoolean6288) {
        super(anInt5084, n3, anInt5085, n, n2, Class77.method780(n4, (byte)(-72), n5));
        try {
            this.aByte6288 = (byte)n5;
            this.aBoolean6284 = aBoolean6288;
            this.aByte6279 = (byte)n4;
            this.aShort6283 = (short)class352.id;
            this.aBoolean6280 = (class352.anInt2998 != 0 && !aBoolean6287);
            this.aBoolean6287 = aBoolean6287;
            super.anInt5084 = anInt5084;
            super.anInt5079 = anInt5085;
            this.aBoolean6281 = (ha.method1771() && class352.aBoolean2935 && !this.aBoolean6287 && Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method579((byte)124) != 0);
            int n6 = 2048;
            if (this.aBoolean6284) {
                n6 |= 0x10000;
            }
            final Class298 method3018 = this.method3018(ha, -99, n6, this.aBoolean6281);
            if (method3018 != null) {
                this.aClass146_6285 = method3018.aClass146_2477;
                this.aR6286 = method3018.aR2479;
                if (this.aBoolean6284) {
                    this.aClass146_6285 = this.aClass146_6285.method2341((byte)0, n6, false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((class352 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt5084 + ',' + n3 + ',' + anInt5085 + ',' + aBoolean6287 + ',' + n4 + ',' + n5 + ',' + aBoolean6288 + ')');
        }
    }
    
    @Override
    public final void method62(final ha ha, final int n) {
        try {
            if (n != 24447) {
                this.aShort6283 = -43;
            }
            r ar6286;
            if (this.aR6286 == null && this.aBoolean6281) {
                final Class298 method3018 = this.method3018(ha, -49, 262144, true);
                ar6286 = ((method3018 == null) ? null : method3018.aR2479);
            }
            else {
                ar6286 = this.aR6286;
                this.aR6286 = null;
            }
            if (ar6286 != null) {
                Class184.method2626(ar6286, super.aByte5081, super.anInt5084, super.anInt5079, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.G(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            this.aBoolean6284 = false;
            if (this.aClass146_6285 != null) {
                this.aClass146_6285.s(0xFFFEFFFF & this.aClass146_6285.ua());
            }
            if (b != -73) {
                this.method2975(null, -1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.DA(" + b + ')');
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                return 127;
            }
            if (this.aClass146_6285 == null) {
                return 0;
            }
            return this.aClass146_6285.fa();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.J(" + n + ')');
        }
    }
    
    static final Class6 method3020(final int n, final int n2) {
        try {
            if (n2 <= 101) {
                method3020(26, -94);
            }
            final Class6[] method520 = IncomingOpcode.method520((byte)(-63));
            for (int n3 = 0; method520.length > n3; ++n3) {
                final Class6 class6 = method520[n3];
                if (~class6.anInt87 == ~n) {
                    return class6;
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.S(" + n + ',' + n2 + ')');
        }
    }
    
    static final float[] method3021(final int n, final int n2, final float[] array) {
        try {
            final float[] array2 = new float[n2];
            Class236.method2897(array, 0, array2, 0, n2);
            if (n != -65537) {
                return null;
            }
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.P(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2987(final int n) {
        try {
            return n != 6540 || (this.aClass146_6285 != null && this.aClass146_6285.F());
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.I(" + n + ')');
        }
    }
    
    @Override
    public final void method67(final int n, final ha ha) {
        try {
            if (n == -25163) {
                r ar6286;
                if (this.aR6286 == null && this.aBoolean6281) {
                    final Class298 method3018 = this.method3018(ha, n + 25108, 262144, true);
                    ar6286 = ((method3018 != null) ? method3018.aR2479 : null);
                }
                else {
                    ar6286 = this.aR6286;
                    this.aR6286 = null;
                }
                if (ar6286 != null) {
                    Class268.method3254(ar6286, super.aByte5081, super.anInt5084, super.anInt5079, null);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vr.E(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
}
