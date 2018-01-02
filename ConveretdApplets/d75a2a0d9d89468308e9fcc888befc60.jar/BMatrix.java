// 
// Decompiled by Procyon v0.5.30
// 

public final class BMatrix
{
    BVector[] d;
    
    BMatrix(final int n) {
        this.d = new BVector[n];
        for (int i = 0; i < n; ++i) {
            this.d[i] = new BVector(n);
        }
    }
    
    public final void scale(final double n) {
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].scale(n);
        }
    }
    
    public final void copy(final BMatrix bMatrix) {
        if (this == bMatrix) {
            return;
        }
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].copy(bMatrix.d[i]);
        }
    }
    
    public final void copyx(final BVector bVector, final int n) {
        bVector.copy(this.d[n]);
    }
    
    public final void copy(final BVector bVector, final int n) {
        for (int i = 0; i < this.d.length; ++i) {
            bVector.c[i] = this.d[i].c[n];
        }
    }
    
    public final void zero() {
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].zero();
        }
    }
    
    public final int length() {
        return this.d.length;
    }
    
    public final double get(final int n, final int n2) {
        return this.d[n].c[n2];
    }
    
    public final void set(final int n, final int n2, final double n3) {
        this.d[n].c[n2] = n3;
    }
    
    public final void setx(final int n, final BVector bVector) {
        this.d[n].copy(bVector);
    }
    
    public final void sety(final int n, final BVector bVector) {
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].c[n] = bVector.c[i];
        }
    }
    
    public String toString() {
        String s = this.d.length + "x" + this.d.length + " matrix:\n";
        for (int i = 0; i < this.d.length; ++i) {
            s = s + this.d[i].toString() + "\n";
        }
        return s;
    }
    
    public final void minus(final BMatrix bMatrix, final BMatrix bMatrix2) {
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].minus(bMatrix.d[i], bMatrix2.d[i]);
        }
    }
    
    public final void plus(final BMatrix bMatrix, final BMatrix bMatrix2) {
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].plus(bMatrix.d[i], bMatrix2.d[i]);
        }
    }
    
    public final void plusa(final BMatrix bMatrix, final double n, final BMatrix bMatrix2) {
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].plusa(bMatrix.d[i], n, bMatrix2.d[i]);
        }
    }
    
    public final void mult(final BMatrix bMatrix) {
        for (int i = 0; i < this.d.length; ++i) {
            for (int j = 0; j < i; ++j) {
                final double n = this.d[i].c[j];
                this.d[i].c[j] = this.d[j].c[i];
                this.d[j].c[i] = n;
            }
        }
    }
    
    public final void mult(BMatrix bMatrix, BMatrix bMatrix2) {
        if (this == bMatrix) {
            final BMatrix bMatrix3 = new BMatrix(this.d.length);
            bMatrix3.copy(this);
            bMatrix = bMatrix3;
            if (this == bMatrix2) {
                bMatrix2 = bMatrix3;
            }
        }
        else if (this == bMatrix2) {
            final BMatrix bMatrix4 = new BMatrix(this.d.length);
            bMatrix4.copy(this);
            bMatrix2 = bMatrix4;
        }
        this.zero();
        for (int i = 0; i < this.d.length; ++i) {
            for (int j = 0; j < this.d.length; ++j) {
                for (int k = 0; k < this.d.length; ++k) {
                    final double[] c = this.d[i].c;
                    final int n = k;
                    c[n] += bMatrix.d[i].c[j] * bMatrix2.d[j].c[k];
                }
            }
        }
    }
    
    public final void multt(BMatrix bMatrix, BMatrix bMatrix2) {
        if (this == bMatrix) {
            final BMatrix bMatrix3 = new BMatrix(this.d.length);
            bMatrix3.copy(this);
            bMatrix = bMatrix3;
            if (this == bMatrix2) {
                bMatrix2 = bMatrix3;
            }
        }
        else if (this == bMatrix2) {
            final BMatrix bMatrix4 = new BMatrix(this.d.length);
            bMatrix4.copy(this);
            bMatrix2 = bMatrix4;
        }
        this.zero();
        for (int i = 0; i < this.d.length; ++i) {
            for (int j = 0; j < this.d.length; ++j) {
                for (int k = 0; k < this.d.length; ++k) {
                    final double[] c = this.d[i].c;
                    final int n = k;
                    c[n] += bMatrix.d[i].c[j] * bMatrix2.d[k].c[j];
                }
            }
        }
    }
    
    public final void inverse(final BMatrix bMatrix) {
        final int[] array = new int[this.d.length];
        this.copy(bMatrix);
        for (int i = 0; i < this.d.length; ++i) {
            array[i] = i;
        }
        for (int j = 0; j < this.d.length; ++j) {
            double n = (this.d[j].c[j] > 0.0) ? this.d[j].c[j] : (-this.d[j].c[j]);
            int n2 = j;
            for (int k = j + 1; k < this.d.length; ++k) {
                final double n3;
                if ((n3 = ((this.d[k].c[j] > 0.0) ? this.d[k].c[j] : (-this.d[k].c[j]))) > n) {
                    n = n3;
                    n2 = k;
                }
            }
            if (n == 0.0) {
                System.out.println("Singular matrix!  No inverse!");
                System.out.println(this.toString());
                return;
            }
            if (n2 > j) {
                final BVector bVector = this.d[j];
                this.d[j] = this.d[n2];
                this.d[n2] = bVector;
                final int n4 = array[j];
                array[j] = array[n2];
                array[n2] = n4;
            }
            final double n5 = 1.0 / this.d[j].c[j];
            for (int l = 0; l < this.d.length; ++l) {
                this.d[l].c[j] *= n5;
            }
            this.d[j].c[j] = n5;
            for (int n6 = 0; n6 < this.d.length; ++n6) {
                final double n7 = this.d[j].c[n6];
                if (n6 != j) {
                    for (int n8 = 0; n8 < this.d.length; ++n8) {
                        if (n8 == j) {
                            this.d[j].c[n6] = -n5 * n7;
                        }
                        else {
                            this.d[n8].c[n6] -= this.d[n8].c[j] * n7;
                        }
                    }
                }
            }
        }
        final BVector bVector2 = new BVector(this.d.length);
        for (int n9 = 0; n9 < this.d.length; ++n9) {
            for (int n10 = 0; n10 < this.d.length; ++n10) {
                bVector2.c[array[n10]] = this.d[n9].c[n10];
            }
            for (int n11 = 0; n11 < this.d.length; ++n11) {
                this.d[n9].c[n11] = bVector2.c[n11];
            }
        }
    }
}
