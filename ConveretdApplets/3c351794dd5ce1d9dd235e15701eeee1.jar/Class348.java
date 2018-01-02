// 
// Decompiled by Procyon v0.5.30
// 

final class Class348
{
    int anInt2909;
    int anInt2910;
    static int anInt2911;
    static IncomingOpcode aClass58_2912;
    int anInt2913;
    static boolean aBoolean2914;
    int anInt2915;
    
    static final void method3836(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2, final byte b3) {
        try {
            if (n2 < n3) {
                final int n5 = (n2 + n3) / 2;
                int n6 = n2;
                final Class53_Sub1 class53_Sub1 = Class98_Sub28_Sub1.aClass53_Sub1Array5805[n5];
                Class98_Sub28_Sub1.aClass53_Sub1Array5805[n5] = Class98_Sub28_Sub1.aClass53_Sub1Array5805[n3];
                Class98_Sub28_Sub1.aClass53_Sub1Array5805[n3] = class53_Sub1;
                for (int n7 = n2; ~n3 < ~n7; ++n7) {
                    if (~Class283.method3348(Class98_Sub28_Sub1.aClass53_Sub1Array5805[n7], 17, b, class53_Sub1, b2, n4, n) >= -1) {
                        final Class53_Sub1 class53_Sub2 = Class98_Sub28_Sub1.aClass53_Sub1Array5805[n7];
                        Class98_Sub28_Sub1.aClass53_Sub1Array5805[n7] = Class98_Sub28_Sub1.aClass53_Sub1Array5805[n6];
                        Class98_Sub28_Sub1.aClass53_Sub1Array5805[n6++] = class53_Sub2;
                    }
                }
                Class98_Sub28_Sub1.aClass53_Sub1Array5805[n3] = Class98_Sub28_Sub1.aClass53_Sub1Array5805[n6];
                Class98_Sub28_Sub1.aClass53_Sub1Array5805[n6] = class53_Sub1;
                method3836(n, n2, n6 - 1, n4, b, b2, (byte)93);
                method3836(n, 1 + n6, n3, n4, b, b2, (byte)53);
            }
            if (b3 <= 52) {
                Class348.aBoolean2914 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vea.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + b2 + ',' + b3 + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vea.toString()");
        }
    }
    
    public static void method3837(final int n) {
        try {
            Class348.aClass58_2912 = null;
            if (n != 2) {
                Class348.aBoolean2914 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vea.B(" + n + ')');
        }
    }
    
    Class348(final int anInt2909, final int anInt2910, final int anInt2911, final int anInt2912) {
        try {
            this.anInt2909 = anInt2909;
            this.anInt2910 = anInt2911;
            this.anInt2915 = anInt2912;
            this.anInt2913 = anInt2910;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vea.<init>(" + anInt2909 + ',' + anInt2910 + ',' + anInt2911 + ',' + anInt2912 + ')');
        }
    }
    
    static {
        Class348.aClass58_2912 = new IncomingOpcode(19, 2);
    }
}
