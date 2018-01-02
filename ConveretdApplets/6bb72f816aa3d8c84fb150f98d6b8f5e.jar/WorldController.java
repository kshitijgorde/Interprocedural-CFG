// 
// Decompiled by Procyon v0.5.30
// 

final class WorldController
{
    private boolean aBoolean434;
    public static boolean lowMem;
    private final int anInt437;
    private final int anInt438;
    private final int anInt439;
    private final int[][][] anIntArrayArrayArray440;
    private final Ground[][][] groundArray;
    private int anInt442;
    private int obj5CacheCurrPos;
    private final Object5[] obj5Cache;
    private final int[][][] anIntArrayArrayArray445;
    private static int anInt446;
    private static int anInt447;
    private static int anInt448;
    private static int anInt449;
    private static int anInt450;
    private static int anInt451;
    private static int anInt452;
    private static int anInt453;
    private static int anInt454;
    private static int anInt455;
    private static int anInt456;
    private static int anInt457;
    private static int anInt458;
    private static int anInt459;
    private static int anInt460;
    private static int anInt461;
    private static Object5[] aClass28Array462;
    private static final int[] anIntArray463;
    private static final int[] anIntArray464;
    private static final int[] anIntArray465;
    private static final int[] anIntArray466;
    private static boolean aBoolean467;
    private static int anInt468;
    private static int anInt469;
    public static int anInt470;
    public static int anInt471;
    private static final int anInt472;
    private static int[] anIntArray473;
    private static Class47[][] aClass47ArrayArray474;
    private static int anInt475;
    private static final Class47[] aClass47Array476;
    private static NodeList aClass19_477;
    private static final int[] anIntArray478;
    private static final int[] anIntArray479;
    private static final int[] anIntArray480;
    private static final int[] anIntArray481;
    private static final int[] anIntArray482;
    private static final int[] anIntArray483;
    private static final int[] anIntArray484;
    private static final int[] anIntArray485;
    private final int[] anIntArray486;
    private final int[] anIntArray487;
    private int anInt488;
    private final int[][] anIntArrayArray489;
    private final int[][] anIntArrayArray490;
    private static boolean[][][][] aBooleanArrayArrayArrayArray491;
    private static boolean[][] aBooleanArrayArray492;
    private static int anInt493;
    private static int anInt494;
    private static int anInt495;
    private static int anInt496;
    private static int anInt497;
    private static int anInt498;
    
    public WorldController(final int[][][] anIntArrayArrayArray440) {
        this.anIntArrayArray489 = new int[][] { new int[16], { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 } };
        this.anIntArrayArray490 = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };
        final int anInt439 = 104;
        final int anInt440 = 104;
        final int anInt441 = 4;
        this.aBoolean434 = true;
        this.obj5Cache = new Object5[5000];
        this.anIntArray486 = new int[10000];
        this.anIntArray487 = new int[10000];
        this.anInt437 = anInt441;
        this.anInt438 = anInt440;
        this.anInt439 = anInt439;
        this.groundArray = new Ground[anInt441][anInt440][anInt439];
        this.anIntArrayArrayArray445 = new int[anInt441][anInt440 + 1][anInt439 + 1];
        this.anIntArrayArrayArray440 = anIntArrayArrayArray440;
        this.initToNull();
    }
    
    public static void nullLoader() {
        WorldController.aClass28Array462 = null;
        WorldController.anIntArray473 = null;
        WorldController.aClass47ArrayArray474 = null;
        WorldController.aClass19_477 = null;
        WorldController.aBooleanArrayArrayArrayArray491 = null;
        WorldController.aBooleanArrayArray492 = null;
    }
    
    public void initToNull() {
        for (int i = 0; i < this.anInt437; ++i) {
            for (int j = 0; j < this.anInt438; ++j) {
                for (int k = 0; k < this.anInt439; ++k) {
                    this.groundArray[i][j][k] = null;
                }
            }
        }
        for (int l = 0; l < WorldController.anInt472; ++l) {
            for (int n = 0; n < WorldController.anIntArray473[l]; ++n) {
                WorldController.aClass47ArrayArray474[l][n] = null;
            }
            WorldController.anIntArray473[l] = 0;
        }
        for (int n2 = 0; n2 < this.obj5CacheCurrPos; ++n2) {
            this.obj5Cache[n2] = null;
        }
        this.obj5CacheCurrPos = 0;
        for (int n3 = 0; n3 < WorldController.aClass28Array462.length; ++n3) {
            WorldController.aClass28Array462[n3] = null;
        }
    }
    
    public void method275(final int anInt442) {
        this.anInt442 = anInt442;
        for (int i = 0; i < this.anInt438; ++i) {
            for (int j = 0; j < this.anInt439; ++j) {
                if (this.groundArray[anInt442][i][j] == null) {
                    this.groundArray[anInt442][i][j] = new Ground(anInt442, i, j);
                }
            }
        }
    }
    
    public void method276(final int n, final int n2) {
        final Ground aClass30_Sub3_1329 = this.groundArray[0][n2][n];
        for (int i = 0; i < 3; ++i) {
            final Ground[] array = this.groundArray[i][n2];
            final Ground ground = this.groundArray[i + 1][n2][n];
            array[n] = ground;
            final Ground ground2 = ground;
            if (ground2 != null) {
                final Ground ground3 = ground2;
                --ground3.anInt1307;
                for (int j = 0; j < ground2.anInt1317; ++j) {
                    final Object5 object5 = ground2.obj5Array[j];
                    if ((object5.uid >> 29 & 0x3) == 0x2 && object5.anInt523 == n2 && object5.anInt525 == n) {
                        final Object5 object6 = object5;
                        --object6.anInt517;
                    }
                }
            }
        }
        if (this.groundArray[0][n2][n] == null) {
            this.groundArray[0][n2][n] = new Ground(0, n2, n);
        }
        this.groundArray[0][n2][n].aClass30_Sub3_1329 = aClass30_Sub3_1329;
        this.groundArray[3][n2][n] = null;
    }
    
    public static void method277(final int n, final int anInt792, final int anInt793, final int anInt794, final int anInt795, final int anInt796, final int anInt797, final int anInt798) {
        final Class47 class47 = new Class47();
        class47.anInt787 = anInt792 / 128;
        class47.anInt788 = anInt794 / 128;
        class47.anInt789 = anInt797 / 128;
        class47.anInt790 = anInt795 / 128;
        class47.anInt791 = anInt798;
        class47.anInt792 = anInt792;
        class47.anInt793 = anInt794;
        class47.anInt794 = anInt797;
        class47.anInt795 = anInt795;
        class47.anInt796 = anInt796;
        class47.anInt797 = anInt793;
        WorldController.aClass47ArrayArray474[n][WorldController.anIntArray473[n]++] = class47;
    }
    
    public void method278(final int n, final int n2, final int n3, final int anInt1321) {
        if (this.groundArray[n][n2][n3] != null) {
            this.groundArray[n][n2][n3].anInt1321 = anInt1321;
        }
    }
    
    public void method279(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final int n16, final int n17, final int n18, final int n19, final int n20) {
        if (n4 == 0) {
            final Class43 aClass43_1311 = new Class43(n11, n12, n13, n14, -1, n19, false);
            for (int i = n; i >= 0; --i) {
                if (this.groundArray[i][n2][n3] == null) {
                    this.groundArray[i][n2][n3] = new Ground(i, n2, n3);
                }
            }
            this.groundArray[n][n2][n3].aClass43_1311 = aClass43_1311;
            return;
        }
        if (n4 == 1) {
            final Class43 aClass43_1312 = new Class43(n15, n16, n17, n18, n6, n20, n7 == n8 && n7 == n9 && n7 == n10);
            for (int j = n; j >= 0; --j) {
                if (this.groundArray[j][n2][n3] == null) {
                    this.groundArray[j][n2][n3] = new Ground(j, n2, n3);
                }
            }
            this.groundArray[n][n2][n3].aClass43_1311 = aClass43_1312;
            return;
        }
        final Class40 aClass40_1312 = new Class40(n3, n15, n14, n9, n6, n17, n5, n11, n19, n13, n10, n8, n7, n4, n18, n16, n12, n2, n20);
        for (int k = n; k >= 0; --k) {
            if (this.groundArray[k][n2][n3] == null) {
                this.groundArray[k][n2][n3] = new Ground(k, n2, n3);
            }
        }
        this.groundArray[n][n2][n3].aClass40_1312 = aClass40_1312;
    }
    
    public void method280(final int n, final int anInt811, final int n2, final Animable aClass30_Sub2_Sub4_814, final byte aByte816, final int uid, final int n3) {
        if (aClass30_Sub2_Sub4_814 == null) {
            return;
        }
        final Object3 obj3 = new Object3();
        obj3.aClass30_Sub2_Sub4_814 = aClass30_Sub2_Sub4_814;
        obj3.anInt812 = n3 * 128 + 64;
        obj3.anInt813 = n2 * 128 + 64;
        obj3.anInt811 = anInt811;
        obj3.uid = uid;
        obj3.aByte816 = aByte816;
        if (this.groundArray[n][n3][n2] == null) {
            this.groundArray[n][n3][n2] = new Ground(n, n3, n2);
        }
        this.groundArray[n][n3][n2].obj3 = obj3;
    }
    
    public void method281(final int n, final int uid, final Animable aClass30_Sub2_Sub4_49, final int anInt45, final Animable aClass30_Sub2_Sub4_50, final Animable aClass30_Sub2_Sub4_51, final int n2, final int n3) {
        final Object4 obj4 = new Object4();
        obj4.aClass30_Sub2_Sub4_48 = aClass30_Sub2_Sub4_51;
        obj4.anInt46 = n * 128 + 64;
        obj4.anInt47 = n3 * 128 + 64;
        obj4.anInt45 = anInt45;
        obj4.uid = uid;
        obj4.aClass30_Sub2_Sub4_49 = aClass30_Sub2_Sub4_49;
        obj4.aClass30_Sub2_Sub4_50 = aClass30_Sub2_Sub4_50;
        int anInt46 = 0;
        final Ground ground = this.groundArray[n2][n][n3];
        if (ground != null) {
            for (int i = 0; i < ground.anInt1317; ++i) {
                if (ground.obj5Array[i].aClass30_Sub2_Sub4_521 instanceof Model) {
                    final int anInt47 = ((Model)ground.obj5Array[i].aClass30_Sub2_Sub4_521).anInt1654;
                    if (anInt47 > anInt46) {
                        anInt46 = anInt47;
                    }
                }
            }
        }
        obj4.anInt52 = anInt46;
        if (this.groundArray[n2][n][n3] == null) {
            this.groundArray[n2][n][n3] = new Ground(n2, n, n3);
        }
        this.groundArray[n2][n][n3].obj4 = obj4;
    }
    
    public void method282(final int orientation, final Animable aClass30_Sub2_Sub4_278, final int uid, final int n, final byte aByte281, final int n2, final Animable aClass30_Sub2_Sub4_279, final int anInt273, final int orientation2, final int n3) {
        if (aClass30_Sub2_Sub4_278 == null && aClass30_Sub2_Sub4_279 == null) {
            return;
        }
        final Object1 obj1 = new Object1();
        obj1.uid = uid;
        obj1.aByte281 = aByte281;
        obj1.anInt274 = n2 * 128 + 64;
        obj1.anInt275 = n * 128 + 64;
        obj1.anInt273 = anInt273;
        obj1.aClass30_Sub2_Sub4_278 = aClass30_Sub2_Sub4_278;
        obj1.aClass30_Sub2_Sub4_279 = aClass30_Sub2_Sub4_279;
        obj1.orientation = orientation;
        obj1.orientation1 = orientation2;
        for (int i = n3; i >= 0; --i) {
            if (this.groundArray[i][n2][n] == null) {
                this.groundArray[i][n2][n] = new Ground(i, n2, n);
            }
        }
        this.groundArray[n3][n2][n].obj1 = obj1;
    }
    
    public void method283(final int uid, final int n, final int anInt503, final int n2, final int n3, final int anInt504, final Animable aClass30_Sub2_Sub4_504, final int n4, final byte aByte506, final int n5, final int anInt505) {
        if (aClass30_Sub2_Sub4_504 == null) {
            return;
        }
        final Object2 obj2 = new Object2();
        obj2.uid = uid;
        obj2.aByte506 = aByte506;
        obj2.anInt500 = n4 * 128 + 64 + n3;
        obj2.anInt501 = n * 128 + 64 + n5;
        obj2.anInt499 = anInt504;
        obj2.aClass30_Sub2_Sub4_504 = aClass30_Sub2_Sub4_504;
        obj2.anInt502 = anInt505;
        obj2.anInt503 = anInt503;
        for (int i = n2; i >= 0; --i) {
            if (this.groundArray[i][n4][n] == null) {
                this.groundArray[i][n4][n] = new Ground(i, n4, n);
            }
        }
        this.groundArray[n2][n4][n].obj2 = obj2;
    }
    
    public boolean method284(final int n, final byte b, final int n2, final int n3, final Animable animable, final int n4, final int n5, final int n6, final int n7, final int n8) {
        return animable == null || this.method287(n5, n8, n7, n4, n3, n8 * 128 + 64 * n4, n7 * 128 + 64 * n3, n2, animable, n6, false, n, b);
    }
    
    public boolean method285(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final Animable animable, final boolean b) {
        if (animable == null) {
            return true;
        }
        int n8 = n7 - n6;
        int n9 = n5 - n6;
        int n10 = n7 + n6;
        int n11 = n5 + n6;
        if (b) {
            if (n2 > 640 && n2 < 1408) {
                n11 += 128;
            }
            if (n2 > 1152 && n2 < 1920) {
                n10 += 128;
            }
            if (n2 > 1664 || n2 < 384) {
                n9 -= 128;
            }
            if (n2 > 128 && n2 < 896) {
                n8 -= 128;
            }
        }
        final int n12 = n8 / 128;
        final int n13 = n9 / 128;
        return this.method287(n, n12, n13, n10 / 128 - n12 + 1, n11 / 128 - n13 + 1, n7, n5, n3, animable, n2, true, n4, (byte)0);
    }
    
    public boolean method286(final int n, final int n2, final Animable animable, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        return animable == null || this.method287(n, n7, n10, n8 - n7 + 1, n4 - n10 + 1, n5, n2, n6, animable, n3, true, n9, (byte)0);
    }
    
    private boolean method287(final int anInt517, final int anInt518, final int anInt519, final int n, final int n2, final int anInt520, final int anInt521, final int anInt522, final Animable aClass30_Sub2_Sub4_521, final int anInt523, final boolean b, final int uid, final byte aByte530) {
        for (int i = anInt518; i < anInt518 + n; ++i) {
            for (int j = anInt519; j < anInt519 + n2; ++j) {
                if (i < 0 || j < 0 || i >= this.anInt438 || j >= this.anInt439) {
                    return false;
                }
                final Ground ground = this.groundArray[anInt517][i][j];
                if (ground != null && ground.anInt1317 >= 5) {
                    return false;
                }
            }
        }
        final Object5 object5 = new Object5();
        object5.uid = uid;
        object5.aByte530 = aByte530;
        object5.anInt517 = anInt517;
        object5.anInt519 = anInt520;
        object5.anInt520 = anInt521;
        object5.anInt518 = anInt522;
        object5.aClass30_Sub2_Sub4_521 = aClass30_Sub2_Sub4_521;
        object5.anInt522 = anInt523;
        object5.anInt523 = anInt518;
        object5.anInt525 = anInt519;
        object5.anInt524 = anInt518 + n - 1;
        object5.anInt526 = anInt519 + n2 - 1;
        for (int k = anInt518; k < anInt518 + n; ++k) {
            for (int l = anInt519; l < anInt519 + n2; ++l) {
                int n3 = 0;
                if (k > anInt518) {
                    ++n3;
                }
                if (k < anInt518 + n - 1) {
                    n3 += 4;
                }
                if (l > anInt519) {
                    n3 += 8;
                }
                if (l < anInt519 + n2 - 1) {
                    n3 += 2;
                }
                for (int n4 = anInt517; n4 >= 0; --n4) {
                    if (this.groundArray[n4][k][l] == null) {
                        this.groundArray[n4][k][l] = new Ground(n4, k, l);
                    }
                }
                final Ground ground2 = this.groundArray[anInt517][k][l];
                ground2.obj5Array[ground2.anInt1317] = object5;
                ground2.anIntArray1319[ground2.anInt1317] = n3;
                final Ground ground3 = ground2;
                ground3.anInt1320 |= n3;
                final Ground ground4 = ground2;
                ++ground4.anInt1317;
            }
        }
        if (b) {
            this.obj5Cache[this.obj5CacheCurrPos++] = object5;
        }
        return true;
    }
    
    public void clearObj5Cache() {
        for (int i = 0; i < this.obj5CacheCurrPos; ++i) {
            this.method289(this.obj5Cache[i]);
            this.obj5Cache[i] = null;
        }
        this.obj5CacheCurrPos = 0;
    }
    
    private void method289(final Object5 object5) {
        for (int i = object5.anInt523; i <= object5.anInt524; ++i) {
            for (int j = object5.anInt525; j <= object5.anInt526; ++j) {
                final Ground ground = this.groundArray[object5.anInt517][i][j];
                if (ground != null) {
                    for (int k = 0; k < ground.anInt1317; ++k) {
                        if (ground.obj5Array[k] == object5) {
                            final Ground ground2 = ground;
                            --ground2.anInt1317;
                            for (int l = k; l < ground.anInt1317; ++l) {
                                ground.obj5Array[l] = ground.obj5Array[l + 1];
                                ground.anIntArray1319[l] = ground.anIntArray1319[l + 1];
                            }
                            ground.obj5Array[ground.anInt1317] = null;
                            break;
                        }
                    }
                    ground.anInt1320 = 0;
                    for (int n = 0; n < ground.anInt1317; ++n) {
                        final Ground ground3 = ground;
                        ground3.anInt1320 |= ground.anIntArray1319[n];
                    }
                }
            }
        }
    }
    
    public void method290(final int n, final int n2, final int n3, final int n4) {
        final Ground ground = this.groundArray[n4][n3][n];
        if (ground == null) {
            return;
        }
        final Object2 obj2 = ground.obj2;
        if (obj2 != null) {
            final int n5 = n3 * 128 + 64;
            final int n6 = n * 128 + 64;
            obj2.anInt500 = n5 + (obj2.anInt500 - n5) * n2 / 16;
            obj2.anInt501 = n6 + (obj2.anInt501 - n6) * n2 / 16;
        }
    }
    
    public void method291(final int n, final int n2, final int n3, final byte b) {
        final Ground ground = this.groundArray[n2][n][n3];
        if (b != -119) {
            this.aBoolean434 = !this.aBoolean434;
        }
        if (ground != null) {
            ground.obj1 = null;
        }
    }
    
    public void method292(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n2][n3][n];
        if (ground != null) {
            ground.obj2 = null;
        }
    }
    
    public void method293(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n][n2][n3];
        if (ground == null) {
            return;
        }
        for (int i = 0; i < ground.anInt1317; ++i) {
            final Object5 object5 = ground.obj5Array[i];
            if ((object5.uid >> 29 & 0x3) == 0x2 && object5.anInt523 == n2 && object5.anInt525 == n3) {
                this.method289(object5);
                return;
            }
        }
    }
    
    public void method294(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n][n3][n2];
        if (ground == null) {
            return;
        }
        ground.obj3 = null;
    }
    
    public void method295(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n][n2][n3];
        if (ground != null) {
            ground.obj4 = null;
        }
    }
    
    public Object1 method296(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n][n2][n3];
        if (ground == null) {
            return null;
        }
        return ground.obj1;
    }
    
    public Object2 method297(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n3][n][n2];
        if (ground == null) {
            return null;
        }
        return ground.obj2;
    }
    
    public Object5 method298(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n3][n][n2];
        if (ground == null) {
            return null;
        }
        for (int i = 0; i < ground.anInt1317; ++i) {
            final Object5 object5 = ground.obj5Array[i];
            if ((object5.uid >> 29 & 0x3) == 0x2 && object5.anInt523 == n && object5.anInt525 == n2) {
                return object5;
            }
        }
        return null;
    }
    
    public Object3 method299(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n3][n2][n];
        if (ground == null || ground.obj3 == null) {
            return null;
        }
        return ground.obj3;
    }
    
    public int method300(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n][n2][n3];
        if (ground == null || ground.obj1 == null) {
            return 0;
        }
        return ground.obj1.uid;
    }
    
    public int method301(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n][n2][n3];
        if (ground == null || ground.obj2 == null) {
            return 0;
        }
        return ground.obj2.uid;
    }
    
    public int method302(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n][n2][n3];
        if (ground == null) {
            return 0;
        }
        for (int i = 0; i < ground.anInt1317; ++i) {
            final Object5 object5 = ground.obj5Array[i];
            if ((object5.uid >> 29 & 0x3) == 0x2 && object5.anInt523 == n2 && object5.anInt525 == n3) {
                return object5.uid;
            }
        }
        return 0;
    }
    
    public int method303(final int n, final int n2, final int n3) {
        final Ground ground = this.groundArray[n][n2][n3];
        if (ground == null || ground.obj3 == null) {
            return 0;
        }
        return ground.obj3.uid;
    }
    
    public int method304(final int n, final int n2, final int n3, final int n4) {
        final Ground ground = this.groundArray[n][n2][n3];
        if (ground == null) {
            return -1;
        }
        if (ground.obj1 != null && ground.obj1.uid == n4) {
            return ground.obj1.aByte281 & 0xFF;
        }
        if (ground.obj2 != null && ground.obj2.uid == n4) {
            return ground.obj2.aByte506 & 0xFF;
        }
        if (ground.obj3 != null && ground.obj3.uid == n4) {
            return ground.obj3.aByte816 & 0xFF;
        }
        for (int i = 0; i < ground.anInt1317; ++i) {
            if (ground.obj5Array[i].uid == n4) {
                return ground.obj5Array[i].aByte530 & 0xFF;
            }
        }
        return -1;
    }
    
    public void method305(final int n, final int n2, final int n3) {
        final int n4 = 100;
        final int n5 = 5500;
        final int n6 = (int)Math.sqrt(n2 * n2 + n * n + n3 * n3);
        final int n7 = n5 >> 4;
        for (int i = 0; i < this.anInt437; ++i) {
            for (int j = 0; j < this.anInt438; ++j) {
                for (int k = 0; k < this.anInt439; ++k) {
                    final Ground ground = this.groundArray[i][j][k];
                    if (ground != null) {
                        final Object1 obj1 = ground.obj1;
                        if (obj1 != null && obj1.aClass30_Sub2_Sub4_278 != null && obj1.aClass30_Sub2_Sub4_278.aClass33Array1425 != null) {
                            this.method307(i, 1, 1, j, k, (Model)obj1.aClass30_Sub2_Sub4_278);
                            if (obj1.aClass30_Sub2_Sub4_279 != null && obj1.aClass30_Sub2_Sub4_279.aClass33Array1425 != null) {
                                this.method307(i, 1, 1, j, k, (Model)obj1.aClass30_Sub2_Sub4_279);
                                this.method308((Model)obj1.aClass30_Sub2_Sub4_278, (Model)obj1.aClass30_Sub2_Sub4_279, 0, 0, 0, false);
                                ((Model)obj1.aClass30_Sub2_Sub4_279).method480(n4, n7, n2, n, n3);
                            }
                            ((Model)obj1.aClass30_Sub2_Sub4_278).method480(n4, n7, n2, n, n3);
                        }
                        for (int l = 0; l < ground.anInt1317; ++l) {
                            final Object5 object5 = ground.obj5Array[l];
                            if (object5 != null && object5.aClass30_Sub2_Sub4_521 != null && object5.aClass30_Sub2_Sub4_521.aClass33Array1425 != null) {
                                this.method307(i, object5.anInt524 - object5.anInt523 + 1, object5.anInt526 - object5.anInt525 + 1, j, k, (Model)object5.aClass30_Sub2_Sub4_521);
                                ((Model)object5.aClass30_Sub2_Sub4_521).method480(n4, n7, n2, n, n3);
                            }
                        }
                        final Object3 obj2 = ground.obj3;
                        if (obj2 != null && obj2.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
                            this.method306(j, i, (Model)obj2.aClass30_Sub2_Sub4_814, k);
                            ((Model)obj2.aClass30_Sub2_Sub4_814).method480(n4, n7, n2, n, n3);
                        }
                    }
                }
            }
        }
    }
    
    private void method306(final int n, final int n2, final Model model, final int n3) {
        if (n < this.anInt438) {
            final Ground ground = this.groundArray[n2][n + 1][n3];
            if (ground != null && ground.obj3 != null && ground.obj3.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
                this.method308(model, (Model)ground.obj3.aClass30_Sub2_Sub4_814, 128, 0, 0, true);
            }
        }
        if (n3 < this.anInt438) {
            final Ground ground2 = this.groundArray[n2][n][n3 + 1];
            if (ground2 != null && ground2.obj3 != null && ground2.obj3.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
                this.method308(model, (Model)ground2.obj3.aClass30_Sub2_Sub4_814, 0, 0, 128, true);
            }
        }
        if (n < this.anInt438 && n3 < this.anInt439) {
            final Ground ground3 = this.groundArray[n2][n + 1][n3 + 1];
            if (ground3 != null && ground3.obj3 != null && ground3.obj3.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
                this.method308(model, (Model)ground3.obj3.aClass30_Sub2_Sub4_814, 128, 0, 128, true);
            }
        }
        if (n < this.anInt438 && n3 > 0) {
            final Ground ground4 = this.groundArray[n2][n + 1][n3 - 1];
            if (ground4 != null && ground4.obj3 != null && ground4.obj3.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
                this.method308(model, (Model)ground4.obj3.aClass30_Sub2_Sub4_814, 128, 0, -128, true);
            }
        }
    }
    
    private void method307(final int n, final int n2, final int n3, final int n4, final int n5, final Model model) {
        boolean b = true;
        int n6 = n4;
        final int n7 = n4 + n2;
        final int n8 = n5 - 1;
        final int n9 = n5 + n3;
        for (int i = n; i <= n + 1; ++i) {
            if (i != this.anInt437) {
                for (int j = n6; j <= n7; ++j) {
                    if (j >= 0 && j < this.anInt438) {
                        for (int k = n8; k <= n9; ++k) {
                            if (k >= 0 && k < this.anInt439 && (!b || j >= n7 || k >= n9 || (k < n5 && j != n4))) {
                                final Ground ground = this.groundArray[i][j][k];
                                if (ground != null) {
                                    final int n10 = (this.anIntArrayArrayArray440[i][j][k] + this.anIntArrayArrayArray440[i][j + 1][k] + this.anIntArrayArrayArray440[i][j][k + 1] + this.anIntArrayArrayArray440[i][j + 1][k + 1]) / 4 - (this.anIntArrayArrayArray440[n][n4][n5] + this.anIntArrayArrayArray440[n][n4 + 1][n5] + this.anIntArrayArrayArray440[n][n4][n5 + 1] + this.anIntArrayArrayArray440[n][n4 + 1][n5 + 1]) / 4;
                                    final Object1 obj1 = ground.obj1;
                                    if (obj1 != null && obj1.aClass30_Sub2_Sub4_278 != null && obj1.aClass30_Sub2_Sub4_278.aClass33Array1425 != null) {
                                        this.method308(model, (Model)obj1.aClass30_Sub2_Sub4_278, (j - n4) * 128 + (1 - n2) * 64, n10, (k - n5) * 128 + (1 - n3) * 64, b);
                                    }
                                    if (obj1 != null && obj1.aClass30_Sub2_Sub4_279 != null && obj1.aClass30_Sub2_Sub4_279.aClass33Array1425 != null) {
                                        this.method308(model, (Model)obj1.aClass30_Sub2_Sub4_279, (j - n4) * 128 + (1 - n2) * 64, n10, (k - n5) * 128 + (1 - n3) * 64, b);
                                    }
                                    for (int l = 0; l < ground.anInt1317; ++l) {
                                        final Object5 object5 = ground.obj5Array[l];
                                        if (object5 != null && object5.aClass30_Sub2_Sub4_521 != null && object5.aClass30_Sub2_Sub4_521.aClass33Array1425 != null) {
                                            this.method308(model, (Model)object5.aClass30_Sub2_Sub4_521, (object5.anInt523 - n4) * 128 + (object5.anInt524 - object5.anInt523 + 1 - n2) * 64, n10, (object5.anInt525 - n5) * 128 + (object5.anInt526 - object5.anInt525 + 1 - n3) * 64, b);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                --n6;
                b = false;
            }
        }
    }
    
    private void method308(final Model model, final Model model2, final int n, final int n2, final int n3, final boolean b) {
        ++this.anInt488;
        int n4 = 0;
        final int[] anIntArray1627 = model2.anIntArray1627;
        final int anInt1626 = model2.anInt1626;
        for (int i = 0; i < model.anInt1626; ++i) {
            if (model.aClass33Array1660[i] == null) {
                return;
            }
            final Class33 class33 = model.aClass33Array1425[i];
            final Class33 class34 = model.aClass33Array1660[i];
            if (class34.anInt605 != 0) {
                final int n5 = model.anIntArray1628[i] - n2;
                if (n5 <= model2.anInt1651) {
                    final int n6 = model.anIntArray1627[i] - n;
                    if (n6 >= model2.anInt1646 && n6 <= model2.anInt1647) {
                        final int n7 = model.anIntArray1629[i] - n3;
                        if (n7 >= model2.anInt1649 && n7 <= model2.anInt1648) {
                            for (int j = 0; j < anInt1626; ++j) {
                                final Class33 class35 = model2.aClass33Array1425[j];
                                final Class33 class36 = model2.aClass33Array1660[j];
                                if (n6 == anIntArray1627[j] && n7 == model2.anIntArray1629[j] && n5 == model2.anIntArray1628[j] && class36.anInt605 != 0) {
                                    final Class33 class37 = class33;
                                    class37.anInt602 += class36.anInt602;
                                    final Class33 class38 = class33;
                                    class38.anInt603 += class36.anInt603;
                                    final Class33 class39 = class33;
                                    class39.anInt604 += class36.anInt604;
                                    final Class33 class40 = class33;
                                    class40.anInt605 += class36.anInt605;
                                    final Class33 class41 = class35;
                                    class41.anInt602 += class34.anInt602;
                                    final Class33 class42 = class35;
                                    class42.anInt603 += class34.anInt603;
                                    final Class33 class43 = class35;
                                    class43.anInt604 += class34.anInt604;
                                    final Class33 class44 = class35;
                                    class44.anInt605 += class34.anInt605;
                                    ++n4;
                                    this.anIntArray486[i] = this.anInt488;
                                    this.anIntArray487[j] = this.anInt488;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (n4 < 3 || !b) {
            return;
        }
        for (int k = 0; k < model.anInt1630; ++k) {
            if (this.anIntArray486[model.anIntArray1631[k]] == this.anInt488 && this.anIntArray486[model.anIntArray1632[k]] == this.anInt488 && this.anIntArray486[model.anIntArray1633[k]] == this.anInt488) {
                model.anIntArray1637[k] = -1;
            }
        }
        for (int l = 0; l < model2.anInt1630; ++l) {
            if (this.anIntArray487[model2.anIntArray1631[l]] == this.anInt488 && this.anIntArray487[model2.anIntArray1632[l]] == this.anInt488 && this.anIntArray487[model2.anIntArray1633[l]] == this.anInt488) {
                model2.anIntArray1637[l] = -1;
            }
        }
    }
    
    public void method309(final int[] array, int n, final int n2, final int n3, final int n4) {
        final int n5 = 512;
        final Ground ground = this.groundArray[n2][n3][n4];
        if (ground == null) {
            return;
        }
        final Class43 aClass43_1311 = ground.aClass43_1311;
        if (aClass43_1311 != null) {
            final int anInt722 = aClass43_1311.anInt722;
            if (anInt722 == 0) {
                return;
            }
            for (int i = 0; i < 4; ++i) {
                array[n + 1] = (array[n] = anInt722);
                array[n + 3] = (array[n + 2] = anInt722);
                n += n5;
            }
        }
        else {
            final Class40 aClass40_1312 = ground.aClass40_1312;
            if (aClass40_1312 == null) {
                return;
            }
            final int anInt723 = aClass40_1312.anInt684;
            final int anInt724 = aClass40_1312.anInt685;
            final int anInt725 = aClass40_1312.anInt686;
            final int anInt726 = aClass40_1312.anInt687;
            final int[] array2 = this.anIntArrayArray489[anInt723];
            final int[] array3 = this.anIntArrayArray490[anInt724];
            int n6 = 0;
            if (anInt725 != 0) {
                for (int j = 0; j < 4; ++j) {
                    array[n] = ((array2[array3[n6++]] != 0) ? anInt726 : anInt725);
                    array[n + 1] = ((array2[array3[n6++]] != 0) ? anInt726 : anInt725);
                    array[n + 2] = ((array2[array3[n6++]] != 0) ? anInt726 : anInt725);
                    array[n + 3] = ((array2[array3[n6++]] != 0) ? anInt726 : anInt725);
                    n += n5;
                }
                return;
            }
            for (int k = 0; k < 4; ++k) {
                if (array2[array3[n6++]] != 0) {
                    array[n] = anInt726;
                }
                if (array2[array3[n6++]] != 0) {
                    array[n + 1] = anInt726;
                }
                if (array2[array3[n6++]] != 0) {
                    array[n + 2] = anInt726;
                }
                if (array2[array3[n6++]] != 0) {
                    array[n + 3] = anInt726;
                }
                n += n5;
            }
        }
    }
    
    public static void method310(final int n, final int n2, final int anInt497, final int anInt498, final int[] array) {
        WorldController.anInt495 = 0;
        WorldController.anInt496 = 0;
        WorldController.anInt497 = anInt497;
        WorldController.anInt498 = anInt498;
        WorldController.anInt493 = anInt497 / 2;
        WorldController.anInt494 = anInt498 / 2;
        final boolean[][][][] array2 = new boolean[9][32][53][53];
        for (int i = 128; i <= 384; i += 32) {
            for (int j = 0; j < 2048; j += 64) {
                WorldController.anInt458 = Model.modelIntArray1[i];
                WorldController.anInt459 = Model.modelIntArray2[i];
                WorldController.anInt460 = Model.modelIntArray1[j];
                WorldController.anInt461 = Model.modelIntArray2[j];
                final int n3 = (i - 128) / 32;
                final int n4 = j / 64;
                for (int k = -26; k <= 26; ++k) {
                    for (int l = -26; l <= 26; ++l) {
                        final int n5 = k * 128;
                        final int n6 = l * 128;
                        boolean b = false;
                        for (int n7 = -n; n7 <= n2; n7 += 128) {
                            if (method311(array[n3] + n7, n6, n5)) {
                                b = true;
                                break;
                            }
                        }
                        array2[n3][n4][k + 25 + 1][l + 25 + 1] = b;
                    }
                }
            }
        }
        for (int n8 = 0; n8 < 8; ++n8) {
            for (int n9 = 0; n9 < 32; ++n9) {
                for (int n10 = -25; n10 < 25; ++n10) {
                    for (int n11 = -25; n11 < 25; ++n11) {
                        boolean b2 = false;
                    Label_0505:
                        for (int n12 = -1; n12 <= 1; ++n12) {
                            for (int n13 = -1; n13 <= 1; ++n13) {
                                if (array2[n8][n9][n10 + n12 + 25 + 1][n11 + n13 + 25 + 1]) {
                                    b2 = true;
                                    break Label_0505;
                                }
                                if (array2[n8][(n9 + 1) % 31][n10 + n12 + 25 + 1][n11 + n13 + 25 + 1]) {
                                    b2 = true;
                                    break Label_0505;
                                }
                                if (array2[n8 + 1][n9][n10 + n12 + 25 + 1][n11 + n13 + 25 + 1]) {
                                    b2 = true;
                                    break Label_0505;
                                }
                                if (array2[n8 + 1][(n9 + 1) % 31][n10 + n12 + 25 + 1][n11 + n13 + 25 + 1]) {
                                    b2 = true;
                                    break Label_0505;
                                }
                            }
                        }
                        WorldController.aBooleanArrayArrayArrayArray491[n8][n9][n10 + 25][n11 + 25] = b2;
                    }
                }
            }
        }
    }
    
    private static boolean method311(final int n, final int n2, final int n3) {
        final int n4 = n2 * WorldController.anInt460 + n3 * WorldController.anInt461 >> 16;
        final int n5 = n2 * WorldController.anInt461 - n3 * WorldController.anInt460 >> 16;
        final int n6 = n * WorldController.anInt458 + n5 * WorldController.anInt459 >> 16;
        final int n7 = n * WorldController.anInt459 - n5 * WorldController.anInt458 >> 16;
        if (n6 < 50 || n6 > 3500) {
            return false;
        }
        final int n8 = WorldController.anInt493 + (n4 << 9) / n6;
        final int n9 = WorldController.anInt494 + (n7 << 9) / n6;
        return n8 >= WorldController.anInt495 && n8 <= WorldController.anInt497 && n9 >= WorldController.anInt496 && n9 <= WorldController.anInt498;
    }
    
    public void method312(final int anInt469, final int anInt470) {
        WorldController.aBoolean467 = true;
        WorldController.anInt468 = anInt470;
        WorldController.anInt469 = anInt469;
        WorldController.anInt470 = -1;
        WorldController.anInt471 = -1;
    }
    
    public void method313(int anInt455, int anInt456, final int n, final int anInt457, final int anInt458, final int n2) {
        if (anInt455 < 0) {
            anInt455 = 0;
        }
        else if (anInt455 >= this.anInt438 * 128) {
            anInt455 = this.anInt438 * 128 - 1;
        }
        if (anInt456 < 0) {
            anInt456 = 0;
        }
        else if (anInt456 >= this.anInt439 * 128) {
            anInt456 = this.anInt439 * 128 - 1;
        }
        ++WorldController.anInt448;
        WorldController.anInt458 = Model.modelIntArray1[n2];
        WorldController.anInt459 = Model.modelIntArray2[n2];
        WorldController.anInt460 = Model.modelIntArray1[n];
        WorldController.anInt461 = Model.modelIntArray2[n];
        WorldController.aBooleanArrayArray492 = WorldController.aBooleanArrayArrayArrayArray491[(n2 - 128) / 32][n / 64];
        WorldController.anInt455 = anInt455;
        WorldController.anInt456 = anInt457;
        WorldController.anInt457 = anInt456;
        WorldController.anInt453 = anInt455 / 128;
        WorldController.anInt454 = anInt456 / 128;
        WorldController.anInt447 = anInt458;
        WorldController.anInt449 = WorldController.anInt453 - 25;
        if (WorldController.anInt449 < 0) {
            WorldController.anInt449 = 0;
        }
        WorldController.anInt451 = WorldController.anInt454 - 25;
        if (WorldController.anInt451 < 0) {
            WorldController.anInt451 = 0;
        }
        WorldController.anInt450 = WorldController.anInt453 + 25;
        if (WorldController.anInt450 > this.anInt438) {
            WorldController.anInt450 = this.anInt438;
        }
        WorldController.anInt452 = WorldController.anInt454 + 25;
        if (WorldController.anInt452 > this.anInt439) {
            WorldController.anInt452 = this.anInt439;
        }
        this.method319();
        WorldController.anInt446 = 0;
        for (int i = this.anInt442; i < this.anInt437; ++i) {
            final Ground[][] array = this.groundArray[i];
            for (int j = WorldController.anInt449; j < WorldController.anInt450; ++j) {
                for (int k = WorldController.anInt451; k < WorldController.anInt452; ++k) {
                    final Ground ground = array[j][k];
                    if (ground != null) {
                        if (ground.anInt1321 > anInt458 || (!WorldController.aBooleanArrayArray492[j - WorldController.anInt453 + 25][k - WorldController.anInt454 + 25] && this.anIntArrayArrayArray440[i][j][k] - anInt457 < 2000)) {
                            ground.aBoolean1322 = false;
                            ground.aBoolean1323 = false;
                            ground.anInt1325 = 0;
                        }
                        else {
                            ground.aBoolean1322 = true;
                            ground.aBoolean1323 = true;
                            ground.aBoolean1324 = (ground.anInt1317 > 0);
                            ++WorldController.anInt446;
                        }
                    }
                }
            }
        }
        for (int l = this.anInt442; l < this.anInt437; ++l) {
            final Ground[][] array2 = this.groundArray[l];
            for (int n3 = -25; n3 <= 0; ++n3) {
                final int n4 = WorldController.anInt453 + n3;
                final int n5 = WorldController.anInt453 - n3;
                if (n4 >= WorldController.anInt449 || n5 < WorldController.anInt450) {
                    for (int n6 = -25; n6 <= 0; ++n6) {
                        final int n7 = WorldController.anInt454 + n6;
                        final int n8 = WorldController.anInt454 - n6;
                        if (n4 >= WorldController.anInt449) {
                            if (n7 >= WorldController.anInt451) {
                                final Ground ground2 = array2[n4][n7];
                                if (ground2 != null && ground2.aBoolean1322) {
                                    this.method314(ground2, true);
                                }
                            }
                            if (n8 < WorldController.anInt452) {
                                final Ground ground3 = array2[n4][n8];
                                if (ground3 != null && ground3.aBoolean1322) {
                                    this.method314(ground3, true);
                                }
                            }
                        }
                        if (n5 < WorldController.anInt450) {
                            if (n7 >= WorldController.anInt451) {
                                final Ground ground4 = array2[n5][n7];
                                if (ground4 != null && ground4.aBoolean1322) {
                                    this.method314(ground4, true);
                                }
                            }
                            if (n8 < WorldController.anInt452) {
                                final Ground ground5 = array2[n5][n8];
                                if (ground5 != null && ground5.aBoolean1322) {
                                    this.method314(ground5, true);
                                }
                            }
                        }
                        if (WorldController.anInt446 == 0) {
                            WorldController.aBoolean467 = false;
                            return;
                        }
                    }
                }
            }
        }
        for (int anInt459 = this.anInt442; anInt459 < this.anInt437; ++anInt459) {
            final Ground[][] array3 = this.groundArray[anInt459];
            for (int n9 = -25; n9 <= 0; ++n9) {
                final int n10 = WorldController.anInt453 + n9;
                final int n11 = WorldController.anInt453 - n9;
                if (n10 >= WorldController.anInt449 || n11 < WorldController.anInt450) {
                    for (int n12 = -25; n12 <= 0; ++n12) {
                        final int n13 = WorldController.anInt454 + n12;
                        final int n14 = WorldController.anInt454 - n12;
                        if (n10 >= WorldController.anInt449) {
                            if (n13 >= WorldController.anInt451) {
                                final Ground ground6 = array3[n10][n13];
                                if (ground6 != null && ground6.aBoolean1322) {
                                    this.method314(ground6, false);
                                }
                            }
                            if (n14 < WorldController.anInt452) {
                                final Ground ground7 = array3[n10][n14];
                                if (ground7 != null && ground7.aBoolean1322) {
                                    this.method314(ground7, false);
                                }
                            }
                        }
                        if (n11 < WorldController.anInt450) {
                            if (n13 >= WorldController.anInt451) {
                                final Ground ground8 = array3[n11][n13];
                                if (ground8 != null && ground8.aBoolean1322) {
                                    this.method314(ground8, false);
                                }
                            }
                            if (n14 < WorldController.anInt452) {
                                final Ground ground9 = array3[n11][n14];
                                if (ground9 != null && ground9.aBoolean1322) {
                                    this.method314(ground9, false);
                                }
                            }
                        }
                        if (WorldController.anInt446 == 0) {
                            WorldController.aBoolean467 = false;
                            return;
                        }
                    }
                }
            }
        }
        WorldController.aBoolean467 = false;
    }
    
    private void method314(final Ground ground, boolean b) {
        WorldController.aClass19_477.insertHead(ground);
        while (true) {
            final Ground ground2 = (Ground)WorldController.aClass19_477.popHead();
            if (ground2 == null) {
                break;
            }
            if (!ground2.aBoolean1323) {
                continue;
            }
            final int anInt1308 = ground2.anInt1308;
            final int anInt1309 = ground2.anInt1309;
            final int anInt1310 = ground2.anInt1307;
            final int anInt1311 = ground2.anInt1310;
            final Ground[][] array = this.groundArray[anInt1310];
            if (ground2.aBoolean1322) {
                if (b) {
                    if (anInt1310 > 0) {
                        final Ground ground3 = this.groundArray[anInt1310 - 1][anInt1308][anInt1309];
                        if (ground3 != null && ground3.aBoolean1323) {
                            continue;
                        }
                    }
                    if (anInt1308 <= WorldController.anInt453 && anInt1308 > WorldController.anInt449) {
                        final Ground ground4 = array[anInt1308 - 1][anInt1309];
                        if (ground4 != null && ground4.aBoolean1323) {
                            if (ground4.aBoolean1322) {
                                continue;
                            }
                            if ((ground2.anInt1320 & 0x1) == 0x0) {
                                continue;
                            }
                        }
                    }
                    if (anInt1308 >= WorldController.anInt453 && anInt1308 < WorldController.anInt450 - 1) {
                        final Ground ground5 = array[anInt1308 + 1][anInt1309];
                        if (ground5 != null && ground5.aBoolean1323) {
                            if (ground5.aBoolean1322) {
                                continue;
                            }
                            if ((ground2.anInt1320 & 0x4) == 0x0) {
                                continue;
                            }
                        }
                    }
                    if (anInt1309 <= WorldController.anInt454 && anInt1309 > WorldController.anInt451) {
                        final Ground ground6 = array[anInt1308][anInt1309 - 1];
                        if (ground6 != null && ground6.aBoolean1323) {
                            if (ground6.aBoolean1322) {
                                continue;
                            }
                            if ((ground2.anInt1320 & 0x8) == 0x0) {
                                continue;
                            }
                        }
                    }
                    if (anInt1309 >= WorldController.anInt454 && anInt1309 < WorldController.anInt452 - 1) {
                        final Ground ground7 = array[anInt1308][anInt1309 + 1];
                        if (ground7 != null && ground7.aBoolean1323) {
                            if (ground7.aBoolean1322) {
                                continue;
                            }
                            if ((ground2.anInt1320 & 0x2) == 0x0) {
                                continue;
                            }
                        }
                    }
                }
                else {
                    b = true;
                }
                ground2.aBoolean1322 = false;
                if (ground2.aClass30_Sub3_1329 != null) {
                    final Ground aClass30_Sub3_1329 = ground2.aClass30_Sub3_1329;
                    if (aClass30_Sub3_1329.aClass43_1311 != null) {
                        if (!this.method320(0, anInt1308, anInt1309)) {
                            this.method315(aClass30_Sub3_1329.aClass43_1311, 0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, anInt1308, anInt1309);
                        }
                    }
                    else if (aClass30_Sub3_1329.aClass40_1312 != null && !this.method320(0, anInt1308, anInt1309)) {
                        this.method316(anInt1308, WorldController.anInt458, WorldController.anInt460, aClass30_Sub3_1329.aClass40_1312, WorldController.anInt459, anInt1309, WorldController.anInt461);
                    }
                    final Object1 obj1 = aClass30_Sub3_1329.obj1;
                    if (obj1 != null) {
                        obj1.aClass30_Sub2_Sub4_278.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj1.anInt274 - WorldController.anInt455, obj1.anInt273 - WorldController.anInt456, obj1.anInt275 - WorldController.anInt457, obj1.uid);
                    }
                    for (int i = 0; i < aClass30_Sub3_1329.anInt1317; ++i) {
                        final Object5 object5 = aClass30_Sub3_1329.obj5Array[i];
                        if (object5 != null) {
                            object5.aClass30_Sub2_Sub4_521.method443(object5.anInt522, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, object5.anInt519 - WorldController.anInt455, object5.anInt518 - WorldController.anInt456, object5.anInt520 - WorldController.anInt457, object5.uid);
                        }
                    }
                }
                boolean b2 = false;
                if (ground2.aClass43_1311 != null) {
                    if (!this.method320(anInt1311, anInt1308, anInt1309)) {
                        b2 = true;
                        this.method315(ground2.aClass43_1311, anInt1311, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, anInt1308, anInt1309);
                    }
                }
                else if (ground2.aClass40_1312 != null && !this.method320(anInt1311, anInt1308, anInt1309)) {
                    b2 = true;
                    this.method316(anInt1308, WorldController.anInt458, WorldController.anInt460, ground2.aClass40_1312, WorldController.anInt459, anInt1309, WorldController.anInt461);
                }
                int n = 0;
                int n2 = 0;
                final Object1 obj2 = ground2.obj1;
                final Object2 obj3 = ground2.obj2;
                if (obj2 != null || obj3 != null) {
                    if (WorldController.anInt453 == anInt1308) {
                        ++n;
                    }
                    else if (WorldController.anInt453 < anInt1308) {
                        n += 2;
                    }
                    if (WorldController.anInt454 == anInt1309) {
                        n += 3;
                    }
                    else if (WorldController.anInt454 > anInt1309) {
                        n += 6;
                    }
                    n2 = WorldController.anIntArray478[n];
                    ground2.anInt1328 = WorldController.anIntArray480[n];
                }
                if (obj2 != null) {
                    if ((obj2.orientation & WorldController.anIntArray479[n]) != 0x0) {
                        if (obj2.orientation == 16) {
                            ground2.anInt1325 = 3;
                            ground2.anInt1326 = WorldController.anIntArray481[n];
                            ground2.anInt1327 = 3 - ground2.anInt1326;
                        }
                        else if (obj2.orientation == 32) {
                            ground2.anInt1325 = 6;
                            ground2.anInt1326 = WorldController.anIntArray482[n];
                            ground2.anInt1327 = 6 - ground2.anInt1326;
                        }
                        else if (obj2.orientation == 64) {
                            ground2.anInt1325 = 12;
                            ground2.anInt1326 = WorldController.anIntArray483[n];
                            ground2.anInt1327 = 12 - ground2.anInt1326;
                        }
                        else {
                            ground2.anInt1325 = 9;
                            ground2.anInt1326 = WorldController.anIntArray484[n];
                            ground2.anInt1327 = 9 - ground2.anInt1326;
                        }
                    }
                    else {
                        ground2.anInt1325 = 0;
                    }
                    if ((obj2.orientation & n2) != 0x0 && !this.method321(anInt1311, anInt1308, anInt1309, obj2.orientation)) {
                        obj2.aClass30_Sub2_Sub4_278.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj2.anInt274 - WorldController.anInt455, obj2.anInt273 - WorldController.anInt456, obj2.anInt275 - WorldController.anInt457, obj2.uid);
                    }
                    if ((obj2.orientation1 & n2) != 0x0 && !this.method321(anInt1311, anInt1308, anInt1309, obj2.orientation1)) {
                        obj2.aClass30_Sub2_Sub4_279.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj2.anInt274 - WorldController.anInt455, obj2.anInt273 - WorldController.anInt456, obj2.anInt275 - WorldController.anInt457, obj2.uid);
                    }
                }
                if (obj3 != null && !this.method322(anInt1311, anInt1308, anInt1309, obj3.aClass30_Sub2_Sub4_504.modelHeight)) {
                    if ((obj3.anInt502 & n2) != 0x0) {
                        obj3.aClass30_Sub2_Sub4_504.method443(obj3.anInt503, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj3.anInt500 - WorldController.anInt455, obj3.anInt499 - WorldController.anInt456, obj3.anInt501 - WorldController.anInt457, obj3.uid);
                    }
                    else if ((obj3.anInt502 & 0x300) != 0x0) {
                        final int n3 = obj3.anInt500 - WorldController.anInt455;
                        final int n4 = obj3.anInt499 - WorldController.anInt456;
                        final int n5 = obj3.anInt501 - WorldController.anInt457;
                        final int anInt1312 = obj3.anInt503;
                        int n6;
                        if (anInt1312 == 1 || anInt1312 == 2) {
                            n6 = -n3;
                        }
                        else {
                            n6 = n3;
                        }
                        int n7;
                        if (anInt1312 == 2 || anInt1312 == 3) {
                            n7 = -n5;
                        }
                        else {
                            n7 = n5;
                        }
                        if ((obj3.anInt502 & 0x100) != 0x0 && n7 < n6) {
                            obj3.aClass30_Sub2_Sub4_504.method443(anInt1312 * 512 + 256, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, n3 + WorldController.anIntArray463[anInt1312], n4, n5 + WorldController.anIntArray464[anInt1312], obj3.uid);
                        }
                        if ((obj3.anInt502 & 0x200) != 0x0 && n7 > n6) {
                            obj3.aClass30_Sub2_Sub4_504.method443(anInt1312 * 512 + 1280 & 0x7FF, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, n3 + WorldController.anIntArray465[anInt1312], n4, n5 + WorldController.anIntArray466[anInt1312], obj3.uid);
                        }
                    }
                }
                if (b2) {
                    final Object3 obj4 = ground2.obj3;
                    if (obj4 != null) {
                        obj4.aClass30_Sub2_Sub4_814.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj4.anInt812 - WorldController.anInt455, obj4.anInt811 - WorldController.anInt456, obj4.anInt813 - WorldController.anInt457, obj4.uid);
                    }
                    final Object4 obj5 = ground2.obj4;
                    if (obj5 != null && obj5.anInt52 == 0) {
                        if (obj5.aClass30_Sub2_Sub4_49 != null) {
                            obj5.aClass30_Sub2_Sub4_49.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj5.anInt46 - WorldController.anInt455, obj5.anInt45 - WorldController.anInt456, obj5.anInt47 - WorldController.anInt457, obj5.uid);
                        }
                        if (obj5.aClass30_Sub2_Sub4_50 != null) {
                            obj5.aClass30_Sub2_Sub4_50.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj5.anInt46 - WorldController.anInt455, obj5.anInt45 - WorldController.anInt456, obj5.anInt47 - WorldController.anInt457, obj5.uid);
                        }
                        if (obj5.aClass30_Sub2_Sub4_48 != null) {
                            obj5.aClass30_Sub2_Sub4_48.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj5.anInt46 - WorldController.anInt455, obj5.anInt45 - WorldController.anInt456, obj5.anInt47 - WorldController.anInt457, obj5.uid);
                        }
                    }
                }
                final int anInt1313 = ground2.anInt1320;
                if (anInt1313 != 0) {
                    if (anInt1308 < WorldController.anInt453 && (anInt1313 & 0x4) != 0x0) {
                        final Ground ground8 = array[anInt1308 + 1][anInt1309];
                        if (ground8 != null && ground8.aBoolean1323) {
                            WorldController.aClass19_477.insertHead(ground8);
                        }
                    }
                    if (anInt1309 < WorldController.anInt454 && (anInt1313 & 0x2) != 0x0) {
                        final Ground ground9 = array[anInt1308][anInt1309 + 1];
                        if (ground9 != null && ground9.aBoolean1323) {
                            WorldController.aClass19_477.insertHead(ground9);
                        }
                    }
                    if (anInt1308 > WorldController.anInt453 && (anInt1313 & 0x1) != 0x0) {
                        final Ground ground10 = array[anInt1308 - 1][anInt1309];
                        if (ground10 != null && ground10.aBoolean1323) {
                            WorldController.aClass19_477.insertHead(ground10);
                        }
                    }
                    if (anInt1309 > WorldController.anInt454 && (anInt1313 & 0x8) != 0x0) {
                        final Ground ground11 = array[anInt1308][anInt1309 - 1];
                        if (ground11 != null && ground11.aBoolean1323) {
                            WorldController.aClass19_477.insertHead(ground11);
                        }
                    }
                }
            }
            if (ground2.anInt1325 != 0) {
                boolean b3 = true;
                for (int j = 0; j < ground2.anInt1317; ++j) {
                    if (ground2.obj5Array[j].anInt528 != WorldController.anInt448 && (ground2.anIntArray1319[j] & ground2.anInt1325) == ground2.anInt1326) {
                        b3 = false;
                        break;
                    }
                }
                if (b3) {
                    final Object1 obj6 = ground2.obj1;
                    if (!this.method321(anInt1311, anInt1308, anInt1309, obj6.orientation)) {
                        obj6.aClass30_Sub2_Sub4_278.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj6.anInt274 - WorldController.anInt455, obj6.anInt273 - WorldController.anInt456, obj6.anInt275 - WorldController.anInt457, obj6.uid);
                    }
                    ground2.anInt1325 = 0;
                }
            }
            if (ground2.aBoolean1324) {
                try {
                    final int anInt1314 = ground2.anInt1317;
                    ground2.aBoolean1324 = false;
                    int k = 0;
                Label_2481:
                    for (int l = 0; l < anInt1314; ++l) {
                        final Object5 object6 = ground2.obj5Array[l];
                        if (object6.anInt528 != WorldController.anInt448) {
                            for (int anInt1315 = object6.anInt523; anInt1315 <= object6.anInt524; ++anInt1315) {
                                for (int anInt1316 = object6.anInt525; anInt1316 <= object6.anInt526; ++anInt1316) {
                                    final Ground ground12 = array[anInt1315][anInt1316];
                                    if (ground12.aBoolean1322) {
                                        ground2.aBoolean1324 = true;
                                        continue Label_2481;
                                    }
                                    if (ground12.anInt1325 != 0) {
                                        int n8 = 0;
                                        if (anInt1315 > object6.anInt523) {
                                            ++n8;
                                        }
                                        if (anInt1315 < object6.anInt524) {
                                            n8 += 4;
                                        }
                                        if (anInt1316 > object6.anInt525) {
                                            n8 += 8;
                                        }
                                        if (anInt1316 < object6.anInt526) {
                                            n8 += 2;
                                        }
                                        if ((n8 & ground12.anInt1325) == ground2.anInt1327) {
                                            ground2.aBoolean1324 = true;
                                            continue Label_2481;
                                        }
                                    }
                                }
                            }
                            WorldController.aClass28Array462[k++] = object6;
                            int n9 = WorldController.anInt453 - object6.anInt523;
                            final int n10 = object6.anInt524 - WorldController.anInt453;
                            if (n10 > n9) {
                                n9 = n10;
                            }
                            final int n11 = WorldController.anInt454 - object6.anInt525;
                            final int n12 = object6.anInt526 - WorldController.anInt454;
                            if (n12 > n11) {
                                object6.anInt527 = n9 + n12;
                            }
                            else {
                                object6.anInt527 = n9 + n11;
                            }
                        }
                    }
                    while (k > 0) {
                        int anInt1317 = -50;
                        int n13 = -1;
                        for (int n14 = 0; n14 < k; ++n14) {
                            final Object5 object7 = WorldController.aClass28Array462[n14];
                            if (object7.anInt528 != WorldController.anInt448) {
                                if (object7.anInt527 > anInt1317) {
                                    anInt1317 = object7.anInt527;
                                    n13 = n14;
                                }
                                else if (object7.anInt527 == anInt1317) {
                                    final int n15 = object7.anInt519 - WorldController.anInt455;
                                    final int n16 = object7.anInt520 - WorldController.anInt457;
                                    final int n17 = WorldController.aClass28Array462[n13].anInt519 - WorldController.anInt455;
                                    final int n18 = WorldController.aClass28Array462[n13].anInt520 - WorldController.anInt457;
                                    if (n15 * n15 + n16 * n16 > n17 * n17 + n18 * n18) {
                                        n13 = n14;
                                    }
                                }
                            }
                        }
                        if (n13 == -1) {
                            break;
                        }
                        final Object5 object8 = WorldController.aClass28Array462[n13];
                        object8.anInt528 = WorldController.anInt448;
                        if (!this.method323(anInt1311, object8.anInt523, object8.anInt524, object8.anInt525, object8.anInt526, object8.aClass30_Sub2_Sub4_521.modelHeight)) {
                            object8.aClass30_Sub2_Sub4_521.method443(object8.anInt522, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, object8.anInt519 - WorldController.anInt455, object8.anInt518 - WorldController.anInt456, object8.anInt520 - WorldController.anInt457, object8.uid);
                        }
                        for (int anInt1318 = object8.anInt523; anInt1318 <= object8.anInt524; ++anInt1318) {
                            for (int anInt1319 = object8.anInt525; anInt1319 <= object8.anInt526; ++anInt1319) {
                                final Ground ground13 = array[anInt1318][anInt1319];
                                if (ground13.anInt1325 != 0) {
                                    WorldController.aClass19_477.insertHead(ground13);
                                }
                                else if ((anInt1318 != anInt1308 || anInt1319 != anInt1309) && ground13.aBoolean1323) {
                                    WorldController.aClass19_477.insertHead(ground13);
                                }
                            }
                        }
                    }
                    if (ground2.aBoolean1324) {
                        continue;
                    }
                }
                catch (Exception ex) {
                    ground2.aBoolean1324 = false;
                }
            }
            if (!ground2.aBoolean1323) {
                continue;
            }
            if (ground2.anInt1325 != 0) {
                continue;
            }
            if (anInt1308 <= WorldController.anInt453 && anInt1308 > WorldController.anInt449) {
                final Ground ground14 = array[anInt1308 - 1][anInt1309];
                if (ground14 != null && ground14.aBoolean1323) {
                    continue;
                }
            }
            if (anInt1308 >= WorldController.anInt453 && anInt1308 < WorldController.anInt450 - 1) {
                final Ground ground15 = array[anInt1308 + 1][anInt1309];
                if (ground15 != null && ground15.aBoolean1323) {
                    continue;
                }
            }
            if (anInt1309 <= WorldController.anInt454 && anInt1309 > WorldController.anInt451) {
                final Ground ground16 = array[anInt1308][anInt1309 - 1];
                if (ground16 != null && ground16.aBoolean1323) {
                    continue;
                }
            }
            if (anInt1309 >= WorldController.anInt454 && anInt1309 < WorldController.anInt452 - 1) {
                final Ground ground17 = array[anInt1308][anInt1309 + 1];
                if (ground17 != null && ground17.aBoolean1323) {
                    continue;
                }
            }
            ground2.aBoolean1323 = false;
            --WorldController.anInt446;
            final Object4 obj7 = ground2.obj4;
            if (obj7 != null && obj7.anInt52 != 0) {
                if (obj7.aClass30_Sub2_Sub4_49 != null) {
                    obj7.aClass30_Sub2_Sub4_49.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj7.anInt46 - WorldController.anInt455, obj7.anInt45 - WorldController.anInt456 - obj7.anInt52, obj7.anInt47 - WorldController.anInt457, obj7.uid);
                }
                if (obj7.aClass30_Sub2_Sub4_50 != null) {
                    obj7.aClass30_Sub2_Sub4_50.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj7.anInt46 - WorldController.anInt455, obj7.anInt45 - WorldController.anInt456 - obj7.anInt52, obj7.anInt47 - WorldController.anInt457, obj7.uid);
                }
                if (obj7.aClass30_Sub2_Sub4_48 != null) {
                    obj7.aClass30_Sub2_Sub4_48.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj7.anInt46 - WorldController.anInt455, obj7.anInt45 - WorldController.anInt456 - obj7.anInt52, obj7.anInt47 - WorldController.anInt457, obj7.uid);
                }
            }
            if (ground2.anInt1328 != 0) {
                final Object2 obj8 = ground2.obj2;
                if (obj8 != null && !this.method322(anInt1311, anInt1308, anInt1309, obj8.aClass30_Sub2_Sub4_504.modelHeight)) {
                    if ((obj8.anInt502 & ground2.anInt1328) != 0x0) {
                        obj8.aClass30_Sub2_Sub4_504.method443(obj8.anInt503, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj8.anInt500 - WorldController.anInt455, obj8.anInt499 - WorldController.anInt456, obj8.anInt501 - WorldController.anInt457, obj8.uid);
                    }
                    else if ((obj8.anInt502 & 0x300) != 0x0) {
                        final int n19 = obj8.anInt500 - WorldController.anInt455;
                        final int n20 = obj8.anInt499 - WorldController.anInt456;
                        final int n21 = obj8.anInt501 - WorldController.anInt457;
                        final int anInt1320 = obj8.anInt503;
                        int n22;
                        if (anInt1320 == 1 || anInt1320 == 2) {
                            n22 = -n19;
                        }
                        else {
                            n22 = n19;
                        }
                        int n23;
                        if (anInt1320 == 2 || anInt1320 == 3) {
                            n23 = -n21;
                        }
                        else {
                            n23 = n21;
                        }
                        if ((obj8.anInt502 & 0x100) != 0x0 && n23 >= n22) {
                            obj8.aClass30_Sub2_Sub4_504.method443(anInt1320 * 512 + 256, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, n19 + WorldController.anIntArray463[anInt1320], n20, n21 + WorldController.anIntArray464[anInt1320], obj8.uid);
                        }
                        if ((obj8.anInt502 & 0x200) != 0x0 && n23 <= n22) {
                            obj8.aClass30_Sub2_Sub4_504.method443(anInt1320 * 512 + 1280 & 0x7FF, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, n19 + WorldController.anIntArray465[anInt1320], n20, n21 + WorldController.anIntArray466[anInt1320], obj8.uid);
                        }
                    }
                }
                final Object1 obj9 = ground2.obj1;
                if (obj9 != null) {
                    if ((obj9.orientation1 & ground2.anInt1328) != 0x0 && !this.method321(anInt1311, anInt1308, anInt1309, obj9.orientation1)) {
                        obj9.aClass30_Sub2_Sub4_279.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj9.anInt274 - WorldController.anInt455, obj9.anInt273 - WorldController.anInt456, obj9.anInt275 - WorldController.anInt457, obj9.uid);
                    }
                    if ((obj9.orientation & ground2.anInt1328) != 0x0 && !this.method321(anInt1311, anInt1308, anInt1309, obj9.orientation)) {
                        obj9.aClass30_Sub2_Sub4_278.method443(0, WorldController.anInt458, WorldController.anInt459, WorldController.anInt460, WorldController.anInt461, obj9.anInt274 - WorldController.anInt455, obj9.anInt273 - WorldController.anInt456, obj9.anInt275 - WorldController.anInt457, obj9.uid);
                    }
                }
            }
            if (anInt1310 < this.anInt437 - 1) {
                final Ground ground18 = this.groundArray[anInt1310 + 1][anInt1308][anInt1309];
                if (ground18 != null && ground18.aBoolean1323) {
                    WorldController.aClass19_477.insertHead(ground18);
                }
            }
            if (anInt1308 < WorldController.anInt453) {
                final Ground ground19 = array[anInt1308 + 1][anInt1309];
                if (ground19 != null && ground19.aBoolean1323) {
                    WorldController.aClass19_477.insertHead(ground19);
                }
            }
            if (anInt1309 < WorldController.anInt454) {
                final Ground ground20 = array[anInt1308][anInt1309 + 1];
                if (ground20 != null && ground20.aBoolean1323) {
                    WorldController.aClass19_477.insertHead(ground20);
                }
            }
            if (anInt1308 > WorldController.anInt453) {
                final Ground ground21 = array[anInt1308 - 1][anInt1309];
                if (ground21 != null && ground21.aBoolean1323) {
                    WorldController.aClass19_477.insertHead(ground21);
                }
            }
            if (anInt1309 <= WorldController.anInt454) {
                continue;
            }
            final Ground ground22 = array[anInt1308][anInt1309 - 1];
            if (ground22 == null || !ground22.aBoolean1323) {
                continue;
            }
            WorldController.aClass19_477.insertHead(ground22);
        }
    }
    
    private void method315(final Class43 class43, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n9;
        final int n8 = n9 = (n6 << 7) - WorldController.anInt455;
        final int n11;
        final int n10 = n11 = (n7 << 7) - WorldController.anInt457;
        final int n13;
        final int n12 = n13 = n9 + 128;
        final int n15;
        final int n14 = n15 = n11 + 128;
        final int n16 = this.anIntArrayArrayArray440[n][n6][n7] - WorldController.anInt456;
        final int n17 = this.anIntArrayArrayArray440[n][n6 + 1][n7] - WorldController.anInt456;
        final int n18 = this.anIntArrayArrayArray440[n][n6 + 1][n7 + 1] - WorldController.anInt456;
        final int n19 = this.anIntArrayArrayArray440[n][n6][n7 + 1] - WorldController.anInt456;
        final int n20 = n11 * n4 + n9 * n5 >> 16;
        final int n21 = n11 * n5 - n9 * n4 >> 16;
        final int n22 = n20;
        final int n23 = n16 * n3 - n21 * n2 >> 16;
        final int n24 = n16 * n2 + n21 * n3 >> 16;
        final int n25 = n23;
        if (n24 < 50) {
            return;
        }
        final int n26 = n10 * n4 + n13 * n5 >> 16;
        final int n27 = n10 * n5 - n13 * n4 >> 16;
        final int n28 = n26;
        final int n29 = n17 * n3 - n27 * n2 >> 16;
        final int n30 = n17 * n2 + n27 * n3 >> 16;
        final int n31 = n29;
        if (n30 < 50) {
            return;
        }
        final int n32 = n15 * n4 + n12 * n5 >> 16;
        final int n33 = n15 * n5 - n12 * n4 >> 16;
        final int n34 = n32;
        final int n35 = n18 * n3 - n33 * n2 >> 16;
        final int n36 = n18 * n2 + n33 * n3 >> 16;
        final int n37 = n35;
        if (n36 < 50) {
            return;
        }
        final int n38 = n14 * n4 + n8 * n5 >> 16;
        final int n39 = n14 * n5 - n8 * n4 >> 16;
        final int n40 = n38;
        final int n41 = n19 * n3 - n39 * n2 >> 16;
        final int n42 = n19 * n2 + n39 * n3 >> 16;
        final int n43 = n41;
        if (n42 < 50) {
            return;
        }
        final int n44 = Texture.textureInt1 + (n22 << 9) / n24;
        final int n45 = Texture.textureInt2 + (n25 << 9) / n24;
        final int n46 = Texture.textureInt1 + (n28 << 9) / n30;
        final int n47 = Texture.textureInt2 + (n31 << 9) / n30;
        final int n48 = Texture.textureInt1 + (n34 << 9) / n36;
        final int n49 = Texture.textureInt2 + (n37 << 9) / n36;
        final int n50 = Texture.textureInt1 + (n40 << 9) / n42;
        final int n51 = Texture.textureInt2 + (n43 << 9) / n42;
        Texture.anInt1465 = 0;
        if ((n48 - n50) * (n47 - n51) - (n49 - n51) * (n46 - n50) > 0) {
            Texture.aBoolean1462 = (n48 < 0 || n50 < 0 || n46 < 0 || n48 > DrawingArea.centerX || n50 > DrawingArea.centerX || n46 > DrawingArea.centerX);
            if (WorldController.aBoolean467 && this.method318(WorldController.anInt468, WorldController.anInt469, n49, n51, n47, n48, n50, n46)) {
                WorldController.anInt470 = n6;
                WorldController.anInt471 = n7;
            }
            if (class43.anInt720 == -1) {
                if (class43.anInt718 != 12345678) {
                    Texture.method374(n49, n51, n47, n48, n50, n46, class43.anInt718, class43.anInt719, class43.anInt717);
                }
            }
            else if (!WorldController.lowMem) {
                if (class43.aBoolean721) {
                    Texture.method378(n49, n51, n47, n48, n50, n46, class43.anInt718, class43.anInt719, class43.anInt717, n22, n28, n40, n25, n31, n43, n24, n30, n42, class43.anInt720);
                }
                else {
                    Texture.method378(n49, n51, n47, n48, n50, n46, class43.anInt718, class43.anInt719, class43.anInt717, n34, n40, n28, n37, n43, n31, n36, n42, n30, class43.anInt720);
                }
            }
            else {
                final int n52 = WorldController.anIntArray485[class43.anInt720];
                Texture.method374(n49, n51, n47, n48, n50, n46, this.method317(n52, class43.anInt718), this.method317(n52, class43.anInt719), this.method317(n52, class43.anInt717));
            }
        }
        if ((n44 - n46) * (n51 - n47) - (n45 - n47) * (n50 - n46) > 0) {
            Texture.aBoolean1462 = (n44 < 0 || n46 < 0 || n50 < 0 || n44 > DrawingArea.centerX || n46 > DrawingArea.centerX || n50 > DrawingArea.centerX);
            if (WorldController.aBoolean467 && this.method318(WorldController.anInt468, WorldController.anInt469, n45, n47, n51, n44, n46, n50)) {
                WorldController.anInt470 = n6;
                WorldController.anInt471 = n7;
            }
            if (class43.anInt720 == -1) {
                if (class43.anInt716 != 12345678) {
                    Texture.method374(n45, n47, n51, n44, n46, n50, class43.anInt716, class43.anInt717, class43.anInt719);
                }
            }
            else {
                if (!WorldController.lowMem) {
                    Texture.method378(n45, n47, n51, n44, n46, n50, class43.anInt716, class43.anInt717, class43.anInt719, n22, n28, n40, n25, n31, n43, n24, n30, n42, class43.anInt720);
                    return;
                }
                final int n53 = WorldController.anIntArray485[class43.anInt720];
                Texture.method374(n45, n47, n51, n44, n46, n50, this.method317(n53, class43.anInt716), this.method317(n53, class43.anInt717), this.method317(n53, class43.anInt719));
            }
        }
    }
    
    private void method316(final int anInt470, final int n, final int n2, final Class40 class40, final int n3, final int anInt471, final int n4) {
        for (int length = class40.anIntArray673.length, i = 0; i < length; ++i) {
            final int n5 = class40.anIntArray673[i] - WorldController.anInt455;
            final int n6 = class40.anIntArray674[i] - WorldController.anInt456;
            final int n7 = class40.anIntArray675[i] - WorldController.anInt457;
            final int n8 = n7 * n2 + n5 * n4 >> 16;
            final int n9 = n7 * n4 - n5 * n2 >> 16;
            final int n10 = n8;
            final int n11 = n6 * n3 - n9 * n >> 16;
            final int n12 = n6 * n + n9 * n3 >> 16;
            final int n13 = n11;
            if (n12 < 50) {
                return;
            }
            if (class40.anIntArray682 != null) {
                Class40.anIntArray690[i] = n10;
                Class40.anIntArray691[i] = n13;
                Class40.anIntArray692[i] = n12;
            }
            Class40.anIntArray688[i] = Texture.textureInt1 + (n10 << 9) / n12;
            Class40.anIntArray689[i] = Texture.textureInt2 + (n13 << 9) / n12;
        }
        Texture.anInt1465 = 0;
        for (int length2 = class40.anIntArray679.length, j = 0; j < length2; ++j) {
            final int n14 = class40.anIntArray679[j];
            final int n15 = class40.anIntArray680[j];
            final int n16 = class40.anIntArray681[j];
            final int n17 = Class40.anIntArray688[n14];
            final int n18 = Class40.anIntArray688[n15];
            final int n19 = Class40.anIntArray688[n16];
            final int n20 = Class40.anIntArray689[n14];
            final int n21 = Class40.anIntArray689[n15];
            final int n22 = Class40.anIntArray689[n16];
            if ((n17 - n18) * (n22 - n21) - (n20 - n21) * (n19 - n18) > 0) {
                Texture.aBoolean1462 = (n17 < 0 || n18 < 0 || n19 < 0 || n17 > DrawingArea.centerX || n18 > DrawingArea.centerX || n19 > DrawingArea.centerX);
                if (WorldController.aBoolean467 && this.method318(WorldController.anInt468, WorldController.anInt469, n20, n21, n22, n17, n18, n19)) {
                    WorldController.anInt470 = anInt470;
                    WorldController.anInt471 = anInt471;
                }
                if (class40.anIntArray682 == null || class40.anIntArray682[j] == -1) {
                    if (class40.anIntArray676[j] != 12345678) {
                        Texture.method374(n20, n21, n22, n17, n18, n19, class40.anIntArray676[j], class40.anIntArray677[j], class40.anIntArray678[j]);
                    }
                }
                else if (!WorldController.lowMem) {
                    if (class40.aBoolean683) {
                        Texture.method378(n20, n21, n22, n17, n18, n19, class40.anIntArray676[j], class40.anIntArray677[j], class40.anIntArray678[j], Class40.anIntArray690[0], Class40.anIntArray690[1], Class40.anIntArray690[3], Class40.anIntArray691[0], Class40.anIntArray691[1], Class40.anIntArray691[3], Class40.anIntArray692[0], Class40.anIntArray692[1], Class40.anIntArray692[3], class40.anIntArray682[j]);
                    }
                    else {
                        Texture.method378(n20, n21, n22, n17, n18, n19, class40.anIntArray676[j], class40.anIntArray677[j], class40.anIntArray678[j], Class40.anIntArray690[n14], Class40.anIntArray690[n15], Class40.anIntArray690[n16], Class40.anIntArray691[n14], Class40.anIntArray691[n15], Class40.anIntArray691[n16], Class40.anIntArray692[n14], Class40.anIntArray692[n15], Class40.anIntArray692[n16], class40.anIntArray682[j]);
                    }
                }
                else {
                    final int n23 = WorldController.anIntArray485[class40.anIntArray682[j]];
                    Texture.method374(n20, n21, n22, n17, n18, n19, this.method317(n23, class40.anIntArray676[j]), this.method317(n23, class40.anIntArray677[j]), this.method317(n23, class40.anIntArray678[j]));
                }
            }
        }
    }
    
    private int method317(final int n, int n2) {
        n2 = 127 - n2;
        n2 = n2 * (n & 0x7F) / 160;
        if (n2 < 2) {
            n2 = 2;
        }
        else if (n2 > 126) {
            n2 = 126;
        }
        return (n & 0xFF80) + n2;
    }
    
    private boolean method318(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (n2 < n3 && n2 < n4 && n2 < n5) {
            return false;
        }
        if (n2 > n3 && n2 > n4 && n2 > n5) {
            return false;
        }
        if (n < n6 && n < n7 && n < n8) {
            return false;
        }
        if (n > n6 && n > n7 && n > n8) {
            return false;
        }
        final int n9 = (n2 - n3) * (n7 - n6) - (n - n6) * (n4 - n3);
        final int n10 = (n2 - n5) * (n6 - n8) - (n - n8) * (n3 - n5);
        final int n11 = (n2 - n4) * (n8 - n7) - (n - n7) * (n5 - n4);
        return n9 * n11 > 0 && n11 * n10 > 0;
    }
    
    private void method319() {
        final int n = WorldController.anIntArray473[WorldController.anInt447];
        final Class47[] array = WorldController.aClass47ArrayArray474[WorldController.anInt447];
        WorldController.anInt475 = 0;
        for (final Class47 class47 : array) {
            Label_0865: {
                if (class47.anInt791 == 1) {
                    final int n2 = class47.anInt787 - WorldController.anInt453 + 25;
                    if (n2 >= 0) {
                        if (n2 <= 50) {
                            int j = class47.anInt789 - WorldController.anInt454 + 25;
                            if (j < 0) {
                                j = 0;
                            }
                            int n3 = class47.anInt790 - WorldController.anInt454 + 25;
                            if (n3 > 50) {
                                n3 = 50;
                            }
                            boolean b = false;
                            while (j <= n3) {
                                if (WorldController.aBooleanArrayArray492[n2][j++]) {
                                    b = true;
                                    break;
                                }
                            }
                            if (b) {
                                int n4 = WorldController.anInt455 - class47.anInt792;
                                if (n4 > 32) {
                                    class47.anInt798 = 1;
                                }
                                else {
                                    if (n4 >= -32) {
                                        break Label_0865;
                                    }
                                    class47.anInt798 = 2;
                                    n4 = -n4;
                                }
                                class47.anInt801 = (class47.anInt794 - WorldController.anInt457 << 8) / n4;
                                class47.anInt802 = (class47.anInt795 - WorldController.anInt457 << 8) / n4;
                                class47.anInt803 = (class47.anInt796 - WorldController.anInt456 << 8) / n4;
                                class47.anInt804 = (class47.anInt797 - WorldController.anInt456 << 8) / n4;
                                WorldController.aClass47Array476[WorldController.anInt475++] = class47;
                            }
                        }
                    }
                }
                else if (class47.anInt791 == 2) {
                    final int n5 = class47.anInt789 - WorldController.anInt454 + 25;
                    if (n5 >= 0) {
                        if (n5 <= 50) {
                            int k = class47.anInt787 - WorldController.anInt453 + 25;
                            if (k < 0) {
                                k = 0;
                            }
                            int n6 = class47.anInt788 - WorldController.anInt453 + 25;
                            if (n6 > 50) {
                                n6 = 50;
                            }
                            boolean b2 = false;
                            while (k <= n6) {
                                if (WorldController.aBooleanArrayArray492[k++][n5]) {
                                    b2 = true;
                                    break;
                                }
                            }
                            if (b2) {
                                int n7 = WorldController.anInt457 - class47.anInt794;
                                if (n7 > 32) {
                                    class47.anInt798 = 3;
                                }
                                else {
                                    if (n7 >= -32) {
                                        break Label_0865;
                                    }
                                    class47.anInt798 = 4;
                                    n7 = -n7;
                                }
                                class47.anInt799 = (class47.anInt792 - WorldController.anInt455 << 8) / n7;
                                class47.anInt800 = (class47.anInt793 - WorldController.anInt455 << 8) / n7;
                                class47.anInt803 = (class47.anInt796 - WorldController.anInt456 << 8) / n7;
                                class47.anInt804 = (class47.anInt797 - WorldController.anInt456 << 8) / n7;
                                WorldController.aClass47Array476[WorldController.anInt475++] = class47;
                            }
                        }
                    }
                }
                else if (class47.anInt791 == 4) {
                    final int n8 = class47.anInt796 - WorldController.anInt456;
                    if (n8 > 128) {
                        int n9 = class47.anInt789 - WorldController.anInt454 + 25;
                        if (n9 < 0) {
                            n9 = 0;
                        }
                        int n10 = class47.anInt790 - WorldController.anInt454 + 25;
                        if (n10 > 50) {
                            n10 = 50;
                        }
                        if (n9 <= n10) {
                            int n11 = class47.anInt787 - WorldController.anInt453 + 25;
                            if (n11 < 0) {
                                n11 = 0;
                            }
                            int n12 = class47.anInt788 - WorldController.anInt453 + 25;
                            if (n12 > 50) {
                                n12 = 50;
                            }
                            boolean b3 = false;
                        Label_0759:
                            for (int l = n11; l <= n12; ++l) {
                                for (int n13 = n9; n13 <= n10; ++n13) {
                                    if (WorldController.aBooleanArrayArray492[l][n13]) {
                                        b3 = true;
                                        break Label_0759;
                                    }
                                }
                            }
                            if (b3) {
                                class47.anInt798 = 5;
                                class47.anInt799 = (class47.anInt792 - WorldController.anInt455 << 8) / n8;
                                class47.anInt800 = (class47.anInt793 - WorldController.anInt455 << 8) / n8;
                                class47.anInt801 = (class47.anInt794 - WorldController.anInt457 << 8) / n8;
                                class47.anInt802 = (class47.anInt795 - WorldController.anInt457 << 8) / n8;
                                WorldController.aClass47Array476[WorldController.anInt475++] = class47;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private boolean method320(final int n, final int n2, final int n3) {
        final int n4 = this.anIntArrayArrayArray445[n][n2][n3];
        if (n4 == -WorldController.anInt448) {
            return false;
        }
        if (n4 == WorldController.anInt448) {
            return true;
        }
        final int n5 = n2 << 7;
        final int n6 = n3 << 7;
        if (this.method324(n5 + 1, this.anIntArrayArrayArray440[n][n2][n3], n6 + 1) && this.method324(n5 + 128 - 1, this.anIntArrayArrayArray440[n][n2 + 1][n3], n6 + 1) && this.method324(n5 + 128 - 1, this.anIntArrayArrayArray440[n][n2 + 1][n3 + 1], n6 + 128 - 1) && this.method324(n5 + 1, this.anIntArrayArrayArray440[n][n2][n3 + 1], n6 + 128 - 1)) {
            this.anIntArrayArrayArray445[n][n2][n3] = WorldController.anInt448;
            return true;
        }
        this.anIntArrayArrayArray445[n][n2][n3] = -WorldController.anInt448;
        return false;
    }
    
    private boolean method321(final int n, final int n2, final int n3, final int n4) {
        if (!this.method320(n, n2, n3)) {
            return false;
        }
        final int n5 = n2 << 7;
        final int n6 = n3 << 7;
        final int n7 = this.anIntArrayArrayArray440[n][n2][n3] - 1;
        final int n8 = n7 - 120;
        final int n9 = n7 - 230;
        final int n10 = n7 - 238;
        if (n4 < 16) {
            if (n4 == 1) {
                if (n5 > WorldController.anInt455) {
                    if (!this.method324(n5, n7, n6)) {
                        return false;
                    }
                    if (!this.method324(n5, n7, n6 + 128)) {
                        return false;
                    }
                }
                if (n > 0) {
                    if (!this.method324(n5, n8, n6)) {
                        return false;
                    }
                    if (!this.method324(n5, n8, n6 + 128)) {
                        return false;
                    }
                }
                return this.method324(n5, n9, n6) && this.method324(n5, n9, n6 + 128);
            }
            if (n4 == 2) {
                if (n6 < WorldController.anInt457) {
                    if (!this.method324(n5, n7, n6 + 128)) {
                        return false;
                    }
                    if (!this.method324(n5 + 128, n7, n6 + 128)) {
                        return false;
                    }
                }
                if (n > 0) {
                    if (!this.method324(n5, n8, n6 + 128)) {
                        return false;
                    }
                    if (!this.method324(n5 + 128, n8, n6 + 128)) {
                        return false;
                    }
                }
                return this.method324(n5, n9, n6 + 128) && this.method324(n5 + 128, n9, n6 + 128);
            }
            if (n4 == 4) {
                if (n5 < WorldController.anInt455) {
                    if (!this.method324(n5 + 128, n7, n6)) {
                        return false;
                    }
                    if (!this.method324(n5 + 128, n7, n6 + 128)) {
                        return false;
                    }
                }
                if (n > 0) {
                    if (!this.method324(n5 + 128, n8, n6)) {
                        return false;
                    }
                    if (!this.method324(n5 + 128, n8, n6 + 128)) {
                        return false;
                    }
                }
                return this.method324(n5 + 128, n9, n6) && this.method324(n5 + 128, n9, n6 + 128);
            }
            if (n4 == 8) {
                if (n6 > WorldController.anInt457) {
                    if (!this.method324(n5, n7, n6)) {
                        return false;
                    }
                    if (!this.method324(n5 + 128, n7, n6)) {
                        return false;
                    }
                }
                if (n > 0) {
                    if (!this.method324(n5, n8, n6)) {
                        return false;
                    }
                    if (!this.method324(n5 + 128, n8, n6)) {
                        return false;
                    }
                }
                return this.method324(n5, n9, n6) && this.method324(n5 + 128, n9, n6);
            }
        }
        if (!this.method324(n5 + 64, n10, n6 + 64)) {
            return false;
        }
        if (n4 == 16) {
            return this.method324(n5, n9, n6 + 128);
        }
        if (n4 == 32) {
            return this.method324(n5 + 128, n9, n6 + 128);
        }
        if (n4 == 64) {
            return this.method324(n5 + 128, n9, n6);
        }
        if (n4 == 128) {
            return this.method324(n5, n9, n6);
        }
        System.out.println("Warning unsupported wall type");
        return true;
    }
    
    private boolean method322(final int n, final int n2, final int n3, final int n4) {
        if (!this.method320(n, n2, n3)) {
            return false;
        }
        final int n5 = n2 << 7;
        final int n6 = n3 << 7;
        return this.method324(n5 + 1, this.anIntArrayArrayArray440[n][n2][n3] - n4, n6 + 1) && this.method324(n5 + 128 - 1, this.anIntArrayArrayArray440[n][n2 + 1][n3] - n4, n6 + 1) && this.method324(n5 + 128 - 1, this.anIntArrayArrayArray440[n][n2 + 1][n3 + 1] - n4, n6 + 128 - 1) && this.method324(n5 + 1, this.anIntArrayArrayArray440[n][n2][n3 + 1] - n4, n6 + 128 - 1);
    }
    
    private boolean method323(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (n2 == n3 && n4 == n5) {
            if (!this.method320(n, n2, n4)) {
                return false;
            }
            final int n7 = n2 << 7;
            final int n8 = n4 << 7;
            return this.method324(n7 + 1, this.anIntArrayArrayArray440[n][n2][n4] - n6, n8 + 1) && this.method324(n7 + 128 - 1, this.anIntArrayArrayArray440[n][n2 + 1][n4] - n6, n8 + 1) && this.method324(n7 + 128 - 1, this.anIntArrayArrayArray440[n][n2 + 1][n4 + 1] - n6, n8 + 128 - 1) && this.method324(n7 + 1, this.anIntArrayArrayArray440[n][n2][n4 + 1] - n6, n8 + 128 - 1);
        }
        else {
            for (int i = n2; i <= n3; ++i) {
                for (int j = n4; j <= n5; ++j) {
                    if (this.anIntArrayArrayArray445[n][i][j] == -WorldController.anInt448) {
                        return false;
                    }
                }
            }
            final int n9 = (n2 << 7) + 1;
            final int n10 = (n4 << 7) + 2;
            final int n11 = this.anIntArrayArrayArray440[n][n2][n4] - n6;
            if (!this.method324(n9, n11, n10)) {
                return false;
            }
            final int n12 = (n3 << 7) - 1;
            if (!this.method324(n12, n11, n10)) {
                return false;
            }
            final int n13 = (n5 << 7) - 1;
            return this.method324(n9, n11, n13) && this.method324(n12, n11, n13);
        }
    }
    
    private boolean method324(final int n, final int n2, final int n3) {
        for (int i = 0; i < WorldController.anInt475; ++i) {
            final Class47 class47 = WorldController.aClass47Array476[i];
            if (class47.anInt798 == 1) {
                final int n4 = class47.anInt792 - n;
                if (n4 > 0) {
                    final int n5 = class47.anInt794 + (class47.anInt801 * n4 >> 8);
                    final int n6 = class47.anInt795 + (class47.anInt802 * n4 >> 8);
                    final int n7 = class47.anInt796 + (class47.anInt803 * n4 >> 8);
                    final int n8 = class47.anInt797 + (class47.anInt804 * n4 >> 8);
                    if (n3 >= n5 && n3 <= n6 && n2 >= n7 && n2 <= n8) {
                        return true;
                    }
                }
            }
            else if (class47.anInt798 == 2) {
                final int n9 = n - class47.anInt792;
                if (n9 > 0) {
                    final int n10 = class47.anInt794 + (class47.anInt801 * n9 >> 8);
                    final int n11 = class47.anInt795 + (class47.anInt802 * n9 >> 8);
                    final int n12 = class47.anInt796 + (class47.anInt803 * n9 >> 8);
                    final int n13 = class47.anInt797 + (class47.anInt804 * n9 >> 8);
                    if (n3 >= n10 && n3 <= n11 && n2 >= n12 && n2 <= n13) {
                        return true;
                    }
                }
            }
            else if (class47.anInt798 == 3) {
                final int n14 = class47.anInt794 - n3;
                if (n14 > 0) {
                    final int n15 = class47.anInt792 + (class47.anInt799 * n14 >> 8);
                    final int n16 = class47.anInt793 + (class47.anInt800 * n14 >> 8);
                    final int n17 = class47.anInt796 + (class47.anInt803 * n14 >> 8);
                    final int n18 = class47.anInt797 + (class47.anInt804 * n14 >> 8);
                    if (n >= n15 && n <= n16 && n2 >= n17 && n2 <= n18) {
                        return true;
                    }
                }
            }
            else if (class47.anInt798 == 4) {
                final int n19 = n3 - class47.anInt794;
                if (n19 > 0) {
                    final int n20 = class47.anInt792 + (class47.anInt799 * n19 >> 8);
                    final int n21 = class47.anInt793 + (class47.anInt800 * n19 >> 8);
                    final int n22 = class47.anInt796 + (class47.anInt803 * n19 >> 8);
                    final int n23 = class47.anInt797 + (class47.anInt804 * n19 >> 8);
                    if (n >= n20 && n <= n21 && n2 >= n22 && n2 <= n23) {
                        return true;
                    }
                }
            }
            else if (class47.anInt798 == 5) {
                final int n24 = n2 - class47.anInt796;
                if (n24 > 0) {
                    final int n25 = class47.anInt792 + (class47.anInt799 * n24 >> 8);
                    final int n26 = class47.anInt793 + (class47.anInt800 * n24 >> 8);
                    final int n27 = class47.anInt794 + (class47.anInt801 * n24 >> 8);
                    final int n28 = class47.anInt795 + (class47.anInt802 * n24 >> 8);
                    if (n >= n25 && n <= n26 && n3 >= n27 && n3 <= n28) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    static {
        WorldController.lowMem = true;
        WorldController.aClass28Array462 = new Object5[100];
        anIntArray463 = new int[] { 53, -53, -53, 53 };
        anIntArray464 = new int[] { -53, -53, 53, 53 };
        anIntArray465 = new int[] { -45, 45, 45, -45 };
        anIntArray466 = new int[] { 45, 45, -45, -45 };
        WorldController.anInt470 = -1;
        WorldController.anInt471 = -1;
        aClass47Array476 = new Class47[500];
        WorldController.aClass19_477 = new NodeList();
        anIntArray478 = new int[] { 19, 55, 38, 155, 255, 110, 137, 205, 76 };
        anIntArray479 = new int[] { 160, 192, 80, 96, 0, 144, 80, 48, 160 };
        anIntArray480 = new int[] { 76, 8, 137, 4, 0, 1, 38, 2, 19 };
        anIntArray481 = new int[] { 0, 0, 2, 0, 0, 2, 1, 1, 0 };
        anIntArray482 = new int[] { 2, 0, 0, 2, 0, 0, 0, 4, 4 };
        anIntArray483 = new int[] { 0, 4, 4, 8, 0, 0, 8, 0, 0 };
        anIntArray484 = new int[] { 1, 1, 0, 0, 0, 8, 0, 0, 8 };
        anIntArray485 = new int[] { 41, 39248, 41, 4643, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 43086, 41, 41, 41, 41, 41, 41, 41, 8602, 41, 28992, 41, 41, 41, 41, 41, 5056, 41, 41, 41, 7079, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 3131, 41, 41, 41 };
        WorldController.aBooleanArrayArrayArrayArray491 = new boolean[8][32][51][51];
        anInt472 = 4;
        WorldController.anIntArray473 = new int[WorldController.anInt472];
        WorldController.aClass47ArrayArray474 = new Class47[WorldController.anInt472][500];
    }
}
