// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import xfunctions.functions.Variable;
import xfunctions.functions.Expression;
import java.util.Vector;

public class Graph1D extends Drawable
{
    private int[] xcoord;
    private int[] ycoord;
    private Vector cachedFrames;
    private int currentFrame;
    private boolean needsReset;
    private Expression exp;
    private Variable v;
    private Color graphColor;
    private double absoluteYmax;
    private double onscreenymax;
    private double absoluteYmin;
    private double onscreenymin;
    private static final int UNDEFINED = 0;
    private static final int ABOVE = 1;
    private static final int BELOW = 2;
    private static final int ONSCREEN = 3;
    private static final int MAX_DEPTH = 10;
    
    public Graph1D() {
        this.cachedFrames = null;
        this.currentFrame = 0;
        this.needsReset = true;
        this.graphColor = Color.magenta;
    }
    
    public Graph1D(final Expression expression, final Variable variable) {
        this.cachedFrames = null;
        this.currentFrame = 0;
        this.needsReset = true;
        this.graphColor = Color.magenta;
        this.setExpression(expression, variable);
    }
    
    public Color getGraphColor() {
        return this.graphColor;
    }
    
    public void setGraphColor(final Color graphColor) {
        if (graphColor != null) {
            this.graphColor = graphColor;
        }
    }
    
    public Expression getExpression() {
        return this.exp;
    }
    
    public void setExpression(final Expression exp) {
        this.exp = exp;
        this.reset();
    }
    
    public Variable getVariable() {
        return this.v;
    }
    
    public void setVariable(final Variable v) {
        this.v = v;
        this.reset();
    }
    
    public void setExpression(final Expression exp, final Variable v) {
        this.exp = exp;
        this.v = v;
        this.reset();
    }
    
    public synchronized void draw(final Graphics graphics, final CoordinateRect coordinateRect) {
        if (this.needsReset) {
            if (this.exp != null && this.v != null) {
                this.setup(coordinateRect);
            }
            this.needsReset = false;
        }
        if (this.exp == null || this.xcoord == null) {
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
        if (this.cachedFrames != null) {
            this.cachedFrames.removeAllElements();
        }
    }
    
    void releaseResources() {
        this.reset();
        final int[] array = null;
        this.ycoord = array;
        this.xcoord = array;
    }
    
    synchronized void swapInFrame(int currentFrame) {
        if (currentFrame == this.currentFrame) {
            return;
        }
        if (currentFrame < 0) {
            currentFrame = 0;
        }
        if (!this.needsReset) {
            if (this.cachedFrames == null) {
                this.cachedFrames = new Vector(this.currentFrame + 50);
            }
            if (this.cachedFrames.size() <= this.currentFrame) {
                this.cachedFrames.setSize(this.currentFrame + 50);
            }
            final int[][] array = new int[2][];
            array[0] = this.xcoord;
            array[1] = this.ycoord;
            this.cachedFrames.setElementAt(array, this.currentFrame);
        }
        this.currentFrame = currentFrame;
        if (this.cachedFrames != null && this.currentFrame < this.cachedFrames.size()) {
            if (this.needsReset) {
                this.cachedFrames.setElementAt(null, this.currentFrame);
            }
            else {
                final int[][] array2 = this.cachedFrames.elementAt(this.currentFrame);
                if (array2 == null) {
                    this.needsReset = true;
                }
                else {
                    this.xcoord = array2[0];
                    this.ycoord = array2[1];
                    this.cachedFrames.setElementAt(null, this.currentFrame);
                }
            }
        }
        else {
            this.needsReset = true;
        }
    }
    
    private double eval(final double value) {
        this.v.setValue(value);
        final double value2 = this.exp.value();
        if (Double.isInfinite(value2) || Double.isNaN(value2)) {
            return Double.NaN;
        }
        if (value2 > this.absoluteYmax) {
            return this.absoluteYmax;
        }
        if (value2 < this.absoluteYmin) {
            return this.absoluteYmin;
        }
        return value2;
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
        final Vector vector = new Vector<Point>();
        this.onscreenymax = coordinateRect.getYmax() + (100 + coordinateRect.getGap()) * coordinateRect.getPixelHeight();
        this.onscreenymin = coordinateRect.getYmin() - (100 + coordinateRect.getGap()) * coordinateRect.getPixelHeight();
        this.absoluteYmax = coordinateRect.getYmax() + 5000.0 * coordinateRect.getPixelHeight();
        this.absoluteYmin = coordinateRect.getYmin() - 5000.0 * coordinateRect.getPixelHeight();
        int i = coordinateRect.getLeft();
        int n = Integer.MIN_VALUE;
        int n2 = 0;
        int n3 = 0;
        double n4 = coordinateRect.pixelToX(i);
        double n5 = this.eval(n4);
        int n6 = this.getStatus(n5);
        if (n6 == 3) {
            vector.addElement(new Point(i, coordinateRect.yToPixel(n5)));
        }
        else if (n6 != 0) {
            n = i;
            n2 = coordinateRect.yToPixel(n5);
            n3 = n6;
        }
        final int n7 = coordinateRect.getLeft() + coordinateRect.getWidth() - 1;
        while (i < n7) {
            final double n8 = n4;
            final double n9 = n5;
            final int n10 = n6;
            i += 4;
            if (i > n7) {
                i = n7;
            }
            n4 = coordinateRect.pixelToX(i);
            n5 = this.eval(n4);
            n6 = this.getStatus(n5);
            if (n6 == 0) {
                if (n10 == 0) {
                    continue;
                }
                if (n10 == 3) {
                    this.domainEndpoint(coordinateRect, vector, n8, n4, n9, n5, n10, n6, 1);
                }
                else if (n != Integer.MIN_VALUE) {
                    vector.addElement(new Point(n, n2));
                }
                n = Integer.MIN_VALUE;
                vector.addElement(new Point(Integer.MIN_VALUE, 0));
            }
            else if (n10 == 0) {
                if (n6 == 3) {
                    this.domainEndpoint(coordinateRect, vector, n8, n4, n9, n5, n10, n6, 1);
                    n5 = this.eval(n4);
                    vector.addElement(new Point(i, coordinateRect.yToPixel(n5)));
                    n = Integer.MIN_VALUE;
                }
                else {
                    n = i;
                    n2 = coordinateRect.yToPixel(n5);
                    n3 = n6;
                }
            }
            else if (this.exp.checkCases()) {
                if (n6 == 3) {
                    if (n != Integer.MIN_VALUE) {
                        vector.addElement(new Point(n, n2));
                        n = Integer.MIN_VALUE;
                    }
                    vector.addElement(new Point(i, coordinateRect.yToPixel(n5)));
                }
                else {
                    final int yToPixel = coordinateRect.yToPixel(n5);
                    if (n != Integer.MIN_VALUE) {
                        if (n6 != n3) {
                            vector.addElement(new Point(n, n2));
                            vector.addElement(new Point(i, yToPixel));
                            vector.addElement(new Point(Integer.MIN_VALUE, 0));
                        }
                    }
                    else {
                        vector.addElement(new Point(i, yToPixel));
                    }
                    n = i;
                    n2 = yToPixel;
                    n3 = n6;
                }
            }
            else if (n10 == 1 || n10 == 2) {
                if (n6 == n10) {
                    if (n != Integer.MIN_VALUE) {
                        vector.addElement(new Point(n, n2));
                        vector.addElement(new Point(Integer.MIN_VALUE, 0));
                    }
                    n = i;
                    n2 = coordinateRect.yToPixel(n5);
                    n3 = n6;
                }
                else if (n6 == 3) {
                    if (n != Integer.MIN_VALUE) {
                        vector.addElement(new Point(n, n2));
                        n = Integer.MIN_VALUE;
                    }
                    this.discontinuity(coordinateRect, vector, n8, n4, n9, n5, n10, n6, 1);
                    n5 = this.eval(n4);
                    vector.addElement(new Point(i, coordinateRect.yToPixel(n5)));
                }
                else {
                    if (n != Integer.MIN_VALUE) {
                        vector.addElement(new Point(n, n2));
                    }
                    vector.addElement(new Point(Integer.MIN_VALUE, 0));
                    n = i;
                    n2 = coordinateRect.yToPixel(n5);
                    n3 = n6;
                }
            }
            else {
                this.discontinuity(coordinateRect, vector, n8, n4, n9, n5, n10, n6, 1);
                n5 = this.eval(n4);
                if (n6 == 3) {
                    vector.addElement(new Point(i, coordinateRect.yToPixel(n5)));
                    n = Integer.MIN_VALUE;
                }
                else {
                    n = i;
                    n2 = coordinateRect.yToPixel(n5);
                    n3 = n6;
                }
            }
        }
        if (vector.size() > 1) {
            this.xcoord = new int[vector.size()];
            this.ycoord = new int[vector.size()];
            for (int j = 0; j < this.ycoord.length; ++j) {
                final Point point = vector.elementAt(j);
                this.xcoord[j] = point.x;
                this.ycoord[j] = point.y;
            }
        }
        else {
            this.xcoord = null;
            this.ycoord = null;
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
            eval = this.eval(n);
            final double eval3 = this.eval(n6);
            final boolean checkCases = this.exp.checkCases();
            eval2 = this.eval(n2);
            final boolean checkCases2 = this.exp.checkCases();
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
            else if (!checkCases) {
                this.discontinuity(coordinateRect, vector, n, n6, eval, eval3, n3, status, n5 + 1);
                if (!checkCases2) {
                    this.discontinuity(coordinateRect, vector, n6, n2, eval3, eval2, status, n4, n5 + 1);
                }
            }
            else if (!checkCases2) {
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
            final double eval = this.eval(n8);
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
