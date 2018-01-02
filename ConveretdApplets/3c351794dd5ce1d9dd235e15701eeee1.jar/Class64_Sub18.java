import java.net.Socket;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub18 extends Class64
{
    static int[] anIntArray3688;
    static Class332[] aClass332Array3689;
    static boolean aBoolean3690;
    static float aFloat3691;
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                Class64_Sub18.aBoolean3690 = false;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.A(" + n + ')');
        }
    }
    
    static final void method622(final byte b) {
        try {
            Class98_Sub46_Sub9 class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2792(-1);
            if (b != -38) {
                Class64_Sub18.anIntArray3688 = null;
            }
            while (class98_Sub46_Sub9 != null) {
                if (class98_Sub46_Sub9.anInt6001 > 1) {
                    class98_Sub46_Sub9.anInt6001 = 0;
                    Class98_Sub46_Sub16.aClass79_6046.method805(((Class98_Sub46_Sub8)class98_Sub46_Sub9.aClass215_5999.aClass98_Sub46_1615.aClass98_Sub46_4262).aLong5991, class98_Sub46_Sub9, (byte)(-80));
                    class98_Sub46_Sub9.aClass215_5999.method2786(16711680);
                }
                class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2787(0);
            }
            Class64_Sub12.anInt3672 = 0;
            Class359.anInt3058 = 0;
            Class33.aClass148_315.method2422((byte)47);
            Class98_Sub47.aClass377_4274.method3994(-99);
            Class98_Sub18.aClass215_3949.method2786(b ^ 0xFF00FFDA);
            Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.O(" + b + ')');
        }
    }
    
    Class64_Sub18(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    static final void method623(final int n) {
        try {
            Class22.anInt216 = 0;
            if (n != -23196) {
                method623(27);
            }
            final int n2 = (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 >> 1513969193) + Class272.anInt2038;
            final int n3 = (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 >> 1695912137) + aa_Sub2.anInt3562;
            if (n2 >= 3053 && n2 <= 3156 && ~n3 <= -3057 && n3 <= 3136) {
                Class22.anInt216 = 1;
            }
            if (n2 >= 3072 && ~n2 >= -3119 && n3 >= 9492 && ~n3 >= -9536) {
                Class22.anInt216 = 1;
            }
            if (Class22.anInt216 == 1 && ~n2 <= -3140 && n2 <= 3199 && n3 >= 3008 && ~n3 >= -3063) {
                Class22.anInt216 = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.H(" + n + ')');
        }
    }
    
    static final void method624(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        try {
            int n9 = 0;
            int n10 = n8;
            int n11 = 0;
            final int n12 = -n4 + n5;
            final int n13 = -n4 + n8;
            final int n14 = n5 * n5;
            final int n15 = n8 * n8;
            final int n16 = n12 * n12;
            final int n17 = n13 * n13;
            final int n18 = n15 << -909163295;
            final int n19 = n14 << -1295639423;
            final int n20 = n17 << 631613089;
            final int n21 = n16 << 2112910369;
            final int n22 = n8 << -1139343487;
            final int n23 = n13 << 1840275937;
            int n24 = n18 + n14 * (-n22 + 1);
            int n25 = -((-1 + n22) * n19) + n15;
            int n26 = n20 + (1 + -n23) * n16;
            int n27 = -(n21 * (n23 - 1)) + n17;
            final int n28 = n14 << -87112830;
            final int n29 = n15 << -1476324542;
            final int n30 = n16 << 2049619074;
            final int n31 = n17 << 167149762;
            int n32 = 3 * n18;
            int n33 = n19 * (-3 + n22);
            int n34 = n20 * 3;
            int n35 = (n23 - 3) * n21;
            int n36 = n29;
            int n37 = (-1 + n8) * n28;
            int n38 = n31;
            int n39 = (-1 + n13) * n30;
            final int[] array = Class97.anIntArrayArray814[n6];
            Class333.method3761(n7, array, n3 - n5, n3 + -n12, (byte)(-123));
            Class333.method3761(n, array, n3 - n12, n12 + n3, (byte)(-127));
            Class333.method3761(n7, array, n12 + n3, n3 + n5, (byte)49);
            if (n2 == -2211) {
                while (~n10 < -1) {
                    final boolean b = n10 <= n13;
                    if (b) {
                        if (n26 < 0) {
                            while (~n26 > -1) {
                                n27 += n38;
                                n26 += n34;
                                n38 += n31;
                                n34 += n31;
                                ++n11;
                            }
                        }
                        if (n27 < 0) {
                            n27 += n38;
                            n26 += n34;
                            n34 += n31;
                            n38 += n31;
                            ++n11;
                        }
                        n27 += -n35;
                        n26 += -n39;
                        n35 -= n30;
                        n39 -= n30;
                    }
                    if (n24 < 0) {
                        while (~n24 > -1) {
                            n25 += n36;
                            n24 += n32;
                            ++n9;
                            n32 += n29;
                            n36 += n29;
                        }
                    }
                    if (~n25 > -1) {
                        n24 += n32;
                        n25 += n36;
                        n32 += n29;
                        ++n9;
                        n36 += n29;
                    }
                    n24 += -n37;
                    n25 += -n33;
                    n37 -= n28;
                    n33 -= n28;
                    --n10;
                    final int n40 = n6 - n10;
                    final int n41 = n6 + n10;
                    final int n42 = n3 - -n9;
                    final int n43 = -n9 + n3;
                    if (b) {
                        final int n44 = n11 + n3;
                        final int n45 = n3 + -n11;
                        Class333.method3761(n7, Class97.anIntArrayArray814[n40], n43, n45, (byte)(-124));
                        Class333.method3761(n, Class97.anIntArrayArray814[n40], n45, n44, (byte)(-126));
                        Class333.method3761(n7, Class97.anIntArrayArray814[n40], n44, n42, (byte)(-124));
                        Class333.method3761(n7, Class97.anIntArrayArray814[n41], n43, n45, (byte)(-125));
                        Class333.method3761(n, Class97.anIntArrayArray814[n41], n45, n44, (byte)91);
                        Class333.method3761(n7, Class97.anIntArrayArray814[n41], n44, n42, (byte)(-128));
                    }
                    else {
                        Class333.method3761(n7, Class97.anIntArrayArray814[n40], n43, n42, (byte)60);
                        Class333.method3761(n7, Class97.anIntArrayArray814[n41], n43, n42, (byte)(-124));
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.N(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    final boolean method625(final byte b) {
        try {
            if (b >= -41) {
                this.method552(-65);
            }
            return Class375.method3986(super.anInt494, (byte)(-108));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.I(" + b + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b <= 118) {
                this.method627((byte)77);
            }
            if (this.method625((byte)(-111))) {
                if (super.aClass98_Sub27_495.aClass64_Sub8_4042.method586(true) && !Class246_Sub4_Sub2.method3107(super.aClass98_Sub27_495.aClass64_Sub8_4042.method583((byte)121), (byte)(-119))) {
                    super.anInt494 = 1;
                }
                if (~super.aClass98_Sub27_495.aClass64_Sub27_4068.method666((byte)123) == 0xFFFFFFFE) {
                    super.anInt494 = 1;
                }
            }
            if (super.anInt494 == 3) {
                super.anInt494 = 2;
            }
            if (super.anInt494 < 0 || ~super.anInt494 < -4) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.C(" + b + ')');
        }
    }
    
    final boolean method626(final int n) {
        try {
            return n == -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.G(" + n + ')');
        }
    }
    
    Class64_Sub18(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (Class375.method3986(n, (byte)(-108))) {
                if (super.aClass98_Sub27_495.aClass64_Sub8_4042.method586(true) && !Class246_Sub4_Sub2.method3107(super.aClass98_Sub27_495.aClass64_Sub8_4042.method583((byte)127), (byte)(-85))) {
                    return 3;
                }
                if (super.aClass98_Sub27_495.aClass64_Sub27_4068.method666((byte)120) == 1) {
                    return 3;
                }
            }
            if (n == 3) {
                return 3;
            }
            if (n2 != 29053) {
                Class64_Sub18.aClass332Array3689 = null;
            }
            if (Class375.method3986(n, (byte)(-108))) {
                return 2;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.F(" + n + ',' + n2 + ')');
        }
    }
    
    final int method627(final byte b) {
        try {
            if (b < 119) {
                Class64_Sub18.anIntArray3688 = null;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.E(" + b + ')');
        }
    }
    
    static final void method628(final int n) {
        try {
            if (~Class21_Sub4.anInt5394 != -1) {
                try {
                    if (~(++Class98_Sub26.anInt4028) < -2001) {
                        if (aa_Sub1.aClass123_3561 != null) {
                            aa_Sub1.aClass123_3561.method2207(-114);
                            aa_Sub1.aClass123_3561 = null;
                        }
                        if (Class372.anInt3150 >= 2) {
                            Class55.anInt442 = -5;
                            Class21_Sub4.anInt5394 = 0;
                            return;
                        }
                        Class299_Sub2.aClass354_5297.method3874(0);
                        ++Class372.anInt3150;
                        Class98_Sub26.anInt4028 = 0;
                        Class21_Sub4.anInt5394 = 1;
                    }
                    if (~Class21_Sub4.anInt5394 == 0xFFFFFFFE) {
                        Class246_Sub3_Sub3.aClass143_6155 = Class299_Sub2.aClass354_5297.method3870(-127, Class98_Sub43_Sub2.aClass88_5907);
                        Class21_Sub4.anInt5394 = 2;
                    }
                    if (~Class21_Sub4.anInt5394 == 0xFFFFFFFD) {
                        if (~Class246_Sub3_Sub3.aClass143_6155.anInt1163 == 0xFFFFFFFD) {
                            throw new IOException();
                        }
                        if (~Class246_Sub3_Sub3.aClass143_6155.anInt1163 != 0xFFFFFFFE) {
                            return;
                        }
                        aa_Sub1.aClass123_3561 = Class196.method2668((Socket)Class246_Sub3_Sub3.aClass143_6155.anObject1162, (byte)11, 7500);
                        Class246_Sub3_Sub3.aClass143_6155 = null;
                        Class95.method920((byte)127);
                        Class21_Sub4.anInt5394 = 4;
                    }
                    if (n != 19700) {
                        method622((byte)(-71));
                    }
                    if (~Class21_Sub4.anInt5394 == 0xFFFFFFFB && aa_Sub1.aClass123_3561.method2203(n - 21649, 1)) {
                        aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 1);
                        Class55.anInt442 = (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992[0] & 0xFF);
                        Class21_Sub4.anInt5394 = 0;
                        aa_Sub1.aClass123_3561.method2207(-113);
                        aa_Sub1.aClass123_3561 = null;
                    }
                }
                catch (IOException ex2) {
                    if (aa_Sub1.aClass123_3561 != null) {
                        aa_Sub1.aClass123_3561.method2207(-100);
                        aa_Sub1.aClass123_3561 = null;
                    }
                    if (~Class372.anInt3150 <= -3) {
                        Class55.anInt442 = -4;
                        Class21_Sub4.anInt5394 = 0;
                    }
                    else {
                        Class299_Sub2.aClass354_5297.method3874(0);
                        ++Class372.anInt3150;
                        Class98_Sub26.anInt4028 = 0;
                        Class21_Sub4.anInt5394 = 1;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.D(" + n + ')');
        }
    }
    
    public static void method629(final int n) {
        try {
            if (n == -32294) {
                Class64_Sub18.anIntArray3688 = null;
                Class64_Sub18.aClass332Array3689 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oc.M(" + n + ')');
        }
    }
    
    static {
        Class64_Sub18.anIntArray3688 = new int[] { 1, -1, -1, 1 };
        Class64_Sub18.aBoolean3690 = false;
    }
}
