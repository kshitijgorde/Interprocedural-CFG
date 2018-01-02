// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub44 extends Class98
{
    Class98_Sub24_Sub1[] aClass98_Sub24_Sub1Array4244;
    static Class113 aClass113_4245;
    private int[] anIntArray4246;
    byte[] aByteArray4247;
    short[] aShortArray4248;
    int anInt4249;
    byte[] aByteArray4250;
    Class89[] aClass89Array4251;
    byte[] aByteArray4252;
    
    static final void method1512(final boolean b) {
        try {
            for (Class246_Sub2 class246_Sub2 = (Class246_Sub2)Class151_Sub2.aClass218_4973.method2805((byte)(-72)); class246_Sub2 != null; class246_Sub2 = (Class246_Sub2)Class151_Sub2.aClass218_4973.method2805((byte)(-72))) {
                Class305.method3572(20, class246_Sub2);
            }
            if (!b) {
                method1512(false);
            }
            int anInt963;
            int n;
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)120) != 0xFFFFFFFE) {
                n = (anInt963 = Class115.anInt963);
            }
            else {
                n = 3;
                anInt963 = 0;
            }
            client.method114();
            for (int n2 = anInt963; ~n2 >= ~n; ++n2) {
                client.method101();
                client.method105(n2);
                client.method110(n2);
            }
            client.method113();
            client.method115();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tba.D(" + b + ')');
        }
    }
    
    final void method1513(final boolean b) {
        try {
            if (!b) {
                method1515(-105);
            }
            this.anIntArray4246 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tba.E(" + b + ')');
        }
    }
    
    static final boolean method1514(final int n, final int n2) {
        try {
            if (n >= -127) {
                Class98_Sub44.aClass113_4245 = null;
            }
            for (Class98_Sub46_Sub8 class98_Sub46_Sub8 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2418(32); class98_Sub46_Sub8 != null; class98_Sub46_Sub8 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2417(96)) {
                if (Class36.method340(class98_Sub46_Sub8.anInt5990, (byte)(-38)) && ~n2 == ~class98_Sub46_Sub8.aLong5987) {
                    return true;
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tba.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1515(final int n) {
        try {
            Class301.method3543(16535);
            if (n != 2) {
                method1514(-31, -6);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tba.B(" + n + ')');
        }
    }
    
    public Class98_Sub44() {
    }
    
    public static void method1516(final int n) {
        try {
            if (n == 0) {
                Class98_Sub44.aClass113_4245 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tba.C(" + n + ')');
        }
    }
    
    Class98_Sub44(final byte[] array) {
        try {
            this.anIntArray4246 = new int[128];
            this.aByteArray4250 = new byte[128];
            this.aClass98_Sub24_Sub1Array4244 = new Class98_Sub24_Sub1[128];
            this.aByteArray4247 = new byte[128];
            this.aByteArray4252 = new byte[128];
            this.aShortArray4248 = new short[128];
            this.aClass89Array4251 = new Class89[128];
            Class98_Sub22 class98_Sub22;
            int i;
            for (class98_Sub22 = new Class98_Sub22(array), i = 0; ~class98_Sub22.aByteArray3992[i + class98_Sub22.anInt3991] != -1; ++i) {}
            final byte[] array2 = new byte[i];
            for (int n = 0; i > n; ++n) {
                array2[n] = class98_Sub22.readSignedByte((byte)(-19));
            }
            ++i;
            final Class98_Sub22 class98_Sub23 = class98_Sub22;
            ++class98_Sub23.anInt3991;
            int anInt3991 = class98_Sub22.anInt3991;
            final Class98_Sub22 class98_Sub24 = class98_Sub22;
            class98_Sub24.anInt3991 += i;
            int j;
            for (j = 0; class98_Sub22.aByteArray3992[j + class98_Sub22.anInt3991] != 0; ++j) {}
            final byte[] array3 = new byte[j];
            for (int n2 = 0; j > n2; ++n2) {
                array3[n2] = class98_Sub22.readSignedByte((byte)(-19));
            }
            ++j;
            final Class98_Sub22 class98_Sub25 = class98_Sub22;
            ++class98_Sub25.anInt3991;
            int anInt3992 = class98_Sub22.anInt3991;
            final Class98_Sub22 class98_Sub26 = class98_Sub22;
            class98_Sub26.anInt3991 += j;
            int n3;
            for (n3 = 0; class98_Sub22.aByteArray3992[n3 + class98_Sub22.anInt3991] != 0; ++n3) {}
            final byte[] array4 = new byte[n3];
            for (int n4 = 0; ~n4 > ~n3; ++n4) {
                array4[n4] = class98_Sub22.readSignedByte((byte)(-19));
            }
            final Class98_Sub22 class98_Sub27 = class98_Sub22;
            ++class98_Sub27.anInt3991;
            final byte[] array5 = new byte[++n3];
            int k;
            if (~n3 >= -2) {
                k = n3;
            }
            else {
                array5[1] = 1;
                k = 2;
                int n5 = 1;
                for (int n6 = 2; ~n6 > ~n3; ++n6) {
                    int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-110));
                    if (unsignedByte != 0) {
                        if (~n5 <= ~unsignedByte) {
                            --unsignedByte;
                        }
                        n5 = unsignedByte;
                    }
                    else {
                        n5 = k++;
                    }
                    array5[n6] = (byte)n5;
                }
            }
            final Class89[] array6 = new Class89[k];
            for (int l = 0; l < array6.length; ++l) {
                final Class89[] array7 = array6;
                final int n7 = l;
                final Class89 class89 = new Class89();
                array7[n7] = class89;
                final Class89 class90 = class89;
                final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)98);
                if (unsignedByte2 > 0) {
                    class90.aByteArray714 = new byte[2 * unsignedByte2];
                }
                final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)114);
                if (~unsignedByte3 < -1) {
                    (class90.aByteArray713 = new byte[unsignedByte3 * 2 + 2])[1] = 64;
                }
            }
            final int unsignedByte4 = class98_Sub22.readUnsignedByte((byte)(-121));
            final byte[] array8 = (byte[])((unsignedByte4 > 0) ? new byte[unsignedByte4 * 2] : null);
            final int unsignedByte5 = class98_Sub22.readUnsignedByte((byte)(-108));
            final byte[] array9 = (byte[])((unsignedByte5 > 0) ? new byte[2 * unsignedByte5] : null);
            int n8;
            for (n8 = 0; class98_Sub22.aByteArray3992[class98_Sub22.anInt3991 - -n8] != 0; ++n8) {}
            final byte[] array10 = new byte[n8];
            for (int n9 = 0; n9 < n8; ++n9) {
                array10[n9] = class98_Sub22.readSignedByte((byte)(-19));
            }
            ++n8;
            final Class98_Sub22 class98_Sub28 = class98_Sub22;
            ++class98_Sub28.anInt3991;
            int n10 = 0;
            for (int n11 = 0; ~n11 > -129; ++n11) {
                n10 += class98_Sub22.readUnsignedByte((byte)26);
                this.aShortArray4248[n11] = (short)n10;
            }
            int n12 = 0;
            for (int n13 = 0; n13 < 128; ++n13) {
                n12 += class98_Sub22.readUnsignedByte((byte)(-115));
                final short[] aShortArray4248 = this.aShortArray4248;
                final int n14 = n13;
                aShortArray4248[n14] += (short)(n12 << -560014136);
            }
            int n15 = 0;
            int n16 = 0;
            int method1240 = 0;
            for (int n17 = 0; n17 < 128; ++n17) {
                if (n15 == 0) {
                    if (~array10.length >= ~n16) {
                        n15 = -1;
                    }
                    else {
                        n15 = array10[n16++];
                    }
                    method1240 = class98_Sub22.method1240((byte)(-20));
                }
                final short[] aShortArray4249 = this.aShortArray4248;
                final int n18 = n17;
                aShortArray4249[n18] += (short)Class202.method2702(32768, -1 + method1240 << -556142098);
                this.anIntArray4246[n17] = method1240;
                --n15;
            }
            int n19 = 0;
            int n20 = 0;
            byte b = 0;
            for (int n21 = 0; ~n21 > -129; ++n21) {
                if (this.anIntArray4246[n21] != 0) {
                    if (n19 == 0) {
                        b = (byte)(-1 + class98_Sub22.aByteArray3992[anInt3991++]);
                        if (~array2.length >= ~n20) {
                            n19 = -1;
                        }
                        else {
                            n19 = array2[n20++];
                        }
                    }
                    this.aByteArray4250[n21] = b;
                    --n19;
                }
            }
            int n22 = 0;
            int n23 = 0;
            int n24 = 0;
            for (int n25 = 0; n25 < 128; ++n25) {
                if (~this.anIntArray4246[n25] != -1) {
                    if (n22 == 0) {
                        n24 = class98_Sub22.aByteArray3992[anInt3992++] + 16 << -1732087870;
                        if (~n23 > ~array3.length) {
                            n22 = array3[n23++];
                        }
                        else {
                            n22 = -1;
                        }
                    }
                    --n22;
                    this.aByteArray4247[n25] = (byte)n24;
                }
            }
            int n26 = 0;
            int n27 = 0;
            Class89 class91 = null;
            for (int n28 = 0; n28 < 128; ++n28) {
                if (this.anIntArray4246[n28] != 0) {
                    if (n26 == 0) {
                        class91 = array6[array5[n27]];
                        if (array4.length <= n27) {
                            n26 = -1;
                        }
                        else {
                            n26 = array4[n27++];
                        }
                    }
                    --n26;
                    this.aClass89Array4251[n28] = class91;
                }
            }
            int n29 = 0;
            int n30 = 0;
            int n31 = 0;
            for (int n32 = 0; ~n32 > -129; ++n32) {
                if (n30 == 0) {
                    if (array10.length <= n29) {
                        n30 = -1;
                    }
                    else {
                        n30 = array10[n29++];
                    }
                    if (~this.anIntArray4246[n32] < -1) {
                        n31 = class98_Sub22.readUnsignedByte((byte)52) + 1;
                    }
                }
                this.aByteArray4252[n32] = (byte)n31;
                --n30;
            }
            this.anInt4249 = 1 + class98_Sub22.readUnsignedByte((byte)(-108));
            for (int n33 = 0; k > n33; ++n33) {
                final Class89 class92 = array6[n33];
                if (class92.aByteArray714 != null) {
                    for (int n34 = 1; class92.aByteArray714.length > n34; n34 += 2) {
                        class92.aByteArray714[n34] = class98_Sub22.readSignedByte((byte)(-19));
                    }
                }
                if (class92.aByteArray713 != null) {
                    for (int n35 = 3; ~(class92.aByteArray713.length - 2) < ~n35; n35 += 2) {
                        class92.aByteArray713[n35] = class98_Sub22.readSignedByte((byte)(-19));
                    }
                }
            }
            if (array8 != null) {
                for (int n36 = 1; ~array8.length < ~n36; n36 += 2) {
                    array8[n36] = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
            if (array9 != null) {
                for (int n37 = 1; n37 < array9.length; n37 += 2) {
                    array9[n37] = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
            for (int n38 = 0; ~n38 > ~k; ++n38) {
                final Class89 class93 = array6[n38];
                if (class93.aByteArray713 != null) {
                    int n39 = 0;
                    for (int n40 = 2; class93.aByteArray713.length > n40; n40 += 2) {
                        n39 = class98_Sub22.readUnsignedByte((byte)16) + (n39 + 1);
                        class93.aByteArray713[n40] = (byte)n39;
                    }
                }
            }
            for (int n41 = 0; ~k < ~n41; ++n41) {
                final Class89 class94 = array6[n41];
                if (class94.aByteArray714 != null) {
                    int n42 = 0;
                    for (int n43 = 2; ~class94.aByteArray714.length < ~n43; n43 += 2) {
                        n42 -= -1 - class98_Sub22.readUnsignedByte((byte)(-113));
                        class94.aByteArray714[n43] = (byte)n42;
                    }
                }
            }
            if (array8 != null) {
                int unsignedByte6 = class98_Sub22.readUnsignedByte((byte)81);
                array8[0] = (byte)unsignedByte6;
                for (int n44 = 2; ~n44 > ~array8.length; n44 += 2) {
                    unsignedByte6 += class98_Sub22.readUnsignedByte((byte)126) + 1;
                    array8[n44] = (byte)unsignedByte6;
                }
                byte b2 = array8[0];
                byte b3 = array8[1];
                for (byte b4 = 0; b4 < b2; ++b4) {
                    this.aByteArray4252[b4] = (byte)(this.aByteArray4252[b4] * b3 + 32 >> -1262360314);
                }
                byte b5;
                byte b6;
                for (int n45 = 2; array8.length > n45; n45 += 2, b3 = b6, b2 = b5) {
                    b5 = array8[n45];
                    b6 = array8[n45 + 1];
                    byte b7 = (byte)((b5 - b2) * b3 - -((-b2 + b5) / 2));
                    for (byte b8 = b2; b8 < b5; ++b8) {
                        this.aByteArray4252[b8] = (byte)(Class64_Sub26.method658(749908671, b7, b5 - b2) * this.aByteArray4252[b8] + 32 >> -388909850);
                        b7 += (byte)(-b3 + b6);
                    }
                }
                for (int n46 = b2; ~n46 > -129; ++n46) {
                    this.aByteArray4252[n46] = (byte)(this.aByteArray4252[n46] * b3 + 32 >> -689142682);
                }
            }
            if (array9 != null) {
                int unsignedByte7 = class98_Sub22.readUnsignedByte((byte)(-108));
                array9[0] = (byte)unsignedByte7;
                for (int n47 = 2; array9.length > n47; n47 += 2) {
                    unsignedByte7 = unsignedByte7 + 1 - -class98_Sub22.readUnsignedByte((byte)61);
                    array9[n47] = (byte)unsignedByte7;
                }
                byte b9 = array9[0];
                int n48 = array9[1] << -1763562239;
                for (byte b10 = 0; b9 > b10; ++b10) {
                    int n49 = (0xFF & this.aByteArray4247[b10]) + n48;
                    if (n49 < 0) {
                        n49 = 0;
                    }
                    if (n49 > 128) {
                        n49 = 128;
                    }
                    this.aByteArray4247[b10] = (byte)n49;
                }
                byte b11;
                int n51;
                for (int n50 = 2; n50 < array9.length; n50 += 2, n48 = n51, b9 = b11) {
                    b11 = array9[n50];
                    n51 = array9[1 + n50] << -1714708127;
                    byte b12 = (byte)((-b9 + b11) / 2 + n48 * (-b9 + b11));
                    for (int n52 = b9; ~n52 > ~b11; ++n52) {
                        int n53 = Class64_Sub26.method658(749908671, b12, b11 - b9) + (this.aByteArray4247[n52] & 0xFF);
                        if (~n53 > -1) {
                            n53 = 0;
                        }
                        if (n53 > 128) {
                            n53 = 128;
                        }
                        this.aByteArray4247[n52] = (byte)n53;
                        b12 += (byte)(n51 + -n48);
                    }
                }
                for (int n54 = b9; n54 < 128; ++n54) {
                    int n55 = n48 + (0xFF & this.aByteArray4247[n54]);
                    if (n55 < 0) {
                        n55 = 0;
                    }
                    if (~n55 < -129) {
                        n55 = 128;
                    }
                    this.aByteArray4247[n54] = (byte)n55;
                }
            }
            for (int n56 = 0; k > n56; ++n56) {
                array6[n56].anInt707 = class98_Sub22.readUnsignedByte((byte)121);
            }
            for (int n57 = 0; ~k < ~n57; ++n57) {
                final Class89 class95 = array6[n57];
                if (class95.aByteArray714 != null) {
                    class95.anInt711 = class98_Sub22.readUnsignedByte((byte)(-120));
                }
                if (class95.aByteArray713 != null) {
                    class95.anInt715 = class98_Sub22.readUnsignedByte((byte)(-119));
                }
                if (~class95.anInt707 < -1) {
                    class95.anInt712 = class98_Sub22.readUnsignedByte((byte)104);
                }
            }
            for (int n58 = 0; k > n58; ++n58) {
                array6[n58].anInt710 = class98_Sub22.readUnsignedByte((byte)19);
            }
            for (int n59 = 0; ~n59 > ~k; ++n59) {
                final Class89 class96 = array6[n59];
                if (class96.anInt710 > 0) {
                    class96.anInt708 = class98_Sub22.readUnsignedByte((byte)100);
                }
            }
            for (final Class89 class97 : array6) {
                if (class97.anInt708 > 0) {
                    class97.anInt717 = class98_Sub22.readUnsignedByte((byte)(-102));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tba.<init>(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method1517(final byte[] array, final Class308 class308, final byte b, final int[] array2) {
        try {
            boolean b2 = true;
            if (b > -116) {
                return false;
            }
            int n = 0;
            Class98_Sub24_Sub1 class98_Sub24_Sub1 = null;
            for (int n2 = 0; ~n2 > -129; ++n2) {
                if (array == null || ~array[n2] != -1) {
                    int n3 = this.anIntArray4246[n2];
                    if (n3 != 0) {
                        if (~n3 != ~n) {
                            n = n3;
                            --n3;
                            if (~(0x1 & n3) != -1) {
                                class98_Sub24_Sub1 = class308.method3613(n3 >> -996567486, true, array2);
                            }
                            else {
                                class98_Sub24_Sub1 = class308.method3611(-2, n3 >> -1729369054, array2);
                            }
                            if (class98_Sub24_Sub1 == null) {
                                b2 = false;
                            }
                        }
                        if (class98_Sub24_Sub1 != null) {
                            this.aClass98_Sub24_Sub1Array4244[n2] = class98_Sub24_Sub1;
                            this.anIntArray4246[n2] = 0;
                        }
                    }
                }
            }
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tba.A(" + ((array != null) ? "{...}" : "null") + ',' + ((class308 != null) ? "{...}" : "null") + ',' + b + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub44.aClass113_4245 = new Class113(3, 2);
    }
}
