// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Point;
import java.util.Vector;
import java.awt.Graphics;
import edu.hws.jcm.data.Cases;
import java.awt.Color;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.awt.Computable;

public class Graph1D extends Drawable implements Computable
{
    private Function func;
    private Color graphColor;
    private boolean changed;
    private transient int[] xcoord;
    private transient int[] ycoord;
    private double absoluteYmax;
    private double onscreenymax;
    private double absoluteYmin;
    private double onscreenymin;
    private static final int UNDEFINED = 0;
    private static final int ABOVE = 1;
    private static final int BELOW = 2;
    private static final int ONSCREEN = 3;
    private double[] v;
    private Cases case1;
    private Cases case2;
    private static final int MAX_DEPTH = 10;
    
    public Graph1D() {
        this.graphColor = Color.magenta;
        this.v = new double[1];
        this.case1 = new Cases();
        this.case2 = new Cases();
    }
    
    public Graph1D(final Function function) {
        this.graphColor = Color.magenta;
        this.v = new double[1];
        this.case1 = new Cases();
        this.case2 = new Cases();
        this.setFunction(function);
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
    
    public synchronized void setFunction(final Function func) {
        if (func != null && func.getArity() != 1) {
            throw new IllegalArgumentException("Internal Error:  Graph1D can only graph a function of one variable.");
        }
        if (func != this.func) {
            this.func = func;
            this.changed = true;
            this.needsRedraw();
        }
    }
    
    public Function getFunction() {
        return this.func;
    }
    
    public synchronized void compute() {
        this.setup(super.coords);
        this.needsRedraw();
    }
    
    public synchronized void draw(final Graphics graphics, final boolean b) {
        if (this.changed || b || this.xcoord == null || this.ycoord == null) {
            this.setup(super.coords);
            this.changed = false;
        }
        if (this.xcoord.length == 0) {
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
    
    private double eval(final double n, final Cases cases) {
        this.v[0] = n;
        if (cases != null) {
            cases.clear();
        }
        final double valueWithCases = this.func.getValueWithCases(this.v, cases);
        if (Double.isInfinite(valueWithCases) || Double.isNaN(valueWithCases)) {
            return Double.NaN;
        }
        if (valueWithCases > this.absoluteYmax) {
            return this.absoluteYmax;
        }
        if (valueWithCases < this.absoluteYmin) {
            return this.absoluteYmin;
        }
        return valueWithCases;
    }
    
    private int getStatus(final double n) {
        if (Double.isNaN(n)) {
            return 0;
        }
        if (n > this.onscreenymax) {
            return 1;
        }
        if (n < this.onscreenymin) {
            return 2;
        }
        return 3;
    }
    
    private void setup(final CoordinateRect coordinateRect) {
        if (this.func == null || coordinateRect == null) {
            final int[] array = new int[0];
            this.ycoord = array;
            this.xcoord = array;
            return;
        }
        final Vector vector = new Vector<Point>();
        final double n = (coordinateRect.getXmax() - coordinateRect.getXmin()) / (coordinateRect.getWidth() - 2 * coordinateRect.getGap() - 1);
        this.onscreenymax = coordinateRect.getYmax() + (100 + coordinateRect.getGap()) * n;
        this.onscreenymin = coordinateRect.getYmin() - (100 + coordinateRect.getGap()) * n;
        this.absoluteYmax = coordinateRect.getYmax() + 5000.0 * n;
        this.absoluteYmin = coordinateRect.getYmin() - 5000.0 * n;
        int i = coordinateRect.getLeft();
        int n2 = Integer.MIN_VALUE;
        int n3 = 0;
        int n4 = 0;
        double n5 = coordinateRect.pixelToX(i);
        double n6 = this.eval(n5, this.case1);
        int n7 = this.getStatus(n6);
        if (n7 == 3) {
            vector.addElement(new Point(i, coordinateRect.yToPixel(n6)));
        }
        else if (n7 != 0) {
            n2 = i;
            n3 = coordinateRect.yToPixel(n6);
            n4 = n7;
        }
        final int n8 = coordinateRect.getLeft() + coordinateRect.getWidth() - 1;
        while (i < n8) {
            final double n9 = n5;
            final double n10 = n6;
            final int n11 = n7;
            i += 3;
            if (i > n8) {
                i = n8;
            }
            n5 = coordinateRect.pixelToX(i);
            n6 = this.eval(n5, this.case2);
            n7 = this.getStatus(n6);
            if (n7 == 0) {
                if (n11 != 0) {
                    if (n11 == 3) {
                        this.domainEndpoint(coordinateRect, vector, n9, n5, n10, n6, n11, n7, 1);
                    }
                    else if (n2 != Integer.MIN_VALUE) {
                        vector.addElement(new Point(n2, n3));
                    }
                    n2 = Integer.MIN_VALUE;
                    vector.addElement(new Point(Integer.MIN_VALUE, 0));
                }
            }
            else if (n11 == 0) {
                if (n7 == 3) {
                    this.domainEndpoint(coordinateRect, vector, n9, n5, n10, n6, n11, n7, 1);
                    vector.addElement(new Point(i, coordinateRect.yToPixel(n6)));
                    n2 = Integer.MIN_VALUE;
                }
                else {
                    n2 = i;
                    n3 = coordinateRect.yToPixel(n6);
                    n4 = n7;
                }
            }
            else if (this.case1.equals(this.case2)) {
                if (n7 == 3) {
                    if (n2 != Integer.MIN_VALUE) {
                        vector.addElement(new Point(n2, n3));
                        n2 = Integer.MIN_VALUE;
                    }
                    vector.addElement(new Point(i, coordinateRect.yToPixel(n6)));
                }
                else {
                    final int yToPixel = coordinateRect.yToPixel(n6);
                    if (n2 != Integer.MIN_VALUE) {
                        if (n7 != n4) {
                            vector.addElement(new Point(n2, n3));
                            vector.addElement(new Point(i, yToPixel));
                            vector.addElement(new Point(Integer.MIN_VALUE, 0));
                        }
                    }
                    else {
                        vector.addElement(new Point(i, yToPixel));
                    }
                    n2 = i;
                    n3 = yToPixel;
                    n4 = n7;
                }
            }
            else if (n11 == 1 || n11 == 2) {
                if (n7 == n11) {
                    if (n2 != Integer.MIN_VALUE) {
                        vector.addElement(new Point(n2, n3));
                        vector.addElement(new Point(Integer.MIN_VALUE, 0));
                    }
                    n2 = i;
                    n3 = coordinateRect.yToPixel(n6);
                    n4 = n7;
                }
                else if (n7 == 3) {
                    if (n2 != Integer.MIN_VALUE) {
                        vector.addElement(new Point(n2, n3));
                        n2 = Integer.MIN_VALUE;
                    }
                    this.discontinuity(coordinateRect, vector, n9, n5, n10, n6, n11, n7, 1);
                    n6 = this.eval(n5, this.case2);
                    vector.addElement(new Point(i, coordinateRect.yToPixel(n6)));
                }
                else {
                    if (n2 != Integer.MIN_VALUE) {
                        vector.addElement(new Point(n2, n3));
                    }
                    vector.addElement(new Point(Integer.MIN_VALUE, 0));
                    n2 = i;
                    n3 = coordinateRect.yToPixel(n6);
                    n4 = n7;
                }
            }
            else {
                this.discontinuity(coordinateRect, vector, n9, n5, n10, n6, n11, n7, 1);
                n6 = this.eval(n5, this.case2);
                if (n7 == 3) {
                    vector.addElement(new Point(i, coordinateRect.yToPixel(n6)));
                    n2 = Integer.MIN_VALUE;
                }
                else {
                    n2 = i;
                    n3 = coordinateRect.yToPixel(n6);
                    n4 = n7;
                }
            }
            final Cases case2 = this.case2;
            this.case2 = this.case1;
            this.case1 = case2;
        }
        this.xcoord = new int[vector.size()];
        this.ycoord = new int[vector.size()];
        for (int j = 0; j < this.ycoord.length; ++j) {
            final Point point = vector.elementAt(j);
            this.xcoord[j] = point.x;
            this.ycoord[j] = point.y;
        }
    }
    
    private void discontinuity(final CoordinateRect coordinateRect, final Vector vector, final double n, final double n2, double eval, double eval2, final int n3, final int n4, final int n5) {
        if (n5 == 10) {
            vector.addElement(new Point(coordinateRect.xToPixel(n), coordinateRect.yToPixel(eval)));
            vector.addElement(new Point(Integer.MIN_VALUE, 0));
            vector.addElement(new Point(coordinateRect.xToPixel(n2), coordinateRect.yToPixel(eval2)));
        }
        else {
            final double n6 = (n + n2) / 2.0;
            eval = this.eval(n, this.case1);
            final double eval3 = this.eval(n6, this.case2);
            final boolean equals = this.case1.equals(this.case2);
            eval2 = this.eval(n2, this.case1);
            final boolean equals2 = this.case1.equals(this.case2);
            final int status = this.getStatus(eval3);
            if (status == 0) {
                if (n3 == 3) {
                    this.domainEndpoint(coordinateRect, vector, n, n6, eval, eval3, n3, status, 1);
                }
                vector.addElement(new Point(Integer.MIN_VALUE, 0));
                if (n4 == 3) {
                    this.domainEndpoint(coordinateRect, vector, n6, n2, eval3, eval2, status, n4, 1);
                }
            }
            else if (!equals) {
                this.discontinuity(coordinateRect, vector, n, n6, eval, eval3, n3, status, n5 + 1);
                if (!equals2) {
                    this.discontinuity(coordinateRect, vector, n6, n2, eval3, eval2, status, n4, n5 + 1);
                }
            }
            else if (!equals2) {
                this.discontinuity(coordinateRect, vector, n6, n2, eval3, eval2, status, n4, n5 + 1);
            }
            else {
                System.out.println("Impossible error?  no discontinuity found in discontinuity for " + n + ',' + n2);
            }
        }
    }
    
    private void domainEndpoint(final CoordinateRect coordinateRect, final Vector vector, final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final int n7) {
        if (n7 == 20) {
            if (n5 == 3) {
                vector.addElement(new Point(coordinateRect.xToPixel(n), coordinateRect.yToPixel(n3)));
            }
            else {
                vector.addElement(new Point(coordinateRect.xToPixel(n2), coordinateRect.yToPixel(n4)));
            }
        }
        else {
            final double n8 = (n + n2) / 2.0;
            final double eval = this.eval(n8, null);
            final int status = this.getStatus(eval);
            if (status == 1 || status == 2) {
                vector.addElement(new Point(coordinateRect.xToPixel(n8), coordinateRect.yToPixel(eval)));
            }
            else if (status == n5) {
                this.domainEndpoint(coordinateRect, vector, n8, n2, eval, n4, status, n6, n7 + 1);
            }
            else {
                this.domainEndpoint(coordinateRect, vector, n, n8, n3, eval, n5, status, n7 + 1);
            }
        }
    }
}
