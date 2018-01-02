import java.awt.Polygon;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class WordBubble
{
    public static final char SPLIT_CHAR = '|';
    public static final int HORIZ_ARC_EXTENT = 12;
    public static final int VERT_ARC_EXTENT = 10;
    private Component parent;
    private String[] lines;
    Color foreground;
    Color background;
    private Font textfont;
    private Point endpoint;
    private Point centerpoint;
    private Rectangle bubblerect;
    private FontMetrics textfontmetrics;
    
    public WordBubble(final String s, final Component parent) {
        final Vector vector = new Vector<String>();
        this.parent = parent;
        int n = 0;
        for (int i = s.indexOf(124, n); i >= 0; i = s.indexOf(124, n)) {
            vector.addElement(s.substring(n, i).trim());
            n = i + 1;
        }
        vector.addElement(s.substring(n).trim());
        this.lines = new String[vector.size()];
        for (int j = 0; j < this.lines.length; ++j) {
            this.lines[j] = vector.elementAt(j);
        }
        this.background = Color.white;
        this.foreground = Color.black;
        this.textfont = null;
        this.endpoint = null;
        this.centerpoint = null;
        this.bubblerect = null;
        this.textfontmetrics = null;
    }
    
    public void setParent(final Component parent) {
        this.parent = parent;
    }
    
    public void setTextFont(final Font textfont) {
        this.textfont = textfont;
        this.bubblerect = null;
        this.textfontmetrics = null;
    }
    
    public void setEndpoint(final Point endpoint) {
        this.endpoint = endpoint;
    }
    
    public void setCenterpoint(final Point centerpoint) {
        this.centerpoint = centerpoint;
        this.bubblerect = null;
    }
    
    private void ensureFontMetrics(final Graphics graphics) {
        if (this.textfontmetrics == null) {
            this.textfontmetrics = graphics.getFontMetrics(this.textfont);
        }
    }
    
    public void recomputeRect(final Graphics graphics) {
        final Dimension dimension = new Dimension();
        this.ensureFontMetrics(graphics);
        dimension.height = this.lines.length * this.textfontmetrics.getHeight();
        dimension.width = 0;
        for (int i = 0; i < this.lines.length; ++i) {
            final int stringWidth = this.textfontmetrics.stringWidth(this.lines[i]);
            if (stringWidth > dimension.width) {
                dimension.width = stringWidth;
            }
        }
        final Dimension dimension2 = dimension;
        dimension2.width += 18;
        final Dimension dimension3 = dimension;
        dimension3.height += 15;
        this.bubblerect = new Rectangle(this.centerpoint.x - dimension.width / 2, this.centerpoint.y - dimension.height / 2, dimension.width, dimension.height);
        int n = 0;
        int n2 = 0;
        final Dimension size = this.parent.size();
        if (this.bubblerect.x < 0) {
            n = -this.bubblerect.x;
        }
        else if (this.bubblerect.x + dimension.width > size.width - 1) {
            n = size.width - 1 - (this.bubblerect.x + dimension.width);
        }
        if (this.bubblerect.y < 0) {
            n2 = -this.bubblerect.y;
        }
        else if (this.bubblerect.y + dimension.height > size.height - 1) {
            n2 = size.height - 1 - (this.bubblerect.y + dimension.height);
        }
        this.bubblerect.translate(n, n2);
        this.centerpoint.x = this.bubblerect.x + this.bubblerect.width / 2;
        this.centerpoint.y = this.bubblerect.y + this.bubblerect.height / 2;
    }
    
    public synchronized void draw(final Graphics graphics) {
        if (this.bubblerect == null) {
            this.recomputeRect(graphics);
        }
        graphics.setColor(this.background);
        graphics.fillRoundRect(this.bubblerect.x, this.bubblerect.y, this.bubblerect.width, this.bubblerect.height, 12, 10);
        graphics.setColor(this.foreground);
        graphics.drawRoundRect(this.bubblerect.x, this.bubblerect.y, this.bubblerect.width, this.bubblerect.height, 12, 10);
        graphics.setColor(this.background);
        final Polygon polygon = new Polygon();
        final int n = (this.endpoint.y > this.centerpoint.y) ? (this.centerpoint.y + this.bubblerect.height / 2 - 1) : (this.centerpoint.y - this.bubblerect.height / 2 + 1);
        polygon.addPoint(this.centerpoint.x - 12, n);
        polygon.addPoint(this.endpoint.x, this.endpoint.y);
        polygon.addPoint(this.centerpoint.x + 12, n);
        graphics.fillPolygon(polygon);
        graphics.setColor(this.foreground);
        graphics.drawLine(this.centerpoint.x - 12, n, this.endpoint.x, this.endpoint.y);
        graphics.drawLine(this.centerpoint.x + 12, n, this.endpoint.x, this.endpoint.y);
        int n2 = this.centerpoint.y - this.bubblerect.height / 2 + 7 + this.textfontmetrics.getMaxAscent();
        for (int i = 0; i < this.lines.length; ++i) {
            final int n3 = this.bubblerect.x + (this.bubblerect.width - this.textfontmetrics.stringWidth(this.lines[i])) / 2;
            graphics.setFont(this.textfont);
            graphics.drawString(this.lines[i], n3, n2);
            n2 += this.textfontmetrics.getHeight();
        }
    }
    
    public void undraw(final Graphics graphics) {
        final Rectangle rectangle = new Rectangle(this.bubblerect.x, this.bubblerect.y, this.bubblerect.width, this.bubblerect.height);
        rectangle.add(this.endpoint);
        rectangle.grow(1, 1);
        this.parent.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
