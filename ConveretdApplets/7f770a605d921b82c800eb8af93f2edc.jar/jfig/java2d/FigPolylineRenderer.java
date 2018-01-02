// 
// Decompiled by Procyon v0.5.30
// 

package jfig.java2d;

import java.awt.Point;
import jfig.objects.FigAttribs;
import java.awt.Stroke;
import java.awt.Shape;
import java.awt.Graphics2D;
import jfig.canvas.FigTrafo2D;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import java.awt.geom.GeneralPath;
import jfig.objects.FigPolyline;
import jfig.objects.FigRenderer;

public class FigPolylineRenderer implements FigRenderer
{
    public static final long NEEDS_UPDATE = 0L;
    FigPolyline polyline;
    GeneralPath path2D;
    BasicStroke stroke;
    AffineTransform cachedAffineTransform;
    
    public void rebuild() {
        this.path2D = FigTools2D.createPath(this.polyline.getPoints(), this.polyline.isClosed());
        this.stroke = FigTools2D.createStroke(this.polyline.getAttributes());
    }
    
    public void paint(final Graphics graphics) {
        this.paint(graphics, this.polyline.getTrafo());
    }
    
    public void paint(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        if (!this.polyline.isVisible()) {
            return;
        }
        try {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            final FigAttribs attributes = this.polyline.getAttributes();
            final AffineTransform transform = graphics2D.getTransform();
            final Stroke stroke = graphics2D.getStroke();
            final Point[] points = this.polyline.getPoints();
            if (this.cachedAffineTransform != transform) {
                graphics2D.setTransform(this.cachedAffineTransform = FigTools2D.createCompoundTransform(transform, figTrafo2D));
            }
            if (attributes.fig_area_fill > 40) {
                graphics2D.setPaint(FigTools2D.getTexture(attributes));
                graphics2D.fill(this.path2D);
            }
            else if (attributes.fillStyle == 2) {
                graphics2D.setColor(attributes.fillColor);
                graphics2D.fill(this.path2D);
            }
            graphics2D.setColor(attributes.lineColor);
            graphics2D.setStroke(this.stroke);
            if (this.stroke.getLineWidth() > 0.0) {
                graphics2D.draw(this.path2D);
            }
            if (points.length >= 2) {
                if ((attributes.arrowMode & 0x2) != 0x0) {
                    FigArrow2D.renderBackArrow(graphics2D, attributes, points[1], points[0]);
                }
                if ((attributes.arrowMode & 0x1) != 0x0) {
                    if (this.polyline.isClosed()) {
                        FigArrow2D.renderFrontArrow(graphics2D, attributes, points[points.length - 1], points[0]);
                    }
                    else {
                        FigArrow2D.renderFrontArrow(graphics2D, attributes, points[points.length - 2], points[points.length - 1]);
                    }
                }
            }
            graphics2D.setTransform(transform);
            graphics2D.setStroke(stroke);
            if (this.polyline.isShowPoints()) {
                FigTools2D.showPoints(graphics, figTrafo2D, points);
            }
            if (this.polyline.isSelected()) {
                FigTools2D.showSelected(graphics, figTrafo2D, points);
            }
        }
        catch (Throwable t) {
            System.err.println("-E- FigPolylineRenderer(2D).paint: " + t);
            t.printStackTrace();
        }
    }
    
    public FigPolylineRenderer(final FigPolyline polyline) {
        this.polyline = polyline;
        this.rebuild();
    }
}
