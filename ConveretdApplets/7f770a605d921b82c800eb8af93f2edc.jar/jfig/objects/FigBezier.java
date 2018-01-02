// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.canvas.FigTrafo2D;
import java.awt.Point;

public class FigBezier extends FigPolyline
{
    static final int PPMAX = 6000;
    static Point[] pp;
    Point[] wcp_spline;
    Point[] wcp1;
    FigBezier.ControlPoint[] cpp;
    int ii;
    double[][] stack;
    int stack_p;
    final double T;
    final double _2xPI;
    final double _1dSQR2;
    final double _SQR2;
    final double M_PI_2;
    
    public void createRenderer() {
        this.renderer = FigObjectFactory.getDefaultObjectFactory().createBezierSplineRenderer(this);
    }
    
    final int round(final double n) {
        return (int)(n + 0.5);
    }
    
    public double[] getControlPoints() {
        final double[] array = new double[4 * this.cpp.length];
        for (int i = 0; i < this.cpp.length; ++i) {
            array[4 * i] = this.cpp[i].lx;
            array[4 * i + 1] = this.cpp[i].ly;
            array[4 * i + 2] = this.cpp[i].rx;
            array[4 * i + 3] = this.cpp[i].ry;
        }
        return array;
    }
    
    public Point[] getSplinePoints() {
        return this.wcp_spline;
    }
    
    private void build_PP() {
        Point point = this.wcp1[0];
        FigBezier.ControlPoint controlPoint = this.cpp[0];
        this.ii = 0;
        for (int i = 1; i < this.wcp1.length; ++i) {
            final Point point2 = this.wcp1[i];
            final FigBezier.ControlPoint controlPoint2 = this.cpp[i];
            this.bezier_spline(point.x, point.y, controlPoint.rx, controlPoint.ry, controlPoint2.lx, controlPoint2.ly, point2.x, point2.y);
            point = point2;
            controlPoint = controlPoint2;
        }
        FigBezier.pp[this.ii++] = new Point(this.wcp1[this.wcp1.length - 1].x, this.wcp1[this.wcp1.length - 1].y);
    }
    
    private void bezier_spline(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        final double max = Math.max(20.0 / this.trafo.getZoom(), 20.0);
        this.clear_stack();
        this.push(n, n2, n3, n4, n5, n6, n7, n8);
        while (this.pop()) {
            final double n9 = this.stack[this.stack_p][0];
            final double n10 = this.stack[this.stack_p][1];
            final double n11 = this.stack[this.stack_p][2];
            final double n12 = this.stack[this.stack_p][3];
            final double n13 = this.stack[this.stack_p][4];
            final double n14 = this.stack[this.stack_p][5];
            final double n15 = this.stack[this.stack_p][6];
            final double n16 = this.stack[this.stack_p][7];
            if (Math.abs(n9 - n15) < max && Math.abs(n10 - n16) < max) {
                if (this.ii > 5996) {
                    break;
                }
                FigBezier.pp[this.ii++] = new Point(this.round(n9), this.round(n10));
            }
            else {
                final double n17 = 0.5 * (n11 + n13);
                final double n18 = 0.5 * (n12 + n14);
                final double n19 = 0.5 * (n9 + n11);
                final double n20 = 0.5 * (n10 + n12);
                final double n21 = 0.5 * (n19 + n17);
                final double n22 = 0.5 * (n20 + n18);
                final double n23 = 0.5 * (n13 + n15);
                final double n24 = 0.5 * (n14 + n16);
                final double n25 = 0.5 * (n23 + n17);
                final double n26 = 0.5 * (n24 + n18);
                final double n27 = 0.5 * (n21 + n25);
                final double n28 = 0.5 * (n22 + n26);
                this.push(n27, n28, n25, n26, n23, n24, n15, n16);
                this.push(n9, n10, n19, n20, n21, n22, n27, n28);
            }
        }
    }
    
    private void clear_stack() {
        this.stack_p = 0;
    }
    
    private void push(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        this.stack[this.stack_p][0] = n;
        this.stack[this.stack_p][1] = n2;
        this.stack[this.stack_p][2] = n3;
        this.stack[this.stack_p][3] = n4;
        this.stack[this.stack_p][4] = n5;
        this.stack[this.stack_p][5] = n6;
        this.stack[this.stack_p][6] = n7;
        this.stack[this.stack_p][7] = n8;
        ++this.stack_p;
    }
    
    private boolean pop() {
        if (this.stack_p == 0) {
            return false;
        }
        --this.stack_p;
        return true;
    }
    
    public String toString() {
        String s = "FigBezier with " + this.wcp.length + " control points: ";
        for (int i = 0; i < this.wcp.length; ++i) {
            s = s + "( " + this.wcp[i].x + ", " + this.wcp[i].y + "), ";
        }
        if (this.wcp_spline != null) {
            s = s + "\n#interpolated points: " + this.wcp_spline.length;
        }
        return s;
    }
    
    private void create_wcp1() {
        if (this.is_closed) {
            this.wcp1 = new Point[this.wcp.length + 1];
        }
        else {
            this.wcp1 = new Point[this.wcp.length];
        }
        for (int i = 0; i < this.wcp.length; ++i) {
            this.wcp1[i] = this.wcp[i];
        }
        if (this.is_closed) {
            this.wcp1[this.wcp.length] = this.wcp[0];
        }
    }
    
    private void create_control_points() {
        this.cpp = new FigBezier.ControlPoint[this.wcp1.length];
        for (int i = 0; i < this.cpp.length; ++i) {
            final FigBezier.ControlPoint[] cpp = this.cpp;
            final int n = i;
            if (this == null) {
                throw null;
            }
            cpp[n] = new FigBezier.ControlPoint(this);
        }
    }
    
    private void compute_control_points() {
        final double n = this.wcp1[0].x;
        final double n2 = this.wcp1[0].y;
        Point point2;
        final Point point = point2 = this.wcp1[1];
        final double n3 = point.x;
        final double n4 = point.y;
        final Point point3 = this.wcp1[2];
        double n5 = point3.x;
        double n6 = point3.y;
        final double n7 = n - n3;
        final double n8 = n4 - n2;
        final double sqrt = Math.sqrt(n7 * n7 + n8 * n8);
        double atan2;
        if (sqrt == 0.0) {
            atan2 = 0.0;
        }
        else {
            atan2 = Math.atan2(n8, n7);
        }
        final double n9 = n5 - n3;
        final double n10 = n4 - n6;
        double n11 = Math.sqrt(n9 * n9 + n10 * n10);
        double n12;
        if (n11 == 0.0) {
            n12 = 0.0;
        }
        else {
            n12 = Math.atan2(n10, n9);
        }
        if (atan2 < 0.0) {
            atan2 += 6.283185307179586;
        }
        if (n12 < 0.0) {
            n12 += 6.283185307179586;
        }
        FigBezier.ControlPoint controlPoint = this.cpp[1];
        this.set_control_point(n3, n4, sqrt, n11, atan2, n12, 0.45, controlPoint);
        if (!this.is_closed) {
            this.cpp[0].lx = 0.0;
            this.cpp[0].ly = 0.0;
            this.cpp[0].rx = (n + 3.0 * controlPoint.lx) * 0.25;
            this.cpp[0].ry = (n2 + 3.0 * controlPoint.ly) * 0.25;
            controlPoint.lx = (3.0 * controlPoint.lx + n3) * 0.25;
            controlPoint.ly = (3.0 * controlPoint.ly + n4) * 0.25;
        }
        int n13 = 2;
        double n14;
        double n15;
        double n16;
        double n17;
        while (true) {
            n14 = n5;
            n15 = n6;
            n16 = n11;
            if (n12 >= 3.141592653589793) {
                n17 = n12 - 3.141592653589793;
            }
            else {
                n17 = n12 + 3.141592653589793;
            }
            if (n13 >= this.wcp1.length - 1) {
                break;
            }
            ++n13;
            final Point point4 = this.wcp1[n13];
            point2 = this.wcp1[n13 - 1];
            n5 = point4.x;
            n6 = point4.y;
            final double n18 = n5 - n14;
            final double n19 = n15 - n6;
            n11 = Math.sqrt(n18 * n18 + n19 * n19);
            if (n11 == 0.0) {
                n12 = 0.0;
            }
            else {
                n12 = Math.atan2(n19, n18);
            }
            if (n12 < 0.0) {
                n12 += 6.283185307179586;
            }
            controlPoint = this.cpp[n13 - 1];
            this.set_control_point(n14, n15, n16, n11, n17, n12, 0.45, controlPoint);
        }
        if (this.is_closed) {
            final double n20 = point.x - n14;
            final double n21 = n15 - point.y;
            final double sqrt2 = Math.sqrt(n20 * n20 + n21 * n21);
            double atan3 = Math.atan2(n21, n20);
            if (atan3 < 0.0) {
                atan3 += 6.283185307179586;
            }
            final FigBezier.ControlPoint controlPoint2 = this.cpp[this.cpp.length - 1];
            this.set_control_point(n14, n15, n16, sqrt2, n17, atan3, 0.45, controlPoint2);
            this.cpp[0].lx = controlPoint2.lx;
            this.cpp[0].ly = controlPoint2.ly;
            this.cpp[0].rx = controlPoint2.rx;
            this.cpp[0].ry = controlPoint2.ry;
        }
        else {
            final FigBezier.ControlPoint controlPoint3 = this.cpp[this.cpp.length - 1];
            controlPoint3.lx = (3.0 * controlPoint.rx + n14) * 0.25;
            controlPoint3.ly = (3.0 * controlPoint.ry + n15) * 0.25;
            controlPoint3.rx = 0.0;
            controlPoint3.ry = 0.0;
            controlPoint.rx = (point2.x + 3.0 * controlPoint.rx) * 0.25;
            controlPoint.ry = (point2.y + 3.0 * controlPoint.ry) * 0.25;
        }
        if (this.debug) {
            System.out.println("FigBezier.compute_control_points(): ");
            for (int i = 0; i < this.wcp1.length; ++i) {
                System.out.println("wcp1[" + i + "]= (" + this.wcp1[i].x + ", " + this.wcp1[i].y + ")  cpp[]= (" + this.cpp[i].lx + ", " + this.cpp[i].ly + ", " + this.cpp[i].rx + ", " + this.cpp[i].rx + ") ");
            }
        }
    }
    
    void set_control_point(final double n, final double n2, double n3, double n4, double n5, double n6, final double n7, final FigBezier.ControlPoint controlPoint) {
        final double n8 = 1.0 - n7;
        final double n9 = 0.5 * (n5 + n6);
        double n10;
        if (n5 > n6) {
            n10 = Math.sin(n9 - n6);
            n5 = n9 + 1.5707963267948966;
            n6 = n9 - 1.5707963267948966;
        }
        else {
            n10 = Math.sin(n6 - n9);
            n5 = n9 - 1.5707963267948966;
            n6 = n9 + 1.5707963267948966;
        }
        if (n10 > 0.7071) {
            n10 = 1.4142 - n10;
        }
        final double n11 = n10 * n8;
        n3 *= n11;
        n4 *= n11;
        controlPoint.lx = n + n3 * Math.cos(n5);
        controlPoint.ly = n2 - n3 * Math.sin(n5);
        controlPoint.rx = n + n4 * Math.cos(n6);
        controlPoint.ry = n2 - n4 * Math.sin(n6);
    }
    
    public void rebuild() {
        if (this.wcp.length >= 3) {
            synchronized (FigBezier.pp) {
                this.create_wcp1();
                this.create_control_points();
                this.compute_control_points();
                this.build_PP();
                this.wcp_spline = new Point[this.ii];
                for (int i = 0; i < this.ii; ++i) {
                    this.wcp_spline[i] = FigBezier.pp[i];
                }
            }
            // monitorexit(FigBezier.pp)
        }
        else if (this.wcp.length == 2) {
            (this.wcp_spline = new Point[2])[0] = this.wcp[0];
            this.wcp_spline[1] = this.wcp[1];
        }
        else {
            this.wcp_spline = new Point[] { this.wcp[0] };
        }
        this.update_bbox();
        this.renderer.rebuild();
    }
    
    public FigObject copy() {
        if (this.debug) {
            System.out.println("FigBezier.copy()...");
        }
        final FigBezier figBezier = new FigBezier(this.wcp[0].x, this.wcp[0].y, this.is_closed, this.attribs.getClone(), this.trafo);
        figBezier.setPoints(this.getPoints());
        return figBezier;
    }
    
    public FigBezier(final int n, final int n2, final boolean b, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D) {
        super(n, n2, b, figAttribs, figTrafo2D);
        this.ii = 0;
        this.stack = new double[20][8];
        this.stack_p = 0;
        this.T = 0.45;
        this._2xPI = 6.283185307179586;
        this._1dSQR2 = 0.7071;
        this._SQR2 = 1.4142;
        this.M_PI_2 = 1.5707963267948966;
        if (b) {
            this.min_num_points = 3;
        }
        else {
            this.min_num_points = 2;
        }
    }
    
    static {
        FigBezier.pp = new Point[6000];
    }
}
