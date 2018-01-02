// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import hypergraph.graphApi.io.CSSColourParser;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JComponent;

public class DefaultLineRenderer extends JComponent implements LineRenderer
{
    protected ModelPoint start;
    protected ModelPoint end;
    protected ModelPanel panel;
    private Color color;
    
    public void configure(final ModelPanel modelPanel) {
        this.setModelPanel(modelPanel);
        this.setColor(null);
    }
    
    public void configure(final ModelPanel modelPanel, final ModelPoint to, final ModelPoint to2) {
        this.configure(modelPanel);
        if (this.start == null) {
            this.start = (ModelPoint)to.clone();
        }
        else {
            this.start.setTo(to);
        }
        if (this.end == null) {
            this.end = (ModelPoint)to2.clone();
        }
        else {
            this.end.setTo(to2);
        }
        this.setBounds(0, 0, modelPanel.getWidth(), modelPanel.getHeight());
    }
    
    public Component getComponent() {
        return this;
    }
    
    public void setModelPanel(final ModelPanel panel) {
        this.panel = panel;
    }
    
    public void paint(final Graphics graphics) {
        if (this.start == null || this.end == null) {
            return;
        }
        graphics.setColor(this.getColor());
        this.panel.getUI();
        final Point[] lineSegments = this.panel.getProjector().getLineSegments(this.start, this.end, this.panel);
        final int[] array = new int[lineSegments.length];
        final int[] array2 = new int[lineSegments.length];
        for (int i = 0; i < lineSegments.length; ++i) {
            array[i] = lineSegments[i].x;
            array2[i] = lineSegments[i].y;
        }
        graphics.drawPolyline(array, array2, lineSegments.length);
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        if (this.color != null) {
            return this.color;
        }
        return CSSColourParser.stringToColor(this.panel.getPropertyManager().getString("hypergraph.hyperbolic.line.color"));
    }
}
