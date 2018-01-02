// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.util.Hashtable;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

public class SexyImageProducer implements ImageProducer
{
    private ImageConsumer consumer;
    private int w;
    private int h;
    private ColorModel cm;
    private int[] pixel;
    private int hints;
    private int sfd;
    
    public SexyImageProducer(final int w, final int h, final ColorModel cm, final int[] pixel) {
        this.w = w;
        this.h = h;
        this.cm = cm;
        this.pixel = pixel;
        this.hints = 30;
        this.sfd = 2;
    }
    
    public void update() {
        if (this.consumer != null) {
            this.startProduction(this.consumer);
        }
    }
    
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public final boolean isConsumer(final ImageConsumer imageConsumer) {
        return this.consumer == imageConsumer;
    }
    
    public final void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    public final void startProduction(final ImageConsumer consumer) {
        if (this.consumer != consumer) {
            (this.consumer = consumer).setDimensions(this.w, this.h);
            this.consumer.setProperties(null);
            this.consumer.setColorModel(this.cm);
            this.consumer.setHints(this.hints);
        }
        this.consumer.setPixels(0, 0, this.w, this.h, this.cm, this.pixel, 0, this.w);
        this.consumer.imageComplete(this.sfd);
    }
    
    public synchronized void addConsumer(final ImageConsumer consumer) {
        this.consumer = consumer;
    }
}
