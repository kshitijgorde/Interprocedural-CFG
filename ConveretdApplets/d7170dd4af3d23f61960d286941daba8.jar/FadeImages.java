import java.awt.Component;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageFilter;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.image.FilteredImageSource;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class FadeImages extends RGBImageFilter
{
    Image[] img;
    Image[] imgShow;
    private int level;
    private MediaTracker track;
    private Image gray;
    private FilteredImageSource fis;
    private Graphics g;
    private Graphics g2;
    private int numImages;
    private int transparent;
    
    public FadeImages(final Applet applet, final Image[] array) {
        this(applet, array, -1);
    }
    
    public FadeImages(final Applet applet, final Image[] array, final int transparent) {
        super.canFilterIndexColorModel = true;
        this.level = 255;
        this.transparent = transparent;
        this.g = applet.getGraphics();
        this.numImages = array.length;
        int n;
        int n2;
        if (this.numImages == 2) {
            n = 10;
            n2 = 10;
        }
        else {
            n = 5 * this.numImages - 5;
            n2 = 5;
        }
        final int n3 = n + 1;
        int n4 = 0;
        this.imgShow = new Image[n3];
        int n5 = 0;
        for (int i = 1; i < this.numImages; ++i) {
            this.fis = new FilteredImageSource(array[i - 1].getSource(), this);
            for (int j = n4; j <= n2; ++j) {
                this.level = (n2 - j) * 255 / n2;
                this.imgShow[n5] = applet.createImage(array[i].getWidth(applet), array[i].getHeight(applet));
                (this.g2 = this.imgShow[n5].getGraphics()).drawImage(array[i], 0, 0, applet);
                if (this.gray != null) {
                    this.gray.flush();
                }
                this.gray = applet.createImage(this.fis);
                (this.track = new MediaTracker(applet)).addImage(this.gray, 0);
                try {
                    this.track.waitForID(0);
                }
                catch (InterruptedException ex) {}
                this.g2.drawImage(this.gray, 0, 0, applet);
                ++n5;
            }
            n4 = 1;
        }
    }
    
    public Image[] getImages() {
        return this.imgShow;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        int n4 = this.level * 16777216;
        if ((n3 & 0xFFFFFF) == this.transparent) {
            n4 = 0;
        }
        return (n3 & 0xFFFFFF) | n4;
    }
}
