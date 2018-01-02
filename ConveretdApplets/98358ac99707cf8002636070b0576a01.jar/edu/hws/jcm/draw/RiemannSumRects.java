// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Graphics;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Value;
import java.awt.Color;
import edu.hws.jcm.awt.Computable;

public class RiemannSumRects extends Drawable implements Computable
{
    private double[] rectHeights;
    private int method;
    private Color color;
    private Color outlineColor;
    private double[] endpointVals;
    private double[] maxVals;
    private double[] minVals;
    private double[] midpointVals;
    private Value intervalCount;
    private Function func;
    private Function deriv;
    private double[] sum;
    private double[] param;
    private boolean changed;
    public static final int LEFTENDPOINT = 0;
    public static final int RIGHTENDPOINT = 1;
    public static final int MIDPOINT = 2;
    public static final int CIRCUMSCRIBED = 3;
    public static final int INSCRIBED = 4;
    public static final int TRAPEZOID = 5;
    public static final int CURRENT_METHOD = -1;
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        if (color != null) {
            this.color = color;
            this.needsRedraw();
        }
    }
    
    public void setOutlineColor(final Color outlineColor) {
        this.outlineColor = outlineColor;
        this.needsRedraw();
    }
    
    public Color getOutlineColor() {
        return this.outlineColor;
    }
    
    public void setFunction(final Function func) {
        if (func != null && func.getArity() != 1) {
            throw new IllegalArgumentException("Function for Riemann sums must have arity 1.");
        }
        this.deriv = (((this.func = func) == null) ? null : func.derivative(1));
        this.changed = true;
        this.needsRedraw();
    }
    
    public Function getFuction() {
        return this.func;
    }
    
    public void setMethod(final int method) {
        this.method = method;
        this.changed = true;
        this.needsRedraw();
    }
    
    public int getMethod() {
        return this.method;
    }
    
    public void compute() {
        this.changed = true;
        this.needsRedraw();
    }
    
    public Value getIntervalCount() {
        return this.intervalCount;
    }
    
    public void setIntervalCount(final Value intervalCount) {
        this.changed = true;
        this.intervalCount = intervalCount;
        this.needsRedraw();
    }
    
    public RiemannSumRects() {
        this(null, null);
    }
    
    public RiemannSumRects(final Function func, final Value intervalCount) {
        this.color = new Color(255, 255, 180);
        this.outlineColor = new Color(180, 180, 0);
        this.param = new double[1];
        this.changed = true;
        this.intervalCount = intervalCount;
        this.func = func;
        if (func != null) {
            this.deriv = this.func.derivative(1);
        }
        this.sum = new double[6];
        this.method = 0;
    }
    
    public void draw(final Graphics graphics, final boolean b) {
        if (this.func == null || super.coords == null) {
            return;
        }
        if (this.changed || this.rectHeights == null || b) {
            this.setSumData();
        }
        final int n = (this.method == 5 || this.method == 0 || this.method == 1) ? (this.rectHeights.length - 1) : this.rectHeights.length;
        double xmin = super.coords.getXmin();
        final double n2 = (super.coords.getXmax() - xmin) / n;
        final int yToPixel = super.coords.yToPixel(0.0);
        graphics.setColor(this.color);
        if (this.method == 5) {
            final int[] array = new int[4];
            final int[] array2 = new int[4];
            array[1] = super.coords.xToPixel(xmin);
            array2[0] = (array2[1] = yToPixel);
            array2[2] = super.coords.yToPixel(this.rectHeights[0]);
            for (int i = 0; i < n; ++i) {
                xmin += n2;
                array[0] = (array[3] = array[1]);
                array[1] = (array[2] = super.coords.xToPixel(xmin));
                array2[3] = array2[2];
                array2[2] = super.coords.yToPixel(this.rectHeights[i + 1]);
                graphics.fillPolygon(array, array2, 4);
                if (this.outlineColor != null) {
                    graphics.setColor(this.outlineColor);
                    graphics.drawPolygon(array, array2, 4);
                    graphics.setColor(this.color);
                }
            }
        }
        else {
            int xToPixel = super.coords.xToPixel(xmin);
            for (int j = 0; j < n; ++j) {
                final int xToPixel2 = super.coords.xToPixel(xmin + n2);
                final int n3 = xToPixel2 - xToPixel + 1;
                final int yToPixel2 = super.coords.yToPixel(this.rectHeights[(this.method == 1) ? (j + 1) : j]);
                final int n4 = yToPixel - yToPixel2;
                if (n4 > 0) {
                    graphics.fillRect(xToPixel, yToPixel2, n3, n4);
                }
                else if (n4 == 0) {
                    graphics.drawLine(xToPixel, yToPixel, xToPixel + n3 - 1, yToPixel);
                }
                else {
                    graphics.fillRect(xToPixel, yToPixel, n3, -n4);
                }
                if (this.outlineColor != null) {
                    graphics.setColor(this.outlineColor);
                    if (n4 > 0) {
                        graphics.drawRect(xToPixel, yToPixel2, n3, n4);
                    }
                    else if (n4 == 0) {
                        graphics.drawLine(xToPixel, yToPixel, xToPixel + n3 - 1, yToPixel);
                    }
                    else {
                        graphics.drawRect(xToPixel, yToPixel, n3, -n4);
                    }
                    graphics.setColor(this.color);
                }
                xmin += n2;
                xToPixel = xToPixel2;
            }
        }
    }
    
    private void setSumData() {
        this.changed = false;
        double n = (this.intervalCount == null) ? 5.0 : (this.intervalCount.getVal() + 0.5);
        if (Double.isNaN(n) || Double.isInfinite(n)) {
            n = 5.0;
        }
        else if (n < 0.0) {
            n = 1.0;
        }
        else if (n > 5000.0) {
            n = 5000.0;
        }
        final int n2 = (int)n;
        this.endpointVals = new double[n2 + 1];
        this.maxVals = new double[n2];
        this.minVals = new double[n2];
        this.midpointVals = new double[n2];
        double xmin = super.coords.getXmin();
        final double n3 = (super.coords.getXmax() - xmin) / n2;
        this.param[0] = xmin;
        this.endpointVals[0] = this.func.getVal(this.param);
        int n4 = 200 / n2;
        double n5;
        if (n4 < 1) {
            n4 = 1;
            n5 = n3;
        }
        else {
            n5 = n3 / n4;
        }
        boolean b = this.deriv.getVal(this.param) > 0.0;
        for (int i = 1; i <= n2; ++i) {
            xmin += n3;
            this.param[0] = xmin;
            this.endpointVals[i] = this.func.getVal(this.param);
            this.param[0] = xmin - n3 / 2.0;
            this.midpointVals[i - 1] = this.func.getVal(this.param);
            double n7;
            double n6 = n7 = this.endpointVals[i - 1];
            for (int j = 1; j <= n4; ++j) {
                final boolean b2 = b;
                final double n8 = xmin - n3 + j * n5;
                this.param[0] = n8;
                b = (this.deriv.getVal(this.param) > 0.0);
                if (b2 != b) {
                    if (b2) {
                        final double searchMax = this.searchMax(n8 - n5, n8, 1);
                        if (searchMax > n7) {
                            n7 = searchMax;
                        }
                    }
                    else {
                        final double searchMin = this.searchMin(n8 - n5, n8, 1);
                        if (searchMin < n6) {
                            n6 = searchMin;
                        }
                    }
                }
            }
            if (this.endpointVals[i] > n7) {
                n7 = this.endpointVals[i];
            }
            else if (this.endpointVals[i] < n6) {
                n6 = this.endpointVals[i];
            }
            this.minVals[i - 1] = n6;
            this.maxVals[i - 1] = n7;
        }
        final double n9 = this.endpointVals[0];
        double n10 = 0.0;
        double n11 = 0.0;
        double n12 = 0.0;
        double n13 = 0.0;
        for (int k = 0; k < n2; ++k) {
            n10 += this.endpointVals[k];
            n11 += this.midpointVals[k];
            n12 += this.maxVals[k];
            n13 += this.minVals[k];
        }
        final double n14 = n10 - this.endpointVals[0] + this.endpointVals[n2];
        this.sum[0] = n10 * n3;
        this.sum[1] = n14 * n3;
        this.sum[2] = n11 * n3;
        this.sum[3] = n12 * n3;
        this.sum[4] = n13 * n3;
        this.sum[5] = (n10 + n14) / 2.0 * n3;
        this.setRectData();
    }
    
    private void setRectData() {
        if (this.method == 3) {
            this.setRectHeights(this.maxVals);
        }
        else if (this.method == 4) {
            this.setRectHeights(this.minVals);
        }
        else if (this.method == 2) {
            this.setRectHeights(this.midpointVals);
        }
        else {
            this.setRectHeights(this.endpointVals);
        }
    }
    
    private void setRectHeights(final double[] rectHeights) {
        this.rectHeights = rectHeights;
        this.changed = true;
    }
    
    private double searchMin(final double n, final double n2, final int n3) {
        final double n4 = (n + n2) / 2.0;
        this.param[0] = n4;
        if (n3 >= 13) {
            return this.func.getVal(this.param);
        }
        if (this.deriv.getVal(this.param) < 0.0) {
            return this.searchMin(n4, n2, n3 + 1);
        }
        return this.searchMin(n, n4, n3 + 1);
    }
    
    private double searchMax(final double n, final double n2, final int n3) {
        final double n4 = (n + n2) / 2.0;
        this.param[0] = n4;
        if (n3 >= 13) {
            return this.func.getVal(this.param);
        }
        if (this.deriv.getVal(this.param) > 0.0) {
            return this.searchMin(n4, n2, n3 + 1);
        }
        return this.searchMin(n, n4, n3 + 1);
    }
    
    public Value getValueObject(final int n) {
        return new Value() {
            public double getVal() {
                if (RiemannSumRects.this.func == null || RiemannSumRects.this.coords == null) {
                    return Double.NaN;
                }
                if (RiemannSumRects.this.changed) {
                    RiemannSumRects.this.setSumData();
                }
                if (n == -1) {
                    return RiemannSumRects.this.sum[RiemannSumRects.this.method];
                }
                return RiemannSumRects.this.sum[n];
            }
        };
    }
}
