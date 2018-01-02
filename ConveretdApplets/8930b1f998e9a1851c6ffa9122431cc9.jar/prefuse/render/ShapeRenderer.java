// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import java.awt.Shape;
import prefuse.visual.VisualItem;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

public class ShapeRenderer extends AbstractShapeRenderer
{
    private int m_baseSize;
    private Ellipse2D m_ellipse;
    private Rectangle2D m_rect;
    private GeneralPath m_path;
    
    public ShapeRenderer() {
        this.m_baseSize = 10;
        this.m_ellipse = new Ellipse2D.Double();
        this.m_rect = new Rectangle2D.Double();
        this.m_path = new GeneralPath();
    }
    
    public ShapeRenderer(final int baseSize) {
        this.m_baseSize = 10;
        this.m_ellipse = new Ellipse2D.Double();
        this.m_rect = new Rectangle2D.Double();
        this.m_path = new GeneralPath();
        this.setBaseSize(baseSize);
    }
    
    public void setBaseSize(final int baseSize) {
        this.m_baseSize = baseSize;
    }
    
    public int getBaseSize() {
        return this.m_baseSize;
    }
    
    protected Shape getRawShape(final VisualItem visualItem) {
        final int shape = visualItem.getShape();
        double x = visualItem.getX();
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            x = 0.0;
        }
        double y = visualItem.getY();
        if (Double.isNaN(y) || Double.isInfinite(y)) {
            y = 0.0;
        }
        final double n = this.m_baseSize * visualItem.getSize();
        if (n > 1.0) {
            x -= n / 2.0;
            y -= n / 2.0;
        }
        switch (shape) {
            case -1: {
                return null;
            }
            case 0: {
                return this.rectangle(x, y, n, n);
            }
            case 1: {
                return this.ellipse(x, y, n, n);
            }
            case 5: {
                return this.triangle_up((float)x, (float)y, (float)n);
            }
            case 6: {
                return this.triangle_down((float)x, (float)y, (float)n);
            }
            case 7: {
                return this.triangle_left((float)x, (float)y, (float)n);
            }
            case 8: {
                return this.triangle_right((float)x, (float)y, (float)n);
            }
            case 3: {
                return this.cross((float)x, (float)y, (float)n);
            }
            case 4: {
                return this.star((float)x, (float)y, (float)n);
            }
            case 9: {
                return this.hexagon((float)x, (float)y, (float)n);
            }
            case 2: {
                return this.diamond((float)x, (float)y, (float)n);
            }
            default: {
                throw new IllegalStateException("Unknown shape type: " + shape);
            }
        }
    }
    
    public Shape rectangle(final double n, final double n2, final double n3, final double n4) {
        this.m_rect.setFrame(n, n2, n3, n4);
        return this.m_rect;
    }
    
    public Shape ellipse(final double n, final double n2, final double n3, final double n4) {
        this.m_ellipse.setFrame(n, n2, n3, n4);
        return this.m_ellipse;
    }
    
    public Shape triangle_up(final float n, final float n2, final float n3) {
        this.m_path.reset();
        this.m_path.moveTo(n, n2 + n3);
        this.m_path.lineTo(n + n3 / 2.0f, n2);
        this.m_path.lineTo(n + n3, n2 + n3);
        this.m_path.closePath();
        return this.m_path;
    }
    
    public Shape triangle_down(final float n, final float n2, final float n3) {
        this.m_path.reset();
        this.m_path.moveTo(n, n2);
        this.m_path.lineTo(n + n3, n2);
        this.m_path.lineTo(n + n3 / 2.0f, n2 + n3);
        this.m_path.closePath();
        return this.m_path;
    }
    
    public Shape triangle_left(final float n, final float n2, final float n3) {
        this.m_path.reset();
        this.m_path.moveTo(n + n3, n2);
        this.m_path.lineTo(n + n3, n2 + n3);
        this.m_path.lineTo(n, n2 + n3 / 2.0f);
        this.m_path.closePath();
        return this.m_path;
    }
    
    public Shape triangle_right(final float n, final float n2, final float n3) {
        this.m_path.reset();
        this.m_path.moveTo(n, n2 + n3);
        this.m_path.lineTo(n + n3, n2 + n3 / 2.0f);
        this.m_path.lineTo(n, n2);
        this.m_path.closePath();
        return this.m_path;
    }
    
    public Shape cross(final float n, final float n2, final float n3) {
        final float n4 = 3.0f * n3 / 8.0f;
        final float n5 = 5.0f * n3 / 8.0f;
        this.m_path.reset();
        this.m_path.moveTo(n + n4, n2);
        this.m_path.lineTo(n + n5, n2);
        this.m_path.lineTo(n + n5, n2 + n4);
        this.m_path.lineTo(n + n3, n2 + n4);
        this.m_path.lineTo(n + n3, n2 + n5);
        this.m_path.lineTo(n + n5, n2 + n5);
        this.m_path.lineTo(n + n5, n2 + n3);
        this.m_path.lineTo(n + n4, n2 + n3);
        this.m_path.lineTo(n + n4, n2 + n5);
        this.m_path.lineTo(n, n2 + n5);
        this.m_path.lineTo(n, n2 + n4);
        this.m_path.lineTo(n + n4, n2 + n4);
        this.m_path.closePath();
        return this.m_path;
    }
    
    public Shape star(final float n, final float n2, final float n3) {
        final float n4 = (float)(n3 / (2.0 * Math.sin(Math.toRadians(54.0))));
        final float n5 = (float)(n3 / (2.0 * Math.tan(Math.toRadians(54.0))));
        final float n6 = (float)(n4 * Math.sin(Math.toRadians(18.0)));
        final float n7 = (float)(n4 * Math.cos(Math.toRadians(18.0)));
        final float n8 = (float)(n4 / (2.0 * Math.cos(Math.toRadians(36.0))));
        final float n9 = n8 * (float)Math.sin(Math.toRadians(36.0));
        final float n10 = n8 * (float)Math.cos(Math.toRadians(36.0));
        this.m_path.reset();
        this.m_path.moveTo(n, n2 + n5);
        this.m_path.lineTo(n + n8, n2 + n5);
        this.m_path.lineTo(n + n3 / 2.0f, n2);
        this.m_path.lineTo(n + n3 - n8, n2 + n5);
        this.m_path.lineTo(n + n3, n2 + n5);
        this.m_path.lineTo(n + n3 - n10, n2 + n5 + n9);
        this.m_path.lineTo(n + n3 - n6, n2 + n3);
        this.m_path.lineTo(n + n3 / 2.0f, n2 + n5 + n7 - n9);
        this.m_path.lineTo(n + n6, n2 + n3);
        this.m_path.lineTo(n + n10, n2 + n5 + n9);
        this.m_path.closePath();
        return this.m_path;
    }
    
    public Shape hexagon(final float n, final float n2, final float n3) {
        final float n4 = n3 / 2.0f;
        this.m_path.reset();
        this.m_path.moveTo(n, n2 + 0.5f * n3);
        this.m_path.lineTo(n + 0.5f * n4, n2);
        this.m_path.lineTo(n + 1.5f * n4, n2);
        this.m_path.lineTo(n + 2.0f * n4, n2 + 0.5f * n3);
        this.m_path.lineTo(n + 1.5f * n4, n2 + n3);
        this.m_path.lineTo(n + 0.5f * n4, n2 + n3);
        this.m_path.closePath();
        return this.m_path;
    }
    
    public Shape diamond(final float n, final float n2, final float n3) {
        this.m_path.reset();
        this.m_path.moveTo(n, n2 + 0.5f * n3);
        this.m_path.lineTo(n + 0.5f * n3, n2);
        this.m_path.lineTo(n + n3, n2 + 0.5f * n3);
        this.m_path.lineTo(n + 0.5f * n3, n2 + n3);
        this.m_path.closePath();
        return this.m_path;
    }
}
