import java.util.Hashtable;
import java.awt.image.PixelGrabber;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

class w extends t
{
    protected ImageProducer e;
    
    w() {
        this.e = null;
    }
    
    synchronized void a(final Image image, final Dimension b, final float[] c) throws RuntimeException, InterruptedException {
        super.a.removeAllElements();
        super.b = b;
        super.c = c;
        this.e = image.getSource();
        try {
            final z z = new z(b);
            super.a.addElement(z);
            if (!new PixelGrabber(image, 0, 0, b.width, b.height, z.a(), 0, b.width).grabPixels()) {
                throw new RuntimeException();
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            for (int n = 1 << (int)(Math.log(1000000 / b.width) / Math.log(2.0)), i = 0; i < b.height; i += n) {
                final int min = Math.min(n, b.height - i);
                final z z2 = new z(new Dimension(b.width, min));
                super.a.addElement(z2);
                if (!new PixelGrabber(image, 0, i, b.width, min, z2.a(), 0, b.width).grabPixels()) {
                    throw new RuntimeException();
                }
            }
        }
    }
    
    ImageProducer d() {
        final ImageProducer e = this.e;
        this.e = null;
        return e;
    }
    
    public Object clone() {
        final w w = new w();
        w.d = (Hashtable)super.d.clone();
        return w;
    }
}
