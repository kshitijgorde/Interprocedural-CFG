import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cB extends JPanel
{
    private Dimension a;
    private rp_dv a;
    Color a;
    Color b;
    
    public rp_cB(final Dimension a, final rp_dv a2) {
        this.a = null;
        this.b = null;
        this.a = a;
        this.a = a2;
    }
    
    public final Dimension getPreferredSize() {
        return this.a;
    }
    
    public final void paintComponent(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        final Rectangle clipBounds = graphics.getClipBounds();
        graphics.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        this.a.a(graphics, this.getSize(), new Dimension(this.a.a(), this.a.b()), this.a, this.b, false, 200, rp_au.a.a(), this, true);
    }
}
