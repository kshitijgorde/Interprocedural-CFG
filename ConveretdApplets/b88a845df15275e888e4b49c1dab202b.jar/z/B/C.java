// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.DataBuffer;

public class C extends DataBuffer
{
    protected float[][] B;
    protected float[] A;
    
    public C(final int n) {
        super(4, n);
        this.A = new float[n];
        (this.B = new float[1][])[0] = this.A;
    }
    
    public C(final int n, final int n2) {
        super(4, n, n2);
        this.B = new float[n2][];
        for (int i = 0; i < n2; ++i) {
            this.B[i] = new float[n];
        }
        this.A = this.B[0];
    }
    
    public C(final float[] a, final int n) {
        super(4, n);
        if (a.length < n) {
            throw new RuntimeException(m.A("DataBuffer0"));
        }
        this.A = a;
        (this.B = new float[1][])[0] = this.A;
    }
    
    public C(final float[] a, final int n, final int n2) {
        super(4, n, 1, n2);
        if (a.length < n) {
            throw new RuntimeException(m.A("DataBuffer1"));
        }
        this.A = a;
        (this.B = new float[1][])[0] = this.A;
    }
    
    public C(final float[][] b, final int n) {
        super(4, n, b.length);
        this.B = b;
        this.A = this.B[0];
    }
    
    public C(final float[][] b, final int n, final int[] array) {
        super(4, n, b.length, array);
        this.B = b;
        this.A = this.B[0];
    }
    
    public float[] B() {
        return this.A;
    }
    
    public float[] A(final int n) {
        return this.B[n];
    }
    
    public float[][] A() {
        return this.B;
    }
    
    public int getElem(final int n) {
        return Math.round(this.A[n + this.offset]);
    }
    
    public int getElem(final int n, final int n2) {
        return Math.round(this.B[n][n2 + this.offsets[n]]);
    }
    
    public void setElem(final int n, final int n2) {
        this.A[n + this.offset] = n2;
    }
    
    public void setElem(final int n, final int n2, final int n3) {
        this.B[n][n2 + this.offsets[n]] = n3;
    }
    
    public float getElemFloat(final int n) {
        return this.A[n + this.offset];
    }
    
    public float getElemFloat(final int n, final int n2) {
        return this.B[n][n2 + this.offsets[n]];
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
        this.A[n + this.offset] = (float)n2;
    }
    
    public void setElemDouble(final int n, final int n2, final double n3) {
        this.B[n][n2 + this.offsets[n]] = (float)n3;
    }
}
