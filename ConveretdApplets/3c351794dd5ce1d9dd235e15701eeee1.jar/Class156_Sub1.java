// 
// Decompiled by Procyon v0.5.30
// 

final class Class156_Sub1 extends Class156 implements Interface8
{
    private int anInt3276;
    static Class377 aClass377_3277;
    static int anInt3278;
    static int[] anIntArray3279;
    
    @Override
    public final int method19(final int n) {
        try {
            if (n != -22132) {
                return 116;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qs.B(" + n + ')');
        }
    }
    
    @Override
    public final long method22(final int n) {
        try {
            if (n != 20260) {
                return -35L;
            }
            return super.aBuffer1247.getAddress();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qs.I(" + n + ')');
        }
    }
    
    @Override
    public final int method21(final int n) {
        try {
            if (n != 5061) {
                Class156_Sub1.anInt3278 = 93;
            }
            return this.anInt3276;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qs.A(" + n + ')');
        }
    }
    
    Class156_Sub1(final ha_Sub1 ha_Sub1, final int anInt3276, final byte[] array, final int n) {
        super(ha_Sub1, array, n);
        try {
            this.anInt3276 = anInt3276;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qs.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + anInt3276 + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method2497(final byte b) {
        try {
            if (b >= -62) {
                Class156_Sub1.anInt3278 = -42;
            }
            Class156_Sub1.aClass377_3277 = null;
            Class156_Sub1.anIntArray3279 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qs.E(" + b + ')');
        }
    }
    
    @Override
    public final void method20(final byte b, final byte[] array, final int n, final int anInt3276) {
        try {
            this.method2496(array, n);
            if (b == -47) {
                this.anInt3276 = anInt3276;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qs.H(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + anInt3276 + ')');
        }
    }
    
    static final int method2498(final int n, final byte b, final boolean b2) {
        try {
            if (b2) {
                return 0;
            }
            final Class98_Sub3 method669 = Class64_Sub28.method669(n, b2, 6);
            if (method669 == null) {
                return Class98_Sub46_Sub14.aClass8_5378.method185(9, n).anInt6055;
            }
            int n2 = 0;
            for (int n3 = 0; method669.anIntArray3824.length > n3; ++n3) {
                if (method669.anIntArray3824[n3] == -1) {
                    ++n2;
                }
            }
            if (b <= 93) {
                return -9;
            }
            return n2 + (Class98_Sub46_Sub14.aClass8_5378.method185(9, n).anInt6055 - method669.anIntArray3824.length);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qs.C(" + n + ',' + b + ',' + b2 + ')');
        }
    }
    
    static final int method2499(final int n, final int n2, final int n3) {
        try {
            return (Class242.method2934(11348, n3 - 1, n2) - (-Class242.method2934(11348, 1 + n3, n2) + -Class242.method2934(11348, n3, -1 + n2) + -Class242.method2934(11348, n3, 1 + n2))) / 8 + (Class242.method2934(11348, n3 - 1, -1 + n2) + Class242.method2934(11348, 1 + n3, -1 + n2) - (-Class242.method2934(11348, n3 - 1, 1 + n2) + -Class242.method2934(11348, n3 + 1, 1 + n2))) / 16 - -(Class242.method2934(11348, n3, n2) / 4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qs.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class156_Sub1.aClass377_3277 = new Class377(4);
        Class156_Sub1.anIntArray3279 = new int[13];
    }
}
