// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub1_Sub2 extends Class246_Sub3_Sub1 implements Interface19
{
    static int[] anIntArray6245;
    private boolean aBoolean6246;
    static Class204 aClass204_6247;
    private boolean aBoolean6248;
    Class359 aClass359_6249;
    private Class228 aClass228_6250;
    static int anInt6251;
    static int anInt6252;
    
    @Override
    public final void method67(final int n, final ha ha) {
        try {
            this.aClass359_6249.method3894((byte)(-85), ha);
            if (n != -25163) {
                this.method2981(null, (byte)(-78), false, 22, null, 107, 28);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.E(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void method62(final ha ha, final int n) {
        try {
            if (n != 24447) {
                Class246_Sub3_Sub1_Sub2.anIntArray6245 = null;
            }
            this.aClass359_6249.method3892(ha, 105);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.G(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                Class246_Sub3_Sub1_Sub2.anInt6251 = -108;
            }
            return this.aClass359_6249.method3899((byte)125);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.J(" + n + ')');
        }
    }
    
    @Override
    public final int method64(final int n) {
        try {
            if (n != 30472) {
                method3001(55);
            }
            return this.aClass359_6249.anInt3052;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.C(" + n + ')');
        }
    }
    
    @Override
    public final void method61(final byte b) {
        try {
            if (b != -96) {
                this.aClass228_6250 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.Q(" + b + ')');
        }
    }
    
    @Override
    public final boolean method65(final boolean b) {
        try {
            return !b || this.aClass359_6249.method3898(21);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.A(" + b + ')');
        }
    }
    
    @Override
    final boolean method2987(final int n) {
        try {
            if (n != 6540) {
                this.method3002(null, (byte)(-110));
            }
            return this.aBoolean6248;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.I(" + n + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (b != -53) {
                Class246_Sub3_Sub1_Sub2.aClass204_6247 = null;
            }
            return this.aClass228_6250;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            if (b != -73) {
                Class246_Sub3_Sub1_Sub2.anInt6251 = -80;
            }
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.DA(" + b + ')');
        }
    }
    
    public static void method3001(final int n) {
        try {
            if (n != -22408) {
                method3001(77);
            }
            Class246_Sub3_Sub1_Sub2.aClass204_6247 = null;
            Class246_Sub3_Sub1_Sub2.anIntArray6245 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.L(" + n + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            final Class146 method3897 = this.aClass359_6249.method3897(-1, false, 131072, ha, false);
            if (method3897 == null) {
                return false;
            }
            if (b < 59) {
                this.method2990(-80);
            }
            final Class111 method3898 = ha.method1793();
            method3898.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            if (Class239.aBoolean1839) {
                return method3897.method2333(n, n2, method3898, false, 0, Class16.anInt197);
            }
            return method3897.method2339(n, n2, method3898, false, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    final void method3002(final Class185 class185, final byte b) {
        try {
            this.aClass359_6249.method3901(class185, -118);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.M(" + ((class185 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b >= -70) {
                Class246_Sub3_Sub1_Sub2.anIntArray6245 = null;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.HA(" + b + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
        try {
            final Class146 method3897 = this.aClass359_6249.method3897(-1, true, 262144, ha, true);
            if (method3897 != null) {
                final int n2 = super.anInt5084 >> 118087081;
                final int n3 = super.anInt5079 >> -1339126199;
                final Class111 method3898 = ha.method1793();
                method3898.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
                this.aClass359_6249.method3895(method3897, n2, n3, method3898, false, n2, ha, false, n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.MA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                this.method66(82);
            }
            return this.aClass359_6249.method3903((byte)(-123));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.O(" + b + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.H(" + n + ')');
        }
    }
    
    @Override
    public final int method66(final int n) {
        try {
            if (n != 4657) {
                this.method2976(108, null, (byte)101, -1);
            }
            return this.aClass359_6249.anInt3059;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.N(" + n + ')');
        }
    }
    
    Class246_Sub3_Sub1_Sub2(final ha ha, final Class352 class352, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final int n6, final int n7) {
        super(n3, n4, n5, n, n2, class352.anInt2945);
        this.aBoolean6248 = false;
        try {
            this.aClass359_6249 = new Class359(ha, class352, 22, n6, n, n2, this, b, n7);
            this.aBoolean6246 = (class352.anInt2998 != 0 && !b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((class352 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            final Class146 method3897 = this.aClass359_6249.method3897(-1, false, 2048, ha, true);
            if (method3897 == null) {
                return null;
            }
            final Class111 method3898 = ha.method1793();
            method3898.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            final Class246_Sub1 method3899 = Class94.method915(1, (byte)(-47), this.aBoolean6246);
            final int n2 = super.anInt5084 >> 1484733673;
            if (n >= -12) {
                this.method2975(null, -86);
            }
            final int n3 = super.anInt5079 >> 560688297;
            this.aClass359_6249.method3895(method3897, n2, n3, method3898, true, n2, ha, false, n3);
            if (!Class239.aBoolean1839) {
                method3897.method2325(method3898, method3899.aClass246_Sub6Array5067[0], 0);
            }
            else {
                method3897.method2329(method3898, method3899.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
            }
            if (this.aClass359_6249.aClass246_Sub5_3062 != null) {
                final Class242 method3900 = this.aClass359_6249.aClass246_Sub5_3062.method3116();
                if (Class239.aBoolean1839) {
                    ha.method1785(method3900, Class16.anInt197);
                }
                else {
                    ha.method1820(method3900);
                }
            }
            this.aBoolean6248 = (method3897.F() || this.aClass359_6249.aClass246_Sub5_3062 != null);
            if (this.aClass228_6250 != null) {
                Class283.method3350(super.anInt5089, super.anInt5084, 18, super.anInt5079, method3897, this.aClass228_6250);
            }
            else {
                this.aClass228_6250 = Class48_Sub2_Sub1.method472(super.anInt5089, super.anInt5084, method3897, super.anInt5079, 4);
            }
            return method3899;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final int method63(final byte b) {
        try {
            if (b != 20) {
                Class246_Sub3_Sub1_Sub2.anInt6251 = -77;
            }
            return this.aClass359_6249.anInt3038;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ps.B(" + b + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub1_Sub2.anIntArray6245 = new int[8];
        Class246_Sub3_Sub1_Sub2.anInt6251 = -1;
        Class246_Sub3_Sub1_Sub2.aClass204_6247 = new Class204();
        Class246_Sub3_Sub1_Sub2.anInt6252 = 0;
    }
}
