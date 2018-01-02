// 
// Decompiled by Procyon v0.5.30
// 

final class Class186 implements Interface17
{
    static int anInt3424;
    private Class333 aClass333_3425;
    int anInt3426;
    int[] anIntArray3427;
    static Class148 aClass148_3428;
    float[] aFloatArray3429;
    int anInt3430;
    static int anInt3431;
    private ha_Sub2 aHa_Sub2_3432;
    static Class348 aClass348_3433;
    
    @Override
    public final void method56(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2) {
        try {
            Class246_Sub3_Sub4_Sub5.method3086(this.aHa_Sub2_3432.aClass98_Sub32_4478.anInt4105, (int[])(b ? this.aHa_Sub2_3432.aClass98_Sub32_4478.anIntArray4108 : null), n2, n, n3, (float[])(b2 ? this.aFloatArray3429 : null), n6, this.anIntArray3427, n4, n5, (float[])(b2 ? this.aHa_Sub2_3432.aFloatArray4488 : null), this.anInt3426, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mga.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + b + ',' + b2 + ')');
        }
    }
    
    public static void method2633(final int n) {
        try {
            Class186.aClass148_3428 = null;
            if (n != -20830) {
                Class186.anInt3424 = 49;
            }
            Class186.aClass348_3433 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mga.C(" + n + ')');
        }
    }
    
    @Override
    public final void method57(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2) {
        try {
            Class246_Sub3_Sub4_Sub5.method3086(this.anInt3426, (int[])(b ? this.anIntArray3427 : null), n2, n, n3, (float[])(b2 ? this.aHa_Sub2_3432.aFloatArray4488 : null), n6, this.aHa_Sub2_3432.aClass98_Sub32_4478.anIntArray4108, n4, n5, (float[])(b2 ? this.aFloatArray3429 : null), this.aHa_Sub2_3432.aClass98_Sub32_4478.anInt4105, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mga.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + b + ',' + b2 + ')');
        }
    }
    
    Class186(final ha_Sub2 aHa_Sub2_3432, final Class332 class332, final Class333 aClass333_3425) {
        try {
            this.aHa_Sub2_3432 = aHa_Sub2_3432;
            Label_0106: {
                if (!(class332 instanceof Class332_Sub3_Sub2)) {
                    if (!(class332 instanceof Class332_Sub3_Sub1)) {
                        throw new RuntimeException();
                    }
                    final Class332_Sub3_Sub1 class332_Sub3_Sub1 = (Class332_Sub3_Sub1)class332;
                    this.anInt3426 = class332_Sub3_Sub1.anInt5433;
                    this.anIntArray3427 = class332_Sub3_Sub1.anIntArray6212;
                    this.anInt3430 = class332_Sub3_Sub1.anInt5454;
                    if (!client.aBoolean3553) {
                        break Label_0106;
                    }
                }
                final Class332_Sub3_Sub2 class332_Sub3_Sub2 = (Class332_Sub3_Sub2)class332;
                this.anInt3430 = class332_Sub3_Sub2.anInt5454;
                this.anIntArray3427 = class332_Sub3_Sub2.anIntArray6213;
                this.anInt3426 = class332_Sub3_Sub2.anInt5433;
            }
            if (aClass333_3425 != null) {
                this.aClass333_3425 = aClass333_3425;
                if (~this.anInt3426 != ~this.aClass333_3425.anInt3388 || ~this.anInt3430 != ~this.aClass333_3425.anInt3387) {
                    throw new RuntimeException();
                }
                this.aFloatArray3429 = this.aClass333_3425.aFloatArray3389;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mga.<init>(" + ((aHa_Sub2_3432 != null) ? "{...}" : "null") + ',' + ((class332 != null) ? "{...}" : "null") + ',' + ((aClass333_3425 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class186.anInt3431 = 0;
        Class186.aClass148_3428 = new Class148();
        Class186.aClass348_3433 = new Class348(8, 0, 4, 1);
    }
}
