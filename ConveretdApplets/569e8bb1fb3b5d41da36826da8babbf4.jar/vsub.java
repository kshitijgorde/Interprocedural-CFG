import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.ImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class vsub extends ImageFilter
{
    public void setDimensions(final int n, final int n2) {
        this.consumer.setDimensions(n, n2);
        final int n3 = 10000;
        final byte[] array = new byte[n3];
        for (int i = 0; i < n3; ++i) {
            array[i] = 0;
        }
        final int n4 = 255;
        final int n5 = 2048;
        final int[] array2 = new int[n4];
        for (int j = 0; j < n4; ++j) {
            array2[j] = -16711165 + j;
        }
        array2[1] = -16645630;
        array2[36] = -16645629;
        this.consumer.setColorModel(new IndexColorModel(8, n4, array2, 0, false, -1, 0));
        final int[] array3 = new int[n5];
        for (int k = 0; k < n4; ++k) {
            array3[k] = -16711165 + k;
        }
        array3[1] = -16645630;
        array3[36] = -16645629;
        for (int l = n4; l < n5 - 1; l += 2) {
            array3[l] = -16645629;
            array3[l + 1] = -16645630;
        }
        this.consumer.setPixels(10, 10, 10, 10, new IndexColorModel(8, n5, array3, 0, false, -1, 0), array, 1, 1);
    }
}
