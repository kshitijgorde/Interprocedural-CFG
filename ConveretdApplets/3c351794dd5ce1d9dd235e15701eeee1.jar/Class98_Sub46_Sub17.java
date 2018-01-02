import jagex3.jagmisc.jagmisc;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub17 extends Class98_Sub46
{
    static int[][] anIntArrayArray6049;
    static Class354 aClass354_6050;
    int anInt6051;
    String aString6052;
    int anInt6053;
    int anInt6054;
    
    final long method1620(final byte b) {
        try {
            if (b > -10) {
                this.method1626((byte)(-40));
            }
            return Long.MAX_VALUE & super.aLong4259;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kw.C(" + b + ')');
        }
    }
    
    final void method1621(final int n) {
        try {
            super.aLong4259 = (500L + Class343.method3819(-47) | (Long.MIN_VALUE & super.aLong4259));
            if (n != 0) {
                this.anInt6053 = -123;
            }
            Class98_Sub10_Sub34.aClass215_5728.method2785(this, n - 43);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kw.E(" + n + ')');
        }
    }
    
    public static void method1622(final byte b) {
        try {
            if (b != 20) {
                Class98_Sub46_Sub17.aClass354_6050 = null;
            }
            Class98_Sub46_Sub17.anIntArrayArray6049 = null;
            Class98_Sub46_Sub17.aClass354_6050 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kw.A(" + b + ')');
        }
    }
    
    final int method1623(final int n) {
        try {
            if (n >= -89) {
                this.anInt6054 = 2;
            }
            return (int)super.aLong832;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kw.G(" + n + ')');
        }
    }
    
    static final int method1624(final int n) {
        try {
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub2_4061.method560((byte)123) == -1) {
                for (int n2 = 0; ~Class329.anInt2765 < ~n2; ++n2) {
                    if (Class21_Sub1.anInterface7Array5385[n2].method15(13313) == 's' || Class21_Sub1.anInterface7Array5385[n2].method15(13313) == 'S') {
                        Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub2_4061);
                        Class82.aBoolean626 = true;
                        break;
                    }
                }
            }
            if (Class45.aClass75_381 == Class75.aClass75_558) {
                final Runtime runtime = Runtime.getRuntime();
                final int n3 = (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
                final long method3819 = Class343.method3819(-47);
                if (~Class98_Sub10_Sub18.aLong5627 == -1L) {
                    Class98_Sub10_Sub18.aLong5627 = method3819;
                }
                if (n3 > 16384 && ~(method3819 + -Class98_Sub10_Sub18.aLong5627) > -5001L) {
                    if (method3819 - Class53.aLong428 > 1000L) {
                        System.gc();
                        Class53.aLong428 = method3819;
                    }
                    return 0;
                }
            }
            if (Class45.aClass75_381 == Class75.aClass75_559) {
                if (Class161.aClass109_1261 == null) {
                    Class161.aClass109_1261 = new Class109(Class98_Sub10_Sub38.aClass135_5765, Class168.aClass253_1290, Class300.aBigInteger2501, Class69.aBigInteger3213);
                }
                if (!Class161.aClass109_1261.method1735(5)) {
                    return 0;
                }
                Class320.method3665((byte)69, null, 0, true);
                Class98_Sub5_Sub2.aBoolean5535 = !Class116.method2162(false);
                Class1.aClass207_67 = Class42_Sub3.method394(-79, false, 1, Class98_Sub5_Sub2.aBoolean5535 ? 34 : 32);
                Class224_Sub1.aClass207_5035 = Class42_Sub3.method394(-50, false, 1, 33);
                Class36.aClass207_348 = Class42_Sub3.method394(-67, false, 1, 13);
            }
            if (Class45.aClass75_381 == Class75.aClass75_561) {
                final int n4 = Class100.aClass339_Sub1Array844[33].method3795(-90) + Class100.aClass339_Sub1Array844[Class98_Sub5_Sub2.aBoolean5535 ? 34 : 32].method3795(-38) + Class100.aClass339_Sub1Array844[13].method3795(-26) + (Class224_Sub1.aClass207_5035.method2730((byte)108) ? 100 : Class224_Sub1.aClass207_5035.method2762((byte)(-49)));
                if (~n4 != 0xFFFFFE6F) {
                    return n4 / 4;
                }
                Class284.anInt2162 = Class1.aClass207_67.method2735((byte)(-50));
                Class118.anInt979 = Class224_Sub1.aClass207_5035.method2735((byte)(-48));
                Class84.method834(Class1.aClass207_67, 78);
                final int method3820 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub4_4053.method568((byte)121);
                Class3.aClass282_76 = new Class282(Class4.aClass279_86, Class374.anInt3159, Class224_Sub1.aClass207_5035);
                int[] array = Class3.aClass282_76.method3335(method3820, (byte)94);
                if (array.length == 0) {
                    array = Class3.aClass282_76.method3335(0, (byte)90);
                }
                final Class362 class362 = new Class362(Class1.aClass207_67, Class36.aClass207_348);
                if (array.length > 0) {
                    Class39_Sub1.anInterface10Array3592 = new Interface10[array.length];
                    for (int n5 = 0; ~n5 > ~Class39_Sub1.anInterface10Array3592.length; ++n5) {
                        Class39_Sub1.anInterface10Array3592[n5] = new Class210(Class3.aClass282_76.method3336(-123, array[n5]), class362);
                    }
                }
            }
            if (Class45.aClass75_381 == Class75.aClass75_562) {
                Class77.method775(3, Class36.aClass207_348, Class121.method2192(-125), Class1.aClass207_67);
            }
            if (Class45.aClass75_381 == Class75.aClass75_563) {
                final int method3821 = Class361.method3917(-13);
                final int method3822 = Class35.method332(2);
                if (method3822 > method3821) {
                    return 100 * method3821 / method3822;
                }
            }
            if (Class75.aClass75_564 == Class45.aClass75_381) {
                if (Class39_Sub1.anInterface10Array3592 != null && ~Class39_Sub1.anInterface10Array3592.length < -1) {
                    if (~Class39_Sub1.anInterface10Array3592[0].method26(-794) > -101) {
                        return 0;
                    }
                    if (~Class39_Sub1.anInterface10Array3592.length < -2 && Class3.aClass282_76.method3337(1) && ~Class39_Sub1.anInterface10Array3592[1].method26(-794) > -101) {
                        return 0;
                    }
                }
                Class98_Sub10_Sub19.method1058((byte)(-128), Class265.aHa1974);
                Class93_Sub2.method910(Class265.aHa1974, (byte)(-116));
                Class61.method538(1, false);
            }
            if (Class45.aClass75_381 == Class75.aClass75_565) {
                for (int i = 0; i < 4; ++i) {
                    Class167.aClass243Array1281[i] = Class299.method3509(2742, Class98_Sub10_Sub7.anInt5572, Class165.anInt1276);
                }
            }
            if (Class45.aClass75_381 == Class75.aClass75_566) {
                Class332_Sub2.aClass207_5423 = Class42_Sub3.method394(-119, false, 1, 8);
                Class94.aClass207_796 = Class42_Sub3.method394(-114, false, 1, 0);
                Class323.aClass207_2716 = Class42_Sub3.method394(-115, false, 1, 1);
                client.aClass207_3549 = Class42_Sub3.method394(-116, false, 1, 2);
                Class64_Sub20.aClass207_3697 = Class42_Sub3.method394(-126, false, 1, 3);
                Class76_Sub2.aClass207_3733 = Class42_Sub3.method394(-92, false, 1, 4);
                Class234.aClass207_1748 = Class42_Sub3.method394(-54, true, 1, 5);
                Class98_Sub10_Sub1.aClass207_5544 = Class42_Sub3.method394(-115, true, 1, 6);
                Class76_Sub9.aClass207_3787 = Class42_Sub3.method394(-92, false, 1, 7);
                Class98_Sub22.aClass207_3995 = Class42_Sub3.method394(-121, false, 1, 9);
                Class98_Sub40.aClass207_4198 = Class42_Sub3.method394(-93, false, 1, 10);
                Class61.aClass207_481 = Class42_Sub3.method394(-100, false, 1, 11);
                Class52.aClass207_3494 = Class42_Sub3.method394(-120, false, 1, 12);
                Class196.aClass207_1512 = Class42_Sub3.method394(-91, false, 1, 14);
                Class119_Sub2.aClass207_4726 = Class42_Sub3.method394(-92, false, 1, 15);
                Class375.aClass207_3167 = Class42_Sub3.method394(-87, false, 1, 16);
                Class98_Sub10_Sub24.aClass207_5668 = Class42_Sub3.method394(-49, false, 1, 17);
                Class234.aClass207_1751 = Class42_Sub3.method394(-90, false, 1, 18);
                Class208.aClass207_1581 = Class42_Sub3.method394(-62, false, 1, 19);
                Class246_Sub3.aClass207_5087 = Class42_Sub3.method394(-111, false, 1, 20);
                PlayerUpdateMask.aClass207_525 = Class42_Sub3.method394(-123, false, 1, 21);
                Class98_Sub46_Sub19.aClass207_6067 = Class42_Sub3.method394(-124, false, 1, 22);
                Class257.aClass207_1947 = Class42_Sub3.method394(-113, true, 1, 23);
                Class81.aClass207_622 = Class42_Sub3.method394(-81, false, 1, 24);
                Class322.aClass207_2714 = Class42_Sub3.method394(-82, false, 1, 25);
                Class64_Sub20.aClass207_3695 = Class42_Sub3.method394(-111, true, 1, 26);
                Class245.aClass207_1864 = Class42_Sub3.method394(-96, false, 1, 27);
                Class253.aClass207_1932 = Class42_Sub3.method394(-80, true, 1, 28);
                Class111_Sub3.aClass207_4715 = Class42_Sub3.method394(-119, false, 1, 29);
                Class245.aClass207_1862 = Class42_Sub3.method394(-102, true, 1, 30);
                Class212.aClass207_1603 = Class42_Sub3.method394(-108, true, 1, 31);
                Class45.aClass207_385 = Class42_Sub3.method394(-121, true, 2, 36);
            }
            if (Class75.aClass75_567 == Class45.aClass75_381) {
                int anInt3635 = 0;
                for (int j = 0; j < 37; ++j) {
                    if (Class100.aClass339_Sub1Array844[j] != null) {
                        anInt3635 += Class100.aClass339_Sub1Array844[j].method3795(-51) * Class98_Sub10_Sub10.anIntArray5590[j] / 100;
                    }
                }
                if (~anInt3635 != 0xFFFFFF9B) {
                    if (Class53_Sub1.anInt3635 < 0) {
                        Class53_Sub1.anInt3635 = anInt3635;
                    }
                    return 100 * (anInt3635 + -Class53_Sub1.anInt3635) / (100 - Class53_Sub1.anInt3635);
                }
                Class1.method165((byte)(-107), Class332_Sub2.aClass207_5423);
                Class77.method775(3, Class36.aClass207_348, Class121.method2192(-125), Class332_Sub2.aClass207_5423);
            }
            if (Class45.aClass75_381 == Class75.aClass75_568) {
                if (~Class94.anInt795 == 0x0) {
                    Class94.anInt795 = Class98_Sub10_Sub1.aClass207_5544.method2750((byte)(-109), "scape main");
                }
                Class98_Sub17_Sub1.method1157(-120);
                Class61.method538(2, false);
            }
            if (Class45.aClass75_381 == Class75.aClass75_569) {
                Class365.method3941(Class245.aClass207_1862, Class98_Sub43_Sub2.aClass88_5907, -1);
            }
            if (Class75.aClass75_570 == Class45.aClass75_381) {
                final int method3823 = Class93_Sub1.method903((byte)(-128));
                if (~method3823 > -101) {
                    return method3823;
                }
                Class276.method3284(Class253.aClass207_1932.method2733(1, 14), -65536);
                Class155.method2495(Class253.aClass207_1932.method2733(3, 82), (byte)(-25));
            }
            if (Class75.aClass75_571 == Class45.aClass75_381) {
                if (~Class64_Sub10.anInt3666 != 0x0 && !Class76_Sub9.aClass207_3787.method2751(0, Class64_Sub10.anInt3666, -6329)) {
                    return 99;
                }
                Class284_Sub2_Sub2.aD6203 = new Class260(Class64_Sub20.aClass207_3695, Class98_Sub22.aClass207_3995, Class332_Sub2.aClass207_5423);
                Class98_Sub43_Sub1.aClass365_5897 = new Class365(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class370.aClass257_3136 = new Class257(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class18.aClass11_213 = new Class11(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549, Class332_Sub2.aClass207_5423);
                Class98_Sub10_Sub16.aClass29_5620 = new Class29(Class4.aClass279_86, Class374.anInt3159, Class98_Sub10_Sub24.aClass207_5668);
                Class199.aClass32_1531 = new Class32(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class82.aClass153_630 = new Class153(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class246_Sub3_Sub1.aClass121_6150 = new Class121(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549, Class332_Sub2.aClass207_5423);
                Class149.aClass83_1205 = new Class83(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549, Class76_Sub9.aClass207_3787);
                Class98_Sub46_Sub14.aClass8_5378 = new Class8(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class21_Sub1.aClass269_5383 = new Class269(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class130.aClass302_1028 = new Class302(Class4.aClass279_86, Class374.anInt3159, true, Class375.aClass207_3167, Class76_Sub9.aClass207_3787);
                Class216.aClass341_1622 = new Class341(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549, Class332_Sub2.aClass207_5423);
                Class98_Sub10_Sub23.aClass335_5662 = new Class335(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549, Class332_Sub2.aClass207_5423);
                Class4.aClass301_85 = new Class301(Class4.aClass279_86, Class374.anInt3159, true, Class234.aClass207_1751, Class76_Sub9.aClass207_3787);
                Class98_Sub46_Sub19.aClass205_6068 = new Class205(Class4.aClass279_86, Class374.anInt3159, true, Class98_Sub43_Sub1.aClass365_5897, Class208.aClass207_1581, Class76_Sub9.aClass207_3787);
                Class303.aClass13_2529 = new Class13(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class151_Sub7.aClass183_5001 = new Class183(Class4.aClass279_86, Class374.anInt3159, Class246_Sub3.aClass207_5087, Class94.aClass207_796, Class323.aClass207_2716);
                Class373_Sub2.aClass59_5470 = new Class59(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class101.aClass115_857 = new Class115(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class196.aClass304_1509 = new Class304(Class4.aClass279_86, Class374.anInt3159, PlayerUpdateMask.aClass207_525, Class76_Sub9.aClass207_3787);
                Class62.aClass264_487 = new Class264(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class111_Sub1.aClass239_4685 = new Class239(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class345.aClass132_2889 = new Class132(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class17.aClass198_205 = new Class198(Class4.aClass279_86, Class374.anInt3159, Class98_Sub46_Sub19.aClass207_6067);
                Class134.aClass139_3465 = new Class139(Class4.aClass279_86, Class374.anInt3159, client.aClass207_3549);
                Class94.method916(-99, Class64_Sub20.aClass207_3697, Class36.aClass207_348, Class332_Sub2.aClass207_5423, Class76_Sub9.aClass207_3787);
                Class253.method3181(Class111_Sub3.aClass207_4715, -1);
                Class218.aClass212_1634 = new Class212(Class374.anInt3159, Class81.aClass207_622, Class322.aClass207_2714);
                Class52.aClass280_3500 = new Class280(Class374.anInt3159, Class81.aClass207_622, Class322.aClass207_2714, new Class255());
                Class313.method3625(-1439);
                Class130.aClass302_1028.method3552(~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)123) == -1, 60);
                Class75.aClass140_584 = new Class140();
                Class98_Sub46_Sub13_Sub1.method1593((byte)54);
                Class373_Sub1_Sub1.method3970(Class245.aClass207_1864, (byte)(-98));
                Class250.method3168(Class76_Sub9.aClass207_3787, true, Class284_Sub2_Sub2.aD6203);
                Class64_Sub22.method643(-256, new Class213(Class98_Sub40.aClass207_4198.method2739("huffman", "", -32734)));
                try {
                    jagmisc.init();
                }
                catch (Throwable t) {}
                OutputStream_Sub1.aClass240_36 = Class109.method1737(72);
                Exception_Sub1.aClass98_Sub35_47 = new Class98_Sub35(true, Class98_Sub43_Sub2.aClass88_5907);
            }
            if (Class45.aClass75_381 == Class75.aClass75_573) {
                final int n6 = Class98_Sub10_Sub33.method1101(-11286, Class332_Sub2.aClass207_5423) - -Class98_Sub46_Sub15.method1610(true, (byte)90);
                final int n7 = Class98_Sub48.method1660(84) - -Class35.method332(2);
                if (~n6 > ~n7) {
                    return n6 * 100 / n7;
                }
            }
            if (Class45.aClass75_381 == Class75.aClass75_574) {
                Class278.method3298(Class257.aClass207_1947, Class199.aClass32_1531, Class82.aClass153_630, Class130.aClass302_1028, Class216.aClass341_1622, Class98_Sub10_Sub23.aClass335_5662, Class75.aClass140_584);
            }
            if (Class75.aClass75_575 == Class45.aClass75_381) {
                Class140.aBooleanArray3246 = new boolean[Class345.aClass132_2889.anInt1047];
                Class151_Sub1.aStringArray4967 = new String[Class111_Sub1.aClass239_4685.anInt1838];
                Class76_Sub5.anIntArray3744 = new int[Class345.aClass132_2889.anInt1047];
                for (int n8 = 0; Class345.aClass132_2889.anInt1047 > n8; ++n8) {
                    if (Class345.aClass132_2889.method2237(n8, 58).anInt718 == 0) {
                        Class140.aBooleanArray3246[n8] = true;
                        ++Class64_Sub21.anInt3700;
                    }
                    Class76_Sub5.anIntArray3744[n8] = -1;
                }
                Class78.method791((byte)102);
                Class297.anInt2470 = Class64_Sub20.aClass207_3697.method2750((byte)(-106), "loginscreen");
                Class19.anInt3450 = Class64_Sub20.aClass207_3697.method2750((byte)(-121), "lobbyscreen");
                Class234.aClass207_1748.method2747(false, true, (byte)95);
                Class98_Sub10_Sub1.aClass207_5544.method2747(true, true, (byte)(-120));
                Class332_Sub2.aClass207_5423.method2747(true, true, (byte)66);
                Class36.aClass207_348.method2747(true, true, (byte)(-78));
                Class98_Sub40.aClass207_4198.method2747(true, true, (byte)(-70));
                Class64_Sub20.aClass207_3697.method2747(true, true, (byte)125);
                Class335.aBoolean2817 = true;
                client.aClass207_3549.anInt1575 = 2;
                Class98_Sub10_Sub24.aClass207_5668.anInt1575 = 2;
                Class375.aClass207_3167.anInt1575 = 2;
                Class234.aClass207_1751.anInt1575 = 2;
                Class208.aClass207_1581.anInt1575 = 2;
                Class246_Sub3.aClass207_5087.anInt1575 = 2;
                PlayerUpdateMask.aClass207_525.anInt1575 = 2;
            }
            if (Class75.aClass75_576 == Class45.aClass75_381) {
                if (!Class85.method837(Class297.anInt2470, 102)) {
                    return 0;
                }
                boolean b = true;
                for (int n9 = 0; ~Class159.aClass293ArrayArray1252[Class297.anInt2470].length < ~n9; ++n9) {
                    final Class293 class363 = Class159.aClass293ArrayArray1252[Class297.anInt2470][n9];
                    if (~class363.anInt2354 == 0xFFFFFFFA && ~class363.anInt2237 != 0x0 && !Class332_Sub2.aClass207_5423.method2751(0, class363.anInt2237, -6329)) {
                        b = false;
                    }
                }
                if (!b) {
                    return 0;
                }
            }
            if (Class45.aClass75_381 == Class75.aClass75_577) {
                Class269.method3269(true, 0);
            }
            if (Class75.aClass75_578 == Class45.aClass75_381) {
                Class140.aClass47_3241.method448(false);
                try {
                    Class76_Sub9.aThread3783.join();
                }
                catch (InterruptedException ex2) {
                    return 0;
                }
                Class39_Sub1.anInterface10Array3592 = null;
                Class76_Sub9.aThread3783 = null;
                Class1.aClass207_67 = null;
                Class140.aClass47_3241 = null;
                Class224_Sub1.aClass207_5035 = null;
                Class3.aClass282_76 = null;
                Class338.method3780((byte)55);
                Class98_Sub46_Sub18.aBoolean6056 = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub2_4061.method560((byte)127) == 1);
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub2_4061);
                if (Class98_Sub46_Sub18.aBoolean6056) {
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062);
                }
                else if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062.aBoolean3658 && ~Exception_Sub1.aClass98_Sub35_47.anInt4129 > -513 && Exception_Sub1.aClass98_Sub35_47.anInt4129 != 0) {
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062);
                }
                Class310.method3618(-5964);
                if (Class98_Sub46_Sub18.aBoolean6056) {
                    Class76_Sub4.method754(0, false, 1);
                }
                else {
                    Class76_Sub4.method754(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062.method583((byte)123), false, 101);
                }
                Class98_Sub16.method1150(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4052.method666((byte)122), -1, 3, -1, false);
                Class98_Sub10_Sub19.method1058((byte)(-112), Class265.aHa1974);
                Class93_Sub2.method910(Class265.aHa1974, (byte)(-88));
                Class141.method2303(Class265.aHa1974, (byte)(-75), Class332_Sub2.aClass207_5423);
                Class303.method3556(Class64_Sub18.aClass332Array3689, 0);
            }
            return Class361.method3914(-5133);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kw.B(" + n + ')');
        }
    }
    
    final int method1625(final byte b) {
        try {
            if (b != -108) {
                this.anInt6053 = 116;
            }
            return (int)(super.aLong832 >>> -1338273504 & 0xFFL);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kw.D(" + b + ')');
        }
    }
    
    Class98_Sub46_Sub17(final int n, final int n2) {
        try {
            super.aLong832 = (n2 | n << -706964768);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kw.<init>(" + n + ',' + n2 + ')');
        }
    }
    
    final void method1626(final byte b) {
        try {
            super.aLong4259 |= Long.MIN_VALUE;
            if (b == -103) {
                if (this.method1620((byte)(-104)) == 0L) {
                    Class367.aClass215_3545.method2785(this, -71);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kw.F(" + b + ')');
        }
    }
}
