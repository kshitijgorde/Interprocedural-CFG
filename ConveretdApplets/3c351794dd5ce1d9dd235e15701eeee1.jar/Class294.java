// 
// Decompiled by Procyon v0.5.30
// 

final class Class294
{
    int anInt2357;
    private int anInt2358;
    int anInt2359;
    int anInt2360;
    int anInt2361;
    int anInt2362;
    int anInt2363;
    int[][] anIntArrayArray2364;
    int anInt2365;
    int[][] anIntArrayArray2366;
    private int anInt2367;
    int anInt2368;
    int anInt2369;
    int anInt2370;
    static int anInt2371;
    int anInt2372;
    int[] anIntArray2373;
    int anInt2374;
    int anInt2375;
    int anInt2376;
    int anInt2377;
    int anInt2378;
    int[] anIntArray2379;
    int anInt2380;
    int anInt2381;
    int anInt2382;
    int anInt2383;
    int anInt2384;
    int anInt2385;
    int[] anIntArray2386;
    private Class111[] aClass111Array2387;
    int anInt2388;
    int anInt2389;
    int anInt2390;
    int anInt2391;
    int anInt2392;
    int anInt2393;
    int anInt2394;
    int[] anIntArray2395;
    int anInt2396;
    static Class377 aClass377_2397;
    int anInt2398;
    int anInt2399;
    boolean aBoolean2400;
    int anInt2401;
    int anInt2402;
    int anInt2403;
    int anInt2404;
    int anInt2405;
    static int[] anIntArray2406;
    static int anInt2407;
    static int[] anIntArray2408;
    
    final void method3475(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            if (n == -22400) {
                while (true) {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-103));
                    if (~unsignedByte == -1) {
                        break;
                    }
                    this.method3476(class98_Sub22, unsignedByte, 91);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sc.F(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method3476(final Class98_Sub22 class98_Sub22, final int n, final int n2) {
        try {
            if (n2 > 76) {
                if (~n != 0xFFFFFFFE) {
                    if (n != 2) {
                        if (~n != 0xFFFFFFFC) {
                            if (~n != 0xFFFFFFFB) {
                                if (n != 5) {
                                    if (~n == 0xFFFFFFF9) {
                                        this.anInt2389 = class98_Sub22.readShort((byte)127);
                                    }
                                    else if (~n == 0xFFFFFFF8) {
                                        this.anInt2361 = class98_Sub22.readShort((byte)127);
                                    }
                                    else if (~n != 0xFFFFFFF7) {
                                        if (~n != 0xFFFFFFF6) {
                                            if (n == 26) {
                                                this.anInt2362 = (short)(class98_Sub22.readUnsignedByte((byte)(-101)) * 4);
                                                this.anInt2382 = (short)(class98_Sub22.readUnsignedByte((byte)(-126)) * 4);
                                            }
                                            else if (n == 27) {
                                                if (this.anIntArrayArray2366 == null) {
                                                    this.anIntArrayArray2366 = new int[12][];
                                                }
                                                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)28);
                                                this.anIntArrayArray2366[unsignedByte] = new int[6];
                                                for (int i = 0; i < 6; ++i) {
                                                    this.anIntArrayArray2366[unsignedByte][i] = class98_Sub22.readUShort(false);
                                                }
                                            }
                                            else if (n == 28) {
                                                this.anIntArray2379 = new int[12];
                                                for (int j = 0; j < 12; ++j) {
                                                    this.anIntArray2379[j] = class98_Sub22.readUnsignedByte((byte)101);
                                                    if (~this.anIntArray2379[j] == 0xFFFFFF00) {
                                                        this.anIntArray2379[j] = -1;
                                                    }
                                                }
                                            }
                                            else if (n == 29) {
                                                this.anInt2398 = class98_Sub22.readUnsignedByte((byte)(-124));
                                            }
                                            else if (~n != 0xFFFFFFE1) {
                                                if (n == 31) {
                                                    this.anInt2390 = class98_Sub22.readUnsignedByte((byte)(-116));
                                                }
                                                else if (n != 32) {
                                                    if (n == 33) {
                                                        this.anInt2393 = class98_Sub22.readUShort(false);
                                                    }
                                                    else if (n != 34) {
                                                        if (n != 35) {
                                                            if (~n != 0xFFFFFFDB) {
                                                                if (~n == 0xFFFFFFDA) {
                                                                    this.anInt2401 = class98_Sub22.readUnsignedByte((byte)2);
                                                                }
                                                                else if (n == 38) {
                                                                    this.anInt2376 = class98_Sub22.readShort((byte)127);
                                                                }
                                                                else if (~n != 0xFFFFFFD8) {
                                                                    if (n != 40) {
                                                                        if (~n != 0xFFFFFFD6) {
                                                                            if (n != 42) {
                                                                                if (n != 43) {
                                                                                    if (n == 44) {
                                                                                        this.anInt2374 = class98_Sub22.readShort((byte)127);
                                                                                    }
                                                                                    else if (~n == 0xFFFFFFD2) {
                                                                                        this.anInt2385 = class98_Sub22.readShort((byte)127);
                                                                                    }
                                                                                    else if (~n != 0xFFFFFFD1) {
                                                                                        if (~n != 0xFFFFFFD0) {
                                                                                            if (n == 48) {
                                                                                                this.anInt2384 = class98_Sub22.readShort((byte)127);
                                                                                            }
                                                                                            else if (~n == 0xFFFFFFCE) {
                                                                                                this.anInt2370 = class98_Sub22.readShort((byte)127);
                                                                                            }
                                                                                            else if (n == 50) {
                                                                                                this.anInt2378 = class98_Sub22.readShort((byte)127);
                                                                                            }
                                                                                            else if (n != 51) {
                                                                                                if (n != 52) {
                                                                                                    if (~n == 0xFFFFFFCA) {
                                                                                                        this.aBoolean2400 = false;
                                                                                                    }
                                                                                                    else if (~n == 0xFFFFFFC9) {
                                                                                                        this.anInt2360 = class98_Sub22.readUnsignedByte((byte)77) << -1514864954;
                                                                                                        this.anInt2391 = class98_Sub22.readUnsignedByte((byte)(-121)) << 666953990;
                                                                                                    }
                                                                                                    else if (~n == 0xFFFFFFC8) {
                                                                                                        if (this.anIntArray2373 == null) {
                                                                                                            this.anIntArray2373 = new int[12];
                                                                                                        }
                                                                                                        this.anIntArray2373[class98_Sub22.readUnsignedByte((byte)121)] = class98_Sub22.readShort((byte)127);
                                                                                                    }
                                                                                                    else if (n == 56) {
                                                                                                        if (this.anIntArrayArray2364 == null) {
                                                                                                            this.anIntArrayArray2364 = new int[12][];
                                                                                                        }
                                                                                                        final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)92);
                                                                                                        this.anIntArrayArray2364[unsignedByte2] = new int[3];
                                                                                                        for (int n3 = 0; ~n3 > -4; ++n3) {
                                                                                                            this.anIntArrayArray2364[unsignedByte2][n3] = class98_Sub22.readUShort(false);
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                else {
                                                                                                    final int k = class98_Sub22.readUnsignedByte((byte)106);
                                                                                                    this.anIntArray2395 = new int[k];
                                                                                                    this.anIntArray2386 = new int[k];
                                                                                                    for (int n4 = 0; k > n4; ++n4) {
                                                                                                        this.anIntArray2395[n4] = class98_Sub22.readShort((byte)127);
                                                                                                        final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)(-113));
                                                                                                        this.anIntArray2386[n4] = unsignedByte3;
                                                                                                        this.anInt2367 += unsignedByte3;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            else {
                                                                                                this.anInt2369 = class98_Sub22.readShort((byte)127);
                                                                                            }
                                                                                        }
                                                                                        else {
                                                                                            this.anInt2404 = class98_Sub22.readShort((byte)127);
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                        this.anInt2405 = class98_Sub22.readShort((byte)127);
                                                                                    }
                                                                                }
                                                                                else {
                                                                                    this.anInt2381 = class98_Sub22.readShort((byte)127);
                                                                                }
                                                                            }
                                                                            else {
                                                                                this.anInt2372 = class98_Sub22.readShort((byte)127);
                                                                            }
                                                                        }
                                                                        else {
                                                                            this.anInt2359 = class98_Sub22.readShort((byte)127);
                                                                        }
                                                                    }
                                                                    else {
                                                                        this.anInt2365 = class98_Sub22.readShort((byte)127);
                                                                    }
                                                                }
                                                                else {
                                                                    this.anInt2388 = class98_Sub22.readShort((byte)127);
                                                                }
                                                            }
                                                            else {
                                                                this.anInt2363 = class98_Sub22.readUShort(false);
                                                            }
                                                        }
                                                        else {
                                                            this.anInt2380 = class98_Sub22.readShort((byte)127);
                                                        }
                                                    }
                                                    else {
                                                        this.anInt2375 = class98_Sub22.readUnsignedByte((byte)10);
                                                    }
                                                }
                                                else {
                                                    this.anInt2392 = class98_Sub22.readShort((byte)127);
                                                }
                                            }
                                            else {
                                                this.anInt2383 = class98_Sub22.readShort((byte)127);
                                            }
                                        }
                                        else {
                                            this.anInt2402 = class98_Sub22.readShort((byte)127);
                                        }
                                    }
                                    else {
                                        this.anInt2357 = class98_Sub22.readShort((byte)127);
                                    }
                                }
                                else {
                                    this.anInt2403 = class98_Sub22.readShort((byte)127);
                                }
                            }
                            else {
                                this.anInt2377 = class98_Sub22.readShort((byte)127);
                            }
                        }
                        else {
                            this.anInt2394 = class98_Sub22.readShort((byte)127);
                        }
                    }
                    else {
                        this.anInt2368 = class98_Sub22.readShort((byte)127);
                    }
                }
                else {
                    this.anInt2396 = class98_Sub22.readShort((byte)127);
                    this.anInt2399 = class98_Sub22.readShort((byte)127);
                    if (this.anInt2396 == 65535) {
                        this.anInt2396 = -1;
                    }
                    if (~this.anInt2399 == 0xFFFF0000) {
                        this.anInt2399 = -1;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sc.H(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method3477(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            return ~(Class281.aByteArrayArrayArray2117[0][n3][n] & 0x2) != -1 || ((Class281.aByteArrayArrayArray2117[n2][n3][n] & 0x10) == 0x0 && Class98_Sub31_Sub4.method1390(n, n2, n3, -8941) == n4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sc.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final int method3478(final int n) {
        try {
            if (~this.anInt2396 != 0x0) {
                return this.anInt2396;
            }
            if (this.anIntArray2395 != null) {
                int n2;
                int n3;
                for (n2 = (int)(Math.random() * this.anInt2367), n3 = 0; this.anIntArray2386[n3] <= n2; n2 -= this.anIntArray2386[n3], ++n3) {}
                return this.anIntArray2395[n3];
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sc.D(" + n + ')');
        }
    }
    
    static final void method3479(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            final Class36[] aClass36Array903 = Class104.aClass36Array903;
            int n8 = 0;
            if (n3 == -7957) {
                while (~aClass36Array903.length < ~n8) {
                    final Class36 class36 = aClass36Array903[n8];
                    if (class36 != null && class36.anInt346 == 2) {
                        Class42_Sub1.method385(n7, class36.anInt342, class36.anInt338, n3 + 7956, n, 2 * class36.anInt343, class36.anInt347, n4 >> 1378251745, n2 >> 830958817);
                        if (~Class259.anIntArray1957[0] < 0 && Class215.anInt1614 % 20 < 10) {
                            final Class332 class37 = Class306.aClass332Array2557[class36.anInt341];
                            final int n9 = -12 + (Class259.anIntArray1957[0] + n5);
                            final int n10 = -28 + (n6 + Class259.anIntArray1957[1]);
                            class37.method3735(n9, n10);
                            Class93_Sub1_Sub1.method908(n10 - -class37.method3749(), n10, false, n9, n9 - -class37.method3737());
                        }
                    }
                    ++n8;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sc.E(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    final boolean method3480(final byte b, final int n) {
        try {
            if (~n == 0x0) {
                return false;
            }
            if (~this.anInt2396 == ~n) {
                return true;
            }
            if (this.anIntArray2395 != null) {
                for (int n2 = 0; this.anIntArray2395.length > n2; ++n2) {
                    if (~this.anIntArray2395[n2] == ~n) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sc.G(" + b + ',' + n + ')');
        }
    }
    
    final Class111[] method3481(final ha ha, final byte b) {
        try {
            if (this.aClass111Array2387 != null && ha.anInt937 == this.anInt2358) {
                return this.aClass111Array2387;
            }
            if (this.anIntArrayArray2366 == null) {
                return null;
            }
            this.aClass111Array2387 = new Class111[this.anIntArrayArray2366.length];
            for (int n = 0; ~this.anIntArrayArray2366.length < ~n; ++n) {
                int n2 = 0;
                int n3 = 0;
                int n4 = 0;
                int n5 = 0;
                int n6 = 0;
                int n7 = 0;
                if (this.anIntArrayArray2366[n] != null) {
                    n5 = this.anIntArrayArray2366[n][3] << 1842507107;
                    n4 = this.anIntArrayArray2366[n][2];
                    n7 = this.anIntArrayArray2366[n][5] << 813035747;
                    n6 = this.anIntArrayArray2366[n][4] << 1803526819;
                    n3 = this.anIntArrayArray2366[n][1];
                    n2 = this.anIntArrayArray2366[n][0];
                }
                if (~n2 != -1 || ~n3 != -1 || ~n4 != -1 || ~n5 != -1 || n6 != 0 || ~n7 != -1) {
                    final Class111[] aClass111Array2387 = this.aClass111Array2387;
                    final int n8 = n;
                    final Class111 method1821 = ha.method1821();
                    aClass111Array2387[n8] = method1821;
                    final Class111 class111 = method1821;
                    if (n7 != 0) {
                        class111.method2090(n7);
                    }
                    if (~n5 != -1) {
                        class111.method2105(n5);
                    }
                    if (n6 != 0) {
                        class111.method2097(n6);
                    }
                    class111.method2106(n2, n3, n4);
                }
            }
            return this.aClass111Array2387;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sc.C(" + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method3482(final int n) {
        try {
            if (n == -10494) {
                Class294.anIntArray2408 = null;
                Class294.aClass377_2397 = null;
                Class294.anIntArray2406 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sc.A(" + n + ')');
        }
    }
    
    public Class294() {
        this.anInt2358 = -1;
        this.anInt2361 = -1;
        this.anInt2359 = -1;
        this.anInt2362 = 0;
        this.anInt2368 = -1;
        this.anInt2370 = -1;
        this.anInt2357 = -1;
        this.anInt2380 = 0;
        this.anInt2360 = 0;
        this.anInt2374 = -1;
        this.anInt2372 = -1;
        this.anIntArray2386 = null;
        this.anInt2376 = -1;
        this.anInt2378 = -1;
        this.anInt2382 = 0;
        this.anInt2375 = 0;
        this.anInt2369 = -1;
        this.anInt2383 = 0;
        this.anInt2367 = 0;
        this.anInt2381 = -1;
        this.anInt2377 = -1;
        this.anInt2363 = 0;
        this.anInt2384 = -1;
        this.anInt2365 = -1;
        this.anInt2388 = -1;
        this.anInt2389 = -1;
        this.anInt2396 = -1;
        this.anInt2385 = -1;
        this.anInt2391 = 0;
        this.anInt2393 = 0;
        this.anInt2394 = -1;
        this.anInt2390 = 0;
        this.anInt2399 = -1;
        this.anIntArray2395 = null;
        this.anInt2392 = 0;
        this.anInt2398 = 0;
        this.anInt2401 = -1;
        this.anInt2404 = -1;
        this.aBoolean2400 = true;
        this.anInt2403 = -1;
        this.anInt2405 = -1;
        this.anInt2402 = -1;
    }
    
    static {
        Class294.anInt2371 = 1405;
        Class294.aClass377_2397 = new Class377(16);
        Class294.anIntArray2408 = new int[1000];
    }
}
