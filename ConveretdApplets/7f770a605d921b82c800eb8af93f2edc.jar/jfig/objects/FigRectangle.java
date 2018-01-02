// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.canvas.FigTrafo2D;
import java.awt.Graphics;
import java.awt.Point;
import jfig.gui.ColorCache;
import java.awt.Color;

public class FigRectangle extends FigBaseobject
{
    protected FigRenderer renderer;
    protected boolean use_rounded_corners;
    protected int radius;
    protected int pixel_width;
    
    public void createRenderer() {
        this.renderer = FigObjectFactory.getDefaultObjectFactory().createRectangleRenderer(this);
    }
    
    public void rebuild() {
        this.renderer.rebuild();
    }
    
    public void setAttributes(final FigAttribs attributes) {
        super.setAttributes(attributes);
        this.rebuild();
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
    
    public boolean isRounded() {
        return this.use_rounded_corners;
    }
    
    public Point[] getPoints() {
        return new Point[] { new Point(this.bbox.getXl(), this.bbox.getYt()), new Point(this.bbox.getXr(), this.bbox.getYb()) };
    }
    
    public void setPoints(final Point[] array) {
        if (array == null || array.length < 2 || array.length > 3) {
            this.message("-E- a rectangle needs 2 Points for setPoints()");
            return;
        }
        this.bbox.set(array[0].x, array[0].y, array[1].x, array[1].y);
        this.x = this.bbox.getXl();
        this.y = this.bbox.getYt();
        this.rebuild();
    }
    
    public void update(final FigAttribs figAttribs) {
        if (this.debug) {
            this.message("FigRectangle.update()...");
        }
        this.attribs.update(figAttribs);
        this.rebuild();
    }
    
    public FigObject copy() {
        if (this.debug) {
            this.message("FigRectangle.copy()...");
        }
        return new FigRectangle(this.bbox.getXl(), this.bbox.getYt(), this.bbox.getXr(), this.bbox.getYb(), this.use_rounded_corners, this.attribs.getClone(), this.trafo);
    }
    
    public void move(final int n, final int n2) {
        this.bbox = new FigBbox(this.bbox.getXl() + n, this.bbox.getYt() + n2, this.bbox.getXr() + n, this.bbox.getYb() + n2);
        this.x = this.bbox.getXl();
        this.y = this.bbox.getYt();
        this.rebuild();
    }
    
    public void mirrorX(final int n, final int n2) {
        final Point[] points = this.getPoints();
        points[0].x = 2 * n - points[0].x;
        points[1].x = 2 * n - points[1].x;
        this.setPoints(points);
    }
    
    public void mirrorY(final int n, final int n2) {
        final Point[] points = this.getPoints();
        points[0].y = 2 * n2 - points[0].y;
        points[1].y = 2 * n2 - points[1].y;
        this.setPoints(points);
    }
    
    public boolean canRotate(final double n) {
        return (int)(180.0 * Math.abs(n) / 3.141592653589793 + 0.5) % 90 == 0;
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
        this.rebuild();
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
    
    public double minDistance(final Point point) {
        return this.bbox.minDistanceInside(point);
    }
    
    public double minDistanceEuclid(final Point point) {
        double n = this.bbox.minDistance(point);
        if (this.bbox.isInside(point)) {
            n = Math.min(n, 0.4 * this.trafo.getSnapRelative());
        }
        return n;
    }
    
    public void paint(final Graphics graphics) {
        this.renderer.paint(graphics);
    }
    
    public void paint(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        this.renderer.paint(graphics, figTrafo2D);
    }
    
    public void paintSave(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        final Graphics create = graphics.create();
        this.renderer.paint(create, figTrafo2D);
        create.dispose();
    }
    
    public String toString() {
        return "FigRectangle  " + this.bbox + " layer " + this.attribs.currentLayer + " colors " + this.attribs.lineColor + " " + this.attribs.fillColor;
    }
    
    public FigRectangle() {
        this(0, 0, 2400, 2400, 10);
    }
    
    public FigRectangle(int x, int y, int n, int n2, final int currentLayer) {
        this.createRenderer();
        if (x > n) {
            final int n3 = n;
            n = x;
            x = n3;
        }
        if (y > n2) {
            final int n4 = n2;
            n2 = y;
            y = n4;
        }
        this.x = x;
        this.y = y;
        this.attribs.currentLayer = currentLayer;
        this.bbox.set(x, y, n, n2);
        this.rebuild();
    }
    
    public FigRectangle(final FigBbox figBbox, final int currentLayer) {
        this(figBbox.getXl(), figBbox.getYt(), figBbox.getXr(), figBbox.getYb(), currentLayer);
        this.x = figBbox.getXl();
        this.y = figBbox.getYb();
        this.attribs.currentLayer = currentLayer;
        this.attribs.lineColor = Color.black;
        this.attribs.fillColor = Color.black;
        this.rebuild();
    }
    
    public FigRectangle(final int n, final int n2, final int n3, final int n4, final boolean use_rounded_corners, final FigAttribs figAttribs, final FigTrafo2D trafo) {
        this(n, n2, n3, n4, 1);
        this.trafo = trafo;
        this.attribs = figAttribs.getClone();
        if (!(this.use_rounded_corners = use_rounded_corners)) {
            this.attribs.cornerRadius = 0;
        }
        this.rebuild();
    }
}
