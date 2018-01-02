import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub7 extends Class98_Sub10
{
    static OutgoingOpcode aClass171_5571;
    static int anInt5572;
    static String aString5573;
    private int anInt5574;
    static Class324[] aClass324Array5575;
    static Font aFont5576;
    static int anInt5577;
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n > -76) {
                method1023(-24, -20);
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[][] method2829 = this.method994(n2, 24431, 0);
                final int[][] method2830 = this.method994(n2, 24431, 1);
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                final int[] array4 = method2829[0];
                final int[] array5 = method2829[1];
                final int[] array6 = method2829[2];
                final int[] array7 = method2830[0];
                final int[] array8 = method2830[1];
                final int[] array9 = method2830[2];
                final int anInt5574 = this.anInt5574;
                if (anInt5574 != 1) {
                    if (anInt5574 != 2) {
                        if (anInt5574 != 3) {
                            if (anInt5574 != 4) {
                                if (anInt5574 != 5) {
                                    if (~anInt5574 != 0xFFFFFFF9) {
                                        if (anInt5574 != 7) {
                                            if (anInt5574 != 8) {
                                                if (anInt5574 != 9) {
                                                    if (~anInt5574 != 0xFFFFFFF5) {
                                                        if (anInt5574 != 11) {
                                                            if (anInt5574 == 12) {
                                                                for (int n3 = 0; Class25.anInt268 > n3; ++n3) {
                                                                    final int n4 = array5[n3];
                                                                    final int n5 = array7[n3];
                                                                    final int n6 = array8[n3];
                                                                    final int n7 = array6[n3];
                                                                    final int n8 = array9[n3];
                                                                    final int n9 = array4[n3];
                                                                    array[n3] = n5 + n9 + -(n9 * n5 >> 1539396619);
                                                                    array2[n3] = n4 - -n6 + -(n6 * n4 >> -1763503637);
                                                                    array3[n3] = n8 + n7 + -(n7 * n8 >> 398684619);
                                                                }
                                                            }
                                                        }
                                                        else {
                                                            for (int n10 = 0; Class25.anInt268 > n10; ++n10) {
                                                                final int n11 = array6[n10];
                                                                final int n12 = array8[n10];
                                                                final int n13 = array5[n10];
                                                                final int n14 = array9[n10];
                                                                final int n15 = array4[n10];
                                                                final int n16 = array7[n10];
                                                                array[n10] = ((~n16 > ~n15) ? (n15 - n16) : (-n15 + n16));
                                                                array2[n10] = ((~n12 <= ~n13) ? (-n13 + n12) : (-n12 + n13));
                                                                array3[n10] = ((~n11 < ~n14) ? (n11 + -n14) : (n14 - n11));
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        for (int n17 = 0; Class25.anInt268 > n17; ++n17) {
                                                            final int n18 = array6[n17];
                                                            final int n19 = array4[n17];
                                                            final int n20 = array7[n17];
                                                            final int n21 = array8[n17];
                                                            final int n22 = array9[n17];
                                                            final int n23 = array5[n17];
                                                            array[n17] = ((n19 > n20) ? n19 : n20);
                                                            array2[n17] = ((n21 >= n23) ? n21 : n23);
                                                            array3[n17] = ((n22 >= n18) ? n22 : n18);
                                                        }
                                                    }
                                                }
                                                else {
                                                    for (int n24 = 0; ~n24 > ~Class25.anInt268; ++n24) {
                                                        final int n25 = array5[n24];
                                                        final int n26 = array8[n24];
                                                        final int n27 = array4[n24];
                                                        final int n28 = array6[n24];
                                                        final int n29 = array9[n24];
                                                        final int n30 = array7[n24];
                                                        array[n24] = ((n30 > n27) ? n27 : n30);
                                                        array2[n24] = ((n26 <= n25) ? n26 : n25);
                                                        array3[n24] = ((n28 < n29) ? n28 : n29);
                                                    }
                                                }
                                            }
                                            else {
                                                for (int n31 = 0; ~Class25.anInt268 < ~n31; ++n31) {
                                                    final int n32 = array4[n31];
                                                    final int n33 = array5[n31];
                                                    final int n34 = array6[n31];
                                                    array[n31] = ((n32 != 0) ? (4096 + -((4096 + -array7[n31] << -1639495252) / n32)) : 0);
                                                    array2[n31] = ((n33 == 0) ? 0 : (4096 + -((4096 - array8[n31] << -1366428756) / n33)));
                                                    array3[n31] = ((~n34 == -1) ? 0 : (4096 - (4096 + -array9[n31] << 798210924) / n34));
                                                }
                                            }
                                        }
                                        else {
                                            for (int i = 0; i < Class25.anInt268; ++i) {
                                                final int n35 = array4[i];
                                                final int n36 = array5[i];
                                                final int n37 = array6[i];
                                                array[i] = ((n35 != 4096) ? ((array7[i] << 2057167660) / (-n35 + 4096)) : 4096);
                                                array2[i] = ((~n36 == 0xFFFFEFFF) ? 4096 : ((array8[i] << 1907195788) / (4096 + -n36)));
                                                array3[i] = ((n37 != 4096) ? ((array9[i] << 1788586060) / (-n37 + 4096)) : 4096);
                                            }
                                        }
                                    }
                                    else {
                                        for (int n38 = 0; ~n38 > ~Class25.anInt268; ++n38) {
                                            final int n39 = array9[n38];
                                            final int n40 = array8[n38];
                                            final int n41 = array7[n38];
                                            array[n38] = ((n41 >= 2048) ? (4096 + -((-array4[n38] + 4096) * (-n41 + 4096) >> -122785909)) : (array4[n38] * n41 >> 702271179));
                                            array2[n38] = ((~n40 > -2049) ? (array5[n38] * n40 >> 1967190795) : (4096 - ((-n40 + 4096) * (4096 + -array5[n38]) >> -827610229)));
                                            array3[n38] = ((n39 < 2048) ? (array6[n38] * n39 >> 908096939) : (-((4096 - array6[n38]) * (-n39 + 4096) >> 1201238987) + 4096));
                                        }
                                    }
                                }
                                else {
                                    for (int j = 0; j < Class25.anInt268; ++j) {
                                        array[j] = -((4096 - array7[j]) * (-array4[j] + 4096) >> -2069537524) + 4096;
                                        array2[j] = 4096 - ((-array8[j] + 4096) * (4096 + -array5[j]) >> 1639164044);
                                        array3[j] = 4096 + -((-array9[j] + 4096) * (4096 - array6[j]) >> -43965556);
                                    }
                                }
                            }
                            else {
                                for (int n42 = 0; ~Class25.anInt268 < ~n42; ++n42) {
                                    final int n43 = array7[n42];
                                    final int n44 = array8[n42];
                                    final int n45 = array9[n42];
                                    array[n42] = ((~n43 != -1) ? ((array4[n42] << 1548161740) / n43) : 4096);
                                    array2[n42] = ((n44 != 0) ? ((array5[n42] << -1588247860) / n44) : 4096);
                                    array3[n42] = ((n45 == 0) ? 4096 : ((array6[n42] << -2111435412) / n45));
                                }
                            }
                        }
                        else {
                            for (int n46 = 0; ~n46 > ~Class25.anInt268; ++n46) {
                                array[n46] = array4[n46] * array7[n46] >> 1762674444;
                                array2[n46] = array5[n46] * array8[n46] >> -166611188;
                                array3[n46] = array9[n46] * array6[n46] >> 136240748;
                            }
                        }
                    }
                    else {
                        for (int n47 = 0; ~n47 > ~Class25.anInt268; ++n47) {
                            array[n47] = -array7[n47] + array4[n47];
                            array2[n47] = array5[n47] - array8[n47];
                            array3[n47] = -array9[n47] + array6[n47];
                        }
                    }
                }
                else {
                    for (int n48 = 0; ~Class25.anInt268 < ~n48; ++n48) {
                        array[n48] = array4[n48] - -array7[n48];
                        array2[n48] = array5[n48] + array8[n48];
                        array3[n48] = array9[n48] + array6[n48];
                    }
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dp.C(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                Class98_Sub10_Sub7.anInt5572 = 39;
            }
            if (super.aClass16_3863.aBoolean198) {
                final int[] method238 = this.method1000(n2, 0, n ^ 0xFF);
                final int[] method239 = this.method1000(n2, 1, 0);
                final int anInt5574 = this.anInt5574;
                if (anInt5574 != 1) {
                    if (anInt5574 != 2) {
                        if (anInt5574 != 3) {
                            if (~anInt5574 != 0xFFFFFFFB) {
                                if (anInt5574 != 5) {
                                    if (anInt5574 != 6) {
                                        if (~anInt5574 != 0xFFFFFFF8) {
                                            if (~anInt5574 != 0xFFFFFFF7) {
                                                if (~anInt5574 != 0xFFFFFFF6) {
                                                    if (anInt5574 != 10) {
                                                        if (anInt5574 != 11) {
                                                            if (anInt5574 == 12) {
                                                                for (int i = 0; i < Class25.anInt268; ++i) {
                                                                    final int n3 = method239[i];
                                                                    final int n4 = method238[i];
                                                                    method237[i] = -(n4 * n3 >> 1977265323) + (n4 + n3);
                                                                }
                                                            }
                                                        }
                                                        else {
                                                            for (int n5 = 0; ~n5 > ~Class25.anInt268; ++n5) {
                                                                final int n6 = method239[n5];
                                                                final int n7 = method238[n5];
                                                                method237[n5] = ((n6 < n7) ? (n7 + -n6) : (-n7 + n6));
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        for (int n8 = 0; ~n8 > ~Class25.anInt268; ++n8) {
                                                            final int n9 = method238[n8];
                                                            final int n10 = method239[n8];
                                                            method237[n8] = ((n9 <= n10) ? n10 : n9);
                                                        }
                                                    }
                                                }
                                                else {
                                                    for (int j = 0; j < Class25.anInt268; ++j) {
                                                        final int n11 = method238[j];
                                                        final int n12 = method239[j];
                                                        method237[j] = ((n11 >= n12) ? n12 : n11);
                                                    }
                                                }
                                            }
                                            else {
                                                for (int n13 = 0; ~Class25.anInt268 < ~n13; ++n13) {
                                                    final int n14 = method238[n13];
                                                    method237[n13] = ((~n14 == -1) ? 0 : (-((4096 - method239[n13] << -250440436) / n14) + 4096));
                                                }
                                            }
                                        }
                                        else {
                                            for (int n15 = 0; ~n15 > ~Class25.anInt268; ++n15) {
                                                final int n16 = method238[n15];
                                                method237[n15] = ((~n16 == 0xFFFFEFFF) ? 4096 : ((method239[n15] << 1138900140) / (4096 - n16)));
                                            }
                                        }
                                    }
                                    else {
                                        for (int n17 = 0; ~Class25.anInt268 < ~n17; ++n17) {
                                            final int n18 = method239[n17];
                                            method237[n17] = ((~n18 <= -2049) ? (-((4096 - method238[n17]) * (4096 + -n18) >> -328797877) + 4096) : (n18 * method238[n17] >> 1396388075));
                                        }
                                    }
                                }
                                else {
                                    for (int n19 = 0; ~Class25.anInt268 < ~n19; ++n19) {
                                        method237[n19] = 4096 + -((-method238[n19] + 4096) * (4096 + -method239[n19]) >> 1203892940);
                                    }
                                }
                            }
                            else {
                                for (int n20 = 0; Class25.anInt268 > n20; ++n20) {
                                    final int n21 = method239[n20];
                                    method237[n20] = ((n21 != 0) ? ((method238[n20] << -1552573364) / n21) : 4096);
                                }
                            }
                        }
                        else {
                            for (int k = 0; k < Class25.anInt268; ++k) {
                                method237[k] = method239[k] * method238[k] >> 159781452;
                            }
                        }
                    }
                    else {
                        for (int l = 0; l < Class25.anInt268; ++l) {
                            method237[l] = method238[l] - method239[l];
                        }
                    }
                }
                else {
                    for (int n22 = 0; Class25.anInt268 > n22; ++n22) {
                        method237[n22] = method238[n22] + method239[n22];
                    }
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dp.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method1023(final int n, final int n2) {
        try {
            if (n != 1) {
                Class98_Sub10_Sub7.aString5573 = null;
            }
            return n2 != 1 && n2 != 7;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dp.E(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub7() {
        super(2, false);
        this.anInt5574 = 6;
    }
    
    public static void method1024(final byte b) {
        try {
            Class98_Sub10_Sub7.aClass171_5571 = null;
            Class98_Sub10_Sub7.aClass324Array5575 = null;
            Class98_Sub10_Sub7.aFont5576 = null;
            Class98_Sub10_Sub7.aString5573 = null;
            if (b < 106) {
                method1024((byte)69);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dp.D(" + b + ')');
        }
    }
    
    static final Class348 method1025(final int n, final byte b) {
        try {
            final Class348[] method906 = Class93_Sub1.method906((byte)15);
            for (int n2 = 0; ~n2 > ~method906.length; ++n2) {
                if (n == method906[n2].anInt2909) {
                    return method906[n2];
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dp.B(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (n == 1) {
                    super.aBoolean3861 = (class98_Sub22.readUnsignedByte((byte)(-113)) == 1);
                }
            }
            else {
                this.anInt5574 = class98_Sub22.readUnsignedByte((byte)99);
            }
            if (b >= -92) {
                Class98_Sub10_Sub7.aClass171_5571 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dp.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub7.aClass171_5571 = new OutgoingOpcode(24, -1);
        Class98_Sub10_Sub7.aString5573 = null;
        Class98_Sub10_Sub7.anInt5572 = 104;
        Class98_Sub10_Sub7.anInt5577 = 0;
    }
}
