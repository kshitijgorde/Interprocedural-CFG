// 
// Decompiled by Procyon v0.5.30
// 

final class Class157
{
    static byte[][] aByteArrayArray1248;
    static float aFloat1249;
    private Class148 aClass148_1250;
    private Class98 aClass98_1251;
    
    static final void method2502(final int[] anIntArray4681, final int[] array, final int n) {
        try {
            if (anIntArray4681 == null || array == null) {
                Class190.aByteArrayArrayArray1468 = null;
                Class98_Sub46_Sub13_Sub1.anIntArray6308 = null;
                Class111_Sub1.anIntArray4681 = null;
            }
            else {
                Class111_Sub1.anIntArray4681 = anIntArray4681;
                Class98_Sub46_Sub13_Sub1.anIntArray6308 = new int[anIntArray4681.length];
                Class190.aByteArrayArrayArray1468 = new byte[anIntArray4681.length][][];
                for (int n2 = n; ~Class111_Sub1.anIntArray4681.length < ~n2; ++n2) {
                    Class190.aByteArrayArrayArray1468[n2] = new byte[array[n2]][];
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kk.E(" + ((anIntArray4681 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class98 method2503(final int n) {
        try {
            if (n != 1000) {
                return null;
            }
            final Class98 aClass98_1251 = this.aClass98_1251;
            if (this.aClass148_1250.aClass98_1198 == aClass98_1251) {
                return this.aClass98_1251 = null;
            }
            this.aClass98_1251 = aClass98_1251.aClass98_836;
            return aClass98_1251;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kk.F(" + n + ')');
        }
    }
    
    final Class98 method2504(final byte b) {
        try {
            if (b >= -113) {
                return null;
            }
            final Class98 aClass98_836 = this.aClass148_1250.aClass98_1198.aClass98_836;
            if (aClass98_836 == this.aClass148_1250.aClass98_1198) {
                return this.aClass98_1251 = null;
            }
            this.aClass98_1251 = aClass98_836.aClass98_836;
            return aClass98_836;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kk.B(" + b + ')');
        }
    }
    
    final void method2505(final byte b, final Class148 aClass148_1250) {
        try {
            this.aClass148_1250 = aClass148_1250;
            if (b != 26) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kk.C(" + b + ',' + ((aClass148_1250 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method2506(final int n, final Class98_Sub46_Sub8 class98_Sub46_Sub8) {
        try {
            if (class98_Sub46_Sub8 != null && n > 117) {
                Class33.aClass148_315.method2419(class98_Sub46_Sub8, -20911);
                ++Class359.anInt3058;
                Class98_Sub46_Sub9 class98_Sub46_Sub9;
                if (!class98_Sub46_Sub8.aBoolean5989 && !"".equals(class98_Sub46_Sub8.aString5992)) {
                    final long aLong5991 = class98_Sub46_Sub8.aLong5991;
                    for (class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub47.aClass377_4274.method3990(aLong5991, -1); class98_Sub46_Sub9 != null && !class98_Sub46_Sub9.aString5998.equals(class98_Sub46_Sub8.aString5992); class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub47.aClass377_4274.method3993(122)) {}
                    if (class98_Sub46_Sub9 == null) {
                        class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub46_Sub16.aClass79_6046.method802(-126, aLong5991);
                        if (class98_Sub46_Sub9 != null && !class98_Sub46_Sub9.aString5998.equals(class98_Sub46_Sub8.aString5992)) {
                            class98_Sub46_Sub9 = null;
                        }
                        if (class98_Sub46_Sub9 == null) {
                            class98_Sub46_Sub9 = new Class98_Sub46_Sub9(class98_Sub46_Sub8.aString5992);
                        }
                        Class98_Sub47.aClass377_4274.method3996(class98_Sub46_Sub9, aLong5991, -1);
                        ++Class64_Sub12.anInt3672;
                    }
                }
                else {
                    class98_Sub46_Sub9 = new Class98_Sub46_Sub9(class98_Sub46_Sub8.aString5992);
                    ++Class64_Sub12.anInt3672;
                }
                if (class98_Sub46_Sub9.method1555(90, class98_Sub46_Sub8)) {
                    Class9.method189(class98_Sub46_Sub9, (byte)87);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kk.A(" + n + ',' + ((class98_Sub46_Sub8 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2507(final int n) {
        try {
            Class157.aByteArrayArray1248 = null;
            if (n <= 19) {
                Class157.aFloat1249 = -0.7901263f;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kk.D(" + n + ')');
        }
    }
    
    public Class157() {
    }
    
    Class157(final Class148 aClass148_1250) {
        try {
            this.aClass148_1250 = aClass148_1250;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kk.<init>(" + ((aClass148_1250 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class157.aByteArrayArray1248 = new byte[1000][];
    }
}
