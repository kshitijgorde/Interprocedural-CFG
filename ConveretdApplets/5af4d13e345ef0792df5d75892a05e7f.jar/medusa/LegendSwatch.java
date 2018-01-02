// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import java.awt.Paint;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.Icon;

class LegendSwatch implements Icon
{
    Color c;
    
    public LegendSwatch(final Color c) {
        this.c = c;
    }
    
    public int getIconWidth() {
        return 11;
    }
    
    public int getIconHeight() {
        return 11;
    }
    
    public void paintTriangle(final Graphics2D graphics2D, final int n, final int n2) {
        graphics2D.fillPolygon(new int[] { n, n + this.getIconWidth(), n }, new int[] { n2, n2 + this.getIconHeight(), n2 + this.getIconHeight() }, 3);
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setStroke(new BasicStroke(2.0f));
        graphics2D.setPaint(this.c);
        this.paintTriangle(graphics2D, n, n2);
    }
}
