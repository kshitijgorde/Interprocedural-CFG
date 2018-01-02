// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.canvas.FigTrafo2D;
import java.awt.Point;

public class FigSpline extends FigPolyline
{
    static final int PPMAX = 6000;
    static Point[] pp;
    static int ii;
    static double[][] stack;
    static int stack_p;
    Point[] wcp_spline;
    
    public void createRenderer() {
        this.renderer = FigObjectFactory.getDefaultObjectFactory().createSplineRenderer(this);
    }
    
    final int round(final double n) {
        return (int)(n + 0.5);
    }
    
    public Point[] getSplinePoints() {
        return this.wcp_spline;
    }
    
    private void build_openPP() {
        FigSpline.ii = 0;
        final double n = this.wcp[0].x;
        final double n2 = this.wcp[0].y;
        double n3 = this.wcp[1].x;
        double n4 = this.wcp[1].y;
        double n5 = 0.5 * (n + n3);
        double n6 = 0.5 * (n2 + n4);
        double n7 = 0.5 * (n5 + n3);
        double n8 = 0.5 * (n6 + n4);
        FigSpline.pp[FigSpline.ii++] = new Point(this.round(n), this.round(n2));
        for (int i = 2; i < this.wcp.length; ++i) {
            final double n9 = n3;
            final double n10 = n4;
            n3 = this.wcp[i].x;
            n4 = this.wcp[i].y;
            final double n11 = 0.5 * (n9 + n3);
            final double n12 = 0.5 * (n10 + n4);
            this.quadratic_spline(n5, n6, n7, n8, 0.5 * (n9 + n11), 0.5 * (n10 + n12), n11, n12);
            n5 = n11;
            n6 = n12;
            n7 = 0.5 * (n5 + n3);
            n8 = 0.5 * (n6 + n4);
        }
        FigSpline.pp[FigSpline.ii++] = new Point(this.round(n5), this.round(n6));
        FigSpline.pp[FigSpline.ii++] = new Point(this.round(n3), this.round(n4));
    }
    
    private void build_closedPP() {
        FigSpline.ii = 0;
        if (this.debug) {
            this.message("FigSpline: build_closedPP() started...");
        }
        final Point[] array = new Point[this.wcp.length + 1];
        for (int i = 0; i < this.wcp.length; ++i) {
            array[i] = this.wcp[i];
        }
        array[this.wcp.length] = this.wcp[0];
        final double n = array[0].x;
        final double n2 = array[0].y;
        double n3 = array[1].x;
        double n4 = array[1].y;
        double n5 = 0.5 * (n + n3);
        double n6 = 0.5 * (n2 + n4);
        double n7 = 0.25 * (n + 3.0 * n3);
        double n8 = 0.25 * (n2 + 3.0 * n4);
        for (int j = 2; j < array.length; ++j) {
            final double n9 = n3;
            final double n10 = n4;
            n3 = array[j].x;
            n4 = array[j].y;
            final double n11 = 0.5 * (n9 + n3);
            final double n12 = 0.5 * (n10 + n4);
            this.quadratic_spline(n5, n6, n7, n8, 0.5 * (n9 + n11), 0.5 * (n10 + n12), n11, n12);
            n5 = n11;
            n6 = n12;
            n7 = 0.5 * (n5 + n3);
            n8 = 0.5 * (n6 + n4);
        }
        final double n13 = n3;
        final double n14 = n4;
        final double n15 = array[1].x;
        final double n16 = array[1].y;
        final double n17 = 0.5 * (n13 + n15);
        final double n18 = 0.5 * (n14 + n16);
        this.quadratic_spline(n5, n6, n7, n8, 0.5 * (n13 + n17), 0.5 * (n14 + n18), n17, n18);
        FigSpline.pp[FigSpline.ii++] = new Point(this.round(n17), this.round(n18));
    }
    
    private final void quadratic_spline(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        final double max = Math.max(80.0 / this.trafo.getZoom(), 100.0);
        this.clear_stack();
        this.push(n, n2, n3, n4, n5, n6, n7, n8);
        while (this.pop()) {
            final double n9 = FigSpline.stack[FigSpline.stack_p][0];
            final double n10 = FigSpline.stack[FigSpline.stack_p][1];
            final double n11 = FigSpline.stack[FigSpline.stack_p][2];
            final double n12 = FigSpline.stack[FigSpline.stack_p][3];
            final double n13 = FigSpline.stack[FigSpline.stack_p][4];
            final double n14 = FigSpline.stack[FigSpline.stack_p][5];
            final double n15 = FigSpline.stack[FigSpline.stack_p][6];
            final double n16 = FigSpline.stack[FigSpline.stack_p][7];
            final double n17 = 0.5 * (n11 + n13);
            final double n18 = 0.5 * (n12 + n14);
            if (Math.abs(n9 - n17) < max && Math.abs(n10 - n18) < max && Math.abs(n17 - n15) < max && Math.abs(n18 - n16) < max) {
                if (FigSpline.ii > 5997) {
                    break;
                }
                FigSpline.pp[FigSpline.ii++] = new Point(this.round(n9), this.round(n10));
                FigSpline.pp[FigSpline.ii++] = new Point(this.round(n17), this.round(n18));
            }
            else {
                this.push(n17, n18, 0.5 * (n17 + n13), 0.5 * (n18 + n14), 0.5 * (n13 + n15), 0.5 * (n14 + n16), n15, n16);
                this.push(n9, n10, 0.5 * (n9 + n11), 0.5 * (n10 + n12), 0.5 * (n11 + n17), 0.5 * (n12 + n18), n17, n18);
            }
        }
    }
    
    private void clear_stack() {
        FigSpline.stack_p = 0;
    }
    
    private final void push(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        FigSpline.stack[FigSpline.stack_p][0] = n;
        FigSpline.stack[FigSpline.stack_p][1] = n2;
        FigSpline.stack[FigSpline.stack_p][2] = n3;
        FigSpline.stack[FigSpline.stack_p][3] = n4;
        FigSpline.stack[FigSpline.stack_p][4] = n5;
        FigSpline.stack[FigSpline.stack_p][5] = n6;
        FigSpline.stack[FigSpline.stack_p][6] = n7;
        FigSpline.stack[FigSpline.stack_p][7] = n8;
        ++FigSpline.stack_p;
    }
    
    private boolean pop() {
        if (FigSpline.stack_p == 0) {
            return false;
        }
        --FigSpline.stack_p;
        return true;
    }
    
    public String toString() {
        String s = "FigSpline with " + this.wcp.length + " control points: ";
        for (int i = 0; i < this.wcp.length; ++i) {
            s = s + "( " + this.wcp[i].x + ", " + this.wcp[i].y + "), ";
        }
        if (this.wcp_spline != null) {
            s = s + "\n#interpolated points: " + this.wcp_spline.length;
        }
        return s;
    }
    
    public void rebuild() {
        if (this.wcp.length > 2) {
            synchronized (FigSpline.pp) {
                if (this.is_closed) {
                    this.build_closedPP();
                }
                else {
                    this.build_openPP();
                }
                this.wcp_spline = new Point[FigSpline.ii];
                for (int i = 0; i < FigSpline.ii; ++i) {
                    this.wcp_spline[i] = FigSpline.pp[i];
                }
            }
            // monitorexit(FigSpline.pp)
        }
        else if (this.wcp.length == 2) {
            (this.wcp_spline = new Point[2])[0] = this.wcp[0];
            this.wcp_spline[1] = this.wcp[1];
        }
        else {
            this.wcp_spline = new Point[] { this.wcp[0] };
        }
        this.renderer.rebuild();
        super.update_bbox();
    }
    
    public FigObject copy() {
        if (this.debug) {
            this.message("FigSpline.copy()...");
        }
        final FigSpline figSpline = new FigSpline(this.wcp[0].x, this.wcp[0].y, this.is_closed, this.attribs.getClone(), this.trafo);
        figSpline.setPoints(this.getPoints());
        return figSpline;
    }
    
    public FigSpline(final int n, final int n2, final boolean b, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D) {
        super(n, n2, b, figAttribs, figTrafo2D);
        if (b) {
            this.min_num_points = 3;
        }
        else {
            this.min_num_points = 2;
        }
    }
    
    static {
        FigSpline.pp = new Point[6000];
        FigSpline.ii = 0;
        FigSpline.stack = new double[20][8];
        FigSpline.stack_p = 0;
    }
}
