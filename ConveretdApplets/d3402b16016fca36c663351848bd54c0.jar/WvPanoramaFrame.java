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
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int max = Math.max(size.width, 110);
        final int height = size.height;
        if (max > 150) {
            return;
        }
        for (int i = -height; i < max; i += 10) {
            graphics.drawLine(i, 0, i + height, height);
        }
        for (int j = 0; j < max + height; j += 10) {
            graphics.drawLine(j, 0, j - height, height);
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
