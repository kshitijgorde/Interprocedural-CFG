// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

import java.util.Vector;

public class TableFunction extends Function
{
    private Vector points;
    private int derivativeLevel;
    public static final int STEP_FUNCTION = 0;
    public static final int PIECEWISE_LINEAR = 1;
    public static final int BEZIER_FUNCTION = 2;
    private int style;
    private BezierFunction bezier;
    private int caseNum;
    private int prevCaseNum;
    
    public TableFunction() {
        this.derivativeLevel = 0;
        this.style = 0;
        this.prevCaseNum = -1;
        super.arity = 1;
        (super.params = new Variable[1])[0] = new Variable("x");
        this.points = new Vector();
    }
    
    public TableFunction(final TableFunction tableFunction) {
        this();
        super.preferredXmin = tableFunction.preferredXmin;
        super.preferredXmax = tableFunction.preferredXmax;
        super.preferredYmin = tableFunction.preferredYmin;
        super.preferredYmax = tableFunction.preferredYmax;
        this.derivativeLevel = tableFunction.derivativeLevel;
        this.points = new Vector();
        for (int i = 0; i < tableFunction.points.size(); ++i) {
            final RealPoint2D realPoint2D = tableFunction.points.elementAt(i);
            this.points.addElement(new RealPoint2D(realPoint2D.getX(), realPoint2D.getY()));
        }
        this.style = tableFunction.style;
    }
    
    public TableFunction(final String name, final int style, final double[][] array) {
        this();
        this.setName(name);
        this.style = style;
        for (int i = 0; i < array.length; ++i) {
            this.points.addElement(new RealPoint2D(array[i][0], array[i][1]));
        }
    }
    
    public void stealDataFrom(final TableFunction tableFunction) {
        super.preferredXmin = tableFunction.preferredXmin;
        super.preferredXmax = tableFunction.preferredXmax;
        super.preferredYmin = tableFunction.preferredYmin;
        super.preferredYmax = tableFunction.preferredYmax;
        this.derivativeLevel = tableFunction.derivativeLevel;
        this.points = tableFunction.points;
        this.style = tableFunction.style;
        this.bezier = tableFunction.bezier;
    }
    
    public Expression getFunctionExpression(final Variable variable) {
        return new FunctionNode(this, new Expression[] { new VariableNode(variable) });
    }
    
    int getDerivativeLevel() {
        return this.derivativeLevel;
    }
    
    public boolean checkLimits() {
        if (this.points.size() == 0) {
            return false;
        }
        final RealPoint2D realPoint2D = this.points.elementAt(0);
        double x;
        double n = x = realPoint2D.getX();
        double y;
        double n2 = y = realPoint2D.getY();
        for (int i = 0; i < this.points.size(); ++i) {
            final RealPoint2D realPoint2D2 = this.points.elementAt(i);
            final double x2 = realPoint2D2.getX();
            final double y2 = realPoint2D2.getY();
            if (x2 < x) {
                x = x2;
            }
            else if (x2 > n) {
                n = x2;
            }
            if (y2 < y) {
                y = y2;
            }
            else if (y2 > n2) {
                n2 = y2;
            }
        }
        if (super.preferredXmin <= x && super.preferredXmax >= n && super.preferredYmin <= y && super.preferredYmax >= n2) {
            return false;
        }
        double preferredXmin;
        double preferredXmax;
        if (x == n) {
            preferredXmin = x - 1.0;
            preferredXmax = n + 1.0;
        }
        else {
            preferredXmin = x - (n - x) / 10.0;
            preferredXmax = n + (n - preferredXmin) / 10.0;
        }
        double preferredYmin;
        double preferredYmax;
        if (y == n2) {
            preferredYmin = y - 1.0;
            preferredYmax = n2 + 1.0;
        }
        else {
            preferredYmin = y - (n2 - y) / 4.0;
            preferredYmax = n2 + (n2 - preferredYmin) / 4.0;
        }
        if (super.preferredXmin > preferredXmin) {
            super.preferredXmin = preferredXmin;
        }
        if (super.preferredXmax < preferredXmax) {
            super.preferredXmax = preferredXmax;
        }
        if (super.preferredYmin > preferredYmin) {
            super.preferredYmin = preferredYmin;
        }
        if (super.preferredYmax < preferredYmax) {
            super.preferredYmax = preferredYmax;
        }
        return true;
    }
    
    public void setStyle(final int style) {
        if (style == this.style || style < 0 || style > 2) {
            return;
        }
        this.style = style;
        this.bezier = null;
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public int getPointCount() {
        return this.points.size();
    }
    
    public RealPoint2D getPoint(final int n) {
        if (n < 0 || n >= this.points.size()) {
            return null;
        }
        return this.points.elementAt(n);
    }
    
    public double getX(final int n) {
        final RealPoint2D point = this.getPoint(n);
        return (point == null) ? Double.NaN : point.getX();
    }
    
    public double getY(final int n) {
        final RealPoint2D point = this.getPoint(n);
        return (point == null) ? Double.NaN : point.getY();
    }
    
    public int changeY(final double n, final double n2) {
        for (int i = 0; i < this.points.size(); ++i) {
            if (n == this.getX(i)) {
                this.points.setElementAt(new RealPoint2D(n, n2), i);
                this.bezier = null;
                return i;
            }
        }
        return -1;
    }
    
    public int newPoint(final double n, final double n2) {
        for (int i = 0; i < this.points.size(); ++i) {
            final RealPoint2D realPoint2D = this.points.elementAt(i);
            if (realPoint2D.getX() == n) {
                return -1;
            }
            if (n < realPoint2D.getX()) {
                this.points.insertElementAt(new RealPoint2D(n, n2), i);
                this.bezier = null;
                return i;
            }
        }
        this.bezier = null;
        this.points.addElement(new RealPoint2D(n, n2));
        return this.points.size() - 1;
    }
    
    public void deletePoint(final int n) {
        if (n < 0 || n >= this.points.size()) {
            return;
        }
        this.points.removeElementAt(n);
        this.bezier = null;
    }
    
    public double eval(final double n) {
        this.prevCaseNum = this.caseNum;
        this.caseNum = -1;
        final int size = this.points.size();
        if (size == 0) {
            return Double.NaN;
        }
        if (size == 1) {
            return (this.derivativeLevel == 0 && n == this.getX(0)) ? this.getY(0) : Double.NaN;
        }
        if (this.style == 2) {
            if (this.bezier == null) {
                this.bezier = new BezierFunction(this);
            }
            return this.bezier.eval(n);
        }
        RealPoint2D realPoint2D = this.points.elementAt(0);
        if (n < realPoint2D.getX() || n > this.points.elementAt(size - 1).getX()) {
            return Double.NaN;
        }
        if (this.derivativeLevel == 0) {
            int i = 1;
            while (i < size) {
                final RealPoint2D realPoint2D2 = this.points.elementAt(i);
                if (n < realPoint2D2.getX()) {
                    this.caseNum = i;
                    if (this.style == 0) {
                        return realPoint2D.getY();
                    }
                    return realPoint2D.getY() + (n - realPoint2D.getX()) * (realPoint2D2.getY() - realPoint2D.getY()) / (realPoint2D2.getX() - realPoint2D.getX());
                }
                else {
                    realPoint2D = realPoint2D2;
                    ++i;
                }
            }
            if (n == realPoint2D.getX()) {
                this.caseNum = size;
                return realPoint2D.getY();
            }
        }
        else {
            if (this.derivativeLevel >= 2 || this.style == 0) {
                this.caseNum = 1;
                return 0.0;
            }
            for (int j = 1; j < size; ++j) {
                final RealPoint2D realPoint2D3 = this.points.elementAt(j);
                if (n < realPoint2D3.getX() || j == size - 1) {
                    this.caseNum = j;
                    return (realPoint2D3.getY() - realPoint2D.getY()) / (realPoint2D3.getX() - realPoint2D.getX());
                }
                realPoint2D = realPoint2D3;
            }
        }
        return Double.NaN;
    }
    
    public double eval(final double n, final double n2) {
        throw new IllegalArgumentException("Wrong number or parameters in function evaluation.");
    }
    
    public double eval(final double[] array) {
        if (array == null || array.length != 1) {
            throw new IllegalArgumentException("Wrong number or parameters in function evaluation.");
        }
        return this.eval(array[0]);
    }
    
    public Function derivative(final int n) {
        if (n != 1) {
            throw new IllegalArgumentException("Can't take derivative of a 1-argument function w.r.t. argument number " + n + ".");
        }
        final TableFunction tableFunction2;
        final TableFunction tableFunction = tableFunction2 = new TableFunction(this);
        ++tableFunction2.derivativeLevel;
        return tableFunction;
    }
    
    public boolean checkCases() {
        if (this.derivativeLevel == 0 && this.style != 0) {
            return true;
        }
        if (this.style == 2) {
            return this.bezier != null && this.bezier.checkCases();
        }
        return this.prevCaseNum == this.caseNum;
    }
}
