// 
// Decompiled by Procyon v0.5.30
// 

package medusa.example;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.geom.Point2D;
import medusa.graph.Node;
import java.awt.Paint;
import medusa.graph.Edge;
import java.awt.Graphics2D;
import medusa.MedusaSettings;
import medusa.display.BasicGraphPanel;

public class ExamplePanel extends BasicGraphPanel
{
    public ExamplePanel(final MedusaSettings medusaSettings) {
        super(medusaSettings);
    }
    
    @Override
    public void paintEdge(final Graphics2D graphics2D, final Edge edge) {
        graphics2D.setPaint(this.basicEdgeColor);
        this.paintPath(graphics2D, edge);
    }
    
    private void paintPath(final Graphics2D graphics2D, final Edge edge) {
        final Node node = this.graph.getNode(edge.getFromName());
        final Node node2 = this.graph.getNode(edge.n2);
        graphics2D.drawLine((int)node.getX(), (int)node.getY(), (int)node2.getX(), (int)node2.getY());
    }
    
    @Override
    public void paintNode(final Graphics2D graphics2D, final Node node) {
        this.paintShadedNode(graphics2D, node);
    }
    
    private void paintShadedNode(final Graphics2D graphics2D, final Node node) {
        final Color color = node.getColor();
        final int n = 16;
        final int n2 = 2;
        final int n3 = (int)node.getX() - n / 2;
        final int n4 = (int)node.getY() - n / 2;
        final float n5 = n3;
        final float n6 = n4;
        final Point2D.Float float1 = new Point2D.Float(n3, n4);
        final Point2D.Float float2 = new Point2D.Float(n3 + n, n4 + n);
        final GradientPaint paint = new GradientPaint(float1, color, float2, Color.white);
        final GradientPaint paint2 = new GradientPaint(float1, Color.white, float2, color);
        final Ellipse2D.Double double1 = new Ellipse2D.Double(n3, n4, n, n);
        final Ellipse2D.Double double2 = new Ellipse2D.Double(n3 + n2, n4 + n2, n - 2 * n2, n - 2 * n2);
        graphics2D.setPaint(paint);
        graphics2D.fill(double1);
        graphics2D.setPaint(paint2);
        graphics2D.fill(double2);
        graphics2D.setPaint(Color.black);
        if (this.label) {
            graphics2D.drawString(node.getLabel(), n3 - 2, n4 - 2);
        }
    }
}
