// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;
import java.awt.Color;
import java.awt.Canvas;

public class vaf extends Canvas
{
    private static final Color a;
    private URL b;
    private int c;
    private int d;
    private Color e;
    private Image f;
    
    public void a(final URL b, final int c, final int d, final Color color) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = ((color == null) ? this.getBackground() : color);
        this.f = Toolkit.getDefaultToolkit().getImage(b);
        this.repaint();
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.c + 8, this.d + 8);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int width = size.width;
        final int height = size.height;
        graphics.setColor(vaf.a);
        graphics.fill3DRect(0, 0, width, height, true);
        graphics.draw3DRect(3, 3, width - 7, height - 7, false);
        graphics.setColor(this.e);
        graphics.fillRect(4, 4, width - 8, height - 8);
        graphics.clipRect(4, 4, width - 8, height - 8);
        graphics.drawImage(this.f, 4, 4, this);
    }
    
    static {
        a = Color.lightGray;
    }
}
