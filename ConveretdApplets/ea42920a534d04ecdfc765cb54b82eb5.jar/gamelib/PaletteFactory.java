// 
// Decompiled by Procyon v0.5.30
// 

package gamelib;

import java.awt.image.IndexColorModel;

public class PaletteFactory
{
    public static final IndexColorModel createPalette(final int[][] array, final boolean b) {
        final boolean b2 = array[0].length == 5;
        final byte[] array2 = new byte[256];
        final byte[] array3 = new byte[256];
        final byte[] array4 = new byte[256];
        final byte[] array5 = (byte[])(b2 ? new byte[256] : null);
        for (int i = 0; i < array.length - 1; ++i) {
            final int n = array[i][0];
            final int n2 = array[i + 1][0];
            if (n < n2) {
                for (int j = n; j <= n2; ++j) {
                    final float n3 = (n2 - j) / (n2 - n);
                    final float n4 = 1.0f - n3;
                    array2[j] = (byte)(array[i][1] * n3 + array[i + 1][1] * n4);
                    array3[j] = (byte)(array[i][2] * n3 + array[i + 1][2] * n4);
                    array4[j] = (byte)(array[i][3] * n3 + array[i + 1][3] * n4);
                    if (b2) {
                        array5[j] = (byte)(array[i][4] * n3 + array[i + 1][4] * n4);
                    }
                }
            }
        }
        if (b2) {
            return new IndexColorModel(8, 256, array2, array3, array4, array5);
        }
        if (b) {
            return new IndexColorModel(8, 256, array2, array3, array4, 0);
        }
        return new IndexColorModel(8, 256, array2, array3, array4);
    }
}
