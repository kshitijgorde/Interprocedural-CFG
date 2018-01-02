import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.image.DirectColorModel;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

final class RSImageProducer implements ImageProducer, ImageObserver
{
    public final int[] anIntArray315;
    private final int anInt316;
    private final int anInt317;
    private final ColorModel aColorModel318;
    private ImageConsumer anImageConsumer319;
    private final Image anImage320;
    
    public RSImageProducer(final int anInt316, final int anInt317, final Component component) {
        this.anInt316 = anInt316;
        this.anInt317 = anInt317;
        this.anIntArray315 = new int[anInt316 * anInt317];
        this.aColorModel318 = new DirectColorModel(32, 16711680, 65280, 255);
        this.anImage320 = component.createImage(this);
        this.method239();
        component.prepareImage(this.anImage320, this);
        this.method239();
        component.prepareImage(this.anImage320, this);
        this.method239();
        component.prepareImage(this.anImage320, this);
        this.initDrawingArea();
    }
    
    public void initDrawingArea() {
        DrawingArea.initDrawingArea(this.anInt317, this.anInt316, this.anIntArray315);
    }
    
    public void drawGraphics(final int n, final Graphics graphics, int n2) {
        this.method239();
        final client instance = client.instance;
        if (client.clientSize == 0) {
            final int n3 = n2;
            final client instance2 = client.instance;
            n2 = n3 + ((client.clientWidth - 765) / 2 - 4);
        }
        graphics.drawImage(this.anImage320, n2, n, this);
    }
    
    @Override
    public synchronized void addConsumer(final ImageConsumer anImageConsumer319) {
        (this.anImageConsumer319 = anImageConsumer319).setDimensions(this.anInt316, this.anInt317);
        anImageConsumer319.setProperties(null);
        anImageConsumer319.setColorModel(this.aColorModel318);
        anImageConsumer319.setHints(14);
    }
    
    @Override
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        return this.anImageConsumer319 == imageConsumer;
    }
    
    @Override
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        if (this.anImageConsumer319 == imageConsumer) {
            this.anImageConsumer319 = null;
        }
    }
    
    @Override
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    @Override
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    private synchronized void method239() {
        if (this.anImageConsumer319 != null) {
            this.anImageConsumer319.setPixels(0, 0, this.anInt316, this.anInt317, this.aColorModel318, this.anIntArray315, 0, this.anInt316);
            this.anImageConsumer319.imageComplete(2);
        }
    }
    
    @Override
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
}
