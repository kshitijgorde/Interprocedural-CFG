// 
// Decompiled by Procyon v0.5.30
// 

public class BVector
{
    double[] c;
    
    BVector(final int n) {
        this.c = new double[n];
    }
    
    public final void scale(final double n) {
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] *= n;
        }
    }
    
    public final void copy(final BVector bVector) {
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = bVector.c[i];
        }
    }
    
    public final void zero() {
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = 0.0;
        }
    }
    
    public final int length() {
        return this.c.length;
    }
    
    public final double get(final int n) {
        return this.c[n];
    }
    
    public final void set(final int n, final double n2) {
        this.c[n] = n2;
    }
    
    public String toString() {
        String s = "(" + (float)this.c[0];
        for (int i = 1; i < this.c.length; ++i) {
            s = s + "," + (float)this.c[i];
        }
        return s + ")";
    }
    
    public final double dot(final BVector bVector) {
        double n = 0.0;
        for (int i = 0; i < this.c.length; ++i) {
            n += this.c[i] * bVector.c[i];
        }
        return n;
    }
    
    public final void minus(final BVector bVector, final BVector bVector2) {
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = bVector.c[i] - bVector2.c[i];
        }
    }
    
    public final void plus(final BVector bVector, final BVector bVector2) {
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = bVector.c[i] + bVector2.c[i];
        }
    }
    
    public final void plusa(final BVector bVector, final double n, final BVector bVector2) {
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = bVector.c[i] + n * bVector2.c[i];
        }
    }
    
    public final void mult(BVector bVector, BVector bVector2) {
        if (this == bVector) {
            final BVector bVector3 = new BVector(bVector.c.length);
            bVector3.copy(bVector);
            bVector = bVector3;
            if (this == bVector2) {
                bVector2 = bVector3;
            }
        }
        else if (this == bVector2) {
            final BVector bVector4 = new BVector(bVector2.c.length);
            bVector4.copy(bVector2);
            bVector2 = bVector4;
        }
        this.zero();
        for (int i = 0; i < this.c.length; ++i) {
            for (int n = 0; i + n < this.c.length; ++n) {
                final double[] c = this.c;
                final int n2 = i + n;
                c[n2] += bVector.c[i] * bVector2.c[n];
            }
        }
    }
    
    public final void multt(BVector bVector, final BMatrix bMatrix) {
        if (this == bVector) {
            final BVector bVector2 = new BVector(bVector.c.length);
            bVector2.copy(bVector);
            bVector = bVector2;
        }
        this.zero();
        for (int i = 0; i < this.c.length; ++i) {
            for (int j = 0; j < this.c.length; ++j) {
                final double[] c = this.c;
                final int n = i;
                c[n] += bVector.c[j] * bMatrix.d[j].c[i];
            }
        }
    }
    
    public final void mult(BVector bVector, final BMatrix bMatrix) {
        if (this == bVector) {
            final BVector bVector2 = new BVector(bVector.c.length);
            bVector2.copy(bVector);
            bVector = bVector2;
        }
        this.zero();
        for (int i = 0; i < this.c.length; ++i) {
            for (int j = 0; j < this.c.length; ++j) {
                final double[] c = this.c;
                final int n = i;
                c[n] += bVector.c[j] * bMatrix.d[i].c[j];
            }
        }
    }
    
    public final double eval(final double n) {
        double n2 = 0.0;
        double n3 = 1.0;
        for (int i = 0; i < this.c.length; ++i) {
            n2 += this.c[i] * n3;
            n3 *= n;
        }
        return n2;
    }
}
