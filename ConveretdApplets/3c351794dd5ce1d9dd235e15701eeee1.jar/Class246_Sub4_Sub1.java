// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub4_Sub1 extends Class246_Sub4
{
    static IncomingOpcode aClass58_6166;
    short aShort6167;
    String aString6168;
    static Class258 aClass258_6169;
    static Class79 aClass79_6170;
    static String[] aStringArray6171;
    int anInt6172;
    
    public static void method3102(final byte b) {
        try {
            Class246_Sub4_Sub1.aClass58_6166 = null;
            Class246_Sub4_Sub1.aClass79_6170 = null;
            Class246_Sub4_Sub1.aClass258_6169 = null;
            Class246_Sub4_Sub1.aStringArray6171 = null;
            if (b != -62) {
                Class246_Sub4_Sub1.aClass58_6166 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ii.C(" + b + ')');
        }
    }
    
    static final void method3103(final byte b) {
        try {
            if (~Class64_Sub16.anInt3680 == 0xFFFFFFFA) {
                Class64_Sub16.anInt3680 = 6;
                if (b != -38) {
                    method3104(52);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ii.B(" + b + ')');
        }
    }
    
    static final void method3104(final int n) {
        try {
            Class248.aClass377_1894.method3994(-124);
            if (n == 5134) {
                Class366.aClass377_3114.method3994(n ^ 0xFFFFEBCC);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ii.D(" + n + ')');
        }
    }
    
    static final void method3105(final byte b, final Class172[][][] array) {
        try {
            if (b >= -30) {
                Class246_Sub4_Sub1.aClass258_6169 = null;
            }
            for (int n = 0; ~n > ~array.length; ++n) {
                final Class172[][] array2 = array[n];
                for (int n2 = 0; array2.length > n2; ++n2) {
                    for (int n3 = 0; ~array2[n2].length < ~n3; ++n3) {
                        final Class172 class172 = array2[n2][n3];
                        if (class172 != null) {
                            if (class172.aClass246_Sub3_Sub1_1332 instanceof Interface19) {
                                ((Interface19)class172.aClass246_Sub3_Sub1_1332).method61((byte)(-96));
                            }
                            if (class172.aClass246_Sub3_Sub5_1334 instanceof Interface19) {
                                ((Interface19)class172.aClass246_Sub3_Sub5_1334).method61((byte)(-96));
                            }
                            if (class172.aClass246_Sub3_Sub5_1326 instanceof Interface19) {
                                ((Interface19)class172.aClass246_Sub3_Sub5_1326).method61((byte)(-96));
                            }
                            if (class172.aClass246_Sub3_Sub3_1324 instanceof Interface19) {
                                ((Interface19)class172.aClass246_Sub3_Sub3_1324).method61((byte)(-96));
                            }
                            if (class172.aClass246_Sub3_Sub3_1333 instanceof Interface19) {
                                ((Interface19)class172.aClass246_Sub3_Sub3_1333).method61((byte)(-96));
                            }
                            for (Class154 class173 = class172.aClass154_1325; class173 != null; class173 = class173.aClass154_1233) {
                                final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232 = class173.aClass246_Sub3_Sub4_1232;
                                if (aClass246_Sub3_Sub4_1232 instanceof Interface19) {
                                    ((Interface19)aClass246_Sub3_Sub4_1232).method61((byte)(-96));
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ii.A(" + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class246_Sub4_Sub1(final String aString6168, final int n) {
        try {
            this.anInt6172 = (int)(Class343.method3819(-47) / 1000L);
            this.aString6168 = aString6168;
            this.aShort6167 = (short)n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ii.<init>(" + ((aString6168 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class246_Sub4_Sub1.aClass58_6166 = new IncomingOpcode(98, -2);
        Class246_Sub4_Sub1.aClass258_6169 = new Class258();
        Class246_Sub4_Sub1.aClass79_6170 = new Class79(4);
        Class246_Sub4_Sub1.aStringArray6171 = new String[100];
    }
}
