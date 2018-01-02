import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_Z extends JPanel
{
    Color a;
    Color b;
    
    public rp_Z(final rp_bE rp_bE, final Color a, final Color b) {
        this.a = a;
        this.b = b;
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(80, 26);
    }
    
    protected final void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Dimension size = this.getSize();
        graphics.setColor(this.b);
        graphics.fillRect(4, 2, size.width - 4 - 4, size.height - 2 - 2);
        graphics.setColor(this.a);
        graphics.drawRect(4, 2, size.width - 4 - 4, size.height - 2 - 2);
        graphics.drawLine(size.width / 2, 2, size.width / 2, size.height - 2);
    }
}
