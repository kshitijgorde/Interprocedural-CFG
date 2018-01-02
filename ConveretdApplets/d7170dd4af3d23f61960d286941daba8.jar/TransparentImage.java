import java.applet.Applet;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.Component;
import java.awt.image.FilteredImageSource;
import java.awt.Image;
import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class TransparentImage extends RGBImageFilter
{
    Image imgShow;
    Image ti;
    private FilteredImageSource fis;
    private int transparent;
    private int tRedBottom;
    private int tRedTop;
    private int tGreenBottom;
    private int tGreenTop;
    private int tBlueBottom;
    private int tBlueTop;
    private int transAmount;
    private boolean single;
    int count;
    
    public TransparentImage(final Component component, final Image image, final int n, final int n2) {
        this.count = 0;
        this.single = true;
        this.setUpImage(component, image, n, n2);
    }
    
    private void setUpImage(final Component component, final Image image, int n, final int transparent) {
        super.canFilterIndexColorModel = true;
        this.transparent = transparent;
        if (n < 0) {
            n = 0;
        }
        if (n > 100) {
            n = 100;
        }
        this.transAmount = (255 * n / 100 & 0xFF) << 24;
        this.fis = new FilteredImageSource(image.getSource(), this);
        this.ti = component.createImage(this.fis);
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(this.ti, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {}
    }
    
    public TransparentImage(final Applet applet, final Image image, final int n, final int n2, final int n3) {
        this.count = 0;
        if (n2 != n3) {
            this.single = false;
            this.tRedBottom = (n2 & 0xFF0000);
            this.tRedTop = (n3 & 0xFF0000);
            this.tGreenBottom = (n2 & 0xFF00);
            this.tGreenTop = (n3 & 0xFF00);
            this.tBlueBottom = (n2 & 0xFF);
            this.tBlueTop = (n3 & 0xFF);
        }
        else {
            this.single = true;
        }
        this.setUpImage(applet, image, n, n2);
    }
    
    public Image getImage() {
        return this.ti;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        if (this.single) {
            if ((n3 & 0xFFFFFF) == this.transparent) {
                return n3 & 0xFFFFFF;
            }
        }
        else if ((n3 & 0xFF0000) >= this.tRedBottom && (n3 & 0xFF0000) <= this.tRedTop && (n3 & 0xFF00) >= this.tGreenBottom && (n3 & 0xFF00) <= this.tGreenTop && (n3 & 0xFF) >= this.tBlueBottom && (n3 & 0xFF) <= this.tBlueTop) {
            return n3 & 0xFFFFFF;
        }
        return (n3 & 0xFFFFFF) | this.transAmount;
    }
}
