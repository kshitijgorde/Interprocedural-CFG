// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub5_Sub1 extends Class246_Sub3_Sub5 implements Interface19
{
    private Class228 aClass228_6217;
    private boolean aBoolean6218;
    Class359 aClass359_6219;
    private boolean aBoolean6220;
    static OutgoingOpcode aClass171_6221;
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            final Class146 method3897 = this.aClass359_6219.method3897(-1, false, 2048, ha, true);
            if (method3897 == null) {
                return null;
            }
            final Class111 method3898 = ha.method1793();
            if (n >= -12) {
                this.aBoolean6220 = true;
            }
            method3898.method2100(super.aShort6165 + super.anInt5084, super.anInt5089, super.anInt5079 + super.aShort6163);
            final Class246_Sub1 method3899 = Class94.method915(1, (byte)(-47), this.aBoolean6220);
            final int n2 = super.anInt5084 >> -644618167;
            final int n3 = super.anInt5079 >> -811550487;
            this.aClass359_6219.method3895(method3897, n2, n3, method3898, true, n2, ha, false, n3);
            if (Class239.aBoolean1839) {
                method3897.method2329(method3898, method3899.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
            }
            else {
                method3897.method2325(method3898, method3899.aClass246_Sub6Array5067[0], 0);
            }
            if (this.aClass359_6219.aClass246_Sub5_3062 != null) {
                final Class242 method3900 = this.aClass359_6219.aClass246_Sub5_3062.method3116();
                if (Class239.aBoolean1839) {
                    ha.method1785(method3900, Class16.anInt197);
                }
                else {
                    ha.method1820(method3900);
                }
            }
            this.aBoolean6218 = (method3897.F() || this.aClass359_6219.aClass246_Sub5_3062 != null);
            if (this.aClass228_6217 != null) {
                Class283.method3350(super.anInt5089, super.anInt5084, 18, super.anInt5079, method3897, this.aClass228_6217);
            }
            else {
                this.aClass228_6217 = Class48_Sub2_Sub1.method472(super.anInt5089, super.anInt5084, method3897, super.anInt5079, 4);
            }
            return method3899;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final void method67(final int n, final ha ha) {
        try {
            this.aClass359_6219.method3894((byte)125, ha);
            if (n != -25163) {
                this.method2974((byte)25, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.E(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final int method64(final int n) {
        try {
            if (n != 30472) {
                this.method2978(119);
            }
            return this.aClass359_6219.anInt3052;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.C(" + n + ')');
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                this.method66(-34);
            }
            return this.aClass359_6219.method3899((byte)126);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.J(" + n + ')');
        }
    }
    
    @Override
    final boolean method2987(final int n) {
        try {
            if (n != 6540) {
                this.aBoolean6218 = false;
            }
            return this.aBoolean6218;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.I(" + n + ')');
        }
    }
    
    @Override
    public final int method63(final byte b) {
        try {
            if (b != 20) {
                this.method2978(-38);
            }
            return this.aClass359_6219.anInt3038;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.B(" + b + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                this.method2978(-53);
            }
            return this.aClass359_6219.method3903((byte)(-127));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.O(" + b + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
        try {
            final Class146 method3897 = this.aClass359_6219.method3897(-1, false, 262144, ha, true);
            if (method3897 != null) {
                final int n2 = super.anInt5084 >> -2058956119;
                final int n3 = super.anInt5079 >> -1110738775;
                final Class111 method3898 = ha.method1793();
                method3898.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
                this.aClass359_6219.method3895(method3897, n2, n3, method3898, false, n2, ha, false, n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.MA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final void method61(final byte b) {
        try {
            if (b != -96) {
                this.method2988(null, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.Q(" + b + ')');
        }
    }
    
    public static void method3090(final byte b) {
        try {
            Class246_Sub3_Sub5_Sub1.aClass171_6221 = null;
            if (b != -94) {
                method3090((byte)106);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.D(" + b + ')');
        }
    }
    
    @Override
    public final boolean method65(final boolean b) {
        try {
            return b && this.aClass359_6219.method3898(99);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.A(" + b + ')');
        }
    }
    
    @Override
    public final void method62(final ha ha, final int n) {
        try {
            if (n != 24447) {
                this.method64(106);
            }
            this.aClass359_6219.method3892(ha, 103);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.G(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method3091(final Class185 class185, final boolean b) {
        try {
            this.aClass359_6219.method3901(class185, -108);
            if (b) {
                this.aBoolean6218 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.K(" + ((class185 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            final Class146 method3897 = this.aClass359_6219.method3897(-1, false, 131072, ha, false);
            if (method3897 == null) {
                return false;
            }
            final Class111 method3898 = ha.method1793();
            method3898.method2100(super.anInt5084 + super.aShort6165, super.anInt5089, super.anInt5079 + super.aShort6163);
            if (b < 59) {
                this.method64(111);
            }
            if (!Class239.aBoolean1839) {
                return method3897.method2339(n, n2, method3898, false, 0);
            }
            return method3897.method2333(n, n2, method3898, false, 0, Class16.anInt197);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    Class246_Sub3_Sub5_Sub1(final ha ha, final Class352 class352, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final int n6, final int n7, final int n8, final int n9, final int n10) {
        super(n3, n4, n5, n, n2, n6, n7);
        this.aBoolean6218 = false;
        try {
            this.aClass359_6219 = new Class359(ha, class352, n8, n9, super.aByte5088, n2, this, b, n10);
            this.aBoolean6220 = (class352.anInt2998 != 0 && !b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((class352 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.H(" + n + ')');
        }
    }
    
    static final void method3092(final int n, final boolean b) {
        try {
            if (n != -1) {
                method3090((byte)63);
            }
            Class98_Sub10_Sub29.sendPacket(false, Class246_Sub3_Sub4.method3023(260, Class15.aClass171_183, Class331.aClass117_2811));
            for (Class98_Sub18 class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3998(94); class98_Sub18 != null; class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3995(n)) {
                if (!class98_Sub18.method941((byte)78)) {
                    class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3998(95);
                    if (class98_Sub18 == null) {
                        break;
                    }
                }
                if (~class98_Sub18.anInt3947 == -1) {
                    Class196.method2666(16398, b, class98_Sub18, true);
                }
            }
            if (OutputStream_Sub1.aClass293_33 != null) {
                Class341.method3812(1, OutputStream_Sub1.aClass293_33);
                OutputStream_Sub1.aClass293_33 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.F(" + n + ',' + b + ')');
        }
    }
    
    @Override
    public final int method66(final int n) {
        try {
            if (n != 4657) {
                Class246_Sub3_Sub5_Sub1.aClass171_6221 = null;
            }
            return this.aClass359_6219.anInt3059;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.N(" + n + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (b != -53) {
                return null;
            }
            return this.aClass228_6217;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ce.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub5_Sub1.aClass171_6221 = new OutgoingOpcode(6, 8);
    }
}
