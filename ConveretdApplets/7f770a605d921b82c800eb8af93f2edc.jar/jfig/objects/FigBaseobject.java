// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Point;
import jfig.canvas.ObjectPainter;
import jfig.canvas.FigTrafo2D;
import jfig.gui.ConsoleMessage;

public class FigBaseobject implements FigObject
{
    protected static ConsoleMessage printer;
    protected FigAttribs attribs;
    protected FigTrafo2D trafo;
    protected int x;
    protected int y;
    protected FigBbox bbox;
    protected FigBbox sc_bbox;
    protected long sc_bbox_timestamp;
    protected boolean debug;
    protected boolean selected;
    protected boolean showPoints;
    protected boolean syncRedrawFlag;
    protected long timestamp;
    protected String comment;
    protected boolean visible;
    public ObjectPainter painter;
    
    public boolean initialize(final String s) {
        this.message("shouldn't call initialize() on a FigBaseobject!");
        return true;
    }
    
    public void setObjectPainter(final ObjectPainter painter) {
        this.painter = painter;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public FigAttribs getAttributes() {
        return this.attribs;
    }
    
    public void setAttributes(final FigAttribs attribs) {
        this.attribs = attribs;
    }
    
    public void updateAttributes(final String s) {
        this.getAttributes().parse(s);
        this.rebuild();
    }
    
    public void setTrafo(final FigTrafo2D trafo) {
        if (trafo != this.trafo) {
            this.trafo = trafo;
            this.rebuild();
        }
    }
    
    public FigTrafo2D getTrafo() {
        return this.trafo;
    }
    
    public int getLayer() {
        return this.attribs.currentLayer;
    }
    
    public Point getPosition() {
        return new Point(this.x, this.y);
    }
    
    public FigBbox getBbox() {
        return this.bbox;
    }
    
    public void set_debug() {
        this.debug = true;
    }
    
    public void reset_debug() {
        this.debug = false;
    }
    
    public void move(final int n, final int n2) {
        this.x += n;
        this.y += n2;
    }
    
    public void mirrorX(final int n, final int n2) {
        this.message("mirrorX() not supported by " + this.toString());
    }
    
    public void mirrorY(final int n, final int n2) {
        this.message("mirrorY() not supported by " + this.toString());
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
    
    public boolean canRotate(final double n) {
        return false;
    }
    
    public void update(final FigAttribs figAttribs) {
        this.message("update() not supported by " + this.toString());
    }
    
    public void rebuild() {
        this.message("-W- rebuild() not implemented by " + this.toString());
    }
    
    public FigObject copy() {
        return null;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public void showPoints() {
        this.showPoints = true;
        this.selected = false;
    }
    
    public boolean isShowPoints() {
        return this.showPoints;
    }
    
    public void select() {
        this.showPoints = false;
        this.selected = true;
    }
    
    public void deselect() {
        this.showPoints = false;
        this.selected = false;
    }
    
    public boolean getSyncRedrawFlag() {
        return this.syncRedrawFlag;
    }
    
    public void setSyncRedrawFlag(final boolean syncRedrawFlag) {
        this.syncRedrawFlag = syncRedrawFlag;
    }
    
    public boolean supportsPointOps() {
        return false;
    }
    
    public int numPoints() {
        if (this.getPoints() != null) {
            return this.getPoints().length;
        }
        return 0;
    }
    
    public boolean isClosed() {
        return false;
    }
    
    public Point[] getPoints() {
        return null;
    }
    
    public void setPoints(final Point[] array) {
        if (this.debug) {
            this.message("FigBaseobject.setPoints(): You should not call this!");
        }
    }
    
    public static Point[] clonePoints(final Point[] array) {
        final int length = array.length;
        final Point[] array2 = new Point[length];
        for (int i = 0; i < length; ++i) {
            final Point point = array[i];
            array2[i] = new Point(point.x, point.y);
        }
        return array2;
    }
    
    public void appendPoint(final Point point) {
        if (this.debug) {
            this.message("FigBaseobject.appendPoint(): You should not call this!");
        }
    }
    
    public void movePoint(final Point point, final Point point2) {
        if (this.debug) {
            this.message("FigBaseobject.movePoint(): You should not call this!");
        }
    }
    
    public void insertPoint(final Point point, final Point point2) {
        if (this.debug) {
            this.message("FigBaseobject.insertPoint(): You should not call this!");
        }
    }
    
    public Point deletePoint(final Point point) {
        if (this.debug) {
            this.message("FigBaseobject.deletePoint(): You should not call this!");
        }
        return null;
    }
    
    public Point getNearestPoint(final Point point) {
        final Point[] points = this.getPoints();
        if (points == null) {
            return null;
        }
        Point point2 = null;
        double n = 1.0E10;
        for (int i = 0; i < points.length; ++i) {
            final double n2 = manhattan(points[i], point);
            if (n2 < n) {
                n = n2;
                point2 = points[i];
            }
        }
        return new Point(point2);
    }
    
    public static int manhattan(final Point point, final Point point2) {
        return Math.abs(point.x - point2.x) + Math.abs(point.y - point2.y);
    }
    
    public Point[] getNeighborPoints(final Point point) {
        if (this.debug) {
            this.message("FigBaseobject.getNeighborPoints(): You should not call this!");
        }
        return null;
    }
    
    public Point[] getMovePointNeighbors(final Point point) {
        if (this.debug) {
            this.message("FigBaseobject.getMovePointNeighbors(): You should not call this!");
        }
        return null;
    }
    
    public double minDistance(final Point point) {
        return this.bbox.minDistanceInside(point);
    }
    
    public double minDistanceEuclid(final Point point) {
        return this.minDistance(point);
    }
    
    public String getText() {
        return "";
    }
    
    public void setText(final String s) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.message(this.toString() + ".keyPressed( " + keyEvent + ") ");
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public void build_sc_bbox() {
        this.sc_bbox = new FigBbox(this.trafo.wc_to_screen(new Point(this.bbox.getXl(), this.bbox.getYt()), new Point(0, 0)), this.trafo.wc_to_screen(new Point(this.bbox.getXr(), this.bbox.getYb()), new Point(0, 0)));
        this.sc_bbox_timestamp = System.currentTimeMillis();
    }
    
    public FigBbox get_sc_bbox() {
        return this.sc_bbox;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.visible) {
            return;
        }
        if (!this.showPoints && !this.selected) {
            return;
        }
        if (this.sc_bbox_timestamp <= this.trafo.getTimestamp()) {
            this.build_sc_bbox();
        }
        final int xl = this.sc_bbox.getXl();
        final int xr = this.sc_bbox.getXr();
        final int yt = this.sc_bbox.getYt();
        final int yb = this.sc_bbox.getYb();
        if (this.debug) {
            this.message("sc_bbox coords: " + xl + " " + yt + ", " + xr + " " + yb);
        }
        if (this.showPoints) {
            graphics.setColor(Color.black);
            graphics.drawRect(xl - 2, yt - 2, 4, 4);
            graphics.drawRect(xr - 2, yt - 2, 4, 4);
            graphics.drawRect(xl - 2, yb - 2, 4, 4);
            graphics.drawRect(xr - 2, yb - 2, 4, 4);
            graphics.setColor(Color.white);
            graphics.fillRect(xl - 1, yt - 1, 3, 3);
            graphics.fillRect(xr - 1, yt - 1, 3, 3);
            graphics.fillRect(xl - 1, yb - 1, 3, 3);
            graphics.fillRect(xr - 1, yb - 1, 3, 3);
            graphics.setColor(Color.black);
        }
        else if (this.selected) {
            graphics.setColor(Color.black);
            graphics.fillRect(xl - 1, yt - 1, 3, 3);
            graphics.fillRect(xr - 1, yt - 1, 3, 3);
            graphics.fillRect(xl - 1, yb - 1, 3, 3);
            graphics.fillRect(xr - 1, yb - 1, 3, 3);
            graphics.setColor(Color.white);
            graphics.drawRect(xl - 2, yt - 2, 4, 4);
            graphics.drawRect(xr - 2, yt - 2, 4, 4);
            graphics.drawRect(xl - 2, yb - 2, 4, 4);
            graphics.drawRect(xr - 2, yb - 2, 4, 4);
        }
    }
    
    public void paint(final Graphics graphics, final FigTrafo2D trafo) {
        if (trafo != this.getTrafo()) {
            this.setTrafo(trafo);
        }
        this.paint(graphics);
    }
    
    public void paintSave(final Graphics graphics, final FigTrafo2D trafo) {
        final Graphics create = graphics.create();
        this.setTrafo(trafo);
        this.rebuild();
        this.paint(create, trafo);
        create.dispose();
    }
    
    public boolean isVisible(final FigBbox figBbox) {
        return this.bbox.isVisible(figBbox);
    }
    
    public void message(final String s) {
        if (FigBaseobject.printer != null) {
            FigBaseobject.printer.consoleMessage(s);
        }
        else {
            System.err.println(s);
        }
    }
    
    public void setConsole(final ConsoleMessage printer) {
        FigBaseobject.printer = printer;
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
    
    public String toString() {
        return "FigBaseobject at (" + this.x + ", " + this.y + ") on layer " + this.attribs.currentLayer;
    }
    
    public FigBaseobject() {
        this.sc_bbox_timestamp = -1L;
        this.syncRedrawFlag = false;
        this.timestamp = 0L;
        this.painter = null;
        this.x = 0;
        this.y = 0;
        this.debug = false;
        this.attribs = new FigAttribs();
        this.trafo = new FigTrafo2D();
        this.bbox = new FigBbox(this.x, this.y, this.x, this.y);
        this.build_sc_bbox();
        this.sc_bbox_timestamp = 0L;
        this.debug = false;
        this.selected = false;
        this.showPoints = false;
        this.visible = true;
        this.timestamp = 0L;
    }
    
    public FigBaseobject(final FigTrafo2D trafo) {
        this.sc_bbox_timestamp = -1L;
        this.syncRedrawFlag = false;
        this.timestamp = 0L;
        this.painter = null;
        this.x = 0;
        this.y = 0;
        this.debug = false;
        this.attribs = new FigAttribs();
        this.trafo = trafo;
        this.bbox = new FigBbox(this.x, this.y, this.x, this.y);
        this.build_sc_bbox();
        this.sc_bbox_timestamp = 0L;
        this.debug = false;
        this.selected = false;
        this.showPoints = false;
        this.visible = true;
        this.timestamp = 0L;
    }
    
    static {
        FigBaseobject.printer = null;
    }
}
