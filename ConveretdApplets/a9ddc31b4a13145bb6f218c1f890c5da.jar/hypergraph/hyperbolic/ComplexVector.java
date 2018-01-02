// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

public class ComplexVector implements ModelVector
{
    public Complex base;
    public Complex v;
    
    public ComplexVector() {
        this(null, null);
    }
    
    public ComplexVector(Complex base, Complex v) {
        if (base == null) {
            base = new Complex();
        }
        if (v == null) {
            v = new Complex();
        }
        this.base = base;
        this.v = v;
    }
    
    public void setBase(final ModelPoint modelPoint) {
        this.base = (Complex)modelPoint;
        this.v = new Complex();
    }
    
    public ModelPoint getBase() {
        return this.base;
    }
    
    public void scale(final double n) {
        final Complex v = this.v;
        v.real *= n;
        final Complex v2 = this.v;
        v2.imag *= n;
    }
    
    public void setTo(final ModelVector modelVector) {
        this.base.real = ((ComplexVector)modelVector).base.real;
        this.base.imag = ((ComplexVector)modelVector).base.imag;
        this.v.real = ((ComplexVector)modelVector).v.real;
        this.v.imag = ((ComplexVector)modelVector).v.imag;
    }
    
    public Object clone() {
        return new ComplexVector((Complex)this.base.clone(), (Complex)this.v.clone());
    }
    
    public String toString() {
        return "(" + this.base + ";" + this.v + ")";
    }
}
