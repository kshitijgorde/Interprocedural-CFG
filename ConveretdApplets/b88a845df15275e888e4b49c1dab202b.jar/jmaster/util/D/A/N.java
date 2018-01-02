// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

public class N extends H
{
    static final long K = -5329733893740770899L;
    private float I;
    private float H;
    private float J;
    
    public N() {
        this(1.0f);
    }
    
    public N(final float n) {
        this(n, n, n);
    }
    
    public N(final float n, final float n2, final float n3) {
        this.A(n, n2, n3);
    }
    
    public void A(final float i, final float h, final float j) {
        this.I = i;
        this.H = h;
        this.J = j;
        this.B = false;
    }
    
    public void D(final float n) {
        this.A(n, n, n);
    }
    
    public float E() {
        return this.I;
    }
    
    protected void B() {
        this.D = this.C(this.I);
        if (this.H == this.I) {
            this.C = this.D;
        }
        else {
            this.C = this.C(this.H);
        }
        if (this.J == this.I) {
            this.A = this.D;
        }
        else if (this.J == this.H) {
            this.A = this.C;
        }
        else {
            this.A = this.C(this.J);
        }
    }
    
    protected int[] C(final float n) {
        final int[] array = new int[256];
        for (int i = 0; i < 256; ++i) {
            array[i] = this.A(i);
            int n2 = (int)(255.0 * Math.pow(i / 255.0, 1.0 / n) + 0.5);
            if (n2 > 255) {
                n2 = 255;
            }
            array[i] = n2;
        }
        return array;
    }
    
    public String toString() {
        return "Colors/Gamma...";
    }
}
