// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import KJEgraph.Graph;
import java.applet.Applet;
import java.util.Enumeration;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import KJEgraph.AutoColor;
import java.awt.Image;
import javax.swing.JApplet;

public class GraphApplet extends JApplet
{
    Image gImage;
    
    public GraphApplet() {
        this.gImage = null;
    }
    
    public void init() {
        final String parameter = this.getParameter("APPLET_ID");
        if (parameter != null) {
            final Enumeration<Applet> applets = this.getAppletContext().getApplets();
            while (applets.hasMoreElements()) {
                final CalculatorApplet nextElement = applets.nextElement();
                if (nextElement instanceof CalculatorApplet) {
                    final CalculatorApplet calculatorApplet = nextElement;
                    if (!parameter.equals(calculatorApplet.getAppletID())) {
                        continue;
                    }
                    final Graph[] graph = calculatorApplet.getGraph();
                    if (graph == null) {
                        continue;
                    }
                    final int int1 = Integer.parseInt(this.getParameter("GRAPH_ID"));
                    if (graph[int1] == null) {
                        continue;
                    }
                    this.gImage = graph[int1].getImage(this.getWidth(), this.getHeight(), AutoColor.getColor(this.getParameter("PAGEBACKGROUND_COLOR")));
                    graph[int1].dataChanged(true);
                    this.getContentPane().add("Center", new JLabel(new ImageIcon(this.gImage)));
                }
            }
        }
    }
}
