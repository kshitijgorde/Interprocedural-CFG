// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.DataBuffer;

public class q extends DataBuffer
{
    protected double[][] B;
    protected double[] A;
    
    public q(final int n) {
        super(5, n);
        this.A = new double[n];
        (this.B = new double[1][])[0] = this.A;
    }
    
    public q(final int n, final int n2) {
        super(5, n, n2);
        this.B = new double[n2][];
        for (int i = 0; i < n2; ++i) {
            this.B[i] = new double[n];
        }
        this.A = this.B[0];
    }
    
    public q(final double[] a, final int n) {
        super(5, n);
        if (a.length < n) {
            throw new RuntimeException(m.A("DataBuffer0"));
        }
        this.A = a;
        (this.B = new double[1][])[0] = this.A;
    }
    
    public q(final double[] a, final int n, final int n2) {
        super(5, n, 1, n2);
        if (a.length < n) {
            throw new RuntimeException(m.A("DataBuffer1"));
        }
        this.A = a;
        (this.B = new double[1][])[0] = this.A;
    }
    
    public q(final double[][] b, final int n) {
        super(5, n, b.length);
        this.B = b;
        this.A = this.B[0];
    }
    
    public q(final double[][] b, final int n, final int[] array) {
        super(5, n, b.length, array);
        this.B = b;
        this.A = this.B[0];
    }
    
    public double[] A() {
        return this.A;
    }
    
    public double[] A(final int n) {
        return this.B[n];
    }
    
    public double[][] B() {
        return this.B;
    }
    
    public int getElem(final int n) {
        return (int)this.A[n + this.offset];
    }
    
    public int getElem(final int n, final int n2) {
        return (int)this.B[n][n2 + this.offsets[n]];
    }
    
    public void setElem(final int n, final int n2) {
        this.A[n + this.offset] = n2;
    }
    
    public void setElem(final int n, final int n2, final int n3) {
        this.B[n][n2 + this.offsets[n]] = n3;
    }
    
    public float getElemFloat(final int n) {
        return (float)this.A[n + this.offset];
    }
    
    public float getElemFloat(final int n, final int n2) {
        return (float)this.B[n][n2 + this.offsets[n]];
    }
    
    public void setElemFloat(final int n, final float n2) {
        this.A[n + this.offset] = n2;
    }
    
    public void setElemFloat(final int n, final int n2, final float n3) {
        this.B[n][n2 + this.offsets[n]] = n3;
    }
    
    public double getElemDouble(final int n) {
        return this.A[n + this.offset];
    }
    
    public double getElemDouble(final int n, final int n2) {
        return this.B[n][n2 + this.offsets[n]];
    }
    
    public void setElemDouble(final int n, final double n2) {
        this.A[n + this.offset] = n2;
    }
    
    public void setElemDouble(final int n, final int n2, final double n3) {
        this.B[n][n2 + this.offsets[n]] = n3;
    }
}
