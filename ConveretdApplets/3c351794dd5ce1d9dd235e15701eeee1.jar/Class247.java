import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.util.Date;
import java.util.Calendar;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class247
{
    private static int[] anIntArray1875;
    private static Class300 aClass300_1876;
    private static Class293 aClass293_1877;
    private static int[] anIntArray1878;
    private static Class293 aClass293_1879;
    static int anInt1880;
    private static int[][] anIntArrayArray1881;
    private static Calendar aCalendar1882;
    private static String[] aStringArray1883;
    private static int anInt1884;
    private static int anInt1885;
    private static String[] aStringArray1886;
    private static int[] anIntArray1887;
    private static int anInt1888;
    private static Class349[] aClass349Array1889;
    static Class79 aClass79_1890;
    private static int[] anIntArray1891;
    private static String[] aStringArray1892;
    private static int anInt1893;
    
    private static final void method3142(final int n) {
        final Class293 method2509 = Class159.method2509(n, -9820);
        if (method2509 != null) {
            final int n2 = n >>> 16;
            Class293[] array = Class64_Sub13.aClass293ArrayArray3674[n2];
            if (array == null) {
                final Class293[] array2 = Class159.aClass293ArrayArray1252[n2];
                final int length = array2.length;
                final Class293[][] aClass293ArrayArray3674 = Class64_Sub13.aClass293ArrayArray3674;
                final int n3 = n2;
                final Class293[] array3 = new Class293[length];
                aClass293ArrayArray3674[n3] = array3;
                array = array3;
                Class236.method2892(array2, 0, array, 0, array2.length);
            }
            int n4;
            for (n4 = 0; n4 < array.length && array[n4] != method2509; ++n4) {}
            if (n4 < array.length) {
                Class236.method2892(array, n4 + 1, array, n4, array.length - n4 - 1);
                array[array.length - 1] = method2509;
            }
        }
    }
    
    static final void method3143(final int n, final boolean b) {
    }
    
    static final void method3144(final Class98_Sub21 class98_Sub21) {
        method3150(class98_Sub21, 200000);
    }
    
    private static final void method3145(final int n) {
        final Class293 method2509 = Class159.method2509(n, -9820);
        if (method2509 != null) {
            final int n2 = n >>> 16;
            Class293[] array = Class64_Sub13.aClass293ArrayArray3674[n2];
            if (array == null) {
                final Class293[] array2 = Class159.aClass293ArrayArray1252[n2];
                final int length = array2.length;
                final Class293[][] aClass293ArrayArray3674 = Class64_Sub13.aClass293ArrayArray3674;
                final int n3 = n2;
                final Class293[] array3 = new Class293[length];
                aClass293ArrayArray3674[n3] = array3;
                array = array3;
                Class236.method2892(array2, 0, array, 0, array2.length);
            }
            int n4;
            for (n4 = 0; n4 < array.length && array[n4] != method2509; ++n4) {}
            if (n4 < array.length) {
                Class236.method2892(array, 0, array, 1, n4);
                array[0] = method2509;
            }
        }
    }
    
    private static final int method3146(final char c) {
        if (Class184.method2627(376, c)) {
            return 1;
        }
        return 0;
    }
    
    static final void method3147() {
    }
    
    private static final void method3148(int n, final boolean b) {
        if (n < 300) {
            if (n == 100) {
                Class247.anInt1885 -= 3;
                final int n2 = Class247.anIntArray1878[Class247.anInt1885];
                final int anInt2354 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final int anInt2355 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                if (anInt2354 == 0) {
                    throw new RuntimeException();
                }
                final Class293 method2509 = Class159.method2509(n2, -9820);
                if (method2509.aClass293Array2339 == null) {
                    method2509.aClass293Array2339 = new Class293[anInt2355 + 1];
                }
                if (method2509.aClass293Array2339.length <= anInt2355) {
                    final Class293[] aClass293Array2339 = new Class293[anInt2355 + 1];
                    for (int i = 0; i < method2509.aClass293Array2339.length; ++i) {
                        aClass293Array2339[i] = method2509.aClass293Array2339[i];
                    }
                    method2509.aClass293Array2339 = aClass293Array2339;
                }
                if (anInt2355 > 0 && method2509.aClass293Array2339[anInt2355 - 1] == null) {
                    throw new RuntimeException("Gap at:" + (anInt2355 - 1));
                }
                final Class293 class293 = new Class293();
                class293.anInt2354 = anInt2354;
                final Class293 class294 = class293;
                final Class293 class295 = class293;
                final int anInt2356 = method2509.anInt2248;
                class295.anInt2248 = anInt2356;
                class294.anInt2234 = anInt2356;
                class293.anInt2300 = anInt2355;
                method2509.aClass293Array2339[anInt2355] = class293;
                if (b) {
                    Class247.aClass293_1877 = class293;
                }
                else {
                    Class247.aClass293_1879 = class293;
                }
                Class341.method3812(1, method2509);
                return;
            }
            else if (n == 101) {
                final Class293 class296 = b ? Class247.aClass293_1877 : Class247.aClass293_1879;
                if (class296.anInt2300 != -1) {
                    final Class293 method2510 = Class159.method2509(class296.anInt2248, -9820);
                    method2510.aClass293Array2339[class296.anInt2300] = null;
                    Class341.method3812(1, method2510);
                    return;
                }
                if (b) {
                    throw new RuntimeException("Tried to .cc_delete static .active-component!");
                }
                throw new RuntimeException("Tried to cc_delete static active-component!");
            }
            else {
                if (n == 102) {
                    final Class293 method2511 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
                    method2511.aClass293Array2339 = null;
                    Class341.method3812(1, method2511);
                    return;
                }
                if (n == 200) {
                    Class247.anInt1885 -= 2;
                    final int n3 = Class247.anIntArray1878[Class247.anInt1885];
                    final int n4 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    final Class293 method2512 = Class246_Sub9.method3139((byte)72, n3, n4);
                    if (method2512 != null && n4 != -1) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 1;
                        if (b) {
                            Class247.aClass293_1877 = method2512;
                        }
                        else {
                            Class247.aClass293_1879 = method2512;
                        }
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                else if (n == 201) {
                    final Class293 method2513 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
                    if (method2513 != null) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 1;
                        if (b) {
                            Class247.aClass293_1877 = method2513;
                        }
                        else {
                            Class247.aClass293_1879 = method2513;
                        }
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                else {
                    if (n == 202) {
                        method3142(Class247.anIntArray1878[--Class247.anInt1885]);
                        return;
                    }
                    if (n == 203) {
                        method3145(Class247.anIntArray1878[--Class247.anInt1885]);
                        return;
                    }
                }
            }
        }
        else if (n < 500) {
            if (n == 403) {
                Class247.anInt1885 -= 2;
                final int n5 = Class247.anIntArray1878[Class247.anInt1885];
                final int n6 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 != null) {
                    for (int j = 0; j < Class304.anIntArray2538.length; ++j) {
                        if (Class304.anIntArray2538[j] == n5) {
                            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518.method3631(12, j, Class149.aClass83_1205, n6);
                            return;
                        }
                    }
                    for (int k = 0; k < Class49.anIntArray414.length; ++k) {
                        if (Class49.anIntArray414[k] == n5) {
                            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518.method3631(12, k, Class149.aClass83_1205, n6);
                            break;
                        }
                    }
                }
                return;
            }
            else if (n == 404) {
                Class247.anInt1885 -= 2;
                final int n7 = Class247.anIntArray1878[Class247.anInt1885];
                final int n8 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 != null) {
                    Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518.method3632(n8, n7, -9);
                }
                return;
            }
            else if (n == 410) {
                final boolean b2 = Class247.anIntArray1878[--Class247.anInt1885] != 0;
                if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 != null) {
                    Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518.method3635(b2, false);
                }
                return;
            }
            else if (n == 411) {
                Class247.anInt1885 -= 2;
                final int n9 = Class247.anIntArray1878[Class247.anInt1885];
                final int n10 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 != null) {
                    Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518.method3634(n10, n9, Class98_Sub46_Sub19.aClass205_6068, 1073741824);
                }
                return;
            }
        }
        else if ((n >= 1000 && n < 1100) || (n >= 2000 && n < 2100)) {
            Class293 method2514;
            if (n >= 2000) {
                n -= 1000;
                method2514 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
            }
            else {
                method2514 = (b ? Class247.aClass293_1877 : Class247.aClass293_1879);
            }
            if (n == 1000) {
                Class247.anInt1885 -= 4;
                method2514.anInt2283 = Class247.anIntArray1878[Class247.anInt1885];
                method2514.anInt2229 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                int n11 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                if (n11 < 0) {
                    n11 = 0;
                }
                else if (n11 > 5) {
                    n11 = 5;
                }
                int n12 = Class247.anIntArray1878[Class247.anInt1885 + 3];
                if (n12 < 0) {
                    n12 = 0;
                }
                else if (n12 > 5) {
                    n12 = 5;
                }
                method2514.aByte2240 = (byte)n11;
                method2514.aByte2245 = (byte)n12;
                Class341.method3812(1, method2514);
                Class98_Sub45.method1519(method2514, true);
                if (method2514.anInt2300 == -1) {
                    Class224_Sub2_Sub1.method2838(11, method2514.anInt2248);
                }
                return;
            }
            if (n == 1001) {
                Class247.anInt1885 -= 4;
                method2514.anInt2235 = Class247.anIntArray1878[Class247.anInt1885];
                method2514.anInt2242 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                method2514.anInt2232 = 0;
                method2514.anInt2226 = 0;
                int n13 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                if (n13 < 0) {
                    n13 = 0;
                }
                else if (n13 > 4) {
                    n13 = 4;
                }
                int n14 = Class247.anIntArray1878[Class247.anInt1885 + 3];
                if (n14 < 0) {
                    n14 = 0;
                }
                else if (n14 > 4) {
                    n14 = 4;
                }
                method2514.aByte2243 = (byte)n13;
                method2514.aByte2207 = (byte)n14;
                Class341.method3812(1, method2514);
                Class98_Sub45.method1519(method2514, true);
                if (method2514.anInt2354 == 0) {
                    Class63.method549(method2514, false, (byte)118);
                }
                return;
            }
            if (n == 1003) {
                final boolean aBoolean2295 = Class247.anIntArray1878[--Class247.anInt1885] == 1;
                if (method2514.aBoolean2295 != aBoolean2295) {
                    method2514.aBoolean2295 = aBoolean2295;
                    Class341.method3812(1, method2514);
                }
                if (method2514.anInt2300 == -1) {
                    Class98_Sub10_Sub18.method1056((byte)97, method2514.anInt2248);
                }
                return;
            }
            if (n == 1004) {
                Class247.anInt1885 -= 2;
                method2514.anInt2321 = Class247.anIntArray1878[Class247.anInt1885];
                method2514.anInt2338 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                Class341.method3812(1, method2514);
                Class98_Sub45.method1519(method2514, true);
                if (method2514.anInt2354 == 0) {
                    Class63.method549(method2514, false, (byte)(-112));
                }
                return;
            }
            if (n == 1005) {
                method2514.aBoolean2286 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                return;
            }
        }
        else if ((n >= 1100 && n < 1200) || (n >= 2100 && n < 2200)) {
            Class293 method2515;
            if (n >= 2000) {
                n -= 1000;
                method2515 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
            }
            else {
                method2515 = (b ? Class247.aClass293_1877 : Class247.aClass293_1879);
            }
            if (n == 1100) {
                Class247.anInt1885 -= 2;
                method2515.anInt2246 = Class247.anIntArray1878[Class247.anInt1885];
                if (method2515.anInt2246 > method2515.anInt2290 - method2515.anInt2311) {
                    method2515.anInt2246 = method2515.anInt2290 - method2515.anInt2311;
                }
                if (method2515.anInt2246 < 0) {
                    method2515.anInt2246 = 0;
                }
                method2515.anInt2213 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (method2515.anInt2213 > method2515.anInt2228 - method2515.anInt2258) {
                    method2515.anInt2213 = method2515.anInt2228 - method2515.anInt2258;
                }
                if (method2515.anInt2213 < 0) {
                    method2515.anInt2213 = 0;
                }
                Class341.method3812(1, method2515);
                if (method2515.anInt2300 == -1) {
                    Class21_Sub4.method279(16953, method2515.anInt2248);
                }
                return;
            }
            if (n == 1101) {
                method2515.anInt2236 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                if (method2515.anInt2300 == -1) {
                    Class98_Sub16.method1147(124, method2515.anInt2248);
                }
                return;
            }
            if (n == 1102) {
                method2515.aBoolean2263 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1103) {
                method2515.anInt2285 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1104) {
                method2515.anInt2293 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1105) {
                final int anInt2357 = Class247.anIntArray1878[--Class247.anInt1885];
                if (method2515.anInt2237 != anInt2357) {
                    method2515.anInt2237 = anInt2357;
                    Class341.method3812(1, method2515);
                }
                if (method2515.anInt2300 == -1) {
                    Class77.method778(44, method2515.anInt2248);
                }
                return;
            }
            if (n == 1106) {
                method2515.anInt2255 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1107) {
                method2515.aBoolean2288 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1108) {
                method2515.anInt2233 = 1;
                method2515.anInt2343 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                if (method2515.anInt2300 == -1) {
                    Class183.method2625(false, method2515.anInt2248);
                }
                return;
            }
            if (n == 1109) {
                Class247.anInt1885 -= 6;
                method2515.anInt2268 = Class247.anIntArray1878[Class247.anInt1885];
                method2515.anInt2273 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                method2515.anInt2310 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                method2515.anInt2218 = Class247.anIntArray1878[Class247.anInt1885 + 3];
                method2515.anInt2346 = Class247.anIntArray1878[Class247.anInt1885 + 4];
                method2515.anInt2251 = Class247.anIntArray1878[Class247.anInt1885 + 5];
                Class341.method3812(1, method2515);
                if (method2515.anInt2300 == -1) {
                    Class290.method3413(0, method2515.anInt2248);
                    Class185.method2631(10, method2515.anInt2248);
                }
                return;
            }
            if (n == 1110) {
                final int anInt2358 = Class247.anIntArray1878[--Class247.anInt1885];
                if (method2515.anInt2208 != anInt2358) {
                    method2515.anInt2208 = anInt2358;
                    method2515.anInt2303 = 0;
                    method2515.anInt2287 = 1;
                    method2515.anInt2312 = 0;
                    final Class97 class297 = (method2515.anInt2208 == -1) ? null : Class151_Sub7.aClass183_5001.method2623(method2515.anInt2208, 16383);
                    if (class297 != null) {
                        Class280.method3327(method2515.anInt2303, class297, (byte)74);
                    }
                    Class341.method3812(1, method2515);
                }
                if (method2515.anInt2300 == -1) {
                    Class119_Sub3.method2185(5, method2515.anInt2248);
                }
                return;
            }
            if (n == 1111) {
                method2515.aBoolean2265 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1112) {
                final String aString2225 = Class247.aStringArray1883[--Class247.anInt1884];
                if (!aString2225.equals(method2515.aString2225)) {
                    method2515.aString2225 = aString2225;
                    Class341.method3812(1, method2515);
                }
                if (method2515.anInt2300 == -1) {
                    Class286.method3380(3, method2515.anInt2248);
                }
                return;
            }
            if (n == 1113) {
                method2515.anInt2264 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                if (method2515.anInt2300 == -1) {
                    Class40.method361(method2515.anInt2248, 0);
                }
                return;
            }
            if (n == 1114) {
                Class247.anInt1885 -= 3;
                method2515.anInt2341 = Class247.anIntArray1878[Class247.anInt1885];
                method2515.anInt2296 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                method2515.anInt2244 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1115) {
                method2515.aBoolean2315 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1116) {
                method2515.anInt2304 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1117) {
                method2515.anInt2355 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1118) {
                method2515.aBoolean2327 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1119) {
                method2515.aBoolean2281 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1120) {
                Class247.anInt1885 -= 2;
                method2515.anInt2290 = Class247.anIntArray1878[Class247.anInt1885];
                method2515.anInt2228 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                Class341.method3812(1, method2515);
                if (method2515.anInt2354 == 0) {
                    Class63.method549(method2515, false, (byte)60);
                }
                return;
            }
            if (n == 1122) {
                method2515.aBoolean2279 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1123) {
                method2515.anInt2251 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                if (method2515.anInt2300 == -1) {
                    Class290.method3413(0, method2515.anInt2248);
                }
                return;
            }
            if (n == 1124) {
                method2515.aBoolean2256 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1125) {
                Class247.anInt1885 -= 2;
                method2515.anInt2336 = Class247.anIntArray1878[Class247.anInt1885];
                method2515.anInt2344 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1126) {
                method2515.anInt2350 = Class247.anIntArray1878[--Class247.anInt1885];
                Class341.method3812(1, method2515);
                return;
            }
            if (n == 1127) {
                Class247.anInt1885 -= 2;
                final int n15 = Class247.anIntArray1878[Class247.anInt1885];
                final int n16 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (n16 != Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n15).anInt1202) {
                    method2515.method3455(n15, n16, 16);
                    return;
                }
                method2515.method3459((byte)54, n15);
                return;
            }
            else if (n == 1128) {
                final int n17 = Class247.anIntArray1878[--Class247.anInt1885];
                final String s = Class247.aStringArray1883[--Class247.anInt1884];
                if (!Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n17).aString1203.equals(s)) {
                    method2515.method3458(s, 16, n17);
                    return;
                }
                method2515.method3459((byte)101, n17);
                return;
            }
            else if (n == 1129 || n == 1130) {
                final int anInt2359 = Class247.anIntArray1878[--Class247.anInt1885];
                if ((method2515.anInt2354 == 5 || n != 1129) && (method2515.anInt2354 == 4 || n != 1130)) {
                    if (method2515.anInt2211 != anInt2359) {
                        method2515.anInt2211 = anInt2359;
                        Class341.method3812(1, method2515);
                    }
                    if (method2515.anInt2300 == -1) {
                        PacketParser.method3969(121, method2515.anInt2248);
                    }
                }
                return;
            }
        }
        else if ((n >= 1200 && n < 1300) || (n >= 2200 && n < 2300)) {
            Class293 method2516;
            if (n >= 2000) {
                n -= 1000;
                method2516 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
            }
            else {
                method2516 = (b ? Class247.aClass293_1877 : Class247.aClass293_1879);
            }
            Class341.method3812(1, method2516);
            if (n == 1200 || n == 1205 || n == 1208 || n == 1209 || n == 1212 || n == 1213) {
                Class247.anInt1885 -= 2;
                final int anInt2360 = Class247.anIntArray1878[Class247.anInt1885];
                final int anInt2361 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (method2516.anInt2300 == -1) {
                    Class21_Sub2.method274((byte)83, method2516.anInt2248);
                    Class290.method3413(0, method2516.anInt2248);
                    Class185.method2631(10, method2516.anInt2248);
                }
                if (anInt2360 == -1) {
                    method2516.anInt2233 = 1;
                    method2516.anInt2343 = -1;
                    method2516.anInt2302 = -1;
                    return;
                }
                method2516.anInt2302 = anInt2360;
                method2516.anInt2349 = anInt2361;
                if (n == 1208 || n == 1209) {
                    method2516.aBoolean2262 = true;
                }
                else {
                    method2516.aBoolean2262 = false;
                }
                final Class297 method2517 = Class98_Sub46_Sub19.aClass205_6068.method2714(anInt2360, (byte)(-120));
                method2516.anInt2310 = method2517.anInt2416;
                method2516.anInt2218 = method2517.anInt2476;
                method2516.anInt2346 = method2517.anInt2441;
                method2516.anInt2268 = method2517.anInt2437;
                method2516.anInt2273 = method2517.anInt2447;
                method2516.anInt2251 = method2517.anInt2465;
                if (n == 1205 || n == 1209) {
                    method2516.anInt2305 = 0;
                }
                else if (n == 1212 || n == 1213) {
                    method2516.anInt2305 = 1;
                }
                else {
                    method2516.anInt2305 = 2;
                }
                if (method2516.anInt2232 > 0) {
                    method2516.anInt2251 = method2516.anInt2251 * 32 / method2516.anInt2232;
                    return;
                }
                if (method2516.anInt2235 > 0) {
                    method2516.anInt2251 = method2516.anInt2251 * 32 / method2516.anInt2235;
                }
                return;
            }
            else {
                if (n == 1201) {
                    method2516.anInt2233 = 2;
                    method2516.anInt2343 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (method2516.anInt2300 == -1) {
                        Class183.method2625(false, method2516.anInt2248);
                    }
                    return;
                }
                if (n == 1202) {
                    method2516.anInt2233 = 3;
                    method2516.anInt2343 = -1;
                    if (method2516.anInt2300 == -1) {
                        Class183.method2625(false, method2516.anInt2248);
                    }
                    return;
                }
                if (n == 1203) {
                    method2516.anInt2233 = 6;
                    method2516.anInt2343 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (method2516.anInt2300 == -1) {
                        Class183.method2625(false, method2516.anInt2248);
                    }
                    return;
                }
                if (n == 1204) {
                    method2516.anInt2233 = 5;
                    method2516.anInt2343 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (method2516.anInt2300 == -1) {
                        Class183.method2625(false, method2516.anInt2248);
                    }
                    return;
                }
                if (n == 1206) {
                    Class247.anInt1885 -= 4;
                    method2516.anInt2267 = Class247.anIntArray1878[Class247.anInt1885];
                    method2516.anInt2306 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    method2516.anInt2260 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                    method2516.anInt2334 = Class247.anIntArray1878[Class247.anInt1885 + 3];
                    Class341.method3812(1, method2516);
                    return;
                }
                if (n == 1207) {
                    Class247.anInt1885 -= 2;
                    method2516.anInt2216 = Class247.anIntArray1878[Class247.anInt1885];
                    method2516.anInt2261 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    Class341.method3812(1, method2516);
                    return;
                }
                if (n == 1210) {
                    Class247.anInt1885 -= 4;
                    method2516.anInt2343 = Class247.anIntArray1878[Class247.anInt1885];
                    method2516.anInt2210 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    if (Class247.anIntArray1878[Class247.anInt1885 + 2] == 1) {
                        method2516.anInt2233 = 9;
                    }
                    else {
                        method2516.anInt2233 = 8;
                    }
                    if (Class247.anIntArray1878[Class247.anInt1885 + 3] == 1) {
                        method2516.aBoolean2262 = true;
                    }
                    else {
                        method2516.aBoolean2262 = false;
                    }
                    if (method2516.anInt2300 == -1) {
                        Class183.method2625(false, method2516.anInt2248);
                    }
                    return;
                }
                if (n == 1211) {
                    method2516.anInt2233 = 5;
                    method2516.anInt2343 = za_Sub2.anInt6080;
                    method2516.anInt2210 = 0;
                    if (method2516.anInt2300 == -1) {
                        Class183.method2625(false, method2516.anInt2248);
                    }
                    return;
                }
            }
        }
        else if ((n >= 1300 && n < 1400) || (n >= 2300 && n < 2400)) {
            Class293 method2518;
            if (n >= 2000) {
                n -= 1000;
                method2518 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
            }
            else {
                method2518 = (b ? Class247.aClass293_1877 : Class247.aClass293_1879);
            }
            if (n == 1300) {
                final int n18 = Class247.anIntArray1878[--Class247.anInt1885] - 1;
                if (n18 < 0 || n18 > 9) {
                    --Class247.anInt1884;
                    return;
                }
                method2518.method3468(Class247.aStringArray1883[--Class247.anInt1884], n18, 1);
                return;
            }
            else if (n == 1301) {
                Class247.anInt1885 -= 2;
                final int n19 = Class247.anIntArray1878[Class247.anInt1885];
                final int n20 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (n19 == -1 && n20 == -1) {
                    method2518.aClass293_2219 = null;
                    return;
                }
                method2518.aClass293_2219 = Class246_Sub9.method3139((byte)72, n19, n20);
                return;
            }
            else if (n == 1302) {
                final int anInt2362 = Class247.anIntArray1878[--Class247.anInt1885];
                if (anInt2362 == Class369.anInt3129 || anInt2362 == Class253.anInt1929 || anInt2362 == Class36.anInt350) {
                    method2518.anInt2289 = anInt2362;
                }
                return;
            }
            else {
                if (n == 1303) {
                    method2518.anInt2308 = Class247.anIntArray1878[--Class247.anInt1885];
                    return;
                }
                if (n == 1304) {
                    method2518.anInt2353 = Class247.anIntArray1878[--Class247.anInt1885];
                    return;
                }
                if (n == 1305) {
                    method2518.aString2224 = Class247.aStringArray1883[--Class247.anInt1884];
                    return;
                }
                if (n == 1306) {
                    method2518.aString2214 = Class247.aStringArray1883[--Class247.anInt1884];
                    return;
                }
                if (n == 1307) {
                    method2518.aStringArray2351 = null;
                    return;
                }
                if (n == 1308) {
                    method2518.anInt2318 = Class247.anIntArray1878[--Class247.anInt1885];
                    method2518.anInt2309 = Class247.anIntArray1878[--Class247.anInt1885];
                    return;
                }
                if (n == 1309) {
                    final int n21 = Class247.anIntArray1878[--Class247.anInt1885];
                    final int n22 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (n22 >= 1 && n22 <= 10) {
                        method2518.method3474(-17972, n22 - 1, n21);
                    }
                    return;
                }
                if (n == 1310) {
                    method2518.aString2333 = Class247.aStringArray1883[--Class247.anInt1884];
                    return;
                }
                if (n == 1311) {
                    method2518.anInt2254 = Class247.anIntArray1878[--Class247.anInt1885];
                    return;
                }
                if (n == 1312 || n == 1313) {
                    int n23;
                    int n24;
                    int n25;
                    if (n == 1312) {
                        Class247.anInt1885 -= 3;
                        n23 = Class247.anIntArray1878[Class247.anInt1885] - 1;
                        n24 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                        n25 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                        if (n23 < 0 || n23 > 9) {
                            throw new RuntimeException("IOR13121313");
                        }
                    }
                    else {
                        Class247.anInt1885 -= 2;
                        n23 = 10;
                        n24 = Class247.anIntArray1878[Class247.anInt1885];
                        n25 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    }
                    if (method2518.aByteArray2345 == null) {
                        if (n24 == 0) {
                            return;
                        }
                        method2518.aByteArray2345 = new byte[11];
                        method2518.aByteArray2331 = new byte[11];
                        method2518.anIntArray2275 = new int[11];
                    }
                    method2518.aByteArray2345[n23] = (byte)n24;
                    if (n24 != 0) {
                        method2518.aBoolean2222 = true;
                    }
                    else {
                        method2518.aBoolean2222 = false;
                        for (int l = 0; l < method2518.aByteArray2345.length; ++l) {
                            if (method2518.aByteArray2345[l] != 0) {
                                method2518.aBoolean2222 = true;
                                break;
                            }
                        }
                    }
                    method2518.aByteArray2331[n23] = (byte)n25;
                    return;
                }
                if (n == 1314) {
                    method2518.anInt2317 = Class247.anIntArray1878[--Class247.anInt1885];
                    return;
                }
            }
        }
        else if ((n >= 1400 && n < 1500) || (n >= 2400 && n < 2500)) {
            Class293 method2519;
            if (n >= 2000) {
                n -= 1000;
                method2519 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
            }
            else {
                method2519 = (b ? Class247.aClass293_1877 : Class247.aClass293_1879);
            }
            if (n == 1499) {
                method2519.method3465(-1);
                return;
            }
            String substring = Class247.aStringArray1883[--Class247.anInt1884];
            int[] anIntArray2342 = null;
            if (substring.length() > 0 && substring.charAt(substring.length() - 1) == 'Y') {
                int n26 = Class247.anIntArray1878[--Class247.anInt1885];
                if (n26 > 0) {
                    anIntArray2342 = new int[n26];
                    while (n26-- > 0) {
                        anIntArray2342[n26] = Class247.anIntArray1878[--Class247.anInt1885];
                    }
                }
                substring = substring.substring(0, substring.length() - 1);
            }
            Object[] array = new Object[substring.length() + 1];
            for (int n27 = array.length - 1; n27 >= 1; --n27) {
                if (substring.charAt(n27 - 1) == 's') {
                    array[n27] = Class247.aStringArray1883[--Class247.anInt1884];
                }
                else {
                    array[n27] = new Integer(Class247.anIntArray1878[--Class247.anInt1885]);
                }
            }
            final int n28 = Class247.anIntArray1878[--Class247.anInt1885];
            if (n28 != -1) {
                array[0] = new Integer(n28);
            }
            else {
                array = null;
            }
            if (n == 1400) {
                method2519.anObjectArray2291 = array;
            }
            else if (n == 1401) {
                method2519.anObjectArray2230 = array;
            }
            else if (n == 1402) {
                method2519.anObjectArray2356 = array;
            }
            else if (n == 1403) {
                method2519.anObjectArray2227 = array;
            }
            else if (n == 1404) {
                method2519.anObjectArray2272 = array;
            }
            else if (n == 1405) {
                method2519.anObjectArray2316 = array;
            }
            else if (n == 1406) {
                method2519.anObjectArray2324 = array;
            }
            else if (n == 1407) {
                method2519.anObjectArray2269 = array;
                method2519.anIntArray2284 = anIntArray2342;
            }
            else if (n == 1408) {
                method2519.anObjectArray2270 = array;
            }
            else if (n == 1409) {
                method2519.anObjectArray2329 = array;
            }
            else if (n == 1410) {
                method2519.anObjectArray2313 = array;
            }
            else if (n == 1411) {
                method2519.anObjectArray2335 = array;
            }
            else if (n == 1412) {
                method2519.anObjectArray2314 = array;
            }
            else if (n == 1414) {
                method2519.anObjectArray2252 = array;
                method2519.anIntArray2249 = anIntArray2342;
            }
            else if (n == 1415) {
                method2519.anObjectArray2278 = array;
                method2519.anIntArray2271 = anIntArray2342;
            }
            else if (n == 1416) {
                method2519.anObjectArray2257 = array;
            }
            else if (n == 1417) {
                method2519.anObjectArray2277 = array;
            }
            else if (n == 1418) {
                method2519.anObjectArray2239 = array;
            }
            else if (n == 1419) {
                method2519.anObjectArray2274 = array;
            }
            else if (n == 1420) {
                method2519.anObjectArray2215 = array;
            }
            else if (n == 1421) {
                method2519.anObjectArray2292 = array;
            }
            else if (n == 1422) {
                method2519.anObjectArray2340 = array;
            }
            else if (n == 1423) {
                method2519.anObjectArray2330 = array;
            }
            else if (n == 1424) {
                method2519.anObjectArray2319 = array;
            }
            else if (n == 1425) {
                method2519.anObjectArray2294 = array;
            }
            else if (n == 1426) {
                method2519.anObjectArray2220 = array;
            }
            else if (n == 1427) {
                method2519.anObjectArray2266 = array;
            }
            else if (n == 1428) {
                method2519.anObjectArray2212 = array;
                method2519.anIntArray2297 = anIntArray2342;
            }
            else if (n == 1429) {
                method2519.anObjectArray2320 = array;
                method2519.anIntArray2342 = anIntArray2342;
            }
            else if (n == 1430) {
                method2519.anObjectArray2253 = array;
            }
            method2519.aBoolean2209 = true;
            return;
        }
        else if (n < 1600) {
            final Class293 class298 = b ? Class247.aClass293_1877 : Class247.aClass293_1879;
            if (n == 1500) {
                Class247.anIntArray1878[Class247.anInt1885++] = class298.anInt2347;
                return;
            }
            if (n == 1501) {
                Class247.anIntArray1878[Class247.anInt1885++] = class298.anInt2299;
                return;
            }
            if (n == 1502) {
                Class247.anIntArray1878[Class247.anInt1885++] = class298.anInt2311;
                return;
            }
            if (n == 1503) {
                Class247.anIntArray1878[Class247.anInt1885++] = class298.anInt2258;
                return;
            }
            if (n == 1504) {
                Class247.anIntArray1878[Class247.anInt1885++] = (class298.aBoolean2295 ? 1 : 0);
                return;
            }
            if (n == 1505) {
                Class247.anIntArray1878[Class247.anInt1885++] = class298.anInt2234;
                return;
            }
            if (n == 1506) {
                final Class293 method2520 = Class360.method3910(true, class298);
                Class247.anIntArray1878[Class247.anInt1885++] = ((method2520 == null) ? -1 : method2520.anInt2248);
                return;
            }
        }
        else if (n < 1700) {
            final Class293 class299 = b ? Class247.aClass293_1877 : Class247.aClass293_1879;
            if (n == 1600) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2246;
                return;
            }
            if (n == 1601) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2213;
                return;
            }
            if (n == 1602) {
                Class247.aStringArray1883[Class247.anInt1884++] = class299.aString2225;
                return;
            }
            if (n == 1603) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2290;
                return;
            }
            if (n == 1604) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2228;
                return;
            }
            if (n == 1605) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2251;
                return;
            }
            if (n == 1606) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2310;
                return;
            }
            if (n == 1607) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2346;
                return;
            }
            if (n == 1608) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2218;
                return;
            }
            if (n == 1609) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2285;
                return;
            }
            if (n == 1610) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2268;
                return;
            }
            if (n == 1611) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2273;
                return;
            }
            if (n == 1612) {
                Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2237;
                return;
            }
            if (n == 1613) {
                final int n29 = Class247.anIntArray1878[--Class247.anInt1885];
                final Class149 method2521 = Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n29);
                if (method2521.method2433(false)) {
                    Class247.aStringArray1883[Class247.anInt1884++] = class299.method3463(n29, 700, method2521.aString1203);
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = class299.method3472(22241, method2521.anInt1202, n29);
                return;
            }
            else {
                if (n == 1614) {
                    Class247.anIntArray1878[Class247.anInt1885++] = class299.anInt2255;
                    return;
                }
                if (n == 2614) {
                    Class247.anIntArray1878[Class247.anInt1885++] = ((class299.anInt2233 == 1) ? class299.anInt2343 : -1);
                    return;
                }
            }
        }
        else if (n < 1800) {
            final Class293 class300 = b ? Class247.aClass293_1877 : Class247.aClass293_1879;
            if (n == 1700) {
                Class247.anIntArray1878[Class247.anInt1885++] = class300.anInt2302;
                return;
            }
            if (n == 1701) {
                if (class300.anInt2302 != -1) {
                    Class247.anIntArray1878[Class247.anInt1885++] = class300.anInt2349;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
            else if (n == 1702) {
                Class247.anIntArray1878[Class247.anInt1885++] = class300.anInt2300;
                return;
            }
        }
        else if (n < 1900) {
            final Class293 class301 = b ? Class247.aClass293_1877 : Class247.aClass293_1879;
            if (n == 1800) {
                Class247.anIntArray1878[Class247.anInt1885++] = client.method116(class301).method1668(-1);
                return;
            }
            if (n == 1801) {
                int n30 = Class247.anIntArray1878[--Class247.anInt1885];
                --n30;
                if (class301.aStringArray2351 == null || n30 >= class301.aStringArray2351.length || class301.aStringArray2351[n30] == null) {
                    Class247.aStringArray1883[Class247.anInt1884++] = "";
                    return;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = class301.aStringArray2351[n30];
                return;
            }
            else if (n == 1802) {
                if (class301.aString2224 == null) {
                    Class247.aStringArray1883[Class247.anInt1884++] = "";
                    return;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = class301.aString2224;
                return;
            }
        }
        else if (n < 2000 || (n >= 2900 && n < 3000)) {
            Class293 method2522;
            if (n >= 2000) {
                method2522 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
                n -= 1000;
            }
            else {
                method2522 = (b ? Class247.aClass293_1877 : Class247.aClass293_1879);
            }
            if (Class247.anInt1893 >= 10) {
                throw new RuntimeException("C29xx-1");
            }
            if (n == 1927) {
                if (method2522.anObjectArray2266 != null) {
                    final Class98_Sub21 class98_Sub21 = new Class98_Sub21();
                    class98_Sub21.aClass293_3986 = method2522;
                    class98_Sub21.anObjectArray3981 = method2522.anObjectArray2266;
                    class98_Sub21.anInt3990 = Class247.anInt1893 + 1;
                    Class151_Sub3.aClass148_4977.method2419(class98_Sub21, -20911);
                }
                return;
            }
        }
        else if (n < 2600) {
            final Class293 method2523 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
            if (n == 2500) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2523.anInt2347;
                return;
            }
            if (n == 2501) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2523.anInt2299;
                return;
            }
            if (n == 2502) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2523.anInt2311;
                return;
            }
            if (n == 2503) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2523.anInt2258;
                return;
            }
            if (n == 2504) {
                Class247.anIntArray1878[Class247.anInt1885++] = (method2523.aBoolean2295 ? 1 : 0);
                return;
            }
            if (n == 2505) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2523.anInt2234;
                return;
            }
            if (n == 1506) {
                final Class293 method2524 = Class360.method3910(true, method2523);
                Class247.anIntArray1878[Class247.anInt1885++] = ((method2524 == null) ? -1 : method2524.anInt2248);
                return;
            }
        }
        else if (n < 2700) {
            final Class293 method2525 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
            if (n == 2600) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2246;
                return;
            }
            if (n == 2601) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2213;
                return;
            }
            if (n == 2602) {
                Class247.aStringArray1883[Class247.anInt1884++] = method2525.aString2225;
                return;
            }
            if (n == 2603) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2290;
                return;
            }
            if (n == 2604) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2228;
                return;
            }
            if (n == 2605) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2251;
                return;
            }
            if (n == 2606) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2310;
                return;
            }
            if (n == 2607) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2346;
                return;
            }
            if (n == 2608) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2218;
                return;
            }
            if (n == 2609) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2285;
                return;
            }
            if (n == 2610) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2268;
                return;
            }
            if (n == 2611) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2273;
                return;
            }
            if (n == 2612) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2237;
                return;
            }
            if (n == 2613) {
                Class247.anIntArray1878[Class247.anInt1885++] = method2525.anInt2255;
                return;
            }
            if (n == 2614) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((method2525.anInt2233 == 1) ? method2525.anInt2343 : -1);
                return;
            }
        }
        else if (n < 2800) {
            if (n == 2700) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820).anInt2302;
                return;
            }
            if (n == 2701) {
                final Class293 method2526 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
                if (method2526.anInt2302 != -1) {
                    Class247.anIntArray1878[Class247.anInt1885++] = method2526.anInt2349;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
            else if (n == 2702) {
                if (Class116.aClass377_964.method3990(Class247.anIntArray1878[--Class247.anInt1885], -1) != null) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 1;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
            else if (n == 2703) {
                final Class293 method2527 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
                if (method2527.aClass293Array2339 == null) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                int length = method2527.aClass293Array2339.length;
                for (int n31 = 0; n31 < method2527.aClass293Array2339.length; ++n31) {
                    if (method2527.aClass293Array2339[n31] == null) {
                        length = n31;
                        break;
                    }
                }
                Class247.anIntArray1878[Class247.anInt1885++] = length;
                return;
            }
            else if (n == 2704 || n == 2705) {
                Class247.anInt1885 -= 2;
                final int n32 = Class247.anIntArray1878[Class247.anInt1885];
                final int n33 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final Class98_Sub18 class98_Sub22 = (Class98_Sub18)Class116.aClass377_964.method3990(n32, -1);
                if (class98_Sub22 != null && class98_Sub22.anInt3945 == n33) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 1;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
        }
        else if (n < 2900) {
            final Class293 method2528 = Class159.method2509(Class247.anIntArray1878[--Class247.anInt1885], -9820);
            if (n == 2800) {
                Class247.anIntArray1878[Class247.anInt1885++] = client.method116(method2528).method1668(-1);
                return;
            }
            if (n == 2801) {
                int n34 = Class247.anIntArray1878[--Class247.anInt1885];
                --n34;
                if (method2528.aStringArray2351 == null || n34 >= method2528.aStringArray2351.length || method2528.aStringArray2351[n34] == null) {
                    Class247.aStringArray1883[Class247.anInt1884++] = "";
                    return;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = method2528.aStringArray2351[n34];
                return;
            }
            else if (n == 2802) {
                if (method2528.aString2224 == null) {
                    Class247.aStringArray1883[Class247.anInt1884++] = "";
                    return;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = method2528.aString2224;
                return;
            }
        }
        else if (n < 3200) {
            if (n == 3100) {
                Class84.method832(Class247.aStringArray1883[--Class247.anInt1884], (byte)108);
                return;
            }
            if (n == 3101) {
                Class247.anInt1885 -= 2;
                Class194.method2657(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660, (byte)114, Class247.anIntArray1878[Class247.anInt1885 + 1], Class247.anIntArray1878[Class247.anInt1885]);
                return;
            }
            if (n == 3103) {
                Class246_Sub3_Sub5_Sub1.method3092(-1, true);
                return;
            }
            if (n == 3104) {
                final String s2 = Class247.aStringArray1883[--Class247.anInt1884];
                int method2529 = 0;
                if (Class77_Sub1.method781((byte)53, s2)) {
                    method2529 = PacketSender.method3607(-126, s2);
                }
                final Class98_Sub11 method2530 = Class246_Sub3_Sub4.method3023(260, Class246_Sub3_Sub5.aClass171_6164, Class331.aClass117_2811);
                method2530.aClass98_Sub22_Sub1_3865.writeInt(1571862888, method2529);
                Class98_Sub10_Sub29.sendPacket(false, method2530);
                return;
            }
            if (n == 3105) {
                final String s3 = Class247.aStringArray1883[--Class247.anInt1884];
                final Class98_Sub11 method2531 = Class246_Sub3_Sub4.method3023(260, Class284_Sub1_Sub2.aClass171_6191, Class331.aClass117_2811);
                method2531.aClass98_Sub22_Sub1_3865.method1194(s3.length() + 1, -61);
                method2531.aClass98_Sub22_Sub1_3865.method1188(s3, (byte)113);
                Class98_Sub10_Sub29.sendPacket(false, method2531);
                return;
            }
            if (n == 3106) {
                final String s4 = Class247.aStringArray1883[--Class247.anInt1884];
                final Class98_Sub11 method2532 = Class246_Sub3_Sub4.method3023(260, Class199.aClass171_1533, Class331.aClass117_2811);
                method2532.aClass98_Sub22_Sub1_3865.method1194(s4.length() + 1, -58);
                method2532.aClass98_Sub22_Sub1_3865.method1188(s4, (byte)113);
                Class98_Sub10_Sub29.sendPacket(false, method2532);
                return;
            }
            if (n == 3107) {
                Class314.method3639(Class247.anIntArray1878[--Class247.anInt1885], Class247.aStringArray1883[--Class247.anInt1884], false);
                return;
            }
            if (n == 3108) {
                Class247.anInt1885 -= 3;
                Class146_Sub3.method2405(Class159.method2509(Class247.anIntArray1878[Class247.anInt1885 + 2], -9820), (byte)103, Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1]);
                return;
            }
            if (n == 3109) {
                Class247.anInt1885 -= 2;
                Class146_Sub3.method2405(b ? Class247.aClass293_1877 : Class247.aClass293_1879, (byte)118, Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1]);
                return;
            }
            if (n == 3110) {
                final int n35 = Class247.anIntArray1878[--Class247.anInt1885];
                final Class98_Sub11 method2533 = Class246_Sub3_Sub4.method3023(260, Class150.aClass171_1209, Class331.aClass117_2811);
                method2533.aClass98_Sub22_Sub1_3865.writeShort(n35, 1571862888);
                Class98_Sub10_Sub29.sendPacket(false, method2533);
                return;
            }
            if (n == 3111) {
                Class247.anInt1885 -= 2;
                final int n36 = Class247.anIntArray1878[Class247.anInt1885];
                final int n37 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final Class98_Sub18 class98_Sub23 = (Class98_Sub18)Class116.aClass377_964.method3990(n36, -1);
                if (class98_Sub23 != null) {
                    Class196.method2666(16398, true, class98_Sub23, class98_Sub23.anInt3945 != n37);
                }
                Class323.method3677(true, -128, n37, n36, 3);
                return;
            }
            if (n == 3112) {
                --Class247.anInt1885;
                final Class98_Sub18 class98_Sub24 = (Class98_Sub18)Class116.aClass377_964.method3990(Class247.anIntArray1878[Class247.anInt1885], -1);
                if (class98_Sub24 != null && class98_Sub24.anInt3947 == 3) {
                    Class196.method2666(16398, true, class98_Sub24, true);
                }
                return;
            }
            if (n == 3113) {
                Class98_Sub19.method1165((byte)36, Class247.aStringArray1883[--Class247.anInt1884]);
                return;
            }
            if (n == 3114) {
                Class247.anInt1885 -= 2;
                Class98_Sub45.method1521((byte)(-119), Class247.anIntArray1878[Class247.anInt1885], Class247.aStringArray1883[--Class247.anInt1884], Class247.anIntArray1878[Class247.anInt1885 + 1], "", "", "");
                return;
            }
            if (n == 3115) {
                Class247.anInt1885 -= 11;
                OutputStream_Sub1.method129(Class247.anIntArray1878[Class247.anInt1885 + 10], Class247.anIntArray1878[Class247.anInt1885 + 3], Class98_Sub46_Sub13_Sub1.method1595(123)[Class247.anIntArray1878[Class247.anInt1885]], Class247.anIntArray1878[Class247.anInt1885 + 4], Class247.anIntArray1878[Class247.anInt1885 + 2], false, Class247.anIntArray1878[Class247.anInt1885 + 8], Class247.anIntArray1878[Class247.anInt1885 + 9], Class247.anIntArray1878[Class247.anInt1885 + 6], Class247.anIntArray1878[Class247.anInt1885 + 7], Class247.anIntArray1878[Class247.anInt1885 + 5], Class331.method3723(256)[Class247.anIntArray1878[Class247.anInt1885 + 1]]);
                return;
            }
        }
        else if (n < 3300) {
            if (n == 3200) {
                Class247.anInt1885 -= 3;
                Class301.method3537(256, (byte)1, Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1], Class247.anIntArray1878[Class247.anInt1885 + 2], 255);
                return;
            }
            if (n == 3201) {
                Class246_Sub3_Sub1.method2994(Class247.anIntArray1878[--Class247.anInt1885], 255, (byte)(-83), 50);
                return;
            }
            if (n == 3202) {
                Class247.anInt1885 -= 2;
                Class228.method2861(Class247.anIntArray1878[Class247.anInt1885 + 1], 255, Class247.anIntArray1878[Class247.anInt1885], 18596);
                return;
            }
            if (n == 3203) {
                Class247.anInt1885 -= 4;
                Class301.method3537(256, (byte)1, Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1], Class247.anIntArray1878[Class247.anInt1885 + 2], Class247.anIntArray1878[Class247.anInt1885 + 3]);
                return;
            }
            if (n == 3204) {
                Class247.anInt1885 -= 3;
                Class246_Sub3_Sub1.method2994(Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1], (byte)(-83), Class247.anIntArray1878[Class247.anInt1885 + 2]);
                return;
            }
            if (n == 3205) {
                Class247.anInt1885 -= 3;
                Class228.method2861(Class247.anIntArray1878[Class247.anInt1885 + 1], Class247.anIntArray1878[Class247.anInt1885 + 2], Class247.anIntArray1878[Class247.anInt1885], 18596);
                return;
            }
            if (n == 3206) {
                Class247.anInt1885 -= 4;
                Class98_Sub10_Sub9.method1036(-1962608884, Class247.anIntArray1878[Class247.anInt1885 + 3], Class247.anIntArray1878[Class247.anInt1885 + 1], Class247.anIntArray1878[Class247.anInt1885], false, Class247.anIntArray1878[Class247.anInt1885 + 2], 256);
                return;
            }
            if (n == 3207) {
                Class247.anInt1885 -= 4;
                Class98_Sub10_Sub9.method1036(-1962608884, Class247.anIntArray1878[Class247.anInt1885 + 3], Class247.anIntArray1878[Class247.anInt1885 + 1], Class247.anIntArray1878[Class247.anInt1885], true, Class247.anIntArray1878[Class247.anInt1885 + 2], 256);
                return;
            }
            if (n == 3208) {
                Class247.anInt1885 -= 5;
                Class301.method3537(Class247.anIntArray1878[Class247.anInt1885 + 4], (byte)1, Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1], Class247.anIntArray1878[Class247.anInt1885 + 2], Class247.anIntArray1878[Class247.anInt1885 + 3]);
                return;
            }
            if (n == 3209) {
                Class247.anInt1885 -= 5;
                Class98_Sub10_Sub9.method1036(-1962608884, Class247.anIntArray1878[Class247.anInt1885 + 3], Class247.anIntArray1878[Class247.anInt1885 + 1], Class247.anIntArray1878[Class247.anInt1885], false, Class247.anIntArray1878[Class247.anInt1885 + 2], Class247.anIntArray1878[Class247.anInt1885 + 4]);
                return;
            }
        }
        else if (n < 3400) {
            if (n == 3300) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class215.anInt1614;
                return;
            }
            if (n == 3301) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class96.method925(Class247.anIntArray1878[Class247.anInt1885], -121, Class247.anIntArray1878[Class247.anInt1885 + 1], false);
                return;
            }
            if (n == 3302) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class124.method2212(false, (byte)(-96), Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1]);
                return;
            }
            if (n == 3303) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class249.method3161(Class247.anIntArray1878[Class247.anInt1885], -122, false, Class247.anIntArray1878[Class247.anInt1885 + 1]);
                return;
            }
            if (n == 3304) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub46_Sub14.aClass8_5378.method185(9, Class247.anIntArray1878[--Class247.anInt1885]).anInt6055;
                return;
            }
            if (n == 3305) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class64_Sub21.anIntArray3701[Class247.anIntArray1878[--Class247.anInt1885]];
                return;
            }
            if (n == 3306) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class256_Sub1.anIntArray5158[Class247.anIntArray1878[--Class247.anInt1885]];
                return;
            }
            if (n == 3307) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class52.anIntArray3493[Class247.anIntArray1878[--Class247.anInt1885]];
                return;
            }
            if (n == 3308) {
                Class247.anIntArray1878[Class247.anInt1885++] = (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 << 28) + ((Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 >> 9) + Class272.anInt2038 << 14) + ((Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 >> 9) + aa_Sub2.anInt3562);
                return;
            }
            if (n == 3309) {
                Class247.anIntArray1878[Class247.anInt1885++] = (Class247.anIntArray1878[--Class247.anInt1885] >> 14 & 0x3FFF);
                return;
            }
            if (n == 3310) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1878[--Class247.anInt1885] >> 28;
                return;
            }
            if (n == 3311) {
                Class247.anIntArray1878[Class247.anInt1885++] = (Class247.anIntArray1878[--Class247.anInt1885] & 0x3FFF);
                return;
            }
            if (n == 3312) {
                Class247.anIntArray1878[Class247.anInt1885++] = (Class79.aBoolean602 ? 1 : 0);
                return;
            }
            if (n == 3313) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class96.method925(Class247.anIntArray1878[Class247.anInt1885], 118, Class247.anIntArray1878[Class247.anInt1885 + 1], true);
                return;
            }
            if (n == 3314) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class124.method2212(true, (byte)(-96), Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1]);
                return;
            }
            if (n == 3315) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class249.method3161(Class247.anIntArray1878[Class247.anInt1885], -121, true, Class247.anIntArray1878[Class247.anInt1885 + 1]);
                return;
            }
            if (n == 3316) {
                if (Class282.anInt2125 >= 2) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class282.anInt2125;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
            else {
                if (n == 3317) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub6.anInt5569;
                    return;
                }
                if (n == 3318) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub46_Sub10.aClass354_6011.anInt3011;
                    return;
                }
                if (n == 3321) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class368.anInt3124;
                    return;
                }
                if (n == 3322) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class24.anInt255;
                    return;
                }
                if (n == 3323) {
                    if (Class47.anInt407 >= 5 && Class47.anInt407 <= 9) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 1;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                else if (n == 3324) {
                    if (Class47.anInt407 >= 5 && Class47.anInt407 <= 9) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class47.anInt407;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                else {
                    if (n == 3325) {
                        Class247.anIntArray1878[Class247.anInt1885++] = (Class64_Sub18.aBoolean3690 ? 1 : 0);
                        return;
                    }
                    if (n == 3326) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6519;
                        return;
                    }
                    if (n == 3327) {
                        Class247.anIntArray1878[Class247.anInt1885++] = ((Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 != null && Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518.aBoolean2681) ? 1 : 0);
                        return;
                    }
                    if (n == 3329) {
                        Class247.anIntArray1878[Class247.anInt1885++] = (Class178.aBoolean1401 ? 1 : 0);
                        return;
                    }
                    if (n == 3330) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class156_Sub1.method2498(Class247.anIntArray1878[--Class247.anInt1885], (byte)99, false);
                        return;
                    }
                    if (n == 3331) {
                        Class247.anInt1885 -= 2;
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub6.method978(false, false, Class247.anIntArray1878[Class247.anInt1885], true, Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 3332) {
                        Class247.anInt1885 -= 2;
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub6.method978(false, true, Class247.anIntArray1878[Class247.anInt1885], true, Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 3333) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class146_Sub2.anInt4855;
                        return;
                    }
                    if (n == 3335) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class374.anInt3159;
                        return;
                    }
                    if (n == 3336) {
                        Class247.anInt1885 -= 4;
                        Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1878[Class247.anInt1885] + (Class247.anIntArray1878[Class247.anInt1885 + 1] << 14) + (Class247.anIntArray1878[Class247.anInt1885 + 2] << 28) + Class247.anIntArray1878[Class247.anInt1885 + 3];
                        return;
                    }
                    if (n == 3337) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub15.anInt5619;
                        return;
                    }
                    if (n == 3338) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class278_Sub1.method3320(12);
                        return;
                    }
                    if (n == 3339) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 0;
                        return;
                    }
                    if (n == 3340) {
                        Class247.anIntArray1878[Class247.anInt1885++] = (Class4.aBoolean84 ? 1 : 0);
                        return;
                    }
                    if (n == 3341) {
                        Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub10_Sub30.aBoolean5712 ? 1 : 0);
                        return;
                    }
                    if (n == 3342) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class2.aClass299_73.method3514(61);
                        return;
                    }
                    if (n == 3343) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class2.aClass299_73.method3507((byte)82);
                        return;
                    }
                    if (n == 3344) {
                        Class247.aStringArray1883[Class247.anInt1884++] = Class152.method2477(29558);
                        return;
                    }
                    if (n == 3345) {
                        Class247.aStringArray1883[Class247.anInt1884++] = Class231.method2873(0);
                        return;
                    }
                    if (n == 3346) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class102.method1710(66);
                        return;
                    }
                    if (n == 3347) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class325.anInt2729;
                        return;
                    }
                    if (n == 3349) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass325_6399.method3698((byte)116) >> 3;
                        return;
                    }
                }
            }
        }
        else if (n < 3500) {
            if (n == 3400) {
                Class247.anInt1885 -= 2;
                Class247.aStringArray1883[Class247.anInt1884++] = Class98_Sub10_Sub16.aClass29_5620.method302(Class247.anIntArray1878[Class247.anInt1885], 1028602529).method3594(Class247.anIntArray1878[Class247.anInt1885 + 1], (byte)88);
                return;
            }
            if (n == 3408) {
                Class247.anInt1885 -= 4;
                final int n38 = Class247.anIntArray1878[Class247.anInt1885];
                final int n39 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final int n40 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                final int n41 = Class247.anIntArray1878[Class247.anInt1885 + 3];
                final Class306 method2534 = Class98_Sub10_Sub16.aClass29_5620.method302(n40, 1028602529);
                if (method2534.aChar2560 != n38 || method2534.aChar2567 != n39) {
                    throw new RuntimeException("C3408-1 " + n40 + "-" + n41);
                }
                if (n39 == 's') {
                    Class247.aStringArray1883[Class247.anInt1884++] = method2534.method3594(n41, (byte)23);
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = method2534.method3598(n41, -28629);
                return;
            }
            else if (n == 3409) {
                Class247.anInt1885 -= 3;
                final int n42 = Class247.anIntArray1878[Class247.anInt1885];
                final int n43 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final int n44 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                if (n43 == -1) {
                    throw new RuntimeException("C3409-2");
                }
                final Class306 method2535 = Class98_Sub10_Sub16.aClass29_5620.method302(n43, 1028602529);
                if (method2535.aChar2567 != n42) {
                    throw new RuntimeException("C3409-1");
                }
                Class247.anIntArray1878[Class247.anInt1885++] = (method2535.method3596(n44, (byte)104) ? 1 : 0);
                return;
            }
            else if (n == 3410) {
                final int n45 = Class247.anIntArray1878[--Class247.anInt1885];
                final String s5 = Class247.aStringArray1883[--Class247.anInt1884];
                if (n45 == -1) {
                    throw new RuntimeException("C3410-2");
                }
                final Class306 method2536 = Class98_Sub10_Sub16.aClass29_5620.method302(n45, 1028602529);
                if (method2536.aChar2567 != 's') {
                    throw new RuntimeException("C3410-1");
                }
                Class247.anIntArray1878[Class247.anInt1885++] = (method2536.method3602(s5, -16972) ? 1 : 0);
                return;
            }
            else if (n == 3411) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub16.aClass29_5620.method302(Class247.anIntArray1878[--Class247.anInt1885], 1028602529).aClass377_2558.method3999((byte)(-6));
                return;
            }
        }
        else if (n < 3700) {
            if (n == 3600) {
                if (Class98_Sub28.anInt4078 == 0) {
                    Class247.anIntArray1878[Class247.anInt1885++] = -2;
                    return;
                }
                if (Class98_Sub28.anInt4078 == 1) {
                    Class247.anIntArray1878[Class247.anInt1885++] = -1;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = Class314.anInt2692;
                return;
            }
            else if (n == 3601) {
                final int n46 = Class247.anIntArray1878[--Class247.anInt1885];
                if (Class98_Sub28.anInt4078 == 2 && n46 < Class314.anInt2692) {
                    Class247.aStringArray1883[Class247.anInt1884++] = Class98_Sub25.aStringArray4026[n46];
                    if (Class315.aStringArray3527[n46] != null) {
                        Class247.aStringArray1883[Class247.anInt1884++] = Class315.aStringArray3527[n46];
                    }
                    else {
                        Class247.aStringArray1883[Class247.anInt1884++] = "";
                    }
                    return;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                return;
            }
            else if (n == 3602) {
                final int n47 = Class247.anIntArray1878[--Class247.anInt1885];
                if (Class98_Sub28.anInt4078 == 2 && n47 < Class314.anInt2692) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub26.anIntArray4030[n47];
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
            else if (n == 3603) {
                final int n48 = Class247.anIntArray1878[--Class247.anInt1885];
                if (Class98_Sub28.anInt4078 == 2 && n48 < Class314.anInt2692) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class69.anIntArray3222[n48];
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
            else {
                if (n == 3604) {
                    Class373_Sub3.method3976(Class247.anIntArray1878[--Class247.anInt1885], (byte)89, Class247.aStringArray1883[--Class247.anInt1884]);
                    return;
                }
                if (n == 3605) {
                    Class176.method2580(4, Class247.aStringArray1883[--Class247.anInt1884]);
                    return;
                }
                if (n == 3606) {
                    Class118.method2170(54, Class247.aStringArray1883[--Class247.anInt1884]);
                    return;
                }
                if (n == 3607) {
                    Class66.method684(-104, Class247.aStringArray1883[--Class247.anInt1884], false);
                    return;
                }
                if (n == 3608) {
                    s_Sub1.method3430(Class247.aStringArray1883[--Class247.anInt1884], -23995);
                    return;
                }
                if (n == 3609) {
                    String substring2 = Class247.aStringArray1883[--Class247.anInt1884];
                    if (substring2.startsWith("<img=0>") || substring2.startsWith("<img=1>")) {
                        substring2 = substring2.substring(7);
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class256_Sub1.method3195(0, substring2) ? 1 : 0);
                    return;
                }
                if (n == 3610) {
                    final int n49 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (Class98_Sub28.anInt4078 == 2 && n49 < Class314.anInt2692) {
                        Class247.aStringArray1883[Class247.anInt1884++] = Class98_Sub10_Sub17.aStringArray5625[n49];
                        return;
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = "";
                    return;
                }
                else if (n == 3611) {
                    if (Class153.aString1229 != null) {
                        Class247.aStringArray1883[Class247.anInt1884++] = Class98_Sub10_Sub2.method1007(Class153.aString1229, 46);
                        return;
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = "";
                    return;
                }
                else if (n == 3612) {
                    if (Class153.aString1229 != null) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class32.anInt308;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                else if (n == 3613) {
                    final int n50 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (Class153.aString1229 != null && n50 < Class32.anInt308) {
                        Class247.aStringArray1883[Class247.anInt1884++] = Class374.aClass147Array3157[n50].aString1191;
                        return;
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = "";
                    return;
                }
                else if (n == 3614) {
                    final int n51 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (Class153.aString1229 != null && n51 < Class32.anInt308) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class374.aClass147Array3157[n51].anInt1188;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                else if (n == 3615) {
                    final int n52 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (Class153.aString1229 != null && n52 < Class32.anInt308) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class374.aClass147Array3157[n52].aByte1187;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                else {
                    if (n == 3616) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class232.aByte1742;
                        return;
                    }
                    if (n == 3617) {
                        Class76_Sub4.method756(-108, Class247.aStringArray1883[--Class247.anInt1884]);
                        return;
                    }
                    if (n == 3618) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class111.aByte947;
                        return;
                    }
                    if (n == 3619) {
                        Class345.method3824(Class247.aStringArray1883[--Class247.anInt1884], 2);
                        return;
                    }
                    if (n == 3620) {
                        Class339_Sub1.method3799(0);
                        return;
                    }
                    if (n == 3621) {
                        if (Class98_Sub28.anInt4078 == 0) {
                            Class247.anIntArray1878[Class247.anInt1885++] = -1;
                            return;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = Class248.anInt1897;
                        return;
                    }
                    else if (n == 3622) {
                        final int n53 = Class247.anIntArray1878[--Class247.anInt1885];
                        if (Class98_Sub28.anInt4078 != 0 && n53 < Class248.anInt1897) {
                            Class247.aStringArray1883[Class247.anInt1884++] = Class246_Sub4_Sub1.aStringArray6171[n53];
                            if (Class98_Sub45.aStringArray4255[n53] != null) {
                                Class247.aStringArray1883[Class247.anInt1884++] = Class98_Sub45.aStringArray4255[n53];
                            }
                            else {
                                Class247.aStringArray1883[Class247.anInt1884++] = "";
                            }
                            return;
                        }
                        Class247.aStringArray1883[Class247.anInt1884++] = "";
                        Class247.aStringArray1883[Class247.anInt1884++] = "";
                        return;
                    }
                    else {
                        if (n == 3623) {
                            String substring3 = Class247.aStringArray1883[--Class247.anInt1884];
                            if (substring3.startsWith("<img=0>") || substring3.startsWith("<img=1>")) {
                                substring3 = substring3.substring(7);
                            }
                            Class247.anIntArray1878[Class247.anInt1885++] = (Class14.method225(substring3, (byte)117) ? 1 : 0);
                            return;
                        }
                        if (n == 3624) {
                            final int n54 = Class247.anIntArray1878[--Class247.anInt1885];
                            if (Class374.aClass147Array3157 != null && n54 < Class32.anInt308 && Class374.aClass147Array3157[n54].aString1186.equalsIgnoreCase(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537)) {
                                Class247.anIntArray1878[Class247.anInt1885++] = 1;
                                return;
                            }
                            Class247.anIntArray1878[Class247.anInt1885++] = 0;
                            return;
                        }
                        else if (n == 3625) {
                            if (Class93_Sub3.aString5494 != null) {
                                Class247.aStringArray1883[Class247.anInt1884++] = Class93_Sub3.aString5494;
                                return;
                            }
                            Class247.aStringArray1883[Class247.anInt1884++] = "";
                            return;
                        }
                        else if (n == 3626) {
                            final int n55 = Class247.anIntArray1878[--Class247.anInt1885];
                            if (Class153.aString1229 != null && n55 < Class32.anInt308) {
                                Class247.aStringArray1883[Class247.anInt1884++] = Class374.aClass147Array3157[n55].aString1190;
                                return;
                            }
                            Class247.aStringArray1883[Class247.anInt1884++] = "";
                            return;
                        }
                        else if (n == 3627) {
                            final int n56 = Class247.anIntArray1878[--Class247.anInt1885];
                            if (Class98_Sub28.anInt4078 == 2 && n56 >= 0 && n56 < Class314.anInt2692) {
                                Class247.anIntArray1878[Class247.anInt1885++] = (aa_Sub3.aBooleanArray3575[n56] ? 1 : 0);
                                return;
                            }
                            Class247.anIntArray1878[Class247.anInt1885++] = 0;
                            return;
                        }
                        else {
                            if (n == 3628) {
                                String substring4 = Class247.aStringArray1883[--Class247.anInt1884];
                                if (substring4.startsWith("<img=0>") || substring4.startsWith("<img=1>")) {
                                    substring4 = substring4.substring(7);
                                }
                                Class247.anIntArray1878[Class247.anInt1885++] = Class111.method2094(substring4, -125);
                                return;
                            }
                            if (n == 3629) {
                                Class247.anIntArray1878[Class247.anInt1885++] = Class233.anInt1746;
                                return;
                            }
                            if (n == 3630) {
                                Class66.method684(-59, Class247.aStringArray1883[--Class247.anInt1884], true);
                                return;
                            }
                            if (n == 3631) {
                                Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub10_Sub38.aBooleanArray5759[Class247.anIntArray1878[--Class247.anInt1885]] ? 1 : 0);
                                return;
                            }
                            if (n == 3632) {
                                final int n57 = Class247.anIntArray1878[--Class247.anInt1885];
                                if (Class153.aString1229 != null && n57 < Class32.anInt308) {
                                    Class247.aStringArray1883[Class247.anInt1884++] = Class374.aClass147Array3157[n57].aString1186;
                                    return;
                                }
                                Class247.aStringArray1883[Class247.anInt1884++] = "";
                                return;
                            }
                            else if (n == 3633) {
                                final int n58 = Class247.anIntArray1878[--Class247.anInt1885];
                                if (Class98_Sub28.anInt4078 != 0 && n58 < Class248.anInt1897) {
                                    Class247.aStringArray1883[Class247.anInt1884++] = Class255.aStringArray3209[n58];
                                    return;
                                }
                                Class247.aStringArray1883[Class247.anInt1884++] = "";
                                return;
                            }
                        }
                    }
                }
            }
        }
        else if (n < 4000) {
            if (n == 3903) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].method1698(true);
                return;
            }
            if (n == 3904) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].anInt852;
                return;
            }
            if (n == 3905) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].anInt847;
                return;
            }
            if (n == 3906) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].anInt853;
                return;
            }
            if (n == 3907) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].anInt851;
                return;
            }
            if (n == 3908) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].anInt850;
                return;
            }
            if (n == 3910) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].method1700(7) == 0) ? 1 : 0);
                return;
            }
            if (n == 3911) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].method1700(7) == 2) ? 1 : 0);
                return;
            }
            if (n == 3912) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].method1700(7) == 5) ? 1 : 0);
                return;
            }
            if (n == 3913) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub10_Sub24.aClass101Array5666[Class247.anIntArray1878[--Class247.anInt1885]].method1700(7) == 1) ? 1 : 0);
                return;
            }
        }
        else if (n < 4100) {
            if (n == 4000) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1878[Class247.anInt1885] + Class247.anIntArray1878[Class247.anInt1885 + 1];
                return;
            }
            if (n == 4001) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1878[Class247.anInt1885] - Class247.anIntArray1878[Class247.anInt1885 + 1];
                return;
            }
            if (n == 4002) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1878[Class247.anInt1885] * Class247.anIntArray1878[Class247.anInt1885 + 1];
                return;
            }
            if (n == 4003) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1878[Class247.anInt1885] / Class247.anIntArray1878[Class247.anInt1885 + 1];
                return;
            }
            if (n == 4004) {
                Class247.anIntArray1878[Class247.anInt1885++] = (int)(Math.random() * Class247.anIntArray1878[--Class247.anInt1885]);
                return;
            }
            if (n == 4005) {
                Class247.anIntArray1878[Class247.anInt1885++] = (int)(Math.random() * (Class247.anIntArray1878[--Class247.anInt1885] + 1));
                return;
            }
            if (n == 4006) {
                Class247.anInt1885 -= 5;
                final int n59 = Class247.anIntArray1878[Class247.anInt1885];
                final int n60 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final int n61 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                Class247.anIntArray1878[Class247.anInt1885++] = n59 + (n60 - n59) * (Class247.anIntArray1878[Class247.anInt1885 + 4] - n61) / (Class247.anIntArray1878[Class247.anInt1885 + 3] - n61);
                return;
            }
            if (n == 4007) {
                Class247.anInt1885 -= 2;
                final long n62 = Class247.anIntArray1878[Class247.anInt1885];
                Class247.anIntArray1878[Class247.anInt1885++] = (int)(n62 + n62 * Class247.anIntArray1878[Class247.anInt1885 + 1] / 100L);
                return;
            }
            if (n == 4008) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = (Class247.anIntArray1878[Class247.anInt1885] | 1 << Class247.anIntArray1878[Class247.anInt1885 + 1]);
                return;
            }
            if (n == 4009) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = (Class247.anIntArray1878[Class247.anInt1885] & -1 - (1 << Class247.anIntArray1878[Class247.anInt1885 + 1]));
                return;
            }
            if (n == 4010) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = (((Class247.anIntArray1878[Class247.anInt1885] & 1 << Class247.anIntArray1878[Class247.anInt1885 + 1]) != 0x0) ? 1 : 0);
                return;
            }
            if (n == 4011) {
                Class247.anInt1885 -= 2;
                Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1878[Class247.anInt1885] % Class247.anIntArray1878[Class247.anInt1885 + 1];
                return;
            }
            if (n == 4012) {
                Class247.anInt1885 -= 2;
                final int n63 = Class247.anIntArray1878[Class247.anInt1885];
                final int n64 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (n63 == 0) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = (int)Math.pow(n63, n64);
                return;
            }
            else if (n == 4013) {
                Class247.anInt1885 -= 2;
                final int n65 = Class247.anIntArray1878[Class247.anInt1885];
                final int n66 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (n65 == 0) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                if (n66 == 0) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Integer.MAX_VALUE;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = (int)Math.pow(n65, 1.0 / n66);
                return;
            }
            else {
                if (n == 4014) {
                    Class247.anInt1885 -= 2;
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class247.anIntArray1878[Class247.anInt1885] & Class247.anIntArray1878[Class247.anInt1885 + 1]);
                    return;
                }
                if (n == 4015) {
                    Class247.anInt1885 -= 2;
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class247.anIntArray1878[Class247.anInt1885] | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                    return;
                }
                if (n == 4016) {
                    Class247.anInt1885 -= 2;
                    final int n67 = Class247.anIntArray1878[Class247.anInt1885];
                    final int n68 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    Class247.anIntArray1878[Class247.anInt1885++] = ((n67 < n68) ? n67 : n68);
                    return;
                }
                if (n == 4017) {
                    Class247.anInt1885 -= 2;
                    final int n69 = Class247.anIntArray1878[Class247.anInt1885];
                    final int n70 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    Class247.anIntArray1878[Class247.anInt1885++] = ((n69 > n70) ? n69 : n70);
                    return;
                }
                if (n == 4018) {
                    Class247.anInt1885 -= 3;
                    Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1878[Class247.anInt1885] * Class247.anIntArray1878[Class247.anInt1885 + 2] / Class247.anIntArray1878[Class247.anInt1885 + 1];
                    return;
                }
                if (n == 4019) {
                    Class247.anInt1885 -= 2;
                    final int n71 = Class247.anIntArray1878[Class247.anInt1885];
                    final int n72 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    if (n71 > 700 || n72 > 700) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 256;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = (int)(Math.pow(2.0, (Math.random() * (n72 + n71) - n71 + 800.0) / 100.0) + 0.5);
                    return;
                }
            }
        }
        else if (n < 4200) {
            if (n == 4100) {
                Class247.aStringArray1883[Class247.anInt1884++] = Class247.aStringArray1883[--Class247.anInt1884] + Class247.anIntArray1878[--Class247.anInt1885];
                return;
            }
            if (n == 4101) {
                Class247.anInt1884 -= 2;
                Class247.aStringArray1883[Class247.anInt1884++] = Class247.aStringArray1883[Class247.anInt1884] + Class247.aStringArray1883[Class247.anInt1884 + 1];
                return;
            }
            if (n == 4102) {
                Class247.aStringArray1883[Class247.anInt1884++] = Class247.aStringArray1883[--Class247.anInt1884] + Class44.method428(Class247.anIntArray1878[--Class247.anInt1885], false, true);
                return;
            }
            if (n == 4103) {
                Class247.aStringArray1883[Class247.anInt1884++] = Class247.aStringArray1883[--Class247.anInt1884].toLowerCase();
                return;
            }
            if (n == 4104) {
                Class247.aStringArray1883[Class247.anInt1884++] = method3149(Class247.anIntArray1878[--Class247.anInt1885]);
                return;
            }
            if (n == 4105) {
                Class247.anInt1884 -= 2;
                final String s6 = Class247.aStringArray1883[Class247.anInt1884];
                final String s7 = Class247.aStringArray1883[Class247.anInt1884 + 1];
                if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 != null && Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518.aBoolean2681) {
                    Class247.aStringArray1883[Class247.anInt1884++] = s7;
                    return;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = s6;
                return;
            }
            else {
                if (n == 4106) {
                    Class247.aStringArray1883[Class247.anInt1884++] = Integer.toString(Class247.anIntArray1878[--Class247.anInt1885]);
                    return;
                }
                if (n == 4107) {
                    Class247.anInt1884 -= 2;
                    Class247.anIntArray1878[Class247.anInt1885++] = Class336.method3772(Class247.aStringArray1883[Class247.anInt1884], Class247.aStringArray1883[Class247.anInt1884 + 1], Class374.anInt3159, 1166845806);
                    return;
                }
                if (n == 4108) {
                    final String s8 = Class247.aStringArray1883[--Class247.anInt1884];
                    Class247.anInt1885 -= 2;
                    Class247.anIntArray1878[Class247.anInt1885++] = Class109.method1733((byte)121, 0, Class247.anIntArray1878[Class247.anInt1885 + 1], Class36.aClass207_348).method2669(Class247.anIntArray1878[Class247.anInt1885], 0, s8, Class64_Sub18.aClass332Array3689);
                    return;
                }
                if (n == 4109) {
                    final String s9 = Class247.aStringArray1883[--Class247.anInt1884];
                    Class247.anInt1885 -= 2;
                    Class247.anIntArray1878[Class247.anInt1885++] = Class109.method1733((byte)119, 0, Class247.anIntArray1878[Class247.anInt1885 + 1], Class36.aClass207_348).method2670(Class247.anIntArray1878[Class247.anInt1885], s9, Class64_Sub18.aClass332Array3689, (byte)5);
                    return;
                }
                if (n == 4110) {
                    Class247.anInt1884 -= 2;
                    final String s10 = Class247.aStringArray1883[Class247.anInt1884];
                    final String s11 = Class247.aStringArray1883[Class247.anInt1884 + 1];
                    if (Class247.anIntArray1878[--Class247.anInt1885] == 1) {
                        Class247.aStringArray1883[Class247.anInt1884++] = s10;
                        return;
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = s11;
                    return;
                }
                else {
                    if (n == 4111) {
                        Class247.aStringArray1883[Class247.anInt1884++] = Class249.method3160(Class247.aStringArray1883[--Class247.anInt1884], 62);
                        return;
                    }
                    if (n == 4112) {
                        final String s12 = Class247.aStringArray1883[--Class247.anInt1884];
                        final int n73 = Class247.anIntArray1878[--Class247.anInt1885];
                        if (n73 == -1) {
                            throw new RuntimeException("null char");
                        }
                        Class247.aStringArray1883[Class247.anInt1884++] = s12 + (char)n73;
                        return;
                    }
                    else {
                        if (n == 4113) {
                            Class247.anIntArray1878[Class247.anInt1885++] = method3146((char)Class247.anIntArray1878[--Class247.anInt1885]);
                            return;
                        }
                        if (n == 4114) {
                            Class247.anIntArray1878[Class247.anInt1885++] = (Class114.method2147((char)Class247.anIntArray1878[--Class247.anInt1885], 118) ? 1 : 0);
                            return;
                        }
                        if (n == 4115) {
                            Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub46_Sub15.method1611((byte)124, (char)Class247.anIntArray1878[--Class247.anInt1885]) ? 1 : 0);
                            return;
                        }
                        if (n == 4116) {
                            Class247.anIntArray1878[Class247.anInt1885++] = (Class134_Sub1.method2245(18646, (char)Class247.anIntArray1878[--Class247.anInt1885]) ? 1 : 0);
                            return;
                        }
                        if (n == 4117) {
                            final String s13 = Class247.aStringArray1883[--Class247.anInt1884];
                            if (s13 != null) {
                                Class247.anIntArray1878[Class247.anInt1885++] = s13.length();
                                return;
                            }
                            Class247.anIntArray1878[Class247.anInt1885++] = 0;
                            return;
                        }
                        else {
                            if (n == 4118) {
                                final String s14 = Class247.aStringArray1883[--Class247.anInt1884];
                                Class247.anInt1885 -= 2;
                                Class247.aStringArray1883[Class247.anInt1884++] = s14.substring(Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1]);
                                return;
                            }
                            if (n == 4119) {
                                final String s15 = Class247.aStringArray1883[--Class247.anInt1884];
                                final StringBuffer sb = new StringBuffer(s15.length());
                                boolean b3 = false;
                                for (int n74 = 0; n74 < s15.length(); ++n74) {
                                    final char char1 = s15.charAt(n74);
                                    if (char1 == '<') {
                                        b3 = true;
                                    }
                                    else if (char1 == '>') {
                                        b3 = false;
                                    }
                                    else if (!b3) {
                                        sb.append(char1);
                                    }
                                }
                                Class247.aStringArray1883[Class247.anInt1884++] = sb.toString();
                                return;
                            }
                            if (n == 4120) {
                                final String s16 = Class247.aStringArray1883[--Class247.anInt1884];
                                Class247.anInt1885 -= 2;
                                Class247.anIntArray1878[Class247.anInt1885++] = s16.indexOf(Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1]);
                                return;
                            }
                            if (n == 4121) {
                                Class247.anInt1884 -= 2;
                                Class247.anIntArray1878[Class247.anInt1885++] = Class247.aStringArray1883[Class247.anInt1884].indexOf(Class247.aStringArray1883[Class247.anInt1884 + 1], Class247.anIntArray1878[--Class247.anInt1885]);
                                return;
                            }
                            if (n == 4122) {
                                Class247.anIntArray1878[Class247.anInt1885++] = Character.toLowerCase((char)Class247.anIntArray1878[--Class247.anInt1885]);
                                return;
                            }
                            if (n == 4123) {
                                Class247.anIntArray1878[Class247.anInt1885++] = Character.toUpperCase((char)Class247.anIntArray1878[--Class247.anInt1885]);
                                return;
                            }
                            if (n == 4124) {
                                Class247.aStringArray1883[Class247.anInt1884++] = Class39.method349(Class374.anInt3159, 0, 48, Class247.anIntArray1878[--Class247.anInt1885], Class247.anIntArray1878[--Class247.anInt1885] != 0);
                                return;
                            }
                            if (n == 4125) {
                                Class247.anIntArray1878[Class247.anInt1885++] = Class109.method1733((byte)115, 0, Class247.anIntArray1878[--Class247.anInt1885], Class36.aClass207_348).method2676((byte)82, Class64_Sub18.aClass332Array3689, Class247.aStringArray1883[--Class247.anInt1884]);
                                return;
                            }
                        }
                    }
                }
            }
        }
        else if (n < 4300) {
            if (n == 4200) {
                Class247.aStringArray1883[Class247.anInt1884++] = Class98_Sub46_Sub19.aClass205_6068.method2714(Class247.anIntArray1878[--Class247.anInt1885], (byte)(-126)).aString2450;
                return;
            }
            if (n == 4201) {
                Class247.anInt1885 -= 2;
                final int n75 = Class247.anIntArray1878[Class247.anInt1885];
                final int n76 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final Class297 method2537 = Class98_Sub46_Sub19.aClass205_6068.method2714(n75, (byte)(-119));
                if (n76 >= 1 && n76 <= 5 && method2537.aStringArray2446[n76 - 1] != null) {
                    Class247.aStringArray1883[Class247.anInt1884++] = method2537.aStringArray2446[n76 - 1];
                    return;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                return;
            }
            else if (n == 4202) {
                Class247.anInt1885 -= 2;
                final int n77 = Class247.anIntArray1878[Class247.anInt1885];
                final int n78 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final Class297 method2538 = Class98_Sub46_Sub19.aClass205_6068.method2714(n77, (byte)(-125));
                if (n78 >= 1 && n78 <= 5 && method2538.aStringArray2473[n78 - 1] != null) {
                    Class247.aStringArray1883[Class247.anInt1884++] = method2538.aStringArray2473[n78 - 1];
                    return;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                return;
            }
            else {
                if (n == 4203) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub46_Sub19.aClass205_6068.method2714(Class247.anIntArray1878[--Class247.anInt1885], (byte)(-120)).anInt2475;
                    return;
                }
                if (n == 4204) {
                    Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub46_Sub19.aClass205_6068.method2714(Class247.anIntArray1878[--Class247.anInt1885], (byte)(-117)).anInt2469 == 1) ? 1 : 0);
                    return;
                }
                if (n == 4205) {
                    final int n79 = Class247.anIntArray1878[--Class247.anInt1885];
                    final Class297 method2539 = Class98_Sub46_Sub19.aClass205_6068.method2714(n79, (byte)(-116));
                    if (method2539.anInt2414 == -1 && method2539.anInt2433 >= 0) {
                        Class247.anIntArray1878[Class247.anInt1885++] = method2539.anInt2433;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = n79;
                    return;
                }
                else if (n == 4206) {
                    final int n80 = Class247.anIntArray1878[--Class247.anInt1885];
                    final Class297 method2540 = Class98_Sub46_Sub19.aClass205_6068.method2714(n80, (byte)(-126));
                    if (method2540.anInt2414 >= 0 && method2540.anInt2433 >= 0) {
                        Class247.anIntArray1878[Class247.anInt1885++] = method2540.anInt2433;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = n80;
                    return;
                }
                else {
                    if (n == 4207) {
                        Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub46_Sub19.aClass205_6068.method2714(Class247.anIntArray1878[--Class247.anInt1885], (byte)(-124)).aBoolean2420 ? 1 : 0);
                        return;
                    }
                    if (n == 4208) {
                        Class247.anInt1885 -= 2;
                        final int n81 = Class247.anIntArray1878[Class247.anInt1885];
                        final int n82 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                        final Class149 method2541 = Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n82);
                        if (method2541.method2433(false)) {
                            Class247.aStringArray1883[Class247.anInt1884++] = Class98_Sub46_Sub19.aClass205_6068.method2714(n81, (byte)(-126)).method3495(method2541.aString1203, -1, n82);
                            return;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub46_Sub19.aClass205_6068.method2714(n81, (byte)(-123)).method3494(n82, (byte)(-86), method2541.anInt1202);
                        return;
                    }
                    else if (n == 4209) {
                        Class247.anInt1885 -= 2;
                        final int n83 = Class247.anIntArray1878[Class247.anInt1885];
                        final int n84 = Class247.anIntArray1878[Class247.anInt1885 + 1] - 1;
                        final Class297 method2542 = Class98_Sub46_Sub19.aClass205_6068.method2714(n83, (byte)(-116));
                        if (method2542.anInt2463 == n84) {
                            Class247.anIntArray1878[Class247.anInt1885++] = method2542.anInt2440;
                            return;
                        }
                        if (method2542.anInt2434 == n84) {
                            Class247.anIntArray1878[Class247.anInt1885++] = method2542.anInt2462;
                            return;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                        return;
                    }
                    else {
                        if (n == 4210) {
                            Class115.method2156(Class247.anIntArray1878[--Class247.anInt1885] == 1, Class247.aStringArray1883[--Class247.anInt1884], -97);
                            Class247.anIntArray1878[Class247.anInt1885++] = Class18.anInt214;
                            return;
                        }
                        if (n == 4211) {
                            if (Class64_Sub16.aShortArray3682 == null || Class85.anInt638 >= Class18.anInt214) {
                                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                return;
                            }
                            Class247.anIntArray1878[Class247.anInt1885++] = (Class64_Sub16.aShortArray3682[Class85.anInt638++] & 0xFFFF);
                            return;
                        }
                        else {
                            if (n == 4212) {
                                Class85.anInt638 = 0;
                                return;
                            }
                            if (n == 4213) {
                                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub46_Sub19.aClass205_6068.method2714(Class247.anIntArray1878[--Class247.anInt1885], (byte)(-117)).anInt2418;
                                return;
                            }
                            if (n == 4214) {
                                final String s17 = Class247.aStringArray1883[--Class247.anInt1884];
                                Class247.anInt1885 -= 3;
                                Class57.method519(Class247.anIntArray1878[Class247.anInt1885] == 1, s17, Class247.anIntArray1878[Class247.anInt1885 + 2], -1, Class247.anIntArray1878[Class247.anInt1885 + 1]);
                                Class247.anIntArray1878[Class247.anInt1885++] = Class18.anInt214;
                                return;
                            }
                            if (n == 4215) {
                                Class247.anInt1884 -= 2;
                                Class247.anInt1885 -= 2;
                                Class287.method3387(Class247.aStringArray1883[Class247.anInt1884], Class247.aStringArray1883[Class247.anInt1884 + 1], (byte)34, Class247.anIntArray1878[Class247.anInt1885] == 1, Class247.anIntArray1878[Class247.anInt1885 + 1]);
                                Class247.anIntArray1878[Class247.anInt1885++] = Class18.anInt214;
                                return;
                            }
                        }
                    }
                }
            }
        }
        else if (n < 4400) {
            if (n == 4300) {
                Class247.anInt1885 -= 2;
                final int n85 = Class247.anIntArray1878[Class247.anInt1885];
                final int n86 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final Class149 method2543 = Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n86);
                if (method2543.method2433(false)) {
                    Class247.aStringArray1883[Class247.anInt1884++] = Class4.aClass301_85.method3538(5, n85).method2298(n86, -105, method2543.aString1203);
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = Class4.aClass301_85.method3538(5, n85).method2305(n86, method2543.anInt1202, (byte)127);
                return;
            }
        }
        else if (n < 4500) {
            if (n == 4400) {
                Class247.anInt1885 -= 2;
                final int n87 = Class247.anIntArray1878[Class247.anInt1885];
                final int n88 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final Class149 method2544 = Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n88);
                if (method2544.method2433(false)) {
                    Class247.aStringArray1883[Class247.anInt1884++] = Class130.aClass302_1028.method3546(n87, (byte)119).method3864((byte)109, n88, method2544.aString1203);
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = Class130.aClass302_1028.method3546(n87, (byte)119).method3866(method2544.anInt1202, n88, 1);
                return;
            }
        }
        else if (n < 4600) {
            if (n == 4500) {
                Class247.anInt1885 -= 2;
                final int n89 = Class247.anIntArray1878[Class247.anInt1885];
                final int n90 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final Class149 method2545 = Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n90);
                if (method2545.method2433(false)) {
                    Class247.aStringArray1883[Class247.anInt1884++] = Class62.aClass264_487.method3224(26, n89).method1586(n90, (byte)(-19), method2545.aString1203);
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = Class62.aClass264_487.method3224(26, n89).method1585(n90, true, method2545.anInt1202);
                return;
            }
        }
        else if (n < 4700 && n == 4600) {
            final Class294 method2546 = Class370.aClass257_3136.method3199(false, Class247.anIntArray1878[--Class247.anInt1885]);
            if (method2546.anIntArray2395 != null && method2546.anIntArray2395.length > 0) {
                int n91 = 0;
                int n92 = method2546.anIntArray2386[0];
                for (int n93 = 1; n93 < method2546.anIntArray2395.length; ++n93) {
                    if (method2546.anIntArray2386[n93] > n92) {
                        n91 = n93;
                        n92 = method2546.anIntArray2386[n93];
                    }
                }
                Class247.anIntArray1878[Class247.anInt1885++] = method2546.anIntArray2395[n91];
                return;
            }
            Class247.anIntArray1878[Class247.anInt1885++] = method2546.anInt2396;
            return;
        }
        throw new IllegalStateException(String.valueOf(n));
    }
    
    private static final String method3149(final int n) {
        Class247.aCalendar1882.setTime(new Date((n + 11745L) * 86400000L));
        return String.valueOf(Class247.aCalendar1882.get(5)) + "-" + Class247.aStringArray1892[Class247.aCalendar1882.get(2)] + "-" + Class247.aCalendar1882.get(1);
    }
    
    private static final void method3150(final Class98_Sub21 class98_Sub21, final int n) {
        final Object[] anObjectArray3981 = class98_Sub21.anObjectArray3981;
        final Class98_Sub46_Sub4 method1601 = Class98_Sub46_Sub13_Sub2.method1601((int)anObjectArray3981[0], 100);
        if (method1601 != null) {
            Class247.anIntArray1875 = new int[method1601.anInt5958];
            int n2 = 0;
            Class247.aStringArray1886 = new String[method1601.anInt5964];
            int n3 = 0;
            for (int i = 1; i < anObjectArray3981.length; ++i) {
                if (anObjectArray3981[i] instanceof Integer) {
                    int n4 = (int)anObjectArray3981[i];
                    if (n4 == -2147483647) {
                        n4 = class98_Sub21.anInt3985;
                    }
                    if (n4 == -2147483646) {
                        n4 = class98_Sub21.anInt3979;
                    }
                    if (n4 == -2147483645) {
                        n4 = ((class98_Sub21.aClass293_3986 != null) ? class98_Sub21.aClass293_3986.anInt2248 : -1);
                    }
                    if (n4 == -2147483644) {
                        n4 = class98_Sub21.anInt3984;
                    }
                    if (n4 == -2147483643) {
                        n4 = ((class98_Sub21.aClass293_3986 != null) ? class98_Sub21.aClass293_3986.anInt2300 : -1);
                    }
                    if (n4 == -2147483642) {
                        n4 = ((class98_Sub21.aClass293_3982 != null) ? class98_Sub21.aClass293_3982.anInt2248 : -1);
                    }
                    if (n4 == -2147483641) {
                        n4 = ((class98_Sub21.aClass293_3982 != null) ? class98_Sub21.aClass293_3982.anInt2300 : -1);
                    }
                    if (n4 == -2147483640) {
                        n4 = class98_Sub21.anInt3977;
                    }
                    if (n4 == -2147483639) {
                        n4 = class98_Sub21.anInt3976;
                    }
                    Class247.anIntArray1875[n2++] = n4;
                }
                else if (anObjectArray3981[i] instanceof String) {
                    String aString3978 = (String)anObjectArray3981[i];
                    if (aString3978.equals("event_opbase")) {
                        aString3978 = class98_Sub21.aString3978;
                    }
                    Class247.aStringArray1886[n3++] = aString3978;
                }
            }
            Class247.anInt1893 = class98_Sub21.anInt3990;
            method3153(method1601, n);
        }
    }
    
    private static final void method3151(String s, final int n) {
        if (Class282.anInt2125 != 0 || ((!Class109.aBoolean933 || Class98_Sub10_Sub35.aBoolean5732) && !Class178.aBoolean1401)) {
            final String lowerCase = s.toLowerCase();
            int n2 = 0;
            if (lowerCase.startsWith(Class309.aClass309_2633.method3615(0, (byte)25))) {
                n2 = 0;
                s = s.substring(Class309.aClass309_2633.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2634.method3615(0, (byte)25))) {
                n2 = 1;
                s = s.substring(Class309.aClass309_2634.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2635.method3615(0, (byte)25))) {
                n2 = 2;
                s = s.substring(Class309.aClass309_2635.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2636.method3615(0, (byte)25))) {
                n2 = 3;
                s = s.substring(Class309.aClass309_2636.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2637.method3615(0, (byte)25))) {
                n2 = 4;
                s = s.substring(Class309.aClass309_2637.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2638.method3615(0, (byte)25))) {
                n2 = 5;
                s = s.substring(Class309.aClass309_2638.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2639.method3615(0, (byte)25))) {
                n2 = 6;
                s = s.substring(Class309.aClass309_2639.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2640.method3615(0, (byte)25))) {
                n2 = 7;
                s = s.substring(Class309.aClass309_2640.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2641.method3615(0, (byte)25))) {
                n2 = 8;
                s = s.substring(Class309.aClass309_2641.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2642.method3615(0, (byte)25))) {
                n2 = 9;
                s = s.substring(Class309.aClass309_2642.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2643.method3615(0, (byte)25))) {
                n2 = 10;
                s = s.substring(Class309.aClass309_2643.method3615(0, (byte)25).length());
            }
            else if (lowerCase.startsWith(Class309.aClass309_2644.method3615(0, (byte)25))) {
                n2 = 11;
                s = s.substring(Class309.aClass309_2644.method3615(0, (byte)25).length());
            }
            else if (Class374.anInt3159 != 0) {
                if (lowerCase.startsWith(Class309.aClass309_2633.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 0;
                    s = s.substring(Class309.aClass309_2633.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2634.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 1;
                    s = s.substring(Class309.aClass309_2634.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2635.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 2;
                    s = s.substring(Class309.aClass309_2635.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2636.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 3;
                    s = s.substring(Class309.aClass309_2636.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2637.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 4;
                    s = s.substring(Class309.aClass309_2637.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2638.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 5;
                    s = s.substring(Class309.aClass309_2638.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2639.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 6;
                    s = s.substring(Class309.aClass309_2639.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2640.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 7;
                    s = s.substring(Class309.aClass309_2640.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2641.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 8;
                    s = s.substring(Class309.aClass309_2641.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2642.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 9;
                    s = s.substring(Class309.aClass309_2642.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2643.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 10;
                    s = s.substring(Class309.aClass309_2643.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase.startsWith(Class309.aClass309_2644.method3615(Class374.anInt3159, (byte)25))) {
                    n2 = 11;
                    s = s.substring(Class309.aClass309_2644.method3615(Class374.anInt3159, (byte)25).length());
                }
            }
            final String lowerCase2 = s.toLowerCase();
            int n3 = 0;
            if (lowerCase2.startsWith(Class309.aClass309_2645.method3615(0, (byte)25))) {
                n3 = 1;
                s = s.substring(Class309.aClass309_2645.method3615(0, (byte)25).length());
            }
            else if (lowerCase2.startsWith(Class309.aClass309_2646.method3615(0, (byte)25))) {
                n3 = 2;
                s = s.substring(Class309.aClass309_2646.method3615(0, (byte)25).length());
            }
            else if (lowerCase2.startsWith(Class309.aClass309_2647.method3615(0, (byte)25))) {
                n3 = 3;
                s = s.substring(Class309.aClass309_2647.method3615(0, (byte)25).length());
            }
            else if (lowerCase2.startsWith(Class309.aClass309_2648.method3615(0, (byte)25))) {
                n3 = 4;
                s = s.substring(Class309.aClass309_2648.method3615(0, (byte)25).length());
            }
            else if (lowerCase2.startsWith(Class309.aClass309_2649.method3615(0, (byte)25))) {
                n3 = 5;
                s = s.substring(Class309.aClass309_2649.method3615(0, (byte)25).length());
            }
            else if (Class374.anInt3159 != 0) {
                if (lowerCase2.startsWith(Class309.aClass309_2645.method3615(Class374.anInt3159, (byte)25))) {
                    n3 = 1;
                    s = s.substring(Class309.aClass309_2645.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase2.startsWith(Class309.aClass309_2646.method3615(Class374.anInt3159, (byte)25))) {
                    n3 = 2;
                    s = s.substring(Class309.aClass309_2646.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase2.startsWith(Class309.aClass309_2647.method3615(Class374.anInt3159, (byte)25))) {
                    n3 = 3;
                    s = s.substring(Class309.aClass309_2647.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase2.startsWith(Class309.aClass309_2648.method3615(Class374.anInt3159, (byte)25))) {
                    n3 = 4;
                    s = s.substring(Class309.aClass309_2648.method3615(Class374.anInt3159, (byte)25).length());
                }
                else if (lowerCase2.startsWith(Class309.aClass309_2649.method3615(Class374.anInt3159, (byte)25))) {
                    n3 = 5;
                    s = s.substring(Class309.aClass309_2649.method3615(Class374.anInt3159, (byte)25).length());
                }
            }
            final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class87.aClass171_665, Class331.aClass117_2811);
            method3023.aClass98_Sub22_Sub1_3865.method1194(0, 70);
            final int anInt3991 = method3023.aClass98_Sub22_Sub1_3865.anInt3991;
            method3023.aClass98_Sub22_Sub1_3865.method1194(n2, 122);
            method3023.aClass98_Sub22_Sub1_3865.method1194(n3, -54);
            Class284_Sub1_Sub1.method3368(127, s, method3023.aClass98_Sub22_Sub1_3865);
            method3023.aClass98_Sub22_Sub1_3865.method1211((byte)84, method3023.aClass98_Sub22_Sub1_3865.anInt3991 - anInt3991);
            Class98_Sub10_Sub29.sendPacket(false, method3023);
        }
    }
    
    static final void method3152(final Class105 class105, final int n, final int n2) {
        final Class98_Sub46_Sub4 method2779 = Class213.method2779((byte)(-109), n2, n, class105);
        if (method2779 != null) {
            Class247.anIntArray1875 = new int[method2779.anInt5958];
            Class247.aStringArray1886 = new String[method2779.anInt5964];
            if (method2779.aClass105_5957 == Class90.aClass105_719 || method2779.aClass105_5957 == Class331.aClass105_2792 || method2779.aClass105_5957 == Class98_Sub10_Sub26.aClass105_5684) {
                int anInt2347 = 0;
                int anInt2348 = 0;
                if (Class11.aClass293_120 != null) {
                    anInt2347 = Class11.aClass293_120.anInt2347;
                    anInt2348 = Class11.aClass293_120.anInt2299;
                }
                Class247.anIntArray1875[0] = Class2.aClass299_73.method3514(112) - anInt2347;
                Class247.anIntArray1875[1] = Class2.aClass299_73.method3507((byte)72) - anInt2348;
            }
            method3153(method2779, 200000);
        }
    }
    
    private static final void method3153(Class98_Sub46_Sub4 aClass98_Sub46_Sub4_2918, final int n) {
        Class247.anInt1885 = 0;
        Class247.anInt1884 = 0;
        int anInt2919 = -1;
        int[] array = aClass98_Sub46_Sub4_2918.anIntArray5963;
        int[] array2 = aClass98_Sub46_Sub4_2918.anIntArray5967;
        int n2 = -1;
        Class247.anInt1888 = 0;
        try {
            int n3 = 0;
            while (++n3 <= n) {
                n2 = array[++anInt2919];
                if (n2 < 100) {
                    if (n2 == 0) {
                        Class247.anIntArray1878[Class247.anInt1885++] = array2[anInt2919];
                    }
                    else if (n2 == 1) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class75.aClass140_584.anIntArray3244[array2[anInt2919]];
                    }
                    else if (n2 == 2) {
                        Class75.aClass140_584.method2291(array2[anInt2919], 98, Class247.anIntArray1878[--Class247.anInt1885]);
                    }
                    else if (n2 == 3) {
                        Class247.aStringArray1883[Class247.anInt1884++] = aClass98_Sub46_Sub4_2918.aStringArray5959[anInt2919];
                    }
                    else if (n2 == 6) {
                        anInt2919 += array2[anInt2919];
                    }
                    else if (n2 == 7) {
                        Class247.anInt1885 -= 2;
                        if (Class247.anIntArray1878[Class247.anInt1885] == Class247.anIntArray1878[Class247.anInt1885 + 1]) {
                            continue;
                        }
                        anInt2919 += array2[anInt2919];
                    }
                    else if (n2 == 8) {
                        Class247.anInt1885 -= 2;
                        if (Class247.anIntArray1878[Class247.anInt1885] != Class247.anIntArray1878[Class247.anInt1885 + 1]) {
                            continue;
                        }
                        anInt2919 += array2[anInt2919];
                    }
                    else if (n2 == 9) {
                        Class247.anInt1885 -= 2;
                        if (Class247.anIntArray1878[Class247.anInt1885] >= Class247.anIntArray1878[Class247.anInt1885 + 1]) {
                            continue;
                        }
                        anInt2919 += array2[anInt2919];
                    }
                    else if (n2 == 10) {
                        Class247.anInt1885 -= 2;
                        if (Class247.anIntArray1878[Class247.anInt1885] <= Class247.anIntArray1878[Class247.anInt1885 + 1]) {
                            continue;
                        }
                        anInt2919 += array2[anInt2919];
                    }
                    else if (n2 == 21) {
                        if (Class247.anInt1888 == 0) {
                            return;
                        }
                        final Class349 class349 = Class247.aClass349Array1889[--Class247.anInt1888];
                        aClass98_Sub46_Sub4_2918 = class349.aClass98_Sub46_Sub4_2918;
                        array = aClass98_Sub46_Sub4_2918.anIntArray5963;
                        array2 = aClass98_Sub46_Sub4_2918.anIntArray5967;
                        anInt2919 = class349.anInt2919;
                        Class247.anIntArray1875 = class349.anIntArray2916;
                        Class247.aStringArray1886 = class349.aStringArray2917;
                    }
                    else if (n2 == 25) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class75.aClass140_584.method7(array2[anInt2919], 7373);
                    }
                    else if (n2 == 27) {
                        Class75.aClass140_584.method2289(Class247.anIntArray1878[--Class247.anInt1885], array2[anInt2919], 0);
                    }
                    else if (n2 == 31) {
                        Class247.anInt1885 -= 2;
                        if (Class247.anIntArray1878[Class247.anInt1885] > Class247.anIntArray1878[Class247.anInt1885 + 1]) {
                            continue;
                        }
                        anInt2919 += array2[anInt2919];
                    }
                    else if (n2 == 32) {
                        Class247.anInt1885 -= 2;
                        if (Class247.anIntArray1878[Class247.anInt1885] < Class247.anIntArray1878[Class247.anInt1885 + 1]) {
                            continue;
                        }
                        anInt2919 += array2[anInt2919];
                    }
                    else if (n2 == 33) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1875[array2[anInt2919]];
                    }
                    else if (n2 == 34) {
                        Class247.anIntArray1875[array2[anInt2919]] = Class247.anIntArray1878[--Class247.anInt1885];
                    }
                    else if (n2 == 35) {
                        Class247.aStringArray1883[Class247.anInt1884++] = Class247.aStringArray1886[array2[anInt2919]];
                    }
                    else if (n2 == 36) {
                        Class247.aStringArray1886[array2[anInt2919]] = Class247.aStringArray1883[--Class247.anInt1884];
                    }
                    else if (n2 == 37) {
                        final int n4 = array2[anInt2919];
                        Class247.anInt1884 -= n4;
                        Class247.aStringArray1883[Class247.anInt1884++] = Class98_Sub5_Sub2.method968(n4, Class247.aStringArray1883, Class247.anInt1884, -17120);
                    }
                    else if (n2 == 38) {
                        --Class247.anInt1885;
                    }
                    else if (n2 == 39) {
                        --Class247.anInt1884;
                    }
                    else if (n2 == 40) {
                        final Class98_Sub46_Sub4 method1601 = Class98_Sub46_Sub13_Sub2.method1601(array2[anInt2919], 100);
                        if (method1601 == null) {
                            throw new RuntimeException();
                        }
                        final int[] anIntArray1875 = new int[method1601.anInt5958];
                        final String[] aStringArray1886 = new String[method1601.anInt5964];
                        for (int i = 0; i < method1601.anInt5966; ++i) {
                            anIntArray1875[i] = Class247.anIntArray1878[Class247.anInt1885 - method1601.anInt5966 + i];
                        }
                        for (int j = 0; j < method1601.anInt5965; ++j) {
                            aStringArray1886[j] = Class247.aStringArray1883[Class247.anInt1884 - method1601.anInt5965 + j];
                        }
                        Class247.anInt1885 -= method1601.anInt5966;
                        Class247.anInt1884 -= method1601.anInt5965;
                        final Class349 class350 = new Class349();
                        class350.aClass98_Sub46_Sub4_2918 = aClass98_Sub46_Sub4_2918;
                        class350.anInt2919 = anInt2919;
                        class350.anIntArray2916 = Class247.anIntArray1875;
                        class350.aStringArray2917 = Class247.aStringArray1886;
                        if (Class247.anInt1888 >= Class247.aClass349Array1889.length) {
                            throw new RuntimeException();
                        }
                        Class247.aClass349Array1889[Class247.anInt1888++] = class350;
                        aClass98_Sub46_Sub4_2918 = method1601;
                        array = aClass98_Sub46_Sub4_2918.anIntArray5963;
                        array2 = aClass98_Sub46_Sub4_2918.anIntArray5967;
                        anInt2919 = -1;
                        Class247.anIntArray1875 = anIntArray1875;
                        Class247.aStringArray1886 = aStringArray1886;
                    }
                    else if (n2 == 42) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class76_Sub5.anIntArray3744[array2[anInt2919]];
                    }
                    else if (n2 == 43) {
                        final int n5 = array2[anInt2919];
                        Class76_Sub5.anIntArray3744[n5] = Class247.anIntArray1878[--Class247.anInt1885];
                        Class119_Sub1.method2180(n5, 15233);
                        Class66.aBoolean507 |= Class140.aBooleanArray3246[n5];
                    }
                    else if (n2 == 44) {
                        final int n6 = array2[anInt2919] >> 16;
                        final int n7 = array2[anInt2919] & 0xFFFF;
                        final int n8 = Class247.anIntArray1878[--Class247.anInt1885];
                        if (n8 < 0 || n8 > 5000) {
                            throw new RuntimeException();
                        }
                        Class247.anIntArray1887[n6] = n8;
                        int n9 = -1;
                        if (n7 == 105) {
                            n9 = 0;
                        }
                        for (int k = 0; k < n8; ++k) {
                            Class247.anIntArrayArray1881[n6][k] = n9;
                        }
                    }
                    else if (n2 == 45) {
                        final int n10 = array2[anInt2919];
                        final int n11 = Class247.anIntArray1878[--Class247.anInt1885];
                        if (n11 < 0 || n11 >= Class247.anIntArray1887[n10]) {
                            throw new RuntimeException();
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArrayArray1881[n10][n11];
                    }
                    else if (n2 == 46) {
                        final int n12 = array2[anInt2919];
                        Class247.anInt1885 -= 2;
                        final int n13 = Class247.anIntArray1878[Class247.anInt1885];
                        if (n13 < 0 || n13 >= Class247.anIntArray1887[n12]) {
                            throw new RuntimeException();
                        }
                        Class247.anIntArrayArray1881[n12][n13] = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    }
                    else if (n2 == 47) {
                        String s = Class151_Sub1.aStringArray4967[array2[anInt2919]];
                        if (s == null) {
                            s = "null";
                        }
                        Class247.aStringArray1883[Class247.anInt1884++] = s;
                    }
                    else if (n2 == 48) {
                        final int n14 = array2[anInt2919];
                        Class151_Sub1.aStringArray4967[n14] = Class247.aStringArray1883[--Class247.anInt1884];
                        Class347.method3833(n14, 2);
                    }
                    else if (n2 == 51) {
                        final Class98_Sub34 class98_Sub34 = (Class98_Sub34)aClass98_Sub46_Sub4_2918.aClass377Array5956[array2[anInt2919]].method3990(Class247.anIntArray1878[--Class247.anInt1885], -1);
                        if (class98_Sub34 == null) {
                            continue;
                        }
                        anInt2919 += class98_Sub34.anInt4126;
                    }
                    else if (n2 == 86) {
                        if (Class247.anIntArray1878[--Class247.anInt1885] != 1) {
                            continue;
                        }
                        anInt2919 += array2[anInt2919];
                    }
                    else {
                        if (n2 != 87 || Class247.anIntArray1878[--Class247.anInt1885] != 0) {
                            continue;
                        }
                        anInt2919 += array2[anInt2919];
                    }
                }
                else {
                    final boolean b = array2[anInt2919] == 1;
                    if (n2 >= 100 && n2 < 5000) {
                        method3148(n2, b);
                    }
                    else {
                        if (n2 < 5000 || n2 >= 10000) {
                            throw new IllegalStateException("Command: " + n2);
                        }
                        method3156(n2, b);
                    }
                }
            }
            throw new RuntimeException("slow");
        }
        catch (Exception ex) {
            if (aClass98_Sub46_Sub4_2918.aString5968 != null) {
                za_Sub2.method1684("Clientscript error in: " + aClass98_Sub46_Sub4_2918.aString5968, 4, (byte)68);
                final StringBuffer sb = new StringBuffer(30);
                sb.append("Clientscript error in: ").append(aClass98_Sub46_Sub4_2918.aString5968).append("\n");
                for (int l = Class247.anInt1888 - 1; l >= 0; --l) {
                    sb.append("via: ").append(Class247.aClass349Array1889[l].aClass98_Sub46_Sub4_2918.aString5968).append("\n");
                }
                sb.append("Op: ").append(n2).append("\n");
                final String message = ex.getMessage();
                if (message != null && message.length() > 0) {
                    sb.append("Message: ").append(message).append("\n");
                }
                Class305_Sub1.method3585(ex, -125, sb.toString());
                Class98_Sub46.method1525(sb.toString(), -126);
            }
            else {
                final StringBuffer sb2 = new StringBuffer(30);
                sb2.append("CS2: ").append(aClass98_Sub46_Sub4_2918.aLong832).append(" ");
                for (int n15 = Class247.anInt1888 - 1; n15 >= 0; --n15) {
                    sb2.append("v: ").append(Class247.aClass349Array1889[n15].aClass98_Sub46_Sub4_2918.aLong832).append(" ");
                }
                sb2.append("op: ").append(n2);
                Class305_Sub1.method3585(ex, -125, sb2.toString());
            }
        }
    }
    
    public static void method3154() {
        Class247.anIntArray1875 = null;
        Class247.aStringArray1886 = null;
        Class247.anIntArray1887 = null;
        Class247.anIntArrayArray1881 = null;
        Class247.anIntArray1878 = null;
        Class247.aStringArray1883 = null;
        Class247.aClass349Array1889 = null;
        Class247.aClass293_1879 = null;
        Class247.aClass293_1877 = null;
        Class247.aClass300_1876 = null;
        Class247.aCalendar1882 = null;
        Class247.aStringArray1892 = null;
        Class247.anIntArray1891 = null;
        Class247.aClass79_1890 = null;
    }
    
    static final void method3155(final int n) {
        if (n != -1 && Class85.method837(n, 73)) {
            final Class293[] array = Class159.aClass293ArrayArray1252[n];
            for (int i = 0; i < array.length; ++i) {
                final Class293 aClass293_3986 = array[i];
                if (aClass293_3986.anObjectArray2332 != null) {
                    final Class98_Sub21 class98_Sub21 = new Class98_Sub21();
                    class98_Sub21.aClass293_3986 = aClass293_3986;
                    class98_Sub21.anObjectArray3981 = aClass293_3986.anObjectArray2332;
                    method3150(class98_Sub21, 2000000);
                }
            }
        }
    }
    
    private static final void method3156(final int n, final boolean b) {
        if (n < 5100) {
            if (n == 5000) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class265.anInt1983;
                return;
            }
            if (n == 5001) {
                Class247.anInt1885 -= 3;
                Class265.anInt1983 = Class247.anIntArray1878[Class247.anInt1885];
                Class86.aClass350_649 = Class98_Sub10_Sub8.method1029((byte)(-107), Class247.anIntArray1878[Class247.anInt1885 + 1]);
                if (Class86.aClass350_649 == null) {
                    Class86.aClass350_649 = Class98_Sub27.aClass350_4074;
                }
                Class98.anInt837 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class98_Sub17_Sub1.aClass171_5781, Class331.aClass117_2811);
                method3023.aClass98_Sub22_Sub1_3865.method1194(Class265.anInt1983, -77);
                method3023.aClass98_Sub22_Sub1_3865.method1194(Class86.aClass350_649.anInt2920, -57);
                method3023.aClass98_Sub22_Sub1_3865.method1194(Class98.anInt837, 120);
                Class98_Sub10_Sub29.sendPacket(false, method3023);
                return;
            }
            if (n == 5002) {
                Class247.anInt1884 -= 2;
                final String s = Class247.aStringArray1883[Class247.anInt1884];
                String substring = Class247.aStringArray1883[Class247.anInt1884 + 1];
                Class247.anInt1885 -= 2;
                final int n2 = Class247.anIntArray1878[Class247.anInt1885];
                final int n3 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (substring == null) {
                    substring = "";
                }
                if (substring.length() > 80) {
                    substring = substring.substring(0, 80);
                }
                final Class98_Sub11 method3024 = Class246_Sub3_Sub4.method3023(260, Class64_Sub7.aClass171_3657, Class331.aClass117_2811);
                method3024.aClass98_Sub22_Sub1_3865.method1194(r_Sub2.method1650(s, (byte)109) + 2 + r_Sub2.method1650(substring, (byte)110), 52);
                method3024.aClass98_Sub22_Sub1_3865.method1188(s, (byte)113);
                method3024.aClass98_Sub22_Sub1_3865.method1194(n2 - 1, -91);
                method3024.aClass98_Sub22_Sub1_3865.method1194(n3, 108);
                method3024.aClass98_Sub22_Sub1_3865.method1188(substring, (byte)113);
                Class98_Sub10_Sub29.sendPacket(false, method3024);
                return;
            }
            if (n == 5003) {
                final Class131 method3025 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                String aString1041 = "";
                if (method3025 != null && method3025.aString1041 != null) {
                    aString1041 = method3025.aString1041;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = aString1041;
                return;
            }
            if (n == 5004) {
                final Class131 method3026 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                int anInt1040 = -1;
                if (method3026 != null) {
                    anInt1040 = method3026.anInt1040;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = anInt1040;
                return;
            }
            if (n == 5005) {
                if (Class86.aClass350_649 == null) {
                    Class247.anIntArray1878[Class247.anInt1885++] = -1;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = Class86.aClass350_649.anInt2920;
                return;
            }
            else {
                if (n == 5006) {
                    final int n4 = Class247.anIntArray1878[--Class247.anInt1885];
                    final Class98_Sub11 method3027 = Class246_Sub3_Sub4.method3023(260, Class146_Sub3.aClass171_4900, Class331.aClass117_2811);
                    method3027.aClass98_Sub22_Sub1_3865.method1194(n4, 55);
                    Class98_Sub10_Sub29.sendPacket(false, method3027);
                    return;
                }
                if (n == 5008) {
                    method3151(Class247.aStringArray1883[--Class247.anInt1884], n);
                    return;
                }
                if (n == 5009) {
                    Class247.anInt1884 -= 2;
                    final String s2 = Class247.aStringArray1883[Class247.anInt1884];
                    final String s3 = Class247.aStringArray1883[Class247.anInt1884 + 1];
                    if (Class282.anInt2125 != 0 || ((!Class109.aBoolean933 || Class98_Sub10_Sub35.aBoolean5732) && !Class178.aBoolean1401)) {
                        final Class98_Sub11 method3028 = Class246_Sub3_Sub4.method3023(260, Class246_Sub4_Sub2.aClass171_6185, Class331.aClass117_2811);
                        method3028.aClass98_Sub22_Sub1_3865.method1194(0, -83);
                        final int anInt1041 = method3028.aClass98_Sub22_Sub1_3865.anInt3991;
                        method3028.aClass98_Sub22_Sub1_3865.method1188(s2, (byte)113);
                        Class284_Sub1_Sub1.method3368(127, s3, method3028.aClass98_Sub22_Sub1_3865);
                        method3028.aClass98_Sub22_Sub1_3865.method1211((byte)98, method3028.aClass98_Sub22_Sub1_3865.anInt3991 - anInt1041);
                        Class98_Sub10_Sub29.sendPacket(false, method3028);
                    }
                    return;
                }
                else {
                    if (n == 5010) {
                        final Class131 method3029 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                        String aString1042 = "";
                        if (method3029 != null && method3029.aString1033 != null) {
                            aString1042 = method3029.aString1033;
                        }
                        Class247.aStringArray1883[Class247.anInt1884++] = aString1042;
                        return;
                    }
                    if (n == 5011) {
                        final Class131 method3030 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                        String aString1043 = "";
                        if (method3030 != null && method3030.aString1032 != null) {
                            aString1043 = method3030.aString1032;
                        }
                        Class247.aStringArray1883[Class247.anInt1884++] = aString1043;
                        return;
                    }
                    if (n == 5012) {
                        final Class131 method3031 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                        int anInt1042 = -1;
                        if (method3031 != null) {
                            anInt1042 = method3031.anInt1036;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = anInt1042;
                        return;
                    }
                    if (n == 5015) {
                        String method3032;
                        if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 != null && Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6536 != null) {
                            method3032 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3063(0, true);
                        }
                        else {
                            method3032 = "";
                        }
                        Class247.aStringArray1883[Class247.anInt1884++] = method3032;
                        return;
                    }
                    if (n == 5016) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98.anInt837;
                        return;
                    }
                    if (n == 5017) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub27.method1086((byte)(-4));
                        return;
                    }
                    if (n == 5018) {
                        final Class131 method3033 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                        int anInt1043 = 0;
                        if (method3033 != null) {
                            anInt1043 = method3033.anInt1035;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = anInt1043;
                        return;
                    }
                    if (n == 5019) {
                        final Class131 method3034 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                        String aString1044 = "";
                        if (method3034 != null && method3034.aString1034 != null) {
                            aString1044 = method3034.aString1034;
                        }
                        Class247.aStringArray1883[Class247.anInt1884++] = aString1044;
                        return;
                    }
                    if (n == 5020) {
                        String method3035;
                        if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 != null && Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6536 != null) {
                            method3035 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3059(-1, false);
                        }
                        else {
                            method3035 = "";
                        }
                        Class247.aStringArray1883[Class247.anInt1884++] = method3035;
                        return;
                    }
                    if (n == 5023) {
                        final Class131 method3036 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                        int anInt1044 = -1;
                        if (method3036 != null) {
                            anInt1044 = method3036.anInt1037;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = anInt1044;
                        return;
                    }
                    if (n == 5024) {
                        final Class131 method3037 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                        int anInt1045 = -1;
                        if (method3037 != null) {
                            anInt1045 = method3037.anInt1039;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = anInt1045;
                        return;
                    }
                    if (n == 5025) {
                        final Class131 method3038 = Class138.method2280((byte)49, Class247.anIntArray1878[--Class247.anInt1885]);
                        String aString1045 = "";
                        if (method3038 != null && method3038.aString1042 != null) {
                            aString1045 = method3038.aString1042;
                        }
                        Class247.aStringArray1883[Class247.anInt1884++] = aString1045;
                        return;
                    }
                    if (n == 5050) {
                        Class247.aStringArray1883[Class247.anInt1884++] = Class218.aClass212_1634.method2777(28559, Class247.anIntArray1878[--Class247.anInt1885]).aString5941;
                        return;
                    }
                    if (n == 5051) {
                        final Class98_Sub46_Sub1 method3039 = Class218.aClass212_1634.method2777(28559, Class247.anIntArray1878[--Class247.anInt1885]);
                        if (method3039.anIntArray5944 == null) {
                            Class247.anIntArray1878[Class247.anInt1885++] = 0;
                            return;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = method3039.anIntArray5944.length;
                        return;
                    }
                    else {
                        if (n == 5052) {
                            Class247.anInt1885 -= 2;
                            Class247.anIntArray1878[Class247.anInt1885++] = Class218.aClass212_1634.method2777(28559, Class247.anIntArray1878[Class247.anInt1885]).anIntArray5944[Class247.anIntArray1878[Class247.anInt1885 + 1]];
                            return;
                        }
                        if (n == 5053) {
                            final Class98_Sub46_Sub1 method3040 = Class218.aClass212_1634.method2777(28559, Class247.anIntArray1878[--Class247.anInt1885]);
                            if (method3040.anIntArray5942 == null) {
                                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                                return;
                            }
                            Class247.anIntArray1878[Class247.anInt1885++] = method3040.anIntArray5942.length;
                            return;
                        }
                        else {
                            if (n == 5054) {
                                Class247.anInt1885 -= 2;
                                Class247.anIntArray1878[Class247.anInt1885++] = Class218.aClass212_1634.method2777(28559, Class247.anIntArray1878[Class247.anInt1885]).anIntArray5942[Class247.anIntArray1878[Class247.anInt1885 + 1]];
                                return;
                            }
                            if (n == 5055) {
                                Class247.aStringArray1883[Class247.anInt1884++] = Class52.aClass280_3500.method3325(Class247.anIntArray1878[--Class247.anInt1885], 51).method1582(false);
                                return;
                            }
                            if (n == 5056) {
                                final Class98_Sub46_Sub11 method3041 = Class52.aClass280_3500.method3325(Class247.anIntArray1878[--Class247.anInt1885], 53);
                                if (method3041.anIntArray6029 == null) {
                                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                                    return;
                                }
                                Class247.anIntArray1878[Class247.anInt1885++] = method3041.anIntArray6029.length;
                                return;
                            }
                            else {
                                if (n == 5057) {
                                    Class247.anInt1885 -= 2;
                                    Class247.anIntArray1878[Class247.anInt1885++] = Class52.aClass280_3500.method3325(Class247.anIntArray1878[Class247.anInt1885], 74).anIntArray6029[Class247.anIntArray1878[Class247.anInt1885 + 1]];
                                    return;
                                }
                                if (n == 5058) {
                                    Class247.aClass300_1876 = new Class300();
                                    Class247.aClass300_1876.anInt2496 = Class247.anIntArray1878[--Class247.anInt1885];
                                    Class247.aClass300_1876.aClass98_Sub46_Sub11_2498 = Class52.aClass280_3500.method3325(Class247.aClass300_1876.anInt2496, 98);
                                    Class247.aClass300_1876.anIntArray2497 = new int[Class247.aClass300_1876.aClass98_Sub46_Sub11_2498.method1574((byte)(-111))];
                                    return;
                                }
                                if (n == 5059) {
                                    ++Class247.anInt1880;
                                    final Class98_Sub11 method3042 = Class246_Sub3_Sub4.method3023(260, Class246_Sub3_Sub1.aClass171_6148, Class331.aClass117_2811);
                                    method3042.aClass98_Sub22_Sub1_3865.method1194(0, 59);
                                    final int anInt1046 = method3042.aClass98_Sub22_Sub1_3865.anInt3991;
                                    method3042.aClass98_Sub22_Sub1_3865.method1194(0, 72);
                                    method3042.aClass98_Sub22_Sub1_3865.writeShort(Class247.aClass300_1876.anInt2496, 1571862888);
                                    Class247.aClass300_1876.aClass98_Sub46_Sub11_2498.method1579(method3042.aClass98_Sub22_Sub1_3865, Class247.aClass300_1876.anIntArray2497, -3);
                                    method3042.aClass98_Sub22_Sub1_3865.method1211((byte)98, method3042.aClass98_Sub22_Sub1_3865.anInt3991 - anInt1046);
                                    Class98_Sub10_Sub29.sendPacket(false, method3042);
                                    return;
                                }
                                if (n == 5060) {
                                    final String s4 = Class247.aStringArray1883[--Class247.anInt1884];
                                    final Class98_Sub11 method3043 = Class246_Sub3_Sub4.method3023(260, Class98_Sub17_Sub1.aClass171_5780, Class331.aClass117_2811);
                                    method3043.aClass98_Sub22_Sub1_3865.method1194(0, -109);
                                    final int anInt1047 = method3043.aClass98_Sub22_Sub1_3865.anInt3991;
                                    method3043.aClass98_Sub22_Sub1_3865.method1188(s4, (byte)113);
                                    method3043.aClass98_Sub22_Sub1_3865.writeShort(Class247.aClass300_1876.anInt2496, 1571862888);
                                    Class247.aClass300_1876.aClass98_Sub46_Sub11_2498.method1579(method3043.aClass98_Sub22_Sub1_3865, Class247.aClass300_1876.anIntArray2497, -3);
                                    method3043.aClass98_Sub22_Sub1_3865.method1211((byte)115, method3043.aClass98_Sub22_Sub1_3865.anInt3991 - anInt1047);
                                    Class98_Sub10_Sub29.sendPacket(false, method3043);
                                    return;
                                }
                                if (n == 5061) {
                                    ++Class247.anInt1880;
                                    final Class98_Sub11 method3044 = Class246_Sub3_Sub4.method3023(260, Class246_Sub3_Sub1.aClass171_6148, Class331.aClass117_2811);
                                    method3044.aClass98_Sub22_Sub1_3865.method1194(0, 91);
                                    final int anInt1048 = method3044.aClass98_Sub22_Sub1_3865.anInt3991;
                                    method3044.aClass98_Sub22_Sub1_3865.method1194(1, -118);
                                    method3044.aClass98_Sub22_Sub1_3865.writeShort(Class247.aClass300_1876.anInt2496, 1571862888);
                                    Class247.aClass300_1876.aClass98_Sub46_Sub11_2498.method1579(method3044.aClass98_Sub22_Sub1_3865, Class247.aClass300_1876.anIntArray2497, -3);
                                    method3044.aClass98_Sub22_Sub1_3865.method1211((byte)118, method3044.aClass98_Sub22_Sub1_3865.anInt3991 - anInt1048);
                                    Class98_Sub10_Sub29.sendPacket(false, method3044);
                                    return;
                                }
                                if (n == 5062) {
                                    Class247.anInt1885 -= 2;
                                    Class247.anIntArray1878[Class247.anInt1885++] = Class218.aClass212_1634.method2777(28559, Class247.anIntArray1878[Class247.anInt1885]).aCharArray5946[Class247.anIntArray1878[Class247.anInt1885 + 1]];
                                    return;
                                }
                                if (n == 5063) {
                                    Class247.anInt1885 -= 2;
                                    Class247.anIntArray1878[Class247.anInt1885++] = Class218.aClass212_1634.method2777(28559, Class247.anIntArray1878[Class247.anInt1885]).aCharArray5947[Class247.anIntArray1878[Class247.anInt1885 + 1]];
                                    return;
                                }
                                if (n == 5064) {
                                    Class247.anInt1885 -= 2;
                                    final int n5 = Class247.anIntArray1878[Class247.anInt1885];
                                    final int n6 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                                    if (n6 == -1) {
                                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                        return;
                                    }
                                    Class247.anIntArray1878[Class247.anInt1885++] = Class218.aClass212_1634.method2777(28559, n5).method1528(-1, (char)n6);
                                    return;
                                }
                                else if (n == 5065) {
                                    Class247.anInt1885 -= 2;
                                    final int n7 = Class247.anIntArray1878[Class247.anInt1885];
                                    final int n8 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                                    if (n8 == -1) {
                                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                        return;
                                    }
                                    Class247.anIntArray1878[Class247.anInt1885++] = Class218.aClass212_1634.method2777(28559, n7).method1529((char)n8, (byte)(-126));
                                    return;
                                }
                                else {
                                    if (n == 5066) {
                                        Class247.anIntArray1878[Class247.anInt1885++] = Class52.aClass280_3500.method3325(Class247.anIntArray1878[--Class247.anInt1885], 109).method1574((byte)(-109));
                                        return;
                                    }
                                    if (n == 5067) {
                                        Class247.anInt1885 -= 2;
                                        Class247.anIntArray1878[Class247.anInt1885++] = Class52.aClass280_3500.method3325(Class247.anIntArray1878[Class247.anInt1885], 67).method1580(Class247.anIntArray1878[Class247.anInt1885 + 1], 78).anInt2909;
                                        return;
                                    }
                                    if (n == 5068) {
                                        Class247.anInt1885 -= 2;
                                        Class247.aClass300_1876.anIntArray2497[Class247.anIntArray1878[Class247.anInt1885]] = Class247.anIntArray1878[Class247.anInt1885 + 1];
                                        return;
                                    }
                                    if (n == 5069) {
                                        Class247.anInt1885 -= 2;
                                        Class247.aClass300_1876.anIntArray2497[Class247.anIntArray1878[Class247.anInt1885]] = Class247.anIntArray1878[Class247.anInt1885 + 1];
                                        return;
                                    }
                                    if (n == 5070) {
                                        Class247.anInt1885 -= 3;
                                        final int n9 = Class247.anIntArray1878[Class247.anInt1885];
                                        final int n10 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                                        final int n11 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                                        final Class98_Sub46_Sub11 method3045 = Class52.aClass280_3500.method3325(n9, 65);
                                        if (method3045.method1580(n10, -126).anInt2909 != 0) {
                                            throw new RuntimeException("bad command");
                                        }
                                        Class247.anIntArray1878[Class247.anInt1885++] = method3045.method1583(121, n11, n10);
                                        return;
                                    }
                                    else {
                                        if (n == 5071) {
                                            Class207.method2759(Class247.anIntArray1878[--Class247.anInt1885] == 1, Class247.aStringArray1883[--Class247.anInt1884], (byte)69);
                                            Class247.anIntArray1878[Class247.anInt1885++] = Class18.anInt214;
                                            return;
                                        }
                                        if (n == 5072) {
                                            if (Class64_Sub16.aShortArray3682 == null || Class85.anInt638 >= Class18.anInt214) {
                                                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                                return;
                                            }
                                            Class247.anIntArray1878[Class247.anInt1885++] = (Class64_Sub16.aShortArray3682[Class85.anInt638++] & 0xFFFF);
                                            return;
                                        }
                                        else if (n == 5073) {
                                            Class85.anInt638 = 0;
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (n < 5200) {
            if (n == 5100) {
                if (Class219.aClass77_1641.method779(86, 5503)) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 1;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
            else if (n == 5101) {
                if (Class219.aClass77_1641.method779(82, 5503)) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 1;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
            else if (n == 5102) {
                if (Class219.aClass77_1641.method779(81, 5503)) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 1;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
        }
        else if (n < 5300) {
            if (n == 5200) {
                Class98_Sub46_Sub2.method1534(Class247.anIntArray1878[--Class247.anInt1885], false);
                return;
            }
            if (n == 5201) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class64.method553(1024);
                return;
            }
            if (n == 5205) {
                Class119.method2176(-1, false, Class247.anIntArray1878[--Class247.anInt1885], (byte)89, -1);
                return;
            }
            if (n == 5206) {
                final int n12 = Class247.anIntArray1878[--Class247.anInt1885];
                final Class98_Sub46_Sub10 method3046 = Class278.method3303(n12 >> 14 & 0x3FFF, n12 & 0x3FFF);
                if (method3046 == null) {
                    Class247.anIntArray1878[Class247.anInt1885++] = -1;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = method3046.anInt6014;
                return;
            }
            else if (n == 5207) {
                final Class98_Sub46_Sub10 method3047 = Class278.method3306(Class247.anIntArray1878[--Class247.anInt1885]);
                if (method3047 == null || method3047.aString6005 == null) {
                    Class247.aStringArray1883[Class247.anInt1884++] = "";
                    return;
                }
                Class247.aStringArray1883[Class247.anInt1884++] = method3047.aString6005;
                return;
            }
            else {
                if (n == 5208) {
                    Class247.anIntArray1878[Class247.anInt1885++] = aa.anInt48;
                    Class247.anIntArray1878[Class247.anInt1885++] = Class246_Sub3_Sub5_Sub2.anInt6268;
                    return;
                }
                if (n == 5209) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class42_Sub4.anInt5371 + Class278.anInt2075;
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub40.anInt4197 + Class278.anInt2078;
                    return;
                }
                if (n == 5210) {
                    final Class98_Sub46_Sub10 method3048 = Class278.method3306(Class247.anIntArray1878[--Class247.anInt1885]);
                    if (method3048 == null) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 0;
                        Class247.anIntArray1878[Class247.anInt1885++] = 0;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = (method3048.anInt6006 >> 14 & 0x3FFF);
                    Class247.anIntArray1878[Class247.anInt1885++] = (method3048.anInt6006 & 0x3FFF);
                    return;
                }
                else if (n == 5211) {
                    final Class98_Sub46_Sub10 method3049 = Class278.method3306(Class247.anIntArray1878[--Class247.anInt1885]);
                    if (method3049 == null) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 0;
                        Class247.anIntArray1878[Class247.anInt1885++] = 0;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = method3049.anInt6016 - method3049.anInt6008;
                    Class247.anIntArray1878[Class247.anInt1885++] = method3049.anInt6023 - method3049.anInt6009;
                    return;
                }
                else if (n == 5212) {
                    final Class98_Sub47 method3050 = Class256_Sub1.method3196((byte)(-99));
                    if (method3050 == null) {
                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = method3050.anInt4268;
                    Class247.anIntArray1878[Class247.anInt1885++] = (method3050.anInt4269 << 28 | method3050.anInt4272 + Class278.anInt2075 << 14 | method3050.anInt4267 + Class278.anInt2078);
                    return;
                }
                else if (n == 5213) {
                    final Class98_Sub47 method3051 = Class246_Sub3.method2979(-105);
                    if (method3051 == null) {
                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = method3051.anInt4268;
                    Class247.anIntArray1878[Class247.anInt1885++] = (method3051.anInt4269 << 28 | method3051.anInt4272 + Class278.anInt2075 << 14 | method3051.anInt4267 + Class278.anInt2078);
                    return;
                }
                else {
                    if (n == 5214) {
                        final int n13 = Class247.anIntArray1878[--Class247.anInt1885];
                        final Class98_Sub46_Sub10 method3052 = Class98_Sub10_Sub8.method1026(-3);
                        if (method3052 != null && method3052.method1573(n13 >> 28 & 0x3, Class247.anIntArray1891, -90, n13 & 0x3FFF, n13 >> 14 & 0x3FFF)) {
                            Class246_Sub2.method2971(Class247.anIntArray1891[2], (byte)39, Class247.anIntArray1891[1]);
                        }
                        return;
                    }
                    if (n == 5215) {
                        Class247.anInt1885 -= 2;
                        final int n14 = Class247.anIntArray1878[Class247.anInt1885];
                        final int n15 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                        final Class215 method3053 = Class278.method3296(n14 >> 14 & 0x3FFF, n14 & 0x3FFF);
                        boolean b2 = false;
                        for (Class98_Sub46_Sub10 class98_Sub46_Sub10 = (Class98_Sub46_Sub10)method3053.method2792(-1); class98_Sub46_Sub10 != null; class98_Sub46_Sub10 = (Class98_Sub46_Sub10)method3053.method2787(0)) {
                            if (class98_Sub46_Sub10.anInt6014 == n15) {
                                b2 = true;
                                break;
                            }
                        }
                        if (b2) {
                            Class247.anIntArray1878[Class247.anInt1885++] = 1;
                            return;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = 0;
                        return;
                    }
                    else if (n == 5218) {
                        final Class98_Sub46_Sub10 method3054 = Class278.method3306(Class247.anIntArray1878[--Class247.anInt1885]);
                        if (method3054 == null) {
                            Class247.anIntArray1878[Class247.anInt1885++] = -1;
                            return;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = method3054.anInt6007;
                        return;
                    }
                    else {
                        if (n == 5220) {
                            Class247.anIntArray1878[Class247.anInt1885++] = ((Class212.anInt1600 == 100) ? 1 : 0);
                            return;
                        }
                        if (n == 5221) {
                            final int n16 = Class247.anIntArray1878[--Class247.anInt1885];
                            Class246_Sub2.method2971(n16 & 0x3FFF, (byte)100, n16 >> 14 & 0x3FFF);
                            return;
                        }
                        if (n == 5222) {
                            final Class98_Sub46_Sub10 method3055 = Class98_Sub10_Sub8.method1026(-3);
                            if (method3055 != null) {
                                if (method3055.method1563(31960, Class42_Sub4.anInt5371 + Class278.anInt2075, Class247.anIntArray1891, Class98_Sub40.anInt4197 + Class278.anInt2078)) {
                                    Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1891[1];
                                    Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1891[2];
                                }
                                else {
                                    Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                    Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                }
                                return;
                            }
                            Class247.anIntArray1878[Class247.anInt1885++] = -1;
                            Class247.anIntArray1878[Class247.anInt1885++] = -1;
                            return;
                        }
                        else {
                            if (n == 5223) {
                                Class247.anInt1885 -= 2;
                                final int n17 = Class247.anIntArray1878[Class247.anInt1885];
                                final int n18 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                                Class119.method2176(n18 >> 14 & 0x3FFF, false, n17, (byte)89, n18 & 0x3FFF);
                                return;
                            }
                            if (n == 5224) {
                                final int n19 = Class247.anIntArray1878[--Class247.anInt1885];
                                final Class98_Sub46_Sub10 method3056 = Class98_Sub10_Sub8.method1026(-3);
                                if (method3056 != null) {
                                    if (method3056.method1573(n19 >> 28 & 0x3, Class247.anIntArray1891, -105, n19 & 0x3FFF, n19 >> 14 & 0x3FFF)) {
                                        Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1891[1];
                                        Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1891[2];
                                    }
                                    else {
                                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                    }
                                    return;
                                }
                                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                return;
                            }
                            else if (n == 5225) {
                                final int n20 = Class247.anIntArray1878[--Class247.anInt1885];
                                final Class98_Sub46_Sub10 method3057 = Class98_Sub10_Sub8.method1026(-3);
                                if (method3057 != null) {
                                    if (method3057.method1563(31960, n20 >> 14 & 0x3FFF, Class247.anIntArray1891, n20 & 0x3FFF)) {
                                        Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1891[1];
                                        Class247.anIntArray1878[Class247.anInt1885++] = Class247.anIntArray1891[2];
                                    }
                                    else {
                                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                        Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                    }
                                    return;
                                }
                                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                return;
                            }
                            else {
                                if (n == 5226) {
                                    Class40.method362((byte)103, Class247.anIntArray1878[--Class247.anInt1885]);
                                    return;
                                }
                                if (n == 5227) {
                                    Class247.anInt1885 -= 2;
                                    final int n21 = Class247.anIntArray1878[Class247.anInt1885];
                                    final int n22 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                                    Class119.method2176(n22 >> 14 & 0x3FFF, true, n21, (byte)89, n22 & 0x3FFF);
                                    return;
                                }
                                if (n == 5228) {
                                    Class98_Sub43_Sub1.aBoolean5895 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                                    return;
                                }
                                if (n == 5229) {
                                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub43_Sub1.aBoolean5895 ? 1 : 0);
                                    return;
                                }
                                if (n == 5230) {
                                    Class119_Sub4.method2190(125, Class247.anIntArray1878[--Class247.anInt1885]);
                                    return;
                                }
                                if (n == 5231) {
                                    Class247.anInt1885 -= 2;
                                    final int n23 = Class247.anIntArray1878[Class247.anInt1885];
                                    final boolean b3 = Class247.anIntArray1878[Class247.anInt1885 + 1] == 1;
                                    if (Class366.aClass377_3114 != null) {
                                        final Class98 method3058 = Class366.aClass377_3114.method3990(n23, -1);
                                        if (method3058 != null && !b3) {
                                            method3058.method942(120);
                                        }
                                        else if (method3058 == null && b3) {
                                            Class366.aClass377_3114.method3996(new Class98(), n23, -1);
                                        }
                                    }
                                    return;
                                }
                                if (n == 5232) {
                                    final int n24 = Class247.anIntArray1878[--Class247.anInt1885];
                                    if (Class366.aClass377_3114 != null) {
                                        Class247.anIntArray1878[Class247.anInt1885++] = ((Class366.aClass377_3114.method3990(n24, -1) != null) ? 1 : 0);
                                        return;
                                    }
                                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                                    return;
                                }
                                else {
                                    if (n == 5233) {
                                        Class247.anInt1885 -= 2;
                                        final int n25 = Class247.anIntArray1878[Class247.anInt1885];
                                        final boolean b4 = Class247.anIntArray1878[Class247.anInt1885 + 1] == 1;
                                        if (Class248.aClass377_1894 != null) {
                                            final Class98 method3059 = Class248.aClass377_1894.method3990(n25, -1);
                                            if (method3059 != null && !b4) {
                                                method3059.method942(97);
                                            }
                                            else if (method3059 == null && b4) {
                                                Class248.aClass377_1894.method3996(new Class98(), n25, -1);
                                            }
                                        }
                                        return;
                                    }
                                    if (n == 5234) {
                                        final int n26 = Class247.anIntArray1878[--Class247.anInt1885];
                                        if (Class248.aClass377_1894 != null) {
                                            Class247.anIntArray1878[Class247.anInt1885++] = ((Class248.aClass377_1894.method3990(n26, -1) != null) ? 1 : 0);
                                            return;
                                        }
                                        Class247.anIntArray1878[Class247.anInt1885++] = 0;
                                        return;
                                    }
                                    else {
                                        if (n == 5235) {
                                            Class247.anIntArray1878[Class247.anInt1885++] = ((Class278.aClass98_Sub46_Sub10_2056 != null) ? Class278.aClass98_Sub46_Sub10_2056.anInt6014 : -1);
                                            return;
                                        }
                                        if (n == 5236) {
                                            Class247.anInt1885 -= 2;
                                            final int n27 = Class247.anIntArray1878[Class247.anInt1885];
                                            final int n28 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                                            final int method3060 = Class98_Sub28.method1307(n27, 1, n28 >> 14 & 0x3FFF, n28 & 0x3FFF);
                                            if (method3060 < 0) {
                                                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                                                return;
                                            }
                                            Class247.anIntArray1878[Class247.anInt1885++] = method3060;
                                            return;
                                        }
                                        else if (n == 5237) {
                                            Class231.method2878(2);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (n < 5400) {
            if (n == 5300) {
                Class247.anInt1885 -= 2;
                Class98_Sub16.method1150(3, Class247.anIntArray1878[Class247.anInt1885], 3, Class247.anIntArray1878[Class247.anInt1885 + 1], false);
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub18.aFrame3950 != null) ? 1 : 0);
                return;
            }
            if (n == 5301) {
                if (Class98_Sub18.aFrame3950 != null) {
                    Class98_Sub16.method1150(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4052.method666((byte)123), -1, 3, -1, false);
                }
                return;
            }
            if (n == 5302) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class47.method451(124).length;
                return;
            }
            if (n == 5303) {
                final int n29 = Class247.anIntArray1878[--Class247.anInt1885];
                final Class259[] method3061 = Class47.method451(121);
                Class247.anIntArray1878[Class247.anInt1885++] = method3061[n29].anInt1953;
                Class247.anIntArray1878[Class247.anInt1885++] = method3061[n29].anInt1956;
                return;
            }
            if (n == 5305) {
                final int anInt1049 = Class110.anInt946;
                final int anInt1050 = Class112.anInt949;
                int n30 = -1;
                final Class259[] method3062 = Class47.method451(122);
                for (int i = 0; i < method3062.length; ++i) {
                    final Class259 class259 = method3062[i];
                    if (class259.anInt1953 == anInt1049 && class259.anInt1956 == anInt1050) {
                        n30 = i;
                        break;
                    }
                }
                Class247.anIntArray1878[Class247.anInt1885++] = n30;
                return;
            }
            if (n == 5306) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class146_Sub2.method2391((byte)102);
                return;
            }
            if (n == 5307) {
                final int n31 = Class247.anIntArray1878[--Class247.anInt1885];
                if (n31 >= 1 && n31 <= 2) {
                    Class98_Sub16.method1150(n31, -1, 3, -1, false);
                }
                return;
            }
            else {
                if (n == 5308) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4052.method666((byte)122);
                    return;
                }
                if (n == 5309) {
                    final int n32 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (n32 >= 1 && n32 <= 2) {
                        Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n32, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4052);
                        Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n32, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4068);
                        Class310.method3618(-5964);
                    }
                    return;
                }
            }
        }
        else if (n < 5500) {
            if (n == 5400) {
                Class247.anInt1884 -= 2;
                final String s5 = Class247.aStringArray1883[Class247.anInt1884];
                final String s6 = Class247.aStringArray1883[Class247.anInt1884 + 1];
                final int n33 = Class247.anIntArray1878[--Class247.anInt1885];
                final Class98_Sub11 method3063 = Class246_Sub3_Sub4.method3023(260, Class224.aClass171_1684, Class331.aClass117_2811);
                method3063.aClass98_Sub22_Sub1_3865.method1194(r_Sub2.method1650(s5, (byte)79) + r_Sub2.method1650(s6, (byte)124) + 1, -41);
                method3063.aClass98_Sub22_Sub1_3865.method1188(s5, (byte)113);
                method3063.aClass98_Sub22_Sub1_3865.method1188(s6, (byte)113);
                method3063.aClass98_Sub22_Sub1_3865.method1194(n33, -78);
                Class98_Sub10_Sub29.sendPacket(false, method3063);
                return;
            }
            if (n == 5401) {
                Class247.anInt1885 -= 2;
                Class246.aShortArray1869[Class247.anIntArray1878[Class247.anInt1885]] = (short)Class38.method348(Class247.anIntArray1878[Class247.anInt1885 + 1], -13);
                Class98_Sub46_Sub19.aClass205_6068.method2721(60);
                Class98_Sub46_Sub19.aClass205_6068.method2717(64);
                Class4.aClass301_85.method3534(-123);
                Class98_Sub43.method1481(2);
                return;
            }
            if (n == 5405) {
                Class247.anInt1885 -= 2;
                final int n34 = Class247.anIntArray1878[Class247.anInt1885];
                final int n35 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (n34 >= 0 && n34 < 2) {
                    InputStream_Sub1.anIntArrayArrayArray27[n34] = new int[n35 << 1][4];
                }
                return;
            }
            if (n == 5406) {
                Class247.anInt1885 -= 7;
                final int n36 = Class247.anIntArray1878[Class247.anInt1885];
                final int n37 = Class247.anIntArray1878[Class247.anInt1885 + 1] << 1;
                final int n38 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                final int n39 = Class247.anIntArray1878[Class247.anInt1885 + 3];
                final int n40 = Class247.anIntArray1878[Class247.anInt1885 + 4];
                final int n41 = Class247.anIntArray1878[Class247.anInt1885 + 5];
                final int n42 = Class247.anIntArray1878[Class247.anInt1885 + 6];
                if (n36 >= 0 && n36 < 2 && InputStream_Sub1.anIntArrayArrayArray27[n36] != null && n37 >= 0 && n37 < InputStream_Sub1.anIntArrayArrayArray27[n36].length) {
                    InputStream_Sub1.anIntArrayArrayArray27[n36][n37] = new int[] { (n38 >> 14 & 0x3FFF) << 9, n39 << 2, (n38 & 0x3FFF) << 9, n42 };
                    InputStream_Sub1.anIntArrayArrayArray27[n36][n37 + 1] = new int[] { (n40 >> 14 & 0x3FFF) << 9, n41 << 2, (n40 & 0x3FFF) << 9 };
                }
                return;
            }
            if (n == 5407) {
                Class247.anIntArray1878[Class247.anInt1885++] = InputStream_Sub1.anIntArrayArrayArray27[Class247.anIntArray1878[--Class247.anInt1885]].length >> 1;
                return;
            }
            if (n == 5411) {
                if (Class98_Sub18.aFrame3950 != null) {
                    Class98_Sub16.method1150(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4052.method666((byte)121), -1, 3, -1, false);
                }
                if (Class284.aFrame2168 != null) {
                    Class23.method283((byte)100);
                    System.exit(0);
                    return;
                }
                Class315.method3647(false, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)123) == 1, (Class5.aString3440 != null) ? Class5.aString3440 : Class98_Sub17.method1153((byte)(-100)), true, Class98_Sub43_Sub2.aClass88_5907);
                return;
            }
            else {
                if (n == 5419) {
                    String method3064 = "";
                    if (Class187.aClass143_1449 != null) {
                        if (Class187.aClass143_1449.anObject1162 != null) {
                            method3064 = (String)Class187.aClass143_1449.anObject1162;
                        }
                        else {
                            method3064 = Class98_Sub10_Sub39.method1122(Class187.aClass143_1449.anInt1166, (byte)(-36));
                        }
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = method3064;
                    return;
                }
                if (n == 5420) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub43_Sub2.aClass88_5907.aBoolean682 ? 0 : 1);
                    return;
                }
                if (n == 5421) {
                    if (Class98_Sub18.aFrame3950 != null) {
                        Class98_Sub16.method1150(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4052.method666((byte)125), -1, 3, -1, false);
                    }
                    Class315.method3647(Class247.anIntArray1878[--Class247.anInt1885] == 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)123) == 1, Class98_Sub17.method1153((byte)(-64)) + Class247.aStringArray1883[--Class247.anInt1884], true, Class98_Sub43_Sub2.aClass88_5907);
                    return;
                }
                if (n == 5422) {
                    Class247.anInt1884 -= 2;
                    final String s7 = Class247.aStringArray1883[Class247.anInt1884];
                    final String s8 = Class247.aStringArray1883[Class247.anInt1884 + 1];
                    final int n43 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (s7.length() > 0) {
                        if (Class116.aStringArray966 == null) {
                            Class116.aStringArray966 = new String[Class282.anIntArray2130[Class4.aClass279_86.anInt2095]];
                        }
                        Class116.aStringArray966[n43] = s7;
                    }
                    if (s8.length() > 0) {
                        if (Class84.aStringArray636 == null) {
                            Class84.aStringArray636 = new String[Class282.anIntArray2130[Class4.aClass279_86.anInt2095]];
                        }
                        Class84.aStringArray636[n43] = s8;
                    }
                    return;
                }
                if (n == 5423) {
                    System.out.println(Class247.aStringArray1883[--Class247.anInt1884]);
                    return;
                }
                if (n == 5424) {
                    Class247.anInt1885 -= 11;
                    Class260.anInt3261 = Class247.anIntArray1878[Class247.anInt1885];
                    Class355.anInt3017 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    Class93_Sub1_Sub1.anInt6289 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                    Class264.anInt1971 = Class247.anIntArray1878[Class247.anInt1885 + 3];
                    Class38.anInt360 = Class247.anIntArray1878[Class247.anInt1885 + 4];
                    Class222.anInt1672 = Class247.anIntArray1878[Class247.anInt1885 + 5];
                    Class25.anInt267 = Class247.anIntArray1878[Class247.anInt1885 + 6];
                    Class95.anInt799 = Class247.anIntArray1878[Class247.anInt1885 + 7];
                    Class146.anInt1183 = Class247.anIntArray1878[Class247.anInt1885 + 8];
                    Class147.anInt1194 = Class247.anIntArray1878[Class247.anInt1885 + 9];
                    Class246.anInt1871 = Class247.anIntArray1878[Class247.anInt1885 + 10];
                    Class332_Sub2.aClass207_5423.method2742(-28, Class38.anInt360);
                    Class332_Sub2.aClass207_5423.method2742(-124, Class222.anInt1672);
                    Class332_Sub2.aClass207_5423.method2742(-125, Class25.anInt267);
                    Class332_Sub2.aClass207_5423.method2742(-34, Class95.anInt799);
                    Class332_Sub2.aClass207_5423.method2742(-116, Class146.anInt1183);
                    Class45.aClass332_383 = (Class98_Sub50.aClass332_4287 = (Class98_Sub47.aClass332_4273 = null));
                    Class246_Sub3_Sub4_Sub2_Sub1.aClass332_6508 = (Class300.aClass332_2500 = (Class76_Sub11.aClass332_3795 = null));
                    Class98_Sub10_Sub28.aClass332_5704 = (Class221.aClass332_1666 = null);
                    Class98_Sub5_Sub3.aBoolean5539 = true;
                    return;
                }
                if (n == 5425) {
                    Class48_Sub1_Sub2.method466(true);
                    Class98_Sub5_Sub3.aBoolean5539 = false;
                    return;
                }
                if (n == 5426) {
                    Class247.anInt1885 -= 2;
                    OutputStream_Sub2.anInt39 = Class247.anIntArray1878[Class247.anInt1885];
                    Class284_Sub2.anInt5186 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    return;
                }
                if (n == 5427) {
                    Class247.anInt1885 -= 2;
                    Class16.anInt190 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    return;
                }
                if (n == 5428) {
                    Class247.anInt1885 -= 2;
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub46_Sub5.method1543(Class247.anIntArray1878[Class247.anInt1885], Class247.anIntArray1878[Class247.anInt1885 + 1], (byte)6) ? 1 : 0);
                    return;
                }
                if (n == 5429) {
                    PlayerUpdateMask.method710(Class247.aStringArray1883[--Class247.anInt1884], false, false, (byte)117);
                    return;
                }
                if (n == 5430) {
                    try {
                        Class203.method2704("accountcreated", Class76_Sub11.anApplet3799, -26978);
                    }
                    catch (Throwable t) {}
                    return;
                }
                if (n == 5431) {
                    try {
                        Class203.method2704("accountcreatestarted", Class76_Sub11.anApplet3799, -26978);
                    }
                    catch (Throwable t2) {}
                    return;
                }
                if (n == 5432) {
                    String s9 = "";
                    if (Class8.aClipboard113 != null) {
                        final Transferable contents = Class8.aClipboard113.getContents(null);
                        if (contents != null) {
                            try {
                                s9 = (String)contents.getTransferData(DataFlavor.stringFlavor);
                                if (s9 == null) {
                                    s9 = "";
                                }
                            }
                            catch (Exception ex) {}
                        }
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = s9;
                    return;
                }
                if (n == 5433) {
                    Class64_Sub3.anInt3647 = Class247.anIntArray1878[--Class247.anInt1885];
                    return;
                }
            }
        }
        else if (n < 5600) {
            if (n == 5500) {
                Class247.anInt1885 -= 4;
                final int n44 = Class247.anIntArray1878[Class247.anInt1885];
                ha_Sub1.method1871(Class247.anIntArray1878[Class247.anInt1885 + 2], (n44 >> 14 & 0x3FFF) - Class272.anInt2038, false, Class247.anIntArray1878[Class247.anInt1885 + 1] << 2, (n44 & 0x3FFF) - aa_Sub2.anInt3562, Class247.anIntArray1878[Class247.anInt1885 + 3], -116);
                return;
            }
            if (n == 5501) {
                Class247.anInt1885 -= 4;
                final int n45 = Class247.anIntArray1878[Class247.anInt1885];
                Class98_Sub46_Sub13.method1592(-25686, Class247.anIntArray1878[Class247.anInt1885 + 2], (n45 >> 14 & 0x3FFF) - Class272.anInt2038, Class247.anIntArray1878[Class247.anInt1885 + 1] << 2, Class247.anIntArray1878[Class247.anInt1885 + 3], (n45 & 0x3FFF) - aa_Sub2.anInt3562);
                return;
            }
            if (n == 5502) {
                Class247.anInt1885 -= 6;
                final int anInt1051 = Class247.anIntArray1878[Class247.anInt1885];
                if (anInt1051 >= 2) {
                    throw new RuntimeException();
                }
                Class368.anInt3128 = anInt1051;
                final int anInt1052 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (anInt1052 + 1 >= InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128].length >> 1) {
                    throw new RuntimeException();
                }
                Class50.anInt418 = anInt1052;
                Class54.anInt3394 = 0;
                Class246.anInt1872 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                Class98_Sub10_Sub32.anInt5718 = Class247.anIntArray1878[Class247.anInt1885 + 3];
                final int anInt1053 = Class247.anIntArray1878[Class247.anInt1885 + 4];
                if (anInt1053 >= 2) {
                    throw new RuntimeException();
                }
                Class53_Sub1.anInt3636 = anInt1053;
                final int anInt1054 = Class247.anIntArray1878[Class247.anInt1885 + 5];
                if (anInt1054 + 1 >= InputStream_Sub1.anIntArrayArrayArray27[Class53_Sub1.anInt3636].length >> 1) {
                    throw new RuntimeException();
                }
                ha.anInt943 = anInt1054;
                Class98_Sub46_Sub20_Sub2.anInt6319 = 3;
                Class116.anInt967 = (Class64_Sub26.anInt3712 = -1);
                return;
            }
            else {
                if (n == 5503) {
                    Class284.method3359(9268);
                    return;
                }
                if (n == 5504) {
                    Class247.anInt1885 -= 2;
                    Class308.method3608(Class247.anIntArray1878[Class247.anInt1885 + 1], 0, 87, Class247.anIntArray1878[Class247.anInt1885]);
                    return;
                }
                if (n == 5505) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (int)Class119_Sub4.aFloat4740 >> 3;
                    return;
                }
                if (n == 5506) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (int)Class98_Sub22_Sub2.aFloat5794 >> 3;
                    return;
                }
                if (n == 5507) {
                    Exception_Sub1.method134((byte)(-87));
                    return;
                }
                if (n == 5508) {
                    Class98_Sub43.method1485(-1);
                    return;
                }
                if (n == 5509) {
                    Class64_Sub13.method603((byte)(-107));
                    return;
                }
                if (n == 5510) {
                    Class98_Sub31_Sub2.method1367((byte)83);
                    return;
                }
                if (n == 5511) {
                    final int n46 = Class247.anIntArray1878[--Class247.anInt1885];
                    final int n47 = n46 >> 14 & 0x3FFF;
                    final int n48 = n46 & 0x3FFF;
                    int anInt1055 = n47 - Class272.anInt2038;
                    if (anInt1055 < 0) {
                        anInt1055 = 0;
                    }
                    else if (anInt1055 >= Class165.anInt1276) {
                        anInt1055 = Class165.anInt1276;
                    }
                    int anInt1056 = n48 - aa_Sub2.anInt3562;
                    if (anInt1056 < 0) {
                        anInt1056 = 0;
                    }
                    else if (anInt1056 >= Class98_Sub10_Sub7.anInt5572) {
                        anInt1056 = Class98_Sub10_Sub7.anInt5572;
                    }
                    Class98_Sub46_Sub2_Sub2.anInt6295 = (anInt1055 << 9) + 256;
                    Class135.anInt1051 = (anInt1056 << 9) + 256;
                    Class98_Sub46_Sub20_Sub2.anInt6319 = 4;
                    Class116.anInt967 = (Class64_Sub26.anInt3712 = -1);
                    return;
                }
                if (n == 5512) {
                    Class352.method3856((byte)1);
                    return;
                }
                if (n == 5514) {
                    Class16.anInt199 = Class247.anIntArray1878[--Class247.anInt1885];
                    return;
                }
                if (n == 5516) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class16.anInt199;
                    return;
                }
                if (n == 5517) {
                    final int n49 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (n49 == -1) {
                        final int n50 = n49 >> 14 & 0x3FFF;
                        final int n51 = n49 & 0x3FFF;
                        int anInt1057 = n50 - Class272.anInt2038;
                        if (anInt1057 < 0) {
                            anInt1057 = 0;
                        }
                        else if (anInt1057 >= Class165.anInt1276) {
                            anInt1057 = Class165.anInt1276;
                        }
                        int anInt1058 = n51 - aa_Sub2.anInt3562;
                        if (anInt1058 < 0) {
                            anInt1058 = 0;
                        }
                        else if (anInt1058 >= Class98_Sub10_Sub7.anInt5572) {
                            anInt1058 = Class98_Sub10_Sub7.anInt5572;
                        }
                        Class116.anInt967 = (anInt1057 << 9) + 256;
                        Class64_Sub26.anInt3712 = (anInt1058 << 9) + 256;
                        return;
                    }
                    Class116.anInt967 = -1;
                    Class64_Sub26.anInt3712 = -1;
                    return;
                }
            }
        }
        else if (n < 5700) {
            if (n == 5600) {
                Class247.anInt1884 -= 2;
                final String aString1046 = Class247.aStringArray1883[Class247.anInt1884];
                final String aString1047 = Class247.aStringArray1883[Class247.anInt1884 + 1];
                final int anInt1059 = Class247.anIntArray1878[--Class247.anInt1885];
                if (aString1046.length() <= 320 && Class177.anInt1376 == 3 && Class64_Sub16.anInt3680 == 0 && Class21_Sub4.anInt5394 == 0) {
                    Class98_Sub5.aString3837 = aString1046;
                    Class360.aString3064 = aString1047;
                    Class146_Sub2.anInt4855 = anInt1059;
                    Class61.method538(6, false);
                }
                return;
            }
            else {
                if (n == 5601) {
                    Class246_Sub4_Sub1.method3103((byte)(-38));
                    return;
                }
                if (n == 5602) {
                    if (Class64_Sub16.anInt3680 == 0) {
                        Class257.anInt1946 = -2;
                        Class31.anInt300 = -2;
                    }
                    return;
                }
                if (n == 5604) {
                    --Class247.anInt1884;
                    if (Class177.anInt1376 == 3 && Class64_Sub16.anInt3680 == 0 && Class21_Sub4.anInt5394 == 0) {
                        Class329.method3713((byte)34, Class247.aStringArray1883[Class247.anInt1884]);
                    }
                    return;
                }
                else if (n == 5605) {
                    Class247.anInt1884 -= 2;
                    Class247.anInt1885 -= 2;
                    if (Class177.anInt1376 == 3 && Class64_Sub16.anInt3680 == 0 && Class21_Sub4.anInt5394 == 0) {
                        Class132.method2236(Class247.anIntArray1878[Class247.anInt1885 + 1] == 1, 0, Class247.aStringArray1883[Class247.anInt1884 + 1], Class247.aStringArray1883[Class247.anInt1884], Class247.anIntArray1878[Class247.anInt1885]);
                    }
                    return;
                }
                else {
                    if (n == 5606) {
                        if (Class21_Sub4.anInt5394 == 0) {
                            Class55.anInt442 = -2;
                        }
                        return;
                    }
                    if (n == 5607) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class31.anInt300;
                        return;
                    }
                    if (n == 5608) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub48.anInt4277;
                        return;
                    }
                    if (n == 5609) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class55.anInt442;
                        return;
                    }
                    if (n == 5611) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class69_Sub1.anInt5330;
                        return;
                    }
                    if (n == 5612) {
                        final int anInt1060 = Class247.anIntArray1878[--Class247.anInt1885];
                        if (Class177.anInt1376 == 7 && Class64_Sub16.anInt3680 == 0 && Class21_Sub4.anInt5394 == 0) {
                            if (aa_Sub1.aClass123_3561 != null) {
                                aa_Sub1.aClass123_3561.method2207(-18);
                                aa_Sub1.aClass123_3561 = null;
                            }
                            Class146_Sub2.anInt4855 = anInt1060;
                            Class61.method538(9, false);
                        }
                        return;
                    }
                    else {
                        if (n == 5613) {
                            Class247.anIntArray1878[Class247.anInt1885++] = Class31.anInt300;
                            return;
                        }
                        if (n == 5615) {
                            Class247.anInt1884 -= 2;
                            final String aString1048 = Class247.aStringArray1883[Class247.anInt1884];
                            final String aString1049 = Class247.aStringArray1883[Class247.anInt1884 + 1];
                            if (aString1048.length() <= 320 && Class177.anInt1376 == 3 && Class64_Sub16.anInt3680 == 0 && Class21_Sub4.anInt5394 == 0) {
                                if (aa_Sub1.aClass123_3561 != null) {
                                    aa_Sub1.aClass123_3561.method2207(-64);
                                    aa_Sub1.aClass123_3561 = null;
                                }
                                Class98_Sub5.aString3837 = aString1048;
                                Class360.aString3064 = aString1049;
                                Class61.method538(5, false);
                            }
                            return;
                        }
                        else {
                            if (n == 5616) {
                                Class98_Sub10_Sub1.method1003(false, false);
                                return;
                            }
                            if (n == 5617) {
                                Class247.anIntArray1878[Class247.anInt1885++] = Class257.anInt1946;
                                return;
                            }
                            if (n == 5618) {
                                --Class247.anInt1885;
                                return;
                            }
                            if (n == 5619) {
                                --Class247.anInt1885;
                                return;
                            }
                            if (n == 5620) {
                                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                                return;
                            }
                            if (n == 5621) {
                                Class247.anInt1884 -= 2;
                                Class247.anInt1885 -= 2;
                                return;
                            }
                            if (n == 5622) {
                                return;
                            }
                            if (n == 5623) {
                                if (Class98_Sub10_Sub10.aString5593 != null) {
                                    Class247.anIntArray1878[Class247.anInt1885++] = 1;
                                    return;
                                }
                                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                                return;
                            }
                            else {
                                if (n == 5624) {
                                    Class247.anIntArray1878[Class247.anInt1885++] = (int)(Class197.aLong1515 >> 32);
                                    Class247.anIntArray1878[Class247.anInt1885++] = (int)(Class197.aLong1515 & 0xFFFFL);
                                    return;
                                }
                                if (n == 5625) {
                                    Class247.anIntArray1878[Class247.anInt1885++] = (ha.aBoolean940 ? 1 : 0);
                                    return;
                                }
                                if (n == 5626) {
                                    ha.aBoolean940 = true;
                                    Class305.method3571(-68);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (n < 6100) {
            if (n == 6001) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub19_4057);
                Class98_Sub10.method999((byte)(-74));
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6002) {
                final int n52 = (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 1 : 0;
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n52, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4041);
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n52, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076);
                Class98_Sub10.method999((byte)121);
                Class135.method2264((byte)(-118));
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6003) {
                final boolean b5 = Class247.anIntArray1878[--Class247.anInt1885] == 1;
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), b5 ? 2 : 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4034);
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), b5 ? 2 : 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4058);
                Class135.method2264((byte)(-117));
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6005) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038);
                Class98_Sub10.method999((byte)124);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6007) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub24_4047);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6008) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub13_4063);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6010) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6011) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073);
                Class98_Sub10.method999((byte)123);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6012) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070);
                Class98_Sub46_Sub13_Sub1.method1593((byte)67);
                Class374.method3980((byte)127);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6014) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 2 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064);
                Class98_Sub10.method999((byte)119);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6015) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049);
                Class98_Sub10.method999((byte)123);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6016) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055);
                Class76_Sub4.method754(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)123), false, 80);
                Class310.method3618(-5964);
                return;
            }
            if (n == 6017) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub1_4043);
                Class233.method2884(124);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6018) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4066);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6019) {
                final int n53 = Class247.anIntArray1878[--Class247.anInt1885];
                final int method3065 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4069.method641((byte)121);
                if (n53 != method3065) {
                    if (za_Sub2.method1683(-11297, Class177.anInt1376)) {
                        if (method3065 == 0 && Class144.anInt1169 != -1) {
                            s_Sub1.method3434(Class98_Sub10_Sub1.aClass207_5544, false, n53, Class144.anInt1169, 0, -16523);
                            Class233.method2883((byte)111);
                            Class151_Sub5.aBoolean4991 = false;
                        }
                        else if (n53 == 0) {
                            Class337_Sub1.method3777(31585);
                            Class151_Sub5.aBoolean4991 = false;
                        }
                        else {
                            Class98_Sub10_Sub19.method1057(n53, 1024);
                        }
                    }
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n53, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4069);
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                }
                return;
            }
            if (n == 6020) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4051);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6021) {
                final int method3066 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4034.method612((byte)127);
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 0 : method3066, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4058);
                Class135.method2264((byte)(-113));
                return;
            }
            if (n == 6023) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub6_4033);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6024) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4044);
                Class310.method3618(-5964);
                return;
            }
            if (n == 6025) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6027) {
                int n54 = Class247.anIntArray1878[--Class247.anInt1885];
                if (n54 < 0 || n54 > 1) {
                    n54 = 0;
                }
                Class98_Sub5_Sub1.method966(29089, n54 == 1);
                return;
            }
            if (n == 6028) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] != 0) ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub29_4050);
                Class310.method3618(-5964);
                return;
            }
            if (n == 6029) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub24_4047);
                Class310.method3618(-5964);
                return;
            }
            if (n == 6030) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] != 0) ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub25_4039);
                Class310.method3618(-5964);
                Class98_Sub10.method999((byte)(-71));
                return;
            }
            if (n == 6031) {
                int n55 = Class247.anIntArray1878[--Class247.anInt1885];
                if (n55 < 0 || n55 > 5) {
                    n55 = 2;
                }
                Class76_Sub4.method754(n55, false, 3);
                return;
            }
            if (n == 6032) {
                Class247.anInt1885 -= 2;
                final int n56 = Class247.anIntArray1878[Class247.anInt1885];
                final boolean b6 = Class247.anIntArray1878[Class247.anInt1885 + 1] == 1;
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n56, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062);
                if (!b6) {
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub12_4048);
                }
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6033) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub21_4037);
                Class310.method3618(-5964);
                return;
            }
            if (n == 6034) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 1 : 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056);
                Class310.method3618(-5964);
                Class98_Sub46_Sub13_Sub1.method1593((byte)49);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6035) {
                final int method3067 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4041.method564((byte)125);
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), (Class247.anIntArray1878[--Class247.anInt1885] == 1) ? 1 : method3067, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076);
                Class98_Sub10.method999((byte)112);
                Class135.method2264((byte)(-118));
                return;
            }
            if (n == 6036) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040);
                Class310.method3618(-5964);
                Class33.aBoolean316 = true;
                return;
            }
            if (n == 6037) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), Class247.anIntArray1878[--Class247.anInt1885], Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4054);
                Class310.method3618(-5964);
                s_Sub1.aBoolean5207 = false;
                return;
            }
            if (n == 6038) {
                final int n57 = Class247.anIntArray1878[--Class247.anInt1885];
                final int method3068 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4072.method641((byte)123);
                if (n57 != method3068 && Class144.anInt1169 == Class94.anInt795) {
                    if (!za_Sub2.method1683(-11297, Class177.anInt1376)) {
                        if (method3068 == 0) {
                            s_Sub1.method3434(Class98_Sub10_Sub1.aClass207_5544, false, n57, Class144.anInt1169, 0, -16523);
                            Class233.method2883((byte)111);
                            Class151_Sub5.aBoolean4991 = false;
                        }
                        else if (n57 == 0) {
                            Class337_Sub1.method3777(31585);
                            Class151_Sub5.aBoolean4991 = false;
                        }
                        else {
                            Class98_Sub10_Sub19.method1057(n57, 1024);
                        }
                    }
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n57, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4072);
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                }
                return;
            }
            if (n == 6039) {
                int n58 = Class247.anIntArray1878[--Class247.anInt1885];
                if (n58 > 255 || n58 < 0) {
                    n58 = 0;
                }
                if (n58 != Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub4_4053.method568((byte)125)) {
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n58, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub4_4053);
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                }
                return;
            }
            if (n == 6040) {
                final int n59 = Class247.anIntArray1878[--Class247.anInt1885];
                if (n59 != Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub18_4071.method627((byte)122)) {
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n59, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub18_4071);
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                    Class230.method2871(-58);
                }
                return;
            }
        }
        else if (n < 6200) {
            if (n == 6101) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub19_4057.method630((byte)125);
                return;
            }
            if (n == 6102) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4041.method564((byte)127) == 1) ? 1 : 0);
                return;
            }
            if (n == 6103) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4034.method612((byte)125) == 2) ? 1 : 0);
                return;
            }
            if (n == 6105) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038.method596((byte)125) == 1) ? 1 : 0);
                return;
            }
            if (n == 6107) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub24_4047.method651((byte)126);
                return;
            }
            if (n == 6108) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub13_4063.method602((byte)127) == 1) ? 1 : 0);
                return;
            }
            if (n == 6110) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035.method662((byte)120) == 1) ? 1 : 0);
                return;
            }
            if (n == 6111) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method579((byte)126);
                return;
            }
            if (n == 6112) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070.method592((byte)123) == 1) ? 1 : 0);
                return;
            }
            if (n == 6114) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064.method668((byte)125) == 2) ? 1 : 0);
                return;
            }
            if (n == 6115) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049.method609((byte)125) == 1) ? 1 : 0);
                return;
            }
            if (n == 6116) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055.method648((byte)125);
                return;
            }
            if (n == 6117) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub1_4043.method558((byte)120) == 1) ? 1 : 0);
                return;
            }
            if (n == 6118) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4066.method641((byte)126);
                return;
            }
            if (n == 6119) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4069.method641((byte)121);
                return;
            }
            if (n == 6120) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4051.method641((byte)120);
                return;
            }
            if (n == 6123) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub46_Sub13_Sub2.method1600((byte)(-47));
                return;
            }
            if (n == 6124) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4044.method648((byte)121);
                return;
            }
            if (n == 6125) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046.method617((byte)123);
                return;
            }
            if (n == 6127) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065.method570((byte)121) == 1) ? 1 : 0);
                return;
            }
            if (n == 6128) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub29_4050.method677((byte)122) == 1) ? 1 : 0);
                return;
            }
            if (n == 6129) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub24_4047.method651((byte)125);
                return;
            }
            if (n == 6130) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub25_4039.method655((byte)127) == 1) ? 1 : 0);
                return;
            }
            if (n == 6131) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)122);
                return;
            }
            if (n == 6132) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062.method583((byte)120);
                return;
            }
            if (n == 6133) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub43_Sub2.aClass88_5907.aBoolean682 && !Class98_Sub43_Sub2.aClass88_5907.aBoolean675) ? 1 : 0);
                return;
            }
            if (n == 6135) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub21_4037.method639((byte)121);
                return;
            }
            if (n == 6136) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056.method634((byte)122) == 1) ? 1 : 0);
                return;
            }
            if (n == 6138) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class66.method683((byte)(-80), 200, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)122));
                return;
            }
            if (n == 6139) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040.method614((byte)124);
                return;
            }
            if (n == 6142) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4054.method641((byte)127);
                return;
            }
            if (n == 6143) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4072.method641((byte)120);
                return;
            }
            if (n == 6144) {
                Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub46_Sub18.aBoolean6056 ? 1 : 0);
                return;
            }
            if (n == 6145) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub4_4053.method568((byte)121);
                return;
            }
            if (n == 6146) {
                Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub18_4071.method627((byte)127);
                return;
            }
            if (n == 6147) {
                Class247.anIntArray1878[Class247.anInt1885++] = ((Exception_Sub1.aClass98_Sub35_47.anInt4129 < 512 || Class98_Sub46_Sub18.aBoolean6056 || Class223.aBoolean1679) ? 1 : 0);
                return;
            }
            if (n == 6148) {
                Class247.anIntArray1878[Class247.anInt1885++] = (Class82.aBoolean626 ? 1 : 0);
                return;
            }
        }
        else if (n < 6300) {
            if (n == 6200) {
                Class247.anInt1885 -= 2;
                Class265.aShort1973 = (short)Class247.anIntArray1878[Class247.anInt1885];
                if (Class265.aShort1973 <= 0) {
                    Class265.aShort1973 = 256;
                }
                Class98_Sub43_Sub4.aShort5934 = (short)Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (Class98_Sub43_Sub4.aShort5934 <= 0) {
                    Class98_Sub43_Sub4.aShort5934 = 205;
                }
                return;
            }
            if (n == 6201) {
                Class247.anInt1885 -= 2;
                Class64_Sub19.aShort3692 = (short)Class247.anIntArray1878[Class247.anInt1885];
                if (Class64_Sub19.aShort3692 <= 0) {
                    Class64_Sub19.aShort3692 = 256;
                }
                Class135.aShort1057 = (short)Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (Class135.aShort1057 <= 0) {
                    Class135.aShort1057 = 320;
                }
                return;
            }
            if (n == 6202) {
                Class247.anInt1885 -= 4;
                Class284_Sub2_Sub2.aShort6201 = (short)Class247.anIntArray1878[Class247.anInt1885];
                if (Class284_Sub2_Sub2.aShort6201 <= 0) {
                    Class284_Sub2_Sub2.aShort6201 = 1;
                }
                Class112.aShort948 = (short)Class247.anIntArray1878[Class247.anInt1885 + 1];
                if (Class112.aShort948 <= 0) {
                    Class112.aShort948 = 32767;
                }
                else if (Class112.aShort948 < Class284_Sub2_Sub2.aShort6201) {
                    Class112.aShort948 = Class284_Sub2_Sub2.aShort6201;
                }
                Class42.aShort3231 = (short)Class247.anIntArray1878[Class247.anInt1885 + 2];
                if (Class42.aShort3231 <= 0) {
                    Class42.aShort3231 = 1;
                }
                Class260.aShort3256 = (short)Class247.anIntArray1878[Class247.anInt1885 + 3];
                if (Class260.aShort3256 <= 0) {
                    Class260.aShort3256 = 32767;
                    return;
                }
                if (Class260.aShort3256 < Class42.aShort3231) {
                    Class260.aShort3256 = Class42.aShort3231;
                }
                return;
            }
            else {
                if (n == 6203) {
                    Class151_Sub3.method2453(0, false, Class98_Sub32.aClass293_4107.anInt2311, 100, Class98_Sub32.aClass293_4107.anInt2258, 0);
                    Class247.anIntArray1878[Class247.anInt1885++] = Class215.anInt1612;
                    Class247.anIntArray1878[Class247.anInt1885++] = Class332_Sub2.anInt5421;
                    return;
                }
                if (n == 6204) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class64_Sub19.aShort3692;
                    Class247.anIntArray1878[Class247.anInt1885++] = Class135.aShort1057;
                    return;
                }
                if (n == 6205) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class265.aShort1973;
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub43_Sub4.aShort5934;
                    return;
                }
            }
        }
        else if (n < 6400) {
            if (n == 6300) {
                Class247.anIntArray1878[Class247.anInt1885++] = (int)(Class343.method3819(-47) / 60000L);
                return;
            }
            if (n == 6301) {
                Class247.anIntArray1878[Class247.anInt1885++] = (int)(Class343.method3819(-47) / 86400000L) - 11745;
                return;
            }
            if (n == 6302) {
                Class247.anInt1885 -= 3;
                final int n60 = Class247.anIntArray1878[Class247.anInt1885];
                final int n61 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                final int n62 = Class247.anIntArray1878[Class247.anInt1885 + 2];
                Class247.aCalendar1882.clear();
                Class247.aCalendar1882.set(11, 12);
                Class247.aCalendar1882.set(n62, n61, n60);
                int n63 = (int)(Class247.aCalendar1882.getTime().getTime() / 86400000L) - 11745;
                if (n62 < 1970) {
                    --n63;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = n63;
                return;
            }
            if (n == 6303) {
                Class247.aCalendar1882.clear();
                Class247.aCalendar1882.setTime(new Date(Class343.method3819(-47)));
                Class247.anIntArray1878[Class247.anInt1885++] = Class247.aCalendar1882.get(1);
                return;
            }
            if (n == 6304) {
                final int n64 = Class247.anIntArray1878[--Class247.anInt1885];
                boolean b7 = true;
                if (n64 < 0) {
                    b7 = ((n64 + 1) % 4 == 0);
                }
                else if (n64 < 1582) {
                    b7 = (n64 % 4 == 0);
                }
                else if (n64 % 4 != 0) {
                    b7 = false;
                }
                else if (n64 % 100 != 0) {
                    b7 = true;
                }
                else if (n64 % 400 != 0) {
                    b7 = false;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = (b7 ? 1 : 0);
                return;
            }
        }
        else if (n < 6500) {
            if (n == 6405) {
                Class247.anIntArray1878[Class247.anInt1885++] = (Class195.method2662(-96) ? 1 : 0);
                return;
            }
            if (n == 6406) {
                Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub10_Sub18.method1054(76) ? 1 : 0);
                return;
            }
        }
        else if (n < 6600) {
            if (n == 6500) {
                if (Class177.anInt1376 != 7 || Class64_Sub16.anInt3680 != 0 || Class21_Sub4.anInt5394 != 0) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 1;
                    return;
                }
                if (Class64_Sub12.aBoolean3671) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                if (Class267.aLong1998 > Class343.method3819(-47) - 1000L) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 1;
                    return;
                }
                Class64_Sub12.aBoolean3671 = true;
                final Class98_Sub11 method3069 = Class246_Sub3_Sub4.method3023(260, Class98_Sub32_Sub1.aClass171_5887, Class331.aClass117_2811);
                method3069.aClass98_Sub22_Sub1_3865.writeInt(1571862888, Class6.anInt88);
                Class98_Sub10_Sub29.sendPacket(false, method3069);
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                return;
            }
            else if (n == 6501) {
                final Class53_Sub1 method3070 = Class155.method2494((byte)101);
                if (method3070 != null) {
                    Class247.anIntArray1878[Class247.anInt1885++] = method3070.anInt3632;
                    Class247.anIntArray1878[Class247.anInt1885++] = method3070.anInt427;
                    Class247.aStringArray1883[Class247.anInt1884++] = method3070.aString3630;
                    final Class114 method3071 = method3070.method501(-1);
                    Class247.anIntArray1878[Class247.anInt1885++] = method3071.anInt956;
                    Class247.aStringArray1883[Class247.anInt1884++] = method3071.aString957;
                    Class247.anIntArray1878[Class247.anInt1885++] = method3070.anInt429;
                    Class247.anIntArray1878[Class247.anInt1885++] = method3070.anInt3631;
                    Class247.aStringArray1883[Class247.anInt1884++] = method3070.aString3634;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                return;
            }
            else if (n == 6502) {
                final Class53_Sub1 method3072 = Class69_Sub2.method706(200);
                if (method3072 != null) {
                    Class247.anIntArray1878[Class247.anInt1885++] = method3072.anInt3632;
                    Class247.anIntArray1878[Class247.anInt1885++] = method3072.anInt427;
                    Class247.aStringArray1883[Class247.anInt1884++] = method3072.aString3630;
                    final Class114 method3073 = method3072.method501(-1);
                    Class247.anIntArray1878[Class247.anInt1885++] = method3073.anInt956;
                    Class247.aStringArray1883[Class247.anInt1884++] = method3073.aString957;
                    Class247.anIntArray1878[Class247.anInt1885++] = method3072.anInt429;
                    Class247.anIntArray1878[Class247.anInt1885++] = method3072.anInt3631;
                    Class247.aStringArray1883[Class247.anInt1884++] = method3072.aString3634;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                return;
            }
            else if (n == 6503) {
                final int n65 = Class247.anIntArray1878[--Class247.anInt1885];
                final String s10 = Class247.aStringArray1883[--Class247.anInt1884];
                if (Class177.anInt1376 != 7 || Class64_Sub16.anInt3680 != 0 || Class21_Sub4.anInt5394 != 0) {
                    Class247.anIntArray1878[Class247.anInt1885++] = 0;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub12.method1131(-8804, n65, s10) ? 1 : 0);
                return;
            }
            else if (n == 6506) {
                final Class53_Sub1 method3074 = Class275.method3283((byte)123, Class247.anIntArray1878[--Class247.anInt1885]);
                if (method3074 != null) {
                    Class247.anIntArray1878[Class247.anInt1885++] = method3074.anInt427;
                    Class247.aStringArray1883[Class247.anInt1884++] = method3074.aString3630;
                    final Class114 method3075 = method3074.method501(-1);
                    Class247.anIntArray1878[Class247.anInt1885++] = method3075.anInt956;
                    Class247.aStringArray1883[Class247.anInt1884++] = method3075.aString957;
                    Class247.anIntArray1878[Class247.anInt1885++] = method3074.anInt429;
                    Class247.anIntArray1878[Class247.anInt1885++] = method3074.anInt3631;
                    Class247.aStringArray1883[Class247.anInt1884++] = method3074.aString3634;
                    return;
                }
                Class247.anIntArray1878[Class247.anInt1885++] = -1;
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.anIntArray1878[Class247.anInt1885++] = 0;
                Class247.aStringArray1883[Class247.anInt1884++] = "";
                return;
            }
            else {
                if (n == 6507) {
                    Class247.anInt1885 -= 4;
                    Class287_Sub2.method3393(Class247.anIntArray1878[Class247.anInt1885 + 3] == 1, Class247.anIntArray1878[Class247.anInt1885 + 2], Class247.anIntArray1878[Class247.anInt1885], (byte)82, Class247.anIntArray1878[Class247.anInt1885 + 1] == 1);
                    return;
                }
                if (n == 6508) {
                    Class98_Sub10_Sub25.method1080((byte)74);
                    return;
                }
                if (n == 6509) {
                    if (Class177.anInt1376 == 7) {
                        Class224_Sub3_Sub1.aBoolean6144 = (Class247.anIntArray1878[--Class247.anInt1885] == 1);
                    }
                    return;
                }
                else if (n == 6510) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub46.anInt4260;
                    return;
                }
            }
        }
        else if (n >= 6700) {
            if (n < 6800 && Class73.aClass6_3485 == Class244.aClass6_1861) {
                if (n == 6700) {
                    int method3076 = Class116.aClass377_964.method3999((byte)(-6));
                    if (Class15.anInt185 != -1) {
                        ++method3076;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = method3076;
                    return;
                }
                if (n == 6701) {
                    int n66 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (Class15.anInt185 != -1) {
                        if (n66 == 0) {
                            Class247.anIntArray1878[Class247.anInt1885++] = Class15.anInt185;
                            return;
                        }
                        --n66;
                    }
                    Class98_Sub18 class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3998(98);
                    while (n66-- > 0) {
                        class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3995(-1);
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = class98_Sub18.anInt3945;
                    return;
                }
                if (n == 6702) {
                    final int n67 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (Class159.aClass293ArrayArray1252[n67] == null) {
                        Class247.aStringArray1883[Class247.anInt1884++] = "";
                        return;
                    }
                    final String aString1050 = Class159.aClass293ArrayArray1252[n67][0].aString2231;
                    if (aString1050 == null) {
                        Class247.aStringArray1883[Class247.anInt1884++] = "";
                        return;
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = aString1050.substring(0, aString1050.indexOf(58));
                    return;
                }
                else if (n == 6703) {
                    final int n68 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (Class159.aClass293ArrayArray1252[n68] == null) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 0;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = Class159.aClass293ArrayArray1252[n68].length;
                    return;
                }
                else if (n == 6704) {
                    Class247.anInt1885 -= 2;
                    final int n69 = Class247.anIntArray1878[Class247.anInt1885];
                    final int n70 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    if (Class159.aClass293ArrayArray1252[n69] == null) {
                        Class247.aStringArray1883[Class247.anInt1884++] = "";
                        return;
                    }
                    final String aString1051 = Class159.aClass293ArrayArray1252[n69][n70].aString2231;
                    if (aString1051 == null) {
                        Class247.aStringArray1883[Class247.anInt1884++] = "";
                        return;
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = aString1051;
                    return;
                }
                else if (n == 6705) {
                    Class247.anInt1885 -= 2;
                    final int n71 = Class247.anIntArray1878[Class247.anInt1885];
                    final int n72 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    if (Class159.aClass293ArrayArray1252[n71] == null) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 0;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = Class159.aClass293ArrayArray1252[n71][n72].anInt2259;
                    return;
                }
                else {
                    if (n == 6706) {
                        return;
                    }
                    if (n == 6707) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(1, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -121, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6708) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(2, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -117, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6709) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(3, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -126, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6710) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(4, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -121, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6711) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(5, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -117, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6712) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(6, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -118, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6713) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(7, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -125, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6714) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(8, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -117, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6715) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(9, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -115, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6716) {
                        Class247.anInt1885 -= 3;
                        Class303.method3557(10, Class247.anIntArray1878[Class247.anInt1885 + 2], "", -126, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1]);
                        return;
                    }
                    if (n == 6717) {
                        Class247.anInt1885 -= 3;
                        final Class293 method3077 = Class246_Sub9.method3139((byte)72, Class247.anIntArray1878[Class247.anInt1885] << 16 | Class247.anIntArray1878[Class247.anInt1885 + 1], Class247.anIntArray1878[Class247.anInt1885 + 2]);
                        Class98_Sub10_Sub32.method1098((byte)117);
                        final Class98_Sub49 method3078 = client.method116(method3077);
                        Class98_Sub5_Sub2.method970(method3078.anInt4285, method3077, method3078.method1668(-1), -6838);
                        return;
                    }
                }
            }
            else if (n < 6900) {
                if (n == 6800) {
                    final Class24 method3079 = Class216.aClass341_1622.method3807(-114, Class247.anIntArray1878[--Class247.anInt1885]);
                    if (method3079.aString263 == null) {
                        Class247.aStringArray1883[Class247.anInt1884++] = "";
                        return;
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = method3079.aString263;
                    return;
                }
                else {
                    if (n == 6801) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class216.aClass341_1622.method3807(106, Class247.anIntArray1878[--Class247.anInt1885]).anInt245;
                        return;
                    }
                    if (n == 6802) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class216.aClass341_1622.method3807(-54, Class247.anIntArray1878[--Class247.anInt1885]).anInt264;
                        return;
                    }
                    if (n == 6803) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class216.aClass341_1622.method3807(-47, Class247.anIntArray1878[--Class247.anInt1885]).anInt246;
                        return;
                    }
                    if (n == 6804) {
                        Class247.anInt1885 -= 2;
                        final int n73 = Class247.anIntArray1878[Class247.anInt1885];
                        final int n74 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                        final Class149 method3080 = Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n74);
                        if (method3080.method2433(false)) {
                            Class247.aStringArray1883[Class247.anInt1884++] = Class216.aClass341_1622.method3807(105, n73).method289(-5911, method3080.aString1203, n74);
                            return;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = Class216.aClass341_1622.method3807(122, n73).method285(48, n74, method3080.anInt1202);
                        return;
                    }
                }
            }
            else if (n < 7000) {
                if (n == 6900) {
                    Class247.anIntArray1878[Class247.anInt1885++] = ((Class109.aBoolean933 && !Class98_Sub10_Sub35.aBoolean5732) ? 1 : 0);
                    return;
                }
                if (n == 6901) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class48.anInt409;
                    return;
                }
                if (n == 6902) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub1.anInt3814;
                    return;
                }
                if (n == 6903) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class93_Sub1.anInt5489;
                    return;
                }
                if (n == 6904) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub10_Sub19.anInt5630;
                    return;
                }
                if (n == 6905) {
                    String method3081 = "";
                    if (Class187.aClass143_1449 != null) {
                        if (Class187.aClass143_1449.anObject1162 != null) {
                            method3081 = (String)Class187.aClass143_1449.anObject1162;
                        }
                        else {
                            method3081 = Class98_Sub10_Sub39.method1122(Class187.aClass143_1449.anInt1166, (byte)(-63));
                        }
                    }
                    Class247.aStringArray1883[Class247.anInt1884++] = method3081;
                    return;
                }
                if (n == 6906) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class17.anInt203;
                    return;
                }
                if (n == 6907) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub46.anInt4264;
                    return;
                }
                if (n == 6908) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class93_Sub2.anInt5491;
                    return;
                }
                if (n == 6909) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (s_Sub1.aBoolean5200 ? 1 : 0);
                    return;
                }
                if (n == 6910) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub46_Sub9.anInt6003;
                    return;
                }
                if (n == 6911) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub43_Sub2.anInt5910;
                    return;
                }
                if (n == 6912) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class36.anInt349;
                    return;
                }
            }
            else if (n < 7100) {
                if (n == 7000) {
                    final int method3082 = Class98_Sub28.method1306((byte)(-106));
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub10_Sub24.anInt5671 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)120));
                    Class247.anIntArray1878[Class247.anInt1885++] = method3082;
                    Class98_Sub10.method999((byte)122);
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                    return;
                }
                if (n == 7001) {
                    Class98_Sub27.method1284(1);
                    Class98_Sub10.method999((byte)(-27));
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                    return;
                }
                if (n == 7002) {
                    Class98_Sub50.method1672((byte)19);
                    Class98_Sub10.method999((byte)113);
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                    return;
                }
                if (n == 7003) {
                    Class287.method3385((byte)27);
                    Class98_Sub10.method999((byte)(-126));
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                    return;
                }
                if (n == 7004) {
                    Class98_Sub32.method1436(-100, true);
                    Class98_Sub10.method999((byte)(-10));
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                    return;
                }
                if (n == 7005) {
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub12_4048);
                    Class310.method3618(-5964);
                    s_Sub1.aBoolean5207 = false;
                    return;
                }
                if (n == 7006) {
                    if (Class98_Sub10_Sub24.anInt5671 == 2) {
                        Class98_Sub10_Sub38.aBoolean5756 = true;
                        return;
                    }
                    if (Class98_Sub10_Sub24.anInt5671 == 1) {
                        Class95.aBoolean798 = true;
                        return;
                    }
                    if (Class98_Sub10_Sub24.anInt5671 == 3) {
                        Class67.aBoolean520 = true;
                    }
                    return;
                }
                else if (n == 7007) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub12_4048.method600((byte)123);
                    return;
                }
            }
            else if (n < 7200) {
                if (n == 7100) {
                    Class247.anInt1885 -= 2;
                    final int n75 = Class247.anIntArray1878[Class247.anInt1885];
                    int n76 = Class247.anIntArray1878[Class247.anInt1885 + 1];
                    if (n75 != -1) {
                        if (n76 > 255) {
                            n76 = 255;
                        }
                        else if (n76 < 0) {
                            n76 = 0;
                        }
                        Class98_Sub10_Sub30.method1093(-29680, n76, false, n75);
                    }
                    return;
                }
                if (n == 7101) {
                    final int n77 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (n77 != -1) {
                        Class98_Sub11.method1127((byte)67, n77);
                    }
                    return;
                }
                if (n == 7102) {
                    final int n78 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (n78 != -1) {
                        Class98_Sub42.method1476(256, n78);
                    }
                    return;
                }
                if (n == 7103) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub42.method1479("jagtheora", 0) ? 1 : 0);
                    return;
                }
            }
            else if (n < 7300) {
                if (n == 7201) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038.method597(-1) ? 1 : 0);
                    return;
                }
                if (n == 7202) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035.method661(-1) ? 1 : 0);
                    return;
                }
                if (n == 7203) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method581(-1) ? 1 : 0);
                    return;
                }
                if (n == 7204) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064.method671(-1) ? 1 : 0);
                    return;
                }
                if (n == 7205) {
                    Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4044.method647(-1) && Class265.aHa1974.method1823()) ? 1 : 0);
                    return;
                }
                if (n == 7206) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub6_4033.method575(-1) ? 1 : 0);
                    return;
                }
                if (n == 7207) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046.method621(-1) ? 1 : 0);
                    return;
                }
                if (n == 7208) {
                    Class247.anIntArray1878[Class247.anInt1885++] = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065.method571(-1) && Class265.aHa1974.method1767()) ? 1 : 0);
                    return;
                }
                if (n == 7209) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub25_4039.method657(-1) ? 1 : 0);
                    return;
                }
                if (n == 7210) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056.method635(-1) ? 1 : 0);
                    return;
                }
                if (n == 7211) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040.method613(-1) ? 1 : 0);
                    return;
                }
                if (n == 7212) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049.method607(-1) ? 1 : 0);
                    return;
                }
                if (n == 7213) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub18_4071.method626(-1) ? 1 : 0);
                    return;
                }
                if (n == 7214) {
                    Class247.anIntArray1878[Class247.anInt1885++] = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062.method587(-1) ? 1 : 0);
                    return;
                }
            }
            else if (n < 7400) {
                if (n == 7301) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                    return;
                }
                if (n == 7302) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                    return;
                }
                if (n == 7303) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                    return;
                }
                if (n == 7304) {
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                    return;
                }
                if (n == 7305) {
                    final int n79 = Class247.anIntArray1878[--Class247.anInt1885];
                    if (!Class265.aHa1974.method1823()) {
                        Class247.anIntArray1878[Class247.anInt1885++] = 3;
                        return;
                    }
                    Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4044.method556(n79, 29053);
                    return;
                }
                else {
                    if (n == 7306) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub6_4033.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                        return;
                    }
                    if (n == 7307) {
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                        return;
                    }
                    if (n == 7308) {
                        final int n80 = Class247.anIntArray1878[--Class247.anInt1885];
                        if (!Class265.aHa1974.method1767()) {
                            Class247.anIntArray1878[Class247.anInt1885++] = 3;
                            return;
                        }
                        Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065.method556(n80, 29053);
                        return;
                    }
                    else {
                        if (n == 7309) {
                            Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub25_4039.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                            return;
                        }
                        if (n == 7310) {
                            Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                            return;
                        }
                        if (n == 7311) {
                            Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                            return;
                        }
                        if (n == 7312) {
                            Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                            return;
                        }
                        if (n == 7313) {
                            Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub18_4071.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                            return;
                        }
                        if (n == 7314) {
                            Class247.anIntArray1878[Class247.anInt1885++] = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062.method556(Class247.anIntArray1878[--Class247.anInt1885], 29053);
                            return;
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.valueOf(n));
    }
    
    static {
        Class247.anIntArray1878 = new int[1000];
        Class247.anIntArrayArray1881 = new int[5][5000];
        Class247.aStringArray1883 = new String[1000];
        Class247.anInt1884 = 0;
        Class247.anInt1888 = 0;
        Class247.anIntArray1887 = new int[5];
        Class247.anInt1885 = 0;
        Class247.aClass349Array1889 = new Class349[50];
        Class247.aCalendar1882 = Calendar.getInstance();
        Class247.aStringArray1892 = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        Class247.anIntArray1891 = new int[3];
        Class247.aClass79_1890 = new Class79(4);
        Class247.anInt1893 = 0;
    }
}
