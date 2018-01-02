// 
// Decompiled by Procyon v0.5.30
// 

class Class284_Sub2 extends Class284
{
    private int anInt5179;
    static OutgoingOpcode aClass171_5180;
    private int anInt5181;
    private int anInt5182;
    private int anInt5183;
    private byte[] aByteArray5184;
    private int anInt5185;
    static int anInt5186;
    private int anInt5187;
    private int anInt5188;
    private int anInt5189;
    
    @Override
    final void method3356(final int n, final int n2, final int n3) {
        try {
            if (~n == -1) {
                this.anInt5188 = -((~n2 > -1) ? (-n2) : n2) + this.anInt5179;
                this.anInt5183 = 4096;
                this.anInt5188 = this.anInt5188 * this.anInt5188 >> 419437836;
                this.anInt5185 = this.anInt5188;
            }
            else {
                this.anInt5183 = this.anInt5187 * this.anInt5188 >> 1860817932;
                if (~this.anInt5183 > -1) {
                    this.anInt5183 = 0;
                }
                else if (this.anInt5183 > 4096) {
                    this.anInt5183 = 4096;
                }
                this.anInt5188 = this.anInt5179 + -((~n2 <= -1) ? n2 : (-n2));
                this.anInt5188 = this.anInt5188 * this.anInt5188 >> -1937288276;
                this.anInt5188 = this.anInt5183 * this.anInt5188 >> -133065620;
                this.anInt5185 += this.anInt5182 * this.anInt5188 >> 1705588460;
                this.anInt5182 = this.anInt5189 * this.anInt5182 >> -387876148;
            }
            if (n3 != 255) {
                this.anInt5183 = -18;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gt.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method3358(final int anInt5185) {
        try {
            this.anInt5185 >>= 4;
            this.anInt5182 = this.anInt5189;
            Label_0064: {
                if (~this.anInt5185 <= -1) {
                    if (~this.anInt5185 >= -256) {
                        break Label_0064;
                    }
                    this.anInt5185 = 255;
                    if (!client.aBoolean3553) {
                        break Label_0064;
                    }
                }
                this.anInt5185 = 0;
            }
            this.method3375(this.anInt5181++, 64, (byte)this.anInt5185);
            this.anInt5185 = anInt5185;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gt.H(" + anInt5185 + ')');
        }
    }
    
    @Override
    final void method3354(final int n) {
        try {
            this.anInt5181 = 0;
            if (n != 751943489) {
                method3376(-60);
            }
            this.anInt5185 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gt.D(" + n + ')');
        }
    }
    
    static final Class42_Sub1_Sub1 method3374(final int n, final int n2, final int n3, final int n4, final ha_Sub1 ha_Sub1, final boolean b, final byte[] array, final int n5) {
        try {
            if (n3 != 14764) {
                method3376(-123);
            }
            if (ha_Sub1.aBoolean4426 || (Class81.method815(n5, 0) && Class81.method815(n2, 0))) {
                return new Class42_Sub1_Sub1(ha_Sub1, 3553, n4, n5, n2, b, array, n);
            }
            if (!ha_Sub1.aBoolean4378) {
                return new Class42_Sub1_Sub1(ha_Sub1, n4, n5, n2, Class48.method453(423660257, n5), Class48.method453(423660257, n2), array, n);
            }
            return new Class42_Sub1_Sub1(ha_Sub1, 34037, n4, n5, n2, b, array, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gt.K(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n5 + ')');
        }
    }
    
    void method3375(final int n, final int n2, final byte b) {
        try {
            this.aByteArray5184[n] = b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gt.L(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    Class284_Sub2(final int n, final int n2, final int n3, final int n4, final int n5, final float n6, final float n7, final float n8) {
        super(n, n2, n3, n4, n5);
        try {
            this.anInt5187 = (int)(n8 * 4096.0f);
            this.anInt5179 = (int)(n7 * 4096.0f);
            final int n9 = (int)(Math.pow(0.5, -n6) * 4096.0);
            this.anInt5189 = n9;
            this.anInt5182 = n9;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gt.<init>(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    public static void method3376(final int n) {
        try {
            Class284_Sub2.aClass171_5180 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gt.J(" + n + ')');
        }
    }
    
    static {
        Class284_Sub2.anInt5186 = -1;
        Class284_Sub2.aClass171_5180 = new OutgoingOpcode(43, 7);
    }
}
