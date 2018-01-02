// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.canvas.FigTrafo2D;
import jfig.utils.Xspline;
import java.awt.Point;

public class FigXSpline extends FigPolyline
{
    protected double[] sfactors;
    protected Point[] wcp_spline;
    
    public void createRenderer() {
        this.renderer = FigObjectFactory.getDefaultObjectFactory().createXSplineRenderer(this);
    }
    
    public void createSfactors() {
        this.sfactors = new double[this.wcp.length];
        for (int i = 0; i < this.sfactors.length; ++i) {
            this.sfactors[i] = 1.0;
        }
        if (!this.is_closed) {
            this.sfactors[0] = 0.0;
            this.sfactors[this.sfactors.length - 1] = 0.0;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("FigXSpline with " + this.wcp.length + " control points: ");
        for (int i = 0; i < this.wcp.length; ++i) {
            sb.append("( " + this.wcp[i].x + ", " + this.wcp[i].y + "), ");
        }
        return sb.toString();
    }
    
    public void rebuild() {
        this.createSfactors();
        final Xspline xspline = new Xspline(this.wcp, this.sfactors);
        if (this.wcp.length > 2) {
            if (this.is_closed) {
                xspline.compute_closed_spline();
            }
            else {
                xspline.compute_open_spline();
            }
            this.wcp_spline = xspline.getComputedPointArray();
        }
        else if (this.wcp.length == 2) {
            this.wcp_spline = new Point[] { this.wcp[0], this.wcp[1] };
        }
        else {
            this.wcp_spline = new Point[] { this.wcp[0] };
        }
        this.renderer.rebuild();
        this.update_bbox();
    }
    
    public double[] getSFactors() {
        return this.sfactors;
    }
    
    public void setSFactors(final double[] sfactors) {
        if (sfactors.length != this.sfactors.length) {
            System.err.println("-E- FigXSpline.setSFactors: length mismatch! " + sfactors.length + " vs " + this.sfactors.length);
            return;
        }
        this.sfactors = sfactors;
        this.rebuild();
    }
    
    public Point[] getSplinePoints() {
        return this.wcp_spline;
    }
    
    public void dumpXsplinePoints() {
        final int length = this.wcp_spline.length;
        System.out.println("-#- dXPoints: " + length);
        for (int i = 0; i < length; ++i) {
            System.out.println("   " + this.wcp_spline[i]);
        }
    }
    
    public void dumpSfactors() {
        if (this.sfactors == null) {
            System.out.println("-#- dXSfactors: NULL");
            return;
        }
        System.out.println("-#- dXSfactors: " + this.getClass().getName());
        for (int i = 0; i < this.sfactors.length; ++i) {
            System.out.println("   " + this.sfactors[i]);
        }
    }
    
    public FigObject copy() {
        if (this.debug) {
            this.message("FigXSpline.copy()...");
        }
        final FigXSpline figXSpline = new FigXSpline(this.wcp[0].x, this.wcp[0].y, this.is_closed, this.attribs.getClone(), this.trafo);
        figXSpline.setPoints(this.getPoints());
        return figXSpline;
    }
    
    public FigXSpline() {
        if (this.is_closed) {
            this.min_num_points = 3;
        }
        else {
            this.min_num_points = 2;
        }
    }
    
    public FigXSpline(final int n, final int n2, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D) {
        this(n, n2, false, figAttribs, figTrafo2D);
        if (this.is_closed) {
            this.min_num_points = 3;
        }
        else {
            this.min_num_points = 2;
        }
    }
    
    public FigXSpline(final int n, final int n2, final boolean b, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D) {
        super(n, n2, b, figAttribs, figTrafo2D);
        if (b) {
            this.min_num_points = 3;
        }
        else {
            this.min_num_points = 2;
        }
    }
}
