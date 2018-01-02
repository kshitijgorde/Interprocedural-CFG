// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.awt.Graphics;
import javax.swing.JPanel;

class GraphPanel extends JPanel
{
    Graph graph;
    
    public GraphPanel(final Graph graph) {
        this.graph = graph;
        this.setDoubleBuffered(false);
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        MattProperties.instance();
        if (!MattProperties.getString("mode").equals("server")) {
            MattProperties.instance();
            if (!MattProperties.getString("drawGraphs").equals("false")) {
                for (int i = 0; i < this.graph.countSeries(); ++i) {
                    final Series series = this.graph.getSeries(i);
                    series.paint(g);
                }
            }
        }
    }
}
