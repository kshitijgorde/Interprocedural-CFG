import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_eB extends JPanel
{
    public rp_eB(final rp_fK rp_fK) {
    }
    
    public final void paintComponent(final Graphics graphics) {
        final Image a;
        if ((a = rp_au.a.a("wait-cursor-transparent.gif")) != null) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            final int height = this.getHeight();
            final int width = this.getWidth();
            final int height2 = a.getHeight(null);
            final int n = (width - a.getWidth(null)) / 2;
            final int n2 = (height - height2) / 2;
            graphics2D.setColor(this.getBackground());
            graphics2D.fillRect(0, 0, width, height);
            graphics2D.drawImage(a, n, n2, new rp_eO(this));
        }
    }
}
