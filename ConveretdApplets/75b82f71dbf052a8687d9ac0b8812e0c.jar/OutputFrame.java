import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Dimension;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class OutputFrame extends Frame
{
    protected Image image;
    
    OutputFrame(final Dimension size) {
        final int[] map = new int[size.width * size.height];
        this.pixmaps.addElement(map);
        final MemoryImageSource src = new MemoryImageSource(size.width, size.height, map, 0, size.width);
        this.init(size, Toolkit.getDefaultToolkit().createImage(src));
    }
    
    protected void init(final Dimension s, final Image i) {
        this.size = s;
        (this.center = new float[2])[0] = this.size.width / 2.0f - 0.5f;
        this.center[1] = this.size.height / 2.0f - 0.5f;
        this.image = i;
    }
    
    void paint(final Graphics g) {
        try {
            this.image.flush();
            g.drawImage(this.image, 0, 0, null);
        }
        catch (Exception ex) {}
    }
}
