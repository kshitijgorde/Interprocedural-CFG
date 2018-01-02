// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

import java.awt.image.IndexColorModel;
import java.awt.image.ColorModel;
import java.awt.Rectangle;
import java.io.Serializable;
import java.awt.image.ImageFilter;

public abstract class J extends ImageFilter implements Serializable
{
    protected Rectangle F;
    protected Rectangle A;
    protected ColorModel B;
    protected int[] C;
    protected byte[] E;
    protected boolean D;
    
    public J() {
        this.D = false;
        this.B = ColorModel.getRGBdefault();
    }
    
    protected void A(final Rectangle rectangle) {
    }
    
    public void setDimensions(final int n, final int n2) {
        this.A = new Rectangle(0, 0, n, n2);
        this.A(this.F = new Rectangle(0, 0, n, n2));
        this.consumer.setDimensions(this.F.width, this.F.height);
    }
    
    public void setColorModel(final ColorModel colorModel) {
        if (this.D && colorModel instanceof IndexColorModel) {
            this.consumer.setColorModel(colorModel);
        }
        else {
            this.consumer.setColorModel(this.B);
        }
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        int n7 = n2 * this.A.width + n;
        int n8 = n5;
        final int n9 = n6 - n3;
        final int n10 = this.A.width - n3;
        if (this.D) {
            if (this.E == null) {
                this.E = new byte[this.A.width * this.A.height];
            }
            for (int i = 0; i < n4; ++i) {
                for (int j = 0; j < n3; ++j) {
                    this.E[n7++] = array[n8++];
                }
                n8 += n9;
                n7 += n10;
            }
        }
        else {
            if (this.C == null) {
                this.C = new int[this.A.width * this.A.height];
            }
            for (int k = 0; k < n4; ++k) {
                for (int l = 0; l < n3; ++l) {
                    this.C[n7++] = colorModel.getRGB(array[n8++] & 0xFF);
                }
                n8 += n9;
                n7 += n10;
            }
        }
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        int n7 = n2 * this.A.width + n;
        int n8 = n5;
        final int n9 = n6 - n3;
        final int n10 = this.A.width - n3;
        if (this.C == null) {
            this.C = new int[this.A.width * this.A.height];
        }
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                this.C[n7++] = colorModel.getRGB(array[n8++]);
            }
            n8 += n9;
            n7 += n10;
        }
    }
}
