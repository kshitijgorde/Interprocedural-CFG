// 
// Decompiled by Procyon v0.5.30
// 

final class Class372
{
    int anInt3145;
    int anInt3146;
    static IncomingOpcode aClass58_3147;
    int anInt3148;
    static int[][] anIntArrayArray3149;
    static int anInt3150;
    static float aFloat3151;
    static boolean aBoolean3152;
    static short[] aShortArray3153;
    
    static final void method3957(final ha ha, final boolean b, final Class293 class293) {
        try {
            if (b) {
                if (Class98_Sub46_Sub19.aClass205_6068.method2710(class293.anInt2304, class293.anInt2302, ha, class293.aBoolean2262 ? Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 : null, class293.anInt2349, class293.anInt2305, 0xFF000000 | class293.anInt2355, 24056) == null) {
                    IOException_Sub1.aClass148_30.method2419(new Class98_Sub12(class293.anInt2302, class293.anInt2349, class293.anInt2304, class293.anInt2355 | 0xFF000000, class293.anInt2305, class293.aBoolean2262), -20911);
                    Class341.method3812(1, class293);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wj.C(" + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + ((class293 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3958(final int n) {
        try {
            if (n != -12477) {
                method3958(4);
            }
            Class372.aShortArray3153 = null;
            Class372.aClass58_3147 = null;
            Class372.anIntArrayArray3149 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wj.B(" + n + ')');
        }
    }
    
    static final void method3959(final int n, final int n2, final Class98_Sub31_Sub2 aClass98_Sub31_Sub2_965, final Class207 class207, final int n3, final int n4, final boolean b) {
        try {
            s_Sub1.method3434(class207, b, n3, n2, n4, -16523);
            Class116.aClass98_Sub31_Sub2_965 = aClass98_Sub31_Sub2_965;
            if (n != 256) {
                Class372.anInt3150 = 3;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wj.A(" + n + ',' + n2 + ',' + ((aClass98_Sub31_Sub2_965 != null) ? "{...}" : "null") + ',' + ((class207 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    static {
        Class372.aClass58_3147 = new IncomingOpcode(51, 3);
        Class372.aBoolean3152 = false;
        Class372.aShortArray3153 = new short[256];
        Class372.anInt3150 = 0;
    }
}
