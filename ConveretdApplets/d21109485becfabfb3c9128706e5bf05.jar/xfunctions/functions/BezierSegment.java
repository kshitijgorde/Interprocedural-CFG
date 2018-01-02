// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

class BezierSegment
{
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private double d1;
    private double d2;
    private double a;
    private double b;
    private double c;
    private double d;
    boolean x1NotInDomain;
    boolean x2NotInDomain;
    BezierSegment leftNeighbor;
    BezierSegment rightNeighbor;
    boolean smoothOnLeft;
    boolean smoothOnRight;
    
    BezierSegment() {
    }
    
    BezierSegment(final double n, final double n2) {
        this.setData(n, n2, 0.0, 0.0, 0.0, 0.0);
    }
    
    BezierSegment(final BezierSegment bezierSegment) {
        this.setData(bezierSegment.x1, bezierSegment.x2, bezierSegment.y1, bezierSegment.y2, bezierSegment.d1, bezierSegment.d2);
        this.x1NotInDomain = bezierSegment.x1NotInDomain;
        this.x2NotInDomain = bezierSegment.x2NotInDomain;
        this.smoothOnLeft = bezierSegment.smoothOnLeft;
        this.smoothOnRight = bezierSegment.smoothOnRight;
        this.leftNeighbor = bezierSegment.leftNeighbor;
        this.rightNeighbor = bezierSegment.rightNeighbor;
    }
    
    double getX1() {
        return this.x1;
    }
    
    double getX2() {
        return this.x2;
    }
    
    double getY1() {
        return this.y1;
    }
    
    double getY2() {
        return this.y2;
    }
    
    double getD1() {
        return this.d1;
    }
    
    double getD2() {
        return this.d2;
    }
    
    boolean getClosedOnLeft() {
        return this.x1NotInDomain ^ true;
    }
    
    boolean getClosedOnRight() {
        return this.x2NotInDomain ^ true;
    }
    
    void setData(double x1, double x2, double y1, double y2, double d1, double d2) {
        if (x1 == x2) {
            throw new IllegalArgumentException("Attempt to make BezierSegment of length 0");
        }
        if (x1 > x2) {
            final double n = x1;
            x1 = x2;
            x2 = n;
            final double n2 = y1;
            y1 = y2;
            y2 = n2;
            final double n3 = d1;
            d1 = d2;
            d2 = n3;
        }
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.d1 = d1;
        this.d2 = d2;
        this.computeCoefficients();
        if (this.leftNeighbor != null) {
            this.leftNeighbor.y2 = this.y1;
            if (this.smoothOnLeft) {
                this.leftNeighbor.d2 = this.d1;
            }
            this.leftNeighbor.computeCoefficients();
        }
        if (this.rightNeighbor != null) {
            this.rightNeighbor.y1 = this.y2;
            if (this.smoothOnRight) {
                this.rightNeighbor.d1 = this.d2;
            }
            this.rightNeighbor.computeCoefficients();
        }
    }
    
    private void computeCoefficients() {
        final double n = this.x2 - this.x1;
        this.a = this.y2 / (n * n * n);
        this.b = this.d2 / (n * n) - 3.0 * this.a;
        final double n2 = -n;
        this.d = this.y1 / (n2 * n2 * n2);
        this.c = this.d1 / (n2 * n2) - 3.0 * this.d;
    }
    
    void set_y1(final double n) {
        this.setData(this.x1, this.x2, n, this.y2, this.d1, this.d2);
    }
    
    void set_y2(final double n) {
        this.setData(this.x1, this.x2, this.y1, n, this.d1, this.d2);
    }
    
    void set_d1(final double n) {
        this.setData(this.x1, this.x2, this.y1, this.y2, n, this.d2);
    }
    
    void set_d2(final double n) {
        this.setData(this.x1, this.x2, this.y1, this.y2, this.d1, n);
    }
    
    double value(final double n) {
        if (Double.isNaN(n)) {
            return Double.NaN;
        }
        if (n <= this.x1) {
            return (n < this.x1 || this.x1NotInDomain) ? Double.NaN : this.y1;
        }
        if (n >= this.x2) {
            return (n > this.x2 || this.x2NotInDomain) ? Double.NaN : this.y2;
        }
        final double n2 = n - this.x1;
        final double n3 = n2 * n2;
        final double n4 = n3 * n2;
        final double n5 = n - this.x2;
        final double n6 = n5 * n5;
        return this.a * n4 + this.b * n3 * n5 + this.c * n2 * n6 + this.d * (n6 * n5);
    }
    
    double derivativeValue(final double n, final int n2) {
        if (n2 == 0) {
            if (n == this.x1) {
                return this.y1;
            }
            if (n == this.x2) {
                return this.y2;
            }
            return this.value(n);
        }
        else {
            if (n < this.x1 || n > this.x2 || Double.isNaN(n)) {
                return Double.NaN;
            }
            if (n2 > 3) {
                return 0.0;
            }
            if (n2 == 3) {
                return 6.0 * (2.0 * this.a + this.b + this.c);
            }
            if (n2 == 2) {
                return 2.0 * ((3.0 * this.a + 2.0 * this.b + this.c) * (n - this.x1) + (3.0 * this.d + 2.0 * this.c + this.b) * (n - this.x2));
            }
            return (3.0 * this.a + this.b) * (n - this.x1) * (n - this.x1) + 2.0 * (this.b + this.c) * (n - this.x1) * (n - this.x2) + (3.0 * this.d + this.c) * (n - this.x2) * (n - this.x2);
        }
    }
    
    boolean join(final BezierSegment bezierSegment) {
        if (bezierSegment == null || this.x2 != bezierSegment.x1) {
            return false;
        }
        final BezierSegment rightNeighbor = bezierSegment.rightNeighbor;
        if (rightNeighbor != null) {
            rightNeighbor.leftNeighbor = this;
            this.rightNeighbor = rightNeighbor;
            this.smoothOnRight = this.rightNeighbor.smoothOnLeft;
        }
        else {
            this.rightNeighbor = null;
        }
        this.d2 = bezierSegment.d2;
        this.y2 = bezierSegment.y2;
        this.x2 = bezierSegment.x2;
        this.x2NotInDomain = bezierSegment.x2NotInDomain;
        this.computeCoefficients();
        return true;
    }
    
    BezierSegment splitAtNewPoint(final double x2, final boolean b) {
        if (x2 <= this.x1 || x2 >= this.x2 || (x2 == this.x1 && this.x1NotInDomain) || (x2 == this.x2 && this.x2NotInDomain) || Double.isNaN(x2)) {
            return null;
        }
        final BezierSegment bezierSegment = new BezierSegment();
        final double value = this.value(x2);
        final double derivativeValue = this.derivativeValue(x2, 1);
        bezierSegment.setData(x2, this.x2, value, this.y2, derivativeValue, this.d2);
        bezierSegment.x2NotInDomain = this.x2NotInDomain;
        this.x2 = x2;
        this.y2 = value;
        this.d2 = derivativeValue;
        this.computeCoefficients();
        if (b) {
            this.x2NotInDomain = true;
        }
        else {
            bezierSegment.x1NotInDomain = true;
        }
        final BezierSegment rightNeighbor = this.rightNeighbor;
        bezierSegment.rightNeighbor = this.rightNeighbor;
        bezierSegment.smoothOnRight = this.smoothOnRight;
        if (rightNeighbor != null) {
            rightNeighbor.leftNeighbor = bezierSegment;
        }
        this.rightNeighbor = bezierSegment;
        this.smoothOnRight = true;
        bezierSegment.leftNeighbor = this;
        bezierSegment.smoothOnLeft = true;
        return bezierSegment;
    }
    
    public BezierSegment doNewPoint(final double n, final double n2, final double n3, final double n4) {
        if (n < this.x1 + 2.0 * n3 || n > this.x2 - 2.0 * n3) {
            return null;
        }
        if (Math.abs(n2 - this.value(n)) > 10.0 * n4) {
            return null;
        }
        return this.splitAtNewPoint(n, false);
    }
    
    void rescale(final double[] array, final double[] array2) {
        this.x1 = array2[0] + (this.x1 - array[0]) * (array2[1] - array2[0]) / (array[1] - array[0]);
        this.y1 = array2[2] + (this.y1 - array[2]) * (array2[3] - array2[2]) / (array[3] - array[2]);
        this.x2 = array2[0] + (this.x2 - array[0]) * (array2[1] - array2[0]) / (array[1] - array[0]);
        this.y2 = array2[2] + (this.y2 - array[2]) * (array2[3] - array2[2]) / (array[3] - array[2]);
        this.d1 = this.d1 * ((array[1] - array[0]) / (array[3] - array[2])) * ((array2[3] - array2[2]) / (array2[1] - array2[0]));
        this.d2 = this.d2 * ((array[1] - array[0]) / (array[3] - array[2])) * ((array2[3] - array2[2]) / (array2[1] - array2[0]));
        this.computeCoefficients();
    }
}
