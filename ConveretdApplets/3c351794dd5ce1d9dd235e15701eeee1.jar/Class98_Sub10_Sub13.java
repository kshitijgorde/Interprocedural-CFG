// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub13 extends Class98_Sub10
{
    static int anInt5600;
    private int anInt5601;
    static int anInt5602;
    static int[] anIntArray5603;
    
    public Class98_Sub10_Sub13() {
        this(4096);
    }
    
    static final int method1041(final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2, final int n) {
        try {
            if (~class246_Sub3_Sub4_Sub2.anInt6414 == -1) {
                return 0;
            }
            if (~class246_Sub3_Sub4_Sub2.anInt6364 != n) {
                Class246_Sub3 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = null;
                if (~class246_Sub3_Sub4_Sub2.anInt6364 > -32769) {
                    final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(class246_Sub3_Sub4_Sub2.anInt6364, n - 1);
                    if (class98_Sub39 != null) {
                        aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                    }
                }
                else if (~class246_Sub3_Sub4_Sub2.anInt6364 <= -32769) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[class246_Sub3_Sub4_Sub2.anInt6364 - 32768];
                }
                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187 != null) {
                    final int n2 = -aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 + class246_Sub3_Sub4_Sub2.anInt5084;
                    final int n3 = -aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 + class246_Sub3_Sub4_Sub2.anInt5079;
                    if (~n2 != -1 || n3 != 0) {
                        class246_Sub3_Sub4_Sub2.method3042(0x3FFF & (int)(2607.5945876176133 * Math.atan2(n2, n3)), -8193);
                    }
                }
            }
            if (!(class246_Sub3_Sub4_Sub2 instanceof Class246_Sub3_Sub4_Sub2_Sub2)) {
                if (!(class246_Sub3_Sub4_Sub2 instanceof Class246_Sub3_Sub4_Sub2_Sub1)) {
                    return class246_Sub3_Sub4_Sub2.method3033((byte)(-39));
                }
                final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1 = (Class246_Sub3_Sub4_Sub2_Sub1)class246_Sub3_Sub4_Sub2;
                if (class246_Sub3_Sub4_Sub2_Sub1.anInt6510 == -1) {
                    return class246_Sub3_Sub4_Sub2.method3033((byte)(-39));
                }
                if (class246_Sub3_Sub4_Sub2_Sub1.anInt6434 != 0 && ~class246_Sub3_Sub4_Sub2_Sub1.anInt6433 >= -1) {
                    return class246_Sub3_Sub4_Sub2.method3033((byte)(-39));
                }
                final int n4 = class246_Sub3_Sub4_Sub2_Sub1.anInt5084 - (-Class272.anInt2038 + class246_Sub3_Sub4_Sub2_Sub1.anInt6510 + -Class272.anInt2038) * 256;
                final int n5 = class246_Sub3_Sub4_Sub2_Sub1.anInt5079 - (-aa_Sub2.anInt3562 + (class246_Sub3_Sub4_Sub2_Sub1.anInt6505 - aa_Sub2.anInt3562)) * 256;
                if (~n4 != -1 || n5 != 0) {
                    class246_Sub3_Sub4_Sub2_Sub1.method3042((int)(2607.5945876176133 * Math.atan2(n4, n5)) & 0x3FFF, n ^ 0xFFFFDFFF);
                }
                class246_Sub3_Sub4_Sub2_Sub1.anInt6510 = -1;
                if (!client.aBoolean3553) {
                    return class246_Sub3_Sub4_Sub2.method3033((byte)(-39));
                }
            }
            final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = (Class246_Sub3_Sub4_Sub2_Sub2)class246_Sub3_Sub4_Sub2;
            if (class246_Sub3_Sub4_Sub2_Sub2.anInt6512 != -1 && (class246_Sub3_Sub4_Sub2_Sub2.anInt6434 == 0 || ~class246_Sub3_Sub4_Sub2_Sub2.anInt6433 < -1)) {
                class246_Sub3_Sub4_Sub2_Sub2.method3042(class246_Sub3_Sub4_Sub2_Sub2.anInt6512, -8193);
                class246_Sub3_Sub4_Sub2_Sub2.anInt6512 = -1;
            }
            return class246_Sub3_Sub4_Sub2.method3033((byte)(-39));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hb.E(" + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method1042(final int n) {
        try {
            if (n > 126) {
                Class98_Sub10_Sub13.anIntArray5603 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hb.D(" + n + ')');
        }
    }
    
    Class98_Sub10_Sub13(final int anInt5601) {
        super(0, true);
        this.anInt5601 = 4096;
        try {
            this.anInt5601 = anInt5601;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hb.<init>(" + anInt5601 + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                method1043((byte)117);
            }
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                Class236.method2896(method237, 0, Class25.anInt268, this.anInt5601);
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hb.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b > -92) {
                Class98_Sub10_Sub13.anIntArray5603 = null;
            }
            if (~n == -1) {
                this.anInt5601 = (class98_Sub22.readUnsignedByte((byte)(-110)) << -1329303188) / 255;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hb.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method1043(final byte b) {
        try {
            if (~Class151_Sub9.anInt5028 == 0xFFFFFFFE || ~Class151_Sub9.anInt5028 == 0xFFFFFFFC || (~Class118.anInt978 != ~Class151_Sub9.anInt5028 && (Class151_Sub9.anInt5028 == 0 || Class118.anInt978 == 0))) {
                Class150.anInt1211 = 0;
                Class98_Sub10_Sub20.anInt5640 = 0;
                Class260.aClass377_3254.method3994(-67);
            }
            Class118.anInt978 = Class151_Sub9.anInt5028;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hb.B(" + b + ')');
        }
    }
    
    static final void method1044(final byte b, final String[] aStringArray1189) {
        try {
            if (~aStringArray1189.length >= -2) {
                Class45.aString382 += aStringArray1189[0];
                Class198.anInt1524 += aStringArray1189[0].length();
            }
            else {
                for (int i = 0; i < aStringArray1189.length; ++i) {
                    if (aStringArray1189[i].startsWith("pause")) {
                        int int1 = 5;
                        try {
                            int1 = Integer.parseInt(aStringArray1189[i].substring(6));
                        }
                        catch (Exception ex2) {}
                        Class98_Sub46.method1525("Pausing for " + int1 + " seconds...", 101);
                        Class169.anInt1306 = i + 1;
                        Class147.aStringArray1189 = aStringArray1189;
                        Class198.aLong1525 = Class343.method3819(-47) - -(int1 * 1000);
                        return;
                    }
                    Class45.aString382 = aStringArray1189[i];
                    Class295.method3484(false, false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hb.F(" + b + ',' + ((aStringArray1189 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub13.anInt5602 = 0;
        Class98_Sub10_Sub13.anIntArray5603 = new int[5];
    }
}
