// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import java.awt.Point;
import java.awt.Graphics;
import hypergraph.hyperbolic.StubRenderer;
import java.awt.Font;
import hypergraph.graphApi.AttributeManager;
import java.awt.Component;
import hypergraph.graphApi.Element;
import java.awt.Color;
import hypergraph.hyperbolic.ModelPanel;
import hypergraph.hyperbolic.ModelPoint;
import javax.swing.Icon;
import hypergraph.graphApi.Node;
import hypergraph.hyperbolic.TextRenderer;
import javax.swing.JComponent;

public class DefaultNodeRenderer extends JComponent implements NodeRenderer
{
    private TextRenderer textRenderer;
    protected GraphPanel graphPanel;
    protected Node node;
    protected Icon icon;
    
    public void setTextRenderer(final TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
    }
    
    public TextRenderer getTextRenderer() {
        if (this.textRenderer == null) {
            this.textRenderer = this.graphPanel.getTextRenderer();
        }
        return this.textRenderer;
    }
    
    public void configure(final GraphPanel graphPanel, final ModelPoint modelPoint, final Node node) {
        this.graphPanel = graphPanel;
        this.node = node;
        if (node == null) {
            this.getTextRenderer().configure(graphPanel, null, null);
        }
        else {
            int iconWidth = 0;
            int iconHeight = 0;
            int n = 0;
            this.getTextRenderer().configure(graphPanel, modelPoint, node.getLabel());
            final Component component = this.getTextRenderer().getComponent();
            final AttributeManager attributeManager = graphPanel.getGraph().getAttributeManager();
            final Color color = (Color)attributeManager.getAttribute("node.color", node);
            if (color != null) {
                this.getTextRenderer().setColor(color);
            }
            final Color color2 = (Color)attributeManager.getAttribute("node.bkcolor", node);
            this.getTextRenderer().setBackground(color2);
            this.setBackground(color2);
            if (graphPanel.getSelectionModel().isElementSelected(node)) {
                this.setFont(this.getFont().deriveFont(1));
            }
            if (this.graphPanel.hasExpander(node)) {
                n = 10;
            }
            this.icon = (Icon)attributeManager.getAttribute("node.icon", node);
            if (this.icon != null) {
                iconHeight = this.icon.getIconHeight();
                iconWidth = this.icon.getIconWidth();
            }
            this.setSize(iconWidth + component.getWidth() + n, Math.max(iconHeight, component.getHeight()));
            this.setLocation(component.getX() - (this.getWidth() - component.getWidth()) / 2, component.getY() - (this.getHeight() - component.getHeight()) / 2);
            component.setLocation(this.getX() + iconWidth, this.getY() + (this.getHeight() - component.getHeight()) / 2);
            if (graphPanel.getHoverElement() == node) {
                if (this.getBackground() == null) {
                    final Color background = graphPanel.getBackground();
                    this.setBackground(new Color(background.getRed(), background.getGreen(), background.getBlue(), 224));
                }
                this.setFont(this.getFont().deriveFont(1));
            }
        }
    }
    
    public Font getFont() {
        return this.getTextRenderer().getComponent().getFont();
    }
    
    public void setFont(final Font font) {
        this.getTextRenderer().getComponent().setFont(font);
    }
    
    public Component getComponent() {
        if (this.getTextRenderer().getComponent() instanceof StubRenderer) {
            return this.getTextRenderer().getComponent();
        }
        return this;
    }
    
    public void paintExpander(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Color.lightGray);
        graphics.drawRect(n, n2, n3, n4);
        graphics.drawLine(n + 2, n2 + n4 / 2, n + n3 - 2, n2 + n4 / 2);
        if (!this.graphPanel.isExpanded(this.node)) {
            graphics.drawLine(n + n3 / 2, n2 + 2, n + n3 / 2, n2 + n4 - 2);
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        if (this.getBackground() != null) {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        if (this.graphPanel.hasExpander(this.node)) {
            this.paintExpander(graphics, this.getWidth() - 9, 0, 8, 8);
        }
        if (this.icon != null) {
            this.icon.paintIcon(this, graphics, 0, (this.getHeight() - this.icon.getIconHeight()) / 2);
            graphics.translate(this.icon.getIconWidth(), 0);
        }
        this.getTextRenderer().getComponent().paint(graphics);
    }
    
    public boolean expanderHit(final Point point) {
        if (!this.graphPanel.hasExpander(this.node)) {
            return false;
        }
        final int n = point.x - this.getX() - this.getWidth();
        final int n2 = point.y - this.getY();
        return n >= -10 && n <= 0 && n2 >= 0 && n2 <= 10;
    }
}
