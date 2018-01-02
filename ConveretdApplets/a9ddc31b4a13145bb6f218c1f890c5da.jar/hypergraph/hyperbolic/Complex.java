// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

public class Complex implements ModelPoint
{
    double real;
    double imag;
    private final double epsilon = 1.0E-10;
    
    public static Complex getRotation(final double n) {
        return new Complex(Math.cos(n), Math.sin(n));
    }
    
    public Complex() {
        this(0.0, 0.0);
    }
    
    public Complex(final double[] array) {
        if (array.length > 0) {
            this.real = array[0];
        }
        if (array.length > 1) {
            this.imag = array[1];
        }
    }
    
    public Complex(final Complex complex) {
        this(complex.real, complex.imag);
    }
    
    public Complex(final double real) {
        this.real = real;
        this.imag = 0.0;
    }
    
    public Complex(final double real, final double imag) {
        this.real = real;
        this.imag = imag;
    }
    
    public void setTo(final ModelPoint modelPoint) {
        if (modelPoint == null) {
            return;
        }
        this.real = ((Complex)modelPoint).real;
        this.imag = ((Complex)modelPoint).imag;
    }
    
    public boolean equals(final Object o) {
        return Math.abs(((Complex)o).real - this.real) < 1.0E-10 && Math.abs(((Complex)o).imag - this.imag) < 1.0E-10;
    }
    
    public int hashCode() {
        return new Double(this.real).hashCode() + new Double(this.imag).hashCode();
    }
    
    public double[] toArray() {
        return new double[] { this.real, this.imag };
    }
    
    public void setReal(final double real) {
        if (real == Double.NaN) {
            throw new IllegalArgumentException();
        }
        this.real = real;
    }
    
    public double getReal() {
        return this.real;
    }
    
    public void setImag(final double imag) {
        if (imag == Double.NaN) {
            throw new IllegalArgumentException();
        }
        this.imag = imag;
    }
    
    public double getImag() {
        return this.imag;
    }
    
    public double getRad() {
        double acos = Math.acos(this.real / this.norm());
        if (this.imag < 0.0) {
            acos = -acos;
        }
        return acos;
    }
    
    public void add(final double n) {
        this.real += n;
    }
    
    public void add(final Complex complex) {
        this.real += complex.real;
        this.imag += complex.imag;
    }
    
    public void subtract(final Complex complex) {
        this.real -= complex.real;
        this.imag -= complex.imag;
    }
    
    public void multiply(final double n) {
        this.real *= n;
        this.imag *= n;
    }
    
    public void multiply(final Complex complex) {
        final double real = this.real * complex.real - this.imag * complex.imag;
        this.imag = this.real * complex.imag + this.imag * complex.real;
        this.real = real;
    }
    
    public void reciprocal() {
        this.imag = -this.imag;
        this.divide(this.norm2());
    }
    
    public Complex getReciprocal() {
        final Complex complex = new Complex(this);
        complex.reciprocal();
        return complex;
    }
    
    public void divide(final double n) {
        this.real /= n;
        this.imag /= n;
    }
    
    public void divide(final Complex complex) {
        complex.conjugate();
        this.multiply(complex);
        complex.conjugate();
        this.divide(complex.norm2());
    }
    
    public void conjugate() {
        this.imag = -this.imag;
    }
    
    public void normalize() {
        this.multiply(1.0 / this.norm());
    }
    
    public double dist(final Complex complex) {
        if (complex == null) {
            return Double.NaN;
        }
        return Math.sqrt((this.real - complex.real) * (this.real - complex.real) + (this.imag - complex.imag) * (this.imag - complex.imag));
    }
    
    public double dist2(final Complex complex) {
        if (complex == null) {
            return Double.NaN;
        }
        return (this.real - complex.real) * (this.real - complex.real) + (this.imag - complex.imag) * (this.imag - complex.imag);
    }
    
    public double norm2() {
        return this.real * this.real + this.imag * this.imag;
    }
    
    public double norm() {
        return Math.sqrt(this.real * this.real + this.imag * this.imag);
    }
    
    double scalarProduct(final Complex complex) {
        return this.real * complex.real + this.imag * complex.imag;
    }
    
    public Object clone() {
        return new Complex(this);
    }
    
    public String toString() {
        return this.real + "+i*" + this.imag;
    }
}
