// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Color;
import java.awt.Graphics;
import xfunctions.functions.Variable;
import xfunctions.functions.Expression;

public class HiliteDisplayCanvas extends DisplayCanvas
{
    private double crossHair_x;
    private double crossHair_y;
    private double tangent_x;
    private double tangent_y;
    private double tangent_slope;
    private Graph1D graph;
    private Expression exp;
    private Expression deriv;
    private Variable v;
    private boolean clamp;
    
    public Graph1D getGraph() {
        return this.graph;
    }
    
    public void setGraph(final Graph1D graph) {
        this.graph = graph;
    }
    
    public void setDerivData(final Expression exp, final Variable v, final Expression deriv) {
        this.exp = exp;
        this.v = v;
        this.deriv = deriv;
    }
    
    public synchronized void crossHairAt(final double crossHair_x) {
        if (this.graph == null) {
            return;
        }
        if (Double.isNaN(this.crossHair_x)) {
            if (Double.isNaN(crossHair_x)) {
                return;
            }
        }
        else if (!Double.isNaN(crossHair_x) && crossHair_x == this.crossHair_x) {
            return;
        }
        this.clamp = false;
        final Graphics osg = this.getOSG();
        if (osg != null) {
            this.putCrosshair(osg);
        }
        this.crossHair_x = crossHair_x;
        final Expression expression = this.graph.getExpression();
        final Variable variable = this.graph.getVariable();
        if (expression == null || variable == null) {
            this.crossHair_x = Double.NaN;
            this.repaint();
            return;
        }
        variable.setValue(this.crossHair_x);
        this.crossHair_y = expression.value();
        if (Double.isNaN(this.crossHair_y) || Double.isInfinite(this.crossHair_y)) {
            this.crossHair_x = Double.NaN;
        }
        else if (osg != null) {
            this.putCrosshair(osg);
        }
        this.repaint();
    }
    
    public void crossHairAt(double crossHair_x, final double crossHair_y, final boolean clamp) {
        if (Double.isNaN(crossHair_y) || Double.isInfinite(crossHair_y) || Double.isInfinite(crossHair_x)) {
            crossHair_x = Double.NaN;
        }
        this.clamp = clamp;
        final Graphics osg = this.getOSG();
        if (osg != null) {
            this.putCrosshair(osg);
        }
        this.crossHair_x = crossHair_x;
        this.crossHair_y = crossHair_y;
        if (osg != null) {
            this.putCrosshair(osg);
        }
        this.repaint();
    }
    
    public void crossHairOff() {
        this.crossHairAt(Double.NaN, 0.0, this.clamp);
    }
    
    public synchronized void tangentAt(final double tangent_x) {
        if (this.graph == null) {
            return;
        }
        if (Double.isNaN(this.tangent_x)) {
            if (Double.isNaN(tangent_x)) {
                return;
            }
        }
        else if (!Double.isNaN(tangent_x) && tangent_x == this.tangent_x) {
            return;
        }
        final Graphics osg = this.getOSG();
        if (osg != null) {
            this.putTangent(osg);
        }
        this.tangent_x = tangent_x;
        final Expression expression = this.graph.getExpression();
        final Variable variable = this.graph.getVariable();
        if (expression == null || variable == null) {
            this.tangent_x = Double.NaN;
            this.repaint();
            return;
        }
        variable.setValue(this.tangent_x);
        this.tangent_y = expression.value();
        if (expression != this.exp || variable != this.v || this.deriv == null) {
            this.deriv = expression.derivative(variable);
            this.exp = expression;
            this.v = variable;
        }
        this.tangent_slope = this.deriv.value();
        if (Double.isNaN(this.tangent_slope) || Double.isInfinite(this.tangent_slope)) {
            this.tangent_x = Double.NaN;
        }
        else if (osg != null) {
            this.putTangent(osg);
        }
        this.repaint();
    }
    
    public void tangentOff() {
        this.tangentAt(Double.NaN);
    }
    
    public HiliteDisplayCanvas() {
        this((Graph1D)null);
    }
    
    public HiliteDisplayCanvas(final Graph1D graph) {
        final double n = Double.NaN;
        this.tangent_x = n;
        this.crossHair_x = n;
        this.graph = graph;
    }
    
    public void releaseResources() {
        this.exp = null;
        this.deriv = null;
        this.v = null;
        this.crossHair_x = Double.NaN;
        this.tangent_x = Double.NaN;
        super.releaseResources();
    }
    
    protected void drawExtraStuff(final Graphics graphics) {
        this.putCrosshair(graphics);
        this.putTangent(graphics);
    }
    
    private void putCrosshair(final Graphics graphics) {
        if (Double.isNaN(this.crossHair_x) || super.coords == null) {
            return;
        }
        int xToPixel = super.coords.xToPixel(this.crossHair_x);
        int yToPixel = super.coords.yToPixel(this.crossHair_y);
        if (this.clamp) {
            if (xToPixel < 0) {
                xToPixel = 0;
            }
            else if (xToPixel >= this.size().width) {
                xToPixel = this.size().width - 1;
            }
            if (yToPixel < 0) {
                yToPixel = 0;
            }
            else if (yToPixel >= this.size().height) {
                yToPixel = this.size().height - 1;
            }
        }
        else if (xToPixel < -4 || yToPixel < -4 || xToPixel > this.size().width + 5 || yToPixel > this.size().height + 5) {
            this.crossHair_x = Double.NaN;
            return;
        }
        graphics.setXORMode(Color.white);
        graphics.setColor(Color.black);
        graphics.drawLine(xToPixel - 7, yToPixel, xToPixel + 6, yToPixel);
        graphics.drawLine(xToPixel, yToPixel - 7, xToPixel, yToPixel + 6);
        graphics.setPaintMode();
    }
    
    private void putTangent(final Graphics graphics) {
        if (Double.isNaN(this.tangent_x) || this.graph == null || super.coords == null) {
            return;
        }
        final double xmin = super.coords.getXmin();
        final double xmax = super.coords.getXmax();
        final double ymin = super.coords.getYmin();
        final double ymax = super.coords.getYmax();
        double n;
        double n2;
        double n3;
        double n4;
        if (Math.abs(this.tangent_slope) > 1.0) {
            n = this.tangent_x + (ymax - this.tangent_y) / this.tangent_slope;
            n2 = ymax;
            n3 = this.tangent_x + (ymin - this.tangent_y) / this.tangent_slope;
            n4 = ymin;
        }
        else {
            n = xmin;
            n2 = this.tangent_y + this.tangent_slope * (xmin - this.tangent_x);
            n3 = xmax;
            n4 = this.tangent_y + this.tangent_slope * (xmax - this.tangent_x);
        }
        graphics.setXORMode(Color.white);
        graphics.setColor(Color.black);
        graphics.drawLine(super.coords.xToPixel(n), super.coords.yToPixel(n2), super.coords.xToPixel(n3), super.coords.yToPixel(n4));
        graphics.setPaintMode();
    }
}
