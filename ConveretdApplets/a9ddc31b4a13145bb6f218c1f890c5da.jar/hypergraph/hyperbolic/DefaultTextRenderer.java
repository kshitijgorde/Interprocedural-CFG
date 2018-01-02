// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.Component;
import java.util.Iterator;
import java.awt.Graphics2D;
import java.awt.Graphics;
import hypergraph.graphApi.io.CSSColourParser;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComponent;

public class DefaultTextRenderer extends JComponent implements TextRenderer
{
    protected ModelPanel modelPanel;
    protected String text;
    protected JComponent stubRenderer;
    protected boolean useStubRenderer;
    private int maxTextLength;
    private Color color;
    private Color background;
    private Font font1;
    private Font font2;
    private Font font3;
    private Font font4;
    private double scale1;
    private double scale2;
    private double scale3;
    private double scale4;
    private double optimalRatio;
    private List lines;
    
    public DefaultTextRenderer() {
        this.stubRenderer = new EmptyComponent();
        this.maxTextLength = -1;
        this.optimalRatio = 8.0;
        this.setOpaque(false);
        this.useStubRenderer = true;
        this.lines = new ArrayList();
    }
    
    public void configure(final ModelPanel modelPanel) {
        this.configure(modelPanel, null, null);
    }
    
    public int getMaxTextLength() {
        return this.maxTextLength;
    }
    
    public void setMaxTextLength(final int maxTextLength) {
        this.maxTextLength = maxTextLength;
    }
    
    public Font getScaledFont(final Point2D point2D) {
        final double max = Math.max(point2D.getX(), point2D.getY());
        Font font = null;
        if (max > this.scale1) {
            font = this.font1;
        }
        if (max <= this.scale1 && max > this.scale2) {
            font = this.font2;
        }
        if (max <= this.scale2 && max > this.scale3) {
            font = this.font3;
        }
        if (max <= this.scale3) {
            font = this.font4;
        }
        return font;
    }
    
    protected boolean isDelimiter(final char c) {
        return c == ' ' || c == ',' || c == ';' || c == '-' || c == '/' || c == '\\';
    }
    
    public void configure(final ModelPanel modelPanel, final ModelPoint modelPoint, String text) {
        this.modelPanel = modelPanel;
        this.setColor(null);
        if (modelPoint == null || text == null) {
            this.useStubRenderer = true;
            return;
        }
        final String s = (String)modelPanel.getPropertyManager().getProperty("hypergraph.hyperbolic.text.maxLength");
        if (this.maxTextLength == -1 && s != null) {
            this.maxTextLength = Integer.parseInt(s);
        }
        if (this.font1 == null) {
            String string = modelPanel.getPropertyManager().getString("hypergraph.hyperbolic.text.fontName");
            if (string == null) {
                string = "Arial";
            }
            final Font font = new Font(string, 0, 1);
            this.font1 = font.deriveFont((float)(double)modelPanel.getPropertyManager().getDouble("hypergraph.hyperbolic.text.size1", new Double(12.0)));
            this.font2 = font.deriveFont((float)(double)modelPanel.getPropertyManager().getDouble("hypergraph.hyperbolic.text.size2", new Double(10.0)));
            this.font3 = font.deriveFont((float)(double)modelPanel.getPropertyManager().getDouble("hypergraph.hyperbolic.text.size3", new Double(8.0)));
            this.font4 = font.deriveFont((float)(double)modelPanel.getPropertyManager().getDouble("hypergraph.hyperbolic.text.size4", new Double(0.0)));
            this.scale1 = modelPanel.getPropertyManager().getDouble("hypergraph.hyperbolic.text.scale1", new Double(0.75));
            this.scale2 = modelPanel.getPropertyManager().getDouble("hypergraph.hyperbolic.text.scale2", new Double(0.5));
            this.scale3 = modelPanel.getPropertyManager().getDouble("hypergraph.hyperbolic.text.scale3", new Double(0.25));
        }
        final Point project = modelPanel.project(modelPoint);
        final Font scaledFont = this.getScaledFont(modelPanel.getScale(modelPoint));
        if (scaledFont == null) {
            this.useStubRenderer = true;
            return;
        }
        this.useStubRenderer = false;
        this.setFont(scaledFont);
        text = text.trim();
        if (this.maxTextLength >= 2 && text.length() > this.maxTextLength) {
            text = text.substring(0, this.maxTextLength - 2) + "..";
        }
        this.text = text;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont().deriveFont(1));
        final double n = SwingUtilities.computeStringWidth(fontMetrics, text);
        final double n2 = fontMetrics.getHeight();
        double n3 = 0.0;
        this.lines.clear();
        if (n / n2 < this.optimalRatio) {
            this.lines.add(0, text);
            n3 = n;
        }
        else {
            final double sqrt = Math.sqrt(n * n2 * this.optimalRatio);
            int n4 = 0;
            double n5 = 0.0;
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < text.length(); ++i) {
                if (this.isDelimiter(text.charAt(i))) {
                    final String substring = text.substring(n4, i + 1);
                    n4 = i + 1;
                    final double n6 = SwingUtilities.computeStringWidth(fontMetrics, substring);
                    if (n6 + n5 > sqrt) {
                        this.lines.add(sb.toString().trim());
                        sb.setLength(0);
                        n5 = 0.0;
                    }
                    sb.append(substring);
                    n5 += n6;
                    if (n5 > n3) {
                        n3 = n5;
                    }
                }
            }
            if (sb.length() == 0) {
                sb.append(text);
                n3 = SwingUtilities.computeStringWidth(fontMetrics, text);
            }
            else {
                final String substring2 = text.substring(n4, text.length());
                final double n7 = SwingUtilities.computeStringWidth(fontMetrics, substring2);
                if (n7 + n5 > sqrt) {
                    this.lines.add(sb.toString().trim());
                    sb.setLength(0);
                    n5 = 0.0;
                }
                sb.append(substring2);
                final double n8 = n5 + n7;
                if (n8 > n3) {
                    n3 = n8;
                }
            }
            this.lines.add(sb.toString().trim());
        }
        this.setSize(new Dimension((int)Math.round(n3), (int)Math.round((double)(fontMetrics.getHeight() * this.lines.size()))));
        project.translate(-this.getWidth() / 2, -this.getHeight() / 2);
        this.setLocation(project);
    }
    
    public Color getColor() {
        if (this.color != null) {
            return this.color;
        }
        return CSSColourParser.stringToColor(this.modelPanel.getPropertyManager().getString("hypergraph.hyperbolic.text.color"));
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public Color getBackground() {
        if (this.background != null) {
            return this.background;
        }
        final String string = this.modelPanel.getPropertyManager().getString("hypergraph.hyperbolic.text.backgroundColor");
        if (string == null) {
            return null;
        }
        return CSSColourParser.stringToColor(string);
    }
    
    public void setBackground(final Color background) {
        this.background = background;
    }
    
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(this.getColor());
        if (this.getBackground() != null) {
            ((Graphics2D)graphics).setBackground(this.getBackground());
            graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        int n = 0;
        for (final String s : this.lines) {
            final int computeStringWidth = SwingUtilities.computeStringWidth(fontMetrics, s);
            n += fontMetrics.getAscent();
            graphics.drawString(s, (this.getWidth() - computeStringWidth) / 2, n);
        }
    }
    
    public Component getComponent() {
        if (this.useStubRenderer) {
            return this.stubRenderer;
        }
        return this;
    }
}
