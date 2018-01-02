// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Point;
import java.awt.Graphics;
import java.util.Vector;
import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.Value;
import java.awt.Color;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.awt.Computable;

public class ParametricCurve extends Drawable implements Computable
{
    private Function xFunc;
    private Function yFunc;
    private Color graphColor;
    private boolean changed;
    private transient int[] xcoord;
    private transient int[] ycoord;
    private Value tmin;
    private Value tmax;
    private Value intervals;
    private double tmin_val;
    private double tmax_val;
    private int intervals_val;
    private double[] v;
    private Cases case1x;
    private Cases case2x;
    private Cases case1y;
    private Cases case2y;
    private Cases case3x;
    private Cases case3y;
    private Vector points;
    private static int MAXDEPTH;
    
    static {
        ParametricCurve.MAXDEPTH = 10;
    }
    
    public ParametricCurve() {
        this(null, null, null, null, null);
    }
    
    public ParametricCurve(final Function function, final Function function2) {
        this(function, function2, null, null, null);
    }
    
    public ParametricCurve(final Function xFunc, final Function yFunc, final Value tmin, final Value tmax, final Value value) {
        this.graphColor = Color.magenta;
        this.v = new double[1];
        this.case1x = new Cases();
        this.case2x = new Cases();
        this.case1y = new Cases();
        this.case2y = new Cases();
        this.case3x = new Cases();
        this.case3y = new Cases();
        this.points = new Vector(250);
        if ((xFunc != null && xFunc.getArity() != 1) || (yFunc != null && yFunc.getArity() != 1)) {
            throw new IllegalArgumentException("Internal Error:  The functions that define a parametric curve must be functions of one variable.");
        }
        this.xFunc = xFunc;
        this.yFunc = yFunc;
        this.tmin = tmin;
        this.tmax = tmax;
        this.intervals = this.intervals;
        this.changed = true;
    }
    
    public void setColor(final Color graphColor) {
        if (graphColor != null & (graphColor.equals(this.graphColor) ^ true)) {
            this.graphColor = graphColor;
            this.needsRedraw();
        }
    }
    
    public Color getColor() {
        return this.graphColor;
    }
    
    public synchronized void setFunctions(final Function xFunction, final Function yFunction) {
        this.setXFunction(xFunction);
        this.setYFunction(yFunction);
    }
    
    public synchronized void setXFunction(final Function xFunc) {
        if (xFunc != null && xFunc.getArity() != 1) {
            throw new IllegalArgumentException("Internal Error:  ParametricCurve can only graph functions of one variable.");
        }
        if (xFunc != this.xFunc) {
            this.xFunc = xFunc;
            this.changed = true;
            this.needsRedraw();
        }
    }
    
    public synchronized void setYFunction(final Function yFunc) {
        if (yFunc != null && yFunc.getArity() != 1) {
            throw new IllegalArgumentException("Internal Error:  ParametricCurve can only graph functions of one variable.");
        }
        if (yFunc != this.yFunc) {
            this.yFunc = yFunc;
            this.changed = true;
            this.needsRedraw();
        }
    }
    
    public Function getXFunction() {
        return this.xFunc;
    }
    
    public Function getYFunction() {
        return this.yFunc;
    }
    
    public void setIntervals(final Value intervals) {
        this.intervals = intervals;
        this.changed = true;
    }
    
    public Value getIntervals() {
        return this.intervals;
    }
    
    public void setLimits(final Value tMin, final Value tMax) {
        this.setTMin(tMin);
        this.setTMax(tMax);
    }
    
    public Value getTMin() {
        return this.tmin;
    }
    
    public Value getTMax() {
        return this.tmax;
    }
    
    public void setTMin(final Value tmin) {
        this.tmin = tmin;
        this.changed = true;
    }
    
    public void setTMax(final Value tmax) {
        this.tmax = tmax;
        this.changed = false;
    }
    
    public synchronized void compute() {
        this.setup();
        this.needsRedraw();
        this.changed = false;
    }
    
    public synchronized void draw(final Graphics graphics, final boolean b) {
        if (this.changed || b || this.xcoord == null || this.ycoord == null) {
            this.setup();
            this.changed = false;
        }
        if (this.xcoord == null || this.xcoord.length == 0) {
            return;
        }
        graphics.setColor(this.graphColor);
        int n = this.xcoord[0];
        int n2 = this.ycoord[0];
        for (int i = 1; i < this.xcoord.length; ++i) {
            if (this.xcoord[i] == Integer.MIN_VALUE) {
                while (++i < this.xcoord.length && this.xcoord[i] == Integer.MIN_VALUE) {}
                if (i < this.xcoord.length) {
                    n = this.xcoord[i];
                    n2 = this.ycoord[i];
                }
            }
            else {
                final int n3 = this.xcoord[i];
                final int n4 = this.ycoord[i];
                graphics.drawLine(n, n2, n3, n4);
                n = n3;
                n2 = n4;
            }
        }
    }
    
    private Point eval(final double n, final Cases cases, final Cases cases2) {
        this.v[0] = n;
        if (cases != null) {
            cases.clear();
        }
        if (cases2 != null) {
            cases2.clear();
        }
        final double valueWithCases = this.xFunc.getValueWithCases(this.v, cases);
        final double valueWithCases2 = this.yFunc.getValueWithCases(this.v, cases2);
        if (Double.isNaN(valueWithCases) || Double.isNaN(valueWithCases2)) {
            return null;
        }
        final int xToPixel = super.coords.xToPixel(valueWithCases);
        final int yToPixel = super.coords.yToPixel(valueWithCases2);
        if (Math.abs(xToPixel) > 10000 || Math.abs(yToPixel) > 10000) {
            return null;
        }
        return new Point(xToPixel, yToPixel);
    }
    
    private void setup() {
        if (this.xFunc == null || this.yFunc == null || super.coords == null) {
            final int[] array = new int[0];
            this.ycoord = array;
            this.xcoord = array;
            return;
        }
        if (this.tmin == null) {
            this.tmin_val = -5.0;
        }
        else {
            this.tmin_val = this.tmin.getVal();
        }
        if (this.tmax == null) {
            this.tmax_val = 5.0;
        }
        else {
            this.tmax_val = this.tmax.getVal();
        }
        double val;
        if (this.intervals == null) {
            val = 200.0;
        }
        else {
            val = this.intervals.getVal();
        }
        if (Double.isInfinite(this.tmin_val) || Double.isInfinite(this.tmax_val) || Double.isInfinite(val) || Double.isNaN(this.tmax_val) || Double.isNaN(val)) {
            this.tmin_val = Double.NaN;
        }
        if (val < 1.0) {
            this.intervals_val = 1;
        }
        else if (this.intervals_val > 10000) {
            this.intervals_val = 10000;
        }
        else {
            this.intervals_val = (int)Math.round(val);
        }
        if (Double.isNaN(this.tmin_val)) {
            final int[] array2 = new int[0];
            this.ycoord = array2;
            this.xcoord = array2;
            return;
        }
        this.points.setSize(0);
        final double n = (this.tmax_val - this.tmin_val) / this.intervals_val;
        Point eval = this.eval(this.tmin_val, this.case1x, this.case1y);
        if (eval != null) {
            this.points.addElement(eval);
        }
        for (int i = 1; i <= this.intervals_val; ++i) {
            final double n2 = this.tmin_val + i * n;
            final Point eval2 = this.eval(n2, this.case2x, this.case2y);
            if (eval2 != null && eval != null) {
                if (!this.case1x.equals(this.case2x) || !this.case1y.equals(this.case2y)) {
                    this.discontinuity(eval, this.tmin_val + (i - 1) * n, eval2, n2, 0);
                }
                else {
                    this.points.addElement(eval2);
                }
            }
            else if (eval == null && eval2 != null) {
                this.becomesDefined(eval, this.tmin_val + (i - 1) * n, eval2, n2, 0);
            }
            else if (eval != null && eval2 == null) {
                this.becomesUndefined(eval, this.tmin_val + (i - 1) * n, eval2, n2, 0);
            }
            eval = eval2;
            final Cases case1x = this.case1x;
            this.case1x = this.case2x;
            this.case2x = case1x;
            final Cases case1y = this.case1y;
            this.case1y = this.case2y;
            this.case2y = case1y;
        }
        this.xcoord = new int[this.points.size()];
        this.ycoord = new int[this.points.size()];
        for (int j = 0; j < this.ycoord.length; ++j) {
            final Point point = this.points.elementAt(j);
            this.xcoord[j] = point.x;
            this.ycoord[j] = point.y;
        }
    }
    
    void discontinuity(final Point point, final double n, final Point point2, final double n2, final int n3) {
        if (n3 >= ParametricCurve.MAXDEPTH || (Math.abs(point.x - point2.x) < 2 && Math.abs(point.y - point2.y) < 2)) {
            if (this.points.elementAt(this.points.size() - 1) != point) {
                this.points.addElement(point);
            }
            if (n3 >= ParametricCurve.MAXDEPTH) {
                this.points.addElement(new Point(Integer.MIN_VALUE, 0));
            }
            this.points.addElement(point2);
            return;
        }
        final double n4 = (n + n2) / 2.0;
        final Point eval = this.eval(n4, this.case3x, this.case3y);
        if (eval == null) {
            this.becomesUndefined(point, n, eval, n4, n3 + 1);
            this.becomesDefined(eval, n4, point2, n2, n3 + 1);
        }
        else if (this.case3x.equals(this.case1x) && this.case3y.equals(this.case1y)) {
            this.discontinuity(eval, n4, point2, n2, n3 + 1);
        }
        else if (this.case3x.equals(this.case2x) && this.case3y.equals(this.case2y)) {
            this.discontinuity(point, n, eval, n4, n3 + 1);
        }
        else {
            this.discontinuity(point, n, eval, n4, n3 + 2);
            this.discontinuity(eval, n4, point2, n2, n3 + 2);
        }
    }
    
    void becomesUndefined(final Point point, final double n, final Point point2, final double n2, final int n3) {
        if (n3 >= ParametricCurve.MAXDEPTH) {
            if (this.points.elementAt(this.points.size() - 1) != point) {
                this.points.addElement(point);
            }
            this.points.addElement(new Point(Integer.MIN_VALUE, 0));
            return;
        }
        final double n4 = (n + n2) / 2.0;
        final Point eval = this.eval(n4, null, null);
        if (eval == null) {
            this.becomesUndefined(point, n, eval, n4, n3 + 1);
        }
        else {
            this.becomesUndefined(eval, n4, point2, n2, n3 + 1);
        }
    }
    
    void becomesDefined(final Point point, final double n, final Point point2, final double n2, final int n3) {
        if (n3 >= ParametricCurve.MAXDEPTH) {
            if (this.points.size() > 0) {
                this.points.addElement(new Point(Integer.MIN_VALUE, 0));
            }
            this.points.addElement(point2);
            return;
        }
        final double n4 = (n + n2) / 2.0;
        final Point eval = this.eval(n4, null, null);
        if (eval != null) {
            this.becomesDefined(point, n, eval, n4, n3 + 1);
        }
        else {
            this.becomesDefined(eval, n4, point2, n2, n3 + 1);
        }
    }
}
