// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.canvas.FigTrafo2D;
import java.awt.Point;

public class FigGeneralXSpline extends FigXSpline
{
    public void createSfactors() {
        if (this.sfactors == null || this.sfactors.length != this.wcp.length) {
            this.sfactors = new double[this.wcp.length];
            for (int i = 0; i < this.sfactors.length; ++i) {
                this.sfactors[i] = -1.0;
            }
            if (!this.is_closed) {
                this.sfactors[0] = 0.0;
                this.sfactors[this.sfactors.length - 1] = 0.0;
            }
        }
    }
    
    public FigObject copy() {
        final FigGeneralXSpline figGeneralXSpline = new FigGeneralXSpline(this.wcp[0].x, this.wcp[0].y, this.is_closed, this.attribs.getClone(), this.trafo);
        figGeneralXSpline.setPoints(this.getPoints());
        final double[] sFactors = new double[this.sfactors.length];
        System.arraycopy(this.sfactors, 0, sFactors, 0, this.sfactors.length);
        figGeneralXSpline.setSFactors(sFactors);
        return figGeneralXSpline;
    }
    
    public void insertPoint(final Point point, final Point point2) {
        if (this.debug) {
            this.message("FigGeneralXSpline.insertPoint()...");
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
            this.message("FigGeneralXSpline.insertPoint(): Point wprev " + point2.toString() + "is not a Point on this polyline!");
            return;
        }
        if (b) {
            this.message("FigGeneralXSpline.insertPoint(): Point wp " + point.toString() + "is already a Point on this polyline!");
            return;
        }
        final Point[] wcp = new Point[this.wcp.length + 1];
        final double[] sfactors = new double[this.wcp.length + 1];
        for (int j = 0; j <= n; ++j) {
            wcp[j] = this.wcp[j];
            sfactors[j] = this.sfactors[j];
        }
        wcp[n + 1] = new Point(point.x, point.y);
        sfactors[n + 1] = 1.0;
        for (int k = n + 2; k < wcp.length; ++k) {
            wcp[k] = this.wcp[k - 1];
            sfactors[k] = this.sfactors[k - 1];
        }
        this.wcp = wcp;
        this.sfactors = sfactors;
        this.rebuild();
    }
    
    public Point deletePoint(final Point point) {
        if (this.debug) {
            this.message("FigGeneralXSpline.deletePoint()...");
        }
        if (this.wcp.length <= this.min_num_points) {
            return null;
        }
        final int indexOfNearestNeighbor = this.indexOfNearestNeighbor(point);
        if (indexOfNearestNeighbor == -1) {
            this.message("FigGeneralXSpline.delete(): Point wp " + point.toString() + "is not a Point on this polyline!");
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
        final double[] sfactors = new double[this.wcp.length - 1];
        for (int i = 0; i < indexOfNearestNeighbor; ++i) {
            wcp[i] = this.wcp[i];
            sfactors[i] = this.sfactors[i];
        }
        for (int j = indexOfNearestNeighbor; j < wcp.length; ++j) {
            wcp[j] = this.wcp[j + 1];
            sfactors[j] = this.sfactors[j + 1];
        }
        this.wcp = wcp;
        this.sfactors = sfactors;
        this.rebuild();
        return point2;
    }
    
    public void rebuild() {
        super.rebuild();
    }
    
    public FigGeneralXSpline(final int n, final int n2, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D) {
        super(n, n2, figAttribs, figTrafo2D);
    }
    
    public FigGeneralXSpline(final int n, final int n2, final boolean b, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D) {
        super(n, n2, b, figAttribs, figTrafo2D);
    }
}
