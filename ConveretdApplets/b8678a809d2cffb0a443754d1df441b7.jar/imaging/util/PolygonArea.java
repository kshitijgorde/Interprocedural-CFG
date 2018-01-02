// 
// Decompiled by Procyon v0.5.30
// 

package imaging.util;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import imaging.math3D.Point2D;
import java.util.Vector;

public class PolygonArea extends Polygon
{
    private Vector<Point2D> curvePoints;
    private Vector<Point2D> polygPoints;
    private Vector<Integer> atCurve;
    private boolean isSubtracted;
    
    public PolygonArea() {
        this.curvePoints = new Vector<Point2D>();
        this.polygPoints = new Vector<Point2D>();
        this.atCurve = new Vector<Integer>();
    }
    
    private void refreshPolyPoints(final int selectPoint) {
        final Point2D a = this.points.elementAt(selectPoint);
        final Point2D b = this.points.elementAt(selectPoint + 1);
        final Point2D c = this.points.elementAt(selectPoint + 2);
        final Point2D d = this.points.elementAt(selectPoint + 3);
        final float[] GX = new float[4];
        final float[] GY = new float[4];
        GX[0] = a.getX();
        GX[1] = b.getX();
        GX[2] = c.getX();
        GX[3] = d.getX();
        GY[0] = a.getY();
        GY[1] = b.getY();
        GY[2] = c.getY();
        GY[3] = d.getY();
        final Cubic xSpline = new Cubic(Cubic.BEZIER, GX);
        final Cubic ySpline = new Cubic(Cubic.BEZIER, GY);
        int i = this.polygPoints.indexOf(a, selectPoint) + 1;
        for (float t = 0.01f; t < 1.0f; t += 0.02) {
            final float ix = xSpline.eval(t);
            final float iy = ySpline.eval(t);
            this.polygPoints.setElementAt(new Point2D(ix, iy), i++);
        }
    }
    
    public void addNewPoint(final boolean drawCurve, final int x, final int y) {
        final Point2D currentPoint = new Point2D(x, y);
        this.points.addElement(currentPoint);
        this.selectedPoint = this.points.size() - 1;
        if (!drawCurve) {
            if (this.curvePoints.size() > 1) {
                for (int i = 1; i < this.curvePoints.size(); ++i) {
                    this.polygPoints.addElement(this.curvePoints.elementAt(i));
                }
            }
            this.curvePoints.clear();
            this.curvePoints.addElement(currentPoint);
            this.polygPoints.addElement(currentPoint);
        }
        else {
            this.curvePoints.addElement(currentPoint);
            if (this.polygPoints.size() == 0) {
                this.polygPoints.addElement(currentPoint);
            }
            else if (this.curvePoints.size() > 3) {
                this.atCurve.add(this.selectedPoint - 3);
                this.polygPoints.addElement(this.points.elementAt(this.selectedPoint - 3));
                final Point2D a = this.curvePoints.elementAt(0);
                final Point2D b = this.curvePoints.elementAt(1);
                final Point2D c = this.curvePoints.elementAt(2);
                final Point2D d = this.curvePoints.elementAt(3);
                final float[] GX = new float[4];
                final float[] GY = new float[4];
                GX[0] = a.getX();
                GX[1] = b.getX();
                GX[2] = c.getX();
                GX[3] = d.getX();
                GY[0] = a.getY();
                GY[1] = b.getY();
                GY[2] = c.getY();
                GY[3] = d.getY();
                final Cubic xSpline = new Cubic(Cubic.BEZIER, GX);
                final Cubic ySpline = new Cubic(Cubic.BEZIER, GY);
                for (float t = 0.02f; t < 1.0f; t += 0.02) {
                    final float ix = xSpline.eval(t);
                    final float iy = ySpline.eval(t);
                    this.polygPoints.addElement(new Point2D(ix, iy));
                }
                this.curvePoints.clear();
                this.curvePoints.addElement(currentPoint);
            }
        }
    }
    
    @Override
    public void movePointTo(final int x, final int y) {
        final Point2D p = this.points.elementAt(this.selectedPoint);
        p.setX(x);
        p.setY(y);
        if (this.atCurve.contains(this.selectedPoint)) {
            this.refreshPolyPoints(this.selectedPoint);
        }
        if (this.atCurve.contains(this.selectedPoint - 1)) {
            this.refreshPolyPoints(this.selectedPoint - 1);
        }
        if (this.atCurve.contains(this.selectedPoint - 2)) {
            this.refreshPolyPoints(this.selectedPoint - 2);
        }
        if (this.atCurve.contains(this.selectedPoint - 3)) {
            this.refreshPolyPoints(this.selectedPoint - 3);
        }
    }
    
    public boolean isSubtracted() {
        return this.isSubtracted;
    }
    
    public void setSubtracted(final boolean isSubtracted) {
        this.isSubtracted = isSubtracted;
    }
    
    @Override
    public GeneralPath getGeneralPath() {
        if (this.polygPoints.size() == 0) {
            return null;
        }
        final GeneralPath path = new GeneralPath();
        Point2D v = this.polygPoints.elementAt(0);
        path.moveTo(v.getX(), v.getY());
        for (int i = 1; i < this.polygPoints.size(); ++i) {
            v = this.polygPoints.elementAt(i);
            path.lineTo(v.getX(), v.getY());
        }
        path.closePath();
        return path;
    }
    
    @Override
    public void drawLinesOpen(final Graphics2D graphics) {
        Point2D last = this.polygPoints.firstElement();
        final int[] x = new int[this.polygPoints.size()];
        final int[] y = new int[this.polygPoints.size()];
        x[0] = (int)last.getX();
        y[0] = (int)last.getY();
        for (int i = 1; i < this.polygPoints.size(); ++i) {
            final Point2D current = this.polygPoints.get(i);
            x[i] = (int)current.getX();
            y[i] = (int)current.getY();
            last = current;
        }
        graphics.drawPolyline(x, y, x.length);
    }
    
    @Override
    public void drawLines(final Graphics2D graphics) {
        Point2D last = this.polygPoints.firstElement();
        final int[] x = new int[this.polygPoints.size() + 1];
        final int[] y = new int[this.polygPoints.size() + 1];
        final Point2D first = last;
        x[0] = (int)first.getX();
        y[0] = (int)first.getY();
        for (int i = 1; i < this.polygPoints.size(); ++i) {
            final Point2D current = this.polygPoints.get(i);
            x[i] = (int)current.getX();
            y[i] = (int)current.getY();
            last = current;
        }
        x[x.length - 1] = (int)last.getX();
        y[y.length - 1] = (int)last.getY();
        graphics.drawPolyline(x, y, x.length);
    }
    
    @Override
    public void deletePoint() {
        if (this.atCurve.contains(this.selectedPoint) || this.atCurve.contains(this.selectedPoint - 1) || this.atCurve.contains(this.selectedPoint - 2) || this.atCurve.contains(this.selectedPoint - 3)) {
            return;
        }
        final Point2D p = this.points.elementAt(this.selectedPoint);
        this.points.removeElementAt(this.selectedPoint);
        this.polygPoints.removeElement(p);
        int ind = this.atCurve.indexOf(this.selectedPoint);
        this.selectedPoint = -1;
        if (ind == -1) {
            return;
        }
        while (ind < this.atCurve.size()) {
            Integer x = this.atCurve.elementAt(ind);
            --x;
            ++ind;
        }
    }
    
    public Vector<Point2D> getPolygPoints() {
        return this.polygPoints;
    }
}
