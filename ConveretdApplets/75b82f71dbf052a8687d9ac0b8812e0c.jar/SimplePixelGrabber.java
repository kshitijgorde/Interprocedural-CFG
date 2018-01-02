import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.image.ImageConsumer;

// 
// Decompiled by Procyon v0.5.30
// 

class SimplePixelGrabber implements ImageConsumer
{
    protected ImageProducer producer;
    protected Source dstSource;
    private boolean grabbing;
    protected int flags;
    private static final int GRABBEDBITS = 48;
    private static final int DONEBITS = 112;
    
    SimplePixelGrabber(final Source frame, final Image img) {
        this.grabbing = false;
        this.producer = img.getSource();
        this.dstSource = frame;
    }
    
    public synchronized boolean grabPixels() throws InterruptedException {
        if ((this.flags & 0x70) != 0x0) {
            return (this.flags & 0x30) != 0x0;
        }
        if (!this.grabbing) {
            this.grabbing = true;
            this.flags &= 0xFFFFFF7F;
            this.producer.startProduction(this);
            this.wait();
        }
        return (this.flags & 0x30) != 0x0;
    }
    
    public void setDimensions(final int width, final int height) {
        this.dstSource.allocate(new Dimension(width, height));
        this.flags |= 0x3;
    }
    
    public void setHints(final int hints) {
    }
    
    public void setProperties(final Hashtable props) {
    }
    
    public void setColorModel(final ColorModel model) {
    }
    
    public void setPixels(final int srcX, final int srcY, final int srcW, final int srcH, final ColorModel model, final byte[] pixels, final int srcOff, final int srcScan) {
        final Dimension dstSize = new Dimension(this.dstSource.getSize().width, this.dstSource.getMapHeight());
        int srcPix = srcOff;
        for (int y = srcY; y < srcH + srcY; ++y) {
            final int[] dstMap = this.dstSource.getPixmaps().elementAt(y / dstSize.height);
            final int dY = y % dstSize.height * dstSize.width;
            for (int x = 0; x < dstSize.width; ++x) {
                dstMap[dY + x] = model.getRGB(pixels[srcPix + x]);
            }
            srcPix += srcScan;
        }
        this.flags |= 0x8;
    }
    
    public void setPixels(final int srcX, final int srcY, final int srcW, final int srcH, final ColorModel model, final int[] pixels, final int srcOff, final int srcScan) {
        final Dimension dstSize = new Dimension(this.dstSource.getSize().width, this.dstSource.getMapHeight());
        int srcPix = srcOff;
        for (int y = srcY; y < srcH + srcY; ++y) {
            final int[] dstMap = this.dstSource.getPixmaps().elementAt(y / dstSize.height);
            final int dY = y % dstSize.height * dstSize.width;
            for (int x = 0; x < dstSize.width; ++x) {
                dstMap[dY + x] = (pixels[srcPix + x] | 0xFF000000);
            }
            srcPix += srcScan;
        }
        this.flags |= 0x8;
    }
    
    public synchronized void imageComplete(final int status) {
        this.grabbing = false;
        switch (status) {
            case 4: {
                this.flags |= 0x80;
                break;
            }
            case 3: {
                this.flags |= 0x20;
                break;
            }
            case 2: {
                this.flags |= 0x10;
                break;
            }
            default: {
                this.flags |= 0xC0;
                break;
            }
        }
        this.producer.removeConsumer(this);
        this.notifyAll();
    }
}
