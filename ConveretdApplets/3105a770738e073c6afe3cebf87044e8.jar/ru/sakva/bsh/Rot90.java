// 
// Decompiled by Procyon v0.5.30
// 

package ru.sakva.bsh;

import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

public class Rot90 extends ImageFilter
{
    int width;
    int height;
    int[] p;
    ColorModel colorModel;
    public int angle;
    public boolean asIs;
    public int back;
    public int front;
    
    public void setDimensions(final int n, final int n2) {
        if (this.angle == 90 || this.angle == -90) {
            this.width = n2;
            this.height = n;
        }
        else {
            this.width = n;
            this.height = n2;
        }
        this.p = new int[this.width * this.height];
        super.consumer.setDimensions(this.width, this.height);
    }
    
    public void imageComplete(final int n) {
        if (n != 1 && n != 4 && this.p != null) {
            super.consumer.setPixels(0, 0, this.width, this.height, this.colorModel, this.p, 0, this.width);
        }
        super.consumer.imageComplete(n);
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        for (int i = 0; i < n4; ++i) {
            int n7 = n5 + n6 * i + n;
            for (int j = 0; j < n3; ++j) {
                int n8;
                if (this.angle == -90) {
                    n8 = this.width * (this.height - n - j - 1) + (n2 + i);
                }
                else if (this.angle == 90) {
                    n8 = this.width * (n + j) + (this.width - n2 - i - 1);
                }
                else if (this.angle == 180) {
                    n8 = this.width - n - j - 1 + this.width * (this.height - n2 - i - 1);
                }
                else {
                    n8 = this.width * (n2 + i) + (n + j);
                }
                final int rgb = colorModel.getRGB(array[n7++] & 0xFF);
                if (this.asIs) {
                    this.p[n8] = rgb;
                }
                else if (rgb == -16777216) {
                    this.p[n8] = this.back;
                }
                else {
                    this.p[n8] = this.front;
                }
            }
        }
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        for (int i = 0; i < n4; ++i) {
            int n7 = n5 + n6 * i + n;
            for (int j = 0; j < n3; ++j) {
                int n8;
                if (this.angle == -90) {
                    n8 = this.width * (this.height - n - j - 1) + (n2 + i);
                }
                else if (this.angle == 90) {
                    n8 = this.width * (n + j) + (this.width - n2 - i - 1);
                }
                else if (this.angle == 180) {
                    n8 = this.width - n - j - 1 + this.width * (this.height - n2 - i - 1);
                }
                else {
                    n8 = this.width * (n2 + i) + (n + j);
                }
                final int rgb = colorModel.getRGB(array[n7++]);
                if (this.asIs) {
                    this.p[n8] = rgb;
                }
                else if (rgb == -16777216) {
                    this.p[n8] = this.back;
                }
                else {
                    this.p[n8] = this.front;
                }
            }
        }
    }
    
    public void setColorModel(final ColorModel colorModel) {
        super.consumer.setColorModel(this.colorModel);
    }
    
    public Rot90() {
        this.colorModel = ColorModel.getRGBdefault();
        this.angle = -90;
        this.asIs = true;
        this.back = -16777216;
        this.front = 268435455;
    }
}
