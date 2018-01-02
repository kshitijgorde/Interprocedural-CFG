import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class n extends m
{
    protected Image e;
    
    n(final Dimension dimension) {
        final int[] array = new int[dimension.width * dimension.height];
        super.a.addElement(array);
        this.a(dimension, Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(dimension.width, dimension.height, array, 0, dimension.width)));
    }
    
    protected void a(final Dimension b, final Image e) {
        super.b = b;
        (super.c = new float[2])[0] = super.b.width / 2.0f - 0.5f;
        super.c[1] = super.b.height / 2.0f - 0.5f;
        this.e = e;
    }
    
    void a(final Graphics graphics) {
        try {
            this.e.flush();
            graphics.drawImage(this.e, 0, 0, null);
        }
        catch (Exception ex) {}
    }
}
