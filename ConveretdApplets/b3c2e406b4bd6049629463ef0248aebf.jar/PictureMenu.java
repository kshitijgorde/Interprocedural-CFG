import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class PictureMenu extends KLMenu
{
    Image a0;
    Image a1;
    Image a2;
    int a3;
    
    public void init() {
        super.init("PictureMenu http://javabase.go.to/");
        this.a3 = this.a71("border", 2);
        this.a0 = this.a6(super.a44.getRGB());
        this.a1 = this.a6(this.a20(Color.white.getRGB(), super.a44.getRGB()));
        this.a2 = this.a6(this.a20(Color.black.getRGB(), super.a44.getRGB()));
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.a0, 1);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            this.stop();
        }
    }
    
    protected Image a6(final int n) {
        final int[] array = new int[super.width * super.height];
        final PixelGrabber pixelGrabber = new PixelGrabber(super.a53, 0, 0, super.width, super.height, array, 0, super.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            this.stop();
        }
        for (int i = 0; i < super.width; ++i) {
            for (int j = 0; j < super.height; ++j) {
                array[i + j * super.width] = this.a20(array[i + j * super.width], n);
            }
        }
        return this.createImage(new MemoryImageSource(super.width, super.height, ColorModel.getRGBdefault(), array, 0, super.width));
    }
    
    protected void a14(final Rectangle rectangle, final int n) {
        final Rectangle rectangle2 = new Rectangle(rectangle.x + this.a3, rectangle.y + this.a3, rectangle.width - this.a3 * 2, rectangle.height - this.a3 * 2);
        final Rectangle rectangle3 = new Rectangle(rectangle.x, rectangle.y + rectangle.height - this.a3, rectangle.width, this.a3);
        this.a17(new Rectangle(rectangle.x + rectangle.width - this.a3, rectangle.y, this.a3, rectangle.height), super.a46[n] ? this.a1 : this.a2, n);
        this.a17(rectangle3, super.a46[n] ? this.a1 : this.a2, n);
        this.a17(rectangle2, this.a0, n);
        for (int i = 0; i < this.a3; ++i) {
            final Rectangle rectangle4 = new Rectangle(rectangle.x, rectangle.y + i, rectangle.width - i, 1);
            final Rectangle rectangle5 = new Rectangle(rectangle.x + i, rectangle.y, 1, rectangle.height - i);
            this.a17(rectangle4, super.a46[n] ? this.a2 : this.a1, n);
            this.a17(rectangle5, super.a46[n] ? this.a2 : this.a1, n);
        }
    }
    
    void a17(final Rectangle rectangle, final Image image, final int n) {
        if (super.a32[n] != 0) {
            super.a39.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height).drawImage(image, -rectangle.x, -rectangle.y, this);
        }
    }
    
    protected int a20(final int n, final int n2) {
        return 0xFF000000 | ((n & 0xFF0000) + (n2 & 0xFF0000) >> 1 & 0xFF0000) | ((n & 0xFF00) + (n2 & 0xFF00) >> 1 & 0xFF00) | ((n & 0xFF) + (n2 & 0xFF) >> 1 & 0xFF);
    }
}
