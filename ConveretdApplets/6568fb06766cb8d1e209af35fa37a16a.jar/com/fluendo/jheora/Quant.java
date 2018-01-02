// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Buffer;

public class Quant
{
    private static int ilog(long n) {
        int n2 = 0;
        while (n != 0L) {
            ++n2;
            n >>= 1;
        }
        return n2;
    }
    
    public static int readQTables(final Info info, final Buffer buffer) {
        final int[][] array = new int[2][3];
        final int[][][] array2 = new int[2][3][63];
        final int[][][] array3 = new int[2][3][64];
        final long n = buffer.readB(4) + 1L;
        for (int i = 0; i < 64; ++i) {
            final long n2 = buffer.readB((int)n);
            if (n < 0L) {
                return -20;
            }
            info.AcScaleFactorTable[i] = (int)n2;
        }
        final long n3 = buffer.readB(4) + 1L;
        for (int j = 0; j < 64; ++j) {
            final long n4 = buffer.readB((int)n3);
            if (n3 < 0L) {
                return -20;
            }
            info.DcScaleFactorTable[j] = (short)n4;
        }
        int b = buffer.readB(9);
        if (++b > 384) {
            return -20;
        }
        info.MaxQMatrixIndex = b;
        info.qmats = new short[b * 64];
        for (int k = 0; k < b; ++k) {
            for (int l = 0; l < 64; ++l) {
                final long n5 = buffer.readB(8);
                if (n3 < 0L) {
                    return -20;
                }
                info.qmats[(k << 6) + l] = (short)n5;
            }
        }
        for (int n6 = 0; n6 <= 1; ++n6) {
            for (int n7 = 0; n7 <= 2; ++n7) {
                int b2;
                if (n6 > 0 || n7 > 0) {
                    b2 = buffer.readB(1);
                }
                else {
                    b2 = 1;
                }
                if (b2 == 0) {
                    int b3;
                    if (n6 > 0) {
                        b3 = buffer.readB(1);
                    }
                    else {
                        b3 = 0;
                    }
                    int n8;
                    int n9;
                    if (b3 == 1) {
                        n8 = n6 - 1;
                        n9 = n7;
                    }
                    else {
                        n8 = (3 * n6 + n7 - 1) / 3;
                        n9 = (n7 + 2) % 3;
                    }
                    array[n6][n7] = array[n8][n9];
                    array2[n6][n7] = array2[n8][n9];
                    array3[n6][n7] = array3[n8][n9];
                }
                else {
                    int n10 = 0;
                    int n11 = 0;
                    array3[n6][n7][n10] = buffer.readB(ilog(b - 1));
                    if (array3[n6][n7][n10] >= b) {
                        System.out.println("bad header (1)");
                        return -20;
                    }
                    do {
                        array2[n6][n7][n10] = buffer.readB(ilog(62 - n11)) + 1;
                        n11 += array2[n6][n7][n10];
                        ++n10;
                        array3[n6][n7][n10] = buffer.readB(ilog(b - 1));
                    } while (n11 < 63);
                    if (n11 > 63) {
                        System.out.println("bad header (2): " + n11);
                        return -20;
                    }
                    array[n6][n7] = n10;
                }
            }
        }
        for (int n12 = 0; n12 < 2; ++n12) {
            for (int n13 = 0; n13 < 3; ++n13) {
                for (int n14 = 0; n14 < 64; ++n14) {
                    final short[] compQuantMatrix = compQuantMatrix(info.AcScaleFactorTable, info.DcScaleFactorTable, info.qmats, array, array2, array3, n12, n13, n14);
                    for (int n15 = 0; n15 < 64; ++n15) {
                        info.dequant_tables[n12][n13][n14][n15] = compQuantMatrix[Constants.dequant_index[n15]];
                    }
                }
            }
        }
        return 0;
    }
    
    static short[] compQuantMatrix(final int[] array, final short[] array2, final short[] array3, final int[][] array4, final int[][][] array5, final int[][][] array6, final int n, final int n2, final int n3) {
        final short[] array7 = new short[64];
        int i;
        for (i = 0; i < 63; ++i) {
            int n4 = 0;
            for (int j = 0; j < i; ++j) {
                n4 += array5[n][n2][j];
            }
            int n5 = 0;
            for (int k = 0; k <= i; ++k) {
                n5 += array5[n][n2][k];
            }
            if (n3 >= n4 && n3 <= n5) {
                break;
            }
        }
        int n6 = 0;
        for (int l = 0; l < i; ++l) {
            n6 += array5[n][n2][l];
        }
        int n7 = 0;
        for (int n8 = 0; n8 <= i; ++n8) {
            n7 += array5[n][n2][n8];
        }
        final int n9 = array6[n][n2][i];
        final int n10 = array6[n][n2][i + 1];
        final int[] array8 = new int[64];
        for (int n11 = 0; n11 < 64; ++n11) {
            array8[n11] = (2 * (n7 - n3) * array3[(n9 << 6) + n11] + 2 * (n3 - n6) * array3[(n10 << 6) + n11] + array5[n][n2][i]) / (2 * array5[n][n2][i]);
            int n12;
            if (n11 == 0 && n == 0) {
                n12 = 16;
            }
            else if (n11 > 0 && n == 0) {
                n12 = 8;
            }
            else if (n11 == 0 && n == 1) {
                n12 = 32;
            }
            else {
                n12 = 16;
            }
            int n13;
            if (n11 == 0) {
                n13 = array2[n3];
            }
            else {
                n13 = array[n3];
            }
            array7[n11] = (short)Math.max(n12, Math.min(n13 * array8[n11] / 100 * 4, 4096));
        }
        return array7;
    }
}
