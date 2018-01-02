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

class u extends t
{
    protected Image e;
    
    protected u() {
    }
    
    u(final Dimension dimension) {
        final z z = new z(dimension);
        super.a.addElement(z);
        this.a(dimension, Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(dimension.width, dimension.height, z.a(), 0, dimension.width)));
    }
    
    void a(final Graphics graphics) {
        try {
            this.e.flush();
            graphics.drawImage(this.e, 0, 0, null);
        }
        catch (NullPointerException ex) {}
        catch (IllegalArgumentException ex2) {}
    }
    
    protected void a(final Dimension b, final Image e) {
        super.b = b;
        (super.c = new float[2])[0] = super.b.width / 2.0f - 0.5f;
        super.c[1] = super.b.height / 2.0f - 0.5f;
        this.e = e;
    }
}
