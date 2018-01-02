import java.awt.Component;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_a extends JPanel implements ListCellRenderer
{
    private rp_fW a;
    
    public rp_a() {
        this.a = null;
    }
    
    public final void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.getClipBounds();
        if (this.a != null) {
            final Dimension size = this.getSize();
            if (this.a.a()) {
                graphics.setColor(Color.BLACK);
                graphics.drawString(rp_au.a(this.a.a), 10, size.height - 3);
                return;
            }
            int n = 4;
            if (this.a.a) {
                n = 10;
                graphics.setColor(Color.BLACK);
                graphics.drawString("C", 2, size.height - 4);
            }
            graphics.setColor(this.a.a.b);
            graphics.fillRect(n, 2, size.width - n - 4, size.height - 2 - 2);
            graphics.setColor(this.a.a.a);
            graphics.drawRect(n, 2, size.width - n - 4, size.height - 2 - 2);
            graphics.drawLine(size.width / 2, 2, size.width / 2, size.height - 2);
        }
    }
    
    public final Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        this.a = (rp_fW)o;
        return this;
    }
}
