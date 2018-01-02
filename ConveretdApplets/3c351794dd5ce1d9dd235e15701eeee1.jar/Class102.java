// 
// Decompiled by Procyon v0.5.30
// 

final class Class102
{
    private int anInt858;
    private Interface14 anInterface14_859;
    static Class102 aClass102_860;
    private Class191 aClass191_861;
    static Class102 aClass102_862;
    static Class102 aClass102_863;
    static Class102 aClass102_864;
    static Class102 aClass102_865;
    static Class102 aClass102_866;
    static Class102 aClass102_867;
    static Class102 aClass102_868;
    static Class102 aClass102_869;
    static Class102 aClass102_870;
    static Class102 aClass102_871;
    static Class102 aClass102_872;
    static Class102 aClass102_873;
    static Class102 aClass102_874;
    static Class102 aClass102_875;
    static Class102 aClass102_876;
    static Class102 aClass102_877;
    static Class102 aClass102_878;
    static Class102 aClass102_879;
    static Class102 aClass102_880;
    static Class102 aClass102_881;
    static Class102 aClass102_882;
    static Class102 aClass102_883;
    static Class102 aClass102_884;
    static Class102 aClass102_885;
    static Class102 aClass102_886;
    static Class102 aClass102_887;
    static Class350 aClass350_888;
    static boolean aBoolean889;
    
    static final Class146 method1703(final Class97 class97, final int n, final int n2, final int n3, final Class146 class98, final int n4, final int n5, final int n6, final int n7, final int n8, int n9, final ha ha, final int n10, final int n11) {
        try {
            if (class98 == null) {
                return null;
            }
            int method1777 = 2055;
            if (n6 < 113) {
                return null;
            }
            if (class97 != null) {
                method1777 = ((method1777 | class97.method932(false, n9, true, -1)) & 0xFFFFFDFF);
            }
            final long n12 = n2 + (n7 << -903417392) - -(n3 << -423490568) - -(n10 << 142401248) + (n8 << 21507696);
            Class146 method1778;
            synchronized (Class299.aClass79_2493) {
                method1778 = (Class146)Class299.aClass79_2493.method802(-119, n12);
            }
            if (method1778 == null || ~ha.c(method1778.ua(), method1777) != -1) {
                if (method1778 != null) {
                    method1777 = ha.method1777(method1777, method1778.ua());
                }
                int i;
                if (~n2 != 0xFFFFFFFE) {
                    if (n2 != 2) {
                        if (n2 != 3) {
                            if (n2 == 4) {
                                i = 18;
                            }
                            else {
                                i = 21;
                            }
                        }
                        else {
                            i = 15;
                        }
                    }
                    else {
                        i = 12;
                    }
                }
                else {
                    i = 9;
                }
                final int n13 = 3;
                final int[] array = { 64, 96, 128 };
                final Class178 class99 = new Class178(n13 * i + 1, n13 * (i * 2) - i, 0);
                final int method1779 = class99.method2599(14418, 0, 0, 0);
                final int[][] array2 = new int[n13][i];
                for (int j = 0; j < n13; ++j) {
                    final int n14 = array[j];
                    final int n15 = array[j];
                    for (int n16 = 0; i > n16; ++n16) {
                        final int n17 = (n16 << 1158136782) / i;
                        array2[j][n16] = class99.method2599(14418, Class284_Sub2_Sub2.anIntArray6200[n17] * n14 >> 1774149838, 0, Class284_Sub2_Sub2.anIntArray6202[n17] * n15 >> 1320391086);
                    }
                }
                for (int k = 0; k < n13; ++k) {
                    final int n18 = (k * 256 + 128) / n13;
                    final int n19 = -n18 + 256;
                    final byte b = (byte)(n7 * n19 + n18 * n3 >> -266203096);
                    final short n20 = (short)((0x7F00 & n19 * (0x7F & n10) + (0x7F & n8) * n18) + ((0xFC0000 & n18 * (0xFC00 & n8) + n19 * (0xFC00 & n10)) + ((n10 & 0x380) * n19 + (0x380 & n8) * n18 & 0x38000)) >> -887272664);
                    for (int n21 = 0; ~n21 > ~i; ++n21) {
                        if (~k != -1) {
                            class99.method2594((byte)1, n20, (short)(-1), b, false, array2[k][(1 + n21) % i], array2[-1 + k][n21], (byte)(-1), array2[k - 1][(n21 + 1) % i]);
                            class99.method2594((byte)1, n20, (short)(-1), b, false, array2[k][n21], array2[-1 + k][n21], (byte)(-1), array2[k][(1 + n21) % i]);
                        }
                        else {
                            class99.method2594((byte)1, n20, (short)(-1), b, false, array2[0][n21], method1779, (byte)(-1), array2[0][(n21 + 1) % i]);
                        }
                    }
                }
                method1778 = ha.method1790(class99, method1777, za_Sub1.anInt6076, 64, 768);
                synchronized (Class299.aClass79_2493) {
                    Class299.aClass79_2493.method805(n12, method1778, (byte)(-80));
                }
            }
            final int v = class98.V();
            final int ra = class98.RA();
            final int ha2 = class98.HA();
            final int g = class98.G();
            Class98_Sub46_Sub16 method1780 = null;
            if (class97 != null) {
                n9 = class97.anIntArray818[n9];
                method1780 = Class151_Sub7.aClass183_5001.method2624(2, n9 >> 448240368);
                n9 &= 0xFFFF;
            }
            Class146 class100;
            if (method1780 != null) {
                class100 = method1778.method2341((byte)3, method1777, true);
                class100.O(-v + ra >> 1914721025, 128, -ha2 + g >> 1598124673);
                class100.H(ra + v >> 217245921, 0, ha2 - -g >> 1082256225);
                class100.method2340(method1780, 0, n9);
            }
            else {
                class100 = method1778.method2341((byte)3, method1777, true);
                class100.O(-v + ra >> -953095807, 128, -ha2 + g >> 226658273);
                class100.H(v + ra >> -379781503, 0, ha2 - -g >> -1170469311);
            }
            if (n4 != 0) {
                class100.FA(n4);
            }
            if (~n != -1) {
                class100.VA(n);
            }
            if (n11 != 0) {
                class100.H(0, n11, 0);
            }
            return class100;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.G(" + ((class97 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + ((class98 != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n10 + ',' + n11 + ')');
        }
    }
    
    static final Class102[] method1704(final int n) {
        try {
            if (n != 4) {
                return null;
            }
            return new Class102[] { Class102.aClass102_860, Class102.aClass102_862, Class102.aClass102_863, Class102.aClass102_864, Class102.aClass102_865, Class102.aClass102_866, Class102.aClass102_867, Class102.aClass102_868, Class102.aClass102_869, Class102.aClass102_870, Class102.aClass102_871, Class102.aClass102_872, Class102.aClass102_873, Class102.aClass102_874, Class102.aClass102_875, Class102.aClass102_876, Class102.aClass102_877, Class102.aClass102_878, Class102.aClass102_879, Class102.aClass102_880, Class102.aClass102_881, Class102.aClass102_882, Class102.aClass102_883, Class102.aClass102_884, Class102.aClass102_885, Class102.aClass102_886, Class102.aClass102_887 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.B(" + n + ')');
        }
    }
    
    final int method1705(final int n) {
        try {
            if (n < 59) {
                Class102.aClass102_886 = null;
            }
            return this.anInt858;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.C(" + n + ')');
        }
    }
    
    final void method1706(final int anInt858, final int n) {
        try {
            this.anInt858 = anInt858;
            if (n <= 96) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.F(" + anInt858 + ',' + n + ')');
        }
    }
    
    final void method1707(final byte b, final Interface14 anInterface14_859) {
        try {
            if (b != 1) {
                this.method1709(-10);
            }
            if (anInterface14_859.method50(15763) != this.aClass191_861) {
                throw new IllegalArgumentException();
            }
            this.anInterface14_859 = anInterface14_859;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.A(" + b + ',' + ((anInterface14_859 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method1708(final byte b) {
        try {
            Class102.aClass102_879 = null;
            Class102.aClass102_860 = null;
            Class102.aClass102_865 = null;
            Class102.aClass102_877 = null;
            Class102.aClass102_884 = null;
            if (b != 70) {
                method1708((byte)26);
            }
            Class102.aClass102_882 = null;
            Class102.aClass102_863 = null;
            Class102.aClass102_866 = null;
            Class102.aClass102_870 = null;
            Class102.aClass350_888 = null;
            Class102.aClass102_872 = null;
            Class102.aClass102_878 = null;
            Class102.aClass102_869 = null;
            Class102.aClass102_876 = null;
            Class102.aClass102_874 = null;
            Class102.aClass102_868 = null;
            Class102.aClass102_886 = null;
            Class102.aClass102_887 = null;
            Class102.aClass102_873 = null;
            Class102.aClass102_867 = null;
            Class102.aClass102_871 = null;
            Class102.aClass102_881 = null;
            Class102.aClass102_862 = null;
            Class102.aClass102_885 = null;
            Class102.aClass102_880 = null;
            Class102.aClass102_875 = null;
            Class102.aClass102_883 = null;
            Class102.aClass102_864 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.H(" + b + ')');
        }
    }
    
    final Interface14 method1709(final int n) {
        try {
            return this.anInterface14_859;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.E(" + n + ')');
        }
    }
    
    static final int method1710(final int n) {
        try {
            if (n < 45) {
                Class102.aClass102_881 = null;
            }
            if (Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540) {
                return 6;
            }
            if (Class266.aClass98_Sub46_Sub8_1994 == null) {
                return 0;
            }
            final int anInt5990 = Class266.aClass98_Sub46_Sub8_1994.anInt5990;
            if (Class98_Sub21.method1179(anInt5990, 255)) {
                return 1;
            }
            if (Class299_Sub2.method3526(123, anInt5990)) {
                return 2;
            }
            if (Class98_Sub10_Sub21.method1064(anInt5990, false)) {
                return 3;
            }
            if (Class36.method340(anInt5990, (byte)(-91))) {
                return 4;
            }
            return 5;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.D(" + n + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.toString()");
        }
    }
    
    private Class102(final Class191 aClass191_861) {
        try {
            this.aClass191_861 = aClass191_861;
            this.anInt858 = 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gh.<init>(" + ((aClass191_861 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class102.aClass102_860 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_862 = new Class102(Class191.aClass191_1476);
        Class102.aClass102_863 = new Class102(Class191.aClass191_1476);
        Class102.aClass102_864 = new Class102(Class191.aClass191_1476);
        Class102.aClass102_865 = new Class102(Class191.aClass191_1476);
        Class102.aClass102_866 = new Class102(Class191.aClass191_1476);
        Class102.aClass102_867 = new Class102(Class191.aClass191_1476);
        Class102.aClass102_868 = new Class102(Class191.aClass191_1476);
        Class102.aClass102_869 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_870 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_871 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_872 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_873 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_874 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_875 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_876 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_877 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_878 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_879 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_880 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_881 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_882 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_883 = new Class102(Class191.aClass191_1474);
        Class102.aClass102_884 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_885 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_886 = new Class102(Class191.aClass191_1473);
        Class102.aClass102_887 = new Class102(Class191.aClass191_1475);
        Class102.aClass350_888 = new Class350(2);
        Class102.aBoolean889 = false;
    }
}
