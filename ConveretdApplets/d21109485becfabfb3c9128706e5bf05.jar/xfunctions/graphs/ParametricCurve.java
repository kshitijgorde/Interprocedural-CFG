// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Point;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Color;
import xfunctions.functions.Variable;
import xfunctions.functions.Expression;

public class ParametricCurve extends Drawable
{
    private int[] xcoord;
    private int[] ycoord;
    private boolean needsReset;
    private double tmin;
    private double tmax;
    private int pointsOnCurve;
    private Expression xexp;
    private Expression yexp;
    private Variable v;
    private Color graphColor;
    
    public ParametricCurve() {
        this.needsReset = true;
        this.tmin = -5.0;
        this.tmax = 5.0;
        this.pointsOnCurve = 200;
        this.graphColor = Color.magenta;
    }
    
    public ParametricCurve(final Expression expression, final Expression expression2, final Variable variable, final double tmin, final double tmax, final int pointsOnCurve) {
        this.needsReset = true;
        this.tmin = -5.0;
        this.tmax = 5.0;
        this.pointsOnCurve = 200;
        this.graphColor = Color.magenta;
        this.setExpressions(expression, expression2, variable);
        this.tmin = tmin;
        this.tmax = tmax;
        this.pointsOnCurve = pointsOnCurve;
    }
    
    public Color getGraphColor() {
        return this.graphColor;
    }
    
    public void setGraphColor(final Color graphColor) {
        if (graphColor != null) {
            this.graphColor = graphColor;
        }
    }
    
    public Expression getXExpression() {
        return this.xexp;
    }
    
    public Expression getYExpression() {
        return this.yexp;
    }
    
    public void setExpressions(final Expression xexp, final Expression yexp) {
        this.xexp = xexp;
        this.yexp = yexp;
        this.reset();
    }
    
    public Variable getVariable() {
        return this.v;
    }
    
    public void setVariable(final Variable v) {
        this.v = v;
        this.reset();
    }
    
    public void setExpressions(final Expression xexp, final Expression yexp, final Variable v) {
        this.xexp = xexp;
        this.yexp = yexp;
        this.v = v;
        this.reset();
    }
    
    public void setCurveData(final double tmin, final double tmax, final int pointsOnCurve) {
        this.tmin = tmin;
        this.tmax = tmax;
        this.pointsOnCurve = pointsOnCurve;
        this.reset();
    }
    
    public double getX(final double value) {
        if (this.v != null && this.xexp != null) {
            this.v.setValue(value);
            return this.xexp.value();
        }
        return Double.NaN;
    }
    
    public double getY(final double value) {
        if (this.v != null && this.yexp != null) {
            this.v.setValue(value);
            return this.yexp.value();
        }
        return Double.NaN;
    }
    
    public synchronized void draw(final Graphics graphics, final CoordinateRect coordinateRect) {
        if (this.needsReset) {
            if (this.xexp != null && this.yexp != null && this.v != null) {
                this.setup(coordinateRect);
            }
            this.needsReset = false;
        }
        if (this.xexp == null || this.xcoord == null) {
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
    
    public synchronized void reset() {
        this.needsReset = true;
    }
    
    void releaseResources() {
        final int[] array = null;
        this.ycoord = array;
        this.xcoord = array;
        this.needsReset = true;
    }
    
    private void setup(final CoordinateRect coordinateRect) {
        final int[] array = null;
        this.ycoord = array;
        this.xcoord = array;
        if (this.pointsOnCurve < 2 || Double.isNaN(this.tmin) || Double.isNaN(this.tmax)) {
            return;
        }
        final boolean[] array2 = new boolean[this.pointsOnCurve];
        final double[] array3 = new double[this.pointsOnCurve];
        final double[] array4 = new double[this.pointsOnCurve];
        final double n = (this.tmax - this.tmin) / (this.pointsOnCurve - 1);
        this.v.setValue(this.tmin);
        array3[0] = this.xexp.value();
        for (int i = 1; i < this.pointsOnCurve - 1; ++i) {
            this.v.setValue(this.tmin + i * n);
            array3[i] = this.xexp.value();
            array2[i] = this.xexp.checkCases();
        }
        this.v.setValue(this.tmax);
        array3[this.pointsOnCurve - 1] = this.xexp.value();
        array2[this.pointsOnCurve - 1] = this.xexp.checkCases();
        this.v.setValue(this.tmin);
        array4[0] = this.yexp.value();
        for (int j = 1; j < this.pointsOnCurve - 1; ++j) {
            this.v.setValue(this.tmin + j * n);
            array4[j] = this.yexp.value();
            array2[j] = (this.yexp.checkCases() && array2[j]);
        }
        this.v.setValue(this.tmax);
        array4[this.pointsOnCurve - 1] = this.yexp.value();
        array2[this.pointsOnCurve - 1] = (this.yexp.checkCases() && array2[this.pointsOnCurve - 1]);
        final Vector vector = new Vector<Point>();
        final double max = Math.max(Math.abs(coordinateRect.getXmin() - 1000.0 * coordinateRect.getPixelWidth()), coordinateRect.getXmax() + 1000.0 * coordinateRect.getPixelWidth());
        final double max2 = Math.max(Math.abs(coordinateRect.getYmin() - 1000.0 * coordinateRect.getPixelHeight()), coordinateRect.getYmax() + 1000.0 * coordinateRect.getPixelHeight());
        array2[0] = true;
        int n2 = -1;
        for (int k = 0; k < this.pointsOnCurve; ++k) {
            if (Double.isNaN(array3[k]) || Math.abs(array3[k]) > max || Math.abs(array4[k]) > max2) {
                if (n2 != -1) {
                    vector.addElement(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE));
                }
                n2 = -1;
            }
            else if (array2[k] || n2 == -1) {
                vector.addElement(new Point(coordinateRect.xToPixel(array3[k]), coordinateRect.yToPixel(array4[k])));
                n2 = k;
            }
            else {
                final Point point = new Point(coordinateRect.xToPixel(array3[k]), coordinateRect.yToPixel(array4[k]));
                final Point point2 = vector.elementAt(vector.size() - 1);
                if (Math.abs(point2.x - point.x) > 1 || Math.abs(point2.y - point.y) > 1) {
                    this.fillIn(point2, point, this.tmin + n2 * n, this.tmin + k * n, vector, coordinateRect, 5);
                }
                final Point point3 = vector.elementAt(vector.size() - 1);
                if (point.x != point3.x || point.y != point3.y) {
                    vector.addElement(point);
                }
                n2 = k;
            }
        }
        if (vector.size() < 2) {
            return;
        }
        this.xcoord = new int[vector.size()];
        this.ycoord = new int[vector.size()];
        for (int l = 0; l < vector.size(); ++l) {
            final Point point4 = vector.elementAt(l);
            this.xcoord[l] = point4.x;
            this.ycoord[l] = point4.y;
        }
    }
    
    private void fillIn(final Point point, final Point point2, final double n, final double n2, final Vector vector, final CoordinateRect coordinateRect, final int n3) {
        if (n3 == 0 || (Math.abs(point.x - point2.x) <= 1 && Math.abs(point.y - point2.y) <= 1)) {
            final Point point3 = vector.elementAt(vector.size() - 1);
            if (point.x != point3.x || point.y != point3.y) {
                vector.addElement(point);
            }
            vector.addElement(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE));
            vector.addElement(point2);
            return;
        }
        final double n4 = (n + n2) / 2.0;
        this.v.setValue(n);
        this.xexp.value();
        this.v.setValue(n4);
        final double value = this.xexp.value();
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            this.fillIn(point, point2, 0.0, 0.0, vector, coordinateRect, 0);
        }
        else if (this.xexp.checkCases()) {
            this.v.setValue(n);
            this.yexp.value();
            this.v.setValue(n4);
            final double value2 = this.yexp.value();
            if (Double.isNaN(value2) || Double.isInfinite(value2)) {
                this.fillIn(point, point2, 0.0, 0.0, vector, coordinateRect, 0);
            }
            else if (this.yexp.checkCases()) {
                this.fillIn(new Point(coordinateRect.xToPixel(value), coordinateRect.yToPixel(value2)), point2, n4, n2, vector, coordinateRect, n3 - 1);
            }
            else {
                this.v.setValue(n4);
                this.fillIn(point, new Point(coordinateRect.xToPixel(value), coordinateRect.yToPixel(this.yexp.value())), n, n4, vector, coordinateRect, n3 - 1);
            }
        }
        else {
            this.v.setValue(n4);
            this.fillIn(point, new Point(coordinateRect.xToPixel(value), coordinateRect.yToPixel(this.yexp.value())), n, n4, vector, coordinateRect, n3 - 1);
        }
    }
}
