import jaclib.memory.Buffer;
import jaclib.memory.Stream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class146_Sub3 extends Class146
{
    private ha_Sub3 aHa_Sub3_4899;
    static OutgoingOpcode aClass171_4900;
    private short[] aShortArray4901;
    private short aShort4902;
    private int[][] anIntArrayArray4903;
    private short[] aShortArray4904;
    private short aShort4905;
    private int[] anIntArray4906;
    private int[] anIntArray4907;
    private boolean aBoolean4908;
    private int anInt4909;
    private int anInt4910;
    private int anInt4911;
    private int anInt4912;
    private Class181[] aClass181Array4913;
    private float[] aFloatArray4914;
    private short[] aShortArray4915;
    private boolean aBoolean4916;
    private short[] aShortArray4917;
    private int[] anIntArray4918;
    private Class35[] aClass35Array4919;
    private Class189 aClass189_4920;
    private int anInt4921;
    private boolean aBoolean4922;
    private Class168 aClass168_4923;
    private int anInt4924;
    private int[][] anIntArrayArray4925;
    static boolean aBoolean4926;
    private Class189 aClass189_4927;
    private byte[] aByteArray4928;
    private int anInt4929;
    private Class189 aClass189_4930;
    private Class18 aClass18_4931;
    private int[] anIntArray4932;
    private Class189 aClass189_4933;
    private int[][] anIntArrayArray4934;
    private int[] anIntArray4935;
    private int anInt4936;
    private int anInt4937;
    private boolean aBoolean4938;
    private int anInt4939;
    private boolean aBoolean4940;
    private Class281[] aClass281Array4941;
    private int anInt4942;
    private int anInt4943;
    private short[] aShortArray4944;
    private int[] anIntArray4945;
    private short[] aShortArray4946;
    private short[] aShortArray4947;
    private int anInt4948;
    static Class213 aClass213_4949;
    private int anInt4950;
    private Class87[] aClass87Array4951;
    static Class305_Sub1 aClass305_Sub1_4952;
    private short[] aShortArray4953;
    private short[] aShortArray4954;
    private float[] aFloatArray4955;
    private int anInt4956;
    private short[] aShortArray4957;
    private byte[] aByteArray4958;
    private int[] anIntArray4959;
    private short[] aShortArray4960;
    private short[] aShortArray4961;
    private short[] aShortArray4962;
    private int anInt4963;
    
    private final void method2392(final int n) {
        try {
            if (this.aClass189_4920 != null) {
                this.aClass189_4920.aBoolean1458 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.K(" + n + ')');
        }
    }
    
    @Override
    final int fa() {
        try {
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            return this.anInt4956;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.fa()");
        }
    }
    
    @Override
    final void method2325(final Class111 class111, final Class246_Sub6 class246_Sub6, final int n) {
        try {
            if (this.anInt4936 != 0) {
                final Class111_Sub3 aClass111_Sub3_4543 = this.aHa_Sub3_4899.aClass111_Sub3_4543;
                if (!this.aBoolean4922) {
                    this.method2401(-21065);
                }
                final Class111_Sub3 class111_Sub3 = (Class111_Sub3)class111;
                Class64_Sub18.aFloat3691 = aClass111_Sub3_4543.aFloat4713 * class111_Sub3.aFloat4706 + aClass111_Sub3_4543.aFloat4708 * class111_Sub3.aFloat4711 + class111_Sub3.aFloat4713 * aClass111_Sub3_4543.aFloat4704;
                Class322.aFloat2712 = aClass111_Sub3_4543.aFloat4703 + (class111_Sub3.aFloat4702 * aClass111_Sub3_4543.aFloat4708 + class111_Sub3.aFloat4709 * aClass111_Sub3_4543.aFloat4713 + class111_Sub3.aFloat4703 * aClass111_Sub3_4543.aFloat4704);
                final float n2 = Class322.aFloat2712 + this.anInt4956 * Class64_Sub18.aFloat3691;
                final float n3 = this.anInt4950 * Class64_Sub18.aFloat3691 + Class322.aFloat2712;
                float n4;
                float n5;
                if (n2 > n3) {
                    n4 = -this.anInt4939 + n3;
                    n5 = n2 + this.anInt4939;
                }
                else {
                    n4 = -this.anInt4939 + n2;
                    n5 = n3 + this.anInt4939;
                }
                if (n4 < this.aHa_Sub3_4899.aFloat4568 && n5 > this.aHa_Sub3_4899.anInt4640) {
                    Class98_Sub43_Sub2.aFloat5909 = aClass111_Sub3_4543.aFloat4702 + (class111_Sub3.aFloat4703 * aClass111_Sub3_4543.aFloat4705 + (class111_Sub3.aFloat4709 * aClass111_Sub3_4543.aFloat4711 + class111_Sub3.aFloat4702 * aClass111_Sub3_4543.aFloat4712));
                    Class98_Sub42.aFloat4234 = class111_Sub3.aFloat4713 * aClass111_Sub3_4543.aFloat4705 + (aClass111_Sub3_4543.aFloat4711 * class111_Sub3.aFloat4706 + class111_Sub3.aFloat4711 * aClass111_Sub3_4543.aFloat4712);
                    final float n6 = Class98_Sub42.aFloat4234 * this.anInt4956 + Class98_Sub43_Sub2.aFloat5909;
                    final float n7 = Class98_Sub42.aFloat4234 * this.anInt4950 + Class98_Sub43_Sub2.aFloat5909;
                    float n8;
                    float n9;
                    if (n7 >= n6) {
                        n8 = (n7 + this.anInt4939) * this.aHa_Sub3_4899.anInt4593;
                        n9 = this.aHa_Sub3_4899.anInt4593 * (-this.anInt4939 + n6);
                    }
                    else {
                        n8 = (n6 + this.anInt4939) * this.aHa_Sub3_4899.anInt4593;
                        n9 = (n7 - this.anInt4939) * this.aHa_Sub3_4899.anInt4593;
                    }
                    if (this.aHa_Sub3_4899.aFloat4647 > n9 / n5 && this.aHa_Sub3_4899.aFloat4641 < n8 / n5) {
                        Class366.aFloat3120 = aClass111_Sub3_4543.aFloat4714 * class111_Sub3.aFloat4711 + aClass111_Sub3_4543.aFloat4706 * class111_Sub3.aFloat4706 + class111_Sub3.aFloat4713 * aClass111_Sub3_4543.aFloat4710;
                        Class339_Sub1.aFloat5316 = aClass111_Sub3_4543.aFloat4709 + (class111_Sub3.aFloat4703 * aClass111_Sub3_4543.aFloat4710 + (class111_Sub3.aFloat4702 * aClass111_Sub3_4543.aFloat4714 + aClass111_Sub3_4543.aFloat4706 * class111_Sub3.aFloat4709));
                        final float n10 = Class339_Sub1.aFloat5316 + this.anInt4956 * Class366.aFloat3120;
                        final float n11 = Class339_Sub1.aFloat5316 + Class366.aFloat3120 * this.anInt4950;
                        float n12;
                        float n13;
                        if (n11 >= n10) {
                            n12 = this.aHa_Sub3_4899.anInt4589 * (n10 - this.anInt4939);
                            n13 = this.aHa_Sub3_4899.anInt4589 * (this.anInt4939 + n11);
                        }
                        else {
                            n12 = (n11 - this.anInt4939) * this.aHa_Sub3_4899.anInt4589;
                            n13 = this.aHa_Sub3_4899.anInt4589 * (this.anInt4939 + n10);
                        }
                        if (this.aHa_Sub3_4899.aFloat4584 > n12 / n5 && this.aHa_Sub3_4899.aFloat4610 < n13 / n5) {
                            if (class246_Sub6 != null || this.aClass281Array4941 != null) {
                                Class373_Sub2.aFloat5472 = aClass111_Sub3_4543.aFloat4712 * class111_Sub3.aFloat4705 + class111_Sub3.aFloat4710 * aClass111_Sub3_4543.aFloat4711 + class111_Sub3.aFloat4704 * aClass111_Sub3_4543.aFloat4705;
                                Class64_Sub17.aFloat3686 = aClass111_Sub3_4543.aFloat4708 * class111_Sub3.aFloat4712 + class111_Sub3.aFloat4714 * aClass111_Sub3_4543.aFloat4713 + class111_Sub3.aFloat4708 * aClass111_Sub3_4543.aFloat4704;
                                Canvas_Sub1.aFloat25 = aClass111_Sub3_4543.aFloat4711 * class111_Sub3.aFloat4714 + class111_Sub3.aFloat4712 * aClass111_Sub3_4543.aFloat4712 + aClass111_Sub3_4543.aFloat4705 * class111_Sub3.aFloat4708;
                                Class98_Sub18.aFloat3948 = aClass111_Sub3_4543.aFloat4706 * class111_Sub3.aFloat4714 + aClass111_Sub3_4543.aFloat4714 * class111_Sub3.aFloat4712 + class111_Sub3.aFloat4708 * aClass111_Sub3_4543.aFloat4710;
                                Class100.aFloat845 = class111_Sub3.aFloat4704 * aClass111_Sub3_4543.aFloat4710 + (aClass111_Sub3_4543.aFloat4714 * class111_Sub3.aFloat4705 + class111_Sub3.aFloat4710 * aClass111_Sub3_4543.aFloat4706);
                                Class134.aFloat3463 = aClass111_Sub3_4543.aFloat4713 * class111_Sub3.aFloat4710 + aClass111_Sub3_4543.aFloat4708 * class111_Sub3.aFloat4705 + aClass111_Sub3_4543.aFloat4704 * class111_Sub3.aFloat4704;
                            }
                            if (class246_Sub6 != null) {
                                boolean b = false;
                                boolean b2 = true;
                                final int n14 = this.anInt4910 + this.anInt4924 >> -394601631;
                                final int n15 = this.anInt4937 + this.anInt4921 >> -732566495;
                                final int n16 = (int)(Class373_Sub2.aFloat5472 * n15 + (this.anInt4956 * Class98_Sub42.aFloat4234 + (n14 * Canvas_Sub1.aFloat25 + Class98_Sub43_Sub2.aFloat5909)));
                                final int n17 = (int)(this.anInt4956 * Class366.aFloat3120 + (Class339_Sub1.aFloat5316 + Class98_Sub18.aFloat3948 * n14) + n15 * Class100.aFloat845);
                                final int n18 = (int)(Class322.aFloat2712 + n14 * Class64_Sub17.aFloat3686 + this.anInt4956 * Class64_Sub18.aFloat3691 + n15 * Class134.aFloat3463);
                                if (~n18 > ~this.aHa_Sub3_4899.anInt4640) {
                                    b = true;
                                }
                                else {
                                    class246_Sub6.anInt5111 = this.aHa_Sub3_4899.anInt4645 + this.aHa_Sub3_4899.anInt4593 * n16 / n18;
                                    class246_Sub6.anInt5113 = this.aHa_Sub3_4899.anInt4587 + this.aHa_Sub3_4899.anInt4589 * n17 / n18;
                                }
                                final int n19 = (int)(Class373_Sub2.aFloat5472 * n15 + (n14 * Canvas_Sub1.aFloat25 + Class98_Sub43_Sub2.aFloat5909 + this.anInt4950 * Class98_Sub42.aFloat4234));
                                final int n20 = (int)(n15 * Class100.aFloat845 + (n14 * Class98_Sub18.aFloat3948 + Class339_Sub1.aFloat5316 + this.anInt4950 * Class366.aFloat3120));
                                final int n21 = (int)(n14 * Class64_Sub17.aFloat3686 + Class322.aFloat2712 + this.anInt4950 * Class64_Sub18.aFloat3691 + n15 * Class134.aFloat3463);
                                if (~n21 > ~this.aHa_Sub3_4899.anInt4640) {
                                    b = true;
                                }
                                else {
                                    class246_Sub6.anInt5110 = this.aHa_Sub3_4899.anInt4645 - -(n19 * this.aHa_Sub3_4899.anInt4593 / n21);
                                    class246_Sub6.anInt5112 = this.aHa_Sub3_4899.anInt4587 - -(this.aHa_Sub3_4899.anInt4589 * n20 / n21);
                                }
                                if (b) {
                                    if (~this.aHa_Sub3_4899.anInt4640 < ~n18 && this.aHa_Sub3_4899.anInt4640 > n21) {
                                        b2 = false;
                                    }
                                    else if (n18 < this.aHa_Sub3_4899.anInt4640) {
                                        final int n22 = (n21 - this.aHa_Sub3_4899.anInt4640 << 328899056) / (-n18 + n21);
                                        final int n23 = n19 + ((-n16 + n19) * n22 >> 829085296);
                                        final int n24 = (n22 * (n20 - n17) >> 530982992) + n20;
                                        class246_Sub6.anInt5111 = n23 * this.aHa_Sub3_4899.anInt4593 / this.aHa_Sub3_4899.anInt4640 + this.aHa_Sub3_4899.anInt4645;
                                        class246_Sub6.anInt5113 = this.aHa_Sub3_4899.anInt4587 + this.aHa_Sub3_4899.anInt4589 * n24 / this.aHa_Sub3_4899.anInt4640;
                                    }
                                    else if (~this.aHa_Sub3_4899.anInt4640 < ~n21) {
                                        final int n25 = (-this.aHa_Sub3_4899.anInt4640 + n18 << -1759208752) / (n18 + -n21);
                                        class246_Sub6.anInt5111 = this.aHa_Sub3_4899.anInt4593 * (n16 + ((-n19 + n16) * n25 >> 20455536)) / this.aHa_Sub3_4899.anInt4640 + this.aHa_Sub3_4899.anInt4645;
                                        class246_Sub6.anInt5113 = this.aHa_Sub3_4899.anInt4587 + this.aHa_Sub3_4899.anInt4589 * (n17 - -(n25 * (-n20 + n17) >> 1506998864)) / this.aHa_Sub3_4899.anInt4640;
                                    }
                                }
                                if (b2) {
                                    if (n21 >= n18) {
                                        class246_Sub6.anInt5109 = this.aHa_Sub3_4899.anInt4645 - (-(this.aHa_Sub3_4899.anInt4593 * (n19 - -this.anInt4939) / n21) - -class246_Sub6.anInt5110);
                                    }
                                    else {
                                        class246_Sub6.anInt5109 = -class246_Sub6.anInt5111 + this.aHa_Sub3_4899.anInt4593 * (n16 - -this.anInt4939) / n18 + this.aHa_Sub3_4899.anInt4645;
                                    }
                                    class246_Sub6.aBoolean5114 = true;
                                }
                            }
                            this.aHa_Sub3_4899.method1976(-112);
                            this.aHa_Sub3_4899.method2049(0, class111_Sub3);
                            this.method2407(29084);
                            this.method2393(-29619);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.D(" + ((class111 != null) ? "{...}" : "null") + ',' + ((class246_Sub6 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void C(final int n) {
        try {
            this.aShort4902 = (short)n;
            this.method2392(105);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.C(" + n + ')');
        }
    }
    
    @Override
    final boolean r() {
        try {
            return this.aBoolean4940;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.r()");
        }
    }
    
    private final void method2393(final int n) {
        try {
            if (n != -29619) {
                this.ua();
            }
            if (this.aClass281Array4941 != null) {
                this.aHa_Sub3_4899.C(!this.aBoolean4938);
                this.aHa_Sub3_4899.method1979(false, -71);
                this.aHa_Sub3_4899.method2051(1, -79, Class64_Sub16.aClass65_3681);
                this.aHa_Sub3_4899.method1953(-90, Class64_Sub16.aClass65_3681, 1);
                for (int n2 = 0; ~n2 > ~this.anInt4911; ++n2) {
                    final Class281 class281 = this.aClass281Array4941[n2];
                    final Class181 class282 = this.aClass181Array4913[n2];
                    if (!class281.aBoolean2118 || !this.aHa_Sub3_4899.method1768()) {
                        final float n3 = (this.anIntArray4906[class281.anInt2120] + (this.anIntArray4906[class281.anInt2122] + this.anIntArray4906[class281.anInt2115])) * 0.3333333f;
                        final float n4 = (this.anIntArray4959[class281.anInt2122] + this.anIntArray4959[class281.anInt2120] - -this.anIntArray4959[class281.anInt2115]) * 0.3333333f;
                        final float n5 = 0.3333333f * (this.anIntArray4935[class281.anInt2115] + this.anIntArray4935[class281.anInt2122] - -this.anIntArray4935[class281.anInt2120]);
                        final float n6 = Class98_Sub43_Sub2.aFloat5909 + (n5 * Class373_Sub2.aFloat5472 + (n4 * Class98_Sub42.aFloat4234 + Canvas_Sub1.aFloat25 * n3));
                        final float n7 = Class339_Sub1.aFloat5316 + (n3 * Class98_Sub18.aFloat3948 + Class366.aFloat3120 * n4 + Class100.aFloat845 * n5);
                        final float n8 = Class134.aFloat3463 * n5 + (Class64_Sub17.aFloat3686 * n3 + Class64_Sub18.aFloat3691 * n4) + Class322.aFloat2712;
                        final float n9 = (float)(1.0 / Math.sqrt(n8 * n8 + (n6 * n6 + n7 * n7))) * class281.anInt2116;
                        final Class111_Sub3 method1978 = this.aHa_Sub3_4899.method1978((byte)108);
                        method1978.method2131(n6 + class282.anInt1426 - n6 * n9, false, class281.aShort2123 * class282.anInt1429 >> -2068583673, class282.anInt1425 * class281.aShort2119 >> -808665913, n7 + class282.anInt1427 - n9 * n7, -(n8 * n9) + n8, class282.anInt1431);
                        method1978.method2124(true, this.aHa_Sub3_4899.aClass111_Sub3_4544);
                        this.aHa_Sub3_4899.method1935(1);
                        final int anInt1428 = class282.anInt1428;
                        this.aHa_Sub3_4899.method2039(false, 0, class281.aShort2114, false);
                        this.aHa_Sub3_4899.method2001(class281.aByte2113, 112);
                        this.aHa_Sub3_4899.method1984(n + 29621, anInt1428);
                        this.aHa_Sub3_4899.method2002((byte)96);
                    }
                }
                this.aHa_Sub3_4899.method1953(-87, IncomingOpcode.aClass65_459, 1);
                this.aHa_Sub3_4899.method2051(1, n ^ 0x73DC, IncomingOpcode.aClass65_459);
                this.aHa_Sub3_4899.C(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.JA(" + n + ')');
        }
    }
    
    @Override
    final void wa() {
        try {
            for (int n = 0; ~this.anInt4929 < ~n; ++n) {
                this.anIntArray4906[n] = 7 + this.anIntArray4906[n] >> -1178229788;
                this.anIntArray4959[n] = 7 + this.anIntArray4959[n] >> 1103469156;
                this.anIntArray4935[n] = 7 + this.anIntArray4935[n] >> -1541879036;
            }
            this.method2410(-54);
            this.aBoolean4922 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.wa()");
        }
    }
    
    private final boolean method2394(final int n, final int n2, final int n3, final int n4, final boolean b, final int n5, final Class111 class111) {
        try {
            final Class111_Sub3 class111_Sub3 = (Class111_Sub3)class111;
            final Class111_Sub3 aClass111_Sub3_4543 = this.aHa_Sub3_4899.aClass111_Sub3_4543;
            final float n6 = aClass111_Sub3_4543.aFloat4702 + (class111_Sub3.aFloat4703 * aClass111_Sub3_4543.aFloat4705 + (class111_Sub3.aFloat4702 * aClass111_Sub3_4543.aFloat4712 + aClass111_Sub3_4543.aFloat4711 * class111_Sub3.aFloat4709));
            final float n7 = aClass111_Sub3_4543.aFloat4709 + (aClass111_Sub3_4543.aFloat4714 * class111_Sub3.aFloat4702 + aClass111_Sub3_4543.aFloat4706 * class111_Sub3.aFloat4709 + aClass111_Sub3_4543.aFloat4710 * class111_Sub3.aFloat4703);
            Class366.aFloat3120 = class111_Sub3.aFloat4706 * aClass111_Sub3_4543.aFloat4706 + aClass111_Sub3_4543.aFloat4714 * class111_Sub3.aFloat4711 + aClass111_Sub3_4543.aFloat4710 * class111_Sub3.aFloat4713;
            Class100.aFloat845 = aClass111_Sub3_4543.aFloat4710 * class111_Sub3.aFloat4704 + (aClass111_Sub3_4543.aFloat4706 * class111_Sub3.aFloat4710 + aClass111_Sub3_4543.aFloat4714 * class111_Sub3.aFloat4705);
            Class64_Sub17.aFloat3686 = aClass111_Sub3_4543.aFloat4704 * class111_Sub3.aFloat4708 + (aClass111_Sub3_4543.aFloat4708 * class111_Sub3.aFloat4712 + aClass111_Sub3_4543.aFloat4713 * class111_Sub3.aFloat4714);
            Class98_Sub42.aFloat4234 = class111_Sub3.aFloat4713 * aClass111_Sub3_4543.aFloat4705 + (class111_Sub3.aFloat4706 * aClass111_Sub3_4543.aFloat4711 + aClass111_Sub3_4543.aFloat4712 * class111_Sub3.aFloat4711);
            Class64_Sub18.aFloat3691 = aClass111_Sub3_4543.aFloat4704 * class111_Sub3.aFloat4713 + (class111_Sub3.aFloat4711 * aClass111_Sub3_4543.aFloat4708 + class111_Sub3.aFloat4706 * aClass111_Sub3_4543.aFloat4713);
            Class134.aFloat3463 = class111_Sub3.aFloat4710 * aClass111_Sub3_4543.aFloat4713 + class111_Sub3.aFloat4705 * aClass111_Sub3_4543.aFloat4708 + aClass111_Sub3_4543.aFloat4704 * class111_Sub3.aFloat4704;
            Canvas_Sub1.aFloat25 = aClass111_Sub3_4543.aFloat4705 * class111_Sub3.aFloat4708 + (aClass111_Sub3_4543.aFloat4712 * class111_Sub3.aFloat4712 + aClass111_Sub3_4543.aFloat4711 * class111_Sub3.aFloat4714);
            final float n8 = class111_Sub3.aFloat4709 * aClass111_Sub3_4543.aFloat4713 + class111_Sub3.aFloat4702 * aClass111_Sub3_4543.aFloat4708 + aClass111_Sub3_4543.aFloat4704 * class111_Sub3.aFloat4703 + aClass111_Sub3_4543.aFloat4703;
            Class373_Sub2.aFloat5472 = class111_Sub3.aFloat4704 * aClass111_Sub3_4543.aFloat4705 + (class111_Sub3.aFloat4705 * aClass111_Sub3_4543.aFloat4712 + class111_Sub3.aFloat4710 * aClass111_Sub3_4543.aFloat4711);
            Class98_Sub18.aFloat3948 = class111_Sub3.aFloat4712 * aClass111_Sub3_4543.aFloat4714 + class111_Sub3.aFloat4714 * aClass111_Sub3_4543.aFloat4706 + aClass111_Sub3_4543.aFloat4710 * class111_Sub3.aFloat4708;
            boolean b2 = false;
            float n9 = Float.MAX_VALUE;
            float n10 = -3.4028235E38f;
            float n11 = Float.MAX_VALUE;
            float n12 = -3.4028235E38f;
            final int anInt4593 = this.aHa_Sub3_4899.anInt4593;
            final int anInt4594 = this.aHa_Sub3_4899.anInt4589;
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            final int n13 = -this.anInt4924 + this.anInt4910 >> -2118794815;
            final int n14 = this.anInt4950 - this.anInt4956 >> 1361878209;
            final int n15 = this.anInt4937 + -this.anInt4921 >> 1312818657;
            final int n16 = n13 + this.anInt4924;
            final int n17 = n14 + this.anInt4956;
            final int n18 = this.anInt4921 + n15;
            final int n19 = n16 - (n13 << n5);
            final int n20 = -(n14 << n5) + n17;
            final int n21 = n18 - (n15 << n5);
            final int n22 = n16 + (n13 << n5);
            final int n23 = n17 - -(n14 << n5);
            Class246_Sub3_Sub1_Sub2.anIntArray6245[0] = n19;
            final int n24 = (n15 << n5) + n18;
            Class208.anIntArray1580[0] = n20;
            Class246_Sub3_Sub1_Sub2.anIntArray6245[1] = n22;
            Class253.anIntArray1931[0] = n21;
            Class208.anIntArray1580[1] = n20;
            Class246_Sub3_Sub1_Sub2.anIntArray6245[2] = n19;
            Class253.anIntArray1931[1] = n21;
            Class208.anIntArray1580[2] = n23;
            Class253.anIntArray1931[2] = n21;
            Class246_Sub3_Sub1_Sub2.anIntArray6245[3] = n22;
            Class208.anIntArray1580[3] = n23;
            Class246_Sub3_Sub1_Sub2.anIntArray6245[4] = n19;
            Class253.anIntArray1931[3] = n21;
            Class208.anIntArray1580[4] = n20;
            Class253.anIntArray1931[4] = n24;
            Class246_Sub3_Sub1_Sub2.anIntArray6245[5] = n22;
            Class208.anIntArray1580[5] = n20;
            Class253.anIntArray1931[5] = n24;
            Class246_Sub3_Sub1_Sub2.anIntArray6245[6] = n19;
            Class208.anIntArray1580[6] = n23;
            Class246_Sub3_Sub1_Sub2.anIntArray6245[7] = n22;
            Class253.anIntArray1931[6] = n24;
            Class208.anIntArray1580[7] = n23;
            Class253.anIntArray1931[7] = n24;
            for (int n25 = 0; ~n25 > -9; ++n25) {
                final float n26 = Class208.anIntArray1580[n25];
                final float n27 = Class246_Sub3_Sub1_Sub2.anIntArray6245[n25];
                final float n28 = Class253.anIntArray1931[n25];
                float n29 = Class134.aFloat3463 * n28 + (Class64_Sub17.aFloat3686 * n27 + n26 * Class64_Sub18.aFloat3691) + n8;
                final float n30 = n28 * Class373_Sub2.aFloat5472 + (Class98_Sub42.aFloat4234 * n26 + Canvas_Sub1.aFloat25 * n27) + n6;
                final float n31 = n28 * Class100.aFloat845 + (n27 * Class98_Sub18.aFloat3948 + Class366.aFloat3120 * n26) + n7;
                if (this.aHa_Sub3_4899.anInt4640 <= n29) {
                    if (n3 > 0) {
                        n29 = n3;
                    }
                    final float n32 = n30 * anInt4593 / n29 + this.aHa_Sub3_4899.anInt4645;
                    if (n9 > n32) {
                        n9 = n32;
                    }
                    final float n33 = this.aHa_Sub3_4899.anInt4587 + anInt4594 * n31 / n29;
                    if (n10 < n32) {
                        n10 = n32;
                    }
                    if (n12 < n33) {
                        n12 = n33;
                    }
                    if (n11 > n33) {
                        n11 = n33;
                    }
                    b2 = true;
                }
            }
            if (b2 && n2 > n9 && n10 > n2 && n11 < n4 && n12 > n4) {
                if (b) {
                    return true;
                }
                if (~this.anInt4936 < ~RuntimeException_Sub1.anIntArray3200.length) {
                    Class57.anIntArray458 = new int[this.anInt4936];
                    RuntimeException_Sub1.anIntArray3200 = new int[this.anInt4936];
                }
                for (int n34 = 0; ~n34 > ~this.anInt4912; ++n34) {
                    final float n35 = this.anIntArray4935[n34];
                    final float n36 = this.anIntArray4906[n34];
                    final float n37 = this.anIntArray4959[n34];
                    float n38 = n37 * Class64_Sub18.aFloat3691 + Class64_Sub17.aFloat3686 * n36 + Class134.aFloat3463 * n35 + n8;
                    final float n39 = n6 + (n37 * Class98_Sub42.aFloat4234 + n36 * Canvas_Sub1.aFloat25 + n35 * Class373_Sub2.aFloat5472);
                    final float n40 = n35 * Class100.aFloat845 + (Class98_Sub18.aFloat3948 * n36 + Class366.aFloat3120 * n37) + n7;
                    if (n38 >= this.aHa_Sub3_4899.anInt4640) {
                        if (n3 > 0) {
                            n38 = n3;
                        }
                        final int n41 = (int)(anInt4593 * n39 / n38 + this.aHa_Sub3_4899.anInt4645);
                        final int n42 = (int)(n40 * anInt4594 / n38 + this.aHa_Sub3_4899.anInt4587);
                        final int n43 = this.anIntArray4932[n34];
                        for (int n44 = this.anIntArray4932[n34 + 1], n45 = n43; ~n45 > ~n44; ++n45) {
                            final short n46 = (short)(this.aShortArray4960[n45] - 1);
                            if (n46 == -1) {
                                break;
                            }
                            RuntimeException_Sub1.anIntArray3200[n46] = n41;
                            Class57.anIntArray458[n46] = n42;
                        }
                    }
                    else {
                        final int n47 = this.anIntArray4932[n34];
                        for (int n48 = this.anIntArray4932[n34 + 1], n49 = n47; ~n49 > ~n48; ++n49) {
                            if (~(this.aShortArray4960[n49] - 1) == 0x0) {
                                break;
                            }
                            RuntimeException_Sub1.anIntArray3200[-1 + this.aShortArray4960[n49]] = -999999;
                        }
                    }
                }
                for (int n50 = 0; ~n50 > ~this.anInt4948; ++n50) {
                    if (~RuntimeException_Sub1.anIntArray3200[this.aShortArray4947[n50]] != 0xF423E && RuntimeException_Sub1.anIntArray3200[this.aShortArray4915[n50]] != -999999 && ~RuntimeException_Sub1.anIntArray3200[this.aShortArray4961[n50]] != 0xF423E && this.method2398(n4, RuntimeException_Sub1.anIntArray3200[this.aShortArray4961[n50]], n2, Class57.anIntArray458[this.aShortArray4947[n50]], 73, Class57.anIntArray458[this.aShortArray4915[n50]], RuntimeException_Sub1.anIntArray3200[this.aShortArray4947[n50]], Class57.anIntArray458[this.aShortArray4961[n50]], RuntimeException_Sub1.anIntArray3200[this.aShortArray4915[n50]])) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.R(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ',' + ((class111 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int na() {
        try {
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            return this.anInt4939;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.na()");
        }
    }
    
    private final Class146 method2395(final Class146_Sub3 class146_Sub3, final int anInt4942, final boolean b, final boolean b2, final int n, final Class146_Sub3 class146_Sub4) {
        try {
            class146_Sub4.anInt4942 = anInt4942;
            class146_Sub4.anInt4909 = this.anInt4909;
            class146_Sub4.aShort4902 = this.aShort4902;
            class146_Sub4.aBoolean4940 = this.aBoolean4940;
            class146_Sub4.aShort4905 = this.aShort4905;
            class146_Sub4.anInt4929 = this.anInt4929;
            class146_Sub4.anInt4936 = this.anInt4936;
            class146_Sub4.anInt4963 = this.anInt4963;
            if (~(0x100 & anInt4942) != -1) {
                class146_Sub4.aBoolean4938 = true;
            }
            else {
                class146_Sub4.aBoolean4938 = this.aBoolean4938;
            }
            class146_Sub4.anInt4912 = this.anInt4912;
            class146_Sub4.anInt4911 = this.anInt4911;
            class146_Sub4.anInt4948 = this.anInt4948;
            final boolean method523 = IncomingOpcode.method523(this.anInt4963, -1, anInt4942);
            final boolean method524 = Class105.method1715(true, anInt4942, this.anInt4963);
            final boolean method525 = Class76_Sub9.method766(-124, this.anInt4963, anInt4942);
            if (method524 | method523 | method525) {
                if (method523) {
                    if (class146_Sub3.anIntArray4906 == null || ~this.anInt4929 < ~class146_Sub3.anIntArray4906.length) {
                        final int[] array = new int[this.anInt4929];
                        class146_Sub3.anIntArray4906 = array;
                        class146_Sub4.anIntArray4906 = array;
                    }
                    else {
                        class146_Sub4.anIntArray4906 = class146_Sub3.anIntArray4906;
                    }
                }
                else {
                    class146_Sub4.anIntArray4906 = this.anIntArray4906;
                }
                if (method524) {
                    if (class146_Sub3.anIntArray4959 != null && ~class146_Sub3.anIntArray4959.length <= ~this.anInt4929) {
                        class146_Sub4.anIntArray4959 = class146_Sub3.anIntArray4959;
                    }
                    else {
                        final int[] array2 = new int[this.anInt4929];
                        class146_Sub3.anIntArray4959 = array2;
                        class146_Sub4.anIntArray4959 = array2;
                    }
                }
                else {
                    class146_Sub4.anIntArray4959 = this.anIntArray4959;
                }
                if (method525) {
                    if (class146_Sub3.anIntArray4935 == null || ~this.anInt4929 < ~class146_Sub3.anIntArray4935.length) {
                        final int[] array3 = new int[this.anInt4929];
                        class146_Sub3.anIntArray4935 = array3;
                        class146_Sub4.anIntArray4935 = array3;
                    }
                    else {
                        class146_Sub4.anIntArray4935 = class146_Sub3.anIntArray4935;
                    }
                }
                else {
                    class146_Sub4.anIntArray4935 = this.anIntArray4935;
                }
                for (int i = 0; i < this.anInt4929; ++i) {
                    if (method523) {
                        class146_Sub4.anIntArray4906[i] = this.anIntArray4906[i];
                    }
                    if (method524) {
                        class146_Sub4.anIntArray4959[i] = this.anIntArray4959[i];
                    }
                    if (method525) {
                        class146_Sub4.anIntArray4935[i] = this.anIntArray4935[i];
                    }
                }
            }
            else {
                class146_Sub4.anIntArray4906 = this.anIntArray4906;
                class146_Sub4.anIntArray4959 = this.anIntArray4959;
                class146_Sub4.anIntArray4935 = this.anIntArray4935;
            }
            if (Class238.method2919(-68, anInt4942, this.anInt4963)) {
                class146_Sub4.aClass189_4933 = class146_Sub3.aClass189_4933;
                class146_Sub4.aClass189_4933.aBoolean1459 = true;
                class146_Sub4.aClass189_4933.aBoolean1458 = this.aClass189_4933.aBoolean1458;
                class146_Sub4.aClass189_4933.anInterface2_Impl1_1456 = this.aClass189_4933.anInterface2_Impl1_1456;
            }
            else if (!Class93.method901(this.anInt4963, anInt4942, -112)) {
                class146_Sub4.aClass189_4933 = null;
            }
            else {
                class146_Sub4.aClass189_4933 = this.aClass189_4933;
            }
            if (Class349.method3842(this.anInt4963, anInt4942, -18021)) {
                if (class146_Sub3.aShortArray4946 != null && this.anInt4948 <= class146_Sub3.aShortArray4946.length) {
                    class146_Sub4.aShortArray4946 = class146_Sub3.aShortArray4946;
                }
                else {
                    final short[] array4 = new short[this.anInt4948];
                    class146_Sub3.aShortArray4946 = array4;
                    class146_Sub4.aShortArray4946 = array4;
                }
                for (int n2 = 0; this.anInt4948 > n2; ++n2) {
                    class146_Sub4.aShortArray4946[n2] = this.aShortArray4946[n2];
                }
            }
            else {
                class146_Sub4.aShortArray4946 = this.aShortArray4946;
            }
            if (Class98_Sub22.method1241(false, this.anInt4963, anInt4942)) {
                if (class146_Sub3.aByteArray4958 != null && ~class146_Sub3.aByteArray4958.length <= ~this.anInt4948) {
                    class146_Sub4.aByteArray4958 = class146_Sub3.aByteArray4958;
                }
                else {
                    final byte[] array5 = new byte[this.anInt4948];
                    class146_Sub3.aByteArray4958 = array5;
                    class146_Sub4.aByteArray4958 = array5;
                }
                for (int j = 0; j < this.anInt4948; ++j) {
                    class146_Sub4.aByteArray4958[j] = this.aByteArray4958[j];
                }
            }
            else {
                class146_Sub4.aByteArray4958 = this.aByteArray4958;
            }
            if (Class98_Sub27.method1292(this.anInt4963, (byte)122, anInt4942)) {
                class146_Sub4.aClass189_4920 = class146_Sub3.aClass189_4920;
                class146_Sub4.aClass189_4920.aBoolean1458 = this.aClass189_4920.aBoolean1458;
                class146_Sub4.aClass189_4920.aBoolean1459 = true;
                class146_Sub4.aClass189_4920.anInterface2_Impl1_1456 = this.aClass189_4920.anInterface2_Impl1_1456;
            }
            else if (Class53_Sub1.method502(this.anInt4963, anInt4942, (byte)125)) {
                class146_Sub4.aClass189_4920 = this.aClass189_4920;
            }
            else {
                class146_Sub4.aClass189_4920 = null;
            }
            if (!Class373_Sub3.method3978(this.anInt4963, anInt4942, (byte)112)) {
                class146_Sub4.aShortArray4957 = this.aShortArray4957;
                class146_Sub4.aShortArray4962 = this.aShortArray4962;
                class146_Sub4.aClass168_4923 = this.aClass168_4923;
                class146_Sub4.aByteArray4928 = this.aByteArray4928;
                class146_Sub4.aShortArray4953 = this.aShortArray4953;
            }
            else {
                if (class146_Sub3.aShortArray4953 != null && ~class146_Sub3.aShortArray4953.length <= ~this.anInt4936) {
                    class146_Sub4.aShortArray4953 = class146_Sub3.aShortArray4953;
                    class146_Sub4.aShortArray4957 = class146_Sub3.aShortArray4957;
                    class146_Sub4.aShortArray4962 = class146_Sub3.aShortArray4962;
                }
                else {
                    final int anInt4943 = this.anInt4936;
                    final short[] array6 = new short[anInt4943];
                    class146_Sub3.aShortArray4953 = array6;
                    class146_Sub4.aShortArray4953 = array6;
                    final short[] array7 = new short[anInt4943];
                    class146_Sub3.aShortArray4957 = array7;
                    class146_Sub4.aShortArray4957 = array7;
                    final short[] array8 = new short[anInt4943];
                    class146_Sub3.aShortArray4962 = array8;
                    class146_Sub4.aShortArray4962 = array8;
                }
                if (this.aClass168_4923 != null) {
                    if (class146_Sub3.aClass168_4923 == null) {
                        class146_Sub3.aClass168_4923 = new Class168();
                    }
                    final Class168 aClass168_4923 = class146_Sub3.aClass168_4923;
                    class146_Sub4.aClass168_4923 = aClass168_4923;
                    final Class168 class168 = aClass168_4923;
                    if (class168.aShortArray1288 == null || class168.aShortArray1288.length < this.anInt4936) {
                        final int anInt4944 = this.anInt4936;
                        class168.aByteArray1289 = new byte[anInt4944];
                        class168.aShortArray1292 = new short[anInt4944];
                        class168.aShortArray1288 = new short[anInt4944];
                        class168.aShortArray1291 = new short[anInt4944];
                    }
                    for (int n3 = 0; this.anInt4936 > n3; ++n3) {
                        class146_Sub4.aShortArray4953[n3] = this.aShortArray4953[n3];
                        class146_Sub4.aShortArray4957[n3] = this.aShortArray4957[n3];
                        class146_Sub4.aShortArray4962[n3] = this.aShortArray4962[n3];
                        class168.aShortArray1288[n3] = this.aClass168_4923.aShortArray1288[n3];
                        class168.aShortArray1291[n3] = this.aClass168_4923.aShortArray1291[n3];
                        class168.aShortArray1292[n3] = this.aClass168_4923.aShortArray1292[n3];
                        class168.aByteArray1289[n3] = this.aClass168_4923.aByteArray1289[n3];
                    }
                }
                else {
                    class146_Sub4.aClass168_4923 = null;
                    for (int n4 = 0; ~n4 > ~this.anInt4936; ++n4) {
                        class146_Sub4.aShortArray4953[n4] = this.aShortArray4953[n4];
                        class146_Sub4.aShortArray4957[n4] = this.aShortArray4957[n4];
                        class146_Sub4.aShortArray4962[n4] = this.aShortArray4962[n4];
                    }
                }
                class146_Sub4.aByteArray4928 = this.aByteArray4928;
            }
            if (Class21_Sub3.method276(anInt4942, 15123, this.anInt4963)) {
                class146_Sub4.aClass189_4927 = class146_Sub3.aClass189_4927;
                class146_Sub4.aClass189_4927.anInterface2_Impl1_1456 = this.aClass189_4927.anInterface2_Impl1_1456;
                class146_Sub4.aClass189_4927.aBoolean1459 = true;
                class146_Sub4.aClass189_4927.aBoolean1458 = this.aClass189_4927.aBoolean1458;
            }
            else if (Class98_Sub10_Sub1.method1005(anInt4942, this.anInt4963, (byte)(-23))) {
                class146_Sub4.aClass189_4927 = this.aClass189_4927;
            }
            else {
                class146_Sub4.aClass189_4927 = null;
            }
            if (!s_Sub1.method3432(anInt4942, (byte)(-104), this.anInt4963)) {
                class146_Sub4.aFloatArray4955 = this.aFloatArray4955;
                class146_Sub4.aFloatArray4914 = this.aFloatArray4914;
            }
            else {
                if (class146_Sub3.aFloatArray4955 != null && ~class146_Sub3.aFloatArray4955.length <= ~this.anInt4948) {
                    class146_Sub4.aFloatArray4914 = class146_Sub3.aFloatArray4914;
                    class146_Sub4.aFloatArray4955 = class146_Sub3.aFloatArray4955;
                }
                else {
                    final int anInt4945 = this.anInt4936;
                    final float[] array9 = new float[anInt4945];
                    class146_Sub3.aFloatArray4914 = array9;
                    class146_Sub4.aFloatArray4914 = array9;
                    final float[] array10 = new float[anInt4945];
                    class146_Sub3.aFloatArray4955 = array10;
                    class146_Sub4.aFloatArray4955 = array10;
                }
                for (int k = 0; k < this.anInt4936; ++k) {
                    class146_Sub4.aFloatArray4955[k] = this.aFloatArray4955[k];
                    class146_Sub4.aFloatArray4914[k] = this.aFloatArray4914[k];
                }
            }
            if (r_Sub2.method1655(anInt4942, (byte)(-128), this.anInt4963)) {
                class146_Sub4.aClass189_4930 = class146_Sub3.aClass189_4930;
                class146_Sub4.aClass189_4930.anInterface2_Impl1_1456 = this.aClass189_4930.anInterface2_Impl1_1456;
                class146_Sub4.aClass189_4930.aBoolean1459 = true;
                class146_Sub4.aClass189_4930.aBoolean1458 = this.aClass189_4930.aBoolean1458;
            }
            else if (!Class5.method176(24578, anInt4942, this.anInt4963)) {
                class146_Sub4.aClass189_4930 = null;
            }
            else {
                class146_Sub4.aClass189_4930 = this.aClass189_4930;
            }
            if (!Class98_Sub10_Sub9.method1033(anInt4942, this.anInt4963, 16)) {
                class146_Sub4.aShortArray4961 = this.aShortArray4961;
                class146_Sub4.aShortArray4947 = this.aShortArray4947;
                class146_Sub4.aShortArray4915 = this.aShortArray4915;
            }
            else {
                if (class146_Sub3.aShortArray4947 == null || ~class146_Sub3.aShortArray4947.length > ~this.anInt4948) {
                    final int anInt4946 = this.anInt4948;
                    final short[] array11 = new short[anInt4946];
                    class146_Sub3.aShortArray4947 = array11;
                    class146_Sub4.aShortArray4947 = array11;
                    final short[] array12 = new short[anInt4946];
                    class146_Sub3.aShortArray4915 = array12;
                    class146_Sub4.aShortArray4915 = array12;
                    final short[] array13 = new short[anInt4946];
                    class146_Sub3.aShortArray4961 = array13;
                    class146_Sub4.aShortArray4961 = array13;
                }
                else {
                    class146_Sub4.aShortArray4961 = class146_Sub3.aShortArray4961;
                    class146_Sub4.aShortArray4915 = class146_Sub3.aShortArray4915;
                    class146_Sub4.aShortArray4947 = class146_Sub3.aShortArray4947;
                }
                for (int n5 = 0; ~n5 > ~this.anInt4948; ++n5) {
                    class146_Sub4.aShortArray4947[n5] = this.aShortArray4947[n5];
                    class146_Sub4.aShortArray4915[n5] = this.aShortArray4915[n5];
                    class146_Sub4.aShortArray4961[n5] = this.aShortArray4961[n5];
                }
            }
            if (!Class276.method3286((byte)22, anInt4942, this.anInt4963)) {
                if (Class140.method2287(anInt4942, this.anInt4963, 2048)) {
                    class146_Sub4.aClass18_4931 = this.aClass18_4931;
                }
                else {
                    class146_Sub4.aClass18_4931 = null;
                }
            }
            else {
                class146_Sub4.aClass18_4931 = class146_Sub3.aClass18_4931;
                class146_Sub4.aClass18_4931.aBoolean209 = true;
                class146_Sub4.aClass18_4931.anInterface2_Impl2_211 = this.aClass18_4931.anInterface2_Impl2_211;
                class146_Sub4.aClass18_4931.aBoolean207 = this.aClass18_4931.aBoolean207;
            }
            if (Class206.method2725(32768, anInt4942, this.anInt4963)) {
                if (class146_Sub3.aShortArray4954 == null || this.anInt4948 > class146_Sub3.aShortArray4954.length) {
                    final short[] array14 = new short[this.anInt4948];
                    class146_Sub3.aShortArray4954 = array14;
                    class146_Sub4.aShortArray4954 = array14;
                }
                else {
                    class146_Sub4.aShortArray4954 = class146_Sub3.aShortArray4954;
                }
                for (int l = 0; l < this.anInt4948; ++l) {
                    class146_Sub4.aShortArray4954[l] = this.aShortArray4954[l];
                }
            }
            else {
                class146_Sub4.aShortArray4954 = this.aShortArray4954;
            }
            if (n > -81) {
                this.ma();
            }
            if (!Class234.method2887(this.anInt4963, (byte)46, anInt4942)) {
                class146_Sub4.aClass181Array4913 = this.aClass181Array4913;
            }
            else if (class146_Sub3.aClass181Array4913 != null && ~class146_Sub3.aClass181Array4913.length <= ~this.anInt4911) {
                class146_Sub4.aClass181Array4913 = class146_Sub3.aClass181Array4913;
                for (int n6 = 0; ~this.anInt4911 < ~n6; ++n6) {
                    class146_Sub4.aClass181Array4913[n6].method2609(this.aClass181Array4913[n6], (byte)(-100));
                }
            }
            else {
                final Class181[] array15 = new Class181[this.anInt4911];
                class146_Sub3.aClass181Array4913 = array15;
                class146_Sub4.aClass181Array4913 = array15;
                for (int n7 = 0; this.anInt4911 > n7; ++n7) {
                    class146_Sub4.aClass181Array4913[n7] = this.aClass181Array4913[n7].method2611(-1);
                }
            }
            class146_Sub4.anIntArray4932 = this.anIntArray4932;
            class146_Sub4.anIntArrayArray4925 = this.anIntArrayArray4925;
            class146_Sub4.anIntArray4918 = this.anIntArray4918;
            class146_Sub4.aShortArray4904 = this.aShortArray4904;
            class146_Sub4.aShortArray4917 = this.aShortArray4917;
            class146_Sub4.aShortArray4901 = this.aShortArray4901;
            if (!this.aBoolean4922) {
                class146_Sub4.aBoolean4922 = false;
            }
            else {
                class146_Sub4.aBoolean4922 = true;
                class146_Sub4.anInt4924 = this.anInt4924;
                class146_Sub4.anInt4939 = this.anInt4939;
                class146_Sub4.anInt4950 = this.anInt4950;
                class146_Sub4.anInt4937 = this.anInt4937;
                class146_Sub4.anInt4956 = this.anInt4956;
                class146_Sub4.anInt4910 = this.anInt4910;
                class146_Sub4.anInt4921 = this.anInt4921;
                class146_Sub4.anInt4943 = this.anInt4943;
            }
            class146_Sub4.aShortArray4944 = this.aShortArray4944;
            class146_Sub4.anIntArrayArray4903 = this.anIntArrayArray4903;
            class146_Sub4.aClass87Array4951 = this.aClass87Array4951;
            class146_Sub4.aClass35Array4919 = this.aClass35Array4919;
            class146_Sub4.aClass281Array4941 = this.aClass281Array4941;
            class146_Sub4.anIntArray4945 = this.anIntArray4945;
            class146_Sub4.anIntArrayArray4934 = this.anIntArrayArray4934;
            class146_Sub4.anIntArray4907 = this.anIntArray4907;
            class146_Sub4.aShortArray4960 = this.aShortArray4960;
            return class146_Sub4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.N(" + ((class146_Sub3 != null) ? "{...}" : "null") + ',' + anInt4942 + ',' + b + ',' + b2 + ',' + n + ',' + ((class146_Sub4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void ia(final short n, final short n2) {
        try {
            for (int n3 = 0; this.anInt4948 > n3; ++n3) {
                if (~this.aShortArray4946[n3] == ~n) {
                    this.aShortArray4946[n3] = n2;
                }
            }
            if (this.aClass281Array4941 != null) {
                for (int n4 = 0; ~n4 > ~this.anInt4911; ++n4) {
                    final Class281 class281 = this.aClass281Array4941[n4];
                    final Class181 class282 = this.aClass181Array4913[n4];
                    class282.anInt1428 = ((class282.anInt1428 & 0xFF000000) | (Class208.anIntArray1579[0xFFFF & this.aShortArray4946[class281.anInt2121]] & 0xFFFFFF));
                }
            }
            this.method2392(76);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.ia(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final r ba(final r r) {
        try {
            if (this.anInt4936 == 0) {
                return null;
            }
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            int n;
            int n2;
            if (this.aHa_Sub3_4899.anInt4564 > 0) {
                n = this.anInt4924 + -(this.aHa_Sub3_4899.anInt4564 * this.anInt4950 >> -1885074200) >> this.aHa_Sub3_4899.anInt4573;
                n2 = -(this.anInt4956 * this.aHa_Sub3_4899.anInt4564 >> -1369730328) + this.anInt4910 >> this.aHa_Sub3_4899.anInt4573;
            }
            else {
                n = -(this.aHa_Sub3_4899.anInt4564 * this.anInt4956 >> -1397541176) + this.anInt4924 >> this.aHa_Sub3_4899.anInt4573;
                n2 = -(this.anInt4950 * this.aHa_Sub3_4899.anInt4564 >> -854634200) + this.anInt4910 >> this.aHa_Sub3_4899.anInt4573;
            }
            int n3;
            int n4;
            if (~this.aHa_Sub3_4899.anInt4552 >= -1) {
                n3 = this.anInt4921 + -(this.aHa_Sub3_4899.anInt4552 * this.anInt4956 >> 1942279688) >> this.aHa_Sub3_4899.anInt4573;
                n4 = this.anInt4937 - (this.aHa_Sub3_4899.anInt4552 * this.anInt4950 >> -1505360600) >> this.aHa_Sub3_4899.anInt4573;
            }
            else {
                n3 = this.anInt4921 - (this.anInt4950 * this.aHa_Sub3_4899.anInt4552 >> 470725096) >> this.aHa_Sub3_4899.anInt4573;
                n4 = -(this.aHa_Sub3_4899.anInt4552 * this.anInt4956 >> -959976984) + this.anInt4937 >> this.aHa_Sub3_4899.anInt4573;
            }
            final int n5 = n2 - (n - 1);
            final int n6 = n4 - n3 + 1;
            final r_Sub2 r_Sub2 = (r_Sub2)r;
            r_Sub2 r_Sub3;
            if (r_Sub2 == null || !r_Sub2.method1652(n5, n6, 22657)) {
                r_Sub3 = new r_Sub2(this.aHa_Sub3_4899, n5, n6);
            }
            else {
                r_Sub3 = r_Sub2;
                r_Sub3.method1654(106);
            }
            r_Sub3.method1651(n4, n3, 0, n, n2);
            this.method2397(8192, r_Sub3);
            return r_Sub3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.ba(" + ((r != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int ma() {
        try {
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            return this.anInt4943;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.ma()");
        }
    }
    
    @Override
    final void v() {
        try {
            for (int n = 0; this.anInt4912 > n; ++n) {
                this.anIntArray4935[n] = -this.anIntArray4935[n];
            }
            for (int i = 0; i < this.anInt4936; ++i) {
                this.aShortArray4962[i] = (short)(-this.aShortArray4962[i]);
            }
            for (int n2 = 0; ~this.anInt4948 < ~n2; ++n2) {
                final short n3 = this.aShortArray4947[n2];
                this.aShortArray4947[n2] = this.aShortArray4961[n2];
                this.aShortArray4961[n2] = n3;
            }
            this.method2410(-83);
            this.method2404((byte)(-64));
            this.method2406(-14204);
            this.aBoolean4922 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.v()");
        }
    }
    
    @Override
    final Class87[] method2320() {
        try {
            return this.aClass87Array4951;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.IB()");
        }
    }
    
    @Override
    final int EA() {
        try {
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            return this.anInt4950;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.EA()");
        }
    }
    
    @Override
    final void s(final int anInt4942) {
        try {
            if (this.aClass189_4933 != null) {
                this.aClass189_4933.aBoolean1459 = Class238.method2919(-104, anInt4942, this.anInt4963);
            }
            if (this.aClass189_4930 != null) {
                this.aClass189_4930.aBoolean1459 = r_Sub2.method1655(anInt4942, (byte)(-128), this.anInt4963);
            }
            if (this.aClass189_4920 != null) {
                this.aClass189_4920.aBoolean1459 = Class98_Sub27.method1292(this.anInt4963, (byte)118, anInt4942);
            }
            if (this.aClass189_4927 != null) {
                this.aClass189_4927.aBoolean1459 = Class21_Sub3.method276(anInt4942, 15123, this.anInt4963);
            }
            this.anInt4942 = anInt4942;
            this.aBoolean4908 = true;
            if (this.aClass168_4923 != null && ~(this.anInt4942 & 0x10000) == -1) {
                this.aShortArray4962 = this.aClass168_4923.aShortArray1292;
                this.aShortArray4953 = this.aClass168_4923.aShortArray1288;
                this.aShortArray4957 = this.aClass168_4923.aShortArray1291;
                this.aByteArray4928 = this.aClass168_4923.aByteArray1289;
                this.aClass168_4923 = null;
            }
            this.method2396(8);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.s(" + anInt4942 + ')');
        }
    }
    
    private final void method2396(final int n) {
        try {
            if (this.aBoolean4908) {
                this.aBoolean4908 = false;
                if (this.aClass87Array4951 == null && this.aClass35Array4919 == null && this.aClass281Array4941 == null) {
                    if (this.anIntArray4906 != null && !Class93_Sub1.method905(this.anInt4942, -87, this.anInt4963)) {
                        if (this.aClass189_4933 == null || this.aClass189_4933.method2640(13156520)) {
                            if (!this.aBoolean4922) {
                                this.method2401(n - 21073);
                            }
                            this.anIntArray4906 = null;
                        }
                        else {
                            this.aBoolean4908 = true;
                        }
                    }
                    if (this.anIntArray4959 != null && !Class298.method3504(this.anInt4963, this.anInt4942, 28)) {
                        if (this.aClass189_4933 == null || this.aClass189_4933.method2640(13156520)) {
                            if (!this.aBoolean4922) {
                                this.method2401(-21065);
                            }
                            this.anIntArray4959 = null;
                        }
                        else {
                            this.aBoolean4908 = true;
                        }
                    }
                    if (this.anIntArray4935 != null && !Class64_Sub25.method656(this.anInt4963, (byte)(-123), this.anInt4942)) {
                        if (this.aClass189_4933 != null && !this.aClass189_4933.method2640(13156520)) {
                            this.aBoolean4908 = true;
                        }
                        else {
                            if (!this.aBoolean4922) {
                                this.method2401(-21065);
                            }
                            this.anIntArray4935 = null;
                        }
                    }
                }
                if (this.aShortArray4960 != null && this.anIntArray4906 == null && this.anIntArray4959 == null && this.anIntArray4935 == null) {
                    this.anIntArray4932 = null;
                    this.aShortArray4960 = null;
                }
                if (n != 8) {
                    this.fa();
                }
                Label_0400: {
                    if (this.aByteArray4928 != null && !Class133.method2239(this.anInt4963, this.anInt4942, (byte)100)) {
                        Label_0380: {
                            if ((0x37 & this.anInt4963) != 0x0) {
                                if (this.aClass189_4927 == null) {
                                    break Label_0380;
                                }
                                if (this.aClass189_4927.method2640(n ^ 0xC8C0A0)) {
                                    break Label_0380;
                                }
                            }
                            else if (this.aClass189_4920 == null || this.aClass189_4920.method2640(13156520)) {
                                break Label_0380;
                            }
                            this.aBoolean4908 = true;
                            break Label_0400;
                        }
                        this.aByteArray4928 = null;
                        final short[] aShortArray4953 = null;
                        this.aShortArray4962 = aShortArray4953;
                        this.aShortArray4957 = aShortArray4953;
                        this.aShortArray4953 = aShortArray4953;
                    }
                }
                if (this.aShortArray4946 != null && !Class13.method224(8, this.anInt4942, this.anInt4963)) {
                    if (this.aClass189_4920 == null || this.aClass189_4920.method2640(13156520)) {
                        this.aShortArray4946 = null;
                    }
                    else {
                        this.aBoolean4908 = true;
                    }
                }
                if (this.aByteArray4958 != null && !Class241.method2931(this.anInt4963, 262144, this.anInt4942)) {
                    if (this.aClass189_4920 == null || this.aClass189_4920.method2640(13156520)) {
                        this.aByteArray4958 = null;
                    }
                    else {
                        this.aBoolean4908 = true;
                    }
                }
                if (this.aFloatArray4955 != null && !Class15.method228(this.anInt4963, 1, this.anInt4942)) {
                    if (this.aClass189_4930 == null || this.aClass189_4930.method2640(13156520)) {
                        final float[] array = null;
                        this.aFloatArray4914 = array;
                        this.aFloatArray4955 = array;
                    }
                    else {
                        this.aBoolean4908 = true;
                    }
                }
                if (this.aShortArray4954 != null && !Class99.method1686(this.anInt4942, this.anInt4963, false)) {
                    if (this.aClass189_4920 != null && !this.aClass189_4920.method2640(13156520)) {
                        this.aBoolean4908 = true;
                    }
                    else {
                        this.aShortArray4954 = null;
                    }
                }
                if (this.aShortArray4947 != null && !Class98_Sub20.method1172((byte)1, this.anInt4942, this.anInt4963)) {
                    if ((this.aClass18_4931 == null || this.aClass18_4931.method250((byte)(-57))) && (this.aClass189_4920 == null || this.aClass189_4920.method2640(13156520))) {
                        final short[] aShortArray4954 = null;
                        this.aShortArray4961 = aShortArray4954;
                        this.aShortArray4915 = aShortArray4954;
                        this.aShortArray4947 = aShortArray4954;
                    }
                    else {
                        this.aBoolean4908 = true;
                    }
                }
                if (this.aShortArray4901 != null) {
                    if (this.aClass189_4933 != null && !this.aClass189_4933.method2640(13156520)) {
                        this.aBoolean4908 = true;
                    }
                    else {
                        this.aShortArray4901 = null;
                    }
                }
                if (this.aShortArray4904 != null) {
                    if (this.aClass189_4920 == null || this.aClass189_4920.method2640(13156520)) {
                        this.aShortArray4904 = null;
                    }
                    else {
                        this.aBoolean4908 = true;
                    }
                }
                if (this.anIntArrayArray4903 != null && !Class128.method2223(this.anInt4963, (byte)(-67), this.anInt4942)) {
                    this.aShortArray4917 = null;
                    this.anIntArrayArray4903 = null;
                }
                if (this.anIntArrayArray4925 != null && !Class98_Sub10_Sub29.method1092(this.anInt4942, this.anInt4963, (byte)(-114))) {
                    this.aShortArray4944 = null;
                    this.anIntArrayArray4925 = null;
                }
                if (this.anIntArrayArray4934 != null && !Class2.method169(false, this.anInt4942, this.anInt4963)) {
                    this.anIntArrayArray4934 = null;
                }
                if (this.anIntArray4907 != null && ~(0x800 & this.anInt4942) == -1 && (0x40000 & this.anInt4942) == 0x0) {
                    this.anIntArray4918 = null;
                    this.anIntArray4945 = null;
                    this.anIntArray4907 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.W(" + n + ')');
        }
    }
    
    private final void method2397(final int n, final r_Sub2 r_Sub2) {
        try {
            if (~this.anInt4936 < ~RuntimeException_Sub1.anIntArray3200.length) {
                Class57.anIntArray458 = new int[this.anInt4936];
                RuntimeException_Sub1.anIntArray3200 = new int[this.anInt4936];
            }
            if (n != 8192) {
                this.aShortArray4901 = null;
            }
            for (int n2 = 0; ~n2 > ~this.anInt4912; ++n2) {
                final int n3 = (-(this.anIntArray4959[n2] * this.aHa_Sub3_4899.anInt4564 >> 1103211464) + this.anIntArray4906[n2] >> this.aHa_Sub3_4899.anInt4573) + -r_Sub2.anInt6329;
                final int n4 = -r_Sub2.anInt6327 + (this.anIntArray4935[n2] - (this.anIntArray4959[n2] * this.aHa_Sub3_4899.anInt4552 >> 1203490792) >> this.aHa_Sub3_4899.anInt4573);
                final int n5 = this.anIntArray4932[n2];
                for (int n6 = this.anIntArray4932[1 + n2], n7 = n5; ~n7 > ~n6; ++n7) {
                    final short n8 = (short)(-1 + this.aShortArray4960[n7]);
                    if (~n8 == 0x0) {
                        break;
                    }
                    RuntimeException_Sub1.anIntArray3200[n8] = n3;
                    Class57.anIntArray458[n8] = n4;
                }
            }
            for (int n9 = 0; ~n9 > ~this.anInt4909; ++n9) {
                if (this.aByteArray4958 == null || this.aByteArray4958[n9] <= 128) {
                    final short n10 = this.aShortArray4947[n9];
                    final short n11 = this.aShortArray4915[n9];
                    final short n12 = this.aShortArray4961[n9];
                    final int n13 = RuntimeException_Sub1.anIntArray3200[n10];
                    final int n14 = RuntimeException_Sub1.anIntArray3200[n11];
                    final int n15 = RuntimeException_Sub1.anIntArray3200[n12];
                    final int n16 = Class57.anIntArray458[n10];
                    final int n17 = Class57.anIntArray458[n11];
                    final int n18 = Class57.anIntArray458[n12];
                    if (-((-n16 + n17) * (n15 + -n14)) + (n17 - n18) * (n13 - n14) > 0) {
                        r_Sub2.method1653(n16, n13, n14, n15, n18, (byte)(-69), n17);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.CA(" + n + ',' + ((r_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final boolean method2398(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            if (n < n4 && n < n6 && ~n > ~n8) {
                return false;
            }
            if (n5 < 20) {
                this.method2392(86);
            }
            return (n <= n4 || ~n6 <= ~n || n8 >= n) && (n7 <= n3 || n3 >= n9 || ~n2 >= ~n3) && (n7 >= n3 || n9 >= n3 || n3 <= n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.GA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    @Override
    final int RA() {
        try {
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            return this.anInt4910;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.RA()");
        }
    }
    
    @Override
    final void method2332(final Class146 class146, final int n, final int n2, final int n3, final boolean b) {
        try {
            final Class146_Sub3 class146_Sub3 = (Class146_Sub3)class146;
            if (this.anInt4948 != 0 && ~class146_Sub3.anInt4948 != -1) {
                final int i = class146_Sub3.anInt4912;
                final int[] anIntArray4906 = class146_Sub3.anIntArray4906;
                final int[] anIntArray4907 = class146_Sub3.anIntArray4959;
                final int[] anIntArray4908 = class146_Sub3.anIntArray4935;
                final short[] aShortArray4953 = class146_Sub3.aShortArray4953;
                final short[] aShortArray4954 = class146_Sub3.aShortArray4957;
                final short[] aShortArray4955 = class146_Sub3.aShortArray4962;
                final byte[] aByteArray4928 = class146_Sub3.aByteArray4928;
                short[] aShortArray4956;
                short[] aShortArray4957;
                byte[] aByteArray4929;
                short[] aShortArray4958;
                if (this.aClass168_4923 != null) {
                    aShortArray4956 = this.aClass168_4923.aShortArray1292;
                    aShortArray4957 = this.aClass168_4923.aShortArray1288;
                    aByteArray4929 = this.aClass168_4923.aByteArray1289;
                    aShortArray4958 = this.aClass168_4923.aShortArray1291;
                }
                else {
                    aShortArray4958 = null;
                    aByteArray4929 = null;
                    aShortArray4956 = null;
                    aShortArray4957 = null;
                }
                short[] aShortArray4959;
                byte[] aByteArray4930;
                short[] aShortArray4960;
                short[] aShortArray4961;
                if (class146_Sub3.aClass168_4923 != null) {
                    aShortArray4959 = class146_Sub3.aClass168_4923.aShortArray1288;
                    aByteArray4930 = class146_Sub3.aClass168_4923.aByteArray1289;
                    aShortArray4960 = class146_Sub3.aClass168_4923.aShortArray1291;
                    aShortArray4961 = class146_Sub3.aClass168_4923.aShortArray1292;
                }
                else {
                    aByteArray4930 = null;
                    aShortArray4960 = null;
                    aShortArray4959 = null;
                    aShortArray4961 = null;
                }
                final int[] anIntArray4909 = class146_Sub3.anIntArray4932;
                final short[] aShortArray4962 = class146_Sub3.aShortArray4960;
                if (!class146_Sub3.aBoolean4922) {
                    class146_Sub3.method2401(-21065);
                }
                final int anInt4956 = class146_Sub3.anInt4956;
                final int anInt4957 = class146_Sub3.anInt4950;
                final int anInt4958 = class146_Sub3.anInt4924;
                final int anInt4959 = class146_Sub3.anInt4910;
                final int anInt4960 = class146_Sub3.anInt4921;
                final int anInt4961 = class146_Sub3.anInt4937;
                for (int n4 = 0; this.anInt4912 > n4; ++n4) {
                    final int n5 = this.anIntArray4959[n4] + -n2;
                    if (anInt4956 <= n5 && n5 <= anInt4957) {
                        final int n6 = this.anIntArray4906[n4] + -n;
                        if (anInt4958 <= n6 && ~n6 >= ~anInt4959) {
                            final int n7 = this.anIntArray4935[n4] - n3;
                            if (~anInt4960 >= ~n7 && n7 <= anInt4961) {
                                int n8 = -1;
                                final int n9 = this.anIntArray4932[n4];
                                for (int n10 = this.anIntArray4932[n4 + 1], n11 = n9; ~n11 > ~n10; ++n11) {
                                    n8 = this.aShortArray4960[n11] - 1;
                                    if (n8 == -1) {
                                        break;
                                    }
                                    if (~this.aByteArray4928[n8] != -1) {
                                        break;
                                    }
                                }
                                if (~n8 != 0x0) {
                                    for (int n12 = 0; i > n12; ++n12) {
                                        if (~anIntArray4906[n12] == ~n6 && ~n7 == ~anIntArray4908[n12] && n5 == anIntArray4907[n12]) {
                                            final int n13 = anIntArray4909[n12 + 1];
                                            int n14 = -1;
                                            for (int j = anIntArray4909[n12]; j < n13; ++j) {
                                                n14 = aShortArray4962[j] - 1;
                                                if (~n14 == 0x0) {
                                                    break;
                                                }
                                                if (~aByteArray4928[n14] != -1) {
                                                    break;
                                                }
                                            }
                                            if (~n14 != 0x0) {
                                                if (aShortArray4957 == null) {
                                                    this.aClass168_4923 = new Class168();
                                                    final Class168 aClass168_4923 = this.aClass168_4923;
                                                    final short[] method2304 = Class141.method2304((byte)123, this.aShortArray4953);
                                                    aClass168_4923.aShortArray1288 = method2304;
                                                    aShortArray4957 = method2304;
                                                    final Class168 aClass168_4924 = this.aClass168_4923;
                                                    final short[] method2305 = Class141.method2304((byte)113, this.aShortArray4957);
                                                    aClass168_4924.aShortArray1291 = method2305;
                                                    aShortArray4958 = method2305;
                                                    final Class168 aClass168_4925 = this.aClass168_4923;
                                                    final short[] method2306 = Class141.method2304((byte)115, this.aShortArray4962);
                                                    aClass168_4925.aShortArray1292 = method2306;
                                                    aShortArray4956 = method2306;
                                                    final Class168 aClass168_4926 = this.aClass168_4923;
                                                    final byte[] method2307 = Class98_Sub10_Sub24.method1075(this.aByteArray4928, true);
                                                    aClass168_4926.aByteArray1289 = method2307;
                                                    aByteArray4929 = method2307;
                                                }
                                                if (aShortArray4959 == null) {
                                                    final Class146_Sub3 class146_Sub4 = class146_Sub3;
                                                    final Class168 aClass168_4927 = new Class168();
                                                    class146_Sub4.aClass168_4923 = aClass168_4927;
                                                    final Class168 class148;
                                                    final Class168 class147 = class148 = aClass168_4927;
                                                    final short[] method2308 = Class141.method2304((byte)114, aShortArray4953);
                                                    class148.aShortArray1288 = method2308;
                                                    aShortArray4959 = method2308;
                                                    final Class168 class149 = class147;
                                                    final short[] method2309 = Class141.method2304((byte)120, aShortArray4954);
                                                    class149.aShortArray1291 = method2309;
                                                    aShortArray4960 = method2309;
                                                    final Class168 class150 = class147;
                                                    final short[] method2310 = Class141.method2304((byte)114, aShortArray4955);
                                                    class150.aShortArray1292 = method2310;
                                                    aShortArray4961 = method2310;
                                                    final Class168 class151 = class147;
                                                    final byte[] method2311 = Class98_Sub10_Sub24.method1075(aByteArray4928, true);
                                                    class151.aByteArray1289 = method2311;
                                                    aByteArray4930 = method2311;
                                                }
                                                final short n15 = this.aShortArray4953[n8];
                                                final short n16 = this.aShortArray4957[n8];
                                                final short n17 = this.aShortArray4962[n8];
                                                final byte b2 = this.aByteArray4928[n8];
                                                final int n18 = anIntArray4909[n12];
                                                for (int k = anIntArray4909[1 + n12], n19 = n18; k > n19; ++n19) {
                                                    final short n20 = (short)(aShortArray4962[n19] - 1);
                                                    if (n20 == -1) {
                                                        break;
                                                    }
                                                    if (aByteArray4930[n20] != 0) {
                                                        final short[] array = aShortArray4959;
                                                        final short n21 = n20;
                                                        array[n21] += n15;
                                                        final short[] array2 = aShortArray4960;
                                                        final short n22 = n20;
                                                        array2[n22] += n16;
                                                        final short[] array3 = aShortArray4961;
                                                        final short n23 = n20;
                                                        array3[n23] += n17;
                                                        final byte[] array4 = aByteArray4930;
                                                        final short n24 = n20;
                                                        array4[n24] += b2;
                                                    }
                                                }
                                                final byte b3 = aByteArray4928[n14];
                                                final int n25 = this.anIntArray4932[n4];
                                                final short n26 = aShortArray4955[n14];
                                                final short n27 = aShortArray4953[n14];
                                                final int n28 = this.anIntArray4932[n4 + 1];
                                                final short n29 = aShortArray4954[n14];
                                                for (int n30 = n25; ~n28 < ~n30; ++n30) {
                                                    final short n31 = (short)(this.aShortArray4960[n30] - 1);
                                                    if (n31 == -1) {
                                                        break;
                                                    }
                                                    if (~aByteArray4929[n31] != -1) {
                                                        final short[] array5 = aShortArray4957;
                                                        final short n32 = n31;
                                                        array5[n32] += n27;
                                                        final short[] array6 = aShortArray4958;
                                                        final short n33 = n31;
                                                        array6[n33] += n29;
                                                        final short[] array7 = aShortArray4956;
                                                        final short n34 = n31;
                                                        array7[n34] += n26;
                                                        final byte[] array8 = aByteArray4929;
                                                        final short n35 = n31;
                                                        array8[n35] += b3;
                                                    }
                                                }
                                                class146_Sub3.method2404((byte)(-64));
                                                this.method2404((byte)(-64));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.AA(" + ((class146 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + b + ')');
        }
    }
    
    private final boolean method2399(final int n) {
        try {
            if (this.aClass18_4931.aBoolean207) {
                return true;
            }
            if (this.aClass18_4931.anInterface2_Impl2_208 == null) {
                this.aClass18_4931.anInterface2_Impl2_208 = this.aHa_Sub3_4899.method1990((byte)84, this.aBoolean4916);
            }
            final Interface2_Impl2 anInterface2_Impl2_208 = this.aClass18_4931.anInterface2_Impl2_208;
            anInterface2_Impl2_208.method76(this.anInt4909 * 6, 20779);
            final Buffer method78 = anInterface2_Impl2_208.method78(true, -89);
            if (method78 != null) {
                final Stream method79 = this.aHa_Sub3_4899.method2043(24022, method78);
                if (Stream.a()) {
                    for (int n2 = 0; ~n2 > ~this.anInt4909; ++n2) {
                        method79.c(this.aShortArray4947[n2]);
                        method79.c(this.aShortArray4915[n2]);
                        method79.c(this.aShortArray4961[n2]);
                    }
                }
                else {
                    for (int n3 = 0; ~this.anInt4909 < ~n3; ++n3) {
                        method79.d(this.aShortArray4947[n3]);
                        method79.d(this.aShortArray4915[n3]);
                        method79.d(this.aShortArray4961[n3]);
                    }
                }
                method79.c();
                if (anInterface2_Impl2_208.method79((byte)(-87))) {
                    this.aBoolean4908 = true;
                    this.aClass18_4931.anInterface2_Impl2_211 = anInterface2_Impl2_208;
                    return this.aClass18_4931.aBoolean207 = true;
                }
            }
            return n <= 80 && false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.IA(" + n + ')');
        }
    }
    
    @Override
    final void method2331(final Class111 class111, final int n, final boolean b) {
        try {
            if (this.aShortArray4944 != null) {
                final int[] array = new int[3];
                for (int n2 = 0; this.anInt4912 > n2; ++n2) {
                    if (~(this.aShortArray4944[n2] & n) != -1) {
                        if (!b) {
                            class111.method2103(this.anIntArray4906[n2], this.anIntArray4959[n2], this.anIntArray4935[n2], array);
                        }
                        else {
                            class111.method2096(this.anIntArray4906[n2], this.anIntArray4959[n2], this.anIntArray4935[n2], array);
                        }
                        this.anIntArray4906[n2] = array[0];
                        this.anIntArray4959[n2] = array[1];
                        this.anIntArray4935[n2] = array[2];
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.za(" + ((class111 != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    public static void method2400(final int n) {
        try {
            if (n == -1) {
                Class146_Sub3.aClass213_4949 = null;
                Class146_Sub3.aClass305_Sub1_4952 = null;
                Class146_Sub3.aClass171_4900 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.L(" + n + ')');
        }
    }
    
    @Override
    final void method2337(final int n, final int n2, final int n3, final int n4) {
        try {
            for (int n5 = 0; this.anInt4948 > n5; ++n5) {
                final int n6 = this.aShortArray4946[n5] & 0xFFFF;
                int n7 = 0x3F & n6 >> -1348210774;
                int n8 = (n6 & 0x3C0) >> 999663239;
                int n9 = 0x7F & n6;
                if (~n != 0x0) {
                    n7 -= -(n4 * (-n7 + n) >> -1413349625);
                }
                if (~n2 != 0x0) {
                    n8 -= -((n2 + -n8) * n4 >> -289480537);
                }
                if (n3 != -1) {
                    n9 += (n3 - n9) * n4 >> 428093351;
                }
                this.aShortArray4946[n5] = (short)Class41.method366(Class41.method366(n8 << 1530379783, n7 << -1910424694), n9);
            }
            if (this.aClass281Array4941 != null) {
                for (int n10 = 0; ~this.anInt4911 < ~n10; ++n10) {
                    final Class281 class281 = this.aClass281Array4941[n10];
                    final Class181 class282 = this.aClass181Array4913[n10];
                    class282.anInt1428 = ((0xFF000000 & class282.anInt1428) | (Class208.anIntArray1579[this.aShortArray4946[class281.anInt2121] & 0xFFFF] & 0xFFFFFF));
                }
            }
            this.method2392(108);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.HB(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void aa(final short n, final short n2) {
        try {
            final d ad938 = this.aHa_Sub3_4899.aD938;
            for (int n3 = 0; ~n3 > ~this.anInt4948; ++n3) {
                if (~n == ~this.aShortArray4954[n3]) {
                    this.aShortArray4954[n3] = n2;
                }
            }
            byte aByte1830 = 0;
            byte aByte1831 = 0;
            if (~n != 0x0) {
                final Class238 method11 = ad938.method11(n & 0xFFFF, -28755);
                aByte1831 = method11.aByte1829;
                aByte1830 = method11.aByte1830;
            }
            byte aByte1832 = 0;
            byte aByte1833 = 0;
            if (~n2 != 0x0) {
                final Class238 method12 = ad938.method11(n2 & 0xFFFF, -28755);
                aByte1833 = method12.aByte1829;
                aByte1832 = method12.aByte1830;
                if (method12.aByte1823 != 0 || method12.aByte1837 != 0) {
                    this.aBoolean4940 = true;
                }
            }
            if (~aByte1831 != ~aByte1833 | ~aByte1832 != ~aByte1830) {
                if (this.aClass281Array4941 != null) {
                    for (int i = 0; i < this.anInt4911; ++i) {
                        final Class281 class281 = this.aClass281Array4941[i];
                        final Class181 class282 = this.aClass181Array4913[i];
                        class282.anInt1428 = ((0xFF000000 & class282.anInt1428) | (0xFFFFFF & Class208.anIntArray1579[this.aShortArray4946[class281.anInt2121] & 0xFFFF]));
                    }
                }
                this.method2392(73);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.aa(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method2344(final int n, final int[] array, int anInt1256, int anInt1257, int anInt1258, final int n2, final boolean b) {
        try {
            final int i = array.length;
            if (n == 0) {
                anInt1257 <<= 4;
                anInt1256 <<= 4;
                anInt1258 <<= 4;
                Class271.anInt2035 = 0;
                int n3 = 0;
                Class159.anInt1256 = 0;
                Class48_Sub1.anInt3628 = 0;
                for (int n4 = 0; ~n4 > ~i; ++n4) {
                    final int n5 = array[n4];
                    if (this.anIntArrayArray4925.length > n5) {
                        final int[] array2 = this.anIntArrayArray4925[n5];
                        for (int n6 = 0; ~array2.length < ~n6; ++n6) {
                            final int n7 = array2[n6];
                            Class159.anInt1256 += this.anIntArray4906[n7];
                            Class48_Sub1.anInt3628 += this.anIntArray4959[n7];
                            ++n3;
                            Class271.anInt2035 += this.anIntArray4935[n7];
                        }
                    }
                }
                if (~n3 < -1) {
                    Class48_Sub1.anInt3628 = Class48_Sub1.anInt3628 / n3 + anInt1257;
                    Class271.anInt2035 = Class271.anInt2035 / n3 + anInt1258;
                    Class159.anInt1256 = Class159.anInt1256 / n3 - -anInt1256;
                }
                else {
                    Class271.anInt2035 = anInt1258;
                    Class159.anInt1256 = anInt1256;
                    Class48_Sub1.anInt3628 = anInt1257;
                }
            }
            else if (n == 1) {
                anInt1258 <<= 4;
                anInt1257 <<= 4;
                anInt1256 <<= 4;
                for (int n8 = 0; ~n8 > ~i; ++n8) {
                    final int n9 = array[n8];
                    if (~n9 > ~this.anIntArrayArray4925.length) {
                        final int[] array3 = this.anIntArrayArray4925[n9];
                        for (int n10 = 0; array3.length > n10; ++n10) {
                            final int n11 = array3[n10];
                            final int[] anIntArray4906 = this.anIntArray4906;
                            final int n12 = n11;
                            anIntArray4906[n12] += anInt1256;
                            final int[] anIntArray4907 = this.anIntArray4959;
                            final int n13 = n11;
                            anIntArray4907[n13] += anInt1257;
                            final int[] anIntArray4908 = this.anIntArray4935;
                            final int n14 = n11;
                            anIntArray4908[n14] += anInt1258;
                        }
                    }
                }
            }
            else if (~n == 0xFFFFFFFD) {
                for (final int n15 : array) {
                    if (~this.anIntArrayArray4925.length < ~n15) {
                        final int[] array4 = this.anIntArrayArray4925[n15];
                        if (~(0x1 & n2) != -1) {
                            for (int k = 0; k < array4.length; ++k) {
                                final int n16 = array4[k];
                                final int[] anIntArray4909 = this.anIntArray4906;
                                final int n17 = n16;
                                anIntArray4909[n17] -= Class159.anInt1256;
                                final int[] anIntArray4910 = this.anIntArray4959;
                                final int n18 = n16;
                                anIntArray4910[n18] -= Class48_Sub1.anInt3628;
                                final int[] anIntArray4911 = this.anIntArray4935;
                                final int n19 = n16;
                                anIntArray4911[n19] -= Class271.anInt2035;
                                if (~anInt1256 != -1) {
                                    final int n20 = Class284_Sub2_Sub2.anIntArray6200[anInt1256];
                                    final int n21 = Class284_Sub2_Sub2.anIntArray6202[anInt1256];
                                    final int n22 = -(n20 * this.anIntArray4935[n16]) + (n21 * this.anIntArray4959[n16] + 16383) >> 445579438;
                                    this.anIntArray4935[n16] = 16383 + this.anIntArray4959[n16] * n20 - -(n21 * this.anIntArray4935[n16]) >> -461350130;
                                    this.anIntArray4959[n16] = n22;
                                }
                                if (~anInt1258 != -1) {
                                    final int n23 = Class284_Sub2_Sub2.anIntArray6200[anInt1258];
                                    final int n24 = Class284_Sub2_Sub2.anIntArray6202[anInt1258];
                                    final int n25 = 16383 + (this.anIntArray4959[n16] * n23 - -(this.anIntArray4906[n16] * n24)) >> -65447186;
                                    this.anIntArray4959[n16] = 16383 + (-(n23 * this.anIntArray4906[n16]) + n24 * this.anIntArray4959[n16]) >> 2069505006;
                                    this.anIntArray4906[n16] = n25;
                                }
                                if (~anInt1257 != -1) {
                                    final int n26 = Class284_Sub2_Sub2.anIntArray6200[anInt1257];
                                    final int n27 = Class284_Sub2_Sub2.anIntArray6202[anInt1257];
                                    final int n28 = this.anIntArray4906[n16] * n27 + n26 * this.anIntArray4935[n16] + 16383 >> -932225298;
                                    this.anIntArray4935[n16] = this.anIntArray4935[n16] * n27 - this.anIntArray4906[n16] * n26 + 16383 >> 525091886;
                                    this.anIntArray4906[n16] = n28;
                                }
                                final int[] anIntArray4912 = this.anIntArray4906;
                                final int n29 = n16;
                                anIntArray4912[n29] += Class159.anInt1256;
                                final int[] anIntArray4913 = this.anIntArray4959;
                                final int n30 = n16;
                                anIntArray4913[n30] += Class48_Sub1.anInt3628;
                                final int[] anIntArray4914 = this.anIntArray4935;
                                final int n31 = n16;
                                anIntArray4914[n31] += Class271.anInt2035;
                            }
                        }
                        else {
                            for (int n32 = 0; array4.length > n32; ++n32) {
                                final int n33 = array4[n32];
                                final int[] anIntArray4915 = this.anIntArray4906;
                                final int n34 = n33;
                                anIntArray4915[n34] -= Class159.anInt1256;
                                final int[] anIntArray4916 = this.anIntArray4959;
                                final int n35 = n33;
                                anIntArray4916[n35] -= Class48_Sub1.anInt3628;
                                final int[] anIntArray4917 = this.anIntArray4935;
                                final int n36 = n33;
                                anIntArray4917[n36] -= Class271.anInt2035;
                                if (anInt1258 != 0) {
                                    final int n37 = Class284_Sub2_Sub2.anIntArray6200[anInt1258];
                                    final int n38 = Class284_Sub2_Sub2.anIntArray6202[anInt1258];
                                    final int n39 = n37 * this.anIntArray4959[n33] - (-(n38 * this.anIntArray4906[n33]) - 16383) >> 879518286;
                                    this.anIntArray4959[n33] = n38 * this.anIntArray4959[n33] - this.anIntArray4906[n33] * n37 + 16383 >> -1200220562;
                                    this.anIntArray4906[n33] = n39;
                                }
                                if (~anInt1256 != -1) {
                                    final int n40 = Class284_Sub2_Sub2.anIntArray6200[anInt1256];
                                    final int n41 = Class284_Sub2_Sub2.anIntArray6202[anInt1256];
                                    final int n42 = 16383 + (this.anIntArray4959[n33] * n41 + -(n40 * this.anIntArray4935[n33])) >> -185481874;
                                    this.anIntArray4935[n33] = 16383 + (this.anIntArray4935[n33] * n41 + n40 * this.anIntArray4959[n33]) >> -752676850;
                                    this.anIntArray4959[n33] = n42;
                                }
                                if (~anInt1257 != -1) {
                                    final int n43 = Class284_Sub2_Sub2.anIntArray6200[anInt1257];
                                    final int n44 = Class284_Sub2_Sub2.anIntArray6202[anInt1257];
                                    final int n45 = 16383 + n43 * this.anIntArray4935[n33] + n44 * this.anIntArray4906[n33] >> 400648174;
                                    this.anIntArray4935[n33] = -(n43 * this.anIntArray4906[n33]) + this.anIntArray4935[n33] * n44 + 16383 >> -1184273010;
                                    this.anIntArray4906[n33] = n45;
                                }
                                final int[] anIntArray4918 = this.anIntArray4906;
                                final int n46 = n33;
                                anIntArray4918[n46] += Class159.anInt1256;
                                final int[] anIntArray4919 = this.anIntArray4959;
                                final int n47 = n33;
                                anIntArray4919[n47] += Class48_Sub1.anInt3628;
                                final int[] anIntArray4920 = this.anIntArray4935;
                                final int n48 = n33;
                                anIntArray4920[n48] += Class271.anInt2035;
                            }
                        }
                    }
                }
                if (b) {
                    for (int n49 = 0; ~n49 > ~i; ++n49) {
                        final int n50 = array[n49];
                        if (~n50 > ~this.anIntArrayArray4925.length) {
                            final int[] array5 = this.anIntArrayArray4925[n50];
                            for (int n51 = 0; array5.length > n51; ++n51) {
                                final int n52 = array5[n51];
                                final int n53 = this.anIntArray4932[n52];
                                for (int n54 = this.anIntArray4932[1 + n52], n55 = n53; ~n55 > ~n54; ++n55) {
                                    final short n56 = (short)(-1 + this.aShortArray4960[n55]);
                                    if (n56 == -1) {
                                        break;
                                    }
                                    if (~anInt1258 != -1) {
                                        final int n57 = Class284_Sub2_Sub2.anIntArray6200[anInt1258];
                                        final int n58 = Class284_Sub2_Sub2.anIntArray6202[anInt1258];
                                        final int n59 = this.aShortArray4953[n56] * n58 + (this.aShortArray4957[n56] * n57 + 16383) >> 1290072302;
                                        this.aShortArray4957[n56] = (short)(n58 * this.aShortArray4957[n56] + (-(n57 * this.aShortArray4953[n56]) + 16383) >> 2047821486);
                                        this.aShortArray4953[n56] = (short)n59;
                                    }
                                    if (anInt1256 != 0) {
                                        final int n60 = Class284_Sub2_Sub2.anIntArray6200[anInt1256];
                                        final int n61 = Class284_Sub2_Sub2.anIntArray6202[anInt1256];
                                        final int n62 = -(n60 * this.aShortArray4962[n56]) + this.aShortArray4957[n56] * n61 + 16383 >> 133029902;
                                        this.aShortArray4962[n56] = (short)(this.aShortArray4962[n56] * n61 + n60 * this.aShortArray4957[n56] + 16383 >> 1259097230);
                                        this.aShortArray4957[n56] = (short)n62;
                                    }
                                    if (~anInt1257 != -1) {
                                        final int n63 = Class284_Sub2_Sub2.anIntArray6200[anInt1257];
                                        final int n64 = Class284_Sub2_Sub2.anIntArray6202[anInt1257];
                                        final int n65 = 16383 + (n63 * this.aShortArray4962[n56] + n64 * this.aShortArray4953[n56]) >> -1016981842;
                                        this.aShortArray4962[n56] = (short)(n64 * this.aShortArray4962[n56] + -(this.aShortArray4953[n56] * n63) + 16383 >> -2113965746);
                                        this.aShortArray4953[n56] = (short)n65;
                                    }
                                }
                            }
                        }
                    }
                    this.method2404((byte)(-64));
                }
            }
            else if (n == 3) {
                for (int n66 = 0; i > n66; ++n66) {
                    final int n67 = array[n66];
                    if (~this.anIntArrayArray4925.length < ~n67) {
                        final int[] array6 = this.anIntArrayArray4925[n67];
                        for (int l = 0; l < array6.length; ++l) {
                            final int n68 = array6[l];
                            final int[] anIntArray4921 = this.anIntArray4906;
                            final int n69 = n68;
                            anIntArray4921[n69] -= Class159.anInt1256;
                            final int[] anIntArray4922 = this.anIntArray4959;
                            final int n70 = n68;
                            anIntArray4922[n70] -= Class48_Sub1.anInt3628;
                            final int[] anIntArray4923 = this.anIntArray4935;
                            final int n71 = n68;
                            anIntArray4923[n71] -= Class271.anInt2035;
                            this.anIntArray4906[n68] = this.anIntArray4906[n68] * anInt1256 >> 1618522887;
                            this.anIntArray4959[n68] = this.anIntArray4959[n68] * anInt1257 >> 375917447;
                            this.anIntArray4935[n68] = this.anIntArray4935[n68] * anInt1258 >> 81062407;
                            final int[] anIntArray4924 = this.anIntArray4906;
                            final int n72 = n68;
                            anIntArray4924[n72] += Class159.anInt1256;
                            final int[] anIntArray4925 = this.anIntArray4959;
                            final int n73 = n68;
                            anIntArray4925[n73] += Class48_Sub1.anInt3628;
                            final int[] anIntArray4926 = this.anIntArray4935;
                            final int n74 = n68;
                            anIntArray4926[n74] += Class271.anInt2035;
                        }
                    }
                }
            }
            else if (~n == 0xFFFFFFFA) {
                if (this.anIntArrayArray4903 != null) {
                    boolean b2 = false;
                    for (int n75 = 0; ~i < ~n75; ++n75) {
                        final int n76 = array[n75];
                        if (~n76 > ~this.anIntArrayArray4903.length) {
                            final int[] array7 = this.anIntArrayArray4903[n76];
                            for (int n77 = 0; ~n77 > ~array7.length; ++n77) {
                                final int n78 = array7[n77];
                                int n79 = (this.aByteArray4958[n78] & 0xFF) + anInt1256 * 8;
                                if (~n79 > -1) {
                                    n79 = 0;
                                }
                                else if (~n79 < -256) {
                                    n79 = 255;
                                }
                                this.aByteArray4958[n78] = (byte)n79;
                            }
                            b2 |= (~array7.length < -1);
                        }
                    }
                    if (b2) {
                        if (this.aClass281Array4941 != null) {
                            for (int n80 = 0; ~n80 > ~this.anInt4911; ++n80) {
                                final Class281 class281 = this.aClass281Array4941[n80];
                                final Class181 class282 = this.aClass181Array4913[n80];
                                class282.anInt1428 = (255 - (0xFF & this.aByteArray4958[class281.anInt2121]) << 11083608 | (0xFFFFFF & class282.anInt1428));
                            }
                        }
                        this.method2392(80);
                    }
                }
            }
            else if (~n == 0xFFFFFFF8) {
                if (this.anIntArrayArray4903 != null) {
                    boolean b3 = false;
                    for (int n81 = 0; ~i < ~n81; ++n81) {
                        final int n82 = array[n81];
                        if (~n82 > ~this.anIntArrayArray4903.length) {
                            final int[] array8 = this.anIntArrayArray4903[n82];
                            for (int n83 = 0; ~array8.length < ~n83; ++n83) {
                                final int n84 = array8[n83];
                                final int n85 = 0xFFFF & this.aShortArray4946[n84];
                                final int n86 = n85 >> 1037371370 & 0x3F;
                                final int n87 = n85 >> 552003367 & 0x7;
                                final int n88 = n85 & 0x7F;
                                final int n89 = anInt1256 + n86 & 0x3F;
                                int n90 = n87 + anInt1257 / 4;
                                int n91 = n88 + anInt1258;
                                if (n90 >= 0) {
                                    if (~n90 < -8) {
                                        n90 = 7;
                                    }
                                }
                                else {
                                    n90 = 0;
                                }
                                if (~n91 > -1) {
                                    n91 = 0;
                                }
                                else if (~n91 < -128) {
                                    n91 = 127;
                                }
                                this.aShortArray4946[n84] = (short)Class41.method366(Class41.method366(n90 << -1356462873, n89 << 967582634), n91);
                            }
                            b3 |= (array8.length > 0);
                        }
                    }
                    if (b3) {
                        if (this.aClass281Array4941 != null) {
                            for (int n92 = 0; this.anInt4911 > n92; ++n92) {
                                final Class281 class283 = this.aClass281Array4941[n92];
                                final Class181 class284 = this.aClass181Array4913[n92];
                                class284.anInt1428 = ((Class208.anIntArray1579[this.aShortArray4946[class283.anInt2121] & 0xFFFF] & 0xFFFFFF) | (class284.anInt1428 & 0xFF000000));
                            }
                        }
                        this.method2392(75);
                    }
                }
            }
            else if (~n == 0xFFFFFFF7) {
                if (this.anIntArrayArray4934 != null) {
                    for (final int n94 : array) {
                        if (n94 < this.anIntArrayArray4934.length) {
                            final int[] array9 = this.anIntArrayArray4934[n94];
                            for (int n95 = 0; n95 < array9.length; ++n95) {
                                final Class181 class286;
                                final Class181 class285 = class286 = this.aClass181Array4913[array9[n95]];
                                class286.anInt1427 += anInt1257;
                                final Class181 class287 = class285;
                                class287.anInt1426 += anInt1256;
                            }
                        }
                    }
                }
            }
            else if (n == 10) {
                if (this.anIntArrayArray4934 != null) {
                    for (int n96 = 0; ~i < ~n96; ++n96) {
                        final int n97 = array[n96];
                        if (~n97 > ~this.anIntArrayArray4934.length) {
                            final int[] array10 = this.anIntArrayArray4934[n97];
                            for (int n98 = 0; array10.length > n98; ++n98) {
                                final Class181 class288 = this.aClass181Array4913[array10[n98]];
                                class288.anInt1425 = class288.anInt1425 * anInt1257 >> 993080359;
                                class288.anInt1429 = class288.anInt1429 * anInt1256 >> -1056891193;
                            }
                        }
                    }
                }
            }
            else if (n == 9 && this.anIntArrayArray4934 != null) {
                for (int n99 = 0; i > n99; ++n99) {
                    final int n100 = array[n99];
                    if (~this.anIntArrayArray4934.length < ~n100) {
                        final int[] array11 = this.anIntArrayArray4934[n100];
                        for (int n101 = 0; n101 < array11.length; ++n101) {
                            final Class181 class289 = this.aClass181Array4913[array11[n101]];
                            class289.anInt1431 = (class289.anInt1431 + anInt1256 & 0x3FFF);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.BA(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + anInt1256 + ',' + anInt1257 + ',' + anInt1258 + ',' + n2 + ',' + b + ')');
        }
    }
    
    @Override
    final int G() {
        try {
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            return this.anInt4937;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.G()");
        }
    }
    
    @Override
    final Class146 method2341(final byte b, final int n, final boolean b2) {
        try {
            Class146_Sub3 class146_Sub3;
            Class146_Sub3 class146_Sub4;
            if (b != 1) {
                if (~b != 0xFFFFFFFD) {
                    if (b == 3) {
                        class146_Sub3 = this.aHa_Sub3_4899.aClass146_Sub3_4661;
                        class146_Sub4 = this.aHa_Sub3_4899.aClass146_Sub3_4657;
                    }
                    else if (~b != 0xFFFFFFFB) {
                        if (b != 5) {
                            class146_Sub4 = (class146_Sub3 = new Class146_Sub3(this.aHa_Sub3_4899, 0, 0, 1 != 0, 0 != 0));
                        }
                        else {
                            class146_Sub4 = this.aHa_Sub3_4899.aClass146_Sub3_4662;
                            class146_Sub3 = this.aHa_Sub3_4899.aClass146_Sub3_4670;
                        }
                    }
                    else {
                        class146_Sub4 = this.aHa_Sub3_4899.aClass146_Sub3_4650;
                        class146_Sub3 = this.aHa_Sub3_4899.aClass146_Sub3_4649;
                    }
                }
                else {
                    class146_Sub4 = this.aHa_Sub3_4899.aClass146_Sub3_4651;
                    class146_Sub3 = this.aHa_Sub3_4899.aClass146_Sub3_4658;
                }
            }
            else {
                class146_Sub4 = this.aHa_Sub3_4899.aClass146_Sub3_4665;
                class146_Sub3 = this.aHa_Sub3_4899.aClass146_Sub3_4656;
            }
            return this.method2395(class146_Sub4, n, b2, b != 0, -83, class146_Sub3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.T(" + b + ',' + n + ',' + b2 + ')');
        }
    }
    
    @Override
    final int ua() {
        try {
            return this.anInt4942;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.ua()");
        }
    }
    
    @Override
    final void FA(final int n) {
        try {
            final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
            final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
            for (int n4 = 0; ~this.anInt4912 < ~n4; ++n4) {
                final int n5 = n3 * this.anIntArray4959[n4] + -(n2 * this.anIntArray4935[n4]) >> -721294770;
                this.anIntArray4935[n4] = n3 * this.anIntArray4935[n4] + n2 * this.anIntArray4959[n4] >> -1169103346;
                this.anIntArray4959[n4] = n5;
            }
            this.method2410(-127);
            this.aBoolean4922 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.FA(" + n + ')');
        }
    }
    
    @Override
    final void O(final int n, final int n2, final int n3) {
        try {
            for (int n4 = 0; ~n4 > ~this.anInt4912; ++n4) {
                if (~n != 0xFFFFFF7F) {
                    this.anIntArray4906[n4] = n * this.anIntArray4906[n4] >> 1387855815;
                }
                if (n2 != 128) {
                    this.anIntArray4959[n4] = this.anIntArray4959[n4] * n2 >> 1950580391;
                }
                if (n3 != 128) {
                    this.anIntArray4935[n4] = this.anIntArray4935[n4] * n3 >> 1112352167;
                }
            }
            this.method2410(-57);
            this.aBoolean4922 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.O(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final int da() {
        try {
            return this.aShort4905;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.da()");
        }
    }
    
    @Override
    final Class35[] method2322() {
        try {
            return this.aClass35Array4919;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.PB()");
        }
    }
    
    @Override
    final void method2329(final Class111 class111, final Class246_Sub6 class246_Sub6, final int n, final int n2) {
        try {
            if (~this.anInt4936 != -1) {
                final Class111_Sub3 aClass111_Sub3_4543 = this.aHa_Sub3_4899.aClass111_Sub3_4543;
                final Class111_Sub3 class111_Sub3 = (Class111_Sub3)class111;
                if (!this.aBoolean4922) {
                    this.method2401(-21065);
                }
                Class322.aFloat2712 = aClass111_Sub3_4543.aFloat4708 * class111_Sub3.aFloat4702 + class111_Sub3.aFloat4709 * aClass111_Sub3_4543.aFloat4713 + class111_Sub3.aFloat4703 * aClass111_Sub3_4543.aFloat4704 + aClass111_Sub3_4543.aFloat4703;
                Class64_Sub18.aFloat3691 = aClass111_Sub3_4543.aFloat4704 * class111_Sub3.aFloat4713 + (aClass111_Sub3_4543.aFloat4713 * class111_Sub3.aFloat4706 + class111_Sub3.aFloat4711 * aClass111_Sub3_4543.aFloat4708);
                final float n3 = this.anInt4956 * Class64_Sub18.aFloat3691 + Class322.aFloat2712;
                final float n4 = this.anInt4950 * Class64_Sub18.aFloat3691 + Class322.aFloat2712;
                float n5;
                float n6;
                if (n4 >= n3) {
                    n5 = -this.anInt4939 + n3;
                    n6 = this.anInt4939 + n4;
                }
                else {
                    n5 = -this.anInt4939 + n4;
                    n6 = this.anInt4939 + n3;
                }
                if (this.aHa_Sub3_4899.aFloat4642 > n5 && this.aHa_Sub3_4899.anInt4640 < n6) {
                    Class98_Sub42.aFloat4234 = class111_Sub3.aFloat4713 * aClass111_Sub3_4543.aFloat4705 + (class111_Sub3.aFloat4706 * aClass111_Sub3_4543.aFloat4711 + class111_Sub3.aFloat4711 * aClass111_Sub3_4543.aFloat4712);
                    Class98_Sub43_Sub2.aFloat5909 = aClass111_Sub3_4543.aFloat4702 + (class111_Sub3.aFloat4703 * aClass111_Sub3_4543.aFloat4705 + (class111_Sub3.aFloat4709 * aClass111_Sub3_4543.aFloat4711 + class111_Sub3.aFloat4702 * aClass111_Sub3_4543.aFloat4712));
                    final float n7 = Class98_Sub42.aFloat4234 * this.anInt4956 + Class98_Sub43_Sub2.aFloat5909;
                    final float n8 = Class98_Sub43_Sub2.aFloat5909 + this.anInt4950 * Class98_Sub42.aFloat4234;
                    float n9;
                    float n10;
                    if (n8 >= n7) {
                        n9 = this.aHa_Sub3_4899.anInt4593 * (n8 + this.anInt4939);
                        n10 = (-this.anInt4939 + n7) * this.aHa_Sub3_4899.anInt4593;
                    }
                    else {
                        n10 = (-this.anInt4939 + n8) * this.aHa_Sub3_4899.anInt4593;
                        n9 = this.aHa_Sub3_4899.anInt4593 * (n7 + this.anInt4939);
                    }
                    if (n10 / n < this.aHa_Sub3_4899.aFloat4647 && this.aHa_Sub3_4899.aFloat4641 < n9 / n) {
                        Class366.aFloat3120 = class111_Sub3.aFloat4706 * aClass111_Sub3_4543.aFloat4706 + class111_Sub3.aFloat4711 * aClass111_Sub3_4543.aFloat4714 + class111_Sub3.aFloat4713 * aClass111_Sub3_4543.aFloat4710;
                        Class339_Sub1.aFloat5316 = aClass111_Sub3_4543.aFloat4709 + (class111_Sub3.aFloat4709 * aClass111_Sub3_4543.aFloat4706 + aClass111_Sub3_4543.aFloat4714 * class111_Sub3.aFloat4702 + class111_Sub3.aFloat4703 * aClass111_Sub3_4543.aFloat4710);
                        final float n11 = Class339_Sub1.aFloat5316 + this.anInt4956 * Class366.aFloat3120;
                        final float n12 = Class339_Sub1.aFloat5316 + Class366.aFloat3120 * this.anInt4950;
                        float n13;
                        float n14;
                        if (n12 >= n11) {
                            n13 = (-this.anInt4939 + n11) * this.aHa_Sub3_4899.anInt4589;
                            n14 = (this.anInt4939 + n12) * this.aHa_Sub3_4899.anInt4589;
                        }
                        else {
                            n14 = this.aHa_Sub3_4899.anInt4589 * (this.anInt4939 + n11);
                            n13 = this.aHa_Sub3_4899.anInt4589 * (n12 - this.anInt4939);
                        }
                        if (this.aHa_Sub3_4899.aFloat4584 > n13 / n && n14 / n > this.aHa_Sub3_4899.aFloat4610) {
                            if (class246_Sub6 != null || this.aClass281Array4941 != null) {
                                Class134.aFloat3463 = class111_Sub3.aFloat4704 * aClass111_Sub3_4543.aFloat4704 + (class111_Sub3.aFloat4705 * aClass111_Sub3_4543.aFloat4708 + aClass111_Sub3_4543.aFloat4713 * class111_Sub3.aFloat4710);
                                Class373_Sub2.aFloat5472 = class111_Sub3.aFloat4705 * aClass111_Sub3_4543.aFloat4712 + class111_Sub3.aFloat4710 * aClass111_Sub3_4543.aFloat4711 + class111_Sub3.aFloat4704 * aClass111_Sub3_4543.aFloat4705;
                                Canvas_Sub1.aFloat25 = class111_Sub3.aFloat4712 * aClass111_Sub3_4543.aFloat4712 + aClass111_Sub3_4543.aFloat4711 * class111_Sub3.aFloat4714 + aClass111_Sub3_4543.aFloat4705 * class111_Sub3.aFloat4708;
                                Class98_Sub18.aFloat3948 = aClass111_Sub3_4543.aFloat4706 * class111_Sub3.aFloat4714 + aClass111_Sub3_4543.aFloat4714 * class111_Sub3.aFloat4712 + aClass111_Sub3_4543.aFloat4710 * class111_Sub3.aFloat4708;
                                Class64_Sub17.aFloat3686 = aClass111_Sub3_4543.aFloat4713 * class111_Sub3.aFloat4714 + aClass111_Sub3_4543.aFloat4708 * class111_Sub3.aFloat4712 + aClass111_Sub3_4543.aFloat4704 * class111_Sub3.aFloat4708;
                                Class100.aFloat845 = class111_Sub3.aFloat4704 * aClass111_Sub3_4543.aFloat4710 + (aClass111_Sub3_4543.aFloat4706 * class111_Sub3.aFloat4710 + class111_Sub3.aFloat4705 * aClass111_Sub3_4543.aFloat4714);
                            }
                            if (class246_Sub6 != null) {
                                final int n15 = this.anInt4910 + this.anInt4924 >> -185387967;
                                final int n16 = this.anInt4921 + this.anInt4937 >> 683245665;
                                final int n17 = (int)(Class373_Sub2.aFloat5472 * n16 + (this.anInt4956 * Class98_Sub42.aFloat4234 + (Class98_Sub43_Sub2.aFloat5909 + n15 * Canvas_Sub1.aFloat25)));
                                final int n18 = (int)(Class366.aFloat3120 * this.anInt4956 + (Class339_Sub1.aFloat5316 + n15 * Class98_Sub18.aFloat3948) + Class100.aFloat845 * n16);
                                final int n19 = (int)(n15 * Class64_Sub17.aFloat3686 + Class322.aFloat2712 + Class64_Sub18.aFloat3691 * this.anInt4956 + n16 * Class134.aFloat3463);
                                final int n20 = (int)(n16 * Class373_Sub2.aFloat5472 + (Class98_Sub42.aFloat4234 * this.anInt4950 + (Class98_Sub43_Sub2.aFloat5909 + n15 * Canvas_Sub1.aFloat25)));
                                class246_Sub6.anInt5112 = this.aHa_Sub3_4899.anInt4587 - -(this.aHa_Sub3_4899.anInt4589 * (int)(Class339_Sub1.aFloat5316 + n15 * Class98_Sub18.aFloat3948 + Class366.aFloat3120 * this.anInt4950 + n16 * Class100.aFloat845) / n);
                                final int n21 = (int)(Class134.aFloat3463 * n16 + (this.anInt4950 * Class64_Sub18.aFloat3691 + (Class322.aFloat2712 + n15 * Class64_Sub17.aFloat3686)));
                                class246_Sub6.anInt5110 = this.aHa_Sub3_4899.anInt4593 * n20 / n + this.aHa_Sub3_4899.anInt4645;
                                class246_Sub6.anInt5111 = n17 * this.aHa_Sub3_4899.anInt4593 / n + this.aHa_Sub3_4899.anInt4645;
                                class246_Sub6.anInt5113 = this.aHa_Sub3_4899.anInt4587 - -(n18 * this.aHa_Sub3_4899.anInt4589 / n);
                                if (~n19 <= ~this.aHa_Sub3_4899.anInt4640 || ~n21 <= ~this.aHa_Sub3_4899.anInt4640) {
                                    class246_Sub6.aBoolean5114 = true;
                                    class246_Sub6.anInt5109 = this.aHa_Sub3_4899.anInt4645 + ((n17 - -this.anInt4939) * this.aHa_Sub3_4899.anInt4593 / n + -class246_Sub6.anInt5111);
                                }
                            }
                            this.aHa_Sub3_4899.method2056(-123, n);
                            this.aHa_Sub3_4899.method1937(4);
                            this.aHa_Sub3_4899.method2049(0, class111_Sub3);
                            this.method2407(29084);
                            this.method2393(-29619);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.U(" + ((class111 != null) ? "{...}" : "null") + ',' + ((class246_Sub6 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final int WA() {
        try {
            return this.aShort4902;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.WA()");
        }
    }
    
    @Override
    final void I(final int n, final int[] array, int anInt1256, int anInt1257, int anInt1258, final boolean b, final int n2, final int[] array2) {
        try {
            final int i = array.length;
            if (n == 0) {
                anInt1257 <<= 4;
                anInt1256 <<= 4;
                anInt1258 <<= 4;
                Class159.anInt1256 = 0;
                int n3 = 0;
                Class271.anInt2035 = 0;
                Class48_Sub1.anInt3628 = 0;
                for (int n4 = 0; ~n4 > ~i; ++n4) {
                    final int n5 = array[n4];
                    if (~n5 > ~this.anIntArrayArray4925.length) {
                        final int[] array3 = this.anIntArrayArray4925[n5];
                        for (int n6 = 0; ~array3.length < ~n6; ++n6) {
                            final int n7 = array3[n6];
                            if (this.aShortArray4944 == null || ~(n2 & this.aShortArray4944[n7]) != -1) {
                                Class159.anInt1256 += this.anIntArray4906[n7];
                                Class48_Sub1.anInt3628 += this.anIntArray4959[n7];
                                ++n3;
                                Class271.anInt2035 += this.anIntArray4935[n7];
                            }
                        }
                    }
                }
                if (~n3 >= -1) {
                    Class159.anInt1256 = anInt1256;
                    Class48_Sub1.anInt3628 = anInt1257;
                    Class271.anInt2035 = anInt1258;
                }
                else {
                    Class271.anInt2035 = anInt1258 + Class271.anInt2035 / n3;
                    Class98_Sub10.aBoolean3862 = true;
                    Class48_Sub1.anInt3628 = Class48_Sub1.anInt3628 / n3 - -anInt1257;
                    Class159.anInt1256 = anInt1256 + Class159.anInt1256 / n3;
                }
            }
            else if (~n == 0xFFFFFFFE) {
                if (array2 != null) {
                    final int n8 = array2[1] * anInt1257 + anInt1256 * array2[0] + (anInt1258 * array2[2] + 8192) >> -297975666;
                    final int n9 = anInt1258 * array2[5] + anInt1257 * array2[4] + array2[3] * anInt1256 + 8192 >> -450194290;
                    final int n10 = anInt1257 * array2[7] + (array2[6] * anInt1256 + anInt1258 * array2[8]) + 8192 >> -717041778;
                    anInt1256 = n8;
                    anInt1257 = n9;
                    anInt1258 = n10;
                }
                anInt1258 <<= 4;
                anInt1257 <<= 4;
                anInt1256 <<= 4;
                for (final int n11 : array) {
                    if (n11 < this.anIntArrayArray4925.length) {
                        final int[] array4 = this.anIntArrayArray4925[n11];
                        for (int n12 = 0; ~n12 > ~array4.length; ++n12) {
                            final int n13 = array4[n12];
                            if (this.aShortArray4944 == null || (this.aShortArray4944[n13] & n2) != 0x0) {
                                final int[] anIntArray4906 = this.anIntArray4906;
                                final int n14 = n13;
                                anIntArray4906[n14] += anInt1256;
                                final int[] anIntArray4907 = this.anIntArray4959;
                                final int n15 = n13;
                                anIntArray4907[n15] += anInt1257;
                                final int[] anIntArray4908 = this.anIntArray4935;
                                final int n16 = n13;
                                anIntArray4908[n16] += anInt1258;
                            }
                        }
                    }
                }
            }
            else if (~n == 0xFFFFFFFD) {
                if (array2 != null) {
                    final int n17 = array2[9] << -2057775004;
                    final int n18 = array2[10] << -1149619324;
                    final int n19 = array2[11] << -314860284;
                    final int n20 = array2[12] << 65193988;
                    final int n21 = array2[13] << 230320484;
                    final int n22 = array2[14] << -2013413692;
                    if (Class98_Sub10.aBoolean3862) {
                        final int n23 = 8192 + array2[3] * Class48_Sub1.anInt3628 + Class159.anInt1256 * array2[0] + array2[6] * Class271.anInt2035 >> -546007570;
                        final int n24 = 8192 + Class159.anInt1256 * array2[1] + (array2[4] * Class48_Sub1.anInt3628 - -(Class271.anInt2035 * array2[7])) >> -1553249426;
                        final int anInt1259 = n23 + n20;
                        final int anInt1260 = n24 + n21;
                        final int n25 = array2[8] * Class271.anInt2035 + (Class159.anInt1256 * array2[2] + Class48_Sub1.anInt3628 * array2[5] + 8192) >> -1809377138;
                        Class159.anInt1256 = anInt1259;
                        Class48_Sub1.anInt3628 = anInt1260;
                        final int anInt1261 = n25 + n22;
                        Class98_Sub10.aBoolean3862 = false;
                        Class271.anInt2035 = anInt1261;
                    }
                    final int[] array5 = new int[9];
                    final int n26 = Class284_Sub2_Sub2.anIntArray6202[anInt1256];
                    final int n27 = Class284_Sub2_Sub2.anIntArray6200[anInt1256];
                    final int n28 = Class284_Sub2_Sub2.anIntArray6202[anInt1257];
                    final int n29 = Class284_Sub2_Sub2.anIntArray6200[anInt1257];
                    final int n30 = Class284_Sub2_Sub2.anIntArray6202[anInt1258];
                    final int n31 = Class284_Sub2_Sub2.anIntArray6200[anInt1258];
                    final int n32 = 8192 + n27 * n30 >> 1770356814;
                    final int n33 = 8192 + n31 * n27 >> -51726738;
                    array5[3] = 8192 + n31 * n26 >> 1062155310;
                    array5[5] = -n27;
                    array5[2] = n26 * n29 + 8192 >> 55094222;
                    array5[1] = -n28 * n31 + (n32 * n29 + 8192) >> -708894034;
                    array5[8] = n26 * n28 + 8192 >> -1650781618;
                    array5[6] = n30 * -n29 - -(n28 * n33) + 8192 >> 21669550;
                    array5[0] = n28 * n30 - -(n33 * n29) + 8192 >> -1363422290;
                    array5[7] = 8192 + (n29 * n31 - -(n28 * n32)) >> 942796078;
                    array5[4] = 8192 + n26 * n30 >> -1801107186;
                    final int n34 = -Class159.anInt1256 * array5[0] + (-Class48_Sub1.anInt3628 * array5[1] + (-Class271.anInt2035 * array5[2] + 8192)) >> -249354002;
                    final int n35 = 8192 + array5[5] * -Class271.anInt2035 + (array5[3] * -Class159.anInt1256 + array5[4] * -Class48_Sub1.anInt3628) >> 2119544014;
                    final int n36 = 8192 + array5[7] * -Class48_Sub1.anInt3628 + (-Class159.anInt1256 * array5[6] - -(array5[8] * -Class271.anInt2035)) >> 709762958;
                    final int n37 = n34 + Class159.anInt1256;
                    final int n38 = Class48_Sub1.anInt3628 + n35;
                    final int n39 = Class271.anInt2035 + n36;
                    final int[] array6 = new int[9];
                    for (int k = 0; k < 3; ++k) {
                        for (int n40 = 0; ~n40 > -4; ++n40) {
                            int n41 = 0;
                            for (int l = 0; l < 3; ++l) {
                                n41 += array2[n40 * 3 + l] * array5[k * 3 - -l];
                            }
                            array6[k * 3 - -n40] = 8192 + n41 >> 537558350;
                        }
                    }
                    final int n42 = 8192 + (array5[1] * n21 + array5[0] * n20 - -(n22 * array5[2])) >> 38009454;
                    final int n43 = 8192 + array5[3] * n20 + array5[4] * n21 - -(n22 * array5[5]) >> -860146482;
                    final int n44 = n42 + n37;
                    final int n45 = n43 + n38;
                    final int n46 = (8192 + n21 * array5[7] + (n20 * array5[6] - -(array5[8] * n22)) >> 1426342734) + n39;
                    final int[] array7 = new int[9];
                    for (int n47 = 0; ~n47 > -4; ++n47) {
                        for (int n48 = 0; ~n48 > -4; ++n48) {
                            int n49 = 0;
                            for (int n50 = 0; ~n50 > -4; ++n50) {
                                n49 += array6[n50 * 3 + n48] * array2[n50 + n47 * 3];
                            }
                            array7[n47 * 3 + n48] = 8192 + n49 >> -1909840882;
                        }
                    }
                    final int n51 = n46 * array2[2] + (n45 * array2[1] + (array2[0] * n44 + 8192)) >> -1669380050;
                    final int n52 = 8192 + n44 * array2[3] + (array2[4] * n45 + n46 * array2[5]) >> 2007613838;
                    final int n53 = n51 + n17;
                    final int n54 = n52 + n18;
                    final int n55 = (8192 + (n44 * array2[6] - -(array2[7] * n45) - -(n46 * array2[8])) >> -243383218) + n19;
                    for (int n56 = 0; ~n56 > ~i; ++n56) {
                        final int n57 = array[n56];
                        if (this.anIntArrayArray4925.length > n57) {
                            final int[] array8 = this.anIntArrayArray4925[n57];
                            for (int n58 = 0; ~n58 > ~array8.length; ++n58) {
                                final int n59 = array8[n58];
                                if (this.aShortArray4944 == null || ~(this.aShortArray4944[n59] & n2) != -1) {
                                    final int n60 = 8192 + this.anIntArray4935[n59] * array7[2] + (this.anIntArray4959[n59] * array7[1] + array7[0] * this.anIntArray4906[n59]) >> 1946774638;
                                    final int n61 = 8192 + (array7[5] * this.anIntArray4935[n59] + this.anIntArray4959[n59] * array7[4]) + array7[3] * this.anIntArray4906[n59] >> 673886830;
                                    final int n62 = array7[8] * this.anIntArray4935[n59] + array7[7] * this.anIntArray4959[n59] + array7[6] * this.anIntArray4906[n59] + 8192 >> 1381701934;
                                    final int n63 = n60 + n53;
                                    final int n64 = n61 + n54;
                                    this.anIntArray4906[n59] = n63;
                                    final int n65 = n62 + n55;
                                    this.anIntArray4959[n59] = n64;
                                    this.anIntArray4935[n59] = n65;
                                }
                            }
                        }
                    }
                }
                else {
                    for (int n66 = 0; i > n66; ++n66) {
                        final int n67 = array[n66];
                        if (~n67 > ~this.anIntArrayArray4925.length) {
                            final int[] array9 = this.anIntArrayArray4925[n67];
                            for (int n68 = 0; array9.length > n68; ++n68) {
                                final int n69 = array9[n68];
                                if (this.aShortArray4944 == null || ~(this.aShortArray4944[n69] & n2) != -1) {
                                    final int[] anIntArray4909 = this.anIntArray4906;
                                    final int n70 = n69;
                                    anIntArray4909[n70] -= Class159.anInt1256;
                                    final int[] anIntArray4910 = this.anIntArray4959;
                                    final int n71 = n69;
                                    anIntArray4910[n71] -= Class48_Sub1.anInt3628;
                                    final int[] anIntArray4911 = this.anIntArray4935;
                                    final int n72 = n69;
                                    anIntArray4911[n72] -= Class271.anInt2035;
                                    if (anInt1258 != 0) {
                                        final int n73 = Class284_Sub2_Sub2.anIntArray6200[anInt1258];
                                        final int n74 = Class284_Sub2_Sub2.anIntArray6202[anInt1258];
                                        final int n75 = 16383 + (n74 * this.anIntArray4906[n69] + this.anIntArray4959[n69] * n73) >> 1282326286;
                                        this.anIntArray4959[n69] = -(this.anIntArray4906[n69] * n73) + (n74 * this.anIntArray4959[n69] + 16383) >> 335810830;
                                        this.anIntArray4906[n69] = n75;
                                    }
                                    if (anInt1256 != 0) {
                                        final int n76 = Class284_Sub2_Sub2.anIntArray6200[anInt1256];
                                        final int n77 = Class284_Sub2_Sub2.anIntArray6202[anInt1256];
                                        final int n78 = 16383 + this.anIntArray4959[n69] * n77 - n76 * this.anIntArray4935[n69] >> -1366478770;
                                        this.anIntArray4935[n69] = this.anIntArray4959[n69] * n76 + (this.anIntArray4935[n69] * n77 + 16383) >> 1555507054;
                                        this.anIntArray4959[n69] = n78;
                                    }
                                    if (anInt1257 != 0) {
                                        final int n79 = Class284_Sub2_Sub2.anIntArray6200[anInt1257];
                                        final int n80 = Class284_Sub2_Sub2.anIntArray6202[anInt1257];
                                        final int n81 = n79 * this.anIntArray4935[n69] + this.anIntArray4906[n69] * n80 + 16383 >> 1223188654;
                                        this.anIntArray4935[n69] = 16383 + -(n79 * this.anIntArray4906[n69]) + n80 * this.anIntArray4935[n69] >> 1779323214;
                                        this.anIntArray4906[n69] = n81;
                                    }
                                    final int[] anIntArray4912 = this.anIntArray4906;
                                    final int n82 = n69;
                                    anIntArray4912[n82] += Class159.anInt1256;
                                    final int[] anIntArray4913 = this.anIntArray4959;
                                    final int n83 = n69;
                                    anIntArray4913[n83] += Class48_Sub1.anInt3628;
                                    final int[] anIntArray4914 = this.anIntArray4935;
                                    final int n84 = n69;
                                    anIntArray4914[n84] += Class271.anInt2035;
                                }
                            }
                        }
                    }
                    if (b) {
                        for (int n85 = 0; i > n85; ++n85) {
                            final int n86 = array[n85];
                            if (this.anIntArrayArray4925.length > n86) {
                                final int[] array10 = this.anIntArrayArray4925[n86];
                                for (int n87 = 0; ~array10.length < ~n87; ++n87) {
                                    final int n88 = array10[n87];
                                    if (this.aShortArray4944 == null || (n2 & this.aShortArray4944[n88]) != 0x0) {
                                        final int n89 = this.anIntArray4932[n88];
                                        for (int n90 = this.anIntArray4932[1 + n88], n91 = n89; ~n90 < ~n91; ++n91) {
                                            final short n92 = (short)(this.aShortArray4960[n91] - 1);
                                            if (~n92 == 0x0) {
                                                break;
                                            }
                                            if (~anInt1258 != -1) {
                                                final int n93 = Class284_Sub2_Sub2.anIntArray6200[anInt1258];
                                                final int n94 = Class284_Sub2_Sub2.anIntArray6202[anInt1258];
                                                final int n95 = n93 * this.aShortArray4957[n92] - -(this.aShortArray4953[n92] * n94) + 16383 >> -343227890;
                                                this.aShortArray4957[n92] = (short)(-(n93 * this.aShortArray4953[n92]) + (n94 * this.aShortArray4957[n92] + 16383) >> -125577522);
                                                this.aShortArray4953[n92] = (short)n95;
                                            }
                                            if (anInt1256 != 0) {
                                                final int n96 = Class284_Sub2_Sub2.anIntArray6200[anInt1256];
                                                final int n97 = Class284_Sub2_Sub2.anIntArray6202[anInt1256];
                                                final int n98 = 16383 + -(n96 * this.aShortArray4962[n92]) + this.aShortArray4957[n92] * n97 >> 133625070;
                                                this.aShortArray4962[n92] = (short)(16383 + (this.aShortArray4962[n92] * n97 + n96 * this.aShortArray4957[n92]) >> -1117990962);
                                                this.aShortArray4957[n92] = (short)n98;
                                            }
                                            if (~anInt1257 != -1) {
                                                final int n99 = Class284_Sub2_Sub2.anIntArray6200[anInt1257];
                                                final int n100 = Class284_Sub2_Sub2.anIntArray6202[anInt1257];
                                                final int n101 = 16383 + n100 * this.aShortArray4953[n92] + n99 * this.aShortArray4962[n92] >> -153226066;
                                                this.aShortArray4962[n92] = (short)(16383 + (-(this.aShortArray4953[n92] * n99) + n100 * this.aShortArray4962[n92]) >> -1863313682);
                                                this.aShortArray4953[n92] = (short)n101;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        this.method2404((byte)(-64));
                    }
                }
            }
            else if (n == 3) {
                if (array2 == null) {
                    for (int n102 = 0; ~i < ~n102; ++n102) {
                        final int n103 = array[n102];
                        if (this.anIntArrayArray4925.length > n103) {
                            final int[] array11 = this.anIntArrayArray4925[n103];
                            for (int n104 = 0; ~array11.length < ~n104; ++n104) {
                                final int n105 = array11[n104];
                                if (this.aShortArray4944 == null || ~(n2 & this.aShortArray4944[n105]) != -1) {
                                    final int[] anIntArray4915 = this.anIntArray4906;
                                    final int n106 = n105;
                                    anIntArray4915[n106] -= Class159.anInt1256;
                                    final int[] anIntArray4916 = this.anIntArray4959;
                                    final int n107 = n105;
                                    anIntArray4916[n107] -= Class48_Sub1.anInt3628;
                                    final int[] anIntArray4917 = this.anIntArray4935;
                                    final int n108 = n105;
                                    anIntArray4917[n108] -= Class271.anInt2035;
                                    this.anIntArray4906[n105] = anInt1256 * this.anIntArray4906[n105] >> 1857229127;
                                    this.anIntArray4959[n105] = anInt1257 * this.anIntArray4959[n105] >> 762693767;
                                    this.anIntArray4935[n105] = this.anIntArray4935[n105] * anInt1258 >> 1319107975;
                                    final int[] anIntArray4918 = this.anIntArray4906;
                                    final int n109 = n105;
                                    anIntArray4918[n109] += Class159.anInt1256;
                                    final int[] anIntArray4919 = this.anIntArray4959;
                                    final int n110 = n105;
                                    anIntArray4919[n110] += Class48_Sub1.anInt3628;
                                    final int[] anIntArray4920 = this.anIntArray4935;
                                    final int n111 = n105;
                                    anIntArray4920[n111] += Class271.anInt2035;
                                }
                            }
                        }
                    }
                }
                else {
                    final int n112 = array2[9] << -1072485180;
                    final int n113 = array2[10] << 637504484;
                    final int n114 = array2[11] << -1464152764;
                    final int n115 = array2[12] << -1062932860;
                    final int n116 = array2[13] << -197946108;
                    final int n117 = array2[14] << -685372828;
                    if (Class98_Sub10.aBoolean3862) {
                        final int n118 = array2[6] * Class271.anInt2035 + array2[3] * Class48_Sub1.anInt3628 + array2[0] * Class159.anInt1256 + 8192 >> -1799677650;
                        final int n119 = array2[1] * Class159.anInt1256 - (-(Class48_Sub1.anInt3628 * array2[4]) - (array2[7] * Class271.anInt2035 + 8192)) >> -20248434;
                        final int n120 = 8192 + (Class159.anInt1256 * array2[2] - -(Class48_Sub1.anInt3628 * array2[5])) + array2[8] * Class271.anInt2035 >> -197121842;
                        final int anInt1262 = n118 + n115;
                        final int anInt1263 = n119 + n116;
                        Class159.anInt1256 = anInt1262;
                        Class48_Sub1.anInt3628 = anInt1263;
                        Class271.anInt2035 = n120 + n117;
                        Class98_Sub10.aBoolean3862 = false;
                    }
                    final int n121 = anInt1256 << 1807961519 >> -1359455193;
                    final int n122 = anInt1257 << -564514769 >> 1703037991;
                    final int n123 = anInt1258 << 1273318895 >> -1071031225;
                    final int n124 = 8192 + n121 * -Class159.anInt1256 >> 1156571662;
                    final int n125 = 8192 + n122 * -Class48_Sub1.anInt3628 >> -1000051794;
                    final int n126 = n123 * -Class271.anInt2035 + 8192 >> -1345521650;
                    final int n127 = n124 + Class159.anInt1256;
                    final int n128 = Class48_Sub1.anInt3628 + n125;
                    final int n129 = Class271.anInt2035 + n126;
                    final int[] array12 = { 8192 + array2[0] * n121 >> -336975890, array2[3] * n121 + 8192 >> 1498779438, 8192 + array2[6] * n121 >> -818202482, 8192 + n122 * array2[1] >> 226619886, array2[4] * n122 + 8192 >> -1337425842, n122 * array2[7] + 8192 >> -233195602, array2[2] * n123 + 8192 >> -1014771922, n123 * array2[5] + 8192 >> -1214480882, n123 * array2[8] + 8192 >> 1063705422 };
                    final int n130 = 8192 + n115 * n121 >> 238722798;
                    final int n131 = n122 * n116 + 8192 >> -713218354;
                    final int n132 = n130 + n127;
                    final int n133 = n131 + n128;
                    final int n134 = (8192 + n117 * n123 >> -312558418) + n129;
                    final int[] array13 = new int[9];
                    for (int n135 = 0; ~n135 > -4; ++n135) {
                        for (int n136 = 0; ~n136 > -4; ++n136) {
                            int n137 = 0;
                            for (int n138 = 0; n138 < 3; ++n138) {
                                n137 += array12[3 * n138 + n136] * array2[3 * n135 - -n138];
                            }
                            array13[3 * n135 + n136] = n137 + 8192 >> 1291562030;
                        }
                    }
                    final int n139 = 8192 + n134 * array2[2] + (n132 * array2[0] - -(array2[1] * n133)) >> -864140018;
                    final int n140 = n134 * array2[5] + (array2[3] * n132 - -(array2[4] * n133) + 8192) >> -807391858;
                    final int n141 = 8192 + (array2[8] * n134 + array2[6] * n132 + array2[7] * n133) >> -645651762;
                    final int n142 = n140 + n113;
                    final int n143 = n139 + n112;
                    final int n144 = n141 + n114;
                    for (int n145 = 0; i > n145; ++n145) {
                        final int n146 = array[n145];
                        if (~n146 > ~this.anIntArrayArray4925.length) {
                            final int[] array14 = this.anIntArrayArray4925[n146];
                            for (int n147 = 0; n147 < array14.length; ++n147) {
                                final int n148 = array14[n147];
                                if (this.aShortArray4944 == null || ~(this.aShortArray4944[n148] & n2) != -1) {
                                    final int n149 = 8192 + (array13[2] * this.anIntArray4935[n148] + array13[0] * this.anIntArray4906[n148]) + array13[1] * this.anIntArray4959[n148] >> 1301391278;
                                    final int n150 = 8192 + array13[5] * this.anIntArray4935[n148] + (array13[4] * this.anIntArray4959[n148] + array13[3] * this.anIntArray4906[n148]) >> 1577473550;
                                    final int n151 = 8192 + array13[8] * this.anIntArray4935[n148] + (this.anIntArray4959[n148] * array13[7] + array13[6] * this.anIntArray4906[n148]) >> -1810234034;
                                    final int n152 = n150 + n142;
                                    final int n153 = n149 + n143;
                                    final int n154 = n151 + n144;
                                    this.anIntArray4906[n148] = n153;
                                    this.anIntArray4959[n148] = n152;
                                    this.anIntArray4935[n148] = n154;
                                }
                            }
                        }
                    }
                }
            }
            else if (~n == 0xFFFFFFFA) {
                if (this.anIntArrayArray4903 != null) {
                    boolean b2 = false;
                    for (final int n156 : array) {
                        if (~n156 > ~this.anIntArrayArray4903.length) {
                            final int[] array15 = this.anIntArrayArray4903[n156];
                            for (int n157 = 0; array15.length > n157; ++n157) {
                                final int n158 = array15[n157];
                                if (this.aShortArray4917 == null || (this.aShortArray4917[n158] & n2) != 0x0) {
                                    int n159 = 8 * anInt1256 + (0xFF & this.aByteArray4958[n158]);
                                    if (~n159 > -1) {
                                        n159 = 0;
                                    }
                                    else if (~n159 < -256) {
                                        n159 = 255;
                                    }
                                    this.aByteArray4958[n158] = (byte)n159;
                                }
                            }
                            b2 |= (array15.length > 0);
                        }
                    }
                    if (b2) {
                        if (this.aClass281Array4941 != null) {
                            for (int n160 = 0; ~this.anInt4911 < ~n160; ++n160) {
                                final Class281 class281 = this.aClass281Array4941[n160];
                                final Class181 class282 = this.aClass181Array4913[n160];
                                class282.anInt1428 = ((0xFFFFFF & class282.anInt1428) | 255 - (this.aByteArray4958[class281.anInt2121] & 0xFF) << 782787288);
                            }
                        }
                        this.method2392(-66);
                    }
                }
            }
            else if (~n == 0xFFFFFFF8) {
                if (this.anIntArrayArray4903 != null) {
                    boolean b3 = false;
                    for (final int n162 : array) {
                        if (n162 < this.anIntArrayArray4903.length) {
                            final int[] array16 = this.anIntArrayArray4903[n162];
                            for (int n163 = 0; n163 < array16.length; ++n163) {
                                final int n164 = array16[n163];
                                if (this.aShortArray4917 == null || (n2 & this.aShortArray4917[n164]) != 0x0) {
                                    final int n165 = this.aShortArray4946[n164] & 0xFFFF;
                                    final int n166 = (n165 & 0xFC7B) >> -2230550;
                                    final int n167 = 0x7 & n165 >> 2065558343;
                                    final int n168 = n166 - -anInt1256 & 0x3F;
                                    int n169 = n167 + anInt1257 / 4;
                                    int n170 = (0x7F & n165) + anInt1258;
                                    if (~n169 <= -1) {
                                        if (n169 > 7) {
                                            n169 = 7;
                                        }
                                    }
                                    else {
                                        n169 = 0;
                                    }
                                    if (~n170 > -1) {
                                        n170 = 0;
                                    }
                                    else if (~n170 < -128) {
                                        n170 = 127;
                                    }
                                    this.aShortArray4946[n164] = (short)Class41.method366(Class41.method366(n168 << 13076682, n169 << 1033198727), n170);
                                }
                            }
                            b3 |= (array16.length > 0);
                        }
                    }
                    if (b3) {
                        if (this.aClass281Array4941 != null) {
                            for (int n171 = 0; ~n171 > ~this.anInt4911; ++n171) {
                                final Class281 class283 = this.aClass281Array4941[n171];
                                final Class181 class284 = this.aClass181Array4913[n171];
                                class284.anInt1428 = ((0xFFFFFF & Class208.anIntArray1579[0xFFFF & this.aShortArray4946[class283.anInt2121]]) | (0xFF000000 & class284.anInt1428));
                            }
                        }
                        this.method2392(81);
                    }
                }
            }
            else if (n == 8) {
                if (this.anIntArrayArray4934 != null) {
                    for (int n172 = 0; ~i < ~n172; ++n172) {
                        final int n173 = array[n172];
                        if (~this.anIntArrayArray4934.length < ~n173) {
                            final int[] array17 = this.anIntArrayArray4934[n173];
                            for (int n174 = 0; n174 < array17.length; ++n174) {
                                final Class181 class286;
                                final Class181 class285 = class286 = this.aClass181Array4913[array17[n174]];
                                class286.anInt1426 += anInt1256;
                                final Class181 class287 = class285;
                                class287.anInt1427 += anInt1257;
                            }
                        }
                    }
                }
            }
            else if (n == 10) {
                if (this.anIntArrayArray4934 != null) {
                    for (int n175 = 0; ~i < ~n175; ++n175) {
                        final int n176 = array[n175];
                        if (this.anIntArrayArray4934.length > n176) {
                            final int[] array18 = this.anIntArrayArray4934[n176];
                            for (int n177 = 0; array18.length > n177; ++n177) {
                                final Class181 class288 = this.aClass181Array4913[array18[n177]];
                                class288.anInt1425 = anInt1257 * class288.anInt1425 >> -1545119353;
                                class288.anInt1429 = anInt1256 * class288.anInt1429 >> 980544551;
                            }
                        }
                    }
                }
            }
            else if (~n == 0xFFFFFFF6 && this.anIntArrayArray4934 != null) {
                for (int n178 = 0; i > n178; ++n178) {
                    final int n179 = array[n178];
                    if (~this.anIntArrayArray4934.length < ~n179) {
                        final int[] array19 = this.anIntArrayArray4934[n179];
                        for (int n180 = 0; ~array19.length < ~n180; ++n180) {
                            final Class181 class289 = this.aClass181Array4913[array19[n180]];
                            class289.anInt1431 = (0x3FFF & anInt1256 + class289.anInt1431);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.I(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + anInt1256 + ',' + anInt1257 + ',' + anInt1258 + ',' + b + ',' + n2 + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean F() {
        try {
            return this.aBoolean4938;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.F()");
        }
    }
    
    @Override
    final void P(final int n, final int anInt1256, final int anInt1257, final int anInt1258) {
        try {
            if (n == 0) {
                Class271.anInt2035 = 0;
                Class159.anInt1256 = 0;
                int n2 = 0;
                Class48_Sub1.anInt3628 = 0;
                for (int i = 0; i < this.anInt4912; ++i) {
                    Class159.anInt1256 += this.anIntArray4906[i];
                    Class48_Sub1.anInt3628 += this.anIntArray4959[i];
                    Class271.anInt2035 += this.anIntArray4935[i];
                    ++n2;
                }
                if (~n2 < -1) {
                    Class48_Sub1.anInt3628 = anInt1257 + Class48_Sub1.anInt3628 / n2;
                    Class271.anInt2035 = Class271.anInt2035 / n2 + anInt1258;
                    Class159.anInt1256 = anInt1256 + Class159.anInt1256 / n2;
                }
                else {
                    Class159.anInt1256 = anInt1256;
                    Class271.anInt2035 = anInt1258;
                    Class48_Sub1.anInt3628 = anInt1257;
                }
            }
            else if (n == 1) {
                for (int n3 = 0; ~this.anInt4912 < ~n3; ++n3) {
                    final int[] anIntArray4906 = this.anIntArray4906;
                    final int n4 = n3;
                    anIntArray4906[n4] += anInt1256;
                    final int[] anIntArray4907 = this.anIntArray4959;
                    final int n5 = n3;
                    anIntArray4907[n5] += anInt1257;
                    final int[] anIntArray4908 = this.anIntArray4935;
                    final int n6 = n3;
                    anIntArray4908[n6] += anInt1258;
                }
            }
            else if (n == 2) {
                for (int j = 0; j < this.anInt4912; ++j) {
                    final int[] anIntArray4909 = this.anIntArray4906;
                    final int n7 = j;
                    anIntArray4909[n7] -= Class159.anInt1256;
                    final int[] anIntArray4910 = this.anIntArray4959;
                    final int n8 = j;
                    anIntArray4910[n8] -= Class48_Sub1.anInt3628;
                    final int[] anIntArray4911 = this.anIntArray4935;
                    final int n9 = j;
                    anIntArray4911[n9] -= Class271.anInt2035;
                    if (anInt1258 != 0) {
                        final int n10 = Class284_Sub2_Sub2.anIntArray6200[anInt1258];
                        final int n11 = Class284_Sub2_Sub2.anIntArray6202[anInt1258];
                        final int n12 = 16383 + n11 * this.anIntArray4906[j] + this.anIntArray4959[j] * n10 >> -74393330;
                        this.anIntArray4959[j] = -(n10 * this.anIntArray4906[j]) + this.anIntArray4959[j] * n11 + 16383 >> 2052943790;
                        this.anIntArray4906[j] = n12;
                    }
                    if (~anInt1256 != -1) {
                        final int n13 = Class284_Sub2_Sub2.anIntArray6200[anInt1256];
                        final int n14 = Class284_Sub2_Sub2.anIntArray6202[anInt1256];
                        final int n15 = 16383 + (-(n13 * this.anIntArray4935[j]) + n14 * this.anIntArray4959[j]) >> 1855147406;
                        this.anIntArray4935[j] = 16383 + this.anIntArray4959[j] * n13 - -(this.anIntArray4935[j] * n14) >> 1727674158;
                        this.anIntArray4959[j] = n15;
                    }
                    if (anInt1257 != 0) {
                        final int n16 = Class284_Sub2_Sub2.anIntArray6200[anInt1257];
                        final int n17 = Class284_Sub2_Sub2.anIntArray6202[anInt1257];
                        final int n18 = this.anIntArray4935[j] * n16 + this.anIntArray4906[j] * n17 + 16383 >> 1740414926;
                        this.anIntArray4935[j] = 16383 + -(n16 * this.anIntArray4906[j]) + n17 * this.anIntArray4935[j] >> 1673869102;
                        this.anIntArray4906[j] = n18;
                    }
                    final int[] anIntArray4912 = this.anIntArray4906;
                    final int n19 = j;
                    anIntArray4912[n19] += Class159.anInt1256;
                    final int[] anIntArray4913 = this.anIntArray4959;
                    final int n20 = j;
                    anIntArray4913[n20] += Class48_Sub1.anInt3628;
                    final int[] anIntArray4914 = this.anIntArray4935;
                    final int n21 = j;
                    anIntArray4914[n21] += Class271.anInt2035;
                }
            }
            else if (~n == 0xFFFFFFFC) {
                for (int n22 = 0; ~n22 > ~this.anInt4912; ++n22) {
                    final int[] anIntArray4915 = this.anIntArray4906;
                    final int n23 = n22;
                    anIntArray4915[n23] -= Class159.anInt1256;
                    final int[] anIntArray4916 = this.anIntArray4959;
                    final int n24 = n22;
                    anIntArray4916[n24] -= Class48_Sub1.anInt3628;
                    final int[] anIntArray4917 = this.anIntArray4935;
                    final int n25 = n22;
                    anIntArray4917[n25] -= Class271.anInt2035;
                    this.anIntArray4906[n22] = anInt1256 * this.anIntArray4906[n22] / 128;
                    this.anIntArray4959[n22] = this.anIntArray4959[n22] * anInt1257 / 128;
                    this.anIntArray4935[n22] = this.anIntArray4935[n22] * anInt1258 / 128;
                    final int[] anIntArray4918 = this.anIntArray4906;
                    final int n26 = n22;
                    anIntArray4918[n26] += Class159.anInt1256;
                    final int[] anIntArray4919 = this.anIntArray4959;
                    final int n27 = n22;
                    anIntArray4919[n27] += Class48_Sub1.anInt3628;
                    final int[] anIntArray4920 = this.anIntArray4935;
                    final int n28 = n22;
                    anIntArray4920[n28] += Class271.anInt2035;
                }
            }
            else if (n == 5) {
                for (int n29 = 0; ~this.anInt4948 < ~n29; ++n29) {
                    int n30 = 8 * anInt1256 + (this.aByteArray4958[n29] & 0xFF);
                    if (~n30 <= -1) {
                        if (n30 > 255) {
                            n30 = 255;
                        }
                    }
                    else {
                        n30 = 0;
                    }
                    this.aByteArray4958[n29] = (byte)n30;
                }
                if (this.aClass281Array4941 != null) {
                    for (int n31 = 0; ~n31 > ~this.anInt4911; ++n31) {
                        final Class281 class281 = this.aClass281Array4941[n31];
                        final Class181 class282 = this.aClass181Array4913[n31];
                        class282.anInt1428 = ((class282.anInt1428 & 0xFFFFFF) | 255 + -(0xFF & this.aByteArray4958[class281.anInt2121]) << 830117720);
                    }
                }
                this.method2392(-111);
            }
            else if (n == 7) {
                for (int n32 = 0; ~this.anInt4948 < ~n32; ++n32) {
                    final int n33 = this.aShortArray4946[n32] & 0xFFFF;
                    final int n34 = (0xFD09 & n33) >> 1967714794;
                    final int n35 = n33 >> -1093201017 & 0x7;
                    final int n36 = anInt1256 + n34 & 0x3F;
                    final int n37 = 0x7F & n33;
                    int n38 = n35 + anInt1257 / 4;
                    int n39 = n37 + anInt1258;
                    if (n38 >= 0) {
                        if (n38 > 7) {
                            n38 = 7;
                        }
                    }
                    else {
                        n38 = 0;
                    }
                    if (n39 < 0) {
                        n39 = 0;
                    }
                    else if (~n39 < -128) {
                        n39 = 127;
                    }
                    this.aShortArray4946[n32] = (short)Class41.method366(Class41.method366(n36 << 1201630634, n38 << -754765017), n39);
                }
                if (this.aClass281Array4941 != null) {
                    for (int n40 = 0; ~n40 > ~this.anInt4911; ++n40) {
                        final Class281 class283 = this.aClass281Array4941[n40];
                        final Class181 class284 = this.aClass181Array4913[n40];
                        class284.anInt1428 = ((class284.anInt1428 & 0xFF000000) | (0xFFFFFF & Class208.anIntArray1579[this.aShortArray4946[class283.anInt2121] & 0xFFFF]));
                    }
                }
                this.method2392(74);
            }
            else if (~n == 0xFFFFFFF7) {
                for (int n41 = 0; ~this.anInt4911 < ~n41; ++n41) {
                    final Class181 class286;
                    final Class181 class285 = class286 = this.aClass181Array4913[n41];
                    class286.anInt1426 += anInt1256;
                    final Class181 class287 = class285;
                    class287.anInt1427 += anInt1257;
                }
            }
            else if (~n == 0xFFFFFFF5) {
                for (int n42 = 0; ~n42 > ~this.anInt4911; ++n42) {
                    final Class181 class288 = this.aClass181Array4913[n42];
                    class288.anInt1425 = anInt1257 * class288.anInt1425 >> 1678400263;
                    class288.anInt1429 = anInt1256 * class288.anInt1429 >> 2032678311;
                }
            }
            else if (n == 9) {
                for (int k = 0; k < this.anInt4911; ++k) {
                    final Class181 class289 = this.aClass181Array4913[k];
                    class289.anInt1431 = (class289.anInt1431 - -anInt1256 & 0x3FFF);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.P(" + n + ',' + anInt1256 + ',' + anInt1257 + ',' + anInt1258 + ')');
        }
    }
    
    @Override
    final void H(final int n, final int n2, final int n3) {
        try {
            for (int i = 0; i < this.anInt4912; ++i) {
                if (n != 0) {
                    final int[] anIntArray4906 = this.anIntArray4906;
                    final int n4 = i;
                    anIntArray4906[n4] += n;
                }
                if (~n2 != -1) {
                    final int[] anIntArray4907 = this.anIntArray4959;
                    final int n5 = i;
                    anIntArray4907[n5] += n2;
                }
                if (~n3 != -1) {
                    final int[] anIntArray4908 = this.anIntArray4935;
                    final int n6 = i;
                    anIntArray4908[n6] += n3;
                }
            }
            this.method2410(-67);
            this.aBoolean4922 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.H(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void LA(final int n) {
        try {
            this.aShort4905 = (short)n;
            this.method2410(-115);
            this.method2404((byte)(-64));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.LA(" + n + ')');
        }
    }
    
    private final void method2401(final int n) {
        try {
            int anInt4924 = 32767;
            int anInt4925 = 32767;
            int anInt4926 = 32767;
            int anInt4927 = -32768;
            int anInt4928 = -32768;
            if (n == -21065) {
                int anInt4929 = -32768;
                int n2 = 0;
                int n3 = 0;
                for (int n4 = 0; this.anInt4912 > n4; ++n4) {
                    final int n5 = this.anIntArray4906[n4];
                    final int n6 = this.anIntArray4959[n4];
                    if (~anInt4928 > ~n6) {
                        anInt4928 = n6;
                    }
                    if (anInt4925 > n6) {
                        anInt4925 = n6;
                    }
                    if (n5 < anInt4924) {
                        anInt4924 = n5;
                    }
                    final int n7 = this.anIntArray4935[n4];
                    if (n5 > anInt4927) {
                        anInt4927 = n5;
                    }
                    if (~anInt4929 > ~n7) {
                        anInt4929 = n7;
                    }
                    if (~n7 > ~anInt4926) {
                        anInt4926 = n7;
                    }
                    final int n8 = n5 * n5 + n7 * n7;
                    if (n8 > n2) {
                        n2 = n8;
                    }
                    final int n9 = n6 * n6 + n7 * n7 + n5 * n5;
                    if (~n9 < ~n3) {
                        n3 = n9;
                    }
                }
                this.anInt4937 = anInt4929;
                this.anInt4924 = anInt4924;
                this.anInt4950 = anInt4928;
                this.anInt4956 = anInt4925;
                this.anInt4910 = anInt4927;
                this.anInt4921 = anInt4926;
                this.anInt4939 = (int)(0.99 + Math.sqrt(n2));
                this.anInt4943 = (int)(0.99 + Math.sqrt(n3));
                this.aBoolean4922 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.S(" + n + ')');
        }
    }
    
    @Override
    final void p(final int n, final int n2, final s s, final s s2, final int n3, final int n4, final int n5) {
        try {
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            final int n6 = this.anInt4924 + n3;
            final int n7 = this.anInt4910 + n3;
            final int n8 = this.anInt4921 + n5;
            final int n9 = this.anInt4937 + n5;
            if ((~n != 0xFFFFFFFE && n != 2 && n != 3 && n != 5) || (n6 >= 0 && s.anInt2203 > s.anInt2206 + n7 >> s.anInt2200 && n8 >= 0 && s.anInt2204 > s.anInt2206 + n9 >> s.anInt2200)) {
                if (n != 4 && n != 5) {
                    final int n10 = n6 >> s.anInt2200;
                    final int n11 = n7 - 1 - -s.anInt2206 >> s.anInt2200;
                    final int n12 = n8 >> s.anInt2200;
                    final int n13 = s.anInt2206 - 1 + n9 >> s.anInt2200;
                    if (~n4 == ~s.method3420(n12, -12639, n10) && ~n4 == ~s.method3420(n12, -12639, n11) && n4 == s.method3420(n13, -12639, n10) && ~n4 == ~s.method3420(n13, -12639, n11)) {
                        return;
                    }
                }
                else if (s2 == null || ~n6 > -1 || n7 - -s2.anInt2206 >> s2.anInt2200 >= s2.anInt2203 || ~n8 > -1 || s2.anInt2206 + n9 >> s2.anInt2200 >= s2.anInt2204) {
                    return;
                }
                if (~n != 0xFFFFFFFE) {
                    if (n != 2) {
                        if (~n == 0xFFFFFFFC) {
                            final int n14 = 4 * (n2 & 0xFF);
                            final int n15 = ((n2 & 0xFF0B) >> 103369544) * 4;
                            final int n16 = (n2 & 0xFFE75B) >> 1134887344 << -1934264058;
                            final int n17 = (n2 >> 1827617464 & 0xFF) << -1400773210;
                            if (~(n3 + -(n14 >> -219452191)) > -1 || s.anInt2203 << s.anInt2200 <= n3 + (n14 >> 893641537) - -s.anInt2206 || -(n15 >> 618172321) + n5 < 0 || s.anInt2204 << s.anInt2200 <= (n15 >> -91386751) + (n5 - -s.anInt2206)) {
                                return;
                            }
                            this.method2336(n15, n14, s, n4, n3, n5, n16, 2, n17);
                        }
                        else if (n != 4) {
                            if (~n == 0xFFFFFFFA) {
                                final int n18 = this.anInt4950 + -this.anInt4956;
                                for (int n19 = 0; this.anInt4912 > n19; ++n19) {
                                    final int n20 = n3 + this.anIntArray4906[n19];
                                    final int n21 = n5 + this.anIntArray4935[n19];
                                    final int method3417 = s.method3417(n20, n21, true);
                                    this.anIntArray4959[n19] = ((this.anIntArray4959[n19] << -2012164056) / n18 * (-n2 + (-s2.method3417(n20, n21, true) + method3417)) >> -1838068312) + (method3417 + -n4);
                                }
                            }
                        }
                        else {
                            final int n22 = this.anInt4950 - this.anInt4956;
                            for (int n23 = 0; ~this.anInt4912 < ~n23; ++n23) {
                                this.anIntArray4959[n23] = this.anIntArray4959[n23] + -n4 + (s2.method3417(n3 + this.anIntArray4906[n23], n5 + this.anIntArray4935[n23], true) + n22);
                            }
                        }
                    }
                    else {
                        final int anInt4956 = this.anInt4956;
                        if (~anInt4956 == -1) {
                            return;
                        }
                        for (int n24 = 0; ~n24 > ~this.anInt4912; ++n24) {
                            final int n25 = (this.anIntArray4959[n24] << 1047081552) / anInt4956;
                            if (~n25 > ~n2) {
                                this.anIntArray4959[n24] -= -((s.method3417(n3 + this.anIntArray4906[n24], this.anIntArray4935[n24] - -n5, true) + -n4) * (-n25 + n2) / n2);
                            }
                        }
                    }
                }
                else {
                    for (int i = 0; i < this.anInt4912; ++i) {
                        this.anIntArray4959[i] += s.method3417(n3 + this.anIntArray4906[i], n5 + this.anIntArray4935[i], true) - n4;
                    }
                }
                this.method2410(-128);
                this.aBoolean4922 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.p(" + n + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final void method2402(final int n) {
        try {
            if (this.aClass189_4933 != null) {
                this.aClass189_4933.method2639(true);
            }
            if (this.aClass189_4930 != null) {
                this.aClass189_4930.method2639(true);
            }
            if (this.aClass189_4920 != null) {
                this.aClass189_4920.method2639(true);
            }
            if (this.aClass189_4927 != null) {
                this.aClass189_4927.method2639(true);
            }
            if (n != -12884) {
                method2405(null, (byte)0, 91, 115);
            }
            if (this.aClass18_4931 != null) {
                this.aClass18_4931.method249(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.MA(" + n + ')');
        }
    }
    
    private final boolean method2403(final byte b) {
        try {
            final boolean b2 = !this.aClass189_4920.aBoolean1458;
            final boolean b3 = ~(0x37 & this.anInt4963) != -1 && !this.aClass189_4927.aBoolean1458;
            final boolean b4 = !this.aClass189_4933.aBoolean1458;
            if (b != -127) {
                this.HA();
            }
            final boolean b5 = !this.aClass189_4930.aBoolean1458;
            if (!b4 && !b2 && !b3 && !b5) {
                return true;
            }
            boolean b6 = true;
            if (b4) {
                if (this.aClass189_4933.anInterface2_Impl1_1454 == null) {
                    this.aClass189_4933.anInterface2_Impl1_1454 = this.aHa_Sub3_4899.method2060(this.aBoolean4916, 82);
                }
                final Interface2_Impl1 anInterface2_Impl1_1454 = this.aClass189_4933.anInterface2_Impl1_1454;
                anInterface2_Impl1_1454.method74(-20279, 12, this.anInt4936 * 12);
                final Buffer method75 = anInterface2_Impl1_1454.method75(true, (byte)27);
                if (method75 == null) {
                    b6 = false;
                }
                else {
                    this.aHa_Sub3_4899.aNativeInterface4526.copyPositions(this.anIntArray4906, this.anIntArray4959, this.anIntArray4935, this.aShortArray4901, 0, 12, this.anInt4936, method75.getAddress());
                    if (anInterface2_Impl1_1454.method71(13623)) {
                        this.aClass189_4933.aBoolean1458 = true;
                        this.aClass189_4933.anInterface2_Impl1_1456 = anInterface2_Impl1_1454;
                    }
                    else {
                        b6 = false;
                    }
                }
            }
            if (b2) {
                if (this.aClass189_4920.anInterface2_Impl1_1454 == null) {
                    this.aClass189_4920.anInterface2_Impl1_1454 = this.aHa_Sub3_4899.method2060(this.aBoolean4916, 57);
                }
                final Interface2_Impl1 anInterface2_Impl1_1455 = this.aClass189_4920.anInterface2_Impl1_1454;
                anInterface2_Impl1_1455.method74(-20279, 4, 4 * this.anInt4936);
                final Buffer method76 = anInterface2_Impl1_1455.method75(true, (byte)27);
                if (method76 != null) {
                    if ((0x37 & this.anInt4963) != 0x0) {
                        this.aHa_Sub3_4899.aNativeInterface4526.copyColours(this.aShortArray4946, this.aByteArray4958, this.aShortArray4954, this.aShort4902, this.aShortArray4904, 0, 4, this.anInt4936, method76.getAddress());
                    }
                    else {
                        short[] array;
                        short[] array2;
                        byte[] array3;
                        short[] array4;
                        if (this.aClass168_4923 != null) {
                            array = this.aClass168_4923.aShortArray1291;
                            array2 = this.aClass168_4923.aShortArray1288;
                            array3 = this.aClass168_4923.aByteArray1289;
                            array4 = this.aClass168_4923.aShortArray1292;
                        }
                        else {
                            array2 = this.aShortArray4953;
                            array3 = this.aByteArray4928;
                            array4 = this.aShortArray4962;
                            array = this.aShortArray4957;
                        }
                        this.aHa_Sub3_4899.aNativeInterface4526.copyLighting(this.aShortArray4946, this.aByteArray4958, this.aShortArray4954, array2, array, array4, array3, this.aShort4902, this.aShort4905, this.aShortArray4904, 0, 4, this.anInt4936, method76.getAddress());
                    }
                    if (anInterface2_Impl1_1455.method71(13623)) {
                        this.aClass189_4920.aBoolean1458 = true;
                        this.aClass189_4920.anInterface2_Impl1_1456 = anInterface2_Impl1_1455;
                    }
                    else {
                        b6 = false;
                    }
                }
                else {
                    b6 = false;
                }
            }
            if (b3) {
                if (this.aClass189_4927.anInterface2_Impl1_1454 == null) {
                    this.aClass189_4927.anInterface2_Impl1_1454 = this.aHa_Sub3_4899.method2060(this.aBoolean4916, 59);
                }
                final Interface2_Impl1 anInterface2_Impl1_1456 = this.aClass189_4927.anInterface2_Impl1_1454;
                anInterface2_Impl1_1456.method74(-20279, 12, this.anInt4936 * 12);
                final Buffer method77 = anInterface2_Impl1_1456.method75(true, (byte)27);
                if (method77 != null) {
                    short[] array5;
                    short[] array6;
                    byte[] array7;
                    short[] array8;
                    if (this.aClass168_4923 == null) {
                        array5 = this.aShortArray4962;
                        array6 = this.aShortArray4953;
                        array7 = this.aByteArray4928;
                        array8 = this.aShortArray4957;
                    }
                    else {
                        array7 = this.aClass168_4923.aByteArray1289;
                        array5 = this.aClass168_4923.aShortArray1292;
                        array6 = this.aClass168_4923.aShortArray1288;
                        array8 = this.aClass168_4923.aShortArray1291;
                    }
                    this.aHa_Sub3_4899.aNativeInterface4526.copyNormals(array6, array8, array5, array7, 3.0f / this.aShort4905, 3.0f / (this.aShort4905 + this.aShort4905 / 2), 0, 12, this.anInt4936, method77.getAddress());
                    if (anInterface2_Impl1_1456.method71(b + 13750)) {
                        this.aClass189_4927.anInterface2_Impl1_1456 = anInterface2_Impl1_1456;
                        this.aClass189_4927.aBoolean1458 = true;
                    }
                    else {
                        b6 = false;
                    }
                }
                else {
                    b6 = false;
                }
            }
            if (b5) {
                if (this.aClass189_4930.anInterface2_Impl1_1454 == null) {
                    this.aClass189_4930.anInterface2_Impl1_1454 = this.aHa_Sub3_4899.method2060(this.aBoolean4916, 45);
                }
                final Interface2_Impl1 anInterface2_Impl1_1457 = this.aClass189_4930.anInterface2_Impl1_1454;
                anInterface2_Impl1_1457.method74(-20279, 8, this.anInt4936 * 8);
                final Buffer method78 = anInterface2_Impl1_1457.method75(true, (byte)27);
                if (method78 != null) {
                    this.aHa_Sub3_4899.aNativeInterface4526.copyTexCoords(this.aFloatArray4955, this.aFloatArray4914, 0, 8, this.anInt4936, method78.getAddress());
                    if (anInterface2_Impl1_1457.method71(13623)) {
                        this.aClass189_4930.anInterface2_Impl1_1456 = anInterface2_Impl1_1457;
                        this.aClass189_4930.aBoolean1458 = true;
                    }
                    else {
                        b6 = false;
                    }
                }
                else {
                    b6 = false;
                }
            }
            return b6;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.QA(" + b + ')');
        }
    }
    
    private final void method2404(final byte b) {
        try {
            Label_0055: {
                if (~(0x37 & this.anInt4963) == -1) {
                    if (this.aClass189_4920 == null) {
                        break Label_0055;
                    }
                    this.aClass189_4920.aBoolean1458 = false;
                    if (!client.aBoolean3553) {
                        break Label_0055;
                    }
                }
                if (this.aClass189_4927 != null) {
                    this.aClass189_4927.aBoolean1458 = false;
                }
            }
            if (b != -64) {
                this.method2324();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.E(" + b + ')');
        }
    }
    
    @Override
    final void VA(final int n) {
        try {
            final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
            final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
            for (int i = 0; i < this.anInt4912; ++i) {
                final int n4 = this.anIntArray4906[i] * n3 + n2 * this.anIntArray4959[i] >> 850249230;
                this.anIntArray4959[i] = n3 * this.anIntArray4959[i] + -(this.anIntArray4906[i] * n2) >> 1646329358;
                this.anIntArray4906[i] = n4;
            }
            this.method2410(-79);
            this.aBoolean4922 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.VA(" + n + ')');
        }
    }
    
    @Override
    final boolean NA() {
        try {
            if (this.anIntArrayArray4925 == null) {
                return false;
            }
            for (int n = 0; ~n > ~this.anInt4929; ++n) {
                final int[] anIntArray4906 = this.anIntArray4906;
                final int n2 = n;
                anIntArray4906[n2] <<= 4;
                final int[] anIntArray4907 = this.anIntArray4959;
                final int n3 = n;
                anIntArray4907[n3] <<= 4;
                final int[] anIntArray4908 = this.anIntArray4935;
                final int n4 = n;
                anIntArray4908[n4] <<= 4;
            }
            Class271.anInt2035 = 0;
            Class48_Sub1.anInt3628 = 0;
            Class159.anInt1256 = 0;
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.NA()");
        }
    }
    
    @Override
    final void method2342() {
    }
    
    @Override
    final void a(final int n) {
        try {
            final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
            final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
            for (int n4 = 0; this.anInt4912 > n4; ++n4) {
                final int n5 = this.anIntArray4935[n4] * n2 + this.anIntArray4906[n4] * n3 >> 1158693806;
                this.anIntArray4935[n4] = n3 * this.anIntArray4935[n4] + -(this.anIntArray4906[n4] * n2) >> 1012277390;
                this.anIntArray4906[n4] = n5;
            }
            this.method2410(-72);
            this.aBoolean4922 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.a(" + n + ')');
        }
    }
    
    @Override
    final boolean method2324() {
        try {
            if (this.aShortArray4954 == null) {
                return true;
            }
            for (int i = 0; i < this.aShortArray4954.length; ++i) {
                if (~this.aShortArray4954[i] != 0x0 && !this.aHa_Sub3_4899.aD938.method8(-121, this.aShortArray4954[i])) {
                    return false;
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.J()");
        }
    }
    
    @Override
    final boolean method2339(final int n, final int n2, final Class111 class111, final boolean b, final int n3) {
        try {
            return this.method2394(68, n, -1, n2, b, n3, class111);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.CB(" + n + ',' + n2 + ',' + ((class111 != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ')');
        }
    }
    
    static final void method2405(final Class293 aClass293_3208, final byte b, final int anInt1405, final int anInt1406) {
        try {
            if (Class255.aClass293_3208 == null && !Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 && aClass293_3208 != null && Class365.method3939(4456, aClass293_3208) != null) {
                if (b < 11) {
                    Class146_Sub3.aClass213_4949 = null;
                }
                Class255.aClass293_3208 = aClass293_3208;
                Class189.aClass293_1457 = Class365.method3939(4456, aClass293_3208);
                PlayerUpdateMask.anInt526 = anInt1406;
                Class15.aBoolean186 = false;
                Class105.anInt3417 = 0;
                Class178.anInt1405 = anInt1405;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.OA(" + ((aClass293_3208 != null) ? "{...}" : "null") + ',' + b + ',' + anInt1405 + ',' + anInt1406 + ')');
        }
    }
    
    private final void method2406(final int n) {
        try {
            if (this.aClass18_4931 != null) {
                this.aClass18_4931.aBoolean207 = false;
            }
            if (n != -14204) {
                this.P(-41, 55, -103, 64);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.Q(" + n + ')');
        }
    }
    
    private final void method2407(final int n) {
        try {
            if (~this.anInt4909 != -1) {
                if (this.method2403((byte)(-127)) && this.method2399(n ^ 0x71F3)) {
                    this.aHa_Sub3_4899.method1971(0, true, this.aClass189_4933.anInterface2_Impl1_1456);
                    this.aHa_Sub3_4899.method1971(1, true, this.aClass189_4920.anInterface2_Impl1_1456);
                    this.aHa_Sub3_4899.method1971(2, true, this.aClass189_4930.anInterface2_Impl1_1456);
                    boolean b;
                    if (~(0x37 & this.anInt4963) == -1) {
                        this.aHa_Sub3_4899.method1979(false, -90);
                        b = false;
                        this.aHa_Sub3_4899.method2042(this.aHa_Sub3_4899.aClass256_4668, (byte)57);
                    }
                    else {
                        b = true;
                        this.aHa_Sub3_4899.method1979(true, -127);
                        this.aHa_Sub3_4899.method1971(3, true, this.aClass189_4927.anInterface2_Impl1_1456);
                        this.aHa_Sub3_4899.method2042(this.aHa_Sub3_4899.aClass256_4654, (byte)78);
                    }
                    for (int i = 0; i < this.anIntArray4945.length; ++i) {
                        final int n2 = this.anIntArray4907[i];
                        final int n3 = this.anIntArray4907[1 + i];
                        int n4 = this.aShortArray4954[n2] & 0xFFFF;
                        if (~n4 == 0xFFFF0000) {
                            n4 = -1;
                        }
                        this.aHa_Sub3_4899.method2039(true, 0, n4, b);
                        this.aHa_Sub3_4899.method1973(Class336.aClass232_2822, this.anIntArray4918[i], 3 * n2, 26810, this.aClass18_4931.anInterface2_Impl2_211, this.anIntArray4945[i], n3 - n2);
                    }
                }
                if (n != 29084) {
                    Class146_Sub3.aBoolean4926 = false;
                }
                this.method2396(8);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.B(" + n + ')');
        }
    }
    
    Class146_Sub3(final ha_Sub3 ha_Sub3, final Class178 class178, final int n, final int n2, final int n3, final int n4) {
        this(ha_Sub3, n, n4, true, false);
        try {
            final d ad938 = ha_Sub3.aD938;
            final int[] array = new int[class178.anInt1391];
            this.anIntArray4932 = new int[class178.anInt1406 + 1];
            for (int i = 0; i < class178.anInt1391; ++i) {
                if (class178.aByteArray1414 == null || ~class178.aByteArray1414[i] != 0xFFFFFFFD) {
                    if (class178.aShortArray1409 != null && class178.aShortArray1409[i] != -1) {
                        final Class238 method11 = ad938.method11(0xFFFF & class178.aShortArray1409[i], -28755);
                        if ((~(0x40 & this.anInt4963) == -1 || !method11.aBoolean1825) && method11.aBoolean1833) {
                            continue;
                        }
                    }
                    array[this.anInt4948++] = i;
                    final int[] anIntArray4932 = this.anIntArray4932;
                    final short n5 = class178.aShortArray1393[i];
                    ++anIntArray4932[n5];
                    final int[] anIntArray4933 = this.anIntArray4932;
                    final short n6 = class178.aShortArray1410[i];
                    ++anIntArray4933[n6];
                    final int[] anIntArray4934 = this.anIntArray4932;
                    final short n7 = class178.aShortArray1392[i];
                    ++anIntArray4934[n7];
                }
            }
            this.anInt4909 = this.anInt4948;
            final long[] array2 = new long[this.anInt4948];
            final boolean b = ~(this.anInt4942 & 0x100) != -1;
            for (int n8 = 0; this.anInt4948 > n8; ++n8) {
                final int n9 = array[n8];
                Class238 method12 = null;
                int n10 = 0;
                final int n11 = 0;
                int aByte1820 = 0;
                byte aByte1821 = 0;
                if (class178.aClass106Array1419 != null) {
                    boolean b2 = false;
                    for (int n12 = 0; ~n12 > ~class178.aClass106Array1419.length; ++n12) {
                        final Class106 class179 = class178.aClass106Array1419[n12];
                        if (~n9 == ~class179.anInt906) {
                            final Class177 method13 = Class67.method689(class179.anInt905, (byte)(-116));
                            if (method13.aBoolean1377) {
                                b2 = true;
                            }
                            if (~method13.anInt1373 != 0x0 && ~ad938.method11(method13.anInt1373, -28755).anInt1818 == 0xFFFFFFFD) {
                                this.aBoolean4938 = true;
                            }
                        }
                    }
                    if (b2) {
                        array2[n8] = Long.MAX_VALUE;
                        --this.anInt4909;
                        continue;
                    }
                }
                int n13 = -1;
                if (class178.aShortArray1409 != null) {
                    n13 = class178.aShortArray1409[n9];
                    if (~n13 != 0x0) {
                        method12 = ad938.method11(n13 & 0xFFFF, -28755);
                        if (~(0x40 & this.anInt4963) == -1 || !method12.aBoolean1825) {
                            aByte1820 = method12.aByte1820;
                            aByte1821 = method12.aByte1816;
                        }
                        else {
                            method12 = null;
                            n13 = -1;
                        }
                    }
                }
                final boolean b3 = (class178.aByteArray1411 != null && ~class178.aByteArray1411[n9] != -1) || (method12 != null && ~method12.anInt1818 != -1);
                if ((b || b3) && class178.aByteArray1402 != null) {
                    n10 += class178.aByteArray1402[n9] << 154322641;
                }
                if (b3) {
                    n10 += 65536;
                }
                array2[n8] = n11 + (0xFFFF0000 & n13 << -1322021104) + (n8 & 0xFFFF) + (n10 + (0xFF00 & aByte1820 << 294684808) + (aByte1821 & 0xFF) << 2126130144);
                this.aBoolean4938 |= b3;
                this.aBoolean4940 |= (method12 != null && (~method12.aByte1823 != -1 || ~method12.aByte1837 != -1));
            }
            Class90.method882(array, array2, (byte)118);
            this.aShortArray4944 = class178.aShortArray1408;
            this.anIntArray4959 = class178.anIntArray1400;
            this.anInt4912 = class178.anInt1406;
            this.anIntArray4906 = class178.anIntArray1416;
            this.anIntArray4935 = class178.anIntArray1418;
            this.anInt4929 = class178.anInt1407;
            this.aClass35Array4919 = class178.aClass35Array1398;
            final Class251[] array3 = new Class251[this.anInt4912];
            this.aClass87Array4951 = class178.aClass87Array1413;
            if (class178.aClass106Array1419 != null) {
                this.anInt4911 = class178.aClass106Array1419.length;
                this.aClass181Array4913 = new Class181[this.anInt4911];
                this.aClass281Array4941 = new Class281[this.anInt4911];
                for (int n14 = 0; ~n14 > ~this.anInt4911; ++n14) {
                    final Class106 class180 = class178.aClass106Array1419[n14];
                    final Class177 method14 = Class67.method689(class180.anInt905, (byte)(-117));
                    int n15 = -1;
                    for (int n16 = 0; ~this.anInt4948 < ~n16; ++n16) {
                        if (~array[n16] == ~class180.anInt906) {
                            n15 = n16;
                            break;
                        }
                    }
                    if (n15 == -1) {
                        throw new RuntimeException();
                    }
                    final int n17 = (Class208.anIntArray1579[class178.aShortArray1415[class180.anInt906] & 0xFFFF] & 0xFFFFFF) | 255 + -((class178.aByteArray1411 != null) ? class178.aByteArray1411[class180.anInt906] : 0) << 2107284952;
                    this.aClass281Array4941[n14] = new Class281(n15, class178.aShortArray1393[class180.anInt906], class178.aShortArray1410[class180.anInt906], class178.aShortArray1392[class180.anInt906], method14.anInt1374, method14.anInt1380, method14.anInt1373, method14.anInt1384, method14.anInt1379, method14.aBoolean1377, method14.aBoolean1383, class180.anInt907);
                    this.aClass181Array4913[n14] = new Class181(n17);
                }
            }
            final int n18 = 3 * this.anInt4948;
            Class60.aLongArray475 = new long[n18];
            this.aShortArray4901 = new short[n18];
            this.aShortArray4960 = new short[n18];
            this.aShortArray4946 = new short[this.anInt4948];
            this.aShortArray4953 = new short[n18];
            this.aShortArray4904 = new short[n18];
            this.aShort4905 = (short)n3;
            this.aShortArray4915 = new short[this.anInt4948];
            this.aByteArray4928 = new byte[n18];
            this.aShortArray4947 = new short[this.anInt4948];
            this.aShortArray4961 = new short[this.anInt4948];
            this.aFloatArray4914 = new float[n18];
            if (class178.aShortArray1394 != null) {
                this.aShortArray4917 = new short[this.anInt4948];
            }
            this.aShortArray4957 = new short[n18];
            this.aFloatArray4955 = new float[n18];
            this.aShort4902 = (short)n2;
            this.aShortArray4962 = new short[n18];
            this.aShortArray4954 = new short[this.anInt4948];
            this.aByteArray4958 = new byte[this.anInt4948];
            int n19 = 0;
            for (int n20 = 0; ~n20 > ~class178.anInt1406; ++n20) {
                final int n21 = this.anIntArray4932[n20];
                this.anIntArray4932[n20] = n19;
                array3[n20] = new Class251();
                n19 += n21;
            }
            this.anIntArray4932[class178.anInt1406] = n19;
            final Class250 method15 = Class224_Sub2.method2836(array, true, class178, this.anInt4948);
            final Class378[] array4 = new Class378[class178.anInt1391];
            for (int n22 = 0; ~n22 > ~class178.anInt1391; ++n22) {
                final short n23 = class178.aShortArray1393[n22];
                final short n24 = class178.aShortArray1410[n22];
                final short n25 = class178.aShortArray1392[n22];
                final int n26 = this.anIntArray4906[n24] + -this.anIntArray4906[n23];
                final int n27 = this.anIntArray4959[n24] - this.anIntArray4959[n23];
                final int n28 = this.anIntArray4935[n24] - this.anIntArray4935[n23];
                final int n29 = -this.anIntArray4906[n23] + this.anIntArray4906[n25];
                final int n30 = this.anIntArray4959[n25] - this.anIntArray4959[n23];
                final int n31 = -this.anIntArray4935[n23] + this.anIntArray4935[n25];
                int n32;
                int n33;
                int n34;
                for (n32 = -(n28 * n30) + n27 * n31, n33 = n29 * n28 - n26 * n31, n34 = -(n29 * n27) + n26 * n30; ~n32 < -8193 || n33 > 8192 || ~n34 < -8193 || ~n32 > 8191 || n33 < -8192 || ~n34 > 8191; n33 >>= 1, n32 >>= 1, n34 >>= 1) {}
                int n35 = (int)Math.sqrt(n32 * n32 - (-(n33 * n33) - n34 * n34));
                if (~n35 >= -1) {
                    n35 = 1;
                }
                final int anInt3187 = n33 * 256 / n35;
                final int anInt3188 = n34 * 256 / n35;
                final int anInt3189 = 256 * n32 / n35;
                final byte b4 = (byte)((class178.aByteArray1414 != null) ? class178.aByteArray1414[n22] : 0);
                if (~b4 != -1) {
                    if (~b4 == 0xFFFFFFFE) {
                        final Class378[] array5 = array4;
                        final int n36 = n22;
                        final Class378 class181 = new Class378();
                        array5[n36] = class181;
                        final Class378 class182 = class181;
                        class182.anInt3191 = anInt3188;
                        class182.anInt3187 = anInt3187;
                        class182.anInt3188 = anInt3189;
                    }
                }
                else {
                    final Class251 class184;
                    final Class251 class183 = class184 = array3[n23];
                    ++class184.anInt1920;
                    final Class251 class185 = class183;
                    class185.anInt1918 += anInt3189;
                    final Class251 class186 = class183;
                    class186.anInt1917 += anInt3188;
                    final Class251 class187 = class183;
                    class187.anInt1919 += anInt3187;
                    final Class251 class189;
                    final Class251 class188 = class189 = array3[n24];
                    class189.anInt1919 += anInt3187;
                    final Class251 class190 = class188;
                    class190.anInt1918 += anInt3189;
                    final Class251 class191 = class188;
                    ++class191.anInt1920;
                    final Class251 class192 = class188;
                    class192.anInt1917 += anInt3188;
                    final Class251 class194;
                    final Class251 class193 = class194 = array3[n25];
                    class194.anInt1917 += anInt3188;
                    final Class251 class195 = class193;
                    class195.anInt1919 += anInt3187;
                    final Class251 class196 = class193;
                    class196.anInt1918 += anInt3189;
                    final Class251 class197 = class193;
                    ++class197.anInt1920;
                }
            }
            for (int n37 = 0; ~this.anInt4948 < ~n37; ++n37) {
                final int n38 = array[n37];
                final int n39 = class178.aShortArray1415[n38] & 0xFFFF;
                int n40;
                if (class178.aByteArray1420 != null) {
                    n40 = class178.aByteArray1420[n38];
                }
                else {
                    n40 = -1;
                }
                int n41;
                if (class178.aByteArray1411 != null) {
                    n41 = (class178.aByteArray1411[n38] & 0xFF);
                }
                else {
                    n41 = 0;
                }
                short n42 = (short)((class178.aShortArray1409 != null) ? class178.aShortArray1409[n38] : -1);
                if (~n42 != 0x0 && (this.anInt4963 & 0x40) != 0x0 && ad938.method11(n42 & 0xFFFF, -28755).aBoolean1825) {
                    n42 = -1;
                }
                float n43 = 0.0f;
                float n44 = 0.0f;
                float n45 = 0.0f;
                float n46 = 0.0f;
                float n47 = 0.0f;
                float n48 = 0.0f;
                int n49 = 0;
                int n50 = 0;
                int method16 = 0;
                if (n42 != -1) {
                    if (~n40 != 0x0) {
                        n40 &= 0xFF;
                        final byte b5 = class178.aByteArray1388[n40];
                        if (b5 != 0) {
                            final short n51 = class178.aShortArray1393[n38];
                            final short n52 = class178.aShortArray1410[n38];
                            final short n53 = class178.aShortArray1392[n38];
                            final int n54 = method15.anIntArray1911[n40];
                            final int n55 = method15.anIntArray1915[n40];
                            final int n56 = method15.anIntArray1912[n40];
                            final float[] array6 = method15.aFloatArrayArray1910[n40];
                            final byte b6 = class178.aByteArray1399[n40];
                            final float n57 = class178.anIntArray1412[n40] / 256.0f;
                            if (b5 != 1) {
                                if (b5 == 2) {
                                    final float n58 = class178.anIntArray1397[n40] / 256.0f;
                                    final float n59 = class178.anIntArray1386[n40] / 256.0f;
                                    final int n60 = -class178.anIntArray1416[n51] + class178.anIntArray1416[n52];
                                    final int n61 = -class178.anIntArray1400[n51] + class178.anIntArray1400[n52];
                                    final int n62 = -class178.anIntArray1418[n51] + class178.anIntArray1418[n52];
                                    final int n63 = -class178.anIntArray1416[n51] + class178.anIntArray1416[n53];
                                    final int n64 = class178.anIntArray1400[n53] + -class178.anIntArray1400[n51];
                                    final int n65 = -class178.anIntArray1418[n51] + class178.anIntArray1418[n53];
                                    final int n66 = -(n62 * n64) + n61 * n65;
                                    final int n67 = -(n60 * n65) + n62 * n63;
                                    final int n68 = n64 * n60 - n63 * n61;
                                    method16 = Class69.method696((n67 * array6[1] + array6[0] * n66 + array6[2] * n68) / (64.0f / class178.anIntArray1389[n40]), (array6[6] * n66 + n67 * array6[7] + array6[8] * n68) / (64.0f / class178.anIntArray1390[n40]), (byte)(-77), (array6[4] * n67 + n66 * array6[3] + array6[5] * n68) / (64.0f / class178.anIntArray1404[n40]));
                                    Class48_Sub1_Sub1.method461(class178.anIntArray1400[n51], n59, b6, n55, n57, 127, n58, array6, method16, Class76_Sub9.aFloatArray3784, n56, class178.anIntArray1418[n51], n54, class178.anIntArray1416[n51]);
                                    n44 = Class76_Sub9.aFloatArray3784[1];
                                    n43 = Class76_Sub9.aFloatArray3784[0];
                                    Class48_Sub1_Sub1.method461(class178.anIntArray1400[n52], n59, b6, n55, n57, 125, n58, array6, method16, Class76_Sub9.aFloatArray3784, n56, class178.anIntArray1418[n52], n54, class178.anIntArray1416[n52]);
                                    n45 = Class76_Sub9.aFloatArray3784[0];
                                    n46 = Class76_Sub9.aFloatArray3784[1];
                                    Class48_Sub1_Sub1.method461(class178.anIntArray1400[n53], n59, b6, n55, n57, 122, n58, array6, method16, Class76_Sub9.aFloatArray3784, n56, class178.anIntArray1418[n53], n54, class178.anIntArray1416[n53]);
                                    n47 = Class76_Sub9.aFloatArray3784[0];
                                    n48 = Class76_Sub9.aFloatArray3784[1];
                                }
                                else if (~b5 == 0xFFFFFFFC) {
                                    Class243.method2943(Class76_Sub9.aFloatArray3784, class178.anIntArray1400[n51], b6, n56, array6, class178.anIntArray1418[n51], n55, n57, class178.anIntArray1416[n51], n54, 113);
                                    n44 = Class76_Sub9.aFloatArray3784[1];
                                    n43 = Class76_Sub9.aFloatArray3784[0];
                                    Class243.method2943(Class76_Sub9.aFloatArray3784, class178.anIntArray1400[n52], b6, n56, array6, class178.anIntArray1418[n52], n55, n57, class178.anIntArray1416[n52], n54, 103);
                                    n46 = Class76_Sub9.aFloatArray3784[1];
                                    n45 = Class76_Sub9.aFloatArray3784[0];
                                    Class243.method2943(Class76_Sub9.aFloatArray3784, class178.anIntArray1400[n53], b6, n56, array6, class178.anIntArray1418[n53], n55, n57, class178.anIntArray1416[n53], n54, 103);
                                    n48 = Class76_Sub9.aFloatArray3784[1];
                                    n47 = Class76_Sub9.aFloatArray3784[0];
                                    if (~(b6 & 0x1) == -1) {
                                        if (n47 - n43 <= 0.5f) {
                                            if (n43 - n47 > 0.5f) {
                                                n50 = 2;
                                                ++n47;
                                            }
                                        }
                                        else {
                                            n50 = 1;
                                            --n47;
                                        }
                                        if (n45 - n43 <= 0.5f) {
                                            if (n43 - n45 > 0.5f) {
                                                n49 = 2;
                                                ++n45;
                                            }
                                        }
                                        else {
                                            --n45;
                                            n49 = 1;
                                        }
                                    }
                                    else {
                                        if (-n44 + n48 <= 0.5f) {
                                            if (-n48 + n44 > 0.5f) {
                                                ++n48;
                                                n50 = 2;
                                            }
                                        }
                                        else {
                                            n50 = 1;
                                            --n48;
                                        }
                                        if (n46 - n44 <= 0.5f) {
                                            if (-n46 + n44 > 0.5f) {
                                                n49 = 2;
                                                ++n46;
                                            }
                                        }
                                        else {
                                            n49 = 1;
                                            --n46;
                                        }
                                    }
                                }
                            }
                            else {
                                final float n69 = class178.anIntArray1390[n40] / 1024.0f;
                                Class98_Sub37.method1460(class178.anIntArray1416[n51], n56, 8, class178.anIntArray1418[n51], n55, class178.anIntArray1400[n51], array6, n69, b6, n54, n57, Class76_Sub9.aFloatArray3784);
                                n44 = Class76_Sub9.aFloatArray3784[1];
                                n43 = Class76_Sub9.aFloatArray3784[0];
                                Class98_Sub37.method1460(class178.anIntArray1416[n52], n56, 8, class178.anIntArray1418[n52], n55, class178.anIntArray1400[n52], array6, n69, b6, n54, n57, Class76_Sub9.aFloatArray3784);
                                n46 = Class76_Sub9.aFloatArray3784[1];
                                n45 = Class76_Sub9.aFloatArray3784[0];
                                Class98_Sub37.method1460(class178.anIntArray1416[n53], n56, 8, class178.anIntArray1418[n53], n55, class178.anIntArray1400[n53], array6, n69, b6, n54, n57, Class76_Sub9.aFloatArray3784);
                                n47 = Class76_Sub9.aFloatArray3784[0];
                                n48 = Class76_Sub9.aFloatArray3784[1];
                                final float n70 = n69 / 2.0f;
                                if ((b6 & 0x1) == 0x0) {
                                    if (-n43 + n45 > n70) {
                                        n49 = 1;
                                        n45 -= n69;
                                    }
                                    else if (n70 < n43 - n45) {
                                        n49 = 2;
                                        n45 += n69;
                                    }
                                    if (n70 < -n43 + n47) {
                                        n50 = 1;
                                        n47 -= n69;
                                    }
                                    else if (n70 < n43 - n47) {
                                        n47 += n69;
                                        n50 = 2;
                                    }
                                }
                                else {
                                    if (n70 >= -n44 + n48) {
                                        if (n70 < n44 - n48) {
                                            n50 = 2;
                                            n48 += n69;
                                        }
                                    }
                                    else {
                                        n48 -= n69;
                                        n50 = 1;
                                    }
                                    if (n70 >= n46 - n44) {
                                        if (n70 < n44 - n46) {
                                            n46 += n69;
                                            n49 = 2;
                                        }
                                    }
                                    else {
                                        n46 -= n69;
                                        n49 = 1;
                                    }
                                }
                            }
                        }
                        else {
                            final short n71 = class178.aShortArray1393[n38];
                            final short n72 = class178.aShortArray1410[n38];
                            final short n73 = class178.aShortArray1392[n38];
                            final short n74 = class178.aShortArray1403[n40];
                            final short n75 = class178.aShortArray1421[n40];
                            final short n76 = class178.aShortArray1385[n40];
                            final float n77 = class178.anIntArray1416[n74];
                            final float n78 = class178.anIntArray1400[n74];
                            final float n79 = class178.anIntArray1418[n74];
                            final float n80 = class178.anIntArray1416[n75] - n77;
                            final float n81 = -n78 + class178.anIntArray1400[n75];
                            final float n82 = -n79 + class178.anIntArray1418[n75];
                            final float n83 = -n77 + class178.anIntArray1416[n76];
                            final float n84 = class178.anIntArray1400[n76] - n78;
                            final float n85 = -n79 + class178.anIntArray1418[n76];
                            final float n86 = class178.anIntArray1416[n71] - n77;
                            final float n87 = class178.anIntArray1400[n71] - n78;
                            final float n88 = -n79 + class178.anIntArray1418[n71];
                            final float n89 = class178.anIntArray1416[n72] - n77;
                            final float n90 = -n78 + class178.anIntArray1400[n72];
                            final float n91 = -n79 + class178.anIntArray1418[n72];
                            final float n92 = class178.anIntArray1416[n73] - n77;
                            final float n93 = class178.anIntArray1400[n73] - n78;
                            final float n94 = class178.anIntArray1418[n73] - n79;
                            final float n95 = n81 * n85 - n84 * n82;
                            final float n96 = -(n80 * n85) + n82 * n83;
                            final float n97 = n80 * n84 - n81 * n83;
                            final float n98 = n84 * n97 - n96 * n85;
                            final float n99 = n85 * n95 - n97 * n83;
                            final float n100 = n96 * n83 - n84 * n95;
                            final float n101 = 1.0f / (n100 * n82 + (n80 * n98 + n81 * n99));
                            n47 = (n94 * n100 + (n93 * n99 + n92 * n98)) * n101;
                            n45 = (n98 * n89 + n90 * n99 + n100 * n91) * n101;
                            n43 = n101 * (n87 * n99 + n86 * n98 + n88 * n100);
                            final float n102 = -(n81 * n95) + n96 * n80;
                            final float n103 = n81 * n97 - n96 * n82;
                            final float n104 = -(n80 * n97) + n82 * n95;
                            final float n105 = 1.0f / (n84 * n104 + n103 * n83 + n102 * n85);
                            n46 = (n104 * n90 + n103 * n89 + n91 * n102) * n105;
                            n44 = (n88 * n102 + (n87 * n104 + n103 * n86)) * n105;
                            n48 = (n104 * n93 + n92 * n103 + n102 * n94) * n105;
                        }
                    }
                    else {
                        n45 = 1.0f;
                        n48 = 0.0f;
                        n43 = 0.0f;
                        n46 = 1.0f;
                        n47 = 0.0f;
                        n44 = 1.0f;
                        n49 = 1;
                        n50 = 2;
                    }
                }
                byte b7;
                if (class178.aByteArray1414 == null) {
                    b7 = 0;
                }
                else {
                    b7 = class178.aByteArray1414[n38];
                }
                if (~b7 != -1) {
                    if (b7 == 1) {
                        final Class378 class198 = array4[n38];
                        final long n106 = (n41 + (method16 << 655766264) + (n39 << -1296898200) << 279144352) + ((class198.anInt3191 + 256 << 1427014102) + (n40 << 1210213378) + (((~class198.anInt3188 >= -1) ? 2048 : 1024) + (class198.anInt3187 + 256 << 643845708)));
                        this.aShortArray4947[n37] = this.method2409(n43, n37, n106, class198.anInt3188, class178.aShortArray1393[n38], class198.anInt3187, class198.anInt3191, class178, 0, 1, n44);
                        this.aShortArray4915[n37] = this.method2409(n45, n37, n49 + n106, class198.anInt3188, class178.aShortArray1410[n38], class198.anInt3187, class198.anInt3191, class178, 0, 1, n46);
                        this.aShortArray4961[n37] = this.method2409(n47, n37, n106 + n50, class198.anInt3188, class178.aShortArray1392[n38], class198.anInt3187, class198.anInt3191, class178, 0, 1, n48);
                    }
                }
                else {
                    final long n107 = (n40 << 1829690562) + ((method16 << -292347432) - (-(n39 << -747595384) - n41) << -365996704);
                    final short n108 = class178.aShortArray1393[n38];
                    final short n109 = class178.aShortArray1410[n38];
                    final short n110 = class178.aShortArray1392[n38];
                    final Class251 class199 = array3[n108];
                    this.aShortArray4947[n37] = this.method2409(n43, n37, n107, class199.anInt1918, n108, class199.anInt1919, class199.anInt1917, class178, class199.anInt1920, 1, n44);
                    final Class251 class200 = array3[n109];
                    this.aShortArray4915[n37] = this.method2409(n45, n37, n107 + n49, class200.anInt1918, n109, class200.anInt1919, class200.anInt1917, class178, class200.anInt1920, 1, n46);
                    final Class251 class201 = array3[n110];
                    this.aShortArray4961[n37] = this.method2409(n47, n37, n50 + n107, class201.anInt1918, n110, class201.anInt1919, class201.anInt1917, class178, class201.anInt1920, 1, n48);
                }
                if (class178.aByteArray1411 != null) {
                    this.aByteArray4958[n37] = class178.aByteArray1411[n38];
                }
                if (class178.aShortArray1394 != null) {
                    this.aShortArray4917[n37] = class178.aShortArray1394[n38];
                }
                this.aShortArray4946[n37] = class178.aShortArray1415[n38];
                this.aShortArray4954[n37] = n42;
            }
            if (~this.anInt4909 < -1) {
                int n111 = 1;
                short n112 = this.aShortArray4954[0];
                for (int n113 = 0; this.anInt4909 > n113; ++n113) {
                    final short n114 = this.aShortArray4954[n113];
                    if (n114 != n112) {
                        n112 = n114;
                        ++n111;
                    }
                }
                this.anIntArray4945 = new int[n111];
                this.anIntArray4918 = new int[n111];
                (this.anIntArray4907 = new int[n111 + 1])[0] = 0;
                int n115 = this.anInt4936;
                short n116 = 0;
                short n117 = this.aShortArray4954[0];
                int n118 = 0;
                for (int j = 0; j < this.anInt4909; ++j) {
                    final short n119 = this.aShortArray4954[j];
                    if (n117 != n119) {
                        this.anIntArray4945[n118] = n115;
                        this.anIntArray4918[n118] = n116 - n115 + 1;
                        this.anIntArray4907[++n118] = j;
                        n117 = n119;
                        n115 = this.anInt4936;
                        n116 = 0;
                    }
                    final short n120 = this.aShortArray4947[j];
                    if (~n116 > ~n120) {
                        n116 = n120;
                    }
                    if (~n115 < ~n120) {
                        n115 = n120;
                    }
                    final short n121 = this.aShortArray4915[j];
                    if (n121 > n116) {
                        n116 = n121;
                    }
                    if (~n115 < ~n121) {
                        n115 = n121;
                    }
                    final short n122 = this.aShortArray4961[j];
                    if (n122 < n115) {
                        n115 = n122;
                    }
                    if (n122 > n116) {
                        n116 = n122;
                    }
                }
                this.anIntArray4945[n118] = n115;
                this.anIntArray4918[n118] = 1 + n116 - n115;
                this.anIntArray4907[++n118] = this.anInt4909;
            }
            Class60.aLongArray475 = null;
            this.aShortArray4901 = Class223.method2829(this.aShortArray4901, this.anInt4936, 26813);
            this.aShortArray4904 = Class223.method2829(this.aShortArray4904, this.anInt4936, 26813);
            this.aShortArray4953 = Class223.method2829(this.aShortArray4953, this.anInt4936, 26813);
            this.aShortArray4957 = Class223.method2829(this.aShortArray4957, this.anInt4936, 26813);
            this.aShortArray4962 = Class223.method2829(this.aShortArray4962, this.anInt4936, 26813);
            this.aByteArray4928 = Class69_Sub2.method705(this.anInt4936, 81, this.aByteArray4928);
            this.aFloatArray4955 = Class246_Sub3_Sub3_Sub2.method3021(-65537, this.anInt4936, this.aFloatArray4955);
            this.aFloatArray4914 = Class246_Sub3_Sub3_Sub2.method3021(-65537, this.anInt4936, this.aFloatArray4914);
            if (class178.anIntArray1417 != null && Class98_Sub10_Sub29.method1092(n, this.anInt4963, (byte)(-116))) {
                this.anIntArrayArray4925 = class178.method2595(31, false);
            }
            if (class178.aClass106Array1419 != null && Class2.method169(false, n, this.anInt4963)) {
                this.anIntArrayArray4934 = class178.method2596(21517);
            }
            if (class178.anIntArray1395 != null && Class128.method2223(this.anInt4963, (byte)(-67), n)) {
                int n123 = 0;
                final int[] array7 = new int[256];
                for (int n124 = 0; ~n124 > ~this.anInt4948; ++n124) {
                    final int n125 = class178.anIntArray1395[array[n124]];
                    if (n125 >= 0) {
                        final int[] array8 = array7;
                        final int n126 = n125;
                        ++array8[n126];
                        if (n123 < n125) {
                            n123 = n125;
                        }
                    }
                }
                this.anIntArrayArray4903 = new int[1 + n123][];
                for (int n127 = 0; ~n127 >= ~n123; ++n127) {
                    this.anIntArrayArray4903[n127] = new int[array7[n127]];
                    array7[n127] = 0;
                }
                for (int n128 = 0; ~this.anInt4948 < ~n128; ++n128) {
                    final int n129 = class178.anIntArray1395[array[n128]];
                    if (~n129 <= -1) {
                        this.anIntArrayArray4903[n129][array7[n129]++] = n128;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.<init>(" + ((ha_Sub3 != null) ? "{...}" : "null") + ',' + ((class178 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final boolean method2408(final byte b) {
        try {
            return ~Class177.anInt1376 <= -2 && b == 57;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.DA(" + b + ')');
        }
    }
    
    @Override
    final int V() {
        try {
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            return this.anInt4924;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.V()");
        }
    }
    
    @Override
    final void method2327() {
    }
    
    private final short method2409(final float n, final int n2, final long n3, final int n4, final int n5, final int n6, final int n7, final Class178 class178, final int n8, final int n9, final float n10) {
        try {
            final int n11 = this.anIntArray4932[n5];
            final int n12 = this.anIntArray4932[n9 + n5];
            int n13 = 0;
            for (int n14 = n11; ~n14 > ~n12; ++n14) {
                final short n15 = this.aShortArray4960[n14];
                if (n15 == 0) {
                    n13 = n14;
                    break;
                }
                if (Class60.aLongArray475[n14] == n3) {
                    return (short)(n15 - 1);
                }
            }
            this.aShortArray4960[n13] = (short)(1 + this.anInt4936);
            Class60.aLongArray475[n13] = n3;
            this.aShortArray4904[this.anInt4936] = (short)n2;
            this.aShortArray4901[this.anInt4936] = (short)n5;
            this.aShortArray4953[this.anInt4936] = (short)n4;
            this.aShortArray4957[this.anInt4936] = (short)n6;
            this.aShortArray4962[this.anInt4936] = (short)n7;
            this.aByteArray4928[this.anInt4936] = (byte)n8;
            this.aFloatArray4955[this.anInt4936] = n;
            this.aFloatArray4914[this.anInt4936] = n10;
            return (short)(this.anInt4936++);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.PA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((class178 != null) ? "{...}" : "null") + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    @Override
    final boolean method2333(final int n, final int n2, final Class111 class111, final boolean b, final int n3, final int n4) {
        try {
            return this.method2394(-121, n, n4, n2, b, n3, class111);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.KB(" + n + ',' + n2 + ',' + ((class111 != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    private final void method2410(final int n) {
        try {
            if (this.aClass189_4933 != null) {
                this.aClass189_4933.aBoolean1458 = false;
            }
            if (n > -51) {
                this.ua();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.A(" + n + ')');
        }
    }
    
    Class146_Sub3(final ha_Sub3 aHa_Sub3_4899, final int anInt4942, final int anInt4943, final boolean b, final boolean aBoolean4916) {
        this.anInt4909 = 0;
        this.aBoolean4922 = false;
        this.anInt4912 = 0;
        this.anInt4929 = 0;
        this.aBoolean4916 = false;
        this.aBoolean4908 = true;
        this.anInt4948 = 0;
        this.anInt4936 = 0;
        this.aBoolean4940 = false;
        this.aBoolean4938 = false;
        try {
            this.aBoolean4916 = aBoolean4916;
            this.anInt4963 = anInt4943;
            this.anInt4942 = anInt4942;
            this.aHa_Sub3_4899 = aHa_Sub3_4899;
            if (b || Class93.method901(this.anInt4963, this.anInt4942, -125)) {
                this.aClass189_4933 = new Class189(Class238.method2919(-84, this.anInt4942, this.anInt4963));
            }
            if (b || Class5.method176(24578, this.anInt4942, this.anInt4963)) {
                this.aClass189_4930 = new Class189(r_Sub2.method1655(this.anInt4942, (byte)(-121), this.anInt4963));
            }
            if (b || Class53_Sub1.method502(this.anInt4963, this.anInt4942, (byte)115)) {
                this.aClass189_4920 = new Class189(Class98_Sub27.method1292(this.anInt4963, (byte)114, this.anInt4942));
            }
            if (b || Class98_Sub10_Sub1.method1005(this.anInt4942, this.anInt4963, (byte)(-23))) {
                this.aClass189_4927 = new Class189(Class21_Sub3.method276(this.anInt4942, 15123, this.anInt4963));
            }
            if (b || Class140.method2287(this.anInt4942, this.anInt4963, 2048)) {
                this.aClass18_4931 = new Class18(Class276.method3286((byte)127, this.anInt4942, this.anInt4963));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.<init>(" + ((aHa_Sub3_4899 != null) ? "{...}" : "null") + ',' + anInt4942 + ',' + anInt4943 + ',' + b + ',' + aBoolean4916 + ')');
        }
    }
    
    @Override
    final void method2326() {
        try {
            if (this.anInt4936 > 0 && this.anInt4909 > 0) {
                this.method2403((byte)(-127));
                this.method2399(103);
                this.method2396(8);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.M()");
        }
    }
    
    @Override
    final int HA() {
        try {
            if (!this.aBoolean4922) {
                this.method2401(-21065);
            }
            return this.anInt4921;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.HA()");
        }
    }
    
    @Override
    final void k(final int n) {
        try {
            final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
            final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
            for (int n4 = 0; this.anInt4912 > n4; ++n4) {
                final int n5 = this.anIntArray4906[n4] * n3 + this.anIntArray4935[n4] * n2 >> -1407988562;
                this.anIntArray4935[n4] = -(n2 * this.anIntArray4906[n4]) + n3 * this.anIntArray4935[n4] >> 818672142;
                this.anIntArray4906[n4] = n5;
            }
            for (int n6 = 0; this.anInt4936 > n6; ++n6) {
                final int n7 = this.aShortArray4962[n6] * n2 - -(n3 * this.aShortArray4953[n6]) >> 1571333358;
                this.aShortArray4962[n6] = (short)(n3 * this.aShortArray4962[n6] + -(n2 * this.aShortArray4953[n6]) >> -715830066);
                this.aShortArray4953[n6] = (short)n7;
            }
            this.method2410(-81);
            this.method2404((byte)(-64));
            this.aBoolean4922 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.k(" + n + ')');
        }
    }
    
    @Override
    final void method2343(final Class111 class111) {
        try {
            final Class111_Sub3 class111_Sub3 = (Class111_Sub3)class111;
            if (this.aClass87Array4951 != null) {
                for (int n = 0; ~this.aClass87Array4951.length < ~n; ++n) {
                    Class87 aClass87_657;
                    final Class87 class112 = aClass87_657 = this.aClass87Array4951[n];
                    if (class112.aClass87_657 != null) {
                        aClass87_657 = class112.aClass87_657;
                    }
                    aClass87_657.anInt670 = (int)(this.anIntArray4959[class112.anInt666] * class111_Sub3.aFloat4711 + class111_Sub3.aFloat4712 * this.anIntArray4906[class112.anInt666] + this.anIntArray4935[class112.anInt666] * class111_Sub3.aFloat4705 + class111_Sub3.aFloat4702);
                    aClass87_657.anInt668 = (int)(this.anIntArray4959[class112.anInt666] * class111_Sub3.aFloat4706 + this.anIntArray4906[class112.anInt666] * class111_Sub3.aFloat4714 + this.anIntArray4935[class112.anInt666] * class111_Sub3.aFloat4710 + class111_Sub3.aFloat4709);
                    aClass87_657.anInt671 = (int)(this.anIntArray4935[class112.anInt666] * class111_Sub3.aFloat4704 + (class111_Sub3.aFloat4713 * this.anIntArray4959[class112.anInt666] + class111_Sub3.aFloat4708 * this.anIntArray4906[class112.anInt666]) + class111_Sub3.aFloat4703);
                    aClass87_657.anInt663 = (int)(class111_Sub3.aFloat4702 + (class111_Sub3.aFloat4711 * this.anIntArray4959[class112.anInt661] + class111_Sub3.aFloat4712 * this.anIntArray4906[class112.anInt661] + class111_Sub3.aFloat4705 * this.anIntArray4935[class112.anInt661]));
                    aClass87_657.anInt664 = (int)(class111_Sub3.aFloat4709 + (class111_Sub3.aFloat4710 * this.anIntArray4935[class112.anInt661] + (class111_Sub3.aFloat4714 * this.anIntArray4906[class112.anInt661] + this.anIntArray4959[class112.anInt661] * class111_Sub3.aFloat4706)));
                    aClass87_657.anInt656 = (int)(class111_Sub3.aFloat4708 * this.anIntArray4906[class112.anInt661] + class111_Sub3.aFloat4713 * this.anIntArray4959[class112.anInt661] + class111_Sub3.aFloat4704 * this.anIntArray4935[class112.anInt661] + class111_Sub3.aFloat4703);
                    aClass87_657.anInt659 = (int)(this.anIntArray4935[class112.anInt674] * class111_Sub3.aFloat4705 + (class111_Sub3.aFloat4711 * this.anIntArray4959[class112.anInt674] + this.anIntArray4906[class112.anInt674] * class111_Sub3.aFloat4712) + class111_Sub3.aFloat4702);
                    aClass87_657.anInt669 = (int)(class111_Sub3.aFloat4709 + (class111_Sub3.aFloat4706 * this.anIntArray4959[class112.anInt674] + this.anIntArray4906[class112.anInt674] * class111_Sub3.aFloat4714 + class111_Sub3.aFloat4710 * this.anIntArray4935[class112.anInt674]));
                    aClass87_657.anInt662 = (int)(class111_Sub3.aFloat4703 + (class111_Sub3.aFloat4713 * this.anIntArray4959[class112.anInt674] + this.anIntArray4906[class112.anInt674] * class111_Sub3.aFloat4708 + class111_Sub3.aFloat4704 * this.anIntArray4935[class112.anInt674]));
                }
            }
            if (this.aClass35Array4919 != null) {
                for (int n2 = 0; ~this.aClass35Array4919.length < ~n2; ++n2) {
                    Class35 aClass35_328;
                    final Class35 class113 = aClass35_328 = this.aClass35Array4919[n2];
                    if (class113.aClass35_328 != null) {
                        aClass35_328 = class113.aClass35_328;
                    }
                    if (class113.aClass111_334 == null) {
                        class113.aClass111_334 = class111_Sub3.method2102();
                    }
                    else {
                        class113.aClass111_334.method2092(class111_Sub3);
                    }
                    aClass35_328.anInt331 = (int)(class111_Sub3.aFloat4702 + (class111_Sub3.aFloat4705 * this.anIntArray4935[class113.anInt327] + (class111_Sub3.aFloat4712 * this.anIntArray4906[class113.anInt327] + this.anIntArray4959[class113.anInt327] * class111_Sub3.aFloat4711)));
                    aClass35_328.anInt330 = (int)(class111_Sub3.aFloat4709 + (this.anIntArray4935[class113.anInt327] * class111_Sub3.aFloat4710 + (this.anIntArray4906[class113.anInt327] * class111_Sub3.aFloat4714 + this.anIntArray4959[class113.anInt327] * class111_Sub3.aFloat4706)));
                    aClass35_328.anInt337 = (int)(class111_Sub3.aFloat4703 + (this.anIntArray4935[class113.anInt327] * class111_Sub3.aFloat4704 + (this.anIntArray4959[class113.anInt327] * class111_Sub3.aFloat4713 + this.anIntArray4906[class113.anInt327] * class111_Sub3.aFloat4708)));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qw.DB(" + ((class111 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class146_Sub3.aClass171_4900 = new OutgoingOpcode(52, 1);
        Class146_Sub3.aBoolean4926 = false;
    }
}
