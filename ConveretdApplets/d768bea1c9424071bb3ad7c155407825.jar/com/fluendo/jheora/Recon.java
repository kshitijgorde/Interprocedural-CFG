// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public final class Recon
{
    private static final short clamp255(int n) {
        n -= 255;
        n = -(255 + (n >> 31 & n));
        return (short)(-(n >> 31 & n));
    }
    
    public static final void CopyBlock(final short[] array, final short[] array2, final int n, final int n2) {
        int n3 = n;
        for (int i = 0; i < 8; ++i) {
            array2[n3 + 0] = array[n3 + 0];
            array2[n3 + 1] = array[n3 + 1];
            array2[n3 + 2] = array[n3 + 2];
            array2[n3 + 3] = array[n3 + 3];
            array2[n3 + 4] = array[n3 + 4];
            array2[n3 + 5] = array[n3 + 5];
            array2[n3 + 6] = array[n3 + 6];
            array2[n3 + 7] = array[n3 + 7];
            n3 += n2;
        }
    }
    
    public static final void ReconIntra(final short[] array, final int n, final short[] array2, final int n2) {
        int n3 = n;
        int n4 = 0;
        for (int i = 0; i < 8; ++i) {
            array[n3 + 0] = clamp255(array2[n4++] + 128);
            array[n3 + 1] = clamp255(array2[n4++] + 128);
            array[n3 + 2] = clamp255(array2[n4++] + 128);
            array[n3 + 3] = clamp255(array2[n4++] + 128);
            array[n3 + 4] = clamp255(array2[n4++] + 128);
            array[n3 + 5] = clamp255(array2[n4++] + 128);
            array[n3 + 6] = clamp255(array2[n4++] + 128);
            array[n3 + 7] = clamp255(array2[n4++] + 128);
            n3 += n2;
        }
    }
    
    public static final void ReconInter(final short[] array, final int n, final short[] array2, final int n2, final short[] array3, final int n3) {
        int n4 = 0;
        int n5 = n;
        int n6 = n2;
        for (int i = 0; i < 8; ++i) {
            array[n5 + 0] = clamp255(array2[n6 + 0] + array3[n4++]);
            array[n5 + 1] = clamp255(array2[n6 + 1] + array3[n4++]);
            array[n5 + 2] = clamp255(array2[n6 + 2] + array3[n4++]);
            array[n5 + 3] = clamp255(array2[n6 + 3] + array3[n4++]);
            array[n5 + 4] = clamp255(array2[n6 + 4] + array3[n4++]);
            array[n5 + 5] = clamp255(array2[n6 + 5] + array3[n4++]);
            array[n5 + 6] = clamp255(array2[n6 + 6] + array3[n4++]);
            array[n5 + 7] = clamp255(array2[n6 + 7] + array3[n4++]);
            n5 += n3;
            n6 += n3;
        }
    }
    
    public static final void ReconInterHalfPixel2(final short[] array, final int n, final short[] array2, final int n2, final short[] array3, final int n3, final short[] array4, final int n4) {
        int n5 = 0;
        int n6 = n;
        int n7 = n2;
        int n8 = n3;
        for (int i = 0; i < 8; ++i) {
            array[n6 + 0] = clamp255((array2[n7 + 0] + array3[n8 + 0] >> 1) + array4[n5++]);
            array[n6 + 1] = clamp255((array2[n7 + 1] + array3[n8 + 1] >> 1) + array4[n5++]);
            array[n6 + 2] = clamp255((array2[n7 + 2] + array3[n8 + 2] >> 1) + array4[n5++]);
            array[n6 + 3] = clamp255((array2[n7 + 3] + array3[n8 + 3] >> 1) + array4[n5++]);
            array[n6 + 4] = clamp255((array2[n7 + 4] + array3[n8 + 4] >> 1) + array4[n5++]);
            array[n6 + 5] = clamp255((array2[n7 + 5] + array3[n8 + 5] >> 1) + array4[n5++]);
            array[n6 + 6] = clamp255((array2[n7 + 6] + array3[n8 + 6] >> 1) + array4[n5++]);
            array[n6 + 7] = clamp255((array2[n7 + 7] + array3[n8 + 7] >> 1) + array4[n5++]);
            n6 += n4;
            n7 += n4;
            n8 += n4;
        }
    }
}
