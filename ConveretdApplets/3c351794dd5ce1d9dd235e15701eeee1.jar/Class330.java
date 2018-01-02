// 
// Decompiled by Procyon v0.5.30
// 

final class Class330
{
    private static Class331 aClass331_2774;
    
    public static void method3714() {
        Class330.aClass331_2774 = null;
    }
    
    private static final void method3715(final int[] array, final int[] array2, final int[] array3, final byte[] array4, final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = n; i <= n2; ++i) {
            for (int j = 0; j < n3; ++j) {
                if (array4[j] == i) {
                    array3[n4] = j;
                    ++n4;
                }
            }
        }
        for (int k = 0; k < 23; ++k) {
            array2[k] = 0;
        }
        for (int l = 0; l < n3; ++l) {
            final byte b = (byte)(array4[l] + 1);
            ++array2[b];
        }
        for (int n5 = 1; n5 < 23; ++n5) {
            final int n6 = n5;
            array2[n6] += array2[n5 - 1];
        }
        for (int n7 = 0; n7 < 23; ++n7) {
            array[n7] = 0;
        }
        int n8 = 0;
        for (int n9 = n; n9 <= n2; ++n9) {
            final int n10 = n8 + (array2[n9 + 1] - array2[n9]);
            array[n9] = n10 - 1;
            n8 = n10 << 1;
        }
        for (int n11 = n + 1; n11 <= n2; ++n11) {
            array2[n11] = (array[n11 - 1] + 1 << 1) - array2[n11];
        }
    }
    
    static final int method3716(final byte[] aByteArray2775, int anInt2785, final byte[] aByteArray2776, final int n, final int anInt2786) {
        synchronized (Class330.aClass331_2774) {
            Class330.aClass331_2774.aByteArray2799 = aByteArray2776;
            Class330.aClass331_2774.anInt2801 = anInt2786;
            Class330.aClass331_2774.aByteArray2775 = aByteArray2775;
            Class330.aClass331_2774.anInt2808 = 0;
            Class330.aClass331_2774.anInt2785 = anInt2785;
            Class330.aClass331_2774.anInt2779 = 0;
            Class330.aClass331_2774.anInt2777 = 0;
            Class330.aClass331_2774.anInt2798 = 0;
            Class330.aClass331_2774.anInt2789 = 0;
            method3717(Class330.aClass331_2774);
            anInt2785 -= Class330.aClass331_2774.anInt2785;
            Class330.aClass331_2774.aByteArray2799 = null;
            Class330.aClass331_2774.aByteArray2775 = null;
            return anInt2785;
        }
    }
    
    private static final void method3717(final Class331 class331) {
        int n = 0;
        int[] array = null;
        int[] array2 = null;
        int[] array3 = null;
        class331.anInt2778 = 1;
        if (Class329.anIntArray2771 == null) {
            Class329.anIntArray2771 = new int[class331.anInt2778 * 100000];
        }
        int n2 = 1;
        while (n2 != 0 && method3718(class331) != 23) {
            method3718(class331);
            method3718(class331);
            method3718(class331);
            method3718(class331);
            method3718(class331);
            method3718(class331);
            method3718(class331);
            method3718(class331);
            method3718(class331);
            method3721(class331);
            class331.anInt2809 = 0;
            class331.anInt2809 = (class331.anInt2809 << 8 | (method3718(class331) & 0xFF));
            class331.anInt2809 = (class331.anInt2809 << 8 | (method3718(class331) & 0xFF));
            class331.anInt2809 = (class331.anInt2809 << 8 | (method3718(class331) & 0xFF));
            for (int i = 0; i < 16; ++i) {
                if (method3721(class331) == 1) {
                    class331.aBooleanArray2780[i] = true;
                }
                else {
                    class331.aBooleanArray2780[i] = false;
                }
            }
            for (int j = 0; j < 256; ++j) {
                class331.aBooleanArray2782[j] = false;
            }
            for (int k = 0; k < 16; ++k) {
                if (class331.aBooleanArray2780[k]) {
                    for (int l = 0; l < 16; ++l) {
                        if (method3721(class331) == 1) {
                            class331.aBooleanArray2782[k * 16 + l] = true;
                        }
                    }
                }
            }
            method3722(class331);
            final int n3 = class331.anInt2805 + 2;
            final int method3720 = method3720(3, class331);
            final int method3721 = method3720(15, class331);
            for (int n4 = 0; n4 < method3721; ++n4) {
                int n5 = 0;
                while (method3721(class331) != 0) {
                    ++n5;
                }
                class331.aByteArray2783[n4] = (byte)n5;
            }
            final byte[] array4 = new byte[6];
            for (byte b = 0; b < method3720; ++b) {
                array4[b] = b;
            }
            for (int n6 = 0; n6 < method3721; ++n6) {
                byte b2 = class331.aByteArray2783[n6];
                final byte b3 = array4[b2];
                while (b2 > 0) {
                    array4[b2] = array4[b2 - 1];
                    --b2;
                }
                array4[0] = b3;
                class331.aByteArray2776[n6] = b3;
            }
            for (byte b4 = 0; b4 < method3720; ++b4) {
                int method3722 = method3720(5, class331);
                for (int n7 = 0; n7 < n3; ++n7) {
                    while (method3721(class331) != 0) {
                        if (method3721(class331) == 0) {
                            ++method3722;
                        }
                        else {
                            --method3722;
                        }
                    }
                    class331.aByteArrayArray2804[b4][n7] = (byte)method3722;
                }
            }
            for (byte b5 = 0; b5 < method3720; ++b5) {
                byte b6 = 32;
                byte b7 = 0;
                for (int n8 = 0; n8 < n3; ++n8) {
                    if (class331.aByteArrayArray2804[b5][n8] > b7) {
                        b7 = class331.aByteArrayArray2804[b5][n8];
                    }
                    if (class331.aByteArrayArray2804[b5][n8] < b6) {
                        b6 = class331.aByteArrayArray2804[b5][n8];
                    }
                }
                method3715(class331.anIntArrayArray2806[b5], class331.anIntArrayArray2784[b5], class331.anIntArrayArray2790[b5], class331.aByteArrayArray2804[b5], b6, b7, n3);
                class331.anIntArray2791[b5] = b6;
            }
            final int n9 = class331.anInt2805 + 1;
            int n10 = -1;
            int n11 = 0;
            for (int n12 = 0; n12 <= 255; ++n12) {
                class331.anIntArray2794[n12] = 0;
            }
            int n13 = 4095;
            for (int n14 = 15; n14 >= 0; --n14) {
                for (int n15 = 15; n15 >= 0; --n15) {
                    class331.aByteArray2796[n13] = (byte)(n14 * 16 + n15);
                    --n13;
                }
                class331.anIntArray2781[n14] = n13 + 1;
            }
            int anInt2797 = 0;
            if (n11 == 0) {
                ++n10;
                n11 = 50;
                final byte b8 = class331.aByteArray2776[n10];
                n = class331.anIntArray2791[b8];
                array = class331.anIntArrayArray2806[b8];
                array3 = class331.anIntArrayArray2790[b8];
                array2 = class331.anIntArrayArray2784[b8];
            }
            --n11;
            int n16;
            int method3723;
            for (n16 = n, method3723 = method3720(n16, class331); method3723 > array[n16]; ++n16, method3723 = (method3723 << 1 | method3721(class331))) {}
            int n34;
            int method3725;
            for (int n17 = array3[method3723 - array2[n16]]; n17 != n9; n17 = array3[method3725 - array2[n34]]) {
                if (n17 == 0 || n17 == 1) {
                    int n18 = -1;
                    int n19 = 1;
                    do {
                        if (n17 == 0) {
                            n18 += n19;
                        }
                        else if (n17 == 1) {
                            n18 += 2 * n19;
                        }
                        n19 *= 2;
                        if (n11 == 0) {
                            ++n10;
                            n11 = 50;
                            final byte b9 = class331.aByteArray2776[n10];
                            n = class331.anIntArray2791[b9];
                            array = class331.anIntArrayArray2806[b9];
                            array3 = class331.anIntArrayArray2790[b9];
                            array2 = class331.anIntArrayArray2784[b9];
                        }
                        --n11;
                        int n20;
                        int method3724;
                        for (n20 = n, method3724 = method3720(n20, class331); method3724 > array[n20]; ++n20, method3724 = (method3724 << 1 | method3721(class331))) {}
                        n17 = array3[method3724 - array2[n20]];
                    } while (n17 == 0 || n17 == 1);
                    ++n18;
                    final byte b10 = class331.aByteArray2795[class331.aByteArray2796[class331.anIntArray2781[0]] & 0xFF];
                    final int[] anIntArray2794 = class331.anIntArray2794;
                    final int n21 = b10 & 0xFF;
                    anIntArray2794[n21] += n18;
                    while (n18 > 0) {
                        Class329.anIntArray2771[anInt2797] = (b10 & 0xFF);
                        ++anInt2797;
                        --n18;
                    }
                }
                else {
                    int n22 = n17 - 1;
                    byte b11;
                    if (n22 < 16) {
                        final int n23 = class331.anIntArray2781[0];
                        b11 = class331.aByteArray2796[n23 + n22];
                        while (n22 > 3) {
                            final int n24 = n23 + n22;
                            class331.aByteArray2796[n24] = class331.aByteArray2796[n24 - 1];
                            class331.aByteArray2796[n24 - 1] = class331.aByteArray2796[n24 - 2];
                            class331.aByteArray2796[n24 - 2] = class331.aByteArray2796[n24 - 3];
                            class331.aByteArray2796[n24 - 3] = class331.aByteArray2796[n24 - 4];
                            n22 -= 4;
                        }
                        while (n22 > 0) {
                            class331.aByteArray2796[n23 + n22] = class331.aByteArray2796[n23 + n22 - 1];
                            --n22;
                        }
                        class331.aByteArray2796[n23] = b11;
                    }
                    else {
                        int n25 = n22 / 16;
                        int n26 = class331.anIntArray2781[n25] + n22 % 16;
                        b11 = class331.aByteArray2796[n26];
                        while (n26 > class331.anIntArray2781[n25]) {
                            class331.aByteArray2796[n26] = class331.aByteArray2796[n26 - 1];
                            --n26;
                        }
                        final int[] anIntArray2795 = class331.anIntArray2781;
                        final int n27 = n25;
                        ++anIntArray2795[n27];
                        while (n25 > 0) {
                            final int[] anIntArray2796 = class331.anIntArray2781;
                            final int n28 = n25;
                            --anIntArray2796[n28];
                            class331.aByteArray2796[class331.anIntArray2781[n25]] = class331.aByteArray2796[class331.anIntArray2781[n25 - 1] + 16 - 1];
                            --n25;
                        }
                        final int[] anIntArray2797 = class331.anIntArray2781;
                        final int n29 = 0;
                        --anIntArray2797[n29];
                        class331.aByteArray2796[class331.anIntArray2781[0]] = b11;
                        if (class331.anIntArray2781[0] == 0) {
                            int n30 = 4095;
                            for (int n31 = 15; n31 >= 0; --n31) {
                                for (int n32 = 15; n32 >= 0; --n32) {
                                    class331.aByteArray2796[n30] = class331.aByteArray2796[class331.anIntArray2781[n31] + n32];
                                    --n30;
                                }
                                class331.anIntArray2781[n31] = n30 + 1;
                            }
                        }
                    }
                    final int[] anIntArray2798 = class331.anIntArray2794;
                    final int n33 = class331.aByteArray2795[b11 & 0xFF] & 0xFF;
                    ++anIntArray2798[n33];
                    Class329.anIntArray2771[anInt2797] = (class331.aByteArray2795[b11 & 0xFF] & 0xFF);
                    ++anInt2797;
                    if (n11 == 0) {
                        ++n10;
                        n11 = 50;
                        final byte b12 = class331.aByteArray2776[n10];
                        n = class331.anIntArray2791[b12];
                        array = class331.anIntArrayArray2806[b12];
                        array3 = class331.anIntArrayArray2790[b12];
                        array2 = class331.anIntArrayArray2784[b12];
                    }
                    --n11;
                    for (n34 = n, method3725 = method3720(n34, class331); method3725 > array[n34]; ++n34, method3725 = (method3725 << 1 | method3721(class331))) {}
                }
            }
            class331.anInt2788 = 0;
            class331.aByte2787 = 0;
            class331.anIntArray2807[0] = 0;
            for (int n35 = 1; n35 <= 256; ++n35) {
                class331.anIntArray2807[n35] = class331.anIntArray2794[n35 - 1];
            }
            for (int n36 = 1; n36 <= 256; ++n36) {
                final int[] anIntArray2799 = class331.anIntArray2807;
                final int n37 = n36;
                anIntArray2799[n37] += class331.anIntArray2807[n36 - 1];
            }
            for (int n38 = 0; n38 < anInt2797; ++n38) {
                final byte b13 = (byte)(Class329.anIntArray2771[n38] & 0xFF);
                final int[] anIntArray2800 = Class329.anIntArray2771;
                final int n39 = class331.anIntArray2807[b13 & 0xFF];
                anIntArray2800[n39] |= n38 << 8;
                final int[] anIntArray2801 = class331.anIntArray2807;
                final int n40 = b13 & 0xFF;
                ++anIntArray2801[n40];
            }
            class331.anInt2802 = Class329.anIntArray2771[class331.anInt2809] >> 8;
            class331.anInt2793 = 0;
            class331.anInt2802 = Class329.anIntArray2771[class331.anInt2802];
            class331.anInt2786 = (byte)(class331.anInt2802 & 0xFF);
            class331.anInt2802 >>= 8;
            ++class331.anInt2793;
            class331.anInt2797 = anInt2797;
            method3719(class331);
            if (class331.anInt2793 == class331.anInt2797 + 1 && class331.anInt2788 == 0) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
        }
    }
    
    private static final byte method3718(final Class331 class331) {
        return (byte)method3720(8, class331);
    }
    
    private static final void method3719(final Class331 class331) {
        byte aByte2787 = class331.aByte2787;
        int anInt2788 = class331.anInt2788;
        int i = class331.anInt2793;
        int anInt2789 = class331.anInt2786;
        final int[] anIntArray2771 = Class329.anIntArray2771;
        int anInt2790 = class331.anInt2802;
        final byte[] aByteArray2775 = class331.aByteArray2775;
        int anInt2791 = class331.anInt2808;
        final int anInt2792;
        int j = anInt2792 = class331.anInt2785;
        final int n = class331.anInt2797 + 1;
    Label_0373:
        while (true) {
            Label_0122: {
                if (anInt2788 > 0) {
                    while (j != 0) {
                        if (anInt2788 == 1) {
                            if (j == 0) {
                                anInt2788 = 1;
                                break;
                            }
                            aByteArray2775[anInt2791] = aByte2787;
                            ++anInt2791;
                            --j;
                            break Label_0122;
                        }
                        else {
                            aByteArray2775[anInt2791] = aByte2787;
                            --anInt2788;
                            ++anInt2791;
                            --j;
                        }
                    }
                    break;
                }
            }
            while (i != n) {
                aByte2787 = (byte)anInt2789;
                final int n2 = anIntArray2771[anInt2790];
                final byte b = (byte)n2;
                anInt2790 = n2 >> 8;
                ++i;
                if (b != anInt2789) {
                    anInt2789 = b;
                    if (j == 0) {
                        anInt2788 = 1;
                        break Label_0373;
                    }
                    aByteArray2775[anInt2791] = aByte2787;
                    ++anInt2791;
                    --j;
                }
                else {
                    if (i != n) {
                        anInt2788 = 2;
                        final int n3 = anIntArray2771[anInt2790];
                        final byte b2 = (byte)n3;
                        anInt2790 = n3 >> 8;
                        if (++i != n) {
                            if (b2 != anInt2789) {
                                anInt2789 = b2;
                            }
                            else {
                                anInt2788 = 3;
                                final int n4 = anIntArray2771[anInt2790];
                                final byte b3 = (byte)n4;
                                anInt2790 = n4 >> 8;
                                if (++i != n) {
                                    if (b3 != anInt2789) {
                                        anInt2789 = b3;
                                    }
                                    else {
                                        final int n5 = anIntArray2771[anInt2790];
                                        final byte b4 = (byte)n5;
                                        final int n6 = n5 >> 8;
                                        ++i;
                                        anInt2788 = (b4 & 0xFF) + 4;
                                        final int n7 = anIntArray2771[n6];
                                        anInt2789 = (byte)n7;
                                        anInt2790 = n7 >> 8;
                                        ++i;
                                    }
                                }
                            }
                        }
                        continue Label_0373;
                    }
                    if (j == 0) {
                        anInt2788 = 1;
                        break Label_0373;
                    }
                    aByteArray2775[anInt2791] = aByte2787;
                    ++anInt2791;
                    --j;
                }
            }
            anInt2788 = 0;
            break;
        }
        final int anInt2793 = class331.anInt2789;
        class331.anInt2789 += anInt2792 - j;
        class331.aByte2787 = aByte2787;
        class331.anInt2788 = anInt2788;
        class331.anInt2793 = i;
        class331.anInt2786 = anInt2789;
        Class329.anIntArray2771 = anIntArray2771;
        class331.anInt2802 = anInt2790;
        class331.aByteArray2775 = aByteArray2775;
        class331.anInt2808 = anInt2791;
        class331.anInt2785 = j;
    }
    
    private static final int method3720(final int n, final Class331 class331) {
        while (class331.anInt2779 < n) {
            class331.anInt2777 = (class331.anInt2777 << 8 | (class331.aByteArray2799[class331.anInt2801] & 0xFF));
            class331.anInt2779 += 8;
            ++class331.anInt2801;
            ++class331.anInt2798;
        }
        final int n2 = class331.anInt2777 >> class331.anInt2779 - n & (1 << n) - 1;
        class331.anInt2779 -= n;
        return n2;
    }
    
    private static final byte method3721(final Class331 class331) {
        return (byte)method3720(1, class331);
    }
    
    private static final void method3722(final Class331 class331) {
        class331.anInt2805 = 0;
        for (int i = 0; i < 256; ++i) {
            if (class331.aBooleanArray2782[i]) {
                class331.aByteArray2795[class331.anInt2805] = (byte)i;
                ++class331.anInt2805;
            }
        }
    }
    
    static {
        Class330.aClass331_2774 = new Class331();
    }
}
