// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub42 extends Class98
{
    int anInt4205;
    Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_4206;
    boolean aBoolean4207;
    int[] anIntArray4208;
    Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4209;
    int anInt4210;
    Class98_Sub24_Sub1 aClass98_Sub24_Sub1_4211;
    static long aLong4212;
    Class98_Sub13 aClass98_Sub13_4213;
    Class98_Sub24_Sub1 aClass98_Sub24_Sub1_4214;
    boolean aBoolean4215;
    int anInt4216;
    int anInt4217;
    static boolean aBoolean4218;
    int anInt4219;
    int anInt4220;
    int anInt4221;
    static IncomingOpcode aClass58_4222;
    int anInt4223;
    int anInt4224;
    int anInt4225;
    boolean aBoolean4226;
    int anInt4227;
    int anInt4228;
    int anInt4229;
    Class98_Sub31_Sub5 aClass98_Sub31_Sub5_4230;
    Class98_Sub13 aClass98_Sub13_4231;
    Class98_Sub31_Sub5 aClass98_Sub31_Sub5_4232;
    Class352 aClass352_4233;
    static float aFloat4234;
    static OutgoingOpcode aClass171_4235;
    int anInt4236;
    int anInt4237;
    static long aLong4238;
    static int anInt4239;
    
    static final void method1476(final int n, final int n2) {
        try {
            if (n != 256) {
                Class98_Sub42.anInt4239 = -78;
            }
            final Class98_Sub36 class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3990(n2, -1);
            if (class98_Sub36 != null) {
                class98_Sub36.aBoolean4153 = !class98_Sub36.aBoolean4153;
                class98_Sub36.aClass237_Sub1_4157.method2911(class98_Sub36.aBoolean4153, (byte)22);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qfa.B(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1477(final boolean b) {
        try {
            if (Class241.anObject1847 == null) {
                Class241.anObject1847 = Class64_Sub25.method654(2, new Class284_Sub2_Sub2().method3379(16, 20283, 128, 128), false);
            }
            if (!b) {
                if (Class64_Sub24.anObject3709 == null) {
                    Class64_Sub24.anObject3709 = Class64_Sub25.method654(2, new Class284_Sub1_Sub2().method3372(!b, 128, 16, 128), false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qfa.E(" + b + ')');
        }
    }
    
    final void method1478(final boolean b) {
        try {
            if (!b) {
                this.anInt4228 = 82;
            }
            final int anInt4210 = this.anInt4210;
            final boolean aBoolean4215 = this.aBoolean4215;
            if (this.aClass352_4233 != null) {
                final Class352 method3852 = this.aClass352_4233.method3852(Class75.aClass140_584, (byte)(-96));
                if (method3852 != null) {
                    this.anInt4223 = method3852.anInt3006;
                    this.anInt4219 = method3852.anInt2949;
                    this.aBoolean4226 = method3852.aBoolean2957;
                    this.anInt4205 = method3852.anInt2972;
                    this.anInt4236 = method3852.anInt2987;
                    this.anInt4210 = method3852.anInt2996;
                    this.aBoolean4215 = method3852.aBoolean2992;
                    this.anIntArray4208 = method3852.anIntArray2926;
                    this.anInt4237 = method3852.anInt2950;
                    this.anInt4228 = method3852.anInt2981 << -1567841911;
                }
                else {
                    this.aBoolean4215 = false;
                    this.anInt4237 = 256;
                    this.anInt4228 = 0;
                    this.anIntArray4208 = null;
                    this.anInt4223 = 256;
                    this.anInt4217 = 0;
                    this.anInt4205 = 0;
                    this.anInt4219 = 0;
                    this.aBoolean4226 = false;
                    this.anInt4236 = 0;
                    this.anInt4210 = -1;
                }
            }
            else if (this.aClass246_Sub3_Sub4_Sub2_Sub1_4209 == null) {
                if (this.aClass246_Sub3_Sub4_Sub2_Sub2_4206 != null) {
                    this.anInt4210 = Class286.method3383(this.aClass246_Sub3_Sub4_Sub2_Sub2_4206, b);
                    this.anInt4223 = 256;
                    this.anInt4217 = 0;
                    this.anInt4228 = this.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6525 << 1762065833;
                    this.aBoolean4215 = this.aClass246_Sub3_Sub4_Sub2_Sub2_4206.aBoolean6526;
                    this.anInt4236 = this.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6514;
                    this.anInt4237 = 256;
                }
            }
            else {
                final int method3853 = Class277.method3293(125, this.aClass246_Sub3_Sub4_Sub2_Sub1_4209);
                if (anInt4210 != method3853) {
                    this.anInt4210 = method3853;
                    Class141 class141 = this.aClass246_Sub3_Sub4_Sub2_Sub1_4209.aClass141_6504;
                    if (class141.anIntArray1109 != null) {
                        class141 = class141.method2300(Class75.aClass140_584, (byte)79);
                    }
                    if (class141 != null) {
                        this.anInt4236 = class141.anInt1156;
                        this.anInt4223 = class141.anInt1101;
                        this.anInt4237 = class141.anInt1090;
                        this.aBoolean4215 = class141.aBoolean1093;
                        this.anInt4217 = class141.anInt1125 << 1720816489;
                        this.anInt4228 = class141.anInt1128 << 958920233;
                    }
                    else {
                        final boolean anInt4211 = false;
                        this.anInt4217 = (anInt4211 ? 1 : 0);
                        this.anInt4228 = (anInt4211 ? 1 : 0);
                        this.anInt4236 = (anInt4211 ? 1 : 0);
                        this.aBoolean4215 = this.aClass246_Sub3_Sub4_Sub2_Sub1_4209.aClass141_6504.aBoolean1093;
                        this.anInt4237 = 256;
                        this.anInt4223 = 256;
                    }
                }
            }
            if (~anInt4210 != ~this.anInt4210 || this.aBoolean4215 == !aBoolean4215) {
                if (this.aClass98_Sub31_Sub5_4232 != null) {
                    Class81.aClass98_Sub31_Sub3_619.method1374(this.aClass98_Sub31_Sub5_4232);
                    this.aClass98_Sub13_4213 = null;
                    this.aClass98_Sub31_Sub5_4232 = null;
                    this.aClass98_Sub24_Sub1_4214 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qfa.A(" + b + ')');
        }
    }
    
    static final boolean method1479(final String s, final int n) {
        try {
            return n != 0 || Class10.aHashtable118.containsKey(s);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qfa.D(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method1480(final byte b) {
        try {
            Class98_Sub42.aClass58_4222 = null;
            if (b <= 77) {
                method1476(-13, -115);
            }
            Class98_Sub42.aClass171_4235 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qfa.C(" + b + ')');
        }
    }
    
    public Class98_Sub42() {
        this.anInt4227 = 0;
    }
    
    static {
        Class98_Sub42.aBoolean4218 = false;
        Class98_Sub42.aClass58_4222 = new IncomingOpcode(42, 6);
        Class98_Sub42.aLong4238 = (long)(9.999999999E9 * Math.random());
        Class98_Sub42.aClass171_4235 = new OutgoingOpcode(65, 2);
    }
}
