import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class10
{
    static Hashtable aHashtable118;
    static int anInt119;
    
    static final void method196(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            Class231.method2875(256, n3);
            int n8 = 0;
            int n9 = -n6 + n3;
            if (n9 < 0) {
                n9 = 0;
            }
            int i = n3;
            int n10 = -n3;
            int n11 = n9;
            if (n < -16) {
                int n12 = -n9;
                int n13 = -1;
                int n14 = -1;
                final int[] array = Class97.anIntArrayArray814[n7];
                final int n15 = -n9 + n4;
                final int n16 = n9 + n4;
                Class333.method3761(n5, array, n4 + -n3, n15, (byte)(-125));
                Class333.method3761(n2, array, n15, n16, (byte)116);
                Class333.method3761(n5, array, n16, n4 - -n3, (byte)53);
                while (i > n8) {
                    n13 += 2;
                    n14 += 2;
                    n12 += n14;
                    n10 += n13;
                    if (n12 >= 0 && n11 >= 1) {
                        Class331.anIntArray2810[n11] = n8;
                        --n11;
                        n12 -= n11 << 251598881;
                    }
                    ++n8;
                    if (~n10 <= -1) {
                        --i;
                        n10 -= i << 1376638529;
                        if (~i <= ~n9) {
                            final int[] array2 = Class97.anIntArrayArray814[i + n7];
                            final int[] array3 = Class97.anIntArrayArray814[n7 + -i];
                            final int n17 = n4 + n8;
                            final int n18 = n4 + -n8;
                            Class333.method3761(n5, array2, n18, n17, (byte)94);
                            Class333.method3761(n5, array3, n18, n17, (byte)93);
                        }
                        else {
                            final int[] array4 = Class97.anIntArrayArray814[i + n7];
                            final int[] array5 = Class97.anIntArrayArray814[-i + n7];
                            final int n19 = Class331.anIntArray2810[i];
                            final int n20 = n4 + n8;
                            final int n21 = -n8 + n4;
                            final int n22 = n19 + n4;
                            final int n23 = -n19 + n4;
                            Class333.method3761(n5, array4, n21, n23, (byte)(-128));
                            Class333.method3761(n2, array4, n23, n22, (byte)(-123));
                            Class333.method3761(n5, array4, n22, n20, (byte)(-127));
                            Class333.method3761(n5, array5, n21, n23, (byte)(-124));
                            Class333.method3761(n2, array5, n23, n22, (byte)(-126));
                            Class333.method3761(n5, array5, n22, n20, (byte)33);
                        }
                    }
                    final int[] array6 = Class97.anIntArrayArray814[n7 - -n8];
                    final int[] array7 = Class97.anIntArrayArray814[n7 + -n8];
                    final int n24 = i + n4;
                    final int n25 = n4 - i;
                    if (~n9 >= ~n8) {
                        Class333.method3761(n5, array6, n25, n24, (byte)100);
                        Class333.method3761(n5, array7, n25, n24, (byte)(-123));
                    }
                    else {
                        final int n26 = (~n11 <= ~n8) ? n11 : Class331.anIntArray2810[n8];
                        final int n27 = n26 + n4;
                        final int n28 = -n26 + n4;
                        Class333.method3761(n5, array6, n25, n28, (byte)(-123));
                        Class333.method3761(n2, array6, n28, n27, (byte)(-126));
                        Class333.method3761(n5, array6, n27, n24, (byte)(-123));
                        Class333.method3761(n5, array7, n25, n28, (byte)(-126));
                        Class333.method3761(n2, array7, n28, n27, (byte)44);
                        Class333.method3761(n5, array7, n27, n24, (byte)(-128));
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ao.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    static final void method197(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            for (int n7 = n3; ~n7 >= ~n4; ++n7) {
                Class333.method3761(n, Class97.anIntArrayArray814[n7], n5, n2, (byte)(-125));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ao.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    public static void method198(final byte b) {
        try {
            Class10.aHashtable118 = null;
            if (b != -47) {
                method196(21, -124, -34, -45, 31, -40, -29);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ao.B(" + b + ')');
        }
    }
    
    static {
        Class10.anInt119 = 0;
        Class10.aHashtable118 = new Hashtable();
    }
}
