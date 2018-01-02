import java.awt.Component;
import java.awt.Frame;
import java.awt.Canvas;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Vector;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.net.Socket;
import java.awt.Rectangle;
import java.awt.Insets;
import javax.accessibility.Accessible;

// 
// Decompiled by Procyon v0.5.30
// 

public final class client extends Applet_Sub1
{
    static volatile long aLong3547;
    static int anInt3548;
    static Class207 aClass207_3549;
    private static int anInt3550;
    static byte[][] aByteArrayArray3551;
    static Class aClass3552;
    public static boolean aBoolean3553;
    
    private final void method99(final byte b) {
        try {
            if (Class177.anInt1376 != 14) {
                final long n = Class93_Sub3.method913(58) / 1000000L - Class98_Sub46_Sub9.aLong5997;
                Class98_Sub46_Sub9.aLong5997 = Class93_Sub3.method913(b + 67) / 1000000L;
                if (Class98_Sub21.method1178(b + 13308) && Class151_Sub5.aBoolean4991 && Class145.aClass268_1173 != null) {
                    Class145.aClass268_1173.method3249((byte)(-99));
                }
                if (Class98_Sub5_Sub1.method965((byte)101, Class177.anInt1376)) {
                    if (~Class98_Sub10_Sub25.aLong5677 != -1L && Class343.method3819(-47) > Class98_Sub10_Sub25.aLong5677) {
                        Class98_Sub16.method1150(Class146_Sub2.method2391((byte)12), Class98_Sub46_Sub15.anInt6039, 3, Class128.anInt1025, false);
                    }
                    else if (!Class265.aHa1974.method1819() && Class224_Sub2_Sub1.aBoolean6142) {
                        Class277.method3292((byte)(-51));
                    }
                }
                if (Class98_Sub18.aFrame3950 == null) {
                    Accessible accessible;
                    if (Class284.aFrame2168 == null) {
                        if (Class76_Sub11.anApplet3799 == null) {
                            accessible = Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271;
                        }
                        else {
                            accessible = Class76_Sub11.anApplet3799;
                        }
                    }
                    else {
                        accessible = Class284.aFrame2168;
                    }
                    int width = ((Component)accessible).getSize().width;
                    int height = ((Component)accessible).getSize().height;
                    if (Class284.aFrame2168 == accessible) {
                        final Insets insets = Class284.aFrame2168.getInsets();
                        width -= insets.left - -insets.right;
                        height -= insets.top + insets.bottom;
                    }
                    if (~Class188.anInt1453 != ~width || ~Class123_Sub1.anInt4744 != ~height || Class33.aBoolean316) {
                        if (Class265.aHa1974 != null && !Class265.aHa1974.method1800()) {
                            Class123_Sub1.anInt4744 = height;
                            Class188.anInt1453 = width;
                        }
                        else {
                            Class98_Sub31_Sub2.method1336((byte)(-98));
                        }
                        Class98_Sub10_Sub25.aLong5677 = Class343.method3819(-47) + 500L;
                        Class33.aBoolean316 = false;
                    }
                }
                if (Class98_Sub18.aFrame3950 != null && !Class4.aBoolean84 && Class98_Sub5_Sub1.method965((byte)(-51), Class177.anInt1376)) {
                    Class98_Sub16.method1150(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4052.method666((byte)126), -1, b + 12, -1, false);
                }
                boolean b2 = false;
                if (Class246_Sub10.aBoolean5152) {
                    Class246_Sub10.aBoolean5152 = false;
                    b2 = true;
                }
                if (b2) {
                    Class263.method3216(b ^ 0xFFFFC91C);
                }
                if ((Class265.aHa1974 != null && Class265.aHa1974.method1819()) || Class146_Sub2.method2391((byte)110) != 1) {
                    Class98_Sub43.method1481(2);
                }
                if (Class48_Sub1_Sub1.method462(Class177.anInt1376, 126)) {
                    Class34.method330((byte)50, b2);
                }
                else if (!Class191.method2651(Class177.anInt1376, (byte)(-7))) {
                    if (Class98_Sub5_Sub3.method974(Class177.anInt1376, (byte)(-116))) {
                        Class43.method398(105);
                    }
                    else if (Class199.method2690(Class177.anInt1376, ~b)) {
                        if (Class130.anInt1031 == 1) {
                            if (Class98_Sub5_Sub3.anInt5538 < Class142.anInt1160) {
                                Class98_Sub5_Sub3.anInt5538 = Class142.anInt1160;
                            }
                            Class246_Sub2.method2972(-75, Class98_Sub46_Sub10.aClass197_6019, Class195.aClass43_1499, true, Class265.aHa1974, Class309.aClass309_2598.method3615(Class374.anInt3159, (byte)25) + "<br>(" + 50 * (-Class142.anInt1160 + Class98_Sub5_Sub3.anInt5538) / Class98_Sub5_Sub3.anInt5538 + "%)");
                        }
                        else if (~Class130.anInt1031 != 0xFFFFFFFD) {
                            Class246_Sub2.method2972(-115, Class98_Sub46_Sub10.aClass197_6019, Class195.aClass43_1499, true, Class265.aHa1974, Class309.aClass309_2598.method3615(Class374.anInt3159, (byte)25));
                        }
                        else {
                            if (Class132.anInt1043 > Class280.anInt2105) {
                                Class280.anInt2105 = Class132.anInt1043;
                            }
                            Class246_Sub2.method2972(-51, Class98_Sub46_Sub10.aClass197_6019, Class195.aClass43_1499, true, Class265.aHa1974, Class309.aClass309_2598.method3615(Class374.anInt3159, (byte)25) + "<br>(" + (50 + (Class280.anInt2105 + -Class132.anInt1043) * 50 / Class280.anInt2105) + "%)");
                        }
                    }
                    else if (~Class177.anInt1376 != 0xFFFFFFF5) {
                        if (Class177.anInt1376 == 13) {
                            Class246_Sub2.method2972(b ^ 0x48, Class98_Sub46_Sub10.aClass197_6019, Class195.aClass43_1499, false, Class265.aHa1974, Class309.aClass309_2600.method3615(Class374.anInt3159, (byte)25) + "<br>" + Class309.aClass309_2601.method3615(Class374.anInt3159, (byte)25));
                        }
                    }
                    else {
                        Class190.method2648(n, -54);
                    }
                }
                else {
                    Class43.method398(126);
                }
                if (Class167.anInt1282 == 3) {
                    for (int n2 = 0; Class69_Sub2.anInt5335 > n2; ++n2) {
                        final Rectangle rectangle = Class98_Sub35.aRectangleArray4144[n2];
                        if (!Class232.aBooleanArray1741[n2]) {
                            if (Class98_Sub10_Sub20.aBooleanArray5639[n2]) {
                                Class265.aHa1974.method1781(true, rectangle.height, rectangle.width, -65536, rectangle.x, rectangle.y);
                            }
                            else {
                                Class265.aHa1974.method1781(true, rectangle.height, rectangle.width, -16711936, rectangle.x, rectangle.y);
                            }
                        }
                        else {
                            Class265.aHa1974.method1781(true, rectangle.height, rectangle.width, -65281, rectangle.x, rectangle.y);
                        }
                    }
                }
                if (Class217.method2800(-8001)) {
                    Class98_Sub10_Sub3.method1012(Class265.aHa1974, (byte)126);
                }
                if (Class98_Sub43_Sub2.aClass88_5907.aBoolean675 && Class98_Sub5_Sub1.method965((byte)105, Class177.anInt1376) && ~Class167.anInt1282 == -1 && ~Class146_Sub2.method2391((byte)104) == 0xFFFFFFFE && !b2) {
                    int n3 = 0;
                    for (int n4 = 0; ~n4 > ~Class69_Sub2.anInt5335; ++n4) {
                        if (Class98_Sub10_Sub20.aBooleanArray5639[n4]) {
                            Class98_Sub10_Sub20.aBooleanArray5639[n4] = false;
                            Class280.aRectangleArray2106[n3++] = Class98_Sub35.aRectangleArray4144[n4];
                        }
                    }
                    try {
                        if (!za_Sub2.aBoolean6079) {
                            Class265.aHa1974.method1794(Class280.aRectangleArray2106, n3, b + 27188);
                        }
                        else {
                            Class211.method2775(Class280.aRectangleArray2106, n3, b ^ 0xFFFFFEF3);
                        }
                    }
                    catch (Exception_Sub1 exception_Sub2) {}
                }
                else if (!Class48_Sub1_Sub1.method462(Class177.anInt1376, b + 136)) {
                    for (int n5 = 0; ~Class69_Sub2.anInt5335 < ~n5; ++n5) {
                        Class98_Sub10_Sub20.aBooleanArray5639[n5] = false;
                    }
                    try {
                        if (!za_Sub2.aBoolean6079) {
                            Class265.aHa1974.method1754(109);
                        }
                        else {
                            Class6.method181((byte)10);
                        }
                    }
                    catch (Exception_Sub1 exception_Sub1) {
                        Class305_Sub1.method3585(exception_Sub1, -126, exception_Sub1.getMessage() + " (Recovered) " + this.method94(0));
                        Class76_Sub4.method754(0, false, b + 64);
                    }
                }
                if (b == -9) {
                    Class289.method3408((byte)101);
                    final int method639 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub21_4037.method639((byte)121);
                    if (~method639 != -1) {
                        if (~method639 == 0xFFFFFFFE) {
                            Class246_Sub7.method3131(0, 10L);
                        }
                        else if (method639 != 2) {
                            if (~method639 == 0xFFFFFFFC) {
                                Class246_Sub7.method3131(b ^ 0xFFFFFFF7, 2L);
                            }
                        }
                        else {
                            Class246_Sub7.method3131(0, 5L);
                        }
                    }
                    else {
                        Class246_Sub7.method3131(0, 15L);
                    }
                    if (Class335.aBoolean2817) {
                        Class98_Sub10_Sub1.method1002(false);
                    }
                    if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub2_4061.method560((byte)123) == 0xFFFFFFFE && ~Class177.anInt1376 == 0xFFFFFFFC && ~Class15.anInt185 != 0x0) {
                        Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub2_4061);
                        Class310.method3618(-5964);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.CA(" + b + ')');
        }
    }
    
    public static void method100(final int n) {
        try {
            client.aByteArrayArray3551 = null;
            if (n >= -80) {
                client.anInt3550 = 10;
            }
            client.aClass207_3549 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.KA(" + n + ')');
        }
    }
    
    @Override
    final void method90(final int n) {
        try {
            method100(-108);
            Class347.method3832((byte)102);
            Class116.method2160(-25882);
            Class98_Sub46_Sub17.method1622((byte)20);
            Class309.method3616(38);
            Class77.method777((byte)72);
            Class299.method3511(-1);
            Class95.method921(false);
            Class102.method1708((byte)70);
            Class86.method842(false);
            Class121.method2196((byte)(-10));
            Class289.method3409((byte)(-117));
            Class305.method3573((byte)(-20));
            Class98_Sub46.method1523(0);
            Class98.method940(false);
            Class377.method4000((byte)(-97));
            Class6.method179(2566);
            Class196.method2667((byte)(-112));
            Class279.method3321(true);
            ha.method1787(false);
            Class98_Sub27.method1280(true);
            Class98_Sub35.method1456(false);
            Class36.method339(-13);
            Class140.method2285(n ^ 0xFFFFEE74);
            Class148.method2415(false);
            Class135.method2250(n ^ 0x169E);
            Class253.method3178((byte)118);
            Class339_Sub1.method3794((byte)(-101));
            Class207.method2757(1);
            Class225.method2851(true);
            Class17.method244(-24652);
            Class79.method798((byte)22);
            Class257.method3198(51);
            Class11.method199((byte)2);
            Class29.method301(-23881);
            Class32.method314(0);
            Class153.method2488(-1);
            Class83.method830((byte)64);
            Class8.method184((byte)(-109));
            Class269.method3267((byte)118);
            Class302.method3551(-98);
            Class341.method3811(-8433);
            Class335.method3769((byte)121);
            Class301.method3536((byte)(-82));
            Class365.method3942(n + 36);
            Class13.method219(true);
            Class59.method531((byte)(-124));
            Class115.method2154((byte)(-67));
            Class304.method3561(-19357);
            Class264.method3225(true);
            Class132.method2234(0);
            Class198.method2682(false);
            Class212.method2776((byte)(-102));
            Class280.method3329(-121);
            Class98_Sub39.method1467(true);
            Class123.method2210(n + 145149062);
            Class98_Sub22.method1243(81);
            Class117.method2163(-20732);
            IncomingOpcode.method522(77);
            Class265.method3231(-94);
            Class98_Sub46_Sub10.method1567((byte)83);
            Class370.method3956(-121);
            Class346.method3830(-211);
            Class218.method2804((byte)(-81));
            Class259.method3205(true);
            Class246_Sub3_Sub4_Sub2_Sub2.method3064(-2485);
            Class147.method2414(n ^ 0xFFFFFF9A);
            Class101.method1696(true);
            Class98_Sub11.method1123((byte)(-90));
            Class172.method2543(4390);
            Class98_Sub26.method1278(107);
            Class246_Sub3_Sub4_Sub2_Sub1.method3053(false);
            Class98_Sub18.method1161(0);
            Class98_Sub49.method1665((byte)116);
            Class98_Sub46_Sub2.method1535(26767);
            Class246.method2963(n - 1);
            Class57.method518(30);
            Class343.method3818(-59);
            Class2.method170((byte)53);
            Class53_Sub1.method500(-100);
            Class354.method3871(43594);
            RuntimeException_Sub1.method4013(16711935);
            Class188.method2638(23950);
            Class98_Sub46_Sub13_Sub2.method1599(109);
            Class98_Sub46_Sub13_Sub1.method1596((byte)80);
            Class129.method2226((byte)122);
            Class248.method3157(-2229);
            Class98_Sub46_Sub16.method1616(n ^ 0xFFFFFFD8);
            Class97.method934((byte)(-26));
            Class226.method2853(1);
            Class146.method2328(n);
            Class270.method3275(false);
            Class28.method298(-15136);
            Class138.method2279(100);
            Class310.method3617((byte)(-59));
            Class352.method3862(-40);
            Class298.method3502(38);
            s.method3423(true);
            Class185.method2632((byte)(-119));
            Class40.method363(n - 72);
            Class43.method414(-2);
            Class356.method3881(n ^ 0xFFFFC793);
            Class145.method2314(-1);
            Class64_Sub23.method649(-111);
            Class64_Sub5.method569(true);
            Class64_Sub17.method616(-110);
            Class64_Sub13.method605(-2);
            Class64_Sub14.method608(-96);
            Class64_Sub11.method598(0);
            Class64_Sub24.method650(-36);
            Class64_Sub10.method593(34);
            Class64_Sub7.method577(n ^ 0x10);
            Class64_Sub18.method629(n ^ 0xFFFF81DA);
            Class64_Sub15.method611((byte)(-51));
            Class64_Sub16.method615(n ^ 0x3);
            Class64_Sub26.method659(n + 3);
            Class64_Sub9.method589(8);
            Class64_Sub20.method637(0);
            Class64_Sub8.method584(n);
            Class64_Sub3.method566(n ^ 0xFFFFF1D9);
            Class64_Sub28.method672(n + 8192);
            Class64_Sub27.method665(2);
            Class64_Sub29.method676((byte)(-58));
            Class64_Sub12.method599((byte)(-13));
            Class64_Sub21.method638(4);
            Class64_Sub4.method567((byte)81);
            Class64_Sub2.method563((byte)122);
            Class64_Sub22.method642(97);
            Class64_Sub1.method557(n + 109);
            Class166.method2526(-19351);
            Class62.method543(0);
            Exception_Sub1.method133((byte)(-127));
            aa.method151(true);
            za.method1676(-112);
            Class242.method2933(-115);
            Class98_Sub5.method957(n ^ 0xFFFFF24B);
            Class221.method2822(-128);
            Class246_Sub3.method2983((byte)(-67));
            Class98_Sub31_Sub2.method1333(0);
            Class314.method3640(n);
            Class338.method3779(115);
            Class247.method3154();
            Class105.method1714(-9);
            Class98_Sub43_Sub4.method1505(n + 21237);
            Class237.method2906(n + 10);
            Class98_Sub43_Sub3.method1497((byte)95);
            Class98_Sub43_Sub1.method1491(-3);
            Class98_Sub31_Sub4.method1389(-18925);
            Class179.method2602((byte)(-119));
            Class290.method3412(~n);
            Class75.method734((byte)97);
            Class161.method2515(1);
            Class194.method2658(255);
            Class260.method3209(-19788);
            Class213.method2783((byte)(-124));
            Class160.method2512((byte)(-119));
            Class255.method3191((byte)49);
            Class282.method3338(0);
            Class90.method881((byte)(-27));
            Class278.method3313();
            Class47.method449(n ^ 0x50);
            Class41.method365(0);
            Class375.method3987((byte)(-73));
            Class272.method3279(false);
            Class308.method3610(-746085692);
            Class10.method198((byte)(-47));
            Class295.method3483(0);
            Class234.method2885((byte)(-116));
            Class63.method548((byte)(-60));
            Class110.method2086((byte)54);
            Class351.method3848((byte)(-47));
            Class369.method3955(110);
            Class84.method835(2169);
            Class246_Sub1.method2968((byte)(-101));
            Class325.method3700(2);
            Class246_Sub5.method3125();
            Class294.method3482(-10494);
            Class342.method3816(30935);
            Class312.method3621((byte)7);
            Class263.method3221(-124);
            Class339.method3787((byte)(-69));
            Class278_Sub1.method3319(1204);
            Class61.method542(false);
            Class157.method2507(57);
            Class98_Sub47.method1659(58);
            Class326.method3703();
            Class222.method2827((byte)(-101));
            Class191.method2649(n ^ 0x79);
            Class300.method3532((byte)(-63));
            Class98_Sub21.method1177(24301);
            Class98_Sub46_Sub4.method1542(n ^ 0x4C);
            Class159.method2510(4);
            Class142.method2306(-1);
            Class1.method163(68);
            Class245.method2955((byte)67);
            Class246_Sub3_Sub1.method2993(n + 288450632);
            Class246_Sub3_Sub2.method3004(-123);
            Class246_Sub3_Sub3.method3013((byte)(-93));
            Class246_Sub3_Sub5.method3089(4);
            Class154.method2492(-1);
            Class323.method3679(-1443);
            Class175.method2571();
            Class87.method853(n - 75);
            Class35.method334(n ^ 0xFFFFFF84);
            Class98_Sub1.method947(-20899);
            Class7.method183();
            Class231.method2877(35);
            Class74.method727((byte)5);
            Class107.method1726(false);
            Class320.method3664(n ^ 0x1765);
            Class98_Sub42.method1480((byte)112);
            Class98_Sub13.method1136();
            Class176.method2581(84);
            Class376.method3989((byte)(-40));
            Class98_Sub50.method1673(1);
            Class199.method2687(false);
            Class152.method2481(false);
            Class366.method3947(n ^ 0xFFFFC312);
            Class167.method2528((byte)85);
            Class149.method2432(0);
            Class220.method2817(-49);
            Class60.method534((byte)(-63));
            Class266.method3233(86);
            Class379.method4007(true);
            Class98_Sub46_Sub12.method1587((byte)(-99));
            Class92.method896(-1025810040);
            Class50.method483(-11543);
            Class100.method1691(n ^ 0xFFFFFFF7);
            Class23.method281((byte)107);
            Class131.method2233(-11535);
            Class25.method292((byte)41);
            Class137.method2275(121);
            Class211.method2774(79);
            Class150.method2436(true);
            Class254.method3188(-386);
            Class246_Sub2.method2970((byte)(-101));
            Class246_Sub3_Sub4_Sub4.method3077((byte)(-29));
            Class246_Sub3_Sub4_Sub3.method3072(n);
            Class246_Sub7.method3133((byte)64);
            Class318.method3655(true);
            Class98_Sub45.method1518(n + 5);
            Class3.method171((byte)126);
            Class98_Sub46_Sub11.method1577((byte)99);
            Class348.method3837(2);
            Class275.method3282((byte)(-53));
            Class246_Sub3_Sub2_Sub1.method3010((byte)(-37));
            Class246_Sub3_Sub3_Sub1.method3017((byte)97);
            Class359.method3896(11);
            Class246_Sub3_Sub5_Sub1.method3090((byte)(-94));
            Class246_Sub3_Sub4_Sub5.method3087(-1001);
            Class246_Sub3_Sub1_Sub2.method3001(-22408);
            Class78.method792(-17344);
            Class77_Sub1.method785(3);
            Class299_Sub2.method3522(false);
            Class113.method2146((byte)122);
            Class124.method2214(n);
            Class210.method2773(n + 3);
            Class163.method2521((byte)(-106));
            Class367.method3948(true);
            Class93_Sub1.method902(2);
            Class52.method492(true);
            Class337_Sub1.method3776((byte)52);
            Class93_Sub3.method912(58);
            Class337.method3775(n + 10003);
            Class315.method3645(false);
            Class238.method2918(-92);
            Class98_Sub46_Sub19.method1632((byte)37);
            Class306.method3597(92);
            Class177.method2585((byte)70);
            Class98_Sub46_Sub1.method1530(true);
            Class357.method3883(-71);
            Class130.method2231(0);
            Class31.method307(true);
            IOException_Sub1.method126(65535);
            PlayerUpdateMask.method711(-23308);
            Class27.method295((byte)(-124));
            Class374.method3985(0);
            Class330.method3714();
            Class331.method3724(-88);
            Class98_Sub46_Sub2_Sub2.method1539(22412);
            Class98_Sub46_Sub20.method1636(true);
            Class246_Sub3_Sub1_Sub1.method2999(n + 25581);
            Class246_Sub3_Sub4_Sub1.method3029(-1);
            Class45.method432((byte)102);
            Class246_Sub3_Sub5_Sub2.method3097(0);
            Class98_Sub6.method975(1);
            Class98_Sub34.method1451(n - 112);
            Class98_Sub15.method1145(97);
            Class53.method494((byte)116);
            Class114.method2150(0);
            Class271.method3276((byte)87);
            Class358.method3888((byte)(-3));
            Class98_Sub16.method1146(false);
            Class173.method2554();
            Class98_Sub31_Sub1.method1329((byte)(-84));
            Class98_Sub44.method1516(n);
            Class344.method3823();
            Class56.method515();
            Class89.method879(true);
            Class268_Sub1.method3263();
            Class208.method2768(104);
            Class98_Sub43_Sub2.method1494(4);
            Class340.method3804((byte)(-71));
            Class54.method505((byte)123);
            PlayerUpdate.method2856(n);
            Class180.method2605((byte)(-127));
            Class217.method2801((byte)101);
            Class133.method2240((byte)16);
            Class334.method3763(true);
            Class134.method2241(-115);
            Class373_Sub2.method3975((byte)(-10));
            Class373.method3961(n ^ 0xFFFFF145);
            Class93.method899(n ^ 0x7A);
            Class373_Sub3.method3979(false);
            PacketParser.method3968(false);
            Class19.method251(51);
            Class5.method177(n + 7681);
            Class373_Sub1_Sub1.method3971(0);
            Class267.method3240(n ^ 0x1);
            Class368.method3949((byte)49);
            Class136.method2269();
            Class292.method3448(1);
            Class44.method429((byte)74);
            Class4.method175(10);
            Class98_Sub46_Sub6.method1549((byte)(-112));
            Class246_Sub4_Sub2.method3106((byte)116);
            Class246_Sub9.method3137(-6086);
            Class252.method3172(0);
            Class165.method2524((byte)(-108));
            Class284.method3355(15029);
            Class223.method2832((byte)71);
            Class16.method235((byte)102);
            Class98_Sub46_Sub20_Sub2.method1640((byte)45);
            Class277.method3291(114);
            Class262.method3214(n ^ 0xFFFFFF84);
            Class98_Sub10_Sub13.method1042(127);
            Class98_Sub41.method1474(false);
            Class98_Sub32.method1440(-104);
            Class186.method2633(-20830);
            Class235.method2890(64);
            ha_Sub1.method1862(n);
            Class364.method3936((byte)(-59));
            Class55.method506(false);
            Class283.method3352(13);
            Class98_Sub28_Sub1.method1308((byte)113);
            Class360.method3913((byte)112);
            Class111_Sub1.method2114((byte)66);
            Class332_Sub1.method3751(n ^ 0x1702);
            Class288.method3397(true);
            Class42_Sub1.method384(-40);
            Class48_Sub1.method459(false);
            Class42_Sub1_Sub1.method386(-16573);
            Class146_Sub2.method2378(0);
            Class104.method1713((byte)23);
            Class98_Sub22_Sub2.method1263(-125);
            Class42_Sub2.method390(false);
            ha_Sub3.method2024(true);
            Class169.method2536(n - 116);
            Class162.method2517(-120);
            Class195.method2660(n ^ 0x68DD);
            Class111_Sub3.method2127(-51);
            Class204.method2707((byte)15);
            Class258.method3204((byte)(-18));
            Class319.method3658((byte)114);
            Class76.method744(n + 87);
            Class81.method820(n + 1);
            Class146_Sub3.method2400(-1);
            Class65.method680((byte)(-108));
            Class38.method346(255);
            Class49.method482(13);
            Class232.method2881(false);
            Class69_Sub2.method708(7315);
            Class182.method2614();
            Class98_Sub10_Sub22.method1072((byte)(-121));
            Class98_Sub10_Sub3.method1011(true);
            Class98_Sub10_Sub38.method1118(true);
            Class98_Sub10_Sub24.method1078((byte)98);
            Class98_Sub10_Sub15.method1049((byte)21);
            Class98_Sub10_Sub7.method1024((byte)114);
            Class98_Sub10_Sub11.method1040(n - 80);
            Class98_Sub10_Sub4.method1013(119);
            Class98_Sub10_Sub30.method1094(615);
            Class98_Sub10_Sub8.method1028(false);
            Class98_Sub10_Sub17.method1053((byte)40);
            Class98_Sub10_Sub26.method1082(n + 125);
            Class98_Sub10_Sub6.method1020(n + 2048);
            Class98_Sub10_Sub39.method1119(0);
            Class98_Sub10_Sub27.method1085(n + 82);
            Class98_Sub10_Sub16.method1051(false);
            Class98_Sub10_Sub14.method1048((byte)(-100));
            Class98_Sub10_Sub31.method1095(10640);
            Class98_Sub10_Sub23.method1073(4096);
            Class98_Sub10_Sub28.method1088(n ^ 0xFFFFFFA5);
            Class98_Sub10_Sub36.method1112(0);
            Class98_Sub10_Sub10.method1039(75);
            Class98_Sub10_Sub34.method1105(83);
            Class98_Sub10_Sub37.method1113(n);
            Class98_Sub10_Sub20.method1062(0);
            Class98_Sub10_Sub1.method1006(-1);
            Class98_Sub10_Sub21.method1068((byte)(-48));
            Class94.method918(-19406);
            Class98_Sub2.method950();
            aa_Sub1.method154((byte)29);
            Class170.method2539((byte)13);
            Class329.method3712((byte)21);
            Class98_Sub32_Sub1.method1442(true);
            Class98_Sub5_Sub3.method973(1);
            Class187.method2635(109);
            Class33.method327((byte)(-128));
            Class156_Sub1.method2497((byte)(-81));
            s_Sub1.method3431(n ^ 0xFFFFFF80);
            Class336.method3774(-43327976);
            Class98_Sub46_Sub14.method1606((byte)113);
            Class151.method2438(-24883);
            Class151_Sub9.method2469(n ^ Integer.MAX_VALUE);
            Class249.method3163((byte)95);
            Class219.method2813(false);
            r_Sub1.method1644(9949);
            aa_Sub3.method158(6406);
            Class91.method889(true);
            Class98_Sub20.method1166(-22268);
            Class202.method2699((byte)44);
            Class48_Sub1_Sub1.method460(true);
            Class48_Sub1_Sub2.method467(1);
            Class287_Sub2.method3394(90);
            Class287.method3388(32414);
            Class287_Sub1.method3390(n ^ 0xFFFF903A);
            Class151_Sub6.method2465((byte)89);
            Class151_Sub3.method2456(73);
            Class151_Sub1.method2447(32132);
            Class42_Sub3.method392(n ^ 0xFFFFFFCA);
            Class151_Sub7.method2467(0);
            Class151_Sub5.method2461(-3);
            Class151_Sub8.method2468((byte)(-101));
            Class151_Sub4.method2460(23777);
            Class151_Sub2.method2452(928);
            Class345.method3826(-70);
            Class144.method2312(-6569);
            Class34.method329(n);
            Class200.method2695(-382);
            za_Sub1.method1678(-129);
            Class281.method3332(22011);
            Class181.method2606(-16841);
            r_Sub2.method1649(true);
            Class241.method2932(14441);
            Class39_Sub1.method354((byte)(-107));
            Class332_Sub2.method3755(-14320);
            aa_Sub2.method156(13);
            Class98_Sub30.method1318(n - 10);
            Class15.method233(8);
            Class48_Sub2_Sub1.method475(n ^ 0x46C2E462);
            Class98_Sub5_Sub1.method967((byte)77);
            Class76_Sub4.method753(116);
            Class76_Sub7.method761((byte)19);
            Class76_Sub5.method757((byte)4);
            Class76_Sub11.method772((byte)105);
            Class284_Sub2.method3376(-128);
            Class284_Sub1.method3364(n + 13432);
            Class39.method351(true);
            Class303.method3558(10);
            Class21_Sub1.method270(n);
            Class21.method257(0);
            Class21_Sub2.method272((byte)86);
            Class21_Sub4.method277((byte)0);
            Class76_Sub8.method764((byte)122);
            Class76_Sub2.method750(n);
            Class76_Sub9.method767(true);
            Class82.method821(n + 14);
            Class69.method699(-95);
            Class256_Sub1.method3194(0);
            Class224.method2833((byte)(-122));
            Class224_Sub1.method2835(-15870);
            Class224_Sub3_Sub1.method2841((byte)(-101));
            Class224_Sub3.method2840((byte)102);
            Class98_Sub40.method1472(-1);
            Class98_Sub37.method1463((byte)119);
            Class190.method2646(n + 27387);
            Class98_Sub25.method1271(573132577);
            Class230.method2872((byte)(-80));
            Class246_Sub4_Sub1.method3102((byte)(-62));
            Class98_Sub46_Sub5.method1545((byte)78);
            Class98_Sub46_Sub3.method1540(false);
            Class98_Sub36.method1457(-2496);
            Class244.method2954(126);
            InputStream_Sub1.method122(false);
            OutputStream_Sub1.method131((byte)4);
            Class73.method720(-63);
            Class250.method3165(76);
            Class98_Sub46_Sub15.method1612(true);
            Class98_Sub12.method1129(false);
            Class98_Sub23.method1266(-6292);
            Class98_Sub46_Sub9.method1560((byte)118);
            Class98_Sub46_Sub8.method1552(true);
            Class98_Sub8.method987(125);
            Class98_Sub17_Sub1.method1159(-15367);
            Class127.method2218(0);
            OutputStream_Sub2.method132(-1);
            Class216.method2798(0);
            Class98_Sub9.method988(n ^ 0x7166);
            Class98_Sub46_Sub7.method1551((byte)(-28));
            Class372.method3958(-12477);
            Class322.method3669(n ^ 0x3CA9);
            Class284_Sub2_Sub2.method3378(n + 8);
            Class284_Sub1_Sub2.method3369((byte)(-54));
            Class189.method2641((byte)120);
            Class18.method246((byte)(-102));
            Class378.method4002(115);
            Class251.method3169((byte)11);
            Class168.method2532((byte)(-6));
            Class119.method2175((byte)(-30));
            Class119_Sub1.method2181(true);
            Class119_Sub2.method2184((byte)72);
            Class119_Sub4.method2189((byte)(-1));
            Class284_Sub1_Sub1.method3365((byte)89);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.N(" + n + ')');
        }
        if (Applet_Sub1.anInt4 != 0) {
            client.aBoolean3553 = true;
        }
    }
    
    static final void method101() {
        for (int i = 0; i < Class165.anInt1276; ++i) {
            final int[] array = Class372.anIntArrayArray3149[i];
            for (int j = 0; j < Class98_Sub10_Sub7.anInt5572; ++j) {
                array[j] = 0;
            }
        }
    }
    
    static final Class293 method102(Class293 method2509) {
        final int method2510 = method116(method2509).method1663(1);
        if (method2510 == 0) {
            return null;
        }
        for (int i = 0; i < method2510; ++i) {
            method2509 = Class159.method2509(method2509.anInt2234, -9820);
            if (method2509 == null) {
                return null;
            }
        }
        return method2509;
    }
    
    static final void method103(final int n, final ha ha) {
        try {
            if (n != 0) {
                client.anInt3550 = -100;
            }
            if (Canvas_Sub1.anInt26 != Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 && Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 != null) {
                if (Class98_Sub10_Sub5.method1015(ha, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, 8939)) {
                    Canvas_Sub1.anInt26 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.AA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method93(final int n) {
        try {
            Label_0093: {
                if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)123) != 0xFFFFFFFD) {
                    this.method99((byte)(-9));
                    if (!client.aBoolean3553) {
                        break Label_0093;
                    }
                }
                try {
                    this.method99((byte)(-9));
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    Class305_Sub1.method3585(t, -124, t.getMessage() + " (Recovered) " + this.method94(0));
                    Class223.aBoolean1679 = true;
                    Class76_Sub4.method754(0, false, 70);
                }
            }
            if (n <= 91) {
                method110(-22);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.B(" + n + ')');
        }
    }
    
    static final void method104(final Class293[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11) {
        for (int i = 0; i < array.length; ++i) {
            final Class293 aClass293_3986 = array[i];
            if (aClass293_3986 != null && aClass293_3986.anInt2234 == n) {
                final int n12 = aClass293_3986.anInt2347 + n6;
                final int n13 = aClass293_3986.anInt2299 + n7;
                int n14;
                int n15;
                int n16;
                int n17;
                if (aClass293_3986.anInt2354 == 2) {
                    n14 = n2;
                    n15 = n3;
                    n16 = n4;
                    n17 = n5;
                }
                else {
                    int n18 = n12 + aClass293_3986.anInt2311;
                    int n19 = n13 + aClass293_3986.anInt2258;
                    if (aClass293_3986.anInt2354 == 9) {
                        ++n18;
                        ++n19;
                    }
                    n14 = ((n12 > n2) ? n12 : n2);
                    n15 = ((n13 > n3) ? n13 : n3);
                    n16 = ((n18 < n4) ? n18 : n4);
                    n17 = ((n19 < n5) ? n19 : n5);
                }
                if (aClass293_3986.anInt2354 != 0 && !aClass293_3986.aBoolean2209 && method116(aClass293_3986).anInt4284 == 0 && aClass293_3986 != Class189.aClass293_1457 && aClass293_3986.anInt2307 != Class325.anInt2727 && aClass293_3986.anInt2307 != Class373_Sub2.anInt5471) {
                    if (n14 < n16 && n15 < n17) {
                        Class98_Sub10_Sub21.method1066(108, aClass293_3986);
                    }
                }
                else if (!method111(aClass293_3986)) {
                    int method2642 = 0;
                    int method2643 = 0;
                    if (za_Sub2.aBoolean6079) {
                        method2642 = Class189.method2642((byte)42);
                        method2643 = Class335.method3765(false);
                    }
                    if (aClass293_3986 == Class255.aClass293_3208 && Class365.method3939(4456, Class255.aClass293_3208) != null) {
                        Class166.aBoolean1278 = true;
                        Class167.anInt1286 = n12;
                        Class259.anInt1960 = n13;
                    }
                    if (aClass293_3986.aBoolean2222 || (n14 < n16 && n15 < n17)) {
                        if (aClass293_3986.aBoolean2286 && n10 >= n14 && n11 >= n15 && n10 < n16 && n11 < n17) {
                            for (Class98_Sub21 class98_Sub21 = (Class98_Sub21)Class151_Sub3.aClass148_4977.method2418(32); class98_Sub21 != null; class98_Sub21 = (Class98_Sub21)Class151_Sub3.aClass148_4977.method2417(103)) {
                                if (class98_Sub21.aBoolean3980) {
                                    class98_Sub21.method942(118);
                                    class98_Sub21.aClass293_3986.aBoolean2322 = false;
                                }
                            }
                            if (Class105.anInt3417 == 0) {
                                Class255.aClass293_3208 = null;
                                Class189.aClass293_1457 = null;
                            }
                            Class156_Sub2.anInt3423 = 0;
                            Class253.aBoolean1930 = false;
                            Class102.aBoolean889 = false;
                            if (!Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540) {
                                Class46.method435((byte)103);
                            }
                        }
                        int n20;
                        if (Class2.aClass299_73.method3514(124) + method2642 >= n14 && Class2.aClass299_73.method3507((byte)60) + method2643 >= n15 && Class2.aClass299_73.method3514(70) + method2642 < n16 && Class2.aClass299_73.method3507((byte)49) + method2643 < n17) {
                            n20 = 1;
                        }
                        else {
                            n20 = 0;
                        }
                        if (!Class98_Sub10_Sub9.aBoolean5585 && n20 != 0) {
                            if (aClass293_3986.anInt2317 >= 0) {
                                Class21_Sub2.anInt5387 = aClass293_3986.anInt2317;
                            }
                            else if (aClass293_3986.aBoolean2286) {
                                Class21_Sub2.anInt5387 = -1;
                            }
                        }
                        if (!Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 && n10 >= n14 && n11 >= n15 && n10 < n16 && n11 < n17) {
                            Class8.method186(59, aClass293_3986, n11 - n13, n10 - n12);
                        }
                        boolean b = false;
                        if (Class2.aClass299_73.method3506((byte)121) && n20 != 0) {
                            b = true;
                        }
                        int n21 = 0;
                        final Class98_Sub17 class98_Sub22 = (Class98_Sub17)Class167.aClass148_1284.method2418(32);
                        if (class98_Sub22 != null && class98_Sub22.method1156(-5) == 0 && class98_Sub22.method1155(124) + method2642 >= n14 && class98_Sub22.method1151(123) + method2643 >= n15 && class98_Sub22.method1155(125) + method2642 < n16 && class98_Sub22.method1151(80) + method2643 < n17) {
                            n21 = 1;
                        }
                        if (aClass293_3986.aByteArray2345 != null && !Class217.method2800(-8001)) {
                            for (int j = 0; j < aClass293_3986.aByteArray2345.length; ++j) {
                                if (!Class219.aClass77_1641.method779(aClass293_3986.aByteArray2345[j], 5503)) {
                                    if (aClass293_3986.anIntArray2221 != null) {
                                        aClass293_3986.anIntArray2221[j] = 0;
                                    }
                                }
                                else if (aClass293_3986.anIntArray2221 == null || Class215.anInt1614 >= aClass293_3986.anIntArray2221[j]) {
                                    final byte b2 = aClass293_3986.aByteArray2331[j];
                                    if (b2 == 0 || (((b2 & 0x8) == 0x0 || (!Class219.aClass77_1641.method779(86, 5503) && !Class219.aClass77_1641.method779(82, 5503) && !Class219.aClass77_1641.method779(81, 5503))) && ((b2 & 0x2) == 0x0 || Class219.aClass77_1641.method779(86, 5503)) && ((b2 & 0x1) == 0x0 || Class219.aClass77_1641.method779(82, 5503)) && ((b2 & 0x4) == 0x0 || Class219.aClass77_1641.method779(81, 5503)))) {
                                        if (j < 10) {
                                            Class303.method3557(j + 1, -1, "", -120, aClass293_3986.anInt2248);
                                        }
                                        else if (j == 10) {
                                            Class98_Sub10_Sub32.method1098((byte)(-118));
                                            final Class98_Sub49 method2644 = method116(aClass293_3986);
                                            Class98_Sub5_Sub2.method970(method2644.anInt4285, aClass293_3986, method2644.method1668(-1), -6838);
                                            Class287_Sub2.aString3272 = Class170.method2538(-1, aClass293_3986);
                                            if (Class287_Sub2.aString3272 == null) {
                                                Class287_Sub2.aString3272 = "Null";
                                            }
                                            Class246_Sub3_Sub3.aString6156 = aClass293_3986.aString2224 + "<col=ffffff>";
                                        }
                                        final int n22 = aClass293_3986.anIntArray2275[j];
                                        if (aClass293_3986.anIntArray2221 == null) {
                                            aClass293_3986.anIntArray2221 = new int[aClass293_3986.aByteArray2345.length];
                                        }
                                        if (n22 != 0) {
                                            aClass293_3986.anIntArray2221[j] = Class215.anInt1614 + n22;
                                        }
                                        else {
                                            aClass293_3986.anIntArray2221[j] = Integer.MAX_VALUE;
                                        }
                                    }
                                }
                            }
                        }
                        if (n21 != 0) {
                            Class146_Sub3.method2405(aClass293_3986, (byte)52, method2642 + class98_Sub22.method1155(-89) - n12, method2643 + class98_Sub22.method1151(47) - n13);
                        }
                        if (Class255.aClass293_3208 != null && Class255.aClass293_3208 != aClass293_3986 && n20 != 0 && method116(aClass293_3986).method1664(-1)) {
                            Class162.aClass293_1272 = aClass293_3986;
                        }
                        if (aClass293_3986 == Class189.aClass293_1457) {
                            Class239.aBoolean1840 = true;
                            Class246_Sub3_Sub4_Sub2_Sub1.anInt6500 = n12;
                            Class222.anInt1670 = n13;
                        }
                        if (aClass293_3986.aBoolean2209 || aClass293_3986.anInt2307 != 0) {
                            if (n20 != 0 && Class319.anInt2699 != 0 && aClass293_3986.anObjectArray2277 != null) {
                                final Class98_Sub21 class98_Sub23 = new Class98_Sub21();
                                class98_Sub23.aBoolean3980 = true;
                                class98_Sub23.aClass293_3986 = aClass293_3986;
                                class98_Sub23.anInt3979 = Class319.anInt2699;
                                class98_Sub23.anObjectArray3981 = aClass293_3986.anObjectArray2277;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub23, -20911);
                            }
                            if (Class255.aClass293_3208 != null || Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 || (aClass293_3986.anInt2307 != Class87.anInt673 && Class156_Sub2.anInt3423 > 0)) {
                                n21 = 0;
                                b = false;
                                n20 = 0;
                            }
                            if (aClass293_3986.anInt2307 != 0) {
                                if (aClass293_3986.anInt2307 == Class22.anInt218 || aClass293_3986.anInt2307 == Class64_Sub20.anInt3698) {
                                    Class98_Sub32.aClass293_4107 = aClass293_3986;
                                    if (s_Sub1.aClass346_5202 != null) {
                                        s_Sub1.aClass346_5202.method3828(aClass293_3986.anInt2258, 6, Class265.aHa1974);
                                    }
                                    if (aClass293_3986.anInt2307 == Class22.anInt218) {
                                        if (!Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 && n10 >= n14 && n11 >= n15 && n10 < n16 && n11 < n17) {
                                            Class39.method350(0, n9, n8, Class265.aHa1974);
                                            for (Class246_Sub2 class246_Sub2 = (Class246_Sub2)Class151_Sub2.aClass218_4973.method2803((byte)15); class246_Sub2 != null; class246_Sub2 = (Class246_Sub2)Class151_Sub2.aClass218_4973.method2809(false)) {
                                                if (n10 >= class246_Sub2.anInt5074 && n10 < class246_Sub2.anInt5073 && n11 >= class246_Sub2.anInt5071 && n11 < class246_Sub2.anInt5075) {
                                                    Class46.method435((byte)101);
                                                    Class246_Sub3_Sub3.method3012(class246_Sub2.aClass246_Sub3_Sub4_Sub2_5076, (byte)(-124));
                                                }
                                            }
                                        }
                                        continue;
                                    }
                                }
                                if (aClass293_3986.anInt2307 == Class325.anInt2727) {
                                    if (aClass293_3986.method3469(Class265.aHa1974, 115) != null && (Class333.anInt3386 == 0 || Class333.anInt3386 == 3) && !Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 && n10 >= n14 && n11 >= n15 && n10 < n16 && n11 < n17) {
                                        final int n23 = n10 - n12;
                                        final int n24 = n11 - n13;
                                        final int n25 = aClass293_3986.anIntArray2217[n24];
                                        if (n23 >= n25 && n23 <= n25 + aClass293_3986.anIntArray2298[n24]) {
                                            final int n26 = n23 - aClass293_3986.anInt2311 / 2;
                                            final int n27 = n24 - aClass293_3986.anInt2258 / 2;
                                            int n28;
                                            if (Class98_Sub46_Sub20_Sub2.anInt6319 == 4) {
                                                n28 = ((int)Class98_Sub22_Sub2.aFloat5794 & 0x3FFF);
                                            }
                                            else {
                                                n28 = ((int)Class98_Sub22_Sub2.aFloat5794 + Class204.anInt1553 & 0x3FFF);
                                            }
                                            int n29 = Class284_Sub2_Sub2.anIntArray6200[n28];
                                            int n30 = Class284_Sub2_Sub2.anIntArray6202[n28];
                                            if (Class98_Sub46_Sub20_Sub2.anInt6319 != 4) {
                                                n29 = n29 * (Class151.anInt1213 + 256) >> 8;
                                                n30 = n30 * (Class151.anInt1213 + 256) >> 8;
                                            }
                                            final int n31 = n27 * n29 + n26 * n30 >> 14;
                                            final int n32 = n27 * n30 - n26 * n29 >> 14;
                                            int n33;
                                            int n34;
                                            if (Class98_Sub46_Sub20_Sub2.anInt6319 == 4) {
                                                n33 = (Class98_Sub46_Sub2_Sub2.anInt6295 >> 9) + (n31 >> 2);
                                                n34 = (Class135.anInt1051 >> 9) - (n32 >> 2);
                                            }
                                            else {
                                                final int n35 = (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(0) - 1) * 256;
                                                n33 = (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 - n35 >> 9) + (n31 >> 2);
                                                n34 = (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 - n35 >> 9) - (n32 >> 2);
                                            }
                                            if (Class98_Sub10_Sub9.aBoolean5585 && (Class98_Sub4.anInt3826 & 0x40) != 0x0) {
                                                if (Class246_Sub9.method3139((byte)72, Class187.anInt1450, Class310.anInt2652) != null) {
                                                    Class293.method3470(false, true, 1L, Class336.anInt2823, n33, " ->", true, n34, 46, aClass293_3986.anInt2300 << 32 | aClass293_3986.anInt2248, aClass293_3986.anInt2302, false, Class287_Sub2.aString3272);
                                                }
                                                else {
                                                    Class98_Sub10_Sub32.method1098((byte)111);
                                                }
                                            }
                                            else {
                                                if (Class4.aClass279_86 == Class64_Sub2.aClass279_3643) {
                                                    Class293.method3470(false, true, 1L, -1, n33, "", true, n34, 60, 0L, -1, false, Class309.aClass309_2613.method3615(Class374.anInt3159, (byte)25));
                                                }
                                                Class293.method3470(false, true, 1L, Class199.anInt1541, n33, "", true, n34, 6, 0L, -1, false, Class218.aString1636);
                                            }
                                        }
                                    }
                                    continue;
                                }
                                else if (aClass293_3986.anInt2307 == Class87.anInt673) {
                                    Class11.aClass293_120 = aClass293_3986;
                                    if (n20 != 0) {
                                        Class253.aBoolean1930 = true;
                                    }
                                    if (n21 != 0) {
                                        final int n36 = (int)((method2642 + class98_Sub22.method1155(19) - n12 - aClass293_3986.anInt2311 / 2) * 2.0 / Class278.aFloat2064);
                                        final int n37 = (int)(-((method2643 + class98_Sub22.method1151(69) - n13 - aClass293_3986.anInt2258 / 2) * 2.0 / Class278.aFloat2064));
                                        final int n38 = Class42_Sub4.anInt5371 + n36 + Class278.anInt2075;
                                        final int n39 = Class98_Sub40.anInt4197 + n37 + Class278.anInt2078;
                                        final Class98_Sub46_Sub10 method2645 = Class98_Sub10_Sub8.method1026(-3);
                                        if (method2645 != null) {
                                            final int[] array2 = new int[3];
                                            method2645.method1563(31960, n38, array2, n39);
                                            if (array2 != null) {
                                                if (Class219.aClass77_1641.method779(82, 5503) && Class282.anInt2125 > 0) {
                                                    Class351.method3846(array2[2], array2[1], array2[0], -52);
                                                    continue;
                                                }
                                                Class102.aBoolean889 = true;
                                                Class333.anInt3385 = array2[0];
                                                Class122.anInt1009 = array2[1];
                                                Class375.anInt3169 = array2[2];
                                            }
                                            Class156_Sub2.anInt3423 = 1;
                                            Class81.aBoolean621 = false;
                                            Class178.anInt1405 = Class2.aClass299_73.method3514(106);
                                            PlayerUpdateMask.anInt526 = Class2.aClass299_73.method3507((byte)46);
                                        }
                                        continue;
                                    }
                                    if (!b || Class156_Sub2.anInt3423 <= 0) {
                                        if (Class156_Sub2.anInt3423 > 0 && !Class81.aBoolean621) {
                                            if ((Class305_Sub1.anInt5303 == 1 || r_Sub1.method1642((byte)127)) && Class359.anInt3058 > 2) {
                                                Class353.method3869(PlayerUpdateMask.anInt526, Class178.anInt1405, -2);
                                            }
                                            else if (Class98_Sub17_Sub1.method1160(90)) {
                                                Class353.method3869(PlayerUpdateMask.anInt526, Class178.anInt1405, -2);
                                            }
                                        }
                                        Class156_Sub2.anInt3423 = 0;
                                        continue;
                                    }
                                    if (Class156_Sub2.anInt3423 == 1 && (Class178.anInt1405 != Class2.aClass299_73.method3514(64) || PlayerUpdateMask.anInt526 != Class2.aClass299_73.method3507((byte)89))) {
                                        client.anInt3550 = Class42_Sub4.anInt5371;
                                        Class265.anInt1975 = Class98_Sub40.anInt4197;
                                        Class156_Sub2.anInt3423 = 2;
                                    }
                                    if (Class156_Sub2.anInt3423 == 2) {
                                        Class81.aBoolean621 = true;
                                        Class92.method891(client.anInt3550 + (int)((Class178.anInt1405 - Class2.aClass299_73.method3514(27)) * 2.0 / Class278.aFloat2068), -94);
                                        Class113.method2144(-4365, Class265.anInt1975 - (int)((PlayerUpdateMask.anInt526 - Class2.aClass299_73.method3507((byte)92)) * 2.0 / Class278.aFloat2068));
                                    }
                                    continue;
                                }
                                else if (aClass293_3986.anInt2307 == Class239.anInt1841) {
                                    if (b) {
                                        Class202.method2698((byte)48, method2643 + Class2.aClass299_73.method3507((byte)29) - n13, aClass293_3986.anInt2311, method2642 + Class2.aClass299_73.method3514(18) - n12, aClass293_3986.anInt2258);
                                    }
                                    continue;
                                }
                                else if (aClass293_3986.anInt2307 == Class373_Sub2.anInt5471) {
                                    Class304.method3563(n13, aClass293_3986, n12, 60);
                                    continue;
                                }
                            }
                            if (!aClass293_3986.aBoolean2241 && n21 != 0) {
                                aClass293_3986.aBoolean2241 = true;
                                if (aClass293_3986.anObjectArray2291 != null) {
                                    final Class98_Sub21 class98_Sub24 = new Class98_Sub21();
                                    class98_Sub24.aBoolean3980 = true;
                                    class98_Sub24.aClass293_3986 = aClass293_3986;
                                    class98_Sub24.anInt3985 = method2642 + class98_Sub22.method1155(-25) - n12;
                                    class98_Sub24.anInt3979 = method2643 + class98_Sub22.method1151(54) - n13;
                                    class98_Sub24.anObjectArray3981 = aClass293_3986.anObjectArray2291;
                                    Class151_Sub3.aClass148_4977.method2419(class98_Sub24, -20911);
                                }
                            }
                            if (aClass293_3986.aBoolean2241 && b && aClass293_3986.anObjectArray2335 != null) {
                                final Class98_Sub21 class98_Sub25 = new Class98_Sub21();
                                class98_Sub25.aBoolean3980 = true;
                                class98_Sub25.aClass293_3986 = aClass293_3986;
                                class98_Sub25.anInt3985 = method2642 + Class2.aClass299_73.method3514(118) - n12;
                                class98_Sub25.anInt3979 = method2643 + Class2.aClass299_73.method3507((byte)47) - n13;
                                class98_Sub25.anObjectArray3981 = aClass293_3986.anObjectArray2335;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub25, -20911);
                            }
                            if (aClass293_3986.aBoolean2241 && !b) {
                                aClass293_3986.aBoolean2241 = false;
                                if (aClass293_3986.anObjectArray2356 != null) {
                                    final Class98_Sub21 class98_Sub26 = new Class98_Sub21();
                                    class98_Sub26.aBoolean3980 = true;
                                    class98_Sub26.aClass293_3986 = aClass293_3986;
                                    class98_Sub26.anInt3985 = method2642 + Class2.aClass299_73.method3514(47) - n12;
                                    class98_Sub26.anInt3979 = method2643 + Class2.aClass299_73.method3507((byte)72) - n13;
                                    class98_Sub26.anObjectArray3981 = aClass293_3986.anObjectArray2356;
                                    Class98_Sub46_Sub10.aClass148_6018.method2419(class98_Sub26, -20911);
                                }
                            }
                            if (b && aClass293_3986.anObjectArray2230 != null) {
                                final Class98_Sub21 class98_Sub27 = new Class98_Sub21();
                                class98_Sub27.aBoolean3980 = true;
                                class98_Sub27.aClass293_3986 = aClass293_3986;
                                class98_Sub27.anInt3985 = method2642 + Class2.aClass299_73.method3514(51) - n12;
                                class98_Sub27.anInt3979 = method2643 + Class2.aClass299_73.method3507((byte)85) - n13;
                                class98_Sub27.anObjectArray3981 = aClass293_3986.anObjectArray2230;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub27, -20911);
                            }
                            if (!aClass293_3986.aBoolean2322 && n20 != 0) {
                                aClass293_3986.aBoolean2322 = true;
                                if (aClass293_3986.anObjectArray2227 != null) {
                                    final Class98_Sub21 class98_Sub28 = new Class98_Sub21();
                                    class98_Sub28.aBoolean3980 = true;
                                    class98_Sub28.aClass293_3986 = aClass293_3986;
                                    class98_Sub28.anInt3985 = method2642 + Class2.aClass299_73.method3514(10) - n12;
                                    class98_Sub28.anInt3979 = method2643 + Class2.aClass299_73.method3507((byte)124) - n13;
                                    class98_Sub28.anObjectArray3981 = aClass293_3986.anObjectArray2227;
                                    Class151_Sub3.aClass148_4977.method2419(class98_Sub28, -20911);
                                }
                            }
                            if (aClass293_3986.aBoolean2322 && n20 != 0 && aClass293_3986.anObjectArray2314 != null) {
                                final Class98_Sub21 class98_Sub29 = new Class98_Sub21();
                                class98_Sub29.aBoolean3980 = true;
                                class98_Sub29.aClass293_3986 = aClass293_3986;
                                class98_Sub29.anInt3985 = method2642 + Class2.aClass299_73.method3514(45) - n12;
                                class98_Sub29.anInt3979 = method2643 + Class2.aClass299_73.method3507((byte)99) - n13;
                                class98_Sub29.anObjectArray3981 = aClass293_3986.anObjectArray2314;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub29, -20911);
                            }
                            if (aClass293_3986.aBoolean2322 && n20 == 0) {
                                aClass293_3986.aBoolean2322 = false;
                                if (aClass293_3986.anObjectArray2272 != null) {
                                    final Class98_Sub21 class98_Sub30 = new Class98_Sub21();
                                    class98_Sub30.aBoolean3980 = true;
                                    class98_Sub30.aClass293_3986 = aClass293_3986;
                                    class98_Sub30.anInt3985 = method2642 + Class2.aClass299_73.method3514(116) - n12;
                                    class98_Sub30.anInt3979 = method2643 + Class2.aClass299_73.method3507((byte)77) - n13;
                                    class98_Sub30.anObjectArray3981 = aClass293_3986.anObjectArray2272;
                                    Class98_Sub46_Sub10.aClass148_6018.method2419(class98_Sub30, -20911);
                                }
                            }
                            if (aClass293_3986.anObjectArray2270 != null) {
                                final Class98_Sub21 class98_Sub31 = new Class98_Sub21();
                                class98_Sub31.aClass293_3986 = aClass293_3986;
                                class98_Sub31.anObjectArray3981 = aClass293_3986.anObjectArray2270;
                                Class181.aClass148_1430.method2419(class98_Sub31, -20911);
                            }
                            if (aClass293_3986.anObjectArray2212 != null && Class246_Sub4_Sub2.anInt6181 > aClass293_3986.anInt2223) {
                                Label_3615: {
                                    if (aClass293_3986.anIntArray2297 == null || Class246_Sub4_Sub2.anInt6181 - aClass293_3986.anInt2223 > 32) {
                                        final Class98_Sub21 class98_Sub32 = new Class98_Sub21();
                                        class98_Sub32.aClass293_3986 = aClass293_3986;
                                        class98_Sub32.anObjectArray3981 = aClass293_3986.anObjectArray2212;
                                        Class151_Sub3.aClass148_4977.method2419(class98_Sub32, -20911);
                                    }
                                    else {
                                        for (int k = aClass293_3986.anInt2223; k < Class246_Sub4_Sub2.anInt6181; ++k) {
                                            final int n40 = Class98_Sub45.anIntArray4257[k & 0x1F];
                                            for (int l = 0; l < aClass293_3986.anIntArray2297.length; ++l) {
                                                if (aClass293_3986.anIntArray2297[l] == n40) {
                                                    final Class98_Sub21 class98_Sub33 = new Class98_Sub21();
                                                    class98_Sub33.aClass293_3986 = aClass293_3986;
                                                    class98_Sub33.anObjectArray3981 = aClass293_3986.anObjectArray2212;
                                                    Class151_Sub3.aClass148_4977.method2419(class98_Sub33, -20911);
                                                    break Label_3615;
                                                }
                                            }
                                        }
                                    }
                                }
                                aClass293_3986.anInt2223 = Class246_Sub4_Sub2.anInt6181;
                            }
                            if (aClass293_3986.anObjectArray2320 != null && Class96.anInt803 > aClass293_3986.anInt2282) {
                                Label_3809: {
                                    if (aClass293_3986.anIntArray2342 == null || Class96.anInt803 - aClass293_3986.anInt2282 > 32) {
                                        final Class98_Sub21 class98_Sub34 = new Class98_Sub21();
                                        class98_Sub34.aClass293_3986 = aClass293_3986;
                                        class98_Sub34.anObjectArray3981 = aClass293_3986.anObjectArray2320;
                                        Class151_Sub3.aClass148_4977.method2419(class98_Sub34, -20911);
                                    }
                                    else {
                                        for (int anInt2282 = aClass293_3986.anInt2282; anInt2282 < Class96.anInt803; ++anInt2282) {
                                            final int n41 = Class148.anIntArray1196[anInt2282 & 0x1F];
                                            for (int n42 = 0; n42 < aClass293_3986.anIntArray2342.length; ++n42) {
                                                if (aClass293_3986.anIntArray2342[n42] == n41) {
                                                    final Class98_Sub21 class98_Sub35 = new Class98_Sub21();
                                                    class98_Sub35.aClass293_3986 = aClass293_3986;
                                                    class98_Sub35.anObjectArray3981 = aClass293_3986.anObjectArray2320;
                                                    Class151_Sub3.aClass148_4977.method2419(class98_Sub35, -20911);
                                                    break Label_3809;
                                                }
                                            }
                                        }
                                    }
                                }
                                aClass293_3986.anInt2282 = Class96.anInt803;
                            }
                            if (aClass293_3986.anObjectArray2269 != null && Class239.anInt1844 > aClass293_3986.anInt2328) {
                                Label_4003: {
                                    if (aClass293_3986.anIntArray2284 == null || Class239.anInt1844 - aClass293_3986.anInt2328 > 32) {
                                        final Class98_Sub21 class98_Sub36 = new Class98_Sub21();
                                        class98_Sub36.aClass293_3986 = aClass293_3986;
                                        class98_Sub36.anObjectArray3981 = aClass293_3986.anObjectArray2269;
                                        Class151_Sub3.aClass148_4977.method2419(class98_Sub36, -20911);
                                    }
                                    else {
                                        for (int anInt2283 = aClass293_3986.anInt2328; anInt2283 < Class239.anInt1844; ++anInt2283) {
                                            final int n43 = Class111_Sub1.anIntArray4682[anInt2283 & 0x1F];
                                            for (int n44 = 0; n44 < aClass293_3986.anIntArray2284.length; ++n44) {
                                                if (aClass293_3986.anIntArray2284[n44] == n43) {
                                                    final Class98_Sub21 class98_Sub37 = new Class98_Sub21();
                                                    class98_Sub37.aClass293_3986 = aClass293_3986;
                                                    class98_Sub37.anObjectArray3981 = aClass293_3986.anObjectArray2269;
                                                    Class151_Sub3.aClass148_4977.method2419(class98_Sub37, -20911);
                                                    break Label_4003;
                                                }
                                            }
                                        }
                                    }
                                }
                                aClass293_3986.anInt2328 = Class239.anInt1844;
                            }
                            if (aClass293_3986.anObjectArray2252 != null && Class145.anInt1172 > aClass293_3986.anInt2323) {
                                Label_4197: {
                                    if (aClass293_3986.anIntArray2249 == null || Class145.anInt1172 - aClass293_3986.anInt2323 > 32) {
                                        final Class98_Sub21 class98_Sub38 = new Class98_Sub21();
                                        class98_Sub38.aClass293_3986 = aClass293_3986;
                                        class98_Sub38.anObjectArray3981 = aClass293_3986.anObjectArray2252;
                                        Class151_Sub3.aClass148_4977.method2419(class98_Sub38, -20911);
                                    }
                                    else {
                                        for (int anInt2284 = aClass293_3986.anInt2323; anInt2284 < Class145.anInt1172; ++anInt2284) {
                                            final int n45 = Class78.anIntArray597[anInt2284 & 0x1F];
                                            for (int n46 = 0; n46 < aClass293_3986.anIntArray2249.length; ++n46) {
                                                if (aClass293_3986.anIntArray2249[n46] == n45) {
                                                    final Class98_Sub21 class98_Sub39 = new Class98_Sub21();
                                                    class98_Sub39.aClass293_3986 = aClass293_3986;
                                                    class98_Sub39.anObjectArray3981 = aClass293_3986.anObjectArray2252;
                                                    Class151_Sub3.aClass148_4977.method2419(class98_Sub39, -20911);
                                                    break Label_4197;
                                                }
                                            }
                                        }
                                    }
                                }
                                aClass293_3986.anInt2323 = Class145.anInt1172;
                            }
                            if (aClass293_3986.anObjectArray2278 != null && Class93_Sub1.anInt5477 > aClass293_3986.anInt2276) {
                                Label_4391: {
                                    if (aClass293_3986.anIntArray2271 == null || Class93_Sub1.anInt5477 - aClass293_3986.anInt2276 > 32) {
                                        final Class98_Sub21 class98_Sub40 = new Class98_Sub21();
                                        class98_Sub40.aClass293_3986 = aClass293_3986;
                                        class98_Sub40.anObjectArray3981 = aClass293_3986.anObjectArray2278;
                                        Class151_Sub3.aClass148_4977.method2419(class98_Sub40, -20911);
                                    }
                                    else {
                                        for (int anInt2285 = aClass293_3986.anInt2276; anInt2285 < Class93_Sub1.anInt5477; ++anInt2285) {
                                            final int n47 = Class98_Sub16.anIntArray3928[anInt2285 & 0x1F];
                                            for (int n48 = 0; n48 < aClass293_3986.anIntArray2271.length; ++n48) {
                                                if (aClass293_3986.anIntArray2271[n48] == n47) {
                                                    final Class98_Sub21 class98_Sub41 = new Class98_Sub21();
                                                    class98_Sub41.aClass293_3986 = aClass293_3986;
                                                    class98_Sub41.anObjectArray3981 = aClass293_3986.anObjectArray2278;
                                                    Class151_Sub3.aClass148_4977.method2419(class98_Sub41, -20911);
                                                    break Label_4391;
                                                }
                                            }
                                        }
                                    }
                                }
                                aClass293_3986.anInt2276 = Class93_Sub1.anInt5477;
                            }
                            if (Class98_Sub3.anInt3825 > aClass293_3986.anInt2247 && aClass293_3986.anObjectArray2239 != null) {
                                final Class98_Sub21 class98_Sub42 = new Class98_Sub21();
                                class98_Sub42.aClass293_3986 = aClass293_3986;
                                class98_Sub42.anObjectArray3981 = aClass293_3986.anObjectArray2239;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub42, -20911);
                            }
                            if (Class363.anInt3099 > aClass293_3986.anInt2247 && aClass293_3986.anObjectArray2215 != null) {
                                final Class98_Sub21 class98_Sub43 = new Class98_Sub21();
                                class98_Sub43.aClass293_3986 = aClass293_3986;
                                class98_Sub43.anObjectArray3981 = aClass293_3986.anObjectArray2215;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub43, -20911);
                            }
                            if (Class64_Sub22.anInt3705 > aClass293_3986.anInt2247 && aClass293_3986.anObjectArray2292 != null) {
                                final Class98_Sub21 class98_Sub44 = new Class98_Sub21();
                                class98_Sub44.aClass293_3986 = aClass293_3986;
                                class98_Sub44.anObjectArray3981 = aClass293_3986.anObjectArray2292;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub44, -20911);
                            }
                            if (Class64_Sub11.anInt3668 > aClass293_3986.anInt2247 && aClass293_3986.anObjectArray2294 != null) {
                                final Class98_Sub21 class98_Sub45 = new Class98_Sub21();
                                class98_Sub45.aClass293_3986 = aClass293_3986;
                                class98_Sub45.anObjectArray3981 = aClass293_3986.anObjectArray2294;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub45, -20911);
                            }
                            if (Class98_Sub36.anInt4161 > aClass293_3986.anInt2247 && aClass293_3986.anObjectArray2340 != null) {
                                final Class98_Sub21 class98_Sub46 = new Class98_Sub21();
                                class98_Sub46.aClass293_3986 = aClass293_3986;
                                class98_Sub46.anObjectArray3981 = aClass293_3986.anObjectArray2340;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub46, -20911);
                            }
                            aClass293_3986.anInt2247 = Class24.anInt242;
                            if (aClass293_3986.anObjectArray2274 != null) {
                                for (int n49 = 0; n49 < Class329.anInt2765; ++n49) {
                                    final Class98_Sub21 class98_Sub47 = new Class98_Sub21();
                                    class98_Sub47.aClass293_3986 = aClass293_3986;
                                    class98_Sub47.anInt3977 = Class21_Sub1.anInterface7Array5385[n49].method17(true);
                                    class98_Sub47.anInt3976 = Class21_Sub1.anInterface7Array5385[n49].method15(13313);
                                    class98_Sub47.anObjectArray3981 = aClass293_3986.anObjectArray2274;
                                    Class151_Sub3.aClass148_4977.method2419(class98_Sub47, -20911);
                                }
                            }
                            if (Class187.aBoolean1451 && aClass293_3986.anObjectArray2220 != null) {
                                final Class98_Sub21 class98_Sub48 = new Class98_Sub21();
                                class98_Sub48.aClass293_3986 = aClass293_3986;
                                class98_Sub48.anObjectArray3981 = aClass293_3986.anObjectArray2220;
                                Class151_Sub3.aClass148_4977.method2419(class98_Sub48, -20911);
                            }
                        }
                        if (aClass293_3986.anInt2354 == 5 && aClass293_3986.anInt2267 != -1) {
                            aClass293_3986.method3467(0, Class101.aClass115_857, Class373_Sub2.aClass59_5470).method3828(aClass293_3986.anInt2258, 6, Class265.aHa1974);
                        }
                        Class98_Sub10_Sub21.method1066(124, aClass293_3986);
                        if (aClass293_3986.anInt2354 == 0) {
                            method104(array, aClass293_3986.anInt2248, n14, n15, n16, n17, n12 - aClass293_3986.anInt2246, n13 - aClass293_3986.anInt2213, n8, n9, n10, n11);
                            if (aClass293_3986.aClass293Array2339 != null) {
                                method104(aClass293_3986.aClass293Array2339, aClass293_3986.anInt2248, n14, n15, n16, n17, n12 - aClass293_3986.anInt2246, n13 - aClass293_3986.anInt2213, n8, n9, n10, n11);
                            }
                            final Class98_Sub18 class98_Sub49 = (Class98_Sub18)Class116.aClass377_964.method3990(aClass293_3986.anInt2248, -1);
                            if (class98_Sub49 != null) {
                                if (Class4.aClass279_86 == s_Sub1.aClass279_5197 && class98_Sub49.anInt3947 == 0 && !Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 && n20 != 0 && !Class15.aBoolean169) {
                                    Class46.method435((byte)117);
                                }
                                Class62.method544(n10, n15, class98_Sub49.anInt3945, n16, 0, n17, n12, n8, n9, n11, n13, n14);
                            }
                        }
                    }
                }
            }
        }
    }
    
    static final void method105(final int n) {
        final int anInt71 = Class2.anInt71;
        final int[] anIntArray2705 = Class319.anIntArray2705;
        for (int i = 0; i < anInt71 + Class150.anInt1211; ++i) {
            Class246_Sub3_Sub4_Sub2 aClass246_Sub3_Sub4_Sub2_Sub1_4187;
            if (i < anInt71) {
                aClass246_Sub3_Sub4_Sub2_Sub1_4187 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[i]];
            }
            else {
                aClass246_Sub3_Sub4_Sub2_Sub1_4187 = ((Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[i - anInt71], -1)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
            }
            if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088 == n && aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 >= 0) {
                final int method3034 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3034(0);
                if ((method3034 & 0x1) == 0x0) {
                    if ((aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 & 0x1FF) != 0x0) {
                        continue;
                    }
                    if ((aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 & 0x1FF) != 0x0) {
                        continue;
                    }
                }
                else {
                    if ((aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 & 0x1FF) != 0x100) {
                        continue;
                    }
                    if ((aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 & 0x1FF) != 0x100) {
                        continue;
                    }
                }
                if (method3034 == 1) {
                    final int n2 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 >> 9;
                    final int n3 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 >> 9;
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 > Class372.anIntArrayArray3149[n2][n3]) {
                        Class372.anIntArrayArray3149[n2][n3] = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372;
                        Class64_Sub28.anIntArrayArray3719[n2][n3] = 1;
                    }
                    else if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 == Class372.anIntArrayArray3149[n2][n3]) {
                        final int[] array = Class64_Sub28.anIntArrayArray3719[n2];
                        final int n4 = n3;
                        ++array[n4];
                    }
                }
                else {
                    final int n5 = (method3034 - 1) * 256 + 60;
                    final int n6 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 - n5 >> 9;
                    final int n7 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 - n5 >> 9;
                    final int n8 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 + n5 >> 9;
                    final int n9 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 + n5 >> 9;
                    for (int j = n6; j <= n8; ++j) {
                        for (int k = n7; k <= n9; ++k) {
                            if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 > Class372.anIntArrayArray3149[j][k]) {
                                Class372.anIntArrayArray3149[j][k] = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372;
                                Class64_Sub28.anIntArrayArray3719[j][k] = 1;
                            }
                            else if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 == Class372.anIntArrayArray3149[j][k]) {
                                final int[] array2 = Class64_Sub28.anIntArrayArray3719[j];
                                final int n10 = k;
                                ++array2[n10];
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    final void method86(final byte b) {
        try {
            if (b != -6) {
                method104(null, 101, 22, 118, 53, 94, -102, -124, -49, -22, -63, 30);
            }
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)122) == 0xFFFFFFFD) {
                try {
                    this.method108((byte)68);
                    return;
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    Class305_Sub1.method3585(t, b ^ 0x7E, t.getMessage() + " (Recovered) " + this.method94(0));
                    Class223.aBoolean1679 = true;
                    Class76_Sub4.method754(0, false, -125);
                    return;
                }
            }
            this.method108((byte)68);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.Q(" + b + ')');
        }
    }
    
    private final void method106(final byte b) {
        try {
            if (~Class98_Sub10_Sub38.aClass135_5765.anInt1065 < ~Class110.anInt944) {
                Class98_Sub46_Sub10.aClass354_6011.method3874(0);
                Class379.anInt3196 = (Class98_Sub10_Sub38.aClass135_5765.anInt1065 - 1) * 250;
                if (~Class379.anInt3196 < -3001) {
                    Class379.anInt3196 = 3000;
                }
                if (Class98_Sub10_Sub38.aClass135_5765.anInt1065 >= 2 && ~Class98_Sub10_Sub38.aClass135_5765.anInt1066 == 0xFFFFFFF9) {
                    this.method97((byte)89, "js5connect_outofdate");
                    Class177.anInt1376 = 14;
                    return;
                }
                if (Class98_Sub10_Sub38.aClass135_5765.anInt1065 >= 4 && Class98_Sub10_Sub38.aClass135_5765.anInt1066 == -1) {
                    this.method97((byte)(-126), "js5crc");
                    Class177.anInt1376 = 14;
                    return;
                }
                if (Class98_Sub10_Sub38.aClass135_5765.anInt1065 >= 4 && Class48_Sub1_Sub1.method462(Class177.anInt1376, 126)) {
                    if (~Class98_Sub10_Sub38.aClass135_5765.anInt1066 != 0xFFFFFFF8 && Class98_Sub10_Sub38.aClass135_5765.anInt1066 != 9) {
                        if (Class98_Sub10_Sub38.aClass135_5765.anInt1066 <= 0) {
                            this.method97((byte)112, "js5io");
                        }
                        else if (s.aString2202 != null) {
                            this.method97((byte)(-60), "js5proxy_" + s.aString2202.trim());
                        }
                        else {
                            this.method97((byte)125, "js5connect");
                        }
                    }
                    else {
                        this.method97((byte)(-79), "js5connect_full");
                    }
                    Class177.anInt1376 = 14;
                    return;
                }
            }
            Class110.anInt944 = Class98_Sub10_Sub38.aClass135_5765.anInt1065;
            if (~Class379.anInt3196 < -1) {
                --Class379.anInt3196;
            }
            else {
                try {
                    if (b <= -127) {
                        if (Class221.anInt1664 == 0) {
                            Class98_Sub10_Sub36.aClass143_5745 = Class98_Sub46_Sub10.aClass354_6011.method3870(-111, Class98_Sub43_Sub2.aClass88_5907);
                            ++Class221.anInt1664;
                        }
                        if (~Class221.anInt1664 == 0xFFFFFFFE) {
                            if (~Class98_Sub10_Sub36.aClass143_5745.anInt1163 == 0xFFFFFFFD) {
                                if (Class98_Sub10_Sub36.aClass143_5745.anObject1162 != null) {
                                    s.aString2202 = (String)Class98_Sub10_Sub36.aClass143_5745.anObject1162;
                                }
                                this.method107((byte)(-119), 1000);
                                return;
                            }
                            if (~Class98_Sub10_Sub36.aClass143_5745.anInt1163 == 0xFFFFFFFE) {
                                ++Class221.anInt1664;
                            }
                        }
                        if (~Class221.anInt1664 == 0xFFFFFFFD) {
                            Class219.aClass361_1642 = new Class361((Socket)Class98_Sub10_Sub36.aClass143_5745.anObject1162, Class98_Sub43_Sub2.aClass88_5907, 25000);
                            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(5);
                            class98_Sub22.method1194(Class298.aClass222_2480.anInt1668, -84);
                            class98_Sub22.writeInt(1571862888, 637);
                            Class219.aClass361_1642.method3920((byte)77, 0, 5, class98_Sub22.aByteArray3992);
                            ++Class221.anInt1664;
                            Class337.aLong3537 = Class343.method3819(-47);
                        }
                        if (Class221.anInt1664 == 3) {
                            if (Class48_Sub1_Sub1.method462(Class177.anInt1376, 127) || Class219.aClass361_1642.method3915(95) > 0) {
                                final int method3918 = Class219.aClass361_1642.method3918(-14234);
                                if (method3918 != 0) {
                                    this.method107((byte)(-96), method3918);
                                    return;
                                }
                                ++Class221.anInt1664;
                            }
                            else if (Class343.method3819(-47) + -Class337.aLong3537 > 30000L) {
                                this.method107((byte)(-95), 1001);
                                return;
                            }
                        }
                        if (~Class221.anInt1664 == 0xFFFFFFFB) {
                            final boolean b2 = Class48_Sub1_Sub1.method462(Class177.anInt1376, 127) || Class53_Sub1.method499(2048, Class177.anInt1376) || Class246_Sub3_Sub3.method3011(-6410, Class177.anInt1376);
                            final Class102[] method3919 = Class102.method1704(4);
                            final Class98_Sub22 class98_Sub23 = new Class98_Sub22(4 * method3919.length);
                            Class219.aClass361_1642.method3921(0, true, class98_Sub23.aByteArray3992.length, class98_Sub23.aByteArray3992);
                            for (int n = 0; ~method3919.length < ~n; ++n) {
                                method3919[n].method1706(class98_Sub23.readInt(-2), 107);
                            }
                            Class98_Sub10_Sub38.aClass135_5765.method2260(Class219.aClass361_1642, !b2, (byte)74);
                            Class219.aClass361_1642 = null;
                            Class221.anInt1664 = 0;
                            Class98_Sub10_Sub36.aClass143_5745 = null;
                        }
                    }
                }
                catch (IOException ex2) {
                    this.method107((byte)(-118), 1002);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.JA(" + b + ')');
        }
    }
    
    @Override
    final void method92(final boolean b) {
        try {
            if (Class66.aBoolean507) {
                Class23.method283((byte)98);
            }
            Class98_Sub43_Sub1.method1493(124);
            if (Class265.aHa1974 != null) {
                Class265.aHa1974.method1743(-1);
            }
            if (Class98_Sub18.aFrame3950 != null) {
                Class281.method3331(b, Class98_Sub18.aFrame3950, Class98_Sub43_Sub2.aClass88_5907);
                Class98_Sub18.aFrame3950 = null;
            }
            if (aa_Sub1.aClass123_3561 != null) {
                aa_Sub1.aClass123_3561.method2207(-18);
                aa_Sub1.aClass123_3561 = null;
            }
            Class246_Sub3_Sub4_Sub5.method3084(true);
            Class98_Sub10_Sub38.aClass135_5765.method2249(-84);
            Class168.aClass253_1290.method3183((byte)(-75));
            if (Class48_Sub2_Sub1.aClass265_5531 != null) {
                Class48_Sub2_Sub1.aClass265_5531.method3232((byte)(-103));
                Class48_Sub2_Sub1.aClass265_5531 = null;
            }
            try {
                Class98_Sub37.aClass225_4178.method2843((byte)(-54));
                int i = 0;
                if (b) {
                    method115();
                }
                while (i < 37) {
                    Class55.aClass225Array444[i].method2843((byte)(-99));
                    ++i;
                }
                Class252.aClass225_1926.method2843((byte)(-73));
                Class195.aClass225_1502.method2843((byte)(-67));
                Class42_Sub1_Sub1.method387(true);
            }
            catch (Exception ex2) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.F(" + b + ')');
        }
    }
    
    private final void method107(final byte b, final int anInt1066) {
        try {
            Class221.anInt1664 = 0;
            final Class135 aClass135_5765 = Class98_Sub10_Sub38.aClass135_5765;
            ++aClass135_5765.anInt1065;
            if (b >= -89) {
                method115();
            }
            Class98_Sub10_Sub36.aClass143_5745 = null;
            Class219.aClass361_1642 = null;
            Class98_Sub10_Sub38.aClass135_5765.anInt1066 = anInt1066;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.HA(" + b + ',' + anInt1066 + ')');
        }
    }
    
    private final void method108(final byte b) {
        try {
            if (Class177.anInt1376 != 14) {
                ++Class215.anInt1614;
                if (~(Class215.anInt1614 % 1000) == 0xFFFFFFFE) {
                    final GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    Class39_Sub1.anInt3591 = 600 * gregorianCalendar.get(11) + (gregorianCalendar.get(12) * 10 - -(gregorianCalendar.get(13) / 6));
                    Class76_Sub8.aRandom3767.setSeed(Class39_Sub1.anInt3591);
                }
                if (Class215.anInt1614 % 50 == 0) {
                    Class98_Sub12.anInt3879 = Class98_Sub50.anInt4289;
                    Class118.anInt975 = Class103.anInt892;
                    Class98_Sub50.anInt4289 = 0;
                    Class103.anInt892 = 0;
                }
                this.method109((byte)59);
                if (Class161.aClass109_1261 != null) {
                    Class161.aClass109_1261.method1736(0);
                }
                Class40.method360((byte)79);
                Class64_Sub7.method578(16543);
                Class219.aClass77_1641.method774((byte)(-113));
                Class2.aClass299_73.method3516((byte)108);
                if (Class265.aHa1974 != null) {
                    Class265.aHa1974.method1806((int)Class343.method3819(b ^ 0xFFFFFF95));
                }
                Class301.method3540(-2);
                Class98_Sub46_Sub1.anInt5949 = 0;
                Class329.anInt2765 = 0;
                if (b != 68) {
                    method103(81, null);
                }
                for (Interface7 interface7 = Class219.aClass77_1641.method776((byte)31); interface7 != null; interface7 = Class219.aClass77_1641.method776((byte)31)) {
                    final int method14 = interface7.method14(-111);
                    if (~method14 != 0xFFFFFFFD && ~method14 != 0xFFFFFFFC) {
                        if (method14 == 0 && ~Class98_Sub46_Sub1.anInt5949 > -76) {
                            Class339.anInterface7Array2845[Class98_Sub46_Sub1.anInt5949] = interface7;
                            ++Class98_Sub46_Sub1.anInt5949;
                        }
                    }
                    else {
                        final char method15 = interface7.method15(13313);
                        if (!Class146_Sub3.method2408((byte)57) || (method15 != '`' && method15 != '§' && ~method15 != 0xFFFFFF4D)) {
                            if (Class329.anInt2765 < 128) {
                                Class21_Sub1.anInterface7Array5385[Class329.anInt2765] = interface7;
                                ++Class329.anInt2765;
                            }
                        }
                        else if (!Class217.method2800(-8001)) {
                            Class98_Sub20.method1173(0);
                        }
                        else {
                            Class8.method187(true);
                        }
                    }
                }
                Class319.anInt2699 = 0;
                for (Class98_Sub17 class98_Sub17 = Class2.aClass299_73.method3508(600); class98_Sub17 != null; class98_Sub17 = Class2.aClass299_73.method3508(600)) {
                    final int method16 = class98_Sub17.method1156(b ^ 0xFFFFFFBF);
                    if (method16 == -1) {
                        Class64_Sub21.aClass148_3703.method2419(class98_Sub17, -20911);
                    }
                    else if (method16 != 6) {
                        if (Class265.method3230(b - 192, method16)) {
                            Class167.aClass148_1284.method2419(class98_Sub17, b ^ 0xFFFFAE15);
                            if (Class167.aClass148_1284.method2424(0) > 10) {
                                Class167.aClass148_1284.method2421(b ^ 0x191A);
                            }
                        }
                    }
                    else {
                        Class319.anInt2699 += class98_Sub17.method1152(b - 66);
                    }
                }
                if (Class217.method2800(-8001)) {
                    Class261.method3212(104);
                }
                if (!Class48_Sub1_Sub1.method462(Class177.anInt1376, 126)) {
                    if (Class199.method2690(Class177.anInt1376, 8)) {
                        Class181.method2607((byte)99);
                    }
                }
                else {
                    Class98_Sub43_Sub1.method1490(-16798);
                    Class32.method316(false);
                }
                if (!Class53_Sub1.method499(2048, Class177.anInt1376) || Class199.method2690(Class177.anInt1376, 8)) {
                    if (Class246_Sub3_Sub3.method3011(-6410, Class177.anInt1376) && !Class199.method2690(Class177.anInt1376, b ^ 0x4C)) {
                        this.method112(b - 58);
                        Class332_Sub1.method3753(74);
                    }
                    else if (Class177.anInt1376 != 12) {
                        if (!za_Sub2.method1683(b ^ 0xFFFFD39B, Class177.anInt1376) || Class199.method2690(Class177.anInt1376, 8)) {
                            if (Class177.anInt1376 == 13) {
                                Class332_Sub1.method3753(120);
                                if (~Class31.anInt300 != 0x2 && ~Class31.anInt300 != 0xFFFFFFFD && Class31.anInt300 != 15) {
                                    Class98_Sub10_Sub1.method1003(false, false);
                                }
                            }
                        }
                        else {
                            Class185.method2629((byte)(-53));
                        }
                    }
                    else {
                        Class332_Sub1.method3753(115);
                    }
                }
                else {
                    this.method112(10);
                    Class64_Sub18.method628(19700);
                    Class332_Sub1.method3753(113);
                }
                Class119_Sub3.method2186(b ^ 0xFFFFFF82, Class265.aHa1974);
                Class167.aClass148_1284.method2421(b ^ 0x191A);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.DA(" + b + ')');
        }
    }
    
    @Override
    final String method94(final int n) {
        try {
            if (n != 0) {
                this.method95(13);
            }
            String s = null;
            try {
                s = "[1)" + Class272.anInt2038 + "," + aa_Sub2.anInt3562 + "," + Class165.anInt1276 + "," + Class98_Sub10_Sub7.anInt5572 + "|";
                if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 != null) {
                    s = s + "2)" + Class43.anInt377 + "," + (Class272.anInt2038 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0]) + "," + (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0] + aa_Sub2.anInt3562) + "|";
                }
                s = s + "3)" + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)124) + "|4)" + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4044.method648((byte)122) + "|5)" + Class146_Sub2.method2391((byte)(-59)) + "|6)" + Class39_Sub1.anInt3593 + "," + Class98_Sub25.anInt4024 + "|";
                s = s + "7)" + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070.method592((byte)127) + "|";
                s = s + "8)" + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method579((byte)126) + "|";
                s = s + "9)" + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064.method668((byte)121) + "|";
                s = s + "10)" + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056.method634((byte)120) + "|";
                s = s + "11)" + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065.method570((byte)127) + "|";
                s = s + "12)" + Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)126) + "|";
                s = s + "13)" + Class292.anInt3359 + "|";
                s = s + "14)" + Class177.anInt1376;
                if (Exception_Sub1.aClass98_Sub35_47 != null) {
                    s = s + "|15)" + Exception_Sub1.aClass98_Sub35_47.anInt4129;
                }
                try {
                    if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)125) == 2) {
                        final Field declaredField = Class.forName("java.lang.ClassLoader").getDeclaredField("nativeLibraries");
                        final Method declaredMethod = Class.forName("java.lang.reflect.AccessibleObject").getDeclaredMethod("setAccessible", Boolean.TYPE);
                        declaredMethod.invoke(declaredField, Boolean.TRUE);
                        final Vector vector = (Vector)declaredField.get(((client.aClass3552 != null) ? client.aClass3552 : (client.aClass3552 = method117("client"))).getClassLoader());
                        for (int n2 = 0; ~vector.size() < ~n2; ++n2) {
                            try {
                                final Object element = vector.elementAt(n2);
                                final Field declaredField2 = element.getClass().getDeclaredField("name");
                                declaredMethod.invoke(declaredField2, Boolean.TRUE);
                                try {
                                    final String s2 = (String)declaredField2.get(element);
                                    if (s2 != null && ~s2.indexOf("sw3d.dll") != 0x0) {
                                        final Field declaredField3 = element.getClass().getDeclaredField("handle");
                                        declaredMethod.invoke(declaredField3, Boolean.TRUE);
                                        s = s + "|16)" + Long.toHexString(declaredField3.getLong(element));
                                        declaredMethod.invoke(declaredField3, Boolean.FALSE);
                                    }
                                }
                                catch (Throwable t) {}
                                declaredMethod.invoke(declaredField2, Boolean.FALSE);
                            }
                            catch (Throwable t2) {}
                        }
                    }
                }
                catch (Throwable t3) {}
                s += "]";
            }
            catch (Throwable t4) {}
            return s;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.H(" + n + ')');
        }
    }
    
    @Override
    final synchronized void method87(final int n) {
        try {
            if (Class76_Sub11.anApplet3799 != null && Class42_Sub3.aCanvas5361 == null && !Class98_Sub43_Sub2.aClass88_5907.aBoolean675) {
                try {
                    final Field declaredField = Class76_Sub11.anApplet3799.getClass().getDeclaredField("canvas");
                    Class42_Sub3.aCanvas5361 = (Canvas)declaredField.get(Class76_Sub11.anApplet3799);
                    declaredField.set(Class76_Sub11.anApplet3799, null);
                    if (Class42_Sub3.aCanvas5361 != null) {
                        return;
                    }
                }
                catch (Exception ex2) {}
            }
            super.method87(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.J(" + n + ')');
        }
    }
    
    public static final void main(final String[] array) {
        try {
            try {
                if (array.length != 6) {
                    Class98_Sub26.method1277(1, "Argument count");
                }
                Class98_Sub46_Sub17.aClass354_6050 = new Class354();
                Class98_Sub46_Sub17.aClass354_6050.anInt3011 = Integer.parseInt(array[0]);
                Class299_Sub2.aClass354_5297 = new Class354();
                Class299_Sub2.aClass354_5297.anInt3011 = Integer.parseInt(array[1]);
                Class250.aClass354_1914 = new Class354();
                Class250.aClass354_1914.anInt3011 = Integer.parseInt(array[2]);
                Class64_Sub29.aClass196_3720 = Class98_Sub43_Sub2.aClass196_5908;
                if (!array[3].equals("live")) {
                    if (!array[3].equals("rc")) {
                        if (!array[3].equals("wip")) {
                            Class98_Sub26.method1277(1, "modewhat");
                        }
                        else {
                            Class73.aClass6_3485 = Class244.aClass6_1861;
                        }
                    }
                    else {
                        Class73.aClass6_3485 = Class1.aClass6_63;
                    }
                }
                else {
                    Class73.aClass6_3485 = Class2.aClass6_68;
                }
                Class374.anInt3159 = Class76_Sub4.method755(false, array[4]);
                if (Class374.anInt3159 == -1) {
                    if (array[4].equals("english")) {
                        Class374.anInt3159 = 0;
                    }
                    else if (!array[4].equals("german")) {
                        Class98_Sub26.method1277(1, "language");
                    }
                    else {
                        Class374.anInt3159 = 1;
                    }
                }
                Class76_Sub7.aBoolean3761 = false;
                Class133.aBoolean3456 = false;
                if (!array[5].equals("game0")) {
                    if (!array[5].equals("game1")) {
                        if (!array[5].equals("game2")) {
                            if (!array[5].equals("game3")) {
                                Class98_Sub26.method1277(1, "game");
                            }
                            else {
                                Class4.aClass279_86 = Class98_Sub46.aClass279_4263;
                            }
                        }
                        else {
                            Class4.aClass279_86 = Class246_Sub3_Sub4_Sub1.aClass279_6240;
                        }
                    }
                    else {
                        Class4.aClass279_86 = Class64_Sub2.aClass279_3643;
                    }
                }
                else {
                    Class4.aClass279_86 = s_Sub1.aClass279_5197;
                }
                Class89.aString716 = "";
                Class98_Sub10_Sub10.aString5593 = null;
                Class233.anInt1746 = 0;
                Class98_Sub46.anInt4260 = 0;
                Class98_Sub10_Sub30.aBoolean5712 = false;
                Class197.aLong1515 = 0L;
                Class64_Sub18.aBoolean3690 = (Class146_Sub3.aBoolean4926 = true);
                Class98_Sub10_Sub7.aString5573 = null;
                Class23.aBoolean220 = false;
                Class98_Sub10_Sub15.anInt5619 = 0;
                Class98_Sub9.anInt3855 = Class4.aClass279_86.anInt2095;
                (Class315.aClient3529 = new client()).method88(false, Class4.aClass279_86.aString2098, 37, 768, Class73.aClass6_3485.method182(-106) + 32, true, 637, 1024);
                Class284.aFrame2168.setLocation(40, 40);
            }
            catch (Exception ex) {
                Class305_Sub1.method3585(ex, -124, null);
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "client.main(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method109(final byte b) {
        try {
            final boolean method2256 = Class98_Sub10_Sub38.aClass135_5765.method2256(4096);
            if (b != 59) {
                client.anInt3550 = -77;
            }
            if (!method2256) {
                this.method106((byte)(-128));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.EA(" + b + ')');
        }
    }
    
    static final void method110(final int n) {
        final int anInt71 = Class2.anInt71;
        final int[] anIntArray2705 = Class319.anIntArray2705;
        for (int n2 = Class237_Sub1.aBoolean5044 ? anInt71 : (anInt71 + Class150.anInt1211), i = 0; i < n2; ++i) {
            Class246_Sub3_Sub4_Sub2 aClass246_Sub3_Sub4_Sub2_Sub1_4187;
            if (i < anInt71) {
                aClass246_Sub3_Sub4_Sub2_Sub1_4187 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[i]];
            }
            else {
                aClass246_Sub3_Sub4_Sub2_Sub1_4187 = ((Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[i - anInt71], -1)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
            }
            if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088 == n) {
                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6358 = 0;
                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 < 0) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371 = false;
                }
                else {
                    final int method3034 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3034(0);
                    if ((method3034 & 0x1) == 0x0) {
                        if ((aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 & 0x1FF) != 0x0 || (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 & 0x1FF) != 0x0) {
                            aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371 = false;
                            continue;
                        }
                    }
                    else if ((aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 & 0x1FF) != 0x100 || (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 & 0x1FF) != 0x100) {
                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371 = false;
                        continue;
                    }
                    if (method3034 == 1) {
                        final int n3 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 >> 9;
                        final int n4 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 >> 9;
                        if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 != Class372.anIntArrayArray3149[n3][n4]) {
                            aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371 = true;
                            continue;
                        }
                        if (Class64_Sub28.anIntArrayArray3719[n3][n4] > 1) {
                            final int[] array = Class64_Sub28.anIntArrayArray3719[n3];
                            final int n5 = n4;
                            --array[n5];
                            aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371 = true;
                            continue;
                        }
                    }
                    else {
                        final int n6 = (method3034 - 1) * 256 + 252;
                        final int n7 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 - n6 >> 9;
                        final int n8 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 - n6 >> 9;
                        final int n9 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 + n6 >> 9;
                        final int n10 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 + n6 >> 9;
                        if (!Class97.method935(n10, n9, n7, 50, n8, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372)) {
                            for (int j = n7; j <= n9; ++j) {
                                for (int k = n8; k <= n10; ++k) {
                                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 == Class372.anIntArrayArray3149[j][k]) {
                                        final int[] array2 = Class64_Sub28.anIntArrayArray3719[j];
                                        final int n11 = k;
                                        --array2[n11];
                                    }
                                }
                            }
                            aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371 = true;
                            continue;
                        }
                    }
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371 = false;
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5089 = Class98_Sub46_Sub2_Sub2.method1538(aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084, 24111);
                    Class222.method2826(aClass246_Sub3_Sub4_Sub2_Sub1_4187, true);
                }
            }
        }
    }
    
    static final boolean method111(final Class293 class293) {
        if (Class15.aBoolean169) {
            if (method116(class293).anInt4284 != 0) {
                return false;
            }
            if (class293.anInt2354 == 0) {
                return false;
            }
        }
        return class293.aBoolean2295;
    }
    
    private final void method112(final int n) {
        try {
            if (~Class177.anInt1376 == 0xFFFFFFF8 && Class64_Sub16.anInt3680 == 0) {
                if (Class98_Sub10_Sub6.anInt5569 > 1) {
                    Class98_Sub36.anInt4161 = Class24.anInt242;
                    --Class98_Sub10_Sub6.anInt5569;
                }
                if (!Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540) {
                    Class46.method435((byte)89);
                }
                for (int n2 = 0; ~n2 > -101; ++n2) {
                    if (!Class98_Sub10_Sub24.method1076(114)) {
                        break;
                    }
                }
            }
            ++Class279.anInt2099;
            Class98_Sub1.method946(-1, n - 119, -1, null);
            Class304.method3563(-1, null, -1, 60);
            Class3.method172(118);
            ++Class24.anInt242;
            for (int n3 = 0; ~Class98_Sub10_Sub20.anInt5640 < ~n3; ++n3) {
                final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = Class163.aClass98_Sub39Array3516[n3].aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187 != null) {
                    final byte aByte1103 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aByte1103;
                    if ((0x1 & aByte1103) != 0x0) {
                        final int method3034 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3034(0);
                        if (~(0x2 & aByte1103) != -1 && ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6434 == -1 && Math.random() * 1000.0 < 10.0) {
                            final int n4 = (int)Math.round(-5.0 + Math.random() * 10.0);
                            final int n5 = (int)Math.round(-5.0 + 10.0 * Math.random());
                            if (~n4 != -1 || ~n5 != -1) {
                                int n6 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6437[0] - -n4;
                                int n7 = n5 + aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6438[0];
                                if (~n6 > -1) {
                                    n6 = 0;
                                }
                                else if (~(-method3034 + (Class165.anInt1276 - 1)) > ~n6) {
                                    n6 = -1 + -method3034 + Class165.anInt1276;
                                }
                                if (n7 < 0) {
                                    n7 = 0;
                                }
                                else if (Class98_Sub10_Sub7.anInt5572 - method3034 - 1 < n7) {
                                    n7 = Class98_Sub10_Sub7.anInt5572 + (-method3034 - 1);
                                }
                                int method3035 = Applet_Sub1.method96(Class167.aClass243Array1281[aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088], method3034, Class76_Sub5.anIntArray3743, 0, 0, n7, method3034, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6438[0], Class117.anIntArray974, true, n6, -1, 48, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6437[0], method3034);
                                if (method3035 > 0) {
                                    if (~method3035 < -10) {
                                        method3035 = 9;
                                    }
                                    for (int n8 = 0; ~n8 > ~method3035; ++n8) {
                                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6437[n8] = Class76_Sub5.anIntArray3743[-1 + (method3035 - n8)];
                                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6438[n8] = Class117.anIntArray974[-1 + method3035 + -n8];
                                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByteArray6443[n8] = 1;
                                    }
                                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6434 = method3035;
                                }
                            }
                        }
                        Class333.method3762((byte)57, true, aClass246_Sub3_Sub4_Sub2_Sub1_4187);
                        final int method3036 = Class98_Sub10_Sub13.method1041(aClass246_Sub3_Sub4_Sub2_Sub1_4187, 0);
                        Class108.method1729(-90, aClass246_Sub3_Sub4_Sub2_Sub1_4187);
                        Class284_Sub1_Sub2.method3370(Class366.anInt3121, n ^ 0x180A, aClass246_Sub3_Sub4_Sub2_Sub1_4187, Class64_Sub23.anInt3708, method3036);
                        Class282.method3334((byte)37, Class64_Sub23.anInt3708, aClass246_Sub3_Sub4_Sub2_Sub1_4187);
                        Class340.method3801(aClass246_Sub3_Sub4_Sub2_Sub1_4187, -28111);
                    }
                }
            }
            if (Class64_Sub16.anInt3680 == 0 && Class21_Sub4.anInt5394 == 0) {
                if (Class98_Sub46_Sub20_Sub2.anInt6319 != 2) {
                    Class183.method2620(0);
                }
                else {
                    Class98_Sub46_Sub3.method1541(123);
                }
                if (Class98_Sub46_Sub10.anInt6020 >> 1205626121 < 14 || Class98_Sub46_Sub10.anInt6020 >> -1191358487 >= Class165.anInt1276 - 14 || Class134.anInt3461 >> -1312364695 < 14 || ~(Class134.anInt3461 >> 242670249) <= ~(Class98_Sub10_Sub7.anInt5572 - 14)) {
                    Class61.method537((byte)66);
                }
            }
            while (true) {
                final Class98_Sub21 class98_Sub21 = (Class98_Sub21)Class181.aClass148_1430.method2421(6494);
                if (class98_Sub21 == null) {
                    break;
                }
                final Class293 aClass293_3986 = class98_Sub21.aClass293_3986;
                if (aClass293_3986.anInt2300 >= 0) {
                    final Class293 method3037 = Class159.method2509(aClass293_3986.anInt2234, -9820);
                    if (method3037 == null || method3037.aClass293Array2339 == null || method3037.aClass293Array2339.length <= aClass293_3986.anInt2300) {
                        continue;
                    }
                    if (method3037.aClass293Array2339[aClass293_3986.anInt2300] != aClass293_3986) {
                        continue;
                    }
                }
                Class247.method3144(class98_Sub21);
            }
            while (true) {
                final Class98_Sub21 class98_Sub22 = (Class98_Sub21)Class98_Sub46_Sub10.aClass148_6018.method2421(6494);
                if (class98_Sub22 == null) {
                    break;
                }
                final Class293 aClass293_3987 = class98_Sub22.aClass293_3986;
                if (~aClass293_3987.anInt2300 <= -1) {
                    final Class293 method3038 = Class159.method2509(aClass293_3987.anInt2234, -9820);
                    if (method3038 == null || method3038.aClass293Array2339 == null || ~aClass293_3987.anInt2300 <= ~method3038.aClass293Array2339.length) {
                        continue;
                    }
                    if (method3038.aClass293Array2339[aClass293_3987.anInt2300] != aClass293_3987) {
                        continue;
                    }
                }
                Class247.method3144(class98_Sub22);
            }
            while (true) {
                final Class98_Sub21 class98_Sub23 = (Class98_Sub21)Class151_Sub3.aClass148_4977.method2421(6494);
                if (class98_Sub23 == null) {
                    break;
                }
                final Class293 aClass293_3988 = class98_Sub23.aClass293_3986;
                if (aClass293_3988.anInt2300 >= 0) {
                    final Class293 method3039 = Class159.method2509(aClass293_3988.anInt2234, -9820);
                    if (method3039 == null || method3039.aClass293Array2339 == null || method3039.aClass293Array2339.length <= aClass293_3988.anInt2300) {
                        continue;
                    }
                    if (aClass293_3988 != method3039.aClass293Array2339[aClass293_3988.anInt2300]) {
                        continue;
                    }
                }
                Class247.method3144(class98_Sub23);
            }
            if (Class255.aClass293_3208 != null) {
                Class111_Sub3.method2118(19653);
            }
            if (~(Class215.anInt1614 % 1500) == -1) {
                Class4.method174((byte)99);
            }
            if (Class177.anInt1376 == 7 && ~Class64_Sub16.anInt3680 == -1) {
                Class204.method2709((byte)49);
            }
            Class368.method3951((byte)(-101));
            if (Class66.aBoolean507 && ~r_Sub1.aLong6322 > ~(-60000L + Class343.method3819(-47))) {
                Class23.method283((byte)98);
            }
            if (n != 10) {
                client.anInt3550 = -112;
            }
            for (Class246_Sub4_Sub1 class246_Sub4_Sub1 = (Class246_Sub4_Sub1)Class119.aClass218_986.method2803((byte)15); class246_Sub4_Sub1 != null; class246_Sub4_Sub1 = (Class246_Sub4_Sub1)Class119.aClass218_986.method2809(false)) {
                if (-5L + Class343.method3819(-47) / 1000L > class246_Sub4_Sub1.anInt6172) {
                    if (class246_Sub4_Sub1.aShort6167 > 0) {
                        Class98_Sub45.method1521((byte)(-97), 5, class246_Sub4_Sub1.aString6168 + Class309.aClass309_2605.method3615(Class374.anInt3159, (byte)25), 0, "", "", "");
                    }
                    if (class246_Sub4_Sub1.aShort6167 == 0) {
                        Class98_Sub45.method1521((byte)109, 5, class246_Sub4_Sub1.aString6168 + Class309.aClass309_2606.method3615(Class374.anInt3159, (byte)25), 0, "", "", "");
                    }
                    class246_Sub4_Sub1.method2965((byte)(-97));
                }
            }
            if (Class177.anInt1376 == 7 && ~Class64_Sub16.anInt3680 == -1) {
                if (aa_Sub1.aClass123_3561 == null) {
                    Class98_Sub10_Sub1.method1003(false, false);
                }
                else {
                    ++Class196.anInt1511;
                    if (~Class196.anInt1511 < -51) {
                        ++Class76_Sub5.anInt3746;
                        Class98_Sub10_Sub29.sendPacket(false, Class246_Sub3_Sub4.method3023(n ^ 0x10E, Class98_Sub40.aClass171_4193, Class331.aClass117_2811));
                    }
                    try {
                        Class95.method920((byte)124);
                    }
                    catch (IOException ex2) {
                        Class98_Sub10_Sub1.method1003(false, false);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.U(" + n + ')');
        }
    }
    
    static final void method113() {
        Class98_Sub19.anInt3955 = 0;
        for (int i = 0; i < Class150.anInt1211; ++i) {
            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = ((Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[i], -1)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
            if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371 && aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3035(28213) != -1) {
                final int n = (aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3034(0) - 1) * 256 + 252;
                final Class246_Sub3_Sub4_Sub2 method2874 = Class231.method2874(aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 - n >> 9, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 - n >> 9, 64);
                if (method2874 != null) {
                    int anInt6369 = method2874.anInt6369;
                    if (method2874 instanceof Class246_Sub3_Sub4_Sub2_Sub1) {
                        anInt6369 += 2048;
                    }
                    if (method2874.anInt6358 == 0 && method2874.method3035(28213) != -1) {
                        Class151_Sub1.anIntArray4969[Class98_Sub19.anInt3955] = anInt6369;
                        Class119_Sub2.anIntArray4727[Class98_Sub19.anInt3955] = anInt6369;
                        ++Class98_Sub19.anInt3955;
                        final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2 = method2874;
                        ++class246_Sub3_Sub4_Sub2.anInt6358;
                    }
                    Class151_Sub1.anIntArray4969[Class98_Sub19.anInt3955] = anInt6369;
                    Class119_Sub2.anIntArray4727[Class98_Sub19.anInt3955] = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6369 + 2048;
                    ++Class98_Sub19.anInt3955;
                    final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub3 = method2874;
                    ++class246_Sub3_Sub4_Sub3.anInt6358;
                }
            }
        }
        Class221.method2823((byte)(-125), Class151_Sub1.anIntArray4969, Class119_Sub2.anIntArray4727, 0, Class98_Sub19.anInt3955 - 1);
    }
    
    static final void method114() {
        final int anInt71 = Class2.anInt71;
        final int[] anIntArray2705 = Class319.anIntArray2705;
        final int method651 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub24_4047.method651((byte)127);
        final boolean b = (method651 == 1 && anInt71 > 200) || (method651 == 0 && anInt71 > 50);
        for (int i = 0; i < anInt71; ++i) {
            final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[i]];
            if (!class246_Sub3_Sub4_Sub2_Sub2.method3055((byte)106)) {
                class246_Sub3_Sub4_Sub2_Sub2.anInt6372 = -1;
            }
            else if (class246_Sub3_Sub4_Sub2_Sub2.aBoolean6523) {
                class246_Sub3_Sub4_Sub2_Sub2.anInt6372 = -1;
            }
            else {
                class246_Sub3_Sub4_Sub2_Sub2.method3022(-8675);
                if (class246_Sub3_Sub4_Sub2_Sub2.aShort6158 < 0 || class246_Sub3_Sub4_Sub2_Sub2.aShort6157 < 0 || class246_Sub3_Sub4_Sub2_Sub2.aShort6160 >= Class165.anInt1276 || class246_Sub3_Sub4_Sub2_Sub2.aShort6159 >= Class98_Sub10_Sub7.anInt5572) {
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6372 = -1;
                }
                else {
                    class246_Sub3_Sub4_Sub2_Sub2.aBoolean6520 = (class246_Sub3_Sub4_Sub2_Sub2.aBoolean6359 && b);
                    if (class246_Sub3_Sub4_Sub2_Sub2 == Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660) {
                        class246_Sub3_Sub4_Sub2_Sub2.anInt6372 = Integer.MAX_VALUE;
                    }
                    else {
                        int n = 0;
                        if (!class246_Sub3_Sub4_Sub2_Sub2.aBoolean6371) {
                            ++n;
                        }
                        if (class246_Sub3_Sub4_Sub2_Sub2.anInt6418 > Class215.anInt1614) {
                            n += 2;
                        }
                        int n2 = n + (5 - class246_Sub3_Sub4_Sub2_Sub2.method3034(0) << 2);
                        if (class246_Sub3_Sub4_Sub2_Sub2.aBoolean6534) {
                            n2 += 512;
                        }
                        else {
                            if (Class103.anInt896 == 0) {
                                n2 += 32;
                            }
                            else {
                                n2 += 128;
                            }
                            n2 += 256;
                        }
                        class246_Sub3_Sub4_Sub2_Sub2.anInt6372 = n2 + 1;
                    }
                }
            }
        }
        for (int j = 0; j < Class150.anInt1211; ++j) {
            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = ((Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[j], -1)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
            if (!aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3052((byte)106) || !aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.method2296(Class75.aClass140_584, (byte)(-118))) {
                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 = -1;
            }
            else {
                aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3022(-8675);
                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aShort6158 < 0 || aClass246_Sub3_Sub4_Sub2_Sub1_4187.aShort6157 < 0 || aClass246_Sub3_Sub4_Sub2_Sub1_4187.aShort6160 >= Class165.anInt1276 || aClass246_Sub3_Sub4_Sub2_Sub1_4187.aShort6159 >= Class98_Sub10_Sub7.anInt5572) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 = -1;
                }
                else {
                    int n3 = 0;
                    if (!aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371) {
                        ++n3;
                    }
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6418 > Class215.anInt1614) {
                        n3 += 2;
                    }
                    int n4 = n3 + (5 - aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3034(0) << 2);
                    if (Class103.anInt896 == 0) {
                        if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aBoolean1153) {
                            n4 += 64;
                        }
                        else {
                            n4 += 128;
                        }
                    }
                    else if (Class103.anInt896 == 1) {
                        if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aBoolean1153) {
                            n4 += 32;
                        }
                        else {
                            n4 += 64;
                        }
                    }
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aBoolean1106) {
                        n4 += 1024;
                    }
                    else if (!aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aBoolean1149) {
                        n4 += 256;
                    }
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 = n4 + 1;
                }
            }
        }
        for (int k = 0; k < Class104.aClass36Array903.length; ++k) {
            final Class36 class36 = Class104.aClass36Array903[k];
            if (class36 != null) {
                if (class36.anInt346 == 1) {
                    final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(class36.anInt345, -1);
                    if (class98_Sub39 != null) {
                        final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4188 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                        if (aClass246_Sub3_Sub4_Sub2_Sub1_4188.anInt6372 >= 0) {
                            final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub3 = aClass246_Sub3_Sub4_Sub2_Sub1_4188;
                            class246_Sub3_Sub4_Sub2_Sub3.anInt6372 += 2048;
                        }
                    }
                }
                else if (class36.anInt346 == 10) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub4 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[class36.anInt345];
                    if (class246_Sub3_Sub4_Sub2_Sub4 != null && class246_Sub3_Sub4_Sub2_Sub4 != Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 && class246_Sub3_Sub4_Sub2_Sub4.anInt6372 >= 0) {
                        final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub5 = class246_Sub3_Sub4_Sub2_Sub4;
                        class246_Sub3_Sub4_Sub2_Sub5.anInt6372 += 2048;
                    }
                }
            }
        }
    }
    
    @Override
    final void method95(final int n) {
        try {
            if (Class23.aBoolean220) {
                Class292.anInt3359 = 64;
            }
            if (n == -13395) {
                final Frame frame = new Frame("Jagex");
                frame.pack();
                frame.dispose();
                Class98_Sub31_Sub2.method1336((byte)(-106));
                Class168.aClass253_1290 = new Class253(Class98_Sub43_Sub2.aClass88_5907);
                Class98_Sub10_Sub38.aClass135_5765 = new Class135();
                Class157.method2502(new int[] { 20, 260 }, new int[] { 1000, 100 }, 0);
                if (Class64_Sub29.aClass196_3720 != Class43.aClass196_375) {
                    Class76.aByteArrayArray590 = new byte[50][];
                }
                Class98_Sub9.aClass98_Sub27_3856 = Class98_Sub5_Sub3.method972(-122);
                if (Class43.aClass196_375 != Class64_Sub29.aClass196_3720) {
                    if (!Class133.method2238(true, Class64_Sub29.aClass196_3720)) {
                        if (Class98_Sub43_Sub2.aClass196_5908 == Class64_Sub29.aClass196_3720) {
                            Class98_Sub46_Sub17.aClass354_6050.aString3016 = RunClient.mainurl;
                            Class299_Sub2.aClass354_5297.aString3016 = RunClient.mainurl;
                            Class250.aClass354_1914.aString3016 = RunClient.mainurl;
                            Class98_Sub46_Sub17.aClass354_6050.anInt3015 = Class98_Sub46_Sub17.aClass354_6050.anInt3011 + 40000;
                            Class299_Sub2.aClass354_5297.anInt3015 = 40000 + Class299_Sub2.aClass354_5297.anInt3011;
                            Class250.aClass354_1914.anInt3015 = Class250.aClass354_1914.anInt3011 + 40000;
                            Class98_Sub46_Sub17.aClass354_6050.anInt3012 = Class98_Sub46_Sub17.aClass354_6050.anInt3011 + 50000;
                            Class299_Sub2.aClass354_5297.anInt3012 = Class299_Sub2.aClass354_5297.anInt3011 + 50000;
                            Class250.aClass354_1914.anInt3012 = Class250.aClass354_1914.anInt3011 + 50000;
                        }
                    }
                    else {
                        Class98_Sub46_Sub17.aClass354_6050.aString3016 = this.getCodeBase().getHost();
                        Class98_Sub46_Sub17.aClass354_6050.anInt3015 = 40000 + Class98_Sub46_Sub17.aClass354_6050.anInt3011;
                        Class299_Sub2.aClass354_5297.anInt3015 = Class299_Sub2.aClass354_5297.anInt3011 + 40000;
                        Class250.aClass354_1914.anInt3015 = Class250.aClass354_1914.anInt3011 + 40000;
                        Class98_Sub46_Sub17.aClass354_6050.anInt3012 = Class98_Sub46_Sub17.aClass354_6050.anInt3011 + 50000;
                        Class299_Sub2.aClass354_5297.anInt3012 = Class299_Sub2.aClass354_5297.anInt3011 + 50000;
                        Class250.aClass354_1914.anInt3012 = 50000 - -Class250.aClass354_1914.anInt3011;
                    }
                }
                else {
                    Class98_Sub46_Sub17.aClass354_6050.aString3016 = this.getCodeBase().getHost();
                }
                Class98_Sub46_Sub10.aClass354_6011 = Class98_Sub46_Sub17.aClass354_6050;
                if (Class64_Sub2.aClass279_3643 == Class4.aClass279_86) {
                    Class299_Sub2.anInt5298 = 16777215;
                    Class98_Sub10_Sub11.aShortArrayArray5597 = Class98_Sub10_Sub24.aShortArrayArray5669;
                    Class109.aBoolean934 = true;
                    Class189.anInt1455 = 0;
                    Class61.aShortArrayArrayArray478 = Class119.aShortArrayArrayArray983;
                }
                else if (Class98_Sub46.aClass279_4263 == Class4.aClass279_86) {
                    Class61.aShortArrayArrayArray478 = Class180.aShortArrayArrayArray3397;
                    Class98_Sub10_Sub11.aShortArrayArray5597 = Class48_Sub1_Sub1.aShortArrayArray5503;
                }
                else {
                    Class98_Sub10_Sub11.aShortArrayArray5597 = Class98_Sub21.aShortArrayArray3987;
                    Class61.aShortArrayArrayArray478 = Class98_Sub46_Sub2_Sub2.aShortArrayArrayArray6299;
                }
                if (Class4.aClass279_86 == s_Sub1.aClass279_5197) {
                    Class246_Sub3_Sub5_Sub2.aBoolean6272 = false;
                }
                Class246.aShortArray1869 = (Class372.aShortArray3153 = (Class265.aShortArray1977 = (Class338.aShortArray2833 = new short[256])));
                try {
                    Class8.aClipboard113 = Class315.aClient3529.getToolkit().getSystemClipboard();
                }
                catch (Exception ex2) {}
                Class219.aClass77_1641 = Class368.method3950((byte)10, Class42_Sub3.aCanvas5361);
                Class2.aClass299_73 = Class151_Sub3.method2457(Class42_Sub3.aCanvas5361, true, -16777216);
                try {
                    if (Class98_Sub43_Sub2.aClass88_5907.aClass356_702 != null) {
                        Class98_Sub37.aClass225_4178 = new Class225(Class98_Sub43_Sub2.aClass88_5907.aClass356_702, 5200, 0);
                        for (int n2 = 0; ~n2 > -38; ++n2) {
                            Class55.aClass225Array444[n2] = new Class225(Class98_Sub43_Sub2.aClass88_5907.aClass356Array695[n2], 6000, 0);
                        }
                        Class252.aClass225_1926 = new Class225(Class98_Sub43_Sub2.aClass88_5907.aClass356_680, 6000, 0);
                        Class29.aClass17_298 = new Class17(255, Class98_Sub37.aClass225_4178, Class252.aClass225_1926, 500000);
                        Class195.aClass225_1502 = new Class225(Class98_Sub43_Sub2.aClass88_5907.aClass356_685, 24, 0);
                        Class98_Sub43_Sub2.aClass88_5907.aClass356_685 = null;
                        Class98_Sub43_Sub2.aClass88_5907.aClass356_702 = null;
                        Class98_Sub43_Sub2.aClass88_5907.aClass356Array695 = null;
                        Class98_Sub43_Sub2.aClass88_5907.aClass356_680 = null;
                    }
                }
                catch (IOException ex3) {
                    Class98_Sub37.aClass225_4178 = null;
                    Class252.aClass225_1926 = null;
                    Class29.aClass17_298 = null;
                    Class195.aClass225_1502 = null;
                }
                if (Class43.aClass196_375 != Class64_Sub29.aClass196_3720) {
                    Class91.aBoolean725 = true;
                }
                Class265.aString1978 = Class309.aClass309_2598.method3615(Class374.anInt3159, (byte)25);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.D(" + n + ')');
        }
    }
    
    static final void method115() {
        final int anInt71 = Class2.anInt71;
        final int[] anIntArray2705 = Class319.anIntArray2705;
        for (int n = Class237_Sub1.aBoolean5044 ? anInt71 : (anInt71 + Class150.anInt1211), i = 0; i < n; ++i) {
            Class246_Sub3_Sub4_Sub2 aClass246_Sub3_Sub4_Sub2_Sub1_4187;
            if (i < anInt71) {
                aClass246_Sub3_Sub4_Sub2_Sub1_4187 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[i]];
            }
            else {
                aClass246_Sub3_Sub4_Sub2_Sub1_4187 = ((Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[i - anInt71], -1)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
            }
            if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 >= 0) {
                if ((aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3034(0) & 0x1) == 0x0) {
                    if ((aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 & 0x1FF) == 0x0 && (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 & 0x1FF) == 0x0) {
                        continue;
                    }
                }
                else if ((aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 & 0x1FF) == 0x100 && (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 & 0x1FF) == 0x100) {
                    continue;
                }
                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5089 = Class98_Sub46_Sub2_Sub2.method1538(aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084, 24111);
                Class222.method2826(aClass246_Sub3_Sub4_Sub2_Sub1_4187, true);
            }
        }
    }
    
    @Override
    public final void init() {
        try {
            if (this.method89(31)) {
                Class98_Sub46_Sub17.aClass354_6050 = new Class354();
                Class98_Sub46_Sub17.aClass354_6050.anInt3011 = Integer.parseInt(this.getParameter("worldid"));
                Class299_Sub2.aClass354_5297 = new Class354();
                Class299_Sub2.aClass354_5297.anInt3011 = Integer.parseInt(this.getParameter("lobbyid"));
                Class299_Sub2.aClass354_5297.aString3016 = this.getParameter("lobbyaddress");
                Class250.aClass354_1914 = new Class354();
                Class250.aClass354_1914.anInt3011 = Integer.parseInt(this.getParameter("demoid"));
                Class250.aClass354_1914.aString3016 = this.getParameter("demoaddress");
                Class64_Sub29.aClass196_3720 = Class246_Sub3_Sub1_Sub1.method2997(103, Integer.parseInt(this.getParameter("modewhere")));
                if (Class64_Sub29.aClass196_3720 != Class98_Sub43_Sub2.aClass196_5908) {
                    if (!Class133.method2238(true, Class64_Sub29.aClass196_3720) && Class64_Sub29.aClass196_3720 != Class43.aClass196_375) {
                        Class64_Sub29.aClass196_3720 = Class43.aClass196_375;
                    }
                }
                else {
                    Class64_Sub29.aClass196_3720 = Class207.aClass196_1578;
                }
                Class73.aClass6_3485 = Class246_Sub3_Sub3_Sub2.method3020(Integer.parseInt(this.getParameter("modewhat")), 108);
                if (Class244.aClass6_1861 != Class73.aClass6_3485 && Class73.aClass6_3485 != Class1.aClass6_63 && Class2.aClass6_68 != Class73.aClass6_3485) {
                    Class73.aClass6_3485 = Class2.aClass6_68;
                }
                try {
                    Class374.anInt3159 = Integer.parseInt(this.getParameter("lang"));
                }
                catch (Exception ex2) {
                    Class374.anInt3159 = 0;
                }
                final String parameter = this.getParameter("objecttag");
                if (parameter != null && parameter.equals("1")) {
                    Class133.aBoolean3456 = true;
                }
                else {
                    Class133.aBoolean3456 = false;
                }
                final String parameter2 = this.getParameter("js");
                if (parameter2 == null || !parameter2.equals("1")) {
                    Class76_Sub7.aBoolean3761 = false;
                }
                else {
                    Class76_Sub7.aBoolean3761 = true;
                }
                final String parameter3 = this.getParameter("advert");
                if (parameter3 == null || !parameter3.equals("1")) {
                    Class172.aBoolean1321 = false;
                }
                else {
                    Class172.aBoolean1321 = true;
                }
                final String parameter4 = this.getParameter("game");
                if (parameter4 != null) {
                    if (parameter4.equals("0")) {
                        Class4.aClass279_86 = s_Sub1.aClass279_5197;
                    }
                    else if (!parameter4.equals("1")) {
                        if (!parameter4.equals("2")) {
                            if (parameter4.equals("3")) {
                                Class4.aClass279_86 = Class98_Sub46.aClass279_4263;
                            }
                        }
                        else {
                            Class4.aClass279_86 = Class246_Sub3_Sub4_Sub1.aClass279_6240;
                        }
                    }
                    else {
                        Class4.aClass279_86 = Class64_Sub2.aClass279_3643;
                    }
                }
                try {
                    Class98_Sub10_Sub15.anInt5619 = Integer.parseInt(this.getParameter("affid"));
                }
                catch (Exception ex3) {
                    Class98_Sub10_Sub15.anInt5619 = 0;
                }
                Class5.aString3440 = this.getParameter("quiturl");
                Class89.aString716 = this.getParameter("settings");
                if (Class89.aString716 == null) {
                    Class89.aString716 = "";
                }
                ha.aBoolean940 = "1".equals(this.getParameter("under"));
                final String parameter5 = this.getParameter("country");
                if (parameter5 != null) {
                    try {
                        Class233.anInt1746 = Integer.parseInt(parameter5);
                    }
                    catch (Exception ex4) {
                        Class233.anInt1746 = 0;
                    }
                }
                Class98_Sub9.anInt3855 = Integer.parseInt(this.getParameter("colourid"));
                if (Class98_Sub9.anInt3855 < 0 || ~Class301.aColorArray2508.length >= ~Class98_Sub9.anInt3855) {
                    Class98_Sub9.anInt3855 = 0;
                }
                if (Integer.parseInt(this.getParameter("sitesettings_member")) == 1) {
                    Class64_Sub18.aBoolean3690 = (Class146_Sub3.aBoolean4926 = true);
                }
                final String parameter6 = this.getParameter("frombilling");
                if (parameter6 != null && parameter6.equals("true")) {
                    Class98_Sub10_Sub30.aBoolean5712 = true;
                }
                Class98_Sub10_Sub10.aString5593 = this.getParameter("sskey");
                if (Class98_Sub10_Sub10.aString5593 != null && ~Class98_Sub10_Sub10.aString5593.length() > -3) {
                    Class98_Sub10_Sub10.aString5593 = null;
                }
                final String parameter7 = this.getParameter("force64mb");
                if (parameter7 != null && parameter7.equals("true")) {
                    Class23.aBoolean220 = true;
                }
                final String parameter8 = this.getParameter("worldflags");
                if (parameter8 != null) {
                    try {
                        Class98_Sub46.anInt4260 = Integer.parseInt(parameter8);
                    }
                    catch (Exception ex5) {}
                }
                final String parameter9 = this.getParameter("userFlow");
                if (parameter9 != null) {
                    try {
                        Class197.aLong1515 = Long.parseLong(parameter9);
                    }
                    catch (NumberFormatException ex6) {}
                }
                Class98_Sub10_Sub7.aString5573 = this.getParameter("additionalInfo");
                if (Class98_Sub10_Sub7.aString5573 != null && Class98_Sub10_Sub7.aString5573.length() > 50) {
                    Class98_Sub10_Sub7.aString5573 = null;
                }
                Class315.aClient3529 = this;
                if (s_Sub1.aClass279_5197 != Class4.aClass279_86) {
                    if (Class4.aClass279_86 == Class64_Sub2.aClass279_3643) {
                        Class246_Sub2.anInt5072 = 480;
                        Class98_Sub17_Sub1.anInt5782 = 640;
                    }
                }
                else {
                    Class246_Sub2.anInt5072 = 503;
                    Class98_Sub17_Sub1.anInt5782 = 765;
                }
                this.method84(637, Class246_Sub2.anInt5072, 4, 37, Class4.aClass279_86.aString2098, 32 + Class73.aClass6_3485.method182(-124), Class98_Sub17_Sub1.anInt5782);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "client.init()");
        }
    }
    
    static final Class98_Sub49 method116(final Class293 class293) {
        final Class98_Sub49 class98_Sub49 = (Class98_Sub49)Class168.aClass377_1287.method3990((class293.anInt2248 << 32) + class293.anInt2300, -1);
        if (class98_Sub49 != null) {
            return class98_Sub49;
        }
        return class293.aClass98_Sub49_2348;
    }
    
    static Class method117(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        client.aLong3547 = 0L;
    }
}
