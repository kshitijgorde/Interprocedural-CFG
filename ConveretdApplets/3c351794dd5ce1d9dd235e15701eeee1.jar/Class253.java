// 
// Decompiled by Procyon v0.5.30
// 

final class Class253 implements Runnable
{
    static int[] anIntArray1928;
    static int anInt1929;
    static boolean aBoolean1930;
    static int[] anIntArray1931;
    static Class207 aClass207_1932;
    private Class215 aClass215_1933;
    static int anInt1934;
    int anInt1935;
    private boolean aBoolean1936;
    private Thread aThread1937;
    
    static final boolean method3175(final int n, final int n2, final Class172[][][] array, final int n3, final byte b, final boolean b2) {
        try {
            final byte b3 = (byte)(b2 ? 1 : ((byte)(Class64_Sub15.anInt3676 & 0xFF)));
            if (OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n][n3] == b3) {
                return false;
            }
            if ((0x4 & Class281.aByteArrayArrayArray2117[Class43.anInt377][n][n3]) == 0x0) {
                return false;
            }
            if (b != -76) {
                method3180(-52, -17, -126, null, false);
            }
            int n4 = 0;
            int n5 = 0;
            Class213.anIntArray1610[n4] = n;
            Class69.anIntArray3220[n4++] = n3;
            OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n][n3] = b3;
            while (~n4 != ~n5) {
                final int n6 = Class213.anIntArray1610[n5] & 0xFFFF;
                final int n7 = Class213.anIntArray1610[n5] >> 72865712 & 0xFF;
                final int n8 = Class213.anIntArray1610[n5] >> 1855655704 & 0xFF;
                int n9 = 0xFFFF & Class69.anIntArray3220[n5];
                final int n10 = (Class69.anIntArray3220[n5] & 0xFF7A19) >> -1279562416;
                n5 = (1 + n5 & 0xFFF);
                boolean b4 = false;
                if ((0x4 & Class281.aByteArrayArrayArray2117[Class43.anInt377][n6][n9]) == 0x0) {
                    b4 = true;
                }
                boolean b5 = false;
                if (array != null) {
                Label_0870:
                    for (int n11 = Class43.anInt377 + 1; ~n11 >= -4; ++n11) {
                        if (array[n11] != null && ~(0x8 & Class281.aByteArrayArrayArray2117[n11][n6][n9]) == -1) {
                            if (b4 && array[n11][n6][n9] != null) {
                                if (array[n11][n6][n9].aClass246_Sub3_Sub3_1324 != null) {
                                    final int method561 = Class64_Sub2.method561((byte)(-95), n7);
                                    if (array[n11][n6][n9].aClass246_Sub3_Sub3_1324.aShort6153 == method561) {
                                        continue;
                                    }
                                    if (array[n11][n6][n9].aClass246_Sub3_Sub3_1333 != null && method561 == array[n11][n6][n9].aClass246_Sub3_Sub3_1333.aShort6153) {
                                        continue;
                                    }
                                    if (~n8 != -1) {
                                        final int method562 = Class64_Sub2.method561((byte)(-95), n8);
                                        if (~method562 == ~array[n11][n6][n9].aClass246_Sub3_Sub3_1324.aShort6153) {
                                            continue;
                                        }
                                        if (array[n11][n6][n9].aClass246_Sub3_Sub3_1333 != null && ~array[n11][n6][n9].aClass246_Sub3_Sub3_1333.aShort6153 == ~method562) {
                                            continue;
                                        }
                                    }
                                    if (n10 != 0) {
                                        final int method563 = Class64_Sub2.method561((byte)(-95), n10);
                                        if (method563 == array[n11][n6][n9].aClass246_Sub3_Sub3_1324.aShort6153) {
                                            continue;
                                        }
                                        if (array[n11][n6][n9].aClass246_Sub3_Sub3_1333 != null && ~method563 == ~array[n11][n6][n9].aClass246_Sub3_Sub3_1333.aShort6153) {
                                            continue;
                                        }
                                    }
                                }
                                final Class172 class172 = array[n11][n6][n9];
                                if (class172.aClass154_1325 != null) {
                                    for (Class154 class173 = class172.aClass154_1325; class173 != null; class173 = class173.aClass154_1233) {
                                        final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232 = class173.aClass246_Sub3_Sub4_1232;
                                        if (aClass246_Sub3_Sub4_1232 instanceof Interface19) {
                                            final Interface19 interface19 = (Interface19)aClass246_Sub3_Sub4_1232;
                                            int method564 = interface19.method63((byte)20);
                                            final int method565 = interface19.method66(b + 4733);
                                            if (~method564 == 0xFFFFFFEA) {
                                                method564 = 19;
                                            }
                                            final int n12 = method564 | method565 << 1030705638;
                                            if (n7 == n12 || (~n8 != -1 && ~n12 == ~n8)) {
                                                continue Label_0870;
                                            }
                                            if (~n10 != -1 && ~n12 == ~n10) {
                                                continue Label_0870;
                                            }
                                        }
                                    }
                                }
                            }
                            final Class172 class174 = array[n11][n6][n9];
                            if (class174 != null && class174.aClass154_1325 != null) {
                                for (Class154 class175 = class174.aClass154_1325; class175 != null; class175 = class175.aClass154_1233) {
                                    final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1233 = class175.aClass246_Sub3_Sub4_1232;
                                    if (aClass246_Sub3_Sub4_1233.aShort6158 != aClass246_Sub3_Sub4_1233.aShort6160 || ~aClass246_Sub3_Sub4_1233.aShort6159 != ~aClass246_Sub3_Sub4_1233.aShort6157) {
                                        for (int aShort6158 = aClass246_Sub3_Sub4_1233.aShort6158; ~aClass246_Sub3_Sub4_1233.aShort6160 <= ~aShort6158; ++aShort6158) {
                                            for (int aShort6159 = aClass246_Sub3_Sub4_1233.aShort6157; ~aShort6159 >= ~aClass246_Sub3_Sub4_1233.aShort6159; ++aShort6159) {
                                                OutputStream_Sub2.aByteArrayArrayArray41[n11][aShort6158][aShort6159] = b3;
                                            }
                                        }
                                    }
                                }
                            }
                            OutputStream_Sub2.aByteArrayArrayArray41[n11][n6][n9] = b3;
                            b5 = true;
                        }
                    }
                }
                if (b5) {
                    final int method566 = Class78.aSArray594[Class43.anInt377 + 1].method3420(n9, -12639, n6);
                    if (~Class204.anIntArray1551[n2] > ~method566) {
                        Class204.anIntArray1551[n2] = method566;
                    }
                    final int n13 = n6 << -999054935;
                    final int n14 = n9 << -405497975;
                    if (~Class336.anIntArray2826[n2] >= ~n13) {
                        if (n13 > Class287.anIntArray2195[n2]) {
                            Class287.anIntArray2195[n2] = n13;
                        }
                    }
                    else {
                        Class336.anIntArray2826[n2] = n13;
                    }
                    if (Class48_Sub1_Sub2.anIntArray5518[n2] > n14) {
                        Class48_Sub1_Sub2.anIntArray5518[n2] = n14;
                    }
                    else if (Class295.anIntArray2409[n2] < n14) {
                        Class295.anIntArray2409[n2] = n14;
                    }
                }
                if (!b4) {
                    if (n6 >= 1 && OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n6 - 1][n9] != b3) {
                        Class213.anIntArray1610[n4] = Class41.method366(-754974720, Class41.method366(n6 - 1, 1179648));
                        Class69.anIntArray3220[n4] = Class41.method366(1245184, n9);
                        n4 = (0xFFF & n4 + 1);
                        OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][-1 + n6][n9] = b3;
                    }
                    if (~(++n9) > ~Class98_Sub10_Sub7.anInt5572) {
                        if (n6 - 1 >= 0 && b3 != OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n6 - 1][n9] && ~(Class281.aByteArrayArrayArray2117[Class43.anInt377][n6][n9] & 0x4) == -1 && (0x4 & Class281.aByteArrayArrayArray2117[Class43.anInt377][n6 - 1][n9 - 1]) == 0x0) {
                            Class213.anIntArray1610[n4] = Class41.method366(Class41.method366(1179648, n6 - 1), 1375731712);
                            Class69.anIntArray3220[n4] = Class41.method366(n9, 1245184);
                            OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][-1 + n6][n9] = b3;
                            n4 = (0xFFF & n4 + 1);
                        }
                        if (~OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n6][n9] != ~b3) {
                            Class213.anIntArray1610[n4] = Class41.method366(Class41.method366(5373952, n6), 318767104);
                            Class69.anIntArray3220[n4] = Class41.method366(5439488, n9);
                            OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n6][n9] = b3;
                            n4 = (0xFFF & n4 + 1);
                        }
                        if (Class165.anInt1276 > n6 + 1 && b3 != OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][1 + n6][n9] && ~(Class281.aByteArrayArrayArray2117[Class43.anInt377][n6][n9] & 0x4) == -1 && (0x4 & Class281.aByteArrayArrayArray2117[Class43.anInt377][n6 + 1][-1 + n9]) == 0x0) {
                            Class213.anIntArray1610[n4] = Class41.method366(Class41.method366(1 + n6, 5373952), -1845493760);
                            Class69.anIntArray3220[n4] = Class41.method366(5439488, n9);
                            OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][1 + n6][n9] = b3;
                            n4 = (1 + n4 & 0xFFF);
                        }
                    }
                    --n9;
                    if (n6 + 1 < Class165.anInt1276 && ~OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][1 + n6][n9] != ~b3) {
                        Class213.anIntArray1610[n4] = Class41.method366(Class41.method366(1 + n6, 9568256), 1392508928);
                        Class69.anIntArray3220[n4] = Class41.method366(9633792, n9);
                        n4 = (0xFFF & 1 + n4);
                        OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n6 + 1][n9] = b3;
                    }
                    if (~(--n9) > -1) {
                        continue;
                    }
                    if (~(-1 + n6) <= -1 && ~OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][-1 + n6][n9] != ~b3 && (Class281.aByteArrayArrayArray2117[Class43.anInt377][n6][n9] & 0x4) == 0x0 && (0x4 & Class281.aByteArrayArrayArray2117[Class43.anInt377][-1 + n6][n9 + 1]) == 0x0) {
                        Class213.anIntArray1610[n4] = Class41.method366(Class41.method366(13762560, -1 + n6), 301989888);
                        Class69.anIntArray3220[n4] = Class41.method366(13828096, n9);
                        OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][-1 + n6][n9] = b3;
                        n4 = (0xFFF & 1 + n4);
                    }
                    if (~OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n6][n9] != ~b3) {
                        Class213.anIntArray1610[n4] = Class41.method366(Class41.method366(n6, 13762560), -1828716544);
                        Class69.anIntArray3220[n4] = Class41.method366(13828096, n9);
                        n4 = (0xFFF & n4 + 1);
                        OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n6][n9] = b3;
                    }
                    if (1 + n6 >= Class165.anInt1276 || b3 == OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][1 + n6][n9] || ~(Class281.aByteArrayArrayArray2117[Class43.anInt377][n6][n9] & 0x4) != -1 || ~(Class281.aByteArrayArrayArray2117[Class43.anInt377][n6 + 1][1 + n9] & 0x4) != -1) {
                        continue;
                    }
                    Class213.anIntArray1610[n4] = Class41.method366(-771751936, Class41.method366(n6 + 1, 9568256));
                    Class69.anIntArray3220[n4] = Class41.method366(n9, 9633792);
                    n4 = (n4 + 1 & 0xFFF);
                    OutputStream_Sub2.aByteArrayArrayArray41[Class43.anInt377][n6 + 1][n9] = b3;
                }
            }
            if (Class204.anIntArray1551[n2] != -1000000) {
                final int[] anIntArray1551 = Class204.anIntArray1551;
                anIntArray1551[n2] += 40;
                final int[] anIntArray1552 = Class336.anIntArray2826;
                anIntArray1552[n2] -= 512;
                final int[] anIntArray1553 = Class287.anIntArray2195;
                anIntArray1553[n2] += 512;
                final int[] anIntArray1554 = Class295.anIntArray2409;
                anIntArray1554[n2] += 512;
                final int[] anIntArray1555 = Class48_Sub1_Sub2.anIntArray5518;
                anIntArray1555[n2] -= 512;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.F(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + b + ',' + b2 + ')');
        }
    }
    
    final Class98_Sub46_Sub13_Sub2 method3176(final byte b, final int n, final Class17 aClass17_6312) {
        try {
            final Class98_Sub46_Sub13_Sub2 class98_Sub46_Sub13_Sub2 = new Class98_Sub46_Sub13_Sub2();
            class98_Sub46_Sub13_Sub2.aLong4259 = n;
            class98_Sub46_Sub13_Sub2.anInt6310 = 3;
            class98_Sub46_Sub13_Sub2.aClass17_6312 = aClass17_6312;
            class98_Sub46_Sub13_Sub2.aBoolean6037 = false;
            this.method3182(-12972, class98_Sub46_Sub13_Sub2);
            return class98_Sub46_Sub13_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.I(" + b + ',' + n + ',' + ((aClass17_6312 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class246_Sub3_Sub1 method3177(final int n, final int n2, final int n3) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 == null || class172.aClass246_Sub3_Sub1_1332 == null) {
            return null;
        }
        return class172.aClass246_Sub3_Sub1_1332;
    }
    
    public static void method3178(final byte b) {
        try {
            Class253.anIntArray1928 = null;
            if (b == 118) {
                Class253.anIntArray1931 = null;
                Class253.aClass207_1932 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.H(" + b + ')');
        }
    }
    
    final Class98_Sub46_Sub13_Sub2 method3179(final byte[] aByteArray6313, final byte b, final Class17 aClass17_6312, final int n) {
        try {
            if (b <= 14) {
                return null;
            }
            final Class98_Sub46_Sub13_Sub2 class98_Sub46_Sub13_Sub2 = new Class98_Sub46_Sub13_Sub2();
            class98_Sub46_Sub13_Sub2.aLong4259 = n;
            class98_Sub46_Sub13_Sub2.aClass17_6312 = aClass17_6312;
            class98_Sub46_Sub13_Sub2.aByteArray6313 = aByteArray6313;
            class98_Sub46_Sub13_Sub2.aBoolean6037 = false;
            class98_Sub46_Sub13_Sub2.anInt6310 = 2;
            this.method3182(-12972, class98_Sub46_Sub13_Sub2);
            return class98_Sub46_Sub13_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.D(" + ((aByteArray6313 != null) ? "{...}" : "null") + ',' + b + ',' + ((aClass17_6312 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method3180(final int n, final int n2, final int n3, final Class293 class293, final boolean b) {
        try {
            final int anInt2311 = class293.anInt2311;
            if (~class293.aByte2243 != -1) {
                if (class293.aByte2243 != 1) {
                    if (~class293.aByte2243 == 0xFFFFFFFD) {
                        class293.anInt2311 = n2 * class293.anInt2235 >> 564972174;
                    }
                }
                else {
                    class293.anInt2311 = n2 + -class293.anInt2235;
                }
            }
            else {
                class293.anInt2311 = class293.anInt2235;
            }
            final int anInt2312 = class293.anInt2258;
            if (class293.aByte2207 == 0) {
                class293.anInt2258 = class293.anInt2242;
            }
            else if (class293.aByte2207 != 1) {
                if (~class293.aByte2207 == 0xFFFFFFFD) {
                    class293.anInt2258 = n * class293.anInt2242 >> 221623758;
                }
            }
            else {
                class293.anInt2258 = n - class293.anInt2242;
            }
            if (~class293.aByte2243 == 0xFFFFFFFB) {
                class293.anInt2311 = class293.anInt2321 * class293.anInt2258 / class293.anInt2338;
            }
            if (class293.aByte2207 == 4) {
                class293.anInt2258 = class293.anInt2311 * class293.anInt2338 / class293.anInt2321;
            }
            if (Class15.aBoolean169 && (client.method116(class293).anInt4284 != 0 || class293.anInt2354 == 0)) {
                if (class293.anInt2258 >= 5 || ~class293.anInt2311 <= -6) {
                    if (class293.anInt2258 <= 0) {
                        class293.anInt2258 = 5;
                    }
                    if (class293.anInt2311 <= 0) {
                        class293.anInt2311 = 5;
                    }
                }
                else {
                    class293.anInt2311 = 5;
                    class293.anInt2258 = 5;
                }
            }
            if (Class22.anInt218 == class293.anInt2307) {
                Class98_Sub32.aClass293_4107 = class293;
            }
            if (b && class293.anObjectArray2266 != null && (class293.anInt2311 != anInt2311 || ~class293.anInt2258 != ~anInt2312)) {
                final Class98_Sub21 class98_Sub21 = new Class98_Sub21();
                class98_Sub21.aClass293_3986 = class293;
                class98_Sub21.anObjectArray3981 = class293.anObjectArray2266;
                Class151_Sub3.aClass148_4977.method2419(class98_Sub21, -20911);
            }
            if (n3 != 1375731712) {
                Class253.anInt1934 = -87;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.G(" + n + ',' + n2 + ',' + n3 + ',' + ((class293 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method3181(final Class207 aClass207_4528, final int n) {
        try {
            ha_Sub3.aClass207_4528 = aClass207_4528;
            if (n != -1) {
                Class253.anInt1929 = 127;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.B(" + ((aClass207_4528 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method3182(final int n, final Class98_Sub46_Sub13_Sub2 class98_Sub46_Sub13_Sub2) {
        try {
            synchronized (this.aClass215_1933) {
                this.aClass215_1933.method2785(class98_Sub46_Sub13_Sub2, -108);
                ++this.anInt1935;
                this.aClass215_1933.notifyAll();
                if (n != -12972) {
                    Class253.anIntArray1931 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.C(" + n + ',' + ((class98_Sub46_Sub13_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void run() {
        try {
            while (!this.aBoolean1936) {
                final Class98_Sub46_Sub13_Sub2 class98_Sub46_Sub13_Sub2;
                synchronized (this.aClass215_1933) {
                    class98_Sub46_Sub13_Sub2 = (Class98_Sub46_Sub13_Sub2)this.aClass215_1933.method2789(-16711936);
                    if (class98_Sub46_Sub13_Sub2 == null) {
                        try {
                            this.aClass215_1933.wait();
                        }
                        catch (InterruptedException ex3) {}
                        continue;
                    }
                    --this.anInt1935;
                }
                try {
                    if (~class98_Sub46_Sub13_Sub2.anInt6310 != 0xFFFFFFFD) {
                        if (class98_Sub46_Sub13_Sub2.anInt6310 == 3) {
                            class98_Sub46_Sub13_Sub2.aByteArray6313 = class98_Sub46_Sub13_Sub2.aClass17_6312.method240((int)class98_Sub46_Sub13_Sub2.aLong4259, false);
                        }
                    }
                    else {
                        class98_Sub46_Sub13_Sub2.aClass17_6312.method245(false, class98_Sub46_Sub13_Sub2.aByteArray6313.length, (int)class98_Sub46_Sub13_Sub2.aLong4259, class98_Sub46_Sub13_Sub2.aByteArray6313);
                    }
                }
                catch (Exception ex) {
                    Class305_Sub1.method3585(ex, -123, null);
                }
                class98_Sub46_Sub13_Sub2.aBoolean6038 = false;
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "pt.run()");
        }
    }
    
    final void method3183(final byte b) {
        try {
            if (b >= -61) {
                method3178((byte)(-60));
            }
            this.aBoolean1936 = true;
            synchronized (this.aClass215_1933) {
                this.aClass215_1933.notifyAll();
            }
            try {
                this.aThread1937.join();
            }
            catch (InterruptedException ex2) {}
            this.aThread1937 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.A(" + b + ')');
        }
    }
    
    final Class98_Sub46_Sub13_Sub2 method3184(final int anInt6310, final Class17 class17, final int n) {
        try {
            final Class98_Sub46_Sub13_Sub2 class98_Sub46_Sub13_Sub2 = new Class98_Sub46_Sub13_Sub2();
            class98_Sub46_Sub13_Sub2.anInt6310 = anInt6310;
            synchronized (this.aClass215_1933) {
                for (Class98_Sub46_Sub13_Sub2 class98_Sub46_Sub13_Sub3 = (Class98_Sub46_Sub13_Sub2)this.aClass215_1933.method2792(-1); class98_Sub46_Sub13_Sub3 != null; class98_Sub46_Sub13_Sub3 = (Class98_Sub46_Sub13_Sub2)this.aClass215_1933.method2787(0)) {
                    if (~n == ~class98_Sub46_Sub13_Sub3.aLong4259 && class17 == class98_Sub46_Sub13_Sub3.aClass17_6312 && ~class98_Sub46_Sub13_Sub3.anInt6310 == 0xFFFFFFFD) {
                        class98_Sub46_Sub13_Sub2.aByteArray6313 = class98_Sub46_Sub13_Sub3.aByteArray6313;
                        class98_Sub46_Sub13_Sub2.aBoolean6038 = false;
                        return class98_Sub46_Sub13_Sub2;
                    }
                }
            }
            class98_Sub46_Sub13_Sub2.aByteArray6313 = class17.method240(n, false);
            class98_Sub46_Sub13_Sub2.aBoolean6038 = false;
            class98_Sub46_Sub13_Sub2.aBoolean6037 = true;
            return class98_Sub46_Sub13_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.E(" + anInt6310 + ',' + ((class17 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    Class253(final Class88 class88) {
        this.aClass215_1933 = new Class215();
        this.aBoolean1936 = false;
        this.anInt1935 = 0;
        try {
            final Class143 method858 = class88.method858(5, this, 1);
            while (method858.anInt1163 == 0) {
                Class246_Sub7.method3131(0, 10L);
            }
            if (~method858.anInt1163 == 0xFFFFFFFD) {
                throw new RuntimeException();
            }
            this.aThread1937 = (Thread)method858.anObject1162;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pt.<init>(" + ((class88 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class253.anIntArray1928 = new int[] { 0, 4, 3, 3, 1, 1, 3, 5, 1, 5, 3, 6, 4 };
        Class253.anIntArray1931 = new int[8];
        Class253.anInt1929 = 1;
        Class253.aBoolean1930 = false;
    }
}
