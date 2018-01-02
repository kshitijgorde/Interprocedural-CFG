// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub6 extends Class98_Sub46
{
    Class35 aClass35_5971;
    int anInt5972;
    Class66 aClass66_5973;
    int anInt5974;
    static IncomingOpcode aClass58_5975;
    int anInt5976;
    int anInt5977;
    int anInt5978;
    static int anInt5979;
    static int[] anIntArray5980;
    
    static final String method1546(final int n, final int n2, final byte b, final byte[] array) {
        try {
            final char[] array2 = new char[n];
            int n3 = 0;
            if (b >= -49) {
                method1548(-78, 47, 80, 103, (byte)(-48), -72, 123);
            }
            for (int n4 = 0; ~n4 > ~n; ++n4) {
                int n5 = array[n2 + n4] & 0xFF;
                if (~n5 != -1) {
                    if (~n5 <= -129 && n5 < 160) {
                        int n6 = Class65.aCharArray497[-128 + n5];
                        if (n6 == 0) {
                            n6 = 63;
                        }
                        n5 = n6;
                    }
                    array2[n3++] = (char)n5;
                }
            }
            return new String(array2, 0, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dh.A(" + n + ',' + n2 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method1547(final int n) {
        try {
            this.anInt5974 = this.aClass35_5971.anInt330;
            this.anInt5978 = this.aClass35_5971.anInt337;
            this.anInt5972 = this.aClass35_5971.anInt331;
            if (this.aClass35_5971.aClass111_334 != null) {
                this.aClass35_5971.aClass111_334.method2099(this.aClass66_5973.anInt506, this.aClass66_5973.anInt511, this.aClass66_5973.anInt505, Class210.anIntArray3329);
            }
            this.anInt5977 = Class210.anIntArray3329[2];
            this.anInt5976 = Class210.anIntArray3329[0];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dh.D(" + n + ')');
        }
    }
    
    static final void method1548(final int anInt3274, final int anInt3275, final int anInt3276, final int anInt3277, final byte b, final int anInt3278, final int anInt3279) {
        try {
            if (b != -78) {
                Class98_Sub46_Sub6.anIntArray5980 = null;
            }
            Class64_Sub28.anInt3717 = anInt3279;
            Class64_Sub6.anInt3655 = anInt3277;
            Class287_Sub2.anInt3274 = anInt3274;
            Class356.anInt3025 = anInt3276;
            Class137.anInt1079 = anInt3278;
            Class98_Sub42.anInt4239 = anInt3275;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dh.E(" + anInt3274 + ',' + anInt3275 + ',' + anInt3276 + ',' + anInt3277 + ',' + b + ',' + anInt3278 + ',' + anInt3279 + ')');
        }
    }
    
    public static void method1549(final byte b) {
        try {
            Class98_Sub46_Sub6.anIntArray5980 = null;
            if (b == -112) {
                Class98_Sub46_Sub6.aClass58_5975 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dh.C(" + b + ')');
        }
    }
    
    static final Class197 method1550(final int n, final int n2, final ha ha) {
        try {
            if (n2 != 18361) {
                method1550(59, 118, null);
            }
            final Class244 method2151 = Class114.method2151(n, true, ha, true);
            if (method2151 == null) {
                return null;
            }
            return method2151.aClass197_1858;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dh.B(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class98_Sub46_Sub6(final Class35 aClass35_5971, final Class246_Sub5 class246_Sub5) {
        try {
            this.aClass35_5971 = aClass35_5971;
            this.aClass66_5973 = this.aClass35_5971.method331((byte)93);
            this.method1547(-102);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dh.<init>(" + ((aClass35_5971 != null) ? "{...}" : "null") + ',' + ((class246_Sub5 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub6.aClass58_5975 = new IncomingOpcode(60, 4);
        Class98_Sub46_Sub6.anIntArray5980 = new int[3];
    }
}
