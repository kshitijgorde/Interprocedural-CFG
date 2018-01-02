// 
// Decompiled by Procyon v0.5.30
// 

final class Class169
{
    private Class162 aClass162_1293;
    static Class169 aClass169_1294;
    int anInt1295;
    private int anInt1296;
    static Class169 aClass169_1297;
    static Class169 aClass169_1298;
    static Class169 aClass169_1299;
    int anInt1300;
    static Class169 aClass169_1301;
    static Class169 aClass169_1302;
    static Class169 aClass169_1303;
    static int anInt1304;
    static int anInt1305;
    static int anInt1306;
    static int anInt1307;
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lf.toString()");
        }
    }
    
    static final void method2535(final int n, final int n2, final int n3, final int n4, final byte b) {
        try {
            if (b > 101) {
                for (Class98_Sub42 class98_Sub42 = (Class98_Sub42)Class98_Sub10_Sub37.aClass148_5748.method2418(32); class98_Sub42 != null; class98_Sub42 = (Class98_Sub42)Class98_Sub10_Sub37.aClass148_5748.method2417(107)) {
                    Class280.method3328(n3, 256, n2, n4, n, class98_Sub42);
                }
                for (Class98_Sub42 class98_Sub43 = (Class98_Sub42)Class358.aClass148_3032.method2418(32); class98_Sub43 != null; class98_Sub43 = (Class98_Sub42)Class358.aClass148_3032.method2417(123)) {
                    int anInt4227 = 1;
                    final Class294 method3039 = class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.method3039(1);
                    if (~class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt6385 == 0x0 || class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.aBoolean6359) {
                        anInt4227 = 0;
                    }
                    else if (~method3039.anInt2389 == ~class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt6385 || class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt6385 == method3039.anInt2361 || method3039.anInt2402 == class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt6385 || class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt6385 == method3039.anInt2357) {
                        anInt4227 = 2;
                    }
                    else if (method3039.anInt2368 == class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt6385 || method3039.anInt2394 == class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt6385 || class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt6385 == method3039.anInt2403 || ~method3039.anInt2377 == ~class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt6385) {
                        anInt4227 = 3;
                    }
                    if (~class98_Sub43.anInt4227 != ~anInt4227) {
                        final int method3040 = Class277.method3293(120, class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209);
                        Class141 class141 = class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.aClass141_6504;
                        if (class141.anIntArray1109 != null) {
                            class141 = class141.method2300(Class75.aClass140_584, (byte)29);
                        }
                        if (class141 == null || ~method3040 == 0x0) {
                            class98_Sub43.anInt4227 = anInt4227;
                            class98_Sub43.anInt4210 = -1;
                            class98_Sub43.aBoolean4215 = false;
                        }
                        else if (~class98_Sub43.anInt4210 != ~method3040 || !class98_Sub43.aBoolean4215 == class141.aBoolean1093) {
                            boolean b2 = false;
                            if (class98_Sub43.aClass98_Sub31_Sub5_4232 != null) {
                                final Class98_Sub42 class98_Sub44 = class98_Sub43;
                                class98_Sub44.anInt4236 -= 512;
                                if (~class98_Sub43.anInt4236 >= -1) {
                                    Class81.aClass98_Sub31_Sub3_619.method1374(class98_Sub43.aClass98_Sub31_Sub5_4232);
                                    class98_Sub43.aClass98_Sub31_Sub5_4232 = null;
                                    b2 = true;
                                }
                            }
                            else {
                                b2 = true;
                            }
                            if (b2) {
                                class98_Sub43.aClass98_Sub24_Sub1_4214 = null;
                                class98_Sub43.anInt4236 = class141.anInt1156;
                                class98_Sub43.aBoolean4215 = class141.aBoolean1093;
                                class98_Sub43.anInt4227 = anInt4227;
                                class98_Sub43.aClass98_Sub13_4213 = null;
                                class98_Sub43.anInt4210 = method3040;
                            }
                        }
                        else {
                            class98_Sub43.anInt4236 = class141.anInt1156;
                            class98_Sub43.anInt4227 = anInt4227;
                        }
                    }
                    class98_Sub43.anInt4229 = class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt5084;
                    class98_Sub43.anInt4224 = class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt5084 + (class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.method3034(0) << 1021778312);
                    class98_Sub43.anInt4225 = class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt5079;
                    class98_Sub43.anInt4216 = class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.anInt5079 + (class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4209.method3034(0) << -1760510648);
                    Class280.method3328(n3, 256, n2, n4, n, class98_Sub43);
                }
                for (Class98_Sub42 class98_Sub45 = (Class98_Sub42)Class98_Sub10_Sub14.aClass377_5612.method3998(107); class98_Sub45 != null; class98_Sub45 = (Class98_Sub42)Class98_Sub10_Sub14.aClass377_5612.method3995(-1)) {
                    int n5 = 1;
                    final Class294 method3041 = class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.method3039(1);
                    if (class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6385 == -1 || class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.aBoolean6359) {
                        n5 = 0;
                    }
                    else if (method3041.anInt2389 != class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6385 && ~method3041.anInt2361 != ~class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6385 && class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6385 != method3041.anInt2402 && class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6385 != method3041.anInt2357) {
                        if (class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6385 == method3041.anInt2368 || class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6385 == method3041.anInt2394 || ~method3041.anInt2403 == ~class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6385 || method3041.anInt2377 == class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6385) {
                            n5 = 3;
                        }
                    }
                    else {
                        n5 = 2;
                    }
                    if (n5 != class98_Sub45.anInt4227) {
                        final int method3042 = Class286.method3383(class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206, true);
                        if (~method3042 == ~class98_Sub45.anInt4210 && !class98_Sub45.aBoolean4215 == !class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.aBoolean6526) {
                            class98_Sub45.anInt4227 = n5;
                            class98_Sub45.anInt4236 = class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6514;
                        }
                        else {
                            boolean b3 = false;
                            if (class98_Sub45.aClass98_Sub31_Sub5_4232 == null) {
                                b3 = true;
                            }
                            else {
                                final Class98_Sub42 class98_Sub46 = class98_Sub45;
                                class98_Sub46.anInt4236 -= 512;
                                if (~class98_Sub45.anInt4236 >= -1) {
                                    Class81.aClass98_Sub31_Sub3_619.method1374(class98_Sub45.aClass98_Sub31_Sub5_4232);
                                    class98_Sub45.aClass98_Sub31_Sub5_4232 = null;
                                    b3 = true;
                                }
                            }
                            if (b3) {
                                class98_Sub45.anInt4210 = method3042;
                                class98_Sub45.aClass98_Sub24_Sub1_4214 = null;
                                class98_Sub45.aClass98_Sub13_4213 = null;
                                class98_Sub45.anInt4236 = class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6514;
                                class98_Sub45.anInt4227 = n5;
                                class98_Sub45.aBoolean4215 = class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.aBoolean6526;
                            }
                        }
                    }
                    class98_Sub45.anInt4229 = class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt5084;
                    class98_Sub45.anInt4224 = class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt5084 - -(class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.method3034(0) << 1257463912);
                    class98_Sub45.anInt4225 = class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt5079;
                    class98_Sub45.anInt4216 = class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt5079 + (class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub2_4206.method3034(0) << -1206921304);
                    Class280.method3328(n3, 256, n2, n4, n, class98_Sub45);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lf.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    public static void method2536(final int n) {
        try {
            Class169.aClass169_1294 = null;
            Class169.aClass169_1298 = null;
            Class169.aClass169_1302 = null;
            Class169.aClass169_1299 = null;
            if (n > -111) {
                Class169.aClass169_1297 = null;
            }
            Class169.aClass169_1301 = null;
            Class169.aClass169_1303 = null;
            Class169.aClass169_1297 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lf.C(" + n + ')');
        }
    }
    
    static final Class169 method2537(final int n, final byte b) {
        try {
            if (~n != -1) {
                if (n == 1) {
                    return Class169.aClass169_1297;
                }
                if (~n == 0xFFFFFFFD) {
                    return Class169.aClass169_1298;
                }
                if (n == 3) {
                    return Class169.aClass169_1299;
                }
                if (~n == 0xFFFFFFFB) {
                    return Class169.aClass169_1301;
                }
                if (n == 5) {
                    return Class169.aClass169_1302;
                }
                if (~n != 0xFFFFFFF9) {
                    if (b < 5) {
                        Class169.aClass169_1298 = null;
                    }
                    return null;
                }
                if (!client.aBoolean3553) {
                    return Class169.aClass169_1303;
                }
            }
            return Class169.aClass169_1294;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lf.A(" + n + ',' + b + ')');
        }
    }
    
    private Class169(final int anInt1300, final int anInt1301, final Class162 aClass162_1293) {
        try {
            this.aClass162_1293 = aClass162_1293;
            this.anInt1296 = anInt1301;
            this.anInt1300 = anInt1300;
            this.anInt1295 = this.aClass162_1293.anInt1263 * this.anInt1296;
            if (this.anInt1300 >= 16) {
                throw new RuntimeException();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lf.<init>(" + anInt1300 + ',' + anInt1301 + ',' + ((aClass162_1293 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class169.aClass169_1294 = new Class169(0, 3, Class162.aClass162_1270);
        Class169.aClass169_1297 = new Class169(1, 3, Class162.aClass162_1270);
        Class169.aClass169_1298 = new Class169(2, 4, Class162.aClass162_1266);
        Class169.aClass169_1299 = new Class169(3, 1, Class162.aClass162_1270);
        Class169.aClass169_1301 = new Class169(4, 2, Class162.aClass162_1270);
        Class169.aClass169_1302 = new Class169(5, 3, Class162.aClass162_1270);
        Class169.aClass169_1303 = new Class169(6, 4, Class162.aClass162_1270);
        Class169.anInt1304 = Class48_Sub2_Sub1.method474(16, (byte)31);
        Class169.anInt1305 = 2;
        Class169.anInt1307 = -1;
        Class169.anInt1306 = -1;
    }
}
