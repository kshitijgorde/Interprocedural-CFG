import java.awt.Component;
import java.awt.Insets;
import javax.accessibility.Accessible;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub31_Sub2 extends Class98_Sub31
{
    private int[] anIntArray5819;
    private Class377 aClass377_5820;
    private int[] anIntArray5821;
    static int anInt5822;
    private Class173 aClass173_5823;
    static int anInt5824;
    private Class98_Sub16[][] aClass98_Sub16ArrayArray5825;
    private int[] anIntArray5826;
    private int anInt5827;
    private Class98_Sub16[][] aClass98_Sub16ArrayArray5828;
    private int[] anIntArray5829;
    private int[] anIntArray5830;
    private int[] anIntArray5831;
    static int[] anIntArray5832;
    private int[] anIntArray5833;
    private int[] anIntArray5834;
    private int[] anIntArray5835;
    private int anInt5836;
    private int[] anIntArray5837;
    static IncomingOpcode aClass58_5838;
    private int[] anIntArray5839;
    int[] anIntArray5840;
    private int[] anIntArray5841;
    int[] anIntArray5842;
    int[] anIntArray5843;
    private int[] anIntArray5844;
    private int anInt5845;
    private Class98_Sub31_Sub1 aClass98_Sub31_Sub1_5846;
    private long aLong5847;
    private boolean aBoolean5848;
    private long aLong5849;
    private int anInt5850;
    private Class98_Sub7 aClass98_Sub7_5851;
    private boolean aBoolean5852;
    private int anInt5853;
    
    private final void method1331(final boolean b, final int n) {
        try {
            if (!b) {
                this.method1335(-1, 0);
            }
            else {
                this.method1355(false, -1);
            }
            this.method1342(0, -1);
            for (int n2 = 0; ~n2 > -17; ++n2) {
                this.anIntArray5834[n2] = this.anIntArray5830[n2];
            }
            for (int n3 = n; ~n3 > -17; ++n3) {
                this.anIntArray5841[n3] = Class202.method2702(-128, this.anIntArray5830[n3]);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.QA(" + b + ',' + n + ')');
        }
    }
    
    final synchronized void method1332(final boolean b, final Class98_Sub7 class98_Sub7, final byte b2) {
        try {
            this.method1343(class98_Sub7, true, b, b2 ^ 0xFFFFFFAD);
            if (b2 != -4) {
                this.anIntArray5837 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.Q(" + b + ',' + ((class98_Sub7 != null) ? "{...}" : "null") + ',' + b2 + ')');
        }
    }
    
    public static void method1333(final int n) {
        try {
            Class98_Sub31_Sub2.anIntArray5832 = null;
            if (n != 0) {
                Class98_Sub31_Sub2.anInt5822 = -88;
            }
            Class98_Sub31_Sub2.aClass58_5838 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.E(" + n + ')');
        }
    }
    
    private final int method1334(final int n, final Class98_Sub16 class98_Sub16) {
        try {
            if (this.anIntArray5844[class98_Sub16.anInt3940] == 0) {
                return 0;
            }
            final Class89 aClass89_3935 = class98_Sub16.aClass89_3935;
            final int n2 = this.anIntArray5831[class98_Sub16.anInt3940] * this.anIntArray5826[class98_Sub16.anInt3940] + 4096 >> -2144255443;
            int n3 = 128 + (this.anInt5836 * (16384 + class98_Sub16.anInt3925 * (16384 + n2 * n2 >> 1838107727) >> 755612303) + 128 >> 1177580712) * this.anIntArray5844[class98_Sub16.anInt3940] >> -273066168;
            if (~aClass89_3935.anInt707 < -1) {
                n3 = (int)(0.5 + Math.pow(0.5, class98_Sub16.anInt3932 * 1.953125E-5 * aClass89_3935.anInt707) * n3);
            }
            if (aClass89_3935.aByteArray714 != null) {
                final int anInt3920 = class98_Sub16.anInt3920;
                int n4 = aClass89_3935.aByteArray714[1 + class98_Sub16.anInt3937];
                if (aClass89_3935.aByteArray714.length - 2 > class98_Sub16.anInt3937) {
                    final int n5 = 0xFF00 & aClass89_3935.aByteArray714[class98_Sub16.anInt3937] << 814996168;
                    n4 += (aClass89_3935.aByteArray714[3 + class98_Sub16.anInt3937] - n4) * (anInt3920 - n5) / ((aClass89_3935.aByteArray714[2 + class98_Sub16.anInt3937] << -603738584 & 0xFF00) - n5);
                }
                n3 = n4 * n3 + 32 >> 559368486;
            }
            if (class98_Sub16.anInt3930 > 0 && aClass89_3935.aByteArray713 != null) {
                final int anInt3921 = class98_Sub16.anInt3930;
                int n6 = aClass89_3935.aByteArray713[1 + class98_Sub16.anInt3922];
                if (class98_Sub16.anInt3922 < -2 + aClass89_3935.aByteArray713.length) {
                    final int n7 = aClass89_3935.aByteArray713[class98_Sub16.anInt3922] << -624556984 & 0xFF00;
                    n6 += (-n7 + anInt3921) * (-n6 + aClass89_3935.aByteArray713[class98_Sub16.anInt3922 + 3]) / (((0xFF & aClass89_3935.aByteArray713[2 + class98_Sub16.anInt3922]) << -1122491128) + -n7);
                }
                n3 = 32 + n6 * n3 >> 139934694;
            }
            if (n != 255) {
                return -41;
            }
            return n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.DA(" + n + ',' + ((class98_Sub16 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method1335(final int n, final int n2) {
        try {
            if (n2 != 0) {
                this.method1359((byte)(-87), -124L);
            }
            for (Class98_Sub16 class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2418(32); class98_Sub16 != null; class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2417(104)) {
                if ((n < 0 || class98_Sub16.anInt3940 == n) && class98_Sub16.anInt3930 < 0) {
                    this.aClass98_Sub16ArrayArray5825[class98_Sub16.anInt3940][class98_Sub16.anInt3936] = null;
                    class98_Sub16.anInt3930 = 0;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.I(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1336(final byte b) {
        try {
            synchronized (Class315.aClient3529) {
                if (Class98_Sub18.aFrame3950 == null) {
                    Accessible accessible;
                    if (Class284.aFrame2168 == null) {
                        if (Class76_Sub11.anApplet3799 != null) {
                            accessible = Class76_Sub11.anApplet3799;
                        }
                        else {
                            accessible = Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271;
                        }
                    }
                    else {
                        accessible = Class284.aFrame2168;
                    }
                    Class188.anInt1453 = ((Component)accessible).getSize().width;
                    Class123_Sub1.anInt4744 = ((Component)accessible).getSize().height;
                    if (Class284.aFrame2168 == accessible) {
                        final Insets insets = Class284.aFrame2168.getInsets();
                        Class123_Sub1.anInt4744 -= insets.bottom + insets.top;
                        Class188.anInt1453 -= insets.left - -insets.right;
                    }
                    if (Class146_Sub2.method2391((byte)118) == 1) {
                        Class191.anInt1479 = 0;
                        Class39_Sub1.anInt3593 = Class98_Sub17_Sub1.anInt5782;
                        Class98_Sub25.anInt4024 = Class246_Sub2.anInt5072;
                        Class98_Sub46_Sub10.anInt6022 = (Class188.anInt1453 + -Class98_Sub17_Sub1.anInt5782) / 2;
                    }
                    else {
                        Class299.method3513(false);
                    }
                    if (Class43.aClass196_375 == Class64_Sub29.aClass196_3720 || ~Class39_Sub1.anInt3593 <= -1025 || Class98_Sub25.anInt4024 < 768) {}
                    Class42_Sub3.aCanvas5361.setSize(Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                    if (Class265.aHa1974 != null) {
                        if (!za_Sub2.aBoolean6079) {
                            Class265.aHa1974.method1782(Class42_Sub3.aCanvas5361, Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                        }
                        else {
                            Class293.method3471(Class42_Sub3.aCanvas5361, 0);
                        }
                    }
                    if (b > -41) {
                        method1333(94);
                    }
                    if (accessible == Class284.aFrame2168) {
                        final Insets insets2 = Class284.aFrame2168.getInsets();
                        Class42_Sub3.aCanvas5361.setLocation(insets2.left + Class98_Sub46_Sub10.anInt6022, Class191.anInt1479 + insets2.top);
                    }
                    else {
                        Class42_Sub3.aCanvas5361.setLocation(Class98_Sub46_Sub10.anInt6022, Class191.anInt1479);
                    }
                    if (~Class15.anInt185 != 0x0) {
                        Class40.method359(-1, true);
                    }
                    Class263.method3216(14059);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.J(" + b + ')');
        }
    }
    
    private final void method1337(final int n, final byte b) {
        try {
            final int n2 = 0xF0 & n;
            if (n2 == 128) {
                this.method1353(0x7F & n >> 374811400, n & 0xF, (0x7F5CE4 & n) >> 907280592, 42);
            }
            else if (n2 == 144) {
                final int n3 = n & 0xF;
                final int n4 = n >> 1952651336 & 0x7F;
                final int n5 = n >> 1968912016 & 0x7F;
                if (~n5 >= -1) {
                    this.method1353(n4, n3, 64, 19);
                }
                else {
                    this.method1346(98, n4, n5, n3);
                }
            }
            else if (~n2 == 0xFFFFFF5F) {
                this.method1338(n >> 1959226056 & 0x7F, 0xF & n, true, (0x7F67E2 & n) >> -805171600);
            }
            else if (~n2 == 0xFFFFFF4F) {
                final int n6 = 0xF & n;
                final int n7 = (0x7F65 & n) >> -1760183224;
                final int n8 = 0x7F & n >> -428057136;
                if (~n7 == -1) {
                    this.anIntArray5841[n6] = (n8 << -243849874) + Class202.method2702(this.anIntArray5841[n6], -2080769);
                }
                if (~n7 == 0xFFFFFFDF) {
                    this.anIntArray5841[n6] = (n8 << 1679403687) + Class202.method2702(this.anIntArray5841[n6], -16257);
                }
                if (~n7 == 0xFFFFFFFE) {
                    this.anIntArray5821[n6] = Class202.method2702(this.anIntArray5821[n6], -16257) - -(n8 << 231051687);
                }
                if (n7 == 33) {
                    this.anIntArray5821[n6] = n8 + Class202.method2702(this.anIntArray5821[n6], -128);
                }
                if (n7 == 5) {
                    this.anIntArray5835[n6] = (n8 << -324226233) + Class202.method2702(-16257, this.anIntArray5835[n6]);
                }
                if (~n7 == 0xFFFFFFDA) {
                    this.anIntArray5835[n6] = Class202.method2702(-128, this.anIntArray5835[n6]) - -n8;
                }
                if (n7 == 7) {
                    this.anIntArray5831[n6] = Class202.method2702(-16257, this.anIntArray5831[n6]) + (n8 << 1142330119);
                }
                if (n7 == 39) {
                    this.anIntArray5831[n6] = Class202.method2702(this.anIntArray5831[n6], -128) + n8;
                }
                if (n7 == 10) {
                    this.anIntArray5839[n6] = Class202.method2702(this.anIntArray5839[n6], -16257) - -(n8 << 1026620103);
                }
                if (~n7 == 0xFFFFFFD5) {
                    this.anIntArray5839[n6] = Class202.method2702(-128, this.anIntArray5839[n6]) + n8;
                }
                if (n7 == 11) {
                    this.anIntArray5826[n6] = Class202.method2702(this.anIntArray5826[n6], -16257) + (n8 << -1493630745);
                }
                if (~n7 == 0xFFFFFFD4) {
                    this.anIntArray5826[n6] = Class202.method2702(-128, this.anIntArray5826[n6]) + n8;
                }
                if (n7 == 64) {
                    if (~n8 > -65) {
                        this.anIntArray5840[n6] = Class202.method2702(this.anIntArray5840[n6], -2);
                    }
                    else {
                        this.anIntArray5840[n6] = Class41.method366(this.anIntArray5840[n6], 1);
                    }
                }
                if (~n7 == 0xFFFFFFBE) {
                    if (n8 < 64) {
                        this.method1369(n6, -114);
                        this.anIntArray5840[n6] = Class202.method2702(this.anIntArray5840[n6], -3);
                    }
                    else {
                        this.anIntArray5840[n6] = Class41.method366(this.anIntArray5840[n6], 2);
                    }
                }
                if (~n7 == 0xFFFFFF9C) {
                    this.anIntArray5829[n6] = Class202.method2702(this.anIntArray5829[n6], 127) - -(n8 << -107977913);
                }
                if (n7 == 98) {
                    this.anIntArray5829[n6] = n8 + Class202.method2702(this.anIntArray5829[n6], 16256);
                }
                if (~n7 == 0xFFFFFF9A) {
                    this.anIntArray5829[n6] = Class202.method2702(this.anIntArray5829[n6], 127) + (16384 + (n8 << -1693120281));
                }
                if (~n7 == 0xFFFFFF9B) {
                    this.anIntArray5829[n6] = n8 + Class202.method2702(16256, this.anIntArray5829[n6]) + 16384;
                }
                if (~n7 == 0xFFFFFF87) {
                    this.method1355(false, n6);
                }
                if (~n7 == 0xFFFFFF86) {
                    this.method1342(0, n6);
                }
                if (~n7 == 0xFFFFFF84) {
                    this.method1335(n6, 0);
                }
                if (n7 == 6 && ~this.anIntArray5829[n6] == 0xFFFFBFFF) {
                    this.anIntArray5819[n6] = Class202.method2702(-16257, this.anIntArray5819[n6]) + (n8 << 1255328263);
                }
                if (n7 == 38 && ~this.anIntArray5829[n6] == 0xFFFFBFFF) {
                    this.anIntArray5819[n6] = Class202.method2702(-128, this.anIntArray5819[n6]) + n8;
                }
                if (n7 == 16) {
                    this.anIntArray5842[n6] = Class202.method2702(this.anIntArray5842[n6], -16257) - -(n8 << -1160099449);
                }
                if (~n7 == 0xFFFFFFCF) {
                    this.anIntArray5842[n6] = n8 + Class202.method2702(this.anIntArray5842[n6], -128);
                }
                if (n7 == 81) {
                    if (~n8 <= -65) {
                        this.anIntArray5840[n6] = Class41.method366(this.anIntArray5840[n6], 4);
                    }
                    else {
                        this.method1368(103, n6);
                        this.anIntArray5840[n6] = Class202.method2702(this.anIntArray5840[n6], -5);
                    }
                }
                if (~n7 == 0xFFFFFFEE) {
                    this.method1348(103, n6, (0xFFFFC07F & this.anIntArray5837[n6]) - -(n8 << -1302433305));
                }
                if (~n7 == 0xFFFFFFCE) {
                    this.method1348(111, n6, (0xFFFFFF80 & this.anIntArray5837[n6]) + n8);
                }
            }
            else if (~n2 == 0xFFFFFF3F) {
                final int n9 = n & 0xF;
                this.method1347((byte)(-128), this.anIntArray5841[n9] + ((n & 0x7FC6) >> -889995320), n9);
            }
            else if (n2 == 208) {
                this.method1365(n >> -966108184 & 0x7F, n & 0xF, 15);
            }
            else if (n2 == 224) {
                this.method1362(n & 0xF, 1, ((n & 0x7F94) >> -1491268920) + ((n & 0x7F0001) >> 1964066633));
            }
            else if ((n & 0xFF) == 0xFF) {
                this.method1331(true, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.P(" + n + ',' + b + ')');
        }
    }
    
    private final void method1338(final int n, final int n2, final boolean b, final int n3) {
        try {
            if (!b) {
                this.anIntArray5819 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.C(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    static final int method1339(final int n, final int n2) {
        try {
            if (n == 6407 || ~n == 0xFFFF77E4 || n == 34837) {
                return 6407;
            }
            if (~n == 0xFFFFE6F7 || ~n == 0xFFFF77E5 || ~n == 0xFFFF77EB) {
                return 6408;
            }
            if (n == 6406 || ~n == 0xFFFF77E3) {
                return 6406;
            }
            if (n == 6409 || ~n == 0xFFFF77E1) {
                return 6409;
            }
            if (~n == 0xFFFFE6F5 || ~n == 0xFFFF77E0) {
                return 6410;
            }
            if (n2 < 123) {
                method1333(-1);
            }
            if (n == 6402) {
                return 6402;
            }
            throw new IllegalArgumentException("");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.FB(" + n + ',' + n2 + ')');
        }
    }
    
    final boolean method1340(final int n, final int[] array, final int n2, final int n3, final Class98_Sub16 class98_Sub16) {
        try {
            class98_Sub16.anInt3923 = Class64_Sub15.anInt3678 / 100;
            if (class98_Sub16.anInt3930 >= 0 && (class98_Sub16.aClass98_Sub31_Sub5_3939 == null || class98_Sub16.aClass98_Sub31_Sub5_3939.method1401())) {
                class98_Sub16.method1148(-1);
                class98_Sub16.method942(53);
                if (class98_Sub16.anInt3931 > 0 && this.aClass98_Sub16ArrayArray5828[class98_Sub16.anInt3940][class98_Sub16.anInt3931] == class98_Sub16) {
                    this.aClass98_Sub16ArrayArray5828[class98_Sub16.anInt3940][class98_Sub16.anInt3931] = null;
                }
                return true;
            }
            final int anInt3929 = class98_Sub16.anInt3929;
            if (anInt3929 > 0) {
                int anInt3930 = anInt3929 - (int)(0.5 + Math.pow(2.0, 4.921259842519685E-4 * this.anIntArray5835[class98_Sub16.anInt3940]) * 16.0);
                if (anInt3930 < 0) {
                    anInt3930 = 0;
                }
                class98_Sub16.anInt3929 = anInt3930;
            }
            class98_Sub16.aClass98_Sub31_Sub5_3939.method1407(this.method1350(n2, class98_Sub16));
            final Class89 aClass89_3935 = class98_Sub16.aClass89_3935;
            ++class98_Sub16.anInt3921;
            boolean b = false;
            class98_Sub16.anInt3941 += aClass89_3935.anInt710;
            final double n4 = ((-60 + class98_Sub16.anInt3936 << -498361240) + (class98_Sub16.anInt3938 * class98_Sub16.anInt3929 >> -147636500)) * 5.086263020833333E-6;
            if (aClass89_3935.anInt707 > 0) {
                if (~aClass89_3935.anInt712 >= -1) {
                    class98_Sub16.anInt3932 += 128;
                }
                else {
                    class98_Sub16.anInt3932 += (int)(0.5 + 128.0 * Math.pow(2.0, aClass89_3935.anInt712 * n4));
                }
                if (aClass89_3935.anInt707 * class98_Sub16.anInt3932 >= 819200) {
                    b = true;
                }
            }
            if (aClass89_3935.aByteArray714 != null) {
                if (~aClass89_3935.anInt711 >= -1) {
                    class98_Sub16.anInt3920 += 128;
                }
                else {
                    class98_Sub16.anInt3920 += (int)(0.5 + Math.pow(2.0, n4 * aClass89_3935.anInt711) * 128.0);
                }
                while (~(aClass89_3935.aByteArray714.length - 2) < ~class98_Sub16.anInt3937 && class98_Sub16.anInt3920 > (0xFF & aClass89_3935.aByteArray714[2 + class98_Sub16.anInt3937]) << 2116910312) {
                    class98_Sub16.anInt3937 += 2;
                }
                if (~class98_Sub16.anInt3937 == ~(-2 + aClass89_3935.aByteArray714.length) && ~aClass89_3935.aByteArray714[1 + class98_Sub16.anInt3937] == -1) {
                    b = true;
                }
            }
            if (~class98_Sub16.anInt3930 <= -1 && aClass89_3935.aByteArray713 != null && ~(0x1 & this.anIntArray5840[class98_Sub16.anInt3940]) == -1 && (class98_Sub16.anInt3931 < 0 || class98_Sub16 != this.aClass98_Sub16ArrayArray5828[class98_Sub16.anInt3940][class98_Sub16.anInt3931])) {
                if (~aClass89_3935.anInt715 >= -1) {
                    class98_Sub16.anInt3930 += 128;
                }
                else {
                    class98_Sub16.anInt3930 += (int)(128.0 * Math.pow(2.0, n4 * aClass89_3935.anInt715) + 0.5);
                }
                while (~(-2 + aClass89_3935.aByteArray713.length) < ~class98_Sub16.anInt3922 && ~((0xFF & aClass89_3935.aByteArray713[2 + class98_Sub16.anInt3922]) << -865703064) > ~class98_Sub16.anInt3930) {
                    class98_Sub16.anInt3922 += 2;
                }
                if (~(aClass89_3935.aByteArray713.length - 2) == ~class98_Sub16.anInt3922) {
                    b = true;
                }
            }
            if (b) {
                class98_Sub16.aClass98_Sub31_Sub5_3939.method1423(class98_Sub16.anInt3923);
                if (array != null) {
                    class98_Sub16.aClass98_Sub31_Sub5_3939.method1325(array, n3, n);
                }
                else {
                    class98_Sub16.aClass98_Sub31_Sub5_3939.method1321(n);
                }
                if (class98_Sub16.aClass98_Sub31_Sub5_3939.method1425()) {
                    this.aClass98_Sub31_Sub1_5846.aClass98_Sub31_Sub3_5818.method1371(class98_Sub16.aClass98_Sub31_Sub5_3939);
                }
                class98_Sub16.method1148(n2 ^ 0xFFFFFFFD);
                if (class98_Sub16.anInt3930 >= 0) {
                    class98_Sub16.method942(107);
                    if (class98_Sub16.anInt3931 > 0 && class98_Sub16 == this.aClass98_Sub16ArrayArray5828[class98_Sub16.anInt3940][class98_Sub16.anInt3931]) {
                        this.aClass98_Sub16ArrayArray5828[class98_Sub16.anInt3940][class98_Sub16.anInt3931] = null;
                    }
                }
                return true;
            }
            class98_Sub16.aClass98_Sub31_Sub5_3939.method1412(class98_Sub16.anInt3923, this.method1334(255, class98_Sub16), this.method1351(class98_Sub16, n2 - 114));
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.O(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + ((class98_Sub16 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final synchronized void method1341(final boolean b, final boolean b2, final Class98_Sub7 class98_Sub7, final long n, final int n2) {
        try {
            this.method1343(class98_Sub7, b, b2, 113);
            this.method1359((byte)(-117), this.aClass173_5823.anInt1340 * n);
            if (n2 != -3) {
                this.anIntArray5844 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.AB(" + b + ',' + b2 + ',' + ((class98_Sub7 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    private final void method1342(final int n, int i) {
        try {
            if (~i > -1) {
                for (i = 0; i < 16; ++i) {
                    this.method1342(n, i);
                }
            }
            else {
                this.anIntArray5831[i] = 12800;
                this.anIntArray5839[i] = 8192;
                this.anIntArray5826[i] = 16383;
                this.anIntArray5833[i] = 8192;
                this.anIntArray5821[i] = n;
                this.anIntArray5835[i] = 8192;
                this.method1369(i, n ^ 0x12);
                this.method1368(98, i);
                this.anIntArray5840[i] = 0;
                this.anIntArray5829[i] = 32767;
                this.anIntArray5819[i] = 256;
                this.anIntArray5842[i] = 0;
                this.method1348(71, i, 8192);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.EA(" + n + ',' + i + ')');
        }
    }
    
    private final synchronized void method1343(final Class98_Sub7 class98_Sub7, final boolean b, final boolean aBoolean5848, final int n) {
        try {
            if (n < 76) {
                this.method1346(-76, 125, -76, 76);
            }
            this.method1344(95, b);
            this.aClass173_5823.method2557(class98_Sub7.aByteArray3850);
            this.aBoolean5848 = aBoolean5848;
            this.aLong5847 = 0L;
            for (int method2558 = this.aClass173_5823.method2558(), i = 0; i < method2558; ++i) {
                this.aClass173_5823.method2555(i);
                this.aClass173_5823.method2556(i);
                this.aClass173_5823.method2552(i);
            }
            this.anInt5845 = this.aClass173_5823.method2551();
            this.anInt5850 = this.aClass173_5823.anIntArray1343[this.anInt5845];
            this.aLong5849 = this.aClass173_5823.method2547(this.anInt5850);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.IA(" + ((class98_Sub7 != null) ? "{...}" : "null") + ',' + b + ',' + aBoolean5848 + ',' + n + ')');
        }
    }
    
    private final synchronized void method1344(final int n, final boolean b) {
        try {
            this.aClass173_5823.method2545();
            this.aClass98_Sub7_5851 = null;
            this.method1331(b, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.F(" + n + ',' + b + ')');
        }
    }
    
    final boolean method1345(final int n, final Class98_Sub16 class98_Sub16) {
        try {
            if (n != 1816) {
                this.aClass377_5820 = null;
            }
            if (class98_Sub16.aClass98_Sub31_Sub5_3939 == null) {
                if (class98_Sub16.anInt3930 >= 0) {
                    class98_Sub16.method942(47);
                    if (~class98_Sub16.anInt3931 < -1 && class98_Sub16 == this.aClass98_Sub16ArrayArray5828[class98_Sub16.anInt3940][class98_Sub16.anInt3931]) {
                        this.aClass98_Sub16ArrayArray5828[class98_Sub16.anInt3940][class98_Sub16.anInt3931] = null;
                    }
                }
                return true;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.OA(" + n + ',' + ((class98_Sub16 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method1346(final int n, final int n2, final int n3, final int anInt3940) {
        try {
            this.method1353(n2, anInt3940, 64, 104);
            if (~(0x2 & this.anIntArray5840[anInt3940]) != -1) {
                for (Class98_Sub16 class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2427(-125); class98_Sub16 != null; class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2416((byte)5)) {
                    if (~anInt3940 == ~class98_Sub16.anInt3940 && class98_Sub16.anInt3930 < 0) {
                        this.aClass98_Sub16ArrayArray5825[anInt3940][class98_Sub16.anInt3936] = null;
                        this.aClass98_Sub16ArrayArray5825[anInt3940][n2] = class98_Sub16;
                        final int n4 = class98_Sub16.anInt3926 + (class98_Sub16.anInt3938 * class98_Sub16.anInt3929 >> 805761964);
                        final Class98_Sub16 class98_Sub17 = class98_Sub16;
                        class98_Sub17.anInt3926 += n2 + -class98_Sub16.anInt3936 << 308353096;
                        class98_Sub16.anInt3938 = -class98_Sub16.anInt3926 + n4;
                        class98_Sub16.anInt3936 = n2;
                        class98_Sub16.anInt3929 = 4096;
                        return;
                    }
                }
            }
            final Class98_Sub44 aClass98_Sub44_3918 = (Class98_Sub44)this.aClass377_5820.method3990(this.anIntArray5834[anInt3940], -1);
            if (aClass98_Sub44_3918 != null) {
                final Class98_Sub24_Sub1 aClass98_Sub24_Sub1_3934 = aClass98_Sub44_3918.aClass98_Sub24_Sub1Array4244[n2];
                if (aClass98_Sub24_Sub1_3934 != null) {
                    final Class98_Sub16 class98_Sub18 = new Class98_Sub16();
                    class98_Sub18.aClass98_Sub24_Sub1_3934 = aClass98_Sub24_Sub1_3934;
                    class98_Sub18.anInt3940 = anInt3940;
                    class98_Sub18.aClass98_Sub44_3918 = aClass98_Sub44_3918;
                    class98_Sub18.aClass89_3935 = aClass98_Sub44_3918.aClass89Array4251[n2];
                    class98_Sub18.anInt3931 = aClass98_Sub44_3918.aByteArray4250[n2];
                    class98_Sub18.anInt3936 = n2;
                    class98_Sub18.anInt3925 = 1024 + aClass98_Sub44_3918.anInt4249 * n3 * (n3 * aClass98_Sub44_3918.aByteArray4252[n2]) >> 622064875;
                    class98_Sub18.anInt3924 = (aClass98_Sub44_3918.aByteArray4247[n2] & 0xFF);
                    class98_Sub18.anInt3926 = (n2 << 1695782120) + -(0x7FFF & aClass98_Sub44_3918.aShortArray4248[n2]);
                    class98_Sub18.anInt3932 = 0;
                    class98_Sub18.anInt3937 = 0;
                    class98_Sub18.anInt3920 = 0;
                    class98_Sub18.anInt3922 = 0;
                    class98_Sub18.anInt3930 = -1;
                    if (this.anIntArray5842[anInt3940] == 0) {
                        class98_Sub18.aClass98_Sub31_Sub5_3939 = Class98_Sub31_Sub5.method1402(aClass98_Sub24_Sub1_3934, this.method1350(2, class98_Sub18), this.method1334(255, class98_Sub18), this.method1351(class98_Sub18, 20));
                    }
                    else {
                        class98_Sub18.aClass98_Sub31_Sub5_3939 = Class98_Sub31_Sub5.method1402(aClass98_Sub24_Sub1_3934, this.method1350(2, class98_Sub18), 0, this.method1351(class98_Sub18, -103));
                        this.method1361(1, aClass98_Sub44_3918.aShortArray4248[n2] < 0, class98_Sub18);
                    }
                    if (~aClass98_Sub44_3918.aShortArray4248[n2] > -1) {
                        class98_Sub18.aClass98_Sub31_Sub5_3939.method1422(-1);
                    }
                    if (class98_Sub18.anInt3931 >= 0) {
                        final Class98_Sub16 class98_Sub19 = this.aClass98_Sub16ArrayArray5828[anInt3940][class98_Sub18.anInt3931];
                        if (class98_Sub19 != null && ~class98_Sub19.anInt3930 > -1) {
                            this.aClass98_Sub16ArrayArray5825[anInt3940][class98_Sub19.anInt3936] = null;
                            class98_Sub19.anInt3930 = 0;
                        }
                        this.aClass98_Sub16ArrayArray5828[anInt3940][class98_Sub18.anInt3931] = class98_Sub18;
                    }
                    this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2419(class98_Sub18, -20911);
                    this.aClass98_Sub16ArrayArray5825[anInt3940][n2] = class98_Sub18;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.JA(" + n + ',' + n2 + ',' + n3 + ',' + anInt3940 + ')');
        }
    }
    
    private final void method1347(final byte b, final int n, final int n2) {
        try {
            if (b >= -127) {
                this.aBoolean5852 = true;
            }
            if (this.anIntArray5834[n2] != n) {
                this.anIntArray5834[n2] = n;
                for (int i = 0; i < 128; ++i) {
                    this.aClass98_Sub16ArrayArray5828[n2][i] = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.R(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    private final void method1348(final int n, final int n2, final int n3) {
        try {
            if (n >= 40) {
                this.anIntArray5837[n2] = n3;
                this.anIntArray5843[n2] = (int)(0.5 + Math.pow(2.0, 5.4931640625E-4 * n3) * 2097152.0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.FA(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final synchronized void method1349(final int n) {
        try {
            if (n == -1) {
                for (Class98_Sub44 class98_Sub44 = (Class98_Sub44)this.aClass377_5820.method3998(n + 96); class98_Sub44 != null; class98_Sub44 = (Class98_Sub44)this.aClass377_5820.method3995(-1)) {
                    class98_Sub44.method942(n + 52);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.HA(" + n + ')');
        }
    }
    
    private final int method1350(final int n, final Class98_Sub16 class98_Sub16) {
        try {
            int n2 = (class98_Sub16.anInt3938 * class98_Sub16.anInt3929 >> 94082348) + class98_Sub16.anInt3926 + ((-8192 + this.anIntArray5833[class98_Sub16.anInt3940]) * this.anIntArray5819[class98_Sub16.anInt3940] >> -1798455636);
            final Class89 aClass89_3935 = class98_Sub16.aClass89_3935;
            if (~aClass89_3935.anInt710 < -1 && (aClass89_3935.anInt708 > 0 || ~this.anIntArray5821[class98_Sub16.anInt3940] < -1)) {
                int n3 = aClass89_3935.anInt708 << 789514338;
                final int n4 = aClass89_3935.anInt717 << 1268741473;
                if (~class98_Sub16.anInt3921 > ~n4) {
                    n3 = class98_Sub16.anInt3921 * n3 / n4;
                }
                n2 += (int)((n3 + (this.anIntArray5821[class98_Sub16.anInt3940] >> 677293351)) * Math.sin(0.01227184630308513 * (0x1FF & class98_Sub16.anInt3941)));
            }
            final int n5 = (int)(class98_Sub16.aClass98_Sub24_Sub1_3934.anInt5795 * 256 * Math.pow(n, 3.255208333333333E-4 * n2) / Class64_Sub15.anInt3678 + 0.5);
            if (~n5 <= -2) {
                return n5;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.LA(" + n + ',' + ((class98_Sub16 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final int method1351(final Class98_Sub16 class98_Sub16, final int n) {
        try {
            final int n2 = this.anIntArray5839[class98_Sub16.anInt3940];
            if (n2 < 8192) {
                return class98_Sub16.anInt3924 * n2 + 32 >> 1741690950;
            }
            return 16384 - (32 + (128 + -class98_Sub16.anInt3924) * (16384 + -n2) >> -526683354);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.BB(" + ((class98_Sub16 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final synchronized boolean method1352(final Class98_Sub7 class98_Sub7, final int n, final Class308 class308, final Class207 class309, final boolean b) {
        try {
            class98_Sub7.method984();
            boolean b2 = true;
            int[] array = null;
            if (~n < -1) {
                array = new int[] { n };
            }
            if (b) {
                return false;
            }
            for (Class98_Sub14 class98_Sub8 = (Class98_Sub14)class98_Sub7.aClass377_3849.method3998(117); class98_Sub8 != null; class98_Sub8 = (Class98_Sub14)class98_Sub7.aClass377_3849.method3995(-1)) {
                final int n2 = (int)class98_Sub8.aLong832;
                Class98_Sub44 method3875 = (Class98_Sub44)this.aClass377_5820.method3990(n2, -1);
                if (method3875 == null) {
                    method3875 = Class355.method3875(n2, class309, -118);
                    if (method3875 == null) {
                        b2 = false;
                        continue;
                    }
                    this.aClass377_5820.method3996(method3875, n2, -1);
                }
                if (!method3875.method1517(class98_Sub8.aByteArray3914, class308, (byte)(-121), array)) {
                    b2 = false;
                }
            }
            if (b2) {
                class98_Sub7.method983();
            }
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.PA(" + ((class98_Sub7 != null) ? "{...}" : "null") + ',' + n + ',' + ((class308 != null) ? "{...}" : "null") + ',' + ((class309 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    private final void method1353(final int n, final int n2, final int n3, final int n4) {
        try {
            if (n4 <= 5) {
                this.aLong5847 = -45L;
            }
            final Class98_Sub16 class98_Sub16 = this.aClass98_Sub16ArrayArray5825[n2][n];
            if (class98_Sub16 != null) {
                this.aClass98_Sub16ArrayArray5825[n2][n] = null;
                if (~(0x2 & this.anIntArray5840[n2]) == -1) {
                    class98_Sub16.anInt3930 = 0;
                }
                else {
                    for (Class98_Sub16 class98_Sub17 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2418(32); class98_Sub17 != null; class98_Sub17 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2417(97)) {
                        if (~class98_Sub17.anInt3940 == ~class98_Sub16.anInt3940 && class98_Sub17.anInt3930 < 0 && class98_Sub16 != class98_Sub17) {
                            class98_Sub16.anInt3930 = 0;
                            break;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.NA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final synchronized void method1321(int n) {
        try {
            if (this.aClass173_5823.method2546()) {
                final int n2 = this.anInt5827 * this.aClass173_5823.anInt1340 / Class64_Sub15.anInt3678;
                do {
                    final long aLong5847 = this.aLong5847 - -(n2 * n);
                    if (this.aLong5849 - aLong5847 >= 0L) {
                        this.aLong5847 = aLong5847;
                        break;
                    }
                    final int n3 = (int)((-1L + n2 + (-this.aLong5847 + this.aLong5849)) / n2);
                    this.aLong5847 += n2 * n3;
                    n -= n3;
                    this.aClass98_Sub31_Sub1_5846.method1321(n3);
                    this.method1370(121);
                } while (this.aClass173_5823.method2546());
            }
            this.aClass98_Sub31_Sub1_5846.method1321(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.M(" + n + ')');
        }
    }
    
    final synchronized boolean method1354(final int n) {
        try {
            if (n != -3619) {
                this.method1356(17, 42, false);
            }
            return this.aClass173_5823.method2546();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.TA(" + n + ')');
        }
    }
    
    private final void method1355(final boolean b, final int n) {
        try {
            for (Class98_Sub16 class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2418(32); class98_Sub16 != null; class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2417(102)) {
                if (~n > -1 || class98_Sub16.anInt3940 == n) {
                    if (class98_Sub16.aClass98_Sub31_Sub5_3939 != null) {
                        class98_Sub16.aClass98_Sub31_Sub5_3939.method1423(Class64_Sub15.anInt3678 / 100);
                        if (class98_Sub16.aClass98_Sub31_Sub5_3939.method1425()) {
                            this.aClass98_Sub31_Sub1_5846.aClass98_Sub31_Sub3_5818.method1371(class98_Sub16.aClass98_Sub31_Sub5_3939);
                        }
                        class98_Sub16.method1148(-1);
                    }
                    if (~class98_Sub16.anInt3930 > -1) {
                        this.aClass98_Sub16ArrayArray5825[class98_Sub16.anInt3940][class98_Sub16.anInt3936] = null;
                    }
                    class98_Sub16.method942(115);
                }
            }
            if (b) {
                this.method1348(-2, -56, -43);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.GA(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final synchronized Class98_Sub31 method1327() {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.D()");
        }
    }
    
    private final void method1356(final int n, final int n2, final boolean b) {
        try {
            this.anIntArray5830[n] = n2;
            this.anIntArray5841[n] = Class202.method2702(n2, -128);
            if (b) {
                this.aClass98_Sub31_Sub1_5846 = null;
            }
            this.method1347((byte)(-128), n2, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.H(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    final synchronized void method1357(final int n, final int n2, final int n3) {
        try {
            this.method1356(n3, n2, false);
            if (n != 26377) {
                this.method1337(102, (byte)85);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.UA(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final synchronized void method1358(final byte b) {
        try {
            if (b != 23) {
                this.anIntArray5842 = null;
            }
            for (Class98_Sub44 class98_Sub44 = (Class98_Sub44)this.aClass377_5820.method3998(123); class98_Sub44 != null; class98_Sub44 = (Class98_Sub44)this.aClass377_5820.method3995(-1)) {
                class98_Sub44.method1513(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.MA(" + b + ')');
        }
    }
    
    private final void method1359(final byte b, final long n) {
        try {
            while (n >= this.aLong5849) {
                int anInt5845 = this.anInt5845;
                int anInt5846 = this.anInt5850;
                long n2 = this.aLong5849;
                while (this.anInt5850 == anInt5846) {
                    while (~anInt5846 == ~this.aClass173_5823.anIntArray1343[anInt5845]) {
                        this.aClass173_5823.method2555(anInt5845);
                        final int method2559 = this.aClass173_5823.method2559(anInt5845);
                        if (method2559 == 1) {
                            this.aClass173_5823.method2553();
                            this.aClass173_5823.method2552(anInt5845);
                            if (!this.aClass173_5823.method2550()) {
                                break;
                            }
                            if (this.aBoolean5848 && ~anInt5846 != -1) {
                                this.aClass173_5823.method2548(n2);
                                break;
                            }
                            this.method1331(true, 0);
                            this.aClass173_5823.method2545();
                            return;
                        }
                        else {
                            if (~(method2559 & 0x80) != -1 && ~(method2559 & 0xF0) != 0xFFFFFF6F) {
                                this.method1337(method2559, (byte)126);
                            }
                            this.aClass173_5823.method2556(anInt5845);
                            this.aClass173_5823.method2552(anInt5845);
                        }
                    }
                    this.aLong5847 = n2;
                    anInt5845 = this.aClass173_5823.method2551();
                    anInt5846 = this.aClass173_5823.anIntArray1343[anInt5845];
                    n2 = this.aClass173_5823.method2547(anInt5846);
                }
                this.aLong5849 = n2;
                this.anInt5850 = anInt5846;
                this.anInt5845 = anInt5845;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.WA(" + b + ',' + n + ')');
        }
    }
    
    final int method1360(final int n) {
        try {
            if (n != -16257) {
                return -118;
            }
            return this.anInt5836;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.RA(" + n + ')');
        }
    }
    
    final void method1361(final int n, final boolean b, final Class98_Sub16 class98_Sub16) {
        try {
            int length = class98_Sub16.aClass98_Sub24_Sub1_3934.aByteArray5799.length;
            int n2 = 0;
            Label_0129: {
                if (b && class98_Sub16.aClass98_Sub24_Sub1_3934.aBoolean5797) {
                    n2 = this.anIntArray5842[class98_Sub16.anInt3940] * (-class98_Sub16.aClass98_Sub24_Sub1_3934.anInt5798 + (length + length)) >> -261318330;
                    length <<= 8;
                    if (n2 < length) {
                        break Label_0129;
                    }
                    n2 = -n2 + (length + length) - 1;
                    class98_Sub16.aClass98_Sub31_Sub5_3939.method1411(true);
                    if (!client.aBoolean3553) {
                        break Label_0129;
                    }
                }
                n2 = this.anIntArray5842[class98_Sub16.anInt3940] * length >> 1204742342;
            }
            class98_Sub16.aClass98_Sub31_Sub5_3939.method1399(n2);
            if (n != 1) {
                this.aBoolean5852 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.L(" + n + ',' + b + ',' + ((class98_Sub16 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method1362(final int n, final int n2, final int n3) {
        try {
            this.anIntArray5833[n] = n3;
            if (n2 != 1) {
                method1333(-63);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.S(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final synchronized Class98_Sub31 method1322() {
        try {
            return this.aClass98_Sub31_Sub1_5846;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.B()");
        }
    }
    
    final synchronized void method1363(final int n, final int n2, final int n3) {
        try {
            if (n2 >= 0) {
                this.anIntArray5844[n2] = n3;
            }
            else {
                for (int n4 = 0; ~n4 > -17; ++n4) {
                    this.anIntArray5844[n4] = n3;
                }
            }
            if (n != -17) {
                Class98_Sub31_Sub2.anInt5824 = -115;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.EB(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final synchronized void method1364(final int n) {
        try {
            if (n <= 84) {
                this.anIntArray5830 = null;
            }
            this.method1344(-123, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.DB(" + n + ')');
        }
    }
    
    private final void method1365(final int n, final int n2, final int n3) {
        try {
            if (n3 != 15) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.CB(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final synchronized void method1366(final int anInt5836, final byte b) {
        try {
            if (b <= 7) {
                this.method1337(-49, (byte)(-22));
            }
            this.anInt5836 = anInt5836;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.N(" + anInt5836 + ',' + b + ')');
        }
    }
    
    static final void method1367(final byte b) {
        try {
            if (!Class4.aBoolean79) {
                Class180.aFloat3405 += (-24.0f - Class180.aFloat3405) / 2.0f;
                if (b == 83) {
                    Class64_Sub6.aBoolean3656 = true;
                    Class4.aBoolean79 = true;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.VA(" + b + ')');
        }
    }
    
    private final void method1368(final int n, final int n2) {
        try {
            if (~(this.anIntArray5840[n2] & 0x4) != -1) {
                for (Class98_Sub16 class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2418(32); class98_Sub16 != null; class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2417(101)) {
                    if (n2 == class98_Sub16.anInt3940) {
                        class98_Sub16.anInt3919 = 0;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.SA(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final synchronized void method1325(final int[] array, int n, int n2) {
        try {
            if (this.aClass173_5823.method2546()) {
                final int n3 = this.anInt5827 * this.aClass173_5823.anInt1340 / Class64_Sub15.anInt3678;
                do {
                    final long aLong5847 = n2 * n3 + this.aLong5847;
                    if (~(-aLong5847 + this.aLong5849) <= -1L) {
                        this.aLong5847 = aLong5847;
                        break;
                    }
                    final int n4 = (int)((-1L + (-this.aLong5847 + (this.aLong5849 - -n3))) / n3);
                    this.aLong5847 += n3 * n4;
                    this.aClass98_Sub31_Sub1_5846.method1325(array, n, n4);
                    n += n4;
                    n2 -= n4;
                    this.method1370(115);
                } while (this.aClass173_5823.method2546());
            }
            this.aClass98_Sub31_Sub1_5846.method1325(array, n, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.A(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    private final void method1369(final int n, final int n2) {
        try {
            if ((0x2 & this.anIntArray5840[n]) != 0x0) {
                for (Class98_Sub16 class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2418(32); class98_Sub16 != null; class98_Sub16 = (Class98_Sub16)this.aClass98_Sub31_Sub1_5846.aClass148_5817.method2417(110)) {
                    if (class98_Sub16.anInt3940 == n && this.aClass98_Sub16ArrayArray5825[n][class98_Sub16.anInt3936] == null && ~class98_Sub16.anInt3930 > -1) {
                        class98_Sub16.anInt3930 = 0;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.K(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method1370(final int n) {
        try {
            int anInt5845 = this.anInt5845;
            if (n <= 112) {
                this.method1344(-33, false);
            }
            int anInt5846 = this.anInt5850;
            long aLong5849 = this.aLong5849;
            if (this.aClass98_Sub7_5851 != null && ~anInt5846 == ~this.anInt5853) {
                this.method1343(this.aClass98_Sub7_5851, this.aBoolean5852, this.aBoolean5848, 90);
                this.method1370(124);
            }
            else {
                while (~anInt5846 == ~this.anInt5850) {
                    while (~this.aClass173_5823.anIntArray1343[anInt5845] == ~anInt5846) {
                        this.aClass173_5823.method2555(anInt5845);
                        final int method2559 = this.aClass173_5823.method2559(anInt5845);
                        if (method2559 == 1) {
                            this.aClass173_5823.method2553();
                            this.aClass173_5823.method2552(anInt5845);
                            if (!this.aClass173_5823.method2550()) {
                                break;
                            }
                            if (this.aClass98_Sub7_5851 != null) {
                                this.method1332(this.aBoolean5848, this.aClass98_Sub7_5851, (byte)(-4));
                                this.method1370(119);
                                return;
                            }
                            if (this.aBoolean5848 && anInt5846 != 0) {
                                this.aClass173_5823.method2548(aLong5849);
                                break;
                            }
                            this.method1331(true, 0);
                            this.aClass173_5823.method2545();
                            return;
                        }
                        else {
                            if ((0x80 & method2559) != 0x0) {
                                this.method1337(method2559, (byte)127);
                            }
                            this.aClass173_5823.method2556(anInt5845);
                            this.aClass173_5823.method2552(anInt5845);
                        }
                    }
                    anInt5845 = this.aClass173_5823.method2551();
                    anInt5846 = this.aClass173_5823.anIntArray1343[anInt5845];
                    aLong5849 = this.aClass173_5823.method2547(anInt5846);
                }
                this.anInt5850 = anInt5846;
                this.anInt5845 = anInt5845;
                this.aLong5849 = aLong5849;
                if (this.aClass98_Sub7_5851 != null && this.anInt5853 < anInt5846) {
                    this.anInt5845 = -1;
                    this.anInt5850 = this.anInt5853;
                    this.aLong5849 = this.aClass173_5823.method2547(this.anInt5850);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.KA(" + n + ')');
        }
    }
    
    @Override
    final synchronized int method1326() {
        try {
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.G()");
        }
    }
    
    public Class98_Sub31_Sub2() {
        this.anIntArray5821 = new int[16];
        this.aClass98_Sub16ArrayArray5825 = new Class98_Sub16[16][128];
        this.aClass98_Sub16ArrayArray5828 = new Class98_Sub16[16][128];
        this.anIntArray5830 = new int[16];
        this.anIntArray5826 = new int[16];
        this.anIntArray5831 = new int[16];
        this.anIntArray5833 = new int[16];
        this.anIntArray5834 = new int[16];
        this.anIntArray5829 = new int[16];
        this.anIntArray5837 = new int[16];
        this.anInt5827 = 1000000;
        this.anIntArray5840 = new int[16];
        this.anIntArray5839 = new int[16];
        this.anInt5836 = 256;
        this.anIntArray5842 = new int[16];
        this.anIntArray5841 = new int[16];
        this.anIntArray5843 = new int[16];
        this.anIntArray5819 = new int[16];
        this.anIntArray5844 = new int[16];
        this.anIntArray5835 = new int[16];
        this.aClass173_5823 = new Class173();
        this.aClass98_Sub31_Sub1_5846 = new Class98_Sub31_Sub1(this);
        try {
            this.aClass377_5820 = new Class377(128);
            this.method1363(-17, -1, 256);
            this.method1331(true, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.<init>()");
        }
    }
    
    Class98_Sub31_Sub2(final Class98_Sub31_Sub2 class98_Sub31_Sub2) {
        this.anIntArray5821 = new int[16];
        this.aClass98_Sub16ArrayArray5825 = new Class98_Sub16[16][128];
        this.aClass98_Sub16ArrayArray5828 = new Class98_Sub16[16][128];
        this.anIntArray5830 = new int[16];
        this.anIntArray5826 = new int[16];
        this.anIntArray5831 = new int[16];
        this.anIntArray5833 = new int[16];
        this.anIntArray5834 = new int[16];
        this.anIntArray5829 = new int[16];
        this.anIntArray5837 = new int[16];
        this.anInt5827 = 1000000;
        this.anIntArray5840 = new int[16];
        this.anIntArray5839 = new int[16];
        this.anInt5836 = 256;
        this.anIntArray5842 = new int[16];
        this.anIntArray5841 = new int[16];
        this.anIntArray5843 = new int[16];
        this.anIntArray5819 = new int[16];
        this.anIntArray5844 = new int[16];
        this.anIntArray5835 = new int[16];
        this.aClass173_5823 = new Class173();
        this.aClass98_Sub31_Sub1_5846 = new Class98_Sub31_Sub1(this);
        try {
            this.aClass377_5820 = class98_Sub31_Sub2.aClass377_5820;
            this.method1363(-17, -1, 256);
            this.method1331(true, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jga.<init>(" + ((class98_Sub31_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub31_Sub2.anIntArray5832 = new int[1];
        Class98_Sub31_Sub2.anInt5824 = 0;
        Class98_Sub31_Sub2.anInt5822 = 0;
        Class98_Sub31_Sub2.aClass58_5838 = new IncomingOpcode(32, 1);
    }
}
