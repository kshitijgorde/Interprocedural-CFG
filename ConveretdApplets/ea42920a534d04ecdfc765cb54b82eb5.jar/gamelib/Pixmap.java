// 
// Decompiled by Procyon v0.5.30
// 

package gamelib;

import java.awt.Rectangle;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.Image;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;

public class Pixmap implements ImageObserver
{
    public static SimpleRandom gen;
    public int w;
    public int h;
    private MemoryImageSource source;
    private byte[] pix;
    private int bpr;
    
    static {
        Pixmap.gen = new SimpleRandom();
    }
    
    public Pixmap(final int w, final int h) {
        this.w = w;
        this.h = h;
        this.bpr = (int)Math.ceil(this.w / 4.0) * 4;
        this.pix = new byte[this.bpr * this.h];
    }
    
    public final Image createImage(final IndexColorModel indexColorModel, final boolean animated) {
        (this.source = new MemoryImageSource(this.w, this.h, indexColorModel, this.pix, 0, this.bpr)).setAnimated(animated);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final Image image = defaultToolkit.createImage(this.source);
        defaultToolkit.prepareImage(image, -1, -1, this);
        return image;
    }
    
    public final void fillRandom() {
        this.setBoundedPix(0, 0, Pixmap.gen.nextInt(this.w / 2) + 127);
        this.setBoundedPix(this.w - 1, 0, Pixmap.gen.nextInt(this.h / 2) + 127);
        this.setBoundedPix(0, this.h - 1, Pixmap.gen.nextInt(this.w / 2) + 127);
        this.setBoundedPix(this.w - 1, this.h - 1, Pixmap.gen.nextInt(this.h / 2) + 127);
        this.renderPattern(0, 0, this.w - 1, this.h - 1);
    }
    
    public final int getPix(final int n, final int n2) {
        final byte b = this.pix[n + n2 * this.bpr];
        return (b >= 0) ? b : (b + 256);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return n5 == this.h;
    }
    
    public final void mixPix(final int n, final int n2, final int n3) {
        final byte b = this.pix[n + n2 * this.bpr];
        final int n4 = (((b >= 0) ? b : (b + 256)) + n3) / 2;
        this.pix[n + n2 * this.bpr] = (byte)((n4 > 1) ? ((n4 < 255) ? n4 : 255) : 1);
    }
    
    private final void renderPattern(final int n, final int n2, final int n3, final int n4) {
        if (n3 - n <= 1 && n4 - n2 <= 1) {
            return;
        }
        final int n5 = (n + n3) / 2;
        final int n6 = (n2 + n4) / 2;
        this.setBoundedPix(n5, n2, (this.getPix(n, n2) + this.getPix(n3, n2)) / 2);
        this.setBoundedPix(n3, n6, (this.getPix(n3, n2) + this.getPix(n3, n4)) / 2);
        this.setBoundedPix(n5, n4, (this.getPix(n, n4) + this.getPix(n3, n4)) / 2);
        this.setBoundedPix(n, n6, (this.getPix(n, n2) + this.getPix(n, n4)) / 2);
        this.setBoundedPix(n5, n6, (this.getPix(n, n2) + this.getPix(n3, n2) + this.getPix(n, n4) + this.getPix(n3, n4)) / 4 + Pixmap.gen.nextInt() % (n3 - n));
        this.renderPattern(n, n2, n5, n6);
        this.renderPattern(n, n6, n5, n4);
        this.renderPattern(n5, n2, n3, n6);
        this.renderPattern(n5, n6, n3, n4);
    }
    
    public final void setBoundedPix(final int n, final int n2, final int n3) {
        this.pix[n + n2 * this.bpr] = (byte)((n3 > 1) ? ((n3 < 255) ? n3 : 255) : 1);
    }
    
    public final void setPix(final int n, final int n2, final int n3) {
        this.pix[n + n2 * this.bpr] = (byte)n3;
    }
    
    public final void updateImage() {
        this.source.newPixels();
    }
    
    public final void updateImage(final Rectangle rectangle) {
        this.source.newPixels(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
