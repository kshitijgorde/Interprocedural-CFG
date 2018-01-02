import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class p extends Canvas
{
    private Image a;
    private Color b;
    
    public p(final Image a, final Color b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a(final Image a) {
        this.a = a;
        this.repaint();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.drawImage(this.a, 0, 0, size.width, size.height, this.b, this);
    }
}
