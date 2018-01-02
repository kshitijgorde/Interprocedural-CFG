// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jtiger;

import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.IndexColorModel;
import com.fluendo.utils.Debug;
import java.awt.image.ImageObserver;
import com.fluendo.jkate.Palette;
import com.fluendo.jkate.Bitmap;
import java.awt.Component;
import java.awt.Image;

public class TigerBitmap
{
    private Image image;
    private Image scaled_image;
    
    public TigerBitmap(final Component component, final Bitmap bitmap, final Palette palette) {
        if (bitmap == null) {
            this.image = null;
        }
        else if (bitmap.bpp == 0) {
            this.image = this.createPNGBitmap(component, bitmap, palette);
        }
        else if (palette == null) {
            this.image = null;
        }
        else {
            this.image = this.createPalettedBitmap(component, bitmap, palette);
        }
    }
    
    public Image getScaled(final int n, final int n2) {
        if (this.scaled_image == null || n != this.scaled_image.getWidth(null) || n2 != this.scaled_image.getHeight(null)) {
            this.scaled_image = this.image.getScaledInstance(n, n2, 4);
        }
        return this.scaled_image;
    }
    
    private Image createPNGBitmap(final Component component, final Bitmap bitmap, final Palette palette) {
        Debug.warning("PNG bitmaps not supported yet");
        return null;
    }
    
    private Image createPalettedBitmap(final Component component, final Bitmap bitmap, final Palette palette) {
        final byte[] array = new byte[4 * palette.colors.length];
        for (int i = 0; i < palette.colors.length; ++i) {
            array[i * 4 + 0] = palette.colors[i].r;
            array[i * 4 + 1] = palette.colors[i].g;
            array[i * 4 + 2] = palette.colors[i].b;
            array[i * 4 + 3] = palette.colors[i].a;
        }
        return component.createImage(new MemoryImageSource(bitmap.width, bitmap.height, new IndexColorModel(bitmap.bpp, palette.colors.length, array, 0, true), bitmap.pixels, 0, bitmap.width));
    }
}
