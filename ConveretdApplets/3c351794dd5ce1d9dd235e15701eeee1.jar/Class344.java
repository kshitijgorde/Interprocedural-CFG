import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class344
{
    private static int[] anIntArray2864;
    private Class209 aClass209_2865;
    private static int[] anIntArray2866;
    int anInt2867;
    private int anInt2868;
    private Class209 aClass209_2869;
    int anInt2870;
    private Class182 aClass182_2871;
    private Class209 aClass209_2872;
    private int[] anIntArray2873;
    private Class209 aClass209_2874;
    private int[] anIntArray2875;
    private Class209 aClass209_2876;
    private Class209 aClass209_2877;
    private Class209 aClass209_2878;
    private static int[] anIntArray2879;
    private Class209 aClass209_2880;
    private int[] anIntArray2881;
    private int anInt2882;
    private Class209 aClass209_2883;
    private static int[] anIntArray2884;
    private static int[] anIntArray2885;
    private static int[] anIntArray2886;
    private static int[] anIntArray2887;
    private static int[] anIntArray2888;
    
    final void method3820(final Class98_Sub22 class98_Sub22) {
        (this.aClass209_2880 = new Class209()).method2771(class98_Sub22);
        (this.aClass209_2883 = new Class209()).method2771(class98_Sub22);
        if (class98_Sub22.readUnsignedByte((byte)95) != 0) {
            --class98_Sub22.anInt3991;
            (this.aClass209_2874 = new Class209()).method2771(class98_Sub22);
            (this.aClass209_2869 = new Class209()).method2771(class98_Sub22);
        }
        if (class98_Sub22.readUnsignedByte((byte)36) != 0) {
            --class98_Sub22.anInt3991;
            (this.aClass209_2876 = new Class209()).method2771(class98_Sub22);
            (this.aClass209_2878 = new Class209()).method2771(class98_Sub22);
        }
        if (class98_Sub22.readUnsignedByte((byte)(-99)) != 0) {
            --class98_Sub22.anInt3991;
            (this.aClass209_2872 = new Class209()).method2771(class98_Sub22);
            (this.aClass209_2877 = new Class209()).method2771(class98_Sub22);
        }
        for (int i = 0; i < 10; ++i) {
            final int smart = class98_Sub22.readSmart(1689622712);
            if (smart == 0) {
                break;
            }
            this.anIntArray2881[i] = smart;
            this.anIntArray2875[i] = class98_Sub22.method1239(-80);
            this.anIntArray2873[i] = class98_Sub22.readSmart(1689622712);
        }
        this.anInt2882 = class98_Sub22.readSmart(1689622712);
        this.anInt2868 = class98_Sub22.readSmart(1689622712);
        this.anInt2870 = class98_Sub22.readShort((byte)127);
        this.anInt2867 = class98_Sub22.readShort((byte)127);
        this.aClass182_2871 = new Class182();
        this.aClass209_2865 = new Class209();
        this.aClass182_2871.method2612(class98_Sub22, this.aClass209_2865);
    }
    
    private final int method3821(final int n, final int n2, final int n3) {
        if (n3 == 1) {
            if ((n & 0x7FFF) < 16384) {
                return n2;
            }
            return -n2;
        }
        else {
            if (n3 == 2) {
                return Class344.anIntArray2866[n & 0x7FFF] * n2 >> 14;
            }
            if (n3 == 3) {
                return ((n & 0x7FFF) * n2 >> 14) - n2;
            }
            if (n3 == 4) {
                return Class344.anIntArray2864[n / 2607 & 0x7FFF] * n2;
            }
            return 0;
        }
    }
    
    final int[] method3822(final int n, final int n2) {
        Class236.method2893(Class344.anIntArray2879, 0, n);
        if (n2 < 10) {
            return Class344.anIntArray2879;
        }
        final double n3 = n / (n2 + 0.0);
        this.aClass209_2880.method2769();
        this.aClass209_2883.method2769();
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        if (this.aClass209_2874 != null) {
            this.aClass209_2874.method2769();
            this.aClass209_2869.method2769();
            n4 = (int)((this.aClass209_2874.anInt1583 - this.aClass209_2874.anInt1587) * 32.768 / n3);
            n5 = (int)(this.aClass209_2874.anInt1587 * 32.768 / n3);
        }
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        if (this.aClass209_2876 != null) {
            this.aClass209_2876.method2769();
            this.aClass209_2878.method2769();
            n7 = (int)((this.aClass209_2876.anInt1583 - this.aClass209_2876.anInt1587) * 32.768 / n3);
            n8 = (int)(this.aClass209_2876.anInt1587 * 32.768 / n3);
        }
        for (int i = 0; i < 5; ++i) {
            if (this.anIntArray2881[i] != 0) {
                Class344.anIntArray2887[i] = 0;
                Class344.anIntArray2884[i] = (int)(this.anIntArray2873[i] * n3);
                Class344.anIntArray2888[i] = (this.anIntArray2881[i] << 14) / 100;
                Class344.anIntArray2885[i] = (int)((this.aClass209_2880.anInt1583 - this.aClass209_2880.anInt1587) * 32.768 * Math.pow(1.0057929410678534, this.anIntArray2875[i]) / n3);
                Class344.anIntArray2886[i] = (int)(this.aClass209_2880.anInt1587 * 32.768 / n3);
            }
        }
        for (int j = 0; j < n; ++j) {
            int method2770 = this.aClass209_2880.method2770(n);
            int method2771 = this.aClass209_2883.method2770(n);
            if (this.aClass209_2874 != null) {
                final int method2772 = this.aClass209_2874.method2770(n);
                method2770 += this.method3821(n6, this.aClass209_2869.method2770(n), this.aClass209_2874.anInt1584) >> 1;
                n6 += (method2772 * n4 >> 16) + n5;
            }
            if (this.aClass209_2876 != null) {
                final int method2773 = this.aClass209_2876.method2770(n);
                method2771 = method2771 * ((this.method3821(n9, this.aClass209_2878.method2770(n), this.aClass209_2876.anInt1584) >> 1) + 32768) >> 15;
                n9 += (method2773 * n7 >> 16) + n8;
            }
            for (int k = 0; k < 5; ++k) {
                if (this.anIntArray2881[k] != 0) {
                    final int n10 = j + Class344.anIntArray2884[k];
                    if (n10 < n) {
                        final int[] anIntArray2879 = Class344.anIntArray2879;
                        final int n11 = n10;
                        anIntArray2879[n11] += this.method3821(Class344.anIntArray2887[k], method2771 * Class344.anIntArray2888[k] >> 15, this.aClass209_2880.anInt1584);
                        final int[] anIntArray2880 = Class344.anIntArray2887;
                        final int n12 = k;
                        anIntArray2880[n12] += (method2770 * Class344.anIntArray2885[k] >> 16) + Class344.anIntArray2886[k];
                    }
                }
            }
        }
        if (this.aClass209_2872 != null) {
            this.aClass209_2872.method2769();
            this.aClass209_2877.method2769();
            int n13 = 0;
            boolean b = true;
            for (int l = 0; l < n; ++l) {
                final int method2774 = this.aClass209_2872.method2770(n);
                final int method2775 = this.aClass209_2877.method2770(n);
                int n14;
                if (b) {
                    n14 = this.aClass209_2872.anInt1587 + ((this.aClass209_2872.anInt1583 - this.aClass209_2872.anInt1587) * method2774 >> 8);
                }
                else {
                    n14 = this.aClass209_2872.anInt1587 + ((this.aClass209_2872.anInt1583 - this.aClass209_2872.anInt1587) * method2775 >> 8);
                }
                n13 += 256;
                if (n13 >= n14) {
                    n13 = 0;
                    b = !b;
                }
                if (b) {
                    Class344.anIntArray2879[l] = 0;
                }
            }
        }
        if (this.anInt2882 > 0 && this.anInt2868 > 0) {
            int n16;
            for (int n15 = n16 = (int)(this.anInt2882 * n3); n16 < n; ++n16) {
                final int[] anIntArray2881 = Class344.anIntArray2879;
                final int n17 = n16;
                anIntArray2881[n17] += Class344.anIntArray2879[n16 - n15] * this.anInt2868 / 100;
            }
        }
        if (this.aClass182_2871.anIntArray1437[0] > 0 || this.aClass182_2871.anIntArray1437[1] > 0) {
            this.aClass209_2865.method2769();
            int n18 = this.aClass209_2865.method2770(n + 1);
            int n19 = this.aClass182_2871.method2613(0, n18 / 65536.0f);
            int n20 = this.aClass182_2871.method2613(1, n18 / 65536.0f);
            if (n >= n19 + n20) {
                int n21 = 0;
                int n22 = n20;
                if (n22 > n - n19) {
                    n22 = n - n19;
                }
                while (n21 < n22) {
                    int n23 = Class344.anIntArray2879[n21 + n19] * Class182.anInt1439 >> 16;
                    for (int n24 = 0; n24 < n19; ++n24) {
                        n23 += Class344.anIntArray2879[n21 + n19 - 1 - n24] * Class182.anIntArrayArray1438[0][n24] >> 16;
                    }
                    for (int n25 = 0; n25 < n21; ++n25) {
                        n23 -= Class344.anIntArray2879[n21 - 1 - n25] * Class182.anIntArrayArray1438[1][n25] >> 16;
                    }
                    Class344.anIntArray2879[n21] = n23;
                    n18 = this.aClass209_2865.method2770(n + 1);
                    ++n21;
                }
                int n26 = 128;
                while (true) {
                    if (n26 > n - n19) {
                        n26 = n - n19;
                    }
                    while (n21 < n26) {
                        int n27 = Class344.anIntArray2879[n21 + n19] * Class182.anInt1439 >> 16;
                        for (int n28 = 0; n28 < n19; ++n28) {
                            n27 += Class344.anIntArray2879[n21 + n19 - 1 - n28] * Class182.anIntArrayArray1438[0][n28] >> 16;
                        }
                        for (int n29 = 0; n29 < n20; ++n29) {
                            n27 -= Class344.anIntArray2879[n21 - 1 - n29] * Class182.anIntArrayArray1438[1][n29] >> 16;
                        }
                        Class344.anIntArray2879[n21] = n27;
                        n18 = this.aClass209_2865.method2770(n + 1);
                        ++n21;
                    }
                    if (n21 >= n - n19) {
                        break;
                    }
                    n19 = this.aClass182_2871.method2613(0, n18 / 65536.0f);
                    n20 = this.aClass182_2871.method2613(1, n18 / 65536.0f);
                    n26 += 128;
                }
                while (n21 < n) {
                    int n30 = 0;
                    for (int n31 = n21 + n19 - n; n31 < n19; ++n31) {
                        n30 += Class344.anIntArray2879[n21 + n19 - 1 - n31] * Class182.anIntArrayArray1438[0][n31] >> 16;
                    }
                    for (int n32 = 0; n32 < n20; ++n32) {
                        n30 -= Class344.anIntArray2879[n21 - 1 - n32] * Class182.anIntArrayArray1438[1][n32] >> 16;
                    }
                    Class344.anIntArray2879[n21] = n30;
                    this.aClass209_2865.method2770(n + 1);
                    ++n21;
                }
            }
        }
        for (int n33 = 0; n33 < n; ++n33) {
            if (Class344.anIntArray2879[n33] < -32768) {
                Class344.anIntArray2879[n33] = -32768;
            }
            if (Class344.anIntArray2879[n33] > 32767) {
                Class344.anIntArray2879[n33] = 32767;
            }
        }
        return Class344.anIntArray2879;
    }
    
    public static void method3823() {
        Class344.anIntArray2879 = null;
        Class344.anIntArray2864 = null;
        Class344.anIntArray2866 = null;
        Class344.anIntArray2887 = null;
        Class344.anIntArray2884 = null;
        Class344.anIntArray2888 = null;
        Class344.anIntArray2885 = null;
        Class344.anIntArray2886 = null;
    }
    
    public Class344() {
        this.anInt2868 = 100;
        this.anInt2867 = 0;
        this.anIntArray2873 = new int[5];
        this.anIntArray2875 = new int[5];
        this.anInt2870 = 500;
        this.anIntArray2881 = new int[5];
        this.anInt2882 = 0;
    }
    
    static {
        Class344.anIntArray2864 = new int[32768];
        final Random random = new Random(0L);
        for (int i = 0; i < 32768; ++i) {
            Class344.anIntArray2864[i] = (random.nextInt() & 0x2) - 1;
        }
        Class344.anIntArray2866 = new int[32768];
        for (int j = 0; j < 32768; ++j) {
            Class344.anIntArray2866[j] = (int)(Math.sin(j / 5215.1903) * 16384.0);
        }
        Class344.anIntArray2879 = new int[220500];
        Class344.anIntArray2885 = new int[5];
        Class344.anIntArray2888 = new int[5];
        Class344.anIntArray2887 = new int[5];
        Class344.anIntArray2884 = new int[5];
        Class344.anIntArray2886 = new int[5];
    }
}
