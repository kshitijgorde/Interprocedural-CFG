import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvPanoramaFrame extends Frame
{
    public void pack() {
        super.pack();
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        final Dimension dimension = this.size();
        final int i = Math.max(dimension.width, 110);
        final int j = dimension.height;
        if (i > 150) {
            return;
        }
        for (int k = -j; k < i; k += 10) {
            g.drawLine(k, 0, k + j, j);
        }
        for (int l = 0; l < i + j; l += 10) {
            g.drawLine(l, 0, l - j, j);
        }
    }
    
    public WvPanoramaFrame() {
        this.setLayout(new BorderLayout());
        this.setResizable(false);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
        }
        return false;
    }
}
