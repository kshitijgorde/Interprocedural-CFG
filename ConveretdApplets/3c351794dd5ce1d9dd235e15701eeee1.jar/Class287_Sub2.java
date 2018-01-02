// 
// Decompiled by Procyon v0.5.30
// 

final class Class287_Sub2 extends Class287 implements Interface8
{
    static OutgoingOpcode aClass171_3270;
    private int anInt3271;
    static String aString3272;
    static float aFloat3273;
    static int anInt3274;
    static Class332[] aClass332Array3275;
    
    @Override
    public final long method22(final int n) {
        try {
            if (n != 20260) {
                Class287_Sub2.aClass332Array3275 = null;
            }
            return 0L;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.I(" + n + ')');
        }
    }
    
    static final void method3391(final int n, final int n2, final int n3) {
        try {
            if (n3 != 2) {
                method3391(101, -12, 47);
            }
            if (Class154.aHa1231 != null) {
                final int anInt1477 = Class191.anInt1477;
                final int anInt1478 = Class63.anInt493;
                Class93_Sub1.method907(7, n, n2);
                if (~Class98_Sub46.anInt4261 == -1) {
                    Class31.anInterface17_301 = null;
                    Class31.anInterface17_301 = Class154.aHa1231.method1815(Class154.aHa1231.method1813(aa_Sub1.anInt3556, Class48_Sub1_Sub2.anInt5519), Class154.aHa1231.method1744(aa_Sub1.anInt3556, Class48_Sub1_Sub2.anInt5519));
                }
                else if (Class98_Sub46.anInt4261 == 1 && (Class172.anInterface17Array1327 == null || ~Class191.anInt1477 != ~anInt1477 || Class63.anInt493 != anInt1478)) {
                    Class172.anInterface17Array1327 = new Interface17[Class191.anInt1477 * Class63.anInt493];
                    for (int n4 = 0; ~Class172.anInterface17Array1327.length < ~n4; ++n4) {
                        Class172.anInterface17Array1327[n4] = Class154.aHa1231.method1815(Class154.aHa1231.method1813(Class197.anInt1513, Class98_Sub10_Sub38.anInt5761), Class154.aHa1231.method1744(Class197.anInt1513, Class98_Sub10_Sub38.anInt5761));
                    }
                    Class146_Sub2.anIntArray4873 = new int[Class191.anInt1477 * Class63.anInt493];
                    Class230.anInt1732 = 1;
                }
                Class358.aBoolean3033 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.F(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final byte[][][] method3392(final int i, final byte b) {
        try {
            final byte[][][] array = new byte[8][4][];
            final byte[] array2 = new byte[i * i];
            int n = 0;
            for (int n2 = 0; ~n2 > ~i; ++n2) {
                for (int j = 0; j < i; ++j) {
                    if (~n2 <= ~j) {
                        array2[n] = -1;
                    }
                    ++n;
                }
            }
            array[0][0] = array2;
            final byte[] array3 = new byte[i * i];
            int n3 = 0;
            for (int k = i - 1; k >= 0; --k) {
                for (int n4 = 0; ~n4 > ~i; ++n4) {
                    if (k >= n4) {
                        array3[n3] = -1;
                    }
                    ++n3;
                }
            }
            array[0][1] = array3;
            int n5 = 0;
            final byte[] array4 = new byte[i * i];
            for (int l = 0; l < i; ++l) {
                for (int n6 = 0; ~i < ~n6; ++n6) {
                    if (l <= n6) {
                        array4[n5] = -1;
                    }
                    ++n5;
                }
            }
            array[0][2] = array4;
            int n7 = 0;
            final byte[] array5 = new byte[i * i];
            for (int n8 = -1 + i; n8 >= 0; --n8) {
                for (int n9 = 0; i > n9; ++n9) {
                    if (n9 >= n8) {
                        array5[n7] = -1;
                    }
                    ++n7;
                }
            }
            array[0][3] = array5;
            int n10 = 0;
            final byte[] array6 = new byte[i * i];
            for (int n11 = i - 1; ~n11 <= -1; --n11) {
                for (int n12 = 0; i > n12; ++n12) {
                    if (n11 >> 637988225 >= n12) {
                        array6[n10] = -1;
                    }
                    ++n10;
                }
            }
            array[1][0] = array6;
            final byte[] array7 = new byte[i * i];
            int n13 = 0;
            for (int n14 = 0; ~n14 > ~i; ++n14) {
                for (int n15 = 0; ~n15 > ~i; ++n15) {
                    if (~n13 > -1 || ~n13 <= ~array7.length) {
                        ++n13;
                    }
                    else {
                        if (~n15 <= ~(n14 << -973618751)) {
                            array7[n13] = -1;
                        }
                        ++n13;
                    }
                }
            }
            array[1][1] = array7;
            final byte[] array8 = new byte[i * i];
            int n16 = 0;
            for (int n17 = 0; n17 < i; ++n17) {
                for (int n18 = -1 + i; ~n18 <= -1; --n18) {
                    if (n18 <= n17 >> -681409855) {
                        array8[n16] = -1;
                    }
                    ++n16;
                }
            }
            array[1][2] = array8;
            int n19 = 0;
            final byte[] array9 = new byte[i * i];
            for (int n20 = i - 1; ~n20 <= -1; --n20) {
                for (int n21 = -1 + i; ~n21 <= -1; --n21) {
                    if (n21 >= n20 << 632067009) {
                        array9[n19] = -1;
                    }
                    ++n19;
                }
            }
            array[1][3] = array9;
            int n22 = 0;
            final byte[] array10 = new byte[i * i];
            for (int n23 = i - 1; ~n23 <= -1; --n23) {
                for (int n24 = -1 + i; ~n24 <= -1; --n24) {
                    if (~n24 >= ~(n23 >> 662025921)) {
                        array10[n22] = -1;
                    }
                    ++n22;
                }
            }
            array[2][0] = array10;
            final byte[] array11 = new byte[i * i];
            int n25 = 0;
            for (int n26 = -1 + i; n26 >= 0; --n26) {
                for (int n27 = 0; i > n27; ++n27) {
                    if (~n27 <= ~(n26 << -1812059327)) {
                        array11[n25] = -1;
                    }
                    ++n25;
                }
            }
            array[2][1] = array11;
            final byte[] array12 = new byte[i * i];
            int n28 = 0;
            for (int n29 = 0; i > n29; ++n29) {
                for (int n30 = 0; i > n30; ++n30) {
                    if (~(n29 >> 1049233889) <= ~n30) {
                        array12[n28] = -1;
                    }
                    ++n28;
                }
            }
            array[2][2] = array12;
            int n31 = 0;
            final byte[] array13 = new byte[i * i];
            for (int n32 = 0; ~i < ~n32; ++n32) {
                for (int n33 = i - 1; n33 >= 0; --n33) {
                    if (~n33 <= ~(n32 << 1402682849)) {
                        array13[n31] = -1;
                    }
                    ++n31;
                }
            }
            array[2][3] = array13;
            final byte[] array14 = new byte[i * i];
            int n34 = 0;
            for (int n35 = i - 1; n35 >= 0; --n35) {
                for (int n36 = 0; n36 < i; ++n36) {
                    if (~(n35 >> -87112031) >= ~n36) {
                        array14[n34] = -1;
                    }
                    ++n34;
                }
            }
            array[3][0] = array14;
            final byte[] array15 = new byte[i * i];
            int n37 = 0;
            for (int n38 = 0; n38 < i; ++n38) {
                for (int n39 = 0; i > n39; ++n39) {
                    if (~n39 >= ~(n38 << -1040521279)) {
                        array15[n37] = -1;
                    }
                    ++n37;
                }
            }
            array[3][1] = array15;
            final byte[] array16 = new byte[i * i];
            int n40 = 0;
            for (int n41 = 0; ~i < ~n41; ++n41) {
                for (int n42 = -1 + i; n42 >= 0; --n42) {
                    if (~n42 <= ~(n41 >> 826979297)) {
                        array16[n40] = -1;
                    }
                    ++n40;
                }
            }
            array[3][2] = array16;
            final byte[] array17 = new byte[i * i];
            int n43 = 0;
            for (int n44 = i - 1; n44 >= 0; --n44) {
                for (int n45 = i - 1; ~n45 <= -1; --n45) {
                    if (n44 << -687952063 >= n45) {
                        array17[n43] = -1;
                    }
                    ++n43;
                }
            }
            array[3][3] = array17;
            final byte[] array18 = new byte[i * i];
            int n46 = 0;
            for (int n47 = i - 1; ~n47 <= -1; --n47) {
                for (int n48 = -1 + i; n48 >= 0; --n48) {
                    if (n47 >> -1221739007 <= n48) {
                        array18[n46] = -1;
                    }
                    ++n46;
                }
            }
            array[4][0] = array18;
            final byte[] array19 = new byte[i * i];
            int n49 = 0;
            for (int n50 = i - 1; n50 >= 0; --n50) {
                for (int n51 = 0; i > n51; ++n51) {
                    if (n51 <= n50 << 573824577) {
                        array19[n49] = -1;
                    }
                    ++n49;
                }
            }
            array[4][1] = array19;
            int n52 = 0;
            final byte[] array20 = new byte[i * i];
            for (int n53 = 0; ~i < ~n53; ++n53) {
                for (int n54 = 0; i > n54; ++n54) {
                    if (n54 >= n53 >> -1550209439) {
                        array20[n52] = -1;
                    }
                    ++n52;
                }
            }
            array[4][2] = array20;
            int n55 = 0;
            final byte[] array21 = new byte[i * i];
            for (int n56 = 0; n56 < i; ++n56) {
                for (int n57 = -1 + i; ~n57 <= -1; --n57) {
                    if (~(n56 << 1546929537) <= ~n57) {
                        array21[n55] = -1;
                    }
                    ++n55;
                }
            }
            array[4][3] = array21;
            int n58 = 0;
            final byte[] array22 = new byte[i * i];
            for (int n59 = 0; ~n59 > ~i; ++n59) {
                for (int n60 = 0; ~i < ~n60; ++n60) {
                    if (i / 2 >= n60) {
                        array22[n58] = -1;
                    }
                    ++n58;
                }
            }
            array[5][0] = array22;
            int n61 = 0;
            final byte[] array23 = new byte[i * i];
            for (int n62 = 0; ~i < ~n62; ++n62) {
                for (int n63 = 0; ~n63 > ~i; ++n63) {
                    if (i / 2 >= n62) {
                        array23[n61] = -1;
                    }
                    ++n61;
                }
            }
            array[5][1] = array23;
            int n64 = 0;
            final byte[] array24 = new byte[i * i];
            for (int n65 = 0; ~n65 > ~i; ++n65) {
                for (int n66 = 0; n66 < i; ++n66) {
                    if (n66 >= i / 2) {
                        array24[n64] = -1;
                    }
                    ++n64;
                }
            }
            array[5][2] = array24;
            int n67 = 0;
            final byte[] array25 = new byte[i * i];
            for (int n68 = 0; ~i < ~n68; ++n68) {
                for (int n69 = 0; ~i < ~n69; ++n69) {
                    if (n68 >= i / 2) {
                        array25[n67] = -1;
                    }
                    ++n67;
                }
            }
            array[5][3] = array25;
            final byte[] array26 = new byte[i * i];
            int n70 = 0;
            for (int n71 = 0; i > n71; ++n71) {
                for (int n72 = 0; ~n72 > ~i; ++n72) {
                    if (~n72 >= ~(n71 + -(i / 2))) {
                        array26[n70] = -1;
                    }
                    ++n70;
                }
            }
            array[6][0] = array26;
            final byte[] array27 = new byte[i * i];
            int n73 = 0;
            for (int n74 = -1 + i; n74 >= 0; --n74) {
                for (int n75 = 0; ~i < ~n75; ++n75) {
                    if (~n75 >= ~(n74 - i / 2)) {
                        array27[n73] = -1;
                    }
                    ++n73;
                }
            }
            array[6][1] = array27;
            final byte[] array28 = new byte[i * i];
            int n76 = 0;
            for (int n77 = i - 1; ~n77 <= -1; --n77) {
                for (int n78 = i - 1; n78 >= 0; --n78) {
                    if (~n78 >= ~(-(i / 2) + n77)) {
                        array28[n76] = -1;
                    }
                    ++n76;
                }
            }
            array[6][2] = array28;
            final byte[] array29 = new byte[i * i];
            int n79 = 0;
            for (int n80 = 0; ~i < ~n80; ++n80) {
                for (int n81 = -1 + i; ~n81 <= -1; --n81) {
                    if (~n81 >= ~(-(i / 2) + n80)) {
                        array29[n79] = -1;
                    }
                    ++n79;
                }
            }
            array[6][3] = array29;
            int n82 = 0;
            final byte[] array30 = new byte[i * i];
            for (int n83 = 0; i > n83; ++n83) {
                for (int n84 = 0; i > n84; ++n84) {
                    if (~n84 <= ~(n83 - i / 2)) {
                        array30[n82] = -1;
                    }
                    ++n82;
                }
            }
            array[7][0] = array30;
            final byte[] array31 = new byte[i * i];
            int n85 = 0;
            for (int n86 = -1 + i; n86 >= 0; --n86) {
                for (int n87 = 0; n87 < i; ++n87) {
                    if (~(n86 - i / 2) >= ~n87) {
                        array31[n85] = -1;
                    }
                    ++n85;
                }
            }
            array[7][1] = array31;
            int n88 = 0;
            final byte[] array32 = new byte[i * i];
            for (int n89 = -1 + i; n89 >= 0; --n89) {
                for (int n90 = i - 1; ~n90 <= -1; --n90) {
                    if (n90 >= -(i / 2) + n89) {
                        array32[n88] = -1;
                    }
                    ++n88;
                }
            }
            array[7][2] = array32;
            int n91 = 0;
            final byte[] array33 = new byte[i * i];
            for (int n92 = 0; i > n92; ++n92) {
                for (int n93 = -1 + i; n93 >= 0; --n93) {
                    if (-(i / 2) + n92 <= n93) {
                        array33[n91] = -1;
                    }
                    ++n91;
                }
            }
            array[7][3] = array33;
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.E(" + i + ',' + b + ')');
        }
    }
    
    @Override
    public final int method19(final int n) {
        try {
            if (n != -22132) {
                return 118;
            }
            return super.anInt2191;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.B(" + n + ')');
        }
    }
    
    @Override
    public final int method21(final int n) {
        try {
            if (n != 5061) {
                Class287_Sub2.aString3272 = null;
            }
            return this.anInt3271;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.A(" + n + ')');
        }
    }
    
    Class287_Sub2(final ha_Sub1 ha_Sub1, final int anInt3271, final byte[] array, final int n, final boolean b) {
        super(ha_Sub1, 34963, array, n, b);
        try {
            this.anInt3271 = anInt3271;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + anInt3271 + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    @Override
    public final void method20(final byte b, final byte[] array, final int n, final int anInt3271) {
        try {
            if (b != -47) {
                Class287_Sub2.aClass171_3270 = null;
            }
            this.method3389(0, n, array);
            this.anInt3271 = anInt3271;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.H(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + anInt3271 + ')');
        }
    }
    
    static final void method3393(final boolean b, final int n, final int n2, final byte b2, final boolean b3) {
        try {
            Class348.method3836(n, 0, Class98_Sub28_Sub1.aClass53_Sub1Array5805.length - 1, n2, b3, b, (byte)91);
            Class98_Sub9.anInt3854 = 0;
            Class220.aClass98_Sub4_1657 = null;
            if (b2 != 82) {
                Class287_Sub2.aClass171_3270 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.C(" + b + ',' + n + ',' + n2 + ',' + b2 + ',' + b3 + ')');
        }
    }
    
    public static void method3394(final int n) {
        try {
            Class287_Sub2.aString3272 = null;
            Class287_Sub2.aClass171_3270 = null;
            Class287_Sub2.aClass332Array3275 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.G(" + n + ')');
        }
    }
    
    @Override
    final void method3384(final int n) {
        try {
            super.aHa_Sub1_2185.method1830(this, 2936);
            if (n != 0) {
                method3394(30);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "no.D(" + n + ')');
        }
    }
    
    static {
        Class287_Sub2.aClass171_3270 = new OutgoingOpcode(29, 6);
        Class287_Sub2.aString3272 = null;
    }
}
