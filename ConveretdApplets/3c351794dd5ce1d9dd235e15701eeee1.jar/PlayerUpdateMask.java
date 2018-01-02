import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import jagex3.jagmisc.jagmisc;

// 
// Decompiled by Procyon v0.5.30
// 

final class PlayerUpdateMask
{
    static OutgoingOpcode aClass171_524;
    static Class207 aClass207_525;
    static int anInt526;
    static Class259[] aClass259Array527;
    static int[][] anIntArrayArray528;
    static int anInt529;
    
    static final void method709(final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2, final int n, final Class98_Sub22_Sub1 class98_Sub22_Sub1, final byte b, final int n2) {
        try {
            if ((0x400 & n) != 0x0) {
                final int short1 = class98_Sub22_Sub1.readShort1((byte)51);
                class246_Sub3_Sub4_Sub2_Sub2.anInt6394 = class98_Sub22_Sub1.readByteA(true);
                class246_Sub3_Sub4_Sub2_Sub2.anInt6401 = class98_Sub22_Sub1.readByteS(-121);
                class246_Sub3_Sub4_Sub2_Sub2.anInt6420 = (short1 & 0x7FFF);
                class246_Sub3_Sub4_Sub2_Sub2.aBoolean6348 = ((short1 & 0x8000) != 0x0);
                class246_Sub3_Sub4_Sub2_Sub2.anInt6412 = class246_Sub3_Sub4_Sub2_Sub2.anInt6420 + (Class215.anInt1614 - -class246_Sub3_Sub4_Sub2_Sub2.anInt6394);
            }
            byte method1184 = -1;
            if ((n & 0x4000) != 0x0) {
                int leShortA = class98_Sub22_Sub1.readLEShortA((byte)(-28));
                final int int2 = class98_Sub22_Sub1.readInt2(-74);
                if (leShortA == 65535) {
                    leShortA = -1;
                }
                final int unsignedByte = class98_Sub22_Sub1.readUnsignedByte((byte)(-112));
                final int n3 = 0x7 & unsignedByte;
                int n4 = (0x7E & unsignedByte) >> -1359180605;
                if (n4 == 15) {
                    n4 = -1;
                }
                class246_Sub3_Sub4_Sub2_Sub2.method3032(n4, true, int2, n3, leShortA, -94);
            }
            if ((n & 0x2000) != 0x0) {
                method1184 = class98_Sub22_Sub1.method1184(-1498293360);
            }
            if ((0x40000 & n) != 0x0) {
                final int i = class98_Sub22_Sub1.readByteS(111);
                final int[] array = new int[i];
                final int[] array2 = new int[i];
                for (int n5 = 0; i > n5; ++n5) {
                    final int short2 = class98_Sub22_Sub1.readShort1((byte)111);
                    if (~(0xC000 & short2) != 0xFFFF3FFF) {
                        array[n5] = short2;
                    }
                    else {
                        array[n5] = Class41.method366(class98_Sub22_Sub1.readShort1((byte)(-76)), short2 << -663124336);
                    }
                    array2[n5] = class98_Sub22_Sub1.readShort((byte)127);
                }
                class246_Sub3_Sub4_Sub2_Sub2.method3038(array2, array, true);
            }
            if (~(n & 0x80) != -1) {
                int short3 = class98_Sub22_Sub1.readShort((byte)127);
                final int intReverse = class98_Sub22_Sub1.readIntReverse(true);
                if (~short3 == 0xFFFF0000) {
                    short3 = -1;
                }
                final int byteA = class98_Sub22_Sub1.readByteA(true);
                final int n6 = 0x7 & byteA;
                int n7 = 0xF & byteA >> -1521340829;
                if (~n7 == 0xFFFFFFF0) {
                    n7 = -1;
                }
                class246_Sub3_Sub4_Sub2_Sub2.method3032(n7, false, intReverse, n6, short3, -72);
            }
            if (~(n & 0x200) != -1) {
                class246_Sub3_Sub4_Sub2_Sub2.anInt6378 = class98_Sub22_Sub1.method1187((byte)(-112));
                class246_Sub3_Sub4_Sub2_Sub2.anInt6347 = class98_Sub22_Sub1.method1184(-1498293360);
                class246_Sub3_Sub4_Sub2_Sub2.anInt6362 = class98_Sub22_Sub1.method1187((byte)(-112));
                class246_Sub3_Sub4_Sub2_Sub2.anInt6392 = class98_Sub22_Sub1.method1187((byte)(-112));
                class246_Sub3_Sub4_Sub2_Sub2.anInt6390 = class98_Sub22_Sub1.readShort1((byte)92) + Class215.anInt1614;
                class246_Sub3_Sub4_Sub2_Sub2.anInt6424 = class98_Sub22_Sub1.readLEShortA((byte)(-53)) + Class215.anInt1614;
                class246_Sub3_Sub4_Sub2_Sub2.anInt6407 = class98_Sub22_Sub1.readByteA(true);
                if (!class246_Sub3_Sub4_Sub2_Sub2.aBoolean6532) {
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6347 += class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0];
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6392 += class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0];
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6378 += class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0];
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6362 += class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0];
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6434 = 1;
                }
                else {
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6347 += class246_Sub3_Sub4_Sub2_Sub2.anInt6541;
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6392 += class246_Sub3_Sub4_Sub2_Sub2.anInt6541;
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6378 += class246_Sub3_Sub4_Sub2_Sub2.anInt6521;
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6362 += class246_Sub3_Sub4_Sub2_Sub2.anInt6521;
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6434 = 0;
                }
                class246_Sub3_Sub4_Sub2_Sub2.anInt6436 = 0;
            }
            if ((n & 0x4) != 0x0) {
                class246_Sub3_Sub4_Sub2_Sub2.anInt6512 = class98_Sub22_Sub1.readLEShortA((byte)91);
                if (class246_Sub3_Sub4_Sub2_Sub2.anInt6434 == 0) {
                    class246_Sub3_Sub4_Sub2_Sub2.method3042(class246_Sub3_Sub4_Sub2_Sub2.anInt6512, -8193);
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6512 = -1;
                }
            }
            if (~(0x2 & n) != -1) {
                int short4 = class98_Sub22_Sub1.readShort1((byte)(-126));
                if (short4 == 65535) {
                    short4 = -1;
                }
                class246_Sub3_Sub4_Sub2_Sub2.anInt6364 = short4;
            }
            if (~(n & 0x10000) != -1) {
                class246_Sub3_Sub4_Sub2_Sub2.aByte6404 = class98_Sub22_Sub1.method1187((byte)(-112));
                class246_Sub3_Sub4_Sub2_Sub2.aByte6381 = class98_Sub22_Sub1.method1234(128);
                class246_Sub3_Sub4_Sub2_Sub2.aByte6368 = class98_Sub22_Sub1.method1234(128);
                class246_Sub3_Sub4_Sub2_Sub2.aByte6422 = (byte)class98_Sub22_Sub1.readUnsignedByte((byte)107);
                class246_Sub3_Sub4_Sub2_Sub2.anInt6403 = Class215.anInt1614 + class98_Sub22_Sub1.readLEShortA((byte)97);
                class246_Sub3_Sub4_Sub2_Sub2.anInt6349 = Class215.anInt1614 - -class98_Sub22_Sub1.readShortA(98);
            }
            if ((0x8 & n) != 0x0) {
                final int unsignedByte2 = class98_Sub22_Sub1.readUnsignedByte((byte)85);
                if (unsignedByte2 > 0) {
                    for (int j = 0; j < unsignedByte2; ++j) {
                        int smart = -1;
                        int n8 = -1;
                        int n9 = class98_Sub22_Sub1.readSmart(1689622712);
                        int smart2 = -1;
                        if (n9 != 32767) {
                            if (n9 == 32766) {
                                n9 = -1;
                            }
                            else {
                                n8 = class98_Sub22_Sub1.readSmart(1689622712);
                            }
                        }
                        else {
                            n9 = class98_Sub22_Sub1.readSmart(1689622712);
                            n8 = class98_Sub22_Sub1.readSmart(1689622712);
                            smart = class98_Sub22_Sub1.readSmart(1689622712);
                            smart2 = class98_Sub22_Sub1.readSmart(1689622712);
                        }
                        class246_Sub3_Sub4_Sub2_Sub2.method3037(class98_Sub22_Sub1.readUnsignedByte((byte)(-105)), false, n8, n9, Class215.anInt1614, smart2, class98_Sub22_Sub1.readSmart(1689622712), smart);
                    }
                }
            }
            if (~(n & 0x10) != -1) {
                final int[] array3 = new int[4];
                for (int n10 = 0; ~n10 > -5; ++n10) {
                    array3[n10] = class98_Sub22_Sub1.readLEShortA((byte)(-116));
                    if (~array3[n10] == 0xFFFF0000) {
                        array3[n10] = -1;
                    }
                }
                Class181.method2608(class98_Sub22_Sub1.readByteC((byte)58), class246_Sub3_Sub4_Sub2_Sub2, array3, 0);
            }
            if (~(0x40 & n) != -1) {
                final int byteA2 = class98_Sub22_Sub1.readByteA(true);
                final byte[] array4 = new byte[byteA2];
                final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array4);
                class98_Sub22_Sub1.method1190(array4, true, byteA2, 0);
                class246_Sub3_Sub4_Sub2_Sub2.method3062(Class224_Sub3_Sub1.aClass98_Sub22Array6146[n2] = class98_Sub22, (byte)73);
            }
            if (~(0x1000 & n) != -1) {
                class246_Sub3_Sub4_Sub2_Sub2.aBoolean6534 = (class98_Sub22_Sub1.readByteA(true) == 1);
            }
            if (b <= 7) {
                method710(null, false, true, (byte)(-128));
            }
            if ((n & 0x8000) != 0x0) {
                class246_Sub3_Sub4_Sub2_Sub2.aString6374 = class98_Sub22_Sub1.readString((byte)84);
                if (~class246_Sub3_Sub4_Sub2_Sub2.aString6374.charAt(0) == 0xFFFFFF81) {
                    Class98_Sub45.method1521((byte)(-96), 2, class246_Sub3_Sub4_Sub2_Sub2.aString6374 = class246_Sub3_Sub4_Sub2_Sub2.aString6374.substring(1), 0, class246_Sub3_Sub4_Sub2_Sub2.method3059(-1, false), class246_Sub3_Sub4_Sub2_Sub2.method3063(0, true), class246_Sub3_Sub4_Sub2_Sub2.aString6536);
                }
                else if (class246_Sub3_Sub4_Sub2_Sub2 == Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660) {
                    Class98_Sub45.method1521((byte)90, 2, class246_Sub3_Sub4_Sub2_Sub2.aString6374, 0, class246_Sub3_Sub4_Sub2_Sub2.method3059(-1, false), class246_Sub3_Sub4_Sub2_Sub2.method3063(0, true), class246_Sub3_Sub4_Sub2_Sub2.aString6536);
                }
                class246_Sub3_Sub4_Sub2_Sub2.anInt6384 = 150;
                class246_Sub3_Sub4_Sub2_Sub2.anInt6398 = 0;
                class246_Sub3_Sub4_Sub2_Sub2.anInt6402 = 0;
            }
            if (~(0x100 & n) != -1) {
                final int byteC = class98_Sub22_Sub1.readByteC((byte)(-109));
                final int[] array5 = new int[byteC];
                final int[] array6 = new int[byteC];
                final int[] array7 = new int[byteC];
                for (int k = 0; k < byteC; ++k) {
                    int shortA = class98_Sub22_Sub1.readShortA(79);
                    if (shortA == 65535) {
                        shortA = -1;
                    }
                    array5[k] = shortA;
                    array6[k] = class98_Sub22_Sub1.readByteC((byte)124);
                    array7[k] = class98_Sub22_Sub1.readLEShortA((byte)(-37));
                }
                Class266.method3234(class246_Sub3_Sub4_Sub2_Sub2, -3433, array7, array6, array5);
            }
            if (~(0x1 & n) != -1) {
                Class98_Sub10_Sub21.aByteArray5642[n2] = class98_Sub22_Sub1.method1184(-1498293360);
            }
            if (class246_Sub3_Sub4_Sub2_Sub2.aBoolean6532) {
                if (method1184 != 127) {
                    byte b2;
                    if (method1184 == -1) {
                        b2 = Class98_Sub10_Sub21.aByteArray5642[n2];
                    }
                    else {
                        b2 = method1184;
                    }
                    Class282.method3334((byte)37, b2, class246_Sub3_Sub4_Sub2_Sub2);
                    class246_Sub3_Sub4_Sub2_Sub2.method3057(class246_Sub3_Sub4_Sub2_Sub2.anInt6521, class246_Sub3_Sub4_Sub2_Sub2.anInt6541, b2, -1);
                }
                else {
                    class246_Sub3_Sub4_Sub2_Sub2.method3060(class246_Sub3_Sub4_Sub2_Sub2.anInt6541, class246_Sub3_Sub4_Sub2_Sub2.anInt6521, 1470);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eia.B(" + ((class246_Sub3_Sub4_Sub2_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + ((class98_Sub22_Sub1 != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    static final void method710(final String s, final boolean b, final boolean b2, final byte b3) {
        try {
            try {
                if (s.equalsIgnoreCase("commands") || s.equalsIgnoreCase("help")) {
                    Class98_Sub46.method1525("commands - This command", 114);
                    Class98_Sub46.method1525("cls - Clear console", b3 ^ 0x28);
                    Class98_Sub46.method1525("displayfps - Toggle FPS and other information", 98);
                    Class98_Sub46.method1525("renderer - Print graphics renderer information", 93);
                    Class98_Sub46.method1525("heap - Print java memory information", -83);
                    return;
                }
                if (s.equalsIgnoreCase("cls")) {
                    Class54.anInt3395 = 0;
                    Class98_Sub28.anInt4080 = 0;
                    return;
                }
                if (s.equalsIgnoreCase("displayfps")) {
                    Class91.aBoolean725 = !Class91.aBoolean725;
                    if (Class91.aBoolean725) {
                        Class98_Sub46.method1525("FPS on", b3 - 72);
                        return;
                    }
                    Class98_Sub46.method1525("FPS off", -101);
                    return;
                }
                else {
                    if (s.equals("renderer")) {
                        final Class62 method1799 = Class265.aHa1974.method1799();
                        Class98_Sub46.method1525("Vendor: " + method1799.anInt484, -123);
                        Class98_Sub46.method1525("Name: " + method1799.aString489, b3 ^ 0xFFFFFFC1);
                        Class98_Sub46.method1525("Version: " + method1799.anInt483, 52);
                        Class98_Sub46.method1525("Device: " + method1799.aString488, -115);
                        Class98_Sub46.method1525("Driver Version: " + method1799.aLong485, -82);
                        return;
                    }
                    if (b3 != 117) {
                        PlayerUpdateMask.aClass171_524 = null;
                    }
                    if (s.equals("heap")) {
                        Class98_Sub46.method1525("Heap: " + Class292.anInt3359 + "MB", b3 ^ 0xFFFFFFD5);
                        return;
                    }
                }
            }
            catch (Exception ex2) {
                Class98_Sub46.method1525(Class309.aClass309_2587.method3615(Class374.anInt3159, (byte)25), 107);
                return;
            }
            if (Class43.aClass196_375 != Class64_Sub29.aClass196_3720 || ~Class282.anInt2125 <= -3) {
                if (s.equalsIgnoreCase("errortest")) {
                    throw new RuntimeException();
                }
                if (s.equals("nativememerror")) {
                    throw new OutOfMemoryError("native(MPR");
                }
                try {
                    if (s.equalsIgnoreCase("printfps")) {
                        Class98_Sub46.method1525("FPS: " + Class338.anInt2842, -74);
                        return;
                    }
                    if (s.equalsIgnoreCase("occlude")) {
                        Class98_Sub17.aBoolean3942 = !Class98_Sub17.aBoolean3942;
                        if (Class98_Sub17.aBoolean3942) {
                            Class98_Sub46.method1525("Occlsion now on!", b3 ^ 0xD);
                            return;
                        }
                        Class98_Sub46.method1525("Occlsion now off!", 58);
                        return;
                    }
                    else {
                        if (s.equalsIgnoreCase("fpson")) {
                            Class91.aBoolean725 = true;
                            Class98_Sub46.method1525("fps debug enabled", -85);
                            return;
                        }
                        if (s.equalsIgnoreCase("fpsoff")) {
                            Class91.aBoolean725 = false;
                            Class98_Sub46.method1525("fps debug disabled", -74);
                            return;
                        }
                        if (s.equals("systemmem")) {
                            try {
                                Class98_Sub46.method1525("System memory: " + jagmisc.getAvailablePhysicalMemory() / 1048576L + "/" + Exception_Sub1.aClass98_Sub35_47.anInt4129 + "Mb", 108);
                            }
                            catch (Throwable t) {}
                            return;
                        }
                        if (s.equalsIgnoreCase("cleartext")) {
                            Class64_Sub20.aClass218_3694.method2802(b3 ^ 0x12);
                            Class98_Sub46.method1525("Text coords cleared", 95);
                            return;
                        }
                        if (s.equalsIgnoreCase("gc")) {
                            Class27.method294(true);
                            for (int n = 0; ~n > -11; ++n) {
                                System.gc();
                            }
                            final Runtime runtime = Runtime.getRuntime();
                            Class98_Sub46.method1525("mem=" + (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L) + "k", b3 - 235);
                            return;
                        }
                        if (s.equalsIgnoreCase("compact")) {
                            Class27.method294(true);
                            for (int i = 0; i < 10; ++i) {
                                System.gc();
                            }
                            final Runtime runtime2 = Runtime.getRuntime();
                            Class98_Sub46.method1525("Memory before cleanup=" + (int)((runtime2.totalMemory() - runtime2.freeMemory()) / 1024L) + "k", -95);
                            Class206.method2727(24);
                            Class27.method294(true);
                            for (int j = 0; j < 10; ++j) {
                                System.gc();
                            }
                            Class98_Sub46.method1525("Memory after cleanup=" + (int)((runtime2.totalMemory() - runtime2.freeMemory()) / 1024L) + "k", 104);
                            return;
                        }
                        if (s.equalsIgnoreCase("unloadnatives")) {
                            Class98_Sub46.method1525(Class351.method3847(5) ? "Libraries unloaded" : "Library unloading failed!", -104);
                            return;
                        }
                        if (s.equalsIgnoreCase("clientdrop")) {
                            Class98_Sub46.method1525("Dropped client connection", -67);
                            if (~Class177.anInt1376 != 0xFFFFFFF5) {
                                if (~Class177.anInt1376 == 0xFFFFFFF4) {
                                    Class76_Sub9.aBoolean3788 = true;
                                }
                            }
                            else {
                                Canvas_Sub1.method118((byte)104);
                            }
                            return;
                        }
                        if (s.equalsIgnoreCase("rotateconnectmethods")) {
                            Class98_Sub46_Sub10.aClass354_6011.method3874(0);
                            Class98_Sub46.method1525("Rotated connection methods", b3 - 206);
                            return;
                        }
                        if (s.equalsIgnoreCase("clientjs5drop")) {
                            Class98_Sub10_Sub38.aClass135_5765.method2249(-45);
                            Class98_Sub46.method1525("Dropped client js5 net queue", b3 - 220);
                            return;
                        }
                        if (s.equalsIgnoreCase("serverjs5drop")) {
                            Class98_Sub10_Sub38.aClass135_5765.method2254(b3 ^ 0x7106);
                            Class98_Sub46.method1525("Dropped server js5 net queue", -84);
                            return;
                        }
                        if (s.equalsIgnoreCase("breakcon")) {
                            Class98_Sub43_Sub2.aClass88_5907.method861(b3 ^ 0x75);
                            aa_Sub1.aClass123_3561.method2204(-1);
                            Class98_Sub10_Sub38.aClass135_5765.method2259(0);
                            Class98_Sub46.method1525("Breaking new connections for 5 seconds", -110);
                            return;
                        }
                        if (s.equalsIgnoreCase("rebuild")) {
                            Class98_Sub10.method999((byte)(-59));
                            InputStream_Sub2.method124(-109);
                            Class98_Sub46.method1525("Rebuilding map", 107);
                            return;
                        }
                        if (s.equalsIgnoreCase("rebuildprofile")) {
                            Class123.aLong1011 = Class343.method3819(-47);
                            Class270.aBoolean2031 = true;
                            Class98_Sub10.method999((byte)(-70));
                            InputStream_Sub2.method124(-115);
                            Class98_Sub46.method1525("Rebuilding map (with profiling)", -119);
                            return;
                        }
                        if (s.equalsIgnoreCase("wm1")) {
                            Class98_Sub16.method1150(1, -1, 3, -1, false);
                            if (Class146_Sub2.method2391((byte)124) != 1) {
                                Class98_Sub46.method1525("wm1 failed", 99);
                                return;
                            }
                            Class98_Sub46.method1525("wm1 succeeded", 51);
                            return;
                        }
                        else if (s.equalsIgnoreCase("wm2")) {
                            Class98_Sub16.method1150(2, -1, b3 ^ 0x76, -1, false);
                            if (~Class146_Sub2.method2391((byte)107) == 0xFFFFFFFD) {
                                Class98_Sub46.method1525("wm2 succeeded", -94);
                                return;
                            }
                            Class98_Sub46.method1525("wm2 failed", -86);
                            return;
                        }
                        else if (s.equalsIgnoreCase("wm3")) {
                            Class98_Sub16.method1150(3, 1024, 3, 768, false);
                            if (Class146_Sub2.method2391((byte)(-75)) != 3) {
                                Class98_Sub46.method1525("wm3 failed", -108);
                                return;
                            }
                            Class98_Sub46.method1525("wm3 succeeded", 106);
                            return;
                        }
                        else if (s.equalsIgnoreCase("tk0")) {
                            Class76_Sub4.method754(0, false, 103);
                            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)121) == 0) {
                                Class98_Sub46.method1525("Entered tk0", 108);
                                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062);
                                Class310.method3618(-5964);
                                s_Sub1.aBoolean5207 = false;
                                return;
                            }
                            Class98_Sub46.method1525("Failed to enter tk0", -128);
                            return;
                        }
                        else if (s.equalsIgnoreCase("tk1")) {
                            Class76_Sub4.method754(1, false, -116);
                            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)123) == 1) {
                                Class98_Sub46.method1525("Entered tk1", 110);
                                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062);
                                Class310.method3618(b3 - 6081);
                                s_Sub1.aBoolean5207 = false;
                                return;
                            }
                            Class98_Sub46.method1525("Failed to enter tk1", -104);
                            return;
                        }
                        else if (s.equalsIgnoreCase("tk2")) {
                            Class76_Sub4.method754(2, false, 71);
                            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)120) != 0xFFFFFFFD) {
                                Class98_Sub46.method1525("Failed to enter tk2", -64);
                                return;
                            }
                            Class98_Sub46.method1525("Entered tk2", -116);
                            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062);
                            Class310.method3618(-5964);
                            s_Sub1.aBoolean5207 = false;
                            return;
                        }
                        else if (s.equalsIgnoreCase("tk3")) {
                            Class76_Sub4.method754(3, false, b3 - 13);
                            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)127) != 0xFFFFFFFC) {
                                Class98_Sub46.method1525("Failed to enter tk3", 50);
                                return;
                            }
                            Class98_Sub46.method1525("Entered tk3", 97);
                            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 3, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062);
                            Class310.method3618(-5964);
                            s_Sub1.aBoolean5207 = false;
                            return;
                        }
                        else if (s.equalsIgnoreCase("tk5")) {
                            Class76_Sub4.method754(5, false, 76);
                            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)124) == 5) {
                                Class98_Sub46.method1525("Entered tk5", 111);
                                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 5, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062);
                                Class310.method3618(b3 - 6081);
                                s_Sub1.aBoolean5207 = false;
                                return;
                            }
                            Class98_Sub46.method1525("Failed to enter tk5", 88);
                            return;
                        }
                        else if (s.startsWith("setba")) {
                            if (~s.length() > -7) {
                                Class98_Sub46.method1525("Invalid buildarea value", -80);
                                return;
                            }
                            final int method1800 = PacketSender.method3607(-102, s.substring(6));
                            if (~method1800 > -1 || method1800 > Class98_Sub22_Sub2.method1262(b3 - 109, Class292.anInt3359)) {
                                Class98_Sub46.method1525("Invalid buildarea value", -63);
                                return;
                            }
                            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), method1800, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046);
                            Class310.method3618(-5964);
                            s_Sub1.aBoolean5207 = false;
                            Class98_Sub46.method1525("maxbuildarea=" + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046.method617((byte)120), -65);
                            return;
                        }
                        else if (s.startsWith("rect_debug")) {
                            if (s.length() < 10) {
                                Class98_Sub46.method1525("Invalid rect_debug value", -126);
                                return;
                            }
                            Class167.anInt1282 = PacketSender.method3607(64, s.substring(10).trim());
                            Class98_Sub46.method1525("rect_debug=" + Class167.anInt1282, -119);
                            return;
                        }
                        else {
                            if (s.equalsIgnoreCase("qa_op_test")) {
                                Class15.aBoolean169 = true;
                                Class98_Sub46.method1525("qa_op_test=" + Class15.aBoolean169, -113);
                                return;
                            }
                            if (s.equalsIgnoreCase("clipcomponents")) {
                                Class153.aBoolean1230 = !Class153.aBoolean1230;
                                Class98_Sub46.method1525("clipcomponents=" + Class153.aBoolean1230, b3 ^ 0xFFFFFFC8);
                                return;
                            }
                            if (s.startsWith("bloom")) {
                                final boolean method1801 = Class265.aHa1974.method1768();
                                if (!Class98_Sub5_Sub1.method966(29089, !method1801)) {
                                    Class98_Sub46.method1525("Failed to enable bloom", 126);
                                    return;
                                }
                                if (!method1801) {
                                    Class98_Sub46.method1525("Bloom enabled", 46);
                                    return;
                                }
                                Class98_Sub46.method1525("Bloom disabled", -85);
                                return;
                            }
                            else if (s.equalsIgnoreCase("tween")) {
                                if (!Class357.aBoolean3027) {
                                    Class357.aBoolean3027 = true;
                                    Class98_Sub46.method1525("Forced tweening ENABLED!", 123);
                                    return;
                                }
                                Class357.aBoolean3027 = false;
                                Class98_Sub46.method1525("Forced tweening disabled.", b3 ^ 0xFFFFFFD3);
                                return;
                            }
                            else if (s.equalsIgnoreCase("shiftclick")) {
                                if (Class109.aBoolean934) {
                                    Class98_Sub46.method1525("Shift-click disabled.", b3 - 55);
                                    Class109.aBoolean934 = false;
                                    return;
                                }
                                Class98_Sub46.method1525("Shift-click ENABLED!", b3 ^ 0x11);
                                Class109.aBoolean934 = true;
                                return;
                            }
                            else {
                                if (s.equalsIgnoreCase("getcgcoord")) {
                                    Class98_Sub46.method1525("x:" + (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 >> -766503447) + " z:" + (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 >> 1009373513), -102);
                                    return;
                                }
                                if (s.equalsIgnoreCase("getheight")) {
                                    Class98_Sub46.method1525("Height: " + Class78.aSArray594[Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088].method3420(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 >> -447569975, -12639, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 >> 1269250985), 88);
                                    return;
                                }
                                if (s.equalsIgnoreCase("resetminimap")) {
                                    Class332_Sub2.aClass207_5423.method2766(16);
                                    Class332_Sub2.aClass207_5423.method2760((byte)(-116));
                                    Class98_Sub10_Sub23.aClass335_5662.method3770(b3 - 83);
                                    Class216.aClass341_1622.method3808(0);
                                    InputStream_Sub2.method124(-79);
                                    Class98_Sub46.method1525("Minimap reset", 103);
                                    return;
                                }
                                if (s.startsWith("mc")) {
                                    if (Class265.aHa1974.method1810()) {
                                        int int1 = Integer.parseInt(s.substring(3));
                                        if (~int1 <= -2) {
                                            if (int1 > 4) {
                                                int1 = 4;
                                            }
                                        }
                                        else {
                                            int1 = 1;
                                        }
                                        Class337_Sub1.anInt5499 = int1;
                                        Class98_Sub10.method999((byte)(-47));
                                        Class98_Sub46.method1525("Render cores now: " + Class337_Sub1.anInt5499, -93);
                                        return;
                                    }
                                    Class98_Sub46.method1525("Current toolkit doesn't support multiple cores", 121);
                                    return;
                                }
                                else {
                                    if (s.startsWith("cachespace")) {
                                        Class98_Sub46.method1525("I(s): " + Class69_Sub2.aClass79_5334.method799(1551398789) + "/" + Class69_Sub2.aClass79_5334.method793(18), 53);
                                        Class98_Sub46.method1525("I(m): " + Class64_Sub5.aClass79_3650.method799(1551398789) + "/" + Class64_Sub5.aClass79_3650.method793(16), -122);
                                        Class98_Sub46.method1525("O(s): " + Class98_Sub46_Sub19.aClass205_6068.aClass74_1561.method726(true) + "/" + Class98_Sub46_Sub19.aClass205_6068.aClass74_1561.method730(-19536), b3 - 197);
                                        return;
                                    }
                                    if (s.equalsIgnoreCase("getcamerapos")) {
                                        Class98_Sub46.method1525("Pos: " + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 + "," + ((Class98_Sub46_Sub10.anInt6020 >> 1855638953) - -Class272.anInt2038 >> -82400218) + "," + ((Class134.anInt3461 >> -583047319) + aa_Sub2.anInt3562 >> 559817670) + "," + (0x3F & Class272.anInt2038 + (Class98_Sub46_Sub10.anInt6020 >> 86672457)) + "," + (0x3F & (Class134.anInt3461 >> 1182901385) + aa_Sub2.anInt3562) + " Height: " + (Class98_Sub46_Sub2_Sub2.method1538(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, Class134.anInt3461, Class98_Sub46_Sub10.anInt6020, 24111) + -Class79.anInt601), 96);
                                        Class98_Sub46.method1525("Look: " + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 + "," + (Exception_Sub1.anInt44 - -Class272.anInt2038 >> -1575614138) + "," + (aa_Sub2.anInt3562 + Class98_Sub15.anInt3915 >> 1704567462) + "," + (0x3F & Class272.anInt2038 + Exception_Sub1.anInt44) + "," + (0x3F & Class98_Sub15.anInt3915 - -aa_Sub2.anInt3562) + " Height: " + (Class98_Sub46_Sub2_Sub2.method1538(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, Class98_Sub15.anInt3915, Exception_Sub1.anInt44, 24111) - Class303.anInt2530), 122);
                                        return;
                                    }
                                    if (s.equals("renderprofile") || s.equals("rp")) {
                                        Class170.aBoolean1313 = !Class170.aBoolean1313;
                                        Class265.aHa1974.method1761(Class170.aBoolean1313);
                                        Class228.method2862(-123);
                                        Class98_Sub46.method1525("showprofiling=" + Class170.aBoolean1313, 89);
                                        return;
                                    }
                                    if (s.startsWith("performancetest")) {
                                        int int2 = -1;
                                        int int3 = 1000;
                                        if (~s.length() < -16) {
                                            final String[] method1802 = Class112.method2142(s, ' ', false);
                                            try {
                                                if (~method1802.length < -2) {
                                                    int3 = Integer.parseInt(method1802[1]);
                                                }
                                            }
                                            catch (Throwable t2) {}
                                            try {
                                                if (~method1802.length < -3) {
                                                    int2 = Integer.parseInt(method1802[2]);
                                                }
                                            }
                                            catch (Throwable t3) {}
                                        }
                                        if (~int2 != 0x0) {
                                            Class98_Sub46.method1525("Performance: " + Class66.method683((byte)(-86), int3, int2), -64);
                                            return;
                                        }
                                        Class98_Sub46.method1525("Java toolkit: " + Class66.method683((byte)(-109), int3, 0), -108);
                                        Class98_Sub46.method1525("SSE toolkit:  " + Class66.method683((byte)(-128), int3, 2), b3 ^ 0x2B);
                                        Class98_Sub46.method1525("D3D toolkit:  " + Class66.method683((byte)(-117), int3, 3), -61);
                                        Class98_Sub46.method1525("GL toolkit:   " + Class66.method683((byte)(-104), int3, 1), 126);
                                        Class98_Sub46.method1525("GLX toolkit:  " + Class66.method683((byte)(-92), int3, 5), b3 + 10);
                                        return;
                                    }
                                    else {
                                        if (s.equals("nonpcs")) {
                                            Class237_Sub1.aBoolean5044 = !Class237_Sub1.aBoolean5044;
                                            Class98_Sub46.method1525("nonpcs=" + Class237_Sub1.aBoolean5044, 94);
                                            return;
                                        }
                                        if (s.equals("autoworld")) {
                                            Class98_Sub10_Sub25.method1080((byte)(-86));
                                            Class98_Sub46.method1525("auto world selected", -63);
                                            return;
                                        }
                                        if (s.startsWith("switchworld")) {
                                            final int int4 = Integer.parseInt(s.substring(12));
                                            Class98_Sub12.method1131(-8804, int4, Class275.method3283((byte)113, int4).aString3634);
                                            Class98_Sub46.method1525("switched", b3 - 77);
                                            return;
                                        }
                                        if (s.equals("getworld")) {
                                            Class98_Sub46.method1525("w: " + Class98_Sub46_Sub10.aClass354_6011.anInt3011, b3 - 196);
                                            return;
                                        }
                                        if (s.startsWith("pc")) {
                                            final Class98_Sub11 method1803 = Class246_Sub3_Sub4.method3023(260, Class246_Sub4_Sub2.aClass171_6185, Class331.aClass117_2811);
                                            method1803.aClass98_Sub22_Sub1_3865.method1194(0, b3 ^ 0x48);
                                            final int anInt3991 = method1803.aClass98_Sub22_Sub1_3865.anInt3991;
                                            final int index = s.indexOf(" ", 4);
                                            method1803.aClass98_Sub22_Sub1_3865.method1188(s.substring(3, index), (byte)113);
                                            Class284_Sub1_Sub1.method3368(b3 + 10, s.substring(index), method1803.aClass98_Sub22_Sub1_3865);
                                            method1803.aClass98_Sub22_Sub1_3865.method1211((byte)118, -anInt3991 + method1803.aClass98_Sub22_Sub1_3865.anInt3991);
                                            Class98_Sub10_Sub29.sendPacket(false, method1803);
                                            return;
                                        }
                                        if (s.equals("savevarcs")) {
                                            Class23.method283((byte)(-80));
                                            Class98_Sub46.method1525("perm varcs saved", 103);
                                            return;
                                        }
                                        if (s.equals("scramblevarcs")) {
                                            for (int n2 = 0; ~Class76_Sub5.anIntArray3744.length < ~n2; ++n2) {
                                                if (Class140.aBooleanArray3246[n2]) {
                                                    Class76_Sub5.anIntArray3744[n2] = (int)(Math.random() * 99999.0);
                                                    if (Math.random() > 0.5) {
                                                        final int[] anIntArray3744 = Class76_Sub5.anIntArray3744;
                                                        final int n3 = n2;
                                                        anIntArray3744[n3] *= -1;
                                                    }
                                                }
                                            }
                                            Class23.method283((byte)(-118));
                                            Class98_Sub46.method1525("perm varcs scrambled", -114);
                                            return;
                                        }
                                        if (s.equals("showcolmap")) {
                                            Class44.aBoolean378 = true;
                                            InputStream_Sub2.method124(-119);
                                            Class98_Sub46.method1525("colmap is shown", -67);
                                            return;
                                        }
                                        if (s.equals("hidecolmap")) {
                                            Class44.aBoolean378 = false;
                                            InputStream_Sub2.method124(122);
                                            Class98_Sub46.method1525("colmap is hidden", b3 ^ 0xFFFFFFD0);
                                            return;
                                        }
                                        if (s.equals("resetcache")) {
                                            Class98_Sub10_Sub15.method1050((byte)114);
                                            Class98_Sub46.method1525("Caches reset", -104);
                                            return;
                                        }
                                        if (s.equals("profilecpu")) {
                                            Class98_Sub46.method1525(String.valueOf(Class278_Sub1.method3320(12)) + "ms", b3 - 204);
                                            return;
                                        }
                                        if (s.startsWith("getclientvarpbit")) {
                                            Class98_Sub46.method1525("varpbit=" + Class75.aClass140_584.method7(Integer.parseInt(s.substring(17)), b3 ^ 0x1CB8), 65);
                                            return;
                                        }
                                        if (s.startsWith("getclientvarp")) {
                                            Class98_Sub46.method1525("varp=" + Class75.aClass140_584.method6(Integer.parseInt(s.substring(14)), b3 ^ 0xFFFFFFF4), -83);
                                            return;
                                        }
                                        if (s.startsWith("directlogin")) {
                                            final String[] method1804 = Class112.method2142(s.substring(12), ' ', false);
                                            if (~method1804.length <= -3) {
                                                Class251.method3171(method1804[1], -17877, method1804[0], (method1804.length > 2) ? Integer.parseInt(method1804[2]) : 0);
                                                return;
                                            }
                                        }
                                        if (s.startsWith("csprofileclear")) {
                                            Class247.method3147();
                                            return;
                                        }
                                        if (s.startsWith("csprofileoutputc")) {
                                            Class247.method3143(100, false);
                                            return;
                                        }
                                        if (s.startsWith("csprofileoutputt")) {
                                            Class247.method3143(10, true);
                                            return;
                                        }
                                        if (s.startsWith("texsize")) {
                                            Class265.aHa1974.method1778(Integer.parseInt(s.substring(8)));
                                            return;
                                        }
                                        if (s.equals("soundstreamcount")) {
                                            Class98_Sub46.method1525("Active streams: " + Class81.aClass98_Sub31_Sub3_619.method1377(), -93);
                                            return;
                                        }
                                        if (s.equals("autosetup")) {
                                            Class98_Sub28.method1306((byte)(-106));
                                            Class98_Sub46.method1525("Complete. Toolkit now: " + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)123), b3 - 52);
                                            return;
                                        }
                                        if (s.equals("errormessage")) {
                                            Class98_Sub46.method1525(Class315.aClient3529.method94(0), 58);
                                            return;
                                        }
                                        if (s.equals("heapdump")) {
                                            if (Class88.aString699.startsWith("win")) {
                                                Class98_Sub30.method1319(0, new File("C:\\Temp\\heap.dump"), false);
                                            }
                                            else {
                                                Class98_Sub30.method1319(0, new File("/tmp/heap.dump"), false);
                                            }
                                            Class98_Sub46.method1525("Done", -115);
                                            return;
                                        }
                                        if (s.equals("os")) {
                                            Class98_Sub46.method1525("Name: " + Class88.aString699, b3 - 234);
                                            Class98_Sub46.method1525("Arch: " + Class88.aString690, -80);
                                            Class98_Sub46.method1525("Ver: " + Class88.aString676, -100);
                                            return;
                                        }
                                        if (s.startsWith("w2debug")) {
                                            Class98_Sub10_Sub14.anInt5614 = Integer.parseInt(s.substring(8, 9));
                                            Class98_Sub10.method999((byte)124);
                                            Class98_Sub46.method1525("Toggled!", 74);
                                            return;
                                        }
                                        if (s.startsWith("ortho ")) {
                                            final int index2 = s.indexOf(32);
                                            if (index2 < 0) {
                                                Class98_Sub46.method1525("Syntax: ortho <n>", -66);
                                                return;
                                            }
                                            final int method1805 = PacketSender.method3607(84, s.substring(index2 + 1));
                                            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), method1805, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub18_4071);
                                            Class310.method3618(-5964);
                                            s_Sub1.aBoolean5207 = false;
                                            Class230.method2871(-45);
                                            if (~method1805 != ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub18_4071.method627((byte)124)) {
                                                Class98_Sub46.method1525("Failed to change ortho mode", -94);
                                                return;
                                            }
                                            Class98_Sub46.method1525("Successfully changed ortho mode", -104);
                                            return;
                                        }
                                        else if (s.startsWith("orthozoom ")) {
                                            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub18_4071.method627((byte)122) == 0) {
                                                Class98_Sub46.method1525("enable ortho mode first (use 'ortho <n>')", -114);
                                                return;
                                            }
                                            Class16.anInt199 = PacketSender.method3607(-52, s.substring(s.indexOf(32) + 1));
                                            Class98_Sub46.method1525("orthozoom=" + Class16.anInt199, -124);
                                            return;
                                        }
                                        else {
                                            if (s.startsWith("orthotilesize ")) {
                                                final int method1806 = PacketSender.method3607(-54, s.substring(1 + s.indexOf(32)));
                                                Class341.anInt2856 = (Class356.anInt3018 = method1806);
                                                Class98_Sub46.method1525("ortho tile size=" + method1806, -73);
                                                Class230.method2871(-37);
                                                return;
                                            }
                                            if (s.equals("orthocamlock")) {
                                                Class69.aBoolean3223 = !Class69.aBoolean3223;
                                                Class98_Sub46.method1525("ortho camera lock is " + (Class69.aBoolean3223 ? "on" : "off"), 53);
                                                return;
                                            }
                                            if (s.startsWith("setoutput ")) {
                                                File file = new File(s.substring(10));
                                                if (file.exists()) {
                                                    file = new File(s.substring(10) + "." + Class343.method3819(-47) + ".log");
                                                    if (file.exists()) {
                                                        Class98_Sub46.method1525("file already exists!", -106);
                                                        return;
                                                    }
                                                }
                                                if (Class264.aFileOutputStream1969 != null) {
                                                    Class264.aFileOutputStream1969.close();
                                                    Class264.aFileOutputStream1969 = null;
                                                }
                                                try {
                                                    Class264.aFileOutputStream1969 = new FileOutputStream(file);
                                                }
                                                catch (FileNotFoundException ex3) {
                                                    Class98_Sub46.method1525("Could not create " + file.getName(), b3 - 15);
                                                }
                                                catch (SecurityException ex4) {
                                                    Class98_Sub46.method1525("Cannot write to " + file.getName(), 67);
                                                }
                                                return;
                                            }
                                            if (s.equals("closeoutput")) {
                                                if (Class264.aFileOutputStream1969 != null) {
                                                    Class264.aFileOutputStream1969.close();
                                                }
                                                Class264.aFileOutputStream1969 = null;
                                                return;
                                            }
                                            if (s.startsWith("runscript ")) {
                                                final File file2 = new File(s.substring(10));
                                                if (!file2.exists()) {
                                                    Class98_Sub46.method1525("No such file", 45);
                                                    return;
                                                }
                                                final byte[] method1807 = Class273.method3281(b3 ^ 0xFFFFFFFC, file2);
                                                if (method1807 == null) {
                                                    Class98_Sub46.method1525("Failed to read file", b3 ^ 0x2B);
                                                    return;
                                                }
                                                Class98_Sub10_Sub13.method1044((byte)91, Class112.method2142(Class98_Sub32.method1435(Class69_Sub2.method707(method1807, true), "", (byte)(-110), '\r'), '\n', false));
                                            }
                                            if (s.startsWith("zoom ")) {
                                                final short aShort1057 = (short)PacketSender.method3607(66, s.substring(5));
                                                if (aShort1057 > 0) {
                                                    Class135.aShort1057 = aShort1057;
                                                }
                                                return;
                                            }
                                            if (~Class177.anInt1376 == 0xFFFFFFF5) {
                                                final Class98_Sub11 method1808 = Class246_Sub3_Sub4.method3023(260, Class309.aClass171_2650, Class331.aClass117_2811);
                                                method1808.aClass98_Sub22_Sub1_3865.method1194(s.length() + 3, b3 ^ 0x25);
                                                method1808.aClass98_Sub22_Sub1_3865.method1194(b2 ? 1 : 0, -61);
                                                method1808.aClass98_Sub22_Sub1_3865.method1194(b ? 1 : 0, 93);
                                                method1808.aClass98_Sub22_Sub1_3865.method1188(s, (byte)113);
                                                Class98_Sub10_Sub29.sendPacket(false, method1808);
                                            }
                                            if (s.startsWith("fps ") && Class64_Sub29.aClass196_3720 != Class43.aClass196_375) {
                                                Class342.method3815(PacketSender.method3607(b3 ^ 0x59, s.substring(4)), 59);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                catch (Exception ex5) {
                    Class98_Sub46.method1525(Class309.aClass309_2587.method3615(Class374.anInt3159, (byte)25), -117);
                    return;
                }
            }
            if (Class177.anInt1376 != 10) {
                Class98_Sub46.method1525(Class309.aClass309_2589.method3615(Class374.anInt3159, (byte)25) + s, 71);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eia.A(" + ((s != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + b3 + ')');
        }
    }
    
    public static void method711(final int n) {
        try {
            if (n == -23308) {
                PlayerUpdateMask.anIntArrayArray528 = null;
                PlayerUpdateMask.aClass207_525 = null;
                PlayerUpdateMask.aClass171_524 = null;
                PlayerUpdateMask.aClass259Array527 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eia.C(" + n + ')');
        }
    }
    
    static {
        PlayerUpdateMask.anInt526 = 0;
        PlayerUpdateMask.aClass171_524 = new OutgoingOpcode(51, -1);
        PlayerUpdateMask.anIntArrayArray528 = new int[128][128];
    }
}
