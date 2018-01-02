// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub3_Sub1 extends Class246_Sub3_Sub3 implements Interface19
{
    private boolean aBoolean6253;
    private Class228 aClass228_6254;
    private boolean aBoolean6255;
    static boolean[] aBooleanArray6256;
    static float aFloat6257;
    Class359 aClass359_6258;
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            final Class146 method3897 = this.aClass359_6258.method3897(-1, false, 131072, ha, false);
            if (method3897 == null) {
                return false;
            }
            if (b <= 59) {
                this.aBoolean6253 = false;
            }
            final Class111 method3898 = ha.method1793();
            method3898.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            if (!Class239.aBoolean1839) {
                return method3897.method2339(n, n2, method3898, false, 0);
            }
            return method3897.method2333(n, n2, method3898, false, 0, Class16.anInt197);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    public final void method67(final int n, final ha ha) {
        try {
            if (n != -25163) {
                this.method2987(-89);
            }
            this.aClass359_6258.method3894((byte)(-72), ha);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.E(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2987(final int n) {
        try {
            return n == 6540 && this.aBoolean6255;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.I(" + n + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (b != -53) {
                this.aBoolean6255 = false;
            }
            return this.aClass228_6254;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final int method64(final int n) {
        try {
            if (n != 30472) {
                return -48;
            }
            return this.aClass359_6258.anInt3052;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.C(" + n + ')');
        }
    }
    
    Class246_Sub3_Sub3_Sub1(final ha ha, final Class352 class352, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final int n6, final int n7, final int n8) {
        super(n3, n4, n5, n, n2, Class1.method160(n7, (byte)(-101), n6));
        this.aBoolean6255 = false;
        try {
            this.aClass359_6258 = new Class359(ha, class352, n6, n7, super.aByte5088, n2, this, b, n8);
            this.aBoolean6253 = (~class352.anInt2998 != -1 && !b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((class352 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
        try {
            final Class146 method3897 = this.aClass359_6258.method3897(-1, true, 262144, ha, true);
            if (method3897 != null) {
                final int n2 = super.anInt5084 >> -1240765751;
                final int n3 = super.anInt5079 >> -1493666103;
                final Class111 method3898 = ha.method1793();
                method3898.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
                this.aClass359_6258.method3895(method3897, n2, n3, method3898, false, n2, ha, false, n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.MA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            if (b != -73) {
                Class246_Sub3_Sub3_Sub1.aFloat6257 = 1.3506109f;
            }
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.DA(" + b + ')');
        }
    }
    
    @Override
    public final boolean method65(final boolean b) {
        try {
            if (!b) {
                this.method2990(-31);
            }
            return this.aClass359_6258.method3898(35);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.A(" + b + ')');
        }
    }
    
    @Override
    public final int method66(final int n) {
        try {
            if (n != 4657) {
                return -76;
            }
            return this.aClass359_6258.anInt3059;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.N(" + n + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                return -32;
            }
            return this.aClass359_6258.method3903((byte)(-102));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.O(" + b + ')');
        }
    }
    
    final void method3016(final int n, final Class185 class185) {
        try {
            this.aClass359_6258.method3901(class185, n ^ 0xFFFFF787);
            if (n != 2048) {
                this.aClass228_6254 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.P(" + n + ',' + ((class185 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void method62(final ha ha, final int n) {
        try {
            this.aClass359_6258.method3892(ha, n - 24343);
            if (n != 24447) {
                this.method2981(null, (byte)16, true, -102, null, 75, -43);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.G(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.H(" + n + ')');
        }
    }
    
    @Override
    public final int method63(final byte b) {
        try {
            if (b != 20) {
                this.aClass228_6254 = null;
            }
            return this.aClass359_6258.anInt3038;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.B(" + b + ')');
        }
    }
    
    @Override
    public final void method61(final byte b) {
        try {
            if (b != -96) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.Q(" + b + ')');
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                this.method62(null, 53);
            }
            return this.aClass359_6258.method3899((byte)127);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.J(" + n + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b > -70) {
                this.method61((byte)39);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.HA(" + b + ')');
        }
    }
    
    public static void method3017(final byte b) {
        try {
            Class246_Sub3_Sub3_Sub1.aBooleanArray6256 = null;
            if (b < 19) {
                method3017((byte)70);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.R(" + b + ')');
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            final Class146 method3897 = this.aClass359_6258.method3897(-1, false, 2048, ha, true);
            if (method3897 == null) {
                return null;
            }
            final Class111 method3898 = ha.method1793();
            method3898.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            final Class246_Sub1 method3899 = Class94.method915(1, (byte)(-47), this.aBoolean6253);
            final int n2 = super.anInt5084 >> -600721783;
            final int n3 = super.anInt5079 >> 1261366377;
            this.aClass359_6258.method3895(method3897, n2, n3, method3898, true, n2, ha, false, n3);
            if (Class239.aBoolean1839) {
                method3897.method2329(method3898, method3899.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
            }
            else {
                method3897.method2325(method3898, method3899.aClass246_Sub6Array5067[0], 0);
            }
            if (n > -12) {
                Class246_Sub3_Sub3_Sub1.aFloat6257 = 0.4871182f;
            }
            if (this.aClass359_6258.aClass246_Sub5_3062 != null) {
                final Class242 method3900 = this.aClass359_6258.aClass246_Sub5_3062.method3116();
                if (!Class239.aBoolean1839) {
                    ha.method1820(method3900);
                }
                else {
                    ha.method1785(method3900, Class16.anInt197);
                }
            }
            this.aBoolean6255 = (method3897.F() || this.aClass359_6258.aClass246_Sub5_3062 != null);
            if (this.aClass228_6254 == null) {
                this.aClass228_6254 = Class48_Sub2_Sub1.method472(super.anInt5089, super.anInt5084, method3897, super.anInt5079, 4);
            }
            else {
                Class283.method3350(super.anInt5089, super.anInt5084, 18, super.anInt5079, method3897, this.aClass228_6254);
            }
            return method3899;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tp.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub3_Sub1.aFloat6257 = 1.0f;
    }
}
