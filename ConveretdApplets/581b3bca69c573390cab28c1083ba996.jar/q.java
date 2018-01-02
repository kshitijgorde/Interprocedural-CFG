import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.image.ImageConsumer;

// 
// Decompiled by Procyon v0.5.30
// 

class q implements ImageConsumer
{
    protected ImageProducer a;
    protected o b;
    private boolean c;
    protected int d;
    private static final int e = 48;
    private static final int f = 112;
    
    q(final o b, final Image image) {
        this.c = false;
        this.a = image.getSource();
        this.b = b;
    }
    
    public synchronized boolean a() throws InterruptedException {
        if ((this.d & 0x70) != 0x0) {
            return (this.d & 0x30) != 0x0;
        }
        if (!this.c) {
            this.c = true;
            this.d &= 0xFFFFFF7F;
            this.a.startProduction(this);
            this.wait();
        }
        return (this.d & 0x30) != 0x0;
    }
    
    public void setDimensions(final int n, final int n2) {
        this.b.a(new Dimension(n, n2));
        this.d |= 0x3;
    }
    
    public void setHints(final int n) {
    }
    
    public void setProperties(final Hashtable hashtable) {
    }
    
    public void setColorModel(final ColorModel colorModel) {
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        final Dimension dimension = new Dimension(this.b.b().width, this.b.f());
        int n7 = n5;
        for (int i = n2; i < n4 + n2; ++i) {
            final int[] array2 = this.b.a().elementAt(i / dimension.height);
            final int n8 = i % dimension.height * dimension.width;
            for (int j = 0; j < dimension.width; ++j) {
                array2[n8 + j] = colorModel.getRGB(array[n7 + j]);
            }
            n7 += n6;
        }
        this.d |= 0x8;
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        final Dimension dimension = new Dimension(this.b.b().width, this.b.f());
        int n7 = n5;
        for (int i = n2; i < n4 + n2; ++i) {
            final int[] array2 = this.b.a().elementAt(i / dimension.height);
            final int n8 = i % dimension.height * dimension.width;
            for (int j = 0; j < dimension.width; ++j) {
                array2[n8 + j] = (array[n7 + j] | 0xFF000000);
            }
            n7 += n6;
        }
        this.d |= 0x8;
    }
    
    public synchronized void imageComplete(final int n) {
        this.c = false;
        switch (n) {
            case 4: {
                this.d |= 0x80;
                break;
            }
            case 3: {
                this.d |= 0x20;
                break;
            }
            case 2: {
                this.d |= 0x10;
                break;
            }
            default: {
                this.d |= 0xC0;
                break;
            }
        }
        this.a.removeConsumer(this);
        this.notifyAll();
    }
}
