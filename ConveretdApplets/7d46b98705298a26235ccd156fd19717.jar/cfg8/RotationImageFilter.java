// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

public class RotationImageFilter extends ImageFilter
{
    public static int a;
    
    public void setHints(final int n) {
        super.consumer.setHints(n & 0xFFFFFFF9);
    }
    
    public void setDimensions(final int n, final int n2) {
        super.consumer.setDimensions(n2, n);
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        final int a = RotationImageFilter.a;
        final byte[] array2 = new byte[array.length];
        int n7 = n3;
        int i = 0;
    Label_0092_Outer:
        while (i < n4) {
            int n8 = 0;
            if (a == 0) {
                int j = 0;
                while (true) {
                    while (j < n3) {
                        array2[n8 + i] = array[n7 - j - 1 + n5];
                        n8 += n4;
                        ++j;
                        if (a == 0) {
                            if (a != 0) {
                                break;
                            }
                            continue Label_0092_Outer;
                        }
                        else {
                            if (a != 0) {
                                break Label_0092_Outer;
                            }
                            continue Label_0092_Outer;
                        }
                    }
                    n7 += n3;
                    ++i;
                    continue;
                }
            }
            return;
        }
        super.consumer.setPixels(n2, n, n4, n3, colorModel, array2, 0, n4);
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        final int a = RotationImageFilter.a;
        final int[] array2 = new int[array.length];
        int n7 = n6;
        int i = 0;
    Label_0094_Outer:
        while (i < n4) {
            int n8 = 0;
            if (a == 0) {
                int j = 0;
                while (true) {
                    while (j < n3) {
                        array2[n8 + i] = array[n7 - j - 1 + n5];
                        n8 += n4;
                        ++j;
                        if (a == 0) {
                            if (a != 0) {
                                break;
                            }
                            continue Label_0094_Outer;
                        }
                        else {
                            if (a != 0) {
                                break Label_0094_Outer;
                            }
                            continue Label_0094_Outer;
                        }
                    }
                    n7 += n6;
                    ++i;
                    continue;
                }
            }
            return;
        }
        super.consumer.setPixels(n2, n, n4, n3, colorModel, array2, 0, n4);
    }
}
