// 
// Decompiled by Procyon v0.5.30
// 

package jfig.java2d;

import java.awt.Stroke;
import java.awt.Paint;
import jfig.objects.FillPatterns;
import java.awt.Graphics2D;
import jfig.canvas.FigTrafo2D;
import java.awt.Graphics;
import jfig.objects.FigBbox;
import jfig.objects.FigAttribs;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import java.awt.Shape;
import jfig.objects.FigRectangle;
import jfig.objects.FigRenderer;

public class FigRectangleRenderer implements FigRenderer
{
    FigRectangle rectangle;
    Shape rect2D;
    BasicStroke stroke;
    AffineTransform cachedAffineTransform;
    
    public void rebuild() {
        this.createRectangle();
        this.stroke = FigTools2D.createStroke(this.rectangle.getAttributes());
    }
    
    public void createRectangle() {
        final FigAttribs attributes = this.rectangle.getAttributes();
        final FigBbox bbox = this.rectangle.getBbox();
        if (attributes.cornerRadius > 0) {
            final float n = 2.0f * attributes.cornerRadius;
            this.rect2D = new RoundRectangle2D.Float(bbox.getXl(), bbox.getYt(), bbox.getXr() - bbox.getXl(), bbox.getYb() - bbox.getYt(), n, n);
        }
        else {
            this.rect2D = new Rectangle2D.Float(bbox.getXl(), bbox.getYt(), bbox.getXr() - bbox.getXl(), bbox.getYb() - bbox.getYt());
        }
    }
    
    public void paint(final Graphics graphics) {
        this.paint(graphics, this.rectangle.getTrafo());
    }
    
    public void paint(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        if (!this.rectangle.isVisible()) {
            return;
        }
        try {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            final FigAttribs attributes = this.rectangle.getAttributes();
            final AffineTransform transform = graphics2D.getTransform();
            final Stroke stroke = graphics2D.getStroke();
            if (this.cachedAffineTransform != transform) {
                graphics2D.setTransform(this.cachedAffineTransform = FigTools2D.createCompoundTransform(transform, figTrafo2D));
            }
            if (attributes.fig_area_fill > 40) {
                graphics2D.setPaint(FillPatterns.getTexturePaint(attributes.fig_area_fill - 40 + 20, 32, attributes.lineColor, attributes.fillColor));
                graphics2D.fill(this.rect2D);
            }
            else if (attributes.fillStyle == 2) {
                graphics2D.setColor(attributes.fillColor);
                graphics2D.fill(this.rect2D);
            }
            graphics2D.setColor(attributes.lineColor);
            if (this.stroke != null) {
                graphics2D.setStroke(this.stroke);
            }
            if (this.stroke.getLineWidth() > 0.0) {
                graphics2D.draw(this.rect2D);
            }
            graphics2D.setTransform(transform);
            graphics2D.setStroke(stroke);
            final FigBbox bbox = this.rectangle.getBbox();
            if (this.rectangle.isShowPoints()) {
                FigTools2D.showPoints(graphics, figTrafo2D, bbox);
            }
            if (this.rectangle.isSelected()) {
                FigTools2D.showSelected(graphics, figTrafo2D, bbox);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public FigRectangleRenderer(final FigRectangle rectangle) {
        this.rectangle = rectangle;
        this.rebuild();
    }
}
