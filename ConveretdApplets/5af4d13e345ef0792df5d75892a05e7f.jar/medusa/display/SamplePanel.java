// 
// Decompiled by Procyon v0.5.30
// 

package medusa.display;

import medusa.graph.Node;
import java.awt.Color;
import java.awt.Paint;
import medusa.graph.Edge;
import java.awt.Graphics2D;
import medusa.MedusaSettings;

public class SamplePanel extends BasicGraphPanel
{
    public SamplePanel(final MedusaSettings ms) {
        super(ms);
    }
    
    public void paintEdge(final Graphics2D g, final Edge e) {
        g.setPaint(this.basicEdgeColor);
        this.paintPathWithConfLabel(g, e);
    }
    
    public void paintPathWithConfLabel(final Graphics2D g2d, final Edge e) {
        final Node from = this.graph.getNode(e.getFromName());
        final Node to = this.graph.getNode(e.n2);
        final int x1 = (int)from.getX();
        final int y1 = (int)from.getY();
        final int x2 = (int)to.getX();
        final int y2 = (int)to.getY();
        final float conf = e.getConf();
        String conf_string = Float.toString(conf);
        final int lastIndex = conf_string.length();
        final int last = Math.min(lastIndex, 3);
        conf_string = conf_string.substring(0, last);
        g2d.drawLine(x1, y1, x2, y2);
        final int width = 11;
        final int height = 6;
        final int halfx = (x1 + x2) / 2;
        final int halfy = (y1 + y2) / 2;
        g2d.setColor(Color.YELLOW.brighter());
        g2d.fillRect(halfx - width, halfy - height, width * 2, height * 2);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(halfx - width, halfy - height, width * 2, height * 2);
        g2d.setColor(Color.BLACK);
        g2d.drawString(conf_string, halfx - width + 1, halfy + height - 1);
    }
}
