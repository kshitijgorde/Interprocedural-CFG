import java.awt.image.IndexColorModel;
import java.awt.image.ColorModel;
import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class ButtonFilter extends RGBImageFilter
{
    boolean pressed;
    int defpercent;
    int border;
    int width;
    int height;
    ColorModel[] models;
    ColorModel origbuttonmodel;
    
    public ButtonFilter(final boolean pressed, final int defpercent, final int border, final int width, final int height) {
        this.models = new ColorModel[7];
        this.pressed = pressed;
        this.defpercent = defpercent;
        this.border = border;
        this.width = width;
        this.height = height;
    }
    
    public void setHints(final int n) {
        super.setHints(n & 0xFFFFFFFB);
    }
    
    public void setColorModel(final ColorModel colorModel) {
        if (colorModel instanceof IndexColorModel) {
            final IndexColorModel indexColorModel = (IndexColorModel)colorModel;
            this.models[0] = this.filterIndexColorModel(indexColorModel, false, false, 0);
            this.models[1] = this.filterIndexColorModel(indexColorModel, true, !this.pressed, this.defpercent);
            this.models[2] = null;
            if (this.pressed) {
                this.models[3] = this.filterIndexColorModel(indexColorModel, true, false, this.defpercent / 2);
            }
            else {
                this.models[3] = this.models[0];
            }
            this.models[4] = null;
            this.models[5] = this.filterIndexColorModel(indexColorModel, true, this.pressed, this.defpercent);
            this.models[6] = this.models[0];
            this.origbuttonmodel = colorModel;
            super.consumer.setColorModel(this.models[3]);
            return;
        }
        super.setColorModel(colorModel);
    }
    
    public IndexColorModel filterIndexColorModel(final IndexColorModel indexColorModel, final boolean b, final boolean b2, final int n) {
        final byte[] array = new byte[256];
        final byte[] array2 = new byte[256];
        final byte[] array3 = new byte[256];
        final byte[] array4 = new byte[256];
        final int mapSize = indexColorModel.getMapSize();
        indexColorModel.getReds(array);
        indexColorModel.getGreens(array2);
        indexColorModel.getBlues(array3);
        if (b) {
            indexColorModel.getAlphas(array4);
            for (int i = 0; i < mapSize; ++i) {
                final int filterRGB = this.filterRGB(indexColorModel.getRGB(i), b2, n);
                array4[i] = (byte)(filterRGB >> 24);
                array[i] = (byte)(filterRGB >> 16);
                array2[i] = (byte)(filterRGB >> 8);
                array3[i] = (byte)filterRGB;
            }
        }
        return new IndexColorModel(indexColorModel.getPixelSize(), mapSize, array, array2, array3, array4);
    }
    
    public void buttonRanges(final int n, final int[] array) {
        array[0] = (array[1] = 0);
        if (n < this.border) {
            final int n2 = 2;
            final int n3 = 3;
            final int n4 = 4;
            final int n5 = 5;
            final int n6 = this.width - n;
            array[n4] = (array[n5] = n6);
            array[n2] = (array[n3] = n6);
        }
        else if (n > this.height - this.border) {
            final int n7 = 2;
            final int n8 = 3;
            final int n9 = 4;
            final int n10 = 5;
            final int n11 = this.height - n;
            array[n9] = (array[n10] = n11);
            array[n7] = (array[n8] = n11);
        }
        else {
            array[2] = (array[3] = this.border);
            array[4] = (array[5] = this.width - this.border);
        }
        array[6] = (array[7] = this.width);
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, int n5, final int n6) {
        if (colorModel == this.origbuttonmodel) {
            final int[] array2 = new int[8];
            final int n7 = n + n3;
            for (int i = n2; i < n2 + n4; ++i) {
                this.buttonRanges(i, array2);
                for (int j = 0; j < 7; ++j) {
                    if (n7 > array2[j] && n < array2[j + 1]) {
                        final int max = Math.max(n, array2[j]);
                        final int min = Math.min(n7, array2[j + 1]);
                        if (this.models[j] == null) {
                            super.setPixels(max, i, min - max, 1, colorModel, array, n5 + (max - n), n6);
                        }
                        else if (max < min) {
                            super.consumer.setPixels(max, i, min - max, 1, this.models[j], array, n5 + (max - n), n6);
                        }
                    }
                }
                n5 += n6;
            }
            return;
        }
        super.setPixels(n, n2, n3, n4, colorModel, array, n5, n6);
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        boolean pressed;
        int n4;
        if ((n < this.border && n2 < this.height - n) || (n2 < this.border && n < this.width - n2)) {
            pressed = !this.pressed;
            n4 = this.defpercent;
        }
        else if (n >= this.width - this.border || n2 >= this.height - this.border) {
            pressed = this.pressed;
            n4 = this.defpercent;
        }
        else {
            if (!this.pressed) {
                return n3 & 0xFFFFFF;
            }
            pressed = false;
            n4 = this.defpercent / 2;
        }
        return this.filterRGB(n3, pressed, n4);
    }
    
    public int filterRGB(final int n, final boolean b, final int n2) {
        final int n3 = n >> 16 & 0xFF;
        final int n4 = n >> 8 & 0xFF;
        final int n5 = n & 0xFF;
        int n6;
        int n7;
        int n8;
        if (b) {
            n6 = 255 - (255 - n3) * (100 - n2) / 100;
            n7 = 255 - (255 - n4) * (100 - n2) / 100;
            n8 = 255 - (255 - n5) * (100 - n2) / 100;
        }
        else {
            n6 = n3 * (100 - n2) / 100;
            n7 = n4 * (100 - n2) / 100;
            n8 = n5 * (100 - n2) / 100;
        }
        if (n6 < 0) {
            n6 = 0;
        }
        if (n7 < 0) {
            n7 = 0;
        }
        if (n8 < 0) {
            n8 = 0;
        }
        if (n6 > 255) {
            n6 = 255;
        }
        if (n7 > 255) {
            n7 = 255;
        }
        if (n8 > 255) {
            n8 = 255;
        }
        return (n & 0xFF000000) | n6 << 16 | n7 << 8 | n8;
    }
}
