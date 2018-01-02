// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

public class PoincareModel extends AbstractModel
{
    private ComplexVector v1;
    
    public PoincareModel() {
        this.v1 = new ComplexVector();
        this.setViewMatrix(null);
    }
    
    public ModelPoint getOrigin() {
        return new Complex();
    }
    
    public hypergraph.hyperbolic.Isometry getIdentity() {
        return new Isometry();
    }
    
    public hypergraph.hyperbolic.Isometry getRotation(final double n) {
        return new Isometry(new Complex(Complex.getRotation(n / 2.0)), new Complex());
    }
    
    public void getTranslation(final hypergraph.hyperbolic.Isometry isometry, final ModelPoint modelPoint, final double n) {
        final Complex complex = (Complex)modelPoint;
        double n2 = 1.0;
        if (Math.abs(n - 1.0) > 0.01) {
            final double n3 = this.dist(complex) * n;
            n2 = (Math.exp(n3) - 1.0) / (Math.exp(n3) + 1.0) / complex.norm();
        }
        ((Isometry)isometry).a.real = 1.0;
        ((Isometry)isometry).a.imag = 0.0;
        ((Isometry)isometry).c.real = complex.real * n2;
        ((Isometry)isometry).c.imag = -complex.imag * n2;
    }
    
    public void setOrientation() {
    }
    
    public double getDistance(final ModelPoint modelPoint, final ModelPoint modelPoint2, final ModelPoint modelPoint3, final boolean b, final boolean b2) {
        return this.dist(modelPoint, this.getProjection(modelPoint, modelPoint2, modelPoint3, b, b2));
    }
    
    public double getAngle(final ModelPoint modelPoint, final ModelPoint modelPoint2, final ModelPoint modelPoint3) {
        final double dist = this.dist(modelPoint, modelPoint2);
        if (dist == 0.0) {
            return 0.0;
        }
        final double dist2 = this.dist(modelPoint, modelPoint3);
        if (dist2 == 0.0) {
            return 0.0;
        }
        final double dist3 = this.dist(modelPoint2, modelPoint3);
        if (dist3 == 0.0) {
            return 0.0;
        }
        double n = (Functions.cosh(dist) * Functions.cosh(dist2) - Functions.cosh(dist3)) / (Functions.sinh(dist) * Functions.sinh(dist2));
        if (n > 1.0) {
            n = 1.0;
        }
        if (n < -1.0) {
            n = -1.0;
        }
        return Math.acos(n);
    }
    
    public ModelPoint getProjection(final ModelPoint modelPoint, final ModelPoint modelPoint2, final ModelPoint modelPoint3, final boolean b, final boolean b2) {
        final double angle = this.getAngle(modelPoint2, modelPoint, modelPoint3);
        ModelPoint modelPoint6;
        if (Math.abs(angle) > 1.0E-7) {
            final double arcosh = Functions.arcosh(Functions.cosh(this.dist(modelPoint, modelPoint2)) / Functions.cosh(Functions.arsinh(Functions.sinh(this.dist(modelPoint2, modelPoint)) * Math.sin(angle))));
            final ModelPoint modelPoint4 = (ModelPoint)modelPoint2.clone();
            this.getTranslation(modelPoint2, modelPoint3, arcosh / this.dist(modelPoint2, modelPoint3)).apply(modelPoint4);
            final ModelPoint modelPoint5 = (ModelPoint)modelPoint2.clone();
            this.getTranslation(modelPoint2, modelPoint3, -arcosh / this.dist(modelPoint2, modelPoint3)).apply(modelPoint5);
            if (this.dist(modelPoint, modelPoint4) > this.dist(modelPoint, modelPoint5)) {
                modelPoint6 = modelPoint5;
            }
            else {
                modelPoint6 = modelPoint4;
            }
        }
        else {
            modelPoint6 = modelPoint;
        }
        if (!b && !b2) {
            return modelPoint6;
        }
        if (this.getAngle(modelPoint6, modelPoint2, modelPoint3) > 1.5707963267948966) {
            return modelPoint6;
        }
        if (this.dist(modelPoint6, modelPoint2) < this.dist(modelPoint6, modelPoint3)) {
            if (b) {
                return modelPoint2;
            }
            return modelPoint6;
        }
        else {
            if (b2) {
                return modelPoint3;
            }
            return modelPoint6;
        }
    }
    
    public double dist(final ModelPoint modelPoint) {
        final double norm = ((Complex)modelPoint).norm();
        return Math.log((1.0 + norm) / (1.0 - norm));
    }
    
    public double dist(final ModelPoint modelPoint, final ModelPoint modelPoint2) {
        final double real = ((Complex)modelPoint).real;
        final double imag = ((Complex)modelPoint).imag;
        final double real2 = ((Complex)modelPoint2).real;
        final double imag2 = ((Complex)modelPoint2).imag;
        final double n = real - real2;
        final double n2 = imag - imag2;
        final double n3 = 1.0 - real * real2 - imag * imag2;
        final double n4 = real * imag2 - imag * real2;
        final double sqrt = Math.sqrt((n * n + n2 * n2) / (n3 * n3 + n4 * n4));
        return Math.log((1.0 + sqrt) / (1.0 - sqrt));
    }
    
    double getReciprocalScale(final ModelPoint modelPoint) {
        return (1.0 - ((Complex)modelPoint).norm2()) / 2.0;
    }
    
    double getScale(final ModelPoint modelPoint) {
        return 2.0 / (1.0 - ((Complex)modelPoint).norm2());
    }
    
    public double length2(final ModelVector modelVector) {
        final double n = ((ComplexVector)modelVector).v.real * ((ComplexVector)modelVector).v.real + ((ComplexVector)modelVector).v.imag * ((ComplexVector)modelVector).v.imag;
        final double n2 = 2.0 / (1.0 - (((ComplexVector)modelVector).base.real * ((ComplexVector)modelVector).base.real + ((ComplexVector)modelVector).base.imag * ((ComplexVector)modelVector).base.imag));
        return n2 * n2 * n;
    }
    
    public double length(final ModelVector modelVector) {
        return 2.0 / (1.0 - (((ComplexVector)modelVector).base.real * ((ComplexVector)modelVector).base.real + ((ComplexVector)modelVector).base.imag * ((ComplexVector)modelVector).base.imag)) * Math.sqrt(((ComplexVector)modelVector).v.real * ((ComplexVector)modelVector).v.real + ((ComplexVector)modelVector).v.imag * ((ComplexVector)modelVector).v.imag);
    }
    
    public double product(final ModelVector modelVector, final ModelVector modelVector2) {
        if (modelVector.getBase().equals(modelVector2.getBase())) {
            final double n = 2.0 / (1.0 - ((ComplexVector)modelVector).base.norm2());
            return n * n * (((ComplexVector)modelVector).v.real * ((ComplexVector)modelVector2).v.real + ((ComplexVector)modelVector).v.imag * ((ComplexVector)modelVector2).v.imag);
        }
        return Double.NaN;
    }
    
    public ModelPoint exp(final ModelVector modelVector, final double n) {
        final ComplexVector complexVector = (ComplexVector)modelVector;
        if (complexVector.v.norm2() == 0.0) {
            return (ModelPoint)modelVector.getBase().clone();
        }
        final double exp = Math.exp(n);
        final double n2 = (exp - 1.0) / (exp + 1.0);
        final Complex complex = new Complex(complexVector.v);
        complex.multiply(n2 / complexVector.v.norm());
        this.getTranslation(this.isom1, complexVector.base);
        this.isom1.apply(complex);
        return complex;
    }
    
    public ModelVector getDefaultVector() {
        return new ComplexVector(new Complex(), new Complex(0.5, 0.0));
    }
    
    public void distanceGradient(final ModelPoint modelPoint, final ModelPoint modelPoint2, final ModelVector modelVector) {
        ((Isometry)this.isom1).a.real = 1.0;
        ((Isometry)this.isom1).a.imag = 0.0;
        ((Isometry)this.isom1).c.real = -((Complex)modelPoint).real;
        ((Isometry)this.isom1).c.imag = ((Complex)modelPoint).imag;
        ((Complex)this.z1).real = ((Complex)modelPoint2).real;
        ((Complex)this.z1).imag = ((Complex)modelPoint2).imag;
        this.isom1.apply(this.z1);
        final double n = 2.0 * Math.sqrt(((Complex)this.z1).real * ((Complex)this.z1).real + ((Complex)this.z1).imag * ((Complex)this.z1).imag);
        this.v1.base.real = 0.0;
        this.v1.base.imag = 0.0;
        this.v1.v.real = -((Complex)this.z1).real / n;
        this.v1.v.imag = -((Complex)this.z1).imag / n;
        ((Isometry)this.isom1).a.real = 1.0;
        ((Isometry)this.isom1).a.imag = 0.0;
        ((Isometry)this.isom1).c.real = ((Complex)modelPoint).real;
        ((Isometry)this.isom1).c.imag = -((Complex)modelPoint).imag;
        this.isom1.apply(this.v1);
        modelVector.setTo(this.v1);
    }
    
    public ModelVector distanceGradient(final ModelPoint modelPoint, final ModelPoint modelPoint2) {
        final ComplexVector complexVector = new ComplexVector();
        this.distanceGradient(modelPoint, modelPoint2, complexVector);
        return complexVector;
    }
    
    public class Isometry implements hypergraph.hyperbolic.Isometry
    {
        Complex a;
        Complex c;
        
        public Isometry() {
            this.a = new Complex(1.0);
            this.c = new Complex();
        }
        
        public Isometry(final Isometry isometry) {
            this.a = new Complex(isometry.a);
            this.c = new Complex(isometry.c);
        }
        
        public Isometry(final PoincareModel poincareModel, final ModelPoint modelPoint, final ModelPoint modelPoint2) {
            this(poincareModel, ((Complex)modelPoint).getReal(), ((Complex)modelPoint).getImag(), ((Complex)modelPoint2).getReal(), ((Complex)modelPoint2).getImag());
        }
        
        public Isometry(final double n, final double n2, final double n3, final double n4) {
            this.a = new Complex(n, n2);
            this.c = new Complex(n3, n4);
        }
        
        public boolean equals(final Object o) {
            return this.a.equals(((Isometry)o).a) && this.c.equals(((Isometry)o).c);
        }
        
        public void setToIdentity() {
            this.a.real = 1.0;
            this.a.imag = 0.0;
            this.c.real = 0.0;
            this.c.imag = 0.0;
        }
        
        public void multiplyRight(final hypergraph.hyperbolic.Isometry isometry) {
            final Isometry isometry2 = (Isometry)isometry;
            final double real = isometry2.a.real * this.a.real - isometry2.a.imag * this.a.imag + isometry2.c.real * this.c.real + isometry2.c.imag * this.c.imag;
            final double imag = isometry2.a.real * this.a.imag + isometry2.a.imag * this.a.real - isometry2.c.real * this.c.imag + isometry2.c.imag * this.c.real;
            final double real2 = isometry2.a.real * this.c.real - isometry2.a.imag * this.c.imag + isometry2.c.real * this.a.real + isometry2.c.imag * this.a.imag;
            final double imag2 = isometry2.a.real * this.c.imag + isometry2.a.imag * this.c.real - isometry2.c.real * this.a.imag + isometry2.c.imag * this.a.real;
            this.a.real = real;
            this.a.imag = imag;
            this.c.real = real2;
            this.c.imag = imag2;
        }
        
        public void multiplyLeft(final hypergraph.hyperbolic.Isometry isometry) {
            final Isometry isometry2 = new Isometry((Isometry)isometry);
            isometry2.multiplyRight(this);
            this.a = new Complex(isometry2.a);
            this.c = new Complex(isometry2.c);
        }
        
        public void apply(final ModelPoint modelPoint) {
            if (modelPoint == null) {
                return;
            }
            final double n = this.a.real * ((Complex)modelPoint).real - this.a.imag * ((Complex)modelPoint).imag + this.c.real;
            final double n2 = this.a.real * ((Complex)modelPoint).imag + this.a.imag * ((Complex)modelPoint).real - this.c.imag;
            final double n3 = this.c.real * ((Complex)modelPoint).real - this.c.imag * ((Complex)modelPoint).imag + this.a.real;
            final double n4 = this.c.real * ((Complex)modelPoint).imag + this.c.imag * ((Complex)modelPoint).real - this.a.imag;
            final double n5 = n3 * n3 + n4 * n4;
            ((Complex)modelPoint).real = (n * n3 + n2 * n4) / n5;
            ((Complex)modelPoint).imag = (-n * n4 + n2 * n3) / n5;
        }
        
        public void apply(final ModelVector modelVector) {
            final ComplexVector complexVector = (ComplexVector)modelVector;
            PoincareModel.this.z3.setTo(this.c);
            ((Complex)PoincareModel.this.z3).multiply(complexVector.base);
            this.a.conjugate();
            ((Complex)PoincareModel.this.z3).add(this.a);
            this.a.conjugate();
            ((Complex)PoincareModel.this.z3).multiply((Complex)PoincareModel.this.z3);
            ((Complex)PoincareModel.this.z3).reciprocal();
            ((Complex)PoincareModel.this.z3).multiply(this.a.norm2() - this.c.norm2());
            complexVector.v.multiply((Complex)PoincareModel.this.z3);
            this.apply(complexVector.base);
        }
        
        public hypergraph.hyperbolic.Isometry getInvers() {
            return new Isometry(new Complex(this.a.getReal(), -this.a.getImag()), new Complex(-this.c.getReal(), -this.c.getImag()));
        }
        
        public void invert() {
            this.a.imag = -this.a.imag;
            this.c.real = -this.c.real;
            this.c.imag = -this.c.imag;
        }
        
        public void setTo(final hypergraph.hyperbolic.Isometry isometry) {
            this.a.real = ((Isometry)isometry).a.real;
            this.a.imag = ((Isometry)isometry).a.imag;
            this.c.real = ((Isometry)isometry).c.real;
            this.c.imag = ((Isometry)isometry).c.imag;
        }
        
        public Object clone() {
            return new Isometry(this);
        }
        
        public String toString() {
            return "( " + this.a + " ; " + this.c + " )";
        }
    }
}
