// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import java.awt.Graphics;
import java.io.PrintWriter;
import java.awt.event.KeyEvent;
import jfig.gui.ColorCache;
import java.awt.Color;
import jfig.canvas.ObjectPainter;
import jfig.canvas.FigTrafo2D;
import java.awt.Point;

public class FigPolyline implements FigObject
{
    protected Point[] wcp;
    protected int min_i;
    protected int min_num_points;
    protected boolean is_closed;
    protected FigRenderer renderer;
    protected FigAttribs attribs;
    protected FigTrafo2D trafo;
    protected FigBbox bbox;
    protected ObjectPainter painter;
    protected String comment;
    protected boolean debug;
    protected boolean syncRedrawFlag;
    protected boolean selected;
    protected boolean showPoints;
    protected boolean visible;
    
    public void createRenderer() {
        this.renderer = FigObjectFactory.getDefaultObjectFactory().createPolylineRenderer(this);
    }
    
    public void rebuild() {
        this.renderer.rebuild();
        this.update_bbox();
    }
    
    public boolean isClosed() {
        return this.is_closed;
    }
    
    public void setIsClosed(final boolean is_closed) {
        final boolean b = is_closed != this.is_closed;
        this.is_closed = is_closed;
        if (b) {
            this.renderer.rebuild();
        }
    }
    
    public void appendPoint(final Point point) {
        if (this.debug) {
            this.message("FigPolyline.appendPoint: " + point);
        }
        if (point.x == this.wcp[this.wcp.length - 1].x && point.y == this.wcp[this.wcp.length - 1].y) {
            return;
        }
        final Point[] wcp = new Point[this.wcp.length + 1];
        for (int i = 0; i < this.wcp.length; ++i) {
            wcp[i] = this.wcp[i];
        }
        wcp[this.wcp.length] = new Point(point.x, point.y);
        this.wcp = wcp;
        this.rebuild();
    }
    
    public void update_bbox() {
        if (this.bbox == null) {
            this.bbox = new FigBbox(0, 0, 0, 0);
        }
        int x = Integer.MAX_VALUE;
        int x2 = -2147483647;
        int y = Integer.MAX_VALUE;
        int y2 = -2147483647;
        for (int i = 0; i < this.wcp.length; ++i) {
            if (this.wcp[i].x > x2) {
                x2 = this.wcp[i].x;
            }
            if (this.wcp[i].x < x) {
                x = this.wcp[i].x;
            }
            if (this.wcp[i].y > y2) {
                y2 = this.wcp[i].y;
            }
            if (this.wcp[i].y < y) {
                y = this.wcp[i].y;
            }
        }
        this.bbox.set(x, y, x2, y2);
    }
    
    public void setTrafo(final FigTrafo2D trafo) {
        if (trafo == null) {
            this.message("-E- FigPolyline.setTrafo: trafo is null! ignoring...");
            return;
        }
        if (trafo != this.trafo) {
            this.trafo = trafo;
            this.rebuild();
        }
    }
    
    public void setLayer(final int currentLayer) {
        this.attribs.currentLayer = currentLayer;
    }
    
    public void setArrowMode(final int arrowMode) {
        this.attribs.arrowMode = arrowMode;
        this.rebuild();
    }
    
    public void setForwardArrow(final int n, final double n2, final double n3, final double n4) {
        this.attribs.arrow_f_Width = 96.0 * n2;
        this.attribs.arrow_f_Length = 96.0 * n3;
        this.attribs.arrow_f_Thickness = 30.0 * n4;
        this.rebuild();
    }
    
    public void setBackwardArrow(final int n, final double n2, final double n3, final double n4) {
        this.attribs.arrow_b_Width = 96.0 * n2;
        this.attribs.arrow_b_Length = 96.0 * n3;
        this.attribs.arrow_b_Thickness = 30.0 * n4;
        this.rebuild();
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
        int n = Integer.MAX_VALUE;
        for (int i = 0; i < this.wcp.length; ++i) {
            final int abs = Math.abs(this.wcp[i].x - point.x);
            final int abs2 = Math.abs(this.wcp[i].y - point.y);
            if (abs + abs2 < n) {
                n = abs + abs2;
            }
        }
        return n;
    }
    
    public double minDistanceEuclid(final Point point) {
        return this.getMinPerpendicularDistance(point) + 5.0;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("FigPolyline with " + this.wcp.length + "points: ");
        for (int i = 0; i < this.wcp.length; ++i) {
            sb.append("( " + this.wcp[i].x + ", " + this.wcp[i].y + "), ");
        }
        return sb.toString();
    }
    
    public void move(final int n, final int n2) {
        if (this.debug) {
            this.message("Polyline.move( " + n + ", " + n2 + ") ");
        }
        for (int i = 0; i < this.wcp.length; ++i) {
            final Point point = this.wcp[i];
            point.x += n;
            final Point point2 = this.wcp[i];
            point2.y += n2;
        }
        this.rebuild();
    }
    
    public FigObject copy() {
        if (this.debug) {
            this.message("FigPolyline.copy()...");
        }
        final FigPolyline figPolyline = new FigPolyline();
        figPolyline.setPoints(this.getPoints());
        figPolyline.is_closed = this.is_closed;
        figPolyline.setAttributes(this.attribs.getClone());
        figPolyline.setTrafo(this.trafo);
        return figPolyline;
    }
    
    public boolean canRotate(final double n) {
        return true;
    }
    
    public void update(final FigAttribs figAttribs) {
        if (this.debug) {
            this.message("FigPolyline.update()...");
        }
        this.attribs.update(figAttribs);
        this.rebuild();
    }
    
    public void mirrorX(final int n, final int n2) {
        for (int i = 0; i < this.wcp.length; ++i) {
            this.wcp[i].x = 2 * n - this.wcp[i].x;
        }
        this.rebuild();
    }
    
    public void mirrorY(final int n, final int n2) {
        for (int i = 0; i < this.wcp.length; ++i) {
            this.wcp[i].y = 2 * n2 - this.wcp[i].y;
        }
        this.rebuild();
    }
    
    public boolean supportsPointOps() {
        return true;
    }
    
    public Point[] getPoints() {
        return this.wcp;
    }
    
    public void setPoints(final Point[] array) {
        final Point[] wcp = new Point[array.length];
        for (int i = 0; i < array.length; ++i) {
            wcp[i] = new Point(array[i].x, array[i].y);
        }
        this.wcp = wcp;
        this.rebuild();
    }
    
    public void insertPoint(final Point point, final Point point2) {
        if (this.debug) {
            this.message("FigPolyline.insertPoint()...");
        }
        boolean b = false;
        int n = -1;
        for (int i = 0; i < this.wcp.length; ++i) {
            if (point2.x == this.wcp[i].x && point2.y == this.wcp[i].y) {
                n = i;
            }
            if (point.x == this.wcp[i].x && point.y == this.wcp[i].y) {
                b = true;
            }
        }
        if (n == -1) {
            this.message("FigPolyline.insertPoint(): Point wprev " + point2.toString() + "is not a Point on this polyline!");
            return;
        }
        if (b) {
            this.message("FigPolyline.insertPoint(): Point wp " + point.toString() + "is already a Point on this polyline!");
            return;
        }
        final Point[] wcp = new Point[this.wcp.length + 1];
        for (int j = 0; j <= n; ++j) {
            wcp[j] = this.wcp[j];
        }
        wcp[n + 1] = new Point(point.x, point.y);
        for (int k = n + 2; k < wcp.length; ++k) {
            wcp[k] = this.wcp[k - 1];
        }
        this.wcp = wcp;
        this.rebuild();
    }
    
    public Point deletePoint(final Point point) {
        if (this.debug) {
            this.message("FigPolyline.deletePoint()...");
        }
        if (this.wcp.length <= this.min_num_points) {
            return null;
        }
        final int indexOfNearestNeighbor = this.indexOfNearestNeighbor(point);
        if (indexOfNearestNeighbor == -1) {
            this.message("FigPolyline.delete(): Point wp " + point.toString() + "is not a Point on this polyline!");
            return null;
        }
        Point point2;
        if (indexOfNearestNeighbor > 0) {
            point2 = this.wcp[indexOfNearestNeighbor - 1];
        }
        else {
            point2 = this.wcp[0];
        }
        final Point[] wcp = new Point[this.wcp.length - 1];
        for (int i = 0; i < indexOfNearestNeighbor; ++i) {
            wcp[i] = this.wcp[i];
        }
        for (int j = indexOfNearestNeighbor; j < wcp.length; ++j) {
            wcp[j] = this.wcp[j + 1];
        }
        this.wcp = wcp;
        this.rebuild();
        return point2;
    }
    
    public int indexOfNearestNeighbor(final Point point) {
        int n = -1;
        int n2 = Integer.MAX_VALUE;
        for (int i = 0; i < this.wcp.length; ++i) {
            final int n3 = Math.abs(point.x - this.wcp[i].x) + Math.abs(point.y - this.wcp[i].y);
            if (n3 < n2) {
                n2 = n3;
                n = i;
            }
        }
        if (n2 > this.trafo.getSnapRelative()) {
            return -1;
        }
        return n;
    }
    
    public void movePoint(final Point point, final Point point2) {
        if (this.debug) {
            this.message("FigPolyline.movePoint()...");
        }
        final int indexOfNearestNeighbor = this.indexOfNearestNeighbor(point);
        if (indexOfNearestNeighbor == -1) {
            if (this.debug) {
                this.message("FigPolyline.movePoint(): Point wp " + point.toString() + "is not a Point on this polyline!");
            }
            if (this.debug) {
                String string = "the points are: ";
                for (int i = 0; i < this.wcp.length; ++i) {
                    string = string + "(" + this.wcp[i].x + "," + this.wcp[i].y + ") ";
                }
                this.message(string);
            }
            return;
        }
        this.wcp[indexOfNearestNeighbor].x = point2.x;
        this.wcp[indexOfNearestNeighbor].y = point2.y;
        this.rebuild();
    }
    
    public Point[] getMovePointNeighbors(final Point point) {
        int n = Integer.MAX_VALUE;
        for (int i = 0; i < this.wcp.length; ++i) {
            final int n2 = Math.abs(point.x - this.wcp[i].x) + Math.abs(point.y - this.wcp[i].y);
            if (n2 < n) {
                n = n2;
                this.min_i = i;
            }
        }
        final Point[] array = new Point[2];
        if (this.min_i > 0) {
            array[0] = this.wcp[this.min_i - 1];
        }
        else if (this.is_closed) {
            array[0] = this.wcp[this.wcp.length - 1];
        }
        else {
            array[0] = null;
        }
        if (this.min_i < this.wcp.length - 1) {
            array[1] = this.wcp[this.min_i + 1];
        }
        else if (this.is_closed) {
            array[1] = this.wcp[0];
        }
        else {
            array[1] = null;
        }
        return array;
    }
    
    public Point[] getNeighborPoints(final Point point) {
        double n = Double.MAX_VALUE;
        Point point2 = this.wcp[0];
        for (int i = 0; i < this.wcp.length - 1; ++i) {
            final double segmentDistance = this.getSegmentDistance(this.wcp[i], this.wcp[i + 1], point);
            if (this.debug) {
                this.printCost(i, segmentDistance, n);
            }
            if (segmentDistance <= n) {
                n = segmentDistance;
                this.min_i = i;
                point2 = this.nearerPoint(this.wcp[i], this.wcp[i + 1], point);
            }
        }
        if (this.is_closed) {
            final double segmentDistance2 = this.getSegmentDistance(this.wcp[this.wcp.length - 1], this.wcp[0], point);
            if (this.debug) {
                this.printCost(this.wcp.length, segmentDistance2, n);
            }
            if (segmentDistance2 <= n) {
                this.min_i = this.wcp.length - 1;
                point2 = this.nearerPoint(this.wcp[this.wcp.length - 1], this.wcp[0], point);
            }
        }
        final Point[] array = { this.wcp[this.min_i], null, null };
        if (this.min_i < this.wcp.length - 1) {
            array[1] = this.wcp[this.min_i + 1];
        }
        else {
            array[1] = this.wcp[0];
        }
        array[2] = point2;
        return array;
    }
    
    protected double getSegmentDistance(final Point point, final Point point2, final Point point3) {
        final int n = point3.x - point.x;
        final int n2 = point3.y - point.y;
        final int n3 = point3.x - point2.x;
        final int n4 = point3.y - point2.y;
        final int n5 = point2.x - point.x;
        final int n6 = point2.y - point.y;
        return (n * n + n2 * n2 + n3 * n3 + n4 * n4) / (n5 * n5 + n6 * n6);
    }
    
    protected Point nearerPoint(final Point point, final Point point2, final Point point3) {
        if (Math.abs(point.x - point3.x) + Math.abs(point.y - point3.y) < Math.abs(point2.x - point3.x) + Math.abs(point2.y - point3.y)) {
            return point;
        }
        return point2;
    }
    
    protected void printCost(final int n, final double n2, final double n3) {
        if (this.debug) {
            this.message("FigPolyline.getNeighborPoints(): segment " + n + " cost: " + n2 + " min_cost: " + n3);
        }
    }
    
    public double calc_dist(final Point point) {
        double n = Double.MAX_VALUE;
        for (int i = 0; i < this.wcp.length - 1; ++i) {
            final int n2 = point.x - this.wcp[i].x;
            final int n3 = point.y - this.wcp[i].y;
            final int n4 = point.x - this.wcp[i + 1].x;
            final int n5 = point.y - this.wcp[i + 1].y;
            final double n6 = n2 * n2 + n3 * n3 + n4 * n4 + n5 * n5;
            if (this.debug) {
                this.message("FigPolyline.dist(): segment " + i + " cost: " + n6 + " min_cost: " + n);
            }
            if (n6 <= n) {
                n = n6;
                this.min_i = i;
            }
        }
        return n;
    }
    
    public double getMinPerpendicularDistance(final Point point) {
        double n = Double.MAX_VALUE;
        for (int i = 0; i < this.wcp.length - 1; ++i) {
            final double minPerpendicularDistance = this.getMinPerpendicularDistance(point, this.wcp[i], this.wcp[i + 1]);
            if (minPerpendicularDistance < n) {
                n = minPerpendicularDistance;
                this.min_i = i;
            }
        }
        return n;
    }
    
    public double getMinPerpendicularDistance(final Point point, final Point point2, final Point point3) {
        final double n = point3.x - point2.x;
        final double n2 = point3.y - point2.y;
        final double n3 = point.x - point2.x;
        final double n4 = point.y - point2.y;
        final double n5 = n * n + n2 * n2;
        double n6;
        if (n5 <= 0.0) {
            n6 = Math.sqrt(n3 * n3 + n4 * n4);
        }
        else {
            final double n7 = (n3 * n + n4 * n2) / n5;
            final double n8 = (n3 * n2 - n4 * n) / n5;
            if (n7 >= 0.0 && n7 <= 1.0) {
                n6 = Math.abs(n8) * Math.sqrt(n5);
            }
            else if (n7 < 0.0) {
                n6 = Math.sqrt(n3 * n3 + n4 * n4);
            }
            else {
                final double n9 = point.x - point3.x;
                final double n10 = point.y - point3.y;
                n6 = Math.sqrt(n9 * n9 + n10 * n10);
            }
        }
        return n6;
    }
    
    public int get_min_dist_index(final Point point) {
        this.calc_dist(point);
        return this.min_i;
    }
    
    public Point getMinDistPoint1() {
        return this.wcp[this.min_i];
    }
    
    public Point getMinDistPoint2() {
        return this.wcp[this.min_i + 1];
    }
    
    public boolean isVisible(final FigBbox figBbox) {
        return this.getBbox().isVisible(figBbox);
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public boolean initialize(final String s) {
        this.message("-E- FigPolyline.initialize: not supported, ignored!");
        return true;
    }
    
    public void setObjectPainter(final ObjectPainter painter) {
        this.painter = painter;
    }
    
    public ObjectPainter getObjectPainter() {
        return this.painter;
    }
    
    public FigTrafo2D getTrafo() {
        return this.trafo;
    }
    
    public int getLayer() {
        return this.getAttributes().currentLayer;
    }
    
    public FigBbox getBbox() {
        if (this.bbox == null) {
            this.update_bbox();
        }
        return this.bbox;
    }
    
    public FigBbox get_sc_bbox() {
        if (this.bbox == null) {
            this.update_bbox();
        }
        return new FigBbox(this.trafo.wc_to_screen_x(this.bbox.getXl()), this.trafo.wc_to_screen_y(this.bbox.getYt()), this.trafo.wc_to_screen_x(this.bbox.getXr()), this.trafo.wc_to_screen_y(this.bbox.getYb()));
    }
    
    public Point getPosition() {
        if (this.wcp != null && this.wcp.length > 0) {
            return this.wcp[0];
        }
        return new Point(0, 0);
    }
    
    public FigAttribs getAttributes() {
        return this.attribs;
    }
    
    public void setAttributes(final FigAttribs attribs) {
        this.attribs = attribs;
        this.rebuild();
    }
    
    public void updateAttributes(final String s) {
        this.getAttributes().parse(s);
        this.rebuild();
    }
    
    public void set_debug() {
        this.debug = true;
    }
    
    public void reset_debug() {
        this.debug = false;
    }
    
    public void scale(final Point point, double abs, final double n) {
        final Point[] points = this.getPoints();
        if (points != null) {
            final Point[] points2 = new Point[points.length];
            for (int i = 0; i < points2.length; ++i) {
                points2[i] = new Point(point.x + (int)((points[i].x - point.x) * abs), point.y + (int)((points[i].y - point.y) * n));
            }
            this.setPoints(points2);
        }
        abs = Math.abs(abs);
        final FigAttribs attribs = this.attribs;
        attribs.lineWidth *= abs;
        final FigAttribs attribs2 = this.attribs;
        attribs2.dashLength *= abs;
        final FigAttribs attribs3 = this.attribs;
        attribs3.arrow_f_Width *= abs;
        final FigAttribs attribs4 = this.attribs;
        attribs4.arrow_f_Length *= abs;
        final FigAttribs attribs5 = this.attribs;
        attribs5.arrow_f_Thickness *= abs;
        final FigAttribs attribs6 = this.attribs;
        attribs6.arrow_b_Width *= abs;
        final FigAttribs attribs7 = this.attribs;
        attribs7.arrow_b_Length *= abs;
        final FigAttribs attribs8 = this.attribs;
        attribs8.arrow_b_Thickness *= abs;
    }
    
    public void rotate(final Point point, final double n) throws Exception {
        if (this.canRotate(n)) {
            final Point[] points = this.getPoints();
            final Point[] points2 = new Point[points.length];
            final double cos = Math.cos(n);
            final double sin = Math.sin(n);
            for (int i = 0; i < points.length; ++i) {
                final double n2 = points[i].x - point.x;
                final double n3 = points[i].y - point.y;
                points2[i] = new Point((int)(n2 * cos - n3 * sin + point.x), (int)(n2 * sin + n3 * cos + point.y));
            }
            this.setPoints(points2);
            return;
        }
        throw new Exception("Rotation not supported on FigBaseObject!");
    }
    
    public void showPoints() {
        this.showPoints = true;
        this.selected = false;
    }
    
    public void select() {
        this.selected = true;
        this.showPoints = false;
    }
    
    public void deselect() {
        this.selected = false;
        this.showPoints = false;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public boolean isShowPoints() {
        return this.showPoints;
    }
    
    public int numPoints() {
        return this.wcp.length;
    }
    
    public String getText() {
        this.message("-E- FigPolyline.getText: should not be called! ");
        return null;
    }
    
    public void setText(final String s) {
        this.message("-E- FigPolyline.setText: should not be called! ");
    }
    
    public Point getNearestPoint(final Point point) {
        Point point2 = null;
        double n = Double.MAX_VALUE;
        for (int i = 0; i < this.wcp.length; ++i) {
            final double n2 = this.manhattan(this.wcp[i], point);
            if (n2 < n) {
                n = n2;
                point2 = this.wcp[i];
            }
        }
        return point2;
    }
    
    public int manhattan(final Point point, final Point point2) {
        return Math.abs(point.x - point2.x) + Math.abs(point.y - point2.y);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.message("-E- FigPolyline.keyPressed: ignored");
    }
    
    public void writeAsResource(final PrintWriter printWriter) {
        new FigWriter().writeAsResource(printWriter, (FigObject)this);
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(final String comment) {
        this.comment = comment;
    }
    
    public boolean getSyncRedrawFlag() {
        return this.syncRedrawFlag;
    }
    
    public void setSyncRedrawFlag(final boolean syncRedrawFlag) {
        this.syncRedrawFlag = syncRedrawFlag;
    }
    
    public void message(final String s) {
        System.err.println(s);
    }
    
    public void paint(final Graphics graphics) {
        this.renderer.paint(graphics);
    }
    
    public void paint(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        this.renderer.paint(graphics, figTrafo2D);
    }
    
    public void paintSave(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        final Graphics create = graphics.create();
        this.paint(create, figTrafo2D);
        create.dispose();
    }
    
    public FigPolyline() {
        this.min_num_points = 2;
        this.renderer = null;
        this.attribs = null;
        this.trafo = null;
        this.bbox = null;
        this.painter = null;
        this.comment = null;
        this.debug = false;
        this.syncRedrawFlag = false;
        this.selected = false;
        this.showPoints = false;
        this.visible = true;
        this.is_closed = false;
        (this.wcp = new Point[1])[0] = new Point(0, 0);
        this.trafo = new FigTrafo2D();
        this.attribs = new FigAttribs();
        this.visible = true;
        this.selected = false;
        this.showPoints = false;
        this.syncRedrawFlag = false;
        this.createRenderer();
    }
    
    public FigPolyline(final int n, final int n2, final boolean is_closed, final FigAttribs figAttribs, final FigTrafo2D trafo) {
        this();
        this.wcp = new Point[] { new Point(n, n2) };
        this.attribs = figAttribs.getClone();
        this.trafo = trafo;
        this.is_closed = is_closed;
        this.rebuild();
    }
}
