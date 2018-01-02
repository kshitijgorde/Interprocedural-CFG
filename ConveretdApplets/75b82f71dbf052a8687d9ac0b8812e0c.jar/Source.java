import java.awt.Dimension;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

class Source extends Frame
{
    protected ImageProducer producer;
    protected int mapHeight;
    
    Source() {
        this.producer = null;
    }
    
    ImageProducer getProducer() {
        final ImageProducer p = this.producer;
        this.producer = null;
        return p;
    }
    
    Object copy() {
        final Source f = new Source();
        f.properties = (Hashtable)this.properties.clone();
        return f;
    }
    
    int getMapHeight() {
        return this.mapHeight;
    }
    
    synchronized void setImage(final Image image) throws RuntimeException, InterruptedException {
        final SimplePixelGrabber pix = new SimplePixelGrabber(this, image);
        pix.grabPixels();
        this.producer = image.getSource();
    }
    
    void allocate(final Dimension inSize) throws RuntimeException {
        if (this.pixmaps.size() != 0) {
            return;
        }
        this.size = inSize;
        try {
            this.pixmaps.addElement(new int[this.size.width * this.size.height]);
            this.mapHeight = this.size.height;
        }
        catch (OutOfMemoryError e) {
            this.mapHeight = 1000000 / this.size.width;
            this.mapHeight = 1 << (int)(Math.log(this.mapHeight) / Math.log(2.0));
            for (int y = 0; y < this.size.height; y += this.mapHeight) {
                final int h = Math.min(this.mapHeight, this.size.height - y);
                this.pixmaps.addElement(new int[this.size.width * h]);
            }
        }
    }
}
