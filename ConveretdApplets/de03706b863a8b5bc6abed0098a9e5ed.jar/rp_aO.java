import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_aO extends JPanel
{
    private BufferedImage a;
    private Container a;
    
    public rp_aO(final Container a, final int n, final int n2) {
        this.a = a;
        this.a = new BufferedImage(n, n2, 1);
        this.a();
    }
    
    public final void a() {
        final Graphics2D graphics = this.a.createGraphics();
        this.a.print(graphics);
        graphics.dispose();
        this.repaint();
    }
    
    protected final void paintComponent(final Graphics graphics) {
        if (this.a != null) {
            ((Graphics2D)graphics).drawImage(this.a, 0, 0, Color.WHITE, null);
        }
        this.paintChildren(graphics);
    }
}
