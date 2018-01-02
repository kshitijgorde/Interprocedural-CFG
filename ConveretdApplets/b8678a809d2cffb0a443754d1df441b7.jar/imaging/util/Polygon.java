// 
// Decompiled by Procyon v0.5.30
// 

package imaging.util;

import java.awt.geom.GeneralPath;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import imaging.math3D.Point2D;
import java.util.Vector;

public class Polygon
{
    protected Vector<Point2D> points;
    protected int selectedPoint;
    
    public Polygon() {
        this.points = new Vector<Point2D>();
        this.selectedPoint = -1;
    }
    
    public int hitsPoint(final int x, final int y) {
        for (int i = 0; i < this.points.size(); ++i) {
            final Point2D p = this.points.elementAt(i);
            if (this.distance(p.getX(), p.getY(), x, y) <= 5.0) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean isWithin(final int x, final int y) {
        final int[] ptX = new int[this.points.size()];
        final int[] ptY = new int[this.points.size()];
        for (int i = 0; i < this.points.size(); ++i) {
            final Point2D p = this.points.elementAt(i);
            ptX[i] = (int)p.getX();
            ptY[i] = (int)p.getY();
        }
        final java.awt.Polygon pg = new java.awt.Polygon(ptX, ptY, ptX.length);
        return pg.contains(x, y);
    }
    
    public Rectangle getBounds() {
        final int[] ptX = new int[this.points.size()];
        final int[] ptY = new int[this.points.size()];
        for (int i = 0; i < this.points.size(); ++i) {
            final Point2D p = this.points.elementAt(i);
            ptX[i] = (int)p.getX();
            ptY[i] = (int)p.getY();
        }
        final java.awt.Polygon pg = new java.awt.Polygon(ptX, ptY, ptX.length);
        return pg.getBounds();
    }
    
    public double distance(final double x1, final double y1, final double x2, final double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    
    public boolean[] setSelectedPoint(final int x, final int y) {
        final int hitsPoint = this.hitsPoint(x, y);
        this.selectedPoint = hitsPoint;
        if (hitsPoint < 0) {
            this.selectedPoint = -1;
            return new boolean[2];
        }
        if (this.selectedPoint == 0) {
            return new boolean[] { true, true };
        }
        return new boolean[] { true, false };
    }
    
    public void addNewPoint(final int x, final int y) {
        final Point2D currentPoint = new Point2D(x, y);
        this.points.addElement(currentPoint);
        this.selectedPoint = this.points.size() - 1;
    }
    
    public void movePointTo(final int x, final int y) {
        final Point2D p = this.points.elementAt(this.selectedPoint);
        p.setX(x);
        p.setY(y);
    }
    
    public boolean moveRelvantPoint(final int xOri, final int yOri, final int xNew, final int yNew) {
        final int iResult = this.hitsPoint(xOri, yOri);
        if (iResult >= 0) {
            final Point2D p = this.points.elementAt(iResult);
            p.setX(xNew);
            p.setY(yNew);
            return true;
        }
        return false;
    }
    
    public void renderPoints(final Graphics2D graphics) {
        if (this.points.size() == 0) {
            return;
        }
        final int[] x = new int[this.points.size()];
        final int[] y = new int[this.points.size()];
        graphics.setColor(Color.green);
        Point2D p = this.points.elementAt(0);
        x[0] = (int)p.getX();
        y[0] = (int)p.getY();
        graphics.fillOval((int)(p.getX() - 5.0f), (int)(p.getY() - 5.0f), 10, 10);
        Point2D pl = p;
        for (int i = 1; i < this.points.size(); ++i) {
            graphics.setColor(Color.black);
            p = this.points.elementAt(i);
            x[i] = (int)p.getX();
            y[i] = (int)p.getY();
            graphics.fillOval((int)(p.getX() - 5.0f), (int)(p.getY() - 5.0f), 10, 10);
            pl = p;
        }
        graphics.setColor(Color.blue);
        graphics.drawPolyline(x, y, x.length);
        if (this.selectedPoint != -1) {
            graphics.setColor(Color.blue);
            p = this.points.elementAt(this.selectedPoint);
            graphics.fillOval((int)(p.getX() - 5.0f), (int)(p.getY() - 5.0f), 10, 10);
        }
    }
    
    public void drawTail(final Graphics2D graphics, final int x, final int y) {
        final Point2D p = this.points.elementAt(this.points.size() - 1);
        graphics.drawLine((int)p.getX(), (int)p.getY(), x, y);
    }
    
    public GeneralPath getGeneralPath() {
        if (this.points.size() == 0) {
            return null;
        }
        final GeneralPath path = new GeneralPath();
        Point2D v = this.points.elementAt(0);
        path.moveTo(v.getX(), v.getY());
        for (int i = 1; i < this.points.size(); ++i) {
            v = this.points.elementAt(i);
            path.lineTo(v.getX(), v.getY());
        }
        path.closePath();
        return path;
    }
    
    public void drawLinesOpen(final Graphics2D graphics) {
        Point2D last = this.points.firstElement();
        final int[] x = new int[this.points.size()];
        final int[] y = new int[this.points.size()];
        x[0] = (int)last.getX();
        y[0] = (int)last.getY();
        for (int i = 1; i < this.points.size(); ++i) {
            final Point2D current = this.points.get(i);
            x[i] = (int)current.getX();
            y[i] = (int)current.getY();
            last = current;
        }
        graphics.drawPolyline(x, y, x.length);
    }
    
    public void drawLines(final Graphics2D graphics) {
        Point2D last = this.points.firstElement();
        final int[] x = new int[this.points.size() + 1];
        final int[] y = new int[this.points.size() + 1];
        final Point2D first = last;
        x[0] = (int)first.getX();
        y[0] = (int)first.getY();
        for (int i = 1; i < this.points.size(); ++i) {
            final Point2D current = this.points.get(i);
            x[i] = (int)current.getX();
            y[i] = (int)current.getY();
            last = current;
        }
        x[x.length] = (int)last.getX();
        y[y.length] = (int)last.getY();
        graphics.drawPolyline(x, y, x.length);
    }
    
    public void deletePoint() {
        this.points.removeElementAt(this.selectedPoint);
        this.selectedPoint = -1;
    }
    
    public void deleteLastPoint() {
        if (this.points.size() <= 0) {
            return;
        }
        this.points.removeElementAt(this.points.size() - 1);
        this.selectedPoint = -1;
    }
    
    public Vector<Point2D> getPoints() {
        return this.points;
    }
}
