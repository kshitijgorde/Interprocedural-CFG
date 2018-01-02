import jaggl.OpenGL;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class60
{
    int anInt470;
    static Class85 aClass85_471;
    int anInt472;
    int[] anIntArray473;
    static Class105 aClass105_474;
    static long[] aLongArray475;
    static IncomingOpcode aClass58_476;
    
    private final void method532(final byte b, final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (b < 1) {
                method535(101, 11, -47, -9, null, null, null, null, null, null, -9, (byte)(-87), 72, -113, false, true, -44, -71, false);
            }
            if (~n == 0xFFFFFFFE) {
                this.anInt470 = class98_Sub22.readShort((byte)127);
            }
            else if (n != 2) {
                if (n == 3) {
                    this.anInt472 = class98_Sub22.readUnsignedByte((byte)(-101));
                }
            }
            else {
                this.anIntArray473 = new int[class98_Sub22.readUnsignedByte((byte)(-101))];
                for (int i = 0; i < this.anIntArray473.length; ++i) {
                    this.anIntArray473[i] = class98_Sub22.readShort((byte)127);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eba.A(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method533(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (n != 0) {
                this.method533(null, 76);
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-120));
                if (unsignedByte == 0) {
                    break;
                }
                this.method532((byte)58, class98_Sub22, unsignedByte);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eba.C(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method534(final byte b) {
        try {
            Class60.aClass85_471 = null;
            if (b != -63) {
                Class60.aLongArray475 = null;
            }
            Class60.aClass58_476 = null;
            Class60.aClass105_474 = null;
            Class60.aLongArray475 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eba.D(" + b + ')');
        }
    }
    
    static final void method535(final int n, final int anInt1018, final int anInt1019, final int anInt1020, final byte[][][] array, final int[] anIntArray1044, final int[] anIntArray1045, final int[] anIntArray1046, final int[] anIntArray1047, final int[] anIntArray1048, final int n2, final byte b, final int n3, final int n4, final boolean b2, final boolean aBoolean2526, final int n5, final int n6, final boolean b3) {
        Class135.aBoolean1052 = true;
        Class348.aBoolean2914 = (Class98_Sub10_Sub30.aHa5709.method1822() > 0);
        Class302.aBoolean2526 = aBoolean2526;
        Class241.anInt1845 = anInt1018 >> Class151_Sub8.anInt5015;
        Class64_Sub26.anInt3714 = anInt1020 >> Class151_Sub8.anInt5015;
        Class127.anInt1018 = anInt1018;
        Class98_Sub48.anInt4280 = anInt1020;
        Class42_Sub1_Sub1.anInt6208 = anInt1019;
        Class306.anInt2561 = Class241.anInt1845 - Class259.anInt1959;
        if (Class306.anInt2561 < 0) {
            Class67.anInt521 = -Class306.anInt2561;
            Class306.anInt2561 = 0;
        }
        else {
            Class67.anInt521 = 0;
        }
        OutgoingOpcode.anInt1318 = Class64_Sub26.anInt3714 - Class259.anInt1959;
        if (OutgoingOpcode.anInt1318 < 0) {
            Class98_Sub37.anInt4184 = -OutgoingOpcode.anInt1318;
            OutgoingOpcode.anInt1318 = 0;
        }
        else {
            Class98_Sub37.anInt4184 = 0;
        }
        Class21_Sub2.anInt5388 = Class241.anInt1845 + Class259.anInt1959;
        if (Class21_Sub2.anInt5388 > Class366.anInt3112) {
            Class21_Sub2.anInt5388 = Class366.anInt3112;
        }
        Class32.anInt303 = Class64_Sub26.anInt3714 + Class259.anInt1959;
        if (Class32.anInt303 > Class64_Sub9.anInt3662) {
            Class32.anInt303 = Class64_Sub9.anInt3662;
        }
        final boolean[][] aBooleanArrayArray551 = Class74.aBooleanArrayArray551;
        final boolean[][] aBooleanArrayArray552 = Class319.aBooleanArrayArray2702;
        if (Class302.aBoolean2526) {
            for (int i = 0; i < Class259.anInt1959 + Class259.anInt1959 + 2; ++i) {
                int n7 = 0;
                int n8 = 0;
                for (int j = 0; j < Class259.anInt1959 + Class259.anInt1959 + 2; ++j) {
                    if (j > 1) {
                        Class347.anIntArray2906[j - 2] = n7;
                    }
                    n7 = n8;
                    final int n9 = Class241.anInt1845 - Class259.anInt1959 + i;
                    final int n10 = Class64_Sub26.anInt3714 - Class259.anInt1959 + j;
                    if (n9 >= 0 && n10 >= 0 && n9 < Class366.anInt3112 && n10 < Class64_Sub9.anInt3662) {
                        final int n11 = n9 << Class151_Sub8.anInt5015;
                        final int n12 = n10 << Class151_Sub8.anInt5015;
                        final int n13 = Class98_Sub46_Sub2_Sub2.aSArray6298[Class98_Sub46_Sub2_Sub2.aSArray6298.length - 1].method3420(n10, -12639, n9) - (1000 << Class151_Sub8.anInt5015 - 7);
                        final int n14 = (Class81.aSArray618 != null) ? (Class81.aSArray618[0].method3420(n10, -12639, n9) + r_Sub2.anInt6333) : (Class98_Sub46_Sub2_Sub2.aSArray6298[0].method3420(n10, -12639, n9) + r_Sub2.anInt6333);
                        n8 = ((n5 >= 0) ? Class98_Sub10_Sub30.aHa5709.r(n11, n13, n12, n11, n14, n12, n5) : Class98_Sub10_Sub30.aHa5709.JA(n11, n13, n12, n11, n14, n12));
                        Class319.aBooleanArrayArray2702[i][j] = (n8 == 0);
                    }
                    else {
                        n8 = -1;
                        Class319.aBooleanArrayArray2702[i][j] = false;
                    }
                    if (i > 0 && j > 0) {
                        Class74.aBooleanArrayArray551[i - 1][j - 1] = ((Class347.anIntArray2906[j - 1] & Class347.anIntArray2906[j] & n7 & n8) == 0x0);
                    }
                }
                Class347.anIntArray2906[Class259.anInt1959 + Class259.anInt1959] = n7;
                Class347.anIntArray2906[Class259.anInt1959 + Class259.anInt1959 + 1] = n8;
            }
            if (n5 >= 0) {
                Class135.aBoolean1052 = false;
            }
            else {
                Class132.anIntArray1044 = anIntArray1044;
                Class288.anIntArray3376 = anIntArray1045;
                Class98_Sub46_Sub10.anIntArray6015 = anIntArray1046;
                Class98_Sub10_Sub10.anIntArray5589 = anIntArray1047;
                Class364.anIntArray3102 = anIntArray1048;
                Class340.method3802(Class98_Sub10_Sub30.aHa5709, n2, (byte)(-59));
            }
        }
        else {
            if (aa_Sub1.aBooleanArrayArray3560 == null) {
                aa_Sub1.aBooleanArrayArray3560 = new boolean[Class366.anInt3112 + Class366.anInt3112 + 1][Class64_Sub9.anInt3662 + Class366.anInt3112 + 1];
            }
            for (int k = 0; k < aa_Sub1.aBooleanArrayArray3560.length; ++k) {
                for (int l = 0; l < aa_Sub1.aBooleanArrayArray3560[0].length; ++l) {
                    aa_Sub1.aBooleanArrayArray3560[k][l] = true;
                }
            }
            Class319.aBooleanArrayArray2702 = aa_Sub1.aBooleanArrayArray3560;
            Class74.aBooleanArrayArray551 = aa_Sub1.aBooleanArrayArray3560;
            Class306.anInt2561 = 0;
            OutgoingOpcode.anInt1318 = 0;
            Class21_Sub2.anInt5388 = Class366.anInt3112;
            Class32.anInt303 = Class64_Sub9.anInt3662;
            Class135.aBoolean1052 = false;
        }
        Class64_Sub7.method580(Class98_Sub10_Sub30.aHa5709, 59);
        if (!Class98_Sub10_Sub27.aClass84_5692.aBoolean637) {
            final Class218 aClass218_635 = Class98_Sub10_Sub27.aClass84_5692.aClass218_635;
            for (Class246_Sub1 class246_Sub1 = (Class246_Sub1)aClass218_635.method2803((byte)15); class246_Sub1 != null; class246_Sub1 = (Class246_Sub1)aClass218_635.method2809(false)) {
                class246_Sub1.method2965((byte)(-37));
                Class35.method333(class246_Sub1, 84);
            }
        }
        if (Class348.aBoolean2914) {
            for (int n15 = 0; n15 < Class226.anInt1705; ++n15) {
                Class98_Sub10_Sub31.aClass1Array5717[n15].method161(b2, n, 71);
            }
        }
        if (Class375.aBoolean3170) {
            Class302.anIntArray2521 = Class98_Sub10_Sub30.aHa5709.Y();
            Class98_Sub10_Sub30.aHa5709.K(Class98_Sub16.anIntArray3933);
            final int n16 = (Class98_Sub16.anIntArray3933[2] - Class98_Sub16.anIntArray3933[0]) / Class18.anInt212;
            for (int n17 = 0; n17 < Class18.anInt212 - 1; ++n17) {
                s.anIntArray2205[n17] = n16 * (n17 + 1) + Class15.anIntArray182[n17];
            }
            for (int n18 = 0; n18 < Class98_Sub46_Sub5.aClass174Array5970.length; ++n18) {
                Class98_Sub46_Sub5.aClass174Array5970[n18].method2566();
            }
        }
        if (Class252.aClass172ArrayArrayArray1927 != null) {
            if (Class375.aBoolean3170) {
                Class69.method701(0);
            }
            Class248.method3158(true);
            Class98_Sub10_Sub30.aHa5709.ra(-1, 1583160, 40, 127);
            Class69.method700(true, array, n2, b, n5, n6, b3);
            if (Class375.aBoolean3170) {
                Class9.method194();
            }
            Class98_Sub10_Sub30.aHa5709.pa();
            Class248.method3158(false);
        }
        Class69.method700(false, array, n2, b, n5, n6, b3);
        if (Class375.aBoolean3170) {
            for (int n19 = 0; n19 < Class364.anInt3103; ++n19) {
                Class64_Sub12.aBooleanArrayArrayArray3673[n19] = Class34.aBooleanArrayArrayArray325[n19];
            }
            Class69.method701(0);
            for (int n20 = 0; n20 < Class98_Sub46_Sub5.aClass174Array5970.length; ++n20) {
                Class98_Sub46_Sub5.aClass174Array5970[n20].method2566();
            }
        }
        if (Class375.aBoolean3170) {
            Class9.method194();
            for (int n21 = 0; n21 < Class364.anInt3103; ++n21) {
                Class34.aBooleanArrayArrayArray325[n21] = Class64_Sub12.aBooleanArrayArrayArray3673[n21];
            }
            if (Class294.anInt2407 == 2) {
                if (Class98_Sub10_Sub39.aLongArray5772[0] < Class98_Sub10_Sub39.aLongArray5772[1]) {
                    if (s.anIntArray2205[0] + Class15.anIntArray182[0] > Class98_Sub16.anIntArray3933[0]) {
                        final int[] anIntArray1049 = Class15.anIntArray182;
                        final int n22 = 0;
                        ++anIntArray1049[n22];
                    }
                }
                else if (Class98_Sub10_Sub39.aLongArray5772[0] > Class98_Sub10_Sub39.aLongArray5772[1] && s.anIntArray2205[0] + Class15.anIntArray182[0] < Class98_Sub16.anIntArray3933[2]) {
                    final int[] anIntArray1050 = Class15.anIntArray182;
                    final int n23 = 0;
                    --anIntArray1050[n23];
                }
            }
        }
        if (!Class302.aBoolean2526) {
            Class74.aBooleanArrayArray551 = aBooleanArrayArray551;
            Class319.aBooleanArrayArray2702 = aBooleanArrayArray552;
        }
        Class208.method2767();
    }
    
    static final ha method536(final int n, final Class207 class207, final d d, final int n2, final Canvas canvas) {
        try {
            if (!Class319.method3660(true)) {
                throw new RuntimeException("");
            }
            if (!Class134_Sub1.method2246("jaggl", (byte)(-36))) {
                throw new RuntimeException("");
            }
            final OpenGL openGL = new OpenGL();
            final long init = openGL.init(canvas, 8, 8, 8, 24, n2, n);
            if (init == 0L) {
                throw new RuntimeException("");
            }
            final ha_Sub3_Sub2 ha_Sub3_Sub2 = new ha_Sub3_Sub2(openGL, canvas, init, d, class207, n);
            ha_Sub3_Sub2.method1965(true);
            return ha_Sub3_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eba.E(" + n + ',' + ((class207 != null) ? "{...}" : "null") + ',' + ((d != null) ? "{...}" : "null") + ',' + n2 + ',' + ((canvas != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class60() {
        this.anInt470 = -1;
        this.anInt472 = -1;
    }
    
    static {
        Class60.aClass85_471 = new Class85(4, 4);
        Class60.aClass105_474 = new Class105("", 13);
        Class60.aClass58_476 = new IncomingOpcode(27, 6);
    }
}
