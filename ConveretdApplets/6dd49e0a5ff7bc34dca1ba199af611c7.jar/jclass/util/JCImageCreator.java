// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Component;

public class JCImageCreator
{
    protected int curRow;
    protected int width;
    protected int height;
    protected int[] colorMap;
    protected int[] pixels;
    protected Component comp;
    
    public JCImageCreator(final Component component, final int n, final int n2) {
        this(component);
        this.setSize(n, n2);
    }
    
    public JCImageCreator(final Component comp) {
        (this.colorMap = new int[256])[32] = 0;
        this.comp = comp;
    }
    
    public synchronized void clear() {
        if (this.width != 0 && this.height != 0) {
            this.pixels = new int[this.width * this.height];
        }
        this.curRow = 0;
    }
    
    public synchronized Image create() {
        if (this.pixels == null) {
            return null;
        }
        final Image image = this.comp.createImage(new MemoryImageSource(this.width, this.height, this.pixels, 0, this.width));
        return JCUtilConverter.waitForImage(this.comp, image) ? image : null;
    }
    
    public synchronized Image create(final String[] pixels) {
        this.clear();
        this.setPixels(pixels);
        return this.create();
    }
    
    public synchronized void setColor(final char c, final int n) {
        if (c < '\u0100') {
            this.colorMap[c] = n;
        }
    }
    
    public synchronized void setColor(final char c, final Color color) {
        this.setColor(c, color.getRGB());
    }
    
    public synchronized void setSize(final int width, final int height) {
        if (width == this.width && height == this.height) {
            return;
        }
        this.width = width;
        this.height = height;
        if (width == 0 && height == 0) {
            this.pixels = null;
        }
        else {
            this.pixels = new int[width * height];
        }
    }
    
    public synchronized void setPixels(final String[] array) {
        for (int n = 0; n < array.length && n < this.height; ++n) {
            this.setPixels(this.curRow++, array[n]);
        }
    }
    
    public synchronized void setPixels(final String s) {
        this.setPixels(this.curRow++, s);
    }
    
    public synchronized void setPixels(final int n, final String s) {
        if (s.length() != this.width) {
            return;
        }
        int i = 0;
        final int n2 = n * this.width;
        while (i < this.width) {
            this.pixels[i + n2] = this.colorMap[s.charAt(i)];
            ++i;
        }
    }
}
