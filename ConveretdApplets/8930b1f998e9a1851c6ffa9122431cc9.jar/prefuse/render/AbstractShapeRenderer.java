// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import java.awt.geom.Point2D;
import java.awt.BasicStroke;
import prefuse.util.GraphicsLib;
import java.awt.Shape;
import prefuse.visual.VisualItem;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public abstract class AbstractShapeRenderer implements Renderer
{
    public static final int RENDER_TYPE_NONE = 0;
    public static final int RENDER_TYPE_DRAW = 1;
    public static final int RENDER_TYPE_FILL = 2;
    public static final int RENDER_TYPE_DRAW_AND_FILL = 3;
    private int m_renderType;
    protected AffineTransform m_transform;
    protected boolean m_manageBounds;
    
    public AbstractShapeRenderer() {
        this.m_renderType = 3;
        this.m_transform = new AffineTransform();
        this.m_manageBounds = true;
    }
    
    public void setManageBounds(final boolean manageBounds) {
        this.m_manageBounds = manageBounds;
    }
    
    public void render(final Graphics2D graphics2D, final VisualItem visualItem) {
        final Shape shape = this.getShape(visualItem);
        if (shape != null) {
            this.drawShape(graphics2D, visualItem, shape);
        }
    }
    
    protected void drawShape(final Graphics2D graphics2D, final VisualItem visualItem, final Shape shape) {
        GraphicsLib.paint(graphics2D, visualItem, shape, this.getStroke(visualItem), this.getRenderType(visualItem));
    }
    
    public Shape getShape(final VisualItem visualItem) {
        final AffineTransform transform = this.getTransform(visualItem);
        final Shape rawShape = this.getRawShape(visualItem);
        return (transform == null || rawShape == null) ? rawShape : transform.createTransformedShape(rawShape);
    }
    
    protected BasicStroke getStroke(final VisualItem visualItem) {
        return visualItem.getStroke();
    }
    
    protected abstract Shape getRawShape(final VisualItem p0);
    
    protected AffineTransform getTransform(final VisualItem visualItem) {
        return null;
    }
    
    public int getRenderType(final VisualItem visualItem) {
        return this.m_renderType;
    }
    
    public void setRenderType(final int renderType) {
        if (renderType < 0 || renderType > 3) {
            throw new IllegalArgumentException("Unrecognized render type.");
        }
        this.m_renderType = renderType;
    }
    
    public boolean locatePoint(final Point2D point2D, final VisualItem visualItem) {
        if (visualItem.getBounds().contains(point2D)) {
            final Shape shape = this.getShape(visualItem);
            return shape != null && shape.contains(point2D);
        }
        return false;
    }
    
    public void setBounds(final VisualItem visualItem) {
        if (!this.m_manageBounds) {
            return;
        }
        final Shape shape = this.getShape(visualItem);
        if (shape == null) {
            visualItem.setBounds(visualItem.getX(), visualItem.getY(), 0.0, 0.0);
        }
        else {
            GraphicsLib.setBounds(visualItem, shape, this.getStroke(visualItem));
        }
    }
}
