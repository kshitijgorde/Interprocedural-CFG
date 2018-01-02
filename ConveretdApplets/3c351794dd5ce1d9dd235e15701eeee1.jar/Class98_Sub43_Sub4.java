import java.awt.Component;
import java.awt.Color;
import jagtheora.ogg.OggStreamState;
import jagtheora.ogg.OggPacket;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub43_Sub4 extends Class98_Sub43
{
    private int anInt5930;
    private String aString5931;
    static String[] aStringArray5932;
    private String aString5933;
    static short aShort5934;
    private float aFloat5935;
    private int anInt5936;
    private String aString5937;
    static int anInt5938;
    private float aFloat5939;
    static float[] aFloatArray5940;
    
    final String method1503(final int n) {
        try {
            if (n != 22875) {
                this.anInt5936 = 92;
            }
            return this.aString5937;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.G(" + n + ')');
        }
    }
    
    static final void method1504(final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1, final int n) {
        try {
            if (n != -16255) {
                method1508(-54, -22, null);
            }
            for (Class98_Sub42 class98_Sub42 = (Class98_Sub42)Class358.aClass148_3032.method2418(n + 16287); class98_Sub42 != null; class98_Sub42 = (Class98_Sub42)Class358.aClass148_3032.method2417(n ^ 0xFFFFC0DB)) {
                if (class98_Sub42.aClass246_Sub3_Sub4_Sub2_Sub1_4209 == class246_Sub3_Sub4_Sub2_Sub1) {
                    if (class98_Sub42.aClass98_Sub31_Sub5_4232 != null) {
                        Class81.aClass98_Sub31_Sub3_619.method1374(class98_Sub42.aClass98_Sub31_Sub5_4232);
                        class98_Sub42.aClass98_Sub31_Sub5_4232 = null;
                    }
                    class98_Sub42.method942(116);
                    break;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.D(" + ((class246_Sub3_Sub4_Sub2_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void method1482(final OggPacket oggPacket, final boolean b) {
        try {
            if (super.anInt4240 <= 0 || "SUB".equals(this.aString5931)) {
                final Class98_Sub22 class98_Sub22 = new Class98_Sub22(oggPacket.getData());
                if (b) {
                    method1510(-72);
                }
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)27);
                if (super.anInt4240 > 8) {
                    if (unsignedByte == 0) {
                        final long method1189 = class98_Sub22.method1189((byte)(-53));
                        final long method1190 = class98_Sub22.method1189((byte)(-63));
                        final long method1191 = class98_Sub22.method1189((byte)(-110));
                        if (method1189 < 0L || ~method1190 > -1L || method1191 < 0L || ~method1191 < ~method1189) {
                            throw new IllegalStateException();
                        }
                        this.aFloat5939 = this.anInt5936 * method1189 / this.anInt5930;
                        this.aFloat5935 = this.anInt5936 * (method1189 - -method1190) / this.anInt5930;
                        final int method1192 = class98_Sub22.method1202((byte)(-75));
                        if (~method1192 > -1 || method1192 > class98_Sub22.aByteArray3992.length - class98_Sub22.anInt3991) {
                            throw new IllegalStateException();
                        }
                        this.aString5937 = za_Sub1.method1679(class98_Sub22.anInt3991, method1192, (byte)(-51), class98_Sub22.aByteArray3992);
                    }
                    if ((0x80 | unsignedByte) != 0x0) {}
                }
                else {
                    if (~(unsignedByte | 0x80) == -1) {
                        throw new IllegalStateException();
                    }
                    if (~super.anInt4240 == -1) {
                        final Class98_Sub22 class98_Sub23 = class98_Sub22;
                        class98_Sub23.anInt3991 += 23;
                        this.anInt5930 = class98_Sub22.method1202((byte)(-51));
                        this.anInt5936 = class98_Sub22.method1202((byte)(-108));
                        if (this.anInt5930 == 0 || this.anInt5936 == 0) {
                            throw new IllegalStateException();
                        }
                        final Class98_Sub22 class98_Sub24 = new Class98_Sub22(16);
                        class98_Sub22.method1190(class98_Sub24.aByteArray3992, true, 16, 0);
                        this.aString5933 = class98_Sub24.readString((byte)84);
                        class98_Sub24.anInt3991 = 0;
                        class98_Sub22.method1190(class98_Sub24.aByteArray3992, !b, 16, 0);
                        this.aString5931 = class98_Sub24.readString((byte)84);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.J(" + ((oggPacket != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method1505(final int n) {
        try {
            Class98_Sub43_Sub4.aFloatArray5940 = null;
            Class98_Sub43_Sub4.aStringArray5932 = null;
            if (n != 21237) {
                Class98_Sub43_Sub4.aShort5934 = -113;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.B(" + n + ')');
        }
    }
    
    static final boolean method1506(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Class243 class243, final int n7, final int n8, final int n9, final int n10) {
        try {
            int anInt1539 = n4;
            int anInt1540 = n10;
            final int n11 = 64;
            if (n2 != 14664) {
                method1504(null, -75);
            }
            final int n12 = 64;
            final int n13 = -n11 + n4;
            final int n14 = n10 + -n12;
            PlayerUpdateMask.anIntArrayArray528[n11][n12] = 99;
            Class339.anIntArrayArray2846[n11][n12] = 0;
            int i = 0;
            int n15 = 0;
            Class359.anIntArray3060[i] = anInt1539;
            Class75.anIntArray580[i++] = anInt1540;
            final int[][] anIntArrayArray1853 = class243.anIntArrayArray1853;
            while (i != n15) {
                anInt1539 = Class359.anIntArray3060[n15];
                anInt1540 = Class75.anIntArray580[n15];
                final int n16 = anInt1539 - n13;
                final int n17 = -n14 + anInt1540;
                final int n18 = -class243.anInt1854 + anInt1539;
                n15 = (1 + n15 & 0xFFF);
                final int n19 = anInt1540 + -class243.anInt1855;
                if (~n != 0x3) {
                    if (~n != 0x2) {
                        if (n != -2) {
                            if (n != -1) {
                                if (~n != -1 && n != 1 && n != 2 && n != 3 && ~n != 0xFFFFFFF6) {
                                    if (class243.method2938(n6, n8, anInt1540, n7, n, 17761, 1, anInt1539)) {
                                        Class22.anInt217 = anInt1540;
                                        Class199.anInt1539 = anInt1539;
                                        return true;
                                    }
                                }
                                else if (class243.method2952(n7, anInt1539, (byte)(-110), 1, anInt1540, n8, n6, n)) {
                                    Class22.anInt217 = anInt1540;
                                    Class199.anInt1539 = anInt1539;
                                    return true;
                                }
                            }
                            else if (class243.method2939(n9, n6, anInt1540, 14672, 1, n7, n5, anInt1539, n3)) {
                                Class22.anInt217 = anInt1540;
                                Class199.anInt1539 = anInt1539;
                                return true;
                            }
                        }
                        else if (class243.method2936(n6, n3, n7, -1, anInt1540, n9, 1, anInt1539, 1, n5)) {
                            Class22.anInt217 = anInt1540;
                            Class199.anInt1539 = anInt1539;
                            return true;
                        }
                    }
                    else if (Class98_Sub5.method960(n6, n9, -105, anInt1540, n3, anInt1539, n7, 1, 1)) {
                        Class199.anInt1539 = anInt1539;
                        Class22.anInt217 = anInt1540;
                        return true;
                    }
                }
                else if (~n6 == ~anInt1539 && anInt1540 == n7) {
                    Class22.anInt217 = anInt1540;
                    Class199.anInt1539 = anInt1539;
                    return true;
                }
                final int n20 = Class339.anIntArrayArray2846[n16][n17] + 1;
                if (n16 > 0 && ~PlayerUpdateMask.anIntArrayArray528[n16 - 1][n17] == -1 && ~(anIntArrayArray1853[n18 - 1][n19] & 0x42240000) == -1) {
                    Class359.anIntArray3060[i] = anInt1539 - 1;
                    Class75.anIntArray580[i] = anInt1540;
                    PlayerUpdateMask.anIntArrayArray528[n16 - 1][n17] = 2;
                    i = (0xFFF & 1 + i);
                    Class339.anIntArrayArray2846[n16 - 1][n17] = n20;
                }
                if (~n16 > -128 && PlayerUpdateMask.anIntArrayArray528[1 + n16][n17] == 0 && ~(0x60240000 & anIntArrayArray1853[1 + n18][n19]) == -1) {
                    Class359.anIntArray3060[i] = 1 + anInt1539;
                    Class75.anIntArray580[i] = anInt1540;
                    PlayerUpdateMask.anIntArrayArray528[1 + n16][n17] = 8;
                    i = (i + 1 & 0xFFF);
                    Class339.anIntArrayArray2846[1 + n16][n17] = n20;
                }
                if (n17 > 0 && ~PlayerUpdateMask.anIntArrayArray528[n16][-1 + n17] == -1 && (anIntArrayArray1853[n18][-1 + n19] & 0x40A40000) == 0x0) {
                    Class359.anIntArray3060[i] = anInt1539;
                    Class75.anIntArray580[i] = anInt1540 - 1;
                    PlayerUpdateMask.anIntArrayArray528[n16][n17 - 1] = 1;
                    i = (i + 1 & 0xFFF);
                    Class339.anIntArrayArray2846[n16][n17 - 1] = n20;
                }
                if (n17 < 127 && PlayerUpdateMask.anIntArrayArray528[n16][n17 + 1] == 0 && ~(anIntArrayArray1853[n18][1 + n19] & 0x48240000) == -1) {
                    Class359.anIntArray3060[i] = anInt1539;
                    Class75.anIntArray580[i] = anInt1540 + 1;
                    i = (i + 1 & 0xFFF);
                    PlayerUpdateMask.anIntArrayArray528[n16][n17 + 1] = 4;
                    Class339.anIntArrayArray2846[n16][1 + n17] = n20;
                }
                if (n16 > 0 && ~n17 < -1 && ~PlayerUpdateMask.anIntArrayArray528[-1 + n16][n17 - 1] == -1 && (anIntArrayArray1853[n18 - 1][n19 - 1] & 0x43A40000) == 0x0 && ~(anIntArrayArray1853[-1 + n18][n19] & 0x42240000) == -1 && (0x40A40000 & anIntArrayArray1853[n18][-1 + n19]) == 0x0) {
                    Class359.anIntArray3060[i] = -1 + anInt1539;
                    Class75.anIntArray580[i] = anInt1540 - 1;
                    i = (0xFFF & 1 + i);
                    PlayerUpdateMask.anIntArrayArray528[-1 + n16][-1 + n17] = 3;
                    Class339.anIntArrayArray2846[-1 + n16][-1 + n17] = n20;
                }
                if (n16 < 127 && n17 > 0 && PlayerUpdateMask.anIntArrayArray528[n16 + 1][-1 + n17] == 0 && ~(anIntArrayArray1853[1 + n18][n19 - 1] & 0x60E40000) == -1 && (anIntArrayArray1853[1 + n18][n19] & 0x60240000) == 0x0 && (anIntArrayArray1853[n18][n19 - 1] & 0x40A40000) == 0x0) {
                    Class359.anIntArray3060[i] = anInt1539 + 1;
                    Class75.anIntArray580[i] = anInt1540 - 1;
                    i = (0xFFF & 1 + i);
                    PlayerUpdateMask.anIntArrayArray528[n16 + 1][-1 + n17] = 9;
                    Class339.anIntArrayArray2846[1 + n16][n17 - 1] = n20;
                }
                if (~n16 < -1 && ~n17 > -128 && ~PlayerUpdateMask.anIntArrayArray528[-1 + n16][n17 + 1] == -1 && (anIntArrayArray1853[-1 + n18][n19 + 1] & 0x4E240000) == 0x0 && (0x42240000 & anIntArrayArray1853[n18 - 1][n19]) == 0x0 && (0x48240000 & anIntArrayArray1853[n18][n19 + 1]) == 0x0) {
                    Class359.anIntArray3060[i] = -1 + anInt1539;
                    Class75.anIntArray580[i] = anInt1540 + 1;
                    PlayerUpdateMask.anIntArrayArray528[-1 + n16][1 + n17] = 6;
                    i = (1 + i & 0xFFF);
                    Class339.anIntArrayArray2846[n16 - 1][n17 + 1] = n20;
                }
                if (~n16 > -128 && ~n17 > -128 && ~PlayerUpdateMask.anIntArrayArray528[n16 + 1][n17 + 1] == -1 && (0x78240000 & anIntArrayArray1853[n18 + 1][1 + n19]) == 0x0 && (0x60240000 & anIntArrayArray1853[1 + n18][n19]) == 0x0 && (0x48240000 & anIntArrayArray1853[n18][1 + n19]) == 0x0) {
                    Class359.anIntArray3060[i] = 1 + anInt1539;
                    Class75.anIntArray580[i] = anInt1540 + 1;
                    PlayerUpdateMask.anIntArrayArray528[n16 + 1][n17 + 1] = 12;
                    i = (0xFFF & i + 1);
                    Class339.anIntArrayArray2846[1 + n16][1 + n17] = n20;
                }
            }
            Class199.anInt1539 = anInt1539;
            Class22.anInt217 = anInt1540;
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((class243 != null) ? "{...}" : "null") + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    final String method1507(final boolean b) {
        try {
            if (!b) {
                return null;
            }
            return this.aString5933;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.E(" + b + ')');
        }
    }
    
    @Override
    final void method1487(final int n) {
        try {
            if (n != -1128) {
                method1510(33);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.C(" + n + ')');
        }
    }
    
    static final int method1508(final int n, final int n2, final String s) {
        try {
            if (n != 1) {
                Class98_Sub43_Sub4.aStringArray5932 = null;
            }
            return PlayerUpdate.method2859(n2, true, s, n ^ 0xFFFFAA2D);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.K(" + n + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class98_Sub43_Sub4(final OggStreamState oggStreamState) {
        super(oggStreamState);
    }
    
    final float method1509(final int n) {
        try {
            if (n != -6085) {
                return 2.7172983f;
            }
            return this.aFloat5935;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.F(" + n + ')');
        }
    }
    
    static final void method1510(final int n) {
        try {
            Class219.aClass77_1641.method773((byte)(-30));
            if (n != 28837) {
                Class98_Sub43_Sub4.anInt5938 = -111;
            }
            Class2.aClass299_73.method3515(-119);
            Class315.aClient3529.method87(n - 28837);
            Class42_Sub3.aCanvas5361.setBackground(Color.black);
            Class325.anInt2729 = -1;
            Class219.aClass77_1641 = Class368.method3950((byte)10, Class42_Sub3.aCanvas5361);
            Class2.aClass299_73 = Class151_Sub3.method2457(Class42_Sub3.aCanvas5361, true, -16777216);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.I(" + n + ')');
        }
    }
    
    final float method1511(final int n) {
        try {
            if (n >= -38) {
                Class98_Sub43_Sub4.aShort5934 = 29;
            }
            return this.aFloat5939;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wda.H(" + n + ')');
        }
    }
    
    static {
        Class98_Sub43_Sub4.aStringArray5932 = new String[100];
        Class98_Sub43_Sub4.aShort5934 = 205;
        Class98_Sub43_Sub4.aFloatArray5940 = new float[4];
    }
}
