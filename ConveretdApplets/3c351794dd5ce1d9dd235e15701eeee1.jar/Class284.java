import java.util.Random;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class284
{
    int anInt2159;
    private int anInt2160;
    private short[] aShortArray2161;
    static int anInt2162;
    private int anInt2163;
    private short[] aShortArray2164;
    private int anInt2165;
    private int anInt2166;
    static Class98_Sub4 aClass98_Sub4_2167;
    static Frame aFrame2168;
    
    abstract void method3354(final int p0);
    
    public static void method3355(final int n) {
        try {
            Class284.aFrame2168 = null;
            Class284.aClass98_Sub4_2167 = null;
            if (n != 15029) {
                method3359(-38);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rn.C(" + n + ')');
        }
    }
    
    abstract void method3356(final int p0, final int p1, final int p2);
    
    private final void method3357(final byte b) {
        try {
            final Random random = new Random(this.anInt2163);
            for (int i = 0; i < 255; ++i) {
                this.aShortArray2161[i] = (short)i;
            }
            int n = 0;
            if (b != 116) {
                this.method3358(-94);
            }
            while (~n > -256) {
                final int n2 = 255 - n;
                final int method546 = Class63.method546(-28737, n2, random);
                final short n3 = this.aShortArray2161[method546];
                this.aShortArray2161[method546] = this.aShortArray2161[n2];
                this.aShortArray2161[n2] = (this.aShortArray2161[n2 + 256] = n3);
                ++n;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rn.F(" + b + ')');
        }
    }
    
    abstract void method3358(final int p0);
    
    static final void method3359(final int n) {
        try {
            for (int i = 0; i < 5; ++i) {
                Class217.aBooleanArray3410[i] = false;
            }
            if (n != 9268) {
                Class284.aClass98_Sub4_2167 = null;
            }
            Class98_Sub4.anInt3828 = 0;
            Class98_Sub41.anInt4202 = 0;
            Class98_Sub46_Sub20_Sub2.anInt6319 = 1;
            Class368.anInt3128 = -1;
            Class116.anInt967 = (Class64_Sub26.anInt3712 = -1);
            Class53_Sub1.anInt3636 = -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rn.A(" + n + ')');
        }
    }
    
    private final void method3360(final int n) {
        try {
            this.aShortArray2164 = new short[this.anInt2159];
            if (n == 10567) {
                for (int i = 0; i < this.anInt2159; ++i) {
                    this.aShortArray2164[i] = (short)Math.pow(2.0, i);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rn.I(" + n + ')');
        }
    }
    
    final void method3361(final byte b, final int n, final int i, final int n2) {
        try {
            final int[] array = new int[n];
            final int[] array2 = new int[i];
            for (int n3 = 0; ~n < ~n3; ++n3) {
                array[n3] = (n3 << -1065786996) / n;
            }
            final int[] array3 = new int[n2];
            if (b >= 0) {
                Class284.aFrame2168 = null;
            }
            for (int j = 0; j < i; ++j) {
                array2[j] = (j << 786345612) / i;
            }
            for (int n4 = 0; ~n2 < ~n4; ++n4) {
                array3[n4] = (n4 << -258200020) / n2;
            }
            this.method3354(751943489);
            for (int k = 0; k < n2; ++k) {
                for (int n5 = 0; i > n5; ++n5) {
                    for (int l = 0; l < n; ++l) {
                        for (int n6 = 0; n6 < this.anInt2159; ++n6) {
                            final int n7 = this.aShortArray2164[n6] << 705054604;
                            final int n8 = n7 * this.anInt2165 >> 486926636;
                            final int n9 = n7 * this.anInt2160 >> 627216364;
                            final int n10 = this.anInt2166 * n7 >> 1211593132;
                            final int n11 = n7 * array3[k] >> 192686284;
                            final int n12 = array2[n5] * n7 >> -1320253108;
                            final int n13 = array[l] * n7 >> 865773868;
                            final int n14 = n11 * this.anInt2166;
                            final int n15 = n13 * this.anInt2165;
                            final int n16 = n12 * this.anInt2160;
                            final int n17 = n15 >> -2083454548;
                            final int n18 = 1 + n17;
                            final int n19 = n17 & 0xFF;
                            final int n20 = n16 >> -833753588;
                            final int n21 = 1 + n20;
                            final int n22 = n20 & 0xFF;
                            final int n23 = n14 >> -1617570836;
                            final int n24 = n23 + 1;
                            final int n25 = n23 & 0xFF;
                            final int n26 = n15 & 0xFFF;
                            int n27;
                            if (~n21 > ~n9) {
                                n27 = (n21 & 0xFF);
                            }
                            else {
                                n27 = 0;
                            }
                            int n28;
                            if (~n10 >= ~n24) {
                                n28 = 0;
                            }
                            else {
                                n28 = (n24 & 0xFF);
                            }
                            final int n29 = n14 & 0xFFF;
                            final int n30 = n16 & 0xFFF;
                            int n31;
                            if (~n8 >= ~n18) {
                                n31 = 0;
                            }
                            else {
                                n31 = (n18 & 0xFF);
                            }
                            final short n32 = this.aShortArray2161[n28];
                            final short n33 = this.aShortArray2161[n25];
                            final int n34 = Class151_Sub8.anIntArray5014[n29];
                            final int n35 = Class151_Sub8.anIntArray5014[n30];
                            final int n36 = -4096 + n26;
                            final int n37 = -4096 + n30;
                            final int n38 = n29 - 4096;
                            final int n39 = Class151_Sub8.anIntArray5014[n26];
                            final short n40 = this.aShortArray2161[n32 + n22];
                            final short n41 = this.aShortArray2161[n22 + n33];
                            final short n42 = this.aShortArray2161[n33 + n27];
                            final short n43 = this.aShortArray2161[n32 + n27];
                            final int method1717 = Class105.method1717(n29, n30, n26, this.aShortArray2161[n41 + n19], -5);
                            final int n44 = (n39 * (Class105.method1717(n29, n30, n36, this.aShortArray2161[n31 - -n41], -5) - method1717) >> -2081855316) + method1717;
                            final int method1718 = Class105.method1717(n29, n37, n26, this.aShortArray2161[n19 - -n42], -5);
                            final int n45 = ((Class105.method1717(n29, n37, n36, this.aShortArray2161[n31 + n42], -5) + -method1718) * n39 >> -2138527092) + method1718;
                            final int method1719 = Class105.method1717(n38, n30, n26, this.aShortArray2161[n40 + n19], -5);
                            final int n46 = n44 - -(n35 * (n45 - n44) >> 404369900);
                            final int n47 = ((Class105.method1717(n38, n30, n36, this.aShortArray2161[n40 + n31], -5) + -method1719) * n39 >> 409714956) + method1719;
                            final int method1720 = Class105.method1717(n38, n37, n26, this.aShortArray2161[n43 + n19], -5);
                            this.method3356(n6, n46 - -(((n35 * (-n47 + (method1720 + (n39 * (Class105.method1717(n38, n37, n36, this.aShortArray2161[n31 - -n43], -5) + -method1720) >> -369561460))) >> 547048108) + n47 + -n46) * n34 >> -612835924), 255);
                        }
                        this.method3358(0);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rn.E(" + b + ',' + n + ',' + i + ',' + n2 + ')');
        }
    }
    
    static final void method3362(final int n, final int n2, final int n3, final byte b, final int n4, final int n5) {
        try {
            int n6 = 0;
            int i = n5;
            final int n7 = n2 * n2;
            final int n8 = n5 * n5;
            final int n9 = n8 << -551098815;
            final int n10 = n7 << -2118941983;
            final int n11 = n5 << -1268930943;
            int j = n7 * (1 - n11) - -n9;
            int n12 = -(n10 * (n11 - 1)) + n8;
            final int n13 = n7 << 641021474;
            final int n14 = n8 << -578970494;
            int n15 = n9 * (3 + (n6 << -629209375));
            int n16 = n10 * ((i << 751943489) - 3);
            int n17 = n14 * (1 + n6);
            Class333.method3761(n3, Class97.anIntArrayArray814[n], -n2 + n4, n4 + n2, (byte)99);
            int n18 = n13 * (-1 + i);
            while (i > 0) {
                if (~j > -1) {
                    while (j < 0) {
                        j += n15;
                        n12 += n17;
                        ++n6;
                        n17 += n14;
                        n15 += n14;
                    }
                }
                if (n12 < 0) {
                    n12 += n17;
                    j += n15;
                    n15 += n14;
                    ++n6;
                    n17 += n14;
                }
                j += -n18;
                n12 += -n16;
                n16 -= n13;
                --i;
                n18 -= n13;
                final int n19 = -i + n;
                final int n20 = i + n;
                final int n21 = n4 - -n6;
                final int n22 = n4 - n6;
                Class333.method3761(n3, Class97.anIntArrayArray814[n19], n22, n21, (byte)(-127));
                Class333.method3761(n3, Class97.anIntArrayArray814[n20], n22, n21, (byte)(-124));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rn.B(" + n + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    Class284(final int anInt2163, final int anInt2164, final int anInt2165, final int anInt2166, final int anInt2167) {
        this.anInt2160 = 4;
        this.aShortArray2161 = new short[512];
        this.anInt2159 = 4;
        this.anInt2165 = 4;
        this.anInt2163 = 0;
        this.anInt2166 = 4;
        try {
            this.anInt2160 = anInt2166;
            this.anInt2166 = anInt2167;
            this.anInt2165 = anInt2165;
            this.anInt2159 = anInt2164;
            this.anInt2163 = anInt2163;
            this.method3360(10567);
            this.method3357((byte)116);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rn.<init>(" + anInt2163 + ',' + anInt2164 + ',' + anInt2165 + ',' + anInt2166 + ',' + anInt2167 + ')');
        }
    }
}
