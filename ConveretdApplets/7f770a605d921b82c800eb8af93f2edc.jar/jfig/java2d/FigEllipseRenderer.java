// 
// Decompiled by Procyon v0.5.30
// 

package jfig.java2d;

import jfig.objects.FigAttribs;
import java.awt.Stroke;
import java.awt.Paint;
import jfig.objects.FillPatterns;
import java.awt.Graphics2D;
import java.awt.Graphics;
import jfig.canvas.FigTrafo2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import java.awt.Shape;
import jfig.objects.FigEllipse;
import jfig.objects.FigRenderer;

public class FigEllipseRenderer implements FigRenderer
{
    FigEllipse ellipse;
    Shape ellipse2D;
    BasicStroke stroke;
    AffineTransform cachedAffineTransform;
    
    public void rebuild() {
        this.stroke = FigTools2D.createStroke(this.ellipse.getAttributes());
        final Point centerPoint = this.ellipse.getCenterPoint();
        final Point radiusPoint = this.ellipse.getRadiusPoint();
        this.ellipse2D = new Ellipse2D.Double(centerPoint.x - radiusPoint.x, centerPoint.y - radiusPoint.y, 2.0 * radiusPoint.x, 2.0 * radiusPoint.y);
    }
    
    public AffineTransform createCompoundTransform(final AffineTransform affineTransform, final FigTrafo2D figTrafo2D) {
        final double n = -this.ellipse.getAttributes().fig_ellipse_angle;
        final double n2 = figTrafo2D.getZoomFactor() / 32.0;
        final double n3 = this.ellipse.getCenterPoint().x;
        final double n4 = this.ellipse.getCenterPoint().y;
        final AffineTransform affineTransform2 = new AffineTransform();
        affineTransform2.scale(n2, n2);
        affineTransform2.translate(-figTrafo2D.getAnchor().x, -figTrafo2D.getAnchor().y);
        affineTransform2.rotate(n, n3, n4);
        affineTransform2.preConcatenate(affineTransform);
        return affineTransform2;
    }
    
    public void paint(final Graphics graphics) {
        this.paint(graphics, this.ellipse.getTrafo());
    }
    
    public void paint(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        if (!this.ellipse.isVisible()) {
            return;
        }
        try {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            final FigAttribs attributes = this.ellipse.getAttributes();
            final AffineTransform transform = graphics2D.getTransform();
            final Stroke stroke = graphics2D.getStroke();
            if (this.cachedAffineTransform != transform) {
                graphics2D.setTransform(this.cachedAffineTransform = this.createCompoundTransform(transform, figTrafo2D));
            }
            graphics2D.setTransform(this.cachedAffineTransform);
            if (attributes.fig_area_fill >= 40) {
                graphics2D.setPaint(FillPatterns.getTexturePaint(attributes.fig_area_fill - 40 + 20, 32, attributes.lineColor, attributes.fillColor));
                graphics2D.fill(this.ellipse2D);
            }
            else if (attributes.fillStyle == 2) {
                graphics2D.setColor(attributes.fillColor);
                graphics2D.fill(this.ellipse2D);
            }
            graphics2D.setColor(attributes.lineColor);
            graphics2D.setStroke(this.stroke);
            if (this.stroke.getLineWidth() > 0.0) {
                graphics2D.draw(this.ellipse2D);
            }
            graphics2D.setTransform(transform);
            graphics2D.setStroke(stroke);
            if (this.ellipse.isSelected()) {
                FigTools2D.showSelected(graphics, figTrafo2D, this.ellipse.getPoints());
            }
            if (this.ellipse.isShowPoints()) {
                FigTools2D.showPoints(graphics, figTrafo2D, this.ellipse.getPoints());
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public Point[] getRotatedPoints(final Point[] array, final double n) {
        final Point centerPoint = this.ellipse.getCenterPoint();
        final Point[] array2 = new Point[array.length];
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        for (int i = 0; i < array.length; ++i) {
            final double n2 = array[i].x - centerPoint.x;
            final double n3 = array[i].y - centerPoint.y;
            array2[i] = new Point((int)(n2 * cos - n3 * sin + centerPoint.x), (int)(n2 * sin + n3 * cos + centerPoint.y));
        }
        return array2;
    }
    
    public FigEllipseRenderer(final FigEllipse ellipse) {
        this.ellipse = ellipse;
    }
}
