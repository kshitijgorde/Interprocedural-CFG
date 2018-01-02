// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import java.awt.Component;
import java.awt.Stroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import hypergraph.graphApi.AttributeManager;
import hypergraph.graphApi.Element;
import java.awt.Color;
import hypergraph.hyperbolic.ModelPoint;
import hypergraph.hyperbolic.ModelPanel;
import hypergraph.graphApi.Edge;
import java.awt.BasicStroke;
import hypergraph.hyperbolic.LineRenderer;
import hypergraph.hyperbolic.TextRenderer;
import javax.swing.JComponent;

public class DefaultEdgeRenderer extends JComponent implements EdgeRenderer
{
    private TextRenderer labelRenderer;
    private LineRenderer lineRenderer;
    private boolean labelVisible;
    protected GraphPanel graphPanel;
    protected BasicStroke lineStroke;
    
    public void setLabelRenderer(final TextRenderer labelRenderer) {
        this.labelRenderer = labelRenderer;
    }
    
    public TextRenderer getLabelRenderer() {
        if (this.labelRenderer == null) {
            this.labelRenderer = this.graphPanel.getTextRenderer();
        }
        return this.labelRenderer;
    }
    
    public void setLineRenderer(final LineRenderer lineRenderer) {
        this.lineRenderer = lineRenderer;
    }
    
    public LineRenderer getLineRenderer() {
        if (this.lineRenderer == null) {
            this.lineRenderer = this.graphPanel.getLineRenderer();
        }
        return this.lineRenderer;
    }
    
    public void setLabelVisible(final boolean labelVisible) {
        this.labelVisible = labelVisible;
    }
    
    public boolean isLabelVisible() {
        return this.labelVisible;
    }
    
    public void configure(final GraphPanel graphPanel, final Edge edge) {
        this.graphPanel = graphPanel;
        this.setBounds(0, 0, graphPanel.getWidth(), graphPanel.getHeight());
        final AttributeManager attributeManager = graphPanel.getGraph().getAttributeManager();
        final ModelPoint nodePosition = graphPanel.getGraphLayout().getGraphLayoutModel().getNodePosition(edge.getSource());
        final ModelPoint nodePosition2 = graphPanel.getGraphLayout().getGraphLayoutModel().getNodePosition(edge.getTarget());
        if (nodePosition != null && nodePosition2 != null) {
            this.getLineRenderer().configure(graphPanel, nodePosition, nodePosition2);
            if (this.labelVisible) {
                final ModelPoint modelPoint = (ModelPoint)nodePosition.clone();
                graphPanel.getModel().getTranslation(nodePosition, nodePosition2, 0.5).apply(modelPoint);
                this.getLabelRenderer().configure(graphPanel, modelPoint, edge.getLabel());
                this.getLabelRenderer().setBackground(null);
                Color color = null;
                if (attributeManager != null) {
                    color = (Color)attributeManager.getAttribute("edge.textcolor", edge);
                }
                if (color != null) {
                    this.getLabelRenderer().setColor(color);
                }
            }
        }
        if (attributeManager != null) {
            Color darker = (Color)attributeManager.getAttribute("edge.linecolor", edge);
            if (darker != null) {
                if (edge.getSource().equals(graphPanel.getHoverElement()) || edge.getTarget().equals(graphPanel.getHoverElement()) || edge.equals(graphPanel.getHoverElement())) {
                    darker = darker.darker();
                }
                this.getLineRenderer().setColor(darker);
            }
            final float[] array = (float[])attributeManager.getAttribute("edge.stroke", edge);
            final float floatValue = (float)attributeManager.getAttribute("edge.linewidth", edge);
            if (array != null && array.length > 0) {
                this.lineStroke = new BasicStroke(floatValue, 0, 2, 10.0f, array, 0.0f);
            }
            else if (array != null && array.length == 0) {
                this.lineStroke = null;
            }
            else {
                this.lineStroke = new BasicStroke(floatValue, 0, 2);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.lineStroke == null) {
            return;
        }
        ((Graphics2D)graphics).setStroke(this.lineStroke);
        this.getLineRenderer().getComponent().paint(graphics);
        if (this.labelVisible) {
            graphics.translate(this.getLabelRenderer().getComponent().getX(), this.getLabelRenderer().getComponent().getY());
            this.getLabelRenderer().getComponent().paint(graphics);
        }
    }
    
    public Component getComponent() {
        return this;
    }
}
