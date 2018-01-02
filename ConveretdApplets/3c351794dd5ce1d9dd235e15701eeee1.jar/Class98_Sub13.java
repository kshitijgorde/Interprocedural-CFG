// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub13 extends Class98
{
    private int anInt3880;
    private float[] aFloatArray3881;
    private static float[] aFloatArray3882;
    private static float[] aFloatArray3883;
    private int anInt3884;
    static Class71[] aClass71Array3885;
    private static float[] aFloatArray3886;
    private static float[] aFloatArray3887;
    private static float[] aFloatArray3888;
    private static int anInt3889;
    private boolean aBoolean3890;
    private static int[] anIntArray3891;
    private boolean aBoolean3892;
    private static int anInt3893;
    private static Class311[] aClass311Array3894;
    private static int anInt3895;
    private int anInt3896;
    private static int[] anIntArray3897;
    private byte[][] aByteArrayArray3898;
    private static float[] aFloatArray3899;
    private int anInt3900;
    private static Class56[] aClass56Array3901;
    private static Class371[] aClass371Array3902;
    private static boolean[] aBooleanArray3903;
    private static int anInt3904;
    private static boolean aBoolean3905;
    private static int[] anIntArray3906;
    private static float[] aFloatArray3907;
    private static byte[] aByteArray3908;
    private int anInt3909;
    private int anInt3910;
    private byte[] aByteArray3911;
    private int anInt3912;
    private int anInt3913;
    
    final Class98_Sub24_Sub1 method1132(final int[] array) {
        if (array != null && array[0] <= 0) {
            return null;
        }
        if (this.aByteArray3911 == null) {
            this.anInt3884 = 0;
            this.aFloatArray3881 = new float[Class98_Sub13.anInt3904];
            this.aByteArray3911 = new byte[this.anInt3910];
            this.anInt3913 = 0;
            this.anInt3912 = 0;
        }
        while (this.anInt3912 < this.aByteArrayArray3898.length) {
            if (array != null && array[0] <= 0) {
                return null;
            }
            final float[] method1135 = this.method1135(this.anInt3912);
            if (method1135 != null) {
                int anInt3913 = this.anInt3913;
                int length = method1135.length;
                if (length > this.anInt3910 - anInt3913) {
                    length = this.anInt3910 - anInt3913;
                }
                for (int i = 0; i < length; ++i) {
                    int n = (int)(128.0f + method1135[i] * 128.0f);
                    if ((n & 0xFFFFFF00) != 0x0) {
                        n = ~n >> 31;
                    }
                    this.aByteArray3911[anInt3913++] = (byte)(n - 128);
                }
                if (array != null) {
                    final int n2 = 0;
                    array[n2] -= anInt3913 - this.anInt3913;
                }
                this.anInt3913 = anInt3913;
            }
            ++this.anInt3912;
        }
        this.aFloatArray3881 = null;
        final byte[] aByteArray3911 = this.aByteArray3911;
        this.aByteArray3911 = null;
        return new Class98_Sub24_Sub1(this.anInt3880, aByteArray3911, this.anInt3896, this.anInt3900, this.aBoolean3890);
    }
    
    private static final boolean method1133(final Class207 class207) {
        if (!Class98_Sub13.aBoolean3905) {
            final byte[] method2745 = class207.method2745(0, 0, false);
            if (method2745 == null) {
                return false;
            }
            method1143(method2745);
        }
        return true;
    }
    
    static final int method1134() {
        final int n = Class98_Sub13.aByteArray3908[Class98_Sub13.anInt3893] >> Class98_Sub13.anInt3895 & 0x1;
        ++Class98_Sub13.anInt3895;
        Class98_Sub13.anInt3893 += Class98_Sub13.anInt3895 >> 3;
        Class98_Sub13.anInt3895 &= 0x7;
        return n;
    }
    
    private final float[] method1135(final int n) {
        method1141(this.aByteArrayArray3898[n], 0);
        method1134();
        final int method1138 = method1138(Class48_Sub2_Sub1.method474(Class98_Sub13.anIntArray3906.length - 1, (byte)31));
        final boolean b = Class98_Sub13.aBooleanArray3903[method1138];
        final int anInt3884 = b ? Class98_Sub13.anInt3904 : Class98_Sub13.anInt3889;
        int n2 = 0;
        int n3 = 0;
        if (b) {
            n2 = ((method1134() != 0) ? 1 : 0);
            n3 = ((method1134() != 0) ? 1 : 0);
        }
        final int n4 = anInt3884 >> 1;
        int n5;
        int n6;
        int n7;
        if (b && n2 == 0) {
            n5 = (anInt3884 >> 2) - (Class98_Sub13.anInt3889 >> 2);
            n6 = (anInt3884 >> 2) + (Class98_Sub13.anInt3889 >> 2);
            n7 = Class98_Sub13.anInt3889 >> 1;
        }
        else {
            n5 = 0;
            n6 = n4;
            n7 = anInt3884 >> 1;
        }
        int n8;
        int n9;
        int n10;
        if (b && n3 == 0) {
            n8 = anInt3884 - (anInt3884 >> 2) - (Class98_Sub13.anInt3889 >> 2);
            n9 = anInt3884 - (anInt3884 >> 2) + (Class98_Sub13.anInt3889 >> 2);
            n10 = Class98_Sub13.anInt3889 >> 1;
        }
        else {
            n8 = n4;
            n9 = anInt3884;
            n10 = anInt3884 >> 1;
        }
        final Class371 class371 = Class98_Sub13.aClass371Array3902[Class98_Sub13.anIntArray3906[method1138]];
        final boolean b2;
        final boolean aBoolean3892 = b2 = !Class98_Sub13.aClass56Array3901[class371.anIntArray3142[class371.anInt3144]].method510();
        for (int i = 0; i < class371.anInt3141; ++i) {
            Class98_Sub13.aClass311Array3894[class371.anIntArray3143[i]].method3619(Class98_Sub13.aFloatArray3882, anInt3884 >> 1, b2);
        }
        if (!aBoolean3892) {
            Class98_Sub13.aClass56Array3901[class371.anIntArray3142[class371.anInt3144]].method513(Class98_Sub13.aFloatArray3882, anInt3884 >> 1);
        }
        if (aBoolean3892) {
            for (int j = anInt3884 >> 1; j < anInt3884; ++j) {
                Class98_Sub13.aFloatArray3882[j] = 0.0f;
            }
        }
        else {
            final int n11 = anInt3884 >> 1;
            final int n12 = anInt3884 >> 2;
            final int n13 = anInt3884 >> 3;
            final float[] aFloatArray3882 = Class98_Sub13.aFloatArray3882;
            for (int k = 0; k < n11; ++k) {
                final float[] array = aFloatArray3882;
                final int n14 = k;
                array[n14] *= 0.5f;
            }
            for (int l = n11; l < anInt3884; ++l) {
                aFloatArray3882[l] = -aFloatArray3882[anInt3884 - l - 1];
            }
            final float[] array2 = b ? Class98_Sub13.aFloatArray3883 : Class98_Sub13.aFloatArray3886;
            final float[] array3 = b ? Class98_Sub13.aFloatArray3888 : Class98_Sub13.aFloatArray3899;
            final float[] array4 = b ? Class98_Sub13.aFloatArray3887 : Class98_Sub13.aFloatArray3907;
            final int[] array5 = b ? Class98_Sub13.anIntArray3891 : Class98_Sub13.anIntArray3897;
            for (int n15 = 0; n15 < n12; ++n15) {
                final float n16 = aFloatArray3882[4 * n15] - aFloatArray3882[anInt3884 - 4 * n15 - 1];
                final float n17 = aFloatArray3882[4 * n15 + 2] - aFloatArray3882[anInt3884 - 4 * n15 - 3];
                final float n18 = array2[2 * n15];
                final float n19 = array2[2 * n15 + 1];
                aFloatArray3882[anInt3884 - 4 * n15 - 1] = n16 * n18 - n17 * n19;
                aFloatArray3882[anInt3884 - 4 * n15 - 3] = n16 * n19 + n17 * n18;
            }
            for (int n20 = 0; n20 < n13; ++n20) {
                final float n21 = aFloatArray3882[n11 + 3 + 4 * n20];
                final float n22 = aFloatArray3882[n11 + 1 + 4 * n20];
                final float n23 = aFloatArray3882[4 * n20 + 3];
                final float n24 = aFloatArray3882[4 * n20 + 1];
                aFloatArray3882[n11 + 3 + 4 * n20] = n21 + n23;
                aFloatArray3882[n11 + 1 + 4 * n20] = n22 + n24;
                final float n25 = array2[n11 - 4 - 4 * n20];
                final float n26 = array2[n11 - 3 - 4 * n20];
                aFloatArray3882[4 * n20 + 3] = (n21 - n23) * n25 - (n22 - n24) * n26;
                aFloatArray3882[4 * n20 + 1] = (n22 - n24) * n25 + (n21 - n23) * n26;
            }
            for (int method1139 = Class48_Sub2_Sub1.method474(anInt3884 - 1, (byte)31), n27 = 0; n27 < method1139 - 3; ++n27) {
                final int n28 = anInt3884 >> n27 + 2;
                final int n29 = 8 << n27;
                for (int n30 = 0; n30 < 2 << n27; ++n30) {
                    final int n31 = anInt3884 - n28 * 2 * n30;
                    final int n32 = anInt3884 - n28 * (2 * n30 + 1);
                    for (int n33 = 0; n33 < anInt3884 >> n27 + 4; ++n33) {
                        final int n34 = 4 * n33;
                        final float n35 = aFloatArray3882[n31 - 1 - n34];
                        final float n36 = aFloatArray3882[n31 - 3 - n34];
                        final float n37 = aFloatArray3882[n32 - 1 - n34];
                        final float n38 = aFloatArray3882[n32 - 3 - n34];
                        aFloatArray3882[n31 - 1 - n34] = n35 + n37;
                        aFloatArray3882[n31 - 3 - n34] = n36 + n38;
                        final float n39 = array2[n33 * n29];
                        final float n40 = array2[n33 * n29 + 1];
                        aFloatArray3882[n32 - 1 - n34] = (n35 - n37) * n39 - (n36 - n38) * n40;
                        aFloatArray3882[n32 - 3 - n34] = (n36 - n38) * n39 + (n35 - n37) * n40;
                    }
                }
            }
            for (int n41 = 1; n41 < n13 - 1; ++n41) {
                final int n42 = array5[n41];
                if (n41 < n42) {
                    final int n43 = 8 * n41;
                    final int n44 = 8 * n42;
                    final float n45 = aFloatArray3882[n43 + 1];
                    aFloatArray3882[n43 + 1] = aFloatArray3882[n44 + 1];
                    aFloatArray3882[n44 + 1] = n45;
                    final float n46 = aFloatArray3882[n43 + 3];
                    aFloatArray3882[n43 + 3] = aFloatArray3882[n44 + 3];
                    aFloatArray3882[n44 + 3] = n46;
                    final float n47 = aFloatArray3882[n43 + 5];
                    aFloatArray3882[n43 + 5] = aFloatArray3882[n44 + 5];
                    aFloatArray3882[n44 + 5] = n47;
                    final float n48 = aFloatArray3882[n43 + 7];
                    aFloatArray3882[n43 + 7] = aFloatArray3882[n44 + 7];
                    aFloatArray3882[n44 + 7] = n48;
                }
            }
            for (int n49 = 0; n49 < n11; ++n49) {
                aFloatArray3882[n49] = aFloatArray3882[2 * n49 + 1];
            }
            for (int n50 = 0; n50 < n13; ++n50) {
                aFloatArray3882[anInt3884 - 1 - 2 * n50] = aFloatArray3882[4 * n50];
                aFloatArray3882[anInt3884 - 2 - 2 * n50] = aFloatArray3882[4 * n50 + 1];
                aFloatArray3882[anInt3884 - n12 - 1 - 2 * n50] = aFloatArray3882[4 * n50 + 2];
                aFloatArray3882[anInt3884 - n12 - 2 - 2 * n50] = aFloatArray3882[4 * n50 + 3];
            }
            for (int n51 = 0; n51 < n13; ++n51) {
                final float n52 = array4[2 * n51];
                final float n53 = array4[2 * n51 + 1];
                final float n54 = aFloatArray3882[n11 + 2 * n51];
                final float n55 = aFloatArray3882[n11 + 2 * n51 + 1];
                final float n56 = aFloatArray3882[anInt3884 - 2 - 2 * n51];
                final float n57 = aFloatArray3882[anInt3884 - 1 - 2 * n51];
                final float n58 = n53 * (n54 - n56) + n52 * (n55 + n57);
                aFloatArray3882[n11 + 2 * n51] = (n54 + n56 + n58) * 0.5f;
                aFloatArray3882[anInt3884 - 2 - 2 * n51] = (n54 + n56 - n58) * 0.5f;
                final float n59 = n53 * (n55 + n57) - n52 * (n54 - n56);
                aFloatArray3882[n11 + 2 * n51 + 1] = (n55 - n57 + n59) * 0.5f;
                aFloatArray3882[anInt3884 - 1 - 2 * n51] = (-n55 + n57 + n59) * 0.5f;
            }
            for (int n60 = 0; n60 < n12; ++n60) {
                aFloatArray3882[n60] = aFloatArray3882[2 * n60 + n11] * array3[2 * n60] + aFloatArray3882[2 * n60 + 1 + n11] * array3[2 * n60 + 1];
                aFloatArray3882[n11 - 1 - n60] = aFloatArray3882[2 * n60 + n11] * array3[2 * n60 + 1] - aFloatArray3882[2 * n60 + 1 + n11] * array3[2 * n60];
            }
            for (int n61 = 0; n61 < n12; ++n61) {
                aFloatArray3882[anInt3884 - n12 + n61] = -aFloatArray3882[n61];
            }
            for (int n62 = 0; n62 < n12; ++n62) {
                aFloatArray3882[n62] = aFloatArray3882[n12 + n62];
            }
            for (int n63 = 0; n63 < n12; ++n63) {
                aFloatArray3882[n12 + n63] = -aFloatArray3882[n12 - n63 - 1];
            }
            for (int n64 = 0; n64 < n12; ++n64) {
                aFloatArray3882[n11 + n64] = aFloatArray3882[anInt3884 - n64 - 1];
            }
            for (int n65 = n5; n65 < n6; ++n65) {
                final float n66 = (float)Math.sin((n65 - n5 + 0.5) / n7 * 0.5 * 3.141592653589793);
                final float[] aFloatArray3883 = Class98_Sub13.aFloatArray3882;
                final int n67 = n65;
                aFloatArray3883[n67] *= (float)Math.sin(1.5707963267948966 * n66 * n66);
            }
            for (int n68 = n8; n68 < n9; ++n68) {
                final float n69 = (float)Math.sin((n68 - n8 + 0.5) / n10 * 0.5 * 3.141592653589793 + 1.5707963267948966);
                final float[] aFloatArray3884 = Class98_Sub13.aFloatArray3882;
                final int n70 = n68;
                aFloatArray3884[n70] *= (float)Math.sin(1.5707963267948966 * n69 * n69);
            }
        }
        float[] array6 = null;
        if (this.anInt3884 > 0) {
            array6 = new float[this.anInt3884 + anInt3884 >> 2];
            if (!this.aBoolean3892) {
                for (int n71 = 0; n71 < this.anInt3909; ++n71) {
                    final int n72 = (this.anInt3884 >> 1) + n71;
                    final float[] array7 = array6;
                    final int n73 = n71;
                    array7[n73] += this.aFloatArray3881[n72];
                }
            }
            if (!aBoolean3892) {
                for (int n74 = n5; n74 < anInt3884 >> 1; ++n74) {
                    final int n75 = array6.length - (anInt3884 >> 1) + n74;
                    final float[] array8 = array6;
                    final int n76 = n75;
                    array8[n76] += Class98_Sub13.aFloatArray3882[n74];
                }
            }
        }
        final float[] aFloatArray3885 = this.aFloatArray3881;
        this.aFloatArray3881 = Class98_Sub13.aFloatArray3882;
        Class98_Sub13.aFloatArray3882 = aFloatArray3885;
        this.anInt3884 = anInt3884;
        this.anInt3909 = n9 - (anInt3884 >> 1);
        this.aBoolean3892 = aBoolean3892;
        return array6;
    }
    
    public static void method1136() {
        Class98_Sub13.aByteArray3908 = null;
        Class98_Sub13.aClass71Array3885 = null;
        Class98_Sub13.aClass56Array3901 = null;
        Class98_Sub13.aClass311Array3894 = null;
        Class98_Sub13.aClass371Array3902 = null;
        Class98_Sub13.aBooleanArray3903 = null;
        Class98_Sub13.anIntArray3906 = null;
        Class98_Sub13.aFloatArray3882 = null;
        Class98_Sub13.aFloatArray3886 = null;
        Class98_Sub13.aFloatArray3899 = null;
        Class98_Sub13.aFloatArray3907 = null;
        Class98_Sub13.aFloatArray3883 = null;
        Class98_Sub13.aFloatArray3888 = null;
        Class98_Sub13.aFloatArray3887 = null;
        Class98_Sub13.anIntArray3897 = null;
        Class98_Sub13.anIntArray3891 = null;
    }
    
    static final Class98_Sub13 method1137(final Class207 class207, final int n) {
        if (!method1133(class207)) {
            class207.method2742(-88, n);
            return null;
        }
        final byte[] method2733 = class207.method2733(n, 55);
        if (method2733 == null) {
            return null;
        }
        return new Class98_Sub13(method2733);
    }
    
    static final int method1138(int i) {
        int n = 0;
        int n2 = 0;
        while (i >= 8 - Class98_Sub13.anInt3895) {
            final int n3 = 8 - Class98_Sub13.anInt3895;
            n += (Class98_Sub13.aByteArray3908[Class98_Sub13.anInt3893] >> Class98_Sub13.anInt3895 & (1 << n3) - 1) << n2;
            Class98_Sub13.anInt3895 = 0;
            ++Class98_Sub13.anInt3893;
            n2 += n3;
            i -= n3;
        }
        if (i > 0) {
            n += (Class98_Sub13.aByteArray3908[Class98_Sub13.anInt3893] >> Class98_Sub13.anInt3895 & (1 << i) - 1) << n2;
            Class98_Sub13.anInt3895 += i;
        }
        return n;
    }
    
    static final float method1139(final int n) {
        int n2 = n & 0x1FFFFF;
        final int n3 = n & Integer.MIN_VALUE;
        final int n4 = (n & 0x7FE00000) >> 21;
        if (n3 != 0) {
            n2 = -n2;
        }
        return (float)(n2 * Math.pow(2.0, n4 - 788));
    }
    
    static final Class98_Sub13 method1140(final Class207 class207, final int n, final int n2) {
        if (!method1133(class207)) {
            class207.method2751(n2, n, -6329);
            return null;
        }
        final byte[] method2745 = class207.method2745(n2, n, false);
        if (method2745 == null) {
            return null;
        }
        return new Class98_Sub13(method2745);
    }
    
    private static final void method1141(final byte[] aByteArray3908, final int anInt3893) {
        Class98_Sub13.aByteArray3908 = aByteArray3908;
        Class98_Sub13.anInt3893 = anInt3893;
        Class98_Sub13.anInt3895 = 0;
    }
    
    private final void method1142(final byte[] array) {
        final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
        this.anInt3880 = class98_Sub22.readInt(-2);
        this.anInt3910 = class98_Sub22.readInt(-2);
        this.anInt3896 = class98_Sub22.readInt(-2);
        this.anInt3900 = class98_Sub22.readInt(-2);
        if (this.anInt3900 < 0) {
            this.anInt3900 ^= -1;
            this.aBoolean3890 = true;
        }
        final int int1 = class98_Sub22.readInt(-2);
        this.aByteArrayArray3898 = new byte[int1][];
        for (int i = 0; i < int1; ++i) {
            int n = 0;
            int j;
            do {
                j = class98_Sub22.readUnsignedByte((byte)(-101));
                n += j;
            } while (j >= 255);
            final byte[] array2 = new byte[n];
            class98_Sub22.method1190(array2, true, n, 0);
            this.aByteArrayArray3898[i] = array2;
        }
    }
    
    private static final void method1143(final byte[] array) {
        method1141(array, 0);
        Class98_Sub13.anInt3889 = 1 << method1138(4);
        Class98_Sub13.anInt3904 = 1 << method1138(4);
        Class98_Sub13.aFloatArray3882 = new float[Class98_Sub13.anInt3904];
        for (int i = 0; i < 2; ++i) {
            final int n = (i != 0) ? Class98_Sub13.anInt3904 : Class98_Sub13.anInt3889;
            final int n2 = n >> 1;
            final int n3 = n >> 2;
            final int n4 = n >> 3;
            final float[] array2 = new float[n2];
            for (int j = 0; j < n3; ++j) {
                array2[2 * j] = (float)Math.cos(4 * j * 3.141592653589793 / n);
                array2[2 * j + 1] = -(float)Math.sin(4 * j * 3.141592653589793 / n);
            }
            final float[] array3 = new float[n2];
            for (int k = 0; k < n3; ++k) {
                array3[2 * k] = (float)Math.cos((2 * k + 1) * 3.141592653589793 / (2 * n));
                array3[2 * k + 1] = (float)Math.sin((2 * k + 1) * 3.141592653589793 / (2 * n));
            }
            final float[] array4 = new float[n3];
            for (int l = 0; l < n4; ++l) {
                array4[2 * l] = (float)Math.cos((4 * l + 2) * 3.141592653589793 / n);
                array4[2 * l + 1] = -(float)Math.sin((4 * l + 2) * 3.141592653589793 / n);
            }
            final int[] array5 = new int[n4];
            final int method474 = Class48_Sub2_Sub1.method474(n4 - 1, (byte)31);
            for (int n5 = 0; n5 < n4; ++n5) {
                array5[n5] = Applet_Sub1.method81(method474, (byte)(-9), n5);
            }
            if (i != 0) {
                Class98_Sub13.aFloatArray3883 = array2;
                Class98_Sub13.aFloatArray3888 = array3;
                Class98_Sub13.aFloatArray3887 = array4;
                Class98_Sub13.anIntArray3891 = array5;
            }
            else {
                Class98_Sub13.aFloatArray3886 = array2;
                Class98_Sub13.aFloatArray3899 = array3;
                Class98_Sub13.aFloatArray3907 = array4;
                Class98_Sub13.anIntArray3897 = array5;
            }
        }
        final int n6 = method1138(8) + 1;
        Class98_Sub13.aClass71Array3885 = new Class71[n6];
        for (int n7 = 0; n7 < n6; ++n7) {
            Class98_Sub13.aClass71Array3885[n7] = new Class71();
        }
        for (int n8 = method1138(6) + 1, n9 = 0; n9 < n8; ++n9) {
            method1138(16);
        }
        final int n10 = method1138(6) + 1;
        Class98_Sub13.aClass56Array3901 = new Class56[n10];
        for (int n11 = 0; n11 < n10; ++n11) {
            Class98_Sub13.aClass56Array3901[n11] = new Class56();
        }
        final int n12 = method1138(6) + 1;
        Class98_Sub13.aClass311Array3894 = new Class311[n12];
        for (int n13 = 0; n13 < n12; ++n13) {
            Class98_Sub13.aClass311Array3894[n13] = new Class311();
        }
        final int n14 = method1138(6) + 1;
        Class98_Sub13.aClass371Array3902 = new Class371[n14];
        for (int n15 = 0; n15 < n14; ++n15) {
            Class98_Sub13.aClass371Array3902[n15] = new Class371();
        }
        final int n16 = method1138(6) + 1;
        Class98_Sub13.aBooleanArray3903 = new boolean[n16];
        Class98_Sub13.anIntArray3906 = new int[n16];
        for (int n17 = 0; n17 < n16; ++n17) {
            Class98_Sub13.aBooleanArray3903[n17] = (method1134() != 0);
            method1138(16);
            method1138(16);
            Class98_Sub13.anIntArray3906[n17] = method1138(8);
        }
        Class98_Sub13.aBoolean3905 = true;
    }
    
    private Class98_Sub13(final byte[] array) {
        this.method1142(array);
    }
    
    static {
        Class98_Sub13.aBoolean3905 = false;
    }
}
