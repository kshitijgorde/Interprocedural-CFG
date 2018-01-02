// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import java.awt.geom.Point2D;
import prefuse.util.ColorLib;
import java.awt.BasicStroke;
import prefuse.util.GraphicsLib;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.Shape;
import prefuse.visual.VisualItem;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;

public class AxisRenderer extends AbstractShapeRenderer
{
    private Line2D m_line;
    private Rectangle2D m_box;
    private int m_xalign;
    private int m_yalign;
    private int m_ascent;
    
    public AxisRenderer() {
        this(0, 3);
    }
    
    public AxisRenderer(final int xalign, final int yalign) {
        this.m_line = new Line2D.Double();
        this.m_box = new Rectangle2D.Double();
        this.m_xalign = xalign;
        this.m_yalign = yalign;
    }
    
    public void setHorizontalAlignment(final int xalign) {
        this.m_xalign = xalign;
    }
    
    public void setVerticalAlignment(final int yalign) {
        this.m_yalign = yalign;
    }
    
    protected Shape getRawShape(final VisualItem visualItem) {
        final double double1 = visualItem.getDouble(VisualItem.X);
        final double double2 = visualItem.getDouble(VisualItem.Y);
        final double double3 = visualItem.getDouble(VisualItem.X2);
        final double double4 = visualItem.getDouble(VisualItem.Y2);
        this.m_line.setLine(double1, double2, double3, double4);
        if (!visualItem.canGetString(VisualItem.LABEL)) {
            return this.m_line;
        }
        final String string = visualItem.getString(VisualItem.LABEL);
        if (string == null) {
            return this.m_line;
        }
        final FontMetrics fontMetrics = AxisRenderer.DEFAULT_GRAPHICS.getFontMetrics(visualItem.getFont());
        this.m_ascent = fontMetrics.getAscent();
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth(string);
        double n = 0.0;
        switch (this.m_xalign) {
            case 6: {
                n = double3 + 2.0;
                break;
            }
            case 5: {
                n = double1 - stringWidth - 2.0;
                break;
            }
            case 2: {
                n = double1 + (double3 - double1) / 2.0 - stringWidth / 2;
                break;
            }
            case 1: {
                n = double3 - stringWidth;
                break;
            }
            default: {
                n = double1;
                break;
            }
        }
        double n2 = 0.0;
        switch (this.m_yalign) {
            case 8: {
                n2 = double2 - height;
                break;
            }
            case 7: {
                n2 = double4;
                break;
            }
            case 2: {
                n2 = double2 + (double4 - double2) / 2.0 - height / 2;
                break;
            }
            case 4: {
                n2 = double2;
                break;
            }
            default: {
                n2 = double4 - height;
                break;
            }
        }
        this.m_box.setFrame(n, n2, stringWidth, height);
        return this.m_box;
    }
    
    public void render(final Graphics2D graphics2D, final VisualItem visualItem) {
        final Shape shape = this.getShape(visualItem);
        GraphicsLib.paint(graphics2D, visualItem, this.m_line, this.getStroke(visualItem), this.getRenderType(visualItem));
        if (shape == this.m_box) {
            final float n = (float)this.m_box.getMinX();
            final float n2 = (float)this.m_box.getMinY() + this.m_ascent;
            GraphicsLib.paint(graphics2D, visualItem, shape, null, 2);
            final String string = visualItem.getString(VisualItem.LABEL);
            graphics2D.setFont(visualItem.getFont());
            graphics2D.setColor(ColorLib.getColor(visualItem.getTextColor()));
            graphics2D.drawString(string, n, n2);
        }
    }
    
    public boolean locatePoint(final Point2D point2D, final VisualItem visualItem) {
        final Shape shape = this.getShape(visualItem);
        if (shape == null) {
            return false;
        }
        if (shape == this.m_box && this.m_box.contains(point2D)) {
            return true;
        }
        final double max = Math.max(2.0, visualItem.getSize());
        final double n = max / 2.0;
        return shape.intersects(point2D.getX() - n, point2D.getY() - n, max, max);
    }
    
    public void setBounds(final VisualItem visualItem) {
        if (!this.m_manageBounds) {
            return;
        }
        final Shape shape = this.getShape(visualItem);
        if (shape == null) {
            visualItem.setBounds(visualItem.getX(), visualItem.getY(), 0.0, 0.0);
        }
        else if (shape == this.m_line) {
            GraphicsLib.setBounds(visualItem, shape, this.getStroke(visualItem));
        }
        else {
            this.m_box.add(this.m_line.getX1(), this.m_line.getY1());
            this.m_box.add(this.m_line.getX2(), this.m_line.getY2());
            visualItem.setBounds(this.m_box.getMinX(), this.m_box.getMinY(), this.m_box.getWidth(), this.m_box.getHeight());
        }
    }
}
