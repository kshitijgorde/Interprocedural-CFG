// 
// Decompiled by Procyon v0.5.30
// 

class Class98
{
    long aLong832;
    Class98 aClass98_833;
    static Class38 aClass38_834;
    static int anInt835;
    Class98 aClass98_836;
    static int anInt837;
    
    public static void method940(final boolean b) {
        try {
            if (!b) {
                Class98.aClass38_834 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gda.AA(" + b + ')');
        }
    }
    
    final boolean method941(final byte b) {
        try {
            if (this.aClass98_833 == null) {
                return false;
            }
            if (b != 78) {
                method943(null, false, true);
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gda.T(" + b + ')');
        }
    }
    
    final void method942(final int n) {
        try {
            if (n <= 41) {
                Class98.anInt837 = -8;
            }
            if (this.aClass98_833 != null) {
                this.aClass98_833.aClass98_836 = this.aClass98_836;
                this.aClass98_836.aClass98_833 = this.aClass98_833;
                this.aClass98_833 = null;
                this.aClass98_836 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gda.V(" + n + ')');
        }
    }
    
    static final void method943(final byte[] array, final boolean b, final boolean b2) {
        try {
            if (b2) {
                method943(null, false, true);
            }
            if (Class98_Sub10_Sub28.aClass98_Sub22_5705 == null) {
                Class98_Sub10_Sub28.aClass98_Sub22_5705 = new Class98_Sub22(20000);
            }
            Class98_Sub10_Sub28.aClass98_Sub22_5705.method1217(array, array.length, -1, 0);
            if (b) {
                Class30.method304(120, Class98_Sub10_Sub28.aClass98_Sub22_5705.aByteArray3992);
                Class98_Sub28_Sub1.aClass53_Sub1Array5805 = new Class53_Sub1[Class42_Sub3.anInt5366];
                int n = 0;
                for (int anInt1274 = Class164.anInt1274; Class101.anInt854 >= anInt1274; ++anInt1274) {
                    final Class53_Sub1 method3283 = Class275.method3283((byte)127, anInt1274);
                    if (method3283 != null) {
                        Class98_Sub28_Sub1.aClass53_Sub1Array5805[n++] = method3283;
                    }
                }
                Class64_Sub12.aBoolean3671 = false;
                Class267.aLong1998 = Class343.method3819(-47);
                Class98_Sub10_Sub28.aClass98_Sub22_5705 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gda.U(" + ((array != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ')');
        }
    }
    
    static final boolean method944(final int n, final int n2, final byte b) {
        try {
            if (b != 85) {
                method944(43, 69, (byte)22);
            }
            return ~(0x22 & n) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gda.W(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static {
        Class98.anInt835 = -1;
        Class98.anInt837 = 0;
        Class98.aClass38_834 = new Class38();
    }
}
