// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub5_Sub2 extends Class98_Sub5
{
    static boolean aBoolean5535;
    static int anInt5536;
    static double aDouble5537;
    
    static final String method968(final int n, final String[] array, final int n2, final int n3) {
        try {
            if (~n == -1) {
                return "";
            }
            if (n != 1) {
                final int i = n2 + n;
                int n4 = 0;
                for (int n5 = n2; ~n5 > ~i; ++n5) {
                    final String s = array[n5];
                    if (s == null) {
                        n4 += 4;
                    }
                    else {
                        n4 += s.length();
                    }
                }
                final StringBuffer sb = new StringBuffer(n4);
                for (int n6 = n2; i > n6; ++n6) {
                    final String s2 = array[n6];
                    if (s2 == null) {
                        sb.append("null");
                    }
                    else {
                        sb.append(s2);
                    }
                }
                if (n3 != -17120) {
                    method970(-109, null, -36, -75);
                }
                return sb.toString();
            }
            final String s3 = array[n2];
            if (s3 == null) {
                return "null";
            }
            return s3.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "im.M(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final boolean method969(final int n, final int n2, final int n3) {
        try {
            if (n3 < 70) {
                Class98_Sub5_Sub2.aDouble5537 = -0.3046933013113084;
            }
            return (0x84080 & n2) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "im.K(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method970(final int anInt5945, final Class293 aClass293_3986, final int anInt5946, final int n) {
        try {
            if (aClass293_3986 != null) {
                if (n != -6838) {
                    method968(67, null, -92, 84);
                }
                if (aClass293_3986.anObjectArray2257 != null) {
                    final Class98_Sub21 class98_Sub21 = new Class98_Sub21();
                    class98_Sub21.aClass293_3986 = aClass293_3986;
                    class98_Sub21.anObjectArray3981 = aClass293_3986.anObjectArray2257;
                    Class247.method3144(class98_Sub21);
                }
                Class98_Sub10_Sub9.aBoolean5585 = true;
                Class98_Sub46_Sub1.anInt5945 = anInt5945;
                Class98_Sub4.anInt3826 = anInt5946;
                Class187.anInt1450 = aClass293_3986.anInt2248;
                Class21_Sub2.anInt5387 = aClass293_3986.anInt2318;
                Class310.anInt2652 = aClass293_3986.anInt2300;
                Class376.anInt3173 = aClass293_3986.anInt2302;
                Class336.anInt2823 = aClass293_3986.anInt2309;
                Class341.method3812(n ^ 0xFFFFE54B, aClass293_3986);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "im.L(" + anInt5945 + ',' + ((aClass293_3986 != null) ? "{...}" : "null") + ',' + anInt5946 + ',' + n + ')');
        }
    }
    
    @Override
    final void method959(final float aFloat3832, final int n) {
        try {
            if (n < 12) {
                method970(-34, null, 53, 96);
            }
            super.aFloat3832 = aFloat3832;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "im.D(" + aFloat3832 + ',' + n + ')');
        }
    }
    
    Class98_Sub5_Sub2(final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
        super(n, n2, n3, n4, n5, n6);
    }
    
    @Override
    final void method955(final int anInt3834, final byte b, final int anInt3835, final int anInt3836) {
        try {
            if (b >= -120) {
                method970(-97, null, 25, 43);
            }
            super.anInt3833 = anInt3835;
            super.anInt3830 = anInt3836;
            super.anInt3834 = anInt3834;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "im.A(" + anInt3834 + ',' + b + ',' + anInt3835 + ',' + anInt3836 + ')');
        }
    }
    
    static {
        Class98_Sub5_Sub2.anInt5536 = -1;
    }
}
