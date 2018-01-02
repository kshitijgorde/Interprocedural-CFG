import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_j extends JPanel
{
    private int a;
    
    public rp_j(final int a) {
        this.a = a;
        this.setBackground(null);
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(this.a, this.a);
    }
    
    public final Dimension getMaximumSize() {
        return new Dimension(this.a, 9999);
    }
    
    public final void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Dimension size;
        final int n = (size = this.getSize()).width / 2;
        graphics.setColor(rp_aJ.u);
        graphics.drawLine(n, 6, n, size.height - 6);
        graphics.setColor(Color.WHITE);
        graphics.drawLine(n + 1, 6, n + 1, size.height - 6);
    }
}
