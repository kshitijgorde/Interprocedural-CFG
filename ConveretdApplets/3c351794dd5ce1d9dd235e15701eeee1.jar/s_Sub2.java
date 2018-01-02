import jaclib.memory.heap.NativeHeapBuffer;
import jaclib.memory.Source;
import jaclib.memory.Buffer;
import jaclib.memory.Stream;

// 
// Decompiled by Procyon v0.5.30
// 

final class s_Sub2 extends s
{
    ha_Sub3 aHa_Sub3_5225;
    int[][][] anIntArrayArrayArray5226;
    private int anInt5227;
    private int[][][] anIntArrayArrayArray5228;
    private int anInt5229;
    short[][] aShortArrayArray5230;
    private Class148 aClass148_5231;
    private int anInt5232;
    int anInt5233;
    private byte[][] aByteArrayArray5234;
    private Class15 aClass15_5235;
    private Class98_Sub30[][][] aClass98_Sub30ArrayArrayArray5236;
    private int anInt5237;
    private float aFloat5238;
    private int[][][] anIntArrayArrayArray5239;
    int[][][] anIntArrayArrayArray5240;
    int[][][] anIntArrayArrayArray5241;
    private int[][][] anIntArrayArrayArray5242;
    private float aFloat5243;
    private int anInt5244;
    private Interface2_Impl1 anInterface2_Impl1_5245;
    private Class98_Sub30[] aClass98_Sub30Array5246;
    private Class377 aClass377_5247;
    private float[][] aFloatArrayArray5248;
    private Interface2_Impl1 anInterface2_Impl1_5249;
    private int anInt5250;
    private float[][] aFloatArrayArray5251;
    Class256 aClass256_5252;
    private byte[][] aByteArrayArray5253;
    private float[][] aFloatArrayArray5254;
    
    private final void method3435(final int n, final int n2, final int n3, final boolean b, final boolean[][] array, final int n4, final int n5, final byte b2) {
        try {
            if (this.aClass98_Sub30Array5246 != null) {
                final int n6 = 1 + (n4 + n4);
                final int n7 = n6 * n6;
                if (~Class98_Sub31_Sub2.anIntArray5832.length > ~n7) {
                    Class98_Sub31_Sub2.anIntArray5832 = new int[n7];
                }
                final int n9;
                int n8 = n9 = -n4 + n2;
                if (~n8 > -1) {
                    n8 = 0;
                }
                int n10 = n3 - n4;
                final int n11;
                if ((n11 = n10) < 0) {
                    n10 = 0;
                }
                int i = n2 + n4;
                if (~i < ~(super.anInt2203 - 1)) {
                    i = super.anInt2203 - 1;
                }
                int j = n3 - -n4;
                Class337.anInt3539 = 0;
                if (-1 + super.anInt2204 < j) {
                    j = super.anInt2204 - 1;
                }
                for (int n12 = n8; i >= n12; ++n12) {
                    final boolean[] array2 = array[-n9 + n12];
                    for (int n13 = n10; j >= n13; ++n13) {
                        if (array2[-n11 + n13]) {
                            Class98_Sub31_Sub2.anIntArray5832[Class337.anInt3539++] = super.anInt2203 * n13 - -n12;
                        }
                    }
                }
                if (n5 != -1) {
                    this.aHa_Sub3_5225.method2056(117, n5);
                    this.aHa_Sub3_5225.method1937(4);
                }
                else {
                    this.aHa_Sub3_5225.method1976(-65);
                }
                this.aHa_Sub3_5225.method1960(13951);
                this.aHa_Sub3_5225.method1979((0x7 & this.anInt5233) != 0x0, -126);
                this.aHa_Sub3_5225.method2039(false, 0, -1, false);
                this.aHa_Sub3_5225.method1971(0, true, this.anInterface2_Impl1_5249);
                for (int n14 = 0; this.aClass98_Sub30Array5246.length > n14; ++n14) {
                    this.aClass98_Sub30Array5246[n14].method1314(Class98_Sub31_Sub2.anIntArray5832, Class337.anInt3539, 32736);
                }
                this.aHa_Sub3_5225.method1978((byte)121).method2100(0, -1, 0);
                this.aHa_Sub3_5225.method1935(1);
                if (!this.aClass148_5231.method2420(-124)) {
                    final int anInt4636 = this.aHa_Sub3_5225.anInt4636;
                    final int anInt4637 = this.aHa_Sub3_5225.anInt4581;
                    this.aHa_Sub3_5225.L(0, anInt4637, this.aHa_Sub3_5225.anInt4601);
                    this.aHa_Sub3_5225.method1979(false, -105);
                    this.aHa_Sub3_5225.method1997(0, false);
                    this.aHa_Sub3_5225.method2001(128, 70);
                    this.aHa_Sub3_5225.method2039(false, 0, -2, false);
                    this.aHa_Sub3_5225.method2005(this.aHa_Sub3_5225.anInterface4_4586, -115);
                    this.aHa_Sub3_5225.method2019(Class288.aClass128_3381, Class249.aClass128_1903, 22831);
                    this.aHa_Sub3_5225.method2051(0, -126, Class64_Sub16.aClass65_3681);
                    this.aHa_Sub3_5225.method1953(-91, Class98_Sub43_Sub3.aClass65_5926, 0);
                    for (Class98 class98 = this.aClass148_5231.method2418(32); class98 != null; class98 = this.aClass148_5231.method2417(119)) {
                        ((Class98_Sub25)class98).method1270(0, array, n2, n3, n4);
                    }
                    this.aHa_Sub3_5225.method2051(0, -76, Class300.aClass65_2499);
                    this.aHa_Sub3_5225.method1953(-103, Class300.aClass65_2499, 0);
                    this.aHa_Sub3_5225.method2005(null, 69);
                    this.aHa_Sub3_5225.L(anInt4636, anInt4637, this.aHa_Sub3_5225.anInt4601);
                }
                if (this.aClass15_5235 != null) {
                    this.aHa_Sub3_5225.method1971(0, true, this.anInterface2_Impl1_5249);
                    this.aHa_Sub3_5225.method1971(1, true, this.anInterface2_Impl1_5245);
                    this.aHa_Sub3_5225.method2042(this.aClass256_5252, (byte)100);
                    this.aClass15_5235.method227(n2, b, array, n3, n4, 119);
                }
            }
            if (b2 >= -55) {
                this.aFloatArrayArray5248 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.A(" + n + ',' + n2 + ',' + n3 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + b2 + ')');
        }
    }
    
    @Override
    final void YA() {
        try {
            if (this.anInt5250 <= 0) {
                this.aClass15_5235 = null;
            }
            else {
                final byte[][] array = new byte[super.anInt2203 + 1][super.anInt2204 + 1];
                for (int n = 1; ~n > ~super.anInt2203; ++n) {
                    for (int n2 = 1; ~super.anInt2204 < ~n2; ++n2) {
                        array[n][n2] = (byte)((this.aByteArrayArray5253[n + 1][n2] >> -1922706173) + ((this.aByteArrayArray5253[n - 1][n2] >> 1738188642) + (this.aByteArrayArray5253[n][n2 - 1] >> 692982306) + (this.aByteArrayArray5253[n][n2 + 1] >> -1256993341) + (this.aByteArrayArray5253[n][n2] >> 1578820961)));
                    }
                }
                final Class98_Sub30[] array2 = new Class98_Sub30[this.aClass377_5247.method3999((byte)(-6))];
                this.aClass377_5247.method3992(array2, (byte)74);
                for (int n3 = 0; array2.length > n3; ++n3) {
                    array2[n3].method1316(this.anInt5250, 4);
                }
                int n4 = 20;
                if (this.anIntArrayArrayArray5239 != null) {
                    n4 += 4;
                }
                if (~(0x7 & this.anInt5233) != -1) {
                    n4 += 12;
                }
                final NativeHeapBuffer a = this.aHa_Sub3_5225.aNativeHeap4522.a(this.anInt5250 * 4, false);
                final NativeHeapBuffer a2 = this.aHa_Sub3_5225.aNativeHeap4522.a(this.anInt5250 * n4, false);
                final Stream stream = new Stream(a2);
                final Stream stream2 = new Stream(a);
                final Class98_Sub30[] array3 = new Class98_Sub30[this.anInt5250];
                int method282 = Class23.method282(68, this.anInt5250 / 4);
                if (method282 < 1) {
                    method282 = 1;
                }
                final Class377 class377 = new Class377(method282);
                final Class98_Sub30[] array4 = new Class98_Sub30[this.anInt5244];
                for (int n5 = 0; super.anInt2203 > n5; ++n5) {
                    for (int n6 = 0; ~n6 > ~super.anInt2204; ++n6) {
                        if (this.anIntArrayArrayArray5241[n5][n6] != null) {
                            final Class98_Sub30[] array5 = this.aClass98_Sub30ArrayArrayArray5236[n5][n6];
                            final int[] array6 = this.anIntArrayArrayArray5226[n5][n6];
                            final int[] array7 = this.anIntArrayArrayArray5240[n5][n6];
                            int[] array8 = this.anIntArrayArrayArray5242[n5][n6];
                            final int[] array9 = this.anIntArrayArrayArray5241[n5][n6];
                            final int[] array10 = (int[])((this.anIntArrayArrayArray5228 != null) ? this.anIntArrayArrayArray5228[n5][n6] : null);
                            if (array8 == null) {
                                array8 = array9;
                            }
                            final int[] array11 = (int[])((this.anIntArrayArrayArray5239 == null) ? null : this.anIntArrayArrayArray5239[n5][n6]);
                            final float n7 = this.aFloatArrayArray5251[n5][n6];
                            final float n8 = this.aFloatArrayArray5248[n5][n6];
                            final float n9 = this.aFloatArrayArray5254[n5][n6];
                            final float n10 = this.aFloatArrayArray5251[n5][n6 + 1];
                            final float n11 = this.aFloatArrayArray5248[n5][1 + n6];
                            final float n12 = this.aFloatArrayArray5254[n5][n6 + 1];
                            final float n13 = this.aFloatArrayArray5251[1 + n5][n6 + 1];
                            final float n14 = this.aFloatArrayArray5248[1 + n5][1 + n6];
                            final float n15 = this.aFloatArrayArray5254[n5 + 1][n6 + 1];
                            final float n16 = this.aFloatArrayArray5251[1 + n5][n6];
                            final float n17 = this.aFloatArrayArray5248[1 + n5][n6];
                            final float n18 = this.aFloatArrayArray5254[n5 + 1][n6];
                            final int n19 = array[n5][n6] & 0xFF;
                            final int n20 = array[n5][1 + n6] & 0xFF;
                            final int n21 = 0xFF & array[n5 + 1][n6 + 1];
                            final int n22 = array[1 + n5][n6] & 0xFF;
                            int i = 0;
                            int n23 = 0;
                        Label_0724:
                            while (array9.length > n23) {
                                final Class98_Sub30 class98_Sub30 = array5[n23];
                                while (true) {
                                    for (int n24 = 0; ~n24 > ~i; ++n24) {
                                        if (array4[n24] == class98_Sub30) {
                                            ++n23;
                                            continue Label_0724;
                                        }
                                    }
                                    array4[i++] = class98_Sub30;
                                    continue;
                                }
                            }
                            final short[][] aShortArrayArray5230 = this.aShortArrayArray5230;
                            final int n25 = n5 + super.anInt2203 * n6;
                            final short[] array12 = new short[array9.length];
                            aShortArrayArray5230[n25] = array12;
                            final short[] array13 = array12;
                            for (int n26 = 0; ~array9.length < ~n26; ++n26) {
                                final int n27 = (n5 << super.anInt2200) - -array6[n26];
                                final int n28 = (n6 << super.anInt2200) + array7[n26];
                                final int n29 = n27 >> this.anInt5229;
                                final int n30 = n28 >> this.anInt5229;
                                final int n31 = array9[n26];
                                final int n32 = array8[n26];
                                final int n33 = (array10 == null) ? 0 : array10[n26];
                                final long n34 = n32 << -2100004688 | n31 << -684988576 | n29 << -2037303504 | n30;
                                final int n35 = array6[n26];
                                final int n36 = array7[n26];
                                final int n37 = 74;
                                int n38 = 0;
                                float n39 = 1.0f;
                                float n40;
                                int n41;
                                float n42;
                                float n43;
                                if (~n35 == -1 && ~n36 == -1) {
                                    n40 = n7;
                                    n41 = n37 - n19;
                                    n42 = n9;
                                    n43 = n8;
                                }
                                else if (~n35 != -1 || ~super.anInt2206 != ~n36) {
                                    if (~n35 == ~super.anInt2206 && ~super.anInt2206 == ~n36) {
                                        n43 = n14;
                                        n40 = n13;
                                        n41 = n37 - n21;
                                        n42 = n15;
                                    }
                                    else if (~super.anInt2206 == ~n35 && n36 == 0) {
                                        n43 = n17;
                                        n42 = n18;
                                        n41 = n37 - n22;
                                        n40 = n16;
                                    }
                                    else {
                                        final float n44 = n35 / super.anInt2206;
                                        final float n45 = n36 / super.anInt2206;
                                        final float n46 = (n16 - n7) * n44 + n7;
                                        final float n47 = n44 * (-n8 + n17) + n8;
                                        final float n48 = (-n9 + n18) * n44 + n9;
                                        final float n49 = (-n10 + n13) * n44 + n10;
                                        n43 = n47 + (n11 + (n14 - n11) * n44 - n47) * n45;
                                        final float n50 = n12 + n44 * (-n12 + n15);
                                        n40 = n45 * (-n46 + n49) + n46;
                                        n42 = (-n48 + n50) * n45 + n48;
                                        final int n51 = ((n22 - n19) * n35 >> super.anInt2200) + n19;
                                        n41 = n37 - (n51 + ((n20 + ((n21 + -n20) * n35 >> super.anInt2200) - n51) * n36 >> super.anInt2200));
                                    }
                                }
                                else {
                                    n41 = n37 - n20;
                                    n43 = n11;
                                    n42 = n12;
                                    n40 = n10;
                                }
                                if (n31 != -1) {
                                    int n52 = n41 * (0x7F & n31) >> 644451911;
                                    if (n52 < 2) {
                                        n52 = 2;
                                    }
                                    else if (~n52 < -127) {
                                        n52 = 126;
                                    }
                                    if (~(0x7 & this.anInt5233) == -1) {
                                        final float n53 = this.aHa_Sub3_5225.aFloatArray4596[0] * n40 + this.aHa_Sub3_5225.aFloatArray4596[1] * n43 + n42 * this.aHa_Sub3_5225.aFloatArray4596[2];
                                        n39 = this.aHa_Sub3_5225.aFloat4576 + n53 * ((n53 <= 0.0f) ? this.aHa_Sub3_5225.aFloat4594 : this.aHa_Sub3_5225.aFloat4630);
                                    }
                                    n38 = Class208.anIntArray1579[n52 | (0xFF80 & n31)];
                                }
                                Class98 method283 = null;
                                if ((n27 & -1 + this.anInt5232) == 0x0 && ~(-1 + this.anInt5232 & n28) == -1) {
                                    method283 = class377.method3990(n34, -1);
                                }
                                int n64;
                                if (method283 == null) {
                                    int n55;
                                    if (~n31 != ~n32) {
                                        int n54 = (n32 & 0x7F) * n41 >> 2050513671;
                                        if (n54 < 2) {
                                            n54 = 2;
                                        }
                                        else if (~n54 < -127) {
                                            n54 = 126;
                                        }
                                        n55 = Class208.anIntArray1579[n54 | (n32 & 0xFF80)];
                                        if ((0x7 & this.anInt5233) == 0x0) {
                                            final float n56 = this.aHa_Sub3_5225.aFloatArray4596[1] * n43 + n40 * this.aHa_Sub3_5225.aFloatArray4596[0] + n42 * this.aHa_Sub3_5225.aFloatArray4596[2];
                                            final float n57 = ((n39 <= 0.0f) ? this.aHa_Sub3_5225.aFloat4594 : this.aHa_Sub3_5225.aFloat4630) * n39 + this.aHa_Sub3_5225.aFloat4576;
                                            final int n58 = (0xFF85B1 & n55) >> -762643504;
                                            final int n59 = (n55 & 0xFFEB) >> 1741284072;
                                            final int n60 = n55 & 0xFF;
                                            int n61 = (int)(n58 * n57);
                                            int n62 = (int)(n59 * n57);
                                            if (n61 >= 0) {
                                                if (n61 > 255) {
                                                    n61 = 255;
                                                }
                                            }
                                            else {
                                                n61 = 0;
                                            }
                                            if (~n62 <= -1) {
                                                if (n62 > 255) {
                                                    n62 = 255;
                                                }
                                            }
                                            else {
                                                n62 = 0;
                                            }
                                            int n63 = (int)(n60 * n57);
                                            if (n63 >= 0) {
                                                if (n63 > 255) {
                                                    n63 = 255;
                                                }
                                            }
                                            else {
                                                n63 = 0;
                                            }
                                            n55 = (n62 << 1339637864 | n61 << -1999932656 | n63);
                                        }
                                    }
                                    else {
                                        n55 = n38;
                                    }
                                    if (!Stream.a()) {
                                        stream.a((float)n27);
                                        stream.a((float)(n33 + this.method3417(n27, n28, true)));
                                        stream.a((float)n28);
                                        stream.a((float)n27);
                                        stream.a((float)n28);
                                        if (this.anIntArrayArrayArray5239 != null) {
                                            stream.a((float)((array11 == null) ? 0 : (-1 + array11[n26])));
                                        }
                                        if ((this.anInt5233 & 0x7) != 0x0) {
                                            stream.a(n40);
                                            stream.a(n43);
                                            stream.a(n42);
                                        }
                                    }
                                    else {
                                        stream.b((float)n27);
                                        stream.b((float)(n33 + this.method3417(n27, n28, true)));
                                        stream.b((float)n28);
                                        stream.b((float)n27);
                                        stream.b((float)n28);
                                        if (this.anIntArrayArrayArray5239 != null) {
                                            stream.b((float)((array11 != null) ? (-1 + array11[n26]) : 0));
                                        }
                                        if ((this.anInt5233 & 0x7) != 0x0) {
                                            stream.b(n40);
                                            stream.b(n43);
                                            stream.b(n42);
                                        }
                                    }
                                    if (this.aHa_Sub3_5225.anInt4580 == 0) {
                                        stream2.f(0xFF000000 | n55);
                                    }
                                    else {
                                        stream2.a(n55 | 0xFF000000);
                                    }
                                    n64 = this.anInt5237++;
                                    array13[n26] = (short)n64;
                                    if (~n31 != 0x0) {
                                        array3[n64] = array5[n26];
                                    }
                                    class377.method3996(new Class98_Sub40(array13[n26]), n34, -1);
                                }
                                else {
                                    array13[n26] = ((Class98_Sub40)method283).aShort4191;
                                    n64 = (0xFFFF & array13[n26]);
                                    if (~n31 != 0x0 && array5[n26].aLong832 < array3[n64].aLong832) {
                                        array3[n64] = array5[n26];
                                    }
                                }
                                for (int n65 = 0; i > n65; ++n65) {
                                    array4[n65].method1317(n64, n41, -1, n38, n39);
                                }
                                ++this.anInt5227;
                            }
                        }
                    }
                }
                for (int j = 0; j < this.anInt5237; ++j) {
                    final Class98_Sub30 class98_Sub31 = array3[j];
                    if (class98_Sub31 != null) {
                        class98_Sub31.method1312(j, true);
                    }
                }
                for (int n66 = 0; ~n66 > ~super.anInt2203; ++n66) {
                    for (int n67 = 0; ~n67 > ~super.anInt2204; ++n67) {
                        final short[] array14 = this.aShortArrayArray5230[n67 * super.anInt2203 - -n66];
                        if (array14 != null) {
                            int n68 = 0;
                            int n69 = 0;
                            while (array14.length > n69) {
                                final int n70 = 0xFFFF & array14[n69++];
                                final int n71 = 0xFFFF & array14[n69++];
                                final int n72 = 0xFFFF & array14[n69++];
                                final Class98_Sub30 class98_Sub32 = array3[n70];
                                final Class98_Sub30 class98_Sub33 = array3[n71];
                                final Class98_Sub30 class98_Sub34 = array3[n72];
                                Class98_Sub30 class98_Sub35 = null;
                                if (class98_Sub32 != null) {
                                    class98_Sub32.method1315(n68, -20787, n66, n67);
                                    class98_Sub35 = class98_Sub32;
                                }
                                if (class98_Sub33 != null) {
                                    class98_Sub33.method1315(n68, -20787, n66, n67);
                                    if (class98_Sub35 == null || class98_Sub33.aLong832 < class98_Sub35.aLong832) {
                                        class98_Sub35 = class98_Sub33;
                                    }
                                }
                                if (class98_Sub34 != null) {
                                    class98_Sub34.method1315(n68, -20787, n66, n67);
                                    if (class98_Sub35 == null || class98_Sub34.aLong832 < class98_Sub35.aLong832) {
                                        class98_Sub35 = class98_Sub34;
                                    }
                                }
                                if (class98_Sub35 != null) {
                                    if (class98_Sub32 != null) {
                                        class98_Sub35.method1312(n70, true);
                                    }
                                    if (class98_Sub33 != null) {
                                        class98_Sub35.method1312(n71, true);
                                    }
                                    if (class98_Sub34 != null) {
                                        class98_Sub35.method1312(n72, true);
                                    }
                                    class98_Sub35.method1315(n68, -20787, n66, n67);
                                }
                                ++n68;
                            }
                        }
                    }
                }
                stream.c();
                stream2.c();
                (this.anInterface2_Impl1_5245 = this.aHa_Sub3_5225.method2060(false, 44)).method73((byte)(-123), this.anInt5237 * 4, 4, a);
                (this.anInterface2_Impl1_5249 = this.aHa_Sub3_5225.method2060(false, 71)).method73((byte)(-89), n4 * this.anInt5237, n4, a2);
                if ((0x7 & this.anInt5233) != 0x0) {
                    if (this.anIntArrayArrayArray5239 == null) {
                        this.aClass256_5252 = this.aHa_Sub3_5225.method1982(new Class49[] { new Class49(new Class169[] { Class169.aClass169_1294, Class169.aClass169_1301, Class169.aClass169_1297 }), new Class49(Class169.aClass169_1298) }, 6);
                    }
                    else {
                        this.aClass256_5252 = this.aHa_Sub3_5225.method1982(new Class49[] { new Class49(new Class169[] { Class169.aClass169_1294, Class169.aClass169_1301, Class169.aClass169_1299, Class169.aClass169_1297 }), new Class49(Class169.aClass169_1298) }, 6);
                    }
                }
                else if (this.anIntArrayArrayArray5239 != null) {
                    this.aClass256_5252 = this.aHa_Sub3_5225.method1982(new Class49[] { new Class49(new Class169[] { Class169.aClass169_1294, Class169.aClass169_1301, Class169.aClass169_1299 }), new Class49(Class169.aClass169_1298) }, 6);
                }
                else {
                    this.aClass256_5252 = this.aHa_Sub3_5225.method1982(new Class49[] { new Class49(new Class169[] { Class169.aClass169_1294, Class169.aClass169_1301 }), new Class49(Class169.aClass169_1298) }, 6);
                }
                int n73 = 0;
                for (int n74 = 0; ~array2.length < ~n74; ++n74) {
                    if (~array2[n74].anInt4098 < -1) {
                        array2[n73++] = array2[n74];
                    }
                }
                this.aClass98_Sub30Array5246 = new Class98_Sub30[n73];
                final long[] array15 = new long[n73];
                for (int n75 = 0; ~n75 > ~n73; ++n75) {
                    final Class98_Sub30 class98_Sub36 = array2[n75];
                    array15[n75] = class98_Sub36.aLong832;
                    (this.aClass98_Sub30Array5246[n75] = class98_Sub36).method1313(this.anInt5237, (byte)97);
                }
                Class46.method436(this.aClass98_Sub30Array5246, false, array15);
                if (this.aClass15_5235 != null) {
                    this.aClass15_5235.method229((byte)(-23));
                }
            }
            this.anIntArrayArrayArray5239 = null;
            this.anIntArrayArrayArray5228 = null;
            this.aClass377_5247 = null;
            this.anIntArrayArrayArray5241 = null;
            this.aByteArrayArray5253 = null;
            final float[][] aFloatArrayArray5251 = null;
            this.aFloatArrayArray5254 = aFloatArrayArray5251;
            this.aFloatArrayArray5248 = aFloatArrayArray5251;
            this.aFloatArrayArray5251 = aFloatArrayArray5251;
            this.aClass98_Sub30ArrayArrayArray5236 = null;
            this.anIntArrayArrayArray5242 = null;
            final int[][][] array16 = null;
            this.anIntArrayArrayArray5240 = array16;
            this.anIntArrayArrayArray5226 = array16;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.YA()");
        }
    }
    
    @Override
    final void ka(final int n, final int n2, final int n3) {
        try {
            if (n3 > (0xFF & this.aByteArrayArray5253[n][n2])) {
                this.aByteArrayArray5253[n][n2] = (byte)n3;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.ka(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final r fa(final int n, final int n2, final r r) {
        try {
            if ((0x1 & this.aByteArrayArray5234[n][n2]) == 0x0) {
                return null;
            }
            final int n3 = super.anInt2206 >> this.aHa_Sub3_5225.anInt4573;
            final r_Sub2 r_Sub2 = (r_Sub2)r;
            r_Sub2 r_Sub3 = null;
            Label_0092: {
                if (r_Sub2 == null || !r_Sub2.method1652(n3, n3, 22657)) {
                    r_Sub3 = new r_Sub2(this.aHa_Sub3_5225, n3, n3);
                    if (!client.aBoolean3553) {
                        break Label_0092;
                    }
                }
                r_Sub3 = r_Sub2;
                r_Sub3.method1654(105);
            }
            r_Sub3.method1651(n3, 0, 0, 0, n3);
            this.method3436(true, n, n2, r_Sub3);
            return r_Sub3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.fa(" + n + ',' + n2 + ',' + ((r != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method3424(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int[] array8, final int[] array9, final int[] array10, final int[] array11, final int n3, final int n4, final int n5, final boolean b) {
        try {
            final int length = array8.length;
            final int[] array12 = new int[length * 3];
            final int[] array13 = new int[length * 3];
            final int[] array14 = new int[3 * length];
            final int[] array15 = new int[length * 3];
            final int[] array16 = new int[3 * length];
            final int[] array17 = new int[length * 3];
            final int[] array18 = (int[])((array2 != null) ? new int[3 * length] : null);
            final int[] array19 = (int[])((array4 == null) ? null : new int[3 * length]);
            int n6 = 0;
            for (int i = 0; i < length; ++i) {
                final int n7 = array5[i];
                final int n8 = array6[i];
                final int n9 = array7[i];
                array12[n6] = array[n7];
                array13[n6] = array3[n7];
                array14[n6] = array8[i];
                array16[n6] = array10[i];
                array17[n6] = array11[i];
                array15[n6] = ((array9 == null) ? array8[i] : array9[i]);
                if (array2 != null) {
                    array18[n6] = array2[n7];
                }
                if (array4 != null) {
                    array19[n6] = array4[n7];
                }
                ++n6;
                array12[n6] = array[n8];
                array13[n6] = array3[n8];
                array14[n6] = array8[i];
                array16[n6] = array10[i];
                array17[n6] = array11[i];
                array15[n6] = ((array9 != null) ? array9[i] : array8[i]);
                if (array2 != null) {
                    array18[n6] = array2[n8];
                }
                if (array4 != null) {
                    array19[n6] = array4[n8];
                }
                ++n6;
                array12[n6] = array[n9];
                array13[n6] = array3[n9];
                array14[n6] = array8[i];
                array16[n6] = array10[i];
                array17[n6] = array11[i];
                array15[n6] = ((array9 == null) ? array8[i] : array9[i]);
                if (array2 != null) {
                    array18[n6] = array2[n9];
                }
                if (array4 != null) {
                    array19[n6] = array4[n9];
                }
                ++n6;
            }
            this.U(n, n2, array12, array18, array13, array19, array14, array15, array16, array17, n3, n4, n5, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.H(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ',' + ((array4 != null) ? "{...}" : "null") + ',' + ((array5 != null) ? "{...}" : "null") + ',' + ((array6 != null) ? "{...}" : "null") + ',' + ((array7 != null) ? "{...}" : "null") + ',' + ((array8 != null) ? "{...}" : "null") + ',' + ((array9 != null) ? "{...}" : "null") + ',' + ((array10 != null) ? "{...}" : "null") + ',' + ((array11 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ')');
        }
    }
    
    @Override
    final void CA(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            if (this.aClass15_5235 != null && r != null) {
                this.aClass15_5235.method231(r, n - (n2 * this.aHa_Sub3_5225.anInt4564 >> -1331666552) >> this.aHa_Sub3_5225.anInt4573, (byte)60, n3 - (n2 * this.aHa_Sub3_5225.anInt4552 >> -1140973816) >> this.aHa_Sub3_5225.anInt4573);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.CA(" + ((r != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    @Override
    final void method3422(final int n, final int n2, final int n3, final int n4, final int n5, final int i, final int n6, final boolean[][] array) {
        try {
            if (this.anInt5250 > 0) {
                final Interface2_Impl2 method1963 = this.aHa_Sub3_5225.method1963(71, this.anInt5227);
                int n7 = 0;
                int n8 = 32767;
                int n9 = -32768;
                for (int j = 0; j < 4; ++j) {
                    n7 = 0;
                    final Buffer method1964 = method1963.method78(true, -123);
                    if (method1964 != null) {
                        final Stream method1965 = this.aHa_Sub3_5225.method2043(24022, method1964);
                        if (Stream.a()) {
                            for (int k = n5; k < n6; ++k) {
                                int n10 = n4 + super.anInt2203 * k;
                                for (int n11 = n4; ~i < ~n11; ++n11) {
                                    if (array[n11 - n4][-n5 + k]) {
                                        final short[] array2 = this.aShortArrayArray5230[n10];
                                        if (array2 != null) {
                                            for (int l = 0; l < array2.length; ++l) {
                                                final int n12 = 0xFFFF & array2[l];
                                                ++n7;
                                                if (~n9 > ~n12) {
                                                    n9 = n12;
                                                }
                                                method1965.c(n12);
                                                if (~n12 > ~n8) {
                                                    n8 = n12;
                                                }
                                            }
                                        }
                                    }
                                    ++n10;
                                }
                            }
                        }
                        else {
                            for (int n13 = n5; n13 < n6; ++n13) {
                                int n14 = n13 * super.anInt2203 + n4;
                                for (int n15 = n4; i > n15; ++n15) {
                                    if (array[n15 + -n4][-n5 + n13]) {
                                        final short[] array3 = this.aShortArrayArray5230[n14];
                                        if (array3 != null) {
                                            for (int n16 = 0; ~array3.length < ~n16; ++n16) {
                                                final int n17 = array3[n16] & 0xFFFF;
                                                if (~n9 > ~n17) {
                                                    n9 = n17;
                                                }
                                                if (~n8 < ~n17) {
                                                    n8 = n17;
                                                }
                                                method1965.d(n17);
                                                ++n7;
                                            }
                                        }
                                    }
                                    ++n14;
                                }
                            }
                        }
                        method1965.c();
                        if (method1963.method79((byte)109)) {
                            break;
                        }
                    }
                }
                if (~n7 < -1) {
                    this.aHa_Sub3_5225.method1992((byte)125);
                    this.aHa_Sub3_5225.method2028(false, (byte)(-101));
                    this.aHa_Sub3_5225.method1979(false, -85);
                    this.aHa_Sub3_5225.method2013(false, 2103);
                    this.aHa_Sub3_5225.method1997(0, false);
                    this.aHa_Sub3_5225.method2001(0, 74);
                    this.aHa_Sub3_5225.method2039(false, 0, -2, false);
                    this.aHa_Sub3_5225.method2005(null, 24);
                    final Class111_Sub3 method1966 = this.aHa_Sub3_5225.method1978((byte)(-6));
                    final float[] method1967 = this.aHa_Sub3_5225.method2034(-113);
                    method1967[0] = n3 / (this.aHa_Sub3_5225.anInt4527 * (128.0f * super.anInt2206));
                    method1967[5] = n3 / (super.anInt2206 * 128.0f * this.aHa_Sub3_5225.anInt4531);
                    method1967[6] = (method1967[9] = 0.0f);
                    method1967[10] = 1.0f / (this.aFloat5243 - this.aFloat5238);
                    method1967[14] = -this.aFloat5238 / (this.aFloat5243 - this.aFloat5238);
                    method1967[13] = 1.0f - (n3 * n6 / 128.0f + n2 * 2) / this.aHa_Sub3_5225.anInt4531;
                    method1967[7] = 0.0f;
                    method1967[8] = (method1967[2] = 0.0f);
                    method1967[1] = (method1967[11] = 0.0f);
                    method1967[15] = 1.0f;
                    method1967[3] = (method1967[4] = 0.0f);
                    method1967[12] = -1.0f - (n4 * n3 / 128.0f - 2 * n) / this.aHa_Sub3_5225.anInt4527;
                    method1966.method2123(0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, (byte)(-110));
                    this.aHa_Sub3_5225.method1940((byte)(-99));
                    this.aHa_Sub3_5225.method1935(1);
                    if (~(0x7 & this.anInt5233) == -1) {
                        this.aHa_Sub3_5225.method1979(false, -91);
                    }
                    else {
                        this.aHa_Sub3_5225.method1979(true, -116);
                        this.aHa_Sub3_5225.method2065((byte)80);
                    }
                    this.aHa_Sub3_5225.method2059(false, false);
                    this.aHa_Sub3_5225.method1971(0, true, this.anInterface2_Impl1_5249);
                    this.aHa_Sub3_5225.method1971(1, true, this.anInterface2_Impl1_5245);
                    this.aHa_Sub3_5225.method2042(this.aClass256_5252, (byte)(-101));
                    this.aHa_Sub3_5225.method1973(Class336.aClass232_2822, -n8 + (n9 + 1), 0, 26810, method1963, n8, n7 / 3);
                    this.aHa_Sub3_5225.method2059(true, false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.F(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + i + ',' + n6 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void wa(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            if (this.aClass15_5235 != null && r != null) {
                this.aClass15_5235.method232(r, (byte)(-102), -(this.aHa_Sub3_5225.anInt4564 * n2 >> 164954408) + n >> this.aHa_Sub3_5225.anInt4573, n3 - (this.aHa_Sub3_5225.anInt4552 * n2 >> 275343208) >> this.aHa_Sub3_5225.anInt4573);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.wa(" + ((r != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method3418(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            return this.aClass15_5235 != null && r != null && this.aClass15_5235.method234(n3 + -(this.aHa_Sub3_5225.anInt4552 * n2 >> 182333608) >> this.aHa_Sub3_5225.anInt4573, r, 8, -(this.aHa_Sub3_5225.anInt4564 * n2 >> 1080435208) + n >> this.aHa_Sub3_5225.anInt4573);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.O(" + ((r != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    private final void method3436(final boolean b, final int n, final int n2, final r_Sub2 r_Sub2) {
        try {
            final int[] array = this.anIntArrayArrayArray5226[n][n2];
            if (!b) {
                this.method3421(null, null);
            }
            final int[] array2 = this.anIntArrayArrayArray5240[n][n2];
            final int length = array.length;
            if (length > Class374.anIntArray3156.length) {
                Class224_Sub1.anIntArray5033 = new int[length];
                Class374.anIntArray3156 = new int[length];
            }
            for (int i = 0; i < length; ++i) {
                Class374.anIntArray3156[i] = array[i] >> this.aHa_Sub3_5225.anInt4573;
                Class224_Sub1.anIntArray5033[i] = array2[i] >> this.aHa_Sub3_5225.anInt4573;
            }
            int j = 0;
            while (j < length) {
                final int n3 = Class374.anIntArray3156[j];
                final int n4 = Class224_Sub1.anIntArray5033[j++];
                final int n5 = Class374.anIntArray3156[j];
                final int n6 = Class224_Sub1.anIntArray5033[j++];
                final int n7 = Class374.anIntArray3156[j];
                final int n8 = Class224_Sub1.anIntArray5033[j++];
                if (-((-n4 + n6) * (-n5 + n7)) + (-n8 + n6) * (n3 - n5) > 0) {
                    r_Sub2.method1653(n4, n3, n5, n7, n8, (byte)(-69), n6);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.B(" + b + ',' + n + ',' + n2 + ',' + ((r_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method3421(final Class98_Sub5 class98_Sub5, final int[] array) {
        try {
            this.aClass148_5231.method2419(new Class98_Sub25(this.aHa_Sub3_5225, this, class98_Sub5, array), -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.K(" + ((class98_Sub5 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    s_Sub2(final ha_Sub3 aHa_Sub3_5225, final int n, final int anInt5233, final int n2, final int n3, final int[][] array, final int[][] array2, final int n4) {
        super(n2, n3, n4, array);
        this.aFloat5238 = Float.MAX_VALUE;
        this.aFloat5243 = -3.4028235E38f;
        this.aClass148_5231 = new Class148();
        try {
            this.aHa_Sub3_5225 = aHa_Sub3_5225;
            this.anInt5229 = -2 + super.anInt2200;
            this.aByteArrayArray5234 = new byte[n2][n3];
            this.anIntArrayArrayArray5242 = new int[n2][n3][];
            this.anInt5232 = 1 << this.anInt5229;
            this.anInt5233 = anInt5233;
            this.anIntArrayArrayArray5240 = new int[n2][n3][];
            this.aClass98_Sub30ArrayArrayArray5236 = new Class98_Sub30[n2][n3][];
            this.aShortArrayArray5230 = new short[n2 * n3][];
            this.aFloatArrayArray5248 = new float[super.anInt2203 + 1][super.anInt2204 + 1];
            this.aFloatArrayArray5251 = new float[super.anInt2203 + 1][1 + super.anInt2204];
            this.anIntArrayArrayArray5228 = new int[n2][n3][];
            this.anIntArrayArrayArray5226 = new int[n2][n3][];
            this.aByteArrayArray5253 = new byte[1 + n2][n3 + 1];
            this.anIntArrayArrayArray5241 = new int[n2][n3][];
            this.aFloatArrayArray5254 = new float[super.anInt2203 + 1][super.anInt2204 + 1];
            for (int n5 = 0; ~n5 >= ~super.anInt2204; ++n5) {
                for (int n6 = 0; ~n6 >= ~super.anInt2203; ++n6) {
                    final int n7 = super.anIntArrayArray2201[n6][n5];
                    if (this.aFloat5243 < n7) {
                        this.aFloat5243 = n7;
                    }
                    if (n7 < this.aFloat5238) {
                        this.aFloat5238 = n7;
                    }
                    if (n6 > 0 && ~n5 < -1 && n6 < super.anInt2203 && n5 < super.anInt2204) {
                        final int n8 = array2[1 + n6][n5] - array2[n6 - 1][n5];
                        final int n9 = -array2[n6][-1 + n5] + array2[n6][n5 + 1];
                        final float n10 = (float)(1.0 / Math.sqrt(n8 * n8 + (n4 * 4 * n4 + n9 * n9)));
                        this.aFloatArrayArray5251[n6][n5] = n8 * n10;
                        this.aFloatArrayArray5248[n6][n5] = n10 * (2 * -n4);
                        this.aFloatArrayArray5254[n6][n5] = n10 * n9;
                    }
                }
            }
            --this.aFloat5238;
            ++this.aFloat5243;
            this.aClass377_5247 = new Class377(128);
            if ((0x10 & this.anInt5233) != 0x0) {
                this.aClass15_5235 = new Class15(this.aHa_Sub3_5225, this);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.<init>(" + ((aHa_Sub3_5225 != null) ? "{...}" : "null") + ',' + n + ',' + anInt5233 + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method3426(final int n, final int n2, final int n3, final boolean[][] array, final boolean b, final int n4) {
        try {
            this.method3435(n4, n, n2, b, array, n3, -1, (byte)(-127));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.C(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method3425(final int n, final int n2) {
    }
    
    @Override
    final void U(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int[] array8, final int n3, final int n4, final int n5, final boolean b) {
        try {
            final d ad938 = this.aHa_Sub3_5225.aD938;
            if (array4 != null && this.anIntArrayArrayArray5239 == null) {
                this.anIntArrayArrayArray5239 = new int[super.anInt2203][super.anInt2204][];
            }
            if (array2 != null && this.anIntArrayArrayArray5228 == null) {
                this.anIntArrayArrayArray5228 = new int[super.anInt2203][super.anInt2204][];
            }
            this.anIntArrayArrayArray5226[n][n2] = array;
            this.anIntArrayArrayArray5240[n][n2] = array3;
            this.anIntArrayArrayArray5241[n][n2] = array5;
            this.anIntArrayArrayArray5242[n][n2] = array6;
            if (this.anIntArrayArrayArray5239 != null) {
                this.anIntArrayArrayArray5239[n][n2] = array4;
            }
            if (this.anIntArrayArrayArray5228 != null) {
                this.anIntArrayArrayArray5228[n][n2] = array2;
            }
            final Class98_Sub30[][] array9 = this.aClass98_Sub30ArrayArrayArray5236[n];
            final Class98_Sub30[] array10 = new Class98_Sub30[array5.length];
            array9[n2] = array10;
            final Class98_Sub30[] array11 = array10;
            for (int i = 0; i < array5.length; ++i) {
                int n6 = array7[i];
                int n7 = array8[i];
                if (~(this.anInt5233 & 0x20) != -1 && ~n6 != 0x0 && ad938.method11(n6, -28755).aBoolean1825) {
                    n7 = 128;
                    n6 = -1;
                }
                final long n8 = n3 << 940517980 | (n5 << -648515088 | n4 << -670963670) | n7 << 1221309550 | n6;
                Class98 class98;
                for (class98 = this.aClass377_5247.method3990(n8, -1); class98 != null; class98 = this.aClass377_5247.method3993(-95)) {
                    final Class98_Sub30 class98_Sub30 = (Class98_Sub30)class98;
                    if (~class98_Sub30.anInt4084 == ~n6 && n7 == class98_Sub30.aFloat4092 && ~n3 == ~class98_Sub30.anInt4091 && class98_Sub30.anInt4090 == n4 && n5 == class98_Sub30.anInt4086) {
                        break;
                    }
                }
                if (class98 == null) {
                    array11[i] = new Class98_Sub30(this, n6, n7, n3, n4, n5);
                    this.aClass377_5247.method3996(array11[i], n8, -1);
                }
                else {
                    array11[i] = (Class98_Sub30)class98;
                }
            }
            if (b) {
                this.aByteArrayArray5234[n][n2] = (byte)Class41.method366(this.aByteArrayArray5234[n][n2], 1);
            }
            if (array5.length > this.anInt5244) {
                this.anInt5244 = array5.length;
            }
            this.anInt5250 += array5.length;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.U(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ',' + ((array4 != null) ? "{...}" : "null") + ',' + ((array5 != null) ? "{...}" : "null") + ',' + ((array6 != null) ? "{...}" : "null") + ',' + ((array7 != null) ? "{...}" : "null") + ',' + ((array8 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ')');
        }
    }
    
    @Override
    final void method3416(final int n, final int n2, final int n3, final boolean[][] array, final boolean b, final int n4, final int n5) {
        try {
            this.method3435(n5, n, n2, b, array, n3, n4, (byte)(-98));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "si.N(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ',' + n4 + ',' + n5 + ')');
        }
    }
}
