// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.functions;

class CubicSegment
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
    
    CubicSegment() {
    }
    
    CubicSegment(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        this.setData(n, n2, n3, n4, n5, n6);
    }
    
    void setData(double x1, double x2, double y1, double y2, double d1, double d2) {
        if (x1 == x2) {
            throw new IllegalArgumentException("Attempt to make CubicSegment of length 0");
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
        final double n4 = this.x2 - this.x1;
        this.a = this.y2 / (n4 * n4 * n4);
        this.b = this.d2 / (n4 * n4) - 3.0 * this.a;
        final double n5 = -n4;
        this.d = this.y1 / (n5 * n5 * n5);
        this.c = this.d1 / (n5 * n5) - 3.0 * this.d;
    }
    
    void setDerivativesFromNeighbors(final double n, final double n2, final double n3, final double n4) {
        double n5;
        if (!Double.isNaN(n) && n < this.x1) {
            n5 = (this.y2 - n2) / (this.x2 - n);
        }
        else {
            n5 = (this.y2 - this.y1) / (this.x2 - this.x1);
        }
        double n6;
        if (!Double.isNaN(n3) && n3 > this.x2) {
            n6 = (n4 - this.y1) / (n3 - this.x1);
        }
        else {
            n6 = (this.y2 - this.y1) / (this.x2 - this.x1);
        }
        this.setData(this.x1, this.x2, this.y1, this.y2, n5, n6);
    }
    
    double value(final double n) {
        return this.derivativeValue(n, 0);
    }
    
    double derivativeValue(final double n, final int n2) {
        switch (n2) {
            case 0: {
                final double n3 = n - this.x1;
                final double n4 = n3 * n3;
                final double n5 = n4 * n3;
                final double n6 = n - this.x2;
                final double n7 = n6 * n6;
                return this.a * n5 + this.b * n4 * n6 + this.c * n3 * n7 + this.d * (n7 * n6);
            }
            case 1: {
                return (3.0 * this.a + this.b) * (n - this.x1) * (n - this.x1) + 2.0 * (this.b + this.c) * (n - this.x1) * (n - this.x2) + (3.0 * this.d + this.c) * (n - this.x2) * (n - this.x2);
            }
            case 2: {
                return 2.0 * ((3.0 * this.a + 2.0 * this.b + this.c) * (n - this.x1) + (3.0 * this.d + 2.0 * this.c + this.b) * (n - this.x2));
            }
            case 3: {
                return 6.0 * (2.0 * this.a + this.b + this.c);
            }
            default: {
                return 0.0;
            }
        }
    }
}
