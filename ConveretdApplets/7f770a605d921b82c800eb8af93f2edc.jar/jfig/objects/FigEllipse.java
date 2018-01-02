// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.gui.ColorCache;
import java.awt.Color;
import java.awt.Graphics;
import jfig.canvas.FigTrafo2D;
import java.awt.Point;

public class FigEllipse extends FigBaseobject
{
    FigRenderer renderer;
    Point center;
    Point size;
    
    public void createRenderer() {
        this.renderer = FigObjectFactory.getDefaultObjectFactory().createEllipseRenderer(this);
    }
    
    public void setAttributes(final FigAttribs attribs) {
        this.attribs = attribs;
        attribs.fig_ellipse_angle = attribs.fig_angle;
        this.rebuild();
    }
    
    public Point getCenterPoint() {
        return this.center;
    }
    
    public Point getRadiusPoint() {
        return this.size;
    }
    
    public void setTrafo(final FigTrafo2D trafo) {
        if (trafo != this.trafo) {
            this.trafo = trafo;
            this.rebuild();
        }
    }
    
    public void setCenterAndRadius(final Point point, final Point point2) {
        this.center = new Point(point.x, point.y);
        this.size = new Point(point2.x, point2.y);
        this.rebuild();
    }
    
    public void rebuild() {
        if (this.renderer == null) {
            this.createRenderer();
        }
        this.renderer.rebuild();
        this.bbox = new FigBbox(this.center.x - this.size.x, this.center.y - this.size.y, this.center.x + this.size.x, this.center.y + this.size.y);
        this.timestamp = System.currentTimeMillis();
    }
    
    public Point[] getPoints() {
        return new Point[] { new Point(this.center.x - this.size.x, this.center.y - this.size.y), new Point(this.center.x + this.size.x, this.center.y + this.size.y) };
    }
    
    public void setPoints(final Point[] array) {
        if (array == null || array.length != 2) {
            this.message("-E- FigEllipse.setPoints: invalid argument:" + array);
            return;
        }
        final int n = Math.abs(array[0].x - array[1].x) / 2;
        final int n2 = Math.abs(array[0].y - array[1].y) / 2;
        this.center = new Point((array[0].x + array[1].x) / 2, (array[0].y + array[1].y) / 2);
        this.size = new Point(n, n2);
        this.rebuild();
    }
    
    public boolean supportsPointOps() {
        return true;
    }
    
    public boolean isClosed() {
        return true;
    }
    
    public void appendPoint(final Point point) {
    }
    
    public Point deletePoint(final Point point) {
        return null;
    }
    
    public void insertPoint(final Point point, final Point point2) {
    }
    
    public Point[] getMovePointNeighbors(final Point point) {
        int n;
        if (Math.abs(point.x - this.bbox.getXl()) < Math.abs(point.x - this.bbox.getXr())) {
            n = this.bbox.getXr();
        }
        else {
            n = this.bbox.getXl();
        }
        int n2;
        if (Math.abs(point.y - this.bbox.getYt()) < Math.abs(point.y - this.bbox.getYb())) {
            n2 = this.bbox.getYb();
        }
        else {
            n2 = this.bbox.getYt();
        }
        return new Point[] { new Point(n, n2) };
    }
    
    public void movePoint(final Point point, final Point point2) {
        final int abs = Math.abs(point.x - this.bbox.getXl());
        final int abs2 = Math.abs(point.x - this.bbox.getXr());
        final int abs3 = Math.abs(point.y - this.bbox.getYt());
        final int abs4 = Math.abs(point.y - this.bbox.getYb());
        int n;
        if (abs < abs2) {
            n = this.bbox.getXr();
        }
        else {
            n = this.bbox.getXl();
        }
        int n2;
        if (abs3 < abs4) {
            n2 = this.bbox.getYb();
        }
        else {
            n2 = this.bbox.getYt();
        }
        final Point point3 = new Point(n, n2);
        this.bbox.set(point2.x, point2.y, point3.x, point3.y);
        this.x = this.bbox.getXl();
        this.y = this.bbox.getYt();
        this.setPoints(new Point[] { point2, point3 });
    }
    
    public Point getNearestPoint(final Point point) {
        final int abs = Math.abs(point.x - this.bbox.getXl());
        final int abs2 = Math.abs(point.x - this.bbox.getXr());
        final int abs3 = Math.abs(point.y - this.bbox.getYt());
        final int abs4 = Math.abs(point.y - this.bbox.getYb());
        int n;
        if (abs > abs2) {
            n = this.bbox.getXr();
        }
        else {
            n = this.bbox.getXl();
        }
        int n2;
        if (abs3 > abs4) {
            n2 = this.bbox.getYb();
        }
        else {
            n2 = this.bbox.getYt();
        }
        return new Point(n, n2);
    }
    
    public Point[] getNeighborPoints(final Point point) {
        return new Point[] { point, point, point };
    }
    
    void update_bbox() {
    }
    
    public void paint(final Graphics graphics) {
        this.renderer.paint(graphics);
    }
    
    public void move(final int n, final int n2) {
        this.x += n;
        this.y += n2;
        final Point center = this.center;
        center.x += n;
        final Point center2 = this.center;
        center2.y += n2;
        this.rebuild();
    }
    
    public FigObject copy() {
        return new FigEllipse(new Point(this.center.x, this.center.y), new Point(this.center.x + this.size.x, this.center.y + this.size.y), this.attribs.getClone(), this.trafo);
    }
    
    public void mirrorX(final int n, final int n2) {
        final Point centerPoint = this.getCenterPoint();
        final Point radiusPoint = this.getRadiusPoint();
        centerPoint.x = 2 * n - centerPoint.x;
        this.setCenterAndRadius(centerPoint, radiusPoint);
    }
    
    public void mirrorY(final int n, final int n2) {
        final Point centerPoint = this.getCenterPoint();
        final Point radiusPoint = this.getRadiusPoint();
        centerPoint.y = 2 * n2 - centerPoint.y;
        this.setCenterAndRadius(centerPoint, radiusPoint);
    }
    
    public void update(final FigAttribs figAttribs) {
        this.attribs.update(figAttribs);
        this.attribs.fig_ellipse_angle = this.attribs.fig_angle;
        this.rebuild();
    }
    
    public boolean canRotate(final double n) {
        return true;
    }
    
    public void rotate(final Point point, final double n) {
        try {
            final Point centerPoint = this.getCenterPoint();
            final Point radiusPoint = this.getRadiusPoint();
            final double cos = Math.cos(n);
            final double sin = Math.sin(n);
            final double n2 = centerPoint.x - point.x;
            final double n3 = centerPoint.y - point.y;
            final double n4 = n2 * cos - n3 * sin + point.x + 0.5;
            final double n5 = n2 * sin + n3 * cos + point.y + 0.5;
            final FigAttribs attribs = this.attribs;
            attribs.fig_ellipse_angle -= n;
            this.setCenterAndRadius(new Point((int)n4, (int)n5), radiusPoint);
            this.rebuild();
        }
        catch (Throwable t) {
            System.err.println(t);
            t.printStackTrace();
        }
    }
    
    public void setLayer(final int currentLayer) {
        this.attribs.currentLayer = currentLayer;
    }
    
    public void setLineColor(final Color lineColor) {
        this.attribs.fig_line_color = ColorCache.getColorCache().registerUserColor(lineColor);
        this.attribs.lineColor = lineColor;
    }
    
    public void setLineWidth(final int figLineWidth) {
        this.attribs.setFigLineWidth(figLineWidth);
        this.rebuild();
    }
    
    public void setLineStyle(final int lineStyle) {
        this.attribs.lineStyle = lineStyle;
        this.rebuild();
    }
    
    public void setFillColor(final Color fillColor) {
        this.attribs.fig_fill_color = ColorCache.getColorCache().registerUserColor(fillColor);
        this.attribs.fillColor = fillColor;
    }
    
    public void setFillStyle(final int n) {
        if (n < 0) {
            this.attribs.fillStyle = 1;
        }
        else if (n < 41) {
            this.attribs.fillStyle = 2;
            this.attribs.fig_area_fill = n;
        }
        else if (n <= 56) {
            this.attribs.fillStyle = 3;
            this.attribs.fig_area_fill = n;
        }
        else {
            this.message("setFillStyle: value out of range: " + n);
        }
        this.rebuild();
    }
    
    public double minDistance(final Point point) {
        return this.bbox.minDistanceInside(point);
    }
    
    public String toString() {
        return "FigEllipse  center " + this.x + "," + this.y + " radii " + this.size.x + "," + this.size.y + " layer " + this.attribs.currentLayer + " colors " + this.attribs.lineColor + " " + this.attribs.fillColor;
    }
    
    public FigEllipse(final Point point, final Point point2, final FigAttribs figAttribs, final FigTrafo2D trafo) {
        this.center = new Point(point.x, point.y);
        this.size = new Point(Math.abs(point2.x - point.x), Math.abs(point2.y - point.y));
        this.x = this.center.x;
        this.y = this.center.y;
        this.attribs = figAttribs.getClone();
        this.attribs.fig_ellipse_angle = figAttribs.fig_angle;
        this.trafo = trafo;
        this.timestamp = 0L;
        this.createRenderer();
        this.rebuild();
    }
}
