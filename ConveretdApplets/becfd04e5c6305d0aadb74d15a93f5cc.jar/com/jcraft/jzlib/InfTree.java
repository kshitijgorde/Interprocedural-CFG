// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jzlib;

final class InfTree
{
    private static final int MANY = 1440;
    private static final int Z_OK = 0;
    private static final int Z_STREAM_END = 1;
    private static final int Z_NEED_DICT = 2;
    private static final int Z_ERRNO = -1;
    private static final int Z_STREAM_ERROR = -2;
    private static final int Z_DATA_ERROR = -3;
    private static final int Z_MEM_ERROR = -4;
    private static final int Z_BUF_ERROR = -5;
    private static final int Z_VERSION_ERROR = -6;
    static final int fixed_bl = 9;
    static final int fixed_bd = 5;
    static final int[] fixed_tl;
    static final int[] fixed_td;
    static final int[] cplens;
    static final int[] cplext;
    static final int[] cpdist;
    static final int[] cpdext;
    static final int BMAX = 15;
    
    static int inflate_trees_dynamic(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final ZStream zStream) {
        final int[] array7 = { 0 };
        final int[] array8 = new int[288];
        int huft_build = huft_build(array, 0, n, 257, InfTree.cplens, InfTree.cplext, array4, array2, array6, array7, array8);
        if (huft_build != 0 || array2[0] == 0) {
            if (huft_build == -3) {
                zStream.msg = "oversubscribed literal/length tree";
            }
            else if (huft_build != -4) {
                zStream.msg = "incomplete literal/length tree";
                huft_build = -3;
            }
            return huft_build;
        }
        int huft_build2 = huft_build(array, n, n2, 0, InfTree.cpdist, InfTree.cpdext, array5, array3, array6, array7, array8);
        if (huft_build2 != 0 || (array3[0] == 0 && n > 257)) {
            if (huft_build2 == -3) {
                zStream.msg = "oversubscribed distance tree";
            }
            else if (huft_build2 == -5) {
                zStream.msg = "incomplete distance tree";
                huft_build2 = -3;
            }
            else if (huft_build2 != -4) {
                zStream.msg = "empty distance tree with lengths";
                huft_build2 = -3;
            }
            return huft_build2;
        }
        return 0;
    }
    
    static int inflate_trees_fixed(final int[] array, final int[] array2, final int[][] array3, final int[][] array4, final ZStream zStream) {
        array[0] = 9;
        array2[0] = 5;
        array3[0] = InfTree.fixed_tl;
        array4[0] = InfTree.fixed_td;
        return 0;
    }
    
    static {
        fixed_tl = new int[] { 96, 7, 256, 0, 8, 80, 0, 8, 16, 84, 8, 115, 82, 7, 31, 0, 8, 112, 0, 8, 48, 0, 9, 192, 80, 7, 10, 0, 8, 96, 0, 8, 32, 0, 9, 160, 0, 8, 0, 0, 8, 128, 0, 8, 64, 0, 9, 224, 80, 7, 6, 0, 8, 88, 0, 8, 24, 0, 9, 144, 83, 7, 59, 0, 8, 120, 0, 8, 56, 0, 9, 208, 81, 7, 17, 0, 8, 104, 0, 8, 40, 0, 9, 176, 0, 8, 8, 0, 8, 136, 0, 8, 72, 0, 9, 240, 80, 7, 4, 0, 8, 84, 0, 8, 20, 85, 8, 227, 83, 7, 43, 0, 8, 116, 0, 8, 52, 0, 9, 200, 81, 7, 13, 0, 8, 100, 0, 8, 36, 0, 9, 168, 0, 8, 4, 0, 8, 132, 0, 8, 68, 0, 9, 232, 80, 7, 8, 0, 8, 92, 0, 8, 28, 0, 9, 152, 84, 7, 83, 0, 8, 124, 0, 8, 60, 0, 9, 216, 82, 7, 23, 0, 8, 108, 0, 8, 44, 0, 9, 184, 0, 8, 12, 0, 8, 140, 0, 8, 76, 0, 9, 248, 80, 7, 3, 0, 8, 82, 0, 8, 18, 85, 8, 163, 83, 7, 35, 0, 8, 114, 0, 8, 50, 0, 9, 196, 81, 7, 11, 0, 8, 98, 0, 8, 34, 0, 9, 164, 0, 8, 2, 0, 8, 130, 0, 8, 66, 0, 9, 228, 80, 7, 7, 0, 8, 90, 0, 8, 26, 0, 9, 148, 84, 7, 67, 0, 8, 122, 0, 8, 58, 0, 9, 212, 82, 7, 19, 0, 8, 106, 0, 8, 42, 0, 9, 180, 0, 8, 10, 0, 8, 138, 0, 8, 74, 0, 9, 244, 80, 7, 5, 0, 8, 86, 0, 8, 22, 192, 8, 0, 83, 7, 51, 0, 8, 118, 0, 8, 54, 0, 9, 204, 81, 7, 15, 0, 8, 102, 0, 8, 38, 0, 9, 172, 0, 8, 6, 0, 8, 134, 0, 8, 70, 0, 9, 236, 80, 7, 9, 0, 8, 94, 0, 8, 30, 0, 9, 156, 84, 7, 99, 0, 8, 126, 0, 8, 62, 0, 9, 220, 82, 7, 27, 0, 8, 110, 0, 8, 46, 0, 9, 188, 0, 8, 14, 0, 8, 142, 0, 8, 78, 0, 9, 252, 96, 7, 256, 0, 8, 81, 0, 8, 17, 85, 8, 131, 82, 7, 31, 0, 8, 113, 0, 8, 49, 0, 9, 194, 80, 7, 10, 0, 8, 97, 0, 8, 33, 0, 9, 162, 0, 8, 1, 0, 8, 129, 0, 8, 65, 0, 9, 226, 80, 7, 6, 0, 8, 89, 0, 8, 25, 0, 9, 146, 83, 7, 59, 0, 8, 121, 0, 8, 57, 0, 9, 210, 81, 7, 17, 0, 8, 105, 0, 8, 41, 0, 9, 178, 0, 8, 9, 0, 8, 137, 0, 8, 73, 0, 9, 242, 80, 7, 4, 0, 8, 85, 0, 8, 21, 80, 8, 258, 83, 7, 43, 0, 8, 117, 0, 8, 53, 0, 9, 202, 81, 7, 13, 0, 8, 101, 0, 8, 37, 0, 9, 170, 0, 8, 5, 0, 8, 133, 0, 8, 69, 0, 9, 234, 80, 7, 8, 0, 8, 93, 0, 8, 29, 0, 9, 154, 84, 7, 83, 0, 8, 125, 0, 8, 61, 0, 9, 218, 82, 7, 23, 0, 8, 109, 0, 8, 45, 0, 9, 186, 0, 8, 13, 0, 8, 141, 0, 8, 77, 0, 9, 250, 80, 7, 3, 0, 8, 83, 0, 8, 19, 85, 8, 195, 83, 7, 35, 0, 8, 115, 0, 8, 51, 0, 9, 198, 81, 7, 11, 0, 8, 99, 0, 8, 35, 0, 9, 166, 0, 8, 3, 0, 8, 131, 0, 8, 67, 0, 9, 230, 80, 7, 7, 0, 8, 91, 0, 8, 27, 0, 9, 150, 84, 7, 67, 0, 8, 123, 0, 8, 59, 0, 9, 214, 82, 7, 19, 0, 8, 107, 0, 8, 43, 0, 9, 182, 0, 8, 11, 0, 8, 139, 0, 8, 75, 0, 9, 246, 80, 7, 5, 0, 8, 87, 0, 8, 23, 192, 8, 0, 83, 7, 51, 0, 8, 119, 0, 8, 55, 0, 9, 206, 81, 7, 15, 0, 8, 103, 0, 8, 39, 0, 9, 174, 0, 8, 7, 0, 8, 135, 0, 8, 71, 0, 9, 238, 80, 7, 9, 0, 8, 95, 0, 8, 31, 0, 9, 158, 84, 7, 99, 0, 8, 127, 0, 8, 63, 0, 9, 222, 82, 7, 27, 0, 8, 111, 0, 8, 47, 0, 9, 190, 0, 8, 15, 0, 8, 143, 0, 8, 79, 0, 9, 254, 96, 7, 256, 0, 8, 80, 0, 8, 16, 84, 8, 115, 82, 7, 31, 0, 8, 112, 0, 8, 48, 0, 9, 193, 80, 7, 10, 0, 8, 96, 0, 8, 32, 0, 9, 161, 0, 8, 0, 0, 8, 128, 0, 8, 64, 0, 9, 225, 80, 7, 6, 0, 8, 88, 0, 8, 24, 0, 9, 145, 83, 7, 59, 0, 8, 120, 0, 8, 56, 0, 9, 209, 81, 7, 17, 0, 8, 104, 0, 8, 40, 0, 9, 177, 0, 8, 8, 0, 8, 136, 0, 8, 72, 0, 9, 241, 80, 7, 4, 0, 8, 84, 0, 8, 20, 85, 8, 227, 83, 7, 43, 0, 8, 116, 0, 8, 52, 0, 9, 201, 81, 7, 13, 0, 8, 100, 0, 8, 36, 0, 9, 169, 0, 8, 4, 0, 8, 132, 0, 8, 68, 0, 9, 233, 80, 7, 8, 0, 8, 92, 0, 8, 28, 0, 9, 153, 84, 7, 83, 0, 8, 124, 0, 8, 60, 0, 9, 217, 82, 7, 23, 0, 8, 108, 0, 8, 44, 0, 9, 185, 0, 8, 12, 0, 8, 140, 0, 8, 76, 0, 9, 249, 80, 7, 3, 0, 8, 82, 0, 8, 18, 85, 8, 163, 83, 7, 35, 0, 8, 114, 0, 8, 50, 0, 9, 197, 81, 7, 11, 0, 8, 98, 0, 8, 34, 0, 9, 165, 0, 8, 2, 0, 8, 130, 0, 8, 66, 0, 9, 229, 80, 7, 7, 0, 8, 90, 0, 8, 26, 0, 9, 149, 84, 7, 67, 0, 8, 122, 0, 8, 58, 0, 9, 213, 82, 7, 19, 0, 8, 106, 0, 8, 42, 0, 9, 181, 0, 8, 10, 0, 8, 138, 0, 8, 74, 0, 9, 245, 80, 7, 5, 0, 8, 86, 0, 8, 22, 192, 8, 0, 83, 7, 51, 0, 8, 118, 0, 8, 54, 0, 9, 205, 81, 7, 15, 0, 8, 102, 0, 8, 38, 0, 9, 173, 0, 8, 6, 0, 8, 134, 0, 8, 70, 0, 9, 237, 80, 7, 9, 0, 8, 94, 0, 8, 30, 0, 9, 157, 84, 7, 99, 0, 8, 126, 0, 8, 62, 0, 9, 221, 82, 7, 27, 0, 8, 110, 0, 8, 46, 0, 9, 189, 0, 8, 14, 0, 8, 142, 0, 8, 78, 0, 9, 253, 96, 7, 256, 0, 8, 81, 0, 8, 17, 85, 8, 131, 82, 7, 31, 0, 8, 113, 0, 8, 49, 0, 9, 195, 80, 7, 10, 0, 8, 97, 0, 8, 33, 0, 9, 163, 0, 8, 1, 0, 8, 129, 0, 8, 65, 0, 9, 227, 80, 7, 6, 0, 8, 89, 0, 8, 25, 0, 9, 147, 83, 7, 59, 0, 8, 121, 0, 8, 57, 0, 9, 211, 81, 7, 17, 0, 8, 105, 0, 8, 41, 0, 9, 179, 0, 8, 9, 0, 8, 137, 0, 8, 73, 0, 9, 243, 80, 7, 4, 0, 8, 85, 0, 8, 21, 80, 8, 258, 83, 7, 43, 0, 8, 117, 0, 8, 53, 0, 9, 203, 81, 7, 13, 0, 8, 101, 0, 8, 37, 0, 9, 171, 0, 8, 5, 0, 8, 133, 0, 8, 69, 0, 9, 235, 80, 7, 8, 0, 8, 93, 0, 8, 29, 0, 9, 155, 84, 7, 83, 0, 8, 125, 0, 8, 61, 0, 9, 219, 82, 7, 23, 0, 8, 109, 0, 8, 45, 0, 9, 187, 0, 8, 13, 0, 8, 141, 0, 8, 77, 0, 9, 251, 80, 7, 3, 0, 8, 83, 0, 8, 19, 85, 8, 195, 83, 7, 35, 0, 8, 115, 0, 8, 51, 0, 9, 199, 81, 7, 11, 0, 8, 99, 0, 8, 35, 0, 9, 167, 0, 8, 3, 0, 8, 131, 0, 8, 67, 0, 9, 231, 80, 7, 7, 0, 8, 91, 0, 8, 27, 0, 9, 151, 84, 7, 67, 0, 8, 123, 0, 8, 59, 0, 9, 215, 82, 7, 19, 0, 8, 107, 0, 8, 43, 0, 9, 183, 0, 8, 11, 0, 8, 139, 0, 8, 75, 0, 9, 247, 80, 7, 5, 0, 8, 87, 0, 8, 23, 192, 8, 0, 83, 7, 51, 0, 8, 119, 0, 8, 55, 0, 9, 207, 81, 7, 15, 0, 8, 103, 0, 8, 39, 0, 9, 175, 0, 8, 7, 0, 8, 135, 0, 8, 71, 0, 9, 239, 80, 7, 9, 0, 8, 95, 0, 8, 31, 0, 9, 159, 84, 7, 99, 0, 8, 127, 0, 8, 63, 0, 9, 223, 82, 7, 27, 0, 8, 111, 0, 8, 47, 0, 9, 191, 0, 8, 15, 0, 8, 143, 0, 8, 79, 0, 9, 255 };
        fixed_td = new int[] { 80, 5, 1, 87, 5, 257, 83, 5, 17, 91, 5, 4097, 81, 5, 5, 89, 5, 1025, 85, 5, 65, 93, 5, 16385, 80, 5, 3, 88, 5, 513, 84, 5, 33, 92, 5, 8193, 82, 5, 9, 90, 5, 2049, 86, 5, 129, 192, 5, 24577, 80, 5, 2, 87, 5, 385, 83, 5, 25, 91, 5, 6145, 81, 5, 7, 89, 5, 1537, 85, 5, 97, 93, 5, 24577, 80, 5, 4, 88, 5, 769, 84, 5, 49, 92, 5, 12289, 82, 5, 13, 90, 5, 3073, 86, 5, 193, 192, 5, 24577 };
        cplens = new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19, 23, 27, 31, 35, 43, 51, 59, 67, 83, 99, 115, 131, 163, 195, 227, 258, 0, 0 };
        cplext = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 0, 112, 112 };
        cpdist = new int[] { 1, 2, 3, 4, 5, 7, 9, 13, 17, 25, 33, 49, 65, 97, 129, 193, 257, 385, 513, 769, 1025, 1537, 2049, 3073, 4097, 6145, 8193, 12289, 16385, 24577 };
        cpdext = new int[] { 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13 };
    }
    
    static int huft_build(final int[] array, final int n, int n2, final int n3, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int[] array8) {
        final int[] array9 = new int[16];
        final int[] array10 = new int[3];
        final int[] array11 = new int[15];
        final int[] array12 = new int[16];
        int n4 = 0;
        do {
            final int[] array13 = array9;
            final int n5 = array[n + n4];
            ++array13[n5];
            ++n4;
        } while (--n2 != 0);
        if (array9[0] == n2) {
            array4[0] = -1;
            return array5[0] = 0;
        }
        int n6 = array5[0];
        int i = 1;
        while (array9[i] == 0 && ++i <= 15) {}
        int j;
        if (n6 < (j = i)) {
            n6 = i;
        }
        int n7 = 15;
        while (array9[n7] == 0 && --n7 != 0) {}
        final int n8;
        if (n6 > (n8 = n7)) {
            n6 = n7;
        }
        array5[0] = n6;
        int n9 = 1 << i;
        while (i < n7) {
            final int n10;
            if ((n10 = n9 - array9[i]) < 0) {
                return -3;
            }
            ++i;
            n9 = n10 << 1;
        }
        final int n11;
        if ((n11 = n9 - array9[n7]) < 0) {
            return -3;
        }
        final int[] array14 = array9;
        final int n12 = n7;
        array14[n12] += n11;
        int n13 = array12[1] = 0;
        int n14 = 1;
        int n15 = 2;
        while (--n7 != 0) {
            n13 = (array12[n15] = n13 + array9[n14]);
            ++n15;
            ++n14;
        }
        int n16 = 0;
        int n17 = 0;
        do {
            final int n18;
            if ((n18 = array[n + n17]) != 0) {
                array8[array12[n18]++] = n16;
            }
            ++n17;
        } while (++n16 < n2);
        n2 = array12[n8];
        int n19 = array12[0] = 0;
        int n20 = 0;
        int n21 = -1;
        int n22 = -n6;
        array11[0] = 0;
        int n23 = 0;
        int n24 = 0;
        while (j <= n8) {
            int n25 = array9[j];
            while (n25-- != 0) {
                while (j > n22 + n6) {
                    ++n21;
                    n22 += n6;
                    final int n26 = n8 - n22;
                    final int n27 = (n26 > n6) ? n6 : n26;
                    int n29;
                    final int n28;
                    if ((n28 = 1 << (n29 = j - n22)) > n25 + 1) {
                        int n30 = n28 - (n25 + 1);
                        int n31 = j;
                        if (n29 < n27) {
                            int n32;
                            while (++n29 < n27 && (n32 = n30 << 1) > array9[++n31]) {
                                n30 = n32 - array9[n31];
                            }
                        }
                    }
                    n24 = 1 << n29;
                    if (array7[0] + n24 > 1440) {
                        return -3;
                    }
                    n23 = (array11[n21] = array7[0]);
                    final int n33 = 0;
                    array7[n33] += n24;
                    if (n21 != 0) {
                        array12[n21] = n19;
                        array10[0] = (byte)n29;
                        array10[1] = (byte)n6;
                        final int n34 = n19 >>> n22 - n6;
                        array10[2] = n23 - array11[n21 - 1] - n34;
                        System.arraycopy(array10, 0, array6, (array11[n21 - 1] + n34) * 3, 3);
                    }
                    else {
                        array4[0] = n23;
                    }
                }
                array10[1] = (byte)(j - n22);
                if (n20 >= n2) {
                    array10[0] = 192;
                }
                else if (array8[n20] < n3) {
                    array10[0] = (byte)((array8[n20] < 256) ? 0 : 96);
                    array10[2] = array8[n20++];
                }
                else {
                    array10[0] = (byte)(array3[array8[n20] - n3] + 16 + 64);
                    array10[2] = array2[array8[n20++] - n3];
                }
                for (int n35 = 1 << j - n22, k = n19 >>> n22; k < n24; k += n35) {
                    System.arraycopy(array10, 0, array6, (n23 + k) * 3, 3);
                }
                int n36;
                for (n36 = 1 << j - 1; (n19 & n36) != 0x0; n19 ^= n36, n36 >>>= 1) {}
                n19 ^= n36;
                for (int n37 = (1 << n22) - 1; (n19 & n37) != array12[n21]; --n21, n22 -= n6, n37 = (1 << n22) - 1) {}
            }
            ++j;
        }
        if (n11 != 0 && n8 != 1) {
            return -5;
        }
        return 0;
    }
    
    static int inflate_trees_bits(final int[] array, final int[] array2, final int[] array3, final int[] array4, final ZStream zStream) {
        int huft_build = huft_build(array, 0, 19, 19, null, null, array3, array2, array4, new int[1], new int[19]);
        if (huft_build == -3) {
            zStream.msg = "oversubscribed dynamic bit lengths tree";
        }
        else if (huft_build == -5 || array2[0] == 0) {
            zStream.msg = "incomplete dynamic bit lengths tree";
            huft_build = -3;
        }
        return huft_build;
    }
}
