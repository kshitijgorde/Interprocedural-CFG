import java.io.DataInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class305_Sub1 extends Class305
{
    int anInt5302;
    static int anInt5303;
    static Class aClass5304;
    
    Class305_Sub1(final int n, final int n2, final int n3, final boolean b) {
        super(n, n2, n3, b, Class199.aClass32_1531, Class82.aClass153_630);
        this.anInt5302 = 99;
    }
    
    final void method3582(final int n, final int n2, final int n3, final ha ha, final Class98_Sub22 class98_Sub22, final int[] array) {
        try {
            if (!super.aBoolean2544) {
                boolean b = false;
                if (n != 17685) {
                    this.anInt5302 = 114;
                }
                Class28 class28 = null;
                if (array != null) {
                    array[0] = -1;
                }
                while (class98_Sub22.aByteArray3992.length > class98_Sub22.anInt3991) {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-125));
                    if (~unsignedByte != -1) {
                        if (~unsignedByte != 0xFFFFFFFE) {
                            if (unsignedByte == 2) {
                                if (class28 == null) {
                                    class28 = new Class28();
                                }
                                class28.method297(-50, class98_Sub22);
                            }
                            else if (unsignedByte != 128) {
                                if (~unsignedByte != 0xFFFFFF7E) {
                                    throw new IllegalStateException("");
                                }
                                if (super.aByteArrayArrayArray2554 == null) {
                                    super.aByteArrayArrayArray2554 = new byte[4][][];
                                }
                                b = true;
                                for (int i = 0; i < 4; ++i) {
                                    final byte signedByte = class98_Sub22.readSignedByte((byte)(-19));
                                    if (~signedByte != -1 || super.aByteArrayArrayArray2554[i] == null) {
                                        if (~signedByte == 0xFFFFFFFE) {
                                            if (super.aByteArrayArrayArray2554[i] == null) {
                                                super.aByteArrayArrayArray2554[i] = new byte[super.anInt2543 + 1][1 + super.anInt2542];
                                            }
                                            for (int n4 = 0; ~n4 > -65; n4 += 4) {
                                                for (int n5 = 0; ~n5 > -65; n5 += 4) {
                                                    final byte signedByte2 = class98_Sub22.readSignedByte((byte)(-19));
                                                    for (int n6 = n4 + n3; ~n6 > ~(n4 - (-n3 - 4)); ++n6) {
                                                        for (int j = n5 - -n2; j < n2 + n5 + 4; ++j) {
                                                            if (n6 >= 0 && ~n6 > ~super.anInt2543 && ~j <= -1 && ~j > ~super.anInt2542) {
                                                                super.aByteArrayArrayArray2554[i][n6][j] = signedByte2;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        else if (~signedByte == 0xFFFFFFFD) {
                                            if (super.aByteArrayArrayArray2554[i] == null) {
                                                super.aByteArrayArrayArray2554[i] = new byte[1 + super.anInt2543][1 + super.anInt2542];
                                            }
                                            if (~i < -1) {
                                                int anInt2543 = n3;
                                                int anInt2544 = n3 + 64;
                                                int anInt2545 = n2;
                                                if (~anInt2545 > -1) {
                                                    anInt2545 = 0;
                                                }
                                                else if (~anInt2545 <= ~super.anInt2542) {
                                                    anInt2545 = super.anInt2542;
                                                }
                                                if (anInt2544 >= 0) {
                                                    if (~super.anInt2543 >= ~anInt2544) {
                                                        anInt2544 = super.anInt2543;
                                                    }
                                                }
                                                else {
                                                    anInt2544 = 0;
                                                }
                                                int anInt2546 = n2 + 64;
                                                if (~anInt2543 > -1) {
                                                    anInt2543 = 0;
                                                }
                                                else if (~anInt2543 <= ~super.anInt2543) {
                                                    anInt2543 = super.anInt2543;
                                                }
                                                if (anInt2546 >= 0) {
                                                    if (~anInt2546 <= ~super.anInt2542) {
                                                        anInt2546 = super.anInt2542;
                                                    }
                                                }
                                                else {
                                                    anInt2546 = 0;
                                                }
                                                while (~anInt2543 > ~anInt2544) {
                                                    while (~anInt2546 < ~anInt2545) {
                                                        super.aByteArrayArrayArray2554[i][anInt2543][anInt2545] = super.aByteArrayArrayArray2554[i - 1][anInt2543][anInt2545];
                                                        ++anInt2545;
                                                    }
                                                    ++anInt2543;
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        int anInt2547 = n3;
                                        int k = 64 + n3;
                                        int anInt2548 = n2;
                                        if (~anInt2547 <= -1) {
                                            if (~super.anInt2543 >= ~anInt2547) {
                                                anInt2547 = super.anInt2543;
                                            }
                                        }
                                        else {
                                            anInt2547 = 0;
                                        }
                                        if (~anInt2548 <= -1) {
                                            if (~super.anInt2542 >= ~anInt2548) {
                                                anInt2548 = super.anInt2542;
                                            }
                                        }
                                        else {
                                            anInt2548 = 0;
                                        }
                                        int anInt2549 = 64 + n2;
                                        if (k >= 0) {
                                            if (~super.anInt2543 >= ~k) {
                                                k = super.anInt2543;
                                            }
                                        }
                                        else {
                                            k = 0;
                                        }
                                        if (anInt2549 >= 0) {
                                            if (anInt2549 >= super.anInt2542) {
                                                anInt2549 = super.anInt2542;
                                            }
                                        }
                                        else {
                                            anInt2549 = 0;
                                        }
                                        while (k > anInt2547) {
                                            while (~anInt2549 < ~anInt2548) {
                                                super.aByteArrayArrayArray2554[i][anInt2547][anInt2548] = 0;
                                                ++anInt2548;
                                            }
                                            ++anInt2547;
                                        }
                                    }
                                }
                            }
                            else if (array != null) {
                                array[0] = class98_Sub22.readShort((byte)127);
                                array[1] = class98_Sub22.readUShort(false);
                                array[2] = class98_Sub22.readUShort(false);
                                array[3] = class98_Sub22.readUShort(false);
                                array[4] = class98_Sub22.readShort((byte)127);
                            }
                            else {
                                class98_Sub22.anInt3991 += 10;
                            }
                        }
                        else {
                            final int l = class98_Sub22.readUnsignedByte((byte)(-103));
                            if (l <= 0) {
                                continue;
                            }
                            for (int n7 = 0; l > n7; ++n7) {
                                final Class1 class29 = new Class1(ha, class98_Sub22, 2);
                                if (~class29.anInt62 == 0xFFFFFFE0) {
                                    final Class379 method3268 = Class21_Sub1.aClass269_5383.method3268(n ^ 0xFFFFBACE, class98_Sub22.readShort((byte)127));
                                    class29.method166(method3268.anInt3197, method3268.anInt3194, method3268.anInt3193, (byte)(-103), method3268.anInt3195);
                                }
                                if (~ha.method1822() < -1) {
                                    final Class98_Sub5 aClass98_Sub5_55 = class29.aClass98_Sub5_55;
                                    final int n8 = (n3 << -384073015) + aClass98_Sub5_55.method954(7019);
                                    final int n9 = aClass98_Sub5_55.method962(28699) - -(n2 << -1220460727);
                                    final int n10 = n8 >> 1950973833;
                                    final int n11 = n9 >> -130868727;
                                    if (~n10 <= -1 && ~n11 <= -1 && ~n10 > ~super.anInt2543 && ~n11 > ~super.anInt2542) {
                                        aClass98_Sub5_55.method955(n9, (byte)(-123), n8, super.anIntArrayArrayArray2549[class29.anInt57][n10][n11] + -aClass98_Sub5_55.method963((byte)96));
                                        Class64_Sub17.method619(class29);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        class28 = new Class28(class98_Sub22);
                    }
                }
                if (class28 != null) {
                    for (int n12 = 0; n12 < 8; ++n12) {
                        for (int n13 = 0; n13 < 8; ++n13) {
                            final int n14 = (n3 >> -436409085) - -n12;
                            final int n15 = (n2 >> -1019194845) - -n13;
                            if (~n14 <= -1 && super.anInt2543 >> -182199197 > n14 && ~n15 <= -1 && n15 < super.anInt2542 >> -262306781) {
                                Class246_Sub3_Sub3.method3015(n15, n14, (byte)71, class28);
                            }
                        }
                    }
                }
                if (!b && super.aByteArrayArrayArray2554 != null) {
                    for (int n16 = 0; ~n16 > -5; ++n16) {
                        if (super.aByteArrayArrayArray2554[n16] != null) {
                            for (int n17 = 0; n17 < 16; ++n17) {
                                for (int n18 = 0; n18 < 16; ++n18) {
                                    final int n19 = (n3 >> 1076603618) + n17;
                                    final int n20 = (n2 >> 2130564034) - -n18;
                                    if (~n19 <= -1 && n19 < 26 && n20 >= 0 && ~n20 > -27) {
                                        super.aByteArrayArrayArray2554[n16][n19][n20] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ms.R(" + n + ',' + n2 + ',' + n3 + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Interface19 method3583(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            Interface19 interface19 = null;
            if (~n2 == n4) {
                interface19 = (Interface19)Class21_Sub1.method268(n3, n5, n);
            }
            if (~n2 == 0xFFFFFFFE) {
                interface19 = (Interface19)Class101.method1701(n3, n5, n);
            }
            if (n2 == 2) {
                interface19 = (Interface19)Class97.method931(n3, n5, n, (Class305_Sub1.aClass5304 != null) ? Class305_Sub1.aClass5304 : (Class305_Sub1.aClass5304 = method3592("Interface19")));
            }
            if (~n2 == 0xFFFFFFFC) {
                interface19 = (Interface19)Class253.method3177(n3, n5, n);
            }
            return interface19;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ms.S(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final void method3584(final Class243[] array, final int n, final int n2, final byte[] array2, final int n3, final int n4, final int n5, final int n6, final int n7, final ha ha, final int n8) {
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array2);
            int n9 = -1;
            while (true) {
                final int method1208 = class98_Sub22.method1208(3893);
                if (~method1208 == -1) {
                    break;
                }
                n9 += method1208;
                int n10 = 0;
                while (true) {
                    final int smart = class98_Sub22.readSmart(1689622712);
                    if (smart == 0) {
                        break;
                    }
                    n10 += smart - 1;
                    final int n11 = n10 & 0x3F;
                    final int n12 = (n10 & 0xFE7) >> -456824154;
                    final int n13 = n10 >> 1908291884;
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-127));
                    final int n14 = unsignedByte >> 1689510498;
                    final int n15 = unsignedByte & 0x3;
                    if (~n13 != ~n2 || n12 < n5 || ~n12 <= ~(n5 + 8) || ~n11 > ~n6 || n11 >= n6 + 8) {
                        continue;
                    }
                    final Class352 method1209 = Class130.aClass302_1028.method3546(n9, (byte)119);
                    final int n16 = Class292.method3451(n12 & 0x7, n15, false, n11 & 0x7, n7, method1209.sizeY, method1209.sizeX) + n4;
                    final int n17 = Class35.method338(method1209.sizeX, n7, n15, 0x7 & n12, 0x7 & n11, method1209.sizeY, (byte)(-23)) + n3;
                    if (n16 <= 0 || ~n17 >= -1 || ~(-1 + super.anInt2543) >= ~n16 || n17 >= super.anInt2542 - 1) {
                        continue;
                    }
                    Class243 class243 = null;
                    if (!super.aBoolean2544) {
                        int n18 = n;
                        if ((Class281.aByteArrayArrayArray2117[1][n16][n17] & 0x2) == 0x2) {
                            --n18;
                        }
                        if (n18 >= 0) {
                            class243 = array[n18];
                        }
                    }
                    this.method3588(n9, n17, -1, n, false, n16, ha, 0x3 & n7 + n15, n14, n, class243);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ms.P(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n8 + ')');
        }
    }
    
    static final void method3585(final Throwable t, final int n, final String s) {
        try {
            try {
                String s2 = "";
                if (t != null) {
                    s2 = Class13.method222(t, (byte)(-24));
                }
                if (n > -120) {
                    Class305_Sub1.anInt5303 = 20;
                }
                if (s != null) {
                    if (t != null) {
                        s2 += " | ";
                    }
                    s2 += s;
                }
                Class199.method2686(s2, (byte)(-80));
                final String method765 = Class76_Sub9.method765("%23", 4185, "#", Class76_Sub9.method765("%26", 4185, "&", Class76_Sub9.method765("%40", 4185, "@", Class76_Sub9.method765("%3a", 4185, ":", s2))));
                if (Class131.anApplet1038 != null) {
                    final Class143 method766 = Class98_Sub40.aClass88_4192.method866(-108, new URL(Class131.anApplet1038.getCodeBase(), "clienterror.ws?c=" + Class164.anInt1273 + "&u=" + ((Class256_Sub1.aString5157 == null) ? String.valueOf(Class106.aLong904) : Class256_Sub1.aString5157) + "&v1=" + Class88.aString696 + "&v2=" + Class88.aString692 + "&e=" + method765));
                    while (~method766.anInt1163 == -1) {
                        Class246_Sub7.method3131(0, 1L);
                    }
                    if (method766.anInt1163 == 1) {
                        final DataInputStream dataInputStream = (DataInputStream)method766.anObject1162;
                        dataInputStream.read();
                        dataInputStream.close();
                    }
                }
            }
            catch (Exception ex2) {}
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method3586(final int n, final int n2, final int[] array, final int n3, final int n4, final Class98_Sub22 class98_Sub22, final ha ha, final int n5, final boolean b, final int n6, final int n7) {
        try {
            if (!super.aBoolean2544) {
                if (!b) {
                    this.anInt5302 = -44;
                }
                final boolean b2 = false;
                Class28 class28 = null;
                if (array != null) {
                    array[0] = -1;
                }
                final int n8 = (0x7 & n) * 8;
                final int n9 = 8 * (n2 & 0x7);
                while (class98_Sub22.anInt3991 < class98_Sub22.aByteArray3992.length) {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-116));
                    if (unsignedByte == 0) {
                        class28 = new Class28(class98_Sub22);
                    }
                    else if (~unsignedByte == 0xFFFFFFFE) {
                        final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-105));
                        if (~unsignedByte2 >= -1) {
                            continue;
                        }
                        for (int i = 0; i < unsignedByte2; ++i) {
                            final Class1 class29 = new Class1(ha, class98_Sub22, 2);
                            if (class29.anInt62 == 31) {
                                final Class379 method3268 = Class21_Sub1.aClass269_5383.method3268(-37, class98_Sub22.readShort((byte)127));
                                class29.method166(method3268.anInt3197, method3268.anInt3194, method3268.anInt3193, (byte)(-98), method3268.anInt3195);
                            }
                            if (ha.method1822() > 0) {
                                final Class98_Sub5 aClass98_Sub5_55 = class29.aClass98_Sub5_55;
                                final int n10 = aClass98_Sub5_55.method954(7019) >> 945832681;
                                final int n11 = aClass98_Sub5_55.method962(28699) >> 341917193;
                                if (class29.anInt57 == n6 && ~n10 <= ~n8 && n10 < 8 + n8 && ~n11 <= ~n9 && n9 + 8 > n11) {
                                    final int n12 = (n3 << -854995383) + Class98_Sub32.method1433(0xFFF & aClass98_Sub5_55.method962(28699), n5, aClass98_Sub5_55.method954(7019) & 0xFFF, -7175);
                                    final int n13 = n12 >> -1912547319;
                                    final int n14 = (n4 << 1462769929) - -Class107.method1724(7, n5, 0xFFF & aClass98_Sub5_55.method962(28699), 0xFFF & aClass98_Sub5_55.method954(7019));
                                    final int n15 = n14 >> 1721073289;
                                    if (~n13 <= -1 && ~n15 <= -1 && ~super.anInt2543 < ~n13 && super.anInt2542 > n15) {
                                        aClass98_Sub5_55.method955(n14, (byte)(-122), n12, super.anIntArrayArrayArray2549[n6][n13][n15] - aClass98_Sub5_55.method963((byte)72));
                                        Class64_Sub17.method619(class29);
                                    }
                                }
                            }
                        }
                    }
                    else if (~unsignedByte != 0xFFFFFFFD) {
                        if (unsignedByte == 128) {
                            if (array != null) {
                                array[0] = class98_Sub22.readShort((byte)127);
                                array[1] = class98_Sub22.readUShort(!b);
                                array[2] = class98_Sub22.readUShort(false);
                                array[3] = class98_Sub22.readUShort(false);
                                array[4] = class98_Sub22.readShort((byte)127);
                            }
                            else {
                                class98_Sub22.anInt3991 += 10;
                            }
                        }
                        else {
                            if (~unsignedByte != 0xFFFFFF7E) {
                                throw new IllegalStateException("");
                            }
                            if (super.aByteArrayArrayArray2554 == null) {
                                super.aByteArrayArrayArray2554 = new byte[4][][];
                            }
                            for (int n16 = 0; ~n16 > -5; ++n16) {
                                final byte signedByte = class98_Sub22.readSignedByte((byte)(-19));
                                if (~signedByte != -1 || super.aByteArrayArrayArray2554[n7] == null) {
                                    if (signedByte == 1) {
                                        if (super.aByteArrayArrayArray2554[n7] == null) {
                                            super.aByteArrayArrayArray2554[n7] = new byte[1 + super.anInt2543][1 + super.anInt2542];
                                        }
                                        for (int n17 = 0; ~n17 > -65; n17 += 4) {
                                            for (int n18 = 0; ~n18 > -65; n18 += 4) {
                                                final byte signedByte2 = class98_Sub22.readSignedByte((byte)(-19));
                                                if (~n6 <= ~n16) {
                                                    for (int n19 = n17; ~n19 > ~(n17 + 4); ++n19) {
                                                        for (int j = n18; j < n18 + 4; ++j) {
                                                            if (~n8 >= ~n19 && ~(8 + n8) < ~n19 && n9 <= j && ~n9 > ~(n9 + 8)) {
                                                                final int n20 = n3 - -Class107.method1720(0x7 & n19, 0, 0x7 & j, n5);
                                                                final int n21 = Class250.method3166(n19 & 0x7, j & 0x7, n5, (byte)(-122)) + n4;
                                                                if (n20 >= 0 && ~super.anInt2543 < ~n20 && ~n21 <= -1 && ~super.anInt2542 < ~n21) {
                                                                    super.aByteArrayArrayArray2554[n7][n20][n21] = signedByte2;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                else if (n16 <= n6) {
                                    int anInt2543 = n3;
                                    int anInt2544 = n3 + 7;
                                    int anInt2545 = n4;
                                    if (anInt2545 < 0) {
                                        anInt2545 = 0;
                                    }
                                    else if (anInt2545 >= super.anInt2542) {
                                        anInt2545 = super.anInt2542;
                                    }
                                    int anInt2546 = 7 + n4;
                                    if (anInt2543 < 0) {
                                        anInt2543 = 0;
                                    }
                                    else if (anInt2543 >= super.anInt2543) {
                                        anInt2543 = super.anInt2543;
                                    }
                                    if (anInt2544 >= 0) {
                                        if (~super.anInt2543 >= ~anInt2544) {
                                            anInt2544 = super.anInt2543;
                                        }
                                    }
                                    else {
                                        anInt2544 = 0;
                                    }
                                    if (anInt2546 >= 0) {
                                        if (anInt2546 >= super.anInt2542) {
                                            anInt2546 = super.anInt2542;
                                        }
                                    }
                                    else {
                                        anInt2546 = 0;
                                    }
                                    while (~anInt2544 < ~anInt2543) {
                                        while (~anInt2545 > ~anInt2546) {
                                            super.aByteArrayArrayArray2554[n7][anInt2543][anInt2545] = 0;
                                            ++anInt2545;
                                        }
                                        ++anInt2543;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (class28 == null) {
                            class28 = new Class28();
                        }
                        class28.method297(-50, class98_Sub22);
                    }
                }
                if (class28 != null) {
                    Class246_Sub3_Sub3.method3015(n4 >> -243448253, n3 >> -466562909, (byte)31, class28);
                }
                if (!b2 && super.aByteArrayArrayArray2554 != null && super.aByteArrayArrayArray2554[n7] != null) {
                    final int n22 = 7 + n3;
                    final int n23 = n4 + 7;
                    for (int n24 = n3; ~n24 > ~n22; ++n24) {
                        for (int n25 = n4; ~n23 < ~n25; ++n25) {
                            super.aByteArrayArrayArray2554[n7][n24][n25] = 0;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ms.Q(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + n5 + ',' + b + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    static final void method3587(final int anInt6054, final int n, final int n2) {
        try {
            if (n < -1) {
                final Class98_Sub46_Sub17 method2628 = Class185.method2628(n2, -101, 7);
                method2628.method1626((byte)(-103));
                method2628.anInt6054 = anInt6054;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ms.T(" + anInt6054 + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method3588(final int n, final int n2, final int n3, final int n4, final boolean b, final int n5, final ha ha, final int n6, final int n7, final int anInt5302, final Class243 class243) {
        try {
            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)120) != 0 || Class294.method3477(n2, n4, n5, Class115.anInt963, 55)) {
                if (~this.anInt5302 < ~anInt5302) {
                    this.anInt5302 = anInt5302;
                }
                final Class352 method3546 = Class130.aClass302_1028.method3546(n, (byte)119);
                if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056.method634((byte)121) != 0 || !method3546.aBoolean2982) {
                    int n8;
                    int n9;
                    if (~n6 == 0xFFFFFFFE || ~n6 == 0xFFFFFFFC) {
                        n8 = method3546.sizeX;
                        n9 = method3546.sizeY;
                    }
                    else {
                        n8 = method3546.sizeY;
                        n9 = method3546.sizeX;
                    }
                    int n10;
                    int n11;
                    if (~(n5 - -n8) < ~super.anInt2543) {
                        n10 = n5 + 1;
                        n11 = n5;
                    }
                    else {
                        n11 = (n8 >> 1955640673) + n5;
                        n10 = n5 + (n8 + 1 >> 1935606721);
                    }
                    int n12;
                    int n13;
                    if (~super.anInt2542 <= ~(n2 - -n9)) {
                        n12 = (n9 >> -1353300511) + n2;
                        n13 = n2 - -(n9 + 1 >> -730048575);
                    }
                    else {
                        n13 = 1 + n2;
                        n12 = n2;
                    }
                    final s s = Class78.aSArray594[n4];
                    final int n14 = s.method3420(n12, -12639, n11) + (s.method3420(n12, -12639, n10) + s.method3420(n13, -12639, n11) - -s.method3420(n13, -12639, n10)) >> -60292670;
                    final int n15 = (n8 << -882314456) + (n5 << 1219490217);
                    if (b) {
                        this.method3591(null, 36, (byte)(-79), null, null, 13);
                    }
                    final int n16 = (n2 << -1157872503) + (n9 << -1315771672);
                    final boolean b2 = Class98_Sub9.aBoolean3851 && !super.aBoolean2544 && method3546.aBoolean3007;
                    if (method3546.method3858(103)) {
                        Class98_Sub31_Sub4.method1383(null, method3546, n5, n6, 3, n2, anInt5302, null);
                    }
                    final boolean b3 = ~n3 == 0x0 && ~method3546.anInt2941 == 0x0 && method3546.anIntArray2979 == null && method3546.anIntArray2928 == null && !method3546.aBoolean3005 && !method3546.aBoolean2984;
                    if (!Class99.aBoolean838 || ((!Class360.method3909(-4, n7) || method3546.anInt2956 == 1) && (!Class246_Sub4.method3100(n7, (byte)85) || method3546.anInt2956 != 0))) {
                        if (~n7 == 0xFFFFFFE9) {
                            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038.method596((byte)121) != 0 || method3546.anInt2998 != 0 || ~method3546.actionCount == 0xFFFFFFFE || method3546.aBoolean2969) {
                                Interface19 interface19;
                                if (b3) {
                                    final Class246_Sub3_Sub1_Sub1 class246_Sub3_Sub1_Sub1 = (Class246_Sub3_Sub1_Sub1)(interface19 = new Class246_Sub3_Sub1_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n6, b2));
                                    if (class246_Sub3_Sub1_Sub1.method65(!b)) {
                                        class246_Sub3_Sub1_Sub1.method62(ha, 24447);
                                    }
                                }
                                else {
                                    interface19 = new Class246_Sub3_Sub1_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n6, n3);
                                }
                                Class322.method3671(anInt5302, n5, n2, (Class246_Sub3_Sub1)interface19);
                                if (~method3546.actionCount == 0xFFFFFFFE && class243 != null) {
                                    class243.method2946(n5, (byte)(-14), n2);
                                }
                            }
                        }
                        else if (n7 == 10 || n7 == 11) {
                            Class246_Sub3_Sub4_Sub1 class246_Sub3_Sub4_Sub1 = null;
                            int method3547;
                            Interface19 interface20;
                            if (b3) {
                                final Class246_Sub3_Sub4_Sub1 class246_Sub3_Sub4_Sub2 = new Class246_Sub3_Sub4_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n5, -1 + (n8 + n5), n2, -1 + n9 + n2, n7, n6, b2);
                                method3547 = class246_Sub3_Sub4_Sub2.method3027(94);
                                interface20 = class246_Sub3_Sub4_Sub2;
                                class246_Sub3_Sub4_Sub1 = class246_Sub3_Sub4_Sub2;
                            }
                            else {
                                interface20 = new Class246_Sub3_Sub4_Sub5(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n5, -1 + n5 - -n8, n2, -1 + n9 + n2, n7, n6, n3);
                                method3547 = 15;
                            }
                            if (Class222.method2826((Class246_Sub3_Sub4)interface20, false)) {
                                if (class246_Sub3_Sub4_Sub1 != null && class246_Sub3_Sub4_Sub1.method65(true)) {
                                    class246_Sub3_Sub4_Sub1.method62(ha, 24447);
                                }
                                if (method3546.aBoolean2947 && Class98_Sub9.aBoolean3851) {
                                    if (~method3547 < -31) {
                                        method3547 = 30;
                                    }
                                    for (int n17 = 0; ~n17 >= ~n8; ++n17) {
                                        for (int i = 0; i <= n9; ++i) {
                                            s.ka(n5 + n17, n2 + i, method3547);
                                        }
                                    }
                                }
                            }
                            if (method3546.actionCount != 0 && class243 != null) {
                                class243.method2949(0, !method3546.clippingFlag, n5, n9, n8, method3546.walkable, n2);
                            }
                        }
                        else if ((n7 >= 12 && n7 <= 17) || (n7 >= 18 && ~n7 >= -22)) {
                            Interface19 interface21;
                            if (!b3) {
                                interface21 = new Class246_Sub3_Sub4_Sub5(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n5, n8 + (n5 - 1), n2, n9 + (n2 - 1), n7, n6, n3);
                            }
                            else {
                                final Class246_Sub3_Sub4_Sub1 class246_Sub3_Sub4_Sub3 = new Class246_Sub3_Sub4_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n5, -1 + n5 + n8, n2, n2 - (-n9 + 1), n7, n6, b2);
                                if (class246_Sub3_Sub4_Sub3.method65(!b)) {
                                    class246_Sub3_Sub4_Sub3.method62(ha, 24447);
                                }
                                interface21 = class246_Sub3_Sub4_Sub3;
                            }
                            Class222.method2826((Class246_Sub3_Sub4)interface21, false);
                            if (Class98_Sub9.aBoolean3851 && !super.aBoolean2544 && n7 >= 12 && n7 <= 17 && ~n7 != 0xFFFFFFF2 && anInt5302 > 0 && ~method3546.anInt2956 != -1) {
                                super.aByteArrayArrayArray2550[anInt5302][n5][n2] = (byte)Class41.method366(super.aByteArrayArrayArray2550[anInt5302][n5][n2], 4);
                            }
                            if (method3546.actionCount != 0 && class243 != null) {
                                class243.method2949(0, !method3546.clippingFlag, n5, n9, n8, method3546.walkable, n2);
                            }
                        }
                        else if (n7 == 0) {
                            int anInt5303 = method3546.anInt2956;
                            if (Class98_Sub42.aBoolean4218 && method3546.anInt2956 == -1) {
                                anInt5303 = 1;
                            }
                            Interface19 interface22;
                            if (!b3) {
                                interface22 = new Class246_Sub3_Sub3_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, n6, n3);
                            }
                            else {
                                final Class246_Sub3_Sub3_Sub2 class246_Sub3_Sub3_Sub2 = (Class246_Sub3_Sub3_Sub2)(interface22 = new Class246_Sub3_Sub3_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, n6, b2));
                                if (class246_Sub3_Sub3_Sub2.method65(true)) {
                                    class246_Sub3_Sub3_Sub2.method62(ha, 24447);
                                }
                            }
                            Class78.method790(anInt5302, n5, n2, (Class246_Sub3_Sub3)interface22, null);
                            if (~n6 == -1) {
                                if (Class98_Sub9.aBoolean3851 && method3546.aBoolean2947) {
                                    s.ka(n5, n2, 50);
                                    s.ka(n5, 1 + n2, 50);
                                }
                                if (anInt5303 == 1 && !super.aBoolean2544) {
                                    Class239.method2921(n5, anInt5302, n2, method3546.anInt2953, method3546.anInt2986, 1, 8);
                                }
                            }
                            else if (n6 != 1) {
                                if (n6 != 2) {
                                    if (n6 == 3) {
                                        if (Class98_Sub9.aBoolean3851 && method3546.aBoolean2947) {
                                            s.ka(n5, n2, 50);
                                            s.ka(n5 + 1, n2, 50);
                                        }
                                        if (~anInt5303 == 0xFFFFFFFE && !super.aBoolean2544) {
                                            Class239.method2921(n5, anInt5302, n2, method3546.anInt2953, method3546.anInt2986, 2, 8);
                                        }
                                    }
                                }
                                else {
                                    if (Class98_Sub9.aBoolean3851 && method3546.aBoolean2947) {
                                        s.ka(n5 + 1, n2, 50);
                                        s.ka(1 + n5, n2 + 1, 50);
                                    }
                                    if (~anInt5303 == 0xFFFFFFFE && !super.aBoolean2544) {
                                        Class239.method2921(n5 + 1, anInt5302, n2, -method3546.anInt2953, method3546.anInt2986, 1, 8);
                                    }
                                }
                            }
                            else {
                                if (Class98_Sub9.aBoolean3851 && method3546.aBoolean2947) {
                                    s.ka(n5, n2 + 1, 50);
                                    s.ka(n5 + 1, n2 + 1, 50);
                                }
                                if (anInt5303 == 1 && !super.aBoolean2544) {
                                    Class239.method2921(n5, anInt5302, 1 + n2, -method3546.anInt2953, method3546.anInt2986, 2, 8);
                                }
                            }
                            if (method3546.actionCount != 0 && class243 != null) {
                                class243.method2945(n2, method3546.walkable, !method3546.clippingFlag, n6, n5, n7, (byte)122);
                            }
                            if (~method3546.anInt2966 != 0xFFFFFFBF) {
                                Class98_Sub32.method1439(anInt5302, n5, n2, method3546.anInt2966);
                            }
                        }
                        else if (n7 == 1) {
                            Interface19 interface23;
                            if (!b3) {
                                interface23 = new Class246_Sub3_Sub3_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, n6, n3);
                            }
                            else {
                                final Class246_Sub3_Sub3_Sub2 class246_Sub3_Sub3_Sub3 = new Class246_Sub3_Sub3_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, n6, b2);
                                if (class246_Sub3_Sub3_Sub3.method65(!b)) {
                                    class246_Sub3_Sub3_Sub3.method62(ha, 24447);
                                }
                                interface23 = class246_Sub3_Sub3_Sub3;
                            }
                            Class78.method790(anInt5302, n5, n2, (Class246_Sub3_Sub3)interface23, null);
                            if (method3546.aBoolean2947 && Class98_Sub9.aBoolean3851) {
                                if (~n6 == -1) {
                                    s.ka(n5, 1 + n2, 50);
                                }
                                else if (~n6 != 0xFFFFFFFE) {
                                    if (~n6 == 0xFFFFFFFD) {
                                        s.ka(n5 + 1, n2, 50);
                                    }
                                    else if (~n6 == 0xFFFFFFFC) {
                                        s.ka(n5, n2, 50);
                                    }
                                }
                                else {
                                    s.ka(1 + n5, n2 + 1, 50);
                                }
                            }
                            if (~method3546.actionCount != -1 && class243 != null) {
                                class243.method2945(n2, method3546.walkable, !method3546.clippingFlag, n6, n5, n7, (byte)104);
                            }
                        }
                        else if (~n7 == 0xFFFFFFFD) {
                            final int n18 = 0x3 & 1 + n6;
                            Interface19 interface24;
                            Interface19 interface25;
                            if (b3) {
                                final Class246_Sub3_Sub3_Sub2 class246_Sub3_Sub3_Sub4 = new Class246_Sub3_Sub3_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, 4 + n6, b2);
                                final Class246_Sub3_Sub3_Sub2 class246_Sub3_Sub3_Sub5 = new Class246_Sub3_Sub3_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, n18, b2);
                                if (class246_Sub3_Sub3_Sub4.method65(true)) {
                                    class246_Sub3_Sub3_Sub4.method62(ha, 24447);
                                }
                                interface24 = class246_Sub3_Sub3_Sub4;
                                if (class246_Sub3_Sub3_Sub5.method65(true)) {
                                    class246_Sub3_Sub3_Sub5.method62(ha, 24447);
                                }
                                interface25 = class246_Sub3_Sub3_Sub5;
                            }
                            else {
                                interface24 = new Class246_Sub3_Sub3_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, n6 + 4, n3);
                                interface25 = new Class246_Sub3_Sub3_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, n18, n3);
                            }
                            Class78.method790(anInt5302, n5, n2, (Class246_Sub3_Sub3)interface24, (Class246_Sub3_Sub3)interface25);
                            if ((method3546.anInt2956 == 1 || (Class98_Sub42.aBoolean4218 && ~method3546.anInt2956 == 0x0)) && !super.aBoolean2544) {
                                if (~n6 == -1) {
                                    Class239.method2921(n5, anInt5302, n2, method3546.anInt2953, method3546.anInt2986, 1, 8);
                                    Class239.method2921(n5, anInt5302, n2 + 1, method3546.anInt2953, method3546.anInt2986, 2, 8);
                                }
                                else if (n6 != 1) {
                                    if (~n6 != 0xFFFFFFFD) {
                                        if (~n6 == 0xFFFFFFFC) {
                                            Class239.method2921(n5, anInt5302, n2, method3546.anInt2953, method3546.anInt2986, 1, 8);
                                            Class239.method2921(n5, anInt5302, n2, method3546.anInt2953, method3546.anInt2986, 2, 8);
                                        }
                                    }
                                    else {
                                        Class239.method2921(n5 + 1, anInt5302, n2, method3546.anInt2953, method3546.anInt2986, 1, 8);
                                        Class239.method2921(n5, anInt5302, n2, method3546.anInt2953, method3546.anInt2986, 2, 8);
                                    }
                                }
                                else {
                                    Class239.method2921(1 + n5, anInt5302, n2, method3546.anInt2953, method3546.anInt2986, 1, 8);
                                    Class239.method2921(n5, anInt5302, n2 + 1, method3546.anInt2953, method3546.anInt2986, 2, 8);
                                }
                            }
                            if (~method3546.actionCount != -1 && class243 != null) {
                                class243.method2945(n2, method3546.walkable, !method3546.clippingFlag, n6, n5, n7, (byte)70);
                            }
                            if (~method3546.anInt2966 != 0xFFFFFFBF) {
                                Class98_Sub32.method1439(anInt5302, n5, n2, method3546.anInt2966);
                            }
                        }
                        else if (n7 == 3) {
                            Interface19 interface26;
                            if (!b3) {
                                interface26 = new Class246_Sub3_Sub3_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, n6, n3);
                            }
                            else {
                                final Class246_Sub3_Sub3_Sub2 class246_Sub3_Sub3_Sub6 = (Class246_Sub3_Sub3_Sub2)(interface26 = new Class246_Sub3_Sub3_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n7, n6, b2));
                                if (class246_Sub3_Sub3_Sub6.method65(true)) {
                                    class246_Sub3_Sub3_Sub6.method62(ha, 24447);
                                }
                            }
                            Class78.method790(anInt5302, n5, n2, (Class246_Sub3_Sub3)interface26, null);
                            if (method3546.aBoolean2947 && Class98_Sub9.aBoolean3851) {
                                if (n6 != 0) {
                                    if (n6 == 1) {
                                        s.ka(1 + n5, 1 + n2, 50);
                                    }
                                    else if (~n6 == 0xFFFFFFFD) {
                                        s.ka(1 + n5, n2, 50);
                                    }
                                    else if (n6 == 3) {
                                        s.ka(n5, n2, 50);
                                    }
                                }
                                else {
                                    s.ka(n5, 1 + n2, 50);
                                }
                            }
                            if (~method3546.actionCount != -1 && class243 != null) {
                                class243.method2945(n2, method3546.walkable, !method3546.clippingFlag, n6, n5, n7, (byte)126);
                            }
                        }
                        else if (n7 == 9) {
                            Interface19 interface27;
                            if (b3) {
                                final Class246_Sub3_Sub4_Sub1 class246_Sub3_Sub4_Sub4 = (Class246_Sub3_Sub4_Sub1)(interface27 = new Class246_Sub3_Sub4_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n5, n5, n2, n2, n7, n6, b2));
                                if (class246_Sub3_Sub4_Sub4.method65(true)) {
                                    class246_Sub3_Sub4_Sub4.method62(ha, 24447);
                                }
                            }
                            else {
                                interface27 = new Class246_Sub3_Sub4_Sub5(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n5, n8 + n5 - 1, n2, -1 + n9 + n2, n7, n6, n3);
                            }
                            Class222.method2826((Class246_Sub3_Sub4)interface27, false);
                            if (~method3546.anInt2956 == 0xFFFFFFFE && !super.aBoolean2544) {
                                int n19;
                                if ((0x1 & n6) == 0x0) {
                                    n19 = 8;
                                }
                                else {
                                    n19 = 16;
                                }
                                Class239.method2921(n5, anInt5302, n2, 0, method3546.anInt2986, n19, 8);
                            }
                            if (~method3546.actionCount != -1 && class243 != null) {
                                class243.method2949(0, !method3546.clippingFlag, n5, n9, n8, method3546.walkable, n2);
                            }
                            if (~method3546.anInt2966 != 0xFFFFFFBF) {
                                Class98_Sub32.method1439(anInt5302, n5, n2, method3546.anInt2966);
                            }
                        }
                        else if (n7 == 4) {
                            Interface19 interface28;
                            if (!b3) {
                                interface28 = new Class246_Sub3_Sub5_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, 0, 0, n7, n6, n3);
                            }
                            else {
                                final Class246_Sub3_Sub5_Sub2 class246_Sub3_Sub5_Sub2 = new Class246_Sub3_Sub5_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, 0, 0, n7, n6);
                                if (class246_Sub3_Sub5_Sub2.method65(!b)) {
                                    class246_Sub3_Sub5_Sub2.method62(ha, 24447);
                                }
                                interface28 = class246_Sub3_Sub5_Sub2;
                            }
                            Class246_Sub3_Sub1.method2995(anInt5302, n5, n2, (Class246_Sub3_Sub5)interface28, null);
                        }
                        else if (~n7 == 0xFFFFFFFA) {
                            int n20 = 65;
                            final Interface19 interface29 = (Interface19)Class21_Sub1.method268(anInt5302, n5, n2);
                            if (interface29 != null) {
                                n20 = 1 + Class130.aClass302_1028.method3546(interface29.method64(30472), (byte)119).anInt2966;
                            }
                            Interface19 interface30;
                            if (!b3) {
                                interface30 = new Class246_Sub3_Sub5_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n20 * Class351.anIntArray2923[n6], n20 * Class113.anIntArray951[n6], n7, n6, n3);
                            }
                            else {
                                final Class246_Sub3_Sub5_Sub2 class246_Sub3_Sub5_Sub3 = new Class246_Sub3_Sub5_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, Class351.anIntArray2923[n6] * n20, n20 * Class113.anIntArray951[n6], n7, n6);
                                if (class246_Sub3_Sub5_Sub3.method65(true)) {
                                    class246_Sub3_Sub5_Sub3.method62(ha, 24447);
                                }
                                interface30 = class246_Sub3_Sub5_Sub3;
                            }
                            Class246_Sub3_Sub1.method2995(anInt5302, n5, n2, (Class246_Sub3_Sub5)interface30, null);
                        }
                        else if (n7 == 6) {
                            int n21 = 33;
                            final Interface19 interface31 = (Interface19)Class21_Sub1.method268(anInt5302, n5, n2);
                            if (interface31 != null) {
                                n21 = 1 + Class130.aClass302_1028.method3546(interface31.method64(30472), (byte)119).anInt2966 / 2;
                            }
                            Interface19 interface32;
                            if (b3) {
                                final Class246_Sub3_Sub5_Sub2 class246_Sub3_Sub5_Sub4 = (Class246_Sub3_Sub5_Sub2)(interface32 = new Class246_Sub3_Sub5_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, Class351.anIntArray2923[n6] * n21, Class113.anIntArray951[n6] * n21, n7, 4 + n6));
                                if (class246_Sub3_Sub5_Sub4.method65(true)) {
                                    class246_Sub3_Sub5_Sub4.method62(ha, 24447);
                                }
                            }
                            else {
                                interface32 = new Class246_Sub3_Sub5_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, Class64_Sub18.anIntArray3688[n6] * n21, n21 * Class78.anIntArray595[n6], n7, 4 + n6, n3);
                            }
                            Class246_Sub3_Sub1.method2995(anInt5302, n5, n2, (Class246_Sub3_Sub5)interface32, null);
                        }
                        else if (n7 == 7) {
                            final int n22 = 0x3 & n6 + 2;
                            Interface19 interface33;
                            if (!b3) {
                                interface33 = new Class246_Sub3_Sub5_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, 0, 0, n7, 4 + n22, n3);
                            }
                            else {
                                final Class246_Sub3_Sub5_Sub2 class246_Sub3_Sub5_Sub5 = (Class246_Sub3_Sub5_Sub2)(interface33 = new Class246_Sub3_Sub5_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, 0, 0, n7, 4 + n22));
                                if (class246_Sub3_Sub5_Sub5.method65(true)) {
                                    class246_Sub3_Sub5_Sub5.method62(ha, 24447);
                                }
                            }
                            Class246_Sub3_Sub1.method2995(anInt5302, n5, n2, (Class246_Sub3_Sub5)interface33, null);
                        }
                        else if (~n7 == 0xFFFFFFF7) {
                            final int n23 = 0x3 & 2 + n6;
                            int n24 = 33;
                            final Interface19 interface34 = (Interface19)Class21_Sub1.method268(anInt5302, n5, n2);
                            if (interface34 != null) {
                                n24 = Class130.aClass302_1028.method3546(interface34.method64(30472), (byte)119).anInt2966 / 2 + 1;
                            }
                            Interface19 interface35;
                            Interface19 interface36;
                            if (!b3) {
                                final Class246_Sub3_Sub5_Sub1 class246_Sub3_Sub5_Sub6 = new Class246_Sub3_Sub5_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, Class64_Sub18.anIntArray3688[n6] * n24, Class78.anIntArray595[n6] * n24, n7, 4 + n6, n3);
                                final Class246_Sub3_Sub5_Sub1 class246_Sub3_Sub5_Sub7 = new Class246_Sub3_Sub5_Sub1(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, 0, 0, n7, n23 + 4, n3);
                                interface35 = class246_Sub3_Sub5_Sub6;
                                interface36 = class246_Sub3_Sub5_Sub7;
                            }
                            else {
                                final Class246_Sub3_Sub5_Sub2 class246_Sub3_Sub5_Sub8 = new Class246_Sub3_Sub5_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, n24 * Class64_Sub18.anIntArray3688[n6], n24 * Class78.anIntArray595[n6], n7, 4 + n6);
                                final Class246_Sub3_Sub5_Sub2 class246_Sub3_Sub5_Sub9 = new Class246_Sub3_Sub5_Sub2(ha, method3546, anInt5302, n4, n15, n14, n16, super.aBoolean2544, 0, 0, n7, 4 + n23);
                                if (class246_Sub3_Sub5_Sub8.method65(true)) {
                                    class246_Sub3_Sub5_Sub8.method62(ha, 24447);
                                }
                                if (class246_Sub3_Sub5_Sub9.method65(true)) {
                                    class246_Sub3_Sub5_Sub9.method62(ha, 24447);
                                }
                                interface35 = class246_Sub3_Sub5_Sub8;
                                interface36 = class246_Sub3_Sub5_Sub9;
                            }
                            Class246_Sub3_Sub1.method2995(anInt5302, n5, n2, (Class246_Sub3_Sub5)interface35, (Class246_Sub3_Sub5)interface36);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ms.AA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n6 + ',' + n7 + ',' + anInt5302 + ',' + ((class243 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3589(final boolean b, final byte b2, final ha ha) {
        try {
            Class262.method3213();
            if (b2 == 105) {
                if (!b) {
                    if (super.anInt2547 > 1) {
                        for (int i = 0; i < super.anInt2543; ++i) {
                            for (int n = 0; ~super.anInt2542 < ~n; ++n) {
                                if (~(0x2 & Class281.aByteArrayArrayArray2117[1][i][n]) == 0xFFFFFFFD) {
                                    Class206.method2723(i, n);
                                }
                            }
                        }
                    }
                    for (int n2 = 0; super.anInt2547 > n2; ++n2) {
                        for (int n3 = 0; ~n3 >= ~super.anInt2542; ++n3) {
                            for (int j = 0; j <= super.anInt2543; ++j) {
                                if ((super.aByteArrayArrayArray2550[n2][j][n3] & 0x4) != 0x0) {
                                    int k = j;
                                    int l = j;
                                    int n4 = n3;
                                    int n5 = n3;
                                    while (~n4 < -1 && (0x4 & super.aByteArrayArrayArray2550[n2][j][-1 + n4]) != 0x0) {
                                        if (~(-n4 + n5) <= -11) {
                                            break;
                                        }
                                        --n4;
                                    }
                                    while (n5 < super.anInt2542 && ~(0x4 & super.aByteArrayArrayArray2550[n2][j][n5 + 1]) != -1) {
                                        if (~(-n4 + n5) <= -11) {
                                            break;
                                        }
                                        ++n5;
                                    }
                                Label_0333:
                                    while (k > 0) {
                                        if (~(l + -k) <= -11) {
                                            break;
                                        }
                                        for (int n6 = n4; n6 <= n5; ++n6) {
                                            if ((super.aByteArrayArrayArray2550[n2][-1 + k][n6] & 0x4) == 0x0) {
                                                break Label_0333;
                                            }
                                        }
                                        --k;
                                    }
                                Label_0408:
                                    while (l < super.anInt2543 && ~(l + -k) > -11) {
                                        for (int n7 = n4; ~n7 >= ~n5; ++n7) {
                                            if (~(super.aByteArrayArrayArray2550[n2][1 + l][n7] & 0x4) == -1) {
                                                break Label_0408;
                                            }
                                        }
                                        ++l;
                                    }
                                    if (~((1 + (-k + l)) * (n5 + -n4 + 1)) <= -5) {
                                        final int n8 = super.anIntArrayArrayArray2549[n2][k][n4];
                                        Class98_Sub46_Sub10.method1565(k << 361291369, 512 + (n5 << -91630135), false, 4, n2, 512 + (l << -2021985079), n8, n4 << 467375497, n8);
                                        for (int n9 = k; l >= n9; ++n9) {
                                            for (int n10 = n4; n10 <= n5; ++n10) {
                                                super.aByteArrayArrayArray2550[n2][n9][n10] = (byte)Class202.method2702(super.aByteArrayArrayArray2550[n2][n9][n10], -5);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Class284_Sub1_Sub2.method3371(31398);
                }
                super.aByteArrayArrayArray2550 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ms.BA(" + b + ',' + b2 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3590(final int n, final int n2, final ha ha, final Class243 class243, final int n3, final int n4, final int n5) {
        try {
            final Interface19 method3583 = this.method3583(n, n4, n2, -1, n5);
            if (method3583 != null) {
                final Class352 method3584 = Class130.aClass302_1028.method3546(method3583.method64(30472), (byte)119);
                final int method3585 = method3583.method63((byte)20);
                final int method3586 = method3583.method66(4657);
                if (method3584.method3858(95)) {
                    Class312.method3620(method3584, -22015, n, n2, n5);
                }
                if (method3583.method65(true)) {
                    method3583.method67(-25163, ha);
                }
                if (~n4 == -1) {
                    Class373_Sub1_Sub1.method3972(n2, n5, n);
                    if (method3584.actionCount != 0) {
                        class243.method2937(method3585, method3584.walkable, (byte)84, !method3584.clippingFlag, n5, method3586, n);
                    }
                    if (method3584.anInt2956 == 1) {
                        if (method3586 != 0) {
                            if (~method3586 == 0xFFFFFFFE) {
                                Class100.method1692(2, n2, n5, 1 + n, 64);
                            }
                            else if (method3586 != 2) {
                                if (method3586 == 3) {
                                    Class100.method1692(2, n2, n5, n, n3 + 63);
                                }
                            }
                            else {
                                Class100.method1692(1, n2, n5 + 1, n, n3 ^ 0x41);
                            }
                        }
                        else {
                            Class100.method1692(1, n2, n5, n, 64);
                        }
                    }
                }
                else if (~n4 == 0xFFFFFFFE) {
                    Class218.method2807(n2, n5, n);
                }
                else if (~n4 != 0xFFFFFFFD) {
                    if (n4 == 3) {
                        Class98_Sub46_Sub14.method1602(n2, n5, n);
                        if (method3584.actionCount == 1) {
                            class243.method2948(false, n5, n);
                        }
                    }
                }
                else {
                    Class98_Sub18.method1162(n2, n5, n, (Class305_Sub1.aClass5304 != null) ? Class305_Sub1.aClass5304 : (Class305_Sub1.aClass5304 = method3592("Interface19")));
                    if (~method3584.actionCount != -1 && ~super.anInt2543 < ~(n5 - -method3584.sizeY) && ~(n + method3584.sizeY) > ~super.anInt2542 && ~(method3584.sizeX + n5) > ~super.anInt2543 && super.anInt2542 > n - -method3584.sizeX) {
                        class243.method2951(method3584.sizeY, method3584.walkable, method3586, method3584.sizeX, n, !method3584.clippingFlag, 131072, n5);
                    }
                    if (~method3585 == 0xFFFFFFF6) {
                        if ((method3586 & 0x1) == 0x0) {
                            Class100.method1692(8, n2, n5, n, 64);
                        }
                        else {
                            Class100.method1692(16, n2, n5, n, 64);
                        }
                    }
                }
            }
            if (n3 != 1) {
                Class305_Sub1.anInt5303 = 81;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ms.W(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((class243 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final void method3591(final Class243[] array, final int n, final byte b, final byte[] array2, final ha ha, final int n2) {
        try {
            if (b >= -13) {
                this.method3582(-109, 44, -116, null, null, null);
            }
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array2);
            int n3 = -1;
            while (true) {
                final int method1208 = class98_Sub22.method1208(3893);
                if (~method1208 == -1) {
                    break;
                }
                n3 += method1208;
                int n4 = 0;
                while (true) {
                    final int smart = class98_Sub22.readSmart(1689622712);
                    if (smart == 0) {
                        break;
                    }
                    n4 += -1 + smart;
                    final int n5 = 0x3F & n4;
                    final int n6 = (0xFEA & n4) >> -1921081114;
                    final int n7 = n4 >> 1860692172;
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)50);
                    final int n8 = unsignedByte >> 175799586;
                    final int n9 = 0x3 & unsignedByte;
                    final int n10 = n2 + n6;
                    final int n11 = n5 + n;
                    if (~n10 >= -1 || ~n11 >= -1 || ~n10 <= ~(super.anInt2543 - 1) || -1 + super.anInt2542 <= n11) {
                        continue;
                    }
                    Class243 class243 = null;
                    if (!super.aBoolean2544) {
                        int n12 = n7;
                        if ((Class281.aByteArrayArrayArray2117[1][n10][n11] & 0x2) == 0x2) {
                            --n12;
                        }
                        if (~n12 <= -1) {
                            class243 = array[n12];
                        }
                    }
                    this.method3588(n3, n11, -1, n7, false, n10, ha, n9, n8, n7, class243);
                }
            }
        }
        catch (Exception ex) {
            throw Class64_Sub27.method667(ex, "ms.U(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static Class method3592(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class305_Sub1.anInt5303 = 0;
    }
}
