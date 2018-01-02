// 
// Decompiled by Procyon v0.5.30
// 

package medusa.display;

import java.awt.Shape;
import java.util.Iterator;
import medusa.graph.Node;
import java.awt.Paint;
import java.awt.Composite;
import medusa.graph.Edge;
import java.awt.Stroke;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Dimension;
import medusa.MedusaSettings;
import java.awt.Font;
import medusa.graph.Graph;
import java.awt.Color;
import javax.swing.JPanel;

public class BasicGraphRenderer extends JPanel
{
    public boolean alpha;
    public boolean arrow;
    public Color basicEdgeColor;
    public int nodeSize;
    protected int correct;
    public Color fontColor;
    public int fontSize;
    public Graph graph;
    public boolean hideWhenMove;
    public boolean label;
    public Font nodeFont;
    public boolean showAnnotation;
    public boolean showConfidence;
    private MedusaSettings stringSettings;
    final int defaultSize = 600;
    protected int panelWidth;
    protected int panelHeight;
    public Dimension dims;
    public boolean pretty;
    
    public BasicGraphRenderer() {
        this.alpha = true;
        this.arrow = true;
        this.basicEdgeColor = Color.gray;
        this.nodeSize = 10;
        this.correct = (int)(this.nodeSize / 2.0);
        this.fontColor = Color.black;
        this.fontSize = 10;
        this.hideWhenMove = false;
        this.label = true;
        this.nodeFont = new Font("TimesNewRoman", 0, 10);
        this.showAnnotation = false;
        this.showConfidence = true;
        this.panelWidth = 600;
        this.panelHeight = 600;
        this.dims = new Dimension(600, 600);
        this.pretty = false;
    }
    
    public void clear(final Graphics graphics) {
        super.paintComponent(graphics);
    }
    
    public Color getFontColor() {
        return this.fontColor;
    }
    
    public Graph getGraph() {
        return this.graph;
    }
    
    public String[] getGraphData() {
        return new String[] { new Integer(this.graph.getNodeSize()).toString(), new Integer(this.graph.getEdgeSize()).toString(), new Integer(this.graph.getUniqueEdgeSize()).toString() };
    }
    
    public Dimension getMinimumSize() {
        return this.dims;
    }
    
    public int getPanelHeight() {
        return this.panelHeight;
    }
    
    public int getPanelWidth() {
        return this.panelWidth;
    }
    
    public Dimension getPreferredSize() {
        return this.dims;
    }
    
    protected BasicStroke getStroke(final float n) {
        final float[] array = { 3.0f, 2.0f, 3.0f, 2.0f };
        final float[] array2 = { 1.0f, 4.0f, 1.0f, 4.0f };
        if (n > 0.67) {
            return new BasicStroke();
        }
        if (n > 0.33) {
            return new BasicStroke(1.0f, 0, 0, 10.0f, array, 0.0f);
        }
        return new BasicStroke(1.0f, 0, 0, 10.0f, array2, 0.0f);
    }
    
    public void setPanelWidth(final int panelWidth) {
        this.panelWidth = panelWidth;
    }
    
    public void setPanelHeight(final int panelHeight) {
        this.panelHeight = panelHeight;
    }
    
    protected AlphaComposite makeComposite(final float n) {
        return AlphaComposite.getInstance(3, n);
    }
    
    public synchronized void paintComponent(final Graphics graphics) {
        this.paintNet(this.prePaint(graphics));
    }
    
    protected Graphics2D prePaint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(new BasicStroke(1.0f));
        this.clear(graphics2D);
        return graphics2D;
    }
    
    public void paintEdge(final Graphics2D graphics2D, final Edge edge) {
        final Node node = this.graph.getNode(edge.getFromName());
        final Node node2 = this.graph.getNode(edge.n2);
        final int n = (int)node.getX();
        final int n2 = (int)node.getY();
        final int n3 = (int)node2.getX();
        final int n4 = (int)node2.getY();
        if (this.showConfidence) {
            if (this.alpha) {
                graphics2D.setComposite(this.makeComposite(edge.getConf()));
            }
            else {
                graphics2D.setStroke(this.getStroke(edge.getConf()));
            }
        }
        graphics2D.setPaint(this.basicEdgeColor);
        if (this.pretty) {
            graphics2D.setPaint(this.getStringSettings().getColor(new Integer(edge.getType())));
            PaintTools.paintPath(graphics2D, n, n2, n3, n4, edge.getOrientation(), 0.3, this.arrow);
        }
        else {
            graphics2D.drawLine(n, n2, n3, n4);
        }
    }
    
    public void paintNet(final Graphics2D graphics2D) {
        final Iterator<Edge> edgesIterator = this.graph.edgesIterator();
        while (edgesIterator.hasNext()) {
            this.paintEdge(graphics2D, edgesIterator.next());
        }
        graphics2D.setComposite(this.makeComposite(1.0f));
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            this.paintNode(graphics2D, nodesIterator.next());
        }
    }
    
    public void paintNode(final Graphics2D graphics2D, final Node node) {
        graphics2D.setStroke(new BasicStroke(1.0f));
        graphics2D.setPaint(node.getColor());
        final int n = (int)node.getX() - this.correct;
        final int n2 = (int)node.getY() - this.correct;
        final Shape shape = PaintTools.getShape(node.getShape(), n, n2, this.nodeSize);
        graphics2D.fill(shape);
        graphics2D.setPaint(Color.black);
        if (node.isFixed()) {
            graphics2D.setPaint(Color.yellow);
        }
        graphics2D.draw(shape);
        graphics2D.setPaint(this.fontColor);
        graphics2D.setFont(this.nodeFont);
        if (this.label) {
            if (this.showAnnotation) {
                graphics2D.drawString(node.getAnnotation(), n - 2, n2 - 2);
            }
            else {
                graphics2D.drawString(node.getLabel(), n - 2, n2 - 2);
            }
        }
    }
    
    public MedusaSettings getStringSettings() {
        return this.stringSettings;
    }
    
    public void setStringSettings(final MedusaSettings stringSettings) {
        this.stringSettings = stringSettings;
    }
}
