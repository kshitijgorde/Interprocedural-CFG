// 
// Decompiled by Procyon v0.5.30
// 

final class Class308
{
    static Class105 aClass105_2576;
    private Class207 aClass207_2577;
    private Class207 aClass207_2578;
    private Class377 aClass377_2579;
    static int anInt2580;
    static IncomingOpcode aClass58_2581;
    static Class113 aClass113_2582;
    static Class98_Sub46_Sub9 aClass98_Sub46_Sub9_2583;
    static int anInt2584;
    private Class377 aClass377_2585;
    
    static final void method3608(int anInt3424, int anInt3425, final int n, int anInt3426) {
        try {
            anInt3424 <<= 3;
            anInt3426 <<= 3;
            anInt3425 <<= 3;
            Class98_Sub22_Sub2.aFloat5794 = anInt3424;
            if (~Class98_Sub46_Sub20_Sub2.anInt6319 == 0xFFFFFFFD) {
                Class246_Sub3_Sub4_Sub2.anInt6357 = anInt3426;
                Class186.anInt3424 = anInt3424;
                Class308.anInt2584 = anInt3425;
            }
            Class119_Sub4.aFloat4740 = anInt3426;
            Class42_Sub2.method388(true);
            Class64_Sub6.aBoolean3656 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sv.D(" + anInt3424 + ',' + anInt3425 + ',' + n + ',' + anInt3426 + ')');
        }
    }
    
    private final Class98_Sub24_Sub1 method3609(final int n, final int n2, final int n3, final int[] array) {
        try {
            final long n4 = ((((0xFFF8 & n2 << -42388412) | n2 >>> 885159052) ^ n3) | n2 << -1252543472) ^ 0x100000000L;
            final Class98_Sub24_Sub1 class98_Sub24_Sub1 = (Class98_Sub24_Sub1)this.aClass377_2585.method3990(n4, n ^ 0xFFFFAE88);
            if (class98_Sub24_Sub1 != null) {
                return class98_Sub24_Sub1;
            }
            if (array != null && ~array[0] >= -1) {
                return null;
            }
            Class98_Sub13 method1140 = (Class98_Sub13)this.aClass377_2579.method3990(n4, -1);
            if (method1140 == null) {
                method1140 = Class98_Sub13.method1140(this.aClass207_2577, n2, n3);
                if (method1140 == null) {
                    return null;
                }
                this.aClass377_2579.method3996(method1140, n4, n - 20856);
            }
            final Class98_Sub24_Sub1 method1141 = method1140.method1132(array);
            if (method1141 == null) {
                return null;
            }
            method1140.method942(98);
            if (n != 20855) {
                method3610(-68);
            }
            this.aClass377_2585.method3996(method1141, n4, n - 20856);
            return method1141;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sv.A(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3610(final int n) {
        try {
            Class308.aClass113_2582 = null;
            Class308.aClass105_2576 = null;
            Class308.aClass98_Sub46_Sub9_2583 = null;
            Class308.aClass58_2581 = null;
            if (n != -746085692) {
                method3610(-1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sv.C(" + n + ')');
        }
    }
    
    final Class98_Sub24_Sub1 method3611(final int n, final int n2, final int[] array) {
        try {
            if (n == ~this.aClass207_2578.method2752((byte)(-11))) {
                return this.method3612(14913, array, n2, 0);
            }
            if (~this.aClass207_2578.method2761(0, n2) == 0xFFFFFFFE) {
                return this.method3612(n + 14915, array, 0, n2);
            }
            throw new RuntimeException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sv.E(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final Class98_Sub24_Sub1 method3612(final int n, final int[] array, final int n2, final int n3) {
        try {
            final long n4 = (n2 ^ ((0xA0000FFF & n3) << -746085692 | n3 >>> -2070738644)) | n3 << 965219344;
            final Class98_Sub24_Sub1 class98_Sub24_Sub1 = (Class98_Sub24_Sub1)this.aClass377_2585.method3990(n4, -1);
            if (class98_Sub24_Sub1 != null) {
                return class98_Sub24_Sub1;
            }
            if (array != null && ~array[0] >= -1) {
                return null;
            }
            final Class37 method342 = Class37.method342(this.aClass207_2578, n3, n2);
            if (method342 == null) {
                return null;
            }
            if (n != 14913) {
                this.method3609(96, 36, 96, null);
            }
            final Class98_Sub24_Sub1 method343 = method342.method344();
            this.aClass377_2585.method3996(method343, n4, -1);
            if (array != null) {
                final int n5 = 0;
                array[n5] -= method343.aByteArray5799.length;
            }
            return method343;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sv.B(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final Class98_Sub24_Sub1 method3613(final int n, final boolean b, final int[] array) {
        try {
            if (~this.aClass207_2577.method2752((byte)(-11)) == 0xFFFFFFFE) {
                return this.method3609(20855, 0, n, array);
            }
            if (~this.aClass207_2577.method2761(0, n) == 0xFFFFFFFE) {
                return this.method3609(20855, n, 0, array);
            }
            if (!b) {
                return null;
            }
            throw new RuntimeException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sv.F(" + n + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class308(final Class207 aClass207_2578, final Class207 aClass207_2579) {
        this.aClass377_2579 = new Class377(256);
        this.aClass377_2585 = new Class377(256);
        try {
            this.aClass207_2578 = aClass207_2578;
            this.aClass207_2577 = aClass207_2579;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sv.<init>(" + ((aClass207_2578 != null) ? "{...}" : "null") + ',' + ((aClass207_2579 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class308.aClass105_2576 = new Class105("", 14);
        Class308.aClass58_2581 = new IncomingOpcode(96, 10);
        Class308.aClass113_2582 = new Class113(7, 2);
        Class308.aClass98_Sub46_Sub9_2583 = null;
    }
}
