// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Canvas;

class ScanvasKin extends Canvas
{
    private GraphKin a;
    
    public ScanvasKin(final GraphKin a) {
        this.a = a;
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, size.width, size.height);
        this.paint(graphics2);
        graphics.drawImage(image, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        this.a.a(graphics);
    }
}
